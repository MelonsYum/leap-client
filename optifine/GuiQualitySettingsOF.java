/*    */ package optifine;
/*    */ 
/*    */ import net.minecraft.client.gui.GuiButton;
/*    */ import net.minecraft.client.gui.GuiOptionButton;
/*    */ import net.minecraft.client.gui.GuiScreen;
/*    */ import net.minecraft.client.gui.ScaledResolution;
/*    */ import net.minecraft.client.resources.I18n;
/*    */ import net.minecraft.client.settings.GameSettings;
/*    */ 
/*    */ public class GuiQualitySettingsOF
/*    */   extends GuiScreen {
/*    */   private GuiScreen prevScreen;
/*    */   protected String title;
/*    */   private GameSettings settings;
/* 15 */   private static GameSettings.Options[] enumOptions = new GameSettings.Options[] { GameSettings.Options.MIPMAP_LEVELS, GameSettings.Options.MIPMAP_TYPE, GameSettings.Options.AF_LEVEL, GameSettings.Options.AA_LEVEL, GameSettings.Options.CLEAR_WATER, GameSettings.Options.RANDOM_MOBS, GameSettings.Options.BETTER_GRASS, GameSettings.Options.BETTER_SNOW, GameSettings.Options.CUSTOM_FONTS, GameSettings.Options.CUSTOM_COLORS, GameSettings.Options.SWAMP_COLORS, GameSettings.Options.SMOOTH_BIOMES, GameSettings.Options.CONNECTED_TEXTURES, GameSettings.Options.NATURAL_TEXTURES, GameSettings.Options.CUSTOM_SKY, GameSettings.Options.CUSTOM_ITEMS, GameSettings.Options.DYNAMIC_LIGHTS };
/* 16 */   private TooltipManager tooltipManager = new TooltipManager(this);
/*    */ 
/*    */   
/*    */   public GuiQualitySettingsOF(GuiScreen guiscreen, GameSettings gamesettings) {
/* 20 */     this.prevScreen = guiscreen;
/* 21 */     this.settings = gamesettings;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void initGui() {
/* 29 */     this.title = I18n.format("of.options.qualityTitle", new Object[0]);
/* 30 */     this.buttonList.clear();
/*    */     
/* 32 */     for (int i = 0; i < enumOptions.length; i++) {
/*    */       
/* 34 */       GameSettings.Options opt = enumOptions[i];
/* 35 */       int x = width / 2 - 155 + i % 2 * 160;
/* 36 */       int y = height / 6 + 21 * i / 2 - 12;
/*    */       
/* 38 */       if (!opt.getEnumFloat()) {
/*    */         
/* 40 */         this.buttonList.add(new GuiOptionButtonOF(opt.returnEnumOrdinal(), x, y, opt, this.settings.getKeyBinding(opt)));
/*    */       }
/*    */       else {
/*    */         
/* 44 */         this.buttonList.add(new GuiOptionSliderOF(opt.returnEnumOrdinal(), x, y, opt));
/*    */       } 
/*    */     } 
/*    */     
/* 48 */     this.buttonList.add(new GuiButton(200, width / 2 - 100, height / 6 + 168 + 11, I18n.format("gui.done", new Object[0])));
/*    */   }
/*    */ 
/*    */   
/*    */   protected void actionPerformed(GuiButton guibutton) {
/* 53 */     if (guibutton.enabled) {
/*    */       
/* 55 */       if (guibutton.id < 200 && guibutton instanceof GuiOptionButton) {
/*    */         
/* 57 */         this.settings.setOptionValue(((GuiOptionButton)guibutton).returnEnumOptions(), 1);
/* 58 */         guibutton.displayString = this.settings.getKeyBinding(GameSettings.Options.getEnumOptions(guibutton.id));
/*    */       } 
/*    */       
/* 61 */       if (guibutton.id == 200) {
/*    */         
/* 63 */         this.mc.gameSettings.saveOptions();
/* 64 */         this.mc.displayGuiScreen(this.prevScreen);
/*    */       } 
/*    */       
/* 67 */       if (guibutton.id != GameSettings.Options.AA_LEVEL.ordinal()) {
/*    */         
/* 69 */         ScaledResolution sr = new ScaledResolution(this.mc, this.mc.displayWidth, this.mc.displayHeight);
/* 70 */         setWorldAndResolution(this.mc, sr.getScaledWidth(), sr.getScaledHeight());
/*    */       } 
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void drawScreen(int x, int y, float f) {
/* 80 */     drawDefaultBackground();
/* 81 */     drawCenteredString(fontRendererObj, this.title, (width / 2), 15.0F, 16777215);
/* 82 */     super.drawScreen(x, y, f);
/* 83 */     this.tooltipManager.drawTooltips(x, y, this.buttonList);
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\optifine\GuiQualitySettingsOF.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */