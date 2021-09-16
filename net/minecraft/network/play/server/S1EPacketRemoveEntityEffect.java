/*    */ package net.minecraft.network.play.server;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import net.minecraft.network.INetHandler;
/*    */ import net.minecraft.network.Packet;
/*    */ import net.minecraft.network.PacketBuffer;
/*    */ import net.minecraft.network.play.INetHandlerPlayClient;
/*    */ import net.minecraft.potion.PotionEffect;
/*    */ 
/*    */ public class S1EPacketRemoveEntityEffect
/*    */   implements Packet
/*    */ {
/*    */   private int field_149079_a;
/*    */   private int field_149078_b;
/*    */   private static final String __OBFID = "CL_00001321";
/*    */   
/*    */   public S1EPacketRemoveEntityEffect() {}
/*    */   
/*    */   public S1EPacketRemoveEntityEffect(int p_i45212_1_, PotionEffect p_i45212_2_) {
/* 20 */     this.field_149079_a = p_i45212_1_;
/* 21 */     this.field_149078_b = p_i45212_2_.getPotionID();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void readPacketData(PacketBuffer data) throws IOException {
/* 29 */     this.field_149079_a = data.readVarIntFromBuffer();
/* 30 */     this.field_149078_b = data.readUnsignedByte();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void writePacketData(PacketBuffer data) throws IOException {
/* 38 */     data.writeVarIntToBuffer(this.field_149079_a);
/* 39 */     data.writeByte(this.field_149078_b);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void processPacket(INetHandlerPlayClient handler) {
/* 47 */     handler.handleRemoveEntityEffect(this);
/*    */   }
/*    */ 
/*    */   
/*    */   public int func_149076_c() {
/* 52 */     return this.field_149079_a;
/*    */   }
/*    */ 
/*    */   
/*    */   public int func_149075_d() {
/* 57 */     return this.field_149078_b;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void processPacket(INetHandler handler) {
/* 65 */     processPacket((INetHandlerPlayClient)handler);
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\network\play\server\S1EPacketRemoveEntityEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */