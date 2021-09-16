/*    */ package leap.notifications;
/*    */ 
/*    */ public class SmoothStepAnimation
/*    */   extends Animation {
/*    */   public SmoothStepAnimation(int ms, double endPoint) {
/*  6 */     super(ms, endPoint);
/*    */   }
/*    */   
/*    */   public SmoothStepAnimation(int ms, double endPoint, Enum<Direction> direction) {
/* 10 */     super(ms, endPoint, direction);
/*    */   }
/*    */   
/*    */   protected double getEquation(double x) {
/* 14 */     double x1 = x / this.duration;
/* 15 */     return -2.0D * Math.pow(x1, 3.0D) + 3.0D * Math.pow(x1, 2.0D);
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\leap\notifications\SmoothStepAnimation.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */