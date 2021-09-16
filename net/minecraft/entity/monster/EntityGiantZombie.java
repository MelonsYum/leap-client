/*    */ package net.minecraft.entity.monster;
/*    */ 
/*    */ import net.minecraft.entity.SharedMonsterAttributes;
/*    */ import net.minecraft.util.BlockPos;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ public class EntityGiantZombie
/*    */   extends EntityMob
/*    */ {
/*    */   private static final String __OBFID = "CL_00001690";
/*    */   
/*    */   public EntityGiantZombie(World worldIn) {
/* 13 */     super(worldIn);
/* 14 */     setSize(this.width * 6.0F, this.height * 6.0F);
/*    */   }
/*    */ 
/*    */   
/*    */   public float getEyeHeight() {
/* 19 */     return 10.440001F;
/*    */   }
/*    */ 
/*    */   
/*    */   protected void applyEntityAttributes() {
/* 24 */     super.applyEntityAttributes();
/* 25 */     getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(100.0D);
/* 26 */     getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.5D);
/* 27 */     getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(50.0D);
/*    */   }
/*    */ 
/*    */   
/*    */   public float func_180484_a(BlockPos p_180484_1_) {
/* 32 */     return this.worldObj.getLightBrightness(p_180484_1_) - 0.5F;
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\entity\monster\EntityGiantZombie.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */