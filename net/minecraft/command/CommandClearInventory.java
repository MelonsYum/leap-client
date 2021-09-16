/*     */ package net.minecraft.command;
/*     */ 
/*     */ import java.util.List;
/*     */ import net.minecraft.entity.player.EntityPlayerMP;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.nbt.JsonToNBT;
/*     */ import net.minecraft.nbt.NBTException;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.server.MinecraftServer;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.ChatComponentTranslation;
/*     */ import net.minecraft.util.IChatComponent;
/*     */ 
/*     */ public class CommandClearInventory
/*     */   extends CommandBase {
/*     */   private static final String __OBFID = "CL_00000218";
/*     */   
/*     */   public String getCommandName() {
/*  19 */     return "clear";
/*     */   }
/*     */ 
/*     */   
/*     */   public String getCommandUsage(ICommandSender sender) {
/*  24 */     return "commands.clear.usage";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getRequiredPermissionLevel() {
/*  32 */     return 2;
/*     */   }
/*     */ 
/*     */   
/*     */   public void processCommand(ICommandSender sender, String[] args) throws CommandException {
/*  37 */     EntityPlayerMP var3 = (args.length == 0) ? getCommandSenderAsPlayer(sender) : getPlayer(sender, args[0]);
/*  38 */     Item var4 = (args.length >= 2) ? getItemByText(sender, args[1]) : null;
/*  39 */     int var5 = (args.length >= 3) ? parseInt(args[2], -1) : -1;
/*  40 */     int var6 = (args.length >= 4) ? parseInt(args[3], -1) : -1;
/*  41 */     NBTTagCompound var7 = null;
/*     */     
/*  43 */     if (args.length >= 5) {
/*     */       
/*     */       try {
/*     */         
/*  47 */         var7 = JsonToNBT.func_180713_a(func_180529_a(args, 4));
/*     */       }
/*  49 */       catch (NBTException var9) {
/*     */         
/*  51 */         throw new CommandException("commands.clear.tagError", new Object[] { var9.getMessage() });
/*     */       } 
/*     */     }
/*     */     
/*  55 */     if (args.length >= 2 && var4 == null)
/*     */     {
/*  57 */       throw new CommandException("commands.clear.failure", new Object[] { var3.getName() });
/*     */     }
/*     */ 
/*     */     
/*  61 */     int var8 = var3.inventory.func_174925_a(var4, var5, var6, var7);
/*  62 */     var3.inventoryContainer.detectAndSendChanges();
/*     */     
/*  64 */     if (!var3.capabilities.isCreativeMode)
/*     */     {
/*  66 */       var3.updateHeldItem();
/*     */     }
/*     */     
/*  69 */     sender.func_174794_a(CommandResultStats.Type.AFFECTED_ITEMS, var8);
/*     */     
/*  71 */     if (var8 == 0)
/*     */     {
/*  73 */       throw new CommandException("commands.clear.failure", new Object[] { var3.getName() });
/*     */     }
/*     */ 
/*     */     
/*  77 */     if (var6 == 0) {
/*     */       
/*  79 */       sender.addChatMessage((IChatComponent)new ChatComponentTranslation("commands.clear.testing", new Object[] { var3.getName(), Integer.valueOf(var8) }));
/*     */     }
/*     */     else {
/*     */       
/*  83 */       notifyOperators(sender, this, "commands.clear.success", new Object[] { var3.getName(), Integer.valueOf(var8) });
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List addTabCompletionOptions(ICommandSender sender, String[] args, BlockPos pos) {
/*  91 */     return (args.length == 1) ? getListOfStringsMatchingLastWord(args, func_147209_d()) : ((args.length == 2) ? func_175762_a(args, Item.itemRegistry.getKeys()) : null);
/*     */   }
/*     */ 
/*     */   
/*     */   protected String[] func_147209_d() {
/*  96 */     return MinecraftServer.getServer().getAllUsernames();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isUsernameIndex(String[] args, int index) {
/* 104 */     return (index == 0);
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\command\CommandClearInventory.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */