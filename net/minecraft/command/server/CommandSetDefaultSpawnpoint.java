/*    */ package net.minecraft.command.server;
/*    */ 
/*    */ import java.util.List;
/*    */ import net.minecraft.command.CommandBase;
/*    */ import net.minecraft.command.CommandException;
/*    */ import net.minecraft.command.ICommand;
/*    */ import net.minecraft.command.ICommandSender;
/*    */ import net.minecraft.command.WrongUsageException;
/*    */ import net.minecraft.network.Packet;
/*    */ import net.minecraft.network.play.server.S05PacketSpawnPosition;
/*    */ import net.minecraft.server.MinecraftServer;
/*    */ import net.minecraft.util.BlockPos;
/*    */ 
/*    */ public class CommandSetDefaultSpawnpoint extends CommandBase {
/*    */   private static final String __OBFID = "CL_00000973";
/*    */   
/*    */   public String getCommandName() {
/* 18 */     return "setworldspawn";
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int getRequiredPermissionLevel() {
/* 26 */     return 2;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getCommandUsage(ICommandSender sender) {
/* 31 */     return "commands.setworldspawn.usage";
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void processCommand(ICommandSender sender, String[] args) throws CommandException {
/*    */     BlockPos var3;
/* 38 */     if (args.length == 0) {
/*    */       
/* 40 */       var3 = getCommandSenderAsPlayer(sender).getPosition();
/*    */     }
/*    */     else {
/*    */       
/* 44 */       if (args.length != 3 || sender.getEntityWorld() == null)
/*    */       {
/* 46 */         throw new WrongUsageException("commands.setworldspawn.usage", new Object[0]);
/*    */       }
/*    */       
/* 49 */       var3 = func_175757_a(sender, args, 0, true);
/*    */     } 
/*    */     
/* 52 */     sender.getEntityWorld().setSpawnLocation(var3);
/* 53 */     MinecraftServer.getServer().getConfigurationManager().sendPacketToAllPlayers((Packet)new S05PacketSpawnPosition(var3));
/* 54 */     notifyOperators(sender, (ICommand)this, "commands.setworldspawn.success", new Object[] { Integer.valueOf(var3.getX()), Integer.valueOf(var3.getY()), Integer.valueOf(var3.getZ()) });
/*    */   }
/*    */ 
/*    */   
/*    */   public List addTabCompletionOptions(ICommandSender sender, String[] args, BlockPos pos) {
/* 59 */     return (args.length > 0 && args.length <= 3) ? func_175771_a(args, 0, pos) : null;
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\command\server\CommandSetDefaultSpawnpoint.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */