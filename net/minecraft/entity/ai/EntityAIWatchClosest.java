/*    */ package net.minecraft.entity.ai;
/*    */ 
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntityLiving;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class EntityAIWatchClosest
/*    */   extends EntityAIBase
/*    */ {
/*    */   protected EntityLiving theWatcher;
/*    */   protected Entity closestEntity;
/*    */   protected float maxDistanceForPlayer;
/*    */   private int lookTime;
/*    */   private float field_75331_e;
/*    */   protected Class watchedClass;
/*    */   private static final String __OBFID = "CL_00001592";
/*    */   
/*    */   public EntityAIWatchClosest(EntityLiving p_i1631_1_, Class p_i1631_2_, float p_i1631_3_) {
/* 23 */     this.theWatcher = p_i1631_1_;
/* 24 */     this.watchedClass = p_i1631_2_;
/* 25 */     this.maxDistanceForPlayer = p_i1631_3_;
/* 26 */     this.field_75331_e = 0.02F;
/* 27 */     setMutexBits(2);
/*    */   }
/*    */ 
/*    */   
/*    */   public EntityAIWatchClosest(EntityLiving p_i1632_1_, Class p_i1632_2_, float p_i1632_3_, float p_i1632_4_) {
/* 32 */     this.theWatcher = p_i1632_1_;
/* 33 */     this.watchedClass = p_i1632_2_;
/* 34 */     this.maxDistanceForPlayer = p_i1632_3_;
/* 35 */     this.field_75331_e = p_i1632_4_;
/* 36 */     setMutexBits(2);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean shouldExecute() {
/* 44 */     if (this.theWatcher.getRNG().nextFloat() >= this.field_75331_e)
/*    */     {
/* 46 */       return false;
/*    */     }
/*    */ 
/*    */     
/* 50 */     if (this.theWatcher.getAttackTarget() != null)
/*    */     {
/* 52 */       this.closestEntity = (Entity)this.theWatcher.getAttackTarget();
/*    */     }
/*    */     
/* 55 */     if (this.watchedClass == EntityPlayer.class) {
/*    */       
/* 57 */       this.closestEntity = (Entity)this.theWatcher.worldObj.getClosestPlayerToEntity((Entity)this.theWatcher, this.maxDistanceForPlayer);
/*    */     }
/*    */     else {
/*    */       
/* 61 */       this.closestEntity = this.theWatcher.worldObj.findNearestEntityWithinAABB(this.watchedClass, this.theWatcher.getEntityBoundingBox().expand(this.maxDistanceForPlayer, 3.0D, this.maxDistanceForPlayer), (Entity)this.theWatcher);
/*    */     } 
/*    */     
/* 64 */     return (this.closestEntity != null);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean continueExecuting() {
/* 73 */     return !this.closestEntity.isEntityAlive() ? false : ((this.theWatcher.getDistanceSqToEntity(this.closestEntity) > (this.maxDistanceForPlayer * this.maxDistanceForPlayer)) ? false : ((this.lookTime > 0)));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void startExecuting() {
/* 81 */     this.lookTime = 40 + this.theWatcher.getRNG().nextInt(40);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void resetTask() {
/* 89 */     this.closestEntity = null;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void updateTask() {
/* 97 */     this.theWatcher.getLookHelper().setLookPosition(this.closestEntity.posX, this.closestEntity.posY + this.closestEntity.getEyeHeight(), this.closestEntity.posZ, 10.0F, this.theWatcher.getVerticalFaceSpeed());
/* 98 */     this.lookTime--;
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\entity\ai\EntityAIWatchClosest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */