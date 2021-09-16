/*    */ package leap.thealtening.auth;
/*    */ 
/*    */ import leap.thealtening.auth.service.AlteningServiceType;
/*    */ import leap.thealtening.auth.service.ServiceSwitcher;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public final class TheAlteningAuthentication
/*    */ {
/* 38 */   private final ServiceSwitcher serviceSwitcher = new ServiceSwitcher();
/* 39 */   private final SSLController sslController = new SSLController();
/*    */   private static TheAlteningAuthentication instance;
/*    */   private AlteningServiceType service;
/*    */   
/*    */   private TheAlteningAuthentication(AlteningServiceType service) {
/* 44 */     updateService(service);
/*    */   }
/*    */   
/*    */   public void updateService(AlteningServiceType service) {
/* 48 */     if (service == null || this.service == service) {
/*    */       return;
/*    */     }
/*    */     
/* 52 */     switch (service) {
/*    */       case null:
/* 54 */         this.sslController.enableCertificateValidation();
/*    */         break;
/*    */       
/*    */       case THEALTENING:
/* 58 */         this.sslController.disableCertificateValidation();
/*    */         break;
/*    */     } 
/*    */     
/* 62 */     this.service = this.serviceSwitcher.switchToService(service);
/*    */   }
/*    */   
/*    */   public AlteningServiceType getService() {
/* 66 */     return this.service;
/*    */   }
/*    */   
/*    */   public static TheAlteningAuthentication mojang() {
/* 70 */     return withService(AlteningServiceType.MOJANG);
/*    */   }
/*    */   
/*    */   public static TheAlteningAuthentication theAltening() {
/* 74 */     return withService(AlteningServiceType.THEALTENING);
/*    */   }
/*    */   
/*    */   private static TheAlteningAuthentication withService(AlteningServiceType service) {
/* 78 */     if (instance == null) {
/* 79 */       instance = new TheAlteningAuthentication(service);
/* 80 */     } else if (instance.getService() != service) {
/* 81 */       instance.updateService(service);
/*    */     } 
/*    */     
/* 84 */     return instance;
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\leap\thealtening\auth\TheAlteningAuthentication.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */