/*    */ package net.minecraft.entity.ai;
/*    */ 
/*    */ import net.minecraft.entity.EntityCreature;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.entity.passive.EntityTameable;
/*    */ 
/*    */ public class EntityAIOwnerHurtTarget
/*    */   extends EntityAITarget {
/*    */   EntityTameable theEntityTameable;
/*    */   EntityLivingBase theTarget;
/*    */   private int field_142050_e;
/*    */   private static final String __OBFID = "CL_00001625";
/*    */   
/*    */   public EntityAIOwnerHurtTarget(EntityTameable p_i1668_1_) {
/* 15 */     super((EntityCreature)p_i1668_1_, false);
/* 16 */     this.theEntityTameable = p_i1668_1_;
/* 17 */     setMutexBits(1);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean shouldExecute() {
/* 25 */     if (!this.theEntityTameable.isTamed())
/*    */     {
/* 27 */       return false;
/*    */     }
/*    */ 
/*    */     
/* 31 */     EntityLivingBase var1 = this.theEntityTameable.func_180492_cm();
/*    */     
/* 33 */     if (var1 == null)
/*    */     {
/* 35 */       return false;
/*    */     }
/*    */ 
/*    */     
/* 39 */     this.theTarget = var1.getLastAttacker();
/* 40 */     int var2 = var1.getLastAttackerTime();
/* 41 */     return (var2 != this.field_142050_e && isSuitableTarget(this.theTarget, false) && this.theEntityTameable.func_142018_a(this.theTarget, var1));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void startExecuting() {
/* 51 */     this.taskOwner.setAttackTarget(this.theTarget);
/* 52 */     EntityLivingBase var1 = this.theEntityTameable.func_180492_cm();
/*    */     
/* 54 */     if (var1 != null)
/*    */     {
/* 56 */       this.field_142050_e = var1.getLastAttackerTime();
/*    */     }
/*    */     
/* 59 */     super.startExecuting();
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\entity\ai\EntityAIOwnerHurtTarget.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */