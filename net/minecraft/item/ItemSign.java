/*    */ package net.minecraft.item;
/*    */ 
/*    */ import net.minecraft.block.BlockStandingSign;
/*    */ import net.minecraft.block.BlockWallSign;
/*    */ import net.minecraft.block.properties.IProperty;
/*    */ import net.minecraft.creativetab.CreativeTabs;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.init.Blocks;
/*    */ import net.minecraft.tileentity.TileEntity;
/*    */ import net.minecraft.tileentity.TileEntitySign;
/*    */ import net.minecraft.util.BlockPos;
/*    */ import net.minecraft.util.EnumFacing;
/*    */ import net.minecraft.util.MathHelper;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ public class ItemSign
/*    */   extends Item {
/*    */   private static final String __OBFID = "CL_00000064";
/*    */   
/*    */   public ItemSign() {
/* 21 */     this.maxStackSize = 16;
/* 22 */     setCreativeTab(CreativeTabs.tabDecorations);
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
/* 33 */     if (side == EnumFacing.DOWN)
/*    */     {
/* 35 */       return false;
/*    */     }
/* 37 */     if (!worldIn.getBlockState(pos).getBlock().getMaterial().isSolid())
/*    */     {
/* 39 */       return false;
/*    */     }
/*    */ 
/*    */     
/* 43 */     pos = pos.offset(side);
/*    */     
/* 45 */     if (!playerIn.func_175151_a(pos, side, stack))
/*    */     {
/* 47 */       return false;
/*    */     }
/* 49 */     if (!Blocks.standing_sign.canPlaceBlockAt(worldIn, pos))
/*    */     {
/* 51 */       return false;
/*    */     }
/* 53 */     if (worldIn.isRemote)
/*    */     {
/* 55 */       return true;
/*    */     }
/*    */ 
/*    */     
/* 59 */     if (side == EnumFacing.UP) {
/*    */       
/* 61 */       int var9 = MathHelper.floor_double(((playerIn.rotationYaw + 180.0F) * 16.0F / 360.0F) + 0.5D) & 0xF;
/* 62 */       worldIn.setBlockState(pos, Blocks.standing_sign.getDefaultState().withProperty((IProperty)BlockStandingSign.ROTATION_PROP, Integer.valueOf(var9)), 3);
/*    */     }
/*    */     else {
/*    */       
/* 66 */       worldIn.setBlockState(pos, Blocks.wall_sign.getDefaultState().withProperty((IProperty)BlockWallSign.field_176412_a, (Comparable)side), 3);
/*    */     } 
/*    */     
/* 69 */     stack.stackSize--;
/* 70 */     TileEntity var10 = worldIn.getTileEntity(pos);
/*    */     
/* 72 */     if (var10 instanceof TileEntitySign && !ItemBlock.setTileEntityNBT(worldIn, pos, stack))
/*    */     {
/* 74 */       playerIn.func_175141_a((TileEntitySign)var10);
/*    */     }
/*    */     
/* 77 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\item\ItemSign.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */