/*    */ package net.minecraft.server.management;
/*    */ 
/*    */ import com.google.gson.JsonObject;
/*    */ import com.mojang.authlib.GameProfile;
/*    */ import java.io.File;
/*    */ import java.util.Iterator;
/*    */ 
/*    */ public class UserListWhitelist
/*    */   extends UserList
/*    */ {
/*    */   private static final String __OBFID = "CL_00001871";
/*    */   
/*    */   public UserListWhitelist(File p_i1132_1_) {
/* 14 */     super(p_i1132_1_);
/*    */   }
/*    */ 
/*    */   
/*    */   protected UserListEntry createEntry(JsonObject entryData) {
/* 19 */     return new UserListWhitelistEntry(entryData);
/*    */   }
/*    */ 
/*    */   
/*    */   public String[] getKeys() {
/* 24 */     String[] var1 = new String[getValues().size()];
/* 25 */     int var2 = 0;
/*    */ 
/*    */     
/* 28 */     for (Iterator<UserListWhitelistEntry> var3 = getValues().values().iterator(); var3.hasNext(); var1[var2++] = ((GameProfile)var4.getValue()).getName())
/*    */     {
/* 30 */       UserListWhitelistEntry var4 = var3.next();
/*    */     }
/*    */     
/* 33 */     return var1;
/*    */   }
/*    */ 
/*    */   
/*    */   protected String func_152704_b(GameProfile p_152704_1_) {
/* 38 */     return p_152704_1_.getId().toString();
/*    */   }
/*    */   
/*    */   public GameProfile func_152706_a(String p_152706_1_) {
/*    */     UserListWhitelistEntry var3;
/* 43 */     Iterator<UserListWhitelistEntry> var2 = getValues().values().iterator();
/*    */ 
/*    */ 
/*    */     
/*    */     do {
/* 48 */       if (!var2.hasNext())
/*    */       {
/* 50 */         return null;
/*    */       }
/*    */       
/* 53 */       var3 = var2.next();
/*    */     }
/* 55 */     while (!p_152706_1_.equalsIgnoreCase(((GameProfile)var3.getValue()).getName()));
/*    */     
/* 57 */     return (GameProfile)var3.getValue();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected String getObjectKey(Object obj) {
/* 65 */     return func_152704_b((GameProfile)obj);
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\server\management\UserListWhitelist.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */