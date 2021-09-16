/*    */ package leap.modules.memes;
/*    */ 
/*    */ import leap.events.Event;
/*    */ import leap.modules.Module;
/*    */ import leap.util.Timer;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Ligma
/*    */   extends Module
/*    */ {
/* 26 */   Timer timer = new Timer();
/*    */   public Ligma() {
/* 28 */     super("Ligma", 0, Module.Category.MEMES);
/*    */   }
/*    */ 
/*    */   
/*    */   public void onDisable() {
/* 33 */     super.onDisable();
/*    */   }
/*    */   
/*    */   public void onEnable() {
/* 37 */     mc.thePlayer.sendChatMessage("guys i think i have ligma");
/* 38 */     super.onEnable();
/*    */   }
/*    */ 
/*    */   
/*    */   public void onEvent(Event e) {
/* 43 */     if (e instanceof leap.events.listeners.EventUpdate && 
/* 44 */       e.isPre() && 
/* 45 */       this.timer.hasTimeElapsed(5000L, true)) {
/* 46 */       mc.thePlayer.sendChatMessage("ligma balls");
/* 47 */       toggle();
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\leap\modules\memes\Ligma.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */