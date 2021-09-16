/*    */ package net.minecraft.network.play.server;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.network.INetHandler;
/*    */ import net.minecraft.network.Packet;
/*    */ import net.minecraft.network.PacketBuffer;
/*    */ import net.minecraft.network.play.INetHandlerPlayClient;
/*    */ 
/*    */ public class S0BPacketAnimation
/*    */   implements Packet
/*    */ {
/*    */   private int entityId;
/*    */   private int type;
/*    */   private static final String __OBFID = "CL_00001282";
/*    */   
/*    */   public S0BPacketAnimation() {}
/*    */   
/*    */   public S0BPacketAnimation(Entity ent, int animationType) {
/* 20 */     this.entityId = ent.getEntityId();
/* 21 */     this.type = animationType;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void readPacketData(PacketBuffer data) throws IOException {
/* 29 */     this.entityId = data.readVarIntFromBuffer();
/* 30 */     this.type = data.readUnsignedByte();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void writePacketData(PacketBuffer data) throws IOException {
/* 38 */     data.writeVarIntToBuffer(this.entityId);
/* 39 */     data.writeByte(this.type);
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_180723_a(INetHandlerPlayClient p_180723_1_) {
/* 44 */     p_180723_1_.handleAnimation(this);
/*    */   }
/*    */ 
/*    */   
/*    */   public int func_148978_c() {
/* 49 */     return this.entityId;
/*    */   }
/*    */ 
/*    */   
/*    */   public int func_148977_d() {
/* 54 */     return this.type;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void processPacket(INetHandler handler) {
/* 62 */     func_180723_a((INetHandlerPlayClient)handler);
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\network\play\server\S0BPacketAnimation.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */