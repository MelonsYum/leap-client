/*     */ package leap.util;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ 
/*     */ public class ColorUtil
/*     */ {
/*     */   public static int getRainbow(float seconds, float saturation, float brightness) {
/*  10 */     float hue = (float)(System.currentTimeMillis() % (int)(seconds * 1000.0F)) / seconds * 1000.0F;
/*  11 */     int color = Color.HSBtoRGB(hue, saturation, brightness);
/*  12 */     return color;
/*     */   }
/*     */   
/*     */   public static int getRainbow(float seconds, float saturation, float brightness, long index) {
/*  16 */     float hue = (float)((System.currentTimeMillis() + index) % (int)(seconds * 1000.0F)) / seconds * 1000.0F;
/*  17 */     int color = Color.HSBtoRGB(hue, saturation, brightness);
/*  18 */     return color;
/*     */   }
/*     */   
/*     */   public static int getRainbowOverlay(float seconds, float saturation, float brightness) {
/*  22 */     float hue = (float)(System.currentTimeMillis() % (int)(seconds * 1000.0F)) / seconds * 1000.0F;
/*  23 */     int color = (new Color(hue, saturation, brightness, 100.0F)).getRGB();
/*  24 */     return color;
/*     */   }
/*     */   
/*     */   public static int getAstoGay(int delay, float offset) {
/*  28 */     int yStart = 20;
/*  29 */     float speed = 3000.0F;
/*  30 */     float index = 0.3F;
/*  31 */     float hue = (float)(System.currentTimeMillis() % delay) + offset;
/*  32 */     while (hue > speed) {
/*  33 */       hue -= speed;
/*     */     }
/*  35 */     hue /= speed;
/*  36 */     if (hue > 0.5D) {
/*  37 */       hue = 0.5F - hue - 0.5F;
/*     */     }
/*  39 */     hue += 0.5F;
/*  40 */     return Color.HSBtoRGB(hue, 0.5F, 1.0F);
/*     */   }
/*     */   
/*     */   public static Color flash(Color color, float index, int count) {
/*  44 */     float[] hsb = new float[3];
/*  45 */     Color.RGBtoHSB(color.getRed(), color.getGreen(), color.getBlue(), hsb);
/*  46 */     float brightness = Math.abs(((float)(System.currentTimeMillis() % 200L) / 500.0F + index / count * 2.0F) % 2.0F - 1.0F);
/*  47 */     brightness = 0.5F + 0.5F * brightness;
/*  48 */     hsb[2] = brightness % 2.0F;
/*  49 */     return new Color(Color.HSBtoRGB(hsb[0], hsb[1], hsb[2]));
/*     */   }
/*     */   
/*     */   public static Color fade(Color color, int index, int count) {
/*  53 */     float[] hsb = new float[3];
/*  54 */     Color.RGBtoHSB(color.getRed(), color.getGreen(), color.getBlue(), hsb);
/*  55 */     float brightness = Math.abs(((float)(System.currentTimeMillis() % 2000L) / 1000.0F + index / count * 2.0F) % 2.0F - 1.0F);
/*  56 */     brightness = 0.5F + 0.5F * brightness;
/*  57 */     hsb[2] = brightness % 2.0F;
/*  58 */     return new Color(Color.HSBtoRGB(hsb[0], hsb[1], hsb[2]));
/*     */   }
/*     */   
/*     */   public static Color blendColors(float[] fractions, Color[] colors, float progress) {
/*  62 */     if (fractions.length == colors.length) {
/*  63 */       int[] indices = getFractionIndices(fractions, progress);
/*  64 */       float[] range = { fractions[indices[0]], fractions[indices[1]] };
/*  65 */       Color[] colorRange = { colors[indices[0]], colors[indices[1]] };
/*  66 */       float max = range[1] - range[0];
/*  67 */       float value = progress - range[0];
/*  68 */       float weight = value / max;
/*  69 */       Color color = blend(colorRange[0], colorRange[1], (1.0F - weight));
/*  70 */       return color;
/*     */     } 
/*  72 */     throw new IllegalArgumentException("Fractions and colours must have equal number of elements");
/*     */   }
/*     */ 
/*     */   
/*     */   public static int[] getFractionIndices(float[] fractions, float progress) {
/*  77 */     int[] range = new int[2];
/*     */     
/*     */     int startPoint;
/*  80 */     for (startPoint = 0; startPoint < fractions.length && fractions[startPoint] <= progress; startPoint++);
/*     */ 
/*     */     
/*  83 */     if (startPoint >= fractions.length) {
/*  84 */       startPoint = fractions.length - 1;
/*     */     }
/*     */     
/*  87 */     range[0] = startPoint - 1;
/*  88 */     range[1] = startPoint;
/*  89 */     return range;
/*     */   }
/*     */   
/*     */   public static Color blend(Color color1, Color color2, double ratio) {
/*  93 */     float r = (float)ratio;
/*  94 */     float ir = 1.0F - r;
/*  95 */     float[] rgb1 = color1.getColorComponents(new float[3]);
/*  96 */     float[] rgb2 = color2.getColorComponents(new float[3]);
/*  97 */     float red = rgb1[0] * r + rgb2[0] * ir;
/*  98 */     float green = rgb1[1] * r + rgb2[1] * ir;
/*  99 */     float blue = rgb1[2] * r + rgb2[2] * ir;
/* 100 */     if (red < 0.0F) {
/* 101 */       red = 0.0F;
/* 102 */     } else if (red > 255.0F) {
/* 103 */       red = 255.0F;
/*     */     } 
/*     */     
/* 106 */     if (green < 0.0F) {
/* 107 */       green = 0.0F;
/* 108 */     } else if (green > 255.0F) {
/* 109 */       green = 255.0F;
/*     */     } 
/*     */     
/* 112 */     if (blue < 0.0F) {
/* 113 */       blue = 0.0F;
/* 114 */     } else if (blue > 255.0F) {
/* 115 */       blue = 255.0F;
/*     */     } 
/*     */     
/* 118 */     Color color3 = null;
/*     */     
/*     */     try {
/* 121 */       color3 = new Color(red, green, blue);
/* 122 */     } catch (IllegalArgumentException illegalArgumentException) {}
/*     */ 
/*     */     
/* 125 */     return color3;
/*     */   }
/*     */   
/*     */   public static List<Color> getColorsList() {
/* 129 */     List<Color> list = new ArrayList<>();
/* 130 */     list.add(Color.BLACK);
/* 131 */     list.add(Color.BLUE);
/* 132 */     list.add(Color.CYAN);
/* 133 */     list.add(Color.DARK_GRAY);
/* 134 */     list.add(Color.GRAY);
/* 135 */     list.add(Color.GREEN);
/* 136 */     list.add(Color.LIGHT_GRAY);
/* 137 */     list.add(Color.MAGENTA);
/* 138 */     list.add(Color.ORANGE);
/* 139 */     list.add(Color.PINK);
/* 140 */     list.add(Color.RED);
/* 141 */     list.add(Color.WHITE);
/* 142 */     list.add(Color.YELLOW);
/* 143 */     return list;
/*     */   }
/*     */   
/*     */   public static Color getRandomColor() {
/* 147 */     Color color = null;
/* 148 */     int random = RandomUtil.randomNumber(getColorsList().size(), 0.0D);
/* 149 */     color = getColorsList().get(random);
/* 150 */     return color;
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\lea\\util\ColorUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */