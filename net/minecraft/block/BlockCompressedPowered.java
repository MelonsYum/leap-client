/*    */ package net.minecraft.block;
/*    */ 
/*    */ import net.minecraft.block.material.MapColor;
/*    */ import net.minecraft.block.state.IBlockState;
/*    */ import net.minecraft.creativetab.CreativeTabs;
/*    */ import net.minecraft.util.BlockPos;
/*    */ import net.minecraft.util.EnumFacing;
/*    */ import net.minecraft.world.IBlockAccess;
/*    */ 
/*    */ public class BlockCompressedPowered
/*    */   extends BlockCompressed
/*    */ {
/*    */   private static final String __OBFID = "CL_00000287";
/*    */   
/*    */   public BlockCompressedPowered(MapColor p_i45416_1_) {
/* 16 */     super(p_i45416_1_);
/* 17 */     setCreativeTab(CreativeTabs.tabRedstone);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean canProvidePower() {
/* 25 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public int isProvidingWeakPower(IBlockAccess worldIn, BlockPos pos, IBlockState state, EnumFacing side) {
/* 30 */     return 15;
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\block\BlockCompressedPowered.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */