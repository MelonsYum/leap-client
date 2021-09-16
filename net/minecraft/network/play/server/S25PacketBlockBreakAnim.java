/*    */ package net.minecraft.network.play.server;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import net.minecraft.network.INetHandler;
/*    */ import net.minecraft.network.Packet;
/*    */ import net.minecraft.network.PacketBuffer;
/*    */ import net.minecraft.network.play.INetHandlerPlayClient;
/*    */ import net.minecraft.util.BlockPos;
/*    */ 
/*    */ public class S25PacketBlockBreakAnim
/*    */   implements Packet
/*    */ {
/*    */   private int breakerId;
/*    */   private BlockPos position;
/*    */   private int progress;
/*    */   private static final String __OBFID = "CL_00001284";
/*    */   
/*    */   public S25PacketBlockBreakAnim() {}
/*    */   
/*    */   public S25PacketBlockBreakAnim(int breakerId, BlockPos pos, int progress) {
/* 21 */     this.breakerId = breakerId;
/* 22 */     this.position = pos;
/* 23 */     this.progress = progress;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void readPacketData(PacketBuffer data) throws IOException {
/* 31 */     this.breakerId = data.readVarIntFromBuffer();
/* 32 */     this.position = data.readBlockPos();
/* 33 */     this.progress = data.readUnsignedByte();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void writePacketData(PacketBuffer data) throws IOException {
/* 41 */     data.writeVarIntToBuffer(this.breakerId);
/* 42 */     data.writeBlockPos(this.position);
/* 43 */     data.writeByte(this.progress);
/*    */   }
/*    */ 
/*    */   
/*    */   public void handle(INetHandlerPlayClient handler) {
/* 48 */     handler.handleBlockBreakAnim(this);
/*    */   }
/*    */ 
/*    */   
/*    */   public int func_148845_c() {
/* 53 */     return this.breakerId;
/*    */   }
/*    */ 
/*    */   
/*    */   public BlockPos func_179821_b() {
/* 58 */     return this.position;
/*    */   }
/*    */ 
/*    */   
/*    */   public int func_148846_g() {
/* 63 */     return this.progress;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void processPacket(INetHandler handler) {
/* 71 */     handle((INetHandlerPlayClient)handler);
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\network\play\server\S25PacketBlockBreakAnim.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */