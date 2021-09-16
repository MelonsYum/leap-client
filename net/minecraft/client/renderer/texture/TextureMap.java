/*     */ package net.minecraft.client.renderer.texture;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import com.google.common.collect.Maps;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.image.BufferedImage;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import java.util.TreeSet;
/*     */ import java.util.concurrent.Callable;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.renderer.GlStateManager;
/*     */ import net.minecraft.client.renderer.StitcherException;
/*     */ import net.minecraft.client.resources.IResource;
/*     */ import net.minecraft.client.resources.IResourceManager;
/*     */ import net.minecraft.client.resources.data.AnimationMetadataSection;
/*     */ import net.minecraft.client.resources.data.TextureMetadataSection;
/*     */ import net.minecraft.crash.CrashReport;
/*     */ import net.minecraft.crash.CrashReportCategory;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.util.ReportedException;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import optifine.Config;
/*     */ import optifine.ConnectedTextures;
/*     */ import optifine.CustomItems;
/*     */ import optifine.Reflector;
/*     */ import optifine.ReflectorForge;
/*     */ import optifine.TextureUtils;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ import shadersmod.client.ShadersTex;
/*     */ 
/*     */ public class TextureMap
/*     */   extends AbstractTexture
/*     */   implements ITickableTextureObject
/*     */ {
/*  42 */   private static final Logger logger = LogManager.getLogger();
/*  43 */   public static final ResourceLocation field_174945_f = new ResourceLocation("missingno");
/*  44 */   public static final ResourceLocation locationBlocksTexture = new ResourceLocation("textures/atlas/blocks.png");
/*     */   private final List listAnimatedSprites;
/*     */   private final Map mapRegisteredSprites;
/*     */   private final Map mapUploadedSprites;
/*     */   private final String basePath;
/*     */   private final IIconCreator field_174946_m;
/*     */   private int mipmapLevels;
/*     */   private final TextureAtlasSprite missingImage;
/*     */   private static final String __OBFID = "CL_00001058";
/*     */   private TextureAtlasSprite[] iconGrid;
/*     */   private int iconGridSize;
/*     */   private int iconGridCountX;
/*     */   private int iconGridCountY;
/*     */   private double iconGridSizeU;
/*     */   private double iconGridSizeV;
/*  59 */   private static final boolean ENABLE_SKIP = Boolean.parseBoolean(System.getProperty("fml.skipFirstTextureLoad", "true"));
/*     */   
/*     */   private boolean skipFirst;
/*     */   public int atlasWidth;
/*     */   public int atlasHeight;
/*     */   
/*     */   public TextureMap(String p_i46099_1_) {
/*  66 */     this(p_i46099_1_, (IIconCreator)null);
/*     */   }
/*     */ 
/*     */   
/*     */   public TextureMap(String p_i46099_1_, boolean skipFirst) {
/*  71 */     this(p_i46099_1_, (IIconCreator)null, skipFirst);
/*     */   }
/*     */ 
/*     */   
/*     */   public TextureMap(String p_i46100_1_, IIconCreator p_i46100_2_) {
/*  76 */     this(p_i46100_1_, p_i46100_2_, false);
/*     */   }
/*     */ 
/*     */   
/*     */   public TextureMap(String p_i46100_1_, IIconCreator p_i46100_2_, boolean skipFirst) {
/*  81 */     this.iconGrid = null;
/*  82 */     this.iconGridSize = -1;
/*  83 */     this.iconGridCountX = -1;
/*  84 */     this.iconGridCountY = -1;
/*  85 */     this.iconGridSizeU = -1.0D;
/*  86 */     this.iconGridSizeV = -1.0D;
/*  87 */     this.skipFirst = false;
/*  88 */     this.atlasWidth = 0;
/*  89 */     this.atlasHeight = 0;
/*  90 */     this.listAnimatedSprites = Lists.newArrayList();
/*  91 */     this.mapRegisteredSprites = Maps.newHashMap();
/*  92 */     this.mapUploadedSprites = Maps.newHashMap();
/*  93 */     this.missingImage = new TextureAtlasSprite("missingno");
/*  94 */     this.basePath = p_i46100_1_;
/*  95 */     this.field_174946_m = p_i46100_2_;
/*  96 */     this.skipFirst = (skipFirst && ENABLE_SKIP);
/*     */   }
/*     */ 
/*     */   
/*     */   private void initMissingImage() {
/* 101 */     int size = getMinSpriteSize();
/* 102 */     int[] var1 = getMissingImageData(size);
/* 103 */     this.missingImage.setIconWidth(size);
/* 104 */     this.missingImage.setIconHeight(size);
/* 105 */     int[][] var2 = new int[this.mipmapLevels + 1][];
/* 106 */     var2[0] = var1;
/* 107 */     this.missingImage.setFramesTextureData(Lists.newArrayList((Object[])new int[][][] { var2 }));
/* 108 */     this.missingImage.setIndexInMap(0);
/*     */   }
/*     */ 
/*     */   
/*     */   public void loadTexture(IResourceManager p_110551_1_) throws IOException {
/* 113 */     ShadersTex.resManager = p_110551_1_;
/*     */     
/* 115 */     if (this.field_174946_m != null)
/*     */     {
/* 117 */       func_174943_a(p_110551_1_, this.field_174946_m);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_174943_a(IResourceManager p_174943_1_, IIconCreator p_174943_2_) {
/* 123 */     this.mapRegisteredSprites.clear();
/* 124 */     p_174943_2_.func_177059_a(this);
/*     */     
/* 126 */     if (this.mipmapLevels >= 4) {
/*     */       
/* 128 */       this.mipmapLevels = detectMaxMipmapLevel(this.mapRegisteredSprites, p_174943_1_);
/* 129 */       Config.log("Mipmap levels: " + this.mipmapLevels);
/*     */     } 
/*     */     
/* 132 */     initMissingImage();
/* 133 */     deleteGlTexture();
/* 134 */     loadTextureAtlas(p_174943_1_);
/*     */   }
/*     */ 
/*     */   
/*     */   public void loadTextureAtlas(IResourceManager p_110571_1_) {
/* 139 */     ShadersTex.resManager = p_110571_1_;
/* 140 */     Config.dbg("Multitexture: " + Config.isMultiTexture());
/*     */     
/* 142 */     if (Config.isMultiTexture()) {
/*     */       
/* 144 */       Iterator<TextureAtlasSprite> var2 = this.mapUploadedSprites.values().iterator();
/*     */       
/* 146 */       while (var2.hasNext()) {
/*     */         
/* 148 */         TextureAtlasSprite var3 = var2.next();
/* 149 */         var3.deleteSpriteTexture();
/*     */       } 
/*     */     } 
/*     */     
/* 153 */     ConnectedTextures.updateIcons(this);
/* 154 */     CustomItems.updateIcons(this);
/* 155 */     int var21 = Minecraft.getGLMaximumTextureSize();
/* 156 */     Stitcher var31 = new Stitcher(var21, var21, true, 0, this.mipmapLevels);
/* 157 */     this.mapUploadedSprites.clear();
/* 158 */     this.listAnimatedSprites.clear();
/* 159 */     int var4 = Integer.MAX_VALUE;
/* 160 */     Reflector.callVoid(Reflector.ForgeHooksClient_onTextureStitchedPre, new Object[] { this });
/* 161 */     int minSpriteSize = getMinSpriteSize();
/* 162 */     this.iconGridSize = minSpriteSize;
/* 163 */     int var5 = 1 << this.mipmapLevels;
/* 164 */     Iterator<Map.Entry> var6 = this.mapRegisteredSprites.entrySet().iterator();
/*     */ 
/*     */ 
/*     */     
/* 168 */     while (var6.hasNext() && !this.skipFirst) {
/*     */       
/* 170 */       Map.Entry var25 = var6.next();
/* 171 */       TextureAtlasSprite var26 = (TextureAtlasSprite)var25.getValue();
/* 172 */       ResourceLocation var27 = new ResourceLocation(var26.getIconName());
/* 173 */       ResourceLocation var28 = completeResourceLocation(var27, 0);
/*     */       
/* 175 */       if (var26.hasCustomLoader(p_110571_1_, var27)) {
/*     */         
/* 177 */         if (!var26.load(p_110571_1_, var27)) {
/*     */           
/* 179 */           var4 = Math.min(var4, Math.min(var26.getIconWidth(), var26.getIconHeight()));
/* 180 */           var31.addSprite(var26);
/*     */         } 
/*     */         
/* 183 */         Config.dbg("Custom loader: " + var26);
/*     */         
/*     */         continue;
/*     */       } 
/*     */       
/*     */       try {
/* 189 */         IResource var30 = ShadersTex.loadResource(p_110571_1_, var28);
/* 190 */         BufferedImage[] var311 = new BufferedImage[1 + this.mipmapLevels];
/* 191 */         var311[0] = TextureUtil.func_177053_a(var30.getInputStream());
/*     */         
/* 193 */         if (var311 != null) {
/*     */           
/* 195 */           int sheetHeight = var311[0].getWidth();
/*     */           
/* 197 */           if (sheetHeight < minSpriteSize || this.mipmapLevels > 0) {
/*     */             
/* 199 */             var311[0] = (this.mipmapLevels > 0) ? TextureUtils.scaleToPowerOfTwo(var311[0], minSpriteSize) : TextureUtils.scaleMinTo(var311[0], minSpriteSize);
/* 200 */             int listSprites = var311[0].getWidth();
/*     */             
/* 202 */             if (listSprites != sheetHeight)
/*     */             {
/* 204 */               if (!TextureUtils.isPowerOfTwo(sheetHeight)) {
/*     */                 
/* 206 */                 Config.log("Scaled non power of 2: " + var26.getIconName() + ", " + sheetHeight + " -> " + listSprites);
/*     */               }
/*     */               else {
/*     */                 
/* 210 */                 Config.log("Scaled too small texture: " + var26.getIconName() + ", " + sheetHeight + " -> " + listSprites);
/*     */               } 
/*     */             }
/*     */           } 
/*     */         } 
/*     */         
/* 216 */         TextureMetadataSection sheetHeight1 = (TextureMetadataSection)var30.getMetadata("texture");
/*     */         
/* 218 */         if (sheetHeight1 != null) {
/*     */           
/* 220 */           List listSprites1 = sheetHeight1.getListMipmaps();
/*     */ 
/*     */           
/* 223 */           if (!listSprites1.isEmpty()) {
/*     */             
/* 225 */             int tas = var311[0].getWidth();
/* 226 */             int it = var311[0].getHeight();
/*     */             
/* 228 */             if (MathHelper.roundUpToPowerOfTwo(tas) != tas || MathHelper.roundUpToPowerOfTwo(it) != it)
/*     */             {
/* 230 */               throw new RuntimeException("Unable to load extra miplevels, source-texture is not power of two");
/*     */             }
/*     */           } 
/*     */           
/* 234 */           Iterator<Integer> tas1 = listSprites1.iterator();
/*     */           
/* 236 */           while (tas1.hasNext()) {
/*     */             
/* 238 */             int it = ((Integer)tas1.next()).intValue();
/*     */             
/* 240 */             if (it > 0 && it < var311.length - 1 && var311[it] == null) {
/*     */               
/* 242 */               ResourceLocation ss = completeResourceLocation(var27, it);
/*     */ 
/*     */               
/*     */               try {
/* 246 */                 var311[it] = TextureUtil.func_177053_a(ShadersTex.loadResource(p_110571_1_, ss).getInputStream());
/*     */               }
/* 248 */               catch (IOException var251) {
/*     */                 
/* 250 */                 logger.error("Unable to load miplevel {} from: {}", new Object[] { Integer.valueOf(it), ss, var251 });
/*     */               } 
/*     */             } 
/*     */           } 
/*     */         } 
/*     */         
/* 256 */         AnimationMetadataSection listSprites2 = (AnimationMetadataSection)var30.getMetadata("animation");
/* 257 */         var26.func_180598_a(var311, listSprites2);
/*     */       }
/* 259 */       catch (RuntimeException var261) {
/*     */         
/* 261 */         logger.error("Unable to parse metadata from " + var28, var261);
/* 262 */         ReflectorForge.FMLClientHandler_trackBrokenTexture(var28, var261.getMessage());
/*     */         
/*     */         continue;
/* 265 */       } catch (IOException var271) {
/*     */         
/* 267 */         logger.error("Using missing texture, unable to load " + var28 + ", " + var271.getClass().getName());
/* 268 */         ReflectorForge.FMLClientHandler_trackMissingTexture(var28);
/*     */         
/*     */         continue;
/*     */       } 
/* 272 */       var4 = Math.min(var4, Math.min(var26.getIconWidth(), var26.getIconHeight()));
/* 273 */       int var301 = Math.min(Integer.lowestOneBit(var26.getIconWidth()), Integer.lowestOneBit(var26.getIconHeight()));
/*     */       
/* 275 */       if (var301 < var5) {
/*     */         
/* 277 */         logger.warn("Texture {} with size {}x{} limits mip level from {} to {}", new Object[] { var28, Integer.valueOf(var26.getIconWidth()), Integer.valueOf(var26.getIconHeight()), Integer.valueOf(MathHelper.calculateLogBaseTwo(var5)), Integer.valueOf(MathHelper.calculateLogBaseTwo(var301)) });
/* 278 */         var5 = var301;
/*     */       } 
/*     */       
/* 281 */       var31.addSprite(var26);
/*     */     } 
/*     */ 
/*     */     
/* 285 */     int var252 = Math.min(var4, var5);
/* 286 */     int var262 = MathHelper.calculateLogBaseTwo(var252);
/*     */     
/* 288 */     if (var262 < 0)
/*     */     {
/* 290 */       var262 = 0;
/*     */     }
/*     */     
/* 293 */     if (var262 < this.mipmapLevels) {
/*     */       
/* 295 */       logger.info("{}: dropping miplevel from {} to {}, because of minimum power of two: {}", new Object[] { this.basePath, Integer.valueOf(this.mipmapLevels), Integer.valueOf(var262), Integer.valueOf(var252) });
/* 296 */       this.mipmapLevels = var262;
/*     */     } 
/*     */     
/* 299 */     Iterator<TextureAtlasSprite> var272 = this.mapRegisteredSprites.values().iterator();
/*     */     
/* 301 */     while (var272.hasNext() && !this.skipFirst) {
/*     */       
/* 303 */       final TextureAtlasSprite var281 = var272.next();
/*     */ 
/*     */       
/*     */       try {
/* 307 */         var281.generateMipmaps(this.mipmapLevels);
/*     */       }
/* 309 */       catch (Throwable var24) {
/*     */         
/* 311 */         CrashReport var311 = CrashReport.makeCrashReport(var24, "Applying mipmap");
/* 312 */         CrashReportCategory sheetWidth = var311.makeCategory("Sprite being mipmapped");
/* 313 */         sheetWidth.addCrashSectionCallable("Sprite name", new Callable()
/*     */             {
/*     */               private static final String __OBFID = "CL_00001059";
/*     */               
/*     */               public String call() {
/* 318 */                 return var281.getIconName();
/*     */               }
/*     */             });
/* 321 */         sheetWidth.addCrashSectionCallable("Sprite size", new Callable()
/*     */             {
/*     */               private static final String __OBFID = "CL_00001060";
/*     */               
/*     */               public String call() {
/* 326 */                 return String.valueOf(var281.getIconWidth()) + " x " + var281.getIconHeight();
/*     */               }
/*     */             });
/* 329 */         sheetWidth.addCrashSectionCallable("Sprite frames", new Callable()
/*     */             {
/*     */               private static final String __OBFID = "CL_00001061";
/*     */               
/*     */               public String call() {
/* 334 */                 return String.valueOf(var281.getFrameCount()) + " frames";
/*     */               }
/*     */             });
/* 337 */         sheetWidth.addCrashSection("Mipmap levels", Integer.valueOf(this.mipmapLevels));
/* 338 */         throw new ReportedException(var311);
/*     */       } 
/*     */     } 
/*     */     
/* 342 */     this.missingImage.generateMipmaps(this.mipmapLevels);
/* 343 */     var31.addSprite(this.missingImage);
/* 344 */     this.skipFirst = false;
/*     */ 
/*     */     
/*     */     try {
/* 348 */       var31.doStitch();
/*     */     }
/* 350 */     catch (StitcherException var23) {
/*     */       
/* 352 */       throw var23;
/*     */     } 
/*     */     
/* 355 */     logger.info("Created: {}x{} {}-atlas", new Object[] { Integer.valueOf(var31.getCurrentWidth()), Integer.valueOf(var31.getCurrentHeight()), this.basePath });
/*     */     
/* 357 */     if (Config.isShaders()) {
/*     */       
/* 359 */       ShadersTex.allocateTextureMap(getGlTextureId(), this.mipmapLevels, var31.getCurrentWidth(), var31.getCurrentHeight(), var31, this);
/*     */     }
/*     */     else {
/*     */       
/* 363 */       TextureUtil.func_180600_a(getGlTextureId(), this.mipmapLevels, var31.getCurrentWidth(), var31.getCurrentHeight());
/*     */     } 
/*     */     
/* 366 */     HashMap var282 = Maps.newHashMap(this.mapRegisteredSprites);
/* 367 */     Iterator<TextureAtlasSprite> var302 = var31.getStichSlots().iterator();
/*     */ 
/*     */     
/* 370 */     while (var302.hasNext()) {
/*     */       
/* 372 */       TextureAtlasSprite var312 = var302.next();
/*     */       
/* 374 */       if (Config.isShaders())
/*     */       {
/* 376 */         ShadersTex.setIconName(ShadersTex.setSprite(var312).getIconName());
/*     */       }
/*     */       
/* 379 */       String sheetWidth1 = var312.getIconName();
/* 380 */       var282.remove(sheetWidth1);
/* 381 */       this.mapUploadedSprites.put(sheetWidth1, var312);
/*     */ 
/*     */       
/*     */       try {
/* 385 */         if (Config.isShaders())
/*     */         {
/* 387 */           ShadersTex.uploadTexSubForLoadAtlas(var312.getFrameTextureData(0), var312.getIconWidth(), var312.getIconHeight(), var312.getOriginX(), var312.getOriginY(), false, false);
/*     */         }
/*     */         else
/*     */         {
/* 391 */           TextureUtil.uploadTextureMipmap(var312.getFrameTextureData(0), var312.getIconWidth(), var312.getIconHeight(), var312.getOriginX(), var312.getOriginY(), false, false);
/*     */         }
/*     */       
/* 394 */       } catch (Throwable var22) {
/*     */         
/* 396 */         CrashReport listSprites3 = CrashReport.makeCrashReport(var22, "Stitching texture atlas");
/* 397 */         CrashReportCategory it1 = listSprites3.makeCategory("Texture being stitched together");
/* 398 */         it1.addCrashSection("Atlas path", this.basePath);
/* 399 */         it1.addCrashSection("Sprite", var312);
/* 400 */         throw new ReportedException(listSprites3);
/*     */       } 
/*     */       
/* 403 */       if (var312.hasAnimationMetadata())
/*     */       {
/* 405 */         this.listAnimatedSprites.add(var312);
/*     */       }
/*     */     } 
/*     */     
/* 409 */     var302 = var282.values().iterator();
/*     */     
/* 411 */     while (var302.hasNext()) {
/*     */       
/* 413 */       TextureAtlasSprite var312 = var302.next();
/* 414 */       var312.copyFrom(this.missingImage);
/*     */     } 
/*     */     
/* 417 */     if (Config.isMultiTexture()) {
/*     */       
/* 419 */       int sheetWidth2 = var31.getCurrentWidth();
/* 420 */       int sheetHeight = var31.getCurrentHeight();
/* 421 */       List listSprites1 = var31.getStichSlots();
/* 422 */       Iterator<TextureAtlasSprite> it2 = listSprites1.iterator();
/*     */       
/* 424 */       while (it2.hasNext()) {
/*     */         
/* 426 */         TextureAtlasSprite tas2 = it2.next();
/* 427 */         tas2.sheetWidth = sheetWidth2;
/* 428 */         tas2.sheetHeight = sheetHeight;
/* 429 */         tas2.mipmapLevels = this.mipmapLevels;
/* 430 */         TextureAtlasSprite ss1 = tas2.spriteSingle;
/*     */         
/* 432 */         if (ss1 != null) {
/*     */           
/* 434 */           ss1.sheetWidth = sheetWidth2;
/* 435 */           ss1.sheetHeight = sheetHeight;
/* 436 */           ss1.mipmapLevels = this.mipmapLevels;
/* 437 */           tas2.bindSpriteTexture();
/* 438 */           boolean texBlur = false;
/* 439 */           boolean texClamp = true;
/* 440 */           TextureUtil.uploadTextureMipmap(ss1.getFrameTextureData(0), ss1.getIconWidth(), ss1.getIconHeight(), ss1.getOriginX(), ss1.getOriginY(), texBlur, texClamp);
/*     */         } 
/*     */       } 
/*     */       
/* 444 */       Config.getMinecraft().getTextureManager().bindTexture(locationBlocksTexture);
/*     */     } 
/*     */     
/* 447 */     Reflector.callVoid(Reflector.ForgeHooksClient_onTextureStitchedPost, new Object[] { this });
/* 448 */     updateIconGrid(var31.getCurrentWidth(), var31.getCurrentHeight());
/*     */     
/* 450 */     if (Config.equals(System.getProperty("saveTextureMap"), "true")) {
/*     */       
/* 452 */       Config.dbg("Exporting texture map to: " + this.basePath + "_x.png");
/* 453 */       TextureUtil.func_177055_a(this.basePath.replaceAll("/", "_"), getGlTextureId(), this.mipmapLevels, var31.getCurrentWidth(), var31.getCurrentHeight());
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public ResourceLocation completeResourceLocation(ResourceLocation p_147634_1_, int p_147634_2_) {
/* 459 */     return isAbsoluteLocation(p_147634_1_) ? ((p_147634_2_ == 0) ? new ResourceLocation(p_147634_1_.getResourceDomain(), String.valueOf(p_147634_1_.getResourcePath()) + ".png") : new ResourceLocation(p_147634_1_.getResourceDomain(), String.valueOf(p_147634_1_.getResourcePath()) + "mipmap" + p_147634_2_ + ".png")) : ((p_147634_2_ == 0) ? new ResourceLocation(p_147634_1_.getResourceDomain(), String.format("%s/%s%s", new Object[] { this.basePath, p_147634_1_.getResourcePath(), ".png" })) : new ResourceLocation(p_147634_1_.getResourceDomain(), String.format("%s/mipmaps/%s.%d%s", new Object[] { this.basePath, p_147634_1_.getResourcePath(), Integer.valueOf(p_147634_2_), ".png" })));
/*     */   }
/*     */ 
/*     */   
/*     */   public TextureAtlasSprite getAtlasSprite(String p_110572_1_) {
/* 464 */     TextureAtlasSprite var2 = (TextureAtlasSprite)this.mapUploadedSprites.get(p_110572_1_);
/*     */     
/* 466 */     if (var2 == null)
/*     */     {
/* 468 */       var2 = this.missingImage;
/*     */     }
/*     */     
/* 471 */     return var2;
/*     */   }
/*     */ 
/*     */   
/*     */   public void updateAnimations() {
/* 476 */     if (Config.isShaders())
/*     */     {
/* 478 */       ShadersTex.updatingTex = getMultiTexID();
/*     */     }
/*     */     
/* 481 */     TextureUtil.bindTexture(getGlTextureId());
/* 482 */     Iterator<TextureAtlasSprite> var1 = this.listAnimatedSprites.iterator();
/*     */     
/* 484 */     while (var1.hasNext()) {
/*     */       
/* 486 */       TextureAtlasSprite it = var1.next();
/*     */       
/* 488 */       if (isTerrainAnimationActive(it))
/*     */       {
/* 490 */         it.updateAnimation();
/*     */       }
/*     */     } 
/*     */     
/* 494 */     if (Config.isMultiTexture()) {
/*     */       
/* 496 */       Iterator<TextureAtlasSprite> it1 = this.listAnimatedSprites.iterator();
/*     */       
/* 498 */       while (it1.hasNext()) {
/*     */         
/* 500 */         TextureAtlasSprite ts = it1.next();
/*     */         
/* 502 */         if (isTerrainAnimationActive(ts)) {
/*     */           
/* 504 */           TextureAtlasSprite spriteSingle = ts.spriteSingle;
/*     */           
/* 506 */           if (spriteSingle != null) {
/*     */             
/* 508 */             if (ts == TextureUtils.iconClock || ts == TextureUtils.iconCompass)
/*     */             {
/* 510 */               spriteSingle.frameCounter = ts.frameCounter;
/*     */             }
/*     */             
/* 513 */             ts.bindSpriteTexture();
/* 514 */             spriteSingle.updateAnimation();
/*     */           } 
/*     */         } 
/*     */       } 
/*     */       
/* 519 */       TextureUtil.bindTexture(getGlTextureId());
/*     */     } 
/*     */     
/* 522 */     if (Config.isShaders())
/*     */     {
/* 524 */       ShadersTex.updatingTex = null;
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public TextureAtlasSprite func_174942_a(ResourceLocation p_174942_1_) {
/* 530 */     if (p_174942_1_ == null)
/*     */     {
/* 532 */       throw new IllegalArgumentException("Location cannot be null!");
/*     */     }
/*     */ 
/*     */     
/* 536 */     TextureAtlasSprite var2 = (TextureAtlasSprite)this.mapRegisteredSprites.get(p_174942_1_.toString());
/*     */     
/* 538 */     if (var2 == null) {
/*     */       
/* 540 */       var2 = TextureAtlasSprite.func_176604_a(p_174942_1_);
/* 541 */       this.mapRegisteredSprites.put(p_174942_1_.toString(), var2);
/*     */       
/* 543 */       if (var2 instanceof TextureAtlasSprite && var2.getIndexInMap() < 0)
/*     */       {
/* 545 */         var2.setIndexInMap(this.mapRegisteredSprites.size());
/*     */       }
/*     */     } 
/*     */     
/* 549 */     return var2;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void tick() {
/* 555 */     updateAnimations();
/*     */   }
/*     */ 
/*     */   
/*     */   public void setMipmapLevels(int p_147633_1_) {
/* 560 */     this.mipmapLevels = p_147633_1_;
/*     */   }
/*     */ 
/*     */   
/*     */   public TextureAtlasSprite func_174944_f() {
/* 565 */     return this.missingImage;
/*     */   }
/*     */ 
/*     */   
/*     */   public TextureAtlasSprite getTextureExtry(String name) {
/* 570 */     ResourceLocation loc = new ResourceLocation(name);
/* 571 */     return (TextureAtlasSprite)this.mapRegisteredSprites.get(loc.toString());
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean setTextureEntry(String name, TextureAtlasSprite entry) {
/* 576 */     if (!this.mapRegisteredSprites.containsKey(name)) {
/*     */       
/* 578 */       this.mapRegisteredSprites.put(name, entry);
/*     */       
/* 580 */       if (entry.getIndexInMap() < 0)
/*     */       {
/* 582 */         entry.setIndexInMap(this.mapRegisteredSprites.size());
/*     */       }
/*     */       
/* 585 */       return true;
/*     */     } 
/*     */ 
/*     */     
/* 589 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean isAbsoluteLocation(ResourceLocation loc) {
/* 595 */     String path = loc.getResourcePath();
/* 596 */     return isAbsoluteLocationPath(path);
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean isAbsoluteLocationPath(String resPath) {
/* 601 */     String path = resPath.toLowerCase();
/* 602 */     return !(!path.startsWith("mcpatcher/") && !path.startsWith("optifine/"));
/*     */   }
/*     */ 
/*     */   
/*     */   public TextureAtlasSprite getSpriteSafe(String name) {
/* 607 */     ResourceLocation loc = new ResourceLocation(name);
/* 608 */     return (TextureAtlasSprite)this.mapRegisteredSprites.get(loc.toString());
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean isTerrainAnimationActive(TextureAtlasSprite ts) {
/* 613 */     return (ts != TextureUtils.iconWaterStill && ts != TextureUtils.iconWaterFlow) ? ((ts != TextureUtils.iconLavaStill && ts != TextureUtils.iconLavaFlow) ? ((ts != TextureUtils.iconFireLayer0 && ts != TextureUtils.iconFireLayer1) ? ((ts == TextureUtils.iconPortal) ? Config.isAnimatedPortal() : ((ts != TextureUtils.iconClock && ts != TextureUtils.iconCompass) ? Config.isAnimatedTerrain() : true)) : Config.isAnimatedFire()) : Config.isAnimatedLava()) : Config.isAnimatedWater();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getCountRegisteredSprites() {
/* 618 */     return this.mapRegisteredSprites.size();
/*     */   }
/*     */ 
/*     */   
/*     */   private int detectMaxMipmapLevel(Map mapSprites, IResourceManager rm) {
/* 623 */     int minSize = detectMinimumSpriteSize(mapSprites, rm, 20);
/*     */     
/* 625 */     if (minSize < 16)
/*     */     {
/* 627 */       minSize = 16;
/*     */     }
/*     */     
/* 630 */     minSize = MathHelper.roundUpToPowerOfTwo(minSize);
/*     */     
/* 632 */     if (minSize > 16)
/*     */     {
/* 634 */       Config.log("Sprite size: " + minSize);
/*     */     }
/*     */     
/* 637 */     int minLevel = MathHelper.calculateLogBaseTwo(minSize);
/*     */     
/* 639 */     if (minLevel < 4)
/*     */     {
/* 641 */       minLevel = 4;
/*     */     }
/*     */     
/* 644 */     return minLevel;
/*     */   }
/*     */ 
/*     */   
/*     */   private int detectMinimumSpriteSize(Map mapSprites, IResourceManager rm, int percentScale) {
/* 649 */     HashMap<Object, Object> mapSizeCounts = new HashMap<>();
/* 650 */     Set entrySetSprites = mapSprites.entrySet();
/* 651 */     Iterator<Map.Entry> countSprites = entrySetSprites.iterator();
/*     */ 
/*     */     
/* 654 */     while (countSprites.hasNext()) {
/*     */       
/* 656 */       Map.Entry setSizes = countSprites.next();
/* 657 */       TextureAtlasSprite setSizesSorted = (TextureAtlasSprite)setSizes.getValue();
/* 658 */       ResourceLocation minSize = new ResourceLocation(setSizesSorted.getIconName());
/* 659 */       ResourceLocation countScale = completeResourceLocation(minSize, 0);
/*     */       
/* 661 */       if (!setSizesSorted.hasCustomLoader(rm, minSize)) {
/*     */         
/*     */         try {
/*     */           
/* 665 */           IResource countScaleMax = rm.getResource(countScale);
/*     */           
/* 667 */           if (countScaleMax != null) {
/*     */             
/* 669 */             InputStream it = countScaleMax.getInputStream();
/*     */             
/* 671 */             if (it != null)
/*     */             {
/* 673 */               Dimension size = TextureUtils.getImageSize(it, "png");
/*     */               
/* 675 */               if (size != null)
/*     */               {
/* 677 */                 int count = size.width;
/* 678 */                 int width2 = MathHelper.roundUpToPowerOfTwo(count);
/*     */                 
/* 680 */                 if (!mapSizeCounts.containsKey(Integer.valueOf(width2))) {
/*     */                   
/* 682 */                   mapSizeCounts.put(Integer.valueOf(width2), Integer.valueOf(1));
/*     */                   
/*     */                   continue;
/*     */                 } 
/* 686 */                 int count1 = ((Integer)mapSizeCounts.get(Integer.valueOf(width2))).intValue();
/* 687 */                 mapSizeCounts.put(Integer.valueOf(width2), Integer.valueOf(count1 + 1));
/*     */               }
/*     */             
/*     */             }
/*     */           
/*     */           } 
/* 693 */         } catch (Exception exception) {}
/*     */       }
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 700 */     int countSprites1 = 0;
/* 701 */     Set<?> setSizes1 = mapSizeCounts.keySet();
/* 702 */     TreeSet setSizesSorted1 = new TreeSet(setSizes1);
/*     */ 
/*     */ 
/*     */     
/* 706 */     for (Iterator<Integer> minSize1 = setSizesSorted1.iterator(); minSize1.hasNext(); countSprites1 += j) {
/*     */       
/* 708 */       int i = ((Integer)minSize1.next()).intValue();
/* 709 */       int j = ((Integer)mapSizeCounts.get(Integer.valueOf(i))).intValue();
/*     */     } 
/*     */     
/* 712 */     int minSize2 = 16;
/* 713 */     int countScale1 = 0;
/* 714 */     int countScaleMax1 = countSprites1 * percentScale / 100;
/* 715 */     Iterator<Integer> it1 = setSizesSorted1.iterator();
/*     */ 
/*     */     
/*     */     do {
/* 719 */       if (!it1.hasNext())
/*     */       {
/* 721 */         return minSize2;
/*     */       }
/*     */       
/* 724 */       int size1 = ((Integer)it1.next()).intValue();
/* 725 */       int count = ((Integer)mapSizeCounts.get(Integer.valueOf(size1))).intValue();
/* 726 */       countScale1 += count;
/*     */       
/* 728 */       if (size1 <= minSize2)
/*     */         continue; 
/* 730 */       minSize2 = size1;
/*     */     
/*     */     }
/* 733 */     while (countScale1 <= countScaleMax1);
/*     */     
/* 735 */     return minSize2;
/*     */   }
/*     */ 
/*     */   
/*     */   private int getMinSpriteSize() {
/* 740 */     int minSize = 1 << this.mipmapLevels;
/*     */     
/* 742 */     if (minSize < 8)
/*     */     {
/* 744 */       minSize = 8;
/*     */     }
/*     */     
/* 747 */     return minSize;
/*     */   }
/*     */ 
/*     */   
/*     */   private int[] getMissingImageData(int size) {
/* 752 */     BufferedImage bi = new BufferedImage(16, 16, 2);
/* 753 */     bi.setRGB(0, 0, 16, 16, TextureUtil.missingTextureData, 0, 16);
/* 754 */     BufferedImage bi2 = TextureUtils.scaleToPowerOfTwo(bi, size);
/* 755 */     int[] data = new int[size * size];
/* 756 */     bi2.getRGB(0, 0, size, size, data, 0, size);
/* 757 */     return data;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isTextureBound() {
/* 762 */     int boundTexId = GlStateManager.getBoundTexture();
/* 763 */     int texId = getGlTextureId();
/* 764 */     return (boundTexId == texId);
/*     */   }
/*     */ 
/*     */   
/*     */   private void updateIconGrid(int sheetWidth, int sheetHeight) {
/* 769 */     this.iconGridCountX = -1;
/* 770 */     this.iconGridCountY = -1;
/* 771 */     this.iconGrid = null;
/*     */     
/* 773 */     if (this.iconGridSize > 0) {
/*     */       
/* 775 */       this.iconGridCountX = sheetWidth / this.iconGridSize;
/* 776 */       this.iconGridCountY = sheetHeight / this.iconGridSize;
/* 777 */       this.iconGrid = new TextureAtlasSprite[this.iconGridCountX * this.iconGridCountY];
/* 778 */       this.iconGridSizeU = 1.0D / this.iconGridCountX;
/* 779 */       this.iconGridSizeV = 1.0D / this.iconGridCountY;
/* 780 */       Iterator<TextureAtlasSprite> it = this.mapUploadedSprites.values().iterator();
/*     */       
/* 782 */       while (it.hasNext()) {
/*     */         
/* 784 */         TextureAtlasSprite ts = it.next();
/* 785 */         double deltaU = 0.5D / sheetWidth;
/* 786 */         double deltaV = 0.5D / sheetHeight;
/* 787 */         double uMin = Math.min(ts.getMinU(), ts.getMaxU()) + deltaU;
/* 788 */         double vMin = Math.min(ts.getMinV(), ts.getMaxV()) + deltaV;
/* 789 */         double uMax = Math.max(ts.getMinU(), ts.getMaxU()) - deltaU;
/* 790 */         double vMax = Math.max(ts.getMinV(), ts.getMaxV()) - deltaV;
/* 791 */         int iuMin = (int)(uMin / this.iconGridSizeU);
/* 792 */         int ivMin = (int)(vMin / this.iconGridSizeV);
/* 793 */         int iuMax = (int)(uMax / this.iconGridSizeU);
/* 794 */         int ivMax = (int)(vMax / this.iconGridSizeV);
/*     */         
/* 796 */         for (int iu = iuMin; iu <= iuMax; iu++) {
/*     */           
/* 798 */           if (iu >= 0 && iu < this.iconGridCountX) {
/*     */             
/* 800 */             for (int iv = ivMin; iv <= ivMax; iv++) {
/*     */               
/* 802 */               if (iv >= 0 && iv < this.iconGridCountX)
/*     */               {
/* 804 */                 int index = iv * this.iconGridCountX + iu;
/* 805 */                 this.iconGrid[index] = ts;
/*     */               }
/*     */               else
/*     */               {
/* 809 */                 Config.warn("Invalid grid V: " + iv + ", icon: " + ts.getIconName());
/*     */               }
/*     */             
/*     */             } 
/*     */           } else {
/*     */             
/* 815 */             Config.warn("Invalid grid U: " + iu + ", icon: " + ts.getIconName());
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public TextureAtlasSprite getIconByUV(double u, double v) {
/* 824 */     if (this.iconGrid == null)
/*     */     {
/* 826 */       return null;
/*     */     }
/*     */ 
/*     */     
/* 830 */     int iu = (int)(u / this.iconGridSizeU);
/* 831 */     int iv = (int)(v / this.iconGridSizeV);
/* 832 */     int index = iv * this.iconGridCountX + iu;
/* 833 */     return (index >= 0 && index <= this.iconGrid.length) ? this.iconGrid[index] : null;
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\renderer\texture\TextureMap.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */