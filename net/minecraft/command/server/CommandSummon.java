/*     */ package net.minecraft.command.server;
/*     */ 
/*     */ import java.util.List;
/*     */ import net.minecraft.command.CommandBase;
/*     */ import net.minecraft.command.CommandException;
/*     */ import net.minecraft.command.ICommand;
/*     */ import net.minecraft.command.ICommandSender;
/*     */ import net.minecraft.command.WrongUsageException;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityList;
/*     */ import net.minecraft.entity.EntityLiving;
/*     */ import net.minecraft.entity.effect.EntityLightningBolt;
/*     */ import net.minecraft.nbt.JsonToNBT;
/*     */ import net.minecraft.nbt.NBTException;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.IChatComponent;
/*     */ import net.minecraft.util.Vec3;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class CommandSummon
/*     */   extends CommandBase
/*     */ {
/*     */   private static final String __OBFID = "CL_00001158";
/*     */   
/*     */   public String getCommandName() {
/*  27 */     return "summon";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getRequiredPermissionLevel() {
/*  35 */     return 2;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getCommandUsage(ICommandSender sender) {
/*  40 */     return "commands.summon.usage";
/*     */   }
/*     */ 
/*     */   
/*     */   public void processCommand(ICommandSender sender, String[] args) throws CommandException {
/*  45 */     if (args.length < 1)
/*     */     {
/*  47 */       throw new WrongUsageException("commands.summon.usage", new Object[0]);
/*     */     }
/*     */ 
/*     */     
/*  51 */     String var3 = args[0];
/*  52 */     BlockPos var4 = sender.getPosition();
/*  53 */     Vec3 var5 = sender.getPositionVector();
/*  54 */     double var6 = var5.xCoord;
/*  55 */     double var8 = var5.yCoord;
/*  56 */     double var10 = var5.zCoord;
/*     */     
/*  58 */     if (args.length >= 4) {
/*     */       
/*  60 */       var6 = func_175761_b(var6, args[1], true);
/*  61 */       var8 = func_175761_b(var8, args[2], false);
/*  62 */       var10 = func_175761_b(var10, args[3], true);
/*  63 */       var4 = new BlockPos(var6, var8, var10);
/*     */     } 
/*     */     
/*  66 */     World var12 = sender.getEntityWorld();
/*     */     
/*  68 */     if (!var12.isBlockLoaded(var4))
/*     */     {
/*  70 */       throw new CommandException("commands.summon.outOfWorld", new Object[0]);
/*     */     }
/*  72 */     if ("LightningBolt".equals(var3)) {
/*     */       
/*  74 */       var12.addWeatherEffect((Entity)new EntityLightningBolt(var12, var6, var8, var10));
/*  75 */       notifyOperators(sender, (ICommand)this, "commands.summon.success", new Object[0]);
/*     */     } else {
/*     */       Entity var21;
/*     */       
/*  79 */       NBTTagCompound var13 = new NBTTagCompound();
/*  80 */       boolean var14 = false;
/*     */       
/*  82 */       if (args.length >= 5) {
/*     */         
/*  84 */         IChatComponent var15 = getChatComponentFromNthArg(sender, args, 4);
/*     */ 
/*     */         
/*     */         try {
/*  88 */           var13 = JsonToNBT.func_180713_a(var15.getUnformattedText());
/*  89 */           var14 = true;
/*     */         }
/*  91 */         catch (NBTException var20) {
/*     */           
/*  93 */           throw new CommandException("commands.summon.tagError", new Object[] { var20.getMessage() });
/*     */         } 
/*     */       } 
/*     */       
/*  97 */       var13.setString("id", var3);
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/* 102 */         var21 = EntityList.createEntityFromNBT(var13, var12);
/*     */       }
/* 104 */       catch (RuntimeException var19) {
/*     */         
/* 106 */         throw new CommandException("commands.summon.failed", new Object[0]);
/*     */       } 
/*     */       
/* 109 */       if (var21 == null)
/*     */       {
/* 111 */         throw new CommandException("commands.summon.failed", new Object[0]);
/*     */       }
/*     */ 
/*     */       
/* 115 */       var21.setLocationAndAngles(var6, var8, var10, var21.rotationYaw, var21.rotationPitch);
/*     */       
/* 117 */       if (!var14 && var21 instanceof EntityLiving)
/*     */       {
/* 119 */         ((EntityLiving)var21).func_180482_a(var12.getDifficultyForLocation(new BlockPos(var21)), null);
/*     */       }
/*     */       
/* 122 */       var12.spawnEntityInWorld(var21);
/* 123 */       Entity var16 = var21;
/*     */       
/* 125 */       for (NBTTagCompound var17 = var13; var16 != null && var17.hasKey("Riding", 10); var17 = var17.getCompoundTag("Riding")) {
/*     */         
/* 127 */         Entity var18 = EntityList.createEntityFromNBT(var17.getCompoundTag("Riding"), var12);
/*     */         
/* 129 */         if (var18 != null) {
/*     */           
/* 131 */           var18.setLocationAndAngles(var6, var8, var10, var18.rotationYaw, var18.rotationPitch);
/* 132 */           var12.spawnEntityInWorld(var18);
/* 133 */           var16.mountEntity(var18);
/*     */         } 
/*     */         
/* 136 */         var16 = var18;
/*     */       } 
/*     */       
/* 139 */       notifyOperators(sender, (ICommand)this, "commands.summon.success", new Object[0]);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List addTabCompletionOptions(ICommandSender sender, String[] args, BlockPos pos) {
/* 147 */     return (args.length == 1) ? func_175762_a(args, EntityList.func_180124_b()) : ((args.length > 1 && args.length <= 4) ? func_175771_a(args, 1, pos) : null);
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\command\server\CommandSummon.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */