/*    */ package net.minecraft.item;
/*    */ 
/*    */ import net.minecraft.block.material.Material;
/*    */ import net.minecraft.creativetab.CreativeTabs;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.init.Items;
/*    */ import net.minecraft.stats.StatList;
/*    */ import net.minecraft.util.BlockPos;
/*    */ import net.minecraft.util.MovingObjectPosition;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ public class ItemGlassBottle
/*    */   extends Item
/*    */ {
/*    */   private static final String __OBFID = "CL_00001776";
/*    */   
/*    */   public ItemGlassBottle() {
/* 18 */     setCreativeTab(CreativeTabs.tabBrewing);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public ItemStack onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn) {
/* 26 */     MovingObjectPosition var4 = getMovingObjectPositionFromPlayer(worldIn, playerIn, true);
/*    */     
/* 28 */     if (var4 == null)
/*    */     {
/* 30 */       return itemStackIn;
/*    */     }
/*    */ 
/*    */     
/* 34 */     if (var4.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK) {
/*    */       
/* 36 */       BlockPos var5 = var4.func_178782_a();
/*    */       
/* 38 */       if (!worldIn.isBlockModifiable(playerIn, var5))
/*    */       {
/* 40 */         return itemStackIn;
/*    */       }
/*    */       
/* 43 */       if (!playerIn.func_175151_a(var5.offset(var4.field_178784_b), var4.field_178784_b, itemStackIn))
/*    */       {
/* 45 */         return itemStackIn;
/*    */       }
/*    */       
/* 48 */       if (worldIn.getBlockState(var5).getBlock().getMaterial() == Material.water) {
/*    */         
/* 50 */         itemStackIn.stackSize--;
/* 51 */         playerIn.triggerAchievement(StatList.objectUseStats[Item.getIdFromItem(this)]);
/*    */         
/* 53 */         if (itemStackIn.stackSize <= 0)
/*    */         {
/* 55 */           return new ItemStack(Items.potionitem);
/*    */         }
/*    */         
/* 58 */         if (!playerIn.inventory.addItemStackToInventory(new ItemStack(Items.potionitem)))
/*    */         {
/* 60 */           playerIn.dropPlayerItemWithRandomChoice(new ItemStack(Items.potionitem, 1, 0), false);
/*    */         }
/*    */       } 
/*    */     } 
/*    */     
/* 65 */     return itemStackIn;
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\item\ItemGlassBottle.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */