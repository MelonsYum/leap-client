/*     */ package net.minecraft.network.play.server;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.util.List;
/*     */ import net.minecraft.network.INetHandler;
/*     */ import net.minecraft.network.Packet;
/*     */ import net.minecraft.network.PacketBuffer;
/*     */ import net.minecraft.network.play.INetHandlerPlayClient;
/*     */ import net.minecraft.world.chunk.Chunk;
/*     */ 
/*     */ public class S26PacketMapChunkBulk
/*     */   implements Packet
/*     */ {
/*     */   private int[] field_149266_a;
/*     */   private int[] field_149264_b;
/*     */   private S21PacketChunkData.Extracted[] field_179755_c;
/*     */   private boolean field_149267_h;
/*     */   private static final String __OBFID = "CL_00001306";
/*     */   
/*     */   public S26PacketMapChunkBulk() {}
/*     */   
/*     */   public S26PacketMapChunkBulk(List<Chunk> p_i45197_1_) {
/*  23 */     int var2 = p_i45197_1_.size();
/*  24 */     this.field_149266_a = new int[var2];
/*  25 */     this.field_149264_b = new int[var2];
/*  26 */     this.field_179755_c = new S21PacketChunkData.Extracted[var2];
/*  27 */     this.field_149267_h = !(((Chunk)p_i45197_1_.get(0)).getWorld()).provider.getHasNoSky();
/*     */     
/*  29 */     for (int var3 = 0; var3 < var2; var3++) {
/*     */       
/*  31 */       Chunk var4 = p_i45197_1_.get(var3);
/*  32 */       S21PacketChunkData.Extracted var5 = S21PacketChunkData.func_179756_a(var4, true, this.field_149267_h, 65535);
/*  33 */       this.field_149266_a[var3] = var4.xPosition;
/*  34 */       this.field_149264_b[var3] = var4.zPosition;
/*  35 */       this.field_179755_c[var3] = var5;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void readPacketData(PacketBuffer data) throws IOException {
/*  44 */     this.field_149267_h = data.readBoolean();
/*  45 */     int var2 = data.readVarIntFromBuffer();
/*  46 */     this.field_149266_a = new int[var2];
/*  47 */     this.field_149264_b = new int[var2];
/*  48 */     this.field_179755_c = new S21PacketChunkData.Extracted[var2];
/*     */     
/*     */     int var3;
/*  51 */     for (var3 = 0; var3 < var2; var3++) {
/*     */       
/*  53 */       this.field_149266_a[var3] = data.readInt();
/*  54 */       this.field_149264_b[var3] = data.readInt();
/*  55 */       this.field_179755_c[var3] = new S21PacketChunkData.Extracted();
/*  56 */       (this.field_179755_c[var3]).field_150280_b = data.readShort() & 0xFFFF;
/*  57 */       (this.field_179755_c[var3]).field_150282_a = new byte[S21PacketChunkData.func_180737_a(Integer.bitCount((this.field_179755_c[var3]).field_150280_b), this.field_149267_h, true)];
/*     */     } 
/*     */     
/*  60 */     for (var3 = 0; var3 < var2; var3++)
/*     */     {
/*  62 */       data.readBytes((this.field_179755_c[var3]).field_150282_a);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void writePacketData(PacketBuffer data) throws IOException {
/*  71 */     data.writeBoolean(this.field_149267_h);
/*  72 */     data.writeVarIntToBuffer(this.field_179755_c.length);
/*     */     
/*     */     int var2;
/*  75 */     for (var2 = 0; var2 < this.field_149266_a.length; var2++) {
/*     */       
/*  77 */       data.writeInt(this.field_149266_a[var2]);
/*  78 */       data.writeInt(this.field_149264_b[var2]);
/*  79 */       data.writeShort((short)((this.field_179755_c[var2]).field_150280_b & 0xFFFF));
/*     */     } 
/*     */     
/*  82 */     for (var2 = 0; var2 < this.field_149266_a.length; var2++)
/*     */     {
/*  84 */       data.writeBytes((this.field_179755_c[var2]).field_150282_a);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_180738_a(INetHandlerPlayClient p_180738_1_) {
/*  90 */     p_180738_1_.handleMapChunkBulk(this);
/*     */   }
/*     */ 
/*     */   
/*     */   public int func_149255_a(int p_149255_1_) {
/*  95 */     return this.field_149266_a[p_149255_1_];
/*     */   }
/*     */ 
/*     */   
/*     */   public int func_149253_b(int p_149253_1_) {
/* 100 */     return this.field_149264_b[p_149253_1_];
/*     */   }
/*     */ 
/*     */   
/*     */   public int func_149254_d() {
/* 105 */     return this.field_149266_a.length;
/*     */   }
/*     */ 
/*     */   
/*     */   public byte[] func_149256_c(int p_149256_1_) {
/* 110 */     return (this.field_179755_c[p_149256_1_]).field_150282_a;
/*     */   }
/*     */ 
/*     */   
/*     */   public int func_179754_d(int p_179754_1_) {
/* 115 */     return (this.field_179755_c[p_179754_1_]).field_150280_b;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void processPacket(INetHandler handler) {
/* 123 */     func_180738_a((INetHandlerPlayClient)handler);
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\network\play\server\S26PacketMapChunkBulk.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */