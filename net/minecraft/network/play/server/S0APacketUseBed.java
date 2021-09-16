/*    */ package net.minecraft.network.play.server;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.network.INetHandler;
/*    */ import net.minecraft.network.Packet;
/*    */ import net.minecraft.network.PacketBuffer;
/*    */ import net.minecraft.network.play.INetHandlerPlayClient;
/*    */ import net.minecraft.util.BlockPos;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ public class S0APacketUseBed
/*    */   implements Packet
/*    */ {
/*    */   private int playerID;
/*    */   private BlockPos field_179799_b;
/*    */   private static final String __OBFID = "CL_00001319";
/*    */   
/*    */   public S0APacketUseBed() {}
/*    */   
/*    */   public S0APacketUseBed(EntityPlayer p_i45964_1_, BlockPos p_i45964_2_) {
/* 22 */     this.playerID = p_i45964_1_.getEntityId();
/* 23 */     this.field_179799_b = p_i45964_2_;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void readPacketData(PacketBuffer data) throws IOException {
/* 31 */     this.playerID = data.readVarIntFromBuffer();
/* 32 */     this.field_179799_b = data.readBlockPos();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void writePacketData(PacketBuffer data) throws IOException {
/* 40 */     data.writeVarIntToBuffer(this.playerID);
/* 41 */     data.writeBlockPos(this.field_179799_b);
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_180744_a(INetHandlerPlayClient p_180744_1_) {
/* 46 */     p_180744_1_.handleUseBed(this);
/*    */   }
/*    */ 
/*    */   
/*    */   public EntityPlayer getPlayer(World worldIn) {
/* 51 */     return (EntityPlayer)worldIn.getEntityByID(this.playerID);
/*    */   }
/*    */ 
/*    */   
/*    */   public BlockPos func_179798_a() {
/* 56 */     return this.field_179799_b;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void processPacket(INetHandler handler) {
/* 64 */     func_180744_a((INetHandlerPlayClient)handler);
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\network\play\server\S0APacketUseBed.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */