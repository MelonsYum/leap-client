/*     */ package optifine;
/*     */ 
/*     */ import java.io.BufferedReader;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.InputStreamReader;
/*     */ import java.io.OutputStream;
/*     */ import java.net.HttpURLConnection;
/*     */ import java.net.URL;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import net.minecraft.client.Minecraft;
/*     */ 
/*     */ 
/*     */ public class HttpUtils
/*     */ {
/*     */   public static final String SERVER_URL = "http://s.optifine.net";
/*     */   public static final String POST_URL = "http://optifine.net";
/*     */   
/*     */   public static byte[] get(String urlStr) throws IOException {
/*  22 */     HttpURLConnection conn = null;
/*     */ 
/*     */     
/*     */     try {
/*  26 */       URL url = new URL(urlStr);
/*  27 */       conn = (HttpURLConnection)url.openConnection(Minecraft.getMinecraft().getProxy());
/*  28 */       conn.setDoInput(true);
/*  29 */       conn.setDoOutput(false);
/*  30 */       conn.connect();
/*     */       
/*  32 */       if (conn.getResponseCode() / 100 != 2) {
/*     */         
/*  34 */         if (conn.getErrorStream() != null)
/*     */         {
/*  36 */           Config.readAll(conn.getErrorStream());
/*     */         }
/*     */         
/*  39 */         throw new IOException("HTTP response: " + conn.getResponseCode());
/*     */       } 
/*     */ 
/*     */       
/*  43 */       InputStream in = conn.getInputStream();
/*  44 */       byte[] bytes = new byte[conn.getContentLength()];
/*  45 */       int pos = 0;
/*     */ 
/*     */       
/*     */       do {
/*  49 */         int len = in.read(bytes, pos, bytes.length - pos);
/*     */         
/*  51 */         if (len < 0)
/*     */         {
/*  53 */           throw new IOException("Input stream closed: " + urlStr);
/*     */         }
/*     */         
/*  56 */         pos += len;
/*     */       }
/*  58 */       while (pos < bytes.length);
/*     */       
/*  60 */       byte[] len1 = bytes;
/*  61 */       return len1;
/*     */     
/*     */     }
/*     */     finally {
/*     */       
/*  66 */       if (conn != null)
/*     */       {
/*  68 */         conn.disconnect();
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static String post(String urlStr, Map headers, byte[] content) throws IOException {
/*  75 */     HttpURLConnection conn = null;
/*     */ 
/*     */     
/*     */     try {
/*  79 */       URL url = new URL(urlStr);
/*  80 */       conn = (HttpURLConnection)url.openConnection(Minecraft.getMinecraft().getProxy());
/*  81 */       conn.setRequestMethod("POST");
/*     */       
/*  83 */       if (headers != null) {
/*     */         
/*  85 */         Set os = headers.keySet();
/*  86 */         Iterator<String> in = os.iterator();
/*     */         
/*  88 */         while (in.hasNext()) {
/*     */           
/*  90 */           String isr = in.next();
/*  91 */           String br = (String)headers.get(isr);
/*  92 */           conn.setRequestProperty(isr, br);
/*     */         } 
/*     */       } 
/*     */       
/*  96 */       conn.setRequestProperty("Content-Type", "text/plain");
/*  97 */       conn.setRequestProperty("Content-Length", content.length);
/*  98 */       conn.setRequestProperty("Content-Language", "en-US");
/*  99 */       conn.setUseCaches(false);
/* 100 */       conn.setDoInput(true);
/* 101 */       conn.setDoOutput(true);
/* 102 */       OutputStream os1 = conn.getOutputStream();
/* 103 */       os1.write(content);
/* 104 */       os1.flush();
/* 105 */       os1.close();
/* 106 */       InputStream in1 = conn.getInputStream();
/* 107 */       InputStreamReader isr1 = new InputStreamReader(in1, "ASCII");
/* 108 */       BufferedReader br1 = new BufferedReader(isr1);
/* 109 */       StringBuffer sb = new StringBuffer();
/*     */       
/*     */       String line;
/* 112 */       while ((line = br1.readLine()) != null) {
/*     */         
/* 114 */         sb.append(line);
/* 115 */         sb.append('\r');
/*     */       } 
/*     */       
/* 118 */       br1.close();
/* 119 */       String var11 = sb.toString();
/* 120 */       return var11;
/*     */     }
/*     */     finally {
/*     */       
/* 124 */       if (conn != null)
/*     */       {
/* 126 */         conn.disconnect();
/*     */       }
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\optifine\HttpUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */