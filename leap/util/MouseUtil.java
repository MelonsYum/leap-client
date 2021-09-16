/*    */ package leap.util;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class MouseUtil
/*    */ {
/*    */   public static boolean isClickable(int mouseX, int mouseY, double addX, double addY) {
/*  9 */     if (mouseX < addX + 80.0D && mouseX > addX - 20.0D && mouseY < addY + 20.0D && mouseY > addY - 20.0D) {
/* 10 */       return true;
/*    */     }
/* 12 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\lea\\util\MouseUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */