/*    */ package net.minecraft.server.management;
/*    */ 
/*    */ import com.google.gson.JsonObject;
/*    */ import java.util.Date;
/*    */ 
/*    */ public class IPBanEntry
/*    */   extends BanEntry
/*    */ {
/*    */   private static final String __OBFID = "CL_00001883";
/*    */   
/*    */   public IPBanEntry(String p_i46330_1_) {
/* 12 */     this(p_i46330_1_, null, null, null, null);
/*    */   }
/*    */ 
/*    */   
/*    */   public IPBanEntry(String p_i1159_1_, Date p_i1159_2_, String p_i1159_3_, Date p_i1159_4_, String p_i1159_5_) {
/* 17 */     super(p_i1159_1_, p_i1159_2_, p_i1159_3_, p_i1159_4_, p_i1159_5_);
/*    */   }
/*    */ 
/*    */   
/*    */   public IPBanEntry(JsonObject p_i46331_1_) {
/* 22 */     super(func_152647_b(p_i46331_1_), p_i46331_1_);
/*    */   }
/*    */ 
/*    */   
/*    */   private static String func_152647_b(JsonObject p_152647_0_) {
/* 27 */     return p_152647_0_.has("ip") ? p_152647_0_.get("ip").getAsString() : null;
/*    */   }
/*    */ 
/*    */   
/*    */   protected void onSerialization(JsonObject data) {
/* 32 */     if (getValue() != null) {
/*    */       
/* 34 */       data.addProperty("ip", (String)getValue());
/* 35 */       super.onSerialization(data);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\server\management\IPBanEntry.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */