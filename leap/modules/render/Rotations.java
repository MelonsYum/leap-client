/*    */ package leap.modules.render;
/*    */ 
/*    */ import leap.events.Event;
/*    */ import leap.events.listeners.EventMotion;
/*    */ import leap.modules.Module;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Rotations
/*    */   extends Module
/*    */ {
/*    */   public Rotations() {
/* 16 */     super("Rotations", 0, Module.Category.RENDER);
/*    */   }
/*    */   
/*    */   public void onDisable() {
/* 20 */     super.onDisable();
/*    */   }
/*    */   
/*    */   public void onEnable() {
/* 24 */     super.onEnable();
/*    */   }
/*    */   
/*    */   public void onEvent(Event e) {
/* 28 */     if (e instanceof EventMotion) {
/* 29 */       EventMotion event = (EventMotion)e;
/*    */       
/* 31 */       mc.thePlayer.renderYawOffset = event.getYaw();
/* 32 */       mc.thePlayer.rotationYawHead = event.getYaw();
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\leap\modules\render\Rotations.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */