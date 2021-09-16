/*    */ package net.minecraft.command;
/*    */ 
/*    */ import java.util.List;
/*    */ import net.minecraft.entity.player.EntityPlayerMP;
/*    */ import net.minecraft.server.MinecraftServer;
/*    */ import net.minecraft.util.BlockPos;
/*    */ 
/*    */ public class CommandServerKick
/*    */   extends CommandBase
/*    */ {
/*    */   private static final String __OBFID = "CL_00000550";
/*    */   
/*    */   public String getCommandName() {
/* 14 */     return "kick";
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int getRequiredPermissionLevel() {
/* 22 */     return 3;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getCommandUsage(ICommandSender sender) {
/* 27 */     return "commands.kick.usage";
/*    */   }
/*    */ 
/*    */   
/*    */   public void processCommand(ICommandSender sender, String[] args) throws CommandException {
/* 32 */     if (args.length > 0 && args[0].length() > 1) {
/*    */       
/* 34 */       EntityPlayerMP var3 = MinecraftServer.getServer().getConfigurationManager().getPlayerByUsername(args[0]);
/* 35 */       String var4 = "Kicked by an operator.";
/* 36 */       boolean var5 = false;
/*    */       
/* 38 */       if (var3 == null)
/*    */       {
/* 40 */         throw new PlayerNotFoundException();
/*    */       }
/*    */ 
/*    */       
/* 44 */       if (args.length >= 2) {
/*    */         
/* 46 */         var4 = getChatComponentFromNthArg(sender, args, 1).getUnformattedText();
/* 47 */         var5 = true;
/*    */       } 
/*    */       
/* 50 */       var3.playerNetServerHandler.kickPlayerFromServer(var4);
/*    */       
/* 52 */       if (var5)
/*    */       {
/* 54 */         notifyOperators(sender, this, "commands.kick.success.reason", new Object[] { var3.getName(), var4 });
/*    */       }
/*    */       else
/*    */       {
/* 58 */         notifyOperators(sender, this, "commands.kick.success", new Object[] { var3.getName() });
/*    */       }
/*    */     
/*    */     }
/*    */     else {
/*    */       
/* 64 */       throw new WrongUsageException("commands.kick.usage", new Object[0]);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public List addTabCompletionOptions(ICommandSender sender, String[] args, BlockPos pos) {
/* 70 */     return (args.length >= 1) ? getListOfStringsMatchingLastWord(args, MinecraftServer.getServer().getAllUsernames()) : null;
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\command\CommandServerKick.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */