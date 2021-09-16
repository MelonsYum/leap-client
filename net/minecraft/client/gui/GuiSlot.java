/*     */ package net.minecraft.client.gui;
/*     */ 
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.renderer.GlStateManager;
/*     */ import net.minecraft.client.renderer.Tessellator;
/*     */ import net.minecraft.client.renderer.WorldRenderer;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import org.lwjgl.input.Mouse;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class GuiSlot
/*     */ {
/*     */   protected final Minecraft mc;
/*     */   protected int width;
/*     */   protected int height;
/*     */   protected int top;
/*     */   protected int bottom;
/*     */   protected int right;
/*     */   protected int left;
/*     */   protected final int slotHeight;
/*     */   private int scrollUpButtonID;
/*     */   private int scrollDownButtonID;
/*     */   protected int mouseX;
/*     */   protected int mouseY;
/*     */   protected boolean field_148163_i = true;
/*  37 */   protected float initialClickY = -2.0F;
/*     */ 
/*     */ 
/*     */   
/*     */   protected float scrollMultiplier;
/*     */ 
/*     */ 
/*     */   
/*     */   protected float amountScrolled;
/*     */ 
/*     */ 
/*     */   
/*  49 */   protected int selectedElement = -1;
/*     */   
/*     */   protected long lastClicked;
/*     */   
/*     */   protected boolean field_178041_q = true;
/*     */   
/*     */   protected boolean showSelectionBox = true;
/*     */   
/*     */   protected boolean hasListHeader;
/*     */   
/*     */   protected int headerPadding;
/*     */   
/*     */   private boolean enabled = true;
/*     */   
/*     */   private static final String __OBFID = "CL_00000679";
/*     */   
/*     */   public GuiSlot(Minecraft mcIn, int width, int height, int p_i1052_4_, int p_i1052_5_, int p_i1052_6_) {
/*  66 */     this.mc = mcIn;
/*  67 */     this.width = width;
/*  68 */     this.height = height;
/*  69 */     this.top = p_i1052_4_;
/*  70 */     this.bottom = p_i1052_5_;
/*  71 */     this.slotHeight = p_i1052_6_;
/*  72 */     this.left = 0;
/*  73 */     this.right = width;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDimensions(int p_148122_1_, int p_148122_2_, int p_148122_3_, int p_148122_4_) {
/*  78 */     this.width = p_148122_1_;
/*  79 */     this.height = p_148122_2_;
/*  80 */     this.top = p_148122_3_;
/*  81 */     this.bottom = p_148122_4_;
/*  82 */     this.left = 0;
/*  83 */     this.right = p_148122_1_;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setShowSelectionBox(boolean p_148130_1_) {
/*  88 */     this.showSelectionBox = p_148130_1_;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void setHasListHeader(boolean p_148133_1_, int p_148133_2_) {
/*  97 */     this.hasListHeader = p_148133_1_;
/*  98 */     this.headerPadding = p_148133_2_;
/*     */     
/* 100 */     if (!p_148133_1_)
/*     */     {
/* 102 */       this.headerPadding = 0;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected abstract int getSize();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected abstract void elementClicked(int paramInt1, boolean paramBoolean, int paramInt2, int paramInt3);
/*     */ 
/*     */ 
/*     */   
/*     */   protected abstract boolean isSelected(int paramInt);
/*     */ 
/*     */ 
/*     */   
/*     */   protected int getContentHeight() {
/* 123 */     return getSize() * this.slotHeight + this.headerPadding;
/*     */   }
/*     */ 
/*     */   
/*     */   protected abstract void drawBackground();
/*     */ 
/*     */   
/*     */   protected void func_178040_a(int p_178040_1_, int p_178040_2_, int p_178040_3_) {}
/*     */ 
/*     */   
/*     */   protected abstract void drawSlot(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6);
/*     */ 
/*     */   
/*     */   protected void drawListHeader(int p_148129_1_, int p_148129_2_, Tessellator p_148129_3_) {}
/*     */   
/*     */   protected void func_148132_a(int p_148132_1_, int p_148132_2_) {}
/*     */   
/*     */   protected void func_148142_b(int p_148142_1_, int p_148142_2_) {}
/*     */   
/*     */   public int getSlotIndexFromScreenCoords(int p_148124_1_, int p_148124_2_) {
/* 143 */     int var3 = this.left + this.width / 2 - getListWidth() / 2;
/* 144 */     int var4 = this.left + this.width / 2 + getListWidth() / 2;
/* 145 */     int var5 = p_148124_2_ - this.top - this.headerPadding + (int)this.amountScrolled - 4;
/* 146 */     int var6 = var5 / this.slotHeight;
/* 147 */     return (p_148124_1_ < getScrollBarX() && p_148124_1_ >= var3 && p_148124_1_ <= var4 && var6 >= 0 && var5 >= 0 && var6 < getSize()) ? var6 : -1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void registerScrollButtons(int p_148134_1_, int p_148134_2_) {
/* 155 */     this.scrollUpButtonID = p_148134_1_;
/* 156 */     this.scrollDownButtonID = p_148134_2_;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void bindAmountScrolled() {
/* 164 */     int var1 = func_148135_f();
/*     */     
/* 166 */     if (var1 < 0)
/*     */     {
/* 168 */       var1 /= 2;
/*     */     }
/*     */     
/* 171 */     if (!this.field_148163_i && var1 < 0)
/*     */     {
/* 173 */       var1 = 0;
/*     */     }
/*     */     
/* 176 */     this.amountScrolled = MathHelper.clamp_float(this.amountScrolled, 0.0F, var1);
/*     */   }
/*     */ 
/*     */   
/*     */   public int func_148135_f() {
/* 181 */     return Math.max(0, getContentHeight() - this.bottom - this.top - 4);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getAmountScrolled() {
/* 189 */     return (int)this.amountScrolled;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isMouseYWithinSlotBounds(int p_148141_1_) {
/* 194 */     return (p_148141_1_ >= this.top && p_148141_1_ <= this.bottom && this.mouseX >= this.left && this.mouseX <= this.right);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void scrollBy(int p_148145_1_) {
/* 202 */     this.amountScrolled += p_148145_1_;
/* 203 */     bindAmountScrolled();
/* 204 */     this.initialClickY = -2.0F;
/*     */   }
/*     */ 
/*     */   
/*     */   public void actionPerformed(GuiButton p_148147_1_) {
/* 209 */     if (p_148147_1_.enabled)
/*     */     {
/* 211 */       if (p_148147_1_.id == this.scrollUpButtonID) {
/*     */         
/* 213 */         this.amountScrolled -= (this.slotHeight * 2 / 3);
/* 214 */         this.initialClickY = -2.0F;
/* 215 */         bindAmountScrolled();
/*     */       }
/* 217 */       else if (p_148147_1_.id == this.scrollDownButtonID) {
/*     */         
/* 219 */         this.amountScrolled += (this.slotHeight * 2 / 3);
/* 220 */         this.initialClickY = -2.0F;
/* 221 */         bindAmountScrolled();
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void drawScreen(int p_148128_1_, int p_148128_2_, float p_148128_3_) {
/* 228 */     if (this.field_178041_q) {
/*     */       
/* 230 */       this.mouseX = p_148128_1_;
/* 231 */       this.mouseY = p_148128_2_;
/* 232 */       drawBackground();
/* 233 */       int var4 = getScrollBarX();
/* 234 */       int var5 = var4 + 6;
/* 235 */       bindAmountScrolled();
/* 236 */       GlStateManager.disableLighting();
/* 237 */       GlStateManager.disableFog();
/* 238 */       Tessellator var6 = Tessellator.getInstance();
/* 239 */       WorldRenderer var7 = var6.getWorldRenderer();
/* 240 */       this.mc.getTextureManager().bindTexture(Gui.optionsBackground);
/* 241 */       GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
/* 242 */       float var8 = 32.0F;
/* 243 */       var7.startDrawingQuads();
/* 244 */       var7.func_178991_c(2105376);
/* 245 */       var7.addVertexWithUV(this.left, this.bottom, 0.0D, (this.left / var8), ((this.bottom + (int)this.amountScrolled) / var8));
/* 246 */       var7.addVertexWithUV(this.right, this.bottom, 0.0D, (this.right / var8), ((this.bottom + (int)this.amountScrolled) / var8));
/* 247 */       var7.addVertexWithUV(this.right, this.top, 0.0D, (this.right / var8), ((this.top + (int)this.amountScrolled) / var8));
/* 248 */       var7.addVertexWithUV(this.left, this.top, 0.0D, (this.left / var8), ((this.top + (int)this.amountScrolled) / var8));
/* 249 */       var6.draw();
/* 250 */       int var9 = this.left + this.width / 2 - getListWidth() / 2 + 2;
/* 251 */       int var10 = this.top + 4 - (int)this.amountScrolled;
/*     */       
/* 253 */       if (this.hasListHeader)
/*     */       {
/* 255 */         drawListHeader(var9, var10, var6);
/*     */       }
/*     */       
/* 258 */       drawSelectionBox(var9, var10, p_148128_1_, p_148128_2_);
/* 259 */       GlStateManager.disableDepth();
/* 260 */       byte var11 = 4;
/* 261 */       overlayBackground(0, this.top, 255, 255);
/* 262 */       overlayBackground(this.bottom, this.height, 255, 255);
/* 263 */       GlStateManager.enableBlend();
/* 264 */       GlStateManager.tryBlendFuncSeparate(770, 771, 0, 1);
/* 265 */       GlStateManager.disableAlpha();
/* 266 */       GlStateManager.shadeModel(7425);
/* 267 */       GlStateManager.func_179090_x();
/* 268 */       var7.startDrawingQuads();
/* 269 */       var7.func_178974_a(0, 0);
/* 270 */       var7.addVertexWithUV(this.left, (this.top + var11), 0.0D, 0.0D, 1.0D);
/* 271 */       var7.addVertexWithUV(this.right, (this.top + var11), 0.0D, 1.0D, 1.0D);
/* 272 */       var7.func_178974_a(0, 255);
/* 273 */       var7.addVertexWithUV(this.right, this.top, 0.0D, 1.0D, 0.0D);
/* 274 */       var7.addVertexWithUV(this.left, this.top, 0.0D, 0.0D, 0.0D);
/* 275 */       var6.draw();
/* 276 */       var7.startDrawingQuads();
/* 277 */       var7.func_178974_a(0, 255);
/* 278 */       var7.addVertexWithUV(this.left, this.bottom, 0.0D, 0.0D, 1.0D);
/* 279 */       var7.addVertexWithUV(this.right, this.bottom, 0.0D, 1.0D, 1.0D);
/* 280 */       var7.func_178974_a(0, 0);
/* 281 */       var7.addVertexWithUV(this.right, (this.bottom - var11), 0.0D, 1.0D, 0.0D);
/* 282 */       var7.addVertexWithUV(this.left, (this.bottom - var11), 0.0D, 0.0D, 0.0D);
/* 283 */       var6.draw();
/* 284 */       int var12 = func_148135_f();
/*     */       
/* 286 */       if (var12 > 0) {
/*     */         
/* 288 */         int var13 = (this.bottom - this.top) * (this.bottom - this.top) / getContentHeight();
/* 289 */         var13 = MathHelper.clamp_int(var13, 32, this.bottom - this.top - 8);
/* 290 */         int var14 = (int)this.amountScrolled * (this.bottom - this.top - var13) / var12 + this.top;
/*     */         
/* 292 */         if (var14 < this.top)
/*     */         {
/* 294 */           var14 = this.top;
/*     */         }
/*     */         
/* 297 */         var7.startDrawingQuads();
/* 298 */         var7.func_178974_a(0, 255);
/* 299 */         var7.addVertexWithUV(var4, this.bottom, 0.0D, 0.0D, 1.0D);
/* 300 */         var7.addVertexWithUV(var5, this.bottom, 0.0D, 1.0D, 1.0D);
/* 301 */         var7.addVertexWithUV(var5, this.top, 0.0D, 1.0D, 0.0D);
/* 302 */         var7.addVertexWithUV(var4, this.top, 0.0D, 0.0D, 0.0D);
/* 303 */         var6.draw();
/* 304 */         var7.startDrawingQuads();
/* 305 */         var7.func_178974_a(8421504, 255);
/* 306 */         var7.addVertexWithUV(var4, (var14 + var13), 0.0D, 0.0D, 1.0D);
/* 307 */         var7.addVertexWithUV(var5, (var14 + var13), 0.0D, 1.0D, 1.0D);
/* 308 */         var7.addVertexWithUV(var5, var14, 0.0D, 1.0D, 0.0D);
/* 309 */         var7.addVertexWithUV(var4, var14, 0.0D, 0.0D, 0.0D);
/* 310 */         var6.draw();
/* 311 */         var7.startDrawingQuads();
/* 312 */         var7.func_178974_a(12632256, 255);
/* 313 */         var7.addVertexWithUV(var4, (var14 + var13 - 1), 0.0D, 0.0D, 1.0D);
/* 314 */         var7.addVertexWithUV((var5 - 1), (var14 + var13 - 1), 0.0D, 1.0D, 1.0D);
/* 315 */         var7.addVertexWithUV((var5 - 1), var14, 0.0D, 1.0D, 0.0D);
/* 316 */         var7.addVertexWithUV(var4, var14, 0.0D, 0.0D, 0.0D);
/* 317 */         var6.draw();
/*     */       } 
/*     */       
/* 320 */       func_148142_b(p_148128_1_, p_148128_2_);
/* 321 */       GlStateManager.func_179098_w();
/* 322 */       GlStateManager.shadeModel(7424);
/* 323 */       GlStateManager.enableAlpha();
/* 324 */       GlStateManager.disableBlend();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_178039_p() {
/* 330 */     if (isMouseYWithinSlotBounds(this.mouseY)) {
/*     */       
/* 332 */       if (Mouse.isButtonDown(0) && getEnabled()) {
/*     */         
/* 334 */         if (this.initialClickY == -1.0F) {
/*     */           
/* 336 */           boolean var1 = true;
/*     */           
/* 338 */           if (this.mouseY >= this.top && this.mouseY <= this.bottom) {
/*     */             
/* 340 */             int var2 = this.width / 2 - getListWidth() / 2;
/* 341 */             int var3 = this.width / 2 + getListWidth() / 2;
/* 342 */             int var4 = this.mouseY - this.top - this.headerPadding + (int)this.amountScrolled - 4;
/* 343 */             int var5 = var4 / this.slotHeight;
/*     */             
/* 345 */             if (this.mouseX >= var2 && this.mouseX <= var3 && var5 >= 0 && var4 >= 0 && var5 < getSize()) {
/*     */               
/* 347 */               boolean var6 = (var5 == this.selectedElement && Minecraft.getSystemTime() - this.lastClicked < 250L);
/* 348 */               elementClicked(var5, var6, this.mouseX, this.mouseY);
/* 349 */               this.selectedElement = var5;
/* 350 */               this.lastClicked = Minecraft.getSystemTime();
/*     */             }
/* 352 */             else if (this.mouseX >= var2 && this.mouseX <= var3 && var4 < 0) {
/*     */               
/* 354 */               func_148132_a(this.mouseX - var2, this.mouseY - this.top + (int)this.amountScrolled - 4);
/* 355 */               var1 = false;
/*     */             } 
/*     */             
/* 358 */             int var11 = getScrollBarX();
/* 359 */             int var7 = var11 + 6;
/*     */             
/* 361 */             if (this.mouseX >= var11 && this.mouseX <= var7) {
/*     */               
/* 363 */               this.scrollMultiplier = -1.0F;
/* 364 */               int var8 = func_148135_f();
/*     */               
/* 366 */               if (var8 < 1)
/*     */               {
/* 368 */                 var8 = 1;
/*     */               }
/*     */               
/* 371 */               int var9 = (int)(((this.bottom - this.top) * (this.bottom - this.top)) / getContentHeight());
/* 372 */               var9 = MathHelper.clamp_int(var9, 32, this.bottom - this.top - 8);
/* 373 */               this.scrollMultiplier /= (this.bottom - this.top - var9) / var8;
/*     */             }
/*     */             else {
/*     */               
/* 377 */               this.scrollMultiplier = 1.0F;
/*     */             } 
/*     */             
/* 380 */             if (var1)
/*     */             {
/* 382 */               this.initialClickY = this.mouseY;
/*     */             }
/*     */             else
/*     */             {
/* 386 */               this.initialClickY = -2.0F;
/*     */             }
/*     */           
/*     */           } else {
/*     */             
/* 391 */             this.initialClickY = -2.0F;
/*     */           }
/*     */         
/* 394 */         } else if (this.initialClickY >= 0.0F) {
/*     */           
/* 396 */           this.amountScrolled -= (this.mouseY - this.initialClickY) * this.scrollMultiplier;
/* 397 */           this.initialClickY = this.mouseY;
/*     */         }
/*     */       
/*     */       } else {
/*     */         
/* 402 */         this.initialClickY = -1.0F;
/*     */       } 
/*     */       
/* 405 */       int var10 = Mouse.getEventDWheel();
/*     */       
/* 407 */       if (var10 != 0) {
/*     */         
/* 409 */         if (var10 > 0) {
/*     */           
/* 411 */           var10 = -1;
/*     */         }
/* 413 */         else if (var10 < 0) {
/*     */           
/* 415 */           var10 = 1;
/*     */         } 
/*     */         
/* 418 */         this.amountScrolled += (var10 * this.slotHeight / 2);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void setEnabled(boolean p_148143_1_) {
/* 425 */     this.enabled = p_148143_1_;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean getEnabled() {
/* 430 */     return this.enabled;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getListWidth() {
/* 438 */     return 220;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void drawSelectionBox(int p_148120_1_, int p_148120_2_, int p_148120_3_, int p_148120_4_) {
/* 446 */     int var5 = getSize();
/* 447 */     Tessellator var6 = Tessellator.getInstance();
/* 448 */     WorldRenderer var7 = var6.getWorldRenderer();
/*     */     
/* 450 */     for (int var8 = 0; var8 < var5; var8++) {
/*     */       
/* 452 */       int var9 = p_148120_2_ + var8 * this.slotHeight + this.headerPadding;
/* 453 */       int var10 = this.slotHeight - 4;
/*     */       
/* 455 */       if (var9 > this.bottom || var9 + var10 < this.top)
/*     */       {
/* 457 */         func_178040_a(var8, p_148120_1_, var9);
/*     */       }
/*     */       
/* 460 */       if (this.showSelectionBox && isSelected(var8)) {
/*     */         
/* 462 */         int var11 = this.left + this.width / 2 - getListWidth() / 2;
/* 463 */         int var12 = this.left + this.width / 2 + getListWidth() / 2;
/* 464 */         GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
/* 465 */         GlStateManager.func_179090_x();
/* 466 */         var7.startDrawingQuads();
/* 467 */         var7.func_178991_c(8421504);
/* 468 */         var7.addVertexWithUV(var11, (var9 + var10 + 2), 0.0D, 0.0D, 1.0D);
/* 469 */         var7.addVertexWithUV(var12, (var9 + var10 + 2), 0.0D, 1.0D, 1.0D);
/* 470 */         var7.addVertexWithUV(var12, (var9 - 2), 0.0D, 1.0D, 0.0D);
/* 471 */         var7.addVertexWithUV(var11, (var9 - 2), 0.0D, 0.0D, 0.0D);
/* 472 */         var7.func_178991_c(0);
/* 473 */         var7.addVertexWithUV((var11 + 1), (var9 + var10 + 1), 0.0D, 0.0D, 1.0D);
/* 474 */         var7.addVertexWithUV((var12 - 1), (var9 + var10 + 1), 0.0D, 1.0D, 1.0D);
/* 475 */         var7.addVertexWithUV((var12 - 1), (var9 - 1), 0.0D, 1.0D, 0.0D);
/* 476 */         var7.addVertexWithUV((var11 + 1), (var9 - 1), 0.0D, 0.0D, 0.0D);
/* 477 */         var6.draw();
/* 478 */         GlStateManager.func_179098_w();
/*     */       } 
/*     */       
/* 481 */       drawSlot(var8, p_148120_1_, var9, var10, p_148120_3_, p_148120_4_);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected int getScrollBarX() {
/* 487 */     return this.width / 2 + 124;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void overlayBackground(int p_148136_1_, int p_148136_2_, int p_148136_3_, int p_148136_4_) {
/* 495 */     Tessellator var5 = Tessellator.getInstance();
/* 496 */     WorldRenderer var6 = var5.getWorldRenderer();
/* 497 */     this.mc.getTextureManager().bindTexture(Gui.optionsBackground);
/* 498 */     GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
/* 499 */     float var7 = 32.0F;
/* 500 */     var6.startDrawingQuads();
/* 501 */     var6.func_178974_a(4210752, p_148136_4_);
/* 502 */     var6.addVertexWithUV(this.left, p_148136_2_, 0.0D, 0.0D, (p_148136_2_ / var7));
/* 503 */     var6.addVertexWithUV((this.left + this.width), p_148136_2_, 0.0D, (this.width / var7), (p_148136_2_ / var7));
/* 504 */     var6.func_178974_a(4210752, p_148136_3_);
/* 505 */     var6.addVertexWithUV((this.left + this.width), p_148136_1_, 0.0D, (this.width / var7), (p_148136_1_ / var7));
/* 506 */     var6.addVertexWithUV(this.left, p_148136_1_, 0.0D, 0.0D, (p_148136_1_ / var7));
/* 507 */     var5.draw();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSlotXBoundsFromLeft(int p_148140_1_) {
/* 515 */     this.left = p_148140_1_;
/* 516 */     this.right = p_148140_1_ + this.width;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getSlotHeight() {
/* 521 */     return this.slotHeight;
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\gui\GuiSlot.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */