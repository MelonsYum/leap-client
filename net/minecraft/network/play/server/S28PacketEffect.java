/*    */ package net.minecraft.network.play.server;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import net.minecraft.network.INetHandler;
/*    */ import net.minecraft.network.Packet;
/*    */ import net.minecraft.network.PacketBuffer;
/*    */ import net.minecraft.network.play.INetHandlerPlayClient;
/*    */ import net.minecraft.util.BlockPos;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class S28PacketEffect
/*    */   implements Packet
/*    */ {
/*    */   private int soundType;
/*    */   private BlockPos field_179747_b;
/*    */   private int soundData;
/*    */   private boolean serverWide;
/*    */   private static final String __OBFID = "CL_00001307";
/*    */   
/*    */   public S28PacketEffect() {}
/*    */   
/*    */   public S28PacketEffect(int p_i45978_1_, BlockPos p_i45978_2_, int p_i45978_3_, boolean p_i45978_4_) {
/* 26 */     this.soundType = p_i45978_1_;
/* 27 */     this.field_179747_b = p_i45978_2_;
/* 28 */     this.soundData = p_i45978_3_;
/* 29 */     this.serverWide = p_i45978_4_;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void readPacketData(PacketBuffer data) throws IOException {
/* 37 */     this.soundType = data.readInt();
/* 38 */     this.field_179747_b = data.readBlockPos();
/* 39 */     this.soundData = data.readInt();
/* 40 */     this.serverWide = data.readBoolean();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void writePacketData(PacketBuffer data) throws IOException {
/* 48 */     data.writeInt(this.soundType);
/* 49 */     data.writeBlockPos(this.field_179747_b);
/* 50 */     data.writeInt(this.soundData);
/* 51 */     data.writeBoolean(this.serverWide);
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_180739_a(INetHandlerPlayClient p_180739_1_) {
/* 56 */     p_180739_1_.handleEffect(this);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isSoundServerwide() {
/* 61 */     return this.serverWide;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getSoundType() {
/* 66 */     return this.soundType;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getSoundData() {
/* 71 */     return this.soundData;
/*    */   }
/*    */ 
/*    */   
/*    */   public BlockPos func_179746_d() {
/* 76 */     return this.field_179747_b;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void processPacket(INetHandler handler) {
/* 84 */     func_180739_a((INetHandlerPlayClient)handler);
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\network\play\server\S28PacketEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */