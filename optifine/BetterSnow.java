/*    */ package optifine;
/*    */ 
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.block.BlockLever;
/*    */ import net.minecraft.block.BlockTorch;
/*    */ import net.minecraft.block.properties.IProperty;
/*    */ import net.minecraft.block.state.IBlockState;
/*    */ import net.minecraft.client.resources.model.IBakedModel;
/*    */ import net.minecraft.init.Blocks;
/*    */ import net.minecraft.util.BlockPos;
/*    */ import net.minecraft.util.EnumFacing;
/*    */ import net.minecraft.world.IBlockAccess;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BetterSnow
/*    */ {
/* 29 */   private static IBakedModel modelSnowLayer = null;
/*    */ 
/*    */   
/*    */   public static void update() {
/* 33 */     modelSnowLayer = Config.getMinecraft().getBlockRendererDispatcher().func_175023_a().func_178125_b(Blocks.snow_layer.getDefaultState());
/*    */   }
/*    */ 
/*    */   
/*    */   public static IBakedModel getModelSnowLayer() {
/* 38 */     return modelSnowLayer;
/*    */   }
/*    */ 
/*    */   
/*    */   public static IBlockState getStateSnowLayer() {
/* 43 */     return Blocks.snow_layer.getDefaultState();
/*    */   }
/*    */ 
/*    */   
/*    */   public static boolean shouldRender(IBlockAccess blockAccess, Block block, IBlockState blockState, BlockPos blockPos) {
/* 48 */     return !checkBlock(block, blockState) ? false : hasSnowNeighbours(blockAccess, blockPos);
/*    */   }
/*    */ 
/*    */   
/*    */   private static boolean hasSnowNeighbours(IBlockAccess blockAccess, BlockPos pos) {
/* 53 */     Block blockSnow = Blocks.snow_layer;
/* 54 */     return (blockAccess.getBlockState(pos.offsetNorth()).getBlock() != blockSnow && blockAccess.getBlockState(pos.offsetSouth()).getBlock() != blockSnow && blockAccess.getBlockState(pos.offsetWest()).getBlock() != blockSnow && blockAccess.getBlockState(pos.offsetEast()).getBlock() != blockSnow) ? false : blockAccess.getBlockState(pos.offsetDown()).getBlock().isOpaqueCube();
/*    */   }
/*    */ 
/*    */   
/*    */   private static boolean checkBlock(Block block, IBlockState blockState) {
/* 59 */     if (block.isFullCube())
/*    */     {
/* 61 */       return false;
/*    */     }
/* 63 */     if (block.isOpaqueCube())
/*    */     {
/* 65 */       return false;
/*    */     }
/* 67 */     if (block instanceof net.minecraft.block.BlockSnow)
/*    */     {
/* 69 */       return false;
/*    */     }
/* 71 */     if (block instanceof net.minecraft.block.BlockBush && (block instanceof net.minecraft.block.BlockDoublePlant || block instanceof net.minecraft.block.BlockFlower || block instanceof net.minecraft.block.BlockMushroom || block instanceof net.minecraft.block.BlockSapling || block instanceof net.minecraft.block.BlockTallGrass))
/*    */     {
/* 73 */       return true;
/*    */     }
/* 75 */     if (!(block instanceof net.minecraft.block.BlockFence) && !(block instanceof net.minecraft.block.BlockFenceGate) && !(block instanceof net.minecraft.block.BlockFlowerPot) && !(block instanceof net.minecraft.block.BlockPane) && !(block instanceof net.minecraft.block.BlockReed) && !(block instanceof net.minecraft.block.BlockWall)) {
/*    */       
/* 77 */       if (block instanceof net.minecraft.block.BlockRedstoneTorch && blockState.getValue((IProperty)BlockTorch.FACING_PROP) == EnumFacing.UP)
/*    */       {
/* 79 */         return true;
/*    */       }
/*    */ 
/*    */       
/* 83 */       if (block instanceof BlockLever) {
/*    */         
/* 85 */         Comparable orient = blockState.getValue((IProperty)BlockLever.FACING);
/*    */         
/* 87 */         if (orient == BlockLever.EnumOrientation.UP_X || orient == BlockLever.EnumOrientation.UP_Z)
/*    */         {
/* 89 */           return true;
/*    */         }
/*    */       } 
/*    */       
/* 93 */       return false;
/*    */     } 
/*    */ 
/*    */ 
/*    */     
/* 98 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\optifine\BetterSnow.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */