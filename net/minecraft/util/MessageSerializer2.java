/*    */ package net.minecraft.util;
/*    */ 
/*    */ import io.netty.buffer.ByteBuf;
/*    */ import io.netty.channel.ChannelHandlerContext;
/*    */ import io.netty.handler.codec.MessageToByteEncoder;
/*    */ import net.minecraft.network.PacketBuffer;
/*    */ 
/*    */ public class MessageSerializer2
/*    */   extends MessageToByteEncoder
/*    */ {
/*    */   private static final String __OBFID = "CL_00001256";
/*    */   
/*    */   protected void encode(ChannelHandlerContext p_encode_1_, ByteBuf p_encode_2_, ByteBuf p_encode_3_) {
/* 14 */     int var4 = p_encode_2_.readableBytes();
/* 15 */     int var5 = PacketBuffer.getVarIntSize(var4);
/*    */     
/* 17 */     if (var5 > 3)
/*    */     {
/* 19 */       throw new IllegalArgumentException("unable to fit " + var4 + " into " + '\003');
/*    */     }
/*    */ 
/*    */     
/* 23 */     PacketBuffer var6 = new PacketBuffer(p_encode_3_);
/* 24 */     var6.ensureWritable(var5 + var4);
/* 25 */     var6.writeVarIntToBuffer(var4);
/* 26 */     var6.writeBytes(p_encode_2_, p_encode_2_.readerIndex(), var4);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected void encode(ChannelHandlerContext p_encode_1_, Object p_encode_2_, ByteBuf p_encode_3_) {
/* 32 */     encode(p_encode_1_, (ByteBuf)p_encode_2_, p_encode_3_);
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraf\\util\MessageSerializer2.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */