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
/*    */ public class S43PacketCamera
/*    */   implements Packet
/*    */ {
/*    */   public int field_179781_a;
/*    */   private static final String __OBFID = "CL_00002289";
/*    */   
/*    */   public S43PacketCamera() {}
/*    */   
/*    */   public S43PacketCamera(Entity p_i45960_1_) {
/* 20 */     this.field_179781_a = p_i45960_1_.getEntityId();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void readPacketData(PacketBuffer data) throws IOException {
/* 28 */     this.field_179781_a = data.readVarIntFromBuffer();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void writePacketData(PacketBuffer data) throws IOException {
/* 36 */     data.writeVarIntToBuffer(this.field_179781_a);
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_179779_a(INetHandlerPlayClient p_179779_1_) {
/* 41 */     p_179779_1_.func_175094_a(this);
/*    */   }
/*    */ 
/*    */   
/*    */   public Entity func_179780_a(World worldIn) {
/* 46 */     return worldIn.getEntityByID(this.field_179781_a);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void processPacket(INetHandler handler) {
/* 54 */     func_179779_a((INetHandlerPlayClient)handler);
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\network\play\server\S43PacketCamera.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */