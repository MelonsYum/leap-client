/*    */ package net.minecraft.item;
/*    */ 
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.creativetab.CreativeTabs;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.util.BlockPos;
/*    */ import net.minecraft.util.EnumFacing;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ItemSeeds
/*    */   extends Item
/*    */ {
/*    */   private Block field_150925_a;
/*    */   private Block soilBlockID;
/*    */   private static final String __OBFID = "CL_00000061";
/*    */   
/*    */   public ItemSeeds(Block p_i45352_1_, Block p_i45352_2_) {
/* 20 */     this.field_150925_a = p_i45352_1_;
/* 21 */     this.soilBlockID = p_i45352_2_;
/* 22 */     setCreativeTab(CreativeTabs.tabMaterials);
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
/* 33 */     if (side != EnumFacing.UP)
/*    */     {
/* 35 */       return false;
/*    */     }
/* 37 */     if (!playerIn.func_175151_a(pos.offset(side), side, stack))
/*    */     {
/* 39 */       return false;
/*    */     }
/* 41 */     if (worldIn.getBlockState(pos).getBlock() == this.soilBlockID && worldIn.isAirBlock(pos.offsetUp())) {
/*    */       
/* 43 */       worldIn.setBlockState(pos.offsetUp(), this.field_150925_a.getDefaultState());
/* 44 */       stack.stackSize--;
/* 45 */       return true;
/*    */     } 
/*    */ 
/*    */     
/* 49 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\item\ItemSeeds.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */