/*    */ package net.minecraft.network.play.server;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.network.INetHandler;
/*    */ import net.minecraft.network.Packet;
/*    */ import net.minecraft.network.PacketBuffer;
/*    */ import net.minecraft.network.play.INetHandlerPlayClient;
/*    */ import net.minecraft.util.BlockPos;
/*    */ 
/*    */ public class S24PacketBlockAction
/*    */   implements Packet
/*    */ {
/*    */   private BlockPos field_179826_a;
/*    */   private int field_148872_d;
/*    */   private int field_148873_e;
/*    */   private Block field_148871_f;
/*    */   private static final String __OBFID = "CL_00001286";
/*    */   
/*    */   public S24PacketBlockAction() {}
/*    */   
/*    */   public S24PacketBlockAction(BlockPos p_i45989_1_, Block p_i45989_2_, int p_i45989_3_, int p_i45989_4_) {
/* 23 */     this.field_179826_a = p_i45989_1_;
/* 24 */     this.field_148872_d = p_i45989_3_;
/* 25 */     this.field_148873_e = p_i45989_4_;
/* 26 */     this.field_148871_f = p_i45989_2_;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void readPacketData(PacketBuffer data) throws IOException {
/* 34 */     this.field_179826_a = data.readBlockPos();
/* 35 */     this.field_148872_d = data.readUnsignedByte();
/* 36 */     this.field_148873_e = data.readUnsignedByte();
/* 37 */     this.field_148871_f = Block.getBlockById(data.readVarIntFromBuffer() & 0xFFF);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void writePacketData(PacketBuffer data) throws IOException {
/* 45 */     data.writeBlockPos(this.field_179826_a);
/* 46 */     data.writeByte(this.field_148872_d);
/* 47 */     data.writeByte(this.field_148873_e);
/* 48 */     data.writeVarIntToBuffer(Block.getIdFromBlock(this.field_148871_f) & 0xFFF);
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_180726_a(INetHandlerPlayClient p_180726_1_) {
/* 53 */     p_180726_1_.handleBlockAction(this);
/*    */   }
/*    */ 
/*    */   
/*    */   public BlockPos func_179825_a() {
/* 58 */     return this.field_179826_a;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int getData1() {
/* 66 */     return this.field_148872_d;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int getData2() {
/* 74 */     return this.field_148873_e;
/*    */   }
/*    */ 
/*    */   
/*    */   public Block getBlockType() {
/* 79 */     return this.field_148871_f;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void processPacket(INetHandler handler) {
/* 87 */     func_180726_a((INetHandlerPlayClient)handler);
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\network\play\server\S24PacketBlockAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */