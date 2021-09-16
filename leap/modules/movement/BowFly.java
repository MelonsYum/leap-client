/*    */ package leap.modules.movement;
/*    */ 
/*    */ import leap.events.Event;
/*    */ import leap.modules.Module;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.network.Packet;
/*    */ import net.minecraft.network.play.client.C07PacketPlayerDigging;
/*    */ import net.minecraft.network.play.client.C08PacketPlayerBlockPlacement;
/*    */ import net.minecraft.util.BlockPos;
/*    */ import net.minecraft.util.EnumFacing;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BowFly
/*    */   extends Module
/*    */ {
/*    */   boolean enable;
/*    */   int bow;
/* 20 */   public static int yprocents = 160;
/* 21 */   public static int xzprocents = 190;
/*    */   public static float ymultiplier;
/*    */   public static float xzmulitplier;
/*    */   public static boolean boost = true;
/*    */   
/*    */   public BowFly() {
/* 27 */     super("BowFly", 0, Module.Category.MOVEMENT);
/* 28 */     addSettings(new leap.settings.Setting[0]);
/*    */   }
/*    */   
/*    */   public void onEvent(Event e) {
/* 32 */     if (e instanceof leap.events.listeners.EventUpdate) {
/* 33 */       ymultiplier = (yprocents / 100);
/* 34 */       if (e.isPre() && this.enable) {
/* 35 */         if (boost) {
/* 36 */           mc.timer.timerSpeed = 0.2F;
/*    */         }
/*    */         
/* 39 */         this.enable = false;
/*    */       } 
/* 41 */       if (e.isPost() && !this.enable) {
/* 42 */         mc.thePlayer.sendQueue.addToSendQueue((Packet)new C07PacketPlayerDigging(C07PacketPlayerDigging.Action.RELEASE_USE_ITEM, new BlockPos(mc.thePlayer.posX, mc.thePlayer.posY, mc.thePlayer.posZ), EnumFacing.DOWN));
/* 43 */         mc.timer.timerSpeed = 1.0F;
/* 44 */         this.enable = true;
/*    */       } 
/* 46 */       if (e.isPost() && !doesMove()) {
/* 47 */         mc.thePlayer.motionX = 0.0D;
/* 48 */         mc.thePlayer.motionZ = 0.0D;
/*    */       } 
/* 50 */       if (e.isPost() && !boost) {
/* 51 */         if (mc.thePlayer.motionY <= 0.0D) {
/* 52 */           mc.timer.timerSpeed = 0.2F;
/*    */         } else {
/* 54 */           mc.timer.timerSpeed = 1.0F;
/*    */         } 
/*    */       }
/* 57 */       if (e.isPost()) {
/* 58 */         ItemStack stack = mc.thePlayer.getCurrentEquippedItem();
/* 59 */         if (stack != null && stack.getItem() instanceof net.minecraft.item.ItemBow) {
/* 60 */           this.bow++;
/* 61 */           if (this.bow >= 4) {
/* 62 */             mc.thePlayer.sendQueue.addToSendQueue((Packet)new C07PacketPlayerDigging(C07PacketPlayerDigging.Action.RELEASE_USE_ITEM, new BlockPos(mc.thePlayer.posX, mc.thePlayer.posY, mc.thePlayer.posZ), EnumFacing.DOWN));
/* 63 */             this.bow = 0;
/*    */           } else {
/* 65 */             mc.thePlayer.sendQueue.addToSendQueue((Packet)new C08PacketPlayerBlockPlacement(mc.thePlayer.inventory.getCurrentItem()));
/*    */           } 
/*    */         } 
/*    */       } 
/* 69 */       if (e.isPost()) {
/* 70 */         if (mc.thePlayer.rotationPitch == -90.0F) {
/* 71 */           xzmulitplier = 0.0F;
/*    */         } else {
/* 73 */           xzmulitplier = (xzprocents / 100);
/*    */         } 
/*    */       } else {
/* 76 */         xzmulitplier = (xzprocents / 100);
/*    */       } 
/*    */     } 
/*    */   }
/*    */   
/*    */   public void onEnable() {
/* 82 */     super.onEnable();
/*    */   }
/*    */   
/*    */   public void onDisable() {
/* 86 */     super.onDisable();
/*    */   }
/*    */   
/*    */   public static boolean doesMove() {
/* 90 */     return !(!mc.gameSettings.keyBindForward.pressed && !mc.gameSettings.keyBindRight.pressed && !mc.gameSettings.keyBindBack.pressed && !mc.gameSettings.keyBindLeft.pressed);
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\leap\modules\movement\BowFly.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */