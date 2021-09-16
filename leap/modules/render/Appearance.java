/*    */ package leap.modules.render;
/*    */ 
/*    */ import leap.events.Event;
/*    */ import leap.modules.Module;
/*    */ import leap.settings.ModeSetting;
/*    */ import leap.settings.NumberSetting;
/*    */ import leap.settings.Setting;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Appearance
/*    */   extends Module
/*    */ {
/* 14 */   public static ModeSetting look = new ModeSetting("Look", "Leap", new String[] { "Chill", "Colorful", "Shadow", "Leap", "Custom", "Flat", "Sigma" });
/* 15 */   public static NumberSetting red = new NumberSetting("Red", 0.0D, 0.0D, 255.0D, 1.0D);
/* 16 */   public static NumberSetting green = new NumberSetting("Green", 173.0D, 0.0D, 255.0D, 1.0D);
/* 17 */   public static NumberSetting blue = new NumberSetting("Blue", 255.0D, 0.0D, 255.0D, 1.0D);
/*    */   
/*    */   public Appearance() {
/* 20 */     super("Appearance", 0, Module.Category.RENDER);
/* 21 */     addSettings(new Setting[] { (Setting)look, (Setting)red, (Setting)green, (Setting)blue });
/*    */   }
/*    */   
/*    */   public void onDisable() {
/* 25 */     super.onDisable();
/*    */   }
/*    */   
/*    */   public void onEnable() {
/* 29 */     this.toggled = false;
/* 30 */     super.onEnable();
/*    */   }
/*    */   
/*    */   public void onEvent(Event e) {
/* 34 */     e instanceof leap.events.listeners.EventUpdate;
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\leap\modules\render\Appearance.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */