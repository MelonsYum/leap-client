/*    */ package net.minecraft.block;
/*    */ 
/*    */ import java.util.List;
/*    */ import net.minecraft.block.material.Material;
/*    */ import net.minecraft.block.properties.IProperty;
/*    */ import net.minecraft.block.state.IBlockState;
/*    */ import net.minecraft.creativetab.CreativeTabs;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.init.Blocks;
/*    */ import net.minecraft.util.AxisAlignedBB;
/*    */ import net.minecraft.util.BlockPos;
/*    */ import net.minecraft.world.IBlockAccess;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ public class BlockLilyPad
/*    */   extends BlockBush
/*    */ {
/*    */   private static final String __OBFID = "CL_00000332";
/*    */   
/*    */   protected BlockLilyPad() {
/* 21 */     float var1 = 0.5F;
/* 22 */     float var2 = 0.015625F;
/* 23 */     setBlockBounds(0.5F - var1, 0.0F, 0.5F - var1, 0.5F + var1, var2, 0.5F + var1);
/* 24 */     setCreativeTab(CreativeTabs.tabDecorations);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void addCollisionBoxesToList(World worldIn, BlockPos pos, IBlockState state, AxisAlignedBB mask, List list, Entity collidingEntity) {
/* 34 */     if (collidingEntity == null || !(collidingEntity instanceof net.minecraft.entity.item.EntityBoat))
/*    */     {
/* 36 */       super.addCollisionBoxesToList(worldIn, pos, state, mask, list, collidingEntity);
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public AxisAlignedBB getCollisionBoundingBox(World worldIn, BlockPos pos, IBlockState state) {
/* 42 */     return new AxisAlignedBB(pos.getX() + this.minX, pos.getY() + this.minY, pos.getZ() + this.minZ, pos.getX() + this.maxX, pos.getY() + this.maxY, pos.getZ() + this.maxZ);
/*    */   }
/*    */ 
/*    */   
/*    */   public int getBlockColor() {
/* 47 */     return 7455580;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getRenderColor(IBlockState state) {
/* 52 */     return 7455580;
/*    */   }
/*    */ 
/*    */   
/*    */   public int colorMultiplier(IBlockAccess worldIn, BlockPos pos, int renderPass) {
/* 57 */     return 2129968;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected boolean canPlaceBlockOn(Block ground) {
/* 65 */     return (ground == Blocks.water);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean canBlockStay(World worldIn, BlockPos p_180671_2_, IBlockState p_180671_3_) {
/* 70 */     if (p_180671_2_.getY() >= 0 && p_180671_2_.getY() < 256) {
/*    */       
/* 72 */       IBlockState var4 = worldIn.getBlockState(p_180671_2_.offsetDown());
/* 73 */       return (var4.getBlock().getMaterial() == Material.water && ((Integer)var4.getValue((IProperty)BlockLiquid.LEVEL)).intValue() == 0);
/*    */     } 
/*    */ 
/*    */     
/* 77 */     return false;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int getMetaFromState(IBlockState state) {
/* 86 */     return 0;
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\block\BlockLilyPad.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */