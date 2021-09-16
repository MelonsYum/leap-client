/*     */ package net.minecraft.item.crafting;
/*     */ 
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.inventory.InventoryCrafting;
/*     */ import net.minecraft.item.ItemEditableBook;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class RecipeBookCloning
/*     */   implements IRecipe
/*     */ {
/*     */   private static final String __OBFID = "CL_00000081";
/*     */   
/*     */   public boolean matches(InventoryCrafting p_77569_1_, World worldIn) {
/*  19 */     int var3 = 0;
/*  20 */     ItemStack var4 = null;
/*     */     
/*  22 */     for (int var5 = 0; var5 < p_77569_1_.getSizeInventory(); var5++) {
/*     */       
/*  24 */       ItemStack var6 = p_77569_1_.getStackInSlot(var5);
/*     */       
/*  26 */       if (var6 != null)
/*     */       {
/*  28 */         if (var6.getItem() == Items.written_book) {
/*     */           
/*  30 */           if (var4 != null)
/*     */           {
/*  32 */             return false;
/*     */           }
/*     */           
/*  35 */           var4 = var6;
/*     */         }
/*     */         else {
/*     */           
/*  39 */           if (var6.getItem() != Items.writable_book)
/*     */           {
/*  41 */             return false;
/*     */           }
/*     */           
/*  44 */           var3++;
/*     */         } 
/*     */       }
/*     */     } 
/*     */     
/*  49 */     return (var4 != null && var3 > 0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack getCraftingResult(InventoryCrafting p_77572_1_) {
/*  57 */     int var2 = 0;
/*  58 */     ItemStack var3 = null;
/*     */     
/*  60 */     for (int var4 = 0; var4 < p_77572_1_.getSizeInventory(); var4++) {
/*     */       
/*  62 */       ItemStack var5 = p_77572_1_.getStackInSlot(var4);
/*     */       
/*  64 */       if (var5 != null)
/*     */       {
/*  66 */         if (var5.getItem() == Items.written_book) {
/*     */           
/*  68 */           if (var3 != null)
/*     */           {
/*  70 */             return null;
/*     */           }
/*     */           
/*  73 */           var3 = var5;
/*     */         }
/*     */         else {
/*     */           
/*  77 */           if (var5.getItem() != Items.writable_book)
/*     */           {
/*  79 */             return null;
/*     */           }
/*     */           
/*  82 */           var2++;
/*     */         } 
/*     */       }
/*     */     } 
/*     */     
/*  87 */     if (var3 != null && var2 >= 1 && ItemEditableBook.func_179230_h(var3) < 2) {
/*     */       
/*  89 */       ItemStack var6 = new ItemStack(Items.written_book, var2);
/*  90 */       var6.setTagCompound((NBTTagCompound)var3.getTagCompound().copy());
/*  91 */       var6.getTagCompound().setInteger("generation", ItemEditableBook.func_179230_h(var3) + 1);
/*     */       
/*  93 */       if (var3.hasDisplayName())
/*     */       {
/*  95 */         var6.setStackDisplayName(var3.getDisplayName());
/*     */       }
/*     */       
/*  98 */       return var6;
/*     */     } 
/*     */ 
/*     */     
/* 102 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getRecipeSize() {
/* 111 */     return 9;
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemStack getRecipeOutput() {
/* 116 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemStack[] func_179532_b(InventoryCrafting p_179532_1_) {
/* 121 */     ItemStack[] var2 = new ItemStack[p_179532_1_.getSizeInventory()];
/*     */     
/* 123 */     for (int var3 = 0; var3 < var2.length; var3++) {
/*     */       
/* 125 */       ItemStack var4 = p_179532_1_.getStackInSlot(var3);
/*     */       
/* 127 */       if (var4 != null && var4.getItem() instanceof ItemEditableBook) {
/*     */         
/* 129 */         var2[var3] = var4;
/*     */         
/*     */         break;
/*     */       } 
/*     */     } 
/* 134 */     return var2;
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\item\crafting\RecipeBookCloning.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */