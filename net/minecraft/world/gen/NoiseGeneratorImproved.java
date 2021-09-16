/*     */ package net.minecraft.world.gen;
/*     */ 
/*     */ import java.util.Random;
/*     */ 
/*     */ public class NoiseGeneratorImproved
/*     */   extends NoiseGenerator {
/*     */   private int[] permutations;
/*     */   public double xCoord;
/*     */   public double yCoord;
/*     */   public double zCoord;
/*  11 */   private static final double[] field_152381_e = new double[] { 1.0D, -1.0D, 1.0D, -1.0D, 1.0D, -1.0D, 1.0D, -1.0D, 0.0D, 0.0D, 0.0D, 0.0D, 1.0D, 0.0D, -1.0D, 0.0D };
/*  12 */   private static final double[] field_152382_f = new double[] { 1.0D, 1.0D, -1.0D, -1.0D, 0.0D, 0.0D, 0.0D, 0.0D, 1.0D, -1.0D, 1.0D, -1.0D, 1.0D, -1.0D, 1.0D, -1.0D };
/*  13 */   private static final double[] field_152383_g = new double[] { 0.0D, 0.0D, 0.0D, 0.0D, 1.0D, 1.0D, -1.0D, -1.0D, 1.0D, 1.0D, -1.0D, -1.0D, 0.0D, 1.0D, 0.0D, -1.0D };
/*  14 */   private static final double[] field_152384_h = new double[] { 1.0D, -1.0D, 1.0D, -1.0D, 1.0D, -1.0D, 1.0D, -1.0D, 0.0D, 0.0D, 0.0D, 0.0D, 1.0D, 0.0D, -1.0D, 0.0D };
/*  15 */   private static final double[] field_152385_i = new double[] { 0.0D, 0.0D, 0.0D, 0.0D, 1.0D, 1.0D, -1.0D, -1.0D, 1.0D, 1.0D, -1.0D, -1.0D, 0.0D, 1.0D, 0.0D, -1.0D };
/*     */   
/*     */   private static final String __OBFID = "CL_00000534";
/*     */   
/*     */   public NoiseGeneratorImproved() {
/*  20 */     this(new Random());
/*     */   }
/*     */ 
/*     */   
/*     */   public NoiseGeneratorImproved(Random p_i45469_1_) {
/*  25 */     this.permutations = new int[512];
/*  26 */     this.xCoord = p_i45469_1_.nextDouble() * 256.0D;
/*  27 */     this.yCoord = p_i45469_1_.nextDouble() * 256.0D;
/*  28 */     this.zCoord = p_i45469_1_.nextDouble() * 256.0D;
/*     */     
/*     */     int var2;
/*  31 */     for (var2 = 0; var2 < 256; this.permutations[var2] = var2++);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  36 */     for (var2 = 0; var2 < 256; var2++) {
/*     */       
/*  38 */       int var3 = p_i45469_1_.nextInt(256 - var2) + var2;
/*  39 */       int var4 = this.permutations[var2];
/*  40 */       this.permutations[var2] = this.permutations[var3];
/*  41 */       this.permutations[var3] = var4;
/*  42 */       this.permutations[var2 + 256] = this.permutations[var2];
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public final double lerp(double p_76311_1_, double p_76311_3_, double p_76311_5_) {
/*  48 */     return p_76311_3_ + p_76311_1_ * (p_76311_5_ - p_76311_3_);
/*     */   }
/*     */ 
/*     */   
/*     */   public final double func_76309_a(int p_76309_1_, double p_76309_2_, double p_76309_4_) {
/*  53 */     int var6 = p_76309_1_ & 0xF;
/*  54 */     return field_152384_h[var6] * p_76309_2_ + field_152385_i[var6] * p_76309_4_;
/*     */   }
/*     */ 
/*     */   
/*     */   public final double grad(int p_76310_1_, double p_76310_2_, double p_76310_4_, double p_76310_6_) {
/*  59 */     int var8 = p_76310_1_ & 0xF;
/*  60 */     return field_152381_e[var8] * p_76310_2_ + field_152382_f[var8] * p_76310_4_ + field_152383_g[var8] * p_76310_6_;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void populateNoiseArray(double[] p_76308_1_, double p_76308_2_, double p_76308_4_, double p_76308_6_, int p_76308_8_, int p_76308_9_, int p_76308_10_, double p_76308_11_, double p_76308_13_, double p_76308_15_, double p_76308_17_) {
/*  81 */     if (p_76308_9_ == 1) {
/*     */       
/*  83 */       boolean var64 = false;
/*  84 */       boolean var65 = false;
/*  85 */       boolean var21 = false;
/*  86 */       boolean var68 = false;
/*  87 */       double var70 = 0.0D;
/*  88 */       double var73 = 0.0D;
/*  89 */       int var75 = 0;
/*  90 */       double var77 = 1.0D / p_76308_17_;
/*     */       
/*  92 */       for (int var30 = 0; var30 < p_76308_8_; var30++) {
/*     */         
/*  94 */         double var31 = p_76308_2_ + var30 * p_76308_11_ + this.xCoord;
/*  95 */         int var78 = (int)var31;
/*     */         
/*  97 */         if (var31 < var78)
/*     */         {
/*  99 */           var78--;
/*     */         }
/*     */         
/* 102 */         int var34 = var78 & 0xFF;
/* 103 */         var31 -= var78;
/* 104 */         double var35 = var31 * var31 * var31 * (var31 * (var31 * 6.0D - 15.0D) + 10.0D);
/*     */         
/* 106 */         for (int var37 = 0; var37 < p_76308_10_; var37++)
/*     */         {
/* 108 */           double var38 = p_76308_6_ + var37 * p_76308_15_ + this.zCoord;
/* 109 */           int var40 = (int)var38;
/*     */           
/* 111 */           if (var38 < var40)
/*     */           {
/* 113 */             var40--;
/*     */           }
/*     */           
/* 116 */           int var41 = var40 & 0xFF;
/* 117 */           var38 -= var40;
/* 118 */           double var42 = var38 * var38 * var38 * (var38 * (var38 * 6.0D - 15.0D) + 10.0D);
/* 119 */           int var19 = this.permutations[var34] + 0;
/* 120 */           int var66 = this.permutations[var19] + var41;
/* 121 */           int var67 = this.permutations[var34 + 1] + 0;
/* 122 */           int var22 = this.permutations[var67] + var41;
/* 123 */           var70 = lerp(var35, func_76309_a(this.permutations[var66], var31, var38), grad(this.permutations[var22], var31 - 1.0D, 0.0D, var38));
/* 124 */           var73 = lerp(var35, grad(this.permutations[var66 + 1], var31, 0.0D, var38 - 1.0D), grad(this.permutations[var22 + 1], var31 - 1.0D, 0.0D, var38 - 1.0D));
/* 125 */           double var79 = lerp(var42, var70, var73);
/* 126 */           int var10001 = var75++;
/* 127 */           p_76308_1_[var10001] = p_76308_1_[var10001] + var79 * var77;
/*     */         }
/*     */       
/*     */       } 
/*     */     } else {
/*     */       
/* 133 */       int var19 = 0;
/* 134 */       double var20 = 1.0D / p_76308_17_;
/* 135 */       int var22 = -1;
/* 136 */       boolean var23 = false;
/* 137 */       boolean var24 = false;
/* 138 */       boolean var25 = false;
/* 139 */       boolean var26 = false;
/* 140 */       boolean var27 = false;
/* 141 */       boolean var28 = false;
/* 142 */       double var29 = 0.0D;
/* 143 */       double var31 = 0.0D;
/* 144 */       double var33 = 0.0D;
/* 145 */       double var35 = 0.0D;
/*     */       
/* 147 */       for (int var37 = 0; var37 < p_76308_8_; var37++) {
/*     */         
/* 149 */         double var38 = p_76308_2_ + var37 * p_76308_11_ + this.xCoord;
/* 150 */         int var40 = (int)var38;
/*     */         
/* 152 */         if (var38 < var40)
/*     */         {
/* 154 */           var40--;
/*     */         }
/*     */         
/* 157 */         int var41 = var40 & 0xFF;
/* 158 */         var38 -= var40;
/* 159 */         double var42 = var38 * var38 * var38 * (var38 * (var38 * 6.0D - 15.0D) + 10.0D);
/*     */         
/* 161 */         for (int var44 = 0; var44 < p_76308_10_; var44++) {
/*     */           
/* 163 */           double var45 = p_76308_6_ + var44 * p_76308_15_ + this.zCoord;
/* 164 */           int var47 = (int)var45;
/*     */           
/* 166 */           if (var45 < var47)
/*     */           {
/* 168 */             var47--;
/*     */           }
/*     */           
/* 171 */           int var48 = var47 & 0xFF;
/* 172 */           var45 -= var47;
/* 173 */           double var49 = var45 * var45 * var45 * (var45 * (var45 * 6.0D - 15.0D) + 10.0D);
/*     */           
/* 175 */           for (int var51 = 0; var51 < p_76308_9_; var51++) {
/*     */             
/* 177 */             double var52 = p_76308_4_ + var51 * p_76308_13_ + this.yCoord;
/* 178 */             int var54 = (int)var52;
/*     */             
/* 180 */             if (var52 < var54)
/*     */             {
/* 182 */               var54--;
/*     */             }
/*     */             
/* 185 */             int var55 = var54 & 0xFF;
/* 186 */             var52 -= var54;
/* 187 */             double var56 = var52 * var52 * var52 * (var52 * (var52 * 6.0D - 15.0D) + 10.0D);
/*     */             
/* 189 */             if (var51 == 0 || var55 != var22) {
/*     */               
/* 191 */               var22 = var55;
/* 192 */               int var69 = this.permutations[var41] + var55;
/* 193 */               int var71 = this.permutations[var69] + var48;
/* 194 */               int var72 = this.permutations[var69 + 1] + var48;
/* 195 */               int var74 = this.permutations[var41 + 1] + var55;
/* 196 */               int var75 = this.permutations[var74] + var48;
/* 197 */               int var76 = this.permutations[var74 + 1] + var48;
/* 198 */               var29 = lerp(var42, grad(this.permutations[var71], var38, var52, var45), grad(this.permutations[var75], var38 - 1.0D, var52, var45));
/* 199 */               var31 = lerp(var42, grad(this.permutations[var72], var38, var52 - 1.0D, var45), grad(this.permutations[var76], var38 - 1.0D, var52 - 1.0D, var45));
/* 200 */               var33 = lerp(var42, grad(this.permutations[var71 + 1], var38, var52, var45 - 1.0D), grad(this.permutations[var75 + 1], var38 - 1.0D, var52, var45 - 1.0D));
/* 201 */               var35 = lerp(var42, grad(this.permutations[var72 + 1], var38, var52 - 1.0D, var45 - 1.0D), grad(this.permutations[var76 + 1], var38 - 1.0D, var52 - 1.0D, var45 - 1.0D));
/*     */             } 
/*     */             
/* 204 */             double var58 = lerp(var56, var29, var31);
/* 205 */             double var60 = lerp(var56, var33, var35);
/* 206 */             double var62 = lerp(var49, var58, var60);
/* 207 */             int var10001 = var19++;
/* 208 */             p_76308_1_[var10001] = p_76308_1_[var10001] + var62 * var20;
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\world\gen\NoiseGeneratorImproved.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */