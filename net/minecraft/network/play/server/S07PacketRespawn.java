/*    */ package net.minecraft.network.play.server;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import net.minecraft.network.INetHandler;
/*    */ import net.minecraft.network.Packet;
/*    */ import net.minecraft.network.PacketBuffer;
/*    */ import net.minecraft.network.play.INetHandlerPlayClient;
/*    */ import net.minecraft.world.EnumDifficulty;
/*    */ import net.minecraft.world.WorldSettings;
/*    */ import net.minecraft.world.WorldType;
/*    */ 
/*    */ public class S07PacketRespawn
/*    */   implements Packet
/*    */ {
/*    */   private int field_149088_a;
/*    */   private EnumDifficulty field_149086_b;
/*    */   private WorldSettings.GameType field_149087_c;
/*    */   private WorldType field_149085_d;
/*    */   private static final String __OBFID = "CL_00001322";
/*    */   
/*    */   public S07PacketRespawn() {}
/*    */   
/*    */   public S07PacketRespawn(int p_i45213_1_, EnumDifficulty p_i45213_2_, WorldType p_i45213_3_, WorldSettings.GameType p_i45213_4_) {
/* 24 */     this.field_149088_a = p_i45213_1_;
/* 25 */     this.field_149086_b = p_i45213_2_;
/* 26 */     this.field_149087_c = p_i45213_4_;
/* 27 */     this.field_149085_d = p_i45213_3_;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void processPacket(INetHandlerPlayClient handler) {
/* 35 */     handler.handleRespawn(this);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void readPacketData(PacketBuffer data) throws IOException {
/* 43 */     this.field_149088_a = data.readInt();
/* 44 */     this.field_149086_b = EnumDifficulty.getDifficultyEnum(data.readUnsignedByte());
/* 45 */     this.field_149087_c = WorldSettings.GameType.getByID(data.readUnsignedByte());
/* 46 */     this.field_149085_d = WorldType.parseWorldType(data.readStringFromBuffer(16));
/*    */     
/* 48 */     if (this.field_149085_d == null)
/*    */     {
/* 50 */       this.field_149085_d = WorldType.DEFAULT;
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void writePacketData(PacketBuffer data) throws IOException {
/* 59 */     data.writeInt(this.field_149088_a);
/* 60 */     data.writeByte(this.field_149086_b.getDifficultyId());
/* 61 */     data.writeByte(this.field_149087_c.getID());
/* 62 */     data.writeString(this.field_149085_d.getWorldTypeName());
/*    */   }
/*    */ 
/*    */   
/*    */   public int func_149082_c() {
/* 67 */     return this.field_149088_a;
/*    */   }
/*    */ 
/*    */   
/*    */   public EnumDifficulty func_149081_d() {
/* 72 */     return this.field_149086_b;
/*    */   }
/*    */ 
/*    */   
/*    */   public WorldSettings.GameType func_149083_e() {
/* 77 */     return this.field_149087_c;
/*    */   }
/*    */ 
/*    */   
/*    */   public WorldType func_149080_f() {
/* 82 */     return this.field_149085_d;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void processPacket(INetHandler handler) {
/* 90 */     processPacket((INetHandlerPlayClient)handler);
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\network\play\server\S07PacketRespawn.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */