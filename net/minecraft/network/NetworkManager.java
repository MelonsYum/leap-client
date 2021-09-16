/*     */ package net.minecraft.network;
/*     */ 
/*     */ import com.google.common.collect.Queues;
/*     */ import com.google.common.util.concurrent.ThreadFactoryBuilder;
/*     */ import io.netty.bootstrap.Bootstrap;
/*     */ import io.netty.channel.Channel;
/*     */ import io.netty.channel.ChannelException;
/*     */ import io.netty.channel.ChannelFuture;
/*     */ import io.netty.channel.ChannelFutureListener;
/*     */ import io.netty.channel.ChannelHandler;
/*     */ import io.netty.channel.ChannelHandlerContext;
/*     */ import io.netty.channel.ChannelInitializer;
/*     */ import io.netty.channel.ChannelOption;
/*     */ import io.netty.channel.EventLoopGroup;
/*     */ import io.netty.channel.SimpleChannelInboundHandler;
/*     */ import io.netty.channel.local.LocalChannel;
/*     */ import io.netty.channel.local.LocalEventLoopGroup;
/*     */ import io.netty.channel.nio.NioEventLoopGroup;
/*     */ import io.netty.channel.socket.nio.NioSocketChannel;
/*     */ import io.netty.handler.timeout.ReadTimeoutHandler;
/*     */ import io.netty.util.AttributeKey;
/*     */ import io.netty.util.concurrent.GenericFutureListener;
/*     */ import java.net.InetAddress;
/*     */ import java.net.SocketAddress;
/*     */ import java.util.Queue;
/*     */ import javax.crypto.SecretKey;
/*     */ import leap.Client;
/*     */ import leap.events.Event;
/*     */ import leap.events.listeners.EventReceivePacket;
/*     */ import leap.events.listeners.EventSendPacket;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.server.gui.IUpdatePlayerListBox;
/*     */ import net.minecraft.util.ChatComponentTranslation;
/*     */ import net.minecraft.util.CryptManager;
/*     */ import net.minecraft.util.IChatComponent;
/*     */ import net.minecraft.util.LazyLoadBase;
/*     */ import net.minecraft.util.MessageDeserializer;
/*     */ import net.minecraft.util.MessageDeserializer2;
/*     */ import net.minecraft.util.MessageSerializer;
/*     */ import net.minecraft.util.MessageSerializer2;
/*     */ import org.apache.commons.lang3.ArrayUtils;
/*     */ import org.apache.commons.lang3.Validate;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ import org.apache.logging.log4j.Marker;
/*     */ import org.apache.logging.log4j.MarkerManager;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class NetworkManager
/*     */   extends SimpleChannelInboundHandler
/*     */ {
/*  55 */   private static final Logger logger = LogManager.getLogger();
/*  56 */   public static final Marker logMarkerNetwork = MarkerManager.getMarker("NETWORK");
/*  57 */   public static final Marker logMarkerPackets = MarkerManager.getMarker("NETWORK_PACKETS", logMarkerNetwork);
/*  58 */   public static final AttributeKey attrKeyConnectionState = AttributeKey.valueOf("protocol");
/*  59 */   public static final LazyLoadBase CLIENT_NIO_EVENTLOOP = new LazyLoadBase()
/*     */     {
/*     */       private static final String __OBFID = "CL_00001241";
/*     */       
/*     */       protected NioEventLoopGroup genericLoad() {
/*  64 */         return new NioEventLoopGroup(0, (new ThreadFactoryBuilder()).setNameFormat("Netty Client IO #%d").setDaemon(true).build());
/*     */       }
/*     */       
/*     */       protected Object load() {
/*  68 */         return genericLoad();
/*     */       }
/*     */     };
/*  71 */   public static final LazyLoadBase CLIENT_LOCAL_EVENTLOOP = new LazyLoadBase()
/*     */     {
/*     */       private static final String __OBFID = "CL_00001242";
/*     */       
/*     */       protected LocalEventLoopGroup genericLoad() {
/*  76 */         return new LocalEventLoopGroup(0, (new ThreadFactoryBuilder()).setNameFormat("Netty Local Client IO #%d").setDaemon(true).build());
/*     */       }
/*     */       
/*     */       protected Object load() {
/*  80 */         return genericLoad();
/*     */       }
/*     */     };
/*     */   
/*     */   private final EnumPacketDirection direction;
/*     */   
/*  86 */   private final Queue outboundPacketsQueue = Queues.newConcurrentLinkedQueue();
/*     */ 
/*     */   
/*     */   private Channel channel;
/*     */   
/*     */   private SocketAddress socketAddress;
/*     */   
/*     */   private INetHandler packetListener;
/*     */   
/*     */   private IChatComponent terminationReason;
/*     */   
/*     */   private boolean isEncrypted;
/*     */   
/*     */   private boolean disconnected;
/*     */   
/*     */   private static final String __OBFID = "CL_00001240";
/*     */ 
/*     */   
/*     */   public NetworkManager(EnumPacketDirection packetDirection) {
/* 105 */     this.direction = packetDirection;
/*     */   }
/*     */ 
/*     */   
/*     */   public void channelActive(ChannelHandlerContext p_channelActive_1_) throws Exception {
/* 110 */     super.channelActive(p_channelActive_1_);
/* 111 */     this.channel = p_channelActive_1_.channel();
/* 112 */     this.socketAddress = this.channel.remoteAddress();
/*     */ 
/*     */     
/*     */     try {
/* 116 */       setConnectionState(EnumConnectionState.HANDSHAKING);
/*     */     }
/* 118 */     catch (Throwable var3) {
/*     */       
/* 120 */       logger.fatal(var3);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setConnectionState(EnumConnectionState newState) {
/* 129 */     this.channel.attr(attrKeyConnectionState).set(newState);
/* 130 */     this.channel.config().setAutoRead(true);
/* 131 */     logger.debug("Enabled auto read");
/*     */   }
/*     */ 
/*     */   
/*     */   public void channelInactive(ChannelHandlerContext p_channelInactive_1_) {
/* 136 */     closeChannel((IChatComponent)new ChatComponentTranslation("disconnect.endOfStream", new Object[0]));
/*     */   }
/*     */ 
/*     */   
/*     */   public void exceptionCaught(ChannelHandlerContext p_exceptionCaught_1_, Throwable p_exceptionCaught_2_) {
/* 141 */     logger.debug("Disconnecting " + getRemoteAddress(), p_exceptionCaught_2_);
/* 142 */     closeChannel((IChatComponent)new ChatComponentTranslation("disconnect.genericReason", new Object[] { "Internal Exception: " + p_exceptionCaught_2_ }));
/*     */   }
/*     */ 
/*     */   
/*     */   protected void channelRead0(ChannelHandlerContext p_channelRead0_1_, Packet p_channelRead0_2_) {
/* 147 */     EventReceivePacket e = new EventReceivePacket(p_channelRead0_2_);
/*     */     
/* 149 */     if ((Minecraft.getMinecraft()).thePlayer != null && (Minecraft.getMinecraft()).theWorld != null) {
/* 150 */       Client.onEvent((Event)e);
/*     */       
/* 152 */       if (e.isCancelled()) {
/*     */         return;
/*     */       }
/*     */     } 
/* 156 */     if (this.channel.isOpen()) {
/*     */       
/*     */       try {
/*     */         
/* 160 */         p_channelRead0_2_.processPacket(this.packetListener);
/*     */       }
/* 162 */       catch (ThreadQuickExitException threadQuickExitException) {}
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setNetHandler(INetHandler handler) {
/* 176 */     Validate.notNull(handler, "packetListener", new Object[0]);
/* 177 */     logger.debug("Set listener of {} to {}", new Object[] { this, handler });
/* 178 */     this.packetListener = handler;
/*     */   }
/*     */ 
/*     */   
/*     */   public void sendPacket(Packet packetIn) {
/* 183 */     EventSendPacket e = new EventSendPacket(packetIn);
/* 184 */     Client.onEvent((Event)e);
/*     */     
/* 186 */     if (e.getPacket() != null) packetIn = e.getPacket(); 
/* 187 */     if (e.isCancelled()) {
/*     */       return;
/*     */     }
/* 190 */     if (this.channel != null && this.channel.isOpen()) {
/*     */       
/* 192 */       flushOutboundQueue();
/* 193 */       dispatchPacket(packetIn, null);
/*     */     }
/*     */     else {
/*     */       
/* 197 */       this.outboundPacketsQueue.add(new InboundHandlerTuplePacketListener(packetIn, null));
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void sendPacket(Packet packetIn, GenericFutureListener listener, GenericFutureListener... listeners) {
/* 203 */     if (this.channel != null && this.channel.isOpen()) {
/*     */       
/* 205 */       flushOutboundQueue();
/* 206 */       dispatchPacket(packetIn, (GenericFutureListener[])ArrayUtils.add((Object[])listeners, 0, listener));
/*     */     }
/*     */     else {
/*     */       
/* 210 */       this.outboundPacketsQueue.add(new InboundHandlerTuplePacketListener(packetIn, (GenericFutureListener[])ArrayUtils.add((Object[])listeners, 0, listener)));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void dispatchPacket(final Packet inPacket, final GenericFutureListener[] futureListeners) {
/* 220 */     final EnumConnectionState var3 = EnumConnectionState.getFromPacket(inPacket);
/* 221 */     final EnumConnectionState var4 = (EnumConnectionState)this.channel.attr(attrKeyConnectionState).get();
/*     */     
/* 223 */     if (var4 != var3) {
/*     */       
/* 225 */       logger.debug("Disabled auto read");
/* 226 */       this.channel.config().setAutoRead(false);
/*     */     } 
/*     */     
/* 229 */     if (this.channel.eventLoop().inEventLoop()) {
/*     */       
/* 231 */       if (var3 != var4)
/*     */       {
/* 233 */         setConnectionState(var3);
/*     */       }
/*     */       
/* 236 */       ChannelFuture var5 = this.channel.writeAndFlush(inPacket);
/*     */       
/* 238 */       if (futureListeners != null)
/*     */       {
/* 240 */         var5.addListeners(futureListeners);
/*     */       }
/*     */       
/* 243 */       var5.addListener((GenericFutureListener)ChannelFutureListener.FIRE_EXCEPTION_ON_FAILURE);
/*     */     }
/*     */     else {
/*     */       
/* 247 */       this.channel.eventLoop().execute(new Runnable()
/*     */           {
/*     */             private static final String __OBFID = "CL_00001243";
/*     */             
/*     */             public void run() {
/* 252 */               if (var3 != var4)
/*     */               {
/* 254 */                 NetworkManager.this.setConnectionState(var3);
/*     */               }
/*     */               
/* 257 */               ChannelFuture var1 = NetworkManager.this.channel.writeAndFlush(inPacket);
/*     */               
/* 259 */               if (futureListeners != null)
/*     */               {
/* 261 */                 var1.addListeners(futureListeners);
/*     */               }
/*     */               
/* 264 */               var1.addListener((GenericFutureListener)ChannelFutureListener.FIRE_EXCEPTION_ON_FAILURE);
/*     */             }
/*     */           });
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void flushOutboundQueue() {
/* 275 */     if (this.channel != null && this.channel.isOpen())
/*     */     {
/* 277 */       while (!this.outboundPacketsQueue.isEmpty()) {
/*     */         
/* 279 */         InboundHandlerTuplePacketListener var1 = this.outboundPacketsQueue.poll();
/* 280 */         dispatchPacket(var1.packet, var1.futureListeners);
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void processReceivedPackets() {
/* 290 */     flushOutboundQueue();
/*     */     
/* 292 */     if (this.packetListener instanceof IUpdatePlayerListBox)
/*     */     {
/* 294 */       ((IUpdatePlayerListBox)this.packetListener).update();
/*     */     }
/*     */     
/* 297 */     this.channel.flush();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SocketAddress getRemoteAddress() {
/* 305 */     return this.socketAddress;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void closeChannel(IChatComponent message) {
/* 313 */     if (this.channel.isOpen()) {
/*     */       
/* 315 */       this.channel.close().awaitUninterruptibly();
/* 316 */       this.terminationReason = message;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isLocalChannel() {
/* 326 */     return !(!(this.channel instanceof LocalChannel) && !(this.channel instanceof io.netty.channel.local.LocalServerChannel));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static NetworkManager provideLanClient(InetAddress p_150726_0_, int p_150726_1_) {
/* 335 */     final NetworkManager var2 = new NetworkManager(EnumPacketDirection.CLIENTBOUND);
/* 336 */     ((Bootstrap)((Bootstrap)((Bootstrap)(new Bootstrap()).group((EventLoopGroup)CLIENT_NIO_EVENTLOOP.getValue())).handler((ChannelHandler)new ChannelInitializer()
/*     */         {
/*     */           private static final String __OBFID = "CL_00002312";
/*     */ 
/*     */           
/*     */           protected void initChannel(Channel p_initChannel_1_) {
/*     */             try {
/* 343 */               p_initChannel_1_.config().setOption(ChannelOption.IP_TOS, Integer.valueOf(24));
/*     */             }
/* 345 */             catch (ChannelException channelException) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */             
/*     */             try {
/* 352 */               p_initChannel_1_.config().setOption(ChannelOption.TCP_NODELAY, Boolean.valueOf(false));
/*     */             }
/* 354 */             catch (ChannelException channelException) {}
/*     */ 
/*     */ 
/*     */ 
/*     */             
/* 359 */             p_initChannel_1_.pipeline().addLast("timeout", (ChannelHandler)new ReadTimeoutHandler(30)).addLast("splitter", (ChannelHandler)new MessageDeserializer2()).addLast("decoder", (ChannelHandler)new MessageDeserializer(EnumPacketDirection.CLIENTBOUND)).addLast("prepender", (ChannelHandler)new MessageSerializer2()).addLast("encoder", (ChannelHandler)new MessageSerializer(EnumPacketDirection.SERVERBOUND)).addLast("packet_handler", (ChannelHandler)var2);
/*     */           }
/* 362 */         })).channel(NioSocketChannel.class)).connect(p_150726_0_, p_150726_1_).syncUninterruptibly();
/* 363 */     return var2;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static NetworkManager provideLocalClient(SocketAddress p_150722_0_) {
/* 372 */     final NetworkManager var1 = new NetworkManager(EnumPacketDirection.CLIENTBOUND);
/* 373 */     ((Bootstrap)((Bootstrap)((Bootstrap)(new Bootstrap()).group((EventLoopGroup)CLIENT_LOCAL_EVENTLOOP.getValue())).handler((ChannelHandler)new ChannelInitializer()
/*     */         {
/*     */           private static final String __OBFID = "CL_00002311";
/*     */           
/*     */           protected void initChannel(Channel p_initChannel_1_) {
/* 378 */             p_initChannel_1_.pipeline().addLast("packet_handler", (ChannelHandler)var1);
/*     */           }
/* 380 */         })).channel(LocalChannel.class)).connect(p_150722_0_).syncUninterruptibly();
/* 381 */     return var1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void enableEncryption(SecretKey key) {
/* 389 */     this.isEncrypted = true;
/* 390 */     this.channel.pipeline().addBefore("splitter", "decrypt", (ChannelHandler)new NettyEncryptingDecoder(CryptManager.func_151229_a(2, key)));
/* 391 */     this.channel.pipeline().addBefore("prepender", "encrypt", (ChannelHandler)new NettyEncryptingEncoder(CryptManager.func_151229_a(1, key)));
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_179292_f() {
/* 396 */     return this.isEncrypted;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isChannelOpen() {
/* 404 */     return (this.channel != null && this.channel.isOpen());
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hasNoChannel() {
/* 409 */     return (this.channel == null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public INetHandler getNetHandler() {
/* 417 */     return this.packetListener;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IChatComponent getExitMessage() {
/* 425 */     return this.terminationReason;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void disableAutoRead() {
/* 433 */     this.channel.config().setAutoRead(false);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setCompressionTreshold(int treshold) {
/* 438 */     if (treshold >= 0) {
/*     */       
/* 440 */       if (this.channel.pipeline().get("decompress") instanceof NettyCompressionDecoder) {
/*     */         
/* 442 */         ((NettyCompressionDecoder)this.channel.pipeline().get("decompress")).setCompressionTreshold(treshold);
/*     */       }
/*     */       else {
/*     */         
/* 446 */         this.channel.pipeline().addBefore("decoder", "decompress", (ChannelHandler)new NettyCompressionDecoder(treshold));
/*     */       } 
/*     */ 
/*     */       
/* 450 */       if (this.channel.pipeline().get("compress") instanceof NettyCompressionEncoder)
/*     */       {
/* 452 */         ((NettyCompressionEncoder)this.channel.pipeline().get("decompress")).setCompressionTreshold(treshold);
/*     */       }
/*     */       else
/*     */       {
/* 456 */         this.channel.pipeline().addBefore("encoder", "compress", (ChannelHandler)new NettyCompressionEncoder(treshold));
/*     */       }
/*     */     
/*     */     }
/*     */     else {
/*     */       
/* 462 */       if (this.channel.pipeline().get("decompress") instanceof NettyCompressionDecoder)
/*     */       {
/* 464 */         this.channel.pipeline().remove("decompress");
/*     */       }
/*     */       
/* 467 */       if (this.channel.pipeline().get("compress") instanceof NettyCompressionEncoder)
/*     */       {
/* 469 */         this.channel.pipeline().remove("compress");
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void checkDisconnected() {
/* 489 */     this.disconnected = false;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void channelRead0(ChannelHandlerContext p_channelRead0_1_, Object p_channelRead0_2_) {
/* 494 */     channelRead0(p_channelRead0_1_, (Packet)p_channelRead0_2_);
/*     */   }
/*     */ 
/*     */   
/*     */   static class InboundHandlerTuplePacketListener
/*     */   {
/*     */     private final Packet packet;
/*     */     private final GenericFutureListener[] futureListeners;
/*     */     private static final String __OBFID = "CL_00001244";
/*     */     
/*     */     public InboundHandlerTuplePacketListener(Packet inPacket, GenericFutureListener... inFutureListeners) {
/* 505 */       this.packet = inPacket;
/* 506 */       this.futureListeners = inFutureListeners;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\network\NetworkManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */