/*    */ package net.minecraft.entity.ai;
/*    */ 
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.monster.EntityIronGolem;
/*    */ import net.minecraft.entity.passive.EntityVillager;
/*    */ 
/*    */ public class EntityAILookAtVillager
/*    */   extends EntityAIBase {
/*    */   private EntityIronGolem theGolem;
/*    */   private EntityVillager theVillager;
/*    */   private int lookTime;
/*    */   private static final String __OBFID = "CL_00001602";
/*    */   
/*    */   public EntityAILookAtVillager(EntityIronGolem p_i1643_1_) {
/* 15 */     this.theGolem = p_i1643_1_;
/* 16 */     setMutexBits(3);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean shouldExecute() {
/* 24 */     if (!this.theGolem.worldObj.isDaytime())
/*    */     {
/* 26 */       return false;
/*    */     }
/* 28 */     if (this.theGolem.getRNG().nextInt(8000) != 0)
/*    */     {
/* 30 */       return false;
/*    */     }
/*    */ 
/*    */     
/* 34 */     this.theVillager = (EntityVillager)this.theGolem.worldObj.findNearestEntityWithinAABB(EntityVillager.class, this.theGolem.getEntityBoundingBox().expand(6.0D, 2.0D, 6.0D), (Entity)this.theGolem);
/* 35 */     return (this.theVillager != null);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean continueExecuting() {
/* 44 */     return (this.lookTime > 0);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void startExecuting() {
/* 52 */     this.lookTime = 400;
/* 53 */     this.theGolem.setHoldingRose(true);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void resetTask() {
/* 61 */     this.theGolem.setHoldingRose(false);
/* 62 */     this.theVillager = null;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void updateTask() {
/* 70 */     this.theGolem.getLookHelper().setLookPositionWithEntity((Entity)this.theVillager, 30.0F, 30.0F);
/* 71 */     this.lookTime--;
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\entity\ai\EntityAILookAtVillager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */