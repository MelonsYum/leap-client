/*    */ package net.minecraft.server.network;
/*    */ 
/*    */ import net.minecraft.network.EnumConnectionState;
/*    */ import net.minecraft.network.INetHandler;
/*    */ import net.minecraft.network.NetworkManager;
/*    */ import net.minecraft.network.Packet;
/*    */ import net.minecraft.network.handshake.INetHandlerHandshakeServer;
/*    */ import net.minecraft.network.handshake.client.C00Handshake;
/*    */ import net.minecraft.network.login.server.S00PacketDisconnect;
/*    */ import net.minecraft.server.MinecraftServer;
/*    */ import net.minecraft.util.ChatComponentText;
/*    */ import net.minecraft.util.IChatComponent;
/*    */ 
/*    */ public class NetHandlerHandshakeTCP implements INetHandlerHandshakeServer {
/*    */   private final MinecraftServer server;
/*    */   private final NetworkManager networkManager;
/*    */   private static final String __OBFID = "CL_00001456";
/*    */   
/*    */   public NetHandlerHandshakeTCP(MinecraftServer serverIn, NetworkManager netManager) {
/* 20 */     this.server = serverIn;
/* 21 */     this.networkManager = netManager;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void processHandshake(C00Handshake packetIn) {
/* 31 */     switch (SwitchEnumConnectionState.VALUES[packetIn.getRequestedState().ordinal()]) {
/*    */       
/*    */       case 1:
/* 34 */         this.networkManager.setConnectionState(EnumConnectionState.LOGIN);
/*    */ 
/*    */         
/* 37 */         if (packetIn.getProtocolVersion() > 47) {
/*    */           
/* 39 */           ChatComponentText var2 = new ChatComponentText("Outdated server! I'm still on 1.8");
/* 40 */           this.networkManager.sendPacket((Packet)new S00PacketDisconnect((IChatComponent)var2));
/* 41 */           this.networkManager.closeChannel((IChatComponent)var2);
/*    */         }
/* 43 */         else if (packetIn.getProtocolVersion() < 47) {
/*    */           
/* 45 */           ChatComponentText var2 = new ChatComponentText("Outdated client! Please use 1.8");
/* 46 */           this.networkManager.sendPacket((Packet)new S00PacketDisconnect((IChatComponent)var2));
/* 47 */           this.networkManager.closeChannel((IChatComponent)var2);
/*    */         }
/*    */         else {
/*    */           
/* 51 */           this.networkManager.setNetHandler((INetHandler)new NetHandlerLoginServer(this.server, this.networkManager));
/*    */         } 
/*    */         return;
/*    */ 
/*    */       
/*    */       case 2:
/* 57 */         this.networkManager.setConnectionState(EnumConnectionState.STATUS);
/* 58 */         this.networkManager.setNetHandler((INetHandler)new NetHandlerStatusServer(this.server, this.networkManager));
/*    */         return;
/*    */     } 
/*    */     
/* 62 */     throw new UnsupportedOperationException("Invalid intention " + packetIn.getRequestedState());
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void onDisconnect(IChatComponent reason) {}
/*    */ 
/*    */ 
/*    */   
/*    */   static final class SwitchEnumConnectionState
/*    */   {
/* 73 */     static final int[] VALUES = new int[(EnumConnectionState.values()).length];
/*    */     
/*    */     private static final String __OBFID = "CL_00001457";
/*    */ 
/*    */     
/*    */     static {
/*    */       try {
/* 80 */         VALUES[EnumConnectionState.LOGIN.ordinal()] = 1;
/*    */       }
/* 82 */       catch (NoSuchFieldError noSuchFieldError) {}
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */       
/*    */       try {
/* 89 */         VALUES[EnumConnectionState.STATUS.ordinal()] = 2;
/*    */       }
/* 91 */       catch (NoSuchFieldError noSuchFieldError) {}
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\server\network\NetHandlerHandshakeTCP.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */