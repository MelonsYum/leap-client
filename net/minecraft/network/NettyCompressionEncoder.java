/*    */ package net.minecraft.network;
/*    */ 
/*    */ import io.netty.buffer.ByteBuf;
/*    */ import io.netty.channel.ChannelHandlerContext;
/*    */ import io.netty.handler.codec.MessageToByteEncoder;
/*    */ import java.util.zip.Deflater;
/*    */ 
/*    */ public class NettyCompressionEncoder
/*    */   extends MessageToByteEncoder {
/* 10 */   private final byte[] buffer = new byte[8192];
/*    */   
/*    */   private final Deflater deflater;
/*    */   private int treshold;
/*    */   private static final String __OBFID = "CL_00002313";
/*    */   
/*    */   public NettyCompressionEncoder(int treshold) {
/* 17 */     this.treshold = treshold;
/* 18 */     this.deflater = new Deflater();
/*    */   }
/*    */ 
/*    */   
/*    */   protected void compress(ChannelHandlerContext ctx, ByteBuf input, ByteBuf output) {
/* 23 */     int var4 = input.readableBytes();
/* 24 */     PacketBuffer var5 = new PacketBuffer(output);
/*    */     
/* 26 */     if (var4 < this.treshold) {
/*    */       
/* 28 */       var5.writeVarIntToBuffer(0);
/* 29 */       var5.writeBytes(input);
/*    */     }
/*    */     else {
/*    */       
/* 33 */       byte[] var6 = new byte[var4];
/* 34 */       input.readBytes(var6);
/* 35 */       var5.writeVarIntToBuffer(var6.length);
/* 36 */       this.deflater.setInput(var6, 0, var4);
/* 37 */       this.deflater.finish();
/*    */       
/* 39 */       while (!this.deflater.finished()) {
/*    */         
/* 41 */         int var7 = this.deflater.deflate(this.buffer);
/* 42 */         var5.writeBytes(this.buffer, 0, var7);
/*    */       } 
/*    */       
/* 45 */       this.deflater.reset();
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void setCompressionTreshold(int treshold) {
/* 51 */     this.treshold = treshold;
/*    */   }
/*    */ 
/*    */   
/*    */   protected void encode(ChannelHandlerContext p_encode_1_, Object p_encode_2_, ByteBuf p_encode_3_) {
/* 56 */     compress(p_encode_1_, (ByteBuf)p_encode_2_, p_encode_3_);
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\network\NettyCompressionEncoder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */