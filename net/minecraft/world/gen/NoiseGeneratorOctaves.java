/*    */ package net.minecraft.world.gen;
/*    */ 
/*    */ import java.util.Random;
/*    */ import net.minecraft.util.MathHelper;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class NoiseGeneratorOctaves
/*    */   extends NoiseGenerator
/*    */ {
/*    */   private NoiseGeneratorImproved[] generatorCollection;
/*    */   private int octaves;
/*    */   private static final String __OBFID = "CL_00000535";
/*    */   
/*    */   public NoiseGeneratorOctaves(Random p_i2111_1_, int p_i2111_2_) {
/* 17 */     this.octaves = p_i2111_2_;
/* 18 */     this.generatorCollection = new NoiseGeneratorImproved[p_i2111_2_];
/*    */     
/* 20 */     for (int var3 = 0; var3 < p_i2111_2_; var3++)
/*    */     {
/* 22 */       this.generatorCollection[var3] = new NoiseGeneratorImproved(p_i2111_1_);
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public double[] generateNoiseOctaves(double[] p_76304_1_, int p_76304_2_, int p_76304_3_, int p_76304_4_, int p_76304_5_, int p_76304_6_, int p_76304_7_, double p_76304_8_, double p_76304_10_, double p_76304_12_) {
/* 32 */     if (p_76304_1_ == null) {
/*    */       
/* 34 */       p_76304_1_ = new double[p_76304_5_ * p_76304_6_ * p_76304_7_];
/*    */     }
/*    */     else {
/*    */       
/* 38 */       for (int var14 = 0; var14 < p_76304_1_.length; var14++)
/*    */       {
/* 40 */         p_76304_1_[var14] = 0.0D;
/*    */       }
/*    */     } 
/*    */     
/* 44 */     double var27 = 1.0D;
/*    */     
/* 46 */     for (int var16 = 0; var16 < this.octaves; var16++) {
/*    */       
/* 48 */       double var17 = p_76304_2_ * var27 * p_76304_8_;
/* 49 */       double var19 = p_76304_3_ * var27 * p_76304_10_;
/* 50 */       double var21 = p_76304_4_ * var27 * p_76304_12_;
/* 51 */       long var23 = MathHelper.floor_double_long(var17);
/* 52 */       long var25 = MathHelper.floor_double_long(var21);
/* 53 */       var17 -= var23;
/* 54 */       var21 -= var25;
/* 55 */       var23 %= 16777216L;
/* 56 */       var25 %= 16777216L;
/* 57 */       var17 += var23;
/* 58 */       var21 += var25;
/* 59 */       this.generatorCollection[var16].populateNoiseArray(p_76304_1_, var17, var19, var21, p_76304_5_, p_76304_6_, p_76304_7_, p_76304_8_ * var27, p_76304_10_ * var27, p_76304_12_ * var27, var27);
/* 60 */       var27 /= 2.0D;
/*    */     } 
/*    */     
/* 63 */     return p_76304_1_;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public double[] generateNoiseOctaves(double[] p_76305_1_, int p_76305_2_, int p_76305_3_, int p_76305_4_, int p_76305_5_, double p_76305_6_, double p_76305_8_, double p_76305_10_) {
/* 71 */     return generateNoiseOctaves(p_76305_1_, p_76305_2_, 10, p_76305_3_, p_76305_4_, 1, p_76305_5_, p_76305_6_, 1.0D, p_76305_8_);
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\world\gen\NoiseGeneratorOctaves.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */