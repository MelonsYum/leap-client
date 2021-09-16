/*    */ package leap.irc;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.io.PrintWriter;
/*    */ import java.net.Socket;
/*    */ import java.util.Scanner;
/*    */ 
/*    */ public class IRC {
/*    */   private String username;
/*    */   private IRCReceiver receiver;
/*    */   private boolean connected;
/*    */   
/*    */   public IRC(String username, String host, int port) {
/* 14 */     this.username = username;
/*    */     
/*    */     try {
/* 17 */       Socket socket = new Socket(host, port);
/* 18 */       Scanner in = new Scanner(socket.getInputStream());
/* 19 */       PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
/* 20 */       Scanner inp = new Scanner(System.in);
/*    */       
/* 22 */       this.receiver = new IRCReceiver(socket, in, out, inp, username);
/* 23 */       this.receiver.run();
/* 24 */     } catch (IOException e) {
/* 25 */       e.printStackTrace();
/*    */     } 
/*    */   }
/*    */   
/*    */   public String getUsername() {
/* 30 */     return this.username;
/*    */   }
/*    */   
/*    */   public IRCReceiver getReceiver() {
/* 34 */     return this.receiver;
/*    */   }
/*    */   
/*    */   public boolean isConnected() {
/* 38 */     return this.connected;
/*    */   }
/*    */   
/*    */   public void setConnected(boolean connected) {
/* 42 */     this.connected = connected;
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\leap\irc\IRC.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */