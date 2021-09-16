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
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.EnumParticleTypes;
/*     */ import net.minecraft.util.EnumWorldBlockLayer;
/*     */ import net.minecraft.util.MovingObjectPosition;
/*     */ import net.minecraft.util.Vec3;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class BlockTorch extends Block {
/*  25 */   public static final PropertyDirection FACING_PROP = PropertyDirection.create("facing", new Predicate()
/*     */       {
/*     */         private static final String __OBFID = "CL_00002054";
/*     */         
/*     */         public boolean func_176601_a(EnumFacing p_176601_1_) {
/*  30 */           return (p_176601_1_ != EnumFacing.DOWN);
/*     */         }
/*     */         
/*     */         public boolean apply(Object p_apply_1_) {
/*  34 */           return func_176601_a((EnumFacing)p_apply_1_);
/*     */         }
/*     */       });
/*     */   
/*     */   private static final String __OBFID = "CL_00000325";
/*     */   
/*     */   protected BlockTorch() {
/*  41 */     super(Material.circuits);
/*  42 */     setDefaultState(this.blockState.getBaseState().withProperty((IProperty)FACING_PROP, (Comparable)EnumFacing.UP));
/*  43 */     setTickRandomly(true);
/*  44 */     setCreativeTab(CreativeTabs.tabDecorations);
/*     */   }
/*     */ 
/*     */   
/*     */   public AxisAlignedBB getCollisionBoundingBox(World worldIn, BlockPos pos, IBlockState state) {
/*  49 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isOpaqueCube() {
/*  54 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isFullCube() {
/*  59 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean func_176594_d(World worldIn, BlockPos p_176594_2_) {
/*  64 */     if (World.doesBlockHaveSolidTopSurface((IBlockAccess)worldIn, p_176594_2_))
/*     */     {
/*  66 */       return true;
/*     */     }
/*     */ 
/*     */     
/*  70 */     Block var3 = worldIn.getBlockState(p_176594_2_).getBlock();
/*  71 */     return !(!(var3 instanceof BlockFence) && var3 != Blocks.glass && var3 != Blocks.cobblestone_wall && var3 != Blocks.stained_glass);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canPlaceBlockAt(World worldIn, BlockPos pos) {
/*     */     EnumFacing var4;
/*  77 */     Iterator<EnumFacing> var3 = FACING_PROP.getAllowedValues().iterator();
/*     */ 
/*     */ 
/*     */     
/*     */     do {
/*  82 */       if (!var3.hasNext())
/*     */       {
/*  84 */         return false;
/*     */       }
/*     */       
/*  87 */       var4 = var3.next();
/*     */     }
/*  89 */     while (!func_176595_b(worldIn, pos, var4));
/*     */     
/*  91 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean func_176595_b(World worldIn, BlockPos p_176595_2_, EnumFacing p_176595_3_) {
/*  96 */     BlockPos var4 = p_176595_2_.offset(p_176595_3_.getOpposite());
/*  97 */     boolean var5 = p_176595_3_.getAxis().isHorizontal();
/*  98 */     return !((!var5 || !worldIn.func_175677_d(var4, true)) && (!p_176595_3_.equals(EnumFacing.UP) || !func_176594_d(worldIn, var4)));
/*     */   }
/*     */   
/*     */   public IBlockState onBlockPlaced(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
/*     */     EnumFacing var10;
/* 103 */     if (func_176595_b(worldIn, pos, facing))
/*     */     {
/* 105 */       return getDefaultState().withProperty((IProperty)FACING_PROP, (Comparable)facing);
/*     */     }
/*     */ 
/*     */     
/* 109 */     Iterator<EnumFacing> var9 = EnumFacing.Plane.HORIZONTAL.iterator();
/*     */ 
/*     */ 
/*     */     
/*     */     do {
/* 114 */       if (!var9.hasNext())
/*     */       {
/* 116 */         return getDefaultState();
/*     */       }
/*     */       
/* 119 */       var10 = var9.next();
/*     */     }
/* 121 */     while (!worldIn.func_175677_d(pos.offset(var10.getOpposite()), true));
/*     */     
/* 123 */     return getDefaultState().withProperty((IProperty)FACING_PROP, (Comparable)var10);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state) {
/* 129 */     func_176593_f(worldIn, pos, state);
/*     */   }
/*     */ 
/*     */   
/*     */   public void onNeighborBlockChange(World worldIn, BlockPos pos, IBlockState state, Block neighborBlock) {
/* 134 */     func_176592_e(worldIn, pos, state);
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean func_176592_e(World worldIn, BlockPos p_176592_2_, IBlockState p_176592_3_) {
/* 139 */     if (!func_176593_f(worldIn, p_176592_2_, p_176592_3_))
/*     */     {
/* 141 */       return true;
/*     */     }
/*     */ 
/*     */     
/* 145 */     EnumFacing var4 = (EnumFacing)p_176592_3_.getValue((IProperty)FACING_PROP);
/* 146 */     EnumFacing.Axis var5 = var4.getAxis();
/* 147 */     EnumFacing var6 = var4.getOpposite();
/* 148 */     boolean var7 = false;
/*     */     
/* 150 */     if (var5.isHorizontal() && !worldIn.func_175677_d(p_176592_2_.offset(var6), true)) {
/*     */       
/* 152 */       var7 = true;
/*     */     }
/* 154 */     else if (var5.isVertical() && !func_176594_d(worldIn, p_176592_2_.offset(var6))) {
/*     */       
/* 156 */       var7 = true;
/*     */     } 
/*     */     
/* 159 */     if (var7) {
/*     */       
/* 161 */       dropBlockAsItem(worldIn, p_176592_2_, p_176592_3_, 0);
/* 162 */       worldIn.setBlockToAir(p_176592_2_);
/* 163 */       return true;
/*     */     } 
/*     */ 
/*     */     
/* 167 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean func_176593_f(World worldIn, BlockPos p_176593_2_, IBlockState p_176593_3_) {
/* 174 */     if (p_176593_3_.getBlock() == this && func_176595_b(worldIn, p_176593_2_, (EnumFacing)p_176593_3_.getValue((IProperty)FACING_PROP)))
/*     */     {
/* 176 */       return true;
/*     */     }
/*     */ 
/*     */     
/* 180 */     if (worldIn.getBlockState(p_176593_2_).getBlock() == this) {
/*     */       
/* 182 */       dropBlockAsItem(worldIn, p_176593_2_, p_176593_3_, 0);
/* 183 */       worldIn.setBlockToAir(p_176593_2_);
/*     */     } 
/*     */     
/* 186 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MovingObjectPosition collisionRayTrace(World worldIn, BlockPos pos, Vec3 start, Vec3 end) {
/* 198 */     EnumFacing var5 = (EnumFacing)worldIn.getBlockState(pos).getValue((IProperty)FACING_PROP);
/* 199 */     float var6 = 0.15F;
/*     */     
/* 201 */     if (var5 == EnumFacing.EAST) {
/*     */       
/* 203 */       setBlockBounds(0.0F, 0.2F, 0.5F - var6, var6 * 2.0F, 0.8F, 0.5F + var6);
/*     */     }
/* 205 */     else if (var5 == EnumFacing.WEST) {
/*     */       
/* 207 */       setBlockBounds(1.0F - var6 * 2.0F, 0.2F, 0.5F - var6, 1.0F, 0.8F, 0.5F + var6);
/*     */     }
/* 209 */     else if (var5 == EnumFacing.SOUTH) {
/*     */       
/* 211 */       setBlockBounds(0.5F - var6, 0.2F, 0.0F, 0.5F + var6, 0.8F, var6 * 2.0F);
/*     */     }
/* 213 */     else if (var5 == EnumFacing.NORTH) {
/*     */       
/* 215 */       setBlockBounds(0.5F - var6, 0.2F, 1.0F - var6 * 2.0F, 0.5F + var6, 0.8F, 1.0F);
/*     */     }
/*     */     else {
/*     */       
/* 219 */       var6 = 0.1F;
/* 220 */       setBlockBounds(0.5F - var6, 0.0F, 0.5F - var6, 0.5F + var6, 0.6F, 0.5F + var6);
/*     */     } 
/*     */     
/* 223 */     return super.collisionRayTrace(worldIn, pos, start, end);
/*     */   }
/*     */ 
/*     */   
/*     */   public void randomDisplayTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
/* 228 */     EnumFacing var5 = (EnumFacing)state.getValue((IProperty)FACING_PROP);
/* 229 */     double var6 = pos.getX() + 0.5D;
/* 230 */     double var8 = pos.getY() + 0.7D;
/* 231 */     double var10 = pos.getZ() + 0.5D;
/* 232 */     double var12 = 0.22D;
/* 233 */     double var14 = 0.27D;
/*     */     
/* 235 */     if (var5.getAxis().isHorizontal()) {
/*     */       
/* 237 */       EnumFacing var16 = var5.getOpposite();
/* 238 */       worldIn.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, var6 + var14 * var16.getFrontOffsetX(), var8 + var12, var10 + var14 * var16.getFrontOffsetZ(), 0.0D, 0.0D, 0.0D, new int[0]);
/* 239 */       worldIn.spawnParticle(EnumParticleTypes.FLAME, var6 + var14 * var16.getFrontOffsetX(), var8 + var12, var10 + var14 * var16.getFrontOffsetZ(), 0.0D, 0.0D, 0.0D, new int[0]);
/*     */     }
/*     */     else {
/*     */       
/* 243 */       worldIn.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, var6, var8, var10, 0.0D, 0.0D, 0.0D, new int[0]);
/* 244 */       worldIn.spawnParticle(EnumParticleTypes.FLAME, var6, var8, var10, 0.0D, 0.0D, 0.0D, new int[0]);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public EnumWorldBlockLayer getBlockLayer() {
/* 250 */     return EnumWorldBlockLayer.CUTOUT;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IBlockState getStateFromMeta(int meta) {
/* 258 */     IBlockState var2 = getDefaultState();
/*     */     
/* 260 */     switch (meta)
/*     */     
/*     */     { case 1:
/* 263 */         var2 = var2.withProperty((IProperty)FACING_PROP, (Comparable)EnumFacing.EAST);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 283 */         return var2;case 2: var2 = var2.withProperty((IProperty)FACING_PROP, (Comparable)EnumFacing.WEST); return var2;case 3: var2 = var2.withProperty((IProperty)FACING_PROP, (Comparable)EnumFacing.SOUTH); return var2;case 4: var2 = var2.withProperty((IProperty)FACING_PROP, (Comparable)EnumFacing.NORTH); return var2; }  var2 = var2.withProperty((IProperty)FACING_PROP, (Comparable)EnumFacing.UP); return var2;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getMetaFromState(IBlockState state) {
/* 291 */     byte var2 = 0;
/*     */ 
/*     */     
/* 294 */     switch (SwitchEnumFacing.field_176609_a[((EnumFacing)state.getValue((IProperty)FACING_PROP)).ordinal()])
/*     */     
/*     */     { case 1:
/* 297 */         var3 = var2 | 0x1;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 318 */         return var3;case 2: var3 = var2 | 0x2; return var3;case 3: var3 = var2 | 0x3; return var3;case 4: var3 = var2 | 0x4; return var3; }  int var3 = var2 | 0x5; return var3;
/*     */   }
/*     */ 
/*     */   
/*     */   protected BlockState createBlockState() {
/* 323 */     return new BlockState(this, new IProperty[] { (IProperty)FACING_PROP });
/*     */   }
/*     */   
/*     */   static final class SwitchEnumFacing
/*     */   {
/* 328 */     static final int[] field_176609_a = new int[(EnumFacing.values()).length];
/*     */     
/*     */     private static final String __OBFID = "CL_00002053";
/*     */ 
/*     */     
/*     */     static {
/*     */       try {
/* 335 */         field_176609_a[EnumFacing.EAST.ordinal()] = 1;
/*     */       }
/* 337 */       catch (NoSuchFieldError noSuchFieldError) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/* 344 */         field_176609_a[EnumFacing.WEST.ordinal()] = 2;
/*     */       }
/* 346 */       catch (NoSuchFieldError noSuchFieldError) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/* 353 */         field_176609_a[EnumFacing.SOUTH.ordinal()] = 3;
/*     */       }
/* 355 */       catch (NoSuchFieldError noSuchFieldError) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/* 362 */         field_176609_a[EnumFacing.NORTH.ordinal()] = 4;
/*     */       }
/* 364 */       catch (NoSuchFieldError noSuchFieldError) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/* 371 */         field_176609_a[EnumFacing.DOWN.ordinal()] = 5;
/*     */       }
/* 373 */       catch (NoSuchFieldError noSuchFieldError) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/* 380 */         field_176609_a[EnumFacing.UP.ordinal()] = 6;
/*     */       }
/* 382 */       catch (NoSuchFieldError noSuchFieldError) {}
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\block\BlockTorch.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */