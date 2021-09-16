/*    */ package net.minecraft.server.management;
/*    */ 
/*    */ import com.google.gson.JsonObject;
/*    */ import com.mojang.authlib.GameProfile;
/*    */ import java.io.File;
/*    */ import java.util.Iterator;
/*    */ 
/*    */ public class UserListBans
/*    */   extends UserList
/*    */ {
/*    */   private static final String __OBFID = "CL_00001873";
/*    */   
/*    */   public UserListBans(File bansFile) {
/* 14 */     super(bansFile);
/*    */   }
/*    */ 
/*    */   
/*    */   protected UserListEntry createEntry(JsonObject entryData) {
/* 19 */     return new UserListBansEntry(entryData);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isBanned(GameProfile profile) {
/* 24 */     return hasEntry(profile);
/*    */   }
/*    */ 
/*    */   
/*    */   public String[] getKeys() {
/* 29 */     String[] var1 = new String[getValues().size()];
/* 30 */     int var2 = 0;
/*    */ 
/*    */     
/* 33 */     for (Iterator<UserListBansEntry> var3 = getValues().values().iterator(); var3.hasNext(); var1[var2++] = ((GameProfile)var4.getValue()).getName())
/*    */     {
/* 35 */       UserListBansEntry var4 = var3.next();
/*    */     }
/*    */     
/* 38 */     return var1;
/*    */   }
/*    */ 
/*    */   
/*    */   protected String getProfileId(GameProfile profile) {
/* 43 */     return profile.getId().toString();
/*    */   }
/*    */   
/*    */   public GameProfile isUsernameBanned(String username) {
/*    */     UserListBansEntry var3;
/* 48 */     Iterator<UserListBansEntry> var2 = getValues().values().iterator();
/*    */ 
/*    */ 
/*    */     
/*    */     do {
/* 53 */       if (!var2.hasNext())
/*    */       {
/* 55 */         return null;
/*    */       }
/*    */       
/* 58 */       var3 = var2.next();
/*    */     }
/* 60 */     while (!username.equalsIgnoreCase(((GameProfile)var3.getValue()).getName()));
/*    */     
/* 62 */     return (GameProfile)var3.getValue();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected String getObjectKey(Object obj) {
/* 70 */     return getProfileId((GameProfile)obj);
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\server\management\UserListBans.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */