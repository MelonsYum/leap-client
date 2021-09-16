/*     */ package net.minecraft.client.resources;
/*     */ 
/*     */ import com.google.common.cache.CacheBuilder;
/*     */ import com.google.common.cache.CacheLoader;
/*     */ import com.google.common.cache.LoadingCache;
/*     */ import com.google.common.collect.Maps;
/*     */ import com.mojang.authlib.GameProfile;
/*     */ import com.mojang.authlib.minecraft.InsecureTextureException;
/*     */ import com.mojang.authlib.minecraft.MinecraftProfileTexture;
/*     */ import com.mojang.authlib.minecraft.MinecraftSessionService;
/*     */ import java.awt.image.BufferedImage;
/*     */ import java.io.File;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import java.util.concurrent.ExecutorService;
/*     */ import java.util.concurrent.LinkedBlockingQueue;
/*     */ import java.util.concurrent.ThreadPoolExecutor;
/*     */ import java.util.concurrent.TimeUnit;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.renderer.IImageBuffer;
/*     */ import net.minecraft.client.renderer.ImageBufferDownload;
/*     */ import net.minecraft.client.renderer.ThreadDownloadImageData;
/*     */ import net.minecraft.client.renderer.texture.ITextureObject;
/*     */ import net.minecraft.client.renderer.texture.TextureManager;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ 
/*     */ 
/*     */ public class SkinManager
/*     */ {
/*  30 */   private static final ExecutorService THREAD_POOL = new ThreadPoolExecutor(0, 2, 1L, TimeUnit.MINUTES, new LinkedBlockingQueue<>());
/*     */   
/*     */   private final TextureManager textureManager;
/*     */   private final File skinCacheDir;
/*     */   private final MinecraftSessionService sessionService;
/*     */   private final LoadingCache skinCacheLoader;
/*     */   private static final String __OBFID = "CL_00001830";
/*     */   
/*     */   public SkinManager(TextureManager textureManagerInstance, File skinCacheDirectory, MinecraftSessionService sessionService) {
/*  39 */     this.textureManager = textureManagerInstance;
/*  40 */     this.skinCacheDir = skinCacheDirectory;
/*  41 */     this.sessionService = sessionService;
/*  42 */     this.skinCacheLoader = CacheBuilder.newBuilder().expireAfterAccess(15L, TimeUnit.SECONDS).build(new CacheLoader()
/*     */         {
/*     */           private static final String __OBFID = "CL_00001829";
/*     */           
/*     */           public Map func_152786_a(GameProfile p_152786_1_) {
/*  47 */             return Minecraft.getMinecraft().getSessionService().getTextures(p_152786_1_, false);
/*     */           }
/*     */           
/*     */           public Object load(Object p_load_1_) {
/*  51 */             return func_152786_a((GameProfile)p_load_1_);
/*     */           }
/*     */         });
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ResourceLocation loadSkin(MinecraftProfileTexture p_152792_1_, MinecraftProfileTexture.Type p_152792_2_) {
/*  61 */     return loadSkin(p_152792_1_, p_152792_2_, null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ResourceLocation loadSkin(final MinecraftProfileTexture p_152789_1_, final MinecraftProfileTexture.Type p_152789_2_, final SkinAvailableCallback p_152789_3_) {
/*  69 */     final ResourceLocation var4 = new ResourceLocation("skins/" + p_152789_1_.getHash());
/*  70 */     ITextureObject var5 = this.textureManager.getTexture(var4);
/*     */     
/*  72 */     if (var5 != null) {
/*     */       
/*  74 */       if (p_152789_3_ != null)
/*     */       {
/*  76 */         p_152789_3_.func_180521_a(p_152789_2_, var4, p_152789_1_);
/*     */       }
/*     */     }
/*     */     else {
/*     */       
/*  81 */       File var6 = new File(this.skinCacheDir, p_152789_1_.getHash().substring(0, 2));
/*  82 */       File var7 = new File(var6, p_152789_1_.getHash());
/*  83 */       final ImageBufferDownload var8 = (p_152789_2_ == MinecraftProfileTexture.Type.SKIN) ? new ImageBufferDownload() : null;
/*  84 */       ThreadDownloadImageData var9 = new ThreadDownloadImageData(var7, p_152789_1_.getUrl(), DefaultPlayerSkin.func_177335_a(), new IImageBuffer()
/*     */           {
/*     */             private static final String __OBFID = "CL_00001828";
/*     */             
/*     */             public BufferedImage parseUserSkin(BufferedImage p_78432_1_) {
/*  89 */               if (var8 != null)
/*     */               {
/*  91 */                 p_78432_1_ = var8.parseUserSkin(p_78432_1_);
/*     */               }
/*     */               
/*  94 */               return p_78432_1_;
/*     */             }
/*     */             
/*     */             public void func_152634_a() {
/*  98 */               if (var8 != null)
/*     */               {
/* 100 */                 var8.func_152634_a();
/*     */               }
/*     */               
/* 103 */               if (p_152789_3_ != null)
/*     */               {
/* 105 */                 p_152789_3_.func_180521_a(p_152789_2_, var4, p_152789_1_);
/*     */               }
/*     */             }
/*     */           });
/* 109 */       this.textureManager.loadTexture(var4, (ITextureObject)var9);
/*     */     } 
/*     */     
/* 112 */     return var4;
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_152790_a(final GameProfile p_152790_1_, final SkinAvailableCallback p_152790_2_, final boolean p_152790_3_) {
/* 117 */     THREAD_POOL.submit(new Runnable()
/*     */         {
/*     */           private static final String __OBFID = "CL_00001827";
/*     */           
/*     */           public void run() {
/* 122 */             final HashMap var1 = Maps.newHashMap();
/*     */ 
/*     */             
/*     */             try {
/* 126 */               var1.putAll(SkinManager.this.sessionService.getTextures(p_152790_1_, p_152790_3_));
/*     */             }
/* 128 */             catch (InsecureTextureException insecureTextureException) {}
/*     */ 
/*     */ 
/*     */ 
/*     */             
/* 133 */             if (var1.isEmpty() && p_152790_1_.getId().equals(Minecraft.getMinecraft().getSession().getProfile().getId()))
/*     */             {
/* 135 */               var1.putAll(SkinManager.this.sessionService.getTextures(SkinManager.this.sessionService.fillProfileProperties(p_152790_1_, false), false));
/*     */             }
/*     */             
/* 138 */             Minecraft.getMinecraft().addScheduledTask(new Runnable()
/*     */                 {
/*     */                   private static final String __OBFID = "CL_00001826";
/*     */                   
/*     */                   public void run() {
/* 143 */                     if (var1.containsKey(MinecraftProfileTexture.Type.SKIN))
/*     */                     {
/* 145 */                       SkinManager.null.access$0(SkinManager.null.this).loadSkin((MinecraftProfileTexture)var1.get(MinecraftProfileTexture.Type.SKIN), MinecraftProfileTexture.Type.SKIN, p_152790_2_);
/*     */                     }
/*     */                     
/* 148 */                     if (var1.containsKey(MinecraftProfileTexture.Type.CAPE))
/*     */                     {
/* 150 */                       SkinManager.null.access$0(SkinManager.null.this).loadSkin((MinecraftProfileTexture)var1.get(MinecraftProfileTexture.Type.CAPE), MinecraftProfileTexture.Type.CAPE, p_152790_2_);
/*     */                     }
/*     */                   }
/*     */                 });
/*     */           }
/*     */         });
/*     */   }
/*     */ 
/*     */   
/*     */   public Map loadSkinFromCache(GameProfile p_152788_1_) {
/* 160 */     return (Map)this.skinCacheLoader.getUnchecked(p_152788_1_);
/*     */   }
/*     */   
/*     */   public static interface SkinAvailableCallback {
/*     */     void func_180521_a(MinecraftProfileTexture.Type param1Type, ResourceLocation param1ResourceLocation, MinecraftProfileTexture param1MinecraftProfileTexture);
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\resources\SkinManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */