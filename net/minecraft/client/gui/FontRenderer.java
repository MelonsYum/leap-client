/*      */ package net.minecraft.client.gui;
/*      */ 
/*      */ import com.ibm.icu.text.ArabicShaping;
/*      */ import com.ibm.icu.text.ArabicShapingException;
/*      */ import com.ibm.icu.text.Bidi;
/*      */ import java.awt.image.BufferedImage;
/*      */ import java.io.IOException;
/*      */ import java.io.InputStream;
/*      */ import java.util.Arrays;
/*      */ import java.util.Iterator;
/*      */ import java.util.List;
/*      */ import java.util.Properties;
/*      */ import java.util.Random;
/*      */ import net.minecraft.client.Minecraft;
/*      */ import net.minecraft.client.renderer.GlStateManager;
/*      */ import net.minecraft.client.renderer.Tessellator;
/*      */ import net.minecraft.client.renderer.WorldRenderer;
/*      */ import net.minecraft.client.renderer.texture.TextureManager;
/*      */ import net.minecraft.client.renderer.texture.TextureUtil;
/*      */ import net.minecraft.client.resources.IResourceManager;
/*      */ import net.minecraft.client.resources.IResourceManagerReloadListener;
/*      */ import net.minecraft.client.settings.GameSettings;
/*      */ import net.minecraft.util.ResourceLocation;
/*      */ import optifine.Config;
/*      */ import optifine.CustomColors;
/*      */ import optifine.FontUtils;
/*      */ import org.apache.commons.io.IOUtils;
/*      */ import org.lwjgl.opengl.GL11;
/*      */ 
/*      */ public class FontRenderer
/*      */   implements IResourceManagerReloadListener
/*      */ {
/*   33 */   private static final ResourceLocation[] unicodePageLocations = new ResourceLocation[256];
/*      */ 
/*      */   
/*   36 */   private float[] charWidth = new float[256];
/*      */ 
/*      */   
/*   39 */   public int FONT_HEIGHT = 9;
/*   40 */   public Random fontRandom = new Random();
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*   45 */   private byte[] glyphWidth = new byte[65536];
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*   51 */   private int[] colorCode = new int[32];
/*      */ 
/*      */   
/*      */   private ResourceLocation locationFontTexture;
/*      */ 
/*      */   
/*      */   private final TextureManager renderEngine;
/*      */ 
/*      */   
/*      */   private float posX;
/*      */ 
/*      */   
/*      */   private float posY;
/*      */ 
/*      */   
/*      */   private boolean unicodeFlag;
/*      */ 
/*      */   
/*      */   private boolean bidiFlag;
/*      */ 
/*      */   
/*      */   private float red;
/*      */ 
/*      */   
/*      */   private float blue;
/*      */ 
/*      */   
/*      */   private float green;
/*      */ 
/*      */   
/*      */   private float alpha;
/*      */ 
/*      */   
/*      */   private int textColor;
/*      */ 
/*      */   
/*      */   private boolean randomStyle;
/*      */ 
/*      */   
/*      */   private boolean boldStyle;
/*      */ 
/*      */   
/*      */   private boolean italicStyle;
/*      */ 
/*      */   
/*      */   private boolean underlineStyle;
/*      */ 
/*      */   
/*      */   private boolean strikethroughStyle;
/*      */ 
/*      */   
/*      */   private static final String __OBFID = "CL_00000660";
/*      */   
/*      */   public GameSettings gameSettings;
/*      */   
/*      */   public ResourceLocation locationFontTextureBase;
/*      */   
/*      */   public boolean enabled = true;
/*      */   
/*  110 */   public float offsetBold = 1.0F;
/*      */ 
/*      */   
/*      */   public FontRenderer(GameSettings p_i1035_1_, ResourceLocation p_i1035_2_, TextureManager p_i1035_3_, boolean p_i1035_4_) {
/*  114 */     this.gameSettings = p_i1035_1_;
/*  115 */     this.locationFontTextureBase = p_i1035_2_;
/*  116 */     this.locationFontTexture = p_i1035_2_;
/*  117 */     this.renderEngine = p_i1035_3_;
/*  118 */     this.unicodeFlag = p_i1035_4_;
/*  119 */     this.locationFontTexture = FontUtils.getHdFontLocation(this.locationFontTextureBase);
/*  120 */     bindTexture(this.locationFontTexture);
/*      */     
/*  122 */     for (int var5 = 0; var5 < 32; var5++) {
/*      */       
/*  124 */       int var6 = (var5 >> 3 & 0x1) * 85;
/*  125 */       int var7 = (var5 >> 2 & 0x1) * 170 + var6;
/*  126 */       int var8 = (var5 >> 1 & 0x1) * 170 + var6;
/*  127 */       int var9 = (var5 >> 0 & 0x1) * 170 + var6;
/*      */       
/*  129 */       if (var5 == 6)
/*      */       {
/*  131 */         var7 += 85;
/*      */       }
/*      */       
/*  134 */       if (p_i1035_1_.anaglyph) {
/*      */         
/*  136 */         int var10 = (var7 * 30 + var8 * 59 + var9 * 11) / 100;
/*  137 */         int var11 = (var7 * 30 + var8 * 70) / 100;
/*  138 */         int var12 = (var7 * 30 + var9 * 70) / 100;
/*  139 */         var7 = var10;
/*  140 */         var8 = var11;
/*  141 */         var9 = var12;
/*      */       } 
/*      */       
/*  144 */       if (var5 >= 16) {
/*      */         
/*  146 */         var7 /= 4;
/*  147 */         var8 /= 4;
/*  148 */         var9 /= 4;
/*      */       } 
/*      */       
/*  151 */       this.colorCode[var5] = (var7 & 0xFF) << 16 | (var8 & 0xFF) << 8 | var9 & 0xFF;
/*      */     } 
/*      */     
/*  154 */     readGlyphSizes();
/*      */   }
/*      */ 
/*      */   
/*      */   public void onResourceManagerReload(IResourceManager resourceManager) {
/*  159 */     this.locationFontTexture = FontUtils.getHdFontLocation(this.locationFontTextureBase);
/*      */     
/*  161 */     for (int i = 0; i < unicodePageLocations.length; i++)
/*      */     {
/*  163 */       unicodePageLocations[i] = null;
/*      */     }
/*      */     
/*  166 */     readFontTexture();
/*  167 */     readGlyphSizes();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void readFontTexture() {
/*      */     BufferedImage bufferedimage;
/*      */     try {
/*  176 */       bufferedimage = TextureUtil.func_177053_a(getResourceInputStream(this.locationFontTexture));
/*      */     }
/*  178 */     catch (IOException var21) {
/*      */       
/*  180 */       throw new RuntimeException(var21);
/*      */     } 
/*      */     
/*  183 */     Properties props = FontUtils.readFontProperties(this.locationFontTexture);
/*  184 */     int imgWidth = bufferedimage.getWidth();
/*  185 */     int imgHeight = bufferedimage.getHeight();
/*  186 */     int charW = imgWidth / 16;
/*  187 */     int charH = imgHeight / 16;
/*  188 */     float kx = imgWidth / 128.0F;
/*  189 */     float boldScaleFactor = Config.limit(kx, 1.0F, 2.0F);
/*  190 */     this.offsetBold = 1.0F / boldScaleFactor;
/*  191 */     float offsetBoldConfig = FontUtils.readFloat(props, "offsetBold", -1.0F);
/*      */     
/*  193 */     if (offsetBoldConfig >= 0.0F)
/*      */     {
/*  195 */       this.offsetBold = offsetBoldConfig;
/*      */     }
/*      */     
/*  198 */     int[] ai = new int[imgWidth * imgHeight];
/*  199 */     bufferedimage.getRGB(0, 0, imgWidth, imgHeight, ai, 0, imgWidth);
/*  200 */     int k = 0;
/*      */     
/*  202 */     while (k < 256) {
/*      */       
/*  204 */       int cx = k % 16;
/*  205 */       int cy = k / 16;
/*  206 */       boolean px = false;
/*  207 */       int var22 = charW - 1;
/*      */ 
/*      */ 
/*      */       
/*  211 */       while (var22 >= 0) {
/*      */         
/*  213 */         int x = cx * charW + var22;
/*  214 */         boolean flag = true;
/*      */         
/*  216 */         for (int py = 0; py < charH && flag; py++) {
/*      */           
/*  218 */           int ypos = (cy * charH + py) * imgWidth;
/*  219 */           int col = ai[x + ypos];
/*  220 */           int al = col >> 24 & 0xFF;
/*      */           
/*  222 */           if (al > 16)
/*      */           {
/*  224 */             flag = false;
/*      */           }
/*      */         } 
/*      */         
/*  228 */         if (flag)
/*      */         {
/*  230 */           var22--;
/*      */         }
/*      */       } 
/*      */ 
/*      */       
/*  235 */       if (k == 65)
/*      */       {
/*  237 */         k = k;
/*      */       }
/*      */       
/*  240 */       if (k == 32)
/*      */       {
/*  242 */         if (charW <= 8) {
/*      */           
/*  244 */           var22 = (int)(2.0F * kx);
/*      */         }
/*      */         else {
/*      */           
/*  248 */           var22 = (int)(1.5F * kx);
/*      */         } 
/*      */       }
/*      */       
/*  252 */       this.charWidth[k] = (var22 + 1) / kx + 1.0F;
/*  253 */       k++;
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*  258 */     FontUtils.readCustomCharWidths(props, this.charWidth);
/*      */   }
/*      */ 
/*      */   
/*      */   private void readGlyphSizes() {
/*  263 */     InputStream var1 = null;
/*      */ 
/*      */     
/*      */     try {
/*  267 */       var1 = getResourceInputStream(new ResourceLocation("font/glyph_sizes.bin"));
/*  268 */       var1.read(this.glyphWidth);
/*      */     }
/*  270 */     catch (IOException var6) {
/*      */       
/*  272 */       throw new RuntimeException(var6);
/*      */     }
/*      */     finally {
/*      */       
/*  276 */       IOUtils.closeQuietly(var1);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private float renderCharAtPos(int p_78278_1_, char p_78278_2_, boolean p_78278_3_) {
/*  285 */     return (p_78278_2_ == ' ') ? (!this.unicodeFlag ? this.charWidth[p_78278_2_] : 4.0F) : ((p_78278_2_ == ' ') ? 4.0F : (("ÀÁÂÈÊËÍÓÔÕÚßãõğİıŒœŞşŴŵžȇ\000\000\000\000\000\000\000 !\"#$%&'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\]^_`abcdefghijklmnopqrstuvwxyz{|}~\000ÇüéâäàåçêëèïîìÄÅÉæÆôöòûùÿÖÜø£Ø×ƒáíóúñÑªº¿®¬½¼¡«»░▒▓│┤╡╢╖╕╣║╗╝╜╛┐└┴┬├─┼╞╟╚╔╩╦╠═╬╧╨╤╥╙╘╒╓╫╪┘┌█▄▌▐▀αβΓπΣσμτΦΘΩδ∞∅∈∩≡±≥≤⌠⌡÷≈°∙·√ⁿ²■\000".indexOf(p_78278_2_) != -1 && !this.unicodeFlag) ? renderDefaultChar(p_78278_1_, p_78278_3_) : renderUnicodeChar(p_78278_2_, p_78278_3_)));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private float renderDefaultChar(int p_78266_1_, boolean p_78266_2_) {
/*  293 */     float var3 = (p_78266_1_ % 16 * 8);
/*  294 */     float var4 = (p_78266_1_ / 16 * 8);
/*  295 */     float var5 = p_78266_2_ ? 1.0F : 0.0F;
/*  296 */     bindTexture(this.locationFontTexture);
/*  297 */     float var6 = 7.99F;
/*  298 */     GL11.glBegin(5);
/*  299 */     GL11.glTexCoord2f(var3 / 128.0F, var4 / 128.0F);
/*  300 */     GL11.glVertex3f(this.posX + var5, this.posY, 0.0F);
/*  301 */     GL11.glTexCoord2f(var3 / 128.0F, (var4 + 7.99F) / 128.0F);
/*  302 */     GL11.glVertex3f(this.posX - var5, this.posY + 7.99F, 0.0F);
/*  303 */     GL11.glTexCoord2f((var3 + var6 - 1.0F) / 128.0F, var4 / 128.0F);
/*  304 */     GL11.glVertex3f(this.posX + var6 - 1.0F + var5, this.posY, 0.0F);
/*  305 */     GL11.glTexCoord2f((var3 + var6 - 1.0F) / 128.0F, (var4 + 7.99F) / 128.0F);
/*  306 */     GL11.glVertex3f(this.posX + var6 - 1.0F - var5, this.posY + 7.99F, 0.0F);
/*  307 */     GL11.glEnd();
/*  308 */     return this.charWidth[p_78266_1_];
/*      */   }
/*      */ 
/*      */   
/*      */   private ResourceLocation getUnicodePageLocation(int p_111271_1_) {
/*  313 */     if (unicodePageLocations[p_111271_1_] == null) {
/*      */       
/*  315 */       unicodePageLocations[p_111271_1_] = new ResourceLocation(String.format("textures/font/unicode_page_%02x.png", new Object[] { Integer.valueOf(p_111271_1_) }));
/*  316 */       unicodePageLocations[p_111271_1_] = FontUtils.getHdFontLocation(unicodePageLocations[p_111271_1_]);
/*      */     } 
/*      */     
/*  319 */     return unicodePageLocations[p_111271_1_];
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void loadGlyphTexture(int p_78257_1_) {
/*  327 */     bindTexture(getUnicodePageLocation(p_78257_1_));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private float renderUnicodeChar(char p_78277_1_, boolean p_78277_2_) {
/*  335 */     if (this.glyphWidth[p_78277_1_] == 0)
/*      */     {
/*  337 */       return 0.0F;
/*      */     }
/*      */ 
/*      */     
/*  341 */     int var3 = p_78277_1_ / 256;
/*  342 */     loadGlyphTexture(var3);
/*  343 */     int var4 = this.glyphWidth[p_78277_1_] >>> 4;
/*  344 */     int var5 = this.glyphWidth[p_78277_1_] & 0xF;
/*  345 */     var4 &= 0xF;
/*  346 */     float var6 = var4;
/*  347 */     float var7 = (var5 + 1);
/*  348 */     float var8 = (p_78277_1_ % 16 * 16) + var6;
/*  349 */     float var9 = ((p_78277_1_ & 0xFF) / 16 * 16);
/*  350 */     float var10 = var7 - var6 - 0.02F;
/*  351 */     float var11 = p_78277_2_ ? 1.0F : 0.0F;
/*  352 */     GL11.glBegin(5);
/*  353 */     GL11.glTexCoord2f(var8 / 256.0F, var9 / 256.0F);
/*  354 */     GL11.glVertex3f(this.posX + var11, this.posY, 0.0F);
/*  355 */     GL11.glTexCoord2f(var8 / 256.0F, (var9 + 15.98F) / 256.0F);
/*  356 */     GL11.glVertex3f(this.posX - var11, this.posY + 7.99F, 0.0F);
/*  357 */     GL11.glTexCoord2f((var8 + var10) / 256.0F, var9 / 256.0F);
/*  358 */     GL11.glVertex3f(this.posX + var10 / 2.0F + var11, this.posY, 0.0F);
/*  359 */     GL11.glTexCoord2f((var8 + var10) / 256.0F, (var9 + 15.98F) / 256.0F);
/*  360 */     GL11.glVertex3f(this.posX + var10 / 2.0F - var11, this.posY + 7.99F, 0.0F);
/*  361 */     GL11.glEnd();
/*  362 */     return (var7 - var6) / 2.0F + 1.0F;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public int drawStringWithShadow(String p_175063_1_, double p_175063_2_, double p_175063_3_, int p_175063_4_) {
/*  368 */     return func_175065_a(p_175063_1_, (float)p_175063_2_, (float)p_175063_3_, p_175063_4_, true);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int drawString(String text, double x, double d, int color) {
/*  376 */     return !this.enabled ? 0 : func_175065_a(text, (float)x, (float)d, color, false);
/*      */   }
/*      */   
/*      */   public int func_175065_a(String p_175065_1_, float p_175065_2_, float p_175065_3_, int p_175065_4_, boolean p_175065_5_) {
/*      */     int var6;
/*  381 */     enableAlpha();
/*  382 */     resetStyles();
/*      */ 
/*      */     
/*  385 */     if (p_175065_5_) {
/*      */       
/*  387 */       var6 = func_180455_b(p_175065_1_, p_175065_2_ + 1.0F, p_175065_3_ + 1.0F, p_175065_4_, true);
/*  388 */       var6 = Math.max(var6, func_180455_b(p_175065_1_, p_175065_2_, p_175065_3_, p_175065_4_, false));
/*      */     }
/*      */     else {
/*      */       
/*  392 */       var6 = func_180455_b(p_175065_1_, p_175065_2_, p_175065_3_, p_175065_4_, false);
/*      */     } 
/*      */     
/*  395 */     return var6;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private String bidiReorder(String p_147647_1_) {
/*      */     try {
/*  405 */       Bidi var3 = new Bidi((new ArabicShaping(8)).shape(p_147647_1_), 127);
/*  406 */       var3.setReorderingMode(0);
/*  407 */       return var3.writeReordered(2);
/*      */     }
/*  409 */     catch (ArabicShapingException var31) {
/*      */       
/*  411 */       return p_147647_1_;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void resetStyles() {
/*  420 */     this.randomStyle = false;
/*  421 */     this.boldStyle = false;
/*  422 */     this.italicStyle = false;
/*  423 */     this.underlineStyle = false;
/*  424 */     this.strikethroughStyle = false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void renderStringAtPos(String p_78255_1_, boolean p_78255_2_) {
/*  432 */     for (int var3 = 0; var3 < p_78255_1_.length(); var3++) {
/*      */       
/*  434 */       char var4 = p_78255_1_.charAt(var3);
/*      */ 
/*      */ 
/*      */       
/*  438 */       if (var4 == '§' && var3 + 1 < p_78255_1_.length()) {
/*      */         
/*  440 */         int var5 = "0123456789abcdefklmnor".indexOf(p_78255_1_.toLowerCase().charAt(var3 + 1));
/*      */         
/*  442 */         if (var5 < 16) {
/*      */           
/*  444 */           this.randomStyle = false;
/*  445 */           this.boldStyle = false;
/*  446 */           this.strikethroughStyle = false;
/*  447 */           this.underlineStyle = false;
/*  448 */           this.italicStyle = false;
/*      */           
/*  450 */           if (var5 < 0 || var5 > 15)
/*      */           {
/*  452 */             var5 = 15;
/*      */           }
/*      */           
/*  455 */           if (p_78255_2_)
/*      */           {
/*  457 */             var5 += 16;
/*      */           }
/*      */           
/*  460 */           int var6 = this.colorCode[var5];
/*      */           
/*  462 */           if (Config.isCustomColors())
/*      */           {
/*  464 */             var6 = CustomColors.getTextColor(var5, var6);
/*      */           }
/*      */           
/*  467 */           this.textColor = var6;
/*  468 */           setColor((var6 >> 16) / 255.0F, (var6 >> 8 & 0xFF) / 255.0F, (var6 & 0xFF) / 255.0F, this.alpha);
/*      */         }
/*  470 */         else if (var5 == 16) {
/*      */           
/*  472 */           this.randomStyle = true;
/*      */         }
/*  474 */         else if (var5 == 17) {
/*      */           
/*  476 */           this.boldStyle = true;
/*      */         }
/*  478 */         else if (var5 == 18) {
/*      */           
/*  480 */           this.strikethroughStyle = true;
/*      */         }
/*  482 */         else if (var5 == 19) {
/*      */           
/*  484 */           this.underlineStyle = true;
/*      */         }
/*  486 */         else if (var5 == 20) {
/*      */           
/*  488 */           this.italicStyle = true;
/*      */         }
/*  490 */         else if (var5 == 21) {
/*      */           
/*  492 */           this.randomStyle = false;
/*  493 */           this.boldStyle = false;
/*  494 */           this.strikethroughStyle = false;
/*  495 */           this.underlineStyle = false;
/*  496 */           this.italicStyle = false;
/*  497 */           setColor(this.red, this.blue, this.green, this.alpha);
/*      */         } 
/*      */         
/*  500 */         var3++;
/*      */       }
/*      */       else {
/*      */         
/*  504 */         int var5 = "ÀÁÂÈÊËÍÓÔÕÚßãõğİıŒœŞşŴŵžȇ\000\000\000\000\000\000\000 !\"#$%&'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\]^_`abcdefghijklmnopqrstuvwxyz{|}~\000ÇüéâäàåçêëèïîìÄÅÉæÆôöòûùÿÖÜø£Ø×ƒáíóúñÑªº¿®¬½¼¡«»░▒▓│┤╡╢╖╕╣║╗╝╜╛┐└┴┬├─┼╞╟╚╔╩╦╠═╬╧╨╤╥╙╘╒╓╫╪┘┌█▄▌▐▀αβΓπΣσμτΦΘΩδ∞∅∈∩≡±≥≤⌠⌡÷≈°∙·√ⁿ²■\000".indexOf(var4);
/*      */         
/*  506 */         if (this.randomStyle && var5 != -1) {
/*      */           int var6;
/*      */           
/*      */           do {
/*  510 */             var6 = this.fontRandom.nextInt(this.charWidth.length);
/*      */           }
/*  512 */           while ((int)this.charWidth[var5] != (int)this.charWidth[var6]);
/*      */           
/*  514 */           var5 = var6;
/*      */         } 
/*      */         
/*  517 */         float var12 = (var5 != -1 && !this.unicodeFlag) ? this.offsetBold : 0.5F;
/*  518 */         boolean var7 = ((var4 == '\000' || var5 == -1 || this.unicodeFlag) && p_78255_2_);
/*      */         
/*  520 */         if (var7) {
/*      */           
/*  522 */           this.posX -= var12;
/*  523 */           this.posY -= var12;
/*      */         } 
/*      */         
/*  526 */         float var8 = renderCharAtPos(var5, var4, this.italicStyle);
/*      */         
/*  528 */         if (var7) {
/*      */           
/*  530 */           this.posX += var12;
/*  531 */           this.posY += var12;
/*      */         } 
/*      */         
/*  534 */         if (this.boldStyle) {
/*      */           
/*  536 */           this.posX += var12;
/*      */           
/*  538 */           if (var7) {
/*      */             
/*  540 */             this.posX -= var12;
/*  541 */             this.posY -= var12;
/*      */           } 
/*      */           
/*  544 */           renderCharAtPos(var5, var4, this.italicStyle);
/*  545 */           this.posX -= var12;
/*      */           
/*  547 */           if (var7) {
/*      */             
/*  549 */             this.posX += var12;
/*  550 */             this.posY += var12;
/*      */           } 
/*      */           
/*  553 */           var8 += var12;
/*      */         } 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  559 */         if (this.strikethroughStyle) {
/*      */           
/*  561 */           Tessellator var9 = Tessellator.getInstance();
/*  562 */           WorldRenderer var10 = var9.getWorldRenderer();
/*  563 */           GlStateManager.func_179090_x();
/*  564 */           var10.startDrawingQuads();
/*  565 */           var10.addVertex(this.posX, (this.posY + (this.FONT_HEIGHT / 2)), 0.0D);
/*  566 */           var10.addVertex((this.posX + var8), (this.posY + (this.FONT_HEIGHT / 2)), 0.0D);
/*  567 */           var10.addVertex((this.posX + var8), (this.posY + (this.FONT_HEIGHT / 2) - 1.0F), 0.0D);
/*  568 */           var10.addVertex(this.posX, (this.posY + (this.FONT_HEIGHT / 2) - 1.0F), 0.0D);
/*  569 */           var9.draw();
/*  570 */           GlStateManager.func_179098_w();
/*      */         } 
/*      */         
/*  573 */         if (this.underlineStyle) {
/*      */           
/*  575 */           Tessellator var9 = Tessellator.getInstance();
/*  576 */           WorldRenderer var10 = var9.getWorldRenderer();
/*  577 */           GlStateManager.func_179090_x();
/*  578 */           var10.startDrawingQuads();
/*  579 */           int var11 = this.underlineStyle ? -1 : 0;
/*  580 */           var10.addVertex((this.posX + var11), (this.posY + this.FONT_HEIGHT), 0.0D);
/*  581 */           var10.addVertex((this.posX + var8), (this.posY + this.FONT_HEIGHT), 0.0D);
/*  582 */           var10.addVertex((this.posX + var8), (this.posY + this.FONT_HEIGHT - 1.0F), 0.0D);
/*  583 */           var10.addVertex((this.posX + var11), (this.posY + this.FONT_HEIGHT - 1.0F), 0.0D);
/*  584 */           var9.draw();
/*  585 */           GlStateManager.func_179098_w();
/*      */         } 
/*      */         
/*  588 */         this.posX += var8;
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private int renderStringAligned(String p_78274_1_, int p_78274_2_, int p_78274_3_, int p_78274_4_, int p_78274_5_, boolean p_78274_6_) {
/*  598 */     if (this.bidiFlag) {
/*      */       
/*  600 */       int var7 = getStringWidth(bidiReorder(p_78274_1_));
/*  601 */       p_78274_2_ = p_78274_2_ + p_78274_4_ - var7;
/*      */     } 
/*      */     
/*  604 */     return func_180455_b(p_78274_1_, p_78274_2_, p_78274_3_, p_78274_5_, p_78274_6_);
/*      */   }
/*      */ 
/*      */   
/*      */   private int func_180455_b(String p_180455_1_, float p_180455_2_, float p_180455_3_, int p_180455_4_, boolean p_180455_5_) {
/*  609 */     if (p_180455_1_ == null)
/*      */     {
/*  611 */       return 0;
/*      */     }
/*      */ 
/*      */     
/*  615 */     if (this.bidiFlag)
/*      */     {
/*  617 */       p_180455_1_ = bidiReorder(p_180455_1_);
/*      */     }
/*      */     
/*  620 */     if ((p_180455_4_ & 0xFC000000) == 0)
/*      */     {
/*  622 */       p_180455_4_ |= 0xFF000000;
/*      */     }
/*      */     
/*  625 */     if (p_180455_5_)
/*      */     {
/*  627 */       p_180455_4_ = (p_180455_4_ & 0xFCFCFC) >> 2 | p_180455_4_ & 0xFF000000;
/*      */     }
/*      */     
/*  630 */     this.red = (p_180455_4_ >> 16 & 0xFF) / 255.0F;
/*  631 */     this.blue = (p_180455_4_ >> 8 & 0xFF) / 255.0F;
/*  632 */     this.green = (p_180455_4_ & 0xFF) / 255.0F;
/*  633 */     this.alpha = (p_180455_4_ >> 24 & 0xFF) / 255.0F;
/*  634 */     setColor(this.red, this.blue, this.green, this.alpha);
/*  635 */     this.posX = p_180455_2_;
/*  636 */     this.posY = p_180455_3_;
/*  637 */     renderStringAtPos(p_180455_1_, p_180455_5_);
/*  638 */     return (int)this.posX;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getStringWidth(String p_78256_1_) {
/*  647 */     if (p_78256_1_ == null)
/*      */     {
/*  649 */       return 0;
/*      */     }
/*      */ 
/*      */     
/*  653 */     float var2 = 0.0F;
/*  654 */     boolean var3 = false;
/*      */     
/*  656 */     for (int var4 = 0; var4 < p_78256_1_.length(); var4++) {
/*      */       
/*  658 */       char var5 = p_78256_1_.charAt(var4);
/*  659 */       float var6 = getCharWidthFloat(var5);
/*      */       
/*  661 */       if (var6 < 0.0F && var4 < p_78256_1_.length() - 1) {
/*      */         
/*  663 */         var4++;
/*  664 */         var5 = p_78256_1_.charAt(var4);
/*      */         
/*  666 */         if (var5 != 'l' && var5 != 'L') {
/*      */           
/*  668 */           if (var5 == 'r' || var5 == 'R')
/*      */           {
/*  670 */             var3 = false;
/*      */           }
/*      */         }
/*      */         else {
/*      */           
/*  675 */           var3 = true;
/*      */         } 
/*      */         
/*  678 */         var6 = 0.0F;
/*      */       } 
/*      */       
/*  681 */       var2 += var6;
/*      */       
/*  683 */       if (var3 && var6 > 0.0F)
/*      */       {
/*  685 */         var2 += this.unicodeFlag ? 1.0F : this.offsetBold;
/*      */       }
/*      */     } 
/*      */     
/*  689 */     return (int)var2;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getCharWidth(char par1) {
/*  698 */     return Math.round(getCharWidthFloat(par1));
/*      */   }
/*      */ 
/*      */   
/*      */   private float getCharWidthFloat(char p_78263_1_) {
/*  703 */     if (p_78263_1_ == '§')
/*      */     {
/*  705 */       return -1.0F;
/*      */     }
/*  707 */     if (p_78263_1_ == ' ')
/*      */     {
/*  709 */       return this.charWidth[32];
/*      */     }
/*      */ 
/*      */     
/*  713 */     int var2 = "ÀÁÂÈÊËÍÓÔÕÚßãõğİıŒœŞşŴŵžȇ\000\000\000\000\000\000\000 !\"#$%&'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\]^_`abcdefghijklmnopqrstuvwxyz{|}~\000ÇüéâäàåçêëèïîìÄÅÉæÆôöòûùÿÖÜø£Ø×ƒáíóúñÑªº¿®¬½¼¡«»░▒▓│┤╡╢╖╕╣║╗╝╜╛┐└┴┬├─┼╞╟╚╔╩╦╠═╬╧╨╤╥╙╘╒╓╫╪┘┌█▄▌▐▀αβΓπΣσμτΦΘΩδ∞∅∈∩≡±≥≤⌠⌡÷≈°∙·√ⁿ²■\000".indexOf(p_78263_1_);
/*      */     
/*  715 */     if (p_78263_1_ > '\000' && var2 != -1 && !this.unicodeFlag)
/*      */     {
/*  717 */       return this.charWidth[var2];
/*      */     }
/*  719 */     if (this.glyphWidth[p_78263_1_] != 0) {
/*      */       
/*  721 */       int var3 = this.glyphWidth[p_78263_1_] >>> 4;
/*  722 */       int var4 = this.glyphWidth[p_78263_1_] & 0xF;
/*  723 */       var3 &= 0xF;
/*  724 */       var4++;
/*  725 */       return ((var4 - var3) / 2 + 1);
/*      */     } 
/*      */ 
/*      */     
/*  729 */     return 0.0F;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String trimStringToWidth(String p_78269_1_, int p_78269_2_) {
/*  739 */     return trimStringToWidth(p_78269_1_, p_78269_2_, false);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String trimStringToWidth(String p_78262_1_, int p_78262_2_, boolean p_78262_3_) {
/*  747 */     StringBuilder var4 = new StringBuilder();
/*  748 */     float var5 = 0.0F;
/*  749 */     int var6 = p_78262_3_ ? (p_78262_1_.length() - 1) : 0;
/*  750 */     int var7 = p_78262_3_ ? -1 : 1;
/*  751 */     boolean var8 = false;
/*  752 */     boolean var9 = false;
/*      */     
/*  754 */     for (int var10 = var6; var10 >= 0 && var10 < p_78262_1_.length() && var5 < p_78262_2_; var10 += var7) {
/*      */       
/*  756 */       char var11 = p_78262_1_.charAt(var10);
/*  757 */       float var12 = getCharWidthFloat(var11);
/*      */       
/*  759 */       if (var8) {
/*      */         
/*  761 */         var8 = false;
/*      */         
/*  763 */         if (var11 != 'l' && var11 != 'L')
/*      */         {
/*  765 */           if (var11 == 'r' || var11 == 'R')
/*      */           {
/*  767 */             var9 = false;
/*      */           }
/*      */         }
/*      */         else
/*      */         {
/*  772 */           var9 = true;
/*      */         }
/*      */       
/*  775 */       } else if (var12 < 0.0F) {
/*      */         
/*  777 */         var8 = true;
/*      */       }
/*      */       else {
/*      */         
/*  781 */         var5 += var12;
/*      */         
/*  783 */         if (var9)
/*      */         {
/*  785 */           var5++;
/*      */         }
/*      */       } 
/*      */       
/*  789 */       if (var5 > p_78262_2_) {
/*      */         break;
/*      */       }
/*      */ 
/*      */       
/*  794 */       if (p_78262_3_) {
/*      */         
/*  796 */         var4.insert(0, var11);
/*      */       }
/*      */       else {
/*      */         
/*  800 */         var4.append(var11);
/*      */       } 
/*      */     } 
/*      */     
/*  804 */     return var4.toString();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private String trimStringNewline(String p_78273_1_) {
/*  812 */     while (p_78273_1_ != null && p_78273_1_.endsWith("\n"))
/*      */     {
/*  814 */       p_78273_1_ = p_78273_1_.substring(0, p_78273_1_.length() - 1);
/*      */     }
/*      */     
/*  817 */     return p_78273_1_;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void drawSplitString(String str, int x, int y, int wrapWidth, int textColor) {
/*  825 */     resetStyles();
/*  826 */     this.textColor = textColor;
/*  827 */     str = trimStringNewline(str);
/*  828 */     renderSplitString(str, x, y, wrapWidth, false);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void renderSplitString(String str, int x, int y, int wrapWidth, boolean addShadow) {
/*  837 */     List var6 = listFormattedStringToWidth(str, wrapWidth);
/*      */     
/*  839 */     for (Iterator<String> var7 = var6.iterator(); var7.hasNext(); y += this.FONT_HEIGHT) {
/*      */       
/*  841 */       String var8 = var7.next();
/*  842 */       renderStringAligned(var8, x, y, wrapWidth, this.textColor, addShadow);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int splitStringWidth(String p_78267_1_, int p_78267_2_) {
/*  851 */     return this.FONT_HEIGHT * listFormattedStringToWidth(p_78267_1_, p_78267_2_).size();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setUnicodeFlag(boolean p_78264_1_) {
/*  860 */     this.unicodeFlag = p_78264_1_;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean getUnicodeFlag() {
/*  869 */     return this.unicodeFlag;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setBidiFlag(boolean p_78275_1_) {
/*  877 */     this.bidiFlag = p_78275_1_;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public List listFormattedStringToWidth(String str, int wrapWidth) {
/*  885 */     return Arrays.asList(wrapFormattedStringToWidth(str, wrapWidth).split("\n"));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   String wrapFormattedStringToWidth(String str, int wrapWidth) {
/*  893 */     int var3 = sizeStringToWidth(str, wrapWidth);
/*      */     
/*  895 */     if (str.length() <= var3)
/*      */     {
/*  897 */       return str;
/*      */     }
/*      */ 
/*      */     
/*  901 */     String var4 = str.substring(0, var3);
/*  902 */     char var5 = str.charAt(var3);
/*  903 */     boolean var6 = !(var5 != ' ' && var5 != '\n');
/*  904 */     String var7 = String.valueOf(getFormatFromString(var4)) + str.substring(var3 + (var6 ? 1 : 0));
/*  905 */     return String.valueOf(var4) + "\n" + wrapFormattedStringToWidth(var7, wrapWidth);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private int sizeStringToWidth(String str, int wrapWidth) {
/*  914 */     int var3 = str.length();
/*  915 */     float var4 = 0.0F;
/*  916 */     int var5 = 0;
/*  917 */     int var6 = -1;
/*      */     
/*  919 */     for (boolean var7 = false; var5 < var3; var5++) {
/*      */       
/*  921 */       char var8 = str.charAt(var5);
/*      */       
/*  923 */       switch (var8) {
/*      */         
/*      */         case '\n':
/*  926 */           var5--;
/*      */           break;
/*      */         
/*      */         case '§':
/*  930 */           if (var5 < var3 - 1) {
/*      */             
/*  932 */             var5++;
/*  933 */             char var9 = str.charAt(var5);
/*      */             
/*  935 */             if (var9 != 'l' && var9 != 'L') {
/*      */               
/*  937 */               if (var9 == 'r' || var9 == 'R' || isFormatColor(var9))
/*      */               {
/*  939 */                 var7 = false;
/*      */               }
/*      */               
/*      */               break;
/*      */             } 
/*  944 */             var7 = true;
/*      */           } 
/*      */           break;
/*      */ 
/*      */ 
/*      */         
/*      */         case ' ':
/*  951 */           var6 = var5;
/*      */         
/*      */         default:
/*  954 */           var4 += getCharWidthFloat(var8);
/*      */           
/*  956 */           if (var7)
/*      */           {
/*  958 */             var4++;
/*      */           }
/*      */           break;
/*      */       } 
/*  962 */       if (var8 == '\n') {
/*      */ 
/*      */         
/*  965 */         var6 = ++var5;
/*      */         
/*      */         break;
/*      */       } 
/*  969 */       if (var4 > wrapWidth) {
/*      */         break;
/*      */       }
/*      */     } 
/*      */ 
/*      */     
/*  975 */     return (var5 != var3 && var6 != -1 && var6 < var5) ? var6 : var5;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static boolean isFormatColor(char colorChar) {
/*  983 */     return !((colorChar < '0' || colorChar > '9') && (colorChar < 'a' || colorChar > 'f') && (colorChar < 'A' || colorChar > 'F'));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static boolean isFormatSpecial(char formatChar) {
/*  991 */     return !((formatChar < 'k' || formatChar > 'o') && (formatChar < 'K' || formatChar > 'O') && formatChar != 'r' && formatChar != 'R');
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String getFormatFromString(String p_78282_0_) {
/*  999 */     String var1 = "";
/* 1000 */     int var2 = -1;
/* 1001 */     int var3 = p_78282_0_.length();
/*      */     
/* 1003 */     while ((var2 = p_78282_0_.indexOf('§', var2 + 1)) != -1) {
/*      */       
/* 1005 */       if (var2 < var3 - 1) {
/*      */         
/* 1007 */         char var4 = p_78282_0_.charAt(var2 + 1);
/*      */         
/* 1009 */         if (isFormatColor(var4)) {
/*      */           
/* 1011 */           var1 = "§" + var4; continue;
/*      */         } 
/* 1013 */         if (isFormatSpecial(var4))
/*      */         {
/* 1015 */           var1 = String.valueOf(var1) + "§" + var4;
/*      */         }
/*      */       } 
/*      */     } 
/*      */     
/* 1020 */     return var1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean getBidiFlag() {
/* 1028 */     return this.bidiFlag;
/*      */   }
/*      */ 
/*      */   
/*      */   public int func_175064_b(char p_175064_1_) {
/* 1033 */     int index = "0123456789abcdef".indexOf(p_175064_1_);
/*      */     
/* 1035 */     if (index >= 0 && index < this.colorCode.length) {
/*      */       
/* 1037 */       int color = this.colorCode[index];
/*      */       
/* 1039 */       if (Config.isCustomColors())
/*      */       {
/* 1041 */         color = CustomColors.getTextColor(index, color);
/*      */       }
/*      */       
/* 1044 */       return color;
/*      */     } 
/*      */ 
/*      */     
/* 1048 */     return 16777215;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected void setColor(float r, float g, float b, float a) {
/* 1054 */     GlStateManager.color(r, g, b, a);
/*      */   }
/*      */ 
/*      */   
/*      */   protected void enableAlpha() {
/* 1059 */     GlStateManager.enableAlpha();
/*      */   }
/*      */ 
/*      */   
/*      */   protected void bindTexture(ResourceLocation location) {
/* 1064 */     this.renderEngine.bindTexture(location);
/*      */   }
/*      */ 
/*      */   
/*      */   protected InputStream getResourceInputStream(ResourceLocation location) throws IOException {
/* 1069 */     return Minecraft.getMinecraft().getResourceManager().getResource(location).getInputStream();
/*      */   }
/*      */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\gui\FontRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */