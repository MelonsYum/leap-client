/*    */ package net.minecraft.network.play.client;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import net.minecraft.network.INetHandler;
/*    */ import net.minecraft.network.Packet;
/*    */ import net.minecraft.network.PacketBuffer;
/*    */ import net.minecraft.network.play.INetHandlerPlayServer;
/*    */ 
/*    */ public class C11PacketEnchantItem
/*    */   implements Packet
/*    */ {
/*    */   private int id;
/*    */   private int button;
/*    */   private static final String __OBFID = "CL_00001352";
/*    */   
/*    */   public C11PacketEnchantItem() {}
/*    */   
/*    */   public C11PacketEnchantItem(int p_i45245_1_, int p_i45245_2_) {
/* 19 */     this.id = p_i45245_1_;
/* 20 */     this.button = p_i45245_2_;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void processPacket(INetHandlerPlayServer handler) {
/* 28 */     handler.processEnchantItem(this);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void readPacketData(PacketBuffer data) throws IOException {
/* 36 */     this.id = data.readByte();
/* 37 */     this.button = data.readByte();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void writePacketData(PacketBuffer data) throws IOException {
/* 45 */     data.writeByte(this.id);
/* 46 */     data.writeByte(this.button);
/*    */   }
/*    */ 
/*    */   
/*    */   public int getId() {
/* 51 */     return this.id;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getButton() {
/* 56 */     return this.button;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void processPacket(INetHandler handler) {
/* 64 */     processPacket((INetHandlerPlayServer)handler);
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\network\play\client\C11PacketEnchantItem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */