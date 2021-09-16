/*    */ package leap.modules.movement;
/*    */ 
/*    */ import leap.events.Event;
/*    */ import leap.modules.Module;
/*    */ 
/*    */ public class Sprint
/*    */   extends Module
/*    */ {
/*    */   public Sprint() {
/* 10 */     super("Sprint", 0, Module.Category.MOVEMENT);
/*    */   }
/*    */   
/*    */   public void onEnable() {
/* 14 */     super.onEnable();
/*    */   }
/*    */   
/*    */   public void onDisable() {
/* 18 */     mc.thePlayer.setSprinting(mc.gameSettings.keyBindSprint.getIsKeyPressed());
/* 19 */     super.onDisable();
/*    */   }
/*    */   
/*    */   public void onEvent(Event e) {
/* 23 */     if (e instanceof leap.events.listeners.EventUpdate && 
/* 24 */       e.isPre() && 
/* 25 */       mc.thePlayer.moveForward > 0.0F && !mc.thePlayer.isSneaking() && !mc.thePlayer.isCollidedHorizontally)
/* 26 */       mc.thePlayer.setSprinting(true); 
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\leap\modules\movement\Sprint.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */