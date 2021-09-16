/*     */ package net.minecraft.server.network;
/*     */ 
/*     */ import com.google.common.base.Charsets;
/*     */ import com.mojang.authlib.GameProfile;
/*     */ import com.mojang.authlib.exceptions.AuthenticationUnavailableException;
/*     */ import io.netty.channel.ChannelFuture;
/*     */ import io.netty.channel.ChannelFutureListener;
/*     */ import io.netty.util.concurrent.Future;
/*     */ import io.netty.util.concurrent.GenericFutureListener;
/*     */ import java.math.BigInteger;
/*     */ import java.security.PrivateKey;
/*     */ import java.util.Arrays;
/*     */ import java.util.Random;
/*     */ import java.util.UUID;
/*     */ import java.util.concurrent.atomic.AtomicInteger;
/*     */ import javax.crypto.SecretKey;
/*     */ import net.minecraft.network.NetworkManager;
/*     */ import net.minecraft.network.Packet;
/*     */ import net.minecraft.network.login.INetHandlerLoginServer;
/*     */ import net.minecraft.network.login.client.C00PacketLoginStart;
/*     */ import net.minecraft.network.login.client.C01PacketEncryptionResponse;
/*     */ import net.minecraft.network.login.server.S00PacketDisconnect;
/*     */ import net.minecraft.network.login.server.S01PacketEncryptionRequest;
/*     */ import net.minecraft.network.login.server.S02PacketLoginSuccess;
/*     */ import net.minecraft.network.login.server.S03PacketEnableCompression;
/*     */ import net.minecraft.server.MinecraftServer;
/*     */ import net.minecraft.server.gui.IUpdatePlayerListBox;
/*     */ import net.minecraft.util.ChatComponentText;
/*     */ import net.minecraft.util.CryptManager;
/*     */ import net.minecraft.util.IChatComponent;
/*     */ import org.apache.commons.lang3.Validate;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ 
/*     */ public class NetHandlerLoginServer implements INetHandlerLoginServer, IUpdatePlayerListBox {
/*  36 */   private static final AtomicInteger AUTHENTICATOR_THREAD_ID = new AtomicInteger(0);
/*  37 */   private static final Logger logger = LogManager.getLogger();
/*  38 */   private static final Random RANDOM = new Random();
/*  39 */   private final byte[] field_147330_e = new byte[4];
/*     */   
/*     */   private final MinecraftServer server;
/*     */   
/*     */   public final NetworkManager networkManager;
/*     */   
/*     */   private LoginState currentLoginState;
/*     */   private int connectionTimer;
/*     */   private GameProfile loginGameProfile;
/*     */   private String serverId;
/*     */   private SecretKey secretKey;
/*     */   private static final String __OBFID = "CL_00001458";
/*     */   
/*     */   public NetHandlerLoginServer(MinecraftServer p_i45298_1_, NetworkManager p_i45298_2_) {
/*  53 */     this.currentLoginState = LoginState.HELLO;
/*  54 */     this.serverId = "";
/*  55 */     this.server = p_i45298_1_;
/*  56 */     this.networkManager = p_i45298_2_;
/*  57 */     RANDOM.nextBytes(this.field_147330_e);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void update() {
/*  65 */     if (this.currentLoginState == LoginState.READY_TO_ACCEPT)
/*     */     {
/*  67 */       func_147326_c();
/*     */     }
/*     */     
/*  70 */     if (this.connectionTimer++ == 600)
/*     */     {
/*  72 */       closeConnection("Took too long to log in");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void closeConnection(String reason) {
/*     */     try {
/*  80 */       logger.info("Disconnecting " + func_147317_d() + ": " + reason);
/*  81 */       ChatComponentText var2 = new ChatComponentText(reason);
/*  82 */       this.networkManager.sendPacket((Packet)new S00PacketDisconnect((IChatComponent)var2));
/*  83 */       this.networkManager.closeChannel((IChatComponent)var2);
/*     */     }
/*  85 */     catch (Exception var3) {
/*     */       
/*  87 */       logger.error("Error whilst disconnecting player", var3);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_147326_c() {
/*  93 */     if (!this.loginGameProfile.isComplete())
/*     */     {
/*  95 */       this.loginGameProfile = getOfflineProfile(this.loginGameProfile);
/*     */     }
/*     */     
/*  98 */     String var1 = this.server.getConfigurationManager().allowUserToConnect(this.networkManager.getRemoteAddress(), this.loginGameProfile);
/*     */     
/* 100 */     if (var1 != null) {
/*     */       
/* 102 */       closeConnection(var1);
/*     */     }
/*     */     else {
/*     */       
/* 106 */       this.currentLoginState = LoginState.ACCEPTED;
/*     */       
/* 108 */       if (this.server.getNetworkCompressionTreshold() >= 0 && !this.networkManager.isLocalChannel())
/*     */       {
/* 110 */         this.networkManager.sendPacket((Packet)new S03PacketEnableCompression(this.server.getNetworkCompressionTreshold()), (GenericFutureListener)new ChannelFutureListener()
/*     */             {
/*     */               private static final String __OBFID = "CL_00001459";
/*     */               
/*     */               public void operationComplete(ChannelFuture p_operationComplete_1_) {
/* 115 */                 NetHandlerLoginServer.this.networkManager.setCompressionTreshold(NetHandlerLoginServer.this.server.getNetworkCompressionTreshold());
/*     */               }
/* 117 */             },  new GenericFutureListener[0]);
/*     */       }
/*     */       
/* 120 */       this.networkManager.sendPacket((Packet)new S02PacketLoginSuccess(this.loginGameProfile));
/* 121 */       this.server.getConfigurationManager().initializeConnectionToPlayer(this.networkManager, this.server.getConfigurationManager().createPlayerForUser(this.loginGameProfile));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onDisconnect(IChatComponent reason) {
/* 130 */     logger.info(String.valueOf(func_147317_d()) + " lost connection: " + reason.getUnformattedText());
/*     */   }
/*     */ 
/*     */   
/*     */   public String func_147317_d() {
/* 135 */     return (this.loginGameProfile != null) ? (String.valueOf(this.loginGameProfile.toString()) + " (" + this.networkManager.getRemoteAddress().toString() + ")") : String.valueOf(this.networkManager.getRemoteAddress());
/*     */   }
/*     */ 
/*     */   
/*     */   public void processLoginStart(C00PacketLoginStart packetIn) {
/* 140 */     Validate.validState((this.currentLoginState == LoginState.HELLO), "Unexpected hello packet", new Object[0]);
/* 141 */     this.loginGameProfile = packetIn.getProfile();
/*     */     
/* 143 */     if (this.server.isServerInOnlineMode() && !this.networkManager.isLocalChannel()) {
/*     */       
/* 145 */       this.currentLoginState = LoginState.KEY;
/* 146 */       this.networkManager.sendPacket((Packet)new S01PacketEncryptionRequest(this.serverId, this.server.getKeyPair().getPublic(), this.field_147330_e));
/*     */     }
/*     */     else {
/*     */       
/* 150 */       this.currentLoginState = LoginState.READY_TO_ACCEPT;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void processEncryptionResponse(C01PacketEncryptionResponse packetIn) {
/* 156 */     Validate.validState((this.currentLoginState == LoginState.KEY), "Unexpected key packet", new Object[0]);
/* 157 */     PrivateKey var2 = this.server.getKeyPair().getPrivate();
/*     */     
/* 159 */     if (!Arrays.equals(this.field_147330_e, packetIn.func_149299_b(var2)))
/*     */     {
/* 161 */       throw new IllegalStateException("Invalid nonce!");
/*     */     }
/*     */ 
/*     */     
/* 165 */     this.secretKey = packetIn.func_149300_a(var2);
/* 166 */     this.currentLoginState = LoginState.AUTHENTICATING;
/* 167 */     this.networkManager.enableEncryption(this.secretKey);
/* 168 */     (new Thread("User Authenticator #" + AUTHENTICATOR_THREAD_ID.incrementAndGet())
/*     */       {
/*     */         private static final String __OBFID = "CL_00002268";
/*     */         
/*     */         public void run() {
/* 173 */           GameProfile var1 = NetHandlerLoginServer.this.loginGameProfile;
/*     */ 
/*     */           
/*     */           try {
/* 177 */             String var2 = (new BigInteger(CryptManager.getServerIdHash(NetHandlerLoginServer.this.serverId, NetHandlerLoginServer.this.server.getKeyPair().getPublic(), NetHandlerLoginServer.this.secretKey))).toString(16);
/* 178 */             NetHandlerLoginServer.this.loginGameProfile = NetHandlerLoginServer.this.server.getMinecraftSessionService().hasJoinedServer(new GameProfile(null, var1.getName()), var2);
/*     */             
/* 180 */             if (NetHandlerLoginServer.this.loginGameProfile != null)
/*     */             {
/* 182 */               NetHandlerLoginServer.logger.info("UUID of player " + NetHandlerLoginServer.this.loginGameProfile.getName() + " is " + NetHandlerLoginServer.this.loginGameProfile.getId());
/* 183 */               NetHandlerLoginServer.this.currentLoginState = NetHandlerLoginServer.LoginState.READY_TO_ACCEPT;
/*     */             }
/* 185 */             else if (NetHandlerLoginServer.this.server.isSinglePlayer())
/*     */             {
/* 187 */               NetHandlerLoginServer.logger.warn("Failed to verify username but will let them in anyway!");
/* 188 */               NetHandlerLoginServer.this.loginGameProfile = NetHandlerLoginServer.this.getOfflineProfile(var1);
/* 189 */               NetHandlerLoginServer.this.currentLoginState = NetHandlerLoginServer.LoginState.READY_TO_ACCEPT;
/*     */             }
/*     */             else
/*     */             {
/* 193 */               NetHandlerLoginServer.this.closeConnection("Failed to verify username!");
/* 194 */               NetHandlerLoginServer.logger.error("Username '" + NetHandlerLoginServer.this.loginGameProfile.getName() + "' tried to join with an invalid session");
/*     */             }
/*     */           
/* 197 */           } catch (AuthenticationUnavailableException var3) {
/*     */             
/* 199 */             if (NetHandlerLoginServer.this.server.isSinglePlayer()) {
/*     */               
/* 201 */               NetHandlerLoginServer.logger.warn("Authentication servers are down but will let them in anyway!");
/* 202 */               NetHandlerLoginServer.this.loginGameProfile = NetHandlerLoginServer.this.getOfflineProfile(var1);
/* 203 */               NetHandlerLoginServer.this.currentLoginState = NetHandlerLoginServer.LoginState.READY_TO_ACCEPT;
/*     */             }
/*     */             else {
/*     */               
/* 207 */               NetHandlerLoginServer.this.closeConnection("Authentication servers are down. Please try again later, sorry!");
/* 208 */               NetHandlerLoginServer.logger.error("Couldn't verify username because servers are unavailable");
/*     */             } 
/*     */           } 
/*     */         }
/* 212 */       }).start();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected GameProfile getOfflineProfile(GameProfile original) {
/* 218 */     UUID var2 = UUID.nameUUIDFromBytes(("OfflinePlayer:" + original.getName()).getBytes(Charsets.UTF_8));
/* 219 */     return new GameProfile(var2, original.getName());
/*     */   }
/*     */   
/*     */   enum LoginState
/*     */   {
/* 224 */     HELLO("HELLO", 0),
/* 225 */     KEY("KEY", 1),
/* 226 */     AUTHENTICATING("AUTHENTICATING", 2),
/* 227 */     READY_TO_ACCEPT("READY_TO_ACCEPT", 3),
/* 228 */     ACCEPTED("ACCEPTED", 4);
/*     */     
/* 230 */     private static final LoginState[] $VALUES = new LoginState[] { HELLO, KEY, AUTHENTICATING, READY_TO_ACCEPT, ACCEPTED };
/*     */     private static final String __OBFID = "CL_00001463";
/*     */     
/*     */     static {
/*     */     
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\server\network\NetHandlerLoginServer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */