/*    */ package optifine;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.io.OutputStream;
/*    */ import java.net.InetSocketAddress;
/*    */ import java.net.Proxy;
/*    */ import java.net.Socket;
/*    */ import java.nio.charset.Charset;
/*    */ import java.util.Iterator;
/*    */ import java.util.Map;
/*    */ import java.util.Set;
/*    */ 
/*    */ public class HttpPipelineSender
/*    */   extends Thread {
/* 15 */   private HttpPipelineConnection httpPipelineConnection = null;
/*    */   private static final String CRLF = "\r\n";
/* 17 */   private static Charset ASCII = Charset.forName("ASCII");
/*    */ 
/*    */   
/*    */   public HttpPipelineSender(HttpPipelineConnection httpPipelineConnection) {
/* 21 */     super("HttpPipelineSender");
/* 22 */     this.httpPipelineConnection = httpPipelineConnection;
/*    */   }
/*    */ 
/*    */   
/*    */   public void run() {
/* 27 */     HttpPipelineRequest hpr = null;
/*    */ 
/*    */     
/*    */     try {
/* 31 */       connect();
/*    */       
/* 33 */       while (!Thread.interrupted())
/*    */       {
/* 35 */         hpr = this.httpPipelineConnection.getNextRequestSend();
/* 36 */         HttpRequest e = hpr.getHttpRequest();
/* 37 */         OutputStream out = this.httpPipelineConnection.getOutputStream();
/* 38 */         writeRequest(e, out);
/* 39 */         this.httpPipelineConnection.onRequestSent(hpr);
/*    */       }
/*    */     
/* 42 */     } catch (InterruptedException var4) {
/*    */       
/*    */       return;
/*    */     }
/* 46 */     catch (Exception var5) {
/*    */       
/* 48 */       this.httpPipelineConnection.onExceptionSend(hpr, var5);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   private void connect() throws IOException {
/* 54 */     String host = this.httpPipelineConnection.getHost();
/* 55 */     int port = this.httpPipelineConnection.getPort();
/* 56 */     Proxy proxy = this.httpPipelineConnection.getProxy();
/* 57 */     Socket socket = new Socket(proxy);
/* 58 */     socket.connect(new InetSocketAddress(host, port), 5000);
/* 59 */     this.httpPipelineConnection.setSocket(socket);
/*    */   }
/*    */ 
/*    */   
/*    */   private void writeRequest(HttpRequest req, OutputStream out) throws IOException {
/* 64 */     write(out, String.valueOf(req.getMethod()) + " " + req.getFile() + " " + req.getHttp() + "\r\n");
/* 65 */     Map<String, String> headers = req.getHeaders();
/* 66 */     Set<String> keySet = headers.keySet();
/* 67 */     Iterator<String> it = keySet.iterator();
/*    */     
/* 69 */     while (it.hasNext()) {
/*    */       
/* 71 */       String key = it.next();
/* 72 */       String val = req.getHeaders().get(key);
/* 73 */       write(out, String.valueOf(key) + ": " + val + "\r\n");
/*    */     } 
/*    */     
/* 76 */     write(out, "\r\n");
/*    */   }
/*    */ 
/*    */   
/*    */   private void write(OutputStream out, String str) throws IOException {
/* 81 */     byte[] bytes = str.getBytes(ASCII);
/* 82 */     out.write(bytes);
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\optifine\HttpPipelineSender.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */