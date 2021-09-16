/*    */ package leap.modules.player;
/*    */ 
/*    */ import leap.events.Event;
/*    */ import leap.events.listeners.EventMotion;
/*    */ import leap.modules.Module;
/*    */ import leap.settings.ModeSetting;
/*    */ import leap.settings.Setting;
/*    */ import net.minecraft.network.Packet;
/*    */ import net.minecraft.network.play.client.C03PacketPlayer;
/*    */ 
/*    */ public class NoFall
/*    */   extends Module
/*    */ {
/*    */   private int state;
/*    */   double fall;
/* 16 */   public static ModeSetting mode = new ModeSetting("Mode", "Packet", new String[] { "Packet", "AAC", "NCP", "Hypixel" });
/*    */   
/*    */   public NoFall() {
/* 19 */     super("NoFall", 0, Module.Category.PLAYER);
/* 20 */     addSettings(new Setting[] { (Setting)mode });
/*    */   }
/*    */   
/*    */   public void onEvent(Event e) {
/* 24 */     if (e instanceof EventMotion && e.isPre()) {
/*    */       
/* 26 */       EventMotion event = (EventMotion)e;
/*    */       
/* 28 */       if (mode.getMode() == "Packet" && 
/* 29 */         mc.thePlayer.fallDistance > 2.0F) {
/* 30 */         sendPacket((Packet)new C03PacketPlayer(true));
/*    */       }
/*    */       
/* 33 */       if (mode.getMode() == "NCP" && 
/* 34 */         mc.thePlayer.fallDistance > 2.0F) {
/* 35 */         sendNetPacket((Packet)new C03PacketPlayer.C06PacketPlayerPosLook(mc.thePlayer.posX, mc.thePlayer.posY + 0.1D, mc.thePlayer.posZ, mc.thePlayer.rotationYaw, mc.thePlayer.rotationPitch, (mc.thePlayer.ticksExisted % 2 == 0)));
/*    */       }
/*    */ 
/*    */       
/* 39 */       if (mode.getMode() == "AAC") {
/* 40 */         if (mc.thePlayer.fallDistance > 2.0F) {
/* 41 */           mc.getNetHandler().addToSendQueue((Packet)new C03PacketPlayer(true));
/* 42 */           this.state = 2;
/* 43 */         } else if (this.state == 2 && mc.thePlayer.fallDistance < 2.0F) {
/* 44 */           mc.thePlayer.motionY = 0.1D;
/* 45 */           this.state = 3;
/*    */           
/*    */           return;
/*    */         } 
/* 49 */         switch (this.state) {
/*    */           case 3:
/* 51 */             mc.thePlayer.motionY = 0.1D;
/* 52 */             this.state = 4;
/*    */             break;
/*    */           case 4:
/* 55 */             mc.thePlayer.motionY = 0.1D;
/* 56 */             this.state = 5;
/*    */             break;
/*    */           case 5:
/* 59 */             mc.thePlayer.motionY = 0.1D;
/* 60 */             this.state = 1;
/*    */             break;
/*    */         } 
/*    */       } 
/*    */     } 
/*    */   }
/*    */   
/*    */   public void onEnable() {
/* 68 */     super.onEnable();
/*    */   }
/*    */   
/*    */   public void onDisable() {
/* 72 */     super.onDisable();
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\leap\modules\player\NoFall.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */