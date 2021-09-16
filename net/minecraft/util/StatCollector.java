/*    */ package net.minecraft.util;
/*    */ 
/*    */ public class StatCollector
/*    */ {
/*  5 */   private static StringTranslate localizedName = StringTranslate.getInstance();
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 11 */   private static StringTranslate fallbackTranslator = new StringTranslate();
/*    */ 
/*    */   
/*    */   private static final String __OBFID = "CL_00001211";
/*    */ 
/*    */ 
/*    */   
/*    */   public static String translateToLocal(String p_74838_0_) {
/* 19 */     return localizedName.translateKey(p_74838_0_);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static String translateToLocalFormatted(String p_74837_0_, Object... p_74837_1_) {
/* 27 */     return localizedName.translateKeyFormat(p_74837_0_, p_74837_1_);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static String translateToFallback(String p_150826_0_) {
/* 36 */     return fallbackTranslator.translateKey(p_150826_0_);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static boolean canTranslate(String p_94522_0_) {
/* 44 */     return localizedName.isKeyTranslated(p_94522_0_);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static long getLastTranslationUpdateTimeInMilliseconds() {
/* 52 */     return localizedName.getLastUpdateTimeInMilliseconds();
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraf\\util\StatCollector.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */