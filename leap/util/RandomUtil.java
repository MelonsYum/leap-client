/*   */ package leap.util;
/*   */ 
/*   */ 
/*   */ public class RandomUtil
/*   */ {
/*   */   public static int randomNumber(double max, double min) {
/* 7 */     int ii = (int)(-min + (int)(Math.random() * (max - -min + 1.0D)));
/* 8 */     return ii;
/*   */   }
/*   */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\lea\\util\RandomUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */