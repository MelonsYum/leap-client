/*    */ package leap.modules.movement;
/*    */ 
/*    */ import leap.events.Event;
/*    */ import leap.events.listeners.EventReceivePacket;
/*    */ import leap.modules.Module;
/*    */ import leap.settings.ModeSetting;
/*    */ import leap.settings.Setting;
/*    */ import net.minecraft.network.play.server.S12PacketEntityVelocity;
/*    */ import net.minecraft.network.play.server.S27PacketExplosion;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Velocity
/*    */   extends Module
/*    */ {
/* 17 */   public static ModeSetting mode = new ModeSetting("Mode", "Cancel", new String[] { "Motion", "Cancel", "AAC" });
/*    */   
/*    */   public Velocity() {
/* 20 */     super("Velocity", 0, Module.Category.MOVEMENT);
/* 21 */     addSettings(new Setting[] { (Setting)mode });
/*    */   }
/*    */   
/*    */   public void onEvent(Event e) {
/* 25 */     if (mode.getMode() == "AAC" && 
/* 26 */       e instanceof EventReceivePacket) {
/* 27 */       EventReceivePacket event = (EventReceivePacket)e;
/*    */       
/* 29 */       if (event.getPacket() instanceof S12PacketEntityVelocity) {
/*    */         
/* 31 */         S12PacketEntityVelocity packet = (S12PacketEntityVelocity)event.getPacket();
/*    */ 
/*    */         
/* 34 */         packet.motionX = (int)(packet.getMotionX() * 0.25D);
/* 35 */         packet.motionY = (int)(packet.getMotionY() * 0.25D);
/* 36 */         packet.motionZ = (int)(packet.getMotionZ() * 0.25D);
/*    */       } 
/*    */     } 
/*    */ 
/*    */     
/* 41 */     if (mode.getMode() == "Cancel" && 
/* 42 */       e instanceof EventReceivePacket) {
/* 43 */       EventReceivePacket event = (EventReceivePacket)e;
/*    */       
/* 45 */       if (event.getPacket() instanceof S12PacketEntityVelocity) {
/*    */         
/* 47 */         S12PacketEntityVelocity packet = (S12PacketEntityVelocity)event.getPacket();
/*    */ 
/*    */         
/* 50 */         event.setCancelled(true);
/*    */       } 
/*    */       
/* 53 */       if (event.getPacket() instanceof S27PacketExplosion) {
/*    */         
/* 55 */         S27PacketExplosion packet = (S27PacketExplosion)event.getPacket();
/*    */ 
/*    */         
/* 58 */         event.setCancelled(true);
/*    */       } 
/*    */     } 
/*    */ 
/*    */ 
/*    */     
/* 64 */     if (e instanceof leap.events.listeners.EventMotion)
/*    */     {
/*    */       
/* 67 */       if (mode.getMode() == "AAC" && 
/* 68 */         mc.thePlayer.hurtTime > 0.0D && 
/* 69 */         mc.thePlayer.onGround) {
/* 70 */         double yaw = mc.thePlayer.rotationYawHead;
/* 71 */         yaw = Math.toRadians(yaw);
/* 72 */         double dX = -Math.sin(yaw) * 0.045D;
/* 73 */         double dZ = -Math.sin(yaw) * 0.045D;
/*    */         
/* 75 */         mc.thePlayer.motionX = dZ;
/* 76 */         mc.thePlayer.motionZ = dZ;
/*    */       } 
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void onEnable() {
/* 84 */     super.onEnable();
/*    */   }
/*    */   
/*    */   public void onDisable() {
/* 88 */     super.onDisable();
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\leap\modules\movement\Velocity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */