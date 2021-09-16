/*    */ package net.minecraft.item;
/*    */ 
/*    */ import net.minecraft.creativetab.CreativeTabs;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.entity.passive.EntityPig;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ 
/*    */ public class ItemSaddle
/*    */   extends Item {
/*    */   private static final String __OBFID = "CL_00000059";
/*    */   
/*    */   public ItemSaddle() {
/* 14 */     this.maxStackSize = 1;
/* 15 */     setCreativeTab(CreativeTabs.tabTransport);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean itemInteractionForEntity(ItemStack stack, EntityPlayer playerIn, EntityLivingBase target) {
/* 23 */     if (target instanceof EntityPig) {
/*    */       
/* 25 */       EntityPig var4 = (EntityPig)target;
/*    */       
/* 27 */       if (!var4.getSaddled() && !var4.isChild()) {
/*    */         
/* 29 */         var4.setSaddled(true);
/* 30 */         var4.worldObj.playSoundAtEntity((Entity)var4, "mob.horse.leather", 0.5F, 1.0F);
/* 31 */         stack.stackSize--;
/*    */       } 
/*    */       
/* 34 */       return true;
/*    */     } 
/*    */ 
/*    */     
/* 38 */     return false;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {
/* 51 */     itemInteractionForEntity(stack, (EntityPlayer)null, target);
/* 52 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\item\ItemSaddle.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */