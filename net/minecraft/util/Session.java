/*    */ package net.minecraft.util;
/*    */ 
/*    */ import com.google.common.collect.Maps;
/*    */ import com.mojang.authlib.GameProfile;
/*    */ import com.mojang.util.UUIDTypeAdapter;
/*    */ import java.util.Map;
/*    */ import java.util.UUID;
/*    */ 
/*    */ 
/*    */ public class Session
/*    */ {
/*    */   private final String username;
/*    */   private final String playerID;
/*    */   private final String token;
/*    */   private final Type sessionType;
/*    */   private static final String __OBFID = "CL_00000659";
/*    */   
/*    */   public Session(String p_i1098_1_, String p_i1098_2_, String p_i1098_3_, String p_i1098_4_) {
/* 19 */     this.username = p_i1098_1_;
/* 20 */     this.playerID = p_i1098_2_;
/* 21 */     this.token = p_i1098_3_;
/* 22 */     this.sessionType = Type.setSessionType(p_i1098_4_);
/*    */   }
/*    */ 
/*    */   
/*    */   public String getSessionID() {
/* 27 */     return "token:" + this.token + ":" + this.playerID;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getPlayerID() {
/* 32 */     return this.playerID;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getUsername() {
/* 37 */     return this.username;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getToken() {
/* 42 */     return this.token;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public GameProfile getProfile() {
/*    */     try {
/* 49 */       UUID var1 = UUIDTypeAdapter.fromString(getPlayerID());
/* 50 */       return new GameProfile(var1, getUsername());
/*    */     }
/* 52 */     catch (IllegalArgumentException var2) {
/*    */       
/* 54 */       return new GameProfile(null, getUsername());
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Type getSessionType() {
/* 63 */     return this.sessionType;
/*    */   }
/*    */   
/*    */   public enum Type
/*    */   {
/* 68 */     LEGACY("LEGACY", 0, "legacy"),
/* 69 */     MOJANG("MOJANG", 1, "mojang");
/* 70 */     private static final Map field_152425_c = Maps.newHashMap();
/*    */     
/*    */     private final String sessionType;
/* 73 */     private static final Type[] $VALUES = new Type[] { LEGACY, MOJANG };
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/*    */     private static final String __OBFID = "CL_00001851";
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/*    */     static {
/* 87 */       Type[] var0 = values();
/* 88 */       int var1 = var0.length;
/*    */       
/* 90 */       for (int var2 = 0; var2 < var1; var2++) {
/*    */         
/* 92 */         Type var3 = var0[var2];
/* 93 */         field_152425_c.put(var3.sessionType, var3);
/*    */       } 
/*    */     }
/*    */     
/*    */     Type(String p_i1096_1_, int p_i1096_2_, String p_i1096_3_) {
/*    */       this.sessionType = p_i1096_3_;
/*    */     }
/*    */     
/*    */     public static Type setSessionType(String p_152421_0_) {
/*    */       return (Type)field_152425_c.get(p_152421_0_.toLowerCase());
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraf\\util\Session.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */