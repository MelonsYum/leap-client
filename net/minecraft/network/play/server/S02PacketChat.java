/*    */ package net.minecraft.network.play.server;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import net.minecraft.network.INetHandler;
/*    */ import net.minecraft.network.Packet;
/*    */ import net.minecraft.network.PacketBuffer;
/*    */ import net.minecraft.network.play.INetHandlerPlayClient;
/*    */ import net.minecraft.util.IChatComponent;
/*    */ 
/*    */ public class S02PacketChat
/*    */   implements Packet
/*    */ {
/*    */   private IChatComponent chatComponent;
/*    */   private byte field_179842_b;
/*    */   private static final String __OBFID = "CL_00001289";
/*    */   
/*    */   public S02PacketChat() {}
/*    */   
/*    */   public S02PacketChat(IChatComponent component) {
/* 20 */     this(component, (byte)1);
/*    */   }
/*    */ 
/*    */   
/*    */   public S02PacketChat(IChatComponent p_i45986_1_, byte p_i45986_2_) {
/* 25 */     this.chatComponent = p_i45986_1_;
/* 26 */     this.field_179842_b = p_i45986_2_;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void readPacketData(PacketBuffer data) throws IOException {
/* 34 */     this.chatComponent = data.readChatComponent();
/* 35 */     this.field_179842_b = data.readByte();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void writePacketData(PacketBuffer data) throws IOException {
/* 43 */     data.writeChatComponent(this.chatComponent);
/* 44 */     data.writeByte(this.field_179842_b);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void processPacket(INetHandlerPlayClient handler) {
/* 52 */     handler.handleChat(this);
/*    */   }
/*    */ 
/*    */   
/*    */   public IChatComponent func_148915_c() {
/* 57 */     return this.chatComponent;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isChat() {
/* 62 */     return !(this.field_179842_b != 1 && this.field_179842_b != 2);
/*    */   }
/*    */ 
/*    */   
/*    */   public byte func_179841_c() {
/* 67 */     return this.field_179842_b;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void processPacket(INetHandler handler) {
/* 75 */     processPacket((INetHandlerPlayClient)handler);
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\network\play\server\S02PacketChat.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */