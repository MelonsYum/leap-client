/*     */ package net.minecraft.entity.item;
/*     */ 
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityHanging;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.item.ItemMap;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTBase;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.storage.MapData;
/*     */ 
/*     */ public class EntityItemFrame
/*     */   extends EntityHanging {
/*  20 */   private float itemDropChance = 1.0F;
/*     */   
/*     */   private static final String __OBFID = "CL_00001547";
/*     */   
/*     */   public EntityItemFrame(World worldIn) {
/*  25 */     super(worldIn);
/*     */   }
/*     */ 
/*     */   
/*     */   public EntityItemFrame(World worldIn, BlockPos p_i45852_2_, EnumFacing p_i45852_3_) {
/*  30 */     super(worldIn, p_i45852_2_);
/*  31 */     func_174859_a(p_i45852_3_);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void entityInit() {
/*  36 */     getDataWatcher().addObjectByDataType(8, 5);
/*  37 */     getDataWatcher().addObject(9, Byte.valueOf((byte)0));
/*     */   }
/*     */ 
/*     */   
/*     */   public float getCollisionBorderSize() {
/*  42 */     return 0.0F;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean attackEntityFrom(DamageSource source, float amount) {
/*  50 */     if (func_180431_b(source))
/*     */     {
/*  52 */       return false;
/*     */     }
/*  54 */     if (!source.isExplosion() && getDisplayedItem() != null) {
/*     */       
/*  56 */       if (!this.worldObj.isRemote) {
/*     */         
/*  58 */         func_146065_b(source.getEntity(), false);
/*  59 */         setDisplayedItem((ItemStack)null);
/*     */       } 
/*     */       
/*  62 */       return true;
/*     */     } 
/*     */ 
/*     */     
/*  66 */     return super.attackEntityFrom(source, amount);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getWidthPixels() {
/*  72 */     return 12;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getHeightPixels() {
/*  77 */     return 12;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isInRangeToRenderDist(double distance) {
/*  86 */     double var3 = 16.0D;
/*  87 */     var3 *= 64.0D * this.renderDistanceWeight;
/*  88 */     return (distance < var3 * var3);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onBroken(Entity p_110128_1_) {
/*  96 */     func_146065_b(p_110128_1_, true);
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_146065_b(Entity p_146065_1_, boolean p_146065_2_) {
/* 101 */     if (this.worldObj.getGameRules().getGameRuleBooleanValue("doTileDrops")) {
/*     */       
/* 103 */       ItemStack var3 = getDisplayedItem();
/*     */       
/* 105 */       if (p_146065_1_ instanceof EntityPlayer) {
/*     */         
/* 107 */         EntityPlayer var4 = (EntityPlayer)p_146065_1_;
/*     */         
/* 109 */         if (var4.capabilities.isCreativeMode) {
/*     */           
/* 111 */           removeFrameFromMap(var3);
/*     */           
/*     */           return;
/*     */         } 
/*     */       } 
/* 116 */       if (p_146065_2_)
/*     */       {
/* 118 */         entityDropItem(new ItemStack(Items.item_frame), 0.0F);
/*     */       }
/*     */       
/* 121 */       if (var3 != null && this.rand.nextFloat() < this.itemDropChance) {
/*     */         
/* 123 */         var3 = var3.copy();
/* 124 */         removeFrameFromMap(var3);
/* 125 */         entityDropItem(var3, 0.0F);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void removeFrameFromMap(ItemStack p_110131_1_) {
/* 135 */     if (p_110131_1_ != null) {
/*     */       
/* 137 */       if (p_110131_1_.getItem() == Items.filled_map) {
/*     */         
/* 139 */         MapData var2 = ((ItemMap)p_110131_1_.getItem()).getMapData(p_110131_1_, this.worldObj);
/* 140 */         var2.playersVisibleOnMap.remove("frame-" + getEntityId());
/*     */       } 
/*     */       
/* 143 */       p_110131_1_.setItemFrame(null);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemStack getDisplayedItem() {
/* 149 */     return getDataWatcher().getWatchableObjectItemStack(8);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDisplayedItem(ItemStack p_82334_1_) {
/* 154 */     func_174864_a(p_82334_1_, true);
/*     */   }
/*     */ 
/*     */   
/*     */   private void func_174864_a(ItemStack p_174864_1_, boolean p_174864_2_) {
/* 159 */     if (p_174864_1_ != null) {
/*     */       
/* 161 */       p_174864_1_ = p_174864_1_.copy();
/* 162 */       p_174864_1_.stackSize = 1;
/* 163 */       p_174864_1_.setItemFrame(this);
/*     */     } 
/*     */     
/* 166 */     getDataWatcher().updateObject(8, p_174864_1_);
/* 167 */     getDataWatcher().setObjectWatched(8);
/*     */     
/* 169 */     if (p_174864_2_ && this.field_174861_a != null)
/*     */     {
/* 171 */       this.worldObj.updateComparatorOutputLevel(this.field_174861_a, Blocks.air);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getRotation() {
/* 180 */     return getDataWatcher().getWatchableObjectByte(9);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setItemRotation(int p_82336_1_) {
/* 185 */     func_174865_a(p_82336_1_, true);
/*     */   }
/*     */ 
/*     */   
/*     */   private void func_174865_a(int p_174865_1_, boolean p_174865_2_) {
/* 190 */     getDataWatcher().updateObject(9, Byte.valueOf((byte)(p_174865_1_ % 8)));
/*     */     
/* 192 */     if (p_174865_2_ && this.field_174861_a != null)
/*     */     {
/* 194 */       this.worldObj.updateComparatorOutputLevel(this.field_174861_a, Blocks.air);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void writeEntityToNBT(NBTTagCompound tagCompound) {
/* 203 */     if (getDisplayedItem() != null) {
/*     */       
/* 205 */       tagCompound.setTag("Item", (NBTBase)getDisplayedItem().writeToNBT(new NBTTagCompound()));
/* 206 */       tagCompound.setByte("ItemRotation", (byte)getRotation());
/* 207 */       tagCompound.setFloat("ItemDropChance", this.itemDropChance);
/*     */     } 
/*     */     
/* 210 */     super.writeEntityToNBT(tagCompound);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void readEntityFromNBT(NBTTagCompound tagCompund) {
/* 218 */     NBTTagCompound var2 = tagCompund.getCompoundTag("Item");
/*     */     
/* 220 */     if (var2 != null && !var2.hasNoTags()) {
/*     */       
/* 222 */       func_174864_a(ItemStack.loadItemStackFromNBT(var2), false);
/* 223 */       func_174865_a(tagCompund.getByte("ItemRotation"), false);
/*     */       
/* 225 */       if (tagCompund.hasKey("ItemDropChance", 99))
/*     */       {
/* 227 */         this.itemDropChance = tagCompund.getFloat("ItemDropChance");
/*     */       }
/*     */       
/* 230 */       if (tagCompund.hasKey("Direction"))
/*     */       {
/* 232 */         func_174865_a(getRotation() * 2, false);
/*     */       }
/*     */     } 
/*     */     
/* 236 */     super.readEntityFromNBT(tagCompund);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean interactFirst(EntityPlayer playerIn) {
/* 244 */     if (getDisplayedItem() == null) {
/*     */       
/* 246 */       ItemStack var2 = playerIn.getHeldItem();
/*     */       
/* 248 */       if (var2 != null && !this.worldObj.isRemote)
/*     */       {
/* 250 */         setDisplayedItem(var2);
/*     */         
/* 252 */         if (!playerIn.capabilities.isCreativeMode && --var2.stackSize <= 0)
/*     */         {
/* 254 */           playerIn.inventory.setInventorySlotContents(playerIn.inventory.currentItem, null);
/*     */         }
/*     */       }
/*     */     
/* 258 */     } else if (!this.worldObj.isRemote) {
/*     */       
/* 260 */       setItemRotation(getRotation() + 1);
/*     */     } 
/*     */     
/* 263 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public int func_174866_q() {
/* 268 */     return (getDisplayedItem() == null) ? 0 : (getRotation() % 8 + 1);
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\entity\item\EntityItemFrame.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */