/*    */ package net.minecraft.network.play.server;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.nbt.NBTTagCompound;
/*    */ import net.minecraft.network.INetHandler;
/*    */ import net.minecraft.network.Packet;
/*    */ import net.minecraft.network.PacketBuffer;
/*    */ import net.minecraft.network.play.INetHandlerPlayClient;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ public class S49PacketUpdateEntityNBT
/*    */   implements Packet
/*    */ {
/*    */   private int field_179766_a;
/*    */   private NBTTagCompound field_179765_b;
/*    */   private static final String __OBFID = "CL_00002301";
/*    */   
/*    */   public S49PacketUpdateEntityNBT() {}
/*    */   
/*    */   public S49PacketUpdateEntityNBT(int p_i45979_1_, NBTTagCompound p_i45979_2_) {
/* 22 */     this.field_179766_a = p_i45979_1_;
/* 23 */     this.field_179765_b = p_i45979_2_;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void readPacketData(PacketBuffer data) throws IOException {
/* 31 */     this.field_179766_a = data.readVarIntFromBuffer();
/* 32 */     this.field_179765_b = data.readNBTTagCompoundFromBuffer();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void writePacketData(PacketBuffer data) throws IOException {
/* 40 */     data.writeVarIntToBuffer(this.field_179766_a);
/* 41 */     data.writeNBTTagCompoundToBuffer(this.field_179765_b);
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_179762_a(INetHandlerPlayClient p_179762_1_) {
/* 46 */     p_179762_1_.func_175097_a(this);
/*    */   }
/*    */ 
/*    */   
/*    */   public NBTTagCompound func_179763_a() {
/* 51 */     return this.field_179765_b;
/*    */   }
/*    */ 
/*    */   
/*    */   public Entity func_179764_a(World worldIn) {
/* 56 */     return worldIn.getEntityByID(this.field_179766_a);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void processPacket(INetHandler handler) {
/* 64 */     func_179762_a((INetHandlerPlayClient)handler);
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\network\play\server\S49PacketUpdateEntityNBT.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */