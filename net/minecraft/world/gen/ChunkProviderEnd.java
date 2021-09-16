/*     */ package net.minecraft.world.gen;
/*     */ 
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.BlockFalling;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.entity.EnumCreatureType;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.IProgressUpdate;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.biome.BiomeGenBase;
/*     */ import net.minecraft.world.chunk.Chunk;
/*     */ import net.minecraft.world.chunk.ChunkPrimer;
/*     */ import net.minecraft.world.chunk.IChunkProvider;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ChunkProviderEnd
/*     */   implements IChunkProvider
/*     */ {
/*     */   private Random endRNG;
/*     */   private NoiseGeneratorOctaves noiseGen1;
/*     */   private NoiseGeneratorOctaves noiseGen2;
/*     */   private NoiseGeneratorOctaves noiseGen3;
/*     */   public NoiseGeneratorOctaves noiseGen4;
/*     */   public NoiseGeneratorOctaves noiseGen5;
/*     */   private World endWorld;
/*     */   private double[] densities;
/*     */   private BiomeGenBase[] biomesForGeneration;
/*     */   double[] noiseData1;
/*     */   double[] noiseData2;
/*     */   double[] noiseData3;
/*     */   double[] noiseData4;
/*     */   double[] noiseData5;
/*     */   private static final String __OBFID = "CL_00000397";
/*     */   
/*     */   public ChunkProviderEnd(World worldIn, long p_i2007_2_) {
/*  41 */     this.endWorld = worldIn;
/*  42 */     this.endRNG = new Random(p_i2007_2_);
/*  43 */     this.noiseGen1 = new NoiseGeneratorOctaves(this.endRNG, 16);
/*  44 */     this.noiseGen2 = new NoiseGeneratorOctaves(this.endRNG, 16);
/*  45 */     this.noiseGen3 = new NoiseGeneratorOctaves(this.endRNG, 8);
/*  46 */     this.noiseGen4 = new NoiseGeneratorOctaves(this.endRNG, 10);
/*  47 */     this.noiseGen5 = new NoiseGeneratorOctaves(this.endRNG, 16);
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_180520_a(int p_180520_1_, int p_180520_2_, ChunkPrimer p_180520_3_) {
/*  52 */     byte var4 = 2;
/*  53 */     int var5 = var4 + 1;
/*  54 */     byte var6 = 33;
/*  55 */     int var7 = var4 + 1;
/*  56 */     this.densities = initializeNoiseField(this.densities, p_180520_1_ * var4, 0, p_180520_2_ * var4, var5, var6, var7);
/*     */     
/*  58 */     for (int var8 = 0; var8 < var4; var8++) {
/*     */       
/*  60 */       for (int var9 = 0; var9 < var4; var9++) {
/*     */         
/*  62 */         for (int var10 = 0; var10 < 32; var10++) {
/*     */           
/*  64 */           double var11 = 0.25D;
/*  65 */           double var13 = this.densities[((var8 + 0) * var7 + var9 + 0) * var6 + var10 + 0];
/*  66 */           double var15 = this.densities[((var8 + 0) * var7 + var9 + 1) * var6 + var10 + 0];
/*  67 */           double var17 = this.densities[((var8 + 1) * var7 + var9 + 0) * var6 + var10 + 0];
/*  68 */           double var19 = this.densities[((var8 + 1) * var7 + var9 + 1) * var6 + var10 + 0];
/*  69 */           double var21 = (this.densities[((var8 + 0) * var7 + var9 + 0) * var6 + var10 + 1] - var13) * var11;
/*  70 */           double var23 = (this.densities[((var8 + 0) * var7 + var9 + 1) * var6 + var10 + 1] - var15) * var11;
/*  71 */           double var25 = (this.densities[((var8 + 1) * var7 + var9 + 0) * var6 + var10 + 1] - var17) * var11;
/*  72 */           double var27 = (this.densities[((var8 + 1) * var7 + var9 + 1) * var6 + var10 + 1] - var19) * var11;
/*     */           
/*  74 */           for (int var29 = 0; var29 < 4; var29++) {
/*     */             
/*  76 */             double var30 = 0.125D;
/*  77 */             double var32 = var13;
/*  78 */             double var34 = var15;
/*  79 */             double var36 = (var17 - var13) * var30;
/*  80 */             double var38 = (var19 - var15) * var30;
/*     */             
/*  82 */             for (int var40 = 0; var40 < 8; var40++) {
/*     */               
/*  84 */               double var41 = 0.125D;
/*  85 */               double var43 = var32;
/*  86 */               double var45 = (var34 - var32) * var41;
/*     */               
/*  88 */               for (int var47 = 0; var47 < 8; var47++) {
/*     */                 
/*  90 */                 IBlockState var48 = null;
/*     */                 
/*  92 */                 if (var43 > 0.0D)
/*     */                 {
/*  94 */                   var48 = Blocks.end_stone.getDefaultState();
/*     */                 }
/*     */                 
/*  97 */                 int var49 = var40 + var8 * 8;
/*  98 */                 int var50 = var29 + var10 * 4;
/*  99 */                 int var51 = var47 + var9 * 8;
/* 100 */                 p_180520_3_.setBlockState(var49, var50, var51, var48);
/* 101 */                 var43 += var45;
/*     */               } 
/*     */               
/* 104 */               var32 += var36;
/* 105 */               var34 += var38;
/*     */             } 
/*     */             
/* 108 */             var13 += var21;
/* 109 */             var15 += var23;
/* 110 */             var17 += var25;
/* 111 */             var19 += var27;
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_180519_a(ChunkPrimer p_180519_1_) {
/* 120 */     for (int var2 = 0; var2 < 16; var2++) {
/*     */       
/* 122 */       for (int var3 = 0; var3 < 16; var3++) {
/*     */         
/* 124 */         byte var4 = 1;
/* 125 */         int var5 = -1;
/* 126 */         IBlockState var6 = Blocks.end_stone.getDefaultState();
/* 127 */         IBlockState var7 = Blocks.end_stone.getDefaultState();
/*     */         
/* 129 */         for (int var8 = 127; var8 >= 0; var8--) {
/*     */           
/* 131 */           IBlockState var9 = p_180519_1_.getBlockState(var2, var8, var3);
/*     */           
/* 133 */           if (var9.getBlock().getMaterial() == Material.air) {
/*     */             
/* 135 */             var5 = -1;
/*     */           }
/* 137 */           else if (var9.getBlock() == Blocks.stone) {
/*     */             
/* 139 */             if (var5 == -1) {
/*     */               
/* 141 */               if (var4 <= 0) {
/*     */                 
/* 143 */                 var6 = Blocks.air.getDefaultState();
/* 144 */                 var7 = Blocks.end_stone.getDefaultState();
/*     */               } 
/*     */               
/* 147 */               var5 = var4;
/*     */               
/* 149 */               if (var8 >= 0)
/*     */               {
/* 151 */                 p_180519_1_.setBlockState(var2, var8, var3, var6);
/*     */               }
/*     */               else
/*     */               {
/* 155 */                 p_180519_1_.setBlockState(var2, var8, var3, var7);
/*     */               }
/*     */             
/* 158 */             } else if (var5 > 0) {
/*     */               
/* 160 */               var5--;
/* 161 */               p_180519_1_.setBlockState(var2, var8, var3, var7);
/*     */             } 
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
/* 175 */     this.endRNG.setSeed(p_73154_1_ * 341873128712L + p_73154_2_ * 132897987541L);
/* 176 */     ChunkPrimer var3 = new ChunkPrimer();
/* 177 */     this.biomesForGeneration = this.endWorld.getWorldChunkManager().loadBlockGeneratorData(this.biomesForGeneration, p_73154_1_ * 16, p_73154_2_ * 16, 16, 16);
/* 178 */     func_180520_a(p_73154_1_, p_73154_2_, var3);
/* 179 */     func_180519_a(var3);
/* 180 */     Chunk var4 = new Chunk(this.endWorld, var3, p_73154_1_, p_73154_2_);
/* 181 */     byte[] var5 = var4.getBiomeArray();
/*     */     
/* 183 */     for (int var6 = 0; var6 < var5.length; var6++)
/*     */     {
/* 185 */       var5[var6] = (byte)(this.biomesForGeneration[var6]).biomeID;
/*     */     }
/*     */     
/* 188 */     var4.generateSkylightMap();
/* 189 */     return var4;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private double[] initializeNoiseField(double[] p_73187_1_, int p_73187_2_, int p_73187_3_, int p_73187_4_, int p_73187_5_, int p_73187_6_, int p_73187_7_) {
/* 198 */     if (p_73187_1_ == null)
/*     */     {
/* 200 */       p_73187_1_ = new double[p_73187_5_ * p_73187_6_ * p_73187_7_];
/*     */     }
/*     */     
/* 203 */     double var8 = 684.412D;
/* 204 */     double var10 = 684.412D;
/* 205 */     this.noiseData4 = this.noiseGen4.generateNoiseOctaves(this.noiseData4, p_73187_2_, p_73187_4_, p_73187_5_, p_73187_7_, 1.121D, 1.121D, 0.5D);
/* 206 */     this.noiseData5 = this.noiseGen5.generateNoiseOctaves(this.noiseData5, p_73187_2_, p_73187_4_, p_73187_5_, p_73187_7_, 200.0D, 200.0D, 0.5D);
/* 207 */     var8 *= 2.0D;
/* 208 */     this.noiseData1 = this.noiseGen3.generateNoiseOctaves(this.noiseData1, p_73187_2_, p_73187_3_, p_73187_4_, p_73187_5_, p_73187_6_, p_73187_7_, var8 / 80.0D, var10 / 160.0D, var8 / 80.0D);
/* 209 */     this.noiseData2 = this.noiseGen1.generateNoiseOctaves(this.noiseData2, p_73187_2_, p_73187_3_, p_73187_4_, p_73187_5_, p_73187_6_, p_73187_7_, var8, var10, var8);
/* 210 */     this.noiseData3 = this.noiseGen2.generateNoiseOctaves(this.noiseData3, p_73187_2_, p_73187_3_, p_73187_4_, p_73187_5_, p_73187_6_, p_73187_7_, var8, var10, var8);
/* 211 */     int var12 = 0;
/*     */     
/* 213 */     for (int var13 = 0; var13 < p_73187_5_; var13++) {
/*     */       
/* 215 */       for (int var14 = 0; var14 < p_73187_7_; var14++) {
/*     */         
/* 217 */         float var15 = (var13 + p_73187_2_) / 1.0F;
/* 218 */         float var16 = (var14 + p_73187_4_) / 1.0F;
/* 219 */         float var17 = 100.0F - MathHelper.sqrt_float(var15 * var15 + var16 * var16) * 8.0F;
/*     */         
/* 221 */         if (var17 > 80.0F)
/*     */         {
/* 223 */           var17 = 80.0F;
/*     */         }
/*     */         
/* 226 */         if (var17 < -100.0F)
/*     */         {
/* 228 */           var17 = -100.0F;
/*     */         }
/*     */         
/* 231 */         for (int var18 = 0; var18 < p_73187_6_; var18++) {
/*     */           
/* 233 */           double var19 = 0.0D;
/* 234 */           double var21 = this.noiseData2[var12] / 512.0D;
/* 235 */           double var23 = this.noiseData3[var12] / 512.0D;
/* 236 */           double var25 = (this.noiseData1[var12] / 10.0D + 1.0D) / 2.0D;
/*     */           
/* 238 */           if (var25 < 0.0D) {
/*     */             
/* 240 */             var19 = var21;
/*     */           }
/* 242 */           else if (var25 > 1.0D) {
/*     */             
/* 244 */             var19 = var23;
/*     */           }
/*     */           else {
/*     */             
/* 248 */             var19 = var21 + (var23 - var21) * var25;
/*     */           } 
/*     */           
/* 251 */           var19 -= 8.0D;
/* 252 */           var19 += var17;
/* 253 */           byte var27 = 2;
/*     */ 
/*     */           
/* 256 */           if (var18 > p_73187_6_ / 2 - var27) {
/*     */             
/* 258 */             double var28 = ((var18 - p_73187_6_ / 2 - var27) / 64.0F);
/* 259 */             var28 = MathHelper.clamp_double(var28, 0.0D, 1.0D);
/* 260 */             var19 = var19 * (1.0D - var28) + -3000.0D * var28;
/*     */           } 
/*     */           
/* 263 */           var27 = 8;
/*     */           
/* 265 */           if (var18 < var27) {
/*     */             
/* 267 */             double var28 = ((var27 - var18) / (var27 - 1.0F));
/* 268 */             var19 = var19 * (1.0D - var28) + -30.0D * var28;
/*     */           } 
/*     */           
/* 271 */           p_73187_1_[var12] = var19;
/* 272 */           var12++;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 277 */     return p_73187_1_;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean chunkExists(int p_73149_1_, int p_73149_2_) {
/* 285 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void populate(IChunkProvider p_73153_1_, int p_73153_2_, int p_73153_3_) {
/* 293 */     BlockFalling.fallInstantly = true;
/* 294 */     BlockPos var4 = new BlockPos(p_73153_2_ * 16, 0, p_73153_3_ * 16);
/* 295 */     this.endWorld.getBiomeGenForCoords(var4.add(16, 0, 16)).func_180624_a(this.endWorld, this.endWorld.rand, var4);
/* 296 */     BlockFalling.fallInstantly = false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_177460_a(IChunkProvider p_177460_1_, Chunk p_177460_2_, int p_177460_3_, int p_177460_4_) {
/* 301 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean saveChunks(boolean p_73151_1_, IProgressUpdate p_73151_2_) {
/* 310 */     return true;
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
/* 324 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canSave() {
/* 332 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String makeString() {
/* 340 */     return "RandomLevelSource";
/*     */   }
/*     */ 
/*     */   
/*     */   public List func_177458_a(EnumCreatureType p_177458_1_, BlockPos p_177458_2_) {
/* 345 */     return this.endWorld.getBiomeGenForCoords(p_177458_2_).getSpawnableList(p_177458_1_);
/*     */   }
/*     */ 
/*     */   
/*     */   public BlockPos func_180513_a(World worldIn, String p_180513_2_, BlockPos p_180513_3_) {
/* 350 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getLoadedChunkCount() {
/* 355 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_180514_a(Chunk p_180514_1_, int p_180514_2_, int p_180514_3_) {}
/*     */   
/*     */   public Chunk func_177459_a(BlockPos p_177459_1_) {
/* 362 */     return provideChunk(p_177459_1_.getX() >> 4, p_177459_1_.getZ() >> 4);
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\world\gen\ChunkProviderEnd.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */