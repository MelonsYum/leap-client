/*    */ package net.minecraft.client.particle;
/*    */ 
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ public class EntityAuraFX
/*    */   extends EntityFX
/*    */ {
/*    */   private static final String __OBFID = "CL_00000929";
/*    */   
/*    */   protected EntityAuraFX(World worldIn, double p_i1232_2_, double p_i1232_4_, double p_i1232_6_, double p_i1232_8_, double p_i1232_10_, double p_i1232_12_) {
/* 11 */     super(worldIn, p_i1232_2_, p_i1232_4_, p_i1232_6_, p_i1232_8_, p_i1232_10_, p_i1232_12_);
/* 12 */     float var14 = this.rand.nextFloat() * 0.1F + 0.2F;
/* 13 */     this.particleRed = var14;
/* 14 */     this.particleGreen = var14;
/* 15 */     this.particleBlue = var14;
/* 16 */     setParticleTextureIndex(0);
/* 17 */     setSize(0.02F, 0.02F);
/* 18 */     this.particleScale *= this.rand.nextFloat() * 0.6F + 0.5F;
/* 19 */     this.motionX *= 0.019999999552965164D;
/* 20 */     this.motionY *= 0.019999999552965164D;
/* 21 */     this.motionZ *= 0.019999999552965164D;
/* 22 */     this.particleMaxAge = (int)(20.0D / (Math.random() * 0.8D + 0.2D));
/* 23 */     this.noClip = true;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void onUpdate() {
/* 31 */     this.prevPosX = this.posX;
/* 32 */     this.prevPosY = this.posY;
/* 33 */     this.prevPosZ = this.posZ;
/* 34 */     moveEntity(this.motionX, this.motionY, this.motionZ);
/* 35 */     this.motionX *= 0.99D;
/* 36 */     this.motionY *= 0.99D;
/* 37 */     this.motionZ *= 0.99D;
/*    */     
/* 39 */     if (this.particleMaxAge-- <= 0)
/*    */     {
/* 41 */       setDead();
/*    */     }
/*    */   }
/*    */   
/*    */   public static class Factory
/*    */     implements IParticleFactory
/*    */   {
/*    */     private static final String __OBFID = "CL_00002577";
/*    */     
/*    */     public EntityFX func_178902_a(int p_178902_1_, World worldIn, double p_178902_3_, double p_178902_5_, double p_178902_7_, double p_178902_9_, double p_178902_11_, double p_178902_13_, int... p_178902_15_) {
/* 51 */       return new EntityAuraFX(worldIn, p_178902_3_, p_178902_5_, p_178902_7_, p_178902_9_, p_178902_11_, p_178902_13_);
/*    */     }
/*    */   }
/*    */   
/*    */   public static class HappyVillagerFactory
/*    */     implements IParticleFactory
/*    */   {
/*    */     private static final String __OBFID = "CL_00002578";
/*    */     
/*    */     public EntityFX func_178902_a(int p_178902_1_, World worldIn, double p_178902_3_, double p_178902_5_, double p_178902_7_, double p_178902_9_, double p_178902_11_, double p_178902_13_, int... p_178902_15_) {
/* 61 */       EntityAuraFX var16 = new EntityAuraFX(worldIn, p_178902_3_, p_178902_5_, p_178902_7_, p_178902_9_, p_178902_11_, p_178902_13_);
/* 62 */       var16.setParticleTextureIndex(82);
/* 63 */       var16.setRBGColorF(1.0F, 1.0F, 1.0F);
/* 64 */       return var16;
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\particle\EntityAuraFX.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */