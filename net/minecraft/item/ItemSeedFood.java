/*    */ package net.minecraft.item;
/*    */ 
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.util.BlockPos;
/*    */ import net.minecraft.util.EnumFacing;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ItemSeedFood
/*    */   extends ItemFood
/*    */ {
/*    */   private Block field_150908_b;
/*    */   private Block soilId;
/*    */   private static final String __OBFID = "CL_00000060";
/*    */   
/*    */   public ItemSeedFood(int p_i45351_1_, float p_i45351_2_, Block p_i45351_3_, Block p_i45351_4_) {
/* 19 */     super(p_i45351_1_, p_i45351_2_, false);
/* 20 */     this.field_150908_b = p_i45351_3_;
/* 21 */     this.soilId = p_i45351_4_;
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
/* 32 */     if (side != EnumFacing.UP)
/*    */     {
/* 34 */       return false;
/*    */     }
/* 36 */     if (!playerIn.func_175151_a(pos.offset(side), side, stack))
/*    */     {
/* 38 */       return false;
/*    */     }
/* 40 */     if (worldIn.getBlockState(pos).getBlock() == this.soilId && worldIn.isAirBlock(pos.offsetUp())) {
/*    */       
/* 42 */       worldIn.setBlockState(pos.offsetUp(), this.field_150908_b.getDefaultState());
/* 43 */       stack.stackSize--;
/* 44 */       return true;
/*    */     } 
/*    */ 
/*    */     
/* 48 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\item\ItemSeedFood.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */