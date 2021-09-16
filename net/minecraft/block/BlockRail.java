/*    */ package net.minecraft.block;
/*    */ 
/*    */ import net.minecraft.block.properties.IProperty;
/*    */ import net.minecraft.block.properties.PropertyEnum;
/*    */ import net.minecraft.block.state.BlockState;
/*    */ import net.minecraft.block.state.IBlockState;
/*    */ import net.minecraft.util.BlockPos;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ public class BlockRail
/*    */   extends BlockRailBase {
/* 12 */   public static final PropertyEnum field_176565_b = PropertyEnum.create("shape", BlockRailBase.EnumRailDirection.class);
/*    */   
/*    */   private static final String __OBFID = "CL_00000293";
/*    */   
/*    */   protected BlockRail() {
/* 17 */     super(false);
/* 18 */     setDefaultState(this.blockState.getBaseState().withProperty((IProperty)field_176565_b, BlockRailBase.EnumRailDirection.NORTH_SOUTH));
/*    */   }
/*    */ 
/*    */   
/*    */   protected void func_176561_b(World worldIn, BlockPos p_176561_2_, IBlockState p_176561_3_, Block p_176561_4_) {
/* 23 */     if (p_176561_4_.canProvidePower() && (new BlockRailBase.Rail(this, worldIn, p_176561_2_, p_176561_3_)).countAdjacentRails() == 3)
/*    */     {
/* 25 */       func_176564_a(worldIn, p_176561_2_, p_176561_3_, false);
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public IProperty func_176560_l() {
/* 31 */     return (IProperty)field_176565_b;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public IBlockState getStateFromMeta(int meta) {
/* 39 */     return getDefaultState().withProperty((IProperty)field_176565_b, BlockRailBase.EnumRailDirection.func_177016_a(meta));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int getMetaFromState(IBlockState state) {
/* 47 */     return ((BlockRailBase.EnumRailDirection)state.getValue((IProperty)field_176565_b)).func_177015_a();
/*    */   }
/*    */ 
/*    */   
/*    */   protected BlockState createBlockState() {
/* 52 */     return new BlockState(this, new IProperty[] { (IProperty)field_176565_b });
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\block\BlockRail.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */