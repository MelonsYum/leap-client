/*    */ package net.minecraft.world.storage;
/*    */ 
/*    */ import com.google.common.collect.Lists;
/*    */ import java.util.Collections;
/*    */ import java.util.List;
/*    */ 
/*    */ public class ThreadedFileIOBase
/*    */   implements Runnable
/*    */ {
/* 10 */   private static final ThreadedFileIOBase threadedIOInstance = new ThreadedFileIOBase();
/* 11 */   private List threadedIOQueue = Collections.synchronizedList(Lists.newArrayList());
/*    */   
/*    */   private volatile long writeQueuedCounter;
/*    */   private volatile long savedIOCounter;
/*    */   private volatile boolean isThreadWaiting;
/*    */   private static final String __OBFID = "CL_00000605";
/*    */   
/*    */   private ThreadedFileIOBase() {
/* 19 */     Thread var1 = new Thread(this, "File IO Thread");
/* 20 */     var1.setPriority(1);
/* 21 */     var1.start();
/*    */   }
/*    */ 
/*    */   
/*    */   public static ThreadedFileIOBase func_178779_a() {
/* 26 */     return threadedIOInstance;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void run() {
/*    */     while (true) {
/* 33 */       processQueue();
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   private void processQueue() {
/* 42 */     for (int var1 = 0; var1 < this.threadedIOQueue.size(); var1++) {
/*    */       
/* 44 */       IThreadedFileIO var2 = this.threadedIOQueue.get(var1);
/* 45 */       boolean var3 = var2.writeNextIO();
/*    */       
/* 47 */       if (!var3) {
/*    */         
/* 49 */         this.threadedIOQueue.remove(var1--);
/* 50 */         this.savedIOCounter++;
/*    */       } 
/*    */ 
/*    */       
/*    */       try {
/* 55 */         Thread.sleep(this.isThreadWaiting ? 0L : 10L);
/*    */       }
/* 57 */       catch (InterruptedException var6) {
/*    */         
/* 59 */         var6.printStackTrace();
/*    */       } 
/*    */     } 
/*    */     
/* 63 */     if (this.threadedIOQueue.isEmpty()) {
/*    */       
/*    */       try {
/*    */         
/* 67 */         Thread.sleep(25L);
/*    */       }
/* 69 */       catch (InterruptedException var5) {
/*    */         
/* 71 */         var5.printStackTrace();
/*    */       } 
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void queueIO(IThreadedFileIO p_75735_1_) {
/* 81 */     if (!this.threadedIOQueue.contains(p_75735_1_)) {
/*    */       
/* 83 */       this.writeQueuedCounter++;
/* 84 */       this.threadedIOQueue.add(p_75735_1_);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void waitForFinish() throws InterruptedException {
/* 90 */     this.isThreadWaiting = true;
/*    */     
/* 92 */     while (this.writeQueuedCounter != this.savedIOCounter)
/*    */     {
/* 94 */       Thread.sleep(10L);
/*    */     }
/*    */     
/* 97 */     this.isThreadWaiting = false;
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\world\storage\ThreadedFileIOBase.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */