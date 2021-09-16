/*     */ package net.minecraft.inventory;
/*     */ 
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.ChatComponentText;
/*     */ import net.minecraft.util.ChatComponentTranslation;
/*     */ import net.minecraft.util.IChatComponent;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class InventoryCrafting
/*     */   implements IInventory
/*     */ {
/*     */   private final ItemStack[] stackList;
/*     */   private final int inventoryWidth;
/*     */   private final int field_174924_c;
/*     */   private final Container eventHandler;
/*     */   private static final String __OBFID = "CL_00001743";
/*     */   
/*     */   public InventoryCrafting(Container p_i1807_1_, int p_i1807_2_, int p_i1807_3_) {
/*  26 */     int var4 = p_i1807_2_ * p_i1807_3_;
/*  27 */     this.stackList = new ItemStack[var4];
/*  28 */     this.eventHandler = p_i1807_1_;
/*  29 */     this.inventoryWidth = p_i1807_2_;
/*  30 */     this.field_174924_c = p_i1807_3_;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getSizeInventory() {
/*  38 */     return this.stackList.length;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack getStackInSlot(int slotIn) {
/*  46 */     return (slotIn >= getSizeInventory()) ? null : this.stackList[slotIn];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack getStackInRowAndColumn(int p_70463_1_, int p_70463_2_) {
/*  54 */     return (p_70463_1_ >= 0 && p_70463_1_ < this.inventoryWidth && p_70463_2_ >= 0 && p_70463_2_ <= this.field_174924_c) ? getStackInSlot(p_70463_1_ + p_70463_2_ * this.inventoryWidth) : null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getName() {
/*  62 */     return "container.crafting";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasCustomName() {
/*  70 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public IChatComponent getDisplayName() {
/*  75 */     return hasCustomName() ? (IChatComponent)new ChatComponentText(getName()) : (IChatComponent)new ChatComponentTranslation(getName(), new Object[0]);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack getStackInSlotOnClosing(int index) {
/*  84 */     if (this.stackList[index] != null) {
/*     */       
/*  86 */       ItemStack var2 = this.stackList[index];
/*  87 */       this.stackList[index] = null;
/*  88 */       return var2;
/*     */     } 
/*     */ 
/*     */     
/*  92 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack decrStackSize(int index, int count) {
/* 102 */     if (this.stackList[index] != null) {
/*     */ 
/*     */ 
/*     */       
/* 106 */       if ((this.stackList[index]).stackSize <= count) {
/*     */         
/* 108 */         ItemStack itemStack = this.stackList[index];
/* 109 */         this.stackList[index] = null;
/* 110 */         this.eventHandler.onCraftMatrixChanged(this);
/* 111 */         return itemStack;
/*     */       } 
/*     */ 
/*     */       
/* 115 */       ItemStack var3 = this.stackList[index].splitStack(count);
/*     */       
/* 117 */       if ((this.stackList[index]).stackSize == 0)
/*     */       {
/* 119 */         this.stackList[index] = null;
/*     */       }
/*     */       
/* 122 */       this.eventHandler.onCraftMatrixChanged(this);
/* 123 */       return var3;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 128 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setInventorySlotContents(int index, ItemStack stack) {
/* 137 */     this.stackList[index] = stack;
/* 138 */     this.eventHandler.onCraftMatrixChanged(this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getInventoryStackLimit() {
/* 147 */     return 64;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void markDirty() {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isUseableByPlayer(EntityPlayer playerIn) {
/* 161 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void openInventory(EntityPlayer playerIn) {}
/*     */ 
/*     */   
/*     */   public void closeInventory(EntityPlayer playerIn) {}
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
/*     */   public void clearInventory() {
/* 190 */     for (int var1 = 0; var1 < this.stackList.length; var1++)
/*     */     {
/* 192 */       this.stackList[var1] = null;
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public int func_174923_h() {
/* 198 */     return this.field_174924_c;
/*     */   }
/*     */ 
/*     */   
/*     */   public int func_174922_i() {
/* 203 */     return this.inventoryWidth;
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\inventory\InventoryCrafting.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */