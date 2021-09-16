/*     */ package net.minecraft.client.stream;
/*     */ 
/*     */ import tv.twitch.ErrorCode;
/*     */ import tv.twitch.broadcast.IngestServer;
/*     */ import tv.twitch.chat.ChatUserInfo;
/*     */ 
/*     */ public class NullStream
/*     */   implements IStream
/*     */ {
/*     */   private final Throwable field_152938_a;
/*     */   private static final String __OBFID = "CL_00001809";
/*     */   
/*     */   public NullStream(Throwable p_i1006_1_) {
/*  14 */     this.field_152938_a = p_i1006_1_;
/*     */   }
/*     */ 
/*     */   
/*     */   public void shutdownStream() {}
/*     */ 
/*     */   
/*     */   public void func_152935_j() {}
/*     */ 
/*     */   
/*     */   public void func_152922_k() {}
/*     */ 
/*     */   
/*     */   public boolean func_152936_l() {
/*  28 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_152924_m() {
/*  33 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_152934_n() {
/*  38 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_152911_a(Metadata p_152911_1_, long p_152911_2_) {}
/*     */   
/*     */   public void func_176026_a(Metadata p_176026_1_, long p_176026_2_, long p_176026_4_) {}
/*     */   
/*     */   public boolean isPaused() {
/*  47 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_152931_p() {}
/*     */   
/*     */   public void func_152916_q() {}
/*     */   
/*     */   public void func_152933_r() {}
/*     */   
/*     */   public void func_152915_s() {}
/*     */   
/*     */   public void func_152930_t() {}
/*     */   
/*     */   public void func_152914_u() {}
/*     */   
/*     */   public IngestServer[] func_152925_v() {
/*  64 */     return new IngestServer[0];
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_152909_x() {}
/*     */   
/*     */   public IngestServerTester func_152932_y() {
/*  71 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_152908_z() {
/*  76 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public int func_152920_A() {
/*  81 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_152927_B() {
/*  86 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public String func_152921_C() {
/*  91 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public ChatUserInfo func_152926_a(String p_152926_1_) {
/*  96 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_152917_b(String p_152917_1_) {}
/*     */   
/*     */   public boolean func_152928_D() {
/* 103 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public ErrorCode func_152912_E() {
/* 108 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_152913_F() {
/* 113 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_152910_a(boolean p_152910_1_) {}
/*     */   
/*     */   public boolean func_152929_G() {
/* 120 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public IStream.AuthFailureReason func_152918_H() {
/* 125 */     return IStream.AuthFailureReason.ERROR;
/*     */   }
/*     */ 
/*     */   
/*     */   public Throwable func_152937_a() {
/* 130 */     return this.field_152938_a;
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\stream\NullStream.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */