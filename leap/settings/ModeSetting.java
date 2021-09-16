/*    */ package leap.settings;
/*    */ 
/*    */ import java.util.Arrays;
/*    */ import java.util.List;
/*    */ 
/*    */ public class ModeSetting
/*    */   extends Setting {
/*    */   public int index;
/*    */   public List<String> modes;
/*    */   
/*    */   public ModeSetting(String name, String defaultMode, String... modes) {
/* 12 */     this.name = name;
/* 13 */     this.modes = Arrays.asList(modes);
/* 14 */     this.index = this.modes.indexOf(defaultMode);
/*    */   }
/*    */   
/*    */   public String getMode() {
/* 18 */     return this.modes.get(this.index);
/*    */   }
/*    */   
/*    */   public boolean is(String mode) {
/* 22 */     return (this.index == this.modes.indexOf(mode));
/*    */   }
/*    */   
/*    */   public void cycle() {
/* 26 */     if (this.index < this.modes.size() - 1) {
/* 27 */       this.index++;
/*    */     } else {
/* 29 */       this.index = 0;
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\leap\settings\ModeSetting.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */