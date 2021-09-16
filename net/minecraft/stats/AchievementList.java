/*     */ package net.minecraft.stats;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import java.util.List;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.JsonSerializableSet;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class AchievementList
/*     */ {
/*     */   public static int minDisplayColumn;
/*     */   public static int minDisplayRow;
/*     */   public static int maxDisplayColumn;
/*     */   public static int maxDisplayRow;
/*  25 */   public static List achievementList = Lists.newArrayList();
/*     */ 
/*     */   
/*  28 */   public static Achievement openInventory = (new Achievement("achievement.openInventory", "openInventory", 0, 0, Items.book, null)).func_180789_a().func_180788_c();
/*     */ 
/*     */   
/*  31 */   public static Achievement mineWood = (new Achievement("achievement.mineWood", "mineWood", 2, 1, Blocks.log, openInventory)).func_180788_c();
/*     */ 
/*     */   
/*  34 */   public static Achievement buildWorkBench = (new Achievement("achievement.buildWorkBench", "buildWorkBench", 4, -1, Blocks.crafting_table, mineWood)).func_180788_c();
/*     */ 
/*     */   
/*  37 */   public static Achievement buildPickaxe = (new Achievement("achievement.buildPickaxe", "buildPickaxe", 4, 2, Items.wooden_pickaxe, buildWorkBench)).func_180788_c();
/*     */ 
/*     */   
/*  40 */   public static Achievement buildFurnace = (new Achievement("achievement.buildFurnace", "buildFurnace", 3, 4, Blocks.furnace, buildPickaxe)).func_180788_c();
/*     */ 
/*     */   
/*  43 */   public static Achievement acquireIron = (new Achievement("achievement.acquireIron", "acquireIron", 1, 4, Items.iron_ingot, buildFurnace)).func_180788_c();
/*     */ 
/*     */   
/*  46 */   public static Achievement buildHoe = (new Achievement("achievement.buildHoe", "buildHoe", 2, -3, Items.wooden_hoe, buildWorkBench)).func_180788_c();
/*     */ 
/*     */   
/*  49 */   public static Achievement makeBread = (new Achievement("achievement.makeBread", "makeBread", -1, -3, Items.bread, buildHoe)).func_180788_c();
/*     */ 
/*     */   
/*  52 */   public static Achievement bakeCake = (new Achievement("achievement.bakeCake", "bakeCake", 0, -5, Items.cake, buildHoe)).func_180788_c();
/*     */ 
/*     */   
/*  55 */   public static Achievement buildBetterPickaxe = (new Achievement("achievement.buildBetterPickaxe", "buildBetterPickaxe", 6, 2, Items.stone_pickaxe, buildPickaxe)).func_180788_c();
/*     */ 
/*     */   
/*  58 */   public static Achievement cookFish = (new Achievement("achievement.cookFish", "cookFish", 2, 6, Items.cooked_fish, buildFurnace)).func_180788_c();
/*     */ 
/*     */   
/*  61 */   public static Achievement onARail = (new Achievement("achievement.onARail", "onARail", 2, 3, Blocks.rail, acquireIron)).setSpecial().func_180788_c();
/*     */ 
/*     */   
/*  64 */   public static Achievement buildSword = (new Achievement("achievement.buildSword", "buildSword", 6, -1, Items.wooden_sword, buildWorkBench)).func_180788_c();
/*     */ 
/*     */   
/*  67 */   public static Achievement killEnemy = (new Achievement("achievement.killEnemy", "killEnemy", 8, -1, Items.bone, buildSword)).func_180788_c();
/*     */ 
/*     */   
/*  70 */   public static Achievement killCow = (new Achievement("achievement.killCow", "killCow", 7, -3, Items.leather, buildSword)).func_180788_c();
/*     */ 
/*     */   
/*  73 */   public static Achievement flyPig = (new Achievement("achievement.flyPig", "flyPig", 9, -3, Items.saddle, killCow)).setSpecial().func_180788_c();
/*     */ 
/*     */   
/*  76 */   public static Achievement snipeSkeleton = (new Achievement("achievement.snipeSkeleton", "snipeSkeleton", 7, 0, (Item)Items.bow, killEnemy)).setSpecial().func_180788_c();
/*     */ 
/*     */   
/*  79 */   public static Achievement diamonds = (new Achievement("achievement.diamonds", "diamonds", -1, 5, Blocks.diamond_ore, acquireIron)).func_180788_c();
/*  80 */   public static Achievement diamondsToYou = (new Achievement("achievement.diamondsToYou", "diamondsToYou", -1, 2, Items.diamond, diamonds)).func_180788_c();
/*     */ 
/*     */   
/*  83 */   public static Achievement portal = (new Achievement("achievement.portal", "portal", -1, 7, Blocks.obsidian, diamonds)).func_180788_c();
/*     */ 
/*     */   
/*  86 */   public static Achievement ghast = (new Achievement("achievement.ghast", "ghast", -4, 8, Items.ghast_tear, portal)).setSpecial().func_180788_c();
/*     */ 
/*     */   
/*  89 */   public static Achievement blazeRod = (new Achievement("achievement.blazeRod", "blazeRod", 0, 9, Items.blaze_rod, portal)).func_180788_c();
/*     */ 
/*     */   
/*  92 */   public static Achievement potion = (new Achievement("achievement.potion", "potion", 2, 8, (Item)Items.potionitem, blazeRod)).func_180788_c();
/*     */ 
/*     */   
/*  95 */   public static Achievement theEnd = (new Achievement("achievement.theEnd", "theEnd", 3, 10, Items.ender_eye, blazeRod)).setSpecial().func_180788_c();
/*     */ 
/*     */   
/*  98 */   public static Achievement theEnd2 = (new Achievement("achievement.theEnd2", "theEnd2", 4, 13, Blocks.dragon_egg, theEnd)).setSpecial().func_180788_c();
/*     */ 
/*     */   
/* 101 */   public static Achievement enchantments = (new Achievement("achievement.enchantments", "enchantments", -4, 4, Blocks.enchanting_table, diamonds)).func_180788_c();
/* 102 */   public static Achievement overkill = (new Achievement("achievement.overkill", "overkill", -4, 1, Items.diamond_sword, enchantments)).setSpecial().func_180788_c();
/*     */ 
/*     */   
/* 105 */   public static Achievement bookcase = (new Achievement("achievement.bookcase", "bookcase", -3, 6, Blocks.bookshelf, enchantments)).func_180788_c();
/*     */ 
/*     */   
/* 108 */   public static Achievement breedCow = (new Achievement("achievement.breedCow", "breedCow", 7, -5, Items.wheat, killCow)).func_180788_c();
/*     */ 
/*     */   
/* 111 */   public static Achievement spawnWither = (new Achievement("achievement.spawnWither", "spawnWither", 7, 12, new ItemStack(Items.skull, 1, 1), theEnd2)).func_180788_c();
/*     */ 
/*     */   
/* 114 */   public static Achievement killWither = (new Achievement("achievement.killWither", "killWither", 7, 10, Items.nether_star, spawnWither)).func_180788_c();
/*     */ 
/*     */   
/* 117 */   public static Achievement fullBeacon = (new Achievement("achievement.fullBeacon", "fullBeacon", 7, 8, (Block)Blocks.beacon, killWither)).setSpecial().func_180788_c();
/*     */ 
/*     */   
/* 120 */   public static Achievement exploreAllBiomes = (new Achievement("achievement.exploreAllBiomes", "exploreAllBiomes", 4, 8, (Item)Items.diamond_boots, theEnd)).func_180787_a(JsonSerializableSet.class).setSpecial().func_180788_c();
/* 121 */   public static Achievement overpowered = (new Achievement("achievement.overpowered", "overpowered", 6, 4, Items.golden_apple, buildBetterPickaxe)).setSpecial().func_180788_c();
/*     */   private static final String __OBFID = "CL_00001467";
/*     */   
/*     */   public static void init() {}
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\stats\AchievementList.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */