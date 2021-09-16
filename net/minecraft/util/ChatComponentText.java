/*    */ package net.minecraft.util;
/*    */ 
/*    */ import java.util.Iterator;
/*    */ 
/*    */ public class ChatComponentText
/*    */   extends ChatComponentStyle
/*    */ {
/*    */   private final String text;
/*    */   private static final String __OBFID = "CL_00001269";
/*    */   
/*    */   public ChatComponentText(String msg) {
/* 12 */     this.text = msg;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String getChatComponentText_TextValue() {
/* 21 */     return this.text;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String getUnformattedTextForChat() {
/* 30 */     return this.text;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public ChatComponentText createCopy() {
/* 38 */     ChatComponentText var1 = new ChatComponentText(this.text);
/* 39 */     var1.setChatStyle(getChatStyle().createShallowCopy());
/* 40 */     Iterator<IChatComponent> var2 = getSiblings().iterator();
/*    */     
/* 42 */     while (var2.hasNext()) {
/*    */       
/* 44 */       IChatComponent var3 = var2.next();
/* 45 */       var1.appendSibling(var3.createCopy());
/*    */     } 
/*    */     
/* 48 */     return var1;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean equals(Object p_equals_1_) {
/* 53 */     if (this == p_equals_1_)
/*    */     {
/* 55 */       return true;
/*    */     }
/* 57 */     if (!(p_equals_1_ instanceof ChatComponentText))
/*    */     {
/* 59 */       return false;
/*    */     }
/*    */ 
/*    */     
/* 63 */     ChatComponentText var2 = (ChatComponentText)p_equals_1_;
/* 64 */     return (this.text.equals(var2.getChatComponentText_TextValue()) && super.equals(p_equals_1_));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String toString() {
/* 70 */     return "TextComponent{text='" + this.text + '\'' + ", siblings=" + this.siblings + ", style=" + getChatStyle() + '}';
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraf\\util\ChatComponentText.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */