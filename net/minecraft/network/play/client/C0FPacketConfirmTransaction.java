/*    */ package net.minecraft.network.play.client;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import net.minecraft.network.INetHandler;
/*    */ import net.minecraft.network.Packet;
/*    */ import net.minecraft.network.PacketBuffer;
/*    */ import net.minecraft.network.play.INetHandlerPlayServer;
/*    */ 
/*    */ public class C0FPacketConfirmTransaction
/*    */   implements Packet
/*    */ {
/*    */   private int id;
/*    */   private short uid;
/*    */   private boolean accepted;
/*    */   private static final String __OBFID = "CL_00001351";
/*    */   
/*    */   public C0FPacketConfirmTransaction() {}
/*    */   
/*    */   public C0FPacketConfirmTransaction(int p_i45244_1_, short p_i45244_2_, boolean p_i45244_3_) {
/* 20 */     this.id = p_i45244_1_;
/* 21 */     this.uid = p_i45244_2_;
/* 22 */     this.accepted = p_i45244_3_;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void processPacket(INetHandlerPlayServer handler) {
/* 30 */     handler.processConfirmTransaction(this);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void readPacketData(PacketBuffer data) throws IOException {
/* 38 */     this.id = data.readByte();
/* 39 */     this.uid = data.readShort();
/* 40 */     this.accepted = (data.readByte() != 0);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void writePacketData(PacketBuffer data) throws IOException {
/* 48 */     data.writeByte(this.id);
/* 49 */     data.writeShort(this.uid);
/* 50 */     data.writeByte(this.accepted ? 1 : 0);
/*    */   }
/*    */ 
/*    */   
/*    */   public int getId() {
/* 55 */     return this.id;
/*    */   }
/*    */ 
/*    */   
/*    */   public short getUid() {
/* 60 */     return this.uid;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void processPacket(INetHandler handler) {
/* 68 */     processPacket((INetHandlerPlayServer)handler);
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\network\play\client\C0FPacketConfirmTransaction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */