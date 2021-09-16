/*    */ package net.minecraft.item;
/*    */ 
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.block.BlockLiquid;
/*    */ import net.minecraft.block.material.Material;
/*    */ import net.minecraft.block.properties.IProperty;
/*    */ import net.minecraft.block.state.IBlockState;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.init.Blocks;
/*    */ import net.minecraft.stats.StatList;
/*    */ import net.minecraft.util.BlockPos;
/*    */ import net.minecraft.util.MovingObjectPosition;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ public class ItemLilyPad
/*    */   extends ItemColored {
/*    */   private static final String __OBFID = "CL_00000074";
/*    */   
/*    */   public ItemLilyPad(Block p_i45357_1_) {
/* 20 */     super(p_i45357_1_, false);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public ItemStack onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn) {
/* 28 */     MovingObjectPosition var4 = getMovingObjectPositionFromPlayer(worldIn, playerIn, true);
/*    */     
/* 30 */     if (var4 == null)
/*    */     {
/* 32 */       return itemStackIn;
/*    */     }
/*    */ 
/*    */     
/* 36 */     if (var4.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK) {
/*    */       
/* 38 */       BlockPos var5 = var4.func_178782_a();
/*    */       
/* 40 */       if (!worldIn.isBlockModifiable(playerIn, var5))
/*    */       {
/* 42 */         return itemStackIn;
/*    */       }
/*    */       
/* 45 */       if (!playerIn.func_175151_a(var5.offset(var4.field_178784_b), var4.field_178784_b, itemStackIn))
/*    */       {
/* 47 */         return itemStackIn;
/*    */       }
/*    */       
/* 50 */       BlockPos var6 = var5.offsetUp();
/* 51 */       IBlockState var7 = worldIn.getBlockState(var5);
/*    */       
/* 53 */       if (var7.getBlock().getMaterial() == Material.water && ((Integer)var7.getValue((IProperty)BlockLiquid.LEVEL)).intValue() == 0 && worldIn.isAirBlock(var6)) {
/*    */         
/* 55 */         worldIn.setBlockState(var6, Blocks.waterlily.getDefaultState());
/*    */         
/* 57 */         if (!playerIn.capabilities.isCreativeMode)
/*    */         {
/* 59 */           itemStackIn.stackSize--;
/*    */         }
/*    */         
/* 62 */         playerIn.triggerAchievement(StatList.objectUseStats[Item.getIdFromItem(this)]);
/*    */       } 
/*    */     } 
/*    */     
/* 66 */     return itemStackIn;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getColorFromItemStack(ItemStack stack, int renderPass) {
/* 72 */     return Blocks.waterlily.getRenderColor(Blocks.waterlily.getStateFromMeta(stack.getMetadata()));
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\item\ItemLilyPad.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */