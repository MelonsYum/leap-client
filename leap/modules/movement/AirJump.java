/*    */ package leap.modules.movement;
/*    */ 
/*    */ import leap.events.Event;
/*    */ import leap.modules.Module;
/*    */ import org.lwjgl.input.Keyboard;
/*    */ 
/*    */ public class AirJump
/*    */   extends Module {
/*    */   public AirJump() {
/* 10 */     super("AirJump", 0, Module.Category.MOVEMENT);
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
/* 23 */       Keyboard.isKeyDown(57))
/* 24 */       mc.thePlayer.onGround = true; 
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\leap\modules\movement\AirJump.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */