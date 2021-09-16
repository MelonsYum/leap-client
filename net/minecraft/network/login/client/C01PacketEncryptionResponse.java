/*    */ package net.minecraft.network.login.client;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.security.PrivateKey;
/*    */ import java.security.PublicKey;
/*    */ import javax.crypto.SecretKey;
/*    */ import net.minecraft.network.INetHandler;
/*    */ import net.minecraft.network.Packet;
/*    */ import net.minecraft.network.PacketBuffer;
/*    */ import net.minecraft.network.login.INetHandlerLoginServer;
/*    */ import net.minecraft.util.CryptManager;
/*    */ 
/*    */ public class C01PacketEncryptionResponse
/*    */   implements Packet {
/* 15 */   private byte[] field_149302_a = new byte[0];
/* 16 */   private byte[] field_149301_b = new byte[0];
/*    */   
/*    */   private static final String __OBFID = "CL_00001380";
/*    */   
/*    */   public C01PacketEncryptionResponse() {}
/*    */   
/*    */   public C01PacketEncryptionResponse(SecretKey p_i45271_1_, PublicKey p_i45271_2_, byte[] p_i45271_3_) {
/* 23 */     this.field_149302_a = CryptManager.encryptData(p_i45271_2_, p_i45271_1_.getEncoded());
/* 24 */     this.field_149301_b = CryptManager.encryptData(p_i45271_2_, p_i45271_3_);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void readPacketData(PacketBuffer data) throws IOException {
/* 32 */     this.field_149302_a = data.readByteArray();
/* 33 */     this.field_149301_b = data.readByteArray();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void writePacketData(PacketBuffer data) throws IOException {
/* 41 */     data.writeByteArray(this.field_149302_a);
/* 42 */     data.writeByteArray(this.field_149301_b);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void processPacket(INetHandlerLoginServer handler) {
/* 50 */     handler.processEncryptionResponse(this);
/*    */   }
/*    */ 
/*    */   
/*    */   public SecretKey func_149300_a(PrivateKey key) {
/* 55 */     return CryptManager.decryptSharedKey(key, this.field_149302_a);
/*    */   }
/*    */ 
/*    */   
/*    */   public byte[] func_149299_b(PrivateKey p_149299_1_) {
/* 60 */     return (p_149299_1_ == null) ? this.field_149301_b : CryptManager.decryptData(p_149299_1_, this.field_149301_b);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void processPacket(INetHandler handler) {
/* 68 */     processPacket((INetHandlerLoginServer)handler);
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\network\login\client\C01PacketEncryptionResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */