/*      */ package net.minecraft.item;
/*      */ 
/*      */ import com.google.common.base.Function;
/*      */ import com.google.common.collect.HashMultimap;
/*      */ import com.google.common.collect.Maps;
/*      */ import com.google.common.collect.Multimap;
/*      */ import java.util.List;
/*      */ import java.util.Map;
/*      */ import java.util.Random;
/*      */ import java.util.UUID;
/*      */ import net.minecraft.block.Block;
/*      */ import net.minecraft.block.BlockDirt;
/*      */ import net.minecraft.block.BlockDoublePlant;
/*      */ import net.minecraft.block.BlockFlower;
/*      */ import net.minecraft.block.BlockPlanks;
/*      */ import net.minecraft.block.BlockPrismarine;
/*      */ import net.minecraft.block.BlockRedSandstone;
/*      */ import net.minecraft.block.BlockSand;
/*      */ import net.minecraft.block.BlockSandStone;
/*      */ import net.minecraft.block.BlockSilverfish;
/*      */ import net.minecraft.block.BlockStone;
/*      */ import net.minecraft.block.BlockStoneBrick;
/*      */ import net.minecraft.block.BlockWall;
/*      */ import net.minecraft.creativetab.CreativeTabs;
/*      */ import net.minecraft.entity.Entity;
/*      */ import net.minecraft.entity.EntityLivingBase;
/*      */ import net.minecraft.entity.item.EntityItemFrame;
/*      */ import net.minecraft.entity.item.EntityMinecart;
/*      */ import net.minecraft.entity.item.EntityPainting;
/*      */ import net.minecraft.entity.player.EntityPlayer;
/*      */ import net.minecraft.init.Blocks;
/*      */ import net.minecraft.init.Items;
/*      */ import net.minecraft.nbt.NBTTagCompound;
/*      */ import net.minecraft.potion.Potion;
/*      */ import net.minecraft.potion.PotionHelper;
/*      */ import net.minecraft.util.BlockPos;
/*      */ import net.minecraft.util.EnumFacing;
/*      */ import net.minecraft.util.MathHelper;
/*      */ import net.minecraft.util.MovingObjectPosition;
/*      */ import net.minecraft.util.RegistryNamespaced;
/*      */ import net.minecraft.util.ResourceLocation;
/*      */ import net.minecraft.util.StatCollector;
/*      */ import net.minecraft.util.Vec3;
/*      */ import net.minecraft.world.World;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class Item
/*      */ {
/*      */   public Item() {
/*   57 */     this.maxStackSize = 64;
/*      */   }
/*      */ 
/*      */   
/*      */   public static final RegistryNamespaced itemRegistry = new RegistryNamespaced();
/*      */   
/*      */   private static final Map BLOCK_TO_ITEM = Maps.newHashMap();
/*      */   
/*      */   protected static final UUID itemModifierUUID = UUID.fromString("CB3F55D3-645C-4F38-A497-9C13A33DB5CF");
/*      */   
/*      */   private CreativeTabs tabToDisplayOn;
/*      */   
/*      */   protected static Random itemRand = new Random();
/*      */   
/*      */   protected int maxStackSize;
/*      */   
/*      */   private int maxDamage;
/*      */   protected boolean bFull3D;
/*      */   protected boolean hasSubtypes;
/*      */   private Item containerItem;
/*      */   private String potionEffect;
/*      */   private String unlocalizedName;
/*      */   private static final String __OBFID = "CL_00000041";
/*      */   
/*      */   public static int getIdFromItem(Item itemIn) {
/*   82 */     return (itemIn == null) ? 0 : itemRegistry.getIDForObject(itemIn);
/*      */   }
/*      */ 
/*      */   
/*      */   public static Item getItemById(int id) {
/*   87 */     return (Item)itemRegistry.getObjectById(id);
/*      */   }
/*      */ 
/*      */   
/*      */   public static Item getItemFromBlock(Block blockIn) {
/*   92 */     return (Item)BLOCK_TO_ITEM.get(blockIn);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static Item getByNameOrId(String id) {
/*  101 */     Item var1 = (Item)itemRegistry.getObject(new ResourceLocation(id));
/*      */     
/*  103 */     if (var1 == null) {
/*      */       
/*      */       try {
/*      */         
/*  107 */         return getItemById(Integer.parseInt(id));
/*      */       }
/*  109 */       catch (NumberFormatException numberFormatException) {}
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  115 */     return var1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean updateItemStackNBT(NBTTagCompound nbt) {
/*  123 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   public Item setMaxStackSize(int maxStackSize) {
/*  128 */     this.maxStackSize = maxStackSize;
/*  129 */     return this;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ) {
/*  140 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   public float getStrVsBlock(ItemStack stack, Block p_150893_2_) {
/*  145 */     return 1.0F;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ItemStack onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn) {
/*  153 */     return itemStackIn;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ItemStack onItemUseFinish(ItemStack stack, World worldIn, EntityPlayer playerIn) {
/*  162 */     return stack;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getItemStackLimit() {
/*  170 */     return this.maxStackSize;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getMetadata(int damage) {
/*  179 */     return 0;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean getHasSubtypes() {
/*  184 */     return this.hasSubtypes;
/*      */   }
/*      */ 
/*      */   
/*      */   protected Item setHasSubtypes(boolean hasSubtypes) {
/*  189 */     this.hasSubtypes = hasSubtypes;
/*  190 */     return this;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getMaxDamage() {
/*  198 */     return this.maxDamage;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected Item setMaxDamage(int maxDurability) {
/*  206 */     this.maxDamage = maxDurability;
/*  207 */     return this;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isDamageable() {
/*  212 */     return (this.maxDamage > 0 && !this.hasSubtypes);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {
/*  224 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean onBlockDestroyed(ItemStack stack, World worldIn, Block blockIn, BlockPos pos, EntityLivingBase playerIn) {
/*  232 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean canHarvestBlock(Block blockIn) {
/*  240 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean itemInteractionForEntity(ItemStack stack, EntityPlayer playerIn, EntityLivingBase target) {
/*  248 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Item setFull3D() {
/*  256 */     this.bFull3D = true;
/*  257 */     return this;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isFull3D() {
/*  265 */     return this.bFull3D;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean shouldRotateAroundWhenRendering() {
/*  274 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Item setUnlocalizedName(String unlocalizedName) {
/*  282 */     this.unlocalizedName = unlocalizedName;
/*  283 */     return this;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getUnlocalizedNameInefficiently(ItemStack stack) {
/*  292 */     String var2 = getUnlocalizedName(stack);
/*  293 */     return (var2 == null) ? "" : StatCollector.translateToLocal(var2);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getUnlocalizedName() {
/*  301 */     return "item." + this.unlocalizedName;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getUnlocalizedName(ItemStack stack) {
/*  310 */     return "item." + this.unlocalizedName;
/*      */   }
/*      */ 
/*      */   
/*      */   public Item setContainerItem(Item containerItem) {
/*  315 */     this.containerItem = containerItem;
/*  316 */     return this;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean getShareTag() {
/*  324 */     return true;
/*      */   }
/*      */ 
/*      */   
/*      */   public Item getContainerItem() {
/*  329 */     return this.containerItem;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean hasContainerItem() {
/*  337 */     return (this.containerItem != null);
/*      */   }
/*      */ 
/*      */   
/*      */   public int getColorFromItemStack(ItemStack stack, int renderPass) {
/*  342 */     return 16777215;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void onCreated(ItemStack stack, World worldIn, EntityPlayer playerIn) {}
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isMap() {
/*  361 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public EnumAction getItemUseAction(ItemStack stack) {
/*  369 */     return EnumAction.NONE;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getMaxItemUseDuration(ItemStack stack) {
/*  377 */     return 0;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void onPlayerStoppedUsing(ItemStack stack, World worldIn, EntityPlayer playerIn, int timeLeft) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected Item setPotionEffect(String potionEffect) {
/*  392 */     this.potionEffect = potionEffect;
/*  393 */     return this;
/*      */   }
/*      */ 
/*      */   
/*      */   public String getPotionEffect(ItemStack stack) {
/*  398 */     return this.potionEffect;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isPotionIngredient(ItemStack stack) {
/*  403 */     return (getPotionEffect(stack) != null);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void addInformation(ItemStack stack, EntityPlayer playerIn, List tooltip, boolean advanced) {}
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getItemStackDisplayName(ItemStack stack) {
/*  416 */     return StatCollector.translateToLocal(String.valueOf(getUnlocalizedNameInefficiently(stack)) + ".name").trim();
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean hasEffect(ItemStack stack) {
/*  421 */     return stack.isItemEnchanted();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public EnumRarity getRarity(ItemStack stack) {
/*  429 */     return stack.isItemEnchanted() ? EnumRarity.RARE : EnumRarity.COMMON;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isItemTool(ItemStack stack) {
/*  437 */     return (getItemStackLimit() == 1 && isDamageable());
/*      */   }
/*      */ 
/*      */   
/*      */   protected MovingObjectPosition getMovingObjectPositionFromPlayer(World worldIn, EntityPlayer playerIn, boolean useLiquids) {
/*  442 */     float var4 = playerIn.prevRotationPitch + playerIn.rotationPitch - playerIn.prevRotationPitch;
/*  443 */     float var5 = playerIn.prevRotationYaw + playerIn.rotationYaw - playerIn.prevRotationYaw;
/*  444 */     double var6 = playerIn.prevPosX + playerIn.posX - playerIn.prevPosX;
/*  445 */     double var8 = playerIn.prevPosY + playerIn.posY - playerIn.prevPosY + playerIn.getEyeHeight();
/*  446 */     double var10 = playerIn.prevPosZ + playerIn.posZ - playerIn.prevPosZ;
/*  447 */     Vec3 var12 = new Vec3(var6, var8, var10);
/*  448 */     float var13 = MathHelper.cos(-var5 * 0.017453292F - 3.1415927F);
/*  449 */     float var14 = MathHelper.sin(-var5 * 0.017453292F - 3.1415927F);
/*  450 */     float var15 = -MathHelper.cos(-var4 * 0.017453292F);
/*  451 */     float var16 = MathHelper.sin(-var4 * 0.017453292F);
/*  452 */     float var17 = var14 * var15;
/*  453 */     float var19 = var13 * var15;
/*  454 */     double var20 = 5.0D;
/*  455 */     Vec3 var22 = var12.addVector(var17 * var20, var16 * var20, var19 * var20);
/*  456 */     return worldIn.rayTraceBlocks(var12, var22, useLiquids, !useLiquids, false);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getItemEnchantability() {
/*  464 */     return 0;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void getSubItems(Item itemIn, CreativeTabs tab, List<ItemStack> subItems) {
/*  474 */     subItems.add(new ItemStack(itemIn, 1, 0));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public CreativeTabs getCreativeTab() {
/*  482 */     return this.tabToDisplayOn;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Item setCreativeTab(CreativeTabs tab) {
/*  490 */     this.tabToDisplayOn = tab;
/*  491 */     return this;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean canItemEditBlocks() {
/*  500 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
/*  511 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Multimap getItemAttributeModifiers() {
/*  519 */     return (Multimap)HashMultimap.create();
/*      */   }
/*      */ 
/*      */   
/*      */   public static void registerItems() {
/*  524 */     registerItemBlock(Blocks.stone, (new ItemMultiTexture(Blocks.stone, Blocks.stone, new Function()
/*      */           {
/*      */             private static final String __OBFID = "CL_00002178";
/*      */             
/*      */             public String apply(ItemStack stack) {
/*  529 */               return BlockStone.EnumType.getStateFromMeta(stack.getMetadata()).func_176644_c();
/*      */             }
/*      */             
/*      */             public Object apply(Object p_apply_1_) {
/*  533 */               return apply((ItemStack)p_apply_1_);
/*      */             }
/*  535 */           })).setUnlocalizedName("stone"));
/*  536 */     registerItemBlock((Block)Blocks.grass, new ItemColored((Block)Blocks.grass, false));
/*  537 */     registerItemBlock(Blocks.dirt, (new ItemMultiTexture(Blocks.dirt, Blocks.dirt, new Function()
/*      */           {
/*      */             private static final String __OBFID = "CL_00002169";
/*      */             
/*      */             public String apply(ItemStack stack) {
/*  542 */               return BlockDirt.DirtType.byMetadata(stack.getMetadata()).getUnlocalizedName();
/*      */             }
/*      */             
/*      */             public Object apply(Object p_apply_1_) {
/*  546 */               return apply((ItemStack)p_apply_1_);
/*      */             }
/*  548 */           })).setUnlocalizedName("dirt"));
/*  549 */     registerItemBlock(Blocks.cobblestone);
/*  550 */     registerItemBlock(Blocks.planks, (new ItemMultiTexture(Blocks.planks, Blocks.planks, new Function()
/*      */           {
/*      */             private static final String __OBFID = "CL_00002168";
/*      */             
/*      */             public String apply(ItemStack stack) {
/*  555 */               return BlockPlanks.EnumType.func_176837_a(stack.getMetadata()).func_176840_c();
/*      */             }
/*      */             
/*      */             public Object apply(Object p_apply_1_) {
/*  559 */               return apply((ItemStack)p_apply_1_);
/*      */             }
/*  561 */           })).setUnlocalizedName("wood"));
/*  562 */     registerItemBlock(Blocks.sapling, (new ItemMultiTexture(Blocks.sapling, Blocks.sapling, new Function()
/*      */           {
/*      */             private static final String __OBFID = "CL_00002167";
/*      */             
/*      */             public String apply(ItemStack stack) {
/*  567 */               return BlockPlanks.EnumType.func_176837_a(stack.getMetadata()).func_176840_c();
/*      */             }
/*      */             
/*      */             public Object apply(Object p_apply_1_) {
/*  571 */               return apply((ItemStack)p_apply_1_);
/*      */             }
/*  573 */           })).setUnlocalizedName("sapling"));
/*  574 */     registerItemBlock(Blocks.bedrock);
/*  575 */     registerItemBlock((Block)Blocks.sand, (new ItemMultiTexture((Block)Blocks.sand, (Block)Blocks.sand, new Function()
/*      */           {
/*      */             private static final String __OBFID = "CL_00002166";
/*      */             
/*      */             public String apply(ItemStack stack) {
/*  580 */               return BlockSand.EnumType.func_176686_a(stack.getMetadata()).func_176685_d();
/*      */             }
/*      */             
/*      */             public Object apply(Object p_apply_1_) {
/*  584 */               return apply((ItemStack)p_apply_1_);
/*      */             }
/*  586 */           })).setUnlocalizedName("sand"));
/*  587 */     registerItemBlock(Blocks.gravel);
/*  588 */     registerItemBlock(Blocks.gold_ore);
/*  589 */     registerItemBlock(Blocks.iron_ore);
/*  590 */     registerItemBlock(Blocks.coal_ore);
/*  591 */     registerItemBlock(Blocks.log, (new ItemMultiTexture(Blocks.log, Blocks.log, new Function()
/*      */           {
/*      */             private static final String __OBFID = "CL_00002165";
/*      */             
/*      */             public String apply(ItemStack stack) {
/*  596 */               return BlockPlanks.EnumType.func_176837_a(stack.getMetadata()).func_176840_c();
/*      */             }
/*      */             
/*      */             public Object apply(Object p_apply_1_) {
/*  600 */               return apply((ItemStack)p_apply_1_);
/*      */             }
/*  602 */           })).setUnlocalizedName("log"));
/*  603 */     registerItemBlock(Blocks.log2, (new ItemMultiTexture(Blocks.log2, Blocks.log2, new Function()
/*      */           {
/*      */             private static final String __OBFID = "CL_00002164";
/*      */             
/*      */             public String apply(ItemStack stack) {
/*  608 */               return BlockPlanks.EnumType.func_176837_a(stack.getMetadata() + 4).func_176840_c();
/*      */             }
/*      */             
/*      */             public Object apply(Object p_apply_1_) {
/*  612 */               return apply((ItemStack)p_apply_1_);
/*      */             }
/*  614 */           })).setUnlocalizedName("log"));
/*  615 */     registerItemBlock((Block)Blocks.leaves, (new ItemLeaves(Blocks.leaves)).setUnlocalizedName("leaves"));
/*  616 */     registerItemBlock((Block)Blocks.leaves2, (new ItemLeaves(Blocks.leaves2)).setUnlocalizedName("leaves"));
/*  617 */     registerItemBlock(Blocks.sponge, (new ItemMultiTexture(Blocks.sponge, Blocks.sponge, new Function()
/*      */           {
/*      */             private static final String __OBFID = "CL_00002163";
/*      */             
/*      */             public String apply(ItemStack stack) {
/*  622 */               return ((stack.getMetadata() & 0x1) == 1) ? "wet" : "dry";
/*      */             }
/*      */             
/*      */             public Object apply(Object p_apply_1_) {
/*  626 */               return apply((ItemStack)p_apply_1_);
/*      */             }
/*  628 */           })).setUnlocalizedName("sponge"));
/*  629 */     registerItemBlock(Blocks.glass);
/*  630 */     registerItemBlock(Blocks.lapis_ore);
/*  631 */     registerItemBlock(Blocks.lapis_block);
/*  632 */     registerItemBlock(Blocks.dispenser);
/*  633 */     registerItemBlock(Blocks.sandstone, (new ItemMultiTexture(Blocks.sandstone, Blocks.sandstone, new Function()
/*      */           {
/*      */             private static final String __OBFID = "CL_00002162";
/*      */             
/*      */             public String apply(ItemStack stack) {
/*  638 */               return BlockSandStone.EnumType.func_176673_a(stack.getMetadata()).func_176676_c();
/*      */             }
/*      */             
/*      */             public Object apply(Object p_apply_1_) {
/*  642 */               return apply((ItemStack)p_apply_1_);
/*      */             }
/*  644 */           })).setUnlocalizedName("sandStone"));
/*  645 */     registerItemBlock(Blocks.noteblock);
/*  646 */     registerItemBlock(Blocks.golden_rail);
/*  647 */     registerItemBlock(Blocks.detector_rail);
/*  648 */     registerItemBlock((Block)Blocks.sticky_piston, new ItemPiston((Block)Blocks.sticky_piston));
/*  649 */     registerItemBlock(Blocks.web);
/*  650 */     registerItemBlock((Block)Blocks.tallgrass, (new ItemColored((Block)Blocks.tallgrass, true)).func_150943_a(new String[] { "shrub", "grass", "fern" }));
/*  651 */     registerItemBlock((Block)Blocks.deadbush);
/*  652 */     registerItemBlock((Block)Blocks.piston, new ItemPiston((Block)Blocks.piston));
/*  653 */     registerItemBlock(Blocks.wool, (new ItemCloth(Blocks.wool)).setUnlocalizedName("cloth"));
/*  654 */     registerItemBlock((Block)Blocks.yellow_flower, (new ItemMultiTexture((Block)Blocks.yellow_flower, (Block)Blocks.yellow_flower, new Function()
/*      */           {
/*      */             private static final String __OBFID = "CL_00002177";
/*      */             
/*      */             public String apply(ItemStack stack) {
/*  659 */               return BlockFlower.EnumFlowerType.func_176967_a(BlockFlower.EnumFlowerColor.YELLOW, stack.getMetadata()).func_176963_d();
/*      */             }
/*      */             
/*      */             public Object apply(Object p_apply_1_) {
/*  663 */               return apply((ItemStack)p_apply_1_);
/*      */             }
/*  665 */           })).setUnlocalizedName("flower"));
/*  666 */     registerItemBlock((Block)Blocks.red_flower, (new ItemMultiTexture((Block)Blocks.red_flower, (Block)Blocks.red_flower, new Function()
/*      */           {
/*      */             private static final String __OBFID = "CL_00002176";
/*      */             
/*      */             public String apply(ItemStack stack) {
/*  671 */               return BlockFlower.EnumFlowerType.func_176967_a(BlockFlower.EnumFlowerColor.RED, stack.getMetadata()).func_176963_d();
/*      */             }
/*      */             
/*      */             public Object apply(Object p_apply_1_) {
/*  675 */               return apply((ItemStack)p_apply_1_);
/*      */             }
/*  677 */           })).setUnlocalizedName("rose"));
/*  678 */     registerItemBlock((Block)Blocks.brown_mushroom);
/*  679 */     registerItemBlock((Block)Blocks.red_mushroom);
/*  680 */     registerItemBlock(Blocks.gold_block);
/*  681 */     registerItemBlock(Blocks.iron_block);
/*  682 */     registerItemBlock((Block)Blocks.stone_slab, (new ItemSlab((Block)Blocks.stone_slab, Blocks.stone_slab, Blocks.double_stone_slab)).setUnlocalizedName("stoneSlab"));
/*  683 */     registerItemBlock(Blocks.brick_block);
/*  684 */     registerItemBlock(Blocks.tnt);
/*  685 */     registerItemBlock(Blocks.bookshelf);
/*  686 */     registerItemBlock(Blocks.mossy_cobblestone);
/*  687 */     registerItemBlock(Blocks.obsidian);
/*  688 */     registerItemBlock(Blocks.torch);
/*  689 */     registerItemBlock(Blocks.mob_spawner);
/*  690 */     registerItemBlock(Blocks.oak_stairs);
/*  691 */     registerItemBlock((Block)Blocks.chest);
/*  692 */     registerItemBlock(Blocks.diamond_ore);
/*  693 */     registerItemBlock(Blocks.diamond_block);
/*  694 */     registerItemBlock(Blocks.crafting_table);
/*  695 */     registerItemBlock(Blocks.farmland);
/*  696 */     registerItemBlock(Blocks.furnace);
/*  697 */     registerItemBlock(Blocks.lit_furnace);
/*  698 */     registerItemBlock(Blocks.ladder);
/*  699 */     registerItemBlock(Blocks.rail);
/*  700 */     registerItemBlock(Blocks.stone_stairs);
/*  701 */     registerItemBlock(Blocks.lever);
/*  702 */     registerItemBlock(Blocks.stone_pressure_plate);
/*  703 */     registerItemBlock(Blocks.wooden_pressure_plate);
/*  704 */     registerItemBlock(Blocks.redstone_ore);
/*  705 */     registerItemBlock(Blocks.redstone_torch);
/*  706 */     registerItemBlock(Blocks.stone_button);
/*  707 */     registerItemBlock(Blocks.snow_layer, new ItemSnow(Blocks.snow_layer));
/*  708 */     registerItemBlock(Blocks.ice);
/*  709 */     registerItemBlock(Blocks.snow);
/*  710 */     registerItemBlock((Block)Blocks.cactus);
/*  711 */     registerItemBlock(Blocks.clay);
/*  712 */     registerItemBlock(Blocks.jukebox);
/*  713 */     registerItemBlock(Blocks.oak_fence);
/*  714 */     registerItemBlock(Blocks.spruce_fence);
/*  715 */     registerItemBlock(Blocks.birch_fence);
/*  716 */     registerItemBlock(Blocks.jungle_fence);
/*  717 */     registerItemBlock(Blocks.dark_oak_fence);
/*  718 */     registerItemBlock(Blocks.acacia_fence);
/*  719 */     registerItemBlock(Blocks.pumpkin);
/*  720 */     registerItemBlock(Blocks.netherrack);
/*  721 */     registerItemBlock(Blocks.soul_sand);
/*  722 */     registerItemBlock(Blocks.glowstone);
/*  723 */     registerItemBlock(Blocks.lit_pumpkin);
/*  724 */     registerItemBlock(Blocks.trapdoor);
/*  725 */     registerItemBlock(Blocks.monster_egg, (new ItemMultiTexture(Blocks.monster_egg, Blocks.monster_egg, new Function()
/*      */           {
/*      */             private static final String __OBFID = "CL_00002175";
/*      */             
/*      */             public String apply(ItemStack stack) {
/*  730 */               return BlockSilverfish.EnumType.func_176879_a(stack.getMetadata()).func_176882_c();
/*      */             }
/*      */             
/*      */             public Object apply(Object p_apply_1_) {
/*  734 */               return apply((ItemStack)p_apply_1_);
/*      */             }
/*  736 */           })).setUnlocalizedName("monsterStoneEgg"));
/*  737 */     registerItemBlock(Blocks.stonebrick, (new ItemMultiTexture(Blocks.stonebrick, Blocks.stonebrick, new Function()
/*      */           {
/*      */             private static final String __OBFID = "CL_00002174";
/*      */             
/*      */             public String apply(ItemStack stack) {
/*  742 */               return BlockStoneBrick.EnumType.getStateFromMeta(stack.getMetadata()).getVariantName();
/*      */             }
/*      */             
/*      */             public Object apply(Object p_apply_1_) {
/*  746 */               return apply((ItemStack)p_apply_1_);
/*      */             }
/*  748 */           })).setUnlocalizedName("stonebricksmooth"));
/*  749 */     registerItemBlock(Blocks.brown_mushroom_block);
/*  750 */     registerItemBlock(Blocks.red_mushroom_block);
/*  751 */     registerItemBlock(Blocks.iron_bars);
/*  752 */     registerItemBlock(Blocks.glass_pane);
/*  753 */     registerItemBlock(Blocks.melon_block);
/*  754 */     registerItemBlock(Blocks.vine, new ItemColored(Blocks.vine, false));
/*  755 */     registerItemBlock(Blocks.oak_fence_gate);
/*  756 */     registerItemBlock(Blocks.spruce_fence_gate);
/*  757 */     registerItemBlock(Blocks.birch_fence_gate);
/*  758 */     registerItemBlock(Blocks.jungle_fence_gate);
/*  759 */     registerItemBlock(Blocks.dark_oak_fence_gate);
/*  760 */     registerItemBlock(Blocks.acacia_fence_gate);
/*  761 */     registerItemBlock(Blocks.brick_stairs);
/*  762 */     registerItemBlock(Blocks.stone_brick_stairs);
/*  763 */     registerItemBlock((Block)Blocks.mycelium);
/*  764 */     registerItemBlock(Blocks.waterlily, new ItemLilyPad(Blocks.waterlily));
/*  765 */     registerItemBlock(Blocks.nether_brick);
/*  766 */     registerItemBlock(Blocks.nether_brick_fence);
/*  767 */     registerItemBlock(Blocks.nether_brick_stairs);
/*  768 */     registerItemBlock(Blocks.enchanting_table);
/*  769 */     registerItemBlock(Blocks.end_portal_frame);
/*  770 */     registerItemBlock(Blocks.end_stone);
/*  771 */     registerItemBlock(Blocks.dragon_egg);
/*  772 */     registerItemBlock(Blocks.redstone_lamp);
/*  773 */     registerItemBlock((Block)Blocks.wooden_slab, (new ItemSlab((Block)Blocks.wooden_slab, Blocks.wooden_slab, Blocks.double_wooden_slab)).setUnlocalizedName("woodSlab"));
/*  774 */     registerItemBlock(Blocks.sandstone_stairs);
/*  775 */     registerItemBlock(Blocks.emerald_ore);
/*  776 */     registerItemBlock(Blocks.ender_chest);
/*  777 */     registerItemBlock((Block)Blocks.tripwire_hook);
/*  778 */     registerItemBlock(Blocks.emerald_block);
/*  779 */     registerItemBlock(Blocks.spruce_stairs);
/*  780 */     registerItemBlock(Blocks.birch_stairs);
/*  781 */     registerItemBlock(Blocks.jungle_stairs);
/*  782 */     registerItemBlock(Blocks.command_block);
/*  783 */     registerItemBlock((Block)Blocks.beacon);
/*  784 */     registerItemBlock(Blocks.cobblestone_wall, (new ItemMultiTexture(Blocks.cobblestone_wall, Blocks.cobblestone_wall, new Function()
/*      */           {
/*      */             private static final String __OBFID = "CL_00002173";
/*      */             
/*      */             public String apply(ItemStack stack) {
/*  789 */               return BlockWall.EnumType.func_176660_a(stack.getMetadata()).func_176659_c();
/*      */             }
/*      */             
/*      */             public Object apply(Object p_apply_1_) {
/*  793 */               return apply((ItemStack)p_apply_1_);
/*      */             }
/*  795 */           })).setUnlocalizedName("cobbleWall"));
/*  796 */     registerItemBlock(Blocks.wooden_button);
/*  797 */     registerItemBlock(Blocks.anvil, (new ItemAnvilBlock(Blocks.anvil)).setUnlocalizedName("anvil"));
/*  798 */     registerItemBlock(Blocks.trapped_chest);
/*  799 */     registerItemBlock(Blocks.light_weighted_pressure_plate);
/*  800 */     registerItemBlock(Blocks.heavy_weighted_pressure_plate);
/*  801 */     registerItemBlock((Block)Blocks.daylight_detector);
/*  802 */     registerItemBlock(Blocks.redstone_block);
/*  803 */     registerItemBlock(Blocks.quartz_ore);
/*  804 */     registerItemBlock((Block)Blocks.hopper);
/*  805 */     registerItemBlock(Blocks.quartz_block, (new ItemMultiTexture(Blocks.quartz_block, Blocks.quartz_block, new String[] { "default", "chiseled", "lines" })).setUnlocalizedName("quartzBlock"));
/*  806 */     registerItemBlock(Blocks.quartz_stairs);
/*  807 */     registerItemBlock(Blocks.activator_rail);
/*  808 */     registerItemBlock(Blocks.dropper);
/*  809 */     registerItemBlock(Blocks.stained_hardened_clay, (new ItemCloth(Blocks.stained_hardened_clay)).setUnlocalizedName("clayHardenedStained"));
/*  810 */     registerItemBlock(Blocks.barrier);
/*  811 */     registerItemBlock(Blocks.iron_trapdoor);
/*  812 */     registerItemBlock(Blocks.hay_block);
/*  813 */     registerItemBlock(Blocks.carpet, (new ItemCloth(Blocks.carpet)).setUnlocalizedName("woolCarpet"));
/*  814 */     registerItemBlock(Blocks.hardened_clay);
/*  815 */     registerItemBlock(Blocks.coal_block);
/*  816 */     registerItemBlock(Blocks.packed_ice);
/*  817 */     registerItemBlock(Blocks.acacia_stairs);
/*  818 */     registerItemBlock(Blocks.dark_oak_stairs);
/*  819 */     registerItemBlock(Blocks.slime_block);
/*  820 */     registerItemBlock((Block)Blocks.double_plant, (new ItemDoublePlant((Block)Blocks.double_plant, (Block)Blocks.double_plant, new Function()
/*      */           {
/*      */             private static final String __OBFID = "CL_00002172";
/*      */             
/*      */             public String apply(ItemStack stack) {
/*  825 */               return BlockDoublePlant.EnumPlantType.func_176938_a(stack.getMetadata()).func_176939_c();
/*      */             }
/*      */             
/*      */             public Object apply(Object p_apply_1_) {
/*  829 */               return apply((ItemStack)p_apply_1_);
/*      */             }
/*  831 */           })).setUnlocalizedName("doublePlant"));
/*  832 */     registerItemBlock((Block)Blocks.stained_glass, (new ItemCloth((Block)Blocks.stained_glass)).setUnlocalizedName("stainedGlass"));
/*  833 */     registerItemBlock((Block)Blocks.stained_glass_pane, (new ItemCloth((Block)Blocks.stained_glass_pane)).setUnlocalizedName("stainedGlassPane"));
/*  834 */     registerItemBlock(Blocks.prismarine, (new ItemMultiTexture(Blocks.prismarine, Blocks.prismarine, new Function()
/*      */           {
/*      */             private static final String __OBFID = "CL_00002171";
/*      */             
/*      */             public String apply(ItemStack stack) {
/*  839 */               return BlockPrismarine.EnumType.func_176810_a(stack.getMetadata()).func_176809_c();
/*      */             }
/*      */             
/*      */             public Object apply(Object p_apply_1_) {
/*  843 */               return apply((ItemStack)p_apply_1_);
/*      */             }
/*  845 */           })).setUnlocalizedName("prismarine"));
/*  846 */     registerItemBlock(Blocks.sea_lantern);
/*  847 */     registerItemBlock(Blocks.red_sandstone, (new ItemMultiTexture(Blocks.red_sandstone, Blocks.red_sandstone, new Function()
/*      */           {
/*      */             private static final String __OBFID = "CL_00002170";
/*      */             
/*      */             public String apply(ItemStack stack) {
/*  852 */               return BlockRedSandstone.EnumType.func_176825_a(stack.getMetadata()).func_176828_c();
/*      */             }
/*      */             
/*      */             public Object apply(Object p_apply_1_) {
/*  856 */               return apply((ItemStack)p_apply_1_);
/*      */             }
/*  858 */           })).setUnlocalizedName("redSandStone"));
/*  859 */     registerItemBlock(Blocks.red_sandstone_stairs);
/*  860 */     registerItemBlock((Block)Blocks.stone_slab2, (new ItemSlab((Block)Blocks.stone_slab2, Blocks.stone_slab2, Blocks.double_stone_slab2)).setUnlocalizedName("stoneSlab2"));
/*  861 */     registerItem(256, "iron_shovel", (new ItemSpade(ToolMaterial.IRON)).setUnlocalizedName("shovelIron"));
/*  862 */     registerItem(257, "iron_pickaxe", (new ItemPickaxe(ToolMaterial.IRON)).setUnlocalizedName("pickaxeIron"));
/*  863 */     registerItem(258, "iron_axe", (new ItemAxe(ToolMaterial.IRON)).setUnlocalizedName("hatchetIron"));
/*  864 */     registerItem(259, "flint_and_steel", (new ItemFlintAndSteel()).setUnlocalizedName("flintAndSteel"));
/*  865 */     registerItem(260, "apple", (new ItemFood(4, 0.3F, false)).setUnlocalizedName("apple"));
/*  866 */     registerItem(261, "bow", (new ItemBow()).setUnlocalizedName("bow"));
/*  867 */     registerItem(262, "arrow", (new Item()).setUnlocalizedName("arrow").setCreativeTab(CreativeTabs.tabCombat));
/*  868 */     registerItem(263, "coal", (new ItemCoal()).setUnlocalizedName("coal"));
/*  869 */     registerItem(264, "diamond", (new Item()).setUnlocalizedName("diamond").setCreativeTab(CreativeTabs.tabMaterials));
/*  870 */     registerItem(265, "iron_ingot", (new Item()).setUnlocalizedName("ingotIron").setCreativeTab(CreativeTabs.tabMaterials));
/*  871 */     registerItem(266, "gold_ingot", (new Item()).setUnlocalizedName("ingotGold").setCreativeTab(CreativeTabs.tabMaterials));
/*  872 */     registerItem(267, "iron_sword", (new ItemSword(ToolMaterial.IRON)).setUnlocalizedName("swordIron"));
/*  873 */     registerItem(268, "wooden_sword", (new ItemSword(ToolMaterial.WOOD)).setUnlocalizedName("swordWood"));
/*  874 */     registerItem(269, "wooden_shovel", (new ItemSpade(ToolMaterial.WOOD)).setUnlocalizedName("shovelWood"));
/*  875 */     registerItem(270, "wooden_pickaxe", (new ItemPickaxe(ToolMaterial.WOOD)).setUnlocalizedName("pickaxeWood"));
/*  876 */     registerItem(271, "wooden_axe", (new ItemAxe(ToolMaterial.WOOD)).setUnlocalizedName("hatchetWood"));
/*  877 */     registerItem(272, "stone_sword", (new ItemSword(ToolMaterial.STONE)).setUnlocalizedName("swordStone"));
/*  878 */     registerItem(273, "stone_shovel", (new ItemSpade(ToolMaterial.STONE)).setUnlocalizedName("shovelStone"));
/*  879 */     registerItem(274, "stone_pickaxe", (new ItemPickaxe(ToolMaterial.STONE)).setUnlocalizedName("pickaxeStone"));
/*  880 */     registerItem(275, "stone_axe", (new ItemAxe(ToolMaterial.STONE)).setUnlocalizedName("hatchetStone"));
/*  881 */     registerItem(276, "diamond_sword", (new ItemSword(ToolMaterial.EMERALD)).setUnlocalizedName("swordDiamond"));
/*  882 */     registerItem(277, "diamond_shovel", (new ItemSpade(ToolMaterial.EMERALD)).setUnlocalizedName("shovelDiamond"));
/*  883 */     registerItem(278, "diamond_pickaxe", (new ItemPickaxe(ToolMaterial.EMERALD)).setUnlocalizedName("pickaxeDiamond"));
/*  884 */     registerItem(279, "diamond_axe", (new ItemAxe(ToolMaterial.EMERALD)).setUnlocalizedName("hatchetDiamond"));
/*  885 */     registerItem(280, "stick", (new Item()).setFull3D().setUnlocalizedName("stick").setCreativeTab(CreativeTabs.tabMaterials));
/*  886 */     registerItem(281, "bowl", (new Item()).setUnlocalizedName("bowl").setCreativeTab(CreativeTabs.tabMaterials));
/*  887 */     registerItem(282, "mushroom_stew", (new ItemSoup(6)).setUnlocalizedName("mushroomStew"));
/*  888 */     registerItem(283, "golden_sword", (new ItemSword(ToolMaterial.GOLD)).setUnlocalizedName("swordGold"));
/*  889 */     registerItem(284, "golden_shovel", (new ItemSpade(ToolMaterial.GOLD)).setUnlocalizedName("shovelGold"));
/*  890 */     registerItem(285, "golden_pickaxe", (new ItemPickaxe(ToolMaterial.GOLD)).setUnlocalizedName("pickaxeGold"));
/*  891 */     registerItem(286, "golden_axe", (new ItemAxe(ToolMaterial.GOLD)).setUnlocalizedName("hatchetGold"));
/*  892 */     registerItem(287, "string", (new ItemReed(Blocks.tripwire)).setUnlocalizedName("string").setCreativeTab(CreativeTabs.tabMaterials));
/*  893 */     registerItem(288, "feather", (new Item()).setUnlocalizedName("feather").setCreativeTab(CreativeTabs.tabMaterials));
/*  894 */     registerItem(289, "gunpowder", (new Item()).setUnlocalizedName("sulphur").setPotionEffect(PotionHelper.gunpowderEffect).setCreativeTab(CreativeTabs.tabMaterials));
/*  895 */     registerItem(290, "wooden_hoe", (new ItemHoe(ToolMaterial.WOOD)).setUnlocalizedName("hoeWood"));
/*  896 */     registerItem(291, "stone_hoe", (new ItemHoe(ToolMaterial.STONE)).setUnlocalizedName("hoeStone"));
/*  897 */     registerItem(292, "iron_hoe", (new ItemHoe(ToolMaterial.IRON)).setUnlocalizedName("hoeIron"));
/*  898 */     registerItem(293, "diamond_hoe", (new ItemHoe(ToolMaterial.EMERALD)).setUnlocalizedName("hoeDiamond"));
/*  899 */     registerItem(294, "golden_hoe", (new ItemHoe(ToolMaterial.GOLD)).setUnlocalizedName("hoeGold"));
/*  900 */     registerItem(295, "wheat_seeds", (new ItemSeeds(Blocks.wheat, Blocks.farmland)).setUnlocalizedName("seeds"));
/*  901 */     registerItem(296, "wheat", (new Item()).setUnlocalizedName("wheat").setCreativeTab(CreativeTabs.tabMaterials));
/*  902 */     registerItem(297, "bread", (new ItemFood(5, 0.6F, false)).setUnlocalizedName("bread"));
/*  903 */     registerItem(298, "leather_helmet", (new ItemArmor(ItemArmor.ArmorMaterial.LEATHER, 0, 0)).setUnlocalizedName("helmetCloth"));
/*  904 */     registerItem(299, "leather_chestplate", (new ItemArmor(ItemArmor.ArmorMaterial.LEATHER, 0, 1)).setUnlocalizedName("chestplateCloth"));
/*  905 */     registerItem(300, "leather_leggings", (new ItemArmor(ItemArmor.ArmorMaterial.LEATHER, 0, 2)).setUnlocalizedName("leggingsCloth"));
/*  906 */     registerItem(301, "leather_boots", (new ItemArmor(ItemArmor.ArmorMaterial.LEATHER, 0, 3)).setUnlocalizedName("bootsCloth"));
/*  907 */     registerItem(302, "chainmail_helmet", (new ItemArmor(ItemArmor.ArmorMaterial.CHAIN, 1, 0)).setUnlocalizedName("helmetChain"));
/*  908 */     registerItem(303, "chainmail_chestplate", (new ItemArmor(ItemArmor.ArmorMaterial.CHAIN, 1, 1)).setUnlocalizedName("chestplateChain"));
/*  909 */     registerItem(304, "chainmail_leggings", (new ItemArmor(ItemArmor.ArmorMaterial.CHAIN, 1, 2)).setUnlocalizedName("leggingsChain"));
/*  910 */     registerItem(305, "chainmail_boots", (new ItemArmor(ItemArmor.ArmorMaterial.CHAIN, 1, 3)).setUnlocalizedName("bootsChain"));
/*  911 */     registerItem(306, "iron_helmet", (new ItemArmor(ItemArmor.ArmorMaterial.IRON, 2, 0)).setUnlocalizedName("helmetIron"));
/*  912 */     registerItem(307, "iron_chestplate", (new ItemArmor(ItemArmor.ArmorMaterial.IRON, 2, 1)).setUnlocalizedName("chestplateIron"));
/*  913 */     registerItem(308, "iron_leggings", (new ItemArmor(ItemArmor.ArmorMaterial.IRON, 2, 2)).setUnlocalizedName("leggingsIron"));
/*  914 */     registerItem(309, "iron_boots", (new ItemArmor(ItemArmor.ArmorMaterial.IRON, 2, 3)).setUnlocalizedName("bootsIron"));
/*  915 */     registerItem(310, "diamond_helmet", (new ItemArmor(ItemArmor.ArmorMaterial.DIAMOND, 3, 0)).setUnlocalizedName("helmetDiamond"));
/*  916 */     registerItem(311, "diamond_chestplate", (new ItemArmor(ItemArmor.ArmorMaterial.DIAMOND, 3, 1)).setUnlocalizedName("chestplateDiamond"));
/*  917 */     registerItem(312, "diamond_leggings", (new ItemArmor(ItemArmor.ArmorMaterial.DIAMOND, 3, 2)).setUnlocalizedName("leggingsDiamond"));
/*  918 */     registerItem(313, "diamond_boots", (new ItemArmor(ItemArmor.ArmorMaterial.DIAMOND, 3, 3)).setUnlocalizedName("bootsDiamond"));
/*  919 */     registerItem(314, "golden_helmet", (new ItemArmor(ItemArmor.ArmorMaterial.GOLD, 4, 0)).setUnlocalizedName("helmetGold"));
/*  920 */     registerItem(315, "golden_chestplate", (new ItemArmor(ItemArmor.ArmorMaterial.GOLD, 4, 1)).setUnlocalizedName("chestplateGold"));
/*  921 */     registerItem(316, "golden_leggings", (new ItemArmor(ItemArmor.ArmorMaterial.GOLD, 4, 2)).setUnlocalizedName("leggingsGold"));
/*  922 */     registerItem(317, "golden_boots", (new ItemArmor(ItemArmor.ArmorMaterial.GOLD, 4, 3)).setUnlocalizedName("bootsGold"));
/*  923 */     registerItem(318, "flint", (new Item()).setUnlocalizedName("flint").setCreativeTab(CreativeTabs.tabMaterials));
/*  924 */     registerItem(319, "porkchop", (new ItemFood(3, 0.3F, true)).setUnlocalizedName("porkchopRaw"));
/*  925 */     registerItem(320, "cooked_porkchop", (new ItemFood(8, 0.8F, true)).setUnlocalizedName("porkchopCooked"));
/*  926 */     registerItem(321, "painting", (new ItemHangingEntity(EntityPainting.class)).setUnlocalizedName("painting"));
/*  927 */     registerItem(322, "golden_apple", (new ItemAppleGold(4, 1.2F, false)).setAlwaysEdible().setPotionEffect(Potion.regeneration.id, 5, 1, 1.0F).setUnlocalizedName("appleGold"));
/*  928 */     registerItem(323, "sign", (new ItemSign()).setUnlocalizedName("sign"));
/*  929 */     registerItem(324, "wooden_door", (new ItemDoor(Blocks.oak_door)).setUnlocalizedName("doorOak"));
/*  930 */     Item var0 = (new ItemBucket(Blocks.air)).setUnlocalizedName("bucket").setMaxStackSize(16);
/*  931 */     registerItem(325, "bucket", var0);
/*  932 */     registerItem(326, "water_bucket", (new ItemBucket((Block)Blocks.flowing_water)).setUnlocalizedName("bucketWater").setContainerItem(var0));
/*  933 */     registerItem(327, "lava_bucket", (new ItemBucket((Block)Blocks.flowing_lava)).setUnlocalizedName("bucketLava").setContainerItem(var0));
/*  934 */     registerItem(328, "minecart", (new ItemMinecart(EntityMinecart.EnumMinecartType.RIDEABLE)).setUnlocalizedName("minecart"));
/*  935 */     registerItem(329, "saddle", (new ItemSaddle()).setUnlocalizedName("saddle"));
/*  936 */     registerItem(330, "iron_door", (new ItemDoor(Blocks.iron_door)).setUnlocalizedName("doorIron"));
/*  937 */     registerItem(331, "redstone", (new ItemRedstone()).setUnlocalizedName("redstone").setPotionEffect(PotionHelper.redstoneEffect));
/*  938 */     registerItem(332, "snowball", (new ItemSnowball()).setUnlocalizedName("snowball"));
/*  939 */     registerItem(333, "boat", (new ItemBoat()).setUnlocalizedName("boat"));
/*  940 */     registerItem(334, "leather", (new Item()).setUnlocalizedName("leather").setCreativeTab(CreativeTabs.tabMaterials));
/*  941 */     registerItem(335, "milk_bucket", (new ItemBucketMilk()).setUnlocalizedName("milk").setContainerItem(var0));
/*  942 */     registerItem(336, "brick", (new Item()).setUnlocalizedName("brick").setCreativeTab(CreativeTabs.tabMaterials));
/*  943 */     registerItem(337, "clay_ball", (new Item()).setUnlocalizedName("clay").setCreativeTab(CreativeTabs.tabMaterials));
/*  944 */     registerItem(338, "reeds", (new ItemReed((Block)Blocks.reeds)).setUnlocalizedName("reeds").setCreativeTab(CreativeTabs.tabMaterials));
/*  945 */     registerItem(339, "paper", (new Item()).setUnlocalizedName("paper").setCreativeTab(CreativeTabs.tabMisc));
/*  946 */     registerItem(340, "book", (new ItemBook()).setUnlocalizedName("book").setCreativeTab(CreativeTabs.tabMisc));
/*  947 */     registerItem(341, "slime_ball", (new Item()).setUnlocalizedName("slimeball").setCreativeTab(CreativeTabs.tabMisc));
/*  948 */     registerItem(342, "chest_minecart", (new ItemMinecart(EntityMinecart.EnumMinecartType.CHEST)).setUnlocalizedName("minecartChest"));
/*  949 */     registerItem(343, "furnace_minecart", (new ItemMinecart(EntityMinecart.EnumMinecartType.FURNACE)).setUnlocalizedName("minecartFurnace"));
/*  950 */     registerItem(344, "egg", (new ItemEgg()).setUnlocalizedName("egg"));
/*  951 */     registerItem(345, "compass", (new Item()).setUnlocalizedName("compass").setCreativeTab(CreativeTabs.tabTools));
/*  952 */     registerItem(346, "fishing_rod", (new ItemFishingRod()).setUnlocalizedName("fishingRod"));
/*  953 */     registerItem(347, "clock", (new Item()).setUnlocalizedName("clock").setCreativeTab(CreativeTabs.tabTools));
/*  954 */     registerItem(348, "glowstone_dust", (new Item()).setUnlocalizedName("yellowDust").setPotionEffect(PotionHelper.glowstoneEffect).setCreativeTab(CreativeTabs.tabMaterials));
/*  955 */     registerItem(349, "fish", (new ItemFishFood(false)).setUnlocalizedName("fish").setHasSubtypes(true));
/*  956 */     registerItem(350, "cooked_fish", (new ItemFishFood(true)).setUnlocalizedName("fish").setHasSubtypes(true));
/*  957 */     registerItem(351, "dye", (new ItemDye()).setUnlocalizedName("dyePowder"));
/*  958 */     registerItem(352, "bone", (new Item()).setUnlocalizedName("bone").setFull3D().setCreativeTab(CreativeTabs.tabMisc));
/*  959 */     registerItem(353, "sugar", (new Item()).setUnlocalizedName("sugar").setPotionEffect(PotionHelper.sugarEffect).setCreativeTab(CreativeTabs.tabMaterials));
/*  960 */     registerItem(354, "cake", (new ItemReed(Blocks.cake)).setMaxStackSize(1).setUnlocalizedName("cake").setCreativeTab(CreativeTabs.tabFood));
/*  961 */     registerItem(355, "bed", (new ItemBed()).setMaxStackSize(1).setUnlocalizedName("bed"));
/*  962 */     registerItem(356, "repeater", (new ItemReed((Block)Blocks.unpowered_repeater)).setUnlocalizedName("diode").setCreativeTab(CreativeTabs.tabRedstone));
/*  963 */     registerItem(357, "cookie", (new ItemFood(2, 0.1F, false)).setUnlocalizedName("cookie"));
/*  964 */     registerItem(358, "filled_map", (new ItemMap()).setUnlocalizedName("map"));
/*  965 */     registerItem(359, "shears", (new ItemShears()).setUnlocalizedName("shears"));
/*  966 */     registerItem(360, "melon", (new ItemFood(2, 0.3F, false)).setUnlocalizedName("melon"));
/*  967 */     registerItem(361, "pumpkin_seeds", (new ItemSeeds(Blocks.pumpkin_stem, Blocks.farmland)).setUnlocalizedName("seeds_pumpkin"));
/*  968 */     registerItem(362, "melon_seeds", (new ItemSeeds(Blocks.melon_stem, Blocks.farmland)).setUnlocalizedName("seeds_melon"));
/*  969 */     registerItem(363, "beef", (new ItemFood(3, 0.3F, true)).setUnlocalizedName("beefRaw"));
/*  970 */     registerItem(364, "cooked_beef", (new ItemFood(8, 0.8F, true)).setUnlocalizedName("beefCooked"));
/*  971 */     registerItem(365, "chicken", (new ItemFood(2, 0.3F, true)).setPotionEffect(Potion.hunger.id, 30, 0, 0.3F).setUnlocalizedName("chickenRaw"));
/*  972 */     registerItem(366, "cooked_chicken", (new ItemFood(6, 0.6F, true)).setUnlocalizedName("chickenCooked"));
/*  973 */     registerItem(367, "rotten_flesh", (new ItemFood(4, 0.1F, true)).setPotionEffect(Potion.hunger.id, 30, 0, 0.8F).setUnlocalizedName("rottenFlesh"));
/*  974 */     registerItem(368, "ender_pearl", (new ItemEnderPearl()).setUnlocalizedName("enderPearl"));
/*  975 */     registerItem(369, "blaze_rod", (new Item()).setUnlocalizedName("blazeRod").setCreativeTab(CreativeTabs.tabMaterials).setFull3D());
/*  976 */     registerItem(370, "ghast_tear", (new Item()).setUnlocalizedName("ghastTear").setPotionEffect("+0-1-2-3&4-4+13").setCreativeTab(CreativeTabs.tabBrewing));
/*  977 */     registerItem(371, "gold_nugget", (new Item()).setUnlocalizedName("goldNugget").setCreativeTab(CreativeTabs.tabMaterials));
/*  978 */     registerItem(372, "nether_wart", (new ItemSeeds(Blocks.nether_wart, Blocks.soul_sand)).setUnlocalizedName("netherStalkSeeds").setPotionEffect("+4"));
/*  979 */     registerItem(373, "potion", (new ItemPotion()).setUnlocalizedName("potion"));
/*  980 */     registerItem(374, "glass_bottle", (new ItemGlassBottle()).setUnlocalizedName("glassBottle"));
/*  981 */     registerItem(375, "spider_eye", (new ItemFood(2, 0.8F, false)).setPotionEffect(Potion.poison.id, 5, 0, 1.0F).setUnlocalizedName("spiderEye").setPotionEffect(PotionHelper.spiderEyeEffect));
/*  982 */     registerItem(376, "fermented_spider_eye", (new Item()).setUnlocalizedName("fermentedSpiderEye").setPotionEffect(PotionHelper.fermentedSpiderEyeEffect).setCreativeTab(CreativeTabs.tabBrewing));
/*  983 */     registerItem(377, "blaze_powder", (new Item()).setUnlocalizedName("blazePowder").setPotionEffect(PotionHelper.blazePowderEffect).setCreativeTab(CreativeTabs.tabBrewing));
/*  984 */     registerItem(378, "magma_cream", (new Item()).setUnlocalizedName("magmaCream").setPotionEffect(PotionHelper.magmaCreamEffect).setCreativeTab(CreativeTabs.tabBrewing));
/*  985 */     registerItem(379, "brewing_stand", (new ItemReed(Blocks.brewing_stand)).setUnlocalizedName("brewingStand").setCreativeTab(CreativeTabs.tabBrewing));
/*  986 */     registerItem(380, "cauldron", (new ItemReed((Block)Blocks.cauldron)).setUnlocalizedName("cauldron").setCreativeTab(CreativeTabs.tabBrewing));
/*  987 */     registerItem(381, "ender_eye", (new ItemEnderEye()).setUnlocalizedName("eyeOfEnder"));
/*  988 */     registerItem(382, "speckled_melon", (new Item()).setUnlocalizedName("speckledMelon").setPotionEffect(PotionHelper.speckledMelonEffect).setCreativeTab(CreativeTabs.tabBrewing));
/*  989 */     registerItem(383, "spawn_egg", (new ItemMonsterPlacer()).setUnlocalizedName("monsterPlacer"));
/*  990 */     registerItem(384, "experience_bottle", (new ItemExpBottle()).setUnlocalizedName("expBottle"));
/*  991 */     registerItem(385, "fire_charge", (new ItemFireball()).setUnlocalizedName("fireball"));
/*  992 */     registerItem(386, "writable_book", (new ItemWritableBook()).setUnlocalizedName("writingBook").setCreativeTab(CreativeTabs.tabMisc));
/*  993 */     registerItem(387, "written_book", (new ItemEditableBook()).setUnlocalizedName("writtenBook").setMaxStackSize(16));
/*  994 */     registerItem(388, "emerald", (new Item()).setUnlocalizedName("emerald").setCreativeTab(CreativeTabs.tabMaterials));
/*  995 */     registerItem(389, "item_frame", (new ItemHangingEntity(EntityItemFrame.class)).setUnlocalizedName("frame"));
/*  996 */     registerItem(390, "flower_pot", (new ItemReed(Blocks.flower_pot)).setUnlocalizedName("flowerPot").setCreativeTab(CreativeTabs.tabDecorations));
/*  997 */     registerItem(391, "carrot", (new ItemSeedFood(3, 0.6F, Blocks.carrots, Blocks.farmland)).setUnlocalizedName("carrots"));
/*  998 */     registerItem(392, "potato", (new ItemSeedFood(1, 0.3F, Blocks.potatoes, Blocks.farmland)).setUnlocalizedName("potato"));
/*  999 */     registerItem(393, "baked_potato", (new ItemFood(5, 0.6F, false)).setUnlocalizedName("potatoBaked"));
/* 1000 */     registerItem(394, "poisonous_potato", (new ItemFood(2, 0.3F, false)).setPotionEffect(Potion.poison.id, 5, 0, 0.6F).setUnlocalizedName("potatoPoisonous"));
/* 1001 */     registerItem(395, "map", (new ItemEmptyMap()).setUnlocalizedName("emptyMap"));
/* 1002 */     registerItem(396, "golden_carrot", (new ItemFood(6, 1.2F, false)).setUnlocalizedName("carrotGolden").setPotionEffect(PotionHelper.goldenCarrotEffect).setCreativeTab(CreativeTabs.tabBrewing));
/* 1003 */     registerItem(397, "skull", (new ItemSkull()).setUnlocalizedName("skull"));
/* 1004 */     registerItem(398, "carrot_on_a_stick", (new ItemCarrotOnAStick()).setUnlocalizedName("carrotOnAStick"));
/* 1005 */     registerItem(399, "nether_star", (new ItemSimpleFoiled()).setUnlocalizedName("netherStar").setCreativeTab(CreativeTabs.tabMaterials));
/* 1006 */     registerItem(400, "pumpkin_pie", (new ItemFood(8, 0.3F, false)).setUnlocalizedName("pumpkinPie").setCreativeTab(CreativeTabs.tabFood));
/* 1007 */     registerItem(401, "fireworks", (new ItemFirework()).setUnlocalizedName("fireworks"));
/* 1008 */     registerItem(402, "firework_charge", (new ItemFireworkCharge()).setUnlocalizedName("fireworksCharge").setCreativeTab(CreativeTabs.tabMisc));
/* 1009 */     registerItem(403, "enchanted_book", (new ItemEnchantedBook()).setMaxStackSize(1).setUnlocalizedName("enchantedBook"));
/* 1010 */     registerItem(404, "comparator", (new ItemReed((Block)Blocks.unpowered_comparator)).setUnlocalizedName("comparator").setCreativeTab(CreativeTabs.tabRedstone));
/* 1011 */     registerItem(405, "netherbrick", (new Item()).setUnlocalizedName("netherbrick").setCreativeTab(CreativeTabs.tabMaterials));
/* 1012 */     registerItem(406, "quartz", (new Item()).setUnlocalizedName("netherquartz").setCreativeTab(CreativeTabs.tabMaterials));
/* 1013 */     registerItem(407, "tnt_minecart", (new ItemMinecart(EntityMinecart.EnumMinecartType.TNT)).setUnlocalizedName("minecartTnt"));
/* 1014 */     registerItem(408, "hopper_minecart", (new ItemMinecart(EntityMinecart.EnumMinecartType.HOPPER)).setUnlocalizedName("minecartHopper"));
/* 1015 */     registerItem(409, "prismarine_shard", (new Item()).setUnlocalizedName("prismarineShard").setCreativeTab(CreativeTabs.tabMaterials));
/* 1016 */     registerItem(410, "prismarine_crystals", (new Item()).setUnlocalizedName("prismarineCrystals").setCreativeTab(CreativeTabs.tabMaterials));
/* 1017 */     registerItem(411, "rabbit", (new ItemFood(3, 0.3F, true)).setUnlocalizedName("rabbitRaw"));
/* 1018 */     registerItem(412, "cooked_rabbit", (new ItemFood(5, 0.6F, true)).setUnlocalizedName("rabbitCooked"));
/* 1019 */     registerItem(413, "rabbit_stew", (new ItemSoup(10)).setUnlocalizedName("rabbitStew"));
/* 1020 */     registerItem(414, "rabbit_foot", (new Item()).setUnlocalizedName("rabbitFoot").setPotionEffect(PotionHelper.field_179538_n).setCreativeTab(CreativeTabs.tabBrewing));
/* 1021 */     registerItem(415, "rabbit_hide", (new Item()).setUnlocalizedName("rabbitHide").setCreativeTab(CreativeTabs.tabMaterials));
/* 1022 */     registerItem(416, "armor_stand", (new ItemArmorStand()).setUnlocalizedName("armorStand").setMaxStackSize(16));
/* 1023 */     registerItem(417, "iron_horse_armor", (new Item()).setUnlocalizedName("horsearmormetal").setMaxStackSize(1).setCreativeTab(CreativeTabs.tabMisc));
/* 1024 */     registerItem(418, "golden_horse_armor", (new Item()).setUnlocalizedName("horsearmorgold").setMaxStackSize(1).setCreativeTab(CreativeTabs.tabMisc));
/* 1025 */     registerItem(419, "diamond_horse_armor", (new Item()).setUnlocalizedName("horsearmordiamond").setMaxStackSize(1).setCreativeTab(CreativeTabs.tabMisc));
/* 1026 */     registerItem(420, "lead", (new ItemLead()).setUnlocalizedName("leash"));
/* 1027 */     registerItem(421, "name_tag", (new ItemNameTag()).setUnlocalizedName("nameTag"));
/* 1028 */     registerItem(422, "command_block_minecart", (new ItemMinecart(EntityMinecart.EnumMinecartType.COMMAND_BLOCK)).setUnlocalizedName("minecartCommandBlock").setCreativeTab(null));
/* 1029 */     registerItem(423, "mutton", (new ItemFood(2, 0.3F, true)).setUnlocalizedName("muttonRaw"));
/* 1030 */     registerItem(424, "cooked_mutton", (new ItemFood(6, 0.8F, true)).setUnlocalizedName("muttonCooked"));
/* 1031 */     registerItem(425, "banner", (new ItemBanner()).setUnlocalizedName("banner"));
/* 1032 */     registerItem(427, "spruce_door", (new ItemDoor(Blocks.spruce_door)).setUnlocalizedName("doorSpruce"));
/* 1033 */     registerItem(428, "birch_door", (new ItemDoor(Blocks.birch_door)).setUnlocalizedName("doorBirch"));
/* 1034 */     registerItem(429, "jungle_door", (new ItemDoor(Blocks.jungle_door)).setUnlocalizedName("doorJungle"));
/* 1035 */     registerItem(430, "acacia_door", (new ItemDoor(Blocks.acacia_door)).setUnlocalizedName("doorAcacia"));
/* 1036 */     registerItem(431, "dark_oak_door", (new ItemDoor(Blocks.dark_oak_door)).setUnlocalizedName("doorDarkOak"));
/* 1037 */     registerItem(2256, "record_13", (new ItemRecord("13")).setUnlocalizedName("record"));
/* 1038 */     registerItem(2257, "record_cat", (new ItemRecord("cat")).setUnlocalizedName("record"));
/* 1039 */     registerItem(2258, "record_blocks", (new ItemRecord("blocks")).setUnlocalizedName("record"));
/* 1040 */     registerItem(2259, "record_chirp", (new ItemRecord("chirp")).setUnlocalizedName("record"));
/* 1041 */     registerItem(2260, "record_far", (new ItemRecord("far")).setUnlocalizedName("record"));
/* 1042 */     registerItem(2261, "record_mall", (new ItemRecord("mall")).setUnlocalizedName("record"));
/* 1043 */     registerItem(2262, "record_mellohi", (new ItemRecord("mellohi")).setUnlocalizedName("record"));
/* 1044 */     registerItem(2263, "record_stal", (new ItemRecord("stal")).setUnlocalizedName("record"));
/* 1045 */     registerItem(2264, "record_strad", (new ItemRecord("strad")).setUnlocalizedName("record"));
/* 1046 */     registerItem(2265, "record_ward", (new ItemRecord("ward")).setUnlocalizedName("record"));
/* 1047 */     registerItem(2266, "record_11", (new ItemRecord("11")).setUnlocalizedName("record"));
/* 1048 */     registerItem(2267, "record_wait", (new ItemRecord("wait")).setUnlocalizedName("record"));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static void registerItemBlock(Block blockIn) {
/* 1056 */     registerItemBlock(blockIn, new ItemBlock(blockIn));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected static void registerItemBlock(Block blockIn, Item itemIn) {
/* 1064 */     registerItem(Block.getIdFromBlock(blockIn), (ResourceLocation)Block.blockRegistry.getNameForObject(blockIn), itemIn);
/* 1065 */     BLOCK_TO_ITEM.put(blockIn, itemIn);
/*      */   }
/*      */ 
/*      */   
/*      */   private static void registerItem(int id, String textualID, Item itemIn) {
/* 1070 */     registerItem(id, new ResourceLocation(textualID), itemIn);
/*      */   }
/*      */ 
/*      */   
/*      */   private static void registerItem(int id, ResourceLocation textualID, Item itemIn) {
/* 1075 */     itemRegistry.register(id, textualID, itemIn);
/*      */   }
/*      */   
/*      */   public enum ToolMaterial
/*      */   {
/* 1080 */     WOOD("WOOD", 0, 0, 59, 2.0F, 0.0F, 15),
/* 1081 */     STONE("STONE", 1, 1, 131, 4.0F, 1.0F, 5),
/* 1082 */     IRON("IRON", 2, 2, 250, 6.0F, 2.0F, 14),
/* 1083 */     EMERALD("EMERALD", 3, 3, 1561, 8.0F, 3.0F, 10),
/* 1084 */     GOLD("GOLD", 4, 0, 32, 12.0F, 0.0F, 22);
/*      */     
/*      */     private final int harvestLevel;
/*      */     private final int maxUses;
/*      */     private final float efficiencyOnProperMaterial;
/*      */     private final float damageVsEntity;
/*      */     private final int enchantability;
/* 1091 */     private static final ToolMaterial[] $VALUES = new ToolMaterial[] { WOOD, STONE, IRON, EMERALD, GOLD };
/*      */     
/*      */     private static final String __OBFID = "CL_00000042";
/*      */     
/*      */     ToolMaterial(String p_i1874_1_, int p_i1874_2_, int harvestLevel, int maxUses, float efficiency, float damageVsEntity, int enchantability) {
/* 1096 */       this.harvestLevel = harvestLevel;
/* 1097 */       this.maxUses = maxUses;
/* 1098 */       this.efficiencyOnProperMaterial = efficiency;
/* 1099 */       this.damageVsEntity = damageVsEntity;
/* 1100 */       this.enchantability = enchantability;
/*      */     } static {
/*      */     
/*      */     }
/*      */     public int getMaxUses() {
/* 1105 */       return this.maxUses;
/*      */     }
/*      */ 
/*      */     
/*      */     public float getEfficiencyOnProperMaterial() {
/* 1110 */       return this.efficiencyOnProperMaterial;
/*      */     }
/*      */ 
/*      */     
/*      */     public float getDamageVsEntity() {
/* 1115 */       return this.damageVsEntity;
/*      */     }
/*      */ 
/*      */     
/*      */     public int getHarvestLevel() {
/* 1120 */       return this.harvestLevel;
/*      */     }
/*      */ 
/*      */     
/*      */     public int getEnchantability() {
/* 1125 */       return this.enchantability;
/*      */     }
/*      */ 
/*      */     
/*      */     public Item getBaseItemForRepair() {
/* 1130 */       return (this == WOOD) ? Item.getItemFromBlock(Blocks.planks) : ((this == STONE) ? Item.getItemFromBlock(Blocks.cobblestone) : ((this == GOLD) ? Items.gold_ingot : ((this == IRON) ? Items.iron_ingot : ((this == EMERALD) ? Items.diamond : null))));
/*      */     }
/*      */   }
/*      */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\item\Item.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */