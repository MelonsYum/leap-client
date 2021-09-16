/*     */ package net.minecraft.network.play.server;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.util.List;
/*     */ import net.minecraft.entity.DataWatcher;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityList;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.network.INetHandler;
/*     */ import net.minecraft.network.Packet;
/*     */ import net.minecraft.network.PacketBuffer;
/*     */ import net.minecraft.network.play.INetHandlerPlayClient;
/*     */ import net.minecraft.util.MathHelper;
/*     */ 
/*     */ public class S0FPacketSpawnMob
/*     */   implements Packet {
/*     */   private int field_149042_a;
/*     */   private int field_149040_b;
/*     */   private int field_149041_c;
/*     */   private int field_149038_d;
/*     */   private int field_149039_e;
/*     */   private int field_149036_f;
/*     */   private int field_149037_g;
/*     */   private int field_149047_h;
/*     */   private byte field_149048_i;
/*     */   private byte field_149045_j;
/*     */   private byte field_149046_k;
/*     */   private DataWatcher field_149043_l;
/*     */   private List field_149044_m;
/*     */   private static final String __OBFID = "CL_00001279";
/*     */   
/*     */   public S0FPacketSpawnMob() {}
/*     */   
/*     */   public S0FPacketSpawnMob(EntityLivingBase p_i45192_1_) {
/*  35 */     this.field_149042_a = p_i45192_1_.getEntityId();
/*  36 */     this.field_149040_b = (byte)EntityList.getEntityID((Entity)p_i45192_1_);
/*  37 */     this.field_149041_c = MathHelper.floor_double(p_i45192_1_.posX * 32.0D);
/*  38 */     this.field_149038_d = MathHelper.floor_double(p_i45192_1_.posY * 32.0D);
/*  39 */     this.field_149039_e = MathHelper.floor_double(p_i45192_1_.posZ * 32.0D);
/*  40 */     this.field_149048_i = (byte)(int)(p_i45192_1_.rotationYaw * 256.0F / 360.0F);
/*  41 */     this.field_149045_j = (byte)(int)(p_i45192_1_.rotationPitch * 256.0F / 360.0F);
/*  42 */     this.field_149046_k = (byte)(int)(p_i45192_1_.rotationYawHead * 256.0F / 360.0F);
/*  43 */     double var2 = 3.9D;
/*  44 */     double var4 = p_i45192_1_.motionX;
/*  45 */     double var6 = p_i45192_1_.motionY;
/*  46 */     double var8 = p_i45192_1_.motionZ;
/*     */     
/*  48 */     if (var4 < -var2)
/*     */     {
/*  50 */       var4 = -var2;
/*     */     }
/*     */     
/*  53 */     if (var6 < -var2)
/*     */     {
/*  55 */       var6 = -var2;
/*     */     }
/*     */     
/*  58 */     if (var8 < -var2)
/*     */     {
/*  60 */       var8 = -var2;
/*     */     }
/*     */     
/*  63 */     if (var4 > var2)
/*     */     {
/*  65 */       var4 = var2;
/*     */     }
/*     */     
/*  68 */     if (var6 > var2)
/*     */     {
/*  70 */       var6 = var2;
/*     */     }
/*     */     
/*  73 */     if (var8 > var2)
/*     */     {
/*  75 */       var8 = var2;
/*     */     }
/*     */     
/*  78 */     this.field_149036_f = (int)(var4 * 8000.0D);
/*  79 */     this.field_149037_g = (int)(var6 * 8000.0D);
/*  80 */     this.field_149047_h = (int)(var8 * 8000.0D);
/*  81 */     this.field_149043_l = p_i45192_1_.getDataWatcher();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void readPacketData(PacketBuffer data) throws IOException {
/*  89 */     this.field_149042_a = data.readVarIntFromBuffer();
/*  90 */     this.field_149040_b = data.readByte() & 0xFF;
/*  91 */     this.field_149041_c = data.readInt();
/*  92 */     this.field_149038_d = data.readInt();
/*  93 */     this.field_149039_e = data.readInt();
/*  94 */     this.field_149048_i = data.readByte();
/*  95 */     this.field_149045_j = data.readByte();
/*  96 */     this.field_149046_k = data.readByte();
/*  97 */     this.field_149036_f = data.readShort();
/*  98 */     this.field_149037_g = data.readShort();
/*  99 */     this.field_149047_h = data.readShort();
/* 100 */     this.field_149044_m = DataWatcher.readWatchedListFromPacketBuffer(data);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void writePacketData(PacketBuffer data) throws IOException {
/* 108 */     data.writeVarIntToBuffer(this.field_149042_a);
/* 109 */     data.writeByte(this.field_149040_b & 0xFF);
/* 110 */     data.writeInt(this.field_149041_c);
/* 111 */     data.writeInt(this.field_149038_d);
/* 112 */     data.writeInt(this.field_149039_e);
/* 113 */     data.writeByte(this.field_149048_i);
/* 114 */     data.writeByte(this.field_149045_j);
/* 115 */     data.writeByte(this.field_149046_k);
/* 116 */     data.writeShort(this.field_149036_f);
/* 117 */     data.writeShort(this.field_149037_g);
/* 118 */     data.writeShort(this.field_149047_h);
/* 119 */     this.field_149043_l.writeTo(data);
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_180721_a(INetHandlerPlayClient p_180721_1_) {
/* 124 */     p_180721_1_.handleSpawnMob(this);
/*     */   }
/*     */ 
/*     */   
/*     */   public List func_149027_c() {
/* 129 */     if (this.field_149044_m == null)
/*     */     {
/* 131 */       this.field_149044_m = this.field_149043_l.getAllWatched();
/*     */     }
/*     */     
/* 134 */     return this.field_149044_m;
/*     */   }
/*     */ 
/*     */   
/*     */   public int func_149024_d() {
/* 139 */     return this.field_149042_a;
/*     */   }
/*     */ 
/*     */   
/*     */   public int func_149025_e() {
/* 144 */     return this.field_149040_b;
/*     */   }
/*     */ 
/*     */   
/*     */   public int func_149023_f() {
/* 149 */     return this.field_149041_c;
/*     */   }
/*     */ 
/*     */   
/*     */   public int func_149034_g() {
/* 154 */     return this.field_149038_d;
/*     */   }
/*     */ 
/*     */   
/*     */   public int func_149029_h() {
/* 159 */     return this.field_149039_e;
/*     */   }
/*     */ 
/*     */   
/*     */   public int func_149026_i() {
/* 164 */     return this.field_149036_f;
/*     */   }
/*     */ 
/*     */   
/*     */   public int func_149033_j() {
/* 169 */     return this.field_149037_g;
/*     */   }
/*     */ 
/*     */   
/*     */   public int func_149031_k() {
/* 174 */     return this.field_149047_h;
/*     */   }
/*     */ 
/*     */   
/*     */   public byte func_149028_l() {
/* 179 */     return this.field_149048_i;
/*     */   }
/*     */ 
/*     */   
/*     */   public byte func_149030_m() {
/* 184 */     return this.field_149045_j;
/*     */   }
/*     */ 
/*     */   
/*     */   public byte func_149032_n() {
/* 189 */     return this.field_149046_k;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void processPacket(INetHandler handler) {
/* 197 */     func_180721_a((INetHandlerPlayClient)handler);
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\network\play\server\S0FPacketSpawnMob.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */