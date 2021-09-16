/*    */ package net.minecraft.server.management;
/*    */ 
/*    */ import com.google.gson.JsonObject;
/*    */ import com.mojang.authlib.GameProfile;
/*    */ import java.util.UUID;
/*    */ 
/*    */ public class UserListOpsEntry
/*    */   extends UserListEntry
/*    */ {
/*    */   private final int field_152645_a;
/*    */   private static final String __OBFID = "CL_00001878";
/*    */   
/*    */   public UserListOpsEntry(GameProfile p_i46328_1_, int p_i46328_2_) {
/* 14 */     super(p_i46328_1_);
/* 15 */     this.field_152645_a = p_i46328_2_;
/*    */   }
/*    */ 
/*    */   
/*    */   public UserListOpsEntry(JsonObject p_i1150_1_) {
/* 20 */     super(func_152643_b(p_i1150_1_), p_i1150_1_);
/* 21 */     this.field_152645_a = p_i1150_1_.has("level") ? p_i1150_1_.get("level").getAsInt() : 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public int func_152644_a() {
/* 26 */     return this.field_152645_a;
/*    */   }
/*    */ 
/*    */   
/*    */   protected void onSerialization(JsonObject data) {
/* 31 */     if (getValue() != null) {
/*    */       
/* 33 */       data.addProperty("uuid", (((GameProfile)getValue()).getId() == null) ? "" : ((GameProfile)getValue()).getId().toString());
/* 34 */       data.addProperty("name", ((GameProfile)getValue()).getName());
/* 35 */       super.onSerialization(data);
/* 36 */       data.addProperty("level", Integer.valueOf(this.field_152645_a));
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   private static GameProfile func_152643_b(JsonObject p_152643_0_) {
/* 42 */     if (p_152643_0_.has("uuid") && p_152643_0_.has("name")) {
/*    */       UUID var2;
/* 44 */       String var1 = p_152643_0_.get("uuid").getAsString();
/*    */ 
/*    */ 
/*    */       
/*    */       try {
/* 49 */         var2 = UUID.fromString(var1);
/*    */       }
/* 51 */       catch (Throwable var4) {
/*    */         
/* 53 */         return null;
/*    */       } 
/*    */       
/* 56 */       return new GameProfile(var2, p_152643_0_.get("name").getAsString());
/*    */     } 
/*    */ 
/*    */     
/* 60 */     return null;
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\server\management\UserListOpsEntry.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */