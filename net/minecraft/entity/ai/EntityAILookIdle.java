/*    */ package net.minecraft.entity.ai;
/*    */ 
/*    */ import net.minecraft.entity.EntityLiving;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class EntityAILookIdle
/*    */   extends EntityAIBase
/*    */ {
/*    */   private EntityLiving idleEntity;
/*    */   private double lookX;
/*    */   private double lookZ;
/*    */   private int idleTime;
/*    */   private static final String __OBFID = "CL_00001607";
/*    */   
/*    */   public EntityAILookIdle(EntityLiving p_i1647_1_) {
/* 24 */     this.idleEntity = p_i1647_1_;
/* 25 */     setMutexBits(3);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean shouldExecute() {
/* 33 */     return (this.idleEntity.getRNG().nextFloat() < 0.02F);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean continueExecuting() {
/* 41 */     return (this.idleTime >= 0);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void startExecuting() {
/* 49 */     double var1 = 6.283185307179586D * this.idleEntity.getRNG().nextDouble();
/* 50 */     this.lookX = Math.cos(var1);
/* 51 */     this.lookZ = Math.sin(var1);
/* 52 */     this.idleTime = 20 + this.idleEntity.getRNG().nextInt(20);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void updateTask() {
/* 60 */     this.idleTime--;
/* 61 */     this.idleEntity.getLookHelper().setLookPosition(this.idleEntity.posX + this.lookX, this.idleEntity.posY + this.idleEntity.getEyeHeight(), this.idleEntity.posZ + this.lookZ, 10.0F, this.idleEntity.getVerticalFaceSpeed());
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\entity\ai\EntityAILookIdle.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */