/*    */ package net.minecraft.entity.ai;
/*    */ 
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntityCreature;
/*    */ import net.minecraft.pathfinding.PathNavigateGround;
/*    */ import net.minecraft.util.BlockPos;
/*    */ import net.minecraft.village.Village;
/*    */ import net.minecraft.village.VillageDoorInfo;
/*    */ 
/*    */ public class EntityAIRestrictOpenDoor
/*    */   extends EntityAIBase {
/*    */   private EntityCreature entityObj;
/*    */   private VillageDoorInfo frontDoor;
/*    */   private static final String __OBFID = "CL_00001610";
/*    */   
/*    */   public EntityAIRestrictOpenDoor(EntityCreature p_i1651_1_) {
/* 17 */     this.entityObj = p_i1651_1_;
/*    */     
/* 19 */     if (!(p_i1651_1_.getNavigator() instanceof PathNavigateGround))
/*    */     {
/* 21 */       throw new IllegalArgumentException("Unsupported mob type for RestrictOpenDoorGoal");
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean shouldExecute() {
/* 30 */     if (this.entityObj.worldObj.isDaytime())
/*    */     {
/* 32 */       return false;
/*    */     }
/*    */ 
/*    */     
/* 36 */     BlockPos var1 = new BlockPos((Entity)this.entityObj);
/* 37 */     Village var2 = this.entityObj.worldObj.getVillageCollection().func_176056_a(var1, 16);
/*    */     
/* 39 */     if (var2 == null)
/*    */     {
/* 41 */       return false;
/*    */     }
/*    */ 
/*    */     
/* 45 */     this.frontDoor = var2.func_179865_b(var1);
/* 46 */     return (this.frontDoor == null) ? false : ((this.frontDoor.func_179846_b(var1) < 2.25D));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean continueExecuting() {
/* 56 */     return this.entityObj.worldObj.isDaytime() ? false : ((!this.frontDoor.func_179851_i() && this.frontDoor.func_179850_c(new BlockPos((Entity)this.entityObj))));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void startExecuting() {
/* 64 */     ((PathNavigateGround)this.entityObj.getNavigator()).func_179688_b(false);
/* 65 */     ((PathNavigateGround)this.entityObj.getNavigator()).func_179691_c(false);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void resetTask() {
/* 73 */     ((PathNavigateGround)this.entityObj.getNavigator()).func_179688_b(true);
/* 74 */     ((PathNavigateGround)this.entityObj.getNavigator()).func_179691_c(true);
/* 75 */     this.frontDoor = null;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void updateTask() {
/* 83 */     this.frontDoor.incrementDoorOpeningRestrictionCounter();
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\entity\ai\EntityAIRestrictOpenDoor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */