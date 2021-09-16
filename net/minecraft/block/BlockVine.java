/*     */ package net.minecraft.block;
/*     */ 
/*     */ import java.util.Iterator;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.block.properties.IProperty;
/*     */ import net.minecraft.block.properties.PropertyBool;
/*     */ import net.minecraft.block.state.BlockState;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.creativetab.CreativeTabs;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.stats.StatList;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.EnumWorldBlockLayer;
/*     */ import net.minecraft.world.ColorizerFoliage;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class BlockVine
/*     */   extends Block {
/*  29 */   public static final PropertyBool field_176277_a = PropertyBool.create("up");
/*  30 */   public static final PropertyBool field_176273_b = PropertyBool.create("north");
/*  31 */   public static final PropertyBool field_176278_M = PropertyBool.create("east");
/*  32 */   public static final PropertyBool field_176279_N = PropertyBool.create("south");
/*  33 */   public static final PropertyBool field_176280_O = PropertyBool.create("west");
/*  34 */   public static final PropertyBool[] field_176274_P = new PropertyBool[] { field_176277_a, field_176273_b, field_176279_N, field_176280_O, field_176278_M };
/*  35 */   public static final int field_176272_Q = func_176270_b(EnumFacing.SOUTH);
/*  36 */   public static final int field_176276_R = func_176270_b(EnumFacing.NORTH);
/*  37 */   public static final int field_176275_S = func_176270_b(EnumFacing.EAST);
/*  38 */   public static final int field_176271_T = func_176270_b(EnumFacing.WEST);
/*     */   
/*     */   private static final String __OBFID = "CL_00000330";
/*     */   
/*     */   public BlockVine() {
/*  43 */     super(Material.vine);
/*  44 */     setDefaultState(this.blockState.getBaseState().withProperty((IProperty)field_176277_a, Boolean.valueOf(false)).withProperty((IProperty)field_176273_b, Boolean.valueOf(false)).withProperty((IProperty)field_176278_M, Boolean.valueOf(false)).withProperty((IProperty)field_176279_N, Boolean.valueOf(false)).withProperty((IProperty)field_176280_O, Boolean.valueOf(false)));
/*  45 */     setTickRandomly(true);
/*  46 */     setCreativeTab(CreativeTabs.tabDecorations);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
/*  55 */     return state.withProperty((IProperty)field_176277_a, Boolean.valueOf(worldIn.getBlockState(pos.offsetUp()).getBlock().isSolidFullCube()));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setBlockBoundsForItemRender() {
/*  63 */     setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isOpaqueCube() {
/*  68 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isFullCube() {
/*  73 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isReplaceable(World worldIn, BlockPos pos) {
/*  81 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setBlockBoundsBasedOnState(IBlockAccess access, BlockPos pos) {
/*  86 */     float var3 = 0.0625F;
/*  87 */     float var4 = 1.0F;
/*  88 */     float var5 = 1.0F;
/*  89 */     float var6 = 1.0F;
/*  90 */     float var7 = 0.0F;
/*  91 */     float var8 = 0.0F;
/*  92 */     float var9 = 0.0F;
/*  93 */     boolean var10 = false;
/*     */     
/*  95 */     if (((Boolean)access.getBlockState(pos).getValue((IProperty)field_176280_O)).booleanValue()) {
/*     */       
/*  97 */       var7 = Math.max(var7, 0.0625F);
/*  98 */       var4 = 0.0F;
/*  99 */       var5 = 0.0F;
/* 100 */       var8 = 1.0F;
/* 101 */       var6 = 0.0F;
/* 102 */       var9 = 1.0F;
/* 103 */       var10 = true;
/*     */     } 
/*     */     
/* 106 */     if (((Boolean)access.getBlockState(pos).getValue((IProperty)field_176278_M)).booleanValue()) {
/*     */       
/* 108 */       var4 = Math.min(var4, 0.9375F);
/* 109 */       var7 = 1.0F;
/* 110 */       var5 = 0.0F;
/* 111 */       var8 = 1.0F;
/* 112 */       var6 = 0.0F;
/* 113 */       var9 = 1.0F;
/* 114 */       var10 = true;
/*     */     } 
/*     */     
/* 117 */     if (((Boolean)access.getBlockState(pos).getValue((IProperty)field_176273_b)).booleanValue()) {
/*     */       
/* 119 */       var9 = Math.max(var9, 0.0625F);
/* 120 */       var6 = 0.0F;
/* 121 */       var4 = 0.0F;
/* 122 */       var7 = 1.0F;
/* 123 */       var5 = 0.0F;
/* 124 */       var8 = 1.0F;
/* 125 */       var10 = true;
/*     */     } 
/*     */     
/* 128 */     if (((Boolean)access.getBlockState(pos).getValue((IProperty)field_176279_N)).booleanValue()) {
/*     */       
/* 130 */       var6 = Math.min(var6, 0.9375F);
/* 131 */       var9 = 1.0F;
/* 132 */       var4 = 0.0F;
/* 133 */       var7 = 1.0F;
/* 134 */       var5 = 0.0F;
/* 135 */       var8 = 1.0F;
/* 136 */       var10 = true;
/*     */     } 
/*     */     
/* 139 */     if (!var10 && func_150093_a(access.getBlockState(pos.offsetUp()).getBlock())) {
/*     */       
/* 141 */       var5 = Math.min(var5, 0.9375F);
/* 142 */       var8 = 1.0F;
/* 143 */       var4 = 0.0F;
/* 144 */       var7 = 1.0F;
/* 145 */       var6 = 0.0F;
/* 146 */       var9 = 1.0F;
/*     */     } 
/*     */     
/* 149 */     setBlockBounds(var4, var5, var6, var7, var8, var9);
/*     */   }
/*     */ 
/*     */   
/*     */   public AxisAlignedBB getCollisionBoundingBox(World worldIn, BlockPos pos, IBlockState state) {
/* 154 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canPlaceBlockOnSide(World worldIn, BlockPos pos, EnumFacing side) {
/* 162 */     switch (SwitchEnumFacing.field_177057_a[side.ordinal()]) {
/*     */       
/*     */       case 1:
/* 165 */         return func_150093_a(worldIn.getBlockState(pos.offsetUp()).getBlock());
/*     */       
/*     */       case 2:
/*     */       case 3:
/*     */       case 4:
/*     */       case 5:
/* 171 */         return func_150093_a(worldIn.getBlockState(pos.offset(side.getOpposite())).getBlock());
/*     */     } 
/*     */     
/* 174 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean func_150093_a(Block p_150093_1_) {
/* 180 */     return (p_150093_1_.isFullCube() && p_150093_1_.blockMaterial.blocksMovement());
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean func_176269_e(World worldIn, BlockPos p_176269_2_, IBlockState p_176269_3_) {
/* 185 */     IBlockState var4 = p_176269_3_;
/* 186 */     Iterator<EnumFacing> var5 = EnumFacing.Plane.HORIZONTAL.iterator();
/*     */     
/* 188 */     while (var5.hasNext()) {
/*     */       
/* 190 */       EnumFacing var6 = var5.next();
/* 191 */       PropertyBool var7 = func_176267_a(var6);
/*     */       
/* 193 */       if (((Boolean)p_176269_3_.getValue((IProperty)var7)).booleanValue() && !func_150093_a(worldIn.getBlockState(p_176269_2_.offset(var6)).getBlock())) {
/*     */         
/* 195 */         IBlockState var8 = worldIn.getBlockState(p_176269_2_.offsetUp());
/*     */         
/* 197 */         if (var8.getBlock() != this || !((Boolean)var8.getValue((IProperty)var7)).booleanValue())
/*     */         {
/* 199 */           p_176269_3_ = p_176269_3_.withProperty((IProperty)var7, Boolean.valueOf(false));
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 204 */     if (func_176268_d(p_176269_3_) == 0)
/*     */     {
/* 206 */       return false;
/*     */     }
/*     */ 
/*     */     
/* 210 */     if (var4 != p_176269_3_)
/*     */     {
/* 212 */       worldIn.setBlockState(p_176269_2_, p_176269_3_, 2);
/*     */     }
/*     */     
/* 215 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getBlockColor() {
/* 221 */     return ColorizerFoliage.getFoliageColorBasic();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getRenderColor(IBlockState state) {
/* 226 */     return ColorizerFoliage.getFoliageColorBasic();
/*     */   }
/*     */ 
/*     */   
/*     */   public int colorMultiplier(IBlockAccess worldIn, BlockPos pos, int renderPass) {
/* 231 */     return worldIn.getBiomeGenForCoords(pos).func_180625_c(pos);
/*     */   }
/*     */ 
/*     */   
/*     */   public void onNeighborBlockChange(World worldIn, BlockPos pos, IBlockState state, Block neighborBlock) {
/* 236 */     if (!worldIn.isRemote && !func_176269_e(worldIn, pos, state)) {
/*     */       
/* 238 */       dropBlockAsItem(worldIn, pos, state, 0);
/* 239 */       worldIn.setBlockToAir(pos);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
/* 245 */     if (!worldIn.isRemote)
/*     */     {
/* 247 */       if (worldIn.rand.nextInt(4) == 0) {
/*     */         
/* 249 */         byte var5 = 4;
/* 250 */         int var6 = 5;
/* 251 */         boolean var7 = false;
/*     */         
/*     */         int var8;
/* 254 */         label103: for (var8 = -var5; var8 <= var5; var8++) {
/*     */           
/* 256 */           for (int var9 = -var5; var9 <= var5; var9++) {
/*     */             
/* 258 */             for (int var10 = -1; var10 <= 1; var10++) {
/*     */               
/* 260 */               if (worldIn.getBlockState(pos.add(var8, var10, var9)).getBlock() == this) {
/*     */                 
/* 262 */                 var6--;
/*     */                 
/* 264 */                 if (var6 <= 0) {
/*     */                   
/* 266 */                   var7 = true;
/*     */                   
/*     */                   break label103;
/*     */                 } 
/*     */               } 
/*     */             } 
/*     */           } 
/*     */         } 
/* 274 */         EnumFacing var17 = EnumFacing.random(rand);
/*     */ 
/*     */         
/* 277 */         if (var17 == EnumFacing.UP && pos.getY() < 255 && worldIn.isAirBlock(pos.offsetUp())) {
/*     */           
/* 279 */           if (!var7)
/*     */           {
/* 281 */             IBlockState var19 = state;
/* 282 */             Iterator<EnumFacing> var22 = EnumFacing.Plane.HORIZONTAL.iterator();
/*     */             
/* 284 */             while (var22.hasNext()) {
/*     */               
/* 286 */               EnumFacing var23 = var22.next();
/*     */               
/* 288 */               if (rand.nextBoolean() || !func_150093_a(worldIn.getBlockState(pos.offset(var23).offsetUp()).getBlock()))
/*     */               {
/* 290 */                 var19 = var19.withProperty((IProperty)func_176267_a(var23), Boolean.valueOf(false));
/*     */               }
/*     */             } 
/*     */             
/* 294 */             if (((Boolean)var19.getValue((IProperty)field_176273_b)).booleanValue() || ((Boolean)var19.getValue((IProperty)field_176278_M)).booleanValue() || ((Boolean)var19.getValue((IProperty)field_176279_N)).booleanValue() || ((Boolean)var19.getValue((IProperty)field_176280_O)).booleanValue())
/*     */             {
/* 296 */               worldIn.setBlockState(pos.offsetUp(), var19, 2);
/*     */             
/*     */             }
/*     */           
/*     */           }
/*     */ 
/*     */         
/*     */         }
/* 304 */         else if (var17.getAxis().isHorizontal() && !((Boolean)state.getValue((IProperty)func_176267_a(var17))).booleanValue()) {
/*     */           
/* 306 */           if (!var7)
/*     */           {
/* 308 */             BlockPos var18 = pos.offset(var17);
/* 309 */             Block var21 = worldIn.getBlockState(var18).getBlock();
/*     */             
/* 311 */             if (var21.blockMaterial == Material.air) {
/*     */               
/* 313 */               EnumFacing var23 = var17.rotateY();
/* 314 */               EnumFacing var24 = var17.rotateYCCW();
/* 315 */               boolean var25 = ((Boolean)state.getValue((IProperty)func_176267_a(var23))).booleanValue();
/* 316 */               boolean var26 = ((Boolean)state.getValue((IProperty)func_176267_a(var24))).booleanValue();
/* 317 */               BlockPos var27 = var18.offset(var23);
/* 318 */               BlockPos var16 = var18.offset(var24);
/*     */               
/* 320 */               if (var25 && func_150093_a(worldIn.getBlockState(var27).getBlock()))
/*     */               {
/* 322 */                 worldIn.setBlockState(var18, getDefaultState().withProperty((IProperty)func_176267_a(var23), Boolean.valueOf(true)), 2);
/*     */               }
/* 324 */               else if (var26 && func_150093_a(worldIn.getBlockState(var16).getBlock()))
/*     */               {
/* 326 */                 worldIn.setBlockState(var18, getDefaultState().withProperty((IProperty)func_176267_a(var24), Boolean.valueOf(true)), 2);
/*     */               }
/* 328 */               else if (var25 && worldIn.isAirBlock(var27) && func_150093_a(worldIn.getBlockState(pos.offset(var23)).getBlock()))
/*     */               {
/* 330 */                 worldIn.setBlockState(var27, getDefaultState().withProperty((IProperty)func_176267_a(var17.getOpposite()), Boolean.valueOf(true)), 2);
/*     */               }
/* 332 */               else if (var26 && worldIn.isAirBlock(var16) && func_150093_a(worldIn.getBlockState(pos.offset(var24)).getBlock()))
/*     */               {
/* 334 */                 worldIn.setBlockState(var16, getDefaultState().withProperty((IProperty)func_176267_a(var17.getOpposite()), Boolean.valueOf(true)), 2);
/*     */               }
/* 336 */               else if (func_150093_a(worldIn.getBlockState(var18.offsetUp()).getBlock()))
/*     */               {
/* 338 */                 worldIn.setBlockState(var18, getDefaultState(), 2);
/*     */               }
/*     */             
/* 341 */             } else if (var21.blockMaterial.isOpaque() && var21.isFullCube()) {
/*     */               
/* 343 */               worldIn.setBlockState(pos, state.withProperty((IProperty)func_176267_a(var17), Boolean.valueOf(true)), 2);
/*     */             }
/*     */           
/*     */           }
/*     */         
/*     */         }
/* 349 */         else if (pos.getY() > 1) {
/*     */           
/* 351 */           BlockPos var18 = pos.offsetDown();
/* 352 */           IBlockState var20 = worldIn.getBlockState(var18);
/* 353 */           Block var11 = var20.getBlock();
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 358 */           if (var11.blockMaterial == Material.air) {
/*     */             
/* 360 */             IBlockState var12 = state;
/* 361 */             Iterator<EnumFacing> var13 = EnumFacing.Plane.HORIZONTAL.iterator();
/*     */             
/* 363 */             while (var13.hasNext()) {
/*     */               
/* 365 */               EnumFacing var14 = var13.next();
/*     */               
/* 367 */               if (rand.nextBoolean())
/*     */               {
/* 369 */                 var12 = var12.withProperty((IProperty)func_176267_a(var14), Boolean.valueOf(false));
/*     */               }
/*     */             } 
/*     */             
/* 373 */             if (((Boolean)var12.getValue((IProperty)field_176273_b)).booleanValue() || ((Boolean)var12.getValue((IProperty)field_176278_M)).booleanValue() || ((Boolean)var12.getValue((IProperty)field_176279_N)).booleanValue() || ((Boolean)var12.getValue((IProperty)field_176280_O)).booleanValue())
/*     */             {
/* 375 */               worldIn.setBlockState(var18, var12, 2);
/*     */             }
/*     */           }
/* 378 */           else if (var11 == this) {
/*     */             
/* 380 */             IBlockState var12 = var20;
/* 381 */             Iterator<EnumFacing> var13 = EnumFacing.Plane.HORIZONTAL.iterator();
/*     */             
/* 383 */             while (var13.hasNext()) {
/*     */               
/* 385 */               EnumFacing var14 = var13.next();
/* 386 */               PropertyBool var15 = func_176267_a(var14);
/*     */               
/* 388 */               if (rand.nextBoolean() || !((Boolean)state.getValue((IProperty)var15)).booleanValue())
/*     */               {
/* 390 */                 var12 = var12.withProperty((IProperty)var15, Boolean.valueOf(false));
/*     */               }
/*     */             } 
/*     */             
/* 394 */             if (((Boolean)var12.getValue((IProperty)field_176273_b)).booleanValue() || ((Boolean)var12.getValue((IProperty)field_176278_M)).booleanValue() || ((Boolean)var12.getValue((IProperty)field_176279_N)).booleanValue() || ((Boolean)var12.getValue((IProperty)field_176280_O)).booleanValue())
/*     */             {
/* 396 */               worldIn.setBlockState(var18, var12, 2);
/*     */             }
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static int func_176270_b(EnumFacing p_176270_0_) {
/* 408 */     return 1 << p_176270_0_.getHorizontalIndex();
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockState onBlockPlaced(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
/* 413 */     IBlockState var9 = getDefaultState().withProperty((IProperty)field_176277_a, Boolean.valueOf(false)).withProperty((IProperty)field_176273_b, Boolean.valueOf(false)).withProperty((IProperty)field_176278_M, Boolean.valueOf(false)).withProperty((IProperty)field_176279_N, Boolean.valueOf(false)).withProperty((IProperty)field_176280_O, Boolean.valueOf(false));
/* 414 */     return facing.getAxis().isHorizontal() ? var9.withProperty((IProperty)func_176267_a(facing.getOpposite()), Boolean.valueOf(true)) : var9;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Item getItemDropped(IBlockState state, Random rand, int fortune) {
/* 424 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int quantityDropped(Random random) {
/* 432 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public void harvestBlock(World worldIn, EntityPlayer playerIn, BlockPos pos, IBlockState state, TileEntity te) {
/* 437 */     if (!worldIn.isRemote && playerIn.getCurrentEquippedItem() != null && playerIn.getCurrentEquippedItem().getItem() == Items.shears) {
/*     */       
/* 439 */       playerIn.triggerAchievement(StatList.mineBlockStatArray[Block.getIdFromBlock(this)]);
/* 440 */       spawnAsEntity(worldIn, pos, new ItemStack(Blocks.vine, 1, 0));
/*     */     }
/*     */     else {
/*     */       
/* 444 */       super.harvestBlock(worldIn, playerIn, pos, state, te);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public EnumWorldBlockLayer getBlockLayer() {
/* 450 */     return EnumWorldBlockLayer.CUTOUT;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IBlockState getStateFromMeta(int meta) {
/* 458 */     return getDefaultState().withProperty((IProperty)field_176273_b, Boolean.valueOf(((meta & field_176276_R) > 0))).withProperty((IProperty)field_176278_M, Boolean.valueOf(((meta & field_176275_S) > 0))).withProperty((IProperty)field_176279_N, Boolean.valueOf(((meta & field_176272_Q) > 0))).withProperty((IProperty)field_176280_O, Boolean.valueOf(((meta & field_176271_T) > 0)));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getMetaFromState(IBlockState state) {
/* 466 */     int var2 = 0;
/*     */     
/* 468 */     if (((Boolean)state.getValue((IProperty)field_176273_b)).booleanValue())
/*     */     {
/* 470 */       var2 |= field_176276_R;
/*     */     }
/*     */     
/* 473 */     if (((Boolean)state.getValue((IProperty)field_176278_M)).booleanValue())
/*     */     {
/* 475 */       var2 |= field_176275_S;
/*     */     }
/*     */     
/* 478 */     if (((Boolean)state.getValue((IProperty)field_176279_N)).booleanValue())
/*     */     {
/* 480 */       var2 |= field_176272_Q;
/*     */     }
/*     */     
/* 483 */     if (((Boolean)state.getValue((IProperty)field_176280_O)).booleanValue())
/*     */     {
/* 485 */       var2 |= field_176271_T;
/*     */     }
/*     */     
/* 488 */     return var2;
/*     */   }
/*     */ 
/*     */   
/*     */   protected BlockState createBlockState() {
/* 493 */     return new BlockState(this, new IProperty[] { (IProperty)field_176277_a, (IProperty)field_176273_b, (IProperty)field_176278_M, (IProperty)field_176279_N, (IProperty)field_176280_O });
/*     */   }
/*     */ 
/*     */   
/*     */   public static PropertyBool func_176267_a(EnumFacing p_176267_0_) {
/* 498 */     switch (SwitchEnumFacing.field_177057_a[p_176267_0_.ordinal()]) {
/*     */       
/*     */       case 1:
/* 501 */         return field_176277_a;
/*     */       
/*     */       case 2:
/* 504 */         return field_176273_b;
/*     */       
/*     */       case 3:
/* 507 */         return field_176279_N;
/*     */       
/*     */       case 4:
/* 510 */         return field_176278_M;
/*     */       
/*     */       case 5:
/* 513 */         return field_176280_O;
/*     */     } 
/*     */     
/* 516 */     throw new IllegalArgumentException(p_176267_0_ + " is an invalid choice");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static int func_176268_d(IBlockState p_176268_0_) {
/* 522 */     int var1 = 0;
/* 523 */     PropertyBool[] var2 = field_176274_P;
/* 524 */     int var3 = var2.length;
/*     */     
/* 526 */     for (int var4 = 0; var4 < var3; var4++) {
/*     */       
/* 528 */       PropertyBool var5 = var2[var4];
/*     */       
/* 530 */       if (((Boolean)p_176268_0_.getValue((IProperty)var5)).booleanValue())
/*     */       {
/* 532 */         var1++;
/*     */       }
/*     */     } 
/*     */     
/* 536 */     return var1;
/*     */   }
/*     */   
/*     */   static final class SwitchEnumFacing
/*     */   {
/* 541 */     static final int[] field_177057_a = new int[(EnumFacing.values()).length];
/*     */     
/*     */     private static final String __OBFID = "CL_00002049";
/*     */ 
/*     */     
/*     */     static {
/*     */       try {
/* 548 */         field_177057_a[EnumFacing.UP.ordinal()] = 1;
/*     */       }
/* 550 */       catch (NoSuchFieldError noSuchFieldError) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/* 557 */         field_177057_a[EnumFacing.NORTH.ordinal()] = 2;
/*     */       }
/* 559 */       catch (NoSuchFieldError noSuchFieldError) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/* 566 */         field_177057_a[EnumFacing.SOUTH.ordinal()] = 3;
/*     */       }
/* 568 */       catch (NoSuchFieldError noSuchFieldError) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/* 575 */         field_177057_a[EnumFacing.EAST.ordinal()] = 4;
/*     */       }
/* 577 */       catch (NoSuchFieldError noSuchFieldError) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/* 584 */         field_177057_a[EnumFacing.WEST.ordinal()] = 5;
/*     */       }
/* 586 */       catch (NoSuchFieldError noSuchFieldError) {}
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\block\BlockVine.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */