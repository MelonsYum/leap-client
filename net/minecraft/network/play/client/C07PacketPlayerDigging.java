/*    */ package net.minecraft.network.play.client;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import net.minecraft.network.INetHandler;
/*    */ import net.minecraft.network.Packet;
/*    */ import net.minecraft.network.PacketBuffer;
/*    */ import net.minecraft.network.play.INetHandlerPlayServer;
/*    */ import net.minecraft.util.BlockPos;
/*    */ import net.minecraft.util.EnumFacing;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class C07PacketPlayerDigging
/*    */   implements Packet
/*    */ {
/*    */   private BlockPos field_179717_a;
/*    */   private EnumFacing field_179716_b;
/*    */   private Action status;
/*    */   private static final String __OBFID = "CL_00001365";
/*    */   
/*    */   public C07PacketPlayerDigging() {}
/*    */   
/*    */   public C07PacketPlayerDigging(Action p_i45940_1_, BlockPos p_i45940_2_, EnumFacing p_i45940_3_) {
/* 24 */     this.status = p_i45940_1_;
/* 25 */     this.field_179717_a = p_i45940_2_;
/* 26 */     this.field_179716_b = p_i45940_3_;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void readPacketData(PacketBuffer data) throws IOException {
/* 34 */     this.status = (Action)data.readEnumValue(Action.class);
/* 35 */     this.field_179717_a = data.readBlockPos();
/* 36 */     this.field_179716_b = EnumFacing.getFront(data.readUnsignedByte());
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void writePacketData(PacketBuffer data) throws IOException {
/* 44 */     data.writeEnumValue(this.status);
/* 45 */     data.writeBlockPos(this.field_179717_a);
/* 46 */     data.writeByte(this.field_179716_b.getIndex());
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_180763_a(INetHandlerPlayServer p_180763_1_) {
/* 51 */     p_180763_1_.processPlayerDigging(this);
/*    */   }
/*    */ 
/*    */   
/*    */   public BlockPos func_179715_a() {
/* 56 */     return this.field_179717_a;
/*    */   }
/*    */ 
/*    */   
/*    */   public EnumFacing func_179714_b() {
/* 61 */     return this.field_179716_b;
/*    */   }
/*    */ 
/*    */   
/*    */   public Action func_180762_c() {
/* 66 */     return this.status;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void processPacket(INetHandler handler) {
/* 74 */     func_180763_a((INetHandlerPlayServer)handler);
/*    */   }
/*    */   
/*    */   public enum Action
/*    */   {
/* 79 */     START_DESTROY_BLOCK("START_DESTROY_BLOCK", 0),
/* 80 */     ABORT_DESTROY_BLOCK("ABORT_DESTROY_BLOCK", 1),
/* 81 */     STOP_DESTROY_BLOCK("STOP_DESTROY_BLOCK", 2),
/* 82 */     DROP_ALL_ITEMS("DROP_ALL_ITEMS", 3),
/* 83 */     DROP_ITEM("DROP_ITEM", 4),
/* 84 */     RELEASE_USE_ITEM("RELEASE_USE_ITEM", 5);
/*    */     
/* 86 */     private static final Action[] $VALUES = new Action[] { START_DESTROY_BLOCK, ABORT_DESTROY_BLOCK, STOP_DESTROY_BLOCK, DROP_ALL_ITEMS, DROP_ITEM, RELEASE_USE_ITEM };
/*    */     private static final String __OBFID = "CL_00002284";
/*    */     
/*    */     static {
/*    */     
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\network\play\client\C07PacketPlayerDigging.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */