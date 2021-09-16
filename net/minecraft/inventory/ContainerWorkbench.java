/*     */ package net.minecraft.inventory;
/*     */ 
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.player.InventoryPlayer;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.item.crafting.CraftingManager;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class ContainerWorkbench
/*     */   extends Container
/*     */ {
/*  14 */   public InventoryCrafting craftMatrix = new InventoryCrafting(this, 3, 3);
/*  15 */   public IInventory craftResult = new InventoryCraftResult();
/*     */   
/*     */   private World worldObj;
/*     */   private BlockPos field_178145_h;
/*     */   private static final String __OBFID = "CL_00001744";
/*     */   
/*     */   public ContainerWorkbench(InventoryPlayer p_i45800_1_, World worldIn, BlockPos p_i45800_3_) {
/*  22 */     this.worldObj = worldIn;
/*  23 */     this.field_178145_h = p_i45800_3_;
/*  24 */     addSlotToContainer(new SlotCrafting(p_i45800_1_.player, this.craftMatrix, this.craftResult, 0, 124, 35));
/*     */     
/*     */     int var4;
/*     */     
/*  28 */     for (var4 = 0; var4 < 3; var4++) {
/*     */       
/*  30 */       for (int var5 = 0; var5 < 3; var5++)
/*     */       {
/*  32 */         addSlotToContainer(new Slot(this.craftMatrix, var5 + var4 * 3, 30 + var5 * 18, 17 + var4 * 18));
/*     */       }
/*     */     } 
/*     */     
/*  36 */     for (var4 = 0; var4 < 3; var4++) {
/*     */       
/*  38 */       for (int var5 = 0; var5 < 9; var5++)
/*     */       {
/*  40 */         addSlotToContainer(new Slot((IInventory)p_i45800_1_, var5 + var4 * 9 + 9, 8 + var5 * 18, 84 + var4 * 18));
/*     */       }
/*     */     } 
/*     */     
/*  44 */     for (var4 = 0; var4 < 9; var4++)
/*     */     {
/*  46 */       addSlotToContainer(new Slot((IInventory)p_i45800_1_, var4, 8 + var4 * 18, 142));
/*     */     }
/*     */     
/*  49 */     onCraftMatrixChanged(this.craftMatrix);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onCraftMatrixChanged(IInventory p_75130_1_) {
/*  57 */     this.craftResult.setInventorySlotContents(0, CraftingManager.getInstance().findMatchingRecipe(this.craftMatrix, this.worldObj));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onContainerClosed(EntityPlayer p_75134_1_) {
/*  65 */     super.onContainerClosed(p_75134_1_);
/*     */     
/*  67 */     if (!this.worldObj.isRemote)
/*     */     {
/*  69 */       for (int var2 = 0; var2 < 9; var2++) {
/*     */         
/*  71 */         ItemStack var3 = this.craftMatrix.getStackInSlotOnClosing(var2);
/*     */         
/*  73 */         if (var3 != null)
/*     */         {
/*  75 */           p_75134_1_.dropPlayerItemWithRandomChoice(var3, false);
/*     */         }
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canInteractWith(EntityPlayer playerIn) {
/*  83 */     return (this.worldObj.getBlockState(this.field_178145_h).getBlock() != Blocks.crafting_table) ? false : ((playerIn.getDistanceSq(this.field_178145_h.getX() + 0.5D, this.field_178145_h.getY() + 0.5D, this.field_178145_h.getZ() + 0.5D) <= 64.0D));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack transferStackInSlot(EntityPlayer playerIn, int index) {
/*  91 */     ItemStack var3 = null;
/*  92 */     Slot var4 = this.inventorySlots.get(index);
/*     */     
/*  94 */     if (var4 != null && var4.getHasStack()) {
/*     */       
/*  96 */       ItemStack var5 = var4.getStack();
/*  97 */       var3 = var5.copy();
/*     */       
/*  99 */       if (index == 0) {
/*     */         
/* 101 */         if (!mergeItemStack(var5, 10, 46, true))
/*     */         {
/* 103 */           return null;
/*     */         }
/*     */         
/* 106 */         var4.onSlotChange(var5, var3);
/*     */       }
/* 108 */       else if (index >= 10 && index < 37) {
/*     */         
/* 110 */         if (!mergeItemStack(var5, 37, 46, false))
/*     */         {
/* 112 */           return null;
/*     */         }
/*     */       }
/* 115 */       else if (index >= 37 && index < 46) {
/*     */         
/* 117 */         if (!mergeItemStack(var5, 10, 37, false))
/*     */         {
/* 119 */           return null;
/*     */         }
/*     */       }
/* 122 */       else if (!mergeItemStack(var5, 10, 46, false)) {
/*     */         
/* 124 */         return null;
/*     */       } 
/*     */       
/* 127 */       if (var5.stackSize == 0) {
/*     */         
/* 129 */         var4.putStack(null);
/*     */       }
/*     */       else {
/*     */         
/* 133 */         var4.onSlotChanged();
/*     */       } 
/*     */       
/* 136 */       if (var5.stackSize == var3.stackSize)
/*     */       {
/* 138 */         return null;
/*     */       }
/*     */       
/* 141 */       var4.onPickupFromSlot(playerIn, var5);
/*     */     } 
/*     */     
/* 144 */     return var3;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_94530_a(ItemStack p_94530_1_, Slot p_94530_2_) {
/* 149 */     return (p_94530_2_.inventory != this.craftResult && super.func_94530_a(p_94530_1_, p_94530_2_));
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\inventory\ContainerWorkbench.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */