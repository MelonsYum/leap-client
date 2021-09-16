/*    */ package net.minecraft.util;
/*    */ 
/*    */ import org.apache.commons.lang3.Validate;
/*    */ 
/*    */ public class RegistryNamespacedDefaultedByKey
/*    */   extends RegistryNamespaced
/*    */ {
/*    */   private final Object field_148760_d;
/*    */   private Object field_148761_e;
/*    */   private static final String __OBFID = "CL_00001196";
/*    */   
/*    */   public RegistryNamespacedDefaultedByKey(Object p_i46017_1_) {
/* 13 */     this.field_148760_d = p_i46017_1_;
/*    */   }
/*    */ 
/*    */   
/*    */   public void register(int p_177775_1_, Object p_177775_2_, Object p_177775_3_) {
/* 18 */     if (this.field_148760_d.equals(p_177775_2_))
/*    */     {
/* 20 */       this.field_148761_e = p_177775_3_;
/*    */     }
/*    */     
/* 23 */     super.register(p_177775_1_, p_177775_2_, p_177775_3_);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void validateKey() {
/* 31 */     Validate.notNull(this.field_148760_d);
/*    */   }
/*    */ 
/*    */   
/*    */   public Object getObject(Object p_82594_1_) {
/* 36 */     Object var2 = super.getObject(p_82594_1_);
/* 37 */     return (var2 == null) ? this.field_148761_e : var2;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Object getObjectById(int p_148754_1_) {
/* 45 */     Object var2 = super.getObjectById(p_148754_1_);
/* 46 */     return (var2 == null) ? this.field_148761_e : var2;
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraf\\util\RegistryNamespacedDefaultedByKey.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */