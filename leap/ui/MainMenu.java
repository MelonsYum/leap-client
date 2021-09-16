/*     */ package leap.ui;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.util.ArrayList;
/*     */ import leap.Client;
/*     */ import leap.alts.Alt;
/*     */ import leap.alts.AltManager;
/*     */ import leap.alts.GuiAltManager;
/*     */ import leap.util.ColorUtil;
/*     */ import leap.util.JColor;
/*     */ import leap.util.Shape;
/*     */ import net.minecraft.client.gui.Gui;
/*     */ import net.minecraft.client.gui.GuiMultiplayer;
/*     */ import net.minecraft.client.gui.GuiOptions;
/*     */ import net.minecraft.client.gui.GuiScreen;
/*     */ import net.minecraft.client.gui.GuiSelectWorld;
/*     */ import net.minecraft.util.ResourceLocation;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MainMenu
/*     */   extends GuiScreen
/*     */ {
/*     */   public void initGUI() {}
/*     */   
/*     */   public void drawScreen(int mouseX, int mouseY, float partialTicks) {
/*  40 */     this.mc.getTextureManager().bindTexture(new ResourceLocation("pics/guiback.jpg"));
/*  41 */     drawModalRectWithCustomSizedTexture(0.0F, 0.0F, 0.0F, 0.0F, width, height, width, height);
/*     */     
/*  43 */     Gui.drawRect(0.0D, 0.0D, width, height, (new Color(0, 155, 255, 20)).getRGB());
/*     */ 
/*     */     
/*  46 */     Gui.drawRect(0.0D, 0.0D, width, 5.0D, ColorUtil.getRainbow(40.0F, 1.0F, 1.0F));
/*     */     
/*  48 */     drawGradientRect(0.0F, 0.0F, width, height, (new Color(0, 0, 255, 155)).getRGB(), (new Color(0, 255, 255, 155)).getRGB());
/*     */     
/*  50 */     int countAlts = 0;
/*     */     
/*  52 */     boolean MaxAltsReached = false;
/*  53 */     for (Alt alt2 : AltManager.registry) {
/*  54 */       String name = alt2.getMask().equals("") ? alt2.getUsername() : alt2.getMask();
/*  55 */       String pass = alt2.getPassword().equals("") ? "§cCracked" : alt2.getPassword().replaceAll(".", "*");
/*     */       
/*  57 */       if (countAlts > 10) {
/*  58 */         MaxAltsReached = true;
/*     */       }
/*  60 */       if (countAlts < 10) {
/*  61 */         MaxAltsReached = false;
/*     */       }
/*  63 */       if (!MaxAltsReached) {
/*     */         
/*  65 */         Client.customFontRenderer.drawCenteredString(name, (float)(width / 1.2D), (height / 4 + countAlts * 10), new JColor(-1));
/*  66 */         countAlts++;
/*     */       } 
/*     */     } 
/*  69 */     if (MaxAltsReached) {
/*  70 */       Client.customFontRenderer.drawCenteredString("...", (float)(width / 1.2D), (height / 4 + countAlts * 10), new JColor(-1));
/*     */     }
/*     */ 
/*     */     
/*  74 */     Client.customFontRenderer.drawCenteredString("Alts:", (float)(width / 1.2D), (height / 4 - 10), new JColor(-1));
/*     */     
/*  76 */     Shape.color((new Color(40, 40, 40, 20)).getRGB());
/*  77 */     Shape.drawRoundedRect(width / 1.2D - 50.0D, (height / 4 - 15), 100.0D, (14 + countAlts * 12), 3.0F);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  82 */     Shape.color((new Color(50, 50, 50, 180)).getRGB());
/*  83 */     Shape.drawRoundedRect((width / 2 - 40), (height / 2 - 77), 80.0D, 107.0D, 1.0F);
/*     */     
/*  85 */     Gui.drawRect((width / 2 - 40), (height / 2 - 77), (width / 2 + 40), (height / 2 - 72), ColorUtil.fade(new Color(0, 80, 104), 1, 1).getRGB());
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  94 */     Client.customFontRendererBig.drawString(String.valueOf(Client.name) + " " + Client.version, (width / 2 - 40), (height / 2 - 107), new JColor(-1));
/*  95 */     drawChangeLog();
/*     */     
/*  97 */     String[] buttons = { "Singleplayer", "Multiplayer", "Settings", "Alt Manager", "Quit" };
/*     */     
/*  99 */     int count = 0; byte b; int i; String[] arrayOfString1;
/* 100 */     for (i = (arrayOfString1 = buttons).length, b = 0; b < i; ) { String name = arrayOfString1[b];
/* 101 */       float x = (width / 2);
/* 102 */       float y = (height / 2 - 67 + count * 20);
/*     */ 
/*     */ 
/*     */       
/* 106 */       if (isHovered((width / 2 - 40), (height / 2 - 67 + count * 20), (width / 2 + 40), (height / 2 - 67 + count * 20 + 20), mouseX, mouseY)) {
/* 107 */         Shape.color((new Color(0, 0, 0, 40)).getRGB());
/* 108 */         Shape.drawRoundedRect((x - 40.0F), (y - 4.0F), 80.0D, 20.0D, 0.0F);
/*     */       } 
/*     */       
/* 111 */       Client.customFontRendererBig.drawCenteredString(name, (width / 2), (height / 2 - 67 + count * 20), new JColor(Color.white));
/* 112 */       count++;
/*     */       b++; }
/*     */   
/*     */   }
/*     */   public void mouseClicked(int mouseX, int mouseY, int button) {
/* 117 */     String[] buttons = { "Singleplayer", "Multiplayer", "Settings", "Alt Manager", "Quit" };
/*     */     
/* 119 */     int count = 0; byte b; int i;
/*     */     String[] arrayOfString1;
/* 121 */     for (i = (arrayOfString1 = buttons).length, b = 0; b < i; ) { String name = arrayOfString1[b];
/* 122 */       float x = (width / 2 - 40);
/* 123 */       float y = (height / 2 - 67 + count * 20);
/*     */       
/* 125 */       if (isHovered((width / 2 - 40), (height / 2 - 67 + count * 20), (width / 2 + 40), (height / 2 - 67 + count * 20 + 20), mouseX, mouseY)) {
/* 126 */         String str; switch ((str = name).hashCode()) { case -2064742086: if (!str.equals("Multiplayer")) {
/*     */               break;
/*     */             }
/*     */ 
/*     */             
/* 131 */             this.mc.displayGuiScreen((GuiScreen)new GuiMultiplayer(this));
/*     */             break;
/*     */           case -1657361418:
/*     */             if (!str.equals("Alt Manager")) {
/*     */               break;
/*     */             }
/* 137 */             this.mc.displayGuiScreen((GuiScreen)new GuiAltManager()); break;case -1500504759: if (!str.equals("Singleplayer"))
/*     */               break;  this.mc.displayGuiScreen((GuiScreen)new GuiSelectWorld(this)); break;
/*     */           case 2528879: if (!str.equals("Quit"))
/* 140 */               break;  this.mc.shutdown(); break;
/*     */           case 1499275331:
/*     */             if (!str.equals("Settings"))
/*     */               break;  this.mc.displayGuiScreen((GuiScreen)new GuiOptions(this, this.mc.gameSettings));
/*     */             break; }
/*     */       
/*     */       } 
/* 147 */       count++;
/*     */       b++; }
/*     */   
/*     */   }
/*     */   
/*     */   public void drawChangeLog() {
/* 153 */     ArrayList<String> changelog = new ArrayList<>();
/*     */     
/* 155 */     changelog.add("Changes:");
/* 156 */     changelog.add("Improved Scaffold");
/* 157 */     changelog.add("Improved KillAura");
/* 158 */     changelog.add("Fixed Font Issues");
/*     */     
/* 160 */     int count = 0;
/* 161 */     for (String log : changelog) {
/* 162 */       Client.customFontRenderer.drawStringWithShadow(log, 10.0D, (10 + count * 14), new JColor(-1));
/* 163 */       count++;
/*     */     } 
/* 165 */     Shape.color((new Color(255, 255, 255, 20)).getRGB());
/* 166 */     Shape.drawRoundedRect(4.0D, 7.0D, 120.0D, (count * 14), 2.0F);
/*     */   }
/*     */ 
/*     */   
/*     */   public void onGuiClosed() {}
/*     */ 
/*     */   
/*     */   public static boolean isHovered(float left, float top, float right, float bottom, int mouseX, int mouseY) {
/* 174 */     return (mouseX >= left && mouseY >= top && mouseX < right && mouseY < bottom);
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\lea\\ui\MainMenu.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */