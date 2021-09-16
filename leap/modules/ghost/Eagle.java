/*    */ package leap.modules.ghost;
/*    */ 
/*    */ import leap.events.Event;
/*    */ import leap.modules.Module;
/*    */ import net.minecraft.init.Blocks;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.network.Packet;
/*    */ import net.minecraft.network.play.client.C0APacketAnimation;
/*    */ import net.minecraft.util.BlockPos;
/*    */ import org.lwjgl.input.Mouse;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Eagle
/*    */   extends Module
/*    */ {
/*    */   public Eagle() {
/* 21 */     super("Eagle", 0, Module.Category.GHOST);
/*    */   }
/*    */   
/*    */   public void onEnable() {
/* 25 */     super.onEnable();
/*    */   }
/*    */   
/*    */   public void onDisable() {
/* 29 */     mc.gameSettings.keyBindSneak.pressed = false;
/* 30 */     super.onDisable();
/*    */   }
/*    */   
/*    */   public void onEvent(Event e) {
/* 34 */     if (e instanceof leap.events.listeners.EventUpdate) {
/* 35 */       ItemStack i = mc.thePlayer.getCurrentEquippedItem();
/* 36 */       BlockPos bP = new BlockPos(mc.thePlayer.posX, mc.thePlayer.posY - 1.0D, mc.thePlayer.posZ);
/* 37 */       if (i != null && 
/* 38 */         i.getItem() instanceof net.minecraft.item.ItemBlock) {
/* 39 */         mc.gameSettings.keyBindSneak.pressed = false;
/* 40 */         if (mc.theWorld.getBlockState(bP).getBlock() == Blocks.air) {
/* 41 */           mc.gameSettings.keyBindSneak.pressed = true;
/* 42 */           Mouse.isButtonDown(1);
/* 43 */           if (e.isPost())
/* 44 */             mc.getNetHandler().addToSendQueue((Packet)new C0APacketAnimation()); 
/*    */         } 
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\leap\modules\ghost\Eagle.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */