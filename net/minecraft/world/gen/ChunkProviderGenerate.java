/*     */ package net.minecraft.world.gen;
/*     */ 
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.BlockFalling;
/*     */ import net.minecraft.entity.EnumCreatureType;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.IProgressUpdate;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.world.ChunkCoordIntPair;
/*     */ import net.minecraft.world.SpawnerAnimals;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.WorldType;
/*     */ import net.minecraft.world.biome.BiomeGenBase;
/*     */ import net.minecraft.world.chunk.Chunk;
/*     */ import net.minecraft.world.chunk.ChunkPrimer;
/*     */ import net.minecraft.world.chunk.IChunkProvider;
/*     */ import net.minecraft.world.gen.feature.WorldGenDungeons;
/*     */ import net.minecraft.world.gen.feature.WorldGenLakes;
/*     */ import net.minecraft.world.gen.structure.MapGenMineshaft;
/*     */ import net.minecraft.world.gen.structure.MapGenScatteredFeature;
/*     */ import net.minecraft.world.gen.structure.MapGenStronghold;
/*     */ import net.minecraft.world.gen.structure.MapGenVillage;
/*     */ import net.minecraft.world.gen.structure.StructureOceanMonument;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ChunkProviderGenerate
/*     */   implements IChunkProvider
/*     */ {
/*     */   private Random rand;
/*     */   private NoiseGeneratorOctaves field_147431_j;
/*     */   private NoiseGeneratorOctaves field_147432_k;
/*     */   private NoiseGeneratorOctaves field_147429_l;
/*     */   private NoiseGeneratorPerlin field_147430_m;
/*     */   public NoiseGeneratorOctaves noiseGen5;
/*     */   public NoiseGeneratorOctaves noiseGen6;
/*     */   public NoiseGeneratorOctaves mobSpawnerNoise;
/*     */   private World worldObj;
/*     */   private final boolean mapFeaturesEnabled;
/*     */   private WorldType field_177475_o;
/*     */   private final double[] field_147434_q;
/*     */   private final float[] parabolicField;
/*     */   private ChunkProviderSettings field_177477_r;
/*     */   private Block field_177476_s;
/*     */   private double[] stoneNoise;
/*     */   private MapGenBase caveGenerator;
/*     */   private MapGenStronghold strongholdGenerator;
/*     */   private MapGenVillage villageGenerator;
/*     */   private MapGenMineshaft mineshaftGenerator;
/*     */   private MapGenScatteredFeature scatteredFeatureGenerator;
/*     */   private MapGenBase ravineGenerator;
/*     */   private StructureOceanMonument field_177474_A;
/*     */   private BiomeGenBase[] biomesForGeneration;
/*     */   double[] field_147427_d;
/*     */   double[] field_147428_e;
/*     */   double[] field_147425_f;
/*     */   double[] field_147426_g;
/*     */   private static final String __OBFID = "CL_00000396";
/*     */   
/*     */   public ChunkProviderGenerate(World worldIn, long p_i45636_2_, boolean p_i45636_4_, String p_i45636_5_) {
/*  81 */     this.field_177476_s = (Block)Blocks.water;
/*  82 */     this.stoneNoise = new double[256];
/*  83 */     this.caveGenerator = new MapGenCaves();
/*  84 */     this.strongholdGenerator = new MapGenStronghold();
/*  85 */     this.villageGenerator = new MapGenVillage();
/*  86 */     this.mineshaftGenerator = new MapGenMineshaft();
/*  87 */     this.scatteredFeatureGenerator = new MapGenScatteredFeature();
/*  88 */     this.ravineGenerator = new MapGenRavine();
/*  89 */     this.field_177474_A = new StructureOceanMonument();
/*  90 */     this.worldObj = worldIn;
/*  91 */     this.mapFeaturesEnabled = p_i45636_4_;
/*  92 */     this.field_177475_o = worldIn.getWorldInfo().getTerrainType();
/*  93 */     this.rand = new Random(p_i45636_2_);
/*  94 */     this.field_147431_j = new NoiseGeneratorOctaves(this.rand, 16);
/*  95 */     this.field_147432_k = new NoiseGeneratorOctaves(this.rand, 16);
/*  96 */     this.field_147429_l = new NoiseGeneratorOctaves(this.rand, 8);
/*  97 */     this.field_147430_m = new NoiseGeneratorPerlin(this.rand, 4);
/*  98 */     this.noiseGen5 = new NoiseGeneratorOctaves(this.rand, 10);
/*  99 */     this.noiseGen6 = new NoiseGeneratorOctaves(this.rand, 16);
/* 100 */     this.mobSpawnerNoise = new NoiseGeneratorOctaves(this.rand, 8);
/* 101 */     this.field_147434_q = new double[825];
/* 102 */     this.parabolicField = new float[25];
/*     */     
/* 104 */     for (int var6 = -2; var6 <= 2; var6++) {
/*     */       
/* 106 */       for (int var7 = -2; var7 <= 2; var7++) {
/*     */         
/* 108 */         float var8 = 10.0F / MathHelper.sqrt_float((var6 * var6 + var7 * var7) + 0.2F);
/* 109 */         this.parabolicField[var6 + 2 + (var7 + 2) * 5] = var8;
/*     */       } 
/*     */     } 
/*     */     
/* 113 */     if (p_i45636_5_ != null) {
/*     */       
/* 115 */       this.field_177477_r = ChunkProviderSettings.Factory.func_177865_a(p_i45636_5_).func_177864_b();
/* 116 */       this.field_177476_s = this.field_177477_r.field_177778_E ? (Block)Blocks.lava : (Block)Blocks.water;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_180518_a(int p_180518_1_, int p_180518_2_, ChunkPrimer p_180518_3_) {
/* 122 */     this.biomesForGeneration = this.worldObj.getWorldChunkManager().getBiomesForGeneration(this.biomesForGeneration, p_180518_1_ * 4 - 2, p_180518_2_ * 4 - 2, 10, 10);
/* 123 */     func_147423_a(p_180518_1_ * 4, 0, p_180518_2_ * 4);
/*     */     
/* 125 */     for (int var4 = 0; var4 < 4; var4++) {
/*     */       
/* 127 */       int var5 = var4 * 5;
/* 128 */       int var6 = (var4 + 1) * 5;
/*     */       
/* 130 */       for (int var7 = 0; var7 < 4; var7++) {
/*     */         
/* 132 */         int var8 = (var5 + var7) * 33;
/* 133 */         int var9 = (var5 + var7 + 1) * 33;
/* 134 */         int var10 = (var6 + var7) * 33;
/* 135 */         int var11 = (var6 + var7 + 1) * 33;
/*     */         
/* 137 */         for (int var12 = 0; var12 < 32; var12++) {
/*     */           
/* 139 */           double var13 = 0.125D;
/* 140 */           double var15 = this.field_147434_q[var8 + var12];
/* 141 */           double var17 = this.field_147434_q[var9 + var12];
/* 142 */           double var19 = this.field_147434_q[var10 + var12];
/* 143 */           double var21 = this.field_147434_q[var11 + var12];
/* 144 */           double var23 = (this.field_147434_q[var8 + var12 + 1] - var15) * var13;
/* 145 */           double var25 = (this.field_147434_q[var9 + var12 + 1] - var17) * var13;
/* 146 */           double var27 = (this.field_147434_q[var10 + var12 + 1] - var19) * var13;
/* 147 */           double var29 = (this.field_147434_q[var11 + var12 + 1] - var21) * var13;
/*     */           
/* 149 */           for (int var31 = 0; var31 < 8; var31++) {
/*     */             
/* 151 */             double var32 = 0.25D;
/* 152 */             double var34 = var15;
/* 153 */             double var36 = var17;
/* 154 */             double var38 = (var19 - var15) * var32;
/* 155 */             double var40 = (var21 - var17) * var32;
/*     */             
/* 157 */             for (int var42 = 0; var42 < 4; var42++) {
/*     */               
/* 159 */               double var43 = 0.25D;
/* 160 */               double var47 = (var36 - var34) * var43;
/* 161 */               double var45 = var34 - var47;
/*     */               
/* 163 */               for (int var49 = 0; var49 < 4; var49++) {
/*     */                 
/* 165 */                 if ((var45 += var47) > 0.0D) {
/*     */                   
/* 167 */                   p_180518_3_.setBlockState(var4 * 4 + var42, var12 * 8 + var31, var7 * 4 + var49, Blocks.stone.getDefaultState());
/*     */                 }
/* 169 */                 else if (var12 * 8 + var31 < this.field_177477_r.field_177841_q) {
/*     */                   
/* 171 */                   p_180518_3_.setBlockState(var4 * 4 + var42, var12 * 8 + var31, var7 * 4 + var49, this.field_177476_s.getDefaultState());
/*     */                 } 
/*     */               } 
/*     */               
/* 175 */               var34 += var38;
/* 176 */               var36 += var40;
/*     */             } 
/*     */             
/* 179 */             var15 += var23;
/* 180 */             var17 += var25;
/* 181 */             var19 += var27;
/* 182 */             var21 += var29;
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_180517_a(int p_180517_1_, int p_180517_2_, ChunkPrimer p_180517_3_, BiomeGenBase[] p_180517_4_) {
/* 191 */     double var5 = 0.03125D;
/* 192 */     this.stoneNoise = this.field_147430_m.func_151599_a(this.stoneNoise, (p_180517_1_ * 16), (p_180517_2_ * 16), 16, 16, var5 * 2.0D, var5 * 2.0D, 1.0D);
/*     */     
/* 194 */     for (int var7 = 0; var7 < 16; var7++) {
/*     */       
/* 196 */       for (int var8 = 0; var8 < 16; var8++) {
/*     */         
/* 198 */         BiomeGenBase var9 = p_180517_4_[var8 + var7 * 16];
/* 199 */         var9.genTerrainBlocks(this.worldObj, this.rand, p_180517_3_, p_180517_1_ * 16 + var7, p_180517_2_ * 16 + var8, this.stoneNoise[var8 + var7 * 16]);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Chunk provideChunk(int p_73154_1_, int p_73154_2_) {
/* 210 */     this.rand.setSeed(p_73154_1_ * 341873128712L + p_73154_2_ * 132897987541L);
/* 211 */     ChunkPrimer var3 = new ChunkPrimer();
/* 212 */     func_180518_a(p_73154_1_, p_73154_2_, var3);
/* 213 */     this.biomesForGeneration = this.worldObj.getWorldChunkManager().loadBlockGeneratorData(this.biomesForGeneration, p_73154_1_ * 16, p_73154_2_ * 16, 16, 16);
/* 214 */     func_180517_a(p_73154_1_, p_73154_2_, var3, this.biomesForGeneration);
/*     */     
/* 216 */     if (this.field_177477_r.field_177839_r)
/*     */     {
/* 218 */       this.caveGenerator.func_175792_a(this, this.worldObj, p_73154_1_, p_73154_2_, var3);
/*     */     }
/*     */     
/* 221 */     if (this.field_177477_r.field_177850_z)
/*     */     {
/* 223 */       this.ravineGenerator.func_175792_a(this, this.worldObj, p_73154_1_, p_73154_2_, var3);
/*     */     }
/*     */     
/* 226 */     if (this.field_177477_r.field_177829_w && this.mapFeaturesEnabled)
/*     */     {
/* 228 */       this.mineshaftGenerator.func_175792_a(this, this.worldObj, p_73154_1_, p_73154_2_, var3);
/*     */     }
/*     */     
/* 231 */     if (this.field_177477_r.field_177831_v && this.mapFeaturesEnabled)
/*     */     {
/* 233 */       this.villageGenerator.func_175792_a(this, this.worldObj, p_73154_1_, p_73154_2_, var3);
/*     */     }
/*     */     
/* 236 */     if (this.field_177477_r.field_177833_u && this.mapFeaturesEnabled)
/*     */     {
/* 238 */       this.strongholdGenerator.func_175792_a(this, this.worldObj, p_73154_1_, p_73154_2_, var3);
/*     */     }
/*     */     
/* 241 */     if (this.field_177477_r.field_177854_x && this.mapFeaturesEnabled)
/*     */     {
/* 243 */       this.scatteredFeatureGenerator.func_175792_a(this, this.worldObj, p_73154_1_, p_73154_2_, var3);
/*     */     }
/*     */     
/* 246 */     if (this.field_177477_r.field_177852_y && this.mapFeaturesEnabled)
/*     */     {
/* 248 */       this.field_177474_A.func_175792_a(this, this.worldObj, p_73154_1_, p_73154_2_, var3);
/*     */     }
/*     */     
/* 251 */     Chunk var4 = new Chunk(this.worldObj, var3, p_73154_1_, p_73154_2_);
/* 252 */     byte[] var5 = var4.getBiomeArray();
/*     */     
/* 254 */     for (int var6 = 0; var6 < var5.length; var6++)
/*     */     {
/* 256 */       var5[var6] = (byte)(this.biomesForGeneration[var6]).biomeID;
/*     */     }
/*     */     
/* 259 */     var4.generateSkylightMap();
/* 260 */     return var4;
/*     */   }
/*     */ 
/*     */   
/*     */   private void func_147423_a(int p_147423_1_, int p_147423_2_, int p_147423_3_) {
/* 265 */     this.field_147426_g = this.noiseGen6.generateNoiseOctaves(this.field_147426_g, p_147423_1_, p_147423_3_, 5, 5, this.field_177477_r.field_177808_e, this.field_177477_r.field_177803_f, this.field_177477_r.field_177804_g);
/* 266 */     float var4 = this.field_177477_r.field_177811_a;
/* 267 */     float var5 = this.field_177477_r.field_177809_b;
/* 268 */     this.field_147427_d = this.field_147429_l.generateNoiseOctaves(this.field_147427_d, p_147423_1_, p_147423_2_, p_147423_3_, 5, 33, 5, (var4 / this.field_177477_r.field_177825_h), (var5 / this.field_177477_r.field_177827_i), (var4 / this.field_177477_r.field_177821_j));
/* 269 */     this.field_147428_e = this.field_147431_j.generateNoiseOctaves(this.field_147428_e, p_147423_1_, p_147423_2_, p_147423_3_, 5, 33, 5, var4, var5, var4);
/* 270 */     this.field_147425_f = this.field_147432_k.generateNoiseOctaves(this.field_147425_f, p_147423_1_, p_147423_2_, p_147423_3_, 5, 33, 5, var4, var5, var4);
/* 271 */     boolean var37 = false;
/* 272 */     boolean var36 = false;
/* 273 */     int var6 = 0;
/* 274 */     int var7 = 0;
/*     */     
/* 276 */     for (int var8 = 0; var8 < 5; var8++) {
/*     */       
/* 278 */       for (int var9 = 0; var9 < 5; var9++) {
/*     */         
/* 280 */         float var10 = 0.0F;
/* 281 */         float var11 = 0.0F;
/* 282 */         float var12 = 0.0F;
/* 283 */         byte var13 = 2;
/* 284 */         BiomeGenBase var14 = this.biomesForGeneration[var8 + 2 + (var9 + 2) * 10];
/*     */         
/* 286 */         for (int var15 = -var13; var15 <= var13; var15++) {
/*     */           
/* 288 */           for (int var16 = -var13; var16 <= var13; var16++) {
/*     */             
/* 290 */             BiomeGenBase var17 = this.biomesForGeneration[var8 + var15 + 2 + (var9 + var16 + 2) * 10];
/* 291 */             float var18 = this.field_177477_r.field_177813_n + var17.minHeight * this.field_177477_r.field_177819_m;
/* 292 */             float var19 = this.field_177477_r.field_177843_p + var17.maxHeight * this.field_177477_r.field_177815_o;
/*     */             
/* 294 */             if (this.field_177475_o == WorldType.AMPLIFIED && var18 > 0.0F) {
/*     */               
/* 296 */               var18 = 1.0F + var18 * 2.0F;
/* 297 */               var19 = 1.0F + var19 * 4.0F;
/*     */             } 
/*     */             
/* 300 */             float var20 = this.parabolicField[var15 + 2 + (var16 + 2) * 5] / (var18 + 2.0F);
/*     */             
/* 302 */             if (var17.minHeight > var14.minHeight)
/*     */             {
/* 304 */               var20 /= 2.0F;
/*     */             }
/*     */             
/* 307 */             var10 += var19 * var20;
/* 308 */             var11 += var18 * var20;
/* 309 */             var12 += var20;
/*     */           } 
/*     */         } 
/*     */         
/* 313 */         var10 /= var12;
/* 314 */         var11 /= var12;
/* 315 */         var10 = var10 * 0.9F + 0.1F;
/* 316 */         var11 = (var11 * 4.0F - 1.0F) / 8.0F;
/* 317 */         double var38 = this.field_147426_g[var7] / 8000.0D;
/*     */         
/* 319 */         if (var38 < 0.0D)
/*     */         {
/* 321 */           var38 = -var38 * 0.3D;
/*     */         }
/*     */         
/* 324 */         var38 = var38 * 3.0D - 2.0D;
/*     */         
/* 326 */         if (var38 < 0.0D) {
/*     */           
/* 328 */           var38 /= 2.0D;
/*     */           
/* 330 */           if (var38 < -1.0D)
/*     */           {
/* 332 */             var38 = -1.0D;
/*     */           }
/*     */           
/* 335 */           var38 /= 1.4D;
/* 336 */           var38 /= 2.0D;
/*     */         }
/*     */         else {
/*     */           
/* 340 */           if (var38 > 1.0D)
/*     */           {
/* 342 */             var38 = 1.0D;
/*     */           }
/*     */           
/* 345 */           var38 /= 8.0D;
/*     */         } 
/*     */         
/* 348 */         var7++;
/* 349 */         double var39 = var11;
/* 350 */         double var40 = var10;
/* 351 */         var39 += var38 * 0.2D;
/* 352 */         var39 = var39 * this.field_177477_r.field_177823_k / 8.0D;
/* 353 */         double var21 = this.field_177477_r.field_177823_k + var39 * 4.0D;
/*     */         
/* 355 */         for (int var23 = 0; var23 < 33; var23++) {
/*     */           
/* 357 */           double var24 = (var23 - var21) * this.field_177477_r.field_177817_l * 128.0D / 256.0D / var40;
/*     */           
/* 359 */           if (var24 < 0.0D)
/*     */           {
/* 361 */             var24 *= 4.0D;
/*     */           }
/*     */           
/* 364 */           double var26 = this.field_147428_e[var6] / this.field_177477_r.field_177806_d;
/* 365 */           double var28 = this.field_147425_f[var6] / this.field_177477_r.field_177810_c;
/* 366 */           double var30 = (this.field_147427_d[var6] / 10.0D + 1.0D) / 2.0D;
/* 367 */           double var32 = MathHelper.denormalizeClamp(var26, var28, var30) - var24;
/*     */           
/* 369 */           if (var23 > 29) {
/*     */             
/* 371 */             double var34 = ((var23 - 29) / 3.0F);
/* 372 */             var32 = var32 * (1.0D - var34) + -10.0D * var34;
/*     */           } 
/*     */           
/* 375 */           this.field_147434_q[var6] = var32;
/* 376 */           var6++;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean chunkExists(int p_73149_1_, int p_73149_2_) {
/* 387 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void populate(IChunkProvider p_73153_1_, int p_73153_2_, int p_73153_3_) {
/* 395 */     BlockFalling.fallInstantly = true;
/* 396 */     int var4 = p_73153_2_ * 16;
/* 397 */     int var5 = p_73153_3_ * 16;
/* 398 */     BlockPos var6 = new BlockPos(var4, 0, var5);
/* 399 */     BiomeGenBase var7 = this.worldObj.getBiomeGenForCoords(var6.add(16, 0, 16));
/* 400 */     this.rand.setSeed(this.worldObj.getSeed());
/* 401 */     long var8 = this.rand.nextLong() / 2L * 2L + 1L;
/* 402 */     long var10 = this.rand.nextLong() / 2L * 2L + 1L;
/* 403 */     this.rand.setSeed(p_73153_2_ * var8 + p_73153_3_ * var10 ^ this.worldObj.getSeed());
/* 404 */     boolean var12 = false;
/* 405 */     ChunkCoordIntPair var13 = new ChunkCoordIntPair(p_73153_2_, p_73153_3_);
/*     */     
/* 407 */     if (this.field_177477_r.field_177829_w && this.mapFeaturesEnabled)
/*     */     {
/* 409 */       this.mineshaftGenerator.func_175794_a(this.worldObj, this.rand, var13);
/*     */     }
/*     */     
/* 412 */     if (this.field_177477_r.field_177831_v && this.mapFeaturesEnabled)
/*     */     {
/* 414 */       var12 = this.villageGenerator.func_175794_a(this.worldObj, this.rand, var13);
/*     */     }
/*     */     
/* 417 */     if (this.field_177477_r.field_177833_u && this.mapFeaturesEnabled)
/*     */     {
/* 419 */       this.strongholdGenerator.func_175794_a(this.worldObj, this.rand, var13);
/*     */     }
/*     */     
/* 422 */     if (this.field_177477_r.field_177854_x && this.mapFeaturesEnabled)
/*     */     {
/* 424 */       this.scatteredFeatureGenerator.func_175794_a(this.worldObj, this.rand, var13);
/*     */     }
/*     */     
/* 427 */     if (this.field_177477_r.field_177852_y && this.mapFeaturesEnabled)
/*     */     {
/* 429 */       this.field_177474_A.func_175794_a(this.worldObj, this.rand, var13);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 436 */     if (var7 != BiomeGenBase.desert && var7 != BiomeGenBase.desertHills && this.field_177477_r.field_177781_A && !var12 && this.rand.nextInt(this.field_177477_r.field_177782_B) == 0) {
/*     */       
/* 438 */       int i = this.rand.nextInt(16) + 8;
/* 439 */       int var15 = this.rand.nextInt(256);
/* 440 */       int var16 = this.rand.nextInt(16) + 8;
/* 441 */       (new WorldGenLakes((Block)Blocks.water)).generate(this.worldObj, this.rand, var6.add(i, var15, var16));
/*     */     } 
/*     */     
/* 444 */     if (!var12 && this.rand.nextInt(this.field_177477_r.field_177777_D / 10) == 0 && this.field_177477_r.field_177783_C) {
/*     */       
/* 446 */       int i = this.rand.nextInt(16) + 8;
/* 447 */       int var15 = this.rand.nextInt(this.rand.nextInt(248) + 8);
/* 448 */       int var16 = this.rand.nextInt(16) + 8;
/*     */       
/* 450 */       if (var15 < 63 || this.rand.nextInt(this.field_177477_r.field_177777_D / 8) == 0)
/*     */       {
/* 452 */         (new WorldGenLakes((Block)Blocks.lava)).generate(this.worldObj, this.rand, var6.add(i, var15, var16));
/*     */       }
/*     */     } 
/*     */     
/* 456 */     if (this.field_177477_r.field_177837_s)
/*     */     {
/* 458 */       for (int i = 0; i < this.field_177477_r.field_177835_t; i++) {
/*     */         
/* 460 */         int var15 = this.rand.nextInt(16) + 8;
/* 461 */         int var16 = this.rand.nextInt(256);
/* 462 */         int var17 = this.rand.nextInt(16) + 8;
/* 463 */         (new WorldGenDungeons()).generate(this.worldObj, this.rand, var6.add(var15, var16, var17));
/*     */       } 
/*     */     }
/*     */     
/* 467 */     var7.func_180624_a(this.worldObj, this.rand, new BlockPos(var4, 0, var5));
/* 468 */     SpawnerAnimals.performWorldGenSpawning(this.worldObj, var7, var4 + 8, var5 + 8, 16, 16, this.rand);
/* 469 */     var6 = var6.add(8, 0, 8);
/*     */     
/* 471 */     for (int var14 = 0; var14 < 16; var14++) {
/*     */       
/* 473 */       for (int var15 = 0; var15 < 16; var15++) {
/*     */         
/* 475 */         BlockPos var18 = this.worldObj.func_175725_q(var6.add(var14, 0, var15));
/* 476 */         BlockPos var19 = var18.offsetDown();
/*     */         
/* 478 */         if (this.worldObj.func_175675_v(var19))
/*     */         {
/* 480 */           this.worldObj.setBlockState(var19, Blocks.ice.getDefaultState(), 2);
/*     */         }
/*     */         
/* 483 */         if (this.worldObj.func_175708_f(var18, true))
/*     */         {
/* 485 */           this.worldObj.setBlockState(var18, Blocks.snow_layer.getDefaultState(), 2);
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 490 */     BlockFalling.fallInstantly = false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_177460_a(IChunkProvider p_177460_1_, Chunk p_177460_2_, int p_177460_3_, int p_177460_4_) {
/* 495 */     boolean var5 = false;
/*     */     
/* 497 */     if (this.field_177477_r.field_177852_y && this.mapFeaturesEnabled && p_177460_2_.getInhabitedTime() < 3600L)
/*     */     {
/* 499 */       var5 |= this.field_177474_A.func_175794_a(this.worldObj, this.rand, new ChunkCoordIntPair(p_177460_3_, p_177460_4_));
/*     */     }
/*     */     
/* 502 */     return var5;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean saveChunks(boolean p_73151_1_, IProgressUpdate p_73151_2_) {
/* 511 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void saveExtraData() {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean unloadQueuedChunks() {
/* 525 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canSave() {
/* 533 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String makeString() {
/* 541 */     return "RandomLevelSource";
/*     */   }
/*     */ 
/*     */   
/*     */   public List func_177458_a(EnumCreatureType p_177458_1_, BlockPos p_177458_2_) {
/* 546 */     BiomeGenBase var3 = this.worldObj.getBiomeGenForCoords(p_177458_2_);
/*     */     
/* 548 */     if (this.mapFeaturesEnabled) {
/*     */       
/* 550 */       if (p_177458_1_ == EnumCreatureType.MONSTER && this.scatteredFeatureGenerator.func_175798_a(p_177458_2_))
/*     */       {
/* 552 */         return this.scatteredFeatureGenerator.getScatteredFeatureSpawnList();
/*     */       }
/*     */       
/* 555 */       if (p_177458_1_ == EnumCreatureType.MONSTER && this.field_177477_r.field_177852_y && this.field_177474_A.func_175796_a(this.worldObj, p_177458_2_))
/*     */       {
/* 557 */         return this.field_177474_A.func_175799_b();
/*     */       }
/*     */     } 
/*     */     
/* 561 */     return var3.getSpawnableList(p_177458_1_);
/*     */   }
/*     */ 
/*     */   
/*     */   public BlockPos func_180513_a(World worldIn, String p_180513_2_, BlockPos p_180513_3_) {
/* 566 */     return ("Stronghold".equals(p_180513_2_) && this.strongholdGenerator != null) ? this.strongholdGenerator.func_180706_b(worldIn, p_180513_3_) : null;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getLoadedChunkCount() {
/* 571 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_180514_a(Chunk p_180514_1_, int p_180514_2_, int p_180514_3_) {
/* 576 */     if (this.field_177477_r.field_177829_w && this.mapFeaturesEnabled)
/*     */     {
/* 578 */       this.mineshaftGenerator.func_175792_a(this, this.worldObj, p_180514_2_, p_180514_3_, null);
/*     */     }
/*     */     
/* 581 */     if (this.field_177477_r.field_177831_v && this.mapFeaturesEnabled)
/*     */     {
/* 583 */       this.villageGenerator.func_175792_a(this, this.worldObj, p_180514_2_, p_180514_3_, null);
/*     */     }
/*     */     
/* 586 */     if (this.field_177477_r.field_177833_u && this.mapFeaturesEnabled)
/*     */     {
/* 588 */       this.strongholdGenerator.func_175792_a(this, this.worldObj, p_180514_2_, p_180514_3_, null);
/*     */     }
/*     */     
/* 591 */     if (this.field_177477_r.field_177854_x && this.mapFeaturesEnabled)
/*     */     {
/* 593 */       this.scatteredFeatureGenerator.func_175792_a(this, this.worldObj, p_180514_2_, p_180514_3_, null);
/*     */     }
/*     */     
/* 596 */     if (this.field_177477_r.field_177852_y && this.mapFeaturesEnabled)
/*     */     {
/* 598 */       this.field_177474_A.func_175792_a(this, this.worldObj, p_180514_2_, p_180514_3_, null);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public Chunk func_177459_a(BlockPos p_177459_1_) {
/* 604 */     return provideChunk(p_177459_1_.getX() >> 4, p_177459_1_.getZ() >> 4);
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\world\gen\ChunkProviderGenerate.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */