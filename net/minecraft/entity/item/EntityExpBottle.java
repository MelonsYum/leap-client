/*    */ package net.minecraft.entity.item;
/*    */ 
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.entity.projectile.EntityThrowable;
/*    */ import net.minecraft.util.BlockPos;
/*    */ import net.minecraft.util.MovingObjectPosition;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ public class EntityExpBottle
/*    */   extends EntityThrowable {
/*    */   private static final String __OBFID = "CL_00001726";
/*    */   
/*    */   public EntityExpBottle(World worldIn) {
/* 15 */     super(worldIn);
/*    */   }
/*    */ 
/*    */   
/*    */   public EntityExpBottle(World worldIn, EntityLivingBase p_i1786_2_) {
/* 20 */     super(worldIn, p_i1786_2_);
/*    */   }
/*    */ 
/*    */   
/*    */   public EntityExpBottle(World worldIn, double p_i1787_2_, double p_i1787_4_, double p_i1787_6_) {
/* 25 */     super(worldIn, p_i1787_2_, p_i1787_4_, p_i1787_6_);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected float getGravityVelocity() {
/* 33 */     return 0.07F;
/*    */   }
/*    */ 
/*    */   
/*    */   protected float func_70182_d() {
/* 38 */     return 0.7F;
/*    */   }
/*    */ 
/*    */   
/*    */   protected float func_70183_g() {
/* 43 */     return -20.0F;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected void onImpact(MovingObjectPosition p_70184_1_) {
/* 51 */     if (!this.worldObj.isRemote) {
/*    */       
/* 53 */       this.worldObj.playAuxSFX(2002, new BlockPos((Entity)this), 0);
/* 54 */       int var2 = 3 + this.worldObj.rand.nextInt(5) + this.worldObj.rand.nextInt(5);
/*    */       
/* 56 */       while (var2 > 0) {
/*    */         
/* 58 */         int var3 = EntityXPOrb.getXPSplit(var2);
/* 59 */         var2 -= var3;
/* 60 */         this.worldObj.spawnEntityInWorld(new EntityXPOrb(this.worldObj, this.posX, this.posY, this.posZ, var3));
/*    */       } 
/*    */       
/* 63 */       setDead();
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\entity\item\EntityExpBottle.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */