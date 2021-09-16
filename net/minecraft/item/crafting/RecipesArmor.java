/*    */ package net.minecraft.item.crafting;
/*    */ 
/*    */ import net.minecraft.init.Items;
/*    */ import net.minecraft.item.Item;
/*    */ import net.minecraft.item.ItemStack;
/*    */ 
/*    */ public class RecipesArmor
/*    */ {
/*  9 */   private String[][] recipePatterns = new String[][] { { "XXX", "X X" }, { "X X", "XXX", "XXX" }, { "XXX", "X X", "X X" }, { "X X", "X X" } };
/*    */   
/*    */   private Item[][] recipeItems;
/*    */   private static final String __OBFID = "CL_00000080";
/*    */   
/*    */   public RecipesArmor() {
/* 15 */     this.recipeItems = new Item[][] { { Items.leather, Items.iron_ingot, Items.diamond, Items.gold_ingot }, { (Item)Items.leather_helmet, (Item)Items.iron_helmet, (Item)Items.diamond_helmet, (Item)Items.golden_helmet }, { (Item)Items.leather_chestplate, (Item)Items.iron_chestplate, (Item)Items.diamond_chestplate, (Item)Items.golden_chestplate }, { (Item)Items.leather_leggings, (Item)Items.iron_leggings, (Item)Items.diamond_leggings, (Item)Items.golden_leggings }, { (Item)Items.leather_boots, (Item)Items.iron_boots, (Item)Items.diamond_boots, (Item)Items.golden_boots } };
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void addRecipes(CraftingManager p_77609_1_) {
/* 23 */     for (int var2 = 0; var2 < (this.recipeItems[0]).length; var2++) {
/*    */       
/* 25 */       Item var3 = this.recipeItems[0][var2];
/*    */       
/* 27 */       for (int var4 = 0; var4 < this.recipeItems.length - 1; var4++) {
/*    */         
/* 29 */         Item var5 = this.recipeItems[var4 + 1][var2];
/* 30 */         p_77609_1_.addRecipe(new ItemStack(var5), new Object[] { this.recipePatterns[var4], Character.valueOf('X'), var3 });
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\item\crafting\RecipesArmor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */