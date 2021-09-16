/*     */ package net.minecraft.tileentity;
/*     */ 
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.BlockFurnace;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.player.InventoryPlayer;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.inventory.Container;
/*     */ import net.minecraft.inventory.ContainerFurnace;
/*     */ import net.minecraft.inventory.IInventory;
/*     */ import net.minecraft.inventory.ISidedInventory;
/*     */ import net.minecraft.inventory.SlotFurnaceFuel;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemHoe;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.item.ItemSword;
/*     */ import net.minecraft.item.ItemTool;
/*     */ import net.minecraft.item.crafting.FurnaceRecipes;
/*     */ import net.minecraft.nbt.NBTBase;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.nbt.NBTTagList;
/*     */ import net.minecraft.server.gui.IUpdatePlayerListBox;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.MathHelper;
/*     */ 
/*     */ public class TileEntityFurnace
/*     */   extends TileEntityLockable implements IUpdatePlayerListBox, ISidedInventory {
/*  30 */   private static final int[] slotsTop = new int[1];
/*  31 */   private static final int[] slotsBottom = new int[] { 2, 1 };
/*  32 */   private static final int[] slotsSides = new int[] { 1 };
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  37 */   private ItemStack[] furnaceItemStacks = new ItemStack[3];
/*     */ 
/*     */   
/*     */   private int furnaceBurnTime;
/*     */ 
/*     */   
/*     */   private int currentItemBurnTime;
/*     */ 
/*     */   
/*     */   private int field_174906_k;
/*     */   
/*     */   private int field_174905_l;
/*     */   
/*     */   private String furnaceCustomName;
/*     */   
/*     */   private static final String __OBFID = "CL_00000357";
/*     */ 
/*     */   
/*     */   public int getSizeInventory() {
/*  56 */     return this.furnaceItemStacks.length;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack getStackInSlot(int slotIn) {
/*  64 */     return this.furnaceItemStacks[slotIn];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack decrStackSize(int index, int count) {
/*  73 */     if (this.furnaceItemStacks[index] != null) {
/*     */ 
/*     */ 
/*     */       
/*  77 */       if ((this.furnaceItemStacks[index]).stackSize <= count) {
/*     */         
/*  79 */         ItemStack itemStack = this.furnaceItemStacks[index];
/*  80 */         this.furnaceItemStacks[index] = null;
/*  81 */         return itemStack;
/*     */       } 
/*     */ 
/*     */       
/*  85 */       ItemStack var3 = this.furnaceItemStacks[index].splitStack(count);
/*     */       
/*  87 */       if ((this.furnaceItemStacks[index]).stackSize == 0)
/*     */       {
/*  89 */         this.furnaceItemStacks[index] = null;
/*     */       }
/*     */       
/*  92 */       return var3;
/*     */     } 
/*     */ 
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
/*     */   
/*     */   public ItemStack getStackInSlotOnClosing(int index) {
/* 107 */     if (this.furnaceItemStacks[index] != null) {
/*     */       
/* 109 */       ItemStack var2 = this.furnaceItemStacks[index];
/* 110 */       this.furnaceItemStacks[index] = null;
/* 111 */       return var2;
/*     */     } 
/*     */ 
/*     */     
/* 115 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setInventorySlotContents(int index, ItemStack stack) {
/* 124 */     boolean var3 = (stack != null && stack.isItemEqual(this.furnaceItemStacks[index]) && ItemStack.areItemStackTagsEqual(stack, this.furnaceItemStacks[index]));
/* 125 */     this.furnaceItemStacks[index] = stack;
/*     */     
/* 127 */     if (stack != null && stack.stackSize > getInventoryStackLimit())
/*     */     {
/* 129 */       stack.stackSize = getInventoryStackLimit();
/*     */     }
/*     */     
/* 132 */     if (index == 0 && !var3) {
/*     */       
/* 134 */       this.field_174905_l = func_174904_a(stack);
/* 135 */       this.field_174906_k = 0;
/* 136 */       markDirty();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getName() {
/* 145 */     return hasCustomName() ? this.furnaceCustomName : "container.furnace";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasCustomName() {
/* 153 */     return (this.furnaceCustomName != null && this.furnaceCustomName.length() > 0);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setCustomInventoryName(String p_145951_1_) {
/* 158 */     this.furnaceCustomName = p_145951_1_;
/*     */   }
/*     */ 
/*     */   
/*     */   public void readFromNBT(NBTTagCompound compound) {
/* 163 */     super.readFromNBT(compound);
/* 164 */     NBTTagList var2 = compound.getTagList("Items", 10);
/* 165 */     this.furnaceItemStacks = new ItemStack[getSizeInventory()];
/*     */     
/* 167 */     for (int var3 = 0; var3 < var2.tagCount(); var3++) {
/*     */       
/* 169 */       NBTTagCompound var4 = var2.getCompoundTagAt(var3);
/* 170 */       byte var5 = var4.getByte("Slot");
/*     */       
/* 172 */       if (var5 >= 0 && var5 < this.furnaceItemStacks.length)
/*     */       {
/* 174 */         this.furnaceItemStacks[var5] = ItemStack.loadItemStackFromNBT(var4);
/*     */       }
/*     */     } 
/*     */     
/* 178 */     this.furnaceBurnTime = compound.getShort("BurnTime");
/* 179 */     this.field_174906_k = compound.getShort("CookTime");
/* 180 */     this.field_174905_l = compound.getShort("CookTimeTotal");
/* 181 */     this.currentItemBurnTime = getItemBurnTime(this.furnaceItemStacks[1]);
/*     */     
/* 183 */     if (compound.hasKey("CustomName", 8))
/*     */     {
/* 185 */       this.furnaceCustomName = compound.getString("CustomName");
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void writeToNBT(NBTTagCompound compound) {
/* 191 */     super.writeToNBT(compound);
/* 192 */     compound.setShort("BurnTime", (short)this.furnaceBurnTime);
/* 193 */     compound.setShort("CookTime", (short)this.field_174906_k);
/* 194 */     compound.setShort("CookTimeTotal", (short)this.field_174905_l);
/* 195 */     NBTTagList var2 = new NBTTagList();
/*     */     
/* 197 */     for (int var3 = 0; var3 < this.furnaceItemStacks.length; var3++) {
/*     */       
/* 199 */       if (this.furnaceItemStacks[var3] != null) {
/*     */         
/* 201 */         NBTTagCompound var4 = new NBTTagCompound();
/* 202 */         var4.setByte("Slot", (byte)var3);
/* 203 */         this.furnaceItemStacks[var3].writeToNBT(var4);
/* 204 */         var2.appendTag((NBTBase)var4);
/*     */       } 
/*     */     } 
/*     */     
/* 208 */     compound.setTag("Items", (NBTBase)var2);
/*     */     
/* 210 */     if (hasCustomName())
/*     */     {
/* 212 */       compound.setString("CustomName", this.furnaceCustomName);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getInventoryStackLimit() {
/* 222 */     return 64;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isBurning() {
/* 230 */     return (this.furnaceBurnTime > 0);
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean func_174903_a(IInventory p_174903_0_) {
/* 235 */     return (p_174903_0_.getField(0) > 0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void update() {
/* 243 */     boolean var1 = isBurning();
/* 244 */     boolean var2 = false;
/*     */     
/* 246 */     if (isBurning())
/*     */     {
/* 248 */       this.furnaceBurnTime--;
/*     */     }
/*     */     
/* 251 */     if (!this.worldObj.isRemote) {
/*     */       
/* 253 */       if (!isBurning() && (this.furnaceItemStacks[1] == null || this.furnaceItemStacks[0] == null)) {
/*     */         
/* 255 */         if (!isBurning() && this.field_174906_k > 0)
/*     */         {
/* 257 */           this.field_174906_k = MathHelper.clamp_int(this.field_174906_k - 2, 0, this.field_174905_l);
/*     */         }
/*     */       }
/*     */       else {
/*     */         
/* 262 */         if (!isBurning() && canSmelt()) {
/*     */           
/* 264 */           this.currentItemBurnTime = this.furnaceBurnTime = getItemBurnTime(this.furnaceItemStacks[1]);
/*     */           
/* 266 */           if (isBurning()) {
/*     */             
/* 268 */             var2 = true;
/*     */             
/* 270 */             if (this.furnaceItemStacks[1] != null) {
/*     */               
/* 272 */               (this.furnaceItemStacks[1]).stackSize--;
/*     */               
/* 274 */               if ((this.furnaceItemStacks[1]).stackSize == 0) {
/*     */                 
/* 276 */                 Item var3 = this.furnaceItemStacks[1].getItem().getContainerItem();
/* 277 */                 this.furnaceItemStacks[1] = (var3 != null) ? new ItemStack(var3) : null;
/*     */               } 
/*     */             } 
/*     */           } 
/*     */         } 
/*     */         
/* 283 */         if (isBurning() && canSmelt()) {
/*     */           
/* 285 */           this.field_174906_k++;
/*     */           
/* 287 */           if (this.field_174906_k == this.field_174905_l)
/*     */           {
/* 289 */             this.field_174906_k = 0;
/* 290 */             this.field_174905_l = func_174904_a(this.furnaceItemStacks[0]);
/* 291 */             smeltItem();
/* 292 */             var2 = true;
/*     */           }
/*     */         
/*     */         } else {
/*     */           
/* 297 */           this.field_174906_k = 0;
/*     */         } 
/*     */       } 
/*     */       
/* 301 */       if (var1 != isBurning()) {
/*     */         
/* 303 */         var2 = true;
/* 304 */         BlockFurnace.func_176446_a(isBurning(), this.worldObj, this.pos);
/*     */       } 
/*     */     } 
/*     */     
/* 308 */     if (var2)
/*     */     {
/* 310 */       markDirty();
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public int func_174904_a(ItemStack p_174904_1_) {
/* 316 */     return 200;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean canSmelt() {
/* 324 */     if (this.furnaceItemStacks[0] == null)
/*     */     {
/* 326 */       return false;
/*     */     }
/*     */ 
/*     */     
/* 330 */     ItemStack var1 = FurnaceRecipes.instance().getSmeltingResult(this.furnaceItemStacks[0]);
/* 331 */     return (var1 == null) ? false : ((this.furnaceItemStacks[2] == null) ? true : (!this.furnaceItemStacks[2].isItemEqual(var1) ? false : (((this.furnaceItemStacks[2]).stackSize < getInventoryStackLimit() && (this.furnaceItemStacks[2]).stackSize < this.furnaceItemStacks[2].getMaxStackSize()) ? true : (((this.furnaceItemStacks[2]).stackSize < var1.getMaxStackSize())))));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void smeltItem() {
/* 340 */     if (canSmelt()) {
/*     */       
/* 342 */       ItemStack var1 = FurnaceRecipes.instance().getSmeltingResult(this.furnaceItemStacks[0]);
/*     */       
/* 344 */       if (this.furnaceItemStacks[2] == null) {
/*     */         
/* 346 */         this.furnaceItemStacks[2] = var1.copy();
/*     */       }
/* 348 */       else if (this.furnaceItemStacks[2].getItem() == var1.getItem()) {
/*     */         
/* 350 */         (this.furnaceItemStacks[2]).stackSize++;
/*     */       } 
/*     */       
/* 353 */       if (this.furnaceItemStacks[0].getItem() == Item.getItemFromBlock(Blocks.sponge) && this.furnaceItemStacks[0].getMetadata() == 1 && this.furnaceItemStacks[1] != null && this.furnaceItemStacks[1].getItem() == Items.bucket)
/*     */       {
/* 355 */         this.furnaceItemStacks[1] = new ItemStack(Items.water_bucket);
/*     */       }
/*     */       
/* 358 */       (this.furnaceItemStacks[0]).stackSize--;
/*     */       
/* 360 */       if ((this.furnaceItemStacks[0]).stackSize <= 0)
/*     */       {
/* 362 */         this.furnaceItemStacks[0] = null;
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int getItemBurnTime(ItemStack p_145952_0_) {
/* 373 */     if (p_145952_0_ == null)
/*     */     {
/* 375 */       return 0;
/*     */     }
/*     */ 
/*     */     
/* 379 */     Item var1 = p_145952_0_.getItem();
/*     */     
/* 381 */     if (var1 instanceof net.minecraft.item.ItemBlock && Block.getBlockFromItem(var1) != Blocks.air) {
/*     */       
/* 383 */       Block var2 = Block.getBlockFromItem(var1);
/*     */       
/* 385 */       if (var2 == Blocks.wooden_slab)
/*     */       {
/* 387 */         return 150;
/*     */       }
/*     */       
/* 390 */       if (var2.getMaterial() == Material.wood)
/*     */       {
/* 392 */         return 300;
/*     */       }
/*     */       
/* 395 */       if (var2 == Blocks.coal_block)
/*     */       {
/* 397 */         return 16000;
/*     */       }
/*     */     } 
/*     */     
/* 401 */     return (var1 instanceof ItemTool && ((ItemTool)var1).getToolMaterialName().equals("WOOD")) ? 200 : ((var1 instanceof ItemSword && ((ItemSword)var1).getToolMaterialName().equals("WOOD")) ? 200 : ((var1 instanceof ItemHoe && ((ItemHoe)var1).getMaterialName().equals("WOOD")) ? 200 : ((var1 == Items.stick) ? 100 : ((var1 == Items.coal) ? 1600 : ((var1 == Items.lava_bucket) ? 20000 : ((var1 == Item.getItemFromBlock(Blocks.sapling)) ? 100 : ((var1 == Items.blaze_rod) ? 2400 : 0)))))));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean isItemFuel(ItemStack p_145954_0_) {
/* 407 */     return (getItemBurnTime(p_145954_0_) > 0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isUseableByPlayer(EntityPlayer playerIn) {
/* 415 */     return (this.worldObj.getTileEntity(this.pos) != this) ? false : ((playerIn.getDistanceSq(this.pos.getX() + 0.5D, this.pos.getY() + 0.5D, this.pos.getZ() + 0.5D) <= 64.0D));
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
/* 427 */     return (index == 2) ? false : ((index != 1) ? true : (!(!isItemFuel(stack) && !SlotFurnaceFuel.func_178173_c_(stack))));
/*     */   }
/*     */ 
/*     */   
/*     */   public int[] getSlotsForFace(EnumFacing side) {
/* 432 */     return (side == EnumFacing.DOWN) ? slotsBottom : ((side == EnumFacing.UP) ? slotsTop : slotsSides);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canInsertItem(int slotIn, ItemStack itemStackIn, EnumFacing direction) {
/* 441 */     return isItemValidForSlot(slotIn, itemStackIn);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canExtractItem(int slotId, ItemStack stack, EnumFacing direction) {
/* 450 */     if (direction == EnumFacing.DOWN && slotId == 1) {
/*     */       
/* 452 */       Item var4 = stack.getItem();
/*     */       
/* 454 */       if (var4 != Items.water_bucket && var4 != Items.bucket)
/*     */       {
/* 456 */         return false;
/*     */       }
/*     */     } 
/*     */     
/* 460 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getGuiID() {
/* 465 */     return "minecraft:furnace";
/*     */   }
/*     */ 
/*     */   
/*     */   public Container createContainer(InventoryPlayer playerInventory, EntityPlayer playerIn) {
/* 470 */     return (Container)new ContainerFurnace(playerInventory, (IInventory)this);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getField(int id) {
/* 475 */     switch (id) {
/*     */       
/*     */       case 0:
/* 478 */         return this.furnaceBurnTime;
/*     */       
/*     */       case 1:
/* 481 */         return this.currentItemBurnTime;
/*     */       
/*     */       case 2:
/* 484 */         return this.field_174906_k;
/*     */       
/*     */       case 3:
/* 487 */         return this.field_174905_l;
/*     */     } 
/*     */     
/* 490 */     return 0;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setField(int id, int value) {
/* 496 */     switch (id) {
/*     */       
/*     */       case 0:
/* 499 */         this.furnaceBurnTime = value;
/*     */         break;
/*     */       
/*     */       case 1:
/* 503 */         this.currentItemBurnTime = value;
/*     */         break;
/*     */       
/*     */       case 2:
/* 507 */         this.field_174906_k = value;
/*     */         break;
/*     */       
/*     */       case 3:
/* 511 */         this.field_174905_l = value;
/*     */         break;
/*     */     } 
/*     */   }
/*     */   
/*     */   public int getFieldCount() {
/* 517 */     return 4;
/*     */   }
/*     */ 
/*     */   
/*     */   public void clearInventory() {
/* 522 */     for (int var1 = 0; var1 < this.furnaceItemStacks.length; var1++)
/*     */     {
/* 524 */       this.furnaceItemStacks[var1] = null;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\tileentity\TileEntityFurnace.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */