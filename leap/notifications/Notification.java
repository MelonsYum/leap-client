/*     */ package leap.notifications;
/*     */ 
/*     */ import leap.Client;
/*     */ import leap.modules.render.NotificationsModule;
/*     */ import leap.util.Timer;
/*     */ 
/*     */ 
/*     */ public class Notification
/*     */ {
/*     */   private final NotificationType type;
/*     */   public float y;
/*     */   public boolean vanishing;
/*  13 */   Timer timer = new Timer();
/*     */   
/*     */   public float width;
/*     */   public float height;
/*     */   public float roundness;
/*     */   public float time;
/*     */   
/*     */   public Notification(NotificationType type, String title, String description, double time2) {
/*  21 */     this.title = title;
/*  22 */     this.description = description;
/*  23 */     this.width = (float)(125.0D * NotificationsModule.length.getValue());
/*  24 */     this.height = 30.0F;
/*  25 */     this.roundness = 0.0F;
/*  26 */     this.time = (float)(long)(time2 * 1000.0D);
/*  27 */     this.timer = new Timer();
/*  28 */     this.type = type;
/*     */   }
/*     */   private String title; private String description; private boolean heightAdjusted; private Animation animation; private Direction direction;
/*     */   public static void post(NotificationType type, String title, String description, double time) {
/*  32 */     Client.notificationManager.add(new Notification(type, title, description, time));
/*     */   }
/*     */   
/*     */   public void post() {
/*  36 */     Client.notificationManager.add(this);
/*     */   }
/*     */   
/*     */   public boolean isHeightAdjusted() {
/*  40 */     return this.heightAdjusted;
/*     */   }
/*     */   
/*     */   public void setHeightAdjusted(boolean bool) {
/*  44 */     this.heightAdjusted = bool;
/*     */   }
/*     */   
/*     */   public NotificationType getType() {
/*  48 */     return this.type;
/*     */   }
/*     */   
/*     */   public float getWidth() {
/*  52 */     return this.width;
/*     */   }
/*     */   
/*     */   public void setWidth(float width) {
/*  56 */     this.width = width;
/*     */   }
/*     */   
/*     */   public float getHeight() {
/*  60 */     return this.height;
/*     */   }
/*     */   
/*     */   public void setHeight(float height) {
/*  64 */     this.height = height;
/*     */   }
/*     */   
/*     */   public float getRoundness() {
/*  68 */     return this.roundness;
/*     */   }
/*     */   
/*     */   public void setRoundness(float roundness) {
/*  72 */     this.roundness = roundness;
/*     */   }
/*     */   
/*     */   public float getTime() {
/*  76 */     return this.time;
/*     */   }
/*     */   
/*     */   public void setTime(float time) {
/*  80 */     this.time = time;
/*     */   }
/*     */   
/*     */   public String getTitle() {
/*  84 */     return this.title;
/*     */   }
/*     */   
/*     */   public void setTitle(String title) {
/*  88 */     this.title = title;
/*     */   }
/*     */   
/*     */   public String getDescription() {
/*  92 */     return this.description;
/*     */   }
/*     */   
/*     */   public void setDescription(String description) {
/*  96 */     this.description = description;
/*     */   }
/*     */   
/*     */   public void startAnimation(Animation animation) {
/* 100 */     this.animation = animation;
/*     */   }
/*     */   
/*     */   public void stopAnimation() {
/* 104 */     this.animation = null;
/*     */   }
/*     */   
/*     */   public Animation getAnimation() {
/* 108 */     return this.animation;
/*     */   }
/*     */   
/*     */   public Direction getDirection() {
/* 112 */     return this.direction;
/*     */   }
/*     */   
/*     */   public void setDirection(Direction direction) {
/* 116 */     this.direction = direction;
/*     */   }
/*     */   
/*     */   public boolean animationInProgress() {
/* 120 */     return (this.animation != null && !this.animation.isDone());
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\leap\notifications\Notification.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */