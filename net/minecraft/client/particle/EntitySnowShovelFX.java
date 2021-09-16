/*    */ package net.minecraft.client.particle;
/*    */ 
/*    */ import net.minecraft.client.renderer.WorldRenderer;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.util.MathHelper;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ public class EntitySnowShovelFX
/*    */   extends EntityFX
/*    */ {
/*    */   float snowDigParticleScale;
/*    */   private static final String __OBFID = "CL_00000925";
/*    */   
/*    */   protected EntitySnowShovelFX(World worldIn, double p_i1227_2_, double p_i1227_4_, double p_i1227_6_, double p_i1227_8_, double p_i1227_10_, double p_i1227_12_) {
/* 15 */     this(worldIn, p_i1227_2_, p_i1227_4_, p_i1227_6_, p_i1227_8_, p_i1227_10_, p_i1227_12_, 1.0F);
/*    */   }
/*    */ 
/*    */   
/*    */   protected EntitySnowShovelFX(World worldIn, double p_i1228_2_, double p_i1228_4_, double p_i1228_6_, double p_i1228_8_, double p_i1228_10_, double p_i1228_12_, float p_i1228_14_) {
/* 20 */     super(worldIn, p_i1228_2_, p_i1228_4_, p_i1228_6_, p_i1228_8_, p_i1228_10_, p_i1228_12_);
/* 21 */     this.motionX *= 0.10000000149011612D;
/* 22 */     this.motionY *= 0.10000000149011612D;
/* 23 */     this.motionZ *= 0.10000000149011612D;
/* 24 */     this.motionX += p_i1228_8_;
/* 25 */     this.motionY += p_i1228_10_;
/* 26 */     this.motionZ += p_i1228_12_;
/* 27 */     this.particleRed = this.particleGreen = this.particleBlue = 1.0F - (float)(Math.random() * 0.30000001192092896D);
/* 28 */     this.particleScale *= 0.75F;
/* 29 */     this.particleScale *= p_i1228_14_;
/* 30 */     this.snowDigParticleScale = this.particleScale;
/* 31 */     this.particleMaxAge = (int)(8.0D / (Math.random() * 0.8D + 0.2D));
/* 32 */     this.particleMaxAge = (int)(this.particleMaxAge * p_i1228_14_);
/* 33 */     this.noClip = false;
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_180434_a(WorldRenderer p_180434_1_, Entity p_180434_2_, float p_180434_3_, float p_180434_4_, float p_180434_5_, float p_180434_6_, float p_180434_7_, float p_180434_8_) {
/* 38 */     float var9 = (this.particleAge + p_180434_3_) / this.particleMaxAge * 32.0F;
/* 39 */     var9 = MathHelper.clamp_float(var9, 0.0F, 1.0F);
/* 40 */     this.particleScale = this.snowDigParticleScale * var9;
/* 41 */     super.func_180434_a(p_180434_1_, p_180434_2_, p_180434_3_, p_180434_4_, p_180434_5_, p_180434_6_, p_180434_7_, p_180434_8_);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void onUpdate() {
/* 49 */     this.prevPosX = this.posX;
/* 50 */     this.prevPosY = this.posY;
/* 51 */     this.prevPosZ = this.posZ;
/*    */     
/* 53 */     if (this.particleAge++ >= this.particleMaxAge)
/*    */     {
/* 55 */       setDead();
/*    */     }
/*    */     
/* 58 */     setParticleTextureIndex(7 - this.particleAge * 8 / this.particleMaxAge);
/* 59 */     this.motionY -= 0.03D;
/* 60 */     moveEntity(this.motionX, this.motionY, this.motionZ);
/* 61 */     this.motionX *= 0.9900000095367432D;
/* 62 */     this.motionY *= 0.9900000095367432D;
/* 63 */     this.motionZ *= 0.9900000095367432D;
/*    */     
/* 65 */     if (this.onGround) {
/*    */       
/* 67 */       this.motionX *= 0.699999988079071D;
/* 68 */       this.motionZ *= 0.699999988079071D;
/*    */     } 
/*    */   }
/*    */   
/*    */   public static class Factory
/*    */     implements IParticleFactory
/*    */   {
/*    */     private static final String __OBFID = "CL_00002586";
/*    */     
/*    */     public EntityFX func_178902_a(int p_178902_1_, World worldIn, double p_178902_3_, double p_178902_5_, double p_178902_7_, double p_178902_9_, double p_178902_11_, double p_178902_13_, int... p_178902_15_) {
/* 78 */       return new EntitySnowShovelFX(worldIn, p_178902_3_, p_178902_5_, p_178902_7_, p_178902_9_, p_178902_11_, p_178902_13_);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\particle\EntitySnowShovelFX.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */