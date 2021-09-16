/*    */ package net.minecraft.network.play.server;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.network.INetHandler;
/*    */ import net.minecraft.network.Packet;
/*    */ import net.minecraft.network.PacketBuffer;
/*    */ import net.minecraft.network.play.INetHandlerPlayClient;
/*    */ 
/*    */ public class S2FPacketSetSlot
/*    */   implements Packet
/*    */ {
/*    */   private int field_149179_a;
/*    */   private int field_149177_b;
/*    */   private ItemStack field_149178_c;
/*    */   private static final String __OBFID = "CL_00001296";
/*    */   
/*    */   public S2FPacketSetSlot() {}
/*    */   
/*    */   public S2FPacketSetSlot(int p_i45188_1_, int p_i45188_2_, ItemStack p_i45188_3_) {
/* 21 */     this.field_149179_a = p_i45188_1_;
/* 22 */     this.field_149177_b = p_i45188_2_;
/* 23 */     this.field_149178_c = (p_i45188_3_ == null) ? null : p_i45188_3_.copy();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void processPacket(INetHandlerPlayClient handler) {
/* 31 */     handler.handleSetSlot(this);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void readPacketData(PacketBuffer data) throws IOException {
/* 39 */     this.field_149179_a = data.readByte();
/* 40 */     this.field_149177_b = data.readShort();
/* 41 */     this.field_149178_c = data.readItemStackFromBuffer();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void writePacketData(PacketBuffer data) throws IOException {
/* 49 */     data.writeByte(this.field_149179_a);
/* 50 */     data.writeShort(this.field_149177_b);
/* 51 */     data.writeItemStackToBuffer(this.field_149178_c);
/*    */   }
/*    */ 
/*    */   
/*    */   public int func_149175_c() {
/* 56 */     return this.field_149179_a;
/*    */   }
/*    */ 
/*    */   
/*    */   public int func_149173_d() {
/* 61 */     return this.field_149177_b;
/*    */   }
/*    */ 
/*    */   
/*    */   public ItemStack func_149174_e() {
/* 66 */     return this.field_149178_c;
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


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\network\play\server\S2FPacketSetSlot.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */