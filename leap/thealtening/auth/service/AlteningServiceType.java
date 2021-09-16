/*    */ package leap.thealtening.auth.service;
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
/*    */ public enum AlteningServiceType
/*    */ {
/* 26 */   MOJANG("https://authserver.mojang.com/", "https://sessionserver.mojang.com/"),
/* 27 */   THEALTENING("http://authserver.thealtening.com/", "http://sessionserver.thealtening.com/");
/*    */   
/*    */   private final String authServer;
/*    */   private final String sessionServer;
/*    */   
/*    */   AlteningServiceType(String authServer, String sessionServer) {
/* 33 */     this.authServer = authServer;
/* 34 */     this.sessionServer = sessionServer;
/*    */   }
/*    */   
/*    */   public String getAuthServer() {
/* 38 */     return this.authServer;
/*    */   }
/*    */   
/*    */   public String getSessionServer() {
/* 42 */     return this.sessionServer;
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\leap\thealtening\auth\service\AlteningServiceType.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */