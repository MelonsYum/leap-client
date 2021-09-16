/*     */ package net.minecraft.network.play.server;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.network.INetHandler;
/*     */ import net.minecraft.network.Packet;
/*     */ import net.minecraft.network.PacketBuffer;
/*     */ import net.minecraft.network.play.INetHandlerPlayClient;
/*     */ import net.minecraft.util.MathHelper;
/*     */ 
/*     */ public class S18PacketEntityTeleport
/*     */   implements Packet
/*     */ {
/*     */   private int field_149458_a;
/*     */   private int field_149456_b;
/*     */   private int field_149457_c;
/*     */   private int field_149454_d;
/*     */   private byte field_149455_e;
/*     */   private byte field_149453_f;
/*     */   private boolean field_179698_g;
/*     */   private static final String __OBFID = "CL_00001340";
/*     */   
/*     */   public S18PacketEntityTeleport() {}
/*     */   
/*     */   public S18PacketEntityTeleport(Entity p_i45233_1_) {
/*  26 */     this.field_149458_a = p_i45233_1_.getEntityId();
/*  27 */     this.field_149456_b = MathHelper.floor_double(p_i45233_1_.posX * 32.0D);
/*  28 */     this.field_149457_c = MathHelper.floor_double(p_i45233_1_.posY * 32.0D);
/*  29 */     this.field_149454_d = MathHelper.floor_double(p_i45233_1_.posZ * 32.0D);
/*  30 */     this.field_149455_e = (byte)(int)(p_i45233_1_.rotationYaw * 256.0F / 360.0F);
/*  31 */     this.field_149453_f = (byte)(int)(p_i45233_1_.rotationPitch * 256.0F / 360.0F);
/*  32 */     this.field_179698_g = p_i45233_1_.onGround;
/*     */   }
/*     */ 
/*     */   
/*     */   public S18PacketEntityTeleport(int p_i45949_1_, int p_i45949_2_, int p_i45949_3_, int p_i45949_4_, byte p_i45949_5_, byte p_i45949_6_, boolean p_i45949_7_) {
/*  37 */     this.field_149458_a = p_i45949_1_;
/*  38 */     this.field_149456_b = p_i45949_2_;
/*  39 */     this.field_149457_c = p_i45949_3_;
/*  40 */     this.field_149454_d = p_i45949_4_;
/*  41 */     this.field_149455_e = p_i45949_5_;
/*  42 */     this.field_149453_f = p_i45949_6_;
/*  43 */     this.field_179698_g = p_i45949_7_;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void readPacketData(PacketBuffer data) throws IOException {
/*  51 */     this.field_149458_a = data.readVarIntFromBuffer();
/*  52 */     this.field_149456_b = data.readInt();
/*  53 */     this.field_149457_c = data.readInt();
/*  54 */     this.field_149454_d = data.readInt();
/*  55 */     this.field_149455_e = data.readByte();
/*  56 */     this.field_149453_f = data.readByte();
/*  57 */     this.field_179698_g = data.readBoolean();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void writePacketData(PacketBuffer data) throws IOException {
/*  65 */     data.writeVarIntToBuffer(this.field_149458_a);
/*  66 */     data.writeInt(this.field_149456_b);
/*  67 */     data.writeInt(this.field_149457_c);
/*  68 */     data.writeInt(this.field_149454_d);
/*  69 */     data.writeByte(this.field_149455_e);
/*  70 */     data.writeByte(this.field_149453_f);
/*  71 */     data.writeBoolean(this.field_179698_g);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void processPacket(INetHandlerPlayClient handler) {
/*  79 */     handler.handleEntityTeleport(this);
/*     */   }
/*     */ 
/*     */   
/*     */   public int func_149451_c() {
/*  84 */     return this.field_149458_a;
/*     */   }
/*     */ 
/*     */   
/*     */   public int func_149449_d() {
/*  89 */     return this.field_149456_b;
/*     */   }
/*     */ 
/*     */   
/*     */   public int func_149448_e() {
/*  94 */     return this.field_149457_c;
/*     */   }
/*     */ 
/*     */   
/*     */   public int func_149446_f() {
/*  99 */     return this.field_149454_d;
/*     */   }
/*     */ 
/*     */   
/*     */   public byte func_149450_g() {
/* 104 */     return this.field_149455_e;
/*     */   }
/*     */ 
/*     */   
/*     */   public byte func_149447_h() {
/* 109 */     return this.field_149453_f;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_179697_g() {
/* 114 */     return this.field_179698_g;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void processPacket(INetHandler handler) {
/* 122 */     processPacket((INetHandlerPlayClient)handler);
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\network\play\server\S18PacketEntityTeleport.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */