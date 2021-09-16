/*     */ package net.minecraft.realms;
/*     */ import com.google.common.collect.Lists;
/*     */ import java.net.InetAddress;
/*     */ import java.net.UnknownHostException;
/*     */ import java.util.Collections;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import net.minecraft.network.EnumConnectionState;
/*     */ import net.minecraft.network.INetHandler;
/*     */ import net.minecraft.network.NetworkManager;
/*     */ import net.minecraft.network.Packet;
/*     */ import net.minecraft.network.ServerStatusResponse;
/*     */ import net.minecraft.network.handshake.client.C00Handshake;
/*     */ import net.minecraft.network.status.INetHandlerStatusClient;
/*     */ import net.minecraft.network.status.client.C00PacketServerQuery;
/*     */ import net.minecraft.network.status.client.C01PacketPing;
/*     */ import net.minecraft.network.status.server.S00PacketServerInfo;
/*     */ import net.minecraft.network.status.server.S01PacketPong;
/*     */ import net.minecraft.util.ChatComponentText;
/*     */ import net.minecraft.util.IChatComponent;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ 
/*     */ public class RealmsServerStatusPinger {
/*  25 */   private static final Logger LOGGER = LogManager.getLogger();
/*  26 */   private final List connections = Collections.synchronizedList(Lists.newArrayList());
/*     */   
/*     */   private static final String __OBFID = "CL_00001854";
/*     */   
/*     */   public void pingServer(final String p_pingServer_1_, final RealmsServerPing p_pingServer_2_) throws UnknownHostException {
/*  31 */     if (p_pingServer_1_ != null && !p_pingServer_1_.startsWith("0.0.0.0") && !p_pingServer_1_.isEmpty()) {
/*     */       
/*  33 */       RealmsServerAddress var3 = RealmsServerAddress.parseString(p_pingServer_1_);
/*  34 */       final NetworkManager var4 = NetworkManager.provideLanClient(InetAddress.getByName(var3.getHost()), var3.getPort());
/*  35 */       this.connections.add(var4);
/*  36 */       var4.setNetHandler((INetHandler)new INetHandlerStatusClient()
/*     */           {
/*     */             private boolean field_154345_e = false;
/*     */             private static final String __OBFID = "CL_00001807";
/*     */             
/*     */             public void handleServerInfo(S00PacketServerInfo packetIn) {
/*  42 */               ServerStatusResponse var2 = packetIn.func_149294_c();
/*     */               
/*  44 */               if (var2.getPlayerCountData() != null)
/*     */               {
/*  46 */                 p_pingServer_2_.nrOfPlayers = String.valueOf(var2.getPlayerCountData().getOnlinePlayerCount());
/*     */               }
/*     */               
/*  49 */               var4.sendPacket((Packet)new C01PacketPing(Realms.currentTimeMillis()));
/*  50 */               this.field_154345_e = true;
/*     */             }
/*     */             
/*     */             public void handlePong(S01PacketPong packetIn) {
/*  54 */               var4.closeChannel((IChatComponent)new ChatComponentText("Finished"));
/*     */             }
/*     */             
/*     */             public void onDisconnect(IChatComponent reason) {
/*  58 */               if (!this.field_154345_e)
/*     */               {
/*  60 */                 RealmsServerStatusPinger.LOGGER.error("Can't ping " + p_pingServer_1_ + ": " + reason.getUnformattedText());
/*     */               }
/*     */             }
/*     */           });
/*     */ 
/*     */       
/*     */       try {
/*  67 */         var4.sendPacket((Packet)new C00Handshake(RealmsSharedConstants.NETWORK_PROTOCOL_VERSION, var3.getHost(), var3.getPort(), EnumConnectionState.STATUS));
/*  68 */         var4.sendPacket((Packet)new C00PacketServerQuery());
/*     */       }
/*  70 */       catch (Throwable var6) {
/*     */         
/*  72 */         LOGGER.error(var6);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void tick() {
/*  79 */     List var1 = this.connections;
/*     */     
/*  81 */     synchronized (this.connections) {
/*     */       
/*  83 */       Iterator<NetworkManager> var2 = this.connections.iterator();
/*     */       
/*  85 */       while (var2.hasNext()) {
/*     */         
/*  87 */         NetworkManager var3 = var2.next();
/*     */         
/*  89 */         if (var3.isChannelOpen()) {
/*     */           
/*  91 */           var3.processReceivedPackets();
/*     */           
/*     */           continue;
/*     */         } 
/*  95 */         var2.remove();
/*  96 */         var3.checkDisconnected();
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void removeAll() {
/* 104 */     List var1 = this.connections;
/*     */     
/* 106 */     synchronized (this.connections) {
/*     */       
/* 108 */       Iterator<NetworkManager> var2 = this.connections.iterator();
/*     */       
/* 110 */       while (var2.hasNext()) {
/*     */         
/* 112 */         NetworkManager var3 = var2.next();
/*     */         
/* 114 */         if (var3.isChannelOpen()) {
/*     */           
/* 116 */           var2.remove();
/* 117 */           var3.closeChannel((IChatComponent)new ChatComponentText("Cancelled"));
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\realms\RealmsServerStatusPinger.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */