/*     */ package net.minecraft.inventory;
/*     */ 
/*     */ import net.minecraft.entity.IMerchant;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.player.InventoryPlayer;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ContainerMerchant
/*     */   extends Container
/*     */ {
/*     */   private IMerchant theMerchant;
/*     */   private InventoryMerchant merchantInventory;
/*     */   private final World theWorld;
/*     */   private static final String __OBFID = "CL_00001757";
/*     */   
/*     */   public ContainerMerchant(InventoryPlayer p_i1821_1_, IMerchant p_i1821_2_, World worldIn) {
/*  21 */     this.theMerchant = p_i1821_2_;
/*  22 */     this.theWorld = worldIn;
/*  23 */     this.merchantInventory = new InventoryMerchant(p_i1821_1_.player, p_i1821_2_);
/*  24 */     addSlotToContainer(new Slot(this.merchantInventory, 0, 36, 53));
/*  25 */     addSlotToContainer(new Slot(this.merchantInventory, 1, 62, 53));
/*  26 */     addSlotToContainer(new SlotMerchantResult(p_i1821_1_.player, p_i1821_2_, this.merchantInventory, 2, 120, 53));
/*     */     
/*     */     int var4;
/*  29 */     for (var4 = 0; var4 < 3; var4++) {
/*     */       
/*  31 */       for (int var5 = 0; var5 < 9; var5++)
/*     */       {
/*  33 */         addSlotToContainer(new Slot((IInventory)p_i1821_1_, var5 + var4 * 9 + 9, 8 + var5 * 18, 84 + var4 * 18));
/*     */       }
/*     */     } 
/*     */     
/*  37 */     for (var4 = 0; var4 < 9; var4++)
/*     */     {
/*  39 */       addSlotToContainer(new Slot((IInventory)p_i1821_1_, var4, 8 + var4 * 18, 142));
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public InventoryMerchant getMerchantInventory() {
/*  45 */     return this.merchantInventory;
/*     */   }
/*     */ 
/*     */   
/*     */   public void onCraftGuiOpened(ICrafting p_75132_1_) {
/*  50 */     super.onCraftGuiOpened(p_75132_1_);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void detectAndSendChanges() {
/*  58 */     super.detectAndSendChanges();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onCraftMatrixChanged(IInventory p_75130_1_) {
/*  66 */     this.merchantInventory.resetRecipeAndSlots();
/*  67 */     super.onCraftMatrixChanged(p_75130_1_);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setCurrentRecipeIndex(int p_75175_1_) {
/*  72 */     this.merchantInventory.setCurrentRecipeIndex(p_75175_1_);
/*     */   }
/*     */ 
/*     */   
/*     */   public void updateProgressBar(int p_75137_1_, int p_75137_2_) {}
/*     */   
/*     */   public boolean canInteractWith(EntityPlayer playerIn) {
/*  79 */     return (this.theMerchant.getCustomer() == playerIn);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack transferStackInSlot(EntityPlayer playerIn, int index) {
/*  87 */     ItemStack var3 = null;
/*  88 */     Slot var4 = this.inventorySlots.get(index);
/*     */     
/*  90 */     if (var4 != null && var4.getHasStack()) {
/*     */       
/*  92 */       ItemStack var5 = var4.getStack();
/*  93 */       var3 = var5.copy();
/*     */       
/*  95 */       if (index == 2) {
/*     */         
/*  97 */         if (!mergeItemStack(var5, 3, 39, true))
/*     */         {
/*  99 */           return null;
/*     */         }
/*     */         
/* 102 */         var4.onSlotChange(var5, var3);
/*     */       }
/* 104 */       else if (index != 0 && index != 1) {
/*     */         
/* 106 */         if (index >= 3 && index < 30)
/*     */         {
/* 108 */           if (!mergeItemStack(var5, 30, 39, false))
/*     */           {
/* 110 */             return null;
/*     */           }
/*     */         }
/* 113 */         else if (index >= 30 && index < 39 && !mergeItemStack(var5, 3, 30, false))
/*     */         {
/* 115 */           return null;
/*     */         }
/*     */       
/* 118 */       } else if (!mergeItemStack(var5, 3, 39, false)) {
/*     */         
/* 120 */         return null;
/*     */       } 
/*     */       
/* 123 */       if (var5.stackSize == 0) {
/*     */         
/* 125 */         var4.putStack(null);
/*     */       }
/*     */       else {
/*     */         
/* 129 */         var4.onSlotChanged();
/*     */       } 
/*     */       
/* 132 */       if (var5.stackSize == var3.stackSize)
/*     */       {
/* 134 */         return null;
/*     */       }
/*     */       
/* 137 */       var4.onPickupFromSlot(playerIn, var5);
/*     */     } 
/*     */     
/* 140 */     return var3;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onContainerClosed(EntityPlayer p_75134_1_) {
/* 148 */     super.onContainerClosed(p_75134_1_);
/* 149 */     this.theMerchant.setCustomer(null);
/* 150 */     super.onContainerClosed(p_75134_1_);
/*     */     
/* 152 */     if (!this.theWorld.isRemote) {
/*     */       
/* 154 */       ItemStack var2 = this.merchantInventory.getStackInSlotOnClosing(0);
/*     */       
/* 156 */       if (var2 != null)
/*     */       {
/* 158 */         p_75134_1_.dropPlayerItemWithRandomChoice(var2, false);
/*     */       }
/*     */       
/* 161 */       var2 = this.merchantInventory.getStackInSlotOnClosing(1);
/*     */       
/* 163 */       if (var2 != null)
/*     */       {
/* 165 */         p_75134_1_.dropPlayerItemWithRandomChoice(var2, false);
/*     */       }
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\inventory\ContainerMerchant.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */