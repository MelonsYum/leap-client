/*     */ package net.minecraft.tileentity;
/*     */ import java.util.Arrays;
/*     */ import java.util.List;
/*     */ import net.minecraft.block.BlockBrewingStand;
/*     */ import net.minecraft.block.properties.IProperty;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.player.InventoryPlayer;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.inventory.Container;
/*     */ import net.minecraft.inventory.ContainerBrewingStand;
/*     */ import net.minecraft.inventory.IInventory;
/*     */ import net.minecraft.inventory.ISidedInventory;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemPotion;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTBase;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.nbt.NBTTagList;
/*     */ import net.minecraft.potion.PotionHelper;
/*     */ import net.minecraft.server.gui.IUpdatePlayerListBox;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ 
/*     */ public class TileEntityBrewingStand extends TileEntityLockable implements IUpdatePlayerListBox, ISidedInventory {
/*  25 */   private static final int[] inputSlots = new int[] { 3 };
/*     */ 
/*     */   
/*  28 */   private static final int[] outputSlots = new int[] { 0, 1, 2 };
/*     */ 
/*     */   
/*  31 */   private ItemStack[] brewingItemStacks = new ItemStack[4];
/*     */ 
/*     */   
/*     */   private int brewTime;
/*     */ 
/*     */   
/*     */   private boolean[] filledSlots;
/*     */ 
/*     */   
/*     */   private Item ingredientID;
/*     */ 
/*     */   
/*     */   private String field_145942_n;
/*     */ 
/*     */   
/*     */   private static final String __OBFID = "CL_00000345";
/*     */ 
/*     */ 
/*     */   
/*     */   public String getName() {
/*  51 */     return hasCustomName() ? this.field_145942_n : "container.brewing";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasCustomName() {
/*  59 */     return (this.field_145942_n != null && this.field_145942_n.length() > 0);
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_145937_a(String p_145937_1_) {
/*  64 */     this.field_145942_n = p_145937_1_;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getSizeInventory() {
/*  72 */     return this.brewingItemStacks.length;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void update() {
/*  80 */     if (this.brewTime > 0) {
/*     */       
/*  82 */       this.brewTime--;
/*     */       
/*  84 */       if (this.brewTime == 0)
/*     */       {
/*  86 */         brewPotions();
/*  87 */         markDirty();
/*     */       }
/*  89 */       else if (!canBrew())
/*     */       {
/*  91 */         this.brewTime = 0;
/*  92 */         markDirty();
/*     */       }
/*  94 */       else if (this.ingredientID != this.brewingItemStacks[3].getItem())
/*     */       {
/*  96 */         this.brewTime = 0;
/*  97 */         markDirty();
/*     */       }
/*     */     
/* 100 */     } else if (canBrew()) {
/*     */       
/* 102 */       this.brewTime = 400;
/* 103 */       this.ingredientID = this.brewingItemStacks[3].getItem();
/*     */     } 
/*     */     
/* 106 */     if (!this.worldObj.isRemote) {
/*     */       
/* 108 */       boolean[] var1 = func_174902_m();
/*     */       
/* 110 */       if (!Arrays.equals(var1, this.filledSlots)) {
/*     */         
/* 112 */         this.filledSlots = var1;
/* 113 */         IBlockState var2 = this.worldObj.getBlockState(getPos());
/*     */         
/* 115 */         if (!(var2.getBlock() instanceof BlockBrewingStand)) {
/*     */           return;
/*     */         }
/*     */ 
/*     */         
/* 120 */         for (int var3 = 0; var3 < BlockBrewingStand.BOTTLE_PROPS.length; var3++)
/*     */         {
/* 122 */           var2 = var2.withProperty((IProperty)BlockBrewingStand.BOTTLE_PROPS[var3], Boolean.valueOf(var1[var3]));
/*     */         }
/*     */         
/* 125 */         this.worldObj.setBlockState(this.pos, var2, 2);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean canBrew() {
/* 132 */     if (this.brewingItemStacks[3] != null && (this.brewingItemStacks[3]).stackSize > 0) {
/*     */       
/* 134 */       ItemStack var1 = this.brewingItemStacks[3];
/*     */       
/* 136 */       if (!var1.getItem().isPotionIngredient(var1))
/*     */       {
/* 138 */         return false;
/*     */       }
/*     */ 
/*     */       
/* 142 */       boolean var2 = false;
/*     */       
/* 144 */       for (int var3 = 0; var3 < 3; var3++) {
/*     */         
/* 146 */         if (this.brewingItemStacks[var3] != null && this.brewingItemStacks[var3].getItem() == Items.potionitem) {
/*     */           
/* 148 */           int var4 = this.brewingItemStacks[var3].getMetadata();
/* 149 */           int var5 = func_145936_c(var4, var1);
/*     */           
/* 151 */           if (!ItemPotion.isSplash(var4) && ItemPotion.isSplash(var5)) {
/*     */             
/* 153 */             var2 = true;
/*     */             
/*     */             break;
/*     */           } 
/* 157 */           List var6 = Items.potionitem.getEffects(var4);
/* 158 */           List var7 = Items.potionitem.getEffects(var5);
/*     */           
/* 160 */           if ((var4 <= 0 || var6 != var7) && (var6 == null || (!var6.equals(var7) && var7 != null)) && var4 != var5) {
/*     */             
/* 162 */             var2 = true;
/*     */             
/*     */             break;
/*     */           } 
/*     */         } 
/*     */       } 
/* 168 */       return var2;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 173 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void brewPotions() {
/* 179 */     if (canBrew()) {
/*     */       
/* 181 */       ItemStack var1 = this.brewingItemStacks[3];
/*     */       
/* 183 */       for (int var2 = 0; var2 < 3; var2++) {
/*     */         
/* 185 */         if (this.brewingItemStacks[var2] != null && this.brewingItemStacks[var2].getItem() == Items.potionitem) {
/*     */           
/* 187 */           int var3 = this.brewingItemStacks[var2].getMetadata();
/* 188 */           int var4 = func_145936_c(var3, var1);
/* 189 */           List var5 = Items.potionitem.getEffects(var3);
/* 190 */           List var6 = Items.potionitem.getEffects(var4);
/*     */           
/* 192 */           if ((var3 <= 0 || var5 != var6) && (var5 == null || (!var5.equals(var6) && var6 != null))) {
/*     */             
/* 194 */             if (var3 != var4)
/*     */             {
/* 196 */               this.brewingItemStacks[var2].setItemDamage(var4);
/*     */             }
/*     */           }
/* 199 */           else if (!ItemPotion.isSplash(var3) && ItemPotion.isSplash(var4)) {
/*     */             
/* 201 */             this.brewingItemStacks[var2].setItemDamage(var4);
/*     */           } 
/*     */         } 
/*     */       } 
/*     */       
/* 206 */       if (var1.getItem().hasContainerItem()) {
/*     */         
/* 208 */         this.brewingItemStacks[3] = new ItemStack(var1.getItem().getContainerItem());
/*     */       }
/*     */       else {
/*     */         
/* 212 */         (this.brewingItemStacks[3]).stackSize--;
/*     */         
/* 214 */         if ((this.brewingItemStacks[3]).stackSize <= 0)
/*     */         {
/* 216 */           this.brewingItemStacks[3] = null;
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private int func_145936_c(int p_145936_1_, ItemStack p_145936_2_) {
/* 224 */     return (p_145936_2_ == null) ? p_145936_1_ : (p_145936_2_.getItem().isPotionIngredient(p_145936_2_) ? PotionHelper.applyIngredient(p_145936_1_, p_145936_2_.getItem().getPotionEffect(p_145936_2_)) : p_145936_1_);
/*     */   }
/*     */ 
/*     */   
/*     */   public void readFromNBT(NBTTagCompound compound) {
/* 229 */     super.readFromNBT(compound);
/* 230 */     NBTTagList var2 = compound.getTagList("Items", 10);
/* 231 */     this.brewingItemStacks = new ItemStack[getSizeInventory()];
/*     */     
/* 233 */     for (int var3 = 0; var3 < var2.tagCount(); var3++) {
/*     */       
/* 235 */       NBTTagCompound var4 = var2.getCompoundTagAt(var3);
/* 236 */       byte var5 = var4.getByte("Slot");
/*     */       
/* 238 */       if (var5 >= 0 && var5 < this.brewingItemStacks.length)
/*     */       {
/* 240 */         this.brewingItemStacks[var5] = ItemStack.loadItemStackFromNBT(var4);
/*     */       }
/*     */     } 
/*     */     
/* 244 */     this.brewTime = compound.getShort("BrewTime");
/*     */     
/* 246 */     if (compound.hasKey("CustomName", 8))
/*     */     {
/* 248 */       this.field_145942_n = compound.getString("CustomName");
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void writeToNBT(NBTTagCompound compound) {
/* 254 */     super.writeToNBT(compound);
/* 255 */     compound.setShort("BrewTime", (short)this.brewTime);
/* 256 */     NBTTagList var2 = new NBTTagList();
/*     */     
/* 258 */     for (int var3 = 0; var3 < this.brewingItemStacks.length; var3++) {
/*     */       
/* 260 */       if (this.brewingItemStacks[var3] != null) {
/*     */         
/* 262 */         NBTTagCompound var4 = new NBTTagCompound();
/* 263 */         var4.setByte("Slot", (byte)var3);
/* 264 */         this.brewingItemStacks[var3].writeToNBT(var4);
/* 265 */         var2.appendTag((NBTBase)var4);
/*     */       } 
/*     */     } 
/*     */     
/* 269 */     compound.setTag("Items", (NBTBase)var2);
/*     */     
/* 271 */     if (hasCustomName())
/*     */     {
/* 273 */       compound.setString("CustomName", this.field_145942_n);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack getStackInSlot(int slotIn) {
/* 282 */     return (slotIn >= 0 && slotIn < this.brewingItemStacks.length) ? this.brewingItemStacks[slotIn] : null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack decrStackSize(int index, int count) {
/* 291 */     if (index >= 0 && index < this.brewingItemStacks.length) {
/*     */       
/* 293 */       ItemStack var3 = this.brewingItemStacks[index];
/* 294 */       this.brewingItemStacks[index] = null;
/* 295 */       return var3;
/*     */     } 
/*     */ 
/*     */     
/* 299 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack getStackInSlotOnClosing(int index) {
/* 309 */     if (index >= 0 && index < this.brewingItemStacks.length) {
/*     */       
/* 311 */       ItemStack var2 = this.brewingItemStacks[index];
/* 312 */       this.brewingItemStacks[index] = null;
/* 313 */       return var2;
/*     */     } 
/*     */ 
/*     */     
/* 317 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setInventorySlotContents(int index, ItemStack stack) {
/* 326 */     if (index >= 0 && index < this.brewingItemStacks.length)
/*     */     {
/* 328 */       this.brewingItemStacks[index] = stack;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getInventoryStackLimit() {
/* 338 */     return 64;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isUseableByPlayer(EntityPlayer playerIn) {
/* 346 */     return (this.worldObj.getTileEntity(this.pos) != this) ? false : ((playerIn.getDistanceSq(this.pos.getX() + 0.5D, this.pos.getY() + 0.5D, this.pos.getZ() + 0.5D) <= 64.0D));
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
/* 358 */     return (index == 3) ? stack.getItem().isPotionIngredient(stack) : (!(stack.getItem() != Items.potionitem && stack.getItem() != Items.glass_bottle));
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean[] func_174902_m() {
/* 363 */     boolean[] var1 = new boolean[3];
/*     */     
/* 365 */     for (int var2 = 0; var2 < 3; var2++) {
/*     */       
/* 367 */       if (this.brewingItemStacks[var2] != null)
/*     */       {
/* 369 */         var1[var2] = true;
/*     */       }
/*     */     } 
/*     */     
/* 373 */     return var1;
/*     */   }
/*     */ 
/*     */   
/*     */   public int[] getSlotsForFace(EnumFacing side) {
/* 378 */     return (side == EnumFacing.UP) ? inputSlots : outputSlots;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canInsertItem(int slotIn, ItemStack itemStackIn, EnumFacing direction) {
/* 387 */     return isItemValidForSlot(slotIn, itemStackIn);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canExtractItem(int slotId, ItemStack stack, EnumFacing direction) {
/* 396 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getGuiID() {
/* 401 */     return "minecraft:brewing_stand";
/*     */   }
/*     */ 
/*     */   
/*     */   public Container createContainer(InventoryPlayer playerInventory, EntityPlayer playerIn) {
/* 406 */     return (Container)new ContainerBrewingStand(playerInventory, (IInventory)this);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getField(int id) {
/* 411 */     switch (id) {
/*     */       
/*     */       case 0:
/* 414 */         return this.brewTime;
/*     */     } 
/*     */     
/* 417 */     return 0;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setField(int id, int value) {
/* 423 */     switch (id) {
/*     */       
/*     */       case 0:
/* 426 */         this.brewTime = value;
/*     */         break;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getFieldCount() {
/* 434 */     return 1;
/*     */   }
/*     */ 
/*     */   
/*     */   public void clearInventory() {
/* 439 */     for (int var1 = 0; var1 < this.brewingItemStacks.length; var1++)
/*     */     {
/* 441 */       this.brewingItemStacks[var1] = null;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\tileentity\TileEntityBrewingStand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */