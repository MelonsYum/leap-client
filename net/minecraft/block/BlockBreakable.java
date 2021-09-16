/*    */ package net.minecraft.block;
/*    */ 
/*    */ import net.minecraft.block.material.Material;
/*    */ import net.minecraft.block.state.IBlockState;
/*    */ import net.minecraft.init.Blocks;
/*    */ import net.minecraft.util.BlockPos;
/*    */ import net.minecraft.util.EnumFacing;
/*    */ import net.minecraft.world.IBlockAccess;
/*    */ 
/*    */ public class BlockBreakable
/*    */   extends Block
/*    */ {
/*    */   private boolean ignoreSimilarity;
/*    */   private static final String __OBFID = "CL_00000254";
/*    */   
/*    */   protected BlockBreakable(Material p_i45712_1_, boolean p_i45712_2_) {
/* 17 */     super(p_i45712_1_);
/* 18 */     this.ignoreSimilarity = p_i45712_2_;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isOpaqueCube() {
/* 23 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean shouldSideBeRendered(IBlockAccess worldIn, BlockPos pos, EnumFacing side) {
/* 28 */     IBlockState var4 = worldIn.getBlockState(pos);
/* 29 */     Block var5 = var4.getBlock();
/*    */     
/* 31 */     if (this == Blocks.glass || this == Blocks.stained_glass) {
/*    */       
/* 33 */       if (worldIn.getBlockState(pos.offset(side.getOpposite())) != var4)
/*    */       {
/* 35 */         return true;
/*    */       }
/*    */       
/* 38 */       if (var5 == this)
/*    */       {
/* 40 */         return false;
/*    */       }
/*    */     } 
/*    */     
/* 44 */     return (!this.ignoreSimilarity && var5 == this) ? false : super.shouldSideBeRendered(worldIn, pos, side);
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\block\BlockBreakable.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */