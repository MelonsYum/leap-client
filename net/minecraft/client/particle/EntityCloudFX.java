/*    */ package net.minecraft.client.particle;
/*    */ 
/*    */ import net.minecraft.client.renderer.WorldRenderer;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.util.MathHelper;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ public class EntityCloudFX
/*    */   extends EntityFX
/*    */ {
/*    */   float field_70569_a;
/*    */   private static final String __OBFID = "CL_00000920";
/*    */   
/*    */   protected EntityCloudFX(World worldIn, double p_i1221_2_, double p_i1221_4_, double p_i1221_6_, double p_i1221_8_, double p_i1221_10_, double p_i1221_12_) {
/* 16 */     super(worldIn, p_i1221_2_, p_i1221_4_, p_i1221_6_, 0.0D, 0.0D, 0.0D);
/* 17 */     float var14 = 2.5F;
/* 18 */     this.motionX *= 0.10000000149011612D;
/* 19 */     this.motionY *= 0.10000000149011612D;
/* 20 */     this.motionZ *= 0.10000000149011612D;
/* 21 */     this.motionX += p_i1221_8_;
/* 22 */     this.motionY += p_i1221_10_;
/* 23 */     this.motionZ += p_i1221_12_;
/* 24 */     this.particleRed = this.particleGreen = this.particleBlue = 1.0F - (float)(Math.random() * 0.30000001192092896D);
/* 25 */     this.particleScale *= 0.75F;
/* 26 */     this.particleScale *= var14;
/* 27 */     this.field_70569_a = this.particleScale;
/* 28 */     this.particleMaxAge = (int)(8.0D / (Math.random() * 0.8D + 0.3D));
/* 29 */     this.particleMaxAge = (int)(this.particleMaxAge * var14);
/* 30 */     this.noClip = false;
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_180434_a(WorldRenderer p_180434_1_, Entity p_180434_2_, float p_180434_3_, float p_180434_4_, float p_180434_5_, float p_180434_6_, float p_180434_7_, float p_180434_8_) {
/* 35 */     float var9 = (this.particleAge + p_180434_3_) / this.particleMaxAge * 32.0F;
/* 36 */     var9 = MathHelper.clamp_float(var9, 0.0F, 1.0F);
/* 37 */     this.particleScale = this.field_70569_a * var9;
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
/* 55 */     setParticleTextureIndex(7 - this.particleAge * 8 / this.particleMaxAge);
/* 56 */     moveEntity(this.motionX, this.motionY, this.motionZ);
/* 57 */     this.motionX *= 0.9599999785423279D;
/* 58 */     this.motionY *= 0.9599999785423279D;
/* 59 */     this.motionZ *= 0.9599999785423279D;
/* 60 */     EntityPlayer var1 = this.worldObj.getClosestPlayerToEntity(this, 2.0D);
/*    */     
/* 62 */     if (var1 != null && this.posY > (var1.getEntityBoundingBox()).minY) {
/*    */       
/* 64 */       this.posY += ((var1.getEntityBoundingBox()).minY - this.posY) * 0.2D;
/* 65 */       this.motionY += (var1.motionY - this.motionY) * 0.2D;
/* 66 */       setPosition(this.posX, this.posY, this.posZ);
/*    */     } 
/*    */     
/* 69 */     if (this.onGround) {
/*    */       
/* 71 */       this.motionX *= 0.699999988079071D;
/* 72 */       this.motionZ *= 0.699999988079071D;
/*    */     } 
/*    */   }
/*    */   
/*    */   public static class Factory
/*    */     implements IParticleFactory
/*    */   {
/*    */     private static final String __OBFID = "CL_00002591";
/*    */     
/*    */     public EntityFX func_178902_a(int p_178902_1_, World worldIn, double p_178902_3_, double p_178902_5_, double p_178902_7_, double p_178902_9_, double p_178902_11_, double p_178902_13_, int... p_178902_15_) {
/* 82 */       return new EntityCloudFX(worldIn, p_178902_3_, p_178902_5_, p_178902_7_, p_178902_9_, p_178902_11_, p_178902_13_);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\particle\EntityCloudFX.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */