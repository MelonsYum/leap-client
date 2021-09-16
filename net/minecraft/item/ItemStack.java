/*      */ package net.minecraft.item;
/*      */ 
/*      */ import com.google.common.collect.HashMultimap;
/*      */ import com.google.common.collect.Lists;
/*      */ import com.google.common.collect.Multimap;
/*      */ import java.text.DecimalFormat;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Iterator;
/*      */ import java.util.List;
/*      */ import java.util.Map;
/*      */ import java.util.Random;
/*      */ import net.minecraft.block.Block;
/*      */ import net.minecraft.enchantment.Enchantment;
/*      */ import net.minecraft.enchantment.EnchantmentDurability;
/*      */ import net.minecraft.enchantment.EnchantmentHelper;
/*      */ import net.minecraft.entity.Entity;
/*      */ import net.minecraft.entity.EntityLivingBase;
/*      */ import net.minecraft.entity.EnumCreatureAttribute;
/*      */ import net.minecraft.entity.SharedMonsterAttributes;
/*      */ import net.minecraft.entity.ai.attributes.AttributeModifier;
/*      */ import net.minecraft.entity.item.EntityItemFrame;
/*      */ import net.minecraft.entity.player.EntityPlayer;
/*      */ import net.minecraft.event.HoverEvent;
/*      */ import net.minecraft.init.Items;
/*      */ import net.minecraft.nbt.NBTBase;
/*      */ import net.minecraft.nbt.NBTTagCompound;
/*      */ import net.minecraft.nbt.NBTTagList;
/*      */ import net.minecraft.stats.StatList;
/*      */ import net.minecraft.util.BlockPos;
/*      */ import net.minecraft.util.ChatComponentText;
/*      */ import net.minecraft.util.EnumChatFormatting;
/*      */ import net.minecraft.util.EnumFacing;
/*      */ import net.minecraft.util.IChatComponent;
/*      */ import net.minecraft.util.ResourceLocation;
/*      */ import net.minecraft.util.StatCollector;
/*      */ import net.minecraft.world.World;
/*      */ 
/*      */ public final class ItemStack
/*      */ {
/*   40 */   public static final DecimalFormat DECIMALFORMAT = new DecimalFormat("#.###");
/*      */ 
/*      */   
/*      */   public int stackSize;
/*      */   
/*      */   public int animationsToGo;
/*      */   
/*      */   private Item item;
/*      */   
/*      */   private NBTTagCompound stackTagCompound;
/*      */   
/*      */   private int itemDamage;
/*      */   
/*      */   private EntityItemFrame itemFrame;
/*      */   
/*      */   private Block canDestroyCacheBlock;
/*      */   
/*      */   private boolean canDestroyCacheResult;
/*      */   
/*      */   private Block canPlaceOnCacheBlock;
/*      */   
/*      */   private boolean canPlaceOnCacheResult;
/*      */   
/*      */   private static final String __OBFID = "CL_00000043";
/*      */ 
/*      */   
/*      */   public ItemStack(Block blockIn) {
/*   67 */     this(blockIn, 1);
/*      */   }
/*      */ 
/*      */   
/*      */   public ItemStack(Block blockIn, int amount) {
/*   72 */     this(blockIn, amount, 0);
/*      */   }
/*      */ 
/*      */   
/*      */   public ItemStack(Block blockIn, int amount, int meta) {
/*   77 */     this(Item.getItemFromBlock(blockIn), amount, meta);
/*      */   }
/*      */ 
/*      */   
/*      */   public ItemStack(Item itemIn) {
/*   82 */     this(itemIn, 1);
/*      */   }
/*      */ 
/*      */   
/*      */   public ItemStack(Item itemIn, int amount) {
/*   87 */     this(itemIn, amount, 0);
/*      */   }
/*      */ 
/*      */   
/*      */   public ItemStack(Item itemIn, int amount, int meta) {
/*   92 */     this.canDestroyCacheBlock = null;
/*   93 */     this.canDestroyCacheResult = false;
/*   94 */     this.canPlaceOnCacheBlock = null;
/*   95 */     this.canPlaceOnCacheResult = false;
/*   96 */     this.item = itemIn;
/*   97 */     this.stackSize = amount;
/*   98 */     this.itemDamage = meta;
/*      */     
/*  100 */     if (this.itemDamage < 0)
/*      */     {
/*  102 */       this.itemDamage = 0;
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public static ItemStack loadItemStackFromNBT(NBTTagCompound nbt) {
/*  108 */     ItemStack var1 = new ItemStack();
/*  109 */     var1.readFromNBT(nbt);
/*  110 */     return (var1.getItem() != null) ? var1 : null;
/*      */   }
/*      */ 
/*      */   
/*      */   private ItemStack() {
/*  115 */     this.canDestroyCacheBlock = null;
/*  116 */     this.canDestroyCacheResult = false;
/*  117 */     this.canPlaceOnCacheBlock = null;
/*  118 */     this.canPlaceOnCacheResult = false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ItemStack splitStack(int amount) {
/*  126 */     ItemStack var2 = new ItemStack(this.item, amount, this.itemDamage);
/*      */     
/*  128 */     if (this.stackTagCompound != null)
/*      */     {
/*  130 */       var2.stackTagCompound = (NBTTagCompound)this.stackTagCompound.copy();
/*      */     }
/*      */     
/*  133 */     this.stackSize -= amount;
/*  134 */     return var2;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Item getItem() {
/*  142 */     return this.item;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean onItemUse(EntityPlayer playerIn, World worldIn, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ) {
/*  151 */     boolean var8 = getItem().onItemUse(this, playerIn, worldIn, pos, side, hitX, hitY, hitZ);
/*      */     
/*  153 */     if (var8)
/*      */     {
/*  155 */       playerIn.triggerAchievement(StatList.objectUseStats[Item.getIdFromItem(this.item)]);
/*      */     }
/*      */     
/*  158 */     return var8;
/*      */   }
/*      */ 
/*      */   
/*      */   public float getStrVsBlock(Block p_150997_1_) {
/*  163 */     return getItem().getStrVsBlock(this, p_150997_1_);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ItemStack useItemRightClick(World worldIn, EntityPlayer playerIn) {
/*  172 */     return getItem().onItemRightClick(this, worldIn, playerIn);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ItemStack onItemUseFinish(World worldIn, EntityPlayer playerIn) {
/*  180 */     return getItem().onItemUseFinish(this, worldIn, playerIn);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
/*  188 */     ResourceLocation var2 = (ResourceLocation)Item.itemRegistry.getNameForObject(this.item);
/*  189 */     nbt.setString("id", (var2 == null) ? "minecraft:air" : var2.toString());
/*  190 */     nbt.setByte("Count", (byte)this.stackSize);
/*  191 */     nbt.setShort("Damage", (short)this.itemDamage);
/*      */     
/*  193 */     if (this.stackTagCompound != null)
/*      */     {
/*  195 */       nbt.setTag("tag", (NBTBase)this.stackTagCompound);
/*      */     }
/*      */     
/*  198 */     return nbt;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void readFromNBT(NBTTagCompound nbt) {
/*  206 */     if (nbt.hasKey("id", 8)) {
/*      */       
/*  208 */       this.item = Item.getByNameOrId(nbt.getString("id"));
/*      */     }
/*      */     else {
/*      */       
/*  212 */       this.item = Item.getItemById(nbt.getShort("id"));
/*      */     } 
/*      */     
/*  215 */     this.stackSize = nbt.getByte("Count");
/*  216 */     this.itemDamage = nbt.getShort("Damage");
/*      */     
/*  218 */     if (this.itemDamage < 0)
/*      */     {
/*  220 */       this.itemDamage = 0;
/*      */     }
/*      */     
/*  223 */     if (nbt.hasKey("tag", 10)) {
/*      */       
/*  225 */       this.stackTagCompound = nbt.getCompoundTag("tag");
/*      */       
/*  227 */       if (this.item != null)
/*      */       {
/*  229 */         this.item.updateItemStackNBT(this.stackTagCompound);
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getMaxStackSize() {
/*  239 */     return getItem().getItemStackLimit();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isStackable() {
/*  247 */     return (getMaxStackSize() > 1 && (!isItemStackDamageable() || !isItemDamaged()));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isItemStackDamageable() {
/*  255 */     return (this.item == null) ? false : ((this.item.getMaxDamage() <= 0) ? false : (!(hasTagCompound() && getTagCompound().getBoolean("Unbreakable"))));
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean getHasSubtypes() {
/*  260 */     return this.item.getHasSubtypes();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isItemDamaged() {
/*  268 */     return (isItemStackDamageable() && this.itemDamage > 0);
/*      */   }
/*      */ 
/*      */   
/*      */   public int getItemDamage() {
/*  273 */     return this.itemDamage;
/*      */   }
/*      */ 
/*      */   
/*      */   public int getMetadata() {
/*  278 */     return this.itemDamage;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setItemDamage(int meta) {
/*  283 */     this.itemDamage = meta;
/*      */     
/*  285 */     if (this.itemDamage < 0)
/*      */     {
/*  287 */       this.itemDamage = 0;
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getMaxDamage() {
/*  296 */     return this.item.getMaxDamage();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean attemptDamageItem(int amount, Random rand) {
/*  307 */     if (!isItemStackDamageable())
/*      */     {
/*  309 */       return false;
/*      */     }
/*      */ 
/*      */     
/*  313 */     if (amount > 0) {
/*      */       
/*  315 */       int var3 = EnchantmentHelper.getEnchantmentLevel(Enchantment.unbreaking.effectId, this);
/*  316 */       int var4 = 0;
/*      */       
/*  318 */       for (int var5 = 0; var3 > 0 && var5 < amount; var5++) {
/*      */         
/*  320 */         if (EnchantmentDurability.negateDamage(this, var3, rand))
/*      */         {
/*  322 */           var4++;
/*      */         }
/*      */       } 
/*      */       
/*  326 */       amount -= var4;
/*      */       
/*  328 */       if (amount <= 0)
/*      */       {
/*  330 */         return false;
/*      */       }
/*      */     } 
/*      */     
/*  334 */     this.itemDamage += amount;
/*  335 */     return (this.itemDamage > getMaxDamage());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void damageItem(int amount, EntityLivingBase entityIn) {
/*  344 */     if (!(entityIn instanceof EntityPlayer) || !((EntityPlayer)entityIn).capabilities.isCreativeMode)
/*      */     {
/*  346 */       if (isItemStackDamageable())
/*      */       {
/*  348 */         if (attemptDamageItem(amount, entityIn.getRNG())) {
/*      */           
/*  350 */           entityIn.renderBrokenItemStack(this);
/*  351 */           this.stackSize--;
/*      */           
/*  353 */           if (entityIn instanceof EntityPlayer) {
/*      */             
/*  355 */             EntityPlayer var3 = (EntityPlayer)entityIn;
/*  356 */             var3.triggerAchievement(StatList.objectBreakStats[Item.getIdFromItem(this.item)]);
/*      */             
/*  358 */             if (this.stackSize == 0 && getItem() instanceof ItemBow)
/*      */             {
/*  360 */               var3.destroyCurrentEquippedItem();
/*      */             }
/*      */           } 
/*      */           
/*  364 */           if (this.stackSize < 0)
/*      */           {
/*  366 */             this.stackSize = 0;
/*      */           }
/*      */           
/*  369 */           this.itemDamage = 0;
/*      */         } 
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void hitEntity(EntityLivingBase entityIn, EntityPlayer playerIn) {
/*  380 */     boolean var3 = this.item.hitEntity(this, entityIn, (EntityLivingBase)playerIn);
/*      */     
/*  382 */     if (var3)
/*      */     {
/*  384 */       playerIn.triggerAchievement(StatList.objectUseStats[Item.getIdFromItem(this.item)]);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void onBlockDestroyed(World worldIn, Block blockIn, BlockPos pos, EntityPlayer playerIn) {
/*  395 */     boolean var5 = this.item.onBlockDestroyed(this, worldIn, blockIn, pos, (EntityLivingBase)playerIn);
/*      */     
/*  397 */     if (var5)
/*      */     {
/*  399 */       playerIn.triggerAchievement(StatList.objectUseStats[Item.getIdFromItem(this.item)]);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean canHarvestBlock(Block p_150998_1_) {
/*  408 */     return this.item.canHarvestBlock(p_150998_1_);
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean interactWithEntity(EntityPlayer playerIn, EntityLivingBase entityIn) {
/*  413 */     return this.item.itemInteractionForEntity(this, playerIn, entityIn);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ItemStack copy() {
/*  421 */     ItemStack var1 = new ItemStack(this.item, this.stackSize, this.itemDamage);
/*      */     
/*  423 */     if (this.stackTagCompound != null)
/*      */     {
/*  425 */       var1.stackTagCompound = (NBTTagCompound)this.stackTagCompound.copy();
/*      */     }
/*      */     
/*  428 */     return var1;
/*      */   }
/*      */ 
/*      */   
/*      */   public static boolean areItemStackTagsEqual(ItemStack stackA, ItemStack stackB) {
/*  433 */     return (stackA == null && stackB == null) ? true : ((stackA != null && stackB != null) ? ((stackA.stackTagCompound == null && stackB.stackTagCompound != null) ? false : (!(stackA.stackTagCompound != null && !stackA.stackTagCompound.equals(stackB.stackTagCompound)))) : false);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean areItemStacksEqual(ItemStack stackA, ItemStack stackB) {
/*  441 */     return (stackA == null && stackB == null) ? true : ((stackA != null && stackB != null) ? stackA.isItemStackEqual(stackB) : false);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean isItemStackEqual(ItemStack other) {
/*  449 */     return (this.stackSize != other.stackSize) ? false : ((this.item != other.item) ? false : ((this.itemDamage != other.itemDamage) ? false : ((this.stackTagCompound == null && other.stackTagCompound != null) ? false : (!(this.stackTagCompound != null && !this.stackTagCompound.equals(other.stackTagCompound))))));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean areItemsEqual(ItemStack stackA, ItemStack stackB) {
/*  457 */     return (stackA == null && stackB == null) ? true : ((stackA != null && stackB != null) ? stackA.isItemEqual(stackB) : false);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isItemEqual(ItemStack other) {
/*  466 */     return (other != null && this.item == other.item && this.itemDamage == other.itemDamage);
/*      */   }
/*      */ 
/*      */   
/*      */   public String getUnlocalizedName() {
/*  471 */     return this.item.getUnlocalizedName(this);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static ItemStack copyItemStack(ItemStack stack) {
/*  479 */     return (stack == null) ? null : stack.copy();
/*      */   }
/*      */ 
/*      */   
/*      */   public String toString() {
/*  484 */     return String.valueOf(this.stackSize) + "x" + this.item.getUnlocalizedName() + "@" + this.itemDamage;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void updateAnimation(World worldIn, Entity entityIn, int inventorySlot, boolean isCurrentItem) {
/*  493 */     if (this.animationsToGo > 0)
/*      */     {
/*  495 */       this.animationsToGo--;
/*      */     }
/*      */     
/*  498 */     this.item.onUpdate(this, worldIn, entityIn, inventorySlot, isCurrentItem);
/*      */   }
/*      */ 
/*      */   
/*      */   public void onCrafting(World worldIn, EntityPlayer playerIn, int amount) {
/*  503 */     playerIn.addStat(StatList.objectCraftStats[Item.getIdFromItem(this.item)], amount);
/*  504 */     this.item.onCreated(this, worldIn, playerIn);
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean getIsItemStackEqual(ItemStack p_179549_1_) {
/*  509 */     return isItemStackEqual(p_179549_1_);
/*      */   }
/*      */ 
/*      */   
/*      */   public int getMaxItemUseDuration() {
/*  514 */     return getItem().getMaxItemUseDuration(this);
/*      */   }
/*      */ 
/*      */   
/*      */   public EnumAction getItemUseAction() {
/*  519 */     return getItem().getItemUseAction(this);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void onPlayerStoppedUsing(World worldIn, EntityPlayer playerIn, int timeLeft) {
/*  529 */     getItem().onPlayerStoppedUsing(this, worldIn, playerIn, timeLeft);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean hasTagCompound() {
/*  537 */     return (this.stackTagCompound != null);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public NBTTagCompound getTagCompound() {
/*  545 */     return this.stackTagCompound;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public NBTTagCompound getSubCompound(String key, boolean create) {
/*  556 */     if (this.stackTagCompound != null && this.stackTagCompound.hasKey(key, 10))
/*      */     {
/*  558 */       return this.stackTagCompound.getCompoundTag(key);
/*      */     }
/*  560 */     if (create) {
/*      */       
/*  562 */       NBTTagCompound var3 = new NBTTagCompound();
/*  563 */       setTagInfo(key, (NBTBase)var3);
/*  564 */       return var3;
/*      */     } 
/*      */ 
/*      */     
/*  568 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public NBTTagList getEnchantmentTagList() {
/*  574 */     return (this.stackTagCompound == null) ? null : this.stackTagCompound.getTagList("ench", 10);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setTagCompound(NBTTagCompound nbt) {
/*  582 */     this.stackTagCompound = nbt;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getDisplayName() {
/*  590 */     String var1 = getItem().getItemStackDisplayName(this);
/*      */     
/*  592 */     if (this.stackTagCompound != null && this.stackTagCompound.hasKey("display", 10)) {
/*      */       
/*  594 */       NBTTagCompound var2 = this.stackTagCompound.getCompoundTag("display");
/*      */       
/*  596 */       if (var2.hasKey("Name", 8))
/*      */       {
/*  598 */         var1 = var2.getString("Name");
/*      */       }
/*      */     } 
/*      */     
/*  602 */     return var1;
/*      */   }
/*      */ 
/*      */   
/*      */   public ItemStack setStackDisplayName(String p_151001_1_) {
/*  607 */     if (this.stackTagCompound == null)
/*      */     {
/*  609 */       this.stackTagCompound = new NBTTagCompound();
/*      */     }
/*      */     
/*  612 */     if (!this.stackTagCompound.hasKey("display", 10))
/*      */     {
/*  614 */       this.stackTagCompound.setTag("display", (NBTBase)new NBTTagCompound());
/*      */     }
/*      */     
/*  617 */     this.stackTagCompound.getCompoundTag("display").setString("Name", p_151001_1_);
/*  618 */     return this;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void clearCustomName() {
/*  626 */     if (this.stackTagCompound != null)
/*      */     {
/*  628 */       if (this.stackTagCompound.hasKey("display", 10)) {
/*      */         
/*  630 */         NBTTagCompound var1 = this.stackTagCompound.getCompoundTag("display");
/*  631 */         var1.removeTag("Name");
/*      */         
/*  633 */         if (var1.hasNoTags()) {
/*      */           
/*  635 */           this.stackTagCompound.removeTag("display");
/*      */           
/*  637 */           if (this.stackTagCompound.hasNoTags())
/*      */           {
/*  639 */             setTagCompound(null);
/*      */           }
/*      */         } 
/*      */       } 
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean hasDisplayName() {
/*  651 */     return (this.stackTagCompound == null) ? false : (!this.stackTagCompound.hasKey("display", 10) ? false : this.stackTagCompound.getCompoundTag("display").hasKey("Name", 8));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public List getTooltip(EntityPlayer playerIn, boolean advanced) {
/*  661 */     ArrayList<String> var3 = Lists.newArrayList();
/*  662 */     String var4 = getDisplayName();
/*      */     
/*  664 */     if (hasDisplayName())
/*      */     {
/*  666 */       var4 = EnumChatFormatting.ITALIC + var4;
/*      */     }
/*      */     
/*  669 */     var4 = String.valueOf(var4) + EnumChatFormatting.RESET;
/*      */     
/*  671 */     if (advanced) {
/*      */       
/*  673 */       String var5 = "";
/*      */       
/*  675 */       if (var4.length() > 0) {
/*      */         
/*  677 */         var4 = String.valueOf(var4) + " (";
/*  678 */         var5 = ")";
/*      */       } 
/*      */       
/*  681 */       int var6 = Item.getIdFromItem(this.item);
/*      */       
/*  683 */       if (getHasSubtypes())
/*      */       {
/*  685 */         var4 = String.valueOf(var4) + String.format("#%04d/%d%s", new Object[] { Integer.valueOf(var6), Integer.valueOf(this.itemDamage), var5 });
/*      */       }
/*      */       else
/*      */       {
/*  689 */         var4 = String.valueOf(var4) + String.format("#%04d%s", new Object[] { Integer.valueOf(var6), var5 });
/*      */       }
/*      */     
/*  692 */     } else if (!hasDisplayName() && this.item == Items.filled_map) {
/*      */       
/*  694 */       var4 = String.valueOf(var4) + " #" + this.itemDamage;
/*      */     } 
/*      */     
/*  697 */     var3.add(var4);
/*  698 */     int var14 = 0;
/*      */     
/*  700 */     if (hasTagCompound() && this.stackTagCompound.hasKey("HideFlags", 99))
/*      */     {
/*  702 */       var14 = this.stackTagCompound.getInteger("HideFlags");
/*      */     }
/*      */     
/*  705 */     if ((var14 & 0x20) == 0)
/*      */     {
/*  707 */       this.item.addInformation(this, playerIn, var3, advanced);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  713 */     if (hasTagCompound()) {
/*      */       
/*  715 */       if ((var14 & 0x1) == 0) {
/*      */         
/*  717 */         NBTTagList var15 = getEnchantmentTagList();
/*      */         
/*  719 */         if (var15 != null)
/*      */         {
/*  721 */           for (int var7 = 0; var7 < var15.tagCount(); var7++) {
/*      */             
/*  723 */             short var8 = var15.getCompoundTagAt(var7).getShort("id");
/*  724 */             short var9 = var15.getCompoundTagAt(var7).getShort("lvl");
/*      */             
/*  726 */             if (Enchantment.func_180306_c(var8) != null)
/*      */             {
/*  728 */               var3.add(Enchantment.func_180306_c(var8).getTranslatedName(var9));
/*      */             }
/*      */           } 
/*      */         }
/*      */       } 
/*      */       
/*  734 */       if (this.stackTagCompound.hasKey("display", 10)) {
/*      */         
/*  736 */         NBTTagCompound var16 = this.stackTagCompound.getCompoundTag("display");
/*      */         
/*  738 */         if (var16.hasKey("color", 3))
/*      */         {
/*  740 */           if (advanced) {
/*      */             
/*  742 */             var3.add("Color: #" + Integer.toHexString(var16.getInteger("color")).toUpperCase());
/*      */           }
/*      */           else {
/*      */             
/*  746 */             var3.add(EnumChatFormatting.ITALIC + StatCollector.translateToLocal("item.dyed"));
/*      */           } 
/*      */         }
/*      */         
/*  750 */         if (var16.getTagType("Lore") == 9) {
/*      */           
/*  752 */           NBTTagList var18 = var16.getTagList("Lore", 8);
/*      */           
/*  754 */           if (var18.tagCount() > 0)
/*      */           {
/*  756 */             for (int var20 = 0; var20 < var18.tagCount(); var20++)
/*      */             {
/*  758 */               var3.add(EnumChatFormatting.DARK_PURPLE + EnumChatFormatting.ITALIC + var18.getStringTagAt(var20));
/*      */             }
/*      */           }
/*      */         } 
/*      */       } 
/*      */     } 
/*      */     
/*  765 */     Multimap var17 = getAttributeModifiers();
/*      */     
/*  767 */     if (!var17.isEmpty() && (var14 & 0x2) == 0) {
/*      */       
/*  769 */       var3.add("");
/*  770 */       Iterator<Map.Entry> var19 = var17.entries().iterator();
/*      */       
/*  772 */       while (var19.hasNext()) {
/*      */         double var12;
/*  774 */         Map.Entry var21 = var19.next();
/*  775 */         AttributeModifier var22 = (AttributeModifier)var21.getValue();
/*  776 */         double var10 = var22.getAmount();
/*      */         
/*  778 */         if (var22.getID() == Item.itemModifierUUID)
/*      */         {
/*  780 */           var10 += EnchantmentHelper.func_152377_a(this, EnumCreatureAttribute.UNDEFINED);
/*      */         }
/*      */ 
/*      */ 
/*      */         
/*  785 */         if (var22.getOperation() != 1 && var22.getOperation() != 2) {
/*      */           
/*  787 */           var12 = var10;
/*      */         }
/*      */         else {
/*      */           
/*  791 */           var12 = var10 * 100.0D;
/*      */         } 
/*      */         
/*  794 */         if (var10 > 0.0D) {
/*      */           
/*  796 */           var3.add(EnumChatFormatting.BLUE + StatCollector.translateToLocalFormatted("attribute.modifier.plus." + var22.getOperation(), new Object[] { DECIMALFORMAT.format(var12), StatCollector.translateToLocal("attribute.name." + (String)var21.getKey()) })); continue;
/*      */         } 
/*  798 */         if (var10 < 0.0D) {
/*      */           
/*  800 */           var12 *= -1.0D;
/*  801 */           var3.add(EnumChatFormatting.RED + StatCollector.translateToLocalFormatted("attribute.modifier.take." + var22.getOperation(), new Object[] { DECIMALFORMAT.format(var12), StatCollector.translateToLocal("attribute.name." + (String)var21.getKey()) }));
/*      */         } 
/*      */       } 
/*      */     } 
/*      */     
/*  806 */     if (hasTagCompound() && getTagCompound().getBoolean("Unbreakable") && (var14 & 0x4) == 0)
/*      */     {
/*  808 */       var3.add(EnumChatFormatting.BLUE + StatCollector.translateToLocal("item.unbreakable"));
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*  813 */     if (hasTagCompound() && this.stackTagCompound.hasKey("CanDestroy", 9) && (var14 & 0x8) == 0) {
/*      */       
/*  815 */       NBTTagList var18 = this.stackTagCompound.getTagList("CanDestroy", 8);
/*      */       
/*  817 */       if (var18.tagCount() > 0) {
/*      */         
/*  819 */         var3.add("");
/*  820 */         var3.add(EnumChatFormatting.GRAY + StatCollector.translateToLocal("item.canBreak"));
/*      */         
/*  822 */         for (int var20 = 0; var20 < var18.tagCount(); var20++) {
/*      */           
/*  824 */           Block var23 = Block.getBlockFromName(var18.getStringTagAt(var20));
/*      */           
/*  826 */           if (var23 != null) {
/*      */             
/*  828 */             var3.add(EnumChatFormatting.DARK_GRAY + var23.getLocalizedName());
/*      */           }
/*      */           else {
/*      */             
/*  832 */             var3.add(EnumChatFormatting.DARK_GRAY + "missingno");
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     } 
/*      */     
/*  838 */     if (hasTagCompound() && this.stackTagCompound.hasKey("CanPlaceOn", 9) && (var14 & 0x10) == 0) {
/*      */       
/*  840 */       NBTTagList var18 = this.stackTagCompound.getTagList("CanPlaceOn", 8);
/*      */       
/*  842 */       if (var18.tagCount() > 0) {
/*      */         
/*  844 */         var3.add("");
/*  845 */         var3.add(EnumChatFormatting.GRAY + StatCollector.translateToLocal("item.canPlace"));
/*      */         
/*  847 */         for (int var20 = 0; var20 < var18.tagCount(); var20++) {
/*      */           
/*  849 */           Block var23 = Block.getBlockFromName(var18.getStringTagAt(var20));
/*      */           
/*  851 */           if (var23 != null) {
/*      */             
/*  853 */             var3.add(EnumChatFormatting.DARK_GRAY + var23.getLocalizedName());
/*      */           }
/*      */           else {
/*      */             
/*  857 */             var3.add(EnumChatFormatting.DARK_GRAY + "missingno");
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     } 
/*      */     
/*  863 */     if (advanced) {
/*      */       
/*  865 */       if (isItemDamaged())
/*      */       {
/*  867 */         var3.add("Durability: " + (getMaxDamage() - getItemDamage()) + " / " + getMaxDamage());
/*      */       }
/*      */       
/*  870 */       var3.add(EnumChatFormatting.DARK_GRAY + ((ResourceLocation)Item.itemRegistry.getNameForObject(this.item)).toString());
/*      */       
/*  872 */       if (hasTagCompound())
/*      */       {
/*  874 */         var3.add(EnumChatFormatting.DARK_GRAY + "NBT: " + getTagCompound().getKeySet().size() + " tag(s)");
/*      */       }
/*      */     } 
/*      */     
/*  878 */     return var3;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean hasEffect() {
/*  883 */     return getItem().hasEffect(this);
/*      */   }
/*      */ 
/*      */   
/*      */   public EnumRarity getRarity() {
/*  888 */     return getItem().getRarity(this);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isItemEnchantable() {
/*  896 */     return !getItem().isItemTool(this) ? false : (!isItemEnchanted());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void addEnchantment(Enchantment ench, int level) {
/*  904 */     if (this.stackTagCompound == null)
/*      */     {
/*  906 */       setTagCompound(new NBTTagCompound());
/*      */     }
/*      */     
/*  909 */     if (!this.stackTagCompound.hasKey("ench", 9))
/*      */     {
/*  911 */       this.stackTagCompound.setTag("ench", (NBTBase)new NBTTagList());
/*      */     }
/*      */     
/*  914 */     NBTTagList var3 = this.stackTagCompound.getTagList("ench", 10);
/*  915 */     NBTTagCompound var4 = new NBTTagCompound();
/*  916 */     var4.setShort("id", (short)ench.effectId);
/*  917 */     var4.setShort("lvl", (short)(byte)level);
/*  918 */     var3.appendTag((NBTBase)var4);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isItemEnchanted() {
/*  926 */     return (this.stackTagCompound != null && this.stackTagCompound.hasKey("ench", 9));
/*      */   }
/*      */ 
/*      */   
/*      */   public void setTagInfo(String key, NBTBase value) {
/*  931 */     if (this.stackTagCompound == null)
/*      */     {
/*  933 */       setTagCompound(new NBTTagCompound());
/*      */     }
/*      */     
/*  936 */     this.stackTagCompound.setTag(key, value);
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean canEditBlocks() {
/*  941 */     return getItem().canItemEditBlocks();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isOnItemFrame() {
/*  949 */     return (this.itemFrame != null);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setItemFrame(EntityItemFrame frame) {
/*  957 */     this.itemFrame = frame;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public EntityItemFrame getItemFrame() {
/*  965 */     return this.itemFrame;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getRepairCost() {
/*  973 */     return (hasTagCompound() && this.stackTagCompound.hasKey("RepairCost", 3)) ? this.stackTagCompound.getInteger("RepairCost") : 0;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setRepairCost(int cost) {
/*  981 */     if (!hasTagCompound())
/*      */     {
/*  983 */       this.stackTagCompound = new NBTTagCompound();
/*      */     }
/*      */     
/*  986 */     this.stackTagCompound.setInteger("RepairCost", cost);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Multimap getAttributeModifiers() {
/*      */     Object var1;
/*  997 */     if (hasTagCompound() && this.stackTagCompound.hasKey("AttributeModifiers", 9)) {
/*      */       
/*  999 */       var1 = HashMultimap.create();
/* 1000 */       NBTTagList var2 = this.stackTagCompound.getTagList("AttributeModifiers", 10);
/*      */       
/* 1002 */       for (int var3 = 0; var3 < var2.tagCount(); var3++)
/*      */       {
/* 1004 */         NBTTagCompound var4 = var2.getCompoundTagAt(var3);
/* 1005 */         AttributeModifier var5 = SharedMonsterAttributes.readAttributeModifierFromNBT(var4);
/*      */         
/* 1007 */         if (var5 != null && var5.getID().getLeastSignificantBits() != 0L && var5.getID().getMostSignificantBits() != 0L)
/*      */         {
/* 1009 */           ((Multimap)var1).put(var4.getString("AttributeName"), var5);
/*      */         }
/*      */       }
/*      */     
/*      */     } else {
/*      */       
/* 1015 */       var1 = getItem().getItemAttributeModifiers();
/*      */     } 
/*      */     
/* 1018 */     return (Multimap)var1;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setItem(Item p_150996_1_) {
/* 1023 */     this.item = p_150996_1_;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public IChatComponent getChatComponent() {
/* 1031 */     ChatComponentText var1 = new ChatComponentText(getDisplayName());
/*      */     
/* 1033 */     if (hasDisplayName())
/*      */     {
/* 1035 */       var1.getChatStyle().setItalic(Boolean.valueOf(true));
/*      */     }
/*      */     
/* 1038 */     IChatComponent var2 = (new ChatComponentText("[")).appendSibling((IChatComponent)var1).appendText("]");
/*      */     
/* 1040 */     if (this.item != null) {
/*      */       
/* 1042 */       NBTTagCompound var3 = new NBTTagCompound();
/* 1043 */       writeToNBT(var3);
/* 1044 */       var2.getChatStyle().setChatHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_ITEM, (IChatComponent)new ChatComponentText(var3.toString())));
/* 1045 */       var2.getChatStyle().setColor((getRarity()).rarityColor);
/*      */     } 
/*      */     
/* 1048 */     return var2;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean canDestroy(Block blockIn) {
/* 1053 */     if (blockIn == this.canDestroyCacheBlock)
/*      */     {
/* 1055 */       return this.canDestroyCacheResult;
/*      */     }
/*      */ 
/*      */     
/* 1059 */     this.canDestroyCacheBlock = blockIn;
/*      */     
/* 1061 */     if (hasTagCompound() && this.stackTagCompound.hasKey("CanDestroy", 9)) {
/*      */       
/* 1063 */       NBTTagList var2 = this.stackTagCompound.getTagList("CanDestroy", 8);
/*      */       
/* 1065 */       for (int var3 = 0; var3 < var2.tagCount(); var3++) {
/*      */         
/* 1067 */         Block var4 = Block.getBlockFromName(var2.getStringTagAt(var3));
/*      */         
/* 1069 */         if (var4 == blockIn) {
/*      */           
/* 1071 */           this.canDestroyCacheResult = true;
/* 1072 */           return true;
/*      */         } 
/*      */       } 
/*      */     } 
/*      */     
/* 1077 */     this.canDestroyCacheResult = false;
/* 1078 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean canPlaceOn(Block blockIn) {
/* 1084 */     if (blockIn == this.canPlaceOnCacheBlock)
/*      */     {
/* 1086 */       return this.canPlaceOnCacheResult;
/*      */     }
/*      */ 
/*      */     
/* 1090 */     this.canPlaceOnCacheBlock = blockIn;
/*      */     
/* 1092 */     if (hasTagCompound() && this.stackTagCompound.hasKey("CanPlaceOn", 9)) {
/*      */       
/* 1094 */       NBTTagList var2 = this.stackTagCompound.getTagList("CanPlaceOn", 8);
/*      */       
/* 1096 */       for (int var3 = 0; var3 < var2.tagCount(); var3++) {
/*      */         
/* 1098 */         Block var4 = Block.getBlockFromName(var2.getStringTagAt(var3));
/*      */         
/* 1100 */         if (var4 == blockIn) {
/*      */           
/* 1102 */           this.canPlaceOnCacheResult = true;
/* 1103 */           return true;
/*      */         } 
/*      */       } 
/*      */     } 
/*      */     
/* 1108 */     this.canPlaceOnCacheResult = false;
/* 1109 */     return false;
/*      */   }
/*      */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\item\ItemStack.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */