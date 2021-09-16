/*    */ package net.minecraft.network.play.server;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import net.minecraft.network.INetHandler;
/*    */ import net.minecraft.network.Packet;
/*    */ import net.minecraft.network.PacketBuffer;
/*    */ import net.minecraft.network.play.INetHandlerPlayClient;
/*    */ import net.minecraft.util.IChatComponent;
/*    */ 
/*    */ public class S47PacketPlayerListHeaderFooter
/*    */   implements Packet
/*    */ {
/*    */   private IChatComponent field_179703_a;
/*    */   private IChatComponent field_179702_b;
/*    */   private static final String __OBFID = "CL_00002285";
/*    */   
/*    */   public S47PacketPlayerListHeaderFooter() {}
/*    */   
/*    */   public S47PacketPlayerListHeaderFooter(IChatComponent p_i45950_1_) {
/* 20 */     this.field_179703_a = p_i45950_1_;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void readPacketData(PacketBuffer data) throws IOException {
/* 28 */     this.field_179703_a = data.readChatComponent();
/* 29 */     this.field_179702_b = data.readChatComponent();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void writePacketData(PacketBuffer data) throws IOException {
/* 37 */     data.writeChatComponent(this.field_179703_a);
/* 38 */     data.writeChatComponent(this.field_179702_b);
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_179699_a(INetHandlerPlayClient p_179699_1_) {
/* 43 */     p_179699_1_.func_175096_a(this);
/*    */   }
/*    */ 
/*    */   
/*    */   public IChatComponent func_179700_a() {
/* 48 */     return this.field_179703_a;
/*    */   }
/*    */ 
/*    */   
/*    */   public IChatComponent func_179701_b() {
/* 53 */     return this.field_179702_b;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void processPacket(INetHandler handler) {
/* 61 */     func_179699_a((INetHandlerPlayClient)handler);
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\network\play\server\S47PacketPlayerListHeaderFooter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */