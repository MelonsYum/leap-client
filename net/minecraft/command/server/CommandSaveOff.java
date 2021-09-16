/*    */ package net.minecraft.command.server;
/*    */ 
/*    */ import net.minecraft.command.CommandBase;
/*    */ import net.minecraft.command.CommandException;
/*    */ import net.minecraft.command.ICommand;
/*    */ import net.minecraft.command.ICommandSender;
/*    */ import net.minecraft.server.MinecraftServer;
/*    */ import net.minecraft.world.WorldServer;
/*    */ 
/*    */ public class CommandSaveOff
/*    */   extends CommandBase {
/*    */   private static final String __OBFID = "CL_00000847";
/*    */   
/*    */   public String getCommandName() {
/* 15 */     return "save-off";
/*    */   }
/*    */ 
/*    */   
/*    */   public String getCommandUsage(ICommandSender sender) {
/* 20 */     return "commands.save-off.usage";
/*    */   }
/*    */ 
/*    */   
/*    */   public void processCommand(ICommandSender sender, String[] args) throws CommandException {
/* 25 */     MinecraftServer var3 = MinecraftServer.getServer();
/* 26 */     boolean var4 = false;
/*    */     
/* 28 */     for (int var5 = 0; var5 < var3.worldServers.length; var5++) {
/*    */       
/* 30 */       if (var3.worldServers[var5] != null) {
/*    */         
/* 32 */         WorldServer var6 = var3.worldServers[var5];
/*    */         
/* 34 */         if (!var6.disableLevelSaving) {
/*    */           
/* 36 */           var6.disableLevelSaving = true;
/* 37 */           var4 = true;
/*    */         } 
/*    */       } 
/*    */     } 
/*    */     
/* 42 */     if (var4) {
/*    */       
/* 44 */       notifyOperators(sender, (ICommand)this, "commands.save.disabled", new Object[0]);
/*    */     }
/*    */     else {
/*    */       
/* 48 */       throw new CommandException("commands.save-off.alreadyOff", new Object[0]);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\command\server\CommandSaveOff.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */