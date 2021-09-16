/*    */ package net.minecraft.network;
/*    */ 
/*    */ import io.netty.buffer.ByteBuf;
/*    */ import io.netty.channel.ChannelHandlerContext;
/*    */ import javax.crypto.Cipher;
/*    */ import javax.crypto.ShortBufferException;
/*    */ 
/*    */ public class NettyEncryptionTranslator
/*    */ {
/*    */   private final Cipher cipher;
/* 11 */   private byte[] field_150505_b = new byte[0];
/* 12 */   private byte[] field_150506_c = new byte[0];
/*    */   
/*    */   private static final String __OBFID = "CL_00001237";
/*    */   
/*    */   protected NettyEncryptionTranslator(Cipher cipherIn) {
/* 17 */     this.cipher = cipherIn;
/*    */   }
/*    */ 
/*    */   
/*    */   private byte[] func_150502_a(ByteBuf p_150502_1_) {
/* 22 */     int var2 = p_150502_1_.readableBytes();
/*    */     
/* 24 */     if (this.field_150505_b.length < var2)
/*    */     {
/* 26 */       this.field_150505_b = new byte[var2];
/*    */     }
/*    */     
/* 29 */     p_150502_1_.readBytes(this.field_150505_b, 0, var2);
/* 30 */     return this.field_150505_b;
/*    */   }
/*    */ 
/*    */   
/*    */   protected ByteBuf decipher(ChannelHandlerContext ctx, ByteBuf buffer) throws ShortBufferException {
/* 35 */     int var3 = buffer.readableBytes();
/* 36 */     byte[] var4 = func_150502_a(buffer);
/* 37 */     ByteBuf var5 = ctx.alloc().heapBuffer(this.cipher.getOutputSize(var3));
/* 38 */     var5.writerIndex(this.cipher.update(var4, 0, var3, var5.array(), var5.arrayOffset()));
/* 39 */     return var5;
/*    */   }
/*    */ 
/*    */   
/*    */   protected void cipher(ByteBuf p_150504_1_, ByteBuf p_150504_2_) throws ShortBufferException {
/* 44 */     int var3 = p_150504_1_.readableBytes();
/* 45 */     byte[] var4 = func_150502_a(p_150504_1_);
/* 46 */     int var5 = this.cipher.getOutputSize(var3);
/*    */     
/* 48 */     if (this.field_150506_c.length < var5)
/*    */     {
/* 50 */       this.field_150506_c = new byte[var5];
/*    */     }
/*    */     
/* 53 */     p_150504_2_.writeBytes(this.field_150506_c, 0, this.cipher.update(var4, 0, var3, this.field_150506_c));
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\network\NettyEncryptionTranslator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */