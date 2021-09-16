/*     */ package net.minecraft.item.crafting;
/*     */ 
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.inventory.InventoryCrafting;
/*     */ import net.minecraft.item.EnumDyeColor;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTBase;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.nbt.NBTTagList;
/*     */ import net.minecraft.tileentity.TileEntityBanner;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class RecipesBanners
/*     */ {
/*     */   private static final String __OBFID = "CL_00002160";
/*     */   
/*     */   void func_179534_a(CraftingManager p_179534_1_) {
/*  19 */     EnumDyeColor[] var2 = EnumDyeColor.values();
/*  20 */     int var3 = var2.length;
/*     */     
/*  22 */     for (int var4 = 0; var4 < var3; var4++) {
/*     */       
/*  24 */       EnumDyeColor var5 = var2[var4];
/*  25 */       p_179534_1_.addRecipe(new ItemStack(Items.banner, 1, var5.getDyeColorDamage()), new Object[] { "###", "###", " | ", Character.valueOf('#'), new ItemStack(Blocks.wool, 1, var5.func_176765_a()), Character.valueOf('|'), Items.stick });
/*     */     } 
/*     */     
/*  28 */     p_179534_1_.func_180302_a(new RecipeDuplicatePattern(null));
/*  29 */     p_179534_1_.func_180302_a(new RecipeAddPattern(null));
/*     */   }
/*     */   
/*     */   static class RecipeAddPattern
/*     */     implements IRecipe
/*     */   {
/*     */     private static final String __OBFID = "CL_00002158";
/*     */     
/*     */     private RecipeAddPattern() {}
/*     */     
/*     */     public boolean matches(InventoryCrafting p_77569_1_, World worldIn) {
/*  40 */       boolean var3 = false;
/*     */       
/*  42 */       for (int var4 = 0; var4 < p_77569_1_.getSizeInventory(); var4++) {
/*     */         
/*  44 */         ItemStack var5 = p_77569_1_.getStackInSlot(var4);
/*     */         
/*  46 */         if (var5 != null && var5.getItem() == Items.banner) {
/*     */           
/*  48 */           if (var3)
/*     */           {
/*  50 */             return false;
/*     */           }
/*     */           
/*  53 */           if (TileEntityBanner.func_175113_c(var5) >= 6)
/*     */           {
/*  55 */             return false;
/*     */           }
/*     */           
/*  58 */           var3 = true;
/*     */         } 
/*     */       } 
/*     */       
/*  62 */       if (!var3)
/*     */       {
/*  64 */         return false;
/*     */       }
/*     */ 
/*     */       
/*  68 */       return (func_179533_c(p_77569_1_) != null);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public ItemStack getCraftingResult(InventoryCrafting p_77572_1_) {
/*  74 */       ItemStack var2 = null;
/*     */       
/*  76 */       for (int var3 = 0; var3 < p_77572_1_.getSizeInventory(); var3++) {
/*     */         
/*  78 */         ItemStack var4 = p_77572_1_.getStackInSlot(var3);
/*     */         
/*  80 */         if (var4 != null && var4.getItem() == Items.banner) {
/*     */           
/*  82 */           var2 = var4.copy();
/*  83 */           var2.stackSize = 1;
/*     */           
/*     */           break;
/*     */         } 
/*     */       } 
/*  88 */       TileEntityBanner.EnumBannerPattern var8 = func_179533_c(p_77572_1_);
/*     */       
/*  90 */       if (var8 != null) {
/*     */         NBTTagList var11;
/*  92 */         int var9 = 0;
/*     */ 
/*     */         
/*  95 */         for (int var5 = 0; var5 < p_77572_1_.getSizeInventory(); var5++) {
/*     */           
/*  97 */           ItemStack itemStack = p_77572_1_.getStackInSlot(var5);
/*     */           
/*  99 */           if (itemStack != null && itemStack.getItem() == Items.dye) {
/*     */             
/* 101 */             var9 = itemStack.getMetadata();
/*     */             
/*     */             break;
/*     */           } 
/*     */         } 
/* 106 */         NBTTagCompound var10 = var2.getSubCompound("BlockEntityTag", true);
/* 107 */         ItemStack var6 = null;
/*     */ 
/*     */         
/* 110 */         if (var10.hasKey("Patterns", 9)) {
/*     */           
/* 112 */           var11 = var10.getTagList("Patterns", 10);
/*     */         }
/*     */         else {
/*     */           
/* 116 */           var11 = new NBTTagList();
/* 117 */           var10.setTag("Patterns", (NBTBase)var11);
/*     */         } 
/*     */         
/* 120 */         NBTTagCompound var7 = new NBTTagCompound();
/* 121 */         var7.setString("Pattern", var8.func_177273_b());
/* 122 */         var7.setInteger("Color", var9);
/* 123 */         var11.appendTag((NBTBase)var7);
/*     */       } 
/*     */       
/* 126 */       return var2;
/*     */     }
/*     */ 
/*     */     
/*     */     public int getRecipeSize() {
/* 131 */       return 10;
/*     */     }
/*     */ 
/*     */     
/*     */     public ItemStack getRecipeOutput() {
/* 136 */       return null;
/*     */     }
/*     */ 
/*     */     
/*     */     public ItemStack[] func_179532_b(InventoryCrafting p_179532_1_) {
/* 141 */       ItemStack[] var2 = new ItemStack[p_179532_1_.getSizeInventory()];
/*     */       
/* 143 */       for (int var3 = 0; var3 < var2.length; var3++) {
/*     */         
/* 145 */         ItemStack var4 = p_179532_1_.getStackInSlot(var3);
/*     */         
/* 147 */         if (var4 != null && var4.getItem().hasContainerItem())
/*     */         {
/* 149 */           var2[var3] = new ItemStack(var4.getItem().getContainerItem());
/*     */         }
/*     */       } 
/*     */       
/* 153 */       return var2;
/*     */     }
/*     */ 
/*     */     
/*     */     private TileEntityBanner.EnumBannerPattern func_179533_c(InventoryCrafting p_179533_1_) {
/* 158 */       TileEntityBanner.EnumBannerPattern[] var2 = TileEntityBanner.EnumBannerPattern.values();
/* 159 */       int var3 = var2.length;
/*     */       
/* 161 */       for (int var4 = 0; var4 < var3; var4++) {
/*     */         
/* 163 */         TileEntityBanner.EnumBannerPattern var5 = var2[var4];
/*     */         
/* 165 */         if (var5.func_177270_d()) {
/*     */           
/* 167 */           boolean var6 = true;
/*     */ 
/*     */           
/* 170 */           if (var5.func_177269_e()) {
/*     */             
/* 172 */             boolean var12 = false;
/* 173 */             boolean var13 = false;
/*     */             
/* 175 */             for (int var9 = 0; var9 < p_179533_1_.getSizeInventory() && var6; var9++) {
/*     */               
/* 177 */               ItemStack var14 = p_179533_1_.getStackInSlot(var9);
/*     */               
/* 179 */               if (var14 != null && var14.getItem() != Items.banner)
/*     */               {
/* 181 */                 if (var14.getItem() == Items.dye) {
/*     */                   
/* 183 */                   if (var13) {
/*     */                     
/* 185 */                     var6 = false;
/*     */                     
/*     */                     break;
/*     */                   } 
/* 189 */                   var13 = true;
/*     */                 }
/*     */                 else {
/*     */                   
/* 193 */                   if (var12 || !var14.isItemEqual(var5.func_177272_f())) {
/*     */                     
/* 195 */                     var6 = false;
/*     */                     
/*     */                     break;
/*     */                   } 
/* 199 */                   var12 = true;
/*     */                 } 
/*     */               }
/*     */             } 
/*     */             
/* 204 */             if (!var12)
/*     */             {
/* 206 */               var6 = false;
/*     */             }
/*     */           }
/* 209 */           else if (p_179533_1_.getSizeInventory() != (var5.func_177267_c()).length * var5.func_177267_c()[0].length()) {
/*     */             
/* 211 */             var6 = false;
/*     */           }
/*     */           else {
/*     */             
/* 215 */             int var7 = -1;
/*     */             
/* 217 */             for (int var8 = 0; var8 < p_179533_1_.getSizeInventory() && var6; var8++) {
/*     */               
/* 219 */               int var9 = var8 / 3;
/* 220 */               int var10 = var8 % 3;
/* 221 */               ItemStack var11 = p_179533_1_.getStackInSlot(var8);
/*     */               
/* 223 */               if (var11 != null && var11.getItem() != Items.banner) {
/*     */                 
/* 225 */                 if (var11.getItem() != Items.dye) {
/*     */                   
/* 227 */                   var6 = false;
/*     */                   
/*     */                   break;
/*     */                 } 
/* 231 */                 if (var7 != -1 && var7 != var11.getMetadata()) {
/*     */                   
/* 233 */                   var6 = false;
/*     */                   
/*     */                   break;
/*     */                 } 
/* 237 */                 if (var5.func_177267_c()[var9].charAt(var10) == ' ') {
/*     */                   
/* 239 */                   var6 = false;
/*     */                   
/*     */                   break;
/*     */                 } 
/* 243 */                 var7 = var11.getMetadata();
/*     */               }
/* 245 */               else if (var5.func_177267_c()[var9].charAt(var10) != ' ') {
/*     */                 
/* 247 */                 var6 = false;
/*     */                 
/*     */                 break;
/*     */               } 
/*     */             } 
/*     */           } 
/* 253 */           if (var6)
/*     */           {
/* 255 */             return var5;
/*     */           }
/*     */         } 
/*     */       } 
/*     */       
/* 260 */       return null;
/*     */     }
/*     */ 
/*     */     
/*     */     RecipeAddPattern(Object p_i45780_1_) {
/* 265 */       this();
/*     */     }
/*     */   }
/*     */   
/*     */   static class RecipeDuplicatePattern
/*     */     implements IRecipe
/*     */   {
/*     */     private static final String __OBFID = "CL_00002157";
/*     */     
/*     */     private RecipeDuplicatePattern() {}
/*     */     
/*     */     public boolean matches(InventoryCrafting p_77569_1_, World worldIn) {
/* 277 */       ItemStack var3 = null;
/* 278 */       ItemStack var4 = null;
/*     */       
/* 280 */       for (int var5 = 0; var5 < p_77569_1_.getSizeInventory(); var5++) {
/*     */         
/* 282 */         ItemStack var6 = p_77569_1_.getStackInSlot(var5);
/*     */         
/* 284 */         if (var6 != null) {
/*     */           
/* 286 */           if (var6.getItem() != Items.banner)
/*     */           {
/* 288 */             return false;
/*     */           }
/*     */           
/* 291 */           if (var3 != null && var4 != null)
/*     */           {
/* 293 */             return false;
/*     */           }
/*     */           
/* 296 */           int var7 = TileEntityBanner.getBaseColor(var6);
/* 297 */           boolean var8 = (TileEntityBanner.func_175113_c(var6) > 0);
/*     */           
/* 299 */           if (var3 != null) {
/*     */             
/* 301 */             if (var8)
/*     */             {
/* 303 */               return false;
/*     */             }
/*     */             
/* 306 */             if (var7 != TileEntityBanner.getBaseColor(var3))
/*     */             {
/* 308 */               return false;
/*     */             }
/*     */             
/* 311 */             var4 = var6;
/*     */           }
/* 313 */           else if (var4 != null) {
/*     */             
/* 315 */             if (!var8)
/*     */             {
/* 317 */               return false;
/*     */             }
/*     */             
/* 320 */             if (var7 != TileEntityBanner.getBaseColor(var4))
/*     */             {
/* 322 */               return false;
/*     */             }
/*     */             
/* 325 */             var3 = var6;
/*     */           }
/* 327 */           else if (var8) {
/*     */             
/* 329 */             var3 = var6;
/*     */           }
/*     */           else {
/*     */             
/* 333 */             var4 = var6;
/*     */           } 
/*     */         } 
/*     */       } 
/*     */       
/* 338 */       return (var3 != null && var4 != null);
/*     */     }
/*     */ 
/*     */     
/*     */     public ItemStack getCraftingResult(InventoryCrafting p_77572_1_) {
/* 343 */       for (int var2 = 0; var2 < p_77572_1_.getSizeInventory(); var2++) {
/*     */         
/* 345 */         ItemStack var3 = p_77572_1_.getStackInSlot(var2);
/*     */         
/* 347 */         if (var3 != null && TileEntityBanner.func_175113_c(var3) > 0) {
/*     */           
/* 349 */           ItemStack var4 = var3.copy();
/* 350 */           var4.stackSize = 1;
/* 351 */           return var4;
/*     */         } 
/*     */       } 
/*     */       
/* 355 */       return null;
/*     */     }
/*     */ 
/*     */     
/*     */     public int getRecipeSize() {
/* 360 */       return 2;
/*     */     }
/*     */ 
/*     */     
/*     */     public ItemStack getRecipeOutput() {
/* 365 */       return null;
/*     */     }
/*     */ 
/*     */     
/*     */     public ItemStack[] func_179532_b(InventoryCrafting p_179532_1_) {
/* 370 */       ItemStack[] var2 = new ItemStack[p_179532_1_.getSizeInventory()];
/*     */       
/* 372 */       for (int var3 = 0; var3 < var2.length; var3++) {
/*     */         
/* 374 */         ItemStack var4 = p_179532_1_.getStackInSlot(var3);
/*     */         
/* 376 */         if (var4 != null)
/*     */         {
/* 378 */           if (var4.getItem().hasContainerItem()) {
/*     */             
/* 380 */             var2[var3] = new ItemStack(var4.getItem().getContainerItem());
/*     */           }
/* 382 */           else if (var4.hasTagCompound() && TileEntityBanner.func_175113_c(var4) > 0) {
/*     */             
/* 384 */             var2[var3] = var4.copy();
/* 385 */             (var2[var3]).stackSize = 1;
/*     */           } 
/*     */         }
/*     */       } 
/*     */       
/* 390 */       return var2;
/*     */     }
/*     */ 
/*     */     
/*     */     RecipeDuplicatePattern(Object p_i45779_1_) {
/* 395 */       this();
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\item\crafting\RecipesBanners.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */