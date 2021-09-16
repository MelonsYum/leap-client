/*    */ package leap.util;
/*    */ 
/*    */ import java.awt.Color;
/*    */ import java.util.function.Supplier;
/*    */ 
/*    */ public enum ColorManager {
/*  7 */   BLUE(() -> new Color(116, 202, 255)),
/*  8 */   NICE_BLUE(() -> new Color(116, 202, 255)),
/*  9 */   DARK_PURPLE(() -> new Color(133, 46, 215)),
/* 10 */   GREEN(() -> new Color(0, 255, 138)),
/* 11 */   PURPLE(() -> new Color(198, 139, 255)),
/* 12 */   WHITE(() -> Color.WHITE);
/*    */   
/*    */   private final Supplier<Color> colorSupplier;
/*    */   
/*    */   ColorManager(Supplier<Color> colorSupplier) {
/* 17 */     this.colorSupplier = colorSupplier;
/*    */   }
/*    */   
/*    */   public static Color fade(Color color) {
/* 21 */     return fade(color, 2, 100);
/*    */   }
/*    */   
/*    */   public static Color flash(Color color) {
/* 25 */     return flash(color, 2, 10);
/*    */   }
/*    */   
/*    */   public static Color fade(Color color, int index, int count) {
/* 29 */     float[] hsb = new float[3];
/* 30 */     Color.RGBtoHSB(color.getRed(), color.getGreen(), color.getBlue(), hsb);
/* 31 */     float brightness = Math.abs(((float)(System.currentTimeMillis() % 2000L) / 1000.0F + index / count * 2.0F) % 2.0F - 1.0F);
/* 32 */     brightness = 0.5F + 0.5F * brightness;
/* 33 */     hsb[2] = brightness % 2.0F;
/* 34 */     return new Color(Color.HSBtoRGB(hsb[0], hsb[1], hsb[2]));
/*    */   }
/*    */   
/*    */   public static Color flash(Color color, int index, int count) {
/* 38 */     float[] hsb = new float[3];
/* 39 */     Color.RGBtoHSB(color.getRed(), color.getGreen(), color.getBlue(), hsb);
/* 40 */     float brightness = Math.abs(((float)(System.currentTimeMillis() % 200L) / 500.0F + index / count * 2.0F) % 2.0F - 1.0F);
/* 41 */     brightness = 0.5F + 0.5F * brightness;
/* 42 */     hsb[2] = brightness % 2.0F;
/* 43 */     return new Color(Color.HSBtoRGB(hsb[0], hsb[1], hsb[2]));
/*    */   }
/*    */   
/*    */   public static Color rainbow(int index, double speed) {
/* 47 */     int angle = (int)((System.currentTimeMillis() / speed + index) % 360.0D);
/* 48 */     float hue = angle / 360.0F;
/* 49 */     int color = Color.HSBtoRGB(hue, 1.0F, 1.0F);
/* 50 */     return new Color(color);
/*    */   }
/*    */   
/*    */   public static Color rainbow(int index, int speed, float saturation, float brightness, float opacity) {
/* 54 */     int angle = (int)((System.currentTimeMillis() / speed + index) % 360L);
/* 55 */     float hue = angle / 360.0F;
/* 56 */     int color = Color.HSBtoRGB(hue, saturation, brightness);
/* 57 */     Color obj = new Color(color);
/* 58 */     return new Color(obj.getRed(), obj.getGreen(), obj.getBlue(), Math.max(0, Math.min(255, (int)(opacity * 255.0F))));
/*    */   }
/*    */   
/*    */   public static Color astolfo(int index, int speed, float saturation, float brightness, float opacity) {
/* 62 */     int angle = (int)((System.currentTimeMillis() / speed + index) % 360L);
/* 63 */     angle = ((angle > 180) ? (360 - angle) : angle) + 180;
/* 64 */     float hue = angle / 360.0F;
/*    */     
/* 66 */     int color = Color.HSBtoRGB(brightness, saturation, hue);
/* 67 */     Color obj = new Color(color);
/* 68 */     return new Color(obj.getRed(), obj.getGreen(), obj.getBlue(), Math.max(0, Math.min(255, (int)(opacity * 255.0F))));
/*    */   }
/*    */ 
/*    */   
/*    */   public Color getColor() {
/* 73 */     return this.colorSupplier.get();
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\lea\\util\ColorManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */