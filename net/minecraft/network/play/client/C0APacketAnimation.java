/*    */ package net.minecraft.network.play.client;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import net.minecraft.network.INetHandler;
/*    */ import net.minecraft.network.Packet;
/*    */ import net.minecraft.network.PacketBuffer;
/*    */ import net.minecraft.network.play.INetHandlerPlayServer;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class C0APacketAnimation
/*    */   implements Packet
/*    */ {
/*    */   private static final String __OBFID = "CL_00001345";
/*    */   
/*    */   public void readPacketData(PacketBuffer data) throws IOException {}
/*    */   
/*    */   public void writePacketData(PacketBuffer data) throws IOException {}
/*    */   
/*    */   public void func_179721_a(INetHandlerPlayServer p_179721_1_) {
/* 25 */     p_179721_1_.func_175087_a(this);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void processPacket(INetHandler handler) {
/* 33 */     func_179721_a((INetHandlerPlayServer)handler);
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\network\play\client\C0APacketAnimation.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */