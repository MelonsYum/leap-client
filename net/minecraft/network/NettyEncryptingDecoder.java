/*    */ package net.minecraft.network;
/*    */ 
/*    */ import io.netty.buffer.ByteBuf;
/*    */ import io.netty.channel.ChannelHandlerContext;
/*    */ import io.netty.handler.codec.MessageToMessageDecoder;
/*    */ import java.util.List;
/*    */ import javax.crypto.Cipher;
/*    */ import javax.crypto.ShortBufferException;
/*    */ 
/*    */ public class NettyEncryptingDecoder
/*    */   extends MessageToMessageDecoder
/*    */ {
/*    */   private final NettyEncryptionTranslator decryptionCodec;
/*    */   private static final String __OBFID = "CL_00001238";
/*    */   
/*    */   public NettyEncryptingDecoder(Cipher cipher) {
/* 17 */     this.decryptionCodec = new NettyEncryptionTranslator(cipher);
/*    */   }
/*    */ 
/*    */   
/*    */   protected void decode(ChannelHandlerContext p_decode_1_, ByteBuf p_decode_2_, List<ByteBuf> p_decode_3_) throws ShortBufferException {
/* 22 */     p_decode_3_.add(this.decryptionCodec.decipher(p_decode_1_, p_decode_2_));
/*    */   }
/*    */ 
/*    */   
/*    */   protected void decode(ChannelHandlerContext p_decode_1_, Object p_decode_2_, List p_decode_3_) throws ShortBufferException {
/* 27 */     decode(p_decode_1_, (ByteBuf)p_decode_2_, p_decode_3_);
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\network\NettyEncryptingDecoder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */