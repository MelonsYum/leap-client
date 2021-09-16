/*    */ package net.minecraft.item;
/*    */ 
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.creativetab.CreativeTabs;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.init.Blocks;
/*    */ import net.minecraft.util.BlockPos;
/*    */ import net.minecraft.util.EnumFacing;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ 
/*    */ public class ItemRedstone
/*    */   extends Item
/*    */ {
/*    */   private static final String __OBFID = "CL_00000058";
/*    */   
/*    */   public ItemRedstone() {
/* 18 */     setCreativeTab(CreativeTabs.tabRedstone);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ) {
/* 29 */     boolean var9 = worldIn.getBlockState(pos).getBlock().isReplaceable(worldIn, pos);
/* 30 */     BlockPos var10 = var9 ? pos : pos.offset(side);
/*    */     
/* 32 */     if (!playerIn.func_175151_a(var10, side, stack))
/*    */     {
/* 34 */       return false;
/*    */     }
/*    */ 
/*    */     
/* 38 */     Block var11 = worldIn.getBlockState(var10).getBlock();
/*    */     
/* 40 */     if (!worldIn.canBlockBePlaced(var11, var10, false, side, null, stack))
/*    */     {
/* 42 */       return false;
/*    */     }
/* 44 */     if (Blocks.redstone_wire.canPlaceBlockAt(worldIn, var10)) {
/*    */       
/* 46 */       stack.stackSize--;
/* 47 */       worldIn.setBlockState(var10, Blocks.redstone_wire.getDefaultState());
/* 48 */       return true;
/*    */     } 
/*    */ 
/*    */     
/* 52 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\item\ItemRedstone.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */