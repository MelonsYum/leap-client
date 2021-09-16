/*    */ package net.minecraft.item.crafting;
/*    */ 
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.block.BlockDirt;
/*    */ import net.minecraft.block.BlockPrismarine;
/*    */ import net.minecraft.block.BlockQuartz;
/*    */ import net.minecraft.block.BlockRedSandstone;
/*    */ import net.minecraft.block.BlockSand;
/*    */ import net.minecraft.block.BlockSandStone;
/*    */ import net.minecraft.block.BlockStone;
/*    */ import net.minecraft.block.BlockStoneBrick;
/*    */ import net.minecraft.block.BlockStoneSlab;
/*    */ import net.minecraft.block.BlockStoneSlabNew;
/*    */ import net.minecraft.init.Blocks;
/*    */ import net.minecraft.init.Items;
/*    */ import net.minecraft.item.EnumDyeColor;
/*    */ import net.minecraft.item.ItemStack;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class RecipesCrafting
/*    */ {
/*    */   private static final String __OBFID = "CL_00000095";
/*    */   
/*    */   public void addRecipes(CraftingManager p_77589_1_) {
/* 27 */     p_77589_1_.addRecipe(new ItemStack((Block)Blocks.chest), new Object[] { "###", "# #", "###", Character.valueOf('#'), Blocks.planks });
/* 28 */     p_77589_1_.addRecipe(new ItemStack(Blocks.trapped_chest), new Object[] { "#-", Character.valueOf('#'), Blocks.chest, Character.valueOf('-'), Blocks.tripwire_hook });
/* 29 */     p_77589_1_.addRecipe(new ItemStack(Blocks.ender_chest), new Object[] { "###", "#E#", "###", Character.valueOf('#'), Blocks.obsidian, Character.valueOf('E'), Items.ender_eye });
/* 30 */     p_77589_1_.addRecipe(new ItemStack(Blocks.furnace), new Object[] { "###", "# #", "###", Character.valueOf('#'), Blocks.cobblestone });
/* 31 */     p_77589_1_.addRecipe(new ItemStack(Blocks.crafting_table), new Object[] { "##", "##", Character.valueOf('#'), Blocks.planks });
/* 32 */     p_77589_1_.addRecipe(new ItemStack(Blocks.sandstone), new Object[] { "##", "##", Character.valueOf('#'), new ItemStack((Block)Blocks.sand, 1, BlockSand.EnumType.SAND.func_176688_a()) });
/* 33 */     p_77589_1_.addRecipe(new ItemStack(Blocks.red_sandstone), new Object[] { "##", "##", Character.valueOf('#'), new ItemStack((Block)Blocks.sand, 1, BlockSand.EnumType.RED_SAND.func_176688_a()) });
/* 34 */     p_77589_1_.addRecipe(new ItemStack(Blocks.sandstone, 4, BlockSandStone.EnumType.SMOOTH.func_176675_a()), new Object[] { "##", "##", Character.valueOf('#'), new ItemStack(Blocks.sandstone, 1, BlockSandStone.EnumType.DEFAULT.func_176675_a()) });
/* 35 */     p_77589_1_.addRecipe(new ItemStack(Blocks.red_sandstone, 4, BlockRedSandstone.EnumType.SMOOTH.getMetaFromState()), new Object[] { "##", "##", Character.valueOf('#'), new ItemStack(Blocks.red_sandstone, 1, BlockRedSandstone.EnumType.DEFAULT.getMetaFromState()) });
/* 36 */     p_77589_1_.addRecipe(new ItemStack(Blocks.sandstone, 1, BlockSandStone.EnumType.CHISELED.func_176675_a()), new Object[] { "#", "#", Character.valueOf('#'), new ItemStack((Block)Blocks.stone_slab, 1, BlockStoneSlab.EnumType.SAND.func_176624_a()) });
/* 37 */     p_77589_1_.addRecipe(new ItemStack(Blocks.red_sandstone, 1, BlockRedSandstone.EnumType.CHISELED.getMetaFromState()), new Object[] { "#", "#", Character.valueOf('#'), new ItemStack((Block)Blocks.stone_slab2, 1, BlockStoneSlabNew.EnumType.RED_SANDSTONE.func_176915_a()) });
/* 38 */     p_77589_1_.addRecipe(new ItemStack(Blocks.quartz_block, 1, BlockQuartz.EnumType.CHISELED.getMetaFromState()), new Object[] { "#", "#", Character.valueOf('#'), new ItemStack((Block)Blocks.stone_slab, 1, BlockStoneSlab.EnumType.QUARTZ.func_176624_a()) });
/* 39 */     p_77589_1_.addRecipe(new ItemStack(Blocks.quartz_block, 2, BlockQuartz.EnumType.LINES_Y.getMetaFromState()), new Object[] { "#", "#", Character.valueOf('#'), new ItemStack(Blocks.quartz_block, 1, BlockQuartz.EnumType.DEFAULT.getMetaFromState()) });
/* 40 */     p_77589_1_.addRecipe(new ItemStack(Blocks.stonebrick, 4), new Object[] { "##", "##", Character.valueOf('#'), new ItemStack(Blocks.stone, 1, BlockStone.EnumType.STONE.getMetaFromState()) });
/* 41 */     p_77589_1_.addRecipe(new ItemStack(Blocks.stonebrick, 1, BlockStoneBrick.CHISELED_META), new Object[] { "#", "#", Character.valueOf('#'), new ItemStack((Block)Blocks.stone_slab, 1, BlockStoneSlab.EnumType.SMOOTHBRICK.func_176624_a()) });
/* 42 */     p_77589_1_.addShapelessRecipe(new ItemStack(Blocks.stonebrick, 1, BlockStoneBrick.MOSSY_META), new Object[] { Blocks.stonebrick, Blocks.vine });
/* 43 */     p_77589_1_.addShapelessRecipe(new ItemStack(Blocks.mossy_cobblestone, 1), new Object[] { Blocks.cobblestone, Blocks.vine });
/* 44 */     p_77589_1_.addRecipe(new ItemStack(Blocks.iron_bars, 16), new Object[] { "###", "###", Character.valueOf('#'), Items.iron_ingot });
/* 45 */     p_77589_1_.addRecipe(new ItemStack(Blocks.glass_pane, 16), new Object[] { "###", "###", Character.valueOf('#'), Blocks.glass });
/* 46 */     p_77589_1_.addRecipe(new ItemStack(Blocks.redstone_lamp, 1), new Object[] { " R ", "RGR", " R ", Character.valueOf('R'), Items.redstone, Character.valueOf('G'), Blocks.glowstone });
/* 47 */     p_77589_1_.addRecipe(new ItemStack((Block)Blocks.beacon, 1), new Object[] { "GGG", "GSG", "OOO", Character.valueOf('G'), Blocks.glass, Character.valueOf('S'), Items.nether_star, Character.valueOf('O'), Blocks.obsidian });
/* 48 */     p_77589_1_.addRecipe(new ItemStack(Blocks.nether_brick, 1), new Object[] { "NN", "NN", Character.valueOf('N'), Items.netherbrick });
/* 49 */     p_77589_1_.addRecipe(new ItemStack(Blocks.stone, 2, BlockStone.EnumType.DIORITE.getMetaFromState()), new Object[] { "CQ", "QC", Character.valueOf('C'), Blocks.cobblestone, Character.valueOf('Q'), Items.quartz });
/* 50 */     p_77589_1_.addShapelessRecipe(new ItemStack(Blocks.stone, 1, BlockStone.EnumType.GRANITE.getMetaFromState()), new Object[] { new ItemStack(Blocks.stone, 1, BlockStone.EnumType.DIORITE.getMetaFromState()), Items.quartz });
/* 51 */     p_77589_1_.addShapelessRecipe(new ItemStack(Blocks.stone, 2, BlockStone.EnumType.ANDESITE.getMetaFromState()), new Object[] { new ItemStack(Blocks.stone, 1, BlockStone.EnumType.DIORITE.getMetaFromState()), Blocks.cobblestone });
/* 52 */     p_77589_1_.addRecipe(new ItemStack(Blocks.dirt, 4, BlockDirt.DirtType.COARSE_DIRT.getMetadata()), new Object[] { "DG", "GD", Character.valueOf('D'), new ItemStack(Blocks.dirt, 1, BlockDirt.DirtType.DIRT.getMetadata()), Character.valueOf('G'), Blocks.gravel });
/* 53 */     p_77589_1_.addRecipe(new ItemStack(Blocks.stone, 4, BlockStone.EnumType.DIORITE_SMOOTH.getMetaFromState()), new Object[] { "SS", "SS", Character.valueOf('S'), new ItemStack(Blocks.stone, 1, BlockStone.EnumType.DIORITE.getMetaFromState()) });
/* 54 */     p_77589_1_.addRecipe(new ItemStack(Blocks.stone, 4, BlockStone.EnumType.GRANITE_SMOOTH.getMetaFromState()), new Object[] { "SS", "SS", Character.valueOf('S'), new ItemStack(Blocks.stone, 1, BlockStone.EnumType.GRANITE.getMetaFromState()) });
/* 55 */     p_77589_1_.addRecipe(new ItemStack(Blocks.stone, 4, BlockStone.EnumType.ANDESITE_SMOOTH.getMetaFromState()), new Object[] { "SS", "SS", Character.valueOf('S'), new ItemStack(Blocks.stone, 1, BlockStone.EnumType.ANDESITE.getMetaFromState()) });
/* 56 */     p_77589_1_.addRecipe(new ItemStack(Blocks.prismarine, 1, BlockPrismarine.ROUGHMETA), new Object[] { "SS", "SS", Character.valueOf('S'), Items.prismarine_shard });
/* 57 */     p_77589_1_.addRecipe(new ItemStack(Blocks.prismarine, 1, BlockPrismarine.BRICKSMETA), new Object[] { "SSS", "SSS", "SSS", Character.valueOf('S'), Items.prismarine_shard });
/* 58 */     p_77589_1_.addRecipe(new ItemStack(Blocks.prismarine, 1, BlockPrismarine.DARKMETA), new Object[] { "SSS", "SIS", "SSS", Character.valueOf('S'), Items.prismarine_shard, Character.valueOf('I'), new ItemStack(Items.dye, 1, EnumDyeColor.BLACK.getDyeColorDamage()) });
/* 59 */     p_77589_1_.addRecipe(new ItemStack(Blocks.sea_lantern, 1, 0), new Object[] { "SCS", "CCC", "SCS", Character.valueOf('S'), Items.prismarine_shard, Character.valueOf('C'), Items.prismarine_crystals });
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\item\crafting\RecipesCrafting.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */