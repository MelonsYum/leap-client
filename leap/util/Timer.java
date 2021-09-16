/*    */ package leap.util;
/*    */ 
/*    */ public class Timer
/*    */ {
/*  5 */   public long lastMS = System.currentTimeMillis();
/*    */   
/*    */   public void reset() {
/*  8 */     this.lastMS = System.currentTimeMillis();
/*    */   }
/*    */   
/*    */   public boolean hasTimeElapsed(long time, boolean reset) {
/* 12 */     if (System.currentTimeMillis() - this.lastMS > time) {
/* 13 */       if (reset) {
/* 14 */         reset();
/*    */       }
/* 16 */       return true;
/*    */     } 
/*    */     
/* 19 */     return false;
/*    */   }
/*    */   
/*    */   public void setTime(long Time) {
/* 23 */     this.lastMS = Time;
/*    */   }
/*    */   
/*    */   public long getTime() {
/* 27 */     return System.currentTimeMillis() - this.lastMS;
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\lea\\util\Timer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */