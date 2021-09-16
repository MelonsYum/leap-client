/*     */ package optifine;
/*     */ 
/*     */ import java.io.FileNotFoundException;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.util.Iterator;
/*     */ import java.util.Properties;
/*     */ import java.util.Set;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ 
/*     */ 
/*     */ public class FontUtils
/*     */ {
/*     */   public static Properties readFontProperties(ResourceLocation locationFontTexture) {
/*  15 */     String fontFileName = locationFontTexture.getResourcePath();
/*  16 */     Properties props = new Properties();
/*  17 */     String suffix = ".png";
/*     */     
/*  19 */     if (!fontFileName.endsWith(suffix))
/*     */     {
/*  21 */       return props;
/*     */     }
/*     */ 
/*     */     
/*  25 */     String fileName = String.valueOf(fontFileName.substring(0, fontFileName.length() - suffix.length())) + ".properties";
/*     */ 
/*     */     
/*     */     try {
/*  29 */       ResourceLocation e = new ResourceLocation(locationFontTexture.getResourceDomain(), fileName);
/*  30 */       InputStream in = Config.getResourceStream(Config.getResourceManager(), e);
/*     */       
/*  32 */       if (in == null)
/*     */       {
/*  34 */         return props;
/*     */       }
/*     */       
/*  37 */       Config.log("Loading " + fileName);
/*  38 */       props.load(in);
/*     */     }
/*  40 */     catch (FileNotFoundException fileNotFoundException) {
/*     */ 
/*     */     
/*     */     }
/*  44 */     catch (IOException var8) {
/*     */       
/*  46 */       var8.printStackTrace();
/*     */     } 
/*     */     
/*  49 */     return props;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static void readCustomCharWidths(Properties props, float[] charWidth) {
/*  55 */     Set keySet = props.keySet();
/*  56 */     Iterator<String> iter = keySet.iterator();
/*     */     
/*  58 */     while (iter.hasNext()) {
/*     */       
/*  60 */       String key = iter.next();
/*  61 */       String prefix = "width.";
/*     */       
/*  63 */       if (key.startsWith(prefix)) {
/*     */         
/*  65 */         String numStr = key.substring(prefix.length());
/*  66 */         int num = Config.parseInt(numStr, -1);
/*     */         
/*  68 */         if (num >= 0 && num < charWidth.length) {
/*     */           
/*  70 */           String value = props.getProperty(key);
/*  71 */           float width = Config.parseFloat(value, -1.0F);
/*     */           
/*  73 */           if (width >= 0.0F)
/*     */           {
/*  75 */             charWidth[num] = width;
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static float readFloat(Properties props, String key, float defOffset) {
/*  84 */     String str = props.getProperty(key);
/*     */     
/*  86 */     if (str == null)
/*     */     {
/*  88 */       return defOffset;
/*     */     }
/*     */ 
/*     */     
/*  92 */     float offset = Config.parseFloat(str, Float.MIN_VALUE);
/*     */     
/*  94 */     if (offset == Float.MIN_VALUE) {
/*     */       
/*  96 */       Config.warn("Invalid value for " + key + ": " + str);
/*  97 */       return defOffset;
/*     */     } 
/*     */ 
/*     */     
/* 101 */     return offset;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static ResourceLocation getHdFontLocation(ResourceLocation fontLoc) {
/* 108 */     if (!Config.isCustomFonts())
/*     */     {
/* 110 */       return fontLoc;
/*     */     }
/* 112 */     if (fontLoc == null)
/*     */     {
/* 114 */       return fontLoc;
/*     */     }
/*     */ 
/*     */     
/* 118 */     String fontName = fontLoc.getResourcePath();
/* 119 */     String texturesStr = "textures/";
/* 120 */     String mcpatcherStr = "mcpatcher/";
/*     */     
/* 122 */     if (!fontName.startsWith(texturesStr))
/*     */     {
/* 124 */       return fontLoc;
/*     */     }
/*     */ 
/*     */     
/* 128 */     fontName = fontName.substring(texturesStr.length());
/* 129 */     fontName = String.valueOf(mcpatcherStr) + fontName;
/* 130 */     ResourceLocation fontLocHD = new ResourceLocation(fontLoc.getResourceDomain(), fontName);
/* 131 */     return Config.hasResource(Config.getResourceManager(), fontLocHD) ? fontLocHD : fontLoc;
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\optifine\FontUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */