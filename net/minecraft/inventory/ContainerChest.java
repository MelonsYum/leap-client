/*     */ package net.minecraft.inventory;
/*     */ 
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.ItemStack;
/*     */ 
/*     */ public class ContainerChest
/*     */   extends Container
/*     */ {
/*     */   private IInventory lowerChestInventory;
/*     */   private int numRows;
/*     */   private static final String __OBFID = "CL_00001742";
/*     */   
/*     */   public ContainerChest(IInventory p_i45801_1_, IInventory p_i45801_2_, EntityPlayer p_i45801_3_) {
/*  14 */     this.lowerChestInventory = p_i45801_2_;
/*  15 */     this.numRows = p_i45801_2_.getSizeInventory() / 9;
/*  16 */     p_i45801_2_.openInventory(p_i45801_3_);
/*  17 */     int var4 = (this.numRows - 4) * 18;
/*     */     
/*     */     int var5;
/*     */     
/*  21 */     for (var5 = 0; var5 < this.numRows; var5++) {
/*     */       
/*  23 */       for (int var6 = 0; var6 < 9; var6++)
/*     */       {
/*  25 */         addSlotToContainer(new Slot(p_i45801_2_, var6 + var5 * 9, 8 + var6 * 18, 18 + var5 * 18));
/*     */       }
/*     */     } 
/*     */     
/*  29 */     for (var5 = 0; var5 < 3; var5++) {
/*     */       
/*  31 */       for (int var6 = 0; var6 < 9; var6++)
/*     */       {
/*  33 */         addSlotToContainer(new Slot(p_i45801_1_, var6 + var5 * 9 + 9, 8 + var6 * 18, 103 + var5 * 18 + var4));
/*     */       }
/*     */     } 
/*     */     
/*  37 */     for (var5 = 0; var5 < 9; var5++)
/*     */     {
/*  39 */       addSlotToContainer(new Slot(p_i45801_1_, var5, 8 + var5 * 18, 161 + var4));
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canInteractWith(EntityPlayer playerIn) {
/*  45 */     return this.lowerChestInventory.isUseableByPlayer(playerIn);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack transferStackInSlot(EntityPlayer playerIn, int index) {
/*  53 */     ItemStack var3 = null;
/*  54 */     Slot var4 = this.inventorySlots.get(index);
/*     */     
/*  56 */     if (var4 != null && var4.getHasStack()) {
/*     */       
/*  58 */       ItemStack var5 = var4.getStack();
/*  59 */       var3 = var5.copy();
/*     */       
/*  61 */       if (index < this.numRows * 9) {
/*     */         
/*  63 */         if (!mergeItemStack(var5, this.numRows * 9, this.inventorySlots.size(), true))
/*     */         {
/*  65 */           return null;
/*     */         }
/*     */       }
/*  68 */       else if (!mergeItemStack(var5, 0, this.numRows * 9, false)) {
/*     */         
/*  70 */         return null;
/*     */       } 
/*     */       
/*  73 */       if (var5.stackSize == 0) {
/*     */         
/*  75 */         var4.putStack(null);
/*     */       }
/*     */       else {
/*     */         
/*  79 */         var4.onSlotChanged();
/*     */       } 
/*     */     } 
/*     */     
/*  83 */     return var3;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onContainerClosed(EntityPlayer p_75134_1_) {
/*  91 */     super.onContainerClosed(p_75134_1_);
/*  92 */     this.lowerChestInventory.closeInventory(p_75134_1_);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IInventory getLowerChestInventory() {
/* 100 */     return this.lowerChestInventory;
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\inventory\ContainerChest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */