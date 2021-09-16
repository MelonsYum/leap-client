/*     */ package net.minecraft.network.play.server;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.network.INetHandler;
/*     */ import net.minecraft.network.Packet;
/*     */ import net.minecraft.network.PacketBuffer;
/*     */ import net.minecraft.network.play.INetHandlerPlayClient;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class S14PacketEntity
/*     */   implements Packet
/*     */ {
/*     */   protected int field_149074_a;
/*     */   protected byte field_149072_b;
/*     */   protected byte field_149073_c;
/*     */   protected byte field_149070_d;
/*     */   protected byte field_149071_e;
/*     */   protected byte field_149068_f;
/*     */   protected boolean field_179743_g;
/*     */   protected boolean field_149069_g;
/*     */   private static final String __OBFID = "CL_00001312";
/*     */   
/*     */   public S14PacketEntity() {}
/*     */   
/*     */   public S14PacketEntity(int p_i45206_1_) {
/*  27 */     this.field_149074_a = p_i45206_1_;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void readPacketData(PacketBuffer data) throws IOException {
/*  35 */     this.field_149074_a = data.readVarIntFromBuffer();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void writePacketData(PacketBuffer data) throws IOException {
/*  43 */     data.writeVarIntToBuffer(this.field_149074_a);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void processPacket(INetHandlerPlayClient handler) {
/*  51 */     handler.handleEntityMovement(this);
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/*  56 */     return "Entity_" + super.toString();
/*     */   }
/*     */ 
/*     */   
/*     */   public Entity func_149065_a(World worldIn) {
/*  61 */     return worldIn.getEntityByID(this.field_149074_a);
/*     */   }
/*     */ 
/*     */   
/*     */   public byte func_149062_c() {
/*  66 */     return this.field_149072_b;
/*     */   }
/*     */ 
/*     */   
/*     */   public byte func_149061_d() {
/*  71 */     return this.field_149073_c;
/*     */   }
/*     */ 
/*     */   
/*     */   public byte func_149064_e() {
/*  76 */     return this.field_149070_d;
/*     */   }
/*     */ 
/*     */   
/*     */   public byte func_149066_f() {
/*  81 */     return this.field_149071_e;
/*     */   }
/*     */ 
/*     */   
/*     */   public byte func_149063_g() {
/*  86 */     return this.field_149068_f;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_149060_h() {
/*  91 */     return this.field_149069_g;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_179742_g() {
/*  96 */     return this.field_179743_g;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void processPacket(INetHandler handler) {
/* 104 */     processPacket((INetHandlerPlayClient)handler);
/*     */   }
/*     */   
/*     */   public static class S15PacketEntityRelMove
/*     */     extends S14PacketEntity
/*     */   {
/*     */     private static final String __OBFID = "CL_00001313";
/*     */     
/*     */     public S15PacketEntityRelMove() {}
/*     */     
/*     */     public S15PacketEntityRelMove(int p_i45974_1_, byte p_i45974_2_, byte p_i45974_3_, byte p_i45974_4_, boolean p_i45974_5_) {
/* 115 */       super(p_i45974_1_);
/* 116 */       this.field_149072_b = p_i45974_2_;
/* 117 */       this.field_149073_c = p_i45974_3_;
/* 118 */       this.field_149070_d = p_i45974_4_;
/* 119 */       this.field_179743_g = p_i45974_5_;
/*     */     }
/*     */ 
/*     */     
/*     */     public void readPacketData(PacketBuffer data) throws IOException {
/* 124 */       super.readPacketData(data);
/* 125 */       this.field_149072_b = data.readByte();
/* 126 */       this.field_149073_c = data.readByte();
/* 127 */       this.field_149070_d = data.readByte();
/* 128 */       this.field_179743_g = data.readBoolean();
/*     */     }
/*     */ 
/*     */     
/*     */     public void writePacketData(PacketBuffer data) throws IOException {
/* 133 */       super.writePacketData(data);
/* 134 */       data.writeByte(this.field_149072_b);
/* 135 */       data.writeByte(this.field_149073_c);
/* 136 */       data.writeByte(this.field_149070_d);
/* 137 */       data.writeBoolean(this.field_179743_g);
/*     */     }
/*     */ 
/*     */     
/*     */     public void processPacket(INetHandler handler) {
/* 142 */       processPacket((INetHandlerPlayClient)handler);
/*     */     }
/*     */   }
/*     */   
/*     */   public static class S16PacketEntityLook
/*     */     extends S14PacketEntity
/*     */   {
/*     */     private static final String __OBFID = "CL_00001315";
/*     */     
/*     */     public S16PacketEntityLook() {
/* 152 */       this.field_149069_g = true;
/*     */     }
/*     */ 
/*     */     
/*     */     public S16PacketEntityLook(int p_i45972_1_, byte p_i45972_2_, byte p_i45972_3_, boolean p_i45972_4_) {
/* 157 */       super(p_i45972_1_);
/* 158 */       this.field_149071_e = p_i45972_2_;
/* 159 */       this.field_149068_f = p_i45972_3_;
/* 160 */       this.field_149069_g = true;
/* 161 */       this.field_179743_g = p_i45972_4_;
/*     */     }
/*     */ 
/*     */     
/*     */     public void readPacketData(PacketBuffer data) throws IOException {
/* 166 */       super.readPacketData(data);
/* 167 */       this.field_149071_e = data.readByte();
/* 168 */       this.field_149068_f = data.readByte();
/* 169 */       this.field_179743_g = data.readBoolean();
/*     */     }
/*     */ 
/*     */     
/*     */     public void writePacketData(PacketBuffer data) throws IOException {
/* 174 */       super.writePacketData(data);
/* 175 */       data.writeByte(this.field_149071_e);
/* 176 */       data.writeByte(this.field_149068_f);
/* 177 */       data.writeBoolean(this.field_179743_g);
/*     */     }
/*     */ 
/*     */     
/*     */     public void processPacket(INetHandler handler) {
/* 182 */       processPacket((INetHandlerPlayClient)handler);
/*     */     }
/*     */   }
/*     */   
/*     */   public static class S17PacketEntityLookMove
/*     */     extends S14PacketEntity
/*     */   {
/*     */     private static final String __OBFID = "CL_00001314";
/*     */     
/*     */     public S17PacketEntityLookMove() {
/* 192 */       this.field_149069_g = true;
/*     */     }
/*     */ 
/*     */     
/*     */     public S17PacketEntityLookMove(int p_i45973_1_, byte p_i45973_2_, byte p_i45973_3_, byte p_i45973_4_, byte p_i45973_5_, byte p_i45973_6_, boolean p_i45973_7_) {
/* 197 */       super(p_i45973_1_);
/* 198 */       this.field_149072_b = p_i45973_2_;
/* 199 */       this.field_149073_c = p_i45973_3_;
/* 200 */       this.field_149070_d = p_i45973_4_;
/* 201 */       this.field_149071_e = p_i45973_5_;
/* 202 */       this.field_149068_f = p_i45973_6_;
/* 203 */       this.field_179743_g = p_i45973_7_;
/* 204 */       this.field_149069_g = true;
/*     */     }
/*     */ 
/*     */     
/*     */     public void readPacketData(PacketBuffer data) throws IOException {
/* 209 */       super.readPacketData(data);
/* 210 */       this.field_149072_b = data.readByte();
/* 211 */       this.field_149073_c = data.readByte();
/* 212 */       this.field_149070_d = data.readByte();
/* 213 */       this.field_149071_e = data.readByte();
/* 214 */       this.field_149068_f = data.readByte();
/* 215 */       this.field_179743_g = data.readBoolean();
/*     */     }
/*     */ 
/*     */     
/*     */     public void writePacketData(PacketBuffer data) throws IOException {
/* 220 */       super.writePacketData(data);
/* 221 */       data.writeByte(this.field_149072_b);
/* 222 */       data.writeByte(this.field_149073_c);
/* 223 */       data.writeByte(this.field_149070_d);
/* 224 */       data.writeByte(this.field_149071_e);
/* 225 */       data.writeByte(this.field_149068_f);
/* 226 */       data.writeBoolean(this.field_179743_g);
/*     */     }
/*     */ 
/*     */     
/*     */     public void processPacket(INetHandler handler) {
/* 231 */       processPacket((INetHandlerPlayClient)handler);
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\network\play\server\S14PacketEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */