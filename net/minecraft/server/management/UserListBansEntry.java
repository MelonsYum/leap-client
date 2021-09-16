/*    */ package net.minecraft.server.management;
/*    */ 
/*    */ import com.google.gson.JsonObject;
/*    */ import com.mojang.authlib.GameProfile;
/*    */ import java.util.Date;
/*    */ import java.util.UUID;
/*    */ 
/*    */ public class UserListBansEntry
/*    */   extends BanEntry
/*    */ {
/*    */   private static final String __OBFID = "CL_00001872";
/*    */   
/*    */   public UserListBansEntry(GameProfile p_i1134_1_) {
/* 14 */     this(p_i1134_1_, null, null, null, null);
/*    */   }
/*    */ 
/*    */   
/*    */   public UserListBansEntry(GameProfile p_i1135_1_, Date p_i1135_2_, String p_i1135_3_, Date p_i1135_4_, String p_i1135_5_) {
/* 19 */     super(p_i1135_1_, p_i1135_4_, p_i1135_3_, p_i1135_4_, p_i1135_5_);
/*    */   }
/*    */ 
/*    */   
/*    */   public UserListBansEntry(JsonObject p_i1136_1_) {
/* 24 */     super(func_152648_b(p_i1136_1_), p_i1136_1_);
/*    */   }
/*    */ 
/*    */   
/*    */   protected void onSerialization(JsonObject data) {
/* 29 */     if (getValue() != null) {
/*    */       
/* 31 */       data.addProperty("uuid", (((GameProfile)getValue()).getId() == null) ? "" : ((GameProfile)getValue()).getId().toString());
/* 32 */       data.addProperty("name", ((GameProfile)getValue()).getName());
/* 33 */       super.onSerialization(data);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   private static GameProfile func_152648_b(JsonObject p_152648_0_) {
/* 39 */     if (p_152648_0_.has("uuid") && p_152648_0_.has("name")) {
/*    */       UUID var2;
/* 41 */       String var1 = p_152648_0_.get("uuid").getAsString();
/*    */ 
/*    */ 
/*    */       
/*    */       try {
/* 46 */         var2 = UUID.fromString(var1);
/*    */       }
/* 48 */       catch (Throwable var4) {
/*    */         
/* 50 */         return null;
/*    */       } 
/*    */       
/* 53 */       return new GameProfile(var2, p_152648_0_.get("name").getAsString());
/*    */     } 
/*    */ 
/*    */     
/* 57 */     return null;
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\server\management\UserListBansEntry.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */