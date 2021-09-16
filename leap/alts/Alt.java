/*    */ package leap.alts;
/*    */ 
/*    */ public final class Alt
/*    */ {
/*  5 */   private String mask = "";
/*    */   private final String username;
/*    */   private String password;
/*    */   
/*    */   public Alt(String username, String password) {
/* 10 */     this(username, password, "");
/*    */   }
/*    */   
/*    */   public Alt(String username, String password, String mask) {
/* 14 */     this.username = username;
/* 15 */     this.password = password;
/* 16 */     this.mask = mask;
/*    */   }
/*    */   
/*    */   public String getMask() {
/* 20 */     return this.mask;
/*    */   }
/*    */   
/*    */   public String getPassword() {
/* 24 */     return this.password;
/*    */   }
/*    */   
/*    */   public String getUsername() {
/* 28 */     return this.username;
/*    */   }
/*    */   
/*    */   public void setMask(String mask) {
/* 32 */     this.mask = mask;
/*    */   }
/*    */   
/*    */   public void setPassword(String password) {
/* 36 */     this.password = password;
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\leap\alts\Alt.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */