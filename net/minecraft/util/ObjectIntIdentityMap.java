/*    */ package net.minecraft.util;
/*    */ 
/*    */ import com.google.common.base.Predicates;
/*    */ import com.google.common.collect.Iterators;
/*    */ import com.google.common.collect.Lists;
/*    */ import java.util.IdentityHashMap;
/*    */ import java.util.Iterator;
/*    */ import java.util.List;
/*    */ 
/*    */ public class ObjectIntIdentityMap
/*    */   implements IObjectIntIterable {
/* 12 */   private final IdentityHashMap field_148749_a = new IdentityHashMap<>(512);
/* 13 */   private final List field_148748_b = Lists.newArrayList();
/*    */   
/*    */   private static final String __OBFID = "CL_00001203";
/*    */   
/*    */   public void put(Object key, int value) {
/* 18 */     this.field_148749_a.put(key, Integer.valueOf(value));
/*    */     
/* 20 */     while (this.field_148748_b.size() <= value)
/*    */     {
/* 22 */       this.field_148748_b.add(null);
/*    */     }
/*    */     
/* 25 */     this.field_148748_b.set(value, key);
/*    */   }
/*    */ 
/*    */   
/*    */   public int get(Object key) {
/* 30 */     Integer var2 = (Integer)this.field_148749_a.get(key);
/* 31 */     return (var2 == null) ? -1 : var2.intValue();
/*    */   }
/*    */ 
/*    */   
/*    */   public final Object getByValue(int value) {
/* 36 */     return (value >= 0 && value < this.field_148748_b.size()) ? this.field_148748_b.get(value) : null;
/*    */   }
/*    */ 
/*    */   
/*    */   public Iterator iterator() {
/* 41 */     return (Iterator)Iterators.filter(this.field_148748_b.iterator(), Predicates.notNull());
/*    */   }
/*    */ 
/*    */   
/*    */   public List getObjectList() {
/* 46 */     return this.field_148748_b;
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraf\\util\ObjectIntIdentityMap.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */