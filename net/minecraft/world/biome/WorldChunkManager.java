/*     */ package net.minecraft.world.biome;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import net.minecraft.crash.CrashReport;
/*     */ import net.minecraft.crash.CrashReportCategory;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.ReportedException;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.WorldType;
/*     */ import net.minecraft.world.gen.layer.GenLayer;
/*     */ import net.minecraft.world.gen.layer.IntCache;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class WorldChunkManager
/*     */ {
/*     */   private GenLayer genBiomes;
/*     */   private GenLayer biomeIndexLayer;
/*     */   private BiomeCache biomeCache;
/*     */   private List biomesToSpawnIn;
/*     */   private String field_180301_f;
/*     */   private static final String __OBFID = "CL_00000166";
/*     */   
/*     */   protected WorldChunkManager() {
/*  32 */     this.biomeCache = new BiomeCache(this);
/*  33 */     this.field_180301_f = "";
/*  34 */     this.biomesToSpawnIn = Lists.newArrayList();
/*  35 */     this.biomesToSpawnIn.add(BiomeGenBase.forest);
/*  36 */     this.biomesToSpawnIn.add(BiomeGenBase.plains);
/*  37 */     this.biomesToSpawnIn.add(BiomeGenBase.taiga);
/*  38 */     this.biomesToSpawnIn.add(BiomeGenBase.taigaHills);
/*  39 */     this.biomesToSpawnIn.add(BiomeGenBase.forestHills);
/*  40 */     this.biomesToSpawnIn.add(BiomeGenBase.jungle);
/*  41 */     this.biomesToSpawnIn.add(BiomeGenBase.jungleHills);
/*     */   }
/*     */ 
/*     */   
/*     */   public WorldChunkManager(long p_i45744_1_, WorldType p_i45744_3_, String p_i45744_4_) {
/*  46 */     this();
/*  47 */     this.field_180301_f = p_i45744_4_;
/*  48 */     GenLayer[] var5 = GenLayer.func_180781_a(p_i45744_1_, p_i45744_3_, p_i45744_4_);
/*  49 */     this.genBiomes = var5[0];
/*  50 */     this.biomeIndexLayer = var5[1];
/*     */   }
/*     */ 
/*     */   
/*     */   public WorldChunkManager(World worldIn) {
/*  55 */     this(worldIn.getSeed(), worldIn.getWorldInfo().getTerrainType(), worldIn.getWorldInfo().getGeneratorOptions());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List getBiomesToSpawnIn() {
/*  63 */     return this.biomesToSpawnIn;
/*     */   }
/*     */ 
/*     */   
/*     */   public BiomeGenBase func_180631_a(BlockPos p_180631_1_) {
/*  68 */     return func_180300_a(p_180631_1_, null);
/*     */   }
/*     */ 
/*     */   
/*     */   public BiomeGenBase func_180300_a(BlockPos p_180300_1_, BiomeGenBase p_180300_2_) {
/*  73 */     return this.biomeCache.func_180284_a(p_180300_1_.getX(), p_180300_1_.getZ(), p_180300_2_);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float[] getRainfall(float[] p_76936_1_, int p_76936_2_, int p_76936_3_, int p_76936_4_, int p_76936_5_) {
/*  81 */     IntCache.resetIntCache();
/*     */     
/*  83 */     if (p_76936_1_ == null || p_76936_1_.length < p_76936_4_ * p_76936_5_)
/*     */     {
/*  85 */       p_76936_1_ = new float[p_76936_4_ * p_76936_5_];
/*     */     }
/*     */     
/*  88 */     int[] var6 = this.biomeIndexLayer.getInts(p_76936_2_, p_76936_3_, p_76936_4_, p_76936_5_);
/*     */     
/*  90 */     for (int var7 = 0; var7 < p_76936_4_ * p_76936_5_; var7++) {
/*     */ 
/*     */       
/*     */       try {
/*  94 */         float var8 = BiomeGenBase.getBiomeFromBiomeList(var6[var7], BiomeGenBase.field_180279_ad).getIntRainfall() / 65536.0F;
/*     */         
/*  96 */         if (var8 > 1.0F)
/*     */         {
/*  98 */           var8 = 1.0F;
/*     */         }
/*     */         
/* 101 */         p_76936_1_[var7] = var8;
/*     */       }
/* 103 */       catch (Throwable var11) {
/*     */         
/* 105 */         CrashReport var9 = CrashReport.makeCrashReport(var11, "Invalid Biome id");
/* 106 */         CrashReportCategory var10 = var9.makeCategory("DownfallBlock");
/* 107 */         var10.addCrashSection("biome id", Integer.valueOf(var7));
/* 108 */         var10.addCrashSection("downfalls[] size", Integer.valueOf(p_76936_1_.length));
/* 109 */         var10.addCrashSection("x", Integer.valueOf(p_76936_2_));
/* 110 */         var10.addCrashSection("z", Integer.valueOf(p_76936_3_));
/* 111 */         var10.addCrashSection("w", Integer.valueOf(p_76936_4_));
/* 112 */         var10.addCrashSection("h", Integer.valueOf(p_76936_5_));
/* 113 */         throw new ReportedException(var9);
/*     */       } 
/*     */     } 
/*     */     
/* 117 */     return p_76936_1_;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getTemperatureAtHeight(float p_76939_1_, int p_76939_2_) {
/* 125 */     return p_76939_1_;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public BiomeGenBase[] getBiomesForGeneration(BiomeGenBase[] p_76937_1_, int p_76937_2_, int p_76937_3_, int p_76937_4_, int p_76937_5_) {
/* 133 */     IntCache.resetIntCache();
/*     */     
/* 135 */     if (p_76937_1_ == null || p_76937_1_.length < p_76937_4_ * p_76937_5_)
/*     */     {
/* 137 */       p_76937_1_ = new BiomeGenBase[p_76937_4_ * p_76937_5_];
/*     */     }
/*     */     
/* 140 */     int[] var6 = this.genBiomes.getInts(p_76937_2_, p_76937_3_, p_76937_4_, p_76937_5_);
/*     */ 
/*     */     
/*     */     try {
/* 144 */       for (int var7 = 0; var7 < p_76937_4_ * p_76937_5_; var7++)
/*     */       {
/* 146 */         p_76937_1_[var7] = BiomeGenBase.getBiomeFromBiomeList(var6[var7], BiomeGenBase.field_180279_ad);
/*     */       }
/*     */       
/* 149 */       return p_76937_1_;
/*     */     }
/* 151 */     catch (Throwable var10) {
/*     */       
/* 153 */       CrashReport var8 = CrashReport.makeCrashReport(var10, "Invalid Biome id");
/* 154 */       CrashReportCategory var9 = var8.makeCategory("RawBiomeBlock");
/* 155 */       var9.addCrashSection("biomes[] size", Integer.valueOf(p_76937_1_.length));
/* 156 */       var9.addCrashSection("x", Integer.valueOf(p_76937_2_));
/* 157 */       var9.addCrashSection("z", Integer.valueOf(p_76937_3_));
/* 158 */       var9.addCrashSection("w", Integer.valueOf(p_76937_4_));
/* 159 */       var9.addCrashSection("h", Integer.valueOf(p_76937_5_));
/* 160 */       throw new ReportedException(var8);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public BiomeGenBase[] loadBlockGeneratorData(BiomeGenBase[] p_76933_1_, int p_76933_2_, int p_76933_3_, int p_76933_4_, int p_76933_5_) {
/* 170 */     return getBiomeGenAt(p_76933_1_, p_76933_2_, p_76933_3_, p_76933_4_, p_76933_5_, true);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public BiomeGenBase[] getBiomeGenAt(BiomeGenBase[] p_76931_1_, int p_76931_2_, int p_76931_3_, int p_76931_4_, int p_76931_5_, boolean p_76931_6_) {
/* 179 */     IntCache.resetIntCache();
/*     */     
/* 181 */     if (p_76931_1_ == null || p_76931_1_.length < p_76931_4_ * p_76931_5_)
/*     */     {
/* 183 */       p_76931_1_ = new BiomeGenBase[p_76931_4_ * p_76931_5_];
/*     */     }
/*     */     
/* 186 */     if (p_76931_6_ && p_76931_4_ == 16 && p_76931_5_ == 16 && (p_76931_2_ & 0xF) == 0 && (p_76931_3_ & 0xF) == 0) {
/*     */       
/* 188 */       BiomeGenBase[] var9 = this.biomeCache.getCachedBiomes(p_76931_2_, p_76931_3_);
/* 189 */       System.arraycopy(var9, 0, p_76931_1_, 0, p_76931_4_ * p_76931_5_);
/* 190 */       return p_76931_1_;
/*     */     } 
/*     */ 
/*     */     
/* 194 */     int[] var7 = this.biomeIndexLayer.getInts(p_76931_2_, p_76931_3_, p_76931_4_, p_76931_5_);
/*     */     
/* 196 */     for (int var8 = 0; var8 < p_76931_4_ * p_76931_5_; var8++)
/*     */     {
/* 198 */       p_76931_1_[var8] = BiomeGenBase.getBiomeFromBiomeList(var7[var8], BiomeGenBase.field_180279_ad);
/*     */     }
/*     */     
/* 201 */     return p_76931_1_;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean areBiomesViable(int p_76940_1_, int p_76940_2_, int p_76940_3_, List p_76940_4_) {
/* 210 */     IntCache.resetIntCache();
/* 211 */     int var5 = p_76940_1_ - p_76940_3_ >> 2;
/* 212 */     int var6 = p_76940_2_ - p_76940_3_ >> 2;
/* 213 */     int var7 = p_76940_1_ + p_76940_3_ >> 2;
/* 214 */     int var8 = p_76940_2_ + p_76940_3_ >> 2;
/* 215 */     int var9 = var7 - var5 + 1;
/* 216 */     int var10 = var8 - var6 + 1;
/* 217 */     int[] var11 = this.genBiomes.getInts(var5, var6, var9, var10);
/*     */ 
/*     */     
/*     */     try {
/* 221 */       for (int var12 = 0; var12 < var9 * var10; var12++) {
/*     */         
/* 223 */         BiomeGenBase var16 = BiomeGenBase.getBiome(var11[var12]);
/*     */         
/* 225 */         if (!p_76940_4_.contains(var16))
/*     */         {
/* 227 */           return false;
/*     */         }
/*     */       } 
/*     */       
/* 231 */       return true;
/*     */     }
/* 233 */     catch (Throwable var15) {
/*     */       
/* 235 */       CrashReport var13 = CrashReport.makeCrashReport(var15, "Invalid Biome id");
/* 236 */       CrashReportCategory var14 = var13.makeCategory("Layer");
/* 237 */       var14.addCrashSection("Layer", this.genBiomes.toString());
/* 238 */       var14.addCrashSection("x", Integer.valueOf(p_76940_1_));
/* 239 */       var14.addCrashSection("z", Integer.valueOf(p_76940_2_));
/* 240 */       var14.addCrashSection("radius", Integer.valueOf(p_76940_3_));
/* 241 */       var14.addCrashSection("allowed", p_76940_4_);
/* 242 */       throw new ReportedException(var13);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public BlockPos findBiomePosition(int x, int z, int range, List biomes, Random random) {
/* 248 */     IntCache.resetIntCache();
/* 249 */     int var6 = x - range >> 2;
/* 250 */     int var7 = z - range >> 2;
/* 251 */     int var8 = x + range >> 2;
/* 252 */     int var9 = z + range >> 2;
/* 253 */     int var10 = var8 - var6 + 1;
/* 254 */     int var11 = var9 - var7 + 1;
/* 255 */     int[] var12 = this.genBiomes.getInts(var6, var7, var10, var11);
/* 256 */     BlockPos var13 = null;
/* 257 */     int var14 = 0;
/*     */     
/* 259 */     for (int var15 = 0; var15 < var10 * var11; var15++) {
/*     */       
/* 261 */       int var16 = var6 + var15 % var10 << 2;
/* 262 */       int var17 = var7 + var15 / var10 << 2;
/* 263 */       BiomeGenBase var18 = BiomeGenBase.getBiome(var12[var15]);
/*     */       
/* 265 */       if (biomes.contains(var18) && (var13 == null || random.nextInt(var14 + 1) == 0)) {
/*     */         
/* 267 */         var13 = new BlockPos(var16, 0, var17);
/* 268 */         var14++;
/*     */       } 
/*     */     } 
/*     */     
/* 272 */     return var13;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void cleanupCache() {
/* 280 */     this.biomeCache.cleanupCache();
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\world\biome\WorldChunkManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */