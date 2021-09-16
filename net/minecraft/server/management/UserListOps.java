/*    */ package net.minecraft.server.management;
/*    */ 
/*    */ import com.google.gson.JsonObject;
/*    */ import com.mojang.authlib.GameProfile;
/*    */ import java.io.File;
/*    */ import java.util.Iterator;
/*    */ 
/*    */ public class UserListOps
/*    */   extends UserList
/*    */ {
/*    */   private static final String __OBFID = "CL_00001879";
/*    */   
/*    */   public UserListOps(File p_i1152_1_) {
/* 14 */     super(p_i1152_1_);
/*    */   }
/*    */ 
/*    */   
/*    */   protected UserListEntry createEntry(JsonObject entryData) {
/* 19 */     return new UserListOpsEntry(entryData);
/*    */   }
/*    */ 
/*    */   
/*    */   public String[] getKeys() {
/* 24 */     String[] var1 = new String[getValues().size()];
/* 25 */     int var2 = 0;
/*    */ 
/*    */     
/* 28 */     for (Iterator<UserListOpsEntry> var3 = getValues().values().iterator(); var3.hasNext(); var1[var2++] = ((GameProfile)var4.getValue()).getName())
/*    */     {
/* 30 */       UserListOpsEntry var4 = var3.next();
/*    */     }
/*    */     
/* 33 */     return var1;
/*    */   }
/*    */ 
/*    */   
/*    */   protected String func_152699_b(GameProfile p_152699_1_) {
/* 38 */     return p_152699_1_.getId().toString();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public GameProfile getGameProfileFromName(String p_152700_1_) {
/*    */     UserListOpsEntry var3;
/* 46 */     Iterator<UserListOpsEntry> var2 = getValues().values().iterator();
/*    */ 
/*    */ 
/*    */     
/*    */     do {
/* 51 */       if (!var2.hasNext())
/*    */       {
/* 53 */         return null;
/*    */       }
/*    */       
/* 56 */       var3 = var2.next();
/*    */     }
/* 58 */     while (!p_152700_1_.equalsIgnoreCase(((GameProfile)var3.getValue()).getName()));
/*    */     
/* 60 */     return (GameProfile)var3.getValue();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected String getObjectKey(Object obj) {
/* 68 */     return func_152699_b((GameProfile)obj);
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\server\management\UserListOps.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */