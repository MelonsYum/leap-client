/*    */ package net.minecraft.network.play.server;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.util.List;
/*    */ import net.minecraft.entity.DataWatcher;
/*    */ import net.minecraft.network.INetHandler;
/*    */ import net.minecraft.network.Packet;
/*    */ import net.minecraft.network.PacketBuffer;
/*    */ import net.minecraft.network.play.INetHandlerPlayClient;
/*    */ 
/*    */ public class S1CPacketEntityMetadata
/*    */   implements Packet
/*    */ {
/*    */   private int field_149379_a;
/*    */   private List field_149378_b;
/*    */   private static final String __OBFID = "CL_00001326";
/*    */   
/*    */   public S1CPacketEntityMetadata() {}
/*    */   
/*    */   public S1CPacketEntityMetadata(int p_i45217_1_, DataWatcher p_i45217_2_, boolean p_i45217_3_) {
/* 21 */     this.field_149379_a = p_i45217_1_;
/*    */     
/* 23 */     if (p_i45217_3_) {
/*    */       
/* 25 */       this.field_149378_b = p_i45217_2_.getAllWatched();
/*    */     }
/*    */     else {
/*    */       
/* 29 */       this.field_149378_b = p_i45217_2_.getChanged();
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void readPacketData(PacketBuffer data) throws IOException {
/* 38 */     this.field_149379_a = data.readVarIntFromBuffer();
/* 39 */     this.field_149378_b = DataWatcher.readWatchedListFromPacketBuffer(data);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void writePacketData(PacketBuffer data) throws IOException {
/* 47 */     data.writeVarIntToBuffer(this.field_149379_a);
/* 48 */     DataWatcher.writeWatchedListToPacketBuffer(this.field_149378_b, data);
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_180748_a(INetHandlerPlayClient p_180748_1_) {
/* 53 */     p_180748_1_.handleEntityMetadata(this);
/*    */   }
/*    */ 
/*    */   
/*    */   public List func_149376_c() {
/* 58 */     return this.field_149378_b;
/*    */   }
/*    */ 
/*    */   
/*    */   public int func_149375_d() {
/* 63 */     return this.field_149379_a;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void processPacket(INetHandler handler) {
/* 71 */     func_180748_a((INetHandlerPlayClient)handler);
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\network\play\server\S1CPacketEntityMetadata.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */