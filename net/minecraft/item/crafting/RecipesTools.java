/*    */ package net.minecraft.item.crafting;
/*    */ 
/*    */ import net.minecraft.init.Blocks;
/*    */ import net.minecraft.init.Items;
/*    */ import net.minecraft.item.Item;
/*    */ import net.minecraft.item.ItemStack;
/*    */ 
/*    */ public class RecipesTools
/*    */ {
/* 10 */   private String[][] recipePatterns = new String[][] { { "XXX", " # ", " # " }, { "X", "#", "#" }, { "XX", "X#", " #" }, { "XX", " #", " #" } };
/*    */   
/*    */   private Object[][] recipeItems;
/*    */   private static final String __OBFID = "CL_00000096";
/*    */   
/*    */   public RecipesTools() {
/* 16 */     this.recipeItems = new Object[][] { { Blocks.planks, Blocks.cobblestone, Items.iron_ingot, Items.diamond, Items.gold_ingot }, { Items.wooden_pickaxe, Items.stone_pickaxe, Items.iron_pickaxe, Items.diamond_pickaxe, Items.golden_pickaxe }, { Items.wooden_shovel, Items.stone_shovel, Items.iron_shovel, Items.diamond_shovel, Items.golden_shovel }, { Items.wooden_axe, Items.stone_axe, Items.iron_axe, Items.diamond_axe, Items.golden_axe }, { Items.wooden_hoe, Items.stone_hoe, Items.iron_hoe, Items.diamond_hoe, Items.golden_hoe } };
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void addRecipes(CraftingManager p_77586_1_) {
/* 24 */     for (int var2 = 0; var2 < (this.recipeItems[0]).length; var2++) {
/*    */       
/* 26 */       Object var3 = this.recipeItems[0][var2];
/*    */       
/* 28 */       for (int var4 = 0; var4 < this.recipeItems.length - 1; var4++) {
/*    */         
/* 30 */         Item var5 = (Item)this.recipeItems[var4 + 1][var2];
/* 31 */         p_77586_1_.addRecipe(new ItemStack(var5), new Object[] { this.recipePatterns[var4], Character.valueOf('#'), Items.stick, Character.valueOf('X'), var3 });
/*    */       } 
/*    */     } 
/*    */     
/* 35 */     p_77586_1_.addRecipe(new ItemStack((Item)Items.shears), new Object[] { " #", "# ", Character.valueOf('#'), Items.iron_ingot });
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\item\crafting\RecipesTools.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */