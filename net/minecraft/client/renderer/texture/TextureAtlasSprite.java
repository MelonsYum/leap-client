/*     */ package net.minecraft.client.renderer.texture;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import java.awt.image.BufferedImage;
/*     */ import java.io.IOException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.concurrent.Callable;
/*     */ import net.minecraft.client.resources.IResourceManager;
/*     */ import net.minecraft.client.resources.data.AnimationFrame;
/*     */ import net.minecraft.client.resources.data.AnimationMetadataSection;
/*     */ import net.minecraft.crash.CrashReport;
/*     */ import net.minecraft.crash.CrashReportCategory;
/*     */ import net.minecraft.util.ReportedException;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import optifine.Config;
/*     */ import optifine.TextureUtils;
/*     */ import shadersmod.client.ShadersTex;
/*     */ 
/*     */ public class TextureAtlasSprite
/*     */ {
/*     */   private final String iconName;
/*  24 */   protected List framesTextureData = Lists.newArrayList();
/*     */   protected int[][] field_176605_b;
/*     */   private AnimationMetadataSection animationMetadata;
/*     */   protected boolean rotated;
/*     */   protected int originX;
/*     */   protected int originY;
/*     */   protected int width;
/*     */   protected int height;
/*     */   private float minU;
/*     */   private float maxU;
/*     */   private float minV;
/*     */   private float maxV;
/*     */   protected int frameCounter;
/*     */   protected int tickCounter;
/*  38 */   private static String field_176607_p = "builtin/clock";
/*  39 */   private static String field_176606_q = "builtin/compass";
/*     */   private static final String __OBFID = "CL_00001062";
/*  41 */   private int indexInMap = -1;
/*     */   public float baseU;
/*     */   public float baseV;
/*     */   public int sheetWidth;
/*     */   public int sheetHeight;
/*  46 */   public int glSpriteTextureId = -1;
/*  47 */   public TextureAtlasSprite spriteSingle = null;
/*     */   public boolean isSpriteSingle = false;
/*  49 */   public int mipmapLevels = 0;
/*     */ 
/*     */   
/*     */   private TextureAtlasSprite(TextureAtlasSprite parent) {
/*  53 */     this.iconName = parent.iconName;
/*  54 */     this.isSpriteSingle = true;
/*     */   }
/*     */ 
/*     */   
/*     */   protected TextureAtlasSprite(String p_i1282_1_) {
/*  59 */     this.iconName = p_i1282_1_;
/*     */     
/*  61 */     if (Config.isMultiTexture())
/*     */     {
/*  63 */       this.spriteSingle = new TextureAtlasSprite(this);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   protected static TextureAtlasSprite func_176604_a(ResourceLocation p_176604_0_) {
/*  69 */     String var1 = p_176604_0_.toString();
/*  70 */     return field_176607_p.equals(var1) ? new TextureClock(var1) : (field_176606_q.equals(var1) ? new TextureCompass(var1) : new TextureAtlasSprite(var1));
/*     */   }
/*     */ 
/*     */   
/*     */   public static void func_176602_a(String p_176602_0_) {
/*  75 */     field_176607_p = p_176602_0_;
/*     */   }
/*     */ 
/*     */   
/*     */   public static void func_176603_b(String p_176603_0_) {
/*  80 */     field_176606_q = p_176603_0_;
/*     */   }
/*     */ 
/*     */   
/*     */   public void initSprite(int p_110971_1_, int p_110971_2_, int p_110971_3_, int p_110971_4_, boolean p_110971_5_) {
/*  85 */     this.originX = p_110971_3_;
/*  86 */     this.originY = p_110971_4_;
/*  87 */     this.rotated = p_110971_5_;
/*  88 */     float var6 = (float)(0.009999999776482582D / p_110971_1_);
/*  89 */     float var7 = (float)(0.009999999776482582D / p_110971_2_);
/*  90 */     this.minU = p_110971_3_ / (float)p_110971_1_ + var6;
/*  91 */     this.maxU = (p_110971_3_ + this.width) / (float)p_110971_1_ - var6;
/*  92 */     this.minV = p_110971_4_ / p_110971_2_ + var7;
/*  93 */     this.maxV = (p_110971_4_ + this.height) / p_110971_2_ - var7;
/*  94 */     this.baseU = Math.min(this.minU, this.maxU);
/*  95 */     this.baseV = Math.min(this.minV, this.maxV);
/*     */     
/*  97 */     if (this.spriteSingle != null)
/*     */     {
/*  99 */       this.spriteSingle.initSprite(this.width, this.height, 0, 0, false);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void copyFrom(TextureAtlasSprite p_94217_1_) {
/* 105 */     this.originX = p_94217_1_.originX;
/* 106 */     this.originY = p_94217_1_.originY;
/* 107 */     this.width = p_94217_1_.width;
/* 108 */     this.height = p_94217_1_.height;
/* 109 */     this.rotated = p_94217_1_.rotated;
/* 110 */     this.minU = p_94217_1_.minU;
/* 111 */     this.maxU = p_94217_1_.maxU;
/* 112 */     this.minV = p_94217_1_.minV;
/* 113 */     this.maxV = p_94217_1_.maxV;
/*     */     
/* 115 */     if (this.spriteSingle != null)
/*     */     {
/* 117 */       this.spriteSingle.initSprite(this.width, this.height, 0, 0, false);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getOriginX() {
/* 126 */     return this.originX;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getOriginY() {
/* 134 */     return this.originY;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getIconWidth() {
/* 142 */     return this.width;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getIconHeight() {
/* 150 */     return this.height;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getMinU() {
/* 158 */     return this.minU;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getMaxU() {
/* 166 */     return this.maxU;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getInterpolatedU(double p_94214_1_) {
/* 174 */     float var3 = this.maxU - this.minU;
/* 175 */     return this.minU + var3 * (float)p_94214_1_ / 16.0F;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getMinV() {
/* 183 */     return this.minV;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getMaxV() {
/* 191 */     return this.maxV;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getInterpolatedV(double p_94207_1_) {
/* 199 */     float var3 = this.maxV - this.minV;
/* 200 */     return this.minV + var3 * (float)p_94207_1_ / 16.0F;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getIconName() {
/* 205 */     return this.iconName;
/*     */   }
/*     */ 
/*     */   
/*     */   public void updateAnimation() {
/* 210 */     this.tickCounter++;
/*     */     
/* 212 */     if (this.tickCounter >= this.animationMetadata.getFrameTimeSingle(this.frameCounter)) {
/*     */       
/* 214 */       int var1 = this.animationMetadata.getFrameIndex(this.frameCounter);
/* 215 */       int var2 = (this.animationMetadata.getFrameCount() == 0) ? this.framesTextureData.size() : this.animationMetadata.getFrameCount();
/* 216 */       this.frameCounter = (this.frameCounter + 1) % var2;
/* 217 */       this.tickCounter = 0;
/* 218 */       int var3 = this.animationMetadata.getFrameIndex(this.frameCounter);
/* 219 */       boolean texBlur = false;
/* 220 */       boolean texClamp = this.isSpriteSingle;
/*     */       
/* 222 */       if (var1 != var3 && var3 >= 0 && var3 < this.framesTextureData.size())
/*     */       {
/* 224 */         if (Config.isShaders())
/*     */         {
/* 226 */           ShadersTex.uploadTexSub(this.framesTextureData.get(var3), this.width, this.height, this.originX, this.originY, texBlur, texClamp);
/*     */         }
/*     */         else
/*     */         {
/* 230 */           TextureUtil.uploadTextureMipmap(this.framesTextureData.get(var3), this.width, this.height, this.originX, this.originY, texBlur, texClamp);
/*     */         }
/*     */       
/*     */       }
/* 234 */     } else if (this.animationMetadata.func_177219_e()) {
/*     */       
/* 236 */       func_180599_n();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void func_180599_n() {
/* 242 */     double var1 = 1.0D - this.tickCounter / this.animationMetadata.getFrameTimeSingle(this.frameCounter);
/* 243 */     int var3 = this.animationMetadata.getFrameIndex(this.frameCounter);
/* 244 */     int var4 = (this.animationMetadata.getFrameCount() == 0) ? this.framesTextureData.size() : this.animationMetadata.getFrameCount();
/* 245 */     int var5 = this.animationMetadata.getFrameIndex((this.frameCounter + 1) % var4);
/*     */     
/* 247 */     if (var3 != var5 && var5 >= 0 && var5 < this.framesTextureData.size()) {
/*     */       
/* 249 */       int[][] var6 = this.framesTextureData.get(var3);
/* 250 */       int[][] var7 = this.framesTextureData.get(var5);
/*     */       
/* 252 */       if (this.field_176605_b == null || this.field_176605_b.length != var6.length)
/*     */       {
/* 254 */         this.field_176605_b = new int[var6.length][];
/*     */       }
/*     */       
/* 257 */       for (int var8 = 0; var8 < var6.length; var8++) {
/*     */         
/* 259 */         if (this.field_176605_b[var8] == null)
/*     */         {
/* 261 */           this.field_176605_b[var8] = new int[(var6[var8]).length];
/*     */         }
/*     */         
/* 264 */         if (var8 < var7.length && (var7[var8]).length == (var6[var8]).length)
/*     */         {
/* 266 */           for (int var9 = 0; var9 < (var6[var8]).length; var9++) {
/*     */             
/* 268 */             int var10 = var6[var8][var9];
/* 269 */             int var11 = var7[var8][var9];
/* 270 */             int var12 = (int)(((var10 & 0xFF0000) >> 16) * var1 + ((var11 & 0xFF0000) >> 16) * (1.0D - var1));
/* 271 */             int var13 = (int)(((var10 & 0xFF00) >> 8) * var1 + ((var11 & 0xFF00) >> 8) * (1.0D - var1));
/* 272 */             int var14 = (int)((var10 & 0xFF) * var1 + (var11 & 0xFF) * (1.0D - var1));
/* 273 */             this.field_176605_b[var8][var9] = var10 & 0xFF000000 | var12 << 16 | var13 << 8 | var14;
/*     */           } 
/*     */         }
/*     */       } 
/*     */       
/* 278 */       TextureUtil.uploadTextureMipmap(this.field_176605_b, this.width, this.height, this.originX, this.originY, false, false);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public int[][] getFrameTextureData(int p_147965_1_) {
/* 284 */     return this.framesTextureData.get(p_147965_1_);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getFrameCount() {
/* 289 */     return this.framesTextureData.size();
/*     */   }
/*     */ 
/*     */   
/*     */   public void setIconWidth(int p_110966_1_) {
/* 294 */     this.width = p_110966_1_;
/*     */     
/* 296 */     if (this.spriteSingle != null)
/*     */     {
/* 298 */       this.spriteSingle.setIconWidth(this.width);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void setIconHeight(int p_110969_1_) {
/* 304 */     this.height = p_110969_1_;
/*     */     
/* 306 */     if (this.spriteSingle != null)
/*     */     {
/* 308 */       this.spriteSingle.setIconHeight(this.height);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_180598_a(BufferedImage[] p_180598_1_, AnimationMetadataSection p_180598_2_) throws IOException {
/* 314 */     resetSprite();
/* 315 */     int var3 = p_180598_1_[0].getWidth();
/* 316 */     int var4 = p_180598_1_[0].getHeight();
/* 317 */     this.width = var3;
/* 318 */     this.height = var4;
/* 319 */     int[][] var5 = new int[p_180598_1_.length][];
/*     */     
/*     */     int var6;
/* 322 */     for (var6 = 0; var6 < p_180598_1_.length; var6++) {
/*     */       
/* 324 */       BufferedImage i = p_180598_1_[var6];
/*     */       
/* 326 */       if (i != null) {
/*     */         
/* 328 */         if (var6 > 0 && (i.getWidth() != var3 >> var6 || i.getHeight() != var4 >> var6))
/*     */         {
/* 330 */           throw new RuntimeException(String.format("Unable to load miplevel: %d, image is size: %dx%d, expected %dx%d", new Object[] { Integer.valueOf(var6), Integer.valueOf(i.getWidth()), Integer.valueOf(i.getHeight()), Integer.valueOf(var3 >> var6), Integer.valueOf(var4 >> var6) }));
/*     */         }
/*     */         
/* 333 */         var5[var6] = new int[i.getWidth() * i.getHeight()];
/* 334 */         i.getRGB(0, 0, i.getWidth(), i.getHeight(), var5[var6], 0, i.getWidth());
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 341 */     if (p_180598_2_ == null) {
/*     */       
/* 343 */       if (var4 != var3)
/*     */       {
/* 345 */         throw new RuntimeException("broken aspect ratio and not an animation");
/*     */       }
/*     */       
/* 348 */       this.framesTextureData.add(var5);
/*     */     }
/*     */     else {
/*     */       
/* 352 */       var6 = var4 / var3;
/* 353 */       int i = var3;
/* 354 */       int datas = var3;
/* 355 */       this.height = this.width;
/*     */       
/* 357 */       if (p_180598_2_.getFrameCount() > 0) {
/*     */         
/* 359 */         Iterator<Integer> data = p_180598_2_.getFrameIndexSet().iterator();
/*     */         
/* 361 */         while (data.hasNext()) {
/*     */           
/* 363 */           int di = ((Integer)data.next()).intValue();
/*     */           
/* 365 */           if (di >= var6)
/*     */           {
/* 367 */             throw new RuntimeException("invalid frameindex " + di);
/*     */           }
/*     */           
/* 370 */           allocateFrameTextureData(di);
/* 371 */           this.framesTextureData.set(di, getFrameTextureData(var5, i, datas, di));
/*     */         } 
/*     */         
/* 374 */         this.animationMetadata = p_180598_2_;
/*     */       }
/*     */       else {
/*     */         
/* 378 */         ArrayList<AnimationFrame> var13 = Lists.newArrayList();
/*     */         
/* 380 */         for (int di = 0; di < var6; di++) {
/*     */           
/* 382 */           this.framesTextureData.add(getFrameTextureData(var5, i, datas, di));
/* 383 */           var13.add(new AnimationFrame(di, -1));
/*     */         } 
/*     */         
/* 386 */         this.animationMetadata = new AnimationMetadataSection(var13, this.width, this.height, p_180598_2_.getFrameTime(), p_180598_2_.func_177219_e());
/*     */       } 
/*     */     } 
/*     */     
/* 390 */     for (int var11 = 0; var11 < this.framesTextureData.size(); var11++) {
/*     */       
/* 392 */       int[][] var12 = this.framesTextureData.get(var11);
/*     */       
/* 394 */       if (var12 != null && !this.iconName.startsWith("minecraft:blocks/leaves_"))
/*     */       {
/* 396 */         for (int di = 0; di < var12.length; di++) {
/*     */           
/* 398 */           int[] var14 = var12[di];
/* 399 */           fixTransparentColor(var14);
/*     */         } 
/*     */       }
/*     */     } 
/*     */     
/* 404 */     if (this.spriteSingle != null)
/*     */     {
/* 406 */       this.spriteSingle.func_180598_a(p_180598_1_, p_180598_2_);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void generateMipmaps(int p_147963_1_) {
/* 412 */     ArrayList<int[][]> var2 = Lists.newArrayList();
/*     */     
/* 414 */     for (int var3 = 0; var3 < this.framesTextureData.size(); var3++) {
/*     */       
/* 416 */       final int[][] var4 = this.framesTextureData.get(var3);
/*     */       
/* 418 */       if (var4 != null) {
/*     */         
/*     */         try {
/*     */           
/* 422 */           var2.add(TextureUtil.generateMipmapData(p_147963_1_, this.width, var4));
/*     */         }
/* 424 */         catch (Throwable var8) {
/*     */           
/* 426 */           CrashReport var6 = CrashReport.makeCrashReport(var8, "Generating mipmaps for frame");
/* 427 */           CrashReportCategory var7 = var6.makeCategory("Frame being iterated");
/* 428 */           var7.addCrashSection("Frame index", Integer.valueOf(var3));
/* 429 */           var7.addCrashSectionCallable("Frame sizes", new Callable()
/*     */               {
/*     */                 private static final String __OBFID = "CL_00001063";
/*     */                 
/*     */                 public String call() {
/* 434 */                   StringBuilder var1 = new StringBuilder();
/* 435 */                   int[][] var2 = var4;
/* 436 */                   int var3 = var2.length;
/*     */                   
/* 438 */                   for (int var4x = 0; var4x < var3; var4x++) {
/*     */                     
/* 440 */                     int[] var5 = var2[var4x];
/*     */                     
/* 442 */                     if (var1.length() > 0)
/*     */                     {
/* 444 */                       var1.append(", ");
/*     */                     }
/*     */                     
/* 447 */                     var1.append((var5 == null) ? "null" : Integer.valueOf(var5.length));
/*     */                   } 
/*     */                   
/* 450 */                   return var1.toString();
/*     */                 }
/*     */               });
/* 453 */           throw new ReportedException(var6);
/*     */         } 
/*     */       }
/*     */     } 
/*     */     
/* 458 */     setFramesTextureData(var2);
/*     */     
/* 460 */     if (this.spriteSingle != null)
/*     */     {
/* 462 */       this.spriteSingle.generateMipmaps(p_147963_1_);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private void allocateFrameTextureData(int p_130099_1_) {
/* 468 */     if (this.framesTextureData.size() <= p_130099_1_)
/*     */     {
/* 470 */       for (int var2 = this.framesTextureData.size(); var2 <= p_130099_1_; var2++)
/*     */       {
/* 472 */         this.framesTextureData.add(null);
/*     */       }
/*     */     }
/*     */     
/* 476 */     if (this.spriteSingle != null)
/*     */     {
/* 478 */       this.spriteSingle.allocateFrameTextureData(p_130099_1_);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private static int[][] getFrameTextureData(int[][] p_147962_0_, int p_147962_1_, int p_147962_2_, int p_147962_3_) {
/* 484 */     int[][] var4 = new int[p_147962_0_.length][];
/*     */     
/* 486 */     for (int var5 = 0; var5 < p_147962_0_.length; var5++) {
/*     */       
/* 488 */       int[] var6 = p_147962_0_[var5];
/*     */       
/* 490 */       if (var6 != null) {
/*     */         
/* 492 */         var4[var5] = new int[(p_147962_1_ >> var5) * (p_147962_2_ >> var5)];
/* 493 */         System.arraycopy(var6, p_147962_3_ * (var4[var5]).length, var4[var5], 0, (var4[var5]).length);
/*     */       } 
/*     */     } 
/*     */     
/* 497 */     return var4;
/*     */   }
/*     */ 
/*     */   
/*     */   public void clearFramesTextureData() {
/* 502 */     this.framesTextureData.clear();
/*     */     
/* 504 */     if (this.spriteSingle != null)
/*     */     {
/* 506 */       this.spriteSingle.clearFramesTextureData();
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hasAnimationMetadata() {
/* 512 */     return (this.animationMetadata != null);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setFramesTextureData(List p_110968_1_) {
/* 517 */     this.framesTextureData = p_110968_1_;
/*     */     
/* 519 */     if (this.spriteSingle != null)
/*     */     {
/* 521 */       this.spriteSingle.setFramesTextureData(p_110968_1_);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private void resetSprite() {
/* 527 */     this.animationMetadata = null;
/* 528 */     setFramesTextureData(Lists.newArrayList());
/* 529 */     this.frameCounter = 0;
/* 530 */     this.tickCounter = 0;
/*     */     
/* 532 */     if (this.spriteSingle != null)
/*     */     {
/* 534 */       this.spriteSingle.resetSprite();
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 540 */     return "TextureAtlasSprite{name='" + this.iconName + '\'' + ", frameCount=" + this.framesTextureData.size() + ", rotated=" + this.rotated + ", x=" + this.originX + ", y=" + this.originY + ", height=" + this.height + ", width=" + this.width + ", u0=" + this.minU + ", u1=" + this.maxU + ", v0=" + this.minV + ", v1=" + this.maxV + '}';
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hasCustomLoader(IResourceManager manager, ResourceLocation location) {
/* 545 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean load(IResourceManager manager, ResourceLocation location) {
/* 550 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getIndexInMap() {
/* 555 */     return this.indexInMap;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setIndexInMap(int indexInMap) {
/* 560 */     this.indexInMap = indexInMap;
/*     */   }
/*     */ 
/*     */   
/*     */   private void fixTransparentColor(int[] data) {
/* 565 */     if (data != null) {
/*     */       
/* 567 */       long redSum = 0L;
/* 568 */       long greenSum = 0L;
/* 569 */       long blueSum = 0L;
/* 570 */       long count = 0L;
/*     */ 
/*     */ 
/*     */       
/*     */       int redAvg;
/*     */ 
/*     */ 
/*     */       
/* 578 */       for (redAvg = 0; redAvg < data.length; redAvg++) {
/*     */         
/* 580 */         int greenAvg = data[redAvg];
/* 581 */         int blueAvg = greenAvg >> 24 & 0xFF;
/*     */         
/* 583 */         if (blueAvg >= 16) {
/*     */           
/* 585 */           int colAvg = greenAvg >> 16 & 0xFF;
/* 586 */           int i = greenAvg >> 8 & 0xFF;
/* 587 */           int col = greenAvg & 0xFF;
/* 588 */           redSum += colAvg;
/* 589 */           greenSum += i;
/* 590 */           blueSum += col;
/* 591 */           count++;
/*     */         } 
/*     */       } 
/*     */       
/* 595 */       if (count > 0L) {
/*     */         
/* 597 */         redAvg = (int)(redSum / count);
/* 598 */         int greenAvg = (int)(greenSum / count);
/* 599 */         int blueAvg = (int)(blueSum / count);
/* 600 */         int colAvg = redAvg << 16 | greenAvg << 8 | blueAvg;
/*     */         
/* 602 */         for (int i = 0; i < data.length; i++) {
/*     */           
/* 604 */           int col = data[i];
/* 605 */           int alpha = col >> 24 & 0xFF;
/*     */           
/* 607 */           if (alpha <= 16)
/*     */           {
/* 609 */             data[i] = colAvg;
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public double getSpriteU16(float atlasU) {
/* 618 */     float dU = this.maxU - this.minU;
/* 619 */     return ((atlasU - this.minU) / dU * 16.0F);
/*     */   }
/*     */ 
/*     */   
/*     */   public double getSpriteV16(float atlasV) {
/* 624 */     float dV = this.maxV - this.minV;
/* 625 */     return ((atlasV - this.minV) / dV * 16.0F);
/*     */   }
/*     */ 
/*     */   
/*     */   public void bindSpriteTexture() {
/* 630 */     if (this.glSpriteTextureId < 0) {
/*     */       
/* 632 */       this.glSpriteTextureId = TextureUtil.glGenTextures();
/* 633 */       TextureUtil.func_180600_a(this.glSpriteTextureId, this.mipmapLevels, this.width, this.height);
/* 634 */       TextureUtils.applyAnisotropicLevel();
/*     */     } 
/*     */     
/* 637 */     TextureUtils.bindTexture(this.glSpriteTextureId);
/*     */   }
/*     */ 
/*     */   
/*     */   public void deleteSpriteTexture() {
/* 642 */     if (this.glSpriteTextureId >= 0) {
/*     */       
/* 644 */       TextureUtil.deleteTexture(this.glSpriteTextureId);
/* 645 */       this.glSpriteTextureId = -1;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public float toSingleU(float u) {
/* 651 */     u -= this.baseU;
/* 652 */     float ku = this.sheetWidth / this.width;
/* 653 */     u *= ku;
/* 654 */     return u;
/*     */   }
/*     */ 
/*     */   
/*     */   public float toSingleV(float v) {
/* 659 */     v -= this.baseV;
/* 660 */     float kv = this.sheetHeight / this.height;
/* 661 */     v *= kv;
/* 662 */     return v;
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\renderer\texture\TextureAtlasSprite.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */