/*     */ package leap.alts;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.io.IOException;
/*     */ import leap.Client;
/*     */ import leap.util.JColor;
/*     */ import leap.util.Shape;
/*     */ import net.minecraft.client.gui.FontRenderer;
/*     */ import net.minecraft.client.gui.Gui;
/*     */ import net.minecraft.client.gui.GuiButton;
/*     */ import net.minecraft.client.gui.GuiScreen;
/*     */ import net.minecraft.client.gui.ScaledResolution;
/*     */ import net.minecraft.util.EnumChatFormatting;
/*     */ import org.lwjgl.input.Keyboard;
/*     */ import org.lwjgl.input.Mouse;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ public class GuiAltManager
/*     */   extends GuiScreen {
/*     */   private GuiButton login;
/*     */   private GuiButton remove;
/*     */   private GuiButton rename;
/*     */   private AltLoginThread loginThread;
/*     */   private int offset;
/*  25 */   public Alt selectedAlt = null;
/*  26 */   private String status = EnumChatFormatting.GRAY + "No alts selected"; public void actionPerformed(GuiButton button) throws IOException {
/*     */     String user;
/*     */     String pass;
/*  29 */     switch (button.id) {
/*     */       case 0:
/*  31 */         if (this.loginThread == null) {
/*  32 */           this.mc.displayGuiScreen(null);
/*     */           break;
/*     */         } 
/*  35 */         if (!this.loginThread.getStatus().equals(EnumChatFormatting.YELLOW + "Attempting to log in") && !this.loginThread.getStatus().equals(EnumChatFormatting.RED + "Do not hit back!" + EnumChatFormatting.YELLOW + " Logging in...")) {
/*  36 */           this.mc.displayGuiScreen(null);
/*     */           break;
/*     */         } 
/*  39 */         this.loginThread.setStatus(EnumChatFormatting.RED + "Failed to login! Please try again!" + EnumChatFormatting.YELLOW + " Logging in...");
/*     */         break;
/*     */       
/*     */       case 1:
/*  43 */         user = this.selectedAlt.getUsername();
/*  44 */         pass = this.selectedAlt.getPassword();
/*  45 */         this.loginThread = new AltLoginThread(user, pass);
/*  46 */         this.loginThread.start();
/*     */         break;
/*     */       
/*     */       case 2:
/*  50 */         if (this.loginThread != null) {
/*  51 */           this.loginThread = null;
/*     */         }
/*  53 */         AltManager.registry.remove(this.selectedAlt);
/*  54 */         this.status = "§aRemoved.";
/*  55 */         this.selectedAlt = null;
/*     */         break;
/*     */       
/*     */       case 3:
/*  59 */         this.mc.displayGuiScreen(new GuiAddAlt(this));
/*     */         break;
/*     */       
/*     */       case 4:
/*  63 */         this.mc.displayGuiScreen(new GuiAltLogin(this));
/*     */         break;
/*     */ 
/*     */       
/*     */       case 6:
/*  68 */         this.mc.displayGuiScreen(new GuiRenameAlt(this));
/*     */         break;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void drawScreen(int par1, int par2, float par3) {
/*  85 */     drawBackground(1);
/*     */     
/*  87 */     if (Mouse.hasWheel()) {
/*  88 */       int wheel = Mouse.getDWheel();
/*  89 */       if (wheel < 0) {
/*  90 */         this.offset += 26;
/*  91 */         if (this.offset < 0) {
/*  92 */           this.offset = 0;
/*     */         }
/*  94 */       } else if (wheel > 0) {
/*  95 */         this.offset -= 26;
/*  96 */         if (this.offset < 0) {
/*  97 */           this.offset = 0;
/*     */         }
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 103 */     Shape.color((new Color(50, 50, 50, 50)).getRGB());
/* 104 */     Shape.drawRoundedRect(5.0D, 1.0D, (GuiScreen.width - 12), 40.0D, 4.0F);
/*     */     
/* 106 */     Client.customFontRenderer.drawString("Alt Manager", (GuiScreen.width / 2), 8.0D, new JColor(new Color(150, 200, 255)));
/*     */     
/* 108 */     Shape.color((new Color(255, 200, 120)).getRGB());
/* 109 */     Shape.drawRoundedRect((GuiScreen.width / 2 - 20), 24.0D, 100.0D, 10.0D, 1.0F);
/*     */     
/* 111 */     Shape.color((new Color(40, 40, 40, 100)).getRGB());
/* 112 */     Shape.drawRoundedRect(0.0D, (GuiScreen.height - 50), GuiScreen.width, (GuiScreen.width / 8), 0.0F);
/* 113 */     super.drawScreen(par1, par2, par3);
/* 114 */     Client.customFontRenderer.drawCenteredString(this.mc.session.getUsername(), 40.0F, 10.0F, new JColor(-7829368));
/* 115 */     FontRenderer fontRendererObj = GuiAltManager.fontRendererObj;
/* 116 */     StringBuilder sb2 = new StringBuilder("Account Manager - ");
/*     */     
/* 118 */     Gui.drawRect(50.0D, 33.0D, (width - 50), (height - 50), (new Color(70, 70, 70, 15)).getRGB());
/* 119 */     GL11.glPushMatrix();
/* 120 */     prepareScissorBox(0.0F, 33.0F, width, (height - 50));
/* 121 */     GL11.glEnable(3089);
/* 122 */     int y2 = 38;
/* 123 */     for (Alt alt2 : AltManager.registry) {
/* 124 */       if (!isAltInArea(y2))
/* 125 */         continue;  String name = alt2.getMask().equals("") ? alt2.getUsername() : alt2.getMask();
/* 126 */       String pass = alt2.getPassword().equals("") ? "§cCracked" : alt2.getPassword().replaceAll(".", "*");
/* 127 */       if (alt2 == this.selectedAlt) {
/* 128 */         if (isMouseOverAlt(par1, par2, y2 - this.offset) && Mouse.isButtonDown(0)) {
/* 129 */           Gui.drawRect(52.0D, (y2 - this.offset - 4), (width - 52), (y2 - this.offset + 20), -2142943931);
/* 130 */         } else if (isMouseOverAlt(par1, par2, y2 - this.offset)) {
/* 131 */           Gui.drawRect(52.0D, (y2 - this.offset - 4), (width - 52), (y2 - this.offset + 20), -2142088622);
/*     */         } else {
/* 133 */           Gui.drawRect(52.0D, (y2 - this.offset - 4), (width - 52), (y2 - this.offset + 20), -2144259791);
/*     */         } 
/* 135 */       } else if (isMouseOverAlt(par1, par2, y2 - this.offset) && Mouse.isButtonDown(0)) {
/* 136 */         Gui.drawRect(52.0D, (y2 - this.offset - 4), (width - 52), (y2 - this.offset + 20), -16777216);
/* 137 */       } else if (isMouseOverAlt(par1, par2, y2 - this.offset)) {
/* 138 */         Gui.drawRect(52.0D, (y2 - this.offset - 4), (width - 52), (y2 - this.offset + 20), -16777216);
/*     */       } 
/* 140 */       Client.customFontRenderer.drawCenteredString(name, (width / 2), (y2 - this.offset), new JColor(-1));
/* 141 */       drawCenteredString(GuiAltManager.fontRendererObj, pass, (width / 2), (y2 - this.offset + 10), 5592405);
/* 142 */       y2 += 26;
/*     */     } 
/* 144 */     GL11.glDisable(3089);
/* 145 */     GL11.glPopMatrix();
/* 146 */     super.drawScreen(par1, par2, par3);
/*     */ 
/*     */     
/* 149 */     if (this.selectedAlt == null) {
/* 150 */       this.login.enabled = false;
/* 151 */       this.remove.enabled = false;
/* 152 */       this.rename.enabled = false;
/*     */     } else {
/* 154 */       this.login.enabled = true;
/* 155 */       this.remove.enabled = true;
/* 156 */       this.rename.enabled = true;
/*     */     } 
/* 158 */     if (Keyboard.isKeyDown(200)) {
/* 159 */       this.offset -= 26;
/* 160 */       if (this.offset < 0) {
/* 161 */         this.offset = 0;
/*     */       }
/* 163 */     } else if (Keyboard.isKeyDown(208)) {
/* 164 */       this.offset += 26;
/* 165 */       if (this.offset < 0) {
/* 166 */         this.offset = 0;
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   public void initGui() {
/* 172 */     this.buttonList.add(new GuiButton(0, width / 2 + 4 + 50, height - 24, 100, 20, "Cancel"));
/* 173 */     this.login = new GuiButton(1, width / 2 - 154, height - 48, 100, 20, "Login");
/* 174 */     this.buttonList.add(this.login);
/* 175 */     this.remove = new GuiButton(2, width / 2 - 154, height - 24, 100, 20, "Remove");
/* 176 */     this.buttonList.add(this.remove);
/* 177 */     this.buttonList.add(new GuiButton(3, width / 2 + 4 + 50, height - 48, 100, 20, "Add"));
/* 178 */     this.buttonList.add(new GuiButton(4, width / 2 - 50, height - 48, 100, 20, "Direct Login"));
/* 179 */     this.rename = new GuiButton(6, width / 2 - 50, height - 24, 100, 20, "Edit");
/* 180 */     this.buttonList.add(this.rename);
/* 181 */     this.buttonList.add(new GuiButton(7, width - 100, 0, 100, 20, "Use Mojang"));
/* 182 */     this.buttonList.add(new GuiButton(8, width - 200, 0, 100, 20, "Use TheAltening"));
/* 183 */     this.login.enabled = false;
/* 184 */     this.remove.enabled = false;
/* 185 */     this.rename.enabled = false;
/*     */   }
/*     */   
/*     */   private boolean isAltInArea(int y2) {
/* 189 */     if (y2 - this.offset <= height - 50) {
/* 190 */       return true;
/*     */     }
/* 192 */     return false;
/*     */   }
/*     */   
/*     */   private boolean isMouseOverAlt(int x2, int y2, int y1) {
/* 196 */     if (x2 >= 52 && y2 >= y1 - 4 && x2 <= width - 52 && y2 <= y1 + 20 && x2 >= 0 && y2 >= 33 && x2 <= width && y2 <= height - 50) {
/* 197 */       return true;
/*     */     }
/* 199 */     return false;
/*     */   }
/*     */   
/*     */   protected void mouseClicked(int par1, int par2, int par3) throws IOException {
/* 203 */     if (this.offset < 0) {
/* 204 */       this.offset = 0;
/*     */     }
/* 206 */     int y2 = 38 - this.offset;
/* 207 */     for (Alt alt2 : AltManager.registry) {
/* 208 */       if (isMouseOverAlt(par1, par2, y2)) {
/* 209 */         if (alt2 == this.selectedAlt) {
/* 210 */           actionPerformed(this.buttonList.get(1));
/*     */           return;
/*     */         } 
/* 213 */         this.selectedAlt = alt2;
/*     */       } 
/* 215 */       y2 += 26;
/*     */     } 
/*     */     try {
/* 218 */       super.mouseClicked(par1, par2, par3);
/*     */     }
/* 220 */     catch (IOException e) {
/* 221 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean doesGuiPauseGame() {
/* 226 */     return false;
/*     */   }
/*     */   
/*     */   public void prepareScissorBox(float x2, float y2, float x22, float y22) {
/* 230 */     ScaledResolution scale = new ScaledResolution(this.mc, this.mc.displayWidth, this.mc.displayHeight);
/* 231 */     int factor = scale.getScaleFactor();
/* 232 */     GL11.glScissor((int)(x2 * factor), (int)((scale.getScaledHeight() - y22) * factor), (int)((x22 - x2) * factor), (int)((y22 - y2) * factor));
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\leap\alts\GuiAltManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */