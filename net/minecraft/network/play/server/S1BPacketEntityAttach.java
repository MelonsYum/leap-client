/*    */ package net.minecraft.network.play.server;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.network.INetHandler;
/*    */ import net.minecraft.network.Packet;
/*    */ import net.minecraft.network.PacketBuffer;
/*    */ import net.minecraft.network.play.INetHandlerPlayClient;
/*    */ 
/*    */ public class S1BPacketEntityAttach
/*    */   implements Packet
/*    */ {
/*    */   private int field_149408_a;
/*    */   private int field_149406_b;
/*    */   private int field_149407_c;
/*    */   private static final String __OBFID = "CL_00001327";
/*    */   
/*    */   public S1BPacketEntityAttach() {}
/*    */   
/*    */   public S1BPacketEntityAttach(int p_i45218_1_, Entity p_i45218_2_, Entity p_i45218_3_) {
/* 21 */     this.field_149408_a = p_i45218_1_;
/* 22 */     this.field_149406_b = p_i45218_2_.getEntityId();
/* 23 */     this.field_149407_c = (p_i45218_3_ != null) ? p_i45218_3_.getEntityId() : -1;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void readPacketData(PacketBuffer data) throws IOException {
/* 31 */     this.field_149406_b = data.readInt();
/* 32 */     this.field_149407_c = data.readInt();
/* 33 */     this.field_149408_a = data.readUnsignedByte();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void writePacketData(PacketBuffer data) throws IOException {
/* 41 */     data.writeInt(this.field_149406_b);
/* 42 */     data.writeInt(this.field_149407_c);
/* 43 */     data.writeByte(this.field_149408_a);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void processPacket(INetHandlerPlayClient handler) {
/* 51 */     handler.handleEntityAttach(this);
/*    */   }
/*    */ 
/*    */   
/*    */   public int func_149404_c() {
/* 56 */     return this.field_149408_a;
/*    */   }
/*    */ 
/*    */   
/*    */   public int func_149403_d() {
/* 61 */     return this.field_149406_b;
/*    */   }
/*    */ 
/*    */   
/*    */   public int func_149402_e() {
/* 66 */     return this.field_149407_c;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void processPacket(INetHandler handler) {
/* 74 */     processPacket((INetHandlerPlayClient)handler);
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\network\play\server\S1BPacketEntityAttach.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */