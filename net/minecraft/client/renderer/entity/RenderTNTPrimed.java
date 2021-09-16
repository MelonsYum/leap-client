/*    */ package net.minecraft.client.renderer.entity;
/*    */ 
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.renderer.BlockRendererDispatcher;
/*    */ import net.minecraft.client.renderer.GlStateManager;
/*    */ import net.minecraft.client.renderer.texture.TextureMap;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.item.EntityTNTPrimed;
/*    */ import net.minecraft.init.Blocks;
/*    */ import net.minecraft.util.MathHelper;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ 
/*    */ public class RenderTNTPrimed
/*    */   extends Render
/*    */ {
/*    */   private static final String __OBFID = "CL_00001030";
/*    */   
/*    */   public RenderTNTPrimed(RenderManager p_i46134_1_) {
/* 19 */     super(p_i46134_1_);
/* 20 */     this.shadowSize = 0.5F;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void doRender(EntityTNTPrimed p_76986_1_, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_) {
/* 31 */     BlockRendererDispatcher var10 = Minecraft.getMinecraft().getBlockRendererDispatcher();
/* 32 */     GlStateManager.pushMatrix();
/* 33 */     GlStateManager.translate((float)p_76986_2_, (float)p_76986_4_ + 0.5F, (float)p_76986_6_);
/*    */ 
/*    */     
/* 36 */     if (p_76986_1_.fuse - p_76986_9_ + 1.0F < 10.0F) {
/*    */       
/* 38 */       float f1 = 1.0F - (p_76986_1_.fuse - p_76986_9_ + 1.0F) / 10.0F;
/* 39 */       f1 = MathHelper.clamp_float(f1, 0.0F, 1.0F);
/* 40 */       f1 *= f1;
/* 41 */       f1 *= f1;
/* 42 */       float var12 = 1.0F + f1 * 0.3F;
/* 43 */       GlStateManager.scale(var12, var12, var12);
/*    */     } 
/*    */     
/* 46 */     float var11 = (1.0F - (p_76986_1_.fuse - p_76986_9_ + 1.0F) / 100.0F) * 0.8F;
/* 47 */     bindEntityTexture((Entity)p_76986_1_);
/* 48 */     GlStateManager.translate(-0.5F, -0.5F, 0.5F);
/* 49 */     var10.func_175016_a(Blocks.tnt.getDefaultState(), p_76986_1_.getBrightness(p_76986_9_));
/* 50 */     GlStateManager.translate(0.0F, 0.0F, 1.0F);
/*    */     
/* 52 */     if (p_76986_1_.fuse / 5 % 2 == 0) {
/*    */       
/* 54 */       GlStateManager.func_179090_x();
/* 55 */       GlStateManager.disableLighting();
/* 56 */       GlStateManager.enableBlend();
/* 57 */       GlStateManager.blendFunc(770, 772);
/* 58 */       GlStateManager.color(1.0F, 1.0F, 1.0F, var11);
/* 59 */       GlStateManager.doPolygonOffset(-3.0F, -3.0F);
/* 60 */       GlStateManager.enablePolygonOffset();
/* 61 */       var10.func_175016_a(Blocks.tnt.getDefaultState(), 1.0F);
/* 62 */       GlStateManager.doPolygonOffset(0.0F, 0.0F);
/* 63 */       GlStateManager.disablePolygonOffset();
/* 64 */       GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
/* 65 */       GlStateManager.disableBlend();
/* 66 */       GlStateManager.enableLighting();
/* 67 */       GlStateManager.func_179098_w();
/*    */     } 
/*    */     
/* 70 */     GlStateManager.popMatrix();
/* 71 */     super.doRender((Entity)p_76986_1_, p_76986_2_, p_76986_4_, p_76986_6_, p_76986_8_, p_76986_9_);
/*    */   }
/*    */ 
/*    */   
/*    */   protected ResourceLocation func_180563_a(EntityTNTPrimed p_180563_1_) {
/* 76 */     return TextureMap.locationBlocksTexture;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected ResourceLocation getEntityTexture(Entity p_110775_1_) {
/* 84 */     return func_180563_a((EntityTNTPrimed)p_110775_1_);
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
/* 95 */     doRender((EntityTNTPrimed)p_76986_1_, p_76986_2_, p_76986_4_, p_76986_6_, p_76986_8_, p_76986_9_);
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\renderer\entity\RenderTNTPrimed.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */