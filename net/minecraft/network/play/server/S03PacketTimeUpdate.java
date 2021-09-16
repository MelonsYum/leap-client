/*    */ package net.minecraft.network.play.server;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import net.minecraft.network.INetHandler;
/*    */ import net.minecraft.network.Packet;
/*    */ import net.minecraft.network.PacketBuffer;
/*    */ import net.minecraft.network.play.INetHandlerPlayClient;
/*    */ 
/*    */ public class S03PacketTimeUpdate
/*    */   implements Packet
/*    */ {
/*    */   private long field_149369_a;
/*    */   private long field_149368_b;
/*    */   private static final String __OBFID = "CL_00001337";
/*    */   
/*    */   public S03PacketTimeUpdate() {}
/*    */   
/*    */   public S03PacketTimeUpdate(long p_i45230_1_, long p_i45230_3_, boolean p_i45230_5_) {
/* 19 */     this.field_149369_a = p_i45230_1_;
/* 20 */     this.field_149368_b = p_i45230_3_;
/*    */     
/* 22 */     if (!p_i45230_5_) {
/*    */       
/* 24 */       this.field_149368_b = -this.field_149368_b;
/*    */       
/* 26 */       if (this.field_149368_b == 0L)
/*    */       {
/* 28 */         this.field_149368_b = -1L;
/*    */       }
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void readPacketData(PacketBuffer data) throws IOException {
/* 38 */     this.field_149369_a = data.readLong();
/* 39 */     this.field_149368_b = data.readLong();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void writePacketData(PacketBuffer data) throws IOException {
/* 47 */     data.writeLong(this.field_149369_a);
/* 48 */     data.writeLong(this.field_149368_b);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void processPacket(INetHandlerPlayClient handler) {
/* 56 */     handler.handleTimeUpdate(this);
/*    */   }
/*    */ 
/*    */   
/*    */   public long func_149366_c() {
/* 61 */     return this.field_149369_a;
/*    */   }
/*    */ 
/*    */   
/*    */   public long func_149365_d() {
/* 66 */     return this.field_149368_b;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void processPacket(INetHandler handler) {
/* 74 */     processPacket((INetHandlerPlayClient)handler);
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\network\play\server\S03PacketTimeUpdate.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */