/*    */ package net.minecraft.server.management;
/*    */ 
/*    */ import com.google.gson.JsonObject;
/*    */ 
/*    */ 
/*    */ public class UserListEntry
/*    */ {
/*    */   private final Object value;
/*    */   private static final String __OBFID = "CL_00001877";
/*    */   
/*    */   public UserListEntry(Object p_i1146_1_) {
/* 12 */     this.value = p_i1146_1_;
/*    */   }
/*    */ 
/*    */   
/*    */   protected UserListEntry(Object p_i1147_1_, JsonObject p_i1147_2_) {
/* 17 */     this.value = p_i1147_1_;
/*    */   }
/*    */ 
/*    */   
/*    */   Object getValue() {
/* 22 */     return this.value;
/*    */   }
/*    */ 
/*    */   
/*    */   boolean hasBanExpired() {
/* 27 */     return false;
/*    */   }
/*    */   
/*    */   protected void onSerialization(JsonObject data) {}
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\server\management\UserListEntry.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */