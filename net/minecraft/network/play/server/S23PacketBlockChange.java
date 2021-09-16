/*    */ package net.minecraft.network.play.server;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.block.state.IBlockState;
/*    */ import net.minecraft.network.INetHandler;
/*    */ import net.minecraft.network.Packet;
/*    */ import net.minecraft.network.PacketBuffer;
/*    */ import net.minecraft.network.play.INetHandlerPlayClient;
/*    */ import net.minecraft.util.BlockPos;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ public class S23PacketBlockChange
/*    */   implements Packet
/*    */ {
/*    */   private BlockPos field_179828_a;
/*    */   private IBlockState field_148883_d;
/*    */   private static final String __OBFID = "CL_00001287";
/*    */   
/*    */   public S23PacketBlockChange() {}
/*    */   
/*    */   public S23PacketBlockChange(World worldIn, BlockPos p_i45988_2_) {
/* 23 */     this.field_179828_a = p_i45988_2_;
/* 24 */     this.field_148883_d = worldIn.getBlockState(p_i45988_2_);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void readPacketData(PacketBuffer data) throws IOException {
/* 32 */     this.field_179828_a = data.readBlockPos();
/* 33 */     this.field_148883_d = (IBlockState)Block.BLOCK_STATE_IDS.getByValue(data.readVarIntFromBuffer());
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void writePacketData(PacketBuffer data) throws IOException {
/* 41 */     data.writeBlockPos(this.field_179828_a);
/* 42 */     data.writeVarIntToBuffer(Block.BLOCK_STATE_IDS.get(this.field_148883_d));
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_180727_a(INetHandlerPlayClient p_180727_1_) {
/* 47 */     p_180727_1_.handleBlockChange(this);
/*    */   }
/*    */ 
/*    */   
/*    */   public IBlockState func_180728_a() {
/* 52 */     return this.field_148883_d;
/*    */   }
/*    */ 
/*    */   
/*    */   public BlockPos func_179827_b() {
/* 57 */     return this.field_179828_a;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void processPacket(INetHandler handler) {
/* 65 */     func_180727_a((INetHandlerPlayClient)handler);
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\network\play\server\S23PacketBlockChange.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */