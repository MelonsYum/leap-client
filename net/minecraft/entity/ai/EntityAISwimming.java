/*    */ package net.minecraft.entity.ai;
/*    */ 
/*    */ import net.minecraft.entity.EntityLiving;
/*    */ import net.minecraft.pathfinding.PathNavigateGround;
/*    */ 
/*    */ public class EntityAISwimming
/*    */   extends EntityAIBase
/*    */ {
/*    */   private EntityLiving theEntity;
/*    */   private static final String __OBFID = "CL_00001584";
/*    */   
/*    */   public EntityAISwimming(EntityLiving p_i1624_1_) {
/* 13 */     this.theEntity = p_i1624_1_;
/* 14 */     setMutexBits(4);
/* 15 */     ((PathNavigateGround)p_i1624_1_.getNavigator()).func_179693_d(true);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean shouldExecute() {
/* 23 */     return !(!this.theEntity.isInWater() && !this.theEntity.func_180799_ab());
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void updateTask() {
/* 31 */     if (this.theEntity.getRNG().nextFloat() < 0.8F)
/*    */     {
/* 33 */       this.theEntity.getJumpHelper().setJumping();
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\entity\ai\EntityAISwimming.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */