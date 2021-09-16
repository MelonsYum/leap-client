/*     */ package net.minecraft.block;
/*     */ 
/*     */ import com.google.common.collect.Maps;
/*     */ import java.util.Map;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.material.MapColor;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.block.properties.IProperty;
/*     */ import net.minecraft.block.properties.PropertyBool;
/*     */ import net.minecraft.block.properties.PropertyInteger;
/*     */ import net.minecraft.block.state.BlockState;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.EnumParticleTypes;
/*     */ import net.minecraft.util.EnumWorldBlockLayer;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class BlockFire
/*     */   extends Block
/*     */ {
/*  25 */   public static final PropertyInteger field_176543_a = PropertyInteger.create("age", 0, 15);
/*  26 */   public static final PropertyBool field_176540_b = PropertyBool.create("flip");
/*  27 */   public static final PropertyBool field_176544_M = PropertyBool.create("alt");
/*  28 */   public static final PropertyBool field_176545_N = PropertyBool.create("north");
/*  29 */   public static final PropertyBool field_176546_O = PropertyBool.create("east");
/*  30 */   public static final PropertyBool field_176541_P = PropertyBool.create("south");
/*  31 */   public static final PropertyBool field_176539_Q = PropertyBool.create("west");
/*  32 */   public static final PropertyInteger field_176542_R = PropertyInteger.create("upper", 0, 2);
/*  33 */   private final Map field_149849_a = Maps.newIdentityHashMap();
/*  34 */   private final Map field_149848_b = Maps.newIdentityHashMap();
/*     */ 
/*     */ 
/*     */   
/*     */   private static final String __OBFID = "CL_00000245";
/*     */ 
/*     */ 
/*     */   
/*     */   public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
/*  43 */     int var4 = pos.getX();
/*  44 */     int var5 = pos.getY();
/*  45 */     int var6 = pos.getZ();
/*     */     
/*  47 */     if (!World.doesBlockHaveSolidTopSurface(worldIn, pos.offsetDown()) && !Blocks.fire.func_176535_e(worldIn, pos.offsetDown())) {
/*     */       
/*  49 */       boolean var7 = ((var4 + var5 + var6 & 0x1) == 1);
/*  50 */       boolean var8 = ((var4 / 2 + var5 / 2 + var6 / 2 & 0x1) == 1);
/*  51 */       int var9 = 0;
/*     */       
/*  53 */       if (func_176535_e(worldIn, pos.offsetUp()))
/*     */       {
/*  55 */         var9 = var7 ? 1 : 2;
/*     */       }
/*     */       
/*  58 */       return state.withProperty((IProperty)field_176545_N, Boolean.valueOf(func_176535_e(worldIn, pos.offsetNorth()))).withProperty((IProperty)field_176546_O, Boolean.valueOf(func_176535_e(worldIn, pos.offsetEast()))).withProperty((IProperty)field_176541_P, Boolean.valueOf(func_176535_e(worldIn, pos.offsetSouth()))).withProperty((IProperty)field_176539_Q, Boolean.valueOf(func_176535_e(worldIn, pos.offsetWest()))).withProperty((IProperty)field_176542_R, Integer.valueOf(var9)).withProperty((IProperty)field_176540_b, Boolean.valueOf(var8)).withProperty((IProperty)field_176544_M, Boolean.valueOf(var7));
/*     */     } 
/*     */ 
/*     */     
/*  62 */     return getDefaultState();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected BlockFire() {
/*  68 */     super(Material.fire);
/*  69 */     setDefaultState(this.blockState.getBaseState().withProperty((IProperty)field_176543_a, Integer.valueOf(0)).withProperty((IProperty)field_176540_b, Boolean.valueOf(false)).withProperty((IProperty)field_176544_M, Boolean.valueOf(false)).withProperty((IProperty)field_176545_N, Boolean.valueOf(false)).withProperty((IProperty)field_176546_O, Boolean.valueOf(false)).withProperty((IProperty)field_176541_P, Boolean.valueOf(false)).withProperty((IProperty)field_176539_Q, Boolean.valueOf(false)).withProperty((IProperty)field_176542_R, Integer.valueOf(0)));
/*  70 */     setTickRandomly(true);
/*     */   }
/*     */ 
/*     */   
/*     */   public static void func_149843_e() {
/*  75 */     Blocks.fire.func_180686_a(Blocks.planks, 5, 20);
/*  76 */     Blocks.fire.func_180686_a(Blocks.double_wooden_slab, 5, 20);
/*  77 */     Blocks.fire.func_180686_a(Blocks.wooden_slab, 5, 20);
/*  78 */     Blocks.fire.func_180686_a(Blocks.oak_fence_gate, 5, 20);
/*  79 */     Blocks.fire.func_180686_a(Blocks.spruce_fence_gate, 5, 20);
/*  80 */     Blocks.fire.func_180686_a(Blocks.birch_fence_gate, 5, 20);
/*  81 */     Blocks.fire.func_180686_a(Blocks.jungle_fence_gate, 5, 20);
/*  82 */     Blocks.fire.func_180686_a(Blocks.dark_oak_fence_gate, 5, 20);
/*  83 */     Blocks.fire.func_180686_a(Blocks.acacia_fence_gate, 5, 20);
/*  84 */     Blocks.fire.func_180686_a(Blocks.oak_fence, 5, 20);
/*  85 */     Blocks.fire.func_180686_a(Blocks.spruce_fence, 5, 20);
/*  86 */     Blocks.fire.func_180686_a(Blocks.birch_fence, 5, 20);
/*  87 */     Blocks.fire.func_180686_a(Blocks.jungle_fence, 5, 20);
/*  88 */     Blocks.fire.func_180686_a(Blocks.dark_oak_fence, 5, 20);
/*  89 */     Blocks.fire.func_180686_a(Blocks.acacia_fence, 5, 20);
/*  90 */     Blocks.fire.func_180686_a(Blocks.oak_stairs, 5, 20);
/*  91 */     Blocks.fire.func_180686_a(Blocks.birch_stairs, 5, 20);
/*  92 */     Blocks.fire.func_180686_a(Blocks.spruce_stairs, 5, 20);
/*  93 */     Blocks.fire.func_180686_a(Blocks.jungle_stairs, 5, 20);
/*  94 */     Blocks.fire.func_180686_a(Blocks.log, 5, 5);
/*  95 */     Blocks.fire.func_180686_a(Blocks.log2, 5, 5);
/*  96 */     Blocks.fire.func_180686_a(Blocks.leaves, 30, 60);
/*  97 */     Blocks.fire.func_180686_a(Blocks.leaves2, 30, 60);
/*  98 */     Blocks.fire.func_180686_a(Blocks.bookshelf, 30, 20);
/*  99 */     Blocks.fire.func_180686_a(Blocks.tnt, 15, 100);
/* 100 */     Blocks.fire.func_180686_a(Blocks.tallgrass, 60, 100);
/* 101 */     Blocks.fire.func_180686_a(Blocks.double_plant, 60, 100);
/* 102 */     Blocks.fire.func_180686_a(Blocks.yellow_flower, 60, 100);
/* 103 */     Blocks.fire.func_180686_a(Blocks.red_flower, 60, 100);
/* 104 */     Blocks.fire.func_180686_a(Blocks.deadbush, 60, 100);
/* 105 */     Blocks.fire.func_180686_a(Blocks.wool, 30, 60);
/* 106 */     Blocks.fire.func_180686_a(Blocks.vine, 15, 100);
/* 107 */     Blocks.fire.func_180686_a(Blocks.coal_block, 5, 5);
/* 108 */     Blocks.fire.func_180686_a(Blocks.hay_block, 60, 20);
/* 109 */     Blocks.fire.func_180686_a(Blocks.carpet, 60, 20);
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_180686_a(Block p_180686_1_, int p_180686_2_, int p_180686_3_) {
/* 114 */     this.field_149849_a.put(p_180686_1_, Integer.valueOf(p_180686_2_));
/* 115 */     this.field_149848_b.put(p_180686_1_, Integer.valueOf(p_180686_3_));
/*     */   }
/*     */ 
/*     */   
/*     */   public AxisAlignedBB getCollisionBoundingBox(World worldIn, BlockPos pos, IBlockState state) {
/* 120 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isOpaqueCube() {
/* 125 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isFullCube() {
/* 130 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int quantityDropped(Random random) {
/* 138 */     return 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int tickRate(World worldIn) {
/* 146 */     return 30;
/*     */   }
/*     */ 
/*     */   
/*     */   public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
/* 151 */     if (worldIn.getGameRules().getGameRuleBooleanValue("doFireTick")) {
/*     */       
/* 153 */       if (!canPlaceBlockAt(worldIn, pos))
/*     */       {
/* 155 */         worldIn.setBlockToAir(pos);
/*     */       }
/*     */       
/* 158 */       Block var5 = worldIn.getBlockState(pos.offsetDown()).getBlock();
/* 159 */       boolean var6 = (var5 == Blocks.netherrack);
/*     */       
/* 161 */       if (worldIn.provider instanceof net.minecraft.world.WorldProviderEnd && var5 == Blocks.bedrock)
/*     */       {
/* 163 */         var6 = true;
/*     */       }
/*     */       
/* 166 */       if (!var6 && worldIn.isRaining() && func_176537_d(worldIn, pos)) {
/*     */         
/* 168 */         worldIn.setBlockToAir(pos);
/*     */       }
/*     */       else {
/*     */         
/* 172 */         int var7 = ((Integer)state.getValue((IProperty)field_176543_a)).intValue();
/*     */         
/* 174 */         if (var7 < 15) {
/*     */           
/* 176 */           state = state.withProperty((IProperty)field_176543_a, Integer.valueOf(var7 + rand.nextInt(3) / 2));
/* 177 */           worldIn.setBlockState(pos, state, 4);
/*     */         } 
/*     */         
/* 180 */         worldIn.scheduleUpdate(pos, this, tickRate(worldIn) + rand.nextInt(10));
/*     */         
/* 182 */         if (!var6) {
/*     */           
/* 184 */           if (!func_176533_e(worldIn, pos)) {
/*     */             
/* 186 */             if (!World.doesBlockHaveSolidTopSurface((IBlockAccess)worldIn, pos.offsetDown()) || var7 > 3)
/*     */             {
/* 188 */               worldIn.setBlockToAir(pos);
/*     */             }
/*     */             
/*     */             return;
/*     */           } 
/*     */           
/* 194 */           if (!func_176535_e((IBlockAccess)worldIn, pos.offsetDown()) && var7 == 15 && rand.nextInt(4) == 0) {
/*     */             
/* 196 */             worldIn.setBlockToAir(pos);
/*     */             
/*     */             return;
/*     */           } 
/*     */         } 
/* 201 */         boolean var8 = worldIn.func_180502_D(pos);
/* 202 */         byte var9 = 0;
/*     */         
/* 204 */         if (var8)
/*     */         {
/* 206 */           var9 = -50;
/*     */         }
/*     */         
/* 209 */         func_176536_a(worldIn, pos.offsetEast(), 300 + var9, rand, var7);
/* 210 */         func_176536_a(worldIn, pos.offsetWest(), 300 + var9, rand, var7);
/* 211 */         func_176536_a(worldIn, pos.offsetDown(), 250 + var9, rand, var7);
/* 212 */         func_176536_a(worldIn, pos.offsetUp(), 250 + var9, rand, var7);
/* 213 */         func_176536_a(worldIn, pos.offsetNorth(), 300 + var9, rand, var7);
/* 214 */         func_176536_a(worldIn, pos.offsetSouth(), 300 + var9, rand, var7);
/*     */         
/* 216 */         for (int var10 = -1; var10 <= 1; var10++) {
/*     */           
/* 218 */           for (int var11 = -1; var11 <= 1; var11++) {
/*     */             
/* 220 */             for (int var12 = -1; var12 <= 4; var12++) {
/*     */               
/* 222 */               if (var10 != 0 || var12 != 0 || var11 != 0) {
/*     */                 
/* 224 */                 int var13 = 100;
/*     */                 
/* 226 */                 if (var12 > 1)
/*     */                 {
/* 228 */                   var13 += (var12 - 1) * 100;
/*     */                 }
/*     */                 
/* 231 */                 BlockPos var14 = pos.add(var10, var12, var11);
/* 232 */                 int var15 = func_176538_m(worldIn, var14);
/*     */                 
/* 234 */                 if (var15 > 0) {
/*     */                   
/* 236 */                   int var16 = (var15 + 40 + worldIn.getDifficulty().getDifficultyId() * 7) / (var7 + 30);
/*     */                   
/* 238 */                   if (var8)
/*     */                   {
/* 240 */                     var16 /= 2;
/*     */                   }
/*     */                   
/* 243 */                   if (var16 > 0 && rand.nextInt(var13) <= var16 && (!worldIn.isRaining() || !func_176537_d(worldIn, var14))) {
/*     */                     
/* 245 */                     int var17 = var7 + rand.nextInt(5) / 4;
/*     */                     
/* 247 */                     if (var17 > 15)
/*     */                     {
/* 249 */                       var17 = 15;
/*     */                     }
/*     */                     
/* 252 */                     worldIn.setBlockState(var14, state.withProperty((IProperty)field_176543_a, Integer.valueOf(var17)), 3);
/*     */                   } 
/*     */                 } 
/*     */               } 
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean func_176537_d(World worldIn, BlockPos p_176537_2_) {
/* 265 */     return !(!worldIn.func_175727_C(p_176537_2_) && !worldIn.func_175727_C(p_176537_2_.offsetWest()) && !worldIn.func_175727_C(p_176537_2_.offsetEast()) && !worldIn.func_175727_C(p_176537_2_.offsetNorth()) && !worldIn.func_175727_C(p_176537_2_.offsetSouth()));
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean requiresUpdates() {
/* 270 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   private int func_176532_c(Block p_176532_1_) {
/* 275 */     Integer var2 = (Integer)this.field_149848_b.get(p_176532_1_);
/* 276 */     return (var2 == null) ? 0 : var2.intValue();
/*     */   }
/*     */ 
/*     */   
/*     */   private int func_176534_d(Block p_176534_1_) {
/* 281 */     Integer var2 = (Integer)this.field_149849_a.get(p_176534_1_);
/* 282 */     return (var2 == null) ? 0 : var2.intValue();
/*     */   }
/*     */ 
/*     */   
/*     */   private void func_176536_a(World worldIn, BlockPos p_176536_2_, int p_176536_3_, Random p_176536_4_, int p_176536_5_) {
/* 287 */     int var6 = func_176532_c(worldIn.getBlockState(p_176536_2_).getBlock());
/*     */     
/* 289 */     if (p_176536_4_.nextInt(p_176536_3_) < var6) {
/*     */       
/* 291 */       IBlockState var7 = worldIn.getBlockState(p_176536_2_);
/*     */       
/* 293 */       if (p_176536_4_.nextInt(p_176536_5_ + 10) < 5 && !worldIn.func_175727_C(p_176536_2_)) {
/*     */         
/* 295 */         int var8 = p_176536_5_ + p_176536_4_.nextInt(5) / 4;
/*     */         
/* 297 */         if (var8 > 15)
/*     */         {
/* 299 */           var8 = 15;
/*     */         }
/*     */         
/* 302 */         worldIn.setBlockState(p_176536_2_, getDefaultState().withProperty((IProperty)field_176543_a, Integer.valueOf(var8)), 3);
/*     */       }
/*     */       else {
/*     */         
/* 306 */         worldIn.setBlockToAir(p_176536_2_);
/*     */       } 
/*     */       
/* 309 */       if (var7.getBlock() == Blocks.tnt)
/*     */       {
/* 311 */         Blocks.tnt.onBlockDestroyedByPlayer(worldIn, p_176536_2_, var7.withProperty((IProperty)BlockTNT.field_176246_a, Boolean.valueOf(true)));
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean func_176533_e(World worldIn, BlockPos p_176533_2_) {
/* 318 */     EnumFacing[] var3 = EnumFacing.values();
/* 319 */     int var4 = var3.length;
/*     */     
/* 321 */     for (int var5 = 0; var5 < var4; var5++) {
/*     */       
/* 323 */       EnumFacing var6 = var3[var5];
/*     */       
/* 325 */       if (func_176535_e((IBlockAccess)worldIn, p_176533_2_.offset(var6)))
/*     */       {
/* 327 */         return true;
/*     */       }
/*     */     } 
/*     */     
/* 331 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   private int func_176538_m(World worldIn, BlockPos p_176538_2_) {
/* 336 */     if (!worldIn.isAirBlock(p_176538_2_))
/*     */     {
/* 338 */       return 0;
/*     */     }
/*     */ 
/*     */     
/* 342 */     int var3 = 0;
/* 343 */     EnumFacing[] var4 = EnumFacing.values();
/* 344 */     int var5 = var4.length;
/*     */     
/* 346 */     for (int var6 = 0; var6 < var5; var6++) {
/*     */       
/* 348 */       EnumFacing var7 = var4[var6];
/* 349 */       var3 = Math.max(func_176534_d(worldIn.getBlockState(p_176538_2_.offset(var7)).getBlock()), var3);
/*     */     } 
/*     */     
/* 352 */     return var3;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isCollidable() {
/* 361 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_176535_e(IBlockAccess p_176535_1_, BlockPos p_176535_2_) {
/* 366 */     return (func_176534_d(p_176535_1_.getBlockState(p_176535_2_).getBlock()) > 0);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canPlaceBlockAt(World worldIn, BlockPos pos) {
/* 371 */     return !(!World.doesBlockHaveSolidTopSurface((IBlockAccess)worldIn, pos.offsetDown()) && !func_176533_e(worldIn, pos));
/*     */   }
/*     */ 
/*     */   
/*     */   public void onNeighborBlockChange(World worldIn, BlockPos pos, IBlockState state, Block neighborBlock) {
/* 376 */     if (!World.doesBlockHaveSolidTopSurface((IBlockAccess)worldIn, pos.offsetDown()) && !func_176533_e(worldIn, pos))
/*     */     {
/* 378 */       worldIn.setBlockToAir(pos);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state) {
/* 384 */     if (worldIn.provider.getDimensionId() > 0 || !Blocks.portal.func_176548_d(worldIn, pos))
/*     */     {
/* 386 */       if (!World.doesBlockHaveSolidTopSurface((IBlockAccess)worldIn, pos.offsetDown()) && !func_176533_e(worldIn, pos)) {
/*     */         
/* 388 */         worldIn.setBlockToAir(pos);
/*     */       }
/*     */       else {
/*     */         
/* 392 */         worldIn.scheduleUpdate(pos, this, tickRate(worldIn) + worldIn.rand.nextInt(10));
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void randomDisplayTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
/* 399 */     if (rand.nextInt(24) == 0)
/*     */     {
/* 401 */       worldIn.playSound((pos.getX() + 0.5F), (pos.getY() + 0.5F), (pos.getZ() + 0.5F), "fire.fire", 1.0F + rand.nextFloat(), rand.nextFloat() * 0.7F + 0.3F, false);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 409 */     if (!World.doesBlockHaveSolidTopSurface((IBlockAccess)worldIn, pos.offsetDown()) && !Blocks.fire.func_176535_e((IBlockAccess)worldIn, pos.offsetDown())) {
/*     */       
/* 411 */       if (Blocks.fire.func_176535_e((IBlockAccess)worldIn, pos.offsetWest()))
/*     */       {
/* 413 */         for (int var5 = 0; var5 < 2; var5++) {
/*     */           
/* 415 */           double var6 = pos.getX() + rand.nextDouble() * 0.10000000149011612D;
/* 416 */           double var8 = pos.getY() + rand.nextDouble();
/* 417 */           double var10 = pos.getZ() + rand.nextDouble();
/* 418 */           worldIn.spawnParticle(EnumParticleTypes.SMOKE_LARGE, var6, var8, var10, 0.0D, 0.0D, 0.0D, new int[0]);
/*     */         } 
/*     */       }
/*     */       
/* 422 */       if (Blocks.fire.func_176535_e((IBlockAccess)worldIn, pos.offsetEast()))
/*     */       {
/* 424 */         for (int var5 = 0; var5 < 2; var5++) {
/*     */           
/* 426 */           double var6 = (pos.getX() + 1) - rand.nextDouble() * 0.10000000149011612D;
/* 427 */           double var8 = pos.getY() + rand.nextDouble();
/* 428 */           double var10 = pos.getZ() + rand.nextDouble();
/* 429 */           worldIn.spawnParticle(EnumParticleTypes.SMOKE_LARGE, var6, var8, var10, 0.0D, 0.0D, 0.0D, new int[0]);
/*     */         } 
/*     */       }
/*     */       
/* 433 */       if (Blocks.fire.func_176535_e((IBlockAccess)worldIn, pos.offsetNorth()))
/*     */       {
/* 435 */         for (int var5 = 0; var5 < 2; var5++) {
/*     */           
/* 437 */           double var6 = pos.getX() + rand.nextDouble();
/* 438 */           double var8 = pos.getY() + rand.nextDouble();
/* 439 */           double var10 = pos.getZ() + rand.nextDouble() * 0.10000000149011612D;
/* 440 */           worldIn.spawnParticle(EnumParticleTypes.SMOKE_LARGE, var6, var8, var10, 0.0D, 0.0D, 0.0D, new int[0]);
/*     */         } 
/*     */       }
/*     */       
/* 444 */       if (Blocks.fire.func_176535_e((IBlockAccess)worldIn, pos.offsetSouth()))
/*     */       {
/* 446 */         for (int var5 = 0; var5 < 2; var5++) {
/*     */           
/* 448 */           double var6 = pos.getX() + rand.nextDouble();
/* 449 */           double var8 = pos.getY() + rand.nextDouble();
/* 450 */           double var10 = (pos.getZ() + 1) - rand.nextDouble() * 0.10000000149011612D;
/* 451 */           worldIn.spawnParticle(EnumParticleTypes.SMOKE_LARGE, var6, var8, var10, 0.0D, 0.0D, 0.0D, new int[0]);
/*     */         } 
/*     */       }
/*     */       
/* 455 */       if (Blocks.fire.func_176535_e((IBlockAccess)worldIn, pos.offsetUp()))
/*     */       {
/* 457 */         for (int var5 = 0; var5 < 2; var5++)
/*     */         {
/* 459 */           double var6 = pos.getX() + rand.nextDouble();
/* 460 */           double var8 = (pos.getY() + 1) - rand.nextDouble() * 0.10000000149011612D;
/* 461 */           double var10 = pos.getZ() + rand.nextDouble();
/* 462 */           worldIn.spawnParticle(EnumParticleTypes.SMOKE_LARGE, var6, var8, var10, 0.0D, 0.0D, 0.0D, new int[0]);
/*     */         }
/*     */       
/*     */       }
/*     */     } else {
/*     */       
/* 468 */       for (int var5 = 0; var5 < 3; var5++) {
/*     */         
/* 470 */         double var6 = pos.getX() + rand.nextDouble();
/* 471 */         double var8 = pos.getY() + rand.nextDouble() * 0.5D + 0.5D;
/* 472 */         double var10 = pos.getZ() + rand.nextDouble();
/* 473 */         worldIn.spawnParticle(EnumParticleTypes.SMOKE_LARGE, var6, var8, var10, 0.0D, 0.0D, 0.0D, new int[0]);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MapColor getMapColor(IBlockState state) {
/* 483 */     return MapColor.tntColor;
/*     */   }
/*     */ 
/*     */   
/*     */   public EnumWorldBlockLayer getBlockLayer() {
/* 488 */     return EnumWorldBlockLayer.CUTOUT;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IBlockState getStateFromMeta(int meta) {
/* 496 */     return getDefaultState().withProperty((IProperty)field_176543_a, Integer.valueOf(meta));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getMetaFromState(IBlockState state) {
/* 504 */     return ((Integer)state.getValue((IProperty)field_176543_a)).intValue();
/*     */   }
/*     */ 
/*     */   
/*     */   protected BlockState createBlockState() {
/* 509 */     return new BlockState(this, new IProperty[] { (IProperty)field_176543_a, (IProperty)field_176545_N, (IProperty)field_176546_O, (IProperty)field_176541_P, (IProperty)field_176539_Q, (IProperty)field_176542_R, (IProperty)field_176540_b, (IProperty)field_176544_M });
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\block\BlockFire.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */