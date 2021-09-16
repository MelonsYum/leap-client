/*     */ package optifine;
/*     */ 
/*     */ import net.minecraft.client.model.ModelRenderer;
/*     */ import net.minecraft.client.renderer.GlStateManager;
/*     */ import net.minecraft.client.renderer.Tessellator;
/*     */ import net.minecraft.client.renderer.WorldRenderer;
/*     */ import net.minecraft.util.MathHelper;
/*     */ 
/*     */ public class ModelSprite
/*     */ {
/*  11 */   private ModelRenderer modelRenderer = null;
/*  12 */   private int textureOffsetX = 0;
/*  13 */   private int textureOffsetY = 0;
/*  14 */   private float posX = 0.0F;
/*  15 */   private float posY = 0.0F;
/*  16 */   private float posZ = 0.0F;
/*  17 */   private int sizeX = 0;
/*  18 */   private int sizeY = 0;
/*  19 */   private int sizeZ = 0;
/*  20 */   private float sizeAdd = 0.0F;
/*  21 */   private float minU = 0.0F;
/*  22 */   private float minV = 0.0F;
/*  23 */   private float maxU = 0.0F;
/*  24 */   private float maxV = 0.0F;
/*     */ 
/*     */   
/*     */   public ModelSprite(ModelRenderer modelRenderer, int textureOffsetX, int textureOffsetY, float posX, float posY, float posZ, int sizeX, int sizeY, int sizeZ, float sizeAdd) {
/*  28 */     this.modelRenderer = modelRenderer;
/*  29 */     this.textureOffsetX = textureOffsetX;
/*  30 */     this.textureOffsetY = textureOffsetY;
/*  31 */     this.posX = posX;
/*  32 */     this.posY = posY;
/*  33 */     this.posZ = posZ;
/*  34 */     this.sizeX = sizeX;
/*  35 */     this.sizeY = sizeY;
/*  36 */     this.sizeZ = sizeZ;
/*  37 */     this.sizeAdd = sizeAdd;
/*  38 */     this.minU = textureOffsetX / modelRenderer.textureWidth;
/*  39 */     this.minV = textureOffsetY / modelRenderer.textureHeight;
/*  40 */     this.maxU = (textureOffsetX + sizeX) / modelRenderer.textureWidth;
/*  41 */     this.maxV = (textureOffsetY + sizeY) / modelRenderer.textureHeight;
/*     */   }
/*     */ 
/*     */   
/*     */   public void render(Tessellator tessellator, float scale) {
/*  46 */     GlStateManager.translate(this.posX * scale, this.posY * scale, this.posZ * scale);
/*  47 */     float rMinU = this.minU;
/*  48 */     float rMaxU = this.maxU;
/*  49 */     float rMinV = this.minV;
/*  50 */     float rMaxV = this.maxV;
/*     */     
/*  52 */     if (this.modelRenderer.mirror) {
/*     */       
/*  54 */       rMinU = this.maxU;
/*  55 */       rMaxU = this.minU;
/*     */     } 
/*     */     
/*  58 */     if (this.modelRenderer.mirrorV) {
/*     */       
/*  60 */       rMinV = this.maxV;
/*  61 */       rMaxV = this.minV;
/*     */     } 
/*     */     
/*  64 */     renderItemIn2D(tessellator, rMinU, rMinV, rMaxU, rMaxV, this.sizeX, this.sizeY, scale * this.sizeZ, this.modelRenderer.textureWidth, this.modelRenderer.textureHeight);
/*  65 */     GlStateManager.translate(-this.posX * scale, -this.posY * scale, -this.posZ * scale);
/*     */   }
/*     */ 
/*     */   
/*     */   public static void renderItemIn2D(Tessellator tess, float minU, float minV, float maxU, float maxV, int sizeX, int sizeY, float width, float texWidth, float texHeight) {
/*  70 */     if (width < 6.25E-4F)
/*     */     {
/*  72 */       width = 6.25E-4F;
/*     */     }
/*     */     
/*  75 */     float dU = maxU - minU;
/*  76 */     float dV = maxV - minV;
/*  77 */     double dimX = (MathHelper.abs(dU) * texWidth / 16.0F);
/*  78 */     double dimY = (MathHelper.abs(dV) * texHeight / 16.0F);
/*  79 */     WorldRenderer tessellator = tess.getWorldRenderer();
/*  80 */     tessellator.startDrawingQuads();
/*  81 */     tessellator.func_178980_d(0.0F, 0.0F, -1.0F);
/*  82 */     tessellator.addVertexWithUV(0.0D, 0.0D, 0.0D, minU, minV);
/*  83 */     tessellator.addVertexWithUV(dimX, 0.0D, 0.0D, maxU, minV);
/*  84 */     tessellator.addVertexWithUV(dimX, dimY, 0.0D, maxU, maxV);
/*  85 */     tessellator.addVertexWithUV(0.0D, dimY, 0.0D, minU, maxV);
/*  86 */     tess.draw();
/*  87 */     tessellator.startDrawingQuads();
/*  88 */     tessellator.func_178980_d(0.0F, 0.0F, 1.0F);
/*  89 */     tessellator.addVertexWithUV(0.0D, dimY, width, minU, maxV);
/*  90 */     tessellator.addVertexWithUV(dimX, dimY, width, maxU, maxV);
/*  91 */     tessellator.addVertexWithUV(dimX, 0.0D, width, maxU, minV);
/*  92 */     tessellator.addVertexWithUV(0.0D, 0.0D, width, minU, minV);
/*  93 */     tess.draw();
/*  94 */     float var8 = 0.5F * dU / sizeX;
/*  95 */     float var9 = 0.5F * dV / sizeY;
/*  96 */     tessellator.startDrawingQuads();
/*  97 */     tessellator.func_178980_d(-1.0F, 0.0F, 0.0F);
/*     */ 
/*     */     
/*     */     int var10;
/*     */     
/* 102 */     for (var10 = 0; var10 < sizeX; var10++) {
/*     */       
/* 104 */       float var11 = var10 / sizeX;
/* 105 */       float var12 = minU + dU * var11 + var8;
/* 106 */       tessellator.addVertexWithUV(var11 * dimX, 0.0D, width, var12, minV);
/* 107 */       tessellator.addVertexWithUV(var11 * dimX, 0.0D, 0.0D, var12, minV);
/* 108 */       tessellator.addVertexWithUV(var11 * dimX, dimY, 0.0D, var12, maxV);
/* 109 */       tessellator.addVertexWithUV(var11 * dimX, dimY, width, var12, maxV);
/*     */     } 
/*     */     
/* 112 */     tess.draw();
/* 113 */     tessellator.startDrawingQuads();
/* 114 */     tessellator.func_178980_d(1.0F, 0.0F, 0.0F);
/*     */ 
/*     */     
/* 117 */     for (var10 = 0; var10 < sizeX; var10++) {
/*     */       
/* 119 */       float var11 = var10 / sizeX;
/* 120 */       float var12 = minU + dU * var11 + var8;
/* 121 */       float var13 = var11 + 1.0F / sizeX;
/* 122 */       tessellator.addVertexWithUV(var13 * dimX, dimY, width, var12, maxV);
/* 123 */       tessellator.addVertexWithUV(var13 * dimX, dimY, 0.0D, var12, maxV);
/* 124 */       tessellator.addVertexWithUV(var13 * dimX, 0.0D, 0.0D, var12, minV);
/* 125 */       tessellator.addVertexWithUV(var13 * dimX, 0.0D, width, var12, minV);
/*     */     } 
/*     */     
/* 128 */     tess.draw();
/* 129 */     tessellator.startDrawingQuads();
/* 130 */     tessellator.func_178980_d(0.0F, 1.0F, 0.0F);
/*     */     
/* 132 */     for (var10 = 0; var10 < sizeY; var10++) {
/*     */       
/* 134 */       float var11 = var10 / sizeY;
/* 135 */       float var12 = minV + dV * var11 + var9;
/* 136 */       float var13 = var11 + 1.0F / sizeY;
/* 137 */       tessellator.addVertexWithUV(0.0D, var13 * dimY, 0.0D, minU, var12);
/* 138 */       tessellator.addVertexWithUV(dimX, var13 * dimY, 0.0D, maxU, var12);
/* 139 */       tessellator.addVertexWithUV(dimX, var13 * dimY, width, maxU, var12);
/* 140 */       tessellator.addVertexWithUV(0.0D, var13 * dimY, width, minU, var12);
/*     */     } 
/*     */     
/* 143 */     tess.draw();
/* 144 */     tessellator.startDrawingQuads();
/* 145 */     tessellator.func_178980_d(0.0F, -1.0F, 0.0F);
/*     */     
/* 147 */     for (var10 = 0; var10 < sizeY; var10++) {
/*     */       
/* 149 */       float var11 = var10 / sizeY;
/* 150 */       float var12 = minV + dV * var11 + var9;
/* 151 */       tessellator.addVertexWithUV(dimX, var11 * dimY, 0.0D, maxU, var12);
/* 152 */       tessellator.addVertexWithUV(0.0D, var11 * dimY, 0.0D, minU, var12);
/* 153 */       tessellator.addVertexWithUV(0.0D, var11 * dimY, width, minU, var12);
/* 154 */       tessellator.addVertexWithUV(dimX, var11 * dimY, width, maxU, var12);
/*     */     } 
/*     */     
/* 157 */     tess.draw();
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\optifine\ModelSprite.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */