/*    */ package net.minecraft.command;
/*    */ 
/*    */ import java.util.Iterator;
/*    */ import net.minecraft.entity.player.EntityPlayerMP;
/*    */ import net.minecraft.server.MinecraftServer;
/*    */ import net.minecraft.util.ChatComponentTranslation;
/*    */ import net.minecraft.world.WorldSettings;
/*    */ 
/*    */ public class CommandDefaultGameMode
/*    */   extends CommandGameMode
/*    */ {
/*    */   private static final String __OBFID = "CL_00000296";
/*    */   
/*    */   public String getCommandName() {
/* 15 */     return "defaultgamemode";
/*    */   }
/*    */ 
/*    */   
/*    */   public String getCommandUsage(ICommandSender sender) {
/* 20 */     return "commands.defaultgamemode.usage";
/*    */   }
/*    */ 
/*    */   
/*    */   public void processCommand(ICommandSender sender, String[] args) throws CommandException {
/* 25 */     if (args.length <= 0)
/*    */     {
/* 27 */       throw new WrongUsageException("commands.defaultgamemode.usage", new Object[0]);
/*    */     }
/*    */ 
/*    */     
/* 31 */     WorldSettings.GameType var3 = getGameModeFromCommand(sender, args[0]);
/* 32 */     setGameType(var3);
/* 33 */     notifyOperators(sender, this, "commands.defaultgamemode.success", new Object[] { new ChatComponentTranslation("gameMode." + var3.getName(), new Object[0]) });
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected void setGameType(WorldSettings.GameType p_71541_1_) {
/* 39 */     MinecraftServer var2 = MinecraftServer.getServer();
/* 40 */     var2.setGameType(p_71541_1_);
/*    */ 
/*    */     
/* 43 */     if (var2.getForceGamemode())
/*    */     {
/* 45 */       for (Iterator<EntityPlayerMP> var3 = (MinecraftServer.getServer().getConfigurationManager()).playerEntityList.iterator(); var3.hasNext(); var4.fallDistance = 0.0F) {
/*    */         
/* 47 */         EntityPlayerMP var4 = var3.next();
/* 48 */         var4.setGameType(p_71541_1_);
/*    */       } 
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\command\CommandDefaultGameMode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */