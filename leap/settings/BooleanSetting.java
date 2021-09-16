/*    */ package leap.settings;
/*    */ 
/*    */ public class BooleanSetting
/*    */   extends Setting {
/*    */   public boolean enabled;
/*    */   
/*    */   public BooleanSetting(String name, boolean enabled) {
/*  8 */     this.name = name;
/*  9 */     this.enabled = enabled;
/*    */   }
/*    */   
/*    */   public boolean isEnabled() {
/* 13 */     return this.enabled;
/*    */   }
/*    */   
/*    */   public void setEnabled(boolean enabled) {
/* 17 */     this.enabled = enabled;
/*    */   }
/*    */   
/*    */   public void toggle() {
/* 21 */     this.enabled = !this.enabled;
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\leap\settings\BooleanSetting.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */