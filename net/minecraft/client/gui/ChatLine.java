/*    */ package net.minecraft.client.gui;
/*    */ 
/*    */ import net.minecraft.util.IChatComponent;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ChatLine
/*    */ {
/*    */   private final int updateCounterCreated;
/*    */   private final IChatComponent lineString;
/*    */   private final int chatLineID;
/*    */   private static final String __OBFID = "CL_00000627";
/*    */   
/*    */   public ChatLine(int p_i45000_1_, IChatComponent p_i45000_2_, int p_i45000_3_) {
/* 19 */     this.lineString = p_i45000_2_;
/* 20 */     this.updateCounterCreated = p_i45000_1_;
/* 21 */     this.chatLineID = p_i45000_3_;
/*    */   }
/*    */ 
/*    */   
/*    */   public IChatComponent getChatComponent() {
/* 26 */     return this.lineString;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getUpdatedCounter() {
/* 31 */     return this.updateCounterCreated;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getChatLineID() {
/* 36 */     return this.chatLineID;
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\gui\ChatLine.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */