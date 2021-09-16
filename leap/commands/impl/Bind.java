/*    */ package leap.commands.impl;
/*    */ 
/*    */ import leap.Client;
/*    */ import leap.commands.Command;
/*    */ import leap.modules.Module;
/*    */ import org.lwjgl.input.Keyboard;
/*    */ 
/*    */ public class Bind
/*    */   extends Command
/*    */ {
/*    */   public Bind() {
/* 12 */     super("Bind", "bind a module", "bind <name> <key>", new String[] { "b" });
/*    */   }
/*    */ 
/*    */   
/*    */   public void onCommand(String[] args, String command) {
/* 17 */     if (args.length == 2) {
/* 18 */       String moduleName = args[0];
/* 19 */       String keyName = args[1];
/*    */       
/* 21 */       boolean foundModule = false;
/*    */       
/* 23 */       for (Module module : Client.modules) {
/* 24 */         if (module.name.equalsIgnoreCase(moduleName)) {
/* 25 */           module.keyCode.setKeyCode(Keyboard.getKeyIndex(keyName.toUpperCase()));
/*    */           
/* 27 */           Client.addChatMessage(String.format("Bound %s to %s", new Object[] { module.name, Keyboard.getKeyName(module.getKey()) }));
/* 28 */           foundModule = true;
/*    */           break;
/*    */         } 
/*    */       } 
/* 32 */       if (!foundModule)
/* 33 */         Client.addChatMessage("Could not find module!"); 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\leap\commands\impl\Bind.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */