/*     */ package net.minecraft.command.server;
/*     */ 
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.command.CommandBase;
/*     */ import net.minecraft.command.CommandException;
/*     */ import net.minecraft.command.CommandResultStats;
/*     */ import net.minecraft.command.ICommand;
/*     */ import net.minecraft.command.ICommandSender;
/*     */ import net.minecraft.command.NumberInvalidException;
/*     */ import net.minecraft.command.WrongUsageException;
/*     */ import net.minecraft.nbt.JsonToNBT;
/*     */ import net.minecraft.nbt.NBTBase;
/*     */ import net.minecraft.nbt.NBTException;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.nbt.NBTTagList;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class CommandTestForBlock
/*     */   extends CommandBase {
/*     */   private static final String __OBFID = "CL_00001181";
/*     */   
/*     */   public String getCommandName() {
/*  28 */     return "testforblock";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getRequiredPermissionLevel() {
/*  36 */     return 2;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getCommandUsage(ICommandSender sender) {
/*  41 */     return "commands.testforblock.usage";
/*     */   }
/*     */ 
/*     */   
/*     */   public void processCommand(ICommandSender sender, String[] args) throws CommandException {
/*  46 */     if (args.length < 4)
/*     */     {
/*  48 */       throw new WrongUsageException("commands.testforblock.usage", new Object[0]);
/*     */     }
/*     */ 
/*     */     
/*  52 */     sender.func_174794_a(CommandResultStats.Type.AFFECTED_BLOCKS, 0);
/*  53 */     BlockPos var3 = func_175757_a(sender, args, 0, false);
/*  54 */     Block var4 = Block.getBlockFromName(args[3]);
/*     */     
/*  56 */     if (var4 == null)
/*     */     {
/*  58 */       throw new NumberInvalidException("commands.setblock.notFound", new Object[] { args[3] });
/*     */     }
/*     */ 
/*     */     
/*  62 */     int var5 = -1;
/*     */     
/*  64 */     if (args.length >= 5)
/*     */     {
/*  66 */       var5 = parseInt(args[4], -1, 15);
/*     */     }
/*     */     
/*  69 */     World var6 = sender.getEntityWorld();
/*     */     
/*  71 */     if (!var6.isBlockLoaded(var3))
/*     */     {
/*  73 */       throw new CommandException("commands.testforblock.outOfWorld", new Object[0]);
/*     */     }
/*     */ 
/*     */     
/*  77 */     NBTTagCompound var7 = new NBTTagCompound();
/*  78 */     boolean var8 = false;
/*     */     
/*  80 */     if (args.length >= 6 && var4.hasTileEntity()) {
/*     */       
/*  82 */       String var9 = getChatComponentFromNthArg(sender, args, 5).getUnformattedText();
/*     */ 
/*     */       
/*     */       try {
/*  86 */         var7 = JsonToNBT.func_180713_a(var9);
/*  87 */         var8 = true;
/*     */       }
/*  89 */       catch (NBTException var13) {
/*     */         
/*  91 */         throw new CommandException("commands.setblock.tagError", new Object[] { var13.getMessage() });
/*     */       } 
/*     */     } 
/*     */     
/*  95 */     IBlockState var14 = var6.getBlockState(var3);
/*  96 */     Block var10 = var14.getBlock();
/*     */     
/*  98 */     if (var10 != var4)
/*     */     {
/* 100 */       throw new CommandException("commands.testforblock.failed.tile", new Object[] { Integer.valueOf(var3.getX()), Integer.valueOf(var3.getY()), Integer.valueOf(var3.getZ()), var10.getLocalizedName(), var4.getLocalizedName() });
/*     */     }
/*     */ 
/*     */     
/* 104 */     if (var5 > -1) {
/*     */       
/* 106 */       int var11 = var14.getBlock().getMetaFromState(var14);
/*     */       
/* 108 */       if (var11 != var5)
/*     */       {
/* 110 */         throw new CommandException("commands.testforblock.failed.data", new Object[] { Integer.valueOf(var3.getX()), Integer.valueOf(var3.getY()), Integer.valueOf(var3.getZ()), Integer.valueOf(var11), Integer.valueOf(var5) });
/*     */       }
/*     */     } 
/*     */     
/* 114 */     if (var8) {
/*     */       
/* 116 */       TileEntity var15 = var6.getTileEntity(var3);
/*     */       
/* 118 */       if (var15 == null)
/*     */       {
/* 120 */         throw new CommandException("commands.testforblock.failed.tileEntity", new Object[] { Integer.valueOf(var3.getX()), Integer.valueOf(var3.getY()), Integer.valueOf(var3.getZ()) });
/*     */       }
/*     */       
/* 123 */       NBTTagCompound var12 = new NBTTagCompound();
/* 124 */       var15.writeToNBT(var12);
/*     */       
/* 126 */       if (!func_175775_a((NBTBase)var7, (NBTBase)var12, true))
/*     */       {
/* 128 */         throw new CommandException("commands.testforblock.failed.nbt", new Object[] { Integer.valueOf(var3.getX()), Integer.valueOf(var3.getY()), Integer.valueOf(var3.getZ()) });
/*     */       }
/*     */     } 
/*     */     
/* 132 */     sender.func_174794_a(CommandResultStats.Type.AFFECTED_BLOCKS, 1);
/* 133 */     notifyOperators(sender, (ICommand)this, "commands.testforblock.success", new Object[] { Integer.valueOf(var3.getX()), Integer.valueOf(var3.getY()), Integer.valueOf(var3.getZ()) });
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean func_175775_a(NBTBase p_175775_0_, NBTBase p_175775_1_, boolean p_175775_2_) {
/* 142 */     if (p_175775_0_ == p_175775_1_)
/*     */     {
/* 144 */       return true;
/*     */     }
/* 146 */     if (p_175775_0_ == null)
/*     */     {
/* 148 */       return true;
/*     */     }
/* 150 */     if (p_175775_1_ == null)
/*     */     {
/* 152 */       return false;
/*     */     }
/* 154 */     if (!p_175775_0_.getClass().equals(p_175775_1_.getClass()))
/*     */     {
/* 156 */       return false;
/*     */     }
/* 158 */     if (p_175775_0_ instanceof NBTTagCompound) {
/*     */       String var12; NBTBase var13;
/* 160 */       NBTTagCompound var9 = (NBTTagCompound)p_175775_0_;
/* 161 */       NBTTagCompound var10 = (NBTTagCompound)p_175775_1_;
/* 162 */       Iterator<String> var11 = var9.getKeySet().iterator();
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       do {
/* 168 */         if (!var11.hasNext())
/*     */         {
/* 170 */           return true;
/*     */         }
/*     */         
/* 173 */         var12 = var11.next();
/* 174 */         var13 = var9.getTag(var12);
/*     */       }
/* 176 */       while (func_175775_a(var13, var10.getTag(var12), p_175775_2_));
/*     */       
/* 178 */       return false;
/*     */     } 
/* 180 */     if (p_175775_0_ instanceof NBTTagList && p_175775_2_) {
/*     */       
/* 182 */       NBTTagList var3 = (NBTTagList)p_175775_0_;
/* 183 */       NBTTagList var4 = (NBTTagList)p_175775_1_;
/*     */       
/* 185 */       if (var3.tagCount() == 0)
/*     */       {
/* 187 */         return (var4.tagCount() == 0);
/*     */       }
/*     */ 
/*     */       
/* 191 */       int var5 = 0;
/*     */       
/* 193 */       while (var5 < var3.tagCount()) {
/*     */         
/* 195 */         NBTBase var6 = var3.get(var5);
/* 196 */         boolean var7 = false;
/* 197 */         int var8 = 0;
/*     */ 
/*     */ 
/*     */         
/* 201 */         while (var8 < var4.tagCount()) {
/*     */           
/* 203 */           if (!func_175775_a(var6, var4.get(var8), p_175775_2_)) {
/*     */             
/* 205 */             var8++;
/*     */             
/*     */             continue;
/*     */           } 
/* 209 */           var7 = true;
/*     */         } 
/*     */         
/* 212 */         if (!var7)
/*     */         {
/* 214 */           return false;
/*     */         }
/*     */         
/* 217 */         var5++;
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 222 */       return true;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 227 */     return p_175775_0_.equals(p_175775_1_);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public List addTabCompletionOptions(ICommandSender sender, String[] args, BlockPos pos) {
/* 233 */     return (args.length > 0 && args.length <= 3) ? func_175771_a(args, 0, pos) : ((args.length == 4) ? func_175762_a(args, Block.blockRegistry.getKeys()) : null);
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\command\server\CommandTestForBlock.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */