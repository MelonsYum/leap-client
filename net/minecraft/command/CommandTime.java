/*     */ package net.minecraft.command;
/*     */ 
/*     */ import java.util.List;
/*     */ import net.minecraft.server.MinecraftServer;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.world.WorldServer;
/*     */ 
/*     */ public class CommandTime
/*     */   extends CommandBase
/*     */ {
/*     */   private static final String __OBFID = "CL_00001183";
/*     */   
/*     */   public String getCommandName() {
/*  14 */     return "time";
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
/*  27 */     return "commands.time.usage";
/*     */   }
/*     */ 
/*     */   
/*     */   public void processCommand(ICommandSender sender, String[] args) throws CommandException {
/*  32 */     if (args.length > 1) {
/*     */ 
/*     */ 
/*     */       
/*  36 */       if (args[0].equals("set")) {
/*     */         int var3;
/*  38 */         if (args[1].equals("day")) {
/*     */           
/*  40 */           var3 = 1000;
/*     */         }
/*  42 */         else if (args[1].equals("night")) {
/*     */           
/*  44 */           var3 = 13000;
/*     */         }
/*     */         else {
/*     */           
/*  48 */           var3 = parseInt(args[1], 0);
/*     */         } 
/*     */         
/*  51 */         setTime(sender, var3);
/*  52 */         notifyOperators(sender, this, "commands.time.set", new Object[] { Integer.valueOf(var3) });
/*     */         
/*     */         return;
/*     */       } 
/*  56 */       if (args[0].equals("add")) {
/*     */         
/*  58 */         int var3 = parseInt(args[1], 0);
/*  59 */         addTime(sender, var3);
/*  60 */         notifyOperators(sender, this, "commands.time.added", new Object[] { Integer.valueOf(var3) });
/*     */         
/*     */         return;
/*     */       } 
/*  64 */       if (args[0].equals("query")) {
/*     */         
/*  66 */         if (args[1].equals("daytime")) {
/*     */           
/*  68 */           int var3 = (int)(sender.getEntityWorld().getWorldTime() % 2147483647L);
/*  69 */           sender.func_174794_a(CommandResultStats.Type.QUERY_RESULT, var3);
/*  70 */           notifyOperators(sender, this, "commands.time.query", new Object[] { Integer.valueOf(var3) });
/*     */           
/*     */           return;
/*     */         } 
/*  74 */         if (args[1].equals("gametime")) {
/*     */           
/*  76 */           int var3 = (int)(sender.getEntityWorld().getTotalWorldTime() % 2147483647L);
/*  77 */           sender.func_174794_a(CommandResultStats.Type.QUERY_RESULT, var3);
/*  78 */           notifyOperators(sender, this, "commands.time.query", new Object[] { Integer.valueOf(var3) });
/*     */           
/*     */           return;
/*     */         } 
/*     */       } 
/*     */     } 
/*  84 */     throw new WrongUsageException("commands.time.usage", new Object[0]);
/*     */   }
/*     */ 
/*     */   
/*     */   public List addTabCompletionOptions(ICommandSender sender, String[] args, BlockPos pos) {
/*  89 */     return (args.length == 1) ? getListOfStringsMatchingLastWord(args, new String[] { "set", "add", "query" }) : ((args.length == 2 && args[0].equals("set")) ? getListOfStringsMatchingLastWord(args, new String[] { "day", "night" }) : ((args.length == 2 && args[0].equals("query")) ? getListOfStringsMatchingLastWord(args, new String[] { "daytime", "gametime" }) : null));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void setTime(ICommandSender p_71552_1_, int p_71552_2_) {
/*  97 */     for (int var3 = 0; var3 < (MinecraftServer.getServer()).worldServers.length; var3++)
/*     */     {
/*  99 */       (MinecraftServer.getServer()).worldServers[var3].setWorldTime(p_71552_2_);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void addTime(ICommandSender p_71553_1_, int p_71553_2_) {
/* 108 */     for (int var3 = 0; var3 < (MinecraftServer.getServer()).worldServers.length; var3++) {
/*     */       
/* 110 */       WorldServer var4 = (MinecraftServer.getServer()).worldServers[var3];
/* 111 */       var4.setWorldTime(var4.getWorldTime() + p_71553_2_);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\command\CommandTime.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */