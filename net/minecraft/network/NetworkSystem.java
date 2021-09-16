/*     */ package net.minecraft.network;
/*     */ import com.google.common.collect.Lists;
/*     */ import com.google.common.util.concurrent.ThreadFactoryBuilder;
/*     */ import io.netty.bootstrap.ServerBootstrap;
/*     */ import io.netty.channel.Channel;
/*     */ import io.netty.channel.ChannelException;
/*     */ import io.netty.channel.ChannelFuture;
/*     */ import io.netty.channel.ChannelHandler;
/*     */ import io.netty.channel.ChannelInitializer;
/*     */ import io.netty.channel.ChannelOption;
/*     */ import io.netty.channel.EventLoopGroup;
/*     */ import io.netty.channel.local.LocalAddress;
/*     */ import io.netty.channel.local.LocalEventLoopGroup;
/*     */ import io.netty.channel.local.LocalServerChannel;
/*     */ import io.netty.channel.nio.NioEventLoopGroup;
/*     */ import io.netty.channel.socket.nio.NioServerSocketChannel;
/*     */ import io.netty.handler.timeout.ReadTimeoutHandler;
/*     */ import io.netty.util.concurrent.Future;
/*     */ import io.netty.util.concurrent.GenericFutureListener;
/*     */ import java.io.IOException;
/*     */ import java.net.InetAddress;
/*     */ import java.net.SocketAddress;
/*     */ import java.util.Collections;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.concurrent.Callable;
/*     */ import net.minecraft.client.network.NetHandlerHandshakeMemory;
/*     */ import net.minecraft.crash.CrashReport;
/*     */ import net.minecraft.crash.CrashReportCategory;
/*     */ import net.minecraft.network.play.server.S40PacketDisconnect;
/*     */ import net.minecraft.server.MinecraftServer;
/*     */ import net.minecraft.server.network.NetHandlerHandshakeTCP;
/*     */ import net.minecraft.util.ChatComponentText;
/*     */ import net.minecraft.util.IChatComponent;
/*     */ import net.minecraft.util.LazyLoadBase;
/*     */ import net.minecraft.util.MessageDeserializer;
/*     */ import net.minecraft.util.MessageDeserializer2;
/*     */ import net.minecraft.util.MessageSerializer;
/*     */ import net.minecraft.util.MessageSerializer2;
/*     */ import net.minecraft.util.ReportedException;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ 
/*     */ public class NetworkSystem {
/*  45 */   private static final Logger logger = LogManager.getLogger();
/*  46 */   public static final LazyLoadBase eventLoops = new LazyLoadBase()
/*     */     {
/*     */       private static final String __OBFID = "CL_00001448";
/*     */       
/*     */       protected NioEventLoopGroup genericLoad() {
/*  51 */         return new NioEventLoopGroup(0, (new ThreadFactoryBuilder()).setNameFormat("Netty Server IO #%d").setDaemon(true).build());
/*     */       }
/*     */       
/*     */       protected Object load() {
/*  55 */         return genericLoad();
/*     */       }
/*     */     };
/*  58 */   public static final LazyLoadBase SERVER_LOCAL_EVENTLOOP = new LazyLoadBase()
/*     */     {
/*     */       private static final String __OBFID = "CL_00001449";
/*     */       
/*     */       protected LocalEventLoopGroup genericLoad() {
/*  63 */         return new LocalEventLoopGroup(0, (new ThreadFactoryBuilder()).setNameFormat("Netty Local Server IO #%d").setDaemon(true).build());
/*     */       }
/*     */       
/*     */       protected Object load() {
/*  67 */         return genericLoad();
/*     */       }
/*     */     };
/*     */ 
/*     */   
/*     */   private final MinecraftServer mcServer;
/*     */ 
/*     */   
/*     */   public volatile boolean isAlive;
/*     */ 
/*     */   
/*  78 */   private final List endpoints = Collections.synchronizedList(Lists.newArrayList());
/*     */ 
/*     */   
/*  81 */   private final List networkManagers = Collections.synchronizedList(Lists.newArrayList());
/*     */   
/*     */   private static final String __OBFID = "CL_00001447";
/*     */   
/*     */   public NetworkSystem(MinecraftServer server) {
/*  86 */     this.mcServer = server;
/*  87 */     this.isAlive = true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addLanEndpoint(InetAddress address, int port) throws IOException {
/*  95 */     List var3 = this.endpoints;
/*     */     
/*  97 */     synchronized (this.endpoints) {
/*     */       
/*  99 */       this.endpoints.add(((ServerBootstrap)((ServerBootstrap)(new ServerBootstrap()).channel(NioServerSocketChannel.class)).childHandler((ChannelHandler)new ChannelInitializer()
/*     */             {
/*     */               private static final String __OBFID = "CL_00001450";
/*     */ 
/*     */               
/*     */               protected void initChannel(Channel p_initChannel_1_) {
/*     */                 try {
/* 106 */                   p_initChannel_1_.config().setOption(ChannelOption.IP_TOS, Integer.valueOf(24));
/*     */                 }
/* 108 */                 catch (ChannelException channelException) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */                 
/*     */                 try {
/* 115 */                   p_initChannel_1_.config().setOption(ChannelOption.TCP_NODELAY, Boolean.valueOf(false));
/*     */                 }
/* 117 */                 catch (ChannelException channelException) {}
/*     */ 
/*     */ 
/*     */ 
/*     */                 
/* 122 */                 p_initChannel_1_.pipeline().addLast("timeout", (ChannelHandler)new ReadTimeoutHandler(30)).addLast("legacy_query", (ChannelHandler)new PingResponseHandler(NetworkSystem.this)).addLast("splitter", (ChannelHandler)new MessageDeserializer2()).addLast("decoder", (ChannelHandler)new MessageDeserializer(EnumPacketDirection.SERVERBOUND)).addLast("prepender", (ChannelHandler)new MessageSerializer2()).addLast("encoder", (ChannelHandler)new MessageSerializer(EnumPacketDirection.CLIENTBOUND));
/* 123 */                 NetworkManager var2 = new NetworkManager(EnumPacketDirection.SERVERBOUND);
/* 124 */                 NetworkSystem.this.networkManagers.add(var2);
/* 125 */                 p_initChannel_1_.pipeline().addLast("packet_handler", (ChannelHandler)var2);
/* 126 */                 var2.setNetHandler((INetHandler)new NetHandlerHandshakeTCP(NetworkSystem.this.mcServer, var2));
/*     */               }
/* 128 */             }).group((EventLoopGroup)eventLoops.getValue()).localAddress(address, port)).bind().syncUninterruptibly());
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SocketAddress addLocalEndpoint() {
/*     */     ChannelFuture var1;
/* 137 */     List var2 = this.endpoints;
/*     */ 
/*     */     
/* 140 */     synchronized (this.endpoints) {
/*     */       
/* 142 */       var1 = ((ServerBootstrap)((ServerBootstrap)(new ServerBootstrap()).channel(LocalServerChannel.class)).childHandler((ChannelHandler)new ChannelInitializer()
/*     */           {
/*     */             private static final String __OBFID = "CL_00001451";
/*     */             
/*     */             protected void initChannel(Channel p_initChannel_1_) {
/* 147 */               NetworkManager var2 = new NetworkManager(EnumPacketDirection.SERVERBOUND);
/* 148 */               var2.setNetHandler((INetHandler)new NetHandlerHandshakeMemory(NetworkSystem.this.mcServer, var2));
/* 149 */               NetworkSystem.this.networkManagers.add(var2);
/* 150 */               p_initChannel_1_.pipeline().addLast("packet_handler", (ChannelHandler)var2);
/*     */             }
/* 152 */           }).group((EventLoopGroup)eventLoops.getValue()).localAddress((SocketAddress)LocalAddress.ANY)).bind().syncUninterruptibly();
/* 153 */       this.endpoints.add(var1);
/*     */     } 
/*     */     
/* 156 */     return var1.channel().localAddress();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void terminateEndpoints() {
/* 164 */     this.isAlive = false;
/* 165 */     Iterator<ChannelFuture> var1 = this.endpoints.iterator();
/*     */     
/* 167 */     while (var1.hasNext()) {
/*     */       
/* 169 */       ChannelFuture var2 = var1.next();
/*     */ 
/*     */       
/*     */       try {
/* 173 */         var2.channel().close().sync();
/*     */       }
/* 175 */       catch (InterruptedException var4) {
/*     */         
/* 177 */         logger.error("Interrupted whilst closing channel");
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void networkTick() {
/* 188 */     List var1 = this.networkManagers;
/*     */     
/* 190 */     synchronized (this.networkManagers) {
/*     */       
/* 192 */       Iterator<NetworkManager> var2 = this.networkManagers.iterator();
/*     */       
/* 194 */       while (var2.hasNext()) {
/*     */         
/* 196 */         final NetworkManager var3 = var2.next();
/*     */         
/* 198 */         if (!var3.hasNoChannel()) {
/*     */           
/* 200 */           if (!var3.isChannelOpen()) {
/*     */             
/* 202 */             var2.remove();
/* 203 */             var3.checkDisconnected();
/*     */             
/*     */             continue;
/*     */           } 
/*     */           
/*     */           try {
/* 209 */             var3.processReceivedPackets();
/*     */           }
/* 211 */           catch (Exception var8) {
/*     */             
/* 213 */             if (var3.isLocalChannel()) {
/*     */               
/* 215 */               CrashReport var10 = CrashReport.makeCrashReport(var8, "Ticking memory connection");
/* 216 */               CrashReportCategory var6 = var10.makeCategory("Ticking connection");
/* 217 */               var6.addCrashSectionCallable("Connection", new Callable()
/*     */                   {
/*     */                     private static final String __OBFID = "CL_00002272";
/*     */                     
/*     */                     public String func_180229_a() {
/* 222 */                       return var3.toString();
/*     */                     }
/*     */                     
/*     */                     public Object call() {
/* 226 */                       return func_180229_a();
/*     */                     }
/*     */                   });
/* 229 */               throw new ReportedException(var10);
/*     */             } 
/*     */             
/* 232 */             logger.warn("Failed to handle packet for " + var3.getRemoteAddress(), var8);
/* 233 */             final ChatComponentText var5 = new ChatComponentText("Internal server error");
/* 234 */             var3.sendPacket((Packet)new S40PacketDisconnect((IChatComponent)var5), new GenericFutureListener()
/*     */                 {
/*     */                   private static final String __OBFID = "CL_00002271";
/*     */                   
/*     */                   public void operationComplete(Future p_operationComplete_1_) {
/* 239 */                     var3.closeChannel((IChatComponent)var5);
/*     */                   }
/* 241 */                 },  new GenericFutureListener[0]);
/* 242 */             var3.disableAutoRead();
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public MinecraftServer getServer() {
/* 252 */     return this.mcServer;
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\network\NetworkSystem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */