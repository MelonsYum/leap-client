/*     */ package leap.ui;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.util.Comparator;
/*     */ import leap.Client;
/*     */ import leap.modules.Module;
/*     */ import leap.modules.render.ClickGUI;
/*     */ import leap.notifications.Direction;
/*     */ import leap.notifications.SmoothStepAnimation;
/*     */ import leap.settings.BooleanSetting;
/*     */ import leap.settings.KeyBindSetting;
/*     */ import leap.settings.ModeSetting;
/*     */ import leap.settings.NumberSetting;
/*     */ import leap.settings.Setting;
/*     */ import leap.util.ColorManager;
/*     */ import leap.util.JColor;
/*     */ import leap.util.Shape;
/*     */ import leap.util.font.CustomFontRenderer;
/*     */ import net.minecraft.client.gui.Gui;
/*     */ import net.minecraft.client.gui.GuiScreen;
/*     */ import net.minecraft.client.renderer.GlStateManager;
/*     */ import net.minecraft.client.shader.ShaderGroup;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import org.lwjgl.input.Keyboard;
/*     */ import org.lwjgl.input.Mouse;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ClickGUIScreen
/*     */   extends GuiScreen
/*     */ {
/*     */   public static Module.Category dragging;
/*     */   public static int startX;
/*     */   public static int startY;
/*     */   public static Module binding;
/*     */   public static NumberSetting draggingNumber;
/*     */   public int accent;
/*     */   public static int modBackground;
/*     */   public int settingsBackground;
/*     */   public int bindingBackground;
/*     */   
/*     */   public ClickGUIScreen() {
/*  47 */     this.openingAnimation = new SmoothStepAnimation(335, 1.0D, (Enum)Direction.FORWARDS);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  53 */     this.Slider = new ResourceLocation("pics/Slider.png");
/*     */   } public int sliderColor; public int categoryColor; public int enabledColor; public int textColor; SmoothStepAnimation openingAnimation; private static ShaderGroup shaderGroup; CustomFontRenderer fr; CustomFontRenderer small; double categoryPos; ResourceLocation Slider;
/*     */   public static int applyOpacity(int color, float opacity) {
/*  56 */     Color old = new Color(color);
/*  57 */     return (new Color(old.getRed(), old.getGreen(), old.getBlue(), (int)(opacity * (color >> 24 & 0xFF)))).getRGB();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void drawScreen(int mouseX, int mouseY, float partialTicks) {
/*  63 */     handle(mouseX, mouseY, -1, Handle.DRAW);
/*     */   }
/*     */   
/*     */   public void mouseClicked(int mouseX, int mouseY, int button) {
/*  67 */     handle(mouseX, mouseY, button, Handle.CLICK);
/*     */   }
/*     */   
/*     */   public void mouseReleased(int mouseX, int mouseY, int button) {
/*  71 */     handle(mouseX, mouseY, button, Handle.RELEASE);
/*     */   }
/*     */   
/*     */   public void keyTyped(char character, int key) {
/*  75 */     if (key == Client.clickGui.getKey() || key == 1) {
/*     */ 
/*     */       
/*  78 */       this.mc.displayGuiScreen(null);
/*  79 */       if (this.mc.currentScreen == null) {
/*  80 */         this.mc.setIngameFocus();
/*     */       }
/*     */     } 
/*     */     
/*  84 */     if (binding != null) {
/*  85 */       if (key == 57 || key == 1 || key == 211) {
/*  86 */         binding.keyCode.setKeyCode(0);
/*     */       } else {
/*  88 */         binding.keyCode.setKeyCode(key);
/*     */       } 
/*  90 */       binding = null;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void handle(int mouseX, int mouseY, int button, Handle type) {
/*  95 */     GL11.glPushMatrix();
/*  96 */     GlStateManager.pushMatrix();
/*     */     
/*  98 */     Shape.color((new Color(50, 50, 50, 50)).getRGB());
/*  99 */     Shape.drawRoundedRect(5.0D, 1.0D, (GuiScreen.width - 12), 40.0D, 4.0F);
/*     */     
/* 101 */     Client.customFontRenderer.drawString("Click GUI", (GuiScreen.width / 2), 8.0D, new JColor(new Color(120, 200, 255)));
/*     */     
/* 103 */     Shape.color((new Color(255, 200, 120)).getRGB());
/* 104 */     Shape.drawRoundedRect((GuiScreen.width / 2 - 20), 24.0D, 100.0D, 10.0D, 1.0F);
/*     */ 
/*     */     
/* 107 */     if (!this.openingAnimation.isDone()) {
/* 108 */       GL11.glEnable(3089);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 118 */     this.fr = Client.customFontRenderer;
/* 119 */     Client.modules.sort(Comparator.comparingDouble(m -> {
/*     */             String modulesText = ((Module)m).name;
/*     */ 
/*     */ 
/*     */             
/*     */             return this.fr.getStringWidth(modulesText);
/* 125 */           }).reversed());
/*     */     
/* 127 */     if (type == Handle.RELEASE && button == 0) {
/* 128 */       dragging = null;
/* 129 */       draggingNumber = null;
/* 130 */       if (binding != null) {
/* 131 */         binding = null;
/*     */       }
/*     */     } 
/* 134 */     if (dragging != null) {
/* 135 */       dragging.posX = mouseX - startX;
/* 136 */       dragging.posY = mouseY - startY;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 141 */     ClickGUI clickGui = Client.clickGui;
/* 142 */     String theme = clickGui.theme.getMode();
/* 143 */     float offset = (float)clickGui.offset.getValue();
/* 144 */     float corners = (float)clickGui.corners.getValue();
/* 145 */     float red = (float)(clickGui.red.getValue() / 255.0D);
/* 146 */     float green = (float)(clickGui.green.getValue() / 255.0D);
/* 147 */     float blue = (float)(clickGui.blue.getValue() / 255.0D);
/* 148 */     float rainbowSpeed = (float)clickGui.rainbowSpeed.getValue();
/* 149 */     boolean custom = clickGui.custom.isEnabled();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 156 */     this.small = Client.customFontRendererSmall;
/* 157 */     this.categoryPos = 4.5D;
/*     */ 
/*     */ 
/*     */     
/* 161 */     if (theme.equals("Custom Color") && 
/* 162 */       red * 255.0F + green * 255.0F >= 445.0F) {
/* 163 */       this.categoryColor = -16777216;
/*     */     }
/*     */     
/* 166 */     if (theme.equals("Leap")) {
/* 167 */       this.categoryColor = (new Color(214, 215, 220)).getRGB();
/*     */     } else {
/* 169 */       this.categoryColor = -1;
/*     */     } 
/*     */ 
/*     */     
/* 173 */     this.textColor = -1;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 179 */     if (theme.equals("Custom Color")) {
/* 180 */       this.accent = applyOpacity((new Color(red, green, blue, 1.0F)).getRGB(), 1.0F);
/* 181 */       if (!custom) {
/* 182 */         modBackground = applyOpacity((new Color(red, green, blue, 0.8F)).darker().getRGB(), 0.8F);
/* 183 */         this.settingsBackground = applyOpacity((new Color(red, green, blue, 0.75F)).darker().getRGB(), 0.8F);
/* 184 */         this.bindingBackground = applyOpacity((new Color(red, green, blue, 1.0F)).getRGB(), 1.0F);
/* 185 */         this.sliderColor = applyOpacity((new Color(red, green, blue, 1.0F)).getRGB(), 1.0F);
/*     */       } else {
/* 187 */         modBackground = applyOpacity((new Color(40, 50, 50)).darker().getRGB(), 0.8F);
/* 188 */         this.settingsBackground = applyOpacity((new Color(41, 43, 42)).darker().getRGB(), 0.7F);
/* 189 */         this.bindingBackground = -1;
/* 190 */         this.sliderColor = -1;
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 195 */     if (theme.equals("Leap")) {
/* 196 */       this.accent = (new Color(32, 34, 37, 255)).getRGB();
/* 197 */       modBackground = (new Color(40, 40, 40, 255)).getRGB();
/* 198 */       this.settingsBackground = (new Color(54, 57, 63, 255)).getRGB();
/* 199 */       this.bindingBackground = (new Color(34, 36, 40, 255)).getRGB();
/* 200 */       this.sliderColor = (new Color(255, 255, 255, 255)).getRGB();
/* 201 */       this.textColor = (new Color(214, 215, 220)).getRGB();
/*     */     } 
/*     */     
/*     */     byte b;
/*     */     int i;
/*     */     Module.Category[] arrayOfCategory;
/* 207 */     for (i = (arrayOfCategory = Module.Category.values()).length, b = 0; b < i; ) { String categoryText; Module.Category category = arrayOfCategory[b];
/* 208 */       float left = category.posX;
/* 209 */       float top = category.posY;
/* 210 */       float width = 90.0F;
/* 211 */       float right = left + width;
/*     */       
/* 213 */       boolean hoveringCategory = isHovered(left, top, left + width, top + 20.0F, mouseX, mouseY);
/*     */       
/* 215 */       if (type == Handle.CLICK && hoveringCategory && button == 0) {
/* 216 */         dragging = category;
/* 217 */         startX = mouseX - category.posX;
/* 218 */         startY = mouseY - category.posY;
/*     */         
/*     */         return;
/*     */       } 
/* 222 */       if (type == Handle.CLICK && hoveringCategory && button == 1) {
/* 223 */         category.expanded = !category.expanded;
/*     */         return;
/*     */       } 
/* 226 */       int count = 0;
/* 227 */       if (theme.equals("Chill Rainbow")) {
/* 228 */         this.accent = ColorManager.rainbow(0, (int)rainbowSpeed, 0.5F, 1.0F, 1.0F).getRGB();
/*     */       }
/* 230 */       if (theme.equals("Rainbow")) {
/* 231 */         this.accent = ColorManager.rainbow(0, (int)rainbowSpeed, 1.0F, 1.0F, 1.0F).getRGB();
/*     */       }
/*     */ 
/*     */ 
/*     */       
/* 236 */       boolean textEnabled = HUD.text.isEnabled();
/*     */ 
/*     */       
/* 239 */       if (textEnabled) {
/* 240 */         categoryText = category.name.toLowerCase();
/*     */       } else {
/* 242 */         categoryText = category.name;
/*     */       } 
/*     */ 
/*     */       
/* 246 */       GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
/* 247 */       Gui.drawRect(0.0D, 0.0D, 0.0D, 0.0D, this.accent);
/*     */ 
/*     */ 
/*     */       
/* 251 */       Shape.drawRoundedRect(left - 0.2D, (top - 1.0F), width, (width - 69.0F), 0.0F);
/* 252 */       this.fr.drawCenteredString(categoryText, left + width / 2.0F, (float)(top + this.categoryPos) + 1.0F, new JColor(this.categoryColor));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 258 */       int lastModBackground = -1;
/*     */       
/* 260 */       boolean isGradient = false;
/*     */       
/* 262 */       if (category.expanded)
/* 263 */         for (Module m : Client.getModulesByCategory(category)) {
/* 264 */           String textModule; boolean hoveringModule = isHovered(left, top + 20.0F + (count * 20), left + width, top + 20.0F + (count * 20) + 20.0F, mouseX, mouseY);
/*     */           
/* 266 */           if (type == Handle.CLICK && hoveringModule && button == 0) {
/* 267 */             m.toggle();
/*     */             
/*     */             return;
/*     */           } 
/* 271 */           if (type == Handle.CLICK && hoveringModule && button == 1) {
/* 272 */             m.expanded = !m.expanded;
/*     */             
/*     */             return;
/*     */           } 
/* 276 */           if (theme.contains("Rainbow")) {
/* 277 */             isGradient = true;
/* 278 */             if (lastModBackground != -1) {
/* 279 */               lastModBackground = modBackground;
/*     */             }
/* 281 */             modBackground = ColorManager.rainbow((int)offset * count, (int)rainbowSpeed, theme.equals("Chill Rainbow") ? 0.5F : 1.0F, 0.8F, 1.0F).getRGB();
/*     */             
/* 283 */             if (lastModBackground == -1) {
/* 284 */               lastModBackground = modBackground;
/*     */             }
/*     */           } 
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
/*     */ 
/*     */ 
/*     */           
/* 303 */           if (isGradient) {
/* 304 */             drawGradientRect(left, top + 20.0F + (count * 20), left + width, top + 20.0F + (count * 20) + 20.0F, lastModBackground, modBackground);
/*     */           } else {
/* 306 */             Gui.drawRect(left, (top + 20.0F + (count * 20)), (left + width), (top + 20.0F + (count * 20) + 20.0F), modBackground);
/*     */           } 
/* 308 */           Gui.drawRect(0.0D, 0.0D, 0.0D, 0.0D, this.accent);
/* 309 */           if (clickGui.theme.getMode() != "Leap") {
/* 310 */             Shape.drawRoundedRect(left - 1.5D, (top + 16.0F), (width + 3.0F), (width - 84.0F), corners / 4.0F);
/*     */           }
/*     */ 
/*     */           
/* 314 */           if (textEnabled) {
/* 315 */             textModule = m.name.toLowerCase();
/*     */           } else {
/* 317 */             textModule = m.name;
/*     */           } 
/* 319 */           if (!m.isEnabled() || m.name.equals("HUD") || m.name.equals("ArrayList")) {
/* 320 */             this.fr.drawString(textModule, (left + 5.0F), (top + 25.5F + (count * 20)), new JColor(this.textColor));
/*     */           }
/*     */           
/* 323 */           int toggledColor = -1;
/*     */ 
/*     */           
/* 326 */           if (theme.equals("Custom Color")) {
/* 327 */             toggledColor = applyOpacity((new Color(red, green, blue, 1.0F)).getRGB(), 1.0F);
/* 328 */           } else if (theme.equals("Leap")) {
/* 329 */             toggledColor = (new Color(0, 160, 255)).getRGB();
/*     */           } 
/*     */           
/* 332 */           if (m.isEnabled() && !m.name.equals("HUD") && !m.name.equals("ArrayList")) {
/* 333 */             if (theme.equals("Rainbow") || theme.equals("Chill Rainbow"))
/*     */             {
/* 335 */               if (!m.name.equals("Appearance")) {
/* 336 */                 Shape.color(toggledColor);
/* 337 */                 Shape.drawRoundedRect(left, (top + 19.5F + (count * 20)), 90.0D, 20.0D, 0.0F);
/*     */               } 
/*     */             }
/* 340 */             Gui.drawRect(left, (top + 20.0F + (count * 20)), (left + width), (top + 20.0F + (count * 20) + 20.0F), toggledColor);
/*     */ 
/*     */             
/* 343 */             this.fr.drawString(textModule, (left + 5.0F), (top + 25.5F + (count * 20)), new JColor(this.textColor));
/*     */           } 
/*     */ 
/*     */ 
/*     */           
/* 348 */           count++;
/*     */           
/* 350 */           lastModBackground = modBackground;
/*     */           
/* 352 */           if (m.expanded) {
/* 353 */             for (Setting setting : m.settings) {
/*     */               String settingText;
/* 355 */               if (theme.equals("Rainbow")) {
/* 356 */                 if (lastModBackground != modBackground) {
/* 357 */                   lastModBackground = this.settingsBackground;
/*     */                 }
/* 359 */                 this.settingsBackground = ColorManager.rainbow((int)offset * count, (int)rainbowSpeed, 1.0F, 0.6F, 1.0F).getRGB();
/* 360 */                 this.bindingBackground = ColorManager.rainbow((int)offset * count, (int)rainbowSpeed, 0.5F, 1.0F, 1.0F).getRGB();
/* 361 */                 this.sliderColor = ColorManager.rainbow((int)offset * count, (int)rainbowSpeed, 0.5F, 1.0F, 1.0F).getRGB();
/*     */                 
/* 363 */                 if (lastModBackground == modBackground) {
/* 364 */                   lastModBackground = this.settingsBackground;
/*     */                 }
/*     */               } 
/* 367 */               if (theme.equals("Chill Rainbow")) {
/* 368 */                 if (lastModBackground != modBackground) {
/* 369 */                   lastModBackground = this.settingsBackground;
/*     */                 }
/* 371 */                 this.settingsBackground = ColorManager.rainbow((int)offset * count, (int)rainbowSpeed, 0.5F, 0.6F, 1.0F).getRGB();
/* 372 */                 this.bindingBackground = ColorManager.rainbow((int)offset * count, (int)rainbowSpeed, 0.5F, 0.5F, 1.0F).getRGB();
/* 373 */                 this.sliderColor = ColorManager.rainbow((int)offset * count, (int)rainbowSpeed, 0.5F, 1.0F, 1.0F).getRGB();
/*     */                 
/* 375 */                 if (lastModBackground == modBackground)
/* 376 */                   lastModBackground = this.settingsBackground; 
/*     */               } 
/* 378 */               boolean hoveringSetting = isHovered(left, top + 20.0F + (count * 20), left + width, top + 20.0F + (count * 20) + 20.0F, mouseX, mouseY);
/*     */ 
/*     */               
/* 381 */               if (setting instanceof BooleanSetting) {
/* 382 */                 BooleanSetting bool = (BooleanSetting)setting;
/*     */                 
/* 384 */                 if (isGradient) {
/* 385 */                   drawGradientRect(left, top + 20.0F + (count * 20), left + width, top + 20.0F + (count * 20) + 20.0F, lastModBackground, this.settingsBackground);
/*     */                 } else {
/* 387 */                   Gui.drawRect(left, (top + 20.0F + (count * 20)), (left + width), (top + 20.0F + (count * 20) + 20.0F), this.settingsBackground);
/*     */                 } 
/* 389 */                 String title = bool.isEnabled() ? "On" : "Off";
/*     */ 
/*     */                 
/* 392 */                 if (type == Handle.CLICK && hoveringSetting && button == 0) {
/* 393 */                   bool.toggle();
/*     */                 }
/*     */ 
/*     */                 
/* 397 */                 if (textEnabled) {
/* 398 */                   title = title.toLowerCase();
/*     */                 }
/* 400 */                 this.small.drawString(title, (right - 5.0F - this.small.getStringWidth(title)), (top + 31.0F + (count * 20)), new JColor(-1));
/*     */               } 
/* 402 */               if (setting instanceof NumberSetting) {
/* 403 */                 NumberSetting number = (NumberSetting)setting;
/* 404 */                 float percent = (float)((number.getValue() - number.getMinimum()) / (number.getMaximum() - number.getMinimum()));
/* 405 */                 float numberWidth = percent * width;
/*     */                 
/* 407 */                 if (draggingNumber != null && draggingNumber == number) {
/* 408 */                   double mousePercent = Math.min(1.0F, Math.max(0.0F, (mouseX - left) / width));
/* 409 */                   double newValue = mousePercent * (number.getMaximum() - number.getMinimum()) + number.getMinimum();
/* 410 */                   number.setValue(newValue);
/*     */                 } 
/*     */                 
/* 413 */                 if (isGradient) {
/* 414 */                   drawGradientRect(left, top + 20.0F + (count * 20), left + width, top + 20.0F + (count * 20) + 20.0F, lastModBackground, this.settingsBackground);
/*     */                 } else {
/* 416 */                   Gui.drawRect(left, (top + 20.0F + (count * 20)), (left + width), (top + 20.0F + (count * 20) + 20.0F), this.settingsBackground);
/*     */                 } 
/*     */                 
/* 419 */                 Gui.drawRect(left, (top + 35.0F + (count * 20)), (left + numberWidth), (top + 18.0F + (count * 20) + 20.0F), this.sliderColor);
/*     */ 
/*     */                 
/* 422 */                 int colorslider = -1;
/* 423 */                 Gui.drawRect(0.0D, 0.0D, 0.0D, 0.0D, colorslider);
/*     */ 
/*     */                 
/* 426 */                 GlStateManager.enableBlend();
/*     */                 
/* 428 */                 this.mc.getTextureManager().bindTexture(this.Slider);
/* 429 */                 Gui.drawModalRectWithCustomSizedTexture(Math.min((int)(left + numberWidth), left + width - 5.0F), (int)(top + 34.0F + (count * 20)), 0.0F, 0.0F, 5.0F, 5.0F, 5.0F, 5.0F);
/*     */ 
/*     */                 
/* 432 */                 String title = String.valueOf(number.getValue());
/*     */                 
/* 434 */                 if (type == Handle.CLICK && hoveringSetting && button == 0) {
/* 435 */                   draggingNumber = number;
/*     */                 }
/*     */                 
/* 438 */                 if (textEnabled) {
/* 439 */                   title = title.toLowerCase();
/*     */                 }
/* 441 */                 this.small.drawString(title, (right - 3.0F - this.small.getStringWidth(title)), (top + 28.0F + (count * 20)), new JColor(-1));
/*     */               } 
/* 443 */               if (setting instanceof ModeSetting) {
/* 444 */                 ModeSetting mode = (ModeSetting)setting;
/*     */                 
/* 446 */                 if (isGradient) {
/* 447 */                   drawGradientRect(left, top + 20.0F + (count * 20), left + width, top + 20.0F + (count * 20) + 20.0F, lastModBackground, this.settingsBackground);
/*     */                 } else {
/* 449 */                   Gui.drawRect(left, (top + 20.0F + (count * 20)), (left + width), (top + 20.0F + (count * 20) + 20.0F), this.settingsBackground);
/*     */                 } 
/* 451 */                 String title = mode.getMode();
/*     */                 
/* 453 */                 if (type == Handle.CLICK && hoveringSetting && button == 0) {
/* 454 */                   mode.cycle();
/*     */                 }
/*     */                 
/* 457 */                 if (textEnabled) {
/* 458 */                   title = title.toLowerCase();
/*     */                 }
/* 460 */                 this.small.drawString(title, (right - 5.0F - this.small.getStringWidth(title)), (top + 31.0F + (count * 20)), new JColor(-1));
/*     */               } 
/* 462 */               if (setting instanceof KeyBindSetting) {
/* 463 */                 KeyBindSetting bind = (KeyBindSetting)setting;
/*     */                 
/* 465 */                 if (isGradient) {
/* 466 */                   drawGradientRect(left, top + 20.0F + (count * 20), left + width, top + 20.0F + (count * 20) + 20.0F, lastModBackground, this.settingsBackground);
/*     */                 } else {
/* 468 */                   Gui.drawRect(left, (top + 20.0F + (count * 20)), (left + width), (top + 20.0F + (count * 20) + 20.0F), (binding == m) ? this.bindingBackground : this.settingsBackground);
/*     */                 } 
/* 470 */                 String title = Keyboard.getKeyName(bind.getKeyCode());
/*     */                 
/* 472 */                 if (type == Handle.CLICK && hoveringSetting && button == 0) {
/* 473 */                   binding = m;
/*     */                 }
/*     */                 
/* 476 */                 if (textEnabled) {
/* 477 */                   title = title.toLowerCase();
/*     */                 }
/* 479 */                 this.small.drawString(title, (right - 5.0F - this.small.getStringWidth(title)), (top + 31.0F + (count * 20)), new JColor(-1));
/*     */               } 
/*     */ 
/*     */               
/* 483 */               if (textEnabled) {
/* 484 */                 settingText = setting.name.toLowerCase();
/*     */               } else {
/* 486 */                 settingText = setting.name;
/*     */               } 
/* 488 */               this.small.drawString(settingText, (left + 5.0F), (top + 25.0F + (count * 20)), new JColor(this.textColor));
/*     */               
/* 490 */               count++;
/*     */               
/* 492 */               if (hoveringSetting) {
/* 493 */                 Gui.drawRect(left, (top + (count * 20)), (left + width), (top + 20.0F + (count * 20)), (new Color(0, 0, 0, 30)).getRGB());
/*     */               }
/*     */             } 
/*     */ 
/*     */             
/* 498 */             if (isGradient) {
/* 499 */               modBackground = ColorManager.rainbow((int)offset * count, (int)rainbowSpeed, theme.equals("Chill Rainbow") ? 0.5F : 1.0F, 0.8F, 1.0F).getRGB();
/*     */             }
/*     */           } 
/* 502 */           if (hoveringModule && !m.expanded) {
/* 503 */             Gui.drawRect(left, (top + (count * 20)), (left + width), (top + 20.0F + (count * 20)), (new Color(0, 0, 0, 30)).getRGB());
/*     */           }
/*     */         }  
/* 506 */       left += 105.0F;
/*     */       b++; }
/*     */     
/* 509 */     if (type == Handle.CLICK && button == 0) {
/* 510 */       dragging = null;
/*     */     }
/*     */ 
/*     */     
/* 514 */     Client.modules.sort(Comparator.comparingDouble(m -> {
/*     */             String modulesText = ((Module)m).name;
/*     */ 
/*     */ 
/*     */             
/*     */             return this.fr.getStringWidth(modulesText);
/* 520 */           }).reversed());
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 525 */     Shape.color((new Color(255, 255, 255, 40)).getRGB());
/* 526 */     Shape.drawRoundedRect(7.0D, 69.0D, 67.0D, 19.0D, 4.0F);
/*     */     
/* 528 */     Shape.color((new Color(10, 10, 10, 90)).getRGB());
/* 529 */     Shape.drawRoundedRect(8.0D, 70.0D, 65.0D, 17.0D, 4.0F);
/*     */     
/* 531 */     Client.customFontRendererBig.drawString("Hud Editor", 10.0D, 74.0D, new JColor(Color.white.getRGB()));
/* 532 */     if (type == Handle.CLICK && isHovered(8.0F, 8.0F, 135.0F, 87.0F, mouseX, mouseY) && button == 0 && Mouse.isCreated() && 
/* 533 */       Mouse.isButtonDown(0)) {
/* 534 */       this.mc.displayGuiScreen(new HudEdit());
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 539 */     GL11.glDisable(3089);
/*     */     
/* 541 */     GlStateManager.popMatrix();
/* 542 */     GL11.glPopMatrix();
/*     */   }
/*     */   
/*     */   public boolean doesGuiPauseGame() {
/* 546 */     return false;
/*     */   }
/*     */   
/*     */   public static boolean isHovered(float left, float top, float right, float bottom, int mouseX, int mouseY) {
/* 550 */     return (mouseX >= left && mouseY >= top && mouseX < right && mouseY < bottom);
/*     */   }
/*     */   
/*     */   enum Handle {
/* 554 */     DRAW,
/* 555 */     CLICK,
/* 556 */     RELEASE; }
/*     */   
/*     */   enum Categorys {
/* 559 */     COMBAT,
/* 560 */     MOVEMENT,
/* 561 */     PLAYER,
/* 562 */     RENDER,
/* 563 */     MEMES,
/* 564 */     GHOST,
/* 565 */     CONFIGS;
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\lea\\ui\ClickGUIScreen.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */