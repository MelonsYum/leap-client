/*     */ package leap.util.font;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.awt.Font;
/*     */ import java.awt.FontMetrics;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.RenderingHints;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.awt.image.BufferedImage;
/*     */ import net.minecraft.client.renderer.texture.DynamicTexture;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ public class CustomFont
/*     */ {
/*  15 */   private float imgSize = 512.0F;
/*  16 */   protected CharData[] charData = new CharData[2048];
/*     */   protected Font font;
/*     */   protected boolean antiAlias;
/*     */   protected boolean fractionalMetrics;
/*  20 */   protected int fontHeight = 5;
/*  21 */   protected int charOffset = 0;
/*     */   protected DynamicTexture tex;
/*     */   
/*     */   public CustomFont(Font font, boolean antiAlias, boolean fractionalMetrics) {
/*  25 */     this.font = font;
/*  26 */     this.antiAlias = antiAlias;
/*  27 */     this.fractionalMetrics = fractionalMetrics;
/*  28 */     this.tex = setupTexture(font, antiAlias, fractionalMetrics, this.charData);
/*     */   }
/*     */   
/*     */   protected DynamicTexture setupTexture(Font font, boolean antiAlias, boolean fractionalMetrics, CharData[] chars) {
/*  32 */     BufferedImage img = generateFontImage(font, antiAlias, fractionalMetrics, chars);
/*     */     try {
/*  34 */       return new DynamicTexture(img);
/*  35 */     } catch (Exception e) {
/*  36 */       e.printStackTrace();
/*     */       
/*  38 */       return null;
/*     */     } 
/*     */   }
/*     */   protected BufferedImage generateFontImage(Font font, boolean antiAlias, boolean fractionalMetrics, CharData[] chars) {
/*  42 */     int imgSize = (int)this.imgSize;
/*  43 */     BufferedImage bufferedImage = new BufferedImage(imgSize, imgSize, 2);
/*  44 */     Graphics2D g = (Graphics2D)bufferedImage.getGraphics();
/*  45 */     g.setFont(font);
/*  46 */     g.setColor(new Color(255, 255, 255, 0));
/*  47 */     g.fillRect(0, 0, imgSize, imgSize);
/*  48 */     g.setColor(Color.WHITE);
/*  49 */     g.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, fractionalMetrics ? RenderingHints.VALUE_FRACTIONALMETRICS_ON : RenderingHints.VALUE_FRACTIONALMETRICS_OFF);
/*  50 */     g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, antiAlias ? RenderingHints.VALUE_TEXT_ANTIALIAS_ON : RenderingHints.VALUE_TEXT_ANTIALIAS_OFF);
/*  51 */     g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, antiAlias ? RenderingHints.VALUE_ANTIALIAS_ON : RenderingHints.VALUE_ANTIALIAS_OFF);
/*  52 */     FontMetrics fontMetrics = g.getFontMetrics();
/*  53 */     int charHeight = 0;
/*  54 */     int positionX = 0;
/*  55 */     int positionY = 1;
/*  56 */     for (int i = 0; i < chars.length; i++) {
/*  57 */       char ch = (char)i;
/*  58 */       CharData charData = new CharData();
/*  59 */       Rectangle2D dimensions = fontMetrics.getStringBounds(String.valueOf(ch), g);
/*  60 */       charData.width = (dimensions.getBounds()).width + 8;
/*  61 */       charData.height = (dimensions.getBounds()).height;
/*  62 */       if (positionX + charData.width >= imgSize) {
/*  63 */         positionX = 0;
/*  64 */         positionY += charHeight;
/*  65 */         charHeight = 0;
/*     */       } 
/*  67 */       if (charData.height > charHeight) {
/*  68 */         charHeight = charData.height;
/*     */       }
/*  70 */       charData.storedX = positionX;
/*  71 */       charData.storedY = positionY;
/*  72 */       if (charData.height > this.fontHeight) {
/*  73 */         this.fontHeight = charData.height;
/*     */       }
/*  75 */       chars[i] = charData;
/*  76 */       g.drawString(String.valueOf(ch), positionX + 2, positionY + fontMetrics.getAscent());
/*  77 */       positionX += charData.width;
/*     */     } 
/*  79 */     return bufferedImage;
/*     */   }
/*     */   
/*     */   public void drawChar(CharData[] chars, char c, float x, float y) throws ArrayIndexOutOfBoundsException {
/*     */     try {
/*  84 */       drawQuad(x, y, (chars[c]).width, (chars[c]).height, (chars[c]).storedX, (chars[c]).storedY, (chars[c]).width, (chars[c]).height);
/*  85 */     } catch (Exception e) {
/*  86 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void drawQuad(float x, float y, float width, float height, float srcX, float srcY, float srcWidth, float srcHeight) {
/*  91 */     float renderSRCX = srcX / this.imgSize;
/*  92 */     float renderSRCY = srcY / this.imgSize;
/*  93 */     float renderSRCWidth = srcWidth / this.imgSize;
/*  94 */     float renderSRCHeight = srcHeight / this.imgSize;
/*  95 */     GL11.glTexCoord2f(renderSRCX + renderSRCWidth, renderSRCY);
/*  96 */     GL11.glVertex2d((x + width), y);
/*  97 */     GL11.glTexCoord2f(renderSRCX, renderSRCY);
/*  98 */     GL11.glVertex2d(x, y);
/*  99 */     GL11.glTexCoord2f(renderSRCX, renderSRCY + renderSRCHeight);
/* 100 */     GL11.glVertex2d(x, (y + height));
/* 101 */     GL11.glTexCoord2f(renderSRCX, renderSRCY + renderSRCHeight);
/* 102 */     GL11.glVertex2d(x, (y + height));
/* 103 */     GL11.glTexCoord2f(renderSRCX + renderSRCWidth, renderSRCY + renderSRCHeight);
/* 104 */     GL11.glVertex2d((x + width), (y + height));
/* 105 */     GL11.glTexCoord2f(renderSRCX + renderSRCWidth, renderSRCY);
/* 106 */     GL11.glVertex2d((x + width), y);
/*     */   }
/*     */   
/*     */   public int getStringHeight(String text) {
/* 110 */     return getHeight();
/*     */   }
/*     */   
/*     */   public int getHeight() {
/* 114 */     return (this.fontHeight - 8) / 2;
/*     */   }
/*     */   
/*     */   public int getStringWidth(String text) {
/* 118 */     int width = 0; byte b; int i; char[] arrayOfChar;
/* 119 */     for (i = (arrayOfChar = text.toCharArray()).length, b = 0; b < i; ) { char c = arrayOfChar[b];
/* 120 */       if (c < this.charData.length && c >= '\000') width += (this.charData[c]).width - 8 + this.charOffset;  b++; }
/*     */     
/* 122 */     return width / 2;
/*     */   }
/*     */   
/*     */   public boolean isAntiAlias() {
/* 126 */     return this.antiAlias;
/*     */   }
/*     */   
/*     */   public void setAntiAlias(boolean antiAlias) {
/* 130 */     if (this.antiAlias != antiAlias) {
/* 131 */       this.antiAlias = antiAlias;
/* 132 */       this.tex = setupTexture(this.font, antiAlias, this.fractionalMetrics, this.charData);
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean isFractionalMetrics() {
/* 137 */     return this.fractionalMetrics;
/*     */   }
/*     */   
/*     */   public void setFractionalMetrics(boolean fractionalMetrics) {
/* 141 */     if (this.fractionalMetrics != fractionalMetrics) {
/* 142 */       this.fractionalMetrics = fractionalMetrics;
/* 143 */       this.tex = setupTexture(this.font, this.antiAlias, fractionalMetrics, this.charData);
/*     */     } 
/*     */   }
/*     */   
/*     */   public Font getFont() {
/* 148 */     return this.font;
/*     */   }
/*     */   
/*     */   public void setFont(Font font) {
/* 152 */     this.font = font;
/* 153 */     this.tex = setupTexture(font, this.antiAlias, this.fractionalMetrics, this.charData);
/*     */   }
/*     */   
/*     */   protected class CharData {
/*     */     public int width;
/*     */     public int height;
/*     */     public int storedX;
/*     */     public int storedY;
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\lea\\util\font\CustomFont.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */