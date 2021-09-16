/*    */ package net.minecraft.network.play.server;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import net.minecraft.network.INetHandler;
/*    */ import net.minecraft.network.Packet;
/*    */ import net.minecraft.network.PacketBuffer;
/*    */ import net.minecraft.network.play.INetHandlerPlayClient;
/*    */ import net.minecraft.world.EnumDifficulty;
/*    */ 
/*    */ public class S41PacketServerDifficulty
/*    */   implements Packet
/*    */ {
/*    */   private EnumDifficulty field_179833_a;
/*    */   private boolean field_179832_b;
/*    */   private static final String __OBFID = "CL_00002303";
/*    */   
/*    */   public S41PacketServerDifficulty() {}
/*    */   
/*    */   public S41PacketServerDifficulty(EnumDifficulty p_i45987_1_, boolean p_i45987_2_) {
/* 20 */     this.field_179833_a = p_i45987_1_;
/* 21 */     this.field_179832_b = p_i45987_2_;
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_179829_a(INetHandlerPlayClient p_179829_1_) {
/* 26 */     p_179829_1_.func_175101_a(this);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void readPacketData(PacketBuffer data) throws IOException {
/* 34 */     this.field_179833_a = EnumDifficulty.getDifficultyEnum(data.readUnsignedByte());
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void writePacketData(PacketBuffer data) throws IOException {
/* 42 */     data.writeByte(this.field_179833_a.getDifficultyId());
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean func_179830_a() {
/* 47 */     return this.field_179832_b;
/*    */   }
/*    */ 
/*    */   
/*    */   public EnumDifficulty func_179831_b() {
/* 52 */     return this.field_179833_a;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void processPacket(INetHandler handler) {
/* 60 */     func_179829_a((INetHandlerPlayClient)handler);
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\network\play\server\S41PacketServerDifficulty.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */