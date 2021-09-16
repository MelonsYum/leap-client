/*    */ package net.minecraft.network.play.server;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import net.minecraft.nbt.NBTTagCompound;
/*    */ import net.minecraft.network.INetHandler;
/*    */ import net.minecraft.network.Packet;
/*    */ import net.minecraft.network.PacketBuffer;
/*    */ import net.minecraft.network.play.INetHandlerPlayClient;
/*    */ import net.minecraft.util.BlockPos;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class S35PacketUpdateTileEntity
/*    */   implements Packet
/*    */ {
/*    */   private BlockPos field_179824_a;
/*    */   private int metadata;
/*    */   private NBTTagCompound nbt;
/*    */   private static final String __OBFID = "CL_00001285";
/*    */   
/*    */   public S35PacketUpdateTileEntity() {}
/*    */   
/*    */   public S35PacketUpdateTileEntity(BlockPos p_i45990_1_, int p_i45990_2_, NBTTagCompound p_i45990_3_) {
/* 24 */     this.field_179824_a = p_i45990_1_;
/* 25 */     this.metadata = p_i45990_2_;
/* 26 */     this.nbt = p_i45990_3_;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void readPacketData(PacketBuffer data) throws IOException {
/* 34 */     this.field_179824_a = data.readBlockPos();
/* 35 */     this.metadata = data.readUnsignedByte();
/* 36 */     this.nbt = data.readNBTTagCompoundFromBuffer();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void writePacketData(PacketBuffer data) throws IOException {
/* 44 */     data.writeBlockPos(this.field_179824_a);
/* 45 */     data.writeByte((byte)this.metadata);
/* 46 */     data.writeNBTTagCompoundToBuffer(this.nbt);
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_180725_a(INetHandlerPlayClient p_180725_1_) {
/* 51 */     p_180725_1_.handleUpdateTileEntity(this);
/*    */   }
/*    */ 
/*    */   
/*    */   public BlockPos func_179823_a() {
/* 56 */     return this.field_179824_a;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getTileEntityType() {
/* 61 */     return this.metadata;
/*    */   }
/*    */ 
/*    */   
/*    */   public NBTTagCompound getNbtCompound() {
/* 66 */     return this.nbt;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void processPacket(INetHandler handler) {
/* 74 */     func_180725_a((INetHandlerPlayClient)handler);
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\network\play\server\S35PacketUpdateTileEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */