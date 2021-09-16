/*    */ package net.minecraft.client.particle;
/*    */ 
/*    */ import net.minecraft.client.renderer.WorldRenderer;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.util.MathHelper;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ public class EntityHeartFX
/*    */   extends EntityFX
/*    */ {
/*    */   float particleScaleOverTime;
/*    */   private static final String __OBFID = "CL_00000909";
/*    */   
/*    */   protected EntityHeartFX(World worldIn, double p_i1211_2_, double p_i1211_4_, double p_i1211_6_, double p_i1211_8_, double p_i1211_10_, double p_i1211_12_) {
/* 15 */     this(worldIn, p_i1211_2_, p_i1211_4_, p_i1211_6_, p_i1211_8_, p_i1211_10_, p_i1211_12_, 2.0F);
/*    */   }
/*    */ 
/*    */   
/*    */   protected EntityHeartFX(World worldIn, double p_i46354_2_, double p_i46354_4_, double p_i46354_6_, double p_i46354_8_, double p_i46354_10_, double p_i46354_12_, float p_i46354_14_) {
/* 20 */     super(worldIn, p_i46354_2_, p_i46354_4_, p_i46354_6_, 0.0D, 0.0D, 0.0D);
/* 21 */     this.motionX *= 0.009999999776482582D;
/* 22 */     this.motionY *= 0.009999999776482582D;
/* 23 */     this.motionZ *= 0.009999999776482582D;
/* 24 */     this.motionY += 0.1D;
/* 25 */     this.particleScale *= 0.75F;
/* 26 */     this.particleScale *= p_i46354_14_;
/* 27 */     this.particleScaleOverTime = this.particleScale;
/* 28 */     this.particleMaxAge = 16;
/* 29 */     this.noClip = false;
/* 30 */     setParticleTextureIndex(80);
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_180434_a(WorldRenderer p_180434_1_, Entity p_180434_2_, float p_180434_3_, float p_180434_4_, float p_180434_5_, float p_180434_6_, float p_180434_7_, float p_180434_8_) {
/* 35 */     float var9 = (this.particleAge + p_180434_3_) / this.particleMaxAge * 32.0F;
/* 36 */     var9 = MathHelper.clamp_float(var9, 0.0F, 1.0F);
/* 37 */     this.particleScale = this.particleScaleOverTime * var9;
/* 38 */     super.func_180434_a(p_180434_1_, p_180434_2_, p_180434_3_, p_180434_4_, p_180434_5_, p_180434_6_, p_180434_7_, p_180434_8_);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void onUpdate() {
/* 46 */     this.prevPosX = this.posX;
/* 47 */     this.prevPosY = this.posY;
/* 48 */     this.prevPosZ = this.posZ;
/*    */     
/* 50 */     if (this.particleAge++ >= this.particleMaxAge)
/*    */     {
/* 52 */       setDead();
/*    */     }
/*    */     
/* 55 */     moveEntity(this.motionX, this.motionY, this.motionZ);
/*    */     
/* 57 */     if (this.posY == this.prevPosY) {
/*    */       
/* 59 */       this.motionX *= 1.1D;
/* 60 */       this.motionZ *= 1.1D;
/*    */     } 
/*    */     
/* 63 */     this.motionX *= 0.8600000143051147D;
/* 64 */     this.motionY *= 0.8600000143051147D;
/* 65 */     this.motionZ *= 0.8600000143051147D;
/*    */     
/* 67 */     if (this.onGround) {
/*    */       
/* 69 */       this.motionX *= 0.699999988079071D;
/* 70 */       this.motionZ *= 0.699999988079071D;
/*    */     } 
/*    */   }
/*    */   
/*    */   public static class AngryVillagerFactory
/*    */     implements IParticleFactory
/*    */   {
/*    */     private static final String __OBFID = "CL_00002600";
/*    */     
/*    */     public EntityFX func_178902_a(int p_178902_1_, World worldIn, double p_178902_3_, double p_178902_5_, double p_178902_7_, double p_178902_9_, double p_178902_11_, double p_178902_13_, int... p_178902_15_) {
/* 80 */       EntityHeartFX var16 = new EntityHeartFX(worldIn, p_178902_3_, p_178902_5_ + 0.5D, p_178902_7_, p_178902_9_, p_178902_11_, p_178902_13_);
/* 81 */       var16.setParticleTextureIndex(81);
/* 82 */       var16.setRBGColorF(1.0F, 1.0F, 1.0F);
/* 83 */       return var16;
/*    */     }
/*    */   }
/*    */   
/*    */   public static class Factory
/*    */     implements IParticleFactory
/*    */   {
/*    */     private static final String __OBFID = "CL_00002599";
/*    */     
/*    */     public EntityFX func_178902_a(int p_178902_1_, World worldIn, double p_178902_3_, double p_178902_5_, double p_178902_7_, double p_178902_9_, double p_178902_11_, double p_178902_13_, int... p_178902_15_) {
/* 93 */       return new EntityHeartFX(worldIn, p_178902_3_, p_178902_5_, p_178902_7_, p_178902_9_, p_178902_11_, p_178902_13_);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\particle\EntityHeartFX.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */