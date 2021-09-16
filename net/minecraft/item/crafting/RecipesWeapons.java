/*    */ package net.minecraft.item.crafting;
/*    */ 
/*    */ import net.minecraft.init.Blocks;
/*    */ import net.minecraft.init.Items;
/*    */ import net.minecraft.item.Item;
/*    */ import net.minecraft.item.ItemStack;
/*    */ 
/*    */ public class RecipesWeapons
/*    */ {
/* 10 */   private String[][] recipePatterns = new String[][] { { "X", "X", "#" } };
/*    */   
/*    */   private Object[][] recipeItems;
/*    */   private static final String __OBFID = "CL_00000097";
/*    */   
/*    */   public RecipesWeapons() {
/* 16 */     this.recipeItems = new Object[][] { { Blocks.planks, Blocks.cobblestone, Items.iron_ingot, Items.diamond, Items.gold_ingot }, { Items.wooden_sword, Items.stone_sword, Items.iron_sword, Items.diamond_sword, Items.golden_sword } };
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void addRecipes(CraftingManager p_77583_1_) {
/* 24 */     for (int var2 = 0; var2 < (this.recipeItems[0]).length; var2++) {
/*    */       
/* 26 */       Object var3 = this.recipeItems[0][var2];
/*    */       
/* 28 */       for (int var4 = 0; var4 < this.recipeItems.length - 1; var4++) {
/*    */         
/* 30 */         Item var5 = (Item)this.recipeItems[var4 + 1][var2];
/* 31 */         p_77583_1_.addRecipe(new ItemStack(var5), new Object[] { this.recipePatterns[var4], Character.valueOf('#'), Items.stick, Character.valueOf('X'), var3 });
/*    */       } 
/*    */     } 
/*    */     
/* 35 */     p_77583_1_.addRecipe(new ItemStack((Item)Items.bow, 1), new Object[] { " #X", "# X", " #X", Character.valueOf('X'), Items.string, Character.valueOf('#'), Items.stick });
/* 36 */     p_77583_1_.addRecipe(new ItemStack(Items.arrow, 4), new Object[] { "X", "#", "Y", Character.valueOf('Y'), Items.feather, Character.valueOf('X'), Items.flint, Character.valueOf('#'), Items.stick });
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\item\crafting\RecipesWeapons.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */