/*    */ package net.minecraft.network.play.server;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.util.List;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.network.INetHandler;
/*    */ import net.minecraft.network.Packet;
/*    */ import net.minecraft.network.PacketBuffer;
/*    */ import net.minecraft.network.play.INetHandlerPlayClient;
/*    */ 
/*    */ public class S30PacketWindowItems
/*    */   implements Packet
/*    */ {
/*    */   private int field_148914_a;
/*    */   private ItemStack[] field_148913_b;
/*    */   private static final String __OBFID = "CL_00001294";
/*    */   
/*    */   public S30PacketWindowItems() {}
/*    */   
/*    */   public S30PacketWindowItems(int p_i45186_1_, List<ItemStack> p_i45186_2_) {
/* 21 */     this.field_148914_a = p_i45186_1_;
/* 22 */     this.field_148913_b = new ItemStack[p_i45186_2_.size()];
/*    */     
/* 24 */     for (int var3 = 0; var3 < this.field_148913_b.length; var3++) {
/*    */       
/* 26 */       ItemStack var4 = p_i45186_2_.get(var3);
/* 27 */       this.field_148913_b[var3] = (var4 == null) ? null : var4.copy();
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void readPacketData(PacketBuffer data) throws IOException {
/* 36 */     this.field_148914_a = data.readUnsignedByte();
/* 37 */     short var2 = data.readShort();
/* 38 */     this.field_148913_b = new ItemStack[var2];
/*    */     
/* 40 */     for (int var3 = 0; var3 < var2; var3++)
/*    */     {
/* 42 */       this.field_148913_b[var3] = data.readItemStackFromBuffer();
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void writePacketData(PacketBuffer data) throws IOException {
/* 51 */     data.writeByte(this.field_148914_a);
/* 52 */     data.writeShort(this.field_148913_b.length);
/* 53 */     ItemStack[] var2 = this.field_148913_b;
/* 54 */     int var3 = var2.length;
/*    */     
/* 56 */     for (int var4 = 0; var4 < var3; var4++) {
/*    */       
/* 58 */       ItemStack var5 = var2[var4];
/* 59 */       data.writeItemStackToBuffer(var5);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_180732_a(INetHandlerPlayClient p_180732_1_) {
/* 65 */     p_180732_1_.handleWindowItems(this);
/*    */   }
/*    */ 
/*    */   
/*    */   public int func_148911_c() {
/* 70 */     return this.field_148914_a;
/*    */   }
/*    */ 
/*    */   
/*    */   public ItemStack[] func_148910_d() {
/* 75 */     return this.field_148913_b;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void processPacket(INetHandler handler) {
/* 83 */     func_180732_a((INetHandlerPlayClient)handler);
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\network\play\server\S30PacketWindowItems.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */