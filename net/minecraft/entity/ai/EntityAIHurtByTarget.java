/*    */ package net.minecraft.entity.ai;
/*    */ 
/*    */ import java.util.Iterator;
/*    */ import java.util.List;
/*    */ import net.minecraft.entity.EntityCreature;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.util.AxisAlignedBB;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class EntityAIHurtByTarget
/*    */   extends EntityAITarget
/*    */ {
/*    */   private boolean entityCallsForHelp;
/*    */   private int revengeTimerOld;
/*    */   private final Class[] field_179447_c;
/*    */   private static final String __OBFID = "CL_00001619";
/*    */   
/*    */   public EntityAIHurtByTarget(EntityCreature p_i45885_1_, boolean p_i45885_2_, Class... p_i45885_3_) {
/* 20 */     super(p_i45885_1_, false);
/* 21 */     this.entityCallsForHelp = p_i45885_2_;
/* 22 */     this.field_179447_c = p_i45885_3_;
/* 23 */     setMutexBits(1);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean shouldExecute() {
/* 31 */     int var1 = this.taskOwner.getRevengeTimer();
/* 32 */     return (var1 != this.revengeTimerOld && isSuitableTarget(this.taskOwner.getAITarget(), false));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void startExecuting() {
/* 40 */     this.taskOwner.setAttackTarget(this.taskOwner.getAITarget());
/* 41 */     this.revengeTimerOld = this.taskOwner.getRevengeTimer();
/*    */     
/* 43 */     if (this.entityCallsForHelp) {
/*    */       
/* 45 */       double var1 = getTargetDistance();
/* 46 */       List var3 = this.taskOwner.worldObj.getEntitiesWithinAABB(this.taskOwner.getClass(), (new AxisAlignedBB(this.taskOwner.posX, this.taskOwner.posY, this.taskOwner.posZ, this.taskOwner.posX + 1.0D, this.taskOwner.posY + 1.0D, this.taskOwner.posZ + 1.0D)).expand(var1, 10.0D, var1));
/* 47 */       Iterator<EntityCreature> var4 = var3.iterator();
/*    */       
/* 49 */       while (var4.hasNext()) {
/*    */         
/* 51 */         EntityCreature var5 = var4.next();
/*    */         
/* 53 */         if (this.taskOwner != var5 && var5.getAttackTarget() == null && !var5.isOnSameTeam(this.taskOwner.getAITarget())) {
/*    */           
/* 55 */           boolean var6 = false;
/* 56 */           Class[] var7 = this.field_179447_c;
/* 57 */           int var8 = var7.length;
/*    */           
/* 59 */           for (int var9 = 0; var9 < var8; var9++) {
/*    */             
/* 61 */             Class<?> var10 = var7[var9];
/*    */             
/* 63 */             if (var5.getClass() == var10) {
/*    */               
/* 65 */               var6 = true;
/*    */               
/*    */               break;
/*    */             } 
/*    */           } 
/* 70 */           if (!var6)
/*    */           {
/* 72 */             func_179446_a(var5, this.taskOwner.getAITarget());
/*    */           }
/*    */         } 
/*    */       } 
/*    */     } 
/*    */     
/* 78 */     super.startExecuting();
/*    */   }
/*    */ 
/*    */   
/*    */   protected void func_179446_a(EntityCreature p_179446_1_, EntityLivingBase p_179446_2_) {
/* 83 */     p_179446_1_.setAttackTarget(p_179446_2_);
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\entity\ai\EntityAIHurtByTarget.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */