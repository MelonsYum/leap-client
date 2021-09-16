/*     */ package net.minecraft.command.server;
/*     */ 
/*     */ import com.mojang.authlib.GameProfile;
/*     */ import java.util.List;
/*     */ import net.minecraft.command.CommandBase;
/*     */ import net.minecraft.command.CommandException;
/*     */ import net.minecraft.command.ICommand;
/*     */ import net.minecraft.command.ICommandSender;
/*     */ import net.minecraft.command.WrongUsageException;
/*     */ import net.minecraft.server.MinecraftServer;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.ChatComponentText;
/*     */ import net.minecraft.util.ChatComponentTranslation;
/*     */ import net.minecraft.util.IChatComponent;
/*     */ 
/*     */ public class CommandWhitelist extends CommandBase {
/*     */   private static final String __OBFID = "CL_00001186";
/*     */   
/*     */   public String getCommandName() {
/*  20 */     return "whitelist";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getRequiredPermissionLevel() {
/*  28 */     return 3;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getCommandUsage(ICommandSender sender) {
/*  33 */     return "commands.whitelist.usage";
/*     */   }
/*     */ 
/*     */   
/*     */   public void processCommand(ICommandSender sender, String[] args) throws CommandException {
/*  38 */     if (args.length < 1)
/*     */     {
/*  40 */       throw new WrongUsageException("commands.whitelist.usage", new Object[0]);
/*     */     }
/*     */ 
/*     */     
/*  44 */     MinecraftServer var3 = MinecraftServer.getServer();
/*     */     
/*  46 */     if (args[0].equals("on")) {
/*     */       
/*  48 */       var3.getConfigurationManager().setWhiteListEnabled(true);
/*  49 */       notifyOperators(sender, (ICommand)this, "commands.whitelist.enabled", new Object[0]);
/*     */     }
/*  51 */     else if (args[0].equals("off")) {
/*     */       
/*  53 */       var3.getConfigurationManager().setWhiteListEnabled(false);
/*  54 */       notifyOperators(sender, (ICommand)this, "commands.whitelist.disabled", new Object[0]);
/*     */     }
/*  56 */     else if (args[0].equals("list")) {
/*     */       
/*  58 */       sender.addChatMessage((IChatComponent)new ChatComponentTranslation("commands.whitelist.list", new Object[] { Integer.valueOf((var3.getConfigurationManager().getWhitelistedPlayerNames()).length), Integer.valueOf((var3.getConfigurationManager().getAvailablePlayerDat()).length) }));
/*  59 */       String[] var4 = var3.getConfigurationManager().getWhitelistedPlayerNames();
/*  60 */       sender.addChatMessage((IChatComponent)new ChatComponentText(joinNiceString((Object[])var4)));
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     }
/*  66 */     else if (args[0].equals("add")) {
/*     */       
/*  68 */       if (args.length < 2)
/*     */       {
/*  70 */         throw new WrongUsageException("commands.whitelist.add.usage", new Object[0]);
/*     */       }
/*     */       
/*  73 */       GameProfile var5 = var3.getPlayerProfileCache().getGameProfileForUsername(args[1]);
/*     */       
/*  75 */       if (var5 == null)
/*     */       {
/*  77 */         throw new CommandException("commands.whitelist.add.failed", new Object[] { args[1] });
/*     */       }
/*     */       
/*  80 */       var3.getConfigurationManager().addWhitelistedPlayer(var5);
/*  81 */       notifyOperators(sender, (ICommand)this, "commands.whitelist.add.success", new Object[] { args[1] });
/*     */     }
/*  83 */     else if (args[0].equals("remove")) {
/*     */       
/*  85 */       if (args.length < 2)
/*     */       {
/*  87 */         throw new WrongUsageException("commands.whitelist.remove.usage", new Object[0]);
/*     */       }
/*     */       
/*  90 */       GameProfile var5 = var3.getConfigurationManager().getWhitelistedPlayers().func_152706_a(args[1]);
/*     */       
/*  92 */       if (var5 == null)
/*     */       {
/*  94 */         throw new CommandException("commands.whitelist.remove.failed", new Object[] { args[1] });
/*     */       }
/*     */       
/*  97 */       var3.getConfigurationManager().removePlayerFromWhitelist(var5);
/*  98 */       notifyOperators(sender, (ICommand)this, "commands.whitelist.remove.success", new Object[] { args[1] });
/*     */     }
/* 100 */     else if (args[0].equals("reload")) {
/*     */       
/* 102 */       var3.getConfigurationManager().loadWhiteList();
/* 103 */       notifyOperators(sender, (ICommand)this, "commands.whitelist.reloaded", new Object[0]);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List addTabCompletionOptions(ICommandSender sender, String[] args, BlockPos pos) {
/* 111 */     if (args.length == 1)
/*     */     {
/* 113 */       return getListOfStringsMatchingLastWord(args, new String[] { "on", "off", "list", "add", "remove", "reload" });
/*     */     }
/*     */ 
/*     */     
/* 117 */     if (args.length == 2) {
/*     */       
/* 119 */       if (args[0].equals("remove"))
/*     */       {
/* 121 */         return getListOfStringsMatchingLastWord(args, MinecraftServer.getServer().getConfigurationManager().getWhitelistedPlayerNames());
/*     */       }
/*     */       
/* 124 */       if (args[0].equals("add"))
/*     */       {
/* 126 */         return getListOfStringsMatchingLastWord(args, MinecraftServer.getServer().getPlayerProfileCache().func_152654_a());
/*     */       }
/*     */     } 
/*     */     
/* 130 */     return null;
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\command\server\CommandWhitelist.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */