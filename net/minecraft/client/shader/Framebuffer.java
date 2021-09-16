/*     */ package net.minecraft.client.shader;
/*     */ 
/*     */ import net.minecraft.client.renderer.GlStateManager;
/*     */ import net.minecraft.client.renderer.OpenGlHelper;
/*     */ import net.minecraft.client.renderer.Tessellator;
/*     */ import net.minecraft.client.renderer.WorldRenderer;
/*     */ import net.minecraft.client.renderer.texture.TextureUtil;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Framebuffer
/*     */ {
/*     */   public int framebufferTextureWidth;
/*     */   public int framebufferTextureHeight;
/*     */   public int framebufferWidth;
/*     */   public int framebufferHeight;
/*     */   public boolean useDepth;
/*     */   public int framebufferObject;
/*     */   public int framebufferTexture;
/*     */   public int depthBuffer;
/*     */   public float[] framebufferColor;
/*     */   public int framebufferFilter;
/*     */   private static final String __OBFID = "CL_00000959";
/*     */   
/*     */   public Framebuffer(int p_i45078_1_, int p_i45078_2_, boolean p_i45078_3_) {
/*  27 */     this.useDepth = p_i45078_3_;
/*  28 */     this.framebufferObject = -1;
/*  29 */     this.framebufferTexture = -1;
/*  30 */     this.depthBuffer = -1;
/*  31 */     this.framebufferColor = new float[4];
/*  32 */     this.framebufferColor[0] = 1.0F;
/*  33 */     this.framebufferColor[1] = 1.0F;
/*  34 */     this.framebufferColor[2] = 1.0F;
/*  35 */     this.framebufferColor[3] = 0.0F;
/*  36 */     createBindFramebuffer(p_i45078_1_, p_i45078_2_);
/*     */   }
/*     */ 
/*     */   
/*     */   public void createBindFramebuffer(int p_147613_1_, int p_147613_2_) {
/*  41 */     if (!OpenGlHelper.isFramebufferEnabled()) {
/*     */       
/*  43 */       this.framebufferWidth = p_147613_1_;
/*  44 */       this.framebufferHeight = p_147613_2_;
/*     */     }
/*     */     else {
/*     */       
/*  48 */       GlStateManager.enableDepth();
/*     */       
/*  50 */       if (this.framebufferObject >= 0)
/*     */       {
/*  52 */         deleteFramebuffer();
/*     */       }
/*     */       
/*  55 */       createFramebuffer(p_147613_1_, p_147613_2_);
/*  56 */       checkFramebufferComplete();
/*  57 */       OpenGlHelper.func_153171_g(OpenGlHelper.field_153198_e, 0);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void deleteFramebuffer() {
/*  63 */     if (OpenGlHelper.isFramebufferEnabled()) {
/*     */       
/*  65 */       unbindFramebufferTexture();
/*  66 */       unbindFramebuffer();
/*     */       
/*  68 */       if (this.depthBuffer > -1) {
/*     */         
/*  70 */         OpenGlHelper.func_153184_g(this.depthBuffer);
/*  71 */         this.depthBuffer = -1;
/*     */       } 
/*     */       
/*  74 */       if (this.framebufferTexture > -1) {
/*     */         
/*  76 */         TextureUtil.deleteTexture(this.framebufferTexture);
/*  77 */         this.framebufferTexture = -1;
/*     */       } 
/*     */       
/*  80 */       if (this.framebufferObject > -1) {
/*     */         
/*  82 */         OpenGlHelper.func_153171_g(OpenGlHelper.field_153198_e, 0);
/*  83 */         OpenGlHelper.func_153174_h(this.framebufferObject);
/*  84 */         this.framebufferObject = -1;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void createFramebuffer(int p_147605_1_, int p_147605_2_) {
/*  91 */     this.framebufferWidth = p_147605_1_;
/*  92 */     this.framebufferHeight = p_147605_2_;
/*  93 */     this.framebufferTextureWidth = p_147605_1_;
/*  94 */     this.framebufferTextureHeight = p_147605_2_;
/*     */     
/*  96 */     if (!OpenGlHelper.isFramebufferEnabled()) {
/*     */       
/*  98 */       framebufferClear();
/*     */     }
/*     */     else {
/*     */       
/* 102 */       this.framebufferObject = OpenGlHelper.func_153165_e();
/* 103 */       this.framebufferTexture = TextureUtil.glGenTextures();
/*     */       
/* 105 */       if (this.useDepth)
/*     */       {
/* 107 */         this.depthBuffer = OpenGlHelper.func_153185_f();
/*     */       }
/*     */       
/* 110 */       setFramebufferFilter(9728);
/* 111 */       GlStateManager.func_179144_i(this.framebufferTexture);
/* 112 */       GL11.glTexImage2D(3553, 0, 32856, this.framebufferTextureWidth, this.framebufferTextureHeight, 0, 6408, 5121, null);
/* 113 */       OpenGlHelper.func_153171_g(OpenGlHelper.field_153198_e, this.framebufferObject);
/* 114 */       OpenGlHelper.func_153188_a(OpenGlHelper.field_153198_e, OpenGlHelper.field_153200_g, 3553, this.framebufferTexture, 0);
/*     */       
/* 116 */       if (this.useDepth) {
/*     */         
/* 118 */         OpenGlHelper.func_153176_h(OpenGlHelper.field_153199_f, this.depthBuffer);
/* 119 */         OpenGlHelper.func_153186_a(OpenGlHelper.field_153199_f, 33190, this.framebufferTextureWidth, this.framebufferTextureHeight);
/* 120 */         OpenGlHelper.func_153190_b(OpenGlHelper.field_153198_e, OpenGlHelper.field_153201_h, OpenGlHelper.field_153199_f, this.depthBuffer);
/*     */       } 
/*     */       
/* 123 */       framebufferClear();
/* 124 */       unbindFramebufferTexture();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void setFramebufferFilter(int p_147607_1_) {
/* 130 */     if (OpenGlHelper.isFramebufferEnabled()) {
/*     */       
/* 132 */       this.framebufferFilter = p_147607_1_;
/* 133 */       GlStateManager.func_179144_i(this.framebufferTexture);
/* 134 */       GL11.glTexParameterf(3553, 10241, p_147607_1_);
/* 135 */       GL11.glTexParameterf(3553, 10240, p_147607_1_);
/* 136 */       GL11.glTexParameterf(3553, 10242, 10496.0F);
/* 137 */       GL11.glTexParameterf(3553, 10243, 10496.0F);
/* 138 */       GlStateManager.func_179144_i(0);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void checkFramebufferComplete() {
/* 144 */     int var1 = OpenGlHelper.func_153167_i(OpenGlHelper.field_153198_e);
/*     */     
/* 146 */     if (var1 != OpenGlHelper.field_153202_i) {
/*     */       
/* 148 */       if (var1 == OpenGlHelper.field_153203_j)
/*     */       {
/* 150 */         throw new RuntimeException("GL_FRAMEBUFFER_INCOMPLETE_ATTACHMENT");
/*     */       }
/* 152 */       if (var1 == OpenGlHelper.field_153204_k)
/*     */       {
/* 154 */         throw new RuntimeException("GL_FRAMEBUFFER_INCOMPLETE_MISSING_ATTACHMENT");
/*     */       }
/* 156 */       if (var1 == OpenGlHelper.field_153205_l)
/*     */       {
/* 158 */         throw new RuntimeException("GL_FRAMEBUFFER_INCOMPLETE_DRAW_BUFFER");
/*     */       }
/* 160 */       if (var1 == OpenGlHelper.field_153206_m)
/*     */       {
/* 162 */         throw new RuntimeException("GL_FRAMEBUFFER_INCOMPLETE_READ_BUFFER");
/*     */       }
/*     */ 
/*     */       
/* 166 */       throw new RuntimeException("glCheckFramebufferStatus returned unknown status:" + var1);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void bindFramebufferTexture() {
/* 173 */     if (OpenGlHelper.isFramebufferEnabled())
/*     */     {
/* 175 */       GlStateManager.func_179144_i(this.framebufferTexture);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void unbindFramebufferTexture() {
/* 181 */     if (OpenGlHelper.isFramebufferEnabled())
/*     */     {
/* 183 */       GlStateManager.func_179144_i(0);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void bindFramebuffer(boolean p_147610_1_) {
/* 189 */     if (OpenGlHelper.isFramebufferEnabled()) {
/*     */       
/* 191 */       OpenGlHelper.func_153171_g(OpenGlHelper.field_153198_e, this.framebufferObject);
/*     */       
/* 193 */       if (p_147610_1_)
/*     */       {
/* 195 */         GlStateManager.viewport(0, 0, this.framebufferWidth, this.framebufferHeight);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void unbindFramebuffer() {
/* 202 */     if (OpenGlHelper.isFramebufferEnabled())
/*     */     {
/* 204 */       OpenGlHelper.func_153171_g(OpenGlHelper.field_153198_e, 0);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void setFramebufferColor(float p_147604_1_, float p_147604_2_, float p_147604_3_, float p_147604_4_) {
/* 210 */     this.framebufferColor[0] = p_147604_1_;
/* 211 */     this.framebufferColor[1] = p_147604_2_;
/* 212 */     this.framebufferColor[2] = p_147604_3_;
/* 213 */     this.framebufferColor[3] = p_147604_4_;
/*     */   }
/*     */ 
/*     */   
/*     */   public void framebufferRender(int p_147615_1_, int p_147615_2_) {
/* 218 */     func_178038_a(p_147615_1_, p_147615_2_, true);
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_178038_a(int p_178038_1_, int p_178038_2_, boolean p_178038_3_) {
/* 223 */     if (OpenGlHelper.isFramebufferEnabled()) {
/*     */       
/* 225 */       GlStateManager.colorMask(true, true, true, false);
/* 226 */       GlStateManager.disableDepth();
/* 227 */       GlStateManager.depthMask(false);
/* 228 */       GlStateManager.matrixMode(5889);
/* 229 */       GlStateManager.loadIdentity();
/* 230 */       GlStateManager.ortho(0.0D, p_178038_1_, p_178038_2_, 0.0D, 1000.0D, 3000.0D);
/* 231 */       GlStateManager.matrixMode(5888);
/* 232 */       GlStateManager.loadIdentity();
/* 233 */       GlStateManager.translate(0.0F, 0.0F, -2000.0F);
/* 234 */       GlStateManager.viewport(0, 0, p_178038_1_, p_178038_2_);
/* 235 */       GlStateManager.func_179098_w();
/* 236 */       GlStateManager.disableLighting();
/* 237 */       GlStateManager.disableAlpha();
/*     */       
/* 239 */       if (p_178038_3_) {
/*     */         
/* 241 */         GlStateManager.disableBlend();
/* 242 */         GlStateManager.enableColorMaterial();
/*     */       } 
/*     */       
/* 245 */       GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
/* 246 */       bindFramebufferTexture();
/* 247 */       float var4 = p_178038_1_;
/* 248 */       float var5 = p_178038_2_;
/* 249 */       float var6 = this.framebufferWidth / this.framebufferTextureWidth;
/* 250 */       float var7 = this.framebufferHeight / this.framebufferTextureHeight;
/* 251 */       Tessellator var8 = Tessellator.getInstance();
/* 252 */       WorldRenderer var9 = var8.getWorldRenderer();
/* 253 */       var9.startDrawingQuads();
/* 254 */       var9.func_178991_c(-1);
/* 255 */       var9.addVertexWithUV(0.0D, var5, 0.0D, 0.0D, 0.0D);
/* 256 */       var9.addVertexWithUV(var4, var5, 0.0D, var6, 0.0D);
/* 257 */       var9.addVertexWithUV(var4, 0.0D, 0.0D, var6, var7);
/* 258 */       var9.addVertexWithUV(0.0D, 0.0D, 0.0D, 0.0D, var7);
/* 259 */       var8.draw();
/* 260 */       unbindFramebufferTexture();
/* 261 */       GlStateManager.depthMask(true);
/* 262 */       GlStateManager.colorMask(true, true, true, true);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void framebufferClear() {
/* 268 */     bindFramebuffer(true);
/* 269 */     GlStateManager.clearColor(this.framebufferColor[0], this.framebufferColor[1], this.framebufferColor[2], this.framebufferColor[3]);
/* 270 */     int var1 = 16384;
/*     */     
/* 272 */     if (this.useDepth) {
/*     */       
/* 274 */       GlStateManager.clearDepth(1.0D);
/* 275 */       var1 |= 0x100;
/*     */     } 
/*     */     
/* 278 */     GlStateManager.clear(var1);
/* 279 */     unbindFramebuffer();
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\shader\Framebuffer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */