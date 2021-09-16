/*    */ package net.minecraft.entity.monster;
/*    */ 
/*    */ import com.google.common.base.Predicate;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.passive.IAnimals;
/*    */ 
/*    */ public interface IMob
/*    */   extends IAnimals
/*    */ {
/* 10 */   public static final Predicate mobSelector = new Predicate()
/*    */     {
/*    */       private static final String __OBFID = "CL_00001688";
/*    */       
/*    */       public boolean func_179983_a(Entity p_179983_1_) {
/* 15 */         return p_179983_1_ instanceof IMob;
/*    */       }
/*    */       
/*    */       public boolean apply(Object p_apply_1_) {
/* 19 */         return func_179983_a((Entity)p_apply_1_);
/*    */       }
/*    */     };
/* 22 */   public static final Predicate field_175450_e = new Predicate()
/*    */     {
/*    */       private static final String __OBFID = "CL_00002218";
/*    */       
/*    */       public boolean func_179982_a(Entity p_179982_1_) {
/* 27 */         return (p_179982_1_ instanceof IMob && !p_179982_1_.isInvisible());
/*    */       }
/*    */       
/*    */       public boolean apply(Object p_apply_1_) {
/* 31 */         return func_179982_a((Entity)p_apply_1_);
/*    */       }
/*    */     };
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\entity\monster\IMob.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */