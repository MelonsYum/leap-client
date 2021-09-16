/*     */ package net.minecraft.client;
/*     */ 
/*     */ import net.minecraft.client.gui.Gui;
/*     */ import net.minecraft.client.gui.ScaledResolution;
/*     */ import net.minecraft.client.renderer.GlStateManager;
/*     */ import net.minecraft.client.renderer.OpenGlHelper;
/*     */ import net.minecraft.client.renderer.Tessellator;
/*     */ import net.minecraft.client.renderer.WorldRenderer;
/*     */ import net.minecraft.client.shader.Framebuffer;
/*     */ import net.minecraft.util.IProgressUpdate;
/*     */ import net.minecraft.util.MinecraftError;
/*     */ 
/*     */ public class LoadingScreenRenderer
/*     */   implements IProgressUpdate {
/*  15 */   private String field_73727_a = "";
/*     */ 
/*     */ 
/*     */   
/*     */   private Minecraft mc;
/*     */ 
/*     */ 
/*     */   
/*  23 */   private String currentlyDisplayedText = "";
/*  24 */   private long field_73723_d = Minecraft.getSystemTime();
/*     */   
/*     */   private boolean field_73724_e;
/*     */   private ScaledResolution field_146587_f;
/*     */   private Framebuffer field_146588_g;
/*     */   private static final String __OBFID = "CL_00000655";
/*     */   
/*     */   public LoadingScreenRenderer(Minecraft mcIn) {
/*  32 */     this.mc = mcIn;
/*  33 */     this.field_146587_f = new ScaledResolution(mcIn, mcIn.displayWidth, mcIn.displayHeight);
/*  34 */     this.field_146588_g = new Framebuffer(mcIn.displayWidth, mcIn.displayHeight, false);
/*  35 */     this.field_146588_g.setFramebufferFilter(9728);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void resetProgressAndMessage(String p_73721_1_) {
/*  44 */     this.field_73724_e = false;
/*  45 */     func_73722_d(p_73721_1_);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void displaySavingString(String message) {
/*  53 */     this.field_73724_e = true;
/*  54 */     func_73722_d(message);
/*     */   }
/*     */ 
/*     */   
/*     */   private void func_73722_d(String p_73722_1_) {
/*  59 */     this.currentlyDisplayedText = p_73722_1_;
/*     */     
/*  61 */     if (!this.mc.running) {
/*     */       
/*  63 */       if (!this.field_73724_e)
/*     */       {
/*  65 */         throw new MinecraftError();
/*     */       }
/*     */     }
/*     */     else {
/*     */       
/*  70 */       GlStateManager.clear(256);
/*  71 */       GlStateManager.matrixMode(5889);
/*  72 */       GlStateManager.loadIdentity();
/*     */       
/*  74 */       if (OpenGlHelper.isFramebufferEnabled()) {
/*     */         
/*  76 */         int var2 = this.field_146587_f.getScaleFactor();
/*  77 */         GlStateManager.ortho(0.0D, (this.field_146587_f.getScaledWidth() * var2), (this.field_146587_f.getScaledHeight() * var2), 0.0D, 100.0D, 300.0D);
/*     */       }
/*     */       else {
/*     */         
/*  81 */         ScaledResolution var3 = new ScaledResolution(this.mc, this.mc.displayWidth, this.mc.displayHeight);
/*  82 */         GlStateManager.ortho(0.0D, var3.getScaledWidth_double(), var3.getScaledHeight_double(), 0.0D, 100.0D, 300.0D);
/*     */       } 
/*     */       
/*  85 */       GlStateManager.matrixMode(5888);
/*  86 */       GlStateManager.loadIdentity();
/*  87 */       GlStateManager.translate(0.0F, 0.0F, -200.0F);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void displayLoadingString(String message) {
/*  96 */     if (!this.mc.running) {
/*     */       
/*  98 */       if (!this.field_73724_e)
/*     */       {
/* 100 */         throw new MinecraftError();
/*     */       }
/*     */     }
/*     */     else {
/*     */       
/* 105 */       this.field_73723_d = 0L;
/* 106 */       this.field_73727_a = message;
/* 107 */       setLoadingProgress(-1);
/* 108 */       this.field_73723_d = 0L;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setLoadingProgress(int progress) {
/* 117 */     if (!this.mc.running) {
/*     */       
/* 119 */       if (!this.field_73724_e)
/*     */       {
/* 121 */         throw new MinecraftError();
/*     */       }
/*     */     }
/*     */     else {
/*     */       
/* 126 */       long var2 = Minecraft.getSystemTime();
/*     */       
/* 128 */       if (var2 - this.field_73723_d >= 100L) {
/*     */         
/* 130 */         this.field_73723_d = var2;
/* 131 */         ScaledResolution var4 = new ScaledResolution(this.mc, this.mc.displayWidth, this.mc.displayHeight);
/* 132 */         int var5 = var4.getScaleFactor();
/* 133 */         int var6 = var4.getScaledWidth();
/* 134 */         int var7 = var4.getScaledHeight();
/*     */         
/* 136 */         if (OpenGlHelper.isFramebufferEnabled()) {
/*     */           
/* 138 */           this.field_146588_g.framebufferClear();
/*     */         }
/*     */         else {
/*     */           
/* 142 */           GlStateManager.clear(256);
/*     */         } 
/*     */         
/* 145 */         this.field_146588_g.bindFramebuffer(false);
/* 146 */         GlStateManager.matrixMode(5889);
/* 147 */         GlStateManager.loadIdentity();
/* 148 */         GlStateManager.ortho(0.0D, var4.getScaledWidth_double(), var4.getScaledHeight_double(), 0.0D, 100.0D, 300.0D);
/* 149 */         GlStateManager.matrixMode(5888);
/* 150 */         GlStateManager.loadIdentity();
/* 151 */         GlStateManager.translate(0.0F, 0.0F, -200.0F);
/*     */         
/* 153 */         if (!OpenGlHelper.isFramebufferEnabled())
/*     */         {
/* 155 */           GlStateManager.clear(16640);
/*     */         }
/*     */         
/* 158 */         Tessellator var8 = Tessellator.getInstance();
/* 159 */         WorldRenderer var9 = var8.getWorldRenderer();
/* 160 */         this.mc.getTextureManager().bindTexture(Gui.optionsBackground);
/* 161 */         float var10 = 32.0F;
/* 162 */         var9.startDrawingQuads();
/* 163 */         var9.func_178991_c(4210752);
/* 164 */         var9.addVertexWithUV(0.0D, var7, 0.0D, 0.0D, (var7 / var10));
/* 165 */         var9.addVertexWithUV(var6, var7, 0.0D, (var6 / var10), (var7 / var10));
/* 166 */         var9.addVertexWithUV(var6, 0.0D, 0.0D, (var6 / var10), 0.0D);
/* 167 */         var9.addVertexWithUV(0.0D, 0.0D, 0.0D, 0.0D, 0.0D);
/* 168 */         var8.draw();
/*     */         
/* 170 */         if (progress >= 0) {
/*     */           
/* 172 */           byte var11 = 100;
/* 173 */           byte var12 = 2;
/* 174 */           int var13 = var6 / 2 - var11 / 2;
/* 175 */           int var14 = var7 / 2 + 16;
/* 176 */           GlStateManager.func_179090_x();
/* 177 */           var9.startDrawingQuads();
/* 178 */           var9.func_178991_c(8421504);
/* 179 */           var9.addVertex(var13, var14, 0.0D);
/* 180 */           var9.addVertex(var13, (var14 + var12), 0.0D);
/* 181 */           var9.addVertex((var13 + var11), (var14 + var12), 0.0D);
/* 182 */           var9.addVertex((var13 + var11), var14, 0.0D);
/* 183 */           var9.func_178991_c(8454016);
/* 184 */           var9.addVertex(var13, var14, 0.0D);
/* 185 */           var9.addVertex(var13, (var14 + var12), 0.0D);
/* 186 */           var9.addVertex((var13 + progress), (var14 + var12), 0.0D);
/* 187 */           var9.addVertex((var13 + progress), var14, 0.0D);
/* 188 */           var8.draw();
/* 189 */           GlStateManager.func_179098_w();
/*     */         } 
/*     */         
/* 192 */         GlStateManager.enableBlend();
/* 193 */         GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
/* 194 */         this.mc.fontRendererObj.drawStringWithShadow(this.currentlyDisplayedText, ((var6 - this.mc.fontRendererObj.getStringWidth(this.currentlyDisplayedText)) / 2), (var7 / 2 - 4 - 16), 16777215);
/* 195 */         this.mc.fontRendererObj.drawStringWithShadow(this.field_73727_a, ((var6 - this.mc.fontRendererObj.getStringWidth(this.field_73727_a)) / 2), (var7 / 2 - 4 + 8), 16777215);
/* 196 */         this.field_146588_g.unbindFramebuffer();
/*     */         
/* 198 */         if (OpenGlHelper.isFramebufferEnabled())
/*     */         {
/* 200 */           this.field_146588_g.framebufferRender(var6 * var5, var7 * var5);
/*     */         }
/*     */         
/* 203 */         this.mc.func_175601_h();
/*     */ 
/*     */         
/*     */         try {
/* 207 */           Thread.yield();
/*     */         }
/* 209 */         catch (Exception exception) {}
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public void setDoneWorking() {}
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\LoadingScreenRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */