/*     */ package net.minecraft.tileentity;
/*     */ 
/*     */ import java.util.List;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.BlockChest;
/*     */ import net.minecraft.block.BlockHopper;
/*     */ import net.minecraft.command.IEntitySelector;
/*     */ import net.minecraft.entity.item.EntityItem;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.player.InventoryPlayer;
/*     */ import net.minecraft.inventory.Container;
/*     */ import net.minecraft.inventory.ContainerHopper;
/*     */ import net.minecraft.inventory.IInventory;
/*     */ import net.minecraft.inventory.ISidedInventory;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTBase;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.nbt.NBTTagList;
/*     */ import net.minecraft.server.gui.IUpdatePlayerListBox;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class TileEntityHopper
/*     */   extends TileEntityLockable implements IHopper, IUpdatePlayerListBox {
/*  28 */   private ItemStack[] inventory = new ItemStack[5];
/*     */   private String customName;
/*  30 */   private int transferCooldown = -1;
/*     */   
/*     */   private static final String __OBFID = "CL_00000359";
/*     */   
/*     */   public void readFromNBT(NBTTagCompound compound) {
/*  35 */     super.readFromNBT(compound);
/*  36 */     NBTTagList var2 = compound.getTagList("Items", 10);
/*  37 */     this.inventory = new ItemStack[getSizeInventory()];
/*     */     
/*  39 */     if (compound.hasKey("CustomName", 8))
/*     */     {
/*  41 */       this.customName = compound.getString("CustomName");
/*     */     }
/*     */     
/*  44 */     this.transferCooldown = compound.getInteger("TransferCooldown");
/*     */     
/*  46 */     for (int var3 = 0; var3 < var2.tagCount(); var3++) {
/*     */       
/*  48 */       NBTTagCompound var4 = var2.getCompoundTagAt(var3);
/*  49 */       byte var5 = var4.getByte("Slot");
/*     */       
/*  51 */       if (var5 >= 0 && var5 < this.inventory.length)
/*     */       {
/*  53 */         this.inventory[var5] = ItemStack.loadItemStackFromNBT(var4);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void writeToNBT(NBTTagCompound compound) {
/*  60 */     super.writeToNBT(compound);
/*  61 */     NBTTagList var2 = new NBTTagList();
/*     */     
/*  63 */     for (int var3 = 0; var3 < this.inventory.length; var3++) {
/*     */       
/*  65 */       if (this.inventory[var3] != null) {
/*     */         
/*  67 */         NBTTagCompound var4 = new NBTTagCompound();
/*  68 */         var4.setByte("Slot", (byte)var3);
/*  69 */         this.inventory[var3].writeToNBT(var4);
/*  70 */         var2.appendTag((NBTBase)var4);
/*     */       } 
/*     */     } 
/*     */     
/*  74 */     compound.setTag("Items", (NBTBase)var2);
/*  75 */     compound.setInteger("TransferCooldown", this.transferCooldown);
/*     */     
/*  77 */     if (hasCustomName())
/*     */     {
/*  79 */       compound.setString("CustomName", this.customName);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void markDirty() {
/*  89 */     super.markDirty();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getSizeInventory() {
/*  97 */     return this.inventory.length;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack getStackInSlot(int slotIn) {
/* 105 */     return this.inventory[slotIn];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack decrStackSize(int index, int count) {
/* 114 */     if (this.inventory[index] != null) {
/*     */ 
/*     */ 
/*     */       
/* 118 */       if ((this.inventory[index]).stackSize <= count) {
/*     */         
/* 120 */         ItemStack itemStack = this.inventory[index];
/* 121 */         this.inventory[index] = null;
/* 122 */         return itemStack;
/*     */       } 
/*     */ 
/*     */       
/* 126 */       ItemStack var3 = this.inventory[index].splitStack(count);
/*     */       
/* 128 */       if ((this.inventory[index]).stackSize == 0)
/*     */       {
/* 130 */         this.inventory[index] = null;
/*     */       }
/*     */       
/* 133 */       return var3;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 138 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack getStackInSlotOnClosing(int index) {
/* 148 */     if (this.inventory[index] != null) {
/*     */       
/* 150 */       ItemStack var2 = this.inventory[index];
/* 151 */       this.inventory[index] = null;
/* 152 */       return var2;
/*     */     } 
/*     */ 
/*     */     
/* 156 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setInventorySlotContents(int index, ItemStack stack) {
/* 165 */     this.inventory[index] = stack;
/*     */     
/* 167 */     if (stack != null && stack.stackSize > getInventoryStackLimit())
/*     */     {
/* 169 */       stack.stackSize = getInventoryStackLimit();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getName() {
/* 178 */     return hasCustomName() ? this.customName : "container.hopper";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasCustomName() {
/* 186 */     return (this.customName != null && this.customName.length() > 0);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setCustomName(String customNameIn) {
/* 191 */     this.customName = customNameIn;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getInventoryStackLimit() {
/* 200 */     return 64;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isUseableByPlayer(EntityPlayer playerIn) {
/* 208 */     return (this.worldObj.getTileEntity(this.pos) != this) ? false : ((playerIn.getDistanceSq(this.pos.getX() + 0.5D, this.pos.getY() + 0.5D, this.pos.getZ() + 0.5D) <= 64.0D));
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
/* 220 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void update() {
/* 228 */     if (this.worldObj != null && !this.worldObj.isRemote) {
/*     */       
/* 230 */       this.transferCooldown--;
/*     */       
/* 232 */       if (!isOnTransferCooldown()) {
/*     */         
/* 234 */         setTransferCooldown(0);
/* 235 */         func_145887_i();
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_145887_i() {
/* 242 */     if (this.worldObj != null && !this.worldObj.isRemote) {
/*     */       
/* 244 */       if (!isOnTransferCooldown() && BlockHopper.getActiveStateFromMetadata(getBlockMetadata())) {
/*     */         
/* 246 */         boolean var1 = false;
/*     */         
/* 248 */         if (!func_152104_k())
/*     */         {
/* 250 */           var1 = func_145883_k();
/*     */         }
/*     */         
/* 253 */         if (!func_152105_l())
/*     */         {
/* 255 */           var1 = !(!func_145891_a(this) && !var1);
/*     */         }
/*     */         
/* 258 */         if (var1) {
/*     */           
/* 260 */           setTransferCooldown(8);
/* 261 */           markDirty();
/* 262 */           return true;
/*     */         } 
/*     */       } 
/*     */       
/* 266 */       return false;
/*     */     } 
/*     */ 
/*     */     
/* 270 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean func_152104_k() {
/* 276 */     ItemStack[] var1 = this.inventory;
/* 277 */     int var2 = var1.length;
/*     */     
/* 279 */     for (int var3 = 0; var3 < var2; var3++) {
/*     */       
/* 281 */       ItemStack var4 = var1[var3];
/*     */       
/* 283 */       if (var4 != null)
/*     */       {
/* 285 */         return false;
/*     */       }
/*     */     } 
/*     */     
/* 289 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean func_152105_l() {
/* 294 */     ItemStack[] var1 = this.inventory;
/* 295 */     int var2 = var1.length;
/*     */     
/* 297 */     for (int var3 = 0; var3 < var2; var3++) {
/*     */       
/* 299 */       ItemStack var4 = var1[var3];
/*     */       
/* 301 */       if (var4 == null || var4.stackSize != var4.getMaxStackSize())
/*     */       {
/* 303 */         return false;
/*     */       }
/*     */     } 
/*     */     
/* 307 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean func_145883_k() {
/* 312 */     IInventory var1 = func_145895_l();
/*     */     
/* 314 */     if (var1 == null)
/*     */     {
/* 316 */       return false;
/*     */     }
/*     */ 
/*     */     
/* 320 */     EnumFacing var2 = BlockHopper.func_176428_b(getBlockMetadata()).getOpposite();
/*     */     
/* 322 */     if (func_174919_a(var1, var2))
/*     */     {
/* 324 */       return false;
/*     */     }
/*     */ 
/*     */     
/* 328 */     for (int var3 = 0; var3 < getSizeInventory(); var3++) {
/*     */       
/* 330 */       if (getStackInSlot(var3) != null) {
/*     */         
/* 332 */         ItemStack var4 = getStackInSlot(var3).copy();
/* 333 */         ItemStack var5 = func_174918_a(var1, decrStackSize(var3, 1), var2);
/*     */         
/* 335 */         if (var5 == null || var5.stackSize == 0) {
/*     */           
/* 337 */           var1.markDirty();
/* 338 */           return true;
/*     */         } 
/*     */         
/* 341 */         setInventorySlotContents(var3, var4);
/*     */       } 
/*     */     } 
/*     */     
/* 345 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean func_174919_a(IInventory p_174919_1_, EnumFacing p_174919_2_) {
/* 352 */     if (p_174919_1_ instanceof ISidedInventory) {
/*     */       
/* 354 */       ISidedInventory var3 = (ISidedInventory)p_174919_1_;
/* 355 */       int[] var4 = var3.getSlotsForFace(p_174919_2_);
/*     */       
/* 357 */       for (int var5 = 0; var5 < var4.length; var5++)
/*     */       {
/* 359 */         ItemStack var6 = var3.getStackInSlot(var4[var5]);
/*     */         
/* 361 */         if (var6 == null || var6.stackSize != var6.getMaxStackSize())
/*     */         {
/* 363 */           return false;
/*     */         }
/*     */       }
/*     */     
/*     */     } else {
/*     */       
/* 369 */       int var7 = p_174919_1_.getSizeInventory();
/*     */       
/* 371 */       for (int var8 = 0; var8 < var7; var8++) {
/*     */         
/* 373 */         ItemStack var9 = p_174919_1_.getStackInSlot(var8);
/*     */         
/* 375 */         if (var9 == null || var9.stackSize != var9.getMaxStackSize())
/*     */         {
/* 377 */           return false;
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 382 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   private static boolean func_174917_b(IInventory p_174917_0_, EnumFacing p_174917_1_) {
/* 387 */     if (p_174917_0_ instanceof ISidedInventory) {
/*     */       
/* 389 */       ISidedInventory var2 = (ISidedInventory)p_174917_0_;
/* 390 */       int[] var3 = var2.getSlotsForFace(p_174917_1_);
/*     */       
/* 392 */       for (int var4 = 0; var4 < var3.length; var4++)
/*     */       {
/* 394 */         if (var2.getStackInSlot(var3[var4]) != null)
/*     */         {
/* 396 */           return false;
/*     */         }
/*     */       }
/*     */     
/*     */     } else {
/*     */       
/* 402 */       int var5 = p_174917_0_.getSizeInventory();
/*     */       
/* 404 */       for (int var6 = 0; var6 < var5; var6++) {
/*     */         
/* 406 */         if (p_174917_0_.getStackInSlot(var6) != null)
/*     */         {
/* 408 */           return false;
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 413 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean func_145891_a(IHopper p_145891_0_) {
/* 418 */     IInventory var1 = func_145884_b(p_145891_0_);
/*     */     
/* 420 */     if (var1 != null) {
/*     */       
/* 422 */       EnumFacing var2 = EnumFacing.DOWN;
/*     */       
/* 424 */       if (func_174917_b(var1, var2))
/*     */       {
/* 426 */         return false;
/*     */       }
/*     */       
/* 429 */       if (var1 instanceof ISidedInventory) {
/*     */         
/* 431 */         ISidedInventory var3 = (ISidedInventory)var1;
/* 432 */         int[] var4 = var3.getSlotsForFace(var2);
/*     */         
/* 434 */         for (int var5 = 0; var5 < var4.length; var5++)
/*     */         {
/* 436 */           if (func_174915_a(p_145891_0_, var1, var4[var5], var2))
/*     */           {
/* 438 */             return true;
/*     */           }
/*     */         }
/*     */       
/*     */       } else {
/*     */         
/* 444 */         int var7 = var1.getSizeInventory();
/*     */         
/* 446 */         for (int var8 = 0; var8 < var7; var8++)
/*     */         {
/* 448 */           if (func_174915_a(p_145891_0_, var1, var8, var2))
/*     */           {
/* 450 */             return true;
/*     */           }
/*     */         }
/*     */       
/*     */       } 
/*     */     } else {
/*     */       
/* 457 */       EntityItem var6 = func_145897_a(p_145891_0_.getWorld(), p_145891_0_.getXPos(), p_145891_0_.getYPos() + 1.0D, p_145891_0_.getZPos());
/*     */       
/* 459 */       if (var6 != null)
/*     */       {
/* 461 */         return func_145898_a(p_145891_0_, var6);
/*     */       }
/*     */     } 
/*     */     
/* 465 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   private static boolean func_174915_a(IHopper p_174915_0_, IInventory p_174915_1_, int p_174915_2_, EnumFacing p_174915_3_) {
/* 470 */     ItemStack var4 = p_174915_1_.getStackInSlot(p_174915_2_);
/*     */     
/* 472 */     if (var4 != null && func_174921_b(p_174915_1_, var4, p_174915_2_, p_174915_3_)) {
/*     */       
/* 474 */       ItemStack var5 = var4.copy();
/* 475 */       ItemStack var6 = func_174918_a(p_174915_0_, p_174915_1_.decrStackSize(p_174915_2_, 1), (EnumFacing)null);
/*     */       
/* 477 */       if (var6 == null || var6.stackSize == 0) {
/*     */         
/* 479 */         p_174915_1_.markDirty();
/* 480 */         return true;
/*     */       } 
/*     */       
/* 483 */       p_174915_1_.setInventorySlotContents(p_174915_2_, var5);
/*     */     } 
/*     */     
/* 486 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean func_145898_a(IInventory p_145898_0_, EntityItem p_145898_1_) {
/* 491 */     boolean var2 = false;
/*     */     
/* 493 */     if (p_145898_1_ == null)
/*     */     {
/* 495 */       return false;
/*     */     }
/*     */ 
/*     */     
/* 499 */     ItemStack var3 = p_145898_1_.getEntityItem().copy();
/* 500 */     ItemStack var4 = func_174918_a(p_145898_0_, var3, (EnumFacing)null);
/*     */     
/* 502 */     if (var4 != null && var4.stackSize != 0) {
/*     */       
/* 504 */       p_145898_1_.setEntityItemStack(var4);
/*     */     }
/*     */     else {
/*     */       
/* 508 */       var2 = true;
/* 509 */       p_145898_1_.setDead();
/*     */     } 
/*     */     
/* 512 */     return var2;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static ItemStack func_174918_a(IInventory p_174918_0_, ItemStack p_174918_1_, EnumFacing p_174918_2_) {
/* 518 */     if (p_174918_0_ instanceof ISidedInventory && p_174918_2_ != null) {
/*     */       
/* 520 */       ISidedInventory var6 = (ISidedInventory)p_174918_0_;
/* 521 */       int[] var7 = var6.getSlotsForFace(p_174918_2_);
/*     */       
/* 523 */       for (int var5 = 0; var5 < var7.length && p_174918_1_ != null && p_174918_1_.stackSize > 0; var5++)
/*     */       {
/* 525 */         p_174918_1_ = func_174916_c(p_174918_0_, p_174918_1_, var7[var5], p_174918_2_);
/*     */       }
/*     */     }
/*     */     else {
/*     */       
/* 530 */       int var3 = p_174918_0_.getSizeInventory();
/*     */       
/* 532 */       for (int var4 = 0; var4 < var3 && p_174918_1_ != null && p_174918_1_.stackSize > 0; var4++)
/*     */       {
/* 534 */         p_174918_1_ = func_174916_c(p_174918_0_, p_174918_1_, var4, p_174918_2_);
/*     */       }
/*     */     } 
/*     */     
/* 538 */     if (p_174918_1_ != null && p_174918_1_.stackSize == 0)
/*     */     {
/* 540 */       p_174918_1_ = null;
/*     */     }
/*     */     
/* 543 */     return p_174918_1_;
/*     */   }
/*     */ 
/*     */   
/*     */   private static boolean func_174920_a(IInventory p_174920_0_, ItemStack p_174920_1_, int p_174920_2_, EnumFacing p_174920_3_) {
/* 548 */     return !p_174920_0_.isItemValidForSlot(p_174920_2_, p_174920_1_) ? false : (!(p_174920_0_ instanceof ISidedInventory && !((ISidedInventory)p_174920_0_).canInsertItem(p_174920_2_, p_174920_1_, p_174920_3_)));
/*     */   }
/*     */ 
/*     */   
/*     */   private static boolean func_174921_b(IInventory p_174921_0_, ItemStack p_174921_1_, int p_174921_2_, EnumFacing p_174921_3_) {
/* 553 */     return !(p_174921_0_ instanceof ISidedInventory && !((ISidedInventory)p_174921_0_).canExtractItem(p_174921_2_, p_174921_1_, p_174921_3_));
/*     */   }
/*     */ 
/*     */   
/*     */   private static ItemStack func_174916_c(IInventory p_174916_0_, ItemStack p_174916_1_, int p_174916_2_, EnumFacing p_174916_3_) {
/* 558 */     ItemStack var4 = p_174916_0_.getStackInSlot(p_174916_2_);
/*     */     
/* 560 */     if (func_174920_a(p_174916_0_, p_174916_1_, p_174916_2_, p_174916_3_)) {
/*     */       
/* 562 */       boolean var5 = false;
/*     */       
/* 564 */       if (var4 == null) {
/*     */         
/* 566 */         p_174916_0_.setInventorySlotContents(p_174916_2_, p_174916_1_);
/* 567 */         p_174916_1_ = null;
/* 568 */         var5 = true;
/*     */       }
/* 570 */       else if (canCombine(var4, p_174916_1_)) {
/*     */         
/* 572 */         int var6 = p_174916_1_.getMaxStackSize() - var4.stackSize;
/* 573 */         int var7 = Math.min(p_174916_1_.stackSize, var6);
/* 574 */         p_174916_1_.stackSize -= var7;
/* 575 */         var4.stackSize += var7;
/* 576 */         var5 = (var7 > 0);
/*     */       } 
/*     */       
/* 579 */       if (var5) {
/*     */         
/* 581 */         if (p_174916_0_ instanceof TileEntityHopper) {
/*     */           
/* 583 */           TileEntityHopper var8 = (TileEntityHopper)p_174916_0_;
/*     */           
/* 585 */           if (var8.mayTransfer())
/*     */           {
/* 587 */             var8.setTransferCooldown(8);
/*     */           }
/*     */           
/* 590 */           p_174916_0_.markDirty();
/*     */         } 
/*     */         
/* 593 */         p_174916_0_.markDirty();
/*     */       } 
/*     */     } 
/*     */     
/* 597 */     return p_174916_1_;
/*     */   }
/*     */ 
/*     */   
/*     */   private IInventory func_145895_l() {
/* 602 */     EnumFacing var1 = BlockHopper.func_176428_b(getBlockMetadata());
/* 603 */     return func_145893_b(getWorld(), (this.pos.getX() + var1.getFrontOffsetX()), (this.pos.getY() + var1.getFrontOffsetY()), (this.pos.getZ() + var1.getFrontOffsetZ()));
/*     */   }
/*     */ 
/*     */   
/*     */   public static IInventory func_145884_b(IHopper p_145884_0_) {
/* 608 */     return func_145893_b(p_145884_0_.getWorld(), p_145884_0_.getXPos(), p_145884_0_.getYPos() + 1.0D, p_145884_0_.getZPos());
/*     */   }
/*     */ 
/*     */   
/*     */   public static EntityItem func_145897_a(World worldIn, double p_145897_1_, double p_145897_3_, double p_145897_5_) {
/* 613 */     List<EntityItem> var7 = worldIn.func_175647_a(EntityItem.class, new AxisAlignedBB(p_145897_1_, p_145897_3_, p_145897_5_, p_145897_1_ + 1.0D, p_145897_3_ + 1.0D, p_145897_5_ + 1.0D), IEntitySelector.selectAnything);
/* 614 */     return (var7.size() > 0) ? var7.get(0) : null;
/*     */   }
/*     */ 
/*     */   
/*     */   public static IInventory func_145893_b(World worldIn, double p_145893_1_, double p_145893_3_, double p_145893_5_) {
/* 619 */     Object var7 = null;
/* 620 */     int var8 = MathHelper.floor_double(p_145893_1_);
/* 621 */     int var9 = MathHelper.floor_double(p_145893_3_);
/* 622 */     int var10 = MathHelper.floor_double(p_145893_5_);
/* 623 */     BlockPos var11 = new BlockPos(var8, var9, var10);
/* 624 */     TileEntity var12 = worldIn.getTileEntity(new BlockPos(var8, var9, var10));
/*     */     
/* 626 */     if (var12 instanceof IInventory) {
/*     */       
/* 628 */       var7 = var12;
/*     */       
/* 630 */       if (var7 instanceof TileEntityChest) {
/*     */         
/* 632 */         Block var13 = worldIn.getBlockState(new BlockPos(var8, var9, var10)).getBlock();
/*     */         
/* 634 */         if (var13 instanceof BlockChest)
/*     */         {
/* 636 */           var7 = ((BlockChest)var13).getLockableContainer(worldIn, var11);
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 641 */     if (var7 == null) {
/*     */       
/* 643 */       List<IInventory> var14 = worldIn.func_175674_a(null, new AxisAlignedBB(p_145893_1_, p_145893_3_, p_145893_5_, p_145893_1_ + 1.0D, p_145893_3_ + 1.0D, p_145893_5_ + 1.0D), IEntitySelector.selectInventories);
/*     */       
/* 645 */       if (var14.size() > 0)
/*     */       {
/* 647 */         var7 = var14.get(worldIn.rand.nextInt(var14.size()));
/*     */       }
/*     */     } 
/*     */     
/* 651 */     return (IInventory)var7;
/*     */   }
/*     */ 
/*     */   
/*     */   private static boolean canCombine(ItemStack stack1, ItemStack stack2) {
/* 656 */     return (stack1.getItem() != stack2.getItem()) ? false : ((stack1.getMetadata() != stack2.getMetadata()) ? false : ((stack1.stackSize > stack1.getMaxStackSize()) ? false : ItemStack.areItemStackTagsEqual(stack1, stack2)));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public double getXPos() {
/* 664 */     return this.pos.getX();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public double getYPos() {
/* 672 */     return this.pos.getY();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public double getZPos() {
/* 680 */     return this.pos.getZ();
/*     */   }
/*     */ 
/*     */   
/*     */   public void setTransferCooldown(int ticks) {
/* 685 */     this.transferCooldown = ticks;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isOnTransferCooldown() {
/* 690 */     return (this.transferCooldown > 0);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean mayTransfer() {
/* 695 */     return (this.transferCooldown <= 1);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getGuiID() {
/* 700 */     return "minecraft:hopper";
/*     */   }
/*     */ 
/*     */   
/*     */   public Container createContainer(InventoryPlayer playerInventory, EntityPlayer playerIn) {
/* 705 */     return (Container)new ContainerHopper(playerInventory, this, playerIn);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getField(int id) {
/* 710 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setField(int id, int value) {}
/*     */   
/*     */   public int getFieldCount() {
/* 717 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public void clearInventory() {
/* 722 */     for (int var1 = 0; var1 < this.inventory.length; var1++)
/*     */     {
/* 724 */       this.inventory[var1] = null;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\tileentity\TileEntityHopper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */