/*    */ package leap.notifications;
/*    */ 
/*    */ public class LogisticAnimation extends Animation {
/*    */   private final double steepness;
/*    */   
/*    */   public LogisticAnimation(int ms, int distance, double steepness) {
/*  7 */     super(ms, distance);
/*  8 */     this.steepness = steepness;
/*    */   }
/*    */   
/*    */   public LogisticAnimation(int ms, int distance) {
/* 12 */     super(ms, distance);
/* 13 */     this.steepness = 1.0D;
/*    */   }
/*    */   
/*    */   public LogisticAnimation(int ms, int distance, Enum<Direction> direction, double steepness) {
/* 17 */     super(ms, distance, direction);
/* 18 */     this.steepness = steepness;
/*    */   }
/*    */   
/*    */   protected double getEquation(double x) {
/* 22 */     return this.duration / (1.0D + Math.exp(-this.steepness * (x - (this.duration / 2)))) / this.duration;
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\leap\notifications\LogisticAnimation.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */