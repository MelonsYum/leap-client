/*     */ package net.minecraft.inventory;
/*     */ 
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import net.minecraft.block.BlockAnvil;
/*     */ import net.minecraft.block.properties.IProperty;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.enchantment.Enchantment;
/*     */ import net.minecraft.enchantment.EnchantmentHelper;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.player.InventoryPlayer;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.world.World;
/*     */ import org.apache.commons.lang3.StringUtils;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ 
/*     */ public class ContainerRepair extends Container {
/*  22 */   private static final Logger logger = LogManager.getLogger();
/*     */ 
/*     */   
/*     */   private IInventory outputSlot;
/*     */ 
/*     */   
/*     */   private IInventory inputSlots;
/*     */ 
/*     */   
/*     */   private World theWorld;
/*     */   
/*     */   private BlockPos field_178156_j;
/*     */   
/*     */   public int maximumCost;
/*     */   
/*     */   private int materialCost;
/*     */   
/*     */   private String repairedItemName;
/*     */   
/*     */   private final EntityPlayer thePlayer;
/*     */   
/*     */   private static final String __OBFID = "CL_00001732";
/*     */ 
/*     */   
/*     */   public ContainerRepair(InventoryPlayer p_i45806_1_, World worldIn, EntityPlayer p_i45806_3_) {
/*  47 */     this(p_i45806_1_, worldIn, BlockPos.ORIGIN, p_i45806_3_);
/*     */   }
/*     */ 
/*     */   
/*     */   public ContainerRepair(InventoryPlayer p_i45807_1_, final World worldIn, final BlockPos p_i45807_3_, EntityPlayer p_i45807_4_) {
/*  52 */     this.outputSlot = new InventoryCraftResult();
/*  53 */     this.inputSlots = new InventoryBasic("Repair", true, 2)
/*     */       {
/*     */         private static final String __OBFID = "CL_00001733";
/*     */         
/*     */         public void markDirty() {
/*  58 */           super.markDirty();
/*  59 */           ContainerRepair.this.onCraftMatrixChanged(this);
/*     */         }
/*     */       };
/*  62 */     this.field_178156_j = p_i45807_3_;
/*  63 */     this.theWorld = worldIn;
/*  64 */     this.thePlayer = p_i45807_4_;
/*  65 */     addSlotToContainer(new Slot(this.inputSlots, 0, 27, 47));
/*  66 */     addSlotToContainer(new Slot(this.inputSlots, 1, 76, 47));
/*  67 */     addSlotToContainer(new Slot(this.outputSlot, 2, 134, 47)
/*     */         {
/*     */           private static final String __OBFID = "CL_00001734";
/*     */           
/*     */           public boolean isItemValid(ItemStack stack) {
/*  72 */             return false;
/*     */           }
/*     */           
/*     */           public boolean canTakeStack(EntityPlayer p_82869_1_) {
/*  76 */             return ((p_82869_1_.capabilities.isCreativeMode || p_82869_1_.experienceLevel >= ContainerRepair.this.maximumCost) && ContainerRepair.this.maximumCost > 0 && getHasStack());
/*     */           }
/*     */           
/*     */           public void onPickupFromSlot(EntityPlayer playerIn, ItemStack stack) {
/*  80 */             if (!playerIn.capabilities.isCreativeMode)
/*     */             {
/*  82 */               playerIn.addExperienceLevel(-ContainerRepair.this.maximumCost);
/*     */             }
/*     */             
/*  85 */             ContainerRepair.this.inputSlots.setInventorySlotContents(0, null);
/*     */             
/*  87 */             if (ContainerRepair.this.materialCost > 0) {
/*     */               
/*  89 */               ItemStack var3 = ContainerRepair.this.inputSlots.getStackInSlot(1);
/*     */               
/*  91 */               if (var3 != null && var3.stackSize > ContainerRepair.this.materialCost)
/*     */               {
/*  93 */                 var3.stackSize -= ContainerRepair.this.materialCost;
/*  94 */                 ContainerRepair.this.inputSlots.setInventorySlotContents(1, var3);
/*     */               }
/*     */               else
/*     */               {
/*  98 */                 ContainerRepair.this.inputSlots.setInventorySlotContents(1, null);
/*     */               }
/*     */             
/*     */             } else {
/*     */               
/* 103 */               ContainerRepair.this.inputSlots.setInventorySlotContents(1, null);
/*     */             } 
/*     */             
/* 106 */             ContainerRepair.this.maximumCost = 0;
/* 107 */             IBlockState var5 = worldIn.getBlockState(p_i45807_3_);
/*     */             
/* 109 */             if (!playerIn.capabilities.isCreativeMode && !worldIn.isRemote && var5.getBlock() == Blocks.anvil && playerIn.getRNG().nextFloat() < 0.12F) {
/*     */               
/* 111 */               int var4 = ((Integer)var5.getValue((IProperty)BlockAnvil.DAMAGE)).intValue();
/* 112 */               var4++;
/*     */               
/* 114 */               if (var4 > 2)
/*     */               {
/* 116 */                 worldIn.setBlockToAir(p_i45807_3_);
/* 117 */                 worldIn.playAuxSFX(1020, p_i45807_3_, 0);
/*     */               }
/*     */               else
/*     */               {
/* 121 */                 worldIn.setBlockState(p_i45807_3_, var5.withProperty((IProperty)BlockAnvil.DAMAGE, Integer.valueOf(var4)), 2);
/* 122 */                 worldIn.playAuxSFX(1021, p_i45807_3_, 0);
/*     */               }
/*     */             
/* 125 */             } else if (!worldIn.isRemote) {
/*     */               
/* 127 */               worldIn.playAuxSFX(1021, p_i45807_3_, 0);
/*     */             } 
/*     */           }
/*     */         });
/*     */     
/*     */     int var5;
/* 133 */     for (var5 = 0; var5 < 3; var5++) {
/*     */       
/* 135 */       for (int var6 = 0; var6 < 9; var6++)
/*     */       {
/* 137 */         addSlotToContainer(new Slot((IInventory)p_i45807_1_, var6 + var5 * 9 + 9, 8 + var6 * 18, 84 + var5 * 18));
/*     */       }
/*     */     } 
/*     */     
/* 141 */     for (var5 = 0; var5 < 9; var5++)
/*     */     {
/* 143 */       addSlotToContainer(new Slot((IInventory)p_i45807_1_, var5, 8 + var5 * 18, 142));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onCraftMatrixChanged(IInventory p_75130_1_) {
/* 152 */     super.onCraftMatrixChanged(p_75130_1_);
/*     */     
/* 154 */     if (p_75130_1_ == this.inputSlots)
/*     */     {
/* 156 */       updateRepairOutput();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void updateRepairOutput() {
/* 165 */     boolean var1 = false;
/* 166 */     boolean var2 = true;
/* 167 */     boolean var3 = true;
/* 168 */     boolean var4 = true;
/* 169 */     boolean var5 = true;
/* 170 */     boolean var6 = true;
/* 171 */     boolean var7 = true;
/* 172 */     ItemStack var8 = this.inputSlots.getStackInSlot(0);
/* 173 */     this.maximumCost = 1;
/* 174 */     int var9 = 0;
/* 175 */     byte var10 = 0;
/* 176 */     byte var11 = 0;
/*     */     
/* 178 */     if (var8 == null) {
/*     */       
/* 180 */       this.outputSlot.setInventorySlotContents(0, null);
/* 181 */       this.maximumCost = 0;
/*     */     }
/*     */     else {
/*     */       
/* 185 */       ItemStack var12 = var8.copy();
/* 186 */       ItemStack var13 = this.inputSlots.getStackInSlot(1);
/* 187 */       Map<Integer, Integer> var14 = EnchantmentHelper.getEnchantments(var12);
/* 188 */       boolean var15 = false;
/* 189 */       int var25 = var10 + var8.getRepairCost() + ((var13 == null) ? 0 : var13.getRepairCost());
/* 190 */       this.materialCost = 0;
/*     */ 
/*     */       
/* 193 */       if (var13 != null) {
/*     */         
/* 195 */         var15 = (var13.getItem() == Items.enchanted_book && Items.enchanted_book.func_92110_g(var13).tagCount() > 0);
/*     */ 
/*     */ 
/*     */         
/* 199 */         if (var12.isItemStackDamageable() && var12.getItem().getIsRepairable(var8, var13)) {
/*     */           
/* 201 */           int var16 = Math.min(var12.getItemDamage(), var12.getMaxDamage() / 4);
/*     */           
/* 203 */           if (var16 <= 0) {
/*     */             
/* 205 */             this.outputSlot.setInventorySlotContents(0, null);
/* 206 */             this.maximumCost = 0;
/*     */             return;
/*     */           } 
/*     */           int var17;
/* 210 */           for (var17 = 0; var16 > 0 && var17 < var13.stackSize; var17++) {
/*     */             
/* 212 */             int var18 = var12.getItemDamage() - var16;
/* 213 */             var12.setItemDamage(var18);
/* 214 */             var9++;
/* 215 */             var16 = Math.min(var12.getItemDamage(), var12.getMaxDamage() / 4);
/*     */           } 
/*     */           
/* 218 */           this.materialCost = var17;
/*     */         }
/*     */         else {
/*     */           
/* 222 */           if (!var15 && (var12.getItem() != var13.getItem() || !var12.isItemStackDamageable())) {
/*     */             
/* 224 */             this.outputSlot.setInventorySlotContents(0, null);
/* 225 */             this.maximumCost = 0;
/*     */ 
/*     */             
/*     */             return;
/*     */           } 
/*     */           
/* 231 */           if (var12.isItemStackDamageable() && !var15) {
/*     */             
/* 233 */             int var16 = var8.getMaxDamage() - var8.getItemDamage();
/* 234 */             int var17 = var13.getMaxDamage() - var13.getItemDamage();
/* 235 */             int var18 = var17 + var12.getMaxDamage() * 12 / 100;
/* 236 */             int var19 = var16 + var18;
/* 237 */             int var20 = var12.getMaxDamage() - var19;
/*     */             
/* 239 */             if (var20 < 0)
/*     */             {
/* 241 */               var20 = 0;
/*     */             }
/*     */             
/* 244 */             if (var20 < var12.getMetadata()) {
/*     */               
/* 246 */               var12.setItemDamage(var20);
/* 247 */               var9 += 2;
/*     */             } 
/*     */           } 
/*     */           
/* 251 */           Map var26 = EnchantmentHelper.getEnchantments(var13);
/* 252 */           Iterator<Integer> var27 = var26.keySet().iterator();
/*     */           
/* 254 */           while (var27.hasNext()) {
/*     */             
/* 256 */             int var18 = ((Integer)var27.next()).intValue();
/* 257 */             Enchantment var28 = Enchantment.func_180306_c(var18);
/*     */             
/* 259 */             if (var28 != null) {
/*     */               
/* 261 */               int var10000, var20 = var14.containsKey(Integer.valueOf(var18)) ? ((Integer)var14.get(Integer.valueOf(var18))).intValue() : 0;
/* 262 */               int var21 = ((Integer)var26.get(Integer.valueOf(var18))).intValue();
/*     */ 
/*     */               
/* 265 */               if (var20 == var21) {
/*     */ 
/*     */                 
/* 268 */                 var10000 = ++var21;
/*     */               }
/*     */               else {
/*     */                 
/* 272 */                 var10000 = Math.max(var21, var20);
/*     */               } 
/*     */               
/* 275 */               var21 = var10000;
/* 276 */               boolean var22 = var28.canApply(var8);
/*     */               
/* 278 */               if (this.thePlayer.capabilities.isCreativeMode || var8.getItem() == Items.enchanted_book)
/*     */               {
/* 280 */                 var22 = true;
/*     */               }
/*     */               
/* 283 */               Iterator<Integer> var23 = var14.keySet().iterator();
/*     */               
/* 285 */               while (var23.hasNext()) {
/*     */                 
/* 287 */                 int var24 = ((Integer)var23.next()).intValue();
/*     */                 
/* 289 */                 if (var24 != var18 && !var28.canApplyTogether(Enchantment.func_180306_c(var24))) {
/*     */                   
/* 291 */                   var22 = false;
/* 292 */                   var9++;
/*     */                 } 
/*     */               } 
/*     */               
/* 296 */               if (var22) {
/*     */                 
/* 298 */                 if (var21 > var28.getMaxLevel())
/*     */                 {
/* 300 */                   var21 = var28.getMaxLevel();
/*     */                 }
/*     */                 
/* 303 */                 var14.put(Integer.valueOf(var18), Integer.valueOf(var21));
/* 304 */                 int var29 = 0;
/*     */                 
/* 306 */                 switch (var28.getWeight()) {
/*     */                   
/*     */                   case 1:
/* 309 */                     var29 = 8;
/*     */                     break;
/*     */                   
/*     */                   case 2:
/* 313 */                     var29 = 4;
/*     */                     break;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */                   
/*     */                   case 5:
/* 325 */                     var29 = 2;
/*     */                     break;
/*     */                   
/*     */                   case 10:
/* 329 */                     var29 = 1;
/*     */                     break;
/*     */                 } 
/* 332 */                 if (var15)
/*     */                 {
/* 334 */                   var29 = Math.max(1, var29 / 2);
/*     */                 }
/*     */                 
/* 337 */                 var9 += var29 * var21;
/*     */               } 
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */       
/* 344 */       if (StringUtils.isBlank(this.repairedItemName)) {
/*     */         
/* 346 */         if (var8.hasDisplayName())
/*     */         {
/* 348 */           var11 = 1;
/* 349 */           var9 += var11;
/* 350 */           var12.clearCustomName();
/*     */         }
/*     */       
/* 353 */       } else if (!this.repairedItemName.equals(var8.getDisplayName())) {
/*     */         
/* 355 */         var11 = 1;
/* 356 */         var9 += var11;
/* 357 */         var12.setStackDisplayName(this.repairedItemName);
/*     */       } 
/*     */       
/* 360 */       this.maximumCost = var25 + var9;
/*     */       
/* 362 */       if (var9 <= 0)
/*     */       {
/* 364 */         var12 = null;
/*     */       }
/*     */       
/* 367 */       if (var11 == var9 && var11 > 0 && this.maximumCost >= 40)
/*     */       {
/* 369 */         this.maximumCost = 39;
/*     */       }
/*     */       
/* 372 */       if (this.maximumCost >= 40 && !this.thePlayer.capabilities.isCreativeMode)
/*     */       {
/* 374 */         var12 = null;
/*     */       }
/*     */       
/* 377 */       if (var12 != null) {
/*     */         
/* 379 */         int var16 = var12.getRepairCost();
/*     */         
/* 381 */         if (var13 != null && var16 < var13.getRepairCost())
/*     */         {
/* 383 */           var16 = var13.getRepairCost();
/*     */         }
/*     */         
/* 386 */         var16 = var16 * 2 + 1;
/* 387 */         var12.setRepairCost(var16);
/* 388 */         EnchantmentHelper.setEnchantments(var14, var12);
/*     */       } 
/*     */       
/* 391 */       this.outputSlot.setInventorySlotContents(0, var12);
/* 392 */       detectAndSendChanges();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void onCraftGuiOpened(ICrafting p_75132_1_) {
/* 398 */     super.onCraftGuiOpened(p_75132_1_);
/* 399 */     p_75132_1_.sendProgressBarUpdate(this, 0, this.maximumCost);
/*     */   }
/*     */ 
/*     */   
/*     */   public void updateProgressBar(int p_75137_1_, int p_75137_2_) {
/* 404 */     if (p_75137_1_ == 0)
/*     */     {
/* 406 */       this.maximumCost = p_75137_2_;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onContainerClosed(EntityPlayer p_75134_1_) {
/* 415 */     super.onContainerClosed(p_75134_1_);
/*     */     
/* 417 */     if (!this.theWorld.isRemote)
/*     */     {
/* 419 */       for (int var2 = 0; var2 < this.inputSlots.getSizeInventory(); var2++) {
/*     */         
/* 421 */         ItemStack var3 = this.inputSlots.getStackInSlotOnClosing(var2);
/*     */         
/* 423 */         if (var3 != null)
/*     */         {
/* 425 */           p_75134_1_.dropPlayerItemWithRandomChoice(var3, false);
/*     */         }
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canInteractWith(EntityPlayer playerIn) {
/* 433 */     return (this.theWorld.getBlockState(this.field_178156_j).getBlock() != Blocks.anvil) ? false : ((playerIn.getDistanceSq(this.field_178156_j.getX() + 0.5D, this.field_178156_j.getY() + 0.5D, this.field_178156_j.getZ() + 0.5D) <= 64.0D));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack transferStackInSlot(EntityPlayer playerIn, int index) {
/* 441 */     ItemStack var3 = null;
/* 442 */     Slot var4 = this.inventorySlots.get(index);
/*     */     
/* 444 */     if (var4 != null && var4.getHasStack()) {
/*     */       
/* 446 */       ItemStack var5 = var4.getStack();
/* 447 */       var3 = var5.copy();
/*     */       
/* 449 */       if (index == 2) {
/*     */         
/* 451 */         if (!mergeItemStack(var5, 3, 39, true))
/*     */         {
/* 453 */           return null;
/*     */         }
/*     */         
/* 456 */         var4.onSlotChange(var5, var3);
/*     */       }
/* 458 */       else if (index != 0 && index != 1) {
/*     */         
/* 460 */         if (index >= 3 && index < 39 && !mergeItemStack(var5, 0, 2, false))
/*     */         {
/* 462 */           return null;
/*     */         }
/*     */       }
/* 465 */       else if (!mergeItemStack(var5, 3, 39, false)) {
/*     */         
/* 467 */         return null;
/*     */       } 
/*     */       
/* 470 */       if (var5.stackSize == 0) {
/*     */         
/* 472 */         var4.putStack(null);
/*     */       }
/*     */       else {
/*     */         
/* 476 */         var4.onSlotChanged();
/*     */       } 
/*     */       
/* 479 */       if (var5.stackSize == var3.stackSize)
/*     */       {
/* 481 */         return null;
/*     */       }
/*     */       
/* 484 */       var4.onPickupFromSlot(playerIn, var5);
/*     */     } 
/*     */     
/* 487 */     return var3;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void updateItemName(String p_82850_1_) {
/* 495 */     this.repairedItemName = p_82850_1_;
/*     */     
/* 497 */     if (getSlot(2).getHasStack()) {
/*     */       
/* 499 */       ItemStack var2 = getSlot(2).getStack();
/*     */       
/* 501 */       if (StringUtils.isBlank(p_82850_1_)) {
/*     */         
/* 503 */         var2.clearCustomName();
/*     */       }
/*     */       else {
/*     */         
/* 507 */         var2.setStackDisplayName(this.repairedItemName);
/*     */       } 
/*     */     } 
/*     */     
/* 511 */     updateRepairOutput();
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\inventory\ContainerRepair.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */