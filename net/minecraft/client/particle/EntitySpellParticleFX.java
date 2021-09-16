/*     */ package net.minecraft.client.particle;
/*     */ 
/*     */ import java.util.Random;
/*     */ import net.minecraft.client.renderer.WorldRenderer;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class EntitySpellParticleFX
/*     */   extends EntityFX {
/*  11 */   private static final Random field_174848_a = new Random();
/*     */ 
/*     */   
/*  14 */   private int baseSpellTextureIndex = 128;
/*     */   
/*     */   private static final String __OBFID = "CL_00000926";
/*     */   
/*     */   protected EntitySpellParticleFX(World worldIn, double p_i1229_2_, double p_i1229_4_, double p_i1229_6_, double p_i1229_8_, double p_i1229_10_, double p_i1229_12_) {
/*  19 */     super(worldIn, p_i1229_2_, p_i1229_4_, p_i1229_6_, 0.5D - field_174848_a.nextDouble(), p_i1229_10_, 0.5D - field_174848_a.nextDouble());
/*  20 */     this.motionY *= 0.20000000298023224D;
/*     */     
/*  22 */     if (p_i1229_8_ == 0.0D && p_i1229_12_ == 0.0D) {
/*     */       
/*  24 */       this.motionX *= 0.10000000149011612D;
/*  25 */       this.motionZ *= 0.10000000149011612D;
/*     */     } 
/*     */     
/*  28 */     this.particleScale *= 0.75F;
/*  29 */     this.particleMaxAge = (int)(8.0D / (Math.random() * 0.8D + 0.2D));
/*  30 */     this.noClip = false;
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_180434_a(WorldRenderer p_180434_1_, Entity p_180434_2_, float p_180434_3_, float p_180434_4_, float p_180434_5_, float p_180434_6_, float p_180434_7_, float p_180434_8_) {
/*  35 */     float var9 = (this.particleAge + p_180434_3_) / this.particleMaxAge * 32.0F;
/*  36 */     var9 = MathHelper.clamp_float(var9, 0.0F, 1.0F);
/*  37 */     super.func_180434_a(p_180434_1_, p_180434_2_, p_180434_3_, p_180434_4_, p_180434_5_, p_180434_6_, p_180434_7_, p_180434_8_);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onUpdate() {
/*  45 */     this.prevPosX = this.posX;
/*  46 */     this.prevPosY = this.posY;
/*  47 */     this.prevPosZ = this.posZ;
/*     */     
/*  49 */     if (this.particleAge++ >= this.particleMaxAge)
/*     */     {
/*  51 */       setDead();
/*     */     }
/*     */     
/*  54 */     setParticleTextureIndex(this.baseSpellTextureIndex + 7 - this.particleAge * 8 / this.particleMaxAge);
/*  55 */     this.motionY += 0.004D;
/*  56 */     moveEntity(this.motionX, this.motionY, this.motionZ);
/*     */     
/*  58 */     if (this.posY == this.prevPosY) {
/*     */       
/*  60 */       this.motionX *= 1.1D;
/*  61 */       this.motionZ *= 1.1D;
/*     */     } 
/*     */     
/*  64 */     this.motionX *= 0.9599999785423279D;
/*  65 */     this.motionY *= 0.9599999785423279D;
/*  66 */     this.motionZ *= 0.9599999785423279D;
/*     */     
/*  68 */     if (this.onGround) {
/*     */       
/*  70 */       this.motionX *= 0.699999988079071D;
/*  71 */       this.motionZ *= 0.699999988079071D;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setBaseSpellTextureIndex(int p_70589_1_) {
/*  80 */     this.baseSpellTextureIndex = p_70589_1_;
/*     */   }
/*     */   
/*     */   public static class AmbientMobFactory
/*     */     implements IParticleFactory
/*     */   {
/*     */     private static final String __OBFID = "CL_00002585";
/*     */     
/*     */     public EntityFX func_178902_a(int p_178902_1_, World worldIn, double p_178902_3_, double p_178902_5_, double p_178902_7_, double p_178902_9_, double p_178902_11_, double p_178902_13_, int... p_178902_15_) {
/*  89 */       EntitySpellParticleFX var16 = new EntitySpellParticleFX(worldIn, p_178902_3_, p_178902_5_, p_178902_7_, p_178902_9_, p_178902_11_, p_178902_13_);
/*  90 */       var16.setAlphaF(0.15F);
/*  91 */       var16.setRBGColorF((float)p_178902_9_, (float)p_178902_11_, (float)p_178902_13_);
/*  92 */       return var16;
/*     */     }
/*     */   }
/*     */   
/*     */   public static class Factory
/*     */     implements IParticleFactory
/*     */   {
/*     */     private static final String __OBFID = "CL_00002582";
/*     */     
/*     */     public EntityFX func_178902_a(int p_178902_1_, World worldIn, double p_178902_3_, double p_178902_5_, double p_178902_7_, double p_178902_9_, double p_178902_11_, double p_178902_13_, int... p_178902_15_) {
/* 102 */       return new EntitySpellParticleFX(worldIn, p_178902_3_, p_178902_5_, p_178902_7_, p_178902_9_, p_178902_11_, p_178902_13_);
/*     */     }
/*     */   }
/*     */   
/*     */   public static class InstantFactory
/*     */     implements IParticleFactory
/*     */   {
/*     */     private static final String __OBFID = "CL_00002584";
/*     */     
/*     */     public EntityFX func_178902_a(int p_178902_1_, World worldIn, double p_178902_3_, double p_178902_5_, double p_178902_7_, double p_178902_9_, double p_178902_11_, double p_178902_13_, int... p_178902_15_) {
/* 112 */       EntitySpellParticleFX var16 = new EntitySpellParticleFX(worldIn, p_178902_3_, p_178902_5_, p_178902_7_, p_178902_9_, p_178902_11_, p_178902_13_);
/* 113 */       var16.setBaseSpellTextureIndex(144);
/* 114 */       return var16;
/*     */     }
/*     */   }
/*     */   
/*     */   public static class MobFactory
/*     */     implements IParticleFactory
/*     */   {
/*     */     private static final String __OBFID = "CL_00002583";
/*     */     
/*     */     public EntityFX func_178902_a(int p_178902_1_, World worldIn, double p_178902_3_, double p_178902_5_, double p_178902_7_, double p_178902_9_, double p_178902_11_, double p_178902_13_, int... p_178902_15_) {
/* 124 */       EntitySpellParticleFX var16 = new EntitySpellParticleFX(worldIn, p_178902_3_, p_178902_5_, p_178902_7_, p_178902_9_, p_178902_11_, p_178902_13_);
/* 125 */       var16.setRBGColorF((float)p_178902_9_, (float)p_178902_11_, (float)p_178902_13_);
/* 126 */       return var16;
/*     */     }
/*     */   }
/*     */   
/*     */   public static class WitchFactory
/*     */     implements IParticleFactory
/*     */   {
/*     */     private static final String __OBFID = "CL_00002581";
/*     */     
/*     */     public EntityFX func_178902_a(int p_178902_1_, World worldIn, double p_178902_3_, double p_178902_5_, double p_178902_7_, double p_178902_9_, double p_178902_11_, double p_178902_13_, int... p_178902_15_) {
/* 136 */       EntitySpellParticleFX var16 = new EntitySpellParticleFX(worldIn, p_178902_3_, p_178902_5_, p_178902_7_, p_178902_9_, p_178902_11_, p_178902_13_);
/* 137 */       var16.setBaseSpellTextureIndex(144);
/* 138 */       float var17 = worldIn.rand.nextFloat() * 0.5F + 0.35F;
/* 139 */       var16.setRBGColorF(1.0F * var17, 0.0F * var17, 1.0F * var17);
/* 140 */       return var16;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\particle\EntitySpellParticleFX.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */