/*    */ package net.minecraft.client.particle;
/*    */ 
/*    */ import net.minecraft.client.renderer.WorldRenderer;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.util.MathHelper;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ 
/*    */ public class EntityFlameFX
/*    */   extends EntityFX
/*    */ {
/*    */   private float flameScale;
/*    */   private static final String __OBFID = "CL_00000907";
/*    */   
/*    */   protected EntityFlameFX(World worldIn, double p_i1209_2_, double p_i1209_4_, double p_i1209_6_, double p_i1209_8_, double p_i1209_10_, double p_i1209_12_) {
/* 16 */     super(worldIn, p_i1209_2_, p_i1209_4_, p_i1209_6_, p_i1209_8_, p_i1209_10_, p_i1209_12_);
/* 17 */     this.motionX = this.motionX * 0.009999999776482582D + p_i1209_8_;
/* 18 */     this.motionY = this.motionY * 0.009999999776482582D + p_i1209_10_;
/* 19 */     this.motionZ = this.motionZ * 0.009999999776482582D + p_i1209_12_;
/* 20 */     double var10000 = p_i1209_2_ + ((this.rand.nextFloat() - this.rand.nextFloat()) * 0.05F);
/* 21 */     var10000 = p_i1209_4_ + ((this.rand.nextFloat() - this.rand.nextFloat()) * 0.05F);
/* 22 */     var10000 = p_i1209_6_ + ((this.rand.nextFloat() - this.rand.nextFloat()) * 0.05F);
/* 23 */     this.flameScale = this.particleScale;
/* 24 */     this.particleRed = this.particleGreen = this.particleBlue = 1.0F;
/* 25 */     this.particleMaxAge = (int)(8.0D / (Math.random() * 0.8D + 0.2D)) + 4;
/* 26 */     this.noClip = true;
/* 27 */     setParticleTextureIndex(48);
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_180434_a(WorldRenderer p_180434_1_, Entity p_180434_2_, float p_180434_3_, float p_180434_4_, float p_180434_5_, float p_180434_6_, float p_180434_7_, float p_180434_8_) {
/* 32 */     float var9 = (this.particleAge + p_180434_3_) / this.particleMaxAge;
/* 33 */     this.particleScale = this.flameScale * (1.0F - var9 * var9 * 0.5F);
/* 34 */     super.func_180434_a(p_180434_1_, p_180434_2_, p_180434_3_, p_180434_4_, p_180434_5_, p_180434_6_, p_180434_7_, p_180434_8_);
/*    */   }
/*    */ 
/*    */   
/*    */   public int getBrightnessForRender(float p_70070_1_) {
/* 39 */     float var2 = (this.particleAge + p_70070_1_) / this.particleMaxAge;
/* 40 */     var2 = MathHelper.clamp_float(var2, 0.0F, 1.0F);
/* 41 */     int var3 = super.getBrightnessForRender(p_70070_1_);
/* 42 */     int var4 = var3 & 0xFF;
/* 43 */     int var5 = var3 >> 16 & 0xFF;
/* 44 */     var4 += (int)(var2 * 15.0F * 16.0F);
/*    */     
/* 46 */     if (var4 > 240)
/*    */     {
/* 48 */       var4 = 240;
/*    */     }
/*    */     
/* 51 */     return var4 | var5 << 16;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public float getBrightness(float p_70013_1_) {
/* 59 */     float var2 = (this.particleAge + p_70013_1_) / this.particleMaxAge;
/* 60 */     var2 = MathHelper.clamp_float(var2, 0.0F, 1.0F);
/* 61 */     float var3 = super.getBrightness(p_70013_1_);
/* 62 */     return var3 * var2 + 1.0F - var2;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void onUpdate() {
/* 70 */     this.prevPosX = this.posX;
/* 71 */     this.prevPosY = this.posY;
/* 72 */     this.prevPosZ = this.posZ;
/*    */     
/* 74 */     if (this.particleAge++ >= this.particleMaxAge)
/*    */     {
/* 76 */       setDead();
/*    */     }
/*    */     
/* 79 */     moveEntity(this.motionX, this.motionY, this.motionZ);
/* 80 */     this.motionX *= 0.9599999785423279D;
/* 81 */     this.motionY *= 0.9599999785423279D;
/* 82 */     this.motionZ *= 0.9599999785423279D;
/*    */     
/* 84 */     if (this.onGround) {
/*    */       
/* 86 */       this.motionX *= 0.699999988079071D;
/* 87 */       this.motionZ *= 0.699999988079071D;
/*    */     } 
/*    */   }
/*    */   
/*    */   public static class Factory
/*    */     implements IParticleFactory
/*    */   {
/*    */     private static final String __OBFID = "CL_00002602";
/*    */     
/*    */     public EntityFX func_178902_a(int p_178902_1_, World worldIn, double p_178902_3_, double p_178902_5_, double p_178902_7_, double p_178902_9_, double p_178902_11_, double p_178902_13_, int... p_178902_15_) {
/* 97 */       return new EntityFlameFX(worldIn, p_178902_3_, p_178902_5_, p_178902_7_, p_178902_9_, p_178902_11_, p_178902_13_);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\particle\EntityFlameFX.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */