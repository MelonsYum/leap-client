/*    */ package net.minecraft.entity;
/*    */ 
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.util.BlockPos;
/*    */ import net.minecraft.util.MathHelper;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ public abstract class EntityFlying
/*    */   extends EntityLiving
/*    */ {
/*    */   private static final String __OBFID = "CL_00001545";
/*    */   
/*    */   public EntityFlying(World worldIn) {
/* 14 */     super(worldIn);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void fall(float distance, float damageMultiplier) {}
/*    */ 
/*    */   
/*    */   protected void func_180433_a(double p_180433_1_, boolean p_180433_3_, Block p_180433_4_, BlockPos p_180433_5_) {}
/*    */ 
/*    */   
/*    */   public void moveEntityWithHeading(float p_70612_1_, float p_70612_2_) {
/* 26 */     if (isInWater()) {
/*    */       
/* 28 */       moveFlying(p_70612_1_, p_70612_2_, 0.02F);
/* 29 */       moveEntity(this.motionX, this.motionY, this.motionZ);
/* 30 */       this.motionX *= 0.800000011920929D;
/* 31 */       this.motionY *= 0.800000011920929D;
/* 32 */       this.motionZ *= 0.800000011920929D;
/*    */     }
/* 34 */     else if (func_180799_ab()) {
/*    */       
/* 36 */       moveFlying(p_70612_1_, p_70612_2_, 0.02F);
/* 37 */       moveEntity(this.motionX, this.motionY, this.motionZ);
/* 38 */       this.motionX *= 0.5D;
/* 39 */       this.motionY *= 0.5D;
/* 40 */       this.motionZ *= 0.5D;
/*    */     }
/*    */     else {
/*    */       
/* 44 */       float var3 = 0.91F;
/*    */       
/* 46 */       if (this.onGround)
/*    */       {
/* 48 */         var3 = (this.worldObj.getBlockState(new BlockPos(MathHelper.floor_double(this.posX), MathHelper.floor_double((getEntityBoundingBox()).minY) - 1, MathHelper.floor_double(this.posZ))).getBlock()).slipperiness * 0.91F;
/*    */       }
/*    */       
/* 51 */       float var4 = 0.16277136F / var3 * var3 * var3;
/* 52 */       moveFlying(p_70612_1_, p_70612_2_, this.onGround ? (0.1F * var4) : 0.02F);
/* 53 */       var3 = 0.91F;
/*    */       
/* 55 */       if (this.onGround)
/*    */       {
/* 57 */         var3 = (this.worldObj.getBlockState(new BlockPos(MathHelper.floor_double(this.posX), MathHelper.floor_double((getEntityBoundingBox()).minY) - 1, MathHelper.floor_double(this.posZ))).getBlock()).slipperiness * 0.91F;
/*    */       }
/*    */       
/* 60 */       moveEntity(this.motionX, this.motionY, this.motionZ);
/* 61 */       this.motionX *= var3;
/* 62 */       this.motionY *= var3;
/* 63 */       this.motionZ *= var3;
/*    */     } 
/*    */     
/* 66 */     this.prevLimbSwingAmount = this.limbSwingAmount;
/* 67 */     double var8 = this.posX - this.prevPosX;
/* 68 */     double var5 = this.posZ - this.prevPosZ;
/* 69 */     float var7 = MathHelper.sqrt_double(var8 * var8 + var5 * var5) * 4.0F;
/*    */     
/* 71 */     if (var7 > 1.0F)
/*    */     {
/* 73 */       var7 = 1.0F;
/*    */     }
/*    */     
/* 76 */     this.limbSwingAmount += (var7 - this.limbSwingAmount) * 0.4F;
/* 77 */     this.limbSwing += this.limbSwingAmount;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean isOnLadder() {
/* 85 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\entity\EntityFlying.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */