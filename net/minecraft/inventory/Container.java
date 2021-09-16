/*     */ package net.minecraft.inventory;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import com.google.common.collect.Sets;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Set;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.player.InventoryPlayer;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.MathHelper;
/*     */ 
/*     */ 
/*     */ public abstract class Container
/*     */ {
/*  19 */   public List inventoryItemStacks = Lists.newArrayList();
/*     */ 
/*     */   
/*  22 */   public List inventorySlots = Lists.newArrayList();
/*     */ 
/*     */   
/*     */   public int windowId;
/*     */   
/*     */   private short transactionID;
/*     */   
/*  29 */   private int dragMode = -1;
/*     */ 
/*     */   
/*     */   private int dragEvent;
/*     */ 
/*     */   
/*  35 */   private final Set dragSlots = Sets.newHashSet();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  40 */   protected List crafters = Lists.newArrayList();
/*  41 */   private Set playerList = Sets.newHashSet();
/*     */ 
/*     */   
/*     */   private static final String __OBFID = "CL_00001730";
/*     */ 
/*     */ 
/*     */   
/*     */   protected Slot addSlotToContainer(Slot p_75146_1_) {
/*  49 */     p_75146_1_.slotNumber = this.inventorySlots.size();
/*  50 */     this.inventorySlots.add(p_75146_1_);
/*  51 */     this.inventoryItemStacks.add(null);
/*  52 */     return p_75146_1_;
/*     */   }
/*     */ 
/*     */   
/*     */   public void onCraftGuiOpened(ICrafting p_75132_1_) {
/*  57 */     if (this.crafters.contains(p_75132_1_))
/*     */     {
/*  59 */       throw new IllegalArgumentException("Listener already listening");
/*     */     }
/*     */ 
/*     */     
/*  63 */     this.crafters.add(p_75132_1_);
/*  64 */     p_75132_1_.updateCraftingInventory(this, getInventory());
/*  65 */     detectAndSendChanges();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void removeCraftingFromCrafters(ICrafting p_82847_1_) {
/*  74 */     this.crafters.remove(p_82847_1_);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List getInventory() {
/*  82 */     ArrayList<ItemStack> var1 = Lists.newArrayList();
/*     */     
/*  84 */     for (int var2 = 0; var2 < this.inventorySlots.size(); var2++)
/*     */     {
/*  86 */       var1.add(((Slot)this.inventorySlots.get(var2)).getStack());
/*     */     }
/*     */     
/*  89 */     return var1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void detectAndSendChanges() {
/*  97 */     for (int var1 = 0; var1 < this.inventorySlots.size(); var1++) {
/*     */       
/*  99 */       ItemStack var2 = ((Slot)this.inventorySlots.get(var1)).getStack();
/* 100 */       ItemStack var3 = this.inventoryItemStacks.get(var1);
/*     */       
/* 102 */       if (!ItemStack.areItemStacksEqual(var3, var2)) {
/*     */         
/* 104 */         var3 = (var2 == null) ? null : var2.copy();
/* 105 */         this.inventoryItemStacks.set(var1, var3);
/*     */         
/* 107 */         for (int var4 = 0; var4 < this.crafters.size(); var4++)
/*     */         {
/* 109 */           ((ICrafting)this.crafters.get(var4)).sendSlotContents(this, var1, var3);
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean enchantItem(EntityPlayer playerIn, int id) {
/* 120 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public Slot getSlotFromInventory(IInventory p_75147_1_, int p_75147_2_) {
/* 125 */     for (int var3 = 0; var3 < this.inventorySlots.size(); var3++) {
/*     */       
/* 127 */       Slot var4 = this.inventorySlots.get(var3);
/*     */       
/* 129 */       if (var4.isHere(p_75147_1_, p_75147_2_))
/*     */       {
/* 131 */         return var4;
/*     */       }
/*     */     } 
/*     */     
/* 135 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public Slot getSlot(int p_75139_1_) {
/* 140 */     return this.inventorySlots.get(p_75139_1_);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack transferStackInSlot(EntityPlayer playerIn, int index) {
/* 148 */     Slot var3 = this.inventorySlots.get(index);
/* 149 */     return (var3 != null) ? var3.getStack() : null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack slotClick(int slotId, int clickedButton, int mode, EntityPlayer playerIn) {
/* 158 */     ItemStack var5 = null;
/* 159 */     InventoryPlayer var6 = playerIn.inventory;
/*     */ 
/*     */ 
/*     */     
/* 163 */     if (mode == 5) {
/*     */       
/* 165 */       int var7 = this.dragEvent;
/* 166 */       this.dragEvent = getDragEvent(clickedButton);
/*     */       
/* 168 */       if ((var7 != 1 || this.dragEvent != 2) && var7 != this.dragEvent) {
/*     */         
/* 170 */         resetDrag();
/*     */       }
/* 172 */       else if (var6.getItemStack() == null) {
/*     */         
/* 174 */         resetDrag();
/*     */       }
/* 176 */       else if (this.dragEvent == 0) {
/*     */         
/* 178 */         this.dragMode = extractDragMode(clickedButton);
/*     */         
/* 180 */         if (func_180610_a(this.dragMode, playerIn))
/*     */         {
/* 182 */           this.dragEvent = 1;
/* 183 */           this.dragSlots.clear();
/*     */         }
/*     */         else
/*     */         {
/* 187 */           resetDrag();
/*     */         }
/*     */       
/* 190 */       } else if (this.dragEvent == 1) {
/*     */         
/* 192 */         Slot var8 = this.inventorySlots.get(slotId);
/*     */         
/* 194 */         if (var8 != null && canAddItemToSlot(var8, var6.getItemStack(), true) && var8.isItemValid(var6.getItemStack()) && (var6.getItemStack()).stackSize > this.dragSlots.size() && canDragIntoSlot(var8))
/*     */         {
/* 196 */           this.dragSlots.add(var8);
/*     */         }
/*     */       }
/* 199 */       else if (this.dragEvent == 2) {
/*     */         
/* 201 */         if (!this.dragSlots.isEmpty()) {
/*     */           
/* 203 */           ItemStack var17 = var6.getItemStack().copy();
/* 204 */           int var9 = (var6.getItemStack()).stackSize;
/* 205 */           Iterator<Slot> var10 = this.dragSlots.iterator();
/*     */           
/* 207 */           while (var10.hasNext()) {
/*     */             
/* 209 */             Slot var11 = var10.next();
/*     */             
/* 211 */             if (var11 != null && canAddItemToSlot(var11, var6.getItemStack(), true) && var11.isItemValid(var6.getItemStack()) && (var6.getItemStack()).stackSize >= this.dragSlots.size() && canDragIntoSlot(var11)) {
/*     */               
/* 213 */               ItemStack var12 = var17.copy();
/* 214 */               int var13 = var11.getHasStack() ? (var11.getStack()).stackSize : 0;
/* 215 */               computeStackSize(this.dragSlots, this.dragMode, var12, var13);
/*     */               
/* 217 */               if (var12.stackSize > var12.getMaxStackSize())
/*     */               {
/* 219 */                 var12.stackSize = var12.getMaxStackSize();
/*     */               }
/*     */               
/* 222 */               if (var12.stackSize > var11.func_178170_b(var12))
/*     */               {
/* 224 */                 var12.stackSize = var11.func_178170_b(var12);
/*     */               }
/*     */               
/* 227 */               var9 -= var12.stackSize - var13;
/* 228 */               var11.putStack(var12);
/*     */             } 
/*     */           } 
/*     */           
/* 232 */           var17.stackSize = var9;
/*     */           
/* 234 */           if (var17.stackSize <= 0)
/*     */           {
/* 236 */             var17 = null;
/*     */           }
/*     */           
/* 239 */           var6.setItemStack(var17);
/*     */         } 
/*     */         
/* 242 */         resetDrag();
/*     */       }
/*     */       else {
/*     */         
/* 246 */         resetDrag();
/*     */       }
/*     */     
/* 249 */     } else if (this.dragEvent != 0) {
/*     */       
/* 251 */       resetDrag();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     }
/* 259 */     else if ((mode == 0 || mode == 1) && (clickedButton == 0 || clickedButton == 1)) {
/*     */       
/* 261 */       if (slotId == -999) {
/*     */         
/* 263 */         if (var6.getItemStack() != null) {
/*     */           
/* 265 */           if (clickedButton == 0) {
/*     */             
/* 267 */             playerIn.dropPlayerItemWithRandomChoice(var6.getItemStack(), true);
/* 268 */             var6.setItemStack(null);
/*     */           } 
/*     */           
/* 271 */           if (clickedButton == 1)
/*     */           {
/* 273 */             playerIn.dropPlayerItemWithRandomChoice(var6.getItemStack().splitStack(1), true);
/*     */             
/* 275 */             if ((var6.getItemStack()).stackSize == 0)
/*     */             {
/* 277 */               var6.setItemStack(null);
/*     */             }
/*     */           }
/*     */         
/*     */         } 
/* 282 */       } else if (mode == 1) {
/*     */         
/* 284 */         if (slotId < 0)
/*     */         {
/* 286 */           return null;
/*     */         }
/*     */         
/* 289 */         Slot var16 = this.inventorySlots.get(slotId);
/*     */         
/* 291 */         if (var16 != null && var16.canTakeStack(playerIn)) {
/*     */           
/* 293 */           ItemStack var17 = transferStackInSlot(playerIn, slotId);
/*     */           
/* 295 */           if (var17 != null)
/*     */           {
/* 297 */             Item var19 = var17.getItem();
/* 298 */             var5 = var17.copy();
/*     */             
/* 300 */             if (var16.getStack() != null && var16.getStack().getItem() == var19)
/*     */             {
/* 302 */               retrySlotClick(slotId, clickedButton, true, playerIn);
/*     */             }
/*     */           }
/*     */         
/*     */         } 
/*     */       } else {
/*     */         
/* 309 */         if (slotId < 0)
/*     */         {
/* 311 */           return null;
/*     */         }
/*     */         
/* 314 */         Slot var16 = this.inventorySlots.get(slotId);
/*     */         
/* 316 */         if (var16 != null)
/*     */         {
/* 318 */           ItemStack var17 = var16.getStack();
/* 319 */           ItemStack var20 = var6.getItemStack();
/*     */           
/* 321 */           if (var17 != null)
/*     */           {
/* 323 */             var5 = var17.copy();
/*     */           }
/*     */           
/* 326 */           if (var17 == null) {
/*     */             
/* 328 */             if (var20 != null && var16.isItemValid(var20))
/*     */             {
/* 330 */               int var21 = (clickedButton == 0) ? var20.stackSize : 1;
/*     */               
/* 332 */               if (var21 > var16.func_178170_b(var20))
/*     */               {
/* 334 */                 var21 = var16.func_178170_b(var20);
/*     */               }
/*     */               
/* 337 */               if (var20.stackSize >= var21)
/*     */               {
/* 339 */                 var16.putStack(var20.splitStack(var21));
/*     */               }
/*     */               
/* 342 */               if (var20.stackSize == 0)
/*     */               {
/* 344 */                 var6.setItemStack(null);
/*     */               }
/*     */             }
/*     */           
/* 348 */           } else if (var16.canTakeStack(playerIn)) {
/*     */             
/* 350 */             if (var20 == null) {
/*     */               
/* 352 */               int var21 = (clickedButton == 0) ? var17.stackSize : ((var17.stackSize + 1) / 2);
/* 353 */               ItemStack var23 = var16.decrStackSize(var21);
/* 354 */               var6.setItemStack(var23);
/*     */               
/* 356 */               if (var17.stackSize == 0)
/*     */               {
/* 358 */                 var16.putStack(null);
/*     */               }
/*     */               
/* 361 */               var16.onPickupFromSlot(playerIn, var6.getItemStack());
/*     */             }
/* 363 */             else if (var16.isItemValid(var20)) {
/*     */               
/* 365 */               if (var17.getItem() == var20.getItem() && var17.getMetadata() == var20.getMetadata() && ItemStack.areItemStackTagsEqual(var17, var20))
/*     */               {
/* 367 */                 int var21 = (clickedButton == 0) ? var20.stackSize : 1;
/*     */                 
/* 369 */                 if (var21 > var16.func_178170_b(var20) - var17.stackSize)
/*     */                 {
/* 371 */                   var21 = var16.func_178170_b(var20) - var17.stackSize;
/*     */                 }
/*     */                 
/* 374 */                 if (var21 > var20.getMaxStackSize() - var17.stackSize)
/*     */                 {
/* 376 */                   var21 = var20.getMaxStackSize() - var17.stackSize;
/*     */                 }
/*     */                 
/* 379 */                 var20.splitStack(var21);
/*     */                 
/* 381 */                 if (var20.stackSize == 0)
/*     */                 {
/* 383 */                   var6.setItemStack(null);
/*     */                 }
/*     */                 
/* 386 */                 var17.stackSize += var21;
/*     */               }
/* 388 */               else if (var20.stackSize <= var16.func_178170_b(var20))
/*     */               {
/* 390 */                 var16.putStack(var20);
/* 391 */                 var6.setItemStack(var17);
/*     */               }
/*     */             
/* 394 */             } else if (var17.getItem() == var20.getItem() && var20.getMaxStackSize() > 1 && (!var17.getHasSubtypes() || var17.getMetadata() == var20.getMetadata()) && ItemStack.areItemStackTagsEqual(var17, var20)) {
/*     */               
/* 396 */               int var21 = var17.stackSize;
/*     */               
/* 398 */               if (var21 > 0 && var21 + var20.stackSize <= var20.getMaxStackSize()) {
/*     */                 
/* 400 */                 var20.stackSize += var21;
/* 401 */                 var17 = var16.decrStackSize(var21);
/*     */                 
/* 403 */                 if (var17.stackSize == 0)
/*     */                 {
/* 405 */                   var16.putStack(null);
/*     */                 }
/*     */                 
/* 408 */                 var16.onPickupFromSlot(playerIn, var6.getItemStack());
/*     */               } 
/*     */             } 
/*     */           } 
/*     */           
/* 413 */           var16.onSlotChanged();
/*     */         }
/*     */       
/*     */       } 
/* 417 */     } else if (mode == 2 && clickedButton >= 0 && clickedButton < 9) {
/*     */       
/* 419 */       Slot var16 = this.inventorySlots.get(slotId);
/*     */       
/* 421 */       if (var16.canTakeStack(playerIn)) {
/*     */         int i;
/* 423 */         ItemStack var17 = var6.getStackInSlot(clickedButton);
/* 424 */         boolean var18 = !(var17 != null && (var16.inventory != var6 || !var16.isItemValid(var17)));
/* 425 */         int var21 = -1;
/*     */         
/* 427 */         if (!var18) {
/*     */           
/* 429 */           var21 = var6.getFirstEmptyStack();
/* 430 */           i = var18 | ((var21 > -1) ? 1 : 0);
/*     */         } 
/*     */         
/* 433 */         if (var16.getHasStack() && i != 0) {
/*     */           
/* 435 */           ItemStack var23 = var16.getStack();
/* 436 */           var6.setInventorySlotContents(clickedButton, var23.copy());
/*     */           
/* 438 */           if ((var16.inventory != var6 || !var16.isItemValid(var17)) && var17 != null) {
/*     */             
/* 440 */             if (var21 > -1)
/*     */             {
/* 442 */               var6.addItemStackToInventory(var17);
/* 443 */               var16.decrStackSize(var23.stackSize);
/* 444 */               var16.putStack(null);
/* 445 */               var16.onPickupFromSlot(playerIn, var23);
/*     */             }
/*     */           
/*     */           } else {
/*     */             
/* 450 */             var16.decrStackSize(var23.stackSize);
/* 451 */             var16.putStack(var17);
/* 452 */             var16.onPickupFromSlot(playerIn, var23);
/*     */           }
/*     */         
/* 455 */         } else if (!var16.getHasStack() && var17 != null && var16.isItemValid(var17)) {
/*     */           
/* 457 */           var6.setInventorySlotContents(clickedButton, null);
/* 458 */           var16.putStack(var17);
/*     */         }
/*     */       
/*     */       } 
/* 462 */     } else if (mode == 3 && playerIn.capabilities.isCreativeMode && var6.getItemStack() == null && slotId >= 0) {
/*     */       
/* 464 */       Slot var16 = this.inventorySlots.get(slotId);
/*     */       
/* 466 */       if (var16 != null && var16.getHasStack())
/*     */       {
/* 468 */         ItemStack var17 = var16.getStack().copy();
/* 469 */         var17.stackSize = var17.getMaxStackSize();
/* 470 */         var6.setItemStack(var17);
/*     */       }
/*     */     
/* 473 */     } else if (mode == 4 && var6.getItemStack() == null && slotId >= 0) {
/*     */       
/* 475 */       Slot var16 = this.inventorySlots.get(slotId);
/*     */       
/* 477 */       if (var16 != null && var16.getHasStack() && var16.canTakeStack(playerIn))
/*     */       {
/* 479 */         ItemStack var17 = var16.decrStackSize((clickedButton == 0) ? 1 : (var16.getStack()).stackSize);
/* 480 */         var16.onPickupFromSlot(playerIn, var17);
/* 481 */         playerIn.dropPlayerItemWithRandomChoice(var17, true);
/*     */       }
/*     */     
/* 484 */     } else if (mode == 6 && slotId >= 0) {
/*     */       
/* 486 */       Slot var16 = this.inventorySlots.get(slotId);
/* 487 */       ItemStack var17 = var6.getItemStack();
/*     */       
/* 489 */       if (var17 != null && (var16 == null || !var16.getHasStack() || !var16.canTakeStack(playerIn))) {
/*     */         
/* 491 */         int var9 = (clickedButton == 0) ? 0 : (this.inventorySlots.size() - 1);
/* 492 */         int var21 = (clickedButton == 0) ? 1 : -1;
/*     */         
/* 494 */         for (int var22 = 0; var22 < 2; var22++) {
/*     */           
/* 496 */           for (int var24 = var9; var24 >= 0 && var24 < this.inventorySlots.size() && var17.stackSize < var17.getMaxStackSize(); var24 += var21) {
/*     */             
/* 498 */             Slot var25 = this.inventorySlots.get(var24);
/*     */             
/* 500 */             if (var25.getHasStack() && canAddItemToSlot(var25, var17, true) && var25.canTakeStack(playerIn) && func_94530_a(var17, var25) && (var22 != 0 || (var25.getStack()).stackSize != var25.getStack().getMaxStackSize())) {
/*     */               
/* 502 */               int var14 = Math.min(var17.getMaxStackSize() - var17.stackSize, (var25.getStack()).stackSize);
/* 503 */               ItemStack var15 = var25.decrStackSize(var14);
/* 504 */               var17.stackSize += var14;
/*     */               
/* 506 */               if (var15.stackSize <= 0)
/*     */               {
/* 508 */                 var25.putStack(null);
/*     */               }
/*     */               
/* 511 */               var25.onPickupFromSlot(playerIn, var15);
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */       
/* 517 */       detectAndSendChanges();
/*     */     } 
/*     */ 
/*     */     
/* 521 */     return var5;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_94530_a(ItemStack p_94530_1_, Slot p_94530_2_) {
/* 526 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void retrySlotClick(int p_75133_1_, int p_75133_2_, boolean p_75133_3_, EntityPlayer p_75133_4_) {
/* 531 */     slotClick(p_75133_1_, p_75133_2_, 1, p_75133_4_);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onContainerClosed(EntityPlayer p_75134_1_) {
/* 539 */     InventoryPlayer var2 = p_75134_1_.inventory;
/*     */     
/* 541 */     if (var2.getItemStack() != null) {
/*     */       
/* 543 */       p_75134_1_.dropPlayerItemWithRandomChoice(var2.getItemStack(), false);
/* 544 */       var2.setItemStack(null);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onCraftMatrixChanged(IInventory p_75130_1_) {
/* 553 */     detectAndSendChanges();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void putStackInSlot(int p_75141_1_, ItemStack p_75141_2_) {
/* 561 */     getSlot(p_75141_1_).putStack(p_75141_2_);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void putStacksInSlots(ItemStack[] p_75131_1_) {
/* 569 */     for (int var2 = 0; var2 < p_75131_1_.length; var2++)
/*     */     {
/* 571 */       getSlot(var2).putStack(p_75131_1_[var2]);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void updateProgressBar(int p_75137_1_, int p_75137_2_) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public short getNextTransactionID(InventoryPlayer p_75136_1_) {
/* 582 */     this.transactionID = (short)(this.transactionID + 1);
/* 583 */     return this.transactionID;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean getCanCraft(EntityPlayer p_75129_1_) {
/* 591 */     return !this.playerList.contains(p_75129_1_);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setCanCraft(EntityPlayer p_75128_1_, boolean p_75128_2_) {
/* 599 */     if (p_75128_2_) {
/*     */       
/* 601 */       this.playerList.remove(p_75128_1_);
/*     */     }
/*     */     else {
/*     */       
/* 605 */       this.playerList.add(p_75128_1_);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public abstract boolean canInteractWith(EntityPlayer paramEntityPlayer);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean mergeItemStack(ItemStack stack, int startIndex, int endIndex, boolean useEndIndex) {
/* 618 */     boolean var5 = false;
/* 619 */     int var6 = startIndex;
/*     */     
/* 621 */     if (useEndIndex)
/*     */     {
/* 623 */       var6 = endIndex - 1;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 629 */     if (stack.isStackable())
/*     */     {
/* 631 */       while (stack.stackSize > 0 && ((!useEndIndex && var6 < endIndex) || (useEndIndex && var6 >= startIndex))) {
/*     */         
/* 633 */         Slot var7 = this.inventorySlots.get(var6);
/* 634 */         ItemStack var8 = var7.getStack();
/*     */         
/* 636 */         if (var8 != null && var8.getItem() == stack.getItem() && (!stack.getHasSubtypes() || stack.getMetadata() == var8.getMetadata()) && ItemStack.areItemStackTagsEqual(stack, var8)) {
/*     */           
/* 638 */           int var9 = var8.stackSize + stack.stackSize;
/*     */           
/* 640 */           if (var9 <= stack.getMaxStackSize()) {
/*     */             
/* 642 */             stack.stackSize = 0;
/* 643 */             var8.stackSize = var9;
/* 644 */             var7.onSlotChanged();
/* 645 */             var5 = true;
/*     */           }
/* 647 */           else if (var8.stackSize < stack.getMaxStackSize()) {
/*     */             
/* 649 */             stack.stackSize -= stack.getMaxStackSize() - var8.stackSize;
/* 650 */             var8.stackSize = stack.getMaxStackSize();
/* 651 */             var7.onSlotChanged();
/* 652 */             var5 = true;
/*     */           } 
/*     */         } 
/*     */         
/* 656 */         if (useEndIndex) {
/*     */           
/* 658 */           var6--;
/*     */           
/*     */           continue;
/*     */         } 
/* 662 */         var6++;
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/* 667 */     if (stack.stackSize > 0) {
/*     */       
/* 669 */       if (useEndIndex) {
/*     */         
/* 671 */         var6 = endIndex - 1;
/*     */       }
/*     */       else {
/*     */         
/* 675 */         var6 = startIndex;
/*     */       } 
/*     */       
/* 678 */       while ((!useEndIndex && var6 < endIndex) || (useEndIndex && var6 >= startIndex)) {
/*     */         
/* 680 */         Slot var7 = this.inventorySlots.get(var6);
/* 681 */         ItemStack var8 = var7.getStack();
/*     */         
/* 683 */         if (var8 == null) {
/*     */           
/* 685 */           var7.putStack(stack.copy());
/* 686 */           var7.onSlotChanged();
/* 687 */           stack.stackSize = 0;
/* 688 */           var5 = true;
/*     */           
/*     */           break;
/*     */         } 
/* 692 */         if (useEndIndex) {
/*     */           
/* 694 */           var6--;
/*     */           
/*     */           continue;
/*     */         } 
/* 698 */         var6++;
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 703 */     return var5;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int extractDragMode(int p_94529_0_) {
/* 711 */     return p_94529_0_ >> 2 & 0x3;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int getDragEvent(int p_94532_0_) {
/* 719 */     return p_94532_0_ & 0x3;
/*     */   }
/*     */ 
/*     */   
/*     */   public static int func_94534_d(int p_94534_0_, int p_94534_1_) {
/* 724 */     return p_94534_0_ & 0x3 | (p_94534_1_ & 0x3) << 2;
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean func_180610_a(int p_180610_0_, EntityPlayer p_180610_1_) {
/* 729 */     return (p_180610_0_ == 0) ? true : ((p_180610_0_ == 1) ? true : ((p_180610_0_ == 2 && p_180610_1_.capabilities.isCreativeMode)));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void resetDrag() {
/* 737 */     this.dragEvent = 0;
/* 738 */     this.dragSlots.clear();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean canAddItemToSlot(Slot slotIn, ItemStack stack, boolean stackSizeMatters) {
/*     */     int i;
/* 746 */     boolean var3 = !(slotIn != null && slotIn.getHasStack());
/*     */     
/* 748 */     if (slotIn != null && slotIn.getHasStack() && stack != null && stack.isItemEqual(slotIn.getStack()) && ItemStack.areItemStackTagsEqual(slotIn.getStack(), stack)) {
/*     */       
/* 750 */       int var10002 = stackSizeMatters ? 0 : stack.stackSize;
/* 751 */       i = var3 | (((slotIn.getStack()).stackSize + var10002 <= stack.getMaxStackSize()) ? 1 : 0);
/*     */     } 
/*     */     
/* 754 */     return i;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void computeStackSize(Set p_94525_0_, int p_94525_1_, ItemStack p_94525_2_, int p_94525_3_) {
/* 763 */     switch (p_94525_1_) {
/*     */       
/*     */       case 0:
/* 766 */         p_94525_2_.stackSize = MathHelper.floor_float(p_94525_2_.stackSize / p_94525_0_.size());
/*     */         break;
/*     */       
/*     */       case 1:
/* 770 */         p_94525_2_.stackSize = 1;
/*     */         break;
/*     */       
/*     */       case 2:
/* 774 */         p_94525_2_.stackSize = p_94525_2_.getItem().getItemStackLimit(); break;
/* 775 */     }  p_94525_2_.stackSize += 
/*     */       
/* 777 */       p_94525_3_;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canDragIntoSlot(Slot p_94531_1_) {
/* 786 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int calcRedstoneFromInventory(TileEntity te) {
/* 794 */     return (te instanceof IInventory) ? calcRedstoneFromInventory((IInventory)te) : 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public static int calcRedstoneFromInventory(IInventory inv) {
/* 799 */     if (inv == null)
/*     */     {
/* 801 */       return 0;
/*     */     }
/*     */ 
/*     */     
/* 805 */     int var1 = 0;
/* 806 */     float var2 = 0.0F;
/*     */     
/* 808 */     for (int var3 = 0; var3 < inv.getSizeInventory(); var3++) {
/*     */       
/* 810 */       ItemStack var4 = inv.getStackInSlot(var3);
/*     */       
/* 812 */       if (var4 != null) {
/*     */         
/* 814 */         var2 += var4.stackSize / Math.min(inv.getInventoryStackLimit(), var4.getMaxStackSize());
/* 815 */         var1++;
/*     */       } 
/*     */     } 
/*     */     
/* 819 */     var2 /= inv.getSizeInventory();
/* 820 */     return MathHelper.floor_float(var2 * 14.0F) + ((var1 > 0) ? 1 : 0);
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\inventory\Container.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */