/*      */ package net.minecraft.command.server;
/*      */ 
/*      */ import com.google.common.collect.Lists;
/*      */ import com.google.common.collect.Sets;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Arrays;
/*      */ import java.util.Collection;
/*      */ import java.util.HashSet;
/*      */ import java.util.Iterator;
/*      */ import java.util.List;
/*      */ import java.util.Map;
/*      */ import java.util.Set;
/*      */ import net.minecraft.command.CommandBase;
/*      */ import net.minecraft.command.CommandException;
/*      */ import net.minecraft.command.CommandResultStats;
/*      */ import net.minecraft.command.ICommand;
/*      */ import net.minecraft.command.ICommandSender;
/*      */ import net.minecraft.command.SyntaxErrorException;
/*      */ import net.minecraft.command.WrongUsageException;
/*      */ import net.minecraft.entity.Entity;
/*      */ import net.minecraft.nbt.JsonToNBT;
/*      */ import net.minecraft.nbt.NBTBase;
/*      */ import net.minecraft.nbt.NBTException;
/*      */ import net.minecraft.nbt.NBTTagCompound;
/*      */ import net.minecraft.scoreboard.IScoreObjectiveCriteria;
/*      */ import net.minecraft.scoreboard.Score;
/*      */ import net.minecraft.scoreboard.ScoreObjective;
/*      */ import net.minecraft.scoreboard.ScorePlayerTeam;
/*      */ import net.minecraft.scoreboard.Scoreboard;
/*      */ import net.minecraft.scoreboard.Team;
/*      */ import net.minecraft.server.MinecraftServer;
/*      */ import net.minecraft.util.BlockPos;
/*      */ import net.minecraft.util.ChatComponentText;
/*      */ import net.minecraft.util.ChatComponentTranslation;
/*      */ import net.minecraft.util.EnumChatFormatting;
/*      */ import net.minecraft.util.IChatComponent;
/*      */ 
/*      */ public class CommandScoreboard extends CommandBase {
/*      */   private static final String __OBFID = "CL_00000896";
/*      */   
/*      */   public String getCommandName() {
/*   42 */     return "scoreboard";
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getRequiredPermissionLevel() {
/*   50 */     return 2;
/*      */   }
/*      */ 
/*      */   
/*      */   public String getCommandUsage(ICommandSender sender) {
/*   55 */     return "commands.scoreboard.usage";
/*      */   }
/*      */ 
/*      */   
/*      */   public void processCommand(ICommandSender sender, String[] args) throws CommandException {
/*   60 */     if (!func_175780_b(sender, args)) {
/*      */       
/*   62 */       if (args.length < 1)
/*      */       {
/*   64 */         throw new WrongUsageException("commands.scoreboard.usage", new Object[0]);
/*      */       }
/*      */ 
/*      */       
/*   68 */       if (args[0].equalsIgnoreCase("objectives")) {
/*      */         
/*   70 */         if (args.length == 1)
/*      */         {
/*   72 */           throw new WrongUsageException("commands.scoreboard.objectives.usage", new Object[0]);
/*      */         }
/*      */         
/*   75 */         if (args[1].equalsIgnoreCase("list"))
/*      */         {
/*   77 */           listObjectives(sender);
/*      */         }
/*   79 */         else if (args[1].equalsIgnoreCase("add"))
/*      */         {
/*   81 */           if (args.length < 4)
/*      */           {
/*   83 */             throw new WrongUsageException("commands.scoreboard.objectives.add.usage", new Object[0]);
/*      */           }
/*      */           
/*   86 */           addObjective(sender, args, 2);
/*      */         }
/*   88 */         else if (args[1].equalsIgnoreCase("remove"))
/*      */         {
/*   90 */           if (args.length != 3)
/*      */           {
/*   92 */             throw new WrongUsageException("commands.scoreboard.objectives.remove.usage", new Object[0]);
/*      */           }
/*      */           
/*   95 */           removeObjective(sender, args[2]);
/*      */         }
/*      */         else
/*      */         {
/*   99 */           if (!args[1].equalsIgnoreCase("setdisplay"))
/*      */           {
/*  101 */             throw new WrongUsageException("commands.scoreboard.objectives.usage", new Object[0]);
/*      */           }
/*      */           
/*  104 */           if (args.length != 3 && args.length != 4)
/*      */           {
/*  106 */             throw new WrongUsageException("commands.scoreboard.objectives.setdisplay.usage", new Object[0]);
/*      */           }
/*      */           
/*  109 */           setObjectiveDisplay(sender, args, 2);
/*      */         }
/*      */       
/*  112 */       } else if (args[0].equalsIgnoreCase("players")) {
/*      */         
/*  114 */         if (args.length == 1)
/*      */         {
/*  116 */           throw new WrongUsageException("commands.scoreboard.players.usage", new Object[0]);
/*      */         }
/*      */         
/*  119 */         if (args[1].equalsIgnoreCase("list"))
/*      */         {
/*  121 */           if (args.length > 3)
/*      */           {
/*  123 */             throw new WrongUsageException("commands.scoreboard.players.list.usage", new Object[0]);
/*      */           }
/*      */           
/*  126 */           listPlayers(sender, args, 2);
/*      */         }
/*  128 */         else if (args[1].equalsIgnoreCase("add"))
/*      */         {
/*  130 */           if (args.length < 5)
/*      */           {
/*  132 */             throw new WrongUsageException("commands.scoreboard.players.add.usage", new Object[0]);
/*      */           }
/*      */           
/*  135 */           setPlayer(sender, args, 2);
/*      */         }
/*  137 */         else if (args[1].equalsIgnoreCase("remove"))
/*      */         {
/*  139 */           if (args.length < 5)
/*      */           {
/*  141 */             throw new WrongUsageException("commands.scoreboard.players.remove.usage", new Object[0]);
/*      */           }
/*      */           
/*  144 */           setPlayer(sender, args, 2);
/*      */         }
/*  146 */         else if (args[1].equalsIgnoreCase("set"))
/*      */         {
/*  148 */           if (args.length < 5)
/*      */           {
/*  150 */             throw new WrongUsageException("commands.scoreboard.players.set.usage", new Object[0]);
/*      */           }
/*      */           
/*  153 */           setPlayer(sender, args, 2);
/*      */         }
/*  155 */         else if (args[1].equalsIgnoreCase("reset"))
/*      */         {
/*  157 */           if (args.length != 3 && args.length != 4)
/*      */           {
/*  159 */             throw new WrongUsageException("commands.scoreboard.players.reset.usage", new Object[0]);
/*      */           }
/*      */           
/*  162 */           resetPlayers(sender, args, 2);
/*      */         }
/*  164 */         else if (args[1].equalsIgnoreCase("enable"))
/*      */         {
/*  166 */           if (args.length != 4)
/*      */           {
/*  168 */             throw new WrongUsageException("commands.scoreboard.players.enable.usage", new Object[0]);
/*      */           }
/*      */           
/*  171 */           func_175779_n(sender, args, 2);
/*      */         }
/*  173 */         else if (args[1].equalsIgnoreCase("test"))
/*      */         {
/*  175 */           if (args.length != 5 && args.length != 6)
/*      */           {
/*  177 */             throw new WrongUsageException("commands.scoreboard.players.test.usage", new Object[0]);
/*      */           }
/*      */           
/*  180 */           func_175781_o(sender, args, 2);
/*      */         }
/*      */         else
/*      */         {
/*  184 */           if (!args[1].equalsIgnoreCase("operation"))
/*      */           {
/*  186 */             throw new WrongUsageException("commands.scoreboard.players.usage", new Object[0]);
/*      */           }
/*      */           
/*  189 */           if (args.length != 7)
/*      */           {
/*  191 */             throw new WrongUsageException("commands.scoreboard.players.operation.usage", new Object[0]);
/*      */           }
/*      */           
/*  194 */           func_175778_p(sender, args, 2);
/*      */         }
/*      */       
/*  197 */       } else if (args[0].equalsIgnoreCase("teams")) {
/*      */         
/*  199 */         if (args.length == 1)
/*      */         {
/*  201 */           throw new WrongUsageException("commands.scoreboard.teams.usage", new Object[0]);
/*      */         }
/*      */         
/*  204 */         if (args[1].equalsIgnoreCase("list")) {
/*      */           
/*  206 */           if (args.length > 3)
/*      */           {
/*  208 */             throw new WrongUsageException("commands.scoreboard.teams.list.usage", new Object[0]);
/*      */           }
/*      */           
/*  211 */           listTeams(sender, args, 2);
/*      */         }
/*  213 */         else if (args[1].equalsIgnoreCase("add")) {
/*      */           
/*  215 */           if (args.length < 3)
/*      */           {
/*  217 */             throw new WrongUsageException("commands.scoreboard.teams.add.usage", new Object[0]);
/*      */           }
/*      */           
/*  220 */           addTeam(sender, args, 2);
/*      */         }
/*  222 */         else if (args[1].equalsIgnoreCase("remove")) {
/*      */           
/*  224 */           if (args.length != 3)
/*      */           {
/*  226 */             throw new WrongUsageException("commands.scoreboard.teams.remove.usage", new Object[0]);
/*      */           }
/*      */           
/*  229 */           removeTeam(sender, args, 2);
/*      */         }
/*  231 */         else if (args[1].equalsIgnoreCase("empty")) {
/*      */           
/*  233 */           if (args.length != 3)
/*      */           {
/*  235 */             throw new WrongUsageException("commands.scoreboard.teams.empty.usage", new Object[0]);
/*      */           }
/*      */           
/*  238 */           emptyTeam(sender, args, 2);
/*      */         }
/*  240 */         else if (args[1].equalsIgnoreCase("join")) {
/*      */           
/*  242 */           if (args.length < 4 && (args.length != 3 || !(sender instanceof net.minecraft.entity.player.EntityPlayer)))
/*      */           {
/*  244 */             throw new WrongUsageException("commands.scoreboard.teams.join.usage", new Object[0]);
/*      */           }
/*      */           
/*  247 */           joinTeam(sender, args, 2);
/*      */         }
/*  249 */         else if (args[1].equalsIgnoreCase("leave")) {
/*      */           
/*  251 */           if (args.length < 3 && !(sender instanceof net.minecraft.entity.player.EntityPlayer))
/*      */           {
/*  253 */             throw new WrongUsageException("commands.scoreboard.teams.leave.usage", new Object[0]);
/*      */           }
/*      */           
/*  256 */           leaveTeam(sender, args, 2);
/*      */         }
/*      */         else {
/*      */           
/*  260 */           if (!args[1].equalsIgnoreCase("option"))
/*      */           {
/*  262 */             throw new WrongUsageException("commands.scoreboard.teams.usage", new Object[0]);
/*      */           }
/*      */           
/*  265 */           if (args.length != 4 && args.length != 5)
/*      */           {
/*  267 */             throw new WrongUsageException("commands.scoreboard.teams.option.usage", new Object[0]);
/*      */           }
/*      */           
/*  270 */           setTeamOption(sender, args, 2);
/*      */         } 
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean func_175780_b(ICommandSender p_175780_1_, String[] p_175780_2_) throws CommandException {
/*  279 */     int var3 = -1;
/*      */     
/*  281 */     for (int var4 = 0; var4 < p_175780_2_.length; var4++) {
/*      */       
/*  283 */       if (isUsernameIndex(p_175780_2_, var4) && "*".equals(p_175780_2_[var4])) {
/*      */         
/*  285 */         if (var3 >= 0)
/*      */         {
/*  287 */           throw new CommandException("commands.scoreboard.noMultiWildcard", new Object[0]);
/*      */         }
/*      */         
/*  290 */         var3 = var4;
/*      */       } 
/*      */     } 
/*      */     
/*  294 */     if (var3 < 0)
/*      */     {
/*  296 */       return false;
/*      */     }
/*      */ 
/*      */     
/*  300 */     ArrayList var12 = Lists.newArrayList(getScoreboard().getObjectiveNames());
/*  301 */     String var5 = p_175780_2_[var3];
/*  302 */     ArrayList<String> var6 = Lists.newArrayList();
/*  303 */     Iterator<String> var7 = var12.iterator();
/*      */     
/*  305 */     while (var7.hasNext()) {
/*      */       
/*  307 */       String var8 = var7.next();
/*  308 */       p_175780_2_[var3] = var8;
/*      */ 
/*      */       
/*      */       try {
/*  312 */         processCommand(p_175780_1_, p_175780_2_);
/*  313 */         var6.add(var8);
/*      */       }
/*  315 */       catch (CommandException var11) {
/*      */         
/*  317 */         ChatComponentTranslation var10 = new ChatComponentTranslation(var11.getMessage(), var11.getErrorOjbects());
/*  318 */         var10.getChatStyle().setColor(EnumChatFormatting.RED);
/*  319 */         p_175780_1_.addChatMessage((IChatComponent)var10);
/*      */       } 
/*      */     } 
/*      */     
/*  323 */     p_175780_2_[var3] = var5;
/*  324 */     p_175780_1_.func_174794_a(CommandResultStats.Type.AFFECTED_ENTITIES, var6.size());
/*      */     
/*  326 */     if (var6.size() == 0)
/*      */     {
/*  328 */       throw new WrongUsageException("commands.scoreboard.allMatchesFailed", new Object[0]);
/*      */     }
/*      */ 
/*      */     
/*  332 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected Scoreboard getScoreboard() {
/*  339 */     return MinecraftServer.getServer().worldServerForDimension(0).getScoreboard();
/*      */   }
/*      */ 
/*      */   
/*      */   protected ScoreObjective func_147189_a(String name, boolean edit) throws CommandException {
/*  344 */     Scoreboard var3 = getScoreboard();
/*  345 */     ScoreObjective var4 = var3.getObjective(name);
/*      */     
/*  347 */     if (var4 == null)
/*      */     {
/*  349 */       throw new CommandException("commands.scoreboard.objectiveNotFound", new Object[] { name });
/*      */     }
/*  351 */     if (edit && var4.getCriteria().isReadOnly())
/*      */     {
/*  353 */       throw new CommandException("commands.scoreboard.objectiveReadOnly", new Object[] { name });
/*      */     }
/*      */ 
/*      */     
/*  357 */     return var4;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected ScorePlayerTeam func_147183_a(String name) throws CommandException {
/*  363 */     Scoreboard var2 = getScoreboard();
/*  364 */     ScorePlayerTeam var3 = var2.getTeam(name);
/*      */     
/*  366 */     if (var3 == null)
/*      */     {
/*  368 */       throw new CommandException("commands.scoreboard.teamNotFound", new Object[] { name });
/*      */     }
/*      */ 
/*      */     
/*  372 */     return var3;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected void addObjective(ICommandSender sender, String[] args, int index) throws CommandException {
/*  378 */     String var4 = args[index++];
/*  379 */     String var5 = args[index++];
/*  380 */     Scoreboard var6 = getScoreboard();
/*  381 */     IScoreObjectiveCriteria var7 = (IScoreObjectiveCriteria)IScoreObjectiveCriteria.INSTANCES.get(var5);
/*      */     
/*  383 */     if (var7 == null)
/*      */     {
/*  385 */       throw new WrongUsageException("commands.scoreboard.objectives.add.wrongType", new Object[] { var5 });
/*      */     }
/*  387 */     if (var6.getObjective(var4) != null)
/*      */     {
/*  389 */       throw new CommandException("commands.scoreboard.objectives.add.alreadyExists", new Object[] { var4 });
/*      */     }
/*  391 */     if (var4.length() > 16)
/*      */     {
/*  393 */       throw new SyntaxErrorException("commands.scoreboard.objectives.add.tooLong", new Object[] { var4, Integer.valueOf(16) });
/*      */     }
/*  395 */     if (var4.length() == 0)
/*      */     {
/*  397 */       throw new WrongUsageException("commands.scoreboard.objectives.add.usage", new Object[0]);
/*      */     }
/*      */ 
/*      */     
/*  401 */     if (args.length > index) {
/*      */       
/*  403 */       String var8 = getChatComponentFromNthArg(sender, args, index).getUnformattedText();
/*      */       
/*  405 */       if (var8.length() > 32)
/*      */       {
/*  407 */         throw new SyntaxErrorException("commands.scoreboard.objectives.add.displayTooLong", new Object[] { var8, Integer.valueOf(32) });
/*      */       }
/*      */       
/*  410 */       if (var8.length() > 0)
/*      */       {
/*  412 */         var6.addScoreObjective(var4, var7).setDisplayName(var8);
/*      */       }
/*      */       else
/*      */       {
/*  416 */         var6.addScoreObjective(var4, var7);
/*      */       }
/*      */     
/*      */     } else {
/*      */       
/*  421 */       var6.addScoreObjective(var4, var7);
/*      */     } 
/*      */     
/*  424 */     notifyOperators(sender, (ICommand)this, "commands.scoreboard.objectives.add.success", new Object[] { var4 });
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected void addTeam(ICommandSender p_147185_1_, String[] p_147185_2_, int p_147185_3_) throws CommandException {
/*  430 */     String var4 = p_147185_2_[p_147185_3_++];
/*  431 */     Scoreboard var5 = getScoreboard();
/*      */     
/*  433 */     if (var5.getTeam(var4) != null)
/*      */     {
/*  435 */       throw new CommandException("commands.scoreboard.teams.add.alreadyExists", new Object[] { var4 });
/*      */     }
/*  437 */     if (var4.length() > 16)
/*      */     {
/*  439 */       throw new SyntaxErrorException("commands.scoreboard.teams.add.tooLong", new Object[] { var4, Integer.valueOf(16) });
/*      */     }
/*  441 */     if (var4.length() == 0)
/*      */     {
/*  443 */       throw new WrongUsageException("commands.scoreboard.teams.add.usage", new Object[0]);
/*      */     }
/*      */ 
/*      */     
/*  447 */     if (p_147185_2_.length > p_147185_3_) {
/*      */       
/*  449 */       String var6 = getChatComponentFromNthArg(p_147185_1_, p_147185_2_, p_147185_3_).getUnformattedText();
/*      */       
/*  451 */       if (var6.length() > 32)
/*      */       {
/*  453 */         throw new SyntaxErrorException("commands.scoreboard.teams.add.displayTooLong", new Object[] { var6, Integer.valueOf(32) });
/*      */       }
/*      */       
/*  456 */       if (var6.length() > 0)
/*      */       {
/*  458 */         var5.createTeam(var4).setTeamName(var6);
/*      */       }
/*      */       else
/*      */       {
/*  462 */         var5.createTeam(var4);
/*      */       }
/*      */     
/*      */     } else {
/*      */       
/*  467 */       var5.createTeam(var4);
/*      */     } 
/*      */     
/*  470 */     notifyOperators(p_147185_1_, (ICommand)this, "commands.scoreboard.teams.add.success", new Object[] { var4 });
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected void setTeamOption(ICommandSender p_147200_1_, String[] p_147200_2_, int p_147200_3_) throws CommandException {
/*  476 */     ScorePlayerTeam var4 = func_147183_a(p_147200_2_[p_147200_3_++]);
/*      */     
/*  478 */     if (var4 != null) {
/*      */       
/*  480 */       String var5 = p_147200_2_[p_147200_3_++].toLowerCase();
/*      */       
/*  482 */       if (!var5.equalsIgnoreCase("color") && !var5.equalsIgnoreCase("friendlyfire") && !var5.equalsIgnoreCase("seeFriendlyInvisibles") && !var5.equalsIgnoreCase("nametagVisibility") && !var5.equalsIgnoreCase("deathMessageVisibility"))
/*      */       {
/*  484 */         throw new WrongUsageException("commands.scoreboard.teams.option.usage", new Object[0]);
/*      */       }
/*  486 */       if (p_147200_2_.length == 4) {
/*      */         
/*  488 */         if (var5.equalsIgnoreCase("color"))
/*      */         {
/*  490 */           throw new WrongUsageException("commands.scoreboard.teams.option.noValue", new Object[] { var5, joinNiceStringFromCollection(EnumChatFormatting.getValidValues(true, false)) });
/*      */         }
/*  492 */         if (!var5.equalsIgnoreCase("friendlyfire") && !var5.equalsIgnoreCase("seeFriendlyInvisibles")) {
/*      */           
/*  494 */           if (!var5.equalsIgnoreCase("nametagVisibility") && !var5.equalsIgnoreCase("deathMessageVisibility"))
/*      */           {
/*  496 */             throw new WrongUsageException("commands.scoreboard.teams.option.usage", new Object[0]);
/*      */           }
/*      */ 
/*      */           
/*  500 */           throw new WrongUsageException("commands.scoreboard.teams.option.noValue", new Object[] { var5, joinNiceString(Team.EnumVisible.func_178825_a()) });
/*      */         } 
/*      */ 
/*      */ 
/*      */         
/*  505 */         throw new WrongUsageException("commands.scoreboard.teams.option.noValue", new Object[] { var5, joinNiceStringFromCollection(Arrays.asList(new String[] { "true", "false" })) });
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/*  510 */       String var6 = p_147200_2_[p_147200_3_];
/*      */       
/*  512 */       if (var5.equalsIgnoreCase("color")) {
/*      */         
/*  514 */         EnumChatFormatting var7 = EnumChatFormatting.getValueByName(var6);
/*      */         
/*  516 */         if (var7 == null || var7.isFancyStyling())
/*      */         {
/*  518 */           throw new WrongUsageException("commands.scoreboard.teams.option.noValue", new Object[] { var5, joinNiceStringFromCollection(EnumChatFormatting.getValidValues(true, false)) });
/*      */         }
/*      */         
/*  521 */         var4.func_178774_a(var7);
/*  522 */         var4.setNamePrefix(var7.toString());
/*  523 */         var4.setNameSuffix(EnumChatFormatting.RESET.toString());
/*      */       }
/*  525 */       else if (var5.equalsIgnoreCase("friendlyfire")) {
/*      */         
/*  527 */         if (!var6.equalsIgnoreCase("true") && !var6.equalsIgnoreCase("false"))
/*      */         {
/*  529 */           throw new WrongUsageException("commands.scoreboard.teams.option.noValue", new Object[] { var5, joinNiceStringFromCollection(Arrays.asList(new String[] { "true", "false" })) });
/*      */         }
/*      */         
/*  532 */         var4.setAllowFriendlyFire(var6.equalsIgnoreCase("true"));
/*      */       }
/*  534 */       else if (var5.equalsIgnoreCase("seeFriendlyInvisibles")) {
/*      */         
/*  536 */         if (!var6.equalsIgnoreCase("true") && !var6.equalsIgnoreCase("false"))
/*      */         {
/*  538 */           throw new WrongUsageException("commands.scoreboard.teams.option.noValue", new Object[] { var5, joinNiceStringFromCollection(Arrays.asList(new String[] { "true", "false" })) });
/*      */         }
/*      */         
/*  541 */         var4.setSeeFriendlyInvisiblesEnabled(var6.equalsIgnoreCase("true"));
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       }
/*  547 */       else if (var5.equalsIgnoreCase("nametagVisibility")) {
/*      */         
/*  549 */         Team.EnumVisible var8 = Team.EnumVisible.func_178824_a(var6);
/*      */         
/*  551 */         if (var8 == null)
/*      */         {
/*  553 */           throw new WrongUsageException("commands.scoreboard.teams.option.noValue", new Object[] { var5, joinNiceString(Team.EnumVisible.func_178825_a()) });
/*      */         }
/*      */         
/*  556 */         var4.func_178772_a(var8);
/*      */       }
/*  558 */       else if (var5.equalsIgnoreCase("deathMessageVisibility")) {
/*      */         
/*  560 */         Team.EnumVisible var8 = Team.EnumVisible.func_178824_a(var6);
/*      */         
/*  562 */         if (var8 == null)
/*      */         {
/*  564 */           throw new WrongUsageException("commands.scoreboard.teams.option.noValue", new Object[] { var5, joinNiceString(Team.EnumVisible.func_178825_a()) });
/*      */         }
/*      */         
/*  567 */         var4.func_178773_b(var8);
/*      */       } 
/*      */ 
/*      */       
/*  571 */       notifyOperators(p_147200_1_, (ICommand)this, "commands.scoreboard.teams.option.success", new Object[] { var5, var4.getRegisteredName(), var6 });
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected void removeTeam(ICommandSender p_147194_1_, String[] p_147194_2_, int p_147194_3_) throws CommandException {
/*  578 */     Scoreboard var4 = getScoreboard();
/*  579 */     ScorePlayerTeam var5 = func_147183_a(p_147194_2_[p_147194_3_]);
/*      */     
/*  581 */     if (var5 != null) {
/*      */       
/*  583 */       var4.removeTeam(var5);
/*  584 */       notifyOperators(p_147194_1_, (ICommand)this, "commands.scoreboard.teams.remove.success", new Object[] { var5.getRegisteredName() });
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   protected void listTeams(ICommandSender p_147186_1_, String[] p_147186_2_, int p_147186_3_) throws CommandException {
/*  590 */     Scoreboard var4 = getScoreboard();
/*      */     
/*  592 */     if (p_147186_2_.length > p_147186_3_) {
/*      */       
/*  594 */       ScorePlayerTeam var5 = func_147183_a(p_147186_2_[p_147186_3_]);
/*      */       
/*  596 */       if (var5 == null) {
/*      */         return;
/*      */       }
/*      */ 
/*      */       
/*  601 */       Collection var6 = var5.getMembershipCollection();
/*  602 */       p_147186_1_.func_174794_a(CommandResultStats.Type.QUERY_RESULT, var6.size());
/*      */       
/*  604 */       if (var6.size() <= 0)
/*      */       {
/*  606 */         throw new CommandException("commands.scoreboard.teams.list.player.empty", new Object[] { var5.getRegisteredName() });
/*      */       }
/*      */       
/*  609 */       ChatComponentTranslation var7 = new ChatComponentTranslation("commands.scoreboard.teams.list.player.count", new Object[] { Integer.valueOf(var6.size()), var5.getRegisteredName() });
/*  610 */       var7.getChatStyle().setColor(EnumChatFormatting.DARK_GREEN);
/*  611 */       p_147186_1_.addChatMessage((IChatComponent)var7);
/*  612 */       p_147186_1_.addChatMessage((IChatComponent)new ChatComponentText(joinNiceString(var6.toArray())));
/*      */     }
/*      */     else {
/*      */       
/*  616 */       Collection var9 = var4.getTeams();
/*  617 */       p_147186_1_.func_174794_a(CommandResultStats.Type.QUERY_RESULT, var9.size());
/*      */       
/*  619 */       if (var9.size() <= 0)
/*      */       {
/*  621 */         throw new CommandException("commands.scoreboard.teams.list.empty", new Object[0]);
/*      */       }
/*      */       
/*  624 */       ChatComponentTranslation var10 = new ChatComponentTranslation("commands.scoreboard.teams.list.count", new Object[] { Integer.valueOf(var9.size()) });
/*  625 */       var10.getChatStyle().setColor(EnumChatFormatting.DARK_GREEN);
/*  626 */       p_147186_1_.addChatMessage((IChatComponent)var10);
/*  627 */       Iterator<ScorePlayerTeam> var11 = var9.iterator();
/*      */       
/*  629 */       while (var11.hasNext()) {
/*      */         
/*  631 */         ScorePlayerTeam var8 = var11.next();
/*  632 */         p_147186_1_.addChatMessage((IChatComponent)new ChatComponentTranslation("commands.scoreboard.teams.list.entry", new Object[] { var8.getRegisteredName(), var8.func_96669_c(), Integer.valueOf(var8.getMembershipCollection().size()) }));
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   protected void joinTeam(ICommandSender p_147190_1_, String[] p_147190_2_, int p_147190_3_) throws CommandException {
/*  639 */     Scoreboard var4 = getScoreboard();
/*  640 */     String var5 = p_147190_2_[p_147190_3_++];
/*  641 */     HashSet<String> var6 = Sets.newHashSet();
/*  642 */     HashSet<String> var7 = Sets.newHashSet();
/*      */ 
/*      */     
/*  645 */     if (p_147190_1_ instanceof net.minecraft.entity.player.EntityPlayer && p_147190_3_ == p_147190_2_.length) {
/*      */       
/*  647 */       String var8 = getCommandSenderAsPlayer(p_147190_1_).getName();
/*      */       
/*  649 */       if (var4.func_151392_a(var8, var5))
/*      */       {
/*  651 */         var6.add(var8);
/*      */       }
/*      */       else
/*      */       {
/*  655 */         var7.add(var8);
/*      */       }
/*      */     
/*      */     } else {
/*      */       
/*  660 */       while (p_147190_3_ < p_147190_2_.length) {
/*      */         
/*  662 */         String var8 = p_147190_2_[p_147190_3_++];
/*      */         
/*  664 */         if (var8.startsWith("@")) {
/*      */           
/*  666 */           List var13 = func_175763_c(p_147190_1_, var8);
/*  667 */           Iterator<Entity> var10 = var13.iterator();
/*      */           
/*  669 */           while (var10.hasNext()) {
/*      */             
/*  671 */             Entity var11 = var10.next();
/*  672 */             String var12 = func_175758_e(p_147190_1_, var11.getUniqueID().toString());
/*      */             
/*  674 */             if (var4.func_151392_a(var12, var5)) {
/*      */               
/*  676 */               var6.add(var12);
/*      */               
/*      */               continue;
/*      */             } 
/*  680 */             var7.add(var12);
/*      */           } 
/*      */           
/*      */           continue;
/*      */         } 
/*      */         
/*  686 */         String var9 = func_175758_e(p_147190_1_, var8);
/*      */         
/*  688 */         if (var4.func_151392_a(var9, var5)) {
/*      */           
/*  690 */           var6.add(var9);
/*      */           
/*      */           continue;
/*      */         } 
/*  694 */         var7.add(var9);
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*  700 */     if (!var6.isEmpty()) {
/*      */       
/*  702 */       p_147190_1_.func_174794_a(CommandResultStats.Type.AFFECTED_ENTITIES, var6.size());
/*  703 */       notifyOperators(p_147190_1_, (ICommand)this, "commands.scoreboard.teams.join.success", new Object[] { Integer.valueOf(var6.size()), var5, joinNiceString(var6.toArray((Object[])new String[0])) });
/*      */     } 
/*      */     
/*  706 */     if (!var7.isEmpty())
/*      */     {
/*  708 */       throw new CommandException("commands.scoreboard.teams.join.failure", new Object[] { Integer.valueOf(var7.size()), var5, joinNiceString(var7.toArray(new String[0])) });
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   protected void leaveTeam(ICommandSender p_147199_1_, String[] p_147199_2_, int p_147199_3_) throws CommandException {
/*  714 */     Scoreboard var4 = getScoreboard();
/*  715 */     HashSet<String> var5 = Sets.newHashSet();
/*  716 */     HashSet<String> var6 = Sets.newHashSet();
/*      */ 
/*      */     
/*  719 */     if (p_147199_1_ instanceof net.minecraft.entity.player.EntityPlayer && p_147199_3_ == p_147199_2_.length) {
/*      */       
/*  721 */       String var7 = getCommandSenderAsPlayer(p_147199_1_).getName();
/*      */       
/*  723 */       if (var4.removePlayerFromTeams(var7))
/*      */       {
/*  725 */         var5.add(var7);
/*      */       }
/*      */       else
/*      */       {
/*  729 */         var6.add(var7);
/*      */       }
/*      */     
/*      */     } else {
/*      */       
/*  734 */       while (p_147199_3_ < p_147199_2_.length) {
/*      */         
/*  736 */         String var7 = p_147199_2_[p_147199_3_++];
/*      */         
/*  738 */         if (var7.startsWith("@")) {
/*      */           
/*  740 */           List var12 = func_175763_c(p_147199_1_, var7);
/*  741 */           Iterator<Entity> var9 = var12.iterator();
/*      */           
/*  743 */           while (var9.hasNext()) {
/*      */             
/*  745 */             Entity var10 = var9.next();
/*  746 */             String var11 = func_175758_e(p_147199_1_, var10.getUniqueID().toString());
/*      */             
/*  748 */             if (var4.removePlayerFromTeams(var11)) {
/*      */               
/*  750 */               var5.add(var11);
/*      */               
/*      */               continue;
/*      */             } 
/*  754 */             var6.add(var11);
/*      */           } 
/*      */           
/*      */           continue;
/*      */         } 
/*      */         
/*  760 */         String var8 = func_175758_e(p_147199_1_, var7);
/*      */         
/*  762 */         if (var4.removePlayerFromTeams(var8)) {
/*      */           
/*  764 */           var5.add(var8);
/*      */           
/*      */           continue;
/*      */         } 
/*  768 */         var6.add(var8);
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*  774 */     if (!var5.isEmpty()) {
/*      */       
/*  776 */       p_147199_1_.func_174794_a(CommandResultStats.Type.AFFECTED_ENTITIES, var5.size());
/*  777 */       notifyOperators(p_147199_1_, (ICommand)this, "commands.scoreboard.teams.leave.success", new Object[] { Integer.valueOf(var5.size()), joinNiceString(var5.toArray((Object[])new String[0])) });
/*      */     } 
/*      */     
/*  780 */     if (!var6.isEmpty())
/*      */     {
/*  782 */       throw new CommandException("commands.scoreboard.teams.leave.failure", new Object[] { Integer.valueOf(var6.size()), joinNiceString(var6.toArray(new String[0])) });
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   protected void emptyTeam(ICommandSender p_147188_1_, String[] p_147188_2_, int p_147188_3_) throws CommandException {
/*  788 */     Scoreboard var4 = getScoreboard();
/*  789 */     ScorePlayerTeam var5 = func_147183_a(p_147188_2_[p_147188_3_]);
/*      */     
/*  791 */     if (var5 != null) {
/*      */       
/*  793 */       ArrayList var6 = Lists.newArrayList(var5.getMembershipCollection());
/*  794 */       p_147188_1_.func_174794_a(CommandResultStats.Type.AFFECTED_ENTITIES, var6.size());
/*      */       
/*  796 */       if (var6.isEmpty())
/*      */       {
/*  798 */         throw new CommandException("commands.scoreboard.teams.empty.alreadyEmpty", new Object[] { var5.getRegisteredName() });
/*      */       }
/*      */ 
/*      */       
/*  802 */       Iterator<String> var7 = var6.iterator();
/*      */       
/*  804 */       while (var7.hasNext()) {
/*      */         
/*  806 */         String var8 = var7.next();
/*  807 */         var4.removePlayerFromTeam(var8, var5);
/*      */       } 
/*      */       
/*  810 */       notifyOperators(p_147188_1_, (ICommand)this, "commands.scoreboard.teams.empty.success", new Object[] { Integer.valueOf(var6.size()), var5.getRegisteredName() });
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected void removeObjective(ICommandSender p_147191_1_, String p_147191_2_) throws CommandException {
/*  817 */     Scoreboard var3 = getScoreboard();
/*  818 */     ScoreObjective var4 = func_147189_a(p_147191_2_, false);
/*  819 */     var3.func_96519_k(var4);
/*  820 */     notifyOperators(p_147191_1_, (ICommand)this, "commands.scoreboard.objectives.remove.success", new Object[] { p_147191_2_ });
/*      */   }
/*      */ 
/*      */   
/*      */   protected void listObjectives(ICommandSender p_147196_1_) throws CommandException {
/*  825 */     Scoreboard var2 = getScoreboard();
/*  826 */     Collection var3 = var2.getScoreObjectives();
/*      */     
/*  828 */     if (var3.size() <= 0)
/*      */     {
/*  830 */       throw new CommandException("commands.scoreboard.objectives.list.empty", new Object[0]);
/*      */     }
/*      */ 
/*      */     
/*  834 */     ChatComponentTranslation var4 = new ChatComponentTranslation("commands.scoreboard.objectives.list.count", new Object[] { Integer.valueOf(var3.size()) });
/*  835 */     var4.getChatStyle().setColor(EnumChatFormatting.DARK_GREEN);
/*  836 */     p_147196_1_.addChatMessage((IChatComponent)var4);
/*  837 */     Iterator<ScoreObjective> var5 = var3.iterator();
/*      */     
/*  839 */     while (var5.hasNext()) {
/*      */       
/*  841 */       ScoreObjective var6 = var5.next();
/*  842 */       p_147196_1_.addChatMessage((IChatComponent)new ChatComponentTranslation("commands.scoreboard.objectives.list.entry", new Object[] { var6.getName(), var6.getDisplayName(), var6.getCriteria().getName() }));
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected void setObjectiveDisplay(ICommandSender p_147198_1_, String[] p_147198_2_, int p_147198_3_) throws CommandException {
/*  849 */     Scoreboard var4 = getScoreboard();
/*  850 */     String var5 = p_147198_2_[p_147198_3_++];
/*  851 */     int var6 = Scoreboard.getObjectiveDisplaySlotNumber(var5);
/*  852 */     ScoreObjective var7 = null;
/*      */     
/*  854 */     if (p_147198_2_.length == 4)
/*      */     {
/*  856 */       var7 = func_147189_a(p_147198_2_[p_147198_3_], false);
/*      */     }
/*      */     
/*  859 */     if (var6 < 0)
/*      */     {
/*  861 */       throw new CommandException("commands.scoreboard.objectives.setdisplay.invalidSlot", new Object[] { var5 });
/*      */     }
/*      */ 
/*      */     
/*  865 */     var4.setObjectiveInDisplaySlot(var6, var7);
/*      */     
/*  867 */     if (var7 != null) {
/*      */       
/*  869 */       notifyOperators(p_147198_1_, (ICommand)this, "commands.scoreboard.objectives.setdisplay.successSet", new Object[] { Scoreboard.getObjectiveDisplaySlot(var6), var7.getName() });
/*      */     }
/*      */     else {
/*      */       
/*  873 */       notifyOperators(p_147198_1_, (ICommand)this, "commands.scoreboard.objectives.setdisplay.successCleared", new Object[] { Scoreboard.getObjectiveDisplaySlot(var6) });
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected void listPlayers(ICommandSender p_147195_1_, String[] p_147195_2_, int p_147195_3_) throws CommandException {
/*  880 */     Scoreboard var4 = getScoreboard();
/*      */     
/*  882 */     if (p_147195_2_.length > p_147195_3_) {
/*      */       
/*  884 */       String var5 = func_175758_e(p_147195_1_, p_147195_2_[p_147195_3_]);
/*  885 */       Map var6 = var4.func_96510_d(var5);
/*  886 */       p_147195_1_.func_174794_a(CommandResultStats.Type.QUERY_RESULT, var6.size());
/*      */       
/*  888 */       if (var6.size() <= 0)
/*      */       {
/*  890 */         throw new CommandException("commands.scoreboard.players.list.player.empty", new Object[] { var5 });
/*      */       }
/*      */       
/*  893 */       ChatComponentTranslation var7 = new ChatComponentTranslation("commands.scoreboard.players.list.player.count", new Object[] { Integer.valueOf(var6.size()), var5 });
/*  894 */       var7.getChatStyle().setColor(EnumChatFormatting.DARK_GREEN);
/*  895 */       p_147195_1_.addChatMessage((IChatComponent)var7);
/*  896 */       Iterator<Score> var8 = var6.values().iterator();
/*      */       
/*  898 */       while (var8.hasNext())
/*      */       {
/*  900 */         Score var9 = var8.next();
/*  901 */         p_147195_1_.addChatMessage((IChatComponent)new ChatComponentTranslation("commands.scoreboard.players.list.player.entry", new Object[] { Integer.valueOf(var9.getScorePoints()), var9.getObjective().getDisplayName(), var9.getObjective().getName() }));
/*      */       }
/*      */     
/*      */     } else {
/*      */       
/*  906 */       Collection var10 = var4.getObjectiveNames();
/*  907 */       p_147195_1_.func_174794_a(CommandResultStats.Type.QUERY_RESULT, var10.size());
/*      */       
/*  909 */       if (var10.size() <= 0)
/*      */       {
/*  911 */         throw new CommandException("commands.scoreboard.players.list.empty", new Object[0]);
/*      */       }
/*      */       
/*  914 */       ChatComponentTranslation var11 = new ChatComponentTranslation("commands.scoreboard.players.list.count", new Object[] { Integer.valueOf(var10.size()) });
/*  915 */       var11.getChatStyle().setColor(EnumChatFormatting.DARK_GREEN);
/*  916 */       p_147195_1_.addChatMessage((IChatComponent)var11);
/*  917 */       p_147195_1_.addChatMessage((IChatComponent)new ChatComponentText(joinNiceString(var10.toArray())));
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   protected void setPlayer(ICommandSender p_147197_1_, String[] p_147197_2_, int p_147197_3_) throws CommandException {
/*  923 */     String var4 = p_147197_2_[p_147197_3_ - 1];
/*  924 */     int var5 = p_147197_3_;
/*  925 */     String var6 = func_175758_e(p_147197_1_, p_147197_2_[p_147197_3_++]);
/*  926 */     ScoreObjective var7 = func_147189_a(p_147197_2_[p_147197_3_++], true);
/*  927 */     int var8 = var4.equalsIgnoreCase("set") ? parseInt(p_147197_2_[p_147197_3_++]) : parseInt(p_147197_2_[p_147197_3_++], 0);
/*      */     
/*  929 */     if (p_147197_2_.length > p_147197_3_) {
/*      */       
/*  931 */       Entity var9 = func_175768_b(p_147197_1_, p_147197_2_[var5]);
/*      */ 
/*      */       
/*      */       try {
/*  935 */         NBTTagCompound var10 = JsonToNBT.func_180713_a(func_180529_a(p_147197_2_, p_147197_3_));
/*  936 */         NBTTagCompound var11 = new NBTTagCompound();
/*  937 */         var9.writeToNBT(var11);
/*      */         
/*  939 */         if (!CommandTestForBlock.func_175775_a((NBTBase)var10, (NBTBase)var11, true))
/*      */         {
/*  941 */           throw new CommandException("commands.scoreboard.players.set.tagMismatch", new Object[] { var6 });
/*      */         }
/*      */       }
/*  944 */       catch (NBTException var12) {
/*      */         
/*  946 */         throw new CommandException("commands.scoreboard.players.set.tagError", new Object[] { var12.getMessage() });
/*      */       } 
/*      */     } 
/*      */     
/*  950 */     Scoreboard var13 = getScoreboard();
/*  951 */     Score var14 = var13.getValueFromObjective(var6, var7);
/*      */     
/*  953 */     if (var4.equalsIgnoreCase("set")) {
/*      */       
/*  955 */       var14.setScorePoints(var8);
/*      */     }
/*  957 */     else if (var4.equalsIgnoreCase("add")) {
/*      */       
/*  959 */       var14.increseScore(var8);
/*      */     }
/*      */     else {
/*      */       
/*  963 */       var14.decreaseScore(var8);
/*      */     } 
/*      */     
/*  966 */     notifyOperators(p_147197_1_, (ICommand)this, "commands.scoreboard.players.set.success", new Object[] { var7.getName(), var6, Integer.valueOf(var14.getScorePoints()) });
/*      */   }
/*      */ 
/*      */   
/*      */   protected void resetPlayers(ICommandSender p_147187_1_, String[] p_147187_2_, int p_147187_3_) throws CommandException {
/*  971 */     Scoreboard var4 = getScoreboard();
/*  972 */     String var5 = func_175758_e(p_147187_1_, p_147187_2_[p_147187_3_++]);
/*      */     
/*  974 */     if (p_147187_2_.length > p_147187_3_) {
/*      */       
/*  976 */       ScoreObjective var6 = func_147189_a(p_147187_2_[p_147187_3_++], false);
/*  977 */       var4.func_178822_d(var5, var6);
/*  978 */       notifyOperators(p_147187_1_, (ICommand)this, "commands.scoreboard.players.resetscore.success", new Object[] { var6.getName(), var5 });
/*      */     }
/*      */     else {
/*      */       
/*  982 */       var4.func_178822_d(var5, null);
/*  983 */       notifyOperators(p_147187_1_, (ICommand)this, "commands.scoreboard.players.reset.success", new Object[] { var5 });
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   protected void func_175779_n(ICommandSender p_175779_1_, String[] p_175779_2_, int p_175779_3_) throws CommandException {
/*  989 */     Scoreboard var4 = getScoreboard();
/*  990 */     String var5 = getPlayerName(p_175779_1_, p_175779_2_[p_175779_3_++]);
/*  991 */     ScoreObjective var6 = func_147189_a(p_175779_2_[p_175779_3_], false);
/*      */     
/*  993 */     if (var6.getCriteria() != IScoreObjectiveCriteria.field_178791_c)
/*      */     {
/*  995 */       throw new CommandException("commands.scoreboard.players.enable.noTrigger", new Object[] { var6.getName() });
/*      */     }
/*      */ 
/*      */     
/*  999 */     Score var7 = var4.getValueFromObjective(var5, var6);
/* 1000 */     var7.func_178815_a(false);
/* 1001 */     notifyOperators(p_175779_1_, (ICommand)this, "commands.scoreboard.players.enable.success", new Object[] { var6.getName(), var5 });
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected void func_175781_o(ICommandSender p_175781_1_, String[] p_175781_2_, int p_175781_3_) throws CommandException {
/* 1007 */     Scoreboard var4 = getScoreboard();
/* 1008 */     String var5 = func_175758_e(p_175781_1_, p_175781_2_[p_175781_3_++]);
/* 1009 */     ScoreObjective var6 = func_147189_a(p_175781_2_[p_175781_3_++], false);
/*      */     
/* 1011 */     if (!var4.func_178819_b(var5, var6))
/*      */     {
/* 1013 */       throw new CommandException("commands.scoreboard.players.test.notFound", new Object[] { var6.getName(), var5 });
/*      */     }
/*      */ 
/*      */     
/* 1017 */     int var7 = p_175781_2_[p_175781_3_].equals("*") ? Integer.MIN_VALUE : parseInt(p_175781_2_[p_175781_3_]);
/* 1018 */     p_175781_3_++;
/* 1019 */     int var8 = (p_175781_3_ < p_175781_2_.length && !p_175781_2_[p_175781_3_].equals("*")) ? parseInt(p_175781_2_[p_175781_3_], var7) : Integer.MAX_VALUE;
/* 1020 */     Score var9 = var4.getValueFromObjective(var5, var6);
/*      */     
/* 1022 */     if (var9.getScorePoints() >= var7 && var9.getScorePoints() <= var8) {
/*      */       
/* 1024 */       notifyOperators(p_175781_1_, (ICommand)this, "commands.scoreboard.players.test.success", new Object[] { Integer.valueOf(var9.getScorePoints()), Integer.valueOf(var7), Integer.valueOf(var8) });
/*      */     }
/*      */     else {
/*      */       
/* 1028 */       throw new CommandException("commands.scoreboard.players.test.failed", new Object[] { Integer.valueOf(var9.getScorePoints()), Integer.valueOf(var7), Integer.valueOf(var8) });
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected void func_175778_p(ICommandSender p_175778_1_, String[] p_175778_2_, int p_175778_3_) throws CommandException {
/* 1035 */     Scoreboard var4 = getScoreboard();
/* 1036 */     String var5 = func_175758_e(p_175778_1_, p_175778_2_[p_175778_3_++]);
/* 1037 */     ScoreObjective var6 = func_147189_a(p_175778_2_[p_175778_3_++], true);
/* 1038 */     String var7 = p_175778_2_[p_175778_3_++];
/* 1039 */     String var8 = func_175758_e(p_175778_1_, p_175778_2_[p_175778_3_++]);
/* 1040 */     ScoreObjective var9 = func_147189_a(p_175778_2_[p_175778_3_], false);
/* 1041 */     Score var10 = var4.getValueFromObjective(var5, var6);
/*      */     
/* 1043 */     if (!var4.func_178819_b(var8, var9))
/*      */     {
/* 1045 */       throw new CommandException("commands.scoreboard.players.operation.notFound", new Object[] { var9.getName(), var8 });
/*      */     }
/*      */ 
/*      */     
/* 1049 */     Score var11 = var4.getValueFromObjective(var8, var9);
/*      */     
/* 1051 */     if (var7.equals("+=")) {
/*      */       
/* 1053 */       var10.setScorePoints(var10.getScorePoints() + var11.getScorePoints());
/*      */     }
/* 1055 */     else if (var7.equals("-=")) {
/*      */       
/* 1057 */       var10.setScorePoints(var10.getScorePoints() - var11.getScorePoints());
/*      */     }
/* 1059 */     else if (var7.equals("*=")) {
/*      */       
/* 1061 */       var10.setScorePoints(var10.getScorePoints() * var11.getScorePoints());
/*      */     }
/* 1063 */     else if (var7.equals("/=")) {
/*      */       
/* 1065 */       if (var11.getScorePoints() != 0)
/*      */       {
/* 1067 */         var10.setScorePoints(var10.getScorePoints() / var11.getScorePoints());
/*      */       }
/*      */     }
/* 1070 */     else if (var7.equals("%=")) {
/*      */       
/* 1072 */       if (var11.getScorePoints() != 0)
/*      */       {
/* 1074 */         var10.setScorePoints(var10.getScorePoints() % var11.getScorePoints());
/*      */       }
/*      */     }
/* 1077 */     else if (var7.equals("=")) {
/*      */       
/* 1079 */       var10.setScorePoints(var11.getScorePoints());
/*      */     }
/* 1081 */     else if (var7.equals("<")) {
/*      */       
/* 1083 */       var10.setScorePoints(Math.min(var10.getScorePoints(), var11.getScorePoints()));
/*      */     }
/* 1085 */     else if (var7.equals(">")) {
/*      */       
/* 1087 */       var10.setScorePoints(Math.max(var10.getScorePoints(), var11.getScorePoints()));
/*      */     }
/*      */     else {
/*      */       
/* 1091 */       if (!var7.equals("><"))
/*      */       {
/* 1093 */         throw new CommandException("commands.scoreboard.players.operation.invalidOperation", new Object[] { var7 });
/*      */       }
/*      */       
/* 1096 */       int var12 = var10.getScorePoints();
/* 1097 */       var10.setScorePoints(var11.getScorePoints());
/* 1098 */       var11.setScorePoints(var12);
/*      */     } 
/*      */     
/* 1101 */     notifyOperators(p_175778_1_, (ICommand)this, "commands.scoreboard.players.operation.success", new Object[0]);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public List addTabCompletionOptions(ICommandSender sender, String[] args, BlockPos pos) {
/* 1107 */     if (args.length == 1)
/*      */     {
/* 1109 */       return getListOfStringsMatchingLastWord(args, new String[] { "objectives", "players", "teams" });
/*      */     }
/*      */ 
/*      */     
/* 1113 */     if (args[0].equalsIgnoreCase("objectives")) {
/*      */       
/* 1115 */       if (args.length == 2)
/*      */       {
/* 1117 */         return getListOfStringsMatchingLastWord(args, new String[] { "list", "add", "remove", "setdisplay" });
/*      */       }
/*      */       
/* 1120 */       if (args[1].equalsIgnoreCase("add")) {
/*      */         
/* 1122 */         if (args.length == 4)
/*      */         {
/* 1124 */           Set var4 = IScoreObjectiveCriteria.INSTANCES.keySet();
/* 1125 */           return func_175762_a(args, var4);
/*      */         }
/*      */       
/* 1128 */       } else if (args[1].equalsIgnoreCase("remove")) {
/*      */         
/* 1130 */         if (args.length == 3)
/*      */         {
/* 1132 */           return func_175762_a(args, func_147184_a(false));
/*      */         }
/*      */       }
/* 1135 */       else if (args[1].equalsIgnoreCase("setdisplay")) {
/*      */         
/* 1137 */         if (args.length == 3)
/*      */         {
/* 1139 */           return getListOfStringsMatchingLastWord(args, Scoreboard.func_178821_h());
/*      */         }
/*      */         
/* 1142 */         if (args.length == 4)
/*      */         {
/* 1144 */           return func_175762_a(args, func_147184_a(false));
/*      */         }
/*      */       }
/*      */     
/* 1148 */     } else if (args[0].equalsIgnoreCase("players")) {
/*      */       
/* 1150 */       if (args.length == 2)
/*      */       {
/* 1152 */         return getListOfStringsMatchingLastWord(args, new String[] { "set", "add", "remove", "reset", "list", "enable", "test", "operation" });
/*      */       }
/*      */       
/* 1155 */       if (!args[1].equalsIgnoreCase("set") && !args[1].equalsIgnoreCase("add") && !args[1].equalsIgnoreCase("remove") && !args[1].equalsIgnoreCase("reset")) {
/*      */         
/* 1157 */         if (args[1].equalsIgnoreCase("enable")) {
/*      */           
/* 1159 */           if (args.length == 3)
/*      */           {
/* 1161 */             return getListOfStringsMatchingLastWord(args, MinecraftServer.getServer().getAllUsernames());
/*      */           }
/*      */           
/* 1164 */           if (args.length == 4)
/*      */           {
/* 1166 */             return func_175762_a(args, func_175782_e());
/*      */           }
/*      */         }
/* 1169 */         else if (!args[1].equalsIgnoreCase("list") && !args[1].equalsIgnoreCase("test")) {
/*      */           
/* 1171 */           if (args[1].equalsIgnoreCase("operation"))
/*      */           {
/* 1173 */             if (args.length == 3)
/*      */             {
/* 1175 */               return func_175762_a(args, getScoreboard().getObjectiveNames());
/*      */             }
/*      */             
/* 1178 */             if (args.length == 4)
/*      */             {
/* 1180 */               return func_175762_a(args, func_147184_a(true));
/*      */             }
/*      */             
/* 1183 */             if (args.length == 5)
/*      */             {
/* 1185 */               return getListOfStringsMatchingLastWord(args, new String[] { "+=", "-=", "*=", "/=", "%=", "=", "<", ">", "><" });
/*      */             }
/*      */             
/* 1188 */             if (args.length == 6)
/*      */             {
/* 1190 */               return getListOfStringsMatchingLastWord(args, MinecraftServer.getServer().getAllUsernames());
/*      */             }
/*      */             
/* 1193 */             if (args.length == 7)
/*      */             {
/* 1195 */               return func_175762_a(args, func_147184_a(false));
/*      */             }
/*      */           }
/*      */         
/*      */         } else {
/*      */           
/* 1201 */           if (args.length == 3)
/*      */           {
/* 1203 */             return func_175762_a(args, getScoreboard().getObjectiveNames());
/*      */           }
/*      */           
/* 1206 */           if (args.length == 4 && args[1].equalsIgnoreCase("test"))
/*      */           {
/* 1208 */             return func_175762_a(args, func_147184_a(false));
/*      */           }
/*      */         }
/*      */       
/*      */       } else {
/*      */         
/* 1214 */         if (args.length == 3)
/*      */         {
/* 1216 */           return getListOfStringsMatchingLastWord(args, MinecraftServer.getServer().getAllUsernames());
/*      */         }
/*      */         
/* 1219 */         if (args.length == 4)
/*      */         {
/* 1221 */           return func_175762_a(args, func_147184_a(true));
/*      */         }
/*      */       }
/*      */     
/* 1225 */     } else if (args[0].equalsIgnoreCase("teams")) {
/*      */       
/* 1227 */       if (args.length == 2)
/*      */       {
/* 1229 */         return getListOfStringsMatchingLastWord(args, new String[] { "add", "remove", "join", "leave", "empty", "list", "option" });
/*      */       }
/*      */       
/* 1232 */       if (args[1].equalsIgnoreCase("join")) {
/*      */         
/* 1234 */         if (args.length == 3)
/*      */         {
/* 1236 */           return func_175762_a(args, getScoreboard().getTeamNames());
/*      */         }
/*      */         
/* 1239 */         if (args.length >= 4)
/*      */         {
/* 1241 */           return getListOfStringsMatchingLastWord(args, MinecraftServer.getServer().getAllUsernames());
/*      */         }
/*      */       }
/*      */       else {
/*      */         
/* 1246 */         if (args[1].equalsIgnoreCase("leave"))
/*      */         {
/* 1248 */           return getListOfStringsMatchingLastWord(args, MinecraftServer.getServer().getAllUsernames());
/*      */         }
/*      */         
/* 1251 */         if (!args[1].equalsIgnoreCase("empty") && !args[1].equalsIgnoreCase("list") && !args[1].equalsIgnoreCase("remove")) {
/*      */           
/* 1253 */           if (args[1].equalsIgnoreCase("option")) {
/*      */             
/* 1255 */             if (args.length == 3)
/*      */             {
/* 1257 */               return func_175762_a(args, getScoreboard().getTeamNames());
/*      */             }
/*      */             
/* 1260 */             if (args.length == 4)
/*      */             {
/* 1262 */               return getListOfStringsMatchingLastWord(args, new String[] { "color", "friendlyfire", "seeFriendlyInvisibles", "nametagVisibility", "deathMessageVisibility" });
/*      */             }
/*      */             
/* 1265 */             if (args.length == 5)
/*      */             {
/* 1267 */               if (args[3].equalsIgnoreCase("color"))
/*      */               {
/* 1269 */                 return func_175762_a(args, EnumChatFormatting.getValidValues(true, false));
/*      */               }
/*      */               
/* 1272 */               if (args[3].equalsIgnoreCase("nametagVisibility") || args[3].equalsIgnoreCase("deathMessageVisibility"))
/*      */               {
/* 1274 */                 return getListOfStringsMatchingLastWord(args, Team.EnumVisible.func_178825_a());
/*      */               }
/*      */               
/* 1277 */               if (args[3].equalsIgnoreCase("friendlyfire") || args[3].equalsIgnoreCase("seeFriendlyInvisibles"))
/*      */               {
/* 1279 */                 return getListOfStringsMatchingLastWord(args, new String[] { "true", "false" });
/*      */               }
/*      */             }
/*      */           
/*      */           } 
/* 1284 */         } else if (args.length == 3) {
/*      */           
/* 1286 */           return func_175762_a(args, getScoreboard().getTeamNames());
/*      */         } 
/*      */       } 
/*      */     } 
/*      */     
/* 1291 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected List func_147184_a(boolean p_147184_1_) {
/* 1297 */     Collection var2 = getScoreboard().getScoreObjectives();
/* 1298 */     ArrayList<String> var3 = Lists.newArrayList();
/* 1299 */     Iterator<ScoreObjective> var4 = var2.iterator();
/*      */     
/* 1301 */     while (var4.hasNext()) {
/*      */       
/* 1303 */       ScoreObjective var5 = var4.next();
/*      */       
/* 1305 */       if (!p_147184_1_ || !var5.getCriteria().isReadOnly())
/*      */       {
/* 1307 */         var3.add(var5.getName());
/*      */       }
/*      */     } 
/*      */     
/* 1311 */     return var3;
/*      */   }
/*      */ 
/*      */   
/*      */   protected List func_175782_e() {
/* 1316 */     Collection var1 = getScoreboard().getScoreObjectives();
/* 1317 */     ArrayList<String> var2 = Lists.newArrayList();
/* 1318 */     Iterator<ScoreObjective> var3 = var1.iterator();
/*      */     
/* 1320 */     while (var3.hasNext()) {
/*      */       
/* 1322 */       ScoreObjective var4 = var3.next();
/*      */       
/* 1324 */       if (var4.getCriteria() == IScoreObjectiveCriteria.field_178791_c)
/*      */       {
/* 1326 */         var2.add(var4.getName());
/*      */       }
/*      */     } 
/*      */     
/* 1330 */     return var2;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isUsernameIndex(String[] args, int index) {
/* 1338 */     return !args[0].equalsIgnoreCase("players") ? (args[0].equalsIgnoreCase("teams") ? ((index == 2)) : false) : ((args.length > 1 && args[1].equalsIgnoreCase("operation")) ? (!(index != 2 && index != 5)) : ((index == 2)));
/*      */   }
/*      */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\command\server\CommandScoreboard.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */