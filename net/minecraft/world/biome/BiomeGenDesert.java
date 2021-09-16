/*    */ package net.minecraft.world.biome;
/*    */ 
/*    */ import java.util.Random;
/*    */ import net.minecraft.init.Blocks;
/*    */ import net.minecraft.util.BlockPos;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraft.world.gen.feature.WorldGenDesertWells;
/*    */ 
/*    */ public class BiomeGenDesert
/*    */   extends BiomeGenBase
/*    */ {
/*    */   private static final String __OBFID = "CL_00000167";
/*    */   
/*    */   public BiomeGenDesert(int p_i1977_1_) {
/* 15 */     super(p_i1977_1_);
/* 16 */     this.spawnableCreatureList.clear();
/* 17 */     this.topBlock = Blocks.sand.getDefaultState();
/* 18 */     this.fillerBlock = Blocks.sand.getDefaultState();
/* 19 */     this.theBiomeDecorator.treesPerChunk = -999;
/* 20 */     this.theBiomeDecorator.deadBushPerChunk = 2;
/* 21 */     this.theBiomeDecorator.reedsPerChunk = 50;
/* 22 */     this.theBiomeDecorator.cactiPerChunk = 10;
/* 23 */     this.spawnableCreatureList.clear();
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_180624_a(World worldIn, Random p_180624_2_, BlockPos p_180624_3_) {
/* 28 */     super.func_180624_a(worldIn, p_180624_2_, p_180624_3_);
/*    */     
/* 30 */     if (p_180624_2_.nextInt(1000) == 0) {
/*    */       
/* 32 */       int var4 = p_180624_2_.nextInt(16) + 8;
/* 33 */       int var5 = p_180624_2_.nextInt(16) + 8;
/* 34 */       BlockPos var6 = worldIn.getHorizon(p_180624_3_.add(var4, 0, var5)).offsetUp();
/* 35 */       (new WorldGenDesertWells()).generate(worldIn, p_180624_2_, var6);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\world\biome\BiomeGenDesert.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */