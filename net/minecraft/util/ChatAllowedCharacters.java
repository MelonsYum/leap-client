/*    */ package net.minecraft.util;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ChatAllowedCharacters
/*    */ {
/*  8 */   public static final char[] allowedCharactersArray = new char[] { '/', '\n', '\r', '\t', '\f', '`', '?', '*', '\\', '<', '>', '|', '"', ':' };
/*    */   
/*    */   private static final String __OBFID = "CL_00001606";
/*    */   
/*    */   public static boolean isAllowedCharacter(char character) {
/* 13 */     return (character != '§' && character >= ' ' && character != '');
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static String filterAllowedCharacters(String input) {
/* 21 */     StringBuilder var1 = new StringBuilder();
/* 22 */     char[] var2 = input.toCharArray();
/* 23 */     int var3 = var2.length;
/*    */     
/* 25 */     for (int var4 = 0; var4 < var3; var4++) {
/*    */       
/* 27 */       char var5 = var2[var4];
/*    */       
/* 29 */       if (isAllowedCharacter(var5))
/*    */       {
/* 31 */         var1.append(var5);
/*    */       }
/*    */     } 
/*    */     
/* 35 */     return var1.toString();
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraf\\util\ChatAllowedCharacters.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */