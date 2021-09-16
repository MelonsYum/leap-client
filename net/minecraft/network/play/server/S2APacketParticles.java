/*     */ package net.minecraft.network.play.server;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import net.minecraft.network.INetHandler;
/*     */ import net.minecraft.network.Packet;
/*     */ import net.minecraft.network.PacketBuffer;
/*     */ import net.minecraft.network.play.INetHandlerPlayClient;
/*     */ import net.minecraft.util.EnumParticleTypes;
/*     */ 
/*     */ public class S2APacketParticles
/*     */   implements Packet
/*     */ {
/*     */   private EnumParticleTypes field_179751_a;
/*     */   private float field_149234_b;
/*     */   private float field_149235_c;
/*     */   private float field_149232_d;
/*     */   private float field_149233_e;
/*     */   private float field_149230_f;
/*     */   private float field_149231_g;
/*     */   private float field_149237_h;
/*     */   private int field_149238_i;
/*     */   private boolean field_179752_j;
/*     */   private int[] field_179753_k;
/*     */   private static final String __OBFID = "CL_00001308";
/*     */   
/*     */   public S2APacketParticles() {}
/*     */   
/*     */   public S2APacketParticles(EnumParticleTypes p_i45977_1_, boolean p_i45977_2_, float p_i45977_3_, float p_i45977_4_, float p_i45977_5_, float p_i45977_6_, float p_i45977_7_, float p_i45977_8_, float p_i45977_9_, int p_i45977_10_, int... p_i45977_11_) {
/*  29 */     this.field_179751_a = p_i45977_1_;
/*  30 */     this.field_179752_j = p_i45977_2_;
/*  31 */     this.field_149234_b = p_i45977_3_;
/*  32 */     this.field_149235_c = p_i45977_4_;
/*  33 */     this.field_149232_d = p_i45977_5_;
/*  34 */     this.field_149233_e = p_i45977_6_;
/*  35 */     this.field_149230_f = p_i45977_7_;
/*  36 */     this.field_149231_g = p_i45977_8_;
/*  37 */     this.field_149237_h = p_i45977_9_;
/*  38 */     this.field_149238_i = p_i45977_10_;
/*  39 */     this.field_179753_k = p_i45977_11_;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void readPacketData(PacketBuffer data) throws IOException {
/*  47 */     this.field_179751_a = EnumParticleTypes.func_179342_a(data.readInt());
/*     */     
/*  49 */     if (this.field_179751_a == null)
/*     */     {
/*  51 */       this.field_179751_a = EnumParticleTypes.BARRIER;
/*     */     }
/*     */     
/*  54 */     this.field_179752_j = data.readBoolean();
/*  55 */     this.field_149234_b = data.readFloat();
/*  56 */     this.field_149235_c = data.readFloat();
/*  57 */     this.field_149232_d = data.readFloat();
/*  58 */     this.field_149233_e = data.readFloat();
/*  59 */     this.field_149230_f = data.readFloat();
/*  60 */     this.field_149231_g = data.readFloat();
/*  61 */     this.field_149237_h = data.readFloat();
/*  62 */     this.field_149238_i = data.readInt();
/*  63 */     int var2 = this.field_179751_a.func_179345_d();
/*  64 */     this.field_179753_k = new int[var2];
/*     */     
/*  66 */     for (int var3 = 0; var3 < var2; var3++)
/*     */     {
/*  68 */       this.field_179753_k[var3] = data.readVarIntFromBuffer();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void writePacketData(PacketBuffer data) throws IOException {
/*  77 */     data.writeInt(this.field_179751_a.func_179348_c());
/*  78 */     data.writeBoolean(this.field_179752_j);
/*  79 */     data.writeFloat(this.field_149234_b);
/*  80 */     data.writeFloat(this.field_149235_c);
/*  81 */     data.writeFloat(this.field_149232_d);
/*  82 */     data.writeFloat(this.field_149233_e);
/*  83 */     data.writeFloat(this.field_149230_f);
/*  84 */     data.writeFloat(this.field_149231_g);
/*  85 */     data.writeFloat(this.field_149237_h);
/*  86 */     data.writeInt(this.field_149238_i);
/*  87 */     int var2 = this.field_179751_a.func_179345_d();
/*     */     
/*  89 */     for (int var3 = 0; var3 < var2; var3++)
/*     */     {
/*  91 */       data.writeVarIntToBuffer(this.field_179753_k[var3]);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public EnumParticleTypes func_179749_a() {
/*  97 */     return this.field_179751_a;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_179750_b() {
/* 102 */     return this.field_179752_j;
/*     */   }
/*     */ 
/*     */   
/*     */   public double func_149220_d() {
/* 107 */     return this.field_149234_b;
/*     */   }
/*     */ 
/*     */   
/*     */   public double func_149226_e() {
/* 112 */     return this.field_149235_c;
/*     */   }
/*     */ 
/*     */   
/*     */   public double func_149225_f() {
/* 117 */     return this.field_149232_d;
/*     */   }
/*     */ 
/*     */   
/*     */   public float func_149221_g() {
/* 122 */     return this.field_149233_e;
/*     */   }
/*     */ 
/*     */   
/*     */   public float func_149224_h() {
/* 127 */     return this.field_149230_f;
/*     */   }
/*     */ 
/*     */   
/*     */   public float func_149223_i() {
/* 132 */     return this.field_149231_g;
/*     */   }
/*     */ 
/*     */   
/*     */   public float func_149227_j() {
/* 137 */     return this.field_149237_h;
/*     */   }
/*     */ 
/*     */   
/*     */   public int func_149222_k() {
/* 142 */     return this.field_149238_i;
/*     */   }
/*     */ 
/*     */   
/*     */   public int[] func_179748_k() {
/* 147 */     return this.field_179753_k;
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_180740_a(INetHandlerPlayClient p_180740_1_) {
/* 152 */     p_180740_1_.handleParticles(this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void processPacket(INetHandler handler) {
/* 160 */     func_180740_a((INetHandlerPlayClient)handler);
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\network\play\server\S2APacketParticles.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */