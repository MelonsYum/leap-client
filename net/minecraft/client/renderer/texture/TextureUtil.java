/*     */ package net.minecraft.client.renderer.texture;
/*     */ 
/*     */ import java.awt.image.BufferedImage;
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.nio.IntBuffer;
/*     */ import javax.imageio.ImageIO;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.renderer.GLAllocation;
/*     */ import net.minecraft.client.renderer.GlStateManager;
/*     */ import net.minecraft.client.resources.IResourceManager;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import optifine.Config;
/*     */ import optifine.Mipmaps;
/*     */ import optifine.Reflector;
/*     */ import org.apache.commons.io.IOUtils;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ import org.lwjgl.BufferUtils;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TextureUtil
/*     */ {
/*  28 */   private static final Logger logger = LogManager.getLogger();
/*  29 */   private static final IntBuffer dataBuffer = GLAllocation.createDirectIntBuffer(4194304);
/*  30 */   public static final DynamicTexture missingTexture = new DynamicTexture(16, 16);
/*  31 */   public static final int[] missingTextureData = missingTexture.getTextureData();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int glGenTextures() {
/*  37 */     return GlStateManager.func_179146_y();
/*     */   }
/*     */ 
/*     */   
/*     */   public static void deleteTexture(int p_147942_0_) {
/*  42 */     GlStateManager.func_179150_h(p_147942_0_);
/*     */   }
/*     */ 
/*     */   
/*     */   public static int uploadTextureImage(int p_110987_0_, BufferedImage p_110987_1_) {
/*  47 */     return uploadTextureImageAllocate(p_110987_0_, p_110987_1_, false, false);
/*     */   }
/*     */ 
/*     */   
/*     */   public static void uploadTexture(int p_110988_0_, int[] p_110988_1_, int p_110988_2_, int p_110988_3_) {
/*  52 */     bindTexture(p_110988_0_);
/*  53 */     uploadTextureSub(0, p_110988_1_, p_110988_2_, p_110988_3_, 0, 0, false, false, false);
/*     */   }
/*     */ 
/*     */   
/*     */   public static int[][] generateMipmapData(int p_147949_0_, int p_147949_1_, int[][] p_147949_2_) {
/*  58 */     int[][] var3 = new int[p_147949_0_ + 1][];
/*  59 */     var3[0] = p_147949_2_[0];
/*     */     
/*  61 */     if (p_147949_0_ > 0) {
/*     */       
/*  63 */       boolean var4 = false;
/*     */       
/*     */       int var5;
/*  66 */       for (var5 = 0; var5 < p_147949_2_.length; var5++) {
/*     */         
/*  68 */         if (p_147949_2_[0][var5] >> 24 == 0) {
/*     */           
/*  70 */           var4 = true;
/*     */           
/*     */           break;
/*     */         } 
/*     */       } 
/*  75 */       for (var5 = 1; var5 <= p_147949_0_; var5++) {
/*     */         
/*  77 */         if (p_147949_2_[var5] != null) {
/*     */           
/*  79 */           var3[var5] = p_147949_2_[var5];
/*     */         }
/*     */         else {
/*     */           
/*  83 */           int[] var6 = var3[var5 - 1];
/*  84 */           int[] var7 = new int[var6.length >> 2];
/*  85 */           int var8 = p_147949_1_ >> var5;
/*  86 */           int var9 = var7.length / var8;
/*  87 */           int var10 = var8 << 1;
/*     */           
/*  89 */           for (int var11 = 0; var11 < var8; var11++) {
/*     */             
/*  91 */             for (int var12 = 0; var12 < var9; var12++) {
/*     */               
/*  93 */               int var13 = 2 * (var11 + var12 * var10);
/*  94 */               var7[var11 + var12 * var8] = func_147943_a(var6[var13 + 0], var6[var13 + 1], var6[var13 + 0 + var10], var6[var13 + 1 + var10], var4);
/*     */             } 
/*     */           } 
/*     */           
/*  98 */           var3[var5] = var7;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 103 */     return var3;
/*     */   }
/*     */ 
/*     */   
/*     */   private static int func_147943_a(int p_147943_0_, int p_147943_1_, int p_147943_2_, int p_147943_3_, boolean p_147943_4_) {
/* 108 */     return Mipmaps.alphaBlend(p_147943_0_, p_147943_1_, p_147943_2_, p_147943_3_);
/*     */   }
/*     */ 
/*     */   
/*     */   private static int func_147944_a(int p_147944_0_, int p_147944_1_, int p_147944_2_, int p_147944_3_, int p_147944_4_) {
/* 113 */     float var5 = (float)Math.pow(((p_147944_0_ >> p_147944_4_ & 0xFF) / 255.0F), 2.2D);
/* 114 */     float var6 = (float)Math.pow(((p_147944_1_ >> p_147944_4_ & 0xFF) / 255.0F), 2.2D);
/* 115 */     float var7 = (float)Math.pow(((p_147944_2_ >> p_147944_4_ & 0xFF) / 255.0F), 2.2D);
/* 116 */     float var8 = (float)Math.pow(((p_147944_3_ >> p_147944_4_ & 0xFF) / 255.0F), 2.2D);
/* 117 */     float var9 = (float)Math.pow((var5 + var6 + var7 + var8) * 0.25D, 0.45454545454545453D);
/* 118 */     return (int)(var9 * 255.0D);
/*     */   }
/*     */ 
/*     */   
/*     */   public static void uploadTextureMipmap(int[][] p_147955_0_, int p_147955_1_, int p_147955_2_, int p_147955_3_, int p_147955_4_, boolean p_147955_5_, boolean p_147955_6_) {
/* 123 */     for (int var7 = 0; var7 < p_147955_0_.length; var7++) {
/*     */       
/* 125 */       int[] var8 = p_147955_0_[var7];
/* 126 */       uploadTextureSub(var7, var8, p_147955_1_ >> var7, p_147955_2_ >> var7, p_147955_3_ >> var7, p_147955_4_ >> var7, p_147955_5_, p_147955_6_, (p_147955_0_.length > 1));
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private static void uploadTextureSub(int p_147947_0_, int[] p_147947_1_, int p_147947_2_, int p_147947_3_, int p_147947_4_, int p_147947_5_, boolean p_147947_6_, boolean p_147947_7_, boolean p_147947_8_) {
/* 132 */     int var9 = 4194304 / p_147947_2_;
/* 133 */     func_147954_b(p_147947_6_, p_147947_8_);
/* 134 */     setTextureClamped(p_147947_7_);
/*     */ 
/*     */     
/* 137 */     for (int var10 = 0; var10 < p_147947_2_ * p_147947_3_; var10 += p_147947_2_ * var12) {
/*     */       
/* 139 */       int var11 = var10 / p_147947_2_;
/* 140 */       int var12 = Math.min(var9, p_147947_3_ - var11);
/* 141 */       int var13 = p_147947_2_ * var12;
/* 142 */       copyToBufferPos(p_147947_1_, var10, var13);
/* 143 */       GL11.glTexSubImage2D(3553, p_147947_0_, p_147947_4_, p_147947_5_ + var11, p_147947_2_, var12, 32993, 33639, dataBuffer);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static int uploadTextureImageAllocate(int p_110989_0_, BufferedImage p_110989_1_, boolean p_110989_2_, boolean p_110989_3_) {
/* 149 */     allocateTexture(p_110989_0_, p_110989_1_.getWidth(), p_110989_1_.getHeight());
/* 150 */     return uploadTextureImageSub(p_110989_0_, p_110989_1_, 0, 0, p_110989_2_, p_110989_3_);
/*     */   }
/*     */ 
/*     */   
/*     */   public static void allocateTexture(int p_110991_0_, int p_110991_1_, int p_110991_2_) {
/* 155 */     func_180600_a(p_110991_0_, 0, p_110991_1_, p_110991_2_);
/*     */   }
/*     */ 
/*     */   
/*     */   public static void func_180600_a(int p_180600_0_, int p_180600_1_, int p_180600_2_, int p_180600_3_) {
/* 160 */     Class<TextureUtil> monitor = TextureUtil.class;
/*     */     
/* 162 */     if (Reflector.SplashScreen.exists())
/*     */     {
/* 164 */       monitor = Reflector.SplashScreen.getTargetClass();
/*     */     }
/*     */     
/* 167 */     synchronized (monitor) {
/*     */       
/* 169 */       deleteTexture(p_180600_0_);
/* 170 */       bindTexture(p_180600_0_);
/*     */     } 
/*     */     
/* 173 */     if (p_180600_1_ >= 0) {
/*     */       
/* 175 */       GL11.glTexParameteri(3553, 33085, p_180600_1_);
/* 176 */       GL11.glTexParameterf(3553, 33082, 0.0F);
/* 177 */       GL11.glTexParameterf(3553, 33083, p_180600_1_);
/* 178 */       GL11.glTexParameterf(3553, 34049, 0.0F);
/*     */     } 
/*     */     
/* 181 */     for (int var4 = 0; var4 <= p_180600_1_; var4++)
/*     */     {
/* 183 */       GL11.glTexImage2D(3553, var4, 6408, p_180600_2_ >> var4, p_180600_3_ >> var4, 0, 32993, 33639, null);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public static int uploadTextureImageSub(int p_110995_0_, BufferedImage p_110995_1_, int p_110995_2_, int p_110995_3_, boolean p_110995_4_, boolean p_110995_5_) {
/* 189 */     bindTexture(p_110995_0_);
/* 190 */     uploadTextureImageSubImpl(p_110995_1_, p_110995_2_, p_110995_3_, p_110995_4_, p_110995_5_);
/* 191 */     return p_110995_0_;
/*     */   }
/*     */ 
/*     */   
/*     */   private static void uploadTextureImageSubImpl(BufferedImage p_110993_0_, int p_110993_1_, int p_110993_2_, boolean p_110993_3_, boolean p_110993_4_) {
/* 196 */     int var5 = p_110993_0_.getWidth();
/* 197 */     int var6 = p_110993_0_.getHeight();
/* 198 */     int var7 = 4194304 / var5;
/* 199 */     int[] var8 = new int[var7 * var5];
/* 200 */     setTextureBlurred(p_110993_3_);
/* 201 */     setTextureClamped(p_110993_4_);
/*     */     
/* 203 */     for (int var9 = 0; var9 < var5 * var6; var9 += var5 * var7) {
/*     */       
/* 205 */       int var10 = var9 / var5;
/* 206 */       int var11 = Math.min(var7, var6 - var10);
/* 207 */       int var12 = var5 * var11;
/* 208 */       p_110993_0_.getRGB(0, var10, var5, var11, var8, 0, var5);
/* 209 */       copyToBuffer(var8, var12);
/* 210 */       GL11.glTexSubImage2D(3553, 0, p_110993_1_, p_110993_2_ + var10, var5, var11, 32993, 33639, dataBuffer);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static void setTextureClamped(boolean p_110997_0_) {
/* 216 */     if (p_110997_0_) {
/*     */       
/* 218 */       GL11.glTexParameteri(3553, 10242, 33071);
/* 219 */       GL11.glTexParameteri(3553, 10243, 33071);
/*     */     }
/*     */     else {
/*     */       
/* 223 */       GL11.glTexParameteri(3553, 10242, 10497);
/* 224 */       GL11.glTexParameteri(3553, 10243, 10497);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private static void setTextureBlurred(boolean p_147951_0_) {
/* 230 */     func_147954_b(p_147951_0_, false);
/*     */   }
/*     */ 
/*     */   
/*     */   public static void func_147954_b(boolean p_147954_0_, boolean p_147954_1_) {
/* 235 */     if (p_147954_0_) {
/*     */       
/* 237 */       GL11.glTexParameteri(3553, 10241, p_147954_1_ ? 9987 : 9729);
/* 238 */       GL11.glTexParameteri(3553, 10240, 9729);
/*     */     }
/*     */     else {
/*     */       
/* 242 */       int mipmapType = Config.getMipmapType();
/* 243 */       GL11.glTexParameteri(3553, 10241, p_147954_1_ ? mipmapType : 9728);
/* 244 */       GL11.glTexParameteri(3553, 10240, 9728);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private static void copyToBuffer(int[] p_110990_0_, int p_110990_1_) {
/* 250 */     copyToBufferPos(p_110990_0_, 0, p_110990_1_);
/*     */   }
/*     */ 
/*     */   
/*     */   private static void copyToBufferPos(int[] p_110994_0_, int p_110994_1_, int p_110994_2_) {
/* 255 */     int[] var3 = p_110994_0_;
/*     */     
/* 257 */     if ((Minecraft.getMinecraft()).gameSettings.anaglyph)
/*     */     {
/* 259 */       var3 = updateAnaglyph(p_110994_0_);
/*     */     }
/*     */     
/* 262 */     dataBuffer.clear();
/* 263 */     dataBuffer.put(var3, p_110994_1_, p_110994_2_);
/* 264 */     dataBuffer.position(0).limit(p_110994_2_);
/*     */   }
/*     */ 
/*     */   
/*     */   static void bindTexture(int p_94277_0_) {
/* 269 */     GlStateManager.func_179144_i(p_94277_0_);
/*     */   }
/*     */ 
/*     */   
/*     */   public static int[] readImageData(IResourceManager p_110986_0_, ResourceLocation p_110986_1_) throws IOException {
/* 274 */     BufferedImage var2 = func_177053_a(p_110986_0_.getResource(p_110986_1_).getInputStream());
/*     */     
/* 276 */     if (var2 == null)
/*     */     {
/* 278 */       return null;
/*     */     }
/*     */ 
/*     */     
/* 282 */     int var3 = var2.getWidth();
/* 283 */     int var4 = var2.getHeight();
/* 284 */     int[] var5 = new int[var3 * var4];
/* 285 */     var2.getRGB(0, 0, var3, var4, var5, 0, var3);
/* 286 */     return var5;
/*     */   }
/*     */ 
/*     */   
/*     */   public static BufferedImage func_177053_a(InputStream p_177053_0_) throws IOException, IOException {
/*     */     BufferedImage var1;
/* 292 */     if (p_177053_0_ == null)
/*     */     {
/* 294 */       return null;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/* 302 */       var1 = ImageIO.read(p_177053_0_);
/*     */     }
/*     */     finally {
/*     */       
/* 306 */       IOUtils.closeQuietly(p_177053_0_);
/*     */     } 
/*     */     
/* 309 */     return var1;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static int[] updateAnaglyph(int[] p_110985_0_) {
/* 315 */     int[] var1 = new int[p_110985_0_.length];
/*     */     
/* 317 */     for (int var2 = 0; var2 < p_110985_0_.length; var2++)
/*     */     {
/* 319 */       var1[var2] = func_177054_c(p_110985_0_[var2]);
/*     */     }
/*     */     
/* 322 */     return var1;
/*     */   }
/*     */ 
/*     */   
/*     */   public static int func_177054_c(int p_177054_0_) {
/* 327 */     int var1 = p_177054_0_ >> 24 & 0xFF;
/* 328 */     int var2 = p_177054_0_ >> 16 & 0xFF;
/* 329 */     int var3 = p_177054_0_ >> 8 & 0xFF;
/* 330 */     int var4 = p_177054_0_ & 0xFF;
/* 331 */     int var5 = (var2 * 30 + var3 * 59 + var4 * 11) / 100;
/* 332 */     int var6 = (var2 * 30 + var3 * 70) / 100;
/* 333 */     int var7 = (var2 * 30 + var4 * 70) / 100;
/* 334 */     return var1 << 24 | var5 << 16 | var6 << 8 | var7;
/*     */   }
/*     */ 
/*     */   
/*     */   public static void func_177055_a(String name, int textureId, int mipmapLevels, int width, int height) {
/* 339 */     bindTexture(textureId);
/* 340 */     GL11.glPixelStorei(3333, 1);
/* 341 */     GL11.glPixelStorei(3317, 1);
/*     */     
/* 343 */     for (int var5 = 0; var5 <= mipmapLevels; var5++) {
/*     */       
/* 345 */       File var6 = new File(String.valueOf(name) + "_" + var5 + ".png");
/* 346 */       int var7 = width >> var5;
/* 347 */       int var8 = height >> var5;
/* 348 */       int var9 = var7 * var8;
/* 349 */       IntBuffer var10 = BufferUtils.createIntBuffer(var9);
/* 350 */       int[] var11 = new int[var9];
/* 351 */       GL11.glGetTexImage(3553, var5, 32993, 33639, var10);
/* 352 */       var10.get(var11);
/* 353 */       BufferedImage var12 = new BufferedImage(var7, var8, 2);
/* 354 */       var12.setRGB(0, 0, var7, var8, var11, 0, var7);
/*     */ 
/*     */       
/*     */       try {
/* 358 */         ImageIO.write(var12, "png", var6);
/* 359 */         logger.debug("Exported png to: {}", new Object[] { var6.getAbsolutePath() });
/*     */       }
/* 361 */       catch (Exception var14) {
/*     */         
/* 363 */         logger.debug("Unable to write: ", var14);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static void func_147953_a(int[] p_147953_0_, int p_147953_1_, int p_147953_2_) {
/* 370 */     int[] var3 = new int[p_147953_1_];
/* 371 */     int var4 = p_147953_2_ / 2;
/*     */     
/* 373 */     for (int var5 = 0; var5 < var4; var5++) {
/*     */       
/* 375 */       System.arraycopy(p_147953_0_, var5 * p_147953_1_, var3, 0, p_147953_1_);
/* 376 */       System.arraycopy(p_147953_0_, (p_147953_2_ - 1 - var5) * p_147953_1_, p_147953_0_, var5 * p_147953_1_, p_147953_1_);
/* 377 */       System.arraycopy(var3, 0, p_147953_0_, (p_147953_2_ - 1 - var5) * p_147953_1_, p_147953_1_);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   static {
/* 383 */     int var0 = -16777216;
/* 384 */     int var1 = -524040;
/* 385 */     int[] var2 = { -524040, -524040, -524040, -524040, -524040, -524040, -524040, -524040 };
/* 386 */     int[] var3 = { -16777216, -16777216, -16777216, -16777216, -16777216, -16777216, -16777216, -16777216 };
/* 387 */     int var4 = var2.length;
/*     */     
/* 389 */     for (int var5 = 0; var5 < 16; var5++) {
/*     */       
/* 391 */       System.arraycopy((var5 < var4) ? var2 : var3, 0, missingTextureData, 16 * var5, var4);
/* 392 */       System.arraycopy((var5 < var4) ? var3 : var2, 0, missingTextureData, 16 * var5 + var4, var4);
/*     */     } 
/*     */     
/* 395 */     missingTexture.updateDynamicTexture();
/* 396 */   } private static final int[] field_147957_g = new int[4];
/*     */   private static final String __OBFID = "CL_00001067";
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\renderer\texture\TextureUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */