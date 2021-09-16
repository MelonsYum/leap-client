/*     */ package net.minecraft.client.particle;
/*     */ 
/*     */ import net.minecraft.client.renderer.WorldRenderer;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class EntityPortalFX
/*     */   extends EntityFX
/*     */ {
/*     */   private float portalParticleScale;
/*     */   private double portalPosX;
/*     */   private double portalPosY;
/*     */   private double portalPosZ;
/*     */   private static final String __OBFID = "CL_00000921";
/*     */   
/*     */   protected EntityPortalFX(World worldIn, double p_i46351_2_, double p_i46351_4_, double p_i46351_6_, double p_i46351_8_, double p_i46351_10_, double p_i46351_12_) {
/*  17 */     super(worldIn, p_i46351_2_, p_i46351_4_, p_i46351_6_, p_i46351_8_, p_i46351_10_, p_i46351_12_);
/*  18 */     this.motionX = p_i46351_8_;
/*  19 */     this.motionY = p_i46351_10_;
/*  20 */     this.motionZ = p_i46351_12_;
/*  21 */     this.portalPosX = this.posX = p_i46351_2_;
/*  22 */     this.portalPosY = this.posY = p_i46351_4_;
/*  23 */     this.portalPosZ = this.posZ = p_i46351_6_;
/*  24 */     float var14 = this.rand.nextFloat() * 0.6F + 0.4F;
/*  25 */     this.portalParticleScale = this.particleScale = this.rand.nextFloat() * 0.2F + 0.5F;
/*  26 */     this.particleRed = this.particleGreen = this.particleBlue = 1.0F * var14;
/*  27 */     this.particleGreen *= 0.3F;
/*  28 */     this.particleRed *= 0.9F;
/*  29 */     this.particleMaxAge = (int)(Math.random() * 10.0D) + 40;
/*  30 */     this.noClip = true;
/*  31 */     setParticleTextureIndex((int)(Math.random() * 8.0D));
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_180434_a(WorldRenderer p_180434_1_, Entity p_180434_2_, float p_180434_3_, float p_180434_4_, float p_180434_5_, float p_180434_6_, float p_180434_7_, float p_180434_8_) {
/*  36 */     float var9 = (this.particleAge + p_180434_3_) / this.particleMaxAge;
/*  37 */     var9 = 1.0F - var9;
/*  38 */     var9 *= var9;
/*  39 */     var9 = 1.0F - var9;
/*  40 */     this.particleScale = this.portalParticleScale * var9;
/*  41 */     super.func_180434_a(p_180434_1_, p_180434_2_, p_180434_3_, p_180434_4_, p_180434_5_, p_180434_6_, p_180434_7_, p_180434_8_);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getBrightnessForRender(float p_70070_1_) {
/*  46 */     int var2 = super.getBrightnessForRender(p_70070_1_);
/*  47 */     float var3 = this.particleAge / this.particleMaxAge;
/*  48 */     var3 *= var3;
/*  49 */     var3 *= var3;
/*  50 */     int var4 = var2 & 0xFF;
/*  51 */     int var5 = var2 >> 16 & 0xFF;
/*  52 */     var5 += (int)(var3 * 15.0F * 16.0F);
/*     */     
/*  54 */     if (var5 > 240)
/*     */     {
/*  56 */       var5 = 240;
/*     */     }
/*     */     
/*  59 */     return var4 | var5 << 16;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getBrightness(float p_70013_1_) {
/*  67 */     float var2 = super.getBrightness(p_70013_1_);
/*  68 */     float var3 = this.particleAge / this.particleMaxAge;
/*  69 */     var3 = var3 * var3 * var3 * var3;
/*  70 */     return var2 * (1.0F - var3) + var3;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onUpdate() {
/*  78 */     this.prevPosX = this.posX;
/*  79 */     this.prevPosY = this.posY;
/*  80 */     this.prevPosZ = this.posZ;
/*  81 */     float var1 = this.particleAge / this.particleMaxAge;
/*  82 */     float var2 = var1;
/*  83 */     var1 = -var1 + var1 * var1 * 2.0F;
/*  84 */     var1 = 1.0F - var1;
/*  85 */     this.posX = this.portalPosX + this.motionX * var1;
/*  86 */     this.posY = this.portalPosY + this.motionY * var1 + (1.0F - var2);
/*  87 */     this.posZ = this.portalPosZ + this.motionZ * var1;
/*     */     
/*  89 */     if (this.particleAge++ >= this.particleMaxAge)
/*     */     {
/*  91 */       setDead();
/*     */     }
/*     */   }
/*     */   
/*     */   public static class Factory
/*     */     implements IParticleFactory
/*     */   {
/*     */     private static final String __OBFID = "CL_00002590";
/*     */     
/*     */     public EntityFX func_178902_a(int p_178902_1_, World worldIn, double p_178902_3_, double p_178902_5_, double p_178902_7_, double p_178902_9_, double p_178902_11_, double p_178902_13_, int... p_178902_15_) {
/* 101 */       return new EntityPortalFX(worldIn, p_178902_3_, p_178902_5_, p_178902_7_, p_178902_9_, p_178902_11_, p_178902_13_);
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\particle\EntityPortalFX.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */