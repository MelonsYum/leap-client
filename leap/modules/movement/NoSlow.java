/*    */ package leap.modules.movement;
/*    */ 
/*    */ import leap.events.Event;
/*    */ import leap.events.listeners.EventMotion;
/*    */ import leap.modules.Module;
/*    */ import leap.settings.ModeSetting;
/*    */ import leap.settings.Setting;
/*    */ import leap.util.Timer;
/*    */ import net.minecraft.network.Packet;
/*    */ import net.minecraft.network.play.client.C07PacketPlayerDigging;
/*    */ import net.minecraft.network.play.client.C08PacketPlayerBlockPlacement;
/*    */ import net.minecraft.util.BlockPos;
/*    */ import net.minecraft.util.EnumFacing;
/*    */ 
/*    */ public class NoSlow
/*    */   extends Module {
/*    */   static boolean NoSlowEnabled = false;
/* 18 */   public ModeSetting mode = new ModeSetting("Mode", "Hypixel", new String[] { "NCP", "AAC", "Hypixel", "Basic" });
/* 19 */   Timer timer = new Timer();
/*    */   
/*    */   public NoSlow() {
/* 22 */     super("NoSlow", 0, Module.Category.MOVEMENT);
/* 23 */     addSettings(new Setting[] { (Setting)this.mode });
/*    */   }
/*    */   
/*    */   public static boolean isNoSlowOn() {
/* 27 */     return NoSlowEnabled;
/*    */   }
/*    */   
/*    */   public void onEnable() {
/* 31 */     NoSlowEnabled = true;
/* 32 */     super.onEnable();
/*    */   }
/*    */   
/*    */   public void onDisable() {
/* 36 */     NoSlowEnabled = false;
/* 37 */     super.onDisable();
/*    */   }
/*    */   
/*    */   public void onEvent(Event e) {
/* 41 */     if (e instanceof EventMotion) {
/* 42 */       if (this.mode.getMode() == "NCP" && 
/* 43 */         mc.thePlayer.isBlocking()) {
/* 44 */         double x = mc.thePlayer.posX, y = mc.thePlayer.posY, z = mc.thePlayer.posZ;
/* 45 */         if (e.isPre()) {
/* 46 */           mc.thePlayer.sendQueue.addToSendQueue((Packet)new C07PacketPlayerDigging(C07PacketPlayerDigging.Action.RELEASE_USE_ITEM, BlockPos.ORIGIN, EnumFacing.DOWN));
/* 47 */         } else if (e.isPost()) {
/* 48 */           mc.thePlayer.sendQueue.addToSendQueue((Packet)new C08PacketPlayerBlockPlacement(mc.thePlayer.inventory.getCurrentItem()));
/*    */         } 
/*    */       } 
/*    */ 
/*    */ 
/*    */       
/* 54 */       if (this.mode.getMode() == "Hypixel") {
/* 55 */         if (mc.thePlayer.isBlocking()) {
/* 56 */           if (e.isPre())
/* 57 */           { mc.thePlayer.sendQueue.addToSendQueue((Packet)new C07PacketPlayerDigging(C07PacketPlayerDigging.Action.RELEASE_USE_ITEM, BlockPos.ORIGIN, EnumFacing.DOWN)); }
/* 58 */           else { e.isPost(); }
/*    */         
/*    */         }
/*    */         
/* 62 */         if (this.mode.getMode() == "AAC" && 
/* 63 */           mc.thePlayer.isBlocking()) {
/* 64 */           if (e.isPre()) {
/* 65 */             if (mc.thePlayer.onGround)
/* 66 */               mc.thePlayer.sendQueue.addToSendQueue((Packet)new C07PacketPlayerDigging(C07PacketPlayerDigging.Action.RELEASE_USE_ITEM, BlockPos.ORIGIN, EnumFacing.DOWN)); 
/* 67 */           } else if (e.isPost() && 
/* 68 */             this.timer.hasTimeElapsed(65L, true)) {
/* 69 */             mc.thePlayer.sendQueue.addToSendQueue((Packet)new C08PacketPlayerBlockPlacement(mc.thePlayer.inventory.getCurrentItem()));
/* 70 */             this.timer.reset();
/*    */           } 
/*    */         }
/*    */ 
/*    */         
/* 75 */         if (this.mode.getMode() == "Basic" && 
/* 76 */           mc.thePlayer.isBlocking() && 
/* 77 */           e instanceof EventMotion && 
/* 78 */           e.isPre()) {
/*    */           
/* 80 */           EventMotion event = (EventMotion)e;
/*    */           
/* 82 */           if (mc.thePlayer.onGround)
/* 83 */             e.setMotion(5.0D); 
/*    */         } 
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\leap\modules\movement\NoSlow.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */