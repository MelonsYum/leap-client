/*    */ package leap.modules.render;
/*    */ 
/*    */ import leap.events.Event;
/*    */ import leap.modules.Module;
/*    */ import leap.settings.ModeSetting;
/*    */ import leap.settings.NumberSetting;
/*    */ import leap.settings.Setting;
/*    */ 
/*    */ 
/*    */ public class Animations
/*    */   extends Module
/*    */ {
/*    */   float mcpitch;
/*    */   float mcyaw;
/* 15 */   public static NumberSetting y = new NumberSetting("Y", 0.0D, -1.4D, 0.7D, 0.1D);
/* 16 */   public static NumberSetting x = new NumberSetting("X", 0.0D, -0.5D, 0.3D, 0.1D);
/* 17 */   public static NumberSetting blocky = new NumberSetting("Block Y", 0.0D, -0.2D, 0.9D, 0.1D);
/* 18 */   public static NumberSetting blockx = new NumberSetting("Block X", 0.0D, -5.0D, 5.0D, 0.1D);
/* 19 */   public static ModeSetting block = new ModeSetting("BlockHit", "Leap", new String[] { "Basic", "Leap", "Spin", "Sigma", "Nudge", "Avatar", "Swing", "Swang", "Swong", "Swank", "Swaing" });
/* 20 */   public static NumberSetting blockscale = new NumberSetting("Block Scale", 0.0D, -10.0D, 0.0D, 1.0D);
/* 21 */   public static NumberSetting scale = new NumberSetting("Scale", 0.0D, -10.0D, 0.0D, 1.0D);
/*    */   
/*    */   public Animations() {
/* 24 */     super("Animations", 0, Module.Category.RENDER);
/* 25 */     addSettings(new Setting[] { (Setting)x, (Setting)y, (Setting)blockx, (Setting)blocky, (Setting)block, (Setting)blockscale, (Setting)scale });
/*    */   }
/*    */   
/*    */   public void onEnable() {
/* 29 */     super.onEnable();
/*    */   }
/*    */   
/*    */   public void onDisable() {
/* 33 */     mc.thePlayer.setSprinting(mc.gameSettings.keyBindSprint.getIsKeyPressed());
/* 34 */     super.onDisable();
/*    */   }
/*    */   
/*    */   public void onEvent(Event e) {
/* 38 */     if (e instanceof leap.events.listeners.EventUpdate) {
/* 39 */       e.isPre();
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public static String getMode() {
/* 45 */     return block.getMode();
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\leap\modules\render\Animations.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */