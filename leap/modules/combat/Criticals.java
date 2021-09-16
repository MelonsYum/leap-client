/*    */ package leap.modules.combat;
/*    */ 
/*    */ import leap.Client;
/*    */ import leap.events.Event;
/*    */ import leap.events.listeners.EventMotion;
/*    */ import leap.modules.Module;
/*    */ import leap.util.RandomUtil;
/*    */ import net.minecraft.util.BlockPos;
/*    */ import org.lwjgl.input.Keyboard;
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
/*    */ 
/*    */ public class Criticals
/*    */   extends Module
/*    */ {
/*    */   private float FallStack;
/*    */   
/*    */   public Criticals() {
/* 31 */     super("Criticals", 0, Module.Category.COMBAT);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void onEnable() {
/* 37 */     super.onEnable();
/*    */   }
/*    */   
/*    */   public void onDisable() {
/* 41 */     super.onDisable();
/*    */   }
/*    */   
/*    */   public void onEvent(Event e) {
/* 45 */     if (e instanceof EventMotion) {
/*    */       
/* 47 */       EventMotion event = (EventMotion)e;
/*    */       
/* 49 */       if (mc.thePlayer.motionY < 0.0D && isBlockUnder() && mc.thePlayer.onGround) if (!Client.getModule("Speed").isEnabled()) if (!Client.getModule("Fly").isEnabled() && !Keyboard.isKeyDown(mc.gameSettings.keyBindJump.getKeyCode()) && mc.thePlayer.fallDistance == 0.0F) {
/* 50 */             event.setOnGround(false);
/* 51 */             if (this.FallStack >= 0.0F && this.FallStack < 0.1D && mc.thePlayer.ticksExisted % 2 == 0) {
/* 52 */               double value = 0.0624D + RandomUtil.randomNumber(1.0E-7D, 1.0E-8D);
/* 53 */               this.FallStack = (float)(this.FallStack + value);
/* 54 */               event.setY(mc.thePlayer.posY + value);
/*    */             } else {
/*    */               
/* 57 */               event.setY(mc.thePlayer.posY + 1.0E-8D);
/* 58 */               if (this.FallStack < 0.0F) {
/* 59 */                 this.FallStack = 0.0F;
/* 60 */                 event.setOnGround(true);
/* 61 */                 event.setY(mc.thePlayer.posY);
/*    */               } 
/*    */             } 
/*    */             return;
/*    */           }   
/* 66 */       this.FallStack = -1.0F;
/*    */     } 
/*    */   }
/*    */   
/*    */   private boolean isBlockUnder() {
/* 71 */     for (int i = (int)(mc.thePlayer.posY - 1.0D); i > 0; ) {
/* 72 */       BlockPos pos = new BlockPos(mc.thePlayer.posX, i, mc.thePlayer.posZ);
/* 73 */       if (mc.theWorld.getBlockState(pos).getBlock() instanceof net.minecraft.block.BlockAir) { i--; continue; }
/* 74 */        return true;
/*    */     } 
/* 76 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\leap\modules\combat\Criticals.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */