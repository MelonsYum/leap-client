/*    */ package net.minecraft.world.biome;
/*    */ 
/*    */ import net.minecraft.init.Blocks;
/*    */ 
/*    */ public class BiomeGenStoneBeach
/*    */   extends BiomeGenBase
/*    */ {
/*    */   private static final String __OBFID = "CL_00000184";
/*    */   
/*    */   public BiomeGenStoneBeach(int p_i45384_1_) {
/* 11 */     super(p_i45384_1_);
/* 12 */     this.spawnableCreatureList.clear();
/* 13 */     this.topBlock = Blocks.stone.getDefaultState();
/* 14 */     this.fillerBlock = Blocks.stone.getDefaultState();
/* 15 */     this.theBiomeDecorator.treesPerChunk = -999;
/* 16 */     this.theBiomeDecorator.deadBushPerChunk = 0;
/* 17 */     this.theBiomeDecorator.reedsPerChunk = 0;
/* 18 */     this.theBiomeDecorator.cactiPerChunk = 0;
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\world\biome\BiomeGenStoneBeach.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */