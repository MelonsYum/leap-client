/*     */ package net.minecraft.inventory;
/*     */ 
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemPickaxe;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.item.crafting.CraftingManager;
/*     */ import net.minecraft.stats.AchievementList;
/*     */ import net.minecraft.stats.StatBase;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SlotCrafting
/*     */   extends Slot
/*     */ {
/*     */   private final InventoryCrafting craftMatrix;
/*     */   private final EntityPlayer thePlayer;
/*     */   private int amountCrafted;
/*     */   private static final String __OBFID = "CL_00001761";
/*     */   
/*     */   public SlotCrafting(EntityPlayer p_i45790_1_, InventoryCrafting p_i45790_2_, IInventory p_i45790_3_, int p_i45790_4_, int p_i45790_5_, int p_i45790_6_) {
/*  30 */     super(p_i45790_3_, p_i45790_4_, p_i45790_5_, p_i45790_6_);
/*  31 */     this.thePlayer = p_i45790_1_;
/*  32 */     this.craftMatrix = p_i45790_2_;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isItemValid(ItemStack stack) {
/*  40 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack decrStackSize(int p_75209_1_) {
/*  49 */     if (getHasStack())
/*     */     {
/*  51 */       this.amountCrafted += Math.min(p_75209_1_, (getStack()).stackSize);
/*     */     }
/*     */     
/*  54 */     return super.decrStackSize(p_75209_1_);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void onCrafting(ItemStack p_75210_1_, int p_75210_2_) {
/*  63 */     this.amountCrafted += p_75210_2_;
/*  64 */     onCrafting(p_75210_1_);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void onCrafting(ItemStack p_75208_1_) {
/*  72 */     if (this.amountCrafted > 0)
/*     */     {
/*  74 */       p_75208_1_.onCrafting(this.thePlayer.worldObj, this.thePlayer, this.amountCrafted);
/*     */     }
/*     */     
/*  77 */     this.amountCrafted = 0;
/*     */     
/*  79 */     if (p_75208_1_.getItem() == Item.getItemFromBlock(Blocks.crafting_table))
/*     */     {
/*  81 */       this.thePlayer.triggerAchievement((StatBase)AchievementList.buildWorkBench);
/*     */     }
/*     */     
/*  84 */     if (p_75208_1_.getItem() instanceof ItemPickaxe)
/*     */     {
/*  86 */       this.thePlayer.triggerAchievement((StatBase)AchievementList.buildPickaxe);
/*     */     }
/*     */     
/*  89 */     if (p_75208_1_.getItem() == Item.getItemFromBlock(Blocks.furnace))
/*     */     {
/*  91 */       this.thePlayer.triggerAchievement((StatBase)AchievementList.buildFurnace);
/*     */     }
/*     */     
/*  94 */     if (p_75208_1_.getItem() instanceof net.minecraft.item.ItemHoe)
/*     */     {
/*  96 */       this.thePlayer.triggerAchievement((StatBase)AchievementList.buildHoe);
/*     */     }
/*     */     
/*  99 */     if (p_75208_1_.getItem() == Items.bread)
/*     */     {
/* 101 */       this.thePlayer.triggerAchievement((StatBase)AchievementList.makeBread);
/*     */     }
/*     */     
/* 104 */     if (p_75208_1_.getItem() == Items.cake)
/*     */     {
/* 106 */       this.thePlayer.triggerAchievement((StatBase)AchievementList.bakeCake);
/*     */     }
/*     */     
/* 109 */     if (p_75208_1_.getItem() instanceof ItemPickaxe && ((ItemPickaxe)p_75208_1_.getItem()).getToolMaterial() != Item.ToolMaterial.WOOD)
/*     */     {
/* 111 */       this.thePlayer.triggerAchievement((StatBase)AchievementList.buildBetterPickaxe);
/*     */     }
/*     */     
/* 114 */     if (p_75208_1_.getItem() instanceof net.minecraft.item.ItemSword)
/*     */     {
/* 116 */       this.thePlayer.triggerAchievement((StatBase)AchievementList.buildSword);
/*     */     }
/*     */     
/* 119 */     if (p_75208_1_.getItem() == Item.getItemFromBlock(Blocks.enchanting_table))
/*     */     {
/* 121 */       this.thePlayer.triggerAchievement((StatBase)AchievementList.enchantments);
/*     */     }
/*     */     
/* 124 */     if (p_75208_1_.getItem() == Item.getItemFromBlock(Blocks.bookshelf))
/*     */     {
/* 126 */       this.thePlayer.triggerAchievement((StatBase)AchievementList.bookcase);
/*     */     }
/*     */     
/* 129 */     if (p_75208_1_.getItem() == Items.golden_apple && p_75208_1_.getMetadata() == 1)
/*     */     {
/* 131 */       this.thePlayer.triggerAchievement((StatBase)AchievementList.overpowered);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void onPickupFromSlot(EntityPlayer playerIn, ItemStack stack) {
/* 137 */     onCrafting(stack);
/* 138 */     ItemStack[] var3 = CraftingManager.getInstance().func_180303_b(this.craftMatrix, playerIn.worldObj);
/*     */     
/* 140 */     for (int var4 = 0; var4 < var3.length; var4++) {
/*     */       
/* 142 */       ItemStack var5 = this.craftMatrix.getStackInSlot(var4);
/* 143 */       ItemStack var6 = var3[var4];
/*     */       
/* 145 */       if (var5 != null)
/*     */       {
/* 147 */         this.craftMatrix.decrStackSize(var4, 1);
/*     */       }
/*     */       
/* 150 */       if (var6 != null)
/*     */       {
/* 152 */         if (this.craftMatrix.getStackInSlot(var4) == null) {
/*     */           
/* 154 */           this.craftMatrix.setInventorySlotContents(var4, var6);
/*     */         }
/* 156 */         else if (!this.thePlayer.inventory.addItemStackToInventory(var6)) {
/*     */           
/* 158 */           this.thePlayer.dropPlayerItemWithRandomChoice(var6, false);
/*     */         } 
/*     */       }
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\inventory\SlotCrafting.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */