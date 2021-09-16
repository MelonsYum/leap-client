/*     */ package leap.modules.render;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import leap.Client;
/*     */ import leap.events.Event;
/*     */ import leap.modules.Module;
/*     */ import leap.settings.BooleanSetting;
/*     */ import leap.settings.Setting;
/*     */ import leap.util.ColorUtil;
/*     */ import leap.util.JColor;
/*     */ import leap.util.Shape;
/*     */ import net.minecraft.client.gui.FontRenderer;
/*     */ import net.minecraft.client.gui.ScaledResolution;
/*     */ import org.lwjgl.input.Keyboard;
/*     */ import org.lwjgl.input.Mouse;
/*     */ 
/*     */ 
/*     */ public class KeyStrokes
/*     */   extends Module
/*     */ {
/*  21 */   public BooleanSetting rainbow = new BooleanSetting("Rainbow", true);
/*     */   
/*     */   public KeyStrokes() {
/*  24 */     super("KeyStrokes", 0, Module.Category.RENDER);
/*  25 */     addSettings(new Setting[] { (Setting)this.rainbow });
/*     */   }
/*     */   
/*     */   public void onEnable() {
/*  29 */     super.onEnable();
/*     */   }
/*     */   
/*     */   public void onDisable() {
/*  33 */     super.onDisable();
/*     */   }
/*     */   
/*     */   public void onEvent(Event e) {
/*  37 */     if (e instanceof leap.events.listeners.EventRenderGUI) {
/*     */       
/*  39 */       FontRenderer fr = mc.fontRendererObj;
/*  40 */       ScaledResolution sr = new ScaledResolution(mc, mc.displayWidth, mc.displayHeight);
/*  41 */       int count = 0;
/*     */ 
/*     */       
/*  44 */       int wAlpha = Keyboard.isKeyDown(mc.gameSettings.keyBindForward.getKeyCode()) ? 255 : 1;
/*  45 */       int aAlpha = Keyboard.isKeyDown(mc.gameSettings.keyBindLeft.getKeyCode()) ? 255 : 1;
/*  46 */       int sAlpha = Keyboard.isKeyDown(mc.gameSettings.keyBindBack.getKeyCode()) ? 255 : 1;
/*  47 */       int dAlpha = Keyboard.isKeyDown(mc.gameSettings.keyBindRight.getKeyCode()) ? 255 : 1;
/*     */       
/*  49 */       int leftAlpha = Mouse.isButtonDown(0) ? 255 : 1;
/*  50 */       int rightAlpha = Mouse.isButtonDown(1) ? 255 : 1;
/*     */ 
/*     */       
/*  53 */       Shape.color((new Color(wAlpha, wAlpha, wAlpha, 150)).getRGB());
/*  54 */       Shape.drawRoundedRect(38.0D, 174.0D, 35.0D, 30.0D, 1.0F);
/*     */ 
/*     */ 
/*     */       
/*  58 */       Shape.color((new Color(aAlpha, aAlpha, aAlpha, 150)).getRGB());
/*  59 */       Shape.drawRoundedRect(1.0D, 205.0D, 35.0D, 30.0D, 1.0F);
/*     */ 
/*     */ 
/*     */       
/*  63 */       Shape.color((new Color(sAlpha, sAlpha, sAlpha, 150)).getRGB());
/*  64 */       Shape.drawRoundedRect(38.0D, 205.0D, 35.0D, 30.0D, 1.0F);
/*     */ 
/*     */       
/*  67 */       Shape.color((new Color(dAlpha, dAlpha, dAlpha, 150)).getRGB());
/*  68 */       Shape.drawRoundedRect(74.0D, 205.0D, 35.0D, 30.0D, 1.0F);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  75 */       Shape.color((new Color(leftAlpha, leftAlpha, leftAlpha, 150)).getRGB());
/*  76 */       Shape.drawRoundedRect(1.0D, 237.0D, 50.0D, 28.0D, 1.0F);
/*     */ 
/*     */ 
/*     */       
/*  80 */       Shape.color((new Color(rightAlpha, rightAlpha, rightAlpha, 150)).getRGB());
/*  81 */       Shape.drawRoundedRect(52.0D, 237.0D, 57.0D, 28.0D, 1.0F);
/*     */       
/*  83 */       if (this.rainbow.isEnabled()) {
/*  84 */         int fontcolor = ColorUtil.getRainbow(4.0F, 0.8F, 1.0F, (count * 100));
/*     */       } else {
/*  86 */         int fontcolor = Color.white.getRGB();
/*     */       } 
/*     */       
/*  89 */       Client.customFontRendererBig.drawString("W", 48.0D, 185.0D, new JColor(ColorUtil.getRainbow(4.0F, 0.8F, 1.0F, (count * 100))));
/*  90 */       count++;
/*  91 */       Client.customFontRendererBig.drawString("A", 18.0D, 217.0D, new JColor(ColorUtil.getRainbow(4.0F, 0.8F, 1.0F, (count * 100))));
/*  92 */       count++;
/*  93 */       Client.customFontRendererBig.drawString("S", 48.0D, 217.0D, new JColor(ColorUtil.getRainbow(4.0F, 0.8F, 1.0F, (count * 100))));
/*  94 */       count++;
/*  95 */       Client.customFontRendererBig.drawString("D", 79.0D, 217.0D, new JColor(ColorUtil.getRainbow(4.0F, 0.8F, 1.0F, (count * 100))));
/*  96 */       count++;
/*  97 */       Client.customFontRendererBig.drawString("LMB", 18.0D, 242.0D, new JColor(ColorUtil.getRainbow(4.0F, 0.8F, 1.0F, (count * 100))));
/*  98 */       count++;
/*  99 */       Client.customFontRendererBig.drawString("RMB", 65.0D, 242.0D, new JColor(ColorUtil.getRainbow(4.0F, 0.8F, 1.0F, (count * 100))));
/* 100 */       count++;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\leap\modules\render\KeyStrokes.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */