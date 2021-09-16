/*    */ package net.minecraft.network.play.server;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import net.minecraft.network.INetHandler;
/*    */ import net.minecraft.network.Packet;
/*    */ import net.minecraft.network.PacketBuffer;
/*    */ import net.minecraft.network.play.INetHandlerPlayClient;
/*    */ import net.minecraft.scoreboard.IScoreObjectiveCriteria;
/*    */ import net.minecraft.scoreboard.ScoreObjective;
/*    */ 
/*    */ public class S3BPacketScoreboardObjective
/*    */   implements Packet
/*    */ {
/*    */   public static String field_149343_a;
/*    */   public static String field_149341_b;
/*    */   private IScoreObjectiveCriteria.EnumRenderType field_179818_c;
/*    */   private int field_149342_c;
/*    */   private static final String __OBFID = "CL_00001333";
/*    */   
/*    */   public S3BPacketScoreboardObjective() {}
/*    */   
/*    */   public S3BPacketScoreboardObjective(ScoreObjective p_i45224_1_, int p_i45224_2_) {
/* 23 */     field_149343_a = p_i45224_1_.getName();
/* 24 */     field_149341_b = p_i45224_1_.getDisplayName();
/* 25 */     this.field_179818_c = p_i45224_1_.getCriteria().func_178790_c();
/* 26 */     this.field_149342_c = p_i45224_2_;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void readPacketData(PacketBuffer data) throws IOException {
/* 34 */     field_149343_a = data.readStringFromBuffer(16);
/* 35 */     this.field_149342_c = data.readByte();
/*    */     
/* 37 */     if (this.field_149342_c == 0 || this.field_149342_c == 2) {
/*    */       
/* 39 */       field_149341_b = data.readStringFromBuffer(32);
/* 40 */       this.field_179818_c = IScoreObjectiveCriteria.EnumRenderType.func_178795_a(data.readStringFromBuffer(16));
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void writePacketData(PacketBuffer data) throws IOException {
/* 49 */     data.writeString(field_149343_a);
/* 50 */     data.writeByte(this.field_149342_c);
/*    */     
/* 52 */     if (this.field_149342_c == 0 || this.field_149342_c == 2) {
/*    */       
/* 54 */       data.writeString(field_149341_b);
/* 55 */       data.writeString(this.field_179818_c.func_178796_a());
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void processPacket(INetHandlerPlayClient handler) {
/* 64 */     handler.handleScoreboardObjective(this);
/*    */   }
/*    */ 
/*    */   
/*    */   public String func_149339_c() {
/* 69 */     return field_149343_a;
/*    */   }
/*    */ 
/*    */   
/*    */   public String func_149337_d() {
/* 74 */     return field_149341_b;
/*    */   }
/*    */ 
/*    */   
/*    */   public int func_149338_e() {
/* 79 */     return this.field_149342_c;
/*    */   }
/*    */ 
/*    */   
/*    */   public IScoreObjectiveCriteria.EnumRenderType func_179817_d() {
/* 84 */     return this.field_179818_c;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void processPacket(INetHandler handler) {
/* 92 */     processPacket((INetHandlerPlayClient)handler);
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\network\play\server\S3BPacketScoreboardObjective.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */