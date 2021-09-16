/*    */ package net.minecraft.block;
/*    */ 
/*    */ import java.util.Random;
/*    */ import net.minecraft.block.material.Material;
/*    */ import net.minecraft.block.state.IBlockState;
/*    */ import net.minecraft.init.Blocks;
/*    */ import net.minecraft.item.Item;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.BlockPos;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ public class BlockRedstoneLight
/*    */   extends Block
/*    */ {
/*    */   private final boolean isOn;
/*    */   private static final String __OBFID = "CL_00000297";
/*    */   
/*    */   public BlockRedstoneLight(boolean p_i45421_1_) {
/* 19 */     super(Material.redstoneLight);
/* 20 */     this.isOn = p_i45421_1_;
/*    */     
/* 22 */     if (p_i45421_1_)
/*    */     {
/* 24 */       setLightLevel(1.0F);
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state) {
/* 30 */     if (!worldIn.isRemote)
/*    */     {
/* 32 */       if (this.isOn && !worldIn.isBlockPowered(pos)) {
/*    */         
/* 34 */         worldIn.setBlockState(pos, Blocks.redstone_lamp.getDefaultState(), 2);
/*    */       }
/* 36 */       else if (!this.isOn && worldIn.isBlockPowered(pos)) {
/*    */         
/* 38 */         worldIn.setBlockState(pos, Blocks.lit_redstone_lamp.getDefaultState(), 2);
/*    */       } 
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public void onNeighborBlockChange(World worldIn, BlockPos pos, IBlockState state, Block neighborBlock) {
/* 45 */     if (!worldIn.isRemote)
/*    */     {
/* 47 */       if (this.isOn && !worldIn.isBlockPowered(pos)) {
/*    */         
/* 49 */         worldIn.scheduleUpdate(pos, this, 4);
/*    */       }
/* 51 */       else if (!this.isOn && worldIn.isBlockPowered(pos)) {
/*    */         
/* 53 */         worldIn.setBlockState(pos, Blocks.lit_redstone_lamp.getDefaultState(), 2);
/*    */       } 
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
/* 60 */     if (!worldIn.isRemote)
/*    */     {
/* 62 */       if (this.isOn && !worldIn.isBlockPowered(pos))
/*    */       {
/* 64 */         worldIn.setBlockState(pos, Blocks.redstone_lamp.getDefaultState(), 2);
/*    */       }
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Item getItemDropped(IBlockState state, Random rand, int fortune) {
/* 76 */     return Item.getItemFromBlock(Blocks.redstone_lamp);
/*    */   }
/*    */ 
/*    */   
/*    */   public Item getItem(World worldIn, BlockPos pos) {
/* 81 */     return Item.getItemFromBlock(Blocks.redstone_lamp);
/*    */   }
/*    */ 
/*    */   
/*    */   protected ItemStack createStackedBlock(IBlockState state) {
/* 86 */     return new ItemStack(Blocks.redstone_lamp);
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\block\BlockRedstoneLight.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */