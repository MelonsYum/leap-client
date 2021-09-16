/*    */ package net.minecraft.client.stream;
/*    */ 
/*    */ import tv.twitch.ErrorCode;
/*    */ import tv.twitch.broadcast.IngestServer;
/*    */ import tv.twitch.chat.ChatUserInfo;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public interface IStream
/*    */ {
/*    */   void shutdownStream();
/*    */   
/*    */   void func_152935_j();
/*    */   
/*    */   void func_152922_k();
/*    */   
/*    */   boolean func_152936_l();
/*    */   
/*    */   boolean func_152924_m();
/*    */   
/*    */   boolean func_152934_n();
/*    */   
/*    */   void func_152911_a(Metadata paramMetadata, long paramLong);
/*    */   
/*    */   void func_176026_a(Metadata paramMetadata, long paramLong1, long paramLong2);
/*    */   
/*    */   boolean isPaused();
/*    */   
/*    */   void func_152931_p();
/*    */   
/*    */   void func_152916_q();
/*    */   
/*    */   void func_152933_r();
/*    */   
/*    */   void func_152915_s();
/*    */   
/*    */   void func_152930_t();
/*    */   
/*    */   void func_152914_u();
/*    */   
/*    */   IngestServer[] func_152925_v();
/*    */   
/*    */   void func_152909_x();
/*    */   
/*    */   IngestServerTester func_152932_y();
/*    */   
/*    */   boolean func_152908_z();
/*    */   
/*    */   int func_152920_A();
/*    */   
/*    */   boolean func_152927_B();
/*    */   
/*    */   String func_152921_C();
/*    */   
/*    */   ChatUserInfo func_152926_a(String paramString);
/*    */   
/*    */   void func_152917_b(String paramString);
/*    */   
/*    */   boolean func_152928_D();
/*    */   
/*    */   ErrorCode func_152912_E();
/*    */   
/*    */   boolean func_152913_F();
/*    */   
/*    */   void func_152910_a(boolean paramBoolean);
/*    */   
/*    */   boolean func_152929_G();
/*    */   
/*    */   AuthFailureReason func_152918_H();
/*    */   
/*    */   public enum AuthFailureReason
/*    */   {
/* 74 */     ERROR("ERROR", 0),
/* 75 */     INVALID_TOKEN("INVALID_TOKEN", 1);
/*    */     
/* 77 */     private static final AuthFailureReason[] $VALUES = new AuthFailureReason[] { ERROR, INVALID_TOKEN };
/*    */     private static final String __OBFID = "CL_00001813";
/*    */     
/*    */     static {
/*    */     
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\stream\IStream.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */