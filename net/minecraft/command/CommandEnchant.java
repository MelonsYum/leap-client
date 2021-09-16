/*     */ package net.minecraft.command;
/*     */ 
/*     */ import java.util.List;
/*     */ import net.minecraft.enchantment.Enchantment;
/*     */ import net.minecraft.entity.player.EntityPlayerMP;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagList;
/*     */ import net.minecraft.server.MinecraftServer;
/*     */ import net.minecraft.util.BlockPos;
/*     */ 
/*     */ public class CommandEnchant
/*     */   extends CommandBase
/*     */ {
/*     */   private static final String __OBFID = "CL_00000377";
/*     */   
/*     */   public String getCommandName() {
/*  17 */     return "enchant";
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
/*  30 */     return "commands.enchant.usage";
/*     */   }
/*     */   
/*     */   public void processCommand(ICommandSender sender, String[] args) throws CommandException {
/*     */     int var4;
/*  35 */     if (args.length < 2)
/*     */     {
/*  37 */       throw new WrongUsageException("commands.enchant.usage", new Object[0]);
/*     */     }
/*     */ 
/*     */     
/*  41 */     EntityPlayerMP var3 = getPlayer(sender, args[0]);
/*  42 */     sender.func_174794_a(CommandResultStats.Type.AFFECTED_ITEMS, 0);
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/*  47 */       var4 = parseInt(args[1], 0);
/*     */     }
/*  49 */     catch (NumberInvalidException var12) {
/*     */       
/*  51 */       Enchantment var6 = Enchantment.func_180305_b(args[1]);
/*     */       
/*  53 */       if (var6 == null)
/*     */       {
/*  55 */         throw var12;
/*     */       }
/*     */       
/*  58 */       var4 = var6.effectId;
/*     */     } 
/*     */     
/*  61 */     int var5 = 1;
/*  62 */     ItemStack var13 = var3.getCurrentEquippedItem();
/*     */     
/*  64 */     if (var13 == null)
/*     */     {
/*  66 */       throw new CommandException("commands.enchant.noItem", new Object[0]);
/*     */     }
/*     */ 
/*     */     
/*  70 */     Enchantment var7 = Enchantment.func_180306_c(var4);
/*     */     
/*  72 */     if (var7 == null)
/*     */     {
/*  74 */       throw new NumberInvalidException("commands.enchant.notFound", new Object[] { Integer.valueOf(var4) });
/*     */     }
/*  76 */     if (!var7.canApply(var13))
/*     */     {
/*  78 */       throw new CommandException("commands.enchant.cantEnchant", new Object[0]);
/*     */     }
/*     */ 
/*     */     
/*  82 */     if (args.length >= 3)
/*     */     {
/*  84 */       var5 = parseInt(args[2], var7.getMinLevel(), var7.getMaxLevel());
/*     */     }
/*     */     
/*  87 */     if (var13.hasTagCompound()) {
/*     */       
/*  89 */       NBTTagList var8 = var13.getEnchantmentTagList();
/*     */       
/*  91 */       if (var8 != null)
/*     */       {
/*  93 */         for (int var9 = 0; var9 < var8.tagCount(); var9++) {
/*     */           
/*  95 */           short var10 = var8.getCompoundTagAt(var9).getShort("id");
/*     */           
/*  97 */           if (Enchantment.func_180306_c(var10) != null) {
/*     */             
/*  99 */             Enchantment var11 = Enchantment.func_180306_c(var10);
/*     */             
/* 101 */             if (!var11.canApplyTogether(var7))
/*     */             {
/* 103 */               throw new CommandException("commands.enchant.cantCombine", new Object[] { var7.getTranslatedName(var5), var11.getTranslatedName(var8.getCompoundTagAt(var9).getShort("lvl")) });
/*     */             }
/*     */           } 
/*     */         } 
/*     */       }
/*     */     } 
/*     */     
/* 110 */     var13.addEnchantment(var7, var5);
/* 111 */     notifyOperators(sender, this, "commands.enchant.success", new Object[0]);
/* 112 */     sender.func_174794_a(CommandResultStats.Type.AFFECTED_ITEMS, 1);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List addTabCompletionOptions(ICommandSender sender, String[] args, BlockPos pos) {
/* 120 */     return (args.length == 1) ? getListOfStringsMatchingLastWord(args, getListOfPlayers()) : ((args.length == 2) ? getListOfStringsMatchingLastWord(args, Enchantment.func_180304_c()) : null);
/*     */   }
/*     */ 
/*     */   
/*     */   protected String[] getListOfPlayers() {
/* 125 */     return MinecraftServer.getServer().getAllUsernames();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isUsernameIndex(String[] args, int index) {
/* 133 */     return (index == 0);
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\command\CommandEnchant.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */