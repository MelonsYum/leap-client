/*    */ package net.minecraft.util;
/*    */ 
/*    */ import java.lang.reflect.Array;
/*    */ import java.util.concurrent.locks.ReadWriteLock;
/*    */ import java.util.concurrent.locks.ReentrantReadWriteLock;
/*    */ 
/*    */ public class ThreadSafeBoundList
/*    */ {
/*    */   private final Object[] field_152759_a;
/*    */   private final Class field_152760_b;
/* 11 */   private final ReadWriteLock field_152761_c = new ReentrantReadWriteLock();
/*    */   
/*    */   private int field_152762_d;
/*    */   private int field_152763_e;
/*    */   private static final String __OBFID = "CL_00001868";
/*    */   
/*    */   public ThreadSafeBoundList(Class<?> p_i1126_1_, int p_i1126_2_) {
/* 18 */     this.field_152760_b = p_i1126_1_;
/* 19 */     this.field_152759_a = (Object[])Array.newInstance(p_i1126_1_, p_i1126_2_);
/*    */   }
/*    */ 
/*    */   
/*    */   public Object func_152757_a(Object p_152757_1_) {
/* 24 */     this.field_152761_c.writeLock().lock();
/* 25 */     this.field_152759_a[this.field_152763_e] = p_152757_1_;
/* 26 */     this.field_152763_e = (this.field_152763_e + 1) % func_152758_b();
/*    */     
/* 28 */     if (this.field_152762_d < func_152758_b())
/*    */     {
/* 30 */       this.field_152762_d++;
/*    */     }
/*    */     
/* 33 */     this.field_152761_c.writeLock().unlock();
/* 34 */     return p_152757_1_;
/*    */   }
/*    */ 
/*    */   
/*    */   public int func_152758_b() {
/* 39 */     this.field_152761_c.readLock().lock();
/* 40 */     int var1 = this.field_152759_a.length;
/* 41 */     this.field_152761_c.readLock().unlock();
/* 42 */     return var1;
/*    */   }
/*    */ 
/*    */   
/*    */   public Object[] func_152756_c() {
/* 47 */     Object[] var1 = (Object[])Array.newInstance(this.field_152760_b, this.field_152762_d);
/* 48 */     this.field_152761_c.readLock().lock();
/*    */     
/* 50 */     for (int var2 = 0; var2 < this.field_152762_d; var2++) {
/*    */       
/* 52 */       int var3 = (this.field_152763_e - this.field_152762_d + var2) % func_152758_b();
/*    */       
/* 54 */       if (var3 < 0)
/*    */       {
/* 56 */         var3 += func_152758_b();
/*    */       }
/*    */       
/* 59 */       var1[var2] = this.field_152759_a[var3];
/*    */     } 
/*    */     
/* 62 */     this.field_152761_c.readLock().unlock();
/* 63 */     return var1;
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraf\\util\ThreadSafeBoundList.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */