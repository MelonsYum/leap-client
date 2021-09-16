/*    */ package net.minecraft.server.management;
/*    */ 
/*    */ import com.google.gson.JsonObject;
/*    */ import java.text.ParseException;
/*    */ import java.text.SimpleDateFormat;
/*    */ import java.util.Date;
/*    */ 
/*    */ public abstract class BanEntry
/*    */   extends UserListEntry {
/* 10 */   public static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss Z");
/*    */   
/*    */   protected final Date banStartDate;
/*    */   protected final String bannedBy;
/*    */   protected final Date banEndDate;
/*    */   protected final String reason;
/*    */   private static final String __OBFID = "CL_00001395";
/*    */   
/*    */   public BanEntry(Object p_i46334_1_, Date p_i46334_2_, String p_i46334_3_, Date p_i46334_4_, String p_i46334_5_) {
/* 19 */     super(p_i46334_1_);
/* 20 */     this.banStartDate = (p_i46334_2_ == null) ? new Date() : p_i46334_2_;
/* 21 */     this.bannedBy = (p_i46334_3_ == null) ? "(Unknown)" : p_i46334_3_;
/* 22 */     this.banEndDate = p_i46334_4_;
/* 23 */     this.reason = (p_i46334_5_ == null) ? "Banned by an operator." : p_i46334_5_;
/*    */   }
/*    */ 
/*    */   
/*    */   protected BanEntry(Object p_i1174_1_, JsonObject p_i1174_2_) {
/* 28 */     super(p_i1174_1_, p_i1174_2_);
/*    */     
/*    */     Date var3, var4;
/*    */     
/*    */     try {
/* 33 */       var3 = p_i1174_2_.has("created") ? dateFormat.parse(p_i1174_2_.get("created").getAsString()) : new Date();
/*    */     }
/* 35 */     catch (ParseException var7) {
/*    */       
/* 37 */       var3 = new Date();
/*    */     } 
/*    */     
/* 40 */     this.banStartDate = var3;
/* 41 */     this.bannedBy = p_i1174_2_.has("source") ? p_i1174_2_.get("source").getAsString() : "(Unknown)";
/*    */ 
/*    */ 
/*    */     
/*    */     try {
/* 46 */       var4 = p_i1174_2_.has("expires") ? dateFormat.parse(p_i1174_2_.get("expires").getAsString()) : null;
/*    */     }
/* 48 */     catch (ParseException var6) {
/*    */       
/* 50 */       var4 = null;
/*    */     } 
/*    */     
/* 53 */     this.banEndDate = var4;
/* 54 */     this.reason = p_i1174_2_.has("reason") ? p_i1174_2_.get("reason").getAsString() : "Banned by an operator.";
/*    */   }
/*    */ 
/*    */   
/*    */   public Date getBanEndDate() {
/* 59 */     return this.banEndDate;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getBanReason() {
/* 64 */     return this.reason;
/*    */   }
/*    */ 
/*    */   
/*    */   boolean hasBanExpired() {
/* 69 */     return (this.banEndDate == null) ? false : this.banEndDate.before(new Date());
/*    */   }
/*    */ 
/*    */   
/*    */   protected void onSerialization(JsonObject data) {
/* 74 */     data.addProperty("created", dateFormat.format(this.banStartDate));
/* 75 */     data.addProperty("source", this.bannedBy);
/* 76 */     data.addProperty("expires", (this.banEndDate == null) ? "forever" : dateFormat.format(this.banEndDate));
/* 77 */     data.addProperty("reason", this.reason);
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\server\management\BanEntry.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */