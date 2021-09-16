/*    */ package leap.events.listeners;
/*    */ 
/*    */ import leap.events.Event;
/*    */ 
/*    */ public class EventMotion
/*    */   extends Event<EventMotion> {
/*    */   public double x;
/*    */   public double y;
/*    */   public double z;
/*    */   
/*    */   public EventMotion(double x, double y, double z, float yaw, float pitch, boolean onGround) {
/* 12 */     this.x = x;
/* 13 */     this.y = y;
/* 14 */     this.z = z;
/* 15 */     this.yaw = yaw;
/* 16 */     this.pitch = pitch;
/* 17 */     this.onGround = onGround;
/*    */   }
/*    */   public float yaw; public float pitch; public boolean onGround;
/*    */   public double getX() {
/* 21 */     return this.x;
/*    */   }
/*    */   
/*    */   public void setX(double x) {
/* 25 */     this.x = x;
/*    */   }
/*    */   
/*    */   public double getY() {
/* 29 */     return this.y;
/*    */   }
/*    */   
/*    */   public void setY(double y) {
/* 33 */     this.y = y;
/*    */   }
/*    */   
/*    */   public double getZ() {
/* 37 */     return this.z;
/*    */   }
/*    */   
/*    */   public void setZ(double z) {
/* 41 */     this.z = z;
/*    */   }
/*    */   
/*    */   public float getYaw() {
/* 45 */     return this.yaw;
/*    */   }
/*    */   
/*    */   public void setYaw(float yaw) {
/* 49 */     this.yaw = yaw;
/*    */   }
/*    */   
/*    */   public float getPitch() {
/* 53 */     return this.pitch;
/*    */   }
/*    */   
/*    */   public void setPitch(float pitch) {
/* 57 */     this.pitch = pitch;
/*    */   }
/*    */   
/*    */   public boolean isOnGround() {
/* 61 */     return this.onGround;
/*    */   }
/*    */   
/*    */   public void setOnGround(boolean onGround) {
/* 65 */     this.onGround = onGround;
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\leap\events\listeners\EventMotion.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */