/*    */ package net.minecraft.client.renderer.entity.layers;
/*    */ 
/*    */ import net.minecraft.client.model.ModelCreeper;
/*    */ import net.minecraft.client.renderer.GlStateManager;
/*    */ import net.minecraft.client.renderer.entity.RenderCreeper;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.entity.monster.EntityCreeper;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ 
/*    */ public class LayerCreeperCharge implements LayerRenderer {
/* 12 */   private static final ResourceLocation LIGHTNING_TEXTURE = new ResourceLocation("textures/entity/creeper/creeper_armor.png");
/*    */   private final RenderCreeper creeperRenderer;
/* 14 */   private final ModelCreeper creeperModel = new ModelCreeper(2.0F);
/*    */   
/*    */   private static final String __OBFID = "CL_00002423";
/*    */   
/*    */   public LayerCreeperCharge(RenderCreeper p_i46121_1_) {
/* 19 */     this.creeperRenderer = p_i46121_1_;
/*    */   }
/*    */ 
/*    */   
/*    */   public void doRenderLayer(EntityCreeper p_177169_1_, float p_177169_2_, float p_177169_3_, float p_177169_4_, float p_177169_5_, float p_177169_6_, float p_177169_7_, float p_177169_8_) {
/* 24 */     if (p_177169_1_.getPowered()) {
/*    */       
/* 26 */       GlStateManager.depthMask(!p_177169_1_.isInvisible());
/* 27 */       this.creeperRenderer.bindTexture(LIGHTNING_TEXTURE);
/* 28 */       GlStateManager.matrixMode(5890);
/* 29 */       GlStateManager.loadIdentity();
/* 30 */       float var9 = p_177169_1_.ticksExisted + p_177169_4_;
/* 31 */       GlStateManager.translate(var9 * 0.01F, var9 * 0.01F, 0.0F);
/* 32 */       GlStateManager.matrixMode(5888);
/* 33 */       GlStateManager.enableBlend();
/* 34 */       float var10 = 0.5F;
/* 35 */       GlStateManager.color(var10, var10, var10, 1.0F);
/* 36 */       GlStateManager.disableLighting();
/* 37 */       GlStateManager.blendFunc(1, 1);
/* 38 */       this.creeperModel.setModelAttributes(this.creeperRenderer.getMainModel());
/* 39 */       this.creeperModel.render((Entity)p_177169_1_, p_177169_2_, p_177169_3_, p_177169_5_, p_177169_6_, p_177169_7_, p_177169_8_);
/* 40 */       GlStateManager.matrixMode(5890);
/* 41 */       GlStateManager.loadIdentity();
/* 42 */       GlStateManager.matrixMode(5888);
/* 43 */       GlStateManager.enableLighting();
/* 44 */       GlStateManager.disableBlend();
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean shouldCombineTextures() {
/* 50 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public void doRenderLayer(EntityLivingBase p_177141_1_, float p_177141_2_, float p_177141_3_, float p_177141_4_, float p_177141_5_, float p_177141_6_, float p_177141_7_, float p_177141_8_) {
/* 55 */     doRenderLayer((EntityCreeper)p_177141_1_, p_177141_2_, p_177141_3_, p_177141_4_, p_177141_5_, p_177141_6_, p_177141_7_, p_177141_8_);
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\renderer\entity\layers\LayerCreeperCharge.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */