/*    */ package net.minecraft.command.server;
/*    */ 
/*    */ import com.google.common.collect.Lists;
/*    */ import com.mojang.authlib.GameProfile;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import net.minecraft.command.CommandBase;
/*    */ import net.minecraft.command.CommandException;
/*    */ import net.minecraft.command.ICommand;
/*    */ import net.minecraft.command.ICommandSender;
/*    */ import net.minecraft.command.WrongUsageException;
/*    */ import net.minecraft.server.MinecraftServer;
/*    */ import net.minecraft.util.BlockPos;
/*    */ 
/*    */ public class CommandOp
/*    */   extends CommandBase {
/*    */   private static final String __OBFID = "CL_00000694";
/*    */   
/*    */   public String getCommandName() {
/* 20 */     return "op";
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int getRequiredPermissionLevel() {
/* 28 */     return 3;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getCommandUsage(ICommandSender sender) {
/* 33 */     return "commands.op.usage";
/*    */   }
/*    */ 
/*    */   
/*    */   public void processCommand(ICommandSender sender, String[] args) throws CommandException {
/* 38 */     if (args.length == 1 && args[0].length() > 0) {
/*    */       
/* 40 */       MinecraftServer var3 = MinecraftServer.getServer();
/* 41 */       GameProfile var4 = var3.getPlayerProfileCache().getGameProfileForUsername(args[0]);
/*    */       
/* 43 */       if (var4 == null)
/*    */       {
/* 45 */         throw new CommandException("commands.op.failed", new Object[] { args[0] });
/*    */       }
/*    */ 
/*    */       
/* 49 */       var3.getConfigurationManager().addOp(var4);
/* 50 */       notifyOperators(sender, (ICommand)this, "commands.op.success", new Object[] { args[0] });
/*    */     
/*    */     }
/*    */     else {
/*    */       
/* 55 */       throw new WrongUsageException("commands.op.usage", new Object[0]);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public List addTabCompletionOptions(ICommandSender sender, String[] args, BlockPos pos) {
/* 61 */     if (args.length == 1) {
/*    */       
/* 63 */       String var4 = args[args.length - 1];
/* 64 */       ArrayList<String> var5 = Lists.newArrayList();
/* 65 */       GameProfile[] var6 = MinecraftServer.getServer().getGameProfiles();
/* 66 */       int var7 = var6.length;
/*    */       
/* 68 */       for (int var8 = 0; var8 < var7; var8++) {
/*    */         
/* 70 */         GameProfile var9 = var6[var8];
/*    */         
/* 72 */         if (!MinecraftServer.getServer().getConfigurationManager().canSendCommands(var9) && doesStringStartWith(var4, var9.getName()))
/*    */         {
/* 74 */           var5.add(var9.getName());
/*    */         }
/*    */       } 
/*    */       
/* 78 */       return var5;
/*    */     } 
/*    */ 
/*    */     
/* 82 */     return null;
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\command\server\CommandOp.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */