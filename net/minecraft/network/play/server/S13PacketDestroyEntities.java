/*    */ package net.minecraft.network.play.server;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import net.minecraft.network.INetHandler;
/*    */ import net.minecraft.network.Packet;
/*    */ import net.minecraft.network.PacketBuffer;
/*    */ import net.minecraft.network.play.INetHandlerPlayClient;
/*    */ 
/*    */ public class S13PacketDestroyEntities
/*    */   implements Packet
/*    */ {
/*    */   private int[] field_149100_a;
/*    */   private static final String __OBFID = "CL_00001320";
/*    */   
/*    */   public S13PacketDestroyEntities() {}
/*    */   
/*    */   public S13PacketDestroyEntities(int... p_i45211_1_) {
/* 18 */     this.field_149100_a = p_i45211_1_;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void readPacketData(PacketBuffer data) throws IOException {
/* 26 */     this.field_149100_a = new int[data.readVarIntFromBuffer()];
/*    */     
/* 28 */     for (int var2 = 0; var2 < this.field_149100_a.length; var2++)
/*    */     {
/* 30 */       this.field_149100_a[var2] = data.readVarIntFromBuffer();
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void writePacketData(PacketBuffer data) throws IOException {
/* 39 */     data.writeVarIntToBuffer(this.field_149100_a.length);
/*    */     
/* 41 */     for (int var2 = 0; var2 < this.field_149100_a.length; var2++)
/*    */     {
/* 43 */       data.writeVarIntToBuffer(this.field_149100_a[var2]);
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void processPacket(INetHandlerPlayClient handler) {
/* 52 */     handler.handleDestroyEntities(this);
/*    */   }
/*    */ 
/*    */   
/*    */   public int[] func_149098_c() {
/* 57 */     return this.field_149100_a;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void processPacket(INetHandler handler) {
/* 65 */     processPacket((INetHandlerPlayClient)handler);
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\network\play\server\S13PacketDestroyEntities.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */