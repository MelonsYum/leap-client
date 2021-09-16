/*     */ package net.minecraft.client.renderer.tileentity;
/*     */ 
/*     */ import java.nio.FloatBuffer;
/*     */ import java.util.Random;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.renderer.ActiveRenderInfo;
/*     */ import net.minecraft.client.renderer.GLAllocation;
/*     */ import net.minecraft.client.renderer.GlStateManager;
/*     */ import net.minecraft.client.renderer.Tessellator;
/*     */ import net.minecraft.client.renderer.WorldRenderer;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.tileentity.TileEntityEndPortal;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ 
/*     */ public class TileEntityEndPortalRenderer
/*     */   extends TileEntitySpecialRenderer {
/*  17 */   private static final ResourceLocation field_147529_c = new ResourceLocation("textures/environment/end_sky.png");
/*  18 */   private static final ResourceLocation field_147526_d = new ResourceLocation("textures/entity/end_portal.png");
/*  19 */   private static final Random field_147527_e = new Random(31100L);
/*  20 */   FloatBuffer field_147528_b = GLAllocation.createDirectFloatBuffer(16);
/*     */   
/*     */   private static final String __OBFID = "CL_00002467";
/*     */   
/*     */   public void func_180544_a(TileEntityEndPortal p_180544_1_, double p_180544_2_, double p_180544_4_, double p_180544_6_, float p_180544_8_, int p_180544_9_) {
/*  25 */     float var10 = (float)this.rendererDispatcher.field_147560_j;
/*  26 */     float var11 = (float)this.rendererDispatcher.field_147561_k;
/*  27 */     float var12 = (float)this.rendererDispatcher.field_147558_l;
/*  28 */     GlStateManager.disableLighting();
/*  29 */     field_147527_e.setSeed(31100L);
/*  30 */     float var13 = 0.75F;
/*     */     
/*  32 */     for (int var14 = 0; var14 < 16; var14++) {
/*     */       
/*  34 */       GlStateManager.pushMatrix();
/*  35 */       float var15 = (16 - var14);
/*  36 */       float var16 = 0.0625F;
/*  37 */       float var17 = 1.0F / (var15 + 1.0F);
/*     */       
/*  39 */       if (var14 == 0) {
/*     */         
/*  41 */         bindTexture(field_147529_c);
/*  42 */         var17 = 0.1F;
/*  43 */         var15 = 65.0F;
/*  44 */         var16 = 0.125F;
/*  45 */         GlStateManager.enableBlend();
/*  46 */         GlStateManager.blendFunc(770, 771);
/*     */       } 
/*     */       
/*  49 */       if (var14 >= 1)
/*     */       {
/*  51 */         bindTexture(field_147526_d);
/*     */       }
/*     */       
/*  54 */       if (var14 == 1) {
/*     */         
/*  56 */         GlStateManager.enableBlend();
/*  57 */         GlStateManager.blendFunc(1, 1);
/*  58 */         var16 = 0.5F;
/*     */       } 
/*     */       
/*  61 */       float var18 = (float)-(p_180544_4_ + var13);
/*  62 */       float var19 = var18 + (float)(ActiveRenderInfo.func_178804_a()).yCoord;
/*  63 */       float var20 = var18 + var15 + (float)(ActiveRenderInfo.func_178804_a()).yCoord;
/*  64 */       float var21 = var19 / var20;
/*  65 */       var21 += (float)(p_180544_4_ + var13);
/*  66 */       GlStateManager.translate(var10, var21, var12);
/*  67 */       GlStateManager.texGen(GlStateManager.TexGen.S, 9217);
/*  68 */       GlStateManager.texGen(GlStateManager.TexGen.T, 9217);
/*  69 */       GlStateManager.texGen(GlStateManager.TexGen.R, 9217);
/*  70 */       GlStateManager.texGen(GlStateManager.TexGen.Q, 9216);
/*  71 */       GlStateManager.func_179105_a(GlStateManager.TexGen.S, 9473, func_147525_a(1.0F, 0.0F, 0.0F, 0.0F));
/*  72 */       GlStateManager.func_179105_a(GlStateManager.TexGen.T, 9473, func_147525_a(0.0F, 0.0F, 1.0F, 0.0F));
/*  73 */       GlStateManager.func_179105_a(GlStateManager.TexGen.R, 9473, func_147525_a(0.0F, 0.0F, 0.0F, 1.0F));
/*  74 */       GlStateManager.func_179105_a(GlStateManager.TexGen.Q, 9474, func_147525_a(0.0F, 1.0F, 0.0F, 0.0F));
/*  75 */       GlStateManager.enableTexGenCoord(GlStateManager.TexGen.S);
/*  76 */       GlStateManager.enableTexGenCoord(GlStateManager.TexGen.T);
/*  77 */       GlStateManager.enableTexGenCoord(GlStateManager.TexGen.R);
/*  78 */       GlStateManager.enableTexGenCoord(GlStateManager.TexGen.Q);
/*  79 */       GlStateManager.popMatrix();
/*  80 */       GlStateManager.matrixMode(5890);
/*  81 */       GlStateManager.pushMatrix();
/*  82 */       GlStateManager.loadIdentity();
/*  83 */       GlStateManager.translate(0.0F, (float)(Minecraft.getSystemTime() % 700000L) / 700000.0F, 0.0F);
/*  84 */       GlStateManager.scale(var16, var16, var16);
/*  85 */       GlStateManager.translate(0.5F, 0.5F, 0.0F);
/*  86 */       GlStateManager.rotate((var14 * var14 * 4321 + var14 * 9) * 2.0F, 0.0F, 0.0F, 1.0F);
/*  87 */       GlStateManager.translate(-0.5F, -0.5F, 0.0F);
/*  88 */       GlStateManager.translate(-var10, -var12, -var11);
/*  89 */       var19 = var18 + (float)(ActiveRenderInfo.func_178804_a()).yCoord;
/*  90 */       GlStateManager.translate((float)(ActiveRenderInfo.func_178804_a()).xCoord * var15 / var19, (float)(ActiveRenderInfo.func_178804_a()).zCoord * var15 / var19, -var11);
/*  91 */       Tessellator var25 = Tessellator.getInstance();
/*  92 */       WorldRenderer var26 = var25.getWorldRenderer();
/*  93 */       var26.startDrawingQuads();
/*  94 */       float var22 = field_147527_e.nextFloat() * 0.5F + 0.1F;
/*  95 */       float var23 = field_147527_e.nextFloat() * 0.5F + 0.4F;
/*  96 */       float var24 = field_147527_e.nextFloat() * 0.5F + 0.5F;
/*     */       
/*  98 */       if (var14 == 0) {
/*     */         
/* 100 */         var24 = 1.0F;
/* 101 */         var23 = 1.0F;
/* 102 */         var22 = 1.0F;
/*     */       } 
/*     */       
/* 105 */       var26.func_178960_a(var22 * var17, var23 * var17, var24 * var17, 1.0F);
/* 106 */       var26.addVertex(p_180544_2_, p_180544_4_ + var13, p_180544_6_);
/* 107 */       var26.addVertex(p_180544_2_, p_180544_4_ + var13, p_180544_6_ + 1.0D);
/* 108 */       var26.addVertex(p_180544_2_ + 1.0D, p_180544_4_ + var13, p_180544_6_ + 1.0D);
/* 109 */       var26.addVertex(p_180544_2_ + 1.0D, p_180544_4_ + var13, p_180544_6_);
/* 110 */       var25.draw();
/* 111 */       GlStateManager.popMatrix();
/* 112 */       GlStateManager.matrixMode(5888);
/*     */     } 
/*     */     
/* 115 */     GlStateManager.disableBlend();
/* 116 */     GlStateManager.disableTexGenCoord(GlStateManager.TexGen.S);
/* 117 */     GlStateManager.disableTexGenCoord(GlStateManager.TexGen.T);
/* 118 */     GlStateManager.disableTexGenCoord(GlStateManager.TexGen.R);
/* 119 */     GlStateManager.disableTexGenCoord(GlStateManager.TexGen.Q);
/* 120 */     GlStateManager.enableLighting();
/*     */   }
/*     */ 
/*     */   
/*     */   private FloatBuffer func_147525_a(float p_147525_1_, float p_147525_2_, float p_147525_3_, float p_147525_4_) {
/* 125 */     this.field_147528_b.clear();
/* 126 */     this.field_147528_b.put(p_147525_1_).put(p_147525_2_).put(p_147525_3_).put(p_147525_4_);
/* 127 */     this.field_147528_b.flip();
/* 128 */     return this.field_147528_b;
/*     */   }
/*     */ 
/*     */   
/*     */   public void renderTileEntityAt(TileEntity p_180535_1_, double p_180535_2_, double p_180535_4_, double p_180535_6_, float p_180535_8_, int p_180535_9_) {
/* 133 */     func_180544_a((TileEntityEndPortal)p_180535_1_, p_180535_2_, p_180535_4_, p_180535_6_, p_180535_8_, p_180535_9_);
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\renderer\tileentity\TileEntityEndPortalRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */