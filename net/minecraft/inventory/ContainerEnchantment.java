/*     */ package net.minecraft.inventory;
/*     */ 
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import net.minecraft.enchantment.EnchantmentData;
/*     */ import net.minecraft.enchantment.EnchantmentHelper;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.player.InventoryPlayer;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.item.EnumDyeColor;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ContainerEnchantment
/*     */   extends Container
/*     */ {
/*     */   public IInventory tableInventory;
/*     */   private World worldPointer;
/*     */   private BlockPos field_178150_j;
/*     */   private Random rand;
/*     */   public int field_178149_f;
/*     */   public int[] enchantLevels;
/*     */   public int[] field_178151_h;
/*     */   private static final String __OBFID = "CL_00001745";
/*     */   
/*     */   public ContainerEnchantment(InventoryPlayer p_i45797_1_, World worldIn) {
/*  34 */     this(p_i45797_1_, worldIn, BlockPos.ORIGIN);
/*     */   }
/*     */ 
/*     */   
/*     */   public ContainerEnchantment(InventoryPlayer p_i45798_1_, World worldIn, BlockPos p_i45798_3_) {
/*  39 */     this.tableInventory = new InventoryBasic("Enchant", true, 2)
/*     */       {
/*     */         private static final String __OBFID = "CL_00001746";
/*     */         
/*     */         public int getInventoryStackLimit() {
/*  44 */           return 64;
/*     */         }
/*     */         
/*     */         public void markDirty() {
/*  48 */           super.markDirty();
/*  49 */           ContainerEnchantment.this.onCraftMatrixChanged(this);
/*     */         }
/*     */       };
/*  52 */     this.rand = new Random();
/*  53 */     this.enchantLevels = new int[3];
/*  54 */     this.field_178151_h = new int[] { -1, -1, -1 };
/*  55 */     this.worldPointer = worldIn;
/*  56 */     this.field_178150_j = p_i45798_3_;
/*  57 */     this.field_178149_f = p_i45798_1_.player.func_175138_ci();
/*  58 */     addSlotToContainer(new Slot(this.tableInventory, 0, 15, 47)
/*     */         {
/*     */           private static final String __OBFID = "CL_00001747";
/*     */           
/*     */           public boolean isItemValid(ItemStack stack) {
/*  63 */             return true;
/*     */           }
/*     */           
/*     */           public int getSlotStackLimit() {
/*  67 */             return 1;
/*     */           }
/*     */         });
/*  70 */     addSlotToContainer(new Slot(this.tableInventory, 1, 35, 47)
/*     */         {
/*     */           private static final String __OBFID = "CL_00002185";
/*     */           
/*     */           public boolean isItemValid(ItemStack stack) {
/*  75 */             return (stack.getItem() == Items.dye && EnumDyeColor.func_176766_a(stack.getMetadata()) == EnumDyeColor.BLUE);
/*     */           }
/*     */         });
/*     */     
/*     */     int var4;
/*  80 */     for (var4 = 0; var4 < 3; var4++) {
/*     */       
/*  82 */       for (int var5 = 0; var5 < 9; var5++)
/*     */       {
/*  84 */         addSlotToContainer(new Slot((IInventory)p_i45798_1_, var5 + var4 * 9 + 9, 8 + var5 * 18, 84 + var4 * 18));
/*     */       }
/*     */     } 
/*     */     
/*  88 */     for (var4 = 0; var4 < 9; var4++)
/*     */     {
/*  90 */       addSlotToContainer(new Slot((IInventory)p_i45798_1_, var4, 8 + var4 * 18, 142));
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void onCraftGuiOpened(ICrafting p_75132_1_) {
/*  96 */     super.onCraftGuiOpened(p_75132_1_);
/*  97 */     p_75132_1_.sendProgressBarUpdate(this, 0, this.enchantLevels[0]);
/*  98 */     p_75132_1_.sendProgressBarUpdate(this, 1, this.enchantLevels[1]);
/*  99 */     p_75132_1_.sendProgressBarUpdate(this, 2, this.enchantLevels[2]);
/* 100 */     p_75132_1_.sendProgressBarUpdate(this, 3, this.field_178149_f & 0xFFFFFFF0);
/* 101 */     p_75132_1_.sendProgressBarUpdate(this, 4, this.field_178151_h[0]);
/* 102 */     p_75132_1_.sendProgressBarUpdate(this, 5, this.field_178151_h[1]);
/* 103 */     p_75132_1_.sendProgressBarUpdate(this, 6, this.field_178151_h[2]);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void detectAndSendChanges() {
/* 111 */     super.detectAndSendChanges();
/*     */     
/* 113 */     for (int var1 = 0; var1 < this.crafters.size(); var1++) {
/*     */       
/* 115 */       ICrafting var2 = this.crafters.get(var1);
/* 116 */       var2.sendProgressBarUpdate(this, 0, this.enchantLevels[0]);
/* 117 */       var2.sendProgressBarUpdate(this, 1, this.enchantLevels[1]);
/* 118 */       var2.sendProgressBarUpdate(this, 2, this.enchantLevels[2]);
/* 119 */       var2.sendProgressBarUpdate(this, 3, this.field_178149_f & 0xFFFFFFF0);
/* 120 */       var2.sendProgressBarUpdate(this, 4, this.field_178151_h[0]);
/* 121 */       var2.sendProgressBarUpdate(this, 5, this.field_178151_h[1]);
/* 122 */       var2.sendProgressBarUpdate(this, 6, this.field_178151_h[2]);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void updateProgressBar(int p_75137_1_, int p_75137_2_) {
/* 128 */     if (p_75137_1_ >= 0 && p_75137_1_ <= 2) {
/*     */       
/* 130 */       this.enchantLevels[p_75137_1_] = p_75137_2_;
/*     */     }
/* 132 */     else if (p_75137_1_ == 3) {
/*     */       
/* 134 */       this.field_178149_f = p_75137_2_;
/*     */     }
/* 136 */     else if (p_75137_1_ >= 4 && p_75137_1_ <= 6) {
/*     */       
/* 138 */       this.field_178151_h[p_75137_1_ - 4] = p_75137_2_;
/*     */     }
/*     */     else {
/*     */       
/* 142 */       super.updateProgressBar(p_75137_1_, p_75137_2_);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onCraftMatrixChanged(IInventory p_75130_1_) {
/* 151 */     if (p_75130_1_ == this.tableInventory) {
/*     */       
/* 153 */       ItemStack var2 = p_75130_1_.getStackInSlot(0);
/*     */ 
/*     */       
/* 156 */       if (var2 != null && var2.isItemEnchantable()) {
/*     */         
/* 158 */         if (!this.worldPointer.isRemote)
/*     */         {
/* 160 */           int var3 = 0;
/*     */           
/*     */           int var4;
/* 163 */           for (var4 = -1; var4 <= 1; var4++) {
/*     */             
/* 165 */             for (int var5 = -1; var5 <= 1; var5++) {
/*     */               
/* 167 */               if ((var4 != 0 || var5 != 0) && this.worldPointer.isAirBlock(this.field_178150_j.add(var5, 0, var4)) && this.worldPointer.isAirBlock(this.field_178150_j.add(var5, 1, var4))) {
/*     */                 
/* 169 */                 if (this.worldPointer.getBlockState(this.field_178150_j.add(var5 * 2, 0, var4 * 2)).getBlock() == Blocks.bookshelf)
/*     */                 {
/* 171 */                   var3++;
/*     */                 }
/*     */                 
/* 174 */                 if (this.worldPointer.getBlockState(this.field_178150_j.add(var5 * 2, 1, var4 * 2)).getBlock() == Blocks.bookshelf)
/*     */                 {
/* 176 */                   var3++;
/*     */                 }
/*     */                 
/* 179 */                 if (var5 != 0 && var4 != 0) {
/*     */                   
/* 181 */                   if (this.worldPointer.getBlockState(this.field_178150_j.add(var5 * 2, 0, var4)).getBlock() == Blocks.bookshelf)
/*     */                   {
/* 183 */                     var3++;
/*     */                   }
/*     */                   
/* 186 */                   if (this.worldPointer.getBlockState(this.field_178150_j.add(var5 * 2, 1, var4)).getBlock() == Blocks.bookshelf)
/*     */                   {
/* 188 */                     var3++;
/*     */                   }
/*     */                   
/* 191 */                   if (this.worldPointer.getBlockState(this.field_178150_j.add(var5, 0, var4 * 2)).getBlock() == Blocks.bookshelf)
/*     */                   {
/* 193 */                     var3++;
/*     */                   }
/*     */                   
/* 196 */                   if (this.worldPointer.getBlockState(this.field_178150_j.add(var5, 1, var4 * 2)).getBlock() == Blocks.bookshelf)
/*     */                   {
/* 198 */                     var3++;
/*     */                   }
/*     */                 } 
/*     */               } 
/*     */             } 
/*     */           } 
/*     */           
/* 205 */           this.rand.setSeed(this.field_178149_f);
/*     */           
/* 207 */           for (var4 = 0; var4 < 3; var4++) {
/*     */             
/* 209 */             this.enchantLevels[var4] = EnchantmentHelper.calcItemStackEnchantability(this.rand, var4, var3, var2);
/* 210 */             this.field_178151_h[var4] = -1;
/*     */             
/* 212 */             if (this.enchantLevels[var4] < var4 + 1)
/*     */             {
/* 214 */               this.enchantLevels[var4] = 0;
/*     */             }
/*     */           } 
/*     */           
/* 218 */           for (var4 = 0; var4 < 3; var4++) {
/*     */             
/* 220 */             if (this.enchantLevels[var4] > 0) {
/*     */               
/* 222 */               List<EnchantmentData> var7 = func_178148_a(var2, var4, this.enchantLevels[var4]);
/*     */               
/* 224 */               if (var7 != null && !var7.isEmpty()) {
/*     */                 
/* 226 */                 EnchantmentData var6 = var7.get(this.rand.nextInt(var7.size()));
/* 227 */                 this.field_178151_h[var4] = var6.enchantmentobj.effectId | var6.enchantmentLevel << 8;
/*     */               } 
/*     */             } 
/*     */           } 
/*     */           
/* 232 */           detectAndSendChanges();
/*     */         }
/*     */       
/*     */       } else {
/*     */         
/* 237 */         for (int var3 = 0; var3 < 3; var3++) {
/*     */           
/* 239 */           this.enchantLevels[var3] = 0;
/* 240 */           this.field_178151_h[var3] = -1;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean enchantItem(EntityPlayer playerIn, int id) {
/* 251 */     ItemStack var3 = this.tableInventory.getStackInSlot(0);
/* 252 */     ItemStack var4 = this.tableInventory.getStackInSlot(1);
/* 253 */     int var5 = id + 1;
/*     */     
/* 255 */     if ((var4 == null || var4.stackSize < var5) && !playerIn.capabilities.isCreativeMode)
/*     */     {
/* 257 */       return false;
/*     */     }
/* 259 */     if (this.enchantLevels[id] > 0 && var3 != null && ((playerIn.experienceLevel >= var5 && playerIn.experienceLevel >= this.enchantLevels[id]) || playerIn.capabilities.isCreativeMode)) {
/*     */       
/* 261 */       if (!this.worldPointer.isRemote) {
/*     */         
/* 263 */         List<EnchantmentData> var6 = func_178148_a(var3, id, this.enchantLevels[id]);
/* 264 */         boolean var7 = (var3.getItem() == Items.book);
/*     */         
/* 266 */         if (var6 != null) {
/*     */           
/* 268 */           playerIn.func_71013_b(var5);
/*     */           
/* 270 */           if (var7)
/*     */           {
/* 272 */             var3.setItem((Item)Items.enchanted_book);
/*     */           }
/*     */           
/* 275 */           for (int var8 = 0; var8 < var6.size(); var8++) {
/*     */             
/* 277 */             EnchantmentData var9 = var6.get(var8);
/*     */             
/* 279 */             if (var7) {
/*     */               
/* 281 */               Items.enchanted_book.addEnchantment(var3, var9);
/*     */             }
/*     */             else {
/*     */               
/* 285 */               var3.addEnchantment(var9.enchantmentobj, var9.enchantmentLevel);
/*     */             } 
/*     */           } 
/*     */           
/* 289 */           if (!playerIn.capabilities.isCreativeMode) {
/*     */             
/* 291 */             var4.stackSize -= var5;
/*     */             
/* 293 */             if (var4.stackSize <= 0)
/*     */             {
/* 295 */               this.tableInventory.setInventorySlotContents(1, null);
/*     */             }
/*     */           } 
/*     */           
/* 299 */           this.tableInventory.markDirty();
/* 300 */           this.field_178149_f = playerIn.func_175138_ci();
/* 301 */           onCraftMatrixChanged(this.tableInventory);
/*     */         } 
/*     */       } 
/*     */       
/* 305 */       return true;
/*     */     } 
/*     */ 
/*     */     
/* 309 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private List func_178148_a(ItemStack p_178148_1_, int p_178148_2_, int p_178148_3_) {
/* 315 */     this.rand.setSeed((this.field_178149_f + p_178148_2_));
/* 316 */     List var4 = EnchantmentHelper.buildEnchantmentList(this.rand, p_178148_1_, p_178148_3_);
/*     */     
/* 318 */     if (p_178148_1_.getItem() == Items.book && var4 != null && var4.size() > 1)
/*     */     {
/* 320 */       var4.remove(this.rand.nextInt(var4.size()));
/*     */     }
/*     */     
/* 323 */     return var4;
/*     */   }
/*     */ 
/*     */   
/*     */   public int func_178147_e() {
/* 328 */     ItemStack var1 = this.tableInventory.getStackInSlot(1);
/* 329 */     return (var1 == null) ? 0 : var1.stackSize;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onContainerClosed(EntityPlayer p_75134_1_) {
/* 337 */     super.onContainerClosed(p_75134_1_);
/*     */     
/* 339 */     if (!this.worldPointer.isRemote)
/*     */     {
/* 341 */       for (int var2 = 0; var2 < this.tableInventory.getSizeInventory(); var2++) {
/*     */         
/* 343 */         ItemStack var3 = this.tableInventory.getStackInSlotOnClosing(var2);
/*     */         
/* 345 */         if (var3 != null)
/*     */         {
/* 347 */           p_75134_1_.dropPlayerItemWithRandomChoice(var3, false);
/*     */         }
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canInteractWith(EntityPlayer playerIn) {
/* 355 */     return (this.worldPointer.getBlockState(this.field_178150_j).getBlock() != Blocks.enchanting_table) ? false : ((playerIn.getDistanceSq(this.field_178150_j.getX() + 0.5D, this.field_178150_j.getY() + 0.5D, this.field_178150_j.getZ() + 0.5D) <= 64.0D));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack transferStackInSlot(EntityPlayer playerIn, int index) {
/* 363 */     ItemStack var3 = null;
/* 364 */     Slot var4 = this.inventorySlots.get(index);
/*     */     
/* 366 */     if (var4 != null && var4.getHasStack()) {
/*     */       
/* 368 */       ItemStack var5 = var4.getStack();
/* 369 */       var3 = var5.copy();
/*     */       
/* 371 */       if (index == 0) {
/*     */         
/* 373 */         if (!mergeItemStack(var5, 2, 38, true))
/*     */         {
/* 375 */           return null;
/*     */         }
/*     */       }
/* 378 */       else if (index == 1) {
/*     */         
/* 380 */         if (!mergeItemStack(var5, 2, 38, true))
/*     */         {
/* 382 */           return null;
/*     */         }
/*     */       }
/* 385 */       else if (var5.getItem() == Items.dye && EnumDyeColor.func_176766_a(var5.getMetadata()) == EnumDyeColor.BLUE) {
/*     */         
/* 387 */         if (!mergeItemStack(var5, 1, 2, true))
/*     */         {
/* 389 */           return null;
/*     */         }
/*     */       }
/*     */       else {
/*     */         
/* 394 */         if (((Slot)this.inventorySlots.get(0)).getHasStack() || !((Slot)this.inventorySlots.get(0)).isItemValid(var5))
/*     */         {
/* 396 */           return null;
/*     */         }
/*     */         
/* 399 */         if (var5.hasTagCompound() && var5.stackSize == 1) {
/*     */           
/* 401 */           ((Slot)this.inventorySlots.get(0)).putStack(var5.copy());
/* 402 */           var5.stackSize = 0;
/*     */         }
/* 404 */         else if (var5.stackSize >= 1) {
/*     */           
/* 406 */           ((Slot)this.inventorySlots.get(0)).putStack(new ItemStack(var5.getItem(), 1, var5.getMetadata()));
/* 407 */           var5.stackSize--;
/*     */         } 
/*     */       } 
/*     */       
/* 411 */       if (var5.stackSize == 0) {
/*     */         
/* 413 */         var4.putStack(null);
/*     */       }
/*     */       else {
/*     */         
/* 417 */         var4.onSlotChanged();
/*     */       } 
/*     */       
/* 420 */       if (var5.stackSize == var3.stackSize)
/*     */       {
/* 422 */         return null;
/*     */       }
/*     */       
/* 425 */       var4.onPickupFromSlot(playerIn, var5);
/*     */     } 
/*     */     
/* 428 */     return var3;
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\inventory\ContainerEnchantment.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */