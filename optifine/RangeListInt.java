/*    */ package optifine;
/*    */ 
/*    */ public class RangeListInt
/*    */ {
/*  5 */   private RangeInt[] ranges = new RangeInt[0];
/*    */ 
/*    */   
/*    */   public void addRange(RangeInt ri) {
/*  9 */     this.ranges = (RangeInt[])Config.addObjectToArray((Object[])this.ranges, ri);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isInRange(int val) {
/* 14 */     for (int i = 0; i < this.ranges.length; i++) {
/*    */       
/* 16 */       RangeInt ri = this.ranges[i];
/*    */       
/* 18 */       if (ri.isInRange(val))
/*    */       {
/* 20 */         return true;
/*    */       }
/*    */     } 
/*    */     
/* 24 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getCountRanges() {
/* 29 */     return this.ranges.length;
/*    */   }
/*    */ 
/*    */   
/*    */   public RangeInt getRange(int i) {
/* 34 */     return this.ranges[i];
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 39 */     StringBuffer sb = new StringBuffer();
/* 40 */     sb.append("[");
/*    */     
/* 42 */     for (int i = 0; i < this.ranges.length; i++) {
/*    */       
/* 44 */       RangeInt ri = this.ranges[i];
/*    */       
/* 46 */       if (i > 0)
/*    */       {
/* 48 */         sb.append(", ");
/*    */       }
/*    */       
/* 51 */       sb.append(ri.toString());
/*    */     } 
/*    */     
/* 54 */     sb.append("]");
/* 55 */     return sb.toString();
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\optifine\RangeListInt.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */