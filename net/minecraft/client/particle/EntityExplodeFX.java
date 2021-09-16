/*    */ package net.minecraft.client.particle;
/*    */ 
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ public class EntityExplodeFX
/*    */   extends EntityFX
/*    */ {
/*    */   private static final String __OBFID = "CL_00000903";
/*    */   
/*    */   protected EntityExplodeFX(World worldIn, double p_i1205_2_, double p_i1205_4_, double p_i1205_6_, double p_i1205_8_, double p_i1205_10_, double p_i1205_12_) {
/* 11 */     super(worldIn, p_i1205_2_, p_i1205_4_, p_i1205_6_, p_i1205_8_, p_i1205_10_, p_i1205_12_);
/* 12 */     this.motionX = p_i1205_8_ + (Math.random() * 2.0D - 1.0D) * 0.05000000074505806D;
/* 13 */     this.motionY = p_i1205_10_ + (Math.random() * 2.0D - 1.0D) * 0.05000000074505806D;
/* 14 */     this.motionZ = p_i1205_12_ + (Math.random() * 2.0D - 1.0D) * 0.05000000074505806D;
/* 15 */     this.particleRed = this.particleGreen = this.particleBlue = this.rand.nextFloat() * 0.3F + 0.7F;
/* 16 */     this.particleScale = this.rand.nextFloat() * this.rand.nextFloat() * 6.0F + 1.0F;
/* 17 */     this.particleMaxAge = (int)(16.0D / (this.rand.nextFloat() * 0.8D + 0.2D)) + 2;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void onUpdate() {
/* 25 */     this.prevPosX = this.posX;
/* 26 */     this.prevPosY = this.posY;
/* 27 */     this.prevPosZ = this.posZ;
/*    */     
/* 29 */     if (this.particleAge++ >= this.particleMaxAge)
/*    */     {
/* 31 */       setDead();
/*    */     }
/*    */     
/* 34 */     setParticleTextureIndex(7 - this.particleAge * 8 / this.particleMaxAge);
/* 35 */     this.motionY += 0.004D;
/* 36 */     moveEntity(this.motionX, this.motionY, this.motionZ);
/* 37 */     this.motionX *= 0.8999999761581421D;
/* 38 */     this.motionY *= 0.8999999761581421D;
/* 39 */     this.motionZ *= 0.8999999761581421D;
/*    */     
/* 41 */     if (this.onGround) {
/*    */       
/* 43 */       this.motionX *= 0.699999988079071D;
/* 44 */       this.motionZ *= 0.699999988079071D;
/*    */     } 
/*    */   }
/*    */   
/*    */   public static class Factory
/*    */     implements IParticleFactory
/*    */   {
/*    */     private static final String __OBFID = "CL_00002604";
/*    */     
/*    */     public EntityFX func_178902_a(int p_178902_1_, World worldIn, double p_178902_3_, double p_178902_5_, double p_178902_7_, double p_178902_9_, double p_178902_11_, double p_178902_13_, int... p_178902_15_) {
/* 54 */       return new EntityExplodeFX(worldIn, p_178902_3_, p_178902_5_, p_178902_7_, p_178902_9_, p_178902_11_, p_178902_13_);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\particle\EntityExplodeFX.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */