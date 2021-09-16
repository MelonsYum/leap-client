/*    */ package leap.modules.movement;
/*    */ 
/*    */ import leap.events.Event;
/*    */ import leap.events.listeners.EventUpdate;
/*    */ import leap.modules.Module;
/*    */ import leap.settings.NumberSetting;
/*    */ import leap.settings.Setting;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.network.Packet;
/*    */ import net.minecraft.network.play.client.C03PacketPlayer;
/*    */ 
/*    */ public class VClip extends Module {
/* 13 */   Minecraft mc = Minecraft.getMinecraft();
/*    */   
/* 15 */   public NumberSetting distance = new NumberSetting("Distance", 2.0D, 1.0D, 30.0D, 1.0D);
/*    */   
/*    */   public VClip() {
/* 18 */     super("VClip", 0, Module.Category.MOVEMENT);
/* 19 */     addSettings(new Setting[] { (Setting)this.distance });
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
/* 31 */     if (e instanceof EventUpdate) {
/* 32 */       EventUpdate event = (EventUpdate)e;
/* 33 */       if (event.isPre()) {
/* 34 */         int i; for (i = 0; i < 100; i++) {
/* 35 */           this.mc.thePlayer.sendQueue.addToSendQueue((Packet)new C03PacketPlayer.C04PacketPlayerPosition(this.mc.thePlayer.posX, this.mc.thePlayer.posY - 0.05D, this.mc.thePlayer.posZ, true));
/*    */         }
/* 37 */         this.mc.thePlayer.sendQueue.addToSendQueue((Packet)new C03PacketPlayer.C04PacketPlayerPosition(this.mc.thePlayer.posX, this.mc.thePlayer.posY - this.distance.getValue(), this.mc.thePlayer.posZ, true));
/* 38 */         for (i = 0; i < 81; i++) {
/* 39 */           this.mc.thePlayer.sendQueue.addToSendQueue((Packet)new C03PacketPlayer.C04PacketPlayerPosition(this.mc.thePlayer.posX, this.mc.thePlayer.posY - 0.09D, this.mc.thePlayer.posZ, true));
/*    */         }
/* 41 */         this.mc.thePlayer.setPosition(this.mc.thePlayer.posX, this.mc.thePlayer.posY - this.distance.getValue(), this.mc.thePlayer.posZ);
/* 42 */         toggle();
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\leap\modules\movement\VClip.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */