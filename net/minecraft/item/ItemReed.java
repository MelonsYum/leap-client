/*    */ package net.minecraft.item;
/*    */ 
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.block.BlockSnow;
/*    */ import net.minecraft.block.properties.IProperty;
/*    */ import net.minecraft.block.state.IBlockState;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.init.Blocks;
/*    */ import net.minecraft.util.BlockPos;
/*    */ import net.minecraft.util.EnumFacing;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ public class ItemReed
/*    */   extends Item {
/*    */   private Block field_150935_a;
/*    */   private static final String __OBFID = "CL_00001773";
/*    */   
/*    */   public ItemReed(Block p_i45329_1_) {
/* 20 */     this.field_150935_a = p_i45329_1_;
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
/* 31 */     IBlockState var9 = worldIn.getBlockState(pos);
/* 32 */     Block var10 = var9.getBlock();
/*    */     
/* 34 */     if (var10 == Blocks.snow_layer && ((Integer)var9.getValue((IProperty)BlockSnow.LAYERS_PROP)).intValue() < 1) {
/*    */       
/* 36 */       side = EnumFacing.UP;
/*    */     }
/* 38 */     else if (!var10.isReplaceable(worldIn, pos)) {
/*    */       
/* 40 */       pos = pos.offset(side);
/*    */     } 
/*    */     
/* 43 */     if (!playerIn.func_175151_a(pos, side, stack))
/*    */     {
/* 45 */       return false;
/*    */     }
/* 47 */     if (stack.stackSize == 0)
/*    */     {
/* 49 */       return false;
/*    */     }
/*    */ 
/*    */     
/* 53 */     if (worldIn.canBlockBePlaced(this.field_150935_a, pos, false, side, null, stack)) {
/*    */       
/* 55 */       IBlockState var11 = this.field_150935_a.onBlockPlaced(worldIn, pos, side, hitX, hitY, hitZ, 0, (EntityLivingBase)playerIn);
/*    */       
/* 57 */       if (worldIn.setBlockState(pos, var11, 3)) {
/*    */         
/* 59 */         var11 = worldIn.getBlockState(pos);
/*    */         
/* 61 */         if (var11.getBlock() == this.field_150935_a) {
/*    */           
/* 63 */           ItemBlock.setTileEntityNBT(worldIn, pos, stack);
/* 64 */           var11.getBlock().onBlockPlacedBy(worldIn, pos, var11, (EntityLivingBase)playerIn, stack);
/*    */         } 
/*    */         
/* 67 */         worldIn.playSoundEffect((pos.getX() + 0.5F), (pos.getY() + 0.5F), (pos.getZ() + 0.5F), this.field_150935_a.stepSound.getPlaceSound(), (this.field_150935_a.stepSound.getVolume() + 1.0F) / 2.0F, this.field_150935_a.stepSound.getFrequency() * 0.8F);
/* 68 */         stack.stackSize--;
/* 69 */         return true;
/*    */       } 
/*    */     } 
/*    */     
/* 73 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\item\ItemReed.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */