/*    */ package leap.modules.render;
/*    */ 
/*    */ import leap.events.Event;
/*    */ import leap.modules.Module;
/*    */ import net.minecraft.client.entity.AbstractClientPlayer;
/*    */ import optifine.CapeUtils;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Capes
/*    */   extends Module
/*    */ {
/*    */   public Capes() {
/* 15 */     super("Capes", 0, Module.Category.RENDER);
/*    */   }
/*    */   
/*    */   public void onDisable() {
/* 19 */     super.onDisable();
/*    */   }
/*    */   
/*    */   public void onEnable() {
/* 23 */     CapeUtils.downloadCape((AbstractClientPlayer)mc.thePlayer);
/* 24 */     super.onEnable();
/*    */   }
/*    */   
/*    */   public void onEvent(Event e) {
/* 28 */     e instanceof leap.events.listeners.EventMotion;
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\leap\modules\render\Capes.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */