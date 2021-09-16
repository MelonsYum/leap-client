/*    */ package leap.modules.combat;
/*    */ 
/*    */ import leap.events.Event;
/*    */ import leap.modules.Module;
/*    */ import leap.settings.NumberSetting;
/*    */ import leap.settings.Setting;
/*    */ import net.minecraft.entity.player.InventoryPlayer;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.network.Packet;
/*    */ import net.minecraft.network.play.client.C08PacketPlayerBlockPlacement;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class AutoFood
/*    */   extends Module
/*    */ {
/*    */   private boolean goOnce = false;
/* 29 */   private int prevSlot = -1;
/*    */   
/*    */   private boolean finished = false;
/* 32 */   public NumberSetting health = new NumberSetting("Health", 10.0D, 1.0D, 20.0D, 1.0D);
/*    */   
/*    */   public AutoFood() {
/* 35 */     super("AutoFood", 0, Module.Category.COMBAT);
/* 36 */     addSettings(new Setting[] { (Setting)this.health });
/*    */   }
/*    */   
/*    */   public void onEnable() {
/* 40 */     super.onEnable();
/*    */   }
/*    */   
/*    */   public void onDisable() {
/* 44 */     super.onDisable();
/*    */   }
/*    */   
/*    */   public void onEvent(Event e) {
/* 48 */     if (e instanceof leap.events.listeners.EventUpdate) {
/*    */       
/* 50 */       if (mc.thePlayer.getHealth() > this.health.getValue()) {
/* 51 */         this.prevSlot = mc.thePlayer.inventory.currentItem;
/*    */       }
/* 53 */       if (mc.thePlayer.getHealth() <= this.health.getValue()) {
/* 54 */         for (int i = 0; i < InventoryPlayer.getHotbarSize(); i++) {
/*    */           
/* 56 */           ItemStack itemStack = mc.thePlayer.inventory.getStackInSlot(i);
/* 57 */           if (itemStack != null && 
/* 58 */             itemStack.getItem() instanceof net.minecraft.item.ItemFood) {
/* 59 */             mc.thePlayer.inventory.currentItem = i;
/*    */           }
/*    */         } 
/*    */         
/* 63 */         if (mc.thePlayer.getHealth() <= this.health.getValue()) {
/* 64 */           mc.getNetHandler().addToSendQueue((Packet)new C08PacketPlayerBlockPlacement(mc.thePlayer.inventory.getCurrentItem()));
/* 65 */           mc.thePlayer.inventory.currentItem = this.prevSlot;
/*    */         } 
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\leap\modules\combat\AutoFood.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */