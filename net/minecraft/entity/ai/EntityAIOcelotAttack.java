/*    */ package net.minecraft.entity.ai;
/*    */ 
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntityLiving;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ public class EntityAIOcelotAttack
/*    */   extends EntityAIBase {
/*    */   World theWorld;
/*    */   EntityLiving theEntity;
/*    */   EntityLivingBase theVictim;
/*    */   int attackCountdown;
/*    */   private static final String __OBFID = "CL_00001600";
/*    */   
/*    */   public EntityAIOcelotAttack(EntityLiving p_i1641_1_) {
/* 17 */     this.theEntity = p_i1641_1_;
/* 18 */     this.theWorld = p_i1641_1_.worldObj;
/* 19 */     setMutexBits(3);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean shouldExecute() {
/* 27 */     EntityLivingBase var1 = this.theEntity.getAttackTarget();
/*    */     
/* 29 */     if (var1 == null)
/*    */     {
/* 31 */       return false;
/*    */     }
/*    */ 
/*    */     
/* 35 */     this.theVictim = var1;
/* 36 */     return true;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean continueExecuting() {
/* 45 */     return !this.theVictim.isEntityAlive() ? false : ((this.theEntity.getDistanceSqToEntity((Entity)this.theVictim) > 225.0D) ? false : (!(this.theEntity.getNavigator().noPath() && !shouldExecute())));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void resetTask() {
/* 53 */     this.theVictim = null;
/* 54 */     this.theEntity.getNavigator().clearPathEntity();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void updateTask() {
/* 62 */     this.theEntity.getLookHelper().setLookPositionWithEntity((Entity)this.theVictim, 30.0F, 30.0F);
/* 63 */     double var1 = (this.theEntity.width * 2.0F * this.theEntity.width * 2.0F);
/* 64 */     double var3 = this.theEntity.getDistanceSq(this.theVictim.posX, (this.theVictim.getEntityBoundingBox()).minY, this.theVictim.posZ);
/* 65 */     double var5 = 0.8D;
/*    */     
/* 67 */     if (var3 > var1 && var3 < 16.0D) {
/*    */       
/* 69 */       var5 = 1.33D;
/*    */     }
/* 71 */     else if (var3 < 225.0D) {
/*    */       
/* 73 */       var5 = 0.6D;
/*    */     } 
/*    */     
/* 76 */     this.theEntity.getNavigator().tryMoveToEntityLiving((Entity)this.theVictim, var5);
/* 77 */     this.attackCountdown = Math.max(this.attackCountdown - 1, 0);
/*    */     
/* 79 */     if (var3 <= var1)
/*    */     {
/* 81 */       if (this.attackCountdown <= 0) {
/*    */         
/* 83 */         this.attackCountdown = 20;
/* 84 */         this.theEntity.attackEntityAsMob((Entity)this.theVictim);
/*    */       } 
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\entity\ai\EntityAIOcelotAttack.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */