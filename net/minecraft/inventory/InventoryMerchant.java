/*     */ package net.minecraft.inventory;
/*     */ 
/*     */ import net.minecraft.entity.IMerchant;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.ChatComponentText;
/*     */ import net.minecraft.util.ChatComponentTranslation;
/*     */ import net.minecraft.util.IChatComponent;
/*     */ import net.minecraft.village.MerchantRecipe;
/*     */ import net.minecraft.village.MerchantRecipeList;
/*     */ 
/*     */ public class InventoryMerchant
/*     */   implements IInventory {
/*     */   private final IMerchant theMerchant;
/*  15 */   private ItemStack[] theInventory = new ItemStack[3];
/*     */   
/*     */   private final EntityPlayer thePlayer;
/*     */   private MerchantRecipe currentRecipe;
/*     */   private int currentRecipeIndex;
/*     */   private static final String __OBFID = "CL_00001756";
/*     */   
/*     */   public InventoryMerchant(EntityPlayer p_i1820_1_, IMerchant p_i1820_2_) {
/*  23 */     this.thePlayer = p_i1820_1_;
/*  24 */     this.theMerchant = p_i1820_2_;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getSizeInventory() {
/*  32 */     return this.theInventory.length;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack getStackInSlot(int slotIn) {
/*  40 */     return this.theInventory[slotIn];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack decrStackSize(int index, int count) {
/*  49 */     if (this.theInventory[index] != null) {
/*     */ 
/*     */ 
/*     */       
/*  53 */       if (index == 2) {
/*     */         
/*  55 */         ItemStack itemStack = this.theInventory[index];
/*  56 */         this.theInventory[index] = null;
/*  57 */         return itemStack;
/*     */       } 
/*  59 */       if ((this.theInventory[index]).stackSize <= count) {
/*     */         
/*  61 */         ItemStack itemStack = this.theInventory[index];
/*  62 */         this.theInventory[index] = null;
/*     */         
/*  64 */         if (inventoryResetNeededOnSlotChange(index))
/*     */         {
/*  66 */           resetRecipeAndSlots();
/*     */         }
/*     */         
/*  69 */         return itemStack;
/*     */       } 
/*     */ 
/*     */       
/*  73 */       ItemStack var3 = this.theInventory[index].splitStack(count);
/*     */       
/*  75 */       if ((this.theInventory[index]).stackSize == 0)
/*     */       {
/*  77 */         this.theInventory[index] = null;
/*     */       }
/*     */       
/*  80 */       if (inventoryResetNeededOnSlotChange(index))
/*     */       {
/*  82 */         resetRecipeAndSlots();
/*     */       }
/*     */       
/*  85 */       return var3;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/*  90 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean inventoryResetNeededOnSlotChange(int p_70469_1_) {
/*  99 */     return !(p_70469_1_ != 0 && p_70469_1_ != 1);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack getStackInSlotOnClosing(int index) {
/* 108 */     if (this.theInventory[index] != null) {
/*     */       
/* 110 */       ItemStack var2 = this.theInventory[index];
/* 111 */       this.theInventory[index] = null;
/* 112 */       return var2;
/*     */     } 
/*     */ 
/*     */     
/* 116 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setInventorySlotContents(int index, ItemStack stack) {
/* 125 */     this.theInventory[index] = stack;
/*     */     
/* 127 */     if (stack != null && stack.stackSize > getInventoryStackLimit())
/*     */     {
/* 129 */       stack.stackSize = getInventoryStackLimit();
/*     */     }
/*     */     
/* 132 */     if (inventoryResetNeededOnSlotChange(index))
/*     */     {
/* 134 */       resetRecipeAndSlots();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getName() {
/* 143 */     return "mob.villager";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasCustomName() {
/* 151 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public IChatComponent getDisplayName() {
/* 156 */     return hasCustomName() ? (IChatComponent)new ChatComponentText(getName()) : (IChatComponent)new ChatComponentTranslation(getName(), new Object[0]);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getInventoryStackLimit() {
/* 165 */     return 64;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isUseableByPlayer(EntityPlayer playerIn) {
/* 173 */     return (this.theMerchant.getCustomer() == playerIn);
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
/* 185 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void markDirty() {
/* 194 */     resetRecipeAndSlots();
/*     */   }
/*     */ 
/*     */   
/*     */   public void resetRecipeAndSlots() {
/* 199 */     this.currentRecipe = null;
/* 200 */     ItemStack var1 = this.theInventory[0];
/* 201 */     ItemStack var2 = this.theInventory[1];
/*     */     
/* 203 */     if (var1 == null) {
/*     */       
/* 205 */       var1 = var2;
/* 206 */       var2 = null;
/*     */     } 
/*     */     
/* 209 */     if (var1 == null) {
/*     */       
/* 211 */       setInventorySlotContents(2, null);
/*     */     }
/*     */     else {
/*     */       
/* 215 */       MerchantRecipeList var3 = this.theMerchant.getRecipes(this.thePlayer);
/*     */       
/* 217 */       if (var3 != null) {
/*     */         
/* 219 */         MerchantRecipe var4 = var3.canRecipeBeUsed(var1, var2, this.currentRecipeIndex);
/*     */         
/* 221 */         if (var4 != null && !var4.isRecipeDisabled()) {
/*     */           
/* 223 */           this.currentRecipe = var4;
/* 224 */           setInventorySlotContents(2, var4.getItemToSell().copy());
/*     */         }
/* 226 */         else if (var2 != null) {
/*     */           
/* 228 */           var4 = var3.canRecipeBeUsed(var2, var1, this.currentRecipeIndex);
/*     */           
/* 230 */           if (var4 != null && !var4.isRecipeDisabled())
/*     */           {
/* 232 */             this.currentRecipe = var4;
/* 233 */             setInventorySlotContents(2, var4.getItemToSell().copy());
/*     */           }
/*     */           else
/*     */           {
/* 237 */             setInventorySlotContents(2, null);
/*     */           }
/*     */         
/*     */         } else {
/*     */           
/* 242 */           setInventorySlotContents(2, null);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 247 */     this.theMerchant.verifySellingItem(getStackInSlot(2));
/*     */   }
/*     */ 
/*     */   
/*     */   public MerchantRecipe getCurrentRecipe() {
/* 252 */     return this.currentRecipe;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setCurrentRecipeIndex(int p_70471_1_) {
/* 257 */     this.currentRecipeIndex = p_70471_1_;
/* 258 */     resetRecipeAndSlots();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getField(int id) {
/* 263 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setField(int id, int value) {}
/*     */   
/*     */   public int getFieldCount() {
/* 270 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public void clearInventory() {
/* 275 */     for (int var1 = 0; var1 < this.theInventory.length; var1++)
/*     */     {
/* 277 */       this.theInventory[var1] = null;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\inventory\InventoryMerchant.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */