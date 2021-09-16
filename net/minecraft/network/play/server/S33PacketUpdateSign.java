/*    */ package net.minecraft.network.play.server;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import net.minecraft.network.INetHandler;
/*    */ import net.minecraft.network.Packet;
/*    */ import net.minecraft.network.PacketBuffer;
/*    */ import net.minecraft.network.play.INetHandlerPlayClient;
/*    */ import net.minecraft.util.BlockPos;
/*    */ import net.minecraft.util.IChatComponent;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ public class S33PacketUpdateSign
/*    */   implements Packet
/*    */ {
/*    */   private World field_179706_a;
/*    */   private BlockPos field_179705_b;
/*    */   private IChatComponent[] field_149349_d;
/*    */   private static final String __OBFID = "CL_00001338";
/*    */   
/*    */   public S33PacketUpdateSign() {}
/*    */   
/*    */   public S33PacketUpdateSign(World worldIn, BlockPos p_i45951_2_, IChatComponent[] p_i45951_3_) {
/* 23 */     this.field_179706_a = worldIn;
/* 24 */     this.field_179705_b = p_i45951_2_;
/* 25 */     this.field_149349_d = new IChatComponent[] { p_i45951_3_[0], p_i45951_3_[1], p_i45951_3_[2], p_i45951_3_[3] };
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void readPacketData(PacketBuffer data) throws IOException {
/* 33 */     this.field_179705_b = data.readBlockPos();
/* 34 */     this.field_149349_d = new IChatComponent[4];
/*    */     
/* 36 */     for (int var2 = 0; var2 < 4; var2++)
/*    */     {
/* 38 */       this.field_149349_d[var2] = data.readChatComponent();
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void writePacketData(PacketBuffer data) throws IOException {
/* 47 */     data.writeBlockPos(this.field_179705_b);
/*    */     
/* 49 */     for (int var2 = 0; var2 < 4; var2++)
/*    */     {
/* 51 */       data.writeChatComponent(this.field_149349_d[var2]);
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void processPacket(INetHandlerPlayClient handler) {
/* 60 */     handler.handleUpdateSign(this);
/*    */   }
/*    */ 
/*    */   
/*    */   public BlockPos func_179704_a() {
/* 65 */     return this.field_179705_b;
/*    */   }
/*    */ 
/*    */   
/*    */   public IChatComponent[] func_180753_b() {
/* 70 */     return this.field_149349_d;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void processPacket(INetHandler handler) {
/* 78 */     processPacket((INetHandlerPlayClient)handler);
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\network\play\server\S33PacketUpdateSign.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */