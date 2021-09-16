/*    */ package leap.modules.memes;
/*    */ 
/*    */ import java.util.Random;
/*    */ import leap.events.Event;
/*    */ import leap.events.listeners.EventMotion;
/*    */ import leap.modules.Module;
/*    */ import leap.settings.BooleanSetting;
/*    */ import leap.settings.Setting;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Derp
/*    */   extends Module
/*    */ {
/* 15 */   public static BooleanSetting render = new BooleanSetting("Render", false);
/*    */   
/*    */   public Derp() {
/* 18 */     super("Derp", 0, Module.Category.MEMES);
/* 19 */     addSettings(new Setting[] { (Setting)render });
/*    */   }
/*    */   
/*    */   public void onEnable() {
/* 23 */     super.onEnable();
/*    */   }
/*    */   
/*    */   public void onDisable() {
/* 27 */     super.onDisable();
/*    */   }
/*    */   
/*    */   public void onEvent(Event e) {
/* 31 */     if (e instanceof EventMotion) {
/* 32 */       EventMotion event = (EventMotion)e;
/*    */       
/* 34 */       Random random = new Random();
/*    */       
/* 36 */       event.setYaw((random.nextInt(360) - 180));
/* 37 */       event.setPitch((random.nextInt(180) - 90));
/* 38 */       if (render.isEnabled()) {
/* 39 */         mc.thePlayer.renderYawOffset = (random.nextInt(360) - 180);
/* 40 */         mc.thePlayer.rotationYawHead = (random.nextInt(360) - 180);
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\leap\modules\memes\Derp.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */