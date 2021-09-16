/*    */ package leap.modules.player;
/*    */ 
/*    */ import leap.events.Event;
/*    */ import leap.modules.Module;
/*    */ 
/*    */ 
/*    */ public class Respawn
/*    */   extends Module
/*    */ {
/*    */   public Respawn() {
/* 11 */     super("Respawn", 0, Module.Category.PLAYER);
/*    */   }
/*    */   
/*    */   public void onEvent(Event e) {
/* 15 */     if (e instanceof leap.events.listeners.EventUpdate && 
/* 16 */       mc.thePlayer.isDead) {
/* 17 */       mc.thePlayer.respawnPlayer();
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public void onEnable() {
/* 23 */     super.onEnable();
/*    */   }
/*    */   
/*    */   public void onDisable() {
/* 27 */     super.onDisable();
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\leap\modules\player\Respawn.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */