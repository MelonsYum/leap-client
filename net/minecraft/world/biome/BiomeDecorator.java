/*     */ package net.minecraft.world.biome;
/*     */ 
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.BlockFlower;
/*     */ import net.minecraft.block.BlockStone;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.block.properties.IProperty;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.gen.ChunkProviderSettings;
/*     */ import net.minecraft.world.gen.GeneratorBushFeature;
/*     */ import net.minecraft.world.gen.feature.WorldGenAbstractTree;
/*     */ import net.minecraft.world.gen.feature.WorldGenBigMushroom;
/*     */ import net.minecraft.world.gen.feature.WorldGenCactus;
/*     */ import net.minecraft.world.gen.feature.WorldGenClay;
/*     */ import net.minecraft.world.gen.feature.WorldGenDeadBush;
/*     */ import net.minecraft.world.gen.feature.WorldGenFlowers;
/*     */ import net.minecraft.world.gen.feature.WorldGenLiquids;
/*     */ import net.minecraft.world.gen.feature.WorldGenMinable;
/*     */ import net.minecraft.world.gen.feature.WorldGenPumpkin;
/*     */ import net.minecraft.world.gen.feature.WorldGenReed;
/*     */ import net.minecraft.world.gen.feature.WorldGenSand;
/*     */ import net.minecraft.world.gen.feature.WorldGenWaterlily;
/*     */ import net.minecraft.world.gen.feature.WorldGenerator;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BiomeDecorator
/*     */ {
/*     */   protected World currentWorld;
/*     */   protected Random randomGenerator;
/*     */   protected BlockPos field_180294_c;
/*     */   protected ChunkProviderSettings field_180293_d;
/*  37 */   protected WorldGenerator clayGen = (WorldGenerator)new WorldGenClay(4);
/*     */ 
/*     */   
/*     */   protected WorldGenerator sandGen;
/*     */ 
/*     */   
/*     */   protected WorldGenerator gravelAsSandGen;
/*     */ 
/*     */   
/*     */   protected WorldGenerator dirtGen;
/*     */ 
/*     */   
/*     */   protected WorldGenerator gravelGen;
/*     */ 
/*     */   
/*     */   protected WorldGenerator field_180296_j;
/*     */ 
/*     */   
/*     */   protected WorldGenerator field_180297_k;
/*     */ 
/*     */   
/*     */   protected WorldGenerator field_180295_l;
/*     */ 
/*     */   
/*     */   protected WorldGenerator coalGen;
/*     */ 
/*     */   
/*     */   protected WorldGenerator ironGen;
/*     */ 
/*     */   
/*     */   protected WorldGenerator goldGen;
/*     */ 
/*     */   
/*     */   protected WorldGenerator field_180299_p;
/*     */ 
/*     */   
/*     */   protected WorldGenerator field_180298_q;
/*     */ 
/*     */   
/*     */   protected WorldGenerator lapisGen;
/*     */ 
/*     */   
/*     */   protected WorldGenFlowers yellowFlowerGen;
/*     */ 
/*     */   
/*     */   protected WorldGenerator mushroomBrownGen;
/*     */ 
/*     */   
/*     */   protected WorldGenerator mushroomRedGen;
/*     */ 
/*     */   
/*     */   protected WorldGenerator bigMushroomGen;
/*     */ 
/*     */   
/*     */   protected WorldGenerator reedGen;
/*     */ 
/*     */   
/*     */   protected WorldGenerator cactusGen;
/*     */ 
/*     */   
/*     */   protected WorldGenerator waterlilyGen;
/*     */ 
/*     */   
/*     */   protected int waterlilyPerChunk;
/*     */ 
/*     */   
/*     */   protected int treesPerChunk;
/*     */ 
/*     */   
/*     */   protected int flowersPerChunk;
/*     */ 
/*     */   
/*     */   protected int grassPerChunk;
/*     */ 
/*     */   
/*     */   protected int deadBushPerChunk;
/*     */ 
/*     */   
/*     */   protected int mushroomsPerChunk;
/*     */ 
/*     */   
/*     */   protected int reedsPerChunk;
/*     */ 
/*     */   
/*     */   protected int cactiPerChunk;
/*     */ 
/*     */   
/*     */   protected int sandPerChunk;
/*     */ 
/*     */   
/*     */   protected int sandPerChunk2;
/*     */ 
/*     */   
/*     */   protected int clayPerChunk;
/*     */ 
/*     */   
/*     */   protected int bigMushroomsPerChunk;
/*     */ 
/*     */   
/*     */   public boolean generateLakes;
/*     */ 
/*     */   
/*     */   private static final String __OBFID = "CL_00000164";
/*     */ 
/*     */ 
/*     */   
/*     */   public BiomeDecorator() {
/* 144 */     this.sandGen = (WorldGenerator)new WorldGenSand((Block)Blocks.sand, 7);
/* 145 */     this.gravelAsSandGen = (WorldGenerator)new WorldGenSand(Blocks.gravel, 6);
/* 146 */     this.yellowFlowerGen = new WorldGenFlowers(Blocks.yellow_flower, BlockFlower.EnumFlowerType.DANDELION);
/* 147 */     this.mushroomBrownGen = (WorldGenerator)new GeneratorBushFeature(Blocks.brown_mushroom);
/* 148 */     this.mushroomRedGen = (WorldGenerator)new GeneratorBushFeature(Blocks.red_mushroom);
/* 149 */     this.bigMushroomGen = (WorldGenerator)new WorldGenBigMushroom();
/* 150 */     this.reedGen = (WorldGenerator)new WorldGenReed();
/* 151 */     this.cactusGen = (WorldGenerator)new WorldGenCactus();
/* 152 */     this.waterlilyGen = (WorldGenerator)new WorldGenWaterlily();
/* 153 */     this.flowersPerChunk = 2;
/* 154 */     this.grassPerChunk = 1;
/* 155 */     this.sandPerChunk = 1;
/* 156 */     this.sandPerChunk2 = 3;
/* 157 */     this.clayPerChunk = 1;
/* 158 */     this.generateLakes = true;
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_180292_a(World worldIn, Random p_180292_2_, BiomeGenBase p_180292_3_, BlockPos p_180292_4_) {
/* 163 */     if (this.currentWorld != null)
/*     */     {
/* 165 */       throw new RuntimeException("Already decorating");
/*     */     }
/*     */ 
/*     */     
/* 169 */     this.currentWorld = worldIn;
/* 170 */     String var5 = worldIn.getWorldInfo().getGeneratorOptions();
/*     */     
/* 172 */     if (var5 != null) {
/*     */       
/* 174 */       this.field_180293_d = ChunkProviderSettings.Factory.func_177865_a(var5).func_177864_b();
/*     */     }
/*     */     else {
/*     */       
/* 178 */       this.field_180293_d = ChunkProviderSettings.Factory.func_177865_a("").func_177864_b();
/*     */     } 
/*     */     
/* 181 */     this.randomGenerator = p_180292_2_;
/* 182 */     this.field_180294_c = p_180292_4_;
/* 183 */     this.dirtGen = (WorldGenerator)new WorldGenMinable(Blocks.dirt.getDefaultState(), this.field_180293_d.field_177789_I);
/* 184 */     this.gravelGen = (WorldGenerator)new WorldGenMinable(Blocks.gravel.getDefaultState(), this.field_180293_d.field_177785_M);
/* 185 */     this.field_180296_j = (WorldGenerator)new WorldGenMinable(Blocks.stone.getDefaultState().withProperty((IProperty)BlockStone.VARIANT_PROP, (Comparable)BlockStone.EnumType.GRANITE), this.field_180293_d.field_177796_Q);
/* 186 */     this.field_180297_k = (WorldGenerator)new WorldGenMinable(Blocks.stone.getDefaultState().withProperty((IProperty)BlockStone.VARIANT_PROP, (Comparable)BlockStone.EnumType.DIORITE), this.field_180293_d.field_177792_U);
/* 187 */     this.field_180295_l = (WorldGenerator)new WorldGenMinable(Blocks.stone.getDefaultState().withProperty((IProperty)BlockStone.VARIANT_PROP, (Comparable)BlockStone.EnumType.ANDESITE), this.field_180293_d.field_177800_Y);
/* 188 */     this.coalGen = (WorldGenerator)new WorldGenMinable(Blocks.coal_ore.getDefaultState(), this.field_180293_d.field_177844_ac);
/* 189 */     this.ironGen = (WorldGenerator)new WorldGenMinable(Blocks.iron_ore.getDefaultState(), this.field_180293_d.field_177848_ag);
/* 190 */     this.goldGen = (WorldGenerator)new WorldGenMinable(Blocks.gold_ore.getDefaultState(), this.field_180293_d.field_177828_ak);
/* 191 */     this.field_180299_p = (WorldGenerator)new WorldGenMinable(Blocks.redstone_ore.getDefaultState(), this.field_180293_d.field_177836_ao);
/* 192 */     this.field_180298_q = (WorldGenerator)new WorldGenMinable(Blocks.diamond_ore.getDefaultState(), this.field_180293_d.field_177814_as);
/* 193 */     this.lapisGen = (WorldGenerator)new WorldGenMinable(Blocks.lapis_ore.getDefaultState(), this.field_180293_d.field_177822_aw);
/* 194 */     genDecorations(p_180292_3_);
/* 195 */     this.currentWorld = null;
/* 196 */     this.randomGenerator = null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void genDecorations(BiomeGenBase p_150513_1_) {
/* 202 */     generateOres();
/*     */ 
/*     */     
/*     */     int var2;
/*     */     
/* 207 */     for (var2 = 0; var2 < this.sandPerChunk2; var2++) {
/*     */       
/* 209 */       int i = this.randomGenerator.nextInt(16) + 8;
/* 210 */       int var4 = this.randomGenerator.nextInt(16) + 8;
/* 211 */       this.sandGen.generate(this.currentWorld, this.randomGenerator, this.currentWorld.func_175672_r(this.field_180294_c.add(i, 0, var4)));
/*     */     } 
/*     */     
/* 214 */     for (var2 = 0; var2 < this.clayPerChunk; var2++) {
/*     */       
/* 216 */       int i = this.randomGenerator.nextInt(16) + 8;
/* 217 */       int var4 = this.randomGenerator.nextInt(16) + 8;
/* 218 */       this.clayGen.generate(this.currentWorld, this.randomGenerator, this.currentWorld.func_175672_r(this.field_180294_c.add(i, 0, var4)));
/*     */     } 
/*     */     
/* 221 */     for (var2 = 0; var2 < this.sandPerChunk; var2++) {
/*     */       
/* 223 */       int i = this.randomGenerator.nextInt(16) + 8;
/* 224 */       int var4 = this.randomGenerator.nextInt(16) + 8;
/* 225 */       this.gravelAsSandGen.generate(this.currentWorld, this.randomGenerator, this.currentWorld.func_175672_r(this.field_180294_c.add(i, 0, var4)));
/*     */     } 
/*     */     
/* 228 */     var2 = this.treesPerChunk;
/*     */     
/* 230 */     if (this.randomGenerator.nextInt(10) == 0)
/*     */     {
/* 232 */       var2++;
/*     */     }
/*     */ 
/*     */     
/*     */     int var3;
/*     */     
/* 238 */     for (var3 = 0; var3 < var2; var3++) {
/*     */       
/* 240 */       int var4 = this.randomGenerator.nextInt(16) + 8;
/* 241 */       int var5 = this.randomGenerator.nextInt(16) + 8;
/* 242 */       WorldGenAbstractTree var6 = p_150513_1_.genBigTreeChance(this.randomGenerator);
/* 243 */       var6.func_175904_e();
/* 244 */       BlockPos var7 = this.currentWorld.getHorizon(this.field_180294_c.add(var4, 0, var5));
/*     */       
/* 246 */       if (var6.generate(this.currentWorld, this.randomGenerator, var7))
/*     */       {
/* 248 */         var6.func_180711_a(this.currentWorld, this.randomGenerator, var7);
/*     */       }
/*     */     } 
/*     */     
/* 252 */     for (var3 = 0; var3 < this.bigMushroomsPerChunk; var3++) {
/*     */       
/* 254 */       int var4 = this.randomGenerator.nextInt(16) + 8;
/* 255 */       int var5 = this.randomGenerator.nextInt(16) + 8;
/* 256 */       this.bigMushroomGen.generate(this.currentWorld, this.randomGenerator, this.currentWorld.getHorizon(this.field_180294_c.add(var4, 0, var5)));
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 261 */     for (var3 = 0; var3 < this.flowersPerChunk; var3++) {
/*     */       
/* 263 */       int var4 = this.randomGenerator.nextInt(16) + 8;
/* 264 */       int var5 = this.randomGenerator.nextInt(16) + 8;
/* 265 */       int var11 = this.randomGenerator.nextInt(this.currentWorld.getHorizon(this.field_180294_c.add(var4, 0, var5)).getY() + 32);
/* 266 */       BlockPos var7 = this.field_180294_c.add(var4, var11, var5);
/* 267 */       BlockFlower.EnumFlowerType var8 = p_150513_1_.pickRandomFlower(this.randomGenerator, var7);
/* 268 */       BlockFlower var9 = var8.func_176964_a().func_180346_a();
/*     */       
/* 270 */       if (var9.getMaterial() != Material.air) {
/*     */         
/* 272 */         this.yellowFlowerGen.setGeneratedBlock(var9, var8);
/* 273 */         this.yellowFlowerGen.generate(this.currentWorld, this.randomGenerator, var7);
/*     */       } 
/*     */     } 
/*     */     
/* 277 */     for (var3 = 0; var3 < this.grassPerChunk; var3++) {
/*     */       
/* 279 */       int var4 = this.randomGenerator.nextInt(16) + 8;
/* 280 */       int var5 = this.randomGenerator.nextInt(16) + 8;
/* 281 */       int var11 = this.randomGenerator.nextInt(this.currentWorld.getHorizon(this.field_180294_c.add(var4, 0, var5)).getY() * 2);
/* 282 */       p_150513_1_.getRandomWorldGenForGrass(this.randomGenerator).generate(this.currentWorld, this.randomGenerator, this.field_180294_c.add(var4, var11, var5));
/*     */     } 
/*     */     
/* 285 */     for (var3 = 0; var3 < this.deadBushPerChunk; var3++) {
/*     */       
/* 287 */       int var4 = this.randomGenerator.nextInt(16) + 8;
/* 288 */       int var5 = this.randomGenerator.nextInt(16) + 8;
/* 289 */       int var11 = this.randomGenerator.nextInt(this.currentWorld.getHorizon(this.field_180294_c.add(var4, 0, var5)).getY() * 2);
/* 290 */       (new WorldGenDeadBush()).generate(this.currentWorld, this.randomGenerator, this.field_180294_c.add(var4, var11, var5));
/*     */     } 
/*     */     
/* 293 */     var3 = 0;
/*     */     
/* 295 */     while (var3 < this.waterlilyPerChunk) {
/*     */       
/* 297 */       int var4 = this.randomGenerator.nextInt(16) + 8;
/* 298 */       int var5 = this.randomGenerator.nextInt(16) + 8;
/* 299 */       int var11 = this.randomGenerator.nextInt(this.currentWorld.getHorizon(this.field_180294_c.add(var4, 0, var5)).getY() * 2);
/* 300 */       BlockPos var7 = this.field_180294_c.add(var4, var11, var5);
/*     */ 
/*     */ 
/*     */       
/* 304 */       while (var7.getY() > 0) {
/*     */         
/* 306 */         BlockPos var13 = var7.offsetDown();
/*     */         
/* 308 */         if (this.currentWorld.isAirBlock(var13))
/*     */         {
/* 310 */           var7 = var13;
/*     */         }
/*     */       } 
/*     */ 
/*     */       
/* 315 */       this.waterlilyGen.generate(this.currentWorld, this.randomGenerator, var7);
/* 316 */       var3++;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 321 */     for (var3 = 0; var3 < this.mushroomsPerChunk; var3++) {
/*     */       
/* 323 */       if (this.randomGenerator.nextInt(4) == 0) {
/*     */         
/* 325 */         int var4 = this.randomGenerator.nextInt(16) + 8;
/* 326 */         int var5 = this.randomGenerator.nextInt(16) + 8;
/* 327 */         BlockPos var12 = this.currentWorld.getHorizon(this.field_180294_c.add(var4, 0, var5));
/* 328 */         this.mushroomBrownGen.generate(this.currentWorld, this.randomGenerator, var12);
/*     */       } 
/*     */       
/* 331 */       if (this.randomGenerator.nextInt(8) == 0) {
/*     */         
/* 333 */         int var4 = this.randomGenerator.nextInt(16) + 8;
/* 334 */         int var5 = this.randomGenerator.nextInt(16) + 8;
/* 335 */         int var11 = this.randomGenerator.nextInt(this.currentWorld.getHorizon(this.field_180294_c.add(var4, 0, var5)).getY() * 2);
/* 336 */         BlockPos var7 = this.field_180294_c.add(var4, var11, var5);
/* 337 */         this.mushroomRedGen.generate(this.currentWorld, this.randomGenerator, var7);
/*     */       } 
/*     */     } 
/*     */     
/* 341 */     if (this.randomGenerator.nextInt(4) == 0) {
/*     */       
/* 343 */       var3 = this.randomGenerator.nextInt(16) + 8;
/* 344 */       int var4 = this.randomGenerator.nextInt(16) + 8;
/* 345 */       int var5 = this.randomGenerator.nextInt(this.currentWorld.getHorizon(this.field_180294_c.add(var3, 0, var4)).getY() * 2);
/* 346 */       this.mushroomBrownGen.generate(this.currentWorld, this.randomGenerator, this.field_180294_c.add(var3, var5, var4));
/*     */     } 
/*     */     
/* 349 */     if (this.randomGenerator.nextInt(8) == 0) {
/*     */       
/* 351 */       var3 = this.randomGenerator.nextInt(16) + 8;
/* 352 */       int var4 = this.randomGenerator.nextInt(16) + 8;
/* 353 */       int var5 = this.randomGenerator.nextInt(this.currentWorld.getHorizon(this.field_180294_c.add(var3, 0, var4)).getY() * 2);
/* 354 */       this.mushroomRedGen.generate(this.currentWorld, this.randomGenerator, this.field_180294_c.add(var3, var5, var4));
/*     */     } 
/*     */     
/* 357 */     for (var3 = 0; var3 < this.reedsPerChunk; var3++) {
/*     */       
/* 359 */       int var4 = this.randomGenerator.nextInt(16) + 8;
/* 360 */       int var5 = this.randomGenerator.nextInt(16) + 8;
/* 361 */       int var11 = this.randomGenerator.nextInt(this.currentWorld.getHorizon(this.field_180294_c.add(var4, 0, var5)).getY() * 2);
/* 362 */       this.reedGen.generate(this.currentWorld, this.randomGenerator, this.field_180294_c.add(var4, var11, var5));
/*     */     } 
/*     */     
/* 365 */     for (var3 = 0; var3 < 10; var3++) {
/*     */       
/* 367 */       int var4 = this.randomGenerator.nextInt(16) + 8;
/* 368 */       int var5 = this.randomGenerator.nextInt(16) + 8;
/* 369 */       int var11 = this.randomGenerator.nextInt(this.currentWorld.getHorizon(this.field_180294_c.add(var4, 0, var5)).getY() * 2);
/* 370 */       this.reedGen.generate(this.currentWorld, this.randomGenerator, this.field_180294_c.add(var4, var11, var5));
/*     */     } 
/*     */     
/* 373 */     if (this.randomGenerator.nextInt(32) == 0) {
/*     */       
/* 375 */       var3 = this.randomGenerator.nextInt(16) + 8;
/* 376 */       int var4 = this.randomGenerator.nextInt(16) + 8;
/* 377 */       int var5 = this.randomGenerator.nextInt(this.currentWorld.getHorizon(this.field_180294_c.add(var3, 0, var4)).getY() * 2);
/* 378 */       (new WorldGenPumpkin()).generate(this.currentWorld, this.randomGenerator, this.field_180294_c.add(var3, var5, var4));
/*     */     } 
/*     */     
/* 381 */     for (var3 = 0; var3 < this.cactiPerChunk; var3++) {
/*     */       
/* 383 */       int var4 = this.randomGenerator.nextInt(16) + 8;
/* 384 */       int var5 = this.randomGenerator.nextInt(16) + 8;
/* 385 */       int var11 = this.randomGenerator.nextInt(this.currentWorld.getHorizon(this.field_180294_c.add(var4, 0, var5)).getY() * 2);
/* 386 */       this.cactusGen.generate(this.currentWorld, this.randomGenerator, this.field_180294_c.add(var4, var11, var5));
/*     */     } 
/*     */     
/* 389 */     if (this.generateLakes) {
/*     */ 
/*     */ 
/*     */       
/* 393 */       for (var3 = 0; var3 < 50; var3++) {
/*     */         
/* 395 */         BlockPos var10 = this.field_180294_c.add(this.randomGenerator.nextInt(16) + 8, this.randomGenerator.nextInt(this.randomGenerator.nextInt(248) + 8), this.randomGenerator.nextInt(16) + 8);
/* 396 */         (new WorldGenLiquids((Block)Blocks.flowing_water)).generate(this.currentWorld, this.randomGenerator, var10);
/*     */       } 
/*     */       
/* 399 */       for (var3 = 0; var3 < 20; var3++) {
/*     */         
/* 401 */         BlockPos var10 = this.field_180294_c.add(this.randomGenerator.nextInt(16) + 8, this.randomGenerator.nextInt(this.randomGenerator.nextInt(this.randomGenerator.nextInt(240) + 8) + 8), this.randomGenerator.nextInt(16) + 8);
/* 402 */         (new WorldGenLiquids((Block)Blocks.flowing_lava)).generate(this.currentWorld, this.randomGenerator, var10);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void genStandardOre1(int p_76795_1_, WorldGenerator p_76795_2_, int p_76795_3_, int p_76795_4_) {
/* 414 */     if (p_76795_4_ < p_76795_3_) {
/*     */       
/* 416 */       int i = p_76795_3_;
/* 417 */       p_76795_3_ = p_76795_4_;
/* 418 */       p_76795_4_ = i;
/*     */     }
/* 420 */     else if (p_76795_4_ == p_76795_3_) {
/*     */       
/* 422 */       if (p_76795_3_ < 255) {
/*     */         
/* 424 */         p_76795_4_++;
/*     */       }
/*     */       else {
/*     */         
/* 428 */         p_76795_3_--;
/*     */       } 
/*     */     } 
/*     */     
/* 432 */     for (int var5 = 0; var5 < p_76795_1_; var5++) {
/*     */       
/* 434 */       BlockPos var6 = this.field_180294_c.add(this.randomGenerator.nextInt(16), this.randomGenerator.nextInt(p_76795_4_ - p_76795_3_) + p_76795_3_, this.randomGenerator.nextInt(16));
/* 435 */       p_76795_2_.generate(this.currentWorld, this.randomGenerator, var6);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void genStandardOre2(int p_76793_1_, WorldGenerator p_76793_2_, int p_76793_3_, int p_76793_4_) {
/* 444 */     for (int var5 = 0; var5 < p_76793_1_; var5++) {
/*     */       
/* 446 */       BlockPos var6 = this.field_180294_c.add(this.randomGenerator.nextInt(16), this.randomGenerator.nextInt(p_76793_4_) + this.randomGenerator.nextInt(p_76793_4_) + p_76793_3_ - p_76793_4_, this.randomGenerator.nextInt(16));
/* 447 */       p_76793_2_.generate(this.currentWorld, this.randomGenerator, var6);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void generateOres() {
/* 456 */     genStandardOre1(this.field_180293_d.field_177790_J, this.dirtGen, this.field_180293_d.field_177791_K, this.field_180293_d.field_177784_L);
/* 457 */     genStandardOre1(this.field_180293_d.field_177786_N, this.gravelGen, this.field_180293_d.field_177787_O, this.field_180293_d.field_177797_P);
/* 458 */     genStandardOre1(this.field_180293_d.field_177795_V, this.field_180297_k, this.field_180293_d.field_177794_W, this.field_180293_d.field_177801_X);
/* 459 */     genStandardOre1(this.field_180293_d.field_177799_R, this.field_180296_j, this.field_180293_d.field_177798_S, this.field_180293_d.field_177793_T);
/* 460 */     genStandardOre1(this.field_180293_d.field_177802_Z, this.field_180295_l, this.field_180293_d.field_177846_aa, this.field_180293_d.field_177847_ab);
/* 461 */     genStandardOre1(this.field_180293_d.field_177845_ad, this.coalGen, this.field_180293_d.field_177851_ae, this.field_180293_d.field_177853_af);
/* 462 */     genStandardOre1(this.field_180293_d.field_177849_ah, this.ironGen, this.field_180293_d.field_177832_ai, this.field_180293_d.field_177834_aj);
/* 463 */     genStandardOre1(this.field_180293_d.field_177830_al, this.goldGen, this.field_180293_d.field_177840_am, this.field_180293_d.field_177842_an);
/* 464 */     genStandardOre1(this.field_180293_d.field_177838_ap, this.field_180299_p, this.field_180293_d.field_177818_aq, this.field_180293_d.field_177816_ar);
/* 465 */     genStandardOre1(this.field_180293_d.field_177812_at, this.field_180298_q, this.field_180293_d.field_177826_au, this.field_180293_d.field_177824_av);
/* 466 */     genStandardOre2(this.field_180293_d.field_177820_ax, this.lapisGen, this.field_180293_d.field_177807_ay, this.field_180293_d.field_177805_az);
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\world\biome\BiomeDecorator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */