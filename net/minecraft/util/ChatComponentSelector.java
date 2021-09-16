/*    */ package net.minecraft.util;
/*    */ 
/*    */ import java.util.Iterator;
/*    */ 
/*    */ public class ChatComponentSelector
/*    */   extends ChatComponentStyle
/*    */ {
/*    */   private final String field_179993_b;
/*    */   private static final String __OBFID = "CL_00002308";
/*    */   
/*    */   public ChatComponentSelector(String p_i45996_1_) {
/* 12 */     this.field_179993_b = p_i45996_1_;
/*    */   }
/*    */ 
/*    */   
/*    */   public String func_179992_g() {
/* 17 */     return this.field_179993_b;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String getUnformattedTextForChat() {
/* 26 */     return this.field_179993_b;
/*    */   }
/*    */ 
/*    */   
/*    */   public ChatComponentSelector func_179991_h() {
/* 31 */     ChatComponentSelector var1 = new ChatComponentSelector(this.field_179993_b);
/* 32 */     var1.setChatStyle(getChatStyle().createShallowCopy());
/* 33 */     Iterator<IChatComponent> var2 = getSiblings().iterator();
/*    */     
/* 35 */     while (var2.hasNext()) {
/*    */       
/* 37 */       IChatComponent var3 = var2.next();
/* 38 */       var1.appendSibling(var3.createCopy());
/*    */     } 
/*    */     
/* 41 */     return var1;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean equals(Object p_equals_1_) {
/* 46 */     if (this == p_equals_1_)
/*    */     {
/* 48 */       return true;
/*    */     }
/* 50 */     if (!(p_equals_1_ instanceof ChatComponentSelector))
/*    */     {
/* 52 */       return false;
/*    */     }
/*    */ 
/*    */     
/* 56 */     ChatComponentSelector var2 = (ChatComponentSelector)p_equals_1_;
/* 57 */     return (this.field_179993_b.equals(var2.field_179993_b) && super.equals(p_equals_1_));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String toString() {
/* 63 */     return "SelectorComponent{pattern='" + this.field_179993_b + '\'' + ", siblings=" + this.siblings + ", style=" + getChatStyle() + '}';
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public IChatComponent createCopy() {
/* 71 */     return func_179991_h();
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraf\\util\ChatComponentSelector.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */