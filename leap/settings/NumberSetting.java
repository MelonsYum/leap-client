/*    */ package leap.settings;
/*    */ 
/*    */ public class NumberSetting extends Setting {
/*    */   public double value;
/*    */   public double minimum;
/*    */   
/*    */   public NumberSetting(String name, double value, double minimum, double maximum, double increment) {
/*  8 */     this.name = name;
/*  9 */     this.value = value;
/* 10 */     this.minimum = minimum;
/* 11 */     this.maximum = maximum;
/* 12 */     this.increment = increment;
/*    */   }
/*    */   public double maximum; public double increment;
/*    */   public double getValue() {
/* 16 */     return this.value;
/*    */   }
/*    */   
/*    */   public void setValue(double value) {
/* 20 */     double precision = 1.0D / this.increment;
/* 21 */     this.value = Math.round(Math.max(this.minimum, Math.min(this.maximum, value)) * precision) / precision;
/*    */   }
/*    */   
/*    */   public void increment(boolean positive) {
/* 25 */     setValue(getValue() + (positive ? true : -1) * this.increment);
/*    */   }
/*    */   
/*    */   public double getMinimum() {
/* 29 */     return this.minimum;
/*    */   }
/*    */   
/*    */   public void setMinimum(double minimum) {
/* 33 */     this.minimum = minimum;
/*    */   }
/*    */   
/*    */   public double getMaximum() {
/* 37 */     return this.maximum;
/*    */   }
/*    */   
/*    */   public void setMaximum(double maximum) {
/* 41 */     this.maximum = maximum;
/*    */   }
/*    */   
/*    */   public double getIncrement() {
/* 45 */     return this.increment;
/*    */   }
/*    */   
/*    */   public void setIncrement(double increment) {
/* 49 */     this.increment = increment;
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\leap\settings\NumberSetting.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */