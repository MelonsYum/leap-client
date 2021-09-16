/*     */ package net.minecraft.network.play.server;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import net.minecraft.network.INetHandler;
/*     */ import net.minecraft.network.Packet;
/*     */ import net.minecraft.network.PacketBuffer;
/*     */ import net.minecraft.network.play.INetHandlerPlayClient;
/*     */ import net.minecraft.potion.PotionEffect;
/*     */ 
/*     */ public class S1DPacketEntityEffect
/*     */   implements Packet
/*     */ {
/*     */   private int field_149434_a;
/*     */   private byte field_149432_b;
/*     */   private byte field_149433_c;
/*     */   private int field_149431_d;
/*     */   private byte field_179708_e;
/*     */   private static final String __OBFID = "CL_00001343";
/*     */   
/*     */   public S1DPacketEntityEffect() {}
/*     */   
/*     */   public S1DPacketEntityEffect(int p_i45237_1_, PotionEffect p_i45237_2_) {
/*  23 */     this.field_149434_a = p_i45237_1_;
/*  24 */     this.field_149432_b = (byte)(p_i45237_2_.getPotionID() & 0xFF);
/*  25 */     this.field_149433_c = (byte)(p_i45237_2_.getAmplifier() & 0xFF);
/*     */     
/*  27 */     if (p_i45237_2_.getDuration() > 32767) {
/*     */       
/*  29 */       this.field_149431_d = 32767;
/*     */     }
/*     */     else {
/*     */       
/*  33 */       this.field_149431_d = p_i45237_2_.getDuration();
/*     */     } 
/*     */     
/*  36 */     this.field_179708_e = (byte)(p_i45237_2_.func_180154_f() ? 1 : 0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void readPacketData(PacketBuffer data) throws IOException {
/*  44 */     this.field_149434_a = data.readVarIntFromBuffer();
/*  45 */     this.field_149432_b = data.readByte();
/*  46 */     this.field_149433_c = data.readByte();
/*  47 */     this.field_149431_d = data.readVarIntFromBuffer();
/*  48 */     this.field_179708_e = data.readByte();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void writePacketData(PacketBuffer data) throws IOException {
/*  56 */     data.writeVarIntToBuffer(this.field_149434_a);
/*  57 */     data.writeByte(this.field_149432_b);
/*  58 */     data.writeByte(this.field_149433_c);
/*  59 */     data.writeVarIntToBuffer(this.field_149431_d);
/*  60 */     data.writeByte(this.field_179708_e);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_149429_c() {
/*  65 */     return (this.field_149431_d == 32767);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void processPacket(INetHandlerPlayClient handler) {
/*  73 */     handler.handleEntityEffect(this);
/*     */   }
/*     */ 
/*     */   
/*     */   public int func_149426_d() {
/*  78 */     return this.field_149434_a;
/*     */   }
/*     */ 
/*     */   
/*     */   public byte func_149427_e() {
/*  83 */     return this.field_149432_b;
/*     */   }
/*     */ 
/*     */   
/*     */   public byte func_149428_f() {
/*  88 */     return this.field_149433_c;
/*     */   }
/*     */ 
/*     */   
/*     */   public int func_180755_e() {
/*  93 */     return this.field_149431_d;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_179707_f() {
/*  98 */     return (this.field_179708_e != 0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void processPacket(INetHandler handler) {
/* 106 */     processPacket((INetHandlerPlayClient)handler);
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\network\play\server\S1DPacketEntityEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */