/*    */ package net.minecraft.entity.ai;
/*    */ 
/*    */ import com.google.common.base.Predicate;
/*    */ import net.minecraft.entity.EntityCreature;
/*    */ import net.minecraft.entity.passive.EntityTameable;
/*    */ 
/*    */ public class EntityAITargetNonTamed
/*    */   extends EntityAINearestAttackableTarget {
/*    */   private EntityTameable theTameable;
/*    */   private static final String __OBFID = "CL_00001623";
/*    */   
/*    */   public EntityAITargetNonTamed(EntityTameable p_i45876_1_, Class p_i45876_2_, boolean p_i45876_3_, Predicate p_i45876_4_) {
/* 13 */     super((EntityCreature)p_i45876_1_, p_i45876_2_, 10, p_i45876_3_, false, p_i45876_4_);
/* 14 */     this.theTameable = p_i45876_1_;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean shouldExecute() {
/* 22 */     return (!this.theTameable.isTamed() && super.shouldExecute());
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\entity\ai\EntityAITargetNonTamed.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */