/*     */ package net.minecraft.command;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.LinkedList;
/*     */ import java.util.List;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.inventory.IInventory;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.Vec3i;
/*     */ import net.minecraft.world.NextTickListEntry;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.gen.structure.StructureBoundingBox;
/*     */ 
/*     */ public class CommandClone
/*     */   extends CommandBase {
/*     */   private static final String __OBFID = "CL_00002348";
/*     */   
/*     */   public String getCommandName() {
/*  25 */     return "clone";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getRequiredPermissionLevel() {
/*  33 */     return 2;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getCommandUsage(ICommandSender sender) {
/*  38 */     return "commands.clone.usage";
/*     */   }
/*     */ 
/*     */   
/*     */   public void processCommand(ICommandSender sender, String[] args) throws CommandException {
/*  43 */     if (args.length < 9)
/*     */     {
/*  45 */       throw new WrongUsageException("commands.clone.usage", new Object[0]);
/*     */     }
/*     */ 
/*     */     
/*  49 */     sender.func_174794_a(CommandResultStats.Type.AFFECTED_BLOCKS, 0);
/*  50 */     BlockPos var3 = func_175757_a(sender, args, 0, false);
/*  51 */     BlockPos var4 = func_175757_a(sender, args, 3, false);
/*  52 */     BlockPos var5 = func_175757_a(sender, args, 6, false);
/*  53 */     StructureBoundingBox var6 = new StructureBoundingBox((Vec3i)var3, (Vec3i)var4);
/*  54 */     StructureBoundingBox var7 = new StructureBoundingBox((Vec3i)var5, (Vec3i)var5.add(var6.func_175896_b()));
/*  55 */     int var8 = var6.getXSize() * var6.getYSize() * var6.getZSize();
/*     */     
/*  57 */     if (var8 > 32768)
/*     */     {
/*  59 */       throw new CommandException("commands.clone.tooManyBlocks", new Object[] { Integer.valueOf(var8), Integer.valueOf(32768) });
/*     */     }
/*     */ 
/*     */     
/*  63 */     boolean var9 = false;
/*  64 */     Block var10 = null;
/*  65 */     int var11 = -1;
/*     */     
/*  67 */     if ((args.length < 11 || (!args[10].equals("force") && !args[10].equals("move"))) && var6.intersectsWith(var7))
/*     */     {
/*  69 */       throw new CommandException("commands.clone.noOverlap", new Object[0]);
/*     */     }
/*     */ 
/*     */     
/*  73 */     if (args.length >= 11 && args[10].equals("move"))
/*     */     {
/*  75 */       var9 = true;
/*     */     }
/*     */     
/*  78 */     if (var6.minY >= 0 && var6.maxY < 256 && var7.minY >= 0 && var7.maxY < 256) {
/*     */       
/*  80 */       World var12 = sender.getEntityWorld();
/*     */       
/*  82 */       if (var12.isAreaLoaded(var6) && var12.isAreaLoaded(var7))
/*     */       {
/*  84 */         boolean var13 = false;
/*     */         
/*  86 */         if (args.length >= 10)
/*     */         {
/*  88 */           if (args[9].equals("masked")) {
/*     */             
/*  90 */             var13 = true;
/*     */           }
/*  92 */           else if (args[9].equals("filtered")) {
/*     */             
/*  94 */             if (args.length < 12)
/*     */             {
/*  96 */               throw new WrongUsageException("commands.clone.usage", new Object[0]);
/*     */             }
/*     */             
/*  99 */             var10 = getBlockByText(sender, args[11]);
/*     */             
/* 101 */             if (args.length >= 13)
/*     */             {
/* 103 */               var11 = parseInt(args[12], 0, 15);
/*     */             }
/*     */           } 
/*     */         }
/*     */         
/* 108 */         ArrayList<StaticCloneData> var14 = Lists.newArrayList();
/* 109 */         ArrayList<StaticCloneData> var15 = Lists.newArrayList();
/* 110 */         ArrayList<StaticCloneData> var16 = Lists.newArrayList();
/* 111 */         LinkedList<BlockPos> var17 = Lists.newLinkedList();
/* 112 */         BlockPos var18 = new BlockPos(var7.minX - var6.minX, var7.minY - var6.minY, var7.minZ - var6.minZ);
/*     */         
/* 114 */         for (int var19 = var6.minZ; var19 <= var6.maxZ; var19++) {
/*     */           
/* 116 */           for (int var20 = var6.minY; var20 <= var6.maxY; var20++) {
/*     */             
/* 118 */             for (int var21 = var6.minX; var21 <= var6.maxX; var21++) {
/*     */               
/* 120 */               BlockPos var22 = new BlockPos(var21, var20, var19);
/* 121 */               BlockPos var23 = var22.add((Vec3i)var18);
/* 122 */               IBlockState var24 = var12.getBlockState(var22);
/*     */               
/* 124 */               if ((!var13 || var24.getBlock() != Blocks.air) && (var10 == null || (var24.getBlock() == var10 && (var11 < 0 || var24.getBlock().getMetaFromState(var24) == var11)))) {
/*     */                 
/* 126 */                 TileEntity var25 = var12.getTileEntity(var22);
/*     */                 
/* 128 */                 if (var25 != null) {
/*     */                   
/* 130 */                   NBTTagCompound var26 = new NBTTagCompound();
/* 131 */                   var25.writeToNBT(var26);
/* 132 */                   var15.add(new StaticCloneData(var23, var24, var26));
/* 133 */                   var17.addLast(var22);
/*     */                 }
/* 135 */                 else if (!var24.getBlock().isFullBlock() && !var24.getBlock().isFullCube()) {
/*     */                   
/* 137 */                   var16.add(new StaticCloneData(var23, var24, null));
/* 138 */                   var17.addFirst(var22);
/*     */                 }
/*     */                 else {
/*     */                   
/* 142 */                   var14.add(new StaticCloneData(var23, var24, null));
/* 143 */                   var17.addLast(var22);
/*     */                 } 
/*     */               } 
/*     */             } 
/*     */           } 
/*     */         } 
/*     */         
/* 150 */         if (var9) {
/*     */           Iterator<BlockPos> var27;
/*     */ 
/*     */ 
/*     */           
/* 155 */           for (var27 = var17.iterator(); var27.hasNext(); var12.setBlockState(var29, Blocks.barrier.getDefaultState(), 2)) {
/*     */             
/* 157 */             BlockPos var29 = var27.next();
/* 158 */             TileEntity var31 = var12.getTileEntity(var29);
/*     */             
/* 160 */             if (var31 instanceof IInventory)
/*     */             {
/* 162 */               ((IInventory)var31).clearInventory();
/*     */             }
/*     */           } 
/*     */           
/* 166 */           var27 = var17.iterator();
/*     */           
/* 168 */           while (var27.hasNext()) {
/*     */             
/* 170 */             BlockPos var29 = var27.next();
/* 171 */             var12.setBlockState(var29, Blocks.air.getDefaultState(), 3);
/*     */           } 
/*     */         } 
/*     */         
/* 175 */         ArrayList<StaticCloneData> var28 = Lists.newArrayList();
/* 176 */         var28.addAll(var14);
/* 177 */         var28.addAll(var15);
/* 178 */         var28.addAll(var16);
/* 179 */         List<StaticCloneData> var30 = Lists.reverse(var28);
/*     */ 
/*     */         
/*     */         Iterator<StaticCloneData> var32;
/*     */         
/* 184 */         for (var32 = var30.iterator(); var32.hasNext(); var12.setBlockState(var34.field_179537_a, Blocks.barrier.getDefaultState(), 2)) {
/*     */           
/* 186 */           StaticCloneData var34 = var32.next();
/* 187 */           TileEntity var36 = var12.getTileEntity(var34.field_179537_a);
/*     */           
/* 189 */           if (var36 instanceof IInventory)
/*     */           {
/* 191 */             ((IInventory)var36).clearInventory();
/*     */           }
/*     */         } 
/*     */         
/* 195 */         var8 = 0;
/* 196 */         var32 = var28.iterator();
/*     */         
/* 198 */         while (var32.hasNext()) {
/*     */           
/* 200 */           StaticCloneData var34 = var32.next();
/*     */           
/* 202 */           if (var12.setBlockState(var34.field_179537_a, var34.field_179535_b, 2))
/*     */           {
/* 204 */             var8++;
/*     */           }
/*     */         } 
/*     */         
/* 208 */         for (var32 = var15.iterator(); var32.hasNext(); var12.setBlockState(var34.field_179537_a, var34.field_179535_b, 2)) {
/*     */           
/* 210 */           StaticCloneData var34 = var32.next();
/* 211 */           TileEntity var36 = var12.getTileEntity(var34.field_179537_a);
/*     */           
/* 213 */           if (var34.field_179536_c != null && var36 != null) {
/*     */             
/* 215 */             var34.field_179536_c.setInteger("x", var34.field_179537_a.getX());
/* 216 */             var34.field_179536_c.setInteger("y", var34.field_179537_a.getY());
/* 217 */             var34.field_179536_c.setInteger("z", var34.field_179537_a.getZ());
/* 218 */             var36.readFromNBT(var34.field_179536_c);
/* 219 */             var36.markDirty();
/*     */           } 
/*     */         } 
/*     */         
/* 223 */         var32 = var30.iterator();
/*     */         
/* 225 */         while (var32.hasNext()) {
/*     */           
/* 227 */           StaticCloneData var34 = var32.next();
/* 228 */           var12.func_175722_b(var34.field_179537_a, var34.field_179535_b.getBlock());
/*     */         } 
/*     */         
/* 231 */         List var33 = var12.func_175712_a(var6, false);
/*     */         
/* 233 */         if (var33 != null) {
/*     */           
/* 235 */           Iterator<NextTickListEntry> var35 = var33.iterator();
/*     */           
/* 237 */           while (var35.hasNext()) {
/*     */             
/* 239 */             NextTickListEntry var37 = var35.next();
/*     */             
/* 241 */             if (var6.func_175898_b((Vec3i)var37.field_180282_a)) {
/*     */               
/* 243 */               BlockPos var38 = var37.field_180282_a.add((Vec3i)var18);
/* 244 */               var12.func_180497_b(var38, var37.func_151351_a(), (int)(var37.scheduledTime - var12.getWorldInfo().getWorldTotalTime()), var37.priority);
/*     */             } 
/*     */           } 
/*     */         } 
/*     */         
/* 249 */         if (var8 <= 0)
/*     */         {
/* 251 */           throw new CommandException("commands.clone.failed", new Object[0]);
/*     */         }
/*     */ 
/*     */         
/* 255 */         sender.func_174794_a(CommandResultStats.Type.AFFECTED_BLOCKS, var8);
/* 256 */         notifyOperators(sender, this, "commands.clone.success", new Object[] { Integer.valueOf(var8) });
/*     */       
/*     */       }
/*     */       else
/*     */       {
/* 261 */         throw new CommandException("commands.clone.outOfWorld", new Object[0]);
/*     */       }
/*     */     
/*     */     } else {
/*     */       
/* 266 */       throw new CommandException("commands.clone.outOfWorld", new Object[0]);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List addTabCompletionOptions(ICommandSender sender, String[] args, BlockPos pos) {
/* 275 */     return (args.length > 0 && args.length <= 3) ? func_175771_a(args, 0, pos) : ((args.length > 3 && args.length <= 6) ? func_175771_a(args, 3, pos) : ((args.length > 6 && args.length <= 9) ? func_175771_a(args, 6, pos) : ((args.length == 10) ? getListOfStringsMatchingLastWord(args, new String[] { "replace", "masked", "filtered" }) : ((args.length == 11) ? getListOfStringsMatchingLastWord(args, new String[] { "normal", "force", "move" }) : ((args.length == 12 && "filtered".equals(args[9])) ? func_175762_a(args, Block.blockRegistry.getKeys()) : null)))));
/*     */   }
/*     */ 
/*     */   
/*     */   static class StaticCloneData
/*     */   {
/*     */     public final BlockPos field_179537_a;
/*     */     public final IBlockState field_179535_b;
/*     */     public final NBTTagCompound field_179536_c;
/*     */     private static final String __OBFID = "CL_00002347";
/*     */     
/*     */     public StaticCloneData(BlockPos p_i46037_1_, IBlockState p_i46037_2_, NBTTagCompound p_i46037_3_) {
/* 287 */       this.field_179537_a = p_i46037_1_;
/* 288 */       this.field_179535_b = p_i46037_2_;
/* 289 */       this.field_179536_c = p_i46037_3_;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\command\CommandClone.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */