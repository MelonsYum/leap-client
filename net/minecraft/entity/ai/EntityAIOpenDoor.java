/*    */ package net.minecraft.entity.ai;
/*    */ 
/*    */ import net.minecraft.entity.EntityLiving;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class EntityAIOpenDoor
/*    */   extends EntityAIDoorInteract
/*    */ {
/*    */   boolean closeDoor;
/*    */   int closeDoorTemporisation;
/*    */   private static final String __OBFID = "CL_00001603";
/*    */   
/*    */   public EntityAIOpenDoor(EntityLiving p_i1644_1_, boolean p_i1644_2_) {
/* 18 */     super(p_i1644_1_);
/* 19 */     this.theEntity = p_i1644_1_;
/* 20 */     this.closeDoor = p_i1644_2_;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean continueExecuting() {
/* 28 */     return (this.closeDoor && this.closeDoorTemporisation > 0 && super.continueExecuting());
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void startExecuting() {
/* 36 */     this.closeDoorTemporisation = 20;
/* 37 */     this.doorBlock.func_176512_a(this.theEntity.worldObj, this.field_179507_b, true);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void resetTask() {
/* 45 */     if (this.closeDoor)
/*    */     {
/* 47 */       this.doorBlock.func_176512_a(this.theEntity.worldObj, this.field_179507_b, false);
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void updateTask() {
/* 56 */     this.closeDoorTemporisation--;
/* 57 */     super.updateTask();
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\entity\ai\EntityAIOpenDoor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */