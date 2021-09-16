/*    */ package net.minecraft.entity.ai;
/*    */ 
/*    */ import net.minecraft.entity.EntityLiving;
/*    */ 
/*    */ 
/*    */ public class EntityJumpHelper
/*    */ {
/*    */   private EntityLiving entity;
/*    */   protected boolean isJumping;
/*    */   private static final String __OBFID = "CL_00001571";
/*    */   
/*    */   public EntityJumpHelper(EntityLiving p_i1612_1_) {
/* 13 */     this.entity = p_i1612_1_;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setJumping() {
/* 18 */     this.isJumping = true;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void doJump() {
/* 26 */     this.entity.setJumping(this.isJumping);
/* 27 */     this.isJumping = false;
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\entity\ai\EntityJumpHelper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */