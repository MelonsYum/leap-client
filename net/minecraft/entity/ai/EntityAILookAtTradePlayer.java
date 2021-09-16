/*    */ package net.minecraft.entity.ai;
/*    */ 
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntityLiving;
/*    */ import net.minecraft.entity.passive.EntityVillager;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ 
/*    */ public class EntityAILookAtTradePlayer extends EntityAIWatchClosest {
/*    */   private final EntityVillager theMerchant;
/*    */   private static final String __OBFID = "CL_00001593";
/*    */   
/*    */   public EntityAILookAtTradePlayer(EntityVillager p_i1633_1_) {
/* 13 */     super((EntityLiving)p_i1633_1_, EntityPlayer.class, 8.0F);
/* 14 */     this.theMerchant = p_i1633_1_;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean shouldExecute() {
/* 22 */     if (this.theMerchant.isTrading()) {
/*    */       
/* 24 */       this.closestEntity = (Entity)this.theMerchant.getCustomer();
/* 25 */       return true;
/*    */     } 
/*    */ 
/*    */     
/* 29 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\entity\ai\EntityAILookAtTradePlayer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */