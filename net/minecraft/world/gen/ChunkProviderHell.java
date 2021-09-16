/*     */ package net.minecraft.world.gen;
/*     */ 
/*     */ import com.google.common.base.Predicate;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.BlockFalling;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.block.state.pattern.BlockHelper;
/*     */ import net.minecraft.entity.EnumCreatureType;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.IProgressUpdate;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.world.ChunkCoordIntPair;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.biome.BiomeGenBase;
/*     */ import net.minecraft.world.chunk.Chunk;
/*     */ import net.minecraft.world.chunk.ChunkPrimer;
/*     */ import net.minecraft.world.chunk.IChunkProvider;
/*     */ import net.minecraft.world.gen.feature.WorldGenFire;
/*     */ import net.minecraft.world.gen.feature.WorldGenGlowStone1;
/*     */ import net.minecraft.world.gen.feature.WorldGenGlowStone2;
/*     */ import net.minecraft.world.gen.feature.WorldGenHellLava;
/*     */ import net.minecraft.world.gen.feature.WorldGenMinable;
/*     */ import net.minecraft.world.gen.feature.WorldGenerator;
/*     */ import net.minecraft.world.gen.structure.MapGenNetherBridge;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ChunkProviderHell
/*     */   implements IChunkProvider
/*     */ {
/*     */   private final World worldObj;
/*     */   private final boolean field_177466_i;
/*     */   private final Random hellRNG;
/*  38 */   private double[] slowsandNoise = new double[256];
/*  39 */   private double[] gravelNoise = new double[256];
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  44 */   private double[] netherrackExclusivityNoise = new double[256];
/*     */   
/*     */   private double[] noiseField;
/*     */   
/*     */   private final NoiseGeneratorOctaves netherNoiseGen1;
/*     */   
/*     */   private final NoiseGeneratorOctaves netherNoiseGen2;
/*     */   
/*     */   private final NoiseGeneratorOctaves netherNoiseGen3;
/*     */   
/*     */   private final NoiseGeneratorOctaves slowsandGravelNoiseGen;
/*     */   
/*     */   private final NoiseGeneratorOctaves netherrackExculsivityNoiseGen;
/*     */   
/*     */   public final NoiseGeneratorOctaves netherNoiseGen6;
/*     */   
/*     */   public final NoiseGeneratorOctaves netherNoiseGen7;
/*  61 */   private final WorldGenFire field_177470_t = new WorldGenFire();
/*  62 */   private final WorldGenGlowStone1 field_177469_u = new WorldGenGlowStone1();
/*  63 */   private final WorldGenGlowStone2 field_177468_v = new WorldGenGlowStone2();
/*     */   
/*     */   private final WorldGenerator field_177467_w;
/*     */   private final WorldGenHellLava field_177473_x;
/*     */   private final WorldGenHellLava field_177472_y;
/*     */   private final GeneratorBushFeature field_177471_z;
/*     */   private final GeneratorBushFeature field_177465_A;
/*     */   private final MapGenNetherBridge genNetherBridge;
/*     */   private final MapGenBase netherCaveGenerator;
/*     */   double[] noiseData1;
/*     */   double[] noiseData2;
/*     */   double[] noiseData3;
/*     */   double[] noiseData4;
/*     */   double[] noiseData5;
/*     */   private static final String __OBFID = "CL_00000392";
/*     */   
/*     */   public ChunkProviderHell(World worldIn, boolean p_i45637_2_, long p_i45637_3_) {
/*  80 */     this.field_177467_w = (WorldGenerator)new WorldGenMinable(Blocks.quartz_ore.getDefaultState(), 14, (Predicate)BlockHelper.forBlock(Blocks.netherrack));
/*  81 */     this.field_177473_x = new WorldGenHellLava((Block)Blocks.flowing_lava, true);
/*  82 */     this.field_177472_y = new WorldGenHellLava((Block)Blocks.flowing_lava, false);
/*  83 */     this.field_177471_z = new GeneratorBushFeature(Blocks.brown_mushroom);
/*  84 */     this.field_177465_A = new GeneratorBushFeature(Blocks.red_mushroom);
/*  85 */     this.genNetherBridge = new MapGenNetherBridge();
/*  86 */     this.netherCaveGenerator = new MapGenCavesHell();
/*  87 */     this.worldObj = worldIn;
/*  88 */     this.field_177466_i = p_i45637_2_;
/*  89 */     this.hellRNG = new Random(p_i45637_3_);
/*  90 */     this.netherNoiseGen1 = new NoiseGeneratorOctaves(this.hellRNG, 16);
/*  91 */     this.netherNoiseGen2 = new NoiseGeneratorOctaves(this.hellRNG, 16);
/*  92 */     this.netherNoiseGen3 = new NoiseGeneratorOctaves(this.hellRNG, 8);
/*  93 */     this.slowsandGravelNoiseGen = new NoiseGeneratorOctaves(this.hellRNG, 4);
/*  94 */     this.netherrackExculsivityNoiseGen = new NoiseGeneratorOctaves(this.hellRNG, 4);
/*  95 */     this.netherNoiseGen6 = new NoiseGeneratorOctaves(this.hellRNG, 10);
/*  96 */     this.netherNoiseGen7 = new NoiseGeneratorOctaves(this.hellRNG, 16);
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_180515_a(int p_180515_1_, int p_180515_2_, ChunkPrimer p_180515_3_) {
/* 101 */     byte var4 = 4;
/* 102 */     byte var5 = 32;
/* 103 */     int var6 = var4 + 1;
/* 104 */     byte var7 = 17;
/* 105 */     int var8 = var4 + 1;
/* 106 */     this.noiseField = initializeNoiseField(this.noiseField, p_180515_1_ * var4, 0, p_180515_2_ * var4, var6, var7, var8);
/*     */     
/* 108 */     for (int var9 = 0; var9 < var4; var9++) {
/*     */       
/* 110 */       for (int var10 = 0; var10 < var4; var10++) {
/*     */         
/* 112 */         for (int var11 = 0; var11 < 16; var11++) {
/*     */           
/* 114 */           double var12 = 0.125D;
/* 115 */           double var14 = this.noiseField[((var9 + 0) * var8 + var10 + 0) * var7 + var11 + 0];
/* 116 */           double var16 = this.noiseField[((var9 + 0) * var8 + var10 + 1) * var7 + var11 + 0];
/* 117 */           double var18 = this.noiseField[((var9 + 1) * var8 + var10 + 0) * var7 + var11 + 0];
/* 118 */           double var20 = this.noiseField[((var9 + 1) * var8 + var10 + 1) * var7 + var11 + 0];
/* 119 */           double var22 = (this.noiseField[((var9 + 0) * var8 + var10 + 0) * var7 + var11 + 1] - var14) * var12;
/* 120 */           double var24 = (this.noiseField[((var9 + 0) * var8 + var10 + 1) * var7 + var11 + 1] - var16) * var12;
/* 121 */           double var26 = (this.noiseField[((var9 + 1) * var8 + var10 + 0) * var7 + var11 + 1] - var18) * var12;
/* 122 */           double var28 = (this.noiseField[((var9 + 1) * var8 + var10 + 1) * var7 + var11 + 1] - var20) * var12;
/*     */           
/* 124 */           for (int var30 = 0; var30 < 8; var30++) {
/*     */             
/* 126 */             double var31 = 0.25D;
/* 127 */             double var33 = var14;
/* 128 */             double var35 = var16;
/* 129 */             double var37 = (var18 - var14) * var31;
/* 130 */             double var39 = (var20 - var16) * var31;
/*     */             
/* 132 */             for (int var41 = 0; var41 < 4; var41++) {
/*     */               
/* 134 */               double var42 = 0.25D;
/* 135 */               double var44 = var33;
/* 136 */               double var46 = (var35 - var33) * var42;
/*     */               
/* 138 */               for (int var48 = 0; var48 < 4; var48++) {
/*     */                 
/* 140 */                 IBlockState var49 = null;
/*     */                 
/* 142 */                 if (var11 * 8 + var30 < var5)
/*     */                 {
/* 144 */                   var49 = Blocks.lava.getDefaultState();
/*     */                 }
/*     */                 
/* 147 */                 if (var44 > 0.0D)
/*     */                 {
/* 149 */                   var49 = Blocks.netherrack.getDefaultState();
/*     */                 }
/*     */                 
/* 152 */                 int var50 = var41 + var9 * 4;
/* 153 */                 int var51 = var30 + var11 * 8;
/* 154 */                 int var52 = var48 + var10 * 4;
/* 155 */                 p_180515_3_.setBlockState(var50, var51, var52, var49);
/* 156 */                 var44 += var46;
/*     */               } 
/*     */               
/* 159 */               var33 += var37;
/* 160 */               var35 += var39;
/*     */             } 
/*     */             
/* 163 */             var14 += var22;
/* 164 */             var16 += var24;
/* 165 */             var18 += var26;
/* 166 */             var20 += var28;
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_180516_b(int p_180516_1_, int p_180516_2_, ChunkPrimer p_180516_3_) {
/* 175 */     byte var4 = 64;
/* 176 */     double var5 = 0.03125D;
/* 177 */     this.slowsandNoise = this.slowsandGravelNoiseGen.generateNoiseOctaves(this.slowsandNoise, p_180516_1_ * 16, p_180516_2_ * 16, 0, 16, 16, 1, var5, var5, 1.0D);
/* 178 */     this.gravelNoise = this.slowsandGravelNoiseGen.generateNoiseOctaves(this.gravelNoise, p_180516_1_ * 16, 109, p_180516_2_ * 16, 16, 1, 16, var5, 1.0D, var5);
/* 179 */     this.netherrackExclusivityNoise = this.netherrackExculsivityNoiseGen.generateNoiseOctaves(this.netherrackExclusivityNoise, p_180516_1_ * 16, p_180516_2_ * 16, 0, 16, 16, 1, var5 * 2.0D, var5 * 2.0D, var5 * 2.0D);
/*     */     
/* 181 */     for (int var7 = 0; var7 < 16; var7++) {
/*     */       
/* 183 */       for (int var8 = 0; var8 < 16; var8++) {
/*     */         
/* 185 */         boolean var9 = (this.slowsandNoise[var7 + var8 * 16] + this.hellRNG.nextDouble() * 0.2D > 0.0D);
/* 186 */         boolean var10 = (this.gravelNoise[var7 + var8 * 16] + this.hellRNG.nextDouble() * 0.2D > 0.0D);
/* 187 */         int var11 = (int)(this.netherrackExclusivityNoise[var7 + var8 * 16] / 3.0D + 3.0D + this.hellRNG.nextDouble() * 0.25D);
/* 188 */         int var12 = -1;
/* 189 */         IBlockState var13 = Blocks.netherrack.getDefaultState();
/* 190 */         IBlockState var14 = Blocks.netherrack.getDefaultState();
/*     */         
/* 192 */         for (int var15 = 127; var15 >= 0; var15--) {
/*     */           
/* 194 */           if (var15 < 127 - this.hellRNG.nextInt(5) && var15 > this.hellRNG.nextInt(5)) {
/*     */             
/* 196 */             IBlockState var16 = p_180516_3_.getBlockState(var8, var15, var7);
/*     */             
/* 198 */             if (var16.getBlock() != null && var16.getBlock().getMaterial() != Material.air) {
/*     */               
/* 200 */               if (var16.getBlock() == Blocks.netherrack)
/*     */               {
/* 202 */                 if (var12 == -1) {
/*     */                   
/* 204 */                   if (var11 <= 0) {
/*     */                     
/* 206 */                     var13 = null;
/* 207 */                     var14 = Blocks.netherrack.getDefaultState();
/*     */                   }
/* 209 */                   else if (var15 >= var4 - 4 && var15 <= var4 + 1) {
/*     */                     
/* 211 */                     var13 = Blocks.netherrack.getDefaultState();
/* 212 */                     var14 = Blocks.netherrack.getDefaultState();
/*     */                     
/* 214 */                     if (var10) {
/*     */                       
/* 216 */                       var13 = Blocks.gravel.getDefaultState();
/* 217 */                       var14 = Blocks.netherrack.getDefaultState();
/*     */                     } 
/*     */                     
/* 220 */                     if (var9) {
/*     */                       
/* 222 */                       var13 = Blocks.soul_sand.getDefaultState();
/* 223 */                       var14 = Blocks.soul_sand.getDefaultState();
/*     */                     } 
/*     */                   } 
/*     */                   
/* 227 */                   if (var15 < var4 && (var13 == null || var13.getBlock().getMaterial() == Material.air))
/*     */                   {
/* 229 */                     var13 = Blocks.lava.getDefaultState();
/*     */                   }
/*     */                   
/* 232 */                   var12 = var11;
/*     */                   
/* 234 */                   if (var15 >= var4 - 1)
/*     */                   {
/* 236 */                     p_180516_3_.setBlockState(var8, var15, var7, var13);
/*     */                   }
/*     */                   else
/*     */                   {
/* 240 */                     p_180516_3_.setBlockState(var8, var15, var7, var14);
/*     */                   }
/*     */                 
/* 243 */                 } else if (var12 > 0) {
/*     */                   
/* 245 */                   var12--;
/* 246 */                   p_180516_3_.setBlockState(var8, var15, var7, var14);
/*     */                 }
/*     */               
/*     */               }
/*     */             } else {
/*     */               
/* 252 */               var12 = -1;
/*     */             }
/*     */           
/*     */           } else {
/*     */             
/* 257 */             p_180516_3_.setBlockState(var8, var15, var7, Blocks.bedrock.getDefaultState());
/*     */           } 
/*     */         } 
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
/* 270 */     this.hellRNG.setSeed(p_73154_1_ * 341873128712L + p_73154_2_ * 132897987541L);
/* 271 */     ChunkPrimer var3 = new ChunkPrimer();
/* 272 */     func_180515_a(p_73154_1_, p_73154_2_, var3);
/* 273 */     func_180516_b(p_73154_1_, p_73154_2_, var3);
/* 274 */     this.netherCaveGenerator.func_175792_a(this, this.worldObj, p_73154_1_, p_73154_2_, var3);
/*     */     
/* 276 */     if (this.field_177466_i)
/*     */     {
/* 278 */       this.genNetherBridge.func_175792_a(this, this.worldObj, p_73154_1_, p_73154_2_, var3);
/*     */     }
/*     */     
/* 281 */     Chunk var4 = new Chunk(this.worldObj, var3, p_73154_1_, p_73154_2_);
/* 282 */     BiomeGenBase[] var5 = this.worldObj.getWorldChunkManager().loadBlockGeneratorData(null, p_73154_1_ * 16, p_73154_2_ * 16, 16, 16);
/* 283 */     byte[] var6 = var4.getBiomeArray();
/*     */     
/* 285 */     for (int var7 = 0; var7 < var6.length; var7++)
/*     */     {
/* 287 */       var6[var7] = (byte)(var5[var7]).biomeID;
/*     */     }
/*     */     
/* 290 */     var4.resetRelightChecks();
/* 291 */     return var4;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private double[] initializeNoiseField(double[] p_73164_1_, int p_73164_2_, int p_73164_3_, int p_73164_4_, int p_73164_5_, int p_73164_6_, int p_73164_7_) {
/* 300 */     if (p_73164_1_ == null)
/*     */     {
/* 302 */       p_73164_1_ = new double[p_73164_5_ * p_73164_6_ * p_73164_7_];
/*     */     }
/*     */     
/* 305 */     double var8 = 684.412D;
/* 306 */     double var10 = 2053.236D;
/* 307 */     this.noiseData4 = this.netherNoiseGen6.generateNoiseOctaves(this.noiseData4, p_73164_2_, p_73164_3_, p_73164_4_, p_73164_5_, 1, p_73164_7_, 1.0D, 0.0D, 1.0D);
/* 308 */     this.noiseData5 = this.netherNoiseGen7.generateNoiseOctaves(this.noiseData5, p_73164_2_, p_73164_3_, p_73164_4_, p_73164_5_, 1, p_73164_7_, 100.0D, 0.0D, 100.0D);
/* 309 */     this.noiseData1 = this.netherNoiseGen3.generateNoiseOctaves(this.noiseData1, p_73164_2_, p_73164_3_, p_73164_4_, p_73164_5_, p_73164_6_, p_73164_7_, var8 / 80.0D, var10 / 60.0D, var8 / 80.0D);
/* 310 */     this.noiseData2 = this.netherNoiseGen1.generateNoiseOctaves(this.noiseData2, p_73164_2_, p_73164_3_, p_73164_4_, p_73164_5_, p_73164_6_, p_73164_7_, var8, var10, var8);
/* 311 */     this.noiseData3 = this.netherNoiseGen2.generateNoiseOctaves(this.noiseData3, p_73164_2_, p_73164_3_, p_73164_4_, p_73164_5_, p_73164_6_, p_73164_7_, var8, var10, var8);
/* 312 */     int var12 = 0;
/* 313 */     double[] var13 = new double[p_73164_6_];
/*     */     
/*     */     int var14;
/* 316 */     for (var14 = 0; var14 < p_73164_6_; var14++) {
/*     */       
/* 318 */       var13[var14] = Math.cos(var14 * Math.PI * 6.0D / p_73164_6_) * 2.0D;
/* 319 */       double var15 = var14;
/*     */       
/* 321 */       if (var14 > p_73164_6_ / 2)
/*     */       {
/* 323 */         var15 = (p_73164_6_ - 1 - var14);
/*     */       }
/*     */       
/* 326 */       if (var15 < 4.0D) {
/*     */         
/* 328 */         var15 = 4.0D - var15;
/* 329 */         var13[var14] = var13[var14] - var15 * var15 * var15 * 10.0D;
/*     */       } 
/*     */     } 
/*     */     
/* 333 */     for (var14 = 0; var14 < p_73164_5_; var14++) {
/*     */       
/* 335 */       for (int var31 = 0; var31 < p_73164_7_; var31++) {
/*     */         
/* 337 */         double var16 = 0.0D;
/*     */         
/* 339 */         for (int var18 = 0; var18 < p_73164_6_; var18++) {
/*     */           
/* 341 */           double var19 = 0.0D;
/* 342 */           double var21 = var13[var18];
/* 343 */           double var23 = this.noiseData2[var12] / 512.0D;
/* 344 */           double var25 = this.noiseData3[var12] / 512.0D;
/* 345 */           double var27 = (this.noiseData1[var12] / 10.0D + 1.0D) / 2.0D;
/*     */           
/* 347 */           if (var27 < 0.0D) {
/*     */             
/* 349 */             var19 = var23;
/*     */           }
/* 351 */           else if (var27 > 1.0D) {
/*     */             
/* 353 */             var19 = var25;
/*     */           }
/*     */           else {
/*     */             
/* 357 */             var19 = var23 + (var25 - var23) * var27;
/*     */           } 
/*     */           
/* 360 */           var19 -= var21;
/*     */ 
/*     */           
/* 363 */           if (var18 > p_73164_6_ - 4) {
/*     */             
/* 365 */             double var29 = ((var18 - p_73164_6_ - 4) / 3.0F);
/* 366 */             var19 = var19 * (1.0D - var29) + -10.0D * var29;
/*     */           } 
/*     */           
/* 369 */           if (var18 < var16) {
/*     */             
/* 371 */             double var29 = (var16 - var18) / 4.0D;
/* 372 */             var29 = MathHelper.clamp_double(var29, 0.0D, 1.0D);
/* 373 */             var19 = var19 * (1.0D - var29) + -10.0D * var29;
/*     */           } 
/*     */           
/* 376 */           p_73164_1_[var12] = var19;
/* 377 */           var12++;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 382 */     return p_73164_1_;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean chunkExists(int p_73149_1_, int p_73149_2_) {
/* 390 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void populate(IChunkProvider p_73153_1_, int p_73153_2_, int p_73153_3_) {
/* 398 */     BlockFalling.fallInstantly = true;
/* 399 */     BlockPos var4 = new BlockPos(p_73153_2_ * 16, 0, p_73153_3_ * 16);
/* 400 */     ChunkCoordIntPair var5 = new ChunkCoordIntPair(p_73153_2_, p_73153_3_);
/* 401 */     this.genNetherBridge.func_175794_a(this.worldObj, this.hellRNG, var5);
/*     */     
/*     */     int var6;
/* 404 */     for (var6 = 0; var6 < 8; var6++)
/*     */     {
/* 406 */       this.field_177472_y.generate(this.worldObj, this.hellRNG, var4.add(this.hellRNG.nextInt(16) + 8, this.hellRNG.nextInt(120) + 4, this.hellRNG.nextInt(16) + 8));
/*     */     }
/*     */     
/* 409 */     for (var6 = 0; var6 < this.hellRNG.nextInt(this.hellRNG.nextInt(10) + 1) + 1; var6++)
/*     */     {
/* 411 */       this.field_177470_t.generate(this.worldObj, this.hellRNG, var4.add(this.hellRNG.nextInt(16) + 8, this.hellRNG.nextInt(120) + 4, this.hellRNG.nextInt(16) + 8));
/*     */     }
/*     */     
/* 414 */     for (var6 = 0; var6 < this.hellRNG.nextInt(this.hellRNG.nextInt(10) + 1); var6++)
/*     */     {
/* 416 */       this.field_177469_u.generate(this.worldObj, this.hellRNG, var4.add(this.hellRNG.nextInt(16) + 8, this.hellRNG.nextInt(120) + 4, this.hellRNG.nextInt(16) + 8));
/*     */     }
/*     */     
/* 419 */     for (var6 = 0; var6 < 10; var6++)
/*     */     {
/* 421 */       this.field_177468_v.generate(this.worldObj, this.hellRNG, var4.add(this.hellRNG.nextInt(16) + 8, this.hellRNG.nextInt(128), this.hellRNG.nextInt(16) + 8));
/*     */     }
/*     */     
/* 424 */     if (this.hellRNG.nextBoolean())
/*     */     {
/* 426 */       this.field_177471_z.generate(this.worldObj, this.hellRNG, var4.add(this.hellRNG.nextInt(16) + 8, this.hellRNG.nextInt(128), this.hellRNG.nextInt(16) + 8));
/*     */     }
/*     */     
/* 429 */     if (this.hellRNG.nextBoolean())
/*     */     {
/* 431 */       this.field_177465_A.generate(this.worldObj, this.hellRNG, var4.add(this.hellRNG.nextInt(16) + 8, this.hellRNG.nextInt(128), this.hellRNG.nextInt(16) + 8));
/*     */     }
/*     */     
/* 434 */     for (var6 = 0; var6 < 16; var6++)
/*     */     {
/* 436 */       this.field_177467_w.generate(this.worldObj, this.hellRNG, var4.add(this.hellRNG.nextInt(16), this.hellRNG.nextInt(108) + 10, this.hellRNG.nextInt(16)));
/*     */     }
/*     */     
/* 439 */     for (var6 = 0; var6 < 16; var6++)
/*     */     {
/* 441 */       this.field_177473_x.generate(this.worldObj, this.hellRNG, var4.add(this.hellRNG.nextInt(16), this.hellRNG.nextInt(108) + 10, this.hellRNG.nextInt(16)));
/*     */     }
/*     */     
/* 444 */     BlockFalling.fallInstantly = false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_177460_a(IChunkProvider p_177460_1_, Chunk p_177460_2_, int p_177460_3_, int p_177460_4_) {
/* 449 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean saveChunks(boolean p_73151_1_, IProgressUpdate p_73151_2_) {
/* 458 */     return true;
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
/* 472 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canSave() {
/* 480 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String makeString() {
/* 488 */     return "HellRandomLevelSource";
/*     */   }
/*     */ 
/*     */   
/*     */   public List func_177458_a(EnumCreatureType p_177458_1_, BlockPos p_177458_2_) {
/* 493 */     if (p_177458_1_ == EnumCreatureType.MONSTER) {
/*     */       
/* 495 */       if (this.genNetherBridge.func_175795_b(p_177458_2_))
/*     */       {
/* 497 */         return this.genNetherBridge.getSpawnList();
/*     */       }
/*     */       
/* 500 */       if (this.genNetherBridge.func_175796_a(this.worldObj, p_177458_2_) && this.worldObj.getBlockState(p_177458_2_.offsetDown()).getBlock() == Blocks.nether_brick)
/*     */       {
/* 502 */         return this.genNetherBridge.getSpawnList();
/*     */       }
/*     */     } 
/*     */     
/* 506 */     BiomeGenBase var3 = this.worldObj.getBiomeGenForCoords(p_177458_2_);
/* 507 */     return var3.getSpawnableList(p_177458_1_);
/*     */   }
/*     */ 
/*     */   
/*     */   public BlockPos func_180513_a(World worldIn, String p_180513_2_, BlockPos p_180513_3_) {
/* 512 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getLoadedChunkCount() {
/* 517 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_180514_a(Chunk p_180514_1_, int p_180514_2_, int p_180514_3_) {
/* 522 */     this.genNetherBridge.func_175792_a(this, this.worldObj, p_180514_2_, p_180514_3_, null);
/*     */   }
/*     */ 
/*     */   
/*     */   public Chunk func_177459_a(BlockPos p_177459_1_) {
/* 527 */     return provideChunk(p_177459_1_.getX() >> 4, p_177459_1_.getZ() >> 4);
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\world\gen\ChunkProviderHell.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */