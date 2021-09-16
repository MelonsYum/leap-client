/*     */ package net.minecraft.client.gui;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.util.Random;
/*     */ import net.minecraft.client.resources.I18n;
/*     */ import net.minecraft.util.ChatAllowedCharacters;
/*     */ import net.minecraft.world.WorldSettings;
/*     */ import net.minecraft.world.WorldType;
/*     */ import net.minecraft.world.storage.ISaveFormat;
/*     */ import net.minecraft.world.storage.WorldInfo;
/*     */ import org.apache.commons.lang3.StringUtils;
/*     */ import org.lwjgl.input.Keyboard;
/*     */ 
/*     */ public class GuiCreateWorld
/*     */   extends GuiScreen {
/*     */   private GuiScreen field_146332_f;
/*     */   private GuiTextField field_146333_g;
/*     */   private GuiTextField field_146335_h;
/*     */   private String field_146336_i;
/*  20 */   private String field_146342_r = "survival";
/*     */   private String field_175300_s;
/*     */   private boolean field_146341_s = true;
/*     */   private boolean field_146340_t;
/*     */   private boolean field_146339_u;
/*     */   private boolean field_146338_v;
/*     */   private boolean field_146337_w;
/*     */   private boolean field_146345_x;
/*     */   private boolean field_146344_y;
/*     */   private GuiButton field_146343_z;
/*     */   private GuiButton field_146324_A;
/*     */   private GuiButton field_146325_B;
/*     */   private GuiButton field_146326_C;
/*     */   private GuiButton field_146320_D;
/*     */   private GuiButton field_146321_E;
/*     */   private GuiButton field_146322_F;
/*     */   private String field_146323_G;
/*     */   private String field_146328_H;
/*     */   private String field_146329_I;
/*     */   private String field_146330_J;
/*     */   private int field_146331_K;
/*  41 */   public String field_146334_a = "";
/*  42 */   private static final String[] field_146327_L = new String[] { "CON", "COM", "PRN", "AUX", "CLOCK$", "NUL", "COM1", "COM2", "COM3", "COM4", "COM5", "COM6", "COM7", "COM8", "COM9", "LPT1", "LPT2", "LPT3", "LPT4", "LPT5", "LPT6", "LPT7", "LPT8", "LPT9" };
/*     */   
/*     */   private static final String __OBFID = "CL_00000689";
/*     */   
/*     */   public GuiCreateWorld(GuiScreen p_i46320_1_) {
/*  47 */     this.field_146332_f = p_i46320_1_;
/*  48 */     this.field_146329_I = "";
/*  49 */     this.field_146330_J = I18n.format("selectWorld.newWorld", new Object[0]);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void updateScreen() {
/*  57 */     this.field_146333_g.updateCursorCounter();
/*  58 */     this.field_146335_h.updateCursorCounter();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void initGui() {
/*  66 */     Keyboard.enableRepeatEvents(true);
/*  67 */     this.buttonList.clear();
/*  68 */     this.buttonList.add(new GuiButton(0, width / 2 - 155, height - 28, 150, 20, I18n.format("selectWorld.create", new Object[0])));
/*  69 */     this.buttonList.add(new GuiButton(1, width / 2 + 5, height - 28, 150, 20, I18n.format("gui.cancel", new Object[0])));
/*  70 */     this.buttonList.add(this.field_146343_z = new GuiButton(2, width / 2 - 75, 115, 150, 20, I18n.format("selectWorld.gameMode", new Object[0])));
/*  71 */     this.buttonList.add(this.field_146324_A = new GuiButton(3, width / 2 - 75, 187, 150, 20, I18n.format("selectWorld.moreWorldOptions", new Object[0])));
/*  72 */     this.buttonList.add(this.field_146325_B = new GuiButton(4, width / 2 - 155, 100, 150, 20, I18n.format("selectWorld.mapFeatures", new Object[0])));
/*  73 */     this.field_146325_B.visible = false;
/*  74 */     this.buttonList.add(this.field_146326_C = new GuiButton(7, width / 2 + 5, 151, 150, 20, I18n.format("selectWorld.bonusItems", new Object[0])));
/*  75 */     this.field_146326_C.visible = false;
/*  76 */     this.buttonList.add(this.field_146320_D = new GuiButton(5, width / 2 + 5, 100, 150, 20, I18n.format("selectWorld.mapType", new Object[0])));
/*  77 */     this.field_146320_D.visible = false;
/*  78 */     this.buttonList.add(this.field_146321_E = new GuiButton(6, width / 2 - 155, 151, 150, 20, I18n.format("selectWorld.allowCommands", new Object[0])));
/*  79 */     this.field_146321_E.visible = false;
/*  80 */     this.buttonList.add(this.field_146322_F = new GuiButton(8, width / 2 + 5, 120, 150, 20, I18n.format("selectWorld.customizeType", new Object[0])));
/*  81 */     this.field_146322_F.visible = false;
/*  82 */     this.field_146333_g = new GuiTextField(9, fontRendererObj, width / 2 - 100, 60, 200, 20);
/*  83 */     this.field_146333_g.setFocused(true);
/*  84 */     this.field_146333_g.setText(this.field_146330_J);
/*  85 */     this.field_146335_h = new GuiTextField(10, fontRendererObj, width / 2 - 100, 60, 200, 20);
/*  86 */     this.field_146335_h.setText(this.field_146329_I);
/*  87 */     func_146316_a(this.field_146344_y);
/*  88 */     func_146314_g();
/*  89 */     func_146319_h();
/*     */   }
/*     */ 
/*     */   
/*     */   private void func_146314_g() {
/*  94 */     this.field_146336_i = this.field_146333_g.getText().trim();
/*  95 */     char[] var1 = ChatAllowedCharacters.allowedCharactersArray;
/*  96 */     int var2 = var1.length;
/*     */     
/*  98 */     for (int var3 = 0; var3 < var2; var3++) {
/*     */       
/* 100 */       char var4 = var1[var3];
/* 101 */       this.field_146336_i = this.field_146336_i.replace(var4, '_');
/*     */     } 
/*     */     
/* 104 */     if (StringUtils.isEmpty(this.field_146336_i))
/*     */     {
/* 106 */       this.field_146336_i = "World";
/*     */     }
/*     */     
/* 109 */     this.field_146336_i = func_146317_a(this.mc.getSaveLoader(), this.field_146336_i);
/*     */   }
/*     */ 
/*     */   
/*     */   private void func_146319_h() {
/* 114 */     this.field_146343_z.displayString = String.valueOf(I18n.format("selectWorld.gameMode", new Object[0])) + ": " + I18n.format("selectWorld.gameMode." + this.field_146342_r, new Object[0]);
/* 115 */     this.field_146323_G = I18n.format("selectWorld.gameMode." + this.field_146342_r + ".line1", new Object[0]);
/* 116 */     this.field_146328_H = I18n.format("selectWorld.gameMode." + this.field_146342_r + ".line2", new Object[0]);
/* 117 */     this.field_146325_B.displayString = String.valueOf(I18n.format("selectWorld.mapFeatures", new Object[0])) + " ";
/*     */     
/* 119 */     if (this.field_146341_s) {
/*     */       
/* 121 */       this.field_146325_B.displayString = String.valueOf(this.field_146325_B.displayString) + I18n.format("options.on", new Object[0]);
/*     */     }
/*     */     else {
/*     */       
/* 125 */       this.field_146325_B.displayString = String.valueOf(this.field_146325_B.displayString) + I18n.format("options.off", new Object[0]);
/*     */     } 
/*     */     
/* 128 */     this.field_146326_C.displayString = String.valueOf(I18n.format("selectWorld.bonusItems", new Object[0])) + " ";
/*     */     
/* 130 */     if (this.field_146338_v && !this.field_146337_w) {
/*     */       
/* 132 */       this.field_146326_C.displayString = String.valueOf(this.field_146326_C.displayString) + I18n.format("options.on", new Object[0]);
/*     */     }
/*     */     else {
/*     */       
/* 136 */       this.field_146326_C.displayString = String.valueOf(this.field_146326_C.displayString) + I18n.format("options.off", new Object[0]);
/*     */     } 
/*     */     
/* 139 */     this.field_146320_D.displayString = String.valueOf(I18n.format("selectWorld.mapType", new Object[0])) + " " + I18n.format(WorldType.worldTypes[this.field_146331_K].getTranslateName(), new Object[0]);
/* 140 */     this.field_146321_E.displayString = String.valueOf(I18n.format("selectWorld.allowCommands", new Object[0])) + " ";
/*     */     
/* 142 */     if (this.field_146340_t && !this.field_146337_w) {
/*     */       
/* 144 */       this.field_146321_E.displayString = String.valueOf(this.field_146321_E.displayString) + I18n.format("options.on", new Object[0]);
/*     */     }
/*     */     else {
/*     */       
/* 148 */       this.field_146321_E.displayString = String.valueOf(this.field_146321_E.displayString) + I18n.format("options.off", new Object[0]);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static String func_146317_a(ISaveFormat p_146317_0_, String p_146317_1_) {
/* 154 */     p_146317_1_ = p_146317_1_.replaceAll("[\\./\"]", "_");
/* 155 */     String[] var2 = field_146327_L;
/* 156 */     int var3 = var2.length;
/*     */     
/* 158 */     for (int var4 = 0; var4 < var3; var4++) {
/*     */       
/* 160 */       String var5 = var2[var4];
/*     */       
/* 162 */       if (p_146317_1_.equalsIgnoreCase(var5))
/*     */       {
/* 164 */         p_146317_1_ = "_" + p_146317_1_ + "_";
/*     */       }
/*     */     } 
/*     */     
/* 168 */     while (p_146317_0_.getWorldInfo(p_146317_1_) != null)
/*     */     {
/* 170 */       p_146317_1_ = String.valueOf(p_146317_1_) + "-";
/*     */     }
/*     */     
/* 173 */     return p_146317_1_;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onGuiClosed() {
/* 181 */     Keyboard.enableRepeatEvents(false);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void actionPerformed(GuiButton button) throws IOException {
/* 186 */     if (button.enabled)
/*     */     {
/* 188 */       if (button.id == 1) {
/*     */         
/* 190 */         this.mc.displayGuiScreen(this.field_146332_f);
/*     */       }
/* 192 */       else if (button.id == 0) {
/*     */         
/* 194 */         this.mc.displayGuiScreen(null);
/*     */         
/* 196 */         if (this.field_146345_x) {
/*     */           return;
/*     */         }
/*     */ 
/*     */         
/* 201 */         this.field_146345_x = true;
/* 202 */         long var2 = (new Random()).nextLong();
/* 203 */         String var4 = this.field_146335_h.getText();
/*     */         
/* 205 */         if (!StringUtils.isEmpty(var4)) {
/*     */           
/*     */           try {
/*     */             
/* 209 */             long var5 = Long.parseLong(var4);
/*     */             
/* 211 */             if (var5 != 0L)
/*     */             {
/* 213 */               var2 = var5;
/*     */             }
/*     */           }
/* 216 */           catch (NumberFormatException var7) {
/*     */             
/* 218 */             var2 = var4.hashCode();
/*     */           } 
/*     */         }
/*     */         
/* 222 */         WorldSettings.GameType var8 = WorldSettings.GameType.getByName(this.field_146342_r);
/* 223 */         WorldSettings var6 = new WorldSettings(var2, var8, this.field_146341_s, this.field_146337_w, WorldType.worldTypes[this.field_146331_K]);
/* 224 */         var6.setWorldName(this.field_146334_a);
/*     */         
/* 226 */         if (this.field_146338_v && !this.field_146337_w)
/*     */         {
/* 228 */           var6.enableBonusChest();
/*     */         }
/*     */         
/* 231 */         if (this.field_146340_t && !this.field_146337_w)
/*     */         {
/* 233 */           var6.enableCommands();
/*     */         }
/*     */         
/* 236 */         this.mc.launchIntegratedServer(this.field_146336_i, this.field_146333_g.getText().trim(), var6);
/*     */       }
/* 238 */       else if (button.id == 3) {
/*     */         
/* 240 */         func_146315_i();
/*     */       }
/* 242 */       else if (button.id == 2) {
/*     */         
/* 244 */         if (this.field_146342_r.equals("survival")) {
/*     */           
/* 246 */           if (!this.field_146339_u)
/*     */           {
/* 248 */             this.field_146340_t = false;
/*     */           }
/*     */           
/* 251 */           this.field_146337_w = false;
/* 252 */           this.field_146342_r = "hardcore";
/* 253 */           this.field_146337_w = true;
/* 254 */           this.field_146321_E.enabled = false;
/* 255 */           this.field_146326_C.enabled = false;
/* 256 */           func_146319_h();
/*     */         }
/* 258 */         else if (this.field_146342_r.equals("hardcore")) {
/*     */           
/* 260 */           if (!this.field_146339_u)
/*     */           {
/* 262 */             this.field_146340_t = true;
/*     */           }
/*     */           
/* 265 */           this.field_146337_w = false;
/* 266 */           this.field_146342_r = "creative";
/* 267 */           func_146319_h();
/* 268 */           this.field_146337_w = false;
/* 269 */           this.field_146321_E.enabled = true;
/* 270 */           this.field_146326_C.enabled = true;
/*     */         }
/*     */         else {
/*     */           
/* 274 */           if (!this.field_146339_u)
/*     */           {
/* 276 */             this.field_146340_t = false;
/*     */           }
/*     */           
/* 279 */           this.field_146342_r = "survival";
/* 280 */           func_146319_h();
/* 281 */           this.field_146321_E.enabled = true;
/* 282 */           this.field_146326_C.enabled = true;
/* 283 */           this.field_146337_w = false;
/*     */         } 
/*     */         
/* 286 */         func_146319_h();
/*     */       }
/* 288 */       else if (button.id == 4) {
/*     */         
/* 290 */         this.field_146341_s = !this.field_146341_s;
/* 291 */         func_146319_h();
/*     */       }
/* 293 */       else if (button.id == 7) {
/*     */         
/* 295 */         this.field_146338_v = !this.field_146338_v;
/* 296 */         func_146319_h();
/*     */       }
/* 298 */       else if (button.id == 5) {
/*     */         
/* 300 */         this.field_146331_K++;
/*     */         
/* 302 */         if (this.field_146331_K >= WorldType.worldTypes.length)
/*     */         {
/* 304 */           this.field_146331_K = 0;
/*     */         }
/*     */         
/* 307 */         while (!func_175299_g()) {
/*     */           
/* 309 */           this.field_146331_K++;
/*     */           
/* 311 */           if (this.field_146331_K >= WorldType.worldTypes.length)
/*     */           {
/* 313 */             this.field_146331_K = 0;
/*     */           }
/*     */         } 
/*     */         
/* 317 */         this.field_146334_a = "";
/* 318 */         func_146319_h();
/* 319 */         func_146316_a(this.field_146344_y);
/*     */       }
/* 321 */       else if (button.id == 6) {
/*     */         
/* 323 */         this.field_146339_u = true;
/* 324 */         this.field_146340_t = !this.field_146340_t;
/* 325 */         func_146319_h();
/*     */       }
/* 327 */       else if (button.id == 8) {
/*     */         
/* 329 */         if (WorldType.worldTypes[this.field_146331_K] == WorldType.FLAT) {
/*     */           
/* 331 */           this.mc.displayGuiScreen(new GuiCreateFlatWorld(this, this.field_146334_a));
/*     */         }
/*     */         else {
/*     */           
/* 335 */           this.mc.displayGuiScreen(new GuiCustomizeWorldScreen(this, this.field_146334_a));
/*     */         } 
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean func_175299_g() {
/* 343 */     WorldType var1 = WorldType.worldTypes[this.field_146331_K];
/* 344 */     return (var1 != null && var1.getCanBeCreated()) ? ((var1 == WorldType.DEBUG_WORLD) ? isShiftKeyDown() : true) : false;
/*     */   }
/*     */ 
/*     */   
/*     */   private void func_146315_i() {
/* 349 */     func_146316_a(!this.field_146344_y);
/*     */   }
/*     */ 
/*     */   
/*     */   private void func_146316_a(boolean p_146316_1_) {
/* 354 */     this.field_146344_y = p_146316_1_;
/*     */     
/* 356 */     if (WorldType.worldTypes[this.field_146331_K] == WorldType.DEBUG_WORLD) {
/*     */       
/* 358 */       this.field_146343_z.visible = !this.field_146344_y;
/* 359 */       this.field_146343_z.enabled = false;
/*     */       
/* 361 */       if (this.field_175300_s == null)
/*     */       {
/* 363 */         this.field_175300_s = this.field_146342_r;
/*     */       }
/*     */       
/* 366 */       this.field_146342_r = "spectator";
/* 367 */       this.field_146325_B.visible = false;
/* 368 */       this.field_146326_C.visible = false;
/* 369 */       this.field_146320_D.visible = this.field_146344_y;
/* 370 */       this.field_146321_E.visible = false;
/* 371 */       this.field_146322_F.visible = false;
/*     */     }
/*     */     else {
/*     */       
/* 375 */       this.field_146343_z.visible = !this.field_146344_y;
/* 376 */       this.field_146343_z.enabled = true;
/*     */       
/* 378 */       if (this.field_175300_s != null) {
/*     */         
/* 380 */         this.field_146342_r = this.field_175300_s;
/* 381 */         this.field_175300_s = null;
/*     */       } 
/*     */       
/* 384 */       this.field_146325_B.visible = (this.field_146344_y && WorldType.worldTypes[this.field_146331_K] != WorldType.CUSTOMIZED);
/* 385 */       this.field_146326_C.visible = this.field_146344_y;
/* 386 */       this.field_146320_D.visible = this.field_146344_y;
/* 387 */       this.field_146321_E.visible = this.field_146344_y;
/* 388 */       this.field_146322_F.visible = (this.field_146344_y && (WorldType.worldTypes[this.field_146331_K] == WorldType.FLAT || WorldType.worldTypes[this.field_146331_K] == WorldType.CUSTOMIZED));
/*     */     } 
/*     */     
/* 391 */     func_146319_h();
/*     */     
/* 393 */     if (this.field_146344_y) {
/*     */       
/* 395 */       this.field_146324_A.displayString = I18n.format("gui.done", new Object[0]);
/*     */     }
/*     */     else {
/*     */       
/* 399 */       this.field_146324_A.displayString = I18n.format("selectWorld.moreWorldOptions", new Object[0]);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void keyTyped(char typedChar, int keyCode) throws IOException {
/* 409 */     if (this.field_146333_g.isFocused() && !this.field_146344_y) {
/*     */       
/* 411 */       this.field_146333_g.textboxKeyTyped(typedChar, keyCode);
/* 412 */       this.field_146330_J = this.field_146333_g.getText();
/*     */     }
/* 414 */     else if (this.field_146335_h.isFocused() && this.field_146344_y) {
/*     */       
/* 416 */       this.field_146335_h.textboxKeyTyped(typedChar, keyCode);
/* 417 */       this.field_146329_I = this.field_146335_h.getText();
/*     */     } 
/*     */     
/* 420 */     if (keyCode == 28 || keyCode == 156)
/*     */     {
/* 422 */       actionPerformed(this.buttonList.get(0));
/*     */     }
/*     */     
/* 425 */     ((GuiButton)this.buttonList.get(0)).enabled = (this.field_146333_g.getText().length() > 0);
/* 426 */     func_146314_g();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
/* 434 */     super.mouseClicked(mouseX, mouseY, mouseButton);
/*     */     
/* 436 */     if (this.field_146344_y) {
/*     */       
/* 438 */       this.field_146335_h.mouseClicked(mouseX, mouseY, mouseButton);
/*     */     }
/*     */     else {
/*     */       
/* 442 */       this.field_146333_g.mouseClicked(mouseX, mouseY, mouseButton);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void drawScreen(int mouseX, int mouseY, float partialTicks) {
/* 451 */     drawDefaultBackground();
/* 452 */     drawCenteredString(fontRendererObj, I18n.format("selectWorld.create", new Object[0]), (width / 2), 20.0F, -1);
/*     */     
/* 454 */     if (this.field_146344_y) {
/*     */       
/* 456 */       drawString(fontRendererObj, I18n.format("selectWorld.enterSeed", new Object[0]), width / 2 - 100, 47, -6250336);
/* 457 */       drawString(fontRendererObj, I18n.format("selectWorld.seedInfo", new Object[0]), width / 2 - 100, 85, -6250336);
/*     */       
/* 459 */       if (this.field_146325_B.visible)
/*     */       {
/* 461 */         drawString(fontRendererObj, I18n.format("selectWorld.mapFeatures.info", new Object[0]), width / 2 - 150, 122, -6250336);
/*     */       }
/*     */       
/* 464 */       if (this.field_146321_E.visible)
/*     */       {
/* 466 */         drawString(fontRendererObj, I18n.format("selectWorld.allowCommands.info", new Object[0]), width / 2 - 150, 172, -6250336);
/*     */       }
/*     */       
/* 469 */       this.field_146335_h.drawTextBox();
/*     */       
/* 471 */       if (WorldType.worldTypes[this.field_146331_K].showWorldInfoNotice())
/*     */       {
/* 473 */         fontRendererObj.drawSplitString(I18n.format(WorldType.worldTypes[this.field_146331_K].func_151359_c(), new Object[0]), this.field_146320_D.xPosition + 2, this.field_146320_D.yPosition + 22, this.field_146320_D.getButtonWidth(), 10526880);
/*     */       }
/*     */     }
/*     */     else {
/*     */       
/* 478 */       drawString(fontRendererObj, I18n.format("selectWorld.enterName", new Object[0]), width / 2 - 100, 47, -6250336);
/* 479 */       drawString(fontRendererObj, String.valueOf(I18n.format("selectWorld.resultFolder", new Object[0])) + " " + this.field_146336_i, width / 2 - 100, 85, -6250336);
/* 480 */       this.field_146333_g.drawTextBox();
/* 481 */       drawString(fontRendererObj, this.field_146323_G, width / 2 - 100, 137, -6250336);
/* 482 */       drawString(fontRendererObj, this.field_146328_H, width / 2 - 100, 149, -6250336);
/*     */     } 
/*     */     
/* 485 */     super.drawScreen(mouseX, mouseY, partialTicks);
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_146318_a(WorldInfo p_146318_1_) {
/* 490 */     this.field_146330_J = I18n.format("selectWorld.newWorld.copyOf", new Object[] { p_146318_1_.getWorldName() });
/* 491 */     this.field_146329_I = (new StringBuilder(String.valueOf(p_146318_1_.getSeed()))).toString();
/* 492 */     this.field_146331_K = p_146318_1_.getTerrainType().getWorldTypeID();
/* 493 */     this.field_146334_a = p_146318_1_.getGeneratorOptions();
/* 494 */     this.field_146341_s = p_146318_1_.isMapFeaturesEnabled();
/* 495 */     this.field_146340_t = p_146318_1_.areCommandsAllowed();
/*     */     
/* 497 */     if (p_146318_1_.isHardcoreModeEnabled()) {
/*     */       
/* 499 */       this.field_146342_r = "hardcore";
/*     */     }
/* 501 */     else if (p_146318_1_.getGameType().isSurvivalOrAdventure()) {
/*     */       
/* 503 */       this.field_146342_r = "survival";
/*     */     }
/* 505 */     else if (p_146318_1_.getGameType().isCreative()) {
/*     */       
/* 507 */       this.field_146342_r = "creative";
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\gui\GuiCreateWorld.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */