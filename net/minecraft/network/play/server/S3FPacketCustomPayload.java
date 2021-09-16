/*    */ package net.minecraft.network.play.server;
/*    */ 
/*    */ import io.netty.buffer.ByteBuf;
/*    */ import java.io.IOException;
/*    */ import net.minecraft.network.INetHandler;
/*    */ import net.minecraft.network.Packet;
/*    */ import net.minecraft.network.PacketBuffer;
/*    */ import net.minecraft.network.play.INetHandlerPlayClient;
/*    */ 
/*    */ public class S3FPacketCustomPayload
/*    */   implements Packet
/*    */ {
/*    */   private String channel;
/*    */   private PacketBuffer data;
/*    */   private static final String __OBFID = "CL_00001297";
/*    */   
/*    */   public S3FPacketCustomPayload() {}
/*    */   
/*    */   public S3FPacketCustomPayload(String channelName, PacketBuffer dataIn) {
/* 20 */     this.channel = channelName;
/* 21 */     this.data = dataIn;
/*    */     
/* 23 */     if (dataIn.writerIndex() > 1048576)
/*    */     {
/* 25 */       throw new IllegalArgumentException("Payload may not be larger than 1048576 bytes");
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
/* 37 */     if (var2 >= 0 && var2 <= 1048576) {
/*    */       
/* 39 */       this.data = new PacketBuffer(data.readBytes(var2));
/*    */     }
/*    */     else {
/*    */       
/* 43 */       throw new IOException("Payload may not be larger than 1048576 bytes");
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
/*    */   public void process(INetHandlerPlayClient p_180734_1_) {
/* 58 */     p_180734_1_.handleCustomPayload(this);
/*    */   }
/*    */ 
/*    */   
/*    */   public String getChannelName() {
/* 63 */     return this.channel;
/*    */   }
/*    */ 
/*    */   
/*    */   public PacketBuffer getBufferData() {
/* 68 */     return this.data;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void processPacket(INetHandler handler) {
/* 76 */     process((INetHandlerPlayClient)handler);
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\network\play\server\S3FPacketCustomPayload.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */