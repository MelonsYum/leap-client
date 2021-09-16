/*    */ package net.minecraft.network.play.server;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import net.minecraft.entity.item.EntityXPOrb;
/*    */ import net.minecraft.network.INetHandler;
/*    */ import net.minecraft.network.Packet;
/*    */ import net.minecraft.network.PacketBuffer;
/*    */ import net.minecraft.network.play.INetHandlerPlayClient;
/*    */ import net.minecraft.util.MathHelper;
/*    */ 
/*    */ public class S11PacketSpawnExperienceOrb
/*    */   implements Packet
/*    */ {
/*    */   private int field_148992_a;
/*    */   private int field_148990_b;
/*    */   private int field_148991_c;
/*    */   private int field_148988_d;
/*    */   private int field_148989_e;
/*    */   private static final String __OBFID = "CL_00001277";
/*    */   
/*    */   public S11PacketSpawnExperienceOrb() {}
/*    */   
/*    */   public S11PacketSpawnExperienceOrb(EntityXPOrb p_i45167_1_) {
/* 24 */     this.field_148992_a = p_i45167_1_.getEntityId();
/* 25 */     this.field_148990_b = MathHelper.floor_double(p_i45167_1_.posX * 32.0D);
/* 26 */     this.field_148991_c = MathHelper.floor_double(p_i45167_1_.posY * 32.0D);
/* 27 */     this.field_148988_d = MathHelper.floor_double(p_i45167_1_.posZ * 32.0D);
/* 28 */     this.field_148989_e = p_i45167_1_.getXpValue();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void readPacketData(PacketBuffer data) throws IOException {
/* 36 */     this.field_148992_a = data.readVarIntFromBuffer();
/* 37 */     this.field_148990_b = data.readInt();
/* 38 */     this.field_148991_c = data.readInt();
/* 39 */     this.field_148988_d = data.readInt();
/* 40 */     this.field_148989_e = data.readShort();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void writePacketData(PacketBuffer data) throws IOException {
/* 48 */     data.writeVarIntToBuffer(this.field_148992_a);
/* 49 */     data.writeInt(this.field_148990_b);
/* 50 */     data.writeInt(this.field_148991_c);
/* 51 */     data.writeInt(this.field_148988_d);
/* 52 */     data.writeShort(this.field_148989_e);
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_180719_a(INetHandlerPlayClient p_180719_1_) {
/* 57 */     p_180719_1_.handleSpawnExperienceOrb(this);
/*    */   }
/*    */ 
/*    */   
/*    */   public int func_148985_c() {
/* 62 */     return this.field_148992_a;
/*    */   }
/*    */ 
/*    */   
/*    */   public int func_148984_d() {
/* 67 */     return this.field_148990_b;
/*    */   }
/*    */ 
/*    */   
/*    */   public int func_148983_e() {
/* 72 */     return this.field_148991_c;
/*    */   }
/*    */ 
/*    */   
/*    */   public int func_148982_f() {
/* 77 */     return this.field_148988_d;
/*    */   }
/*    */ 
/*    */   
/*    */   public int func_148986_g() {
/* 82 */     return this.field_148989_e;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void processPacket(INetHandler handler) {
/* 90 */     func_180719_a((INetHandlerPlayClient)handler);
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\network\play\server\S11PacketSpawnExperienceOrb.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */