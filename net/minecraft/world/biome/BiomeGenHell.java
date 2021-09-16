/*    */ package net.minecraft.world.biome;
/*    */ 
/*    */ import net.minecraft.entity.monster.EntityGhast;
/*    */ import net.minecraft.entity.monster.EntityMagmaCube;
/*    */ import net.minecraft.entity.monster.EntityPigZombie;
/*    */ 
/*    */ public class BiomeGenHell
/*    */   extends BiomeGenBase
/*    */ {
/*    */   private static final String __OBFID = "CL_00000173";
/*    */   
/*    */   public BiomeGenHell(int p_i1981_1_) {
/* 13 */     super(p_i1981_1_);
/* 14 */     this.spawnableMonsterList.clear();
/* 15 */     this.spawnableCreatureList.clear();
/* 16 */     this.spawnableWaterCreatureList.clear();
/* 17 */     this.spawnableCaveCreatureList.clear();
/* 18 */     this.spawnableMonsterList.add(new BiomeGenBase.SpawnListEntry(EntityGhast.class, 50, 4, 4));
/* 19 */     this.spawnableMonsterList.add(new BiomeGenBase.SpawnListEntry(EntityPigZombie.class, 100, 4, 4));
/* 20 */     this.spawnableMonsterList.add(new BiomeGenBase.SpawnListEntry(EntityMagmaCube.class, 1, 4, 4));
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\world\biome\BiomeGenHell.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */