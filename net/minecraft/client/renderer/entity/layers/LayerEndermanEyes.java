/*    */ package net.minecraft.client.renderer.entity.layers;
/*    */ import net.minecraft.client.renderer.GlStateManager;
/*    */ import net.minecraft.client.renderer.OpenGlHelper;
/*    */ import net.minecraft.client.renderer.entity.RenderEnderman;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntityLiving;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.entity.monster.EntityEnderman;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import optifine.Config;
/*    */ import shadersmod.client.Shaders;
/*    */ 
/*    */ public class LayerEndermanEyes implements LayerRenderer {
/* 14 */   private static final ResourceLocation field_177203_a = new ResourceLocation("textures/entity/enderman/enderman_eyes.png");
/*    */   
/*    */   private final RenderEnderman field_177202_b;
/*    */   private static final String __OBFID = "CL_00002418";
/*    */   
/*    */   public LayerEndermanEyes(RenderEnderman p_i46117_1_) {
/* 20 */     this.field_177202_b = p_i46117_1_;
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_177201_a(EntityEnderman p_177201_1_, float p_177201_2_, float p_177201_3_, float p_177201_4_, float p_177201_5_, float p_177201_6_, float p_177201_7_, float p_177201_8_) {
/* 25 */     this.field_177202_b.bindTexture(field_177203_a);
/* 26 */     GlStateManager.enableBlend();
/* 27 */     GlStateManager.disableAlpha();
/* 28 */     GlStateManager.blendFunc(1, 1);
/* 29 */     GlStateManager.disableLighting();
/*    */     
/* 31 */     if (p_177201_1_.isInvisible()) {
/*    */       
/* 33 */       GlStateManager.depthMask(false);
/*    */     }
/*    */     else {
/*    */       
/* 37 */       GlStateManager.depthMask(true);
/*    */     } 
/*    */     
/* 40 */     char var9 = '';
/* 41 */     int var10 = var9 % 65536;
/* 42 */     int var11 = var9 / 65536;
/* 43 */     OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, var10 / 1.0F, var11 / 1.0F);
/* 44 */     GlStateManager.enableLighting();
/* 45 */     GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
/*    */     
/* 47 */     if (Config.isShaders())
/*    */     {
/* 49 */       Shaders.beginSpiderEyes();
/*    */     }
/*    */     
/* 52 */     this.field_177202_b.getMainModel().render((Entity)p_177201_1_, p_177201_2_, p_177201_3_, p_177201_5_, p_177201_6_, p_177201_7_, p_177201_8_);
/* 53 */     this.field_177202_b.func_177105_a((EntityLiving)p_177201_1_, p_177201_4_);
/* 54 */     GlStateManager.disableBlend();
/* 55 */     GlStateManager.enableAlpha();
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean shouldCombineTextures() {
/* 60 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public void doRenderLayer(EntityLivingBase p_177141_1_, float p_177141_2_, float p_177141_3_, float p_177141_4_, float p_177141_5_, float p_177141_6_, float p_177141_7_, float p_177141_8_) {
/* 65 */     func_177201_a((EntityEnderman)p_177141_1_, p_177141_2_, p_177141_3_, p_177141_4_, p_177141_5_, p_177141_6_, p_177141_7_, p_177141_8_);
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\renderer\entity\layers\LayerEndermanEyes.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */