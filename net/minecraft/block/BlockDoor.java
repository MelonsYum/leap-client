/*     */ package net.minecraft.block;
/*     */ 
/*     */ import com.google.common.base.Predicate;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.block.properties.IProperty;
/*     */ import net.minecraft.block.properties.PropertyBool;
/*     */ import net.minecraft.block.properties.PropertyDirection;
/*     */ import net.minecraft.block.properties.PropertyEnum;
/*     */ import net.minecraft.block.state.BlockState;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.EnumWorldBlockLayer;
/*     */ import net.minecraft.util.IStringSerializable;
/*     */ import net.minecraft.util.MovingObjectPosition;
/*     */ import net.minecraft.util.Vec3;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class BlockDoor extends Block {
/*  27 */   public static final PropertyDirection FACING_PROP = PropertyDirection.create("facing", (Predicate)EnumFacing.Plane.HORIZONTAL);
/*  28 */   public static final PropertyBool OPEN_PROP = PropertyBool.create("open");
/*  29 */   public static final PropertyEnum HINGEPOSITION_PROP = PropertyEnum.create("hinge", EnumHingePosition.class);
/*  30 */   public static final PropertyBool POWERED_PROP = PropertyBool.create("powered");
/*  31 */   public static final PropertyEnum HALF_PROP = PropertyEnum.create("half", EnumDoorHalf.class);
/*     */   
/*     */   private static final String __OBFID = "CL_00000230";
/*     */   
/*     */   protected BlockDoor(Material p_i45402_1_) {
/*  36 */     super(p_i45402_1_);
/*  37 */     setDefaultState(this.blockState.getBaseState().withProperty((IProperty)FACING_PROP, (Comparable)EnumFacing.NORTH).withProperty((IProperty)OPEN_PROP, Boolean.valueOf(false)).withProperty((IProperty)HINGEPOSITION_PROP, EnumHingePosition.LEFT).withProperty((IProperty)POWERED_PROP, Boolean.valueOf(false)).withProperty((IProperty)HALF_PROP, EnumDoorHalf.LOWER));
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isOpaqueCube() {
/*  42 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isPassable(IBlockAccess blockAccess, BlockPos pos) {
/*  47 */     return func_176516_g(func_176515_e(blockAccess, pos));
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isFullCube() {
/*  52 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public AxisAlignedBB getSelectedBoundingBox(World worldIn, BlockPos pos) {
/*  57 */     setBlockBoundsBasedOnState((IBlockAccess)worldIn, pos);
/*  58 */     return super.getSelectedBoundingBox(worldIn, pos);
/*     */   }
/*     */ 
/*     */   
/*     */   public AxisAlignedBB getCollisionBoundingBox(World worldIn, BlockPos pos, IBlockState state) {
/*  63 */     setBlockBoundsBasedOnState((IBlockAccess)worldIn, pos);
/*  64 */     return super.getCollisionBoundingBox(worldIn, pos, state);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setBlockBoundsBasedOnState(IBlockAccess access, BlockPos pos) {
/*  69 */     func_150011_b(func_176515_e(access, pos));
/*     */   }
/*     */ 
/*     */   
/*     */   private void func_150011_b(int p_150011_1_) {
/*  74 */     float var2 = 0.1875F;
/*  75 */     setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 2.0F, 1.0F);
/*  76 */     EnumFacing var3 = func_176511_f(p_150011_1_);
/*  77 */     boolean var4 = func_176516_g(p_150011_1_);
/*  78 */     boolean var5 = func_176513_j(p_150011_1_);
/*     */     
/*  80 */     if (var4) {
/*     */       
/*  82 */       if (var3 == EnumFacing.EAST) {
/*     */         
/*  84 */         if (!var5)
/*     */         {
/*  86 */           setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, var2);
/*     */         }
/*     */         else
/*     */         {
/*  90 */           setBlockBounds(0.0F, 0.0F, 1.0F - var2, 1.0F, 1.0F, 1.0F);
/*     */         }
/*     */       
/*  93 */       } else if (var3 == EnumFacing.SOUTH) {
/*     */         
/*  95 */         if (!var5)
/*     */         {
/*  97 */           setBlockBounds(1.0F - var2, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
/*     */         }
/*     */         else
/*     */         {
/* 101 */           setBlockBounds(0.0F, 0.0F, 0.0F, var2, 1.0F, 1.0F);
/*     */         }
/*     */       
/* 104 */       } else if (var3 == EnumFacing.WEST) {
/*     */         
/* 106 */         if (!var5)
/*     */         {
/* 108 */           setBlockBounds(0.0F, 0.0F, 1.0F - var2, 1.0F, 1.0F, 1.0F);
/*     */         }
/*     */         else
/*     */         {
/* 112 */           setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, var2);
/*     */         }
/*     */       
/* 115 */       } else if (var3 == EnumFacing.NORTH) {
/*     */         
/* 117 */         if (!var5)
/*     */         {
/* 119 */           setBlockBounds(0.0F, 0.0F, 0.0F, var2, 1.0F, 1.0F);
/*     */         }
/*     */         else
/*     */         {
/* 123 */           setBlockBounds(1.0F - var2, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
/*     */         }
/*     */       
/*     */       } 
/* 127 */     } else if (var3 == EnumFacing.EAST) {
/*     */       
/* 129 */       setBlockBounds(0.0F, 0.0F, 0.0F, var2, 1.0F, 1.0F);
/*     */     }
/* 131 */     else if (var3 == EnumFacing.SOUTH) {
/*     */       
/* 133 */       setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, var2);
/*     */     }
/* 135 */     else if (var3 == EnumFacing.WEST) {
/*     */       
/* 137 */       setBlockBounds(1.0F - var2, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
/*     */     }
/* 139 */     else if (var3 == EnumFacing.NORTH) {
/*     */       
/* 141 */       setBlockBounds(0.0F, 0.0F, 1.0F - var2, 1.0F, 1.0F, 1.0F);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumFacing side, float hitX, float hitY, float hitZ) {
/* 147 */     if (this.blockMaterial == Material.iron)
/*     */     {
/* 149 */       return true;
/*     */     }
/*     */ 
/*     */     
/* 153 */     BlockPos var9 = (state.getValue((IProperty)HALF_PROP) == EnumDoorHalf.LOWER) ? pos : pos.offsetDown();
/* 154 */     IBlockState var10 = pos.equals(var9) ? state : worldIn.getBlockState(var9);
/*     */     
/* 156 */     if (var10.getBlock() != this)
/*     */     {
/* 158 */       return false;
/*     */     }
/*     */ 
/*     */     
/* 162 */     state = var10.cycleProperty((IProperty)OPEN_PROP);
/* 163 */     worldIn.setBlockState(var9, state, 2);
/* 164 */     worldIn.markBlockRangeForRenderUpdate(var9, pos);
/* 165 */     worldIn.playAuxSFXAtEntity(playerIn, ((Boolean)state.getValue((IProperty)OPEN_PROP)).booleanValue() ? 1003 : 1006, pos, 0);
/* 166 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_176512_a(World worldIn, BlockPos p_176512_2_, boolean p_176512_3_) {
/* 173 */     IBlockState var4 = worldIn.getBlockState(p_176512_2_);
/*     */     
/* 175 */     if (var4.getBlock() == this) {
/*     */       
/* 177 */       BlockPos var5 = (var4.getValue((IProperty)HALF_PROP) == EnumDoorHalf.LOWER) ? p_176512_2_ : p_176512_2_.offsetDown();
/* 178 */       IBlockState var6 = (p_176512_2_ == var5) ? var4 : worldIn.getBlockState(var5);
/*     */       
/* 180 */       if (var6.getBlock() == this && ((Boolean)var6.getValue((IProperty)OPEN_PROP)).booleanValue() != p_176512_3_) {
/*     */         
/* 182 */         worldIn.setBlockState(var5, var6.withProperty((IProperty)OPEN_PROP, Boolean.valueOf(p_176512_3_)), 2);
/* 183 */         worldIn.markBlockRangeForRenderUpdate(var5, p_176512_2_);
/* 184 */         worldIn.playAuxSFXAtEntity(null, p_176512_3_ ? 1003 : 1006, p_176512_2_, 0);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void onNeighborBlockChange(World worldIn, BlockPos pos, IBlockState state, Block neighborBlock) {
/* 191 */     if (state.getValue((IProperty)HALF_PROP) == EnumDoorHalf.UPPER) {
/*     */       
/* 193 */       BlockPos var5 = pos.offsetDown();
/* 194 */       IBlockState var6 = worldIn.getBlockState(var5);
/*     */       
/* 196 */       if (var6.getBlock() != this)
/*     */       {
/* 198 */         worldIn.setBlockToAir(pos);
/*     */       }
/* 200 */       else if (neighborBlock != this)
/*     */       {
/* 202 */         onNeighborBlockChange(worldIn, var5, var6, neighborBlock);
/*     */       }
/*     */     
/*     */     } else {
/*     */       
/* 207 */       boolean var9 = false;
/* 208 */       BlockPos var10 = pos.offsetUp();
/* 209 */       IBlockState var7 = worldIn.getBlockState(var10);
/*     */       
/* 211 */       if (var7.getBlock() != this) {
/*     */         
/* 213 */         worldIn.setBlockToAir(pos);
/* 214 */         var9 = true;
/*     */       } 
/*     */       
/* 217 */       if (!World.doesBlockHaveSolidTopSurface((IBlockAccess)worldIn, pos.offsetDown())) {
/*     */         
/* 219 */         worldIn.setBlockToAir(pos);
/* 220 */         var9 = true;
/*     */         
/* 222 */         if (var7.getBlock() == this)
/*     */         {
/* 224 */           worldIn.setBlockToAir(var10);
/*     */         }
/*     */       } 
/*     */       
/* 228 */       if (var9) {
/*     */         
/* 230 */         if (!worldIn.isRemote)
/*     */         {
/* 232 */           dropBlockAsItem(worldIn, pos, state, 0);
/*     */         }
/*     */       }
/*     */       else {
/*     */         
/* 237 */         boolean var8 = !(!worldIn.isBlockPowered(pos) && !worldIn.isBlockPowered(var10));
/*     */         
/* 239 */         if ((var8 || neighborBlock.canProvidePower()) && neighborBlock != this && var8 != ((Boolean)var7.getValue((IProperty)POWERED_PROP)).booleanValue()) {
/*     */           
/* 241 */           worldIn.setBlockState(var10, var7.withProperty((IProperty)POWERED_PROP, Boolean.valueOf(var8)), 2);
/*     */           
/* 243 */           if (var8 != ((Boolean)state.getValue((IProperty)OPEN_PROP)).booleanValue()) {
/*     */             
/* 245 */             worldIn.setBlockState(pos, state.withProperty((IProperty)OPEN_PROP, Boolean.valueOf(var8)), 2);
/* 246 */             worldIn.markBlockRangeForRenderUpdate(pos, pos);
/* 247 */             worldIn.playAuxSFXAtEntity(null, var8 ? 1003 : 1006, pos, 0);
/*     */           } 
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
/*     */   
/*     */   public Item getItemDropped(IBlockState state, Random rand, int fortune) {
/* 261 */     return (state.getValue((IProperty)HALF_PROP) == EnumDoorHalf.UPPER) ? null : func_176509_j();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MovingObjectPosition collisionRayTrace(World worldIn, BlockPos pos, Vec3 start, Vec3 end) {
/* 272 */     setBlockBoundsBasedOnState((IBlockAccess)worldIn, pos);
/* 273 */     return super.collisionRayTrace(worldIn, pos, start, end);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canPlaceBlockAt(World worldIn, BlockPos pos) {
/* 278 */     return (pos.getY() >= 255) ? false : ((World.doesBlockHaveSolidTopSurface((IBlockAccess)worldIn, pos.offsetDown()) && super.canPlaceBlockAt(worldIn, pos) && super.canPlaceBlockAt(worldIn, pos.offsetUp())));
/*     */   }
/*     */ 
/*     */   
/*     */   public int getMobilityFlag() {
/* 283 */     return 1;
/*     */   }
/*     */ 
/*     */   
/*     */   public static int func_176515_e(IBlockAccess p_176515_0_, BlockPos p_176515_1_) {
/* 288 */     IBlockState var2 = p_176515_0_.getBlockState(p_176515_1_);
/* 289 */     int var3 = var2.getBlock().getMetaFromState(var2);
/* 290 */     boolean var4 = func_176518_i(var3);
/* 291 */     IBlockState var5 = p_176515_0_.getBlockState(p_176515_1_.offsetDown());
/* 292 */     int var6 = var5.getBlock().getMetaFromState(var5);
/* 293 */     int var7 = var4 ? var6 : var3;
/* 294 */     IBlockState var8 = p_176515_0_.getBlockState(p_176515_1_.offsetUp());
/* 295 */     int var9 = var8.getBlock().getMetaFromState(var8);
/* 296 */     int var10 = var4 ? var3 : var9;
/* 297 */     boolean var11 = ((var10 & 0x1) != 0);
/* 298 */     boolean var12 = ((var10 & 0x2) != 0);
/* 299 */     return func_176510_b(var7) | (var4 ? 8 : 0) | (var11 ? 16 : 0) | (var12 ? 32 : 0);
/*     */   }
/*     */ 
/*     */   
/*     */   public Item getItem(World worldIn, BlockPos pos) {
/* 304 */     return func_176509_j();
/*     */   }
/*     */ 
/*     */   
/*     */   private Item func_176509_j() {
/* 309 */     return (this == Blocks.iron_door) ? Items.iron_door : ((this == Blocks.spruce_door) ? Items.spruce_door : ((this == Blocks.birch_door) ? Items.birch_door : ((this == Blocks.jungle_door) ? Items.jungle_door : ((this == Blocks.acacia_door) ? Items.acacia_door : ((this == Blocks.dark_oak_door) ? Items.dark_oak_door : Items.oak_door)))));
/*     */   }
/*     */ 
/*     */   
/*     */   public void onBlockHarvested(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn) {
/* 314 */     BlockPos var5 = pos.offsetDown();
/*     */     
/* 316 */     if (playerIn.capabilities.isCreativeMode && state.getValue((IProperty)HALF_PROP) == EnumDoorHalf.UPPER && worldIn.getBlockState(var5).getBlock() == this)
/*     */     {
/* 318 */       worldIn.setBlockToAir(var5);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public EnumWorldBlockLayer getBlockLayer() {
/* 324 */     return EnumWorldBlockLayer.CUTOUT;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
/* 335 */     if (state.getValue((IProperty)HALF_PROP) == EnumDoorHalf.LOWER) {
/*     */       
/* 337 */       IBlockState var4 = worldIn.getBlockState(pos.offsetUp());
/*     */       
/* 339 */       if (var4.getBlock() == this)
/*     */       {
/* 341 */         state = state.withProperty((IProperty)HINGEPOSITION_PROP, var4.getValue((IProperty)HINGEPOSITION_PROP)).withProperty((IProperty)POWERED_PROP, var4.getValue((IProperty)POWERED_PROP));
/*     */       }
/*     */     }
/*     */     else {
/*     */       
/* 346 */       IBlockState var4 = worldIn.getBlockState(pos.offsetDown());
/*     */       
/* 348 */       if (var4.getBlock() == this)
/*     */       {
/* 350 */         state = state.withProperty((IProperty)FACING_PROP, var4.getValue((IProperty)FACING_PROP)).withProperty((IProperty)OPEN_PROP, var4.getValue((IProperty)OPEN_PROP));
/*     */       }
/*     */     } 
/*     */     
/* 354 */     return state;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IBlockState getStateFromMeta(int meta) {
/* 362 */     return ((meta & 0x8) > 0) ? getDefaultState().withProperty((IProperty)HALF_PROP, EnumDoorHalf.UPPER).withProperty((IProperty)HINGEPOSITION_PROP, ((meta & 0x1) > 0) ? EnumHingePosition.RIGHT : EnumHingePosition.LEFT).withProperty((IProperty)POWERED_PROP, Boolean.valueOf(((meta & 0x2) > 0))) : getDefaultState().withProperty((IProperty)HALF_PROP, EnumDoorHalf.LOWER).withProperty((IProperty)FACING_PROP, (Comparable)EnumFacing.getHorizontal(meta & 0x3).rotateYCCW()).withProperty((IProperty)OPEN_PROP, Boolean.valueOf(((meta & 0x4) > 0)));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getMetaFromState(IBlockState state) {
/*     */     int var3;
/* 370 */     byte var2 = 0;
/*     */ 
/*     */     
/* 373 */     if (state.getValue((IProperty)HALF_PROP) == EnumDoorHalf.UPPER) {
/*     */       
/* 375 */       var3 = var2 | 0x8;
/*     */       
/* 377 */       if (state.getValue((IProperty)HINGEPOSITION_PROP) == EnumHingePosition.RIGHT)
/*     */       {
/* 379 */         var3 |= 0x1;
/*     */       }
/*     */       
/* 382 */       if (((Boolean)state.getValue((IProperty)POWERED_PROP)).booleanValue())
/*     */       {
/* 384 */         var3 |= 0x2;
/*     */       }
/*     */     }
/*     */     else {
/*     */       
/* 389 */       var3 = var2 | ((EnumFacing)state.getValue((IProperty)FACING_PROP)).rotateY().getHorizontalIndex();
/*     */       
/* 391 */       if (((Boolean)state.getValue((IProperty)OPEN_PROP)).booleanValue())
/*     */       {
/* 393 */         var3 |= 0x4;
/*     */       }
/*     */     } 
/*     */     
/* 397 */     return var3;
/*     */   }
/*     */ 
/*     */   
/*     */   protected static int func_176510_b(int p_176510_0_) {
/* 402 */     return p_176510_0_ & 0x7;
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean func_176514_f(IBlockAccess p_176514_0_, BlockPos p_176514_1_) {
/* 407 */     return func_176516_g(func_176515_e(p_176514_0_, p_176514_1_));
/*     */   }
/*     */ 
/*     */   
/*     */   public static EnumFacing func_176517_h(IBlockAccess p_176517_0_, BlockPos p_176517_1_) {
/* 412 */     return func_176511_f(func_176515_e(p_176517_0_, p_176517_1_));
/*     */   }
/*     */ 
/*     */   
/*     */   public static EnumFacing func_176511_f(int p_176511_0_) {
/* 417 */     return EnumFacing.getHorizontal(p_176511_0_ & 0x3).rotateYCCW();
/*     */   }
/*     */ 
/*     */   
/*     */   protected static boolean func_176516_g(int p_176516_0_) {
/* 422 */     return ((p_176516_0_ & 0x4) != 0);
/*     */   }
/*     */ 
/*     */   
/*     */   protected static boolean func_176518_i(int p_176518_0_) {
/* 427 */     return ((p_176518_0_ & 0x8) != 0);
/*     */   }
/*     */ 
/*     */   
/*     */   protected static boolean func_176513_j(int p_176513_0_) {
/* 432 */     return ((p_176513_0_ & 0x10) != 0);
/*     */   }
/*     */ 
/*     */   
/*     */   protected BlockState createBlockState() {
/* 437 */     return new BlockState(this, new IProperty[] { (IProperty)HALF_PROP, (IProperty)FACING_PROP, (IProperty)OPEN_PROP, (IProperty)HINGEPOSITION_PROP, (IProperty)POWERED_PROP });
/*     */   }
/*     */   
/*     */   public enum EnumDoorHalf
/*     */     implements IStringSerializable {
/* 442 */     UPPER("UPPER", 0),
/* 443 */     LOWER("LOWER", 1);
/*     */     
/* 445 */     private static final EnumDoorHalf[] $VALUES = new EnumDoorHalf[] { UPPER, LOWER };
/*     */ 
/*     */     
/*     */     private static final String __OBFID = "CL_00002124";
/*     */ 
/*     */     
/*     */     public String toString() {
/* 452 */       return getName();
/*     */     } static {
/*     */     
/*     */     }
/*     */     public String getName() {
/* 457 */       return (this == UPPER) ? "upper" : "lower";
/*     */     }
/*     */   }
/*     */   
/*     */   public enum EnumHingePosition
/*     */     implements IStringSerializable {
/* 463 */     LEFT("LEFT", 0),
/* 464 */     RIGHT("RIGHT", 1);
/*     */     
/* 466 */     private static final EnumHingePosition[] $VALUES = new EnumHingePosition[] { LEFT, RIGHT }; private static final String __OBFID = "CL_00002123";
/*     */     
/*     */     static {
/*     */     
/*     */     }
/*     */     
/*     */     public String toString() {
/* 473 */       return getName();
/*     */     }
/*     */ 
/*     */     
/*     */     public String getName() {
/* 478 */       return (this == LEFT) ? "left" : "right";
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\block\BlockDoor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */