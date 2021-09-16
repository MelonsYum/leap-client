/*     */ package net.minecraft.client.gui;
/*     */ 
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.renderer.GlStateManager;
/*     */ import net.minecraft.client.renderer.Tessellator;
/*     */ import net.minecraft.client.renderer.WorldRenderer;
/*     */ import net.minecraft.realms.RealmsSimpleScrolledSelectionList;
/*     */ import net.minecraft.util.MathHelper;
/*     */ 
/*     */ public class GuiSimpleScrolledSelectionListProxy
/*     */   extends GuiSlot
/*     */ {
/*     */   private final RealmsSimpleScrolledSelectionList field_178050_u;
/*     */   private static final String __OBFID = "CL_00001938";
/*     */   
/*     */   public GuiSimpleScrolledSelectionListProxy(RealmsSimpleScrolledSelectionList p_i45525_1_, int p_i45525_2_, int p_i45525_3_, int p_i45525_4_, int p_i45525_5_, int p_i45525_6_) {
/*  17 */     super(Minecraft.getMinecraft(), p_i45525_2_, p_i45525_3_, p_i45525_4_, p_i45525_5_, p_i45525_6_);
/*  18 */     this.field_178050_u = p_i45525_1_;
/*     */   }
/*     */ 
/*     */   
/*     */   protected int getSize() {
/*  23 */     return this.field_178050_u.getItemCount();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void elementClicked(int slotIndex, boolean isDoubleClick, int mouseX, int mouseY) {
/*  31 */     this.field_178050_u.selectItem(slotIndex, isDoubleClick, mouseX, mouseY);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean isSelected(int slotIndex) {
/*  39 */     return this.field_178050_u.isSelectedItem(slotIndex);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void drawBackground() {
/*  44 */     this.field_178050_u.renderBackground();
/*     */   }
/*     */ 
/*     */   
/*     */   protected void drawSlot(int p_180791_1_, int p_180791_2_, int p_180791_3_, int p_180791_4_, int p_180791_5_, int p_180791_6_) {
/*  49 */     this.field_178050_u.renderItem(p_180791_1_, p_180791_2_, p_180791_3_, p_180791_4_, p_180791_5_, p_180791_6_);
/*     */   }
/*     */ 
/*     */   
/*     */   public int func_178048_e() {
/*  54 */     return this.width;
/*     */   }
/*     */ 
/*     */   
/*     */   public int func_178047_f() {
/*  59 */     return this.mouseY;
/*     */   }
/*     */ 
/*     */   
/*     */   public int func_178049_g() {
/*  64 */     return this.mouseX;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected int getContentHeight() {
/*  72 */     return this.field_178050_u.getMaxPosition();
/*     */   }
/*     */ 
/*     */   
/*     */   protected int getScrollBarX() {
/*  77 */     return this.field_178050_u.getScrollbarPosition();
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_178039_p() {
/*  82 */     super.func_178039_p();
/*     */   }
/*     */ 
/*     */   
/*     */   public void drawScreen(int p_148128_1_, int p_148128_2_, float p_148128_3_) {
/*  87 */     if (this.field_178041_q) {
/*     */       
/*  89 */       this.mouseX = p_148128_1_;
/*  90 */       this.mouseY = p_148128_2_;
/*  91 */       drawBackground();
/*  92 */       int var4 = getScrollBarX();
/*  93 */       int var5 = var4 + 6;
/*  94 */       bindAmountScrolled();
/*  95 */       GlStateManager.disableLighting();
/*  96 */       GlStateManager.disableFog();
/*  97 */       Tessellator var6 = Tessellator.getInstance();
/*  98 */       WorldRenderer var7 = var6.getWorldRenderer();
/*  99 */       int var8 = this.left + this.width / 2 - getListWidth() / 2 + 2;
/* 100 */       int var9 = this.top + 4 - (int)this.amountScrolled;
/*     */       
/* 102 */       if (this.hasListHeader)
/*     */       {
/* 104 */         drawListHeader(var8, var9, var6);
/*     */       }
/*     */       
/* 107 */       drawSelectionBox(var8, var9, p_148128_1_, p_148128_2_);
/* 108 */       GlStateManager.disableDepth();
/* 109 */       boolean var10 = true;
/* 110 */       overlayBackground(0, this.top, 255, 255);
/* 111 */       overlayBackground(this.bottom, this.height, 255, 255);
/* 112 */       GlStateManager.enableBlend();
/* 113 */       GlStateManager.tryBlendFuncSeparate(770, 771, 0, 1);
/* 114 */       GlStateManager.disableAlpha();
/* 115 */       GlStateManager.shadeModel(7425);
/* 116 */       GlStateManager.func_179090_x();
/* 117 */       int var11 = func_148135_f();
/*     */       
/* 119 */       if (var11 > 0) {
/*     */         
/* 121 */         int var12 = (this.bottom - this.top) * (this.bottom - this.top) / getContentHeight();
/* 122 */         var12 = MathHelper.clamp_int(var12, 32, this.bottom - this.top - 8);
/* 123 */         int var13 = (int)this.amountScrolled * (this.bottom - this.top - var12) / var11 + this.top;
/*     */         
/* 125 */         if (var13 < this.top)
/*     */         {
/* 127 */           var13 = this.top;
/*     */         }
/*     */         
/* 130 */         var7.startDrawingQuads();
/* 131 */         var7.func_178974_a(0, 255);
/* 132 */         var7.addVertexWithUV(var4, this.bottom, 0.0D, 0.0D, 1.0D);
/* 133 */         var7.addVertexWithUV(var5, this.bottom, 0.0D, 1.0D, 1.0D);
/* 134 */         var7.addVertexWithUV(var5, this.top, 0.0D, 1.0D, 0.0D);
/* 135 */         var7.addVertexWithUV(var4, this.top, 0.0D, 0.0D, 0.0D);
/* 136 */         var6.draw();
/* 137 */         var7.startDrawingQuads();
/* 138 */         var7.func_178974_a(8421504, 255);
/* 139 */         var7.addVertexWithUV(var4, (var13 + var12), 0.0D, 0.0D, 1.0D);
/* 140 */         var7.addVertexWithUV(var5, (var13 + var12), 0.0D, 1.0D, 1.0D);
/* 141 */         var7.addVertexWithUV(var5, var13, 0.0D, 1.0D, 0.0D);
/* 142 */         var7.addVertexWithUV(var4, var13, 0.0D, 0.0D, 0.0D);
/* 143 */         var6.draw();
/* 144 */         var7.startDrawingQuads();
/* 145 */         var7.func_178974_a(12632256, 255);
/* 146 */         var7.addVertexWithUV(var4, (var13 + var12 - 1), 0.0D, 0.0D, 1.0D);
/* 147 */         var7.addVertexWithUV((var5 - 1), (var13 + var12 - 1), 0.0D, 1.0D, 1.0D);
/* 148 */         var7.addVertexWithUV((var5 - 1), var13, 0.0D, 1.0D, 0.0D);
/* 149 */         var7.addVertexWithUV(var4, var13, 0.0D, 0.0D, 0.0D);
/* 150 */         var6.draw();
/*     */       } 
/*     */       
/* 153 */       func_148142_b(p_148128_1_, p_148128_2_);
/* 154 */       GlStateManager.func_179098_w();
/* 155 */       GlStateManager.shadeModel(7424);
/* 156 */       GlStateManager.enableAlpha();
/* 157 */       GlStateManager.disableBlend();
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\gui\GuiSimpleScrolledSelectionListProxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */