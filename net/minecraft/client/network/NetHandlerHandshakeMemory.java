/*    */ package net.minecraft.client.network;
/*    */ 
/*    */ import net.minecraft.network.INetHandler;
/*    */ import net.minecraft.network.NetworkManager;
/*    */ import net.minecraft.network.handshake.INetHandlerHandshakeServer;
/*    */ import net.minecraft.network.handshake.client.C00Handshake;
/*    */ import net.minecraft.server.MinecraftServer;
/*    */ import net.minecraft.server.network.NetHandlerLoginServer;
/*    */ import net.minecraft.util.IChatComponent;
/*    */ 
/*    */ public class NetHandlerHandshakeMemory
/*    */   implements INetHandlerHandshakeServer {
/*    */   private final MinecraftServer field_147385_a;
/*    */   private final NetworkManager field_147384_b;
/*    */   private static final String __OBFID = "CL_00001445";
/*    */   
/*    */   public NetHandlerHandshakeMemory(MinecraftServer p_i45287_1_, NetworkManager p_i45287_2_) {
/* 18 */     this.field_147385_a = p_i45287_1_;
/* 19 */     this.field_147384_b = p_i45287_2_;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void processHandshake(C00Handshake packetIn) {
/* 29 */     this.field_147384_b.setConnectionState(packetIn.getRequestedState());
/* 30 */     this.field_147384_b.setNetHandler((INetHandler)new NetHandlerLoginServer(this.field_147385_a, this.field_147384_b));
/*    */   }
/*    */   
/*    */   public void onDisconnect(IChatComponent reason) {}
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\network\NetHandlerHandshakeMemory.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */