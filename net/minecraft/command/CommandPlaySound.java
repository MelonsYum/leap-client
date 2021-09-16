/*     */ package net.minecraft.command;
/*     */ 
/*     */ import java.util.List;
/*     */ import net.minecraft.entity.player.EntityPlayerMP;
/*     */ import net.minecraft.network.Packet;
/*     */ import net.minecraft.network.play.server.S29PacketSoundEffect;
/*     */ import net.minecraft.server.MinecraftServer;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.Vec3;
/*     */ 
/*     */ public class CommandPlaySound
/*     */   extends CommandBase {
/*     */   private static final String __OBFID = "CL_00000774";
/*     */   
/*     */   public String getCommandName() {
/*  16 */     return "playsound";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getRequiredPermissionLevel() {
/*  24 */     return 2;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getCommandUsage(ICommandSender sender) {
/*  29 */     return "commands.playsound.usage";
/*     */   }
/*     */ 
/*     */   
/*     */   public void processCommand(ICommandSender sender, String[] args) throws CommandException {
/*  34 */     if (args.length < 2)
/*     */     {
/*  36 */       throw new WrongUsageException(getCommandUsage(sender), new Object[0]);
/*     */     }
/*     */ 
/*     */     
/*  40 */     byte var3 = 0;
/*  41 */     int var31 = var3 + 1;
/*  42 */     String var4 = args[var3];
/*  43 */     EntityPlayerMP var5 = getPlayer(sender, args[var31++]);
/*  44 */     Vec3 var6 = sender.getPositionVector();
/*  45 */     double var7 = var6.xCoord;
/*     */     
/*  47 */     if (args.length > var31)
/*     */     {
/*  49 */       var7 = func_175761_b(var7, args[var31++], true);
/*     */     }
/*     */     
/*  52 */     double var9 = var6.yCoord;
/*     */     
/*  54 */     if (args.length > var31)
/*     */     {
/*  56 */       var9 = func_175769_b(var9, args[var31++], 0, 0, false);
/*     */     }
/*     */     
/*  59 */     double var11 = var6.zCoord;
/*     */     
/*  61 */     if (args.length > var31)
/*     */     {
/*  63 */       var11 = func_175761_b(var11, args[var31++], true);
/*     */     }
/*     */     
/*  66 */     double var13 = 1.0D;
/*     */     
/*  68 */     if (args.length > var31)
/*     */     {
/*  70 */       var13 = parseDouble(args[var31++], 0.0D, 3.4028234663852886E38D);
/*     */     }
/*     */     
/*  73 */     double var15 = 1.0D;
/*     */     
/*  75 */     if (args.length > var31)
/*     */     {
/*  77 */       var15 = parseDouble(args[var31++], 0.0D, 2.0D);
/*     */     }
/*     */     
/*  80 */     double var17 = 0.0D;
/*     */     
/*  82 */     if (args.length > var31)
/*     */     {
/*  84 */       var17 = parseDouble(args[var31], 0.0D, 1.0D);
/*     */     }
/*     */     
/*  87 */     double var19 = (var13 > 1.0D) ? (var13 * 16.0D) : 16.0D;
/*  88 */     double var21 = var5.getDistance(var7, var9, var11);
/*     */     
/*  90 */     if (var21 > var19) {
/*     */       
/*  92 */       if (var17 <= 0.0D)
/*     */       {
/*  94 */         throw new CommandException("commands.playsound.playerTooFar", new Object[] { var5.getName() });
/*     */       }
/*     */       
/*  97 */       double var23 = var7 - var5.posX;
/*  98 */       double var25 = var9 - var5.posY;
/*  99 */       double var27 = var11 - var5.posZ;
/* 100 */       double var29 = Math.sqrt(var23 * var23 + var25 * var25 + var27 * var27);
/*     */       
/* 102 */       if (var29 > 0.0D) {
/*     */         
/* 104 */         var7 = var5.posX + var23 / var29 * 2.0D;
/* 105 */         var9 = var5.posY + var25 / var29 * 2.0D;
/* 106 */         var11 = var5.posZ + var27 / var29 * 2.0D;
/*     */       } 
/*     */       
/* 109 */       var13 = var17;
/*     */     } 
/*     */     
/* 112 */     var5.playerNetServerHandler.sendPacket((Packet)new S29PacketSoundEffect(var4, var7, var9, var11, (float)var13, (float)var15));
/* 113 */     notifyOperators(sender, this, "commands.playsound.success", new Object[] { var4, var5.getName() });
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public List addTabCompletionOptions(ICommandSender sender, String[] args, BlockPos pos) {
/* 119 */     return (args.length == 2) ? getListOfStringsMatchingLastWord(args, MinecraftServer.getServer().getAllUsernames()) : ((args.length > 2 && args.length <= 5) ? func_175771_a(args, 2, pos) : null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isUsernameIndex(String[] args, int index) {
/* 127 */     return (index == 1);
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\command\CommandPlaySound.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */