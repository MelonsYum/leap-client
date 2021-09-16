/*     */ package net.minecraft.world.gen;
/*     */ 
/*     */ import java.util.Random;
/*     */ 
/*     */ public class NoiseGeneratorSimplex
/*     */ {
/*   7 */   private static int[][] field_151611_e = new int[][] { { 1, 1 }, { -1, 1 }, { 1, -1 }, { -1, -1 }, { 1, 1 }, { -1, 1 }, { 1, -1 }, { -1, -1 }, { 0, 1, 1 }, { 0, -1, 1 }, { 0, 1, -1 }, { 0, -1, -1 } };
/*   8 */   public static final double field_151614_a = Math.sqrt(3.0D);
/*     */   private int[] field_151608_f;
/*     */   public double field_151612_b;
/*     */   public double field_151613_c;
/*     */   public double field_151610_d;
/*  13 */   private static final double field_151609_g = 0.5D * (field_151614_a - 1.0D);
/*  14 */   private static final double field_151615_h = (3.0D - field_151614_a) / 6.0D;
/*     */   
/*     */   private static final String __OBFID = "CL_00000537";
/*     */   
/*     */   public NoiseGeneratorSimplex() {
/*  19 */     this(new Random());
/*     */   }
/*     */ 
/*     */   
/*     */   public NoiseGeneratorSimplex(Random p_i45471_1_) {
/*  24 */     this.field_151608_f = new int[512];
/*  25 */     this.field_151612_b = p_i45471_1_.nextDouble() * 256.0D;
/*  26 */     this.field_151613_c = p_i45471_1_.nextDouble() * 256.0D;
/*  27 */     this.field_151610_d = p_i45471_1_.nextDouble() * 256.0D;
/*     */     
/*     */     int var2;
/*  30 */     for (var2 = 0; var2 < 256; this.field_151608_f[var2] = var2++);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  35 */     for (var2 = 0; var2 < 256; var2++) {
/*     */       
/*  37 */       int var3 = p_i45471_1_.nextInt(256 - var2) + var2;
/*  38 */       int var4 = this.field_151608_f[var2];
/*  39 */       this.field_151608_f[var2] = this.field_151608_f[var3];
/*  40 */       this.field_151608_f[var3] = var4;
/*  41 */       this.field_151608_f[var2 + 256] = this.field_151608_f[var2];
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private static int func_151607_a(double p_151607_0_) {
/*  47 */     return (p_151607_0_ > 0.0D) ? (int)p_151607_0_ : ((int)p_151607_0_ - 1);
/*     */   }
/*     */ 
/*     */   
/*     */   private static double func_151604_a(int[] p_151604_0_, double p_151604_1_, double p_151604_3_) {
/*  52 */     return p_151604_0_[0] * p_151604_1_ + p_151604_0_[1] * p_151604_3_;
/*     */   }
/*     */   
/*     */   public double func_151605_a(double p_151605_1_, double p_151605_3_) {
/*     */     byte var29, var30;
/*  57 */     double var5, var7, var9, var11 = 0.5D * (field_151614_a - 1.0D);
/*  58 */     double var13 = (p_151605_1_ + p_151605_3_) * var11;
/*  59 */     int var15 = func_151607_a(p_151605_1_ + var13);
/*  60 */     int var16 = func_151607_a(p_151605_3_ + var13);
/*  61 */     double var17 = (3.0D - field_151614_a) / 6.0D;
/*  62 */     double var19 = (var15 + var16) * var17;
/*  63 */     double var21 = var15 - var19;
/*  64 */     double var23 = var16 - var19;
/*  65 */     double var25 = p_151605_1_ - var21;
/*  66 */     double var27 = p_151605_3_ - var23;
/*     */ 
/*     */ 
/*     */     
/*  70 */     if (var25 > var27) {
/*     */       
/*  72 */       var29 = 1;
/*  73 */       var30 = 0;
/*     */     }
/*     */     else {
/*     */       
/*  77 */       var29 = 0;
/*  78 */       var30 = 1;
/*     */     } 
/*     */     
/*  81 */     double var31 = var25 - var29 + var17;
/*  82 */     double var33 = var27 - var30 + var17;
/*  83 */     double var35 = var25 - 1.0D + 2.0D * var17;
/*  84 */     double var37 = var27 - 1.0D + 2.0D * var17;
/*  85 */     int var39 = var15 & 0xFF;
/*  86 */     int var40 = var16 & 0xFF;
/*  87 */     int var41 = this.field_151608_f[var39 + this.field_151608_f[var40]] % 12;
/*  88 */     int var42 = this.field_151608_f[var39 + var29 + this.field_151608_f[var40 + var30]] % 12;
/*  89 */     int var43 = this.field_151608_f[var39 + 1 + this.field_151608_f[var40 + 1]] % 12;
/*  90 */     double var44 = 0.5D - var25 * var25 - var27 * var27;
/*     */ 
/*     */     
/*  93 */     if (var44 < 0.0D) {
/*     */       
/*  95 */       var5 = 0.0D;
/*     */     }
/*     */     else {
/*     */       
/*  99 */       var44 *= var44;
/* 100 */       var5 = var44 * var44 * func_151604_a(field_151611_e[var41], var25, var27);
/*     */     } 
/*     */     
/* 103 */     double var46 = 0.5D - var31 * var31 - var33 * var33;
/*     */ 
/*     */     
/* 106 */     if (var46 < 0.0D) {
/*     */       
/* 108 */       var7 = 0.0D;
/*     */     }
/*     */     else {
/*     */       
/* 112 */       var46 *= var46;
/* 113 */       var7 = var46 * var46 * func_151604_a(field_151611_e[var42], var31, var33);
/*     */     } 
/*     */     
/* 116 */     double var48 = 0.5D - var35 * var35 - var37 * var37;
/*     */ 
/*     */     
/* 119 */     if (var48 < 0.0D) {
/*     */       
/* 121 */       var9 = 0.0D;
/*     */     }
/*     */     else {
/*     */       
/* 125 */       var48 *= var48;
/* 126 */       var9 = var48 * var48 * func_151604_a(field_151611_e[var43], var35, var37);
/*     */     } 
/*     */     
/* 129 */     return 70.0D * (var5 + var7 + var9);
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_151606_a(double[] p_151606_1_, double p_151606_2_, double p_151606_4_, int p_151606_6_, int p_151606_7_, double p_151606_8_, double p_151606_10_, double p_151606_12_) {
/* 134 */     int var14 = 0;
/*     */     
/* 136 */     for (int var15 = 0; var15 < p_151606_7_; var15++) {
/*     */       
/* 138 */       double var16 = (p_151606_4_ + var15) * p_151606_10_ + this.field_151613_c;
/*     */       
/* 140 */       for (int var18 = 0; var18 < p_151606_6_; var18++) {
/*     */         byte var41, var42;
/* 142 */         double var21, var23, var25, var19 = (p_151606_2_ + var18) * p_151606_8_ + this.field_151612_b;
/* 143 */         double var27 = (var19 + var16) * field_151609_g;
/* 144 */         int var29 = func_151607_a(var19 + var27);
/* 145 */         int var30 = func_151607_a(var16 + var27);
/* 146 */         double var31 = (var29 + var30) * field_151615_h;
/* 147 */         double var33 = var29 - var31;
/* 148 */         double var35 = var30 - var31;
/* 149 */         double var37 = var19 - var33;
/* 150 */         double var39 = var16 - var35;
/*     */ 
/*     */ 
/*     */         
/* 154 */         if (var37 > var39) {
/*     */           
/* 156 */           var41 = 1;
/* 157 */           var42 = 0;
/*     */         }
/*     */         else {
/*     */           
/* 161 */           var41 = 0;
/* 162 */           var42 = 1;
/*     */         } 
/*     */         
/* 165 */         double var43 = var37 - var41 + field_151615_h;
/* 166 */         double var45 = var39 - var42 + field_151615_h;
/* 167 */         double var47 = var37 - 1.0D + 2.0D * field_151615_h;
/* 168 */         double var49 = var39 - 1.0D + 2.0D * field_151615_h;
/* 169 */         int var51 = var29 & 0xFF;
/* 170 */         int var52 = var30 & 0xFF;
/* 171 */         int var53 = this.field_151608_f[var51 + this.field_151608_f[var52]] % 12;
/* 172 */         int var54 = this.field_151608_f[var51 + var41 + this.field_151608_f[var52 + var42]] % 12;
/* 173 */         int var55 = this.field_151608_f[var51 + 1 + this.field_151608_f[var52 + 1]] % 12;
/* 174 */         double var56 = 0.5D - var37 * var37 - var39 * var39;
/*     */ 
/*     */         
/* 177 */         if (var56 < 0.0D) {
/*     */           
/* 179 */           var21 = 0.0D;
/*     */         }
/*     */         else {
/*     */           
/* 183 */           var56 *= var56;
/* 184 */           var21 = var56 * var56 * func_151604_a(field_151611_e[var53], var37, var39);
/*     */         } 
/*     */         
/* 187 */         double var58 = 0.5D - var43 * var43 - var45 * var45;
/*     */ 
/*     */         
/* 190 */         if (var58 < 0.0D) {
/*     */           
/* 192 */           var23 = 0.0D;
/*     */         }
/*     */         else {
/*     */           
/* 196 */           var58 *= var58;
/* 197 */           var23 = var58 * var58 * func_151604_a(field_151611_e[var54], var43, var45);
/*     */         } 
/*     */         
/* 200 */         double var60 = 0.5D - var47 * var47 - var49 * var49;
/*     */ 
/*     */         
/* 203 */         if (var60 < 0.0D) {
/*     */           
/* 205 */           var25 = 0.0D;
/*     */         }
/*     */         else {
/*     */           
/* 209 */           var60 *= var60;
/* 210 */           var25 = var60 * var60 * func_151604_a(field_151611_e[var55], var47, var49);
/*     */         } 
/*     */         
/* 213 */         int var10001 = var14++;
/* 214 */         p_151606_1_[var10001] = p_151606_1_[var10001] + 70.0D * (var21 + var23 + var25) * p_151606_12_;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\world\gen\NoiseGeneratorSimplex.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */