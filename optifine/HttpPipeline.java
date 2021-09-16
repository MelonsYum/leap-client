/*     */ package optifine;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.InterruptedIOException;
/*     */ import java.net.Proxy;
/*     */ import java.net.URL;
/*     */ import java.util.Collection;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.LinkedHashMap;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class HttpPipeline
/*     */ {
/*  15 */   private static Map mapConnections = new HashMap<>();
/*     */   
/*     */   public static final String HEADER_USER_AGENT = "User-Agent";
/*     */   public static final String HEADER_HOST = "Host";
/*     */   public static final String HEADER_ACCEPT = "Accept";
/*     */   public static final String HEADER_LOCATION = "Location";
/*     */   public static final String HEADER_KEEP_ALIVE = "Keep-Alive";
/*     */   public static final String HEADER_CONNECTION = "Connection";
/*     */   public static final String HEADER_VALUE_KEEP_ALIVE = "keep-alive";
/*     */   public static final String HEADER_TRANSFER_ENCODING = "Transfer-Encoding";
/*     */   public static final String HEADER_VALUE_CHUNKED = "chunked";
/*     */   
/*     */   public static void addRequest(String urlStr, HttpListener listener) throws IOException {
/*  28 */     addRequest(urlStr, listener, Proxy.NO_PROXY);
/*     */   }
/*     */ 
/*     */   
/*     */   public static void addRequest(String urlStr, HttpListener listener, Proxy proxy) throws IOException {
/*  33 */     HttpRequest hr = makeRequest(urlStr, proxy);
/*  34 */     HttpPipelineRequest hpr = new HttpPipelineRequest(hr, listener);
/*  35 */     addRequest(hpr);
/*     */   }
/*     */ 
/*     */   
/*     */   public static HttpRequest makeRequest(String urlStr, Proxy proxy) throws IOException {
/*  40 */     URL url = new URL(urlStr);
/*     */     
/*  42 */     if (!url.getProtocol().equals("http"))
/*     */     {
/*  44 */       throw new IOException("Only protocol http is supported: " + url);
/*     */     }
/*     */ 
/*     */     
/*  48 */     String file = url.getFile();
/*  49 */     String host = url.getHost();
/*  50 */     int port = url.getPort();
/*     */     
/*  52 */     if (port <= 0)
/*     */     {
/*  54 */       port = 80;
/*     */     }
/*     */     
/*  57 */     String method = "GET";
/*  58 */     String http = "HTTP/1.1";
/*  59 */     LinkedHashMap<Object, Object> headers = new LinkedHashMap<>();
/*  60 */     headers.put("User-Agent", "Java/" + System.getProperty("java.version"));
/*  61 */     headers.put("Host", host);
/*  62 */     headers.put("Accept", "text/html, image/gif, image/png");
/*  63 */     headers.put("Connection", "keep-alive");
/*  64 */     byte[] body = new byte[0];
/*  65 */     HttpRequest req = new HttpRequest(host, port, proxy, method, file, http, (Map)headers, body);
/*  66 */     return req;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static void addRequest(HttpPipelineRequest pr) {
/*  72 */     HttpRequest hr = pr.getHttpRequest();
/*     */     
/*  74 */     for (HttpPipelineConnection conn = getConnection(hr.getHost(), hr.getPort(), hr.getProxy()); !conn.addRequest(pr); conn = getConnection(hr.getHost(), hr.getPort(), hr.getProxy()))
/*     */     {
/*  76 */       removeConnection(hr.getHost(), hr.getPort(), hr.getProxy(), conn);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private static synchronized HttpPipelineConnection getConnection(String host, int port, Proxy proxy) {
/*  82 */     String key = makeConnectionKey(host, port, proxy);
/*  83 */     HttpPipelineConnection conn = (HttpPipelineConnection)mapConnections.get(key);
/*     */     
/*  85 */     if (conn == null) {
/*     */       
/*  87 */       conn = new HttpPipelineConnection(host, port, proxy);
/*  88 */       mapConnections.put(key, conn);
/*     */     } 
/*     */     
/*  91 */     return conn;
/*     */   }
/*     */ 
/*     */   
/*     */   private static synchronized void removeConnection(String host, int port, Proxy proxy, HttpPipelineConnection hpc) {
/*  96 */     String key = makeConnectionKey(host, port, proxy);
/*  97 */     HttpPipelineConnection conn = (HttpPipelineConnection)mapConnections.get(key);
/*     */     
/*  99 */     if (conn == hpc)
/*     */     {
/* 101 */       mapConnections.remove(key);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private static String makeConnectionKey(String host, int port, Proxy proxy) {
/* 107 */     String hostPort = String.valueOf(host) + ":" + port + "-" + proxy;
/* 108 */     return hostPort;
/*     */   }
/*     */ 
/*     */   
/*     */   public static byte[] get(String urlStr) throws IOException {
/* 113 */     return get(urlStr, Proxy.NO_PROXY);
/*     */   }
/*     */ 
/*     */   
/*     */   public static byte[] get(String urlStr, Proxy proxy) throws IOException {
/* 118 */     HttpRequest req = makeRequest(urlStr, proxy);
/* 119 */     HttpResponse resp = executeRequest(req);
/*     */     
/* 121 */     if (resp.getStatus() / 100 != 2)
/*     */     {
/* 123 */       throw new IOException("HTTP response: " + resp.getStatus());
/*     */     }
/*     */ 
/*     */     
/* 127 */     return resp.getBody();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static HttpResponse executeRequest(HttpRequest req) throws IOException {
/* 133 */     final HashMap<Object, Object> map = new HashMap<>();
/* 134 */     String KEY_RESPONSE = "Response";
/* 135 */     String KEY_EXCEPTION = "Exception";
/* 136 */     HttpListener l = new HttpListener()
/*     */       {
/*     */         public void finished(HttpRequest req, HttpResponse resp)
/*     */         {
/* 140 */           Map var3 = map;
/*     */           
/* 142 */           synchronized (map) {
/*     */             
/* 144 */             map.put("Response", resp);
/* 145 */             map.notifyAll();
/*     */           } 
/*     */         }
/*     */         
/*     */         public void failed(HttpRequest req, Exception e) {
/* 150 */           Map var3 = map;
/*     */           
/* 152 */           synchronized (map) {
/*     */             
/* 154 */             map.put("Exception", e);
/* 155 */             map.notifyAll();
/*     */           } 
/*     */         }
/*     */       };
/*     */     
/* 160 */     synchronized (map) {
/*     */       
/* 162 */       HttpPipelineRequest hpr = new HttpPipelineRequest(req, l);
/* 163 */       addRequest(hpr);
/*     */ 
/*     */       
/*     */       try {
/* 167 */         map.wait();
/*     */       }
/* 169 */       catch (InterruptedException var10) {
/*     */         
/* 171 */         throw new InterruptedIOException("Interrupted");
/*     */       } 
/*     */       
/* 174 */       Exception e = (Exception)map.get("Exception");
/*     */       
/* 176 */       if (e != null) {
/*     */         
/* 178 */         if (e instanceof IOException)
/*     */         {
/* 180 */           throw (IOException)e;
/*     */         }
/* 182 */         if (e instanceof RuntimeException)
/*     */         {
/* 184 */           throw (RuntimeException)e;
/*     */         }
/*     */ 
/*     */         
/* 188 */         throw new RuntimeException(e.getMessage(), e);
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 193 */       HttpResponse resp = (HttpResponse)map.get("Response");
/*     */       
/* 195 */       if (resp == null)
/*     */       {
/* 197 */         throw new IOException("Response is null");
/*     */       }
/*     */ 
/*     */       
/* 201 */       return resp;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean hasActiveRequests() {
/*     */     HttpPipelineConnection conn;
/* 209 */     Collection conns = mapConnections.values();
/* 210 */     Iterator<HttpPipelineConnection> it = conns.iterator();
/*     */ 
/*     */ 
/*     */     
/*     */     do {
/* 215 */       if (!it.hasNext())
/*     */       {
/* 217 */         return false;
/*     */       }
/*     */       
/* 220 */       conn = it.next();
/*     */     }
/* 222 */     while (!conn.hasActiveRequests());
/*     */     
/* 224 */     return true;
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\optifine\HttpPipeline.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */