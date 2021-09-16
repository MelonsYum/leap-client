/*     */ package net.minecraft.world.gen.layer;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import java.util.List;
/*     */ 
/*     */ public class IntCache
/*     */ {
/*   8 */   private static int intCacheSize = 256;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  13 */   private static List freeSmallArrays = Lists.newArrayList();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  19 */   private static List inUseSmallArrays = Lists.newArrayList();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  24 */   private static List freeLargeArrays = Lists.newArrayList();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  30 */   private static List inUseLargeArrays = Lists.newArrayList();
/*     */ 
/*     */   
/*     */   private static final String __OBFID = "CL_00000557";
/*     */ 
/*     */   
/*     */   public static synchronized int[] getIntCache(int p_76445_0_) {
/*  37 */     if (p_76445_0_ <= 256) {
/*     */       
/*  39 */       if (freeSmallArrays.isEmpty()) {
/*     */         
/*  41 */         int[] arrayOfInt1 = new int[256];
/*  42 */         inUseSmallArrays.add(arrayOfInt1);
/*  43 */         return arrayOfInt1;
/*     */       } 
/*     */ 
/*     */       
/*  47 */       int[] arrayOfInt = freeSmallArrays.remove(freeSmallArrays.size() - 1);
/*  48 */       inUseSmallArrays.add(arrayOfInt);
/*  49 */       return arrayOfInt;
/*     */     } 
/*     */     
/*  52 */     if (p_76445_0_ > intCacheSize) {
/*     */       
/*  54 */       intCacheSize = p_76445_0_;
/*  55 */       freeLargeArrays.clear();
/*  56 */       inUseLargeArrays.clear();
/*  57 */       int[] arrayOfInt = new int[intCacheSize];
/*  58 */       inUseLargeArrays.add(arrayOfInt);
/*  59 */       return arrayOfInt;
/*     */     } 
/*  61 */     if (freeLargeArrays.isEmpty()) {
/*     */       
/*  63 */       int[] arrayOfInt = new int[intCacheSize];
/*  64 */       inUseLargeArrays.add(arrayOfInt);
/*  65 */       return arrayOfInt;
/*     */     } 
/*     */ 
/*     */     
/*  69 */     int[] var1 = freeLargeArrays.remove(freeLargeArrays.size() - 1);
/*  70 */     inUseLargeArrays.add(var1);
/*  71 */     return var1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static synchronized void resetIntCache() {
/*  80 */     if (!freeLargeArrays.isEmpty())
/*     */     {
/*  82 */       freeLargeArrays.remove(freeLargeArrays.size() - 1);
/*     */     }
/*     */     
/*  85 */     if (!freeSmallArrays.isEmpty())
/*     */     {
/*  87 */       freeSmallArrays.remove(freeSmallArrays.size() - 1);
/*     */     }
/*     */     
/*  90 */     freeLargeArrays.addAll(inUseLargeArrays);
/*  91 */     freeSmallArrays.addAll(inUseSmallArrays);
/*  92 */     inUseLargeArrays.clear();
/*  93 */     inUseSmallArrays.clear();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static synchronized String getCacheSizes() {
/* 102 */     return "cache: " + freeLargeArrays.size() + ", tcache: " + freeSmallArrays.size() + ", allocated: " + inUseLargeArrays.size() + ", tallocated: " + inUseSmallArrays.size();
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\world\gen\layer\IntCache.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */