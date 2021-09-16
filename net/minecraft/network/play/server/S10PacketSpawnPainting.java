/*    */ package net.minecraft.network.play.server;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import net.minecraft.entity.item.EntityPainting;
/*    */ import net.minecraft.network.INetHandler;
/*    */ import net.minecraft.network.Packet;
/*    */ import net.minecraft.network.PacketBuffer;
/*    */ import net.minecraft.network.play.INetHandlerPlayClient;
/*    */ import net.minecraft.util.BlockPos;
/*    */ import net.minecraft.util.EnumFacing;
/*    */ 
/*    */ public class S10PacketSpawnPainting
/*    */   implements Packet
/*    */ {
/*    */   private int field_148973_a;
/*    */   private BlockPos field_179838_b;
/*    */   private EnumFacing field_179839_c;
/*    */   private String field_148968_f;
/*    */   private static final String __OBFID = "CL_00001280";
/*    */   
/*    */   public S10PacketSpawnPainting() {}
/*    */   
/*    */   public S10PacketSpawnPainting(EntityPainting p_i45170_1_) {
/* 24 */     this.field_148973_a = p_i45170_1_.getEntityId();
/* 25 */     this.field_179838_b = p_i45170_1_.func_174857_n();
/* 26 */     this.field_179839_c = p_i45170_1_.field_174860_b;
/* 27 */     this.field_148968_f = p_i45170_1_.art.title;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void readPacketData(PacketBuffer data) throws IOException {
/* 35 */     this.field_148973_a = data.readVarIntFromBuffer();
/* 36 */     this.field_148968_f = data.readStringFromBuffer(EntityPainting.EnumArt.field_180001_A);
/* 37 */     this.field_179838_b = data.readBlockPos();
/* 38 */     this.field_179839_c = EnumFacing.getHorizontal(data.readUnsignedByte());
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void writePacketData(PacketBuffer data) throws IOException {
/* 46 */     data.writeVarIntToBuffer(this.field_148973_a);
/* 47 */     data.writeString(this.field_148968_f);
/* 48 */     data.writeBlockPos(this.field_179838_b);
/* 49 */     data.writeByte(this.field_179839_c.getHorizontalIndex());
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_180722_a(INetHandlerPlayClient p_180722_1_) {
/* 54 */     p_180722_1_.handleSpawnPainting(this);
/*    */   }
/*    */ 
/*    */   
/*    */   public int func_148965_c() {
/* 59 */     return this.field_148973_a;
/*    */   }
/*    */ 
/*    */   
/*    */   public BlockPos func_179837_b() {
/* 64 */     return this.field_179838_b;
/*    */   }
/*    */ 
/*    */   
/*    */   public EnumFacing func_179836_c() {
/* 69 */     return this.field_179839_c;
/*    */   }
/*    */ 
/*    */   
/*    */   public String func_148961_h() {
/* 74 */     return this.field_148968_f;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void processPacket(INetHandler handler) {
/* 82 */     func_180722_a((INetHandlerPlayClient)handler);
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\network\play\server\S10PacketSpawnPainting.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */