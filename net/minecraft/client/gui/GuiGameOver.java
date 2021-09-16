/*     */ package net.minecraft.client.gui;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.util.Iterator;
/*     */ import leap.ui.MainMenu;
/*     */ import net.minecraft.client.renderer.GlStateManager;
/*     */ import net.minecraft.client.resources.I18n;
/*     */ import net.minecraft.util.EnumChatFormatting;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class GuiGameOver
/*     */   extends GuiScreen
/*     */   implements GuiYesNoCallback
/*     */ {
/*     */   private int field_146347_a;
/*     */   private boolean field_146346_f = false;
/*     */   private static final String __OBFID = "CL_00000690";
/*     */   
/*     */   public void initGui() {
/*  23 */     this.buttonList.clear();
/*     */     
/*  25 */     if (this.mc.theWorld.getWorldInfo().isHardcoreModeEnabled()) {
/*     */       
/*  27 */       if (this.mc.isIntegratedServerRunning())
/*     */       {
/*  29 */         this.buttonList.add(new GuiButton(1, width / 2 - 100, height / 4 + 96, I18n.format("deathScreen.deleteWorld", new Object[0])));
/*     */       }
/*     */       else
/*     */       {
/*  33 */         this.buttonList.add(new GuiButton(1, width / 2 - 100, height / 4 + 96, I18n.format("deathScreen.leaveServer", new Object[0])));
/*     */       }
/*     */     
/*     */     } else {
/*     */       
/*  38 */       this.buttonList.add(new GuiButton(0, width / 2 - 100, height / 4 + 72, I18n.format("deathScreen.respawn", new Object[0])));
/*  39 */       this.buttonList.add(new GuiButton(1, width / 2 - 100, height / 4 + 96, I18n.format("deathScreen.titleScreen", new Object[0])));
/*     */       
/*  41 */       if (this.mc.getSession() == null)
/*     */       {
/*  43 */         ((GuiButton)this.buttonList.get(1)).enabled = false;
/*     */       }
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/*  49 */     for (Iterator<GuiButton> var1 = this.buttonList.iterator(); var1.hasNext(); var2.enabled = false)
/*     */     {
/*  51 */       GuiButton var2 = var1.next();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void keyTyped(char typedChar, int keyCode) throws IOException {}
/*     */ 
/*     */ 
/*     */   
/*     */   protected void actionPerformed(GuiButton button) throws IOException {
/*     */     GuiYesNo var2;
/*  63 */     switch (button.id) {
/*     */       
/*     */       case 0:
/*  66 */         this.mc.thePlayer.respawnPlayer();
/*  67 */         this.mc.displayGuiScreen(null);
/*     */         break;
/*     */       
/*     */       case 1:
/*  71 */         var2 = new GuiYesNo(this, I18n.format("deathScreen.quit.confirm", new Object[0]), "", I18n.format("deathScreen.titleScreen", new Object[0]), I18n.format("deathScreen.respawn", new Object[0]), 0);
/*  72 */         this.mc.displayGuiScreen(var2);
/*  73 */         var2.setButtonDelay(20);
/*     */         break;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void confirmClicked(boolean result, int id) {
/*  79 */     if (result) {
/*     */       
/*  81 */       this.mc.theWorld.sendQuittingDisconnectingPacket();
/*  82 */       this.mc.loadWorld(null);
/*  83 */       this.mc.displayGuiScreen((GuiScreen)new MainMenu());
/*     */     }
/*     */     else {
/*     */       
/*  87 */       this.mc.thePlayer.respawnPlayer();
/*  88 */       this.mc.displayGuiScreen(null);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void drawScreen(int mouseX, int mouseY, float partialTicks) {
/*  97 */     drawGradientRect(0.0F, 0.0F, width, height, 1615855616, -1602211792);
/*  98 */     GlStateManager.pushMatrix();
/*  99 */     GlStateManager.scale(2.0F, 2.0F, 2.0F);
/* 100 */     boolean var4 = this.mc.theWorld.getWorldInfo().isHardcoreModeEnabled();
/* 101 */     String var5 = var4 ? I18n.format("deathScreen.title.hardcore", new Object[0]) : I18n.format("deathScreen.title", new Object[0]);
/* 102 */     drawCenteredString(fontRendererObj, var5, (width / 2 / 2), 30.0F, 16777215);
/* 103 */     GlStateManager.popMatrix();
/*     */     
/* 105 */     if (var4)
/*     */     {
/* 107 */       drawCenteredString(fontRendererObj, I18n.format("deathScreen.hardcoreInfo", new Object[0]), (width / 2), 144.0F, 16777215);
/*     */     }
/*     */     
/* 110 */     drawCenteredString(fontRendererObj, String.valueOf(I18n.format("deathScreen.score", new Object[0])) + ": " + EnumChatFormatting.YELLOW + this.mc.thePlayer.getScore(), (width / 2), 100.0F, 16777215);
/* 111 */     super.drawScreen(mouseX, mouseY, partialTicks);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean doesGuiPauseGame() {
/* 119 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void updateScreen() {
/* 127 */     super.updateScreen();
/* 128 */     this.field_146347_a++;
/*     */ 
/*     */     
/* 131 */     if (this.field_146347_a == 20)
/*     */     {
/* 133 */       for (Iterator<GuiButton> var1 = this.buttonList.iterator(); var1.hasNext(); var2.enabled = true)
/*     */       {
/* 135 */         GuiButton var2 = var1.next();
/*     */       }
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\gui\GuiGameOver.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */