/*    */ package leap.modules.player;
/*    */ 
/*    */ import leap.events.Event;
/*    */ import leap.modules.Module;
/*    */ import net.minecraft.potion.Potion;
/*    */ import net.minecraft.potion.PotionEffect;
/*    */ 
/*    */ 
/*    */ public class SpeedMine
/*    */   extends Module
/*    */ {
/*    */   public SpeedMine() {
/* 13 */     super("SpeedMine", 0, Module.Category.PLAYER);
/*    */   }
/*    */   
/*    */   public void onDisable() {
/* 17 */     mc.thePlayer.removePotionEffect(Potion.digSpeed.getId());
/* 18 */     super.onDisable();
/*    */   }
/*    */   
/*    */   public void onEnable() {
/* 22 */     super.onEnable();
/*    */   }
/*    */   
/*    */   public void onEvent(Event e) {
/* 26 */     if (e instanceof leap.events.listeners.EventUpdate) {
/* 27 */       if (mc.thePlayer == null)
/* 28 */         return;  if (e.isPre()) {
/* 29 */         mc.playerController.blockHitDelay = 0;
/* 30 */         boolean item = (mc.thePlayer.getCurrentEquippedItem() == null);
/* 31 */         mc.thePlayer.addPotionEffect(new PotionEffect(Potion.digSpeed.getId(), 100, item ? 1 : 0));
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\leap\modules\player\SpeedMine.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */