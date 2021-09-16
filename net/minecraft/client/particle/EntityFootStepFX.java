/*    */ package net.minecraft.client.particle;
/*    */ 
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.renderer.GlStateManager;
/*    */ import net.minecraft.client.renderer.Tessellator;
/*    */ import net.minecraft.client.renderer.WorldRenderer;
/*    */ import net.minecraft.client.renderer.texture.TextureManager;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.util.BlockPos;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ public class EntityFootStepFX
/*    */   extends EntityFX {
/* 15 */   private static final ResourceLocation field_110126_a = new ResourceLocation("textures/particle/footprint.png");
/*    */   
/*    */   private int footstepAge;
/*    */   private int footstepMaxAge;
/*    */   private TextureManager currentFootSteps;
/*    */   private static final String __OBFID = "CL_00000908";
/*    */   
/*    */   protected EntityFootStepFX(TextureManager p_i1210_1_, World worldIn, double p_i1210_3_, double p_i1210_5_, double p_i1210_7_) {
/* 23 */     super(worldIn, p_i1210_3_, p_i1210_5_, p_i1210_7_, 0.0D, 0.0D, 0.0D);
/* 24 */     this.currentFootSteps = p_i1210_1_;
/* 25 */     this.motionX = this.motionY = this.motionZ = 0.0D;
/* 26 */     this.footstepMaxAge = 200;
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_180434_a(WorldRenderer p_180434_1_, Entity p_180434_2_, float p_180434_3_, float p_180434_4_, float p_180434_5_, float p_180434_6_, float p_180434_7_, float p_180434_8_) {
/* 31 */     float var9 = (this.footstepAge + p_180434_3_) / this.footstepMaxAge;
/* 32 */     var9 *= var9;
/* 33 */     float var10 = 2.0F - var9 * 2.0F;
/*    */     
/* 35 */     if (var10 > 1.0F)
/*    */     {
/* 37 */       var10 = 1.0F;
/*    */     }
/*    */     
/* 40 */     var10 *= 0.2F;
/* 41 */     GlStateManager.disableLighting();
/* 42 */     float var11 = 0.125F;
/* 43 */     float var12 = (float)(this.posX - interpPosX);
/* 44 */     float var13 = (float)(this.posY - interpPosY);
/* 45 */     float var14 = (float)(this.posZ - interpPosZ);
/* 46 */     float var15 = this.worldObj.getLightBrightness(new BlockPos(this));
/* 47 */     this.currentFootSteps.bindTexture(field_110126_a);
/* 48 */     GlStateManager.enableBlend();
/* 49 */     GlStateManager.blendFunc(770, 771);
/* 50 */     p_180434_1_.startDrawingQuads();
/* 51 */     p_180434_1_.func_178960_a(var15, var15, var15, var10);
/* 52 */     p_180434_1_.addVertexWithUV((var12 - var11), var13, (var14 + var11), 0.0D, 1.0D);
/* 53 */     p_180434_1_.addVertexWithUV((var12 + var11), var13, (var14 + var11), 1.0D, 1.0D);
/* 54 */     p_180434_1_.addVertexWithUV((var12 + var11), var13, (var14 - var11), 1.0D, 0.0D);
/* 55 */     p_180434_1_.addVertexWithUV((var12 - var11), var13, (var14 - var11), 0.0D, 0.0D);
/* 56 */     Tessellator.getInstance().draw();
/* 57 */     GlStateManager.disableBlend();
/* 58 */     GlStateManager.enableLighting();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void onUpdate() {
/* 66 */     this.footstepAge++;
/*    */     
/* 68 */     if (this.footstepAge == this.footstepMaxAge)
/*    */     {
/* 70 */       setDead();
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public int getFXLayer() {
/* 76 */     return 3;
/*    */   }
/*    */   
/*    */   public static class Factory
/*    */     implements IParticleFactory
/*    */   {
/*    */     private static final String __OBFID = "CL_00002601";
/*    */     
/*    */     public EntityFX func_178902_a(int p_178902_1_, World worldIn, double p_178902_3_, double p_178902_5_, double p_178902_7_, double p_178902_9_, double p_178902_11_, double p_178902_13_, int... p_178902_15_) {
/* 85 */       return new EntityFootStepFX(Minecraft.getMinecraft().getTextureManager(), worldIn, p_178902_3_, p_178902_5_, p_178902_7_);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\particle\EntityFootStepFX.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */