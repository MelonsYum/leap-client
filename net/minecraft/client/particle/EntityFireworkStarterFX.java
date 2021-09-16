/*     */ package net.minecraft.client.particle;
/*     */ 
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.renderer.WorldRenderer;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.item.ItemDye;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.nbt.NBTTagList;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class EntityFireworkStarterFX
/*     */   extends EntityFX
/*     */ {
/*     */   private int fireworkAge;
/*     */   private final EffectRenderer theEffectRenderer;
/*     */   private NBTTagList fireworkExplosions;
/*     */   boolean twinkle;
/*     */   private static final String __OBFID = "CL_00000906";
/*     */   
/*     */   public EntityFireworkStarterFX(World worldIn, double p_i46355_2_, double p_i46355_4_, double p_i46355_6_, double p_i46355_8_, double p_i46355_10_, double p_i46355_12_, EffectRenderer p_i46355_14_, NBTTagCompound p_i46355_15_) {
/*  22 */     super(worldIn, p_i46355_2_, p_i46355_4_, p_i46355_6_, 0.0D, 0.0D, 0.0D);
/*  23 */     this.motionX = p_i46355_8_;
/*  24 */     this.motionY = p_i46355_10_;
/*  25 */     this.motionZ = p_i46355_12_;
/*  26 */     this.theEffectRenderer = p_i46355_14_;
/*  27 */     this.particleMaxAge = 8;
/*     */     
/*  29 */     if (p_i46355_15_ != null) {
/*     */       
/*  31 */       this.fireworkExplosions = p_i46355_15_.getTagList("Explosions", 10);
/*     */       
/*  33 */       if (this.fireworkExplosions.tagCount() == 0) {
/*     */         
/*  35 */         this.fireworkExplosions = null;
/*     */       }
/*     */       else {
/*     */         
/*  39 */         this.particleMaxAge = this.fireworkExplosions.tagCount() * 2 - 1;
/*     */         
/*  41 */         for (int var16 = 0; var16 < this.fireworkExplosions.tagCount(); var16++) {
/*     */           
/*  43 */           NBTTagCompound var17 = this.fireworkExplosions.getCompoundTagAt(var16);
/*     */           
/*  45 */           if (var17.getBoolean("Flicker")) {
/*     */             
/*  47 */             this.twinkle = true;
/*  48 */             this.particleMaxAge += 15;
/*     */             break;
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_180434_a(WorldRenderer p_180434_1_, Entity p_180434_2_, float p_180434_3_, float p_180434_4_, float p_180434_5_, float p_180434_6_, float p_180434_7_, float p_180434_8_) {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onUpdate() {
/*  65 */     if (this.fireworkAge == 0 && this.fireworkExplosions != null) {
/*     */       
/*  67 */       boolean var1 = func_92037_i();
/*  68 */       boolean var2 = false;
/*     */       
/*  70 */       if (this.fireworkExplosions.tagCount() >= 3) {
/*     */         
/*  72 */         var2 = true;
/*     */       }
/*     */       else {
/*     */         
/*  76 */         for (int var3 = 0; var3 < this.fireworkExplosions.tagCount(); var3++) {
/*     */           
/*  78 */           NBTTagCompound var4 = this.fireworkExplosions.getCompoundTagAt(var3);
/*     */           
/*  80 */           if (var4.getByte("Type") == 1) {
/*     */             
/*  82 */             var2 = true;
/*     */             
/*     */             break;
/*     */           } 
/*     */         } 
/*     */       } 
/*  88 */       String var16 = "fireworks." + (var2 ? "largeBlast" : "blast") + (var1 ? "_far" : "");
/*  89 */       this.worldObj.playSound(this.posX, this.posY, this.posZ, var16, 20.0F, 0.95F + this.rand.nextFloat() * 0.1F, true);
/*     */     } 
/*     */     
/*  92 */     if (this.fireworkAge % 2 == 0 && this.fireworkExplosions != null && this.fireworkAge / 2 < this.fireworkExplosions.tagCount()) {
/*     */       
/*  94 */       int var13 = this.fireworkAge / 2;
/*  95 */       NBTTagCompound var14 = this.fireworkExplosions.getCompoundTagAt(var13);
/*  96 */       byte var17 = var14.getByte("Type");
/*  97 */       boolean var18 = var14.getBoolean("Trail");
/*  98 */       boolean var5 = var14.getBoolean("Flicker");
/*  99 */       int[] var6 = var14.getIntArray("Colors");
/* 100 */       int[] var7 = var14.getIntArray("FadeColors");
/*     */       
/* 102 */       if (var6.length == 0)
/*     */       {
/* 104 */         var6 = new int[] { ItemDye.dyeColors[0] };
/*     */       }
/*     */       
/* 107 */       if (var17 == 1) {
/*     */         
/* 109 */         createBall(0.5D, 4, var6, var7, var18, var5);
/*     */       }
/* 111 */       else if (var17 == 2) {
/*     */         
/* 113 */         createShaped(0.5D, new double[][] { { 0.0D, 1.0D }, , { 0.3455D, 0.309D }, , { 0.9511D, 0.309D }, , { 0.3795918367346939D, -0.12653061224489795D }, , { 0.6122448979591837D, -0.8040816326530612D }, , { 0.0D, -0.35918367346938773D },  }, var6, var7, var18, var5, false);
/*     */       }
/* 115 */       else if (var17 == 3) {
/*     */         
/* 117 */         createShaped(0.5D, new double[][] { { 0.0D, 0.2D }, , { 0.2D, 0.2D }, , { 0.2D, 0.6D }, , { 0.6D, 0.6D }, , { 0.6D, 0.2D }, , { 0.2D, 0.2D }, , { 0.2D, 0.0D }, , { 0.4D, 0.0D }, , { 0.4D, -0.6D }, , { 0.2D, -0.6D }, , { 0.2D, -0.4D }, , { 0.0D, -0.4D },  }, var6, var7, var18, var5, true);
/*     */       }
/* 119 */       else if (var17 == 4) {
/*     */         
/* 121 */         createBurst(var6, var7, var18, var5);
/*     */       }
/*     */       else {
/*     */         
/* 125 */         createBall(0.25D, 2, var6, var7, var18, var5);
/*     */       } 
/*     */       
/* 128 */       int var8 = var6[0];
/* 129 */       float var9 = ((var8 & 0xFF0000) >> 16) / 255.0F;
/* 130 */       float var10 = ((var8 & 0xFF00) >> 8) / 255.0F;
/* 131 */       float var11 = ((var8 & 0xFF) >> 0) / 255.0F;
/* 132 */       EntityFireworkOverlayFX var12 = new EntityFireworkOverlayFX(this.worldObj, this.posX, this.posY, this.posZ);
/* 133 */       var12.setRBGColorF(var9, var10, var11);
/* 134 */       this.theEffectRenderer.addEffect(var12);
/*     */     } 
/*     */     
/* 137 */     this.fireworkAge++;
/*     */     
/* 139 */     if (this.fireworkAge > this.particleMaxAge) {
/*     */       
/* 141 */       if (this.twinkle) {
/*     */         
/* 143 */         boolean var1 = func_92037_i();
/* 144 */         String var15 = "fireworks." + (var1 ? "twinkle_far" : "twinkle");
/* 145 */         this.worldObj.playSound(this.posX, this.posY, this.posZ, var15, 20.0F, 0.9F + this.rand.nextFloat() * 0.15F, true);
/*     */       } 
/*     */       
/* 148 */       setDead();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean func_92037_i() {
/* 154 */     Minecraft var1 = Minecraft.getMinecraft();
/* 155 */     return !(var1 != null && var1.func_175606_aa() != null && var1.func_175606_aa().getDistanceSq(this.posX, this.posY, this.posZ) < 256.0D);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void createParticle(double p_92034_1_, double p_92034_3_, double p_92034_5_, double p_92034_7_, double p_92034_9_, double p_92034_11_, int[] p_92034_13_, int[] p_92034_14_, boolean p_92034_15_, boolean p_92034_16_) {
/* 164 */     EntityFireworkSparkFX var17 = new EntityFireworkSparkFX(this.worldObj, p_92034_1_, p_92034_3_, p_92034_5_, p_92034_7_, p_92034_9_, p_92034_11_, this.theEffectRenderer);
/* 165 */     var17.setAlphaF(0.99F);
/* 166 */     var17.setTrail(p_92034_15_);
/* 167 */     var17.setTwinkle(p_92034_16_);
/* 168 */     int var18 = this.rand.nextInt(p_92034_13_.length);
/* 169 */     var17.setColour(p_92034_13_[var18]);
/*     */     
/* 171 */     if (p_92034_14_ != null && p_92034_14_.length > 0)
/*     */     {
/* 173 */       var17.setFadeColour(p_92034_14_[this.rand.nextInt(p_92034_14_.length)]);
/*     */     }
/*     */     
/* 176 */     this.theEffectRenderer.addEffect(var17);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void createBall(double p_92035_1_, int p_92035_3_, int[] p_92035_4_, int[] p_92035_5_, boolean p_92035_6_, boolean p_92035_7_) {
/* 185 */     double var8 = this.posX;
/* 186 */     double var10 = this.posY;
/* 187 */     double var12 = this.posZ;
/*     */     
/* 189 */     for (int var14 = -p_92035_3_; var14 <= p_92035_3_; var14++) {
/*     */       
/* 191 */       for (int var15 = -p_92035_3_; var15 <= p_92035_3_; var15++) {
/*     */         
/* 193 */         for (int var16 = -p_92035_3_; var16 <= p_92035_3_; var16++) {
/*     */           
/* 195 */           double var17 = var15 + (this.rand.nextDouble() - this.rand.nextDouble()) * 0.5D;
/* 196 */           double var19 = var14 + (this.rand.nextDouble() - this.rand.nextDouble()) * 0.5D;
/* 197 */           double var21 = var16 + (this.rand.nextDouble() - this.rand.nextDouble()) * 0.5D;
/* 198 */           double var23 = MathHelper.sqrt_double(var17 * var17 + var19 * var19 + var21 * var21) / p_92035_1_ + this.rand.nextGaussian() * 0.05D;
/* 199 */           createParticle(var8, var10, var12, var17 / var23, var19 / var23, var21 / var23, p_92035_4_, p_92035_5_, p_92035_6_, p_92035_7_);
/*     */           
/* 201 */           if (var14 != -p_92035_3_ && var14 != p_92035_3_ && var15 != -p_92035_3_ && var15 != p_92035_3_)
/*     */           {
/* 203 */             var16 += p_92035_3_ * 2 - 1;
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void createShaped(double p_92038_1_, double[][] p_92038_3_, int[] p_92038_4_, int[] p_92038_5_, boolean p_92038_6_, boolean p_92038_7_, boolean p_92038_8_) {
/* 216 */     double var9 = p_92038_3_[0][0];
/* 217 */     double var11 = p_92038_3_[0][1];
/* 218 */     createParticle(this.posX, this.posY, this.posZ, var9 * p_92038_1_, var11 * p_92038_1_, 0.0D, p_92038_4_, p_92038_5_, p_92038_6_, p_92038_7_);
/* 219 */     float var13 = this.rand.nextFloat() * 3.1415927F;
/* 220 */     double var14 = p_92038_8_ ? 0.034D : 0.34D;
/*     */     
/* 222 */     for (int var16 = 0; var16 < 3; var16++) {
/*     */       
/* 224 */       double var17 = var13 + (var16 * 3.1415927F) * var14;
/* 225 */       double var19 = var9;
/* 226 */       double var21 = var11;
/*     */       
/* 228 */       for (int var23 = 1; var23 < p_92038_3_.length; var23++) {
/*     */         
/* 230 */         double var24 = p_92038_3_[var23][0];
/* 231 */         double var26 = p_92038_3_[var23][1];
/*     */         
/* 233 */         for (double var28 = 0.25D; var28 <= 1.0D; var28 += 0.25D) {
/*     */           
/* 235 */           double var30 = (var19 + (var24 - var19) * var28) * p_92038_1_;
/* 236 */           double var32 = (var21 + (var26 - var21) * var28) * p_92038_1_;
/* 237 */           double var34 = var30 * Math.sin(var17);
/* 238 */           var30 *= Math.cos(var17);
/*     */           
/* 240 */           for (double var36 = -1.0D; var36 <= 1.0D; var36 += 2.0D)
/*     */           {
/* 242 */             createParticle(this.posX, this.posY, this.posZ, var30 * var36, var32, var34 * var36, p_92038_4_, p_92038_5_, p_92038_6_, p_92038_7_);
/*     */           }
/*     */         } 
/*     */         
/* 246 */         var19 = var24;
/* 247 */         var21 = var26;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void createBurst(int[] p_92036_1_, int[] p_92036_2_, boolean p_92036_3_, boolean p_92036_4_) {
/* 257 */     double var5 = this.rand.nextGaussian() * 0.05D;
/* 258 */     double var7 = this.rand.nextGaussian() * 0.05D;
/*     */     
/* 260 */     for (int var9 = 0; var9 < 70; var9++) {
/*     */       
/* 262 */       double var10 = this.motionX * 0.5D + this.rand.nextGaussian() * 0.15D + var5;
/* 263 */       double var12 = this.motionZ * 0.5D + this.rand.nextGaussian() * 0.15D + var7;
/* 264 */       double var14 = this.motionY * 0.5D + this.rand.nextDouble() * 0.5D;
/* 265 */       createParticle(this.posX, this.posY, this.posZ, var10, var14, var12, p_92036_1_, p_92036_2_, p_92036_3_, p_92036_4_);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public int getFXLayer() {
/* 271 */     return 0;
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\particle\EntityFireworkStarterFX.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */