/*     */ package net.minecraft.block;
/*     */ 
/*     */ import com.google.common.base.Objects;
/*     */ import com.google.common.base.Predicate;
/*     */ import java.util.Iterator;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.block.properties.IProperty;
/*     */ import net.minecraft.block.properties.PropertyBool;
/*     */ import net.minecraft.block.properties.PropertyDirection;
/*     */ import net.minecraft.block.state.BlockState;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.creativetab.CreativeTabs;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.EnumWorldBlockLayer;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class BlockTripWireHook extends Block {
/*  25 */   public static final PropertyDirection field_176264_a = PropertyDirection.create("facing", (Predicate)EnumFacing.Plane.HORIZONTAL);
/*  26 */   public static final PropertyBool field_176263_b = PropertyBool.create("powered");
/*  27 */   public static final PropertyBool field_176265_M = PropertyBool.create("attached");
/*  28 */   public static final PropertyBool field_176266_N = PropertyBool.create("suspended");
/*     */   
/*     */   private static final String __OBFID = "CL_00000329";
/*     */   
/*     */   public BlockTripWireHook() {
/*  33 */     super(Material.circuits);
/*  34 */     setDefaultState(this.blockState.getBaseState().withProperty((IProperty)field_176264_a, (Comparable)EnumFacing.NORTH).withProperty((IProperty)field_176263_b, Boolean.valueOf(false)).withProperty((IProperty)field_176265_M, Boolean.valueOf(false)).withProperty((IProperty)field_176266_N, Boolean.valueOf(false)));
/*  35 */     setCreativeTab(CreativeTabs.tabRedstone);
/*  36 */     setTickRandomly(true);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
/*  45 */     return state.withProperty((IProperty)field_176266_N, Boolean.valueOf(!World.doesBlockHaveSolidTopSurface(worldIn, pos.offsetDown())));
/*     */   }
/*     */ 
/*     */   
/*     */   public AxisAlignedBB getCollisionBoundingBox(World worldIn, BlockPos pos, IBlockState state) {
/*  50 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isOpaqueCube() {
/*  55 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isFullCube() {
/*  60 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canPlaceBlockOnSide(World worldIn, BlockPos pos, EnumFacing side) {
/*  68 */     return (side.getAxis().isHorizontal() && worldIn.getBlockState(pos.offset(side.getOpposite())).getBlock().isNormalCube());
/*     */   }
/*     */   
/*     */   public boolean canPlaceBlockAt(World worldIn, BlockPos pos) {
/*     */     EnumFacing var4;
/*  73 */     Iterator<EnumFacing> var3 = EnumFacing.Plane.HORIZONTAL.iterator();
/*     */ 
/*     */ 
/*     */     
/*     */     do {
/*  78 */       if (!var3.hasNext())
/*     */       {
/*  80 */         return false;
/*     */       }
/*     */       
/*  83 */       var4 = var3.next();
/*     */     }
/*  85 */     while (!worldIn.getBlockState(pos.offset(var4)).getBlock().isNormalCube());
/*     */     
/*  87 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockState onBlockPlaced(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
/*  92 */     IBlockState var9 = getDefaultState().withProperty((IProperty)field_176263_b, Boolean.valueOf(false)).withProperty((IProperty)field_176265_M, Boolean.valueOf(false)).withProperty((IProperty)field_176266_N, Boolean.valueOf(false));
/*     */     
/*  94 */     if (facing.getAxis().isHorizontal())
/*     */     {
/*  96 */       var9 = var9.withProperty((IProperty)field_176264_a, (Comparable)facing);
/*     */     }
/*     */     
/*  99 */     return var9;
/*     */   }
/*     */ 
/*     */   
/*     */   public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
/* 104 */     func_176260_a(worldIn, pos, state, false, false, -1, (IBlockState)null);
/*     */   }
/*     */ 
/*     */   
/*     */   public void onNeighborBlockChange(World worldIn, BlockPos pos, IBlockState state, Block neighborBlock) {
/* 109 */     if (neighborBlock != this)
/*     */     {
/* 111 */       if (func_176261_e(worldIn, pos, state)) {
/*     */         
/* 113 */         EnumFacing var5 = (EnumFacing)state.getValue((IProperty)field_176264_a);
/*     */         
/* 115 */         if (!worldIn.getBlockState(pos.offset(var5.getOpposite())).getBlock().isNormalCube()) {
/*     */           
/* 117 */           dropBlockAsItem(worldIn, pos, state, 0);
/* 118 */           worldIn.setBlockToAir(pos);
/*     */         } 
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   public void func_176260_a(World worldIn, BlockPos p_176260_2_, IBlockState p_176260_3_, boolean p_176260_4_, boolean p_176260_5_, int p_176260_6_, IBlockState p_176260_7_) {
/*     */     int i, j;
/* 126 */     EnumFacing var8 = (EnumFacing)p_176260_3_.getValue((IProperty)field_176264_a);
/* 127 */     int var9 = ((Boolean)p_176260_3_.getValue((IProperty)field_176265_M)).booleanValue();
/* 128 */     boolean var10 = ((Boolean)p_176260_3_.getValue((IProperty)field_176263_b)).booleanValue();
/* 129 */     boolean var11 = !World.doesBlockHaveSolidTopSurface((IBlockAccess)worldIn, p_176260_2_.offsetDown());
/* 130 */     boolean var12 = !p_176260_4_;
/* 131 */     boolean var13 = false;
/* 132 */     int var14 = 0;
/* 133 */     IBlockState[] var15 = new IBlockState[42];
/*     */ 
/*     */     
/* 136 */     for (int var16 = 1; var16 < 42; var16++) {
/*     */       
/* 138 */       BlockPos var17 = p_176260_2_.offset(var8, var16);
/* 139 */       IBlockState var18 = worldIn.getBlockState(var17);
/*     */       
/* 141 */       if (var18.getBlock() == Blocks.tripwire_hook) {
/*     */         
/* 143 */         if (var18.getValue((IProperty)field_176264_a) == var8.getOpposite())
/*     */         {
/* 145 */           var14 = var16;
/*     */         }
/*     */         
/*     */         break;
/*     */       } 
/*     */       
/* 151 */       if (var18.getBlock() != Blocks.tripwire && var16 != p_176260_6_) {
/*     */         
/* 153 */         var15[var16] = null;
/* 154 */         var12 = false;
/*     */       }
/*     */       else {
/*     */         
/* 158 */         if (var16 == p_176260_6_)
/*     */         {
/* 160 */           var18 = (IBlockState)Objects.firstNonNull(p_176260_7_, var18);
/*     */         }
/*     */         
/* 163 */         int var19 = ((Boolean)var18.getValue((IProperty)BlockTripWire.field_176295_N)).booleanValue() ? 0 : 1;
/* 164 */         boolean var20 = ((Boolean)var18.getValue((IProperty)BlockTripWire.field_176293_a)).booleanValue();
/* 165 */         boolean var21 = ((Boolean)var18.getValue((IProperty)BlockTripWire.field_176290_b)).booleanValue();
/* 166 */         int k = var12 & ((var21 == var11) ? 1 : 0);
/* 167 */         j = var13 | ((var19 && var20) ? 1 : 0);
/* 168 */         var15[var16] = var18;
/*     */         
/* 170 */         if (var16 == p_176260_6_) {
/*     */           
/* 172 */           worldIn.scheduleUpdate(p_176260_2_, this, tickRate(worldIn));
/* 173 */           i = k & var19;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 178 */     i &= (var14 > 1) ? 1 : 0;
/* 179 */     j &= i;
/* 180 */     IBlockState var22 = getDefaultState().withProperty((IProperty)field_176265_M, Boolean.valueOf(i)).withProperty((IProperty)field_176263_b, Boolean.valueOf(j));
/*     */     
/* 182 */     if (var14 > 0) {
/*     */       
/* 184 */       BlockPos var17 = p_176260_2_.offset(var8, var14);
/* 185 */       EnumFacing var24 = var8.getOpposite();
/* 186 */       worldIn.setBlockState(var17, var22.withProperty((IProperty)field_176264_a, (Comparable)var24), 3);
/* 187 */       func_176262_b(worldIn, var17, var24);
/* 188 */       func_180694_a(worldIn, var17, i, j, var9, var10);
/*     */     } 
/*     */     
/* 191 */     func_180694_a(worldIn, p_176260_2_, i, j, var9, var10);
/*     */     
/* 193 */     if (!p_176260_4_) {
/*     */       
/* 195 */       worldIn.setBlockState(p_176260_2_, var22.withProperty((IProperty)field_176264_a, (Comparable)var8), 3);
/*     */       
/* 197 */       if (p_176260_5_)
/*     */       {
/* 199 */         func_176262_b(worldIn, p_176260_2_, var8);
/*     */       }
/*     */     } 
/*     */     
/* 203 */     if (var9 != i)
/*     */     {
/* 205 */       for (int var23 = 1; var23 < var14; var23++) {
/*     */         
/* 207 */         BlockPos var25 = p_176260_2_.offset(var8, var23);
/* 208 */         IBlockState var26 = var15[var23];
/*     */         
/* 210 */         if (var26 != null && worldIn.getBlockState(var25).getBlock() != Blocks.air)
/*     */         {
/* 212 */           worldIn.setBlockState(var25, var26.withProperty((IProperty)field_176265_M, Boolean.valueOf(i)), 3);
/*     */         }
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void randomTick(World worldIn, BlockPos pos, IBlockState state, Random random) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
/* 225 */     func_176260_a(worldIn, pos, state, false, true, -1, (IBlockState)null);
/*     */   }
/*     */ 
/*     */   
/*     */   private void func_180694_a(World worldIn, BlockPos p_180694_2_, boolean p_180694_3_, boolean p_180694_4_, boolean p_180694_5_, boolean p_180694_6_) {
/* 230 */     if (p_180694_4_ && !p_180694_6_) {
/*     */       
/* 232 */       worldIn.playSoundEffect(p_180694_2_.getX() + 0.5D, p_180694_2_.getY() + 0.1D, p_180694_2_.getZ() + 0.5D, "random.click", 0.4F, 0.6F);
/*     */     }
/* 234 */     else if (!p_180694_4_ && p_180694_6_) {
/*     */       
/* 236 */       worldIn.playSoundEffect(p_180694_2_.getX() + 0.5D, p_180694_2_.getY() + 0.1D, p_180694_2_.getZ() + 0.5D, "random.click", 0.4F, 0.5F);
/*     */     }
/* 238 */     else if (p_180694_3_ && !p_180694_5_) {
/*     */       
/* 240 */       worldIn.playSoundEffect(p_180694_2_.getX() + 0.5D, p_180694_2_.getY() + 0.1D, p_180694_2_.getZ() + 0.5D, "random.click", 0.4F, 0.7F);
/*     */     }
/* 242 */     else if (!p_180694_3_ && p_180694_5_) {
/*     */       
/* 244 */       worldIn.playSoundEffect(p_180694_2_.getX() + 0.5D, p_180694_2_.getY() + 0.1D, p_180694_2_.getZ() + 0.5D, "random.bowhit", 0.4F, 1.2F / (worldIn.rand.nextFloat() * 0.2F + 0.9F));
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void func_176262_b(World worldIn, BlockPos p_176262_2_, EnumFacing p_176262_3_) {
/* 250 */     worldIn.notifyNeighborsOfStateChange(p_176262_2_, this);
/* 251 */     worldIn.notifyNeighborsOfStateChange(p_176262_2_.offset(p_176262_3_.getOpposite()), this);
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean func_176261_e(World worldIn, BlockPos p_176261_2_, IBlockState p_176261_3_) {
/* 256 */     if (!canPlaceBlockAt(worldIn, p_176261_2_)) {
/*     */       
/* 258 */       dropBlockAsItem(worldIn, p_176261_2_, p_176261_3_, 0);
/* 259 */       worldIn.setBlockToAir(p_176261_2_);
/* 260 */       return false;
/*     */     } 
/*     */ 
/*     */     
/* 264 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setBlockBoundsBasedOnState(IBlockAccess access, BlockPos pos) {
/* 270 */     float var3 = 0.1875F;
/*     */     
/* 272 */     switch (SwitchEnumFacing.field_177056_a[((EnumFacing)access.getBlockState(pos).getValue((IProperty)field_176264_a)).ordinal()]) {
/*     */       
/*     */       case 1:
/* 275 */         setBlockBounds(0.0F, 0.2F, 0.5F - var3, var3 * 2.0F, 0.8F, 0.5F + var3);
/*     */         break;
/*     */       
/*     */       case 2:
/* 279 */         setBlockBounds(1.0F - var3 * 2.0F, 0.2F, 0.5F - var3, 1.0F, 0.8F, 0.5F + var3);
/*     */         break;
/*     */       
/*     */       case 3:
/* 283 */         setBlockBounds(0.5F - var3, 0.2F, 0.0F, 0.5F + var3, 0.8F, var3 * 2.0F);
/*     */         break;
/*     */       
/*     */       case 4:
/* 287 */         setBlockBounds(0.5F - var3, 0.2F, 1.0F - var3 * 2.0F, 0.5F + var3, 0.8F, 1.0F);
/*     */         break;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
/* 293 */     boolean var4 = ((Boolean)state.getValue((IProperty)field_176265_M)).booleanValue();
/* 294 */     boolean var5 = ((Boolean)state.getValue((IProperty)field_176263_b)).booleanValue();
/*     */     
/* 296 */     if (var4 || var5)
/*     */     {
/* 298 */       func_176260_a(worldIn, pos, state, true, false, -1, (IBlockState)null);
/*     */     }
/*     */     
/* 301 */     if (var5) {
/*     */       
/* 303 */       worldIn.notifyNeighborsOfStateChange(pos, this);
/* 304 */       worldIn.notifyNeighborsOfStateChange(pos.offset(((EnumFacing)state.getValue((IProperty)field_176264_a)).getOpposite()), this);
/*     */     } 
/*     */     
/* 307 */     super.breakBlock(worldIn, pos, state);
/*     */   }
/*     */ 
/*     */   
/*     */   public int isProvidingWeakPower(IBlockAccess worldIn, BlockPos pos, IBlockState state, EnumFacing side) {
/* 312 */     return ((Boolean)state.getValue((IProperty)field_176263_b)).booleanValue() ? 15 : 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public int isProvidingStrongPower(IBlockAccess worldIn, BlockPos pos, IBlockState state, EnumFacing side) {
/* 317 */     return !((Boolean)state.getValue((IProperty)field_176263_b)).booleanValue() ? 0 : ((state.getValue((IProperty)field_176264_a) == side) ? 15 : 0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canProvidePower() {
/* 325 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public EnumWorldBlockLayer getBlockLayer() {
/* 330 */     return EnumWorldBlockLayer.CUTOUT_MIPPED;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IBlockState getStateFromMeta(int meta) {
/* 338 */     return getDefaultState().withProperty((IProperty)field_176264_a, (Comparable)EnumFacing.getHorizontal(meta & 0x3)).withProperty((IProperty)field_176263_b, Boolean.valueOf(((meta & 0x8) > 0))).withProperty((IProperty)field_176265_M, Boolean.valueOf(((meta & 0x4) > 0)));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getMetaFromState(IBlockState state) {
/* 346 */     byte var2 = 0;
/* 347 */     int var3 = var2 | ((EnumFacing)state.getValue((IProperty)field_176264_a)).getHorizontalIndex();
/*     */     
/* 349 */     if (((Boolean)state.getValue((IProperty)field_176263_b)).booleanValue())
/*     */     {
/* 351 */       var3 |= 0x8;
/*     */     }
/*     */     
/* 354 */     if (((Boolean)state.getValue((IProperty)field_176265_M)).booleanValue())
/*     */     {
/* 356 */       var3 |= 0x4;
/*     */     }
/*     */     
/* 359 */     return var3;
/*     */   }
/*     */ 
/*     */   
/*     */   protected BlockState createBlockState() {
/* 364 */     return new BlockState(this, new IProperty[] { (IProperty)field_176264_a, (IProperty)field_176263_b, (IProperty)field_176265_M, (IProperty)field_176266_N });
/*     */   }
/*     */   
/*     */   static final class SwitchEnumFacing
/*     */   {
/* 369 */     static final int[] field_177056_a = new int[(EnumFacing.values()).length];
/*     */     
/*     */     private static final String __OBFID = "CL_00002050";
/*     */ 
/*     */     
/*     */     static {
/*     */       try {
/* 376 */         field_177056_a[EnumFacing.EAST.ordinal()] = 1;
/*     */       }
/* 378 */       catch (NoSuchFieldError noSuchFieldError) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/* 385 */         field_177056_a[EnumFacing.WEST.ordinal()] = 2;
/*     */       }
/* 387 */       catch (NoSuchFieldError noSuchFieldError) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/* 394 */         field_177056_a[EnumFacing.SOUTH.ordinal()] = 3;
/*     */       }
/* 396 */       catch (NoSuchFieldError noSuchFieldError) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/* 403 */         field_177056_a[EnumFacing.NORTH.ordinal()] = 4;
/*     */       }
/* 405 */       catch (NoSuchFieldError noSuchFieldError) {}
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\block\BlockTripWireHook.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */