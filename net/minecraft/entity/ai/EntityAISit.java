/*    */ package net.minecraft.entity.ai;
/*    */ 
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.entity.passive.EntityTameable;
/*    */ 
/*    */ 
/*    */ public class EntityAISit
/*    */   extends EntityAIBase
/*    */ {
/*    */   private EntityTameable theEntity;
/*    */   private boolean isSitting;
/*    */   private static final String __OBFID = "CL_00001613";
/*    */   
/*    */   public EntityAISit(EntityTameable p_i1654_1_) {
/* 16 */     this.theEntity = p_i1654_1_;
/* 17 */     setMutexBits(5);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean shouldExecute() {
/* 25 */     if (!this.theEntity.isTamed())
/*    */     {
/* 27 */       return false;
/*    */     }
/* 29 */     if (this.theEntity.isInWater())
/*    */     {
/* 31 */       return false;
/*    */     }
/* 33 */     if (!this.theEntity.onGround)
/*    */     {
/* 35 */       return false;
/*    */     }
/*    */ 
/*    */     
/* 39 */     EntityLivingBase var1 = this.theEntity.func_180492_cm();
/* 40 */     return (var1 == null) ? true : ((this.theEntity.getDistanceSqToEntity((Entity)var1) < 144.0D && var1.getAITarget() != null) ? false : this.isSitting);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void startExecuting() {
/* 49 */     this.theEntity.getNavigator().clearPathEntity();
/* 50 */     this.theEntity.setSitting(true);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void resetTask() {
/* 58 */     this.theEntity.setSitting(false);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setSitting(boolean p_75270_1_) {
/* 66 */     this.isSitting = p_75270_1_;
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\entity\ai\EntityAISit.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */