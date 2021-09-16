/*     */ package net.minecraft.item.crafting;
/*     */ 
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.inventory.InventoryCrafting;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class RecipesMapCloning
/*     */   implements IRecipe
/*     */ {
/*     */   private static final String __OBFID = "CL_00000087";
/*     */   
/*     */   public boolean matches(InventoryCrafting p_77569_1_, World worldIn) {
/*  17 */     int var3 = 0;
/*  18 */     ItemStack var4 = null;
/*     */     
/*  20 */     for (int var5 = 0; var5 < p_77569_1_.getSizeInventory(); var5++) {
/*     */       
/*  22 */       ItemStack var6 = p_77569_1_.getStackInSlot(var5);
/*     */       
/*  24 */       if (var6 != null)
/*     */       {
/*  26 */         if (var6.getItem() == Items.filled_map) {
/*     */           
/*  28 */           if (var4 != null)
/*     */           {
/*  30 */             return false;
/*     */           }
/*     */           
/*  33 */           var4 = var6;
/*     */         }
/*     */         else {
/*     */           
/*  37 */           if (var6.getItem() != Items.map)
/*     */           {
/*  39 */             return false;
/*     */           }
/*     */           
/*  42 */           var3++;
/*     */         } 
/*     */       }
/*     */     } 
/*     */     
/*  47 */     return (var4 != null && var3 > 0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack getCraftingResult(InventoryCrafting p_77572_1_) {
/*  55 */     int var2 = 0;
/*  56 */     ItemStack var3 = null;
/*     */     
/*  58 */     for (int var4 = 0; var4 < p_77572_1_.getSizeInventory(); var4++) {
/*     */       
/*  60 */       ItemStack var5 = p_77572_1_.getStackInSlot(var4);
/*     */       
/*  62 */       if (var5 != null)
/*     */       {
/*  64 */         if (var5.getItem() == Items.filled_map) {
/*     */           
/*  66 */           if (var3 != null)
/*     */           {
/*  68 */             return null;
/*     */           }
/*     */           
/*  71 */           var3 = var5;
/*     */         }
/*     */         else {
/*     */           
/*  75 */           if (var5.getItem() != Items.map)
/*     */           {
/*  77 */             return null;
/*     */           }
/*     */           
/*  80 */           var2++;
/*     */         } 
/*     */       }
/*     */     } 
/*     */     
/*  85 */     if (var3 != null && var2 >= 1) {
/*     */       
/*  87 */       ItemStack var6 = new ItemStack((Item)Items.filled_map, var2 + 1, var3.getMetadata());
/*     */       
/*  89 */       if (var3.hasDisplayName())
/*     */       {
/*  91 */         var6.setStackDisplayName(var3.getDisplayName());
/*     */       }
/*     */       
/*  94 */       return var6;
/*     */     } 
/*     */ 
/*     */     
/*  98 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getRecipeSize() {
/* 107 */     return 9;
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemStack getRecipeOutput() {
/* 112 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemStack[] func_179532_b(InventoryCrafting p_179532_1_) {
/* 117 */     ItemStack[] var2 = new ItemStack[p_179532_1_.getSizeInventory()];
/*     */     
/* 119 */     for (int var3 = 0; var3 < var2.length; var3++) {
/*     */       
/* 121 */       ItemStack var4 = p_179532_1_.getStackInSlot(var3);
/*     */       
/* 123 */       if (var4 != null && var4.getItem().hasContainerItem())
/*     */       {
/* 125 */         var2[var3] = new ItemStack(var4.getItem().getContainerItem());
/*     */       }
/*     */     } 
/*     */     
/* 129 */     return var2;
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\item\crafting\RecipesMapCloning.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */