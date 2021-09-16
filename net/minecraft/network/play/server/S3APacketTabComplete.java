/*    */ package net.minecraft.network.play.server;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import net.minecraft.network.INetHandler;
/*    */ import net.minecraft.network.Packet;
/*    */ import net.minecraft.network.PacketBuffer;
/*    */ import net.minecraft.network.play.INetHandlerPlayClient;
/*    */ 
/*    */ public class S3APacketTabComplete
/*    */   implements Packet
/*    */ {
/*    */   private String[] field_149632_a;
/*    */   private static final String __OBFID = "CL_00001288";
/*    */   
/*    */   public S3APacketTabComplete() {}
/*    */   
/*    */   public S3APacketTabComplete(String[] p_i45178_1_) {
/* 18 */     this.field_149632_a = p_i45178_1_;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void readPacketData(PacketBuffer data) throws IOException {
/* 26 */     this.field_149632_a = new String[data.readVarIntFromBuffer()];
/*    */     
/* 28 */     for (int var2 = 0; var2 < this.field_149632_a.length; var2++)
/*    */     {
/* 30 */       this.field_149632_a[var2] = data.readStringFromBuffer(32767);
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void writePacketData(PacketBuffer data) throws IOException {
/* 39 */     data.writeVarIntToBuffer(this.field_149632_a.length);
/* 40 */     String[] var2 = this.field_149632_a;
/* 41 */     int var3 = var2.length;
/*    */     
/* 43 */     for (int var4 = 0; var4 < var3; var4++) {
/*    */       
/* 45 */       String var5 = var2[var4];
/* 46 */       data.writeString(var5);
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void processPacket(INetHandlerPlayClient handler) {
/* 55 */     handler.handleTabComplete(this);
/*    */   }
/*    */ 
/*    */   
/*    */   public String[] func_149630_c() {
/* 60 */     return this.field_149632_a;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void processPacket(INetHandler handler) {
/* 68 */     processPacket((INetHandlerPlayClient)handler);
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\network\play\server\S3APacketTabComplete.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */