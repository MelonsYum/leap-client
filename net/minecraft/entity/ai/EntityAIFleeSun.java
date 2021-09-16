/*    */ package net.minecraft.entity.ai;
/*    */ 
/*    */ import java.util.Random;
/*    */ import net.minecraft.entity.EntityCreature;
/*    */ import net.minecraft.util.BlockPos;
/*    */ import net.minecraft.util.Vec3;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ public class EntityAIFleeSun
/*    */   extends EntityAIBase
/*    */ {
/*    */   private EntityCreature theCreature;
/*    */   private double shelterX;
/*    */   private double shelterY;
/*    */   private double shelterZ;
/*    */   private double movementSpeed;
/*    */   private World theWorld;
/*    */   private static final String __OBFID = "CL_00001583";
/*    */   
/*    */   public EntityAIFleeSun(EntityCreature p_i1623_1_, double p_i1623_2_) {
/* 21 */     this.theCreature = p_i1623_1_;
/* 22 */     this.movementSpeed = p_i1623_2_;
/* 23 */     this.theWorld = p_i1623_1_.worldObj;
/* 24 */     setMutexBits(1);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean shouldExecute() {
/* 32 */     if (!this.theWorld.isDaytime())
/*    */     {
/* 34 */       return false;
/*    */     }
/* 36 */     if (!this.theCreature.isBurning())
/*    */     {
/* 38 */       return false;
/*    */     }
/* 40 */     if (!this.theWorld.isAgainstSky(new BlockPos(this.theCreature.posX, (this.theCreature.getEntityBoundingBox()).minY, this.theCreature.posZ)))
/*    */     {
/* 42 */       return false;
/*    */     }
/*    */ 
/*    */     
/* 46 */     Vec3 var1 = findPossibleShelter();
/*    */     
/* 48 */     if (var1 == null)
/*    */     {
/* 50 */       return false;
/*    */     }
/*    */ 
/*    */     
/* 54 */     this.shelterX = var1.xCoord;
/* 55 */     this.shelterY = var1.yCoord;
/* 56 */     this.shelterZ = var1.zCoord;
/* 57 */     return true;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean continueExecuting() {
/* 67 */     return !this.theCreature.getNavigator().noPath();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void startExecuting() {
/* 75 */     this.theCreature.getNavigator().tryMoveToXYZ(this.shelterX, this.shelterY, this.shelterZ, this.movementSpeed);
/*    */   }
/*    */ 
/*    */   
/*    */   private Vec3 findPossibleShelter() {
/* 80 */     Random var1 = this.theCreature.getRNG();
/* 81 */     BlockPos var2 = new BlockPos(this.theCreature.posX, (this.theCreature.getEntityBoundingBox()).minY, this.theCreature.posZ);
/*    */     
/* 83 */     for (int var3 = 0; var3 < 10; var3++) {
/*    */       
/* 85 */       BlockPos var4 = var2.add(var1.nextInt(20) - 10, var1.nextInt(6) - 3, var1.nextInt(20) - 10);
/*    */       
/* 87 */       if (!this.theWorld.isAgainstSky(var4) && this.theCreature.func_180484_a(var4) < 0.0F)
/*    */       {
/* 89 */         return new Vec3(var4.getX(), var4.getY(), var4.getZ());
/*    */       }
/*    */     } 
/*    */     
/* 93 */     return null;
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\entity\ai\EntityAIFleeSun.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */