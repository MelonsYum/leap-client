/*    */ package net.minecraft.entity.ai;
/*    */ 
/*    */ import net.minecraft.entity.EntityCreature;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.entity.monster.EntityIronGolem;
/*    */ import net.minecraft.village.Village;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class EntityAIDefendVillage
/*    */   extends EntityAITarget
/*    */ {
/*    */   EntityIronGolem irongolem;
/*    */   EntityLivingBase villageAgressorTarget;
/*    */   private static final String __OBFID = "CL_00001618";
/*    */   
/*    */   public EntityAIDefendVillage(EntityIronGolem p_i1659_1_) {
/* 19 */     super((EntityCreature)p_i1659_1_, false, true);
/* 20 */     this.irongolem = p_i1659_1_;
/* 21 */     setMutexBits(1);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean shouldExecute() {
/* 29 */     Village var1 = this.irongolem.getVillage();
/*    */     
/* 31 */     if (var1 == null)
/*    */     {
/* 33 */       return false;
/*    */     }
/*    */ 
/*    */     
/* 37 */     this.villageAgressorTarget = var1.findNearestVillageAggressor((EntityLivingBase)this.irongolem);
/*    */     
/* 39 */     if (!isSuitableTarget(this.villageAgressorTarget, false)) {
/*    */       
/* 41 */       if (this.taskOwner.getRNG().nextInt(20) == 0) {
/*    */         
/* 43 */         this.villageAgressorTarget = (EntityLivingBase)var1.func_82685_c((EntityLivingBase)this.irongolem);
/* 44 */         return isSuitableTarget(this.villageAgressorTarget, false);
/*    */       } 
/*    */ 
/*    */       
/* 48 */       return false;
/*    */     } 
/*    */ 
/*    */ 
/*    */     
/* 53 */     return true;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void startExecuting() {
/* 63 */     this.irongolem.setAttackTarget(this.villageAgressorTarget);
/* 64 */     super.startExecuting();
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\entity\ai\EntityAIDefendVillage.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */