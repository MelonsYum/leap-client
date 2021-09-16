/*     */ package net.minecraft.item.crafting;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import java.util.ArrayList;
/*     */ import net.minecraft.entity.passive.EntitySheep;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.inventory.InventoryCrafting;
/*     */ import net.minecraft.item.EnumDyeColor;
/*     */ import net.minecraft.item.ItemArmor;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class RecipesArmorDyes
/*     */   implements IRecipe
/*     */ {
/*     */   private static final String __OBFID = "CL_00000079";
/*     */   
/*     */   public boolean matches(InventoryCrafting p_77569_1_, World worldIn) {
/*  22 */     ItemStack var3 = null;
/*  23 */     ArrayList<ItemStack> var4 = Lists.newArrayList();
/*     */     
/*  25 */     for (int var5 = 0; var5 < p_77569_1_.getSizeInventory(); var5++) {
/*     */       
/*  27 */       ItemStack var6 = p_77569_1_.getStackInSlot(var5);
/*     */       
/*  29 */       if (var6 != null)
/*     */       {
/*  31 */         if (var6.getItem() instanceof ItemArmor) {
/*     */           
/*  33 */           ItemArmor var7 = (ItemArmor)var6.getItem();
/*     */           
/*  35 */           if (var7.getArmorMaterial() != ItemArmor.ArmorMaterial.LEATHER || var3 != null)
/*     */           {
/*  37 */             return false;
/*     */           }
/*     */           
/*  40 */           var3 = var6;
/*     */         }
/*     */         else {
/*     */           
/*  44 */           if (var6.getItem() != Items.dye)
/*     */           {
/*  46 */             return false;
/*     */           }
/*     */           
/*  49 */           var4.add(var6);
/*     */         } 
/*     */       }
/*     */     } 
/*     */     
/*  54 */     return (var3 != null && !var4.isEmpty());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack getCraftingResult(InventoryCrafting p_77572_1_) {
/*  62 */     ItemStack var2 = null;
/*  63 */     int[] var3 = new int[3];
/*  64 */     int var4 = 0;
/*  65 */     int var5 = 0;
/*  66 */     ItemArmor var6 = null;
/*     */ 
/*     */ 
/*     */     
/*     */     int var7;
/*     */ 
/*     */     
/*  73 */     for (var7 = 0; var7 < p_77572_1_.getSizeInventory(); var7++) {
/*     */       
/*  75 */       ItemStack var8 = p_77572_1_.getStackInSlot(var7);
/*     */       
/*  77 */       if (var8 != null)
/*     */       {
/*  79 */         if (var8.getItem() instanceof ItemArmor) {
/*     */           
/*  81 */           var6 = (ItemArmor)var8.getItem();
/*     */           
/*  83 */           if (var6.getArmorMaterial() != ItemArmor.ArmorMaterial.LEATHER || var2 != null)
/*     */           {
/*  85 */             return null;
/*     */           }
/*     */           
/*  88 */           var2 = var8.copy();
/*  89 */           var2.stackSize = 1;
/*     */           
/*  91 */           if (var6.hasColor(var8))
/*     */           {
/*  93 */             int i = var6.getColor(var2);
/*  94 */             float f1 = (i >> 16 & 0xFF) / 255.0F;
/*  95 */             float f2 = (i >> 8 & 0xFF) / 255.0F;
/*  96 */             float var12 = (i & 0xFF) / 255.0F;
/*  97 */             var4 = (int)(var4 + Math.max(f1, Math.max(f2, var12)) * 255.0F);
/*  98 */             var3[0] = (int)(var3[0] + f1 * 255.0F);
/*  99 */             var3[1] = (int)(var3[1] + f2 * 255.0F);
/* 100 */             var3[2] = (int)(var3[2] + var12 * 255.0F);
/* 101 */             var5++;
/*     */           }
/*     */         
/*     */         } else {
/*     */           
/* 106 */           if (var8.getItem() != Items.dye)
/*     */           {
/* 108 */             return null;
/*     */           }
/*     */           
/* 111 */           float[] var14 = EntitySheep.func_175513_a(EnumDyeColor.func_176766_a(var8.getMetadata()));
/* 112 */           int var15 = (int)(var14[0] * 255.0F);
/* 113 */           int var16 = (int)(var14[1] * 255.0F);
/* 114 */           int i = (int)(var14[2] * 255.0F);
/* 115 */           var4 += Math.max(var15, Math.max(var16, i));
/* 116 */           var3[0] = var3[0] + var15;
/* 117 */           var3[1] = var3[1] + var16;
/* 118 */           var3[2] = var3[2] + i;
/* 119 */           var5++;
/*     */         } 
/*     */       }
/*     */     } 
/*     */     
/* 124 */     if (var6 == null)
/*     */     {
/* 126 */       return null;
/*     */     }
/*     */ 
/*     */     
/* 130 */     var7 = var3[0] / var5;
/* 131 */     int var13 = var3[1] / var5;
/* 132 */     int var9 = var3[2] / var5;
/* 133 */     float var10 = var4 / var5;
/* 134 */     float var11 = Math.max(var7, Math.max(var13, var9));
/* 135 */     var7 = (int)(var7 * var10 / var11);
/* 136 */     var13 = (int)(var13 * var10 / var11);
/* 137 */     var9 = (int)(var9 * var10 / var11);
/* 138 */     int var17 = (var7 << 8) + var13;
/* 139 */     var17 = (var17 << 8) + var9;
/* 140 */     var6.func_82813_b(var2, var17);
/* 141 */     return var2;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getRecipeSize() {
/* 150 */     return 10;
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemStack getRecipeOutput() {
/* 155 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemStack[] func_179532_b(InventoryCrafting p_179532_1_) {
/* 160 */     ItemStack[] var2 = new ItemStack[p_179532_1_.getSizeInventory()];
/*     */     
/* 162 */     for (int var3 = 0; var3 < var2.length; var3++) {
/*     */       
/* 164 */       ItemStack var4 = p_179532_1_.getStackInSlot(var3);
/*     */       
/* 166 */       if (var4 != null && var4.getItem().hasContainerItem())
/*     */       {
/* 168 */         var2[var3] = new ItemStack(var4.getItem().getContainerItem());
/*     */       }
/*     */     } 
/*     */     
/* 172 */     return var2;
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\item\crafting\RecipesArmorDyes.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */