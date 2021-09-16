/*     */ package net.minecraft.inventory;
/*     */ 
/*     */ import net.minecraft.entity.IMerchant;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.stats.StatList;
/*     */ import net.minecraft.village.MerchantRecipe;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SlotMerchantResult
/*     */   extends Slot
/*     */ {
/*     */   private final InventoryMerchant theMerchantInventory;
/*     */   private EntityPlayer thePlayer;
/*     */   private int field_75231_g;
/*     */   private final IMerchant theMerchant;
/*     */   private static final String __OBFID = "CL_00001758";
/*     */   
/*     */   public SlotMerchantResult(EntityPlayer p_i1822_1_, IMerchant p_i1822_2_, InventoryMerchant p_i1822_3_, int p_i1822_4_, int p_i1822_5_, int p_i1822_6_) {
/*  24 */     super(p_i1822_3_, p_i1822_4_, p_i1822_5_, p_i1822_6_);
/*  25 */     this.thePlayer = p_i1822_1_;
/*  26 */     this.theMerchant = p_i1822_2_;
/*  27 */     this.theMerchantInventory = p_i1822_3_;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isItemValid(ItemStack stack) {
/*  35 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack decrStackSize(int p_75209_1_) {
/*  44 */     if (getHasStack())
/*     */     {
/*  46 */       this.field_75231_g += Math.min(p_75209_1_, (getStack()).stackSize);
/*     */     }
/*     */     
/*  49 */     return super.decrStackSize(p_75209_1_);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void onCrafting(ItemStack p_75210_1_, int p_75210_2_) {
/*  58 */     this.field_75231_g += p_75210_2_;
/*  59 */     onCrafting(p_75210_1_);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void onCrafting(ItemStack p_75208_1_) {
/*  67 */     p_75208_1_.onCrafting(this.thePlayer.worldObj, this.thePlayer, this.field_75231_g);
/*  68 */     this.field_75231_g = 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public void onPickupFromSlot(EntityPlayer playerIn, ItemStack stack) {
/*  73 */     onCrafting(stack);
/*  74 */     MerchantRecipe var3 = this.theMerchantInventory.getCurrentRecipe();
/*     */     
/*  76 */     if (var3 != null) {
/*     */       
/*  78 */       ItemStack var4 = this.theMerchantInventory.getStackInSlot(0);
/*  79 */       ItemStack var5 = this.theMerchantInventory.getStackInSlot(1);
/*     */       
/*  81 */       if (doTrade(var3, var4, var5) || doTrade(var3, var5, var4)) {
/*     */         
/*  83 */         this.theMerchant.useRecipe(var3);
/*  84 */         playerIn.triggerAchievement(StatList.timesTradedWithVillagerStat);
/*     */         
/*  86 */         if (var4 != null && var4.stackSize <= 0)
/*     */         {
/*  88 */           var4 = null;
/*     */         }
/*     */         
/*  91 */         if (var5 != null && var5.stackSize <= 0)
/*     */         {
/*  93 */           var5 = null;
/*     */         }
/*     */         
/*  96 */         this.theMerchantInventory.setInventorySlotContents(0, var4);
/*  97 */         this.theMerchantInventory.setInventorySlotContents(1, var5);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean doTrade(MerchantRecipe trade, ItemStack firstItem, ItemStack secondItem) {
/* 104 */     ItemStack var4 = trade.getItemToBuy();
/* 105 */     ItemStack var5 = trade.getSecondItemToBuy();
/*     */     
/* 107 */     if (firstItem != null && firstItem.getItem() == var4.getItem()) {
/*     */       
/* 109 */       if (var5 != null && secondItem != null && var5.getItem() == secondItem.getItem()) {
/*     */         
/* 111 */         firstItem.stackSize -= var4.stackSize;
/* 112 */         secondItem.stackSize -= var5.stackSize;
/* 113 */         return true;
/*     */       } 
/*     */       
/* 116 */       if (var5 == null && secondItem == null) {
/*     */         
/* 118 */         firstItem.stackSize -= var4.stackSize;
/* 119 */         return true;
/*     */       } 
/*     */     } 
/*     */     
/* 123 */     return false;
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\inventory\SlotMerchantResult.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */