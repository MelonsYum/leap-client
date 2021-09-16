/*     */ package shadersmod.client;
/*     */ 
/*     */ import java.awt.image.BufferedImage;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.nio.ByteBuffer;
/*     */ import java.nio.IntBuffer;
/*     */ import java.util.Arrays;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import javax.imageio.ImageIO;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.renderer.GlStateManager;
/*     */ import net.minecraft.client.renderer.texture.AbstractTexture;
/*     */ import net.minecraft.client.renderer.texture.DynamicTexture;
/*     */ import net.minecraft.client.renderer.texture.ITextureObject;
/*     */ import net.minecraft.client.renderer.texture.LayeredTexture;
/*     */ import net.minecraft.client.renderer.texture.Stitcher;
/*     */ import net.minecraft.client.renderer.texture.TextureAtlasSprite;
/*     */ import net.minecraft.client.renderer.texture.TextureManager;
/*     */ import net.minecraft.client.renderer.texture.TextureMap;
/*     */ import net.minecraft.client.renderer.texture.TextureUtil;
/*     */ import net.minecraft.client.resources.IResource;
/*     */ import net.minecraft.client.resources.IResourceManager;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import org.lwjgl.BufferUtils;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ import shadersmod.common.SMCLog;
/*     */ 
/*     */ 
/*     */ public class ShadersTex
/*     */ {
/*     */   public static final int initialBufferSize = 1048576;
/*  36 */   public static ByteBuffer byteBuffer = BufferUtils.createByteBuffer(4194304);
/*  37 */   public static IntBuffer intBuffer = byteBuffer.asIntBuffer();
/*  38 */   public static int[] intArray = new int[1048576];
/*     */   public static final int defBaseTexColor = 0;
/*     */   public static final int defNormTexColor = -8421377;
/*     */   public static final int defSpecTexColor = 0;
/*  42 */   public static Map<Integer, MultiTexID> multiTexMap = new HashMap<>();
/*  43 */   public static TextureMap updatingTextureMap = null;
/*  44 */   public static TextureAtlasSprite updatingSprite = null;
/*  45 */   public static MultiTexID updatingTex = null;
/*  46 */   public static MultiTexID boundTex = null;
/*  47 */   public static int updatingPage = 0;
/*  48 */   public static String iconName = null;
/*  49 */   public static IResourceManager resManager = null;
/*  50 */   static ResourceLocation resLocation = null;
/*  51 */   static int imageSize = 0;
/*     */ 
/*     */   
/*     */   public static IntBuffer getIntBuffer(int size) {
/*  55 */     if (intBuffer.capacity() < size) {
/*     */       
/*  57 */       int bufferSize = roundUpPOT(size);
/*  58 */       byteBuffer = BufferUtils.createByteBuffer(bufferSize * 4);
/*  59 */       intBuffer = byteBuffer.asIntBuffer();
/*     */     } 
/*     */     
/*  62 */     return intBuffer;
/*     */   }
/*     */ 
/*     */   
/*     */   public static int[] getIntArray(int size) {
/*  67 */     if (intArray == null)
/*     */     {
/*  69 */       intArray = new int[1048576];
/*     */     }
/*     */     
/*  72 */     if (intArray.length < size)
/*     */     {
/*  74 */       intArray = new int[roundUpPOT(size)];
/*     */     }
/*     */     
/*  77 */     return intArray;
/*     */   }
/*     */ 
/*     */   
/*     */   public static int roundUpPOT(int x) {
/*  82 */     int i = x - 1;
/*  83 */     i |= i >> 1;
/*  84 */     i |= i >> 2;
/*  85 */     i |= i >> 4;
/*  86 */     i |= i >> 8;
/*  87 */     i |= i >> 16;
/*  88 */     return i + 1;
/*     */   }
/*     */ 
/*     */   
/*     */   public static int log2(int x) {
/*  93 */     int log = 0;
/*     */     
/*  95 */     if ((x & 0xFFFF0000) != 0) {
/*     */       
/*  97 */       log += 16;
/*  98 */       x >>= 16;
/*     */     } 
/*     */     
/* 101 */     if ((x & 0xFF00) != 0) {
/*     */       
/* 103 */       log += 8;
/* 104 */       x >>= 8;
/*     */     } 
/*     */     
/* 107 */     if ((x & 0xF0) != 0) {
/*     */       
/* 109 */       log += 4;
/* 110 */       x >>= 4;
/*     */     } 
/*     */     
/* 113 */     if ((x & 0x6) != 0) {
/*     */       
/* 115 */       log += 2;
/* 116 */       x >>= 2;
/*     */     } 
/*     */     
/* 119 */     if ((x & 0x2) != 0)
/*     */     {
/* 121 */       log++;
/*     */     }
/*     */     
/* 124 */     return log;
/*     */   }
/*     */ 
/*     */   
/*     */   public static IntBuffer fillIntBuffer(int size, int value) {
/* 129 */     int[] aint = getIntArray(size);
/* 130 */     IntBuffer intBuf = getIntBuffer(size);
/* 131 */     Arrays.fill(intArray, 0, size, value);
/* 132 */     intBuffer.put(intArray, 0, size);
/* 133 */     return intBuffer;
/*     */   }
/*     */ 
/*     */   
/*     */   public static int[] createAIntImage(int size) {
/* 138 */     int[] aint = new int[size * 3];
/* 139 */     Arrays.fill(aint, 0, size, 0);
/* 140 */     Arrays.fill(aint, size, size * 2, -8421377);
/* 141 */     Arrays.fill(aint, size * 2, size * 3, 0);
/* 142 */     return aint;
/*     */   }
/*     */ 
/*     */   
/*     */   public static int[] createAIntImage(int size, int color) {
/* 147 */     int[] aint = new int[size * 3];
/* 148 */     Arrays.fill(aint, 0, size, color);
/* 149 */     Arrays.fill(aint, size, size * 2, -8421377);
/* 150 */     Arrays.fill(aint, size * 2, size * 3, 0);
/* 151 */     return aint;
/*     */   }
/*     */ 
/*     */   
/*     */   public static MultiTexID getMultiTexID(AbstractTexture tex) {
/* 156 */     MultiTexID multiTex = tex.multiTex;
/*     */     
/* 158 */     if (multiTex == null) {
/*     */       
/* 160 */       int baseTex = tex.getGlTextureId();
/* 161 */       multiTex = multiTexMap.get(Integer.valueOf(baseTex));
/*     */       
/* 163 */       if (multiTex == null) {
/*     */         
/* 165 */         multiTex = new MultiTexID(baseTex, GL11.glGenTextures(), GL11.glGenTextures());
/* 166 */         multiTexMap.put(Integer.valueOf(baseTex), multiTex);
/*     */       } 
/*     */       
/* 169 */       tex.multiTex = multiTex;
/*     */     } 
/*     */     
/* 172 */     return multiTex;
/*     */   }
/*     */ 
/*     */   
/*     */   public static void deleteTextures(AbstractTexture atex, int texid) {
/* 177 */     MultiTexID multiTex = atex.multiTex;
/*     */     
/* 179 */     if (multiTex != null) {
/*     */       
/* 181 */       atex.multiTex = null;
/* 182 */       multiTexMap.remove(Integer.valueOf(multiTex.base));
/* 183 */       GlStateManager.func_179150_h(multiTex.norm);
/* 184 */       GlStateManager.func_179150_h(multiTex.spec);
/*     */       
/* 186 */       if (multiTex.base != texid) {
/*     */         
/* 188 */         SMCLog.warning("Error : MultiTexID.base mismatch: " + multiTex.base + ", texid: " + texid);
/* 189 */         GlStateManager.func_179150_h(multiTex.base);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static void bindNSTextures(int normTex, int specTex) {
/* 196 */     if (Shaders.isRenderingWorld && GlStateManager.getActiveTextureUnit() == 33984) {
/*     */       
/* 198 */       GlStateManager.setActiveTexture(33986);
/* 199 */       GlStateManager.func_179144_i(normTex);
/* 200 */       GlStateManager.setActiveTexture(33987);
/* 201 */       GlStateManager.func_179144_i(specTex);
/* 202 */       GlStateManager.setActiveTexture(33984);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static void bindNSTextures(MultiTexID multiTex) {
/* 208 */     bindNSTextures(multiTex.norm, multiTex.spec);
/*     */   }
/*     */ 
/*     */   
/*     */   public static void bindTextures(int baseTex, int normTex, int specTex) {
/* 213 */     if (Shaders.isRenderingWorld && GlStateManager.getActiveTextureUnit() == 33984) {
/*     */       
/* 215 */       GlStateManager.setActiveTexture(33986);
/* 216 */       GlStateManager.func_179144_i(normTex);
/* 217 */       GlStateManager.setActiveTexture(33987);
/* 218 */       GlStateManager.func_179144_i(specTex);
/* 219 */       GlStateManager.setActiveTexture(33984);
/*     */     } 
/*     */     
/* 222 */     GlStateManager.func_179144_i(baseTex);
/*     */   }
/*     */ 
/*     */   
/*     */   public static void bindTextures(MultiTexID multiTex) {
/* 227 */     boundTex = multiTex;
/*     */     
/* 229 */     if (Shaders.isRenderingWorld && GlStateManager.getActiveTextureUnit() == 33984) {
/*     */       
/* 231 */       if (Shaders.configNormalMap) {
/*     */         
/* 233 */         GlStateManager.setActiveTexture(33986);
/* 234 */         GlStateManager.func_179144_i(multiTex.norm);
/*     */       } 
/*     */       
/* 237 */       if (Shaders.configSpecularMap) {
/*     */         
/* 239 */         GlStateManager.setActiveTexture(33987);
/* 240 */         GlStateManager.func_179144_i(multiTex.spec);
/*     */       } 
/*     */       
/* 243 */       GlStateManager.setActiveTexture(33984);
/*     */     } 
/*     */     
/* 246 */     GlStateManager.func_179144_i(multiTex.base);
/*     */   }
/*     */ 
/*     */   
/*     */   public static void bindTexture(ITextureObject tex) {
/* 251 */     int texId = tex.getGlTextureId();
/*     */     
/* 253 */     if (tex instanceof TextureMap) {
/*     */       
/* 255 */       Shaders.atlasSizeX = ((TextureMap)tex).atlasWidth;
/* 256 */       Shaders.atlasSizeY = ((TextureMap)tex).atlasHeight;
/* 257 */       bindTextures(tex.getMultiTexID());
/*     */     }
/*     */     else {
/*     */       
/* 261 */       Shaders.atlasSizeX = 0;
/* 262 */       Shaders.atlasSizeY = 0;
/* 263 */       bindTextures(tex.getMultiTexID());
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static void bindTextureMapForUpdateAndRender(TextureManager tm, ResourceLocation resLoc) {
/* 269 */     TextureMap tex = (TextureMap)tm.getTexture(resLoc);
/* 270 */     Shaders.atlasSizeX = tex.atlasWidth;
/* 271 */     Shaders.atlasSizeY = tex.atlasHeight;
/* 272 */     bindTextures(updatingTex = tex.getMultiTexID());
/*     */   }
/*     */ 
/*     */   
/*     */   public static void bindTextures(int baseTex) {
/* 277 */     MultiTexID multiTex = multiTexMap.get(Integer.valueOf(baseTex));
/* 278 */     bindTextures(multiTex);
/*     */   }
/*     */ 
/*     */   
/*     */   public static void initDynamicTexture(int texID, int width, int height, DynamicTexture tex) {
/* 283 */     MultiTexID multiTex = tex.getMultiTexID();
/* 284 */     int[] aint = tex.getTextureData();
/* 285 */     int size = width * height;
/* 286 */     Arrays.fill(aint, size, size * 2, -8421377);
/* 287 */     Arrays.fill(aint, size * 2, size * 3, 0);
/* 288 */     TextureUtil.allocateTexture(multiTex.base, width, height);
/* 289 */     TextureUtil.func_147954_b(false, false);
/* 290 */     TextureUtil.setTextureClamped(false);
/* 291 */     TextureUtil.allocateTexture(multiTex.norm, width, height);
/* 292 */     TextureUtil.func_147954_b(false, false);
/* 293 */     TextureUtil.setTextureClamped(false);
/* 294 */     TextureUtil.allocateTexture(multiTex.spec, width, height);
/* 295 */     TextureUtil.func_147954_b(false, false);
/* 296 */     TextureUtil.setTextureClamped(false);
/* 297 */     GlStateManager.func_179144_i(multiTex.base);
/*     */   }
/*     */ 
/*     */   
/*     */   public static void updateDynamicTexture(int texID, int[] src, int width, int height, DynamicTexture tex) {
/* 302 */     MultiTexID multiTex = tex.getMultiTexID();
/* 303 */     GlStateManager.func_179144_i(multiTex.base);
/* 304 */     updateDynTexSubImage1(src, width, height, 0, 0, 0);
/* 305 */     GlStateManager.func_179144_i(multiTex.norm);
/* 306 */     updateDynTexSubImage1(src, width, height, 0, 0, 1);
/* 307 */     GlStateManager.func_179144_i(multiTex.spec);
/* 308 */     updateDynTexSubImage1(src, width, height, 0, 0, 2);
/* 309 */     GlStateManager.func_179144_i(multiTex.base);
/*     */   }
/*     */ 
/*     */   
/*     */   public static void updateDynTexSubImage1(int[] src, int width, int height, int posX, int posY, int page) {
/* 314 */     int size = width * height;
/* 315 */     IntBuffer intBuf = getIntBuffer(size);
/* 316 */     intBuf.clear();
/* 317 */     int offset = page * size;
/*     */     
/* 319 */     if (src.length >= offset + size) {
/*     */       
/* 321 */       intBuf.put(src, offset, size).position(0).limit(size);
/* 322 */       GL11.glTexSubImage2D(3553, 0, posX, posY, width, height, 32993, 33639, intBuf);
/* 323 */       intBuf.clear();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static ITextureObject createDefaultTexture() {
/* 329 */     DynamicTexture tex = new DynamicTexture(1, 1);
/* 330 */     tex.getTextureData()[0] = -1;
/* 331 */     tex.updateDynamicTexture();
/* 332 */     return (ITextureObject)tex;
/*     */   }
/*     */ 
/*     */   
/*     */   public static void allocateTextureMap(int texID, int mipmapLevels, int width, int height, Stitcher stitcher, TextureMap tex) {
/* 337 */     SMCLog.info("allocateTextureMap " + mipmapLevels + " " + width + " " + height + " ");
/* 338 */     updatingTextureMap = tex;
/* 339 */     tex.atlasWidth = width;
/* 340 */     tex.atlasHeight = height;
/* 341 */     MultiTexID multiTex = getMultiTexID((AbstractTexture)tex);
/* 342 */     updatingTex = multiTex;
/* 343 */     TextureUtil.func_180600_a(multiTex.base, mipmapLevels, width, height);
/*     */     
/* 345 */     if (Shaders.configNormalMap)
/*     */     {
/* 347 */       TextureUtil.func_180600_a(multiTex.norm, mipmapLevels, width, height);
/*     */     }
/*     */     
/* 350 */     if (Shaders.configSpecularMap)
/*     */     {
/* 352 */       TextureUtil.func_180600_a(multiTex.spec, mipmapLevels, width, height);
/*     */     }
/*     */     
/* 355 */     GlStateManager.func_179144_i(texID);
/*     */   }
/*     */ 
/*     */   
/*     */   public static TextureAtlasSprite setSprite(TextureAtlasSprite tas) {
/* 360 */     updatingSprite = tas;
/* 361 */     return tas;
/*     */   }
/*     */ 
/*     */   
/*     */   public static String setIconName(String name) {
/* 366 */     iconName = name;
/* 367 */     return name;
/*     */   }
/*     */ 
/*     */   
/*     */   public static void uploadTexSubForLoadAtlas(int[][] data, int width, int height, int xoffset, int yoffset, boolean linear, boolean clamp) {
/* 372 */     TextureUtil.uploadTextureMipmap(data, width, height, xoffset, yoffset, linear, clamp);
/* 373 */     boolean border = false;
/*     */ 
/*     */     
/* 376 */     if (Shaders.configNormalMap) {
/*     */       
/* 378 */       int[][] aaint = readImageAndMipmaps(String.valueOf(iconName) + "_n", width, height, data.length, border, -8421377);
/* 379 */       GlStateManager.func_179144_i(updatingTex.norm);
/* 380 */       TextureUtil.uploadTextureMipmap(aaint, width, height, xoffset, yoffset, linear, clamp);
/*     */     } 
/*     */     
/* 383 */     if (Shaders.configSpecularMap) {
/*     */       
/* 385 */       int[][] aaint = readImageAndMipmaps(String.valueOf(iconName) + "_s", width, height, data.length, border, 0);
/* 386 */       GlStateManager.func_179144_i(updatingTex.spec);
/* 387 */       TextureUtil.uploadTextureMipmap(aaint, width, height, xoffset, yoffset, linear, clamp);
/*     */     } 
/*     */     
/* 390 */     GlStateManager.func_179144_i(updatingTex.base);
/*     */   }
/*     */ 
/*     */   
/*     */   public static int[][] readImageAndMipmaps(String name, int width, int height, int numLevels, boolean border, int defColor) {
/* 395 */     int[][] aaint = new int[numLevels][];
/*     */     
/* 397 */     int[] aint = new int[width * height];
/* 398 */     boolean goodImage = false;
/* 399 */     BufferedImage image = readImage(updatingTextureMap.completeResourceLocation(new ResourceLocation(name), 0));
/*     */     
/* 401 */     if (image != null) {
/*     */       
/* 403 */       int imageWidth = image.getWidth();
/* 404 */       int imageHeight = image.getHeight();
/*     */       
/* 406 */       if (imageWidth + (border ? 16 : 0) == width) {
/*     */         
/* 408 */         goodImage = true;
/* 409 */         image.getRGB(0, 0, imageWidth, imageWidth, aint, 0, imageWidth);
/*     */       } 
/*     */     } 
/*     */     
/* 413 */     if (!goodImage)
/*     */     {
/* 415 */       Arrays.fill(aint, defColor);
/*     */     }
/*     */     
/* 418 */     GlStateManager.func_179144_i(updatingTex.spec);
/* 419 */     aaint = genMipmapsSimple(aaint.length - 1, width, aaint);
/* 420 */     return aaint;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static BufferedImage readImage(ResourceLocation resLoc) {
/*     */     try {
/* 427 */       IResource e = resManager.getResource(resLoc);
/*     */       
/* 429 */       if (e == null)
/*     */       {
/* 431 */         return null;
/*     */       }
/*     */ 
/*     */       
/* 435 */       InputStream istr = e.getInputStream();
/*     */       
/* 437 */       if (istr == null)
/*     */       {
/* 439 */         return null;
/*     */       }
/*     */ 
/*     */       
/* 443 */       BufferedImage image = ImageIO.read(istr);
/* 444 */       istr.close();
/* 445 */       return image;
/*     */ 
/*     */     
/*     */     }
/* 449 */     catch (IOException var4) {
/*     */       
/* 451 */       return null;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static int[][] genMipmapsSimple(int maxLevel, int width, int[][] data) {
/* 457 */     for (int level = 1; level <= maxLevel; level++) {
/*     */       
/* 459 */       if (data[level] == null) {
/*     */         
/* 461 */         int cw = width >> level;
/* 462 */         int pw = cw * 2;
/* 463 */         int[] aintp = data[level - 1];
/* 464 */         int[] aintc = data[level] = new int[cw * cw];
/*     */         
/* 466 */         for (int y = 0; y < cw; y++) {
/*     */           
/* 468 */           for (int x = 0; x < cw; x++) {
/*     */             
/* 470 */             int ppos = y * 2 * pw + x * 2;
/* 471 */             aintc[y * cw + x] = blend4Simple(aintp[ppos], aintp[ppos + 1], aintp[ppos + pw], aintp[ppos + pw + 1]);
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 477 */     return data;
/*     */   }
/*     */ 
/*     */   
/*     */   public static void uploadTexSub(int[][] data, int width, int height, int xoffset, int yoffset, boolean linear, boolean clamp) {
/* 482 */     TextureUtil.uploadTextureMipmap(data, width, height, xoffset, yoffset, linear, clamp);
/*     */     
/* 484 */     if (Shaders.configNormalMap || Shaders.configSpecularMap) {
/*     */       
/* 486 */       if (Shaders.configNormalMap) {
/*     */         
/* 488 */         GlStateManager.func_179144_i(updatingTex.norm);
/* 489 */         uploadTexSub1(data, width, height, xoffset, yoffset, 1);
/*     */       } 
/*     */       
/* 492 */       if (Shaders.configSpecularMap) {
/*     */         
/* 494 */         GlStateManager.func_179144_i(updatingTex.spec);
/* 495 */         uploadTexSub1(data, width, height, xoffset, yoffset, 2);
/*     */       } 
/*     */       
/* 498 */       GlStateManager.func_179144_i(updatingTex.base);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static void uploadTexSub1(int[][] src, int width, int height, int posX, int posY, int page) {
/* 504 */     int size = width * height;
/* 505 */     IntBuffer intBuf = getIntBuffer(size);
/* 506 */     int numLevel = src.length;
/* 507 */     int level = 0;
/* 508 */     int lw = width;
/* 509 */     int lh = height;
/* 510 */     int px = posX;
/*     */     
/* 512 */     for (int py = posY; lw > 0 && lh > 0 && level < numLevel; level++) {
/*     */       
/* 514 */       int lsize = lw * lh;
/* 515 */       int[] aint = src[level];
/* 516 */       intBuf.clear();
/*     */       
/* 518 */       if (aint.length >= lsize * (page + 1)) {
/*     */         
/* 520 */         intBuf.put(aint, lsize * page, lsize).position(0).limit(lsize);
/* 521 */         GL11.glTexSubImage2D(3553, level, px, py, lw, lh, 32993, 33639, intBuf);
/*     */       } 
/*     */       
/* 524 */       lw >>= 1;
/* 525 */       lh >>= 1;
/* 526 */       px >>= 1;
/* 527 */       py >>= 1;
/*     */     } 
/*     */     
/* 530 */     intBuf.clear();
/*     */   }
/*     */ 
/*     */   
/*     */   public static int blend4Alpha(int c0, int c1, int c2, int c3) {
/* 535 */     int dv, a0 = c0 >>> 24 & 0xFF;
/* 536 */     int a1 = c1 >>> 24 & 0xFF;
/* 537 */     int a2 = c2 >>> 24 & 0xFF;
/* 538 */     int a3 = c3 >>> 24 & 0xFF;
/* 539 */     int as = a0 + a1 + a2 + a3;
/* 540 */     int an = (as + 2) / 4;
/*     */ 
/*     */     
/* 543 */     if (as != 0) {
/*     */       
/* 545 */       dv = as;
/*     */     }
/*     */     else {
/*     */       
/* 549 */       dv = 4;
/* 550 */       a0 = 1;
/* 551 */       a1 = 1;
/* 552 */       a2 = 1;
/* 553 */       a3 = 1;
/*     */     } 
/*     */     
/* 556 */     int frac = (dv + 1) / 2;
/* 557 */     int color = an << 24 | ((c0 >>> 16 & 0xFF) * a0 + (c1 >>> 16 & 0xFF) * a1 + (c2 >>> 16 & 0xFF) * a2 + (c3 >>> 16 & 0xFF) * a3 + frac) / dv << 16 | ((c0 >>> 8 & 0xFF) * a0 + (c1 >>> 8 & 0xFF) * a1 + (c2 >>> 8 & 0xFF) * a2 + (c3 >>> 8 & 0xFF) * a3 + frac) / dv << 8 | ((c0 >>> 0 & 0xFF) * a0 + (c1 >>> 0 & 0xFF) * a1 + (c2 >>> 0 & 0xFF) * a2 + (c3 >>> 0 & 0xFF) * a3 + frac) / dv << 0;
/* 558 */     return color;
/*     */   }
/*     */ 
/*     */   
/*     */   public static int blend4Simple(int c0, int c1, int c2, int c3) {
/* 563 */     int color = ((c0 >>> 24 & 0xFF) + (c1 >>> 24 & 0xFF) + (c2 >>> 24 & 0xFF) + (c3 >>> 24 & 0xFF) + 2) / 4 << 24 | ((c0 >>> 16 & 0xFF) + (c1 >>> 16 & 0xFF) + (c2 >>> 16 & 0xFF) + (c3 >>> 16 & 0xFF) + 2) / 4 << 16 | ((c0 >>> 8 & 0xFF) + (c1 >>> 8 & 0xFF) + (c2 >>> 8 & 0xFF) + (c3 >>> 8 & 0xFF) + 2) / 4 << 8 | ((c0 >>> 0 & 0xFF) + (c1 >>> 0 & 0xFF) + (c2 >>> 0 & 0xFF) + (c3 >>> 0 & 0xFF) + 2) / 4 << 0;
/* 564 */     return color;
/*     */   }
/*     */ 
/*     */   
/*     */   public static void genMipmapAlpha(int[] aint, int offset, int width, int height) {
/* 569 */     Math.min(width, height);
/* 570 */     int o2 = offset;
/* 571 */     int w2 = width;
/* 572 */     int h2 = height;
/* 573 */     int o1 = 0;
/* 574 */     int w1 = 0;
/* 575 */     boolean h1 = false;
/*     */ 
/*     */     
/*     */     int level;
/*     */ 
/*     */     
/* 581 */     for (level = 0; w2 > 1 && h2 > 1; o2 = o1) {
/*     */       
/* 583 */       o1 = o2 + w2 * h2;
/* 584 */       w1 = w2 / 2;
/* 585 */       int var16 = h2 / 2;
/*     */       
/* 587 */       for (int p2 = 0; p2 < var16; p2++) {
/*     */         
/* 589 */         int y = o1 + p2 * w1;
/* 590 */         int x = o2 + p2 * 2 * w2;
/*     */         
/* 592 */         for (int x1 = 0; x1 < w1; x1++)
/*     */         {
/* 594 */           aint[y + x1] = blend4Alpha(aint[x + x1 * 2], aint[x + x1 * 2 + 1], aint[x + w2 + x1 * 2], aint[x + w2 + x1 * 2 + 1]);
/*     */         }
/*     */       } 
/*     */       
/* 598 */       level++;
/* 599 */       w2 = w1;
/* 600 */       h2 = var16;
/*     */     } 
/*     */     
/* 603 */     while (level > 0) {
/*     */       
/* 605 */       level--;
/* 606 */       w2 = width >> level;
/* 607 */       h2 = height >> level;
/* 608 */       o2 = o1 - w2 * h2;
/* 609 */       int p2 = o2;
/*     */       
/* 611 */       for (int y = 0; y < h2; y++) {
/*     */         
/* 613 */         for (int x = 0; x < w2; x++) {
/*     */           
/* 615 */           if (aint[p2] == 0)
/*     */           {
/* 617 */             aint[p2] = aint[o1 + y / 2 * w1 + x / 2] & 0xFFFFFF;
/*     */           }
/*     */           
/* 620 */           p2++;
/*     */         } 
/*     */       } 
/*     */       
/* 624 */       o1 = o2;
/* 625 */       w1 = w2;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static void genMipmapSimple(int[] aint, int offset, int width, int height) {
/* 631 */     Math.min(width, height);
/* 632 */     int o2 = offset;
/* 633 */     int w2 = width;
/* 634 */     int h2 = height;
/* 635 */     int o1 = 0;
/* 636 */     int w1 = 0;
/* 637 */     boolean h1 = false;
/*     */ 
/*     */     
/*     */     int level;
/*     */ 
/*     */     
/* 643 */     for (level = 0; w2 > 1 && h2 > 1; o2 = o1) {
/*     */       
/* 645 */       o1 = o2 + w2 * h2;
/* 646 */       w1 = w2 / 2;
/* 647 */       int var16 = h2 / 2;
/*     */       
/* 649 */       for (int p2 = 0; p2 < var16; p2++) {
/*     */         
/* 651 */         int y = o1 + p2 * w1;
/* 652 */         int x = o2 + p2 * 2 * w2;
/*     */         
/* 654 */         for (int x1 = 0; x1 < w1; x1++)
/*     */         {
/* 656 */           aint[y + x1] = blend4Simple(aint[x + x1 * 2], aint[x + x1 * 2 + 1], aint[x + w2 + x1 * 2], aint[x + w2 + x1 * 2 + 1]);
/*     */         }
/*     */       } 
/*     */       
/* 660 */       level++;
/* 661 */       w2 = w1;
/* 662 */       h2 = var16;
/*     */     } 
/*     */     
/* 665 */     while (level > 0) {
/*     */       
/* 667 */       level--;
/* 668 */       w2 = width >> level;
/* 669 */       h2 = height >> level;
/* 670 */       o2 = o1 - w2 * h2;
/* 671 */       int p2 = o2;
/*     */       
/* 673 */       for (int y = 0; y < h2; y++) {
/*     */         
/* 675 */         for (int x = 0; x < w2; x++) {
/*     */           
/* 677 */           if (aint[p2] == 0)
/*     */           {
/* 679 */             aint[p2] = aint[o1 + y / 2 * w1 + x / 2] & 0xFFFFFF;
/*     */           }
/*     */           
/* 682 */           p2++;
/*     */         } 
/*     */       } 
/*     */       
/* 686 */       o1 = o2;
/* 687 */       w1 = w2;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean isSemiTransparent(int[] aint, int width, int height) {
/* 693 */     int size = width * height;
/*     */     
/* 695 */     if (aint[0] >>> 24 == 255 && aint[size - 1] == 0)
/*     */     {
/* 697 */       return true;
/*     */     }
/*     */ 
/*     */     
/* 701 */     for (int i = 0; i < size; i++) {
/*     */       
/* 703 */       int alpha = aint[i] >>> 24;
/*     */       
/* 705 */       if (alpha != 0 && alpha != 255)
/*     */       {
/* 707 */         return true;
/*     */       }
/*     */     } 
/*     */     
/* 711 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static void updateSubTex1(int[] src, int width, int height, int posX, int posY) {
/* 717 */     int level = 0;
/* 718 */     int cw = width;
/* 719 */     int ch = height;
/* 720 */     int cx = posX;
/*     */     
/* 722 */     for (int cy = posY; cw > 0 && ch > 0; cy /= 2) {
/*     */       
/* 724 */       GL11.glCopyTexSubImage2D(3553, level, cx, cy, 0, 0, cw, ch);
/* 725 */       level++;
/* 726 */       cw /= 2;
/* 727 */       ch /= 2;
/* 728 */       cx /= 2;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static void setupTexture(MultiTexID multiTex, int[] src, int width, int height, boolean linear, boolean clamp) {
/* 734 */     int mmfilter = linear ? 9729 : 9728;
/* 735 */     int wraptype = clamp ? 10496 : 10497;
/* 736 */     int size = width * height;
/* 737 */     IntBuffer intBuf = getIntBuffer(size);
/* 738 */     intBuf.clear();
/* 739 */     intBuf.put(src, 0, size).position(0).limit(size);
/* 740 */     GlStateManager.func_179144_i(multiTex.base);
/* 741 */     GL11.glTexImage2D(3553, 0, 6408, width, height, 0, 32993, 33639, intBuf);
/* 742 */     GL11.glTexParameteri(3553, 10241, mmfilter);
/* 743 */     GL11.glTexParameteri(3553, 10240, mmfilter);
/* 744 */     GL11.glTexParameteri(3553, 10242, wraptype);
/* 745 */     GL11.glTexParameteri(3553, 10243, wraptype);
/* 746 */     intBuf.put(src, size, size).position(0).limit(size);
/* 747 */     GlStateManager.func_179144_i(multiTex.norm);
/* 748 */     GL11.glTexImage2D(3553, 0, 6408, width, height, 0, 32993, 33639, intBuf);
/* 749 */     GL11.glTexParameteri(3553, 10241, mmfilter);
/* 750 */     GL11.glTexParameteri(3553, 10240, mmfilter);
/* 751 */     GL11.glTexParameteri(3553, 10242, wraptype);
/* 752 */     GL11.glTexParameteri(3553, 10243, wraptype);
/* 753 */     intBuf.put(src, size * 2, size).position(0).limit(size);
/* 754 */     GlStateManager.func_179144_i(multiTex.spec);
/* 755 */     GL11.glTexImage2D(3553, 0, 6408, width, height, 0, 32993, 33639, intBuf);
/* 756 */     GL11.glTexParameteri(3553, 10241, mmfilter);
/* 757 */     GL11.glTexParameteri(3553, 10240, mmfilter);
/* 758 */     GL11.glTexParameteri(3553, 10242, wraptype);
/* 759 */     GL11.glTexParameteri(3553, 10243, wraptype);
/* 760 */     GlStateManager.func_179144_i(multiTex.base);
/*     */   }
/*     */ 
/*     */   
/*     */   public static void updateSubImage(MultiTexID multiTex, int[] src, int width, int height, int posX, int posY, boolean linear, boolean clamp) {
/* 765 */     int size = width * height;
/* 766 */     IntBuffer intBuf = getIntBuffer(size);
/* 767 */     intBuf.clear();
/* 768 */     intBuf.put(src, 0, size);
/* 769 */     intBuf.position(0).limit(size);
/* 770 */     GlStateManager.func_179144_i(multiTex.base);
/* 771 */     GL11.glTexParameteri(3553, 10241, 9728);
/* 772 */     GL11.glTexParameteri(3553, 10240, 9728);
/* 773 */     GL11.glTexParameteri(3553, 10242, 10497);
/* 774 */     GL11.glTexParameteri(3553, 10243, 10497);
/* 775 */     GL11.glTexSubImage2D(3553, 0, posX, posY, width, height, 32993, 33639, intBuf);
/*     */     
/* 777 */     if (src.length == size * 3) {
/*     */       
/* 779 */       intBuf.clear();
/* 780 */       intBuf.put(src, size, size).position(0);
/* 781 */       intBuf.position(0).limit(size);
/*     */     } 
/*     */     
/* 784 */     GlStateManager.func_179144_i(multiTex.norm);
/* 785 */     GL11.glTexParameteri(3553, 10241, 9728);
/* 786 */     GL11.glTexParameteri(3553, 10240, 9728);
/* 787 */     GL11.glTexParameteri(3553, 10242, 10497);
/* 788 */     GL11.glTexParameteri(3553, 10243, 10497);
/* 789 */     GL11.glTexSubImage2D(3553, 0, posX, posY, width, height, 32993, 33639, intBuf);
/*     */     
/* 791 */     if (src.length == size * 3) {
/*     */       
/* 793 */       intBuf.clear();
/* 794 */       intBuf.put(src, size * 2, size);
/* 795 */       intBuf.position(0).limit(size);
/*     */     } 
/*     */     
/* 798 */     GlStateManager.func_179144_i(multiTex.spec);
/* 799 */     GL11.glTexParameteri(3553, 10241, 9728);
/* 800 */     GL11.glTexParameteri(3553, 10240, 9728);
/* 801 */     GL11.glTexParameteri(3553, 10242, 10497);
/* 802 */     GL11.glTexParameteri(3553, 10243, 10497);
/* 803 */     GL11.glTexSubImage2D(3553, 0, posX, posY, width, height, 32993, 33639, intBuf);
/* 804 */     GlStateManager.setActiveTexture(33984);
/*     */   }
/*     */ 
/*     */   
/*     */   public static ResourceLocation getNSMapLocation(ResourceLocation location, String mapName) {
/* 809 */     String basename = location.getResourcePath();
/* 810 */     String[] basenameParts = basename.split(".png");
/* 811 */     String basenameNoFileType = basenameParts[0];
/* 812 */     return new ResourceLocation(location.getResourceDomain(), String.valueOf(basenameNoFileType) + "_" + mapName + ".png");
/*     */   }
/*     */ 
/*     */   
/*     */   public static void loadNSMap(IResourceManager manager, ResourceLocation location, int width, int height, int[] aint) {
/* 817 */     if (Shaders.configNormalMap)
/*     */     {
/* 819 */       loadNSMap1(manager, getNSMapLocation(location, "n"), width, height, aint, width * height, -8421377);
/*     */     }
/*     */     
/* 822 */     if (Shaders.configSpecularMap)
/*     */     {
/* 824 */       loadNSMap1(manager, getNSMapLocation(location, "s"), width, height, aint, width * height * 2, 0);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public static void loadNSMap1(IResourceManager manager, ResourceLocation location, int width, int height, int[] aint, int offset, int defaultColor) {
/* 830 */     boolean good = false;
/*     */ 
/*     */     
/*     */     try {
/* 834 */       IResource ex = manager.getResource(location);
/* 835 */       BufferedImage bufferedimage = ImageIO.read(ex.getInputStream());
/*     */       
/* 837 */       if (bufferedimage != null && bufferedimage.getWidth() == width && bufferedimage.getHeight() == height)
/*     */       {
/* 839 */         bufferedimage.getRGB(0, 0, width, height, aint, offset, width);
/* 840 */         good = true;
/*     */       }
/*     */     
/* 843 */     } catch (IOException iOException) {}
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 848 */     if (!good)
/*     */     {
/* 850 */       Arrays.fill(aint, offset, offset + width * height, defaultColor);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public static int loadSimpleTexture(int textureID, BufferedImage bufferedimage, boolean linear, boolean clamp, IResourceManager resourceManager, ResourceLocation location, MultiTexID multiTex) {
/* 856 */     int width = bufferedimage.getWidth();
/* 857 */     int height = bufferedimage.getHeight();
/* 858 */     int size = width * height;
/* 859 */     int[] aint = getIntArray(size * 3);
/* 860 */     bufferedimage.getRGB(0, 0, width, height, aint, 0, width);
/* 861 */     loadNSMap(resourceManager, location, width, height, aint);
/* 862 */     setupTexture(multiTex, aint, width, height, linear, clamp);
/* 863 */     return textureID;
/*     */   }
/*     */ 
/*     */   
/*     */   public static void mergeImage(int[] aint, int dstoff, int srcoff, int size) {}
/*     */   
/*     */   public static int blendColor(int color1, int color2, int factor1) {
/* 870 */     int factor2 = 255 - factor1;
/* 871 */     return ((color1 >>> 24 & 0xFF) * factor1 + (color2 >>> 24 & 0xFF) * factor2) / 255 << 24 | ((color1 >>> 16 & 0xFF) * factor1 + (color2 >>> 16 & 0xFF) * factor2) / 255 << 16 | ((color1 >>> 8 & 0xFF) * factor1 + (color2 >>> 8 & 0xFF) * factor2) / 255 << 8 | ((color1 >>> 0 & 0xFF) * factor1 + (color2 >>> 0 & 0xFF) * factor2) / 255 << 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public static void loadLayeredTexture(LayeredTexture tex, IResourceManager manager, List list) {
/* 876 */     int width = 0;
/* 877 */     int height = 0;
/* 878 */     int size = 0;
/* 879 */     int[] image = null;
/* 880 */     Iterator<String> iterator = list.iterator();
/*     */     
/* 882 */     while (iterator.hasNext()) {
/*     */       
/* 884 */       String s = iterator.next();
/*     */       
/* 886 */       if (s != null) {
/*     */         
/*     */         try {
/*     */           
/* 890 */           ResourceLocation ex = new ResourceLocation(s);
/* 891 */           InputStream inputstream = manager.getResource(ex).getInputStream();
/* 892 */           BufferedImage bufimg = ImageIO.read(inputstream);
/*     */           
/* 894 */           if (size == 0) {
/*     */             
/* 896 */             width = bufimg.getWidth();
/* 897 */             height = bufimg.getHeight();
/* 898 */             size = width * height;
/* 899 */             image = createAIntImage(size, 0);
/*     */           } 
/*     */           
/* 902 */           int[] aint = getIntArray(size * 3);
/* 903 */           bufimg.getRGB(0, 0, width, height, aint, 0, width);
/* 904 */           loadNSMap(manager, ex, width, height, aint);
/*     */           
/* 906 */           for (int i = 0; i < size; i++)
/*     */           {
/* 908 */             int alpha = aint[i] >>> 24 & 0xFF;
/* 909 */             image[size * 0 + i] = blendColor(aint[size * 0 + i], image[size * 0 + i], alpha);
/* 910 */             image[size * 1 + i] = blendColor(aint[size * 1 + i], image[size * 1 + i], alpha);
/* 911 */             image[size * 2 + i] = blendColor(aint[size * 2 + i], image[size * 2 + i], alpha);
/*     */           }
/*     */         
/* 914 */         } catch (IOException var15) {
/*     */           
/* 916 */           var15.printStackTrace();
/*     */         } 
/*     */       }
/*     */     } 
/*     */     
/* 921 */     setupTexture(tex.getMultiTexID(), image, width, height, false, false);
/*     */   }
/*     */ 
/*     */   
/*     */   static void updateTextureMinMagFilter() {
/* 926 */     TextureManager texman = Minecraft.getMinecraft().getTextureManager();
/* 927 */     ITextureObject texObj = texman.getTexture(TextureMap.locationBlocksTexture);
/*     */     
/* 929 */     if (texObj != null) {
/*     */       
/* 931 */       MultiTexID multiTex = texObj.getMultiTexID();
/* 932 */       GlStateManager.func_179144_i(multiTex.base);
/* 933 */       GL11.glTexParameteri(3553, 10241, Shaders.texMinFilValue[Shaders.configTexMinFilB]);
/* 934 */       GL11.glTexParameteri(3553, 10240, Shaders.texMagFilValue[Shaders.configTexMagFilB]);
/* 935 */       GlStateManager.func_179144_i(multiTex.norm);
/* 936 */       GL11.glTexParameteri(3553, 10241, Shaders.texMinFilValue[Shaders.configTexMinFilN]);
/* 937 */       GL11.glTexParameteri(3553, 10240, Shaders.texMagFilValue[Shaders.configTexMagFilN]);
/* 938 */       GlStateManager.func_179144_i(multiTex.spec);
/* 939 */       GL11.glTexParameteri(3553, 10241, Shaders.texMinFilValue[Shaders.configTexMinFilS]);
/* 940 */       GL11.glTexParameteri(3553, 10240, Shaders.texMagFilValue[Shaders.configTexMagFilS]);
/* 941 */       GlStateManager.func_179144_i(0);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static IResource loadResource(IResourceManager manager, ResourceLocation location) throws IOException {
/* 947 */     resManager = manager;
/* 948 */     resLocation = location;
/* 949 */     return manager.getResource(location);
/*     */   }
/*     */ 
/*     */   
/*     */   public static int[] loadAtlasSprite(BufferedImage bufferedimage, int startX, int startY, int w, int h, int[] aint, int offset, int scansize) {
/* 954 */     imageSize = w * h;
/* 955 */     bufferedimage.getRGB(startX, startY, w, h, aint, offset, scansize);
/* 956 */     loadNSMap(resManager, resLocation, w, h, aint);
/* 957 */     return aint;
/*     */   }
/*     */ 
/*     */   
/*     */   public static int[][] getFrameTexData(int[][] src, int width, int height, int frameIndex) {
/* 962 */     int numLevel = src.length;
/* 963 */     int[][] dst = new int[numLevel][];
/*     */     
/* 965 */     for (int level = 0; level < numLevel; level++) {
/*     */       
/* 967 */       int[] sr1 = src[level];
/*     */       
/* 969 */       if (sr1 != null) {
/*     */         
/* 971 */         int frameSize = (width >> level) * (height >> level);
/* 972 */         int[] ds1 = new int[frameSize * 3];
/* 973 */         dst[level] = ds1;
/* 974 */         int srcSize = sr1.length / 3;
/* 975 */         int srcPos = frameSize * frameIndex;
/* 976 */         byte dstPos = 0;
/* 977 */         System.arraycopy(sr1, srcPos, ds1, dstPos, frameSize);
/* 978 */         srcPos += srcSize;
/* 979 */         int var13 = dstPos + frameSize;
/* 980 */         System.arraycopy(sr1, srcPos, ds1, var13, frameSize);
/* 981 */         srcPos += srcSize;
/* 982 */         var13 += frameSize;
/* 983 */         System.arraycopy(sr1, srcPos, ds1, var13, frameSize);
/*     */       } 
/*     */     } 
/*     */     
/* 987 */     return dst;
/*     */   }
/*     */ 
/*     */   
/*     */   public static int[][] prepareAF(TextureAtlasSprite tas, int[][] src, int width, int height) {
/* 992 */     boolean skip = true;
/* 993 */     return src;
/*     */   }
/*     */   
/*     */   public static void fixTransparentColor(TextureAtlasSprite tas, int[] aint) {}
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\shadersmod\client\ShadersTex.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */