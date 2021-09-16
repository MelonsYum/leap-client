/*     */ package optifine;
/*     */ 
/*     */ import java.io.ByteArrayOutputStream;
/*     */ import java.io.EOFException;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.nio.charset.Charset;
/*     */ import java.util.LinkedHashMap;
/*     */ 
/*     */ public class HttpPipelineReceiver
/*     */   extends Thread {
/*  12 */   private HttpPipelineConnection httpPipelineConnection = null;
/*  13 */   private static final Charset ASCII = Charset.forName("ASCII");
/*     */   
/*     */   private static final String HEADER_CONTENT_LENGTH = "Content-Length";
/*     */   private static final char CR = '\r';
/*     */   private static final char LF = '\n';
/*     */   
/*     */   public HttpPipelineReceiver(HttpPipelineConnection httpPipelineConnection) {
/*  20 */     super("HttpPipelineReceiver");
/*  21 */     this.httpPipelineConnection = httpPipelineConnection;
/*     */   }
/*     */ 
/*     */   
/*     */   public void run() {
/*  26 */     while (!Thread.interrupted()) {
/*     */       
/*  28 */       HttpPipelineRequest currentRequest = null;
/*     */ 
/*     */       
/*     */       try {
/*  32 */         currentRequest = this.httpPipelineConnection.getNextRequestReceive();
/*  33 */         InputStream e = this.httpPipelineConnection.getInputStream();
/*  34 */         HttpResponse resp = readResponse(e);
/*  35 */         this.httpPipelineConnection.onResponseReceived(currentRequest, resp);
/*     */       }
/*  37 */       catch (InterruptedException var4) {
/*     */         
/*     */         return;
/*     */       }
/*  41 */       catch (Exception var5) {
/*     */         
/*  43 */         this.httpPipelineConnection.onExceptionReceive(currentRequest, var5);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private HttpResponse readResponse(InputStream in) throws IOException {
/*  50 */     String statusLine = readLine(in);
/*  51 */     String[] parts = Config.tokenize(statusLine, " ");
/*     */     
/*  53 */     if (parts.length < 3)
/*     */     {
/*  55 */       throw new IOException("Invalid status line: " + statusLine);
/*     */     }
/*     */ 
/*     */     
/*  59 */     String http = parts[0];
/*  60 */     int status = Config.parseInt(parts[1], 0);
/*  61 */     String message = parts[2];
/*  62 */     LinkedHashMap<Object, Object> headers = new LinkedHashMap<>();
/*     */ 
/*     */     
/*     */     while (true) {
/*  66 */       String body = readLine(in);
/*     */ 
/*     */       
/*  69 */       if (body.length() <= 0) {
/*     */         
/*  71 */         byte[] body1 = null;
/*  72 */         String lenStr1 = (String)headers.get("Content-Length");
/*     */         
/*  74 */         if (lenStr1 != null) {
/*     */           
/*  76 */           int enc1 = Config.parseInt(lenStr1, -1);
/*     */           
/*  78 */           if (enc1 > 0)
/*     */           {
/*  80 */             body1 = new byte[enc1];
/*  81 */             readFull(body1, in);
/*     */           }
/*     */         
/*     */         } else {
/*     */           
/*  86 */           String enc = (String)headers.get("Transfer-Encoding");
/*     */           
/*  88 */           if (Config.equals(enc, "chunked"))
/*     */           {
/*  90 */             body1 = readContentChunked(in);
/*     */           }
/*     */         } 
/*     */         
/*  94 */         return new HttpResponse(status, statusLine, headers, body1);
/*     */       } 
/*     */       
/*  97 */       int lenStr = body.indexOf(":");
/*     */       
/*  99 */       if (lenStr > 0) {
/*     */         
/* 101 */         String enc = body.substring(0, lenStr).trim();
/* 102 */         String val = body.substring(lenStr + 1).trim();
/* 103 */         headers.put(enc, val);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private byte[] readContentChunked(InputStream in) throws IOException {
/*     */     int len;
/* 111 */     ByteArrayOutputStream baos = new ByteArrayOutputStream();
/*     */ 
/*     */ 
/*     */     
/*     */     do {
/* 116 */       String line = readLine(in);
/* 117 */       String[] parts = Config.tokenize(line, "; ");
/* 118 */       len = Integer.parseInt(parts[0], 16);
/* 119 */       byte[] buf = new byte[len];
/* 120 */       readFull(buf, in);
/* 121 */       baos.write(buf);
/* 122 */       readLine(in);
/*     */     }
/* 124 */     while (len != 0);
/*     */     
/* 126 */     return baos.toByteArray();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void readFull(byte[] buf, InputStream in) throws IOException {
/* 133 */     for (int pos = 0; pos < buf.length; pos += len) {
/*     */       
/* 135 */       int len = in.read(buf, pos, buf.length - pos);
/*     */       
/* 137 */       if (len < 0)
/*     */       {
/* 139 */         throw new EOFException();
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private String readLine(InputStream in) throws IOException {
/* 146 */     ByteArrayOutputStream baos = new ByteArrayOutputStream();
/* 147 */     int prev = -1;
/* 148 */     boolean hasCRLF = false;
/*     */ 
/*     */     
/*     */     while (true) {
/* 152 */       int bytes = in.read();
/*     */       
/* 154 */       if (bytes < 0) {
/*     */         break;
/*     */       }
/*     */ 
/*     */       
/* 159 */       baos.write(bytes);
/*     */       
/* 161 */       if (prev == 13 && bytes == 10) {
/*     */         
/* 163 */         hasCRLF = true;
/*     */         
/*     */         break;
/*     */       } 
/* 167 */       prev = bytes;
/*     */     } 
/*     */     
/* 170 */     byte[] bytes1 = baos.toByteArray();
/* 171 */     String str = new String(bytes1, ASCII);
/*     */     
/* 173 */     if (hasCRLF)
/*     */     {
/* 175 */       str = str.substring(0, str.length() - 2);
/*     */     }
/*     */     
/* 178 */     return str;
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\optifine\HttpPipelineReceiver.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */