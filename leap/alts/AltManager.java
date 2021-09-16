/*    */ package leap.alts;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class AltManager
/*    */ {
/*    */   public static Alt lastAlt;
/* 12 */   public static ArrayList<Alt> registry = new ArrayList<>();
/*    */ 
/*    */   
/*    */   public static ArrayList<Alt> getRegistry() {
/* 16 */     return registry;
/*    */   }
/*    */   
/*    */   public void setLastAlt(Alt alt2) {
/* 20 */     lastAlt = alt2;
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\leap\alts\AltManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */