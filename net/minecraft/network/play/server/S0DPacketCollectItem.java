/*    */ package net.minecraft.network.play.server;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import net.minecraft.network.INetHandler;
/*    */ import net.minecraft.network.Packet;
/*    */ import net.minecraft.network.PacketBuffer;
/*    */ import net.minecraft.network.play.INetHandlerPlayClient;
/*    */ 
/*    */ public class S0DPacketCollectItem
/*    */   implements Packet
/*    */ {
/*    */   private int field_149357_a;
/*    */   private int field_149356_b;
/*    */   private static final String __OBFID = "CL_00001339";
/*    */   
/*    */   public S0DPacketCollectItem() {}
/*    */   
/*    */   public S0DPacketCollectItem(int p_i45232_1_, int p_i45232_2_) {
/* 19 */     this.field_149357_a = p_i45232_1_;
/* 20 */     this.field_149356_b = p_i45232_2_;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void readPacketData(PacketBuffer data) throws IOException {
/* 28 */     this.field_149357_a = data.readVarIntFromBuffer();
/* 29 */     this.field_149356_b = data.readVarIntFromBuffer();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void writePacketData(PacketBuffer data) throws IOException {
/* 37 */     data.writeVarIntToBuffer(this.field_149357_a);
/* 38 */     data.writeVarIntToBuffer(this.field_149356_b);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void processPacket(INetHandlerPlayClient handler) {
/* 46 */     handler.handleCollectItem(this);
/*    */   }
/*    */ 
/*    */   
/*    */   public int func_149354_c() {
/* 51 */     return this.field_149357_a;
/*    */   }
/*    */ 
/*    */   
/*    */   public int func_149353_d() {
/* 56 */     return this.field_149356_b;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void processPacket(INetHandler handler) {
/* 64 */     processPacket((INetHandlerPlayClient)handler);
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\network\play\server\S0DPacketCollectItem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */