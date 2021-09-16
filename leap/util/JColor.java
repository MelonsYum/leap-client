/*    */ package leap.util;
/*    */ 
/*    */ import java.awt.Color;
/*    */ import net.minecraft.client.renderer.GlStateManager;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class JColor
/*    */   extends Color
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   
/*    */   public JColor(int rgb) {
/* 19 */     super(rgb);
/*    */   }
/*    */   
/*    */   public JColor(int rgba, boolean hasalpha) {
/* 23 */     super(rgba, hasalpha);
/*    */   }
/*    */   
/*    */   public JColor(int r, int g, int b) {
/* 27 */     super(r, g, b);
/*    */   }
/*    */   
/*    */   public JColor(int r, int g, int b, int a) {
/* 31 */     super(r, g, b, a);
/*    */   }
/*    */   
/*    */   public JColor(Color color) {
/* 35 */     super(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha());
/*    */   }
/*    */   
/*    */   public JColor(JColor color, int a) {
/* 39 */     super(color.getRed(), color.getGreen(), color.getBlue(), a);
/*    */   }
/*    */   
/*    */   public static JColor fromHSB(float hue, float saturation, float brightness) {
/* 43 */     return new JColor(Color.getHSBColor(hue, saturation, brightness));
/*    */   }
/*    */   
/*    */   public float getHue() {
/* 47 */     return RGBtoHSB(getRed(), getGreen(), getBlue(), null)[0];
/*    */   }
/*    */   
/*    */   public float getSaturation() {
/* 51 */     return RGBtoHSB(getRed(), getGreen(), getBlue(), null)[1];
/*    */   }
/*    */   
/*    */   public float getBrightness() {
/* 55 */     return RGBtoHSB(getRed(), getGreen(), getBlue(), null)[2];
/*    */   }
/*    */   
/*    */   public void glColor() {
/* 59 */     GlStateManager.color(getRed() / 255.0F, getGreen() / 255.0F, getBlue() / 255.0F, getAlpha() / 255.0F);
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\lea\\util\JColor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */