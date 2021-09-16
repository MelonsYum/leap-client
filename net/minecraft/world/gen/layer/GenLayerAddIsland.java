/*     */ package net.minecraft.world.gen.layer;
/*     */ 
/*     */ public class GenLayerAddIsland
/*     */   extends GenLayer
/*     */ {
/*     */   private static final String __OBFID = "CL_00000551";
/*     */   
/*     */   public GenLayerAddIsland(long p_i2119_1_, GenLayer p_i2119_3_) {
/*   9 */     super(p_i2119_1_);
/*  10 */     this.parent = p_i2119_3_;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int[] getInts(int areaX, int areaY, int areaWidth, int areaHeight) {
/*  19 */     int var5 = areaX - 1;
/*  20 */     int var6 = areaY - 1;
/*  21 */     int var7 = areaWidth + 2;
/*  22 */     int var8 = areaHeight + 2;
/*  23 */     int[] var9 = this.parent.getInts(var5, var6, var7, var8);
/*  24 */     int[] var10 = IntCache.getIntCache(areaWidth * areaHeight);
/*     */     
/*  26 */     for (int var11 = 0; var11 < areaHeight; var11++) {
/*     */       
/*  28 */       for (int var12 = 0; var12 < areaWidth; var12++) {
/*     */         
/*  30 */         int var13 = var9[var12 + 0 + (var11 + 0) * var7];
/*  31 */         int var14 = var9[var12 + 2 + (var11 + 0) * var7];
/*  32 */         int var15 = var9[var12 + 0 + (var11 + 2) * var7];
/*  33 */         int var16 = var9[var12 + 2 + (var11 + 2) * var7];
/*  34 */         int var17 = var9[var12 + 1 + (var11 + 1) * var7];
/*  35 */         initChunkSeed((var12 + areaX), (var11 + areaY));
/*     */         
/*  37 */         if (var17 == 0 && (var13 != 0 || var14 != 0 || var15 != 0 || var16 != 0)) {
/*     */           
/*  39 */           int var18 = 1;
/*  40 */           int var19 = 1;
/*     */           
/*  42 */           if (var13 != 0 && nextInt(var18++) == 0)
/*     */           {
/*  44 */             var19 = var13;
/*     */           }
/*     */           
/*  47 */           if (var14 != 0 && nextInt(var18++) == 0)
/*     */           {
/*  49 */             var19 = var14;
/*     */           }
/*     */           
/*  52 */           if (var15 != 0 && nextInt(var18++) == 0)
/*     */           {
/*  54 */             var19 = var15;
/*     */           }
/*     */           
/*  57 */           if (var16 != 0 && nextInt(var18++) == 0)
/*     */           {
/*  59 */             var19 = var16;
/*     */           }
/*     */           
/*  62 */           if (nextInt(3) == 0)
/*     */           {
/*  64 */             var10[var12 + var11 * areaWidth] = var19;
/*     */           }
/*  66 */           else if (var19 == 4)
/*     */           {
/*  68 */             var10[var12 + var11 * areaWidth] = 4;
/*     */           }
/*     */           else
/*     */           {
/*  72 */             var10[var12 + var11 * areaWidth] = 0;
/*     */           }
/*     */         
/*  75 */         } else if (var17 > 0 && (var13 == 0 || var14 == 0 || var15 == 0 || var16 == 0)) {
/*     */           
/*  77 */           if (nextInt(5) == 0) {
/*     */             
/*  79 */             if (var17 == 4)
/*     */             {
/*  81 */               var10[var12 + var11 * areaWidth] = 4;
/*     */             }
/*     */             else
/*     */             {
/*  85 */               var10[var12 + var11 * areaWidth] = 0;
/*     */             }
/*     */           
/*     */           } else {
/*     */             
/*  90 */             var10[var12 + var11 * areaWidth] = var17;
/*     */           }
/*     */         
/*     */         } else {
/*     */           
/*  95 */           var10[var12 + var11 * areaWidth] = var17;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 100 */     return var10;
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\world\gen\layer\GenLayerAddIsland.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */