/*     */ package net.minecraft.world.gen.layer;
/*     */ 
/*     */ import net.minecraft.world.biome.BiomeGenBase;
/*     */ import net.minecraft.world.biome.BiomeGenJungle;
/*     */ 
/*     */ 
/*     */ public class GenLayerShore
/*     */   extends GenLayer
/*     */ {
/*     */   private static final String __OBFID = "CL_00000568";
/*     */   
/*     */   public GenLayerShore(long p_i2130_1_, GenLayer p_i2130_3_) {
/*  13 */     super(p_i2130_1_);
/*  14 */     this.parent = p_i2130_3_;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int[] getInts(int areaX, int areaY, int areaWidth, int areaHeight) {
/*  23 */     int[] var5 = this.parent.getInts(areaX - 1, areaY - 1, areaWidth + 2, areaHeight + 2);
/*  24 */     int[] var6 = IntCache.getIntCache(areaWidth * areaHeight);
/*     */     
/*  26 */     for (int var7 = 0; var7 < areaHeight; var7++) {
/*     */       
/*  28 */       for (int var8 = 0; var8 < areaWidth; var8++) {
/*     */         
/*  30 */         initChunkSeed((var8 + areaX), (var7 + areaY));
/*  31 */         int var9 = var5[var8 + 1 + (var7 + 1) * (areaWidth + 2)];
/*  32 */         BiomeGenBase var10 = BiomeGenBase.getBiome(var9);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*  38 */         if (var9 == BiomeGenBase.mushroomIsland.biomeID) {
/*     */           
/*  40 */           int var11 = var5[var8 + 1 + (var7 + 1 - 1) * (areaWidth + 2)];
/*  41 */           int var12 = var5[var8 + 1 + 1 + (var7 + 1) * (areaWidth + 2)];
/*  42 */           int var13 = var5[var8 + 1 - 1 + (var7 + 1) * (areaWidth + 2)];
/*  43 */           int var14 = var5[var8 + 1 + (var7 + 1 + 1) * (areaWidth + 2)];
/*     */           
/*  45 */           if (var11 != BiomeGenBase.ocean.biomeID && var12 != BiomeGenBase.ocean.biomeID && var13 != BiomeGenBase.ocean.biomeID && var14 != BiomeGenBase.ocean.biomeID)
/*     */           {
/*  47 */             var6[var8 + var7 * areaWidth] = var9;
/*     */           }
/*     */           else
/*     */           {
/*  51 */             var6[var8 + var7 * areaWidth] = BiomeGenBase.mushroomIslandShore.biomeID;
/*     */           }
/*     */         
/*  54 */         } else if (var10 != null && var10.getBiomeClass() == BiomeGenJungle.class) {
/*     */           
/*  56 */           int var11 = var5[var8 + 1 + (var7 + 1 - 1) * (areaWidth + 2)];
/*  57 */           int var12 = var5[var8 + 1 + 1 + (var7 + 1) * (areaWidth + 2)];
/*  58 */           int var13 = var5[var8 + 1 - 1 + (var7 + 1) * (areaWidth + 2)];
/*  59 */           int var14 = var5[var8 + 1 + (var7 + 1 + 1) * (areaWidth + 2)];
/*     */           
/*  61 */           if (func_151631_c(var11) && func_151631_c(var12) && func_151631_c(var13) && func_151631_c(var14)) {
/*     */             
/*  63 */             if (!isBiomeOceanic(var11) && !isBiomeOceanic(var12) && !isBiomeOceanic(var13) && !isBiomeOceanic(var14))
/*     */             {
/*  65 */               var6[var8 + var7 * areaWidth] = var9;
/*     */             }
/*     */             else
/*     */             {
/*  69 */               var6[var8 + var7 * areaWidth] = BiomeGenBase.beach.biomeID;
/*     */             }
/*     */           
/*     */           } else {
/*     */             
/*  74 */             var6[var8 + var7 * areaWidth] = BiomeGenBase.jungleEdge.biomeID;
/*     */           }
/*     */         
/*  77 */         } else if (var9 != BiomeGenBase.extremeHills.biomeID && var9 != BiomeGenBase.extremeHillsPlus.biomeID && var9 != BiomeGenBase.extremeHillsEdge.biomeID) {
/*     */           
/*  79 */           if (var10 != null && var10.isSnowyBiome()) {
/*     */             
/*  81 */             func_151632_a(var5, var6, var8, var7, areaWidth, var9, BiomeGenBase.coldBeach.biomeID);
/*     */           }
/*  83 */           else if (var9 != BiomeGenBase.mesa.biomeID && var9 != BiomeGenBase.mesaPlateau_F.biomeID) {
/*     */             
/*  85 */             if (var9 != BiomeGenBase.ocean.biomeID && var9 != BiomeGenBase.deepOcean.biomeID && var9 != BiomeGenBase.river.biomeID && var9 != BiomeGenBase.swampland.biomeID) {
/*     */               
/*  87 */               int var11 = var5[var8 + 1 + (var7 + 1 - 1) * (areaWidth + 2)];
/*  88 */               int var12 = var5[var8 + 1 + 1 + (var7 + 1) * (areaWidth + 2)];
/*  89 */               int var13 = var5[var8 + 1 - 1 + (var7 + 1) * (areaWidth + 2)];
/*  90 */               int var14 = var5[var8 + 1 + (var7 + 1 + 1) * (areaWidth + 2)];
/*     */               
/*  92 */               if (!isBiomeOceanic(var11) && !isBiomeOceanic(var12) && !isBiomeOceanic(var13) && !isBiomeOceanic(var14))
/*     */               {
/*  94 */                 var6[var8 + var7 * areaWidth] = var9;
/*     */               }
/*     */               else
/*     */               {
/*  98 */                 var6[var8 + var7 * areaWidth] = BiomeGenBase.beach.biomeID;
/*     */               }
/*     */             
/*     */             } else {
/*     */               
/* 103 */               var6[var8 + var7 * areaWidth] = var9;
/*     */             }
/*     */           
/*     */           } else {
/*     */             
/* 108 */             int var11 = var5[var8 + 1 + (var7 + 1 - 1) * (areaWidth + 2)];
/* 109 */             int var12 = var5[var8 + 1 + 1 + (var7 + 1) * (areaWidth + 2)];
/* 110 */             int var13 = var5[var8 + 1 - 1 + (var7 + 1) * (areaWidth + 2)];
/* 111 */             int var14 = var5[var8 + 1 + (var7 + 1 + 1) * (areaWidth + 2)];
/*     */             
/* 113 */             if (!isBiomeOceanic(var11) && !isBiomeOceanic(var12) && !isBiomeOceanic(var13) && !isBiomeOceanic(var14)) {
/*     */               
/* 115 */               if (func_151633_d(var11) && func_151633_d(var12) && func_151633_d(var13) && func_151633_d(var14))
/*     */               {
/* 117 */                 var6[var8 + var7 * areaWidth] = var9;
/*     */               }
/*     */               else
/*     */               {
/* 121 */                 var6[var8 + var7 * areaWidth] = BiomeGenBase.desert.biomeID;
/*     */               }
/*     */             
/*     */             } else {
/*     */               
/* 126 */               var6[var8 + var7 * areaWidth] = var9;
/*     */             }
/*     */           
/*     */           } 
/*     */         } else {
/*     */           
/* 132 */           func_151632_a(var5, var6, var8, var7, areaWidth, var9, BiomeGenBase.stoneBeach.biomeID);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 137 */     return var6;
/*     */   }
/*     */ 
/*     */   
/*     */   private void func_151632_a(int[] p_151632_1_, int[] p_151632_2_, int p_151632_3_, int p_151632_4_, int p_151632_5_, int p_151632_6_, int p_151632_7_) {
/* 142 */     if (isBiomeOceanic(p_151632_6_)) {
/*     */       
/* 144 */       p_151632_2_[p_151632_3_ + p_151632_4_ * p_151632_5_] = p_151632_6_;
/*     */     }
/*     */     else {
/*     */       
/* 148 */       int var8 = p_151632_1_[p_151632_3_ + 1 + (p_151632_4_ + 1 - 1) * (p_151632_5_ + 2)];
/* 149 */       int var9 = p_151632_1_[p_151632_3_ + 1 + 1 + (p_151632_4_ + 1) * (p_151632_5_ + 2)];
/* 150 */       int var10 = p_151632_1_[p_151632_3_ + 1 - 1 + (p_151632_4_ + 1) * (p_151632_5_ + 2)];
/* 151 */       int var11 = p_151632_1_[p_151632_3_ + 1 + (p_151632_4_ + 1 + 1) * (p_151632_5_ + 2)];
/*     */       
/* 153 */       if (!isBiomeOceanic(var8) && !isBiomeOceanic(var9) && !isBiomeOceanic(var10) && !isBiomeOceanic(var11)) {
/*     */         
/* 155 */         p_151632_2_[p_151632_3_ + p_151632_4_ * p_151632_5_] = p_151632_6_;
/*     */       }
/*     */       else {
/*     */         
/* 159 */         p_151632_2_[p_151632_3_ + p_151632_4_ * p_151632_5_] = p_151632_7_;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean func_151631_c(int p_151631_1_) {
/* 166 */     return (BiomeGenBase.getBiome(p_151631_1_) != null && BiomeGenBase.getBiome(p_151631_1_).getBiomeClass() == BiomeGenJungle.class) ? true : (!(p_151631_1_ != BiomeGenBase.jungleEdge.biomeID && p_151631_1_ != BiomeGenBase.jungle.biomeID && p_151631_1_ != BiomeGenBase.jungleHills.biomeID && p_151631_1_ != BiomeGenBase.forest.biomeID && p_151631_1_ != BiomeGenBase.taiga.biomeID && !isBiomeOceanic(p_151631_1_)));
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean func_151633_d(int p_151633_1_) {
/* 171 */     return BiomeGenBase.getBiome(p_151633_1_) instanceof net.minecraft.world.biome.BiomeGenMesa;
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\world\gen\layer\GenLayerShore.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */