/*     */ package net.minecraft.world.gen.layer;
/*     */ 
/*     */ import net.minecraft.world.biome.BiomeGenBase;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ 
/*     */ public class GenLayerHills
/*     */   extends GenLayer {
/*   9 */   private static final Logger logger = LogManager.getLogger();
/*     */   
/*     */   private GenLayer field_151628_d;
/*     */   private static final String __OBFID = "CL_00000563";
/*     */   
/*     */   public GenLayerHills(long p_i45479_1_, GenLayer p_i45479_3_, GenLayer p_i45479_4_) {
/*  15 */     super(p_i45479_1_);
/*  16 */     this.parent = p_i45479_3_;
/*  17 */     this.field_151628_d = p_i45479_4_;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int[] getInts(int areaX, int areaY, int areaWidth, int areaHeight) {
/*  26 */     int[] var5 = this.parent.getInts(areaX - 1, areaY - 1, areaWidth + 2, areaHeight + 2);
/*  27 */     int[] var6 = this.field_151628_d.getInts(areaX - 1, areaY - 1, areaWidth + 2, areaHeight + 2);
/*  28 */     int[] var7 = IntCache.getIntCache(areaWidth * areaHeight);
/*     */     
/*  30 */     for (int var8 = 0; var8 < areaHeight; var8++) {
/*     */       
/*  32 */       for (int var9 = 0; var9 < areaWidth; var9++) {
/*     */         
/*  34 */         initChunkSeed((var9 + areaX), (var8 + areaY));
/*  35 */         int var10 = var5[var9 + 1 + (var8 + 1) * (areaWidth + 2)];
/*  36 */         int var11 = var6[var9 + 1 + (var8 + 1) * (areaWidth + 2)];
/*  37 */         boolean var12 = ((var11 - 2) % 29 == 0);
/*     */         
/*  39 */         if (var10 > 255)
/*     */         {
/*  41 */           logger.debug("old! " + var10);
/*     */         }
/*     */         
/*  44 */         if (var10 != 0 && var11 >= 2 && (var11 - 2) % 29 == 1 && var10 < 128) {
/*     */           
/*  46 */           if (BiomeGenBase.getBiome(var10 + 128) != null)
/*     */           {
/*  48 */             var7[var9 + var8 * areaWidth] = var10 + 128;
/*     */           }
/*     */           else
/*     */           {
/*  52 */             var7[var9 + var8 * areaWidth] = var10;
/*     */           }
/*     */         
/*  55 */         } else if (nextInt(3) != 0 && !var12) {
/*     */           
/*  57 */           var7[var9 + var8 * areaWidth] = var10;
/*     */         }
/*     */         else {
/*     */           
/*  61 */           int var13 = var10;
/*     */ 
/*     */           
/*  64 */           if (var10 == BiomeGenBase.desert.biomeID) {
/*     */             
/*  66 */             var13 = BiomeGenBase.desertHills.biomeID;
/*     */           }
/*  68 */           else if (var10 == BiomeGenBase.forest.biomeID) {
/*     */             
/*  70 */             var13 = BiomeGenBase.forestHills.biomeID;
/*     */           }
/*  72 */           else if (var10 == BiomeGenBase.birchForest.biomeID) {
/*     */             
/*  74 */             var13 = BiomeGenBase.birchForestHills.biomeID;
/*     */           }
/*  76 */           else if (var10 == BiomeGenBase.roofedForest.biomeID) {
/*     */             
/*  78 */             var13 = BiomeGenBase.plains.biomeID;
/*     */           }
/*  80 */           else if (var10 == BiomeGenBase.taiga.biomeID) {
/*     */             
/*  82 */             var13 = BiomeGenBase.taigaHills.biomeID;
/*     */           }
/*  84 */           else if (var10 == BiomeGenBase.megaTaiga.biomeID) {
/*     */             
/*  86 */             var13 = BiomeGenBase.megaTaigaHills.biomeID;
/*     */           }
/*  88 */           else if (var10 == BiomeGenBase.coldTaiga.biomeID) {
/*     */             
/*  90 */             var13 = BiomeGenBase.coldTaigaHills.biomeID;
/*     */           }
/*  92 */           else if (var10 == BiomeGenBase.plains.biomeID) {
/*     */             
/*  94 */             if (nextInt(3) == 0)
/*     */             {
/*  96 */               var13 = BiomeGenBase.forestHills.biomeID;
/*     */             }
/*     */             else
/*     */             {
/* 100 */               var13 = BiomeGenBase.forest.biomeID;
/*     */             }
/*     */           
/* 103 */           } else if (var10 == BiomeGenBase.icePlains.biomeID) {
/*     */             
/* 105 */             var13 = BiomeGenBase.iceMountains.biomeID;
/*     */           }
/* 107 */           else if (var10 == BiomeGenBase.jungle.biomeID) {
/*     */             
/* 109 */             var13 = BiomeGenBase.jungleHills.biomeID;
/*     */           }
/* 111 */           else if (var10 == BiomeGenBase.ocean.biomeID) {
/*     */             
/* 113 */             var13 = BiomeGenBase.deepOcean.biomeID;
/*     */           }
/* 115 */           else if (var10 == BiomeGenBase.extremeHills.biomeID) {
/*     */             
/* 117 */             var13 = BiomeGenBase.extremeHillsPlus.biomeID;
/*     */           }
/* 119 */           else if (var10 == BiomeGenBase.savanna.biomeID) {
/*     */             
/* 121 */             var13 = BiomeGenBase.savannaPlateau.biomeID;
/*     */           }
/* 123 */           else if (biomesEqualOrMesaPlateau(var10, BiomeGenBase.mesaPlateau_F.biomeID)) {
/*     */             
/* 125 */             var13 = BiomeGenBase.mesa.biomeID;
/*     */           }
/* 127 */           else if (var10 == BiomeGenBase.deepOcean.biomeID && nextInt(3) == 0) {
/*     */             
/* 129 */             int var14 = nextInt(2);
/*     */             
/* 131 */             if (var14 == 0) {
/*     */               
/* 133 */               var13 = BiomeGenBase.plains.biomeID;
/*     */             }
/*     */             else {
/*     */               
/* 137 */               var13 = BiomeGenBase.forest.biomeID;
/*     */             } 
/*     */           } 
/*     */           
/* 141 */           if (var12 && var13 != var10)
/*     */           {
/* 143 */             if (BiomeGenBase.getBiome(var13 + 128) != null) {
/*     */               
/* 145 */               var13 += 128;
/*     */             }
/*     */             else {
/*     */               
/* 149 */               var13 = var10;
/*     */             } 
/*     */           }
/*     */           
/* 153 */           if (var13 == var10) {
/*     */             
/* 155 */             var7[var9 + var8 * areaWidth] = var10;
/*     */           }
/*     */           else {
/*     */             
/* 159 */             int var14 = var5[var9 + 1 + (var8 + 1 - 1) * (areaWidth + 2)];
/* 160 */             int var15 = var5[var9 + 1 + 1 + (var8 + 1) * (areaWidth + 2)];
/* 161 */             int var16 = var5[var9 + 1 - 1 + (var8 + 1) * (areaWidth + 2)];
/* 162 */             int var17 = var5[var9 + 1 + (var8 + 1 + 1) * (areaWidth + 2)];
/* 163 */             int var18 = 0;
/*     */             
/* 165 */             if (biomesEqualOrMesaPlateau(var14, var10))
/*     */             {
/* 167 */               var18++;
/*     */             }
/*     */             
/* 170 */             if (biomesEqualOrMesaPlateau(var15, var10))
/*     */             {
/* 172 */               var18++;
/*     */             }
/*     */             
/* 175 */             if (biomesEqualOrMesaPlateau(var16, var10))
/*     */             {
/* 177 */               var18++;
/*     */             }
/*     */             
/* 180 */             if (biomesEqualOrMesaPlateau(var17, var10))
/*     */             {
/* 182 */               var18++;
/*     */             }
/*     */             
/* 185 */             if (var18 >= 3) {
/*     */               
/* 187 */               var7[var9 + var8 * areaWidth] = var13;
/*     */             }
/*     */             else {
/*     */               
/* 191 */               var7[var9 + var8 * areaWidth] = var10;
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 198 */     return var7;
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\world\gen\layer\GenLayerHills.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */