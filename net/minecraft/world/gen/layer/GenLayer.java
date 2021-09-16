/*     */ package net.minecraft.world.gen.layer;
/*     */ 
/*     */ import java.util.concurrent.Callable;
/*     */ import net.minecraft.crash.CrashReport;
/*     */ import net.minecraft.crash.CrashReportCategory;
/*     */ import net.minecraft.util.ReportedException;
/*     */ import net.minecraft.world.WorldType;
/*     */ import net.minecraft.world.biome.BiomeGenBase;
/*     */ import net.minecraft.world.gen.ChunkProviderSettings;
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
/*     */ public abstract class GenLayer
/*     */ {
/*     */   private long worldGenSeed;
/*     */   protected GenLayer parent;
/*     */   private long chunkSeed;
/*     */   protected long baseSeed;
/*     */   private static final String __OBFID = "CL_00000559";
/*     */   
/*     */   public static GenLayer[] func_180781_a(long p_180781_0_, WorldType p_180781_2_, String p_180781_3_) {
/*  31 */     GenLayerIsland var4 = new GenLayerIsland(1L);
/*  32 */     GenLayerFuzzyZoom var13 = new GenLayerFuzzyZoom(2000L, var4);
/*  33 */     GenLayerAddIsland var14 = new GenLayerAddIsland(1L, var13);
/*  34 */     GenLayerZoom var15 = new GenLayerZoom(2001L, var14);
/*  35 */     var14 = new GenLayerAddIsland(2L, var15);
/*  36 */     var14 = new GenLayerAddIsland(50L, var14);
/*  37 */     var14 = new GenLayerAddIsland(70L, var14);
/*  38 */     GenLayerRemoveTooMuchOcean var16 = new GenLayerRemoveTooMuchOcean(2L, var14);
/*  39 */     GenLayerAddSnow var17 = new GenLayerAddSnow(2L, var16);
/*  40 */     var14 = new GenLayerAddIsland(3L, var17);
/*  41 */     GenLayerEdge var18 = new GenLayerEdge(2L, var14, GenLayerEdge.Mode.COOL_WARM);
/*  42 */     var18 = new GenLayerEdge(2L, var18, GenLayerEdge.Mode.HEAT_ICE);
/*  43 */     var18 = new GenLayerEdge(3L, var18, GenLayerEdge.Mode.SPECIAL);
/*  44 */     var15 = new GenLayerZoom(2002L, var18);
/*  45 */     var15 = new GenLayerZoom(2003L, var15);
/*  46 */     var14 = new GenLayerAddIsland(4L, var15);
/*  47 */     GenLayerAddMushroomIsland var19 = new GenLayerAddMushroomIsland(5L, var14);
/*  48 */     GenLayerDeepOcean var20 = new GenLayerDeepOcean(4L, var19);
/*  49 */     GenLayer var21 = GenLayerZoom.magnify(1000L, var20, 0);
/*  50 */     ChunkProviderSettings var5 = null;
/*  51 */     int var6 = 4;
/*  52 */     int var7 = var6;
/*     */     
/*  54 */     if (p_180781_2_ == WorldType.CUSTOMIZED && p_180781_3_.length() > 0) {
/*     */       
/*  56 */       var5 = ChunkProviderSettings.Factory.func_177865_a(p_180781_3_).func_177864_b();
/*  57 */       var6 = var5.field_177780_G;
/*  58 */       var7 = var5.field_177788_H;
/*     */     } 
/*     */     
/*  61 */     if (p_180781_2_ == WorldType.LARGE_BIOMES)
/*     */     {
/*  63 */       var6 = 6;
/*     */     }
/*     */     
/*  66 */     GenLayer var8 = GenLayerZoom.magnify(1000L, var21, 0);
/*  67 */     GenLayerRiverInit var22 = new GenLayerRiverInit(100L, var8);
/*  68 */     GenLayerBiome var9 = new GenLayerBiome(200L, var21, p_180781_2_, p_180781_3_);
/*  69 */     GenLayer var25 = GenLayerZoom.magnify(1000L, var9, 2);
/*  70 */     GenLayerBiomeEdge var26 = new GenLayerBiomeEdge(1000L, var25);
/*  71 */     GenLayer var10 = GenLayerZoom.magnify(1000L, var22, 2);
/*  72 */     GenLayerHills var27 = new GenLayerHills(1000L, var26, var10);
/*  73 */     var8 = GenLayerZoom.magnify(1000L, var22, 2);
/*  74 */     var8 = GenLayerZoom.magnify(1000L, var8, var7);
/*  75 */     GenLayerRiver var23 = new GenLayerRiver(1L, var8);
/*  76 */     GenLayerSmooth var24 = new GenLayerSmooth(1000L, var23);
/*  77 */     Object var28 = new GenLayerRareBiome(1001L, var27);
/*     */     
/*  79 */     for (int var11 = 0; var11 < var6; var11++) {
/*     */       
/*  81 */       var28 = new GenLayerZoom((1000 + var11), (GenLayer)var28);
/*     */       
/*  83 */       if (var11 == 0)
/*     */       {
/*  85 */         var28 = new GenLayerAddIsland(3L, (GenLayer)var28);
/*     */       }
/*     */       
/*  88 */       if (var11 == 1 || var6 == 1)
/*     */       {
/*  90 */         var28 = new GenLayerShore(1000L, (GenLayer)var28);
/*     */       }
/*     */     } 
/*     */     
/*  94 */     GenLayerSmooth var29 = new GenLayerSmooth(1000L, (GenLayer)var28);
/*  95 */     GenLayerRiverMix var30 = new GenLayerRiverMix(100L, var29, var24);
/*  96 */     GenLayerVoronoiZoom var12 = new GenLayerVoronoiZoom(10L, var30);
/*  97 */     var30.initWorldGenSeed(p_180781_0_);
/*  98 */     var12.initWorldGenSeed(p_180781_0_);
/*  99 */     return new GenLayer[] { var30, var12, var30 };
/*     */   }
/*     */ 
/*     */   
/*     */   public GenLayer(long p_i2125_1_) {
/* 104 */     this.baseSeed = p_i2125_1_;
/* 105 */     this.baseSeed *= this.baseSeed * 6364136223846793005L + 1442695040888963407L;
/* 106 */     this.baseSeed += p_i2125_1_;
/* 107 */     this.baseSeed *= this.baseSeed * 6364136223846793005L + 1442695040888963407L;
/* 108 */     this.baseSeed += p_i2125_1_;
/* 109 */     this.baseSeed *= this.baseSeed * 6364136223846793005L + 1442695040888963407L;
/* 110 */     this.baseSeed += p_i2125_1_;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void initWorldGenSeed(long p_75905_1_) {
/* 119 */     this.worldGenSeed = p_75905_1_;
/*     */     
/* 121 */     if (this.parent != null)
/*     */     {
/* 123 */       this.parent.initWorldGenSeed(p_75905_1_);
/*     */     }
/*     */     
/* 126 */     this.worldGenSeed *= this.worldGenSeed * 6364136223846793005L + 1442695040888963407L;
/* 127 */     this.worldGenSeed += this.baseSeed;
/* 128 */     this.worldGenSeed *= this.worldGenSeed * 6364136223846793005L + 1442695040888963407L;
/* 129 */     this.worldGenSeed += this.baseSeed;
/* 130 */     this.worldGenSeed *= this.worldGenSeed * 6364136223846793005L + 1442695040888963407L;
/* 131 */     this.worldGenSeed += this.baseSeed;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void initChunkSeed(long p_75903_1_, long p_75903_3_) {
/* 139 */     this.chunkSeed = this.worldGenSeed;
/* 140 */     this.chunkSeed *= this.chunkSeed * 6364136223846793005L + 1442695040888963407L;
/* 141 */     this.chunkSeed += p_75903_1_;
/* 142 */     this.chunkSeed *= this.chunkSeed * 6364136223846793005L + 1442695040888963407L;
/* 143 */     this.chunkSeed += p_75903_3_;
/* 144 */     this.chunkSeed *= this.chunkSeed * 6364136223846793005L + 1442695040888963407L;
/* 145 */     this.chunkSeed += p_75903_1_;
/* 146 */     this.chunkSeed *= this.chunkSeed * 6364136223846793005L + 1442695040888963407L;
/* 147 */     this.chunkSeed += p_75903_3_;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected int nextInt(int p_75902_1_) {
/* 155 */     int var2 = (int)((this.chunkSeed >> 24L) % p_75902_1_);
/*     */     
/* 157 */     if (var2 < 0)
/*     */     {
/* 159 */       var2 += p_75902_1_;
/*     */     }
/*     */     
/* 162 */     this.chunkSeed *= this.chunkSeed * 6364136223846793005L + 1442695040888963407L;
/* 163 */     this.chunkSeed += this.worldGenSeed;
/* 164 */     return var2;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public abstract int[] getInts(int paramInt1, int paramInt2, int paramInt3, int paramInt4);
/*     */ 
/*     */ 
/*     */   
/*     */   protected static boolean biomesEqualOrMesaPlateau(int biomeIDA, int biomeIDB) {
/* 175 */     if (biomeIDA == biomeIDB)
/*     */     {
/* 177 */       return true;
/*     */     }
/* 179 */     if (biomeIDA != BiomeGenBase.mesaPlateau_F.biomeID && biomeIDA != BiomeGenBase.mesaPlateau.biomeID) {
/*     */       
/* 181 */       final BiomeGenBase var2 = BiomeGenBase.getBiome(biomeIDA);
/* 182 */       final BiomeGenBase var3 = BiomeGenBase.getBiome(biomeIDB);
/*     */ 
/*     */       
/*     */       try {
/* 186 */         return (var2 != null && var3 != null) ? var2.isEqualTo(var3) : false;
/*     */       }
/* 188 */       catch (Throwable var7) {
/*     */         
/* 190 */         CrashReport var5 = CrashReport.makeCrashReport(var7, "Comparing biomes");
/* 191 */         CrashReportCategory var6 = var5.makeCategory("Biomes being compared");
/* 192 */         var6.addCrashSection("Biome A ID", Integer.valueOf(biomeIDA));
/* 193 */         var6.addCrashSection("Biome B ID", Integer.valueOf(biomeIDB));
/* 194 */         var6.addCrashSectionCallable("Biome A", new Callable()
/*     */             {
/*     */               private static final String __OBFID = "CL_00000560";
/*     */               
/*     */               public String call() {
/* 199 */                 return String.valueOf(var2);
/*     */               }
/*     */             });
/* 202 */         var6.addCrashSectionCallable("Biome B", new Callable()
/*     */             {
/*     */               private static final String __OBFID = "CL_00000561";
/*     */               
/*     */               public String call() {
/* 207 */                 return String.valueOf(var3);
/*     */               }
/*     */             });
/* 210 */         throw new ReportedException(var5);
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 215 */     return !(biomeIDB != BiomeGenBase.mesaPlateau_F.biomeID && biomeIDB != BiomeGenBase.mesaPlateau.biomeID);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected static boolean isBiomeOceanic(int p_151618_0_) {
/* 224 */     return !(p_151618_0_ != BiomeGenBase.ocean.biomeID && p_151618_0_ != BiomeGenBase.deepOcean.biomeID && p_151618_0_ != BiomeGenBase.frozenOcean.biomeID);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected int selectRandom(int... p_151619_1_) {
/* 232 */     return p_151619_1_[nextInt(p_151619_1_.length)];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected int selectModeOrRandom(int p_151617_1_, int p_151617_2_, int p_151617_3_, int p_151617_4_) {
/* 240 */     return (p_151617_2_ == p_151617_3_ && p_151617_3_ == p_151617_4_) ? p_151617_2_ : ((p_151617_1_ == p_151617_2_ && p_151617_1_ == p_151617_3_) ? p_151617_1_ : ((p_151617_1_ == p_151617_2_ && p_151617_1_ == p_151617_4_) ? p_151617_1_ : ((p_151617_1_ == p_151617_3_ && p_151617_1_ == p_151617_4_) ? p_151617_1_ : ((p_151617_1_ == p_151617_2_ && p_151617_3_ != p_151617_4_) ? p_151617_1_ : ((p_151617_1_ == p_151617_3_ && p_151617_2_ != p_151617_4_) ? p_151617_1_ : ((p_151617_1_ == p_151617_4_ && p_151617_2_ != p_151617_3_) ? p_151617_1_ : ((p_151617_2_ == p_151617_3_ && p_151617_1_ != p_151617_4_) ? p_151617_2_ : ((p_151617_2_ == p_151617_4_ && p_151617_1_ != p_151617_3_) ? p_151617_2_ : ((p_151617_3_ == p_151617_4_ && p_151617_1_ != p_151617_2_) ? p_151617_3_ : selectRandom(new int[] { p_151617_1_, p_151617_2_, p_151617_3_, p_151617_4_ }))))))))));
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\world\gen\layer\GenLayer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */