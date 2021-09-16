/*     */ package net.minecraft.network.play.server;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import net.minecraft.network.INetHandler;
/*     */ import net.minecraft.network.Packet;
/*     */ import net.minecraft.network.PacketBuffer;
/*     */ import net.minecraft.network.play.INetHandlerPlayClient;
/*     */ import net.minecraft.world.EnumDifficulty;
/*     */ import net.minecraft.world.WorldSettings;
/*     */ import net.minecraft.world.WorldType;
/*     */ 
/*     */ public class S01PacketJoinGame
/*     */   implements Packet
/*     */ {
/*     */   private int field_149206_a;
/*     */   private boolean field_149204_b;
/*     */   private WorldSettings.GameType field_149205_c;
/*     */   private int field_149202_d;
/*     */   private EnumDifficulty field_149203_e;
/*     */   private int field_149200_f;
/*     */   private WorldType field_149201_g;
/*     */   private boolean field_179745_h;
/*     */   private static final String __OBFID = "CL_00001310";
/*     */   
/*     */   public S01PacketJoinGame() {}
/*     */   
/*     */   public S01PacketJoinGame(int p_i45976_1_, WorldSettings.GameType p_i45976_2_, boolean p_i45976_3_, int p_i45976_4_, EnumDifficulty p_i45976_5_, int p_i45976_6_, WorldType p_i45976_7_, boolean p_i45976_8_) {
/*  28 */     this.field_149206_a = p_i45976_1_;
/*  29 */     this.field_149202_d = p_i45976_4_;
/*  30 */     this.field_149203_e = p_i45976_5_;
/*  31 */     this.field_149205_c = p_i45976_2_;
/*  32 */     this.field_149200_f = p_i45976_6_;
/*  33 */     this.field_149204_b = p_i45976_3_;
/*  34 */     this.field_149201_g = p_i45976_7_;
/*  35 */     this.field_179745_h = p_i45976_8_;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void readPacketData(PacketBuffer data) throws IOException {
/*  43 */     this.field_149206_a = data.readInt();
/*  44 */     short var2 = data.readUnsignedByte();
/*  45 */     this.field_149204_b = ((var2 & 0x8) == 8);
/*  46 */     int var3 = var2 & 0xFFFFFFF7;
/*  47 */     this.field_149205_c = WorldSettings.GameType.getByID(var3);
/*  48 */     this.field_149202_d = data.readByte();
/*  49 */     this.field_149203_e = EnumDifficulty.getDifficultyEnum(data.readUnsignedByte());
/*  50 */     this.field_149200_f = data.readUnsignedByte();
/*  51 */     this.field_149201_g = WorldType.parseWorldType(data.readStringFromBuffer(16));
/*     */     
/*  53 */     if (this.field_149201_g == null)
/*     */     {
/*  55 */       this.field_149201_g = WorldType.DEFAULT;
/*     */     }
/*     */     
/*  58 */     this.field_179745_h = data.readBoolean();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void writePacketData(PacketBuffer data) throws IOException {
/*  66 */     data.writeInt(this.field_149206_a);
/*  67 */     int var2 = this.field_149205_c.getID();
/*     */     
/*  69 */     if (this.field_149204_b)
/*     */     {
/*  71 */       var2 |= 0x8;
/*     */     }
/*     */     
/*  74 */     data.writeByte(var2);
/*  75 */     data.writeByte(this.field_149202_d);
/*  76 */     data.writeByte(this.field_149203_e.getDifficultyId());
/*  77 */     data.writeByte(this.field_149200_f);
/*  78 */     data.writeString(this.field_149201_g.getWorldTypeName());
/*  79 */     data.writeBoolean(this.field_179745_h);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void processPacket(INetHandlerPlayClient handler) {
/*  87 */     handler.handleJoinGame(this);
/*     */   }
/*     */ 
/*     */   
/*     */   public int func_149197_c() {
/*  92 */     return this.field_149206_a;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_149195_d() {
/*  97 */     return this.field_149204_b;
/*     */   }
/*     */ 
/*     */   
/*     */   public WorldSettings.GameType func_149198_e() {
/* 102 */     return this.field_149205_c;
/*     */   }
/*     */ 
/*     */   
/*     */   public int func_149194_f() {
/* 107 */     return this.field_149202_d;
/*     */   }
/*     */ 
/*     */   
/*     */   public EnumDifficulty func_149192_g() {
/* 112 */     return this.field_149203_e;
/*     */   }
/*     */ 
/*     */   
/*     */   public int func_149193_h() {
/* 117 */     return this.field_149200_f;
/*     */   }
/*     */ 
/*     */   
/*     */   public WorldType func_149196_i() {
/* 122 */     return this.field_149201_g;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_179744_h() {
/* 127 */     return this.field_179745_h;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void processPacket(INetHandler handler) {
/* 135 */     processPacket((INetHandlerPlayClient)handler);
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\network\play\server\S01PacketJoinGame.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */