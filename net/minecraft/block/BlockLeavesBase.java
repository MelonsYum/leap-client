/*    */ package net.minecraft.block;
/*    */ 
/*    */ import java.util.IdentityHashMap;
/*    */ import java.util.Map;
/*    */ import net.minecraft.block.material.Material;
/*    */ import net.minecraft.util.BlockPos;
/*    */ import net.minecraft.util.EnumFacing;
/*    */ import net.minecraft.world.IBlockAccess;
/*    */ import optifine.Config;
/*    */ 
/*    */ public class BlockLeavesBase
/*    */   extends Block {
/*    */   protected boolean field_150121_P;
/*    */   private static final String __OBFID = "CL_00000326";
/* 15 */   private static Map mapOriginalOpacity = new IdentityHashMap<>();
/*    */ 
/*    */   
/*    */   protected BlockLeavesBase(Material materialIn, boolean fancyGraphics) {
/* 19 */     super(materialIn);
/* 20 */     this.field_150121_P = fancyGraphics;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isOpaqueCube() {
/* 25 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean shouldSideBeRendered(IBlockAccess worldIn, BlockPos pos, EnumFacing side) {
/* 30 */     return (Config.isCullFacesLeaves() && worldIn.getBlockState(pos).getBlock() == this) ? false : super.shouldSideBeRendered(worldIn, pos, side);
/*    */   }
/*    */ 
/*    */   
/*    */   public static void setLightOpacity(Block block, int opacity) {
/* 35 */     if (!mapOriginalOpacity.containsKey(block))
/*    */     {
/* 37 */       mapOriginalOpacity.put(block, Integer.valueOf(block.getLightOpacity()));
/*    */     }
/*    */     
/* 40 */     block.setLightOpacity(opacity);
/*    */   }
/*    */ 
/*    */   
/*    */   public static void restoreLightOpacity(Block block) {
/* 45 */     if (mapOriginalOpacity.containsKey(block)) {
/*    */       
/* 47 */       int opacity = ((Integer)mapOriginalOpacity.get(block)).intValue();
/* 48 */       setLightOpacity(block, opacity);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\block\BlockLeavesBase.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */