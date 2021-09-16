/*    */ package net.minecraft.util;
/*    */ 
/*    */ import io.netty.buffer.ByteBuf;
/*    */ import io.netty.buffer.Unpooled;
/*    */ import io.netty.channel.ChannelHandlerContext;
/*    */ import io.netty.handler.codec.ByteToMessageDecoder;
/*    */ import io.netty.handler.codec.CorruptedFrameException;
/*    */ import java.util.List;
/*    */ import net.minecraft.network.PacketBuffer;
/*    */ 
/*    */ public class MessageDeserializer2
/*    */   extends ByteToMessageDecoder
/*    */ {
/*    */   private static final String __OBFID = "CL_00001255";
/*    */   
/*    */   protected void decode(ChannelHandlerContext p_decode_1_, ByteBuf p_decode_2_, List<ByteBuf> p_decode_3_) {
/* 17 */     p_decode_2_.markReaderIndex();
/* 18 */     byte[] var4 = new byte[3];
/*    */     
/* 20 */     for (int var5 = 0; var5 < var4.length; var5++) {
/*    */       
/* 22 */       if (!p_decode_2_.isReadable()) {
/*    */         
/* 24 */         p_decode_2_.resetReaderIndex();
/*    */         
/*    */         return;
/*    */       } 
/* 28 */       var4[var5] = p_decode_2_.readByte();
/*    */       
/* 30 */       if (var4[var5] >= 0) {
/*    */         
/* 32 */         PacketBuffer var6 = new PacketBuffer(Unpooled.wrappedBuffer(var4));
/*    */ 
/*    */         
/*    */         try {
/* 36 */           int var7 = var6.readVarIntFromBuffer();
/*    */           
/* 38 */           if (p_decode_2_.readableBytes() >= var7) {
/*    */             
/* 40 */             p_decode_3_.add(p_decode_2_.readBytes(var7));
/*    */             
/*    */             return;
/*    */           } 
/* 44 */           p_decode_2_.resetReaderIndex();
/*    */         }
/*    */         finally {
/*    */           
/* 48 */           var6.release();
/*    */         } 
/*    */         
/*    */         return;
/*    */       } 
/*    */     } 
/*    */     
/* 55 */     throw new CorruptedFrameException("length wider than 21-bit");
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraf\\util\MessageDeserializer2.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */