/*     */ package net.minecraft.command.server;
/*     */ 
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.regex.Matcher;
/*     */ import java.util.regex.Pattern;
/*     */ import net.minecraft.command.CommandBase;
/*     */ import net.minecraft.command.CommandException;
/*     */ import net.minecraft.command.ICommand;
/*     */ import net.minecraft.command.ICommandSender;
/*     */ import net.minecraft.command.PlayerNotFoundException;
/*     */ import net.minecraft.command.WrongUsageException;
/*     */ import net.minecraft.entity.player.EntityPlayerMP;
/*     */ import net.minecraft.server.MinecraftServer;
/*     */ import net.minecraft.server.management.IPBanEntry;
/*     */ import net.minecraft.server.management.UserListEntry;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.IChatComponent;
/*     */ 
/*     */ public class CommandBanIp extends CommandBase {
/*  21 */   public static final Pattern field_147211_a = Pattern.compile("^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.([01]?\\d\\d?|2[0-4]\\d|25[0-5])$");
/*     */   
/*     */   private static final String __OBFID = "CL_00000139";
/*     */   
/*     */   public String getCommandName() {
/*  26 */     return "ban-ip";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getRequiredPermissionLevel() {
/*  34 */     return 3;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canCommandSenderUseCommand(ICommandSender sender) {
/*  42 */     return (MinecraftServer.getServer().getConfigurationManager().getBannedIPs().isLanServer() && super.canCommandSenderUseCommand(sender));
/*     */   }
/*     */ 
/*     */   
/*     */   public String getCommandUsage(ICommandSender sender) {
/*  47 */     return "commands.banip.usage";
/*     */   }
/*     */ 
/*     */   
/*     */   public void processCommand(ICommandSender sender, String[] args) throws CommandException {
/*  52 */     if (args.length >= 1 && args[0].length() > 1) {
/*     */       
/*  54 */       IChatComponent var3 = (args.length >= 2) ? getChatComponentFromNthArg(sender, args, 1) : null;
/*  55 */       Matcher var4 = field_147211_a.matcher(args[0]);
/*     */       
/*  57 */       if (var4.matches())
/*     */       {
/*  59 */         func_147210_a(sender, args[0], (var3 == null) ? null : var3.getUnformattedText());
/*     */       }
/*     */       else
/*     */       {
/*  63 */         EntityPlayerMP var5 = MinecraftServer.getServer().getConfigurationManager().getPlayerByUsername(args[0]);
/*     */         
/*  65 */         if (var5 == null)
/*     */         {
/*  67 */           throw new PlayerNotFoundException("commands.banip.invalid", new Object[0]);
/*     */         }
/*     */         
/*  70 */         func_147210_a(sender, var5.getPlayerIP(), (var3 == null) ? null : var3.getUnformattedText());
/*     */       }
/*     */     
/*     */     } else {
/*     */       
/*  75 */       throw new WrongUsageException("commands.banip.usage", new Object[0]);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public List addTabCompletionOptions(ICommandSender sender, String[] args, BlockPos pos) {
/*  81 */     return (args.length == 1) ? getListOfStringsMatchingLastWord(args, MinecraftServer.getServer().getAllUsernames()) : null;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void func_147210_a(ICommandSender p_147210_1_, String p_147210_2_, String p_147210_3_) {
/*  86 */     IPBanEntry var4 = new IPBanEntry(p_147210_2_, null, p_147210_1_.getName(), null, p_147210_3_);
/*  87 */     MinecraftServer.getServer().getConfigurationManager().getBannedIPs().addEntry((UserListEntry)var4);
/*  88 */     List var5 = MinecraftServer.getServer().getConfigurationManager().getPlayersMatchingAddress(p_147210_2_);
/*  89 */     String[] var6 = new String[var5.size()];
/*  90 */     int var7 = 0;
/*     */ 
/*     */     
/*  93 */     for (Iterator<EntityPlayerMP> var8 = var5.iterator(); var8.hasNext(); var6[var7++] = var9.getName()) {
/*     */       
/*  95 */       EntityPlayerMP var9 = var8.next();
/*  96 */       var9.playerNetServerHandler.kickPlayerFromServer("You have been IP banned.");
/*     */     } 
/*     */     
/*  99 */     if (var5.isEmpty()) {
/*     */       
/* 101 */       notifyOperators(p_147210_1_, (ICommand)this, "commands.banip.success", new Object[] { p_147210_2_ });
/*     */     }
/*     */     else {
/*     */       
/* 105 */       notifyOperators(p_147210_1_, (ICommand)this, "commands.banip.success.players", new Object[] { p_147210_2_, joinNiceString((Object[])var6) });
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\command\server\CommandBanIp.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */