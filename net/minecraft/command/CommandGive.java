/*     */ package net.minecraft.command;
/*     */ 
/*     */ import java.util.List;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.item.EntityItem;
/*     */ import net.minecraft.entity.player.EntityPlayerMP;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.JsonToNBT;
/*     */ import net.minecraft.nbt.NBTException;
/*     */ import net.minecraft.server.MinecraftServer;
/*     */ import net.minecraft.util.BlockPos;
/*     */ 
/*     */ public class CommandGive
/*     */   extends CommandBase {
/*     */   private static final String __OBFID = "CL_00000502";
/*     */   
/*     */   public String getCommandName() {
/*  19 */     return "give";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getRequiredPermissionLevel() {
/*  27 */     return 2;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getCommandUsage(ICommandSender sender) {
/*  32 */     return "commands.give.usage";
/*     */   }
/*     */ 
/*     */   
/*     */   public void processCommand(ICommandSender sender, String[] args) throws CommandException {
/*  37 */     if (args.length < 2)
/*     */     {
/*  39 */       throw new WrongUsageException("commands.give.usage", new Object[0]);
/*     */     }
/*     */ 
/*     */     
/*  43 */     EntityPlayerMP var3 = getPlayer(sender, args[0]);
/*  44 */     Item var4 = getItemByText(sender, args[1]);
/*  45 */     int var5 = (args.length >= 3) ? parseInt(args[2], 1, 64) : 1;
/*  46 */     int var6 = (args.length >= 4) ? parseInt(args[3]) : 0;
/*  47 */     ItemStack var7 = new ItemStack(var4, var5, var6);
/*     */     
/*  49 */     if (args.length >= 5) {
/*     */       
/*  51 */       String var8 = getChatComponentFromNthArg(sender, args, 4).getUnformattedText();
/*     */ 
/*     */       
/*     */       try {
/*  55 */         var7.setTagCompound(JsonToNBT.func_180713_a(var8));
/*     */       }
/*  57 */       catch (NBTException var10) {
/*     */         
/*  59 */         throw new CommandException("commands.give.tagError", new Object[] { var10.getMessage() });
/*     */       } 
/*     */     } 
/*     */     
/*  63 */     boolean var11 = var3.inventory.addItemStackToInventory(var7);
/*     */     
/*  65 */     if (var11) {
/*     */       
/*  67 */       var3.worldObj.playSoundAtEntity((Entity)var3, "random.pop", 0.2F, ((var3.getRNG().nextFloat() - var3.getRNG().nextFloat()) * 0.7F + 1.0F) * 2.0F);
/*  68 */       var3.inventoryContainer.detectAndSendChanges();
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/*  73 */     if (var11 && var7.stackSize <= 0) {
/*     */       
/*  75 */       var7.stackSize = 1;
/*  76 */       sender.func_174794_a(CommandResultStats.Type.AFFECTED_ITEMS, var5);
/*  77 */       EntityItem var9 = var3.dropPlayerItemWithRandomChoice(var7, false);
/*     */       
/*  79 */       if (var9 != null)
/*     */       {
/*  81 */         var9.func_174870_v();
/*     */       }
/*     */     }
/*     */     else {
/*     */       
/*  86 */       sender.func_174794_a(CommandResultStats.Type.AFFECTED_ITEMS, var5 - var7.stackSize);
/*  87 */       EntityItem var9 = var3.dropPlayerItemWithRandomChoice(var7, false);
/*     */       
/*  89 */       if (var9 != null) {
/*     */         
/*  91 */         var9.setNoPickupDelay();
/*  92 */         var9.setOwner(var3.getName());
/*     */       } 
/*     */     } 
/*     */     
/*  96 */     notifyOperators(sender, this, "commands.give.success", new Object[] { var7.getChatComponent(), Integer.valueOf(var5), var3.getName() });
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public List addTabCompletionOptions(ICommandSender sender, String[] args, BlockPos pos) {
/* 102 */     return (args.length == 1) ? getListOfStringsMatchingLastWord(args, getPlayers()) : ((args.length == 2) ? func_175762_a(args, Item.itemRegistry.getKeys()) : null);
/*     */   }
/*     */ 
/*     */   
/*     */   protected String[] getPlayers() {
/* 107 */     return MinecraftServer.getServer().getAllUsernames();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isUsernameIndex(String[] args, int index) {
/* 115 */     return (index == 0);
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\command\CommandGive.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */