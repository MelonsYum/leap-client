/*     */ package net.minecraft.command.server;
/*     */ 
/*     */ import com.google.common.base.Predicate;
/*     */ import com.google.common.collect.Iterators;
/*     */ import com.google.common.collect.Lists;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import net.minecraft.command.CommandBase;
/*     */ import net.minecraft.command.CommandException;
/*     */ import net.minecraft.command.ICommand;
/*     */ import net.minecraft.command.ICommandSender;
/*     */ import net.minecraft.command.WrongUsageException;
/*     */ import net.minecraft.entity.player.EntityPlayerMP;
/*     */ import net.minecraft.server.MinecraftServer;
/*     */ import net.minecraft.stats.Achievement;
/*     */ import net.minecraft.stats.AchievementList;
/*     */ import net.minecraft.stats.StatBase;
/*     */ import net.minecraft.stats.StatList;
/*     */ import net.minecraft.util.BlockPos;
/*     */ 
/*     */ public class CommandAchievement
/*     */   extends CommandBase {
/*     */   private static final String __OBFID = "CL_00000113";
/*     */   
/*     */   public String getCommandName() {
/*  27 */     return "achievement";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getRequiredPermissionLevel() {
/*  35 */     return 2;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getCommandUsage(ICommandSender sender) {
/*  40 */     return "commands.achievement.usage";
/*     */   }
/*     */ 
/*     */   
/*     */   public void processCommand(ICommandSender sender, String[] args) throws CommandException {
/*  45 */     if (args.length < 2)
/*     */     {
/*  47 */       throw new WrongUsageException("commands.achievement.usage", new Object[0]);
/*     */     }
/*     */ 
/*     */     
/*  51 */     final StatBase var3 = StatList.getOneShotStat(args[1]);
/*     */     
/*  53 */     if (var3 == null && !args[1].equals("*"))
/*     */     {
/*  55 */       throw new CommandException("commands.achievement.unknownAchievement", new Object[] { args[1] });
/*     */     }
/*     */ 
/*     */     
/*  59 */     final EntityPlayerMP var4 = (args.length >= 3) ? getPlayer(sender, args[2]) : getCommandSenderAsPlayer(sender);
/*  60 */     boolean var5 = args[0].equalsIgnoreCase("give");
/*  61 */     boolean var6 = args[0].equalsIgnoreCase("take");
/*     */     
/*  63 */     if (var5 || var6)
/*     */     {
/*  65 */       if (var3 == null) {
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*  70 */         if (var5)
/*     */         {
/*  72 */           Iterator<Achievement> var11 = AchievementList.achievementList.iterator();
/*     */           
/*  74 */           while (var11.hasNext()) {
/*     */             
/*  76 */             Achievement var12 = var11.next();
/*  77 */             var4.triggerAchievement((StatBase)var12);
/*     */           } 
/*     */           
/*  80 */           notifyOperators(sender, (ICommand)this, "commands.achievement.give.success.all", new Object[] { var4.getName() });
/*     */         }
/*  82 */         else if (var6)
/*     */         {
/*  84 */           Iterator<Achievement> var11 = Lists.reverse(AchievementList.achievementList).iterator();
/*     */           
/*  86 */           while (var11.hasNext()) {
/*     */             
/*  88 */             Achievement var12 = var11.next();
/*  89 */             var4.func_175145_a((StatBase)var12);
/*     */           } 
/*     */           
/*  92 */           notifyOperators(sender, (ICommand)this, "commands.achievement.take.success.all", new Object[] { var4.getName() });
/*     */         }
/*     */       
/*     */       } else {
/*     */         
/*  97 */         if (var3 instanceof Achievement) {
/*     */           
/*  99 */           Achievement var7 = (Achievement)var3;
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 104 */           if (var5) {
/*     */             
/* 106 */             if (var4.getStatFile().hasAchievementUnlocked(var7))
/*     */             {
/* 108 */               throw new CommandException("commands.achievement.alreadyHave", new Object[] { var4.getName(), var3.func_150955_j() });
/*     */             }
/*     */             ArrayList<Achievement> var8;
/* 111 */             for (var8 = Lists.newArrayList(); var7.parentAchievement != null && !var4.getStatFile().hasAchievementUnlocked(var7.parentAchievement); var7 = var7.parentAchievement)
/*     */             {
/* 113 */               var8.add(var7.parentAchievement);
/*     */             }
/*     */             
/* 116 */             Iterator<Achievement> var9 = Lists.reverse(var8).iterator();
/*     */             
/* 118 */             while (var9.hasNext())
/*     */             {
/* 120 */               Achievement var10 = var9.next();
/* 121 */               var4.triggerAchievement((StatBase)var10);
/*     */             }
/*     */           
/* 124 */           } else if (var6) {
/*     */             
/* 126 */             if (!var4.getStatFile().hasAchievementUnlocked(var7))
/*     */             {
/* 128 */               throw new CommandException("commands.achievement.dontHave", new Object[] { var4.getName(), var3.func_150955_j() });
/*     */             }
/*     */             
/* 131 */             ArrayList var8 = Lists.newArrayList((Iterator)Iterators.filter(AchievementList.achievementList.iterator(), new Predicate()
/*     */                   {
/*     */                     private static final String __OBFID = "CL_00002350";
/*     */                     
/*     */                     public boolean func_179605_a(Achievement p_179605_1_) {
/* 136 */                       return (var4.getStatFile().hasAchievementUnlocked(p_179605_1_) && p_179605_1_ != var3);
/*     */                     }
/*     */                     
/*     */                     public boolean apply(Object p_apply_1_) {
/* 140 */                       return func_179605_a((Achievement)p_apply_1_); }
/*     */                   }));
/* 142 */             for (; var7.parentAchievement != null && var4.getStatFile().hasAchievementUnlocked(var7.parentAchievement); var7 = var7.parentAchievement)
/*     */             {
/* 144 */               var8.remove(var7.parentAchievement);
/*     */             }
/* 146 */             Iterator<Achievement> var9 = var8.iterator();
/*     */             
/* 148 */             while (var9.hasNext()) {
/*     */               
/* 150 */               Achievement var10 = var9.next();
/* 151 */               var4.func_175145_a((StatBase)var10);
/*     */             } 
/*     */           } 
/*     */         } 
/*     */         
/* 156 */         if (var5) {
/*     */           
/* 158 */           var4.triggerAchievement(var3);
/* 159 */           notifyOperators(sender, (ICommand)this, "commands.achievement.give.success.one", new Object[] { var4.getName(), var3.func_150955_j() });
/*     */         }
/* 161 */         else if (var6) {
/*     */           
/* 163 */           var4.func_175145_a(var3);
/* 164 */           notifyOperators(sender, (ICommand)this, "commands.achievement.take.success.one", new Object[] { var3.func_150955_j(), var4.getName() });
/*     */         } 
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List addTabCompletionOptions(ICommandSender sender, String[] args, BlockPos pos) {
/* 174 */     if (args.length == 1)
/*     */     {
/* 176 */       return getListOfStringsMatchingLastWord(args, new String[] { "give", "take" });
/*     */     }
/* 178 */     if (args.length != 2)
/*     */     {
/* 180 */       return (args.length == 3) ? getListOfStringsMatchingLastWord(args, MinecraftServer.getServer().getAllUsernames()) : null;
/*     */     }
/*     */ 
/*     */     
/* 184 */     ArrayList<String> var4 = Lists.newArrayList();
/* 185 */     Iterator<StatBase> var5 = StatList.allStats.iterator();
/*     */     
/* 187 */     while (var5.hasNext()) {
/*     */       
/* 189 */       StatBase var6 = var5.next();
/* 190 */       var4.add(var6.statId);
/*     */     } 
/*     */     
/* 193 */     return func_175762_a(args, var4);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isUsernameIndex(String[] args, int index) {
/* 202 */     return (index == 2);
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\command\server\CommandAchievement.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */