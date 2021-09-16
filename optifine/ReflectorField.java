/*    */ package optifine;
/*    */ 
/*    */ import java.lang.reflect.Field;
/*    */ 
/*    */ public class ReflectorField
/*    */ {
/*  7 */   private ReflectorClass reflectorClass = null;
/*  8 */   private String targetFieldName = null;
/*    */   private boolean checked = false;
/* 10 */   private Field targetField = null;
/*    */ 
/*    */   
/*    */   public ReflectorField(ReflectorClass reflectorClass, String targetFieldName) {
/* 14 */     this.reflectorClass = reflectorClass;
/* 15 */     this.targetFieldName = targetFieldName;
/* 16 */     Field f = getTargetField();
/*    */   }
/*    */ 
/*    */   
/*    */   public Field getTargetField() {
/* 21 */     if (this.checked)
/*    */     {
/* 23 */       return this.targetField;
/*    */     }
/*    */ 
/*    */     
/* 27 */     this.checked = true;
/* 28 */     Class cls = this.reflectorClass.getTargetClass();
/*    */     
/* 30 */     if (cls == null)
/*    */     {
/* 32 */       return null;
/*    */     }
/*    */ 
/*    */ 
/*    */     
/*    */     try {
/* 38 */       this.targetField = cls.getDeclaredField(this.targetFieldName);
/* 39 */       this.targetField.setAccessible(true);
/*    */     }
/* 41 */     catch (NoSuchFieldException var3) {
/*    */       
/* 43 */       Config.log("(Reflector) Field not present: " + cls.getName() + "." + this.targetFieldName);
/*    */     }
/* 45 */     catch (SecurityException var4) {
/*    */       
/* 47 */       var4.printStackTrace();
/*    */     }
/* 49 */     catch (Throwable var5) {
/*    */       
/* 51 */       var5.printStackTrace();
/*    */     } 
/*    */     
/* 54 */     return this.targetField;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Object getValue() {
/* 61 */     return Reflector.getFieldValue(null, this);
/*    */   }
/*    */ 
/*    */   
/*    */   public void setValue(Object value) {
/* 66 */     Reflector.setFieldValue(null, this, value);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean exists() {
/* 71 */     return this.checked ? ((this.targetField != null)) : ((getTargetField() != null));
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\optifine\ReflectorField.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */