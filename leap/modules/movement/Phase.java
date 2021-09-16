/*    */ package leap.modules.movement;
/*    */ 
/*    */ import leap.events.Event;
/*    */ import leap.events.listeners.EventReceivePacket;
/*    */ import leap.events.listeners.EventUpdate;
/*    */ import leap.modules.Module;
/*    */ import leap.settings.NumberSetting;
/*    */ import leap.settings.Setting;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.network.Packet;
/*    */ import net.minecraft.network.play.client.C03PacketPlayer;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Phase
/*    */   extends Module
/*    */ {
/* 20 */   public NumberSetting distance = new NumberSetting("Distance", -3.0D, -10.0D, 10.0D, 1.0D);
/*    */   
/*    */   public Phase() {
/* 23 */     super("Phase", 0, Module.Category.MOVEMENT);
/* 24 */     addSettings(new Setting[] { (Setting)this.distance });
/*    */   }
/*    */   
/*    */   public void onEvent(Event e) {
/* 28 */     if (e instanceof EventUpdate && 
/* 29 */       e instanceof EventUpdate) {
/* 30 */       EventUpdate event = (EventUpdate)e;
/* 31 */       mc.thePlayer.sendQueue.addToSendQueue((Packet)new C03PacketPlayer.C06PacketPlayerPosLook(mc.thePlayer.posX, mc.thePlayer.posY + this.distance.getValue(), mc.thePlayer.posZ, mc.thePlayer.rotationYaw, mc.thePlayer.rotationPitch, (mc.thePlayer.ticksExisted % 2 == 0)));
/* 32 */       toggle();
/*    */     } 
/*    */     
/* 35 */     if (e instanceof EventReceivePacket) {
/*    */       
/* 37 */       EventReceivePacket event = (EventReceivePacket)e;
/* 38 */       if (isEnabled()) {
/* 39 */         Packet packet = event.getPacket();
/* 40 */         if ((Minecraft.getMinecraft()).theWorld == null || Minecraft.getMinecraft().isSingleplayer()) {
/*    */           return;
/*    */         }
/*    */         
/* 44 */         if (packet instanceof net.minecraft.network.play.client.C01PacketChatMessage || packet instanceof net.minecraft.network.play.client.C0FPacketConfirmTransaction || packet instanceof net.minecraft.network.play.client.C00PacketKeepAlive || packet instanceof net.minecraft.network.play.client.C14PacketTabComplete || packet instanceof net.minecraft.network.play.client.C16PacketClientStatus) {
/*    */           return;
/*    */         }
/* 47 */         event.setCancelled(true);
/*    */       } 
/*    */     } 
/*    */   }
/*    */   
/*    */   public void onEnable() {
/* 53 */     super.onEnable();
/*    */   }
/*    */   
/*    */   public void onDisable() {
/* 57 */     super.onDisable();
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\leap\modules\movement\Phase.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */