/*    */ package net.minecraft.util;
/*    */ 
/*    */ import io.netty.buffer.ByteBuf;
/*    */ import io.netty.channel.ChannelHandlerContext;
/*    */ import io.netty.handler.codec.ByteToMessageDecoder;
/*    */ import java.io.IOException;
/*    */ import java.util.List;
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
/*    */ public class MessageDeserializer
/*    */   extends ByteToMessageDecoder {
/* 20 */   private static final Logger logger = LogManager.getLogger();
/* 21 */   private static final Marker RECEIVED_PACKET_MARKER = MarkerManager.getMarker("PACKET_RECEIVED", NetworkManager.logMarkerPackets);
/*    */   
/*    */   private final EnumPacketDirection direction;
/*    */   private static final String __OBFID = "CL_00001252";
/*    */   
/*    */   public MessageDeserializer(EnumPacketDirection direction) {
/* 27 */     this.direction = direction;
/*    */   }
/*    */ 
/*    */   
/*    */   protected void decode(ChannelHandlerContext p_decode_1_, ByteBuf p_decode_2_, List<Packet> p_decode_3_) throws IOException, InstantiationException, IllegalAccessException {
/* 32 */     if (p_decode_2_.readableBytes() != 0) {
/*    */       
/* 34 */       PacketBuffer var4 = new PacketBuffer(p_decode_2_);
/* 35 */       int var5 = var4.readVarIntFromBuffer();
/* 36 */       Packet var6 = ((EnumConnectionState)p_decode_1_.channel().attr(NetworkManager.attrKeyConnectionState).get()).getPacket(this.direction, var5);
/*    */       
/* 38 */       if (var6 == null)
/*    */       {
/* 40 */         throw new IOException("Bad packet id " + var5);
/*    */       }
/*    */ 
/*    */       
/* 44 */       var6.readPacketData(var4);
/*    */       
/* 46 */       if (var4.readableBytes() > 0)
/*    */       {
/* 48 */         throw new IOException("Packet " + ((EnumConnectionState)p_decode_1_.channel().attr(NetworkManager.attrKeyConnectionState).get()).getId() + "/" + var5 + " (" + var6.getClass().getSimpleName() + ") was larger than I expected, found " + var4.readableBytes() + " bytes extra whilst reading packet " + var5);
/*    */       }
/*    */ 
/*    */       
/* 52 */       p_decode_3_.add(var6);
/*    */       
/* 54 */       if (logger.isDebugEnabled())
/*    */       {
/* 56 */         logger.debug(RECEIVED_PACKET_MARKER, " IN: [{}:{}] {}", new Object[] { p_decode_1_.channel().attr(NetworkManager.attrKeyConnectionState).get(), Integer.valueOf(var5), var6.getClass().getName() });
/*    */       }
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraf\\util\MessageDeserializer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */