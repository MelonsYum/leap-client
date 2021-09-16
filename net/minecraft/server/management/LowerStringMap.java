/*    */ package net.minecraft.server.management;
/*    */ 
/*    */ import com.google.common.collect.Maps;
/*    */ import java.util.Collection;
/*    */ import java.util.Iterator;
/*    */ import java.util.Map;
/*    */ import java.util.Set;
/*    */ 
/*    */ public class LowerStringMap
/*    */   implements Map
/*    */ {
/* 12 */   private final Map internalMap = Maps.newLinkedHashMap();
/*    */   
/*    */   private static final String __OBFID = "CL_00001488";
/*    */   
/*    */   public int size() {
/* 17 */     return this.internalMap.size();
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isEmpty() {
/* 22 */     return this.internalMap.isEmpty();
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean containsKey(Object p_containsKey_1_) {
/* 27 */     return this.internalMap.containsKey(p_containsKey_1_.toString().toLowerCase());
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean containsValue(Object p_containsValue_1_) {
/* 32 */     return this.internalMap.containsKey(p_containsValue_1_);
/*    */   }
/*    */ 
/*    */   
/*    */   public Object get(Object p_get_1_) {
/* 37 */     return this.internalMap.get(p_get_1_.toString().toLowerCase());
/*    */   }
/*    */ 
/*    */   
/*    */   public Object put(String p_put_1_, Object p_put_2_) {
/* 42 */     return this.internalMap.put(p_put_1_.toLowerCase(), p_put_2_);
/*    */   }
/*    */ 
/*    */   
/*    */   public Object remove(Object p_remove_1_) {
/* 47 */     return this.internalMap.remove(p_remove_1_.toString().toLowerCase());
/*    */   }
/*    */ 
/*    */   
/*    */   public void putAll(Map p_putAll_1_) {
/* 52 */     Iterator<Map.Entry> var2 = p_putAll_1_.entrySet().iterator();
/*    */     
/* 54 */     while (var2.hasNext()) {
/*    */       
/* 56 */       Map.Entry var3 = var2.next();
/* 57 */       put((String)var3.getKey(), var3.getValue());
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void clear() {
/* 63 */     this.internalMap.clear();
/*    */   }
/*    */ 
/*    */   
/*    */   public Set keySet() {
/* 68 */     return this.internalMap.keySet();
/*    */   }
/*    */ 
/*    */   
/*    */   public Collection values() {
/* 73 */     return this.internalMap.values();
/*    */   }
/*    */ 
/*    */   
/*    */   public Set entrySet() {
/* 78 */     return this.internalMap.entrySet();
/*    */   }
/*    */ 
/*    */   
/*    */   public Object put(Object p_put_1_, Object p_put_2_) {
/* 83 */     return put((String)p_put_1_, p_put_2_);
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\server\management\LowerStringMap.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */