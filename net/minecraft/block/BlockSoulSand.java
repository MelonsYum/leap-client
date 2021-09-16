/*    */ package net.minecraft.block;
/*    */ 
/*    */ import net.minecraft.block.material.Material;
/*    */ import net.minecraft.block.state.IBlockState;
/*    */ import net.minecraft.creativetab.CreativeTabs;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.util.AxisAlignedBB;
/*    */ import net.minecraft.util.BlockPos;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ public class BlockSoulSand
/*    */   extends Block
/*    */ {
/*    */   private static final String __OBFID = "CL_00000310";
/*    */   
/*    */   public BlockSoulSand() {
/* 17 */     super(Material.sand);
/* 18 */     setCreativeTab(CreativeTabs.tabBlock);
/*    */   }
/*    */ 
/*    */   
/*    */   public AxisAlignedBB getCollisionBoundingBox(World worldIn, BlockPos pos, IBlockState state) {
/* 23 */     float var4 = 0.125F;
/* 24 */     return new AxisAlignedBB(pos.getX(), pos.getY(), pos.getZ(), (pos.getX() + 1), ((pos.getY() + 1) - var4), (pos.getZ() + 1));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void onEntityCollidedWithBlock(World worldIn, BlockPos pos, IBlockState state, Entity entityIn) {
/* 32 */     entityIn.motionX *= 0.4D;
/* 33 */     entityIn.motionZ *= 0.4D;
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\block\BlockSoulSand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */