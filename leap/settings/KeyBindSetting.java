/*    */ package leap.settings;
/*    */ 
/*    */ public class KeyBindSetting
/*    */   extends Setting {
/*    */   public int code;
/*    */   
/*    */   public KeyBindSetting(int code) {
/*  8 */     this.name = "Keybind";
/*  9 */     this.code = code;
/*    */   }
/*    */   
/*    */   public int getKeyCode() {
/* 13 */     return this.code;
/*    */   }
/*    */   
/*    */   public void setKeyCode(int code) {
/* 17 */     this.code = code;
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\leap\settings\KeyBindSetting.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */