/*    */ package net.minecraft.network.play.client;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.network.INetHandler;
/*    */ import net.minecraft.network.Packet;
/*    */ import net.minecraft.network.PacketBuffer;
/*    */ import net.minecraft.network.play.INetHandlerPlayServer;
/*    */ 
/*    */ public class C10PacketCreativeInventoryAction
/*    */   implements Packet
/*    */ {
/*    */   private int slotId;
/*    */   private ItemStack stack;
/*    */   private static final String __OBFID = "CL_00001369";
/*    */   
/*    */   public C10PacketCreativeInventoryAction() {}
/*    */   
/*    */   public C10PacketCreativeInventoryAction(int p_i45263_1_, ItemStack p_i45263_2_) {
/* 20 */     this.slotId = p_i45263_1_;
/* 21 */     this.stack = (p_i45263_2_ != null) ? p_i45263_2_.copy() : null;
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_180767_a(INetHandlerPlayServer p_180767_1_) {
/* 26 */     p_180767_1_.processCreativeInventoryAction(this);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void readPacketData(PacketBuffer data) throws IOException {
/* 34 */     this.slotId = data.readShort();
/* 35 */     this.stack = data.readItemStackFromBuffer();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void writePacketData(PacketBuffer data) throws IOException {
/* 43 */     data.writeShort(this.slotId);
/* 44 */     data.writeItemStackToBuffer(this.stack);
/*    */   }
/*    */ 
/*    */   
/*    */   public int getSlotId() {
/* 49 */     return this.slotId;
/*    */   }
/*    */ 
/*    */   
/*    */   public ItemStack getStack() {
/* 54 */     return this.stack;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void processPacket(INetHandler handler) {
/* 62 */     func_180767_a((INetHandlerPlayServer)handler);
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\network\play\client\C10PacketCreativeInventoryAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */