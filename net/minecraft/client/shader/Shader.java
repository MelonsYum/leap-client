/*     */ package net.minecraft.client.shader;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import javax.vecmath.Matrix4f;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.renderer.GlStateManager;
/*     */ import net.minecraft.client.renderer.Tessellator;
/*     */ import net.minecraft.client.renderer.WorldRenderer;
/*     */ import net.minecraft.client.resources.IResourceManager;
/*     */ import net.minecraft.client.util.JsonException;
/*     */ 
/*     */ public class Shader
/*     */ {
/*     */   private final ShaderManager manager;
/*     */   public final Framebuffer framebufferIn;
/*     */   public final Framebuffer framebufferOut;
/*  19 */   private final List listAuxFramebuffers = Lists.newArrayList();
/*  20 */   private final List listAuxNames = Lists.newArrayList();
/*  21 */   private final List listAuxWidths = Lists.newArrayList();
/*  22 */   private final List listAuxHeights = Lists.newArrayList();
/*     */   
/*     */   private Matrix4f projectionMatrix;
/*     */   private static final String __OBFID = "CL_00001042";
/*     */   
/*     */   public Shader(IResourceManager p_i45089_1_, String p_i45089_2_, Framebuffer p_i45089_3_, Framebuffer p_i45089_4_) throws JsonException {
/*  28 */     this.manager = new ShaderManager(p_i45089_1_, p_i45089_2_);
/*  29 */     this.framebufferIn = p_i45089_3_;
/*  30 */     this.framebufferOut = p_i45089_4_;
/*     */   }
/*     */ 
/*     */   
/*     */   public void deleteShader() {
/*  35 */     this.manager.deleteShader();
/*     */   }
/*     */ 
/*     */   
/*     */   public void addAuxFramebuffer(String p_148041_1_, Object p_148041_2_, int p_148041_3_, int p_148041_4_) {
/*  40 */     this.listAuxNames.add(this.listAuxNames.size(), p_148041_1_);
/*  41 */     this.listAuxFramebuffers.add(this.listAuxFramebuffers.size(), p_148041_2_);
/*  42 */     this.listAuxWidths.add(this.listAuxWidths.size(), Integer.valueOf(p_148041_3_));
/*  43 */     this.listAuxHeights.add(this.listAuxHeights.size(), Integer.valueOf(p_148041_4_));
/*     */   }
/*     */ 
/*     */   
/*     */   private void preLoadShader() {
/*  48 */     GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
/*  49 */     GlStateManager.disableBlend();
/*  50 */     GlStateManager.disableDepth();
/*  51 */     GlStateManager.disableAlpha();
/*  52 */     GlStateManager.disableFog();
/*  53 */     GlStateManager.disableLighting();
/*  54 */     GlStateManager.disableColorMaterial();
/*  55 */     GlStateManager.func_179098_w();
/*  56 */     GlStateManager.func_179144_i(0);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setProjectionMatrix(Matrix4f p_148045_1_) {
/*  61 */     this.projectionMatrix = p_148045_1_;
/*     */   }
/*     */ 
/*     */   
/*     */   public void loadShader(float p_148042_1_) {
/*  66 */     preLoadShader();
/*  67 */     this.framebufferIn.unbindFramebuffer();
/*  68 */     float var2 = this.framebufferOut.framebufferTextureWidth;
/*  69 */     float var3 = this.framebufferOut.framebufferTextureHeight;
/*  70 */     GlStateManager.viewport(0, 0, (int)var2, (int)var3);
/*  71 */     this.manager.addSamplerTexture("DiffuseSampler", this.framebufferIn);
/*     */     
/*  73 */     for (int var4 = 0; var4 < this.listAuxFramebuffers.size(); var4++) {
/*     */       
/*  75 */       this.manager.addSamplerTexture(this.listAuxNames.get(var4), this.listAuxFramebuffers.get(var4));
/*  76 */       this.manager.getShaderUniformOrDefault("AuxSize" + var4).set(((Integer)this.listAuxWidths.get(var4)).intValue(), ((Integer)this.listAuxHeights.get(var4)).intValue());
/*     */     } 
/*     */     
/*  79 */     this.manager.getShaderUniformOrDefault("ProjMat").set(this.projectionMatrix);
/*  80 */     this.manager.getShaderUniformOrDefault("InSize").set(this.framebufferIn.framebufferTextureWidth, this.framebufferIn.framebufferTextureHeight);
/*  81 */     this.manager.getShaderUniformOrDefault("OutSize").set(var2, var3);
/*  82 */     this.manager.getShaderUniformOrDefault("Time").set(p_148042_1_);
/*  83 */     Minecraft var9 = Minecraft.getMinecraft();
/*  84 */     this.manager.getShaderUniformOrDefault("ScreenSize").set(var9.displayWidth, var9.displayHeight);
/*  85 */     this.manager.useShader();
/*  86 */     this.framebufferOut.framebufferClear();
/*  87 */     this.framebufferOut.bindFramebuffer(false);
/*  88 */     GlStateManager.depthMask(false);
/*  89 */     GlStateManager.colorMask(true, true, true, true);
/*  90 */     Tessellator var5 = Tessellator.getInstance();
/*  91 */     WorldRenderer var6 = var5.getWorldRenderer();
/*  92 */     var6.startDrawingQuads();
/*  93 */     var6.func_178991_c(-1);
/*  94 */     var6.addVertex(0.0D, var3, 500.0D);
/*  95 */     var6.addVertex(var2, var3, 500.0D);
/*  96 */     var6.addVertex(var2, 0.0D, 500.0D);
/*  97 */     var6.addVertex(0.0D, 0.0D, 500.0D);
/*  98 */     var5.draw();
/*  99 */     GlStateManager.depthMask(true);
/* 100 */     GlStateManager.colorMask(true, true, true, true);
/* 101 */     this.manager.endShader();
/* 102 */     this.framebufferOut.unbindFramebuffer();
/* 103 */     this.framebufferIn.unbindFramebufferTexture();
/* 104 */     Iterator var7 = this.listAuxFramebuffers.iterator();
/*     */     
/* 106 */     while (var7.hasNext()) {
/*     */       
/* 108 */       Object var8 = var7.next();
/*     */       
/* 110 */       if (var8 instanceof Framebuffer)
/*     */       {
/* 112 */         ((Framebuffer)var8).unbindFramebufferTexture();
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public ShaderManager getShaderManager() {
/* 119 */     return this.manager;
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\shader\Shader.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */