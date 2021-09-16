/*     */ package net.minecraft.block;
/*     */ 
/*     */ import java.util.List;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.block.properties.IProperty;
/*     */ import net.minecraft.block.properties.PropertyBool;
/*     */ import net.minecraft.block.properties.PropertyDirection;
/*     */ import net.minecraft.block.state.BlockPistonStructureHelper;
/*     */ import net.minecraft.block.state.BlockState;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.creativetab.CreativeTabs;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.tileentity.TileEntityPiston;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class BlockPistonBase
/*     */   extends Block {
/*  27 */   public static final PropertyDirection FACING = PropertyDirection.create("facing");
/*  28 */   public static final PropertyBool EXTENDED = PropertyBool.create("extended");
/*     */   
/*     */   private final boolean isSticky;
/*     */   
/*     */   private static final String __OBFID = "CL_00000366";
/*     */ 
/*     */   
/*     */   public BlockPistonBase(boolean p_i45443_1_) {
/*  36 */     super(Material.piston);
/*  37 */     setDefaultState(this.blockState.getBaseState().withProperty((IProperty)FACING, (Comparable)EnumFacing.NORTH).withProperty((IProperty)EXTENDED, Boolean.valueOf(false)));
/*  38 */     this.isSticky = p_i45443_1_;
/*  39 */     setStepSound(soundTypePiston);
/*  40 */     setHardness(0.5F);
/*  41 */     setCreativeTab(CreativeTabs.tabRedstone);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isOpaqueCube() {
/*  46 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
/*  51 */     worldIn.setBlockState(pos, state.withProperty((IProperty)FACING, (Comparable)func_180695_a(worldIn, pos, placer)), 2);
/*     */     
/*  53 */     if (!worldIn.isRemote)
/*     */     {
/*  55 */       func_176316_e(worldIn, pos, state);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void onNeighborBlockChange(World worldIn, BlockPos pos, IBlockState state, Block neighborBlock) {
/*  61 */     if (!worldIn.isRemote)
/*     */     {
/*  63 */       func_176316_e(worldIn, pos, state);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state) {
/*  69 */     if (!worldIn.isRemote && worldIn.getTileEntity(pos) == null)
/*     */     {
/*  71 */       func_176316_e(worldIn, pos, state);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockState onBlockPlaced(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
/*  77 */     return getDefaultState().withProperty((IProperty)FACING, (Comparable)func_180695_a(worldIn, pos, placer)).withProperty((IProperty)EXTENDED, Boolean.valueOf(false));
/*     */   }
/*     */ 
/*     */   
/*     */   private void func_176316_e(World worldIn, BlockPos p_176316_2_, IBlockState p_176316_3_) {
/*  82 */     EnumFacing var4 = (EnumFacing)p_176316_3_.getValue((IProperty)FACING);
/*  83 */     boolean var5 = func_176318_b(worldIn, p_176316_2_, var4);
/*     */     
/*  85 */     if (var5 && !((Boolean)p_176316_3_.getValue((IProperty)EXTENDED)).booleanValue()) {
/*     */       
/*  87 */       if ((new BlockPistonStructureHelper(worldIn, p_176316_2_, var4, true)).func_177253_a())
/*     */       {
/*  89 */         worldIn.addBlockEvent(p_176316_2_, this, 0, var4.getIndex());
/*     */       }
/*     */     }
/*  92 */     else if (!var5 && ((Boolean)p_176316_3_.getValue((IProperty)EXTENDED)).booleanValue()) {
/*     */       
/*  94 */       worldIn.setBlockState(p_176316_2_, p_176316_3_.withProperty((IProperty)EXTENDED, Boolean.valueOf(false)), 2);
/*  95 */       worldIn.addBlockEvent(p_176316_2_, this, 1, var4.getIndex());
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean func_176318_b(World worldIn, BlockPos p_176318_2_, EnumFacing p_176318_3_) {
/* 101 */     EnumFacing[] var4 = EnumFacing.values();
/* 102 */     int var5 = var4.length;
/*     */     
/*     */     int var6;
/* 105 */     for (var6 = 0; var6 < var5; var6++) {
/*     */       
/* 107 */       EnumFacing var7 = var4[var6];
/*     */       
/* 109 */       if (var7 != p_176318_3_ && worldIn.func_175709_b(p_176318_2_.offset(var7), var7))
/*     */       {
/* 111 */         return true;
/*     */       }
/*     */     } 
/*     */     
/* 115 */     if (worldIn.func_175709_b(p_176318_2_, EnumFacing.NORTH))
/*     */     {
/* 117 */       return true;
/*     */     }
/*     */ 
/*     */     
/* 121 */     BlockPos var9 = p_176318_2_.offsetUp();
/* 122 */     EnumFacing[] var10 = EnumFacing.values();
/* 123 */     var6 = var10.length;
/*     */     
/* 125 */     for (int var11 = 0; var11 < var6; var11++) {
/*     */       
/* 127 */       EnumFacing var8 = var10[var11];
/*     */       
/* 129 */       if (var8 != EnumFacing.DOWN && worldIn.func_175709_b(var9.offset(var8), var8))
/*     */       {
/* 131 */         return true;
/*     */       }
/*     */     } 
/*     */     
/* 135 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean onBlockEventReceived(World worldIn, BlockPos pos, IBlockState state, int eventID, int eventParam) {
/* 144 */     EnumFacing var6 = (EnumFacing)state.getValue((IProperty)FACING);
/*     */     
/* 146 */     if (!worldIn.isRemote) {
/*     */       
/* 148 */       boolean var7 = func_176318_b(worldIn, pos, var6);
/*     */       
/* 150 */       if (var7 && eventID == 1) {
/*     */         
/* 152 */         worldIn.setBlockState(pos, state.withProperty((IProperty)EXTENDED, Boolean.valueOf(true)), 2);
/* 153 */         return false;
/*     */       } 
/*     */       
/* 156 */       if (!var7 && eventID == 0)
/*     */       {
/* 158 */         return false;
/*     */       }
/*     */     } 
/*     */     
/* 162 */     if (eventID == 0) {
/*     */       
/* 164 */       if (!func_176319_a(worldIn, pos, var6, true))
/*     */       {
/* 166 */         return false;
/*     */       }
/*     */       
/* 169 */       worldIn.setBlockState(pos, state.withProperty((IProperty)EXTENDED, Boolean.valueOf(true)), 2);
/* 170 */       worldIn.playSoundEffect(pos.getX() + 0.5D, pos.getY() + 0.5D, pos.getZ() + 0.5D, "tile.piston.out", 0.5F, worldIn.rand.nextFloat() * 0.25F + 0.6F);
/*     */     }
/* 172 */     else if (eventID == 1) {
/*     */       
/* 174 */       TileEntity var13 = worldIn.getTileEntity(pos.offset(var6));
/*     */       
/* 176 */       if (var13 instanceof TileEntityPiston)
/*     */       {
/* 178 */         ((TileEntityPiston)var13).clearPistonTileEntity();
/*     */       }
/*     */       
/* 181 */       worldIn.setBlockState(pos, Blocks.piston_extension.getDefaultState().withProperty((IProperty)BlockPistonMoving.field_176426_a, (Comparable)var6).withProperty((IProperty)BlockPistonMoving.field_176425_b, this.isSticky ? BlockPistonExtension.EnumPistonType.STICKY : BlockPistonExtension.EnumPistonType.DEFAULT), 3);
/* 182 */       worldIn.setTileEntity(pos, BlockPistonMoving.func_176423_a(getStateFromMeta(eventParam), var6, false, true));
/*     */       
/* 184 */       if (this.isSticky) {
/*     */         
/* 186 */         BlockPos var8 = pos.add(var6.getFrontOffsetX() * 2, var6.getFrontOffsetY() * 2, var6.getFrontOffsetZ() * 2);
/* 187 */         Block var9 = worldIn.getBlockState(var8).getBlock();
/* 188 */         boolean var10 = false;
/*     */         
/* 190 */         if (var9 == Blocks.piston_extension) {
/*     */           
/* 192 */           TileEntity var11 = worldIn.getTileEntity(var8);
/*     */           
/* 194 */           if (var11 instanceof TileEntityPiston) {
/*     */             
/* 196 */             TileEntityPiston var12 = (TileEntityPiston)var11;
/*     */             
/* 198 */             if (var12.func_174930_e() == var6 && var12.isExtending()) {
/*     */               
/* 200 */               var12.clearPistonTileEntity();
/* 201 */               var10 = true;
/*     */             } 
/*     */           } 
/*     */         } 
/*     */         
/* 206 */         if (!var10 && var9.getMaterial() != Material.air && func_180696_a(var9, worldIn, var8, var6.getOpposite(), false) && (var9.getMobilityFlag() == 0 || var9 == Blocks.piston || var9 == Blocks.sticky_piston))
/*     */         {
/* 208 */           func_176319_a(worldIn, pos, var6, false);
/*     */         }
/*     */       }
/*     */       else {
/*     */         
/* 213 */         worldIn.setBlockToAir(pos.offset(var6));
/*     */       } 
/*     */       
/* 216 */       worldIn.playSoundEffect(pos.getX() + 0.5D, pos.getY() + 0.5D, pos.getZ() + 0.5D, "tile.piston.in", 0.5F, worldIn.rand.nextFloat() * 0.15F + 0.6F);
/*     */     } 
/*     */     
/* 219 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setBlockBoundsBasedOnState(IBlockAccess access, BlockPos pos) {
/* 224 */     IBlockState var3 = access.getBlockState(pos);
/*     */     
/* 226 */     if (var3.getBlock() == this && ((Boolean)var3.getValue((IProperty)EXTENDED)).booleanValue()) {
/*     */       
/* 228 */       float var4 = 0.25F;
/* 229 */       EnumFacing var5 = (EnumFacing)var3.getValue((IProperty)FACING);
/*     */       
/* 231 */       if (var5 != null)
/*     */       {
/* 233 */         switch (SwitchEnumFacing.field_177243_a[var5.ordinal()]) {
/*     */           
/*     */           case 1:
/* 236 */             setBlockBounds(0.0F, 0.25F, 0.0F, 1.0F, 1.0F, 1.0F);
/*     */             break;
/*     */           
/*     */           case 2:
/* 240 */             setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.75F, 1.0F);
/*     */             break;
/*     */           
/*     */           case 3:
/* 244 */             setBlockBounds(0.0F, 0.0F, 0.25F, 1.0F, 1.0F, 1.0F);
/*     */             break;
/*     */           
/*     */           case 4:
/* 248 */             setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 0.75F);
/*     */             break;
/*     */           
/*     */           case 5:
/* 252 */             setBlockBounds(0.25F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
/*     */             break;
/*     */           
/*     */           case 6:
/* 256 */             setBlockBounds(0.0F, 0.0F, 0.0F, 0.75F, 1.0F, 1.0F);
/*     */             break;
/*     */         } 
/*     */       
/*     */       }
/*     */     } else {
/* 262 */       setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setBlockBoundsForItemRender() {
/* 271 */     setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addCollisionBoxesToList(World worldIn, BlockPos pos, IBlockState state, AxisAlignedBB mask, List list, Entity collidingEntity) {
/* 281 */     setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
/* 282 */     super.addCollisionBoxesToList(worldIn, pos, state, mask, list, collidingEntity);
/*     */   }
/*     */ 
/*     */   
/*     */   public AxisAlignedBB getCollisionBoundingBox(World worldIn, BlockPos pos, IBlockState state) {
/* 287 */     setBlockBoundsBasedOnState((IBlockAccess)worldIn, pos);
/* 288 */     return super.getCollisionBoundingBox(worldIn, pos, state);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isFullCube() {
/* 293 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public static EnumFacing func_176317_b(int p_176317_0_) {
/* 298 */     int var1 = p_176317_0_ & 0x7;
/* 299 */     return (var1 > 5) ? null : EnumFacing.getFront(var1);
/*     */   }
/*     */ 
/*     */   
/*     */   public static EnumFacing func_180695_a(World worldIn, BlockPos p_180695_1_, EntityLivingBase p_180695_2_) {
/* 304 */     if (MathHelper.abs((float)p_180695_2_.posX - p_180695_1_.getX()) < 2.0F && MathHelper.abs((float)p_180695_2_.posZ - p_180695_1_.getZ()) < 2.0F) {
/*     */       
/* 306 */       double var3 = p_180695_2_.posY + p_180695_2_.getEyeHeight();
/*     */       
/* 308 */       if (var3 - p_180695_1_.getY() > 2.0D)
/*     */       {
/* 310 */         return EnumFacing.UP;
/*     */       }
/*     */       
/* 313 */       if (p_180695_1_.getY() - var3 > 0.0D)
/*     */       {
/* 315 */         return EnumFacing.DOWN;
/*     */       }
/*     */     } 
/*     */     
/* 319 */     return p_180695_2_.func_174811_aO().getOpposite();
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean func_180696_a(Block p_180696_0_, World worldIn, BlockPos p_180696_2_, EnumFacing p_180696_3_, boolean p_180696_4_) {
/* 324 */     if (p_180696_0_ == Blocks.obsidian)
/*     */     {
/* 326 */       return false;
/*     */     }
/* 328 */     if (!worldIn.getWorldBorder().contains(p_180696_2_))
/*     */     {
/* 330 */       return false;
/*     */     }
/* 332 */     if (p_180696_2_.getY() >= 0 && (p_180696_3_ != EnumFacing.DOWN || p_180696_2_.getY() != 0)) {
/*     */       
/* 334 */       if (p_180696_2_.getY() <= worldIn.getHeight() - 1 && (p_180696_3_ != EnumFacing.UP || p_180696_2_.getY() != worldIn.getHeight() - 1)) {
/*     */         
/* 336 */         if (p_180696_0_ != Blocks.piston && p_180696_0_ != Blocks.sticky_piston) {
/*     */           
/* 338 */           if (p_180696_0_.getBlockHardness(worldIn, p_180696_2_) == -1.0F)
/*     */           {
/* 340 */             return false;
/*     */           }
/*     */           
/* 343 */           if (p_180696_0_.getMobilityFlag() == 2)
/*     */           {
/* 345 */             return false;
/*     */           }
/*     */           
/* 348 */           if (p_180696_0_.getMobilityFlag() == 1)
/*     */           {
/* 350 */             if (!p_180696_4_)
/*     */             {
/* 352 */               return false;
/*     */             }
/*     */             
/* 355 */             return true;
/*     */           }
/*     */         
/* 358 */         } else if (((Boolean)worldIn.getBlockState(p_180696_2_).getValue((IProperty)EXTENDED)).booleanValue()) {
/*     */           
/* 360 */           return false;
/*     */         } 
/*     */         
/* 363 */         return !(p_180696_0_ instanceof ITileEntityProvider);
/*     */       } 
/*     */ 
/*     */       
/* 367 */       return false;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 372 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean func_176319_a(World worldIn, BlockPos p_176319_2_, EnumFacing p_176319_3_, boolean p_176319_4_) {
/* 378 */     if (!p_176319_4_)
/*     */     {
/* 380 */       worldIn.setBlockToAir(p_176319_2_.offset(p_176319_3_));
/*     */     }
/*     */     
/* 383 */     BlockPistonStructureHelper var5 = new BlockPistonStructureHelper(worldIn, p_176319_2_, p_176319_3_, p_176319_4_);
/* 384 */     List<BlockPos> var6 = var5.func_177254_c();
/* 385 */     List<BlockPos> var7 = var5.func_177252_d();
/*     */     
/* 387 */     if (!var5.func_177253_a())
/*     */     {
/* 389 */       return false;
/*     */     }
/*     */ 
/*     */     
/* 393 */     int var8 = var6.size() + var7.size();
/* 394 */     Block[] var9 = new Block[var8];
/* 395 */     EnumFacing var10 = p_176319_4_ ? p_176319_3_ : p_176319_3_.getOpposite();
/*     */     
/*     */     int var11;
/*     */     
/* 399 */     for (var11 = var7.size() - 1; var11 >= 0; var11--) {
/*     */       
/* 401 */       BlockPos var12 = var7.get(var11);
/* 402 */       Block var13 = worldIn.getBlockState(var12).getBlock();
/* 403 */       var13.dropBlockAsItem(worldIn, var12, worldIn.getBlockState(var12), 0);
/* 404 */       worldIn.setBlockToAir(var12);
/* 405 */       var8--;
/* 406 */       var9[var8] = var13;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 411 */     for (var11 = var6.size() - 1; var11 >= 0; var11--) {
/*     */       
/* 413 */       BlockPos var12 = var6.get(var11);
/* 414 */       IBlockState var19 = worldIn.getBlockState(var12);
/* 415 */       Block var14 = var19.getBlock();
/* 416 */       var14.getMetaFromState(var19);
/* 417 */       worldIn.setBlockToAir(var12);
/* 418 */       var12 = var12.offset(var10);
/* 419 */       worldIn.setBlockState(var12, Blocks.piston_extension.getDefaultState().withProperty((IProperty)FACING, (Comparable)p_176319_3_), 4);
/* 420 */       worldIn.setTileEntity(var12, BlockPistonMoving.func_176423_a(var19, p_176319_3_, p_176319_4_, false));
/* 421 */       var8--;
/* 422 */       var9[var8] = var14;
/*     */     } 
/*     */     
/* 425 */     BlockPos var16 = p_176319_2_.offset(p_176319_3_);
/*     */     
/* 427 */     if (p_176319_4_) {
/*     */       
/* 429 */       BlockPistonExtension.EnumPistonType var17 = this.isSticky ? BlockPistonExtension.EnumPistonType.STICKY : BlockPistonExtension.EnumPistonType.DEFAULT;
/* 430 */       IBlockState var19 = Blocks.piston_head.getDefaultState().withProperty((IProperty)BlockPistonExtension.field_176326_a, (Comparable)p_176319_3_).withProperty((IProperty)BlockPistonExtension.field_176325_b, var17);
/* 431 */       IBlockState var20 = Blocks.piston_extension.getDefaultState().withProperty((IProperty)BlockPistonMoving.field_176426_a, (Comparable)p_176319_3_).withProperty((IProperty)BlockPistonMoving.field_176425_b, this.isSticky ? BlockPistonExtension.EnumPistonType.STICKY : BlockPistonExtension.EnumPistonType.DEFAULT);
/* 432 */       worldIn.setBlockState(var16, var20, 4);
/* 433 */       worldIn.setTileEntity(var16, BlockPistonMoving.func_176423_a(var19, p_176319_3_, true, false));
/*     */     } 
/*     */     
/*     */     int var18;
/*     */     
/* 438 */     for (var18 = var7.size() - 1; var18 >= 0; var18--)
/*     */     {
/* 440 */       worldIn.notifyNeighborsOfStateChange(var7.get(var18), var9[var8++]);
/*     */     }
/*     */     
/* 443 */     for (var18 = var6.size() - 1; var18 >= 0; var18--)
/*     */     {
/* 445 */       worldIn.notifyNeighborsOfStateChange(var6.get(var18), var9[var8++]);
/*     */     }
/*     */     
/* 448 */     if (p_176319_4_) {
/*     */       
/* 450 */       worldIn.notifyNeighborsOfStateChange(var16, Blocks.piston_head);
/* 451 */       worldIn.notifyNeighborsOfStateChange(p_176319_2_, this);
/*     */     } 
/*     */     
/* 454 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IBlockState getStateForEntityRender(IBlockState state) {
/* 463 */     return getDefaultState().withProperty((IProperty)FACING, (Comparable)EnumFacing.UP);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IBlockState getStateFromMeta(int meta) {
/* 471 */     return getDefaultState().withProperty((IProperty)FACING, (Comparable)func_176317_b(meta)).withProperty((IProperty)EXTENDED, Boolean.valueOf(((meta & 0x8) > 0)));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getMetaFromState(IBlockState state) {
/* 479 */     byte var2 = 0;
/* 480 */     int var3 = var2 | ((EnumFacing)state.getValue((IProperty)FACING)).getIndex();
/*     */     
/* 482 */     if (((Boolean)state.getValue((IProperty)EXTENDED)).booleanValue())
/*     */     {
/* 484 */       var3 |= 0x8;
/*     */     }
/*     */     
/* 487 */     return var3;
/*     */   }
/*     */ 
/*     */   
/*     */   protected BlockState createBlockState() {
/* 492 */     return new BlockState(this, new IProperty[] { (IProperty)FACING, (IProperty)EXTENDED });
/*     */   }
/*     */   
/*     */   static final class SwitchEnumFacing
/*     */   {
/* 497 */     static final int[] field_177243_a = new int[(EnumFacing.values()).length];
/*     */     
/*     */     private static final String __OBFID = "CL_00002037";
/*     */ 
/*     */     
/*     */     static {
/*     */       try {
/* 504 */         field_177243_a[EnumFacing.DOWN.ordinal()] = 1;
/*     */       }
/* 506 */       catch (NoSuchFieldError noSuchFieldError) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/* 513 */         field_177243_a[EnumFacing.UP.ordinal()] = 2;
/*     */       }
/* 515 */       catch (NoSuchFieldError noSuchFieldError) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/* 522 */         field_177243_a[EnumFacing.NORTH.ordinal()] = 3;
/*     */       }
/* 524 */       catch (NoSuchFieldError noSuchFieldError) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/* 531 */         field_177243_a[EnumFacing.SOUTH.ordinal()] = 4;
/*     */       }
/* 533 */       catch (NoSuchFieldError noSuchFieldError) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/* 540 */         field_177243_a[EnumFacing.WEST.ordinal()] = 5;
/*     */       }
/* 542 */       catch (NoSuchFieldError noSuchFieldError) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/* 549 */         field_177243_a[EnumFacing.EAST.ordinal()] = 6;
/*     */       }
/* 551 */       catch (NoSuchFieldError noSuchFieldError) {}
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\block\BlockPistonBase.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */