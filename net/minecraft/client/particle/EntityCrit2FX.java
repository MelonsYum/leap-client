/*    */ package net.minecraft.client.particle;
/*    */ 
/*    */ import net.minecraft.client.renderer.WorldRenderer;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.util.MathHelper;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ public class EntityCrit2FX
/*    */   extends EntityFX
/*    */ {
/*    */   float field_174839_a;
/*    */   private static final String __OBFID = "CL_00000899";
/*    */   
/*    */   protected EntityCrit2FX(World worldIn, double p_i46284_2_, double p_i46284_4_, double p_i46284_6_, double p_i46284_8_, double p_i46284_10_, double p_i46284_12_) {
/* 15 */     this(worldIn, p_i46284_2_, p_i46284_4_, p_i46284_6_, p_i46284_8_, p_i46284_10_, p_i46284_12_, 1.0F);
/*    */   }
/*    */ 
/*    */   
/*    */   protected EntityCrit2FX(World worldIn, double p_i46285_2_, double p_i46285_4_, double p_i46285_6_, double p_i46285_8_, double p_i46285_10_, double p_i46285_12_, float p_i46285_14_) {
/* 20 */     super(worldIn, p_i46285_2_, p_i46285_4_, p_i46285_6_, 0.0D, 0.0D, 0.0D);
/* 21 */     this.motionX *= 0.10000000149011612D;
/* 22 */     this.motionY *= 0.10000000149011612D;
/* 23 */     this.motionZ *= 0.10000000149011612D;
/* 24 */     this.motionX += p_i46285_8_ * 0.4D;
/* 25 */     this.motionY += p_i46285_10_ * 0.4D;
/* 26 */     this.motionZ += p_i46285_12_ * 0.4D;
/* 27 */     this.particleRed = this.particleGreen = this.particleBlue = (float)(Math.random() * 0.30000001192092896D + 0.6000000238418579D);
/* 28 */     this.particleScale *= 0.75F;
/* 29 */     this.particleScale *= p_i46285_14_;
/* 30 */     this.field_174839_a = this.particleScale;
/* 31 */     this.particleMaxAge = (int)(6.0D / (Math.random() * 0.8D + 0.6D));
/* 32 */     this.particleMaxAge = (int)(this.particleMaxAge * p_i46285_14_);
/* 33 */     this.noClip = false;
/* 34 */     setParticleTextureIndex(65);
/* 35 */     onUpdate();
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_180434_a(WorldRenderer p_180434_1_, Entity p_180434_2_, float p_180434_3_, float p_180434_4_, float p_180434_5_, float p_180434_6_, float p_180434_7_, float p_180434_8_) {
/* 40 */     float var9 = (this.particleAge + p_180434_3_) / this.particleMaxAge * 32.0F;
/* 41 */     var9 = MathHelper.clamp_float(var9, 0.0F, 1.0F);
/* 42 */     this.particleScale = this.field_174839_a * var9;
/* 43 */     super.func_180434_a(p_180434_1_, p_180434_2_, p_180434_3_, p_180434_4_, p_180434_5_, p_180434_6_, p_180434_7_, p_180434_8_);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void onUpdate() {
/* 51 */     this.prevPosX = this.posX;
/* 52 */     this.prevPosY = this.posY;
/* 53 */     this.prevPosZ = this.posZ;
/*    */     
/* 55 */     if (this.particleAge++ >= this.particleMaxAge)
/*    */     {
/* 57 */       setDead();
/*    */     }
/*    */     
/* 60 */     moveEntity(this.motionX, this.motionY, this.motionZ);
/* 61 */     this.particleGreen = (float)(this.particleGreen * 0.96D);
/* 62 */     this.particleBlue = (float)(this.particleBlue * 0.9D);
/* 63 */     this.motionX *= 0.699999988079071D;
/* 64 */     this.motionY *= 0.699999988079071D;
/* 65 */     this.motionZ *= 0.699999988079071D;
/* 66 */     this.motionY -= 0.019999999552965164D;
/*    */     
/* 68 */     if (this.onGround) {
/*    */       
/* 70 */       this.motionX *= 0.699999988079071D;
/* 71 */       this.motionZ *= 0.699999988079071D;
/*    */     } 
/*    */   }
/*    */   
/*    */   public static class Factory
/*    */     implements IParticleFactory
/*    */   {
/*    */     private static final String __OBFID = "CL_00002608";
/*    */     
/*    */     public EntityFX func_178902_a(int p_178902_1_, World worldIn, double p_178902_3_, double p_178902_5_, double p_178902_7_, double p_178902_9_, double p_178902_11_, double p_178902_13_, int... p_178902_15_) {
/* 81 */       return new EntityCrit2FX(worldIn, p_178902_3_, p_178902_5_, p_178902_7_, p_178902_9_, p_178902_11_, p_178902_13_);
/*    */     }
/*    */   }
/*    */   
/*    */   public static class MagicFactory
/*    */     implements IParticleFactory
/*    */   {
/*    */     private static final String __OBFID = "CL_00002609";
/*    */     
/*    */     public EntityFX func_178902_a(int p_178902_1_, World worldIn, double p_178902_3_, double p_178902_5_, double p_178902_7_, double p_178902_9_, double p_178902_11_, double p_178902_13_, int... p_178902_15_) {
/* 91 */       EntityCrit2FX var16 = new EntityCrit2FX(worldIn, p_178902_3_, p_178902_5_, p_178902_7_, p_178902_9_, p_178902_11_, p_178902_13_);
/* 92 */       var16.setRBGColorF(var16.getRedColorF() * 0.3F, var16.getGreenColorF() * 0.8F, var16.getBlueColorF());
/* 93 */       var16.nextTextureIndexX();
/* 94 */       return var16;
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\particle\EntityCrit2FX.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */