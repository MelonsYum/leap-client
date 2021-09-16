/*    */ package leap.modules.render;
/*    */ 
/*    */ import leap.events.Event;
/*    */ import leap.modules.Module;
/*    */ import leap.settings.BooleanSetting;
/*    */ import leap.settings.Setting;
/*    */ 
/*    */ 
/*    */ public class BetterChat
/*    */   extends Module
/*    */ {
/* 12 */   public static BooleanSetting cfont = new BooleanSetting("Custom Font", true);
/* 13 */   public static BooleanSetting background = new BooleanSetting("Background", false);
/*    */   
/*    */   public BetterChat() {
/* 16 */     super("BetterChat", 0, Module.Category.RENDER);
/* 17 */     addSettings(new Setting[] { (Setting)cfont, (Setting)background });
/*    */   }
/*    */   
/*    */   public void onEnable() {
/* 21 */     super.onEnable();
/*    */   }
/*    */   
/*    */   public void onDisable() {
/* 25 */     super.onDisable();
/*    */   }
/*    */   
/*    */   public void onEvent(Event e) {
/* 29 */     e instanceof leap.events.listeners.EventUpdate;
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\leap\modules\render\BetterChat.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */