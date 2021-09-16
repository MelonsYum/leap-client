/*     */ package net.minecraft.command;
/*     */ 
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.player.EntityPlayerMP;
/*     */ import net.minecraft.network.Packet;
/*     */ import net.minecraft.network.play.server.S19PacketEntityStatus;
/*     */ import net.minecraft.server.MinecraftServer;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.ChatComponentText;
/*     */ import net.minecraft.util.IChatComponent;
/*     */ import net.minecraft.world.GameRules;
/*     */ 
/*     */ public class CommandGameRule
/*     */   extends CommandBase {
/*     */   public String getCommandName() {
/*  18 */     return "gamerule";
/*     */   }
/*     */ 
/*     */   
/*     */   private static final String __OBFID = "CL_00000475";
/*     */ 
/*     */   
/*     */   public int getRequiredPermissionLevel() {
/*  26 */     return 2;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getCommandUsage(ICommandSender sender) {
/*  31 */     return "commands.gamerule.usage";
/*     */   }
/*     */   
/*     */   public void processCommand(ICommandSender sender, String[] args) throws CommandException {
/*     */     String var6;
/*  36 */     GameRules var3 = getGameRules();
/*  37 */     String var4 = (args.length > 0) ? args[0] : "";
/*  38 */     String var5 = (args.length > 1) ? func_180529_a(args, 1) : "";
/*     */     
/*  40 */     switch (args.length) {
/*     */       
/*     */       case 0:
/*  43 */         sender.addChatMessage((IChatComponent)new ChatComponentText(joinNiceString((Object[])var3.getRules())));
/*     */         return;
/*     */       
/*     */       case 1:
/*  47 */         if (!var3.hasRule(var4))
/*     */         {
/*  49 */           throw new CommandException("commands.gamerule.norule", new Object[] { var4 });
/*     */         }
/*     */         
/*  52 */         var6 = var3.getGameRuleStringValue(var4);
/*  53 */         sender.addChatMessage((new ChatComponentText(var4)).appendText(" = ").appendText(var6));
/*  54 */         sender.func_174794_a(CommandResultStats.Type.QUERY_RESULT, var3.getInt(var4));
/*     */         return;
/*     */     } 
/*     */     
/*  58 */     if (var3.areSameType(var4, GameRules.ValueType.BOOLEAN_VALUE) && !"true".equals(var5) && !"false".equals(var5))
/*     */     {
/*  60 */       throw new CommandException("commands.generic.boolean.invalid", new Object[] { var5 });
/*     */     }
/*     */     
/*  63 */     var3.setOrCreateGameRule(var4, var5);
/*  64 */     func_175773_a(var3, var4);
/*  65 */     notifyOperators(sender, this, "commands.gamerule.success", new Object[0]);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static void func_175773_a(GameRules p_175773_0_, String p_175773_1_) {
/*  71 */     if ("reducedDebugInfo".equals(p_175773_1_)) {
/*     */       
/*  73 */       int var2 = p_175773_0_.getGameRuleBooleanValue(p_175773_1_) ? 22 : 23;
/*  74 */       Iterator<EntityPlayerMP> var3 = (MinecraftServer.getServer().getConfigurationManager()).playerEntityList.iterator();
/*     */       
/*  76 */       while (var3.hasNext()) {
/*     */         
/*  78 */         EntityPlayerMP var4 = var3.next();
/*  79 */         var4.playerNetServerHandler.sendPacket((Packet)new S19PacketEntityStatus((Entity)var4, (byte)var2));
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public List addTabCompletionOptions(ICommandSender sender, String[] args, BlockPos pos) {
/*  86 */     if (args.length == 1)
/*     */     {
/*  88 */       return getListOfStringsMatchingLastWord(args, getGameRules().getRules());
/*     */     }
/*     */ 
/*     */     
/*  92 */     if (args.length == 2) {
/*     */       
/*  94 */       GameRules var4 = getGameRules();
/*     */       
/*  96 */       if (var4.areSameType(args[0], GameRules.ValueType.BOOLEAN_VALUE))
/*     */       {
/*  98 */         return getListOfStringsMatchingLastWord(args, new String[] { "true", "false" });
/*     */       }
/*     */     } 
/*     */     
/* 102 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private GameRules getGameRules() {
/* 111 */     return MinecraftServer.getServer().worldServerForDimension(0).getGameRules();
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\command\CommandGameRule.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */