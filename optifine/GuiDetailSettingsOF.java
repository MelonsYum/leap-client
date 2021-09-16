/*    */ package optifine;
/*    */ 
/*    */ import net.minecraft.client.gui.GuiButton;
/*    */ import net.minecraft.client.gui.GuiOptionButton;
/*    */ import net.minecraft.client.gui.GuiScreen;
/*    */ import net.minecraft.client.resources.I18n;
/*    */ import net.minecraft.client.settings.GameSettings;
/*    */ 
/*    */ public class GuiDetailSettingsOF
/*    */   extends GuiScreen {
/*    */   private GuiScreen prevScreen;
/*    */   protected String title;
/*    */   private GameSettings settings;
/* 14 */   private static GameSettings.Options[] enumOptions = new GameSettings.Options[] { GameSettings.Options.CLOUDS, GameSettings.Options.CLOUD_HEIGHT, GameSettings.Options.TREES, GameSettings.Options.RAIN, GameSettings.Options.SKY, GameSettings.Options.STARS, GameSettings.Options.SUN_MOON, GameSettings.Options.SHOW_CAPES, GameSettings.Options.TRANSLUCENT_BLOCKS, GameSettings.Options.HELD_ITEM_TOOLTIPS, GameSettings.Options.DROPPED_ITEMS, GameSettings.Options.VIGNETTE, GameSettings.Options.DYNAMIC_FOV };
/* 15 */   private TooltipManager tooltipManager = new TooltipManager(this);
/*    */ 
/*    */   
/*    */   public GuiDetailSettingsOF(GuiScreen guiscreen, GameSettings gamesettings) {
/* 19 */     this.prevScreen = guiscreen;
/* 20 */     this.settings = gamesettings;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void initGui() {
/* 28 */     this.title = I18n.format("of.options.detailsTitle", new Object[0]);
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
/* 47 */     this.buttonList.add(new GuiButton(200, width / 2 - 100, height / 6 + 168 + 11, I18n.format("gui.done", new Object[0])));
/*    */   }
/*    */ 
/*    */   
/*    */   protected void actionPerformed(GuiButton guibutton) {
/* 52 */     if (guibutton.enabled) {
/*    */       
/* 54 */       if (guibutton.id < 200 && guibutton instanceof GuiOptionButton) {
/*    */         
/* 56 */         this.settings.setOptionValue(((GuiOptionButton)guibutton).returnEnumOptions(), 1);
/* 57 */         guibutton.displayString = this.settings.getKeyBinding(GameSettings.Options.getEnumOptions(guibutton.id));
/*    */       } 
/*    */       
/* 60 */       if (guibutton.id == 200) {
/*    */         
/* 62 */         this.mc.gameSettings.saveOptions();
/* 63 */         this.mc.displayGuiScreen(this.prevScreen);
/*    */       } 
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void drawScreen(int x, int y, float f) {
/* 73 */     drawDefaultBackground();
/* 74 */     drawCenteredString(fontRendererObj, this.title, (width / 2), 15.0F, 16777215);
/* 75 */     super.drawScreen(x, y, f);
/* 76 */     this.tooltipManager.drawTooltips(x, y, this.buttonList);
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\optifine\GuiDetailSettingsOF.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */