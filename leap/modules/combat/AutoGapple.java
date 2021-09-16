/*    */ package leap.modules.combat;
/*    */ 
/*    */ import leap.events.Event;
/*    */ import leap.modules.Module;
/*    */ import leap.settings.BooleanSetting;
/*    */ import leap.settings.NumberSetting;
/*    */ import leap.settings.Setting;
/*    */ import net.minecraft.entity.player.InventoryPlayer;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.network.Packet;
/*    */ import net.minecraft.network.play.client.C03PacketPlayer;
/*    */ import net.minecraft.network.play.client.C08PacketPlayerBlockPlacement;
/*    */ import net.minecraft.network.play.client.C09PacketHeldItemChange;
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
/*    */ public class AutoGapple
/*    */   extends Module
/*    */ {
/*    */   private boolean goOnce = false;
/*    */   private int prevSlot;
/*    */   private boolean finished = false;
/* 34 */   int goldSlot = -1;
/*    */ 
/*    */   
/* 37 */   public NumberSetting health = new NumberSetting("Health", 16.0D, 1.0D, 20.0D, 1.0D);
/* 38 */   public BooleanSetting switchF = new BooleanSetting("Switch", true);
/*    */   public AutoGapple() {
/* 40 */     super("AutoGapple", 0, Module.Category.COMBAT);
/* 41 */     addSettings(new Setting[] { (Setting)this.health, (Setting)this.switchF });
/*    */   }
/*    */   
/*    */   public void onEnable() {
/* 45 */     super.onEnable();
/*    */   }
/*    */   
/*    */   public void onDisable() {
/* 49 */     super.onDisable();
/*    */   }
/*    */   
/*    */   public void onEvent(Event e) {
/* 53 */     if (e instanceof leap.events.listeners.EventUpdate) {
/*    */       
/* 55 */       if (mc.thePlayer.onGround && mc.thePlayer.getItemInUseDuration() >= 15 && mc.thePlayer.getItemInUse().getItem() instanceof net.minecraft.item.ItemFood) {
/* 56 */         for (int i = 0; i <= 20; i++) {
/* 57 */           mc.thePlayer.sendQueue.addToSendQueue((Packet)new C03PacketPlayer(true));
/*    */         }
/*    */       }
/*    */       
/* 61 */       if (mc.thePlayer.inventory.getCurrentItem() != null && 
/* 62 */         mc.thePlayer.getHealth() > this.health.getValue() && !(mc.thePlayer.inventory.getCurrentItem().getItem() instanceof net.minecraft.item.ItemAppleGold)) {
/* 63 */         this.prevSlot = mc.thePlayer.inventory.currentItem;
/*    */       }
/*    */       
/* 66 */       if (mc.thePlayer.getHealth() <= this.health.getValue()) {
/* 67 */         for (int i = 0; i < InventoryPlayer.getHotbarSize(); i++) {
/*    */           
/* 69 */           ItemStack itemStack = mc.thePlayer.inventory.getStackInSlot(i);
/* 70 */           if (itemStack != null && 
/* 71 */             itemStack.getItem() instanceof net.minecraft.item.ItemAppleGold) {
/* 72 */             if (this.switchF.isEnabled()) {
/* 73 */               mc.thePlayer.inventory.currentItem = i;
/* 74 */               mc.getNetHandler().addToSendQueue((Packet)new C09PacketHeldItemChange(mc.thePlayer.inventory.currentItem));
/*    */             } else {
/* 76 */               this.goldSlot = i;
/* 77 */               mc.thePlayer.setCurrentItemOrArmor(0, itemStack);
/*    */             } 
/*    */           }
/*    */         } 
/*    */         
/* 82 */         if (this.switchF.isEnabled()) {
/* 83 */           if (mc.thePlayer.inventory.getCurrentItem() != null && 
/* 84 */             mc.thePlayer.inventory.getCurrentItem().getItem() instanceof net.minecraft.item.ItemAppleGold && 
/* 85 */             mc.thePlayer.getHealth() <= this.health.getValue()) {
/* 86 */             sendNetPacket((Packet)new C08PacketPlayerBlockPlacement(mc.thePlayer.inventory.getCurrentItem()));
/*    */           
/*    */           }
/*    */         
/*    */         }
/* 91 */         else if (mc.thePlayer.getHealth() <= this.health.getValue() && this.goldSlot != -1) {
/* 92 */           mc.getNetHandler().addToSendQueue((Packet)new C09PacketHeldItemChange(mc.thePlayer.inventory.currentItem));
/* 93 */           sendNetPacket((Packet)new C08PacketPlayerBlockPlacement(mc.thePlayer.inventory.getStackInSlot(this.goldSlot)));
/*    */         } 
/*    */         
/* 96 */         mc.thePlayer.isEating();
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\leap\modules\combat\AutoGapple.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */