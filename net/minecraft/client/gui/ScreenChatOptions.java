/*    */ package net.minecraft.client.gui;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import net.minecraft.client.resources.I18n;
/*    */ import net.minecraft.client.settings.GameSettings;
/*    */ 
/*    */ public class ScreenChatOptions
/*    */   extends GuiScreen {
/*  9 */   private static final GameSettings.Options[] field_146399_a = new GameSettings.Options[] { GameSettings.Options.CHAT_VISIBILITY, GameSettings.Options.CHAT_COLOR, GameSettings.Options.CHAT_LINKS, GameSettings.Options.CHAT_OPACITY, GameSettings.Options.CHAT_LINKS_PROMPT, GameSettings.Options.CHAT_SCALE, GameSettings.Options.CHAT_HEIGHT_FOCUSED, GameSettings.Options.CHAT_HEIGHT_UNFOCUSED, GameSettings.Options.CHAT_WIDTH, GameSettings.Options.REDUCED_DEBUG_INFO };
/*    */   
/*    */   private final GuiScreen field_146396_g;
/*    */   private final GameSettings game_settings;
/*    */   private String field_146401_i;
/*    */   private String field_146398_r;
/*    */   private int field_146397_s;
/*    */   private static final String __OBFID = "CL_00000681";
/*    */   
/*    */   public ScreenChatOptions(GuiScreen p_i1023_1_, GameSettings p_i1023_2_) {
/* 19 */     this.field_146396_g = p_i1023_1_;
/* 20 */     this.game_settings = p_i1023_2_;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void initGui() {
/* 28 */     int var1 = 0;
/* 29 */     this.field_146401_i = I18n.format("options.chat.title", new Object[0]);
/* 30 */     this.field_146398_r = I18n.format("options.multiplayer.title", new Object[0]);
/* 31 */     GameSettings.Options[] var2 = field_146399_a;
/* 32 */     int var3 = var2.length;
/*    */     
/* 34 */     for (int var4 = 0; var4 < var3; var4++) {
/*    */       
/* 36 */       GameSettings.Options var5 = var2[var4];
/*    */       
/* 38 */       if (var5.getEnumFloat()) {
/*    */         
/* 40 */         this.buttonList.add(new GuiOptionSlider(var5.returnEnumOrdinal(), width / 2 - 155 + var1 % 2 * 160, height / 6 + 24 * (var1 >> 1), var5));
/*    */       }
/*    */       else {
/*    */         
/* 44 */         this.buttonList.add(new GuiOptionButton(var5.returnEnumOrdinal(), width / 2 - 155 + var1 % 2 * 160, height / 6 + 24 * (var1 >> 1), var5, this.game_settings.getKeyBinding(var5)));
/*    */       } 
/*    */       
/* 47 */       var1++;
/*    */     } 
/*    */     
/* 50 */     if (var1 % 2 == 1)
/*    */     {
/* 52 */       var1++;
/*    */     }
/*    */     
/* 55 */     this.field_146397_s = height / 6 + 24 * (var1 >> 1);
/* 56 */     this.buttonList.add(new GuiButton(200, width / 2 - 100, height / 6 + 120, I18n.format("gui.done", new Object[0])));
/*    */   }
/*    */ 
/*    */   
/*    */   protected void actionPerformed(GuiButton button) throws IOException {
/* 61 */     if (button.enabled) {
/*    */       
/* 63 */       if (button.id < 100 && button instanceof GuiOptionButton) {
/*    */         
/* 65 */         this.game_settings.setOptionValue(((GuiOptionButton)button).returnEnumOptions(), 1);
/* 66 */         button.displayString = this.game_settings.getKeyBinding(GameSettings.Options.getEnumOptions(button.id));
/*    */       } 
/*    */       
/* 69 */       if (button.id == 200) {
/*    */         
/* 71 */         this.mc.gameSettings.saveOptions();
/* 72 */         this.mc.displayGuiScreen(this.field_146396_g);
/*    */       } 
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void drawScreen(int mouseX, int mouseY, float partialTicks) {
/* 82 */     drawDefaultBackground();
/* 83 */     drawCenteredString(fontRendererObj, this.field_146401_i, (width / 2), 20.0F, 16777215);
/* 84 */     drawCenteredString(fontRendererObj, this.field_146398_r, (width / 2), (this.field_146397_s + 7), 16777215);
/* 85 */     super.drawScreen(mouseX, mouseY, partialTicks);
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\gui\ScreenChatOptions.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */