/*    */ package net.minecraft.item;
/*    */ 
/*    */ import net.minecraft.creativetab.CreativeTabs;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.entity.passive.EntityPig;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.init.Items;
/*    */ import net.minecraft.stats.StatList;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ public class ItemCarrotOnAStick
/*    */   extends Item {
/*    */   private static final String __OBFID = "CL_00000001";
/*    */   
/*    */   public ItemCarrotOnAStick() {
/* 16 */     setCreativeTab(CreativeTabs.tabTransport);
/* 17 */     setMaxStackSize(1);
/* 18 */     setMaxDamage(25);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean isFull3D() {
/* 26 */     return true;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean shouldRotateAroundWhenRendering() {
/* 35 */     return true;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public ItemStack onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn) {
/* 43 */     if (playerIn.isRiding() && playerIn.ridingEntity instanceof EntityPig) {
/*    */       
/* 45 */       EntityPig var4 = (EntityPig)playerIn.ridingEntity;
/*    */       
/* 47 */       if (var4.getAIControlledByPlayer().isControlledByPlayer() && itemStackIn.getMaxDamage() - itemStackIn.getMetadata() >= 7) {
/*    */         
/* 49 */         var4.getAIControlledByPlayer().boostSpeed();
/* 50 */         itemStackIn.damageItem(7, (EntityLivingBase)playerIn);
/*    */         
/* 52 */         if (itemStackIn.stackSize == 0) {
/*    */           
/* 54 */           ItemStack var5 = new ItemStack(Items.fishing_rod);
/* 55 */           var5.setTagCompound(itemStackIn.getTagCompound());
/* 56 */           return var5;
/*    */         } 
/*    */       } 
/*    */     } 
/*    */     
/* 61 */     playerIn.triggerAchievement(StatList.objectUseStats[Item.getIdFromItem(this)]);
/* 62 */     return itemStackIn;
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\item\ItemCarrotOnAStick.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */