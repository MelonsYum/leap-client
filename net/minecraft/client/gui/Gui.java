/*     */ package net.minecraft.client.gui;
/*     */ 
/*     */ import net.minecraft.client.renderer.GlStateManager;
/*     */ import net.minecraft.client.renderer.Tessellator;
/*     */ import net.minecraft.client.renderer.WorldRenderer;
/*     */ import net.minecraft.client.renderer.texture.TextureAtlasSprite;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ 
/*     */ public class Gui
/*     */ {
/*  11 */   public static final ResourceLocation optionsBackground = new ResourceLocation("textures/gui/options_background.png");
/*  12 */   public static final ResourceLocation statIcons = new ResourceLocation("textures/gui/container/stats_icons.png");
/*  13 */   public static final ResourceLocation icons = new ResourceLocation("textures/gui/icons.png");
/*     */ 
/*     */   
/*     */   public static float zLevel;
/*     */   
/*     */   private static final String __OBFID = "CL_00000662";
/*     */ 
/*     */   
/*     */   protected void drawHorizontalLine(int startX, int endX, int y, int color) {
/*  22 */     if (endX < startX) {
/*     */       
/*  24 */       int var5 = startX;
/*  25 */       startX = endX;
/*  26 */       endX = var5;
/*     */     } 
/*     */     
/*  29 */     drawRect(startX, y, (endX + 1), (y + 1), color);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void drawVerticalLine(int x, int startY, int endY, int color) {
/*  37 */     if (endY < startY) {
/*     */       
/*  39 */       int var5 = startY;
/*  40 */       startY = endY;
/*  41 */       endY = var5;
/*     */     } 
/*     */     
/*  44 */     drawRect(x, (startY + 1), (x + 1), endY, color);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void drawRect(double left, double offset, double right, double d, int color) {
/*  54 */     if (left < right) {
/*     */       
/*  56 */       double var5 = left;
/*  57 */       left = right;
/*  58 */       right = var5;
/*     */     } 
/*     */     
/*  61 */     if (offset < d) {
/*     */       
/*  63 */       double var5 = offset;
/*  64 */       offset = d;
/*  65 */       d = var5;
/*     */     } 
/*     */     
/*  68 */     float var11 = (color >> 24 & 0xFF) / 255.0F;
/*  69 */     float var6 = (color >> 16 & 0xFF) / 255.0F;
/*  70 */     float var7 = (color >> 8 & 0xFF) / 255.0F;
/*  71 */     float var8 = (color & 0xFF) / 255.0F;
/*  72 */     Tessellator var9 = Tessellator.getInstance();
/*  73 */     WorldRenderer var10 = var9.getWorldRenderer();
/*  74 */     GlStateManager.enableBlend();
/*  75 */     GlStateManager.func_179090_x();
/*  76 */     GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
/*  77 */     GlStateManager.color(var6, var7, var8, var11);
/*  78 */     var10.startDrawingQuads();
/*  79 */     var10.addVertex(left, d, 0.0D);
/*  80 */     var10.addVertex(right, d, 0.0D);
/*  81 */     var10.addVertex(right, offset, 0.0D);
/*  82 */     var10.addVertex(left, offset, 0.0D);
/*  83 */     var9.draw();
/*  84 */     GlStateManager.func_179098_w();
/*  85 */     GlStateManager.disableBlend();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void drawGradientRect(float left, float f, float g, float h, int startColor, int endColor) {
/*  94 */     float var7 = (startColor >> 24 & 0xFF) / 255.0F;
/*  95 */     float var8 = (startColor >> 16 & 0xFF) / 255.0F;
/*  96 */     float var9 = (startColor >> 8 & 0xFF) / 255.0F;
/*  97 */     float var10 = (startColor & 0xFF) / 255.0F;
/*  98 */     float var11 = (endColor >> 24 & 0xFF) / 255.0F;
/*  99 */     float var12 = (endColor >> 16 & 0xFF) / 255.0F;
/* 100 */     float var13 = (endColor >> 8 & 0xFF) / 255.0F;
/* 101 */     float var14 = (endColor & 0xFF) / 255.0F;
/* 102 */     GlStateManager.func_179090_x();
/* 103 */     GlStateManager.enableBlend();
/* 104 */     GlStateManager.disableAlpha();
/* 105 */     GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
/* 106 */     GlStateManager.shadeModel(7425);
/* 107 */     Tessellator var15 = Tessellator.getInstance();
/* 108 */     WorldRenderer var16 = var15.getWorldRenderer();
/* 109 */     var16.startDrawingQuads();
/* 110 */     var16.func_178960_a(var8, var9, var10, var7);
/* 111 */     var16.addVertex(g, f, zLevel);
/* 112 */     var16.addVertex(left, f, zLevel);
/* 113 */     var16.func_178960_a(var12, var13, var14, var11);
/* 114 */     var16.addVertex(left, h, zLevel);
/* 115 */     var16.addVertex(g, h, zLevel);
/* 116 */     var15.draw();
/* 117 */     GlStateManager.shadeModel(7424);
/* 118 */     GlStateManager.disableBlend();
/* 119 */     GlStateManager.enableAlpha();
/* 120 */     GlStateManager.func_179098_w();
/*     */   }
/*     */   
/*     */   public static void drawRect2(double x, double y, double width, double height, int color) {
/* 124 */     drawRect(x, y, x + width, y + height, color);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void drawCenteredString(FontRenderer fontRendererIn, String text, float f, float g, int color) {
/* 133 */     fontRendererIn.drawStringWithShadow(text, (f - (fontRendererIn.getStringWidth(text) / 2)), g, color);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void drawString(FontRenderer fontRendererIn, String text, int x, int y, int color) {
/* 141 */     fontRendererIn.drawStringWithShadow(text, x, y, color);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void drawTexturedModalRect(int x, int y, int textureX, int textureY, int width, int height) {
/* 149 */     float var7 = 0.00390625F;
/* 150 */     float var8 = 0.00390625F;
/* 151 */     Tessellator var9 = Tessellator.getInstance();
/* 152 */     WorldRenderer var10 = var9.getWorldRenderer();
/* 153 */     var10.startDrawingQuads();
/* 154 */     var10.addVertexWithUV((x + 0), (y + height), zLevel, ((textureX + 0) * var7), ((textureY + height) * var8));
/* 155 */     var10.addVertexWithUV((x + width), (y + height), zLevel, ((textureX + width) * var7), ((textureY + height) * var8));
/* 156 */     var10.addVertexWithUV((x + width), (y + 0), zLevel, ((textureX + width) * var7), ((textureY + 0) * var8));
/* 157 */     var10.addVertexWithUV((x + 0), (y + 0), zLevel, ((textureX + 0) * var7), ((textureY + 0) * var8));
/* 158 */     var9.draw();
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_175174_a(float p_175174_1_, float p_175174_2_, int p_175174_3_, int p_175174_4_, int p_175174_5_, int p_175174_6_) {
/* 163 */     float var7 = 0.00390625F;
/* 164 */     float var8 = 0.00390625F;
/* 165 */     Tessellator var9 = Tessellator.getInstance();
/* 166 */     WorldRenderer var10 = var9.getWorldRenderer();
/* 167 */     var10.startDrawingQuads();
/* 168 */     var10.addVertexWithUV((p_175174_1_ + 0.0F), (p_175174_2_ + p_175174_6_), zLevel, ((p_175174_3_ + 0) * var7), ((p_175174_4_ + p_175174_6_) * var8));
/* 169 */     var10.addVertexWithUV((p_175174_1_ + p_175174_5_), (p_175174_2_ + p_175174_6_), zLevel, ((p_175174_3_ + p_175174_5_) * var7), ((p_175174_4_ + p_175174_6_) * var8));
/* 170 */     var10.addVertexWithUV((p_175174_1_ + p_175174_5_), (p_175174_2_ + 0.0F), zLevel, ((p_175174_3_ + p_175174_5_) * var7), ((p_175174_4_ + 0) * var8));
/* 171 */     var10.addVertexWithUV((p_175174_1_ + 0.0F), (p_175174_2_ + 0.0F), zLevel, ((p_175174_3_ + 0) * var7), ((p_175174_4_ + 0) * var8));
/* 172 */     var9.draw();
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_175175_a(int p_175175_1_, int p_175175_2_, TextureAtlasSprite p_175175_3_, int p_175175_4_, int p_175175_5_) {
/* 177 */     Tessellator var6 = Tessellator.getInstance();
/* 178 */     WorldRenderer var7 = var6.getWorldRenderer();
/* 179 */     var7.startDrawingQuads();
/* 180 */     var7.addVertexWithUV((p_175175_1_ + 0), (p_175175_2_ + p_175175_5_), zLevel, p_175175_3_.getMinU(), p_175175_3_.getMaxV());
/* 181 */     var7.addVertexWithUV((p_175175_1_ + p_175175_4_), (p_175175_2_ + p_175175_5_), zLevel, p_175175_3_.getMaxU(), p_175175_3_.getMaxV());
/* 182 */     var7.addVertexWithUV((p_175175_1_ + p_175175_4_), (p_175175_2_ + 0), zLevel, p_175175_3_.getMaxU(), p_175175_3_.getMinV());
/* 183 */     var7.addVertexWithUV((p_175175_1_ + 0), (p_175175_2_ + 0), zLevel, p_175175_3_.getMinU(), p_175175_3_.getMinV());
/* 184 */     var6.draw();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void drawModalRectWithCustomSizedTexture(float f, float g, float u, float v, float imageSpace, float imageSpace2, float textureWidth, float textureHeight) {
/* 192 */     float var8 = 1.0F / textureWidth;
/* 193 */     float var9 = 1.0F / textureHeight;
/* 194 */     Tessellator var10 = Tessellator.getInstance();
/* 195 */     WorldRenderer var11 = var10.getWorldRenderer();
/* 196 */     var11.startDrawingQuads();
/* 197 */     var11.addVertexWithUV(f, (g + imageSpace2), 0.0D, (u * var8), ((v + imageSpace2) * var9));
/* 198 */     var11.addVertexWithUV((f + imageSpace), (g + imageSpace2), 0.0D, ((u + imageSpace) * var8), ((v + imageSpace2) * var9));
/* 199 */     var11.addVertexWithUV((f + imageSpace), g, 0.0D, ((u + imageSpace) * var8), (v * var9));
/* 200 */     var11.addVertexWithUV(f, g, 0.0D, (u * var8), (v * var9));
/* 201 */     var10.draw();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void drawScaledCustomSizeModalRect(int x, int y, float u, float v, int uWidth, int vHeight, int width, int height, float tileWidth, float tileHeight) {
/* 209 */     float var10 = 1.0F / tileWidth;
/* 210 */     float var11 = 1.0F / tileHeight;
/* 211 */     Tessellator var12 = Tessellator.getInstance();
/* 212 */     WorldRenderer var13 = var12.getWorldRenderer();
/* 213 */     var13.startDrawingQuads();
/* 214 */     var13.addVertexWithUV(x, (y + height), 0.0D, (u * var10), ((v + vHeight) * var11));
/* 215 */     var13.addVertexWithUV((x + width), (y + height), 0.0D, ((u + uWidth) * var10), ((v + vHeight) * var11));
/* 216 */     var13.addVertexWithUV((x + width), y, 0.0D, ((u + uWidth) * var10), (v * var11));
/* 217 */     var13.addVertexWithUV(x, y, 0.0D, (u * var10), (v * var11));
/* 218 */     var12.draw();
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\gui\Gui.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */