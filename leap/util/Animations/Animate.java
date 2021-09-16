/*    */ package leap.util.Animations;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Animate
/*    */ {
/* 10 */   private Easing ease = Easing.LINEAR;
/* 11 */   private float value = 0.0F;
/* 12 */   private float min = 0.0F;
/* 13 */   private float max = 1.0F;
/* 14 */   private float speed = 50.0F; private boolean reversed = false;
/*    */   private float time;
/*    */   
/*    */   public void reset() {
/* 18 */     this.time = this.min;
/*    */   }
/*    */   public void update() {
/* 21 */     if (this.reversed) {
/* 22 */       if (this.time > this.min) this.time -= Delta.DELTATIME * 0.001F * this.speed; 
/* 23 */       if (this.time < this.min) this.time = this.min; 
/*    */     } else {
/* 25 */       if (this.time < this.max) this.time += Delta.DELTATIME * 0.001F * this.speed; 
/* 26 */       if (this.time > this.max) this.time = this.max; 
/*    */     } 
/* 28 */     this.value = getEase().ease(this.time, this.min, this.max, this.max);
/*    */   }
/*    */   
/*    */   public float getValue() {
/* 32 */     return this.value;
/* 33 */   } public float getMin() { return this.min; }
/* 34 */   public float getMax() { return this.max; }
/* 35 */   public float getSpeed() { return this.speed; }
/* 36 */   public boolean isReversed() { return this.reversed; } public Easing getEase() {
/* 37 */     return this.ease;
/*    */   }
/*    */   
/* 40 */   public void setValue(float value) { this.value = value; }
/* 41 */   public void setMin(float min) { this.min = min; }
/* 42 */   public void setMax(float max) { this.max = max; }
/* 43 */   public void setSpeed(float speed) { this.speed = speed; }
/* 44 */   public void setReversed(boolean reversed) { this.reversed = reversed; } public void setEase(Easing ease) {
/* 45 */     this.ease = ease;
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\lea\\util\Animations\Animate.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */