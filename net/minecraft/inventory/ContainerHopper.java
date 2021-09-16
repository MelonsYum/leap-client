/*    */ package net.minecraft.inventory;
/*    */ 
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.entity.player.InventoryPlayer;
/*    */ import net.minecraft.item.ItemStack;
/*    */ 
/*    */ public class ContainerHopper
/*    */   extends Container
/*    */ {
/*    */   private final IInventory field_94538_a;
/*    */   private static final String __OBFID = "CL_00001750";
/*    */   
/*    */   public ContainerHopper(InventoryPlayer p_i45792_1_, IInventory p_i45792_2_, EntityPlayer p_i45792_3_) {
/* 14 */     this.field_94538_a = p_i45792_2_;
/* 15 */     p_i45792_2_.openInventory(p_i45792_3_);
/* 16 */     byte var4 = 51;
/*    */     
/*    */     int var5;
/* 19 */     for (var5 = 0; var5 < p_i45792_2_.getSizeInventory(); var5++)
/*    */     {
/* 21 */       addSlotToContainer(new Slot(p_i45792_2_, var5, 44 + var5 * 18, 20));
/*    */     }
/*    */     
/* 24 */     for (var5 = 0; var5 < 3; var5++) {
/*    */       
/* 26 */       for (int var6 = 0; var6 < 9; var6++)
/*    */       {
/* 28 */         addSlotToContainer(new Slot((IInventory)p_i45792_1_, var6 + var5 * 9 + 9, 8 + var6 * 18, var5 * 18 + var4));
/*    */       }
/*    */     } 
/*    */     
/* 32 */     for (var5 = 0; var5 < 9; var5++)
/*    */     {
/* 34 */       addSlotToContainer(new Slot((IInventory)p_i45792_1_, var5, 8 + var5 * 18, 58 + var4));
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean canInteractWith(EntityPlayer playerIn) {
/* 40 */     return this.field_94538_a.isUseableByPlayer(playerIn);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public ItemStack transferStackInSlot(EntityPlayer playerIn, int index) {
/* 48 */     ItemStack var3 = null;
/* 49 */     Slot var4 = this.inventorySlots.get(index);
/*    */     
/* 51 */     if (var4 != null && var4.getHasStack()) {
/*    */       
/* 53 */       ItemStack var5 = var4.getStack();
/* 54 */       var3 = var5.copy();
/*    */       
/* 56 */       if (index < this.field_94538_a.getSizeInventory()) {
/*    */         
/* 58 */         if (!mergeItemStack(var5, this.field_94538_a.getSizeInventory(), this.inventorySlots.size(), true))
/*    */         {
/* 60 */           return null;
/*    */         }
/*    */       }
/* 63 */       else if (!mergeItemStack(var5, 0, this.field_94538_a.getSizeInventory(), false)) {
/*    */         
/* 65 */         return null;
/*    */       } 
/*    */       
/* 68 */       if (var5.stackSize == 0) {
/*    */         
/* 70 */         var4.putStack(null);
/*    */       }
/*    */       else {
/*    */         
/* 74 */         var4.onSlotChanged();
/*    */       } 
/*    */     } 
/*    */     
/* 78 */     return var3;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void onContainerClosed(EntityPlayer p_75134_1_) {
/* 86 */     super.onContainerClosed(p_75134_1_);
/* 87 */     this.field_94538_a.closeInventory(p_75134_1_);
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\inventory\ContainerHopper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */