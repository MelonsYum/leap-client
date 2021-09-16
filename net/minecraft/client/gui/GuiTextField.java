/*     */ package net.minecraft.client.gui;
/*     */ 
/*     */ import com.google.common.base.Predicate;
/*     */ import com.google.common.base.Predicates;
/*     */ import net.minecraft.client.renderer.GlStateManager;
/*     */ import net.minecraft.client.renderer.Tessellator;
/*     */ import net.minecraft.client.renderer.WorldRenderer;
/*     */ import net.minecraft.util.ChatAllowedCharacters;
/*     */ import net.minecraft.util.MathHelper;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class GuiTextField
/*     */   extends Gui
/*     */ {
/*     */   private final int field_175208_g;
/*     */   private final FontRenderer fontRendererInstance;
/*     */   public int xPosition;
/*     */   public int yPosition;
/*     */   private final int width;
/*     */   private final int height;
/*  23 */   private String text = "";
/*  24 */   private int maxStringLength = 32;
/*     */ 
/*     */   
/*     */   private int cursorCounter;
/*     */ 
/*     */   
/*     */   private boolean enableBackgroundDrawing = true;
/*     */ 
/*     */   
/*     */   private boolean canLoseFocus = true;
/*     */ 
/*     */   
/*     */   private boolean isFocused;
/*     */ 
/*     */   
/*     */   private boolean isEnabled = true;
/*     */ 
/*     */   
/*     */   private int lineScrollOffset;
/*     */ 
/*     */   
/*     */   private int cursorPosition;
/*     */ 
/*     */   
/*     */   private int selectionEnd;
/*     */ 
/*     */   
/*  51 */   private int enabledColor = 14737632;
/*  52 */   private int disabledColor = 7368816;
/*     */   
/*     */   private boolean visible = true;
/*     */   
/*     */   private GuiPageButtonList.GuiResponder field_175210_x;
/*  57 */   private Predicate field_175209_y = Predicates.alwaysTrue();
/*     */   
/*     */   private static final String __OBFID = "CL_00000670";
/*     */   
/*     */   public GuiTextField(int p_i45542_1_, FontRenderer p_i45542_2_, int p_i45542_3_, int p_i45542_4_, int p_i45542_5_, int p_i45542_6_) {
/*  62 */     this.field_175208_g = p_i45542_1_;
/*  63 */     this.fontRendererInstance = p_i45542_2_;
/*  64 */     this.xPosition = p_i45542_3_;
/*  65 */     this.yPosition = p_i45542_4_;
/*  66 */     this.width = p_i45542_5_;
/*  67 */     this.height = p_i45542_6_;
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_175207_a(GuiPageButtonList.GuiResponder p_175207_1_) {
/*  72 */     this.field_175210_x = p_175207_1_;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void updateCursorCounter() {
/*  80 */     this.cursorCounter++;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setText(String p_146180_1_) {
/*  88 */     if (this.field_175209_y.apply(p_146180_1_)) {
/*     */       
/*  90 */       if (p_146180_1_.length() > this.maxStringLength) {
/*     */         
/*  92 */         this.text = p_146180_1_.substring(0, this.maxStringLength);
/*     */       }
/*     */       else {
/*     */         
/*  96 */         this.text = p_146180_1_;
/*     */       } 
/*     */       
/*  99 */       setCursorPositionEnd();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getText() {
/* 108 */     return this.text;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getSelectedText() {
/* 116 */     int var1 = (this.cursorPosition < this.selectionEnd) ? this.cursorPosition : this.selectionEnd;
/* 117 */     int var2 = (this.cursorPosition < this.selectionEnd) ? this.selectionEnd : this.cursorPosition;
/* 118 */     return this.text.substring(var1, var2);
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_175205_a(Predicate p_175205_1_) {
/* 123 */     this.field_175209_y = p_175205_1_;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void writeText(String p_146191_1_) {
/*     */     int var8;
/* 131 */     String var2 = "";
/* 132 */     String var3 = ChatAllowedCharacters.filterAllowedCharacters(p_146191_1_);
/* 133 */     int var4 = (this.cursorPosition < this.selectionEnd) ? this.cursorPosition : this.selectionEnd;
/* 134 */     int var5 = (this.cursorPosition < this.selectionEnd) ? this.selectionEnd : this.cursorPosition;
/* 135 */     int var6 = this.maxStringLength - this.text.length() - var4 - var5;
/* 136 */     boolean var7 = false;
/*     */     
/* 138 */     if (this.text.length() > 0)
/*     */     {
/* 140 */       var2 = String.valueOf(var2) + this.text.substring(0, var4);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 145 */     if (var6 < var3.length()) {
/*     */       
/* 147 */       var2 = String.valueOf(var2) + var3.substring(0, var6);
/* 148 */       var8 = var6;
/*     */     }
/*     */     else {
/*     */       
/* 152 */       var2 = String.valueOf(var2) + var3;
/* 153 */       var8 = var3.length();
/*     */     } 
/*     */     
/* 156 */     if (this.text.length() > 0 && var5 < this.text.length())
/*     */     {
/* 158 */       var2 = String.valueOf(var2) + this.text.substring(var5);
/*     */     }
/*     */     
/* 161 */     if (this.field_175209_y.apply(var2)) {
/*     */       
/* 163 */       this.text = var2;
/* 164 */       moveCursorBy(var4 - this.selectionEnd + var8);
/*     */       
/* 166 */       if (this.field_175210_x != null)
/*     */       {
/* 168 */         this.field_175210_x.func_175319_a(this.field_175208_g, this.text);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void deleteWords(int p_146177_1_) {
/* 179 */     if (this.text.length() != 0)
/*     */     {
/* 181 */       if (this.selectionEnd != this.cursorPosition) {
/*     */         
/* 183 */         writeText("");
/*     */       }
/*     */       else {
/*     */         
/* 187 */         deleteFromCursor(getNthWordFromCursor(p_146177_1_) - this.cursorPosition);
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void deleteFromCursor(int p_146175_1_) {
/* 197 */     if (this.text.length() != 0)
/*     */     {
/* 199 */       if (this.selectionEnd != this.cursorPosition) {
/*     */         
/* 201 */         writeText("");
/*     */       }
/*     */       else {
/*     */         
/* 205 */         boolean var2 = (p_146175_1_ < 0);
/* 206 */         int var3 = var2 ? (this.cursorPosition + p_146175_1_) : this.cursorPosition;
/* 207 */         int var4 = var2 ? this.cursorPosition : (this.cursorPosition + p_146175_1_);
/* 208 */         String var5 = "";
/*     */         
/* 210 */         if (var3 >= 0)
/*     */         {
/* 212 */           var5 = this.text.substring(0, var3);
/*     */         }
/*     */         
/* 215 */         if (var4 < this.text.length())
/*     */         {
/* 217 */           var5 = String.valueOf(var5) + this.text.substring(var4);
/*     */         }
/*     */         
/* 220 */         this.text = var5;
/*     */         
/* 222 */         if (var2)
/*     */         {
/* 224 */           moveCursorBy(p_146175_1_);
/*     */         }
/*     */         
/* 227 */         if (this.field_175210_x != null)
/*     */         {
/* 229 */           this.field_175210_x.func_175319_a(this.field_175208_g, this.text);
/*     */         }
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public int func_175206_d() {
/* 237 */     return this.field_175208_g;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getNthWordFromCursor(int p_146187_1_) {
/* 245 */     return getNthWordFromPos(p_146187_1_, getCursorPosition());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getNthWordFromPos(int p_146183_1_, int p_146183_2_) {
/* 253 */     return func_146197_a(p_146183_1_, p_146183_2_, true);
/*     */   }
/*     */ 
/*     */   
/*     */   public int func_146197_a(int p_146197_1_, int p_146197_2_, boolean p_146197_3_) {
/* 258 */     int var4 = p_146197_2_;
/* 259 */     boolean var5 = (p_146197_1_ < 0);
/* 260 */     int var6 = Math.abs(p_146197_1_);
/*     */     
/* 262 */     for (int var7 = 0; var7 < var6; var7++) {
/*     */       
/* 264 */       if (var5) {
/*     */         
/* 266 */         while (p_146197_3_ && var4 > 0 && this.text.charAt(var4 - 1) == ' ')
/*     */         {
/* 268 */           var4--;
/*     */         }
/*     */         
/* 271 */         while (var4 > 0 && this.text.charAt(var4 - 1) != ' ')
/*     */         {
/* 273 */           var4--;
/*     */         }
/*     */       }
/*     */       else {
/*     */         
/* 278 */         int var8 = this.text.length();
/* 279 */         var4 = this.text.indexOf(' ', var4);
/*     */         
/* 281 */         if (var4 == -1) {
/*     */           
/* 283 */           var4 = var8;
/*     */         }
/*     */         else {
/*     */           
/* 287 */           while (p_146197_3_ && var4 < var8 && this.text.charAt(var4) == ' ')
/*     */           {
/* 289 */             var4++;
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 295 */     return var4;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void moveCursorBy(int p_146182_1_) {
/* 303 */     setCursorPosition(this.selectionEnd + p_146182_1_);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setCursorPosition(int p_146190_1_) {
/* 311 */     this.cursorPosition = p_146190_1_;
/* 312 */     int var2 = this.text.length();
/* 313 */     this.cursorPosition = MathHelper.clamp_int(this.cursorPosition, 0, var2);
/* 314 */     setSelectionPos(this.cursorPosition);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setCursorPositionZero() {
/* 322 */     setCursorPosition(0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setCursorPositionEnd() {
/* 330 */     setCursorPosition(this.text.length());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean textboxKeyTyped(char p_146201_1_, int p_146201_2_) {
/* 338 */     if (!this.isFocused)
/*     */     {
/* 340 */       return false;
/*     */     }
/* 342 */     if (GuiScreen.func_175278_g(p_146201_2_)) {
/*     */       
/* 344 */       setCursorPositionEnd();
/* 345 */       setSelectionPos(0);
/* 346 */       return true;
/*     */     } 
/* 348 */     if (GuiScreen.func_175280_f(p_146201_2_)) {
/*     */       
/* 350 */       GuiScreen.setClipboardString(getSelectedText());
/* 351 */       return true;
/*     */     } 
/* 353 */     if (GuiScreen.func_175279_e(p_146201_2_)) {
/*     */       
/* 355 */       if (this.isEnabled)
/*     */       {
/* 357 */         writeText(GuiScreen.getClipboardString());
/*     */       }
/*     */       
/* 360 */       return true;
/*     */     } 
/* 362 */     if (GuiScreen.func_175277_d(p_146201_2_)) {
/*     */       
/* 364 */       GuiScreen.setClipboardString(getSelectedText());
/*     */       
/* 366 */       if (this.isEnabled)
/*     */       {
/* 368 */         writeText("");
/*     */       }
/*     */       
/* 371 */       return true;
/*     */     } 
/*     */ 
/*     */     
/* 375 */     switch (p_146201_2_) {
/*     */       
/*     */       case 14:
/* 378 */         if (GuiScreen.isCtrlKeyDown()) {
/*     */           
/* 380 */           if (this.isEnabled)
/*     */           {
/* 382 */             deleteWords(-1);
/*     */           }
/*     */         }
/* 385 */         else if (this.isEnabled) {
/*     */           
/* 387 */           deleteFromCursor(-1);
/*     */         } 
/*     */         
/* 390 */         return true;
/*     */       
/*     */       case 199:
/* 393 */         if (GuiScreen.isShiftKeyDown()) {
/*     */           
/* 395 */           setSelectionPos(0);
/*     */         }
/*     */         else {
/*     */           
/* 399 */           setCursorPositionZero();
/*     */         } 
/*     */         
/* 402 */         return true;
/*     */       
/*     */       case 203:
/* 405 */         if (GuiScreen.isShiftKeyDown()) {
/*     */           
/* 407 */           if (GuiScreen.isCtrlKeyDown())
/*     */           {
/* 409 */             setSelectionPos(getNthWordFromPos(-1, getSelectionEnd()));
/*     */           }
/*     */           else
/*     */           {
/* 413 */             setSelectionPos(getSelectionEnd() - 1);
/*     */           }
/*     */         
/* 416 */         } else if (GuiScreen.isCtrlKeyDown()) {
/*     */           
/* 418 */           setCursorPosition(getNthWordFromCursor(-1));
/*     */         }
/*     */         else {
/*     */           
/* 422 */           moveCursorBy(-1);
/*     */         } 
/*     */         
/* 425 */         return true;
/*     */       
/*     */       case 205:
/* 428 */         if (GuiScreen.isShiftKeyDown()) {
/*     */           
/* 430 */           if (GuiScreen.isCtrlKeyDown())
/*     */           {
/* 432 */             setSelectionPos(getNthWordFromPos(1, getSelectionEnd()));
/*     */           }
/*     */           else
/*     */           {
/* 436 */             setSelectionPos(getSelectionEnd() + 1);
/*     */           }
/*     */         
/* 439 */         } else if (GuiScreen.isCtrlKeyDown()) {
/*     */           
/* 441 */           setCursorPosition(getNthWordFromCursor(1));
/*     */         }
/*     */         else {
/*     */           
/* 445 */           moveCursorBy(1);
/*     */         } 
/*     */         
/* 448 */         return true;
/*     */       
/*     */       case 207:
/* 451 */         if (GuiScreen.isShiftKeyDown()) {
/*     */           
/* 453 */           setSelectionPos(this.text.length());
/*     */         }
/*     */         else {
/*     */           
/* 457 */           setCursorPositionEnd();
/*     */         } 
/*     */         
/* 460 */         return true;
/*     */       
/*     */       case 211:
/* 463 */         if (GuiScreen.isCtrlKeyDown()) {
/*     */           
/* 465 */           if (this.isEnabled)
/*     */           {
/* 467 */             deleteWords(1);
/*     */           }
/*     */         }
/* 470 */         else if (this.isEnabled) {
/*     */           
/* 472 */           deleteFromCursor(1);
/*     */         } 
/*     */         
/* 475 */         return true;
/*     */     } 
/*     */     
/* 478 */     if (ChatAllowedCharacters.isAllowedCharacter(p_146201_1_)) {
/*     */       
/* 480 */       if (this.isEnabled)
/*     */       {
/* 482 */         writeText(Character.toString(p_146201_1_));
/*     */       }
/*     */       
/* 485 */       return true;
/*     */     } 
/*     */ 
/*     */     
/* 489 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void mouseClicked(int p_146192_1_, int p_146192_2_, int p_146192_3_) {
/* 500 */     boolean var4 = (p_146192_1_ >= this.xPosition && p_146192_1_ < this.xPosition + this.width && p_146192_2_ >= this.yPosition && p_146192_2_ < this.yPosition + this.height);
/*     */     
/* 502 */     if (this.canLoseFocus)
/*     */     {
/* 504 */       setFocused(var4);
/*     */     }
/*     */     
/* 507 */     if (this.isFocused && var4 && p_146192_3_ == 0) {
/*     */       
/* 509 */       int var5 = p_146192_1_ - this.xPosition;
/*     */       
/* 511 */       if (this.enableBackgroundDrawing)
/*     */       {
/* 513 */         var5 -= 4;
/*     */       }
/*     */       
/* 516 */       String var6 = this.fontRendererInstance.trimStringToWidth(this.text.substring(this.lineScrollOffset), getWidth());
/* 517 */       setCursorPosition(this.fontRendererInstance.trimStringToWidth(var6, var5).length() + this.lineScrollOffset);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void drawTextBox() {
/* 526 */     if (getVisible()) {
/*     */       
/* 528 */       if (getEnableBackgroundDrawing()) {
/*     */         
/* 530 */         drawRect((this.xPosition - 1), (this.yPosition - 1), (this.xPosition + this.width + 1), (this.yPosition + this.height + 1), -6250336);
/* 531 */         drawRect(this.xPosition, this.yPosition, (this.xPosition + this.width), (this.yPosition + this.height), -16777216);
/*     */       } 
/*     */       
/* 534 */       int var1 = this.isEnabled ? this.enabledColor : this.disabledColor;
/* 535 */       int var2 = this.cursorPosition - this.lineScrollOffset;
/* 536 */       int var3 = this.selectionEnd - this.lineScrollOffset;
/* 537 */       String var4 = this.fontRendererInstance.trimStringToWidth(this.text.substring(this.lineScrollOffset), getWidth());
/* 538 */       boolean var5 = (var2 >= 0 && var2 <= var4.length());
/* 539 */       boolean var6 = (this.isFocused && this.cursorCounter / 6 % 2 == 0 && var5);
/* 540 */       int var7 = this.enableBackgroundDrawing ? (this.xPosition + 4) : this.xPosition;
/* 541 */       int var8 = this.enableBackgroundDrawing ? (this.yPosition + (this.height - 8) / 2) : this.yPosition;
/* 542 */       int var9 = var7;
/*     */       
/* 544 */       if (var3 > var4.length())
/*     */       {
/* 546 */         var3 = var4.length();
/*     */       }
/*     */       
/* 549 */       if (var4.length() > 0) {
/*     */         
/* 551 */         String var10 = var5 ? var4.substring(0, var2) : var4;
/* 552 */         var9 = this.fontRendererInstance.drawStringWithShadow(var10, var7, var8, var1);
/*     */       } 
/*     */       
/* 555 */       boolean var13 = !(this.cursorPosition >= this.text.length() && this.text.length() < getMaxStringLength());
/* 556 */       int var11 = var9;
/*     */       
/* 558 */       if (!var5) {
/*     */         
/* 560 */         var11 = (var2 > 0) ? (var7 + this.width) : var7;
/*     */       }
/* 562 */       else if (var13) {
/*     */         
/* 564 */         var11 = var9 - 1;
/* 565 */         var9--;
/*     */       } 
/*     */       
/* 568 */       if (var4.length() > 0 && var5 && var2 < var4.length())
/*     */       {
/* 570 */         var9 = this.fontRendererInstance.drawStringWithShadow(var4.substring(var2), var9, var8, var1);
/*     */       }
/*     */       
/* 573 */       if (var6)
/*     */       {
/* 575 */         if (var13) {
/*     */           
/* 577 */           Gui.drawRect(var11, (var8 - 1), (var11 + 1), (var8 + 1 + this.fontRendererInstance.FONT_HEIGHT), -3092272);
/*     */         }
/*     */         else {
/*     */           
/* 581 */           this.fontRendererInstance.drawStringWithShadow("_", var11, var8, var1);
/*     */         } 
/*     */       }
/*     */       
/* 585 */       if (var3 != var2) {
/*     */         
/* 587 */         int var12 = var7 + this.fontRendererInstance.getStringWidth(var4.substring(0, var3));
/* 588 */         drawCursorVertical(var11, var8 - 1, var12 - 1, var8 + 1 + this.fontRendererInstance.FONT_HEIGHT);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void drawCursorVertical(int p_146188_1_, int p_146188_2_, int p_146188_3_, int p_146188_4_) {
/* 600 */     if (p_146188_1_ < p_146188_3_) {
/*     */       
/* 602 */       int var5 = p_146188_1_;
/* 603 */       p_146188_1_ = p_146188_3_;
/* 604 */       p_146188_3_ = var5;
/*     */     } 
/*     */     
/* 607 */     if (p_146188_2_ < p_146188_4_) {
/*     */       
/* 609 */       int var5 = p_146188_2_;
/* 610 */       p_146188_2_ = p_146188_4_;
/* 611 */       p_146188_4_ = var5;
/*     */     } 
/*     */     
/* 614 */     if (p_146188_3_ > this.xPosition + this.width)
/*     */     {
/* 616 */       p_146188_3_ = this.xPosition + this.width;
/*     */     }
/*     */     
/* 619 */     if (p_146188_1_ > this.xPosition + this.width)
/*     */     {
/* 621 */       p_146188_1_ = this.xPosition + this.width;
/*     */     }
/*     */     
/* 624 */     Tessellator var7 = Tessellator.getInstance();
/* 625 */     WorldRenderer var6 = var7.getWorldRenderer();
/* 626 */     GlStateManager.color(0.0F, 0.0F, 255.0F, 255.0F);
/* 627 */     GlStateManager.func_179090_x();
/* 628 */     GlStateManager.enableColorLogic();
/* 629 */     GlStateManager.colorLogicOp(5387);
/* 630 */     var6.startDrawingQuads();
/* 631 */     var6.addVertex(p_146188_1_, p_146188_4_, 0.0D);
/* 632 */     var6.addVertex(p_146188_3_, p_146188_4_, 0.0D);
/* 633 */     var6.addVertex(p_146188_3_, p_146188_2_, 0.0D);
/* 634 */     var6.addVertex(p_146188_1_, p_146188_2_, 0.0D);
/* 635 */     var7.draw();
/* 636 */     GlStateManager.disableColorLogic();
/* 637 */     GlStateManager.func_179098_w();
/*     */   }
/*     */ 
/*     */   
/*     */   public void setMaxStringLength(int p_146203_1_) {
/* 642 */     this.maxStringLength = p_146203_1_;
/*     */     
/* 644 */     if (this.text.length() > p_146203_1_)
/*     */     {
/* 646 */       this.text = this.text.substring(0, p_146203_1_);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getMaxStringLength() {
/* 655 */     return this.maxStringLength;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getCursorPosition() {
/* 663 */     return this.cursorPosition;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean getEnableBackgroundDrawing() {
/* 671 */     return this.enableBackgroundDrawing;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setEnableBackgroundDrawing(boolean p_146185_1_) {
/* 679 */     this.enableBackgroundDrawing = p_146185_1_;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setTextColor(int p_146193_1_) {
/* 687 */     this.enabledColor = p_146193_1_;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDisabledTextColour(int p_146204_1_) {
/* 692 */     this.disabledColor = p_146204_1_;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setFocused(boolean p_146195_1_) {
/* 700 */     if (p_146195_1_ && !this.isFocused)
/*     */     {
/* 702 */       this.cursorCounter = 0;
/*     */     }
/*     */     
/* 705 */     this.isFocused = p_146195_1_;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isFocused() {
/* 713 */     return this.isFocused;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setEnabled(boolean p_146184_1_) {
/* 718 */     this.isEnabled = p_146184_1_;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getSelectionEnd() {
/* 726 */     return this.selectionEnd;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getWidth() {
/* 734 */     return getEnableBackgroundDrawing() ? (this.width - 8) : this.width;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSelectionPos(int p_146199_1_) {
/* 742 */     int var2 = this.text.length();
/*     */     
/* 744 */     if (p_146199_1_ > var2)
/*     */     {
/* 746 */       p_146199_1_ = var2;
/*     */     }
/*     */     
/* 749 */     if (p_146199_1_ < 0)
/*     */     {
/* 751 */       p_146199_1_ = 0;
/*     */     }
/*     */     
/* 754 */     this.selectionEnd = p_146199_1_;
/*     */     
/* 756 */     if (this.fontRendererInstance != null) {
/*     */       
/* 758 */       if (this.lineScrollOffset > var2)
/*     */       {
/* 760 */         this.lineScrollOffset = var2;
/*     */       }
/*     */       
/* 763 */       int var3 = getWidth();
/* 764 */       String var4 = this.fontRendererInstance.trimStringToWidth(this.text.substring(this.lineScrollOffset), var3);
/* 765 */       int var5 = var4.length() + this.lineScrollOffset;
/*     */       
/* 767 */       if (p_146199_1_ == this.lineScrollOffset)
/*     */       {
/* 769 */         this.lineScrollOffset -= this.fontRendererInstance.trimStringToWidth(this.text, var3, true).length();
/*     */       }
/*     */       
/* 772 */       if (p_146199_1_ > var5) {
/*     */         
/* 774 */         this.lineScrollOffset += p_146199_1_ - var5;
/*     */       }
/* 776 */       else if (p_146199_1_ <= this.lineScrollOffset) {
/*     */         
/* 778 */         this.lineScrollOffset -= this.lineScrollOffset - p_146199_1_;
/*     */       } 
/*     */       
/* 781 */       this.lineScrollOffset = MathHelper.clamp_int(this.lineScrollOffset, 0, var2);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setCanLoseFocus(boolean p_146205_1_) {
/* 790 */     this.canLoseFocus = p_146205_1_;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean getVisible() {
/* 798 */     return this.visible;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setVisible(boolean p_146189_1_) {
/* 806 */     this.visible = p_146189_1_;
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\gui\GuiTextField.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */