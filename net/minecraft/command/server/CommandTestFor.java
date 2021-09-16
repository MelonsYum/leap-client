/*    */ package net.minecraft.command.server;
/*    */ 
/*    */ import java.util.List;
/*    */ import net.minecraft.command.CommandBase;
/*    */ import net.minecraft.command.CommandException;
/*    */ import net.minecraft.command.ICommand;
/*    */ import net.minecraft.command.ICommandSender;
/*    */ import net.minecraft.command.WrongUsageException;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.nbt.JsonToNBT;
/*    */ import net.minecraft.nbt.NBTBase;
/*    */ import net.minecraft.nbt.NBTException;
/*    */ import net.minecraft.nbt.NBTTagCompound;
/*    */ import net.minecraft.server.MinecraftServer;
/*    */ import net.minecraft.util.BlockPos;
/*    */ 
/*    */ public class CommandTestFor extends CommandBase {
/*    */   private static final String __OBFID = "CL_00001182";
/*    */   
/*    */   public String getCommandName() {
/* 21 */     return "testfor";
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int getRequiredPermissionLevel() {
/* 29 */     return 2;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getCommandUsage(ICommandSender sender) {
/* 34 */     return "commands.testfor.usage";
/*    */   }
/*    */ 
/*    */   
/*    */   public void processCommand(ICommandSender sender, String[] args) throws CommandException {
/* 39 */     if (args.length < 1)
/*    */     {
/* 41 */       throw new WrongUsageException("commands.testfor.usage", new Object[0]);
/*    */     }
/*    */ 
/*    */     
/* 45 */     Entity var3 = func_175768_b(sender, args[0]);
/* 46 */     NBTTagCompound var4 = null;
/*    */     
/* 48 */     if (args.length >= 2) {
/*    */       
/*    */       try {
/*    */         
/* 52 */         var4 = JsonToNBT.func_180713_a(func_180529_a(args, 1));
/*    */       }
/* 54 */       catch (NBTException var6) {
/*    */         
/* 56 */         throw new CommandException("commands.testfor.tagError", new Object[] { var6.getMessage() });
/*    */       } 
/*    */     }
/*    */     
/* 60 */     if (var4 != null) {
/*    */       
/* 62 */       NBTTagCompound var5 = new NBTTagCompound();
/* 63 */       var3.writeToNBT(var5);
/*    */       
/* 65 */       if (!CommandTestForBlock.func_175775_a((NBTBase)var4, (NBTBase)var5, true))
/*    */       {
/* 67 */         throw new CommandException("commands.testfor.failure", new Object[] { var3.getName() });
/*    */       }
/*    */     } 
/*    */     
/* 71 */     notifyOperators(sender, (ICommand)this, "commands.testfor.success", new Object[] { var3.getName() });
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean isUsernameIndex(String[] args, int index) {
/* 80 */     return (index == 0);
/*    */   }
/*    */ 
/*    */   
/*    */   public List addTabCompletionOptions(ICommandSender sender, String[] args, BlockPos pos) {
/* 85 */     return (args.length == 1) ? getListOfStringsMatchingLastWord(args, MinecraftServer.getServer().getAllUsernames()) : null;
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\command\server\CommandTestFor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */