/*    */ package net.minecraft.network.play.server;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.network.INetHandler;
/*    */ import net.minecraft.network.Packet;
/*    */ import net.minecraft.network.PacketBuffer;
/*    */ import net.minecraft.network.play.INetHandlerPlayClient;
/*    */ import net.minecraft.util.MathHelper;
/*    */ 
/*    */ 
/*    */ public class S2CPacketSpawnGlobalEntity
/*    */   implements Packet
/*    */ {
/*    */   private int field_149059_a;
/*    */   private int field_149057_b;
/*    */   private int field_149058_c;
/*    */   private int field_149055_d;
/*    */   private int field_149056_e;
/*    */   private static final String __OBFID = "CL_00001278";
/*    */   
/*    */   public S2CPacketSpawnGlobalEntity() {}
/*    */   
/*    */   public S2CPacketSpawnGlobalEntity(Entity p_i45191_1_) {
/* 25 */     this.field_149059_a = p_i45191_1_.getEntityId();
/* 26 */     this.field_149057_b = MathHelper.floor_double(p_i45191_1_.posX * 32.0D);
/* 27 */     this.field_149058_c = MathHelper.floor_double(p_i45191_1_.posY * 32.0D);
/* 28 */     this.field_149055_d = MathHelper.floor_double(p_i45191_1_.posZ * 32.0D);
/*    */     
/* 30 */     if (p_i45191_1_ instanceof net.minecraft.entity.effect.EntityLightningBolt)
/*    */     {
/* 32 */       this.field_149056_e = 1;
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void readPacketData(PacketBuffer data) throws IOException {
/* 41 */     this.field_149059_a = data.readVarIntFromBuffer();
/* 42 */     this.field_149056_e = data.readByte();
/* 43 */     this.field_149057_b = data.readInt();
/* 44 */     this.field_149058_c = data.readInt();
/* 45 */     this.field_149055_d = data.readInt();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void writePacketData(PacketBuffer data) throws IOException {
/* 53 */     data.writeVarIntToBuffer(this.field_149059_a);
/* 54 */     data.writeByte(this.field_149056_e);
/* 55 */     data.writeInt(this.field_149057_b);
/* 56 */     data.writeInt(this.field_149058_c);
/* 57 */     data.writeInt(this.field_149055_d);
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_180720_a(INetHandlerPlayClient p_180720_1_) {
/* 62 */     p_180720_1_.handleSpawnGlobalEntity(this);
/*    */   }
/*    */ 
/*    */   
/*    */   public int func_149052_c() {
/* 67 */     return this.field_149059_a;
/*    */   }
/*    */ 
/*    */   
/*    */   public int func_149051_d() {
/* 72 */     return this.field_149057_b;
/*    */   }
/*    */ 
/*    */   
/*    */   public int func_149050_e() {
/* 77 */     return this.field_149058_c;
/*    */   }
/*    */ 
/*    */   
/*    */   public int func_149049_f() {
/* 82 */     return this.field_149055_d;
/*    */   }
/*    */ 
/*    */   
/*    */   public int func_149053_g() {
/* 87 */     return this.field_149056_e;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void processPacket(INetHandler handler) {
/* 95 */     func_180720_a((INetHandlerPlayClient)handler);
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\network\play\server\S2CPacketSpawnGlobalEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */