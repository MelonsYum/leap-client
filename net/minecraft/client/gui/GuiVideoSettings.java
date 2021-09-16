/*     */ package net.minecraft.client.gui;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import net.minecraft.client.resources.I18n;
/*     */ import net.minecraft.client.settings.GameSettings;
/*     */ import optifine.Config;
/*     */ import optifine.GuiAnimationSettingsOF;
/*     */ import optifine.GuiDetailSettingsOF;
/*     */ import optifine.GuiOptionButtonOF;
/*     */ import optifine.GuiOptionSliderOF;
/*     */ import optifine.GuiOtherSettingsOF;
/*     */ import optifine.GuiPerformanceSettingsOF;
/*     */ import optifine.GuiQualitySettingsOF;
/*     */ import optifine.Lang;
/*     */ import optifine.TooltipManager;
/*     */ import shadersmod.client.GuiShaders;
/*     */ 
/*     */ public class GuiVideoSettings
/*     */   extends GuiScreen {
/*     */   private GuiScreen parentGuiScreen;
/*  21 */   protected String screenTitle = "Video Settings";
/*     */   
/*     */   private GameSettings guiGameSettings;
/*     */   
/*  25 */   private static GameSettings.Options[] videoOptions = new GameSettings.Options[] { GameSettings.Options.GRAPHICS, GameSettings.Options.RENDER_DISTANCE, GameSettings.Options.AMBIENT_OCCLUSION, GameSettings.Options.FRAMERATE_LIMIT, GameSettings.Options.AO_LEVEL, GameSettings.Options.VIEW_BOBBING, GameSettings.Options.GUI_SCALE, GameSettings.Options.USE_VBO, GameSettings.Options.GAMMA, GameSettings.Options.BLOCK_ALTERNATIVES, GameSettings.Options.FOG_FANCY, GameSettings.Options.FOG_START };
/*     */   private static final String __OBFID = "CL_00000718";
/*  27 */   private TooltipManager tooltipManager = new TooltipManager(this);
/*     */ 
/*     */   
/*     */   public GuiVideoSettings(GuiScreen par1GuiScreen, GameSettings par2GameSettings) {
/*  31 */     this.parentGuiScreen = par1GuiScreen;
/*  32 */     this.guiGameSettings = par2GameSettings;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void initGui() {
/*  40 */     this.screenTitle = I18n.format("options.videoTitle", new Object[0]);
/*  41 */     this.buttonList.clear();
/*     */     
/*     */     int y;
/*  44 */     for (y = 0; y < videoOptions.length; y++) {
/*     */       
/*  46 */       GameSettings.Options x = videoOptions[y];
/*     */       
/*  48 */       if (x != null) {
/*     */         
/*  50 */         int x1 = width / 2 - 155 + y % 2 * 160;
/*  51 */         int y1 = height / 6 + 21 * y / 2 - 12;
/*     */         
/*  53 */         if (x.getEnumFloat()) {
/*     */           
/*  55 */           this.buttonList.add(new GuiOptionSliderOF(x.returnEnumOrdinal(), x1, y1, x));
/*     */         }
/*     */         else {
/*     */           
/*  59 */           this.buttonList.add(new GuiOptionButtonOF(x.returnEnumOrdinal(), x1, y1, x, this.guiGameSettings.getKeyBinding(x)));
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/*  64 */     y = height / 6 + 21 * videoOptions.length / 2 - 12;
/*  65 */     boolean var5 = false;
/*  66 */     int var6 = width / 2 - 155 + 0;
/*  67 */     this.buttonList.add(new GuiOptionButton(231, var6, y, Lang.get("of.options.shaders")));
/*  68 */     var6 = width / 2 - 155 + 160;
/*  69 */     this.buttonList.add(new GuiOptionButton(202, var6, y, Lang.get("of.options.quality")));
/*  70 */     y += 21;
/*  71 */     var6 = width / 2 - 155 + 0;
/*  72 */     this.buttonList.add(new GuiOptionButton(201, var6, y, Lang.get("of.options.details")));
/*  73 */     var6 = width / 2 - 155 + 160;
/*  74 */     this.buttonList.add(new GuiOptionButton(212, var6, y, Lang.get("of.options.performance")));
/*  75 */     y += 21;
/*  76 */     var6 = width / 2 - 155 + 0;
/*  77 */     this.buttonList.add(new GuiOptionButton(211, var6, y, Lang.get("of.options.animations")));
/*  78 */     var6 = width / 2 - 155 + 160;
/*  79 */     this.buttonList.add(new GuiOptionButton(222, var6, y, Lang.get("of.options.other")));
/*  80 */     y += 21;
/*  81 */     this.buttonList.add(new GuiButton(200, width / 2 - 100, height / 6 + 168 + 11, I18n.format("gui.done", new Object[0])));
/*     */   }
/*     */ 
/*     */   
/*     */   protected void actionPerformed(GuiButton button) throws IOException {
/*  86 */     if (button.enabled) {
/*     */       
/*  88 */       int guiScale = this.guiGameSettings.guiScale;
/*     */       
/*  90 */       if (button.id < 200 && button instanceof GuiOptionButton) {
/*     */         
/*  92 */         this.guiGameSettings.setOptionValue(((GuiOptionButton)button).returnEnumOptions(), 1);
/*  93 */         button.displayString = this.guiGameSettings.getKeyBinding(GameSettings.Options.getEnumOptions(button.id));
/*     */       } 
/*     */       
/*  96 */       if (button.id == 200) {
/*     */         
/*  98 */         this.mc.gameSettings.saveOptions();
/*  99 */         this.mc.displayGuiScreen(this.parentGuiScreen);
/*     */       } 
/*     */       
/* 102 */       if (this.guiGameSettings.guiScale != guiScale) {
/*     */         
/* 104 */         ScaledResolution scr = new ScaledResolution(this.mc, this.mc.displayWidth, this.mc.displayHeight);
/* 105 */         int var4 = scr.getScaledWidth();
/* 106 */         int var5 = scr.getScaledHeight();
/* 107 */         setWorldAndResolution(this.mc, var4, var5);
/*     */       } 
/*     */       
/* 110 */       if (button.id == 201) {
/*     */         
/* 112 */         this.mc.gameSettings.saveOptions();
/* 113 */         GuiDetailSettingsOF scr1 = new GuiDetailSettingsOF(this, this.guiGameSettings);
/* 114 */         this.mc.displayGuiScreen((GuiScreen)scr1);
/*     */       } 
/*     */       
/* 117 */       if (button.id == 202) {
/*     */         
/* 119 */         this.mc.gameSettings.saveOptions();
/* 120 */         GuiQualitySettingsOF scr2 = new GuiQualitySettingsOF(this, this.guiGameSettings);
/* 121 */         this.mc.displayGuiScreen((GuiScreen)scr2);
/*     */       } 
/*     */       
/* 124 */       if (button.id == 211) {
/*     */         
/* 126 */         this.mc.gameSettings.saveOptions();
/* 127 */         GuiAnimationSettingsOF scr3 = new GuiAnimationSettingsOF(this, this.guiGameSettings);
/* 128 */         this.mc.displayGuiScreen((GuiScreen)scr3);
/*     */       } 
/*     */       
/* 131 */       if (button.id == 212) {
/*     */         
/* 133 */         this.mc.gameSettings.saveOptions();
/* 134 */         GuiPerformanceSettingsOF scr4 = new GuiPerformanceSettingsOF(this, this.guiGameSettings);
/* 135 */         this.mc.displayGuiScreen((GuiScreen)scr4);
/*     */       } 
/*     */       
/* 138 */       if (button.id == 222) {
/*     */         
/* 140 */         this.mc.gameSettings.saveOptions();
/* 141 */         GuiOtherSettingsOF scr5 = new GuiOtherSettingsOF(this, this.guiGameSettings);
/* 142 */         this.mc.displayGuiScreen((GuiScreen)scr5);
/*     */       } 
/*     */       
/* 145 */       if (button.id == 231) {
/*     */         
/* 147 */         if (Config.isAntialiasing() || Config.isAntialiasingConfigured()) {
/*     */           
/* 149 */           Config.showGuiMessage(Lang.get("of.message.shaders.aa1"), Lang.get("of.message.shaders.aa2"));
/*     */           
/*     */           return;
/*     */         } 
/* 153 */         if (Config.isAnisotropicFiltering()) {
/*     */           
/* 155 */           Config.showGuiMessage(Lang.get("of.message.shaders.af1"), Lang.get("of.message.shaders.af2"));
/*     */           
/*     */           return;
/*     */         } 
/* 159 */         if (Config.isFastRender()) {
/*     */           
/* 161 */           Config.showGuiMessage(Lang.get("of.message.shaders.fr1"), Lang.get("of.message.shaders.fr2"));
/*     */           
/*     */           return;
/*     */         } 
/* 165 */         this.mc.gameSettings.saveOptions();
/* 166 */         GuiShaders scr6 = new GuiShaders(this, this.guiGameSettings);
/* 167 */         this.mc.displayGuiScreen((GuiScreen)scr6);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void drawScreen(int x, int y, float z) {
/* 177 */     drawDefaultBackground();
/* 178 */     drawCenteredString(fontRendererObj, this.screenTitle, (width / 2), 15.0F, 16777215);
/* 179 */     String ver = Config.getVersion();
/* 180 */     String ed = "HD_U";
/*     */     
/* 182 */     if (ed.equals("HD"))
/*     */     {
/* 184 */       ver = "OptiFine HD H6";
/*     */     }
/*     */     
/* 187 */     if (ed.equals("HD_U"))
/*     */     {
/* 189 */       ver = "OptiFine HD H6 Ultra";
/*     */     }
/*     */     
/* 192 */     if (ed.equals("L"))
/*     */     {
/* 194 */       ver = "OptiFine H6 Light";
/*     */     }
/*     */     
/* 197 */     drawString(fontRendererObj, ver, 2, height - 10, 8421504);
/* 198 */     String verMc = "Minecraft 1.8";
/* 199 */     int lenMc = fontRendererObj.getStringWidth(verMc);
/* 200 */     drawString(fontRendererObj, verMc, width - lenMc - 2, height - 10, 8421504);
/* 201 */     super.drawScreen(x, y, z);
/* 202 */     this.tooltipManager.drawTooltips(x, y, this.buttonList);
/*     */   }
/*     */ 
/*     */   
/*     */   public static int getButtonWidth(GuiButton btn) {
/* 207 */     return btn.width;
/*     */   }
/*     */ 
/*     */   
/*     */   public static int getButtonHeight(GuiButton btn) {
/* 212 */     return btn.height;
/*     */   }
/*     */ 
/*     */   
/*     */   public static void drawGradientRect(GuiScreen guiScreen, int left, int top, int right, int bottom, int startColor, int endColor) {
/* 217 */     guiScreen.drawGradientRect(left, top, right, bottom, startColor, endColor);
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\gui\GuiVideoSettings.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */