/*     */ package optifine;
/*     */ 
/*     */ import java.awt.Dimension;
/*     */ import java.nio.IntBuffer;
/*     */ import java.util.ArrayList;
/*     */ import net.minecraft.client.renderer.GLAllocation;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Mipmaps
/*     */ {
/*     */   private final String iconName;
/*     */   private final int width;
/*     */   private final int height;
/*     */   private final int[] data;
/*     */   private final boolean direct;
/*     */   private int[][] mipmapDatas;
/*     */   private IntBuffer[] mipmapBuffers;
/*     */   private Dimension[] mipmapDimensions;
/*     */   
/*     */   public Mipmaps(String iconName, int width, int height, int[] data, boolean direct) {
/*  23 */     this.iconName = iconName;
/*  24 */     this.width = width;
/*  25 */     this.height = height;
/*  26 */     this.data = data;
/*  27 */     this.direct = direct;
/*  28 */     this.mipmapDimensions = makeMipmapDimensions(width, height, iconName);
/*  29 */     this.mipmapDatas = generateMipMapData(data, width, height, this.mipmapDimensions);
/*     */     
/*  31 */     if (direct)
/*     */     {
/*  33 */       this.mipmapBuffers = makeMipmapBuffers(this.mipmapDimensions, this.mipmapDatas);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public static Dimension[] makeMipmapDimensions(int width, int height, String iconName) {
/*  39 */     int texWidth = TextureUtils.ceilPowerOfTwo(width);
/*  40 */     int texHeight = TextureUtils.ceilPowerOfTwo(height);
/*     */     
/*  42 */     if (texWidth == width && texHeight == height) {
/*     */       
/*  44 */       ArrayList<Dimension> listDims = new ArrayList();
/*  45 */       int mipWidth = texWidth;
/*  46 */       int mipHeight = texHeight;
/*     */ 
/*     */       
/*     */       while (true) {
/*  50 */         mipWidth /= 2;
/*  51 */         mipHeight /= 2;
/*     */         
/*  53 */         if (mipWidth <= 0 && mipHeight <= 0) {
/*     */           
/*  55 */           Dimension[] mipmapDimensions1 = (Dimension[])listDims.toArray((Object[])new Dimension[listDims.size()]);
/*  56 */           return mipmapDimensions1;
/*     */         } 
/*     */         
/*  59 */         if (mipWidth <= 0)
/*     */         {
/*  61 */           mipWidth = 1;
/*     */         }
/*     */         
/*  64 */         if (mipHeight <= 0)
/*     */         {
/*  66 */           mipHeight = 1;
/*     */         }
/*     */         
/*  69 */         int mipmapDimensions = mipWidth * mipHeight * 4;
/*  70 */         Dimension dim = new Dimension(mipWidth, mipHeight);
/*  71 */         listDims.add(dim);
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/*  76 */     Config.warn("Mipmaps not possible (power of 2 dimensions needed), texture: " + iconName + ", dim: " + width + "x" + height);
/*  77 */     return new Dimension[0];
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static int[][] generateMipMapData(int[] data, int width, int height, Dimension[] mipmapDimensions) {
/*  83 */     int[] parMipData = data;
/*  84 */     int parWidth = width;
/*  85 */     boolean scale = true;
/*  86 */     int[][] mipmapDatas = new int[mipmapDimensions.length][];
/*     */     
/*  88 */     for (int i = 0; i < mipmapDimensions.length; i++) {
/*     */       
/*  90 */       Dimension dim = mipmapDimensions[i];
/*  91 */       int mipWidth = dim.width;
/*  92 */       int mipHeight = dim.height;
/*  93 */       int[] mipData = new int[mipWidth * mipHeight];
/*  94 */       mipmapDatas[i] = mipData;
/*  95 */       int level = i + 1;
/*     */       
/*  97 */       if (scale)
/*     */       {
/*  99 */         for (int mipX = 0; mipX < mipWidth; mipX++) {
/*     */           
/* 101 */           for (int mipY = 0; mipY < mipHeight; mipY++) {
/*     */             
/* 103 */             int p1 = parMipData[mipX * 2 + 0 + (mipY * 2 + 0) * parWidth];
/* 104 */             int p2 = parMipData[mipX * 2 + 1 + (mipY * 2 + 0) * parWidth];
/* 105 */             int p3 = parMipData[mipX * 2 + 1 + (mipY * 2 + 1) * parWidth];
/* 106 */             int p4 = parMipData[mipX * 2 + 0 + (mipY * 2 + 1) * parWidth];
/* 107 */             int pixel = alphaBlend(p1, p2, p3, p4);
/* 108 */             mipData[mipX + mipY * mipWidth] = pixel;
/*     */           } 
/*     */         } 
/*     */       }
/*     */       
/* 113 */       parMipData = mipData;
/* 114 */       parWidth = mipWidth;
/*     */       
/* 116 */       if (mipWidth <= 1 || mipHeight <= 1)
/*     */       {
/* 118 */         scale = false;
/*     */       }
/*     */     } 
/*     */     
/* 122 */     return mipmapDatas;
/*     */   }
/*     */ 
/*     */   
/*     */   public static int alphaBlend(int c1, int c2, int c3, int c4) {
/* 127 */     int cx1 = alphaBlend(c1, c2);
/* 128 */     int cx2 = alphaBlend(c3, c4);
/* 129 */     int cx = alphaBlend(cx1, cx2);
/* 130 */     return cx;
/*     */   }
/*     */ 
/*     */   
/*     */   private static int alphaBlend(int c1, int c2) {
/* 135 */     int a1 = (c1 & 0xFF000000) >> 24 & 0xFF;
/* 136 */     int a2 = (c2 & 0xFF000000) >> 24 & 0xFF;
/* 137 */     int ax = (a1 + a2) / 2;
/*     */     
/* 139 */     if (a1 == 0 && a2 == 0) {
/*     */       
/* 141 */       a1 = 1;
/* 142 */       a2 = 1;
/*     */     }
/*     */     else {
/*     */       
/* 146 */       if (a1 == 0) {
/*     */         
/* 148 */         c1 = c2;
/* 149 */         ax /= 2;
/*     */       } 
/*     */       
/* 152 */       if (a2 == 0) {
/*     */         
/* 154 */         c2 = c1;
/* 155 */         ax /= 2;
/*     */       } 
/*     */     } 
/*     */     
/* 159 */     int r1 = (c1 >> 16 & 0xFF) * a1;
/* 160 */     int g1 = (c1 >> 8 & 0xFF) * a1;
/* 161 */     int b1 = (c1 & 0xFF) * a1;
/* 162 */     int r2 = (c2 >> 16 & 0xFF) * a2;
/* 163 */     int g2 = (c2 >> 8 & 0xFF) * a2;
/* 164 */     int b2 = (c2 & 0xFF) * a2;
/* 165 */     int rx = (r1 + r2) / (a1 + a2);
/* 166 */     int gx = (g1 + g2) / (a1 + a2);
/* 167 */     int bx = (b1 + b2) / (a1 + a2);
/* 168 */     return ax << 24 | rx << 16 | gx << 8 | bx;
/*     */   }
/*     */ 
/*     */   
/*     */   private int averageColor(int i, int j) {
/* 173 */     int k = (i & 0xFF000000) >> 24 & 0xFF;
/* 174 */     int l = (j & 0xFF000000) >> 24 & 0xFF;
/* 175 */     return (k + l >> 1 << 24) + ((i & 0xFEFEFE) + (j & 0xFEFEFE) >> 1);
/*     */   }
/*     */ 
/*     */   
/*     */   public static IntBuffer[] makeMipmapBuffers(Dimension[] mipmapDimensions, int[][] mipmapDatas) {
/* 180 */     if (mipmapDimensions == null)
/*     */     {
/* 182 */       return null;
/*     */     }
/*     */ 
/*     */     
/* 186 */     IntBuffer[] mipmapBuffers = new IntBuffer[mipmapDimensions.length];
/*     */     
/* 188 */     for (int i = 0; i < mipmapDimensions.length; i++) {
/*     */       
/* 190 */       Dimension dim = mipmapDimensions[i];
/* 191 */       int bufLen = dim.width * dim.height;
/* 192 */       IntBuffer buf = GLAllocation.createDirectIntBuffer(bufLen);
/* 193 */       int[] data = mipmapDatas[i];
/* 194 */       buf.clear();
/* 195 */       buf.put(data);
/* 196 */       buf.clear();
/* 197 */       mipmapBuffers[i] = buf;
/*     */     } 
/*     */     
/* 200 */     return mipmapBuffers;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static void allocateMipmapTextures(int width, int height, String name) {
/* 206 */     Dimension[] dims = makeMipmapDimensions(width, height, name);
/*     */     
/* 208 */     for (int i = 0; i < dims.length; i++) {
/*     */       
/* 210 */       Dimension dim = dims[i];
/* 211 */       int mipWidth = dim.width;
/* 212 */       int mipHeight = dim.height;
/* 213 */       int level = i + 1;
/* 214 */       GL11.glTexImage2D(3553, level, 6408, mipWidth, mipHeight, 0, 32993, 33639, null);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\optifine\Mipmaps.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */