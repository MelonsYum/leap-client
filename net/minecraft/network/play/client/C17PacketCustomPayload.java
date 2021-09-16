/*    */ package net.minecraft.network.play.client;
/*    */ 
/*    */ import io.netty.buffer.ByteBuf;
/*    */ import java.io.IOException;
/*    */ import net.minecraft.network.INetHandler;
/*    */ import net.minecraft.network.Packet;
/*    */ import net.minecraft.network.PacketBuffer;
/*    */ import net.minecraft.network.play.INetHandlerPlayServer;
/*    */ 
/*    */ public class C17PacketCustomPayload
/*    */   implements Packet
/*    */ {
/*    */   private String channel;
/*    */   private PacketBuffer data;
/*    */   private static final String __OBFID = "CL_00001356";
/*    */   
/*    */   public C17PacketCustomPayload() {}
/*    */   
/*    */   public C17PacketCustomPayload(String p_i45945_1_, PacketBuffer p_i45945_2_) {
/* 20 */     this.channel = p_i45945_1_;
/* 21 */     this.data = p_i45945_2_;
/*    */     
/* 23 */     if (p_i45945_2_.writerIndex() > 32767)
/*    */     {
/* 25 */       throw new IllegalArgumentException("Payload may not be larger than 32767 bytes");
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void readPacketData(PacketBuffer data) throws IOException {
/* 34 */     this.channel = data.readStringFromBuffer(20);
/* 35 */     int var2 = data.readableBytes();
/*    */     
/* 37 */     if (var2 >= 0 && var2 <= 32767) {
/*    */       
/* 39 */       this.data = new PacketBuffer(data.readBytes(var2));
/*    */     }
/*    */     else {
/*    */       
/* 43 */       throw new IOException("Payload may not be larger than 32767 bytes");
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void writePacketData(PacketBuffer data) throws IOException {
/* 52 */     data.writeString(this.channel);
/* 53 */     data.writeBytes((ByteBuf)this.data);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void processPacket(INetHandlerPlayServer handler) {
/* 61 */     handler.processVanilla250Packet(this);
/*    */   }
/*    */ 
/*    */   
/*    */   public String getChannelName() {
/* 66 */     return this.channel;
/*    */   }
/*    */ 
/*    */   
/*    */   public PacketBuffer getBufferData() {
/* 71 */     return this.data;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void processPacket(INetHandler handler) {
/* 79 */     processPacket((INetHandlerPlayServer)handler);
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\network\play\client\C17PacketCustomPayload.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */