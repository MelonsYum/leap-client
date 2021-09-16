/*    */ package net.minecraft.client.particle;
/*    */ 
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ public class EntityEnchantmentTableParticleFX
/*    */   extends EntityFX
/*    */ {
/*    */   private float field_70565_a;
/*    */   private double field_70568_aq;
/*    */   private double field_70567_ar;
/*    */   private double field_70566_as;
/*    */   private static final String __OBFID = "CL_00000902";
/*    */   
/*    */   protected EntityEnchantmentTableParticleFX(World worldIn, double p_i1204_2_, double p_i1204_4_, double p_i1204_6_, double p_i1204_8_, double p_i1204_10_, double p_i1204_12_) {
/* 15 */     super(worldIn, p_i1204_2_, p_i1204_4_, p_i1204_6_, p_i1204_8_, p_i1204_10_, p_i1204_12_);
/* 16 */     this.motionX = p_i1204_8_;
/* 17 */     this.motionY = p_i1204_10_;
/* 18 */     this.motionZ = p_i1204_12_;
/* 19 */     this.field_70568_aq = p_i1204_2_;
/* 20 */     this.field_70567_ar = p_i1204_4_;
/* 21 */     this.field_70566_as = p_i1204_6_;
/* 22 */     this.posX = this.prevPosX = p_i1204_2_ + p_i1204_8_;
/* 23 */     this.posY = this.prevPosY = p_i1204_4_ + p_i1204_10_;
/* 24 */     this.posZ = this.prevPosZ = p_i1204_6_ + p_i1204_12_;
/* 25 */     float var14 = this.rand.nextFloat() * 0.6F + 0.4F;
/* 26 */     this.field_70565_a = this.particleScale = this.rand.nextFloat() * 0.5F + 0.2F;
/* 27 */     this.particleRed = this.particleGreen = this.particleBlue = 1.0F * var14;
/* 28 */     this.particleGreen *= 0.9F;
/* 29 */     this.particleRed *= 0.9F;
/* 30 */     this.particleMaxAge = (int)(Math.random() * 10.0D) + 30;
/* 31 */     this.noClip = true;
/* 32 */     setParticleTextureIndex((int)(Math.random() * 26.0D + 1.0D + 224.0D));
/*    */   }
/*    */ 
/*    */   
/*    */   public int getBrightnessForRender(float p_70070_1_) {
/* 37 */     int var2 = super.getBrightnessForRender(p_70070_1_);
/* 38 */     float var3 = this.particleAge / this.particleMaxAge;
/* 39 */     var3 *= var3;
/* 40 */     var3 *= var3;
/* 41 */     int var4 = var2 & 0xFF;
/* 42 */     int var5 = var2 >> 16 & 0xFF;
/* 43 */     var5 += (int)(var3 * 15.0F * 16.0F);
/*    */     
/* 45 */     if (var5 > 240)
/*    */     {
/* 47 */       var5 = 240;
/*    */     }
/*    */     
/* 50 */     return var4 | var5 << 16;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public float getBrightness(float p_70013_1_) {
/* 58 */     float var2 = super.getBrightness(p_70013_1_);
/* 59 */     float var3 = this.particleAge / this.particleMaxAge;
/* 60 */     var3 *= var3;
/* 61 */     var3 *= var3;
/* 62 */     return var2 * (1.0F - var3) + var3;
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
/* 73 */     float var1 = this.particleAge / this.particleMaxAge;
/* 74 */     var1 = 1.0F - var1;
/* 75 */     float var2 = 1.0F - var1;
/* 76 */     var2 *= var2;
/* 77 */     var2 *= var2;
/* 78 */     this.posX = this.field_70568_aq + this.motionX * var1;
/* 79 */     this.posY = this.field_70567_ar + this.motionY * var1 - (var2 * 1.2F);
/* 80 */     this.posZ = this.field_70566_as + this.motionZ * var1;
/*    */     
/* 82 */     if (this.particleAge++ >= this.particleMaxAge)
/*    */     {
/* 84 */       setDead();
/*    */     }
/*    */   }
/*    */   
/*    */   public static class EnchantmentTable
/*    */     implements IParticleFactory
/*    */   {
/*    */     private static final String __OBFID = "CL_00002605";
/*    */     
/*    */     public EntityFX func_178902_a(int p_178902_1_, World worldIn, double p_178902_3_, double p_178902_5_, double p_178902_7_, double p_178902_9_, double p_178902_11_, double p_178902_13_, int... p_178902_15_) {
/* 94 */       return new EntityEnchantmentTableParticleFX(worldIn, p_178902_3_, p_178902_5_, p_178902_7_, p_178902_9_, p_178902_11_, p_178902_13_);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\particle\EntityEnchantmentTableParticleFX.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */