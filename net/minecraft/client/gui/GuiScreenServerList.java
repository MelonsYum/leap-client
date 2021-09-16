/*     */ package net.minecraft.client.gui;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import net.minecraft.client.multiplayer.ServerData;
/*     */ import net.minecraft.client.resources.I18n;
/*     */ import org.lwjgl.input.Keyboard;
/*     */ 
/*     */ public class GuiScreenServerList
/*     */   extends GuiScreen
/*     */ {
/*     */   private final GuiScreen field_146303_a;
/*     */   private final ServerData field_146301_f;
/*     */   private GuiTextField field_146302_g;
/*     */   private static final String __OBFID = "CL_00000692";
/*     */   
/*     */   public GuiScreenServerList(GuiScreen p_i1031_1_, ServerData p_i1031_2_) {
/*  17 */     this.field_146303_a = p_i1031_1_;
/*  18 */     this.field_146301_f = p_i1031_2_;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void updateScreen() {
/*  26 */     this.field_146302_g.updateCursorCounter();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void initGui() {
/*  34 */     Keyboard.enableRepeatEvents(true);
/*  35 */     this.buttonList.clear();
/*  36 */     this.buttonList.add(new GuiButton(0, width / 2 - 100, height / 4 + 96 + 12, I18n.format("selectServer.select", new Object[0])));
/*  37 */     this.buttonList.add(new GuiButton(1, width / 2 - 100, height / 4 + 120 + 12, I18n.format("gui.cancel", new Object[0])));
/*  38 */     this.field_146302_g = new GuiTextField(2, fontRendererObj, width / 2 - 100, 116, 200, 20);
/*  39 */     this.field_146302_g.setMaxStringLength(128);
/*  40 */     this.field_146302_g.setFocused(true);
/*  41 */     this.field_146302_g.setText(this.mc.gameSettings.lastServer);
/*  42 */     ((GuiButton)this.buttonList.get(0)).enabled = (this.field_146302_g.getText().length() > 0 && (this.field_146302_g.getText().split(":")).length > 0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onGuiClosed() {
/*  50 */     Keyboard.enableRepeatEvents(false);
/*  51 */     this.mc.gameSettings.lastServer = this.field_146302_g.getText();
/*  52 */     this.mc.gameSettings.saveOptions();
/*     */   }
/*     */ 
/*     */   
/*     */   protected void actionPerformed(GuiButton button) throws IOException {
/*  57 */     if (button.enabled)
/*     */     {
/*  59 */       if (button.id == 1) {
/*     */         
/*  61 */         this.field_146303_a.confirmClicked(false, 0);
/*     */       }
/*  63 */       else if (button.id == 0) {
/*     */         
/*  65 */         this.field_146301_f.serverIP = this.field_146302_g.getText();
/*  66 */         this.field_146303_a.confirmClicked(true, 0);
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void keyTyped(char typedChar, int keyCode) throws IOException {
/*  77 */     if (this.field_146302_g.textboxKeyTyped(typedChar, keyCode)) {
/*     */       
/*  79 */       ((GuiButton)this.buttonList.get(0)).enabled = (this.field_146302_g.getText().length() > 0 && (this.field_146302_g.getText().split(":")).length > 0);
/*     */     }
/*  81 */     else if (keyCode == 28 || keyCode == 156) {
/*     */       
/*  83 */       actionPerformed(this.buttonList.get(0));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
/*  92 */     super.mouseClicked(mouseX, mouseY, mouseButton);
/*  93 */     this.field_146302_g.mouseClicked(mouseX, mouseY, mouseButton);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void drawScreen(int mouseX, int mouseY, float partialTicks) {
/* 101 */     drawDefaultBackground();
/* 102 */     drawCenteredString(fontRendererObj, I18n.format("selectServer.direct", new Object[0]), (width / 2), 20.0F, 16777215);
/* 103 */     drawString(fontRendererObj, I18n.format("addServer.enterIp", new Object[0]), width / 2 - 100, 100, 10526880);
/* 104 */     this.field_146302_g.drawTextBox();
/* 105 */     super.drawScreen(mouseX, mouseY, partialTicks);
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\gui\GuiScreenServerList.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */