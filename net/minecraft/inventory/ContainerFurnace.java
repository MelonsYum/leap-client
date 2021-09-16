/*     */ package net.minecraft.inventory;
/*     */ 
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.player.InventoryPlayer;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.item.crafting.FurnaceRecipes;
/*     */ import net.minecraft.tileentity.TileEntityFurnace;
/*     */ 
/*     */ public class ContainerFurnace
/*     */   extends Container
/*     */ {
/*     */   private final IInventory tileFurnace;
/*     */   private int field_178152_f;
/*     */   private int field_178153_g;
/*     */   private int field_178154_h;
/*     */   private int field_178155_i;
/*     */   private static final String __OBFID = "CL_00001748";
/*     */   
/*     */   public ContainerFurnace(InventoryPlayer p_i45794_1_, IInventory p_i45794_2_) {
/*  20 */     this.tileFurnace = p_i45794_2_;
/*  21 */     addSlotToContainer(new Slot(p_i45794_2_, 0, 56, 17));
/*  22 */     addSlotToContainer(new SlotFurnaceFuel(p_i45794_2_, 1, 56, 53));
/*  23 */     addSlotToContainer(new SlotFurnaceOutput(p_i45794_1_.player, p_i45794_2_, 2, 116, 35));
/*     */     
/*     */     int var3;
/*  26 */     for (var3 = 0; var3 < 3; var3++) {
/*     */       
/*  28 */       for (int var4 = 0; var4 < 9; var4++)
/*     */       {
/*  30 */         addSlotToContainer(new Slot((IInventory)p_i45794_1_, var4 + var3 * 9 + 9, 8 + var4 * 18, 84 + var3 * 18));
/*     */       }
/*     */     } 
/*     */     
/*  34 */     for (var3 = 0; var3 < 9; var3++)
/*     */     {
/*  36 */       addSlotToContainer(new Slot((IInventory)p_i45794_1_, var3, 8 + var3 * 18, 142));
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void onCraftGuiOpened(ICrafting p_75132_1_) {
/*  42 */     super.onCraftGuiOpened(p_75132_1_);
/*  43 */     p_75132_1_.func_175173_a(this, this.tileFurnace);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void detectAndSendChanges() {
/*  51 */     super.detectAndSendChanges();
/*     */     
/*  53 */     for (int var1 = 0; var1 < this.crafters.size(); var1++) {
/*     */       
/*  55 */       ICrafting var2 = this.crafters.get(var1);
/*     */       
/*  57 */       if (this.field_178152_f != this.tileFurnace.getField(2))
/*     */       {
/*  59 */         var2.sendProgressBarUpdate(this, 2, this.tileFurnace.getField(2));
/*     */       }
/*     */       
/*  62 */       if (this.field_178154_h != this.tileFurnace.getField(0))
/*     */       {
/*  64 */         var2.sendProgressBarUpdate(this, 0, this.tileFurnace.getField(0));
/*     */       }
/*     */       
/*  67 */       if (this.field_178155_i != this.tileFurnace.getField(1))
/*     */       {
/*  69 */         var2.sendProgressBarUpdate(this, 1, this.tileFurnace.getField(1));
/*     */       }
/*     */       
/*  72 */       if (this.field_178153_g != this.tileFurnace.getField(3))
/*     */       {
/*  74 */         var2.sendProgressBarUpdate(this, 3, this.tileFurnace.getField(3));
/*     */       }
/*     */     } 
/*     */     
/*  78 */     this.field_178152_f = this.tileFurnace.getField(2);
/*  79 */     this.field_178154_h = this.tileFurnace.getField(0);
/*  80 */     this.field_178155_i = this.tileFurnace.getField(1);
/*  81 */     this.field_178153_g = this.tileFurnace.getField(3);
/*     */   }
/*     */ 
/*     */   
/*     */   public void updateProgressBar(int p_75137_1_, int p_75137_2_) {
/*  86 */     this.tileFurnace.setField(p_75137_1_, p_75137_2_);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canInteractWith(EntityPlayer playerIn) {
/*  91 */     return this.tileFurnace.isUseableByPlayer(playerIn);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack transferStackInSlot(EntityPlayer playerIn, int index) {
/*  99 */     ItemStack var3 = null;
/* 100 */     Slot var4 = this.inventorySlots.get(index);
/*     */     
/* 102 */     if (var4 != null && var4.getHasStack()) {
/*     */       
/* 104 */       ItemStack var5 = var4.getStack();
/* 105 */       var3 = var5.copy();
/*     */       
/* 107 */       if (index == 2) {
/*     */         
/* 109 */         if (!mergeItemStack(var5, 3, 39, true))
/*     */         {
/* 111 */           return null;
/*     */         }
/*     */         
/* 114 */         var4.onSlotChange(var5, var3);
/*     */       }
/* 116 */       else if (index != 1 && index != 0) {
/*     */         
/* 118 */         if (FurnaceRecipes.instance().getSmeltingResult(var5) != null)
/*     */         {
/* 120 */           if (!mergeItemStack(var5, 0, 1, false))
/*     */           {
/* 122 */             return null;
/*     */           }
/*     */         }
/* 125 */         else if (TileEntityFurnace.isItemFuel(var5))
/*     */         {
/* 127 */           if (!mergeItemStack(var5, 1, 2, false))
/*     */           {
/* 129 */             return null;
/*     */           }
/*     */         }
/* 132 */         else if (index >= 3 && index < 30)
/*     */         {
/* 134 */           if (!mergeItemStack(var5, 30, 39, false))
/*     */           {
/* 136 */             return null;
/*     */           }
/*     */         }
/* 139 */         else if (index >= 30 && index < 39 && !mergeItemStack(var5, 3, 30, false))
/*     */         {
/* 141 */           return null;
/*     */         }
/*     */       
/* 144 */       } else if (!mergeItemStack(var5, 3, 39, false)) {
/*     */         
/* 146 */         return null;
/*     */       } 
/*     */       
/* 149 */       if (var5.stackSize == 0) {
/*     */         
/* 151 */         var4.putStack(null);
/*     */       }
/*     */       else {
/*     */         
/* 155 */         var4.onSlotChanged();
/*     */       } 
/*     */       
/* 158 */       if (var5.stackSize == var3.stackSize)
/*     */       {
/* 160 */         return null;
/*     */       }
/*     */       
/* 163 */       var4.onPickupFromSlot(playerIn, var5);
/*     */     } 
/*     */     
/* 166 */     return var3;
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\inventory\ContainerFurnace.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */