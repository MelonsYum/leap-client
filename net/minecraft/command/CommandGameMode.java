/*    */ package net.minecraft.command;
/*    */ 
/*    */ import java.util.List;
/*    */ import net.minecraft.entity.player.EntityPlayerMP;
/*    */ import net.minecraft.server.MinecraftServer;
/*    */ import net.minecraft.util.BlockPos;
/*    */ import net.minecraft.util.ChatComponentTranslation;
/*    */ import net.minecraft.util.IChatComponent;
/*    */ import net.minecraft.world.WorldSettings;
/*    */ 
/*    */ public class CommandGameMode
/*    */   extends CommandBase {
/*    */   private static final String __OBFID = "CL_00000448";
/*    */   
/*    */   public String getCommandName() {
/* 16 */     return "gamemode";
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int getRequiredPermissionLevel() {
/* 24 */     return 2;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getCommandUsage(ICommandSender sender) {
/* 29 */     return "commands.gamemode.usage";
/*    */   }
/*    */ 
/*    */   
/*    */   public void processCommand(ICommandSender sender, String[] args) throws CommandException {
/* 34 */     if (args.length <= 0)
/*    */     {
/* 36 */       throw new WrongUsageException("commands.gamemode.usage", new Object[0]);
/*    */     }
/*    */ 
/*    */     
/* 40 */     WorldSettings.GameType var3 = getGameModeFromCommand(sender, args[0]);
/* 41 */     EntityPlayerMP var4 = (args.length >= 2) ? getPlayer(sender, args[1]) : getCommandSenderAsPlayer(sender);
/* 42 */     var4.setGameType(var3);
/* 43 */     var4.fallDistance = 0.0F;
/*    */     
/* 45 */     if (sender.getEntityWorld().getGameRules().getGameRuleBooleanValue("sendCommandFeedback"))
/*    */     {
/* 47 */       var4.addChatMessage((IChatComponent)new ChatComponentTranslation("gameMode.changed", new Object[0]));
/*    */     }
/*    */     
/* 50 */     ChatComponentTranslation var5 = new ChatComponentTranslation("gameMode." + var3.getName(), new Object[0]);
/*    */     
/* 52 */     if (var4 != sender) {
/*    */       
/* 54 */       notifyOperators(sender, this, 1, "commands.gamemode.success.other", new Object[] { var4.getName(), var5 });
/*    */     }
/*    */     else {
/*    */       
/* 58 */       notifyOperators(sender, this, 1, "commands.gamemode.success.self", new Object[] { var5 });
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected WorldSettings.GameType getGameModeFromCommand(ICommandSender p_71539_1_, String p_71539_2_) throws CommandException {
/* 68 */     return (!p_71539_2_.equalsIgnoreCase(WorldSettings.GameType.SURVIVAL.getName()) && !p_71539_2_.equalsIgnoreCase("s")) ? ((!p_71539_2_.equalsIgnoreCase(WorldSettings.GameType.CREATIVE.getName()) && !p_71539_2_.equalsIgnoreCase("c")) ? ((!p_71539_2_.equalsIgnoreCase(WorldSettings.GameType.ADVENTURE.getName()) && !p_71539_2_.equalsIgnoreCase("a")) ? ((!p_71539_2_.equalsIgnoreCase(WorldSettings.GameType.SPECTATOR.getName()) && !p_71539_2_.equalsIgnoreCase("sp")) ? WorldSettings.getGameTypeById(parseInt(p_71539_2_, 0, (WorldSettings.GameType.values()).length - 2)) : WorldSettings.GameType.SPECTATOR) : WorldSettings.GameType.ADVENTURE) : WorldSettings.GameType.CREATIVE) : WorldSettings.GameType.SURVIVAL;
/*    */   }
/*    */ 
/*    */   
/*    */   public List addTabCompletionOptions(ICommandSender sender, String[] args, BlockPos pos) {
/* 73 */     return (args.length == 1) ? getListOfStringsMatchingLastWord(args, new String[] { "survival", "creative", "adventure", "spectator" }) : ((args.length == 2) ? getListOfStringsMatchingLastWord(args, getListOfPlayerUsernames()) : null);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected String[] getListOfPlayerUsernames() {
/* 81 */     return MinecraftServer.getServer().getAllUsernames();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean isUsernameIndex(String[] args, int index) {
/* 89 */     return (index == 1);
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\command\CommandGameMode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */