/*    */ package net.minecraft.network.play.server;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import net.minecraft.network.INetHandler;
/*    */ import net.minecraft.network.Packet;
/*    */ import net.minecraft.network.PacketBuffer;
/*    */ import net.minecraft.network.play.INetHandlerPlayClient;
/*    */ import net.minecraft.util.BlockPos;
/*    */ 
/*    */ public class S05PacketSpawnPosition
/*    */   implements Packet
/*    */ {
/*    */   private BlockPos field_179801_a;
/*    */   private static final String __OBFID = "CL_00001336";
/*    */   
/*    */   public S05PacketSpawnPosition() {}
/*    */   
/*    */   public S05PacketSpawnPosition(BlockPos p_i45956_1_) {
/* 19 */     this.field_179801_a = p_i45956_1_;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void readPacketData(PacketBuffer data) throws IOException {
/* 27 */     this.field_179801_a = data.readBlockPos();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void writePacketData(PacketBuffer data) throws IOException {
/* 35 */     data.writeBlockPos(this.field_179801_a);
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_180752_a(INetHandlerPlayClient p_180752_1_) {
/* 40 */     p_180752_1_.handleSpawnPosition(this);
/*    */   }
/*    */ 
/*    */   
/*    */   public BlockPos func_179800_a() {
/* 45 */     return this.field_179801_a;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void processPacket(INetHandler handler) {
/* 53 */     func_180752_a((INetHandlerPlayClient)handler);
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\network\play\server\S05PacketSpawnPosition.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */