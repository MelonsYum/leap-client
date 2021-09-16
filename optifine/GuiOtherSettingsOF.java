/*    */ package optifine;
/*    */ 
/*    */ import net.minecraft.client.gui.GuiButton;
/*    */ import net.minecraft.client.gui.GuiOptionButton;
/*    */ import net.minecraft.client.gui.GuiScreen;
/*    */ import net.minecraft.client.gui.GuiYesNo;
/*    */ import net.minecraft.client.gui.GuiYesNoCallback;
/*    */ import net.minecraft.client.resources.I18n;
/*    */ import net.minecraft.client.settings.GameSettings;
/*    */ 
/*    */ public class GuiOtherSettingsOF
/*    */   extends GuiScreen implements GuiYesNoCallback {
/*    */   private GuiScreen prevScreen;
/*    */   protected String title;
/*    */   private GameSettings settings;
/* 16 */   private static GameSettings.Options[] enumOptions = new GameSettings.Options[] { GameSettings.Options.LAGOMETER, GameSettings.Options.PROFILER, GameSettings.Options.WEATHER, GameSettings.Options.TIME, GameSettings.Options.USE_FULLSCREEN, GameSettings.Options.FULLSCREEN_MODE, GameSettings.Options.SHOW_FPS, GameSettings.Options.AUTOSAVE_TICKS, GameSettings.Options.ANAGLYPH };
/* 17 */   private TooltipManager tooltipManager = new TooltipManager(this);
/*    */ 
/*    */   
/*    */   public GuiOtherSettingsOF(GuiScreen guiscreen, GameSettings gamesettings) {
/* 21 */     this.prevScreen = guiscreen;
/* 22 */     this.settings = gamesettings;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void initGui() {
/* 30 */     this.title = I18n.format("of.options.otherTitle", new Object[0]);
/* 31 */     this.buttonList.clear();
/*    */     
/* 33 */     for (int i = 0; i < enumOptions.length; i++) {
/*    */       
/* 35 */       GameSettings.Options enumoptions = enumOptions[i];
/* 36 */       int x = width / 2 - 155 + i % 2 * 160;
/* 37 */       int y = height / 6 + 21 * i / 2 - 12;
/*    */       
/* 39 */       if (!enumoptions.getEnumFloat()) {
/*    */         
/* 41 */         this.buttonList.add(new GuiOptionButtonOF(enumoptions.returnEnumOrdinal(), x, y, enumoptions, this.settings.getKeyBinding(enumoptions)));
/*    */       }
/*    */       else {
/*    */         
/* 45 */         this.buttonList.add(new GuiOptionSliderOF(enumoptions.returnEnumOrdinal(), x, y, enumoptions));
/*    */       } 
/*    */     } 
/*    */     
/* 49 */     this.buttonList.add(new GuiButton(210, width / 2 - 100, height / 6 + 168 + 11 - 44, I18n.format("of.options.other.reset", new Object[0])));
/* 50 */     this.buttonList.add(new GuiButton(200, width / 2 - 100, height / 6 + 168 + 11, I18n.format("gui.done", new Object[0])));
/*    */   }
/*    */ 
/*    */   
/*    */   protected void actionPerformed(GuiButton guibutton) {
/* 55 */     if (guibutton.enabled) {
/*    */       
/* 57 */       if (guibutton.id < 200 && guibutton instanceof GuiOptionButton) {
/*    */         
/* 59 */         this.settings.setOptionValue(((GuiOptionButton)guibutton).returnEnumOptions(), 1);
/* 60 */         guibutton.displayString = this.settings.getKeyBinding(GameSettings.Options.getEnumOptions(guibutton.id));
/*    */       } 
/*    */       
/* 63 */       if (guibutton.id == 200) {
/*    */         
/* 65 */         this.mc.gameSettings.saveOptions();
/* 66 */         this.mc.displayGuiScreen(this.prevScreen);
/*    */       } 
/*    */       
/* 69 */       if (guibutton.id == 210) {
/*    */         
/* 71 */         this.mc.gameSettings.saveOptions();
/* 72 */         GuiYesNo guiyesno = new GuiYesNo(this, I18n.format("of.message.other.reset", new Object[0]), "", 9999);
/* 73 */         this.mc.displayGuiScreen((GuiScreen)guiyesno);
/*    */       } 
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void confirmClicked(boolean flag, int i) {
/* 80 */     if (flag)
/*    */     {
/* 82 */       this.mc.gameSettings.resetSettings();
/*    */     }
/*    */     
/* 85 */     this.mc.displayGuiScreen(this);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void drawScreen(int x, int y, float f) {
/* 93 */     drawDefaultBackground();
/* 94 */     drawCenteredString(fontRendererObj, this.title, (width / 2), 15.0F, 16777215);
/* 95 */     super.drawScreen(x, y, f);
/* 96 */     this.tooltipManager.drawTooltips(x, y, this.buttonList);
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\optifine\GuiOtherSettingsOF.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */