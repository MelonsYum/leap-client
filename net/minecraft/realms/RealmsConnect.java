/*     */ package net.minecraft.realms;
/*     */ import java.net.InetAddress;
/*     */ import java.net.UnknownHostException;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.gui.GuiScreen;
/*     */ import net.minecraft.client.network.NetHandlerLoginClient;
/*     */ import net.minecraft.network.EnumConnectionState;
/*     */ import net.minecraft.network.INetHandler;
/*     */ import net.minecraft.network.NetworkManager;
/*     */ import net.minecraft.network.Packet;
/*     */ import net.minecraft.network.login.client.C00PacketLoginStart;
/*     */ import net.minecraft.util.ChatComponentTranslation;
/*     */ import net.minecraft.util.IChatComponent;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ 
/*     */ public class RealmsConnect {
/*  17 */   private static final Logger LOGGER = LogManager.getLogger();
/*     */   
/*     */   private final RealmsScreen onlineScreen;
/*     */   private volatile boolean aborted = false;
/*     */   private NetworkManager connection;
/*     */   private static final String __OBFID = "CL_00001844";
/*     */   
/*     */   public RealmsConnect(RealmsScreen p_i1079_1_) {
/*  25 */     this.onlineScreen = p_i1079_1_;
/*     */   }
/*     */ 
/*     */   
/*     */   public void connect(final String p_connect_1_, final int p_connect_2_) {
/*  30 */     (new Thread("Realms-connect-task")
/*     */       {
/*     */         private static final String __OBFID = "CL_00001808";
/*     */         
/*     */         public void run() {
/*  35 */           InetAddress var1 = null;
/*     */ 
/*     */           
/*     */           try {
/*  39 */             var1 = InetAddress.getByName(p_connect_1_);
/*     */             
/*  41 */             if (RealmsConnect.this.aborted) {
/*     */               return;
/*     */             }
/*     */ 
/*     */             
/*  46 */             RealmsConnect.this.connection = NetworkManager.provideLanClient(var1, p_connect_2_);
/*     */             
/*  48 */             if (RealmsConnect.this.aborted) {
/*     */               return;
/*     */             }
/*     */ 
/*     */             
/*  53 */             RealmsConnect.this.connection.setNetHandler((INetHandler)new NetHandlerLoginClient(RealmsConnect.this.connection, Minecraft.getMinecraft(), (GuiScreen)RealmsConnect.this.onlineScreen.getProxy()));
/*     */             
/*  55 */             if (RealmsConnect.this.aborted) {
/*     */               return;
/*     */             }
/*     */ 
/*     */             
/*  60 */             RealmsConnect.this.connection.sendPacket((Packet)new C00Handshake(47, p_connect_1_, p_connect_2_, EnumConnectionState.LOGIN));
/*     */             
/*  62 */             if (RealmsConnect.this.aborted) {
/*     */               return;
/*     */             }
/*     */ 
/*     */             
/*  67 */             RealmsConnect.this.connection.sendPacket((Packet)new C00PacketLoginStart(Minecraft.getMinecraft().getSession().getProfile()));
/*     */           }
/*  69 */           catch (UnknownHostException var5) {
/*     */             
/*  71 */             if (RealmsConnect.this.aborted) {
/*     */               return;
/*     */             }
/*     */ 
/*     */             
/*  76 */             RealmsConnect.LOGGER.error("Couldn't connect to world", var5);
/*  77 */             Realms.setScreen(new DisconnectedRealmsScreen(RealmsConnect.this.onlineScreen, "connect.failed", (IChatComponent)new ChatComponentTranslation("disconnect.genericReason", new Object[] { "Unknown host '" + this.val$p_connect_1_ + "'" })));
/*     */           }
/*  79 */           catch (Exception var6) {
/*     */             
/*  81 */             if (RealmsConnect.this.aborted) {
/*     */               return;
/*     */             }
/*     */ 
/*     */             
/*  86 */             RealmsConnect.LOGGER.error("Couldn't connect to world", var6);
/*  87 */             String var3 = var6.toString();
/*     */             
/*  89 */             if (var1 != null) {
/*     */               
/*  91 */               String var4 = String.valueOf(var1.toString()) + ":" + p_connect_2_;
/*  92 */               var3 = var3.replaceAll(var4, "");
/*     */             } 
/*     */             
/*  95 */             Realms.setScreen(new DisconnectedRealmsScreen(RealmsConnect.this.onlineScreen, "connect.failed", (IChatComponent)new ChatComponentTranslation("disconnect.genericReason", new Object[] { var3 })));
/*     */           } 
/*     */         }
/*  98 */       }).start();
/*     */   }
/*     */ 
/*     */   
/*     */   public void abort() {
/* 103 */     this.aborted = true;
/*     */   }
/*     */ 
/*     */   
/*     */   public void tick() {
/* 108 */     if (this.connection != null)
/*     */     {
/* 110 */       if (this.connection.isChannelOpen()) {
/*     */         
/* 112 */         this.connection.processReceivedPackets();
/*     */       }
/*     */       else {
/*     */         
/* 116 */         this.connection.checkDisconnected();
/*     */       } 
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\realms\RealmsConnect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */