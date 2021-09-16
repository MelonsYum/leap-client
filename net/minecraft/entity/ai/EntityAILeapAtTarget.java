/*    */ package net.minecraft.entity.ai;
/*    */ 
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntityLiving;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.util.MathHelper;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class EntityAILeapAtTarget
/*    */   extends EntityAIBase
/*    */ {
/*    */   EntityLiving leaper;
/*    */   EntityLivingBase leapTarget;
/*    */   float leapMotionY;
/*    */   private static final String __OBFID = "CL_00001591";
/*    */   
/*    */   public EntityAILeapAtTarget(EntityLiving p_i1630_1_, float p_i1630_2_) {
/* 21 */     this.leaper = p_i1630_1_;
/* 22 */     this.leapMotionY = p_i1630_2_;
/* 23 */     setMutexBits(5);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean shouldExecute() {
/* 31 */     this.leapTarget = this.leaper.getAttackTarget();
/*    */     
/* 33 */     if (this.leapTarget == null)
/*    */     {
/* 35 */       return false;
/*    */     }
/*    */ 
/*    */     
/* 39 */     double var1 = this.leaper.getDistanceSqToEntity((Entity)this.leapTarget);
/* 40 */     return (var1 >= 4.0D && var1 <= 16.0D) ? (!this.leaper.onGround ? false : ((this.leaper.getRNG().nextInt(5) == 0))) : false;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean continueExecuting() {
/* 49 */     return !this.leaper.onGround;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void startExecuting() {
/* 57 */     double var1 = this.leapTarget.posX - this.leaper.posX;
/* 58 */     double var3 = this.leapTarget.posZ - this.leaper.posZ;
/* 59 */     float var5 = MathHelper.sqrt_double(var1 * var1 + var3 * var3);
/* 60 */     this.leaper.motionX += var1 / var5 * 0.5D * 0.800000011920929D + this.leaper.motionX * 0.20000000298023224D;
/* 61 */     this.leaper.motionZ += var3 / var5 * 0.5D * 0.800000011920929D + this.leaper.motionZ * 0.20000000298023224D;
/* 62 */     this.leaper.motionY = this.leapMotionY;
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\entity\ai\EntityAILeapAtTarget.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */