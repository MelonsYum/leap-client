/*     */ package net.minecraft.block;
/*     */ 
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.block.properties.IProperty;
/*     */ import net.minecraft.block.properties.PropertyInteger;
/*     */ import net.minecraft.block.state.BlockState;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.EnumWorldBlockLayer;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class BlockCake
/*     */   extends Block {
/*  21 */   public static final PropertyInteger BITES = PropertyInteger.create("bites", 0, 6);
/*     */   
/*     */   private static final String __OBFID = "CL_00000211";
/*     */   
/*     */   protected BlockCake() {
/*  26 */     super(Material.cake);
/*  27 */     setDefaultState(this.blockState.getBaseState().withProperty((IProperty)BITES, Integer.valueOf(0)));
/*  28 */     setTickRandomly(true);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setBlockBoundsBasedOnState(IBlockAccess access, BlockPos pos) {
/*  33 */     float var3 = 0.0625F;
/*  34 */     float var4 = (1 + ((Integer)access.getBlockState(pos).getValue((IProperty)BITES)).intValue() * 2) / 16.0F;
/*  35 */     float var5 = 0.5F;
/*  36 */     setBlockBounds(var4, 0.0F, var3, 1.0F - var3, var5, 1.0F - var3);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setBlockBoundsForItemRender() {
/*  44 */     float var1 = 0.0625F;
/*  45 */     float var2 = 0.5F;
/*  46 */     setBlockBounds(var1, 0.0F, var1, 1.0F - var1, var2, 1.0F - var1);
/*     */   }
/*     */ 
/*     */   
/*     */   public AxisAlignedBB getCollisionBoundingBox(World worldIn, BlockPos pos, IBlockState state) {
/*  51 */     float var4 = 0.0625F;
/*  52 */     float var5 = (1 + ((Integer)state.getValue((IProperty)BITES)).intValue() * 2) / 16.0F;
/*  53 */     float var6 = 0.5F;
/*  54 */     return new AxisAlignedBB((pos.getX() + var5), pos.getY(), (pos.getZ() + var4), ((pos.getX() + 1) - var4), (pos.getY() + var6), ((pos.getZ() + 1) - var4));
/*     */   }
/*     */ 
/*     */   
/*     */   public AxisAlignedBB getSelectedBoundingBox(World worldIn, BlockPos pos) {
/*  59 */     return getCollisionBoundingBox(worldIn, pos, worldIn.getBlockState(pos));
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isFullCube() {
/*  64 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isOpaqueCube() {
/*  69 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumFacing side, float hitX, float hitY, float hitZ) {
/*  74 */     eatCake(worldIn, pos, state, playerIn);
/*  75 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public void onBlockClicked(World worldIn, BlockPos pos, EntityPlayer playerIn) {
/*  80 */     eatCake(worldIn, pos, worldIn.getBlockState(pos), playerIn);
/*     */   }
/*     */ 
/*     */   
/*     */   private void eatCake(World worldIn, BlockPos p_180682_2_, IBlockState p_180682_3_, EntityPlayer p_180682_4_) {
/*  85 */     if (p_180682_4_.canEat(false)) {
/*     */       
/*  87 */       p_180682_4_.getFoodStats().addStats(2, 0.1F);
/*  88 */       int var5 = ((Integer)p_180682_3_.getValue((IProperty)BITES)).intValue();
/*     */       
/*  90 */       if (var5 < 6) {
/*     */         
/*  92 */         worldIn.setBlockState(p_180682_2_, p_180682_3_.withProperty((IProperty)BITES, Integer.valueOf(var5 + 1)), 3);
/*     */       }
/*     */       else {
/*     */         
/*  96 */         worldIn.setBlockToAir(p_180682_2_);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canPlaceBlockAt(World worldIn, BlockPos pos) {
/* 103 */     return super.canPlaceBlockAt(worldIn, pos) ? canBlockStay(worldIn, pos) : false;
/*     */   }
/*     */ 
/*     */   
/*     */   public void onNeighborBlockChange(World worldIn, BlockPos pos, IBlockState state, Block neighborBlock) {
/* 108 */     if (!canBlockStay(worldIn, pos))
/*     */     {
/* 110 */       worldIn.setBlockToAir(pos);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean canBlockStay(World worldIn, BlockPos p_176588_2_) {
/* 116 */     return worldIn.getBlockState(p_176588_2_.offsetDown()).getBlock().getMaterial().isSolid();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int quantityDropped(Random random) {
/* 124 */     return 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Item getItemDropped(IBlockState state, Random rand, int fortune) {
/* 134 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public Item getItem(World worldIn, BlockPos pos) {
/* 139 */     return Items.cake;
/*     */   }
/*     */ 
/*     */   
/*     */   public EnumWorldBlockLayer getBlockLayer() {
/* 144 */     return EnumWorldBlockLayer.CUTOUT;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IBlockState getStateFromMeta(int meta) {
/* 152 */     return getDefaultState().withProperty((IProperty)BITES, Integer.valueOf(meta));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getMetaFromState(IBlockState state) {
/* 160 */     return ((Integer)state.getValue((IProperty)BITES)).intValue();
/*     */   }
/*     */ 
/*     */   
/*     */   protected BlockState createBlockState() {
/* 165 */     return new BlockState(this, new IProperty[] { (IProperty)BITES });
/*     */   }
/*     */ 
/*     */   
/*     */   public int getComparatorInputOverride(World worldIn, BlockPos pos) {
/* 170 */     return (7 - ((Integer)worldIn.getBlockState(pos).getValue((IProperty)BITES)).intValue()) * 2;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hasComparatorInputOverride() {
/* 175 */     return true;
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\block\BlockCake.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */