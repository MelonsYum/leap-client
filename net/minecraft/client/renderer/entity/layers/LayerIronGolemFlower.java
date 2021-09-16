/*    */ package net.minecraft.client.renderer.entity.layers;
/*    */ 
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.model.ModelIronGolem;
/*    */ import net.minecraft.client.renderer.BlockRendererDispatcher;
/*    */ import net.minecraft.client.renderer.GlStateManager;
/*    */ import net.minecraft.client.renderer.OpenGlHelper;
/*    */ import net.minecraft.client.renderer.entity.RenderIronGolem;
/*    */ import net.minecraft.client.renderer.texture.TextureMap;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.entity.monster.EntityIronGolem;
/*    */ import net.minecraft.init.Blocks;
/*    */ 
/*    */ public class LayerIronGolemFlower
/*    */   implements LayerRenderer
/*    */ {
/*    */   private final RenderIronGolem field_177154_a;
/*    */   private static final String __OBFID = "CL_00002408";
/*    */   
/*    */   public LayerIronGolemFlower(RenderIronGolem p_i46107_1_) {
/* 21 */     this.field_177154_a = p_i46107_1_;
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_177153_a(EntityIronGolem p_177153_1_, float p_177153_2_, float p_177153_3_, float p_177153_4_, float p_177153_5_, float p_177153_6_, float p_177153_7_, float p_177153_8_) {
/* 26 */     if (p_177153_1_.getHoldRoseTick() != 0) {
/*    */       
/* 28 */       BlockRendererDispatcher var9 = Minecraft.getMinecraft().getBlockRendererDispatcher();
/* 29 */       GlStateManager.enableRescaleNormal();
/* 30 */       GlStateManager.pushMatrix();
/* 31 */       GlStateManager.rotate(5.0F + 180.0F * ((ModelIronGolem)this.field_177154_a.getMainModel()).ironGolemRightArm.rotateAngleX / 3.1415927F, 1.0F, 0.0F, 0.0F);
/* 32 */       GlStateManager.rotate(90.0F, 1.0F, 0.0F, 0.0F);
/* 33 */       GlStateManager.translate(-0.9375F, -0.625F, -0.9375F);
/* 34 */       float var10 = 0.5F;
/* 35 */       GlStateManager.scale(var10, -var10, var10);
/* 36 */       int var11 = p_177153_1_.getBrightnessForRender(p_177153_4_);
/* 37 */       int var12 = var11 % 65536;
/* 38 */       int var13 = var11 / 65536;
/* 39 */       OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, var12 / 1.0F, var13 / 1.0F);
/* 40 */       GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
/* 41 */       this.field_177154_a.bindTexture(TextureMap.locationBlocksTexture);
/* 42 */       var9.func_175016_a(Blocks.red_flower.getDefaultState(), 1.0F);
/* 43 */       GlStateManager.popMatrix();
/* 44 */       GlStateManager.disableRescaleNormal();
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
/* 55 */     func_177153_a((EntityIronGolem)p_177141_1_, p_177141_2_, p_177141_3_, p_177141_4_, p_177141_5_, p_177141_6_, p_177141_7_, p_177141_8_);
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\renderer\entity\layers\LayerIronGolemFlower.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */