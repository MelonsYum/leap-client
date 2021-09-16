/*     */ package net.minecraft.block;
/*     */ 
/*     */ import com.google.common.base.Predicate;
/*     */ import java.util.Iterator;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.block.properties.IProperty;
/*     */ import net.minecraft.block.properties.PropertyDirection;
/*     */ import net.minecraft.block.state.BlockState;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.creativetab.CreativeTabs;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.passive.EntityOcelot;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.inventory.Container;
/*     */ import net.minecraft.inventory.IInventory;
/*     */ import net.minecraft.inventory.InventoryHelper;
/*     */ import net.minecraft.inventory.InventoryLargeChest;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.tileentity.TileEntityChest;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.ILockableContainer;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class BlockChest extends BlockContainer {
/*  32 */   public static final PropertyDirection FACING_PROP = PropertyDirection.create("facing", (Predicate)EnumFacing.Plane.HORIZONTAL);
/*  33 */   private final Random rand = new Random();
/*     */   
/*     */   public final int chestType;
/*     */   
/*     */   private static final String __OBFID = "CL_00000214";
/*     */ 
/*     */   
/*     */   protected BlockChest(int type) {
/*  41 */     super(Material.wood);
/*  42 */     setDefaultState(this.blockState.getBaseState().withProperty((IProperty)FACING_PROP, (Comparable)EnumFacing.NORTH));
/*  43 */     this.chestType = type;
/*  44 */     setCreativeTab(CreativeTabs.tabDecorations);
/*  45 */     setBlockBounds(0.0625F, 0.0F, 0.0625F, 0.9375F, 0.875F, 0.9375F);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isOpaqueCube() {
/*  50 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isFullCube() {
/*  55 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getRenderType() {
/*  63 */     return 2;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setBlockBoundsBasedOnState(IBlockAccess access, BlockPos pos) {
/*  68 */     if (access.getBlockState(pos.offsetNorth()).getBlock() == this) {
/*     */       
/*  70 */       setBlockBounds(0.0625F, 0.0F, 0.0F, 0.9375F, 0.875F, 0.9375F);
/*     */     }
/*  72 */     else if (access.getBlockState(pos.offsetSouth()).getBlock() == this) {
/*     */       
/*  74 */       setBlockBounds(0.0625F, 0.0F, 0.0625F, 0.9375F, 0.875F, 1.0F);
/*     */     }
/*  76 */     else if (access.getBlockState(pos.offsetWest()).getBlock() == this) {
/*     */       
/*  78 */       setBlockBounds(0.0F, 0.0F, 0.0625F, 0.9375F, 0.875F, 0.9375F);
/*     */     }
/*  80 */     else if (access.getBlockState(pos.offsetEast()).getBlock() == this) {
/*     */       
/*  82 */       setBlockBounds(0.0625F, 0.0F, 0.0625F, 1.0F, 0.875F, 0.9375F);
/*     */     }
/*     */     else {
/*     */       
/*  86 */       setBlockBounds(0.0625F, 0.0F, 0.0625F, 0.9375F, 0.875F, 0.9375F);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state) {
/*  92 */     checkForSurroundingChests(worldIn, pos, state);
/*  93 */     Iterator<EnumFacing> var4 = EnumFacing.Plane.HORIZONTAL.iterator();
/*     */     
/*  95 */     while (var4.hasNext()) {
/*     */       
/*  97 */       EnumFacing var5 = var4.next();
/*  98 */       BlockPos var6 = pos.offset(var5);
/*  99 */       IBlockState var7 = worldIn.getBlockState(var6);
/*     */       
/* 101 */       if (var7.getBlock() == this)
/*     */       {
/* 103 */         checkForSurroundingChests(worldIn, var6, var7);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockState onBlockPlaced(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
/* 110 */     return getDefaultState().withProperty((IProperty)FACING_PROP, (Comparable)placer.func_174811_aO());
/*     */   }
/*     */ 
/*     */   
/*     */   public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
/* 115 */     EnumFacing var6 = EnumFacing.getHorizontal(MathHelper.floor_double((placer.rotationYaw * 4.0F / 360.0F) + 0.5D) & 0x3).getOpposite();
/* 116 */     state = state.withProperty((IProperty)FACING_PROP, (Comparable)var6);
/* 117 */     BlockPos var7 = pos.offsetNorth();
/* 118 */     BlockPos var8 = pos.offsetSouth();
/* 119 */     BlockPos var9 = pos.offsetWest();
/* 120 */     BlockPos var10 = pos.offsetEast();
/* 121 */     boolean var11 = (this == worldIn.getBlockState(var7).getBlock());
/* 122 */     boolean var12 = (this == worldIn.getBlockState(var8).getBlock());
/* 123 */     boolean var13 = (this == worldIn.getBlockState(var9).getBlock());
/* 124 */     boolean var14 = (this == worldIn.getBlockState(var10).getBlock());
/*     */     
/* 126 */     if (!var11 && !var12 && !var13 && !var14) {
/*     */       
/* 128 */       worldIn.setBlockState(pos, state, 3);
/*     */     }
/* 130 */     else if (var6.getAxis() == EnumFacing.Axis.X && (var11 || var12)) {
/*     */       
/* 132 */       if (var11) {
/*     */         
/* 134 */         worldIn.setBlockState(var7, state, 3);
/*     */       }
/*     */       else {
/*     */         
/* 138 */         worldIn.setBlockState(var8, state, 3);
/*     */       } 
/*     */       
/* 141 */       worldIn.setBlockState(pos, state, 3);
/*     */     }
/* 143 */     else if (var6.getAxis() == EnumFacing.Axis.Z && (var13 || var14)) {
/*     */       
/* 145 */       if (var13) {
/*     */         
/* 147 */         worldIn.setBlockState(var9, state, 3);
/*     */       }
/*     */       else {
/*     */         
/* 151 */         worldIn.setBlockState(var10, state, 3);
/*     */       } 
/*     */       
/* 154 */       worldIn.setBlockState(pos, state, 3);
/*     */     } 
/*     */     
/* 157 */     if (stack.hasDisplayName()) {
/*     */       
/* 159 */       TileEntity var15 = worldIn.getTileEntity(pos);
/*     */       
/* 161 */       if (var15 instanceof TileEntityChest)
/*     */       {
/* 163 */         ((TileEntityChest)var15).setCustomName(stack.getDisplayName());
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockState checkForSurroundingChests(World worldIn, BlockPos p_176455_2_, IBlockState p_176455_3_) {
/* 170 */     if (worldIn.isRemote)
/*     */     {
/* 172 */       return p_176455_3_;
/*     */     }
/*     */ 
/*     */     
/* 176 */     IBlockState var4 = worldIn.getBlockState(p_176455_2_.offsetNorth());
/* 177 */     IBlockState var5 = worldIn.getBlockState(p_176455_2_.offsetSouth());
/* 178 */     IBlockState var6 = worldIn.getBlockState(p_176455_2_.offsetWest());
/* 179 */     IBlockState var7 = worldIn.getBlockState(p_176455_2_.offsetEast());
/* 180 */     EnumFacing var8 = (EnumFacing)p_176455_3_.getValue((IProperty)FACING_PROP);
/* 181 */     Block var9 = var4.getBlock();
/* 182 */     Block var10 = var5.getBlock();
/* 183 */     Block var11 = var6.getBlock();
/* 184 */     Block var12 = var7.getBlock();
/*     */     
/* 186 */     if (var9 != this && var10 != this) {
/*     */       
/* 188 */       boolean var21 = var9.isFullBlock();
/* 189 */       boolean var22 = var10.isFullBlock();
/*     */       
/* 191 */       if (var11 == this || var12 == this) {
/*     */         EnumFacing var26;
/* 193 */         BlockPos var23 = (var11 == this) ? p_176455_2_.offsetWest() : p_176455_2_.offsetEast();
/* 194 */         IBlockState var24 = worldIn.getBlockState(var23.offsetNorth());
/* 195 */         IBlockState var25 = worldIn.getBlockState(var23.offsetSouth());
/* 196 */         var8 = EnumFacing.SOUTH;
/*     */ 
/*     */         
/* 199 */         if (var11 == this) {
/*     */           
/* 201 */           var26 = (EnumFacing)var6.getValue((IProperty)FACING_PROP);
/*     */         }
/*     */         else {
/*     */           
/* 205 */           var26 = (EnumFacing)var7.getValue((IProperty)FACING_PROP);
/*     */         } 
/*     */         
/* 208 */         if (var26 == EnumFacing.NORTH)
/*     */         {
/* 210 */           var8 = EnumFacing.NORTH;
/*     */         }
/*     */         
/* 213 */         Block var19 = var24.getBlock();
/* 214 */         Block var20 = var25.getBlock();
/*     */         
/* 216 */         if ((var21 || var19.isFullBlock()) && !var22 && !var20.isFullBlock())
/*     */         {
/* 218 */           var8 = EnumFacing.SOUTH;
/*     */         }
/*     */         
/* 221 */         if ((var22 || var20.isFullBlock()) && !var21 && !var19.isFullBlock())
/*     */         {
/* 223 */           var8 = EnumFacing.NORTH;
/*     */         }
/*     */       } 
/*     */     } else {
/*     */       EnumFacing var16;
/*     */       
/* 229 */       BlockPos var13 = (var9 == this) ? p_176455_2_.offsetNorth() : p_176455_2_.offsetSouth();
/* 230 */       IBlockState var14 = worldIn.getBlockState(var13.offsetWest());
/* 231 */       IBlockState var15 = worldIn.getBlockState(var13.offsetEast());
/* 232 */       var8 = EnumFacing.EAST;
/*     */ 
/*     */       
/* 235 */       if (var9 == this) {
/*     */         
/* 237 */         var16 = (EnumFacing)var4.getValue((IProperty)FACING_PROP);
/*     */       }
/*     */       else {
/*     */         
/* 241 */         var16 = (EnumFacing)var5.getValue((IProperty)FACING_PROP);
/*     */       } 
/*     */       
/* 244 */       if (var16 == EnumFacing.WEST)
/*     */       {
/* 246 */         var8 = EnumFacing.WEST;
/*     */       }
/*     */       
/* 249 */       Block var17 = var14.getBlock();
/* 250 */       Block var18 = var15.getBlock();
/*     */       
/* 252 */       if ((var11.isFullBlock() || var17.isFullBlock()) && !var12.isFullBlock() && !var18.isFullBlock())
/*     */       {
/* 254 */         var8 = EnumFacing.EAST;
/*     */       }
/*     */       
/* 257 */       if ((var12.isFullBlock() || var18.isFullBlock()) && !var11.isFullBlock() && !var17.isFullBlock())
/*     */       {
/* 259 */         var8 = EnumFacing.WEST;
/*     */       }
/*     */     } 
/*     */     
/* 263 */     p_176455_3_ = p_176455_3_.withProperty((IProperty)FACING_PROP, (Comparable)var8);
/* 264 */     worldIn.setBlockState(p_176455_2_, p_176455_3_, 3);
/* 265 */     return p_176455_3_;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public IBlockState func_176458_f(World worldIn, BlockPos p_176458_2_, IBlockState p_176458_3_) {
/* 271 */     EnumFacing var4 = null;
/* 272 */     Iterator<EnumFacing> var5 = EnumFacing.Plane.HORIZONTAL.iterator();
/*     */     
/* 274 */     while (var5.hasNext()) {
/*     */       
/* 276 */       EnumFacing var6 = var5.next();
/* 277 */       IBlockState var7 = worldIn.getBlockState(p_176458_2_.offset(var6));
/*     */       
/* 279 */       if (var7.getBlock() == this)
/*     */       {
/* 281 */         return p_176458_3_;
/*     */       }
/*     */       
/* 284 */       if (var7.getBlock().isFullBlock()) {
/*     */         
/* 286 */         if (var4 != null) {
/*     */           
/* 288 */           var4 = null;
/*     */           
/*     */           break;
/*     */         } 
/* 292 */         var4 = var6;
/*     */       } 
/*     */     } 
/*     */     
/* 296 */     if (var4 != null)
/*     */     {
/* 298 */       return p_176458_3_.withProperty((IProperty)FACING_PROP, (Comparable)var4.getOpposite());
/*     */     }
/*     */ 
/*     */     
/* 302 */     EnumFacing var8 = (EnumFacing)p_176458_3_.getValue((IProperty)FACING_PROP);
/*     */     
/* 304 */     if (worldIn.getBlockState(p_176458_2_.offset(var8)).getBlock().isFullBlock())
/*     */     {
/* 306 */       var8 = var8.getOpposite();
/*     */     }
/*     */     
/* 309 */     if (worldIn.getBlockState(p_176458_2_.offset(var8)).getBlock().isFullBlock())
/*     */     {
/* 311 */       var8 = var8.rotateY();
/*     */     }
/*     */     
/* 314 */     if (worldIn.getBlockState(p_176458_2_.offset(var8)).getBlock().isFullBlock())
/*     */     {
/* 316 */       var8 = var8.getOpposite();
/*     */     }
/*     */     
/* 319 */     return p_176458_3_.withProperty((IProperty)FACING_PROP, (Comparable)var8);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canPlaceBlockAt(World worldIn, BlockPos pos) {
/* 325 */     int var3 = 0;
/* 326 */     BlockPos var4 = pos.offsetWest();
/* 327 */     BlockPos var5 = pos.offsetEast();
/* 328 */     BlockPos var6 = pos.offsetNorth();
/* 329 */     BlockPos var7 = pos.offsetSouth();
/*     */     
/* 331 */     if (worldIn.getBlockState(var4).getBlock() == this) {
/*     */       
/* 333 */       if (isSurroundingBlockChest(worldIn, var4))
/*     */       {
/* 335 */         return false;
/*     */       }
/*     */       
/* 338 */       var3++;
/*     */     } 
/*     */     
/* 341 */     if (worldIn.getBlockState(var5).getBlock() == this) {
/*     */       
/* 343 */       if (isSurroundingBlockChest(worldIn, var5))
/*     */       {
/* 345 */         return false;
/*     */       }
/*     */       
/* 348 */       var3++;
/*     */     } 
/*     */     
/* 351 */     if (worldIn.getBlockState(var6).getBlock() == this) {
/*     */       
/* 353 */       if (isSurroundingBlockChest(worldIn, var6))
/*     */       {
/* 355 */         return false;
/*     */       }
/*     */       
/* 358 */       var3++;
/*     */     } 
/*     */     
/* 361 */     if (worldIn.getBlockState(var7).getBlock() == this) {
/*     */       
/* 363 */       if (isSurroundingBlockChest(worldIn, var7))
/*     */       {
/* 365 */         return false;
/*     */       }
/*     */       
/* 368 */       var3++;
/*     */     } 
/*     */     
/* 371 */     return (var3 <= 1);
/*     */   }
/*     */   
/*     */   private boolean isSurroundingBlockChest(World worldIn, BlockPos p_176454_2_) {
/*     */     EnumFacing var4;
/* 376 */     if (worldIn.getBlockState(p_176454_2_).getBlock() != this)
/*     */     {
/* 378 */       return false;
/*     */     }
/*     */ 
/*     */     
/* 382 */     Iterator<EnumFacing> var3 = EnumFacing.Plane.HORIZONTAL.iterator();
/*     */ 
/*     */ 
/*     */     
/*     */     do {
/* 387 */       if (!var3.hasNext())
/*     */       {
/* 389 */         return false;
/*     */       }
/*     */       
/* 392 */       var4 = var3.next();
/*     */     }
/* 394 */     while (worldIn.getBlockState(p_176454_2_.offset(var4)).getBlock() != this);
/*     */     
/* 396 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void onNeighborBlockChange(World worldIn, BlockPos pos, IBlockState state, Block neighborBlock) {
/* 402 */     super.onNeighborBlockChange(worldIn, pos, state, neighborBlock);
/* 403 */     TileEntity var5 = worldIn.getTileEntity(pos);
/*     */     
/* 405 */     if (var5 instanceof TileEntityChest)
/*     */     {
/* 407 */       var5.updateContainingBlockInfo();
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
/* 413 */     TileEntity var4 = worldIn.getTileEntity(pos);
/*     */     
/* 415 */     if (var4 instanceof IInventory) {
/*     */       
/* 417 */       InventoryHelper.dropInventoryItems(worldIn, pos, (IInventory)var4);
/* 418 */       worldIn.updateComparatorOutputLevel(pos, this);
/*     */     } 
/*     */     
/* 421 */     super.breakBlock(worldIn, pos, state);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumFacing side, float hitX, float hitY, float hitZ) {
/* 426 */     if (worldIn.isRemote)
/*     */     {
/* 428 */       return true;
/*     */     }
/*     */ 
/*     */     
/* 432 */     ILockableContainer var9 = getLockableContainer(worldIn, pos);
/*     */     
/* 434 */     if (var9 != null)
/*     */     {
/* 436 */       playerIn.displayGUIChest((IInventory)var9);
/*     */     }
/*     */     
/* 439 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ILockableContainer getLockableContainer(World worldIn, BlockPos p_180676_2_) {
/* 445 */     TileEntity var3 = worldIn.getTileEntity(p_180676_2_);
/*     */     
/* 447 */     if (!(var3 instanceof TileEntityChest))
/*     */     {
/* 449 */       return null;
/*     */     }
/*     */ 
/*     */     
/* 453 */     Object var4 = var3;
/*     */     
/* 455 */     if (cannotOpenChest(worldIn, p_180676_2_))
/*     */     {
/* 457 */       return null;
/*     */     }
/*     */ 
/*     */     
/* 461 */     Iterator<EnumFacing> var5 = EnumFacing.Plane.HORIZONTAL.iterator();
/*     */     
/* 463 */     while (var5.hasNext()) {
/*     */       
/* 465 */       EnumFacing var6 = var5.next();
/* 466 */       BlockPos var7 = p_180676_2_.offset(var6);
/* 467 */       Block var8 = worldIn.getBlockState(var7).getBlock();
/*     */       
/* 469 */       if (var8 == this) {
/*     */         
/* 471 */         if (cannotOpenChest(worldIn, var7))
/*     */         {
/* 473 */           return null;
/*     */         }
/*     */         
/* 476 */         TileEntity var9 = worldIn.getTileEntity(var7);
/*     */         
/* 478 */         if (var9 instanceof TileEntityChest) {
/*     */           
/* 480 */           if (var6 != EnumFacing.WEST && var6 != EnumFacing.NORTH) {
/*     */             
/* 482 */             var4 = new InventoryLargeChest("container.chestDouble", (ILockableContainer)var4, (ILockableContainer)var9);
/*     */             
/*     */             continue;
/*     */           } 
/* 486 */           var4 = new InventoryLargeChest("container.chestDouble", (ILockableContainer)var9, (ILockableContainer)var4);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 492 */     return (ILockableContainer)var4;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public TileEntity createNewTileEntity(World worldIn, int meta) {
/* 502 */     return (TileEntity)new TileEntityChest();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canProvidePower() {
/* 510 */     return (this.chestType == 1);
/*     */   }
/*     */ 
/*     */   
/*     */   public int isProvidingWeakPower(IBlockAccess worldIn, BlockPos pos, IBlockState state, EnumFacing side) {
/* 515 */     if (!canProvidePower())
/*     */     {
/* 517 */       return 0;
/*     */     }
/*     */ 
/*     */     
/* 521 */     int var5 = 0;
/* 522 */     TileEntity var6 = worldIn.getTileEntity(pos);
/*     */     
/* 524 */     if (var6 instanceof TileEntityChest)
/*     */     {
/* 526 */       var5 = ((TileEntityChest)var6).numPlayersUsing;
/*     */     }
/*     */     
/* 529 */     return MathHelper.clamp_int(var5, 0, 15);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int isProvidingStrongPower(IBlockAccess worldIn, BlockPos pos, IBlockState state, EnumFacing side) {
/* 535 */     return (side == EnumFacing.UP) ? isProvidingWeakPower(worldIn, pos, state, side) : 0;
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean cannotOpenChest(World worldIn, BlockPos p_176457_2_) {
/* 540 */     return !(!isBelowSolidBlock(worldIn, p_176457_2_) && !isOcelotSittingOnChest(worldIn, p_176457_2_));
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean isBelowSolidBlock(World worldIn, BlockPos p_176456_2_) {
/* 545 */     return worldIn.getBlockState(p_176456_2_.offsetUp()).getBlock().isNormalCube();
/*     */   }
/*     */   
/*     */   private boolean isOcelotSittingOnChest(World worldIn, BlockPos p_176453_2_) {
/*     */     EntityOcelot var5;
/* 550 */     Iterator<Entity> var3 = worldIn.getEntitiesWithinAABB(EntityOcelot.class, new AxisAlignedBB(p_176453_2_.getX(), (p_176453_2_.getY() + 1), p_176453_2_.getZ(), (p_176453_2_.getX() + 1), (p_176453_2_.getY() + 2), (p_176453_2_.getZ() + 1))).iterator();
/*     */ 
/*     */ 
/*     */     
/*     */     do {
/* 555 */       if (!var3.hasNext())
/*     */       {
/* 557 */         return false;
/*     */       }
/*     */       
/* 560 */       Entity var4 = var3.next();
/* 561 */       var5 = (EntityOcelot)var4;
/*     */     }
/* 563 */     while (!var5.isSitting());
/*     */     
/* 565 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hasComparatorInputOverride() {
/* 570 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getComparatorInputOverride(World worldIn, BlockPos pos) {
/* 575 */     return Container.calcRedstoneFromInventory((IInventory)getLockableContainer(worldIn, pos));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IBlockState getStateFromMeta(int meta) {
/* 583 */     EnumFacing var2 = EnumFacing.getFront(meta);
/*     */     
/* 585 */     if (var2.getAxis() == EnumFacing.Axis.Y)
/*     */     {
/* 587 */       var2 = EnumFacing.NORTH;
/*     */     }
/*     */     
/* 590 */     return getDefaultState().withProperty((IProperty)FACING_PROP, (Comparable)var2);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getMetaFromState(IBlockState state) {
/* 598 */     return ((EnumFacing)state.getValue((IProperty)FACING_PROP)).getIndex();
/*     */   }
/*     */ 
/*     */   
/*     */   protected BlockState createBlockState() {
/* 603 */     return new BlockState(this, new IProperty[] { (IProperty)FACING_PROP });
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\block\BlockChest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */