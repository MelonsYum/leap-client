/*    */ package net.minecraft.client.particle;
/*    */ 
/*    */ import net.minecraft.client.renderer.WorldRenderer;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.util.MathHelper;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ public class EntitySmokeFX
/*    */   extends EntityFX
/*    */ {
/*    */   float smokeParticleScale;
/*    */   private static final String __OBFID = "CL_00000924";
/*    */   
/*    */   private EntitySmokeFX(World worldIn, double p_i46347_2_, double p_i46347_4_, double p_i46347_6_, double p_i46347_8_, double p_i46347_10_, double p_i46347_12_) {
/* 15 */     this(worldIn, p_i46347_2_, p_i46347_4_, p_i46347_6_, p_i46347_8_, p_i46347_10_, p_i46347_12_, 1.0F);
/*    */   }
/*    */ 
/*    */   
/*    */   protected EntitySmokeFX(World worldIn, double p_i46348_2_, double p_i46348_4_, double p_i46348_6_, double p_i46348_8_, double p_i46348_10_, double p_i46348_12_, float p_i46348_14_) {
/* 20 */     super(worldIn, p_i46348_2_, p_i46348_4_, p_i46348_6_, 0.0D, 0.0D, 0.0D);
/* 21 */     this.motionX *= 0.10000000149011612D;
/* 22 */     this.motionY *= 0.10000000149011612D;
/* 23 */     this.motionZ *= 0.10000000149011612D;
/* 24 */     this.motionX += p_i46348_8_;
/* 25 */     this.motionY += p_i46348_10_;
/* 26 */     this.motionZ += p_i46348_12_;
/* 27 */     this.particleRed = this.particleGreen = this.particleBlue = (float)(Math.random() * 0.30000001192092896D);
/* 28 */     this.particleScale *= 0.75F;
/* 29 */     this.particleScale *= p_i46348_14_;
/* 30 */     this.smokeParticleScale = this.particleScale;
/* 31 */     this.particleMaxAge = (int)(8.0D / (Math.random() * 0.8D + 0.2D));
/* 32 */     this.particleMaxAge = (int)(this.particleMaxAge * p_i46348_14_);
/* 33 */     this.noClip = false;
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_180434_a(WorldRenderer p_180434_1_, Entity p_180434_2_, float p_180434_3_, float p_180434_4_, float p_180434_5_, float p_180434_6_, float p_180434_7_, float p_180434_8_) {
/* 38 */     float var9 = (this.particleAge + p_180434_3_) / this.particleMaxAge * 32.0F;
/* 39 */     var9 = MathHelper.clamp_float(var9, 0.0F, 1.0F);
/* 40 */     this.particleScale = this.smokeParticleScale * var9;
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
/* 59 */     this.motionY += 0.004D;
/* 60 */     moveEntity(this.motionX, this.motionY, this.motionZ);
/*    */     
/* 62 */     if (this.posY == this.prevPosY) {
/*    */       
/* 64 */       this.motionX *= 1.1D;
/* 65 */       this.motionZ *= 1.1D;
/*    */     } 
/*    */     
/* 68 */     this.motionX *= 0.9599999785423279D;
/* 69 */     this.motionY *= 0.9599999785423279D;
/* 70 */     this.motionZ *= 0.9599999785423279D;
/*    */     
/* 72 */     if (this.onGround) {
/*    */       
/* 74 */       this.motionX *= 0.699999988079071D;
/* 75 */       this.motionZ *= 0.699999988079071D;
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   EntitySmokeFX(World p_i46282_1_, double p_i46282_2_, double p_i46282_4_, double p_i46282_6_, double p_i46282_8_, double p_i46282_10_, double p_i46282_12_, Object p_i46282_14_) {
/* 81 */     this(p_i46282_1_, p_i46282_2_, p_i46282_4_, p_i46282_6_, p_i46282_8_, p_i46282_10_, p_i46282_12_);
/*    */   }
/*    */   
/*    */   public static class Factory
/*    */     implements IParticleFactory
/*    */   {
/*    */     private static final String __OBFID = "CL_00002587";
/*    */     
/*    */     public EntityFX func_178902_a(int p_178902_1_, World worldIn, double p_178902_3_, double p_178902_5_, double p_178902_7_, double p_178902_9_, double p_178902_11_, double p_178902_13_, int... p_178902_15_) {
/* 90 */       return new EntitySmokeFX(worldIn, p_178902_3_, p_178902_5_, p_178902_7_, p_178902_9_, p_178902_11_, p_178902_13_, null);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\particle\EntitySmokeFX.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */