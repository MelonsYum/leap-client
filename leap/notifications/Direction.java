/*    */ package leap.notifications;
/*    */ 
/*    */ public enum Direction {
/*  4 */   FORWARDS(new int[2]),
/*  5 */   BACKWARDS(new int[2]),
/*  6 */   UP(new int[] { 0, -1 }),
/*  7 */   DOWN(new int[] { 0, 1 }),
/*  8 */   LEFT(new int[] { -1 }),
/*  9 */   RIGHT(new int[] { 1 });
/*    */   
/*    */   private final int[] xy;
/*    */   
/*    */   Direction(int[] xy) {
/* 14 */     this.xy = xy;
/*    */   }
/*    */   
/*    */   public int[] getXy() {
/* 18 */     return this.xy;
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\leap\notifications\Direction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */