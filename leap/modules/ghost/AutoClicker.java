/*    */ package leap.modules.ghost;
/*    */ 
/*    */ import leap.events.Event;
/*    */ import leap.modules.Module;
/*    */ import leap.settings.BooleanSetting;
/*    */ import leap.settings.NumberSetting;
/*    */ import leap.settings.Setting;
/*    */ import leap.util.RandomUtil;
/*    */ import leap.util.Timer;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import org.lwjgl.input.Mouse;
/*    */ 
/*    */ public class AutoClicker
/*    */   extends Module {
/* 15 */   public NumberSetting cpss = new NumberSetting("CPS", 8.0D, 1.0D, 30.0D, 1.0D);
/* 16 */   public NumberSetting min = new NumberSetting("Min", 7.0D, 1.0D, 30.0D, 1.0D);
/* 17 */   public NumberSetting max = new NumberSetting("Max", 10.0D, 1.0D, 30.0D, 1.0D);
/* 18 */   public BooleanSetting randoms = new BooleanSetting("Random", true);
/* 19 */   public BooleanSetting mouse = new BooleanSetting("Mouse", true);
/* 20 */   Timer timer = new Timer();
/*    */   
/*    */   public AutoClicker() {
/* 23 */     super("AutoClicker", 0, Module.Category.GHOST);
/* 24 */     addSettings(new Setting[] { (Setting)this.cpss, (Setting)this.randoms, (Setting)this.min, (Setting)this.max, (Setting)this.mouse });
/*    */   }
/*    */   
/*    */   public void onEnable() {
/* 28 */     super.onEnable();
/*    */   }
/*    */   
/*    */   public void onDisable() {
/* 32 */     super.onDisable();
/*    */   }
/*    */   
/*    */   public void onEvent(Event e) {
/* 36 */     if (e instanceof leap.events.listeners.EventMotion && 
/* 37 */       e.isPre()) {
/* 38 */       if (this.mouse.isEnabled() && !Mouse.isButtonDown(0)) {
/*    */         return;
/*    */       }
/* 41 */       int cps = Double.valueOf(this.cpss.getValue()).intValue();
/* 42 */       int minran = Double.valueOf(this.min.getValue()).intValue();
/* 43 */       int maxran = Double.valueOf(this.max.getValue()).intValue();
/* 44 */       boolean random = Boolean.valueOf(this.randoms.isEnabled()).booleanValue();
/* 45 */       int rand = random ? RandomUtil.randomNumber(minran, maxran) : 0;
/* 46 */       int cpsdel = (cps + rand <= 0) ? 1 : (cps + rand);
/* 47 */       long del = (1000 / cpsdel);
/* 48 */       if (this.timer.hasTimeElapsed(del, random)) {
/* 49 */         mc.playerController.onStoppedUsingItem((EntityPlayer)mc.thePlayer);
/* 50 */         mc.thePlayer.swingItem();
/* 51 */         mc.clickMouse();
/* 52 */         this.timer.reset();
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\leap\modules\ghost\AutoClicker.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */