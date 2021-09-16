/*     */ package net.minecraft.network;
/*     */ 
/*     */ import com.google.common.base.Charsets;
/*     */ import io.netty.buffer.ByteBuf;
/*     */ import io.netty.buffer.Unpooled;
/*     */ import io.netty.channel.ChannelFutureListener;
/*     */ import io.netty.channel.ChannelHandlerContext;
/*     */ import io.netty.channel.ChannelInboundHandlerAdapter;
/*     */ import io.netty.util.concurrent.GenericFutureListener;
/*     */ import java.net.InetSocketAddress;
/*     */ import net.minecraft.server.MinecraftServer;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ 
/*     */ public class PingResponseHandler extends ChannelInboundHandlerAdapter {
/*  16 */   private static final Logger logger = LogManager.getLogger();
/*     */   
/*     */   private NetworkSystem networkSystem;
/*     */   private static final String __OBFID = "CL_00001444";
/*     */   
/*     */   public PingResponseHandler(NetworkSystem networkSystemIn) {
/*  22 */     this.networkSystem = networkSystemIn;
/*     */   }
/*     */ 
/*     */   
/*     */   public void channelRead(ChannelHandlerContext p_channelRead_1_, Object p_channelRead_2_) {
/*  27 */     ByteBuf var3 = (ByteBuf)p_channelRead_2_;
/*  28 */     var3.markReaderIndex();
/*  29 */     boolean var4 = true; 
/*     */     try { String var8; boolean var23; int j;
/*     */       boolean bool1;
/*     */       int i, var9;
/*     */       String var10;
/*     */       ByteBuf var11;
/*  35 */       if (var3.readUnsignedByte() != 254) {
/*     */         return;
/*     */       }
/*     */ 
/*     */       
/*  40 */       InetSocketAddress var5 = (InetSocketAddress)p_channelRead_1_.channel().remoteAddress();
/*  41 */       MinecraftServer var6 = this.networkSystem.getServer();
/*  42 */       int var7 = var3.readableBytes();
/*     */ 
/*     */       
/*  45 */       switch (var7) {
/*     */         
/*     */         case 0:
/*  48 */           logger.debug("Ping: (<1.3.x) from {}:{}", new Object[] { var5.getAddress(), Integer.valueOf(var5.getPort()) });
/*  49 */           var8 = String.format("%s§%d§%d", new Object[] { var6.getMOTD(), Integer.valueOf(var6.getCurrentPlayerCount()), Integer.valueOf(var6.getMaxPlayers()) });
/*  50 */           writeAndFlush(p_channelRead_1_, getStringBuffer(var8));
/*     */           break;
/*     */         
/*     */         case 1:
/*  54 */           if (var3.readUnsignedByte() != 1) {
/*     */             return;
/*     */           }
/*     */ 
/*     */           
/*  59 */           logger.debug("Ping: (1.4-1.5.x) from {}:{}", new Object[] { var5.getAddress(), Integer.valueOf(var5.getPort()) });
/*  60 */           var8 = String.format("§1\000%d\000%s\000%s\000%d\000%d", new Object[] { Integer.valueOf(127), var6.getMinecraftVersion(), var6.getMOTD(), Integer.valueOf(var6.getCurrentPlayerCount()), Integer.valueOf(var6.getMaxPlayers()) });
/*  61 */           writeAndFlush(p_channelRead_1_, getStringBuffer(var8));
/*     */           break;
/*     */         
/*     */         default:
/*  65 */           var23 = (var3.readUnsignedByte() == 1);
/*  66 */           j = var23 & ((var3.readUnsignedByte() == 250) ? 1 : 0);
/*  67 */           bool1 = j & "MC|PingHost".equals(new String(var3.readBytes(var3.readShort() * 2).array(), Charsets.UTF_16BE));
/*  68 */           var9 = var3.readUnsignedShort();
/*  69 */           i = bool1 & ((var3.readUnsignedByte() >= 73) ? 1 : 0);
/*  70 */           i &= (3 + (var3.readBytes(var3.readShort() * 2).array()).length + 4 == var9) ? 1 : 0;
/*  71 */           i &= (var3.readInt() <= 65535) ? 1 : 0;
/*  72 */           i &= (var3.readableBytes() == 0) ? 1 : 0;
/*     */           
/*  74 */           if (i == 0) {
/*     */             return;
/*     */           }
/*     */ 
/*     */           
/*  79 */           logger.debug("Ping: (1.6) from {}:{}", new Object[] { var5.getAddress(), Integer.valueOf(var5.getPort()) });
/*  80 */           var10 = String.format("§1\000%d\000%s\000%s\000%d\000%d", new Object[] { Integer.valueOf(127), var6.getMinecraftVersion(), var6.getMOTD(), Integer.valueOf(var6.getCurrentPlayerCount()), Integer.valueOf(var6.getMaxPlayers()) });
/*  81 */           var11 = getStringBuffer(var10);
/*     */ 
/*     */           
/*     */           try {
/*  85 */             writeAndFlush(p_channelRead_1_, var11);
/*     */           }
/*     */           finally {
/*     */             
/*  89 */             var11.release();
/*     */           } 
/*     */           break;
/*     */       } 
/*  93 */       var3.release();
/*     */        }
/*     */     
/*  96 */     catch (RuntimeException runtimeException)
/*     */     
/*     */     { 
/*     */        }
/*     */     
/*     */     finally
/*     */     
/* 103 */     { if (var4)
/*     */       
/* 105 */       { var3.resetReaderIndex();
/* 106 */         p_channelRead_1_.channel().pipeline().remove("legacy_query");
/* 107 */         p_channelRead_1_.fireChannelRead(p_channelRead_2_); }  }  if (var4) { var3.resetReaderIndex(); p_channelRead_1_.channel().pipeline().remove("legacy_query"); p_channelRead_1_.fireChannelRead(p_channelRead_2_); }
/*     */   
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void writeAndFlush(ChannelHandlerContext ctx, ByteBuf data) {
/* 114 */     ctx.pipeline().firstContext().writeAndFlush(data).addListener((GenericFutureListener)ChannelFutureListener.CLOSE);
/*     */   }
/*     */ 
/*     */   
/*     */   private ByteBuf getStringBuffer(String string) {
/* 119 */     ByteBuf var2 = Unpooled.buffer();
/* 120 */     var2.writeByte(255);
/* 121 */     char[] var3 = string.toCharArray();
/* 122 */     var2.writeShort(var3.length);
/* 123 */     char[] var4 = var3;
/* 124 */     int var5 = var3.length;
/*     */     
/* 126 */     for (int var6 = 0; var6 < var5; var6++) {
/*     */       
/* 128 */       char var7 = var4[var6];
/* 129 */       var2.writeChar(var7);
/*     */     } 
/*     */     
/* 132 */     return var2;
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\network\PingResponseHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */