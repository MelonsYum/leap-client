/*    */ package net.minecraft.network.play.server;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import net.minecraft.network.INetHandler;
/*    */ import net.minecraft.network.Packet;
/*    */ import net.minecraft.network.PacketBuffer;
/*    */ import net.minecraft.network.play.INetHandlerPlayClient;
/*    */ import net.minecraft.scoreboard.ScoreObjective;
/*    */ 
/*    */ public class S3DPacketDisplayScoreboard
/*    */   implements Packet
/*    */ {
/*    */   private int field_149374_a;
/*    */   private String field_149373_b;
/*    */   private static final String __OBFID = "CL_00001325";
/*    */   
/*    */   public S3DPacketDisplayScoreboard() {}
/*    */   
/*    */   public S3DPacketDisplayScoreboard(int p_i45216_1_, ScoreObjective p_i45216_2_) {
/* 20 */     this.field_149374_a = p_i45216_1_;
/*    */     
/* 22 */     if (p_i45216_2_ == null) {
/*    */       
/* 24 */       this.field_149373_b = "";
/*    */     }
/*    */     else {
/*    */       
/* 28 */       this.field_149373_b = p_i45216_2_.getName();
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void readPacketData(PacketBuffer data) throws IOException {
/* 37 */     this.field_149374_a = data.readByte();
/* 38 */     this.field_149373_b = data.readStringFromBuffer(16);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void writePacketData(PacketBuffer data) throws IOException {
/* 46 */     data.writeByte(this.field_149374_a);
/* 47 */     data.writeString(this.field_149373_b);
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_180747_a(INetHandlerPlayClient p_180747_1_) {
/* 52 */     p_180747_1_.handleDisplayScoreboard(this);
/*    */   }
/*    */ 
/*    */   
/*    */   public int func_149371_c() {
/* 57 */     return this.field_149374_a;
/*    */   }
/*    */ 
/*    */   
/*    */   public String func_149370_d() {
/* 62 */     return this.field_149373_b;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void processPacket(INetHandler handler) {
/* 70 */     func_180747_a((INetHandlerPlayClient)handler);
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\network\play\server\S3DPacketDisplayScoreboard.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */