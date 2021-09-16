/*     */ package net.minecraft.command;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.inventory.IInventory;
/*     */ import net.minecraft.nbt.JsonToNBT;
/*     */ import net.minecraft.nbt.NBTException;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class CommandFill
/*     */   extends CommandBase
/*     */ {
/*     */   private static final String __OBFID = "CL_00002342";
/*     */   
/*     */   public String getCommandName() {
/*  24 */     return "fill";
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
/*     */   public String getCommandUsage(ICommandSender sender) {
/*  37 */     return "commands.fill.usage";
/*     */   }
/*     */ 
/*     */   
/*     */   public void processCommand(ICommandSender sender, String[] args) throws CommandException {
/*  42 */     if (args.length < 7)
/*     */     {
/*  44 */       throw new WrongUsageException("commands.fill.usage", new Object[0]);
/*     */     }
/*     */ 
/*     */     
/*  48 */     sender.func_174794_a(CommandResultStats.Type.AFFECTED_BLOCKS, 0);
/*  49 */     BlockPos var3 = func_175757_a(sender, args, 0, false);
/*  50 */     BlockPos var4 = func_175757_a(sender, args, 3, false);
/*  51 */     Block var5 = CommandBase.getBlockByText(sender, args[6]);
/*  52 */     int var6 = 0;
/*     */     
/*  54 */     if (args.length >= 8)
/*     */     {
/*  56 */       var6 = parseInt(args[7], 0, 15);
/*     */     }
/*     */     
/*  59 */     BlockPos var7 = new BlockPos(Math.min(var3.getX(), var4.getX()), Math.min(var3.getY(), var4.getY()), Math.min(var3.getZ(), var4.getZ()));
/*  60 */     BlockPos var8 = new BlockPos(Math.max(var3.getX(), var4.getX()), Math.max(var3.getY(), var4.getY()), Math.max(var3.getZ(), var4.getZ()));
/*  61 */     int var9 = (var8.getX() - var7.getX() + 1) * (var8.getY() - var7.getY() + 1) * (var8.getZ() - var7.getZ() + 1);
/*     */     
/*  63 */     if (var9 > 32768)
/*     */     {
/*  65 */       throw new CommandException("commands.fill.tooManyBlocks", new Object[] { Integer.valueOf(var9), Integer.valueOf(32768) });
/*     */     }
/*  67 */     if (var7.getY() >= 0 && var8.getY() < 256) {
/*     */       
/*  69 */       World var10 = sender.getEntityWorld();
/*     */       
/*  71 */       for (int var11 = var7.getZ(); var11 < var8.getZ() + 16; var11 += 16) {
/*     */         
/*  73 */         for (int var12 = var7.getX(); var12 < var8.getX() + 16; var12 += 16) {
/*     */           
/*  75 */           if (!var10.isBlockLoaded(new BlockPos(var12, var8.getY() - var7.getY(), var11)))
/*     */           {
/*  77 */             throw new CommandException("commands.fill.outOfWorld", new Object[0]);
/*     */           }
/*     */         } 
/*     */       } 
/*     */       
/*  82 */       NBTTagCompound var22 = new NBTTagCompound();
/*  83 */       boolean var23 = false;
/*     */       
/*  85 */       if (args.length >= 10 && var5.hasTileEntity()) {
/*     */         
/*  87 */         String var13 = getChatComponentFromNthArg(sender, args, 9).getUnformattedText();
/*     */ 
/*     */         
/*     */         try {
/*  91 */           var22 = JsonToNBT.func_180713_a(var13);
/*  92 */           var23 = true;
/*     */         }
/*  94 */         catch (NBTException var21) {
/*     */           
/*  96 */           throw new CommandException("commands.fill.tagError", new Object[] { var21.getMessage() });
/*     */         } 
/*     */       } 
/*     */       
/* 100 */       ArrayList<BlockPos> var24 = Lists.newArrayList();
/* 101 */       var9 = 0;
/*     */       
/* 103 */       for (int var14 = var7.getZ(); var14 <= var8.getZ(); var14++) {
/*     */         
/* 105 */         for (int var15 = var7.getY(); var15 <= var8.getY(); var15++) {
/*     */           
/* 107 */           for (int var16 = var7.getX(); var16 <= var8.getX(); var16++) {
/*     */             
/* 109 */             BlockPos var17 = new BlockPos(var16, var15, var14);
/*     */ 
/*     */             
/* 112 */             if (args.length >= 9)
/*     */             {
/* 114 */               if (!args[8].equals("outline") && !args[8].equals("hollow")) {
/*     */                 
/* 116 */                 if (args[8].equals("destroy"))
/*     */                 {
/* 118 */                   var10.destroyBlock(var17, true);
/*     */                 }
/* 120 */                 else if (args[8].equals("keep"))
/*     */                 {
/* 122 */                   if (!var10.isAirBlock(var17))
/*     */                   {
/*     */                     continue;
/*     */                   }
/*     */                 }
/* 127 */                 else if (args[8].equals("replace") && !var5.hasTileEntity())
/*     */                 {
/* 129 */                   if (args.length > 9) {
/*     */                     
/* 131 */                     Block var18 = CommandBase.getBlockByText(sender, args[9]);
/*     */                     
/* 133 */                     if (var10.getBlockState(var17).getBlock() != var18) {
/*     */                       continue;
/*     */                     }
/*     */                   } 
/*     */ 
/*     */                   
/* 139 */                   if (args.length > 10)
/*     */                   {
/* 141 */                     int var28 = CommandBase.parseInt(args[10]);
/* 142 */                     IBlockState iBlockState = var10.getBlockState(var17);
/*     */                     
/* 144 */                     if (iBlockState.getBlock().getMetaFromState(iBlockState) != var28) {
/*     */                       continue;
/*     */                     }
/*     */                   }
/*     */                 
/*     */                 }
/*     */               
/* 151 */               } else if (var16 != var7.getX() && var16 != var8.getX() && var15 != var7.getY() && var15 != var8.getY() && var14 != var7.getZ() && var14 != var8.getZ()) {
/*     */                 
/* 153 */                 if (args[8].equals("hollow")) {
/*     */                   
/* 155 */                   var10.setBlockState(var17, Blocks.air.getDefaultState(), 2);
/* 156 */                   var24.add(var17);
/*     */                 } 
/*     */                 
/*     */                 continue;
/*     */               } 
/*     */             }
/*     */             
/* 163 */             TileEntity var29 = var10.getTileEntity(var17);
/*     */             
/* 165 */             if (var29 != null) {
/*     */               
/* 167 */               if (var29 instanceof IInventory)
/*     */               {
/* 169 */                 ((IInventory)var29).clearInventory();
/*     */               }
/*     */               
/* 172 */               var10.setBlockState(var17, Blocks.barrier.getDefaultState(), (var5 == Blocks.barrier) ? 2 : 4);
/*     */             } 
/*     */             
/* 175 */             IBlockState var19 = var5.getStateFromMeta(var6);
/*     */             
/* 177 */             if (var10.setBlockState(var17, var19, 2)) {
/*     */               
/* 179 */               var24.add(var17);
/* 180 */               var9++;
/*     */               
/* 182 */               if (var23) {
/*     */                 
/* 184 */                 TileEntity var20 = var10.getTileEntity(var17);
/*     */                 
/* 186 */                 if (var20 != null) {
/*     */                   
/* 188 */                   var22.setInteger("x", var17.getX());
/* 189 */                   var22.setInteger("y", var17.getY());
/* 190 */                   var22.setInteger("z", var17.getZ());
/* 191 */                   var20.readFromNBT(var22);
/*     */                 } 
/*     */               } 
/*     */             } 
/*     */             continue;
/*     */           } 
/*     */         } 
/*     */       } 
/* 199 */       Iterator<BlockPos> var25 = var24.iterator();
/*     */       
/* 201 */       while (var25.hasNext()) {
/*     */         
/* 203 */         BlockPos var26 = var25.next();
/* 204 */         Block var27 = var10.getBlockState(var26).getBlock();
/* 205 */         var10.func_175722_b(var26, var27);
/*     */       } 
/*     */       
/* 208 */       if (var9 <= 0)
/*     */       {
/* 210 */         throw new CommandException("commands.fill.failed", new Object[0]);
/*     */       }
/*     */ 
/*     */       
/* 214 */       sender.func_174794_a(CommandResultStats.Type.AFFECTED_BLOCKS, var9);
/* 215 */       notifyOperators(sender, this, "commands.fill.success", new Object[] { Integer.valueOf(var9) });
/*     */     
/*     */     }
/*     */     else {
/*     */       
/* 220 */       throw new CommandException("commands.fill.outOfWorld", new Object[0]);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public List addTabCompletionOptions(ICommandSender sender, String[] args, BlockPos pos) {
/* 227 */     return (args.length > 0 && args.length <= 3) ? func_175771_a(args, 0, pos) : ((args.length > 3 && args.length <= 6) ? func_175771_a(args, 3, pos) : ((args.length == 7) ? func_175762_a(args, Block.blockRegistry.getKeys()) : ((args.length == 9) ? getListOfStringsMatchingLastWord(args, new String[] { "replace", "destroy", "keep", "hollow", "outline" }) : null)));
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\command\CommandFill.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */