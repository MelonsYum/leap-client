/*    */ package net.minecraft.client.renderer.entity;
/*    */ 
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.projectile.EntityPotion;
/*    */ import net.minecraft.init.Items;
/*    */ import net.minecraft.item.Item;
/*    */ import net.minecraft.item.ItemStack;
/*    */ 
/*    */ public class RenderPotion
/*    */   extends RenderSnowball {
/*    */   private static final String __OBFID = "CL_00002430";
/*    */   
/*    */   public RenderPotion(RenderManager p_i46136_1_, RenderItem p_i46136_2_) {
/* 14 */     super(p_i46136_1_, (Item)Items.potionitem, p_i46136_2_);
/*    */   }
/*    */ 
/*    */   
/*    */   public ItemStack func_177085_a(EntityPotion p_177085_1_) {
/* 19 */     return new ItemStack(this.field_177084_a, 1, p_177085_1_.getPotionDamage());
/*    */   }
/*    */ 
/*    */   
/*    */   public ItemStack func_177082_d(Entity p_177082_1_) {
/* 24 */     return func_177085_a((EntityPotion)p_177082_1_);
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\renderer\entity\RenderPotion.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */