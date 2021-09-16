/*    */ package net.minecraft.item;
/*    */ 
/*    */ import net.minecraft.creativetab.CreativeTabs;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.init.Items;
/*    */ import net.minecraft.stats.StatList;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraft.world.WorldSavedData;
/*    */ import net.minecraft.world.storage.MapData;
/*    */ 
/*    */ public class ItemEmptyMap
/*    */   extends ItemMapBase {
/*    */   private static final String __OBFID = "CL_00000024";
/*    */   
/*    */   protected ItemEmptyMap() {
/* 16 */     setCreativeTab(CreativeTabs.tabMisc);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public ItemStack onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn) {
/* 24 */     ItemStack var4 = new ItemStack(Items.filled_map, 1, worldIn.getUniqueDataId("map"));
/* 25 */     String var5 = "map_" + var4.getMetadata();
/* 26 */     MapData var6 = new MapData(var5);
/* 27 */     worldIn.setItemData(var5, (WorldSavedData)var6);
/* 28 */     var6.scale = 0;
/* 29 */     var6.func_176054_a(playerIn.posX, playerIn.posZ, var6.scale);
/* 30 */     var6.dimension = (byte)worldIn.provider.getDimensionId();
/* 31 */     var6.markDirty();
/* 32 */     itemStackIn.stackSize--;
/*    */     
/* 34 */     if (itemStackIn.stackSize <= 0)
/*    */     {
/* 36 */       return var4;
/*    */     }
/*    */ 
/*    */     
/* 40 */     if (!playerIn.inventory.addItemStackToInventory(var4.copy()))
/*    */     {
/* 42 */       playerIn.dropPlayerItemWithRandomChoice(var4, false);
/*    */     }
/*    */     
/* 45 */     playerIn.triggerAchievement(StatList.objectUseStats[Item.getIdFromItem(this)]);
/* 46 */     return itemStackIn;
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\item\ItemEmptyMap.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */