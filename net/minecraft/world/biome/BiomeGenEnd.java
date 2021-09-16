/*    */ package net.minecraft.world.biome;
/*    */ 
/*    */ import net.minecraft.entity.monster.EntityEnderman;
/*    */ import net.minecraft.init.Blocks;
/*    */ 
/*    */ public class BiomeGenEnd
/*    */   extends BiomeGenBase
/*    */ {
/*    */   private static final String __OBFID = "CL_00000187";
/*    */   
/*    */   public BiomeGenEnd(int p_i1990_1_) {
/* 12 */     super(p_i1990_1_);
/* 13 */     this.spawnableMonsterList.clear();
/* 14 */     this.spawnableCreatureList.clear();
/* 15 */     this.spawnableWaterCreatureList.clear();
/* 16 */     this.spawnableCaveCreatureList.clear();
/* 17 */     this.spawnableMonsterList.add(new BiomeGenBase.SpawnListEntry(EntityEnderman.class, 10, 4, 4));
/* 18 */     this.topBlock = Blocks.dirt.getDefaultState();
/* 19 */     this.fillerBlock = Blocks.dirt.getDefaultState();
/* 20 */     this.theBiomeDecorator = new BiomeEndDecorator();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int getSkyColorByTemp(float p_76731_1_) {
/* 28 */     return 0;
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\world\biome\BiomeGenEnd.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */