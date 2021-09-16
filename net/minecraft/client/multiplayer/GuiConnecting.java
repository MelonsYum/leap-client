/*     */ package net.minecraft.client.multiplayer;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.net.InetAddress;
/*     */ import java.net.UnknownHostException;
/*     */ import java.util.concurrent.atomic.AtomicInteger;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.gui.GuiButton;
/*     */ import net.minecraft.client.gui.GuiDisconnected;
/*     */ import net.minecraft.client.gui.GuiScreen;
/*     */ import net.minecraft.client.network.NetHandlerLoginClient;
/*     */ import net.minecraft.client.resources.I18n;
/*     */ import net.minecraft.network.EnumConnectionState;
/*     */ import net.minecraft.network.INetHandler;
/*     */ import net.minecraft.network.NetworkManager;
/*     */ import net.minecraft.network.Packet;
/*     */ import net.minecraft.network.handshake.client.C00Handshake;
/*     */ import net.minecraft.network.login.client.C00PacketLoginStart;
/*     */ import net.minecraft.util.ChatComponentText;
/*     */ import net.minecraft.util.ChatComponentTranslation;
/*     */ import net.minecraft.util.IChatComponent;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ 
/*     */ public class GuiConnecting extends GuiScreen {
/*  26 */   private static final AtomicInteger CONNECTION_ID = new AtomicInteger(0);
/*  27 */   private static final Logger logger = LogManager.getLogger();
/*     */   
/*     */   private NetworkManager networkManager;
/*     */   private boolean cancel;
/*     */   private final GuiScreen previousGuiScreen;
/*     */   private static final String __OBFID = "CL_00000685";
/*     */   
/*     */   public GuiConnecting(GuiScreen p_i1181_1_, Minecraft mcIn, ServerData p_i1181_3_) {
/*  35 */     this.mc = mcIn;
/*  36 */     this.previousGuiScreen = p_i1181_1_;
/*  37 */     ServerAddress var4 = ServerAddress.func_78860_a(p_i1181_3_.serverIP);
/*  38 */     mcIn.loadWorld(null);
/*  39 */     mcIn.setServerData(p_i1181_3_);
/*  40 */     connect(var4.getIP(), var4.getPort());
/*     */   }
/*     */ 
/*     */   
/*     */   public GuiConnecting(GuiScreen p_i1182_1_, Minecraft mcIn, String p_i1182_3_, int p_i1182_4_) {
/*  45 */     this.mc = mcIn;
/*  46 */     this.previousGuiScreen = p_i1182_1_;
/*  47 */     mcIn.loadWorld(null);
/*  48 */     connect(p_i1182_3_, p_i1182_4_);
/*     */   }
/*     */ 
/*     */   
/*     */   private void connect(final String ip, final int port) {
/*  53 */     logger.info("Connecting to " + ip + ", " + port);
/*  54 */     (new Thread("Server Connector #" + CONNECTION_ID.incrementAndGet())
/*     */       {
/*     */         private static final String __OBFID = "CL_00000686";
/*     */         
/*     */         public void run() {
/*  59 */           InetAddress var1 = null;
/*     */ 
/*     */           
/*     */           try {
/*  63 */             if (GuiConnecting.this.cancel) {
/*     */               return;
/*     */             }
/*     */ 
/*     */             
/*  68 */             var1 = InetAddress.getByName(ip);
/*  69 */             GuiConnecting.this.networkManager = NetworkManager.provideLanClient(var1, port);
/*  70 */             GuiConnecting.this.networkManager.setNetHandler((INetHandler)new NetHandlerLoginClient(GuiConnecting.this.networkManager, GuiConnecting.this.mc, GuiConnecting.this.previousGuiScreen));
/*  71 */             GuiConnecting.this.networkManager.sendPacket((Packet)new C00Handshake(47, ip, port, EnumConnectionState.LOGIN));
/*  72 */             GuiConnecting.this.networkManager.sendPacket((Packet)new C00PacketLoginStart(GuiConnecting.this.mc.getSession().getProfile()));
/*     */           }
/*  74 */           catch (UnknownHostException var5) {
/*     */             
/*  76 */             if (GuiConnecting.this.cancel) {
/*     */               return;
/*     */             }
/*     */ 
/*     */             
/*  81 */             GuiConnecting.logger.error("Couldn't connect to server", var5);
/*  82 */             GuiConnecting.this.mc.displayGuiScreen((GuiScreen)new GuiDisconnected(GuiConnecting.this.previousGuiScreen, "connect.failed", (IChatComponent)new ChatComponentTranslation("disconnect.genericReason", new Object[] { "Unknown host" })));
/*     */           }
/*  84 */           catch (Exception var6) {
/*     */             
/*  86 */             if (GuiConnecting.this.cancel) {
/*     */               return;
/*     */             }
/*     */ 
/*     */             
/*  91 */             GuiConnecting.logger.error("Couldn't connect to server", var6);
/*  92 */             String var3 = var6.toString();
/*     */             
/*  94 */             if (var1 != null) {
/*     */               
/*  96 */               String var4 = String.valueOf(var1.toString()) + ":" + port;
/*  97 */               var3 = var3.replaceAll(var4, "");
/*     */             } 
/*     */             
/* 100 */             GuiConnecting.this.mc.displayGuiScreen((GuiScreen)new GuiDisconnected(GuiConnecting.this.previousGuiScreen, "connect.failed", (IChatComponent)new ChatComponentTranslation("disconnect.genericReason", new Object[] { var3 })));
/*     */           } 
/*     */         }
/* 103 */       }).start();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void updateScreen() {
/* 111 */     if (this.networkManager != null)
/*     */     {
/* 113 */       if (this.networkManager.isChannelOpen()) {
/*     */         
/* 115 */         this.networkManager.processReceivedPackets();
/*     */       }
/*     */       else {
/*     */         
/* 119 */         this.networkManager.checkDisconnected();
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void keyTyped(char typedChar, int keyCode) throws IOException {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void initGui() {
/* 135 */     this.buttonList.clear();
/* 136 */     this.buttonList.add(new GuiButton(0, width / 2 - 100, height / 4 + 120 + 12, I18n.format("gui.cancel", new Object[0])));
/*     */   }
/*     */ 
/*     */   
/*     */   protected void actionPerformed(GuiButton button) throws IOException {
/* 141 */     if (button.id == 0) {
/*     */       
/* 143 */       this.cancel = true;
/*     */       
/* 145 */       if (this.networkManager != null)
/*     */       {
/* 147 */         this.networkManager.closeChannel((IChatComponent)new ChatComponentText("Aborted"));
/*     */       }
/*     */       
/* 150 */       this.mc.displayGuiScreen(this.previousGuiScreen);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void drawScreen(int mouseX, int mouseY, float partialTicks) {
/* 159 */     drawDefaultBackground();
/*     */     
/* 161 */     if (this.networkManager == null) {
/*     */       
/* 163 */       drawCenteredString(fontRendererObj, I18n.format("connect.connecting", new Object[0]), (width / 2), (height / 2 - 50), 16777215);
/*     */     }
/*     */     else {
/*     */       
/* 167 */       drawCenteredString(fontRendererObj, I18n.format("connect.authorizing", new Object[0]), (width / 2), (height / 2 - 50), 16777215);
/*     */     } 
/*     */     
/* 170 */     super.drawScreen(mouseX, mouseY, partialTicks);
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\multiplayer\GuiConnecting.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */