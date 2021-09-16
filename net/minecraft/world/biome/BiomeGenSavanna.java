/*    */ package net.minecraft.world.biome;
/*    */ 
/*    */ import java.util.Random;
/*    */ import net.minecraft.block.BlockDirt;
/*    */ import net.minecraft.block.BlockDoublePlant;
/*    */ import net.minecraft.block.properties.IProperty;
/*    */ import net.minecraft.entity.passive.EntityHorse;
/*    */ import net.minecraft.init.Blocks;
/*    */ import net.minecraft.util.BlockPos;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraft.world.chunk.ChunkPrimer;
/*    */ import net.minecraft.world.gen.feature.WorldGenAbstractTree;
/*    */ import net.minecraft.world.gen.feature.WorldGenSavannaTree;
/*    */ 
/*    */ public class BiomeGenSavanna extends BiomeGenBase {
/* 16 */   private static final WorldGenSavannaTree field_150627_aC = new WorldGenSavannaTree(false);
/*    */   
/*    */   private static final String __OBFID = "CL_00000182";
/*    */   
/*    */   protected BiomeGenSavanna(int p_i45383_1_) {
/* 21 */     super(p_i45383_1_);
/* 22 */     this.spawnableCreatureList.add(new BiomeGenBase.SpawnListEntry(EntityHorse.class, 1, 2, 6));
/* 23 */     this.theBiomeDecorator.treesPerChunk = 1;
/* 24 */     this.theBiomeDecorator.flowersPerChunk = 4;
/* 25 */     this.theBiomeDecorator.grassPerChunk = 20;
/*    */   }
/*    */ 
/*    */   
/*    */   public WorldGenAbstractTree genBigTreeChance(Random p_150567_1_) {
/* 30 */     return (p_150567_1_.nextInt(5) > 0) ? (WorldGenAbstractTree)field_150627_aC : (WorldGenAbstractTree)this.worldGeneratorTrees;
/*    */   }
/*    */ 
/*    */   
/*    */   protected BiomeGenBase createMutatedBiome(int p_180277_1_) {
/* 35 */     Mutated var2 = new Mutated(p_180277_1_, this);
/* 36 */     var2.temperature = (this.temperature + 1.0F) * 0.5F;
/* 37 */     var2.minHeight = this.minHeight * 0.5F + 0.3F;
/* 38 */     var2.maxHeight = this.maxHeight * 0.5F + 1.2F;
/* 39 */     return var2;
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_180624_a(World worldIn, Random p_180624_2_, BlockPos p_180624_3_) {
/* 44 */     field_180280_ag.func_180710_a(BlockDoublePlant.EnumPlantType.GRASS);
/*    */     
/* 46 */     for (int var4 = 0; var4 < 7; var4++) {
/*    */       
/* 48 */       int var5 = p_180624_2_.nextInt(16) + 8;
/* 49 */       int var6 = p_180624_2_.nextInt(16) + 8;
/* 50 */       int var7 = p_180624_2_.nextInt(worldIn.getHorizon(p_180624_3_.add(var5, 0, var6)).getY() + 32);
/* 51 */       field_180280_ag.generate(worldIn, p_180624_2_, p_180624_3_.add(var5, var7, var6));
/*    */     } 
/*    */     
/* 54 */     super.func_180624_a(worldIn, p_180624_2_, p_180624_3_);
/*    */   }
/*    */   
/*    */   public static class Mutated
/*    */     extends BiomeGenMutated
/*    */   {
/*    */     private static final String __OBFID = "CL_00000183";
/*    */     
/*    */     public Mutated(int p_i45382_1_, BiomeGenBase p_i45382_2_) {
/* 63 */       super(p_i45382_1_, p_i45382_2_);
/* 64 */       this.theBiomeDecorator.treesPerChunk = 2;
/* 65 */       this.theBiomeDecorator.flowersPerChunk = 2;
/* 66 */       this.theBiomeDecorator.grassPerChunk = 5;
/*    */     }
/*    */ 
/*    */     
/*    */     public void genTerrainBlocks(World worldIn, Random p_180622_2_, ChunkPrimer p_180622_3_, int p_180622_4_, int p_180622_5_, double p_180622_6_) {
/* 71 */       this.topBlock = Blocks.grass.getDefaultState();
/* 72 */       this.fillerBlock = Blocks.dirt.getDefaultState();
/*    */       
/* 74 */       if (p_180622_6_ > 1.75D) {
/*    */         
/* 76 */         this.topBlock = Blocks.stone.getDefaultState();
/* 77 */         this.fillerBlock = Blocks.stone.getDefaultState();
/*    */       }
/* 79 */       else if (p_180622_6_ > -0.5D) {
/*    */         
/* 81 */         this.topBlock = Blocks.dirt.getDefaultState().withProperty((IProperty)BlockDirt.VARIANT, (Comparable)BlockDirt.DirtType.COARSE_DIRT);
/*    */       } 
/*    */       
/* 84 */       func_180628_b(worldIn, p_180622_2_, p_180622_3_, p_180622_4_, p_180622_5_, p_180622_6_);
/*    */     }
/*    */ 
/*    */     
/*    */     public void func_180624_a(World worldIn, Random p_180624_2_, BlockPos p_180624_3_) {
/* 89 */       this.theBiomeDecorator.func_180292_a(worldIn, p_180624_2_, this, p_180624_3_);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\world\biome\BiomeGenSavanna.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */