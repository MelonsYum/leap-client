/*    */ package net.minecraft.client.renderer.entity.layers;
/*    */ import net.minecraft.client.renderer.GlStateManager;
/*    */ import net.minecraft.client.renderer.OpenGlHelper;
/*    */ import net.minecraft.client.renderer.entity.RenderSpider;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntityLiving;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.entity.monster.EntitySpider;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import optifine.Config;
/*    */ import shadersmod.client.Shaders;
/*    */ 
/*    */ public class LayerSpiderEyes implements LayerRenderer {
/* 14 */   private static final ResourceLocation field_177150_a = new ResourceLocation("textures/entity/spider_eyes.png");
/*    */   
/*    */   private final RenderSpider field_177149_b;
/*    */   private static final String __OBFID = "CL_00002410";
/*    */   
/*    */   public LayerSpiderEyes(RenderSpider p_i46109_1_) {
/* 20 */     this.field_177149_b = p_i46109_1_;
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_177148_a(EntitySpider p_177148_1_, float p_177148_2_, float p_177148_3_, float p_177148_4_, float p_177148_5_, float p_177148_6_, float p_177148_7_, float p_177148_8_) {
/* 25 */     this.field_177149_b.bindTexture(field_177150_a);
/* 26 */     GlStateManager.enableBlend();
/* 27 */     GlStateManager.disableAlpha();
/* 28 */     GlStateManager.blendFunc(1, 1);
/*    */     
/* 30 */     if (p_177148_1_.isInvisible()) {
/*    */       
/* 32 */       GlStateManager.depthMask(false);
/*    */     }
/*    */     else {
/*    */       
/* 36 */       GlStateManager.depthMask(true);
/*    */     } 
/*    */     
/* 39 */     char var9 = '';
/* 40 */     int var10 = var9 % 65536;
/* 41 */     int var11 = var9 / 65536;
/* 42 */     OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, var10 / 1.0F, var11 / 1.0F);
/* 43 */     GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
/*    */     
/* 45 */     if (Config.isShaders())
/*    */     {
/* 47 */       Shaders.beginSpiderEyes();
/*    */     }
/*    */     
/* 50 */     this.field_177149_b.getMainModel().render((Entity)p_177148_1_, p_177148_2_, p_177148_3_, p_177148_5_, p_177148_6_, p_177148_7_, p_177148_8_);
/* 51 */     int var12 = p_177148_1_.getBrightnessForRender(p_177148_4_);
/* 52 */     var10 = var12 % 65536;
/* 53 */     var11 = var12 / 65536;
/* 54 */     OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, var10 / 1.0F, var11 / 1.0F);
/* 55 */     this.field_177149_b.func_177105_a((EntityLiving)p_177148_1_, p_177148_4_);
/* 56 */     GlStateManager.disableBlend();
/* 57 */     GlStateManager.enableAlpha();
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean shouldCombineTextures() {
/* 62 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public void doRenderLayer(EntityLivingBase p_177141_1_, float p_177141_2_, float p_177141_3_, float p_177141_4_, float p_177141_5_, float p_177141_6_, float p_177141_7_, float p_177141_8_) {
/* 67 */     func_177148_a((EntitySpider)p_177141_1_, p_177141_2_, p_177141_3_, p_177141_4_, p_177141_5_, p_177141_6_, p_177141_7_, p_177141_8_);
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\renderer\entity\layers\LayerSpiderEyes.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */