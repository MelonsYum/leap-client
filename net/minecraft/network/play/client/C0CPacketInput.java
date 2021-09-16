/*    */ package net.minecraft.network.play.client;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import net.minecraft.network.INetHandler;
/*    */ import net.minecraft.network.Packet;
/*    */ import net.minecraft.network.PacketBuffer;
/*    */ import net.minecraft.network.play.INetHandlerPlayServer;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class C0CPacketInput
/*    */   implements Packet
/*    */ {
/*    */   private float strafeSpeed;
/*    */   private float forwardSpeed;
/*    */   private boolean jumping;
/*    */   private boolean sneaking;
/*    */   private static final String __OBFID = "CL_00001367";
/*    */   
/*    */   public C0CPacketInput() {}
/*    */   
/*    */   public C0CPacketInput(float p_i45261_1_, float p_i45261_2_, boolean p_i45261_3_, boolean p_i45261_4_) {
/* 24 */     this.strafeSpeed = p_i45261_1_;
/* 25 */     this.forwardSpeed = p_i45261_2_;
/* 26 */     this.jumping = p_i45261_3_;
/* 27 */     this.sneaking = p_i45261_4_;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void readPacketData(PacketBuffer data) throws IOException {
/* 35 */     this.strafeSpeed = data.readFloat();
/* 36 */     this.forwardSpeed = data.readFloat();
/* 37 */     byte var2 = data.readByte();
/* 38 */     this.jumping = ((var2 & 0x1) > 0);
/* 39 */     this.sneaking = ((var2 & 0x2) > 0);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void writePacketData(PacketBuffer data) throws IOException {
/* 47 */     data.writeFloat(this.strafeSpeed);
/* 48 */     data.writeFloat(this.forwardSpeed);
/* 49 */     byte var2 = 0;
/*    */     
/* 51 */     if (this.jumping)
/*    */     {
/* 53 */       var2 = (byte)(var2 | 0x1);
/*    */     }
/*    */     
/* 56 */     if (this.sneaking)
/*    */     {
/* 58 */       var2 = (byte)(var2 | 0x2);
/*    */     }
/*    */     
/* 61 */     data.writeByte(var2);
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_180766_a(INetHandlerPlayServer p_180766_1_) {
/* 66 */     p_180766_1_.processInput(this);
/*    */   }
/*    */ 
/*    */   
/*    */   public float getStrafeSpeed() {
/* 71 */     return this.strafeSpeed;
/*    */   }
/*    */ 
/*    */   
/*    */   public float getForwardSpeed() {
/* 76 */     return this.forwardSpeed;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isJumping() {
/* 81 */     return this.jumping;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isSneaking() {
/* 86 */     return this.sneaking;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void processPacket(INetHandler handler) {
/* 94 */     func_180766_a((INetHandlerPlayServer)handler);
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\network\play\client\C0CPacketInput.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */