/*    */ package net.minecraft.network.play.client;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import net.minecraft.network.INetHandler;
/*    */ import net.minecraft.network.Packet;
/*    */ import net.minecraft.network.PacketBuffer;
/*    */ import net.minecraft.network.play.INetHandlerPlayServer;
/*    */ import net.minecraft.util.BlockPos;
/*    */ import net.minecraft.util.IChatComponent;
/*    */ 
/*    */ public class C12PacketUpdateSign
/*    */   implements Packet
/*    */ {
/*    */   private BlockPos field_179723_a;
/*    */   private IChatComponent[] lines;
/*    */   private static final String __OBFID = "CL_00001370";
/*    */   
/*    */   public C12PacketUpdateSign() {}
/*    */   
/*    */   public C12PacketUpdateSign(BlockPos p_i45933_1_, IChatComponent[] p_i45933_2_) {
/* 21 */     this.field_179723_a = p_i45933_1_;
/* 22 */     this.lines = new IChatComponent[] { p_i45933_2_[0], p_i45933_2_[1], p_i45933_2_[2], p_i45933_2_[3] };
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void readPacketData(PacketBuffer data) throws IOException {
/* 30 */     this.field_179723_a = data.readBlockPos();
/* 31 */     this.lines = new IChatComponent[4];
/*    */     
/* 33 */     for (int var2 = 0; var2 < 4; var2++)
/*    */     {
/* 35 */       this.lines[var2] = data.readChatComponent();
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void writePacketData(PacketBuffer data) throws IOException {
/* 44 */     data.writeBlockPos(this.field_179723_a);
/*    */     
/* 46 */     for (int var2 = 0; var2 < 4; var2++)
/*    */     {
/* 48 */       data.writeChatComponent(this.lines[var2]);
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void processPacket(INetHandlerPlayServer handler) {
/* 57 */     handler.processUpdateSign(this);
/*    */   }
/*    */ 
/*    */   
/*    */   public BlockPos func_179722_a() {
/* 62 */     return this.field_179723_a;
/*    */   }
/*    */ 
/*    */   
/*    */   public IChatComponent[] func_180768_b() {
/* 67 */     return this.lines;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void processPacket(INetHandler handler) {
/* 75 */     processPacket((INetHandlerPlayServer)handler);
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\network\play\client\C12PacketUpdateSign.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */