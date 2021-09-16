/*    */ package net.minecraft.block;
/*    */ 
/*    */ import java.util.Random;
/*    */ import net.minecraft.block.material.Material;
/*    */ import net.minecraft.block.state.IBlockState;
/*    */ import net.minecraft.creativetab.CreativeTabs;
/*    */ import net.minecraft.init.Blocks;
/*    */ import net.minecraft.util.AxisAlignedBB;
/*    */ import net.minecraft.util.BlockPos;
/*    */ import net.minecraft.util.EnumWorldBlockLayer;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ public class BlockBush
/*    */   extends Block
/*    */ {
/*    */   private static final String __OBFID = "CL_00000208";
/*    */   
/*    */   protected BlockBush(Material materialIn) {
/* 19 */     super(materialIn);
/* 20 */     setTickRandomly(true);
/* 21 */     float var2 = 0.2F;
/* 22 */     setBlockBounds(0.5F - var2, 0.0F, 0.5F - var2, 0.5F + var2, var2 * 3.0F, 0.5F + var2);
/* 23 */     setCreativeTab(CreativeTabs.tabDecorations);
/*    */   }
/*    */ 
/*    */   
/*    */   protected BlockBush() {
/* 28 */     this(Material.plants);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean canPlaceBlockAt(World worldIn, BlockPos pos) {
/* 33 */     return (super.canPlaceBlockAt(worldIn, pos) && canPlaceBlockOn(worldIn.getBlockState(pos.offsetDown()).getBlock()));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected boolean canPlaceBlockOn(Block ground) {
/* 41 */     return !(ground != Blocks.grass && ground != Blocks.dirt && ground != Blocks.farmland);
/*    */   }
/*    */ 
/*    */   
/*    */   public void onNeighborBlockChange(World worldIn, BlockPos pos, IBlockState state, Block neighborBlock) {
/* 46 */     super.onNeighborBlockChange(worldIn, pos, state, neighborBlock);
/* 47 */     func_176475_e(worldIn, pos, state);
/*    */   }
/*    */ 
/*    */   
/*    */   public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
/* 52 */     func_176475_e(worldIn, pos, state);
/*    */   }
/*    */ 
/*    */   
/*    */   protected void func_176475_e(World worldIn, BlockPos p_176475_2_, IBlockState p_176475_3_) {
/* 57 */     if (!canBlockStay(worldIn, p_176475_2_, p_176475_3_)) {
/*    */       
/* 59 */       dropBlockAsItem(worldIn, p_176475_2_, p_176475_3_, 0);
/* 60 */       worldIn.setBlockState(p_176475_2_, Blocks.air.getDefaultState(), 3);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean canBlockStay(World worldIn, BlockPos p_180671_2_, IBlockState p_180671_3_) {
/* 66 */     return canPlaceBlockOn(worldIn.getBlockState(p_180671_2_.offsetDown()).getBlock());
/*    */   }
/*    */ 
/*    */   
/*    */   public AxisAlignedBB getCollisionBoundingBox(World worldIn, BlockPos pos, IBlockState state) {
/* 71 */     return null;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isOpaqueCube() {
/* 76 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isFullCube() {
/* 81 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public EnumWorldBlockLayer getBlockLayer() {
/* 86 */     return EnumWorldBlockLayer.CUTOUT;
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\block\BlockBush.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */