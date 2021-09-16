/*    */ package net.minecraft.item;
/*    */ 
/*    */ import net.minecraft.creativetab.CreativeTabs;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.entity.item.EntityEnderPearl;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.stats.StatList;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ public class ItemEnderPearl extends Item {
/*    */   private static final String __OBFID = "CL_00000027";
/*    */   
/*    */   public ItemEnderPearl() {
/* 15 */     this.maxStackSize = 16;
/* 16 */     setCreativeTab(CreativeTabs.tabMisc);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public ItemStack onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn) {
/* 24 */     if (playerIn.capabilities.isCreativeMode)
/*    */     {
/* 26 */       return itemStackIn;
/*    */     }
/*    */ 
/*    */     
/* 30 */     itemStackIn.stackSize--;
/* 31 */     worldIn.playSoundAtEntity((Entity)playerIn, "random.bow", 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));
/*    */     
/* 33 */     if (!worldIn.isRemote)
/*    */     {
/* 35 */       worldIn.spawnEntityInWorld((Entity)new EntityEnderPearl(worldIn, (EntityLivingBase)playerIn));
/*    */     }
/*    */     
/* 38 */     playerIn.triggerAchievement(StatList.objectUseStats[Item.getIdFromItem(this)]);
/* 39 */     return itemStackIn;
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\item\ItemEnderPearl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */