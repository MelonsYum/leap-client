/*     */ package optifine;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.gui.FontRenderer;
/*     */ import net.minecraft.client.gui.GuiButton;
/*     */ import net.minecraft.client.gui.GuiScreen;
/*     */ import net.minecraft.client.gui.GuiVideoSettings;
/*     */ import net.minecraft.client.settings.GameSettings;
/*     */ 
/*     */ public class TooltipManager
/*     */ {
/*  14 */   private GuiScreen guiScreen = null;
/*  15 */   private int lastMouseX = 0;
/*  16 */   private int lastMouseY = 0;
/*  17 */   private long mouseStillTime = 0L;
/*     */ 
/*     */   
/*     */   public TooltipManager(GuiScreen guiScreen) {
/*  21 */     this.guiScreen = guiScreen;
/*     */   }
/*     */ 
/*     */   
/*     */   public void drawTooltips(int x, int y, List buttonList) {
/*  26 */     if (Math.abs(x - this.lastMouseX) <= 5 && Math.abs(y - this.lastMouseY) <= 5) {
/*     */       
/*  28 */       short activateDelay = 700;
/*     */       
/*  30 */       if (System.currentTimeMillis() >= this.mouseStillTime + activateDelay) {
/*     */         
/*  32 */         int x1 = GuiScreen.width / 2 - 150;
/*  33 */         int y1 = GuiScreen.height / 6 - 7;
/*     */         
/*  35 */         if (y <= y1 + 98)
/*     */         {
/*  37 */           y1 += 105;
/*     */         }
/*     */         
/*  40 */         int x2 = x1 + 150 + 150;
/*  41 */         int y2 = y1 + 84 + 10;
/*  42 */         GuiButton btn = getSelectedButton(x, y, buttonList);
/*     */         
/*  44 */         if (btn instanceof IOptionControl) {
/*     */           
/*  46 */           IOptionControl ctl = (IOptionControl)btn;
/*  47 */           GameSettings.Options option = ctl.getOption();
/*  48 */           String[] lines = getTooltipLines(option);
/*     */           
/*  50 */           if (lines == null) {
/*     */             return;
/*     */           }
/*     */ 
/*     */           
/*  55 */           GuiVideoSettings.drawGradientRect(this.guiScreen, x1, y1, x2, y2, -536870912, -536870912);
/*     */           
/*  57 */           for (int i = 0; i < lines.length; i++)
/*     */           {
/*  59 */             String line = lines[i];
/*  60 */             int col = 14540253;
/*     */             
/*  62 */             if (line.endsWith("!"))
/*     */             {
/*  64 */               col = 16719904;
/*     */             }
/*     */             
/*  67 */             FontRenderer fontRenderer = (Minecraft.getMinecraft()).fontRendererObj;
/*  68 */             fontRenderer.drawStringWithShadow(line, (x1 + 5), (y1 + 5 + i * 11), col);
/*     */           }
/*     */         
/*     */         } 
/*     */       } 
/*     */     } else {
/*     */       
/*  75 */       this.lastMouseX = x;
/*  76 */       this.lastMouseY = y;
/*  77 */       this.mouseStillTime = System.currentTimeMillis();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private GuiButton getSelectedButton(int x, int y, List<GuiButton> buttonList) {
/*  83 */     for (int k = 0; k < buttonList.size(); k++) {
/*     */       
/*  85 */       GuiButton btn = buttonList.get(k);
/*  86 */       int btnWidth = GuiVideoSettings.getButtonWidth(btn);
/*  87 */       int btnHeight = GuiVideoSettings.getButtonHeight(btn);
/*  88 */       boolean flag = (x >= btn.xPosition && y >= btn.yPosition && x < btn.xPosition + btnWidth && y < btn.yPosition + btnHeight);
/*     */       
/*  90 */       if (flag)
/*     */       {
/*  92 */         return btn;
/*     */       }
/*     */     } 
/*     */     
/*  96 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   private static String[] getTooltipLines(GameSettings.Options option) {
/* 101 */     return getTooltipLines(option.getEnumString());
/*     */   }
/*     */ 
/*     */   
/*     */   private static String[] getTooltipLines(String key) {
/* 106 */     ArrayList<String> list = new ArrayList();
/*     */     
/* 108 */     for (int lines = 0; lines < 10; lines++) {
/*     */       
/* 110 */       String lineKey = String.valueOf(key) + ".tooltip." + (lines + 1);
/* 111 */       String line = Lang.get(lineKey, null);
/*     */       
/* 113 */       if (line == null) {
/*     */         break;
/*     */       }
/*     */ 
/*     */       
/* 118 */       list.add(line);
/*     */     } 
/*     */     
/* 121 */     if (list.size() <= 0)
/*     */     {
/* 123 */       return null;
/*     */     }
/*     */ 
/*     */     
/* 127 */     String[] var5 = list.<String>toArray(new String[list.size()]);
/* 128 */     return var5;
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\optifine\TooltipManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */