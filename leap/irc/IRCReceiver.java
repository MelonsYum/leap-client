/*    */ package leap.irc;
/*    */ 
/*    */ import java.io.PrintWriter;
/*    */ import java.net.Socket;
/*    */ import java.util.Scanner;
/*    */ import leap.Client;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.util.ChatComponentText;
/*    */ import net.minecraft.util.IChatComponent;
/*    */ 
/*    */ 
/*    */ public class IRCReceiver
/*    */ {
/*    */   Socket socket;
/*    */   Scanner in;
/*    */   PrintWriter out;
/*    */   Scanner inp;
/*    */   boolean loggedIN;
/*    */   String username;
/*    */   IRCSender sender;
/*    */   
/*    */   public IRCReceiver(Socket socket, Scanner in, PrintWriter out, Scanner inp, String username) {
/* 23 */     this.socket = socket;
/* 24 */     this.in = in;
/* 25 */     this.out = out;
/* 26 */     this.inp = inp;
/* 27 */     this.loggedIN = false;
/* 28 */     this.username = username;
/*    */   }
/*    */   
/*    */   public void run() {
/*    */     while (true) {
/* 33 */       if (this.in.hasNextLine()) {
/* 34 */         String line = this.in.nextLine();
/* 35 */         if (line.startsWith("SUBMITNAME")) {
/* 36 */           this.out.println(String.valueOf(this.username) + ":" + Client.name); continue;
/* 37 */         }  if (line.startsWith("NAMEACCEPTED")) {
/* 38 */           System.out.println("Connected to IRC! Your Username is " + this.username);
/* 39 */           Client.INSTANCE.getIrc().setConnected(true);
/* 40 */           if (!this.loggedIN) {
/* 41 */             this.sender = new IRCSender(this.socket, this.in, this.out, this.inp);
/* 42 */             this.sender.run();
/*    */           }  continue;
/* 44 */         }  if (line.startsWith("MESSAGE") && 
/* 45 */           (Minecraft.getMinecraft()).thePlayer != null) if (Client.getModule("IRC").isEnabled()) {
/* 46 */             String l = line.substring(8);
/* 47 */             String username = l.split(" ")[0].split(":")[0];
/* 48 */             String client = l.split(" ")[0].split(":")[1];
/* 49 */             (Minecraft.getMinecraft()).thePlayer.addChatMessage((IChatComponent)new ChatComponentText("(§cIRC§f) §8(§a§n" + username + "§f - [§8" + client + "§f]" + "§8)§f: " + l.substring(username.length() + client.length() + 1)));
/*    */           } 
/*    */       
/*    */       } 
/*    */     } 
/*    */   }
/*    */   
/*    */   public IRCSender getSender() {
/* 57 */     return this.sender;
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\leap\irc\IRCReceiver.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */