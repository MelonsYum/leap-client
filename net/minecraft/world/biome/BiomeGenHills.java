/*     */ package net.minecraft.world.biome;
/*     */ 
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.BlockSilverfish;
/*     */ import net.minecraft.block.properties.IProperty;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.chunk.ChunkPrimer;
/*     */ import net.minecraft.world.gen.feature.WorldGenAbstractTree;
/*     */ import net.minecraft.world.gen.feature.WorldGenMinable;
/*     */ import net.minecraft.world.gen.feature.WorldGenTaiga2;
/*     */ import net.minecraft.world.gen.feature.WorldGenerator;
/*     */ 
/*     */ public class BiomeGenHills
/*     */   extends BiomeGenBase {
/*     */   private WorldGenerator theWorldGenerator;
/*     */   private WorldGenTaiga2 field_150634_aD;
/*     */   private int field_150635_aE;
/*     */   private int field_150636_aF;
/*     */   private int field_150637_aG;
/*     */   private int field_150638_aH;
/*     */   private static final String __OBFID = "CL_00000168";
/*     */   
/*     */   protected BiomeGenHills(int p_i45373_1_, boolean p_i45373_2_) {
/*  26 */     super(p_i45373_1_);
/*  27 */     this.theWorldGenerator = (WorldGenerator)new WorldGenMinable(Blocks.monster_egg.getDefaultState().withProperty((IProperty)BlockSilverfish.VARIANT_PROP, (Comparable)BlockSilverfish.EnumType.STONE), 9);
/*  28 */     this.field_150634_aD = new WorldGenTaiga2(false);
/*  29 */     this.field_150635_aE = 0;
/*  30 */     this.field_150636_aF = 1;
/*  31 */     this.field_150637_aG = 2;
/*  32 */     this.field_150638_aH = this.field_150635_aE;
/*     */     
/*  34 */     if (p_i45373_2_) {
/*     */       
/*  36 */       this.theBiomeDecorator.treesPerChunk = 3;
/*  37 */       this.field_150638_aH = this.field_150636_aF;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public WorldGenAbstractTree genBigTreeChance(Random p_150567_1_) {
/*  43 */     return (p_150567_1_.nextInt(3) > 0) ? (WorldGenAbstractTree)this.field_150634_aD : super.genBigTreeChance(p_150567_1_);
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_180624_a(World worldIn, Random p_180624_2_, BlockPos p_180624_3_) {
/*  48 */     super.func_180624_a(worldIn, p_180624_2_, p_180624_3_);
/*  49 */     int var4 = 3 + p_180624_2_.nextInt(6);
/*     */ 
/*     */     
/*     */     int var5;
/*     */     
/*  54 */     for (var5 = 0; var5 < var4; var5++) {
/*     */       
/*  56 */       int var6 = p_180624_2_.nextInt(16);
/*  57 */       int var7 = p_180624_2_.nextInt(28) + 4;
/*  58 */       int var8 = p_180624_2_.nextInt(16);
/*  59 */       BlockPos var9 = p_180624_3_.add(var6, var7, var8);
/*     */       
/*  61 */       if (worldIn.getBlockState(var9).getBlock() == Blocks.stone)
/*     */       {
/*  63 */         worldIn.setBlockState(var9, Blocks.emerald_ore.getDefaultState(), 2);
/*     */       }
/*     */     } 
/*     */     
/*  67 */     for (var4 = 0; var4 < 7; var4++) {
/*     */       
/*  69 */       var5 = p_180624_2_.nextInt(16);
/*  70 */       int var6 = p_180624_2_.nextInt(64);
/*  71 */       int var7 = p_180624_2_.nextInt(16);
/*  72 */       this.theWorldGenerator.generate(worldIn, p_180624_2_, p_180624_3_.add(var5, var6, var7));
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void genTerrainBlocks(World worldIn, Random p_180622_2_, ChunkPrimer p_180622_3_, int p_180622_4_, int p_180622_5_, double p_180622_6_) {
/*  78 */     this.topBlock = Blocks.grass.getDefaultState();
/*  79 */     this.fillerBlock = Blocks.dirt.getDefaultState();
/*     */     
/*  81 */     if ((p_180622_6_ < -1.0D || p_180622_6_ > 2.0D) && this.field_150638_aH == this.field_150637_aG) {
/*     */       
/*  83 */       this.topBlock = Blocks.gravel.getDefaultState();
/*  84 */       this.fillerBlock = Blocks.gravel.getDefaultState();
/*     */     }
/*  86 */     else if (p_180622_6_ > 1.0D && this.field_150638_aH != this.field_150636_aF) {
/*     */       
/*  88 */       this.topBlock = Blocks.stone.getDefaultState();
/*  89 */       this.fillerBlock = Blocks.stone.getDefaultState();
/*     */     } 
/*     */     
/*  92 */     func_180628_b(worldIn, p_180622_2_, p_180622_3_, p_180622_4_, p_180622_5_, p_180622_6_);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private BiomeGenHills mutateHills(BiomeGenBase p_150633_1_) {
/* 100 */     this.field_150638_aH = this.field_150637_aG;
/* 101 */     func_150557_a(p_150633_1_.color, true);
/* 102 */     setBiomeName(String.valueOf(p_150633_1_.biomeName) + " M");
/* 103 */     setHeight(new BiomeGenBase.Height(p_150633_1_.minHeight, p_150633_1_.maxHeight));
/* 104 */     setTemperatureRainfall(p_150633_1_.temperature, p_150633_1_.rainfall);
/* 105 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   protected BiomeGenBase createMutatedBiome(int p_180277_1_) {
/* 110 */     return (new BiomeGenHills(p_180277_1_, false)).mutateHills(this);
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\world\biome\BiomeGenHills.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */