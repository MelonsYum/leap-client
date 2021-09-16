/*    */ package net.minecraft.world.biome;
/*    */ 
/*    */ import com.google.common.collect.Lists;
/*    */ import java.util.Random;
/*    */ import net.minecraft.util.BlockPos;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraft.world.chunk.ChunkPrimer;
/*    */ import net.minecraft.world.gen.feature.WorldGenAbstractTree;
/*    */ 
/*    */ public class BiomeGenMutated
/*    */   extends BiomeGenBase
/*    */ {
/*    */   protected BiomeGenBase baseBiome;
/*    */   private static final String __OBFID = "CL_00000178";
/*    */   
/*    */   public BiomeGenMutated(int p_i45381_1_, BiomeGenBase p_i45381_2_) {
/* 17 */     super(p_i45381_1_);
/* 18 */     this.baseBiome = p_i45381_2_;
/* 19 */     func_150557_a(p_i45381_2_.color, true);
/* 20 */     this.biomeName = String.valueOf(p_i45381_2_.biomeName) + " M";
/* 21 */     this.topBlock = p_i45381_2_.topBlock;
/* 22 */     this.fillerBlock = p_i45381_2_.fillerBlock;
/* 23 */     this.fillerBlockMetadata = p_i45381_2_.fillerBlockMetadata;
/* 24 */     this.minHeight = p_i45381_2_.minHeight;
/* 25 */     this.maxHeight = p_i45381_2_.maxHeight;
/* 26 */     this.temperature = p_i45381_2_.temperature;
/* 27 */     this.rainfall = p_i45381_2_.rainfall;
/* 28 */     this.waterColorMultiplier = p_i45381_2_.waterColorMultiplier;
/* 29 */     this.enableSnow = p_i45381_2_.enableSnow;
/* 30 */     this.enableRain = p_i45381_2_.enableRain;
/* 31 */     this.spawnableCreatureList = Lists.newArrayList(p_i45381_2_.spawnableCreatureList);
/* 32 */     this.spawnableMonsterList = Lists.newArrayList(p_i45381_2_.spawnableMonsterList);
/* 33 */     this.spawnableCaveCreatureList = Lists.newArrayList(p_i45381_2_.spawnableCaveCreatureList);
/* 34 */     this.spawnableWaterCreatureList = Lists.newArrayList(p_i45381_2_.spawnableWaterCreatureList);
/* 35 */     this.temperature = p_i45381_2_.temperature;
/* 36 */     this.rainfall = p_i45381_2_.rainfall;
/* 37 */     this.minHeight = p_i45381_2_.minHeight + 0.1F;
/* 38 */     this.maxHeight = p_i45381_2_.maxHeight + 0.2F;
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_180624_a(World worldIn, Random p_180624_2_, BlockPos p_180624_3_) {
/* 43 */     this.baseBiome.theBiomeDecorator.func_180292_a(worldIn, p_180624_2_, this, p_180624_3_);
/*    */   }
/*    */ 
/*    */   
/*    */   public void genTerrainBlocks(World worldIn, Random p_180622_2_, ChunkPrimer p_180622_3_, int p_180622_4_, int p_180622_5_, double p_180622_6_) {
/* 48 */     this.baseBiome.genTerrainBlocks(worldIn, p_180622_2_, p_180622_3_, p_180622_4_, p_180622_5_, p_180622_6_);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public float getSpawningChance() {
/* 56 */     return this.baseBiome.getSpawningChance();
/*    */   }
/*    */ 
/*    */   
/*    */   public WorldGenAbstractTree genBigTreeChance(Random p_150567_1_) {
/* 61 */     return this.baseBiome.genBigTreeChance(p_150567_1_);
/*    */   }
/*    */ 
/*    */   
/*    */   public int func_180625_c(BlockPos p_180625_1_) {
/* 66 */     return this.baseBiome.func_180625_c(p_180625_1_);
/*    */   }
/*    */ 
/*    */   
/*    */   public int func_180627_b(BlockPos p_180627_1_) {
/* 71 */     return this.baseBiome.func_180627_b(p_180627_1_);
/*    */   }
/*    */ 
/*    */   
/*    */   public Class getBiomeClass() {
/* 76 */     return this.baseBiome.getBiomeClass();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean isEqualTo(BiomeGenBase p_150569_1_) {
/* 84 */     return this.baseBiome.isEqualTo(p_150569_1_);
/*    */   }
/*    */ 
/*    */   
/*    */   public BiomeGenBase.TempCategory getTempCategory() {
/* 89 */     return this.baseBiome.getTempCategory();
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\world\biome\BiomeGenMutated.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */