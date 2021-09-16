/*    */ package net.minecraft.client.renderer.entity;
/*    */ 
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.renderer.GlStateManager;
/*    */ import net.minecraft.client.renderer.Tessellator;
/*    */ import net.minecraft.client.renderer.WorldRenderer;
/*    */ import net.minecraft.client.renderer.texture.TextureAtlasSprite;
/*    */ import net.minecraft.client.renderer.texture.TextureMap;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.projectile.EntityFireball;
/*    */ import net.minecraft.init.Items;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ 
/*    */ public class RenderFireball
/*    */   extends Render
/*    */ {
/*    */   private float scale;
/*    */   private static final String __OBFID = "CL_00000995";
/*    */   
/*    */   public RenderFireball(RenderManager p_i46176_1_, float p_i46176_2_) {
/* 21 */     super(p_i46176_1_);
/* 22 */     this.scale = p_i46176_2_;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void doRender(EntityFireball p_76986_1_, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_) {
/* 33 */     GlStateManager.pushMatrix();
/* 34 */     bindEntityTexture((Entity)p_76986_1_);
/* 35 */     GlStateManager.translate((float)p_76986_2_, (float)p_76986_4_, (float)p_76986_6_);
/* 36 */     GlStateManager.enableRescaleNormal();
/* 37 */     float var10 = this.scale;
/* 38 */     GlStateManager.scale(var10 / 1.0F, var10 / 1.0F, var10 / 1.0F);
/* 39 */     TextureAtlasSprite var11 = Minecraft.getMinecraft().getRenderItem().getItemModelMesher().getParticleIcon(Items.fire_charge);
/* 40 */     Tessellator var12 = Tessellator.getInstance();
/* 41 */     WorldRenderer var13 = var12.getWorldRenderer();
/* 42 */     float var14 = var11.getMinU();
/* 43 */     float var15 = var11.getMaxU();
/* 44 */     float var16 = var11.getMinV();
/* 45 */     float var17 = var11.getMaxV();
/* 46 */     float var18 = 1.0F;
/* 47 */     float var19 = 0.5F;
/* 48 */     float var20 = 0.25F;
/* 49 */     GlStateManager.rotate(180.0F - this.renderManager.playerViewY, 0.0F, 1.0F, 0.0F);
/* 50 */     GlStateManager.rotate(-this.renderManager.playerViewX, 1.0F, 0.0F, 0.0F);
/* 51 */     var13.startDrawingQuads();
/* 52 */     var13.func_178980_d(0.0F, 1.0F, 0.0F);
/* 53 */     var13.addVertexWithUV((0.0F - var19), (0.0F - var20), 0.0D, var14, var17);
/* 54 */     var13.addVertexWithUV((var18 - var19), (0.0F - var20), 0.0D, var15, var17);
/* 55 */     var13.addVertexWithUV((var18 - var19), (1.0F - var20), 0.0D, var15, var16);
/* 56 */     var13.addVertexWithUV((0.0F - var19), (1.0F - var20), 0.0D, var14, var16);
/* 57 */     var12.draw();
/* 58 */     GlStateManager.disableRescaleNormal();
/* 59 */     GlStateManager.popMatrix();
/* 60 */     super.doRender((Entity)p_76986_1_, p_76986_2_, p_76986_4_, p_76986_6_, p_76986_8_, p_76986_9_);
/*    */   }
/*    */ 
/*    */   
/*    */   protected ResourceLocation func_180556_a(EntityFireball p_180556_1_) {
/* 65 */     return TextureMap.locationBlocksTexture;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected ResourceLocation getEntityTexture(Entity p_110775_1_) {
/* 73 */     return func_180556_a((EntityFireball)p_110775_1_);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void doRender(Entity p_76986_1_, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_) {
/* 84 */     doRender((EntityFireball)p_76986_1_, p_76986_2_, p_76986_4_, p_76986_6_, p_76986_8_, p_76986_9_);
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\renderer\entity\RenderFireball.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */