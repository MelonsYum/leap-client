/*    */ package leap.notifications;
/*    */ 
/*    */ import leap.util.Timer;
/*    */ 
/*    */ public abstract class Animation {
/*  6 */   public Timer timer = new Timer();
/*    */   protected int duration;
/*    */   protected double endPoint;
/*    */   protected Enum<Direction> direction;
/*    */   
/*    */   public Animation(int ms, double endPoint) {
/* 12 */     this.duration = ms;
/* 13 */     this.endPoint = endPoint;
/* 14 */     this.direction = Direction.FORWARDS;
/*    */   }
/*    */   
/*    */   public Animation(int ms, double endPoint, Enum<Direction> direction) {
/* 18 */     this.duration = ms;
/* 19 */     this.endPoint = endPoint;
/* 20 */     this.direction = direction;
/*    */   }
/*    */   
/*    */   public double getTimerOutput() {
/* 24 */     return this.timer.lastMS / this.duration;
/*    */   }
/*    */   
/*    */   public double getEndPoint() {
/* 28 */     return this.endPoint;
/*    */   }
/*    */   
/*    */   public void reset() {
/* 32 */     this.timer.reset();
/*    */   }
/*    */   
/*    */   public boolean isDone() {
/* 36 */     return this.timer.hasTimeElapsed(this.duration, false);
/*    */   }
/*    */   
/*    */   public void changeDirection() {
/* 40 */     if (this.direction == Direction.FORWARDS) {
/* 41 */       this.direction = Direction.BACKWARDS;
/*    */     } else {
/* 43 */       this.direction = Direction.FORWARDS;
/*    */     } 
/* 45 */     this.timer.setTime(System.currentTimeMillis() - this.duration - Math.min(this.duration, this.timer.lastMS));
/*    */   }
/*    */   
/*    */   public Enum<Direction> getDirection() {
/* 49 */     return this.direction;
/*    */   }
/*    */   
/*    */   public void setDirection(Enum<Direction> direction) {
/* 53 */     if (this.direction != direction) {
/* 54 */       this.timer.setTime(System.currentTimeMillis() - this.duration - Math.min(this.duration, this.timer.lastMS));
/* 55 */       this.direction = direction;
/*    */     } 
/*    */   }
/*    */   
/*    */   public void setDuration(int duration) {
/* 60 */     this.duration = duration;
/*    */   }
/*    */   
/*    */   public double getOutput() {
/* 64 */     if (this.direction == Direction.FORWARDS) {
/* 65 */       if (isDone())
/* 66 */         return this.endPoint; 
/* 67 */       return getEquation(this.timer.lastMS) * this.endPoint;
/*    */     } 
/* 69 */     if (isDone())
/* 70 */       return 0.0D; 
/* 71 */     return (1.0D - getEquation(this.timer.lastMS)) * this.endPoint;
/*    */   }
/*    */   
/*    */   protected abstract double getEquation(double paramDouble);
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\leap\notifications\Animation.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */