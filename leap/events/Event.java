/*    */ package leap.events;
/*    */ 
/*    */ import net.minecraft.client.Minecraft;
/*    */ 
/*    */ public class Event<T>
/*    */ {
/*  7 */   private Minecraft mc = Minecraft.getMinecraft();
/*    */   public boolean cancelled;
/*    */   public EventType type;
/*    */   public EventDirection direction;
/*    */   
/*    */   public boolean isCancelled() {
/* 13 */     return this.cancelled;
/*    */   }
/*    */   public void setCancelled(boolean cancelled) {
/* 16 */     this.cancelled = cancelled;
/*    */   }
/*    */   public EventType getType() {
/* 19 */     return this.type;
/*    */   }
/*    */   public void setType(EventType type) {
/* 22 */     this.type = type;
/*    */   }
/*    */   public EventDirection getDirection() {
/* 25 */     return this.direction;
/*    */   }
/*    */   public void setDirection(EventDirection direction) {
/* 28 */     this.direction = direction;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isPre() {
/* 33 */     if (this.type == null) {
/* 34 */       return false;
/*    */     }
/* 36 */     return (this.type == EventType.PRE);
/*    */   }
/*    */   public boolean isPost() {
/* 39 */     if (this.type == null) {
/* 40 */       return false;
/*    */     }
/* 42 */     return (this.type == EventType.POST);
/*    */   }
/*    */   public boolean isIncoming() {
/* 45 */     if (this.direction == null) {
/* 46 */       return false;
/*    */     }
/* 48 */     return (this.direction == EventDirection.INCOMING);
/*    */   }
/*    */   public boolean isOutgoing() {
/* 51 */     if (this.direction == null) {
/* 52 */       return false;
/*    */     }
/* 54 */     return (this.direction == EventDirection.OUTGOING);
/*    */   }
/*    */   
/*    */   public void setMotion(double speed) {
/* 58 */     double forward = this.mc.thePlayer.movementInput.moveForward;
/* 59 */     double strafe = this.mc.thePlayer.movementInput.moveStrafe;
/* 60 */     float yaw = this.mc.thePlayer.rotationYaw;
/* 61 */     if (forward == 0.0D && strafe == 0.0D) {
/* 62 */       this.mc.thePlayer.motionX = 0.0D;
/* 63 */       this.mc.thePlayer.motionZ = 0.0D;
/*    */     } else {
/* 65 */       if (forward != 0.0D) {
/* 66 */         if (strafe > 0.0D) {
/* 67 */           yaw += ((forward > 0.0D) ? -45 : 45);
/* 68 */         } else if (strafe < 0.0D) {
/* 69 */           yaw += ((forward > 0.0D) ? 45 : -45);
/*    */         } 
/* 71 */         strafe = 0.0D;
/* 72 */         if (forward > 0.0D) {
/* 73 */           forward = 1.0D;
/* 74 */         } else if (forward < 0.0D) {
/* 75 */           forward = -1.0D;
/*    */         } 
/*    */       } 
/* 78 */       this.mc.thePlayer.motionX = forward * speed * Math.cos(Math.toRadians((yaw + 90.0F))) + strafe * speed * Math.sin(Math.toRadians((yaw + 90.0F)));
/* 79 */       this.mc.thePlayer.motionZ = forward * speed * Math.sin(Math.toRadians((yaw + 90.0F))) - strafe * speed * Math.cos(Math.toRadians((yaw + 90.0F)));
/*    */     } 
/*    */   }
/*    */   
/*    */   public void setSideMotion(double speed) {
/* 84 */     double strafe = this.mc.thePlayer.movementInput.moveStrafe;
/* 85 */     float yaw = this.mc.thePlayer.rotationYaw;
/* 86 */     if (strafe == 0.0D) {
/* 87 */       this.mc.thePlayer.motionX = 0.0D;
/* 88 */       this.mc.thePlayer.motionZ = 0.0D;
/*    */     } else {
/* 90 */       this.mc.thePlayer.motionX = speed * Math.cos(Math.toRadians((yaw + 90.0F))) + strafe * speed * Math.sin(Math.toRadians((yaw + 90.0F)));
/* 91 */       this.mc.thePlayer.motionZ = speed * Math.sin(Math.toRadians((yaw + 90.0F))) - strafe * speed * Math.cos(Math.toRadians((yaw + 90.0F)));
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\leap\events\Event.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */