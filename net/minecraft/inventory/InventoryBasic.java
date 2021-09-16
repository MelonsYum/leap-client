/*     */ package net.minecraft.inventory;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import java.util.List;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.ChatComponentText;
/*     */ import net.minecraft.util.ChatComponentTranslation;
/*     */ import net.minecraft.util.IChatComponent;
/*     */ 
/*     */ public class InventoryBasic
/*     */   implements IInventory
/*     */ {
/*     */   private String inventoryTitle;
/*     */   private int slotsCount;
/*     */   private ItemStack[] inventoryContents;
/*     */   private List field_70480_d;
/*     */   private boolean hasCustomName;
/*     */   private static final String __OBFID = "CL_00001514";
/*     */   
/*     */   public InventoryBasic(String p_i1561_1_, boolean p_i1561_2_, int p_i1561_3_) {
/*  22 */     this.inventoryTitle = p_i1561_1_;
/*  23 */     this.hasCustomName = p_i1561_2_;
/*  24 */     this.slotsCount = p_i1561_3_;
/*  25 */     this.inventoryContents = new ItemStack[p_i1561_3_];
/*     */   }
/*     */ 
/*     */   
/*     */   public InventoryBasic(IChatComponent p_i45902_1_, int p_i45902_2_) {
/*  30 */     this(p_i45902_1_.getUnformattedText(), true, p_i45902_2_);
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_110134_a(IInvBasic p_110134_1_) {
/*  35 */     if (this.field_70480_d == null)
/*     */     {
/*  37 */       this.field_70480_d = Lists.newArrayList();
/*     */     }
/*     */     
/*  40 */     this.field_70480_d.add(p_110134_1_);
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_110132_b(IInvBasic p_110132_1_) {
/*  45 */     this.field_70480_d.remove(p_110132_1_);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack getStackInSlot(int slotIn) {
/*  53 */     return (slotIn >= 0 && slotIn < this.inventoryContents.length) ? this.inventoryContents[slotIn] : null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack decrStackSize(int index, int count) {
/*  62 */     if (this.inventoryContents[index] != null) {
/*     */ 
/*     */ 
/*     */       
/*  66 */       if ((this.inventoryContents[index]).stackSize <= count) {
/*     */         
/*  68 */         ItemStack itemStack = this.inventoryContents[index];
/*  69 */         this.inventoryContents[index] = null;
/*  70 */         markDirty();
/*  71 */         return itemStack;
/*     */       } 
/*     */ 
/*     */       
/*  75 */       ItemStack var3 = this.inventoryContents[index].splitStack(count);
/*     */       
/*  77 */       if ((this.inventoryContents[index]).stackSize == 0)
/*     */       {
/*  79 */         this.inventoryContents[index] = null;
/*     */       }
/*     */       
/*  82 */       markDirty();
/*  83 */       return var3;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/*  88 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack func_174894_a(ItemStack p_174894_1_) {
/*  94 */     ItemStack var2 = p_174894_1_.copy();
/*     */     
/*  96 */     for (int var3 = 0; var3 < this.slotsCount; var3++) {
/*     */       
/*  98 */       ItemStack var4 = getStackInSlot(var3);
/*     */       
/* 100 */       if (var4 == null) {
/*     */         
/* 102 */         setInventorySlotContents(var3, var2);
/* 103 */         markDirty();
/* 104 */         return null;
/*     */       } 
/*     */       
/* 107 */       if (ItemStack.areItemsEqual(var4, var2)) {
/*     */         
/* 109 */         int var5 = Math.min(getInventoryStackLimit(), var4.getMaxStackSize());
/* 110 */         int var6 = Math.min(var2.stackSize, var5 - var4.stackSize);
/*     */         
/* 112 */         if (var6 > 0) {
/*     */           
/* 114 */           var4.stackSize += var6;
/* 115 */           var2.stackSize -= var6;
/*     */           
/* 117 */           if (var2.stackSize <= 0) {
/*     */             
/* 119 */             markDirty();
/* 120 */             return null;
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 126 */     if (var2.stackSize != p_174894_1_.stackSize)
/*     */     {
/* 128 */       markDirty();
/*     */     }
/*     */     
/* 131 */     return var2;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack getStackInSlotOnClosing(int index) {
/* 140 */     if (this.inventoryContents[index] != null) {
/*     */       
/* 142 */       ItemStack var2 = this.inventoryContents[index];
/* 143 */       this.inventoryContents[index] = null;
/* 144 */       return var2;
/*     */     } 
/*     */ 
/*     */     
/* 148 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setInventorySlotContents(int index, ItemStack stack) {
/* 157 */     this.inventoryContents[index] = stack;
/*     */     
/* 159 */     if (stack != null && stack.stackSize > getInventoryStackLimit())
/*     */     {
/* 161 */       stack.stackSize = getInventoryStackLimit();
/*     */     }
/*     */     
/* 164 */     markDirty();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getSizeInventory() {
/* 172 */     return this.slotsCount;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getName() {
/* 180 */     return this.inventoryTitle;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasCustomName() {
/* 188 */     return this.hasCustomName;
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_110133_a(String p_110133_1_) {
/* 193 */     this.hasCustomName = true;
/* 194 */     this.inventoryTitle = p_110133_1_;
/*     */   }
/*     */ 
/*     */   
/*     */   public IChatComponent getDisplayName() {
/* 199 */     return hasCustomName() ? (IChatComponent)new ChatComponentText(getName()) : (IChatComponent)new ChatComponentTranslation(getName(), new Object[0]);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getInventoryStackLimit() {
/* 208 */     return 64;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void markDirty() {
/* 217 */     if (this.field_70480_d != null)
/*     */     {
/* 219 */       for (int var1 = 0; var1 < this.field_70480_d.size(); var1++)
/*     */       {
/* 221 */         ((IInvBasic)this.field_70480_d.get(var1)).onInventoryChanged(this);
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isUseableByPlayer(EntityPlayer playerIn) {
/* 231 */     return true;
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
/* 243 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getField(int id) {
/* 248 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setField(int id, int value) {}
/*     */   
/*     */   public int getFieldCount() {
/* 255 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public void clearInventory() {
/* 260 */     for (int var1 = 0; var1 < this.inventoryContents.length; var1++)
/*     */     {
/* 262 */       this.inventoryContents[var1] = null;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\inventory\InventoryBasic.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */