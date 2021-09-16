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
/*    */ public class S19PacketEntityStatus
/*    */   implements Packet
/*    */ {
/*    */   private int field_149164_a;
/*    */   private byte field_149163_b;
/*    */   private static final String __OBFID = "CL_00001299";
/*    */   
/*    */   public S19PacketEntityStatus() {}
/*    */   
/*    */   public S19PacketEntityStatus(Entity p_i46335_1_, byte p_i46335_2_) {
/* 21 */     this.field_149164_a = p_i46335_1_.getEntityId();
/* 22 */     this.field_149163_b = p_i46335_2_;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void readPacketData(PacketBuffer data) throws IOException {
/* 30 */     this.field_149164_a = data.readInt();
/* 31 */     this.field_149163_b = data.readByte();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void writePacketData(PacketBuffer data) throws IOException {
/* 39 */     data.writeInt(this.field_149164_a);
/* 40 */     data.writeByte(this.field_149163_b);
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_180736_a(INetHandlerPlayClient p_180736_1_) {
/* 45 */     p_180736_1_.handleEntityStatus(this);
/*    */   }
/*    */ 
/*    */   
/*    */   public Entity func_149161_a(World worldIn) {
/* 50 */     return worldIn.getEntityByID(this.field_149164_a);
/*    */   }
/*    */ 
/*    */   
/*    */   public byte func_149160_c() {
/* 55 */     return this.field_149163_b;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void processPacket(INetHandler handler) {
/* 63 */     func_180736_a((INetHandlerPlayClient)handler);
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\network\play\server\S19PacketEntityStatus.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */