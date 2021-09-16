/*     */ package net.minecraft.item.crafting;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import net.minecraft.inventory.InventoryCrafting;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ShapelessRecipes
/*     */   implements IRecipe
/*     */ {
/*     */   private final ItemStack recipeOutput;
/*     */   private final List recipeItems;
/*     */   private static final String __OBFID = "CL_00000094";
/*     */   
/*     */   public ShapelessRecipes(ItemStack p_i1918_1_, List p_i1918_2_) {
/*  22 */     this.recipeOutput = p_i1918_1_;
/*  23 */     this.recipeItems = p_i1918_2_;
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemStack getRecipeOutput() {
/*  28 */     return this.recipeOutput;
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemStack[] func_179532_b(InventoryCrafting p_179532_1_) {
/*  33 */     ItemStack[] var2 = new ItemStack[p_179532_1_.getSizeInventory()];
/*     */     
/*  35 */     for (int var3 = 0; var3 < var2.length; var3++) {
/*     */       
/*  37 */       ItemStack var4 = p_179532_1_.getStackInSlot(var3);
/*     */       
/*  39 */       if (var4 != null && var4.getItem().hasContainerItem())
/*     */       {
/*  41 */         var2[var3] = new ItemStack(var4.getItem().getContainerItem());
/*     */       }
/*     */     } 
/*     */     
/*  45 */     return var2;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean matches(InventoryCrafting p_77569_1_, World worldIn) {
/*  53 */     ArrayList var3 = Lists.newArrayList(this.recipeItems);
/*     */     
/*  55 */     for (int var4 = 0; var4 < p_77569_1_.func_174923_h(); var4++) {
/*     */       
/*  57 */       for (int var5 = 0; var5 < p_77569_1_.func_174922_i(); var5++) {
/*     */         
/*  59 */         ItemStack var6 = p_77569_1_.getStackInRowAndColumn(var5, var4);
/*     */         
/*  61 */         if (var6 != null) {
/*     */           
/*  63 */           boolean var7 = false;
/*  64 */           Iterator<ItemStack> var8 = var3.iterator();
/*     */           
/*  66 */           while (var8.hasNext()) {
/*     */             
/*  68 */             ItemStack var9 = var8.next();
/*     */             
/*  70 */             if (var6.getItem() == var9.getItem() && (var9.getMetadata() == 32767 || var6.getMetadata() == var9.getMetadata())) {
/*     */               
/*  72 */               var7 = true;
/*  73 */               var3.remove(var9);
/*     */               
/*     */               break;
/*     */             } 
/*     */           } 
/*  78 */           if (!var7)
/*     */           {
/*  80 */             return false;
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/*  86 */     return var3.isEmpty();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack getCraftingResult(InventoryCrafting p_77572_1_) {
/*  94 */     return this.recipeOutput.copy();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getRecipeSize() {
/* 102 */     return this.recipeItems.size();
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\item\crafting\ShapelessRecipes.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */