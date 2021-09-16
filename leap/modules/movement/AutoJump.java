/*    */ package leap.modules.movement;
/*    */ 
/*    */ import leap.events.Event;
/*    */ import leap.modules.Module;
/*    */ 
/*    */ public class AutoJump
/*    */   extends Module
/*    */ {
/*    */   public AutoJump() {
/* 10 */     super("AutoJump", 0, Module.Category.MOVEMENT);
/*    */   }
/*    */   
/*    */   public void onEnable() {
/* 14 */     super.onEnable();
/*    */   }
/*    */   
/*    */   public void onDisable() {
/* 18 */     super.onDisable();
/*    */   }
/*    */   
/*    */   public void onEvent(Event e) {
/* 22 */     if (e instanceof leap.events.listeners.EventUpdate && 
/* 23 */       mc.thePlayer.onGround)
/* 24 */       mc.thePlayer.jump(); 
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\leap\modules\movement\AutoJump.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */