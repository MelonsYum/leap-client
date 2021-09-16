/*     */ package net.minecraft.block;
/*     */ 
/*     */ import com.google.common.base.Predicate;
/*     */ import java.util.Arrays;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.material.MapColor;
/*     */ import net.minecraft.block.properties.IProperty;
/*     */ import net.minecraft.block.properties.PropertyDirection;
/*     */ import net.minecraft.block.properties.PropertyEnum;
/*     */ import net.minecraft.block.state.BlockState;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.creativetab.CreativeTabs;
/*     */ import net.minecraft.entity.Entity;
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
/*     */ import net.minecraft.world.Explosion;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class BlockStairs extends Block {
/*  30 */   public static final PropertyDirection FACING = PropertyDirection.create("facing", (Predicate)EnumFacing.Plane.HORIZONTAL);
/*  31 */   public static final PropertyEnum HALF = PropertyEnum.create("half", EnumHalf.class);
/*  32 */   public static final PropertyEnum SHAPE = PropertyEnum.create("shape", EnumShape.class);
/*  33 */   private static final int[][] field_150150_a = new int[][] { { 4, 5 }, { 5, 7 }, { 6, 7 }, { 4, 6 }, { 0, 1 }, { 1, 3 }, { 2, 3 }, { 0, 2 } };
/*     */   
/*     */   private final Block modelBlock;
/*     */   private final IBlockState modelState;
/*     */   private boolean field_150152_N;
/*     */   private int field_150153_O;
/*     */   private static final String __OBFID = "CL_00000314";
/*     */   
/*     */   protected BlockStairs(IBlockState modelState) {
/*  42 */     super((modelState.getBlock()).blockMaterial);
/*  43 */     setDefaultState(this.blockState.getBaseState().withProperty((IProperty)FACING, (Comparable)EnumFacing.NORTH).withProperty((IProperty)HALF, EnumHalf.BOTTOM).withProperty((IProperty)SHAPE, EnumShape.STRAIGHT));
/*  44 */     this.modelBlock = modelState.getBlock();
/*  45 */     this.modelState = modelState;
/*  46 */     setHardness(this.modelBlock.blockHardness);
/*  47 */     setResistance(this.modelBlock.blockResistance / 3.0F);
/*  48 */     setStepSound(this.modelBlock.stepSound);
/*  49 */     setLightOpacity(255);
/*  50 */     setCreativeTab(CreativeTabs.tabBlock);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setBlockBoundsBasedOnState(IBlockAccess access, BlockPos pos) {
/*  55 */     if (this.field_150152_N) {
/*     */       
/*  57 */       setBlockBounds(0.5F * (this.field_150153_O % 2), 0.5F * (this.field_150153_O / 4 % 2), 0.5F * (this.field_150153_O / 2 % 2), 0.5F + 0.5F * (this.field_150153_O % 2), 0.5F + 0.5F * (this.field_150153_O / 4 % 2), 0.5F + 0.5F * (this.field_150153_O / 2 % 2));
/*     */     }
/*     */     else {
/*     */       
/*  61 */       setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isOpaqueCube() {
/*  67 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isFullCube() {
/*  72 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setBaseCollisionBounds(IBlockAccess worldIn, BlockPos pos) {
/*  80 */     if (worldIn.getBlockState(pos).getValue((IProperty)HALF) == EnumHalf.TOP) {
/*     */       
/*  82 */       setBlockBounds(0.0F, 0.5F, 0.0F, 1.0F, 1.0F, 1.0F);
/*     */     }
/*     */     else {
/*     */       
/*  86 */       setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.5F, 1.0F);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean isBlockStairs(Block p_150148_0_) {
/*  95 */     return p_150148_0_ instanceof BlockStairs;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean isSameStair(IBlockAccess worldIn, BlockPos pos, IBlockState state) {
/* 103 */     IBlockState var3 = worldIn.getBlockState(pos);
/* 104 */     Block var4 = var3.getBlock();
/* 105 */     return (isBlockStairs(var4) && var3.getValue((IProperty)HALF) == state.getValue((IProperty)HALF) && var3.getValue((IProperty)FACING) == state.getValue((IProperty)FACING));
/*     */   }
/*     */ 
/*     */   
/*     */   public int func_176307_f(IBlockAccess p_176307_1_, BlockPos p_176307_2_) {
/* 110 */     IBlockState var3 = p_176307_1_.getBlockState(p_176307_2_);
/* 111 */     EnumFacing var4 = (EnumFacing)var3.getValue((IProperty)FACING);
/* 112 */     EnumHalf var5 = (EnumHalf)var3.getValue((IProperty)HALF);
/* 113 */     boolean var6 = (var5 == EnumHalf.TOP);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 118 */     if (var4 == EnumFacing.EAST) {
/*     */       
/* 120 */       IBlockState var7 = p_176307_1_.getBlockState(p_176307_2_.offsetEast());
/* 121 */       Block var8 = var7.getBlock();
/*     */       
/* 123 */       if (isBlockStairs(var8) && var5 == var7.getValue((IProperty)HALF))
/*     */       {
/* 125 */         EnumFacing var9 = (EnumFacing)var7.getValue((IProperty)FACING);
/*     */         
/* 127 */         if (var9 == EnumFacing.NORTH && !isSameStair(p_176307_1_, p_176307_2_.offsetSouth(), var3))
/*     */         {
/* 129 */           return var6 ? 1 : 2;
/*     */         }
/*     */         
/* 132 */         if (var9 == EnumFacing.SOUTH && !isSameStair(p_176307_1_, p_176307_2_.offsetNorth(), var3))
/*     */         {
/* 134 */           return var6 ? 2 : 1;
/*     */         }
/*     */       }
/*     */     
/* 138 */     } else if (var4 == EnumFacing.WEST) {
/*     */       
/* 140 */       IBlockState var7 = p_176307_1_.getBlockState(p_176307_2_.offsetWest());
/* 141 */       Block var8 = var7.getBlock();
/*     */       
/* 143 */       if (isBlockStairs(var8) && var5 == var7.getValue((IProperty)HALF))
/*     */       {
/* 145 */         EnumFacing var9 = (EnumFacing)var7.getValue((IProperty)FACING);
/*     */         
/* 147 */         if (var9 == EnumFacing.NORTH && !isSameStair(p_176307_1_, p_176307_2_.offsetSouth(), var3))
/*     */         {
/* 149 */           return var6 ? 2 : 1;
/*     */         }
/*     */         
/* 152 */         if (var9 == EnumFacing.SOUTH && !isSameStair(p_176307_1_, p_176307_2_.offsetNorth(), var3))
/*     */         {
/* 154 */           return var6 ? 1 : 2;
/*     */         }
/*     */       }
/*     */     
/* 158 */     } else if (var4 == EnumFacing.SOUTH) {
/*     */       
/* 160 */       IBlockState var7 = p_176307_1_.getBlockState(p_176307_2_.offsetSouth());
/* 161 */       Block var8 = var7.getBlock();
/*     */       
/* 163 */       if (isBlockStairs(var8) && var5 == var7.getValue((IProperty)HALF))
/*     */       {
/* 165 */         EnumFacing var9 = (EnumFacing)var7.getValue((IProperty)FACING);
/*     */         
/* 167 */         if (var9 == EnumFacing.WEST && !isSameStair(p_176307_1_, p_176307_2_.offsetEast(), var3))
/*     */         {
/* 169 */           return var6 ? 2 : 1;
/*     */         }
/*     */         
/* 172 */         if (var9 == EnumFacing.EAST && !isSameStair(p_176307_1_, p_176307_2_.offsetWest(), var3))
/*     */         {
/* 174 */           return var6 ? 1 : 2;
/*     */         }
/*     */       }
/*     */     
/* 178 */     } else if (var4 == EnumFacing.NORTH) {
/*     */       
/* 180 */       IBlockState var7 = p_176307_1_.getBlockState(p_176307_2_.offsetNorth());
/* 181 */       Block var8 = var7.getBlock();
/*     */       
/* 183 */       if (isBlockStairs(var8) && var5 == var7.getValue((IProperty)HALF)) {
/*     */         
/* 185 */         EnumFacing var9 = (EnumFacing)var7.getValue((IProperty)FACING);
/*     */         
/* 187 */         if (var9 == EnumFacing.WEST && !isSameStair(p_176307_1_, p_176307_2_.offsetEast(), var3))
/*     */         {
/* 189 */           return var6 ? 1 : 2;
/*     */         }
/*     */         
/* 192 */         if (var9 == EnumFacing.EAST && !isSameStair(p_176307_1_, p_176307_2_.offsetWest(), var3))
/*     */         {
/* 194 */           return var6 ? 2 : 1;
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 199 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public int func_176305_g(IBlockAccess p_176305_1_, BlockPos p_176305_2_) {
/* 204 */     IBlockState var3 = p_176305_1_.getBlockState(p_176305_2_);
/* 205 */     EnumFacing var4 = (EnumFacing)var3.getValue((IProperty)FACING);
/* 206 */     EnumHalf var5 = (EnumHalf)var3.getValue((IProperty)HALF);
/* 207 */     boolean var6 = (var5 == EnumHalf.TOP);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 212 */     if (var4 == EnumFacing.EAST) {
/*     */       
/* 214 */       IBlockState var7 = p_176305_1_.getBlockState(p_176305_2_.offsetWest());
/* 215 */       Block var8 = var7.getBlock();
/*     */       
/* 217 */       if (isBlockStairs(var8) && var5 == var7.getValue((IProperty)HALF))
/*     */       {
/* 219 */         EnumFacing var9 = (EnumFacing)var7.getValue((IProperty)FACING);
/*     */         
/* 221 */         if (var9 == EnumFacing.NORTH && !isSameStair(p_176305_1_, p_176305_2_.offsetNorth(), var3))
/*     */         {
/* 223 */           return var6 ? 1 : 2;
/*     */         }
/*     */         
/* 226 */         if (var9 == EnumFacing.SOUTH && !isSameStair(p_176305_1_, p_176305_2_.offsetSouth(), var3))
/*     */         {
/* 228 */           return var6 ? 2 : 1;
/*     */         }
/*     */       }
/*     */     
/* 232 */     } else if (var4 == EnumFacing.WEST) {
/*     */       
/* 234 */       IBlockState var7 = p_176305_1_.getBlockState(p_176305_2_.offsetEast());
/* 235 */       Block var8 = var7.getBlock();
/*     */       
/* 237 */       if (isBlockStairs(var8) && var5 == var7.getValue((IProperty)HALF))
/*     */       {
/* 239 */         EnumFacing var9 = (EnumFacing)var7.getValue((IProperty)FACING);
/*     */         
/* 241 */         if (var9 == EnumFacing.NORTH && !isSameStair(p_176305_1_, p_176305_2_.offsetNorth(), var3))
/*     */         {
/* 243 */           return var6 ? 2 : 1;
/*     */         }
/*     */         
/* 246 */         if (var9 == EnumFacing.SOUTH && !isSameStair(p_176305_1_, p_176305_2_.offsetSouth(), var3))
/*     */         {
/* 248 */           return var6 ? 1 : 2;
/*     */         }
/*     */       }
/*     */     
/* 252 */     } else if (var4 == EnumFacing.SOUTH) {
/*     */       
/* 254 */       IBlockState var7 = p_176305_1_.getBlockState(p_176305_2_.offsetNorth());
/* 255 */       Block var8 = var7.getBlock();
/*     */       
/* 257 */       if (isBlockStairs(var8) && var5 == var7.getValue((IProperty)HALF))
/*     */       {
/* 259 */         EnumFacing var9 = (EnumFacing)var7.getValue((IProperty)FACING);
/*     */         
/* 261 */         if (var9 == EnumFacing.WEST && !isSameStair(p_176305_1_, p_176305_2_.offsetWest(), var3))
/*     */         {
/* 263 */           return var6 ? 2 : 1;
/*     */         }
/*     */         
/* 266 */         if (var9 == EnumFacing.EAST && !isSameStair(p_176305_1_, p_176305_2_.offsetEast(), var3))
/*     */         {
/* 268 */           return var6 ? 1 : 2;
/*     */         }
/*     */       }
/*     */     
/* 272 */     } else if (var4 == EnumFacing.NORTH) {
/*     */       
/* 274 */       IBlockState var7 = p_176305_1_.getBlockState(p_176305_2_.offsetSouth());
/* 275 */       Block var8 = var7.getBlock();
/*     */       
/* 277 */       if (isBlockStairs(var8) && var5 == var7.getValue((IProperty)HALF)) {
/*     */         
/* 279 */         EnumFacing var9 = (EnumFacing)var7.getValue((IProperty)FACING);
/*     */         
/* 281 */         if (var9 == EnumFacing.WEST && !isSameStair(p_176305_1_, p_176305_2_.offsetWest(), var3))
/*     */         {
/* 283 */           return var6 ? 1 : 2;
/*     */         }
/*     */         
/* 286 */         if (var9 == EnumFacing.EAST && !isSameStair(p_176305_1_, p_176305_2_.offsetEast(), var3))
/*     */         {
/* 288 */           return var6 ? 2 : 1;
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 293 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_176306_h(IBlockAccess p_176306_1_, BlockPos p_176306_2_) {
/* 298 */     IBlockState var3 = p_176306_1_.getBlockState(p_176306_2_);
/* 299 */     EnumFacing var4 = (EnumFacing)var3.getValue((IProperty)FACING);
/* 300 */     EnumHalf var5 = (EnumHalf)var3.getValue((IProperty)HALF);
/* 301 */     boolean var6 = (var5 == EnumHalf.TOP);
/* 302 */     float var7 = 0.5F;
/* 303 */     float var8 = 1.0F;
/*     */     
/* 305 */     if (var6) {
/*     */       
/* 307 */       var7 = 0.0F;
/* 308 */       var8 = 0.5F;
/*     */     } 
/*     */     
/* 311 */     float var9 = 0.0F;
/* 312 */     float var10 = 1.0F;
/* 313 */     float var11 = 0.0F;
/* 314 */     float var12 = 0.5F;
/* 315 */     boolean var13 = true;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 320 */     if (var4 == EnumFacing.EAST) {
/*     */       
/* 322 */       var9 = 0.5F;
/* 323 */       var12 = 1.0F;
/* 324 */       IBlockState var14 = p_176306_1_.getBlockState(p_176306_2_.offsetEast());
/* 325 */       Block var15 = var14.getBlock();
/*     */       
/* 327 */       if (isBlockStairs(var15) && var5 == var14.getValue((IProperty)HALF)) {
/*     */         
/* 329 */         EnumFacing var16 = (EnumFacing)var14.getValue((IProperty)FACING);
/*     */         
/* 331 */         if (var16 == EnumFacing.NORTH && !isSameStair(p_176306_1_, p_176306_2_.offsetSouth(), var3))
/*     */         {
/* 333 */           var12 = 0.5F;
/* 334 */           var13 = false;
/*     */         }
/* 336 */         else if (var16 == EnumFacing.SOUTH && !isSameStair(p_176306_1_, p_176306_2_.offsetNorth(), var3))
/*     */         {
/* 338 */           var11 = 0.5F;
/* 339 */           var13 = false;
/*     */         }
/*     */       
/*     */       } 
/* 343 */     } else if (var4 == EnumFacing.WEST) {
/*     */       
/* 345 */       var10 = 0.5F;
/* 346 */       var12 = 1.0F;
/* 347 */       IBlockState var14 = p_176306_1_.getBlockState(p_176306_2_.offsetWest());
/* 348 */       Block var15 = var14.getBlock();
/*     */       
/* 350 */       if (isBlockStairs(var15) && var5 == var14.getValue((IProperty)HALF)) {
/*     */         
/* 352 */         EnumFacing var16 = (EnumFacing)var14.getValue((IProperty)FACING);
/*     */         
/* 354 */         if (var16 == EnumFacing.NORTH && !isSameStair(p_176306_1_, p_176306_2_.offsetSouth(), var3))
/*     */         {
/* 356 */           var12 = 0.5F;
/* 357 */           var13 = false;
/*     */         }
/* 359 */         else if (var16 == EnumFacing.SOUTH && !isSameStair(p_176306_1_, p_176306_2_.offsetNorth(), var3))
/*     */         {
/* 361 */           var11 = 0.5F;
/* 362 */           var13 = false;
/*     */         }
/*     */       
/*     */       } 
/* 366 */     } else if (var4 == EnumFacing.SOUTH) {
/*     */       
/* 368 */       var11 = 0.5F;
/* 369 */       var12 = 1.0F;
/* 370 */       IBlockState var14 = p_176306_1_.getBlockState(p_176306_2_.offsetSouth());
/* 371 */       Block var15 = var14.getBlock();
/*     */       
/* 373 */       if (isBlockStairs(var15) && var5 == var14.getValue((IProperty)HALF)) {
/*     */         
/* 375 */         EnumFacing var16 = (EnumFacing)var14.getValue((IProperty)FACING);
/*     */         
/* 377 */         if (var16 == EnumFacing.WEST && !isSameStair(p_176306_1_, p_176306_2_.offsetEast(), var3))
/*     */         {
/* 379 */           var10 = 0.5F;
/* 380 */           var13 = false;
/*     */         }
/* 382 */         else if (var16 == EnumFacing.EAST && !isSameStair(p_176306_1_, p_176306_2_.offsetWest(), var3))
/*     */         {
/* 384 */           var9 = 0.5F;
/* 385 */           var13 = false;
/*     */         }
/*     */       
/*     */       } 
/* 389 */     } else if (var4 == EnumFacing.NORTH) {
/*     */       
/* 391 */       IBlockState var14 = p_176306_1_.getBlockState(p_176306_2_.offsetNorth());
/* 392 */       Block var15 = var14.getBlock();
/*     */       
/* 394 */       if (isBlockStairs(var15) && var5 == var14.getValue((IProperty)HALF)) {
/*     */         
/* 396 */         EnumFacing var16 = (EnumFacing)var14.getValue((IProperty)FACING);
/*     */         
/* 398 */         if (var16 == EnumFacing.WEST && !isSameStair(p_176306_1_, p_176306_2_.offsetEast(), var3)) {
/*     */           
/* 400 */           var10 = 0.5F;
/* 401 */           var13 = false;
/*     */         }
/* 403 */         else if (var16 == EnumFacing.EAST && !isSameStair(p_176306_1_, p_176306_2_.offsetWest(), var3)) {
/*     */           
/* 405 */           var9 = 0.5F;
/* 406 */           var13 = false;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 411 */     setBlockBounds(var9, var7, var11, var10, var8, var12);
/* 412 */     return var13;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_176304_i(IBlockAccess p_176304_1_, BlockPos p_176304_2_) {
/* 417 */     IBlockState var3 = p_176304_1_.getBlockState(p_176304_2_);
/* 418 */     EnumFacing var4 = (EnumFacing)var3.getValue((IProperty)FACING);
/* 419 */     EnumHalf var5 = (EnumHalf)var3.getValue((IProperty)HALF);
/* 420 */     boolean var6 = (var5 == EnumHalf.TOP);
/* 421 */     float var7 = 0.5F;
/* 422 */     float var8 = 1.0F;
/*     */     
/* 424 */     if (var6) {
/*     */       
/* 426 */       var7 = 0.0F;
/* 427 */       var8 = 0.5F;
/*     */     } 
/*     */     
/* 430 */     float var9 = 0.0F;
/* 431 */     float var10 = 0.5F;
/* 432 */     float var11 = 0.5F;
/* 433 */     float var12 = 1.0F;
/* 434 */     boolean var13 = false;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 439 */     if (var4 == EnumFacing.EAST) {
/*     */       
/* 441 */       IBlockState var14 = p_176304_1_.getBlockState(p_176304_2_.offsetWest());
/* 442 */       Block var15 = var14.getBlock();
/*     */       
/* 444 */       if (isBlockStairs(var15) && var5 == var14.getValue((IProperty)HALF)) {
/*     */         
/* 446 */         EnumFacing var16 = (EnumFacing)var14.getValue((IProperty)FACING);
/*     */         
/* 448 */         if (var16 == EnumFacing.NORTH && !isSameStair(p_176304_1_, p_176304_2_.offsetNorth(), var3))
/*     */         {
/* 450 */           var11 = 0.0F;
/* 451 */           var12 = 0.5F;
/* 452 */           var13 = true;
/*     */         }
/* 454 */         else if (var16 == EnumFacing.SOUTH && !isSameStair(p_176304_1_, p_176304_2_.offsetSouth(), var3))
/*     */         {
/* 456 */           var11 = 0.5F;
/* 457 */           var12 = 1.0F;
/* 458 */           var13 = true;
/*     */         }
/*     */       
/*     */       } 
/* 462 */     } else if (var4 == EnumFacing.WEST) {
/*     */       
/* 464 */       IBlockState var14 = p_176304_1_.getBlockState(p_176304_2_.offsetEast());
/* 465 */       Block var15 = var14.getBlock();
/*     */       
/* 467 */       if (isBlockStairs(var15) && var5 == var14.getValue((IProperty)HALF)) {
/*     */         
/* 469 */         var9 = 0.5F;
/* 470 */         var10 = 1.0F;
/* 471 */         EnumFacing var16 = (EnumFacing)var14.getValue((IProperty)FACING);
/*     */         
/* 473 */         if (var16 == EnumFacing.NORTH && !isSameStair(p_176304_1_, p_176304_2_.offsetNorth(), var3))
/*     */         {
/* 475 */           var11 = 0.0F;
/* 476 */           var12 = 0.5F;
/* 477 */           var13 = true;
/*     */         }
/* 479 */         else if (var16 == EnumFacing.SOUTH && !isSameStair(p_176304_1_, p_176304_2_.offsetSouth(), var3))
/*     */         {
/* 481 */           var11 = 0.5F;
/* 482 */           var12 = 1.0F;
/* 483 */           var13 = true;
/*     */         }
/*     */       
/*     */       } 
/* 487 */     } else if (var4 == EnumFacing.SOUTH) {
/*     */       
/* 489 */       IBlockState var14 = p_176304_1_.getBlockState(p_176304_2_.offsetNorth());
/* 490 */       Block var15 = var14.getBlock();
/*     */       
/* 492 */       if (isBlockStairs(var15) && var5 == var14.getValue((IProperty)HALF)) {
/*     */         
/* 494 */         var11 = 0.0F;
/* 495 */         var12 = 0.5F;
/* 496 */         EnumFacing var16 = (EnumFacing)var14.getValue((IProperty)FACING);
/*     */         
/* 498 */         if (var16 == EnumFacing.WEST && !isSameStair(p_176304_1_, p_176304_2_.offsetWest(), var3))
/*     */         {
/* 500 */           var13 = true;
/*     */         }
/* 502 */         else if (var16 == EnumFacing.EAST && !isSameStair(p_176304_1_, p_176304_2_.offsetEast(), var3))
/*     */         {
/* 504 */           var9 = 0.5F;
/* 505 */           var10 = 1.0F;
/* 506 */           var13 = true;
/*     */         }
/*     */       
/*     */       } 
/* 510 */     } else if (var4 == EnumFacing.NORTH) {
/*     */       
/* 512 */       IBlockState var14 = p_176304_1_.getBlockState(p_176304_2_.offsetSouth());
/* 513 */       Block var15 = var14.getBlock();
/*     */       
/* 515 */       if (isBlockStairs(var15) && var5 == var14.getValue((IProperty)HALF)) {
/*     */         
/* 517 */         EnumFacing var16 = (EnumFacing)var14.getValue((IProperty)FACING);
/*     */         
/* 519 */         if (var16 == EnumFacing.WEST && !isSameStair(p_176304_1_, p_176304_2_.offsetWest(), var3)) {
/*     */           
/* 521 */           var13 = true;
/*     */         }
/* 523 */         else if (var16 == EnumFacing.EAST && !isSameStair(p_176304_1_, p_176304_2_.offsetEast(), var3)) {
/*     */           
/* 525 */           var9 = 0.5F;
/* 526 */           var10 = 1.0F;
/* 527 */           var13 = true;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 532 */     if (var13)
/*     */     {
/* 534 */       setBlockBounds(var9, var7, var11, var10, var8, var12);
/*     */     }
/*     */     
/* 537 */     return var13;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addCollisionBoxesToList(World worldIn, BlockPos pos, IBlockState state, AxisAlignedBB mask, List list, Entity collidingEntity) {
/* 547 */     setBaseCollisionBounds((IBlockAccess)worldIn, pos);
/* 548 */     super.addCollisionBoxesToList(worldIn, pos, state, mask, list, collidingEntity);
/* 549 */     boolean var7 = func_176306_h((IBlockAccess)worldIn, pos);
/* 550 */     super.addCollisionBoxesToList(worldIn, pos, state, mask, list, collidingEntity);
/*     */     
/* 552 */     if (var7 && func_176304_i((IBlockAccess)worldIn, pos))
/*     */     {
/* 554 */       super.addCollisionBoxesToList(worldIn, pos, state, mask, list, collidingEntity);
/*     */     }
/*     */     
/* 557 */     setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
/*     */   }
/*     */ 
/*     */   
/*     */   public void randomDisplayTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
/* 562 */     this.modelBlock.randomDisplayTick(worldIn, pos, state, rand);
/*     */   }
/*     */ 
/*     */   
/*     */   public void onBlockClicked(World worldIn, BlockPos pos, EntityPlayer playerIn) {
/* 567 */     this.modelBlock.onBlockClicked(worldIn, pos, playerIn);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onBlockDestroyedByPlayer(World worldIn, BlockPos pos, IBlockState state) {
/* 575 */     this.modelBlock.onBlockDestroyedByPlayer(worldIn, pos, state);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getMixedBrightnessForBlock(IBlockAccess worldIn, BlockPos pos) {
/* 580 */     return this.modelBlock.getMixedBrightnessForBlock(worldIn, pos);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getExplosionResistance(Entity exploder) {
/* 588 */     return this.modelBlock.getExplosionResistance(exploder);
/*     */   }
/*     */ 
/*     */   
/*     */   public EnumWorldBlockLayer getBlockLayer() {
/* 593 */     return this.modelBlock.getBlockLayer();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int tickRate(World worldIn) {
/* 601 */     return this.modelBlock.tickRate(worldIn);
/*     */   }
/*     */ 
/*     */   
/*     */   public AxisAlignedBB getSelectedBoundingBox(World worldIn, BlockPos pos) {
/* 606 */     return this.modelBlock.getSelectedBoundingBox(worldIn, pos);
/*     */   }
/*     */ 
/*     */   
/*     */   public Vec3 modifyAcceleration(World worldIn, BlockPos pos, Entity entityIn, Vec3 motion) {
/* 611 */     return this.modelBlock.modifyAcceleration(worldIn, pos, entityIn, motion);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isCollidable() {
/* 619 */     return this.modelBlock.isCollidable();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canCollideCheck(IBlockState state, boolean p_176209_2_) {
/* 624 */     return this.modelBlock.canCollideCheck(state, p_176209_2_);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canPlaceBlockAt(World worldIn, BlockPos pos) {
/* 629 */     return this.modelBlock.canPlaceBlockAt(worldIn, pos);
/*     */   }
/*     */ 
/*     */   
/*     */   public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state) {
/* 634 */     onNeighborBlockChange(worldIn, pos, this.modelState, Blocks.air);
/* 635 */     this.modelBlock.onBlockAdded(worldIn, pos, this.modelState);
/*     */   }
/*     */ 
/*     */   
/*     */   public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
/* 640 */     this.modelBlock.breakBlock(worldIn, pos, this.modelState);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onEntityCollidedWithBlock(World worldIn, BlockPos pos, Entity entityIn) {
/* 648 */     this.modelBlock.onEntityCollidedWithBlock(worldIn, pos, entityIn);
/*     */   }
/*     */ 
/*     */   
/*     */   public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
/* 653 */     this.modelBlock.updateTick(worldIn, pos, state, rand);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumFacing side, float hitX, float hitY, float hitZ) {
/* 658 */     return this.modelBlock.onBlockActivated(worldIn, pos, this.modelState, playerIn, EnumFacing.DOWN, 0.0F, 0.0F, 0.0F);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onBlockDestroyedByExplosion(World worldIn, BlockPos pos, Explosion explosionIn) {
/* 666 */     this.modelBlock.onBlockDestroyedByExplosion(worldIn, pos, explosionIn);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MapColor getMapColor(IBlockState state) {
/* 674 */     return this.modelBlock.getMapColor(this.modelState);
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockState onBlockPlaced(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
/* 679 */     IBlockState var9 = super.onBlockPlaced(worldIn, pos, facing, hitX, hitY, hitZ, meta, placer);
/* 680 */     var9 = var9.withProperty((IProperty)FACING, (Comparable)placer.func_174811_aO()).withProperty((IProperty)SHAPE, EnumShape.STRAIGHT);
/* 681 */     return (facing != EnumFacing.DOWN && (facing == EnumFacing.UP || hitY <= 0.5D)) ? var9.withProperty((IProperty)HALF, EnumHalf.BOTTOM) : var9.withProperty((IProperty)HALF, EnumHalf.TOP);
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
/* 692 */     MovingObjectPosition[] var5 = new MovingObjectPosition[8];
/* 693 */     IBlockState var6 = worldIn.getBlockState(pos);
/* 694 */     int var7 = ((EnumFacing)var6.getValue((IProperty)FACING)).getHorizontalIndex();
/* 695 */     boolean var8 = (var6.getValue((IProperty)HALF) == EnumHalf.TOP);
/* 696 */     int[] var9 = field_150150_a[var7 + (var8 ? 4 : 0)];
/* 697 */     this.field_150152_N = true;
/*     */     
/* 699 */     for (int var10 = 0; var10 < 8; var10++) {
/*     */       
/* 701 */       this.field_150153_O = var10;
/*     */       
/* 703 */       if (Arrays.binarySearch(var9, var10) < 0)
/*     */       {
/* 705 */         var5[var10] = super.collisionRayTrace(worldIn, pos, start, end);
/*     */       }
/*     */     } 
/*     */     
/* 709 */     int[] var19 = var9;
/* 710 */     int var11 = var9.length;
/*     */     
/* 712 */     for (int var12 = 0; var12 < var11; var12++) {
/*     */       
/* 714 */       int var13 = var19[var12];
/* 715 */       var5[var13] = null;
/*     */     } 
/*     */     
/* 718 */     MovingObjectPosition var20 = null;
/* 719 */     double var21 = 0.0D;
/* 720 */     MovingObjectPosition[] var22 = var5;
/* 721 */     int var14 = var5.length;
/*     */     
/* 723 */     for (int var15 = 0; var15 < var14; var15++) {
/*     */       
/* 725 */       MovingObjectPosition var16 = var22[var15];
/*     */       
/* 727 */       if (var16 != null) {
/*     */         
/* 729 */         double var17 = var16.hitVec.squareDistanceTo(end);
/*     */         
/* 731 */         if (var17 > var21) {
/*     */           
/* 733 */           var20 = var16;
/* 734 */           var21 = var17;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 739 */     return var20;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IBlockState getStateFromMeta(int meta) {
/* 747 */     IBlockState var2 = getDefaultState().withProperty((IProperty)HALF, ((meta & 0x4) > 0) ? EnumHalf.TOP : EnumHalf.BOTTOM);
/* 748 */     var2 = var2.withProperty((IProperty)FACING, (Comparable)EnumFacing.getFront(5 - (meta & 0x3)));
/* 749 */     return var2;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getMetaFromState(IBlockState state) {
/* 757 */     int var2 = 0;
/*     */     
/* 759 */     if (state.getValue((IProperty)HALF) == EnumHalf.TOP)
/*     */     {
/* 761 */       var2 |= 0x4;
/*     */     }
/*     */     
/* 764 */     var2 |= 5 - ((EnumFacing)state.getValue((IProperty)FACING)).getIndex();
/* 765 */     return var2;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
/* 774 */     if (func_176306_h(worldIn, pos)) {
/*     */       
/* 776 */       switch (func_176305_g(worldIn, pos)) {
/*     */         
/*     */         case 0:
/* 779 */           state = state.withProperty((IProperty)SHAPE, EnumShape.STRAIGHT);
/*     */           break;
/*     */         
/*     */         case 1:
/* 783 */           state = state.withProperty((IProperty)SHAPE, EnumShape.INNER_RIGHT);
/*     */           break;
/*     */         
/*     */         case 2:
/* 787 */           state = state.withProperty((IProperty)SHAPE, EnumShape.INNER_LEFT);
/*     */           break;
/*     */       } 
/*     */     
/*     */     } else {
/* 792 */       switch (func_176307_f(worldIn, pos)) {
/*     */         
/*     */         case 0:
/* 795 */           state = state.withProperty((IProperty)SHAPE, EnumShape.STRAIGHT);
/*     */           break;
/*     */         
/*     */         case 1:
/* 799 */           state = state.withProperty((IProperty)SHAPE, EnumShape.OUTER_RIGHT);
/*     */           break;
/*     */         
/*     */         case 2:
/* 803 */           state = state.withProperty((IProperty)SHAPE, EnumShape.OUTER_LEFT);
/*     */           break;
/*     */       } 
/*     */     } 
/* 807 */     return state;
/*     */   }
/*     */ 
/*     */   
/*     */   protected BlockState createBlockState() {
/* 812 */     return new BlockState(this, new IProperty[] { (IProperty)FACING, (IProperty)HALF, (IProperty)SHAPE });
/*     */   }
/*     */   
/*     */   public enum EnumHalf
/*     */     implements IStringSerializable {
/* 817 */     TOP("TOP", 0, "top"),
/* 818 */     BOTTOM("BOTTOM", 1, "bottom");
/*     */     
/*     */     private final String field_176709_c;
/* 821 */     private static final EnumHalf[] $VALUES = new EnumHalf[] { TOP, BOTTOM };
/*     */     
/*     */     private static final String __OBFID = "CL_00002062";
/*     */     
/*     */     EnumHalf(String p_i45683_1_, int p_i45683_2_, String p_i45683_3_) {
/* 826 */       this.field_176709_c = p_i45683_3_;
/*     */     } static {
/*     */     
/*     */     }
/*     */     public String toString() {
/* 831 */       return this.field_176709_c;
/*     */     }
/*     */ 
/*     */     
/*     */     public String getName() {
/* 836 */       return this.field_176709_c;
/*     */     }
/*     */   }
/*     */   
/*     */   public enum EnumShape
/*     */     implements IStringSerializable {
/* 842 */     STRAIGHT("STRAIGHT", 0, "straight"),
/* 843 */     INNER_LEFT("INNER_LEFT", 1, "inner_left"),
/* 844 */     INNER_RIGHT("INNER_RIGHT", 2, "inner_right"),
/* 845 */     OUTER_LEFT("OUTER_LEFT", 3, "outer_left"),
/* 846 */     OUTER_RIGHT("OUTER_RIGHT", 4, "outer_right");
/*     */     
/*     */     private final String field_176699_f;
/* 849 */     private static final EnumShape[] $VALUES = new EnumShape[] { STRAIGHT, INNER_LEFT, INNER_RIGHT, OUTER_LEFT, OUTER_RIGHT }; private static final String __OBFID = "CL_00002061";
/*     */     static {
/*     */     
/*     */     }
/*     */     EnumShape(String p_i45682_1_, int p_i45682_2_, String p_i45682_3_) {
/* 854 */       this.field_176699_f = p_i45682_3_;
/*     */     }
/*     */ 
/*     */     
/*     */     public String toString() {
/* 859 */       return this.field_176699_f;
/*     */     }
/*     */ 
/*     */     
/*     */     public String getName() {
/* 864 */       return this.field_176699_f;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\block\BlockStairs.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */