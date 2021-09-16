/*    */ package leap.commands;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.Arrays;
/*    */ import java.util.List;
/*    */ import leap.Client;
/*    */ import leap.commands.impl.Bind;
/*    */ import leap.commands.impl.Help;
/*    */ import leap.commands.impl.Toggle;
/*    */ import leap.commands.impl.VClip;
/*    */ import leap.events.listeners.EventChat;
/*    */ 
/*    */ 
/*    */ public class CommandManager
/*    */ {
/* 16 */   public static List<Command> commands = new ArrayList<>();
/* 17 */   public String prefix = ".";
/*    */   
/*    */   public CommandManager() {
/* 20 */     setup();
/*    */   }
/*    */   
/*    */   public void setup() {
/* 24 */     commands.add(new Toggle());
/* 25 */     commands.add(new Bind());
/* 26 */     commands.add(new VClip());
/* 27 */     commands.add(new Help());
/*    */   }
/*    */   
/*    */   public void handleChat(EventChat event) {
/* 31 */     String message = event.getMessage();
/*    */     
/* 33 */     if (!message.startsWith(this.prefix) || message.equalsIgnoreCase(".")) {
/*    */       return;
/*    */     }
/* 36 */     event.setCancelled(true);
/*    */     
/* 38 */     message = message.substring(this.prefix.length());
/*    */     
/* 40 */     boolean foundCommand = false;
/*    */     
/* 42 */     if ((message.split(" ")).length > 0) {
/* 43 */       String commandName = message.split(" ")[0];
/*    */       
/* 45 */       for (Command c : commands) {
/* 46 */         if (c.aliases.contains(commandName) || c.name.equalsIgnoreCase(commandName)) {
/* 47 */           c.onCommand(Arrays.<String>copyOfRange(message.split(" "), 1, (message.split(" ")).length), message);
/* 48 */           foundCommand = true;
/*    */           break;
/*    */         } 
/*    */       } 
/*    */     } 
/* 53 */     if (!foundCommand)
/* 54 */       Client.addChatMessage("Could not find command!"); 
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\leap\commands\CommandManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */