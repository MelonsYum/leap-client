/*     */ package net.minecraft.block;
/*     */ 
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.block.properties.IProperty;
/*     */ import net.minecraft.block.properties.PropertyInteger;
/*     */ import net.minecraft.block.state.BlockState;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.creativetab.CreativeTabs;
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
/*     */ import net.minecraft.world.EnumSkyBlock;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class BlockSnow
/*     */   extends Block {
/*  26 */   public static final PropertyInteger LAYERS_PROP = PropertyInteger.create("layers", 1, 8);
/*     */   
/*     */   private static final String __OBFID = "CL_00000309";
/*     */   
/*     */   protected BlockSnow() {
/*  31 */     super(Material.snow);
/*  32 */     setDefaultState(this.blockState.getBaseState().withProperty((IProperty)LAYERS_PROP, Integer.valueOf(1)));
/*  33 */     setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.125F, 1.0F);
/*  34 */     setTickRandomly(true);
/*  35 */     setCreativeTab(CreativeTabs.tabDecorations);
/*  36 */     setBlockBoundsForItemRender();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isPassable(IBlockAccess blockAccess, BlockPos pos) {
/*  41 */     return (((Integer)blockAccess.getBlockState(pos).getValue((IProperty)LAYERS_PROP)).intValue() < 5);
/*     */   }
/*     */ 
/*     */   
/*     */   public AxisAlignedBB getCollisionBoundingBox(World worldIn, BlockPos pos, IBlockState state) {
/*  46 */     int var4 = ((Integer)state.getValue((IProperty)LAYERS_PROP)).intValue() - 1;
/*  47 */     float var5 = 0.125F;
/*  48 */     return new AxisAlignedBB(pos.getX() + this.minX, pos.getY() + this.minY, pos.getZ() + this.minZ, pos.getX() + this.maxX, (pos.getY() + var4 * var5), pos.getZ() + this.maxZ);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isOpaqueCube() {
/*  53 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isFullCube() {
/*  58 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setBlockBoundsForItemRender() {
/*  66 */     getBoundsForLayers(0);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setBlockBoundsBasedOnState(IBlockAccess access, BlockPos pos) {
/*  71 */     IBlockState var3 = access.getBlockState(pos);
/*  72 */     getBoundsForLayers(((Integer)var3.getValue((IProperty)LAYERS_PROP)).intValue());
/*     */   }
/*     */ 
/*     */   
/*     */   protected void getBoundsForLayers(int p_150154_1_) {
/*  77 */     setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, p_150154_1_ / 8.0F, 1.0F);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canPlaceBlockAt(World worldIn, BlockPos pos) {
/*  82 */     IBlockState var3 = worldIn.getBlockState(pos.offsetDown());
/*  83 */     Block var4 = var3.getBlock();
/*  84 */     return (var4 != Blocks.ice && var4 != Blocks.packed_ice) ? ((var4.getMaterial() == Material.leaves) ? true : ((var4 == this && ((Integer)var3.getValue((IProperty)LAYERS_PROP)).intValue() == 7) ? true : ((var4.isOpaqueCube() && var4.blockMaterial.blocksMovement())))) : false;
/*     */   }
/*     */ 
/*     */   
/*     */   public void onNeighborBlockChange(World worldIn, BlockPos pos, IBlockState state, Block neighborBlock) {
/*  89 */     checkAndDropBlock(worldIn, pos, state);
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean checkAndDropBlock(World worldIn, BlockPos p_176314_2_, IBlockState p_176314_3_) {
/*  94 */     if (!canPlaceBlockAt(worldIn, p_176314_2_)) {
/*     */       
/*  96 */       dropBlockAsItem(worldIn, p_176314_2_, p_176314_3_, 0);
/*  97 */       worldIn.setBlockToAir(p_176314_2_);
/*  98 */       return false;
/*     */     } 
/*     */ 
/*     */     
/* 102 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void harvestBlock(World worldIn, EntityPlayer playerIn, BlockPos pos, IBlockState state, TileEntity te) {
/* 108 */     spawnAsEntity(worldIn, pos, new ItemStack(Items.snowball, ((Integer)state.getValue((IProperty)LAYERS_PROP)).intValue() + 1, 0));
/* 109 */     worldIn.setBlockToAir(pos);
/* 110 */     playerIn.triggerAchievement(StatList.mineBlockStatArray[Block.getIdFromBlock(this)]);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Item getItemDropped(IBlockState state, Random rand, int fortune) {
/* 120 */     return Items.snowball;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int quantityDropped(Random random) {
/* 128 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
/* 133 */     if (worldIn.getLightFor(EnumSkyBlock.BLOCK, pos) > 11) {
/*     */       
/* 135 */       dropBlockAsItem(worldIn, pos, worldIn.getBlockState(pos), 0);
/* 136 */       worldIn.setBlockToAir(pos);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean shouldSideBeRendered(IBlockAccess worldIn, BlockPos pos, EnumFacing side) {
/* 142 */     return (side == EnumFacing.UP) ? true : super.shouldSideBeRendered(worldIn, pos, side);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IBlockState getStateFromMeta(int meta) {
/* 150 */     return getDefaultState().withProperty((IProperty)LAYERS_PROP, Integer.valueOf((meta & 0x7) + 1));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isReplaceable(World worldIn, BlockPos pos) {
/* 158 */     return (((Integer)worldIn.getBlockState(pos).getValue((IProperty)LAYERS_PROP)).intValue() == 1);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getMetaFromState(IBlockState state) {
/* 166 */     return ((Integer)state.getValue((IProperty)LAYERS_PROP)).intValue() - 1;
/*     */   }
/*     */ 
/*     */   
/*     */   protected BlockState createBlockState() {
/* 171 */     return new BlockState(this, new IProperty[] { (IProperty)LAYERS_PROP });
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\block\BlockSnow.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */