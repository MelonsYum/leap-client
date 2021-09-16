/*    */ package net.minecraft.world.biome;
/*    */ 
/*    */ import net.minecraft.init.Blocks;
/*    */ 
/*    */ public class BiomeGenBeach
/*    */   extends BiomeGenBase
/*    */ {
/*    */   private static final String __OBFID = "CL_00000157";
/*    */   
/*    */   public BiomeGenBeach(int p_i1969_1_) {
/* 11 */     super(p_i1969_1_);
/* 12 */     this.spawnableCreatureList.clear();
/* 13 */     this.topBlock = Blocks.sand.getDefaultState();
/* 14 */     this.fillerBlock = Blocks.sand.getDefaultState();
/* 15 */     this.theBiomeDecorator.treesPerChunk = -999;
/* 16 */     this.theBiomeDecorator.deadBushPerChunk = 0;
/* 17 */     this.theBiomeDecorator.reedsPerChunk = 0;
/* 18 */     this.theBiomeDecorator.cactiPerChunk = 0;
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\world\biome\BiomeGenBeach.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */