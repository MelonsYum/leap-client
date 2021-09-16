/*    */ package net.minecraft.client.renderer.entity;
/*    */ 
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.block.state.IBlockState;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.renderer.BlockRendererDispatcher;
/*    */ import net.minecraft.client.renderer.GlStateManager;
/*    */ import net.minecraft.client.renderer.Tessellator;
/*    */ import net.minecraft.client.renderer.WorldRenderer;
/*    */ import net.minecraft.client.renderer.texture.TextureMap;
/*    */ import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
/*    */ import net.minecraft.client.resources.model.IBakedModel;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.item.EntityFallingBlock;
/*    */ import net.minecraft.util.BlockPos;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraft.world.IBlockAccess;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ public class RenderFallingBlock
/*    */   extends Render {
/*    */   private static final String __OBFID = "CL_00000994";
/*    */   
/*    */   public RenderFallingBlock(RenderManager p_i46177_1_) {
/* 25 */     super(p_i46177_1_);
/* 26 */     this.shadowSize = 0.5F;
/*    */   }
/*    */ 
/*    */   
/*    */   public void doRender(EntityFallingBlock p_180557_1_, double p_180557_2_, double p_180557_4_, double p_180557_6_, float p_180557_8_, float p_180557_9_) {
/* 31 */     if (p_180557_1_.getBlock() != null) {
/*    */       
/* 33 */       bindTexture(TextureMap.locationBlocksTexture);
/* 34 */       IBlockState var10 = p_180557_1_.getBlock();
/* 35 */       Block var11 = var10.getBlock();
/* 36 */       BlockPos var12 = new BlockPos((Entity)p_180557_1_);
/* 37 */       World var13 = p_180557_1_.getWorldObj();
/*    */       
/* 39 */       if (var10 != var13.getBlockState(var12) && var11.getRenderType() != -1)
/*    */       {
/* 41 */         if (var11.getRenderType() == 3) {
/*    */           
/* 43 */           GlStateManager.pushMatrix();
/* 44 */           GlStateManager.translate((float)p_180557_2_, (float)p_180557_4_, (float)p_180557_6_);
/* 45 */           GlStateManager.disableLighting();
/* 46 */           Tessellator var14 = Tessellator.getInstance();
/* 47 */           WorldRenderer var15 = var14.getWorldRenderer();
/* 48 */           var15.startDrawingQuads();
/* 49 */           var15.setVertexFormat(DefaultVertexFormats.field_176600_a);
/* 50 */           int var16 = var12.getX();
/* 51 */           int var17 = var12.getY();
/* 52 */           int var18 = var12.getZ();
/* 53 */           var15.setTranslation((-var16 - 0.5F), -var17, (-var18 - 0.5F));
/* 54 */           BlockRendererDispatcher var19 = Minecraft.getMinecraft().getBlockRendererDispatcher();
/* 55 */           IBakedModel var20 = var19.getModelFromBlockState(var10, (IBlockAccess)var13, null);
/* 56 */           var19.func_175019_b().renderBlockModel((IBlockAccess)var13, var20, var10, var12, var15, false);
/* 57 */           var15.setTranslation(0.0D, 0.0D, 0.0D);
/* 58 */           var14.draw();
/* 59 */           GlStateManager.enableLighting();
/* 60 */           GlStateManager.popMatrix();
/* 61 */           super.doRender((Entity)p_180557_1_, p_180557_2_, p_180557_4_, p_180557_6_, p_180557_8_, p_180557_9_);
/*    */         } 
/*    */       }
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected ResourceLocation getEntityTexture(EntityFallingBlock p_110775_1_) {
/* 72 */     return TextureMap.locationBlocksTexture;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected ResourceLocation getEntityTexture(Entity p_110775_1_) {
/* 80 */     return getEntityTexture((EntityFallingBlock)p_110775_1_);
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
/* 91 */     doRender((EntityFallingBlock)p_76986_1_, p_76986_2_, p_76986_4_, p_76986_6_, p_76986_8_, p_76986_9_);
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\renderer\entity\RenderFallingBlock.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */