/*    */ package net.minecraft.entity.ai;
/*    */ 
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntityCreature;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.util.Vec3;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class EntityAIMoveTowardsTarget
/*    */   extends EntityAIBase
/*    */ {
/*    */   private EntityCreature theEntity;
/*    */   private EntityLivingBase targetEntity;
/*    */   private double movePosX;
/*    */   private double movePosY;
/*    */   private double movePosZ;
/*    */   private double speed;
/*    */   private float maxTargetDistance;
/*    */   private static final String __OBFID = "CL_00001599";
/*    */   
/*    */   public EntityAIMoveTowardsTarget(EntityCreature p_i1640_1_, double p_i1640_2_, float p_i1640_4_) {
/* 24 */     this.theEntity = p_i1640_1_;
/* 25 */     this.speed = p_i1640_2_;
/* 26 */     this.maxTargetDistance = p_i1640_4_;
/* 27 */     setMutexBits(1);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean shouldExecute() {
/* 35 */     this.targetEntity = this.theEntity.getAttackTarget();
/*    */     
/* 37 */     if (this.targetEntity == null)
/*    */     {
/* 39 */       return false;
/*    */     }
/* 41 */     if (this.targetEntity.getDistanceSqToEntity((Entity)this.theEntity) > (this.maxTargetDistance * this.maxTargetDistance))
/*    */     {
/* 43 */       return false;
/*    */     }
/*    */ 
/*    */     
/* 47 */     Vec3 var1 = RandomPositionGenerator.findRandomTargetBlockTowards(this.theEntity, 16, 7, new Vec3(this.targetEntity.posX, this.targetEntity.posY, this.targetEntity.posZ));
/*    */     
/* 49 */     if (var1 == null)
/*    */     {
/* 51 */       return false;
/*    */     }
/*    */ 
/*    */     
/* 55 */     this.movePosX = var1.xCoord;
/* 56 */     this.movePosY = var1.yCoord;
/* 57 */     this.movePosZ = var1.zCoord;
/* 58 */     return true;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean continueExecuting() {
/* 68 */     return (!this.theEntity.getNavigator().noPath() && this.targetEntity.isEntityAlive() && this.targetEntity.getDistanceSqToEntity((Entity)this.theEntity) < (this.maxTargetDistance * this.maxTargetDistance));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void resetTask() {
/* 76 */     this.targetEntity = null;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void startExecuting() {
/* 84 */     this.theEntity.getNavigator().tryMoveToXYZ(this.movePosX, this.movePosY, this.movePosZ, this.speed);
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\entity\ai\EntityAIMoveTowardsTarget.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */