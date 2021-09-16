/*    */ package net.minecraft.world.biome;
/*    */ 
/*    */ import java.util.Arrays;
/*    */ import java.util.List;
/*    */ import java.util.Random;
/*    */ import net.minecraft.util.BlockPos;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class WorldChunkManagerHell
/*    */   extends WorldChunkManager
/*    */ {
/*    */   private BiomeGenBase biomeGenerator;
/*    */   private float rainfall;
/*    */   private static final String __OBFID = "CL_00000169";
/*    */   
/*    */   public WorldChunkManagerHell(BiomeGenBase p_i45374_1_, float p_i45374_2_) {
/* 19 */     this.biomeGenerator = p_i45374_1_;
/* 20 */     this.rainfall = p_i45374_2_;
/*    */   }
/*    */ 
/*    */   
/*    */   public BiomeGenBase func_180631_a(BlockPos p_180631_1_) {
/* 25 */     return this.biomeGenerator;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public BiomeGenBase[] getBiomesForGeneration(BiomeGenBase[] p_76937_1_, int p_76937_2_, int p_76937_3_, int p_76937_4_, int p_76937_5_) {
/* 33 */     if (p_76937_1_ == null || p_76937_1_.length < p_76937_4_ * p_76937_5_)
/*    */     {
/* 35 */       p_76937_1_ = new BiomeGenBase[p_76937_4_ * p_76937_5_];
/*    */     }
/*    */     
/* 38 */     Arrays.fill((Object[])p_76937_1_, 0, p_76937_4_ * p_76937_5_, this.biomeGenerator);
/* 39 */     return p_76937_1_;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public float[] getRainfall(float[] p_76936_1_, int p_76936_2_, int p_76936_3_, int p_76936_4_, int p_76936_5_) {
/* 47 */     if (p_76936_1_ == null || p_76936_1_.length < p_76936_4_ * p_76936_5_)
/*    */     {
/* 49 */       p_76936_1_ = new float[p_76936_4_ * p_76936_5_];
/*    */     }
/*    */     
/* 52 */     Arrays.fill(p_76936_1_, 0, p_76936_4_ * p_76936_5_, this.rainfall);
/* 53 */     return p_76936_1_;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public BiomeGenBase[] loadBlockGeneratorData(BiomeGenBase[] p_76933_1_, int p_76933_2_, int p_76933_3_, int p_76933_4_, int p_76933_5_) {
/* 62 */     if (p_76933_1_ == null || p_76933_1_.length < p_76933_4_ * p_76933_5_)
/*    */     {
/* 64 */       p_76933_1_ = new BiomeGenBase[p_76933_4_ * p_76933_5_];
/*    */     }
/*    */     
/* 67 */     Arrays.fill((Object[])p_76933_1_, 0, p_76933_4_ * p_76933_5_, this.biomeGenerator);
/* 68 */     return p_76933_1_;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public BiomeGenBase[] getBiomeGenAt(BiomeGenBase[] p_76931_1_, int p_76931_2_, int p_76931_3_, int p_76931_4_, int p_76931_5_, boolean p_76931_6_) {
/* 77 */     return loadBlockGeneratorData(p_76931_1_, p_76931_2_, p_76931_3_, p_76931_4_, p_76931_5_);
/*    */   }
/*    */ 
/*    */   
/*    */   public BlockPos findBiomePosition(int x, int z, int range, List biomes, Random random) {
/* 82 */     return biomes.contains(this.biomeGenerator) ? new BlockPos(x - range + random.nextInt(range * 2 + 1), 0, z - range + random.nextInt(range * 2 + 1)) : null;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean areBiomesViable(int p_76940_1_, int p_76940_2_, int p_76940_3_, List p_76940_4_) {
/* 90 */     return p_76940_4_.contains(this.biomeGenerator);
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\world\biome\WorldChunkManagerHell.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */