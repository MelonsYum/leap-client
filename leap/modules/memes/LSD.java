/*    */ package leap.modules.memes;
/*    */ 
/*    */ import java.awt.Color;
/*    */ import leap.events.Event;
/*    */ import leap.modules.Module;
/*    */ import leap.util.RandomUtil;
/*    */ import net.minecraft.client.gui.Gui;
/*    */ import net.minecraft.client.gui.ScaledResolution;
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
/*    */ 
/*    */ public class LSD
/*    */   extends Module
/*    */ {
/*    */   public LSD() {
/* 29 */     super("LSD", 0, Module.Category.MEMES);
/*    */   }
/*    */   
/*    */   public void onEnable() {
/* 33 */     super.onEnable();
/*    */   }
/*    */   
/*    */   public void onDisable() {
/* 37 */     super.onDisable();
/*    */   }
/*    */   
/*    */   public void onEvent(Event e) {
/* 41 */     if (e instanceof leap.events.listeners.EventRenderGUI) {
/* 42 */       Color primColor = new Color(RandomUtil.randomNumber(255.0D, 0.0D), RandomUtil.randomNumber(255.0D, 0.0D), RandomUtil.randomNumber(255.0D, 0.0D), 50);
/* 43 */       Gui.drawRect(0.0D, 0.0D, (new ScaledResolution(mc, mc.displayWidth, mc.displayHeight)).getScaledWidth(), (new ScaledResolution(mc, mc.displayWidth, mc.displayHeight)).getScaledHeight(), primColor.getRGB());
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\leap\modules\memes\LSD.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */