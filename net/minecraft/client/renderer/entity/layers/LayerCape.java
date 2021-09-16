/*    */ package net.minecraft.client.renderer.entity.layers;
/*    */ 
/*    */ import net.minecraft.client.entity.AbstractClientPlayer;
/*    */ import net.minecraft.client.renderer.GlStateManager;
/*    */ import net.minecraft.client.renderer.entity.RenderPlayer;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.entity.player.EnumPlayerModelParts;
/*    */ import net.minecraft.util.MathHelper;
/*    */ 
/*    */ public class LayerCape
/*    */   implements LayerRenderer
/*    */ {
/*    */   private final RenderPlayer playerRenderer;
/*    */   private static final String __OBFID = "CL_00002425";
/*    */   
/*    */   public LayerCape(RenderPlayer p_i46123_1_) {
/* 17 */     this.playerRenderer = p_i46123_1_;
/*    */   }
/*    */ 
/*    */   
/*    */   public void doRenderLayer(AbstractClientPlayer p_177166_1_, float p_177166_2_, float p_177166_3_, float p_177166_4_, float p_177166_5_, float p_177166_6_, float p_177166_7_, float p_177166_8_) {
/* 22 */     if (p_177166_1_.hasCape() && !p_177166_1_.isInvisible() && p_177166_1_.func_175148_a(EnumPlayerModelParts.CAPE) && p_177166_1_.getLocationCape() != null) {
/*    */       
/* 24 */       GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
/* 25 */       this.playerRenderer.bindTexture(p_177166_1_.getLocationCape());
/* 26 */       GlStateManager.pushMatrix();
/* 27 */       GlStateManager.translate(0.0F, 0.0F, 0.125F);
/* 28 */       double var9 = p_177166_1_.field_71091_bM + (p_177166_1_.field_71094_bP - p_177166_1_.field_71091_bM) * p_177166_4_ - p_177166_1_.prevPosX + (p_177166_1_.posX - p_177166_1_.prevPosX) * p_177166_4_;
/* 29 */       double var11 = p_177166_1_.field_71096_bN + (p_177166_1_.field_71095_bQ - p_177166_1_.field_71096_bN) * p_177166_4_ - p_177166_1_.prevPosY + (p_177166_1_.posY - p_177166_1_.prevPosY) * p_177166_4_;
/* 30 */       double var13 = p_177166_1_.field_71097_bO + (p_177166_1_.field_71085_bR - p_177166_1_.field_71097_bO) * p_177166_4_ - p_177166_1_.prevPosZ + (p_177166_1_.posZ - p_177166_1_.prevPosZ) * p_177166_4_;
/* 31 */       float var15 = p_177166_1_.prevRenderYawOffset + (p_177166_1_.renderYawOffset - p_177166_1_.prevRenderYawOffset) * p_177166_4_;
/* 32 */       double var16 = MathHelper.sin(var15 * 3.1415927F / 180.0F);
/* 33 */       double var18 = -MathHelper.cos(var15 * 3.1415927F / 180.0F);
/* 34 */       float var20 = (float)var11 * 10.0F;
/* 35 */       var20 = MathHelper.clamp_float(var20, -6.0F, 32.0F);
/* 36 */       float var21 = (float)(var9 * var16 + var13 * var18) * 100.0F;
/* 37 */       float var22 = (float)(var9 * var18 - var13 * var16) * 100.0F;
/*    */       
/* 39 */       if (var21 < 0.0F)
/*    */       {
/* 41 */         var21 = 0.0F;
/*    */       }
/*    */       
/* 44 */       if (var21 > 165.0F)
/*    */       {
/* 46 */         var21 = 165.0F;
/*    */       }
/*    */       
/* 49 */       float var23 = p_177166_1_.prevCameraYaw + (p_177166_1_.cameraYaw - p_177166_1_.prevCameraYaw) * p_177166_4_;
/* 50 */       var20 += MathHelper.sin((p_177166_1_.prevDistanceWalkedModified + (p_177166_1_.distanceWalkedModified - p_177166_1_.prevDistanceWalkedModified) * p_177166_4_) * 6.0F) * 32.0F * var23;
/*    */       
/* 52 */       if (p_177166_1_.isSneaking()) {
/*    */         
/* 54 */         var20 += 25.0F;
/* 55 */         GlStateManager.translate(0.0F, 0.142F, -0.0178F);
/*    */       } 
/*    */       
/* 58 */       GlStateManager.rotate(6.0F + var21 / 2.0F + var20, 1.0F, 0.0F, 0.0F);
/* 59 */       GlStateManager.rotate(var22 / 2.0F, 0.0F, 0.0F, 1.0F);
/* 60 */       GlStateManager.rotate(-var22 / 2.0F, 0.0F, 1.0F, 0.0F);
/* 61 */       GlStateManager.rotate(180.0F, 0.0F, 1.0F, 0.0F);
/* 62 */       this.playerRenderer.func_177136_g().func_178728_c(0.0625F);
/* 63 */       GlStateManager.popMatrix();
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean shouldCombineTextures() {
/* 69 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public void doRenderLayer(EntityLivingBase p_177141_1_, float p_177141_2_, float p_177141_3_, float p_177141_4_, float p_177141_5_, float p_177141_6_, float p_177141_7_, float p_177141_8_) {
/* 74 */     doRenderLayer((AbstractClientPlayer)p_177141_1_, p_177141_2_, p_177141_3_, p_177141_4_, p_177141_5_, p_177141_6_, p_177141_7_, p_177141_8_);
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\renderer\entity\layers\LayerCape.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */