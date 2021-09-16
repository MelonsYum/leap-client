/*     */ package net.minecraft.inventory;
/*     */ 
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.ChatComponentText;
/*     */ import net.minecraft.util.ChatComponentTranslation;
/*     */ import net.minecraft.util.IChatComponent;
/*     */ 
/*     */ public class InventoryCraftResult
/*     */   implements IInventory
/*     */ {
/*  12 */   private ItemStack[] stackResult = new ItemStack[1];
/*     */ 
/*     */   
/*     */   private static final String __OBFID = "CL_00001760";
/*     */ 
/*     */ 
/*     */   
/*     */   public int getSizeInventory() {
/*  20 */     return 1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack getStackInSlot(int slotIn) {
/*  28 */     return this.stackResult[0];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getName() {
/*  36 */     return "Result";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasCustomName() {
/*  44 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public IChatComponent getDisplayName() {
/*  49 */     return hasCustomName() ? (IChatComponent)new ChatComponentText(getName()) : (IChatComponent)new ChatComponentTranslation(getName(), new Object[0]);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack decrStackSize(int index, int count) {
/*  58 */     if (this.stackResult[0] != null) {
/*     */       
/*  60 */       ItemStack var3 = this.stackResult[0];
/*  61 */       this.stackResult[0] = null;
/*  62 */       return var3;
/*     */     } 
/*     */ 
/*     */     
/*  66 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack getStackInSlotOnClosing(int index) {
/*  76 */     if (this.stackResult[0] != null) {
/*     */       
/*  78 */       ItemStack var2 = this.stackResult[0];
/*  79 */       this.stackResult[0] = null;
/*  80 */       return var2;
/*     */     } 
/*     */ 
/*     */     
/*  84 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setInventorySlotContents(int index, ItemStack stack) {
/*  93 */     this.stackResult[0] = stack;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getInventoryStackLimit() {
/* 102 */     return 64;
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
/* 116 */     return true;
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
/* 128 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getField(int id) {
/* 133 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setField(int id, int value) {}
/*     */   
/*     */   public int getFieldCount() {
/* 140 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public void clearInventory() {
/* 145 */     for (int var1 = 0; var1 < this.stackResult.length; var1++)
/*     */     {
/* 147 */       this.stackResult[var1] = null;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\inventory\InventoryCraftResult.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */