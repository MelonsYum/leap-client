/*    */ package leap.commands.impl;
/*    */ 
/*    */ import leap.Client;
/*    */ import leap.commands.Command;
/*    */ import leap.commands.CommandManager;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Help
/*    */   extends Command
/*    */ {
/*    */   public Help() {
/* 13 */     super("Help", "Displays all commands and descriptions", "help", new String[] { "h" });
/*    */   }
/*    */ 
/*    */   
/*    */   public void onCommand(String[] args, String command) {
/* 18 */     for (Command com : CommandManager.commands)
/* 19 */       Client.addChatMessage(String.valueOf(com.name) + " - " + com.description); 
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\leap\commands\impl\Help.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */