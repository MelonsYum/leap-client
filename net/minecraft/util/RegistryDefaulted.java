/*    */ package net.minecraft.util;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class RegistryDefaulted
/*    */   extends RegistrySimple
/*    */ {
/*    */   private final Object defaultObject;
/*    */   private static final String __OBFID = "CL_00001198";
/*    */   
/*    */   public RegistryDefaulted(Object p_i1366_1_) {
/* 13 */     this.defaultObject = p_i1366_1_;
/*    */   }
/*    */ 
/*    */   
/*    */   public Object getObject(Object p_82594_1_) {
/* 18 */     Object var2 = super.getObject(p_82594_1_);
/* 19 */     return (var2 == null) ? this.defaultObject : var2;
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraf\\util\RegistryDefaulted.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */