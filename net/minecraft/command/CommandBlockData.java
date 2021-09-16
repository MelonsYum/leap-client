/*    */ package net.minecraft.command;
/*    */ 
/*    */ import java.util.List;
/*    */ import net.minecraft.nbt.JsonToNBT;
/*    */ import net.minecraft.nbt.NBTException;
/*    */ import net.minecraft.nbt.NBTTagCompound;
/*    */ import net.minecraft.tileentity.TileEntity;
/*    */ import net.minecraft.util.BlockPos;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ public class CommandBlockData
/*    */   extends CommandBase
/*    */ {
/*    */   private static final String __OBFID = "CL_00002349";
/*    */   
/*    */   public String getCommandName() {
/* 17 */     return "blockdata";
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int getRequiredPermissionLevel() {
/* 25 */     return 2;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getCommandUsage(ICommandSender sender) {
/* 30 */     return "commands.blockdata.usage";
/*    */   }
/*    */   
/*    */   public void processCommand(ICommandSender sender, String[] args) throws CommandException {
/*    */     NBTTagCompound var8;
/* 35 */     if (args.length < 4)
/*    */     {
/* 37 */       throw new WrongUsageException("commands.blockdata.usage", new Object[0]);
/*    */     }
/*    */ 
/*    */     
/* 41 */     sender.func_174794_a(CommandResultStats.Type.AFFECTED_BLOCKS, 0);
/* 42 */     BlockPos var3 = func_175757_a(sender, args, 0, false);
/* 43 */     World var4 = sender.getEntityWorld();
/*    */     
/* 45 */     if (!var4.isBlockLoaded(var3))
/*    */     {
/* 47 */       throw new CommandException("commands.blockdata.outOfWorld", new Object[0]);
/*    */     }
/*    */ 
/*    */     
/* 51 */     TileEntity var5 = var4.getTileEntity(var3);
/*    */     
/* 53 */     if (var5 == null)
/*    */     {
/* 55 */       throw new CommandException("commands.blockdata.notValid", new Object[0]);
/*    */     }
/*    */ 
/*    */     
/* 59 */     NBTTagCompound var6 = new NBTTagCompound();
/* 60 */     var5.writeToNBT(var6);
/* 61 */     NBTTagCompound var7 = (NBTTagCompound)var6.copy();
/*    */ 
/*    */ 
/*    */     
/*    */     try {
/* 66 */       var8 = JsonToNBT.func_180713_a(getChatComponentFromNthArg(sender, args, 3).getUnformattedText());
/*    */     }
/* 68 */     catch (NBTException var10) {
/*    */       
/* 70 */       throw new CommandException("commands.blockdata.tagError", new Object[] { var10.getMessage() });
/*    */     } 
/*    */     
/* 73 */     var6.merge(var8);
/* 74 */     var6.setInteger("x", var3.getX());
/* 75 */     var6.setInteger("y", var3.getY());
/* 76 */     var6.setInteger("z", var3.getZ());
/*    */     
/* 78 */     if (var6.equals(var7))
/*    */     {
/* 80 */       throw new CommandException("commands.blockdata.failed", new Object[] { var6.toString() });
/*    */     }
/*    */ 
/*    */     
/* 84 */     var5.readFromNBT(var6);
/* 85 */     var5.markDirty();
/* 86 */     var4.markBlockForUpdate(var3);
/* 87 */     sender.func_174794_a(CommandResultStats.Type.AFFECTED_BLOCKS, 1);
/* 88 */     notifyOperators(sender, this, "commands.blockdata.success", new Object[] { var6.toString() });
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public List addTabCompletionOptions(ICommandSender sender, String[] args, BlockPos pos) {
/* 97 */     return (args.length > 0 && args.length <= 3) ? func_175771_a(args, 0, pos) : null;
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\command\CommandBlockData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */