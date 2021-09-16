/*    */ package optifine;
/*    */ 
/*    */ import java.util.Comparator;
/*    */ 
/*    */ public class CustomItemsComparator
/*    */   implements Comparator
/*    */ {
/*    */   public int compare(Object o1, Object o2) {
/*  9 */     CustomItemProperties p1 = (CustomItemProperties)o1;
/* 10 */     CustomItemProperties p2 = (CustomItemProperties)o2;
/* 11 */     return (p1.weight != p2.weight) ? (p2.weight - p1.weight) : (!Config.equals(p1.basePath, p2.basePath) ? p1.basePath.compareTo(p2.basePath) : p1.name.compareTo(p2.name));
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\optifine\CustomItemsComparator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */