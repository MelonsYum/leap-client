/*     */ package net.minecraft.inventory;
/*     */ 
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.passive.EntityHorse;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.item.ItemStack;
/*     */ 
/*     */ public class ContainerHorseInventory
/*     */   extends Container {
/*     */   private IInventory field_111243_a;
/*     */   private EntityHorse theHorse;
/*     */   private static final String __OBFID = "CL_00001751";
/*     */   
/*     */   public ContainerHorseInventory(IInventory p_i45791_1_, IInventory p_i45791_2_, final EntityHorse p_i45791_3_, EntityPlayer p_i45791_4_) {
/*  16 */     this.field_111243_a = p_i45791_2_;
/*  17 */     this.theHorse = p_i45791_3_;
/*  18 */     byte var5 = 3;
/*  19 */     p_i45791_2_.openInventory(p_i45791_4_);
/*  20 */     int var6 = (var5 - 4) * 18;
/*  21 */     addSlotToContainer(new Slot(p_i45791_2_, 0, 8, 18)
/*     */         {
/*     */           private static final String __OBFID = "CL_00001752";
/*     */           
/*     */           public boolean isItemValid(ItemStack stack) {
/*  26 */             return (super.isItemValid(stack) && stack.getItem() == Items.saddle && !getHasStack());
/*     */           }
/*     */         });
/*  29 */     addSlotToContainer(new Slot(p_i45791_2_, 1, 8, 36)
/*     */         {
/*     */           private static final String __OBFID = "CL_00001753";
/*     */           
/*     */           public boolean isItemValid(ItemStack stack) {
/*  34 */             return (super.isItemValid(stack) && p_i45791_3_.canWearArmor() && EntityHorse.func_146085_a(stack.getItem()));
/*     */           }
/*     */           
/*     */           public boolean canBeHovered() {
/*  38 */             return p_i45791_3_.canWearArmor();
/*     */           }
/*     */         });
/*     */ 
/*     */ 
/*     */     
/*  44 */     if (p_i45791_3_.isChested())
/*     */     {
/*  46 */       for (int i = 0; i < var5; i++) {
/*     */         
/*  48 */         for (int var8 = 0; var8 < 5; var8++)
/*     */         {
/*  50 */           addSlotToContainer(new Slot(p_i45791_2_, 2 + var8 + i * 5, 80 + var8 * 18, 18 + i * 18));
/*     */         }
/*     */       } 
/*     */     }
/*     */     int var7;
/*  55 */     for (var7 = 0; var7 < 3; var7++) {
/*     */       
/*  57 */       for (int var8 = 0; var8 < 9; var8++)
/*     */       {
/*  59 */         addSlotToContainer(new Slot(p_i45791_1_, var8 + var7 * 9 + 9, 8 + var8 * 18, 102 + var7 * 18 + var6));
/*     */       }
/*     */     } 
/*     */     
/*  63 */     for (var7 = 0; var7 < 9; var7++)
/*     */     {
/*  65 */       addSlotToContainer(new Slot(p_i45791_1_, var7, 8 + var7 * 18, 160 + var6));
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canInteractWith(EntityPlayer playerIn) {
/*  71 */     return (this.field_111243_a.isUseableByPlayer(playerIn) && this.theHorse.isEntityAlive() && this.theHorse.getDistanceToEntity((Entity)playerIn) < 8.0F);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack transferStackInSlot(EntityPlayer playerIn, int index) {
/*  79 */     ItemStack var3 = null;
/*  80 */     Slot var4 = this.inventorySlots.get(index);
/*     */     
/*  82 */     if (var4 != null && var4.getHasStack()) {
/*     */       
/*  84 */       ItemStack var5 = var4.getStack();
/*  85 */       var3 = var5.copy();
/*     */       
/*  87 */       if (index < this.field_111243_a.getSizeInventory()) {
/*     */         
/*  89 */         if (!mergeItemStack(var5, this.field_111243_a.getSizeInventory(), this.inventorySlots.size(), true))
/*     */         {
/*  91 */           return null;
/*     */         }
/*     */       }
/*  94 */       else if (getSlot(1).isItemValid(var5) && !getSlot(1).getHasStack()) {
/*     */         
/*  96 */         if (!mergeItemStack(var5, 1, 2, false))
/*     */         {
/*  98 */           return null;
/*     */         }
/*     */       }
/* 101 */       else if (getSlot(0).isItemValid(var5)) {
/*     */         
/* 103 */         if (!mergeItemStack(var5, 0, 1, false))
/*     */         {
/* 105 */           return null;
/*     */         }
/*     */       }
/* 108 */       else if (this.field_111243_a.getSizeInventory() <= 2 || !mergeItemStack(var5, 2, this.field_111243_a.getSizeInventory(), false)) {
/*     */         
/* 110 */         return null;
/*     */       } 
/*     */       
/* 113 */       if (var5.stackSize == 0) {
/*     */         
/* 115 */         var4.putStack(null);
/*     */       }
/*     */       else {
/*     */         
/* 119 */         var4.onSlotChanged();
/*     */       } 
/*     */     } 
/*     */     
/* 123 */     return var3;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onContainerClosed(EntityPlayer p_75134_1_) {
/* 131 */     super.onContainerClosed(p_75134_1_);
/* 132 */     this.field_111243_a.closeInventory(p_75134_1_);
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\inventory\ContainerHorseInventory.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */