/*    */ package optifine;
/*    */ 
/*    */ import java.util.LinkedHashMap;
/*    */ import java.util.Map;
/*    */ 
/*    */ public class HttpResponse
/*    */ {
/*  8 */   private int status = 0;
/*  9 */   private String statusLine = null;
/* 10 */   private Map<String, String> headers = new LinkedHashMap<>();
/* 11 */   private byte[] body = null;
/*    */ 
/*    */   
/*    */   public HttpResponse(int status, String statusLine, Map<String, String> headers, byte[] body) {
/* 15 */     this.status = status;
/* 16 */     this.statusLine = statusLine;
/* 17 */     this.headers = headers;
/* 18 */     this.body = body;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getStatus() {
/* 23 */     return this.status;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getStatusLine() {
/* 28 */     return this.statusLine;
/*    */   }
/*    */ 
/*    */   
/*    */   public Map getHeaders() {
/* 33 */     return this.headers;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getHeader(String key) {
/* 38 */     return this.headers.get(key);
/*    */   }
/*    */ 
/*    */   
/*    */   public byte[] getBody() {
/* 43 */     return this.body;
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\optifine\HttpResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */