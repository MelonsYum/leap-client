/*     */ package net.minecraft.inventory;
/*     */ 
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.player.InventoryPlayer;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.ChatComponentText;
/*     */ import net.minecraft.util.ChatComponentTranslation;
/*     */ import net.minecraft.util.IChatComponent;
/*     */ import net.minecraft.world.ILockableContainer;
/*     */ import net.minecraft.world.LockCode;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class InventoryLargeChest
/*     */   implements ILockableContainer
/*     */ {
/*     */   private String name;
/*     */   private ILockableContainer upperChest;
/*     */   private ILockableContainer lowerChest;
/*     */   private static final String __OBFID = "CL_00001507";
/*     */   
/*     */   public InventoryLargeChest(String p_i45905_1_, ILockableContainer p_i45905_2_, ILockableContainer p_i45905_3_) {
/*  26 */     this.name = p_i45905_1_;
/*     */     
/*  28 */     if (p_i45905_2_ == null)
/*     */     {
/*  30 */       p_i45905_2_ = p_i45905_3_;
/*     */     }
/*     */     
/*  33 */     if (p_i45905_3_ == null)
/*     */     {
/*  35 */       p_i45905_3_ = p_i45905_2_;
/*     */     }
/*     */     
/*  38 */     this.upperChest = p_i45905_2_;
/*  39 */     this.lowerChest = p_i45905_3_;
/*     */     
/*  41 */     if (p_i45905_2_.isLocked()) {
/*     */       
/*  43 */       p_i45905_3_.setLockCode(p_i45905_2_.getLockCode());
/*     */     }
/*  45 */     else if (p_i45905_3_.isLocked()) {
/*     */       
/*  47 */       p_i45905_2_.setLockCode(p_i45905_3_.getLockCode());
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getSizeInventory() {
/*  56 */     return this.upperChest.getSizeInventory() + this.lowerChest.getSizeInventory();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isPartOfLargeChest(IInventory p_90010_1_) {
/*  64 */     return !(this.upperChest != p_90010_1_ && this.lowerChest != p_90010_1_);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getName() {
/*  72 */     return this.upperChest.hasCustomName() ? this.upperChest.getName() : (this.lowerChest.hasCustomName() ? this.lowerChest.getName() : this.name);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasCustomName() {
/*  80 */     return !(!this.upperChest.hasCustomName() && !this.lowerChest.hasCustomName());
/*     */   }
/*     */ 
/*     */   
/*     */   public IChatComponent getDisplayName() {
/*  85 */     return hasCustomName() ? (IChatComponent)new ChatComponentText(getName()) : (IChatComponent)new ChatComponentTranslation(getName(), new Object[0]);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack getStackInSlot(int slotIn) {
/*  93 */     return (slotIn >= this.upperChest.getSizeInventory()) ? this.lowerChest.getStackInSlot(slotIn - this.upperChest.getSizeInventory()) : this.upperChest.getStackInSlot(slotIn);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack decrStackSize(int index, int count) {
/* 102 */     return (index >= this.upperChest.getSizeInventory()) ? this.lowerChest.decrStackSize(index - this.upperChest.getSizeInventory(), count) : this.upperChest.decrStackSize(index, count);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack getStackInSlotOnClosing(int index) {
/* 111 */     return (index >= this.upperChest.getSizeInventory()) ? this.lowerChest.getStackInSlotOnClosing(index - this.upperChest.getSizeInventory()) : this.upperChest.getStackInSlotOnClosing(index);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setInventorySlotContents(int index, ItemStack stack) {
/* 119 */     if (index >= this.upperChest.getSizeInventory()) {
/*     */       
/* 121 */       this.lowerChest.setInventorySlotContents(index - this.upperChest.getSizeInventory(), stack);
/*     */     }
/*     */     else {
/*     */       
/* 125 */       this.upperChest.setInventorySlotContents(index, stack);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getInventoryStackLimit() {
/* 135 */     return this.upperChest.getInventoryStackLimit();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void markDirty() {
/* 144 */     this.upperChest.markDirty();
/* 145 */     this.lowerChest.markDirty();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isUseableByPlayer(EntityPlayer playerIn) {
/* 153 */     return (this.upperChest.isUseableByPlayer(playerIn) && this.lowerChest.isUseableByPlayer(playerIn));
/*     */   }
/*     */ 
/*     */   
/*     */   public void openInventory(EntityPlayer playerIn) {
/* 158 */     this.upperChest.openInventory(playerIn);
/* 159 */     this.lowerChest.openInventory(playerIn);
/*     */   }
/*     */ 
/*     */   
/*     */   public void closeInventory(EntityPlayer playerIn) {
/* 164 */     this.upperChest.closeInventory(playerIn);
/* 165 */     this.lowerChest.closeInventory(playerIn);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isItemValidForSlot(int index, ItemStack stack) {
/* 173 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getField(int id) {
/* 178 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setField(int id, int value) {}
/*     */   
/*     */   public int getFieldCount() {
/* 185 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isLocked() {
/* 190 */     return !(!this.upperChest.isLocked() && !this.lowerChest.isLocked());
/*     */   }
/*     */ 
/*     */   
/*     */   public void setLockCode(LockCode code) {
/* 195 */     this.upperChest.setLockCode(code);
/* 196 */     this.lowerChest.setLockCode(code);
/*     */   }
/*     */ 
/*     */   
/*     */   public LockCode getLockCode() {
/* 201 */     return this.upperChest.getLockCode();
/*     */   }
/*     */ 
/*     */   
/*     */   public String getGuiID() {
/* 206 */     return this.upperChest.getGuiID();
/*     */   }
/*     */ 
/*     */   
/*     */   public Container createContainer(InventoryPlayer playerInventory, EntityPlayer playerIn) {
/* 211 */     return new ContainerChest((IInventory)playerInventory, (IInventory)this, playerIn);
/*     */   }
/*     */ 
/*     */   
/*     */   public void clearInventory() {
/* 216 */     this.upperChest.clearInventory();
/* 217 */     this.lowerChest.clearInventory();
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\inventory\InventoryLargeChest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */