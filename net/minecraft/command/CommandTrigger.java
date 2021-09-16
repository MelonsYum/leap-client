/*     */ package net.minecraft.command;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.player.EntityPlayerMP;
/*     */ import net.minecraft.scoreboard.IScoreObjectiveCriteria;
/*     */ import net.minecraft.scoreboard.Score;
/*     */ import net.minecraft.scoreboard.ScoreObjective;
/*     */ import net.minecraft.scoreboard.Scoreboard;
/*     */ import net.minecraft.server.MinecraftServer;
/*     */ import net.minecraft.util.BlockPos;
/*     */ 
/*     */ public class CommandTrigger
/*     */   extends CommandBase
/*     */ {
/*     */   private static final String __OBFID = "CL_00002337";
/*     */   
/*     */   public String getCommandName() {
/*  22 */     return "trigger";
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
/*  35 */     return "commands.trigger.usage";
/*     */   }
/*     */   
/*     */   public void processCommand(ICommandSender sender, String[] args) throws CommandException {
/*     */     EntityPlayerMP var3;
/*  40 */     if (args.length < 3)
/*     */     {
/*  42 */       throw new WrongUsageException("commands.trigger.usage", new Object[0]);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  48 */     if (sender instanceof EntityPlayerMP) {
/*     */       
/*  50 */       var3 = (EntityPlayerMP)sender;
/*     */     }
/*     */     else {
/*     */       
/*  54 */       Entity var4 = sender.getCommandSenderEntity();
/*     */       
/*  56 */       if (!(var4 instanceof EntityPlayerMP))
/*     */       {
/*  58 */         throw new CommandException("commands.trigger.invalidPlayer", new Object[0]);
/*     */       }
/*     */       
/*  61 */       var3 = (EntityPlayerMP)var4;
/*     */     } 
/*     */     
/*  64 */     Scoreboard var8 = MinecraftServer.getServer().worldServerForDimension(0).getScoreboard();
/*  65 */     ScoreObjective var5 = var8.getObjective(args[0]);
/*     */     
/*  67 */     if (var5 != null && var5.getCriteria() == IScoreObjectiveCriteria.field_178791_c) {
/*     */       
/*  69 */       int var6 = parseInt(args[2]);
/*     */       
/*  71 */       if (!var8.func_178819_b(var3.getName(), var5))
/*     */       {
/*  73 */         throw new CommandException("commands.trigger.invalidObjective", new Object[] { args[0] });
/*     */       }
/*     */ 
/*     */       
/*  77 */       Score var7 = var8.getValueFromObjective(var3.getName(), var5);
/*     */       
/*  79 */       if (var7.func_178816_g())
/*     */       {
/*  81 */         throw new CommandException("commands.trigger.disabled", new Object[] { args[0] });
/*     */       }
/*     */ 
/*     */       
/*  85 */       if ("set".equals(args[1])) {
/*     */         
/*  87 */         var7.setScorePoints(var6);
/*     */       }
/*     */       else {
/*     */         
/*  91 */         if (!"add".equals(args[1]))
/*     */         {
/*  93 */           throw new CommandException("commands.trigger.invalidMode", new Object[] { args[1] });
/*     */         }
/*     */         
/*  96 */         var7.increseScore(var6);
/*     */       } 
/*     */       
/*  99 */       var7.func_178815_a(true);
/*     */       
/* 101 */       if (var3.theItemInWorldManager.isCreative())
/*     */       {
/* 103 */         notifyOperators(sender, this, "commands.trigger.success", new Object[] { args[0], args[1], args[2] });
/*     */       
/*     */       }
/*     */     
/*     */     }
/*     */     else {
/*     */       
/* 110 */       throw new CommandException("commands.trigger.invalidObjective", new Object[] { args[0] });
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public List addTabCompletionOptions(ICommandSender sender, String[] args, BlockPos pos) {
/* 117 */     if (args.length == 1) {
/*     */       
/* 119 */       Scoreboard var4 = MinecraftServer.getServer().worldServerForDimension(0).getScoreboard();
/* 120 */       ArrayList<String> var5 = Lists.newArrayList();
/* 121 */       Iterator<ScoreObjective> var6 = var4.getScoreObjectives().iterator();
/*     */       
/* 123 */       while (var6.hasNext()) {
/*     */         
/* 125 */         ScoreObjective var7 = var6.next();
/*     */         
/* 127 */         if (var7.getCriteria() == IScoreObjectiveCriteria.field_178791_c)
/*     */         {
/* 129 */           var5.add(var7.getName());
/*     */         }
/*     */       } 
/*     */       
/* 133 */       return getListOfStringsMatchingLastWord(args, var5.<String>toArray(new String[var5.size()]));
/*     */     } 
/*     */ 
/*     */     
/* 137 */     return (args.length == 2) ? getListOfStringsMatchingLastWord(args, new String[] { "add", "set" }) : null;
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\command\CommandTrigger.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */