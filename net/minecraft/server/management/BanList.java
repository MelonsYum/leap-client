/*    */ package net.minecraft.server.management;
/*    */ 
/*    */ import com.google.gson.JsonObject;
/*    */ import java.io.File;
/*    */ import java.net.SocketAddress;
/*    */ 
/*    */ public class BanList
/*    */   extends UserList
/*    */ {
/*    */   private static final String __OBFID = "CL_00001396";
/*    */   
/*    */   public BanList(File bansFile) {
/* 13 */     super(bansFile);
/*    */   }
/*    */ 
/*    */   
/*    */   protected UserListEntry createEntry(JsonObject entryData) {
/* 18 */     return new IPBanEntry(entryData);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isBanned(SocketAddress address) {
/* 23 */     String var2 = addressToString(address);
/* 24 */     return hasEntry(var2);
/*    */   }
/*    */ 
/*    */   
/*    */   public IPBanEntry getBanEntry(SocketAddress address) {
/* 29 */     String var2 = addressToString(address);
/* 30 */     return (IPBanEntry)getEntry(var2);
/*    */   }
/*    */ 
/*    */   
/*    */   private String addressToString(SocketAddress address) {
/* 35 */     String var2 = address.toString();
/*    */     
/* 37 */     if (var2.contains("/"))
/*    */     {
/* 39 */       var2 = var2.substring(var2.indexOf('/') + 1);
/*    */     }
/*    */     
/* 42 */     if (var2.contains(":"))
/*    */     {
/* 44 */       var2 = var2.substring(0, var2.indexOf(':'));
/*    */     }
/*    */     
/* 47 */     return var2;
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\server\management\BanList.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */