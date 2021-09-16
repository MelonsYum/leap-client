/*    */ package net.minecraft.pathfinding;
/*    */ 
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntityLiving;
/*    */ import net.minecraft.util.MovingObjectPosition;
/*    */ import net.minecraft.util.Vec3;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraft.world.pathfinder.NodeProcessor;
/*    */ import net.minecraft.world.pathfinder.SwimNodeProcessor;
/*    */ 
/*    */ public class PathNavigateSwimmer extends PathNavigate {
/*    */   private static final String __OBFID = "CL_00002244";
/*    */   
/*    */   public PathNavigateSwimmer(EntityLiving p_i45873_1_, World worldIn) {
/* 15 */     super(p_i45873_1_, worldIn);
/*    */   }
/*    */ 
/*    */   
/*    */   protected PathFinder func_179679_a() {
/* 20 */     return new PathFinder((NodeProcessor)new SwimNodeProcessor());
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected boolean canNavigate() {
/* 28 */     return isInLiquid();
/*    */   }
/*    */ 
/*    */   
/*    */   protected Vec3 getEntityPosition() {
/* 33 */     return new Vec3(this.theEntity.posX, this.theEntity.posY + this.theEntity.height * 0.5D, this.theEntity.posZ);
/*    */   }
/*    */ 
/*    */   
/*    */   protected void pathFollow() {
/* 38 */     Vec3 var1 = getEntityPosition();
/* 39 */     float var2 = this.theEntity.width * this.theEntity.width;
/* 40 */     byte var3 = 6;
/*    */     
/* 42 */     if (var1.squareDistanceTo(this.currentPath.getVectorFromIndex((Entity)this.theEntity, this.currentPath.getCurrentPathIndex())) < var2)
/*    */     {
/* 44 */       this.currentPath.incrementPathIndex();
/*    */     }
/*    */     
/* 47 */     for (int var4 = Math.min(this.currentPath.getCurrentPathIndex() + var3, this.currentPath.getCurrentPathLength() - 1); var4 > this.currentPath.getCurrentPathIndex(); var4--) {
/*    */       
/* 49 */       Vec3 var5 = this.currentPath.getVectorFromIndex((Entity)this.theEntity, var4);
/*    */       
/* 51 */       if (var5.squareDistanceTo(var1) <= 36.0D && isDirectPathBetweenPoints(var1, var5, 0, 0, 0)) {
/*    */         
/* 53 */         this.currentPath.setCurrentPathIndex(var4);
/*    */         
/*    */         break;
/*    */       } 
/*    */     } 
/* 58 */     func_179677_a(var1);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected void removeSunnyPath() {
/* 66 */     super.removeSunnyPath();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected boolean isDirectPathBetweenPoints(Vec3 p_75493_1_, Vec3 p_75493_2_, int p_75493_3_, int p_75493_4_, int p_75493_5_) {
/* 75 */     MovingObjectPosition var6 = this.worldObj.rayTraceBlocks(p_75493_1_, new Vec3(p_75493_2_.xCoord, p_75493_2_.yCoord + this.theEntity.height * 0.5D, p_75493_2_.zCoord), false, true, false);
/* 76 */     return !(var6 != null && var6.typeOfHit != MovingObjectPosition.MovingObjectType.MISS);
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\pathfinding\PathNavigateSwimmer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */