/*    */ package net.minecraft.item;
/*    */ 
/*    */ import net.minecraft.creativetab.CreativeTabs;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.entity.projectile.EntityFishHook;
/*    */ import net.minecraft.stats.StatList;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ public class ItemFishingRod extends Item {
/*    */   private static final String __OBFID = "CL_00000034";
/*    */   
/*    */   public ItemFishingRod() {
/* 15 */     setMaxDamage(64);
/* 16 */     setMaxStackSize(1);
/* 17 */     setCreativeTab(CreativeTabs.tabTools);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean isFull3D() {
/* 25 */     return true;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean shouldRotateAroundWhenRendering() {
/* 34 */     return true;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public ItemStack onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn) {
/* 42 */     if (playerIn.fishEntity != null) {
/*    */       
/* 44 */       int var4 = playerIn.fishEntity.handleHookRetraction();
/* 45 */       itemStackIn.damageItem(var4, (EntityLivingBase)playerIn);
/* 46 */       playerIn.swingItem();
/*    */     }
/*    */     else {
/*    */       
/* 50 */       worldIn.playSoundAtEntity((Entity)playerIn, "random.bow", 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));
/*    */       
/* 52 */       if (!worldIn.isRemote)
/*    */       {
/* 54 */         worldIn.spawnEntityInWorld((Entity)new EntityFishHook(worldIn, playerIn));
/*    */       }
/*    */       
/* 57 */       playerIn.swingItem();
/* 58 */       playerIn.triggerAchievement(StatList.objectUseStats[Item.getIdFromItem(this)]);
/*    */     } 
/*    */     
/* 61 */     return itemStackIn;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean isItemTool(ItemStack stack) {
/* 69 */     return super.isItemTool(stack);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int getItemEnchantability() {
/* 77 */     return 1;
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\item\ItemFishingRod.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */