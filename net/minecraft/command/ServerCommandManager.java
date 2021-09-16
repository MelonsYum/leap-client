/*     */ package net.minecraft.command;
/*     */ 
/*     */ import java.util.Iterator;
/*     */ import net.minecraft.command.common.CommandReplaceItem;
/*     */ import net.minecraft.command.server.CommandAchievement;
/*     */ import net.minecraft.command.server.CommandBanIp;
/*     */ import net.minecraft.command.server.CommandBanPlayer;
/*     */ import net.minecraft.command.server.CommandBlockLogic;
/*     */ import net.minecraft.command.server.CommandBroadcast;
/*     */ import net.minecraft.command.server.CommandDeOp;
/*     */ import net.minecraft.command.server.CommandEmote;
/*     */ import net.minecraft.command.server.CommandListBans;
/*     */ import net.minecraft.command.server.CommandListPlayers;
/*     */ import net.minecraft.command.server.CommandMessage;
/*     */ import net.minecraft.command.server.CommandMessageRaw;
/*     */ import net.minecraft.command.server.CommandOp;
/*     */ import net.minecraft.command.server.CommandPardonIp;
/*     */ import net.minecraft.command.server.CommandPardonPlayer;
/*     */ import net.minecraft.command.server.CommandPublishLocalServer;
/*     */ import net.minecraft.command.server.CommandSaveAll;
/*     */ import net.minecraft.command.server.CommandSaveOff;
/*     */ import net.minecraft.command.server.CommandSaveOn;
/*     */ import net.minecraft.command.server.CommandScoreboard;
/*     */ import net.minecraft.command.server.CommandSetBlock;
/*     */ import net.minecraft.command.server.CommandSetDefaultSpawnpoint;
/*     */ import net.minecraft.command.server.CommandStop;
/*     */ import net.minecraft.command.server.CommandSummon;
/*     */ import net.minecraft.command.server.CommandTeleport;
/*     */ import net.minecraft.command.server.CommandTestFor;
/*     */ import net.minecraft.command.server.CommandTestForBlock;
/*     */ import net.minecraft.command.server.CommandWhitelist;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.server.MinecraftServer;
/*     */ import net.minecraft.util.ChatComponentTranslation;
/*     */ import net.minecraft.util.EnumChatFormatting;
/*     */ import net.minecraft.util.IChatComponent;
/*     */ 
/*     */ public class ServerCommandManager
/*     */   extends CommandHandler implements IAdminCommand {
/*     */   private static final String __OBFID = "CL_00000922";
/*     */   
/*     */   public ServerCommandManager() {
/*  43 */     registerCommand(new CommandTime());
/*  44 */     registerCommand(new CommandGameMode());
/*  45 */     registerCommand(new CommandDifficulty());
/*  46 */     registerCommand(new CommandDefaultGameMode());
/*  47 */     registerCommand(new CommandKill());
/*  48 */     registerCommand(new CommandToggleDownfall());
/*  49 */     registerCommand(new CommandWeather());
/*  50 */     registerCommand(new CommandXP());
/*  51 */     registerCommand((ICommand)new CommandTeleport());
/*  52 */     registerCommand(new CommandGive());
/*  53 */     registerCommand((ICommand)new CommandReplaceItem());
/*  54 */     registerCommand(new CommandStats());
/*  55 */     registerCommand(new CommandEffect());
/*  56 */     registerCommand(new CommandEnchant());
/*  57 */     registerCommand(new CommandParticle());
/*  58 */     registerCommand((ICommand)new CommandEmote());
/*  59 */     registerCommand(new CommandShowSeed());
/*  60 */     registerCommand(new CommandHelp());
/*  61 */     registerCommand(new CommandDebug());
/*  62 */     registerCommand((ICommand)new CommandMessage());
/*  63 */     registerCommand((ICommand)new CommandBroadcast());
/*  64 */     registerCommand(new CommandSetSpawnpoint());
/*  65 */     registerCommand((ICommand)new CommandSetDefaultSpawnpoint());
/*  66 */     registerCommand(new CommandGameRule());
/*  67 */     registerCommand(new CommandClearInventory());
/*  68 */     registerCommand((ICommand)new CommandTestFor());
/*  69 */     registerCommand(new CommandSpreadPlayers());
/*  70 */     registerCommand(new CommandPlaySound());
/*  71 */     registerCommand((ICommand)new CommandScoreboard());
/*  72 */     registerCommand(new CommandExecuteAt());
/*  73 */     registerCommand(new CommandTrigger());
/*  74 */     registerCommand((ICommand)new CommandAchievement());
/*  75 */     registerCommand((ICommand)new CommandSummon());
/*  76 */     registerCommand((ICommand)new CommandSetBlock());
/*  77 */     registerCommand(new CommandFill());
/*  78 */     registerCommand(new CommandClone());
/*  79 */     registerCommand(new CommandCompare());
/*  80 */     registerCommand(new CommandBlockData());
/*  81 */     registerCommand((ICommand)new CommandTestForBlock());
/*  82 */     registerCommand((ICommand)new CommandMessageRaw());
/*  83 */     registerCommand(new CommandWorldBorder());
/*  84 */     registerCommand(new CommandTitle());
/*  85 */     registerCommand(new CommandEntityData());
/*     */     
/*  87 */     if (MinecraftServer.getServer().isDedicatedServer()) {
/*     */       
/*  89 */       registerCommand((ICommand)new CommandOp());
/*  90 */       registerCommand((ICommand)new CommandDeOp());
/*  91 */       registerCommand((ICommand)new CommandStop());
/*  92 */       registerCommand((ICommand)new CommandSaveAll());
/*  93 */       registerCommand((ICommand)new CommandSaveOff());
/*  94 */       registerCommand((ICommand)new CommandSaveOn());
/*  95 */       registerCommand((ICommand)new CommandBanIp());
/*  96 */       registerCommand((ICommand)new CommandPardonIp());
/*  97 */       registerCommand((ICommand)new CommandBanPlayer());
/*  98 */       registerCommand((ICommand)new CommandListBans());
/*  99 */       registerCommand((ICommand)new CommandPardonPlayer());
/* 100 */       registerCommand(new CommandServerKick());
/* 101 */       registerCommand((ICommand)new CommandListPlayers());
/* 102 */       registerCommand((ICommand)new CommandWhitelist());
/* 103 */       registerCommand(new CommandSetPlayerTimeout());
/*     */     }
/*     */     else {
/*     */       
/* 107 */       registerCommand((ICommand)new CommandPublishLocalServer());
/*     */     } 
/*     */     
/* 110 */     CommandBase.setAdminCommander(this);
/*     */   }
/*     */ 
/*     */   
/*     */   public void notifyOperators(ICommandSender sender, ICommand command, int p_152372_3_, String msgFormat, Object... msgParams) {
/* 115 */     boolean var6 = true;
/* 116 */     MinecraftServer var7 = MinecraftServer.getServer();
/*     */     
/* 118 */     if (!sender.sendCommandFeedback())
/*     */     {
/* 120 */       var6 = false;
/*     */     }
/*     */     
/* 123 */     ChatComponentTranslation var8 = new ChatComponentTranslation("chat.type.admin", new Object[] { sender.getName(), new ChatComponentTranslation(msgFormat, msgParams) });
/* 124 */     var8.getChatStyle().setColor(EnumChatFormatting.GRAY);
/* 125 */     var8.getChatStyle().setItalic(Boolean.valueOf(true));
/*     */     
/* 127 */     if (var6) {
/*     */       
/* 129 */       Iterator<EntityPlayer> var9 = (var7.getConfigurationManager()).playerEntityList.iterator();
/*     */       
/* 131 */       while (var9.hasNext()) {
/*     */         
/* 133 */         EntityPlayer var10 = var9.next();
/*     */         
/* 135 */         if (var10 != sender && var7.getConfigurationManager().canSendCommands(var10.getGameProfile()) && command.canCommandSenderUseCommand(sender))
/*     */         {
/* 137 */           var10.addChatMessage((IChatComponent)var8);
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 142 */     if (sender != var7 && var7.worldServers[0].getGameRules().getGameRuleBooleanValue("logAdminCommands"))
/*     */     {
/* 144 */       var7.addChatMessage((IChatComponent)var8);
/*     */     }
/*     */     
/* 147 */     boolean var11 = var7.worldServers[0].getGameRules().getGameRuleBooleanValue("sendCommandFeedback");
/*     */     
/* 149 */     if (sender instanceof CommandBlockLogic)
/*     */     {
/* 151 */       var11 = ((CommandBlockLogic)sender).func_175571_m();
/*     */     }
/*     */     
/* 154 */     if ((p_152372_3_ & 0x1) != 1 && var11)
/*     */     {
/* 156 */       sender.addChatMessage((IChatComponent)new ChatComponentTranslation(msgFormat, msgParams));
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\command\ServerCommandManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */