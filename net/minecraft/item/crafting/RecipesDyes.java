/*    */ package net.minecraft.item.crafting;
/*    */ 
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.block.BlockDoublePlant;
/*    */ import net.minecraft.block.BlockFlower;
/*    */ import net.minecraft.init.Blocks;
/*    */ import net.minecraft.init.Items;
/*    */ import net.minecraft.item.EnumDyeColor;
/*    */ import net.minecraft.item.Item;
/*    */ import net.minecraft.item.ItemStack;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class RecipesDyes
/*    */ {
/*    */   private static final String __OBFID = "CL_00000082";
/*    */   
/*    */   public void addRecipes(CraftingManager p_77607_1_) {
/*    */     int var2;
/* 22 */     for (var2 = 0; var2 < 16; var2++) {
/*    */       
/* 24 */       p_77607_1_.addShapelessRecipe(new ItemStack(Blocks.wool, 1, var2), new Object[] { new ItemStack(Items.dye, 1, 15 - var2), new ItemStack(Item.getItemFromBlock(Blocks.wool), 1, 0) });
/* 25 */       p_77607_1_.addRecipe(new ItemStack(Blocks.stained_hardened_clay, 8, 15 - var2), new Object[] { "###", "#X#", "###", Character.valueOf('#'), new ItemStack(Blocks.hardened_clay), Character.valueOf('X'), new ItemStack(Items.dye, 1, var2) });
/* 26 */       p_77607_1_.addRecipe(new ItemStack((Block)Blocks.stained_glass, 8, 15 - var2), new Object[] { "###", "#X#", "###", Character.valueOf('#'), new ItemStack(Blocks.glass), Character.valueOf('X'), new ItemStack(Items.dye, 1, var2) });
/* 27 */       p_77607_1_.addRecipe(new ItemStack((Block)Blocks.stained_glass_pane, 16, var2), new Object[] { "###", "###", Character.valueOf('#'), new ItemStack((Block)Blocks.stained_glass, 1, var2) });
/*    */     } 
/*    */     
/* 30 */     p_77607_1_.addShapelessRecipe(new ItemStack(Items.dye, 1, EnumDyeColor.YELLOW.getDyeColorDamage()), new Object[] { new ItemStack((Block)Blocks.yellow_flower, 1, BlockFlower.EnumFlowerType.DANDELION.func_176968_b()) });
/* 31 */     p_77607_1_.addShapelessRecipe(new ItemStack(Items.dye, 1, EnumDyeColor.RED.getDyeColorDamage()), new Object[] { new ItemStack((Block)Blocks.red_flower, 1, BlockFlower.EnumFlowerType.POPPY.func_176968_b()) });
/* 32 */     p_77607_1_.addShapelessRecipe(new ItemStack(Items.dye, 3, EnumDyeColor.WHITE.getDyeColorDamage()), new Object[] { Items.bone });
/* 33 */     p_77607_1_.addShapelessRecipe(new ItemStack(Items.dye, 2, EnumDyeColor.PINK.getDyeColorDamage()), new Object[] { new ItemStack(Items.dye, 1, EnumDyeColor.RED.getDyeColorDamage()), new ItemStack(Items.dye, 1, EnumDyeColor.WHITE.getDyeColorDamage()) });
/* 34 */     p_77607_1_.addShapelessRecipe(new ItemStack(Items.dye, 2, EnumDyeColor.ORANGE.getDyeColorDamage()), new Object[] { new ItemStack(Items.dye, 1, EnumDyeColor.RED.getDyeColorDamage()), new ItemStack(Items.dye, 1, EnumDyeColor.YELLOW.getDyeColorDamage()) });
/* 35 */     p_77607_1_.addShapelessRecipe(new ItemStack(Items.dye, 2, EnumDyeColor.LIME.getDyeColorDamage()), new Object[] { new ItemStack(Items.dye, 1, EnumDyeColor.GREEN.getDyeColorDamage()), new ItemStack(Items.dye, 1, EnumDyeColor.WHITE.getDyeColorDamage()) });
/* 36 */     p_77607_1_.addShapelessRecipe(new ItemStack(Items.dye, 2, EnumDyeColor.GRAY.getDyeColorDamage()), new Object[] { new ItemStack(Items.dye, 1, EnumDyeColor.BLACK.getDyeColorDamage()), new ItemStack(Items.dye, 1, EnumDyeColor.WHITE.getDyeColorDamage()) });
/* 37 */     p_77607_1_.addShapelessRecipe(new ItemStack(Items.dye, 2, EnumDyeColor.SILVER.getDyeColorDamage()), new Object[] { new ItemStack(Items.dye, 1, EnumDyeColor.GRAY.getDyeColorDamage()), new ItemStack(Items.dye, 1, EnumDyeColor.WHITE.getDyeColorDamage()) });
/* 38 */     p_77607_1_.addShapelessRecipe(new ItemStack(Items.dye, 3, EnumDyeColor.SILVER.getDyeColorDamage()), new Object[] { new ItemStack(Items.dye, 1, EnumDyeColor.BLACK.getDyeColorDamage()), new ItemStack(Items.dye, 1, EnumDyeColor.WHITE.getDyeColorDamage()), new ItemStack(Items.dye, 1, EnumDyeColor.WHITE.getDyeColorDamage()) });
/* 39 */     p_77607_1_.addShapelessRecipe(new ItemStack(Items.dye, 2, EnumDyeColor.LIGHT_BLUE.getDyeColorDamage()), new Object[] { new ItemStack(Items.dye, 1, EnumDyeColor.BLUE.getDyeColorDamage()), new ItemStack(Items.dye, 1, EnumDyeColor.WHITE.getDyeColorDamage()) });
/* 40 */     p_77607_1_.addShapelessRecipe(new ItemStack(Items.dye, 2, EnumDyeColor.CYAN.getDyeColorDamage()), new Object[] { new ItemStack(Items.dye, 1, EnumDyeColor.BLUE.getDyeColorDamage()), new ItemStack(Items.dye, 1, EnumDyeColor.GREEN.getDyeColorDamage()) });
/* 41 */     p_77607_1_.addShapelessRecipe(new ItemStack(Items.dye, 2, EnumDyeColor.PURPLE.getDyeColorDamage()), new Object[] { new ItemStack(Items.dye, 1, EnumDyeColor.BLUE.getDyeColorDamage()), new ItemStack(Items.dye, 1, EnumDyeColor.RED.getDyeColorDamage()) });
/* 42 */     p_77607_1_.addShapelessRecipe(new ItemStack(Items.dye, 2, EnumDyeColor.MAGENTA.getDyeColorDamage()), new Object[] { new ItemStack(Items.dye, 1, EnumDyeColor.PURPLE.getDyeColorDamage()), new ItemStack(Items.dye, 1, EnumDyeColor.PINK.getDyeColorDamage()) });
/* 43 */     p_77607_1_.addShapelessRecipe(new ItemStack(Items.dye, 3, EnumDyeColor.MAGENTA.getDyeColorDamage()), new Object[] { new ItemStack(Items.dye, 1, EnumDyeColor.BLUE.getDyeColorDamage()), new ItemStack(Items.dye, 1, EnumDyeColor.RED.getDyeColorDamage()), new ItemStack(Items.dye, 1, EnumDyeColor.PINK.getDyeColorDamage()) });
/* 44 */     p_77607_1_.addShapelessRecipe(new ItemStack(Items.dye, 4, EnumDyeColor.MAGENTA.getDyeColorDamage()), new Object[] { new ItemStack(Items.dye, 1, EnumDyeColor.BLUE.getDyeColorDamage()), new ItemStack(Items.dye, 1, EnumDyeColor.RED.getDyeColorDamage()), new ItemStack(Items.dye, 1, EnumDyeColor.RED.getDyeColorDamage()), new ItemStack(Items.dye, 1, EnumDyeColor.WHITE.getDyeColorDamage()) });
/* 45 */     p_77607_1_.addShapelessRecipe(new ItemStack(Items.dye, 1, EnumDyeColor.LIGHT_BLUE.getDyeColorDamage()), new Object[] { new ItemStack((Block)Blocks.red_flower, 1, BlockFlower.EnumFlowerType.BLUE_ORCHID.func_176968_b()) });
/* 46 */     p_77607_1_.addShapelessRecipe(new ItemStack(Items.dye, 1, EnumDyeColor.MAGENTA.getDyeColorDamage()), new Object[] { new ItemStack((Block)Blocks.red_flower, 1, BlockFlower.EnumFlowerType.ALLIUM.func_176968_b()) });
/* 47 */     p_77607_1_.addShapelessRecipe(new ItemStack(Items.dye, 1, EnumDyeColor.SILVER.getDyeColorDamage()), new Object[] { new ItemStack((Block)Blocks.red_flower, 1, BlockFlower.EnumFlowerType.HOUSTONIA.func_176968_b()) });
/* 48 */     p_77607_1_.addShapelessRecipe(new ItemStack(Items.dye, 1, EnumDyeColor.RED.getDyeColorDamage()), new Object[] { new ItemStack((Block)Blocks.red_flower, 1, BlockFlower.EnumFlowerType.RED_TULIP.func_176968_b()) });
/* 49 */     p_77607_1_.addShapelessRecipe(new ItemStack(Items.dye, 1, EnumDyeColor.ORANGE.getDyeColorDamage()), new Object[] { new ItemStack((Block)Blocks.red_flower, 1, BlockFlower.EnumFlowerType.ORANGE_TULIP.func_176968_b()) });
/* 50 */     p_77607_1_.addShapelessRecipe(new ItemStack(Items.dye, 1, EnumDyeColor.SILVER.getDyeColorDamage()), new Object[] { new ItemStack((Block)Blocks.red_flower, 1, BlockFlower.EnumFlowerType.WHITE_TULIP.func_176968_b()) });
/* 51 */     p_77607_1_.addShapelessRecipe(new ItemStack(Items.dye, 1, EnumDyeColor.PINK.getDyeColorDamage()), new Object[] { new ItemStack((Block)Blocks.red_flower, 1, BlockFlower.EnumFlowerType.PINK_TULIP.func_176968_b()) });
/* 52 */     p_77607_1_.addShapelessRecipe(new ItemStack(Items.dye, 1, EnumDyeColor.SILVER.getDyeColorDamage()), new Object[] { new ItemStack((Block)Blocks.red_flower, 1, BlockFlower.EnumFlowerType.OXEYE_DAISY.func_176968_b()) });
/* 53 */     p_77607_1_.addShapelessRecipe(new ItemStack(Items.dye, 2, EnumDyeColor.YELLOW.getDyeColorDamage()), new Object[] { new ItemStack((Block)Blocks.double_plant, 1, BlockDoublePlant.EnumPlantType.SUNFLOWER.func_176936_a()) });
/* 54 */     p_77607_1_.addShapelessRecipe(new ItemStack(Items.dye, 2, EnumDyeColor.MAGENTA.getDyeColorDamage()), new Object[] { new ItemStack((Block)Blocks.double_plant, 1, BlockDoublePlant.EnumPlantType.SYRINGA.func_176936_a()) });
/* 55 */     p_77607_1_.addShapelessRecipe(new ItemStack(Items.dye, 2, EnumDyeColor.RED.getDyeColorDamage()), new Object[] { new ItemStack((Block)Blocks.double_plant, 1, BlockDoublePlant.EnumPlantType.ROSE.func_176936_a()) });
/* 56 */     p_77607_1_.addShapelessRecipe(new ItemStack(Items.dye, 2, EnumDyeColor.PINK.getDyeColorDamage()), new Object[] { new ItemStack((Block)Blocks.double_plant, 1, BlockDoublePlant.EnumPlantType.PAEONIA.func_176936_a()) });
/*    */     
/* 58 */     for (var2 = 0; var2 < 16; var2++) {
/*    */       
/* 60 */       p_77607_1_.addRecipe(new ItemStack(Blocks.carpet, 3, var2), new Object[] { "##", Character.valueOf('#'), new ItemStack(Blocks.wool, 1, var2) });
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\item\crafting\RecipesDyes.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */