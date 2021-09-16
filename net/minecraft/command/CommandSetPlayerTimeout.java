/*    */ package net.minecraft.command;
/*    */ 
/*    */ import net.minecraft.server.MinecraftServer;
/*    */ 
/*    */ public class CommandSetPlayerTimeout
/*    */   extends CommandBase
/*    */ {
/*    */   private static final String __OBFID = "CL_00000999";
/*    */   
/*    */   public String getCommandName() {
/* 11 */     return "setidletimeout";
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int getRequiredPermissionLevel() {
/* 19 */     return 3;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getCommandUsage(ICommandSender sender) {
/* 24 */     return "commands.setidletimeout.usage";
/*    */   }
/*    */ 
/*    */   
/*    */   public void processCommand(ICommandSender sender, String[] args) throws CommandException {
/* 29 */     if (args.length != 1)
/*    */     {
/* 31 */       throw new WrongUsageException("commands.setidletimeout.usage", new Object[0]);
/*    */     }
/*    */ 
/*    */     
/* 35 */     int var3 = parseInt(args[0], 0);
/* 36 */     MinecraftServer.getServer().setPlayerIdleTimeout(var3);
/* 37 */     notifyOperators(sender, this, "commands.setidletimeout.success", new Object[] { Integer.valueOf(var3) });
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\command\CommandSetPlayerTimeout.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */