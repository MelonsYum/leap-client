/*    */ package net.minecraft.network.play.client;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import net.minecraft.network.INetHandler;
/*    */ import net.minecraft.network.Packet;
/*    */ import net.minecraft.network.PacketBuffer;
/*    */ import net.minecraft.network.play.INetHandlerPlayServer;
/*    */ 
/*    */ public class C16PacketClientStatus
/*    */   implements Packet
/*    */ {
/*    */   private EnumState status;
/*    */   private static final String __OBFID = "CL_00001348";
/*    */   
/*    */   public C16PacketClientStatus() {}
/*    */   
/*    */   public C16PacketClientStatus(EnumState statusIn) {
/* 18 */     this.status = statusIn;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void readPacketData(PacketBuffer data) throws IOException {
/* 26 */     this.status = (EnumState)data.readEnumValue(EnumState.class);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void writePacketData(PacketBuffer data) throws IOException {
/* 34 */     data.writeEnumValue(this.status);
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_180758_a(INetHandlerPlayServer p_180758_1_) {
/* 39 */     p_180758_1_.processClientStatus(this);
/*    */   }
/*    */ 
/*    */   
/*    */   public EnumState getStatus() {
/* 44 */     return this.status;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void processPacket(INetHandler handler) {
/* 52 */     func_180758_a((INetHandlerPlayServer)handler);
/*    */   }
/*    */   
/*    */   public enum EnumState
/*    */   {
/* 57 */     PERFORM_RESPAWN("PERFORM_RESPAWN", 0),
/* 58 */     REQUEST_STATS("REQUEST_STATS", 1),
/* 59 */     OPEN_INVENTORY_ACHIEVEMENT("OPEN_INVENTORY_ACHIEVEMENT", 2);
/*    */     
/* 61 */     private static final EnumState[] $VALUES = new EnumState[] { PERFORM_RESPAWN, REQUEST_STATS, OPEN_INVENTORY_ACHIEVEMENT };
/*    */     private static final String __OBFID = "CL_00001349";
/*    */     
/*    */     static {
/*    */     
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\network\play\client\C16PacketClientStatus.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */