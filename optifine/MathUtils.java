/*    */ package optifine;
/*    */ 
/*    */ import java.math.BigDecimal;
/*    */ import java.math.RoundingMode;
/*    */ 
/*    */ 
/*    */ public class MathUtils
/*    */ {
/*    */   public static int getAverage(int[] vals) {
/* 10 */     if (vals.length <= 0)
/*    */     {
/* 12 */       return 0;
/*    */     }
/*    */ 
/*    */     
/* 16 */     int sum = 0;
/*    */     
/*    */     int avg;
/* 19 */     for (avg = 0; avg < vals.length; avg++) {
/*    */       
/* 21 */       int val = vals[avg];
/* 22 */       sum += val;
/*    */     } 
/*    */     
/* 25 */     avg = sum / vals.length;
/* 26 */     return avg;
/*    */   }
/*    */ 
/*    */   
/*    */   public static double square(double squareMe) {
/* 31 */     squareMe *= squareMe;
/* 32 */     return squareMe;
/*    */   }
/*    */   
/*    */   public static double round(double num, double increment) {
/* 36 */     if (increment < 0.0D) {
/* 37 */       throw new IllegalArgumentException();
/*    */     }
/* 39 */     BigDecimal bd = new BigDecimal(num);
/* 40 */     bd = bd.setScale((int)increment, RoundingMode.HALF_UP);
/* 41 */     return bd.doubleValue();
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\optifine\MathUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */