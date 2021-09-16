/*    */ package net.minecraft.entity.ai;
/*    */ 
/*    */ import net.minecraft.entity.EntityCreature;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.entity.passive.EntityTameable;
/*    */ 
/*    */ public class EntityAIOwnerHurtByTarget
/*    */   extends EntityAITarget {
/*    */   EntityTameable theDefendingTameable;
/*    */   EntityLivingBase theOwnerAttacker;
/*    */   private int field_142051_e;
/*    */   private static final String __OBFID = "CL_00001624";
/*    */   
/*    */   public EntityAIOwnerHurtByTarget(EntityTameable p_i1667_1_) {
/* 15 */     super((EntityCreature)p_i1667_1_, false);
/* 16 */     this.theDefendingTameable = p_i1667_1_;
/* 17 */     setMutexBits(1);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean shouldExecute() {
/* 25 */     if (!this.theDefendingTameable.isTamed())
/*    */     {
/* 27 */       return false;
/*    */     }
/*    */ 
/*    */     
/* 31 */     EntityLivingBase var1 = this.theDefendingTameable.func_180492_cm();
/*    */     
/* 33 */     if (var1 == null)
/*    */     {
/* 35 */       return false;
/*    */     }
/*    */ 
/*    */     
/* 39 */     this.theOwnerAttacker = var1.getAITarget();
/* 40 */     int var2 = var1.getRevengeTimer();
/* 41 */     return (var2 != this.field_142051_e && isSuitableTarget(this.theOwnerAttacker, false) && this.theDefendingTameable.func_142018_a(this.theOwnerAttacker, var1));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void startExecuting() {
/* 51 */     this.taskOwner.setAttackTarget(this.theOwnerAttacker);
/* 52 */     EntityLivingBase var1 = this.theDefendingTameable.func_180492_cm();
/*    */     
/* 54 */     if (var1 != null)
/*    */     {
/* 56 */       this.field_142051_e = var1.getRevengeTimer();
/*    */     }
/*    */     
/* 59 */     super.startExecuting();
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\entity\ai\EntityAIOwnerHurtByTarget.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */