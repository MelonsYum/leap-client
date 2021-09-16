/*     */ package net.minecraft.world.gen.layer;
/*     */ 
/*     */ import net.minecraft.world.biome.BiomeGenBase;
/*     */ 
/*     */ public class GenLayerBiomeEdge
/*     */   extends GenLayer
/*     */ {
/*     */   private static final String __OBFID = "CL_00000554";
/*     */   
/*     */   public GenLayerBiomeEdge(long p_i45475_1_, GenLayer p_i45475_3_) {
/*  11 */     super(p_i45475_1_);
/*  12 */     this.parent = p_i45475_3_;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int[] getInts(int areaX, int areaY, int areaWidth, int areaHeight) {
/*  21 */     int[] var5 = this.parent.getInts(areaX - 1, areaY - 1, areaWidth + 2, areaHeight + 2);
/*  22 */     int[] var6 = IntCache.getIntCache(areaWidth * areaHeight);
/*     */     
/*  24 */     for (int var7 = 0; var7 < areaHeight; var7++) {
/*     */       
/*  26 */       for (int var8 = 0; var8 < areaWidth; var8++) {
/*     */         
/*  28 */         initChunkSeed((var8 + areaX), (var7 + areaY));
/*  29 */         int var9 = var5[var8 + 1 + (var7 + 1) * (areaWidth + 2)];
/*     */         
/*  31 */         if (!replaceBiomeEdgeIfNecessary(var5, var6, var8, var7, areaWidth, var9, BiomeGenBase.extremeHills.biomeID, BiomeGenBase.extremeHillsEdge.biomeID) && !replaceBiomeEdge(var5, var6, var8, var7, areaWidth, var9, BiomeGenBase.mesaPlateau_F.biomeID, BiomeGenBase.mesa.biomeID) && !replaceBiomeEdge(var5, var6, var8, var7, areaWidth, var9, BiomeGenBase.mesaPlateau.biomeID, BiomeGenBase.mesa.biomeID) && !replaceBiomeEdge(var5, var6, var8, var7, areaWidth, var9, BiomeGenBase.megaTaiga.biomeID, BiomeGenBase.taiga.biomeID))
/*     */         {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/*  38 */           if (var9 == BiomeGenBase.desert.biomeID) {
/*     */             
/*  40 */             int var10 = var5[var8 + 1 + (var7 + 1 - 1) * (areaWidth + 2)];
/*  41 */             int var11 = var5[var8 + 1 + 1 + (var7 + 1) * (areaWidth + 2)];
/*  42 */             int var12 = var5[var8 + 1 - 1 + (var7 + 1) * (areaWidth + 2)];
/*  43 */             int var13 = var5[var8 + 1 + (var7 + 1 + 1) * (areaWidth + 2)];
/*     */             
/*  45 */             if (var10 != BiomeGenBase.icePlains.biomeID && var11 != BiomeGenBase.icePlains.biomeID && var12 != BiomeGenBase.icePlains.biomeID && var13 != BiomeGenBase.icePlains.biomeID)
/*     */             {
/*  47 */               var6[var8 + var7 * areaWidth] = var9;
/*     */             }
/*     */             else
/*     */             {
/*  51 */               var6[var8 + var7 * areaWidth] = BiomeGenBase.extremeHillsPlus.biomeID;
/*     */             }
/*     */           
/*  54 */           } else if (var9 == BiomeGenBase.swampland.biomeID) {
/*     */             
/*  56 */             int var10 = var5[var8 + 1 + (var7 + 1 - 1) * (areaWidth + 2)];
/*  57 */             int var11 = var5[var8 + 1 + 1 + (var7 + 1) * (areaWidth + 2)];
/*  58 */             int var12 = var5[var8 + 1 - 1 + (var7 + 1) * (areaWidth + 2)];
/*  59 */             int var13 = var5[var8 + 1 + (var7 + 1 + 1) * (areaWidth + 2)];
/*     */             
/*  61 */             if (var10 != BiomeGenBase.desert.biomeID && var11 != BiomeGenBase.desert.biomeID && var12 != BiomeGenBase.desert.biomeID && var13 != BiomeGenBase.desert.biomeID && var10 != BiomeGenBase.coldTaiga.biomeID && var11 != BiomeGenBase.coldTaiga.biomeID && var12 != BiomeGenBase.coldTaiga.biomeID && var13 != BiomeGenBase.coldTaiga.biomeID && var10 != BiomeGenBase.icePlains.biomeID && var11 != BiomeGenBase.icePlains.biomeID && var12 != BiomeGenBase.icePlains.biomeID && var13 != BiomeGenBase.icePlains.biomeID) {
/*     */               
/*  63 */               if (var10 != BiomeGenBase.jungle.biomeID && var13 != BiomeGenBase.jungle.biomeID && var11 != BiomeGenBase.jungle.biomeID && var12 != BiomeGenBase.jungle.biomeID)
/*     */               {
/*  65 */                 var6[var8 + var7 * areaWidth] = var9;
/*     */               }
/*     */               else
/*     */               {
/*  69 */                 var6[var8 + var7 * areaWidth] = BiomeGenBase.jungleEdge.biomeID;
/*     */               }
/*     */             
/*     */             } else {
/*     */               
/*  74 */               var6[var8 + var7 * areaWidth] = BiomeGenBase.plains.biomeID;
/*     */             }
/*     */           
/*     */           } else {
/*     */             
/*  79 */             var6[var8 + var7 * areaWidth] = var9;
/*     */           } 
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/*  85 */     return var6;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean replaceBiomeEdgeIfNecessary(int[] p_151636_1_, int[] p_151636_2_, int p_151636_3_, int p_151636_4_, int p_151636_5_, int p_151636_6_, int p_151636_7_, int p_151636_8_) {
/*  93 */     if (!biomesEqualOrMesaPlateau(p_151636_6_, p_151636_7_))
/*     */     {
/*  95 */       return false;
/*     */     }
/*     */ 
/*     */     
/*  99 */     int var9 = p_151636_1_[p_151636_3_ + 1 + (p_151636_4_ + 1 - 1) * (p_151636_5_ + 2)];
/* 100 */     int var10 = p_151636_1_[p_151636_3_ + 1 + 1 + (p_151636_4_ + 1) * (p_151636_5_ + 2)];
/* 101 */     int var11 = p_151636_1_[p_151636_3_ + 1 - 1 + (p_151636_4_ + 1) * (p_151636_5_ + 2)];
/* 102 */     int var12 = p_151636_1_[p_151636_3_ + 1 + (p_151636_4_ + 1 + 1) * (p_151636_5_ + 2)];
/*     */     
/* 104 */     if (canBiomesBeNeighbors(var9, p_151636_7_) && canBiomesBeNeighbors(var10, p_151636_7_) && canBiomesBeNeighbors(var11, p_151636_7_) && canBiomesBeNeighbors(var12, p_151636_7_)) {
/*     */       
/* 106 */       p_151636_2_[p_151636_3_ + p_151636_4_ * p_151636_5_] = p_151636_6_;
/*     */     }
/*     */     else {
/*     */       
/* 110 */       p_151636_2_[p_151636_3_ + p_151636_4_ * p_151636_5_] = p_151636_8_;
/*     */     } 
/*     */     
/* 113 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean replaceBiomeEdge(int[] p_151635_1_, int[] p_151635_2_, int p_151635_3_, int p_151635_4_, int p_151635_5_, int p_151635_6_, int p_151635_7_, int p_151635_8_) {
/* 122 */     if (p_151635_6_ != p_151635_7_)
/*     */     {
/* 124 */       return false;
/*     */     }
/*     */ 
/*     */     
/* 128 */     int var9 = p_151635_1_[p_151635_3_ + 1 + (p_151635_4_ + 1 - 1) * (p_151635_5_ + 2)];
/* 129 */     int var10 = p_151635_1_[p_151635_3_ + 1 + 1 + (p_151635_4_ + 1) * (p_151635_5_ + 2)];
/* 130 */     int var11 = p_151635_1_[p_151635_3_ + 1 - 1 + (p_151635_4_ + 1) * (p_151635_5_ + 2)];
/* 131 */     int var12 = p_151635_1_[p_151635_3_ + 1 + (p_151635_4_ + 1 + 1) * (p_151635_5_ + 2)];
/*     */     
/* 133 */     if (biomesEqualOrMesaPlateau(var9, p_151635_7_) && biomesEqualOrMesaPlateau(var10, p_151635_7_) && biomesEqualOrMesaPlateau(var11, p_151635_7_) && biomesEqualOrMesaPlateau(var12, p_151635_7_)) {
/*     */       
/* 135 */       p_151635_2_[p_151635_3_ + p_151635_4_ * p_151635_5_] = p_151635_6_;
/*     */     }
/*     */     else {
/*     */       
/* 139 */       p_151635_2_[p_151635_3_ + p_151635_4_ * p_151635_5_] = p_151635_8_;
/*     */     } 
/*     */     
/* 142 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean canBiomesBeNeighbors(int p_151634_1_, int p_151634_2_) {
/* 152 */     if (biomesEqualOrMesaPlateau(p_151634_1_, p_151634_2_))
/*     */     {
/* 154 */       return true;
/*     */     }
/*     */ 
/*     */     
/* 158 */     BiomeGenBase var3 = BiomeGenBase.getBiome(p_151634_1_);
/* 159 */     BiomeGenBase var4 = BiomeGenBase.getBiome(p_151634_2_);
/*     */     
/* 161 */     if (var3 != null && var4 != null) {
/*     */       
/* 163 */       BiomeGenBase.TempCategory var5 = var3.getTempCategory();
/* 164 */       BiomeGenBase.TempCategory var6 = var4.getTempCategory();
/* 165 */       return !(var5 != var6 && var5 != BiomeGenBase.TempCategory.MEDIUM && var6 != BiomeGenBase.TempCategory.MEDIUM);
/*     */     } 
/*     */ 
/*     */     
/* 169 */     return false;
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\world\gen\layer\GenLayerBiomeEdge.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */