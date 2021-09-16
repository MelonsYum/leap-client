/*    */ package net.minecraft.network.play.server;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import net.minecraft.network.INetHandler;
/*    */ import net.minecraft.network.Packet;
/*    */ import net.minecraft.network.PacketBuffer;
/*    */ import net.minecraft.network.play.INetHandlerPlayClient;
/*    */ 
/*    */ public class S32PacketConfirmTransaction
/*    */   implements Packet
/*    */ {
/*    */   private int field_148894_a;
/*    */   private short field_148892_b;
/*    */   private boolean field_148893_c;
/*    */   private static final String __OBFID = "CL_00001291";
/*    */   
/*    */   public S32PacketConfirmTransaction() {}
/*    */   
/*    */   public S32PacketConfirmTransaction(int p_i45182_1_, short p_i45182_2_, boolean p_i45182_3_) {
/* 20 */     this.field_148894_a = p_i45182_1_;
/* 21 */     this.field_148892_b = p_i45182_2_;
/* 22 */     this.field_148893_c = p_i45182_3_;
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_180730_a(INetHandlerPlayClient p_180730_1_) {
/* 27 */     p_180730_1_.handleConfirmTransaction(this);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void readPacketData(PacketBuffer data) throws IOException {
/* 35 */     this.field_148894_a = data.readUnsignedByte();
/* 36 */     this.field_148892_b = data.readShort();
/* 37 */     this.field_148893_c = data.readBoolean();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void writePacketData(PacketBuffer data) throws IOException {
/* 45 */     data.writeByte(this.field_148894_a);
/* 46 */     data.writeShort(this.field_148892_b);
/* 47 */     data.writeBoolean(this.field_148893_c);
/*    */   }
/*    */ 
/*    */   
/*    */   public int func_148889_c() {
/* 52 */     return this.field_148894_a;
/*    */   }
/*    */ 
/*    */   
/*    */   public short func_148890_d() {
/* 57 */     return this.field_148892_b;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean func_148888_e() {
/* 62 */     return this.field_148893_c;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void processPacket(INetHandler handler) {
/* 70 */     func_180730_a((INetHandlerPlayClient)handler);
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\network\play\server\S32PacketConfirmTransaction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */