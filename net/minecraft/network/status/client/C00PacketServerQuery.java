/*    */ package net.minecraft.network.status.client;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import net.minecraft.network.INetHandler;
/*    */ import net.minecraft.network.Packet;
/*    */ import net.minecraft.network.PacketBuffer;
/*    */ import net.minecraft.network.status.INetHandlerStatusServer;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class C00PacketServerQuery
/*    */   implements Packet
/*    */ {
/*    */   private static final String __OBFID = "CL_00001393";
/*    */   
/*    */   public void readPacketData(PacketBuffer data) throws IOException {}
/*    */   
/*    */   public void writePacketData(PacketBuffer data) throws IOException {}
/*    */   
/*    */   public void func_180775_a(INetHandlerStatusServer p_180775_1_) {
/* 25 */     p_180775_1_.processServerQuery(this);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void processPacket(INetHandler handler) {
/* 33 */     func_180775_a((INetHandlerStatusServer)handler);
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\network\status\client\C00PacketServerQuery.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */