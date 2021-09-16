/*    */ package net.minecraft.world.gen;
/*    */ 
/*    */ import java.util.Random;
/*    */ 
/*    */ public class NoiseGeneratorPerlin
/*    */   extends NoiseGenerator
/*    */ {
/*    */   private NoiseGeneratorSimplex[] field_151603_a;
/*    */   private int field_151602_b;
/*    */   private static final String __OBFID = "CL_00000536";
/*    */   
/*    */   public NoiseGeneratorPerlin(Random p_i45470_1_, int p_i45470_2_) {
/* 13 */     this.field_151602_b = p_i45470_2_;
/* 14 */     this.field_151603_a = new NoiseGeneratorSimplex[p_i45470_2_];
/*    */     
/* 16 */     for (int var3 = 0; var3 < p_i45470_2_; var3++)
/*    */     {
/* 18 */       this.field_151603_a[var3] = new NoiseGeneratorSimplex(p_i45470_1_);
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public double func_151601_a(double p_151601_1_, double p_151601_3_) {
/* 24 */     double var5 = 0.0D;
/* 25 */     double var7 = 1.0D;
/*    */     
/* 27 */     for (int var9 = 0; var9 < this.field_151602_b; var9++) {
/*    */       
/* 29 */       var5 += this.field_151603_a[var9].func_151605_a(p_151601_1_ * var7, p_151601_3_ * var7) / var7;
/* 30 */       var7 /= 2.0D;
/*    */     } 
/*    */     
/* 33 */     return var5;
/*    */   }
/*    */ 
/*    */   
/*    */   public double[] func_151599_a(double[] p_151599_1_, double p_151599_2_, double p_151599_4_, int p_151599_6_, int p_151599_7_, double p_151599_8_, double p_151599_10_, double p_151599_12_) {
/* 38 */     return func_151600_a(p_151599_1_, p_151599_2_, p_151599_4_, p_151599_6_, p_151599_7_, p_151599_8_, p_151599_10_, p_151599_12_, 0.5D);
/*    */   }
/*    */ 
/*    */   
/*    */   public double[] func_151600_a(double[] p_151600_1_, double p_151600_2_, double p_151600_4_, int p_151600_6_, int p_151600_7_, double p_151600_8_, double p_151600_10_, double p_151600_12_, double p_151600_14_) {
/* 43 */     if (p_151600_1_ != null && p_151600_1_.length >= p_151600_6_ * p_151600_7_) {
/*    */       
/* 45 */       for (int var16 = 0; var16 < p_151600_1_.length; var16++)
/*    */       {
/* 47 */         p_151600_1_[var16] = 0.0D;
/*    */       }
/*    */     }
/*    */     else {
/*    */       
/* 52 */       p_151600_1_ = new double[p_151600_6_ * p_151600_7_];
/*    */     } 
/*    */     
/* 55 */     double var21 = 1.0D;
/* 56 */     double var18 = 1.0D;
/*    */     
/* 58 */     for (int var20 = 0; var20 < this.field_151602_b; var20++) {
/*    */       
/* 60 */       this.field_151603_a[var20].func_151606_a(p_151600_1_, p_151600_2_, p_151600_4_, p_151600_6_, p_151600_7_, p_151600_8_ * var18 * var21, p_151600_10_ * var18 * var21, 0.55D / var21);
/* 61 */       var18 *= p_151600_12_;
/* 62 */       var21 *= p_151600_14_;
/*    */     } 
/*    */     
/* 65 */     return p_151600_1_;
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\world\gen\NoiseGeneratorPerlin.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */