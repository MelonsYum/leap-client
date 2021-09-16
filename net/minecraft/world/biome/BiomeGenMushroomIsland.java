/*    */ package net.minecraft.world.biome;
/*    */ 
/*    */ import net.minecraft.entity.passive.EntityMooshroom;
/*    */ import net.minecraft.init.Blocks;
/*    */ 
/*    */ public class BiomeGenMushroomIsland
/*    */   extends BiomeGenBase
/*    */ {
/*    */   private static final String __OBFID = "CL_00000177";
/*    */   
/*    */   public BiomeGenMushroomIsland(int p_i1984_1_) {
/* 12 */     super(p_i1984_1_);
/* 13 */     this.theBiomeDecorator.treesPerChunk = -100;
/* 14 */     this.theBiomeDecorator.flowersPerChunk = -100;
/* 15 */     this.theBiomeDecorator.grassPerChunk = -100;
/* 16 */     this.theBiomeDecorator.mushroomsPerChunk = 1;
/* 17 */     this.theBiomeDecorator.bigMushroomsPerChunk = 1;
/* 18 */     this.topBlock = Blocks.mycelium.getDefaultState();
/* 19 */     this.spawnableMonsterList.clear();
/* 20 */     this.spawnableCreatureList.clear();
/* 21 */     this.spawnableWaterCreatureList.clear();
/* 22 */     this.spawnableCreatureList.add(new BiomeGenBase.SpawnListEntry(EntityMooshroom.class, 8, 4, 8));
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\world\biome\BiomeGenMushroomIsland.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */