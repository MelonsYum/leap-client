/*    */ package net.minecraft.network.play.server;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import net.minecraft.network.INetHandler;
/*    */ import net.minecraft.network.Packet;
/*    */ import net.minecraft.network.PacketBuffer;
/*    */ import net.minecraft.network.play.INetHandlerPlayClient;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class S46PacketSetCompressionLevel
/*    */   implements Packet
/*    */ {
/*    */   private int field_179761_a;
/*    */   private static final String __OBFID = "CL_00002300";
/*    */   
/*    */   public void readPacketData(PacketBuffer data) throws IOException {
/* 19 */     this.field_179761_a = data.readVarIntFromBuffer();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void writePacketData(PacketBuffer data) throws IOException {
/* 27 */     data.writeVarIntToBuffer(this.field_179761_a);
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_179759_a(INetHandlerPlayClient p_179759_1_) {
/* 32 */     p_179759_1_.func_175100_a(this);
/*    */   }
/*    */ 
/*    */   
/*    */   public int func_179760_a() {
/* 37 */     return this.field_179761_a;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void processPacket(INetHandler handler) {
/* 45 */     func_179759_a((INetHandlerPlayClient)handler);
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\network\play\server\S46PacketSetCompressionLevel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */