/*     */ package leap.util.Animations;public interface Easing { public static final Easing LINEAR;
/*     */   public static final Easing QUAD_IN;
/*     */   public static final Easing QUAD_OUT;
/*     */   public static final Easing QUAD_IN_OUT;
/*     */   public static final Easing CUBIC_IN;
/*     */   
/*     */   static {
/*   8 */     LINEAR = ((t, b, c, d) -> c * t / d + b);
/*     */ 
/*     */ 
/*     */     
/*  12 */     QUAD_IN = ((t, b, c, d) -> c * (t /= d) * t + b);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  18 */     QUAD_OUT = ((t, b, c, d) -> -c * (t /= d) * (t - 2.0F) + b);
/*     */ 
/*     */ 
/*     */     
/*  22 */     QUAD_IN_OUT = ((t, b, c, d) -> ((t /= d / 2.0F) < 1.0F) ? (c / 2.0F * t * t + b) : (-c / 2.0F * (--t * (t - 2.0F) - 1.0F) + b));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  29 */     CUBIC_IN = ((t, b, c, d) -> c * (t /= d) * t * t + b);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  36 */     CUBIC_OUT = ((t, b, c, d) -> c * ((t = t / d - 1.0F) * t * t + 1.0F) + b);
/*     */ 
/*     */ 
/*     */     
/*  40 */     CUBIC_IN_OUT = ((t, b, c, d) -> ((t /= d / 2.0F) < 1.0F) ? (c / 2.0F * t * t * t + b) : (c / 2.0F * ((t -= 2.0F) * t * t + 2.0F) + b));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  47 */     QUARTIC_IN = ((t, b, c, d) -> c * (t /= d) * t * t * t + b);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  53 */     QUARTIC_OUT = ((t, b, c, d) -> -c * ((t = t / d - 1.0F) * t * t * t - 1.0F) + b);
/*     */ 
/*     */ 
/*     */     
/*  57 */     QUARTIC_IN_OUT = ((t, b, c, d) -> ((t /= d / 2.0F) < 1.0F) ? (c / 2.0F * t * t * t * t + b) : (-c / 2.0F * ((t -= 2.0F) * t * t * t - 2.0F) + b));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  64 */     QUINTIC_IN = ((t, b, c, d) -> c * (t /= d) * t * t * t * t + b);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  70 */     QUINTIC_OUT = ((t, b, c, d) -> c * ((t = t / d - 1.0F) * t * t * t * t + 1.0F) + b);
/*     */ 
/*     */ 
/*     */     
/*  74 */     QUINTIC_IN_OUT = ((t, b, c, d) -> ((t /= d / 2.0F) < 1.0F) ? (c / 2.0F * t * t * t * t * t + b) : (c / 2.0F * ((t -= 2.0F) * t * t * t * t + 2.0F) + b));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  81 */     SINE_IN = ((t, b, c, d) -> -c * (float)Math.cos((t / d) * 1.5707963267948966D) + c + b);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  88 */     SINE_OUT = ((t, b, c, d) -> c * (float)Math.sin((t / d) * 1.5707963267948966D) + b);
/*     */ 
/*     */ 
/*     */     
/*  92 */     SINE_IN_OUT = ((t, b, c, d) -> -c / 2.0F * ((float)Math.cos(Math.PI * t / d) - 1.0F) + b);
/*     */ 
/*     */ 
/*     */     
/*  96 */     EXPO_IN = ((t, b, c, d) -> (t == 0.0F) ? b : (c * (float)Math.pow(2.0D, (10.0F * (t / d - 1.0F))) + b));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 102 */     EXPO_OUT = ((t, b, c, d) -> (t == d) ? (b + c) : (c * (-((float)Math.pow(2.0D, (-10.0F * t / d))) + 1.0F) + b));
/*     */ 
/*     */ 
/*     */     
/* 106 */     EXPO_IN_OUT = ((t, b, c, d) -> (t == 0.0F) ? b : ((t == d) ? (b + c) : (((t /= d / 2.0F) < 1.0F) ? (c / 2.0F * (float)Math.pow(2.0D, (10.0F * (t - 1.0F))) + b) : (c / 2.0F * (-((float)Math.pow(2.0D, (-10.0F * --t))) + 2.0F) + b))));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 115 */     CIRC_IN = ((t, b, c, d) -> -c * ((float)Math.sqrt((1.0F - (t /= d) * t)) - 1.0F) + b);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 122 */     CIRC_OUT = ((t, b, c, d) -> c * (float)Math.sqrt((1.0F - (t = t / d - 1.0F) * t)) + b);
/*     */ 
/*     */ 
/*     */     
/* 126 */     CIRC_IN_OUT = ((t, b, c, d) -> ((t /= d / 2.0F) < 1.0F) ? (-c / 2.0F * ((float)Math.sqrt((1.0F - t * t)) - 1.0F) + b) : (c / 2.0F * ((float)Math.sqrt((1.0F - (t -= 2.0F) * t)) + 1.0F) + b));
/*     */   }
/*     */   public static final Easing CUBIC_OUT; public static final Easing CUBIC_IN_OUT; public static final Easing QUARTIC_IN; public static final Easing QUARTIC_OUT; public static final Easing QUARTIC_IN_OUT; public static final Easing QUINTIC_IN; public static final Easing QUINTIC_OUT; public static final Easing QUINTIC_IN_OUT; public static final Easing SINE_IN; public static final Easing SINE_OUT; public static final Easing SINE_IN_OUT; public static final Easing EXPO_IN; public static final Easing EXPO_OUT;
/*     */   public static final Easing EXPO_IN_OUT;
/*     */   public static final Easing CIRC_IN;
/*     */   public static final Easing CIRC_OUT;
/*     */   public static final Easing CIRC_IN_OUT;
/* 133 */   public static final Elastic ELASTIC_IN = new ElasticIn();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 139 */   public static final Elastic ELASTIC_OUT = new ElasticOut();
/*     */ 
/*     */ 
/*     */   
/* 143 */   public static final Elastic ELASTIC_IN_OUT = new ElasticInOut();
/*     */ 
/*     */ 
/*     */   
/* 147 */   public static final Back BACK_IN = new BackIn();
/*     */ 
/*     */ 
/*     */   
/* 151 */   public static final Back BACK_OUT = new BackOut();
/*     */ 
/*     */ 
/*     */   
/* 155 */   public static final Back BACK_IN_OUT = new BackInOut(); public static final Easing BOUNCE_OUT; public static final Easing BOUNCE_IN;
/*     */   public static final Easing BOUNCE_IN_OUT;
/*     */   
/*     */   static {
/* 159 */     BOUNCE_OUT = ((t, b, c, d) -> ((t /= d) < 0.36363637F) ? (c * 7.5625F * t * t + b) : ((t < 0.72727275F) ? (c * (7.5625F * (t -= 0.54545456F) * t + 0.75F) + b) : ((t < 0.90909094F) ? (c * (7.5625F * (t -= 0.8181818F) * t + 0.9375F) + b) : (c * (7.5625F * (t -= 0.95454544F) * t + 0.984375F) + b))));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 173 */     BOUNCE_IN = ((t, b, c, d) -> c - BOUNCE_OUT.ease(d - t, 0.0F, c, d) + b);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 179 */     BOUNCE_IN_OUT = ((t, b, c, d) -> (t < d / 2.0F) ? (BOUNCE_IN.ease(t * 2.0F, 0.0F, c, d) * 0.5F + b) : (BOUNCE_OUT.ease(t * 2.0F - d, 0.0F, c, d) * 0.5F + c * 0.5F + b));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   float ease(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static abstract class Elastic
/*     */     implements Easing
/*     */   {
/*     */     private float amplitude;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     private float period;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public Elastic(float amplitude, float period) {
/* 209 */       this.amplitude = amplitude;
/* 210 */       this.period = period;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public Elastic() {
/* 217 */       this(-1.0F, 0.0F);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public float getPeriod() {
/* 226 */       return this.period;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void setPeriod(float period) {
/* 235 */       this.period = period;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public float getAmplitude() {
/* 244 */       return this.amplitude;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void setAmplitude(float amplitude) {
/* 253 */       this.amplitude = amplitude;
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public static class ElasticIn
/*     */     extends Elastic
/*     */   {
/*     */     public ElasticIn(float amplitude, float period) {
/* 262 */       super(amplitude, period);
/*     */     }
/*     */ 
/*     */     
/*     */     public ElasticIn() {}
/*     */ 
/*     */     
/*     */     public float ease(float t, float b, float c, float d) {
/* 270 */       float a = getAmplitude();
/* 271 */       float p = getPeriod();
/* 272 */       if (t == 0.0F) return b; 
/* 273 */       if ((t /= d) == 1.0F) return b + c; 
/* 274 */       if (p == 0.0F) p = d * 0.3F; 
/* 275 */       float s = 0.0F;
/* 276 */       if (a < Math.abs(c))
/* 277 */       { a = c;
/* 278 */         s = p / 4.0F; }
/* 279 */       else { s = p / 6.2831855F * (float)Math.asin((c / a)); }
/* 280 */        return -(a * (float)Math.pow(2.0D, (10.0F * --t)) * (float)Math.sin((t * d - s) * 6.283185307179586D / p)) + b;
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public static class ElasticOut
/*     */     extends Elastic
/*     */   {
/*     */     public ElasticOut(float amplitude, float period) {
/* 289 */       super(amplitude, period);
/*     */     }
/*     */ 
/*     */     
/*     */     public ElasticOut() {}
/*     */ 
/*     */     
/*     */     public float ease(float t, float b, float c, float d) {
/* 297 */       float a = getAmplitude();
/* 298 */       float p = getPeriod();
/* 299 */       if (t == 0.0F) return b; 
/* 300 */       if ((t /= d) == 1.0F) return b + c; 
/* 301 */       if (p == 0.0F) p = d * 0.3F; 
/* 302 */       float s = 0.0F;
/* 303 */       if (a < Math.abs(c))
/* 304 */       { a = c;
/* 305 */         s = p / 4.0F; }
/* 306 */       else { s = p / 6.2831855F * (float)Math.asin((c / a)); }
/* 307 */        return a * (float)Math.pow(2.0D, (-10.0F * t)) * (float)Math.sin((t * d - s) * 6.283185307179586D / p) + c + b;
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public static class ElasticInOut
/*     */     extends Elastic
/*     */   {
/*     */     public ElasticInOut(float amplitude, float period) {
/* 316 */       super(amplitude, period);
/*     */     }
/*     */ 
/*     */     
/*     */     public ElasticInOut() {}
/*     */ 
/*     */     
/*     */     public float ease(float t, float b, float c, float d) {
/* 324 */       float a = getAmplitude();
/* 325 */       float p = getPeriod();
/* 326 */       if (t == 0.0F) return b; 
/* 327 */       if ((t /= d / 2.0F) == 2.0F) return b + c; 
/* 328 */       if (p == 0.0F) p = d * 0.45000002F; 
/* 329 */       float s = 0.0F;
/* 330 */       if (a < Math.abs(c))
/* 331 */       { a = c;
/* 332 */         s = p / 4.0F; }
/* 333 */       else { s = p / 6.2831855F * (float)Math.asin((c / a)); }
/* 334 */        if (t < 1.0F)
/* 335 */         return -0.5F * a * (float)Math.pow(2.0D, (10.0F * --t)) * (float)Math.sin((t * d - s) * 6.283185307179586D / p) + b; 
/* 336 */       return a * (float)Math.pow(2.0D, (-10.0F * --t)) * (float)Math.sin((t * d - s) * 6.283185307179586D / p) * 0.5F + c + b;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static abstract class Back
/*     */     implements Easing
/*     */   {
/*     */     public static final float DEFAULT_OVERSHOOT = 1.70158F;
/*     */ 
/*     */ 
/*     */     
/*     */     private float overshoot;
/*     */ 
/*     */ 
/*     */     
/*     */     public Back() {
/* 355 */       this(1.70158F);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public Back(float overshoot) {
/* 366 */       this.overshoot = overshoot;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public float getOvershoot() {
/* 375 */       return this.overshoot;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void setOvershoot(float overshoot) {
/* 384 */       this.overshoot = overshoot;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static class BackIn
/*     */     extends Back
/*     */   {
/*     */     public BackIn() {}
/*     */ 
/*     */ 
/*     */     
/*     */     public BackIn(float overshoot) {
/* 399 */       super(overshoot);
/*     */     }
/*     */     
/*     */     public float ease(float t, float b, float c, float d) {
/* 403 */       float s = getOvershoot();
/* 404 */       return c * (t /= d) * t * ((s + 1.0F) * t - s) + b;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static class BackOut
/*     */     extends Back
/*     */   {
/*     */     public BackOut() {}
/*     */ 
/*     */     
/*     */     public BackOut(float overshoot) {
/* 417 */       super(overshoot);
/*     */     }
/*     */     
/*     */     public float ease(float t, float b, float c, float d) {
/* 421 */       float s = getOvershoot();
/* 422 */       return c * ((t = t / d - 1.0F) * t * ((s + 1.0F) * t + s) + 1.0F) + b;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static class BackInOut
/*     */     extends Back
/*     */   {
/*     */     public BackInOut() {}
/*     */ 
/*     */ 
/*     */     
/*     */     public BackInOut(float overshoot) {
/* 436 */       super(overshoot);
/*     */     }
/*     */     
/*     */     public float ease(float t, float b, float c, float d) {
/* 440 */       float s = getOvershoot();
/* 441 */       if ((t /= d / 2.0F) < 1.0F) return c / 2.0F * t * t * (((s = (float)(s * 1.525D)) + 1.0F) * t - s) + b; 
/* 442 */       return c / 2.0F * ((t -= 2.0F) * t * (((s = (float)(s * 1.525D)) + 1.0F) * t + s) + 2.0F) + b;
/*     */     }
/*     */   } }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\lea\\util\Animations\Easing.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */