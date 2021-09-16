/*    */ package leap.commands.impl;
/*    */ 
/*    */ import leap.Client;
/*    */ import leap.commands.Command;
/*    */ import leap.modules.Module;
/*    */ 
/*    */ public class Toggle
/*    */   extends Command {
/*    */   public Toggle() {
/* 10 */     super("Toggle", "toggles a module", "toggle <name>", new String[] { "t" });
/*    */   }
/*    */ 
/*    */   
/*    */   public void onCommand(String[] args, String command) {
/* 15 */     if (args.length > 0) {
/* 16 */       String moduleName = args[0];
/*    */       
/* 18 */       boolean found = false;
/*    */       
/* 20 */       for (Module module : Client.modules) {
/* 21 */         if (module.name.toLowerCase().equals(moduleName)) {
/* 22 */           module.toggle();
/*    */           
/* 24 */           Client.addChatMessage(String.valueOf(module.isEnabled() ? "Enabled" : "Disabled") + " " + module.name);
/*    */           
/* 26 */           found = true;
/*    */           break;
/*    */         } 
/*    */       } 
/* 30 */       if (!found)
/* 31 */         Client.addChatMessage("Could not find module!"); 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\leap\commands\impl\Toggle.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */