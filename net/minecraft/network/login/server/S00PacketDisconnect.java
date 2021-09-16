/*    */ package net.minecraft.network.login.server;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import net.minecraft.network.INetHandler;
/*    */ import net.minecraft.network.Packet;
/*    */ import net.minecraft.network.PacketBuffer;
/*    */ import net.minecraft.network.login.INetHandlerLoginClient;
/*    */ import net.minecraft.util.IChatComponent;
/*    */ 
/*    */ public class S00PacketDisconnect
/*    */   implements Packet
/*    */ {
/*    */   private IChatComponent reason;
/*    */   private static final String __OBFID = "CL_00001377";
/*    */   
/*    */   public S00PacketDisconnect() {}
/*    */   
/*    */   public S00PacketDisconnect(IChatComponent reasonIn) {
/* 19 */     this.reason = reasonIn;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void readPacketData(PacketBuffer data) throws IOException {
/* 27 */     this.reason = data.readChatComponent();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void writePacketData(PacketBuffer data) throws IOException {
/* 35 */     data.writeChatComponent(this.reason);
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_180772_a(INetHandlerLoginClient p_180772_1_) {
/* 40 */     p_180772_1_.handleDisconnect(this);
/*    */   }
/*    */ 
/*    */   
/*    */   public IChatComponent func_149603_c() {
/* 45 */     return this.reason;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void processPacket(INetHandler handler) {
/* 53 */     func_180772_a((INetHandlerLoginClient)handler);
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\network\login\server\S00PacketDisconnect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */