/*     */ package net.minecraft.network.play.client;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import net.minecraft.network.INetHandler;
/*     */ import net.minecraft.network.Packet;
/*     */ import net.minecraft.network.PacketBuffer;
/*     */ import net.minecraft.network.play.INetHandlerPlayServer;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class C03PacketPlayer
/*     */   implements Packet
/*     */ {
/*     */   public double x;
/*     */   public double y;
/*     */   public double z;
/*     */   public float yaw;
/*     */   public float pitch;
/*     */   public boolean field_149474_g;
/*     */   protected boolean field_149480_h;
/*     */   protected boolean rotating;
/*     */   private static final String __OBFID = "CL_00001360";
/*     */   
/*     */   public C03PacketPlayer() {}
/*     */   
/*     */   public C03PacketPlayer(boolean p_i45256_1_) {
/*  27 */     this.field_149474_g = p_i45256_1_;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void processPacket(INetHandlerPlayServer handler) {
/*  35 */     handler.processPlayer(this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void readPacketData(PacketBuffer data) throws IOException {
/*  43 */     this.field_149474_g = (data.readUnsignedByte() != 0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void writePacketData(PacketBuffer data) throws IOException {
/*  51 */     data.writeByte(this.field_149474_g ? 1 : 0);
/*     */   }
/*     */ 
/*     */   
/*     */   public double getPositionX() {
/*  56 */     return this.x;
/*     */   }
/*     */ 
/*     */   
/*     */   public double getPositionY() {
/*  61 */     return this.y;
/*     */   }
/*     */ 
/*     */   
/*     */   public double getPositionZ() {
/*  66 */     return this.z;
/*     */   }
/*     */ 
/*     */   
/*     */   public float getYaw() {
/*  71 */     return this.yaw;
/*     */   }
/*     */ 
/*     */   
/*     */   public float getPitch() {
/*  76 */     return this.pitch;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_149465_i() {
/*  82 */     return this.field_149474_g;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_149466_j() {
/*  87 */     return this.field_149480_h;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean getRotating() {
/*  92 */     return this.rotating;
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_149469_a(boolean p_149469_1_) {
/*  97 */     this.field_149480_h = p_149469_1_;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void processPacket(INetHandler handler) {
/* 105 */     processPacket((INetHandlerPlayServer)handler);
/*     */   }
/*     */ 
/*     */   
/*     */   public static class C04PacketPlayerPosition
/*     */     extends C03PacketPlayer
/*     */   {
/*     */     private static final String __OBFID = "CL_00001361";
/*     */     
/*     */     public C04PacketPlayerPosition() {
/* 115 */       this.field_149480_h = true;
/*     */     }
/*     */ 
/*     */     
/*     */     public C04PacketPlayerPosition(double p_i45942_1_, double p_i45942_3_, double p_i45942_5_, boolean p_i45942_7_) {
/* 120 */       this.x = p_i45942_1_;
/* 121 */       this.y = p_i45942_3_;
/* 122 */       this.z = p_i45942_5_;
/* 123 */       this.field_149474_g = p_i45942_7_;
/* 124 */       this.field_149480_h = true;
/*     */     }
/*     */ 
/*     */     
/*     */     public void readPacketData(PacketBuffer data) throws IOException {
/* 129 */       this.x = data.readDouble();
/* 130 */       this.y = data.readDouble();
/* 131 */       this.z = data.readDouble();
/* 132 */       super.readPacketData(data);
/*     */     }
/*     */ 
/*     */     
/*     */     public void writePacketData(PacketBuffer data) throws IOException {
/* 137 */       data.writeDouble(this.x);
/* 138 */       data.writeDouble(this.y);
/* 139 */       data.writeDouble(this.z);
/* 140 */       super.writePacketData(data);
/*     */     }
/*     */ 
/*     */     
/*     */     public void processPacket(INetHandler handler) {
/* 145 */       processPacket((INetHandlerPlayServer)handler);
/*     */     }
/*     */   }
/*     */   
/*     */   public static class C05PacketPlayerLook
/*     */     extends C03PacketPlayer
/*     */   {
/*     */     private static final String __OBFID = "CL_00001363";
/*     */     
/*     */     public C05PacketPlayerLook() {
/* 155 */       this.rotating = true;
/*     */     }
/*     */ 
/*     */     
/*     */     public C05PacketPlayerLook(float p_i45255_1_, float p_i45255_2_, boolean p_i45255_3_) {
/* 160 */       this.yaw = p_i45255_1_;
/* 161 */       this.pitch = p_i45255_2_;
/* 162 */       this.field_149474_g = p_i45255_3_;
/* 163 */       this.rotating = true;
/*     */     }
/*     */ 
/*     */     
/*     */     public void readPacketData(PacketBuffer data) throws IOException {
/* 168 */       this.yaw = data.readFloat();
/* 169 */       this.pitch = data.readFloat();
/* 170 */       super.readPacketData(data);
/*     */     }
/*     */ 
/*     */     
/*     */     public void writePacketData(PacketBuffer data) throws IOException {
/* 175 */       data.writeFloat(this.yaw);
/* 176 */       data.writeFloat(this.pitch);
/* 177 */       super.writePacketData(data);
/*     */     }
/*     */ 
/*     */     
/*     */     public void processPacket(INetHandler handler) {
/* 182 */       processPacket((INetHandlerPlayServer)handler);
/*     */     }
/*     */   }
/*     */   
/*     */   public static class C06PacketPlayerPosLook
/*     */     extends C03PacketPlayer
/*     */   {
/*     */     private static final String __OBFID = "CL_00001362";
/*     */     
/*     */     public C06PacketPlayerPosLook() {
/* 192 */       this.field_149480_h = true;
/* 193 */       this.rotating = true;
/*     */     }
/*     */ 
/*     */     
/*     */     public C06PacketPlayerPosLook(double p_i45941_1_, double p_i45941_3_, double p_i45941_5_, float p_i45941_7_, float p_i45941_8_, boolean p_i45941_9_) {
/* 198 */       this.x = p_i45941_1_;
/* 199 */       this.y = p_i45941_3_;
/* 200 */       this.z = p_i45941_5_;
/* 201 */       this.yaw = p_i45941_7_;
/* 202 */       this.pitch = p_i45941_8_;
/* 203 */       this.field_149474_g = p_i45941_9_;
/* 204 */       this.rotating = true;
/* 205 */       this.field_149480_h = true;
/*     */     }
/*     */ 
/*     */     
/*     */     public void readPacketData(PacketBuffer data) throws IOException {
/* 210 */       this.x = data.readDouble();
/* 211 */       this.y = data.readDouble();
/* 212 */       this.z = data.readDouble();
/* 213 */       this.yaw = data.readFloat();
/* 214 */       this.pitch = data.readFloat();
/* 215 */       super.readPacketData(data);
/*     */     }
/*     */ 
/*     */     
/*     */     public void writePacketData(PacketBuffer data) throws IOException {
/* 220 */       data.writeDouble(this.x);
/* 221 */       data.writeDouble(this.y);
/* 222 */       data.writeDouble(this.z);
/* 223 */       data.writeFloat(this.yaw);
/* 224 */       data.writeFloat(this.pitch);
/* 225 */       super.writePacketData(data);
/*     */     }
/*     */ 
/*     */     
/*     */     public void processPacket(INetHandler handler) {
/* 230 */       processPacket((INetHandlerPlayServer)handler);
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\network\play\client\C03PacketPlayer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */