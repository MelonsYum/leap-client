/*    */ package net.minecraft.client.particle;
/*    */ 
/*    */ import net.minecraft.client.renderer.WorldRenderer;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.util.EnumParticleTypes;
/*    */ import net.minecraft.util.MathHelper;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ public class EntityLavaFX
/*    */   extends EntityFX
/*    */ {
/*    */   private float lavaParticleScale;
/*    */   private static final String __OBFID = "CL_00000912";
/*    */   
/*    */   protected EntityLavaFX(World worldIn, double p_i1215_2_, double p_i1215_4_, double p_i1215_6_) {
/* 16 */     super(worldIn, p_i1215_2_, p_i1215_4_, p_i1215_6_, 0.0D, 0.0D, 0.0D);
/* 17 */     this.motionX *= 0.800000011920929D;
/* 18 */     this.motionY *= 0.800000011920929D;
/* 19 */     this.motionZ *= 0.800000011920929D;
/* 20 */     this.motionY = (this.rand.nextFloat() * 0.4F + 0.05F);
/* 21 */     this.particleRed = this.particleGreen = this.particleBlue = 1.0F;
/* 22 */     this.particleScale *= this.rand.nextFloat() * 2.0F + 0.2F;
/* 23 */     this.lavaParticleScale = this.particleScale;
/* 24 */     this.particleMaxAge = (int)(16.0D / (Math.random() * 0.8D + 0.2D));
/* 25 */     this.noClip = false;
/* 26 */     setParticleTextureIndex(49);
/*    */   }
/*    */ 
/*    */   
/*    */   public int getBrightnessForRender(float p_70070_1_) {
/* 31 */     float var2 = (this.particleAge + p_70070_1_) / this.particleMaxAge;
/* 32 */     var2 = MathHelper.clamp_float(var2, 0.0F, 1.0F);
/* 33 */     int var3 = super.getBrightnessForRender(p_70070_1_);
/* 34 */     short var4 = 240;
/* 35 */     int var5 = var3 >> 16 & 0xFF;
/* 36 */     return var4 | var5 << 16;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public float getBrightness(float p_70013_1_) {
/* 44 */     return 1.0F;
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_180434_a(WorldRenderer p_180434_1_, Entity p_180434_2_, float p_180434_3_, float p_180434_4_, float p_180434_5_, float p_180434_6_, float p_180434_7_, float p_180434_8_) {
/* 49 */     float var9 = (this.particleAge + p_180434_3_) / this.particleMaxAge;
/* 50 */     this.particleScale = this.lavaParticleScale * (1.0F - var9 * var9);
/* 51 */     super.func_180434_a(p_180434_1_, p_180434_2_, p_180434_3_, p_180434_4_, p_180434_5_, p_180434_6_, p_180434_7_, p_180434_8_);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void onUpdate() {
/* 59 */     this.prevPosX = this.posX;
/* 60 */     this.prevPosY = this.posY;
/* 61 */     this.prevPosZ = this.posZ;
/*    */     
/* 63 */     if (this.particleAge++ >= this.particleMaxAge)
/*    */     {
/* 65 */       setDead();
/*    */     }
/*    */     
/* 68 */     float var1 = this.particleAge / this.particleMaxAge;
/*    */     
/* 70 */     if (this.rand.nextFloat() > var1)
/*    */     {
/* 72 */       this.worldObj.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, this.posX, this.posY, this.posZ, this.motionX, this.motionY, this.motionZ, new int[0]);
/*    */     }
/*    */     
/* 75 */     this.motionY -= 0.03D;
/* 76 */     moveEntity(this.motionX, this.motionY, this.motionZ);
/* 77 */     this.motionX *= 0.9990000128746033D;
/* 78 */     this.motionY *= 0.9990000128746033D;
/* 79 */     this.motionZ *= 0.9990000128746033D;
/*    */     
/* 81 */     if (this.onGround) {
/*    */       
/* 83 */       this.motionX *= 0.699999988079071D;
/* 84 */       this.motionZ *= 0.699999988079071D;
/*    */     } 
/*    */   }
/*    */   
/*    */   public static class Factory
/*    */     implements IParticleFactory
/*    */   {
/*    */     private static final String __OBFID = "CL_00002595";
/*    */     
/*    */     public EntityFX func_178902_a(int p_178902_1_, World worldIn, double p_178902_3_, double p_178902_5_, double p_178902_7_, double p_178902_9_, double p_178902_11_, double p_178902_13_, int... p_178902_15_) {
/* 94 */       return new EntityLavaFX(worldIn, p_178902_3_, p_178902_5_, p_178902_7_);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\particle\EntityLavaFX.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */