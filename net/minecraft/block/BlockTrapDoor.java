/*     */ package net.minecraft.block;
/*     */ 
/*     */ import com.google.common.base.Predicate;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.block.properties.IProperty;
/*     */ import net.minecraft.block.properties.PropertyBool;
/*     */ import net.minecraft.block.properties.PropertyDirection;
/*     */ import net.minecraft.block.properties.PropertyEnum;
/*     */ import net.minecraft.block.state.BlockState;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.creativetab.CreativeTabs;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.init.Blocks;
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
/*     */ public class BlockTrapDoor extends Block {
/*  26 */   public static final PropertyDirection field_176284_a = PropertyDirection.create("facing", (Predicate)EnumFacing.Plane.HORIZONTAL);
/*  27 */   public static final PropertyBool field_176283_b = PropertyBool.create("open");
/*  28 */   public static final PropertyEnum field_176285_M = PropertyEnum.create("half", DoorHalf.class);
/*     */   
/*     */   private static final String __OBFID = "CL_00000327";
/*     */   
/*     */   protected BlockTrapDoor(Material p_i45434_1_) {
/*  33 */     super(p_i45434_1_);
/*  34 */     setDefaultState(this.blockState.getBaseState().withProperty((IProperty)field_176284_a, (Comparable)EnumFacing.NORTH).withProperty((IProperty)field_176283_b, Boolean.valueOf(false)).withProperty((IProperty)field_176285_M, DoorHalf.BOTTOM));
/*  35 */     float var2 = 0.5F;
/*  36 */     float var3 = 1.0F;
/*  37 */     setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
/*  38 */     setCreativeTab(CreativeTabs.tabRedstone);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isOpaqueCube() {
/*  43 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isFullCube() {
/*  48 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isPassable(IBlockAccess blockAccess, BlockPos pos) {
/*  53 */     return !((Boolean)blockAccess.getBlockState(pos).getValue((IProperty)field_176283_b)).booleanValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public AxisAlignedBB getSelectedBoundingBox(World worldIn, BlockPos pos) {
/*  58 */     setBlockBoundsBasedOnState((IBlockAccess)worldIn, pos);
/*  59 */     return super.getSelectedBoundingBox(worldIn, pos);
/*     */   }
/*     */ 
/*     */   
/*     */   public AxisAlignedBB getCollisionBoundingBox(World worldIn, BlockPos pos, IBlockState state) {
/*  64 */     setBlockBoundsBasedOnState((IBlockAccess)worldIn, pos);
/*  65 */     return super.getCollisionBoundingBox(worldIn, pos, state);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setBlockBoundsBasedOnState(IBlockAccess access, BlockPos pos) {
/*  70 */     func_180693_d(access.getBlockState(pos));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setBlockBoundsForItemRender() {
/*  78 */     float var1 = 0.1875F;
/*  79 */     setBlockBounds(0.0F, 0.40625F, 0.0F, 1.0F, 0.59375F, 1.0F);
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_180693_d(IBlockState p_180693_1_) {
/*  84 */     if (p_180693_1_.getBlock() == this) {
/*     */       
/*  86 */       boolean var2 = (p_180693_1_.getValue((IProperty)field_176285_M) == DoorHalf.TOP);
/*  87 */       Boolean var3 = (Boolean)p_180693_1_.getValue((IProperty)field_176283_b);
/*  88 */       EnumFacing var4 = (EnumFacing)p_180693_1_.getValue((IProperty)field_176284_a);
/*  89 */       float var5 = 0.1875F;
/*     */       
/*  91 */       if (var2) {
/*     */         
/*  93 */         setBlockBounds(0.0F, 0.8125F, 0.0F, 1.0F, 1.0F, 1.0F);
/*     */       }
/*     */       else {
/*     */         
/*  97 */         setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.1875F, 1.0F);
/*     */       } 
/*     */       
/* 100 */       if (var3.booleanValue()) {
/*     */         
/* 102 */         if (var4 == EnumFacing.NORTH)
/*     */         {
/* 104 */           setBlockBounds(0.0F, 0.0F, 0.8125F, 1.0F, 1.0F, 1.0F);
/*     */         }
/*     */         
/* 107 */         if (var4 == EnumFacing.SOUTH)
/*     */         {
/* 109 */           setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 0.1875F);
/*     */         }
/*     */         
/* 112 */         if (var4 == EnumFacing.WEST)
/*     */         {
/* 114 */           setBlockBounds(0.8125F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
/*     */         }
/*     */         
/* 117 */         if (var4 == EnumFacing.EAST)
/*     */         {
/* 119 */           setBlockBounds(0.0F, 0.0F, 0.0F, 0.1875F, 1.0F, 1.0F);
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumFacing side, float hitX, float hitY, float hitZ) {
/* 127 */     if (this.blockMaterial == Material.iron)
/*     */     {
/* 129 */       return true;
/*     */     }
/*     */ 
/*     */     
/* 133 */     state = state.cycleProperty((IProperty)field_176283_b);
/* 134 */     worldIn.setBlockState(pos, state, 2);
/* 135 */     worldIn.playAuxSFXAtEntity(playerIn, ((Boolean)state.getValue((IProperty)field_176283_b)).booleanValue() ? 1003 : 1006, pos, 0);
/* 136 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void onNeighborBlockChange(World worldIn, BlockPos pos, IBlockState state, Block neighborBlock) {
/* 142 */     if (!worldIn.isRemote) {
/*     */       
/* 144 */       BlockPos var5 = pos.offset(((EnumFacing)state.getValue((IProperty)field_176284_a)).getOpposite());
/*     */       
/* 146 */       if (!isValidSupportBlock(worldIn.getBlockState(var5).getBlock())) {
/*     */         
/* 148 */         worldIn.setBlockToAir(pos);
/* 149 */         dropBlockAsItem(worldIn, pos, state, 0);
/*     */       }
/*     */       else {
/*     */         
/* 153 */         boolean var6 = worldIn.isBlockPowered(pos);
/*     */         
/* 155 */         if (var6 || neighborBlock.canProvidePower()) {
/*     */           
/* 157 */           boolean var7 = ((Boolean)state.getValue((IProperty)field_176283_b)).booleanValue();
/*     */           
/* 159 */           if (var7 != var6) {
/*     */             
/* 161 */             worldIn.setBlockState(pos, state.withProperty((IProperty)field_176283_b, Boolean.valueOf(var6)), 2);
/* 162 */             worldIn.playAuxSFXAtEntity(null, var6 ? 1003 : 1006, pos, 0);
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
/*     */   
/*     */   public MovingObjectPosition collisionRayTrace(World worldIn, BlockPos pos, Vec3 start, Vec3 end) {
/* 177 */     setBlockBoundsBasedOnState((IBlockAccess)worldIn, pos);
/* 178 */     return super.collisionRayTrace(worldIn, pos, start, end);
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockState onBlockPlaced(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
/* 183 */     IBlockState var9 = getDefaultState();
/*     */     
/* 185 */     if (facing.getAxis().isHorizontal()) {
/*     */       
/* 187 */       var9 = var9.withProperty((IProperty)field_176284_a, (Comparable)facing).withProperty((IProperty)field_176283_b, Boolean.valueOf(false));
/* 188 */       var9 = var9.withProperty((IProperty)field_176285_M, (hitY > 0.5F) ? DoorHalf.TOP : DoorHalf.BOTTOM);
/*     */     } 
/*     */     
/* 191 */     return var9;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canPlaceBlockOnSide(World worldIn, BlockPos pos, EnumFacing side) {
/* 199 */     return (!side.getAxis().isVertical() && isValidSupportBlock(worldIn.getBlockState(pos.offset(side.getOpposite())).getBlock()));
/*     */   }
/*     */ 
/*     */   
/*     */   protected static EnumFacing func_176281_b(int p_176281_0_) {
/* 204 */     switch (p_176281_0_ & 0x3) {
/*     */       
/*     */       case 0:
/* 207 */         return EnumFacing.NORTH;
/*     */       
/*     */       case 1:
/* 210 */         return EnumFacing.SOUTH;
/*     */       
/*     */       case 2:
/* 213 */         return EnumFacing.WEST;
/*     */     } 
/*     */ 
/*     */     
/* 217 */     return EnumFacing.EAST;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected static int func_176282_a(EnumFacing p_176282_0_) {
/* 223 */     switch (SwitchEnumFacing.field_177058_a[p_176282_0_.ordinal()]) {
/*     */       
/*     */       case 1:
/* 226 */         return 0;
/*     */       
/*     */       case 2:
/* 229 */         return 1;
/*     */       
/*     */       case 3:
/* 232 */         return 2;
/*     */     } 
/*     */ 
/*     */     
/* 236 */     return 3;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static boolean isValidSupportBlock(Block p_150119_0_) {
/* 242 */     return !((!p_150119_0_.blockMaterial.isOpaque() || !p_150119_0_.isFullCube()) && p_150119_0_ != Blocks.glowstone && !(p_150119_0_ instanceof BlockSlab) && !(p_150119_0_ instanceof BlockStairs));
/*     */   }
/*     */ 
/*     */   
/*     */   public EnumWorldBlockLayer getBlockLayer() {
/* 247 */     return EnumWorldBlockLayer.CUTOUT;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IBlockState getStateFromMeta(int meta) {
/* 255 */     return getDefaultState().withProperty((IProperty)field_176284_a, (Comparable)func_176281_b(meta)).withProperty((IProperty)field_176283_b, Boolean.valueOf(((meta & 0x4) != 0))).withProperty((IProperty)field_176285_M, ((meta & 0x8) == 0) ? DoorHalf.BOTTOM : DoorHalf.TOP);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getMetaFromState(IBlockState state) {
/* 263 */     byte var2 = 0;
/* 264 */     int var3 = var2 | func_176282_a((EnumFacing)state.getValue((IProperty)field_176284_a));
/*     */     
/* 266 */     if (((Boolean)state.getValue((IProperty)field_176283_b)).booleanValue())
/*     */     {
/* 268 */       var3 |= 0x4;
/*     */     }
/*     */     
/* 271 */     if (state.getValue((IProperty)field_176285_M) == DoorHalf.TOP)
/*     */     {
/* 273 */       var3 |= 0x8;
/*     */     }
/*     */     
/* 276 */     return var3;
/*     */   }
/*     */ 
/*     */   
/*     */   protected BlockState createBlockState() {
/* 281 */     return new BlockState(this, new IProperty[] { (IProperty)field_176284_a, (IProperty)field_176283_b, (IProperty)field_176285_M });
/*     */   }
/*     */   
/*     */   public enum DoorHalf
/*     */     implements IStringSerializable {
/* 286 */     TOP("TOP", 0, "top"),
/* 287 */     BOTTOM("BOTTOM", 1, "bottom");
/*     */     
/*     */     private final String field_176671_c;
/* 290 */     private static final DoorHalf[] $VALUES = new DoorHalf[] { TOP, BOTTOM };
/*     */     
/*     */     private static final String __OBFID = "CL_00002051";
/*     */     
/*     */     DoorHalf(String p_i45674_1_, int p_i45674_2_, String p_i45674_3_) {
/* 295 */       this.field_176671_c = p_i45674_3_;
/*     */     } static {
/*     */     
/*     */     }
/*     */     public String toString() {
/* 300 */       return this.field_176671_c;
/*     */     }
/*     */ 
/*     */     
/*     */     public String getName() {
/* 305 */       return this.field_176671_c;
/*     */     }
/*     */   }
/*     */   
/*     */   static final class SwitchEnumFacing
/*     */   {
/* 311 */     static final int[] field_177058_a = new int[(EnumFacing.values()).length];
/*     */     
/*     */     private static final String __OBFID = "CL_00002052";
/*     */ 
/*     */     
/*     */     static {
/*     */       try {
/* 318 */         field_177058_a[EnumFacing.NORTH.ordinal()] = 1;
/*     */       }
/* 320 */       catch (NoSuchFieldError noSuchFieldError) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/* 327 */         field_177058_a[EnumFacing.SOUTH.ordinal()] = 2;
/*     */       }
/* 329 */       catch (NoSuchFieldError noSuchFieldError) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/* 336 */         field_177058_a[EnumFacing.WEST.ordinal()] = 3;
/*     */       }
/* 338 */       catch (NoSuchFieldError noSuchFieldError) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/* 345 */         field_177058_a[EnumFacing.EAST.ordinal()] = 4;
/*     */       }
/* 347 */       catch (NoSuchFieldError noSuchFieldError) {}
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\block\BlockTrapDoor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */