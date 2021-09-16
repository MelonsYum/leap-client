/*    */ package net.minecraft.realms;
/*    */ 
/*    */ import net.minecraft.client.multiplayer.ServerAddress;
/*    */ 
/*    */ 
/*    */ public class RealmsServerAddress
/*    */ {
/*    */   private final String host;
/*    */   private final int port;
/*    */   private static final String __OBFID = "CL_00001864";
/*    */   
/*    */   protected RealmsServerAddress(String p_i1121_1_, int p_i1121_2_) {
/* 13 */     this.host = p_i1121_1_;
/* 14 */     this.port = p_i1121_2_;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getHost() {
/* 19 */     return this.host;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getPort() {
/* 24 */     return this.port;
/*    */   }
/*    */ 
/*    */   
/*    */   public static RealmsServerAddress parseString(String p_parseString_0_) {
/* 29 */     ServerAddress var1 = ServerAddress.func_78860_a(p_parseString_0_);
/* 30 */     return new RealmsServerAddress(var1.getIP(), var1.getPort());
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\realms\RealmsServerAddress.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */