/*    */ package optifine;
/*    */ 
/*    */ import java.util.Collections;
/*    */ import java.util.Enumeration;
/*    */ import java.util.LinkedHashSet;
/*    */ import java.util.Properties;
/*    */ import java.util.Set;
/*    */ 
/*    */ public class PropertiesOrdered
/*    */   extends Properties {
/* 11 */   private Set<Object> keysOrdered = new LinkedHashSet();
/*    */ 
/*    */   
/*    */   public synchronized Object put(Object key, Object value) {
/* 15 */     this.keysOrdered.add(key);
/* 16 */     return super.put(key, value);
/*    */   }
/*    */ 
/*    */   
/*    */   public Set<Object> keySet() {
/* 21 */     Set<?> keysParent = super.keySet();
/* 22 */     this.keysOrdered.retainAll(keysParent);
/* 23 */     return Collections.unmodifiableSet(this.keysOrdered);
/*    */   }
/*    */ 
/*    */   
/*    */   public synchronized Enumeration<Object> keys() {
/* 28 */     return Collections.enumeration(keySet());
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\optifine\PropertiesOrdered.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */