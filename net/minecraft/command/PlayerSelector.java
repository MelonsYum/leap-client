/*     */ package net.minecraft.command;
/*     */ 
/*     */ import com.google.common.base.Predicate;
/*     */ import com.google.common.base.Predicates;
/*     */ import com.google.common.collect.ComparisonChain;
/*     */ import com.google.common.collect.Lists;
/*     */ import com.google.common.collect.Maps;
/*     */ import com.google.common.collect.Sets;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.Comparator;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import java.util.regex.Matcher;
/*     */ import java.util.regex.Pattern;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityList;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.player.EntityPlayerMP;
/*     */ import net.minecraft.scoreboard.Score;
/*     */ import net.minecraft.scoreboard.ScoreObjective;
/*     */ import net.minecraft.scoreboard.Scoreboard;
/*     */ import net.minecraft.scoreboard.Team;
/*     */ import net.minecraft.server.MinecraftServer;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.ChatComponentTranslation;
/*     */ import net.minecraft.util.EnumChatFormatting;
/*     */ import net.minecraft.util.IChatComponent;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.WorldSettings;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PlayerSelector
/*     */ {
/*  44 */   private static final Pattern tokenPattern = Pattern.compile("^@([pare])(?:\\[([\\w=,!-]*)\\])?$");
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  49 */   private static final Pattern intListPattern = Pattern.compile("\\G([-!]?[\\w-]*)(?:$|,)");
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  54 */   private static final Pattern keyValueListPattern = Pattern.compile("\\G(\\w+)=([-!]?[\\w-]*)(?:$|,)");
/*  55 */   private static final Set field_179666_d = Sets.newHashSet((Object[])new String[] { "x", "y", "z", "dx", "dy", "dz", "rm", "r" });
/*     */ 
/*     */   
/*     */   private static final String __OBFID = "CL_00000086";
/*     */ 
/*     */ 
/*     */   
/*     */   public static EntityPlayerMP matchOnePlayer(ICommandSender p_82386_0_, String p_82386_1_) {
/*  63 */     return (EntityPlayerMP)func_179652_a(p_82386_0_, p_82386_1_, EntityPlayerMP.class);
/*     */   }
/*     */ 
/*     */   
/*     */   public static Entity func_179652_a(ICommandSender p_179652_0_, String p_179652_1_, Class p_179652_2_) {
/*  68 */     List<Entity> var3 = func_179656_b(p_179652_0_, p_179652_1_, p_179652_2_);
/*  69 */     return (var3.size() == 1) ? var3.get(0) : null;
/*     */   }
/*     */ 
/*     */   
/*     */   public static IChatComponent func_150869_b(ICommandSender p_150869_0_, String p_150869_1_) {
/*  74 */     List var2 = func_179656_b(p_150869_0_, p_150869_1_, Entity.class);
/*     */     
/*  76 */     if (var2.isEmpty())
/*     */     {
/*  78 */       return null;
/*     */     }
/*     */ 
/*     */     
/*  82 */     ArrayList<IChatComponent> var3 = Lists.newArrayList();
/*  83 */     Iterator<Entity> var4 = var2.iterator();
/*     */     
/*  85 */     while (var4.hasNext()) {
/*     */       
/*  87 */       Entity var5 = var4.next();
/*  88 */       var3.add(var5.getDisplayName());
/*     */     } 
/*     */     
/*  91 */     return CommandBase.join(var3);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static List func_179656_b(ICommandSender p_179656_0_, String p_179656_1_, Class p_179656_2_) {
/*  97 */     Matcher var3 = tokenPattern.matcher(p_179656_1_);
/*     */     
/*  99 */     if (var3.matches() && p_179656_0_.canCommandSenderUseCommand(1, "@")) {
/*     */       
/* 101 */       Map var4 = getArgumentMap(var3.group(2));
/*     */       
/* 103 */       if (!func_179655_b(p_179656_0_, var4))
/*     */       {
/* 105 */         return Collections.emptyList();
/*     */       }
/*     */ 
/*     */       
/* 109 */       String var5 = var3.group(1);
/* 110 */       BlockPos var6 = func_179664_b(var4, p_179656_0_.getPosition());
/* 111 */       List var7 = func_179654_a(p_179656_0_, var4);
/* 112 */       ArrayList var8 = Lists.newArrayList();
/* 113 */       Iterator<World> var9 = var7.iterator();
/*     */       
/* 115 */       while (var9.hasNext()) {
/*     */         
/* 117 */         World var10 = var9.next();
/*     */         
/* 119 */         if (var10 != null) {
/*     */           
/* 121 */           ArrayList var11 = Lists.newArrayList();
/* 122 */           var11.addAll(func_179663_a(var4, var5));
/* 123 */           var11.addAll(func_179648_b(var4));
/* 124 */           var11.addAll(func_179649_c(var4));
/* 125 */           var11.addAll(func_179659_d(var4));
/* 126 */           var11.addAll(func_179657_e(var4));
/* 127 */           var11.addAll(func_179647_f(var4));
/* 128 */           var11.addAll(func_180698_a(var4, var6));
/* 129 */           var11.addAll(func_179662_g(var4));
/* 130 */           var8.addAll(func_179660_a(var4, p_179656_2_, var11, var5, var10, var6));
/*     */         } 
/*     */       } 
/*     */       
/* 134 */       return func_179658_a(var8, var4, p_179656_0_, p_179656_2_, var5, var6);
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 139 */     return Collections.emptyList();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static List func_179654_a(ICommandSender p_179654_0_, Map p_179654_1_) {
/* 145 */     ArrayList<World> var2 = Lists.newArrayList();
/*     */     
/* 147 */     if (func_179665_h(p_179654_1_)) {
/*     */       
/* 149 */       var2.add(p_179654_0_.getEntityWorld());
/*     */     }
/*     */     else {
/*     */       
/* 153 */       Collections.addAll(var2, (Object[])(MinecraftServer.getServer()).worldServers);
/*     */     } 
/*     */     
/* 156 */     return var2;
/*     */   }
/*     */ 
/*     */   
/*     */   private static boolean func_179655_b(ICommandSender p_179655_0_, Map p_179655_1_) {
/* 161 */     String var2 = func_179651_b(p_179655_1_, "type");
/* 162 */     var2 = (var2 != null && var2.startsWith("!")) ? var2.substring(1) : var2;
/*     */     
/* 164 */     if (var2 != null && !EntityList.func_180125_b(var2)) {
/*     */       
/* 166 */       ChatComponentTranslation var3 = new ChatComponentTranslation("commands.generic.entity.invalidType", new Object[] { var2 });
/* 167 */       var3.getChatStyle().setColor(EnumChatFormatting.RED);
/* 168 */       p_179655_0_.addChatMessage((IChatComponent)var3);
/* 169 */       return false;
/*     */     } 
/*     */ 
/*     */     
/* 173 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static List func_179663_a(Map p_179663_0_, String p_179663_1_) {
/* 179 */     ArrayList<Predicate> var2 = Lists.newArrayList();
/* 180 */     String var3 = func_179651_b(p_179663_0_, "type");
/* 181 */     final boolean var4 = (var3 != null && var3.startsWith("!"));
/*     */     
/* 183 */     if (var4)
/*     */     {
/* 185 */       var3 = var3.substring(1);
/*     */     }
/*     */     
/* 188 */     boolean var6 = !p_179663_1_.equals("e");
/* 189 */     boolean var7 = (p_179663_1_.equals("r") && var3 != null);
/*     */     
/* 191 */     if ((var3 == null || !p_179663_1_.equals("e")) && !var7) {
/*     */       
/* 193 */       if (var6)
/*     */       {
/* 195 */         var2.add(new Predicate()
/*     */             {
/*     */               private static final String __OBFID = "CL_00002358";
/*     */               
/*     */               public boolean func_179624_a(Entity p_179624_1_) {
/* 200 */                 return p_179624_1_ instanceof net.minecraft.entity.player.EntityPlayer;
/*     */               }
/*     */               
/*     */               public boolean apply(Object p_apply_1_) {
/* 204 */                 return func_179624_a((Entity)p_apply_1_);
/*     */               }
/*     */             });
/*     */       }
/*     */     }
/*     */     else {
/*     */       
/* 211 */       final String var3_f = var3;
/* 212 */       var2.add(new Predicate()
/*     */           {
/*     */             private static final String __OBFID = "CL_00002362";
/*     */             
/*     */             public boolean func_179613_a(Entity p_179613_1_) {
/* 217 */               return EntityList.func_180123_a(p_179613_1_, var3_f) ^ var4;
/*     */             }
/*     */             
/*     */             public boolean apply(Object p_apply_1_) {
/* 221 */               return func_179613_a((Entity)p_apply_1_);
/*     */             }
/*     */           });
/*     */     } 
/*     */     
/* 226 */     return var2;
/*     */   }
/*     */ 
/*     */   
/*     */   private static List func_179648_b(Map p_179648_0_) {
/* 231 */     ArrayList<Predicate> var1 = Lists.newArrayList();
/* 232 */     final int var2 = func_179653_a(p_179648_0_, "lm", -1);
/* 233 */     final int var3 = func_179653_a(p_179648_0_, "l", -1);
/*     */     
/* 235 */     if (var2 > -1 || var3 > -1)
/*     */     {
/* 237 */       var1.add(new Predicate()
/*     */           {
/*     */             private static final String __OBFID = "CL_00002357";
/*     */             
/*     */             public boolean func_179625_a(Entity p_179625_1_) {
/* 242 */               if (!(p_179625_1_ instanceof EntityPlayerMP))
/*     */               {
/* 244 */                 return false;
/*     */               }
/*     */ 
/*     */               
/* 248 */               EntityPlayerMP var2x = (EntityPlayerMP)p_179625_1_;
/* 249 */               return ((var2 <= -1 || var2x.experienceLevel >= var2) && (var3 <= -1 || var2x.experienceLevel <= var3));
/*     */             }
/*     */ 
/*     */             
/*     */             public boolean apply(Object p_apply_1_) {
/* 254 */               return func_179625_a((Entity)p_apply_1_);
/*     */             }
/*     */           });
/*     */     }
/*     */     
/* 259 */     return var1;
/*     */   }
/*     */ 
/*     */   
/*     */   private static List func_179649_c(Map p_179649_0_) {
/* 264 */     ArrayList<Predicate> var1 = Lists.newArrayList();
/* 265 */     final int var2 = func_179653_a(p_179649_0_, "m", WorldSettings.GameType.NOT_SET.getID());
/*     */     
/* 267 */     if (var2 != WorldSettings.GameType.NOT_SET.getID())
/*     */     {
/* 269 */       var1.add(new Predicate()
/*     */           {
/*     */             private static final String __OBFID = "CL_00002356";
/*     */             
/*     */             public boolean func_179619_a(Entity p_179619_1_) {
/* 274 */               if (!(p_179619_1_ instanceof EntityPlayerMP))
/*     */               {
/* 276 */                 return false;
/*     */               }
/*     */ 
/*     */               
/* 280 */               EntityPlayerMP var2x = (EntityPlayerMP)p_179619_1_;
/* 281 */               return (var2x.theItemInWorldManager.getGameType().getID() == var2);
/*     */             }
/*     */ 
/*     */             
/*     */             public boolean apply(Object p_apply_1_) {
/* 286 */               return func_179619_a((Entity)p_apply_1_);
/*     */             }
/*     */           });
/*     */     }
/*     */     
/* 291 */     return var1;
/*     */   }
/*     */ 
/*     */   
/*     */   private static List func_179659_d(Map p_179659_0_) {
/* 296 */     ArrayList<Predicate> var1 = Lists.newArrayList();
/* 297 */     String var2 = func_179651_b(p_179659_0_, "team");
/* 298 */     final boolean var3 = (var2 != null && var2.startsWith("!"));
/*     */     
/* 300 */     if (var3)
/*     */     {
/* 302 */       var2 = var2.substring(1);
/*     */     }
/*     */     
/* 305 */     if (var2 != null) {
/*     */       
/* 307 */       final String var2_f = var2;
/* 308 */       var1.add(new Predicate()
/*     */           {
/*     */             private static final String __OBFID = "CL_00002355";
/*     */             
/*     */             public boolean func_179621_a(Entity p_179621_1_) {
/* 313 */               if (!(p_179621_1_ instanceof EntityLivingBase))
/*     */               {
/* 315 */                 return false;
/*     */               }
/*     */ 
/*     */               
/* 319 */               EntityLivingBase var2x = (EntityLivingBase)p_179621_1_;
/* 320 */               Team var3x = var2x.getTeam();
/* 321 */               String var4 = (var3x == null) ? "" : var3x.getRegisteredName();
/* 322 */               return var4.equals(var2_f) ^ var3;
/*     */             }
/*     */ 
/*     */             
/*     */             public boolean apply(Object p_apply_1_) {
/* 327 */               return func_179621_a((Entity)p_apply_1_);
/*     */             }
/*     */           });
/*     */     } 
/*     */     
/* 332 */     return var1;
/*     */   }
/*     */ 
/*     */   
/*     */   private static List func_179657_e(Map p_179657_0_) {
/* 337 */     ArrayList<Predicate> var1 = Lists.newArrayList();
/* 338 */     final Map var2 = func_96560_a(p_179657_0_);
/*     */     
/* 340 */     if (var2 != null && var2.size() > 0)
/*     */     {
/* 342 */       var1.add(new Predicate() {
/*     */             public boolean func_179603_a(Entity p_179603_1_) {
/*     */               Map.Entry var4;
/*     */               boolean var6;
/*     */               int var10;
/* 347 */               Scoreboard var2x = MinecraftServer.getServer().worldServerForDimension(0).getScoreboard();
/* 348 */               Iterator<Map.Entry> var3 = var2.entrySet().iterator();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */               
/*     */               do {
/* 355 */                 if (!var3.hasNext())
/*     */                 {
/* 357 */                   return true;
/*     */                 }
/*     */                 
/* 360 */                 var4 = var3.next();
/* 361 */                 String var5 = (String)var4.getKey();
/* 362 */                 var6 = false;
/*     */                 
/* 364 */                 if (var5.endsWith("_min") && var5.length() > 4) {
/*     */                   
/* 366 */                   var6 = true;
/* 367 */                   var5 = var5.substring(0, var5.length() - 4);
/*     */                 } 
/*     */                 
/* 370 */                 ScoreObjective var7 = var2x.getObjective(var5);
/*     */                 
/* 372 */                 if (var7 == null)
/*     */                 {
/* 374 */                   return false;
/*     */                 }
/*     */                 
/* 377 */                 String var8 = (p_179603_1_ instanceof EntityPlayerMP) ? p_179603_1_.getName() : p_179603_1_.getUniqueID().toString();
/*     */                 
/* 379 */                 if (!var2x.func_178819_b(var8, var7))
/*     */                 {
/* 381 */                   return false;
/*     */                 }
/*     */                 
/* 384 */                 Score var9 = var2x.getValueFromObjective(var8, var7);
/* 385 */                 var10 = var9.getScorePoints();
/*     */                 
/* 387 */                 if (var10 < ((Integer)var4.getValue()).intValue() && var6)
/*     */                 {
/* 389 */                   return false;
/*     */                 }
/*     */               }
/* 392 */               while (var10 <= ((Integer)var4.getValue()).intValue() || var6);
/*     */               
/* 394 */               return false;
/*     */             }
/*     */             private static final String __OBFID = "CL_00002354";
/*     */             public boolean apply(Object p_apply_1_) {
/* 398 */               return func_179603_a((Entity)p_apply_1_);
/*     */             }
/*     */           });
/*     */     }
/*     */     
/* 403 */     return var1;
/*     */   }
/*     */ 
/*     */   
/*     */   private static List func_179647_f(Map p_179647_0_) {
/* 408 */     ArrayList<Predicate> var1 = Lists.newArrayList();
/* 409 */     String var2 = func_179651_b(p_179647_0_, "name");
/* 410 */     final boolean var3 = (var2 != null && var2.startsWith("!"));
/*     */     
/* 412 */     if (var3)
/*     */     {
/* 414 */       var2 = var2.substring(1);
/*     */     }
/*     */     
/* 417 */     if (var2 != null) {
/*     */       
/* 419 */       final String var2_f = var2;
/* 420 */       var1.add(new Predicate()
/*     */           {
/*     */             private static final String __OBFID = "CL_00002353";
/*     */             
/*     */             public boolean func_179600_a(Entity p_179600_1_) {
/* 425 */               return p_179600_1_.getName().equals(var2_f) ^ var3;
/*     */             }
/*     */             
/*     */             public boolean apply(Object p_apply_1_) {
/* 429 */               return func_179600_a((Entity)p_apply_1_);
/*     */             }
/*     */           });
/*     */     } 
/*     */     
/* 434 */     return var1;
/*     */   }
/*     */ 
/*     */   
/*     */   private static List func_180698_a(Map p_180698_0_, final BlockPos p_180698_1_) {
/* 439 */     ArrayList<Predicate> var2 = Lists.newArrayList();
/* 440 */     final int var3 = func_179653_a(p_180698_0_, "rm", -1);
/* 441 */     final int var4 = func_179653_a(p_180698_0_, "r", -1);
/*     */     
/* 443 */     if (p_180698_1_ != null && (var3 >= 0 || var4 >= 0)) {
/*     */       
/* 445 */       final int var5 = var3 * var3;
/* 446 */       final int var6 = var4 * var4;
/* 447 */       var2.add(new Predicate()
/*     */           {
/*     */             private static final String __OBFID = "CL_00002352";
/*     */             
/*     */             public boolean func_179594_a(Entity p_179594_1_) {
/* 452 */               int var2 = (int)p_179594_1_.func_174831_c(p_180698_1_);
/* 453 */               return ((var3 < 0 || var2 >= var5) && (var4 < 0 || var2 <= var6));
/*     */             }
/*     */             
/*     */             public boolean apply(Object p_apply_1_) {
/* 457 */               return func_179594_a((Entity)p_apply_1_);
/*     */             }
/*     */           });
/*     */     } 
/*     */     
/* 462 */     return var2;
/*     */   }
/*     */ 
/*     */   
/*     */   private static List func_179662_g(Map p_179662_0_) {
/* 467 */     ArrayList<Predicate> var1 = Lists.newArrayList();
/*     */     
/* 469 */     if (p_179662_0_.containsKey("rym") || p_179662_0_.containsKey("ry")) {
/*     */       
/* 471 */       final int var2 = func_179650_a(func_179653_a(p_179662_0_, "rym", 0));
/* 472 */       final int var3 = func_179650_a(func_179653_a(p_179662_0_, "ry", 359));
/* 473 */       var1.add(new Predicate()
/*     */           {
/*     */             private static final String __OBFID = "CL_00002351";
/*     */             
/*     */             public boolean func_179591_a(Entity p_179591_1_) {
/* 478 */               int var2x = PlayerSelector.func_179650_a((int)Math.floor(p_179591_1_.rotationYaw));
/* 479 */               return (var2 > var3) ? (!(var2x < var2 && var2x > var3)) : ((var2x >= var2 && var2x <= var3));
/*     */             }
/*     */             
/*     */             public boolean apply(Object p_apply_1_) {
/* 483 */               return func_179591_a((Entity)p_apply_1_);
/*     */             }
/*     */           });
/*     */     } 
/*     */     
/* 488 */     if (p_179662_0_.containsKey("rxm") || p_179662_0_.containsKey("rx")) {
/*     */       
/* 490 */       final int var2 = func_179650_a(func_179653_a(p_179662_0_, "rxm", 0));
/* 491 */       final int var3 = func_179650_a(func_179653_a(p_179662_0_, "rx", 359));
/* 492 */       var1.add(new Predicate()
/*     */           {
/*     */             private static final String __OBFID = "CL_00002361";
/*     */             
/*     */             public boolean func_179616_a(Entity p_179616_1_) {
/* 497 */               int var2x = PlayerSelector.func_179650_a((int)Math.floor(p_179616_1_.rotationPitch));
/* 498 */               return (var2 > var3) ? (!(var2x < var2 && var2x > var3)) : ((var2x >= var2 && var2x <= var3));
/*     */             }
/*     */             
/*     */             public boolean apply(Object p_apply_1_) {
/* 502 */               return func_179616_a((Entity)p_apply_1_);
/*     */             }
/*     */           });
/*     */     } 
/*     */     
/* 507 */     return var1;
/*     */   }
/*     */ 
/*     */   
/*     */   private static List func_179660_a(Map p_179660_0_, Class p_179660_1_, List p_179660_2_, String p_179660_3_, World worldIn, BlockPos p_179660_5_) {
/* 512 */     ArrayList var6 = Lists.newArrayList();
/* 513 */     String var7 = func_179651_b(p_179660_0_, "type");
/* 514 */     var7 = (var7 != null && var7.startsWith("!")) ? var7.substring(1) : var7;
/* 515 */     boolean var8 = !p_179660_3_.equals("e");
/* 516 */     boolean var9 = (p_179660_3_.equals("r") && var7 != null);
/* 517 */     int var10 = func_179653_a(p_179660_0_, "dx", 0);
/* 518 */     int var11 = func_179653_a(p_179660_0_, "dy", 0);
/* 519 */     int var12 = func_179653_a(p_179660_0_, "dz", 0);
/* 520 */     int var13 = func_179653_a(p_179660_0_, "r", -1);
/* 521 */     Predicate var14 = Predicates.and(p_179660_2_);
/* 522 */     Predicate var15 = Predicates.and(IEntitySelector.selectAnything, var14);
/*     */     
/* 524 */     if (p_179660_5_ != null) {
/*     */       
/* 526 */       int var16 = worldIn.playerEntities.size();
/* 527 */       int var17 = worldIn.loadedEntityList.size();
/* 528 */       boolean var18 = (var16 < var17 / 16);
/*     */ 
/*     */       
/* 531 */       if (!p_179660_0_.containsKey("dx") && !p_179660_0_.containsKey("dy") && !p_179660_0_.containsKey("dz")) {
/*     */         
/* 533 */         if (var13 >= 0) {
/*     */           
/* 535 */           final AxisAlignedBB var19 = new AxisAlignedBB((p_179660_5_.getX() - var13), (p_179660_5_.getY() - var13), (p_179660_5_.getZ() - var13), (p_179660_5_.getX() + var13 + 1), (p_179660_5_.getY() + var13 + 1), (p_179660_5_.getZ() + var13 + 1));
/*     */           
/* 537 */           if (var8 && var18 && !var9)
/*     */           {
/* 539 */             var6.addAll(worldIn.func_175661_b(p_179660_1_, var15));
/*     */           }
/*     */           else
/*     */           {
/* 543 */             var6.addAll(worldIn.func_175647_a(p_179660_1_, var19, var15));
/*     */           }
/*     */         
/* 546 */         } else if (p_179660_3_.equals("a")) {
/*     */           
/* 548 */           var6.addAll(worldIn.func_175661_b(p_179660_1_, var14));
/*     */         }
/* 550 */         else if (!p_179660_3_.equals("p") && (!p_179660_3_.equals("r") || var9)) {
/*     */           
/* 552 */           var6.addAll(worldIn.func_175644_a(p_179660_1_, var15));
/*     */         }
/*     */         else {
/*     */           
/* 556 */           var6.addAll(worldIn.func_175661_b(p_179660_1_, var15));
/*     */         }
/*     */       
/*     */       } else {
/*     */         
/* 561 */         final AxisAlignedBB var19 = func_179661_a(p_179660_5_, var10, var11, var12);
/*     */         
/* 563 */         if (var8 && var18 && !var9)
/*     */         {
/* 565 */           Predicate var20 = new Predicate()
/*     */             {
/*     */               private static final String __OBFID = "CL_00002360";
/*     */               
/*     */               public boolean func_179609_a(Entity p_179609_1_) {
/* 570 */                 return (p_179609_1_.posX >= var19.minX && p_179609_1_.posY >= var19.minY && p_179609_1_.posZ >= var19.minZ) ? ((p_179609_1_.posX < var19.maxX && p_179609_1_.posY < var19.maxY && p_179609_1_.posZ < var19.maxZ)) : false;
/*     */               }
/*     */               
/*     */               public boolean apply(Object p_apply_1_) {
/* 574 */                 return func_179609_a((Entity)p_apply_1_);
/*     */               }
/*     */             };
/* 577 */           var6.addAll(worldIn.func_175661_b(p_179660_1_, Predicates.and(var15, var20)));
/*     */         }
/*     */         else
/*     */         {
/* 581 */           var6.addAll(worldIn.func_175647_a(p_179660_1_, var19, var15));
/*     */         }
/*     */       
/*     */       } 
/* 585 */     } else if (p_179660_3_.equals("a")) {
/*     */       
/* 587 */       var6.addAll(worldIn.func_175661_b(p_179660_1_, var14));
/*     */     }
/* 589 */     else if (!p_179660_3_.equals("p") && (!p_179660_3_.equals("r") || var9)) {
/*     */       
/* 591 */       var6.addAll(worldIn.func_175644_a(p_179660_1_, var15));
/*     */     }
/*     */     else {
/*     */       
/* 595 */       var6.addAll(worldIn.func_175661_b(p_179660_1_, var15));
/*     */     } 
/*     */     
/* 598 */     return var6;
/*     */   }
/*     */ 
/*     */   
/*     */   private static List func_179658_a(List<?> p_179658_0_, Map p_179658_1_, ICommandSender p_179658_2_, Class p_179658_3_, String p_179658_4_, final BlockPos p_179658_5_) {
/* 603 */     int var6 = func_179653_a(p_179658_1_, "c", (!p_179658_4_.equals("a") && !p_179658_4_.equals("e")) ? 1 : 0);
/*     */     
/* 605 */     if (!p_179658_4_.equals("p") && !p_179658_4_.equals("a") && !p_179658_4_.equals("e")) {
/*     */       
/* 607 */       if (p_179658_4_.equals("r"))
/*     */       {
/* 609 */         Collections.shuffle(p_179658_0_);
/*     */       }
/*     */     }
/* 612 */     else if (p_179658_5_ != null) {
/*     */       
/* 614 */       Collections.sort(p_179658_0_, new Comparator()
/*     */           {
/*     */             private static final String __OBFID = "CL_00002359";
/*     */             
/*     */             public int func_179611_a(Entity p_179611_1_, Entity p_179611_2_) {
/* 619 */               return ComparisonChain.start().compare(p_179611_1_.getDistanceSq(p_179658_5_), p_179611_2_.getDistanceSq(p_179658_5_)).result();
/*     */             }
/*     */             
/*     */             public int compare(Object p_compare_1_, Object p_compare_2_) {
/* 623 */               return func_179611_a((Entity)p_compare_1_, (Entity)p_compare_2_);
/*     */             }
/*     */           });
/*     */     } 
/*     */     
/* 628 */     Entity var7 = p_179658_2_.getCommandSenderEntity();
/*     */     
/* 630 */     if (var7 != null && p_179658_3_.isAssignableFrom(var7.getClass()) && var6 == 1 && p_179658_0_.contains(var7) && !"r".equals(p_179658_4_))
/*     */     {
/* 632 */       p_179658_0_ = Lists.newArrayList((Object[])new Entity[] { var7 });
/*     */     }
/*     */     
/* 635 */     if (var6 != 0) {
/*     */       
/* 637 */       if (var6 < 0)
/*     */       {
/* 639 */         Collections.reverse(p_179658_0_);
/*     */       }
/*     */       
/* 642 */       p_179658_0_ = p_179658_0_.subList(0, Math.min(Math.abs(var6), p_179658_0_.size()));
/*     */     } 
/*     */     
/* 645 */     return p_179658_0_;
/*     */   }
/*     */ 
/*     */   
/*     */   private static AxisAlignedBB func_179661_a(BlockPos p_179661_0_, int p_179661_1_, int p_179661_2_, int p_179661_3_) {
/* 650 */     boolean var4 = (p_179661_1_ < 0);
/* 651 */     boolean var5 = (p_179661_2_ < 0);
/* 652 */     boolean var6 = (p_179661_3_ < 0);
/* 653 */     int var7 = p_179661_0_.getX() + (var4 ? p_179661_1_ : 0);
/* 654 */     int var8 = p_179661_0_.getY() + (var5 ? p_179661_2_ : 0);
/* 655 */     int var9 = p_179661_0_.getZ() + (var6 ? p_179661_3_ : 0);
/* 656 */     int var10 = p_179661_0_.getX() + (var4 ? 0 : p_179661_1_) + 1;
/* 657 */     int var11 = p_179661_0_.getY() + (var5 ? 0 : p_179661_2_) + 1;
/* 658 */     int var12 = p_179661_0_.getZ() + (var6 ? 0 : p_179661_3_) + 1;
/* 659 */     return new AxisAlignedBB(var7, var8, var9, var10, var11, var12);
/*     */   }
/*     */ 
/*     */   
/*     */   public static int func_179650_a(int p_179650_0_) {
/* 664 */     p_179650_0_ %= 360;
/*     */     
/* 666 */     if (p_179650_0_ >= 160)
/*     */     {
/* 668 */       p_179650_0_ -= 360;
/*     */     }
/*     */     
/* 671 */     if (p_179650_0_ < 0)
/*     */     {
/* 673 */       p_179650_0_ += 360;
/*     */     }
/*     */     
/* 676 */     return p_179650_0_;
/*     */   }
/*     */ 
/*     */   
/*     */   private static BlockPos func_179664_b(Map p_179664_0_, BlockPos p_179664_1_) {
/* 681 */     return new BlockPos(func_179653_a(p_179664_0_, "x", p_179664_1_.getX()), func_179653_a(p_179664_0_, "y", p_179664_1_.getY()), func_179653_a(p_179664_0_, "z", p_179664_1_.getZ()));
/*     */   }
/*     */   
/*     */   private static boolean func_179665_h(Map p_179665_0_) {
/*     */     String var2;
/* 686 */     Iterator<String> var1 = field_179666_d.iterator();
/*     */ 
/*     */ 
/*     */     
/*     */     do {
/* 691 */       if (!var1.hasNext())
/*     */       {
/* 693 */         return false;
/*     */       }
/*     */       
/* 696 */       var2 = var1.next();
/*     */     }
/* 698 */     while (!p_179665_0_.containsKey(var2));
/*     */     
/* 700 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   private static int func_179653_a(Map p_179653_0_, String p_179653_1_, int p_179653_2_) {
/* 705 */     return p_179653_0_.containsKey(p_179653_1_) ? MathHelper.parseIntWithDefault((String)p_179653_0_.get(p_179653_1_), p_179653_2_) : p_179653_2_;
/*     */   }
/*     */ 
/*     */   
/*     */   private static String func_179651_b(Map p_179651_0_, String p_179651_1_) {
/* 710 */     return (String)p_179651_0_.get(p_179651_1_);
/*     */   }
/*     */ 
/*     */   
/*     */   public static Map func_96560_a(Map p_96560_0_) {
/* 715 */     HashMap<String, Integer> var1 = Maps.newHashMap();
/* 716 */     Iterator<String> var2 = p_96560_0_.keySet().iterator();
/*     */     
/* 718 */     while (var2.hasNext()) {
/*     */       
/* 720 */       String var3 = var2.next();
/*     */       
/* 722 */       if (var3.startsWith("score_") && var3.length() > "score_".length())
/*     */       {
/* 724 */         var1.put(var3.substring("score_".length()), Integer.valueOf(MathHelper.parseIntWithDefault((String)p_96560_0_.get(var3), 1)));
/*     */       }
/*     */     } 
/*     */     
/* 728 */     return var1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean matchesMultiplePlayers(String p_82377_0_) {
/* 736 */     Matcher var1 = tokenPattern.matcher(p_82377_0_);
/*     */     
/* 738 */     if (!var1.matches())
/*     */     {
/* 740 */       return false;
/*     */     }
/*     */ 
/*     */     
/* 744 */     Map var2 = getArgumentMap(var1.group(2));
/* 745 */     String var3 = var1.group(1);
/* 746 */     int var4 = (!"a".equals(var3) && !"e".equals(var3)) ? 1 : 0;
/* 747 */     return (func_179653_a(var2, "c", var4) != 1);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean hasArguments(String p_82378_0_) {
/* 756 */     return tokenPattern.matcher(p_82378_0_).matches();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static Map getArgumentMap(String p_82381_0_) {
/* 764 */     HashMap<String, String> var1 = Maps.newHashMap();
/*     */     
/* 766 */     if (p_82381_0_ == null)
/*     */     {
/* 768 */       return var1;
/*     */     }
/*     */ 
/*     */     
/* 772 */     int var2 = 0;
/* 773 */     int var3 = -1;
/*     */     
/* 775 */     for (Matcher var4 = intListPattern.matcher(p_82381_0_); var4.find(); var3 = var4.end()) {
/*     */       
/* 777 */       String var5 = null;
/*     */       
/* 779 */       switch (var2++) {
/*     */         
/*     */         case 0:
/* 782 */           var5 = "x";
/*     */           break;
/*     */         
/*     */         case 1:
/* 786 */           var5 = "y";
/*     */           break;
/*     */         
/*     */         case 2:
/* 790 */           var5 = "z";
/*     */           break;
/*     */         
/*     */         case 3:
/* 794 */           var5 = "r";
/*     */           break;
/*     */       } 
/* 797 */       if (var5 != null && var4.group(1).length() > 0)
/*     */       {
/* 799 */         var1.put(var5, var4.group(1));
/*     */       }
/*     */     } 
/*     */     
/* 803 */     if (var3 < p_82381_0_.length()) {
/*     */       
/* 805 */       Matcher var6 = keyValueListPattern.matcher((var3 == -1) ? p_82381_0_ : p_82381_0_.substring(var3));
/*     */       
/* 807 */       while (var6.find())
/*     */       {
/* 809 */         var1.put(var6.group(1), var6.group(2));
/*     */       }
/*     */     } 
/*     */     
/* 813 */     return var1;
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\command\PlayerSelector.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */