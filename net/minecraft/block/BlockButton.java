/*     */ package net.minecraft.block;
/*     */ 
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.block.properties.IProperty;
/*     */ import net.minecraft.block.properties.PropertyBool;
/*     */ import net.minecraft.block.properties.PropertyDirection;
/*     */ import net.minecraft.block.state.BlockState;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.creativetab.CreativeTabs;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.projectile.EntityArrow;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public abstract class BlockButton
/*     */   extends Block {
/*  24 */   public static final PropertyDirection FACING_PROP = PropertyDirection.create("facing");
/*  25 */   public static final PropertyBool POWERED_PROP = PropertyBool.create("powered");
/*     */   
/*     */   private final boolean wooden;
/*     */   private static final String __OBFID = "CL_00000209";
/*     */   
/*     */   protected BlockButton(boolean wooden) {
/*  31 */     super(Material.circuits);
/*  32 */     setDefaultState(this.blockState.getBaseState().withProperty((IProperty)FACING_PROP, (Comparable)EnumFacing.NORTH).withProperty((IProperty)POWERED_PROP, Boolean.valueOf(false)));
/*  33 */     setTickRandomly(true);
/*  34 */     setCreativeTab(CreativeTabs.tabRedstone);
/*  35 */     this.wooden = wooden;
/*     */   }
/*     */ 
/*     */   
/*     */   public AxisAlignedBB getCollisionBoundingBox(World worldIn, BlockPos pos, IBlockState state) {
/*  40 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int tickRate(World worldIn) {
/*  48 */     return this.wooden ? 30 : 20;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isOpaqueCube() {
/*  53 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isFullCube() {
/*  58 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canPlaceBlockOnSide(World worldIn, BlockPos pos, EnumFacing side) {
/*  66 */     return worldIn.getBlockState(pos.offset(side.getOpposite())).getBlock().isNormalCube();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canPlaceBlockAt(World worldIn, BlockPos pos) {
/*  71 */     EnumFacing[] var3 = EnumFacing.values();
/*  72 */     int var4 = var3.length;
/*     */     
/*  74 */     for (int var5 = 0; var5 < var4; var5++) {
/*     */       
/*  76 */       EnumFacing var6 = var3[var5];
/*     */       
/*  78 */       if (worldIn.getBlockState(pos.offset(var6)).getBlock().isNormalCube())
/*     */       {
/*  80 */         return true;
/*     */       }
/*     */     } 
/*     */     
/*  84 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockState onBlockPlaced(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
/*  89 */     return worldIn.getBlockState(pos.offset(facing.getOpposite())).getBlock().isNormalCube() ? getDefaultState().withProperty((IProperty)FACING_PROP, (Comparable)facing).withProperty((IProperty)POWERED_PROP, Boolean.valueOf(false)) : getDefaultState().withProperty((IProperty)FACING_PROP, (Comparable)EnumFacing.DOWN).withProperty((IProperty)POWERED_PROP, Boolean.valueOf(false));
/*     */   }
/*     */ 
/*     */   
/*     */   public void onNeighborBlockChange(World worldIn, BlockPos pos, IBlockState state, Block neighborBlock) {
/*  94 */     if (func_176583_e(worldIn, pos, state)) {
/*     */       
/*  96 */       EnumFacing var5 = (EnumFacing)state.getValue((IProperty)FACING_PROP);
/*     */       
/*  98 */       if (!worldIn.getBlockState(pos.offset(var5.getOpposite())).getBlock().isNormalCube()) {
/*     */         
/* 100 */         dropBlockAsItem(worldIn, pos, state, 0);
/* 101 */         worldIn.setBlockToAir(pos);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean func_176583_e(World worldIn, BlockPos p_176583_2_, IBlockState p_176583_3_) {
/* 108 */     if (!canPlaceBlockAt(worldIn, p_176583_2_)) {
/*     */       
/* 110 */       dropBlockAsItem(worldIn, p_176583_2_, p_176583_3_, 0);
/* 111 */       worldIn.setBlockToAir(p_176583_2_);
/* 112 */       return false;
/*     */     } 
/*     */ 
/*     */     
/* 116 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setBlockBoundsBasedOnState(IBlockAccess access, BlockPos pos) {
/* 122 */     func_180681_d(access.getBlockState(pos));
/*     */   }
/*     */ 
/*     */   
/*     */   private void func_180681_d(IBlockState p_180681_1_) {
/* 127 */     EnumFacing var2 = (EnumFacing)p_180681_1_.getValue((IProperty)FACING_PROP);
/* 128 */     boolean var3 = ((Boolean)p_180681_1_.getValue((IProperty)POWERED_PROP)).booleanValue();
/* 129 */     float var4 = 0.25F;
/* 130 */     float var5 = 0.375F;
/* 131 */     float var6 = (var3 ? true : 2) / 16.0F;
/* 132 */     float var7 = 0.125F;
/* 133 */     float var8 = 0.1875F;
/*     */     
/* 135 */     switch (SwitchEnumFacing.field_180420_a[var2.ordinal()]) {
/*     */       
/*     */       case 1:
/* 138 */         setBlockBounds(0.0F, 0.375F, 0.3125F, var6, 0.625F, 0.6875F);
/*     */         break;
/*     */       
/*     */       case 2:
/* 142 */         setBlockBounds(1.0F - var6, 0.375F, 0.3125F, 1.0F, 0.625F, 0.6875F);
/*     */         break;
/*     */       
/*     */       case 3:
/* 146 */         setBlockBounds(0.3125F, 0.375F, 0.0F, 0.6875F, 0.625F, var6);
/*     */         break;
/*     */       
/*     */       case 4:
/* 150 */         setBlockBounds(0.3125F, 0.375F, 1.0F - var6, 0.6875F, 0.625F, 1.0F);
/*     */         break;
/*     */       
/*     */       case 5:
/* 154 */         setBlockBounds(0.3125F, 0.0F, 0.375F, 0.6875F, 0.0F + var6, 0.625F);
/*     */         break;
/*     */       
/*     */       case 6:
/* 158 */         setBlockBounds(0.3125F, 1.0F - var6, 0.375F, 0.6875F, 1.0F, 0.625F);
/*     */         break;
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumFacing side, float hitX, float hitY, float hitZ) {
/* 164 */     if (((Boolean)state.getValue((IProperty)POWERED_PROP)).booleanValue())
/*     */     {
/* 166 */       return true;
/*     */     }
/*     */ 
/*     */     
/* 170 */     worldIn.setBlockState(pos, state.withProperty((IProperty)POWERED_PROP, Boolean.valueOf(true)), 3);
/* 171 */     worldIn.markBlockRangeForRenderUpdate(pos, pos);
/* 172 */     worldIn.playSoundEffect(pos.getX() + 0.5D, pos.getY() + 0.5D, pos.getZ() + 0.5D, "random.click", 0.3F, 0.6F);
/* 173 */     func_176582_b(worldIn, pos, (EnumFacing)state.getValue((IProperty)FACING_PROP));
/* 174 */     worldIn.scheduleUpdate(pos, this, tickRate(worldIn));
/* 175 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
/* 181 */     if (((Boolean)state.getValue((IProperty)POWERED_PROP)).booleanValue())
/*     */     {
/* 183 */       func_176582_b(worldIn, pos, (EnumFacing)state.getValue((IProperty)FACING_PROP));
/*     */     }
/*     */     
/* 186 */     super.breakBlock(worldIn, pos, state);
/*     */   }
/*     */ 
/*     */   
/*     */   public int isProvidingWeakPower(IBlockAccess worldIn, BlockPos pos, IBlockState state, EnumFacing side) {
/* 191 */     return ((Boolean)state.getValue((IProperty)POWERED_PROP)).booleanValue() ? 15 : 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public int isProvidingStrongPower(IBlockAccess worldIn, BlockPos pos, IBlockState state, EnumFacing side) {
/* 196 */     return !((Boolean)state.getValue((IProperty)POWERED_PROP)).booleanValue() ? 0 : ((state.getValue((IProperty)FACING_PROP) == side) ? 15 : 0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canProvidePower() {
/* 204 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void randomTick(World worldIn, BlockPos pos, IBlockState state, Random random) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
/* 214 */     if (!worldIn.isRemote)
/*     */     {
/* 216 */       if (((Boolean)state.getValue((IProperty)POWERED_PROP)).booleanValue())
/*     */       {
/* 218 */         if (this.wooden) {
/*     */           
/* 220 */           func_180680_f(worldIn, pos, state);
/*     */         }
/*     */         else {
/*     */           
/* 224 */           worldIn.setBlockState(pos, state.withProperty((IProperty)POWERED_PROP, Boolean.valueOf(false)));
/* 225 */           func_176582_b(worldIn, pos, (EnumFacing)state.getValue((IProperty)FACING_PROP));
/* 226 */           worldIn.playSoundEffect(pos.getX() + 0.5D, pos.getY() + 0.5D, pos.getZ() + 0.5D, "random.click", 0.3F, 0.5F);
/* 227 */           worldIn.markBlockRangeForRenderUpdate(pos, pos);
/*     */         } 
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setBlockBoundsForItemRender() {
/* 238 */     float var1 = 0.1875F;
/* 239 */     float var2 = 0.125F;
/* 240 */     float var3 = 0.125F;
/* 241 */     setBlockBounds(0.5F - var1, 0.5F - var2, 0.5F - var3, 0.5F + var1, 0.5F + var2, 0.5F + var3);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onEntityCollidedWithBlock(World worldIn, BlockPos pos, IBlockState state, Entity entityIn) {
/* 249 */     if (!worldIn.isRemote)
/*     */     {
/* 251 */       if (this.wooden)
/*     */       {
/* 253 */         if (!((Boolean)state.getValue((IProperty)POWERED_PROP)).booleanValue())
/*     */         {
/* 255 */           func_180680_f(worldIn, pos, state);
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private void func_180680_f(World worldIn, BlockPos p_180680_2_, IBlockState p_180680_3_) {
/* 263 */     func_180681_d(p_180680_3_);
/* 264 */     List var4 = worldIn.getEntitiesWithinAABB(EntityArrow.class, new AxisAlignedBB(p_180680_2_.getX() + this.minX, p_180680_2_.getY() + this.minY, p_180680_2_.getZ() + this.minZ, p_180680_2_.getX() + this.maxX, p_180680_2_.getY() + this.maxY, p_180680_2_.getZ() + this.maxZ));
/* 265 */     boolean var5 = !var4.isEmpty();
/* 266 */     boolean var6 = ((Boolean)p_180680_3_.getValue((IProperty)POWERED_PROP)).booleanValue();
/*     */     
/* 268 */     if (var5 && !var6) {
/*     */       
/* 270 */       worldIn.setBlockState(p_180680_2_, p_180680_3_.withProperty((IProperty)POWERED_PROP, Boolean.valueOf(true)));
/* 271 */       func_176582_b(worldIn, p_180680_2_, (EnumFacing)p_180680_3_.getValue((IProperty)FACING_PROP));
/* 272 */       worldIn.markBlockRangeForRenderUpdate(p_180680_2_, p_180680_2_);
/* 273 */       worldIn.playSoundEffect(p_180680_2_.getX() + 0.5D, p_180680_2_.getY() + 0.5D, p_180680_2_.getZ() + 0.5D, "random.click", 0.3F, 0.6F);
/*     */     } 
/*     */     
/* 276 */     if (!var5 && var6) {
/*     */       
/* 278 */       worldIn.setBlockState(p_180680_2_, p_180680_3_.withProperty((IProperty)POWERED_PROP, Boolean.valueOf(false)));
/* 279 */       func_176582_b(worldIn, p_180680_2_, (EnumFacing)p_180680_3_.getValue((IProperty)FACING_PROP));
/* 280 */       worldIn.markBlockRangeForRenderUpdate(p_180680_2_, p_180680_2_);
/* 281 */       worldIn.playSoundEffect(p_180680_2_.getX() + 0.5D, p_180680_2_.getY() + 0.5D, p_180680_2_.getZ() + 0.5D, "random.click", 0.3F, 0.5F);
/*     */     } 
/*     */     
/* 284 */     if (var5)
/*     */     {
/* 286 */       worldIn.scheduleUpdate(p_180680_2_, this, tickRate(worldIn));
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private void func_176582_b(World worldIn, BlockPos p_176582_2_, EnumFacing p_176582_3_) {
/* 292 */     worldIn.notifyNeighborsOfStateChange(p_176582_2_, this);
/* 293 */     worldIn.notifyNeighborsOfStateChange(p_176582_2_.offset(p_176582_3_.getOpposite()), this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IBlockState getStateFromMeta(int meta) {
/*     */     EnumFacing var2;
/* 303 */     switch (meta & 0x7) {
/*     */       
/*     */       case 0:
/* 306 */         var2 = EnumFacing.DOWN;
/*     */         break;
/*     */       
/*     */       case 1:
/* 310 */         var2 = EnumFacing.EAST;
/*     */         break;
/*     */       
/*     */       case 2:
/* 314 */         var2 = EnumFacing.WEST;
/*     */         break;
/*     */       
/*     */       case 3:
/* 318 */         var2 = EnumFacing.SOUTH;
/*     */         break;
/*     */       
/*     */       case 4:
/* 322 */         var2 = EnumFacing.NORTH;
/*     */         break;
/*     */ 
/*     */       
/*     */       default:
/* 327 */         var2 = EnumFacing.UP;
/*     */         break;
/*     */     } 
/* 330 */     return getDefaultState().withProperty((IProperty)FACING_PROP, (Comparable)var2).withProperty((IProperty)POWERED_PROP, Boolean.valueOf(((meta & 0x8) > 0)));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getMetaFromState(IBlockState state) {
/*     */     int var2;
/* 340 */     switch (SwitchEnumFacing.field_180420_a[((EnumFacing)state.getValue((IProperty)FACING_PROP)).ordinal()]) {
/*     */       
/*     */       case 1:
/* 343 */         var2 = 1;
/*     */         break;
/*     */       
/*     */       case 2:
/* 347 */         var2 = 2;
/*     */         break;
/*     */       
/*     */       case 3:
/* 351 */         var2 = 3;
/*     */         break;
/*     */       
/*     */       case 4:
/* 355 */         var2 = 4;
/*     */         break;
/*     */ 
/*     */       
/*     */       default:
/* 360 */         var2 = 5;
/*     */         break;
/*     */       
/*     */       case 6:
/* 364 */         var2 = 0;
/*     */         break;
/*     */     } 
/* 367 */     if (((Boolean)state.getValue((IProperty)POWERED_PROP)).booleanValue())
/*     */     {
/* 369 */       var2 |= 0x8;
/*     */     }
/*     */     
/* 372 */     return var2;
/*     */   }
/*     */ 
/*     */   
/*     */   protected BlockState createBlockState() {
/* 377 */     return new BlockState(this, new IProperty[] { (IProperty)FACING_PROP, (IProperty)POWERED_PROP });
/*     */   }
/*     */   
/*     */   static final class SwitchEnumFacing
/*     */   {
/* 382 */     static final int[] field_180420_a = new int[(EnumFacing.values()).length];
/*     */     
/*     */     private static final String __OBFID = "CL_00002131";
/*     */ 
/*     */     
/*     */     static {
/*     */       try {
/* 389 */         field_180420_a[EnumFacing.EAST.ordinal()] = 1;
/*     */       }
/* 391 */       catch (NoSuchFieldError noSuchFieldError) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/* 398 */         field_180420_a[EnumFacing.WEST.ordinal()] = 2;
/*     */       }
/* 400 */       catch (NoSuchFieldError noSuchFieldError) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/* 407 */         field_180420_a[EnumFacing.SOUTH.ordinal()] = 3;
/*     */       }
/* 409 */       catch (NoSuchFieldError noSuchFieldError) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/* 416 */         field_180420_a[EnumFacing.NORTH.ordinal()] = 4;
/*     */       }
/* 418 */       catch (NoSuchFieldError noSuchFieldError) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/* 425 */         field_180420_a[EnumFacing.UP.ordinal()] = 5;
/*     */       }
/* 427 */       catch (NoSuchFieldError noSuchFieldError) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/* 434 */         field_180420_a[EnumFacing.DOWN.ordinal()] = 6;
/*     */       }
/* 436 */       catch (NoSuchFieldError noSuchFieldError) {}
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\block\BlockButton.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */