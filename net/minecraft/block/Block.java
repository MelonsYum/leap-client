/*      */ package net.minecraft.block;
/*      */ 
/*      */ import com.google.common.collect.UnmodifiableIterator;
/*      */ import java.util.Iterator;
/*      */ import java.util.List;
/*      */ import java.util.Random;
/*      */ import net.minecraft.block.material.MapColor;
/*      */ import net.minecraft.block.material.Material;
/*      */ import net.minecraft.block.properties.IProperty;
/*      */ import net.minecraft.block.state.BlockState;
/*      */ import net.minecraft.block.state.IBlockState;
/*      */ import net.minecraft.creativetab.CreativeTabs;
/*      */ import net.minecraft.enchantment.EnchantmentHelper;
/*      */ import net.minecraft.entity.Entity;
/*      */ import net.minecraft.entity.EntityLivingBase;
/*      */ import net.minecraft.entity.item.EntityItem;
/*      */ import net.minecraft.entity.item.EntityXPOrb;
/*      */ import net.minecraft.entity.player.EntityPlayer;
/*      */ import net.minecraft.item.Item;
/*      */ import net.minecraft.item.ItemBlock;
/*      */ import net.minecraft.item.ItemStack;
/*      */ import net.minecraft.stats.StatList;
/*      */ import net.minecraft.tileentity.TileEntity;
/*      */ import net.minecraft.util.AxisAlignedBB;
/*      */ import net.minecraft.util.BlockPos;
/*      */ import net.minecraft.util.EnumFacing;
/*      */ import net.minecraft.util.EnumWorldBlockLayer;
/*      */ import net.minecraft.util.MovingObjectPosition;
/*      */ import net.minecraft.util.ObjectIntIdentityMap;
/*      */ import net.minecraft.util.RegistryNamespacedDefaultedByKey;
/*      */ import net.minecraft.util.ResourceLocation;
/*      */ import net.minecraft.util.StatCollector;
/*      */ import net.minecraft.util.Vec3;
/*      */ import net.minecraft.world.Explosion;
/*      */ import net.minecraft.world.IBlockAccess;
/*      */ import net.minecraft.world.World;
/*      */ 
/*      */ public class Block
/*      */ {
/*   40 */   private static final ResourceLocation AIR_ID = new ResourceLocation("air");
/*   41 */   public static final RegistryNamespacedDefaultedByKey blockRegistry = new RegistryNamespacedDefaultedByKey(AIR_ID);
/*   42 */   public static final ObjectIntIdentityMap BLOCK_STATE_IDS = new ObjectIntIdentityMap();
/*      */   private CreativeTabs displayOnCreativeTab;
/*   44 */   public static final SoundType soundTypeStone = new SoundType("stone", 1.0F, 1.0F);
/*      */ 
/*      */   
/*   47 */   public static final SoundType soundTypeWood = new SoundType("wood", 1.0F, 1.0F);
/*      */ 
/*      */   
/*   50 */   public static final SoundType soundTypeGravel = new SoundType("gravel", 1.0F, 1.0F);
/*   51 */   public static final SoundType soundTypeGrass = new SoundType("grass", 1.0F, 1.0F);
/*   52 */   public static final SoundType soundTypePiston = new SoundType("stone", 1.0F, 1.0F);
/*   53 */   public static final SoundType soundTypeMetal = new SoundType("stone", 1.0F, 1.5F);
/*   54 */   public static final SoundType soundTypeGlass = new SoundType("stone", 1.0F, 1.0F)
/*      */     {
/*      */       private static final String __OBFID = "CL_00000200";
/*      */       
/*      */       public String getBreakSound() {
/*   59 */         return "dig.glass";
/*      */       }
/*      */       
/*      */       public String getPlaceSound() {
/*   63 */         return "step.stone";
/*      */       }
/*      */     };
/*   66 */   public static final SoundType soundTypeCloth = new SoundType("cloth", 1.0F, 1.0F);
/*   67 */   public static final SoundType soundTypeSand = new SoundType("sand", 1.0F, 1.0F);
/*   68 */   public static final SoundType soundTypeSnow = new SoundType("snow", 1.0F, 1.0F);
/*   69 */   public static final SoundType soundTypeLadder = new SoundType("ladder", 1.0F, 1.0F)
/*      */     {
/*      */       private static final String __OBFID = "CL_00000201";
/*      */       
/*      */       public String getBreakSound() {
/*   74 */         return "dig.wood";
/*      */       }
/*      */     };
/*   77 */   public static final SoundType soundTypeAnvil = new SoundType("anvil", 0.3F, 1.0F)
/*      */     {
/*      */       private static final String __OBFID = "CL_00000202";
/*      */       
/*      */       public String getBreakSound() {
/*   82 */         return "dig.stone";
/*      */       }
/*      */       
/*      */       public String getPlaceSound() {
/*   86 */         return "random.anvil_land";
/*      */       }
/*      */     };
/*   89 */   public static final SoundType SLIME_SOUND = new SoundType("slime", 1.0F, 1.0F)
/*      */     {
/*      */       private static final String __OBFID = "CL_00002133";
/*      */       
/*      */       public String getBreakSound() {
/*   94 */         return "mob.slime.big";
/*      */       }
/*      */       
/*      */       public String getPlaceSound() {
/*   98 */         return "mob.slime.big";
/*      */       }
/*      */       
/*      */       public String getStepSound() {
/*  102 */         return "mob.slime.small";
/*      */       }
/*      */     };
/*      */   
/*      */   protected boolean fullBlock;
/*      */   
/*      */   protected int lightOpacity;
/*      */   
/*      */   protected boolean translucent;
/*      */   
/*      */   protected int lightValue;
/*      */   
/*      */   protected boolean useNeighborBrightness;
/*      */   
/*      */   protected float blockHardness;
/*      */   
/*      */   protected float blockResistance;
/*      */   
/*      */   protected boolean enableStats = true;
/*      */   
/*      */   protected boolean needsRandomTick;
/*      */   
/*      */   protected boolean isBlockContainer;
/*      */   
/*      */   protected double minX;
/*      */   
/*      */   protected double minY;
/*      */   
/*      */   protected double minZ;
/*      */   
/*      */   protected double maxX;
/*      */   
/*      */   protected double maxY;
/*      */   
/*      */   protected double maxZ;
/*      */   
/*      */   public SoundType stepSound;
/*      */   
/*      */   public float blockParticleGravity;
/*      */   
/*      */   protected final Material blockMaterial;
/*      */   
/*      */   public float slipperiness;
/*      */   
/*      */   protected final BlockState blockState;
/*      */   
/*      */   private IBlockState defaultBlockState;
/*      */   
/*      */   private String unlocalizedName;
/*      */   
/*      */   private static final String __OBFID = "CL_00000199";
/*      */   
/*      */   public static int getIdFromBlock(Block blockIn) {
/*  155 */     return blockRegistry.getIDForObject(blockIn);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static int getStateId(IBlockState state) {
/*  163 */     return getIdFromBlock(state.getBlock()) + (state.getBlock().getMetaFromState(state) << 12);
/*      */   }
/*      */ 
/*      */   
/*      */   public static Block getBlockById(int id) {
/*  168 */     return (Block)blockRegistry.getObjectById(id);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static IBlockState getStateById(int id) {
/*  176 */     int var1 = id & 0xFFF;
/*  177 */     int var2 = id >> 12 & 0xF;
/*  178 */     return getBlockById(var1).getStateFromMeta(var2);
/*      */   }
/*      */ 
/*      */   
/*      */   public static Block getBlockFromItem(Item itemIn) {
/*  183 */     return (itemIn instanceof ItemBlock) ? ((ItemBlock)itemIn).getBlock() : null;
/*      */   }
/*      */ 
/*      */   
/*      */   public static Block getBlockFromName(String name) {
/*  188 */     ResourceLocation var1 = new ResourceLocation(name);
/*      */     
/*  190 */     if (blockRegistry.containsKey(var1))
/*      */     {
/*  192 */       return (Block)blockRegistry.getObject(var1);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     try {
/*  198 */       return (Block)blockRegistry.getObjectById(Integer.parseInt(name));
/*      */     }
/*  200 */     catch (NumberFormatException var3) {
/*      */       
/*  202 */       return null;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isFullBlock() {
/*  209 */     return this.fullBlock;
/*      */   }
/*      */ 
/*      */   
/*      */   public int getLightOpacity() {
/*  214 */     return this.lightOpacity;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isTranslucent() {
/*  219 */     return this.translucent;
/*      */   }
/*      */ 
/*      */   
/*      */   public int getLightValue() {
/*  224 */     return this.lightValue;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean getUseNeighborBrightness() {
/*  232 */     return this.useNeighborBrightness;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Material getMaterial() {
/*  240 */     return this.blockMaterial;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public MapColor getMapColor(IBlockState state) {
/*  248 */     return getMaterial().getMaterialMapColor();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public IBlockState getStateFromMeta(int meta) {
/*  256 */     return getDefaultState();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getMetaFromState(IBlockState state) {
/*  264 */     if (state != null && !state.getPropertyNames().isEmpty())
/*      */     {
/*  266 */       throw new IllegalArgumentException("Don't know how to convert " + state + " back into data...");
/*      */     }
/*      */ 
/*      */     
/*  270 */     return 0;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
/*  280 */     return state;
/*      */   }
/*      */ 
/*      */   
/*      */   protected Block(Material materialIn) {
/*  285 */     this.stepSound = soundTypeStone;
/*  286 */     this.blockParticleGravity = 1.0F;
/*  287 */     this.slipperiness = 0.6F;
/*  288 */     this.blockMaterial = materialIn;
/*  289 */     setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
/*  290 */     this.fullBlock = isOpaqueCube();
/*  291 */     this.lightOpacity = isOpaqueCube() ? 255 : 0;
/*  292 */     this.translucent = !materialIn.blocksLight();
/*  293 */     this.blockState = createBlockState();
/*  294 */     setDefaultState(this.blockState.getBaseState());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected Block setStepSound(SoundType sound) {
/*  302 */     this.stepSound = sound;
/*  303 */     return this;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected Block setLightOpacity(int opacity) {
/*  311 */     this.lightOpacity = opacity;
/*  312 */     return this;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected Block setLightLevel(float value) {
/*  321 */     this.lightValue = (int)(15.0F * value);
/*  322 */     return this;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected Block setResistance(float resistance) {
/*  330 */     this.blockResistance = resistance * 3.0F;
/*  331 */     return this;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isSolidFullCube() {
/*  339 */     return (this.blockMaterial.blocksMovement() && isFullCube());
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isNormalCube() {
/*  344 */     return (this.blockMaterial.isOpaque() && isFullCube() && !canProvidePower());
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isVisuallyOpaque() {
/*  349 */     return (this.blockMaterial.blocksMovement() && isFullCube());
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isFullCube() {
/*  354 */     return true;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isPassable(IBlockAccess blockAccess, BlockPos pos) {
/*  359 */     return !this.blockMaterial.blocksMovement();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getRenderType() {
/*  367 */     return 3;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isReplaceable(World worldIn, BlockPos pos) {
/*  375 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected Block setHardness(float hardness) {
/*  383 */     this.blockHardness = hardness;
/*      */     
/*  385 */     if (this.blockResistance < hardness * 5.0F)
/*      */     {
/*  387 */       this.blockResistance = hardness * 5.0F;
/*      */     }
/*      */     
/*  390 */     return this;
/*      */   }
/*      */ 
/*      */   
/*      */   protected Block setBlockUnbreakable() {
/*  395 */     setHardness(-1.0F);
/*  396 */     return this;
/*      */   }
/*      */ 
/*      */   
/*      */   public float getBlockHardness(World worldIn, BlockPos pos) {
/*  401 */     return this.blockHardness;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected Block setTickRandomly(boolean shouldTick) {
/*  409 */     this.needsRandomTick = shouldTick;
/*  410 */     return this;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean getTickRandomly() {
/*  419 */     return this.needsRandomTick;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean hasTileEntity() {
/*  424 */     return this.isBlockContainer;
/*      */   }
/*      */ 
/*      */   
/*      */   protected final void setBlockBounds(float minX, float minY, float minZ, float maxX, float maxY, float maxZ) {
/*  429 */     this.minX = minX;
/*  430 */     this.minY = minY;
/*  431 */     this.minZ = minZ;
/*  432 */     this.maxX = maxX;
/*  433 */     this.maxY = maxY;
/*  434 */     this.maxZ = maxZ;
/*      */   }
/*      */ 
/*      */   
/*      */   public int getMixedBrightnessForBlock(IBlockAccess worldIn, BlockPos pos) {
/*  439 */     Block var3 = worldIn.getBlockState(pos).getBlock();
/*  440 */     int var4 = worldIn.getCombinedLight(pos, var3.getLightValue());
/*      */     
/*  442 */     if (var4 == 0 && var3 instanceof BlockSlab) {
/*      */       
/*  444 */       pos = pos.offsetDown();
/*  445 */       var3 = worldIn.getBlockState(pos).getBlock();
/*  446 */       return worldIn.getCombinedLight(pos, var3.getLightValue());
/*      */     } 
/*      */ 
/*      */     
/*  450 */     return var4;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean shouldSideBeRendered(IBlockAccess worldIn, BlockPos pos, EnumFacing side) {
/*  456 */     return (side == EnumFacing.DOWN && this.minY > 0.0D) ? true : ((side == EnumFacing.UP && this.maxY < 1.0D) ? true : ((side == EnumFacing.NORTH && this.minZ > 0.0D) ? true : ((side == EnumFacing.SOUTH && this.maxZ < 1.0D) ? true : ((side == EnumFacing.WEST && this.minX > 0.0D) ? true : ((side == EnumFacing.EAST && this.maxX < 1.0D) ? true : (!worldIn.getBlockState(pos).getBlock().isOpaqueCube()))))));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isBlockSolid(IBlockAccess worldIn, BlockPos pos, EnumFacing side) {
/*  464 */     return worldIn.getBlockState(pos).getBlock().getMaterial().isSolid();
/*      */   }
/*      */ 
/*      */   
/*      */   public AxisAlignedBB getSelectedBoundingBox(World worldIn, BlockPos pos) {
/*  469 */     return new AxisAlignedBB(pos.getX() + this.minX, pos.getY() + this.minY, pos.getZ() + this.minZ, pos.getX() + this.maxX, pos.getY() + this.maxY, pos.getZ() + this.maxZ);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void addCollisionBoxesToList(World worldIn, BlockPos pos, IBlockState state, AxisAlignedBB mask, List<AxisAlignedBB> list, Entity collidingEntity) {
/*  479 */     AxisAlignedBB var7 = getCollisionBoundingBox(worldIn, pos, state);
/*      */     
/*  481 */     if (var7 != null && mask.intersectsWith(var7))
/*      */     {
/*  483 */       list.add(var7);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public AxisAlignedBB getCollisionBoundingBox(World worldIn, BlockPos pos, IBlockState state) {
/*  489 */     return new AxisAlignedBB(pos.getX() + this.minX, pos.getY() + this.minY, pos.getZ() + this.minZ, pos.getX() + this.maxX, pos.getY() + this.maxY, pos.getZ() + this.maxZ);
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isOpaqueCube() {
/*  494 */     return true;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean canCollideCheck(IBlockState state, boolean p_176209_2_) {
/*  499 */     return isCollidable();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isCollidable() {
/*  507 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void randomTick(World worldIn, BlockPos pos, IBlockState state, Random random) {
/*  515 */     updateTick(worldIn, pos, state, random);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {}
/*      */ 
/*      */ 
/*      */   
/*      */   public void randomDisplayTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {}
/*      */ 
/*      */   
/*      */   public void onBlockDestroyedByPlayer(World worldIn, BlockPos pos, IBlockState state) {}
/*      */ 
/*      */   
/*      */   public void onNeighborBlockChange(World worldIn, BlockPos pos, IBlockState state, Block neighborBlock) {}
/*      */ 
/*      */   
/*      */   public int tickRate(World worldIn) {
/*  534 */     return 10;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state) {}
/*      */ 
/*      */   
/*      */   public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {}
/*      */ 
/*      */   
/*      */   public int quantityDropped(Random random) {
/*  546 */     return 1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Item getItemDropped(IBlockState state, Random rand, int fortune) {
/*  556 */     return Item.getItemFromBlock(this);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public float getPlayerRelativeBlockHardness(EntityPlayer playerIn, World worldIn, BlockPos pos) {
/*  564 */     float var4 = getBlockHardness(worldIn, pos);
/*  565 */     return (var4 < 0.0F) ? 0.0F : (!playerIn.canHarvestBlock(this) ? (playerIn.func_180471_a(this) / var4 / 100.0F) : (playerIn.func_180471_a(this) / var4 / 30.0F));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void dropBlockAsItem(World worldIn, BlockPos pos, IBlockState state, int forture) {
/*  575 */     dropBlockAsItemWithChance(worldIn, pos, state, 1.0F, forture);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void dropBlockAsItemWithChance(World worldIn, BlockPos pos, IBlockState state, float chance, int fortune) {
/*  586 */     if (!worldIn.isRemote) {
/*      */       
/*  588 */       int var6 = quantityDroppedWithBonus(fortune, worldIn.rand);
/*      */       
/*  590 */       for (int var7 = 0; var7 < var6; var7++) {
/*      */         
/*  592 */         if (worldIn.rand.nextFloat() <= chance) {
/*      */           
/*  594 */           Item var8 = getItemDropped(state, worldIn.rand, fortune);
/*      */           
/*  596 */           if (var8 != null)
/*      */           {
/*  598 */             spawnAsEntity(worldIn, pos, new ItemStack(var8, 1, damageDropped(state)));
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
/*      */   public static void spawnAsEntity(World worldIn, BlockPos pos, ItemStack stack) {
/*  610 */     if (!worldIn.isRemote && worldIn.getGameRules().getGameRuleBooleanValue("doTileDrops")) {
/*      */       
/*  612 */       float var3 = 0.5F;
/*  613 */       double var4 = (worldIn.rand.nextFloat() * var3) + (1.0F - var3) * 0.5D;
/*  614 */       double var6 = (worldIn.rand.nextFloat() * var3) + (1.0F - var3) * 0.5D;
/*  615 */       double var8 = (worldIn.rand.nextFloat() * var3) + (1.0F - var3) * 0.5D;
/*  616 */       EntityItem var10 = new EntityItem(worldIn, pos.getX() + var4, pos.getY() + var6, pos.getZ() + var8, stack);
/*  617 */       var10.setDefaultPickupDelay();
/*  618 */       worldIn.spawnEntityInWorld((Entity)var10);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void dropXpOnBlockBreak(World worldIn, BlockPos pos, int amount) {
/*  629 */     if (!worldIn.isRemote)
/*      */     {
/*  631 */       while (amount > 0) {
/*      */         
/*  633 */         int var4 = EntityXPOrb.getXPSplit(amount);
/*  634 */         amount -= var4;
/*  635 */         worldIn.spawnEntityInWorld((Entity)new EntityXPOrb(worldIn, pos.getX() + 0.5D, pos.getY() + 0.5D, pos.getZ() + 0.5D, var4));
/*      */       } 
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int damageDropped(IBlockState state) {
/*  645 */     return 0;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public float getExplosionResistance(Entity exploder) {
/*  653 */     return this.blockResistance / 5.0F;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public MovingObjectPosition collisionRayTrace(World worldIn, BlockPos pos, Vec3 start, Vec3 end) {
/*  664 */     setBlockBoundsBasedOnState((IBlockAccess)worldIn, pos);
/*  665 */     start = start.addVector(-pos.getX(), -pos.getY(), -pos.getZ());
/*  666 */     end = end.addVector(-pos.getX(), -pos.getY(), -pos.getZ());
/*  667 */     Vec3 var5 = start.getIntermediateWithXValue(end, this.minX);
/*  668 */     Vec3 var6 = start.getIntermediateWithXValue(end, this.maxX);
/*  669 */     Vec3 var7 = start.getIntermediateWithYValue(end, this.minY);
/*  670 */     Vec3 var8 = start.getIntermediateWithYValue(end, this.maxY);
/*  671 */     Vec3 var9 = start.getIntermediateWithZValue(end, this.minZ);
/*  672 */     Vec3 var10 = start.getIntermediateWithZValue(end, this.maxZ);
/*      */     
/*  674 */     if (!isVecInsideYZBounds(var5))
/*      */     {
/*  676 */       var5 = null;
/*      */     }
/*      */     
/*  679 */     if (!isVecInsideYZBounds(var6))
/*      */     {
/*  681 */       var6 = null;
/*      */     }
/*      */     
/*  684 */     if (!isVecInsideXZBounds(var7))
/*      */     {
/*  686 */       var7 = null;
/*      */     }
/*      */     
/*  689 */     if (!isVecInsideXZBounds(var8))
/*      */     {
/*  691 */       var8 = null;
/*      */     }
/*      */     
/*  694 */     if (!isVecInsideXYBounds(var9))
/*      */     {
/*  696 */       var9 = null;
/*      */     }
/*      */     
/*  699 */     if (!isVecInsideXYBounds(var10))
/*      */     {
/*  701 */       var10 = null;
/*      */     }
/*      */     
/*  704 */     Vec3 var11 = null;
/*      */     
/*  706 */     if (var5 != null && (var11 == null || start.squareDistanceTo(var5) < start.squareDistanceTo(var11)))
/*      */     {
/*  708 */       var11 = var5;
/*      */     }
/*      */     
/*  711 */     if (var6 != null && (var11 == null || start.squareDistanceTo(var6) < start.squareDistanceTo(var11)))
/*      */     {
/*  713 */       var11 = var6;
/*      */     }
/*      */     
/*  716 */     if (var7 != null && (var11 == null || start.squareDistanceTo(var7) < start.squareDistanceTo(var11)))
/*      */     {
/*  718 */       var11 = var7;
/*      */     }
/*      */     
/*  721 */     if (var8 != null && (var11 == null || start.squareDistanceTo(var8) < start.squareDistanceTo(var11)))
/*      */     {
/*  723 */       var11 = var8;
/*      */     }
/*      */     
/*  726 */     if (var9 != null && (var11 == null || start.squareDistanceTo(var9) < start.squareDistanceTo(var11)))
/*      */     {
/*  728 */       var11 = var9;
/*      */     }
/*      */     
/*  731 */     if (var10 != null && (var11 == null || start.squareDistanceTo(var10) < start.squareDistanceTo(var11)))
/*      */     {
/*  733 */       var11 = var10;
/*      */     }
/*      */     
/*  736 */     if (var11 == null)
/*      */     {
/*  738 */       return null;
/*      */     }
/*      */ 
/*      */     
/*  742 */     EnumFacing var12 = null;
/*      */     
/*  744 */     if (var11 == var5)
/*      */     {
/*  746 */       var12 = EnumFacing.WEST;
/*      */     }
/*      */     
/*  749 */     if (var11 == var6)
/*      */     {
/*  751 */       var12 = EnumFacing.EAST;
/*      */     }
/*      */     
/*  754 */     if (var11 == var7)
/*      */     {
/*  756 */       var12 = EnumFacing.DOWN;
/*      */     }
/*      */     
/*  759 */     if (var11 == var8)
/*      */     {
/*  761 */       var12 = EnumFacing.UP;
/*      */     }
/*      */     
/*  764 */     if (var11 == var9)
/*      */     {
/*  766 */       var12 = EnumFacing.NORTH;
/*      */     }
/*      */     
/*  769 */     if (var11 == var10)
/*      */     {
/*  771 */       var12 = EnumFacing.SOUTH;
/*      */     }
/*      */     
/*  774 */     return new MovingObjectPosition(var11.addVector(pos.getX(), pos.getY(), pos.getZ()), var12, pos);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean isVecInsideYZBounds(Vec3 point) {
/*  783 */     return (point == null) ? false : ((point.yCoord >= this.minY && point.yCoord <= this.maxY && point.zCoord >= this.minZ && point.zCoord <= this.maxZ));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean isVecInsideXZBounds(Vec3 point) {
/*  791 */     return (point == null) ? false : ((point.xCoord >= this.minX && point.xCoord <= this.maxX && point.zCoord >= this.minZ && point.zCoord <= this.maxZ));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean isVecInsideXYBounds(Vec3 point) {
/*  799 */     return (point == null) ? false : ((point.xCoord >= this.minX && point.xCoord <= this.maxX && point.yCoord >= this.minY && point.yCoord <= this.maxY));
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void onBlockDestroyedByExplosion(World worldIn, BlockPos pos, Explosion explosionIn) {}
/*      */ 
/*      */ 
/*      */   
/*      */   public EnumWorldBlockLayer getBlockLayer() {
/*  809 */     return EnumWorldBlockLayer.SOLID;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean canReplace(World worldIn, BlockPos pos, EnumFacing side, ItemStack stack) {
/*  814 */     return canPlaceBlockOnSide(worldIn, pos, side);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean canPlaceBlockOnSide(World worldIn, BlockPos pos, EnumFacing side) {
/*  822 */     return canPlaceBlockAt(worldIn, pos);
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean canPlaceBlockAt(World worldIn, BlockPos pos) {
/*  827 */     return (worldIn.getBlockState(pos).getBlock()).blockMaterial.isReplaceable();
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumFacing side, float hitX, float hitY, float hitZ) {
/*  832 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void onEntityCollidedWithBlock(World worldIn, BlockPos pos, Entity entityIn) {}
/*      */ 
/*      */ 
/*      */   
/*      */   public IBlockState onBlockPlaced(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
/*  842 */     return getStateFromMeta(meta);
/*      */   }
/*      */ 
/*      */   
/*      */   public void onBlockClicked(World worldIn, BlockPos pos, EntityPlayer playerIn) {}
/*      */   
/*      */   public Vec3 modifyAcceleration(World worldIn, BlockPos pos, Entity entityIn, Vec3 motion) {
/*  849 */     return motion;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void setBlockBoundsBasedOnState(IBlockAccess access, BlockPos pos) {}
/*      */ 
/*      */ 
/*      */   
/*      */   public final double getBlockBoundsMinX() {
/*  859 */     return this.minX;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final double getBlockBoundsMaxX() {
/*  867 */     return this.maxX;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final double getBlockBoundsMinY() {
/*  875 */     return this.minY;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final double getBlockBoundsMaxY() {
/*  883 */     return this.maxY;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final double getBlockBoundsMinZ() {
/*  891 */     return this.minZ;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final double getBlockBoundsMaxZ() {
/*  899 */     return this.maxZ;
/*      */   }
/*      */ 
/*      */   
/*      */   public int getBlockColor() {
/*  904 */     return 16777215;
/*      */   }
/*      */ 
/*      */   
/*      */   public int getRenderColor(IBlockState state) {
/*  909 */     return 16777215;
/*      */   }
/*      */ 
/*      */   
/*      */   public int colorMultiplier(IBlockAccess worldIn, BlockPos pos, int renderPass) {
/*  914 */     return 16777215;
/*      */   }
/*      */ 
/*      */   
/*      */   public final int colorMultiplier(IBlockAccess worldIn, BlockPos pos) {
/*  919 */     return colorMultiplier(worldIn, pos, 0);
/*      */   }
/*      */ 
/*      */   
/*      */   public int isProvidingWeakPower(IBlockAccess worldIn, BlockPos pos, IBlockState state, EnumFacing side) {
/*  924 */     return 0;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean canProvidePower() {
/*  932 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void onEntityCollidedWithBlock(World worldIn, BlockPos pos, IBlockState state, Entity entityIn) {}
/*      */ 
/*      */ 
/*      */   
/*      */   public int isProvidingStrongPower(IBlockAccess worldIn, BlockPos pos, IBlockState state, EnumFacing side) {
/*  942 */     return 0;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void setBlockBoundsForItemRender() {}
/*      */ 
/*      */ 
/*      */   
/*      */   public void harvestBlock(World worldIn, EntityPlayer playerIn, BlockPos pos, IBlockState state, TileEntity te) {
/*  952 */     playerIn.triggerAchievement(StatList.mineBlockStatArray[getIdFromBlock(this)]);
/*  953 */     playerIn.addExhaustion(0.025F);
/*      */     
/*  955 */     if (canSilkHarvest() && EnchantmentHelper.getSilkTouchModifier((EntityLivingBase)playerIn)) {
/*      */       
/*  957 */       ItemStack var7 = createStackedBlock(state);
/*      */       
/*  959 */       if (var7 != null)
/*      */       {
/*  961 */         spawnAsEntity(worldIn, pos, var7);
/*      */       }
/*      */     }
/*      */     else {
/*      */       
/*  966 */       int var6 = EnchantmentHelper.getFortuneModifier((EntityLivingBase)playerIn);
/*  967 */       dropBlockAsItem(worldIn, pos, state, var6);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   protected boolean canSilkHarvest() {
/*  973 */     return (isFullCube() && !this.isBlockContainer);
/*      */   }
/*      */ 
/*      */   
/*      */   protected ItemStack createStackedBlock(IBlockState state) {
/*  978 */     int var2 = 0;
/*  979 */     Item var3 = Item.getItemFromBlock(this);
/*      */     
/*  981 */     if (var3 != null && var3.getHasSubtypes())
/*      */     {
/*  983 */       var2 = getMetaFromState(state);
/*      */     }
/*      */     
/*  986 */     return new ItemStack(var3, 1, var2);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int quantityDroppedWithBonus(int fortune, Random random) {
/*  994 */     return quantityDropped(random);
/*      */   }
/*      */ 
/*      */   
/*      */   public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {}
/*      */   
/*      */   public Block setUnlocalizedName(String name) {
/* 1001 */     this.unlocalizedName = name;
/* 1002 */     return this;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getLocalizedName() {
/* 1010 */     return StatCollector.translateToLocal(String.valueOf(getUnlocalizedName()) + ".name");
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getUnlocalizedName() {
/* 1018 */     return "tile." + this.unlocalizedName;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean onBlockEventReceived(World worldIn, BlockPos pos, IBlockState state, int eventID, int eventParam) {
/* 1026 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean getEnableStats() {
/* 1034 */     return this.enableStats;
/*      */   }
/*      */ 
/*      */   
/*      */   protected Block disableStats() {
/* 1039 */     this.enableStats = false;
/* 1040 */     return this;
/*      */   }
/*      */ 
/*      */   
/*      */   public int getMobilityFlag() {
/* 1045 */     return this.blockMaterial.getMaterialMobility();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public float getAmbientOcclusionLightValue() {
/* 1053 */     return isSolidFullCube() ? 0.2F : 1.0F;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void onFallenUpon(World worldIn, BlockPos pos, Entity entityIn, float fallDistance) {
/* 1063 */     entityIn.fall(fallDistance, 1.0F);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void onLanded(World worldIn, Entity entityIn) {
/* 1072 */     entityIn.motionY = 0.0D;
/*      */   }
/*      */ 
/*      */   
/*      */   public Item getItem(World worldIn, BlockPos pos) {
/* 1077 */     return Item.getItemFromBlock(this);
/*      */   }
/*      */ 
/*      */   
/*      */   public int getDamageValue(World worldIn, BlockPos pos) {
/* 1082 */     return damageDropped(worldIn.getBlockState(pos));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void getSubBlocks(Item itemIn, CreativeTabs tab, List<ItemStack> list) {
/* 1090 */     list.add(new ItemStack(itemIn, 1, 0));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public CreativeTabs getCreativeTabToDisplayOn() {
/* 1098 */     return this.displayOnCreativeTab;
/*      */   }
/*      */ 
/*      */   
/*      */   public Block setCreativeTab(CreativeTabs tab) {
/* 1103 */     this.displayOnCreativeTab = tab;
/* 1104 */     return this;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void onBlockHarvested(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn) {}
/*      */ 
/*      */ 
/*      */   
/*      */   public void fillWithRain(World worldIn, BlockPos pos) {}
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isFlowerPot() {
/* 1119 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean requiresUpdates() {
/* 1124 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean canDropFromExplosion(Explosion explosionIn) {
/* 1132 */     return true;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isAssociatedBlock(Block other) {
/* 1137 */     return (this == other);
/*      */   }
/*      */ 
/*      */   
/*      */   public static boolean isEqualTo(Block blockIn, Block other) {
/* 1142 */     return (blockIn != null && other != null) ? ((blockIn == other) ? true : blockIn.isAssociatedBlock(other)) : false;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean hasComparatorInputOverride() {
/* 1147 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   public int getComparatorInputOverride(World worldIn, BlockPos pos) {
/* 1152 */     return 0;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public IBlockState getStateForEntityRender(IBlockState state) {
/* 1160 */     return state;
/*      */   }
/*      */ 
/*      */   
/*      */   protected BlockState createBlockState() {
/* 1165 */     return new BlockState(this, new IProperty[0]);
/*      */   }
/*      */ 
/*      */   
/*      */   public BlockState getBlockState() {
/* 1170 */     return this.blockState;
/*      */   }
/*      */ 
/*      */   
/*      */   protected final void setDefaultState(IBlockState state) {
/* 1175 */     this.defaultBlockState = state;
/*      */   }
/*      */ 
/*      */   
/*      */   public final IBlockState getDefaultState() {
/* 1180 */     return this.defaultBlockState;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public EnumOffsetType getOffsetType() {
/* 1188 */     return EnumOffsetType.NONE;
/*      */   }
/*      */ 
/*      */   
/*      */   public static void registerBlocks() {
/* 1193 */     registerBlock(0, AIR_ID, (new BlockAir()).setUnlocalizedName("air"));
/* 1194 */     registerBlock(1, "stone", (new BlockStone()).setHardness(1.5F).setResistance(10.0F).setStepSound(soundTypePiston).setUnlocalizedName("stone"));
/* 1195 */     registerBlock(2, "grass", (new BlockGrass()).setHardness(0.6F).setStepSound(soundTypeGrass).setUnlocalizedName("grass"));
/* 1196 */     registerBlock(3, "dirt", (new BlockDirt()).setHardness(0.5F).setStepSound(soundTypeGravel).setUnlocalizedName("dirt"));
/* 1197 */     Block var0 = (new Block(Material.rock)).setHardness(2.0F).setResistance(10.0F).setStepSound(soundTypePiston).setUnlocalizedName("stonebrick").setCreativeTab(CreativeTabs.tabBlock);
/* 1198 */     registerBlock(4, "cobblestone", var0);
/* 1199 */     Block var1 = (new BlockPlanks()).setHardness(2.0F).setResistance(5.0F).setStepSound(soundTypeWood).setUnlocalizedName("wood");
/* 1200 */     registerBlock(5, "planks", var1);
/* 1201 */     registerBlock(6, "sapling", (new BlockSapling()).setHardness(0.0F).setStepSound(soundTypeGrass).setUnlocalizedName("sapling"));
/* 1202 */     registerBlock(7, "bedrock", (new Block(Material.rock)).setBlockUnbreakable().setResistance(6000000.0F).setStepSound(soundTypePiston).setUnlocalizedName("bedrock").disableStats().setCreativeTab(CreativeTabs.tabBlock));
/* 1203 */     registerBlock(8, "flowing_water", (new BlockDynamicLiquid(Material.water)).setHardness(100.0F).setLightOpacity(3).setUnlocalizedName("water").disableStats());
/* 1204 */     registerBlock(9, "water", (new BlockStaticLiquid(Material.water)).setHardness(100.0F).setLightOpacity(3).setUnlocalizedName("water").disableStats());
/* 1205 */     registerBlock(10, "flowing_lava", (new BlockDynamicLiquid(Material.lava)).setHardness(100.0F).setLightLevel(1.0F).setUnlocalizedName("lava").disableStats());
/* 1206 */     registerBlock(11, "lava", (new BlockStaticLiquid(Material.lava)).setHardness(100.0F).setLightLevel(1.0F).setUnlocalizedName("lava").disableStats());
/* 1207 */     registerBlock(12, "sand", (new BlockSand()).setHardness(0.5F).setStepSound(soundTypeSand).setUnlocalizedName("sand"));
/* 1208 */     registerBlock(13, "gravel", (new BlockGravel()).setHardness(0.6F).setStepSound(soundTypeGravel).setUnlocalizedName("gravel"));
/* 1209 */     registerBlock(14, "gold_ore", (new BlockOre()).setHardness(3.0F).setResistance(5.0F).setStepSound(soundTypePiston).setUnlocalizedName("oreGold"));
/* 1210 */     registerBlock(15, "iron_ore", (new BlockOre()).setHardness(3.0F).setResistance(5.0F).setStepSound(soundTypePiston).setUnlocalizedName("oreIron"));
/* 1211 */     registerBlock(16, "coal_ore", (new BlockOre()).setHardness(3.0F).setResistance(5.0F).setStepSound(soundTypePiston).setUnlocalizedName("oreCoal"));
/* 1212 */     registerBlock(17, "log", (new BlockOldLog()).setUnlocalizedName("log"));
/* 1213 */     registerBlock(18, "leaves", (new BlockOldLeaf()).setUnlocalizedName("leaves"));
/* 1214 */     registerBlock(19, "sponge", (new BlockSponge()).setHardness(0.6F).setStepSound(soundTypeGrass).setUnlocalizedName("sponge"));
/* 1215 */     registerBlock(20, "glass", (new BlockGlass(Material.glass, false)).setHardness(0.3F).setStepSound(soundTypeGlass).setUnlocalizedName("glass"));
/* 1216 */     registerBlock(21, "lapis_ore", (new BlockOre()).setHardness(3.0F).setResistance(5.0F).setStepSound(soundTypePiston).setUnlocalizedName("oreLapis"));
/* 1217 */     registerBlock(22, "lapis_block", (new BlockCompressed(MapColor.lapisColor)).setHardness(3.0F).setResistance(5.0F).setStepSound(soundTypePiston).setUnlocalizedName("blockLapis").setCreativeTab(CreativeTabs.tabBlock));
/* 1218 */     registerBlock(23, "dispenser", (new BlockDispenser()).setHardness(3.5F).setStepSound(soundTypePiston).setUnlocalizedName("dispenser"));
/* 1219 */     Block var2 = (new BlockSandStone()).setStepSound(soundTypePiston).setHardness(0.8F).setUnlocalizedName("sandStone");
/* 1220 */     registerBlock(24, "sandstone", var2);
/* 1221 */     registerBlock(25, "noteblock", (new BlockNote()).setHardness(0.8F).setUnlocalizedName("musicBlock"));
/* 1222 */     registerBlock(26, "bed", (new BlockBed()).setStepSound(soundTypeWood).setHardness(0.2F).setUnlocalizedName("bed").disableStats());
/* 1223 */     registerBlock(27, "golden_rail", (new BlockRailPowered()).setHardness(0.7F).setStepSound(soundTypeMetal).setUnlocalizedName("goldenRail"));
/* 1224 */     registerBlock(28, "detector_rail", (new BlockRailDetector()).setHardness(0.7F).setStepSound(soundTypeMetal).setUnlocalizedName("detectorRail"));
/* 1225 */     registerBlock(29, "sticky_piston", (new BlockPistonBase(true)).setUnlocalizedName("pistonStickyBase"));
/* 1226 */     registerBlock(30, "web", (new BlockWeb()).setLightOpacity(1).setHardness(4.0F).setUnlocalizedName("web"));
/* 1227 */     registerBlock(31, "tallgrass", (new BlockTallGrass()).setHardness(0.0F).setStepSound(soundTypeGrass).setUnlocalizedName("tallgrass"));
/* 1228 */     registerBlock(32, "deadbush", (new BlockDeadBush()).setHardness(0.0F).setStepSound(soundTypeGrass).setUnlocalizedName("deadbush"));
/* 1229 */     registerBlock(33, "piston", (new BlockPistonBase(false)).setUnlocalizedName("pistonBase"));
/* 1230 */     registerBlock(34, "piston_head", new BlockPistonExtension());
/* 1231 */     registerBlock(35, "wool", (new BlockColored(Material.cloth)).setHardness(0.8F).setStepSound(soundTypeCloth).setUnlocalizedName("cloth"));
/* 1232 */     registerBlock(36, "piston_extension", new BlockPistonMoving());
/* 1233 */     registerBlock(37, "yellow_flower", (new BlockYellowFlower()).setHardness(0.0F).setStepSound(soundTypeGrass).setUnlocalizedName("flower1"));
/* 1234 */     registerBlock(38, "red_flower", (new BlockRedFlower()).setHardness(0.0F).setStepSound(soundTypeGrass).setUnlocalizedName("flower2"));
/* 1235 */     Block var3 = (new BlockMushroom()).setHardness(0.0F).setStepSound(soundTypeGrass).setLightLevel(0.125F).setUnlocalizedName("mushroom");
/* 1236 */     registerBlock(39, "brown_mushroom", var3);
/* 1237 */     Block var4 = (new BlockMushroom()).setHardness(0.0F).setStepSound(soundTypeGrass).setUnlocalizedName("mushroom");
/* 1238 */     registerBlock(40, "red_mushroom", var4);
/* 1239 */     registerBlock(41, "gold_block", (new BlockCompressed(MapColor.goldColor)).setHardness(3.0F).setResistance(10.0F).setStepSound(soundTypeMetal).setUnlocalizedName("blockGold"));
/* 1240 */     registerBlock(42, "iron_block", (new BlockCompressed(MapColor.ironColor)).setHardness(5.0F).setResistance(10.0F).setStepSound(soundTypeMetal).setUnlocalizedName("blockIron"));
/* 1241 */     registerBlock(43, "double_stone_slab", (new BlockDoubleStoneSlab()).setHardness(2.0F).setResistance(10.0F).setStepSound(soundTypePiston).setUnlocalizedName("stoneSlab"));
/* 1242 */     registerBlock(44, "stone_slab", (new BlockHalfStoneSlab()).setHardness(2.0F).setResistance(10.0F).setStepSound(soundTypePiston).setUnlocalizedName("stoneSlab"));
/* 1243 */     Block var5 = (new Block(Material.rock)).setHardness(2.0F).setResistance(10.0F).setStepSound(soundTypePiston).setUnlocalizedName("brick").setCreativeTab(CreativeTabs.tabBlock);
/* 1244 */     registerBlock(45, "brick_block", var5);
/* 1245 */     registerBlock(46, "tnt", (new BlockTNT()).setHardness(0.0F).setStepSound(soundTypeGrass).setUnlocalizedName("tnt"));
/* 1246 */     registerBlock(47, "bookshelf", (new BlockBookshelf()).setHardness(1.5F).setStepSound(soundTypeWood).setUnlocalizedName("bookshelf"));
/* 1247 */     registerBlock(48, "mossy_cobblestone", (new Block(Material.rock)).setHardness(2.0F).setResistance(10.0F).setStepSound(soundTypePiston).setUnlocalizedName("stoneMoss").setCreativeTab(CreativeTabs.tabBlock));
/* 1248 */     registerBlock(49, "obsidian", (new BlockObsidian()).setHardness(50.0F).setResistance(2000.0F).setStepSound(soundTypePiston).setUnlocalizedName("obsidian"));
/* 1249 */     registerBlock(50, "torch", (new BlockTorch()).setHardness(0.0F).setLightLevel(0.9375F).setStepSound(soundTypeWood).setUnlocalizedName("torch"));
/* 1250 */     registerBlock(51, "fire", (new BlockFire()).setHardness(0.0F).setLightLevel(1.0F).setStepSound(soundTypeCloth).setUnlocalizedName("fire").disableStats());
/* 1251 */     registerBlock(52, "mob_spawner", (new BlockMobSpawner()).setHardness(5.0F).setStepSound(soundTypeMetal).setUnlocalizedName("mobSpawner").disableStats());
/* 1252 */     registerBlock(53, "oak_stairs", (new BlockStairs(var1.getDefaultState().withProperty((IProperty)BlockPlanks.VARIANT_PROP, BlockPlanks.EnumType.OAK))).setUnlocalizedName("stairsWood"));
/* 1253 */     registerBlock(54, "chest", (new BlockChest(0)).setHardness(2.5F).setStepSound(soundTypeWood).setUnlocalizedName("chest"));
/* 1254 */     registerBlock(55, "redstone_wire", (new BlockRedstoneWire()).setHardness(0.0F).setStepSound(soundTypeStone).setUnlocalizedName("redstoneDust").disableStats());
/* 1255 */     registerBlock(56, "diamond_ore", (new BlockOre()).setHardness(3.0F).setResistance(5.0F).setStepSound(soundTypePiston).setUnlocalizedName("oreDiamond"));
/* 1256 */     registerBlock(57, "diamond_block", (new BlockCompressed(MapColor.diamondColor)).setHardness(5.0F).setResistance(10.0F).setStepSound(soundTypeMetal).setUnlocalizedName("blockDiamond"));
/* 1257 */     registerBlock(58, "crafting_table", (new BlockWorkbench()).setHardness(2.5F).setStepSound(soundTypeWood).setUnlocalizedName("workbench"));
/* 1258 */     registerBlock(59, "wheat", (new BlockCrops()).setUnlocalizedName("crops"));
/* 1259 */     Block var6 = (new BlockFarmland()).setHardness(0.6F).setStepSound(soundTypeGravel).setUnlocalizedName("farmland");
/* 1260 */     registerBlock(60, "farmland", var6);
/* 1261 */     registerBlock(61, "furnace", (new BlockFurnace(false)).setHardness(3.5F).setStepSound(soundTypePiston).setUnlocalizedName("furnace").setCreativeTab(CreativeTabs.tabDecorations));
/* 1262 */     registerBlock(62, "lit_furnace", (new BlockFurnace(true)).setHardness(3.5F).setStepSound(soundTypePiston).setLightLevel(0.875F).setUnlocalizedName("furnace"));
/* 1263 */     registerBlock(63, "standing_sign", (new BlockStandingSign()).setHardness(1.0F).setStepSound(soundTypeWood).setUnlocalizedName("sign").disableStats());
/* 1264 */     registerBlock(64, "wooden_door", (new BlockDoor(Material.wood)).setHardness(3.0F).setStepSound(soundTypeWood).setUnlocalizedName("doorOak").disableStats());
/* 1265 */     registerBlock(65, "ladder", (new BlockLadder()).setHardness(0.4F).setStepSound(soundTypeLadder).setUnlocalizedName("ladder"));
/* 1266 */     registerBlock(66, "rail", (new BlockRail()).setHardness(0.7F).setStepSound(soundTypeMetal).setUnlocalizedName("rail"));
/* 1267 */     registerBlock(67, "stone_stairs", (new BlockStairs(var0.getDefaultState())).setUnlocalizedName("stairsStone"));
/* 1268 */     registerBlock(68, "wall_sign", (new BlockWallSign()).setHardness(1.0F).setStepSound(soundTypeWood).setUnlocalizedName("sign").disableStats());
/* 1269 */     registerBlock(69, "lever", (new BlockLever()).setHardness(0.5F).setStepSound(soundTypeWood).setUnlocalizedName("lever"));
/* 1270 */     registerBlock(70, "stone_pressure_plate", (new BlockPressurePlate(Material.rock, BlockPressurePlate.Sensitivity.MOBS)).setHardness(0.5F).setStepSound(soundTypePiston).setUnlocalizedName("pressurePlateStone"));
/* 1271 */     registerBlock(71, "iron_door", (new BlockDoor(Material.iron)).setHardness(5.0F).setStepSound(soundTypeMetal).setUnlocalizedName("doorIron").disableStats());
/* 1272 */     registerBlock(72, "wooden_pressure_plate", (new BlockPressurePlate(Material.wood, BlockPressurePlate.Sensitivity.EVERYTHING)).setHardness(0.5F).setStepSound(soundTypeWood).setUnlocalizedName("pressurePlateWood"));
/* 1273 */     registerBlock(73, "redstone_ore", (new BlockRedstoneOre(false)).setHardness(3.0F).setResistance(5.0F).setStepSound(soundTypePiston).setUnlocalizedName("oreRedstone").setCreativeTab(CreativeTabs.tabBlock));
/* 1274 */     registerBlock(74, "lit_redstone_ore", (new BlockRedstoneOre(true)).setLightLevel(0.625F).setHardness(3.0F).setResistance(5.0F).setStepSound(soundTypePiston).setUnlocalizedName("oreRedstone"));
/* 1275 */     registerBlock(75, "unlit_redstone_torch", (new BlockRedstoneTorch(false)).setHardness(0.0F).setStepSound(soundTypeWood).setUnlocalizedName("notGate"));
/* 1276 */     registerBlock(76, "redstone_torch", (new BlockRedstoneTorch(true)).setHardness(0.0F).setLightLevel(0.5F).setStepSound(soundTypeWood).setUnlocalizedName("notGate").setCreativeTab(CreativeTabs.tabRedstone));
/* 1277 */     registerBlock(77, "stone_button", (new BlockButtonStone()).setHardness(0.5F).setStepSound(soundTypePiston).setUnlocalizedName("button"));
/* 1278 */     registerBlock(78, "snow_layer", (new BlockSnow()).setHardness(0.1F).setStepSound(soundTypeSnow).setUnlocalizedName("snow").setLightOpacity(0));
/* 1279 */     registerBlock(79, "ice", (new BlockIce()).setHardness(0.5F).setLightOpacity(3).setStepSound(soundTypeGlass).setUnlocalizedName("ice"));
/* 1280 */     registerBlock(80, "snow", (new BlockSnowBlock()).setHardness(0.2F).setStepSound(soundTypeSnow).setUnlocalizedName("snow"));
/* 1281 */     registerBlock(81, "cactus", (new BlockCactus()).setHardness(0.4F).setStepSound(soundTypeCloth).setUnlocalizedName("cactus"));
/* 1282 */     registerBlock(82, "clay", (new BlockClay()).setHardness(0.6F).setStepSound(soundTypeGravel).setUnlocalizedName("clay"));
/* 1283 */     registerBlock(83, "reeds", (new BlockReed()).setHardness(0.0F).setStepSound(soundTypeGrass).setUnlocalizedName("reeds").disableStats());
/* 1284 */     registerBlock(84, "jukebox", (new BlockJukebox()).setHardness(2.0F).setResistance(10.0F).setStepSound(soundTypePiston).setUnlocalizedName("jukebox"));
/* 1285 */     registerBlock(85, "fence", (new BlockFence(Material.wood)).setHardness(2.0F).setResistance(5.0F).setStepSound(soundTypeWood).setUnlocalizedName("fence"));
/* 1286 */     Block var7 = (new BlockPumpkin()).setHardness(1.0F).setStepSound(soundTypeWood).setUnlocalizedName("pumpkin");
/* 1287 */     registerBlock(86, "pumpkin", var7);
/* 1288 */     registerBlock(87, "netherrack", (new BlockNetherrack()).setHardness(0.4F).setStepSound(soundTypePiston).setUnlocalizedName("hellrock"));
/* 1289 */     registerBlock(88, "soul_sand", (new BlockSoulSand()).setHardness(0.5F).setStepSound(soundTypeSand).setUnlocalizedName("hellsand"));
/* 1290 */     registerBlock(89, "glowstone", (new BlockGlowstone(Material.glass)).setHardness(0.3F).setStepSound(soundTypeGlass).setLightLevel(1.0F).setUnlocalizedName("lightgem"));
/* 1291 */     registerBlock(90, "portal", (new BlockPortal()).setHardness(-1.0F).setStepSound(soundTypeGlass).setLightLevel(0.75F).setUnlocalizedName("portal"));
/* 1292 */     registerBlock(91, "lit_pumpkin", (new BlockPumpkin()).setHardness(1.0F).setStepSound(soundTypeWood).setLightLevel(1.0F).setUnlocalizedName("litpumpkin"));
/* 1293 */     registerBlock(92, "cake", (new BlockCake()).setHardness(0.5F).setStepSound(soundTypeCloth).setUnlocalizedName("cake").disableStats());
/* 1294 */     registerBlock(93, "unpowered_repeater", (new BlockRedstoneRepeater(false)).setHardness(0.0F).setStepSound(soundTypeWood).setUnlocalizedName("diode").disableStats());
/* 1295 */     registerBlock(94, "powered_repeater", (new BlockRedstoneRepeater(true)).setHardness(0.0F).setStepSound(soundTypeWood).setUnlocalizedName("diode").disableStats());
/* 1296 */     registerBlock(95, "stained_glass", (new BlockStainedGlass(Material.glass)).setHardness(0.3F).setStepSound(soundTypeGlass).setUnlocalizedName("stainedGlass"));
/* 1297 */     registerBlock(96, "trapdoor", (new BlockTrapDoor(Material.wood)).setHardness(3.0F).setStepSound(soundTypeWood).setUnlocalizedName("trapdoor").disableStats());
/* 1298 */     registerBlock(97, "monster_egg", (new BlockSilverfish()).setHardness(0.75F).setUnlocalizedName("monsterStoneEgg"));
/* 1299 */     Block var8 = (new BlockStoneBrick()).setHardness(1.5F).setResistance(10.0F).setStepSound(soundTypePiston).setUnlocalizedName("stonebricksmooth");
/* 1300 */     registerBlock(98, "stonebrick", var8);
/* 1301 */     registerBlock(99, "brown_mushroom_block", (new BlockHugeMushroom(Material.wood, var3)).setHardness(0.2F).setStepSound(soundTypeWood).setUnlocalizedName("mushroom"));
/* 1302 */     registerBlock(100, "red_mushroom_block", (new BlockHugeMushroom(Material.wood, var4)).setHardness(0.2F).setStepSound(soundTypeWood).setUnlocalizedName("mushroom"));
/* 1303 */     registerBlock(101, "iron_bars", (new BlockPane(Material.iron, true)).setHardness(5.0F).setResistance(10.0F).setStepSound(soundTypeMetal).setUnlocalizedName("fenceIron"));
/* 1304 */     registerBlock(102, "glass_pane", (new BlockPane(Material.glass, false)).setHardness(0.3F).setStepSound(soundTypeGlass).setUnlocalizedName("thinGlass"));
/* 1305 */     Block var9 = (new BlockMelon()).setHardness(1.0F).setStepSound(soundTypeWood).setUnlocalizedName("melon");
/* 1306 */     registerBlock(103, "melon_block", var9);
/* 1307 */     registerBlock(104, "pumpkin_stem", (new BlockStem(var7)).setHardness(0.0F).setStepSound(soundTypeWood).setUnlocalizedName("pumpkinStem"));
/* 1308 */     registerBlock(105, "melon_stem", (new BlockStem(var9)).setHardness(0.0F).setStepSound(soundTypeWood).setUnlocalizedName("pumpkinStem"));
/* 1309 */     registerBlock(106, "vine", (new BlockVine()).setHardness(0.2F).setStepSound(soundTypeGrass).setUnlocalizedName("vine"));
/* 1310 */     registerBlock(107, "fence_gate", (new BlockFenceGate()).setHardness(2.0F).setResistance(5.0F).setStepSound(soundTypeWood).setUnlocalizedName("fenceGate"));
/* 1311 */     registerBlock(108, "brick_stairs", (new BlockStairs(var5.getDefaultState())).setUnlocalizedName("stairsBrick"));
/* 1312 */     registerBlock(109, "stone_brick_stairs", (new BlockStairs(var8.getDefaultState().withProperty((IProperty)BlockStoneBrick.VARIANT_PROP, BlockStoneBrick.EnumType.DEFAULT))).setUnlocalizedName("stairsStoneBrickSmooth"));
/* 1313 */     registerBlock(110, "mycelium", (new BlockMycelium()).setHardness(0.6F).setStepSound(soundTypeGrass).setUnlocalizedName("mycel"));
/* 1314 */     registerBlock(111, "waterlily", (new BlockLilyPad()).setHardness(0.0F).setStepSound(soundTypeGrass).setUnlocalizedName("waterlily"));
/* 1315 */     Block var10 = (new BlockNetherBrick()).setHardness(2.0F).setResistance(10.0F).setStepSound(soundTypePiston).setUnlocalizedName("netherBrick").setCreativeTab(CreativeTabs.tabBlock);
/* 1316 */     registerBlock(112, "nether_brick", var10);
/* 1317 */     registerBlock(113, "nether_brick_fence", (new BlockFence(Material.rock)).setHardness(2.0F).setResistance(10.0F).setStepSound(soundTypePiston).setUnlocalizedName("netherFence"));
/* 1318 */     registerBlock(114, "nether_brick_stairs", (new BlockStairs(var10.getDefaultState())).setUnlocalizedName("stairsNetherBrick"));
/* 1319 */     registerBlock(115, "nether_wart", (new BlockNetherWart()).setUnlocalizedName("netherStalk"));
/* 1320 */     registerBlock(116, "enchanting_table", (new BlockEnchantmentTable()).setHardness(5.0F).setResistance(2000.0F).setUnlocalizedName("enchantmentTable"));
/* 1321 */     registerBlock(117, "brewing_stand", (new BlockBrewingStand()).setHardness(0.5F).setLightLevel(0.125F).setUnlocalizedName("brewingStand"));
/* 1322 */     registerBlock(118, "cauldron", (new BlockCauldron()).setHardness(2.0F).setUnlocalizedName("cauldron"));
/* 1323 */     registerBlock(119, "end_portal", (new BlockEndPortal(Material.portal)).setHardness(-1.0F).setResistance(6000000.0F));
/* 1324 */     registerBlock(120, "end_portal_frame", (new BlockEndPortalFrame()).setStepSound(soundTypeGlass).setLightLevel(0.125F).setHardness(-1.0F).setUnlocalizedName("endPortalFrame").setResistance(6000000.0F).setCreativeTab(CreativeTabs.tabDecorations));
/* 1325 */     registerBlock(121, "end_stone", (new Block(Material.rock)).setHardness(3.0F).setResistance(15.0F).setStepSound(soundTypePiston).setUnlocalizedName("whiteStone").setCreativeTab(CreativeTabs.tabBlock));
/* 1326 */     registerBlock(122, "dragon_egg", (new BlockDragonEgg()).setHardness(3.0F).setResistance(15.0F).setStepSound(soundTypePiston).setLightLevel(0.125F).setUnlocalizedName("dragonEgg"));
/* 1327 */     registerBlock(123, "redstone_lamp", (new BlockRedstoneLight(false)).setHardness(0.3F).setStepSound(soundTypeGlass).setUnlocalizedName("redstoneLight").setCreativeTab(CreativeTabs.tabRedstone));
/* 1328 */     registerBlock(124, "lit_redstone_lamp", (new BlockRedstoneLight(true)).setHardness(0.3F).setStepSound(soundTypeGlass).setUnlocalizedName("redstoneLight"));
/* 1329 */     registerBlock(125, "double_wooden_slab", (new BlockDoubleWoodSlab()).setHardness(2.0F).setResistance(5.0F).setStepSound(soundTypeWood).setUnlocalizedName("woodSlab"));
/* 1330 */     registerBlock(126, "wooden_slab", (new BlockHalfWoodSlab()).setHardness(2.0F).setResistance(5.0F).setStepSound(soundTypeWood).setUnlocalizedName("woodSlab"));
/* 1331 */     registerBlock(127, "cocoa", (new BlockCocoa()).setHardness(0.2F).setResistance(5.0F).setStepSound(soundTypeWood).setUnlocalizedName("cocoa"));
/* 1332 */     registerBlock(128, "sandstone_stairs", (new BlockStairs(var2.getDefaultState().withProperty((IProperty)BlockSandStone.field_176297_a, BlockSandStone.EnumType.SMOOTH))).setUnlocalizedName("stairsSandStone"));
/* 1333 */     registerBlock(129, "emerald_ore", (new BlockOre()).setHardness(3.0F).setResistance(5.0F).setStepSound(soundTypePiston).setUnlocalizedName("oreEmerald"));
/* 1334 */     registerBlock(130, "ender_chest", (new BlockEnderChest()).setHardness(22.5F).setResistance(1000.0F).setStepSound(soundTypePiston).setUnlocalizedName("enderChest").setLightLevel(0.5F));
/* 1335 */     registerBlock(131, "tripwire_hook", (new BlockTripWireHook()).setUnlocalizedName("tripWireSource"));
/* 1336 */     registerBlock(132, "tripwire", (new BlockTripWire()).setUnlocalizedName("tripWire"));
/* 1337 */     registerBlock(133, "emerald_block", (new BlockCompressed(MapColor.emeraldColor)).setHardness(5.0F).setResistance(10.0F).setStepSound(soundTypeMetal).setUnlocalizedName("blockEmerald"));
/* 1338 */     registerBlock(134, "spruce_stairs", (new BlockStairs(var1.getDefaultState().withProperty((IProperty)BlockPlanks.VARIANT_PROP, BlockPlanks.EnumType.SPRUCE))).setUnlocalizedName("stairsWoodSpruce"));
/* 1339 */     registerBlock(135, "birch_stairs", (new BlockStairs(var1.getDefaultState().withProperty((IProperty)BlockPlanks.VARIANT_PROP, BlockPlanks.EnumType.BIRCH))).setUnlocalizedName("stairsWoodBirch"));
/* 1340 */     registerBlock(136, "jungle_stairs", (new BlockStairs(var1.getDefaultState().withProperty((IProperty)BlockPlanks.VARIANT_PROP, BlockPlanks.EnumType.JUNGLE))).setUnlocalizedName("stairsWoodJungle"));
/* 1341 */     registerBlock(137, "command_block", (new BlockCommandBlock()).setBlockUnbreakable().setResistance(6000000.0F).setUnlocalizedName("commandBlock"));
/* 1342 */     registerBlock(138, "beacon", (new BlockBeacon()).setUnlocalizedName("beacon").setLightLevel(1.0F));
/* 1343 */     registerBlock(139, "cobblestone_wall", (new BlockWall(var0)).setUnlocalizedName("cobbleWall"));
/* 1344 */     registerBlock(140, "flower_pot", (new BlockFlowerPot()).setHardness(0.0F).setStepSound(soundTypeStone).setUnlocalizedName("flowerPot"));
/* 1345 */     registerBlock(141, "carrots", (new BlockCarrot()).setUnlocalizedName("carrots"));
/* 1346 */     registerBlock(142, "potatoes", (new BlockPotato()).setUnlocalizedName("potatoes"));
/* 1347 */     registerBlock(143, "wooden_button", (new BlockButtonWood()).setHardness(0.5F).setStepSound(soundTypeWood).setUnlocalizedName("button"));
/* 1348 */     registerBlock(144, "skull", (new BlockSkull()).setHardness(1.0F).setStepSound(soundTypePiston).setUnlocalizedName("skull"));
/* 1349 */     registerBlock(145, "anvil", (new BlockAnvil()).setHardness(5.0F).setStepSound(soundTypeAnvil).setResistance(2000.0F).setUnlocalizedName("anvil"));
/* 1350 */     registerBlock(146, "trapped_chest", (new BlockChest(1)).setHardness(2.5F).setStepSound(soundTypeWood).setUnlocalizedName("chestTrap"));
/* 1351 */     registerBlock(147, "light_weighted_pressure_plate", (new BlockPressurePlateWeighted("gold_block", Material.iron, 15)).setHardness(0.5F).setStepSound(soundTypeWood).setUnlocalizedName("weightedPlate_light"));
/* 1352 */     registerBlock(148, "heavy_weighted_pressure_plate", (new BlockPressurePlateWeighted("iron_block", Material.iron, 150)).setHardness(0.5F).setStepSound(soundTypeWood).setUnlocalizedName("weightedPlate_heavy"));
/* 1353 */     registerBlock(149, "unpowered_comparator", (new BlockRedstoneComparator(false)).setHardness(0.0F).setStepSound(soundTypeWood).setUnlocalizedName("comparator").disableStats());
/* 1354 */     registerBlock(150, "powered_comparator", (new BlockRedstoneComparator(true)).setHardness(0.0F).setLightLevel(0.625F).setStepSound(soundTypeWood).setUnlocalizedName("comparator").disableStats());
/* 1355 */     registerBlock(151, "daylight_detector", new BlockDaylightDetector(false));
/* 1356 */     registerBlock(152, "redstone_block", (new BlockCompressedPowered(MapColor.tntColor)).setHardness(5.0F).setResistance(10.0F).setStepSound(soundTypeMetal).setUnlocalizedName("blockRedstone"));
/* 1357 */     registerBlock(153, "quartz_ore", (new BlockOre()).setHardness(3.0F).setResistance(5.0F).setStepSound(soundTypePiston).setUnlocalizedName("netherquartz"));
/* 1358 */     registerBlock(154, "hopper", (new BlockHopper()).setHardness(3.0F).setResistance(8.0F).setStepSound(soundTypeMetal).setUnlocalizedName("hopper"));
/* 1359 */     Block var11 = (new BlockQuartz()).setStepSound(soundTypePiston).setHardness(0.8F).setUnlocalizedName("quartzBlock");
/* 1360 */     registerBlock(155, "quartz_block", var11);
/* 1361 */     registerBlock(156, "quartz_stairs", (new BlockStairs(var11.getDefaultState().withProperty((IProperty)BlockQuartz.VARIANT_PROP, BlockQuartz.EnumType.DEFAULT))).setUnlocalizedName("stairsQuartz"));
/* 1362 */     registerBlock(157, "activator_rail", (new BlockRailPowered()).setHardness(0.7F).setStepSound(soundTypeMetal).setUnlocalizedName("activatorRail"));
/* 1363 */     registerBlock(158, "dropper", (new BlockDropper()).setHardness(3.5F).setStepSound(soundTypePiston).setUnlocalizedName("dropper"));
/* 1364 */     registerBlock(159, "stained_hardened_clay", (new BlockColored(Material.rock)).setHardness(1.25F).setResistance(7.0F).setStepSound(soundTypePiston).setUnlocalizedName("clayHardenedStained"));
/* 1365 */     registerBlock(160, "stained_glass_pane", (new BlockStainedGlassPane()).setHardness(0.3F).setStepSound(soundTypeGlass).setUnlocalizedName("thinStainedGlass"));
/* 1366 */     registerBlock(161, "leaves2", (new BlockNewLeaf()).setUnlocalizedName("leaves"));
/* 1367 */     registerBlock(162, "log2", (new BlockNewLog()).setUnlocalizedName("log"));
/* 1368 */     registerBlock(163, "acacia_stairs", (new BlockStairs(var1.getDefaultState().withProperty((IProperty)BlockPlanks.VARIANT_PROP, BlockPlanks.EnumType.ACACIA))).setUnlocalizedName("stairsWoodAcacia"));
/* 1369 */     registerBlock(164, "dark_oak_stairs", (new BlockStairs(var1.getDefaultState().withProperty((IProperty)BlockPlanks.VARIANT_PROP, BlockPlanks.EnumType.DARK_OAK))).setUnlocalizedName("stairsWoodDarkOak"));
/* 1370 */     registerBlock(165, "slime", (new BlockSlime()).setUnlocalizedName("slime").setStepSound(SLIME_SOUND));
/* 1371 */     registerBlock(166, "barrier", (new BlockBarrier()).setUnlocalizedName("barrier"));
/* 1372 */     registerBlock(167, "iron_trapdoor", (new BlockTrapDoor(Material.iron)).setHardness(5.0F).setStepSound(soundTypeMetal).setUnlocalizedName("ironTrapdoor").disableStats());
/* 1373 */     registerBlock(168, "prismarine", (new BlockPrismarine()).setHardness(1.5F).setResistance(10.0F).setStepSound(soundTypePiston).setUnlocalizedName("prismarine"));
/* 1374 */     registerBlock(169, "sea_lantern", (new BlockSeaLantern(Material.glass)).setHardness(0.3F).setStepSound(soundTypeGlass).setLightLevel(1.0F).setUnlocalizedName("seaLantern"));
/* 1375 */     registerBlock(170, "hay_block", (new BlockHay()).setHardness(0.5F).setStepSound(soundTypeGrass).setUnlocalizedName("hayBlock").setCreativeTab(CreativeTabs.tabBlock));
/* 1376 */     registerBlock(171, "carpet", (new BlockCarpet()).setHardness(0.1F).setStepSound(soundTypeCloth).setUnlocalizedName("woolCarpet").setLightOpacity(0));
/* 1377 */     registerBlock(172, "hardened_clay", (new BlockHardenedClay()).setHardness(1.25F).setResistance(7.0F).setStepSound(soundTypePiston).setUnlocalizedName("clayHardened"));
/* 1378 */     registerBlock(173, "coal_block", (new Block(Material.rock)).setHardness(5.0F).setResistance(10.0F).setStepSound(soundTypePiston).setUnlocalizedName("blockCoal").setCreativeTab(CreativeTabs.tabBlock));
/* 1379 */     registerBlock(174, "packed_ice", (new BlockPackedIce()).setHardness(0.5F).setStepSound(soundTypeGlass).setUnlocalizedName("icePacked"));
/* 1380 */     registerBlock(175, "double_plant", new BlockDoublePlant());
/* 1381 */     registerBlock(176, "standing_banner", (new BlockBanner.BlockBannerStanding()).setHardness(1.0F).setStepSound(soundTypeWood).setUnlocalizedName("banner").disableStats());
/* 1382 */     registerBlock(177, "wall_banner", (new BlockBanner.BlockBannerHanging()).setHardness(1.0F).setStepSound(soundTypeWood).setUnlocalizedName("banner").disableStats());
/* 1383 */     registerBlock(178, "daylight_detector_inverted", new BlockDaylightDetector(true));
/* 1384 */     Block var12 = (new BlockRedSandstone()).setStepSound(soundTypePiston).setHardness(0.8F).setUnlocalizedName("redSandStone");
/* 1385 */     registerBlock(179, "red_sandstone", var12);
/* 1386 */     registerBlock(180, "red_sandstone_stairs", (new BlockStairs(var12.getDefaultState().withProperty((IProperty)BlockRedSandstone.TYPE, BlockRedSandstone.EnumType.SMOOTH))).setUnlocalizedName("stairsRedSandStone"));
/* 1387 */     registerBlock(181, "double_stone_slab2", (new BlockDoubleStoneSlabNew()).setHardness(2.0F).setResistance(10.0F).setStepSound(soundTypePiston).setUnlocalizedName("stoneSlab2"));
/* 1388 */     registerBlock(182, "stone_slab2", (new BlockHalfStoneSlabNew()).setHardness(2.0F).setResistance(10.0F).setStepSound(soundTypePiston).setUnlocalizedName("stoneSlab2"));
/* 1389 */     registerBlock(183, "spruce_fence_gate", (new BlockFenceGate()).setHardness(2.0F).setResistance(5.0F).setStepSound(soundTypeWood).setUnlocalizedName("spruceFenceGate"));
/* 1390 */     registerBlock(184, "birch_fence_gate", (new BlockFenceGate()).setHardness(2.0F).setResistance(5.0F).setStepSound(soundTypeWood).setUnlocalizedName("birchFenceGate"));
/* 1391 */     registerBlock(185, "jungle_fence_gate", (new BlockFenceGate()).setHardness(2.0F).setResistance(5.0F).setStepSound(soundTypeWood).setUnlocalizedName("jungleFenceGate"));
/* 1392 */     registerBlock(186, "dark_oak_fence_gate", (new BlockFenceGate()).setHardness(2.0F).setResistance(5.0F).setStepSound(soundTypeWood).setUnlocalizedName("darkOakFenceGate"));
/* 1393 */     registerBlock(187, "acacia_fence_gate", (new BlockFenceGate()).setHardness(2.0F).setResistance(5.0F).setStepSound(soundTypeWood).setUnlocalizedName("acaciaFenceGate"));
/* 1394 */     registerBlock(188, "spruce_fence", (new BlockFence(Material.wood)).setHardness(2.0F).setResistance(5.0F).setStepSound(soundTypeWood).setUnlocalizedName("spruceFence"));
/* 1395 */     registerBlock(189, "birch_fence", (new BlockFence(Material.wood)).setHardness(2.0F).setResistance(5.0F).setStepSound(soundTypeWood).setUnlocalizedName("birchFence"));
/* 1396 */     registerBlock(190, "jungle_fence", (new BlockFence(Material.wood)).setHardness(2.0F).setResistance(5.0F).setStepSound(soundTypeWood).setUnlocalizedName("jungleFence"));
/* 1397 */     registerBlock(191, "dark_oak_fence", (new BlockFence(Material.wood)).setHardness(2.0F).setResistance(5.0F).setStepSound(soundTypeWood).setUnlocalizedName("darkOakFence"));
/* 1398 */     registerBlock(192, "acacia_fence", (new BlockFence(Material.wood)).setHardness(2.0F).setResistance(5.0F).setStepSound(soundTypeWood).setUnlocalizedName("acaciaFence"));
/* 1399 */     registerBlock(193, "spruce_door", (new BlockDoor(Material.wood)).setHardness(3.0F).setStepSound(soundTypeWood).setUnlocalizedName("doorSpruce").disableStats());
/* 1400 */     registerBlock(194, "birch_door", (new BlockDoor(Material.wood)).setHardness(3.0F).setStepSound(soundTypeWood).setUnlocalizedName("doorBirch").disableStats());
/* 1401 */     registerBlock(195, "jungle_door", (new BlockDoor(Material.wood)).setHardness(3.0F).setStepSound(soundTypeWood).setUnlocalizedName("doorJungle").disableStats());
/* 1402 */     registerBlock(196, "acacia_door", (new BlockDoor(Material.wood)).setHardness(3.0F).setStepSound(soundTypeWood).setUnlocalizedName("doorAcacia").disableStats());
/* 1403 */     registerBlock(197, "dark_oak_door", (new BlockDoor(Material.wood)).setHardness(3.0F).setStepSound(soundTypeWood).setUnlocalizedName("doorDarkOak").disableStats());
/* 1404 */     blockRegistry.validateKey();
/* 1405 */     Iterator<Block> var13 = blockRegistry.iterator();
/*      */ 
/*      */     
/* 1408 */     while (var13.hasNext()) {
/*      */       
/* 1410 */       Block var14 = var13.next();
/*      */       
/* 1412 */       if (var14.blockMaterial == Material.air) {
/*      */         
/* 1414 */         var14.useNeighborBrightness = false;
/*      */         
/*      */         continue;
/*      */       } 
/* 1418 */       boolean var15 = false;
/* 1419 */       boolean var16 = var14 instanceof BlockStairs;
/* 1420 */       boolean var17 = var14 instanceof BlockSlab;
/* 1421 */       boolean var18 = (var14 == var6);
/* 1422 */       boolean var19 = var14.translucent;
/* 1423 */       boolean var20 = (var14.lightOpacity == 0);
/*      */       
/* 1425 */       if (var16 || var17 || var18 || var19 || var20)
/*      */       {
/* 1427 */         var15 = true;
/*      */       }
/*      */       
/* 1430 */       var14.useNeighborBrightness = var15;
/*      */     } 
/*      */ 
/*      */     
/* 1434 */     var13 = blockRegistry.iterator();
/*      */     
/* 1436 */     while (var13.hasNext()) {
/*      */       
/* 1438 */       Block var14 = var13.next();
/* 1439 */       UnmodifiableIterator<IBlockState> unmodifiableIterator = var14.getBlockState().getValidStates().iterator();
/*      */       
/* 1441 */       while (unmodifiableIterator.hasNext()) {
/*      */         
/* 1443 */         IBlockState var22 = unmodifiableIterator.next();
/* 1444 */         int var23 = blockRegistry.getIDForObject(var14) << 4 | var14.getMetaFromState(var22);
/* 1445 */         BLOCK_STATE_IDS.put(var22, var23);
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   private static void registerBlock(int id, ResourceLocation textualID, Block block_) {
/* 1452 */     blockRegistry.register(id, textualID, block_);
/*      */   }
/*      */ 
/*      */   
/*      */   private static void registerBlock(int id, String textualID, Block block_) {
/* 1457 */     registerBlock(id, new ResourceLocation(textualID), block_);
/*      */   }
/*      */   
/*      */   public enum EnumOffsetType
/*      */   {
/* 1462 */     NONE("NONE", 0),
/* 1463 */     XZ("XZ", 1),
/* 1464 */     XYZ("XYZ", 2);
/*      */     
/* 1466 */     private static final EnumOffsetType[] $VALUES = new EnumOffsetType[] { NONE, XZ, XYZ };
/*      */     private static final String __OBFID = "CL_00002132";
/*      */     
/*      */     static {
/*      */     
/*      */     }
/*      */   }
/*      */   
/*      */   public static class SoundType {
/*      */     public final String soundName;
/*      */     public final float volume;
/*      */     public final float frequency;
/*      */     private static final String __OBFID = "CL_00000203";
/*      */     
/*      */     public SoundType(String name, float volume, float frequency) {
/* 1481 */       this.soundName = name;
/* 1482 */       this.volume = volume;
/* 1483 */       this.frequency = frequency;
/*      */     }
/*      */ 
/*      */     
/*      */     public float getVolume() {
/* 1488 */       return this.volume;
/*      */     }
/*      */ 
/*      */     
/*      */     public float getFrequency() {
/* 1493 */       return this.frequency;
/*      */     }
/*      */ 
/*      */     
/*      */     public String getBreakSound() {
/* 1498 */       return "dig." + this.soundName;
/*      */     }
/*      */ 
/*      */     
/*      */     public String getStepSound() {
/* 1503 */       return "step." + this.soundName;
/*      */     }
/*      */ 
/*      */     
/*      */     public String getPlaceSound() {
/* 1508 */       return getBreakSound();
/*      */     }
/*      */   }
/*      */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\block\Block.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */