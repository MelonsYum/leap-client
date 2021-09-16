/*    */ package net.minecraft.item;
/*    */ 
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.block.BlockDoor;
/*    */ import net.minecraft.block.properties.IProperty;
/*    */ import net.minecraft.block.state.IBlockState;
/*    */ import net.minecraft.creativetab.CreativeTabs;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.util.BlockPos;
/*    */ import net.minecraft.util.EnumFacing;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ public class ItemDoor
/*    */   extends Item {
/*    */   private Block field_179236_a;
/*    */   private static final String __OBFID = "CL_00000020";
/*    */   
/*    */   public ItemDoor(Block p_i45788_1_) {
/* 19 */     this.field_179236_a = p_i45788_1_;
/* 20 */     setCreativeTab(CreativeTabs.tabRedstone);
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
/* 31 */     if (side != EnumFacing.UP)
/*    */     {
/* 33 */       return false;
/*    */     }
/*    */ 
/*    */     
/* 37 */     IBlockState var9 = worldIn.getBlockState(pos);
/* 38 */     Block var10 = var9.getBlock();
/*    */     
/* 40 */     if (!var10.isReplaceable(worldIn, pos))
/*    */     {
/* 42 */       pos = pos.offset(side);
/*    */     }
/*    */     
/* 45 */     if (!playerIn.func_175151_a(pos, side, stack))
/*    */     {
/* 47 */       return false;
/*    */     }
/* 49 */     if (!this.field_179236_a.canPlaceBlockAt(worldIn, pos))
/*    */     {
/* 51 */       return false;
/*    */     }
/*    */ 
/*    */     
/* 55 */     func_179235_a(worldIn, pos, EnumFacing.fromAngle(playerIn.rotationYaw), this.field_179236_a);
/* 56 */     stack.stackSize--;
/* 57 */     return true;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static void func_179235_a(World worldIn, BlockPos p_179235_1_, EnumFacing p_179235_2_, Block p_179235_3_) {
/* 64 */     BlockPos var4 = p_179235_1_.offset(p_179235_2_.rotateY());
/* 65 */     BlockPos var5 = p_179235_1_.offset(p_179235_2_.rotateYCCW());
/* 66 */     int var6 = (worldIn.getBlockState(var5).getBlock().isNormalCube() ? 1 : 0) + (worldIn.getBlockState(var5.offsetUp()).getBlock().isNormalCube() ? 1 : 0);
/* 67 */     int var7 = (worldIn.getBlockState(var4).getBlock().isNormalCube() ? 1 : 0) + (worldIn.getBlockState(var4.offsetUp()).getBlock().isNormalCube() ? 1 : 0);
/* 68 */     boolean var8 = !(worldIn.getBlockState(var5).getBlock() != p_179235_3_ && worldIn.getBlockState(var5.offsetUp()).getBlock() != p_179235_3_);
/* 69 */     boolean var9 = !(worldIn.getBlockState(var4).getBlock() != p_179235_3_ && worldIn.getBlockState(var4.offsetUp()).getBlock() != p_179235_3_);
/* 70 */     boolean var10 = false;
/*    */     
/* 72 */     if ((var8 && !var9) || var7 > var6)
/*    */     {
/* 74 */       var10 = true;
/*    */     }
/*    */     
/* 77 */     BlockPos var11 = p_179235_1_.offsetUp();
/* 78 */     IBlockState var12 = p_179235_3_.getDefaultState().withProperty((IProperty)BlockDoor.FACING_PROP, (Comparable)p_179235_2_).withProperty((IProperty)BlockDoor.HINGEPOSITION_PROP, var10 ? (Comparable)BlockDoor.EnumHingePosition.RIGHT : (Comparable)BlockDoor.EnumHingePosition.LEFT);
/* 79 */     worldIn.setBlockState(p_179235_1_, var12.withProperty((IProperty)BlockDoor.HALF_PROP, (Comparable)BlockDoor.EnumDoorHalf.LOWER), 2);
/* 80 */     worldIn.setBlockState(var11, var12.withProperty((IProperty)BlockDoor.HALF_PROP, (Comparable)BlockDoor.EnumDoorHalf.UPPER), 2);
/* 81 */     worldIn.notifyNeighborsOfStateChange(p_179235_1_, p_179235_3_);
/* 82 */     worldIn.notifyNeighborsOfStateChange(var11, p_179235_3_);
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\item\ItemDoor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */