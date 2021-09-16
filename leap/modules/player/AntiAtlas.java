/*    */ package leap.modules.player;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import leap.events.Event;
/*    */ import leap.modules.Module;
/*    */ import leap.util.Timer;
/*    */ import net.minecraft.entity.Entity;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class AntiAtlas
/*    */   extends Module
/*    */ {
/*    */   private int state;
/*    */   double fall;
/* 18 */   List<String> reported = new ArrayList<>();
/* 19 */   Timer timer = new Timer();
/*    */   
/*    */   public AntiAtlas() {
/* 22 */     super("AntiAtlas", 0, Module.Category.PLAYER);
/*    */   }
/*    */ 
/*    */   
/*    */   public void onEvent(Event e) {
/* 27 */     if (e instanceof leap.events.listeners.EventUpdate) {
/* 28 */       for (Object entity : mc.theWorld.loadedEntityList) {
/* 29 */         if (entity != null && 
/* 30 */           (Entity)entity instanceof net.minecraft.entity.player.EntityPlayer && 
/* 31 */           entity != mc.thePlayer && 
/* 32 */           this.reported != null && 
/* 33 */           !this.reported.contains(" " + ((Entity)entity).getDisplayName() + " ") && 
/* 34 */           this.timer.hasTimeElapsed(500L, true)) {
/* 35 */           mc.thePlayer.sendChatMessage("/report " + ((Entity)entity).getName() + " fly");
/* 36 */           this.reported.add(" " + ((Entity)entity).getDisplayName() + " ");
/*    */         } 
/*    */       } 
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void onEnable() {
/* 48 */     super.onEnable();
/*    */   }
/*    */   
/*    */   public void onDisable() {
/* 52 */     super.onDisable();
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\leap\modules\player\AntiAtlas.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */