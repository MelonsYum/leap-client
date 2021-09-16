/*     */ package net.minecraft.command;
/*     */ 
/*     */ import java.util.List;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.potion.Potion;
/*     */ import net.minecraft.potion.PotionEffect;
/*     */ import net.minecraft.server.MinecraftServer;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.ChatComponentTranslation;
/*     */ 
/*     */ public class CommandEffect
/*     */   extends CommandBase
/*     */ {
/*     */   private static final String __OBFID = "CL_00000323";
/*     */   
/*     */   public String getCommandName() {
/*  17 */     return "effect";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getRequiredPermissionLevel() {
/*  25 */     return 2;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getCommandUsage(ICommandSender sender) {
/*  30 */     return "commands.effect.usage";
/*     */   }
/*     */ 
/*     */   
/*     */   public void processCommand(ICommandSender sender, String[] args) throws CommandException {
/*  35 */     if (args.length < 2)
/*     */     {
/*  37 */       throw new WrongUsageException("commands.effect.usage", new Object[0]);
/*     */     }
/*     */ 
/*     */     
/*  41 */     EntityLivingBase var3 = (EntityLivingBase)func_175759_a(sender, args[0], EntityLivingBase.class);
/*     */     
/*  43 */     if (args[1].equals("clear")) {
/*     */       
/*  45 */       if (var3.getActivePotionEffects().isEmpty())
/*     */       {
/*  47 */         throw new CommandException("commands.effect.failure.notActive.all", new Object[] { var3.getName() });
/*     */       }
/*     */ 
/*     */       
/*  51 */       var3.clearActivePotions();
/*  52 */       notifyOperators(sender, this, "commands.effect.success.removed.all", new Object[] { var3.getName() });
/*     */     } else {
/*     */       int var4;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/*  61 */         var4 = parseInt(args[1], 1);
/*     */       }
/*  63 */       catch (NumberInvalidException var11) {
/*     */         
/*  65 */         Potion var6 = Potion.func_180142_b(args[1]);
/*     */         
/*  67 */         if (var6 == null)
/*     */         {
/*  69 */           throw var11;
/*     */         }
/*     */         
/*  72 */         var4 = var6.id;
/*     */       } 
/*     */       
/*  75 */       int var5 = 600;
/*  76 */       int var12 = 30;
/*  77 */       int var7 = 0;
/*     */       
/*  79 */       if (var4 >= 0 && var4 < Potion.potionTypes.length && Potion.potionTypes[var4] != null) {
/*     */         
/*  81 */         Potion var8 = Potion.potionTypes[var4];
/*     */         
/*  83 */         if (args.length >= 3) {
/*     */           
/*  85 */           var12 = parseInt(args[2], 0, 1000000);
/*     */           
/*  87 */           if (var8.isInstant())
/*     */           {
/*  89 */             var5 = var12;
/*     */           }
/*     */           else
/*     */           {
/*  93 */             var5 = var12 * 20;
/*     */           }
/*     */         
/*  96 */         } else if (var8.isInstant()) {
/*     */           
/*  98 */           var5 = 1;
/*     */         } 
/*     */         
/* 101 */         if (args.length >= 4)
/*     */         {
/* 103 */           var7 = parseInt(args[3], 0, 255);
/*     */         }
/*     */         
/* 106 */         boolean var9 = true;
/*     */         
/* 108 */         if (args.length >= 5 && "true".equalsIgnoreCase(args[4]))
/*     */         {
/* 110 */           var9 = false;
/*     */         }
/*     */         
/* 113 */         if (var12 > 0)
/*     */         {
/* 115 */           PotionEffect var10 = new PotionEffect(var4, var5, var7, false, var9);
/* 116 */           var3.addPotionEffect(var10);
/* 117 */           notifyOperators(sender, this, "commands.effect.success", new Object[] { new ChatComponentTranslation(var10.getEffectName(), new Object[0]), Integer.valueOf(var4), Integer.valueOf(var7), var3.getName(), Integer.valueOf(var12) });
/*     */         }
/* 119 */         else if (var3.isPotionActive(var4))
/*     */         {
/* 121 */           var3.removePotionEffect(var4);
/* 122 */           notifyOperators(sender, this, "commands.effect.success.removed", new Object[] { new ChatComponentTranslation(var8.getName(), new Object[0]), var3.getName() });
/*     */         }
/*     */         else
/*     */         {
/* 126 */           throw new CommandException("commands.effect.failure.notActive", new Object[] { new ChatComponentTranslation(var8.getName(), new Object[0]), var3.getName() });
/*     */         }
/*     */       
/*     */       } else {
/*     */         
/* 131 */         throw new NumberInvalidException("commands.effect.notFound", new Object[] { Integer.valueOf(var4) });
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public List addTabCompletionOptions(ICommandSender sender, String[] args, BlockPos pos) {
/* 139 */     return (args.length == 1) ? getListOfStringsMatchingLastWord(args, getAllUsernames()) : ((args.length == 2) ? getListOfStringsMatchingLastWord(args, Potion.func_180141_c()) : ((args.length == 5) ? getListOfStringsMatchingLastWord(args, new String[] { "true", "false" }) : null));
/*     */   }
/*     */ 
/*     */   
/*     */   protected String[] getAllUsernames() {
/* 144 */     return MinecraftServer.getServer().getAllUsernames();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isUsernameIndex(String[] args, int index) {
/* 152 */     return (index == 0);
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\command\CommandEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */