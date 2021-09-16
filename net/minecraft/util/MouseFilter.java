/*    */ package net.minecraft.util;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class MouseFilter
/*    */ {
/*    */   private float field_76336_a;
/*    */   private float field_76334_b;
/*    */   private float field_76335_c;
/*    */   private static final String __OBFID = "CL_00001500";
/*    */   
/*    */   public float smooth(float p_76333_1_, float p_76333_2_) {
/* 15 */     this.field_76336_a += p_76333_1_;
/* 16 */     p_76333_1_ = (this.field_76336_a - this.field_76334_b) * p_76333_2_;
/* 17 */     this.field_76335_c += (p_76333_1_ - this.field_76335_c) * 0.5F;
/*    */     
/* 19 */     if ((p_76333_1_ > 0.0F && p_76333_1_ > this.field_76335_c) || (p_76333_1_ < 0.0F && p_76333_1_ < this.field_76335_c))
/*    */     {
/* 21 */       p_76333_1_ = this.field_76335_c;
/*    */     }
/*    */     
/* 24 */     this.field_76334_b += p_76333_1_;
/* 25 */     return p_76333_1_;
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_180179_a() {
/* 30 */     this.field_76336_a = 0.0F;
/* 31 */     this.field_76334_b = 0.0F;
/* 32 */     this.field_76335_c = 0.0F;
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraf\\util\MouseFilter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */