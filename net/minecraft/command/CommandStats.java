/*     */ package net.minecraft.command;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.scoreboard.ScoreObjective;
/*     */ import net.minecraft.server.MinecraftServer;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.tileentity.TileEntityCommandBlock;
/*     */ import net.minecraft.tileentity.TileEntitySign;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class CommandStats
/*     */   extends CommandBase
/*     */ {
/*     */   private static final String __OBFID = "CL_00002339";
/*     */   
/*     */   public String getCommandName() {
/*  23 */     return "stats";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getRequiredPermissionLevel() {
/*  31 */     return 2;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getCommandUsage(ICommandSender sender) {
/*  36 */     return "commands.stats.usage";
/*     */   } public void processCommand(ICommandSender sender, String[] args) throws CommandException {
/*     */     boolean var3;
/*     */     byte var4;
/*     */     CommandResultStats var8;
/*  41 */     if (args.length < 1)
/*     */     {
/*  43 */       throw new WrongUsageException("commands.stats.usage", new Object[0]);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  49 */     if (args[0].equals("entity")) {
/*     */       
/*  51 */       var3 = false;
/*     */     }
/*     */     else {
/*     */       
/*  55 */       if (!args[0].equals("block"))
/*     */       {
/*  57 */         throw new WrongUsageException("commands.stats.usage", new Object[0]);
/*     */       }
/*     */       
/*  60 */       var3 = true;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/*  65 */     if (var3) {
/*     */       
/*  67 */       if (args.length < 5)
/*     */       {
/*  69 */         throw new WrongUsageException("commands.stats.block.usage", new Object[0]);
/*     */       }
/*     */       
/*  72 */       var4 = 4;
/*     */     }
/*     */     else {
/*     */       
/*  76 */       if (args.length < 3)
/*     */       {
/*  78 */         throw new WrongUsageException("commands.stats.entity.usage", new Object[0]);
/*     */       }
/*     */       
/*  81 */       var4 = 2;
/*     */     } 
/*     */     
/*  84 */     int var11 = var4 + 1;
/*  85 */     String var5 = args[var4];
/*     */     
/*  87 */     if ("set".equals(var5)) {
/*     */       
/*  89 */       if (args.length < var11 + 3)
/*     */       {
/*  91 */         if (var11 == 5)
/*     */         {
/*  93 */           throw new WrongUsageException("commands.stats.block.set.usage", new Object[0]);
/*     */         }
/*     */         
/*  96 */         throw new WrongUsageException("commands.stats.entity.set.usage", new Object[0]);
/*     */       }
/*     */     
/*     */     } else {
/*     */       
/* 101 */       if (!"clear".equals(var5))
/*     */       {
/* 103 */         throw new WrongUsageException("commands.stats.usage", new Object[0]);
/*     */       }
/*     */       
/* 106 */       if (args.length < var11 + 1) {
/*     */         
/* 108 */         if (var11 == 5)
/*     */         {
/* 110 */           throw new WrongUsageException("commands.stats.block.clear.usage", new Object[0]);
/*     */         }
/*     */         
/* 113 */         throw new WrongUsageException("commands.stats.entity.clear.usage", new Object[0]);
/*     */       } 
/*     */     } 
/*     */     
/* 117 */     CommandResultStats.Type var6 = CommandResultStats.Type.func_179635_a(args[var11++]);
/*     */     
/* 119 */     if (var6 == null)
/*     */     {
/* 121 */       throw new CommandException("commands.stats.failed", new Object[0]);
/*     */     }
/*     */ 
/*     */     
/* 125 */     World var7 = sender.getEntityWorld();
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 130 */     if (var3) {
/*     */       
/* 132 */       BlockPos var9 = func_175757_a(sender, args, 1, false);
/* 133 */       TileEntity var10 = var7.getTileEntity(var9);
/*     */       
/* 135 */       if (var10 == null)
/*     */       {
/* 137 */         throw new CommandException("commands.stats.noCompatibleBlock", new Object[] { Integer.valueOf(var9.getX()), Integer.valueOf(var9.getY()), Integer.valueOf(var9.getZ()) });
/*     */       }
/*     */       
/* 140 */       if (var10 instanceof TileEntityCommandBlock)
/*     */       {
/* 142 */         var8 = ((TileEntityCommandBlock)var10).func_175124_c();
/*     */       }
/*     */       else
/*     */       {
/* 146 */         if (!(var10 instanceof TileEntitySign))
/*     */         {
/* 148 */           throw new CommandException("commands.stats.noCompatibleBlock", new Object[] { Integer.valueOf(var9.getX()), Integer.valueOf(var9.getY()), Integer.valueOf(var9.getZ()) });
/*     */         }
/*     */         
/* 151 */         var8 = ((TileEntitySign)var10).func_174880_d();
/*     */       }
/*     */     
/*     */     } else {
/*     */       
/* 156 */       Entity var12 = func_175768_b(sender, args[1]);
/* 157 */       var8 = var12.func_174807_aT();
/*     */     } 
/*     */     
/* 160 */     if ("set".equals(var5)) {
/*     */       
/* 162 */       String var13 = args[var11++];
/* 163 */       String var14 = args[var11];
/*     */       
/* 165 */       if (var13.length() == 0 || var14.length() == 0)
/*     */       {
/* 167 */         throw new CommandException("commands.stats.failed", new Object[0]);
/*     */       }
/*     */       
/* 170 */       CommandResultStats.func_179667_a(var8, var6, var13, var14);
/* 171 */       notifyOperators(sender, this, "commands.stats.success", new Object[] { var6.func_179637_b(), var14, var13 });
/*     */     }
/* 173 */     else if ("clear".equals(var5)) {
/*     */       
/* 175 */       CommandResultStats.func_179667_a(var8, var6, null, null);
/* 176 */       notifyOperators(sender, this, "commands.stats.cleared", new Object[] { var6.func_179637_b() });
/*     */     } 
/*     */     
/* 179 */     if (var3) {
/*     */       
/* 181 */       BlockPos var9 = func_175757_a(sender, args, 1, false);
/* 182 */       TileEntity var10 = var7.getTileEntity(var9);
/* 183 */       var10.markDirty();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List addTabCompletionOptions(ICommandSender sender, String[] args, BlockPos pos) {
/* 191 */     return (args.length == 1) ? getListOfStringsMatchingLastWord(args, new String[] { "entity", "block" }) : ((args.length == 2 && args[0].equals("entity")) ? getListOfStringsMatchingLastWord(args, func_175776_d()) : (((args.length != 3 || !args[0].equals("entity")) && (args.length != 5 || !args[0].equals("block"))) ? (((args.length != 4 || !args[0].equals("entity")) && (args.length != 6 || !args[0].equals("block"))) ? (((args.length != 6 || !args[0].equals("entity")) && (args.length != 8 || !args[0].equals("block"))) ? null : func_175762_a(args, func_175777_e())) : getListOfStringsMatchingLastWord(args, CommandResultStats.Type.func_179634_c())) : getListOfStringsMatchingLastWord(args, new String[] { "set", "clear" })));
/*     */   }
/*     */ 
/*     */   
/*     */   protected String[] func_175776_d() {
/* 196 */     return MinecraftServer.getServer().getAllUsernames();
/*     */   }
/*     */ 
/*     */   
/*     */   protected List func_175777_e() {
/* 201 */     Collection var1 = MinecraftServer.getServer().worldServerForDimension(0).getScoreboard().getScoreObjectives();
/* 202 */     ArrayList<String> var2 = Lists.newArrayList();
/* 203 */     Iterator<ScoreObjective> var3 = var1.iterator();
/*     */     
/* 205 */     while (var3.hasNext()) {
/*     */       
/* 207 */       ScoreObjective var4 = var3.next();
/*     */       
/* 209 */       if (!var4.getCriteria().isReadOnly())
/*     */       {
/* 211 */         var2.add(var4.getName());
/*     */       }
/*     */     } 
/*     */     
/* 215 */     return var2;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isUsernameIndex(String[] args, int index) {
/* 223 */     return (args.length > 0 && args[0].equals("entity") && index == 1);
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\command\CommandStats.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */