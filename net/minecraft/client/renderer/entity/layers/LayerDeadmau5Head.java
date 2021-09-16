/*    */ package net.minecraft.client.renderer.entity.layers;
/*    */ 
/*    */ import net.minecraft.client.entity.AbstractClientPlayer;
/*    */ import net.minecraft.client.renderer.GlStateManager;
/*    */ import net.minecraft.client.renderer.entity.RenderPlayer;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ 
/*    */ public class LayerDeadmau5Head
/*    */   implements LayerRenderer
/*    */ {
/*    */   private final RenderPlayer field_177208_a;
/*    */   private static final String __OBFID = "CL_00002421";
/*    */   
/*    */   public LayerDeadmau5Head(RenderPlayer p_i46119_1_) {
/* 15 */     this.field_177208_a = p_i46119_1_;
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_177207_a(AbstractClientPlayer p_177207_1_, float p_177207_2_, float p_177207_3_, float p_177207_4_, float p_177207_5_, float p_177207_6_, float p_177207_7_, float p_177207_8_) {
/* 20 */     if (p_177207_1_.getName().equals("deadmau5") && p_177207_1_.hasSkin() && !p_177207_1_.isInvisible()) {
/*    */       
/* 22 */       this.field_177208_a.bindTexture(p_177207_1_.getLocationSkin());
/*    */       
/* 24 */       for (int var9 = 0; var9 < 2; var9++) {
/*    */         
/* 26 */         float var10 = p_177207_1_.prevRotationYaw + (p_177207_1_.rotationYaw - p_177207_1_.prevRotationYaw) * p_177207_4_ - p_177207_1_.prevRenderYawOffset + (p_177207_1_.renderYawOffset - p_177207_1_.prevRenderYawOffset) * p_177207_4_;
/* 27 */         float var11 = p_177207_1_.prevRotationPitch + (p_177207_1_.rotationPitch - p_177207_1_.prevRotationPitch) * p_177207_4_;
/* 28 */         GlStateManager.pushMatrix();
/* 29 */         GlStateManager.rotate(var10, 0.0F, 1.0F, 0.0F);
/* 30 */         GlStateManager.rotate(var11, 1.0F, 0.0F, 0.0F);
/* 31 */         GlStateManager.translate(0.375F * (var9 * 2 - 1), 0.0F, 0.0F);
/* 32 */         GlStateManager.translate(0.0F, -0.375F, 0.0F);
/* 33 */         GlStateManager.rotate(-var11, 1.0F, 0.0F, 0.0F);
/* 34 */         GlStateManager.rotate(-var10, 0.0F, 1.0F, 0.0F);
/* 35 */         float var12 = 1.3333334F;
/* 36 */         GlStateManager.scale(var12, var12, var12);
/* 37 */         this.field_177208_a.func_177136_g().func_178727_b(0.0625F);
/* 38 */         GlStateManager.popMatrix();
/*    */       } 
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean shouldCombineTextures() {
/* 45 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public void doRenderLayer(EntityLivingBase p_177141_1_, float p_177141_2_, float p_177141_3_, float p_177141_4_, float p_177141_5_, float p_177141_6_, float p_177141_7_, float p_177141_8_) {
/* 50 */     func_177207_a((AbstractClientPlayer)p_177141_1_, p_177141_2_, p_177141_3_, p_177141_4_, p_177141_5_, p_177141_6_, p_177141_7_, p_177141_8_);
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\renderer\entity\layers\LayerDeadmau5Head.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */