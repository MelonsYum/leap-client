/*    */ package net.minecraft.block.state.pattern;
/*    */ 
/*    */ import com.google.common.base.Predicate;
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.block.state.IBlockState;
/*    */ 
/*    */ public class BlockHelper
/*    */   implements Predicate
/*    */ {
/*    */   private final Block block;
/*    */   private static final String __OBFID = "CL_00002020";
/*    */   
/*    */   private BlockHelper(Block p_i45654_1_) {
/* 14 */     this.block = p_i45654_1_;
/*    */   }
/*    */ 
/*    */   
/*    */   public static BlockHelper forBlock(Block p_177642_0_) {
/* 19 */     return new BlockHelper(p_177642_0_);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isBlockEqualTo(IBlockState p_177643_1_) {
/* 24 */     return (p_177643_1_ != null && p_177643_1_.getBlock() == this.block);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean apply(Object p_apply_1_) {
/* 29 */     return isBlockEqualTo((IBlockState)p_apply_1_);
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\block\state\pattern\BlockHelper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */