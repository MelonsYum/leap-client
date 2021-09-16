/*    */ package net.minecraft.network.play.server;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import net.minecraft.network.INetHandler;
/*    */ import net.minecraft.network.Packet;
/*    */ import net.minecraft.network.PacketBuffer;
/*    */ import net.minecraft.network.play.INetHandlerPlayClient;
/*    */ 
/*    */ public class S09PacketHeldItemChange
/*    */   implements Packet
/*    */ {
/*    */   private int field_149387_a;
/*    */   private static final String __OBFID = "CL_00001324";
/*    */   
/*    */   public S09PacketHeldItemChange() {}
/*    */   
/*    */   public S09PacketHeldItemChange(int p_i45215_1_) {
/* 18 */     this.field_149387_a = p_i45215_1_;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void readPacketData(PacketBuffer data) throws IOException {
/* 26 */     this.field_149387_a = data.readByte();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void writePacketData(PacketBuffer data) throws IOException {
/* 34 */     data.writeByte(this.field_149387_a);
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_180746_a(INetHandlerPlayClient p_180746_1_) {
/* 39 */     p_180746_1_.handleHeldItemChange(this);
/*    */   }
/*    */ 
/*    */   
/*    */   public int func_149385_c() {
/* 44 */     return this.field_149387_a;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void processPacket(INetHandler handler) {
/* 52 */     func_180746_a((INetHandlerPlayClient)handler);
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\network\play\server\S09PacketHeldItemChange.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */