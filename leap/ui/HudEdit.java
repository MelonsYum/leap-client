/*     */ package leap.ui;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import leap.Client;
/*     */ import leap.modules.Module;
/*     */ import leap.modules.combat.TargetHUD;
/*     */ import leap.util.JColor;
/*     */ import leap.util.Shape;
/*     */ import net.minecraft.client.gui.GuiScreen;
/*     */ import org.lwjgl.input.Mouse;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class HudEdit
/*     */   extends GuiScreen
/*     */ {
/*     */   public static Module.Category dragging;
/*     */   public static int startX;
/*     */   public static int startY;
/*     */   private boolean clicked;
/*     */   
/*     */   public HudEdit() {
/*  41 */     this.clicked = false;
/*     */   }
/*     */   
/*     */   public void drawScreen(int mouseX, int mouseY, float partialTicks) {
/*  45 */     Shape.color((new Color(50, 50, 50, 50)).getRGB());
/*  46 */     Shape.drawRoundedRect(5.0D, 1.0D, (GuiScreen.width - 12), 40.0D, 4.0F);
/*     */     
/*  48 */     Client.customFontRenderer.drawString("Hud Editor", (GuiScreen.width / 2), 8.0D, new JColor(new Color(150, 200, 255)));
/*     */     
/*  50 */     Shape.color((new Color(255, 200, 120)).getRGB());
/*  51 */     Shape.drawRoundedRect((GuiScreen.width / 2 - 20), 24.0D, 100.0D, 10.0D, 1.0F);
/*     */     
/*  53 */     handleClickShit(mouseX, mouseY, -1, Handle.DRAW);
/*     */   }
/*     */   
/*     */   public void mouseClicked(int mouseX, int mouseY, int button) {
/*  57 */     handleClickShit(mouseX, mouseY, button, Handle.CLICK);
/*     */   }
/*     */   
/*     */   public void mouseReleased(int mouseX, int mouseY, int button) {
/*  61 */     handleClickShit(mouseX, mouseY, button, Handle.RELEASE);
/*     */   }
/*     */ 
/*     */   
/*     */   public void handleClickShit(int mouseX, int mouseY, int button, Handle type) {
/*  66 */     if (type == Handle.CLICK)
/*     */     {
/*  68 */       this.clicked = true;
/*     */     }
/*     */     
/*  71 */     if (type == Handle.RELEASE) {
/*  72 */       startX = 0;
/*  73 */       startY = 0;
/*  74 */       this.clicked = false;
/*     */     } 
/*     */     
/*  77 */     if (type == Handle.DRAW) {
/*     */       
/*  79 */       if (HUD.MouseAddLogoX < 0.0D || HUD.MouseAddLogoY < 0.0D || HUD.MouseAddLogoX > GuiScreen.width || HUD.MouseAddLogoY > GuiScreen.height) {
/*  80 */         HUD.MouseAddLogoX = 0.0D;
/*  81 */         HUD.MouseAddLogoY = 0.0D;
/*     */       } 
/*     */       
/*  84 */       if (this.clicked = (Mouse.isButtonDown(0) && isHovered((float)HUD.MouseAddLogoX, (float)HUD.MouseAddLogoY, (float)HUD.MouseAddLogoX + 70.0F, (float)HUD.MouseAddLogoY + 30.0F, mouseX, mouseY) && HUD.logo.isEnabled())) {
/*     */         
/*  86 */         if (startX == 0 || startY == 0) {
/*  87 */           startX = (int)(mouseX - HUD.MouseAddLogoX);
/*  88 */           startY = (int)(mouseY - HUD.MouseAddLogoY);
/*     */         } 
/*     */         
/*  91 */         HUD.MouseAddLogoX = (mouseX - startX);
/*  92 */         HUD.MouseAddLogoY = (mouseY - startY);
/*     */       } 
/*     */       
/*  95 */       if (this.clicked = (Mouse.isButtonDown(0) && isHovered((float)TargetHUD.MouseAddStatsX, (float)TargetHUD.MouseAddStatsY, (float)TargetHUD.MouseAddStatsX + 110.0F, (float)TargetHUD.MouseAddStatsY + 40.0F, mouseX, mouseY) && TargetHUD.stats.isEnabled())) {
/*     */         
/*  97 */         if (startX == 0 || startY == 0) {
/*  98 */           startX = (int)(mouseX - TargetHUD.MouseAddStatsX);
/*  99 */           startY = (int)(mouseY - TargetHUD.MouseAddStatsY);
/*     */         } 
/*     */         
/* 102 */         TargetHUD.MouseAddStatsX = (mouseX - startX);
/* 103 */         TargetHUD.MouseAddStatsY = (mouseY - startY);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean isHovered(float left, float top, float right, float bottom, int mouseX, int mouseY) {
/* 111 */     return (mouseX >= left && mouseY >= top && mouseX < right && mouseY < bottom);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean doesGuiPauseGame() {
/* 116 */     return false;
/*     */   }
/*     */   
/*     */   enum Handle {
/* 120 */     DRAW,
/* 121 */     CLICK,
/* 122 */     RELEASE;
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\lea\\ui\HudEdit.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */