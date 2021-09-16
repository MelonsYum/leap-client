/*    */ package leap.irc;
/*    */ 
/*    */ import java.io.PrintWriter;
/*    */ import java.net.Socket;
/*    */ import java.util.Scanner;
/*    */ 
/*    */ public class IRCSender {
/*    */   Socket socket;
/*    */   Scanner in;
/*    */   PrintWriter out;
/*    */   Scanner inp;
/*    */   
/*    */   public IRCSender(Socket socket, Scanner in, PrintWriter out, Scanner inp) {
/* 14 */     this.socket = socket;
/* 15 */     this.in = in;
/* 16 */     this.out = out;
/* 17 */     this.inp = inp;
/*    */   }
/*    */   
/*    */   public void run() {
/*    */     while (true);
/*    */   }
/*    */   
/*    */   public void addMessage(String message) {
/* 25 */     this.out.println(message);
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\leap\irc\IRCSender.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */