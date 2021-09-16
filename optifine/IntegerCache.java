/*    */ package optifine;
/*    */ 
/*    */ public class IntegerCache
/*    */ {
/*    */   private static final int CACHE_SIZE = 4096;
/*  6 */   private static final Integer[] cache = makeCache(4096);
/*    */ 
/*    */   
/*    */   private static Integer[] makeCache(int size) {
/* 10 */     Integer[] arr = new Integer[size];
/*    */     
/* 12 */     for (int i = 0; i < size; i++)
/*    */     {
/* 14 */       arr[i] = new Integer(i);
/*    */     }
/*    */     
/* 17 */     return arr;
/*    */   }
/*    */ 
/*    */   
/*    */   public static Integer valueOf(int value) {
/* 22 */     return (value >= 0 && value < 4096) ? cache[value] : new Integer(value);
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\optifine\IntegerCache.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */