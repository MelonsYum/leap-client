/*     */ package net.minecraft.command;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.FileWriter;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.Date;
/*     */ import java.util.List;
/*     */ import net.minecraft.profiler.Profiler;
/*     */ import net.minecraft.server.MinecraftServer;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ 
/*     */ public class CommandDebug
/*     */   extends CommandBase {
/*  16 */   private static final Logger logger = LogManager.getLogger();
/*     */   
/*     */   private long field_147206_b;
/*     */   private int field_147207_c;
/*     */   private static final String __OBFID = "CL_00000270";
/*     */   
/*     */   public String getCommandName() {
/*  23 */     return "debug";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getRequiredPermissionLevel() {
/*  31 */     return 3;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getCommandUsage(ICommandSender sender) {
/*  36 */     return "commands.debug.usage";
/*     */   }
/*     */ 
/*     */   
/*     */   public void processCommand(ICommandSender sender, String[] args) throws CommandException {
/*  41 */     if (args.length < 1)
/*     */     {
/*  43 */       throw new WrongUsageException("commands.debug.usage", new Object[0]);
/*     */     }
/*     */ 
/*     */     
/*  47 */     if (args[0].equals("start")) {
/*     */       
/*  49 */       if (args.length != 1)
/*     */       {
/*  51 */         throw new WrongUsageException("commands.debug.usage", new Object[0]);
/*     */       }
/*     */       
/*  54 */       notifyOperators(sender, this, "commands.debug.start", new Object[0]);
/*  55 */       MinecraftServer.getServer().enableProfiling();
/*  56 */       this.field_147206_b = MinecraftServer.getCurrentTimeMillis();
/*  57 */       this.field_147207_c = MinecraftServer.getServer().getTickCounter();
/*     */     }
/*  59 */     else if (args[0].equals("stop")) {
/*     */       
/*  61 */       if (args.length != 1)
/*     */       {
/*  63 */         throw new WrongUsageException("commands.debug.usage", new Object[0]);
/*     */       }
/*     */       
/*  66 */       if (!(MinecraftServer.getServer()).theProfiler.profilingEnabled)
/*     */       {
/*  68 */         throw new CommandException("commands.debug.notStarted", new Object[0]);
/*     */       }
/*     */       
/*  71 */       long var3 = MinecraftServer.getCurrentTimeMillis();
/*  72 */       int var5 = MinecraftServer.getServer().getTickCounter();
/*  73 */       long var6 = var3 - this.field_147206_b;
/*  74 */       int var8 = var5 - this.field_147207_c;
/*  75 */       func_147205_a(var6, var8);
/*  76 */       (MinecraftServer.getServer()).theProfiler.profilingEnabled = false;
/*  77 */       notifyOperators(sender, this, "commands.debug.stop", new Object[] { Float.valueOf((float)var6 / 1000.0F), Integer.valueOf(var8) });
/*     */     }
/*  79 */     else if (args[0].equals("chunk")) {
/*     */       
/*  81 */       if (args.length != 4)
/*     */       {
/*  83 */         throw new WrongUsageException("commands.debug.usage", new Object[0]);
/*     */       }
/*     */       
/*  86 */       func_175757_a(sender, args, 1, true);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void func_147205_a(long p_147205_1_, int p_147205_3_) {
/*  93 */     File var4 = new File(MinecraftServer.getServer().getFile("debug"), "profile-results-" + (new SimpleDateFormat("yyyy-MM-dd_HH.mm.ss")).format(new Date()) + ".txt");
/*  94 */     var4.getParentFile().mkdirs();
/*     */ 
/*     */     
/*     */     try {
/*  98 */       FileWriter var5 = new FileWriter(var4);
/*  99 */       var5.write(func_147204_b(p_147205_1_, p_147205_3_));
/* 100 */       var5.close();
/*     */     }
/* 102 */     catch (Throwable var6) {
/*     */       
/* 104 */       logger.error("Could not save profiler results to " + var4, var6);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private String func_147204_b(long p_147204_1_, int p_147204_3_) {
/* 110 */     StringBuilder var4 = new StringBuilder();
/* 111 */     var4.append("---- Minecraft Profiler Results ----\n");
/* 112 */     var4.append("// ");
/* 113 */     var4.append(func_147203_d());
/* 114 */     var4.append("\n\n");
/* 115 */     var4.append("Time span: ").append(p_147204_1_).append(" ms\n");
/* 116 */     var4.append("Tick span: ").append(p_147204_3_).append(" ticks\n");
/* 117 */     var4.append("// This is approximately ").append(String.format("%.2f", new Object[] { Float.valueOf(p_147204_3_ / (float)p_147204_1_ / 1000.0F) })).append(" ticks per second. It should be ").append(20).append(" ticks per second\n\n");
/* 118 */     var4.append("--- BEGIN PROFILE DUMP ---\n\n");
/* 119 */     func_147202_a(0, "root", var4);
/* 120 */     var4.append("--- END PROFILE DUMP ---\n\n");
/* 121 */     return var4.toString();
/*     */   }
/*     */ 
/*     */   
/*     */   private void func_147202_a(int p_147202_1_, String p_147202_2_, StringBuilder p_147202_3_) {
/* 126 */     List<Profiler.Result> var4 = (MinecraftServer.getServer()).theProfiler.getProfilingData(p_147202_2_);
/*     */     
/* 128 */     if (var4 != null && var4.size() >= 3)
/*     */     {
/* 130 */       for (int var5 = 1; var5 < var4.size(); var5++) {
/*     */         
/* 132 */         Profiler.Result var6 = var4.get(var5);
/* 133 */         p_147202_3_.append(String.format("[%02d] ", new Object[] { Integer.valueOf(p_147202_1_) }));
/*     */         
/* 135 */         for (int var7 = 0; var7 < p_147202_1_; var7++)
/*     */         {
/* 137 */           p_147202_3_.append(" ");
/*     */         }
/*     */         
/* 140 */         p_147202_3_.append(var6.field_76331_c).append(" - ").append(String.format("%.2f", new Object[] { Double.valueOf(var6.field_76332_a) })).append("%/").append(String.format("%.2f", new Object[] { Double.valueOf(var6.field_76330_b) })).append("%\n");
/*     */         
/* 142 */         if (!var6.field_76331_c.equals("unspecified")) {
/*     */           
/*     */           try {
/*     */             
/* 146 */             func_147202_a(p_147202_1_ + 1, String.valueOf(p_147202_2_) + "." + var6.field_76331_c, p_147202_3_);
/*     */           }
/* 148 */           catch (Exception var8) {
/*     */             
/* 150 */             p_147202_3_.append("[[ EXCEPTION ").append(var8).append(" ]]");
/*     */           } 
/*     */         }
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private static String func_147203_d() {
/* 159 */     String[] var0 = { "Shiny numbers!", "Am I not running fast enough? :(", "I'm working as hard as I can!", "Will I ever be good enough for you? :(", "Speedy. Zoooooom!", "Hello world", "40% better than a crash report.", "Now with extra numbers", "Now with less numbers", "Now with the same numbers", "You should add flames to things, it makes them go faster!", "Do you feel the need for... optimization?", "*cracks redstone whip*", "Maybe if you treated it better then it'll have more motivation to work faster! Poor server." };
/*     */ 
/*     */     
/*     */     try {
/* 163 */       return var0[(int)(System.nanoTime() % var0.length)];
/*     */     }
/* 165 */     catch (Throwable var2) {
/*     */       
/* 167 */       return "Witty comment unavailable :(";
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public List addTabCompletionOptions(ICommandSender sender, String[] args, BlockPos pos) {
/* 173 */     return (args.length == 1) ? getListOfStringsMatchingLastWord(args, new String[] { "start", "stop" }) : null;
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\command\CommandDebug.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */