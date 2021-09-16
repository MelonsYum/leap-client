/*     */ package net.minecraft.network.play.server;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import java.io.IOException;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import net.minecraft.network.INetHandler;
/*     */ import net.minecraft.network.Packet;
/*     */ import net.minecraft.network.PacketBuffer;
/*     */ import net.minecraft.network.play.INetHandlerPlayClient;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.Vec3;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class S27PacketExplosion
/*     */   implements Packet
/*     */ {
/*     */   private double field_149158_a;
/*     */   private double field_149156_b;
/*     */   private double field_149157_c;
/*     */   private float field_149154_d;
/*     */   private List field_149155_e;
/*     */   private float field_149152_f;
/*     */   private float field_149153_g;
/*     */   private float field_149159_h;
/*     */   private static final String __OBFID = "CL_00001300";
/*     */   
/*     */   public S27PacketExplosion() {}
/*     */   
/*     */   public S27PacketExplosion(double p_i45193_1_, double p_i45193_3_, double p_i45193_5_, float p_i45193_7_, List p_i45193_8_, Vec3 p_i45193_9_) {
/*  34 */     this.field_149158_a = p_i45193_1_;
/*  35 */     this.field_149156_b = p_i45193_3_;
/*  36 */     this.field_149157_c = p_i45193_5_;
/*  37 */     this.field_149154_d = p_i45193_7_;
/*  38 */     this.field_149155_e = Lists.newArrayList(p_i45193_8_);
/*     */     
/*  40 */     if (p_i45193_9_ != null) {
/*     */       
/*  42 */       this.field_149152_f = (float)p_i45193_9_.xCoord;
/*  43 */       this.field_149153_g = (float)p_i45193_9_.yCoord;
/*  44 */       this.field_149159_h = (float)p_i45193_9_.zCoord;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void readPacketData(PacketBuffer data) throws IOException {
/*  53 */     this.field_149158_a = data.readFloat();
/*  54 */     this.field_149156_b = data.readFloat();
/*  55 */     this.field_149157_c = data.readFloat();
/*  56 */     this.field_149154_d = data.readFloat();
/*  57 */     int var2 = data.readInt();
/*  58 */     this.field_149155_e = Lists.newArrayListWithCapacity(var2);
/*  59 */     int var3 = (int)this.field_149158_a;
/*  60 */     int var4 = (int)this.field_149156_b;
/*  61 */     int var5 = (int)this.field_149157_c;
/*     */     
/*  63 */     for (int var6 = 0; var6 < var2; var6++) {
/*     */       
/*  65 */       int var7 = data.readByte() + var3;
/*  66 */       int var8 = data.readByte() + var4;
/*  67 */       int var9 = data.readByte() + var5;
/*  68 */       this.field_149155_e.add(new BlockPos(var7, var8, var9));
/*     */     } 
/*     */     
/*  71 */     this.field_149152_f = data.readFloat();
/*  72 */     this.field_149153_g = data.readFloat();
/*  73 */     this.field_149159_h = data.readFloat();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void writePacketData(PacketBuffer data) throws IOException {
/*  82 */     data.writeFloat((float)this.field_149158_a);
/*  83 */     data.writeFloat((float)this.field_149156_b);
/*  84 */     data.writeFloat((float)this.field_149157_c);
/*  85 */     data.writeFloat(this.field_149154_d);
/*  86 */     data.writeInt(this.field_149155_e.size());
/*  87 */     int var2 = (int)this.field_149158_a;
/*  88 */     int var3 = (int)this.field_149156_b;
/*  89 */     int var4 = (int)this.field_149157_c;
/*  90 */     Iterator<BlockPos> var5 = this.field_149155_e.iterator();
/*     */     
/*  92 */     while (var5.hasNext()) {
/*     */       
/*  94 */       BlockPos var6 = var5.next();
/*  95 */       int var7 = var6.getX() - var2;
/*  96 */       int var8 = var6.getY() - var3;
/*  97 */       int var9 = var6.getZ() - var4;
/*  98 */       data.writeByte(var7);
/*  99 */       data.writeByte(var8);
/* 100 */       data.writeByte(var9);
/*     */     } 
/*     */     
/* 103 */     data.writeFloat(this.field_149152_f);
/* 104 */     data.writeFloat(this.field_149153_g);
/* 105 */     data.writeFloat(this.field_149159_h);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void processPacket(INetHandlerPlayClient handler) {
/* 114 */     handler.handleExplosion(this);
/*     */   }
/*     */ 
/*     */   
/*     */   public float func_149149_c() {
/* 119 */     return this.field_149152_f;
/*     */   }
/*     */ 
/*     */   
/*     */   public float func_149144_d() {
/* 124 */     return this.field_149153_g;
/*     */   }
/*     */ 
/*     */   
/*     */   public float func_149147_e() {
/* 129 */     return this.field_149159_h;
/*     */   }
/*     */ 
/*     */   
/*     */   public double func_149148_f() {
/* 134 */     return this.field_149158_a;
/*     */   }
/*     */ 
/*     */   
/*     */   public double func_149143_g() {
/* 139 */     return this.field_149156_b;
/*     */   }
/*     */ 
/*     */   
/*     */   public double func_149145_h() {
/* 144 */     return this.field_149157_c;
/*     */   }
/*     */ 
/*     */   
/*     */   public float func_149146_i() {
/* 149 */     return this.field_149154_d;
/*     */   }
/*     */ 
/*     */   
/*     */   public List func_149150_j() {
/* 154 */     return this.field_149155_e;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void processPacket(INetHandler handler) {
/* 162 */     processPacket((INetHandlerPlayClient)handler);
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\network\play\server\S27PacketExplosion.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */