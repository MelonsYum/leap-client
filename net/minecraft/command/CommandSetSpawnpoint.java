/*    */ package net.minecraft.command;
/*    */ 
/*    */ import java.util.List;
/*    */ import net.minecraft.entity.player.EntityPlayerMP;
/*    */ import net.minecraft.server.MinecraftServer;
/*    */ import net.minecraft.util.BlockPos;
/*    */ 
/*    */ public class CommandSetSpawnpoint
/*    */   extends CommandBase
/*    */ {
/*    */   private static final String __OBFID = "CL_00001026";
/*    */   
/*    */   public String getCommandName() {
/* 14 */     return "spawnpoint";
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int getRequiredPermissionLevel() {
/* 22 */     return 2;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getCommandUsage(ICommandSender sender) {
/* 27 */     return "commands.spawnpoint.usage";
/*    */   }
/*    */ 
/*    */   
/*    */   public void processCommand(ICommandSender sender, String[] args) throws CommandException {
/* 32 */     if (args.length > 0 && args.length < 4)
/*    */     {
/* 34 */       throw new WrongUsageException("commands.spawnpoint.usage", new Object[0]);
/*    */     }
/*    */ 
/*    */     
/* 38 */     EntityPlayerMP var3 = (args.length > 0) ? getPlayer(sender, args[0]) : getCommandSenderAsPlayer(sender);
/* 39 */     BlockPos var4 = (args.length > 3) ? func_175757_a(sender, args, 1, true) : var3.getPosition();
/*    */     
/* 41 */     if (var3.worldObj != null) {
/*    */       
/* 43 */       var3.func_180473_a(var4, true);
/* 44 */       notifyOperators(sender, this, "commands.spawnpoint.success", new Object[] { var3.getName(), Integer.valueOf(var4.getX()), Integer.valueOf(var4.getY()), Integer.valueOf(var4.getZ()) });
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public List addTabCompletionOptions(ICommandSender sender, String[] args, BlockPos pos) {
/* 51 */     return (args.length == 1) ? getListOfStringsMatchingLastWord(args, MinecraftServer.getServer().getAllUsernames()) : ((args.length > 1 && args.length <= 4) ? func_175771_a(args, 1, pos) : null);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean isUsernameIndex(String[] args, int index) {
/* 59 */     return (index == 0);
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\command\CommandSetSpawnpoint.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */