/*     */ package net.minecraft.client.particle;
/*     */ 
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.renderer.WorldRenderer;
/*     */ import net.minecraft.client.renderer.texture.TextureAtlasSprite;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class EntityFX
/*     */   extends Entity
/*     */ {
/*     */   protected int particleTextureIndexX;
/*     */   protected int particleTextureIndexY;
/*     */   protected float particleTextureJitterX;
/*     */   protected float particleTextureJitterY;
/*     */   protected int particleAge;
/*     */   protected int particleMaxAge;
/*     */   protected float particleScale;
/*     */   protected float particleGravity;
/*     */   protected float particleRed;
/*     */   protected float particleGreen;
/*     */   protected float particleBlue;
/*     */   protected float particleAlpha;
/*     */   protected TextureAtlasSprite particleIcon;
/*     */   public static double interpPosX;
/*     */   public static double interpPosY;
/*     */   public static double interpPosZ;
/*     */   private static final String __OBFID = "CL_00000914";
/*     */   
/*     */   protected EntityFX(World worldIn, double p_i46352_2_, double p_i46352_4_, double p_i46352_6_) {
/*  47 */     super(worldIn);
/*  48 */     this.particleAlpha = 1.0F;
/*  49 */     setSize(0.2F, 0.2F);
/*  50 */     setPosition(p_i46352_2_, p_i46352_4_, p_i46352_6_);
/*  51 */     this.lastTickPosX = p_i46352_2_;
/*  52 */     this.lastTickPosY = p_i46352_4_;
/*  53 */     this.lastTickPosZ = p_i46352_6_;
/*  54 */     this.particleRed = this.particleGreen = this.particleBlue = 1.0F;
/*  55 */     this.particleTextureJitterX = this.rand.nextFloat() * 3.0F;
/*  56 */     this.particleTextureJitterY = this.rand.nextFloat() * 3.0F;
/*  57 */     this.particleScale = (this.rand.nextFloat() * 0.5F + 0.5F) * 2.0F;
/*  58 */     this.particleMaxAge = (int)(4.0F / (this.rand.nextFloat() * 0.9F + 0.1F));
/*  59 */     this.particleAge = 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public EntityFX(World worldIn, double p_i1219_2_, double p_i1219_4_, double p_i1219_6_, double p_i1219_8_, double p_i1219_10_, double p_i1219_12_) {
/*  64 */     this(worldIn, p_i1219_2_, p_i1219_4_, p_i1219_6_);
/*  65 */     this.motionX = p_i1219_8_ + (Math.random() * 2.0D - 1.0D) * 0.4000000059604645D;
/*  66 */     this.motionY = p_i1219_10_ + (Math.random() * 2.0D - 1.0D) * 0.4000000059604645D;
/*  67 */     this.motionZ = p_i1219_12_ + (Math.random() * 2.0D - 1.0D) * 0.4000000059604645D;
/*  68 */     float var14 = (float)(Math.random() + Math.random() + 1.0D) * 0.15F;
/*  69 */     float var15 = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionY * this.motionY + this.motionZ * this.motionZ);
/*  70 */     this.motionX = this.motionX / var15 * var14 * 0.4000000059604645D;
/*  71 */     this.motionY = this.motionY / var15 * var14 * 0.4000000059604645D + 0.10000000149011612D;
/*  72 */     this.motionZ = this.motionZ / var15 * var14 * 0.4000000059604645D;
/*     */   }
/*     */ 
/*     */   
/*     */   public EntityFX multiplyVelocity(float p_70543_1_) {
/*  77 */     this.motionX *= p_70543_1_;
/*  78 */     this.motionY = (this.motionY - 0.10000000149011612D) * p_70543_1_ + 0.10000000149011612D;
/*  79 */     this.motionZ *= p_70543_1_;
/*  80 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public EntityFX multipleParticleScaleBy(float p_70541_1_) {
/*  85 */     setSize(0.2F * p_70541_1_, 0.2F * p_70541_1_);
/*  86 */     this.particleScale *= p_70541_1_;
/*  87 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setRBGColorF(float p_70538_1_, float p_70538_2_, float p_70538_3_) {
/*  92 */     this.particleRed = p_70538_1_;
/*  93 */     this.particleGreen = p_70538_2_;
/*  94 */     this.particleBlue = p_70538_3_;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setAlphaF(float p_82338_1_) {
/* 102 */     if (this.particleAlpha == 1.0F && p_82338_1_ < 1.0F) {
/*     */       
/* 104 */       (Minecraft.getMinecraft()).effectRenderer.func_178928_b(this);
/*     */     }
/* 106 */     else if (this.particleAlpha < 1.0F && p_82338_1_ == 1.0F) {
/*     */       
/* 108 */       (Minecraft.getMinecraft()).effectRenderer.func_178931_c(this);
/*     */     } 
/*     */     
/* 111 */     this.particleAlpha = p_82338_1_;
/*     */   }
/*     */ 
/*     */   
/*     */   public float getRedColorF() {
/* 116 */     return this.particleRed;
/*     */   }
/*     */ 
/*     */   
/*     */   public float getGreenColorF() {
/* 121 */     return this.particleGreen;
/*     */   }
/*     */ 
/*     */   
/*     */   public float getBlueColorF() {
/* 126 */     return this.particleBlue;
/*     */   }
/*     */ 
/*     */   
/*     */   public float func_174838_j() {
/* 131 */     return this.particleAlpha;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean canTriggerWalking() {
/* 140 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void entityInit() {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void onUpdate() {
/* 150 */     this.prevPosX = this.posX;
/* 151 */     this.prevPosY = this.posY;
/* 152 */     this.prevPosZ = this.posZ;
/*     */     
/* 154 */     if (this.particleAge++ >= this.particleMaxAge)
/*     */     {
/* 156 */       setDead();
/*     */     }
/*     */     
/* 159 */     this.motionY -= 0.04D * this.particleGravity;
/* 160 */     moveEntity(this.motionX, this.motionY, this.motionZ);
/* 161 */     this.motionX *= 0.9800000190734863D;
/* 162 */     this.motionY *= 0.9800000190734863D;
/* 163 */     this.motionZ *= 0.9800000190734863D;
/*     */     
/* 165 */     if (this.onGround) {
/*     */       
/* 167 */       this.motionX *= 0.699999988079071D;
/* 168 */       this.motionZ *= 0.699999988079071D;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_180434_a(WorldRenderer p_180434_1_, Entity p_180434_2_, float p_180434_3_, float p_180434_4_, float p_180434_5_, float p_180434_6_, float p_180434_7_, float p_180434_8_) {
/* 174 */     float var9 = this.particleTextureIndexX / 16.0F;
/* 175 */     float var10 = var9 + 0.0624375F;
/* 176 */     float var11 = this.particleTextureIndexY / 16.0F;
/* 177 */     float var12 = var11 + 0.0624375F;
/* 178 */     float var13 = 0.1F * this.particleScale;
/*     */     
/* 180 */     if (this.particleIcon != null) {
/*     */       
/* 182 */       var9 = this.particleIcon.getMinU();
/* 183 */       var10 = this.particleIcon.getMaxU();
/* 184 */       var11 = this.particleIcon.getMinV();
/* 185 */       var12 = this.particleIcon.getMaxV();
/*     */     } 
/*     */     
/* 188 */     float var14 = (float)(this.prevPosX + (this.posX - this.prevPosX) * p_180434_3_ - interpPosX);
/* 189 */     float var15 = (float)(this.prevPosY + (this.posY - this.prevPosY) * p_180434_3_ - interpPosY);
/* 190 */     float var16 = (float)(this.prevPosZ + (this.posZ - this.prevPosZ) * p_180434_3_ - interpPosZ);
/* 191 */     p_180434_1_.func_178960_a(this.particleRed, this.particleGreen, this.particleBlue, this.particleAlpha);
/* 192 */     p_180434_1_.addVertexWithUV((var14 - p_180434_4_ * var13 - p_180434_7_ * var13), (var15 - p_180434_5_ * var13), (var16 - p_180434_6_ * var13 - p_180434_8_ * var13), var10, var12);
/* 193 */     p_180434_1_.addVertexWithUV((var14 - p_180434_4_ * var13 + p_180434_7_ * var13), (var15 + p_180434_5_ * var13), (var16 - p_180434_6_ * var13 + p_180434_8_ * var13), var10, var11);
/* 194 */     p_180434_1_.addVertexWithUV((var14 + p_180434_4_ * var13 + p_180434_7_ * var13), (var15 + p_180434_5_ * var13), (var16 + p_180434_6_ * var13 + p_180434_8_ * var13), var9, var11);
/* 195 */     p_180434_1_.addVertexWithUV((var14 + p_180434_4_ * var13 - p_180434_7_ * var13), (var15 - p_180434_5_ * var13), (var16 + p_180434_6_ * var13 - p_180434_8_ * var13), var9, var12);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getFXLayer() {
/* 200 */     return 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void writeEntityToNBT(NBTTagCompound tagCompound) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void readEntityFromNBT(NBTTagCompound tagCompund) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_180435_a(TextureAtlasSprite p_180435_1_) {
/* 215 */     int var2 = getFXLayer();
/*     */     
/* 217 */     if (var2 == 1) {
/*     */       
/* 219 */       this.particleIcon = p_180435_1_;
/*     */     }
/*     */     else {
/*     */       
/* 223 */       throw new RuntimeException("Invalid call to Particle.setTex, use coordinate methods");
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setParticleTextureIndex(int p_70536_1_) {
/* 232 */     if (getFXLayer() != 0)
/*     */     {
/* 234 */       throw new RuntimeException("Invalid call to Particle.setMiscTex");
/*     */     }
/*     */ 
/*     */     
/* 238 */     this.particleTextureIndexX = p_70536_1_ % 16;
/* 239 */     this.particleTextureIndexY = p_70536_1_ / 16;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void nextTextureIndexX() {
/* 245 */     this.particleTextureIndexX++;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canAttackWithItem() {
/* 253 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 258 */     return String.valueOf(getClass().getSimpleName()) + ", Pos (" + this.posX + "," + this.posY + "," + this.posZ + "), RGBA (" + this.particleRed + "," + this.particleGreen + "," + this.particleBlue + "," + this.particleAlpha + "), Age " + this.particleAge;
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\particle\EntityFX.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */