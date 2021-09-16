/*     */ package net.minecraft.entity.item;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.inventory.Container;
/*     */ import net.minecraft.inventory.IInventory;
/*     */ import net.minecraft.inventory.InventoryHelper;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTBase;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.nbt.NBTTagList;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.world.ILockableContainer;
/*     */ import net.minecraft.world.LockCode;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public abstract class EntityMinecartContainer extends EntityMinecart implements ILockableContainer {
/*  16 */   private ItemStack[] minecartContainerItems = new ItemStack[36];
/*     */ 
/*     */   
/*     */   private boolean dropContentsWhenDead = true;
/*     */ 
/*     */   
/*     */   private static final String __OBFID = "CL_00001674";
/*     */ 
/*     */ 
/*     */   
/*     */   public EntityMinecartContainer(World worldIn) {
/*  27 */     super(worldIn);
/*     */   }
/*     */ 
/*     */   
/*     */   public EntityMinecartContainer(World worldIn, double p_i1717_2_, double p_i1717_4_, double p_i1717_6_) {
/*  32 */     super(worldIn, p_i1717_2_, p_i1717_4_, p_i1717_6_);
/*     */   }
/*     */ 
/*     */   
/*     */   public void killMinecart(DamageSource p_94095_1_) {
/*  37 */     super.killMinecart(p_94095_1_);
/*  38 */     InventoryHelper.func_180176_a(this.worldObj, this, (IInventory)this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack getStackInSlot(int slotIn) {
/*  46 */     return this.minecartContainerItems[slotIn];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack decrStackSize(int index, int count) {
/*  55 */     if (this.minecartContainerItems[index] != null) {
/*     */ 
/*     */ 
/*     */       
/*  59 */       if ((this.minecartContainerItems[index]).stackSize <= count) {
/*     */         
/*  61 */         ItemStack itemStack = this.minecartContainerItems[index];
/*  62 */         this.minecartContainerItems[index] = null;
/*  63 */         return itemStack;
/*     */       } 
/*     */ 
/*     */       
/*  67 */       ItemStack var3 = this.minecartContainerItems[index].splitStack(count);
/*     */       
/*  69 */       if ((this.minecartContainerItems[index]).stackSize == 0)
/*     */       {
/*  71 */         this.minecartContainerItems[index] = null;
/*     */       }
/*     */       
/*  74 */       return var3;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/*  79 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack getStackInSlotOnClosing(int index) {
/*  89 */     if (this.minecartContainerItems[index] != null) {
/*     */       
/*  91 */       ItemStack var2 = this.minecartContainerItems[index];
/*  92 */       this.minecartContainerItems[index] = null;
/*  93 */       return var2;
/*     */     } 
/*     */ 
/*     */     
/*  97 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setInventorySlotContents(int index, ItemStack stack) {
/* 106 */     this.minecartContainerItems[index] = stack;
/*     */     
/* 108 */     if (stack != null && stack.stackSize > getInventoryStackLimit())
/*     */     {
/* 110 */       stack.stackSize = getInventoryStackLimit();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void markDirty() {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isUseableByPlayer(EntityPlayer playerIn) {
/* 125 */     return this.isDead ? false : ((playerIn.getDistanceSqToEntity(this) <= 64.0D));
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
/* 137 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getName() {
/* 145 */     return hasCustomName() ? getCustomNameTag() : "container.minecart";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getInventoryStackLimit() {
/* 154 */     return 64;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void travelToDimension(int dimensionId) {
/* 162 */     this.dropContentsWhenDead = false;
/* 163 */     super.travelToDimension(dimensionId);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setDead() {
/* 171 */     if (this.dropContentsWhenDead)
/*     */     {
/* 173 */       InventoryHelper.func_180176_a(this.worldObj, this, (IInventory)this);
/*     */     }
/*     */     
/* 176 */     super.setDead();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void writeEntityToNBT(NBTTagCompound tagCompound) {
/* 184 */     super.writeEntityToNBT(tagCompound);
/* 185 */     NBTTagList var2 = new NBTTagList();
/*     */     
/* 187 */     for (int var3 = 0; var3 < this.minecartContainerItems.length; var3++) {
/*     */       
/* 189 */       if (this.minecartContainerItems[var3] != null) {
/*     */         
/* 191 */         NBTTagCompound var4 = new NBTTagCompound();
/* 192 */         var4.setByte("Slot", (byte)var3);
/* 193 */         this.minecartContainerItems[var3].writeToNBT(var4);
/* 194 */         var2.appendTag((NBTBase)var4);
/*     */       } 
/*     */     } 
/*     */     
/* 198 */     tagCompound.setTag("Items", (NBTBase)var2);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void readEntityFromNBT(NBTTagCompound tagCompund) {
/* 206 */     super.readEntityFromNBT(tagCompund);
/* 207 */     NBTTagList var2 = tagCompund.getTagList("Items", 10);
/* 208 */     this.minecartContainerItems = new ItemStack[getSizeInventory()];
/*     */     
/* 210 */     for (int var3 = 0; var3 < var2.tagCount(); var3++) {
/*     */       
/* 212 */       NBTTagCompound var4 = var2.getCompoundTagAt(var3);
/* 213 */       int var5 = var4.getByte("Slot") & 0xFF;
/*     */       
/* 215 */       if (var5 >= 0 && var5 < this.minecartContainerItems.length)
/*     */       {
/* 217 */         this.minecartContainerItems[var5] = ItemStack.loadItemStackFromNBT(var4);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean interactFirst(EntityPlayer playerIn) {
/* 227 */     if (!this.worldObj.isRemote)
/*     */     {
/* 229 */       playerIn.displayGUIChest((IInventory)this);
/*     */     }
/*     */     
/* 232 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void applyDrag() {
/* 237 */     int var1 = 15 - Container.calcRedstoneFromInventory((IInventory)this);
/* 238 */     float var2 = 0.98F + var1 * 0.001F;
/* 239 */     this.motionX *= var2;
/* 240 */     this.motionY *= 0.0D;
/* 241 */     this.motionZ *= var2;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getField(int id) {
/* 246 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setField(int id, int value) {}
/*     */   
/*     */   public int getFieldCount() {
/* 253 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isLocked() {
/* 258 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setLockCode(LockCode code) {}
/*     */   
/*     */   public LockCode getLockCode() {
/* 265 */     return LockCode.EMPTY_CODE;
/*     */   }
/*     */ 
/*     */   
/*     */   public void clearInventory() {
/* 270 */     for (int var1 = 0; var1 < this.minecartContainerItems.length; var1++)
/*     */     {
/* 272 */       this.minecartContainerItems[var1] = null;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\entity\item\EntityMinecartContainer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */