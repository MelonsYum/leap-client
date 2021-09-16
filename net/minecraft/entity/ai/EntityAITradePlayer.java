/*    */ package net.minecraft.entity.ai;
/*    */ 
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.passive.EntityVillager;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ 
/*    */ public class EntityAITradePlayer
/*    */   extends EntityAIBase
/*    */ {
/*    */   private EntityVillager villager;
/*    */   private static final String __OBFID = "CL_00001617";
/*    */   
/*    */   public EntityAITradePlayer(EntityVillager p_i1658_1_) {
/* 14 */     this.villager = p_i1658_1_;
/* 15 */     setMutexBits(5);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean shouldExecute() {
/* 23 */     if (!this.villager.isEntityAlive())
/*    */     {
/* 25 */       return false;
/*    */     }
/* 27 */     if (this.villager.isInWater())
/*    */     {
/* 29 */       return false;
/*    */     }
/* 31 */     if (!this.villager.onGround)
/*    */     {
/* 33 */       return false;
/*    */     }
/* 35 */     if (this.villager.velocityChanged)
/*    */     {
/* 37 */       return false;
/*    */     }
/*    */ 
/*    */     
/* 41 */     EntityPlayer var1 = this.villager.getCustomer();
/* 42 */     return (var1 == null) ? false : ((this.villager.getDistanceSqToEntity((Entity)var1) > 16.0D) ? false : (var1.openContainer instanceof net.minecraft.inventory.Container));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void startExecuting() {
/* 51 */     this.villager.getNavigator().clearPathEntity();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void resetTask() {
/* 59 */     this.villager.setCustomer(null);
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\entity\ai\EntityAITradePlayer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */