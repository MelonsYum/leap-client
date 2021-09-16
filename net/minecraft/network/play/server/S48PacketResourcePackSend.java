/*    */ package net.minecraft.network.play.server;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import net.minecraft.network.INetHandler;
/*    */ import net.minecraft.network.Packet;
/*    */ import net.minecraft.network.PacketBuffer;
/*    */ import net.minecraft.network.play.INetHandlerPlayClient;
/*    */ 
/*    */ public class S48PacketResourcePackSend
/*    */   implements Packet
/*    */ {
/*    */   private String url;
/*    */   private String hash;
/*    */   private static final String __OBFID = "CL_00002293";
/*    */   
/*    */   public S48PacketResourcePackSend() {}
/*    */   
/*    */   public S48PacketResourcePackSend(String url, String hash) {
/* 19 */     this.url = url;
/* 20 */     this.hash = hash;
/*    */     
/* 22 */     if (hash.length() > 40)
/*    */     {
/* 24 */       throw new IllegalArgumentException("Hash is too long (max 40, was " + hash.length() + ")");
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void readPacketData(PacketBuffer data) throws IOException {
/* 33 */     this.url = data.readStringFromBuffer(32767);
/* 34 */     this.hash = data.readStringFromBuffer(40);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void writePacketData(PacketBuffer data) throws IOException {
/* 42 */     data.writeString(this.url);
/* 43 */     data.writeString(this.hash);
/*    */   }
/*    */ 
/*    */   
/*    */   public void processPacket(INetHandlerPlayClient handler) {
/* 48 */     handler.func_175095_a(this);
/*    */   }
/*    */ 
/*    */   
/*    */   public String func_179783_a() {
/* 53 */     return this.url;
/*    */   }
/*    */ 
/*    */   
/*    */   public String func_179784_b() {
/* 58 */     return this.hash;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void processPacket(INetHandler handler) {
/* 66 */     processPacket((INetHandlerPlayClient)handler);
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\network\play\server\S48PacketResourcePackSend.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */