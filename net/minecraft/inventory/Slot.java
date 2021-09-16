/*     */ package net.minecraft.inventory;
/*     */ 
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.ItemStack;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Slot
/*     */ {
/*     */   private final int slotIndex;
/*     */   public final IInventory inventory;
/*     */   public int slotNumber;
/*     */   public int xDisplayPosition;
/*     */   public int yDisplayPosition;
/*     */   private static final String __OBFID = "CL_00001762";
/*     */   
/*     */   public Slot(IInventory p_i1824_1_, int p_i1824_2_, int p_i1824_3_, int p_i1824_4_) {
/*  26 */     this.inventory = p_i1824_1_;
/*  27 */     this.slotIndex = p_i1824_2_;
/*  28 */     this.xDisplayPosition = p_i1824_3_;
/*  29 */     this.yDisplayPosition = p_i1824_4_;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onSlotChange(ItemStack p_75220_1_, ItemStack p_75220_2_) {
/*  37 */     if (p_75220_1_ != null && p_75220_2_ != null)
/*     */     {
/*  39 */       if (p_75220_1_.getItem() == p_75220_2_.getItem()) {
/*     */         
/*  41 */         int var3 = p_75220_2_.stackSize - p_75220_1_.stackSize;
/*     */         
/*  43 */         if (var3 > 0)
/*     */         {
/*  45 */           onCrafting(p_75220_1_, var3);
/*     */         }
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void onCrafting(ItemStack p_75210_1_, int p_75210_2_) {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void onCrafting(ItemStack p_75208_1_) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void onPickupFromSlot(EntityPlayer playerIn, ItemStack stack) {
/*  64 */     onSlotChanged();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isItemValid(ItemStack stack) {
/*  72 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack getStack() {
/*  80 */     return this.inventory.getStackInSlot(this.slotIndex);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean getHasStack() {
/*  88 */     return (getStack() != null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void putStack(ItemStack p_75215_1_) {
/*  96 */     this.inventory.setInventorySlotContents(this.slotIndex, p_75215_1_);
/*  97 */     onSlotChanged();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onSlotChanged() {
/* 105 */     this.inventory.markDirty();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getSlotStackLimit() {
/* 114 */     return this.inventory.getInventoryStackLimit();
/*     */   }
/*     */ 
/*     */   
/*     */   public int func_178170_b(ItemStack p_178170_1_) {
/* 119 */     return getSlotStackLimit();
/*     */   }
/*     */ 
/*     */   
/*     */   public String func_178171_c() {
/* 124 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack decrStackSize(int p_75209_1_) {
/* 133 */     return this.inventory.decrStackSize(this.slotIndex, p_75209_1_);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isHere(IInventory p_75217_1_, int p_75217_2_) {
/* 141 */     return (p_75217_1_ == this.inventory && p_75217_2_ == this.slotIndex);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canTakeStack(EntityPlayer p_82869_1_) {
/* 149 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canBeHovered() {
/* 158 */     return true;
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\inventory\Slot.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */