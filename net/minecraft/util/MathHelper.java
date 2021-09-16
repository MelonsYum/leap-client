/*     */ package net.minecraft.util;
/*     */ 
/*     */ import java.util.Random;
/*     */ import java.util.UUID;
/*     */ 
/*     */ public class MathHelper
/*     */ {
/*   8 */   public static final float field_180189_a = sqrt_float(2.0F);
/*     */   private static final int SIN_BITS = 12;
/*     */   private static final int SIN_MASK = 4095;
/*     */   private static final int SIN_COUNT = 4096;
/*     */   public static final float PI = 3.1415927F;
/*     */   public static final float PI2 = 6.2831855F;
/*     */   public static final float PId2 = 1.5707964F;
/*     */   private static final float radFull = 6.2831855F;
/*     */   private static final float degFull = 360.0F;
/*     */   private static final float radToIndex = 651.8986F;
/*     */   private static final float degToIndex = 11.377778F;
/*     */   public static final float deg2Rad = 0.017453292F;
/*  20 */   private static final float[] SIN_TABLE_FAST = new float[4096];
/*     */ 
/*     */   
/*     */   public static boolean fastMath = false;
/*     */ 
/*     */   
/*  26 */   private static final float[] SIN_TABLE = new float[65536];
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
/*     */   public static float sin(float p_76126_0_) {
/*  43 */     return fastMath ? SIN_TABLE_FAST[(int)(p_76126_0_ * 651.8986F) & 0xFFF] : SIN_TABLE[(int)(p_76126_0_ * 10430.378F) & 0xFFFF];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static float cos(float p_76134_0_) {
/*  51 */     return fastMath ? SIN_TABLE_FAST[(int)((p_76134_0_ + 1.5707964F) * 651.8986F) & 0xFFF] : SIN_TABLE[(int)(p_76134_0_ * 10430.378F + 16384.0F) & 0xFFFF];
/*     */   }
/*     */ 
/*     */   
/*     */   public static float sqrt_float(float p_76129_0_) {
/*  56 */     return (float)Math.sqrt(p_76129_0_);
/*     */   }
/*     */ 
/*     */   
/*     */   public static float sqrt_double(double p_76133_0_) {
/*  61 */     return (float)Math.sqrt(p_76133_0_);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int floor_float(float p_76141_0_) {
/*  69 */     int var1 = (int)p_76141_0_;
/*  70 */     return (p_76141_0_ < var1) ? (var1 - 1) : var1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int truncateDoubleToInt(double p_76140_0_) {
/*  78 */     return (int)(p_76140_0_ + 1024.0D) - 1024;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int floor_double(double p_76128_0_) {
/*  86 */     int var2 = (int)p_76128_0_;
/*  87 */     return (p_76128_0_ < var2) ? (var2 - 1) : var2;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static long floor_double_long(double p_76124_0_) {
/*  95 */     long var2 = (long)p_76124_0_;
/*  96 */     return (p_76124_0_ < var2) ? (var2 - 1L) : var2;
/*     */   }
/*     */ 
/*     */   
/*     */   public static int func_154353_e(double p_154353_0_) {
/* 101 */     return (int)((p_154353_0_ >= 0.0D) ? p_154353_0_ : (-p_154353_0_ + 1.0D));
/*     */   }
/*     */ 
/*     */   
/*     */   public static float abs(float p_76135_0_) {
/* 106 */     return (p_76135_0_ >= 0.0F) ? p_76135_0_ : -p_76135_0_;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int abs_int(int p_76130_0_) {
/* 114 */     return (p_76130_0_ >= 0) ? p_76130_0_ : -p_76130_0_;
/*     */   }
/*     */ 
/*     */   
/*     */   public static int ceiling_float_int(float p_76123_0_) {
/* 119 */     int var1 = (int)p_76123_0_;
/* 120 */     return (p_76123_0_ > var1) ? (var1 + 1) : var1;
/*     */   }
/*     */ 
/*     */   
/*     */   public static int ceiling_double_int(double p_76143_0_) {
/* 125 */     int var2 = (int)p_76143_0_;
/* 126 */     return (p_76143_0_ > var2) ? (var2 + 1) : var2;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int clamp_int(int p_76125_0_, int p_76125_1_, int p_76125_2_) {
/* 135 */     return (p_76125_0_ < p_76125_1_) ? p_76125_1_ : ((p_76125_0_ > p_76125_2_) ? p_76125_2_ : p_76125_0_);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static float clamp_float(float p_76131_0_, float p_76131_1_, float p_76131_2_) {
/* 144 */     return (p_76131_0_ < p_76131_1_) ? p_76131_1_ : ((p_76131_0_ > p_76131_2_) ? p_76131_2_ : p_76131_0_);
/*     */   }
/*     */ 
/*     */   
/*     */   public static double clamp_double(double p_151237_0_, double p_151237_2_, double p_151237_4_) {
/* 149 */     return (p_151237_0_ < p_151237_2_) ? p_151237_2_ : ((p_151237_0_ > p_151237_4_) ? p_151237_4_ : p_151237_0_);
/*     */   }
/*     */ 
/*     */   
/*     */   public static double denormalizeClamp(double p_151238_0_, double p_151238_2_, double p_151238_4_) {
/* 154 */     return (p_151238_4_ < 0.0D) ? p_151238_0_ : ((p_151238_4_ > 1.0D) ? p_151238_2_ : (p_151238_0_ + (p_151238_2_ - p_151238_0_) * p_151238_4_));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static double abs_max(double p_76132_0_, double p_76132_2_) {
/* 162 */     if (p_76132_0_ < 0.0D)
/*     */     {
/* 164 */       p_76132_0_ = -p_76132_0_;
/*     */     }
/*     */     
/* 167 */     if (p_76132_2_ < 0.0D)
/*     */     {
/* 169 */       p_76132_2_ = -p_76132_2_;
/*     */     }
/*     */     
/* 172 */     return (p_76132_0_ > p_76132_2_) ? p_76132_0_ : p_76132_2_;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int bucketInt(int p_76137_0_, int p_76137_1_) {
/* 180 */     return (p_76137_0_ < 0) ? (-((-p_76137_0_ - 1) / p_76137_1_) - 1) : (p_76137_0_ / p_76137_1_);
/*     */   }
/*     */ 
/*     */   
/*     */   public static int getRandomIntegerInRange(Random p_76136_0_, int p_76136_1_, int p_76136_2_) {
/* 185 */     return (p_76136_1_ >= p_76136_2_) ? p_76136_1_ : (p_76136_0_.nextInt(p_76136_2_ - p_76136_1_ + 1) + p_76136_1_);
/*     */   }
/*     */ 
/*     */   
/*     */   public static float randomFloatClamp(Random p_151240_0_, float p_151240_1_, float p_151240_2_) {
/* 190 */     return (p_151240_1_ >= p_151240_2_) ? p_151240_1_ : (p_151240_0_.nextFloat() * (p_151240_2_ - p_151240_1_) + p_151240_1_);
/*     */   }
/*     */ 
/*     */   
/*     */   public static double getRandomDoubleInRange(Random p_82716_0_, double p_82716_1_, double p_82716_3_) {
/* 195 */     return (p_82716_1_ >= p_82716_3_) ? p_82716_1_ : (p_82716_0_.nextDouble() * (p_82716_3_ - p_82716_1_) + p_82716_1_);
/*     */   }
/*     */ 
/*     */   
/*     */   public static double average(long[] p_76127_0_) {
/* 200 */     long var1 = 0L;
/* 201 */     long[] var3 = p_76127_0_;
/* 202 */     int var4 = p_76127_0_.length;
/*     */     
/* 204 */     for (int var5 = 0; var5 < var4; var5++) {
/*     */       
/* 206 */       long var6 = var3[var5];
/* 207 */       var1 += var6;
/*     */     } 
/*     */     
/* 210 */     return var1 / p_76127_0_.length;
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean func_180185_a(float p_180185_0_, float p_180185_1_) {
/* 215 */     return (abs(p_180185_1_ - p_180185_0_) < 1.0E-5F);
/*     */   }
/*     */ 
/*     */   
/*     */   public static int func_180184_b(int p_180184_0_, int p_180184_1_) {
/* 220 */     return (p_180184_0_ % p_180184_1_ + p_180184_1_) % p_180184_1_;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static float wrapAngleTo180_float(float p_76142_0_) {
/* 228 */     p_76142_0_ %= 360.0F;
/*     */     
/* 230 */     if (p_76142_0_ >= 180.0F)
/*     */     {
/* 232 */       p_76142_0_ -= 360.0F;
/*     */     }
/*     */     
/* 235 */     if (p_76142_0_ < -180.0F)
/*     */     {
/* 237 */       p_76142_0_ += 360.0F;
/*     */     }
/*     */     
/* 240 */     return p_76142_0_;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static double wrapAngleTo180_double(double p_76138_0_) {
/* 248 */     p_76138_0_ %= 360.0D;
/*     */     
/* 250 */     if (p_76138_0_ >= 180.0D)
/*     */     {
/* 252 */       p_76138_0_ -= 360.0D;
/*     */     }
/*     */     
/* 255 */     if (p_76138_0_ < -180.0D)
/*     */     {
/* 257 */       p_76138_0_ += 360.0D;
/*     */     }
/*     */     
/* 260 */     return p_76138_0_;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int parseIntWithDefault(String p_82715_0_, int p_82715_1_) {
/*     */     try {
/* 270 */       return Integer.parseInt(p_82715_0_);
/*     */     }
/* 272 */     catch (Throwable var3) {
/*     */       
/* 274 */       return p_82715_1_;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int parseIntWithDefaultAndMax(String p_82714_0_, int p_82714_1_, int p_82714_2_) {
/* 283 */     return Math.max(p_82714_2_, parseIntWithDefault(p_82714_0_, p_82714_1_));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static double parseDoubleWithDefault(String p_82712_0_, double p_82712_1_) {
/*     */     try {
/* 293 */       return Double.parseDouble(p_82712_0_);
/*     */     }
/* 295 */     catch (Throwable var4) {
/*     */       
/* 297 */       return p_82712_1_;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static double parseDoubleWithDefaultAndMax(String p_82713_0_, double p_82713_1_, double p_82713_3_) {
/* 303 */     return Math.max(p_82713_3_, parseDoubleWithDefault(p_82713_0_, p_82713_1_));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int roundUpToPowerOfTwo(int p_151236_0_) {
/* 311 */     int var1 = p_151236_0_ - 1;
/* 312 */     var1 |= var1 >> 1;
/* 313 */     var1 |= var1 >> 2;
/* 314 */     var1 |= var1 >> 4;
/* 315 */     var1 |= var1 >> 8;
/* 316 */     var1 |= var1 >> 16;
/* 317 */     return var1 + 1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static boolean isPowerOfTwo(int p_151235_0_) {
/* 325 */     return (p_151235_0_ != 0 && (p_151235_0_ & p_151235_0_ - 1) == 0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static int calculateLogBaseTwoDeBruijn(int p_151241_0_) {
/* 335 */     p_151241_0_ = isPowerOfTwo(p_151241_0_) ? p_151241_0_ : roundUpToPowerOfTwo(p_151241_0_);
/* 336 */     return multiplyDeBruijnBitPosition[(int)(p_151241_0_ * 125613361L >> 27L) & 0x1F];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int calculateLogBaseTwo(int p_151239_0_) {
/* 345 */     return calculateLogBaseTwoDeBruijn(p_151239_0_) - (isPowerOfTwo(p_151239_0_) ? 0 : 1);
/*     */   }
/*     */ 
/*     */   
/*     */   public static int func_154354_b(int p_154354_0_, int p_154354_1_) {
/* 350 */     if (p_154354_1_ == 0)
/*     */     {
/* 352 */       return 0;
/*     */     }
/* 354 */     if (p_154354_0_ == 0)
/*     */     {
/* 356 */       return p_154354_1_;
/*     */     }
/*     */ 
/*     */     
/* 360 */     if (p_154354_0_ < 0)
/*     */     {
/* 362 */       p_154354_1_ *= -1;
/*     */     }
/*     */     
/* 365 */     int var2 = p_154354_0_ % p_154354_1_;
/* 366 */     return (var2 == 0) ? p_154354_0_ : (p_154354_0_ + p_154354_1_ - var2);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static int func_180183_b(float p_180183_0_, float p_180183_1_, float p_180183_2_) {
/* 372 */     return func_180181_b(floor_float(p_180183_0_ * 255.0F), floor_float(p_180183_1_ * 255.0F), floor_float(p_180183_2_ * 255.0F));
/*     */   }
/*     */ 
/*     */   
/*     */   public static int func_180181_b(int p_180181_0_, int p_180181_1_, int p_180181_2_) {
/* 377 */     int var3 = (p_180181_0_ << 8) + p_180181_1_;
/* 378 */     var3 = (var3 << 8) + p_180181_2_;
/* 379 */     return var3;
/*     */   }
/*     */ 
/*     */   
/*     */   public static int func_180188_d(int p_180188_0_, int p_180188_1_) {
/* 384 */     int var2 = (p_180188_0_ & 0xFF0000) >> 16;
/* 385 */     int var3 = (p_180188_1_ & 0xFF0000) >> 16;
/* 386 */     int var4 = (p_180188_0_ & 0xFF00) >> 8;
/* 387 */     int var5 = (p_180188_1_ & 0xFF00) >> 8;
/* 388 */     int var6 = (p_180188_0_ & 0xFF) >> 0;
/* 389 */     int var7 = (p_180188_1_ & 0xFF) >> 0;
/* 390 */     int var8 = (int)(var2 * var3 / 255.0F);
/* 391 */     int var9 = (int)(var4 * var5 / 255.0F);
/* 392 */     int var10 = (int)(var6 * var7 / 255.0F);
/* 393 */     return p_180188_0_ & 0xFF000000 | var8 << 16 | var9 << 8 | var10;
/*     */   }
/*     */ 
/*     */   
/*     */   public static long func_180186_a(Vec3i pos) {
/* 398 */     return func_180187_c(pos.getX(), pos.getY(), pos.getZ());
/*     */   }
/*     */ 
/*     */   
/*     */   public static long func_180187_c(int x, int y, int z) {
/* 403 */     long var3 = (x * 3129871) ^ z * 116129781L ^ y;
/* 404 */     var3 = var3 * var3 * 42317861L + var3 * 11L;
/* 405 */     return var3;
/*     */   }
/*     */ 
/*     */   
/*     */   public static UUID func_180182_a(Random p_180182_0_) {
/* 410 */     long var1 = p_180182_0_.nextLong() & 0xFFFFFFFFFFFF0FFFL | 0x4000L;
/* 411 */     long var3 = p_180182_0_.nextLong() & 0x3FFFFFFFFFFFFFFFL | Long.MIN_VALUE;
/* 412 */     return new UUID(var1, var3);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   static {
/*     */     int i;
/* 419 */     for (i = 0; i < 65536; i++)
/*     */     {
/* 421 */       SIN_TABLE[i] = (float)Math.sin(i * Math.PI * 2.0D / 65536.0D); } 
/*     */   }
/*     */   
/* 424 */   private static final int[] multiplyDeBruijnBitPosition = new int[] { 0, 1, 28, 2, 29, 14, 24, 3, 30, 22, 20, 15, 25, 17, 4, 8, 31, 27, 13, 23, 21, 19, 16, 7, 26, 12, 18, 6, 11, 5, 10, 9 };
/*     */   static {
/* 426 */     for (i = 0; i < 4096; i++)
/*     */     {
/* 428 */       SIN_TABLE_FAST[i] = (float)Math.sin(((i + 0.5F) / 4096.0F * 6.2831855F));
/*     */     }
/*     */     
/* 431 */     for (i = 0; i < 360; i += 90)
/*     */     {
/* 433 */       SIN_TABLE_FAST[(int)(i * 11.377778F) & 0xFFF] = (float)Math.sin((i * 0.017453292F)); } 
/*     */   }
/*     */   
/*     */   private static final String __OBFID = "CL_00001496";
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraf\\util\MathHelper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */