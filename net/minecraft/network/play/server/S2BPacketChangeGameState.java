/*    */ package net.minecraft.network.play.server;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import net.minecraft.network.INetHandler;
/*    */ import net.minecraft.network.Packet;
/*    */ import net.minecraft.network.PacketBuffer;
/*    */ import net.minecraft.network.play.INetHandlerPlayClient;
/*    */ 
/*    */ public class S2BPacketChangeGameState
/*    */   implements Packet {
/* 11 */   public static final String[] MESSAGE_NAMES = new String[] { "tile.bed.notValid" };
/*    */   
/*    */   private int state;
/*    */   private float field_149141_c;
/*    */   private static final String __OBFID = "CL_00001301";
/*    */   
/*    */   public S2BPacketChangeGameState() {}
/*    */   
/*    */   public S2BPacketChangeGameState(int stateIn, float p_i45194_2_) {
/* 20 */     this.state = stateIn;
/* 21 */     this.field_149141_c = p_i45194_2_;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void readPacketData(PacketBuffer data) throws IOException {
/* 29 */     this.state = data.readUnsignedByte();
/* 30 */     this.field_149141_c = data.readFloat();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void writePacketData(PacketBuffer data) throws IOException {
/* 38 */     data.writeByte(this.state);
/* 39 */     data.writeFloat(this.field_149141_c);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void processPacket(INetHandlerPlayClient handler) {
/* 47 */     handler.handleChangeGameState(this);
/*    */   }
/*    */ 
/*    */   
/*    */   public int func_149138_c() {
/* 52 */     return this.state;
/*    */   }
/*    */ 
/*    */   
/*    */   public float func_149137_d() {
/* 57 */     return this.field_149141_c;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void processPacket(INetHandler handler) {
/* 65 */     processPacket((INetHandlerPlayClient)handler);
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\network\play\server\S2BPacketChangeGameState.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */