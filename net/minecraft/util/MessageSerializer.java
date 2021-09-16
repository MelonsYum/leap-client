/*    */ package net.minecraft.util;
/*    */ 
/*    */ import io.netty.buffer.ByteBuf;
/*    */ import io.netty.channel.ChannelHandlerContext;
/*    */ import io.netty.handler.codec.MessageToByteEncoder;
/*    */ import java.io.IOException;
/*    */ import net.minecraft.network.EnumConnectionState;
/*    */ import net.minecraft.network.EnumPacketDirection;
/*    */ import net.minecraft.network.NetworkManager;
/*    */ import net.minecraft.network.Packet;
/*    */ import net.minecraft.network.PacketBuffer;
/*    */ import org.apache.logging.log4j.LogManager;
/*    */ import org.apache.logging.log4j.Logger;
/*    */ import org.apache.logging.log4j.Marker;
/*    */ import org.apache.logging.log4j.MarkerManager;
/*    */ 
/*    */ public class MessageSerializer
/*    */   extends MessageToByteEncoder
/*    */ {
/* 20 */   private static final Logger logger = LogManager.getLogger();
/* 21 */   private static final Marker RECEIVED_PACKET_MARKER = MarkerManager.getMarker("PACKET_SENT", NetworkManager.logMarkerPackets);
/*    */   
/*    */   private final EnumPacketDirection direction;
/*    */   private static final String __OBFID = "CL_00001253";
/*    */   
/*    */   public MessageSerializer(EnumPacketDirection direction) {
/* 27 */     this.direction = direction;
/*    */   }
/*    */ 
/*    */   
/*    */   protected void encode(ChannelHandlerContext p_encode_1_, Packet p_encode_2_, ByteBuf p_encode_3_) throws IOException {
/* 32 */     Integer var4 = ((EnumConnectionState)p_encode_1_.channel().attr(NetworkManager.attrKeyConnectionState).get()).getPacketId(this.direction, p_encode_2_);
/*    */     
/* 34 */     if (logger.isDebugEnabled())
/*    */     {
/* 36 */       logger.debug(RECEIVED_PACKET_MARKER, "OUT: [{}:{}] {}", new Object[] { p_encode_1_.channel().attr(NetworkManager.attrKeyConnectionState).get(), var4, p_encode_2_.getClass().getName() });
/*    */     }
/*    */     
/* 39 */     if (var4 == null)
/*    */     {
/* 41 */       throw new IOException("Can't serialize unregistered packet");
/*    */     }
/*    */ 
/*    */     
/* 45 */     PacketBuffer var5 = new PacketBuffer(p_encode_3_);
/* 46 */     var5.writeVarIntToBuffer(var4.intValue());
/*    */ 
/*    */     
/*    */     try {
/* 50 */       if (p_encode_2_ instanceof net.minecraft.network.play.server.S0CPacketSpawnPlayer)
/*    */       {
/* 52 */         p_encode_2_ = p_encode_2_;
/*    */       }
/*    */       
/* 55 */       p_encode_2_.writePacketData(var5);
/*    */     }
/* 57 */     catch (Throwable var7) {
/*    */       
/* 59 */       logger.error(var7);
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected void encode(ChannelHandlerContext p_encode_1_, Object p_encode_2_, ByteBuf p_encode_3_) throws IOException {
/* 66 */     encode(p_encode_1_, (Packet)p_encode_2_, p_encode_3_);
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraf\\util\MessageSerializer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */