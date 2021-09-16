/*     */ package net.minecraft.command;
/*     */ 
/*     */ import java.util.List;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.EnumParticleTypes;
/*     */ import net.minecraft.util.Vec3;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.WorldServer;
/*     */ 
/*     */ public class CommandParticle
/*     */   extends CommandBase
/*     */ {
/*     */   private static final String __OBFID = "CL_00002341";
/*     */   
/*     */   public String getCommandName() {
/*  16 */     return "particle";
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
/*  29 */     return "commands.particle.usage";
/*     */   }
/*     */ 
/*     */   
/*     */   public void processCommand(ICommandSender sender, String[] args) throws CommandException {
/*  34 */     if (args.length < 8)
/*     */     {
/*  36 */       throw new WrongUsageException("commands.particle.usage", new Object[0]);
/*     */     }
/*     */ 
/*     */     
/*  40 */     boolean var3 = false;
/*  41 */     EnumParticleTypes var4 = null;
/*  42 */     EnumParticleTypes[] var5 = EnumParticleTypes.values();
/*  43 */     int var6 = var5.length;
/*     */     
/*  45 */     for (int var7 = 0; var7 < var6; var7++) {
/*     */       
/*  47 */       EnumParticleTypes var8 = var5[var7];
/*     */       
/*  49 */       if (var8.func_179343_f()) {
/*     */         
/*  51 */         if (args[0].startsWith(var8.func_179346_b())) {
/*     */           
/*  53 */           var3 = true;
/*  54 */           var4 = var8;
/*     */           
/*     */           break;
/*     */         } 
/*  58 */       } else if (args[0].equals(var8.func_179346_b())) {
/*     */         
/*  60 */         var3 = true;
/*  61 */         var4 = var8;
/*     */         
/*     */         break;
/*     */       } 
/*     */     } 
/*  66 */     if (!var3)
/*     */     {
/*  68 */       throw new CommandException("commands.particle.notFound", new Object[] { args[0] });
/*     */     }
/*     */ 
/*     */     
/*  72 */     String var30 = args[0];
/*  73 */     Vec3 var31 = sender.getPositionVector();
/*  74 */     double var32 = (float)func_175761_b(var31.xCoord, args[1], true);
/*  75 */     double var9 = (float)func_175761_b(var31.yCoord, args[2], true);
/*  76 */     double var11 = (float)func_175761_b(var31.zCoord, args[3], true);
/*  77 */     double var13 = (float)parseDouble(args[4]);
/*  78 */     double var15 = (float)parseDouble(args[5]);
/*  79 */     double var17 = (float)parseDouble(args[6]);
/*  80 */     double var19 = (float)parseDouble(args[7]);
/*  81 */     int var21 = 0;
/*     */     
/*  83 */     if (args.length > 8)
/*     */     {
/*  85 */       var21 = parseInt(args[8], 0);
/*     */     }
/*     */     
/*  88 */     boolean var22 = false;
/*     */     
/*  90 */     if (args.length > 9 && "force".equals(args[9]))
/*     */     {
/*  92 */       var22 = true;
/*     */     }
/*     */     
/*  95 */     World var23 = sender.getEntityWorld();
/*     */     
/*  97 */     if (var23 instanceof WorldServer) {
/*     */       
/*  99 */       WorldServer var24 = (WorldServer)var23;
/* 100 */       int[] var25 = new int[var4.func_179345_d()];
/*     */       
/* 102 */       if (var4.func_179343_f()) {
/*     */         
/* 104 */         String[] var26 = args[0].split("_", 3);
/*     */         
/* 106 */         for (int var27 = 1; var27 < var26.length; var27++) {
/*     */ 
/*     */           
/*     */           try {
/* 110 */             var25[var27 - 1] = Integer.parseInt(var26[var27]);
/*     */           }
/* 112 */           catch (NumberFormatException var29) {
/*     */             
/* 114 */             throw new CommandException("commands.particle.notFound", new Object[] { args[0] });
/*     */           } 
/*     */         } 
/*     */       } 
/*     */       
/* 119 */       var24.func_180505_a(var4, var22, var32, var9, var11, var21, var13, var15, var17, var19, var25);
/* 120 */       notifyOperators(sender, this, "commands.particle.success", new Object[] { var30, Integer.valueOf(Math.max(var21, 1)) });
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List addTabCompletionOptions(ICommandSender sender, String[] args, BlockPos pos) {
/* 128 */     return (args.length == 1) ? getListOfStringsMatchingLastWord(args, EnumParticleTypes.func_179349_a()) : ((args.length > 1 && args.length <= 4) ? func_175771_a(args, 1, pos) : ((args.length == 9) ? getListOfStringsMatchingLastWord(args, new String[] { "normal", "force" }) : null));
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\command\CommandParticle.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */