/*    */ package net.minecraft.entity.passive;
/*    */ 
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntityLiving;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.util.DamageSource;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ public abstract class EntityWaterMob
/*    */   extends EntityLiving implements IAnimals {
/*    */   private static final String __OBFID = "CL_00001653";
/*    */   
/*    */   public EntityWaterMob(World worldIn) {
/* 14 */     super(worldIn);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean canBreatheUnderwater() {
/* 19 */     return true;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean getCanSpawnHere() {
/* 27 */     return true;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean handleLavaMovement() {
/* 35 */     return this.worldObj.checkNoEntityCollision(getEntityBoundingBox(), (Entity)this);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int getTalkInterval() {
/* 43 */     return 120;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected boolean canDespawn() {
/* 51 */     return true;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected int getExperiencePoints(EntityPlayer p_70693_1_) {
/* 59 */     return 1 + this.worldObj.rand.nextInt(3);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void onEntityUpdate() {
/* 67 */     int var1 = getAir();
/* 68 */     super.onEntityUpdate();
/*    */     
/* 70 */     if (isEntityAlive() && !isInWater()) {
/*    */       
/* 72 */       var1--;
/* 73 */       setAir(var1);
/*    */       
/* 75 */       if (getAir() == -20)
/*    */       {
/* 77 */         setAir(0);
/* 78 */         attackEntityFrom(DamageSource.drown, 2.0F);
/*    */       }
/*    */     
/*    */     } else {
/*    */       
/* 83 */       setAir(300);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isPushedByWater() {
/* 89 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\entity\passive\EntityWaterMob.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */