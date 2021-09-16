/*     */ package net.minecraft.item.crafting;
/*     */ 
/*     */ import net.minecraft.inventory.InventoryCrafting;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ShapedRecipes
/*     */   implements IRecipe
/*     */ {
/*     */   private final int recipeWidth;
/*     */   private final int recipeHeight;
/*     */   private final ItemStack[] recipeItems;
/*     */   private final ItemStack recipeOutput;
/*     */   private boolean field_92101_f;
/*     */   private static final String __OBFID = "CL_00000093";
/*     */   
/*     */   public ShapedRecipes(int p_i1917_1_, int p_i1917_2_, ItemStack[] p_i1917_3_, ItemStack p_i1917_4_) {
/*  26 */     this.recipeWidth = p_i1917_1_;
/*  27 */     this.recipeHeight = p_i1917_2_;
/*  28 */     this.recipeItems = p_i1917_3_;
/*  29 */     this.recipeOutput = p_i1917_4_;
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemStack getRecipeOutput() {
/*  34 */     return this.recipeOutput;
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemStack[] func_179532_b(InventoryCrafting p_179532_1_) {
/*  39 */     ItemStack[] var2 = new ItemStack[p_179532_1_.getSizeInventory()];
/*     */     
/*  41 */     for (int var3 = 0; var3 < var2.length; var3++) {
/*     */       
/*  43 */       ItemStack var4 = p_179532_1_.getStackInSlot(var3);
/*     */       
/*  45 */       if (var4 != null && var4.getItem().hasContainerItem())
/*     */       {
/*  47 */         var2[var3] = new ItemStack(var4.getItem().getContainerItem());
/*     */       }
/*     */     } 
/*     */     
/*  51 */     return var2;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean matches(InventoryCrafting p_77569_1_, World worldIn) {
/*  59 */     for (int var3 = 0; var3 <= 3 - this.recipeWidth; var3++) {
/*     */       
/*  61 */       for (int var4 = 0; var4 <= 3 - this.recipeHeight; var4++) {
/*     */         
/*  63 */         if (checkMatch(p_77569_1_, var3, var4, true))
/*     */         {
/*  65 */           return true;
/*     */         }
/*     */         
/*  68 */         if (checkMatch(p_77569_1_, var3, var4, false))
/*     */         {
/*  70 */           return true;
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/*  75 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean checkMatch(InventoryCrafting p_77573_1_, int p_77573_2_, int p_77573_3_, boolean p_77573_4_) {
/*  83 */     for (int var5 = 0; var5 < 3; var5++) {
/*     */       
/*  85 */       for (int var6 = 0; var6 < 3; var6++) {
/*     */         
/*  87 */         int var7 = var5 - p_77573_2_;
/*  88 */         int var8 = var6 - p_77573_3_;
/*  89 */         ItemStack var9 = null;
/*     */         
/*  91 */         if (var7 >= 0 && var8 >= 0 && var7 < this.recipeWidth && var8 < this.recipeHeight)
/*     */         {
/*  93 */           if (p_77573_4_) {
/*     */             
/*  95 */             var9 = this.recipeItems[this.recipeWidth - var7 - 1 + var8 * this.recipeWidth];
/*     */           }
/*     */           else {
/*     */             
/*  99 */             var9 = this.recipeItems[var7 + var8 * this.recipeWidth];
/*     */           } 
/*     */         }
/*     */         
/* 103 */         ItemStack var10 = p_77573_1_.getStackInRowAndColumn(var5, var6);
/*     */         
/* 105 */         if (var10 != null || var9 != null) {
/*     */           
/* 107 */           if ((var10 == null && var9 != null) || (var10 != null && var9 == null))
/*     */           {
/* 109 */             return false;
/*     */           }
/*     */           
/* 112 */           if (var9.getItem() != var10.getItem())
/*     */           {
/* 114 */             return false;
/*     */           }
/*     */           
/* 117 */           if (var9.getMetadata() != 32767 && var9.getMetadata() != var10.getMetadata())
/*     */           {
/* 119 */             return false;
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 125 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack getCraftingResult(InventoryCrafting p_77572_1_) {
/* 133 */     ItemStack var2 = getRecipeOutput().copy();
/*     */     
/* 135 */     if (this.field_92101_f)
/*     */     {
/* 137 */       for (int var3 = 0; var3 < p_77572_1_.getSizeInventory(); var3++) {
/*     */         
/* 139 */         ItemStack var4 = p_77572_1_.getStackInSlot(var3);
/*     */         
/* 141 */         if (var4 != null && var4.hasTagCompound())
/*     */         {
/* 143 */           var2.setTagCompound((NBTTagCompound)var4.getTagCompound().copy());
/*     */         }
/*     */       } 
/*     */     }
/*     */     
/* 148 */     return var2;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getRecipeSize() {
/* 156 */     return this.recipeWidth * this.recipeHeight;
/*     */   }
/*     */ 
/*     */   
/*     */   public ShapedRecipes func_92100_c() {
/* 161 */     this.field_92101_f = true;
/* 162 */     return this;
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\item\crafting\ShapedRecipes.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */