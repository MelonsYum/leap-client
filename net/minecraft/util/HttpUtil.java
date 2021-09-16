/*     */ package net.minecraft.util;
/*     */ 
/*     */ import com.google.common.util.concurrent.ListenableFuture;
/*     */ import com.google.common.util.concurrent.ListeningExecutorService;
/*     */ import com.google.common.util.concurrent.MoreExecutors;
/*     */ import com.google.common.util.concurrent.ThreadFactoryBuilder;
/*     */ import java.io.BufferedReader;
/*     */ import java.io.DataOutputStream;
/*     */ import java.io.File;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.InputStreamReader;
/*     */ import java.io.UnsupportedEncodingException;
/*     */ import java.net.HttpURLConnection;
/*     */ import java.net.Proxy;
/*     */ import java.net.ServerSocket;
/*     */ import java.net.URL;
/*     */ import java.net.URLConnection;
/*     */ import java.net.URLEncoder;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import java.util.concurrent.Executors;
/*     */ import java.util.concurrent.atomic.AtomicInteger;
/*     */ import net.minecraft.server.MinecraftServer;
/*     */ import org.apache.commons.io.FileUtils;
/*     */ import org.apache.commons.io.IOUtils;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ 
/*     */ 
/*     */ public class HttpUtil
/*     */ {
/*  34 */   public static final ListeningExecutorService field_180193_a = MoreExecutors.listeningDecorator(Executors.newCachedThreadPool((new ThreadFactoryBuilder()).setDaemon(true).setNameFormat("Downloader %d").build()));
/*     */ 
/*     */   
/*  37 */   private static final AtomicInteger downloadThreadsStarted = new AtomicInteger(0);
/*  38 */   private static final Logger logger = LogManager.getLogger();
/*     */ 
/*     */   
/*     */   private static final String __OBFID = "CL_00001485";
/*     */ 
/*     */ 
/*     */   
/*     */   public static String buildPostString(Map data) {
/*  46 */     StringBuilder var1 = new StringBuilder();
/*  47 */     Iterator<Map.Entry> var2 = data.entrySet().iterator();
/*     */     
/*  49 */     while (var2.hasNext()) {
/*     */       
/*  51 */       Map.Entry var3 = var2.next();
/*     */       
/*  53 */       if (var1.length() > 0)
/*     */       {
/*  55 */         var1.append('&');
/*     */       }
/*     */ 
/*     */       
/*     */       try {
/*  60 */         var1.append(URLEncoder.encode((String)var3.getKey(), "UTF-8"));
/*     */       }
/*  62 */       catch (UnsupportedEncodingException var6) {
/*     */         
/*  64 */         var6.printStackTrace();
/*     */       } 
/*     */       
/*  67 */       if (var3.getValue() != null) {
/*     */         
/*  69 */         var1.append('=');
/*     */ 
/*     */         
/*     */         try {
/*  73 */           var1.append(URLEncoder.encode(var3.getValue().toString(), "UTF-8"));
/*     */         }
/*  75 */         catch (UnsupportedEncodingException var5) {
/*     */           
/*  77 */           var5.printStackTrace();
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/*  82 */     return var1.toString();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String postMap(URL url, Map data, boolean skipLoggingErrors) {
/*  90 */     return post(url, buildPostString(data), skipLoggingErrors);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static String post(URL url, String content, boolean skipLoggingErrors) {
/*     */     try {
/* 100 */       Proxy var3 = (MinecraftServer.getServer() == null) ? null : MinecraftServer.getServer().getServerProxy();
/*     */       
/* 102 */       if (var3 == null)
/*     */       {
/* 104 */         var3 = Proxy.NO_PROXY;
/*     */       }
/*     */       
/* 107 */       HttpURLConnection var4 = (HttpURLConnection)url.openConnection(var3);
/* 108 */       var4.setRequestMethod("POST");
/* 109 */       var4.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
/* 110 */       var4.setRequestProperty("Content-Length", (content.getBytes()).length);
/* 111 */       var4.setRequestProperty("Content-Language", "en-US");
/* 112 */       var4.setUseCaches(false);
/* 113 */       var4.setDoInput(true);
/* 114 */       var4.setDoOutput(true);
/* 115 */       DataOutputStream var5 = new DataOutputStream(var4.getOutputStream());
/* 116 */       var5.writeBytes(content);
/* 117 */       var5.flush();
/* 118 */       var5.close();
/* 119 */       BufferedReader var6 = new BufferedReader(new InputStreamReader(var4.getInputStream()));
/* 120 */       StringBuffer var8 = new StringBuffer();
/*     */       
/*     */       String var7;
/* 123 */       while ((var7 = var6.readLine()) != null) {
/*     */         
/* 125 */         var8.append(var7);
/* 126 */         var8.append('\r');
/*     */       } 
/*     */       
/* 129 */       var6.close();
/* 130 */       return var8.toString();
/*     */     }
/* 132 */     catch (Exception var9) {
/*     */       
/* 134 */       if (!skipLoggingErrors)
/*     */       {
/* 136 */         logger.error("Could not post to " + url, var9);
/*     */       }
/*     */       
/* 139 */       return "";
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static ListenableFuture func_180192_a(final File p_180192_0_, final String p_180192_1_, final Map p_180192_2_, final int p_180192_3_, final IProgressUpdate p_180192_4_, final Proxy p_180192_5_) {
/* 145 */     ListenableFuture var6 = field_180193_a.submit(new Runnable()
/*     */         {
/*     */           private static final String __OBFID = "CL_00001486";
/*     */           
/*     */           public void run() {
/* 150 */             InputStream var2 = null;
/* 151 */             DataOutputStream var3 = null;
/*     */             
/* 153 */             if (p_180192_4_ != null) {
/*     */               
/* 155 */               p_180192_4_.resetProgressAndMessage("Downloading Resource Pack");
/* 156 */               p_180192_4_.displayLoadingString("Making Request...");
/*     */             } 
/*     */ 
/*     */ 
/*     */ 
/*     */             
/*     */             try {
/* 163 */               byte[] var4 = new byte[4096];
/* 164 */               URL var5 = new URL(p_180192_1_);
/* 165 */               URLConnection var1 = var5.openConnection(p_180192_5_);
/* 166 */               float var6 = 0.0F;
/* 167 */               float var7 = p_180192_2_.entrySet().size();
/* 168 */               Iterator<Map.Entry> var8 = p_180192_2_.entrySet().iterator();
/*     */               
/* 170 */               while (var8.hasNext()) {
/*     */                 
/* 172 */                 Map.Entry var9 = var8.next();
/* 173 */                 var1.setRequestProperty((String)var9.getKey(), (String)var9.getValue());
/*     */                 
/* 175 */                 if (p_180192_4_ != null)
/*     */                 {
/* 177 */                   p_180192_4_.setLoadingProgress((int)(++var6 / var7 * 100.0F));
/*     */                 }
/*     */               } 
/*     */               
/* 181 */               var2 = var1.getInputStream();
/* 182 */               var7 = var1.getContentLength();
/* 183 */               int var16 = var1.getContentLength();
/*     */               
/* 185 */               if (p_180192_4_ != null)
/*     */               {
/* 187 */                 p_180192_4_.displayLoadingString(String.format("Downloading file (%.2f MB)...", new Object[] { Float.valueOf(var7 / 1000.0F / 1000.0F) }));
/*     */               }
/*     */               
/* 190 */               if (p_180192_0_.exists()) {
/*     */                 
/* 192 */                 long var17 = p_180192_0_.length();
/*     */                 
/* 194 */                 if (var17 == var16) {
/*     */                   
/* 196 */                   if (p_180192_4_ != null)
/*     */                   {
/* 198 */                     p_180192_4_.setDoneWorking();
/*     */                   }
/*     */                   
/*     */                   return;
/*     */                 } 
/*     */                 
/* 204 */                 HttpUtil.logger.warn("Deleting " + p_180192_0_ + " as it does not match what we currently have (" + var16 + " vs our " + var17 + ").");
/* 205 */                 FileUtils.deleteQuietly(p_180192_0_);
/*     */               }
/* 207 */               else if (p_180192_0_.getParentFile() != null) {
/*     */                 
/* 209 */                 p_180192_0_.getParentFile().mkdirs();
/*     */               } 
/*     */               
/* 212 */               var3 = new DataOutputStream(new FileOutputStream(p_180192_0_));
/*     */               
/* 214 */               if (p_180192_3_ > 0 && var7 > p_180192_3_) {
/*     */                 
/* 216 */                 if (p_180192_4_ != null)
/*     */                 {
/* 218 */                   p_180192_4_.setDoneWorking();
/*     */                 }
/*     */                 
/* 221 */                 throw new IOException("Filesize is bigger than maximum allowed (file is " + var6 + ", limit is " + p_180192_3_ + ")");
/*     */               } 
/*     */               
/* 224 */               boolean var18 = false;
/*     */               
/*     */               int var19;
/* 227 */               while ((var19 = var2.read(var4)) >= 0) {
/*     */                 
/* 229 */                 var6 += var19;
/*     */                 
/* 231 */                 if (p_180192_4_ != null)
/*     */                 {
/* 233 */                   p_180192_4_.setLoadingProgress((int)(var6 / var7 * 100.0F));
/*     */                 }
/*     */                 
/* 236 */                 if (p_180192_3_ > 0 && var6 > p_180192_3_) {
/*     */                   
/* 238 */                   if (p_180192_4_ != null)
/*     */                   {
/* 240 */                     p_180192_4_.setDoneWorking();
/*     */                   }
/*     */                   
/* 243 */                   throw new IOException("Filesize was bigger than maximum allowed (got >= " + var6 + ", limit was " + p_180192_3_ + ")");
/*     */                 } 
/*     */                 
/* 246 */                 var3.write(var4, 0, var19);
/*     */               } 
/*     */               
/* 249 */               if (p_180192_4_ != null) {
/*     */                 
/* 251 */                 p_180192_4_.setDoneWorking();
/*     */                 
/*     */                 return;
/*     */               } 
/* 255 */             } catch (Throwable var14) {
/*     */ 
/*     */ 
/*     */             
/*     */             }
/*     */             finally {
/*     */               
/* 262 */               IOUtils.closeQuietly(var2);
/* 263 */               IOUtils.closeQuietly(var3);
/*     */             } 
/*     */           }
/*     */         });
/* 267 */     return var6;
/*     */   }
/*     */   
/*     */   public static int getSuitableLanPort() throws IOException {
/*     */     int var10;
/* 272 */     ServerSocket var0 = null;
/* 273 */     boolean var1 = true;
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/* 278 */       var0 = new ServerSocket(0);
/* 279 */       var10 = var0.getLocalPort();
/*     */     } finally {
/*     */ 
/*     */       
/*     */       try {
/*     */         
/* 285 */         if (var0 != null)
/*     */         {
/* 287 */           var0.close();
/*     */         }
/*     */       }
/* 290 */       catch (IOException iOException) {}
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 296 */     return var10;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String get(URL url) throws IOException {
/* 304 */     HttpURLConnection var1 = (HttpURLConnection)url.openConnection();
/* 305 */     var1.setRequestMethod("GET");
/* 306 */     BufferedReader var2 = new BufferedReader(new InputStreamReader(var1.getInputStream()));
/* 307 */     StringBuilder var4 = new StringBuilder();
/*     */     
/*     */     String var3;
/* 310 */     while ((var3 = var2.readLine()) != null) {
/*     */       
/* 312 */       var4.append(var3);
/* 313 */       var4.append('\r');
/*     */     } 
/*     */     
/* 316 */     var2.close();
/* 317 */     return var4.toString();
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraf\\util\HttpUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */