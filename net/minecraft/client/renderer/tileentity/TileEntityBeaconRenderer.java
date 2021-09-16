/*     */ package net.minecraft.client.renderer.tileentity;
/*     */ 
/*     */ import java.util.List;
/*     */ import net.minecraft.client.renderer.GlStateManager;
/*     */ import net.minecraft.client.renderer.Tessellator;
/*     */ import net.minecraft.client.renderer.WorldRenderer;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.tileentity.TileEntityBeacon;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ public class TileEntityBeaconRenderer
/*     */   extends TileEntitySpecialRenderer {
/*  15 */   private static final ResourceLocation beaconBeam = new ResourceLocation("textures/entity/beacon_beam.png");
/*     */   
/*     */   private static final String __OBFID = "CL_00000962";
/*     */   
/*     */   public void func_180536_a(TileEntityBeacon p_180536_1_, double p_180536_2_, double p_180536_4_, double p_180536_6_, float p_180536_8_, int p_180536_9_) {
/*  20 */     float var10 = p_180536_1_.shouldBeamRender();
/*  21 */     GlStateManager.alphaFunc(516, 0.1F);
/*     */     
/*  23 */     if (var10 > 0.0F) {
/*     */       
/*  25 */       Tessellator var11 = Tessellator.getInstance();
/*  26 */       WorldRenderer var12 = var11.getWorldRenderer();
/*  27 */       List<TileEntityBeacon.BeamSegment> var13 = p_180536_1_.func_174907_n();
/*  28 */       int var14 = 0;
/*     */       
/*  30 */       for (int var15 = 0; var15 < var13.size(); var15++) {
/*     */         
/*  32 */         TileEntityBeacon.BeamSegment var16 = var13.get(var15);
/*  33 */         int var17 = var14 + var16.func_177264_c();
/*  34 */         bindTexture(beaconBeam);
/*  35 */         GL11.glTexParameterf(3553, 10242, 10497.0F);
/*  36 */         GL11.glTexParameterf(3553, 10243, 10497.0F);
/*  37 */         GlStateManager.disableLighting();
/*  38 */         GlStateManager.disableCull();
/*  39 */         GlStateManager.disableBlend();
/*  40 */         GlStateManager.depthMask(true);
/*  41 */         GlStateManager.tryBlendFuncSeparate(770, 1, 1, 0);
/*  42 */         float var18 = (float)p_180536_1_.getWorld().getTotalWorldTime() + p_180536_8_;
/*  43 */         float var19 = -var18 * 0.2F - MathHelper.floor_float(-var18 * 0.1F);
/*  44 */         double var20 = var18 * 0.025D * -1.5D;
/*  45 */         var12.startDrawingQuads();
/*  46 */         double var22 = 0.2D;
/*  47 */         double var24 = 0.5D + Math.cos(var20 + 2.356194490192345D) * var22;
/*  48 */         double var26 = 0.5D + Math.sin(var20 + 2.356194490192345D) * var22;
/*  49 */         double var28 = 0.5D + Math.cos(var20 + 0.7853981633974483D) * var22;
/*  50 */         double var30 = 0.5D + Math.sin(var20 + 0.7853981633974483D) * var22;
/*  51 */         double var32 = 0.5D + Math.cos(var20 + 3.9269908169872414D) * var22;
/*  52 */         double var34 = 0.5D + Math.sin(var20 + 3.9269908169872414D) * var22;
/*  53 */         double var36 = 0.5D + Math.cos(var20 + 5.497787143782138D) * var22;
/*  54 */         double var38 = 0.5D + Math.sin(var20 + 5.497787143782138D) * var22;
/*  55 */         double var40 = 0.0D;
/*  56 */         double var42 = 1.0D;
/*  57 */         double var44 = (-1.0F + var19);
/*  58 */         double var46 = (var16.func_177264_c() * var10) * 0.5D / var22 + var44;
/*  59 */         var12.func_178960_a(var16.func_177263_b()[0], var16.func_177263_b()[1], var16.func_177263_b()[2], 0.125F);
/*  60 */         var12.addVertexWithUV(p_180536_2_ + var24, p_180536_4_ + var17, p_180536_6_ + var26, var42, var46);
/*  61 */         var12.addVertexWithUV(p_180536_2_ + var24, p_180536_4_ + var14, p_180536_6_ + var26, var42, var44);
/*  62 */         var12.addVertexWithUV(p_180536_2_ + var28, p_180536_4_ + var14, p_180536_6_ + var30, var40, var44);
/*  63 */         var12.addVertexWithUV(p_180536_2_ + var28, p_180536_4_ + var17, p_180536_6_ + var30, var40, var46);
/*  64 */         var12.addVertexWithUV(p_180536_2_ + var36, p_180536_4_ + var17, p_180536_6_ + var38, var42, var46);
/*  65 */         var12.addVertexWithUV(p_180536_2_ + var36, p_180536_4_ + var14, p_180536_6_ + var38, var42, var44);
/*  66 */         var12.addVertexWithUV(p_180536_2_ + var32, p_180536_4_ + var14, p_180536_6_ + var34, var40, var44);
/*  67 */         var12.addVertexWithUV(p_180536_2_ + var32, p_180536_4_ + var17, p_180536_6_ + var34, var40, var46);
/*  68 */         var12.addVertexWithUV(p_180536_2_ + var28, p_180536_4_ + var17, p_180536_6_ + var30, var42, var46);
/*  69 */         var12.addVertexWithUV(p_180536_2_ + var28, p_180536_4_ + var14, p_180536_6_ + var30, var42, var44);
/*  70 */         var12.addVertexWithUV(p_180536_2_ + var36, p_180536_4_ + var14, p_180536_6_ + var38, var40, var44);
/*  71 */         var12.addVertexWithUV(p_180536_2_ + var36, p_180536_4_ + var17, p_180536_6_ + var38, var40, var46);
/*  72 */         var12.addVertexWithUV(p_180536_2_ + var32, p_180536_4_ + var17, p_180536_6_ + var34, var42, var46);
/*  73 */         var12.addVertexWithUV(p_180536_2_ + var32, p_180536_4_ + var14, p_180536_6_ + var34, var42, var44);
/*  74 */         var12.addVertexWithUV(p_180536_2_ + var24, p_180536_4_ + var14, p_180536_6_ + var26, var40, var44);
/*  75 */         var12.addVertexWithUV(p_180536_2_ + var24, p_180536_4_ + var17, p_180536_6_ + var26, var40, var46);
/*  76 */         var11.draw();
/*  77 */         GlStateManager.enableBlend();
/*  78 */         GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
/*  79 */         GlStateManager.depthMask(false);
/*  80 */         var12.startDrawingQuads();
/*  81 */         var12.func_178960_a(var16.func_177263_b()[0], var16.func_177263_b()[1], var16.func_177263_b()[2], 0.125F);
/*  82 */         var20 = 0.2D;
/*  83 */         var22 = 0.2D;
/*  84 */         var24 = 0.8D;
/*  85 */         var26 = 0.2D;
/*  86 */         var28 = 0.2D;
/*  87 */         var30 = 0.8D;
/*  88 */         var32 = 0.8D;
/*  89 */         var34 = 0.8D;
/*  90 */         var36 = 0.0D;
/*  91 */         var38 = 1.0D;
/*  92 */         var40 = (-1.0F + var19);
/*  93 */         var42 = (var16.func_177264_c() * var10) + var40;
/*  94 */         var12.addVertexWithUV(p_180536_2_ + var20, p_180536_4_ + var17, p_180536_6_ + var22, var38, var42);
/*  95 */         var12.addVertexWithUV(p_180536_2_ + var20, p_180536_4_ + var14, p_180536_6_ + var22, var38, var40);
/*  96 */         var12.addVertexWithUV(p_180536_2_ + var24, p_180536_4_ + var14, p_180536_6_ + var26, var36, var40);
/*  97 */         var12.addVertexWithUV(p_180536_2_ + var24, p_180536_4_ + var17, p_180536_6_ + var26, var36, var42);
/*  98 */         var12.addVertexWithUV(p_180536_2_ + var32, p_180536_4_ + var17, p_180536_6_ + var34, var38, var42);
/*  99 */         var12.addVertexWithUV(p_180536_2_ + var32, p_180536_4_ + var14, p_180536_6_ + var34, var38, var40);
/* 100 */         var12.addVertexWithUV(p_180536_2_ + var28, p_180536_4_ + var14, p_180536_6_ + var30, var36, var40);
/* 101 */         var12.addVertexWithUV(p_180536_2_ + var28, p_180536_4_ + var17, p_180536_6_ + var30, var36, var42);
/* 102 */         var12.addVertexWithUV(p_180536_2_ + var24, p_180536_4_ + var17, p_180536_6_ + var26, var38, var42);
/* 103 */         var12.addVertexWithUV(p_180536_2_ + var24, p_180536_4_ + var14, p_180536_6_ + var26, var38, var40);
/* 104 */         var12.addVertexWithUV(p_180536_2_ + var32, p_180536_4_ + var14, p_180536_6_ + var34, var36, var40);
/* 105 */         var12.addVertexWithUV(p_180536_2_ + var32, p_180536_4_ + var17, p_180536_6_ + var34, var36, var42);
/* 106 */         var12.addVertexWithUV(p_180536_2_ + var28, p_180536_4_ + var17, p_180536_6_ + var30, var38, var42);
/* 107 */         var12.addVertexWithUV(p_180536_2_ + var28, p_180536_4_ + var14, p_180536_6_ + var30, var38, var40);
/* 108 */         var12.addVertexWithUV(p_180536_2_ + var20, p_180536_4_ + var14, p_180536_6_ + var22, var36, var40);
/* 109 */         var12.addVertexWithUV(p_180536_2_ + var20, p_180536_4_ + var17, p_180536_6_ + var22, var36, var42);
/* 110 */         var11.draw();
/* 111 */         GlStateManager.enableLighting();
/* 112 */         GlStateManager.func_179098_w();
/* 113 */         GlStateManager.depthMask(true);
/* 114 */         var14 = var17;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void renderTileEntityAt(TileEntity p_180535_1_, double p_180535_2_, double p_180535_4_, double p_180535_6_, float p_180535_8_, int p_180535_9_) {
/* 121 */     func_180536_a((TileEntityBeacon)p_180535_1_, p_180535_2_, p_180535_4_, p_180535_6_, p_180535_8_, p_180535_9_);
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\renderer\tileentity\TileEntityBeaconRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */