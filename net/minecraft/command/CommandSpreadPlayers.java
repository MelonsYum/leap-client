/*     */ package net.minecraft.command;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import com.google.common.collect.Maps;
/*     */ import com.google.common.collect.Sets;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.HashSet;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.player.EntityPlayerMP;
/*     */ import net.minecraft.scoreboard.Team;
/*     */ import net.minecraft.server.MinecraftServer;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.ChatComponentTranslation;
/*     */ import net.minecraft.util.IChatComponent;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class CommandSpreadPlayers
/*     */   extends CommandBase {
/*     */   private static final String __OBFID = "CL_00001080";
/*     */   
/*     */   public String getCommandName() {
/*  29 */     return "spreadplayers";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getRequiredPermissionLevel() {
/*  37 */     return 2;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getCommandUsage(ICommandSender sender) {
/*  42 */     return "commands.spreadplayers.usage";
/*     */   }
/*     */ 
/*     */   
/*     */   public void processCommand(ICommandSender sender, String[] args) throws CommandException {
/*  47 */     if (args.length < 6)
/*     */     {
/*  49 */       throw new WrongUsageException("commands.spreadplayers.usage", new Object[0]);
/*     */     }
/*     */ 
/*     */     
/*  53 */     byte var3 = 0;
/*  54 */     BlockPos var4 = sender.getPosition();
/*  55 */     double var10000 = var4.getX();
/*  56 */     int var17 = var3 + 1;
/*  57 */     double var5 = func_175761_b(var10000, args[var3], true);
/*  58 */     double var7 = func_175761_b(var4.getZ(), args[var17++], true);
/*  59 */     double var9 = parseDouble(args[var17++], 0.0D);
/*  60 */     double var11 = parseDouble(args[var17++], var9 + 1.0D);
/*  61 */     boolean var13 = parseBoolean(args[var17++]);
/*  62 */     ArrayList<EntityPlayerMP> var14 = Lists.newArrayList();
/*     */     
/*  64 */     while (var17 < args.length) {
/*     */       
/*  66 */       String var15 = args[var17++];
/*     */       
/*  68 */       if (PlayerSelector.hasArguments(var15)) {
/*     */         
/*  70 */         List var16 = PlayerSelector.func_179656_b(sender, var15, Entity.class);
/*     */         
/*  72 */         if (var16.size() == 0)
/*     */         {
/*  74 */           throw new EntityNotFoundException();
/*     */         }
/*     */         
/*  77 */         var14.addAll(var16);
/*     */         
/*     */         continue;
/*     */       } 
/*  81 */       EntityPlayerMP var18 = MinecraftServer.getServer().getConfigurationManager().getPlayerByUsername(var15);
/*     */       
/*  83 */       if (var18 == null)
/*     */       {
/*  85 */         throw new PlayerNotFoundException();
/*     */       }
/*     */       
/*  88 */       var14.add(var18);
/*     */     } 
/*     */ 
/*     */     
/*  92 */     sender.func_174794_a(CommandResultStats.Type.AFFECTED_ENTITIES, var14.size());
/*     */     
/*  94 */     if (var14.isEmpty())
/*     */     {
/*  96 */       throw new EntityNotFoundException();
/*     */     }
/*     */ 
/*     */     
/* 100 */     sender.addChatMessage((IChatComponent)new ChatComponentTranslation("commands.spreadplayers.spreading." + (var13 ? "teams" : "players"), new Object[] { Integer.valueOf(var14.size()), Double.valueOf(var11), Double.valueOf(var5), Double.valueOf(var7), Double.valueOf(var9) }));
/* 101 */     func_110669_a(sender, var14, new Position(var5, var7), var9, var11, ((Entity)var14.get(0)).worldObj, var13);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void func_110669_a(ICommandSender p_110669_1_, List p_110669_2_, Position p_110669_3_, double p_110669_4_, double p_110669_6_, World worldIn, boolean p_110669_9_) throws CommandException {
/* 108 */     Random var10 = new Random();
/* 109 */     double var11 = p_110669_3_.field_111101_a - p_110669_6_;
/* 110 */     double var13 = p_110669_3_.field_111100_b - p_110669_6_;
/* 111 */     double var15 = p_110669_3_.field_111101_a + p_110669_6_;
/* 112 */     double var17 = p_110669_3_.field_111100_b + p_110669_6_;
/* 113 */     Position[] var19 = func_110670_a(var10, p_110669_9_ ? func_110667_a(p_110669_2_) : p_110669_2_.size(), var11, var13, var15, var17);
/* 114 */     int var20 = func_110668_a(p_110669_3_, p_110669_4_, worldIn, var10, var11, var13, var15, var17, var19, p_110669_9_);
/* 115 */     double var21 = func_110671_a(p_110669_2_, worldIn, var19, p_110669_9_);
/* 116 */     notifyOperators(p_110669_1_, this, "commands.spreadplayers.success." + (p_110669_9_ ? "teams" : "players"), new Object[] { Integer.valueOf(var19.length), Double.valueOf(p_110669_3_.field_111101_a), Double.valueOf(p_110669_3_.field_111100_b) });
/*     */     
/* 118 */     if (var19.length > 1)
/*     */     {
/* 120 */       p_110669_1_.addChatMessage((IChatComponent)new ChatComponentTranslation("commands.spreadplayers.info." + (p_110669_9_ ? "teams" : "players"), new Object[] { String.format("%.2f", new Object[] { Double.valueOf(var21) }), Integer.valueOf(var20) }));
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private int func_110667_a(List p_110667_1_) {
/* 126 */     HashSet<Team> var2 = Sets.newHashSet();
/* 127 */     Iterator<Entity> var3 = p_110667_1_.iterator();
/*     */     
/* 129 */     while (var3.hasNext()) {
/*     */       
/* 131 */       Entity var4 = var3.next();
/*     */       
/* 133 */       if (var4 instanceof EntityPlayer) {
/*     */         
/* 135 */         var2.add(((EntityPlayer)var4).getTeam());
/*     */         
/*     */         continue;
/*     */       } 
/* 139 */       var2.add(null);
/*     */     } 
/*     */ 
/*     */     
/* 143 */     return var2.size();
/*     */   }
/*     */ 
/*     */   
/*     */   private int func_110668_a(Position p_110668_1_, double p_110668_2_, World worldIn, Random p_110668_5_, double p_110668_6_, double p_110668_8_, double p_110668_10_, double p_110668_12_, Position[] p_110668_14_, boolean p_110668_15_) throws CommandException {
/* 148 */     boolean var16 = true;
/* 149 */     double var18 = 3.4028234663852886E38D;
/*     */     
/*     */     int var17;
/* 152 */     for (var17 = 0; var17 < 10000 && var16; var17++) {
/*     */       
/* 154 */       var16 = false;
/* 155 */       var18 = 3.4028234663852886E38D;
/*     */ 
/*     */ 
/*     */       
/* 159 */       for (int var20 = 0; var20 < p_110668_14_.length; var20++) {
/*     */         
/* 161 */         Position var21 = p_110668_14_[var20];
/* 162 */         int var22 = 0;
/* 163 */         Position var23 = new Position();
/*     */         
/* 165 */         for (int var24 = 0; var24 < p_110668_14_.length; var24++) {
/*     */           
/* 167 */           if (var20 != var24) {
/*     */             
/* 169 */             Position var25 = p_110668_14_[var24];
/* 170 */             double var26 = var21.func_111099_a(var25);
/* 171 */             var18 = Math.min(var26, var18);
/*     */             
/* 173 */             if (var26 < p_110668_2_) {
/*     */               
/* 175 */               var22++;
/* 176 */               var23.field_111101_a += var25.field_111101_a - var21.field_111101_a;
/* 177 */               var23.field_111100_b += var25.field_111100_b - var21.field_111100_b;
/*     */             } 
/*     */           } 
/*     */         } 
/*     */         
/* 182 */         if (var22 > 0) {
/*     */           
/* 184 */           var23.field_111101_a /= var22;
/* 185 */           var23.field_111100_b /= var22;
/* 186 */           double var30 = var23.func_111096_b();
/*     */           
/* 188 */           if (var30 > 0.0D) {
/*     */             
/* 190 */             var23.func_111095_a();
/* 191 */             var21.func_111094_b(var23);
/*     */           }
/*     */           else {
/*     */             
/* 195 */             var21.func_111097_a(p_110668_5_, p_110668_6_, p_110668_8_, p_110668_10_, p_110668_12_);
/*     */           } 
/*     */           
/* 198 */           var16 = true;
/*     */         } 
/*     */         
/* 201 */         if (var21.func_111093_a(p_110668_6_, p_110668_8_, p_110668_10_, p_110668_12_))
/*     */         {
/* 203 */           var16 = true;
/*     */         }
/*     */       } 
/*     */       
/* 207 */       if (!var16) {
/*     */         
/* 209 */         Position[] var28 = p_110668_14_;
/* 210 */         int var29 = p_110668_14_.length;
/*     */         
/* 212 */         for (int var22 = 0; var22 < var29; var22++) {
/*     */           
/* 214 */           Position var23 = var28[var22];
/*     */           
/* 216 */           if (!var23.func_111098_b(worldIn)) {
/*     */             
/* 218 */             var23.func_111097_a(p_110668_5_, p_110668_6_, p_110668_8_, p_110668_10_, p_110668_12_);
/* 219 */             var16 = true;
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 225 */     if (var17 >= 10000)
/*     */     {
/* 227 */       throw new CommandException("commands.spreadplayers.failure." + (p_110668_15_ ? "teams" : "players"), new Object[] { Integer.valueOf(p_110668_14_.length), Double.valueOf(p_110668_1_.field_111101_a), Double.valueOf(p_110668_1_.field_111100_b), String.format("%.2f", new Object[] { Double.valueOf(var18) }) });
/*     */     }
/*     */ 
/*     */     
/* 231 */     return var17;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private double func_110671_a(List<Entity> p_110671_1_, World worldIn, Position[] p_110671_3_, boolean p_110671_4_) {
/* 237 */     double var5 = 0.0D;
/* 238 */     int var7 = 0;
/* 239 */     HashMap<Team, Position> var8 = Maps.newHashMap();
/*     */     
/* 241 */     for (int var9 = 0; var9 < p_110671_1_.size(); var9++) {
/*     */       Position var11;
/* 243 */       Entity var10 = p_110671_1_.get(var9);
/*     */ 
/*     */       
/* 246 */       if (p_110671_4_) {
/*     */         
/* 248 */         Team var12 = (var10 instanceof EntityPlayer) ? ((EntityPlayer)var10).getTeam() : null;
/*     */         
/* 250 */         if (!var8.containsKey(var12))
/*     */         {
/* 252 */           var8.put(var12, p_110671_3_[var7++]);
/*     */         }
/*     */         
/* 255 */         var11 = var8.get(var12);
/*     */       }
/*     */       else {
/*     */         
/* 259 */         var11 = p_110671_3_[var7++];
/*     */       } 
/*     */       
/* 262 */       var10.setPositionAndUpdate((MathHelper.floor_double(var11.field_111101_a) + 0.5F), var11.func_111092_a(worldIn), MathHelper.floor_double(var11.field_111100_b) + 0.5D);
/* 263 */       double var17 = Double.MAX_VALUE;
/*     */       
/* 265 */       for (int var14 = 0; var14 < p_110671_3_.length; var14++) {
/*     */         
/* 267 */         if (var11 != p_110671_3_[var14]) {
/*     */           
/* 269 */           double var15 = var11.func_111099_a(p_110671_3_[var14]);
/* 270 */           var17 = Math.min(var15, var17);
/*     */         } 
/*     */       } 
/*     */       
/* 274 */       var5 += var17;
/*     */     } 
/*     */     
/* 277 */     var5 /= p_110671_1_.size();
/* 278 */     return var5;
/*     */   }
/*     */ 
/*     */   
/*     */   private Position[] func_110670_a(Random p_110670_1_, int p_110670_2_, double p_110670_3_, double p_110670_5_, double p_110670_7_, double p_110670_9_) {
/* 283 */     Position[] var11 = new Position[p_110670_2_];
/*     */     
/* 285 */     for (int var12 = 0; var12 < var11.length; var12++) {
/*     */       
/* 287 */       Position var13 = new Position();
/* 288 */       var13.func_111097_a(p_110670_1_, p_110670_3_, p_110670_5_, p_110670_7_, p_110670_9_);
/* 289 */       var11[var12] = var13;
/*     */     } 
/*     */     
/* 292 */     return var11;
/*     */   }
/*     */ 
/*     */   
/*     */   static class Position
/*     */   {
/*     */     double field_111101_a;
/*     */     double field_111100_b;
/*     */     private static final String __OBFID = "CL_00001105";
/*     */     
/*     */     Position() {}
/*     */     
/*     */     Position(double p_i1358_1_, double p_i1358_3_) {
/* 305 */       this.field_111101_a = p_i1358_1_;
/* 306 */       this.field_111100_b = p_i1358_3_;
/*     */     }
/*     */ 
/*     */     
/*     */     double func_111099_a(Position p_111099_1_) {
/* 311 */       double var2 = this.field_111101_a - p_111099_1_.field_111101_a;
/* 312 */       double var4 = this.field_111100_b - p_111099_1_.field_111100_b;
/* 313 */       return Math.sqrt(var2 * var2 + var4 * var4);
/*     */     }
/*     */ 
/*     */     
/*     */     void func_111095_a() {
/* 318 */       double var1 = func_111096_b();
/* 319 */       this.field_111101_a /= var1;
/* 320 */       this.field_111100_b /= var1;
/*     */     }
/*     */ 
/*     */     
/*     */     float func_111096_b() {
/* 325 */       return MathHelper.sqrt_double(this.field_111101_a * this.field_111101_a + this.field_111100_b * this.field_111100_b);
/*     */     }
/*     */ 
/*     */     
/*     */     public void func_111094_b(Position p_111094_1_) {
/* 330 */       this.field_111101_a -= p_111094_1_.field_111101_a;
/* 331 */       this.field_111100_b -= p_111094_1_.field_111100_b;
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean func_111093_a(double p_111093_1_, double p_111093_3_, double p_111093_5_, double p_111093_7_) {
/* 336 */       boolean var9 = false;
/*     */       
/* 338 */       if (this.field_111101_a < p_111093_1_) {
/*     */         
/* 340 */         this.field_111101_a = p_111093_1_;
/* 341 */         var9 = true;
/*     */       }
/* 343 */       else if (this.field_111101_a > p_111093_5_) {
/*     */         
/* 345 */         this.field_111101_a = p_111093_5_;
/* 346 */         var9 = true;
/*     */       } 
/*     */       
/* 349 */       if (this.field_111100_b < p_111093_3_) {
/*     */         
/* 351 */         this.field_111100_b = p_111093_3_;
/* 352 */         var9 = true;
/*     */       }
/* 354 */       else if (this.field_111100_b > p_111093_7_) {
/*     */         
/* 356 */         this.field_111100_b = p_111093_7_;
/* 357 */         var9 = true;
/*     */       } 
/*     */       
/* 360 */       return var9;
/*     */     }
/*     */ 
/*     */     
/*     */     public int func_111092_a(World worldIn) {
/* 365 */       BlockPos var2 = new BlockPos(this.field_111101_a, 256.0D, this.field_111100_b);
/*     */ 
/*     */       
/*     */       do {
/* 369 */         if (var2.getY() <= 0)
/*     */         {
/* 371 */           return 257;
/*     */         }
/*     */         
/* 374 */         var2 = var2.offsetDown();
/*     */       }
/* 376 */       while (worldIn.getBlockState(var2).getBlock().getMaterial() == Material.air);
/*     */       
/* 378 */       return var2.getY() + 1;
/*     */     }
/*     */     
/*     */     public boolean func_111098_b(World worldIn) {
/*     */       Material var3;
/* 383 */       BlockPos var2 = new BlockPos(this.field_111101_a, 256.0D, this.field_111100_b);
/*     */ 
/*     */ 
/*     */       
/*     */       do {
/* 388 */         if (var2.getY() <= 0)
/*     */         {
/* 390 */           return false;
/*     */         }
/*     */         
/* 393 */         var2 = var2.offsetDown();
/* 394 */         var3 = worldIn.getBlockState(var2).getBlock().getMaterial();
/*     */       }
/* 396 */       while (var3 == Material.air);
/*     */       
/* 398 */       return (!var3.isLiquid() && var3 != Material.fire);
/*     */     }
/*     */ 
/*     */     
/*     */     public void func_111097_a(Random p_111097_1_, double p_111097_2_, double p_111097_4_, double p_111097_6_, double p_111097_8_) {
/* 403 */       this.field_111101_a = MathHelper.getRandomDoubleInRange(p_111097_1_, p_111097_2_, p_111097_6_);
/* 404 */       this.field_111100_b = MathHelper.getRandomDoubleInRange(p_111097_1_, p_111097_4_, p_111097_8_);
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\command\CommandSpreadPlayers.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */