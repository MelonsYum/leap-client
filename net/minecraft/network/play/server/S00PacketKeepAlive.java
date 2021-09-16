/*    */ package net.minecraft.network.play.server;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import net.minecraft.network.INetHandler;
/*    */ import net.minecraft.network.Packet;
/*    */ import net.minecraft.network.PacketBuffer;
/*    */ import net.minecraft.network.play.INetHandlerPlayClient;
/*    */ 
/*    */ public class S00PacketKeepAlive
/*    */   implements Packet
/*    */ {
/*    */   private int field_149136_a;
/*    */   private static final String __OBFID = "CL_00001303";
/*    */   
/*    */   public S00PacketKeepAlive() {}
/*    */   
/*    */   public S00PacketKeepAlive(int p_i45195_1_) {
/* 18 */     this.field_149136_a = p_i45195_1_;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void processPacket(INetHandlerPlayClient handler) {
/* 26 */     handler.handleKeepAlive(this);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void readPacketData(PacketBuffer data) throws IOException {
/* 34 */     this.field_149136_a = data.readVarIntFromBuffer();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void writePacketData(PacketBuffer data) throws IOException {
/* 42 */     data.writeVarIntToBuffer(this.field_149136_a);
/*    */   }
/*    */ 
/*    */   
/*    */   public int func_149134_c() {
/* 47 */     return this.field_149136_a;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void processPacket(INetHandler handler) {
/* 55 */     processPacket((INetHandlerPlayClient)handler);
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\network\play\server\S00PacketKeepAlive.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */