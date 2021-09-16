/*    */ package leap.modules.render;
/*    */ 
/*    */ import leap.modules.Module;
/*    */ 
/*    */ public class FullBright
/*    */   extends Module
/*    */ {
/*    */   public FullBright() {
/*  9 */     super("FullBright", 0, Module.Category.RENDER);
/*    */   }
/*    */   
/*    */   public void onEnable() {
/* 13 */     mc.gameSettings.gammaSetting = 100.0F;
/*    */     
/* 15 */     super.onEnable();
/*    */   }
/*    */   
/*    */   public void onDisable() {
/* 19 */     mc.gameSettings.gammaSetting = 1.0F;
/*    */     
/* 21 */     super.onDisable();
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\leap\modules\render\FullBright.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */