/*    */ package net.minecraft.network.play.client;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import net.minecraft.network.INetHandler;
/*    */ import net.minecraft.network.Packet;
/*    */ import net.minecraft.network.PacketBuffer;
/*    */ import net.minecraft.network.play.INetHandlerPlayServer;
/*    */ import net.minecraft.util.BlockPos;
/*    */ import org.apache.commons.lang3.StringUtils;
/*    */ 
/*    */ public class C14PacketTabComplete
/*    */   implements Packet
/*    */ {
/*    */   private String message;
/*    */   private BlockPos field_179710_b;
/*    */   private static final String __OBFID = "CL_00001346";
/*    */   
/*    */   public C14PacketTabComplete() {}
/*    */   
/*    */   public C14PacketTabComplete(String msg) {
/* 21 */     this(msg, null);
/*    */   }
/*    */ 
/*    */   
/*    */   public C14PacketTabComplete(String p_i45948_1_, BlockPos p_i45948_2_) {
/* 26 */     this.message = p_i45948_1_;
/* 27 */     this.field_179710_b = p_i45948_2_;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void readPacketData(PacketBuffer data) throws IOException {
/* 35 */     this.message = data.readStringFromBuffer(32767);
/* 36 */     boolean var2 = data.readBoolean();
/*    */     
/* 38 */     if (var2)
/*    */     {
/* 40 */       this.field_179710_b = data.readBlockPos();
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void writePacketData(PacketBuffer data) throws IOException {
/* 49 */     data.writeString(StringUtils.substring(this.message, 0, 32767));
/* 50 */     boolean var2 = (this.field_179710_b != null);
/* 51 */     data.writeBoolean(var2);
/*    */     
/* 53 */     if (var2)
/*    */     {
/* 55 */       data.writeBlockPos(this.field_179710_b);
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_180756_a(INetHandlerPlayServer p_180756_1_) {
/* 61 */     p_180756_1_.processTabComplete(this);
/*    */   }
/*    */ 
/*    */   
/*    */   public String getMessage() {
/* 66 */     return this.message;
/*    */   }
/*    */ 
/*    */   
/*    */   public BlockPos func_179709_b() {
/* 71 */     return this.field_179710_b;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void processPacket(INetHandler handler) {
/* 79 */     func_180756_a((INetHandlerPlayServer)handler);
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\network\play\client\C14PacketTabComplete.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */