/*    */ package net.minecraft.item;
/*    */ 
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.block.material.Material;
/*    */ import net.minecraft.creativetab.CreativeTabs;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.init.Blocks;
/*    */ import net.minecraft.util.BlockPos;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ public class ItemShears
/*    */   extends Item
/*    */ {
/*    */   private static final String __OBFID = "CL_00000062";
/*    */   
/*    */   public ItemShears() {
/* 17 */     setMaxStackSize(1);
/* 18 */     setMaxDamage(238);
/* 19 */     setCreativeTab(CreativeTabs.tabTools);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean onBlockDestroyed(ItemStack stack, World worldIn, Block blockIn, BlockPos pos, EntityLivingBase playerIn) {
/* 27 */     if (blockIn.getMaterial() != Material.leaves && blockIn != Blocks.web && blockIn != Blocks.tallgrass && blockIn != Blocks.vine && blockIn != Blocks.tripwire && blockIn != Blocks.wool)
/*    */     {
/* 29 */       return super.onBlockDestroyed(stack, worldIn, blockIn, pos, playerIn);
/*    */     }
/*    */ 
/*    */     
/* 33 */     stack.damageItem(1, playerIn);
/* 34 */     return true;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean canHarvestBlock(Block blockIn) {
/* 43 */     return !(blockIn != Blocks.web && blockIn != Blocks.redstone_wire && blockIn != Blocks.tripwire);
/*    */   }
/*    */ 
/*    */   
/*    */   public float getStrVsBlock(ItemStack stack, Block p_150893_2_) {
/* 48 */     return (p_150893_2_ != Blocks.web && p_150893_2_.getMaterial() != Material.leaves) ? ((p_150893_2_ == Blocks.wool) ? 5.0F : super.getStrVsBlock(stack, p_150893_2_)) : 15.0F;
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\item\ItemShears.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */