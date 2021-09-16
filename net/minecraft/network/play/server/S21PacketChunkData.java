/*     */ package net.minecraft.network.play.server;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import java.io.IOException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import net.minecraft.network.INetHandler;
/*     */ import net.minecraft.network.Packet;
/*     */ import net.minecraft.network.PacketBuffer;
/*     */ import net.minecraft.network.play.INetHandlerPlayClient;
/*     */ import net.minecraft.world.chunk.Chunk;
/*     */ import net.minecraft.world.chunk.storage.ExtendedBlockStorage;
/*     */ 
/*     */ public class S21PacketChunkData
/*     */   implements Packet
/*     */ {
/*     */   private int field_149284_a;
/*     */   private int field_149282_b;
/*     */   private Extracted field_179758_c;
/*     */   private boolean field_149279_g;
/*     */   private static final String __OBFID = "CL_00001304";
/*     */   
/*     */   public S21PacketChunkData() {}
/*     */   
/*     */   public S21PacketChunkData(Chunk p_i45196_1_, boolean p_i45196_2_, int p_i45196_3_) {
/*  26 */     this.field_149284_a = p_i45196_1_.xPosition;
/*  27 */     this.field_149282_b = p_i45196_1_.zPosition;
/*  28 */     this.field_149279_g = p_i45196_2_;
/*  29 */     this.field_179758_c = func_179756_a(p_i45196_1_, p_i45196_2_, !(p_i45196_1_.getWorld()).provider.getHasNoSky(), p_i45196_3_);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void readPacketData(PacketBuffer data) throws IOException {
/*  37 */     this.field_149284_a = data.readInt();
/*  38 */     this.field_149282_b = data.readInt();
/*  39 */     this.field_149279_g = data.readBoolean();
/*  40 */     this.field_179758_c = new Extracted();
/*  41 */     this.field_179758_c.field_150280_b = data.readShort();
/*  42 */     this.field_179758_c.field_150282_a = data.readByteArray();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void writePacketData(PacketBuffer data) throws IOException {
/*  50 */     data.writeInt(this.field_149284_a);
/*  51 */     data.writeInt(this.field_149282_b);
/*  52 */     data.writeBoolean(this.field_149279_g);
/*  53 */     data.writeShort((short)(this.field_179758_c.field_150280_b & 0xFFFF));
/*  54 */     data.writeByteArray(this.field_179758_c.field_150282_a);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void processPacket(INetHandlerPlayClient handler) {
/*  62 */     handler.handleChunkData(this);
/*     */   }
/*     */ 
/*     */   
/*     */   public byte[] func_149272_d() {
/*  67 */     return this.field_179758_c.field_150282_a;
/*     */   }
/*     */ 
/*     */   
/*     */   protected static int func_180737_a(int p_180737_0_, boolean p_180737_1_, boolean p_180737_2_) {
/*  72 */     int var3 = p_180737_0_ * 2 * 16 * 16 * 16;
/*  73 */     int var4 = p_180737_0_ * 16 * 16 * 16 / 2;
/*  74 */     int var5 = p_180737_1_ ? (p_180737_0_ * 16 * 16 * 16 / 2) : 0;
/*  75 */     int var6 = p_180737_2_ ? 256 : 0;
/*  76 */     return var3 + var4 + var5 + var6;
/*     */   }
/*     */ 
/*     */   
/*     */   public static Extracted func_179756_a(Chunk p_179756_0_, boolean p_179756_1_, boolean p_179756_2_, int p_179756_3_) {
/*  81 */     ExtendedBlockStorage[] var4 = p_179756_0_.getBlockStorageArray();
/*  82 */     Extracted var5 = new Extracted();
/*  83 */     ArrayList<ExtendedBlockStorage> var6 = Lists.newArrayList();
/*     */     
/*     */     int var7;
/*  86 */     for (var7 = 0; var7 < var4.length; var7++) {
/*     */       
/*  88 */       ExtendedBlockStorage var8 = var4[var7];
/*     */       
/*  90 */       if (var8 != null && (!p_179756_1_ || !var8.isEmpty()) && (p_179756_3_ & 1 << var7) != 0) {
/*     */         
/*  92 */         var5.field_150280_b |= 1 << var7;
/*  93 */         var6.add(var8);
/*     */       } 
/*     */     } 
/*     */     
/*  97 */     var5.field_150282_a = new byte[func_180737_a(Integer.bitCount(var5.field_150280_b), p_179756_2_, p_179756_1_)];
/*  98 */     var7 = 0;
/*  99 */     Iterator<ExtendedBlockStorage> var15 = var6.iterator();
/*     */ 
/*     */     
/* 102 */     while (var15.hasNext()) {
/*     */       
/* 104 */       ExtendedBlockStorage var9 = var15.next();
/* 105 */       char[] var10 = var9.getData();
/* 106 */       char[] var11 = var10;
/* 107 */       int var12 = var10.length;
/*     */       
/* 109 */       for (int var13 = 0; var13 < var12; var13++) {
/*     */         
/* 111 */         char var14 = var11[var13];
/* 112 */         var5.field_150282_a[var7++] = (byte)(var14 & 0xFF);
/* 113 */         var5.field_150282_a[var7++] = (byte)(var14 >> 8 & 0xFF);
/*     */       } 
/*     */     } 
/*     */     
/* 117 */     for (var15 = var6.iterator(); var15.hasNext(); var7 = func_179757_a(var9.getBlocklightArray().getData(), var5.field_150282_a, var7))
/*     */     {
/* 119 */       ExtendedBlockStorage var9 = var15.next();
/*     */     }
/*     */     
/* 122 */     if (p_179756_2_)
/*     */     {
/* 124 */       for (var15 = var6.iterator(); var15.hasNext(); var7 = func_179757_a(var9.getSkylightArray().getData(), var5.field_150282_a, var7))
/*     */       {
/* 126 */         ExtendedBlockStorage var9 = var15.next();
/*     */       }
/*     */     }
/*     */     
/* 130 */     if (p_179756_1_)
/*     */     {
/* 132 */       func_179757_a(p_179756_0_.getBiomeArray(), var5.field_150282_a, var7);
/*     */     }
/*     */     
/* 135 */     return var5;
/*     */   }
/*     */ 
/*     */   
/*     */   private static int func_179757_a(byte[] p_179757_0_, byte[] p_179757_1_, int p_179757_2_) {
/* 140 */     System.arraycopy(p_179757_0_, 0, p_179757_1_, p_179757_2_, p_179757_0_.length);
/* 141 */     return p_179757_2_ + p_179757_0_.length;
/*     */   }
/*     */ 
/*     */   
/*     */   public int func_149273_e() {
/* 146 */     return this.field_149284_a;
/*     */   }
/*     */ 
/*     */   
/*     */   public int func_149271_f() {
/* 151 */     return this.field_149282_b;
/*     */   }
/*     */ 
/*     */   
/*     */   public int func_149276_g() {
/* 156 */     return this.field_179758_c.field_150280_b;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_149274_i() {
/* 161 */     return this.field_149279_g;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void processPacket(INetHandler handler) {
/* 169 */     processPacket((INetHandlerPlayClient)handler);
/*     */   }
/*     */   
/*     */   public static class Extracted {
/*     */     public byte[] field_150282_a;
/*     */     public int field_150280_b;
/*     */     private static final String __OBFID = "CL_00001305";
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\network\play\server\S21PacketChunkData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */