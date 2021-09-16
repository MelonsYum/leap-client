/*     */ package net.minecraft.command.server;
/*     */ 
/*     */ import java.util.List;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.command.CommandBase;
/*     */ import net.minecraft.command.CommandException;
/*     */ import net.minecraft.command.CommandResultStats;
/*     */ import net.minecraft.command.ICommand;
/*     */ import net.minecraft.command.ICommandSender;
/*     */ import net.minecraft.command.WrongUsageException;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.inventory.IInventory;
/*     */ import net.minecraft.nbt.JsonToNBT;
/*     */ import net.minecraft.nbt.NBTException;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class CommandSetBlock
/*     */   extends CommandBase {
/*     */   private static final String __OBFID = "CL_00000949";
/*     */   
/*     */   public String getCommandName() {
/*  26 */     return "setblock";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getRequiredPermissionLevel() {
/*  34 */     return 2;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getCommandUsage(ICommandSender sender) {
/*  39 */     return "commands.setblock.usage";
/*     */   }
/*     */ 
/*     */   
/*     */   public void processCommand(ICommandSender sender, String[] args) throws CommandException {
/*  44 */     if (args.length < 4)
/*     */     {
/*  46 */       throw new WrongUsageException("commands.setblock.usage", new Object[0]);
/*     */     }
/*     */ 
/*     */     
/*  50 */     sender.func_174794_a(CommandResultStats.Type.AFFECTED_BLOCKS, 0);
/*  51 */     BlockPos var3 = func_175757_a(sender, args, 0, false);
/*  52 */     Block var4 = CommandBase.getBlockByText(sender, args[3]);
/*  53 */     int var5 = 0;
/*     */     
/*  55 */     if (args.length >= 5)
/*     */     {
/*  57 */       var5 = parseInt(args[4], 0, 15);
/*     */     }
/*     */     
/*  60 */     World var6 = sender.getEntityWorld();
/*     */     
/*  62 */     if (!var6.isBlockLoaded(var3))
/*     */     {
/*  64 */       throw new CommandException("commands.setblock.outOfWorld", new Object[0]);
/*     */     }
/*     */ 
/*     */     
/*  68 */     NBTTagCompound var7 = new NBTTagCompound();
/*  69 */     boolean var8 = false;
/*     */     
/*  71 */     if (args.length >= 7 && var4.hasTileEntity()) {
/*     */       
/*  73 */       String var9 = getChatComponentFromNthArg(sender, args, 6).getUnformattedText();
/*     */ 
/*     */       
/*     */       try {
/*  77 */         var7 = JsonToNBT.func_180713_a(var9);
/*  78 */         var8 = true;
/*     */       }
/*  80 */       catch (NBTException var12) {
/*     */         
/*  82 */         throw new CommandException("commands.setblock.tagError", new Object[] { var12.getMessage() });
/*     */       } 
/*     */     } 
/*     */     
/*  86 */     if (args.length >= 6)
/*     */     {
/*  88 */       if (args[5].equals("destroy")) {
/*     */         
/*  90 */         var6.destroyBlock(var3, true);
/*     */         
/*  92 */         if (var4 == Blocks.air) {
/*     */           
/*  94 */           notifyOperators(sender, (ICommand)this, "commands.setblock.success", new Object[0]);
/*     */           
/*     */           return;
/*     */         } 
/*  98 */       } else if (args[5].equals("keep") && !var6.isAirBlock(var3)) {
/*     */         
/* 100 */         throw new CommandException("commands.setblock.noChange", new Object[0]);
/*     */       } 
/*     */     }
/*     */     
/* 104 */     TileEntity var13 = var6.getTileEntity(var3);
/*     */     
/* 106 */     if (var13 != null) {
/*     */       
/* 108 */       if (var13 instanceof IInventory)
/*     */       {
/* 110 */         ((IInventory)var13).clearInventory();
/*     */       }
/*     */       
/* 113 */       var6.setBlockState(var3, Blocks.air.getDefaultState(), (var4 == Blocks.air) ? 2 : 4);
/*     */     } 
/*     */     
/* 116 */     IBlockState var10 = var4.getStateFromMeta(var5);
/*     */     
/* 118 */     if (!var6.setBlockState(var3, var10, 2))
/*     */     {
/* 120 */       throw new CommandException("commands.setblock.noChange", new Object[0]);
/*     */     }
/*     */ 
/*     */     
/* 124 */     if (var8) {
/*     */       
/* 126 */       TileEntity var11 = var6.getTileEntity(var3);
/*     */       
/* 128 */       if (var11 != null) {
/*     */         
/* 130 */         var7.setInteger("x", var3.getX());
/* 131 */         var7.setInteger("y", var3.getY());
/* 132 */         var7.setInteger("z", var3.getZ());
/* 133 */         var11.readFromNBT(var7);
/*     */       } 
/*     */     } 
/*     */     
/* 137 */     var6.func_175722_b(var3, var10.getBlock());
/* 138 */     sender.func_174794_a(CommandResultStats.Type.AFFECTED_BLOCKS, 1);
/* 139 */     notifyOperators(sender, (ICommand)this, "commands.setblock.success", new Object[0]);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List addTabCompletionOptions(ICommandSender sender, String[] args, BlockPos pos) {
/* 147 */     return (args.length > 0 && args.length <= 3) ? func_175771_a(args, 0, pos) : ((args.length == 4) ? func_175762_a(args, Block.blockRegistry.getKeys()) : ((args.length == 6) ? getListOfStringsMatchingLastWord(args, new String[] { "replace", "destroy", "keep" }) : null));
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\command\server\CommandSetBlock.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */