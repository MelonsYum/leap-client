/*    */ package net.minecraft.client.renderer.entity.layers;
/*    */ 
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.model.ModelQuadruped;
/*    */ import net.minecraft.client.renderer.BlockRendererDispatcher;
/*    */ import net.minecraft.client.renderer.GlStateManager;
/*    */ import net.minecraft.client.renderer.entity.RenderMooshroom;
/*    */ import net.minecraft.client.renderer.texture.TextureMap;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.entity.passive.EntityMooshroom;
/*    */ import net.minecraft.init.Blocks;
/*    */ 
/*    */ public class LayerMooshroomMushroom
/*    */   implements LayerRenderer
/*    */ {
/*    */   private final RenderMooshroom field_177205_a;
/*    */   private static final String __OBFID = "CL_00002415";
/*    */   
/*    */   public LayerMooshroomMushroom(RenderMooshroom p_i46114_1_) {
/* 20 */     this.field_177205_a = p_i46114_1_;
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_177204_a(EntityMooshroom p_177204_1_, float p_177204_2_, float p_177204_3_, float p_177204_4_, float p_177204_5_, float p_177204_6_, float p_177204_7_, float p_177204_8_) {
/* 25 */     if (!p_177204_1_.isChild() && !p_177204_1_.isInvisible()) {
/*    */       
/* 27 */       BlockRendererDispatcher var9 = Minecraft.getMinecraft().getBlockRendererDispatcher();
/* 28 */       this.field_177205_a.bindTexture(TextureMap.locationBlocksTexture);
/* 29 */       GlStateManager.enableCull();
/* 30 */       GlStateManager.pushMatrix();
/* 31 */       GlStateManager.scale(1.0F, -1.0F, 1.0F);
/* 32 */       GlStateManager.translate(0.2F, 0.35F, 0.5F);
/* 33 */       GlStateManager.rotate(42.0F, 0.0F, 1.0F, 0.0F);
/* 34 */       GlStateManager.pushMatrix();
/* 35 */       GlStateManager.translate(-0.5F, -0.5F, 0.5F);
/* 36 */       var9.func_175016_a(Blocks.red_mushroom.getDefaultState(), 1.0F);
/* 37 */       GlStateManager.popMatrix();
/* 38 */       GlStateManager.pushMatrix();
/* 39 */       GlStateManager.translate(0.1F, 0.0F, -0.6F);
/* 40 */       GlStateManager.rotate(42.0F, 0.0F, 1.0F, 0.0F);
/* 41 */       GlStateManager.translate(-0.5F, -0.5F, 0.5F);
/* 42 */       var9.func_175016_a(Blocks.red_mushroom.getDefaultState(), 1.0F);
/* 43 */       GlStateManager.popMatrix();
/* 44 */       GlStateManager.popMatrix();
/* 45 */       GlStateManager.pushMatrix();
/* 46 */       ((ModelQuadruped)this.field_177205_a.getMainModel()).head.postRender(0.0625F);
/* 47 */       GlStateManager.scale(1.0F, -1.0F, 1.0F);
/* 48 */       GlStateManager.translate(0.0F, 0.7F, -0.2F);
/* 49 */       GlStateManager.rotate(12.0F, 0.0F, 1.0F, 0.0F);
/* 50 */       GlStateManager.translate(-0.5F, -0.5F, 0.5F);
/* 51 */       var9.func_175016_a(Blocks.red_mushroom.getDefaultState(), 1.0F);
/* 52 */       GlStateManager.popMatrix();
/* 53 */       GlStateManager.disableCull();
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean shouldCombineTextures() {
/* 59 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public void doRenderLayer(EntityLivingBase p_177141_1_, float p_177141_2_, float p_177141_3_, float p_177141_4_, float p_177141_5_, float p_177141_6_, float p_177141_7_, float p_177141_8_) {
/* 64 */     func_177204_a((EntityMooshroom)p_177141_1_, p_177141_2_, p_177141_3_, p_177141_4_, p_177141_5_, p_177141_6_, p_177141_7_, p_177141_8_);
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\renderer\entity\layers\LayerMooshroomMushroom.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */