/*     */ package leap.alts;
/*     */ 
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.gui.FontRenderer;
/*     */ import net.minecraft.client.gui.Gui;
/*     */ import net.minecraft.client.gui.GuiScreen;
/*     */ import net.minecraft.client.renderer.Tessellator;
/*     */ import net.minecraft.client.renderer.WorldRenderer;
/*     */ import net.minecraft.util.ChatAllowedCharacters;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PasswordField
/*     */   extends Gui
/*     */ {
/*     */   private final FontRenderer fontRenderer;
/*     */   private final int xPos;
/*     */   private final int yPos;
/*     */   private final int width;
/*     */   private final int height;
/*     */   private String text;
/*     */   private int maxStringLength;
/*     */   private int cursorCounter;
/*     */   private boolean enableBackgroundDrawing;
/*     */   private boolean canLoseFocus;
/*     */   public boolean isFocused;
/*     */   private boolean isEnabled;
/*     */   private int i;
/*     */   private int cursorPosition;
/*     */   private int selectionEnd;
/*     */   private int enabledColor;
/*     */   private int disabledColor;
/*     */   private boolean b;
/*     */   
/*     */   public PasswordField(FontRenderer par1FontRenderer, int par2, int par3, int par4, int par5) {
/*  38 */     this.text = "";
/*  39 */     this.maxStringLength = 50;
/*  40 */     this.enableBackgroundDrawing = true;
/*  41 */     this.canLoseFocus = true;
/*  42 */     this.isFocused = false;
/*  43 */     this.isEnabled = true;
/*  44 */     this.i = 0;
/*  45 */     this.cursorPosition = 0;
/*  46 */     this.selectionEnd = 0;
/*  47 */     this.enabledColor = 14737632;
/*  48 */     this.disabledColor = 7368816;
/*  49 */     this.b = true;
/*  50 */     this.fontRenderer = par1FontRenderer;
/*  51 */     this.xPos = par2;
/*  52 */     this.yPos = par3;
/*  53 */     this.width = par4;
/*  54 */     this.height = par5;
/*     */   }
/*     */   
/*     */   public void updateCursorCounter() {
/*  58 */     this.cursorCounter++;
/*     */   }
/*     */   
/*     */   public void setText(String par1Str) {
/*  62 */     if (par1Str.length() > this.maxStringLength) {
/*  63 */       this.text = par1Str.substring(0, this.maxStringLength);
/*     */     } else {
/*     */       
/*  66 */       this.text = par1Str;
/*     */     } 
/*  68 */     setCursorPositionEnd();
/*     */   }
/*     */   
/*     */   public String getText() {
/*  72 */     String newtext = this.text.replaceAll(" ", "");
/*  73 */     return newtext;
/*     */   }
/*     */   
/*     */   public String getSelectedtext() {
/*  77 */     int var1 = (this.cursorPosition < this.selectionEnd) ? this.cursorPosition : this.selectionEnd;
/*  78 */     int var2 = (this.cursorPosition < this.selectionEnd) ? this.selectionEnd : this.cursorPosition;
/*  79 */     return this.text.substring(var1, var2);
/*     */   }
/*     */   public void writeText(String par1Str) {
/*     */     int var8;
/*  83 */     String var2 = "";
/*  84 */     String var3 = ChatAllowedCharacters.filterAllowedCharacters(par1Str);
/*  85 */     int var4 = (this.cursorPosition < this.selectionEnd) ? this.cursorPosition : this.selectionEnd;
/*  86 */     int var5 = (this.cursorPosition < this.selectionEnd) ? this.selectionEnd : this.cursorPosition;
/*  87 */     int var6 = this.maxStringLength - this.text.length() - var4 - this.selectionEnd;
/*  88 */     boolean var7 = false;
/*  89 */     if (this.text.length() > 0) {
/*  90 */       var2 = String.valueOf(String.valueOf(String.valueOf(var2))) + this.text.substring(0, var4);
/*     */     }
/*     */     
/*  93 */     if (var6 < var3.length()) {
/*  94 */       var2 = String.valueOf(String.valueOf(String.valueOf(var2))) + var3.substring(0, var6);
/*  95 */       var8 = var6;
/*     */     } else {
/*     */       
/*  98 */       var2 = String.valueOf(String.valueOf(String.valueOf(var2))) + var3;
/*  99 */       var8 = var3.length();
/*     */     } 
/* 101 */     if (this.text.length() > 0 && var5 < this.text.length()) {
/* 102 */       var2 = String.valueOf(String.valueOf(String.valueOf(var2))) + this.text.substring(var5);
/*     */     }
/* 104 */     this.text = var2.replaceAll(" ", "");
/* 105 */     cursorPos(var4 - this.selectionEnd + var8);
/*     */   }
/*     */   
/*     */   public void func_73779_a(int par1) {
/* 109 */     if (this.text.length() != 0) {
/* 110 */       if (this.selectionEnd != this.cursorPosition) {
/* 111 */         writeText("");
/*     */       } else {
/*     */         
/* 114 */         deleteFromCursor(getNthWordFromCursor(par1) - this.cursorPosition);
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   public void deleteFromCursor(int par1) {
/* 120 */     if (this.text.length() != 0) {
/* 121 */       if (this.selectionEnd != this.cursorPosition) {
/* 122 */         writeText("");
/*     */       } else {
/*     */         
/* 125 */         boolean var2 = (par1 < 0);
/* 126 */         int var3 = var2 ? (this.cursorPosition + par1) : this.cursorPosition;
/* 127 */         int var4 = var2 ? this.cursorPosition : (this.cursorPosition + par1);
/* 128 */         String var5 = "";
/* 129 */         if (var3 >= 0) {
/* 130 */           var5 = this.text.substring(0, var3);
/*     */         }
/* 132 */         if (var4 < this.text.length()) {
/* 133 */           var5 = String.valueOf(String.valueOf(String.valueOf(var5))) + this.text.substring(var4);
/*     */         }
/* 135 */         this.text = var5;
/* 136 */         if (var2) {
/* 137 */           cursorPos(par1);
/*     */         }
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   public int getNthWordFromCursor(int par1) {
/* 144 */     return getNthWordFromPos(par1, getCursorPosition());
/*     */   }
/*     */   
/*     */   public int getNthWordFromPos(int par1, int par2) {
/* 148 */     return type(par1, getCursorPosition(), true);
/*     */   }
/*     */   
/*     */   public int type(int par1, int par2, boolean par3) {
/* 152 */     int var4 = par2;
/* 153 */     boolean var5 = (par1 < 0);
/* 154 */     for (int var6 = Math.abs(par1), var7 = 0; var7 < var6; var7++) {
/* 155 */       if (!var5) {
/* 156 */         int var8 = this.text.length();
/* 157 */         var4 = this.text.indexOf(' ', var4);
/* 158 */         if (var4 == -1) {
/* 159 */           var4 = var8;
/*     */         } else {
/*     */           
/* 162 */           while (par3 && 
/* 163 */             var4 < var8) {
/*     */ 
/*     */             
/* 166 */             if (this.text.charAt(var4) != ' ') {
/*     */               break;
/*     */             }
/* 169 */             var4++;
/*     */           } 
/*     */         } 
/*     */       } else {
/*     */         
/* 174 */         while (par3 && 
/* 175 */           var4 > 0) {
/*     */ 
/*     */           
/* 178 */           if (this.text.charAt(var4 - 1) != ' ') {
/*     */             break;
/*     */           }
/* 181 */           var4--;
/*     */         } 
/* 183 */         while (var4 > 0 && this.text.charAt(var4 - 1) != ' ') {
/* 184 */           var4--;
/*     */         }
/*     */       } 
/*     */     } 
/* 188 */     return var4;
/*     */   }
/*     */   
/*     */   public void cursorPos(int par1) {
/* 192 */     setCursorPosition(this.selectionEnd + par1);
/*     */   }
/*     */   
/*     */   public void setCursorPosition(int par1) {
/* 196 */     this.cursorPosition = par1;
/* 197 */     int var2 = this.text.length();
/* 198 */     if (this.cursorPosition < 0) {
/* 199 */       this.cursorPosition = 0;
/*     */     }
/* 201 */     if (this.cursorPosition > var2) {
/* 202 */       this.cursorPosition = var2;
/*     */     }
/* 204 */     func_73800_i(this.cursorPosition);
/*     */   }
/*     */   
/*     */   public void setCursorPositionZero() {
/* 208 */     setCursorPosition(0);
/*     */   }
/*     */   
/*     */   public void setCursorPositionEnd() {
/* 212 */     setCursorPosition(this.text.length());
/*     */   }
/*     */   
/*     */   public boolean textboxKeyTyped(char par1, int par2) {
/* 216 */     if (!this.isEnabled || !this.isFocused) {
/* 217 */       return false;
/*     */     }
/* 219 */     switch (par1) {
/*     */       case '\001':
/* 221 */         setCursorPositionEnd();
/* 222 */         func_73800_i(0);
/* 223 */         return true;
/*     */       
/*     */       case '\003':
/* 226 */         GuiScreen.setClipboardString(getSelectedtext());
/* 227 */         return true;
/*     */       
/*     */       case '\026':
/* 230 */         writeText(GuiScreen.getClipboardString());
/* 231 */         return true;
/*     */       
/*     */       case '\030':
/* 234 */         GuiScreen.setClipboardString(getSelectedtext());
/* 235 */         writeText("");
/* 236 */         return true;
/*     */     } 
/*     */     
/* 239 */     switch (par2) {
/*     */       case 14:
/* 241 */         if (GuiScreen.isCtrlKeyDown()) {
/* 242 */           func_73779_a(-1);
/*     */         } else {
/*     */           
/* 245 */           deleteFromCursor(-1);
/*     */         } 
/* 247 */         return true;
/*     */       
/*     */       case 199:
/* 250 */         if (GuiScreen.isShiftKeyDown()) {
/* 251 */           func_73800_i(0);
/*     */         } else {
/*     */           
/* 254 */           setCursorPositionZero();
/*     */         } 
/* 256 */         return true;
/*     */       
/*     */       case 203:
/* 259 */         if (GuiScreen.isShiftKeyDown()) {
/* 260 */           if (GuiScreen.isCtrlKeyDown()) {
/* 261 */             func_73800_i(getNthWordFromPos(-1, getSelectionEnd()));
/*     */           } else {
/*     */             
/* 264 */             func_73800_i(getSelectionEnd() - 1);
/*     */           }
/*     */         
/* 267 */         } else if (GuiScreen.isCtrlKeyDown()) {
/* 268 */           setCursorPosition(getNthWordFromCursor(-1));
/*     */         } else {
/*     */           
/* 271 */           cursorPos(-1);
/*     */         } 
/* 273 */         return true;
/*     */       
/*     */       case 205:
/* 276 */         if (GuiScreen.isShiftKeyDown()) {
/* 277 */           if (GuiScreen.isCtrlKeyDown()) {
/* 278 */             func_73800_i(getNthWordFromPos(1, getSelectionEnd()));
/*     */           } else {
/*     */             
/* 281 */             func_73800_i(getSelectionEnd() + 1);
/*     */           }
/*     */         
/* 284 */         } else if (GuiScreen.isCtrlKeyDown()) {
/* 285 */           setCursorPosition(getNthWordFromCursor(1));
/*     */         } else {
/*     */           
/* 288 */           cursorPos(1);
/*     */         } 
/* 290 */         return true;
/*     */       
/*     */       case 207:
/* 293 */         if (GuiScreen.isShiftKeyDown()) {
/* 294 */           func_73800_i(this.text.length());
/*     */         } else {
/*     */           
/* 297 */           setCursorPositionEnd();
/*     */         } 
/* 299 */         return true;
/*     */       
/*     */       case 211:
/* 302 */         if (GuiScreen.isCtrlKeyDown()) {
/* 303 */           func_73779_a(1);
/*     */         } else {
/*     */           
/* 306 */           deleteFromCursor(1);
/*     */         } 
/* 308 */         return true;
/*     */     } 
/*     */     
/* 311 */     if (ChatAllowedCharacters.isAllowedCharacter(par1)) {
/* 312 */       writeText(Character.toString(par1));
/* 313 */       return true;
/*     */     } 
/* 315 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void mouseClicked(int par1, int par2, int par3) {
/* 323 */     boolean var4 = (par1 >= this.xPos && par1 < this.xPos + this.width && par2 >= this.yPos && par2 < this.yPos + this.height);
/* 324 */     if (this.canLoseFocus) {
/* 325 */       setFocused((this.isEnabled && var4));
/*     */     }
/* 327 */     if (this.isFocused && par3 == 0) {
/* 328 */       int var5 = par1 - this.xPos;
/* 329 */       if (this.enableBackgroundDrawing) {
/* 330 */         var5 -= 4;
/*     */       }
/* 332 */       String var6 = this.fontRenderer.trimStringToWidth(this.text.substring(this.i), getWidth());
/* 333 */       setCursorPosition(this.fontRenderer.trimStringToWidth(var6, var5).length() + this.i);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void drawTextBox() {
/* 338 */     if (func_73778_q()) {
/* 339 */       if (getEnableBackgroundDrawing()) {
/* 340 */         Gui.drawRect((this.xPos - 1), (this.yPos - 1), (this.xPos + this.width + 1), (this.yPos + this.height + 1), -6250336);
/* 341 */         Gui.drawRect(this.xPos, this.yPos, (this.xPos + this.width), (this.yPos + this.height), -16777216);
/*     */       } 
/* 343 */       int var1 = this.isEnabled ? this.enabledColor : this.disabledColor;
/* 344 */       int var2 = this.cursorPosition - this.i;
/* 345 */       int var3 = this.selectionEnd - this.i;
/* 346 */       String var4 = this.fontRenderer.trimStringToWidth(this.text.substring(this.i), getWidth());
/* 347 */       boolean var5 = (var2 >= 0 && var2 <= var4.length());
/* 348 */       boolean var6 = (this.isFocused && this.cursorCounter / 6 % 2 == 0 && var5);
/* 349 */       int var7 = this.enableBackgroundDrawing ? (this.xPos + 4) : this.xPos;
/* 350 */       int var8 = this.enableBackgroundDrawing ? (this.yPos + (this.height - 8) / 2) : this.yPos;
/* 351 */       int var9 = var7;
/* 352 */       if (var3 > var4.length()) {
/* 353 */         var3 = var4.length();
/*     */       }
/* 355 */       if (var4.length() > 0) {
/* 356 */         if (var5) {
/* 357 */           var4.substring(0, var2);
/*     */         }
/* 359 */         var9 = (Minecraft.getMinecraft()).fontRendererObj.drawStringWithShadow(this.text.replaceAll("(?s).", "*"), var7, var8, var1);
/*     */       } 
/* 361 */       boolean var10 = !(this.cursorPosition >= this.text.length() && this.text.length() < getMaxStringLength());
/* 362 */       int var11 = var9;
/* 363 */       if (!var5) {
/* 364 */         var11 = (var2 > 0) ? (var7 + this.width) : var7;
/*     */       }
/* 366 */       else if (var10) {
/* 367 */         var11 = var9 - 1;
/* 368 */         var9--;
/*     */       } 
/* 370 */       if (var4.length() > 0 && var5 && var2 < var4.length()) {
/* 371 */         (Minecraft.getMinecraft()).fontRendererObj.drawStringWithShadow(var4.substring(var2), var9, var8, var1);
/*     */       }
/* 373 */       if (var6) {
/* 374 */         if (var10) {
/* 375 */           Gui.drawRect(var11, (var8 - 1), (var11 + 1), (var8 + 1 + this.fontRenderer.FONT_HEIGHT), -3092272);
/*     */         } else {
/*     */           
/* 378 */           (Minecraft.getMinecraft()).fontRendererObj.drawStringWithShadow("_", var11, var8, var1);
/*     */         } 
/*     */       }
/* 381 */       if (var3 != var2) {
/* 382 */         int var12 = var7 + this.fontRenderer.getStringWidth(var4.substring(0, var3));
/* 383 */         drawCursorVertical(var11, var8 - 1, var12 - 1, var8 + 1 + this.fontRenderer.FONT_HEIGHT);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void drawCursorVertical(int par1, int par2, int par3, int par4) {
/* 389 */     if (par1 < par3) {
/* 390 */       int var5 = par1;
/* 391 */       par1 = par3;
/* 392 */       par3 = var5;
/*     */     } 
/* 394 */     if (par2 < par4) {
/* 395 */       int var5 = par2;
/* 396 */       par2 = par4;
/* 397 */       par4 = var5;
/*     */     } 
/* 399 */     Tessellator var6 = Tessellator.getInstance();
/* 400 */     WorldRenderer var7 = var6.getWorldRenderer();
/* 401 */     GL11.glColor4f(0.0F, 0.0F, 255.0F, 255.0F);
/* 402 */     GL11.glDisable(3553);
/* 403 */     GL11.glEnable(3058);
/* 404 */     GL11.glLogicOp(5387);
/* 405 */     var7.addVertex(par1, par4, 0.0D);
/* 406 */     var7.addVertex(par3, par4, 0.0D);
/* 407 */     var7.addVertex(par3, par2, 0.0D);
/* 408 */     var7.addVertex(par1, par2, 0.0D);
/* 409 */     var7.draw();
/* 410 */     GL11.glDisable(3058);
/* 411 */     GL11.glEnable(3553);
/*     */   }
/*     */   
/*     */   public void setMaxStringLength(int par1) {
/* 415 */     this.maxStringLength = par1;
/* 416 */     if (this.text.length() > par1) {
/* 417 */       this.text = this.text.substring(0, par1);
/*     */     }
/*     */   }
/*     */   
/*     */   public int getMaxStringLength() {
/* 422 */     return this.maxStringLength;
/*     */   }
/*     */   
/*     */   public int getCursorPosition() {
/* 426 */     return this.cursorPosition;
/*     */   }
/*     */   
/*     */   public boolean getEnableBackgroundDrawing() {
/* 430 */     return this.enableBackgroundDrawing;
/*     */   }
/*     */   
/*     */   public void setEnableBackgroundDrawing(boolean par1) {
/* 434 */     this.enableBackgroundDrawing = par1;
/*     */   }
/*     */   
/*     */   public void func_73794_g(int par1) {
/* 438 */     this.enabledColor = par1;
/*     */   }
/*     */   
/*     */   public void setFocused(boolean par1) {
/* 442 */     if (par1 && !this.isFocused) {
/* 443 */       this.cursorCounter = 0;
/*     */     }
/* 445 */     this.isFocused = par1;
/*     */   }
/*     */   
/*     */   public boolean isFocused() {
/* 449 */     return this.isFocused;
/*     */   }
/*     */   
/*     */   public int getSelectionEnd() {
/* 453 */     return this.selectionEnd;
/*     */   }
/*     */   
/*     */   public int getWidth() {
/* 457 */     return getEnableBackgroundDrawing() ? (this.width - 8) : this.width;
/*     */   }
/*     */   
/*     */   public void func_73800_i(int par1) {
/* 461 */     int var2 = this.text.length();
/* 462 */     if (par1 > var2) {
/* 463 */       par1 = var2;
/*     */     }
/* 465 */     if (par1 < 0) {
/* 466 */       par1 = 0;
/*     */     }
/* 468 */     this.selectionEnd = par1;
/* 469 */     if (this.fontRenderer != null) {
/* 470 */       if (this.i > var2) {
/* 471 */         this.i = var2;
/*     */       }
/* 473 */       int var3 = getWidth();
/* 474 */       String var4 = this.fontRenderer.trimStringToWidth(this.text.substring(this.i), var3);
/* 475 */       int var5 = var4.length() + this.i;
/* 476 */       if (par1 == this.i) {
/* 477 */         this.i -= this.fontRenderer.trimStringToWidth(this.text, var3, true).length();
/*     */       }
/* 479 */       if (par1 > var5) {
/* 480 */         this.i += par1 - var5;
/*     */       }
/* 482 */       else if (par1 <= this.i) {
/* 483 */         this.i -= this.i - par1;
/*     */       } 
/* 485 */       if (this.i < 0) {
/* 486 */         this.i = 0;
/*     */       }
/* 488 */       if (this.i > var2) {
/* 489 */         this.i = var2;
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   public void setCanLoseFocus(boolean par1) {
/* 495 */     this.canLoseFocus = par1;
/*     */   }
/*     */   
/*     */   public boolean func_73778_q() {
/* 499 */     return this.b;
/*     */   }
/*     */   
/*     */   public void func_73790_e(boolean par1) {
/* 503 */     this.b = par1;
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\leap\alts\PasswordField.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */