/*     */ package net.minecraft.client.network;
/*     */ import com.google.common.base.Charsets;
/*     */ import com.google.common.base.Splitter;
/*     */ import com.google.common.collect.Iterables;
/*     */ import com.google.common.collect.Lists;
/*     */ import com.mojang.authlib.GameProfile;
/*     */ import io.netty.bootstrap.Bootstrap;
/*     */ import io.netty.buffer.ByteBuf;
/*     */ import io.netty.buffer.Unpooled;
/*     */ import io.netty.channel.Channel;
/*     */ import io.netty.channel.ChannelException;
/*     */ import io.netty.channel.ChannelFutureListener;
/*     */ import io.netty.channel.ChannelHandler;
/*     */ import io.netty.channel.ChannelHandlerContext;
/*     */ import io.netty.channel.ChannelInitializer;
/*     */ import io.netty.channel.ChannelOption;
/*     */ import io.netty.channel.EventLoopGroup;
/*     */ import io.netty.channel.SimpleChannelInboundHandler;
/*     */ import io.netty.channel.socket.nio.NioSocketChannel;
/*     */ import io.netty.util.concurrent.GenericFutureListener;
/*     */ import java.net.InetAddress;
/*     */ import java.net.UnknownHostException;
/*     */ import java.util.Collections;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.multiplayer.ServerAddress;
/*     */ import net.minecraft.client.multiplayer.ServerData;
/*     */ import net.minecraft.network.EnumConnectionState;
/*     */ import net.minecraft.network.INetHandler;
/*     */ import net.minecraft.network.NetworkManager;
/*     */ import net.minecraft.network.Packet;
/*     */ import net.minecraft.network.ServerStatusResponse;
/*     */ import net.minecraft.network.status.INetHandlerStatusClient;
/*     */ import net.minecraft.network.status.client.C00PacketServerQuery;
/*     */ import net.minecraft.network.status.client.C01PacketPing;
/*     */ import net.minecraft.network.status.server.S00PacketServerInfo;
/*     */ import net.minecraft.network.status.server.S01PacketPong;
/*     */ import net.minecraft.util.ChatComponentText;
/*     */ import net.minecraft.util.EnumChatFormatting;
/*     */ import net.minecraft.util.IChatComponent;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import org.apache.commons.lang3.ArrayUtils;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ 
/*     */ public class OldServerPinger {
/*  48 */   private static final Splitter PING_RESPONSE_SPLITTER = Splitter.on(false).limit(6);
/*  49 */   private static final Logger logger = LogManager.getLogger();
/*     */ 
/*     */   
/*  52 */   private final List pingDestinations = Collections.synchronizedList(Lists.newArrayList());
/*     */   
/*     */   private static final String __OBFID = "CL_00000892";
/*     */   
/*     */   public void ping(final ServerData server) throws UnknownHostException {
/*  57 */     ServerAddress var2 = ServerAddress.func_78860_a(server.serverIP);
/*  58 */     final NetworkManager var3 = NetworkManager.provideLanClient(InetAddress.getByName(var2.getIP()), var2.getPort());
/*  59 */     this.pingDestinations.add(var3);
/*  60 */     server.serverMOTD = "Pinging...";
/*  61 */     server.pingToServer = -1L;
/*  62 */     server.playerList = null;
/*  63 */     var3.setNetHandler((INetHandler)new INetHandlerStatusClient()
/*     */         {
/*     */           private boolean field_147403_d = false;
/*  66 */           private long field_175092_e = 0L;
/*     */           private static final String __OBFID = "CL_00000893";
/*     */           
/*     */           public void handleServerInfo(S00PacketServerInfo packetIn) {
/*  70 */             ServerStatusResponse var2 = packetIn.func_149294_c();
/*     */             
/*  72 */             if (var2.getServerDescription() != null) {
/*     */               
/*  74 */               server.serverMOTD = var2.getServerDescription().getFormattedText();
/*     */             }
/*     */             else {
/*     */               
/*  78 */               server.serverMOTD = "";
/*     */             } 
/*     */             
/*  81 */             if (var2.getProtocolVersionInfo() != null) {
/*     */               
/*  83 */               server.gameVersion = var2.getProtocolVersionInfo().getName();
/*  84 */               server.version = var2.getProtocolVersionInfo().getProtocol();
/*     */             }
/*     */             else {
/*     */               
/*  88 */               server.gameVersion = "Old";
/*  89 */               server.version = 0;
/*     */             } 
/*     */             
/*  92 */             if (var2.getPlayerCountData() != null) {
/*     */               
/*  94 */               server.populationInfo = EnumChatFormatting.GRAY + var2.getPlayerCountData().getOnlinePlayerCount() + EnumChatFormatting.DARK_GRAY + "/" + EnumChatFormatting.GRAY + var2.getPlayerCountData().getMaxPlayers();
/*     */               
/*  96 */               if (ArrayUtils.isNotEmpty((Object[])var2.getPlayerCountData().getPlayers()))
/*     */               {
/*  98 */                 StringBuilder var3x = new StringBuilder();
/*  99 */                 GameProfile[] var4 = var2.getPlayerCountData().getPlayers();
/* 100 */                 int var5 = var4.length;
/*     */                 
/* 102 */                 for (int var6 = 0; var6 < var5; var6++) {
/*     */                   
/* 104 */                   GameProfile var7 = var4[var6];
/*     */                   
/* 106 */                   if (var3x.length() > 0)
/*     */                   {
/* 108 */                     var3x.append("\n");
/*     */                   }
/*     */                   
/* 111 */                   var3x.append(var7.getName());
/*     */                 } 
/*     */                 
/* 114 */                 if ((var2.getPlayerCountData().getPlayers()).length < var2.getPlayerCountData().getOnlinePlayerCount()) {
/*     */                   
/* 116 */                   if (var3x.length() > 0)
/*     */                   {
/* 118 */                     var3x.append("\n");
/*     */                   }
/*     */                   
/* 121 */                   var3x.append("... and ").append(var2.getPlayerCountData().getOnlinePlayerCount() - (var2.getPlayerCountData().getPlayers()).length).append(" more ...");
/*     */                 } 
/*     */                 
/* 124 */                 server.playerList = var3x.toString();
/*     */               }
/*     */             
/*     */             } else {
/*     */               
/* 129 */               server.populationInfo = EnumChatFormatting.DARK_GRAY + "???";
/*     */             } 
/*     */             
/* 132 */             if (var2.getFavicon() != null) {
/*     */               
/* 134 */               String var8 = var2.getFavicon();
/*     */               
/* 136 */               if (var8.startsWith("data:image/png;base64,"))
/*     */               {
/* 138 */                 server.setBase64EncodedIconData(var8.substring("data:image/png;base64,".length()));
/*     */               }
/*     */               else
/*     */               {
/* 142 */                 OldServerPinger.logger.error("Invalid server icon (unknown format)");
/*     */               }
/*     */             
/*     */             } else {
/*     */               
/* 147 */               server.setBase64EncodedIconData(null);
/*     */             } 
/*     */             
/* 150 */             this.field_175092_e = Minecraft.getSystemTime();
/* 151 */             var3.sendPacket((Packet)new C01PacketPing(this.field_175092_e));
/* 152 */             this.field_147403_d = true;
/*     */           }
/*     */           
/*     */           public void handlePong(S01PacketPong packetIn) {
/* 156 */             long var2 = this.field_175092_e;
/* 157 */             long var4 = Minecraft.getSystemTime();
/* 158 */             server.pingToServer = var4 - var2;
/* 159 */             var3.closeChannel((IChatComponent)new ChatComponentText("Finished"));
/*     */           }
/*     */           
/*     */           public void onDisconnect(IChatComponent reason) {
/* 163 */             if (!this.field_147403_d) {
/*     */               
/* 165 */               OldServerPinger.logger.error("Can't ping " + server.serverIP + ": " + reason.getUnformattedText());
/* 166 */               server.serverMOTD = EnumChatFormatting.DARK_RED + "Can't connect to server.";
/* 167 */               server.populationInfo = "";
/* 168 */               OldServerPinger.this.tryCompatibilityPing(server);
/*     */             } 
/*     */           }
/*     */         });
/*     */ 
/*     */     
/*     */     try {
/* 175 */       var3.sendPacket((Packet)new C00Handshake(47, var2.getIP(), var2.getPort(), EnumConnectionState.STATUS));
/* 176 */       var3.sendPacket((Packet)new C00PacketServerQuery());
/*     */     }
/* 178 */     catch (Throwable var5) {
/*     */       
/* 180 */       logger.error(var5);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void tryCompatibilityPing(final ServerData server) {
/* 186 */     final ServerAddress var2 = ServerAddress.func_78860_a(server.serverIP);
/* 187 */     ((Bootstrap)((Bootstrap)((Bootstrap)(new Bootstrap()).group((EventLoopGroup)NetworkManager.CLIENT_NIO_EVENTLOOP.getValue())).handler((ChannelHandler)new ChannelInitializer()
/*     */         {
/*     */           private static final String __OBFID = "CL_00000894";
/*     */ 
/*     */           
/*     */           protected void initChannel(Channel p_initChannel_1_) {
/*     */             try {
/* 194 */               p_initChannel_1_.config().setOption(ChannelOption.IP_TOS, Integer.valueOf(24));
/*     */             }
/* 196 */             catch (ChannelException channelException) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */             
/*     */             try {
/* 203 */               p_initChannel_1_.config().setOption(ChannelOption.TCP_NODELAY, Boolean.valueOf(false));
/*     */             }
/* 205 */             catch (ChannelException channelException) {}
/*     */ 
/*     */ 
/*     */ 
/*     */             
/* 210 */             p_initChannel_1_.pipeline().addLast(new ChannelHandler[] { (ChannelHandler)new SimpleChannelInboundHandler()
/*     */                   {
/*     */                     private static final String __OBFID = "CL_00000895";
/*     */                     
/*     */                     public void channelActive(ChannelHandlerContext p_channelActive_1_) throws Exception {
/* 215 */                       super.channelActive(p_channelActive_1_);
/* 216 */                       ByteBuf var2x = Unpooled.buffer();
/*     */ 
/*     */                       
/*     */                       try {
/* 220 */                         var2x.writeByte(254);
/* 221 */                         var2x.writeByte(1);
/* 222 */                         var2x.writeByte(250);
/* 223 */                         char[] var3 = "MC|PingHost".toCharArray();
/* 224 */                         var2x.writeShort(var3.length);
/* 225 */                         char[] var4 = var3;
/* 226 */                         int var5 = var3.length;
/*     */                         
/*     */                         int var6;
/*     */                         
/* 230 */                         for (var6 = 0; var6 < var5; var6++) {
/*     */                           
/* 232 */                           char var7 = var4[var6];
/* 233 */                           var2x.writeChar(var7);
/*     */                         } 
/*     */                         
/* 236 */                         var2x.writeShort(7 + 2 * var2.getIP().length());
/* 237 */                         var2x.writeByte(127);
/* 238 */                         var3 = var2.getIP().toCharArray();
/* 239 */                         var2x.writeShort(var3.length);
/* 240 */                         var4 = var3;
/* 241 */                         var5 = var3.length;
/*     */                         
/* 243 */                         for (var6 = 0; var6 < var5; var6++) {
/*     */                           
/* 245 */                           char var7 = var4[var6];
/* 246 */                           var2x.writeChar(var7);
/*     */                         } 
/*     */                         
/* 249 */                         var2x.writeInt(var2.getPort());
/* 250 */                         p_channelActive_1_.channel().writeAndFlush(var2x).addListener((GenericFutureListener)ChannelFutureListener.CLOSE_ON_FAILURE);
/*     */                       }
/*     */                       finally {
/*     */                         
/* 254 */                         var2x.release();
/*     */                       } 
/*     */                     }
/*     */                     
/*     */                     protected void channelRead0(ChannelHandlerContext p_channelRead0_1_, ByteBuf p_channelRead0_2_) {
/* 259 */                       short var3 = p_channelRead0_2_.readUnsignedByte();
/*     */                       
/* 261 */                       if (var3 == 255) {
/*     */                         
/* 263 */                         String var4 = new String(p_channelRead0_2_.readBytes(p_channelRead0_2_.readShort() * 2).array(), Charsets.UTF_16BE);
/* 264 */                         String[] var5 = (String[])Iterables.toArray(OldServerPinger.PING_RESPONSE_SPLITTER.split(var4), String.class);
/*     */                         
/* 266 */                         if ("§1".equals(var5[0])) {
/*     */                           
/* 268 */                           int var6 = MathHelper.parseIntWithDefault(var5[1], 0);
/* 269 */                           String var7 = var5[2];
/* 270 */                           String var8 = var5[3];
/* 271 */                           int var9 = MathHelper.parseIntWithDefault(var5[4], -1);
/* 272 */                           int var10 = MathHelper.parseIntWithDefault(var5[5], -1);
/* 273 */                           server.version = -1;
/* 274 */                           server.gameVersion = var7;
/* 275 */                           server.serverMOTD = var8;
/* 276 */                           server.populationInfo = EnumChatFormatting.GRAY + var9 + EnumChatFormatting.DARK_GRAY + "/" + EnumChatFormatting.GRAY + var10;
/*     */                         } 
/*     */                       } 
/*     */                       
/* 280 */                       p_channelRead0_1_.close();
/*     */                     }
/*     */                     
/*     */                     public void exceptionCaught(ChannelHandlerContext p_exceptionCaught_1_, Throwable p_exceptionCaught_2_) {
/* 284 */                       p_exceptionCaught_1_.close();
/*     */                     }
/*     */                     
/*     */                     protected void channelRead0(ChannelHandlerContext p_channelRead0_1_, Object p_channelRead0_2_) {
/* 288 */                       channelRead0(p_channelRead0_1_, (ByteBuf)p_channelRead0_2_);
/*     */                     }
/*     */                   }
/*     */                 });
/*     */           }
/* 293 */         })).channel(NioSocketChannel.class)).connect(var2.getIP(), var2.getPort());
/*     */   }
/*     */ 
/*     */   
/*     */   public void pingPendingNetworks() {
/* 298 */     List var1 = this.pingDestinations;
/*     */     
/* 300 */     synchronized (this.pingDestinations) {
/*     */       
/* 302 */       Iterator<NetworkManager> var2 = this.pingDestinations.iterator();
/*     */       
/* 304 */       while (var2.hasNext()) {
/*     */         
/* 306 */         NetworkManager var3 = var2.next();
/*     */         
/* 308 */         if (var3.isChannelOpen()) {
/*     */           
/* 310 */           var3.processReceivedPackets();
/*     */           
/*     */           continue;
/*     */         } 
/* 314 */         var2.remove();
/* 315 */         var3.checkDisconnected();
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void clearPendingNetworks() {
/* 323 */     List var1 = this.pingDestinations;
/*     */     
/* 325 */     synchronized (this.pingDestinations) {
/*     */       
/* 327 */       Iterator<NetworkManager> var2 = this.pingDestinations.iterator();
/*     */       
/* 329 */       while (var2.hasNext()) {
/*     */         
/* 331 */         NetworkManager var3 = var2.next();
/*     */         
/* 333 */         if (var3.isChannelOpen()) {
/*     */           
/* 335 */           var2.remove();
/* 336 */           var3.closeChannel((IChatComponent)new ChatComponentText("Cancelled"));
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\network\OldServerPinger.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */