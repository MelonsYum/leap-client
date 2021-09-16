/*    */ package leap.modules.render;
/*    */ 
/*    */ import leap.events.Event;
/*    */ import leap.modules.Module;
/*    */ import leap.settings.BooleanSetting;
/*    */ import leap.settings.NumberSetting;
/*    */ import leap.settings.Setting;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Scoreboard
/*    */   extends Module
/*    */ {
/* 15 */   public static BooleanSetting show = new BooleanSetting("Show Scoreboard", true);
/*    */   
/* 17 */   public static BooleanSetting clip = new BooleanSetting("Clip ArrayList", true);
/*    */   
/* 19 */   public static NumberSetting scoreboardX = new NumberSetting("Scoreboard X", 2.0D, -100.0D, 50.0D, 1.0D);
/* 20 */   public static NumberSetting scoreboardY = new NumberSetting("Scoreboard Y", 10.0D, -100.0D, 150.0D, 1.0D);
/*    */ 
/*    */ 
/*    */   
/*    */   public Scoreboard() {
/* 25 */     super("Scoreboard", 0, Module.Category.RENDER);
/* 26 */     addSettings(new Setting[] { (Setting)show });
/*    */   }
/*    */   
/*    */   public void onDisable() {
/* 30 */     super.onDisable();
/*    */   }
/*    */   
/*    */   public void onEnable() {
/* 34 */     super.onEnable();
/*    */   }
/*    */   
/*    */   public void onStandby() {
/* 38 */     if (!this.settings.contains(clip) && show.enabled) {
/* 39 */       addSettings(new Setting[] { (Setting)clip, (Setting)scoreboardX });
/* 40 */     } else if (!show.enabled && this.settings.contains(clip)) {
/* 41 */       removeSettings(new Setting[] { (Setting)clip, (Setting)scoreboardX });
/*    */     } 
/*    */     
/* 44 */     if (!this.settings.contains(scoreboardY) && !clip.enabled) {
/* 45 */       addSettings(new Setting[] { (Setting)scoreboardY });
/* 46 */     } else if (clip.enabled && this.settings.contains(scoreboardY)) {
/* 47 */       removeSettings(new Setting[] { (Setting)scoreboardY });
/*    */     } 
/*    */   }
/*    */   
/*    */   public void onEvent(Event e) {
/* 52 */     if (e instanceof leap.events.listeners.EventUpdate) {
/* 53 */       if (!this.settings.contains(clip) && show.enabled) {
/* 54 */         addSettings(new Setting[] { (Setting)clip, (Setting)scoreboardX, (Setting)scoreboardY });
/* 55 */       } else if (!show.enabled && this.settings.contains(clip)) {
/* 56 */         removeSettings(new Setting[] { (Setting)clip, (Setting)scoreboardX, (Setting)scoreboardY });
/*    */       } 
/*    */       
/* 59 */       if (!this.settings.contains(scoreboardY) && !clip.enabled) {
/* 60 */         addSettings(new Setting[] { (Setting)scoreboardY });
/* 61 */       } else if (clip.enabled && this.settings.contains(scoreboardY)) {
/* 62 */         removeSettings(new Setting[] { (Setting)scoreboardY });
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\leap\modules\render\Scoreboard.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */