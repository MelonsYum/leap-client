/*     */ package net.minecraft.item.crafting;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import com.google.common.collect.Maps;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.Comparator;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.BlockPlanks;
/*     */ import net.minecraft.block.BlockStone;
/*     */ import net.minecraft.block.BlockStoneSlab;
/*     */ import net.minecraft.block.BlockStoneSlabNew;
/*     */ import net.minecraft.block.BlockWall;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.inventory.InventoryCrafting;
/*     */ import net.minecraft.item.EnumDyeColor;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ 
/*     */ public class CraftingManager
/*     */ {
/*  28 */   private static final CraftingManager instance = new CraftingManager();
/*     */ 
/*     */   
/*  31 */   private final List recipes = Lists.newArrayList();
/*     */ 
/*     */   
/*     */   private static final String __OBFID = "CL_00000090";
/*     */ 
/*     */ 
/*     */   
/*     */   public static CraftingManager getInstance() {
/*  39 */     return instance;
/*     */   }
/*     */ 
/*     */   
/*     */   private CraftingManager() {
/*  44 */     (new RecipesTools()).addRecipes(this);
/*  45 */     (new RecipesWeapons()).addRecipes(this);
/*  46 */     (new RecipesIngots()).addRecipes(this);
/*  47 */     (new RecipesFood()).addRecipes(this);
/*  48 */     (new RecipesCrafting()).addRecipes(this);
/*  49 */     (new RecipesArmor()).addRecipes(this);
/*  50 */     (new RecipesDyes()).addRecipes(this);
/*  51 */     this.recipes.add(new RecipesArmorDyes());
/*  52 */     this.recipes.add(new RecipeBookCloning());
/*  53 */     this.recipes.add(new RecipesMapCloning());
/*  54 */     this.recipes.add(new RecipesMapExtending());
/*  55 */     this.recipes.add(new RecipeFireworks());
/*  56 */     this.recipes.add(new RecipeRepairItem());
/*  57 */     (new RecipesBanners()).func_179534_a(this);
/*  58 */     addRecipe(new ItemStack(Items.paper, 3), new Object[] { "###", Character.valueOf('#'), Items.reeds });
/*  59 */     addShapelessRecipe(new ItemStack(Items.book, 1), new Object[] { Items.paper, Items.paper, Items.paper, Items.leather });
/*  60 */     addShapelessRecipe(new ItemStack(Items.writable_book, 1), new Object[] { Items.book, new ItemStack(Items.dye, 1, EnumDyeColor.BLACK.getDyeColorDamage()), Items.feather });
/*  61 */     addRecipe(new ItemStack(Blocks.oak_fence, 3), new Object[] { "W#W", "W#W", Character.valueOf('#'), Items.stick, Character.valueOf('W'), new ItemStack(Blocks.planks, 1, BlockPlanks.EnumType.OAK.func_176839_a()) });
/*  62 */     addRecipe(new ItemStack(Blocks.birch_fence, 3), new Object[] { "W#W", "W#W", Character.valueOf('#'), Items.stick, Character.valueOf('W'), new ItemStack(Blocks.planks, 1, BlockPlanks.EnumType.BIRCH.func_176839_a()) });
/*  63 */     addRecipe(new ItemStack(Blocks.spruce_fence, 3), new Object[] { "W#W", "W#W", Character.valueOf('#'), Items.stick, Character.valueOf('W'), new ItemStack(Blocks.planks, 1, BlockPlanks.EnumType.SPRUCE.func_176839_a()) });
/*  64 */     addRecipe(new ItemStack(Blocks.jungle_fence, 3), new Object[] { "W#W", "W#W", Character.valueOf('#'), Items.stick, Character.valueOf('W'), new ItemStack(Blocks.planks, 1, BlockPlanks.EnumType.JUNGLE.func_176839_a()) });
/*  65 */     addRecipe(new ItemStack(Blocks.acacia_fence, 3), new Object[] { "W#W", "W#W", Character.valueOf('#'), Items.stick, Character.valueOf('W'), new ItemStack(Blocks.planks, 1, 4 + BlockPlanks.EnumType.ACACIA.func_176839_a() - 4) });
/*  66 */     addRecipe(new ItemStack(Blocks.dark_oak_fence, 3), new Object[] { "W#W", "W#W", Character.valueOf('#'), Items.stick, Character.valueOf('W'), new ItemStack(Blocks.planks, 1, 4 + BlockPlanks.EnumType.DARK_OAK.func_176839_a() - 4) });
/*  67 */     addRecipe(new ItemStack(Blocks.cobblestone_wall, 6, BlockWall.EnumType.NORMAL.func_176657_a()), new Object[] { "###", "###", Character.valueOf('#'), Blocks.cobblestone });
/*  68 */     addRecipe(new ItemStack(Blocks.cobblestone_wall, 6, BlockWall.EnumType.MOSSY.func_176657_a()), new Object[] { "###", "###", Character.valueOf('#'), Blocks.mossy_cobblestone });
/*  69 */     addRecipe(new ItemStack(Blocks.nether_brick_fence, 6), new Object[] { "###", "###", Character.valueOf('#'), Blocks.nether_brick });
/*  70 */     addRecipe(new ItemStack(Blocks.oak_fence_gate, 1), new Object[] { "#W#", "#W#", Character.valueOf('#'), Items.stick, Character.valueOf('W'), new ItemStack(Blocks.planks, 1, BlockPlanks.EnumType.OAK.func_176839_a()) });
/*  71 */     addRecipe(new ItemStack(Blocks.birch_fence_gate, 1), new Object[] { "#W#", "#W#", Character.valueOf('#'), Items.stick, Character.valueOf('W'), new ItemStack(Blocks.planks, 1, BlockPlanks.EnumType.BIRCH.func_176839_a()) });
/*  72 */     addRecipe(new ItemStack(Blocks.spruce_fence_gate, 1), new Object[] { "#W#", "#W#", Character.valueOf('#'), Items.stick, Character.valueOf('W'), new ItemStack(Blocks.planks, 1, BlockPlanks.EnumType.SPRUCE.func_176839_a()) });
/*  73 */     addRecipe(new ItemStack(Blocks.jungle_fence_gate, 1), new Object[] { "#W#", "#W#", Character.valueOf('#'), Items.stick, Character.valueOf('W'), new ItemStack(Blocks.planks, 1, BlockPlanks.EnumType.JUNGLE.func_176839_a()) });
/*  74 */     addRecipe(new ItemStack(Blocks.acacia_fence_gate, 1), new Object[] { "#W#", "#W#", Character.valueOf('#'), Items.stick, Character.valueOf('W'), new ItemStack(Blocks.planks, 1, 4 + BlockPlanks.EnumType.ACACIA.func_176839_a() - 4) });
/*  75 */     addRecipe(new ItemStack(Blocks.dark_oak_fence_gate, 1), new Object[] { "#W#", "#W#", Character.valueOf('#'), Items.stick, Character.valueOf('W'), new ItemStack(Blocks.planks, 1, 4 + BlockPlanks.EnumType.DARK_OAK.func_176839_a() - 4) });
/*  76 */     addRecipe(new ItemStack(Blocks.jukebox, 1), new Object[] { "###", "#X#", "###", Character.valueOf('#'), Blocks.planks, Character.valueOf('X'), Items.diamond });
/*  77 */     addRecipe(new ItemStack(Items.lead, 2), new Object[] { "~~ ", "~O ", "  ~", Character.valueOf('~'), Items.string, Character.valueOf('O'), Items.slime_ball });
/*  78 */     addRecipe(new ItemStack(Blocks.noteblock, 1), new Object[] { "###", "#X#", "###", Character.valueOf('#'), Blocks.planks, Character.valueOf('X'), Items.redstone });
/*  79 */     addRecipe(new ItemStack(Blocks.bookshelf, 1), new Object[] { "###", "XXX", "###", Character.valueOf('#'), Blocks.planks, Character.valueOf('X'), Items.book });
/*  80 */     addRecipe(new ItemStack(Blocks.snow, 1), new Object[] { "##", "##", Character.valueOf('#'), Items.snowball });
/*  81 */     addRecipe(new ItemStack(Blocks.snow_layer, 6), new Object[] { "###", Character.valueOf('#'), Blocks.snow });
/*  82 */     addRecipe(new ItemStack(Blocks.clay, 1), new Object[] { "##", "##", Character.valueOf('#'), Items.clay_ball });
/*  83 */     addRecipe(new ItemStack(Blocks.brick_block, 1), new Object[] { "##", "##", Character.valueOf('#'), Items.brick });
/*  84 */     addRecipe(new ItemStack(Blocks.glowstone, 1), new Object[] { "##", "##", Character.valueOf('#'), Items.glowstone_dust });
/*  85 */     addRecipe(new ItemStack(Blocks.quartz_block, 1), new Object[] { "##", "##", Character.valueOf('#'), Items.quartz });
/*  86 */     addRecipe(new ItemStack(Blocks.wool, 1), new Object[] { "##", "##", Character.valueOf('#'), Items.string });
/*  87 */     addRecipe(new ItemStack(Blocks.tnt, 1), new Object[] { "X#X", "#X#", "X#X", Character.valueOf('X'), Items.gunpowder, Character.valueOf('#'), Blocks.sand });
/*  88 */     addRecipe(new ItemStack((Block)Blocks.stone_slab, 6, BlockStoneSlab.EnumType.COBBLESTONE.func_176624_a()), new Object[] { "###", Character.valueOf('#'), Blocks.cobblestone });
/*  89 */     addRecipe(new ItemStack((Block)Blocks.stone_slab, 6, BlockStoneSlab.EnumType.STONE.func_176624_a()), new Object[] { "###", Character.valueOf('#'), new ItemStack(Blocks.stone, BlockStone.EnumType.STONE.getMetaFromState()) });
/*  90 */     addRecipe(new ItemStack((Block)Blocks.stone_slab, 6, BlockStoneSlab.EnumType.SAND.func_176624_a()), new Object[] { "###", Character.valueOf('#'), Blocks.sandstone });
/*  91 */     addRecipe(new ItemStack((Block)Blocks.stone_slab, 6, BlockStoneSlab.EnumType.BRICK.func_176624_a()), new Object[] { "###", Character.valueOf('#'), Blocks.brick_block });
/*  92 */     addRecipe(new ItemStack((Block)Blocks.stone_slab, 6, BlockStoneSlab.EnumType.SMOOTHBRICK.func_176624_a()), new Object[] { "###", Character.valueOf('#'), Blocks.stonebrick });
/*  93 */     addRecipe(new ItemStack((Block)Blocks.stone_slab, 6, BlockStoneSlab.EnumType.NETHERBRICK.func_176624_a()), new Object[] { "###", Character.valueOf('#'), Blocks.nether_brick });
/*  94 */     addRecipe(new ItemStack((Block)Blocks.stone_slab, 6, BlockStoneSlab.EnumType.QUARTZ.func_176624_a()), new Object[] { "###", Character.valueOf('#'), Blocks.quartz_block });
/*  95 */     addRecipe(new ItemStack((Block)Blocks.stone_slab2, 6, BlockStoneSlabNew.EnumType.RED_SANDSTONE.func_176915_a()), new Object[] { "###", Character.valueOf('#'), Blocks.red_sandstone });
/*  96 */     addRecipe(new ItemStack((Block)Blocks.wooden_slab, 6, 0), new Object[] { "###", Character.valueOf('#'), new ItemStack(Blocks.planks, 1, BlockPlanks.EnumType.OAK.func_176839_a()) });
/*  97 */     addRecipe(new ItemStack((Block)Blocks.wooden_slab, 6, BlockPlanks.EnumType.BIRCH.func_176839_a()), new Object[] { "###", Character.valueOf('#'), new ItemStack(Blocks.planks, 1, BlockPlanks.EnumType.BIRCH.func_176839_a()) });
/*  98 */     addRecipe(new ItemStack((Block)Blocks.wooden_slab, 6, BlockPlanks.EnumType.SPRUCE.func_176839_a()), new Object[] { "###", Character.valueOf('#'), new ItemStack(Blocks.planks, 1, BlockPlanks.EnumType.SPRUCE.func_176839_a()) });
/*  99 */     addRecipe(new ItemStack((Block)Blocks.wooden_slab, 6, BlockPlanks.EnumType.JUNGLE.func_176839_a()), new Object[] { "###", Character.valueOf('#'), new ItemStack(Blocks.planks, 1, BlockPlanks.EnumType.JUNGLE.func_176839_a()) });
/* 100 */     addRecipe(new ItemStack((Block)Blocks.wooden_slab, 6, 4 + BlockPlanks.EnumType.ACACIA.func_176839_a() - 4), new Object[] { "###", Character.valueOf('#'), new ItemStack(Blocks.planks, 1, 4 + BlockPlanks.EnumType.ACACIA.func_176839_a() - 4) });
/* 101 */     addRecipe(new ItemStack((Block)Blocks.wooden_slab, 6, 4 + BlockPlanks.EnumType.DARK_OAK.func_176839_a() - 4), new Object[] { "###", Character.valueOf('#'), new ItemStack(Blocks.planks, 1, 4 + BlockPlanks.EnumType.DARK_OAK.func_176839_a() - 4) });
/* 102 */     addRecipe(new ItemStack(Blocks.ladder, 3), new Object[] { "# #", "###", "# #", Character.valueOf('#'), Items.stick });
/* 103 */     addRecipe(new ItemStack(Items.oak_door, 3), new Object[] { "##", "##", "##", Character.valueOf('#'), new ItemStack(Blocks.planks, 1, BlockPlanks.EnumType.OAK.func_176839_a()) });
/* 104 */     addRecipe(new ItemStack(Items.spruce_door, 3), new Object[] { "##", "##", "##", Character.valueOf('#'), new ItemStack(Blocks.planks, 1, BlockPlanks.EnumType.SPRUCE.func_176839_a()) });
/* 105 */     addRecipe(new ItemStack(Items.birch_door, 3), new Object[] { "##", "##", "##", Character.valueOf('#'), new ItemStack(Blocks.planks, 1, BlockPlanks.EnumType.BIRCH.func_176839_a()) });
/* 106 */     addRecipe(new ItemStack(Items.jungle_door, 3), new Object[] { "##", "##", "##", Character.valueOf('#'), new ItemStack(Blocks.planks, 1, BlockPlanks.EnumType.JUNGLE.func_176839_a()) });
/* 107 */     addRecipe(new ItemStack(Items.acacia_door, 3), new Object[] { "##", "##", "##", Character.valueOf('#'), new ItemStack(Blocks.planks, 1, BlockPlanks.EnumType.ACACIA.func_176839_a()) });
/* 108 */     addRecipe(new ItemStack(Items.dark_oak_door, 3), new Object[] { "##", "##", "##", Character.valueOf('#'), new ItemStack(Blocks.planks, 1, BlockPlanks.EnumType.DARK_OAK.func_176839_a()) });
/* 109 */     addRecipe(new ItemStack(Blocks.trapdoor, 2), new Object[] { "###", "###", Character.valueOf('#'), Blocks.planks });
/* 110 */     addRecipe(new ItemStack(Items.iron_door, 3), new Object[] { "##", "##", "##", Character.valueOf('#'), Items.iron_ingot });
/* 111 */     addRecipe(new ItemStack(Blocks.iron_trapdoor, 1), new Object[] { "##", "##", Character.valueOf('#'), Items.iron_ingot });
/* 112 */     addRecipe(new ItemStack(Items.sign, 3), new Object[] { "###", "###", " X ", Character.valueOf('#'), Blocks.planks, Character.valueOf('X'), Items.stick });
/* 113 */     addRecipe(new ItemStack(Items.cake, 1), new Object[] { "AAA", "BEB", "CCC", Character.valueOf('A'), Items.milk_bucket, Character.valueOf('B'), Items.sugar, Character.valueOf('C'), Items.wheat, Character.valueOf('E'), Items.egg });
/* 114 */     addRecipe(new ItemStack(Items.sugar, 1), new Object[] { "#", Character.valueOf('#'), Items.reeds });
/* 115 */     addRecipe(new ItemStack(Blocks.planks, 4, BlockPlanks.EnumType.OAK.func_176839_a()), new Object[] { "#", Character.valueOf('#'), new ItemStack(Blocks.log, 1, BlockPlanks.EnumType.OAK.func_176839_a()) });
/* 116 */     addRecipe(new ItemStack(Blocks.planks, 4, BlockPlanks.EnumType.SPRUCE.func_176839_a()), new Object[] { "#", Character.valueOf('#'), new ItemStack(Blocks.log, 1, BlockPlanks.EnumType.SPRUCE.func_176839_a()) });
/* 117 */     addRecipe(new ItemStack(Blocks.planks, 4, BlockPlanks.EnumType.BIRCH.func_176839_a()), new Object[] { "#", Character.valueOf('#'), new ItemStack(Blocks.log, 1, BlockPlanks.EnumType.BIRCH.func_176839_a()) });
/* 118 */     addRecipe(new ItemStack(Blocks.planks, 4, BlockPlanks.EnumType.JUNGLE.func_176839_a()), new Object[] { "#", Character.valueOf('#'), new ItemStack(Blocks.log, 1, BlockPlanks.EnumType.JUNGLE.func_176839_a()) });
/* 119 */     addRecipe(new ItemStack(Blocks.planks, 4, 4 + BlockPlanks.EnumType.ACACIA.func_176839_a() - 4), new Object[] { "#", Character.valueOf('#'), new ItemStack(Blocks.log2, 1, BlockPlanks.EnumType.ACACIA.func_176839_a() - 4) });
/* 120 */     addRecipe(new ItemStack(Blocks.planks, 4, 4 + BlockPlanks.EnumType.DARK_OAK.func_176839_a() - 4), new Object[] { "#", Character.valueOf('#'), new ItemStack(Blocks.log2, 1, BlockPlanks.EnumType.DARK_OAK.func_176839_a() - 4) });
/* 121 */     addRecipe(new ItemStack(Items.stick, 4), new Object[] { "#", "#", Character.valueOf('#'), Blocks.planks });
/* 122 */     addRecipe(new ItemStack(Blocks.torch, 4), new Object[] { "X", "#", Character.valueOf('X'), Items.coal, Character.valueOf('#'), Items.stick });
/* 123 */     addRecipe(new ItemStack(Blocks.torch, 4), new Object[] { "X", "#", Character.valueOf('X'), new ItemStack(Items.coal, 1, 1), Character.valueOf('#'), Items.stick });
/* 124 */     addRecipe(new ItemStack(Items.bowl, 4), new Object[] { "# #", " # ", Character.valueOf('#'), Blocks.planks });
/* 125 */     addRecipe(new ItemStack(Items.glass_bottle, 3), new Object[] { "# #", " # ", Character.valueOf('#'), Blocks.glass });
/* 126 */     addRecipe(new ItemStack(Blocks.rail, 16), new Object[] { "X X", "X#X", "X X", Character.valueOf('X'), Items.iron_ingot, Character.valueOf('#'), Items.stick });
/* 127 */     addRecipe(new ItemStack(Blocks.golden_rail, 6), new Object[] { "X X", "X#X", "XRX", Character.valueOf('X'), Items.gold_ingot, Character.valueOf('R'), Items.redstone, Character.valueOf('#'), Items.stick });
/* 128 */     addRecipe(new ItemStack(Blocks.activator_rail, 6), new Object[] { "XSX", "X#X", "XSX", Character.valueOf('X'), Items.iron_ingot, Character.valueOf('#'), Blocks.redstone_torch, Character.valueOf('S'), Items.stick });
/* 129 */     addRecipe(new ItemStack(Blocks.detector_rail, 6), new Object[] { "X X", "X#X", "XRX", Character.valueOf('X'), Items.iron_ingot, Character.valueOf('R'), Items.redstone, Character.valueOf('#'), Blocks.stone_pressure_plate });
/* 130 */     addRecipe(new ItemStack(Items.minecart, 1), new Object[] { "# #", "###", Character.valueOf('#'), Items.iron_ingot });
/* 131 */     addRecipe(new ItemStack(Items.cauldron, 1), new Object[] { "# #", "# #", "###", Character.valueOf('#'), Items.iron_ingot });
/* 132 */     addRecipe(new ItemStack(Items.brewing_stand, 1), new Object[] { " B ", "###", Character.valueOf('#'), Blocks.cobblestone, Character.valueOf('B'), Items.blaze_rod });
/* 133 */     addRecipe(new ItemStack(Blocks.lit_pumpkin, 1), new Object[] { "A", "B", Character.valueOf('A'), Blocks.pumpkin, Character.valueOf('B'), Blocks.torch });
/* 134 */     addRecipe(new ItemStack(Items.chest_minecart, 1), new Object[] { "A", "B", Character.valueOf('A'), Blocks.chest, Character.valueOf('B'), Items.minecart });
/* 135 */     addRecipe(new ItemStack(Items.furnace_minecart, 1), new Object[] { "A", "B", Character.valueOf('A'), Blocks.furnace, Character.valueOf('B'), Items.minecart });
/* 136 */     addRecipe(new ItemStack(Items.tnt_minecart, 1), new Object[] { "A", "B", Character.valueOf('A'), Blocks.tnt, Character.valueOf('B'), Items.minecart });
/* 137 */     addRecipe(new ItemStack(Items.hopper_minecart, 1), new Object[] { "A", "B", Character.valueOf('A'), Blocks.hopper, Character.valueOf('B'), Items.minecart });
/* 138 */     addRecipe(new ItemStack(Items.boat, 1), new Object[] { "# #", "###", Character.valueOf('#'), Blocks.planks });
/* 139 */     addRecipe(new ItemStack(Items.bucket, 1), new Object[] { "# #", " # ", Character.valueOf('#'), Items.iron_ingot });
/* 140 */     addRecipe(new ItemStack(Items.flower_pot, 1), new Object[] { "# #", " # ", Character.valueOf('#'), Items.brick });
/* 141 */     addShapelessRecipe(new ItemStack(Items.flint_and_steel, 1), new Object[] { new ItemStack(Items.iron_ingot, 1), new ItemStack(Items.flint, 1) });
/* 142 */     addRecipe(new ItemStack(Items.bread, 1), new Object[] { "###", Character.valueOf('#'), Items.wheat });
/* 143 */     addRecipe(new ItemStack(Blocks.oak_stairs, 4), new Object[] { "#  ", "## ", "###", Character.valueOf('#'), new ItemStack(Blocks.planks, 1, BlockPlanks.EnumType.OAK.func_176839_a()) });
/* 144 */     addRecipe(new ItemStack(Blocks.birch_stairs, 4), new Object[] { "#  ", "## ", "###", Character.valueOf('#'), new ItemStack(Blocks.planks, 1, BlockPlanks.EnumType.BIRCH.func_176839_a()) });
/* 145 */     addRecipe(new ItemStack(Blocks.spruce_stairs, 4), new Object[] { "#  ", "## ", "###", Character.valueOf('#'), new ItemStack(Blocks.planks, 1, BlockPlanks.EnumType.SPRUCE.func_176839_a()) });
/* 146 */     addRecipe(new ItemStack(Blocks.jungle_stairs, 4), new Object[] { "#  ", "## ", "###", Character.valueOf('#'), new ItemStack(Blocks.planks, 1, BlockPlanks.EnumType.JUNGLE.func_176839_a()) });
/* 147 */     addRecipe(new ItemStack(Blocks.acacia_stairs, 4), new Object[] { "#  ", "## ", "###", Character.valueOf('#'), new ItemStack(Blocks.planks, 1, 4 + BlockPlanks.EnumType.ACACIA.func_176839_a() - 4) });
/* 148 */     addRecipe(new ItemStack(Blocks.dark_oak_stairs, 4), new Object[] { "#  ", "## ", "###", Character.valueOf('#'), new ItemStack(Blocks.planks, 1, 4 + BlockPlanks.EnumType.DARK_OAK.func_176839_a() - 4) });
/* 149 */     addRecipe(new ItemStack((Item)Items.fishing_rod, 1), new Object[] { "  #", " #X", "# X", Character.valueOf('#'), Items.stick, Character.valueOf('X'), Items.string });
/* 150 */     addRecipe(new ItemStack(Items.carrot_on_a_stick, 1), new Object[] { "# ", " X", Character.valueOf('#'), Items.fishing_rod, Character.valueOf('X'), Items.carrot }).func_92100_c();
/* 151 */     addRecipe(new ItemStack(Blocks.stone_stairs, 4), new Object[] { "#  ", "## ", "###", Character.valueOf('#'), Blocks.cobblestone });
/* 152 */     addRecipe(new ItemStack(Blocks.brick_stairs, 4), new Object[] { "#  ", "## ", "###", Character.valueOf('#'), Blocks.brick_block });
/* 153 */     addRecipe(new ItemStack(Blocks.stone_brick_stairs, 4), new Object[] { "#  ", "## ", "###", Character.valueOf('#'), Blocks.stonebrick });
/* 154 */     addRecipe(new ItemStack(Blocks.nether_brick_stairs, 4), new Object[] { "#  ", "## ", "###", Character.valueOf('#'), Blocks.nether_brick });
/* 155 */     addRecipe(new ItemStack(Blocks.sandstone_stairs, 4), new Object[] { "#  ", "## ", "###", Character.valueOf('#'), Blocks.sandstone });
/* 156 */     addRecipe(new ItemStack(Blocks.red_sandstone_stairs, 4), new Object[] { "#  ", "## ", "###", Character.valueOf('#'), Blocks.red_sandstone });
/* 157 */     addRecipe(new ItemStack(Blocks.quartz_stairs, 4), new Object[] { "#  ", "## ", "###", Character.valueOf('#'), Blocks.quartz_block });
/* 158 */     addRecipe(new ItemStack(Items.painting, 1), new Object[] { "###", "#X#", "###", Character.valueOf('#'), Items.stick, Character.valueOf('X'), Blocks.wool });
/* 159 */     addRecipe(new ItemStack(Items.item_frame, 1), new Object[] { "###", "#X#", "###", Character.valueOf('#'), Items.stick, Character.valueOf('X'), Items.leather });
/* 160 */     addRecipe(new ItemStack(Items.golden_apple, 1, 0), new Object[] { "###", "#X#", "###", Character.valueOf('#'), Items.gold_ingot, Character.valueOf('X'), Items.apple });
/* 161 */     addRecipe(new ItemStack(Items.golden_apple, 1, 1), new Object[] { "###", "#X#", "###", Character.valueOf('#'), Blocks.gold_block, Character.valueOf('X'), Items.apple });
/* 162 */     addRecipe(new ItemStack(Items.golden_carrot, 1, 0), new Object[] { "###", "#X#", "###", Character.valueOf('#'), Items.gold_nugget, Character.valueOf('X'), Items.carrot });
/* 163 */     addRecipe(new ItemStack(Items.speckled_melon, 1), new Object[] { "###", "#X#", "###", Character.valueOf('#'), Items.gold_nugget, Character.valueOf('X'), Items.melon });
/* 164 */     addRecipe(new ItemStack(Blocks.lever, 1), new Object[] { "X", "#", Character.valueOf('#'), Blocks.cobblestone, Character.valueOf('X'), Items.stick });
/* 165 */     addRecipe(new ItemStack((Block)Blocks.tripwire_hook, 2), new Object[] { "I", "S", "#", Character.valueOf('#'), Blocks.planks, Character.valueOf('S'), Items.stick, Character.valueOf('I'), Items.iron_ingot });
/* 166 */     addRecipe(new ItemStack(Blocks.redstone_torch, 1), new Object[] { "X", "#", Character.valueOf('#'), Items.stick, Character.valueOf('X'), Items.redstone });
/* 167 */     addRecipe(new ItemStack(Items.repeater, 1), new Object[] { "#X#", "III", Character.valueOf('#'), Blocks.redstone_torch, Character.valueOf('X'), Items.redstone, Character.valueOf('I'), new ItemStack(Blocks.stone, 1, BlockStone.EnumType.STONE.getMetaFromState()) });
/* 168 */     addRecipe(new ItemStack(Items.comparator, 1), new Object[] { " # ", "#X#", "III", Character.valueOf('#'), Blocks.redstone_torch, Character.valueOf('X'), Items.quartz, Character.valueOf('I'), new ItemStack(Blocks.stone, 1, BlockStone.EnumType.STONE.getMetaFromState()) });
/* 169 */     addRecipe(new ItemStack(Items.clock, 1), new Object[] { " # ", "#X#", " # ", Character.valueOf('#'), Items.gold_ingot, Character.valueOf('X'), Items.redstone });
/* 170 */     addRecipe(new ItemStack(Items.compass, 1), new Object[] { " # ", "#X#", " # ", Character.valueOf('#'), Items.iron_ingot, Character.valueOf('X'), Items.redstone });
/* 171 */     addRecipe(new ItemStack((Item)Items.map, 1), new Object[] { "###", "#X#", "###", Character.valueOf('#'), Items.paper, Character.valueOf('X'), Items.compass });
/* 172 */     addRecipe(new ItemStack(Blocks.stone_button, 1), new Object[] { "#", Character.valueOf('#'), new ItemStack(Blocks.stone, 1, BlockStone.EnumType.STONE.getMetaFromState()) });
/* 173 */     addRecipe(new ItemStack(Blocks.wooden_button, 1), new Object[] { "#", Character.valueOf('#'), Blocks.planks });
/* 174 */     addRecipe(new ItemStack(Blocks.stone_pressure_plate, 1), new Object[] { "##", Character.valueOf('#'), new ItemStack(Blocks.stone, 1, BlockStone.EnumType.STONE.getMetaFromState()) });
/* 175 */     addRecipe(new ItemStack(Blocks.wooden_pressure_plate, 1), new Object[] { "##", Character.valueOf('#'), Blocks.planks });
/* 176 */     addRecipe(new ItemStack(Blocks.heavy_weighted_pressure_plate, 1), new Object[] { "##", Character.valueOf('#'), Items.iron_ingot });
/* 177 */     addRecipe(new ItemStack(Blocks.light_weighted_pressure_plate, 1), new Object[] { "##", Character.valueOf('#'), Items.gold_ingot });
/* 178 */     addRecipe(new ItemStack(Blocks.dispenser, 1), new Object[] { "###", "#X#", "#R#", Character.valueOf('#'), Blocks.cobblestone, Character.valueOf('X'), Items.bow, Character.valueOf('R'), Items.redstone });
/* 179 */     addRecipe(new ItemStack(Blocks.dropper, 1), new Object[] { "###", "# #", "#R#", Character.valueOf('#'), Blocks.cobblestone, Character.valueOf('R'), Items.redstone });
/* 180 */     addRecipe(new ItemStack((Block)Blocks.piston, 1), new Object[] { "TTT", "#X#", "#R#", Character.valueOf('#'), Blocks.cobblestone, Character.valueOf('X'), Items.iron_ingot, Character.valueOf('R'), Items.redstone, Character.valueOf('T'), Blocks.planks });
/* 181 */     addRecipe(new ItemStack((Block)Blocks.sticky_piston, 1), new Object[] { "S", "P", Character.valueOf('S'), Items.slime_ball, Character.valueOf('P'), Blocks.piston });
/* 182 */     addRecipe(new ItemStack(Items.bed, 1), new Object[] { "###", "XXX", Character.valueOf('#'), Blocks.wool, Character.valueOf('X'), Blocks.planks });
/* 183 */     addRecipe(new ItemStack(Blocks.enchanting_table, 1), new Object[] { " B ", "D#D", "###", Character.valueOf('#'), Blocks.obsidian, Character.valueOf('B'), Items.book, Character.valueOf('D'), Items.diamond });
/* 184 */     addRecipe(new ItemStack(Blocks.anvil, 1), new Object[] { "III", " i ", "iii", Character.valueOf('I'), Blocks.iron_block, Character.valueOf('i'), Items.iron_ingot });
/* 185 */     addRecipe(new ItemStack(Items.leather), new Object[] { "##", "##", Character.valueOf('#'), Items.rabbit_hide });
/* 186 */     addShapelessRecipe(new ItemStack(Items.ender_eye, 1), new Object[] { Items.ender_pearl, Items.blaze_powder });
/* 187 */     addShapelessRecipe(new ItemStack(Items.fire_charge, 3), new Object[] { Items.gunpowder, Items.blaze_powder, Items.coal });
/* 188 */     addShapelessRecipe(new ItemStack(Items.fire_charge, 3), new Object[] { Items.gunpowder, Items.blaze_powder, new ItemStack(Items.coal, 1, 1) });
/* 189 */     addRecipe(new ItemStack((Block)Blocks.daylight_detector), new Object[] { "GGG", "QQQ", "WWW", Character.valueOf('G'), Blocks.glass, Character.valueOf('Q'), Items.quartz, Character.valueOf('W'), Blocks.wooden_slab });
/* 190 */     addRecipe(new ItemStack((Block)Blocks.hopper), new Object[] { "I I", "ICI", " I ", Character.valueOf('I'), Items.iron_ingot, Character.valueOf('C'), Blocks.chest });
/* 191 */     addRecipe(new ItemStack((Item)Items.armor_stand, 1), new Object[] { "///", " / ", "/_/", Character.valueOf('/'), Items.stick, Character.valueOf('_'), new ItemStack((Block)Blocks.stone_slab, 1, BlockStoneSlab.EnumType.STONE.func_176624_a()) });
/* 192 */     Collections.sort(this.recipes, new Comparator()
/*     */         {
/*     */           private static final String __OBFID = "CL_00000091";
/*     */           
/*     */           public int compare(IRecipe p_compare_1_, IRecipe p_compare_2_) {
/* 197 */             return (p_compare_1_ instanceof ShapelessRecipes && p_compare_2_ instanceof ShapedRecipes) ? 1 : ((p_compare_2_ instanceof ShapelessRecipes && p_compare_1_ instanceof ShapedRecipes) ? -1 : ((p_compare_2_.getRecipeSize() < p_compare_1_.getRecipeSize()) ? -1 : ((p_compare_2_.getRecipeSize() > p_compare_1_.getRecipeSize()) ? 1 : 0)));
/*     */           }
/*     */           
/*     */           public int compare(Object p_compare_1_, Object p_compare_2_) {
/* 201 */             return compare((IRecipe)p_compare_1_, (IRecipe)p_compare_2_);
/*     */           }
/*     */         });
/*     */   }
/*     */ 
/*     */   
/*     */   public ShapedRecipes addRecipe(ItemStack p_92103_1_, Object... p_92103_2_) {
/* 208 */     String var3 = "";
/* 209 */     int var4 = 0;
/* 210 */     int var5 = 0;
/* 211 */     int var6 = 0;
/*     */     
/* 213 */     if (p_92103_2_[var4] instanceof String[]) {
/*     */       
/* 215 */       String[] var7 = (String[])p_92103_2_[var4++];
/*     */       
/* 217 */       for (int var8 = 0; var8 < var7.length; var8++)
/*     */       {
/* 219 */         String var9 = var7[var8];
/* 220 */         var6++;
/* 221 */         var5 = var9.length();
/* 222 */         var3 = String.valueOf(var3) + var9;
/*     */       }
/*     */     
/*     */     } else {
/*     */       
/* 227 */       while (p_92103_2_[var4] instanceof String) {
/*     */         
/* 229 */         String var11 = (String)p_92103_2_[var4++];
/* 230 */         var6++;
/* 231 */         var5 = var11.length();
/* 232 */         var3 = String.valueOf(var3) + var11;
/*     */       } 
/*     */     } 
/*     */     
/*     */     HashMap<Character, ItemStack> var12;
/*     */     
/* 238 */     for (var12 = Maps.newHashMap(); var4 < p_92103_2_.length; var4 += 2) {
/*     */       
/* 240 */       Character var13 = (Character)p_92103_2_[var4];
/* 241 */       ItemStack var15 = null;
/*     */       
/* 243 */       if (p_92103_2_[var4 + 1] instanceof Item) {
/*     */         
/* 245 */         var15 = new ItemStack((Item)p_92103_2_[var4 + 1]);
/*     */       }
/* 247 */       else if (p_92103_2_[var4 + 1] instanceof Block) {
/*     */         
/* 249 */         var15 = new ItemStack((Block)p_92103_2_[var4 + 1], 1, 32767);
/*     */       }
/* 251 */       else if (p_92103_2_[var4 + 1] instanceof ItemStack) {
/*     */         
/* 253 */         var15 = (ItemStack)p_92103_2_[var4 + 1];
/*     */       } 
/*     */       
/* 256 */       var12.put(var13, var15);
/*     */     } 
/*     */     
/* 259 */     ItemStack[] var14 = new ItemStack[var5 * var6];
/*     */     
/* 261 */     for (int var16 = 0; var16 < var5 * var6; var16++) {
/*     */       
/* 263 */       char var10 = var3.charAt(var16);
/*     */       
/* 265 */       if (var12.containsKey(Character.valueOf(var10))) {
/*     */         
/* 267 */         var14[var16] = ((ItemStack)var12.get(Character.valueOf(var10))).copy();
/*     */       }
/*     */       else {
/*     */         
/* 271 */         var14[var16] = null;
/*     */       } 
/*     */     } 
/*     */     
/* 275 */     ShapedRecipes var17 = new ShapedRecipes(var5, var6, var14, p_92103_1_);
/* 276 */     this.recipes.add(var17);
/* 277 */     return var17;
/*     */   }
/*     */ 
/*     */   
/*     */   public void addShapelessRecipe(ItemStack p_77596_1_, Object... p_77596_2_) {
/* 282 */     ArrayList<ItemStack> var3 = Lists.newArrayList();
/* 283 */     Object[] var4 = p_77596_2_;
/* 284 */     int var5 = p_77596_2_.length;
/*     */     
/* 286 */     for (int var6 = 0; var6 < var5; var6++) {
/*     */       
/* 288 */       Object var7 = var4[var6];
/*     */       
/* 290 */       if (var7 instanceof ItemStack) {
/*     */         
/* 292 */         var3.add(((ItemStack)var7).copy());
/*     */       }
/* 294 */       else if (var7 instanceof Item) {
/*     */         
/* 296 */         var3.add(new ItemStack((Item)var7));
/*     */       }
/*     */       else {
/*     */         
/* 300 */         if (!(var7 instanceof Block))
/*     */         {
/* 302 */           throw new IllegalArgumentException("Invalid shapeless recipe: unknown type " + var7.getClass().getName() + "!");
/*     */         }
/*     */         
/* 305 */         var3.add(new ItemStack((Block)var7));
/*     */       } 
/*     */     } 
/*     */     
/* 309 */     this.recipes.add(new ShapelessRecipes(p_77596_1_, var3));
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_180302_a(IRecipe p_180302_1_) {
/* 314 */     this.recipes.add(p_180302_1_);
/*     */   }
/*     */   
/*     */   public ItemStack findMatchingRecipe(InventoryCrafting p_82787_1_, World worldIn) {
/*     */     IRecipe var4;
/* 319 */     Iterator<IRecipe> var3 = this.recipes.iterator();
/*     */ 
/*     */ 
/*     */     
/*     */     do {
/* 324 */       if (!var3.hasNext())
/*     */       {
/* 326 */         return null;
/*     */       }
/*     */       
/* 329 */       var4 = var3.next();
/*     */     }
/* 331 */     while (!var4.matches(p_82787_1_, worldIn));
/*     */     
/* 333 */     return var4.getCraftingResult(p_82787_1_);
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemStack[] func_180303_b(InventoryCrafting p_180303_1_, World worldIn) {
/* 338 */     Iterator<IRecipe> var3 = this.recipes.iterator();
/*     */     
/* 340 */     while (var3.hasNext()) {
/*     */       
/* 342 */       IRecipe var4 = var3.next();
/*     */       
/* 344 */       if (var4.matches(p_180303_1_, worldIn))
/*     */       {
/* 346 */         return var4.func_179532_b(p_180303_1_);
/*     */       }
/*     */     } 
/*     */     
/* 350 */     ItemStack[] var5 = new ItemStack[p_180303_1_.getSizeInventory()];
/*     */     
/* 352 */     for (int var6 = 0; var6 < var5.length; var6++)
/*     */     {
/* 354 */       var5[var6] = p_180303_1_.getStackInSlot(var6);
/*     */     }
/*     */     
/* 357 */     return var5;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List getRecipeList() {
/* 365 */     return this.recipes;
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\item\crafting\CraftingManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */