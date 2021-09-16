/*     */ package net.minecraft.realms;
/*     */ 
/*     */ import java.util.Random;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import org.apache.commons.lang3.StringUtils;
/*     */ 
/*     */ 
/*     */ public class RealmsMth
/*     */ {
/*     */   private static final String __OBFID = "CL_00001900";
/*     */   
/*     */   public static float sin(float p_sin_0_) {
/*  13 */     return MathHelper.sin(p_sin_0_);
/*     */   }
/*     */ 
/*     */   
/*     */   public static double nextDouble(Random p_nextDouble_0_, double p_nextDouble_1_, double p_nextDouble_3_) {
/*  18 */     return MathHelper.getRandomDoubleInRange(p_nextDouble_0_, p_nextDouble_1_, p_nextDouble_3_);
/*     */   }
/*     */ 
/*     */   
/*     */   public static int ceil(float p_ceil_0_) {
/*  23 */     return MathHelper.ceiling_float_int(p_ceil_0_);
/*     */   }
/*     */ 
/*     */   
/*     */   public static int floor(double p_floor_0_) {
/*  28 */     return MathHelper.floor_double(p_floor_0_);
/*     */   }
/*     */ 
/*     */   
/*     */   public static int intFloorDiv(int p_intFloorDiv_0_, int p_intFloorDiv_1_) {
/*  33 */     return MathHelper.bucketInt(p_intFloorDiv_0_, p_intFloorDiv_1_);
/*     */   }
/*     */ 
/*     */   
/*     */   public static float abs(float p_abs_0_) {
/*  38 */     return MathHelper.abs(p_abs_0_);
/*     */   }
/*     */ 
/*     */   
/*     */   public static int clamp(int p_clamp_0_, int p_clamp_1_, int p_clamp_2_) {
/*  43 */     return MathHelper.clamp_int(p_clamp_0_, p_clamp_1_, p_clamp_2_);
/*     */   }
/*     */ 
/*     */   
/*     */   public static double clampedLerp(double p_clampedLerp_0_, double p_clampedLerp_2_, double p_clampedLerp_4_) {
/*  48 */     return MathHelper.denormalizeClamp(p_clampedLerp_0_, p_clampedLerp_2_, p_clampedLerp_4_);
/*     */   }
/*     */ 
/*     */   
/*     */   public static int ceil(double p_ceil_0_) {
/*  53 */     return MathHelper.ceiling_double_int(p_ceil_0_);
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean isEmpty(String p_isEmpty_0_) {
/*  58 */     return StringUtils.isEmpty(p_isEmpty_0_);
/*     */   }
/*     */ 
/*     */   
/*     */   public static long lfloor(double p_lfloor_0_) {
/*  63 */     return MathHelper.floor_double_long(p_lfloor_0_);
/*     */   }
/*     */ 
/*     */   
/*     */   public static float sqrt(double p_sqrt_0_) {
/*  68 */     return MathHelper.sqrt_double(p_sqrt_0_);
/*     */   }
/*     */ 
/*     */   
/*     */   public static double clamp(double p_clamp_0_, double p_clamp_2_, double p_clamp_4_) {
/*  73 */     return MathHelper.clamp_double(p_clamp_0_, p_clamp_2_, p_clamp_4_);
/*     */   }
/*     */ 
/*     */   
/*     */   public static int getInt(String p_getInt_0_, int p_getInt_1_) {
/*  78 */     return MathHelper.parseIntWithDefault(p_getInt_0_, p_getInt_1_);
/*     */   }
/*     */ 
/*     */   
/*     */   public static double getDouble(String p_getDouble_0_, double p_getDouble_1_) {
/*  83 */     return MathHelper.parseDoubleWithDefault(p_getDouble_0_, p_getDouble_1_);
/*     */   }
/*     */ 
/*     */   
/*     */   public static int log2(int p_log2_0_) {
/*  88 */     return MathHelper.calculateLogBaseTwo(p_log2_0_);
/*     */   }
/*     */ 
/*     */   
/*     */   public static int absFloor(double p_absFloor_0_) {
/*  93 */     return MathHelper.func_154353_e(p_absFloor_0_);
/*     */   }
/*     */ 
/*     */   
/*     */   public static int smallestEncompassingPowerOfTwo(int p_smallestEncompassingPowerOfTwo_0_) {
/*  98 */     return MathHelper.roundUpToPowerOfTwo(p_smallestEncompassingPowerOfTwo_0_);
/*     */   }
/*     */ 
/*     */   
/*     */   public static float sqrt(float p_sqrt_0_) {
/* 103 */     return MathHelper.sqrt_float(p_sqrt_0_);
/*     */   }
/*     */ 
/*     */   
/*     */   public static float cos(float p_cos_0_) {
/* 108 */     return MathHelper.cos(p_cos_0_);
/*     */   }
/*     */ 
/*     */   
/*     */   public static int getInt(String p_getInt_0_, int p_getInt_1_, int p_getInt_2_) {
/* 113 */     return MathHelper.parseIntWithDefaultAndMax(p_getInt_0_, p_getInt_1_, p_getInt_2_);
/*     */   }
/*     */ 
/*     */   
/*     */   public static int fastFloor(double p_fastFloor_0_) {
/* 118 */     return MathHelper.truncateDoubleToInt(p_fastFloor_0_);
/*     */   }
/*     */ 
/*     */   
/*     */   public static double absMax(double p_absMax_0_, double p_absMax_2_) {
/* 123 */     return MathHelper.abs_max(p_absMax_0_, p_absMax_2_);
/*     */   }
/*     */ 
/*     */   
/*     */   public static float nextFloat(Random p_nextFloat_0_, float p_nextFloat_1_, float p_nextFloat_2_) {
/* 128 */     return MathHelper.randomFloatClamp(p_nextFloat_0_, p_nextFloat_1_, p_nextFloat_2_);
/*     */   }
/*     */ 
/*     */   
/*     */   public static double wrapDegrees(double p_wrapDegrees_0_) {
/* 133 */     return MathHelper.wrapAngleTo180_double(p_wrapDegrees_0_);
/*     */   }
/*     */ 
/*     */   
/*     */   public static float wrapDegrees(float p_wrapDegrees_0_) {
/* 138 */     return MathHelper.wrapAngleTo180_float(p_wrapDegrees_0_);
/*     */   }
/*     */ 
/*     */   
/*     */   public static float clamp(float p_clamp_0_, float p_clamp_1_, float p_clamp_2_) {
/* 143 */     return MathHelper.clamp_float(p_clamp_0_, p_clamp_1_, p_clamp_2_);
/*     */   }
/*     */ 
/*     */   
/*     */   public static double getDouble(String p_getDouble_0_, double p_getDouble_1_, double p_getDouble_3_) {
/* 148 */     return MathHelper.parseDoubleWithDefaultAndMax(p_getDouble_0_, p_getDouble_1_, p_getDouble_3_);
/*     */   }
/*     */ 
/*     */   
/*     */   public static int roundUp(int p_roundUp_0_, int p_roundUp_1_) {
/* 153 */     return MathHelper.func_154354_b(p_roundUp_0_, p_roundUp_1_);
/*     */   }
/*     */ 
/*     */   
/*     */   public static double average(long[] p_average_0_) {
/* 158 */     return MathHelper.average(p_average_0_);
/*     */   }
/*     */ 
/*     */   
/*     */   public static int floor(float p_floor_0_) {
/* 163 */     return MathHelper.floor_float(p_floor_0_);
/*     */   }
/*     */ 
/*     */   
/*     */   public static int abs(int p_abs_0_) {
/* 168 */     return MathHelper.abs_int(p_abs_0_);
/*     */   }
/*     */ 
/*     */   
/*     */   public static int nextInt(Random p_nextInt_0_, int p_nextInt_1_, int p_nextInt_2_) {
/* 173 */     return MathHelper.getRandomIntegerInRange(p_nextInt_0_, p_nextInt_1_, p_nextInt_2_);
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\realms\RealmsMth.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */