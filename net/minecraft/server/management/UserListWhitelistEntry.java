/*    */ package net.minecraft.server.management;
/*    */ 
/*    */ import com.google.gson.JsonObject;
/*    */ import com.mojang.authlib.GameProfile;
/*    */ import java.util.UUID;
/*    */ 
/*    */ public class UserListWhitelistEntry
/*    */   extends UserListEntry
/*    */ {
/*    */   private static final String __OBFID = "CL_00001870";
/*    */   
/*    */   public UserListWhitelistEntry(GameProfile p_i1129_1_) {
/* 13 */     super(p_i1129_1_);
/*    */   }
/*    */ 
/*    */   
/*    */   public UserListWhitelistEntry(JsonObject p_i1130_1_) {
/* 18 */     super(func_152646_b(p_i1130_1_), p_i1130_1_);
/*    */   }
/*    */ 
/*    */   
/*    */   protected void onSerialization(JsonObject data) {
/* 23 */     if (getValue() != null) {
/*    */       
/* 25 */       data.addProperty("uuid", (((GameProfile)getValue()).getId() == null) ? "" : ((GameProfile)getValue()).getId().toString());
/* 26 */       data.addProperty("name", ((GameProfile)getValue()).getName());
/* 27 */       super.onSerialization(data);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   private static GameProfile func_152646_b(JsonObject p_152646_0_) {
/* 33 */     if (p_152646_0_.has("uuid") && p_152646_0_.has("name")) {
/*    */       UUID var2;
/* 35 */       String var1 = p_152646_0_.get("uuid").getAsString();
/*    */ 
/*    */ 
/*    */       
/*    */       try {
/* 40 */         var2 = UUID.fromString(var1);
/*    */       }
/* 42 */       catch (Throwable var4) {
/*    */         
/* 44 */         return null;
/*    */       } 
/*    */       
/* 47 */       return new GameProfile(var2, p_152646_0_.get("name").getAsString());
/*    */     } 
/*    */ 
/*    */     
/* 51 */     return null;
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\server\management\UserListWhitelistEntry.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */