/*     */ package net.minecraft.inventory;
/*     */ 
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.player.InventoryPlayer;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemArmor;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.item.crafting.CraftingManager;
/*     */ 
/*     */ public class ContainerPlayer
/*     */   extends Container
/*     */ {
/*  15 */   public InventoryCrafting craftMatrix = new InventoryCrafting(this, 2, 2);
/*  16 */   public IInventory craftResult = new InventoryCraftResult();
/*     */   
/*     */   public boolean isLocalWorld;
/*     */   
/*     */   private final EntityPlayer thePlayer;
/*     */   
/*     */   private static final String __OBFID = "CL_00001754";
/*     */   
/*     */   public ContainerPlayer(InventoryPlayer p_i1819_1_, boolean p_i1819_2_, EntityPlayer p_i1819_3_) {
/*  25 */     this.isLocalWorld = p_i1819_2_;
/*  26 */     this.thePlayer = p_i1819_3_;
/*  27 */     addSlotToContainer(new SlotCrafting(p_i1819_1_.player, this.craftMatrix, this.craftResult, 0, 144, 36));
/*     */     
/*     */     int var4;
/*     */     
/*  31 */     for (var4 = 0; var4 < 2; var4++) {
/*     */       
/*  33 */       for (int var5 = 0; var5 < 2; var5++)
/*     */       {
/*  35 */         addSlotToContainer(new Slot(this.craftMatrix, var5 + var4 * 2, 88 + var5 * 18, 26 + var4 * 18));
/*     */       }
/*     */     } 
/*     */     
/*  39 */     for (var4 = 0; var4 < 4; var4++) {
/*     */       
/*  41 */       final int var44 = var4;
/*  42 */       addSlotToContainer(new Slot((IInventory)p_i1819_1_, p_i1819_1_.getSizeInventory() - 1 - var4, 8, 8 + var4 * 18)
/*     */           {
/*     */             private static final String __OBFID = "CL_00001755";
/*     */             
/*     */             public int getSlotStackLimit() {
/*  47 */               return 1;
/*     */             }
/*     */             
/*     */             public boolean isItemValid(ItemStack stack) {
/*  51 */               return (stack == null) ? false : ((stack.getItem() instanceof ItemArmor) ? ((((ItemArmor)stack.getItem()).armorType == var44)) : ((stack.getItem() != Item.getItemFromBlock(Blocks.pumpkin) && stack.getItem() != Items.skull) ? false : ((var44 == 0))));
/*     */             }
/*     */             
/*     */             public String func_178171_c() {
/*  55 */               return ItemArmor.EMPTY_SLOT_NAMES[var44];
/*     */             }
/*     */           });
/*     */     } 
/*     */     
/*  60 */     for (var4 = 0; var4 < 3; var4++) {
/*     */       
/*  62 */       for (int var5 = 0; var5 < 9; var5++)
/*     */       {
/*  64 */         addSlotToContainer(new Slot((IInventory)p_i1819_1_, var5 + (var4 + 1) * 9, 8 + var5 * 18, 84 + var4 * 18));
/*     */       }
/*     */     } 
/*     */     
/*  68 */     for (var4 = 0; var4 < 9; var4++)
/*     */     {
/*  70 */       addSlotToContainer(new Slot((IInventory)p_i1819_1_, var4, 8 + var4 * 18, 142));
/*     */     }
/*     */     
/*  73 */     onCraftMatrixChanged(this.craftMatrix);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onCraftMatrixChanged(IInventory p_75130_1_) {
/*  81 */     this.craftResult.setInventorySlotContents(0, CraftingManager.getInstance().findMatchingRecipe(this.craftMatrix, this.thePlayer.worldObj));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onContainerClosed(EntityPlayer p_75134_1_) {
/*  89 */     super.onContainerClosed(p_75134_1_);
/*     */     
/*  91 */     for (int var2 = 0; var2 < 4; var2++) {
/*     */       
/*  93 */       ItemStack var3 = this.craftMatrix.getStackInSlotOnClosing(var2);
/*     */       
/*  95 */       if (var3 != null)
/*     */       {
/*  97 */         p_75134_1_.dropPlayerItemWithRandomChoice(var3, false);
/*     */       }
/*     */     } 
/*     */     
/* 101 */     this.craftResult.setInventorySlotContents(0, null);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canInteractWith(EntityPlayer playerIn) {
/* 106 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack transferStackInSlot(EntityPlayer playerIn, int index) {
/* 114 */     ItemStack var3 = null;
/* 115 */     Slot var4 = this.inventorySlots.get(index);
/*     */     
/* 117 */     if (var4 != null && var4.getHasStack()) {
/*     */       
/* 119 */       ItemStack var5 = var4.getStack();
/* 120 */       var3 = var5.copy();
/*     */       
/* 122 */       if (index == 0) {
/*     */         
/* 124 */         if (!mergeItemStack(var5, 9, 45, true))
/*     */         {
/* 126 */           return null;
/*     */         }
/*     */         
/* 129 */         var4.onSlotChange(var5, var3);
/*     */       }
/* 131 */       else if (index >= 1 && index < 5) {
/*     */         
/* 133 */         if (!mergeItemStack(var5, 9, 45, false))
/*     */         {
/* 135 */           return null;
/*     */         }
/*     */       }
/* 138 */       else if (index >= 5 && index < 9) {
/*     */         
/* 140 */         if (!mergeItemStack(var5, 9, 45, false))
/*     */         {
/* 142 */           return null;
/*     */         }
/*     */       }
/* 145 */       else if (var3.getItem() instanceof ItemArmor && !((Slot)this.inventorySlots.get(5 + ((ItemArmor)var3.getItem()).armorType)).getHasStack()) {
/*     */         
/* 147 */         int var6 = 5 + ((ItemArmor)var3.getItem()).armorType;
/*     */         
/* 149 */         if (!mergeItemStack(var5, var6, var6 + 1, false))
/*     */         {
/* 151 */           return null;
/*     */         }
/*     */       }
/* 154 */       else if (index >= 9 && index < 36) {
/*     */         
/* 156 */         if (!mergeItemStack(var5, 36, 45, false))
/*     */         {
/* 158 */           return null;
/*     */         }
/*     */       }
/* 161 */       else if (index >= 36 && index < 45) {
/*     */         
/* 163 */         if (!mergeItemStack(var5, 9, 36, false))
/*     */         {
/* 165 */           return null;
/*     */         }
/*     */       }
/* 168 */       else if (!mergeItemStack(var5, 9, 45, false)) {
/*     */         
/* 170 */         return null;
/*     */       } 
/*     */       
/* 173 */       if (var5.stackSize == 0) {
/*     */         
/* 175 */         var4.putStack(null);
/*     */       }
/*     */       else {
/*     */         
/* 179 */         var4.onSlotChanged();
/*     */       } 
/*     */       
/* 182 */       if (var5.stackSize == var3.stackSize)
/*     */       {
/* 184 */         return null;
/*     */       }
/*     */       
/* 187 */       var4.onPickupFromSlot(playerIn, var5);
/*     */     } 
/*     */     
/* 190 */     return var3;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_94530_a(ItemStack p_94530_1_, Slot p_94530_2_) {
/* 195 */     return (p_94530_2_.inventory != this.craftResult && super.func_94530_a(p_94530_1_, p_94530_2_));
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\inventory\ContainerPlayer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */