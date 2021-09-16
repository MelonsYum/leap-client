/*    */ package leap.modules.render;
/*    */ 
/*    */ import leap.events.Event;
/*    */ import leap.events.listeners.EventReceivePacket;
/*    */ import leap.modules.Module;
/*    */ import leap.settings.NumberSetting;
/*    */ import leap.settings.Setting;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Ambiance
/*    */   extends Module
/*    */ {
/* 15 */   public static NumberSetting tod = new NumberSetting("Time", 0.0D, 0.0D, 23999.0D, 1.0D);
/*    */   
/*    */   public Ambiance() {
/* 18 */     super("Ambiance", 0, Module.Category.RENDER);
/* 19 */     addSettings(new Setting[] { (Setting)tod });
/*    */   }
/*    */   
/*    */   public void onEvent(Event e) {
/* 23 */     if (e instanceof leap.events.listeners.EventUpdate && 
/* 24 */       e.isPre()) {
/* 25 */       mc.theWorld.getWorldInfo().setWorldTime((long)tod.getValue());
/*    */     }
/*    */ 
/*    */     
/* 29 */     if (e instanceof EventReceivePacket) {
/*    */       
/* 31 */       EventReceivePacket event = (EventReceivePacket)e;
/*    */       
/* 33 */       if (event.getPacket() instanceof net.minecraft.network.play.server.S03PacketTimeUpdate)
/*    */       {
/* 35 */         event.setCancelled(true);
/*    */       }
/*    */     } 
/*    */   }
/*    */   
/*    */   public void onEnable() {
/* 41 */     super.onEnable();
/*    */   }
/*    */   
/*    */   public void onDisable() {
/* 45 */     super.onDisable();
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\leap\modules\render\Ambiance.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */