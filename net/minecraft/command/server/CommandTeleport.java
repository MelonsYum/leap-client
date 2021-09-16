/*     */ package net.minecraft.command.server;
/*     */ 
/*     */ import java.util.EnumSet;
/*     */ import java.util.List;
/*     */ import net.minecraft.command.CommandBase;
/*     */ import net.minecraft.command.CommandException;
/*     */ import net.minecraft.command.ICommand;
/*     */ import net.minecraft.command.ICommandSender;
/*     */ import net.minecraft.command.WrongUsageException;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.player.EntityPlayerMP;
/*     */ import net.minecraft.network.play.server.S08PacketPlayerPosLook;
/*     */ import net.minecraft.server.MinecraftServer;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.MathHelper;
/*     */ 
/*     */ public class CommandTeleport
/*     */   extends CommandBase {
/*     */   private static final String __OBFID = "CL_00001180";
/*     */   
/*     */   public String getCommandName() {
/*  22 */     return "tp";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getRequiredPermissionLevel() {
/*  30 */     return 2;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getCommandUsage(ICommandSender sender) {
/*  35 */     return "commands.tp.usage";
/*     */   }
/*     */   
/*     */   public void processCommand(ICommandSender sender, String[] args) throws CommandException {
/*     */     Object var4;
/*  40 */     if (args.length < 1)
/*     */     {
/*  42 */       throw new WrongUsageException("commands.tp.usage", new Object[0]);
/*     */     }
/*     */ 
/*     */     
/*  46 */     byte var3 = 0;
/*     */ 
/*     */     
/*  49 */     if (args.length != 2 && args.length != 4 && args.length != 6) {
/*     */       
/*  51 */       var4 = getCommandSenderAsPlayer(sender);
/*     */     }
/*     */     else {
/*     */       
/*  55 */       var4 = func_175768_b(sender, args[0]);
/*  56 */       var3 = 1;
/*     */     } 
/*     */     
/*  59 */     if (args.length != 1 && args.length != 2) {
/*     */       
/*  61 */       if (args.length < var3 + 3)
/*     */       {
/*  63 */         throw new WrongUsageException("commands.tp.usage", new Object[0]);
/*     */       }
/*  65 */       if (((Entity)var4).worldObj != null)
/*     */       {
/*  67 */         int var14 = var3 + 1;
/*  68 */         CommandBase.CoordinateArg var6 = func_175770_a(((Entity)var4).posX, args[var3], true);
/*  69 */         CommandBase.CoordinateArg var7 = func_175767_a(((Entity)var4).posY, args[var14++], 0, 0, false);
/*  70 */         CommandBase.CoordinateArg var8 = func_175770_a(((Entity)var4).posZ, args[var14++], true);
/*  71 */         CommandBase.CoordinateArg var9 = func_175770_a(((Entity)var4).rotationYaw, (args.length > var14) ? args[var14++] : "~", false);
/*  72 */         CommandBase.CoordinateArg var10 = func_175770_a(((Entity)var4).rotationPitch, (args.length > var14) ? args[var14] : "~", false);
/*     */ 
/*     */         
/*  75 */         if (var4 instanceof EntityPlayerMP) {
/*     */           
/*  77 */           EnumSet<S08PacketPlayerPosLook.EnumFlags> var11 = EnumSet.noneOf(S08PacketPlayerPosLook.EnumFlags.class);
/*     */           
/*  79 */           if (var6.func_179630_c())
/*     */           {
/*  81 */             var11.add(S08PacketPlayerPosLook.EnumFlags.X);
/*     */           }
/*     */           
/*  84 */           if (var7.func_179630_c())
/*     */           {
/*  86 */             var11.add(S08PacketPlayerPosLook.EnumFlags.Y);
/*     */           }
/*     */           
/*  89 */           if (var8.func_179630_c())
/*     */           {
/*  91 */             var11.add(S08PacketPlayerPosLook.EnumFlags.Z);
/*     */           }
/*     */           
/*  94 */           if (var10.func_179630_c())
/*     */           {
/*  96 */             var11.add(S08PacketPlayerPosLook.EnumFlags.X_ROT);
/*     */           }
/*     */           
/*  99 */           if (var9.func_179630_c())
/*     */           {
/* 101 */             var11.add(S08PacketPlayerPosLook.EnumFlags.Y_ROT);
/*     */           }
/*     */           
/* 104 */           float var12 = (float)var9.func_179629_b();
/*     */           
/* 106 */           if (!var9.func_179630_c())
/*     */           {
/* 108 */             var12 = MathHelper.wrapAngleTo180_float(var12);
/*     */           }
/*     */           
/* 111 */           float var13 = (float)var10.func_179629_b();
/*     */           
/* 113 */           if (!var10.func_179630_c())
/*     */           {
/* 115 */             var13 = MathHelper.wrapAngleTo180_float(var13);
/*     */           }
/*     */           
/* 118 */           if (var13 > 90.0F || var13 < -90.0F) {
/*     */             
/* 120 */             var13 = MathHelper.wrapAngleTo180_float(180.0F - var13);
/* 121 */             var12 = MathHelper.wrapAngleTo180_float(var12 + 180.0F);
/*     */           } 
/*     */           
/* 124 */           ((Entity)var4).mountEntity(null);
/* 125 */           ((EntityPlayerMP)var4).playerNetServerHandler.func_175089_a(var6.func_179629_b(), var7.func_179629_b(), var8.func_179629_b(), var12, var13, var11);
/* 126 */           ((Entity)var4).setRotationYawHead(var12);
/*     */         }
/*     */         else {
/*     */           
/* 130 */           float var15 = (float)MathHelper.wrapAngleTo180_double(var9.func_179628_a());
/* 131 */           float var12 = (float)MathHelper.wrapAngleTo180_double(var10.func_179628_a());
/*     */           
/* 133 */           if (var12 > 90.0F || var12 < -90.0F) {
/*     */             
/* 135 */             var12 = MathHelper.wrapAngleTo180_float(180.0F - var12);
/* 136 */             var15 = MathHelper.wrapAngleTo180_float(var15 + 180.0F);
/*     */           } 
/*     */           
/* 139 */           ((Entity)var4).setLocationAndAngles(var6.func_179628_a(), var7.func_179628_a(), var8.func_179628_a(), var15, var12);
/* 140 */           ((Entity)var4).setRotationYawHead(var15);
/*     */         } 
/*     */         
/* 143 */         notifyOperators(sender, (ICommand)this, "commands.tp.success.coordinates", new Object[] { ((Entity)var4).getName(), Double.valueOf(var6.func_179628_a()), Double.valueOf(var7.func_179628_a()), Double.valueOf(var8.func_179628_a()) });
/*     */       }
/*     */     
/*     */     } else {
/*     */       
/* 148 */       Entity var5 = func_175768_b(sender, args[args.length - 1]);
/*     */       
/* 150 */       if (var5.worldObj != ((Entity)var4).worldObj)
/*     */       {
/* 152 */         throw new CommandException("commands.tp.notSameDimension", new Object[0]);
/*     */       }
/*     */ 
/*     */       
/* 156 */       ((Entity)var4).mountEntity(null);
/*     */       
/* 158 */       if (var4 instanceof EntityPlayerMP) {
/*     */         
/* 160 */         ((EntityPlayerMP)var4).playerNetServerHandler.setPlayerLocation(var5.posX, var5.posY, var5.posZ, var5.rotationYaw, var5.rotationPitch);
/*     */       }
/*     */       else {
/*     */         
/* 164 */         ((Entity)var4).setLocationAndAngles(var5.posX, var5.posY, var5.posZ, var5.rotationYaw, var5.rotationPitch);
/*     */       } 
/*     */       
/* 167 */       notifyOperators(sender, (ICommand)this, "commands.tp.success", new Object[] { ((Entity)var4).getName(), var5.getName() });
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List addTabCompletionOptions(ICommandSender sender, String[] args, BlockPos pos) {
/* 175 */     return (args.length != 1 && args.length != 2) ? null : getListOfStringsMatchingLastWord(args, MinecraftServer.getServer().getAllUsernames());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isUsernameIndex(String[] args, int index) {
/* 183 */     return (index == 0);
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\command\server\CommandTeleport.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */