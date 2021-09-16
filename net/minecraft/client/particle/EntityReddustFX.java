/*    */ package net.minecraft.client.particle;
/*    */ 
/*    */ import net.minecraft.client.renderer.WorldRenderer;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.util.MathHelper;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ public class EntityReddustFX
/*    */   extends EntityFX
/*    */ {
/*    */   float reddustParticleScale;
/*    */   private static final String __OBFID = "CL_00000923";
/*    */   
/*    */   protected EntityReddustFX(World worldIn, double p_i46349_2_, double p_i46349_4_, double p_i46349_6_, float p_i46349_8_, float p_i46349_9_, float p_i46349_10_) {
/* 15 */     this(worldIn, p_i46349_2_, p_i46349_4_, p_i46349_6_, 1.0F, p_i46349_8_, p_i46349_9_, p_i46349_10_);
/*    */   }
/*    */ 
/*    */   
/*    */   protected EntityReddustFX(World worldIn, double p_i46350_2_, double p_i46350_4_, double p_i46350_6_, float p_i46350_8_, float p_i46350_9_, float p_i46350_10_, float p_i46350_11_) {
/* 20 */     super(worldIn, p_i46350_2_, p_i46350_4_, p_i46350_6_, 0.0D, 0.0D, 0.0D);
/* 21 */     this.motionX *= 0.10000000149011612D;
/* 22 */     this.motionY *= 0.10000000149011612D;
/* 23 */     this.motionZ *= 0.10000000149011612D;
/*    */     
/* 25 */     if (p_i46350_9_ == 0.0F)
/*    */     {
/* 27 */       p_i46350_9_ = 1.0F;
/*    */     }
/*    */     
/* 30 */     float var12 = (float)Math.random() * 0.4F + 0.6F;
/* 31 */     this.particleRed = ((float)(Math.random() * 0.20000000298023224D) + 0.8F) * p_i46350_9_ * var12;
/* 32 */     this.particleGreen = ((float)(Math.random() * 0.20000000298023224D) + 0.8F) * p_i46350_10_ * var12;
/* 33 */     this.particleBlue = ((float)(Math.random() * 0.20000000298023224D) + 0.8F) * p_i46350_11_ * var12;
/* 34 */     this.particleScale *= 0.75F;
/* 35 */     this.particleScale *= p_i46350_8_;
/* 36 */     this.reddustParticleScale = this.particleScale;
/* 37 */     this.particleMaxAge = (int)(8.0D / (Math.random() * 0.8D + 0.2D));
/* 38 */     this.particleMaxAge = (int)(this.particleMaxAge * p_i46350_8_);
/* 39 */     this.noClip = false;
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_180434_a(WorldRenderer p_180434_1_, Entity p_180434_2_, float p_180434_3_, float p_180434_4_, float p_180434_5_, float p_180434_6_, float p_180434_7_, float p_180434_8_) {
/* 44 */     float var9 = (this.particleAge + p_180434_3_) / this.particleMaxAge * 32.0F;
/* 45 */     var9 = MathHelper.clamp_float(var9, 0.0F, 1.0F);
/* 46 */     this.particleScale = this.reddustParticleScale * var9;
/* 47 */     super.func_180434_a(p_180434_1_, p_180434_2_, p_180434_3_, p_180434_4_, p_180434_5_, p_180434_6_, p_180434_7_, p_180434_8_);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void onUpdate() {
/* 55 */     this.prevPosX = this.posX;
/* 56 */     this.prevPosY = this.posY;
/* 57 */     this.prevPosZ = this.posZ;
/*    */     
/* 59 */     if (this.particleAge++ >= this.particleMaxAge)
/*    */     {
/* 61 */       setDead();
/*    */     }
/*    */     
/* 64 */     setParticleTextureIndex(7 - this.particleAge * 8 / this.particleMaxAge);
/* 65 */     moveEntity(this.motionX, this.motionY, this.motionZ);
/*    */     
/* 67 */     if (this.posY == this.prevPosY) {
/*    */       
/* 69 */       this.motionX *= 1.1D;
/* 70 */       this.motionZ *= 1.1D;
/*    */     } 
/*    */     
/* 73 */     this.motionX *= 0.9599999785423279D;
/* 74 */     this.motionY *= 0.9599999785423279D;
/* 75 */     this.motionZ *= 0.9599999785423279D;
/*    */     
/* 77 */     if (this.onGround) {
/*    */       
/* 79 */       this.motionX *= 0.699999988079071D;
/* 80 */       this.motionZ *= 0.699999988079071D;
/*    */     } 
/*    */   }
/*    */   
/*    */   public static class Factory
/*    */     implements IParticleFactory
/*    */   {
/*    */     private static final String __OBFID = "CL_00002589";
/*    */     
/*    */     public EntityFX func_178902_a(int p_178902_1_, World worldIn, double p_178902_3_, double p_178902_5_, double p_178902_7_, double p_178902_9_, double p_178902_11_, double p_178902_13_, int... p_178902_15_) {
/* 90 */       return new EntityReddustFX(worldIn, p_178902_3_, p_178902_5_, p_178902_7_, (float)p_178902_9_, (float)p_178902_11_, (float)p_178902_13_);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\particle\EntityReddustFX.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */