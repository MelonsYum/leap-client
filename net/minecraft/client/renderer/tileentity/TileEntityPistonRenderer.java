/*    */ package net.minecraft.client.renderer.tileentity;
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.block.BlockPistonBase;
/*    */ import net.minecraft.block.BlockPistonExtension;
/*    */ import net.minecraft.block.material.Material;
/*    */ import net.minecraft.block.properties.IProperty;
/*    */ import net.minecraft.block.state.IBlockState;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.renderer.BlockRendererDispatcher;
/*    */ import net.minecraft.client.renderer.GlStateManager;
/*    */ import net.minecraft.client.renderer.RenderHelper;
/*    */ import net.minecraft.client.renderer.Tessellator;
/*    */ import net.minecraft.client.renderer.WorldRenderer;
/*    */ import net.minecraft.client.renderer.texture.TextureMap;
/*    */ import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
/*    */ import net.minecraft.init.Blocks;
/*    */ import net.minecraft.tileentity.TileEntity;
/*    */ import net.minecraft.tileentity.TileEntityPiston;
/*    */ import net.minecraft.util.BlockPos;
/*    */ import net.minecraft.world.IBlockAccess;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ public class TileEntityPistonRenderer extends TileEntitySpecialRenderer {
/* 24 */   private final BlockRendererDispatcher field_178462_c = Minecraft.getMinecraft().getBlockRendererDispatcher();
/*    */   
/*    */   private static final String __OBFID = "CL_00002469";
/*    */   
/*    */   public void func_178461_a(TileEntityPiston p_178461_1_, double p_178461_2_, double p_178461_4_, double p_178461_6_, float p_178461_8_, int p_178461_9_) {
/* 29 */     BlockPos var10 = p_178461_1_.getPos();
/* 30 */     IBlockState var11 = p_178461_1_.func_174927_b();
/* 31 */     Block var12 = var11.getBlock();
/*    */     
/* 33 */     if (var12.getMaterial() != Material.air && p_178461_1_.func_145860_a(p_178461_8_) < 1.0F) {
/*    */       
/* 35 */       Tessellator var13 = Tessellator.getInstance();
/* 36 */       WorldRenderer var14 = var13.getWorldRenderer();
/* 37 */       bindTexture(TextureMap.locationBlocksTexture);
/* 38 */       RenderHelper.disableStandardItemLighting();
/* 39 */       GlStateManager.blendFunc(770, 771);
/* 40 */       GlStateManager.enableBlend();
/* 41 */       GlStateManager.disableCull();
/*    */       
/* 43 */       if (Minecraft.isAmbientOcclusionEnabled()) {
/*    */         
/* 45 */         GlStateManager.shadeModel(7425);
/*    */       }
/*    */       else {
/*    */         
/* 49 */         GlStateManager.shadeModel(7424);
/*    */       } 
/*    */       
/* 52 */       var14.startDrawingQuads();
/* 53 */       var14.setVertexFormat(DefaultVertexFormats.field_176600_a);
/* 54 */       var14.setTranslation(((float)p_178461_2_ - var10.getX() + p_178461_1_.func_174929_b(p_178461_8_)), ((float)p_178461_4_ - var10.getY() + p_178461_1_.func_174928_c(p_178461_8_)), ((float)p_178461_6_ - var10.getZ() + p_178461_1_.func_174926_d(p_178461_8_)));
/* 55 */       var14.func_178986_b(1.0F, 1.0F, 1.0F);
/* 56 */       World var15 = getWorld();
/*    */       
/* 58 */       if (var12 == Blocks.piston_head && p_178461_1_.func_145860_a(p_178461_8_) < 0.5F) {
/*    */         
/* 60 */         var11 = var11.withProperty((IProperty)BlockPistonExtension.field_176327_M, Boolean.valueOf(true));
/* 61 */         this.field_178462_c.func_175019_b().renderBlockModel((IBlockAccess)var15, this.field_178462_c.getModelFromBlockState(var11, (IBlockAccess)var15, var10), var11, var10, var14, true);
/*    */       }
/* 63 */       else if (p_178461_1_.shouldPistonHeadBeRendered() && !p_178461_1_.isExtending()) {
/*    */         
/* 65 */         BlockPistonExtension.EnumPistonType var16 = (var12 == Blocks.sticky_piston) ? BlockPistonExtension.EnumPistonType.STICKY : BlockPistonExtension.EnumPistonType.DEFAULT;
/* 66 */         IBlockState var17 = Blocks.piston_head.getDefaultState().withProperty((IProperty)BlockPistonExtension.field_176325_b, (Comparable)var16).withProperty((IProperty)BlockPistonExtension.field_176326_a, var11.getValue((IProperty)BlockPistonBase.FACING));
/* 67 */         var17 = var17.withProperty((IProperty)BlockPistonExtension.field_176327_M, Boolean.valueOf((p_178461_1_.func_145860_a(p_178461_8_) >= 0.5F)));
/* 68 */         this.field_178462_c.func_175019_b().renderBlockModel((IBlockAccess)var15, this.field_178462_c.getModelFromBlockState(var17, (IBlockAccess)var15, var10), var17, var10, var14, true);
/* 69 */         var14.setTranslation(((float)p_178461_2_ - var10.getX()), ((float)p_178461_4_ - var10.getY()), ((float)p_178461_6_ - var10.getZ()));
/* 70 */         var11.withProperty((IProperty)BlockPistonBase.EXTENDED, Boolean.valueOf(true));
/* 71 */         this.field_178462_c.func_175019_b().renderBlockModel((IBlockAccess)var15, this.field_178462_c.getModelFromBlockState(var11, (IBlockAccess)var15, var10), var11, var10, var14, true);
/*    */       }
/*    */       else {
/*    */         
/* 75 */         this.field_178462_c.func_175019_b().renderBlockModel((IBlockAccess)var15, this.field_178462_c.getModelFromBlockState(var11, (IBlockAccess)var15, var10), var11, var10, var14, false);
/*    */       } 
/*    */       
/* 78 */       var14.setTranslation(0.0D, 0.0D, 0.0D);
/* 79 */       var13.draw();
/* 80 */       RenderHelper.enableStandardItemLighting();
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void renderTileEntityAt(TileEntity p_180535_1_, double p_180535_2_, double p_180535_4_, double p_180535_6_, float p_180535_8_, int p_180535_9_) {
/* 86 */     func_178461_a((TileEntityPiston)p_180535_1_, p_180535_2_, p_180535_4_, p_180535_6_, p_180535_8_, p_180535_9_);
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\renderer\tileentity\TileEntityPistonRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */