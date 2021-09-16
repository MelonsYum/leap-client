/*    */ package net.minecraft.entity.ai;
/*    */ 
/*    */ import net.minecraft.entity.EntityCreature;
/*    */ import net.minecraft.util.Vec3;
/*    */ 
/*    */ public class EntityAIPanic
/*    */   extends EntityAIBase
/*    */ {
/*    */   private EntityCreature theEntityCreature;
/*    */   protected double speed;
/*    */   private double randPosX;
/*    */   private double randPosY;
/*    */   private double randPosZ;
/*    */   private static final String __OBFID = "CL_00001604";
/*    */   
/*    */   public EntityAIPanic(EntityCreature p_i1645_1_, double p_i1645_2_) {
/* 17 */     this.theEntityCreature = p_i1645_1_;
/* 18 */     this.speed = p_i1645_2_;
/* 19 */     setMutexBits(1);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean shouldExecute() {
/* 27 */     if (this.theEntityCreature.getAITarget() == null && !this.theEntityCreature.isBurning())
/*    */     {
/* 29 */       return false;
/*    */     }
/*    */ 
/*    */     
/* 33 */     Vec3 var1 = RandomPositionGenerator.findRandomTarget(this.theEntityCreature, 5, 4);
/*    */     
/* 35 */     if (var1 == null)
/*    */     {
/* 37 */       return false;
/*    */     }
/*    */ 
/*    */     
/* 41 */     this.randPosX = var1.xCoord;
/* 42 */     this.randPosY = var1.yCoord;
/* 43 */     this.randPosZ = var1.zCoord;
/* 44 */     return true;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void startExecuting() {
/* 54 */     this.theEntityCreature.getNavigator().tryMoveToXYZ(this.randPosX, this.randPosY, this.randPosZ, this.speed);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean continueExecuting() {
/* 62 */     return !this.theEntityCreature.getNavigator().noPath();
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\entity\ai\EntityAIPanic.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */