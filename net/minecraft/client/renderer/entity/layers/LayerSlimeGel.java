/*    */ package net.minecraft.client.renderer.entity.layers;
/*    */ 
/*    */ import net.minecraft.client.model.ModelBase;
/*    */ import net.minecraft.client.model.ModelSlime;
/*    */ import net.minecraft.client.renderer.GlStateManager;
/*    */ import net.minecraft.client.renderer.entity.RenderSlime;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.entity.monster.EntitySlime;
/*    */ 
/*    */ public class LayerSlimeGel implements LayerRenderer {
/*    */   private final RenderSlime slimeRenderer;
/* 13 */   private final ModelBase slimeModel = (ModelBase)new ModelSlime(0);
/*    */   
/*    */   private static final String __OBFID = "CL_00002412";
/*    */   
/*    */   public LayerSlimeGel(RenderSlime p_i46111_1_) {
/* 18 */     this.slimeRenderer = p_i46111_1_;
/*    */   }
/*    */ 
/*    */   
/*    */   public void doRenderLayer(EntitySlime p_177159_1_, float p_177159_2_, float p_177159_3_, float p_177159_4_, float p_177159_5_, float p_177159_6_, float p_177159_7_, float p_177159_8_) {
/* 23 */     if (!p_177159_1_.isInvisible()) {
/*    */       
/* 25 */       GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
/* 26 */       GlStateManager.enableNormalize();
/* 27 */       GlStateManager.enableBlend();
/* 28 */       GlStateManager.blendFunc(770, 771);
/* 29 */       this.slimeModel.setModelAttributes(this.slimeRenderer.getMainModel());
/* 30 */       this.slimeModel.render((Entity)p_177159_1_, p_177159_2_, p_177159_3_, p_177159_5_, p_177159_6_, p_177159_7_, p_177159_8_);
/* 31 */       GlStateManager.disableBlend();
/* 32 */       GlStateManager.disableNormalize();
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean shouldCombineTextures() {
/* 38 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public void doRenderLayer(EntityLivingBase p_177141_1_, float p_177141_2_, float p_177141_3_, float p_177141_4_, float p_177141_5_, float p_177141_6_, float p_177141_7_, float p_177141_8_) {
/* 43 */     doRenderLayer((EntitySlime)p_177141_1_, p_177141_2_, p_177141_3_, p_177141_4_, p_177141_5_, p_177141_6_, p_177141_7_, p_177141_8_);
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\renderer\entity\layers\LayerSlimeGel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */