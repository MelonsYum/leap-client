/*     */ package net.minecraft.client.renderer;
/*     */ 
/*     */ import java.awt.image.BufferedImage;
/*     */ import java.io.ByteArrayInputStream;
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.net.HttpURLConnection;
/*     */ import java.net.Proxy;
/*     */ import java.net.URL;
/*     */ import java.util.concurrent.atomic.AtomicInteger;
/*     */ import javax.imageio.ImageIO;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.renderer.texture.SimpleTexture;
/*     */ import net.minecraft.client.renderer.texture.TextureUtil;
/*     */ import net.minecraft.client.resources.IResourceManager;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import optifine.Config;
/*     */ import optifine.HttpPipeline;
/*     */ import optifine.HttpRequest;
/*     */ import optifine.HttpResponse;
/*     */ import org.apache.commons.io.FileUtils;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ 
/*     */ 
/*     */ public class ThreadDownloadImageData
/*     */   extends SimpleTexture
/*     */ {
/*  29 */   private static final Logger logger = LogManager.getLogger();
/*  30 */   private static final AtomicInteger threadDownloadCounter = new AtomicInteger(0);
/*     */   private final File field_152434_e;
/*     */   private final String imageUrl;
/*     */   private final IImageBuffer imageBuffer;
/*     */   private BufferedImage bufferedImage;
/*     */   private Thread imageThread;
/*     */   private boolean textureUploaded;
/*     */   private static final String __OBFID = "CL_00001049";
/*  38 */   public Boolean imageFound = null;
/*     */   
/*     */   public boolean pipeline = false;
/*     */   
/*     */   public ThreadDownloadImageData(File p_i1049_1_, String p_i1049_2_, ResourceLocation p_i1049_3_, IImageBuffer p_i1049_4_) {
/*  43 */     super(p_i1049_3_);
/*  44 */     this.field_152434_e = p_i1049_1_;
/*  45 */     this.imageUrl = p_i1049_2_;
/*  46 */     this.imageBuffer = p_i1049_4_;
/*     */   }
/*     */ 
/*     */   
/*     */   private void checkTextureUploaded() {
/*  51 */     if (!this.textureUploaded && this.bufferedImage != null) {
/*     */       
/*  53 */       this.textureUploaded = true;
/*     */       
/*  55 */       if (this.textureLocation != null)
/*     */       {
/*  57 */         deleteGlTexture();
/*     */       }
/*     */       
/*  60 */       TextureUtil.uploadTextureImage(super.getGlTextureId(), this.bufferedImage);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public int getGlTextureId() {
/*  66 */     checkTextureUploaded();
/*  67 */     return super.getGlTextureId();
/*     */   }
/*     */ 
/*     */   
/*     */   public void setBufferedImage(BufferedImage p_147641_1_) {
/*  72 */     this.bufferedImage = p_147641_1_;
/*     */     
/*  74 */     if (this.imageBuffer != null)
/*     */     {
/*  76 */       this.imageBuffer.func_152634_a();
/*     */     }
/*     */     
/*  79 */     this.imageFound = Boolean.valueOf((this.bufferedImage != null));
/*     */   }
/*     */ 
/*     */   
/*     */   public void loadTexture(IResourceManager p_110551_1_) throws IOException {
/*  84 */     if (this.bufferedImage == null && this.textureLocation != null)
/*     */     {
/*  86 */       super.loadTexture(p_110551_1_);
/*     */     }
/*     */     
/*  89 */     if (this.imageThread == null)
/*     */     {
/*  91 */       if (this.field_152434_e != null && this.field_152434_e.isFile()) {
/*     */         
/*  93 */         logger.debug("Loading http texture from local cache ({})", new Object[] { this.field_152434_e });
/*     */ 
/*     */         
/*     */         try {
/*  97 */           this.bufferedImage = ImageIO.read(this.field_152434_e);
/*     */           
/*  99 */           if (this.imageBuffer != null)
/*     */           {
/* 101 */             setBufferedImage(this.imageBuffer.parseUserSkin(this.bufferedImage));
/*     */           }
/*     */           
/* 104 */           this.imageFound = Boolean.valueOf((this.bufferedImage != null));
/*     */         }
/* 106 */         catch (IOException var3) {
/*     */           
/* 108 */           logger.error("Couldn't load skin " + this.field_152434_e, var3);
/* 109 */           func_152433_a();
/*     */         }
/*     */       
/*     */       } else {
/*     */         
/* 114 */         func_152433_a();
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   protected void func_152433_a() {
/* 121 */     this.imageThread = new Thread("Texture Downloader #" + threadDownloadCounter.incrementAndGet())
/*     */       {
/*     */         public void run()
/*     */         {
/* 125 */           HttpURLConnection var1 = null;
/* 126 */           ThreadDownloadImageData.logger.debug("Downloading http texture from {} to {}", new Object[] { ThreadDownloadImageData.access$1(this.this$0), ThreadDownloadImageData.access$2(this.this$0) });
/*     */           
/* 128 */           if (ThreadDownloadImageData.this.shouldPipeline()) {
/*     */             
/* 130 */             ThreadDownloadImageData.this.loadPipelined();
/*     */           } else {
/*     */             
/*     */             try { BufferedImage var6;
/*     */ 
/*     */               
/* 136 */               var1 = (HttpURLConnection)(new URL(ThreadDownloadImageData.this.imageUrl)).openConnection(Minecraft.getMinecraft().getProxy());
/* 137 */               var1.setDoInput(true);
/* 138 */               var1.setDoOutput(false);
/* 139 */               var1.connect();
/*     */               
/* 141 */               if (var1.getResponseCode() / 100 != 2) {
/*     */                 
/* 143 */                 if (var1.getErrorStream() != null)
/*     */                 {
/* 145 */                   Config.readAll(var1.getErrorStream());
/*     */                 }
/*     */ 
/*     */                 
/*     */                 return;
/*     */               } 
/*     */ 
/*     */               
/* 153 */               if (ThreadDownloadImageData.this.field_152434_e != null)
/*     */               {
/* 155 */                 FileUtils.copyInputStreamToFile(var1.getInputStream(), ThreadDownloadImageData.this.field_152434_e);
/* 156 */                 var6 = ImageIO.read(ThreadDownloadImageData.this.field_152434_e);
/*     */               }
/*     */               else
/*     */               {
/* 160 */                 var6 = TextureUtil.func_177053_a(var1.getInputStream());
/*     */ 
/*     */ 
/*     */               
/*     */               }
/*     */ 
/*     */ 
/*     */               
/*     */                }
/*     */             
/* 170 */             catch (Exception var61)
/*     */             
/* 172 */             { ThreadDownloadImageData.logger.error("Couldn't download http texture: " + var61.getClass().getName() + ": " + var61.getMessage());
/*     */ 
/*     */               
/*     */               return; }
/*     */             finally
/* 177 */             { if (var1 != null)
/*     */               {
/* 179 */                 var1.disconnect();
/*     */               }
/*     */               
/* 182 */               ThreadDownloadImageData.this.imageFound = Boolean.valueOf((ThreadDownloadImageData.this.bufferedImage != null)); }  if (var1 != null) var1.disconnect();  ThreadDownloadImageData.this.imageFound = Boolean.valueOf((ThreadDownloadImageData.this.bufferedImage != null));
/*     */           } 
/*     */         }
/*     */       };
/*     */     
/* 187 */     this.imageThread.setDaemon(true);
/* 188 */     this.imageThread.start();
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean shouldPipeline() {
/* 193 */     if (!this.pipeline)
/*     */     {
/* 195 */       return false;
/*     */     }
/*     */ 
/*     */     
/* 199 */     Proxy proxy = Minecraft.getMinecraft().getProxy();
/* 200 */     return (proxy.type() != Proxy.Type.DIRECT && proxy.type() != Proxy.Type.SOCKS) ? false : this.imageUrl.startsWith("http://");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void loadPipelined() {
/*     */     
/*     */     try { BufferedImage var2;
/* 208 */       HttpRequest var6 = HttpPipeline.makeRequest(this.imageUrl, Minecraft.getMinecraft().getProxy());
/* 209 */       HttpResponse resp = HttpPipeline.executeRequest(var6);
/*     */       
/* 211 */       if (resp.getStatus() / 100 != 2) {
/*     */         return;
/*     */       }
/*     */ 
/*     */       
/* 216 */       byte[] body = resp.getBody();
/* 217 */       ByteArrayInputStream bais = new ByteArrayInputStream(body);
/*     */ 
/*     */       
/* 220 */       if (this.field_152434_e != null) {
/*     */         
/* 222 */         FileUtils.copyInputStreamToFile(bais, this.field_152434_e);
/* 223 */         var2 = ImageIO.read(this.field_152434_e);
/*     */       }
/*     */       else {
/*     */         
/* 227 */         var2 = TextureUtil.func_177053_a(bais);
/*     */       } 
/*     */       
/* 230 */       if (this.imageBuffer != null)
/*     */       {
/* 232 */         var2 = this.imageBuffer.parseUserSkin(var2);
/*     */       
/*     */       }
/*     */        }
/*     */     
/* 237 */     catch (Exception var9)
/*     */     
/* 239 */     { logger.error("Couldn't download http texture: " + var9.getClass().getName() + ": " + var9.getMessage());
/*     */ 
/*     */       
/*     */       return; }
/*     */     finally
/* 244 */     { this.imageFound = Boolean.valueOf((this.bufferedImage != null)); }  this.imageFound = Boolean.valueOf((this.bufferedImage != null));
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\renderer\ThreadDownloadImageData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */