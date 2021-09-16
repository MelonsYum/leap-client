/*    */ package net.minecraft.network.play.server;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import net.minecraft.network.INetHandler;
/*    */ import net.minecraft.network.Packet;
/*    */ import net.minecraft.network.PacketBuffer;
/*    */ import net.minecraft.network.play.INetHandlerPlayClient;
/*    */ 
/*    */ public class S1FPacketSetExperience
/*    */   implements Packet
/*    */ {
/*    */   private float field_149401_a;
/*    */   private int field_149399_b;
/*    */   private int field_149400_c;
/*    */   private static final String __OBFID = "CL_00001331";
/*    */   
/*    */   public S1FPacketSetExperience() {}
/*    */   
/*    */   public S1FPacketSetExperience(float p_i45222_1_, int p_i45222_2_, int p_i45222_3_) {
/* 20 */     this.field_149401_a = p_i45222_1_;
/* 21 */     this.field_149399_b = p_i45222_2_;
/* 22 */     this.field_149400_c = p_i45222_3_;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void readPacketData(PacketBuffer data) throws IOException {
/* 30 */     this.field_149401_a = data.readFloat();
/* 31 */     this.field_149400_c = data.readVarIntFromBuffer();
/* 32 */     this.field_149399_b = data.readVarIntFromBuffer();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void writePacketData(PacketBuffer data) throws IOException {
/* 40 */     data.writeFloat(this.field_149401_a);
/* 41 */     data.writeVarIntToBuffer(this.field_149400_c);
/* 42 */     data.writeVarIntToBuffer(this.field_149399_b);
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_180749_a(INetHandlerPlayClient p_180749_1_) {
/* 47 */     p_180749_1_.handleSetExperience(this);
/*    */   }
/*    */ 
/*    */   
/*    */   public float func_149397_c() {
/* 52 */     return this.field_149401_a;
/*    */   }
/*    */ 
/*    */   
/*    */   public int func_149396_d() {
/* 57 */     return this.field_149399_b;
/*    */   }
/*    */ 
/*    */   
/*    */   public int func_149395_e() {
/* 62 */     return this.field_149400_c;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void processPacket(INetHandler handler) {
/* 70 */     func_180749_a((INetHandlerPlayClient)handler);
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\network\play\server\S1FPacketSetExperience.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */