/*    */ package net.minecraft.command;
/*    */ 
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.player.EntityPlayerMP;
/*    */ 
/*    */ public class CommandKill
/*    */   extends CommandBase
/*    */ {
/*    */   private static final String __OBFID = "CL_00000570";
/*    */   
/*    */   public String getCommandName() {
/* 12 */     return "kill";
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int getRequiredPermissionLevel() {
/* 20 */     return 2;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getCommandUsage(ICommandSender sender) {
/* 25 */     return "commands.kill.usage";
/*    */   }
/*    */ 
/*    */   
/*    */   public void processCommand(ICommandSender sender, String[] args) throws CommandException {
/* 30 */     if (args.length == 0) {
/*    */       
/* 32 */       EntityPlayerMP var4 = getCommandSenderAsPlayer(sender);
/* 33 */       var4.func_174812_G();
/* 34 */       notifyOperators(sender, this, "commands.kill.successful", new Object[] { var4.getDisplayName() });
/*    */     }
/*    */     else {
/*    */       
/* 38 */       Entity var3 = func_175768_b(sender, args[0]);
/* 39 */       var3.func_174812_G();
/* 40 */       notifyOperators(sender, this, "commands.kill.successful", new Object[] { var3.getDisplayName() });
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean isUsernameIndex(String[] args, int index) {
/* 49 */     return (index == 0);
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\command\CommandKill.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */