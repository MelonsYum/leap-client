/*     */ package net.minecraft.command;
/*     */ 
/*     */ import com.google.common.base.Functions;
/*     */ import com.google.common.collect.Iterables;
/*     */ import com.google.common.collect.Lists;
/*     */ import com.google.common.primitives.Doubles;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.Collection;
/*     */ import java.util.Collections;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.UUID;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.player.EntityPlayerMP;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.server.MinecraftServer;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.ChatComponentText;
/*     */ import net.minecraft.util.IChatComponent;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class CommandBase
/*     */   implements ICommand
/*     */ {
/*     */   private static IAdminCommand theAdmin;
/*     */   private static final String __OBFID = "CL_00001739";
/*     */   
/*     */   public int getRequiredPermissionLevel() {
/*  34 */     return 4;
/*     */   }
/*     */ 
/*     */   
/*     */   public List getCommandAliases() {
/*  39 */     return Collections.emptyList();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canCommandSenderUseCommand(ICommandSender sender) {
/*  47 */     return sender.canCommandSenderUseCommand(getRequiredPermissionLevel(), getCommandName());
/*     */   }
/*     */ 
/*     */   
/*     */   public List addTabCompletionOptions(ICommandSender sender, String[] args, BlockPos pos) {
/*  52 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static int parseInt(String input) throws NumberInvalidException {
/*     */     try {
/*  59 */       return Integer.parseInt(input);
/*     */     }
/*  61 */     catch (NumberFormatException var2) {
/*     */       
/*  63 */       throw new NumberInvalidException("commands.generic.num.invalid", new Object[] { input });
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static int parseInt(String input, int min) throws NumberInvalidException {
/*  69 */     return parseInt(input, min, 2147483647);
/*     */   }
/*     */ 
/*     */   
/*     */   public static int parseInt(String input, int min, int max) throws NumberInvalidException {
/*  74 */     int var3 = parseInt(input);
/*     */     
/*  76 */     if (var3 < min)
/*     */     {
/*  78 */       throw new NumberInvalidException("commands.generic.num.tooSmall", new Object[] { Integer.valueOf(var3), Integer.valueOf(min) });
/*     */     }
/*  80 */     if (var3 > max)
/*     */     {
/*  82 */       throw new NumberInvalidException("commands.generic.num.tooBig", new Object[] { Integer.valueOf(var3), Integer.valueOf(max) });
/*     */     }
/*     */ 
/*     */     
/*  86 */     return var3;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static long parseLong(String input) throws NumberInvalidException {
/*     */     try {
/*  94 */       return Long.parseLong(input);
/*     */     }
/*  96 */     catch (NumberFormatException var2) {
/*     */       
/*  98 */       throw new NumberInvalidException("commands.generic.num.invalid", new Object[] { input });
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static long parseLong(String input, long min, long max) throws NumberInvalidException {
/* 104 */     long var5 = parseLong(input);
/*     */     
/* 106 */     if (var5 < min)
/*     */     {
/* 108 */       throw new NumberInvalidException("commands.generic.num.tooSmall", new Object[] { Long.valueOf(var5), Long.valueOf(min) });
/*     */     }
/* 110 */     if (var5 > max)
/*     */     {
/* 112 */       throw new NumberInvalidException("commands.generic.num.tooBig", new Object[] { Long.valueOf(var5), Long.valueOf(max) });
/*     */     }
/*     */ 
/*     */     
/* 116 */     return var5;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static BlockPos func_175757_a(ICommandSender sender, String[] args, int p_175757_2_, boolean p_175757_3_) throws NumberInvalidException {
/* 122 */     BlockPos var4 = sender.getPosition();
/* 123 */     return new BlockPos(func_175769_b(var4.getX(), args[p_175757_2_], -30000000, 30000000, p_175757_3_), func_175769_b(var4.getY(), args[p_175757_2_ + 1], 0, 256, false), func_175769_b(var4.getZ(), args[p_175757_2_ + 2], -30000000, 30000000, p_175757_3_));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static double parseDouble(String input) throws NumberInvalidException {
/*     */     try {
/* 130 */       double var1 = Double.parseDouble(input);
/*     */       
/* 132 */       if (!Doubles.isFinite(var1))
/*     */       {
/* 134 */         throw new NumberInvalidException("commands.generic.num.invalid", new Object[] { input });
/*     */       }
/*     */ 
/*     */       
/* 138 */       return var1;
/*     */     
/*     */     }
/* 141 */     catch (NumberFormatException var3) {
/*     */       
/* 143 */       throw new NumberInvalidException("commands.generic.num.invalid", new Object[] { input });
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static double parseDouble(String input, double min) throws NumberInvalidException {
/* 149 */     return parseDouble(input, min, Double.MAX_VALUE);
/*     */   }
/*     */ 
/*     */   
/*     */   public static double parseDouble(String input, double min, double max) throws NumberInvalidException {
/* 154 */     double var5 = parseDouble(input);
/*     */     
/* 156 */     if (var5 < min)
/*     */     {
/* 158 */       throw new NumberInvalidException("commands.generic.double.tooSmall", new Object[] { Double.valueOf(var5), Double.valueOf(min) });
/*     */     }
/* 160 */     if (var5 > max)
/*     */     {
/* 162 */       throw new NumberInvalidException("commands.generic.double.tooBig", new Object[] { Double.valueOf(var5), Double.valueOf(max) });
/*     */     }
/*     */ 
/*     */     
/* 166 */     return var5;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean parseBoolean(String input) throws CommandException {
/* 172 */     if (!input.equals("true") && !input.equals("1")) {
/*     */       
/* 174 */       if (!input.equals("false") && !input.equals("0"))
/*     */       {
/* 176 */         throw new CommandException("commands.generic.boolean.invalid", new Object[] { input });
/*     */       }
/*     */ 
/*     */       
/* 180 */       return false;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 185 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static EntityPlayerMP getCommandSenderAsPlayer(ICommandSender sender) throws PlayerNotFoundException {
/* 194 */     if (sender instanceof EntityPlayerMP)
/*     */     {
/* 196 */       return (EntityPlayerMP)sender;
/*     */     }
/*     */ 
/*     */     
/* 200 */     throw new PlayerNotFoundException("You must specify which player you wish to perform this action on.", new Object[0]);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static EntityPlayerMP getPlayer(ICommandSender sender, String username) throws PlayerNotFoundException {
/* 206 */     EntityPlayerMP var2 = PlayerSelector.matchOnePlayer(sender, username);
/*     */     
/* 208 */     if (var2 == null) {
/*     */       
/*     */       try {
/*     */         
/* 212 */         var2 = MinecraftServer.getServer().getConfigurationManager().func_177451_a(UUID.fromString(username));
/*     */       }
/* 214 */       catch (IllegalArgumentException illegalArgumentException) {}
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 220 */     if (var2 == null)
/*     */     {
/* 222 */       var2 = MinecraftServer.getServer().getConfigurationManager().getPlayerByUsername(username);
/*     */     }
/*     */     
/* 225 */     if (var2 == null)
/*     */     {
/* 227 */       throw new PlayerNotFoundException();
/*     */     }
/*     */ 
/*     */     
/* 231 */     return var2;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static Entity func_175768_b(ICommandSender p_175768_0_, String p_175768_1_) throws EntityNotFoundException {
/* 237 */     return func_175759_a(p_175768_0_, p_175768_1_, Entity.class);
/*     */   }
/*     */ 
/*     */   
/*     */   public static Entity func_175759_a(ICommandSender p_175759_0_, String p_175759_1_, Class p_175759_2_) throws EntityNotFoundException {
/* 242 */     Object var3 = PlayerSelector.func_179652_a(p_175759_0_, p_175759_1_, p_175759_2_);
/* 243 */     MinecraftServer var4 = MinecraftServer.getServer();
/*     */     
/* 245 */     if (var3 == null)
/*     */     {
/* 247 */       var3 = var4.getConfigurationManager().getPlayerByUsername(p_175759_1_);
/*     */     }
/*     */     
/* 250 */     if (var3 == null) {
/*     */       
/*     */       try {
/*     */         
/* 254 */         UUID var5 = UUID.fromString(p_175759_1_);
/* 255 */         var3 = var4.getEntityFromUuid(var5);
/*     */         
/* 257 */         if (var3 == null)
/*     */         {
/* 259 */           var3 = var4.getConfigurationManager().func_177451_a(var5);
/*     */         }
/*     */       }
/* 262 */       catch (IllegalArgumentException var6) {
/*     */         
/* 264 */         throw new EntityNotFoundException("commands.generic.entity.invalidUuid", new Object[0]);
/*     */       } 
/*     */     }
/*     */     
/* 268 */     if (var3 != null && p_175759_2_.isAssignableFrom(var3.getClass()))
/*     */     {
/* 270 */       return (Entity)var3;
/*     */     }
/*     */ 
/*     */     
/* 274 */     throw new EntityNotFoundException();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static List func_175763_c(ICommandSender p_175763_0_, String p_175763_1_) throws EntityNotFoundException {
/* 280 */     return PlayerSelector.hasArguments(p_175763_1_) ? PlayerSelector.func_179656_b(p_175763_0_, p_175763_1_, Entity.class) : Lists.newArrayList((Object[])new Entity[] { func_175768_b(p_175763_0_, p_175763_1_) });
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static String getPlayerName(ICommandSender sender, String query) throws PlayerNotFoundException {
/*     */     try {
/* 287 */       return getPlayer(sender, query).getName();
/*     */     }
/* 289 */     catch (PlayerNotFoundException var3) {
/*     */       
/* 291 */       if (PlayerSelector.hasArguments(query))
/*     */       {
/* 293 */         throw var3;
/*     */       }
/*     */ 
/*     */       
/* 297 */       return query;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String func_175758_e(ICommandSender p_175758_0_, String p_175758_1_) throws EntityNotFoundException {
/*     */     try {
/* 306 */       return getPlayer(p_175758_0_, p_175758_1_).getName();
/*     */     }
/* 308 */     catch (PlayerNotFoundException var5) {
/*     */ 
/*     */       
/*     */       try {
/* 312 */         return func_175768_b(p_175758_0_, p_175758_1_).getUniqueID().toString();
/*     */       }
/* 314 */       catch (EntityNotFoundException var4) {
/*     */         
/* 316 */         if (PlayerSelector.hasArguments(p_175758_1_))
/*     */         {
/* 318 */           throw var4;
/*     */         }
/*     */ 
/*     */         
/* 322 */         return p_175758_1_;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static IChatComponent getChatComponentFromNthArg(ICommandSender sender, String[] args, int p_147178_2_) throws CommandException {
/* 330 */     return getChatComponentFromNthArg(sender, args, p_147178_2_, false);
/*     */   }
/*     */ 
/*     */   
/*     */   public static IChatComponent getChatComponentFromNthArg(ICommandSender sender, String[] args, int index, boolean p_147176_3_) throws PlayerNotFoundException {
/* 335 */     ChatComponentText var4 = new ChatComponentText("");
/*     */     
/* 337 */     for (int var5 = index; var5 < args.length; var5++) {
/*     */       
/* 339 */       if (var5 > index)
/*     */       {
/* 341 */         var4.appendText(" ");
/*     */       }
/*     */       
/* 344 */       Object var6 = new ChatComponentText(args[var5]);
/*     */       
/* 346 */       if (p_147176_3_) {
/*     */         
/* 348 */         IChatComponent var7 = PlayerSelector.func_150869_b(sender, args[var5]);
/*     */         
/* 350 */         if (var7 == null) {
/*     */           
/* 352 */           if (PlayerSelector.hasArguments(args[var5]))
/*     */           {
/* 354 */             throw new PlayerNotFoundException();
/*     */           }
/*     */         }
/*     */         else {
/*     */           
/* 359 */           var6 = var7;
/*     */         } 
/*     */       } 
/*     */       
/* 363 */       var4.appendSibling((IChatComponent)var6);
/*     */     } 
/*     */     
/* 366 */     return (IChatComponent)var4;
/*     */   }
/*     */ 
/*     */   
/*     */   public static String func_180529_a(String[] p_180529_0_, int p_180529_1_) {
/* 371 */     StringBuilder var2 = new StringBuilder();
/*     */     
/* 373 */     for (int var3 = p_180529_1_; var3 < p_180529_0_.length; var3++) {
/*     */       
/* 375 */       if (var3 > p_180529_1_)
/*     */       {
/* 377 */         var2.append(" ");
/*     */       }
/*     */       
/* 380 */       String var4 = p_180529_0_[var3];
/* 381 */       var2.append(var4);
/*     */     } 
/*     */     
/* 384 */     return var2.toString();
/*     */   }
/*     */ 
/*     */   
/*     */   public static CoordinateArg func_175770_a(double p_175770_0_, String p_175770_2_, boolean p_175770_3_) throws NumberInvalidException {
/* 389 */     return func_175767_a(p_175770_0_, p_175770_2_, -30000000, 30000000, p_175770_3_);
/*     */   }
/*     */ 
/*     */   
/*     */   public static CoordinateArg func_175767_a(double p_175767_0_, String p_175767_2_, int p_175767_3_, int p_175767_4_, boolean p_175767_5_) throws NumberInvalidException {
/* 394 */     boolean var6 = p_175767_2_.startsWith("~");
/*     */     
/* 396 */     if (var6 && Double.isNaN(p_175767_0_))
/*     */     {
/* 398 */       throw new NumberInvalidException("commands.generic.num.invalid", new Object[] { Double.valueOf(p_175767_0_) });
/*     */     }
/*     */ 
/*     */     
/* 402 */     double var7 = 0.0D;
/*     */     
/* 404 */     if (!var6 || p_175767_2_.length() > 1) {
/*     */       
/* 406 */       boolean var9 = p_175767_2_.contains(".");
/*     */       
/* 408 */       if (var6)
/*     */       {
/* 410 */         p_175767_2_ = p_175767_2_.substring(1);
/*     */       }
/*     */       
/* 413 */       var7 += parseDouble(p_175767_2_);
/*     */       
/* 415 */       if (!var9 && !var6 && p_175767_5_)
/*     */       {
/* 417 */         var7 += 0.5D;
/*     */       }
/*     */     } 
/*     */     
/* 421 */     if (p_175767_3_ != 0 || p_175767_4_ != 0) {
/*     */       
/* 423 */       if (var7 < p_175767_3_)
/*     */       {
/* 425 */         throw new NumberInvalidException("commands.generic.double.tooSmall", new Object[] { Double.valueOf(var7), Integer.valueOf(p_175767_3_) });
/*     */       }
/*     */       
/* 428 */       if (var7 > p_175767_4_)
/*     */       {
/* 430 */         throw new NumberInvalidException("commands.generic.double.tooBig", new Object[] { Double.valueOf(var7), Integer.valueOf(p_175767_4_) });
/*     */       }
/*     */     } 
/*     */     
/* 434 */     return new CoordinateArg(var7 + (var6 ? p_175767_0_ : 0.0D), var7, var6);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static double func_175761_b(double p_175761_0_, String p_175761_2_, boolean p_175761_3_) throws NumberInvalidException {
/* 440 */     return func_175769_b(p_175761_0_, p_175761_2_, -30000000, 30000000, p_175761_3_);
/*     */   }
/*     */ 
/*     */   
/*     */   public static double func_175769_b(double base, String input, int min, int max, boolean centerBlock) throws NumberInvalidException {
/* 445 */     boolean var6 = input.startsWith("~");
/*     */     
/* 447 */     if (var6 && Double.isNaN(base))
/*     */     {
/* 449 */       throw new NumberInvalidException("commands.generic.num.invalid", new Object[] { Double.valueOf(base) });
/*     */     }
/*     */ 
/*     */     
/* 453 */     double var7 = var6 ? base : 0.0D;
/*     */     
/* 455 */     if (!var6 || input.length() > 1) {
/*     */       
/* 457 */       boolean var9 = input.contains(".");
/*     */       
/* 459 */       if (var6)
/*     */       {
/* 461 */         input = input.substring(1);
/*     */       }
/*     */       
/* 464 */       var7 += parseDouble(input);
/*     */       
/* 466 */       if (!var9 && !var6 && centerBlock)
/*     */       {
/* 468 */         var7 += 0.5D;
/*     */       }
/*     */     } 
/*     */     
/* 472 */     if (min != 0 || max != 0) {
/*     */       
/* 474 */       if (var7 < min)
/*     */       {
/* 476 */         throw new NumberInvalidException("commands.generic.double.tooSmall", new Object[] { Double.valueOf(var7), Integer.valueOf(min) });
/*     */       }
/*     */       
/* 479 */       if (var7 > max)
/*     */       {
/* 481 */         throw new NumberInvalidException("commands.generic.double.tooBig", new Object[] { Double.valueOf(var7), Integer.valueOf(max) });
/*     */       }
/*     */     } 
/*     */     
/* 485 */     return var7;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Item getItemByText(ICommandSender sender, String id) throws NumberInvalidException {
/* 496 */     ResourceLocation var2 = new ResourceLocation(id);
/* 497 */     Item var3 = (Item)Item.itemRegistry.getObject(var2);
/*     */     
/* 499 */     if (var3 == null)
/*     */     {
/* 501 */       throw new NumberInvalidException("commands.give.notFound", new Object[] { var2 });
/*     */     }
/*     */ 
/*     */     
/* 505 */     return var3;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Block getBlockByText(ICommandSender sender, String id) throws NumberInvalidException {
/* 516 */     ResourceLocation var2 = new ResourceLocation(id);
/*     */     
/* 518 */     if (!Block.blockRegistry.containsKey(var2))
/*     */     {
/* 520 */       throw new NumberInvalidException("commands.give.notFound", new Object[] { var2 });
/*     */     }
/*     */ 
/*     */     
/* 524 */     Block var3 = (Block)Block.blockRegistry.getObject(var2);
/*     */     
/* 526 */     if (var3 == null)
/*     */     {
/* 528 */       throw new NumberInvalidException("commands.give.notFound", new Object[] { var2 });
/*     */     }
/*     */ 
/*     */     
/* 532 */     return var3;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String joinNiceString(Object[] elements) {
/* 543 */     StringBuilder var1 = new StringBuilder();
/*     */     
/* 545 */     for (int var2 = 0; var2 < elements.length; var2++) {
/*     */       
/* 547 */       String var3 = elements[var2].toString();
/*     */       
/* 549 */       if (var2 > 0)
/*     */       {
/* 551 */         if (var2 == elements.length - 1) {
/*     */           
/* 553 */           var1.append(" and ");
/*     */         }
/*     */         else {
/*     */           
/* 557 */           var1.append(", ");
/*     */         } 
/*     */       }
/*     */       
/* 561 */       var1.append(var3);
/*     */     } 
/*     */     
/* 564 */     return var1.toString();
/*     */   }
/*     */ 
/*     */   
/*     */   public static IChatComponent join(List<IChatComponent> components) {
/* 569 */     ChatComponentText var1 = new ChatComponentText("");
/*     */     
/* 571 */     for (int var2 = 0; var2 < components.size(); var2++) {
/*     */       
/* 573 */       if (var2 > 0)
/*     */       {
/* 575 */         if (var2 == components.size() - 1) {
/*     */           
/* 577 */           var1.appendText(" and ");
/*     */         }
/* 579 */         else if (var2 > 0) {
/*     */           
/* 581 */           var1.appendText(", ");
/*     */         } 
/*     */       }
/*     */       
/* 585 */       var1.appendSibling(components.get(var2));
/*     */     } 
/*     */     
/* 588 */     return (IChatComponent)var1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String joinNiceStringFromCollection(Collection strings) {
/* 598 */     return joinNiceString(strings.toArray((Object[])new String[strings.size()]));
/*     */   }
/*     */   
/*     */   public static List func_175771_a(String[] p_175771_0_, int p_175771_1_, BlockPos p_175771_2_) {
/*     */     String var3;
/* 603 */     if (p_175771_2_ == null)
/*     */     {
/* 605 */       return null;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 611 */     if (p_175771_0_.length - 1 == p_175771_1_) {
/*     */       
/* 613 */       var3 = Integer.toString(p_175771_2_.getX());
/*     */     }
/* 615 */     else if (p_175771_0_.length - 1 == p_175771_1_ + 1) {
/*     */       
/* 617 */       var3 = Integer.toString(p_175771_2_.getY());
/*     */     }
/*     */     else {
/*     */       
/* 621 */       if (p_175771_0_.length - 1 != p_175771_1_ + 2)
/*     */       {
/* 623 */         return null;
/*     */       }
/*     */       
/* 626 */       var3 = Integer.toString(p_175771_2_.getZ());
/*     */     } 
/*     */     
/* 629 */     return Lists.newArrayList((Object[])new String[] { var3 });
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean doesStringStartWith(String original, String region) {
/* 638 */     return region.regionMatches(true, 0, original, 0, original.length());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static List getListOfStringsMatchingLastWord(String[] args, String... possibilities) {
/* 647 */     return func_175762_a(args, Arrays.asList(possibilities));
/*     */   }
/*     */ 
/*     */   
/*     */   public static List func_175762_a(String[] p_175762_0_, Collection<String> p_175762_1_) {
/* 652 */     String var2 = p_175762_0_[p_175762_0_.length - 1];
/* 653 */     ArrayList<String> var3 = Lists.newArrayList();
/*     */     
/* 655 */     if (!p_175762_1_.isEmpty()) {
/*     */       
/* 657 */       Iterator<String> var4 = Iterables.transform(p_175762_1_, Functions.toStringFunction()).iterator();
/*     */       
/* 659 */       while (var4.hasNext()) {
/*     */         
/* 661 */         String var5 = var4.next();
/*     */         
/* 663 */         if (doesStringStartWith(var2, var5))
/*     */         {
/* 665 */           var3.add(var5);
/*     */         }
/*     */       } 
/*     */       
/* 669 */       if (var3.isEmpty()) {
/*     */         
/* 671 */         var4 = p_175762_1_.iterator();
/*     */         
/* 673 */         while (var4.hasNext()) {
/*     */           
/* 675 */           Object var6 = var4.next();
/*     */           
/* 677 */           if (var6 instanceof ResourceLocation && doesStringStartWith(var2, ((ResourceLocation)var6).getResourcePath()))
/*     */           {
/* 679 */             var3.add(String.valueOf(var6));
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 685 */     return var3;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isUsernameIndex(String[] args, int index) {
/* 693 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public static void notifyOperators(ICommandSender sender, ICommand command, String msgFormat, Object... msgParams) {
/* 698 */     notifyOperators(sender, command, 0, msgFormat, msgParams);
/*     */   }
/*     */ 
/*     */   
/*     */   public static void notifyOperators(ICommandSender sender, ICommand command, int p_152374_2_, String msgFormat, Object... msgParams) {
/* 703 */     if (theAdmin != null)
/*     */     {
/* 705 */       theAdmin.notifyOperators(sender, command, p_152374_2_, msgFormat, msgParams);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void setAdminCommander(IAdminCommand command) {
/* 714 */     theAdmin = command;
/*     */   }
/*     */ 
/*     */   
/*     */   public int compareTo(ICommand p_compareTo_1_) {
/* 719 */     return getCommandName().compareTo(p_compareTo_1_.getCommandName());
/*     */   }
/*     */ 
/*     */   
/*     */   public int compareTo(Object p_compareTo_1_) {
/* 724 */     return compareTo((ICommand)p_compareTo_1_);
/*     */   }
/*     */ 
/*     */   
/*     */   public static class CoordinateArg
/*     */   {
/*     */     private final double field_179633_a;
/*     */     private final double field_179631_b;
/*     */     private final boolean field_179632_c;
/*     */     private static final String __OBFID = "CL_00002365";
/*     */     
/*     */     protected CoordinateArg(double p_i46051_1_, double p_i46051_3_, boolean p_i46051_5_) {
/* 736 */       this.field_179633_a = p_i46051_1_;
/* 737 */       this.field_179631_b = p_i46051_3_;
/* 738 */       this.field_179632_c = p_i46051_5_;
/*     */     }
/*     */ 
/*     */     
/*     */     public double func_179628_a() {
/* 743 */       return this.field_179633_a;
/*     */     }
/*     */ 
/*     */     
/*     */     public double func_179629_b() {
/* 748 */       return this.field_179631_b;
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean func_179630_c() {
/* 753 */       return this.field_179632_c;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\command\CommandBase.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */