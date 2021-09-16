/*     */ package net.minecraft.entity.player;
/*     */ 
/*     */ import java.util.concurrent.Callable;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.command.server.CommandTestForBlock;
/*     */ import net.minecraft.crash.CrashReport;
/*     */ import net.minecraft.crash.CrashReportCategory;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.inventory.IInventory;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemArmor;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTBase;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.nbt.NBTTagList;
/*     */ import net.minecraft.util.ChatComponentText;
/*     */ import net.minecraft.util.ChatComponentTranslation;
/*     */ import net.minecraft.util.IChatComponent;
/*     */ import net.minecraft.util.ReportedException;
/*     */ 
/*     */ public class InventoryPlayer
/*     */   implements IInventory
/*     */ {
/*  24 */   public ItemStack[] mainInventory = new ItemStack[36];
/*     */ 
/*     */   
/*  27 */   public ItemStack[] armorInventory = new ItemStack[4];
/*     */ 
/*     */   
/*     */   public int currentItem;
/*     */ 
/*     */   
/*     */   public EntityPlayer player;
/*     */ 
/*     */   
/*     */   private ItemStack itemStack;
/*     */ 
/*     */   
/*     */   public boolean inventoryChanged;
/*     */   
/*     */   private static final String __OBFID = "CL_00001709";
/*     */ 
/*     */   
/*     */   public InventoryPlayer(EntityPlayer p_i1750_1_) {
/*  45 */     this.player = p_i1750_1_;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack getCurrentItem() {
/*  53 */     return (this.currentItem < 9 && this.currentItem >= 0) ? this.mainInventory[this.currentItem] : null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int getHotbarSize() {
/*  61 */     return 9;
/*     */   }
/*     */ 
/*     */   
/*     */   private int getInventorySlotContainItem(Item itemIn) {
/*  66 */     for (int var2 = 0; var2 < this.mainInventory.length; var2++) {
/*     */       
/*  68 */       if (this.mainInventory[var2] != null && this.mainInventory[var2].getItem() == itemIn)
/*     */       {
/*  70 */         return var2;
/*     */       }
/*     */     } 
/*     */     
/*  74 */     return -1;
/*     */   }
/*     */ 
/*     */   
/*     */   private int getInventorySlotContainItemAndDamage(Item p_146024_1_, int p_146024_2_) {
/*  79 */     for (int var3 = 0; var3 < this.mainInventory.length; var3++) {
/*     */       
/*  81 */       if (this.mainInventory[var3] != null && this.mainInventory[var3].getItem() == p_146024_1_ && this.mainInventory[var3].getMetadata() == p_146024_2_)
/*     */       {
/*  83 */         return var3;
/*     */       }
/*     */     } 
/*     */     
/*  87 */     return -1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private int storeItemStack(ItemStack p_70432_1_) {
/*  95 */     for (int var2 = 0; var2 < this.mainInventory.length; var2++) {
/*     */       
/*  97 */       if (this.mainInventory[var2] != null && this.mainInventory[var2].getItem() == p_70432_1_.getItem() && this.mainInventory[var2].isStackable() && (this.mainInventory[var2]).stackSize < this.mainInventory[var2].getMaxStackSize() && (this.mainInventory[var2]).stackSize < getInventoryStackLimit() && (!this.mainInventory[var2].getHasSubtypes() || this.mainInventory[var2].getMetadata() == p_70432_1_.getMetadata()) && ItemStack.areItemStackTagsEqual(this.mainInventory[var2], p_70432_1_))
/*     */       {
/*  99 */         return var2;
/*     */       }
/*     */     } 
/*     */     
/* 103 */     return -1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getFirstEmptyStack() {
/* 111 */     for (int var1 = 0; var1 < this.mainInventory.length; var1++) {
/*     */       
/* 113 */       if (this.mainInventory[var1] == null)
/*     */       {
/* 115 */         return var1;
/*     */       }
/*     */     } 
/*     */     
/* 119 */     return -1;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setCurrentItem(Item p_146030_1_, int p_146030_2_, boolean p_146030_3_, boolean p_146030_4_) {
/* 124 */     ItemStack var5 = getCurrentItem();
/* 125 */     int var6 = p_146030_3_ ? getInventorySlotContainItemAndDamage(p_146030_1_, p_146030_2_) : getInventorySlotContainItem(p_146030_1_);
/*     */     
/* 127 */     if (var6 >= 0 && var6 < 9) {
/*     */       
/* 129 */       this.currentItem = var6;
/*     */     }
/* 131 */     else if (p_146030_4_ && p_146030_1_ != null) {
/*     */       
/* 133 */       int var7 = getFirstEmptyStack();
/*     */       
/* 135 */       if (var7 >= 0 && var7 < 9)
/*     */       {
/* 137 */         this.currentItem = var7;
/*     */       }
/*     */       
/* 140 */       if (var5 == null || !var5.isItemEnchantable() || getInventorySlotContainItemAndDamage(var5.getItem(), var5.getItemDamage()) != this.currentItem) {
/*     */         
/* 142 */         int var9, var8 = getInventorySlotContainItemAndDamage(p_146030_1_, p_146030_2_);
/*     */ 
/*     */         
/* 145 */         if (var8 >= 0) {
/*     */           
/* 147 */           var9 = (this.mainInventory[var8]).stackSize;
/* 148 */           this.mainInventory[var8] = this.mainInventory[this.currentItem];
/*     */         }
/*     */         else {
/*     */           
/* 152 */           var9 = 1;
/*     */         } 
/*     */         
/* 155 */         this.mainInventory[this.currentItem] = new ItemStack(p_146030_1_, var9, p_146030_2_);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void changeCurrentItem(int p_70453_1_) {
/* 165 */     if (p_70453_1_ > 0)
/*     */     {
/* 167 */       p_70453_1_ = 1;
/*     */     }
/*     */     
/* 170 */     if (p_70453_1_ < 0)
/*     */     {
/* 172 */       p_70453_1_ = -1;
/*     */     }
/*     */     
/* 175 */     for (this.currentItem -= p_70453_1_; this.currentItem < 0; this.currentItem += 9);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 180 */     while (this.currentItem >= 9)
/*     */     {
/* 182 */       this.currentItem -= 9;
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public int func_174925_a(Item p_174925_1_, int p_174925_2_, int p_174925_3_, NBTTagCompound p_174925_4_) {
/* 188 */     int var5 = 0;
/*     */ 
/*     */     
/*     */     int var6;
/*     */     
/* 193 */     for (var6 = 0; var6 < this.mainInventory.length; var6++) {
/*     */       
/* 195 */       ItemStack var7 = this.mainInventory[var6];
/*     */       
/* 197 */       if (var7 != null && (p_174925_1_ == null || var7.getItem() == p_174925_1_) && (p_174925_2_ <= -1 || var7.getMetadata() == p_174925_2_) && (p_174925_4_ == null || CommandTestForBlock.func_175775_a((NBTBase)p_174925_4_, (NBTBase)var7.getTagCompound(), true))) {
/*     */         
/* 199 */         int var8 = (p_174925_3_ <= 0) ? var7.stackSize : Math.min(p_174925_3_ - var5, var7.stackSize);
/* 200 */         var5 += var8;
/*     */         
/* 202 */         if (p_174925_3_ != 0) {
/*     */           
/* 204 */           (this.mainInventory[var6]).stackSize -= var8;
/*     */           
/* 206 */           if ((this.mainInventory[var6]).stackSize == 0)
/*     */           {
/* 208 */             this.mainInventory[var6] = null;
/*     */           }
/*     */           
/* 211 */           if (p_174925_3_ > 0 && var5 >= p_174925_3_)
/*     */           {
/* 213 */             return var5;
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 219 */     for (var6 = 0; var6 < this.armorInventory.length; var6++) {
/*     */       
/* 221 */       ItemStack var7 = this.armorInventory[var6];
/*     */       
/* 223 */       if (var7 != null && (p_174925_1_ == null || var7.getItem() == p_174925_1_) && (p_174925_2_ <= -1 || var7.getMetadata() == p_174925_2_) && (p_174925_4_ == null || CommandTestForBlock.func_175775_a((NBTBase)p_174925_4_, (NBTBase)var7.getTagCompound(), false))) {
/*     */         
/* 225 */         int var8 = (p_174925_3_ <= 0) ? var7.stackSize : Math.min(p_174925_3_ - var5, var7.stackSize);
/* 226 */         var5 += var8;
/*     */         
/* 228 */         if (p_174925_3_ != 0) {
/*     */           
/* 230 */           (this.armorInventory[var6]).stackSize -= var8;
/*     */           
/* 232 */           if ((this.armorInventory[var6]).stackSize == 0)
/*     */           {
/* 234 */             this.armorInventory[var6] = null;
/*     */           }
/*     */           
/* 237 */           if (p_174925_3_ > 0 && var5 >= p_174925_3_)
/*     */           {
/* 239 */             return var5;
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 245 */     if (this.itemStack != null) {
/*     */       
/* 247 */       if (p_174925_1_ != null && this.itemStack.getItem() != p_174925_1_)
/*     */       {
/* 249 */         return var5;
/*     */       }
/*     */       
/* 252 */       if (p_174925_2_ > -1 && this.itemStack.getMetadata() != p_174925_2_)
/*     */       {
/* 254 */         return var5;
/*     */       }
/*     */       
/* 257 */       if (p_174925_4_ != null && !CommandTestForBlock.func_175775_a((NBTBase)p_174925_4_, (NBTBase)this.itemStack.getTagCompound(), false))
/*     */       {
/* 259 */         return var5;
/*     */       }
/*     */       
/* 262 */       var6 = (p_174925_3_ <= 0) ? this.itemStack.stackSize : Math.min(p_174925_3_ - var5, this.itemStack.stackSize);
/* 263 */       var5 += var6;
/*     */       
/* 265 */       if (p_174925_3_ != 0) {
/*     */         
/* 267 */         this.itemStack.stackSize -= var6;
/*     */         
/* 269 */         if (this.itemStack.stackSize == 0)
/*     */         {
/* 271 */           this.itemStack = null;
/*     */         }
/*     */         
/* 274 */         if (p_174925_3_ > 0 && var5 >= p_174925_3_)
/*     */         {
/* 276 */           return var5;
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 281 */     return var5;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private int storePartialItemStack(ItemStack p_70452_1_) {
/* 290 */     Item var2 = p_70452_1_.getItem();
/* 291 */     int var3 = p_70452_1_.stackSize;
/* 292 */     int var4 = storeItemStack(p_70452_1_);
/*     */     
/* 294 */     if (var4 < 0)
/*     */     {
/* 296 */       var4 = getFirstEmptyStack();
/*     */     }
/*     */     
/* 299 */     if (var4 < 0)
/*     */     {
/* 301 */       return var3;
/*     */     }
/*     */ 
/*     */     
/* 305 */     if (this.mainInventory[var4] == null) {
/*     */       
/* 307 */       this.mainInventory[var4] = new ItemStack(var2, 0, p_70452_1_.getMetadata());
/*     */       
/* 309 */       if (p_70452_1_.hasTagCompound())
/*     */       {
/* 311 */         this.mainInventory[var4].setTagCompound((NBTTagCompound)p_70452_1_.getTagCompound().copy());
/*     */       }
/*     */     } 
/*     */     
/* 315 */     int var5 = var3;
/*     */     
/* 317 */     if (var3 > this.mainInventory[var4].getMaxStackSize() - (this.mainInventory[var4]).stackSize)
/*     */     {
/* 319 */       var5 = this.mainInventory[var4].getMaxStackSize() - (this.mainInventory[var4]).stackSize;
/*     */     }
/*     */     
/* 322 */     if (var5 > getInventoryStackLimit() - (this.mainInventory[var4]).stackSize)
/*     */     {
/* 324 */       var5 = getInventoryStackLimit() - (this.mainInventory[var4]).stackSize;
/*     */     }
/*     */     
/* 327 */     if (var5 == 0)
/*     */     {
/* 329 */       return var3;
/*     */     }
/*     */ 
/*     */     
/* 333 */     var3 -= var5;
/* 334 */     (this.mainInventory[var4]).stackSize += var5;
/* 335 */     (this.mainInventory[var4]).animationsToGo = 5;
/* 336 */     return var3;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void decrementAnimations() {
/* 347 */     for (int var1 = 0; var1 < this.mainInventory.length; var1++) {
/*     */       
/* 349 */       if (this.mainInventory[var1] != null)
/*     */       {
/* 351 */         this.mainInventory[var1].updateAnimation(this.player.worldObj, (Entity)this.player, var1, (this.currentItem == var1));
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean consumeInventoryItem(Item p_146026_1_) {
/* 361 */     int var2 = getInventorySlotContainItem(p_146026_1_);
/*     */     
/* 363 */     if (var2 < 0)
/*     */     {
/* 365 */       return false;
/*     */     }
/*     */ 
/*     */     
/* 369 */     if (--(this.mainInventory[var2]).stackSize <= 0)
/*     */     {
/* 371 */       this.mainInventory[var2] = null;
/*     */     }
/*     */     
/* 374 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasItem(Item p_146028_1_) {
/* 383 */     int var2 = getInventorySlotContainItem(p_146028_1_);
/* 384 */     return (var2 >= 0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean addItemStackToInventory(final ItemStack p_70441_1_) {
/* 392 */     if (p_70441_1_ != null && p_70441_1_.stackSize != 0 && p_70441_1_.getItem() != null) {
/*     */       try {
/*     */         int var2;
/*     */ 
/*     */ 
/*     */         
/* 398 */         if (p_70441_1_.isItemDamaged()) {
/*     */           
/* 400 */           var2 = getFirstEmptyStack();
/*     */           
/* 402 */           if (var2 >= 0) {
/*     */             
/* 404 */             this.mainInventory[var2] = ItemStack.copyItemStack(p_70441_1_);
/* 405 */             (this.mainInventory[var2]).animationsToGo = 5;
/* 406 */             p_70441_1_.stackSize = 0;
/* 407 */             return true;
/*     */           } 
/* 409 */           if (this.player.capabilities.isCreativeMode) {
/*     */             
/* 411 */             p_70441_1_.stackSize = 0;
/* 412 */             return true;
/*     */           } 
/*     */ 
/*     */           
/* 416 */           return false;
/*     */         } 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*     */         do {
/* 423 */           var2 = p_70441_1_.stackSize;
/* 424 */           p_70441_1_.stackSize = storePartialItemStack(p_70441_1_);
/*     */         }
/* 426 */         while (p_70441_1_.stackSize > 0 && p_70441_1_.stackSize < var2);
/*     */         
/* 428 */         if (p_70441_1_.stackSize == var2 && this.player.capabilities.isCreativeMode) {
/*     */           
/* 430 */           p_70441_1_.stackSize = 0;
/* 431 */           return true;
/*     */         } 
/*     */ 
/*     */         
/* 435 */         return (p_70441_1_.stackSize < var2);
/*     */ 
/*     */       
/*     */       }
/* 439 */       catch (Throwable var5) {
/*     */         
/* 441 */         CrashReport var3 = CrashReport.makeCrashReport(var5, "Adding item to inventory");
/* 442 */         CrashReportCategory var4 = var3.makeCategory("Item being added");
/* 443 */         var4.addCrashSection("Item ID", Integer.valueOf(Item.getIdFromItem(p_70441_1_.getItem())));
/* 444 */         var4.addCrashSection("Item data", Integer.valueOf(p_70441_1_.getMetadata()));
/* 445 */         var4.addCrashSectionCallable("Item name", new Callable()
/*     */             {
/*     */               private static final String __OBFID = "CL_00001710";
/*     */               
/*     */               public String call() {
/* 450 */                 return p_70441_1_.getDisplayName();
/*     */               }
/*     */             });
/* 453 */         throw new ReportedException(var3);
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/* 458 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack decrStackSize(int index, int count) {
/* 468 */     ItemStack[] var3 = this.mainInventory;
/*     */     
/* 470 */     if (index >= this.mainInventory.length) {
/*     */       
/* 472 */       var3 = this.armorInventory;
/* 473 */       index -= this.mainInventory.length;
/*     */     } 
/*     */     
/* 476 */     if (var3[index] != null) {
/*     */ 
/*     */ 
/*     */       
/* 480 */       if ((var3[index]).stackSize <= count) {
/*     */         
/* 482 */         ItemStack itemStack = var3[index];
/* 483 */         var3[index] = null;
/* 484 */         return itemStack;
/*     */       } 
/*     */ 
/*     */       
/* 488 */       ItemStack var4 = var3[index].splitStack(count);
/*     */       
/* 490 */       if ((var3[index]).stackSize == 0)
/*     */       {
/* 492 */         var3[index] = null;
/*     */       }
/*     */       
/* 495 */       return var4;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 500 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack getStackInSlotOnClosing(int index) {
/* 510 */     ItemStack[] var2 = this.mainInventory;
/*     */     
/* 512 */     if (index >= this.mainInventory.length) {
/*     */       
/* 514 */       var2 = this.armorInventory;
/* 515 */       index -= this.mainInventory.length;
/*     */     } 
/*     */     
/* 518 */     if (var2[index] != null) {
/*     */       
/* 520 */       ItemStack var3 = var2[index];
/* 521 */       var2[index] = null;
/* 522 */       return var3;
/*     */     } 
/*     */ 
/*     */     
/* 526 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setInventorySlotContents(int index, ItemStack stack) {
/* 535 */     ItemStack[] var3 = this.mainInventory;
/*     */     
/* 537 */     if (index >= var3.length) {
/*     */       
/* 539 */       index -= var3.length;
/* 540 */       var3 = this.armorInventory;
/*     */     } 
/*     */     
/* 543 */     var3[index] = stack;
/*     */   }
/*     */ 
/*     */   
/*     */   public float getStrVsBlock(Block p_146023_1_) {
/* 548 */     float var2 = 1.0F;
/*     */     
/* 550 */     if (this.mainInventory[this.currentItem] != null)
/*     */     {
/* 552 */       var2 *= this.mainInventory[this.currentItem].getStrVsBlock(p_146023_1_);
/*     */     }
/*     */     
/* 555 */     return var2;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NBTTagList writeToNBT(NBTTagList p_70442_1_) {
/*     */     int var2;
/* 567 */     for (var2 = 0; var2 < this.mainInventory.length; var2++) {
/*     */       
/* 569 */       if (this.mainInventory[var2] != null) {
/*     */         
/* 571 */         NBTTagCompound var3 = new NBTTagCompound();
/* 572 */         var3.setByte("Slot", (byte)var2);
/* 573 */         this.mainInventory[var2].writeToNBT(var3);
/* 574 */         p_70442_1_.appendTag((NBTBase)var3);
/*     */       } 
/*     */     } 
/*     */     
/* 578 */     for (var2 = 0; var2 < this.armorInventory.length; var2++) {
/*     */       
/* 580 */       if (this.armorInventory[var2] != null) {
/*     */         
/* 582 */         NBTTagCompound var3 = new NBTTagCompound();
/* 583 */         var3.setByte("Slot", (byte)(var2 + 100));
/* 584 */         this.armorInventory[var2].writeToNBT(var3);
/* 585 */         p_70442_1_.appendTag((NBTBase)var3);
/*     */       } 
/*     */     } 
/*     */     
/* 589 */     return p_70442_1_;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void readFromNBT(NBTTagList p_70443_1_) {
/* 597 */     this.mainInventory = new ItemStack[36];
/* 598 */     this.armorInventory = new ItemStack[4];
/*     */     
/* 600 */     for (int var2 = 0; var2 < p_70443_1_.tagCount(); var2++) {
/*     */       
/* 602 */       NBTTagCompound var3 = p_70443_1_.getCompoundTagAt(var2);
/* 603 */       int var4 = var3.getByte("Slot") & 0xFF;
/* 604 */       ItemStack var5 = ItemStack.loadItemStackFromNBT(var3);
/*     */       
/* 606 */       if (var5 != null) {
/*     */         
/* 608 */         if (var4 >= 0 && var4 < this.mainInventory.length)
/*     */         {
/* 610 */           this.mainInventory[var4] = var5;
/*     */         }
/*     */         
/* 613 */         if (var4 >= 100 && var4 < this.armorInventory.length + 100)
/*     */         {
/* 615 */           this.armorInventory[var4 - 100] = var5;
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getSizeInventory() {
/* 626 */     return this.mainInventory.length + 4;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack getStackInSlot(int slotIn) {
/* 634 */     ItemStack[] var2 = this.mainInventory;
/*     */     
/* 636 */     if (slotIn >= var2.length) {
/*     */       
/* 638 */       slotIn -= var2.length;
/* 639 */       var2 = this.armorInventory;
/*     */     } 
/*     */     
/* 642 */     return var2[slotIn];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getName() {
/* 650 */     return "container.inventory";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasCustomName() {
/* 658 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public IChatComponent getDisplayName() {
/* 663 */     return hasCustomName() ? (IChatComponent)new ChatComponentText(getName()) : (IChatComponent)new ChatComponentTranslation(getName(), new Object[0]);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getInventoryStackLimit() {
/* 672 */     return 64;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_146025_b(Block p_146025_1_) {
/* 677 */     if (p_146025_1_.getMaterial().isToolNotRequired())
/*     */     {
/* 679 */       return true;
/*     */     }
/*     */ 
/*     */     
/* 683 */     ItemStack var2 = getStackInSlot(this.currentItem);
/* 684 */     return (var2 != null) ? var2.canHarvestBlock(p_146025_1_) : false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack armorItemInSlot(int p_70440_1_) {
/* 693 */     return this.armorInventory[p_70440_1_];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getTotalArmorValue() {
/* 701 */     int var1 = 0;
/*     */     
/* 703 */     for (int var2 = 0; var2 < this.armorInventory.length; var2++) {
/*     */       
/* 705 */       if (this.armorInventory[var2] != null && this.armorInventory[var2].getItem() instanceof ItemArmor) {
/*     */         
/* 707 */         int var3 = ((ItemArmor)this.armorInventory[var2].getItem()).damageReduceAmount;
/* 708 */         var1 += var3;
/*     */       } 
/*     */     } 
/*     */     
/* 712 */     return var1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void damageArmor(float p_70449_1_) {
/* 720 */     p_70449_1_ /= 4.0F;
/*     */     
/* 722 */     if (p_70449_1_ < 1.0F)
/*     */     {
/* 724 */       p_70449_1_ = 1.0F;
/*     */     }
/*     */     
/* 727 */     for (int var2 = 0; var2 < this.armorInventory.length; var2++) {
/*     */       
/* 729 */       if (this.armorInventory[var2] != null && this.armorInventory[var2].getItem() instanceof ItemArmor) {
/*     */         
/* 731 */         this.armorInventory[var2].damageItem((int)p_70449_1_, this.player);
/*     */         
/* 733 */         if ((this.armorInventory[var2]).stackSize == 0)
/*     */         {
/* 735 */           this.armorInventory[var2] = null;
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void dropAllItems() {
/*     */     int var1;
/* 748 */     for (var1 = 0; var1 < this.mainInventory.length; var1++) {
/*     */       
/* 750 */       if (this.mainInventory[var1] != null) {
/*     */         
/* 752 */         this.player.func_146097_a(this.mainInventory[var1], true, false);
/* 753 */         this.mainInventory[var1] = null;
/*     */       } 
/*     */     } 
/*     */     
/* 757 */     for (var1 = 0; var1 < this.armorInventory.length; var1++) {
/*     */       
/* 759 */       if (this.armorInventory[var1] != null) {
/*     */         
/* 761 */         this.player.func_146097_a(this.armorInventory[var1], true, false);
/* 762 */         this.armorInventory[var1] = null;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void markDirty() {
/* 773 */     this.inventoryChanged = true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setItemStack(ItemStack p_70437_1_) {
/* 781 */     this.itemStack = p_70437_1_;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack getItemStack() {
/* 789 */     return this.itemStack;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isUseableByPlayer(EntityPlayer playerIn) {
/* 797 */     return this.player.isDead ? false : ((playerIn.getDistanceSqToEntity((Entity)this.player) <= 64.0D));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasItemStack(ItemStack p_70431_1_) {
/*     */     int var2;
/* 807 */     for (var2 = 0; var2 < this.armorInventory.length; var2++) {
/*     */       
/* 809 */       if (this.armorInventory[var2] != null && this.armorInventory[var2].isItemEqual(p_70431_1_))
/*     */       {
/* 811 */         return true;
/*     */       }
/*     */     } 
/*     */     
/* 815 */     for (var2 = 0; var2 < this.mainInventory.length; var2++) {
/*     */       
/* 817 */       if (this.mainInventory[var2] != null && this.mainInventory[var2].isItemEqual(p_70431_1_))
/*     */       {
/* 819 */         return true;
/*     */       }
/*     */     } 
/*     */     
/* 823 */     return false;
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
/* 835 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void copyInventory(InventoryPlayer p_70455_1_) {
/*     */     int var2;
/* 845 */     for (var2 = 0; var2 < this.mainInventory.length; var2++)
/*     */     {
/* 847 */       this.mainInventory[var2] = ItemStack.copyItemStack(p_70455_1_.mainInventory[var2]);
/*     */     }
/*     */     
/* 850 */     for (var2 = 0; var2 < this.armorInventory.length; var2++)
/*     */     {
/* 852 */       this.armorInventory[var2] = ItemStack.copyItemStack(p_70455_1_.armorInventory[var2]);
/*     */     }
/*     */     
/* 855 */     this.currentItem = p_70455_1_.currentItem;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getField(int id) {
/* 860 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setField(int id, int value) {}
/*     */   
/*     */   public int getFieldCount() {
/* 867 */     return 0;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void clearInventory() {
/*     */     int var1;
/* 874 */     for (var1 = 0; var1 < this.mainInventory.length; var1++)
/*     */     {
/* 876 */       this.mainInventory[var1] = null;
/*     */     }
/*     */     
/* 879 */     for (var1 = 0; var1 < this.armorInventory.length; var1++)
/*     */     {
/* 881 */       this.armorInventory[var1] = null;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\entity\player\InventoryPlayer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */