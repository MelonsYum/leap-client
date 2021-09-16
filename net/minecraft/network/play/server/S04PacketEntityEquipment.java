/*    */ package net.minecraft.network.play.server;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.network.INetHandler;
/*    */ import net.minecraft.network.Packet;
/*    */ import net.minecraft.network.PacketBuffer;
/*    */ import net.minecraft.network.play.INetHandlerPlayClient;
/*    */ 
/*    */ public class S04PacketEntityEquipment
/*    */   implements Packet
/*    */ {
/*    */   private int field_149394_a;
/*    */   private int field_149392_b;
/*    */   private ItemStack field_149393_c;
/*    */   private static final String __OBFID = "CL_00001330";
/*    */   
/*    */   public S04PacketEntityEquipment() {}
/*    */   
/*    */   public S04PacketEntityEquipment(int p_i45221_1_, int p_i45221_2_, ItemStack p_i45221_3_) {
/* 21 */     this.field_149394_a = p_i45221_1_;
/* 22 */     this.field_149392_b = p_i45221_2_;
/* 23 */     this.field_149393_c = (p_i45221_3_ == null) ? null : p_i45221_3_.copy();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void readPacketData(PacketBuffer data) throws IOException {
/* 31 */     this.field_149394_a = data.readVarIntFromBuffer();
/* 32 */     this.field_149392_b = data.readShort();
/* 33 */     this.field_149393_c = data.readItemStackFromBuffer();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void writePacketData(PacketBuffer data) throws IOException {
/* 41 */     data.writeVarIntToBuffer(this.field_149394_a);
/* 42 */     data.writeShort(this.field_149392_b);
/* 43 */     data.writeItemStackToBuffer(this.field_149393_c);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void processPacket(INetHandlerPlayClient handler) {
/* 51 */     handler.handleEntityEquipment(this);
/*    */   }
/*    */ 
/*    */   
/*    */   public ItemStack func_149390_c() {
/* 56 */     return this.field_149393_c;
/*    */   }
/*    */ 
/*    */   
/*    */   public int func_149389_d() {
/* 61 */     return this.field_149394_a;
/*    */   }
/*    */ 
/*    */   
/*    */   public int func_149388_e() {
/* 66 */     return this.field_149392_b;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void processPacket(INetHandler handler) {
/* 74 */     processPacket((INetHandlerPlayClient)handler);
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\network\play\server\S04PacketEntityEquipment.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */