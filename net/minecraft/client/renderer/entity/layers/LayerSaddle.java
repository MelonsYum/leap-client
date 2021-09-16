/*    */ package net.minecraft.client.renderer.entity.layers;
/*    */ 
/*    */ import net.minecraft.client.model.ModelPig;
/*    */ import net.minecraft.client.renderer.entity.RenderPig;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.entity.passive.EntityPig;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ 
/*    */ public class LayerSaddle implements LayerRenderer {
/* 11 */   private static final ResourceLocation TEXTURE = new ResourceLocation("textures/entity/pig/pig_saddle.png");
/*    */   private final RenderPig pigRenderer;
/* 13 */   private final ModelPig pigModel = new ModelPig(0.5F);
/*    */   
/*    */   private static final String __OBFID = "CL_00002414";
/*    */   
/*    */   public LayerSaddle(RenderPig p_i46113_1_) {
/* 18 */     this.pigRenderer = p_i46113_1_;
/*    */   }
/*    */ 
/*    */   
/*    */   public void doRenderLayer(EntityPig p_177155_1_, float p_177155_2_, float p_177155_3_, float p_177155_4_, float p_177155_5_, float p_177155_6_, float p_177155_7_, float p_177155_8_) {
/* 23 */     if (p_177155_1_.getSaddled()) {
/*    */       
/* 25 */       this.pigRenderer.bindTexture(TEXTURE);
/* 26 */       this.pigModel.setModelAttributes(this.pigRenderer.getMainModel());
/* 27 */       this.pigModel.render((Entity)p_177155_1_, p_177155_2_, p_177155_3_, p_177155_5_, p_177155_6_, p_177155_7_, p_177155_8_);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean shouldCombineTextures() {
/* 33 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public void doRenderLayer(EntityLivingBase p_177141_1_, float p_177141_2_, float p_177141_3_, float p_177141_4_, float p_177141_5_, float p_177141_6_, float p_177141_7_, float p_177141_8_) {
/* 38 */     doRenderLayer((EntityPig)p_177141_1_, p_177141_2_, p_177141_3_, p_177141_4_, p_177141_5_, p_177141_6_, p_177141_7_, p_177141_8_);
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\renderer\entity\layers\LayerSaddle.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */