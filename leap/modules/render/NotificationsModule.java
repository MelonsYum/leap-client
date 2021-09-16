/*    */ package leap.modules.render;
/*    */ 
/*    */ import leap.events.Event;
/*    */ import leap.modules.Module;
/*    */ import leap.settings.NumberSetting;
/*    */ import leap.settings.Setting;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class NotificationsModule
/*    */   extends Module
/*    */ {
/* 13 */   public static NumberSetting length = new NumberSetting("Length", 1.0D, 0.1D, 3.0D, 0.1D);
/*    */   
/*    */   public NotificationsModule() {
/* 16 */     super("Notifications", 0, Module.Category.RENDER);
/* 17 */     addSettings(new Setting[] { (Setting)length });
/*    */   }
/*    */   
/*    */   public void onDisable() {
/* 21 */     super.onDisable();
/*    */   }
/*    */   
/*    */   public void onEnable() {
/* 25 */     super.onEnable();
/*    */   }
/*    */   
/*    */   public void onEvent(Event e) {
/* 29 */     e instanceof leap.events.listeners.EventMotion;
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\leap\modules\render\NotificationsModule.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */