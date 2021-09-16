/*     */ package net.minecraft.command;
/*     */ 
/*     */ import java.util.List;
/*     */ import net.minecraft.entity.player.EntityPlayerMP;
/*     */ import net.minecraft.server.MinecraftServer;
/*     */ import net.minecraft.util.BlockPos;
/*     */ 
/*     */ public class CommandXP
/*     */   extends CommandBase
/*     */ {
/*     */   private static final String __OBFID = "CL_00000398";
/*     */   
/*     */   public String getCommandName() {
/*  14 */     return "xp";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getRequiredPermissionLevel() {
/*  22 */     return 2;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getCommandUsage(ICommandSender sender) {
/*  27 */     return "commands.xp.usage";
/*     */   }
/*     */ 
/*     */   
/*     */   public void processCommand(ICommandSender sender, String[] args) throws CommandException {
/*  32 */     if (args.length <= 0)
/*     */     {
/*  34 */       throw new WrongUsageException("commands.xp.usage", new Object[0]);
/*     */     }
/*     */ 
/*     */     
/*  38 */     String var3 = args[0];
/*  39 */     boolean var4 = !(!var3.endsWith("l") && !var3.endsWith("L"));
/*     */     
/*  41 */     if (var4 && var3.length() > 1)
/*     */     {
/*  43 */       var3 = var3.substring(0, var3.length() - 1);
/*     */     }
/*     */     
/*  46 */     int var5 = parseInt(var3);
/*  47 */     boolean var6 = (var5 < 0);
/*     */     
/*  49 */     if (var6)
/*     */     {
/*  51 */       var5 *= -1;
/*     */     }
/*     */     
/*  54 */     EntityPlayerMP var7 = (args.length > 1) ? getPlayer(sender, args[1]) : getCommandSenderAsPlayer(sender);
/*     */     
/*  56 */     if (var4) {
/*     */       
/*  58 */       sender.func_174794_a(CommandResultStats.Type.QUERY_RESULT, var7.experienceLevel);
/*     */       
/*  60 */       if (var6)
/*     */       {
/*  62 */         var7.addExperienceLevel(-var5);
/*  63 */         notifyOperators(sender, this, "commands.xp.success.negative.levels", new Object[] { Integer.valueOf(var5), var7.getName() });
/*     */       }
/*     */       else
/*     */       {
/*  67 */         var7.addExperienceLevel(var5);
/*  68 */         notifyOperators(sender, this, "commands.xp.success.levels", new Object[] { Integer.valueOf(var5), var7.getName() });
/*     */       }
/*     */     
/*     */     } else {
/*     */       
/*  73 */       sender.func_174794_a(CommandResultStats.Type.QUERY_RESULT, var7.experienceTotal);
/*     */       
/*  75 */       if (var6)
/*     */       {
/*  77 */         throw new CommandException("commands.xp.failure.widthdrawXp", new Object[0]);
/*     */       }
/*     */       
/*  80 */       var7.addExperience(var5);
/*  81 */       notifyOperators(sender, this, "commands.xp.success", new Object[] { Integer.valueOf(var5), var7.getName() });
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public List addTabCompletionOptions(ICommandSender sender, String[] args, BlockPos pos) {
/*  88 */     return (args.length == 2) ? getListOfStringsMatchingLastWord(args, getAllUsernames()) : null;
/*     */   }
/*     */ 
/*     */   
/*     */   protected String[] getAllUsernames() {
/*  93 */     return MinecraftServer.getServer().getAllUsernames();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isUsernameIndex(String[] args, int index) {
/* 101 */     return (index == 1);
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\command\CommandXP.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */