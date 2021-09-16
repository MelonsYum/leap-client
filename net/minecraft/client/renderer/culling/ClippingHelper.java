/*    */ package net.minecraft.client.renderer.culling;
/*    */ 
/*    */ public class ClippingHelper
/*    */ {
/*  5 */   public float[][] frustum = new float[6][4];
/*  6 */   public float[] field_178625_b = new float[16];
/*  7 */   public float[] field_178626_c = new float[16];
/*  8 */   public float[] clippingMatrix = new float[16];
/*    */   
/*    */   private static final String __OBFID = "CL_00000977";
/*    */   
/*    */   private float dot(float[] p_178624_1_, float p_178624_2_, float p_178624_4_, float p_178624_6_) {
/* 13 */     return p_178624_1_[0] * p_178624_2_ + p_178624_1_[1] * p_178624_4_ + p_178624_1_[2] * p_178624_6_ + p_178624_1_[3];
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean isBoxInFrustum(double p_78553_1_, double p_78553_3_, double p_78553_5_, double p_78553_7_, double p_78553_9_, double p_78553_11_) {
/* 21 */     float minXf = (float)p_78553_1_;
/* 22 */     float minYf = (float)p_78553_3_;
/* 23 */     float minZf = (float)p_78553_5_;
/* 24 */     float maxXf = (float)p_78553_7_;
/* 25 */     float maxYf = (float)p_78553_9_;
/* 26 */     float maxZf = (float)p_78553_11_;
/*    */     
/* 28 */     for (int var13 = 0; var13 < 6; var13++) {
/*    */       
/* 30 */       float[] var14 = this.frustum[var13];
/*    */       
/* 32 */       if (dot(var14, minXf, minYf, minZf) <= 0.0F && dot(var14, maxXf, minYf, minZf) <= 0.0F && dot(var14, minXf, maxYf, minZf) <= 0.0F && dot(var14, maxXf, maxYf, minZf) <= 0.0F && dot(var14, minXf, minYf, maxZf) <= 0.0F && dot(var14, maxXf, minYf, maxZf) <= 0.0F && dot(var14, minXf, maxYf, maxZf) <= 0.0F && dot(var14, maxXf, maxYf, maxZf) <= 0.0F)
/*    */       {
/* 34 */         return false;
/*    */       }
/*    */     } 
/*    */     
/* 38 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\renderer\culling\ClippingHelper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */