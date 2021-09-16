/*    */ package net.minecraft.entity;
/*    */ 
/*    */ import net.minecraft.block.material.Material;
/*    */ import net.minecraft.entity.monster.IMob;
/*    */ import net.minecraft.entity.passive.EntityAmbientCreature;
/*    */ import net.minecraft.entity.passive.EntityAnimal;
/*    */ import net.minecraft.entity.passive.EntityWaterMob;
/*    */ 
/*    */ public enum EnumCreatureType
/*    */ {
/* 11 */   MONSTER("MONSTER", 0, IMob.class, 70, Material.air, false, false),
/* 12 */   CREATURE("CREATURE", 1, EntityAnimal.class, 10, Material.air, true, true),
/* 13 */   AMBIENT("AMBIENT", 2, EntityAmbientCreature.class, 15, Material.air, true, false),
/* 14 */   WATER_CREATURE("WATER_CREATURE", 3, EntityWaterMob.class, 5, Material.water, true, false);
/*    */   
/*    */   private final Class creatureClass;
/*    */   
/*    */   private final int maxNumberOfCreature;
/*    */   
/*    */   private final Material creatureMaterial;
/*    */   
/*    */   private final boolean isPeacefulCreature;
/*    */   
/*    */   private final boolean isAnimal;
/*    */   
/*    */   private static final EnumCreatureType[] $VALUES;
/*    */   private static final String __OBFID = "CL_00001551";
/*    */   
/*    */   static {
/* 30 */     $VALUES = new EnumCreatureType[] { MONSTER, CREATURE, AMBIENT, WATER_CREATURE };
/*    */   }
/*    */ 
/*    */   
/*    */   EnumCreatureType(String p_i1596_1_, int p_i1596_2_, Class p_i1596_3_, int p_i1596_4_, Material p_i1596_5_, boolean p_i1596_6_, boolean p_i1596_7_) {
/* 35 */     this.creatureClass = p_i1596_3_;
/* 36 */     this.maxNumberOfCreature = p_i1596_4_;
/* 37 */     this.creatureMaterial = p_i1596_5_;
/* 38 */     this.isPeacefulCreature = p_i1596_6_;
/* 39 */     this.isAnimal = p_i1596_7_;
/*    */   }
/*    */ 
/*    */   
/*    */   public Class getCreatureClass() {
/* 44 */     return this.creatureClass;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getMaxNumberOfCreature() {
/* 49 */     return this.maxNumberOfCreature;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean getPeacefulCreature() {
/* 57 */     return this.isPeacefulCreature;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean getAnimal() {
/* 65 */     return this.isAnimal;
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\entity\EnumCreatureType.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */