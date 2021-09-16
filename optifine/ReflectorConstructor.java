/*    */ package optifine;
/*    */ 
/*    */ import java.lang.reflect.Constructor;
/*    */ 
/*    */ public class ReflectorConstructor
/*    */ {
/*  7 */   private ReflectorClass reflectorClass = null;
/*  8 */   private Class[] parameterTypes = null;
/*    */   private boolean checked = false;
/* 10 */   private Constructor targetConstructor = null;
/*    */ 
/*    */   
/*    */   public ReflectorConstructor(ReflectorClass reflectorClass, Class[] parameterTypes) {
/* 14 */     this.reflectorClass = reflectorClass;
/* 15 */     this.parameterTypes = parameterTypes;
/* 16 */     Constructor c = getTargetConstructor();
/*    */   }
/*    */ 
/*    */   
/*    */   public Constructor getTargetConstructor() {
/* 21 */     if (this.checked)
/*    */     {
/* 23 */       return this.targetConstructor;
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
/* 38 */       this.targetConstructor = findConstructor(cls, this.parameterTypes);
/*    */       
/* 40 */       if (this.targetConstructor == null)
/*    */       {
/* 42 */         Config.dbg("(Reflector) Constructor not present: " + cls.getName() + ", params: " + Config.arrayToString((Object[])this.parameterTypes));
/*    */       }
/*    */       
/* 45 */       if (this.targetConstructor != null)
/*    */       {
/* 47 */         this.targetConstructor.setAccessible(true);
/*    */       }
/*    */     }
/* 50 */     catch (Throwable var3) {
/*    */       
/* 52 */       var3.printStackTrace();
/*    */     } 
/*    */     
/* 55 */     return this.targetConstructor;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   private static Constructor findConstructor(Class cls, Class[] paramTypes) {
/* 62 */     Constructor[] cs = (Constructor[])cls.getDeclaredConstructors();
/*    */     
/* 64 */     for (int i = 0; i < cs.length; i++) {
/*    */       
/* 66 */       Constructor c = cs[i];
/* 67 */       Class[] types = c.getParameterTypes();
/*    */       
/* 69 */       if (Reflector.matchesTypes(paramTypes, types))
/*    */       {
/* 71 */         return c;
/*    */       }
/*    */     } 
/*    */     
/* 75 */     return null;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean exists() {
/* 80 */     return this.checked ? ((this.targetConstructor != null)) : ((getTargetConstructor() != null));
/*    */   }
/*    */ 
/*    */   
/*    */   public void deactivate() {
/* 85 */     this.checked = true;
/* 86 */     this.targetConstructor = null;
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\optifine\ReflectorConstructor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */