/*    */ package leap.thealtening.auth.service;
/*    */ 
/*    */ import java.net.URL;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public final class ServiceSwitcher
/*    */ {
/* 28 */   public String MINECRAFT_SESSION_SERVICE_CLASS = "com.mojang.authlib.yggdrasil.YggdrasilMinecraftSessionService";
/* 29 */   public String MINECRAFT_AUTHENTICATION_SERVICE_CLASS = "com.mojang.authlib.yggdrasil.YggdrasilUserAuthentication";
/*    */   
/* 31 */   public String[] WHITELISTED_DOMAINS = new String[] { ".minecraft.net", ".mojang.com", ".thealtening.com" };
/*    */   
/* 33 */   public FieldAdapter minecraftSessionServer = new FieldAdapter(this.MINECRAFT_SESSION_SERVICE_CLASS);
/* 34 */   public FieldAdapter userAuthentication = new FieldAdapter(this.MINECRAFT_AUTHENTICATION_SERVICE_CLASS);
/*    */   
/*    */   public ServiceSwitcher() {
/*    */     try {
/* 38 */       this.minecraftSessionServer.updateFieldIfPresent("WHITELISTED_DOMAINS", this.WHITELISTED_DOMAINS);
/* 39 */     } catch (Exception e) {
/* 40 */       e.printStackTrace();
/*    */     } 
/*    */   }
/*    */   
/*    */   public AlteningServiceType switchToService(AlteningServiceType service) {
/*    */     try {
/* 46 */       String authServer = service.getAuthServer();
/* 47 */       FieldAdapter userAuth = this.userAuthentication;
/* 48 */       userAuth.updateFieldIfPresent("BASE_URL", authServer);
/* 49 */       userAuth.updateFieldIfPresent("ROUTE_AUTHENTICATE", new URL(String.valueOf(authServer) + "authenticate"));
/* 50 */       userAuth.updateFieldIfPresent("ROUTE_INVALIDATE", new URL(String.valueOf(authServer) + "invalidate"));
/* 51 */       userAuth.updateFieldIfPresent("ROUTE_REFRESH", new URL(String.valueOf(authServer) + "refresh"));
/* 52 */       userAuth.updateFieldIfPresent("ROUTE_VALIDATE", new URL(String.valueOf(authServer) + "validate"));
/* 53 */       userAuth.updateFieldIfPresent("ROUTE_SIGNOUT", new URL(String.valueOf(authServer) + "signout"));
/*    */       
/* 55 */       String sessionServer = service.getSessionServer();
/* 56 */       FieldAdapter userSession = this.minecraftSessionServer;
/* 57 */       userSession.updateFieldIfPresent("BASE_URL", String.valueOf(sessionServer) + "session/minecraft/");
/* 58 */       userSession.updateFieldIfPresent("JOIN_URL", new URL(String.valueOf(sessionServer) + "session/minecraft/join"));
/* 59 */       userSession.updateFieldIfPresent("CHECK_URL", new URL(String.valueOf(sessionServer) + "session/minecraft/hasJoined"));
/* 60 */     } catch (Exception ignored) {
/* 61 */       ignored.printStackTrace();
/* 62 */       return AlteningServiceType.MOJANG;
/*    */     } 
/*    */     
/* 65 */     return service;
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\leap\thealtening\auth\service\ServiceSwitcher.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */