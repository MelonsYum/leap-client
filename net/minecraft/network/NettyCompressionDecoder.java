/*    */ package net.minecraft.network;
/*    */ 
/*    */ import io.netty.buffer.ByteBuf;
/*    */ import io.netty.buffer.Unpooled;
/*    */ import io.netty.channel.ChannelHandlerContext;
/*    */ import io.netty.handler.codec.ByteToMessageDecoder;
/*    */ import io.netty.handler.codec.DecoderException;
/*    */ import java.util.List;
/*    */ import java.util.zip.DataFormatException;
/*    */ import java.util.zip.Inflater;
/*    */ 
/*    */ public class NettyCompressionDecoder
/*    */   extends ByteToMessageDecoder
/*    */ {
/*    */   private final Inflater inflater;
/*    */   private int treshold;
/*    */   private static final String __OBFID = "CL_00002314";
/*    */   
/*    */   public NettyCompressionDecoder(int treshold) {
/* 20 */     this.treshold = treshold;
/* 21 */     this.inflater = new Inflater();
/*    */   }
/*    */ 
/*    */   
/*    */   protected void decode(ChannelHandlerContext p_decode_1_, ByteBuf p_decode_2_, List<ByteBuf> p_decode_3_) throws DataFormatException {
/* 26 */     if (p_decode_2_.readableBytes() != 0) {
/*    */       
/* 28 */       PacketBuffer var4 = new PacketBuffer(p_decode_2_);
/* 29 */       int var5 = var4.readVarIntFromBuffer();
/*    */       
/* 31 */       if (var5 == 0) {
/*    */         
/* 33 */         p_decode_3_.add(var4.readBytes(var4.readableBytes()));
/*    */       }
/*    */       else {
/*    */         
/* 37 */         if (var5 < this.treshold)
/*    */         {
/* 39 */           throw new DecoderException("Badly compressed packet - size of " + var5 + " is below server threshold of " + this.treshold);
/*    */         }
/*    */         
/* 42 */         if (var5 > 2097152)
/*    */         {
/* 44 */           throw new DecoderException("Badly compressed packet - size of " + var5 + " is larger than protocol maximum of " + 2097152);
/*    */         }
/*    */         
/* 47 */         byte[] var6 = new byte[var4.readableBytes()];
/* 48 */         var4.readBytes(var6);
/* 49 */         this.inflater.setInput(var6);
/* 50 */         byte[] var7 = new byte[var5];
/* 51 */         this.inflater.inflate(var7);
/* 52 */         p_decode_3_.add(Unpooled.wrappedBuffer(var7));
/* 53 */         this.inflater.reset();
/*    */       } 
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void setCompressionTreshold(int treshold) {
/* 60 */     this.treshold = treshold;
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\network\NettyCompressionDecoder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */