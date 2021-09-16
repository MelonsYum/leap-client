/*    */ package leap.modules.player;
/*    */ 
/*    */ import leap.events.Event;
/*    */ import leap.modules.Module;
/*    */ import leap.util.Timer;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class XCarry
/*    */   extends Module
/*    */ {
/* 14 */   private final Timer time = new Timer();
/*    */   private boolean inInventory;
/*    */   
/*    */   public XCarry() {
/* 18 */     super("XCarry", 0, Module.Category.PLAYER);
/*    */   }
/*    */   
/*    */   public void onEvent(Event e) {
/* 22 */     if (e instanceof leap.events.listeners.EventUpdate)
/*    */     {
/*    */       
/* 25 */       if (e.isPre()) {
/* 26 */         if (mc.currentScreen instanceof net.minecraft.client.gui.inventory.GuiInventory) {
/* 27 */           this.inInventory = true;
/*    */         } else {
/* 29 */           this.inInventory = false;
/*    */         } 
/*    */         
/* 32 */         if (mc.currentScreen instanceof net.minecraft.client.gui.inventory.GuiInventory)
/* 33 */           mc.playerController.updateController(); 
/*    */       } 
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\leap\modules\player\XCarry.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */