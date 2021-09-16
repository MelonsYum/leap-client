/*     */ package net.minecraft.item.crafting;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import java.util.ArrayList;
/*     */ import net.minecraft.inventory.InventoryCrafting;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class RecipeRepairItem
/*     */   implements IRecipe
/*     */ {
/*     */   private static final String __OBFID = "CL_00002156";
/*     */   
/*     */   public boolean matches(InventoryCrafting p_77569_1_, World worldIn) {
/*  19 */     ArrayList<ItemStack> var3 = Lists.newArrayList();
/*     */     
/*  21 */     for (int var4 = 0; var4 < p_77569_1_.getSizeInventory(); var4++) {
/*     */       
/*  23 */       ItemStack var5 = p_77569_1_.getStackInSlot(var4);
/*     */       
/*  25 */       if (var5 != null) {
/*     */         
/*  27 */         var3.add(var5);
/*     */         
/*  29 */         if (var3.size() > 1) {
/*     */           
/*  31 */           ItemStack var6 = var3.get(0);
/*     */           
/*  33 */           if (var5.getItem() != var6.getItem() || var6.stackSize != 1 || var5.stackSize != 1 || !var6.getItem().isDamageable())
/*     */           {
/*  35 */             return false;
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/*  41 */     return (var3.size() == 2);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack getCraftingResult(InventoryCrafting p_77572_1_) {
/*  49 */     ArrayList<ItemStack> var2 = Lists.newArrayList();
/*     */ 
/*     */     
/*  52 */     for (int var3 = 0; var3 < p_77572_1_.getSizeInventory(); var3++) {
/*     */       
/*  54 */       ItemStack var4 = p_77572_1_.getStackInSlot(var3);
/*     */       
/*  56 */       if (var4 != null) {
/*     */         
/*  58 */         var2.add(var4);
/*     */         
/*  60 */         if (var2.size() > 1) {
/*     */           
/*  62 */           ItemStack var5 = var2.get(0);
/*     */           
/*  64 */           if (var4.getItem() != var5.getItem() || var5.stackSize != 1 || var4.stackSize != 1 || !var5.getItem().isDamageable())
/*     */           {
/*  66 */             return null;
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/*  72 */     if (var2.size() == 2) {
/*     */       
/*  74 */       ItemStack var10 = var2.get(0);
/*  75 */       ItemStack var4 = var2.get(1);
/*     */       
/*  77 */       if (var10.getItem() == var4.getItem() && var10.stackSize == 1 && var4.stackSize == 1 && var10.getItem().isDamageable()) {
/*     */         
/*  79 */         Item var11 = var10.getItem();
/*  80 */         int var6 = var11.getMaxDamage() - var10.getItemDamage();
/*  81 */         int var7 = var11.getMaxDamage() - var4.getItemDamage();
/*  82 */         int var8 = var6 + var7 + var11.getMaxDamage() * 5 / 100;
/*  83 */         int var9 = var11.getMaxDamage() - var8;
/*     */         
/*  85 */         if (var9 < 0)
/*     */         {
/*  87 */           var9 = 0;
/*     */         }
/*     */         
/*  90 */         return new ItemStack(var10.getItem(), 1, var9);
/*     */       } 
/*     */     } 
/*     */     
/*  94 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getRecipeSize() {
/* 102 */     return 4;
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemStack getRecipeOutput() {
/* 107 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemStack[] func_179532_b(InventoryCrafting p_179532_1_) {
/* 112 */     ItemStack[] var2 = new ItemStack[p_179532_1_.getSizeInventory()];
/*     */     
/* 114 */     for (int var3 = 0; var3 < var2.length; var3++) {
/*     */       
/* 116 */       ItemStack var4 = p_179532_1_.getStackInSlot(var3);
/*     */       
/* 118 */       if (var4 != null && var4.getItem().hasContainerItem())
/*     */       {
/* 120 */         var2[var3] = new ItemStack(var4.getItem().getContainerItem());
/*     */       }
/*     */     } 
/*     */     
/* 124 */     return var2;
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\item\crafting\RecipeRepairItem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */