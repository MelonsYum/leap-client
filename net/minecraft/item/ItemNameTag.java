/*    */ package net.minecraft.item;
/*    */ 
/*    */ import net.minecraft.creativetab.CreativeTabs;
/*    */ import net.minecraft.entity.EntityLiving;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ 
/*    */ public class ItemNameTag
/*    */   extends Item
/*    */ {
/*    */   private static final String __OBFID = "CL_00000052";
/*    */   
/*    */   public ItemNameTag() {
/* 14 */     setCreativeTab(CreativeTabs.tabTools);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean itemInteractionForEntity(ItemStack stack, EntityPlayer playerIn, EntityLivingBase target) {
/* 22 */     if (!stack.hasDisplayName())
/*    */     {
/* 24 */       return false;
/*    */     }
/* 26 */     if (target instanceof EntityLiving) {
/*    */       
/* 28 */       EntityLiving var4 = (EntityLiving)target;
/* 29 */       var4.setCustomNameTag(stack.getDisplayName());
/* 30 */       var4.enablePersistence();
/* 31 */       stack.stackSize--;
/* 32 */       return true;
/*    */     } 
/*    */ 
/*    */     
/* 36 */     return super.itemInteractionForEntity(stack, playerIn, target);
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\item\ItemNameTag.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */