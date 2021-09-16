/*    */ package net.minecraft.inventory;
/*    */ 
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.item.ItemStack;
/*    */ 
/*    */ public class ContainerDispenser
/*    */   extends Container
/*    */ {
/*    */   private IInventory field_178146_a;
/*    */   private static final String __OBFID = "CL_00001763";
/*    */   
/*    */   public ContainerDispenser(IInventory p_i45799_1_, IInventory p_i45799_2_) {
/* 13 */     this.field_178146_a = p_i45799_2_;
/*    */     
/*    */     int var3;
/*    */     
/* 17 */     for (var3 = 0; var3 < 3; var3++) {
/*    */       
/* 19 */       for (int var4 = 0; var4 < 3; var4++)
/*    */       {
/* 21 */         addSlotToContainer(new Slot(p_i45799_2_, var4 + var3 * 3, 62 + var4 * 18, 17 + var3 * 18));
/*    */       }
/*    */     } 
/*    */     
/* 25 */     for (var3 = 0; var3 < 3; var3++) {
/*    */       
/* 27 */       for (int var4 = 0; var4 < 9; var4++)
/*    */       {
/* 29 */         addSlotToContainer(new Slot(p_i45799_1_, var4 + var3 * 9 + 9, 8 + var4 * 18, 84 + var3 * 18));
/*    */       }
/*    */     } 
/*    */     
/* 33 */     for (var3 = 0; var3 < 9; var3++)
/*    */     {
/* 35 */       addSlotToContainer(new Slot(p_i45799_1_, var3, 8 + var3 * 18, 142));
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean canInteractWith(EntityPlayer playerIn) {
/* 41 */     return this.field_178146_a.isUseableByPlayer(playerIn);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public ItemStack transferStackInSlot(EntityPlayer playerIn, int index) {
/* 49 */     ItemStack var3 = null;
/* 50 */     Slot var4 = this.inventorySlots.get(index);
/*    */     
/* 52 */     if (var4 != null && var4.getHasStack()) {
/*    */       
/* 54 */       ItemStack var5 = var4.getStack();
/* 55 */       var3 = var5.copy();
/*    */       
/* 57 */       if (index < 9) {
/*    */         
/* 59 */         if (!mergeItemStack(var5, 9, 45, true))
/*    */         {
/* 61 */           return null;
/*    */         }
/*    */       }
/* 64 */       else if (!mergeItemStack(var5, 0, 9, false)) {
/*    */         
/* 66 */         return null;
/*    */       } 
/*    */       
/* 69 */       if (var5.stackSize == 0) {
/*    */         
/* 71 */         var4.putStack(null);
/*    */       }
/*    */       else {
/*    */         
/* 75 */         var4.onSlotChanged();
/*    */       } 
/*    */       
/* 78 */       if (var5.stackSize == var3.stackSize)
/*    */       {
/* 80 */         return null;
/*    */       }
/*    */       
/* 83 */       var4.onPickupFromSlot(playerIn, var5);
/*    */     } 
/*    */     
/* 86 */     return var3;
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\inventory\ContainerDispenser.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */