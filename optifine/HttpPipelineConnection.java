/*     */ package optifine;
/*     */ 
/*     */ import java.io.BufferedOutputStream;
/*     */ import java.io.EOFException;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.OutputStream;
/*     */ import java.net.Proxy;
/*     */ import java.net.Socket;
/*     */ import java.util.LinkedList;
/*     */ import java.util.List;
/*     */ import java.util.regex.Pattern;
/*     */ 
/*     */ public class HttpPipelineConnection
/*     */ {
/*     */   private String host;
/*     */   private int port;
/*     */   private Proxy proxy;
/*     */   private List<HttpPipelineRequest> listRequests;
/*     */   private List<HttpPipelineRequest> listRequestsSend;
/*     */   private Socket socket;
/*     */   private InputStream inputStream;
/*     */   private OutputStream outputStream;
/*     */   private HttpPipelineSender httpPipelineSender;
/*     */   private HttpPipelineReceiver httpPipelineReceiver;
/*     */   private int countRequests;
/*     */   private boolean responseReceived;
/*     */   private long keepaliveTimeoutMs;
/*     */   private int keepaliveMaxCount;
/*     */   private long timeLastActivityMs;
/*     */   private boolean terminated;
/*     */   private static final String LF = "\n";
/*     */   public static final int TIMEOUT_CONNECT_MS = 5000;
/*     */   public static final int TIMEOUT_READ_MS = 5000;
/*  35 */   private static final Pattern patternFullUrl = Pattern.compile("^[a-zA-Z]+://.*");
/*     */ 
/*     */   
/*     */   public HttpPipelineConnection(String host, int port) {
/*  39 */     this(host, port, Proxy.NO_PROXY);
/*     */   }
/*     */ 
/*     */   
/*     */   public HttpPipelineConnection(String host, int port, Proxy proxy) {
/*  44 */     this.host = null;
/*  45 */     this.port = 0;
/*  46 */     this.proxy = Proxy.NO_PROXY;
/*  47 */     this.listRequests = new LinkedList<>();
/*  48 */     this.listRequestsSend = new LinkedList<>();
/*  49 */     this.socket = null;
/*  50 */     this.inputStream = null;
/*  51 */     this.outputStream = null;
/*  52 */     this.httpPipelineSender = null;
/*  53 */     this.httpPipelineReceiver = null;
/*  54 */     this.countRequests = 0;
/*  55 */     this.responseReceived = false;
/*  56 */     this.keepaliveTimeoutMs = 5000L;
/*  57 */     this.keepaliveMaxCount = 1000;
/*  58 */     this.timeLastActivityMs = System.currentTimeMillis();
/*  59 */     this.terminated = false;
/*  60 */     this.host = host;
/*  61 */     this.port = port;
/*  62 */     this.proxy = proxy;
/*  63 */     this.httpPipelineSender = new HttpPipelineSender(this);
/*  64 */     this.httpPipelineSender.start();
/*  65 */     this.httpPipelineReceiver = new HttpPipelineReceiver(this);
/*  66 */     this.httpPipelineReceiver.start();
/*     */   }
/*     */ 
/*     */   
/*     */   public synchronized boolean addRequest(HttpPipelineRequest pr) {
/*  71 */     if (isClosed())
/*     */     {
/*  73 */       return false;
/*     */     }
/*     */ 
/*     */     
/*  77 */     addRequest(pr, this.listRequests);
/*  78 */     addRequest(pr, this.listRequestsSend);
/*  79 */     this.countRequests++;
/*  80 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void addRequest(HttpPipelineRequest pr, List<HttpPipelineRequest> list) {
/*  86 */     list.add(pr);
/*  87 */     notifyAll();
/*     */   }
/*     */ 
/*     */   
/*     */   public synchronized void setSocket(Socket s) throws IOException {
/*  92 */     if (!this.terminated) {
/*     */       
/*  94 */       if (this.socket != null)
/*     */       {
/*  96 */         throw new IllegalArgumentException("Already connected");
/*     */       }
/*     */ 
/*     */       
/* 100 */       this.socket = s;
/* 101 */       this.socket.setTcpNoDelay(true);
/* 102 */       this.inputStream = this.socket.getInputStream();
/* 103 */       this.outputStream = new BufferedOutputStream(this.socket.getOutputStream());
/* 104 */       onActivity();
/* 105 */       notifyAll();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public synchronized OutputStream getOutputStream() throws IOException, InterruptedException {
/* 112 */     while (this.outputStream == null) {
/*     */       
/* 114 */       checkTimeout();
/* 115 */       wait(1000L);
/*     */     } 
/*     */     
/* 118 */     return this.outputStream;
/*     */   }
/*     */ 
/*     */   
/*     */   public synchronized InputStream getInputStream() throws IOException, InterruptedException {
/* 123 */     while (this.inputStream == null) {
/*     */       
/* 125 */       checkTimeout();
/* 126 */       wait(1000L);
/*     */     } 
/*     */     
/* 129 */     return this.inputStream;
/*     */   }
/*     */ 
/*     */   
/*     */   public synchronized HttpPipelineRequest getNextRequestSend() throws InterruptedException, IOException {
/* 134 */     if (this.listRequestsSend.size() <= 0 && this.outputStream != null)
/*     */     {
/* 136 */       this.outputStream.flush();
/*     */     }
/*     */     
/* 139 */     return getNextRequest(this.listRequestsSend, true);
/*     */   }
/*     */ 
/*     */   
/*     */   public synchronized HttpPipelineRequest getNextRequestReceive() throws InterruptedException {
/* 144 */     return getNextRequest(this.listRequests, false);
/*     */   }
/*     */ 
/*     */   
/*     */   private HttpPipelineRequest getNextRequest(List<HttpPipelineRequest> list, boolean remove) throws InterruptedException {
/* 149 */     while (list.size() <= 0) {
/*     */       
/* 151 */       checkTimeout();
/* 152 */       wait(1000L);
/*     */     } 
/*     */     
/* 155 */     onActivity();
/*     */     
/* 157 */     if (remove)
/*     */     {
/* 159 */       return list.remove(0);
/*     */     }
/*     */ 
/*     */     
/* 163 */     return list.get(0);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void checkTimeout() {
/* 169 */     if (this.socket != null) {
/*     */       
/* 171 */       long timeoutMs = this.keepaliveTimeoutMs;
/*     */       
/* 173 */       if (this.listRequests.size() > 0)
/*     */       {
/* 175 */         timeoutMs = 5000L;
/*     */       }
/*     */       
/* 178 */       long timeNowMs = System.currentTimeMillis();
/*     */       
/* 180 */       if (timeNowMs > this.timeLastActivityMs + timeoutMs)
/*     */       {
/* 182 */         terminate(new InterruptedException("Timeout " + timeoutMs));
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void onActivity() {
/* 189 */     this.timeLastActivityMs = System.currentTimeMillis();
/*     */   }
/*     */ 
/*     */   
/*     */   public synchronized void onRequestSent(HttpPipelineRequest pr) {
/* 194 */     if (!this.terminated)
/*     */     {
/* 196 */       onActivity();
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public synchronized void onResponseReceived(HttpPipelineRequest pr, HttpResponse resp) {
/* 202 */     if (!this.terminated) {
/*     */       
/* 204 */       this.responseReceived = true;
/* 205 */       onActivity();
/*     */       
/* 207 */       if (this.listRequests.size() > 0 && this.listRequests.get(0) == pr) {
/*     */         
/* 209 */         this.listRequests.remove(0);
/* 210 */         pr.setClosed(true);
/* 211 */         String location = resp.getHeader("Location");
/*     */         
/* 213 */         if (resp.getStatus() / 100 == 3 && location != null && pr.getHttpRequest().getRedirects() < 5) {
/*     */           
/*     */           try
/*     */           {
/* 217 */             location = normalizeUrl(location, pr.getHttpRequest());
/* 218 */             HttpRequest listener1 = HttpPipeline.makeRequest(location, pr.getHttpRequest().getProxy());
/* 219 */             listener1.setRedirects(pr.getHttpRequest().getRedirects() + 1);
/* 220 */             HttpPipelineRequest hpr2 = new HttpPipelineRequest(listener1, pr.getHttpListener());
/* 221 */             HttpPipeline.addRequest(hpr2);
/*     */           }
/* 223 */           catch (IOException var6)
/*     */           {
/* 225 */             pr.getHttpListener().failed(pr.getHttpRequest(), var6);
/*     */           }
/*     */         
/*     */         } else {
/*     */           
/* 230 */           HttpListener listener = pr.getHttpListener();
/* 231 */           listener.finished(pr.getHttpRequest(), resp);
/*     */         } 
/*     */         
/* 234 */         checkResponseHeader(resp);
/*     */       }
/*     */       else {
/*     */         
/* 238 */         throw new IllegalArgumentException("Response out of order: " + pr);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private String normalizeUrl(String url, HttpRequest hr) {
/* 245 */     if (patternFullUrl.matcher(url).matches())
/*     */     {
/* 247 */       return url;
/*     */     }
/* 249 */     if (url.startsWith("//"))
/*     */     {
/* 251 */       return "http:" + url;
/*     */     }
/*     */ 
/*     */     
/* 255 */     String server = hr.getHost();
/*     */     
/* 257 */     if (hr.getPort() != 80)
/*     */     {
/* 259 */       server = String.valueOf(server) + ":" + hr.getPort();
/*     */     }
/*     */     
/* 262 */     if (url.startsWith("/"))
/*     */     {
/* 264 */       return "http://" + server + url;
/*     */     }
/*     */ 
/*     */     
/* 268 */     String file = hr.getFile();
/* 269 */     int pos = file.lastIndexOf("/");
/* 270 */     return (pos >= 0) ? ("http://" + server + file.substring(0, pos + 1) + url) : ("http://" + server + "/" + url);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void checkResponseHeader(HttpResponse resp) {
/* 277 */     String connStr = resp.getHeader("Connection");
/*     */     
/* 279 */     if (connStr != null && !connStr.toLowerCase().equals("keep-alive"))
/*     */     {
/* 281 */       terminate(new EOFException("Connection not keep-alive"));
/*     */     }
/*     */     
/* 284 */     String keepAliveStr = resp.getHeader("Keep-Alive");
/*     */     
/* 286 */     if (keepAliveStr != null) {
/*     */       
/* 288 */       String[] parts = Config.tokenize(keepAliveStr, ",;");
/*     */       
/* 290 */       for (int i = 0; i < parts.length; i++) {
/*     */         
/* 292 */         String part = parts[i];
/* 293 */         String[] tokens = split(part, '=');
/*     */         
/* 295 */         if (tokens.length >= 2) {
/*     */ 
/*     */ 
/*     */           
/* 299 */           if (tokens[0].equals("timeout")) {
/*     */             
/* 301 */             int max = Config.parseInt(tokens[1], -1);
/*     */             
/* 303 */             if (max > 0)
/*     */             {
/* 305 */               this.keepaliveTimeoutMs = (max * 1000);
/*     */             }
/*     */           } 
/*     */           
/* 309 */           if (tokens[0].equals("max")) {
/*     */             
/* 311 */             int max = Config.parseInt(tokens[1], -1);
/*     */             
/* 313 */             if (max > 0)
/*     */             {
/* 315 */               this.keepaliveMaxCount = max;
/*     */             }
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private String[] split(String str, char separator) {
/* 325 */     int pos = str.indexOf(separator);
/*     */     
/* 327 */     if (pos < 0)
/*     */     {
/* 329 */       return new String[] { str };
/*     */     }
/*     */ 
/*     */     
/* 333 */     String str1 = str.substring(0, pos);
/* 334 */     String str2 = str.substring(pos + 1);
/* 335 */     return new String[] { str1, str2 };
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public synchronized void onExceptionSend(HttpPipelineRequest pr, Exception e) {
/* 341 */     terminate(e);
/*     */   }
/*     */ 
/*     */   
/*     */   public synchronized void onExceptionReceive(HttpPipelineRequest pr, Exception e) {
/* 346 */     terminate(e);
/*     */   }
/*     */ 
/*     */   
/*     */   private synchronized void terminate(Exception e) {
/* 351 */     if (!this.terminated) {
/*     */       
/* 353 */       this.terminated = true;
/* 354 */       terminateRequests(e);
/*     */       
/* 356 */       if (this.httpPipelineSender != null)
/*     */       {
/* 358 */         this.httpPipelineSender.interrupt();
/*     */       }
/*     */       
/* 361 */       if (this.httpPipelineReceiver != null)
/*     */       {
/* 363 */         this.httpPipelineReceiver.interrupt();
/*     */       }
/*     */ 
/*     */       
/*     */       try {
/* 368 */         if (this.socket != null)
/*     */         {
/* 370 */           this.socket.close();
/*     */         }
/*     */       }
/* 373 */       catch (IOException iOException) {}
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 378 */       this.socket = null;
/* 379 */       this.inputStream = null;
/* 380 */       this.outputStream = null;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void terminateRequests(Exception e) {
/* 386 */     if (this.listRequests.size() > 0) {
/*     */ 
/*     */ 
/*     */       
/* 390 */       if (!this.responseReceived) {
/*     */         
/* 392 */         HttpPipelineRequest pr = this.listRequests.remove(0);
/* 393 */         pr.getHttpListener().failed(pr.getHttpRequest(), e);
/* 394 */         pr.setClosed(true);
/*     */       } 
/*     */       
/* 397 */       while (this.listRequests.size() > 0) {
/*     */         
/* 399 */         HttpPipelineRequest pr = this.listRequests.remove(0);
/* 400 */         HttpPipeline.addRequest(pr);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public synchronized boolean isClosed() {
/* 407 */     return this.terminated ? true : ((this.countRequests >= this.keepaliveMaxCount));
/*     */   }
/*     */ 
/*     */   
/*     */   public int getCountRequests() {
/* 412 */     return this.countRequests;
/*     */   }
/*     */ 
/*     */   
/*     */   public synchronized boolean hasActiveRequests() {
/* 417 */     return (this.listRequests.size() > 0);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getHost() {
/* 422 */     return this.host;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getPort() {
/* 427 */     return this.port;
/*     */   }
/*     */ 
/*     */   
/*     */   public Proxy getProxy() {
/* 432 */     return this.proxy;
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\optifine\HttpPipelineConnection.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */