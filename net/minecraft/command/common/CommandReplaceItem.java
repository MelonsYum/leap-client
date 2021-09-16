/*     */ package net.minecraft.command.common;
/*     */ 
/*     */ import com.google.common.collect.Maps;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.command.CommandBase;
/*     */ import net.minecraft.command.CommandException;
/*     */ import net.minecraft.command.CommandResultStats;
/*     */ import net.minecraft.command.ICommand;
/*     */ import net.minecraft.command.ICommandSender;
/*     */ import net.minecraft.command.NumberInvalidException;
/*     */ import net.minecraft.command.WrongUsageException;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.inventory.IInventory;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.JsonToNBT;
/*     */ import net.minecraft.nbt.NBTException;
/*     */ import net.minecraft.server.MinecraftServer;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class CommandReplaceItem extends CommandBase {
/*  28 */   private static final Map field_175785_a = Maps.newHashMap();
/*     */   
/*     */   private static final String __OBFID = "CL_00002340";
/*     */   
/*     */   public String getCommandName() {
/*  33 */     return "replaceitem";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getRequiredPermissionLevel() {
/*  41 */     return 2;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getCommandUsage(ICommandSender sender) {
/*  46 */     return "commands.replaceitem.usage";
/*     */   } public void processCommand(ICommandSender sender, String[] args) throws CommandException {
/*     */     boolean var3;
/*     */     byte var4;
/*     */     Item var6;
/*  51 */     if (args.length < 1)
/*     */     {
/*  53 */       throw new WrongUsageException("commands.replaceitem.usage", new Object[0]);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  59 */     if (args[0].equals("entity")) {
/*     */       
/*  61 */       var3 = false;
/*     */     }
/*     */     else {
/*     */       
/*  65 */       if (!args[0].equals("block"))
/*     */       {
/*  67 */         throw new WrongUsageException("commands.replaceitem.usage", new Object[0]);
/*     */       }
/*     */       
/*  70 */       var3 = true;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/*  75 */     if (var3) {
/*     */       
/*  77 */       if (args.length < 6)
/*     */       {
/*  79 */         throw new WrongUsageException("commands.replaceitem.block.usage", new Object[0]);
/*     */       }
/*     */       
/*  82 */       var4 = 4;
/*     */     }
/*     */     else {
/*     */       
/*  86 */       if (args.length < 4)
/*     */       {
/*  88 */         throw new WrongUsageException("commands.replaceitem.entity.usage", new Object[0]);
/*     */       }
/*     */       
/*  91 */       var4 = 2;
/*     */     } 
/*     */     
/*  94 */     int var16 = var4 + 1;
/*  95 */     int var5 = func_175783_e(args[var4]);
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/* 100 */       var6 = getItemByText(sender, args[var16]);
/*     */     }
/* 102 */     catch (NumberInvalidException var15) {
/*     */       
/* 104 */       if (Block.getBlockFromName(args[var16]) != Blocks.air)
/*     */       {
/* 106 */         throw var15;
/*     */       }
/*     */       
/* 109 */       var6 = null;
/*     */     } 
/*     */     
/* 112 */     var16++;
/* 113 */     int var7 = (args.length > var16) ? parseInt(args[var16++], 1, 64) : 1;
/* 114 */     int var8 = (args.length > var16) ? parseInt(args[var16++]) : 0;
/* 115 */     ItemStack var9 = new ItemStack(var6, var7, var8);
/*     */     
/* 117 */     if (args.length > var16) {
/*     */       
/* 119 */       String var10 = getChatComponentFromNthArg(sender, args, var16).getUnformattedText();
/*     */ 
/*     */       
/*     */       try {
/* 123 */         var9.setTagCompound(JsonToNBT.func_180713_a(var10));
/*     */       }
/* 125 */       catch (NBTException var14) {
/*     */         
/* 127 */         throw new CommandException("commands.replaceitem.tagError", new Object[] { var14.getMessage() });
/*     */       } 
/*     */     } 
/*     */     
/* 131 */     if (var9.getItem() == null)
/*     */     {
/* 133 */       var9 = null;
/*     */     }
/*     */     
/* 136 */     if (var3) {
/*     */       
/* 138 */       sender.func_174794_a(CommandResultStats.Type.AFFECTED_ITEMS, 0);
/* 139 */       BlockPos var17 = func_175757_a(sender, args, 1, false);
/* 140 */       World var11 = sender.getEntityWorld();
/* 141 */       TileEntity var12 = var11.getTileEntity(var17);
/*     */       
/* 143 */       if (var12 == null || !(var12 instanceof IInventory))
/*     */       {
/* 145 */         throw new CommandException("commands.replaceitem.noContainer", new Object[] { Integer.valueOf(var17.getX()), Integer.valueOf(var17.getY()), Integer.valueOf(var17.getZ()) });
/*     */       }
/*     */       
/* 148 */       IInventory var13 = (IInventory)var12;
/*     */       
/* 150 */       if (var5 >= 0 && var5 < var13.getSizeInventory())
/*     */       {
/* 152 */         var13.setInventorySlotContents(var5, var9);
/*     */       }
/*     */     }
/*     */     else {
/*     */       
/* 157 */       Entity var18 = func_175768_b(sender, args[1]);
/* 158 */       sender.func_174794_a(CommandResultStats.Type.AFFECTED_ITEMS, 0);
/*     */       
/* 160 */       if (var18 instanceof EntityPlayer)
/*     */       {
/* 162 */         ((EntityPlayer)var18).inventoryContainer.detectAndSendChanges();
/*     */       }
/*     */       
/* 165 */       if (!var18.func_174820_d(var5, var9))
/*     */       {
/* 167 */         throw new CommandException("commands.replaceitem.failed", new Object[] { Integer.valueOf(var5), Integer.valueOf(var7), (var9 == null) ? "Air" : var9.getChatComponent() });
/*     */       }
/*     */       
/* 170 */       if (var18 instanceof EntityPlayer)
/*     */       {
/* 172 */         ((EntityPlayer)var18).inventoryContainer.detectAndSendChanges();
/*     */       }
/*     */     } 
/*     */     
/* 176 */     sender.func_174794_a(CommandResultStats.Type.AFFECTED_ITEMS, var7);
/* 177 */     notifyOperators(sender, (ICommand)this, "commands.replaceitem.success", new Object[] { Integer.valueOf(var5), Integer.valueOf(var7), (var9 == null) ? "Air" : var9.getChatComponent() });
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private int func_175783_e(String p_175783_1_) throws CommandException {
/* 183 */     if (!field_175785_a.containsKey(p_175783_1_))
/*     */     {
/* 185 */       throw new CommandException("commands.generic.parameter.invalid", new Object[] { p_175783_1_ });
/*     */     }
/*     */ 
/*     */     
/* 189 */     return ((Integer)field_175785_a.get(p_175783_1_)).intValue();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public List addTabCompletionOptions(ICommandSender sender, String[] args, BlockPos pos) {
/* 195 */     return (args.length == 1) ? getListOfStringsMatchingLastWord(args, new String[] { "entity", "block" }) : ((args.length == 2 && args[0].equals("entity")) ? getListOfStringsMatchingLastWord(args, func_175784_d()) : (((args.length != 3 || !args[0].equals("entity")) && (args.length != 5 || !args[0].equals("block"))) ? (((args.length != 4 || !args[0].equals("entity")) && (args.length != 6 || !args[0].equals("block"))) ? null : func_175762_a(args, Item.itemRegistry.getKeys())) : func_175762_a(args, field_175785_a.keySet())));
/*     */   }
/*     */ 
/*     */   
/*     */   protected String[] func_175784_d() {
/* 200 */     return MinecraftServer.getServer().getAllUsernames();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isUsernameIndex(String[] args, int index) {
/* 208 */     return (args.length > 0 && args[0].equals("entity") && index == 1);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   static {
/*     */     int var0;
/* 215 */     for (var0 = 0; var0 < 54; var0++)
/*     */     {
/* 217 */       field_175785_a.put("slot.container." + var0, Integer.valueOf(var0));
/*     */     }
/*     */     
/* 220 */     for (var0 = 0; var0 < 9; var0++)
/*     */     {
/* 222 */       field_175785_a.put("slot.hotbar." + var0, Integer.valueOf(var0));
/*     */     }
/*     */     
/* 225 */     for (var0 = 0; var0 < 27; var0++)
/*     */     {
/* 227 */       field_175785_a.put("slot.inventory." + var0, Integer.valueOf(9 + var0));
/*     */     }
/*     */     
/* 230 */     for (var0 = 0; var0 < 27; var0++)
/*     */     {
/* 232 */       field_175785_a.put("slot.enderchest." + var0, Integer.valueOf(200 + var0));
/*     */     }
/*     */     
/* 235 */     for (var0 = 0; var0 < 8; var0++)
/*     */     {
/* 237 */       field_175785_a.put("slot.villager." + var0, Integer.valueOf(300 + var0));
/*     */     }
/*     */     
/* 240 */     for (var0 = 0; var0 < 15; var0++)
/*     */     {
/* 242 */       field_175785_a.put("slot.horse." + var0, Integer.valueOf(500 + var0));
/*     */     }
/*     */     
/* 245 */     field_175785_a.put("slot.weapon", Integer.valueOf(99));
/* 246 */     field_175785_a.put("slot.armor.head", Integer.valueOf(103));
/* 247 */     field_175785_a.put("slot.armor.chest", Integer.valueOf(102));
/* 248 */     field_175785_a.put("slot.armor.legs", Integer.valueOf(101));
/* 249 */     field_175785_a.put("slot.armor.feet", Integer.valueOf(100));
/* 250 */     field_175785_a.put("slot.horse.saddle", Integer.valueOf(400));
/* 251 */     field_175785_a.put("slot.horse.armor", Integer.valueOf(401));
/* 252 */     field_175785_a.put("slot.horse.chest", Integer.valueOf(499));
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\command\common\CommandReplaceItem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */