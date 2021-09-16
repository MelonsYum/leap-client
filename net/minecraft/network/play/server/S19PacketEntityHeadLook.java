/*    */ package net.minecraft.network.play.server;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.network.INetHandler;
/*    */ import net.minecraft.network.Packet;
/*    */ import net.minecraft.network.PacketBuffer;
/*    */ import net.minecraft.network.play.INetHandlerPlayClient;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ public class S19PacketEntityHeadLook
/*    */   implements Packet
/*    */ {
/*    */   private int field_149384_a;
/*    */   private byte field_149383_b;
/*    */   private static final String __OBFID = "CL_00001323";
/*    */   
/*    */   public S19PacketEntityHeadLook() {}
/*    */   
/*    */   public S19PacketEntityHeadLook(Entity p_i45214_1_, byte p_i45214_2_) {
/* 21 */     this.field_149384_a = p_i45214_1_.getEntityId();
/* 22 */     this.field_149383_b = p_i45214_2_;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void readPacketData(PacketBuffer data) throws IOException {
/* 30 */     this.field_149384_a = data.readVarIntFromBuffer();
/* 31 */     this.field_149383_b = data.readByte();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void writePacketData(PacketBuffer data) throws IOException {
/* 39 */     data.writeVarIntToBuffer(this.field_149384_a);
/* 40 */     data.writeByte(this.field_149383_b);
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_180745_a(INetHandlerPlayClient p_180745_1_) {
/* 45 */     p_180745_1_.handleEntityHeadLook(this);
/*    */   }
/*    */ 
/*    */   
/*    */   public Entity func_149381_a(World worldIn) {
/* 50 */     return worldIn.getEntityByID(this.field_149384_a);
/*    */   }
/*    */ 
/*    */   
/*    */   public byte func_149380_c() {
/* 55 */     return this.field_149383_b;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void processPacket(INetHandler handler) {
/* 63 */     func_180745_a((INetHandlerPlayClient)handler);
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\network\play\server\S19PacketEntityHeadLook.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */