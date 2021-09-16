/*     */ package leap.alts;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import net.minecraft.client.gui.GuiButton;
/*     */ import net.minecraft.client.gui.GuiScreen;
/*     */ import net.minecraft.client.gui.GuiTextField;
/*     */ import net.minecraft.util.EnumChatFormatting;
/*     */ import org.lwjgl.input.Keyboard;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class GuiAltLogin
/*     */   extends GuiScreen
/*     */ {
/*     */   private PasswordField password;
/*     */   private final GuiScreen previousScreen;
/*     */   private AltLoginThread thread;
/*     */   private GuiTextField username;
/*     */   
/*     */   public GuiAltLogin(GuiScreen previousScreen) {
/*  22 */     this.previousScreen = previousScreen;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void actionPerformed(GuiButton button) {
/*  27 */     switch (button.id) {
/*     */       case 1:
/*  29 */         this.mc.displayGuiScreen(this.previousScreen);
/*     */         break;
/*     */       
/*     */       case 0:
/*  33 */         this.thread = new AltLoginThread(this.username.getText(), this.password.getText());
/*  34 */         this.thread.start();
/*     */         break;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void drawScreen(int x2, int y2, float z2) {
/*  41 */     drawDefaultBackground();
/*  42 */     this.username.drawTextBox();
/*  43 */     this.password.drawTextBox();
/*  44 */     drawCenteredString(this.mc.fontRendererObj, "Alt Login", (width / 2), 20.0F, -1);
/*  45 */     drawCenteredString(this.mc.fontRendererObj, (this.thread == null) ? (EnumChatFormatting.GRAY + "Idle...") : this.thread.getStatus(), (width / 2), 29.0F, -1);
/*  46 */     if (this.username.getText().isEmpty()) {
/*  47 */       drawString(this.mc.fontRendererObj, "Username / E-Mail", width / 2 - 96, 66, -7829368);
/*     */     }
/*  49 */     if (this.password.getText().isEmpty()) {
/*  50 */       drawString(this.mc.fontRendererObj, "Password", width / 2 - 96, 106, -7829368);
/*     */     }
/*  52 */     super.drawScreen(x2, y2, z2);
/*     */   }
/*     */ 
/*     */   
/*     */   public void initGui() {
/*  57 */     int var3 = height / 4 + 24;
/*  58 */     this.buttonList.add(new GuiButton(0, width / 2 - 100, var3 + 72 + 12, "Login"));
/*  59 */     this.buttonList.add(new GuiButton(1, width / 2 - 100, var3 + 72 + 12 + 24, "Back"));
/*  60 */     this.username = new GuiTextField(var3, this.mc.fontRendererObj, width / 2 - 100, 60, 200, 20);
/*  61 */     this.password = new PasswordField(this.mc.fontRendererObj, width / 2 - 100, 100, 200, 20);
/*  62 */     this.username.setFocused(true);
/*  63 */     Keyboard.enableRepeatEvents(true);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void keyTyped(char character, int key) {
/*     */     try {
/*  69 */       super.keyTyped(character, key);
/*     */     }
/*  71 */     catch (IOException e) {
/*  72 */       e.printStackTrace();
/*     */     } 
/*  74 */     if (character == '\t') {
/*  75 */       if (!this.username.isFocused() && !this.password.isFocused()) {
/*  76 */         this.username.setFocused(true);
/*     */       } else {
/*  78 */         this.username.setFocused(this.password.isFocused());
/*  79 */         this.password.setFocused(!this.username.isFocused());
/*     */       } 
/*     */     }
/*  82 */     if (character == '\r') {
/*  83 */       actionPerformed(this.buttonList.get(0));
/*     */     }
/*  85 */     this.username.textboxKeyTyped(character, key);
/*  86 */     this.password.textboxKeyTyped(character, key);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void mouseClicked(int x2, int y2, int button) {
/*     */     try {
/*  92 */       super.mouseClicked(x2, y2, button);
/*     */     }
/*  94 */     catch (IOException e) {
/*  95 */       e.printStackTrace();
/*     */     } 
/*  97 */     this.username.mouseClicked(x2, y2, button);
/*  98 */     this.password.mouseClicked(x2, y2, button);
/*     */   }
/*     */ 
/*     */   
/*     */   public void onGuiClosed() {
/* 103 */     Keyboard.enableRepeatEvents(false);
/*     */   }
/*     */ 
/*     */   
/*     */   public void updateScreen() {
/* 108 */     this.username.updateCursorCounter();
/* 109 */     this.password.updateCursorCounter();
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\leap\alts\GuiAltLogin.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */