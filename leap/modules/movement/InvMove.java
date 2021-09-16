/*    */ package leap.modules.movement;
/*    */ 
/*    */ import leap.events.Event;
/*    */ import leap.modules.Module;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import org.lwjgl.input.Keyboard;
/*    */ 
/*    */ public class InvMove extends Module {
/*    */   Minecraft mc;
/*    */   
/*    */   public InvMove() {
/* 12 */     super("InvMove", 0, Module.Category.MOVEMENT);
/*    */ 
/*    */     
/* 15 */     this.mc = Minecraft.getMinecraft();
/*    */   }
/*    */   public void onDisable() {
/* 18 */     super.onDisable();
/*    */   }
/*    */   
/*    */   public void onEnable() {
/* 22 */     super.onEnable();
/*    */   }
/*    */   
/*    */   public void onEvent(Event e) {
/* 26 */     if (e instanceof leap.events.listeners.EventMotion && 
/* 27 */       this.mc.currentScreen != null && !(this.mc.currentScreen instanceof net.minecraft.client.gui.GuiChat)) {
/* 28 */       this.mc.gameSettings.keyBindForward.pressed = Keyboard.isKeyDown(this.mc.gameSettings.keyBindForward.getKeyCode());
/* 29 */       this.mc.gameSettings.keyBindBack.pressed = Keyboard.isKeyDown(this.mc.gameSettings.keyBindBack.getKeyCode());
/* 30 */       this.mc.gameSettings.keyBindLeft.pressed = Keyboard.isKeyDown(this.mc.gameSettings.keyBindLeft.getKeyCode());
/* 31 */       this.mc.gameSettings.keyBindRight.pressed = Keyboard.isKeyDown(this.mc.gameSettings.keyBindRight.getKeyCode());
/* 32 */       this.mc.gameSettings.keyBindJump.pressed = Keyboard.isKeyDown(this.mc.gameSettings.keyBindJump.getKeyCode());
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\leap\modules\movement\InvMove.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */