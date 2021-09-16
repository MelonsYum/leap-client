/*     */ package net.minecraft.command;
/*     */ import com.google.gson.JsonParseException;
/*     */ import java.util.List;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.player.EntityPlayerMP;
/*     */ import net.minecraft.network.Packet;
/*     */ import net.minecraft.network.play.server.S45PacketTitle;
/*     */ import net.minecraft.server.MinecraftServer;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.ChatComponentProcessor;
/*     */ import net.minecraft.util.IChatComponent;
/*     */ import org.apache.commons.lang3.exception.ExceptionUtils;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ 
/*     */ public class CommandTitle extends CommandBase {
/*  17 */   private static final Logger field_175774_a = LogManager.getLogger();
/*     */   
/*     */   private static final String __OBFID = "CL_00002338";
/*     */   
/*     */   public String getCommandName() {
/*  22 */     return "title";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getRequiredPermissionLevel() {
/*  30 */     return 2;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getCommandUsage(ICommandSender sender) {
/*  35 */     return "commands.title.usage";
/*     */   }
/*     */ 
/*     */   
/*     */   public void processCommand(ICommandSender sender, String[] args) throws CommandException {
/*  40 */     if (args.length < 2)
/*     */     {
/*  42 */       throw new WrongUsageException("commands.title.usage", new Object[0]);
/*     */     }
/*     */ 
/*     */     
/*  46 */     if (args.length < 3) {
/*     */       
/*  48 */       if ("title".equals(args[1]) || "subtitle".equals(args[1]))
/*     */       {
/*  50 */         throw new WrongUsageException("commands.title.usage.title", new Object[0]);
/*     */       }
/*     */       
/*  53 */       if ("times".equals(args[1]))
/*     */       {
/*  55 */         throw new WrongUsageException("commands.title.usage.times", new Object[0]);
/*     */       }
/*     */     } 
/*     */     
/*  59 */     EntityPlayerMP var3 = getPlayer(sender, args[0]);
/*  60 */     S45PacketTitle.Type var4 = S45PacketTitle.Type.func_179969_a(args[1]);
/*     */     
/*  62 */     if (var4 != S45PacketTitle.Type.CLEAR && var4 != S45PacketTitle.Type.RESET) {
/*     */       
/*  64 */       if (var4 == S45PacketTitle.Type.TIMES) {
/*     */         
/*  66 */         if (args.length != 5)
/*     */         {
/*  68 */           throw new WrongUsageException("commands.title.usage", new Object[0]);
/*     */         }
/*     */ 
/*     */         
/*  72 */         int var11 = parseInt(args[2]);
/*  73 */         int var12 = parseInt(args[3]);
/*  74 */         int var13 = parseInt(args[4]);
/*  75 */         S45PacketTitle var14 = new S45PacketTitle(var11, var12, var13);
/*  76 */         var3.playerNetServerHandler.sendPacket((Packet)var14);
/*  77 */         notifyOperators(sender, this, "commands.title.success", new Object[0]);
/*     */       } else {
/*     */         IChatComponent var6;
/*  80 */         if (args.length < 3)
/*     */         {
/*  82 */           throw new WrongUsageException("commands.title.usage", new Object[0]);
/*     */         }
/*     */ 
/*     */         
/*  86 */         String var10 = func_180529_a(args, 2);
/*     */ 
/*     */ 
/*     */         
/*     */         try {
/*  91 */           var6 = IChatComponent.Serializer.jsonToComponent(var10);
/*     */         }
/*  93 */         catch (JsonParseException var9) {
/*     */           
/*  95 */           Throwable var8 = ExceptionUtils.getRootCause((Throwable)var9);
/*  96 */           throw new SyntaxErrorException("commands.tellraw.jsonException", new Object[] { (var8 == null) ? "" : var8.getMessage() });
/*     */         } 
/*     */         
/*  99 */         S45PacketTitle var7 = new S45PacketTitle(var4, ChatComponentProcessor.func_179985_a(sender, var6, (Entity)var3));
/* 100 */         var3.playerNetServerHandler.sendPacket((Packet)var7);
/* 101 */         notifyOperators(sender, this, "commands.title.success", new Object[0]);
/*     */       } 
/*     */     } else {
/* 104 */       if (args.length != 2)
/*     */       {
/* 106 */         throw new WrongUsageException("commands.title.usage", new Object[0]);
/*     */       }
/*     */ 
/*     */       
/* 110 */       S45PacketTitle var5 = new S45PacketTitle(var4, null);
/* 111 */       var3.playerNetServerHandler.sendPacket((Packet)var5);
/* 112 */       notifyOperators(sender, this, "commands.title.success", new Object[0]);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public List addTabCompletionOptions(ICommandSender sender, String[] args, BlockPos pos) {
/* 119 */     return (args.length == 1) ? getListOfStringsMatchingLastWord(args, MinecraftServer.getServer().getAllUsernames()) : ((args.length == 2) ? getListOfStringsMatchingLastWord(args, S45PacketTitle.Type.func_179971_a()) : null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isUsernameIndex(String[] args, int index) {
/* 127 */     return (index == 0);
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\command\CommandTitle.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */