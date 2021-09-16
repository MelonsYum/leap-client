/*     */ package net.minecraft.block;
/*     */ 
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.block.properties.IProperty;
/*     */ import net.minecraft.block.properties.PropertyInteger;
/*     */ import net.minecraft.block.state.BlockState;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.item.EnumDyeColor;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.EnumWorldBlockLayer;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class BlockCocoa
/*     */   extends BlockDirectional implements IGrowable {
/*  24 */   public static final PropertyInteger field_176501_a = PropertyInteger.create("age", 0, 2);
/*     */   
/*     */   private static final String __OBFID = "CL_00000216";
/*     */   
/*     */   public BlockCocoa() {
/*  29 */     super(Material.plants);
/*  30 */     setDefaultState(this.blockState.getBaseState().withProperty((IProperty)AGE, (Comparable)EnumFacing.NORTH).withProperty((IProperty)field_176501_a, Integer.valueOf(0)));
/*  31 */     setTickRandomly(true);
/*     */   }
/*     */ 
/*     */   
/*     */   public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
/*  36 */     if (!canBlockStay(worldIn, pos, state)) {
/*     */       
/*  38 */       dropBlock(worldIn, pos, state);
/*     */     }
/*  40 */     else if (worldIn.rand.nextInt(5) == 0) {
/*     */       
/*  42 */       int var5 = ((Integer)state.getValue((IProperty)field_176501_a)).intValue();
/*     */       
/*  44 */       if (var5 < 2)
/*     */       {
/*  46 */         worldIn.setBlockState(pos, state.withProperty((IProperty)field_176501_a, Integer.valueOf(var5 + 1)), 2);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canBlockStay(World worldIn, BlockPos p_176499_2_, IBlockState p_176499_3_) {
/*  53 */     p_176499_2_ = p_176499_2_.offset((EnumFacing)p_176499_3_.getValue((IProperty)AGE));
/*  54 */     IBlockState var4 = worldIn.getBlockState(p_176499_2_);
/*  55 */     return (var4.getBlock() == Blocks.log && var4.getValue((IProperty)BlockPlanks.VARIANT_PROP) == BlockPlanks.EnumType.JUNGLE);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isFullCube() {
/*  60 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isOpaqueCube() {
/*  65 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public AxisAlignedBB getCollisionBoundingBox(World worldIn, BlockPos pos, IBlockState state) {
/*  70 */     setBlockBoundsBasedOnState((IBlockAccess)worldIn, pos);
/*  71 */     return super.getCollisionBoundingBox(worldIn, pos, state);
/*     */   }
/*     */ 
/*     */   
/*     */   public AxisAlignedBB getSelectedBoundingBox(World worldIn, BlockPos pos) {
/*  76 */     setBlockBoundsBasedOnState((IBlockAccess)worldIn, pos);
/*  77 */     return super.getSelectedBoundingBox(worldIn, pos);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setBlockBoundsBasedOnState(IBlockAccess access, BlockPos pos) {
/*  82 */     IBlockState var3 = access.getBlockState(pos);
/*  83 */     EnumFacing var4 = (EnumFacing)var3.getValue((IProperty)AGE);
/*  84 */     int var5 = ((Integer)var3.getValue((IProperty)field_176501_a)).intValue();
/*  85 */     int var6 = 4 + var5 * 2;
/*  86 */     int var7 = 5 + var5 * 2;
/*  87 */     float var8 = var6 / 2.0F;
/*     */     
/*  89 */     switch (SwitchEnumFacing.FACINGARRAY[var4.ordinal()]) {
/*     */       
/*     */       case 1:
/*  92 */         setBlockBounds((8.0F - var8) / 16.0F, (12.0F - var7) / 16.0F, (15.0F - var6) / 16.0F, (8.0F + var8) / 16.0F, 0.75F, 0.9375F);
/*     */         break;
/*     */       
/*     */       case 2:
/*  96 */         setBlockBounds((8.0F - var8) / 16.0F, (12.0F - var7) / 16.0F, 0.0625F, (8.0F + var8) / 16.0F, 0.75F, (1.0F + var6) / 16.0F);
/*     */         break;
/*     */       
/*     */       case 3:
/* 100 */         setBlockBounds(0.0625F, (12.0F - var7) / 16.0F, (8.0F - var8) / 16.0F, (1.0F + var6) / 16.0F, 0.75F, (8.0F + var8) / 16.0F);
/*     */         break;
/*     */       
/*     */       case 4:
/* 104 */         setBlockBounds((15.0F - var6) / 16.0F, (12.0F - var7) / 16.0F, (8.0F - var8) / 16.0F, 0.9375F, 0.75F, (8.0F + var8) / 16.0F);
/*     */         break;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
/* 110 */     EnumFacing var6 = EnumFacing.fromAngle(placer.rotationYaw);
/* 111 */     worldIn.setBlockState(pos, state.withProperty((IProperty)AGE, (Comparable)var6), 2);
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockState onBlockPlaced(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
/* 116 */     if (!facing.getAxis().isHorizontal())
/*     */     {
/* 118 */       facing = EnumFacing.NORTH;
/*     */     }
/*     */     
/* 121 */     return getDefaultState().withProperty((IProperty)AGE, (Comparable)facing.getOpposite()).withProperty((IProperty)field_176501_a, Integer.valueOf(0));
/*     */   }
/*     */ 
/*     */   
/*     */   public void onNeighborBlockChange(World worldIn, BlockPos pos, IBlockState state, Block neighborBlock) {
/* 126 */     if (!canBlockStay(worldIn, pos, state))
/*     */     {
/* 128 */       dropBlock(worldIn, pos, state);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private void dropBlock(World worldIn, BlockPos p_176500_2_, IBlockState p_176500_3_) {
/* 134 */     worldIn.setBlockState(p_176500_2_, Blocks.air.getDefaultState(), 3);
/* 135 */     dropBlockAsItem(worldIn, p_176500_2_, p_176500_3_, 0);
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
/* 146 */     int var6 = ((Integer)state.getValue((IProperty)field_176501_a)).intValue();
/* 147 */     byte var7 = 1;
/*     */     
/* 149 */     if (var6 >= 2)
/*     */     {
/* 151 */       var7 = 3;
/*     */     }
/*     */     
/* 154 */     for (int var8 = 0; var8 < var7; var8++)
/*     */     {
/* 156 */       spawnAsEntity(worldIn, pos, new ItemStack(Items.dye, 1, EnumDyeColor.BROWN.getDyeColorDamage()));
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public Item getItem(World worldIn, BlockPos pos) {
/* 162 */     return Items.dye;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getDamageValue(World worldIn, BlockPos pos) {
/* 167 */     return EnumDyeColor.BROWN.getDyeColorDamage();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isStillGrowing(World worldIn, BlockPos p_176473_2_, IBlockState p_176473_3_, boolean p_176473_4_) {
/* 172 */     return (((Integer)p_176473_3_.getValue((IProperty)field_176501_a)).intValue() < 2);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canUseBonemeal(World worldIn, Random p_180670_2_, BlockPos p_180670_3_, IBlockState p_180670_4_) {
/* 177 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public void grow(World worldIn, Random p_176474_2_, BlockPos p_176474_3_, IBlockState p_176474_4_) {
/* 182 */     worldIn.setBlockState(p_176474_3_, p_176474_4_.withProperty((IProperty)field_176501_a, Integer.valueOf(((Integer)p_176474_4_.getValue((IProperty)field_176501_a)).intValue() + 1)), 2);
/*     */   }
/*     */ 
/*     */   
/*     */   public EnumWorldBlockLayer getBlockLayer() {
/* 187 */     return EnumWorldBlockLayer.CUTOUT;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IBlockState getStateFromMeta(int meta) {
/* 195 */     return getDefaultState().withProperty((IProperty)AGE, (Comparable)EnumFacing.getHorizontal(meta)).withProperty((IProperty)field_176501_a, Integer.valueOf((meta & 0xF) >> 2));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getMetaFromState(IBlockState state) {
/* 203 */     byte var2 = 0;
/* 204 */     int var3 = var2 | ((EnumFacing)state.getValue((IProperty)AGE)).getHorizontalIndex();
/* 205 */     var3 |= ((Integer)state.getValue((IProperty)field_176501_a)).intValue() << 2;
/* 206 */     return var3;
/*     */   }
/*     */ 
/*     */   
/*     */   protected BlockState createBlockState() {
/* 211 */     return new BlockState(this, new IProperty[] { (IProperty)AGE, (IProperty)field_176501_a });
/*     */   }
/*     */   
/*     */   static final class SwitchEnumFacing
/*     */   {
/* 216 */     static final int[] FACINGARRAY = new int[(EnumFacing.values()).length];
/*     */     
/*     */     private static final String __OBFID = "CL_00002130";
/*     */ 
/*     */     
/*     */     static {
/*     */       try {
/* 223 */         FACINGARRAY[EnumFacing.SOUTH.ordinal()] = 1;
/*     */       }
/* 225 */       catch (NoSuchFieldError noSuchFieldError) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/* 232 */         FACINGARRAY[EnumFacing.NORTH.ordinal()] = 2;
/*     */       }
/* 234 */       catch (NoSuchFieldError noSuchFieldError) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/* 241 */         FACINGARRAY[EnumFacing.WEST.ordinal()] = 3;
/*     */       }
/* 243 */       catch (NoSuchFieldError noSuchFieldError) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/* 250 */         FACINGARRAY[EnumFacing.EAST.ordinal()] = 4;
/*     */       }
/* 252 */       catch (NoSuchFieldError noSuchFieldError) {}
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\block\BlockCocoa.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */