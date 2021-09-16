/*    */ package net.minecraft.client.particle;
/*    */ 
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ public class EntityFishWakeFX
/*    */   extends EntityFX
/*    */ {
/*    */   private static final String __OBFID = "CL_00000933";
/*    */   
/*    */   protected EntityFishWakeFX(World worldIn, double p_i45073_2_, double p_i45073_4_, double p_i45073_6_, double p_i45073_8_, double p_i45073_10_, double p_i45073_12_) {
/* 11 */     super(worldIn, p_i45073_2_, p_i45073_4_, p_i45073_6_, 0.0D, 0.0D, 0.0D);
/* 12 */     this.motionX *= 0.30000001192092896D;
/* 13 */     this.motionY = Math.random() * 0.20000000298023224D + 0.10000000149011612D;
/* 14 */     this.motionZ *= 0.30000001192092896D;
/* 15 */     this.particleRed = 1.0F;
/* 16 */     this.particleGreen = 1.0F;
/* 17 */     this.particleBlue = 1.0F;
/* 18 */     setParticleTextureIndex(19);
/* 19 */     setSize(0.01F, 0.01F);
/* 20 */     this.particleMaxAge = (int)(8.0D / (Math.random() * 0.8D + 0.2D));
/* 21 */     this.particleGravity = 0.0F;
/* 22 */     this.motionX = p_i45073_8_;
/* 23 */     this.motionY = p_i45073_10_;
/* 24 */     this.motionZ = p_i45073_12_;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void onUpdate() {
/* 32 */     this.prevPosX = this.posX;
/* 33 */     this.prevPosY = this.posY;
/* 34 */     this.prevPosZ = this.posZ;
/* 35 */     this.motionY -= this.particleGravity;
/* 36 */     moveEntity(this.motionX, this.motionY, this.motionZ);
/* 37 */     this.motionX *= 0.9800000190734863D;
/* 38 */     this.motionY *= 0.9800000190734863D;
/* 39 */     this.motionZ *= 0.9800000190734863D;
/* 40 */     int var1 = 60 - this.particleMaxAge;
/* 41 */     float var2 = var1 * 0.001F;
/* 42 */     setSize(var2, var2);
/* 43 */     setParticleTextureIndex(19 + var1 % 4);
/*    */     
/* 45 */     if (this.particleMaxAge-- <= 0)
/*    */     {
/* 47 */       setDead();
/*    */     }
/*    */   }
/*    */   
/*    */   public static class Factory
/*    */     implements IParticleFactory
/*    */   {
/*    */     private static final String __OBFID = "CL_00002573";
/*    */     
/*    */     public EntityFX func_178902_a(int p_178902_1_, World worldIn, double p_178902_3_, double p_178902_5_, double p_178902_7_, double p_178902_9_, double p_178902_11_, double p_178902_13_, int... p_178902_15_) {
/* 57 */       return new EntityFishWakeFX(worldIn, p_178902_3_, p_178902_5_, p_178902_7_, p_178902_9_, p_178902_11_, p_178902_13_);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\particle\EntityFishWakeFX.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */