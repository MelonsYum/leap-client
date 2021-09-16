/*    */ package net.minecraft.item.crafting;
/*    */ 
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.init.Blocks;
/*    */ import net.minecraft.init.Items;
/*    */ import net.minecraft.item.EnumDyeColor;
/*    */ import net.minecraft.item.ItemStack;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class RecipesIngots
/*    */ {
/* 16 */   private Object[][] recipeItems = new Object[][] { { Blocks.gold_block, new ItemStack(Items.gold_ingot, 9) }, { Blocks.iron_block, new ItemStack(Items.iron_ingot, 9) }, { Blocks.diamond_block, new ItemStack(Items.diamond, 9) }, { Blocks.emerald_block, new ItemStack(Items.emerald, 9) }, { Blocks.lapis_block, new ItemStack(Items.dye, 9, EnumDyeColor.BLUE.getDyeColorDamage()) }, { Blocks.redstone_block, new ItemStack(Items.redstone, 9) }, { Blocks.coal_block, new ItemStack(Items.coal, 9, 0) }, { Blocks.hay_block, new ItemStack(Items.wheat, 9) }, { Blocks.slime_block, new ItemStack(Items.slime_ball, 9) } };
/*    */ 
/*    */   
/*    */   private static final String __OBFID = "CL_00000089";
/*    */ 
/*    */ 
/*    */   
/*    */   public void addRecipes(CraftingManager p_77590_1_) {
/* 24 */     for (int var2 = 0; var2 < this.recipeItems.length; var2++) {
/*    */       
/* 26 */       Block var3 = (Block)this.recipeItems[var2][0];
/* 27 */       ItemStack var4 = (ItemStack)this.recipeItems[var2][1];
/* 28 */       p_77590_1_.addRecipe(new ItemStack(var3), new Object[] { "###", "###", "###", Character.valueOf('#'), var4 });
/* 29 */       p_77590_1_.addRecipe(var4, new Object[] { "#", Character.valueOf('#'), var3 });
/*    */     } 
/*    */     
/* 32 */     p_77590_1_.addRecipe(new ItemStack(Items.gold_ingot), new Object[] { "###", "###", "###", Character.valueOf('#'), Items.gold_nugget });
/* 33 */     p_77590_1_.addRecipe(new ItemStack(Items.gold_nugget, 9), new Object[] { "#", Character.valueOf('#'), Items.gold_ingot });
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\item\crafting\RecipesIngots.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */