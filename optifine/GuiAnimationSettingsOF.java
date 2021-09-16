/*    */ package optifine;
/*    */ 
/*    */ import net.minecraft.client.gui.GuiButton;
/*    */ import net.minecraft.client.gui.GuiOptionButton;
/*    */ import net.minecraft.client.gui.GuiScreen;
/*    */ import net.minecraft.client.gui.ScaledResolution;
/*    */ import net.minecraft.client.resources.I18n;
/*    */ import net.minecraft.client.settings.GameSettings;
/*    */ 
/*    */ public class GuiAnimationSettingsOF
/*    */   extends GuiScreen {
/*    */   private GuiScreen prevScreen;
/*    */   protected String title;
/*    */   private GameSettings settings;
/* 15 */   private static GameSettings.Options[] enumOptions = new GameSettings.Options[] { GameSettings.Options.ANIMATED_WATER, GameSettings.Options.ANIMATED_LAVA, GameSettings.Options.ANIMATED_FIRE, GameSettings.Options.ANIMATED_PORTAL, GameSettings.Options.ANIMATED_REDSTONE, GameSettings.Options.ANIMATED_EXPLOSION, GameSettings.Options.ANIMATED_FLAME, GameSettings.Options.ANIMATED_SMOKE, GameSettings.Options.VOID_PARTICLES, GameSettings.Options.WATER_PARTICLES, GameSettings.Options.RAIN_SPLASH, GameSettings.Options.PORTAL_PARTICLES, GameSettings.Options.POTION_PARTICLES, GameSettings.Options.DRIPPING_WATER_LAVA, GameSettings.Options.ANIMATED_TERRAIN, GameSettings.Options.ANIMATED_TEXTURES, GameSettings.Options.FIREWORK_PARTICLES, GameSettings.Options.PARTICLES };
/*    */ 
/*    */   
/*    */   public GuiAnimationSettingsOF(GuiScreen guiscreen, GameSettings gamesettings) {
/* 19 */     this.prevScreen = guiscreen;
/* 20 */     this.settings = gamesettings;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void initGui() {
/* 28 */     this.title = I18n.format("of.options.animationsTitle", new Object[0]);
/* 29 */     this.buttonList.clear();
/*    */     
/* 31 */     for (int i = 0; i < enumOptions.length; i++) {
/*    */       
/* 33 */       GameSettings.Options opt = enumOptions[i];
/* 34 */       int x = width / 2 - 155 + i % 2 * 160;
/* 35 */       int y = height / 6 + 21 * i / 2 - 12;
/*    */       
/* 37 */       if (!opt.getEnumFloat()) {
/*    */         
/* 39 */         this.buttonList.add(new GuiOptionButtonOF(opt.returnEnumOrdinal(), x, y, opt, this.settings.getKeyBinding(opt)));
/*    */       }
/*    */       else {
/*    */         
/* 43 */         this.buttonList.add(new GuiOptionSliderOF(opt.returnEnumOrdinal(), x, y, opt));
/*    */       } 
/*    */     } 
/*    */     
/* 47 */     this.buttonList.add(new GuiButton(210, width / 2 - 155, height / 6 + 168 + 11, 70, 20, Lang.get("of.options.animation.allOn")));
/* 48 */     this.buttonList.add(new GuiButton(211, width / 2 - 155 + 80, height / 6 + 168 + 11, 70, 20, Lang.get("of.options.animation.allOff")));
/* 49 */     this.buttonList.add(new GuiOptionButton(200, width / 2 + 5, height / 6 + 168 + 11, I18n.format("gui.done", new Object[0])));
/*    */   }
/*    */ 
/*    */   
/*    */   protected void actionPerformed(GuiButton guibutton) {
/* 54 */     if (guibutton.enabled) {
/*    */       
/* 56 */       if (guibutton.id < 200 && guibutton instanceof GuiOptionButton) {
/*    */         
/* 58 */         this.settings.setOptionValue(((GuiOptionButton)guibutton).returnEnumOptions(), 1);
/* 59 */         guibutton.displayString = this.settings.getKeyBinding(GameSettings.Options.getEnumOptions(guibutton.id));
/*    */       } 
/*    */       
/* 62 */       if (guibutton.id == 200) {
/*    */         
/* 64 */         this.mc.gameSettings.saveOptions();
/* 65 */         this.mc.displayGuiScreen(this.prevScreen);
/*    */       } 
/*    */       
/* 68 */       if (guibutton.id == 210)
/*    */       {
/* 70 */         this.mc.gameSettings.setAllAnimations(true);
/*    */       }
/*    */       
/* 73 */       if (guibutton.id == 211)
/*    */       {
/* 75 */         this.mc.gameSettings.setAllAnimations(false);
/*    */       }
/*    */       
/* 78 */       ScaledResolution sr = new ScaledResolution(this.mc, this.mc.displayWidth, this.mc.displayHeight);
/* 79 */       setWorldAndResolution(this.mc, sr.getScaledWidth(), sr.getScaledHeight());
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void drawScreen(int x, int y, float f) {
/* 88 */     drawDefaultBackground();
/* 89 */     drawCenteredString(fontRendererObj, this.title, (width / 2), 15.0F, 16777215);
/* 90 */     super.drawScreen(x, y, f);
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\optifine\GuiAnimationSettingsOF.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */