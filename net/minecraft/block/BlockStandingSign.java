/*    */ package net.minecraft.block;
/*    */ 
/*    */ import net.minecraft.block.properties.IProperty;
/*    */ import net.minecraft.block.properties.PropertyInteger;
/*    */ import net.minecraft.block.state.BlockState;
/*    */ import net.minecraft.block.state.IBlockState;
/*    */ import net.minecraft.util.BlockPos;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ public class BlockStandingSign
/*    */   extends BlockSign {
/* 12 */   public static final PropertyInteger ROTATION_PROP = PropertyInteger.create("rotation", 0, 15);
/*    */   
/*    */   private static final String __OBFID = "CL_00002060";
/*    */   
/*    */   public BlockStandingSign() {
/* 17 */     setDefaultState(this.blockState.getBaseState().withProperty((IProperty)ROTATION_PROP, Integer.valueOf(0)));
/*    */   }
/*    */ 
/*    */   
/*    */   public void onNeighborBlockChange(World worldIn, BlockPos pos, IBlockState state, Block neighborBlock) {
/* 22 */     if (!worldIn.getBlockState(pos.offsetDown()).getBlock().getMaterial().isSolid()) {
/*    */       
/* 24 */       dropBlockAsItem(worldIn, pos, state, 0);
/* 25 */       worldIn.setBlockToAir(pos);
/*    */     } 
/*    */     
/* 28 */     super.onNeighborBlockChange(worldIn, pos, state, neighborBlock);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public IBlockState getStateFromMeta(int meta) {
/* 36 */     return getDefaultState().withProperty((IProperty)ROTATION_PROP, Integer.valueOf(meta));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int getMetaFromState(IBlockState state) {
/* 44 */     return ((Integer)state.getValue((IProperty)ROTATION_PROP)).intValue();
/*    */   }
/*    */ 
/*    */   
/*    */   protected BlockState createBlockState() {
/* 49 */     return new BlockState(this, new IProperty[] { (IProperty)ROTATION_PROP });
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\block\BlockStandingSign.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */