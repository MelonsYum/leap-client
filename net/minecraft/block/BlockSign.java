/*    */ package net.minecraft.block;
/*    */ 
/*    */ import java.util.Random;
/*    */ import net.minecraft.block.material.Material;
/*    */ import net.minecraft.block.state.IBlockState;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.init.Items;
/*    */ import net.minecraft.item.Item;
/*    */ import net.minecraft.tileentity.TileEntity;
/*    */ import net.minecraft.tileentity.TileEntitySign;
/*    */ import net.minecraft.util.AxisAlignedBB;
/*    */ import net.minecraft.util.BlockPos;
/*    */ import net.minecraft.util.EnumFacing;
/*    */ import net.minecraft.world.IBlockAccess;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ public class BlockSign
/*    */   extends BlockContainer
/*    */ {
/*    */   private static final String __OBFID = "CL_00000306";
/*    */   
/*    */   protected BlockSign() {
/* 23 */     super(Material.wood);
/* 24 */     float var1 = 0.25F;
/* 25 */     float var2 = 1.0F;
/* 26 */     setBlockBounds(0.5F - var1, 0.0F, 0.5F - var1, 0.5F + var1, var2, 0.5F + var1);
/*    */   }
/*    */ 
/*    */   
/*    */   public AxisAlignedBB getCollisionBoundingBox(World worldIn, BlockPos pos, IBlockState state) {
/* 31 */     return null;
/*    */   }
/*    */ 
/*    */   
/*    */   public AxisAlignedBB getSelectedBoundingBox(World worldIn, BlockPos pos) {
/* 36 */     setBlockBoundsBasedOnState((IBlockAccess)worldIn, pos);
/* 37 */     return super.getSelectedBoundingBox(worldIn, pos);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isFullCube() {
/* 42 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isPassable(IBlockAccess blockAccess, BlockPos pos) {
/* 47 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isOpaqueCube() {
/* 52 */     return false;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public TileEntity createNewTileEntity(World worldIn, int meta) {
/* 60 */     return (TileEntity)new TileEntitySign();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Item getItemDropped(IBlockState state, Random rand, int fortune) {
/* 70 */     return Items.sign;
/*    */   }
/*    */ 
/*    */   
/*    */   public Item getItem(World worldIn, BlockPos pos) {
/* 75 */     return Items.sign;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumFacing side, float hitX, float hitY, float hitZ) {
/* 80 */     if (worldIn.isRemote)
/*    */     {
/* 82 */       return true;
/*    */     }
/*    */ 
/*    */     
/* 86 */     TileEntity var9 = worldIn.getTileEntity(pos);
/* 87 */     return (var9 instanceof TileEntitySign) ? ((TileEntitySign)var9).func_174882_b(playerIn) : false;
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\block\BlockSign.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */