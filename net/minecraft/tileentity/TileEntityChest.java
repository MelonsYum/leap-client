/*     */ package net.minecraft.tileentity;
/*     */ 
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.BlockChest;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.player.InventoryPlayer;
/*     */ import net.minecraft.inventory.Container;
/*     */ import net.minecraft.inventory.ContainerChest;
/*     */ import net.minecraft.inventory.IInventory;
/*     */ import net.minecraft.inventory.InventoryLargeChest;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTBase;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.nbt.NBTTagList;
/*     */ import net.minecraft.server.gui.IUpdatePlayerListBox;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ 
/*     */ public class TileEntityChest extends TileEntityLockable implements IUpdatePlayerListBox, IInventory {
/*  23 */   private ItemStack[] chestContents = new ItemStack[27];
/*     */ 
/*     */   
/*     */   public boolean adjacentChestChecked;
/*     */ 
/*     */   
/*     */   public TileEntityChest adjacentChestZNeg;
/*     */ 
/*     */   
/*     */   public TileEntityChest adjacentChestXPos;
/*     */ 
/*     */   
/*     */   public TileEntityChest adjacentChestXNeg;
/*     */ 
/*     */   
/*     */   public TileEntityChest adjacentChestZPos;
/*     */ 
/*     */   
/*     */   public float lidAngle;
/*     */   
/*     */   public float prevLidAngle;
/*     */   
/*     */   public int numPlayersUsing;
/*     */   
/*     */   private int ticksSinceSync;
/*     */   
/*     */   private int cachedChestType;
/*     */   
/*     */   private String customName;
/*     */   
/*     */   private static final String __OBFID = "CL_00000346";
/*     */ 
/*     */   
/*     */   public TileEntityChest() {
/*  57 */     this.cachedChestType = -1;
/*     */   }
/*     */ 
/*     */   
/*     */   public TileEntityChest(int p_i2350_1_) {
/*  62 */     this.cachedChestType = p_i2350_1_;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getSizeInventory() {
/*  70 */     return 27;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack getStackInSlot(int slotIn) {
/*  78 */     return this.chestContents[slotIn];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack decrStackSize(int index, int count) {
/*  87 */     if (this.chestContents[index] != null) {
/*     */ 
/*     */ 
/*     */       
/*  91 */       if ((this.chestContents[index]).stackSize <= count) {
/*     */         
/*  93 */         ItemStack itemStack = this.chestContents[index];
/*  94 */         this.chestContents[index] = null;
/*  95 */         markDirty();
/*  96 */         return itemStack;
/*     */       } 
/*     */ 
/*     */       
/* 100 */       ItemStack var3 = this.chestContents[index].splitStack(count);
/*     */       
/* 102 */       if ((this.chestContents[index]).stackSize == 0)
/*     */       {
/* 104 */         this.chestContents[index] = null;
/*     */       }
/*     */       
/* 107 */       markDirty();
/* 108 */       return var3;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 113 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack getStackInSlotOnClosing(int index) {
/* 123 */     if (this.chestContents[index] != null) {
/*     */       
/* 125 */       ItemStack var2 = this.chestContents[index];
/* 126 */       this.chestContents[index] = null;
/* 127 */       return var2;
/*     */     } 
/*     */ 
/*     */     
/* 131 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setInventorySlotContents(int index, ItemStack stack) {
/* 140 */     this.chestContents[index] = stack;
/*     */     
/* 142 */     if (stack != null && stack.stackSize > getInventoryStackLimit())
/*     */     {
/* 144 */       stack.stackSize = getInventoryStackLimit();
/*     */     }
/*     */     
/* 147 */     markDirty();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getName() {
/* 155 */     return hasCustomName() ? this.customName : "container.chest";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasCustomName() {
/* 163 */     return (this.customName != null && this.customName.length() > 0);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setCustomName(String p_145976_1_) {
/* 168 */     this.customName = p_145976_1_;
/*     */   }
/*     */ 
/*     */   
/*     */   public void readFromNBT(NBTTagCompound compound) {
/* 173 */     super.readFromNBT(compound);
/* 174 */     NBTTagList var2 = compound.getTagList("Items", 10);
/* 175 */     this.chestContents = new ItemStack[getSizeInventory()];
/*     */     
/* 177 */     if (compound.hasKey("CustomName", 8))
/*     */     {
/* 179 */       this.customName = compound.getString("CustomName");
/*     */     }
/*     */     
/* 182 */     for (int var3 = 0; var3 < var2.tagCount(); var3++) {
/*     */       
/* 184 */       NBTTagCompound var4 = var2.getCompoundTagAt(var3);
/* 185 */       int var5 = var4.getByte("Slot") & 0xFF;
/*     */       
/* 187 */       if (var5 >= 0 && var5 < this.chestContents.length)
/*     */       {
/* 189 */         this.chestContents[var5] = ItemStack.loadItemStackFromNBT(var4);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void writeToNBT(NBTTagCompound compound) {
/* 196 */     super.writeToNBT(compound);
/* 197 */     NBTTagList var2 = new NBTTagList();
/*     */     
/* 199 */     for (int var3 = 0; var3 < this.chestContents.length; var3++) {
/*     */       
/* 201 */       if (this.chestContents[var3] != null) {
/*     */         
/* 203 */         NBTTagCompound var4 = new NBTTagCompound();
/* 204 */         var4.setByte("Slot", (byte)var3);
/* 205 */         this.chestContents[var3].writeToNBT(var4);
/* 206 */         var2.appendTag((NBTBase)var4);
/*     */       } 
/*     */     } 
/*     */     
/* 210 */     compound.setTag("Items", (NBTBase)var2);
/*     */     
/* 212 */     if (hasCustomName())
/*     */     {
/* 214 */       compound.setString("CustomName", this.customName);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getInventoryStackLimit() {
/* 224 */     return 64;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isUseableByPlayer(EntityPlayer playerIn) {
/* 232 */     return (this.worldObj.getTileEntity(this.pos) != this) ? false : ((playerIn.getDistanceSq(this.pos.getX() + 0.5D, this.pos.getY() + 0.5D, this.pos.getZ() + 0.5D) <= 64.0D));
/*     */   }
/*     */ 
/*     */   
/*     */   public void updateContainingBlockInfo() {
/* 237 */     super.updateContainingBlockInfo();
/* 238 */     this.adjacentChestChecked = false;
/*     */   }
/*     */ 
/*     */   
/*     */   private void func_174910_a(TileEntityChest p_174910_1_, EnumFacing p_174910_2_) {
/* 243 */     if (p_174910_1_.isInvalid()) {
/*     */       
/* 245 */       this.adjacentChestChecked = false;
/*     */     }
/* 247 */     else if (this.adjacentChestChecked) {
/*     */       
/* 249 */       switch (SwitchEnumFacing.field_177366_a[p_174910_2_.ordinal()]) {
/*     */         
/*     */         case 1:
/* 252 */           if (this.adjacentChestZNeg != p_174910_1_)
/*     */           {
/* 254 */             this.adjacentChestChecked = false;
/*     */           }
/*     */           break;
/*     */ 
/*     */         
/*     */         case 2:
/* 260 */           if (this.adjacentChestZPos != p_174910_1_)
/*     */           {
/* 262 */             this.adjacentChestChecked = false;
/*     */           }
/*     */           break;
/*     */ 
/*     */         
/*     */         case 3:
/* 268 */           if (this.adjacentChestXPos != p_174910_1_)
/*     */           {
/* 270 */             this.adjacentChestChecked = false;
/*     */           }
/*     */           break;
/*     */ 
/*     */         
/*     */         case 4:
/* 276 */           if (this.adjacentChestXNeg != p_174910_1_)
/*     */           {
/* 278 */             this.adjacentChestChecked = false;
/*     */           }
/*     */           break;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void checkForAdjacentChests() {
/* 289 */     if (!this.adjacentChestChecked) {
/*     */       
/* 291 */       this.adjacentChestChecked = true;
/* 292 */       this.adjacentChestXNeg = func_174911_a(EnumFacing.WEST);
/* 293 */       this.adjacentChestXPos = func_174911_a(EnumFacing.EAST);
/* 294 */       this.adjacentChestZNeg = func_174911_a(EnumFacing.NORTH);
/* 295 */       this.adjacentChestZPos = func_174911_a(EnumFacing.SOUTH);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected TileEntityChest func_174911_a(EnumFacing p_174911_1_) {
/* 301 */     BlockPos var2 = this.pos.offset(p_174911_1_);
/*     */     
/* 303 */     if (func_174912_b(var2)) {
/*     */       
/* 305 */       TileEntity var3 = this.worldObj.getTileEntity(var2);
/*     */       
/* 307 */       if (var3 instanceof TileEntityChest) {
/*     */         
/* 309 */         TileEntityChest var4 = (TileEntityChest)var3;
/* 310 */         var4.func_174910_a(this, p_174911_1_.getOpposite());
/* 311 */         return var4;
/*     */       } 
/*     */     } 
/*     */     
/* 315 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean func_174912_b(BlockPos p_174912_1_) {
/* 320 */     if (this.worldObj == null)
/*     */     {
/* 322 */       return false;
/*     */     }
/*     */ 
/*     */     
/* 326 */     Block var2 = this.worldObj.getBlockState(p_174912_1_).getBlock();
/* 327 */     return (var2 instanceof BlockChest && ((BlockChest)var2).chestType == getChestType());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void update() {
/* 336 */     checkForAdjacentChests();
/* 337 */     int var1 = this.pos.getX();
/* 338 */     int var2 = this.pos.getY();
/* 339 */     int var3 = this.pos.getZ();
/* 340 */     this.ticksSinceSync++;
/*     */ 
/*     */     
/* 343 */     if (!this.worldObj.isRemote && this.numPlayersUsing != 0 && (this.ticksSinceSync + var1 + var2 + var3) % 200 == 0) {
/*     */       
/* 345 */       this.numPlayersUsing = 0;
/* 346 */       float f = 5.0F;
/* 347 */       List var5 = this.worldObj.getEntitiesWithinAABB(EntityPlayer.class, new AxisAlignedBB((var1 - f), (var2 - f), (var3 - f), ((var1 + 1) + f), ((var2 + 1) + f), ((var3 + 1) + f)));
/* 348 */       Iterator<EntityPlayer> var6 = var5.iterator();
/*     */       
/* 350 */       while (var6.hasNext()) {
/*     */         
/* 352 */         EntityPlayer var7 = var6.next();
/*     */         
/* 354 */         if (var7.openContainer instanceof ContainerChest) {
/*     */           
/* 356 */           IInventory var8 = ((ContainerChest)var7.openContainer).getLowerChestInventory();
/*     */           
/* 358 */           if (var8 == this || (var8 instanceof InventoryLargeChest && ((InventoryLargeChest)var8).isPartOfLargeChest(this)))
/*     */           {
/* 360 */             this.numPlayersUsing++;
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 366 */     this.prevLidAngle = this.lidAngle;
/* 367 */     float var4 = 0.1F;
/*     */ 
/*     */     
/* 370 */     if (this.numPlayersUsing > 0 && this.lidAngle == 0.0F && this.adjacentChestZNeg == null && this.adjacentChestXNeg == null) {
/*     */       
/* 372 */       double var11 = var1 + 0.5D;
/* 373 */       double var14 = var3 + 0.5D;
/*     */       
/* 375 */       if (this.adjacentChestZPos != null)
/*     */       {
/* 377 */         var14 += 0.5D;
/*     */       }
/*     */       
/* 380 */       if (this.adjacentChestXPos != null)
/*     */       {
/* 382 */         var11 += 0.5D;
/*     */       }
/*     */       
/* 385 */       this.worldObj.playSoundEffect(var11, var2 + 0.5D, var14, "random.chestopen", 0.5F, this.worldObj.rand.nextFloat() * 0.1F + 0.9F);
/*     */     } 
/*     */     
/* 388 */     if ((this.numPlayersUsing == 0 && this.lidAngle > 0.0F) || (this.numPlayersUsing > 0 && this.lidAngle < 1.0F)) {
/*     */       
/* 390 */       float var12 = this.lidAngle;
/*     */       
/* 392 */       if (this.numPlayersUsing > 0) {
/*     */         
/* 394 */         this.lidAngle += var4;
/*     */       }
/*     */       else {
/*     */         
/* 398 */         this.lidAngle -= var4;
/*     */       } 
/*     */       
/* 401 */       if (this.lidAngle > 1.0F)
/*     */       {
/* 403 */         this.lidAngle = 1.0F;
/*     */       }
/*     */       
/* 406 */       float var13 = 0.5F;
/*     */       
/* 408 */       if (this.lidAngle < var13 && var12 >= var13 && this.adjacentChestZNeg == null && this.adjacentChestXNeg == null) {
/*     */         
/* 410 */         double var14 = var1 + 0.5D;
/* 411 */         double var9 = var3 + 0.5D;
/*     */         
/* 413 */         if (this.adjacentChestZPos != null)
/*     */         {
/* 415 */           var9 += 0.5D;
/*     */         }
/*     */         
/* 418 */         if (this.adjacentChestXPos != null)
/*     */         {
/* 420 */           var14 += 0.5D;
/*     */         }
/*     */         
/* 423 */         this.worldObj.playSoundEffect(var14, var2 + 0.5D, var9, "random.chestclosed", 0.5F, this.worldObj.rand.nextFloat() * 0.1F + 0.9F);
/*     */       } 
/*     */       
/* 426 */       if (this.lidAngle < 0.0F)
/*     */       {
/* 428 */         this.lidAngle = 0.0F;
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean receiveClientEvent(int id, int type) {
/* 435 */     if (id == 1) {
/*     */       
/* 437 */       this.numPlayersUsing = type;
/* 438 */       return true;
/*     */     } 
/*     */ 
/*     */     
/* 442 */     return super.receiveClientEvent(id, type);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void openInventory(EntityPlayer playerIn) {
/* 448 */     if (!playerIn.func_175149_v()) {
/*     */       
/* 450 */       if (this.numPlayersUsing < 0)
/*     */       {
/* 452 */         this.numPlayersUsing = 0;
/*     */       }
/*     */       
/* 455 */       this.numPlayersUsing++;
/* 456 */       this.worldObj.addBlockEvent(this.pos, getBlockType(), 1, this.numPlayersUsing);
/* 457 */       this.worldObj.notifyNeighborsOfStateChange(this.pos, getBlockType());
/* 458 */       this.worldObj.notifyNeighborsOfStateChange(this.pos.offsetDown(), getBlockType());
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void closeInventory(EntityPlayer playerIn) {
/* 464 */     if (!playerIn.func_175149_v() && getBlockType() instanceof BlockChest) {
/*     */       
/* 466 */       this.numPlayersUsing--;
/* 467 */       this.worldObj.addBlockEvent(this.pos, getBlockType(), 1, this.numPlayersUsing);
/* 468 */       this.worldObj.notifyNeighborsOfStateChange(this.pos, getBlockType());
/* 469 */       this.worldObj.notifyNeighborsOfStateChange(this.pos.offsetDown(), getBlockType());
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isItemValidForSlot(int index, ItemStack stack) {
/* 478 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void invalidate() {
/* 486 */     super.invalidate();
/* 487 */     updateContainingBlockInfo();
/* 488 */     checkForAdjacentChests();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getChestType() {
/* 493 */     if (this.cachedChestType == -1) {
/*     */       
/* 495 */       if (this.worldObj == null || !(getBlockType() instanceof BlockChest))
/*     */       {
/* 497 */         return 0;
/*     */       }
/*     */       
/* 500 */       this.cachedChestType = ((BlockChest)getBlockType()).chestType;
/*     */     } 
/*     */     
/* 503 */     return this.cachedChestType;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getGuiID() {
/* 508 */     return "minecraft:chest";
/*     */   }
/*     */ 
/*     */   
/*     */   public Container createContainer(InventoryPlayer playerInventory, EntityPlayer playerIn) {
/* 513 */     return (Container)new ContainerChest((IInventory)playerInventory, this, playerIn);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getField(int id) {
/* 518 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setField(int id, int value) {}
/*     */   
/*     */   public int getFieldCount() {
/* 525 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public void clearInventory() {
/* 530 */     for (int var1 = 0; var1 < this.chestContents.length; var1++)
/*     */     {
/* 532 */       this.chestContents[var1] = null;
/*     */     }
/*     */   }
/*     */   
/*     */   static final class SwitchEnumFacing
/*     */   {
/* 538 */     static final int[] field_177366_a = new int[(EnumFacing.values()).length];
/*     */     
/*     */     private static final String __OBFID = "CL_00002041";
/*     */ 
/*     */     
/*     */     static {
/*     */       try {
/* 545 */         field_177366_a[EnumFacing.NORTH.ordinal()] = 1;
/*     */       }
/* 547 */       catch (NoSuchFieldError noSuchFieldError) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/* 554 */         field_177366_a[EnumFacing.SOUTH.ordinal()] = 2;
/*     */       }
/* 556 */       catch (NoSuchFieldError noSuchFieldError) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/* 563 */         field_177366_a[EnumFacing.EAST.ordinal()] = 3;
/*     */       }
/* 565 */       catch (NoSuchFieldError noSuchFieldError) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/* 572 */         field_177366_a[EnumFacing.WEST.ordinal()] = 4;
/*     */       }
/* 574 */       catch (NoSuchFieldError noSuchFieldError) {}
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\tileentity\TileEntityChest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */