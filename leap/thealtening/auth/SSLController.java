/*    */ package leap.thealtening.auth;
/*    */ 
/*    */ import java.security.GeneralSecurityException;
/*    */ import java.security.NoSuchAlgorithmException;
/*    */ import java.security.SecureRandom;
/*    */ import java.security.cert.X509Certificate;
/*    */ import javax.net.ssl.HostnameVerifier;
/*    */ import javax.net.ssl.HttpsURLConnection;
/*    */ import javax.net.ssl.SSLContext;
/*    */ import javax.net.ssl.SSLSession;
/*    */ import javax.net.ssl.SSLSocketFactory;
/*    */ import javax.net.ssl.TrustManager;
/*    */ import javax.net.ssl.X509TrustManager;
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
/*    */ public final class SSLController
/*    */ {
/*    */   public SSLController() {
/* 36 */     SSLContext sc = null;
/*    */     try {
/* 38 */       sc = SSLContext.getInstance("SSL");
/* 39 */       sc.init(null, ALL_TRUSTING_TRUST_MANAGER, new SecureRandom());
/* 40 */     } catch (NoSuchAlgorithmException|java.security.KeyManagementException e) {
/* 41 */       e.printStackTrace();
/*    */     } 
/*    */     
/* 44 */     this.allTrustingFactory = sc.getSocketFactory();
/* 45 */     this.originalFactory = HttpsURLConnection.getDefaultSSLSocketFactory();
/* 46 */     this.originalHostVerifier = HttpsURLConnection.getDefaultHostnameVerifier();
/*    */   }
/*    */   
/*    */   public void enableCertificateValidation() {
/* 50 */     updateCertificateValidation(this.originalFactory, this.originalHostVerifier);
/*    */   }
/*    */   
/*    */   public void disableCertificateValidation() {
/* 54 */     updateCertificateValidation(this.allTrustingFactory, ALTENING_HOSTING_VERIFIER);
/*    */   }
/*    */   
/*    */   private void updateCertificateValidation(SSLSocketFactory factory, HostnameVerifier hostnameVerifier) {
/* 58 */     HttpsURLConnection.setDefaultSSLSocketFactory(factory);
/* 59 */     HttpsURLConnection.setDefaultHostnameVerifier(hostnameVerifier);
/*    */   }
/*    */ 
/*    */   
/* 63 */   private static final TrustManager[] ALL_TRUSTING_TRUST_MANAGER = new TrustManager[] {
/* 64 */       new X509TrustManager() { public void checkClientTrusted(X509Certificate[] certs, String authType) {}
/*    */         public X509Certificate[] getAcceptedIssuers() {
/* 66 */           return null;
/*    */         }
/*    */         
/*    */         public void checkServerTrusted(X509Certificate[] certs, String authType) {} }
/*    */        };
/*    */   
/*    */   static {
/* 73 */     ALTENING_HOSTING_VERIFIER = ((hostname, session) -> 
/* 74 */       !(!hostname.equals("authserver.thealtening.com") && !hostname.equals("sessionserver.thealtening.com")));
/*    */   }
/*    */   
/*    */   private static final HostnameVerifier ALTENING_HOSTING_VERIFIER;
/*    */   private final SSLSocketFactory allTrustingFactory;
/*    */   private final SSLSocketFactory originalFactory;
/*    */   private final HostnameVerifier originalHostVerifier;
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\leap\thealtening\auth\SSLController.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */