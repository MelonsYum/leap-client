/*     */ package net.minecraft.world.gen.layer;
/*     */ 
/*     */ import net.minecraft.world.WorldType;
/*     */ import net.minecraft.world.biome.BiomeGenBase;
/*     */ import net.minecraft.world.gen.ChunkProviderSettings;
/*     */ 
/*     */ public class GenLayerBiome
/*     */   extends GenLayer
/*     */ {
/*     */   private BiomeGenBase[] field_151623_c;
/*     */   private BiomeGenBase[] field_151621_d;
/*     */   private BiomeGenBase[] field_151622_e;
/*     */   private BiomeGenBase[] field_151620_f;
/*     */   private final ChunkProviderSettings field_175973_g;
/*     */   private static final String __OBFID = "CL_00000555";
/*     */   
/*     */   public GenLayerBiome(long p_i45560_1_, GenLayer p_i45560_3_, WorldType p_i45560_4_, String p_i45560_5_) {
/*  18 */     super(p_i45560_1_);
/*  19 */     this.field_151623_c = new BiomeGenBase[] { BiomeGenBase.desert, BiomeGenBase.desert, BiomeGenBase.desert, BiomeGenBase.savanna, BiomeGenBase.savanna, BiomeGenBase.plains };
/*  20 */     this.field_151621_d = new BiomeGenBase[] { BiomeGenBase.forest, BiomeGenBase.roofedForest, BiomeGenBase.extremeHills, BiomeGenBase.plains, BiomeGenBase.birchForest, BiomeGenBase.swampland };
/*  21 */     this.field_151622_e = new BiomeGenBase[] { BiomeGenBase.forest, BiomeGenBase.extremeHills, BiomeGenBase.taiga, BiomeGenBase.plains };
/*  22 */     this.field_151620_f = new BiomeGenBase[] { BiomeGenBase.icePlains, BiomeGenBase.icePlains, BiomeGenBase.icePlains, BiomeGenBase.coldTaiga };
/*  23 */     this.parent = p_i45560_3_;
/*     */     
/*  25 */     if (p_i45560_4_ == WorldType.DEFAULT_1_1) {
/*     */       
/*  27 */       this.field_151623_c = new BiomeGenBase[] { BiomeGenBase.desert, BiomeGenBase.forest, BiomeGenBase.extremeHills, BiomeGenBase.swampland, BiomeGenBase.plains, BiomeGenBase.taiga };
/*  28 */       this.field_175973_g = null;
/*     */     }
/*  30 */     else if (p_i45560_4_ == WorldType.CUSTOMIZED) {
/*     */       
/*  32 */       this.field_175973_g = ChunkProviderSettings.Factory.func_177865_a(p_i45560_5_).func_177864_b();
/*     */     }
/*     */     else {
/*     */       
/*  36 */       this.field_175973_g = null;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int[] getInts(int areaX, int areaY, int areaWidth, int areaHeight) {
/*  46 */     int[] var5 = this.parent.getInts(areaX, areaY, areaWidth, areaHeight);
/*  47 */     int[] var6 = IntCache.getIntCache(areaWidth * areaHeight);
/*     */     
/*  49 */     for (int var7 = 0; var7 < areaHeight; var7++) {
/*     */       
/*  51 */       for (int var8 = 0; var8 < areaWidth; var8++) {
/*     */         
/*  53 */         initChunkSeed((var8 + areaX), (var7 + areaY));
/*  54 */         int var9 = var5[var8 + var7 * areaWidth];
/*  55 */         int var10 = (var9 & 0xF00) >> 8;
/*  56 */         var9 &= 0xFFFFF0FF;
/*     */         
/*  58 */         if (this.field_175973_g != null && this.field_175973_g.field_177779_F >= 0) {
/*     */           
/*  60 */           var6[var8 + var7 * areaWidth] = this.field_175973_g.field_177779_F;
/*     */         }
/*  62 */         else if (isBiomeOceanic(var9)) {
/*     */           
/*  64 */           var6[var8 + var7 * areaWidth] = var9;
/*     */         }
/*  66 */         else if (var9 == BiomeGenBase.mushroomIsland.biomeID) {
/*     */           
/*  68 */           var6[var8 + var7 * areaWidth] = var9;
/*     */         }
/*  70 */         else if (var9 == 1) {
/*     */           
/*  72 */           if (var10 > 0) {
/*     */             
/*  74 */             if (nextInt(3) == 0)
/*     */             {
/*  76 */               var6[var8 + var7 * areaWidth] = BiomeGenBase.mesaPlateau.biomeID;
/*     */             }
/*     */             else
/*     */             {
/*  80 */               var6[var8 + var7 * areaWidth] = BiomeGenBase.mesaPlateau_F.biomeID;
/*     */             }
/*     */           
/*     */           } else {
/*     */             
/*  85 */             var6[var8 + var7 * areaWidth] = (this.field_151623_c[nextInt(this.field_151623_c.length)]).biomeID;
/*     */           }
/*     */         
/*  88 */         } else if (var9 == 2) {
/*     */           
/*  90 */           if (var10 > 0)
/*     */           {
/*  92 */             var6[var8 + var7 * areaWidth] = BiomeGenBase.jungle.biomeID;
/*     */           }
/*     */           else
/*     */           {
/*  96 */             var6[var8 + var7 * areaWidth] = (this.field_151621_d[nextInt(this.field_151621_d.length)]).biomeID;
/*     */           }
/*     */         
/*  99 */         } else if (var9 == 3) {
/*     */           
/* 101 */           if (var10 > 0)
/*     */           {
/* 103 */             var6[var8 + var7 * areaWidth] = BiomeGenBase.megaTaiga.biomeID;
/*     */           }
/*     */           else
/*     */           {
/* 107 */             var6[var8 + var7 * areaWidth] = (this.field_151622_e[nextInt(this.field_151622_e.length)]).biomeID;
/*     */           }
/*     */         
/* 110 */         } else if (var9 == 4) {
/*     */           
/* 112 */           var6[var8 + var7 * areaWidth] = (this.field_151620_f[nextInt(this.field_151620_f.length)]).biomeID;
/*     */         }
/*     */         else {
/*     */           
/* 116 */           var6[var8 + var7 * areaWidth] = BiomeGenBase.mushroomIsland.biomeID;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 121 */     return var6;
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\world\gen\layer\GenLayerBiome.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */