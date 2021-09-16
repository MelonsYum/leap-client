/*     */ package net.minecraft.block;
/*     */ 
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.properties.IProperty;
/*     */ import net.minecraft.block.properties.PropertyInteger;
/*     */ import net.minecraft.block.state.BlockState;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class BlockCrops
/*     */   extends BlockBush
/*     */   implements IGrowable {
/*  19 */   public static final PropertyInteger AGE = PropertyInteger.create("age", 0, 7);
/*     */   
/*     */   private static final String __OBFID = "CL_00000222";
/*     */   
/*     */   protected BlockCrops() {
/*  24 */     setDefaultState(this.blockState.getBaseState().withProperty((IProperty)AGE, Integer.valueOf(0)));
/*  25 */     setTickRandomly(true);
/*  26 */     float var1 = 0.5F;
/*  27 */     setBlockBounds(0.5F - var1, 0.0F, 0.5F - var1, 0.5F + var1, 0.25F, 0.5F + var1);
/*  28 */     setCreativeTab(null);
/*  29 */     setHardness(0.0F);
/*  30 */     setStepSound(soundTypeGrass);
/*  31 */     disableStats();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean canPlaceBlockOn(Block ground) {
/*  39 */     return (ground == Blocks.farmland);
/*     */   }
/*     */ 
/*     */   
/*     */   public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
/*  44 */     super.updateTick(worldIn, pos, state, rand);
/*     */     
/*  46 */     if (worldIn.getLightFromNeighbors(pos.offsetUp()) >= 9) {
/*     */       
/*  48 */       int var5 = ((Integer)state.getValue((IProperty)AGE)).intValue();
/*     */       
/*  50 */       if (var5 < 7) {
/*     */         
/*  52 */         float var6 = getGrowthChance(this, worldIn, pos);
/*     */         
/*  54 */         if (rand.nextInt((int)(25.0F / var6) + 1) == 0)
/*     */         {
/*  56 */           worldIn.setBlockState(pos, state.withProperty((IProperty)AGE, Integer.valueOf(var5 + 1)), 2);
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void growCrops(World worldIn, BlockPos p_176487_2_, IBlockState p_176487_3_) {
/*  64 */     int var4 = ((Integer)p_176487_3_.getValue((IProperty)AGE)).intValue() + MathHelper.getRandomIntegerInRange(worldIn.rand, 2, 5);
/*     */     
/*  66 */     if (var4 > 7)
/*     */     {
/*  68 */       var4 = 7;
/*     */     }
/*     */     
/*  71 */     worldIn.setBlockState(p_176487_2_, p_176487_3_.withProperty((IProperty)AGE, Integer.valueOf(var4)), 2);
/*     */   }
/*     */ 
/*     */   
/*     */   protected static float getGrowthChance(Block p_180672_0_, World worldIn, BlockPos p_180672_2_) {
/*  76 */     float var3 = 1.0F;
/*  77 */     BlockPos var4 = p_180672_2_.offsetDown();
/*     */     
/*  79 */     for (int var5 = -1; var5 <= 1; var5++) {
/*     */       
/*  81 */       for (int var6 = -1; var6 <= 1; var6++) {
/*     */         
/*  83 */         float var7 = 0.0F;
/*  84 */         IBlockState var8 = worldIn.getBlockState(var4.add(var5, 0, var6));
/*     */         
/*  86 */         if (var8.getBlock() == Blocks.farmland) {
/*     */           
/*  88 */           var7 = 1.0F;
/*     */           
/*  90 */           if (((Integer)var8.getValue((IProperty)BlockFarmland.field_176531_a)).intValue() > 0)
/*     */           {
/*  92 */             var7 = 3.0F;
/*     */           }
/*     */         } 
/*     */         
/*  96 */         if (var5 != 0 || var6 != 0)
/*     */         {
/*  98 */           var7 /= 4.0F;
/*     */         }
/*     */         
/* 101 */         var3 += var7;
/*     */       } 
/*     */     } 
/*     */     
/* 105 */     BlockPos var12 = p_180672_2_.offsetNorth();
/* 106 */     BlockPos var13 = p_180672_2_.offsetSouth();
/* 107 */     BlockPos var14 = p_180672_2_.offsetWest();
/* 108 */     BlockPos var15 = p_180672_2_.offsetEast();
/* 109 */     boolean var9 = !(p_180672_0_ != worldIn.getBlockState(var14).getBlock() && p_180672_0_ != worldIn.getBlockState(var15).getBlock());
/* 110 */     boolean var10 = !(p_180672_0_ != worldIn.getBlockState(var12).getBlock() && p_180672_0_ != worldIn.getBlockState(var13).getBlock());
/*     */     
/* 112 */     if (var9 && var10) {
/*     */       
/* 114 */       var3 /= 2.0F;
/*     */     }
/*     */     else {
/*     */       
/* 118 */       boolean var11 = !(p_180672_0_ != worldIn.getBlockState(var14.offsetNorth()).getBlock() && p_180672_0_ != worldIn.getBlockState(var15.offsetNorth()).getBlock() && p_180672_0_ != worldIn.getBlockState(var15.offsetSouth()).getBlock() && p_180672_0_ != worldIn.getBlockState(var14.offsetSouth()).getBlock());
/*     */       
/* 120 */       if (var11)
/*     */       {
/* 122 */         var3 /= 2.0F;
/*     */       }
/*     */     } 
/*     */     
/* 126 */     return var3;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canBlockStay(World worldIn, BlockPos p_180671_2_, IBlockState p_180671_3_) {
/* 131 */     return ((worldIn.getLight(p_180671_2_) >= 8 || worldIn.isAgainstSky(p_180671_2_)) && canPlaceBlockOn(worldIn.getBlockState(p_180671_2_.offsetDown()).getBlock()));
/*     */   }
/*     */ 
/*     */   
/*     */   protected Item getSeed() {
/* 136 */     return Items.wheat_seeds;
/*     */   }
/*     */ 
/*     */   
/*     */   protected Item getCrop() {
/* 141 */     return Items.wheat;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void dropBlockAsItemWithChance(World worldIn, BlockPos pos, IBlockState state, float chance, int fortune) {
/* 152 */     super.dropBlockAsItemWithChance(worldIn, pos, state, chance, 0);
/*     */     
/* 154 */     if (!worldIn.isRemote) {
/*     */       
/* 156 */       int var6 = ((Integer)state.getValue((IProperty)AGE)).intValue();
/*     */       
/* 158 */       if (var6 >= 7) {
/*     */         
/* 160 */         int var7 = 3 + fortune;
/*     */         
/* 162 */         for (int var8 = 0; var8 < var7; var8++) {
/*     */           
/* 164 */           if (worldIn.rand.nextInt(15) <= var6)
/*     */           {
/* 166 */             spawnAsEntity(worldIn, pos, new ItemStack(getSeed(), 1, 0));
/*     */           }
/*     */         } 
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
/* 180 */     return (((Integer)state.getValue((IProperty)AGE)).intValue() == 7) ? getCrop() : getSeed();
/*     */   }
/*     */ 
/*     */   
/*     */   public Item getItem(World worldIn, BlockPos pos) {
/* 185 */     return getSeed();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isStillGrowing(World worldIn, BlockPos p_176473_2_, IBlockState p_176473_3_, boolean p_176473_4_) {
/* 190 */     return (((Integer)p_176473_3_.getValue((IProperty)AGE)).intValue() < 7);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canUseBonemeal(World worldIn, Random p_180670_2_, BlockPos p_180670_3_, IBlockState p_180670_4_) {
/* 195 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public void grow(World worldIn, Random p_176474_2_, BlockPos p_176474_3_, IBlockState p_176474_4_) {
/* 200 */     growCrops(worldIn, p_176474_3_, p_176474_4_);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IBlockState getStateFromMeta(int meta) {
/* 208 */     return getDefaultState().withProperty((IProperty)AGE, Integer.valueOf(meta));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getMetaFromState(IBlockState state) {
/* 216 */     return ((Integer)state.getValue((IProperty)AGE)).intValue();
/*     */   }
/*     */ 
/*     */   
/*     */   protected BlockState createBlockState() {
/* 221 */     return new BlockState(this, new IProperty[] { (IProperty)AGE });
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\block\BlockCrops.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */