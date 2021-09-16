/*    */ package leap.modules.player;
/*    */ 
/*    */ import leap.Client;
/*    */ import leap.events.Event;
/*    */ import leap.modules.Module;
/*    */ 
/*    */ 
/*    */ public class IRCM
/*    */   extends Module
/*    */ {
/*    */   public IRCM() {
/* 12 */     super("IRC", 0, Module.Category.PLAYER);
/*    */   }
/*    */   
/*    */   public void onEvent(Event e) {
/* 16 */     e instanceof leap.events.listeners.EventUpdate;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void onEnable() {
/* 22 */     Client.startIRC(Client.realPlayerName);
/* 23 */     super.onEnable();
/*    */   }
/*    */   
/*    */   public void onDisable() {
/* 27 */     super.onDisable();
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\leap\modules\player\IRCM.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */