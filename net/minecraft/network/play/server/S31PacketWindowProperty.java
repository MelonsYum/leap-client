/*    */ package net.minecraft.network.play.server;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import net.minecraft.network.INetHandler;
/*    */ import net.minecraft.network.Packet;
/*    */ import net.minecraft.network.PacketBuffer;
/*    */ import net.minecraft.network.play.INetHandlerPlayClient;
/*    */ 
/*    */ public class S31PacketWindowProperty
/*    */   implements Packet
/*    */ {
/*    */   private int field_149186_a;
/*    */   private int field_149184_b;
/*    */   private int field_149185_c;
/*    */   private static final String __OBFID = "CL_00001295";
/*    */   
/*    */   public S31PacketWindowProperty() {}
/*    */   
/*    */   public S31PacketWindowProperty(int p_i45187_1_, int p_i45187_2_, int p_i45187_3_) {
/* 20 */     this.field_149186_a = p_i45187_1_;
/* 21 */     this.field_149184_b = p_i45187_2_;
/* 22 */     this.field_149185_c = p_i45187_3_;
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_180733_a(INetHandlerPlayClient p_180733_1_) {
/* 27 */     p_180733_1_.handleWindowProperty(this);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void readPacketData(PacketBuffer data) throws IOException {
/* 35 */     this.field_149186_a = data.readUnsignedByte();
/* 36 */     this.field_149184_b = data.readShort();
/* 37 */     this.field_149185_c = data.readShort();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void writePacketData(PacketBuffer data) throws IOException {
/* 45 */     data.writeByte(this.field_149186_a);
/* 46 */     data.writeShort(this.field_149184_b);
/* 47 */     data.writeShort(this.field_149185_c);
/*    */   }
/*    */ 
/*    */   
/*    */   public int func_149182_c() {
/* 52 */     return this.field_149186_a;
/*    */   }
/*    */ 
/*    */   
/*    */   public int func_149181_d() {
/* 57 */     return this.field_149184_b;
/*    */   }
/*    */ 
/*    */   
/*    */   public int func_149180_e() {
/* 62 */     return this.field_149185_c;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void processPacket(INetHandler handler) {
/* 70 */     func_180733_a((INetHandlerPlayClient)handler);
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\network\play\server\S31PacketWindowProperty.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */