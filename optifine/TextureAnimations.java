/*     */ package optifine;
/*     */ 
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.RenderingHints;
/*     */ import java.awt.image.BufferedImage;
/*     */ import java.io.FileNotFoundException;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.Properties;
/*     */ import javax.imageio.ImageIO;
/*     */ import net.minecraft.client.resources.IResourcePack;
/*     */ import net.minecraft.client.settings.GameSettings;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ 
/*     */ 
/*     */ public class TextureAnimations
/*     */ {
/*  20 */   private static TextureAnimation[] textureAnimations = null;
/*     */ 
/*     */   
/*     */   public static void reset() {
/*  24 */     textureAnimations = null;
/*     */   }
/*     */ 
/*     */   
/*     */   public static void update() {
/*  29 */     textureAnimations = null;
/*  30 */     IResourcePack[] rps = Config.getResourcePacks();
/*  31 */     textureAnimations = getTextureAnimations(rps);
/*     */     
/*  33 */     if (Config.isAnimatedTextures())
/*     */     {
/*  35 */       updateAnimations();
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public static void updateCustomAnimations() {
/*  41 */     if (textureAnimations != null)
/*     */     {
/*  43 */       if (Config.isAnimatedTextures())
/*     */       {
/*  45 */         updateAnimations();
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public static void updateAnimations() {
/*  52 */     if (textureAnimations != null)
/*     */     {
/*  54 */       for (int i = 0; i < textureAnimations.length; i++) {
/*     */         
/*  56 */         TextureAnimation anim = textureAnimations[i];
/*  57 */         anim.updateTexture();
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public static TextureAnimation[] getTextureAnimations(IResourcePack[] rps) {
/*  64 */     ArrayList list = new ArrayList();
/*     */     
/*  66 */     for (int anims = 0; anims < rps.length; anims++) {
/*     */       
/*  68 */       IResourcePack rp = rps[anims];
/*  69 */       TextureAnimation[] tas = getTextureAnimations(rp);
/*     */       
/*  71 */       if (tas != null)
/*     */       {
/*  73 */         list.addAll(Arrays.asList(tas));
/*     */       }
/*     */     } 
/*     */     
/*  77 */     TextureAnimation[] var5 = (TextureAnimation[])list.toArray((Object[])new TextureAnimation[list.size()]);
/*  78 */     return var5;
/*     */   }
/*     */ 
/*     */   
/*     */   public static TextureAnimation[] getTextureAnimations(IResourcePack rp) {
/*  83 */     String[] animPropNames = ResUtils.collectFiles(rp, "mcpatcher/anim", ".properties", (String[])null);
/*     */     
/*  85 */     if (animPropNames.length <= 0)
/*     */     {
/*  87 */       return null;
/*     */     }
/*     */ 
/*     */     
/*  91 */     ArrayList<TextureAnimation> list = new ArrayList();
/*     */     
/*  93 */     for (int anims = 0; anims < animPropNames.length; anims++) {
/*     */       
/*  95 */       String propName = animPropNames[anims];
/*  96 */       Config.dbg("Texture animation: " + propName);
/*     */ 
/*     */       
/*     */       try {
/* 100 */         ResourceLocation e = new ResourceLocation(propName);
/* 101 */         InputStream in = rp.getInputStream(e);
/* 102 */         Properties props = new Properties();
/* 103 */         props.load(in);
/* 104 */         TextureAnimation anim = makeTextureAnimation(props, e);
/*     */         
/* 106 */         if (anim != null) {
/*     */           
/* 108 */           ResourceLocation locDstTex = new ResourceLocation(anim.getDstTex());
/*     */           
/* 110 */           if (Config.getDefiningResourcePack(locDstTex) != rp)
/*     */           {
/* 112 */             Config.dbg("Skipped: " + propName + ", target texture not loaded from same resource pack");
/*     */           }
/*     */           else
/*     */           {
/* 116 */             list.add(anim);
/*     */           }
/*     */         
/*     */         } 
/* 120 */       } catch (FileNotFoundException var10) {
/*     */         
/* 122 */         Config.warn("File not found: " + var10.getMessage());
/*     */       }
/* 124 */       catch (IOException var11) {
/*     */         
/* 126 */         var11.printStackTrace();
/*     */       } 
/*     */     } 
/*     */     
/* 130 */     TextureAnimation[] var12 = list.<TextureAnimation>toArray(new TextureAnimation[list.size()]);
/* 131 */     return var12;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static TextureAnimation makeTextureAnimation(Properties props, ResourceLocation propLoc) {
/* 137 */     String texFrom = props.getProperty("from");
/* 138 */     String texTo = props.getProperty("to");
/* 139 */     int x = Config.parseInt(props.getProperty("x"), -1);
/* 140 */     int y = Config.parseInt(props.getProperty("y"), -1);
/* 141 */     int width = Config.parseInt(props.getProperty("w"), -1);
/* 142 */     int height = Config.parseInt(props.getProperty("h"), -1);
/*     */     
/* 144 */     if (texFrom != null && texTo != null) {
/*     */       
/* 146 */       if (x >= 0 && y >= 0 && width >= 0 && height >= 0) {
/*     */         
/* 148 */         texFrom = texFrom.trim();
/* 149 */         texTo = texTo.trim();
/* 150 */         String basePath = TextureUtils.getBasePath(propLoc.getResourcePath());
/* 151 */         texFrom = TextureUtils.fixResourcePath(texFrom, basePath);
/* 152 */         texTo = TextureUtils.fixResourcePath(texTo, basePath);
/* 153 */         byte[] imageBytes = getCustomTextureData(texFrom, width);
/*     */         
/* 155 */         if (imageBytes == null) {
/*     */           
/* 157 */           Config.warn("TextureAnimation: Source texture not found: " + texTo);
/* 158 */           return null;
/*     */         } 
/*     */ 
/*     */         
/* 162 */         ResourceLocation locTexTo = new ResourceLocation(texTo);
/*     */         
/* 164 */         if (!Config.hasResource(locTexTo)) {
/*     */           
/* 166 */           Config.warn("TextureAnimation: Target texture not found: " + texTo);
/* 167 */           return null;
/*     */         } 
/*     */ 
/*     */         
/* 171 */         TextureAnimation anim = new TextureAnimation(texFrom, imageBytes, texTo, locTexTo, x, y, width, height, props, 1);
/* 172 */         return anim;
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 178 */       Config.warn("TextureAnimation: Invalid coordinates");
/* 179 */       return null;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 184 */     Config.warn("TextureAnimation: Source or target texture not specified");
/* 185 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static byte[] getCustomTextureData(String imagePath, int tileWidth) {
/* 191 */     byte[] imageBytes = loadImage(imagePath, tileWidth);
/*     */     
/* 193 */     if (imageBytes == null)
/*     */     {
/* 195 */       imageBytes = loadImage("/anim" + imagePath, tileWidth);
/*     */     }
/*     */     
/* 198 */     return imageBytes;
/*     */   }
/*     */ 
/*     */   
/*     */   private static byte[] loadImage(String name, int targetWidth) {
/* 203 */     GameSettings options = Config.getGameSettings();
/*     */ 
/*     */     
/*     */     try {
/* 207 */       ResourceLocation e = new ResourceLocation(name);
/* 208 */       InputStream in = Config.getResourceStream(e);
/*     */       
/* 210 */       if (in == null)
/*     */       {
/* 212 */         return null;
/*     */       }
/*     */ 
/*     */       
/* 216 */       BufferedImage image = readTextureImage(in);
/* 217 */       in.close();
/*     */       
/* 219 */       if (image == null)
/*     */       {
/* 221 */         return null;
/*     */       }
/*     */ 
/*     */       
/* 225 */       if (targetWidth > 0 && image.getWidth() != targetWidth) {
/*     */         
/* 227 */         double width = (image.getHeight() / image.getWidth());
/* 228 */         int ai = (int)(targetWidth * width);
/* 229 */         image = scaleBufferedImage(image, targetWidth, ai);
/*     */       } 
/*     */       
/* 232 */       int var20 = image.getWidth();
/* 233 */       int height = image.getHeight();
/* 234 */       int[] var21 = new int[var20 * height];
/* 235 */       byte[] byteBuf = new byte[var20 * height * 4];
/* 236 */       image.getRGB(0, 0, var20, height, var21, 0, var20);
/*     */       
/* 238 */       for (int l = 0; l < var21.length; l++) {
/*     */         
/* 240 */         int alpha = var21[l] >> 24 & 0xFF;
/* 241 */         int red = var21[l] >> 16 & 0xFF;
/* 242 */         int green = var21[l] >> 8 & 0xFF;
/* 243 */         int blue = var21[l] & 0xFF;
/*     */         
/* 245 */         if (options != null && options.anaglyph) {
/*     */           
/* 247 */           int j3 = (red * 30 + green * 59 + blue * 11) / 100;
/* 248 */           int l3 = (red * 30 + green * 70) / 100;
/* 249 */           int j4 = (red * 30 + blue * 70) / 100;
/* 250 */           red = j3;
/* 251 */           green = l3;
/* 252 */           blue = j4;
/*     */         } 
/*     */         
/* 255 */         byteBuf[l * 4 + 0] = (byte)red;
/* 256 */         byteBuf[l * 4 + 1] = (byte)green;
/* 257 */         byteBuf[l * 4 + 2] = (byte)blue;
/* 258 */         byteBuf[l * 4 + 3] = (byte)alpha;
/*     */       } 
/*     */       
/* 261 */       return byteBuf;
/*     */ 
/*     */     
/*     */     }
/* 265 */     catch (FileNotFoundException var18) {
/*     */       
/* 267 */       return null;
/*     */     }
/* 269 */     catch (Exception var19) {
/*     */       
/* 271 */       var19.printStackTrace();
/* 272 */       return null;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private static BufferedImage readTextureImage(InputStream par1InputStream) throws IOException {
/* 278 */     BufferedImage var2 = ImageIO.read(par1InputStream);
/* 279 */     par1InputStream.close();
/* 280 */     return var2;
/*     */   }
/*     */ 
/*     */   
/*     */   public static BufferedImage scaleBufferedImage(BufferedImage image, int width, int height) {
/* 285 */     BufferedImage scaledImage = new BufferedImage(width, height, 2);
/* 286 */     Graphics2D gr = scaledImage.createGraphics();
/* 287 */     gr.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
/* 288 */     gr.drawImage(image, 0, 0, width, height, null);
/* 289 */     return scaledImage;
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\optifine\TextureAnimations.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */