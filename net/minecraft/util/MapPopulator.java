/*    */ package net.minecraft.util;
/*    */ 
/*    */ import com.google.common.collect.Maps;
/*    */ import java.util.Iterator;
/*    */ import java.util.Map;
/*    */ import java.util.NoSuchElementException;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class MapPopulator
/*    */ {
/*    */   private static final String __OBFID = "CL_00002318";
/*    */   
/*    */   public static Map createMap(Iterable keys, Iterable values) {
/* 20 */     return populateMap(keys, values, Maps.newLinkedHashMap());
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static Map populateMap(Iterable keys, Iterable values, Map map) {
/* 28 */     Iterator var3 = values.iterator();
/* 29 */     Iterator var4 = keys.iterator();
/*    */     
/* 31 */     while (var4.hasNext()) {
/*    */       
/* 33 */       Object var5 = var4.next();
/* 34 */       map.put(var5, var3.next());
/*    */     } 
/*    */     
/* 37 */     if (var3.hasNext())
/*    */     {
/* 39 */       throw new NoSuchElementException();
/*    */     }
/*    */ 
/*    */     
/* 43 */     return map;
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraf\\util\MapPopulator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */