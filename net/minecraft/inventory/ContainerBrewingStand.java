/*     */ package net.minecraft.inventory;
/*     */ 
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.player.InventoryPlayer;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.stats.AchievementList;
/*     */ import net.minecraft.stats.StatBase;
/*     */ 
/*     */ 
/*     */ public class ContainerBrewingStand
/*     */   extends Container
/*     */ {
/*     */   private IInventory tileBrewingStand;
/*     */   private final Slot theSlot;
/*     */   private int brewTime;
/*     */   private static final String __OBFID = "CL_00001737";
/*     */   
/*     */   public ContainerBrewingStand(InventoryPlayer p_i45802_1_, IInventory p_i45802_2_) {
/*  20 */     this.tileBrewingStand = p_i45802_2_;
/*  21 */     addSlotToContainer(new Potion(p_i45802_1_.player, p_i45802_2_, 0, 56, 46));
/*  22 */     addSlotToContainer(new Potion(p_i45802_1_.player, p_i45802_2_, 1, 79, 53));
/*  23 */     addSlotToContainer(new Potion(p_i45802_1_.player, p_i45802_2_, 2, 102, 46));
/*  24 */     this.theSlot = addSlotToContainer(new Ingredient(p_i45802_2_, 3, 79, 17));
/*     */     
/*     */     int var3;
/*  27 */     for (var3 = 0; var3 < 3; var3++) {
/*     */       
/*  29 */       for (int var4 = 0; var4 < 9; var4++)
/*     */       {
/*  31 */         addSlotToContainer(new Slot((IInventory)p_i45802_1_, var4 + var3 * 9 + 9, 8 + var4 * 18, 84 + var3 * 18));
/*     */       }
/*     */     } 
/*     */     
/*  35 */     for (var3 = 0; var3 < 9; var3++)
/*     */     {
/*  37 */       addSlotToContainer(new Slot((IInventory)p_i45802_1_, var3, 8 + var3 * 18, 142));
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void onCraftGuiOpened(ICrafting p_75132_1_) {
/*  43 */     super.onCraftGuiOpened(p_75132_1_);
/*  44 */     p_75132_1_.func_175173_a(this, this.tileBrewingStand);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void detectAndSendChanges() {
/*  52 */     super.detectAndSendChanges();
/*     */     
/*  54 */     for (int var1 = 0; var1 < this.crafters.size(); var1++) {
/*     */       
/*  56 */       ICrafting var2 = this.crafters.get(var1);
/*     */       
/*  58 */       if (this.brewTime != this.tileBrewingStand.getField(0))
/*     */       {
/*  60 */         var2.sendProgressBarUpdate(this, 0, this.tileBrewingStand.getField(0));
/*     */       }
/*     */     } 
/*     */     
/*  64 */     this.brewTime = this.tileBrewingStand.getField(0);
/*     */   }
/*     */ 
/*     */   
/*     */   public void updateProgressBar(int p_75137_1_, int p_75137_2_) {
/*  69 */     this.tileBrewingStand.setField(p_75137_1_, p_75137_2_);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canInteractWith(EntityPlayer playerIn) {
/*  74 */     return this.tileBrewingStand.isUseableByPlayer(playerIn);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack transferStackInSlot(EntityPlayer playerIn, int index) {
/*  82 */     ItemStack var3 = null;
/*  83 */     Slot var4 = this.inventorySlots.get(index);
/*     */     
/*  85 */     if (var4 != null && var4.getHasStack()) {
/*     */       
/*  87 */       ItemStack var5 = var4.getStack();
/*  88 */       var3 = var5.copy();
/*     */       
/*  90 */       if ((index < 0 || index > 2) && index != 3) {
/*     */         
/*  92 */         if (!this.theSlot.getHasStack() && this.theSlot.isItemValid(var5))
/*     */         {
/*  94 */           if (!mergeItemStack(var5, 3, 4, false))
/*     */           {
/*  96 */             return null;
/*     */           }
/*     */         }
/*  99 */         else if (Potion.canHoldPotion(var3))
/*     */         {
/* 101 */           if (!mergeItemStack(var5, 0, 3, false))
/*     */           {
/* 103 */             return null;
/*     */           }
/*     */         }
/* 106 */         else if (index >= 4 && index < 31)
/*     */         {
/* 108 */           if (!mergeItemStack(var5, 31, 40, false))
/*     */           {
/* 110 */             return null;
/*     */           }
/*     */         }
/* 113 */         else if (index >= 31 && index < 40)
/*     */         {
/* 115 */           if (!mergeItemStack(var5, 4, 31, false))
/*     */           {
/* 117 */             return null;
/*     */           }
/*     */         }
/* 120 */         else if (!mergeItemStack(var5, 4, 40, false))
/*     */         {
/* 122 */           return null;
/*     */         }
/*     */       
/*     */       } else {
/*     */         
/* 127 */         if (!mergeItemStack(var5, 4, 40, true))
/*     */         {
/* 129 */           return null;
/*     */         }
/*     */         
/* 132 */         var4.onSlotChange(var5, var3);
/*     */       } 
/*     */       
/* 135 */       if (var5.stackSize == 0) {
/*     */         
/* 137 */         var4.putStack(null);
/*     */       }
/*     */       else {
/*     */         
/* 141 */         var4.onSlotChanged();
/*     */       } 
/*     */       
/* 144 */       if (var5.stackSize == var3.stackSize)
/*     */       {
/* 146 */         return null;
/*     */       }
/*     */       
/* 149 */       var4.onPickupFromSlot(playerIn, var5);
/*     */     } 
/*     */     
/* 152 */     return var3;
/*     */   }
/*     */   
/*     */   class Ingredient
/*     */     extends Slot
/*     */   {
/*     */     private static final String __OBFID = "CL_00001738";
/*     */     
/*     */     public Ingredient(IInventory p_i1803_2_, int p_i1803_3_, int p_i1803_4_, int p_i1803_5_) {
/* 161 */       super(p_i1803_2_, p_i1803_3_, p_i1803_4_, p_i1803_5_);
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean isItemValid(ItemStack stack) {
/* 166 */       return (stack != null) ? stack.getItem().isPotionIngredient(stack) : false;
/*     */     }
/*     */ 
/*     */     
/*     */     public int getSlotStackLimit() {
/* 171 */       return 64;
/*     */     }
/*     */   }
/*     */   
/*     */   static class Potion
/*     */     extends Slot
/*     */   {
/*     */     private EntityPlayer player;
/*     */     private static final String __OBFID = "CL_00001740";
/*     */     
/*     */     public Potion(EntityPlayer p_i1804_1_, IInventory p_i1804_2_, int p_i1804_3_, int p_i1804_4_, int p_i1804_5_) {
/* 182 */       super(p_i1804_2_, p_i1804_3_, p_i1804_4_, p_i1804_5_);
/* 183 */       this.player = p_i1804_1_;
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean isItemValid(ItemStack stack) {
/* 188 */       return canHoldPotion(stack);
/*     */     }
/*     */ 
/*     */     
/*     */     public int getSlotStackLimit() {
/* 193 */       return 1;
/*     */     }
/*     */ 
/*     */     
/*     */     public void onPickupFromSlot(EntityPlayer playerIn, ItemStack stack) {
/* 198 */       if (stack.getItem() == Items.potionitem && stack.getMetadata() > 0)
/*     */       {
/* 200 */         this.player.triggerAchievement((StatBase)AchievementList.potion);
/*     */       }
/*     */       
/* 203 */       super.onPickupFromSlot(playerIn, stack);
/*     */     }
/*     */ 
/*     */     
/*     */     public static boolean canHoldPotion(ItemStack p_75243_0_) {
/* 208 */       return (p_75243_0_ != null && (p_75243_0_.getItem() == Items.potionitem || p_75243_0_.getItem() == Items.glass_bottle));
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\inventory\ContainerBrewingStand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */