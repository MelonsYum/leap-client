/*     */ package net.minecraft.block;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.block.properties.IProperty;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.creativetab.CreativeTabs;
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
/*     */ public abstract class BlockRailBase
/*     */   extends Block
/*     */ {
/*     */   protected final boolean isPowered;
/*     */   private static final String __OBFID = "CL_00000195";
/*     */   
/*     */   public static boolean func_176562_d(World worldIn, BlockPos p_176562_1_) {
/*  28 */     return func_176563_d(worldIn.getBlockState(p_176562_1_));
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean func_176563_d(IBlockState p_176563_0_) {
/*  33 */     Block var1 = p_176563_0_.getBlock();
/*  34 */     return !(var1 != Blocks.rail && var1 != Blocks.golden_rail && var1 != Blocks.detector_rail && var1 != Blocks.activator_rail);
/*     */   }
/*     */ 
/*     */   
/*     */   protected BlockRailBase(boolean p_i45389_1_) {
/*  39 */     super(Material.circuits);
/*  40 */     this.isPowered = p_i45389_1_;
/*  41 */     setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.125F, 1.0F);
/*  42 */     setCreativeTab(CreativeTabs.tabTransport);
/*     */   }
/*     */ 
/*     */   
/*     */   public AxisAlignedBB getCollisionBoundingBox(World worldIn, BlockPos pos, IBlockState state) {
/*  47 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isOpaqueCube() {
/*  52 */     return false;
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
/*  63 */     setBlockBoundsBasedOnState((IBlockAccess)worldIn, pos);
/*  64 */     return super.collisionRayTrace(worldIn, pos, start, end);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setBlockBoundsBasedOnState(IBlockAccess access, BlockPos pos) {
/*  69 */     IBlockState var3 = access.getBlockState(pos);
/*  70 */     EnumRailDirection var4 = (var3.getBlock() == this) ? (EnumRailDirection)var3.getValue(func_176560_l()) : null;
/*     */     
/*  72 */     if (var4 != null && var4.func_177018_c()) {
/*     */       
/*  74 */       setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.625F, 1.0F);
/*     */     }
/*     */     else {
/*     */       
/*  78 */       setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.125F, 1.0F);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isFullCube() {
/*  84 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canPlaceBlockAt(World worldIn, BlockPos pos) {
/*  89 */     return World.doesBlockHaveSolidTopSurface((IBlockAccess)worldIn, pos.offsetDown());
/*     */   }
/*     */ 
/*     */   
/*     */   public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state) {
/*  94 */     if (!worldIn.isRemote) {
/*     */       
/*  96 */       state = func_176564_a(worldIn, pos, state, true);
/*     */       
/*  98 */       if (this.isPowered)
/*     */       {
/* 100 */         onNeighborBlockChange(worldIn, pos, state, this);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void onNeighborBlockChange(World worldIn, BlockPos pos, IBlockState state, Block neighborBlock) {
/* 107 */     if (!worldIn.isRemote) {
/*     */       
/* 109 */       EnumRailDirection var5 = (EnumRailDirection)state.getValue(func_176560_l());
/* 110 */       boolean var6 = false;
/*     */       
/* 112 */       if (!World.doesBlockHaveSolidTopSurface((IBlockAccess)worldIn, pos.offsetDown()))
/*     */       {
/* 114 */         var6 = true;
/*     */       }
/*     */       
/* 117 */       if (var5 == EnumRailDirection.ASCENDING_EAST && !World.doesBlockHaveSolidTopSurface((IBlockAccess)worldIn, pos.offsetEast())) {
/*     */         
/* 119 */         var6 = true;
/*     */       }
/* 121 */       else if (var5 == EnumRailDirection.ASCENDING_WEST && !World.doesBlockHaveSolidTopSurface((IBlockAccess)worldIn, pos.offsetWest())) {
/*     */         
/* 123 */         var6 = true;
/*     */       }
/* 125 */       else if (var5 == EnumRailDirection.ASCENDING_NORTH && !World.doesBlockHaveSolidTopSurface((IBlockAccess)worldIn, pos.offsetNorth())) {
/*     */         
/* 127 */         var6 = true;
/*     */       }
/* 129 */       else if (var5 == EnumRailDirection.ASCENDING_SOUTH && !World.doesBlockHaveSolidTopSurface((IBlockAccess)worldIn, pos.offsetSouth())) {
/*     */         
/* 131 */         var6 = true;
/*     */       } 
/*     */       
/* 134 */       if (var6) {
/*     */         
/* 136 */         dropBlockAsItem(worldIn, pos, state, 0);
/* 137 */         worldIn.setBlockToAir(pos);
/*     */       }
/*     */       else {
/*     */         
/* 141 */         func_176561_b(worldIn, pos, state, neighborBlock);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void func_176561_b(World worldIn, BlockPos p_176561_2_, IBlockState p_176561_3_, Block p_176561_4_) {}
/*     */   
/*     */   protected IBlockState func_176564_a(World worldIn, BlockPos p_176564_2_, IBlockState p_176564_3_, boolean p_176564_4_) {
/* 150 */     return worldIn.isRemote ? p_176564_3_ : (new Rail(worldIn, p_176564_2_, p_176564_3_)).func_180364_a(worldIn.isBlockPowered(p_176564_2_), p_176564_4_).func_180362_b();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getMobilityFlag() {
/* 155 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public EnumWorldBlockLayer getBlockLayer() {
/* 160 */     return EnumWorldBlockLayer.CUTOUT;
/*     */   }
/*     */ 
/*     */   
/*     */   public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
/* 165 */     super.breakBlock(worldIn, pos, state);
/*     */     
/* 167 */     if (((EnumRailDirection)state.getValue(func_176560_l())).func_177018_c())
/*     */     {
/* 169 */       worldIn.notifyNeighborsOfStateChange(pos.offsetUp(), this);
/*     */     }
/*     */     
/* 172 */     if (this.isPowered) {
/*     */       
/* 174 */       worldIn.notifyNeighborsOfStateChange(pos, this);
/* 175 */       worldIn.notifyNeighborsOfStateChange(pos.offsetDown(), this);
/*     */     } 
/*     */   }
/*     */   
/*     */   public abstract IProperty func_176560_l();
/*     */   
/*     */   public enum EnumRailDirection
/*     */     implements IStringSerializable {
/* 183 */     NORTH_SOUTH("NORTH_SOUTH", 0, 0, "north_south"),
/* 184 */     EAST_WEST("EAST_WEST", 1, 1, "east_west"),
/* 185 */     ASCENDING_EAST("ASCENDING_EAST", 2, 2, "ascending_east"),
/* 186 */     ASCENDING_WEST("ASCENDING_WEST", 3, 3, "ascending_west"),
/* 187 */     ASCENDING_NORTH("ASCENDING_NORTH", 4, 4, "ascending_north"),
/* 188 */     ASCENDING_SOUTH("ASCENDING_SOUTH", 5, 5, "ascending_south"),
/* 189 */     SOUTH_EAST("SOUTH_EAST", 6, 6, "south_east"),
/* 190 */     SOUTH_WEST("SOUTH_WEST", 7, 7, "south_west"),
/* 191 */     NORTH_WEST("NORTH_WEST", 8, 8, "north_west"),
/* 192 */     NORTH_EAST("NORTH_EAST", 9, 9, "north_east");
/* 193 */     private static final EnumRailDirection[] field_177030_k = new EnumRailDirection[(values()).length];
/*     */     
/*     */     private final int field_177027_l;
/*     */     private final String field_177028_m;
/* 197 */     private static final EnumRailDirection[] $VALUES = new EnumRailDirection[] { NORTH_SOUTH, EAST_WEST, ASCENDING_EAST, ASCENDING_WEST, ASCENDING_NORTH, ASCENDING_SOUTH, SOUTH_EAST, SOUTH_WEST, NORTH_WEST, NORTH_EAST };
/*     */ 
/*     */     
/*     */     private static final String __OBFID = "CL_00002137";
/*     */ 
/*     */ 
/*     */     
/*     */     EnumRailDirection(String p_i45738_1_, int p_i45738_2_, int p_i45738_3_, String p_i45738_4_) {
/*     */       this.field_177027_l = p_i45738_3_;
/*     */       this.field_177028_m = p_i45738_4_;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public int func_177015_a() {
/*     */       return this.field_177027_l;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public String toString() {
/*     */       return this.field_177028_m;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public boolean func_177018_c() {
/*     */       return !(this != ASCENDING_NORTH && this != ASCENDING_EAST && this != ASCENDING_SOUTH && this != ASCENDING_WEST);
/*     */     }
/*     */ 
/*     */     
/*     */     public static EnumRailDirection func_177016_a(int p_177016_0_) {
/*     */       if (p_177016_0_ < 0 || p_177016_0_ >= field_177030_k.length) {
/*     */         p_177016_0_ = 0;
/*     */       }
/*     */       return field_177030_k[p_177016_0_];
/*     */     }
/*     */ 
/*     */     
/*     */     static {
/* 237 */       EnumRailDirection[] var0 = values();
/* 238 */       int var1 = var0.length;
/*     */       
/* 240 */       for (int var2 = 0; var2 < var1; var2++) {
/*     */         
/* 242 */         EnumRailDirection var3 = var0[var2];
/* 243 */         field_177030_k[var3.func_177015_a()] = var3;
/*     */       } 
/*     */     }
/*     */     
/*     */     public String getName() {
/*     */       return this.field_177028_m;
/*     */     }
/*     */   }
/*     */   
/*     */   public class Rail {
/*     */     private final World field_150660_b;
/*     */     private final BlockPos field_180367_c;
/* 255 */     private final List field_150657_g = Lists.newArrayList();
/*     */     private final BlockRailBase field_180365_d;
/*     */     private IBlockState field_180366_e;
/*     */     
/*     */     public Rail(World worldIn, BlockPos p_i45739_3_, IBlockState p_i45739_4_) {
/* 260 */       this.field_150660_b = worldIn;
/* 261 */       this.field_180367_c = p_i45739_3_;
/* 262 */       this.field_180366_e = p_i45739_4_;
/* 263 */       this.field_180365_d = (BlockRailBase)p_i45739_4_.getBlock();
/* 264 */       BlockRailBase.EnumRailDirection var5 = (BlockRailBase.EnumRailDirection)p_i45739_4_.getValue(BlockRailBase.this.func_176560_l());
/* 265 */       this.field_150656_f = this.field_180365_d.isPowered;
/* 266 */       func_180360_a(var5);
/*     */     }
/*     */     private final boolean field_150656_f; private static final String __OBFID = "CL_00000196";
/*     */     
/*     */     private void func_180360_a(BlockRailBase.EnumRailDirection p_180360_1_) {
/* 271 */       this.field_150657_g.clear();
/*     */       
/* 273 */       switch (BlockRailBase.SwitchEnumRailDirection.field_180371_a[p_180360_1_.ordinal()]) {
/*     */         
/*     */         case 1:
/* 276 */           this.field_150657_g.add(this.field_180367_c.offsetNorth());
/* 277 */           this.field_150657_g.add(this.field_180367_c.offsetSouth());
/*     */           break;
/*     */         
/*     */         case 2:
/* 281 */           this.field_150657_g.add(this.field_180367_c.offsetWest());
/* 282 */           this.field_150657_g.add(this.field_180367_c.offsetEast());
/*     */           break;
/*     */         
/*     */         case 3:
/* 286 */           this.field_150657_g.add(this.field_180367_c.offsetWest());
/* 287 */           this.field_150657_g.add(this.field_180367_c.offsetEast().offsetUp());
/*     */           break;
/*     */         
/*     */         case 4:
/* 291 */           this.field_150657_g.add(this.field_180367_c.offsetWest().offsetUp());
/* 292 */           this.field_150657_g.add(this.field_180367_c.offsetEast());
/*     */           break;
/*     */         
/*     */         case 5:
/* 296 */           this.field_150657_g.add(this.field_180367_c.offsetNorth().offsetUp());
/* 297 */           this.field_150657_g.add(this.field_180367_c.offsetSouth());
/*     */           break;
/*     */         
/*     */         case 6:
/* 301 */           this.field_150657_g.add(this.field_180367_c.offsetNorth());
/* 302 */           this.field_150657_g.add(this.field_180367_c.offsetSouth().offsetUp());
/*     */           break;
/*     */         
/*     */         case 7:
/* 306 */           this.field_150657_g.add(this.field_180367_c.offsetEast());
/* 307 */           this.field_150657_g.add(this.field_180367_c.offsetSouth());
/*     */           break;
/*     */         
/*     */         case 8:
/* 311 */           this.field_150657_g.add(this.field_180367_c.offsetWest());
/* 312 */           this.field_150657_g.add(this.field_180367_c.offsetSouth());
/*     */           break;
/*     */         
/*     */         case 9:
/* 316 */           this.field_150657_g.add(this.field_180367_c.offsetWest());
/* 317 */           this.field_150657_g.add(this.field_180367_c.offsetNorth());
/*     */           break;
/*     */         
/*     */         case 10:
/* 321 */           this.field_150657_g.add(this.field_180367_c.offsetEast());
/* 322 */           this.field_150657_g.add(this.field_180367_c.offsetNorth());
/*     */           break;
/*     */       } 
/*     */     }
/*     */     
/*     */     private void func_150651_b() {
/* 328 */       for (int var1 = 0; var1 < this.field_150657_g.size(); var1++) {
/*     */         
/* 330 */         Rail var2 = func_180697_b(this.field_150657_g.get(var1));
/*     */         
/* 332 */         if (var2 != null && var2.func_150653_a(this)) {
/*     */           
/* 334 */           this.field_150657_g.set(var1, var2.field_180367_c);
/*     */         }
/*     */         else {
/*     */           
/* 338 */           this.field_150657_g.remove(var1--);
/*     */         } 
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/*     */     private boolean func_180359_a(BlockPos p_180359_1_) {
/* 345 */       return !(!BlockRailBase.func_176562_d(this.field_150660_b, p_180359_1_) && !BlockRailBase.func_176562_d(this.field_150660_b, p_180359_1_.offsetUp()) && !BlockRailBase.func_176562_d(this.field_150660_b, p_180359_1_.offsetDown()));
/*     */     }
/*     */ 
/*     */     
/*     */     private Rail func_180697_b(BlockPos p_180697_1_) {
/* 350 */       IBlockState var3 = this.field_150660_b.getBlockState(p_180697_1_);
/*     */       
/* 352 */       if (BlockRailBase.func_176563_d(var3)) {
/*     */         
/* 354 */         BlockRailBase.this.getClass(); return new Rail(this.field_150660_b, p_180697_1_, var3);
/*     */       } 
/*     */ 
/*     */       
/* 358 */       BlockPos var2 = p_180697_1_.offsetUp();
/* 359 */       var3 = this.field_150660_b.getBlockState(var2);
/*     */       
/* 361 */       if (BlockRailBase.func_176563_d(var3)) {
/*     */         
/* 363 */         BlockRailBase.this.getClass(); return new Rail(this.field_150660_b, var2, var3);
/*     */       } 
/*     */ 
/*     */       
/* 367 */       var2 = p_180697_1_.offsetDown();
/* 368 */       var3 = this.field_150660_b.getBlockState(var2);
/* 369 */       BlockRailBase.this.getClass(); return BlockRailBase.func_176563_d(var3) ? new Rail(this.field_150660_b, var2, var3) : null;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     private boolean func_150653_a(Rail p_150653_1_) {
/* 376 */       return func_180363_c(p_150653_1_.field_180367_c);
/*     */     }
/*     */ 
/*     */     
/*     */     private boolean func_180363_c(BlockPos p_180363_1_) {
/* 381 */       for (int var2 = 0; var2 < this.field_150657_g.size(); var2++) {
/*     */         
/* 383 */         BlockPos var3 = this.field_150657_g.get(var2);
/*     */         
/* 385 */         if (var3.getX() == p_180363_1_.getX() && var3.getZ() == p_180363_1_.getZ())
/*     */         {
/* 387 */           return true;
/*     */         }
/*     */       } 
/*     */       
/* 391 */       return false;
/*     */     }
/*     */ 
/*     */     
/*     */     protected int countAdjacentRails() {
/* 396 */       int var1 = 0;
/* 397 */       Iterator<EnumFacing> var2 = EnumFacing.Plane.HORIZONTAL.iterator();
/*     */       
/* 399 */       while (var2.hasNext()) {
/*     */         
/* 401 */         EnumFacing var3 = var2.next();
/*     */         
/* 403 */         if (func_180359_a(this.field_180367_c.offset(var3)))
/*     */         {
/* 405 */           var1++;
/*     */         }
/*     */       } 
/*     */       
/* 409 */       return var1;
/*     */     }
/*     */ 
/*     */     
/*     */     private boolean func_150649_b(Rail p_150649_1_) {
/* 414 */       return !(!func_150653_a(p_150649_1_) && this.field_150657_g.size() == 2);
/*     */     }
/*     */ 
/*     */     
/*     */     private void func_150645_c(Rail p_150645_1_) {
/* 419 */       this.field_150657_g.add(p_150645_1_.field_180367_c);
/* 420 */       BlockPos var2 = this.field_180367_c.offsetNorth();
/* 421 */       BlockPos var3 = this.field_180367_c.offsetSouth();
/* 422 */       BlockPos var4 = this.field_180367_c.offsetWest();
/* 423 */       BlockPos var5 = this.field_180367_c.offsetEast();
/* 424 */       boolean var6 = func_180363_c(var2);
/* 425 */       boolean var7 = func_180363_c(var3);
/* 426 */       boolean var8 = func_180363_c(var4);
/* 427 */       boolean var9 = func_180363_c(var5);
/* 428 */       BlockRailBase.EnumRailDirection var10 = null;
/*     */       
/* 430 */       if (var6 || var7)
/*     */       {
/* 432 */         var10 = BlockRailBase.EnumRailDirection.NORTH_SOUTH;
/*     */       }
/*     */       
/* 435 */       if (var8 || var9)
/*     */       {
/* 437 */         var10 = BlockRailBase.EnumRailDirection.EAST_WEST;
/*     */       }
/*     */       
/* 440 */       if (!this.field_150656_f) {
/*     */         
/* 442 */         if (var7 && var9 && !var6 && !var8)
/*     */         {
/* 444 */           var10 = BlockRailBase.EnumRailDirection.SOUTH_EAST;
/*     */         }
/*     */         
/* 447 */         if (var7 && var8 && !var6 && !var9)
/*     */         {
/* 449 */           var10 = BlockRailBase.EnumRailDirection.SOUTH_WEST;
/*     */         }
/*     */         
/* 452 */         if (var6 && var8 && !var7 && !var9)
/*     */         {
/* 454 */           var10 = BlockRailBase.EnumRailDirection.NORTH_WEST;
/*     */         }
/*     */         
/* 457 */         if (var6 && var9 && !var7 && !var8)
/*     */         {
/* 459 */           var10 = BlockRailBase.EnumRailDirection.NORTH_EAST;
/*     */         }
/*     */       } 
/*     */       
/* 463 */       if (var10 == BlockRailBase.EnumRailDirection.NORTH_SOUTH) {
/*     */         
/* 465 */         if (BlockRailBase.func_176562_d(this.field_150660_b, var2.offsetUp()))
/*     */         {
/* 467 */           var10 = BlockRailBase.EnumRailDirection.ASCENDING_NORTH;
/*     */         }
/*     */         
/* 470 */         if (BlockRailBase.func_176562_d(this.field_150660_b, var3.offsetUp()))
/*     */         {
/* 472 */           var10 = BlockRailBase.EnumRailDirection.ASCENDING_SOUTH;
/*     */         }
/*     */       } 
/*     */       
/* 476 */       if (var10 == BlockRailBase.EnumRailDirection.EAST_WEST) {
/*     */         
/* 478 */         if (BlockRailBase.func_176562_d(this.field_150660_b, var5.offsetUp()))
/*     */         {
/* 480 */           var10 = BlockRailBase.EnumRailDirection.ASCENDING_EAST;
/*     */         }
/*     */         
/* 483 */         if (BlockRailBase.func_176562_d(this.field_150660_b, var4.offsetUp()))
/*     */         {
/* 485 */           var10 = BlockRailBase.EnumRailDirection.ASCENDING_WEST;
/*     */         }
/*     */       } 
/*     */       
/* 489 */       if (var10 == null)
/*     */       {
/* 491 */         var10 = BlockRailBase.EnumRailDirection.NORTH_SOUTH;
/*     */       }
/*     */       
/* 494 */       this.field_180366_e = this.field_180366_e.withProperty(this.field_180365_d.func_176560_l(), var10);
/* 495 */       this.field_150660_b.setBlockState(this.field_180367_c, this.field_180366_e, 3);
/*     */     }
/*     */ 
/*     */     
/*     */     private boolean func_180361_d(BlockPos p_180361_1_) {
/* 500 */       Rail var2 = func_180697_b(p_180361_1_);
/*     */       
/* 502 */       if (var2 == null)
/*     */       {
/* 504 */         return false;
/*     */       }
/*     */ 
/*     */       
/* 508 */       var2.func_150651_b();
/* 509 */       return var2.func_150649_b(this);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public Rail func_180364_a(boolean p_180364_1_, boolean p_180364_2_) {
/* 515 */       BlockPos var3 = this.field_180367_c.offsetNorth();
/* 516 */       BlockPos var4 = this.field_180367_c.offsetSouth();
/* 517 */       BlockPos var5 = this.field_180367_c.offsetWest();
/* 518 */       BlockPos var6 = this.field_180367_c.offsetEast();
/* 519 */       boolean var7 = func_180361_d(var3);
/* 520 */       boolean var8 = func_180361_d(var4);
/* 521 */       boolean var9 = func_180361_d(var5);
/* 522 */       boolean var10 = func_180361_d(var6);
/* 523 */       BlockRailBase.EnumRailDirection var11 = null;
/*     */       
/* 525 */       if ((var7 || var8) && !var9 && !var10)
/*     */       {
/* 527 */         var11 = BlockRailBase.EnumRailDirection.NORTH_SOUTH;
/*     */       }
/*     */       
/* 530 */       if ((var9 || var10) && !var7 && !var8)
/*     */       {
/* 532 */         var11 = BlockRailBase.EnumRailDirection.EAST_WEST;
/*     */       }
/*     */       
/* 535 */       if (!this.field_150656_f) {
/*     */         
/* 537 */         if (var8 && var10 && !var7 && !var9)
/*     */         {
/* 539 */           var11 = BlockRailBase.EnumRailDirection.SOUTH_EAST;
/*     */         }
/*     */         
/* 542 */         if (var8 && var9 && !var7 && !var10)
/*     */         {
/* 544 */           var11 = BlockRailBase.EnumRailDirection.SOUTH_WEST;
/*     */         }
/*     */         
/* 547 */         if (var7 && var9 && !var8 && !var10)
/*     */         {
/* 549 */           var11 = BlockRailBase.EnumRailDirection.NORTH_WEST;
/*     */         }
/*     */         
/* 552 */         if (var7 && var10 && !var8 && !var9)
/*     */         {
/* 554 */           var11 = BlockRailBase.EnumRailDirection.NORTH_EAST;
/*     */         }
/*     */       } 
/*     */       
/* 558 */       if (var11 == null) {
/*     */         
/* 560 */         if (var7 || var8)
/*     */         {
/* 562 */           var11 = BlockRailBase.EnumRailDirection.NORTH_SOUTH;
/*     */         }
/*     */         
/* 565 */         if (var9 || var10)
/*     */         {
/* 567 */           var11 = BlockRailBase.EnumRailDirection.EAST_WEST;
/*     */         }
/*     */         
/* 570 */         if (!this.field_150656_f)
/*     */         {
/* 572 */           if (p_180364_1_) {
/*     */             
/* 574 */             if (var8 && var10)
/*     */             {
/* 576 */               var11 = BlockRailBase.EnumRailDirection.SOUTH_EAST;
/*     */             }
/*     */             
/* 579 */             if (var9 && var8)
/*     */             {
/* 581 */               var11 = BlockRailBase.EnumRailDirection.SOUTH_WEST;
/*     */             }
/*     */             
/* 584 */             if (var10 && var7)
/*     */             {
/* 586 */               var11 = BlockRailBase.EnumRailDirection.NORTH_EAST;
/*     */             }
/*     */             
/* 589 */             if (var7 && var9)
/*     */             {
/* 591 */               var11 = BlockRailBase.EnumRailDirection.NORTH_WEST;
/*     */             }
/*     */           }
/*     */           else {
/*     */             
/* 596 */             if (var7 && var9)
/*     */             {
/* 598 */               var11 = BlockRailBase.EnumRailDirection.NORTH_WEST;
/*     */             }
/*     */             
/* 601 */             if (var10 && var7)
/*     */             {
/* 603 */               var11 = BlockRailBase.EnumRailDirection.NORTH_EAST;
/*     */             }
/*     */             
/* 606 */             if (var9 && var8)
/*     */             {
/* 608 */               var11 = BlockRailBase.EnumRailDirection.SOUTH_WEST;
/*     */             }
/*     */             
/* 611 */             if (var8 && var10)
/*     */             {
/* 613 */               var11 = BlockRailBase.EnumRailDirection.SOUTH_EAST;
/*     */             }
/*     */           } 
/*     */         }
/*     */       } 
/*     */       
/* 619 */       if (var11 == BlockRailBase.EnumRailDirection.NORTH_SOUTH) {
/*     */         
/* 621 */         if (BlockRailBase.func_176562_d(this.field_150660_b, var3.offsetUp()))
/*     */         {
/* 623 */           var11 = BlockRailBase.EnumRailDirection.ASCENDING_NORTH;
/*     */         }
/*     */         
/* 626 */         if (BlockRailBase.func_176562_d(this.field_150660_b, var4.offsetUp()))
/*     */         {
/* 628 */           var11 = BlockRailBase.EnumRailDirection.ASCENDING_SOUTH;
/*     */         }
/*     */       } 
/*     */       
/* 632 */       if (var11 == BlockRailBase.EnumRailDirection.EAST_WEST) {
/*     */         
/* 634 */         if (BlockRailBase.func_176562_d(this.field_150660_b, var6.offsetUp()))
/*     */         {
/* 636 */           var11 = BlockRailBase.EnumRailDirection.ASCENDING_EAST;
/*     */         }
/*     */         
/* 639 */         if (BlockRailBase.func_176562_d(this.field_150660_b, var5.offsetUp()))
/*     */         {
/* 641 */           var11 = BlockRailBase.EnumRailDirection.ASCENDING_WEST;
/*     */         }
/*     */       } 
/*     */       
/* 645 */       if (var11 == null)
/*     */       {
/* 647 */         var11 = BlockRailBase.EnumRailDirection.NORTH_SOUTH;
/*     */       }
/*     */       
/* 650 */       func_180360_a(var11);
/* 651 */       this.field_180366_e = this.field_180366_e.withProperty(this.field_180365_d.func_176560_l(), var11);
/*     */       
/* 653 */       if (p_180364_2_ || this.field_150660_b.getBlockState(this.field_180367_c) != this.field_180366_e) {
/*     */         
/* 655 */         this.field_150660_b.setBlockState(this.field_180367_c, this.field_180366_e, 3);
/*     */         
/* 657 */         for (int var12 = 0; var12 < this.field_150657_g.size(); var12++) {
/*     */           
/* 659 */           Rail var13 = func_180697_b(this.field_150657_g.get(var12));
/*     */           
/* 661 */           if (var13 != null) {
/*     */             
/* 663 */             var13.func_150651_b();
/*     */             
/* 665 */             if (var13.func_150649_b(this))
/*     */             {
/* 667 */               var13.func_150645_c(this);
/*     */             }
/*     */           } 
/*     */         } 
/*     */       } 
/*     */       
/* 673 */       return this;
/*     */     }
/*     */ 
/*     */     
/*     */     public IBlockState func_180362_b() {
/* 678 */       return this.field_180366_e;
/*     */     }
/*     */   }
/*     */   
/*     */   static final class SwitchEnumRailDirection
/*     */   {
/* 684 */     static final int[] field_180371_a = new int[(BlockRailBase.EnumRailDirection.values()).length];
/*     */     
/*     */     private static final String __OBFID = "CL_00002138";
/*     */ 
/*     */     
/*     */     static {
/*     */       try {
/* 691 */         field_180371_a[BlockRailBase.EnumRailDirection.NORTH_SOUTH.ordinal()] = 1;
/*     */       }
/* 693 */       catch (NoSuchFieldError noSuchFieldError) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/* 700 */         field_180371_a[BlockRailBase.EnumRailDirection.EAST_WEST.ordinal()] = 2;
/*     */       }
/* 702 */       catch (NoSuchFieldError noSuchFieldError) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/* 709 */         field_180371_a[BlockRailBase.EnumRailDirection.ASCENDING_EAST.ordinal()] = 3;
/*     */       }
/* 711 */       catch (NoSuchFieldError noSuchFieldError) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/* 718 */         field_180371_a[BlockRailBase.EnumRailDirection.ASCENDING_WEST.ordinal()] = 4;
/*     */       }
/* 720 */       catch (NoSuchFieldError noSuchFieldError) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/* 727 */         field_180371_a[BlockRailBase.EnumRailDirection.ASCENDING_NORTH.ordinal()] = 5;
/*     */       }
/* 729 */       catch (NoSuchFieldError noSuchFieldError) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/* 736 */         field_180371_a[BlockRailBase.EnumRailDirection.ASCENDING_SOUTH.ordinal()] = 6;
/*     */       }
/* 738 */       catch (NoSuchFieldError noSuchFieldError) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/* 745 */         field_180371_a[BlockRailBase.EnumRailDirection.SOUTH_EAST.ordinal()] = 7;
/*     */       }
/* 747 */       catch (NoSuchFieldError noSuchFieldError) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/* 754 */         field_180371_a[BlockRailBase.EnumRailDirection.SOUTH_WEST.ordinal()] = 8;
/*     */       }
/* 756 */       catch (NoSuchFieldError noSuchFieldError) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/* 763 */         field_180371_a[BlockRailBase.EnumRailDirection.NORTH_WEST.ordinal()] = 9;
/*     */       }
/* 765 */       catch (NoSuchFieldError noSuchFieldError) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/* 772 */         field_180371_a[BlockRailBase.EnumRailDirection.NORTH_EAST.ordinal()] = 10;
/*     */       }
/* 774 */       catch (NoSuchFieldError noSuchFieldError) {}
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\block\BlockRailBase.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */