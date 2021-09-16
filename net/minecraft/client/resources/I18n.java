/*    */ package net.minecraft.client.resources;
/*    */ 
/*    */ import java.util.Map;
/*    */ 
/*    */ 
/*    */ public class I18n
/*    */ {
/*    */   private static Locale i18nLocale;
/*    */   
/*    */   static void setLocale(Locale p_135051_0_) {
/* 11 */     i18nLocale = p_135051_0_;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static String format(String p_135052_0_, Object... p_135052_1_) {
/* 19 */     return i18nLocale.formatMessage(p_135052_0_, p_135052_1_);
/*    */   }
/*    */ 
/*    */   
/*    */   public static Map getLocaleProperties() {
/* 24 */     return i18nLocale.field_135032_a;
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\resources\I18n.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */