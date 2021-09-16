/*     */ package net.minecraft.client.gui;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import leap.Client;
/*     */ import leap.alts.GuiAltManager;
/*     */ import leap.ui.HudEdit;
/*     */ import leap.ui.MainMenu;
/*     */ import net.minecraft.client.gui.achievement.GuiAchievements;
/*     */ import net.minecraft.client.gui.achievement.GuiStats;
/*     */ import net.minecraft.client.resources.I18n;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class GuiIngameMenu
/*     */   extends GuiScreen
/*     */ {
/*     */   private int field_146445_a;
/*     */   private int field_146444_f;
/*     */   private static final String __OBFID = "CL_00000703";
/*     */   
/*     */   public void initGui() {
/*  27 */     this.field_146445_a = 0;
/*  28 */     this.buttonList.clear();
/*  29 */     byte var1 = -16;
/*  30 */     boolean var2 = true;
/*  31 */     this.buttonList.add(new GuiButton(1, width / 2 - 100, height / 4 + 120 + var1, I18n.format("menu.returnToMenu", new Object[0])));
/*     */     
/*  33 */     if (!this.mc.isIntegratedServerRunning())
/*     */     {
/*  35 */       ((GuiButton)this.buttonList.get(0)).displayString = I18n.format("menu.disconnect", new Object[0]);
/*     */     }
/*     */     
/*  38 */     this.buttonList.add(new GuiButton(54, 1, 1, "Disable KillAura"));
/*  39 */     this.buttonList.add(new GuiButton(69, 1, 24, "Disable ChestSteal"));
/*  40 */     this.buttonList.add(new GuiButton(4, width / 2 - 100, height / 4 + 24 + var1, I18n.format("menu.returnToGame", new Object[0])));
/*  41 */     this.buttonList.add(new GuiButton(0, width / 2 - 100, height / 4 + 96 + var1, 98, 20, I18n.format("menu.options", new Object[0])));
/*  42 */     this.buttonList.add(new GuiButton(45, width / 2 - 100, height / 4 + 72 + var1, 98, 20, "Alt Manager"));
/*  43 */     this.buttonList.add(new GuiButton(98, width / 2 + 2, height / 4 + 72 + var1, 98, 20, "Hud Editor"));
/*     */     
/*     */     GuiButton var3;
/*  46 */     this.buttonList.add(var3 = new GuiButton(7, width / 2 + 2, height / 4 + 96 + var1, 98, 20, I18n.format("menu.shareToLan", new Object[0])));
/*  47 */     this.buttonList.add(new GuiButton(5, width / 2 - 100, height / 4 + 48 + var1, 98, 20, I18n.format("gui.achievements", new Object[0])));
/*  48 */     this.buttonList.add(new GuiButton(6, width / 2 + 2, height / 4 + 48 + var1, 98, 20, I18n.format("gui.stats", new Object[0])));
/*  49 */     var3.enabled = (this.mc.isSingleplayer() && !this.mc.getIntegratedServer().getPublic());
/*     */   }
/*     */ 
/*     */   
/*     */   protected void actionPerformed(GuiButton button) throws IOException {
/*  54 */     switch (button.id) {
/*     */       
/*     */       case 0:
/*  57 */         this.mc.displayGuiScreen(new GuiOptions(this, this.mc.gameSettings));
/*     */ 
/*     */       
/*     */       case 1:
/*  61 */         button.enabled = false;
/*  62 */         this.mc.theWorld.sendQuittingDisconnectingPacket();
/*  63 */         this.mc.loadWorld(null);
/*  64 */         this.mc.displayGuiScreen((GuiScreen)new MainMenu());
/*     */ 
/*     */       
/*     */       default:
/*     */         return;
/*     */ 
/*     */       
/*     */       case 4:
/*  72 */         this.mc.displayGuiScreen(null);
/*  73 */         this.mc.setIngameFocus();
/*     */ 
/*     */       
/*     */       case 5:
/*  77 */         this.mc.displayGuiScreen((GuiScreen)new GuiAchievements(this, this.mc.thePlayer.getStatFileWriter()));
/*     */ 
/*     */       
/*     */       case 6:
/*  81 */         this.mc.displayGuiScreen((GuiScreen)new GuiStats(this, this.mc.thePlayer.getStatFileWriter()));
/*     */ 
/*     */       
/*     */       case 7:
/*  85 */         this.mc.displayGuiScreen(new GuiShareToLan(this));
/*     */ 
/*     */       
/*     */       case 54:
/*  89 */         (Client.getModule("KillAura")).toggled = false;
/*     */ 
/*     */       
/*     */       case 69:
/*  93 */         (Client.getModule("ChestSteal")).toggled = false;
/*     */ 
/*     */       
/*     */       case 45:
/*  97 */         this.mc.displayGuiScreen((GuiScreen)new GuiAltManager());
/*     */       case 98:
/*     */         break;
/*     */     } 
/* 101 */     this.mc.displayGuiScreen((GuiScreen)new HudEdit());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void updateScreen() {
/* 112 */     super.updateScreen();
/* 113 */     this.field_146444_f++;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void drawScreen(int mouseX, int mouseY, float partialTicks) {
/* 121 */     ScaledResolution sr = new ScaledResolution(this.mc, this.mc.displayWidth, this.mc.displayHeight);
/*     */     
/* 123 */     drawCenteredString(fontRendererObj, I18n.format("menu.game", new Object[0]), (width / 2), 40.0F, 16777215);
/* 124 */     super.drawScreen(mouseX, mouseY, partialTicks);
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\gui\GuiIngameMenu.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */