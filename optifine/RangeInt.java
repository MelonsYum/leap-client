/*    */ package optifine;
/*    */ 
/*    */ 
/*    */ public class RangeInt
/*    */ {
/*    */   private int min;
/*    */   private int max;
/*    */   
/*    */   public RangeInt(int min, int max) {
/* 10 */     this.min = Math.min(min, max);
/* 11 */     this.max = Math.max(min, max);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isInRange(int val) {
/* 16 */     return (val < this.min) ? false : ((val <= this.max));
/*    */   }
/*    */ 
/*    */   
/*    */   public int getMin() {
/* 21 */     return this.min;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getMax() {
/* 26 */     return this.max;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 31 */     return "min: " + this.min + ", max: " + this.max;
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\optifine\RangeInt.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */