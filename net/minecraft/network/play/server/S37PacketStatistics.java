/*    */ package net.minecraft.network.play.server;
/*    */ 
/*    */ import com.google.common.collect.Maps;
/*    */ import java.io.IOException;
/*    */ import java.util.Iterator;
/*    */ import java.util.Map;
/*    */ import net.minecraft.network.INetHandler;
/*    */ import net.minecraft.network.Packet;
/*    */ import net.minecraft.network.PacketBuffer;
/*    */ import net.minecraft.network.play.INetHandlerPlayClient;
/*    */ import net.minecraft.stats.StatBase;
/*    */ import net.minecraft.stats.StatList;
/*    */ 
/*    */ 
/*    */ public class S37PacketStatistics
/*    */   implements Packet
/*    */ {
/*    */   private Map field_148976_a;
/*    */   private static final String __OBFID = "CL_00001283";
/*    */   
/*    */   public S37PacketStatistics() {}
/*    */   
/*    */   public S37PacketStatistics(Map p_i45173_1_) {
/* 24 */     this.field_148976_a = p_i45173_1_;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void processPacket(INetHandlerPlayClient handler) {
/* 32 */     handler.handleStatistics(this);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void readPacketData(PacketBuffer data) throws IOException {
/* 40 */     int var2 = data.readVarIntFromBuffer();
/* 41 */     this.field_148976_a = Maps.newHashMap();
/*    */     
/* 43 */     for (int var3 = 0; var3 < var2; var3++) {
/*    */       
/* 45 */       StatBase var4 = StatList.getOneShotStat(data.readStringFromBuffer(32767));
/* 46 */       int var5 = data.readVarIntFromBuffer();
/*    */       
/* 48 */       if (var4 != null)
/*    */       {
/* 50 */         this.field_148976_a.put(var4, Integer.valueOf(var5));
/*    */       }
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void writePacketData(PacketBuffer data) throws IOException {
/* 60 */     data.writeVarIntToBuffer(this.field_148976_a.size());
/* 61 */     Iterator<Map.Entry> var2 = this.field_148976_a.entrySet().iterator();
/*    */     
/* 63 */     while (var2.hasNext()) {
/*    */       
/* 65 */       Map.Entry var3 = var2.next();
/* 66 */       data.writeString(((StatBase)var3.getKey()).statId);
/* 67 */       data.writeVarIntToBuffer(((Integer)var3.getValue()).intValue());
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public Map func_148974_c() {
/* 73 */     return this.field_148976_a;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void processPacket(INetHandler handler) {
/* 81 */     processPacket((INetHandlerPlayClient)handler);
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\network\play\server\S37PacketStatistics.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */