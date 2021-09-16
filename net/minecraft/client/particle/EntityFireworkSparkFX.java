/*     */ package net.minecraft.client.particle;
/*     */ 
/*     */ import net.minecraft.client.renderer.WorldRenderer;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class EntityFireworkSparkFX
/*     */   extends EntityFX {
/*  10 */   private int baseTextureIndex = 160;
/*     */   
/*     */   private boolean field_92054_ax;
/*     */   private boolean field_92048_ay;
/*     */   private final EffectRenderer field_92047_az;
/*     */   private float fadeColourRed;
/*     */   private float fadeColourGreen;
/*     */   private float fadeColourBlue;
/*     */   private boolean hasFadeColour;
/*     */   private static final String __OBFID = "CL_00000905";
/*     */   
/*     */   public EntityFireworkSparkFX(World worldIn, double p_i46356_2_, double p_i46356_4_, double p_i46356_6_, double p_i46356_8_, double p_i46356_10_, double p_i46356_12_, EffectRenderer p_i46356_14_) {
/*  22 */     super(worldIn, p_i46356_2_, p_i46356_4_, p_i46356_6_);
/*  23 */     this.motionX = p_i46356_8_;
/*  24 */     this.motionY = p_i46356_10_;
/*  25 */     this.motionZ = p_i46356_12_;
/*  26 */     this.field_92047_az = p_i46356_14_;
/*  27 */     this.particleScale *= 0.75F;
/*  28 */     this.particleMaxAge = 48 + this.rand.nextInt(12);
/*  29 */     this.noClip = false;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setTrail(boolean p_92045_1_) {
/*  34 */     this.field_92054_ax = p_92045_1_;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setTwinkle(boolean p_92043_1_) {
/*  39 */     this.field_92048_ay = p_92043_1_;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setColour(int p_92044_1_) {
/*  44 */     float var2 = ((p_92044_1_ & 0xFF0000) >> 16) / 255.0F;
/*  45 */     float var3 = ((p_92044_1_ & 0xFF00) >> 8) / 255.0F;
/*  46 */     float var4 = ((p_92044_1_ & 0xFF) >> 0) / 255.0F;
/*  47 */     float var5 = 1.0F;
/*  48 */     setRBGColorF(var2 * var5, var3 * var5, var4 * var5);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setFadeColour(int p_92046_1_) {
/*  53 */     this.fadeColourRed = ((p_92046_1_ & 0xFF0000) >> 16) / 255.0F;
/*  54 */     this.fadeColourGreen = ((p_92046_1_ & 0xFF00) >> 8) / 255.0F;
/*  55 */     this.fadeColourBlue = ((p_92046_1_ & 0xFF) >> 0) / 255.0F;
/*  56 */     this.hasFadeColour = true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AxisAlignedBB getBoundingBox() {
/*  64 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canBePushed() {
/*  72 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_180434_a(WorldRenderer p_180434_1_, Entity p_180434_2_, float p_180434_3_, float p_180434_4_, float p_180434_5_, float p_180434_6_, float p_180434_7_, float p_180434_8_) {
/*  77 */     if (!this.field_92048_ay || this.particleAge < this.particleMaxAge / 3 || (this.particleAge + this.particleMaxAge) / 3 % 2 == 0)
/*     */     {
/*  79 */       super.func_180434_a(p_180434_1_, p_180434_2_, p_180434_3_, p_180434_4_, p_180434_5_, p_180434_6_, p_180434_7_, p_180434_8_);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onUpdate() {
/*  88 */     this.prevPosX = this.posX;
/*  89 */     this.prevPosY = this.posY;
/*  90 */     this.prevPosZ = this.posZ;
/*     */     
/*  92 */     if (this.particleAge++ >= this.particleMaxAge)
/*     */     {
/*  94 */       setDead();
/*     */     }
/*     */     
/*  97 */     if (this.particleAge > this.particleMaxAge / 2) {
/*     */       
/*  99 */       setAlphaF(1.0F - (this.particleAge - (this.particleMaxAge / 2)) / this.particleMaxAge);
/*     */       
/* 101 */       if (this.hasFadeColour) {
/*     */         
/* 103 */         this.particleRed += (this.fadeColourRed - this.particleRed) * 0.2F;
/* 104 */         this.particleGreen += (this.fadeColourGreen - this.particleGreen) * 0.2F;
/* 105 */         this.particleBlue += (this.fadeColourBlue - this.particleBlue) * 0.2F;
/*     */       } 
/*     */     } 
/*     */     
/* 109 */     setParticleTextureIndex(this.baseTextureIndex + 7 - this.particleAge * 8 / this.particleMaxAge);
/* 110 */     this.motionY -= 0.004D;
/* 111 */     moveEntity(this.motionX, this.motionY, this.motionZ);
/* 112 */     this.motionX *= 0.9100000262260437D;
/* 113 */     this.motionY *= 0.9100000262260437D;
/* 114 */     this.motionZ *= 0.9100000262260437D;
/*     */     
/* 116 */     if (this.onGround) {
/*     */       
/* 118 */       this.motionX *= 0.699999988079071D;
/* 119 */       this.motionZ *= 0.699999988079071D;
/*     */     } 
/*     */     
/* 122 */     if (this.field_92054_ax && this.particleAge < this.particleMaxAge / 2 && (this.particleAge + this.particleMaxAge) % 2 == 0) {
/*     */       
/* 124 */       EntityFireworkSparkFX var1 = new EntityFireworkSparkFX(this.worldObj, this.posX, this.posY, this.posZ, 0.0D, 0.0D, 0.0D, this.field_92047_az);
/* 125 */       var1.setAlphaF(0.99F);
/* 126 */       var1.setRBGColorF(this.particleRed, this.particleGreen, this.particleBlue);
/* 127 */       var1.particleAge = var1.particleMaxAge / 2;
/*     */       
/* 129 */       if (this.hasFadeColour) {
/*     */         
/* 131 */         var1.hasFadeColour = true;
/* 132 */         var1.fadeColourRed = this.fadeColourRed;
/* 133 */         var1.fadeColourGreen = this.fadeColourGreen;
/* 134 */         var1.fadeColourBlue = this.fadeColourBlue;
/*     */       } 
/*     */       
/* 137 */       var1.field_92048_ay = this.field_92048_ay;
/* 138 */       this.field_92047_az.addEffect(var1);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public int getBrightnessForRender(float p_70070_1_) {
/* 144 */     return 15728880;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getBrightness(float p_70013_1_) {
/* 152 */     return 1.0F;
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\particle\EntityFireworkSparkFX.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */