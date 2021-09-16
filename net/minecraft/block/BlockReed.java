/*     */ package net.minecraft.block;
/*     */ 
/*     */ import java.util.Iterator;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.block.properties.IProperty;
/*     */ import net.minecraft.block.properties.PropertyInteger;
/*     */ import net.minecraft.block.state.BlockState;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.EnumWorldBlockLayer;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class BlockReed
/*     */   extends Block {
/*  22 */   public static final PropertyInteger field_176355_a = PropertyInteger.create("age", 0, 15);
/*     */   
/*     */   private static final String __OBFID = "CL_00000300";
/*     */   
/*     */   protected BlockReed() {
/*  27 */     super(Material.plants);
/*  28 */     setDefaultState(this.blockState.getBaseState().withProperty((IProperty)field_176355_a, Integer.valueOf(0)));
/*  29 */     float var1 = 0.375F;
/*  30 */     setBlockBounds(0.5F - var1, 0.0F, 0.5F - var1, 0.5F + var1, 1.0F, 0.5F + var1);
/*  31 */     setTickRandomly(true);
/*     */   }
/*     */ 
/*     */   
/*     */   public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
/*  36 */     if (worldIn.getBlockState(pos.offsetDown()).getBlock() == Blocks.reeds || func_176353_e(worldIn, pos, state))
/*     */     {
/*  38 */       if (worldIn.isAirBlock(pos.offsetUp())) {
/*     */         int var5;
/*     */ 
/*     */         
/*  42 */         for (var5 = 1; worldIn.getBlockState(pos.offsetDown(var5)).getBlock() == this; var5++);
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*  47 */         if (var5 < 3) {
/*     */           
/*  49 */           int var6 = ((Integer)state.getValue((IProperty)field_176355_a)).intValue();
/*     */           
/*  51 */           if (var6 == 15) {
/*     */             
/*  53 */             worldIn.setBlockState(pos.offsetUp(), getDefaultState());
/*  54 */             worldIn.setBlockState(pos, state.withProperty((IProperty)field_176355_a, Integer.valueOf(0)), 4);
/*     */           }
/*     */           else {
/*     */             
/*  58 */             worldIn.setBlockState(pos, state.withProperty((IProperty)field_176355_a, Integer.valueOf(var6 + 1)), 4);
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean canPlaceBlockAt(World worldIn, BlockPos pos) {
/*     */     EnumFacing var5;
/*  67 */     Block var3 = worldIn.getBlockState(pos.offsetDown()).getBlock();
/*     */     
/*  69 */     if (var3 == this)
/*     */     {
/*  71 */       return true;
/*     */     }
/*  73 */     if (var3 != Blocks.grass && var3 != Blocks.dirt && var3 != Blocks.sand)
/*     */     {
/*  75 */       return false;
/*     */     }
/*     */ 
/*     */     
/*  79 */     Iterator<EnumFacing> var4 = EnumFacing.Plane.HORIZONTAL.iterator();
/*     */ 
/*     */ 
/*     */     
/*     */     do {
/*  84 */       if (!var4.hasNext())
/*     */       {
/*  86 */         return false;
/*     */       }
/*     */       
/*  89 */       var5 = var4.next();
/*     */     }
/*  91 */     while (worldIn.getBlockState(pos.offset(var5).offsetDown()).getBlock().getMaterial() != Material.water);
/*     */     
/*  93 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void onNeighborBlockChange(World worldIn, BlockPos pos, IBlockState state, Block neighborBlock) {
/*  99 */     func_176353_e(worldIn, pos, state);
/*     */   }
/*     */ 
/*     */   
/*     */   protected final boolean func_176353_e(World worldIn, BlockPos p_176353_2_, IBlockState p_176353_3_) {
/* 104 */     if (func_176354_d(worldIn, p_176353_2_))
/*     */     {
/* 106 */       return true;
/*     */     }
/*     */ 
/*     */     
/* 110 */     dropBlockAsItem(worldIn, p_176353_2_, p_176353_3_, 0);
/* 111 */     worldIn.setBlockToAir(p_176353_2_);
/* 112 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_176354_d(World worldIn, BlockPos p_176354_2_) {
/* 118 */     return canPlaceBlockAt(worldIn, p_176354_2_);
/*     */   }
/*     */ 
/*     */   
/*     */   public AxisAlignedBB getCollisionBoundingBox(World worldIn, BlockPos pos, IBlockState state) {
/* 123 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Item getItemDropped(IBlockState state, Random rand, int fortune) {
/* 133 */     return Items.reeds;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isOpaqueCube() {
/* 138 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isFullCube() {
/* 143 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public Item getItem(World worldIn, BlockPos pos) {
/* 148 */     return Items.reeds;
/*     */   }
/*     */ 
/*     */   
/*     */   public int colorMultiplier(IBlockAccess worldIn, BlockPos pos, int renderPass) {
/* 153 */     return worldIn.getBiomeGenForCoords(pos).func_180627_b(pos);
/*     */   }
/*     */ 
/*     */   
/*     */   public EnumWorldBlockLayer getBlockLayer() {
/* 158 */     return EnumWorldBlockLayer.CUTOUT;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IBlockState getStateFromMeta(int meta) {
/* 166 */     return getDefaultState().withProperty((IProperty)field_176355_a, Integer.valueOf(meta));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getMetaFromState(IBlockState state) {
/* 174 */     return ((Integer)state.getValue((IProperty)field_176355_a)).intValue();
/*     */   }
/*     */ 
/*     */   
/*     */   protected BlockState createBlockState() {
/* 179 */     return new BlockState(this, new IProperty[] { (IProperty)field_176355_a });
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\block\BlockReed.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */