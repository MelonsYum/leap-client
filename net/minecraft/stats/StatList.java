/*     */ package net.minecraft.stats;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import com.google.common.collect.Maps;
/*     */ import com.google.common.collect.Sets;
/*     */ import java.util.HashSet;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.entity.EntityList;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.item.crafting.CraftingManager;
/*     */ import net.minecraft.item.crafting.FurnaceRecipes;
/*     */ import net.minecraft.item.crafting.IRecipe;
/*     */ import net.minecraft.util.ChatComponentTranslation;
/*     */ import net.minecraft.util.IChatComponent;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ 
/*     */ 
/*     */ public class StatList
/*     */ {
/*  25 */   protected static Map oneShotStats = Maps.newHashMap();
/*  26 */   public static List allStats = Lists.newArrayList();
/*  27 */   public static List generalStats = Lists.newArrayList();
/*  28 */   public static List itemStats = Lists.newArrayList();
/*     */ 
/*     */   
/*  31 */   public static List objectMineStats = Lists.newArrayList();
/*     */ 
/*     */   
/*  34 */   public static StatBase leaveGameStat = (new StatBasic("stat.leaveGame", (IChatComponent)new ChatComponentTranslation("stat.leaveGame", new Object[0]))).initIndependentStat().registerStat();
/*     */ 
/*     */   
/*  37 */   public static StatBase minutesPlayedStat = (new StatBasic("stat.playOneMinute", (IChatComponent)new ChatComponentTranslation("stat.playOneMinute", new Object[0]), StatBase.timeStatType)).initIndependentStat().registerStat();
/*  38 */   public static StatBase timeSinceDeathStat = (new StatBasic("stat.timeSinceDeath", (IChatComponent)new ChatComponentTranslation("stat.timeSinceDeath", new Object[0]), StatBase.timeStatType)).initIndependentStat().registerStat();
/*     */ 
/*     */   
/*  41 */   public static StatBase distanceWalkedStat = (new StatBasic("stat.walkOneCm", (IChatComponent)new ChatComponentTranslation("stat.walkOneCm", new Object[0]), StatBase.distanceStatType)).initIndependentStat().registerStat();
/*  42 */   public static StatBase distanceCrouchedStat = (new StatBasic("stat.crouchOneCm", (IChatComponent)new ChatComponentTranslation("stat.crouchOneCm", new Object[0]), StatBase.distanceStatType)).initIndependentStat().registerStat();
/*  43 */   public static StatBase distanceSprintedStat = (new StatBasic("stat.sprintOneCm", (IChatComponent)new ChatComponentTranslation("stat.sprintOneCm", new Object[0]), StatBase.distanceStatType)).initIndependentStat().registerStat();
/*     */ 
/*     */   
/*  46 */   public static StatBase distanceSwumStat = (new StatBasic("stat.swimOneCm", (IChatComponent)new ChatComponentTranslation("stat.swimOneCm", new Object[0]), StatBase.distanceStatType)).initIndependentStat().registerStat();
/*     */ 
/*     */   
/*  49 */   public static StatBase distanceFallenStat = (new StatBasic("stat.fallOneCm", (IChatComponent)new ChatComponentTranslation("stat.fallOneCm", new Object[0]), StatBase.distanceStatType)).initIndependentStat().registerStat();
/*     */ 
/*     */   
/*  52 */   public static StatBase distanceClimbedStat = (new StatBasic("stat.climbOneCm", (IChatComponent)new ChatComponentTranslation("stat.climbOneCm", new Object[0]), StatBase.distanceStatType)).initIndependentStat().registerStat();
/*     */ 
/*     */   
/*  55 */   public static StatBase distanceFlownStat = (new StatBasic("stat.flyOneCm", (IChatComponent)new ChatComponentTranslation("stat.flyOneCm", new Object[0]), StatBase.distanceStatType)).initIndependentStat().registerStat();
/*     */ 
/*     */   
/*  58 */   public static StatBase distanceDoveStat = (new StatBasic("stat.diveOneCm", (IChatComponent)new ChatComponentTranslation("stat.diveOneCm", new Object[0]), StatBase.distanceStatType)).initIndependentStat().registerStat();
/*     */ 
/*     */   
/*  61 */   public static StatBase distanceByMinecartStat = (new StatBasic("stat.minecartOneCm", (IChatComponent)new ChatComponentTranslation("stat.minecartOneCm", new Object[0]), StatBase.distanceStatType)).initIndependentStat().registerStat();
/*     */ 
/*     */   
/*  64 */   public static StatBase distanceByBoatStat = (new StatBasic("stat.boatOneCm", (IChatComponent)new ChatComponentTranslation("stat.boatOneCm", new Object[0]), StatBase.distanceStatType)).initIndependentStat().registerStat();
/*     */ 
/*     */   
/*  67 */   public static StatBase distanceByPigStat = (new StatBasic("stat.pigOneCm", (IChatComponent)new ChatComponentTranslation("stat.pigOneCm", new Object[0]), StatBase.distanceStatType)).initIndependentStat().registerStat();
/*  68 */   public static StatBase distanceByHorseStat = (new StatBasic("stat.horseOneCm", (IChatComponent)new ChatComponentTranslation("stat.horseOneCm", new Object[0]), StatBase.distanceStatType)).initIndependentStat().registerStat();
/*     */ 
/*     */   
/*  71 */   public static StatBase jumpStat = (new StatBasic("stat.jump", (IChatComponent)new ChatComponentTranslation("stat.jump", new Object[0]))).initIndependentStat().registerStat();
/*     */ 
/*     */   
/*  74 */   public static StatBase dropStat = (new StatBasic("stat.drop", (IChatComponent)new ChatComponentTranslation("stat.drop", new Object[0]))).initIndependentStat().registerStat();
/*     */ 
/*     */   
/*  77 */   public static StatBase damageDealtStat = (new StatBasic("stat.damageDealt", (IChatComponent)new ChatComponentTranslation("stat.damageDealt", new Object[0]), StatBase.field_111202_k)).registerStat();
/*     */ 
/*     */   
/*  80 */   public static StatBase damageTakenStat = (new StatBasic("stat.damageTaken", (IChatComponent)new ChatComponentTranslation("stat.damageTaken", new Object[0]), StatBase.field_111202_k)).registerStat();
/*     */ 
/*     */   
/*  83 */   public static StatBase deathsStat = (new StatBasic("stat.deaths", (IChatComponent)new ChatComponentTranslation("stat.deaths", new Object[0]))).registerStat();
/*     */ 
/*     */   
/*  86 */   public static StatBase mobKillsStat = (new StatBasic("stat.mobKills", (IChatComponent)new ChatComponentTranslation("stat.mobKills", new Object[0]))).registerStat();
/*     */ 
/*     */   
/*  89 */   public static StatBase animalsBredStat = (new StatBasic("stat.animalsBred", (IChatComponent)new ChatComponentTranslation("stat.animalsBred", new Object[0]))).registerStat();
/*     */ 
/*     */   
/*  92 */   public static StatBase playerKillsStat = (new StatBasic("stat.playerKills", (IChatComponent)new ChatComponentTranslation("stat.playerKills", new Object[0]))).registerStat();
/*  93 */   public static StatBase fishCaughtStat = (new StatBasic("stat.fishCaught", (IChatComponent)new ChatComponentTranslation("stat.fishCaught", new Object[0]))).registerStat();
/*  94 */   public static StatBase junkFishedStat = (new StatBasic("stat.junkFished", (IChatComponent)new ChatComponentTranslation("stat.junkFished", new Object[0]))).registerStat();
/*  95 */   public static StatBase treasureFishedStat = (new StatBasic("stat.treasureFished", (IChatComponent)new ChatComponentTranslation("stat.treasureFished", new Object[0]))).registerStat();
/*  96 */   public static StatBase timesTalkedToVillagerStat = (new StatBasic("stat.talkedToVillager", (IChatComponent)new ChatComponentTranslation("stat.talkedToVillager", new Object[0]))).registerStat();
/*  97 */   public static StatBase timesTradedWithVillagerStat = (new StatBasic("stat.tradedWithVillager", (IChatComponent)new ChatComponentTranslation("stat.tradedWithVillager", new Object[0]))).registerStat();
/*  98 */   public static final StatBase[] mineBlockStatArray = new StatBase[4096];
/*     */ 
/*     */   
/* 101 */   public static final StatBase[] objectCraftStats = new StatBase[32000];
/*     */ 
/*     */   
/* 104 */   public static final StatBase[] objectUseStats = new StatBase[32000];
/*     */ 
/*     */   
/* 107 */   public static final StatBase[] objectBreakStats = new StatBase[32000];
/*     */   
/*     */   private static final String __OBFID = "CL_00001480";
/*     */   
/*     */   public static void func_151178_a() {
/* 112 */     func_151181_c();
/* 113 */     initStats();
/* 114 */     func_151179_e();
/* 115 */     initCraftableStats();
/* 116 */     AchievementList.init();
/* 117 */     EntityList.func_151514_a();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static void initCraftableStats() {
/* 126 */     HashSet<Item> var0 = Sets.newHashSet();
/* 127 */     Iterator<IRecipe> var1 = CraftingManager.getInstance().getRecipeList().iterator();
/*     */     
/* 129 */     while (var1.hasNext()) {
/*     */       
/* 131 */       IRecipe var2 = var1.next();
/*     */       
/* 133 */       if (var2.getRecipeOutput() != null)
/*     */       {
/* 135 */         var0.add(var2.getRecipeOutput().getItem());
/*     */       }
/*     */     } 
/*     */     
/* 139 */     var1 = FurnaceRecipes.instance().getSmeltingList().values().iterator();
/*     */     
/* 141 */     while (var1.hasNext()) {
/*     */       
/* 143 */       ItemStack var5 = (ItemStack)var1.next();
/* 144 */       var0.add(var5.getItem());
/*     */     } 
/*     */     
/* 147 */     var1 = (Iterator)var0.iterator();
/*     */     
/* 149 */     while (var1.hasNext()) {
/*     */       
/* 151 */       Item var6 = (Item)var1.next();
/*     */       
/* 153 */       if (var6 != null) {
/*     */         
/* 155 */         int var3 = Item.getIdFromItem(var6);
/* 156 */         String var4 = func_180204_a(var6);
/*     */         
/* 158 */         if (var4 != null)
/*     */         {
/* 160 */           objectCraftStats[var3] = (new StatCrafting("stat.craftItem.", var4, (IChatComponent)new ChatComponentTranslation("stat.craftItem", new Object[] { (new ItemStack(var6)).getChatComponent() }), var6)).registerStat();
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 165 */     replaceAllSimilarBlocks(objectCraftStats);
/*     */   }
/*     */ 
/*     */   
/*     */   private static void func_151181_c() {
/* 170 */     Iterator<Block> var0 = Block.blockRegistry.iterator();
/*     */     
/* 172 */     while (var0.hasNext()) {
/*     */       
/* 174 */       Block var1 = var0.next();
/* 175 */       Item var2 = Item.getItemFromBlock(var1);
/*     */       
/* 177 */       if (var2 != null) {
/*     */         
/* 179 */         int var3 = Block.getIdFromBlock(var1);
/* 180 */         String var4 = func_180204_a(var2);
/*     */         
/* 182 */         if (var4 != null && var1.getEnableStats()) {
/*     */           
/* 184 */           mineBlockStatArray[var3] = (new StatCrafting("stat.mineBlock.", var4, (IChatComponent)new ChatComponentTranslation("stat.mineBlock", new Object[] { (new ItemStack(var1)).getChatComponent() }), var2)).registerStat();
/* 185 */           objectMineStats.add((StatCrafting)mineBlockStatArray[var3]);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 190 */     replaceAllSimilarBlocks(mineBlockStatArray);
/*     */   }
/*     */ 
/*     */   
/*     */   private static void initStats() {
/* 195 */     Iterator<Item> var0 = Item.itemRegistry.iterator();
/*     */     
/* 197 */     while (var0.hasNext()) {
/*     */       
/* 199 */       Item var1 = var0.next();
/*     */       
/* 201 */       if (var1 != null) {
/*     */         
/* 203 */         int var2 = Item.getIdFromItem(var1);
/* 204 */         String var3 = func_180204_a(var1);
/*     */         
/* 206 */         if (var3 != null) {
/*     */           
/* 208 */           objectUseStats[var2] = (new StatCrafting("stat.useItem.", var3, (IChatComponent)new ChatComponentTranslation("stat.useItem", new Object[] { (new ItemStack(var1)).getChatComponent() }), var1)).registerStat();
/*     */           
/* 210 */           if (!(var1 instanceof net.minecraft.item.ItemBlock))
/*     */           {
/* 212 */             itemStats.add((StatCrafting)objectUseStats[var2]);
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 218 */     replaceAllSimilarBlocks(objectUseStats);
/*     */   }
/*     */ 
/*     */   
/*     */   private static void func_151179_e() {
/* 223 */     Iterator<Item> var0 = Item.itemRegistry.iterator();
/*     */     
/* 225 */     while (var0.hasNext()) {
/*     */       
/* 227 */       Item var1 = var0.next();
/*     */       
/* 229 */       if (var1 != null) {
/*     */         
/* 231 */         int var2 = Item.getIdFromItem(var1);
/* 232 */         String var3 = func_180204_a(var1);
/*     */         
/* 234 */         if (var3 != null && var1.isDamageable())
/*     */         {
/* 236 */           objectBreakStats[var2] = (new StatCrafting("stat.breakItem.", var3, (IChatComponent)new ChatComponentTranslation("stat.breakItem", new Object[] { (new ItemStack(var1)).getChatComponent() }), var1)).registerStat();
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 241 */     replaceAllSimilarBlocks(objectBreakStats);
/*     */   }
/*     */ 
/*     */   
/*     */   private static String func_180204_a(Item p_180204_0_) {
/* 246 */     ResourceLocation var1 = (ResourceLocation)Item.itemRegistry.getNameForObject(p_180204_0_);
/* 247 */     return (var1 != null) ? var1.toString().replace(':', '.') : null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static void replaceAllSimilarBlocks(StatBase[] p_75924_0_) {
/* 255 */     func_151180_a(p_75924_0_, (Block)Blocks.water, (Block)Blocks.flowing_water);
/* 256 */     func_151180_a(p_75924_0_, (Block)Blocks.lava, (Block)Blocks.flowing_lava);
/* 257 */     func_151180_a(p_75924_0_, Blocks.lit_pumpkin, Blocks.pumpkin);
/* 258 */     func_151180_a(p_75924_0_, Blocks.lit_furnace, Blocks.furnace);
/* 259 */     func_151180_a(p_75924_0_, Blocks.lit_redstone_ore, Blocks.redstone_ore);
/* 260 */     func_151180_a(p_75924_0_, (Block)Blocks.powered_repeater, (Block)Blocks.unpowered_repeater);
/* 261 */     func_151180_a(p_75924_0_, (Block)Blocks.powered_comparator, (Block)Blocks.unpowered_comparator);
/* 262 */     func_151180_a(p_75924_0_, Blocks.redstone_torch, Blocks.unlit_redstone_torch);
/* 263 */     func_151180_a(p_75924_0_, Blocks.lit_redstone_lamp, Blocks.redstone_lamp);
/* 264 */     func_151180_a(p_75924_0_, (Block)Blocks.double_stone_slab, (Block)Blocks.stone_slab);
/* 265 */     func_151180_a(p_75924_0_, (Block)Blocks.double_wooden_slab, (Block)Blocks.wooden_slab);
/* 266 */     func_151180_a(p_75924_0_, (Block)Blocks.double_stone_slab2, (Block)Blocks.stone_slab2);
/* 267 */     func_151180_a(p_75924_0_, (Block)Blocks.grass, Blocks.dirt);
/* 268 */     func_151180_a(p_75924_0_, Blocks.farmland, Blocks.dirt);
/*     */   }
/*     */ 
/*     */   
/*     */   private static void func_151180_a(StatBase[] p_151180_0_, Block p_151180_1_, Block p_151180_2_) {
/* 273 */     int var3 = Block.getIdFromBlock(p_151180_1_);
/* 274 */     int var4 = Block.getIdFromBlock(p_151180_2_);
/*     */     
/* 276 */     if (p_151180_0_[var3] != null && p_151180_0_[var4] == null) {
/*     */       
/* 278 */       p_151180_0_[var4] = p_151180_0_[var3];
/*     */     }
/*     */     else {
/*     */       
/* 282 */       allStats.remove(p_151180_0_[var3]);
/* 283 */       objectMineStats.remove(p_151180_0_[var3]);
/* 284 */       generalStats.remove(p_151180_0_[var3]);
/* 285 */       p_151180_0_[var3] = p_151180_0_[var4];
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static StatBase func_151182_a(EntityList.EntityEggInfo p_151182_0_) {
/* 291 */     String var1 = EntityList.getStringFromID(p_151182_0_.spawnedID);
/* 292 */     return (var1 == null) ? null : (new StatBase("stat.killEntity." + var1, (IChatComponent)new ChatComponentTranslation("stat.entityKill", new Object[] { new ChatComponentTranslation("entity." + var1 + ".name", new Object[0]) }))).registerStat();
/*     */   }
/*     */ 
/*     */   
/*     */   public static StatBase func_151176_b(EntityList.EntityEggInfo p_151176_0_) {
/* 297 */     String var1 = EntityList.getStringFromID(p_151176_0_.spawnedID);
/* 298 */     return (var1 == null) ? null : (new StatBase("stat.entityKilledBy." + var1, (IChatComponent)new ChatComponentTranslation("stat.entityKilledBy", new Object[] { new ChatComponentTranslation("entity." + var1 + ".name", new Object[0]) }))).registerStat();
/*     */   }
/*     */ 
/*     */   
/*     */   public static StatBase getOneShotStat(String p_151177_0_) {
/* 303 */     return (StatBase)oneShotStats.get(p_151177_0_);
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\stats\StatList.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */