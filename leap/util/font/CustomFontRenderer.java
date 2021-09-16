/*     */ package leap.util.font;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.awt.Font;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import leap.util.JColor;
/*     */ import net.minecraft.client.renderer.GlStateManager;
/*     */ import net.minecraft.client.renderer.texture.DynamicTexture;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class CustomFontRenderer
/*     */   extends CustomFont
/*     */ {
/*  17 */   protected CustomFont.CharData[] boldChars = new CustomFont.CharData[1024];
/*  18 */   protected CustomFont.CharData[] italicChars = new CustomFont.CharData[1024];
/*  19 */   protected CustomFont.CharData[] boldItalicChars = new CustomFont.CharData[1024];
/*     */   
/*  21 */   private final int[] colorCode = new int[32];
/*  22 */   private final String colorcodeIdentifiers = "0123456789abcdefklmnor"; protected DynamicTexture texBold;
/*     */   
/*     */   public CustomFontRenderer(Font font, boolean antiAlias, boolean fractionalMetrics) {
/*  25 */     super(font, antiAlias, fractionalMetrics);
/*  26 */     setupMinecraftColorcodes();
/*  27 */     setupBoldItalicIDs();
/*     */   }
/*     */   protected DynamicTexture texItalic; protected DynamicTexture texItalicBold;
/*     */   public float drawStringWithShadow(String text, double x, double y, JColor color) {
/*  31 */     float shadowWidth = drawString(text, x + 1.0D, y + 1.0D, color, true);
/*  32 */     return Math.max(shadowWidth, drawString(text, x, y, color, false));
/*     */   }
/*     */   
/*     */   public float drawString(String text, double d, double e, JColor color) {
/*  36 */     return drawString(text, d, e, color, false);
/*     */   }
/*     */   
/*     */   public float drawCenteredStringWithShadow(String text, float x, float y, JColor color) {
/*  40 */     return drawStringWithShadow(text, (x - (getStringWidth(text) / 2)), y, color);
/*     */   }
/*     */   
/*     */   public float drawCenteredString(String text, float x, float y, JColor color) {
/*  44 */     return drawString(text, (x - (getStringWidth(text) / 2)), y, color);
/*     */   }
/*     */   
/*     */   public float drawString(String text, double x, double y, JColor gsColor, boolean shadow) {
/*  48 */     x--;
/*  49 */     y -= 2.0D;
/*  50 */     JColor color = new JColor((Color)gsColor);
/*  51 */     if (text == null) return 0.0F; 
/*  52 */     if (color.getRed() == 255 && color.getGreen() == 255 && color.getBlue() == 255 && color.getAlpha() == 32) color = new JColor(255, 255, 255); 
/*  53 */     if (color.getAlpha() < 4) color = new JColor(color, 255); 
/*  54 */     if (shadow) color = new JColor(color.getRed() / 4, color.getGreen() / 4, color.getBlue() / 4, color.getAlpha());
/*     */     
/*  56 */     CustomFont.CharData[] currentData = this.charData;
/*  57 */     boolean randomCase = false;
/*  58 */     boolean bold = false;
/*  59 */     boolean italic = false;
/*  60 */     boolean strikethrough = false;
/*  61 */     boolean underline = false;
/*  62 */     boolean render = true;
/*  63 */     x *= 2.0D;
/*  64 */     y *= 2.0D;
/*  65 */     if (render) {
/*  66 */       GlStateManager.pushMatrix();
/*  67 */       GlStateManager.scale(0.5D, 0.5D, 0.5D);
/*  68 */       GlStateManager.enableBlend();
/*  69 */       GlStateManager.blendFunc(770, 771);
/*  70 */       GlStateManager.color(color.getRed() / 255.0F, color.getGreen() / 255.0F, color.getBlue() / 255.0F, color.getAlpha() / 255.0F);
/*  71 */       int size = text.length();
/*  72 */       GL11.glBindTexture(3553, this.tex.getGlTextureId());
/*  73 */       for (int i = 0; i < size; i++) {
/*  74 */         char character = text.charAt(i);
/*  75 */         if (character == '§' && i < size) {
/*  76 */           int colorIndex = 21;
/*     */           try {
/*  78 */             colorIndex = "0123456789abcdefklmnor".indexOf(text.charAt(i + 1));
/*     */           }
/*  80 */           catch (Exception exception) {}
/*     */ 
/*     */           
/*  83 */           if (colorIndex < 16) {
/*  84 */             bold = false;
/*  85 */             italic = false;
/*  86 */             randomCase = false;
/*  87 */             underline = false;
/*  88 */             strikethrough = false;
/*  89 */             GL11.glBindTexture(3553, 
/*  90 */                 this.tex.getGlTextureId());
/*  91 */             currentData = this.charData;
/*  92 */             if (colorIndex < 0 || colorIndex > 15) colorIndex = 15; 
/*  93 */             if (shadow) colorIndex += 16; 
/*  94 */             int colorcode = this.colorCode[colorIndex];
/*  95 */             GlStateManager.color((colorcode >> 16 & 0xFF) / 255.0F, (colorcode >> 8 & 0xFF) / 255.0F, (colorcode & 0xFF) / 255.0F, color.getAlpha());
/*     */           }
/*  97 */           else if (colorIndex == 16) {
/*  98 */             randomCase = true;
/*     */           }
/* 100 */           else if (colorIndex == 17) {
/* 101 */             bold = true;
/* 102 */             if (italic) {
/* 103 */               GL11.glBindTexture(3553, 
/* 104 */                   this.texItalicBold.getGlTextureId());
/* 105 */               currentData = this.boldItalicChars;
/*     */             } else {
/*     */               
/* 108 */               GL11.glBindTexture(3553, 
/* 109 */                   this.texBold.getGlTextureId());
/* 110 */               currentData = this.boldChars;
/*     */             }
/*     */           
/* 113 */           } else if (colorIndex == 18) {
/* 114 */             strikethrough = true;
/*     */           }
/* 116 */           else if (colorIndex == 19) {
/* 117 */             underline = true;
/*     */           }
/* 119 */           else if (colorIndex == 20) {
/* 120 */             italic = true;
/* 121 */             if (bold) {
/* 122 */               GL11.glBindTexture(3553, 
/* 123 */                   this.texItalicBold.getGlTextureId());
/* 124 */               currentData = this.boldItalicChars;
/*     */             } else {
/*     */               
/* 127 */               GL11.glBindTexture(3553, 
/* 128 */                   this.texItalic.getGlTextureId());
/* 129 */               currentData = this.italicChars;
/*     */             }
/*     */           
/* 132 */           } else if (colorIndex == 21) {
/* 133 */             bold = false;
/* 134 */             italic = false;
/* 135 */             randomCase = false;
/* 136 */             underline = false;
/* 137 */             strikethrough = false;
/* 138 */             GlStateManager.color(color.getRed() / 255.0F, color.getGreen() / 255.0F, color.getBlue() / 255.0F, color.getAlpha() / 255.0F);
/* 139 */             GL11.glBindTexture(3553, 
/* 140 */                 this.tex.getGlTextureId());
/* 141 */             currentData = this.charData;
/*     */           } 
/* 143 */           i++;
/*     */         }
/* 145 */         else if (character < currentData.length && character >= '\000') {
/* 146 */           GL11.glBegin(4);
/* 147 */           drawChar(currentData, character, (float)x, (float)y);
/* 148 */           GL11.glEnd();
/* 149 */           if (strikethrough) drawLine(x, y + ((currentData[character]).height / 2), x + (currentData[character]).width - 8.0D, y + ((currentData[character]).height / 2), 1.0F); 
/* 150 */           if (underline) drawLine(x, y + (currentData[character]).height - 2.0D, x + (currentData[character]).width - 8.0D, y + (currentData[character]).height - 2.0D, 1.0F); 
/* 151 */           x += ((currentData[character]).width - 8 + this.charOffset);
/*     */         } 
/*     */       } 
/* 154 */       GL11.glHint(3155, 4352);
/* 155 */       GlStateManager.popMatrix();
/*     */     } 
/* 157 */     return (float)x / 2.0F;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getStringWidth(String text) {
/* 162 */     if (text == null) {
/* 163 */       return 0;
/*     */     }
/* 165 */     int width = 0;
/* 166 */     CustomFont.CharData[] currentData = this.charData;
/* 167 */     boolean bold = false;
/* 168 */     boolean italic = false;
/* 169 */     int size = text.length();
/*     */     
/* 171 */     for (int i = 0; i < size; i++) {
/* 172 */       char character = text.charAt(i);
/* 173 */       if (character == '§' && i < size) {
/* 174 */         int colorIndex = "0123456789abcdefklmnor".indexOf(character);
/* 175 */         if (colorIndex < 16) {
/* 176 */           bold = false;
/* 177 */           italic = false;
/* 178 */         } else if (colorIndex == 17) {
/* 179 */           bold = true;
/* 180 */           if (italic) { currentData = this.boldItalicChars; }
/* 181 */           else { currentData = this.boldChars; } 
/* 182 */         } else if (colorIndex == 20) {
/* 183 */           italic = true;
/* 184 */           if (bold) { currentData = this.boldItalicChars; }
/* 185 */           else { currentData = this.italicChars; } 
/* 186 */         } else if (colorIndex == 21) {
/* 187 */           bold = false;
/* 188 */           italic = false;
/* 189 */           currentData = this.charData;
/*     */         } 
/* 191 */         i++;
/* 192 */       } else if (character < currentData.length && character >= '\000') {
/* 193 */         width += (currentData[character]).width - 8 + this.charOffset;
/*     */       } 
/*     */     } 
/*     */     
/* 197 */     return width / 2;
/*     */   }
/*     */   
/*     */   public void setFont(Font font) {
/* 201 */     super.setFont(font);
/* 202 */     setupBoldItalicIDs();
/*     */   }
/*     */   
/*     */   public void setAntiAlias(boolean antiAlias) {
/* 206 */     super.setAntiAlias(antiAlias);
/* 207 */     setupBoldItalicIDs();
/*     */   }
/*     */   
/*     */   public void setFractionalMetrics(boolean fractionalMetrics) {
/* 211 */     super.setFractionalMetrics(fractionalMetrics);
/* 212 */     setupBoldItalicIDs();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void setupBoldItalicIDs() {
/* 220 */     this.texBold = setupTexture(this.font.deriveFont(1), this.antiAlias, this.fractionalMetrics, this.boldChars);
/* 221 */     this.texItalic = setupTexture(this.font.deriveFont(2), this.antiAlias, this.fractionalMetrics, this.italicChars);
/* 222 */     this.texItalicBold = setupTexture(this.font.deriveFont(3), this.antiAlias, this.fractionalMetrics, this.boldItalicChars);
/*     */   }
/*     */   
/*     */   private void drawLine(double x, double y, double x1, double y1, float width) {
/* 226 */     GL11.glDisable(3553);
/* 227 */     GL11.glLineWidth(width);
/* 228 */     GL11.glBegin(1);
/* 229 */     GL11.glVertex2d(x, y);
/* 230 */     GL11.glVertex2d(x1, y1);
/* 231 */     GL11.glEnd();
/* 232 */     GL11.glEnable(3553);
/*     */   }
/*     */   
/*     */   public List<String> wrapWords(String text, double width) {
/* 236 */     List<String> finalWords = new ArrayList<>();
/* 237 */     if (getStringWidth(text) > width) {
/* 238 */       String[] words = text.split(" ");
/* 239 */       String currentWord = "";
/* 240 */       char lastColorCode = Character.MAX_VALUE; byte b; int i;
/*     */       String[] arrayOfString1;
/* 242 */       for (i = (arrayOfString1 = words).length, b = 0; b < i; ) { String word = arrayOfString1[b];
/* 243 */         for (int j = 0; j < (word.toCharArray()).length; j++) {
/* 244 */           char c = word.toCharArray()[j];
/*     */           
/* 246 */           if (c == '§' && j < (word.toCharArray()).length - 1) {
/* 247 */             lastColorCode = word.toCharArray()[j + 1];
/*     */           }
/*     */         } 
/* 250 */         if (getStringWidth(String.valueOf(currentWord) + word + " ") < width) {
/* 251 */           currentWord = String.valueOf(currentWord) + word + " ";
/*     */         } else {
/* 253 */           finalWords.add(currentWord);
/* 254 */           currentWord = "§" + lastColorCode + word + " ";
/*     */         }  b++; }
/*     */       
/* 257 */       if (currentWord.length() > 0) if (getStringWidth(currentWord) < width) {
/* 258 */           finalWords.add("§" + lastColorCode + currentWord + " ");
/* 259 */           currentWord = "";
/*     */         } else {
/* 261 */           for (String s : formatString(currentWord, width))
/* 262 */             finalWords.add(s); 
/*     */         }  
/*     */     } else {
/* 265 */       finalWords.add(text);
/*     */     } 
/* 267 */     return finalWords;
/*     */   }
/*     */   
/*     */   public List<String> formatString(String string, double width) {
/* 271 */     List<String> finalWords = new ArrayList<>();
/* 272 */     String currentWord = "";
/* 273 */     char lastColorCode = Character.MAX_VALUE;
/* 274 */     char[] chars = string.toCharArray();
/* 275 */     for (int i = 0; i < chars.length; i++) {
/* 276 */       char c = chars[i];
/*     */       
/* 278 */       if (c == '§' && i < chars.length - 1) {
/* 279 */         lastColorCode = chars[i + 1];
/*     */       }
/*     */       
/* 282 */       if (getStringWidth(String.valueOf(currentWord) + c) < width) {
/* 283 */         currentWord = String.valueOf(currentWord) + c;
/*     */       } else {
/* 285 */         finalWords.add(currentWord);
/* 286 */         currentWord = "§" + lastColorCode + String.valueOf(c);
/*     */       } 
/*     */     } 
/*     */     
/* 290 */     if (currentWord.length() > 0) {
/* 291 */       finalWords.add(currentWord);
/*     */     }
/*     */     
/* 294 */     return finalWords;
/*     */   }
/*     */   
/*     */   private void setupMinecraftColorcodes() {
/* 298 */     for (int index = 0; index < 32; index++) {
/* 299 */       int noClue = (index >> 3 & 0x1) * 85;
/* 300 */       int red = (index >> 2 & 0x1) * 170 + noClue;
/* 301 */       int green = (index >> 1 & 0x1) * 170 + noClue;
/* 302 */       int blue = (index >> 0 & 0x1) * 170 + noClue;
/*     */       
/* 304 */       if (index == 6) {
/* 305 */         red += 85;
/*     */       }
/*     */       
/* 308 */       if (index >= 16) {
/* 309 */         red /= 4;
/* 310 */         green /= 4;
/* 311 */         blue /= 4;
/*     */       } 
/*     */       
/* 314 */       this.colorCode[index] = (red & 0xFF) << 16 | (green & 0xFF) << 8 | blue & 0xFF;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\lea\\util\font\CustomFontRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */