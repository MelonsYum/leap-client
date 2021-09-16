/*     */ package net.minecraft.block;
/*     */ 
/*     */ import java.util.List;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.block.properties.IProperty;
/*     */ import net.minecraft.block.properties.PropertyEnum;
/*     */ import net.minecraft.block.state.BlockState;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.creativetab.CreativeTabs;
/*     */ import net.minecraft.item.EnumDyeColor;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class BlockCarpet
/*     */   extends Block {
/*  20 */   public static final PropertyEnum field_176330_a = PropertyEnum.create("color", EnumDyeColor.class);
/*     */   
/*     */   private static final String __OBFID = "CL_00000338";
/*     */   
/*     */   protected BlockCarpet() {
/*  25 */     super(Material.carpet);
/*  26 */     setDefaultState(this.blockState.getBaseState().withProperty((IProperty)field_176330_a, (Comparable)EnumDyeColor.WHITE));
/*  27 */     setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.0625F, 1.0F);
/*  28 */     setTickRandomly(true);
/*  29 */     setCreativeTab(CreativeTabs.tabDecorations);
/*  30 */     setBlockBoundsFromMeta(0);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isOpaqueCube() {
/*  35 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isFullCube() {
/*  40 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setBlockBoundsForItemRender() {
/*  48 */     setBlockBoundsFromMeta(0);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setBlockBoundsBasedOnState(IBlockAccess access, BlockPos pos) {
/*  53 */     setBlockBoundsFromMeta(0);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void setBlockBoundsFromMeta(int meta) {
/*  58 */     byte var2 = 0;
/*  59 */     float var3 = (1 * (1 + var2)) / 16.0F;
/*  60 */     setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, var3, 1.0F);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canPlaceBlockAt(World worldIn, BlockPos pos) {
/*  65 */     return (super.canPlaceBlockAt(worldIn, pos) && canBlockStay(worldIn, pos));
/*     */   }
/*     */ 
/*     */   
/*     */   public void onNeighborBlockChange(World worldIn, BlockPos pos, IBlockState state, Block neighborBlock) {
/*  70 */     checkAndDropBlock(worldIn, pos, state);
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean checkAndDropBlock(World worldIn, BlockPos p_176328_2_, IBlockState p_176328_3_) {
/*  75 */     if (!canBlockStay(worldIn, p_176328_2_)) {
/*     */       
/*  77 */       dropBlockAsItem(worldIn, p_176328_2_, p_176328_3_, 0);
/*  78 */       worldIn.setBlockToAir(p_176328_2_);
/*  79 */       return false;
/*     */     } 
/*     */ 
/*     */     
/*  83 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean canBlockStay(World worldIn, BlockPos p_176329_2_) {
/*  89 */     return !worldIn.isAirBlock(p_176329_2_.offsetDown());
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean shouldSideBeRendered(IBlockAccess worldIn, BlockPos pos, EnumFacing side) {
/*  94 */     return (side == EnumFacing.UP) ? true : super.shouldSideBeRendered(worldIn, pos, side);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int damageDropped(IBlockState state) {
/* 102 */     return ((EnumDyeColor)state.getValue((IProperty)field_176330_a)).func_176765_a();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void getSubBlocks(Item itemIn, CreativeTabs tab, List<ItemStack> list) {
/* 110 */     for (int var4 = 0; var4 < 16; var4++)
/*     */     {
/* 112 */       list.add(new ItemStack(itemIn, 1, var4));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IBlockState getStateFromMeta(int meta) {
/* 121 */     return getDefaultState().withProperty((IProperty)field_176330_a, (Comparable)EnumDyeColor.func_176764_b(meta));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getMetaFromState(IBlockState state) {
/* 129 */     return ((EnumDyeColor)state.getValue((IProperty)field_176330_a)).func_176765_a();
/*     */   }
/*     */ 
/*     */   
/*     */   protected BlockState createBlockState() {
/* 134 */     return new BlockState(this, new IProperty[] { (IProperty)field_176330_a });
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\block\BlockCarpet.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */