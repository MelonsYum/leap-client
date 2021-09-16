/*     */ package net.minecraft.world.biome;
/*     */ 
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.BlockDirt;
/*     */ import net.minecraft.block.BlockDoublePlant;
/*     */ import net.minecraft.block.BlockTallGrass;
/*     */ import net.minecraft.block.properties.IProperty;
/*     */ import net.minecraft.entity.passive.EntityWolf;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.chunk.ChunkPrimer;
/*     */ import net.minecraft.world.gen.feature.WorldGenAbstractTree;
/*     */ import net.minecraft.world.gen.feature.WorldGenBlockBlob;
/*     */ import net.minecraft.world.gen.feature.WorldGenMegaPineTree;
/*     */ import net.minecraft.world.gen.feature.WorldGenTaiga1;
/*     */ import net.minecraft.world.gen.feature.WorldGenTaiga2;
/*     */ import net.minecraft.world.gen.feature.WorldGenTallGrass;
/*     */ import net.minecraft.world.gen.feature.WorldGenerator;
/*     */ 
/*     */ public class BiomeGenTaiga extends BiomeGenBase {
/*  22 */   private static final WorldGenTaiga1 field_150639_aC = new WorldGenTaiga1();
/*  23 */   private static final WorldGenTaiga2 field_150640_aD = new WorldGenTaiga2(false);
/*  24 */   private static final WorldGenMegaPineTree field_150641_aE = new WorldGenMegaPineTree(false, false);
/*  25 */   private static final WorldGenMegaPineTree field_150642_aF = new WorldGenMegaPineTree(false, true);
/*  26 */   private static final WorldGenBlockBlob field_150643_aG = new WorldGenBlockBlob(Blocks.mossy_cobblestone, 0);
/*     */   
/*     */   private int field_150644_aH;
/*     */   private static final String __OBFID = "CL_00000186";
/*     */   
/*     */   public BiomeGenTaiga(int p_i45385_1_, int p_i45385_2_) {
/*  32 */     super(p_i45385_1_);
/*  33 */     this.field_150644_aH = p_i45385_2_;
/*  34 */     this.spawnableCreatureList.add(new BiomeGenBase.SpawnListEntry(EntityWolf.class, 8, 4, 4));
/*  35 */     this.theBiomeDecorator.treesPerChunk = 10;
/*     */     
/*  37 */     if (p_i45385_2_ != 1 && p_i45385_2_ != 2) {
/*     */       
/*  39 */       this.theBiomeDecorator.grassPerChunk = 1;
/*  40 */       this.theBiomeDecorator.mushroomsPerChunk = 1;
/*     */     }
/*     */     else {
/*     */       
/*  44 */       this.theBiomeDecorator.grassPerChunk = 7;
/*  45 */       this.theBiomeDecorator.deadBushPerChunk = 1;
/*  46 */       this.theBiomeDecorator.mushroomsPerChunk = 3;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public WorldGenAbstractTree genBigTreeChance(Random p_150567_1_) {
/*  52 */     return ((this.field_150644_aH == 1 || this.field_150644_aH == 2) && p_150567_1_.nextInt(3) == 0) ? ((this.field_150644_aH != 2 && p_150567_1_.nextInt(13) != 0) ? (WorldGenAbstractTree)field_150641_aE : (WorldGenAbstractTree)field_150642_aF) : ((p_150567_1_.nextInt(3) == 0) ? (WorldGenAbstractTree)field_150639_aC : (WorldGenAbstractTree)field_150640_aD);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public WorldGenerator getRandomWorldGenForGrass(Random p_76730_1_) {
/*  60 */     return (p_76730_1_.nextInt(5) > 0) ? (WorldGenerator)new WorldGenTallGrass(BlockTallGrass.EnumType.FERN) : (WorldGenerator)new WorldGenTallGrass(BlockTallGrass.EnumType.GRASS);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_180624_a(World worldIn, Random p_180624_2_, BlockPos p_180624_3_) {
/*  70 */     if (this.field_150644_aH == 1 || this.field_150644_aH == 2) {
/*     */       
/*  72 */       int i = p_180624_2_.nextInt(3);
/*     */       
/*  74 */       for (int var5 = 0; var5 < i; var5++) {
/*     */         
/*  76 */         int var6 = p_180624_2_.nextInt(16) + 8;
/*  77 */         int var7 = p_180624_2_.nextInt(16) + 8;
/*  78 */         BlockPos var8 = worldIn.getHorizon(p_180624_3_.add(var6, 0, var7));
/*  79 */         field_150643_aG.generate(worldIn, p_180624_2_, var8);
/*     */       } 
/*     */     } 
/*     */     
/*  83 */     field_180280_ag.func_180710_a(BlockDoublePlant.EnumPlantType.FERN);
/*     */     
/*  85 */     for (int var4 = 0; var4 < 7; var4++) {
/*     */       
/*  87 */       int var5 = p_180624_2_.nextInt(16) + 8;
/*  88 */       int var6 = p_180624_2_.nextInt(16) + 8;
/*  89 */       int var7 = p_180624_2_.nextInt(worldIn.getHorizon(p_180624_3_.add(var5, 0, var6)).getY() + 32);
/*  90 */       field_180280_ag.generate(worldIn, p_180624_2_, p_180624_3_.add(var5, var7, var6));
/*     */     } 
/*     */     
/*  93 */     super.func_180624_a(worldIn, p_180624_2_, p_180624_3_);
/*     */   }
/*     */ 
/*     */   
/*     */   public void genTerrainBlocks(World worldIn, Random p_180622_2_, ChunkPrimer p_180622_3_, int p_180622_4_, int p_180622_5_, double p_180622_6_) {
/*  98 */     if (this.field_150644_aH == 1 || this.field_150644_aH == 2) {
/*     */       
/* 100 */       this.topBlock = Blocks.grass.getDefaultState();
/* 101 */       this.fillerBlock = Blocks.dirt.getDefaultState();
/*     */       
/* 103 */       if (p_180622_6_ > 1.75D) {
/*     */         
/* 105 */         this.topBlock = Blocks.dirt.getDefaultState().withProperty((IProperty)BlockDirt.VARIANT, (Comparable)BlockDirt.DirtType.COARSE_DIRT);
/*     */       }
/* 107 */       else if (p_180622_6_ > -0.95D) {
/*     */         
/* 109 */         this.topBlock = Blocks.dirt.getDefaultState().withProperty((IProperty)BlockDirt.VARIANT, (Comparable)BlockDirt.DirtType.PODZOL);
/*     */       } 
/*     */     } 
/*     */     
/* 113 */     func_180628_b(worldIn, p_180622_2_, p_180622_3_, p_180622_4_, p_180622_5_, p_180622_6_);
/*     */   }
/*     */ 
/*     */   
/*     */   protected BiomeGenBase createMutatedBiome(int p_180277_1_) {
/* 118 */     return (this.biomeID == BiomeGenBase.megaTaiga.biomeID) ? (new BiomeGenTaiga(p_180277_1_, 2)).func_150557_a(5858897, true).setBiomeName("Mega Spruce Taiga").setFillerBlockMetadata(5159473).setTemperatureRainfall(0.25F, 0.8F).setHeight(new BiomeGenBase.Height(this.minHeight, this.maxHeight)) : super.createMutatedBiome(p_180277_1_);
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\world\biome\BiomeGenTaiga.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */