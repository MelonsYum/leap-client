/*     */ package net.minecraft.block;
/*     */ 
/*     */ import java.util.Iterator;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.block.properties.IProperty;
/*     */ import net.minecraft.block.properties.PropertyInteger;
/*     */ import net.minecraft.block.state.BlockState;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.EnumParticleTypes;
/*     */ import net.minecraft.util.EnumWorldBlockLayer;
/*     */ import net.minecraft.util.Vec3;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.biome.BiomeColorHelper;
/*     */ 
/*     */ public abstract class BlockLiquid
/*     */   extends Block {
/*  25 */   public static final PropertyInteger LEVEL = PropertyInteger.create("level", 0, 15);
/*     */   
/*     */   private static final String __OBFID = "CL_00000265";
/*     */   
/*     */   protected BlockLiquid(Material p_i45413_1_) {
/*  30 */     super(p_i45413_1_);
/*  31 */     setDefaultState(this.blockState.getBaseState().withProperty((IProperty)LEVEL, Integer.valueOf(0)));
/*  32 */     setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
/*  33 */     setTickRandomly(true);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isPassable(IBlockAccess blockAccess, BlockPos pos) {
/*  38 */     return (this.blockMaterial != Material.lava);
/*     */   }
/*     */ 
/*     */   
/*     */   public int colorMultiplier(IBlockAccess worldIn, BlockPos pos, int renderPass) {
/*  43 */     return (this.blockMaterial == Material.water) ? BiomeColorHelper.func_180288_c(worldIn, pos) : 16777215;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static float getLiquidHeightPercent(int p_149801_0_) {
/*  51 */     if (p_149801_0_ >= 8)
/*     */     {
/*  53 */       p_149801_0_ = 0;
/*     */     }
/*     */     
/*  56 */     return (p_149801_0_ + 1) / 9.0F;
/*     */   }
/*     */ 
/*     */   
/*     */   protected int func_176362_e(IBlockAccess p_176362_1_, BlockPos p_176362_2_) {
/*  61 */     return (p_176362_1_.getBlockState(p_176362_2_).getBlock().getMaterial() == this.blockMaterial) ? ((Integer)p_176362_1_.getBlockState(p_176362_2_).getValue((IProperty)LEVEL)).intValue() : -1;
/*     */   }
/*     */ 
/*     */   
/*     */   protected int func_176366_f(IBlockAccess p_176366_1_, BlockPos p_176366_2_) {
/*  66 */     int var3 = func_176362_e(p_176366_1_, p_176366_2_);
/*  67 */     return (var3 >= 8) ? 0 : var3;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isFullCube() {
/*  72 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isOpaqueCube() {
/*  77 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canCollideCheck(IBlockState state, boolean p_176209_2_) {
/*  82 */     return (p_176209_2_ && ((Integer)state.getValue((IProperty)LEVEL)).intValue() == 0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isBlockSolid(IBlockAccess worldIn, BlockPos pos, EnumFacing side) {
/*  90 */     Material var4 = worldIn.getBlockState(pos).getBlock().getMaterial();
/*  91 */     return (var4 == this.blockMaterial) ? false : ((side == EnumFacing.UP) ? true : ((var4 == Material.ice) ? false : super.isBlockSolid(worldIn, pos, side)));
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean shouldSideBeRendered(IBlockAccess worldIn, BlockPos pos, EnumFacing side) {
/*  96 */     return (worldIn.getBlockState(pos).getBlock().getMaterial() == this.blockMaterial) ? false : ((side == EnumFacing.UP) ? true : super.shouldSideBeRendered(worldIn, pos, side));
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_176364_g(IBlockAccess p_176364_1_, BlockPos p_176364_2_) {
/* 101 */     for (int var3 = -1; var3 <= 1; var3++) {
/*     */       
/* 103 */       for (int var4 = -1; var4 <= 1; var4++) {
/*     */         
/* 105 */         IBlockState var5 = p_176364_1_.getBlockState(p_176364_2_.add(var3, 0, var4));
/* 106 */         Block var6 = var5.getBlock();
/* 107 */         Material var7 = var6.getMaterial();
/*     */         
/* 109 */         if (var7 != this.blockMaterial && !var6.isFullBlock())
/*     */         {
/* 111 */           return true;
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 116 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public AxisAlignedBB getCollisionBoundingBox(World worldIn, BlockPos pos, IBlockState state) {
/* 121 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getRenderType() {
/* 129 */     return 1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Item getItemDropped(IBlockState state, Random rand, int fortune) {
/* 139 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int quantityDropped(Random random) {
/* 147 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   protected Vec3 func_180687_h(IBlockAccess p_180687_1_, BlockPos p_180687_2_) {
/* 152 */     Vec3 var3 = new Vec3(0.0D, 0.0D, 0.0D);
/* 153 */     int var4 = func_176366_f(p_180687_1_, p_180687_2_);
/* 154 */     Iterator<EnumFacing> var5 = EnumFacing.Plane.HORIZONTAL.iterator();
/*     */ 
/*     */ 
/*     */     
/* 158 */     while (var5.hasNext()) {
/*     */       
/* 160 */       EnumFacing var6 = var5.next();
/* 161 */       BlockPos var7 = p_180687_2_.offset(var6);
/* 162 */       int var8 = func_176366_f(p_180687_1_, var7);
/*     */ 
/*     */       
/* 165 */       if (var8 < 0) {
/*     */         
/* 167 */         if (!p_180687_1_.getBlockState(var7).getBlock().getMaterial().blocksMovement()) {
/*     */           
/* 169 */           var8 = func_176366_f(p_180687_1_, var7.offsetDown());
/*     */           
/* 171 */           if (var8 >= 0) {
/*     */             
/* 173 */             int var9 = var8 - var4 - 8;
/* 174 */             var3 = var3.addVector(((var7.getX() - p_180687_2_.getX()) * var9), ((var7.getY() - p_180687_2_.getY()) * var9), ((var7.getZ() - p_180687_2_.getZ()) * var9));
/*     */           } 
/*     */         }  continue;
/*     */       } 
/* 178 */       if (var8 >= 0) {
/*     */         
/* 180 */         int var9 = var8 - var4;
/* 181 */         var3 = var3.addVector(((var7.getX() - p_180687_2_.getX()) * var9), ((var7.getY() - p_180687_2_.getY()) * var9), ((var7.getZ() - p_180687_2_.getZ()) * var9));
/*     */       } 
/*     */     } 
/*     */     
/* 185 */     if (((Integer)p_180687_1_.getBlockState(p_180687_2_).getValue((IProperty)LEVEL)).intValue() >= 8) {
/*     */       
/* 187 */       var5 = EnumFacing.Plane.HORIZONTAL.iterator();
/*     */       
/* 189 */       while (var5.hasNext()) {
/*     */         
/* 191 */         EnumFacing var6 = var5.next();
/* 192 */         BlockPos var7 = p_180687_2_.offset(var6);
/*     */         
/* 194 */         if (isBlockSolid(p_180687_1_, var7, var6) || isBlockSolid(p_180687_1_, var7.offsetUp(), var6)) {
/*     */           
/* 196 */           var3 = var3.normalize().addVector(0.0D, -6.0D, 0.0D);
/*     */           
/*     */           break;
/*     */         } 
/*     */       } 
/*     */     } 
/* 202 */     return var3.normalize();
/*     */   }
/*     */ 
/*     */   
/*     */   public Vec3 modifyAcceleration(World worldIn, BlockPos pos, Entity entityIn, Vec3 motion) {
/* 207 */     return motion.add(func_180687_h((IBlockAccess)worldIn, pos));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int tickRate(World worldIn) {
/* 215 */     return (this.blockMaterial == Material.water) ? 5 : ((this.blockMaterial == Material.lava) ? (worldIn.provider.getHasNoSky() ? 10 : 30) : 0);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getMixedBrightnessForBlock(IBlockAccess worldIn, BlockPos pos) {
/* 220 */     int var3 = worldIn.getCombinedLight(pos, 0);
/* 221 */     int var4 = worldIn.getCombinedLight(pos.offsetUp(), 0);
/* 222 */     int var5 = var3 & 0xFF;
/* 223 */     int var6 = var4 & 0xFF;
/* 224 */     int var7 = var3 >> 16 & 0xFF;
/* 225 */     int var8 = var4 >> 16 & 0xFF;
/* 226 */     return ((var5 > var6) ? var5 : var6) | ((var7 > var8) ? var7 : var8) << 16;
/*     */   }
/*     */ 
/*     */   
/*     */   public EnumWorldBlockLayer getBlockLayer() {
/* 231 */     return (this.blockMaterial == Material.water) ? EnumWorldBlockLayer.TRANSLUCENT : EnumWorldBlockLayer.SOLID;
/*     */   }
/*     */ 
/*     */   
/*     */   public void randomDisplayTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
/* 236 */     double var5 = pos.getX();
/* 237 */     double var7 = pos.getY();
/* 238 */     double var9 = pos.getZ();
/*     */     
/* 240 */     if (this.blockMaterial == Material.water) {
/*     */       
/* 242 */       int var11 = ((Integer)state.getValue((IProperty)LEVEL)).intValue();
/*     */       
/* 244 */       if (var11 > 0 && var11 < 8) {
/*     */         
/* 246 */         if (rand.nextInt(64) == 0)
/*     */         {
/* 248 */           worldIn.playSound(var5 + 0.5D, var7 + 0.5D, var9 + 0.5D, "liquid.water", rand.nextFloat() * 0.25F + 0.75F, rand.nextFloat() * 1.0F + 0.5F, false);
/*     */         }
/*     */       }
/* 251 */       else if (rand.nextInt(10) == 0) {
/*     */         
/* 253 */         worldIn.spawnParticle(EnumParticleTypes.SUSPENDED, var5 + rand.nextFloat(), var7 + rand.nextFloat(), var9 + rand.nextFloat(), 0.0D, 0.0D, 0.0D, new int[0]);
/*     */       } 
/*     */     } 
/*     */     
/* 257 */     if (this.blockMaterial == Material.lava && worldIn.getBlockState(pos.offsetUp()).getBlock().getMaterial() == Material.air && !worldIn.getBlockState(pos.offsetUp()).getBlock().isOpaqueCube()) {
/*     */       
/* 259 */       if (rand.nextInt(100) == 0) {
/*     */         
/* 261 */         double var18 = var5 + rand.nextFloat();
/* 262 */         double var13 = var7 + this.maxY;
/* 263 */         double var15 = var9 + rand.nextFloat();
/* 264 */         worldIn.spawnParticle(EnumParticleTypes.LAVA, var18, var13, var15, 0.0D, 0.0D, 0.0D, new int[0]);
/* 265 */         worldIn.playSound(var18, var13, var15, "liquid.lavapop", 0.2F + rand.nextFloat() * 0.2F, 0.9F + rand.nextFloat() * 0.15F, false);
/*     */       } 
/*     */       
/* 268 */       if (rand.nextInt(200) == 0)
/*     */       {
/* 270 */         worldIn.playSound(var5, var7, var9, "liquid.lava", 0.2F + rand.nextFloat() * 0.2F, 0.9F + rand.nextFloat() * 0.15F, false);
/*     */       }
/*     */     } 
/*     */     
/* 274 */     if (rand.nextInt(10) == 0 && World.doesBlockHaveSolidTopSurface((IBlockAccess)worldIn, pos.offsetDown())) {
/*     */       
/* 276 */       Material var19 = worldIn.getBlockState(pos.offsetDown(2)).getBlock().getMaterial();
/*     */       
/* 278 */       if (!var19.blocksMovement() && !var19.isLiquid()) {
/*     */         
/* 280 */         double var12 = var5 + rand.nextFloat();
/* 281 */         double var14 = var7 - 1.05D;
/* 282 */         double var16 = var9 + rand.nextFloat();
/*     */         
/* 284 */         if (this.blockMaterial == Material.water) {
/*     */           
/* 286 */           worldIn.spawnParticle(EnumParticleTypes.DRIP_WATER, var12, var14, var16, 0.0D, 0.0D, 0.0D, new int[0]);
/*     */         }
/*     */         else {
/*     */           
/* 290 */           worldIn.spawnParticle(EnumParticleTypes.DRIP_LAVA, var12, var14, var16, 0.0D, 0.0D, 0.0D, new int[0]);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static double func_180689_a(IBlockAccess p_180689_0_, BlockPos p_180689_1_, Material p_180689_2_) {
/* 298 */     Vec3 var3 = getDynamicLiquidForMaterial(p_180689_2_).func_180687_h(p_180689_0_, p_180689_1_);
/* 299 */     return (var3.xCoord == 0.0D && var3.zCoord == 0.0D) ? -1000.0D : (Math.atan2(var3.zCoord, var3.xCoord) - 1.5707963267948966D);
/*     */   }
/*     */ 
/*     */   
/*     */   public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state) {
/* 304 */     func_176365_e(worldIn, pos, state);
/*     */   }
/*     */ 
/*     */   
/*     */   public void onNeighborBlockChange(World worldIn, BlockPos pos, IBlockState state, Block neighborBlock) {
/* 309 */     func_176365_e(worldIn, pos, state);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_176365_e(World worldIn, BlockPos p_176365_2_, IBlockState p_176365_3_) {
/* 314 */     if (this.blockMaterial == Material.lava) {
/*     */       
/* 316 */       boolean var4 = false;
/* 317 */       EnumFacing[] var5 = EnumFacing.values();
/* 318 */       int var6 = var5.length;
/*     */       
/* 320 */       for (int var7 = 0; var7 < var6; var7++) {
/*     */         
/* 322 */         EnumFacing var8 = var5[var7];
/*     */         
/* 324 */         if (var8 != EnumFacing.DOWN && worldIn.getBlockState(p_176365_2_.offset(var8)).getBlock().getMaterial() == Material.water) {
/*     */           
/* 326 */           var4 = true;
/*     */           
/*     */           break;
/*     */         } 
/*     */       } 
/* 331 */       if (var4) {
/*     */         
/* 333 */         Integer var9 = (Integer)p_176365_3_.getValue((IProperty)LEVEL);
/*     */         
/* 335 */         if (var9.intValue() == 0) {
/*     */           
/* 337 */           worldIn.setBlockState(p_176365_2_, Blocks.obsidian.getDefaultState());
/* 338 */           func_180688_d(worldIn, p_176365_2_);
/* 339 */           return true;
/*     */         } 
/*     */         
/* 342 */         if (var9.intValue() <= 4) {
/*     */           
/* 344 */           worldIn.setBlockState(p_176365_2_, Blocks.cobblestone.getDefaultState());
/* 345 */           func_180688_d(worldIn, p_176365_2_);
/* 346 */           return true;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 351 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void func_180688_d(World worldIn, BlockPos p_180688_2_) {
/* 356 */     double var3 = p_180688_2_.getX();
/* 357 */     double var5 = p_180688_2_.getY();
/* 358 */     double var7 = p_180688_2_.getZ();
/* 359 */     worldIn.playSoundEffect(var3 + 0.5D, var5 + 0.5D, var7 + 0.5D, "random.fizz", 0.5F, 2.6F + (worldIn.rand.nextFloat() - worldIn.rand.nextFloat()) * 0.8F);
/*     */     
/* 361 */     for (int var9 = 0; var9 < 8; var9++)
/*     */     {
/* 363 */       worldIn.spawnParticle(EnumParticleTypes.SMOKE_LARGE, var3 + Math.random(), var5 + 1.2D, var7 + Math.random(), 0.0D, 0.0D, 0.0D, new int[0]);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IBlockState getStateFromMeta(int meta) {
/* 372 */     return getDefaultState().withProperty((IProperty)LEVEL, Integer.valueOf(meta));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getMetaFromState(IBlockState state) {
/* 380 */     return ((Integer)state.getValue((IProperty)LEVEL)).intValue();
/*     */   }
/*     */ 
/*     */   
/*     */   protected BlockState createBlockState() {
/* 385 */     return new BlockState(this, new IProperty[] { (IProperty)LEVEL });
/*     */   }
/*     */ 
/*     */   
/*     */   public static BlockDynamicLiquid getDynamicLiquidForMaterial(Material p_176361_0_) {
/* 390 */     if (p_176361_0_ == Material.water)
/*     */     {
/* 392 */       return Blocks.flowing_water;
/*     */     }
/* 394 */     if (p_176361_0_ == Material.lava)
/*     */     {
/* 396 */       return Blocks.flowing_lava;
/*     */     }
/*     */ 
/*     */     
/* 400 */     throw new IllegalArgumentException("Invalid material");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static BlockStaticLiquid getStaticLiquidForMaterial(Material p_176363_0_) {
/* 406 */     if (p_176363_0_ == Material.water)
/*     */     {
/* 408 */       return Blocks.water;
/*     */     }
/* 410 */     if (p_176363_0_ == Material.lava)
/*     */     {
/* 412 */       return Blocks.lava;
/*     */     }
/*     */ 
/*     */     
/* 416 */     throw new IllegalArgumentException("Invalid material");
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\block\BlockLiquid.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */