/*    */ package net.minecraft.command;
/*    */ 
/*    */ import java.util.List;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.nbt.JsonToNBT;
/*    */ import net.minecraft.nbt.NBTException;
/*    */ import net.minecraft.nbt.NBTTagCompound;
/*    */ import net.minecraft.server.MinecraftServer;
/*    */ import net.minecraft.util.BlockPos;
/*    */ 
/*    */ 
/*    */ public class CommandEntityData
/*    */   extends CommandBase
/*    */ {
/*    */   private static final String __OBFID = "CL_00002345";
/*    */   
/*    */   public String getCommandName() {
/* 18 */     return "entitydata";
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int getRequiredPermissionLevel() {
/* 26 */     return 2;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getCommandUsage(ICommandSender sender) {
/* 31 */     return "commands.entitydata.usage";
/*    */   }
/*    */   
/*    */   public void processCommand(ICommandSender sender, String[] args) throws CommandException {
/*    */     NBTTagCompound var6;
/* 36 */     if (args.length < 2)
/*    */     {
/* 38 */       throw new WrongUsageException("commands.entitydata.usage", new Object[0]);
/*    */     }
/*    */ 
/*    */     
/* 42 */     Entity var3 = func_175768_b(sender, args[0]);
/*    */     
/* 44 */     if (var3 instanceof net.minecraft.entity.player.EntityPlayer)
/*    */     {
/* 46 */       throw new CommandException("commands.entitydata.noPlayers", new Object[] { var3.getDisplayName() });
/*    */     }
/*    */ 
/*    */     
/* 50 */     NBTTagCompound var4 = new NBTTagCompound();
/* 51 */     var3.writeToNBT(var4);
/* 52 */     NBTTagCompound var5 = (NBTTagCompound)var4.copy();
/*    */ 
/*    */ 
/*    */     
/*    */     try {
/* 57 */       var6 = JsonToNBT.func_180713_a(getChatComponentFromNthArg(sender, args, 1).getUnformattedText());
/*    */     }
/* 59 */     catch (NBTException var8) {
/*    */       
/* 61 */       throw new CommandException("commands.entitydata.tagError", new Object[] { var8.getMessage() });
/*    */     } 
/*    */     
/* 64 */     var6.removeTag("UUIDMost");
/* 65 */     var6.removeTag("UUIDLeast");
/* 66 */     var4.merge(var6);
/*    */     
/* 68 */     if (var4.equals(var5))
/*    */     {
/* 70 */       throw new CommandException("commands.entitydata.failed", new Object[] { var4.toString() });
/*    */     }
/*    */ 
/*    */     
/* 74 */     var3.readFromNBT(var4);
/* 75 */     notifyOperators(sender, this, "commands.entitydata.success", new Object[] { var4.toString() });
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public List addTabCompletionOptions(ICommandSender sender, String[] args, BlockPos pos) {
/* 83 */     return (args.length == 1) ? getListOfStringsMatchingLastWord(args, MinecraftServer.getServer().getAllUsernames()) : null;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean isUsernameIndex(String[] args, int index) {
/* 91 */     return (index == 0);
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\command\CommandEntityData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */