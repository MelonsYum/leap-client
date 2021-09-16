/*    */ package optifine;
/*    */ 
/*    */ import java.net.Proxy;
/*    */ import java.util.LinkedHashMap;
/*    */ import java.util.Map;
/*    */ 
/*    */ public class HttpRequest
/*    */ {
/*  9 */   private String host = null;
/* 10 */   private int port = 0;
/*    */   
/*    */   private Proxy proxy;
/*    */   private String method;
/*    */   private String file;
/*    */   private String http;
/*    */   private Map<String, String> headers;
/*    */   private byte[] body;
/*    */   private int redirects;
/*    */   public static final String METHOD_GET = "GET";
/*    */   public static final String METHOD_HEAD = "HEAD";
/*    */   public static final String METHOD_POST = "POST";
/*    */   public static final String HTTP_1_0 = "HTTP/1.0";
/*    */   public static final String HTTP_1_1 = "HTTP/1.1";
/*    */   
/*    */   public HttpRequest(String host, int port, Proxy proxy, String method, String file, String http, Map<String, String> headers, byte[] body) {
/* 26 */     this.proxy = Proxy.NO_PROXY;
/* 27 */     this.method = null;
/* 28 */     this.file = null;
/* 29 */     this.http = null;
/* 30 */     this.headers = new LinkedHashMap<>();
/* 31 */     this.body = null;
/* 32 */     this.redirects = 0;
/* 33 */     this.host = host;
/* 34 */     this.port = port;
/* 35 */     this.proxy = proxy;
/* 36 */     this.method = method;
/* 37 */     this.file = file;
/* 38 */     this.http = http;
/* 39 */     this.headers = headers;
/* 40 */     this.body = body;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getHost() {
/* 45 */     return this.host;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getPort() {
/* 50 */     return this.port;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getMethod() {
/* 55 */     return this.method;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getFile() {
/* 60 */     return this.file;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getHttp() {
/* 65 */     return this.http;
/*    */   }
/*    */ 
/*    */   
/*    */   public Map<String, String> getHeaders() {
/* 70 */     return this.headers;
/*    */   }
/*    */ 
/*    */   
/*    */   public byte[] getBody() {
/* 75 */     return this.body;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getRedirects() {
/* 80 */     return this.redirects;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setRedirects(int redirects) {
/* 85 */     this.redirects = redirects;
/*    */   }
/*    */ 
/*    */   
/*    */   public Proxy getProxy() {
/* 90 */     return this.proxy;
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\optifine\HttpRequest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */