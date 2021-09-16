/*     */ package net.minecraft.inventory;
/*     */ 
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.item.ItemStack;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ContainerBeacon
/*     */   extends Container
/*     */ {
/*     */   private IInventory tileBeacon;
/*     */   private final BeaconSlot beaconSlot;
/*     */   private static final String __OBFID = "CL_00001735";
/*     */   
/*     */   public ContainerBeacon(IInventory p_i45804_1_, IInventory p_i45804_2_) {
/*  19 */     this.tileBeacon = p_i45804_2_;
/*  20 */     addSlotToContainer(this.beaconSlot = new BeaconSlot(p_i45804_2_, 0, 136, 110));
/*  21 */     byte var3 = 36;
/*  22 */     short var4 = 137;
/*     */     
/*     */     int var5;
/*  25 */     for (var5 = 0; var5 < 3; var5++) {
/*     */       
/*  27 */       for (int var6 = 0; var6 < 9; var6++)
/*     */       {
/*  29 */         addSlotToContainer(new Slot(p_i45804_1_, var6 + var5 * 9 + 9, var3 + var6 * 18, var4 + var5 * 18));
/*     */       }
/*     */     } 
/*     */     
/*  33 */     for (var5 = 0; var5 < 9; var5++)
/*     */     {
/*  35 */       addSlotToContainer(new Slot(p_i45804_1_, var5, var3 + var5 * 18, 58 + var4));
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void onCraftGuiOpened(ICrafting p_75132_1_) {
/*  41 */     super.onCraftGuiOpened(p_75132_1_);
/*  42 */     p_75132_1_.func_175173_a(this, this.tileBeacon);
/*     */   }
/*     */ 
/*     */   
/*     */   public void updateProgressBar(int p_75137_1_, int p_75137_2_) {
/*  47 */     this.tileBeacon.setField(p_75137_1_, p_75137_2_);
/*     */   }
/*     */ 
/*     */   
/*     */   public IInventory func_180611_e() {
/*  52 */     return this.tileBeacon;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canInteractWith(EntityPlayer playerIn) {
/*  57 */     return this.tileBeacon.isUseableByPlayer(playerIn);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack transferStackInSlot(EntityPlayer playerIn, int index) {
/*  65 */     ItemStack var3 = null;
/*  66 */     Slot var4 = this.inventorySlots.get(index);
/*     */     
/*  68 */     if (var4 != null && var4.getHasStack()) {
/*     */       
/*  70 */       ItemStack var5 = var4.getStack();
/*  71 */       var3 = var5.copy();
/*     */       
/*  73 */       if (index == 0) {
/*     */         
/*  75 */         if (!mergeItemStack(var5, 1, 37, true))
/*     */         {
/*  77 */           return null;
/*     */         }
/*     */         
/*  80 */         var4.onSlotChange(var5, var3);
/*     */       }
/*  82 */       else if (!this.beaconSlot.getHasStack() && this.beaconSlot.isItemValid(var5) && var5.stackSize == 1) {
/*     */         
/*  84 */         if (!mergeItemStack(var5, 0, 1, false))
/*     */         {
/*  86 */           return null;
/*     */         }
/*     */       }
/*  89 */       else if (index >= 1 && index < 28) {
/*     */         
/*  91 */         if (!mergeItemStack(var5, 28, 37, false))
/*     */         {
/*  93 */           return null;
/*     */         }
/*     */       }
/*  96 */       else if (index >= 28 && index < 37) {
/*     */         
/*  98 */         if (!mergeItemStack(var5, 1, 28, false))
/*     */         {
/* 100 */           return null;
/*     */         }
/*     */       }
/* 103 */       else if (!mergeItemStack(var5, 1, 37, false)) {
/*     */         
/* 105 */         return null;
/*     */       } 
/*     */       
/* 108 */       if (var5.stackSize == 0) {
/*     */         
/* 110 */         var4.putStack(null);
/*     */       }
/*     */       else {
/*     */         
/* 114 */         var4.onSlotChanged();
/*     */       } 
/*     */       
/* 117 */       if (var5.stackSize == var3.stackSize)
/*     */       {
/* 119 */         return null;
/*     */       }
/*     */       
/* 122 */       var4.onPickupFromSlot(playerIn, var5);
/*     */     } 
/*     */     
/* 125 */     return var3;
/*     */   }
/*     */   
/*     */   class BeaconSlot
/*     */     extends Slot
/*     */   {
/*     */     private static final String __OBFID = "CL_00001736";
/*     */     
/*     */     public BeaconSlot(IInventory p_i1801_2_, int p_i1801_3_, int p_i1801_4_, int p_i1801_5_) {
/* 134 */       super(p_i1801_2_, p_i1801_3_, p_i1801_4_, p_i1801_5_);
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean isItemValid(ItemStack stack) {
/* 139 */       return (stack == null) ? false : (!(stack.getItem() != Items.emerald && stack.getItem() != Items.diamond && stack.getItem() != Items.gold_ingot && stack.getItem() != Items.iron_ingot));
/*     */     }
/*     */ 
/*     */     
/*     */     public int getSlotStackLimit() {
/* 144 */       return 1;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\inventory\ContainerBeacon.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */