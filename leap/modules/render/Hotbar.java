/*    */ package leap.modules.render;
/*    */ 
/*    */ import leap.events.Event;
/*    */ import leap.modules.Module;
/*    */ import leap.settings.ModeSetting;
/*    */ import leap.settings.Setting;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Hotbar
/*    */   extends Module
/*    */ {
/* 13 */   public static ModeSetting look = new ModeSetting("Look", "Long", new String[] { "Normal Custom", "Normal", "Long" });
/*    */   
/*    */   public Hotbar() {
/* 16 */     super("Hotbar", 0, Module.Category.RENDER);
/* 17 */     addSettings(new Setting[] { (Setting)look });
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


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\leap\modules\render\Hotbar.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */