/*    */ package net.minecraft.network.handshake.client;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import net.minecraft.network.EnumConnectionState;
/*    */ import net.minecraft.network.INetHandler;
/*    */ import net.minecraft.network.Packet;
/*    */ import net.minecraft.network.PacketBuffer;
/*    */ import net.minecraft.network.handshake.INetHandlerHandshakeServer;
/*    */ 
/*    */ public class C00Handshake
/*    */   implements Packet
/*    */ {
/*    */   private int protocolVersion;
/*    */   private String ip;
/*    */   private int port;
/*    */   private EnumConnectionState requestedState;
/*    */   private static final String __OBFID = "CL_00001372";
/*    */   
/*    */   public C00Handshake() {}
/*    */   
/*    */   public C00Handshake(int p_i45266_1_, String p_i45266_2_, int p_i45266_3_, EnumConnectionState p_i45266_4_) {
/* 22 */     this.protocolVersion = p_i45266_1_;
/* 23 */     this.ip = p_i45266_2_;
/* 24 */     this.port = p_i45266_3_;
/* 25 */     this.requestedState = p_i45266_4_;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void readPacketData(PacketBuffer data) throws IOException {
/* 33 */     this.protocolVersion = data.readVarIntFromBuffer();
/* 34 */     this.ip = data.readStringFromBuffer(255);
/* 35 */     this.port = data.readUnsignedShort();
/* 36 */     this.requestedState = EnumConnectionState.getById(data.readVarIntFromBuffer());
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void writePacketData(PacketBuffer data) throws IOException {
/* 44 */     data.writeVarIntToBuffer(this.protocolVersion);
/* 45 */     data.writeString(this.ip);
/* 46 */     data.writeShort(this.port);
/* 47 */     data.writeVarIntToBuffer(this.requestedState.getId());
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_180770_a(INetHandlerHandshakeServer p_180770_1_) {
/* 52 */     p_180770_1_.processHandshake(this);
/*    */   }
/*    */ 
/*    */   
/*    */   public EnumConnectionState getRequestedState() {
/* 57 */     return this.requestedState;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getProtocolVersion() {
/* 62 */     return this.protocolVersion;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void processPacket(INetHandler handler) {
/* 70 */     func_180770_a((INetHandlerHandshakeServer)handler);
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\network\handshake\client\C00Handshake.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */