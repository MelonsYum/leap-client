/*    */ package leap.thealtening.auth.service;
/*    */ 
/*    */ import java.lang.invoke.MethodHandle;
/*    */ import java.lang.invoke.MethodHandles;
/*    */ import java.lang.reflect.Field;
/*    */ import java.lang.reflect.Modifier;
/*    */ import java.util.HashMap;
/*    */ import java.util.Optional;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public final class FieldAdapter
/*    */ {
/* 33 */   private final HashMap<String, MethodHandle> fields = new HashMap<>();
/*    */   private static final MethodHandles.Lookup LOOKUP;
/*    */   private static Field MODIFIERS;
/*    */   
/*    */   public FieldAdapter(String parent) {
/*    */     try {
/* 39 */       Class<?> cls = Class.forName(parent);
/* 40 */       Field modifiers = MODIFIERS; byte b; int i; Field[] arrayOfField;
/* 41 */       for (i = (arrayOfField = cls.getDeclaredFields()).length, b = 0; b < i; ) { Field field = arrayOfField[b];
/* 42 */         field.setAccessible(true);
/* 43 */         int accessFlags = field.getModifiers();
/* 44 */         if (Modifier.isFinal(accessFlags)) {
/* 45 */           modifiers.setInt(field, accessFlags & 0xFFFFFFEF);
/*    */         }
/*    */         
/* 48 */         MethodHandle handler = LOOKUP.unreflectSetter(field);
/* 49 */         handler = handler.asType(handler.type().generic().changeReturnType(void.class));
/* 50 */         this.fields.put(field.getName(), handler); b++; }
/*    */     
/* 52 */     } catch (ClassNotFoundException e) {
/* 53 */       throw new RuntimeException("Couldn't load/find the specified class");
/* 54 */     } catch (Exception e) {
/* 55 */       throw new RuntimeException("Couldn't create a method handler for the field");
/*    */     } 
/*    */   }
/*    */   
/*    */   public void updateFieldIfPresent(String name, Object newValue) {
/* 60 */     Optional.<MethodHandle>ofNullable(this.fields.get(name)).ifPresent(setter -> {
/*    */           try {
/*    */             setter.invokeExact(paramObject);
/* 63 */           } catch (Throwable e) {
/*    */             e.printStackTrace();
/*    */           } 
/*    */         });
/*    */   }
/*    */   static {
/*    */     MethodHandles.Lookup lookupObject;
/*    */     try {
/* 71 */       MODIFIERS = Field.class.getDeclaredField("modifiers");
/* 72 */       MODIFIERS.setAccessible(true);
/* 73 */     } catch (NoSuchFieldException e) {
/* 74 */       e.printStackTrace();
/*    */     } 
/*    */ 
/*    */     
/*    */     try {
/* 79 */       Field lookupImplField = MethodHandles.Lookup.class.getDeclaredField("IMPL_LOOKUP");
/* 80 */       lookupImplField.setAccessible(true);
/* 81 */       lookupObject = (MethodHandles.Lookup)lookupImplField.get((Object)null);
/* 82 */     } catch (ReflectiveOperationException e) {
/* 83 */       lookupObject = MethodHandles.lookup();
/*    */     } 
/*    */     
/* 86 */     LOOKUP = lookupObject;
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\leap\thealtening\auth\service\FieldAdapter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */