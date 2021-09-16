/*    */ package net.minecraft.network.status.client;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import net.minecraft.network.INetHandler;
/*    */ import net.minecraft.network.Packet;
/*    */ import net.minecraft.network.PacketBuffer;
/*    */ import net.minecraft.network.status.INetHandlerStatusServer;
/*    */ 
/*    */ public class C01PacketPing
/*    */   implements Packet
/*    */ {
/*    */   private long clientTime;
/*    */   private static final String __OBFID = "CL_00001392";
/*    */   
/*    */   public C01PacketPing() {}
/*    */   
/*    */   public C01PacketPing(long p_i45276_1_) {
/* 18 */     this.clientTime = p_i45276_1_;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void readPacketData(PacketBuffer data) throws IOException {
/* 26 */     this.clientTime = data.readLong();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void writePacketData(PacketBuffer data) throws IOException {
/* 34 */     data.writeLong(this.clientTime);
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_180774_a(INetHandlerStatusServer p_180774_1_) {
/* 39 */     p_180774_1_.processPing(this);
/*    */   }
/*    */ 
/*    */   
/*    */   public long getClientTime() {
/* 44 */     return this.clientTime;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void processPacket(INetHandler handler) {
/* 52 */     func_180774_a((INetHandlerStatusServer)handler);
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\network\status\client\C01PacketPing.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */