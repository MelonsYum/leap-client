/*    */ package net.minecraft.item.crafting;
/*    */ 
/*    */ import net.minecraft.init.Items;
/*    */ import net.minecraft.inventory.InventoryCrafting;
/*    */ import net.minecraft.item.Item;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.nbt.NBTTagCompound;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraft.world.storage.MapData;
/*    */ 
/*    */ public class RecipesMapExtending
/*    */   extends ShapedRecipes {
/*    */   private static final String __OBFID = "CL_00000088";
/*    */   
/*    */   public RecipesMapExtending() {
/* 16 */     super(3, 3, new ItemStack[] { new ItemStack(Items.paper), new ItemStack(Items.paper), new ItemStack(Items.paper), new ItemStack(Items.paper), new ItemStack((Item)Items.filled_map, 0, 32767), new ItemStack(Items.paper), new ItemStack(Items.paper), new ItemStack(Items.paper), new ItemStack(Items.paper) }new ItemStack((Item)Items.map, 0, 0));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean matches(InventoryCrafting p_77569_1_, World worldIn) {
/* 24 */     if (!super.matches(p_77569_1_, worldIn))
/*    */     {
/* 26 */       return false;
/*    */     }
/*    */ 
/*    */     
/* 30 */     ItemStack var3 = null;
/*    */     
/* 32 */     for (int var4 = 0; var4 < p_77569_1_.getSizeInventory() && var3 == null; var4++) {
/*    */       
/* 34 */       ItemStack var5 = p_77569_1_.getStackInSlot(var4);
/*    */       
/* 36 */       if (var5 != null && var5.getItem() == Items.filled_map)
/*    */       {
/* 38 */         var3 = var5;
/*    */       }
/*    */     } 
/*    */     
/* 42 */     if (var3 == null)
/*    */     {
/* 44 */       return false;
/*    */     }
/*    */ 
/*    */     
/* 48 */     MapData var6 = Items.filled_map.getMapData(var3, worldIn);
/* 49 */     return (var6 == null) ? false : ((var6.scale < 4));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public ItemStack getCraftingResult(InventoryCrafting p_77572_1_) {
/* 59 */     ItemStack var2 = null;
/*    */     
/* 61 */     for (int var3 = 0; var3 < p_77572_1_.getSizeInventory() && var2 == null; var3++) {
/*    */       
/* 63 */       ItemStack var4 = p_77572_1_.getStackInSlot(var3);
/*    */       
/* 65 */       if (var4 != null && var4.getItem() == Items.filled_map)
/*    */       {
/* 67 */         var2 = var4;
/*    */       }
/*    */     } 
/*    */     
/* 71 */     var2 = var2.copy();
/* 72 */     var2.stackSize = 1;
/*    */     
/* 74 */     if (var2.getTagCompound() == null)
/*    */     {
/* 76 */       var2.setTagCompound(new NBTTagCompound());
/*    */     }
/*    */     
/* 79 */     var2.getTagCompound().setBoolean("map_is_scaling", true);
/* 80 */     return var2;
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\item\crafting\RecipesMapExtending.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */