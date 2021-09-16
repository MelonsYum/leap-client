/*    */ package net.minecraft.block;
/*    */ 
/*    */ import net.minecraft.block.material.Material;
/*    */ import net.minecraft.block.state.IBlockState;
/*    */ import net.minecraft.tileentity.TileEntity;
/*    */ import net.minecraft.util.BlockPos;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ public abstract class BlockContainer
/*    */   extends Block
/*    */   implements ITileEntityProvider {
/*    */   private static final String __OBFID = "CL_00000193";
/*    */   
/*    */   protected BlockContainer(Material materialIn) {
/* 15 */     super(materialIn);
/* 16 */     this.isBlockContainer = true;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int getRenderType() {
/* 24 */     return -1;
/*    */   }
/*    */ 
/*    */   
/*    */   public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
/* 29 */     super.breakBlock(worldIn, pos, state);
/* 30 */     worldIn.removeTileEntity(pos);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean onBlockEventReceived(World worldIn, BlockPos pos, IBlockState state, int eventID, int eventParam) {
/* 38 */     super.onBlockEventReceived(worldIn, pos, state, eventID, eventParam);
/* 39 */     TileEntity var6 = worldIn.getTileEntity(pos);
/* 40 */     return (var6 == null) ? false : var6.receiveClientEvent(eventID, eventParam);
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\block\BlockContainer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */