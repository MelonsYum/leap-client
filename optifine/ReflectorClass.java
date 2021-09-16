/*    */ package optifine;
/*    */ 
/*    */ 
/*    */ public class ReflectorClass
/*    */ {
/*    */   private String targetClassName;
/*    */   private boolean checked;
/*    */   private Class targetClass;
/*    */   
/*    */   public ReflectorClass(String targetClassName) {
/* 11 */     this(targetClassName, false);
/*    */   }
/*    */ 
/*    */   
/*    */   public ReflectorClass(String targetClassName, boolean lazyResolve) {
/* 16 */     this.targetClassName = null;
/* 17 */     this.checked = false;
/* 18 */     this.targetClass = null;
/* 19 */     this.targetClassName = targetClassName;
/*    */     
/* 21 */     if (!lazyResolve)
/*    */     {
/* 23 */       Class clazz = getTargetClass();
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public ReflectorClass(Class targetClass) {
/* 29 */     this.targetClassName = null;
/* 30 */     this.checked = false;
/* 31 */     this.targetClass = null;
/* 32 */     this.targetClass = targetClass;
/* 33 */     this.targetClassName = targetClass.getName();
/* 34 */     this.checked = true;
/*    */   }
/*    */ 
/*    */   
/*    */   public Class getTargetClass() {
/* 39 */     if (this.checked)
/*    */     {
/* 41 */       return this.targetClass;
/*    */     }
/*    */ 
/*    */     
/* 45 */     this.checked = true;
/*    */ 
/*    */     
/*    */     try {
/* 49 */       this.targetClass = Class.forName(this.targetClassName);
/*    */     }
/* 51 */     catch (ClassNotFoundException var2) {
/*    */       
/* 53 */       Config.log("(Reflector) Class not present: " + this.targetClassName);
/*    */     }
/* 55 */     catch (Throwable var3) {
/*    */       
/* 57 */       var3.printStackTrace();
/*    */     } 
/*    */     
/* 60 */     return this.targetClass;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean exists() {
/* 66 */     return (getTargetClass() != null);
/*    */   }
/*    */ 
/*    */   
/*    */   public String getTargetClassName() {
/* 71 */     return this.targetClassName;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isInstance(Object obj) {
/* 76 */     return (getTargetClass() == null) ? false : getTargetClass().isInstance(obj);
/*    */   }
/*    */ 
/*    */   
/*    */   public ReflectorField makeField(String name) {
/* 81 */     return new ReflectorField(this, name);
/*    */   }
/*    */ 
/*    */   
/*    */   public ReflectorMethod makeMethod(String name) {
/* 86 */     return new ReflectorMethod(this, name);
/*    */   }
/*    */ 
/*    */   
/*    */   public ReflectorMethod makeMethod(String name, Class[] paramTypes) {
/* 91 */     return new ReflectorMethod(this, name, paramTypes);
/*    */   }
/*    */ 
/*    */   
/*    */   public ReflectorMethod makeMethod(String name, Class[] paramTypes, boolean lazyResolve) {
/* 96 */     return new ReflectorMethod(this, name, paramTypes, lazyResolve);
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\optifine\ReflectorClass.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */