/*     */ package net.minecraft.command;
/*     */ 
/*     */ import java.util.List;
/*     */ import net.minecraft.server.MinecraftServer;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.ChatComponentTranslation;
/*     */ import net.minecraft.util.IChatComponent;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.world.border.WorldBorder;
/*     */ 
/*     */ public class CommandWorldBorder
/*     */   extends CommandBase {
/*     */   private static final String __OBFID = "CL_00002336";
/*     */   
/*     */   public String getCommandName() {
/*  16 */     return "worldborder";
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
/*  29 */     return "commands.worldborder.usage";
/*     */   }
/*     */ 
/*     */   
/*     */   public void processCommand(ICommandSender sender, String[] args) throws CommandException {
/*  34 */     if (args.length < 1)
/*     */     {
/*  36 */       throw new WrongUsageException("commands.worldborder.usage", new Object[0]);
/*     */     }
/*     */ 
/*     */     
/*  40 */     WorldBorder var3 = getWorldBorder();
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  45 */     if (args[0].equals("set")) {
/*     */       
/*  47 */       if (args.length != 2 && args.length != 3)
/*     */       {
/*  49 */         throw new WrongUsageException("commands.worldborder.set.usage", new Object[0]);
/*     */       }
/*     */       
/*  52 */       double var4 = var3.getTargetSize();
/*  53 */       double var6 = parseDouble(args[1], 1.0D, 6.0E7D);
/*  54 */       long var8 = (args.length > 2) ? (parseLong(args[2], 0L, 9223372036854775L) * 1000L) : 0L;
/*     */       
/*  56 */       if (var8 > 0L) {
/*     */         
/*  58 */         var3.setTransition(var4, var6, var8);
/*     */         
/*  60 */         if (var4 > var6)
/*     */         {
/*  62 */           notifyOperators(sender, this, "commands.worldborder.setSlowly.shrink.success", new Object[] { String.format("%.1f", new Object[] { Double.valueOf(var6) }), String.format("%.1f", new Object[] { Double.valueOf(var4) }), Long.toString(var8 / 1000L) });
/*     */         }
/*     */         else
/*     */         {
/*  66 */           notifyOperators(sender, this, "commands.worldborder.setSlowly.grow.success", new Object[] { String.format("%.1f", new Object[] { Double.valueOf(var6) }), String.format("%.1f", new Object[] { Double.valueOf(var4) }), Long.toString(var8 / 1000L) });
/*     */         }
/*     */       
/*     */       } else {
/*     */         
/*  71 */         var3.setTransition(var6);
/*  72 */         notifyOperators(sender, this, "commands.worldborder.set.success", new Object[] { String.format("%.1f", new Object[] { Double.valueOf(var6) }), String.format("%.1f", new Object[] { Double.valueOf(var4) }) });
/*     */       }
/*     */     
/*  75 */     } else if (args[0].equals("add")) {
/*     */       
/*  77 */       if (args.length != 2 && args.length != 3)
/*     */       {
/*  79 */         throw new WrongUsageException("commands.worldborder.add.usage", new Object[0]);
/*     */       }
/*     */       
/*  82 */       double var4 = var3.getDiameter();
/*  83 */       double var6 = var4 + parseDouble(args[1], -var4, 6.0E7D - var4);
/*  84 */       long var8 = var3.getTimeUntilTarget() + ((args.length > 2) ? (parseLong(args[2], 0L, 9223372036854775L) * 1000L) : 0L);
/*     */       
/*  86 */       if (var8 > 0L) {
/*     */         
/*  88 */         var3.setTransition(var4, var6, var8);
/*     */         
/*  90 */         if (var4 > var6)
/*     */         {
/*  92 */           notifyOperators(sender, this, "commands.worldborder.setSlowly.shrink.success", new Object[] { String.format("%.1f", new Object[] { Double.valueOf(var6) }), String.format("%.1f", new Object[] { Double.valueOf(var4) }), Long.toString(var8 / 1000L) });
/*     */         }
/*     */         else
/*     */         {
/*  96 */           notifyOperators(sender, this, "commands.worldborder.setSlowly.grow.success", new Object[] { String.format("%.1f", new Object[] { Double.valueOf(var6) }), String.format("%.1f", new Object[] { Double.valueOf(var4) }), Long.toString(var8 / 1000L) });
/*     */         }
/*     */       
/*     */       } else {
/*     */         
/* 101 */         var3.setTransition(var6);
/* 102 */         notifyOperators(sender, this, "commands.worldborder.set.success", new Object[] { String.format("%.1f", new Object[] { Double.valueOf(var6) }), String.format("%.1f", new Object[] { Double.valueOf(var4) }) });
/*     */       }
/*     */     
/* 105 */     } else if (args[0].equals("center")) {
/*     */       
/* 107 */       if (args.length != 3)
/*     */       {
/* 109 */         throw new WrongUsageException("commands.worldborder.center.usage", new Object[0]);
/*     */       }
/*     */       
/* 112 */       BlockPos var10 = sender.getPosition();
/* 113 */       double var5 = func_175761_b(var10.getX() + 0.5D, args[1], true);
/* 114 */       double var7 = func_175761_b(var10.getZ() + 0.5D, args[2], true);
/* 115 */       var3.setCenter(var5, var7);
/* 116 */       notifyOperators(sender, this, "commands.worldborder.center.success", new Object[] { Double.valueOf(var5), Double.valueOf(var7) });
/*     */     }
/* 118 */     else if (args[0].equals("damage")) {
/*     */       
/* 120 */       if (args.length < 2)
/*     */       {
/* 122 */         throw new WrongUsageException("commands.worldborder.damage.usage", new Object[0]);
/*     */       }
/*     */       
/* 125 */       if (args[1].equals("buffer"))
/*     */       {
/* 127 */         if (args.length != 3)
/*     */         {
/* 129 */           throw new WrongUsageException("commands.worldborder.damage.buffer.usage", new Object[0]);
/*     */         }
/*     */         
/* 132 */         double var4 = parseDouble(args[2], 0.0D);
/* 133 */         double var6 = var3.getDamageBuffer();
/* 134 */         var3.setDamageBuffer(var4);
/* 135 */         notifyOperators(sender, this, "commands.worldborder.damage.buffer.success", new Object[] { String.format("%.1f", new Object[] { Double.valueOf(var4) }), String.format("%.1f", new Object[] { Double.valueOf(var6) }) });
/*     */       }
/* 137 */       else if (args[1].equals("amount"))
/*     */       {
/* 139 */         if (args.length != 3)
/*     */         {
/* 141 */           throw new WrongUsageException("commands.worldborder.damage.amount.usage", new Object[0]);
/*     */         }
/*     */         
/* 144 */         double var4 = parseDouble(args[2], 0.0D);
/* 145 */         double var6 = var3.func_177727_n();
/* 146 */         var3.func_177744_c(var4);
/* 147 */         notifyOperators(sender, this, "commands.worldborder.damage.amount.success", new Object[] { String.format("%.2f", new Object[] { Double.valueOf(var4) }), String.format("%.2f", new Object[] { Double.valueOf(var6) }) });
/*     */       }
/*     */     
/* 150 */     } else if (args[0].equals("warning")) {
/*     */       
/* 152 */       if (args.length < 2)
/*     */       {
/* 154 */         throw new WrongUsageException("commands.worldborder.warning.usage", new Object[0]);
/*     */       }
/*     */       
/* 157 */       int var11 = parseInt(args[2], 0);
/*     */ 
/*     */       
/* 160 */       if (args[1].equals("time"))
/*     */       {
/* 162 */         if (args.length != 3)
/*     */         {
/* 164 */           throw new WrongUsageException("commands.worldborder.warning.time.usage", new Object[0]);
/*     */         }
/*     */         
/* 167 */         int var12 = var3.getWarningTime();
/* 168 */         var3.setWarningTime(var11);
/* 169 */         notifyOperators(sender, this, "commands.worldborder.warning.time.success", new Object[] { Integer.valueOf(var11), Integer.valueOf(var12) });
/*     */       }
/* 171 */       else if (args[1].equals("distance"))
/*     */       {
/* 173 */         if (args.length != 3)
/*     */         {
/* 175 */           throw new WrongUsageException("commands.worldborder.warning.distance.usage", new Object[0]);
/*     */         }
/*     */         
/* 178 */         int var12 = var3.getWarningDistance();
/* 179 */         var3.setWarningDistance(var11);
/* 180 */         notifyOperators(sender, this, "commands.worldborder.warning.distance.success", new Object[] { Integer.valueOf(var11), Integer.valueOf(var12) });
/*     */       }
/*     */     
/* 183 */     } else if (args[0].equals("get")) {
/*     */       
/* 185 */       double var4 = var3.getDiameter();
/* 186 */       sender.func_174794_a(CommandResultStats.Type.QUERY_RESULT, MathHelper.floor_double(var4 + 0.5D));
/* 187 */       sender.addChatMessage((IChatComponent)new ChatComponentTranslation("commands.worldborder.get.success", new Object[] { String.format("%.0f", new Object[] { Double.valueOf(var4) }) }));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected WorldBorder getWorldBorder() {
/* 194 */     return (MinecraftServer.getServer()).worldServers[0].getWorldBorder();
/*     */   }
/*     */ 
/*     */   
/*     */   public List addTabCompletionOptions(ICommandSender sender, String[] args, BlockPos pos) {
/* 199 */     return (args.length == 1) ? getListOfStringsMatchingLastWord(args, new String[] { "set", "center", "damage", "warning", "add", "get" }) : ((args.length == 2 && args[0].equals("damage")) ? getListOfStringsMatchingLastWord(args, new String[] { "buffer", "amount" }) : ((args.length == 2 && args[0].equals("warning")) ? getListOfStringsMatchingLastWord(args, new String[] { "time", "distance" }) : null));
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\command\CommandWorldBorder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */