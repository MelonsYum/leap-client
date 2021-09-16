/*    */ package net.minecraft.block;
/*    */ 
/*    */ import net.minecraft.block.state.IBlockState;
/*    */ import net.minecraft.dispenser.IBlockSource;
/*    */ import net.minecraft.tileentity.TileEntity;
/*    */ import net.minecraft.util.BlockPos;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ public class BlockSourceImpl
/*    */   implements IBlockSource
/*    */ {
/*    */   private final World worldObj;
/*    */   private final BlockPos pos;
/*    */   private static final String __OBFID = "CL_00001194";
/*    */   
/*    */   public BlockSourceImpl(World worldIn, BlockPos p_i46023_2_) {
/* 17 */     this.worldObj = worldIn;
/* 18 */     this.pos = p_i46023_2_;
/*    */   }
/*    */ 
/*    */   
/*    */   public World getWorld() {
/* 23 */     return this.worldObj;
/*    */   }
/*    */ 
/*    */   
/*    */   public double getX() {
/* 28 */     return this.pos.getX() + 0.5D;
/*    */   }
/*    */ 
/*    */   
/*    */   public double getY() {
/* 33 */     return this.pos.getY() + 0.5D;
/*    */   }
/*    */ 
/*    */   
/*    */   public double getZ() {
/* 38 */     return this.pos.getZ() + 0.5D;
/*    */   }
/*    */ 
/*    */   
/*    */   public BlockPos getBlockPos() {
/* 43 */     return this.pos;
/*    */   }
/*    */ 
/*    */   
/*    */   public Block getBlock() {
/* 48 */     return this.worldObj.getBlockState(this.pos).getBlock();
/*    */   }
/*    */ 
/*    */   
/*    */   public int getBlockMetadata() {
/* 53 */     IBlockState var1 = this.worldObj.getBlockState(this.pos);
/* 54 */     return var1.getBlock().getMetaFromState(var1);
/*    */   }
/*    */ 
/*    */   
/*    */   public TileEntity getBlockTileEntity() {
/* 59 */     return this.worldObj.getTileEntity(this.pos);
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\block\BlockSourceImpl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */