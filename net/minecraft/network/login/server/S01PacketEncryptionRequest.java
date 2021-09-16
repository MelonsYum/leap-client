/*    */ package net.minecraft.network.login.server;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.security.PublicKey;
/*    */ import net.minecraft.network.INetHandler;
/*    */ import net.minecraft.network.Packet;
/*    */ import net.minecraft.network.PacketBuffer;
/*    */ import net.minecraft.network.login.INetHandlerLoginClient;
/*    */ import net.minecraft.util.CryptManager;
/*    */ 
/*    */ public class S01PacketEncryptionRequest
/*    */   implements Packet
/*    */ {
/*    */   private String hashedServerId;
/*    */   private PublicKey publicKey;
/*    */   private byte[] field_149611_c;
/*    */   private static final String __OBFID = "CL_00001376";
/*    */   
/*    */   public S01PacketEncryptionRequest() {}
/*    */   
/*    */   public S01PacketEncryptionRequest(String serverId, PublicKey key, byte[] p_i45268_3_) {
/* 22 */     this.hashedServerId = serverId;
/* 23 */     this.publicKey = key;
/* 24 */     this.field_149611_c = p_i45268_3_;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void readPacketData(PacketBuffer data) throws IOException {
/* 32 */     this.hashedServerId = data.readStringFromBuffer(20);
/* 33 */     this.publicKey = CryptManager.decodePublicKey(data.readByteArray());
/* 34 */     this.field_149611_c = data.readByteArray();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void writePacketData(PacketBuffer data) throws IOException {
/* 42 */     data.writeString(this.hashedServerId);
/* 43 */     data.writeByteArray(this.publicKey.getEncoded());
/* 44 */     data.writeByteArray(this.field_149611_c);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void processPacket(INetHandlerLoginClient handler) {
/* 52 */     handler.handleEncryptionRequest(this);
/*    */   }
/*    */ 
/*    */   
/*    */   public String func_149609_c() {
/* 57 */     return this.hashedServerId;
/*    */   }
/*    */ 
/*    */   
/*    */   public PublicKey func_149608_d() {
/* 62 */     return this.publicKey;
/*    */   }
/*    */ 
/*    */   
/*    */   public byte[] func_149607_e() {
/* 67 */     return this.field_149611_c;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void processPacket(INetHandler handler) {
/* 75 */     processPacket((INetHandlerLoginClient)handler);
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\network\login\server\S01PacketEncryptionRequest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */