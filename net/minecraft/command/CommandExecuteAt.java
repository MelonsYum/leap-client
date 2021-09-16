/*     */ package net.minecraft.command;
/*     */ 
/*     */ import java.util.List;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.server.MinecraftServer;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.IChatComponent;
/*     */ import net.minecraft.util.Vec3;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class CommandExecuteAt
/*     */   extends CommandBase
/*     */ {
/*     */   private static final String __OBFID = "CL_00002344";
/*     */   
/*     */   public String getCommandName() {
/*  19 */     return "execute";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getRequiredPermissionLevel() {
/*  27 */     return 2;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getCommandUsage(ICommandSender sender) {
/*  32 */     return "commands.execute.usage";
/*     */   }
/*     */ 
/*     */   
/*     */   public void processCommand(final ICommandSender sender, String[] args) throws CommandException {
/*  37 */     if (args.length < 5)
/*     */     {
/*  39 */       throw new WrongUsageException("commands.execute.usage", new Object[0]);
/*     */     }
/*     */ 
/*     */     
/*  43 */     final Entity var3 = func_175759_a(sender, args[0], Entity.class);
/*  44 */     final double var4 = func_175761_b(var3.posX, args[1], false);
/*  45 */     final double var6 = func_175761_b(var3.posY, args[2], false);
/*  46 */     final double var8 = func_175761_b(var3.posZ, args[3], false);
/*  47 */     final BlockPos var10 = new BlockPos(var4, var6, var8);
/*  48 */     byte var11 = 4;
/*     */     
/*  50 */     if ("detect".equals(args[4]) && args.length > 10) {
/*     */       
/*  52 */       World var12 = sender.getEntityWorld();
/*  53 */       double var13 = func_175761_b(var4, args[5], false);
/*  54 */       double var15 = func_175761_b(var6, args[6], false);
/*  55 */       double var17 = func_175761_b(var8, args[7], false);
/*  56 */       Block var19 = getBlockByText(sender, args[8]);
/*  57 */       int var20 = parseInt(args[9], -1, 15);
/*  58 */       BlockPos var21 = new BlockPos(var13, var15, var17);
/*  59 */       IBlockState var22 = var12.getBlockState(var21);
/*     */       
/*  61 */       if (var22.getBlock() != var19 || (var20 >= 0 && var22.getBlock().getMetaFromState(var22) != var20))
/*     */       {
/*  63 */         throw new CommandException("commands.execute.failed", new Object[] { "detect", var3.getName() });
/*     */       }
/*     */       
/*  66 */       var11 = 10;
/*     */     } 
/*     */     
/*  69 */     String var24 = func_180529_a(args, var11);
/*  70 */     ICommandSender var14 = new ICommandSender()
/*     */       {
/*     */         private static final String __OBFID = "CL_00002343";
/*     */         
/*     */         public String getName() {
/*  75 */           return var3.getName();
/*     */         }
/*     */         
/*     */         public IChatComponent getDisplayName() {
/*  79 */           return var3.getDisplayName();
/*     */         }
/*     */         
/*     */         public void addChatMessage(IChatComponent message) {
/*  83 */           sender.addChatMessage(message);
/*     */         }
/*     */         
/*     */         public boolean canCommandSenderUseCommand(int permissionLevel, String command) {
/*  87 */           return sender.canCommandSenderUseCommand(permissionLevel, command);
/*     */         }
/*     */         
/*     */         public BlockPos getPosition() {
/*  91 */           return var10;
/*     */         }
/*     */         
/*     */         public Vec3 getPositionVector() {
/*  95 */           return new Vec3(var4, var6, var8);
/*     */         }
/*     */         
/*     */         public World getEntityWorld() {
/*  99 */           return var3.worldObj;
/*     */         }
/*     */         
/*     */         public Entity getCommandSenderEntity() {
/* 103 */           return var3;
/*     */         }
/*     */         
/*     */         public boolean sendCommandFeedback() {
/* 107 */           MinecraftServer var1 = MinecraftServer.getServer();
/* 108 */           return !(var1 != null && !var1.worldServers[0].getGameRules().getGameRuleBooleanValue("commandBlockOutput"));
/*     */         }
/*     */         
/*     */         public void func_174794_a(CommandResultStats.Type p_174794_1_, int p_174794_2_) {
/* 112 */           var3.func_174794_a(p_174794_1_, p_174794_2_);
/*     */         }
/*     */       };
/* 115 */     ICommandManager var25 = MinecraftServer.getServer().getCommandManager();
/*     */ 
/*     */     
/*     */     try {
/* 119 */       int var16 = var25.executeCommand(var14, var24);
/*     */       
/* 121 */       if (var16 < 1)
/*     */       {
/* 123 */         throw new CommandException("commands.execute.allInvocationsFailed", new Object[] { var24 });
/*     */       }
/*     */     }
/* 126 */     catch (Throwable var23) {
/*     */       
/* 128 */       throw new CommandException("commands.execute.failed", new Object[] { var24, var3.getName() });
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public List addTabCompletionOptions(ICommandSender sender, String[] args, BlockPos pos) {
/* 135 */     return (args.length == 1) ? getListOfStringsMatchingLastWord(args, MinecraftServer.getServer().getAllUsernames()) : ((args.length > 1 && args.length <= 4) ? func_175771_a(args, 1, pos) : ((args.length > 5 && args.length <= 8 && "detect".equals(args[4])) ? func_175771_a(args, 5, pos) : ((args.length == 9 && "detect".equals(args[4])) ? func_175762_a(args, Block.blockRegistry.getKeys()) : null)));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isUsernameIndex(String[] args, int index) {
/* 143 */     return (index == 0);
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\command\CommandExecuteAt.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */