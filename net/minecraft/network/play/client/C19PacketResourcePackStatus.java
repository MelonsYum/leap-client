/*    */ package net.minecraft.network.play.client;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import net.minecraft.network.INetHandler;
/*    */ import net.minecraft.network.Packet;
/*    */ import net.minecraft.network.PacketBuffer;
/*    */ import net.minecraft.network.play.INetHandlerPlayServer;
/*    */ 
/*    */ public class C19PacketResourcePackStatus
/*    */   implements Packet
/*    */ {
/*    */   private String field_179720_a;
/*    */   private Action field_179719_b;
/*    */   private static final String __OBFID = "CL_00002282";
/*    */   
/*    */   public C19PacketResourcePackStatus() {}
/*    */   
/*    */   public C19PacketResourcePackStatus(String p_i45935_1_, Action p_i45935_2_) {
/* 19 */     if (p_i45935_1_.length() > 40)
/*    */     {
/* 21 */       p_i45935_1_ = p_i45935_1_.substring(0, 40);
/*    */     }
/*    */     
/* 24 */     this.field_179720_a = p_i45935_1_;
/* 25 */     this.field_179719_b = p_i45935_2_;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void readPacketData(PacketBuffer data) throws IOException {
/* 33 */     this.field_179720_a = data.readStringFromBuffer(40);
/* 34 */     this.field_179719_b = (Action)data.readEnumValue(Action.class);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void writePacketData(PacketBuffer data) throws IOException {
/* 42 */     data.writeString(this.field_179720_a);
/* 43 */     data.writeEnumValue(this.field_179719_b);
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_179718_a(INetHandlerPlayServer p_179718_1_) {
/* 48 */     p_179718_1_.func_175086_a(this);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void processPacket(INetHandler handler) {
/* 56 */     func_179718_a((INetHandlerPlayServer)handler);
/*    */   }
/*    */   
/*    */   public enum Action
/*    */   {
/* 61 */     SUCCESSFULLY_LOADED("SUCCESSFULLY_LOADED", 0),
/* 62 */     DECLINED("DECLINED", 1),
/* 63 */     FAILED_DOWNLOAD("FAILED_DOWNLOAD", 2),
/* 64 */     ACCEPTED("ACCEPTED", 3);
/*    */     
/* 66 */     private static final Action[] $VALUES = new Action[] { SUCCESSFULLY_LOADED, DECLINED, FAILED_DOWNLOAD, ACCEPTED };
/*    */     private static final String __OBFID = "CL_00002281";
/*    */     
/*    */     static {
/*    */     
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\network\play\client\C19PacketResourcePackStatus.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */