/*     */ package leap.alts;
/*     */ 
/*     */ import com.mojang.authlib.Agent;
/*     */ import com.mojang.authlib.exceptions.AuthenticationException;
/*     */ import com.mojang.authlib.yggdrasil.YggdrasilAuthenticationService;
/*     */ import com.mojang.authlib.yggdrasil.YggdrasilUserAuthentication;
/*     */ import java.awt.HeadlessException;
/*     */ import java.awt.Toolkit;
/*     */ import java.awt.datatransfer.DataFlavor;
/*     */ import java.awt.datatransfer.UnsupportedFlavorException;
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.net.Proxy;
/*     */ import java.util.ArrayList;
/*     */ import net.minecraft.client.gui.GuiButton;
/*     */ import net.minecraft.client.gui.GuiScreen;
/*     */ import net.minecraft.client.gui.GuiTextField;
/*     */ import net.minecraft.util.EnumChatFormatting;
/*     */ import org.lwjgl.input.Keyboard;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class GuiAddAlt
/*     */   extends GuiScreen
/*     */ {
/*     */   final GuiAltManager manager;
/*     */   private PasswordField password;
/*  34 */   private String status = EnumChatFormatting.GRAY + "Idle...";
/*     */   private GuiTextField username;
/*     */   private File dir;
/*     */   private File dataFile;
/*     */   
/*     */   public GuiAddAlt(GuiAltManager manager) {
/*  40 */     this.manager = manager;
/*     */   }
/*     */   protected void actionPerformed(GuiButton button) {
/*     */     String alt;
/*     */     String[] clip;
/*  45 */     switch (button.id) {
/*     */       case 0:
/*  47 */         if (!this.username.getText().isEmpty() && !this.password.getText().isEmpty()) {
/*  48 */           String str = String.valueOf(this.username.getText()) + " " + this.password.getText();
/*  49 */           AddAltThread login = new AddAltThread(this.username.getText(), this.password.getText());
/*  50 */           login.start();
/*  51 */           SaveAlts.toSave.add(str);
/*     */           break;
/*     */         } 
/*     */       
/*     */       case 1:
/*  56 */         this.mc.displayGuiScreen(this.manager);
/*     */ 
/*     */       
/*     */       case 21:
/*  60 */         alt = String.valueOf(this.username.getText()) + " " + this.password.getText();
/*     */         
/*  62 */         clip = getClipboardString().split(":");
/*  63 */         if (clip.length == 2) {
/*  64 */           AddAltThread login = new AddAltThread(clip[0], clip[1]);
/*  65 */           SaveAlts.toSave.add(alt);
/*  66 */           login.start();
/*     */           break;
/*     */         } 
/*  69 */         this.status = EnumChatFormatting.RED + "Invalid Format! Example: user:pass";
/*     */         break;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void drawScreen(int i2, int j2, float f2) {
/*  78 */     this.username.drawTextBox();
/*  79 */     this.password.drawTextBox();
/*  80 */     drawCenteredString(fontRendererObj, "Add Alt", (width / 2), 20.0F, -1);
/*  81 */     if (this.username.getText().isEmpty()) {
/*  82 */       drawString(this.mc.fontRendererObj, "Username / E-Mail", width / 2 - 96, 66, -7829368);
/*     */     }
/*  84 */     if (this.password.getText().isEmpty()) {
/*  85 */       drawString(this.mc.fontRendererObj, "Password", width / 2 - 96, 106, -7829368);
/*     */     }
/*  87 */     drawCenteredString(fontRendererObj, this.status, (width / 2), 30.0F, -1);
/*  88 */     super.drawScreen(i2, j2, f2);
/*     */   }
/*     */ 
/*     */   
/*     */   public void initGui() {
/*  93 */     Keyboard.enableRepeatEvents(true);
/*  94 */     this.buttonList.clear();
/*  95 */     this.buttonList.add(new GuiButton(0, width / 2 - 100, height / 4 + 92 + 12, "Login"));
/*  96 */     this.buttonList.add(new GuiButton(21, width / 2 - 100, height / 4 + 92 + 12 - 25, "Copy From Clipboard"));
/*  97 */     this.buttonList.add(new GuiButton(1, width / 2 - 100, height / 4 + 116 + 12, "Back"));
/*  98 */     this.username = new GuiTextField(this.eventButton, this.mc.fontRendererObj, width / 2 - 100, 60, 200, 20);
/*  99 */     this.password = new PasswordField(this.mc.fontRendererObj, width / 2 - 100, 100, 200, 20);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void keyTyped(char par1, int par2) {
/* 104 */     this.username.textboxKeyTyped(par1, par2);
/* 105 */     this.password.textboxKeyTyped(par1, par2);
/* 106 */     if (par1 == '\t' && (this.username.isFocused() || this.password.isFocused())) {
/* 107 */       this.username.setFocused(!this.username.isFocused());
/* 108 */       this.password.setFocused(!this.password.isFocused());
/*     */     } 
/* 110 */     if (par1 == '\r') {
/* 111 */       actionPerformed(this.buttonList.get(0));
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   protected void mouseClicked(int par1, int par2, int par3) {
/*     */     try {
/* 118 */       super.mouseClicked(par1, par2, par3);
/*     */     }
/* 120 */     catch (IOException e) {
/* 121 */       e.printStackTrace();
/*     */     } 
/* 123 */     this.username.mouseClicked(par1, par2, par3);
/* 124 */     this.password.mouseClicked(par1, par2, par3);
/*     */   }
/*     */ 
/*     */   
/*     */   private class AddAltThread
/*     */     extends Thread
/*     */   {
/*     */     private final String password;
/*     */     
/*     */     private final String username;
/*     */ 
/*     */     
/*     */     public AddAltThread(String username, String password) {
/* 137 */       this.username = username;
/* 138 */       this.password = password;
/* 139 */       GuiAddAlt.this.status = EnumChatFormatting.GRAY + "Idle...";
/*     */     }
/*     */     
/*     */     private final void checkAndAddAlt(String username, String password) throws IOException {
/* 143 */       YggdrasilAuthenticationService service = new YggdrasilAuthenticationService(Proxy.NO_PROXY, "");
/* 144 */       YggdrasilUserAuthentication auth = (YggdrasilUserAuthentication)service.createUserAuthentication(Agent.MINECRAFT);
/* 145 */       ArrayList<String> toSave = new ArrayList<>();
/* 146 */       GuiAddAlt.this.dataFile = new File(GuiAddAlt.this.dir, "alts.txt");
/*     */       
/* 148 */       auth.setUsername(username);
/* 149 */       auth.setPassword(password);
/*     */       try {
/* 151 */         auth.logIn();
/* 152 */         SaveAlts.toSave.add(String.valueOf(username) + " " + password);
/* 153 */         AltManager.registry.add(new Alt(username, password, auth.getSelectedProfile().getName()));
/* 154 */         GuiAddAlt.this.status = "Alt added. (" + username + ")";
/*     */       }
/* 156 */       catch (AuthenticationException e) {
/* 157 */         GuiAddAlt.this.status = EnumChatFormatting.RED + "Alt failed!";
/* 158 */         e.printStackTrace();
/*     */       } 
/*     */     }
/*     */     
/*     */     public String getClipBoard() {
/*     */       try {
/* 164 */         return (String)Toolkit.getDefaultToolkit().getSystemClipboard().getData(DataFlavor.stringFlavor);
/* 165 */       } catch (HeadlessException e) {
/* 166 */         e.printStackTrace();
/* 167 */       } catch (UnsupportedFlavorException e) {
/* 168 */         e.printStackTrace();
/* 169 */       } catch (IOException e) {
/* 170 */         e.printStackTrace();
/*     */       } 
/* 172 */       return "";
/*     */     }
/*     */ 
/*     */     
/*     */     public void run() {
/* 177 */       if (this.password.equals("")) {
/*     */         
/* 179 */         AltManager.registry.add(new Alt(this.username, ""));
/* 180 */         GuiAddAlt.this.status = EnumChatFormatting.GREEN + "Alt added. (" + this.username + " - offline name)";
/*     */         return;
/*     */       } 
/* 183 */       GuiAddAlt.this.status = EnumChatFormatting.YELLOW + "Trying alt...";
/*     */       try {
/* 185 */         checkAndAddAlt(this.username, this.password);
/*     */       }
/* 187 */       catch (IOException e) {
/* 188 */         e.printStackTrace();
/*     */       } 
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\leap\alts\GuiAddAlt.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */