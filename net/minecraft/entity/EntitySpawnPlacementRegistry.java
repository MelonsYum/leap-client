/*    */ package net.minecraft.entity;
/*    */ 
/*    */ import com.google.common.collect.Maps;
/*    */ import java.util.HashMap;
/*    */ import net.minecraft.entity.boss.EntityDragon;
/*    */ import net.minecraft.entity.boss.EntityWither;
/*    */ import net.minecraft.entity.monster.EntityBlaze;
/*    */ import net.minecraft.entity.monster.EntityCaveSpider;
/*    */ import net.minecraft.entity.monster.EntityCreeper;
/*    */ import net.minecraft.entity.monster.EntityEnderman;
/*    */ import net.minecraft.entity.monster.EntityEndermite;
/*    */ import net.minecraft.entity.monster.EntityGhast;
/*    */ import net.minecraft.entity.monster.EntityGiantZombie;
/*    */ import net.minecraft.entity.monster.EntityGuardian;
/*    */ import net.minecraft.entity.monster.EntityIronGolem;
/*    */ import net.minecraft.entity.monster.EntityMagmaCube;
/*    */ import net.minecraft.entity.monster.EntityPigZombie;
/*    */ import net.minecraft.entity.monster.EntitySilverfish;
/*    */ import net.minecraft.entity.monster.EntitySkeleton;
/*    */ import net.minecraft.entity.monster.EntitySlime;
/*    */ import net.minecraft.entity.monster.EntitySnowman;
/*    */ import net.minecraft.entity.monster.EntitySpider;
/*    */ import net.minecraft.entity.monster.EntityWitch;
/*    */ import net.minecraft.entity.monster.EntityZombie;
/*    */ import net.minecraft.entity.passive.EntityBat;
/*    */ import net.minecraft.entity.passive.EntityChicken;
/*    */ import net.minecraft.entity.passive.EntityCow;
/*    */ import net.minecraft.entity.passive.EntityHorse;
/*    */ import net.minecraft.entity.passive.EntityMooshroom;
/*    */ import net.minecraft.entity.passive.EntityOcelot;
/*    */ import net.minecraft.entity.passive.EntityPig;
/*    */ import net.minecraft.entity.passive.EntityRabbit;
/*    */ import net.minecraft.entity.passive.EntitySheep;
/*    */ import net.minecraft.entity.passive.EntitySquid;
/*    */ import net.minecraft.entity.passive.EntityVillager;
/*    */ import net.minecraft.entity.passive.EntityWolf;
/*    */ 
/*    */ public class EntitySpawnPlacementRegistry
/*    */ {
/* 40 */   private static final HashMap field_180110_a = Maps.newHashMap();
/*    */   
/*    */   private static final String __OBFID = "CL_00002254";
/*    */   
/*    */   public static EntityLiving.SpawnPlacementType func_180109_a(Class p_180109_0_) {
/* 45 */     return (EntityLiving.SpawnPlacementType)field_180110_a.get(p_180109_0_);
/*    */   }
/*    */ 
/*    */   
/*    */   static {
/* 50 */     field_180110_a.put(EntityBat.class, EntityLiving.SpawnPlacementType.ON_GROUND);
/* 51 */     field_180110_a.put(EntityChicken.class, EntityLiving.SpawnPlacementType.ON_GROUND);
/* 52 */     field_180110_a.put(EntityCow.class, EntityLiving.SpawnPlacementType.ON_GROUND);
/* 53 */     field_180110_a.put(EntityHorse.class, EntityLiving.SpawnPlacementType.ON_GROUND);
/* 54 */     field_180110_a.put(EntityMooshroom.class, EntityLiving.SpawnPlacementType.ON_GROUND);
/* 55 */     field_180110_a.put(EntityOcelot.class, EntityLiving.SpawnPlacementType.ON_GROUND);
/* 56 */     field_180110_a.put(EntityPig.class, EntityLiving.SpawnPlacementType.ON_GROUND);
/* 57 */     field_180110_a.put(EntityRabbit.class, EntityLiving.SpawnPlacementType.ON_GROUND);
/* 58 */     field_180110_a.put(EntitySheep.class, EntityLiving.SpawnPlacementType.ON_GROUND);
/* 59 */     field_180110_a.put(EntitySnowman.class, EntityLiving.SpawnPlacementType.ON_GROUND);
/* 60 */     field_180110_a.put(EntitySquid.class, EntityLiving.SpawnPlacementType.IN_WATER);
/* 61 */     field_180110_a.put(EntityIronGolem.class, EntityLiving.SpawnPlacementType.ON_GROUND);
/* 62 */     field_180110_a.put(EntityWolf.class, EntityLiving.SpawnPlacementType.ON_GROUND);
/* 63 */     field_180110_a.put(EntityVillager.class, EntityLiving.SpawnPlacementType.ON_GROUND);
/* 64 */     field_180110_a.put(EntityDragon.class, EntityLiving.SpawnPlacementType.ON_GROUND);
/* 65 */     field_180110_a.put(EntityWither.class, EntityLiving.SpawnPlacementType.ON_GROUND);
/* 66 */     field_180110_a.put(EntityBlaze.class, EntityLiving.SpawnPlacementType.ON_GROUND);
/* 67 */     field_180110_a.put(EntityCaveSpider.class, EntityLiving.SpawnPlacementType.ON_GROUND);
/* 68 */     field_180110_a.put(EntityCreeper.class, EntityLiving.SpawnPlacementType.ON_GROUND);
/* 69 */     field_180110_a.put(EntityEnderman.class, EntityLiving.SpawnPlacementType.ON_GROUND);
/* 70 */     field_180110_a.put(EntityEndermite.class, EntityLiving.SpawnPlacementType.ON_GROUND);
/* 71 */     field_180110_a.put(EntityGhast.class, EntityLiving.SpawnPlacementType.ON_GROUND);
/* 72 */     field_180110_a.put(EntityGiantZombie.class, EntityLiving.SpawnPlacementType.ON_GROUND);
/* 73 */     field_180110_a.put(EntityGuardian.class, EntityLiving.SpawnPlacementType.IN_WATER);
/* 74 */     field_180110_a.put(EntityMagmaCube.class, EntityLiving.SpawnPlacementType.ON_GROUND);
/* 75 */     field_180110_a.put(EntityPigZombie.class, EntityLiving.SpawnPlacementType.ON_GROUND);
/* 76 */     field_180110_a.put(EntitySilverfish.class, EntityLiving.SpawnPlacementType.ON_GROUND);
/* 77 */     field_180110_a.put(EntitySkeleton.class, EntityLiving.SpawnPlacementType.ON_GROUND);
/* 78 */     field_180110_a.put(EntitySlime.class, EntityLiving.SpawnPlacementType.ON_GROUND);
/* 79 */     field_180110_a.put(EntitySpider.class, EntityLiving.SpawnPlacementType.ON_GROUND);
/* 80 */     field_180110_a.put(EntityWitch.class, EntityLiving.SpawnPlacementType.ON_GROUND);
/* 81 */     field_180110_a.put(EntityZombie.class, EntityLiving.SpawnPlacementType.ON_GROUND);
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\entity\EntitySpawnPlacementRegistry.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */