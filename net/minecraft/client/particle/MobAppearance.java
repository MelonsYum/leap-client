/*    */ package net.minecraft.client.particle;
/*    */ 
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.renderer.GlStateManager;
/*    */ import net.minecraft.client.renderer.OpenGlHelper;
/*    */ import net.minecraft.client.renderer.WorldRenderer;
/*    */ import net.minecraft.client.renderer.entity.RenderManager;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.entity.monster.EntityGuardian;
/*    */ import net.minecraft.util.MathHelper;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ public class MobAppearance
/*    */   extends EntityFX
/*    */ {
/*    */   private EntityLivingBase field_174844_a;
/*    */   private static final String __OBFID = "CL_00002594";
/*    */   
/*    */   protected MobAppearance(World worldIn, double p_i46283_2_, double p_i46283_4_, double p_i46283_6_) {
/* 21 */     super(worldIn, p_i46283_2_, p_i46283_4_, p_i46283_6_, 0.0D, 0.0D, 0.0D);
/* 22 */     this.particleRed = this.particleGreen = this.particleBlue = 1.0F;
/* 23 */     this.motionX = this.motionY = this.motionZ = 0.0D;
/* 24 */     this.particleGravity = 0.0F;
/* 25 */     this.particleMaxAge = 30;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getFXLayer() {
/* 30 */     return 3;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void onUpdate() {
/* 38 */     super.onUpdate();
/*    */     
/* 40 */     if (this.field_174844_a == null) {
/*    */       
/* 42 */       EntityGuardian var1 = new EntityGuardian(this.worldObj);
/* 43 */       var1.func_175465_cm();
/* 44 */       this.field_174844_a = (EntityLivingBase)var1;
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_180434_a(WorldRenderer p_180434_1_, Entity p_180434_2_, float p_180434_3_, float p_180434_4_, float p_180434_5_, float p_180434_6_, float p_180434_7_, float p_180434_8_) {
/* 50 */     if (this.field_174844_a != null) {
/*    */       
/* 52 */       RenderManager var9 = Minecraft.getMinecraft().getRenderManager();
/* 53 */       var9.func_178628_a(EntityFX.interpPosX, EntityFX.interpPosY, EntityFX.interpPosZ);
/* 54 */       float var10 = 0.42553192F;
/* 55 */       float var11 = (this.particleAge + p_180434_3_) / this.particleMaxAge;
/* 56 */       GlStateManager.depthMask(true);
/* 57 */       GlStateManager.enableBlend();
/* 58 */       GlStateManager.enableDepth();
/* 59 */       GlStateManager.blendFunc(770, 771);
/* 60 */       float var12 = 240.0F;
/* 61 */       OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, var12, var12);
/* 62 */       GlStateManager.pushMatrix();
/* 63 */       float var13 = 0.05F + 0.5F * MathHelper.sin(var11 * 3.1415927F);
/* 64 */       GlStateManager.color(1.0F, 1.0F, 1.0F, var13);
/* 65 */       GlStateManager.translate(0.0F, 1.8F, 0.0F);
/* 66 */       GlStateManager.rotate(180.0F - p_180434_2_.rotationYaw, 0.0F, 1.0F, 0.0F);
/* 67 */       GlStateManager.rotate(60.0F - 150.0F * var11 - p_180434_2_.rotationPitch, 1.0F, 0.0F, 0.0F);
/* 68 */       GlStateManager.translate(0.0F, -0.4F, -1.5F);
/* 69 */       GlStateManager.scale(var10, var10, var10);
/* 70 */       this.field_174844_a.rotationYaw = this.field_174844_a.prevRotationYaw = 0.0F;
/* 71 */       this.field_174844_a.rotationYawHead = this.field_174844_a.prevRotationYawHead = 0.0F;
/* 72 */       var9.renderEntityWithPosYaw((Entity)this.field_174844_a, 0.0D, 0.0D, 0.0D, 0.0F, p_180434_3_);
/* 73 */       GlStateManager.popMatrix();
/* 74 */       GlStateManager.enableDepth();
/*    */     } 
/*    */   }
/*    */   
/*    */   public static class Factory
/*    */     implements IParticleFactory
/*    */   {
/*    */     private static final String __OBFID = "CL_00002593";
/*    */     
/*    */     public EntityFX func_178902_a(int p_178902_1_, World worldIn, double p_178902_3_, double p_178902_5_, double p_178902_7_, double p_178902_9_, double p_178902_11_, double p_178902_13_, int... p_178902_15_) {
/* 84 */       return new MobAppearance(worldIn, p_178902_3_, p_178902_5_, p_178902_7_);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\particle\MobAppearance.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */