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
/*     */ public class S0EPacketSpawnObject
/*     */   implements Packet
/*     */ {
/*     */   private int field_149018_a;
/*     */   private int field_149016_b;
/*     */   private int field_149017_c;
/*     */   private int field_149014_d;
/*     */   private int field_149015_e;
/*     */   private int field_149012_f;
/*     */   private int field_149013_g;
/*     */   private int field_149021_h;
/*     */   private int field_149022_i;
/*     */   private int field_149019_j;
/*     */   private int field_149020_k;
/*     */   private static final String __OBFID = "CL_00001276";
/*     */   
/*     */   public S0EPacketSpawnObject() {}
/*     */   
/*     */   public S0EPacketSpawnObject(Entity p_i45165_1_, int p_i45165_2_) {
/*  30 */     this(p_i45165_1_, p_i45165_2_, 0);
/*     */   }
/*     */ 
/*     */   
/*     */   public S0EPacketSpawnObject(Entity p_i45166_1_, int p_i45166_2_, int p_i45166_3_) {
/*  35 */     this.field_149018_a = p_i45166_1_.getEntityId();
/*  36 */     this.field_149016_b = MathHelper.floor_double(p_i45166_1_.posX * 32.0D);
/*  37 */     this.field_149017_c = MathHelper.floor_double(p_i45166_1_.posY * 32.0D);
/*  38 */     this.field_149014_d = MathHelper.floor_double(p_i45166_1_.posZ * 32.0D);
/*  39 */     this.field_149021_h = MathHelper.floor_float(p_i45166_1_.rotationPitch * 256.0F / 360.0F);
/*  40 */     this.field_149022_i = MathHelper.floor_float(p_i45166_1_.rotationYaw * 256.0F / 360.0F);
/*  41 */     this.field_149019_j = p_i45166_2_;
/*  42 */     this.field_149020_k = p_i45166_3_;
/*     */     
/*  44 */     if (p_i45166_3_ > 0) {
/*     */       
/*  46 */       double var4 = p_i45166_1_.motionX;
/*  47 */       double var6 = p_i45166_1_.motionY;
/*  48 */       double var8 = p_i45166_1_.motionZ;
/*  49 */       double var10 = 3.9D;
/*     */       
/*  51 */       if (var4 < -var10)
/*     */       {
/*  53 */         var4 = -var10;
/*     */       }
/*     */       
/*  56 */       if (var6 < -var10)
/*     */       {
/*  58 */         var6 = -var10;
/*     */       }
/*     */       
/*  61 */       if (var8 < -var10)
/*     */       {
/*  63 */         var8 = -var10;
/*     */       }
/*     */       
/*  66 */       if (var4 > var10)
/*     */       {
/*  68 */         var4 = var10;
/*     */       }
/*     */       
/*  71 */       if (var6 > var10)
/*     */       {
/*  73 */         var6 = var10;
/*     */       }
/*     */       
/*  76 */       if (var8 > var10)
/*     */       {
/*  78 */         var8 = var10;
/*     */       }
/*     */       
/*  81 */       this.field_149015_e = (int)(var4 * 8000.0D);
/*  82 */       this.field_149012_f = (int)(var6 * 8000.0D);
/*  83 */       this.field_149013_g = (int)(var8 * 8000.0D);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void readPacketData(PacketBuffer data) throws IOException {
/*  92 */     this.field_149018_a = data.readVarIntFromBuffer();
/*  93 */     this.field_149019_j = data.readByte();
/*  94 */     this.field_149016_b = data.readInt();
/*  95 */     this.field_149017_c = data.readInt();
/*  96 */     this.field_149014_d = data.readInt();
/*  97 */     this.field_149021_h = data.readByte();
/*  98 */     this.field_149022_i = data.readByte();
/*  99 */     this.field_149020_k = data.readInt();
/*     */     
/* 101 */     if (this.field_149020_k > 0) {
/*     */       
/* 103 */       this.field_149015_e = data.readShort();
/* 104 */       this.field_149012_f = data.readShort();
/* 105 */       this.field_149013_g = data.readShort();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void writePacketData(PacketBuffer data) throws IOException {
/* 114 */     data.writeVarIntToBuffer(this.field_149018_a);
/* 115 */     data.writeByte(this.field_149019_j);
/* 116 */     data.writeInt(this.field_149016_b);
/* 117 */     data.writeInt(this.field_149017_c);
/* 118 */     data.writeInt(this.field_149014_d);
/* 119 */     data.writeByte(this.field_149021_h);
/* 120 */     data.writeByte(this.field_149022_i);
/* 121 */     data.writeInt(this.field_149020_k);
/*     */     
/* 123 */     if (this.field_149020_k > 0) {
/*     */       
/* 125 */       data.writeShort(this.field_149015_e);
/* 126 */       data.writeShort(this.field_149012_f);
/* 127 */       data.writeShort(this.field_149013_g);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void processPacket(INetHandlerPlayClient handler) {
/* 136 */     handler.handleSpawnObject(this);
/*     */   }
/*     */ 
/*     */   
/*     */   public int func_149001_c() {
/* 141 */     return this.field_149018_a;
/*     */   }
/*     */ 
/*     */   
/*     */   public int func_148997_d() {
/* 146 */     return this.field_149016_b;
/*     */   }
/*     */ 
/*     */   
/*     */   public int func_148998_e() {
/* 151 */     return this.field_149017_c;
/*     */   }
/*     */ 
/*     */   
/*     */   public int func_148994_f() {
/* 156 */     return this.field_149014_d;
/*     */   }
/*     */ 
/*     */   
/*     */   public int func_149010_g() {
/* 161 */     return this.field_149015_e;
/*     */   }
/*     */ 
/*     */   
/*     */   public int func_149004_h() {
/* 166 */     return this.field_149012_f;
/*     */   }
/*     */ 
/*     */   
/*     */   public int func_148999_i() {
/* 171 */     return this.field_149013_g;
/*     */   }
/*     */ 
/*     */   
/*     */   public int func_149008_j() {
/* 176 */     return this.field_149021_h;
/*     */   }
/*     */ 
/*     */   
/*     */   public int func_149006_k() {
/* 181 */     return this.field_149022_i;
/*     */   }
/*     */ 
/*     */   
/*     */   public int func_148993_l() {
/* 186 */     return this.field_149019_j;
/*     */   }
/*     */ 
/*     */   
/*     */   public int func_149009_m() {
/* 191 */     return this.field_149020_k;
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_148996_a(int p_148996_1_) {
/* 196 */     this.field_149016_b = p_148996_1_;
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_148995_b(int p_148995_1_) {
/* 201 */     this.field_149017_c = p_148995_1_;
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_149005_c(int p_149005_1_) {
/* 206 */     this.field_149014_d = p_149005_1_;
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_149003_d(int p_149003_1_) {
/* 211 */     this.field_149015_e = p_149003_1_;
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_149000_e(int p_149000_1_) {
/* 216 */     this.field_149012_f = p_149000_1_;
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_149007_f(int p_149007_1_) {
/* 221 */     this.field_149013_g = p_149007_1_;
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_149002_g(int p_149002_1_) {
/* 226 */     this.field_149020_k = p_149002_1_;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void processPacket(INetHandler handler) {
/* 234 */     processPacket((INetHandlerPlayClient)handler);
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\network\play\server\S0EPacketSpawnObject.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */