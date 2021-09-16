/*    */ package net.minecraft.util;
/*    */ 
/*    */ import java.util.regex.Pattern;
/*    */ 
/*    */ public class StringUtils
/*    */ {
/*  7 */   private static final Pattern patternControlCode = Pattern.compile("(?i)\\u00A7[0-9A-FK-OR]");
/*    */ 
/*    */   
/*    */   private static final String __OBFID = "CL_00001501";
/*    */ 
/*    */ 
/*    */   
/*    */   public static String ticksToElapsedTime(int p_76337_0_) {
/* 15 */     int var1 = p_76337_0_ / 20;
/* 16 */     int var2 = var1 / 60;
/* 17 */     var1 %= 60;
/* 18 */     return (var1 < 10) ? (String.valueOf(var2) + ":0" + var1) : (String.valueOf(var2) + ":" + var1);
/*    */   }
/*    */ 
/*    */   
/*    */   public static String stripControlCodes(String p_76338_0_) {
/* 23 */     return patternControlCode.matcher(p_76338_0_).replaceAll("");
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static boolean isNullOrEmpty(String p_151246_0_) {
/* 31 */     return org.apache.commons.lang3.StringUtils.isEmpty(p_151246_0_);
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraf\\util\StringUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */