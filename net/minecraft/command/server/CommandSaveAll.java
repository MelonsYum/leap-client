/*    */ package net.minecraft.command.server;
/*    */ 
/*    */ import net.minecraft.command.CommandBase;
/*    */ import net.minecraft.command.CommandException;
/*    */ import net.minecraft.command.ICommand;
/*    */ import net.minecraft.command.ICommandSender;
/*    */ import net.minecraft.server.MinecraftServer;
/*    */ import net.minecraft.util.ChatComponentTranslation;
/*    */ import net.minecraft.util.IChatComponent;
/*    */ import net.minecraft.world.MinecraftException;
/*    */ import net.minecraft.world.WorldServer;
/*    */ 
/*    */ public class CommandSaveAll
/*    */   extends CommandBase {
/*    */   private static final String __OBFID = "CL_00000826";
/*    */   
/*    */   public String getCommandName() {
/* 18 */     return "save-all";
/*    */   }
/*    */ 
/*    */   
/*    */   public String getCommandUsage(ICommandSender sender) {
/* 23 */     return "commands.save.usage";
/*    */   }
/*    */ 
/*    */   
/*    */   public void processCommand(ICommandSender sender, String[] args) throws CommandException {
/* 28 */     MinecraftServer var3 = MinecraftServer.getServer();
/* 29 */     sender.addChatMessage((IChatComponent)new ChatComponentTranslation("commands.save.start", new Object[0]));
/*    */     
/* 31 */     if (var3.getConfigurationManager() != null)
/*    */     {
/* 33 */       var3.getConfigurationManager().saveAllPlayerData();
/*    */     }
/*    */ 
/*    */ 
/*    */     
/*    */     try {
/*    */       int var4;
/*    */ 
/*    */       
/* 42 */       for (var4 = 0; var4 < var3.worldServers.length; var4++) {
/*    */         
/* 44 */         if (var3.worldServers[var4] != null) {
/*    */           
/* 46 */           WorldServer var5 = var3.worldServers[var4];
/* 47 */           boolean var6 = var5.disableLevelSaving;
/* 48 */           var5.disableLevelSaving = false;
/* 49 */           var5.saveAllChunks(true, null);
/* 50 */           var5.disableLevelSaving = var6;
/*    */         } 
/*    */       } 
/*    */       
/* 54 */       if (args.length > 0 && "flush".equals(args[0]))
/*    */       {
/* 56 */         sender.addChatMessage((IChatComponent)new ChatComponentTranslation("commands.save.flushStart", new Object[0]));
/*    */         
/* 58 */         for (var4 = 0; var4 < var3.worldServers.length; var4++) {
/*    */           
/* 60 */           if (var3.worldServers[var4] != null) {
/*    */             
/* 62 */             WorldServer var5 = var3.worldServers[var4];
/* 63 */             boolean var6 = var5.disableLevelSaving;
/* 64 */             var5.disableLevelSaving = false;
/* 65 */             var5.saveChunkData();
/* 66 */             var5.disableLevelSaving = var6;
/*    */           } 
/*    */         } 
/*    */         
/* 70 */         sender.addChatMessage((IChatComponent)new ChatComponentTranslation("commands.save.flushEnd", new Object[0]));
/*    */       }
/*    */     
/* 73 */     } catch (MinecraftException var7) {
/*    */       
/* 75 */       notifyOperators(sender, (ICommand)this, "commands.save.failed", new Object[] { var7.getMessage() });
/*    */       
/*    */       return;
/*    */     } 
/* 79 */     notifyOperators(sender, (ICommand)this, "commands.save.success", new Object[0]);
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\command\server\CommandSaveAll.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */