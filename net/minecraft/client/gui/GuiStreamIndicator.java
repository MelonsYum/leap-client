/*     */ package net.minecraft.client.gui;
/*     */ 
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.renderer.GlStateManager;
/*     */ import net.minecraft.client.renderer.Tessellator;
/*     */ import net.minecraft.client.renderer.WorldRenderer;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ 
/*     */ public class GuiStreamIndicator
/*     */ {
/*  11 */   private static final ResourceLocation locationStreamIndicator = new ResourceLocation("textures/gui/stream_indicator.png");
/*     */   private final Minecraft mc;
/*  13 */   private float field_152443_c = 1.0F;
/*  14 */   private int field_152444_d = 1;
/*     */   
/*     */   private static final String __OBFID = "CL_00001849";
/*     */   
/*     */   public GuiStreamIndicator(Minecraft mcIn) {
/*  19 */     this.mc = mcIn;
/*     */   }
/*     */ 
/*     */   
/*     */   public void render(int p_152437_1_, int p_152437_2_) {
/*  24 */     if (this.mc.getTwitchStream().func_152934_n()) {
/*     */       
/*  26 */       GlStateManager.enableBlend();
/*  27 */       int var3 = this.mc.getTwitchStream().func_152920_A();
/*     */       
/*  29 */       if (var3 > 0) {
/*     */         
/*  31 */         int i = var3;
/*  32 */         int var5 = this.mc.fontRendererObj.getStringWidth(i);
/*  33 */         boolean var6 = true;
/*  34 */         int var7 = p_152437_1_ - var5 - 1;
/*  35 */         int var8 = p_152437_2_ + 20 - 1;
/*  36 */         int var10 = p_152437_2_ + 20 + this.mc.fontRendererObj.FONT_HEIGHT - 1;
/*  37 */         GlStateManager.func_179090_x();
/*  38 */         Tessellator var11 = Tessellator.getInstance();
/*  39 */         WorldRenderer var12 = var11.getWorldRenderer();
/*  40 */         GlStateManager.color(0.0F, 0.0F, 0.0F, (0.65F + 0.35000002F * this.field_152443_c) / 2.0F);
/*  41 */         var12.startDrawingQuads();
/*  42 */         var12.addVertex(var7, var10, 0.0D);
/*  43 */         var12.addVertex(p_152437_1_, var10, 0.0D);
/*  44 */         var12.addVertex(p_152437_1_, var8, 0.0D);
/*  45 */         var12.addVertex(var7, var8, 0.0D);
/*  46 */         var11.draw();
/*  47 */         GlStateManager.func_179098_w();
/*  48 */         this.mc.fontRendererObj.drawString(i, (p_152437_1_ - var5), (p_152437_2_ + 20), 16777215);
/*     */       } 
/*     */       
/*  51 */       render(p_152437_1_, p_152437_2_, func_152440_b(), 0);
/*  52 */       render(p_152437_1_, p_152437_2_, func_152438_c(), 17);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void render(int p_152436_1_, int p_152436_2_, int p_152436_3_, int p_152436_4_) {
/*  58 */     GlStateManager.color(1.0F, 1.0F, 1.0F, 0.65F + 0.35000002F * this.field_152443_c);
/*  59 */     this.mc.getTextureManager().bindTexture(locationStreamIndicator);
/*  60 */     float var5 = 150.0F;
/*  61 */     float var6 = 0.0F;
/*  62 */     float var7 = p_152436_3_ * 0.015625F;
/*  63 */     float var8 = 1.0F;
/*  64 */     float var9 = (p_152436_3_ + 16) * 0.015625F;
/*  65 */     Tessellator var10 = Tessellator.getInstance();
/*  66 */     WorldRenderer var11 = var10.getWorldRenderer();
/*  67 */     var11.startDrawingQuads();
/*  68 */     var11.addVertexWithUV((p_152436_1_ - 16 - p_152436_4_), (p_152436_2_ + 16), var5, var6, var9);
/*  69 */     var11.addVertexWithUV((p_152436_1_ - p_152436_4_), (p_152436_2_ + 16), var5, var8, var9);
/*  70 */     var11.addVertexWithUV((p_152436_1_ - p_152436_4_), (p_152436_2_ + 0), var5, var8, var7);
/*  71 */     var11.addVertexWithUV((p_152436_1_ - 16 - p_152436_4_), (p_152436_2_ + 0), var5, var6, var7);
/*  72 */     var10.draw();
/*  73 */     GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
/*     */   }
/*     */ 
/*     */   
/*     */   private int func_152440_b() {
/*  78 */     return this.mc.getTwitchStream().isPaused() ? 16 : 0;
/*     */   }
/*     */ 
/*     */   
/*     */   private int func_152438_c() {
/*  83 */     return this.mc.getTwitchStream().func_152929_G() ? 48 : 32;
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_152439_a() {
/*  88 */     if (this.mc.getTwitchStream().func_152934_n()) {
/*     */       
/*  90 */       this.field_152443_c += 0.025F * this.field_152444_d;
/*     */       
/*  92 */       if (this.field_152443_c < 0.0F)
/*     */       {
/*  94 */         this.field_152444_d *= -1;
/*  95 */         this.field_152443_c = 0.0F;
/*     */       }
/*  97 */       else if (this.field_152443_c > 1.0F)
/*     */       {
/*  99 */         this.field_152444_d *= -1;
/* 100 */         this.field_152443_c = 1.0F;
/*     */       }
/*     */     
/*     */     } else {
/*     */       
/* 105 */       this.field_152443_c = 1.0F;
/* 106 */       this.field_152444_d = 1;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\gui\GuiStreamIndicator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */