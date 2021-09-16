/*     */ package net.minecraft.network.play.server;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import net.minecraft.network.INetHandler;
/*     */ import net.minecraft.network.Packet;
/*     */ import net.minecraft.network.PacketBuffer;
/*     */ import net.minecraft.network.play.INetHandlerPlayClient;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import org.apache.commons.lang3.Validate;
/*     */ 
/*     */ public class S29PacketSoundEffect
/*     */   implements Packet {
/*     */   private String field_149219_a;
/*     */   private int field_149217_b;
/*  15 */   private int field_149218_c = Integer.MAX_VALUE;
/*     */   
/*     */   private int field_149215_d;
/*     */   private float field_149216_e;
/*     */   private int field_149214_f;
/*     */   private static final String __OBFID = "CL_00001309";
/*     */   
/*     */   public S29PacketSoundEffect() {}
/*     */   
/*     */   public S29PacketSoundEffect(String p_i45200_1_, double p_i45200_2_, double p_i45200_4_, double p_i45200_6_, float p_i45200_8_, float p_i45200_9_) {
/*  25 */     Validate.notNull(p_i45200_1_, "name", new Object[0]);
/*  26 */     this.field_149219_a = p_i45200_1_;
/*  27 */     this.field_149217_b = (int)(p_i45200_2_ * 8.0D);
/*  28 */     this.field_149218_c = (int)(p_i45200_4_ * 8.0D);
/*  29 */     this.field_149215_d = (int)(p_i45200_6_ * 8.0D);
/*  30 */     this.field_149216_e = p_i45200_8_;
/*  31 */     this.field_149214_f = (int)(p_i45200_9_ * 63.0F);
/*  32 */     p_i45200_9_ = MathHelper.clamp_float(p_i45200_9_, 0.0F, 255.0F);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void readPacketData(PacketBuffer data) throws IOException {
/*  40 */     this.field_149219_a = data.readStringFromBuffer(256);
/*  41 */     this.field_149217_b = data.readInt();
/*  42 */     this.field_149218_c = data.readInt();
/*  43 */     this.field_149215_d = data.readInt();
/*  44 */     this.field_149216_e = data.readFloat();
/*  45 */     this.field_149214_f = data.readUnsignedByte();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void writePacketData(PacketBuffer data) throws IOException {
/*  53 */     data.writeString(this.field_149219_a);
/*  54 */     data.writeInt(this.field_149217_b);
/*  55 */     data.writeInt(this.field_149218_c);
/*  56 */     data.writeInt(this.field_149215_d);
/*  57 */     data.writeFloat(this.field_149216_e);
/*  58 */     data.writeByte(this.field_149214_f);
/*     */   }
/*     */ 
/*     */   
/*     */   public String func_149212_c() {
/*  63 */     return this.field_149219_a;
/*     */   }
/*     */ 
/*     */   
/*     */   public double func_149207_d() {
/*  68 */     return (this.field_149217_b / 8.0F);
/*     */   }
/*     */ 
/*     */   
/*     */   public double func_149211_e() {
/*  73 */     return (this.field_149218_c / 8.0F);
/*     */   }
/*     */ 
/*     */   
/*     */   public double func_149210_f() {
/*  78 */     return (this.field_149215_d / 8.0F);
/*     */   }
/*     */ 
/*     */   
/*     */   public float func_149208_g() {
/*  83 */     return this.field_149216_e;
/*     */   }
/*     */ 
/*     */   
/*     */   public float func_149209_h() {
/*  88 */     return this.field_149214_f / 63.0F;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void processPacket(INetHandlerPlayClient handler) {
/*  96 */     handler.handleSoundEffect(this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void processPacket(INetHandler handler) {
/* 104 */     processPacket((INetHandlerPlayClient)handler);
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\network\play\server\S29PacketSoundEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */