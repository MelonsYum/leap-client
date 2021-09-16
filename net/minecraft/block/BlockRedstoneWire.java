/*     */ package net.minecraft.block;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import com.google.common.collect.Sets;
/*     */ import java.util.ArrayList;
/*     */ import java.util.EnumSet;
/*     */ import java.util.Iterator;
/*     */ import java.util.Random;
/*     */ import java.util.Set;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.block.properties.IProperty;
/*     */ import net.minecraft.block.properties.PropertyEnum;
/*     */ import net.minecraft.block.properties.PropertyInteger;
/*     */ import net.minecraft.block.state.BlockState;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.EnumParticleTypes;
/*     */ import net.minecraft.util.EnumWorldBlockLayer;
/*     */ import net.minecraft.util.IStringSerializable;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class BlockRedstoneWire
/*     */   extends Block {
/*  31 */   public static final PropertyEnum NORTH = PropertyEnum.create("north", EnumAttachPosition.class);
/*  32 */   public static final PropertyEnum EAST = PropertyEnum.create("east", EnumAttachPosition.class);
/*  33 */   public static final PropertyEnum SOUTH = PropertyEnum.create("south", EnumAttachPosition.class);
/*  34 */   public static final PropertyEnum WEST = PropertyEnum.create("west", EnumAttachPosition.class);
/*  35 */   public static final PropertyInteger POWER = PropertyInteger.create("power", 0, 15);
/*     */   private boolean canProvidePower = true;
/*  37 */   private final Set field_150179_b = Sets.newHashSet();
/*     */   
/*     */   private static final String __OBFID = "CL_00000295";
/*     */   
/*     */   public BlockRedstoneWire() {
/*  42 */     super(Material.circuits);
/*  43 */     setDefaultState(this.blockState.getBaseState().withProperty((IProperty)NORTH, EnumAttachPosition.NONE).withProperty((IProperty)EAST, EnumAttachPosition.NONE).withProperty((IProperty)SOUTH, EnumAttachPosition.NONE).withProperty((IProperty)WEST, EnumAttachPosition.NONE).withProperty((IProperty)POWER, Integer.valueOf(0)));
/*  44 */     setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.0625F, 1.0F);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
/*  53 */     state = state.withProperty((IProperty)WEST, getAttachPosition(worldIn, pos, EnumFacing.WEST));
/*  54 */     state = state.withProperty((IProperty)EAST, getAttachPosition(worldIn, pos, EnumFacing.EAST));
/*  55 */     state = state.withProperty((IProperty)NORTH, getAttachPosition(worldIn, pos, EnumFacing.NORTH));
/*  56 */     state = state.withProperty((IProperty)SOUTH, getAttachPosition(worldIn, pos, EnumFacing.SOUTH));
/*  57 */     return state;
/*     */   }
/*     */ 
/*     */   
/*     */   private EnumAttachPosition getAttachPosition(IBlockAccess p_176341_1_, BlockPos p_176341_2_, EnumFacing p_176341_3_) {
/*  62 */     BlockPos var4 = p_176341_2_.offset(p_176341_3_);
/*  63 */     Block var5 = p_176341_1_.getBlockState(p_176341_2_.offset(p_176341_3_)).getBlock();
/*     */     
/*  65 */     if (!func_176343_a(p_176341_1_.getBlockState(var4), p_176341_3_) && (var5.isSolidFullCube() || !func_176346_d(p_176341_1_.getBlockState(var4.offsetDown())))) {
/*     */       
/*  67 */       Block var6 = p_176341_1_.getBlockState(p_176341_2_.offsetUp()).getBlock();
/*  68 */       return (!var6.isSolidFullCube() && var5.isSolidFullCube() && func_176346_d(p_176341_1_.getBlockState(var4.offsetUp()))) ? EnumAttachPosition.UP : EnumAttachPosition.NONE;
/*     */     } 
/*     */ 
/*     */     
/*  72 */     return EnumAttachPosition.SIDE;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public AxisAlignedBB getCollisionBoundingBox(World worldIn, BlockPos pos, IBlockState state) {
/*  78 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isOpaqueCube() {
/*  83 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isFullCube() {
/*  88 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public int colorMultiplier(IBlockAccess worldIn, BlockPos pos, int renderPass) {
/*  93 */     IBlockState var4 = worldIn.getBlockState(pos);
/*  94 */     return (var4.getBlock() != this) ? super.colorMultiplier(worldIn, pos, renderPass) : func_176337_b(((Integer)var4.getValue((IProperty)POWER)).intValue());
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canPlaceBlockAt(World worldIn, BlockPos pos) {
/*  99 */     return !(!World.doesBlockHaveSolidTopSurface((IBlockAccess)worldIn, pos.offsetDown()) && worldIn.getBlockState(pos.offsetDown()).getBlock() != Blocks.glowstone);
/*     */   }
/*     */ 
/*     */   
/*     */   private IBlockState updateSurroundingRedstone(World worldIn, BlockPos p_176338_2_, IBlockState p_176338_3_) {
/* 104 */     p_176338_3_ = func_176345_a(worldIn, p_176338_2_, p_176338_2_, p_176338_3_);
/* 105 */     ArrayList var4 = Lists.newArrayList(this.field_150179_b);
/* 106 */     this.field_150179_b.clear();
/* 107 */     Iterator<BlockPos> var5 = var4.iterator();
/*     */     
/* 109 */     while (var5.hasNext()) {
/*     */       
/* 111 */       BlockPos var6 = var5.next();
/* 112 */       worldIn.notifyNeighborsOfStateChange(var6, this);
/*     */     } 
/*     */     
/* 115 */     return p_176338_3_;
/*     */   }
/*     */ 
/*     */   
/*     */   private IBlockState func_176345_a(World worldIn, BlockPos p_176345_2_, BlockPos p_176345_3_, IBlockState p_176345_4_) {
/* 120 */     IBlockState var5 = p_176345_4_;
/* 121 */     int var6 = ((Integer)p_176345_4_.getValue((IProperty)POWER)).intValue();
/* 122 */     byte var7 = 0;
/* 123 */     int var14 = func_176342_a(worldIn, p_176345_3_, var7);
/* 124 */     this.canProvidePower = false;
/* 125 */     int var8 = worldIn.func_175687_A(p_176345_2_);
/* 126 */     this.canProvidePower = true;
/*     */     
/* 128 */     if (var8 > 0 && var8 > var14 - 1)
/*     */     {
/* 130 */       var14 = var8;
/*     */     }
/*     */     
/* 133 */     int var9 = 0;
/* 134 */     Iterator<EnumFacing> var10 = EnumFacing.Plane.HORIZONTAL.iterator();
/*     */     
/* 136 */     while (var10.hasNext()) {
/*     */       
/* 138 */       EnumFacing var11 = var10.next();
/* 139 */       BlockPos var12 = p_176345_2_.offset(var11);
/* 140 */       boolean var13 = !(var12.getX() == p_176345_3_.getX() && var12.getZ() == p_176345_3_.getZ());
/*     */       
/* 142 */       if (var13)
/*     */       {
/* 144 */         var9 = func_176342_a(worldIn, var12, var9);
/*     */       }
/*     */       
/* 147 */       if (worldIn.getBlockState(var12).getBlock().isNormalCube() && !worldIn.getBlockState(p_176345_2_.offsetUp()).getBlock().isNormalCube()) {
/*     */         
/* 149 */         if (var13 && p_176345_2_.getY() >= p_176345_3_.getY())
/*     */         {
/* 151 */           var9 = func_176342_a(worldIn, var12.offsetUp(), var9); } 
/*     */         continue;
/*     */       } 
/* 154 */       if (!worldIn.getBlockState(var12).getBlock().isNormalCube() && var13 && p_176345_2_.getY() <= p_176345_3_.getY())
/*     */       {
/* 156 */         var9 = func_176342_a(worldIn, var12.offsetDown(), var9);
/*     */       }
/*     */     } 
/*     */     
/* 160 */     if (var9 > var14) {
/*     */       
/* 162 */       var14 = var9 - 1;
/*     */     }
/* 164 */     else if (var14 > 0) {
/*     */       
/* 166 */       var14--;
/*     */     }
/*     */     else {
/*     */       
/* 170 */       var14 = 0;
/*     */     } 
/*     */     
/* 173 */     if (var8 > var14 - 1)
/*     */     {
/* 175 */       var14 = var8;
/*     */     }
/*     */     
/* 178 */     if (var6 != var14) {
/*     */       
/* 180 */       p_176345_4_ = p_176345_4_.withProperty((IProperty)POWER, Integer.valueOf(var14));
/*     */       
/* 182 */       if (worldIn.getBlockState(p_176345_2_) == var5)
/*     */       {
/* 184 */         worldIn.setBlockState(p_176345_2_, p_176345_4_, 2);
/*     */       }
/*     */       
/* 187 */       this.field_150179_b.add(p_176345_2_);
/* 188 */       EnumFacing[] var15 = EnumFacing.values();
/* 189 */       int var16 = var15.length;
/*     */       
/* 191 */       for (int var17 = 0; var17 < var16; var17++) {
/*     */         
/* 193 */         EnumFacing var18 = var15[var17];
/* 194 */         this.field_150179_b.add(p_176345_2_.offset(var18));
/*     */       } 
/*     */     } 
/*     */     
/* 198 */     return p_176345_4_;
/*     */   }
/*     */ 
/*     */   
/*     */   private void func_176344_d(World worldIn, BlockPos p_176344_2_) {
/* 203 */     if (worldIn.getBlockState(p_176344_2_).getBlock() == this) {
/*     */       
/* 205 */       worldIn.notifyNeighborsOfStateChange(p_176344_2_, this);
/* 206 */       EnumFacing[] var3 = EnumFacing.values();
/* 207 */       int var4 = var3.length;
/*     */       
/* 209 */       for (int var5 = 0; var5 < var4; var5++) {
/*     */         
/* 211 */         EnumFacing var6 = var3[var5];
/* 212 */         worldIn.notifyNeighborsOfStateChange(p_176344_2_.offset(var6), this);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state) {
/* 219 */     if (!worldIn.isRemote) {
/*     */       
/* 221 */       updateSurroundingRedstone(worldIn, pos, state);
/* 222 */       Iterator<EnumFacing> var4 = EnumFacing.Plane.VERTICAL.iterator();
/*     */ 
/*     */       
/* 225 */       while (var4.hasNext()) {
/*     */         
/* 227 */         EnumFacing var5 = var4.next();
/* 228 */         worldIn.notifyNeighborsOfStateChange(pos.offset(var5), this);
/*     */       } 
/*     */       
/* 231 */       var4 = EnumFacing.Plane.HORIZONTAL.iterator();
/*     */       
/* 233 */       while (var4.hasNext()) {
/*     */         
/* 235 */         EnumFacing var5 = var4.next();
/* 236 */         func_176344_d(worldIn, pos.offset(var5));
/*     */       } 
/*     */       
/* 239 */       var4 = EnumFacing.Plane.HORIZONTAL.iterator();
/*     */       
/* 241 */       while (var4.hasNext()) {
/*     */         
/* 243 */         EnumFacing var5 = var4.next();
/* 244 */         BlockPos var6 = pos.offset(var5);
/*     */         
/* 246 */         if (worldIn.getBlockState(var6).getBlock().isNormalCube()) {
/*     */           
/* 248 */           func_176344_d(worldIn, var6.offsetUp());
/*     */           
/*     */           continue;
/*     */         } 
/* 252 */         func_176344_d(worldIn, var6.offsetDown());
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
/* 260 */     super.breakBlock(worldIn, pos, state);
/*     */     
/* 262 */     if (!worldIn.isRemote) {
/*     */       
/* 264 */       EnumFacing[] var4 = EnumFacing.values();
/* 265 */       int var5 = var4.length;
/*     */       
/* 267 */       for (int var6 = 0; var6 < var5; var6++) {
/*     */         
/* 269 */         EnumFacing var7 = var4[var6];
/* 270 */         worldIn.notifyNeighborsOfStateChange(pos.offset(var7), this);
/*     */       } 
/*     */       
/* 273 */       updateSurroundingRedstone(worldIn, pos, state);
/* 274 */       Iterator<EnumFacing> var8 = EnumFacing.Plane.HORIZONTAL.iterator();
/*     */ 
/*     */       
/* 277 */       while (var8.hasNext()) {
/*     */         
/* 279 */         EnumFacing var9 = var8.next();
/* 280 */         func_176344_d(worldIn, pos.offset(var9));
/*     */       } 
/*     */       
/* 283 */       var8 = EnumFacing.Plane.HORIZONTAL.iterator();
/*     */       
/* 285 */       while (var8.hasNext()) {
/*     */         
/* 287 */         EnumFacing var9 = var8.next();
/* 288 */         BlockPos var10 = pos.offset(var9);
/*     */         
/* 290 */         if (worldIn.getBlockState(var10).getBlock().isNormalCube()) {
/*     */           
/* 292 */           func_176344_d(worldIn, var10.offsetUp());
/*     */           
/*     */           continue;
/*     */         } 
/* 296 */         func_176344_d(worldIn, var10.offsetDown());
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private int func_176342_a(World worldIn, BlockPos p_176342_2_, int p_176342_3_) {
/* 304 */     if (worldIn.getBlockState(p_176342_2_).getBlock() != this)
/*     */     {
/* 306 */       return p_176342_3_;
/*     */     }
/*     */ 
/*     */     
/* 310 */     int var4 = ((Integer)worldIn.getBlockState(p_176342_2_).getValue((IProperty)POWER)).intValue();
/* 311 */     return (var4 > p_176342_3_) ? var4 : p_176342_3_;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void onNeighborBlockChange(World worldIn, BlockPos pos, IBlockState state, Block neighborBlock) {
/* 317 */     if (!worldIn.isRemote)
/*     */     {
/* 319 */       if (canPlaceBlockAt(worldIn, pos)) {
/*     */         
/* 321 */         updateSurroundingRedstone(worldIn, pos, state);
/*     */       }
/*     */       else {
/*     */         
/* 325 */         dropBlockAsItem(worldIn, pos, state, 0);
/* 326 */         worldIn.setBlockToAir(pos);
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
/* 338 */     return Items.redstone;
/*     */   }
/*     */ 
/*     */   
/*     */   public int isProvidingStrongPower(IBlockAccess worldIn, BlockPos pos, IBlockState state, EnumFacing side) {
/* 343 */     return !this.canProvidePower ? 0 : isProvidingWeakPower(worldIn, pos, state, side);
/*     */   }
/*     */ 
/*     */   
/*     */   public int isProvidingWeakPower(IBlockAccess worldIn, BlockPos pos, IBlockState state, EnumFacing side) {
/* 348 */     if (!this.canProvidePower)
/*     */     {
/* 350 */       return 0;
/*     */     }
/*     */ 
/*     */     
/* 354 */     int var5 = ((Integer)state.getValue((IProperty)POWER)).intValue();
/*     */     
/* 356 */     if (var5 == 0)
/*     */     {
/* 358 */       return 0;
/*     */     }
/* 360 */     if (side == EnumFacing.UP)
/*     */     {
/* 362 */       return var5;
/*     */     }
/*     */ 
/*     */     
/* 366 */     EnumSet<EnumFacing> var6 = EnumSet.noneOf(EnumFacing.class);
/* 367 */     Iterator<EnumFacing> var7 = EnumFacing.Plane.HORIZONTAL.iterator();
/*     */     
/* 369 */     while (var7.hasNext()) {
/*     */       
/* 371 */       EnumFacing var8 = var7.next();
/*     */       
/* 373 */       if (func_176339_d(worldIn, pos, var8))
/*     */       {
/* 375 */         var6.add(var8);
/*     */       }
/*     */     } 
/*     */     
/* 379 */     if (side.getAxis().isHorizontal() && var6.isEmpty())
/*     */     {
/* 381 */       return var5;
/*     */     }
/* 383 */     if (var6.contains(side) && !var6.contains(side.rotateYCCW()) && !var6.contains(side.rotateY()))
/*     */     {
/* 385 */       return var5;
/*     */     }
/*     */ 
/*     */     
/* 389 */     return 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean func_176339_d(IBlockAccess p_176339_1_, BlockPos p_176339_2_, EnumFacing p_176339_3_) {
/* 397 */     BlockPos var4 = p_176339_2_.offset(p_176339_3_);
/* 398 */     IBlockState var5 = p_176339_1_.getBlockState(var4);
/* 399 */     Block var6 = var5.getBlock();
/* 400 */     boolean var7 = var6.isNormalCube();
/* 401 */     boolean var8 = p_176339_1_.getBlockState(p_176339_2_.offsetUp()).getBlock().isNormalCube();
/* 402 */     return (!var8 && var7 && func_176340_e(p_176339_1_, var4.offsetUp())) ? true : (func_176343_a(var5, p_176339_3_) ? true : ((var6 == Blocks.powered_repeater && var5.getValue((IProperty)BlockRedstoneDiode.AGE) == p_176339_3_) ? true : ((!var7 && func_176340_e(p_176339_1_, var4.offsetDown())))));
/*     */   }
/*     */ 
/*     */   
/*     */   protected static boolean func_176340_e(IBlockAccess p_176340_0_, BlockPos p_176340_1_) {
/* 407 */     return func_176346_d(p_176340_0_.getBlockState(p_176340_1_));
/*     */   }
/*     */ 
/*     */   
/*     */   protected static boolean func_176346_d(IBlockState p_176346_0_) {
/* 412 */     return func_176343_a(p_176346_0_, (EnumFacing)null);
/*     */   }
/*     */ 
/*     */   
/*     */   protected static boolean func_176343_a(IBlockState p_176343_0_, EnumFacing p_176343_1_) {
/* 417 */     Block var2 = p_176343_0_.getBlock();
/*     */     
/* 419 */     if (var2 == Blocks.redstone_wire)
/*     */     {
/* 421 */       return true;
/*     */     }
/* 423 */     if (Blocks.unpowered_repeater.func_149907_e(var2)) {
/*     */       
/* 425 */       EnumFacing var3 = (EnumFacing)p_176343_0_.getValue((IProperty)BlockRedstoneRepeater.AGE);
/* 426 */       return !(var3 != p_176343_1_ && var3.getOpposite() != p_176343_1_);
/*     */     } 
/*     */ 
/*     */     
/* 430 */     return (var2.canProvidePower() && p_176343_1_ != null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canProvidePower() {
/* 439 */     return this.canProvidePower;
/*     */   }
/*     */ 
/*     */   
/*     */   private int func_176337_b(int p_176337_1_) {
/* 444 */     float var2 = p_176337_1_ / 15.0F;
/* 445 */     float var3 = var2 * 0.6F + 0.4F;
/*     */     
/* 447 */     if (p_176337_1_ == 0)
/*     */     {
/* 449 */       var3 = 0.3F;
/*     */     }
/*     */     
/* 452 */     float var4 = var2 * var2 * 0.7F - 0.5F;
/* 453 */     float var5 = var2 * var2 * 0.6F - 0.7F;
/*     */     
/* 455 */     if (var4 < 0.0F)
/*     */     {
/* 457 */       var4 = 0.0F;
/*     */     }
/*     */     
/* 460 */     if (var5 < 0.0F)
/*     */     {
/* 462 */       var5 = 0.0F;
/*     */     }
/*     */     
/* 465 */     int var6 = MathHelper.clamp_int((int)(var3 * 255.0F), 0, 255);
/* 466 */     int var7 = MathHelper.clamp_int((int)(var4 * 255.0F), 0, 255);
/* 467 */     int var8 = MathHelper.clamp_int((int)(var5 * 255.0F), 0, 255);
/* 468 */     return 0xFF000000 | var6 << 16 | var7 << 8 | var8;
/*     */   }
/*     */ 
/*     */   
/*     */   public void randomDisplayTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
/* 473 */     int var5 = ((Integer)state.getValue((IProperty)POWER)).intValue();
/*     */     
/* 475 */     if (var5 != 0) {
/*     */       
/* 477 */       double var6 = pos.getX() + 0.5D + (rand.nextFloat() - 0.5D) * 0.2D;
/* 478 */       double var8 = (pos.getY() + 0.0625F);
/* 479 */       double var10 = pos.getZ() + 0.5D + (rand.nextFloat() - 0.5D) * 0.2D;
/* 480 */       float var12 = var5 / 15.0F;
/* 481 */       float var13 = var12 * 0.6F + 0.4F;
/* 482 */       float var14 = Math.max(0.0F, var12 * var12 * 0.7F - 0.5F);
/* 483 */       float var15 = Math.max(0.0F, var12 * var12 * 0.6F - 0.7F);
/* 484 */       worldIn.spawnParticle(EnumParticleTypes.REDSTONE, var6, var8, var10, var13, var14, var15, new int[0]);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public Item getItem(World worldIn, BlockPos pos) {
/* 490 */     return Items.redstone;
/*     */   }
/*     */ 
/*     */   
/*     */   public EnumWorldBlockLayer getBlockLayer() {
/* 495 */     return EnumWorldBlockLayer.CUTOUT;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IBlockState getStateFromMeta(int meta) {
/* 503 */     return getDefaultState().withProperty((IProperty)POWER, Integer.valueOf(meta));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getMetaFromState(IBlockState state) {
/* 511 */     return ((Integer)state.getValue((IProperty)POWER)).intValue();
/*     */   }
/*     */ 
/*     */   
/*     */   protected BlockState createBlockState() {
/* 516 */     return new BlockState(this, new IProperty[] { (IProperty)NORTH, (IProperty)EAST, (IProperty)SOUTH, (IProperty)WEST, (IProperty)POWER });
/*     */   }
/*     */   
/*     */   enum EnumAttachPosition
/*     */     implements IStringSerializable {
/* 521 */     UP("UP", 0, "up"),
/* 522 */     SIDE("SIDE", 1, "side"),
/* 523 */     NONE("NONE", 2, "none");
/*     */     
/*     */     private final String field_176820_d;
/* 526 */     private static final EnumAttachPosition[] $VALUES = new EnumAttachPosition[] { UP, SIDE, NONE };
/*     */     
/*     */     private static final String __OBFID = "CL_00002070";
/*     */     
/*     */     EnumAttachPosition(String p_i45689_1_, int p_i45689_2_, String p_i45689_3_) {
/* 531 */       this.field_176820_d = p_i45689_3_;
/*     */     } static {
/*     */     
/*     */     }
/*     */     public String toString() {
/* 536 */       return getName();
/*     */     }
/*     */ 
/*     */     
/*     */     public String getName() {
/* 541 */       return this.field_176820_d;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\block\BlockRedstoneWire.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */