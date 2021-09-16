/*     */ package net.minecraft.command;
/*     */ 
/*     */ import java.util.Arrays;
/*     */ import java.util.Collections;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import net.minecraft.event.ClickEvent;
/*     */ import net.minecraft.server.MinecraftServer;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.ChatComponentTranslation;
/*     */ import net.minecraft.util.EnumChatFormatting;
/*     */ import net.minecraft.util.IChatComponent;
/*     */ import net.minecraft.util.MathHelper;
/*     */ 
/*     */ public class CommandHelp
/*     */   extends CommandBase
/*     */ {
/*     */   private static final String __OBFID = "CL_00000529";
/*     */   
/*     */   public String getCommandName() {
/*  22 */     return "help";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getRequiredPermissionLevel() {
/*  30 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getCommandUsage(ICommandSender sender) {
/*  35 */     return "commands.help.usage";
/*     */   }
/*     */ 
/*     */   
/*     */   public List getCommandAliases() {
/*  40 */     return Arrays.asList(new String[] { "?" });
/*     */   }
/*     */   
/*     */   public void processCommand(ICommandSender sender, String[] args) throws CommandException {
/*     */     int var13;
/*  45 */     List<ICommand> var3 = getSortedPossibleCommands(sender);
/*  46 */     boolean var4 = true;
/*  47 */     int var5 = (var3.size() - 1) / 7;
/*  48 */     boolean var6 = false;
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/*  53 */       var13 = (args.length == 0) ? 0 : (parseInt(args[0], 1, var5 + 1) - 1);
/*     */     }
/*  55 */     catch (NumberInvalidException var12) {
/*     */       
/*  57 */       Map var8 = getCommands();
/*  58 */       ICommand var9 = (ICommand)var8.get(args[0]);
/*     */       
/*  60 */       if (var9 != null)
/*     */       {
/*  62 */         throw new WrongUsageException(var9.getCommandUsage(sender), new Object[0]);
/*     */       }
/*     */       
/*  65 */       if (MathHelper.parseIntWithDefault(args[0], -1) != -1)
/*     */       {
/*  67 */         throw var12;
/*     */       }
/*     */       
/*  70 */       throw new CommandNotFoundException();
/*     */     } 
/*     */     
/*  73 */     int var7 = Math.min((var13 + 1) * 7, var3.size());
/*  74 */     ChatComponentTranslation var14 = new ChatComponentTranslation("commands.help.header", new Object[] { Integer.valueOf(var13 + 1), Integer.valueOf(var5 + 1) });
/*  75 */     var14.getChatStyle().setColor(EnumChatFormatting.DARK_GREEN);
/*  76 */     sender.addChatMessage((IChatComponent)var14);
/*     */     
/*  78 */     for (int var15 = var13 * 7; var15 < var7; var15++) {
/*     */       
/*  80 */       ICommand var10 = var3.get(var15);
/*  81 */       ChatComponentTranslation var11 = new ChatComponentTranslation(var10.getCommandUsage(sender), new Object[0]);
/*  82 */       var11.getChatStyle().setChatClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, "/" + var10.getCommandName() + " "));
/*  83 */       sender.addChatMessage((IChatComponent)var11);
/*     */     } 
/*     */     
/*  86 */     if (var13 == 0 && sender instanceof net.minecraft.entity.player.EntityPlayer) {
/*     */       
/*  88 */       ChatComponentTranslation var16 = new ChatComponentTranslation("commands.help.footer", new Object[0]);
/*  89 */       var16.getChatStyle().setColor(EnumChatFormatting.GREEN);
/*  90 */       sender.addChatMessage((IChatComponent)var16);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected List getSortedPossibleCommands(ICommandSender p_71534_1_) {
/*  99 */     List<Comparable> var2 = MinecraftServer.getServer().getCommandManager().getPossibleCommands(p_71534_1_);
/* 100 */     Collections.sort(var2);
/* 101 */     return var2;
/*     */   }
/*     */ 
/*     */   
/*     */   protected Map getCommands() {
/* 106 */     return MinecraftServer.getServer().getCommandManager().getCommands();
/*     */   }
/*     */ 
/*     */   
/*     */   public List addTabCompletionOptions(ICommandSender sender, String[] args, BlockPos pos) {
/* 111 */     if (args.length == 1) {
/*     */       
/* 113 */       Set var4 = getCommands().keySet();
/* 114 */       return getListOfStringsMatchingLastWord(args, (String[])var4.toArray((Object[])new String[var4.size()]));
/*     */     } 
/*     */ 
/*     */     
/* 118 */     return null;
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\command\CommandHelp.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */