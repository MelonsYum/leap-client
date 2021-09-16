/*    */ package net.minecraft.client.particle;
/*    */ 
/*    */ import net.minecraft.client.renderer.WorldRenderer;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.util.MathHelper;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ public class EntityNoteFX
/*    */   extends EntityFX
/*    */ {
/*    */   float noteParticleScale;
/*    */   private static final String __OBFID = "CL_00000913";
/*    */   
/*    */   protected EntityNoteFX(World worldIn, double p_i46353_2_, double p_i46353_4_, double p_i46353_6_, double p_i46353_8_, double p_i46353_10_, double p_i46353_12_) {
/* 15 */     this(worldIn, p_i46353_2_, p_i46353_4_, p_i46353_6_, p_i46353_8_, p_i46353_10_, p_i46353_12_, 2.0F);
/*    */   }
/*    */ 
/*    */   
/*    */   protected EntityNoteFX(World worldIn, double p_i1217_2_, double p_i1217_4_, double p_i1217_6_, double p_i1217_8_, double p_i1217_10_, double p_i1217_12_, float p_i1217_14_) {
/* 20 */     super(worldIn, p_i1217_2_, p_i1217_4_, p_i1217_6_, 0.0D, 0.0D, 0.0D);
/* 21 */     this.motionX *= 0.009999999776482582D;
/* 22 */     this.motionY *= 0.009999999776482582D;
/* 23 */     this.motionZ *= 0.009999999776482582D;
/* 24 */     this.motionY += 0.2D;
/* 25 */     this.particleRed = MathHelper.sin(((float)p_i1217_8_ + 0.0F) * 3.1415927F * 2.0F) * 0.65F + 0.35F;
/* 26 */     this.particleGreen = MathHelper.sin(((float)p_i1217_8_ + 0.33333334F) * 3.1415927F * 2.0F) * 0.65F + 0.35F;
/* 27 */     this.particleBlue = MathHelper.sin(((float)p_i1217_8_ + 0.6666667F) * 3.1415927F * 2.0F) * 0.65F + 0.35F;
/* 28 */     this.particleScale *= 0.75F;
/* 29 */     this.particleScale *= p_i1217_14_;
/* 30 */     this.noteParticleScale = this.particleScale;
/* 31 */     this.particleMaxAge = 6;
/* 32 */     this.noClip = false;
/* 33 */     setParticleTextureIndex(64);
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_180434_a(WorldRenderer p_180434_1_, Entity p_180434_2_, float p_180434_3_, float p_180434_4_, float p_180434_5_, float p_180434_6_, float p_180434_7_, float p_180434_8_) {
/* 38 */     float var9 = (this.particleAge + p_180434_3_) / this.particleMaxAge * 32.0F;
/* 39 */     var9 = MathHelper.clamp_float(var9, 0.0F, 1.0F);
/* 40 */     this.particleScale = this.noteParticleScale * var9;
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
/* 58 */     moveEntity(this.motionX, this.motionY, this.motionZ);
/*    */     
/* 60 */     if (this.posY == this.prevPosY) {
/*    */       
/* 62 */       this.motionX *= 1.1D;
/* 63 */       this.motionZ *= 1.1D;
/*    */     } 
/*    */     
/* 66 */     this.motionX *= 0.6600000262260437D;
/* 67 */     this.motionY *= 0.6600000262260437D;
/* 68 */     this.motionZ *= 0.6600000262260437D;
/*    */     
/* 70 */     if (this.onGround) {
/*    */       
/* 72 */       this.motionX *= 0.699999988079071D;
/* 73 */       this.motionZ *= 0.699999988079071D;
/*    */     } 
/*    */   }
/*    */   
/*    */   public static class Factory
/*    */     implements IParticleFactory
/*    */   {
/*    */     private static final String __OBFID = "CL_00002592";
/*    */     
/*    */     public EntityFX func_178902_a(int p_178902_1_, World worldIn, double p_178902_3_, double p_178902_5_, double p_178902_7_, double p_178902_9_, double p_178902_11_, double p_178902_13_, int... p_178902_15_) {
/* 83 */       return new EntityNoteFX(worldIn, p_178902_3_, p_178902_5_, p_178902_7_, p_178902_9_, p_178902_11_, p_178902_13_);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\particle\EntityNoteFX.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */