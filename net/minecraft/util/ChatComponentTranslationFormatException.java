/*    */ package net.minecraft.util;
/*    */ 
/*    */ public class ChatComponentTranslationFormatException
/*    */   extends IllegalArgumentException
/*    */ {
/*    */   private static final String __OBFID = "CL_00001271";
/*    */   
/*    */   public ChatComponentTranslationFormatException(ChatComponentTranslation component, String message) {
/*  9 */     super(String.format("Error parsing: %s: %s", new Object[] { component, message }));
/*    */   }
/*    */ 
/*    */   
/*    */   public ChatComponentTranslationFormatException(ChatComponentTranslation component, int index) {
/* 14 */     super(String.format("Invalid index %d requested for %s", new Object[] { Integer.valueOf(index), component }));
/*    */   }
/*    */ 
/*    */   
/*    */   public ChatComponentTranslationFormatException(ChatComponentTranslation component, Throwable cause) {
/* 19 */     super(String.format("Error while parsing: %s", new Object[] { component }), cause);
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraf\\util\ChatComponentTranslationFormatException.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */