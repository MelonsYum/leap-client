/*     */ package net.minecraft.command;
/*     */ 
/*     */ import java.util.List;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.Vec3i;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.gen.structure.StructureBoundingBox;
/*     */ 
/*     */ public class CommandCompare
/*     */   extends CommandBase {
/*     */   private static final String __OBFID = "CL_00002346";
/*     */   
/*     */   public String getCommandName() {
/*  18 */     return "testforblocks";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getRequiredPermissionLevel() {
/*  26 */     return 2;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getCommandUsage(ICommandSender sender) {
/*  31 */     return "commands.compare.usage";
/*     */   }
/*     */ 
/*     */   
/*     */   public void processCommand(ICommandSender sender, String[] args) throws CommandException {
/*  36 */     if (args.length < 9)
/*     */     {
/*  38 */       throw new WrongUsageException("commands.compare.usage", new Object[0]);
/*     */     }
/*     */ 
/*     */     
/*  42 */     sender.func_174794_a(CommandResultStats.Type.AFFECTED_BLOCKS, 0);
/*  43 */     BlockPos var3 = func_175757_a(sender, args, 0, false);
/*  44 */     BlockPos var4 = func_175757_a(sender, args, 3, false);
/*  45 */     BlockPos var5 = func_175757_a(sender, args, 6, false);
/*  46 */     StructureBoundingBox var6 = new StructureBoundingBox((Vec3i)var3, (Vec3i)var4);
/*  47 */     StructureBoundingBox var7 = new StructureBoundingBox((Vec3i)var5, (Vec3i)var5.add(var6.func_175896_b()));
/*  48 */     int var8 = var6.getXSize() * var6.getYSize() * var6.getZSize();
/*     */     
/*  50 */     if (var8 > 524288)
/*     */     {
/*  52 */       throw new CommandException("commands.compare.tooManyBlocks", new Object[] { Integer.valueOf(var8), Integer.valueOf(524288) });
/*     */     }
/*  54 */     if (var6.minY >= 0 && var6.maxY < 256 && var7.minY >= 0 && var7.maxY < 256) {
/*     */       
/*  56 */       World var9 = sender.getEntityWorld();
/*     */       
/*  58 */       if (var9.isAreaLoaded(var6) && var9.isAreaLoaded(var7))
/*     */       {
/*  60 */         boolean var10 = false;
/*     */         
/*  62 */         if (args.length > 9 && args[9].equals("masked"))
/*     */         {
/*  64 */           var10 = true;
/*     */         }
/*     */         
/*  67 */         var8 = 0;
/*  68 */         BlockPos var11 = new BlockPos(var7.minX - var6.minX, var7.minY - var6.minY, var7.minZ - var6.minZ);
/*     */         
/*  70 */         for (int var12 = var6.minZ; var12 <= var6.maxZ; var12++) {
/*     */           
/*  72 */           for (int var13 = var6.minY; var13 <= var6.maxY; var13++) {
/*     */             
/*  74 */             for (int var14 = var6.minX; var14 <= var6.maxX; var14++) {
/*     */               
/*  76 */               BlockPos var15 = new BlockPos(var14, var13, var12);
/*  77 */               BlockPos var16 = var15.add((Vec3i)var11);
/*  78 */               boolean var17 = false;
/*  79 */               IBlockState var18 = var9.getBlockState(var15);
/*     */               
/*  81 */               if (!var10 || var18.getBlock() != Blocks.air) {
/*     */                 
/*  83 */                 if (var18 == var9.getBlockState(var16)) {
/*     */                   
/*  85 */                   TileEntity var19 = var9.getTileEntity(var15);
/*  86 */                   TileEntity var20 = var9.getTileEntity(var16);
/*     */                   
/*  88 */                   if (var19 != null && var20 != null)
/*     */                   {
/*  90 */                     NBTTagCompound var21 = new NBTTagCompound();
/*  91 */                     var19.writeToNBT(var21);
/*  92 */                     var21.removeTag("x");
/*  93 */                     var21.removeTag("y");
/*  94 */                     var21.removeTag("z");
/*  95 */                     NBTTagCompound var22 = new NBTTagCompound();
/*  96 */                     var20.writeToNBT(var22);
/*  97 */                     var22.removeTag("x");
/*  98 */                     var22.removeTag("y");
/*  99 */                     var22.removeTag("z");
/*     */                     
/* 101 */                     if (!var21.equals(var22))
/*     */                     {
/* 103 */                       var17 = true;
/*     */                     }
/*     */                   }
/* 106 */                   else if (var19 != null)
/*     */                   {
/* 108 */                     var17 = true;
/*     */                   }
/*     */                 
/*     */                 } else {
/*     */                   
/* 113 */                   var17 = true;
/*     */                 } 
/*     */                 
/* 116 */                 var8++;
/*     */                 
/* 118 */                 if (var17)
/*     */                 {
/* 120 */                   throw new CommandException("commands.compare.failed", new Object[0]);
/*     */                 }
/*     */               } 
/*     */             } 
/*     */           } 
/*     */         } 
/*     */         
/* 127 */         sender.func_174794_a(CommandResultStats.Type.AFFECTED_BLOCKS, var8);
/* 128 */         notifyOperators(sender, this, "commands.compare.success", new Object[] { Integer.valueOf(var8) });
/*     */       }
/*     */       else
/*     */       {
/* 132 */         throw new CommandException("commands.compare.outOfWorld", new Object[0]);
/*     */       }
/*     */     
/*     */     } else {
/*     */       
/* 137 */       throw new CommandException("commands.compare.outOfWorld", new Object[0]);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public List addTabCompletionOptions(ICommandSender sender, String[] args, BlockPos pos) {
/* 144 */     return (args.length > 0 && args.length <= 3) ? func_175771_a(args, 0, pos) : ((args.length > 3 && args.length <= 6) ? func_175771_a(args, 3, pos) : ((args.length > 6 && args.length <= 9) ? func_175771_a(args, 6, pos) : ((args.length == 10) ? getListOfStringsMatchingLastWord(args, new String[] { "masked", "all" }) : null)));
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\command\CommandCompare.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */