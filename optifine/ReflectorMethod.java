/*     */ package optifine;
/*     */ 
/*     */ import java.lang.reflect.Method;
/*     */ 
/*     */ 
/*     */ public class ReflectorMethod
/*     */ {
/*     */   private ReflectorClass reflectorClass;
/*     */   private String targetMethodName;
/*     */   private Class[] targetMethodParameterTypes;
/*     */   private boolean checked;
/*     */   private Method targetMethod;
/*     */   
/*     */   public ReflectorMethod(ReflectorClass reflectorClass, String targetMethodName) {
/*  15 */     this(reflectorClass, targetMethodName, null, false);
/*     */   }
/*     */ 
/*     */   
/*     */   public ReflectorMethod(ReflectorClass reflectorClass, String targetMethodName, Class[] targetMethodParameterTypes) {
/*  20 */     this(reflectorClass, targetMethodName, targetMethodParameterTypes, false);
/*     */   }
/*     */ 
/*     */   
/*     */   public ReflectorMethod(ReflectorClass reflectorClass, String targetMethodName, Class[] targetMethodParameterTypes, boolean lazyResolve) {
/*  25 */     this.reflectorClass = null;
/*  26 */     this.targetMethodName = null;
/*  27 */     this.targetMethodParameterTypes = null;
/*  28 */     this.checked = false;
/*  29 */     this.targetMethod = null;
/*  30 */     this.reflectorClass = reflectorClass;
/*  31 */     this.targetMethodName = targetMethodName;
/*  32 */     this.targetMethodParameterTypes = targetMethodParameterTypes;
/*     */     
/*  34 */     if (!lazyResolve)
/*     */     {
/*  36 */       Method method = getTargetMethod();
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public Method getTargetMethod() {
/*  42 */     if (this.checked)
/*     */     {
/*  44 */       return this.targetMethod;
/*     */     }
/*     */ 
/*     */     
/*  48 */     this.checked = true;
/*  49 */     Class cls = this.reflectorClass.getTargetClass();
/*     */     
/*  51 */     if (cls == null)
/*     */     {
/*  53 */       return null;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/*  59 */       if (this.targetMethodParameterTypes == null) {
/*     */         
/*  61 */         Method[] e = Reflector.getMethods(cls, this.targetMethodName);
/*     */         
/*  63 */         if (e.length <= 0) {
/*     */           
/*  65 */           Config.log("(Reflector) Method not present: " + cls.getName() + "." + this.targetMethodName);
/*  66 */           return null;
/*     */         } 
/*     */         
/*  69 */         if (e.length > 1) {
/*     */           
/*  71 */           Config.warn("(Reflector) More than one method found: " + cls.getName() + "." + this.targetMethodName);
/*     */           
/*  73 */           for (int i = 0; i < e.length; i++) {
/*     */             
/*  75 */             Method m = e[i];
/*  76 */             Config.warn("(Reflector)  - " + m);
/*     */           } 
/*     */           
/*  79 */           return null;
/*     */         } 
/*     */         
/*  82 */         this.targetMethod = e[0];
/*     */       }
/*     */       else {
/*     */         
/*  86 */         this.targetMethod = Reflector.getMethod(cls, this.targetMethodName, this.targetMethodParameterTypes);
/*     */       } 
/*     */       
/*  89 */       if (this.targetMethod == null) {
/*     */         
/*  91 */         Config.log("(Reflector) Method not present: " + cls.getName() + "." + this.targetMethodName);
/*  92 */         return null;
/*     */       } 
/*     */ 
/*     */       
/*  96 */       this.targetMethod.setAccessible(true);
/*  97 */       return this.targetMethod;
/*     */     
/*     */     }
/* 100 */     catch (Throwable var5) {
/*     */       
/* 102 */       var5.printStackTrace();
/* 103 */       return null;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean exists() {
/* 111 */     return this.checked ? ((this.targetMethod != null)) : ((getTargetMethod() != null));
/*     */   }
/*     */ 
/*     */   
/*     */   public Class getReturnType() {
/* 116 */     Method tm = getTargetMethod();
/* 117 */     return (tm == null) ? null : tm.getReturnType();
/*     */   }
/*     */ 
/*     */   
/*     */   public void deactivate() {
/* 122 */     this.checked = true;
/* 123 */     this.targetMethod = null;
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\optifine\ReflectorMethod.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */