/*     */ package net.minecraft.network.play.server;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.util.Collection;
/*     */ import net.minecraft.network.INetHandler;
/*     */ import net.minecraft.network.Packet;
/*     */ import net.minecraft.network.PacketBuffer;
/*     */ import net.minecraft.network.play.INetHandlerPlayClient;
/*     */ import net.minecraft.util.Vec4b;
/*     */ import net.minecraft.world.storage.MapData;
/*     */ 
/*     */ public class S34PacketMaps
/*     */   implements Packet
/*     */ {
/*     */   private int mapId;
/*     */   private byte field_179739_b;
/*     */   private Vec4b[] field_179740_c;
/*     */   private int field_179737_d;
/*     */   private int field_179738_e;
/*     */   private int field_179735_f;
/*     */   private int field_179736_g;
/*     */   private byte[] field_179741_h;
/*     */   private static final String __OBFID = "CL_00001311";
/*     */   
/*     */   public S34PacketMaps() {}
/*     */   
/*     */   public S34PacketMaps(int p_i45975_1_, byte p_i45975_2_, Collection p_i45975_3_, byte[] p_i45975_4_, int p_i45975_5_, int p_i45975_6_, int p_i45975_7_, int p_i45975_8_) {
/*  28 */     this.mapId = p_i45975_1_;
/*  29 */     this.field_179739_b = p_i45975_2_;
/*  30 */     this.field_179740_c = (Vec4b[])p_i45975_3_.toArray((Object[])new Vec4b[p_i45975_3_.size()]);
/*  31 */     this.field_179737_d = p_i45975_5_;
/*  32 */     this.field_179738_e = p_i45975_6_;
/*  33 */     this.field_179735_f = p_i45975_7_;
/*  34 */     this.field_179736_g = p_i45975_8_;
/*  35 */     this.field_179741_h = new byte[p_i45975_7_ * p_i45975_8_];
/*     */     
/*  37 */     for (int var9 = 0; var9 < p_i45975_7_; var9++) {
/*     */       
/*  39 */       for (int var10 = 0; var10 < p_i45975_8_; var10++)
/*     */       {
/*  41 */         this.field_179741_h[var9 + var10 * p_i45975_7_] = p_i45975_4_[p_i45975_5_ + var9 + (p_i45975_6_ + var10) * 128];
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void readPacketData(PacketBuffer data) throws IOException {
/*  51 */     this.mapId = data.readVarIntFromBuffer();
/*  52 */     this.field_179739_b = data.readByte();
/*  53 */     this.field_179740_c = new Vec4b[data.readVarIntFromBuffer()];
/*     */     
/*  55 */     for (int var2 = 0; var2 < this.field_179740_c.length; var2++) {
/*     */       
/*  57 */       short var3 = (short)data.readByte();
/*  58 */       this.field_179740_c[var2] = new Vec4b((byte)(var3 >> 4 & 0xF), data.readByte(), data.readByte(), (byte)(var3 & 0xF));
/*     */     } 
/*     */     
/*  61 */     this.field_179735_f = data.readUnsignedByte();
/*     */     
/*  63 */     if (this.field_179735_f > 0) {
/*     */       
/*  65 */       this.field_179736_g = data.readUnsignedByte();
/*  66 */       this.field_179737_d = data.readUnsignedByte();
/*  67 */       this.field_179738_e = data.readUnsignedByte();
/*  68 */       this.field_179741_h = data.readByteArray();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void writePacketData(PacketBuffer data) throws IOException {
/*  77 */     data.writeVarIntToBuffer(this.mapId);
/*  78 */     data.writeByte(this.field_179739_b);
/*  79 */     data.writeVarIntToBuffer(this.field_179740_c.length);
/*  80 */     Vec4b[] var2 = this.field_179740_c;
/*  81 */     int var3 = var2.length;
/*     */     
/*  83 */     for (int var4 = 0; var4 < var3; var4++) {
/*     */       
/*  85 */       Vec4b var5 = var2[var4];
/*  86 */       data.writeByte((var5.func_176110_a() & 0xF) << 4 | var5.func_176111_d() & 0xF);
/*  87 */       data.writeByte(var5.func_176112_b());
/*  88 */       data.writeByte(var5.func_176113_c());
/*     */     } 
/*     */     
/*  91 */     data.writeByte(this.field_179735_f);
/*     */     
/*  93 */     if (this.field_179735_f > 0) {
/*     */       
/*  95 */       data.writeByte(this.field_179736_g);
/*  96 */       data.writeByte(this.field_179737_d);
/*  97 */       data.writeByte(this.field_179738_e);
/*  98 */       data.writeByteArray(this.field_179741_h);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_180741_a(INetHandlerPlayClient p_180741_1_) {
/* 104 */     p_180741_1_.handleMaps(this);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getMapId() {
/* 109 */     return this.mapId;
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_179734_a(MapData p_179734_1_) {
/* 114 */     p_179734_1_.scale = this.field_179739_b;
/* 115 */     p_179734_1_.playersVisibleOnMap.clear();
/*     */     
/*     */     int var2;
/* 118 */     for (var2 = 0; var2 < this.field_179740_c.length; var2++) {
/*     */       
/* 120 */       Vec4b var3 = this.field_179740_c[var2];
/* 121 */       p_179734_1_.playersVisibleOnMap.put("icon-" + var2, var3);
/*     */     } 
/*     */     
/* 124 */     for (var2 = 0; var2 < this.field_179735_f; var2++) {
/*     */       
/* 126 */       for (int var4 = 0; var4 < this.field_179736_g; var4++)
/*     */       {
/* 128 */         p_179734_1_.colors[this.field_179737_d + var2 + (this.field_179738_e + var4) * 128] = this.field_179741_h[var2 + var4 * this.field_179735_f];
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void processPacket(INetHandler handler) {
/* 138 */     func_180741_a((INetHandlerPlayClient)handler);
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\network\play\server\S34PacketMaps.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */