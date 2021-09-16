/*    */ package net.minecraft.entity.ai;
/*    */ 
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.entity.monster.EntityCreeper;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class EntityAICreeperSwell
/*    */   extends EntityAIBase
/*    */ {
/*    */   EntityCreeper swellingCreeper;
/*    */   EntityLivingBase creeperAttackTarget;
/*    */   private static final String __OBFID = "CL_00001614";
/*    */   
/*    */   public EntityAICreeperSwell(EntityCreeper p_i1655_1_) {
/* 19 */     this.swellingCreeper = p_i1655_1_;
/* 20 */     setMutexBits(1);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean shouldExecute() {
/* 28 */     EntityLivingBase var1 = this.swellingCreeper.getAttackTarget();
/* 29 */     return !(this.swellingCreeper.getCreeperState() <= 0 && (var1 == null || this.swellingCreeper.getDistanceSqToEntity((Entity)var1) >= 9.0D));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void startExecuting() {
/* 37 */     this.swellingCreeper.getNavigator().clearPathEntity();
/* 38 */     this.creeperAttackTarget = this.swellingCreeper.getAttackTarget();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void resetTask() {
/* 46 */     this.creeperAttackTarget = null;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void updateTask() {
/* 54 */     if (this.creeperAttackTarget == null) {
/*    */       
/* 56 */       this.swellingCreeper.setCreeperState(-1);
/*    */     }
/* 58 */     else if (this.swellingCreeper.getDistanceSqToEntity((Entity)this.creeperAttackTarget) > 49.0D) {
/*    */       
/* 60 */       this.swellingCreeper.setCreeperState(-1);
/*    */     }
/* 62 */     else if (!this.swellingCreeper.getEntitySenses().canSee((Entity)this.creeperAttackTarget)) {
/*    */       
/* 64 */       this.swellingCreeper.setCreeperState(-1);
/*    */     }
/*    */     else {
/*    */       
/* 68 */       this.swellingCreeper.setCreeperState(1);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\entity\ai\EntityAICreeperSwell.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */