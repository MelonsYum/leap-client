/*     */ package net.minecraft.client.network;
/*     */ import com.mojang.authlib.GameProfile;
/*     */ import com.mojang.authlib.exceptions.AuthenticationException;
/*     */ import com.mojang.authlib.exceptions.AuthenticationUnavailableException;
/*     */ import com.mojang.authlib.exceptions.InvalidCredentialsException;
/*     */ import com.mojang.authlib.minecraft.MinecraftSessionService;
/*     */ import io.netty.util.concurrent.Future;
/*     */ import io.netty.util.concurrent.GenericFutureListener;
/*     */ import java.math.BigInteger;
/*     */ import java.security.PublicKey;
/*     */ import javax.crypto.SecretKey;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.gui.GuiDisconnected;
/*     */ import net.minecraft.client.gui.GuiScreen;
/*     */ import net.minecraft.network.EnumConnectionState;
/*     */ import net.minecraft.network.INetHandler;
/*     */ import net.minecraft.network.NetworkManager;
/*     */ import net.minecraft.network.Packet;
/*     */ import net.minecraft.network.login.INetHandlerLoginClient;
/*     */ import net.minecraft.network.login.client.C01PacketEncryptionResponse;
/*     */ import net.minecraft.network.login.server.S00PacketDisconnect;
/*     */ import net.minecraft.network.login.server.S01PacketEncryptionRequest;
/*     */ import net.minecraft.network.login.server.S02PacketLoginSuccess;
/*     */ import net.minecraft.network.login.server.S03PacketEnableCompression;
/*     */ import net.minecraft.util.ChatComponentTranslation;
/*     */ import net.minecraft.util.CryptManager;
/*     */ import net.minecraft.util.IChatComponent;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ 
/*     */ public class NetHandlerLoginClient implements INetHandlerLoginClient {
/*  32 */   private static final Logger logger = LogManager.getLogger();
/*     */   
/*     */   private final Minecraft field_147394_b;
/*     */   private final GuiScreen field_147395_c;
/*     */   private final NetworkManager field_147393_d;
/*     */   private GameProfile field_175091_e;
/*     */   private static final String __OBFID = "CL_00000876";
/*     */   
/*     */   public NetHandlerLoginClient(NetworkManager p_i45059_1_, Minecraft mcIn, GuiScreen p_i45059_3_) {
/*  41 */     this.field_147393_d = p_i45059_1_;
/*  42 */     this.field_147394_b = mcIn;
/*  43 */     this.field_147395_c = p_i45059_3_;
/*     */   }
/*     */ 
/*     */   
/*     */   public void handleEncryptionRequest(S01PacketEncryptionRequest packetIn) {
/*  48 */     final SecretKey var2 = CryptManager.createNewSharedKey();
/*  49 */     String var3 = packetIn.func_149609_c();
/*  50 */     PublicKey var4 = packetIn.func_149608_d();
/*  51 */     String var5 = (new BigInteger(CryptManager.getServerIdHash(var3, var4, var2))).toString(16);
/*     */ 
/*     */     
/*     */     try {
/*  55 */       func_147391_c().joinServer(this.field_147394_b.getSession().getProfile(), this.field_147394_b.getSession().getToken(), var5);
/*     */     }
/*  57 */     catch (AuthenticationUnavailableException var7) {
/*     */       
/*  59 */       this.field_147393_d.closeChannel((IChatComponent)new ChatComponentTranslation("disconnect.loginFailedInfo", new Object[] { new ChatComponentTranslation("disconnect.loginFailedInfo.serversUnavailable", new Object[0]) }));
/*     */       
/*     */       return;
/*  62 */     } catch (InvalidCredentialsException var8) {
/*     */       
/*  64 */       this.field_147393_d.closeChannel((IChatComponent)new ChatComponentTranslation("disconnect.loginFailedInfo", new Object[] { new ChatComponentTranslation("disconnect.loginFailedInfo.invalidSession", new Object[0]) }));
/*     */       
/*     */       return;
/*  67 */     } catch (AuthenticationException var9) {
/*     */       
/*  69 */       this.field_147393_d.closeChannel((IChatComponent)new ChatComponentTranslation("disconnect.loginFailedInfo", new Object[] { var9.getMessage() }));
/*     */       
/*     */       return;
/*     */     } 
/*  73 */     this.field_147393_d.sendPacket((Packet)new C01PacketEncryptionResponse(var2, var4, packetIn.func_149607_e()), new GenericFutureListener()
/*     */         {
/*     */           private static final String __OBFID = "CL_00000877";
/*     */           
/*     */           public void operationComplete(Future p_operationComplete_1_) {
/*  78 */             NetHandlerLoginClient.this.field_147393_d.enableEncryption(var2);
/*     */           }
/*  80 */         },  new GenericFutureListener[0]);
/*     */   }
/*     */ 
/*     */   
/*     */   private MinecraftSessionService func_147391_c() {
/*  85 */     return this.field_147394_b.getSessionService();
/*     */   }
/*     */ 
/*     */   
/*     */   public void handleLoginSuccess(S02PacketLoginSuccess packetIn) {
/*  90 */     this.field_175091_e = packetIn.func_179730_a();
/*  91 */     this.field_147393_d.setConnectionState(EnumConnectionState.PLAY);
/*  92 */     this.field_147393_d.setNetHandler((INetHandler)new NetHandlerPlayClient(this.field_147394_b, this.field_147395_c, this.field_147393_d, this.field_175091_e));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onDisconnect(IChatComponent reason) {
/* 100 */     this.field_147394_b.displayGuiScreen((GuiScreen)new GuiDisconnected(this.field_147395_c, "connect.failed", reason));
/*     */   }
/*     */ 
/*     */   
/*     */   public void handleDisconnect(S00PacketDisconnect packetIn) {
/* 105 */     this.field_147393_d.closeChannel(packetIn.func_149603_c());
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_180464_a(S03PacketEnableCompression p_180464_1_) {
/* 110 */     if (!this.field_147393_d.isLocalChannel())
/*     */     {
/* 112 */       this.field_147393_d.setCompressionTreshold(p_180464_1_.func_179731_a());
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\network\NetHandlerLoginClient.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */