/*     */ package net.minecraft.client.gui;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import net.minecraft.client.multiplayer.ServerData;
/*     */ import net.minecraft.client.resources.I18n;
/*     */ import org.lwjgl.input.Keyboard;
/*     */ 
/*     */ public class GuiScreenAddServer
/*     */   extends GuiScreen
/*     */ {
/*     */   private final GuiScreen parentScreen;
/*     */   private final ServerData serverData;
/*     */   private GuiTextField serverIPField;
/*     */   private GuiTextField serverNameField;
/*     */   private GuiButton serverResourcePacks;
/*     */   private static final String __OBFID = "CL_00000695";
/*     */   
/*     */   public GuiScreenAddServer(GuiScreen p_i1033_1_, ServerData p_i1033_2_) {
/*  19 */     this.parentScreen = p_i1033_1_;
/*  20 */     this.serverData = p_i1033_2_;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void updateScreen() {
/*  28 */     this.serverNameField.updateCursorCounter();
/*  29 */     this.serverIPField.updateCursorCounter();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void initGui() {
/*  37 */     Keyboard.enableRepeatEvents(true);
/*  38 */     this.buttonList.clear();
/*  39 */     this.buttonList.add(new GuiButton(0, width / 2 - 100, height / 4 + 96 + 18, I18n.format("addServer.add", new Object[0])));
/*  40 */     this.buttonList.add(new GuiButton(1, width / 2 - 100, height / 4 + 120 + 18, I18n.format("gui.cancel", new Object[0])));
/*  41 */     this.buttonList.add(this.serverResourcePacks = new GuiButton(2, width / 2 - 100, height / 4 + 72, String.valueOf(I18n.format("addServer.resourcePack", new Object[0])) + ": " + this.serverData.getResourceMode().getMotd().getFormattedText()));
/*  42 */     this.serverNameField = new GuiTextField(0, fontRendererObj, width / 2 - 100, 66, 200, 20);
/*  43 */     this.serverNameField.setFocused(true);
/*  44 */     this.serverNameField.setText(this.serverData.serverName);
/*  45 */     this.serverIPField = new GuiTextField(1, fontRendererObj, width / 2 - 100, 106, 200, 20);
/*  46 */     this.serverIPField.setMaxStringLength(128);
/*  47 */     this.serverIPField.setText(this.serverData.serverIP);
/*  48 */     ((GuiButton)this.buttonList.get(0)).enabled = (this.serverIPField.getText().length() > 0 && (this.serverIPField.getText().split(":")).length > 0 && this.serverNameField.getText().length() > 0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onGuiClosed() {
/*  56 */     Keyboard.enableRepeatEvents(false);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void actionPerformed(GuiButton button) throws IOException {
/*  61 */     if (button.enabled)
/*     */     {
/*  63 */       if (button.id == 2) {
/*     */         
/*  65 */         this.serverData.setResourceMode(ServerData.ServerResourceMode.values()[(this.serverData.getResourceMode().ordinal() + 1) % (ServerData.ServerResourceMode.values()).length]);
/*  66 */         this.serverResourcePacks.displayString = String.valueOf(I18n.format("addServer.resourcePack", new Object[0])) + ": " + this.serverData.getResourceMode().getMotd().getFormattedText();
/*     */       }
/*  68 */       else if (button.id == 1) {
/*     */         
/*  70 */         this.parentScreen.confirmClicked(false, 0);
/*     */       }
/*  72 */       else if (button.id == 0) {
/*     */         
/*  74 */         this.serverData.serverName = this.serverNameField.getText();
/*  75 */         this.serverData.serverIP = this.serverIPField.getText();
/*  76 */         this.parentScreen.confirmClicked(true, 0);
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
/*  87 */     this.serverNameField.textboxKeyTyped(typedChar, keyCode);
/*  88 */     this.serverIPField.textboxKeyTyped(typedChar, keyCode);
/*     */     
/*  90 */     if (keyCode == 15) {
/*     */       
/*  92 */       this.serverNameField.setFocused(!this.serverNameField.isFocused());
/*  93 */       this.serverIPField.setFocused(!this.serverIPField.isFocused());
/*     */     } 
/*     */     
/*  96 */     if (keyCode == 28 || keyCode == 156)
/*     */     {
/*  98 */       actionPerformed(this.buttonList.get(0));
/*     */     }
/*     */     
/* 101 */     ((GuiButton)this.buttonList.get(0)).enabled = (this.serverIPField.getText().length() > 0 && (this.serverIPField.getText().split(":")).length > 0 && this.serverNameField.getText().length() > 0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
/* 109 */     super.mouseClicked(mouseX, mouseY, mouseButton);
/* 110 */     this.serverIPField.mouseClicked(mouseX, mouseY, mouseButton);
/* 111 */     this.serverNameField.mouseClicked(mouseX, mouseY, mouseButton);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void drawScreen(int mouseX, int mouseY, float partialTicks) {
/* 119 */     drawDefaultBackground();
/* 120 */     drawCenteredString(fontRendererObj, I18n.format("addServer.title", new Object[0]), (width / 2), 17.0F, 16777215);
/* 121 */     drawString(fontRendererObj, I18n.format("addServer.enterName", new Object[0]), width / 2 - 100, 53, 10526880);
/* 122 */     drawString(fontRendererObj, I18n.format("addServer.enterIp", new Object[0]), width / 2 - 100, 94, 10526880);
/* 123 */     this.serverNameField.drawTextBox();
/* 124 */     this.serverIPField.drawTextBox();
/* 125 */     super.drawScreen(mouseX, mouseY, partialTicks);
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\gui\GuiScreenAddServer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */