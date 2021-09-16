/*    */ package net.minecraft.client.renderer.entity.layers;
/*    */ import net.minecraft.client.renderer.GlStateManager;
/*    */ import net.minecraft.client.renderer.OpenGlHelper;
/*    */ import net.minecraft.client.renderer.entity.RenderDragon;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntityLiving;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.entity.boss.EntityDragon;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import optifine.Config;
/*    */ import shadersmod.client.Shaders;
/*    */ 
/*    */ public class LayerEnderDragonEyes implements LayerRenderer {
/* 14 */   private static final ResourceLocation TEXTURE = new ResourceLocation("textures/entity/enderdragon/dragon_eyes.png");
/*    */   
/*    */   private final RenderDragon dragonRenderer;
/*    */   private static final String __OBFID = "CL_00002419";
/*    */   
/*    */   public LayerEnderDragonEyes(RenderDragon p_i46118_1_) {
/* 20 */     this.dragonRenderer = p_i46118_1_;
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_177210_a(EntityDragon p_177210_1_, float p_177210_2_, float p_177210_3_, float p_177210_4_, float p_177210_5_, float p_177210_6_, float p_177210_7_, float p_177210_8_) {
/* 25 */     this.dragonRenderer.bindTexture(TEXTURE);
/* 26 */     GlStateManager.enableBlend();
/* 27 */     GlStateManager.disableAlpha();
/* 28 */     GlStateManager.blendFunc(1, 1);
/* 29 */     GlStateManager.disableLighting();
/* 30 */     GlStateManager.depthFunc(514);
/* 31 */     char var9 = '';
/* 32 */     int var10 = var9 % 65536;
/* 33 */     int var11 = var9 / 65536;
/* 34 */     OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, var10 / 1.0F, var11 / 1.0F);
/* 35 */     GlStateManager.enableLighting();
/* 36 */     GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
/*    */     
/* 38 */     if (Config.isShaders())
/*    */     {
/* 40 */       Shaders.beginSpiderEyes();
/*    */     }
/*    */     
/* 43 */     this.dragonRenderer.getMainModel().render((Entity)p_177210_1_, p_177210_2_, p_177210_3_, p_177210_5_, p_177210_6_, p_177210_7_, p_177210_8_);
/* 44 */     this.dragonRenderer.func_177105_a((EntityLiving)p_177210_1_, p_177210_4_);
/* 45 */     GlStateManager.disableBlend();
/* 46 */     GlStateManager.enableAlpha();
/* 47 */     GlStateManager.depthFunc(515);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean shouldCombineTextures() {
/* 52 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public void doRenderLayer(EntityLivingBase p_177141_1_, float p_177141_2_, float p_177141_3_, float p_177141_4_, float p_177141_5_, float p_177141_6_, float p_177141_7_, float p_177141_8_) {
/* 57 */     func_177210_a((EntityDragon)p_177141_1_, p_177141_2_, p_177141_3_, p_177141_4_, p_177141_5_, p_177141_6_, p_177141_7_, p_177141_8_);
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\renderer\entity\layers\LayerEnderDragonEyes.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */