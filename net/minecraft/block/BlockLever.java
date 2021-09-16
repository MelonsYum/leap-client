/*     */ package net.minecraft.block;
/*     */ 
/*     */ import java.util.Iterator;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.block.properties.IProperty;
/*     */ import net.minecraft.block.properties.PropertyBool;
/*     */ import net.minecraft.block.properties.PropertyEnum;
/*     */ import net.minecraft.block.state.BlockState;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.creativetab.CreativeTabs;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.IStringSerializable;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class BlockLever
/*     */   extends Block {
/*  22 */   public static final PropertyEnum FACING = PropertyEnum.create("facing", EnumOrientation.class);
/*  23 */   public static final PropertyBool POWERED = PropertyBool.create("powered");
/*     */   
/*     */   private static final String __OBFID = "CL_00000264";
/*     */   
/*     */   protected BlockLever() {
/*  28 */     super(Material.circuits);
/*  29 */     setDefaultState(this.blockState.getBaseState().withProperty((IProperty)FACING, EnumOrientation.NORTH).withProperty((IProperty)POWERED, Boolean.valueOf(false)));
/*  30 */     setCreativeTab(CreativeTabs.tabRedstone);
/*     */   }
/*     */ 
/*     */   
/*     */   public AxisAlignedBB getCollisionBoundingBox(World worldIn, BlockPos pos, IBlockState state) {
/*  35 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isOpaqueCube() {
/*  40 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isFullCube() {
/*  45 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canPlaceBlockOnSide(World worldIn, BlockPos pos, EnumFacing side) {
/*  53 */     return (side == EnumFacing.UP && World.doesBlockHaveSolidTopSurface((IBlockAccess)worldIn, pos.offsetDown())) ? true : func_176358_d(worldIn, pos.offset(side.getOpposite()));
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canPlaceBlockAt(World worldIn, BlockPos pos) {
/*  58 */     return func_176358_d(worldIn, pos.offsetWest()) ? true : (func_176358_d(worldIn, pos.offsetEast()) ? true : (func_176358_d(worldIn, pos.offsetNorth()) ? true : (func_176358_d(worldIn, pos.offsetSouth()) ? true : (World.doesBlockHaveSolidTopSurface((IBlockAccess)worldIn, pos.offsetDown()) ? true : func_176358_d(worldIn, pos.offsetUp())))));
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean func_176358_d(World worldIn, BlockPos p_176358_2_) {
/*  63 */     return worldIn.getBlockState(p_176358_2_).getBlock().isNormalCube();
/*     */   }
/*     */   
/*     */   public IBlockState onBlockPlaced(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
/*     */     EnumFacing var11;
/*  68 */     IBlockState var9 = getDefaultState().withProperty((IProperty)POWERED, Boolean.valueOf(false));
/*     */     
/*  70 */     if (func_176358_d(worldIn, pos.offset(facing.getOpposite())))
/*     */     {
/*  72 */       return var9.withProperty((IProperty)FACING, EnumOrientation.func_176856_a(facing, placer.func_174811_aO()));
/*     */     }
/*     */ 
/*     */     
/*  76 */     Iterator<EnumFacing> var10 = EnumFacing.Plane.HORIZONTAL.iterator();
/*     */ 
/*     */ 
/*     */     
/*     */     do {
/*  81 */       if (!var10.hasNext()) {
/*     */         
/*  83 */         if (World.doesBlockHaveSolidTopSurface((IBlockAccess)worldIn, pos.offsetDown()))
/*     */         {
/*  85 */           return var9.withProperty((IProperty)FACING, EnumOrientation.func_176856_a(EnumFacing.UP, placer.func_174811_aO()));
/*     */         }
/*     */         
/*  88 */         return var9;
/*     */       } 
/*     */       
/*  91 */       var11 = var10.next();
/*     */     }
/*  93 */     while (var11 == facing || !func_176358_d(worldIn, pos.offset(var11.getOpposite())));
/*     */     
/*  95 */     return var9.withProperty((IProperty)FACING, EnumOrientation.func_176856_a(var11, placer.func_174811_aO()));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static int func_176357_a(EnumFacing p_176357_0_) {
/* 101 */     switch (SwitchEnumFacing.FACING_LOOKUP[p_176357_0_.ordinal()]) {
/*     */       
/*     */       case 1:
/* 104 */         return 0;
/*     */       
/*     */       case 2:
/* 107 */         return 5;
/*     */       
/*     */       case 3:
/* 110 */         return 4;
/*     */       
/*     */       case 4:
/* 113 */         return 3;
/*     */       
/*     */       case 5:
/* 116 */         return 2;
/*     */       
/*     */       case 6:
/* 119 */         return 1;
/*     */     } 
/*     */     
/* 122 */     return -1;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void onNeighborBlockChange(World worldIn, BlockPos pos, IBlockState state, Block neighborBlock) {
/* 128 */     if (func_176356_e(worldIn, pos) && !func_176358_d(worldIn, pos.offset(((EnumOrientation)state.getValue((IProperty)FACING)).func_176852_c().getOpposite()))) {
/*     */       
/* 130 */       dropBlockAsItem(worldIn, pos, state, 0);
/* 131 */       worldIn.setBlockToAir(pos);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean func_176356_e(World worldIn, BlockPos p_176356_2_) {
/* 137 */     if (canPlaceBlockAt(worldIn, p_176356_2_))
/*     */     {
/* 139 */       return true;
/*     */     }
/*     */ 
/*     */     
/* 143 */     dropBlockAsItem(worldIn, p_176356_2_, worldIn.getBlockState(p_176356_2_), 0);
/* 144 */     worldIn.setBlockToAir(p_176356_2_);
/* 145 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setBlockBoundsBasedOnState(IBlockAccess access, BlockPos pos) {
/* 151 */     float var3 = 0.1875F;
/*     */     
/* 153 */     switch (SwitchEnumFacing.ORIENTATION_LOOKUP[((EnumOrientation)access.getBlockState(pos).getValue((IProperty)FACING)).ordinal()]) {
/*     */       
/*     */       case 1:
/* 156 */         setBlockBounds(0.0F, 0.2F, 0.5F - var3, var3 * 2.0F, 0.8F, 0.5F + var3);
/*     */         break;
/*     */       
/*     */       case 2:
/* 160 */         setBlockBounds(1.0F - var3 * 2.0F, 0.2F, 0.5F - var3, 1.0F, 0.8F, 0.5F + var3);
/*     */         break;
/*     */       
/*     */       case 3:
/* 164 */         setBlockBounds(0.5F - var3, 0.2F, 0.0F, 0.5F + var3, 0.8F, var3 * 2.0F);
/*     */         break;
/*     */       
/*     */       case 4:
/* 168 */         setBlockBounds(0.5F - var3, 0.2F, 1.0F - var3 * 2.0F, 0.5F + var3, 0.8F, 1.0F);
/*     */         break;
/*     */       
/*     */       case 5:
/*     */       case 6:
/* 173 */         var3 = 0.25F;
/* 174 */         setBlockBounds(0.5F - var3, 0.0F, 0.5F - var3, 0.5F + var3, 0.6F, 0.5F + var3);
/*     */         break;
/*     */       
/*     */       case 7:
/*     */       case 8:
/* 179 */         var3 = 0.25F;
/* 180 */         setBlockBounds(0.5F - var3, 0.4F, 0.5F - var3, 0.5F + var3, 1.0F, 0.5F + var3);
/*     */         break;
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumFacing side, float hitX, float hitY, float hitZ) {
/* 186 */     if (worldIn.isRemote)
/*     */     {
/* 188 */       return true;
/*     */     }
/*     */ 
/*     */     
/* 192 */     state = state.cycleProperty((IProperty)POWERED);
/* 193 */     worldIn.setBlockState(pos, state, 3);
/* 194 */     worldIn.playSoundEffect(pos.getX() + 0.5D, pos.getY() + 0.5D, pos.getZ() + 0.5D, "random.click", 0.3F, ((Boolean)state.getValue((IProperty)POWERED)).booleanValue() ? 0.6F : 0.5F);
/* 195 */     worldIn.notifyNeighborsOfStateChange(pos, this);
/* 196 */     EnumFacing var9 = ((EnumOrientation)state.getValue((IProperty)FACING)).func_176852_c();
/* 197 */     worldIn.notifyNeighborsOfStateChange(pos.offset(var9.getOpposite()), this);
/* 198 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
/* 204 */     if (((Boolean)state.getValue((IProperty)POWERED)).booleanValue()) {
/*     */       
/* 206 */       worldIn.notifyNeighborsOfStateChange(pos, this);
/* 207 */       EnumFacing var4 = ((EnumOrientation)state.getValue((IProperty)FACING)).func_176852_c();
/* 208 */       worldIn.notifyNeighborsOfStateChange(pos.offset(var4.getOpposite()), this);
/*     */     } 
/*     */     
/* 211 */     super.breakBlock(worldIn, pos, state);
/*     */   }
/*     */ 
/*     */   
/*     */   public int isProvidingWeakPower(IBlockAccess worldIn, BlockPos pos, IBlockState state, EnumFacing side) {
/* 216 */     return ((Boolean)state.getValue((IProperty)POWERED)).booleanValue() ? 15 : 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public int isProvidingStrongPower(IBlockAccess worldIn, BlockPos pos, IBlockState state, EnumFacing side) {
/* 221 */     return !((Boolean)state.getValue((IProperty)POWERED)).booleanValue() ? 0 : ((((EnumOrientation)state.getValue((IProperty)FACING)).func_176852_c() == side) ? 15 : 0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canProvidePower() {
/* 229 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IBlockState getStateFromMeta(int meta) {
/* 237 */     return getDefaultState().withProperty((IProperty)FACING, EnumOrientation.func_176853_a(meta & 0x7)).withProperty((IProperty)POWERED, Boolean.valueOf(((meta & 0x8) > 0)));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getMetaFromState(IBlockState state) {
/* 245 */     byte var2 = 0;
/* 246 */     int var3 = var2 | ((EnumOrientation)state.getValue((IProperty)FACING)).func_176855_a();
/*     */     
/* 248 */     if (((Boolean)state.getValue((IProperty)POWERED)).booleanValue())
/*     */     {
/* 250 */       var3 |= 0x8;
/*     */     }
/*     */     
/* 253 */     return var3;
/*     */   }
/*     */ 
/*     */   
/*     */   protected BlockState createBlockState() {
/* 258 */     return new BlockState(this, new IProperty[] { (IProperty)FACING, (IProperty)POWERED });
/*     */   }
/*     */   
/*     */   public enum EnumOrientation
/*     */     implements IStringSerializable {
/* 263 */     DOWN_X("DOWN_X", 0, 0, "down_x", EnumFacing.DOWN),
/* 264 */     EAST("EAST", 1, 1, "east", EnumFacing.EAST),
/* 265 */     WEST("WEST", 2, 2, "west", EnumFacing.WEST),
/* 266 */     SOUTH("SOUTH", 3, 3, "south", EnumFacing.SOUTH),
/* 267 */     NORTH("NORTH", 4, 4, "north", EnumFacing.NORTH),
/* 268 */     UP_Z("UP_Z", 5, 5, "up_z", EnumFacing.UP),
/* 269 */     UP_X("UP_X", 6, 6, "up_x", EnumFacing.UP),
/* 270 */     DOWN_Z("DOWN_Z", 7, 7, "down_z", EnumFacing.DOWN);
/* 271 */     private static final EnumOrientation[] field_176869_i = new EnumOrientation[(values()).length];
/*     */     
/*     */     private final int field_176866_j;
/*     */     private final String field_176867_k;
/*     */     private final EnumFacing field_176864_l;
/* 276 */     private static final EnumOrientation[] $VALUES = new EnumOrientation[] { DOWN_X, EAST, WEST, SOUTH, NORTH, UP_Z, UP_X, DOWN_Z };
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     private static final String __OBFID = "CL_00002102";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     EnumOrientation(String p_i45709_1_, int p_i45709_2_, int p_i45709_3_, String p_i45709_4_, EnumFacing p_i45709_5_) {
/*     */       this.field_176866_j = p_i45709_3_;
/*     */       this.field_176867_k = p_i45709_4_;
/*     */       this.field_176864_l = p_i45709_5_;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public int func_176855_a() {
/*     */       return this.field_176866_j;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public EnumFacing func_176852_c() {
/*     */       return this.field_176864_l;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public String toString() {
/*     */       return this.field_176867_k;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public static EnumOrientation func_176853_a(int p_176853_0_) {
/*     */       if (p_176853_0_ < 0 || p_176853_0_ >= field_176869_i.length) {
/*     */         p_176853_0_ = 0;
/*     */       }
/*     */       return field_176869_i[p_176853_0_];
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public static EnumOrientation func_176856_a(EnumFacing p_176856_0_, EnumFacing p_176856_1_) {
/*     */       switch (BlockLever.SwitchEnumFacing.FACING_LOOKUP[p_176856_0_.ordinal()]) {
/*     */         case 1:
/*     */           switch (BlockLever.SwitchEnumFacing.AXIS_LOOKUP[p_176856_1_.getAxis().ordinal()]) {
/*     */             case 1:
/*     */               return DOWN_X;
/*     */             case 2:
/*     */               return DOWN_Z;
/*     */           } 
/*     */           throw new IllegalArgumentException("Invalid entityFacing " + p_176856_1_ + " for facing " + p_176856_0_);
/*     */         case 2:
/*     */           switch (BlockLever.SwitchEnumFacing.AXIS_LOOKUP[p_176856_1_.getAxis().ordinal()]) {
/*     */             case 1:
/*     */               return UP_X;
/*     */             case 2:
/*     */               return UP_Z;
/*     */           } 
/*     */           throw new IllegalArgumentException("Invalid entityFacing " + p_176856_1_ + " for facing " + p_176856_0_);
/*     */         case 3:
/*     */           return NORTH;
/*     */         case 4:
/*     */           return SOUTH;
/*     */         case 5:
/*     */           return WEST;
/*     */         case 6:
/*     */           return EAST;
/*     */       } 
/*     */       throw new IllegalArgumentException("Invalid facing: " + p_176856_0_);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     static {
/* 364 */       EnumOrientation[] var0 = values();
/* 365 */       int var1 = var0.length;
/*     */       
/* 367 */       for (int var2 = 0; var2 < var1; var2++) {
/*     */         
/* 369 */         EnumOrientation var3 = var0[var2];
/* 370 */         field_176869_i[var3.func_176855_a()] = var3;
/*     */       } 
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public String getName() {
/*     */       return this.field_176867_k;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static final class SwitchEnumFacing
/*     */   {
/*     */     static final int[] FACING_LOOKUP;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 404 */     static final int[] ORIENTATION_LOOKUP = new int[(BlockLever.EnumOrientation.values()).length]; static final int[] AXIS_LOOKUP = new int[(EnumFacing.Axis.values()).length];
/*     */     
/*     */     static {
/*     */       try {
/* 408 */         ORIENTATION_LOOKUP[BlockLever.EnumOrientation.EAST.ordinal()] = 1;
/*     */       }
/* 410 */       catch (NoSuchFieldError noSuchFieldError) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/* 417 */         ORIENTATION_LOOKUP[BlockLever.EnumOrientation.WEST.ordinal()] = 2;
/*     */       }
/* 419 */       catch (NoSuchFieldError noSuchFieldError) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/* 426 */         ORIENTATION_LOOKUP[BlockLever.EnumOrientation.SOUTH.ordinal()] = 3;
/*     */       }
/* 428 */       catch (NoSuchFieldError noSuchFieldError) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/* 435 */         ORIENTATION_LOOKUP[BlockLever.EnumOrientation.NORTH.ordinal()] = 4;
/*     */       }
/* 437 */       catch (NoSuchFieldError noSuchFieldError) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/* 444 */         ORIENTATION_LOOKUP[BlockLever.EnumOrientation.UP_Z.ordinal()] = 5;
/*     */       }
/* 446 */       catch (NoSuchFieldError noSuchFieldError) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/* 453 */         ORIENTATION_LOOKUP[BlockLever.EnumOrientation.UP_X.ordinal()] = 6;
/*     */       }
/* 455 */       catch (NoSuchFieldError noSuchFieldError) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/* 462 */         ORIENTATION_LOOKUP[BlockLever.EnumOrientation.DOWN_X.ordinal()] = 7;
/*     */       }
/* 464 */       catch (NoSuchFieldError noSuchFieldError) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/* 471 */         ORIENTATION_LOOKUP[BlockLever.EnumOrientation.DOWN_Z.ordinal()] = 8;
/*     */       }
/* 473 */       catch (NoSuchFieldError noSuchFieldError) {}
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 478 */       FACING_LOOKUP = new int[(EnumFacing.values()).length];
/*     */ 
/*     */       
/*     */       try {
/* 482 */         FACING_LOOKUP[EnumFacing.DOWN.ordinal()] = 1;
/*     */       }
/* 484 */       catch (NoSuchFieldError noSuchFieldError) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/* 491 */         FACING_LOOKUP[EnumFacing.UP.ordinal()] = 2;
/*     */       }
/* 493 */       catch (NoSuchFieldError noSuchFieldError) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/* 500 */         FACING_LOOKUP[EnumFacing.NORTH.ordinal()] = 3;
/*     */       }
/* 502 */       catch (NoSuchFieldError noSuchFieldError) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/* 509 */         FACING_LOOKUP[EnumFacing.SOUTH.ordinal()] = 4;
/*     */       }
/* 511 */       catch (NoSuchFieldError noSuchFieldError) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/* 518 */         FACING_LOOKUP[EnumFacing.WEST.ordinal()] = 5;
/*     */       }
/* 520 */       catch (NoSuchFieldError noSuchFieldError) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/* 527 */         FACING_LOOKUP[EnumFacing.EAST.ordinal()] = 6;
/*     */       }
/* 529 */       catch (NoSuchFieldError noSuchFieldError) {}
/*     */     }
/*     */     
/*     */     private static final String __OBFID = "CL_00002103";
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\block\BlockLever.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */