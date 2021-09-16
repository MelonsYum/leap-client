/*     */ package net.minecraft.network.play.server;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.util.List;
/*     */ import java.util.UUID;
/*     */ import net.minecraft.entity.DataWatcher;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.network.INetHandler;
/*     */ import net.minecraft.network.Packet;
/*     */ import net.minecraft.network.PacketBuffer;
/*     */ import net.minecraft.network.play.INetHandlerPlayClient;
/*     */ import net.minecraft.util.MathHelper;
/*     */ 
/*     */ public class S0CPacketSpawnPlayer
/*     */   implements Packet
/*     */ {
/*     */   private int field_148957_a;
/*     */   private UUID field_179820_b;
/*     */   private int field_148956_c;
/*     */   private int field_148953_d;
/*     */   private int field_148954_e;
/*     */   private byte field_148951_f;
/*     */   private byte field_148952_g;
/*     */   private int field_148959_h;
/*     */   private DataWatcher field_148960_i;
/*     */   private List field_148958_j;
/*     */   private static final String __OBFID = "CL_00001281";
/*     */   
/*     */   public S0CPacketSpawnPlayer() {}
/*     */   
/*     */   public S0CPacketSpawnPlayer(EntityPlayer p_i45171_1_) {
/*  34 */     this.field_148957_a = p_i45171_1_.getEntityId();
/*  35 */     this.field_179820_b = p_i45171_1_.getGameProfile().getId();
/*  36 */     this.field_148956_c = MathHelper.floor_double(p_i45171_1_.posX * 32.0D);
/*  37 */     this.field_148953_d = MathHelper.floor_double(p_i45171_1_.posY * 32.0D);
/*  38 */     this.field_148954_e = MathHelper.floor_double(p_i45171_1_.posZ * 32.0D);
/*  39 */     this.field_148951_f = (byte)(int)(p_i45171_1_.rotationYaw * 256.0F / 360.0F);
/*  40 */     this.field_148952_g = (byte)(int)(p_i45171_1_.rotationPitch * 256.0F / 360.0F);
/*  41 */     ItemStack var2 = p_i45171_1_.inventory.getCurrentItem();
/*  42 */     this.field_148959_h = (var2 == null) ? 0 : Item.getIdFromItem(var2.getItem());
/*  43 */     this.field_148960_i = p_i45171_1_.getDataWatcher();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void readPacketData(PacketBuffer data) throws IOException {
/*  51 */     this.field_148957_a = data.readVarIntFromBuffer();
/*  52 */     this.field_179820_b = data.readUuid();
/*  53 */     this.field_148956_c = data.readInt();
/*  54 */     this.field_148953_d = data.readInt();
/*  55 */     this.field_148954_e = data.readInt();
/*  56 */     this.field_148951_f = data.readByte();
/*  57 */     this.field_148952_g = data.readByte();
/*  58 */     this.field_148959_h = data.readShort();
/*  59 */     this.field_148958_j = DataWatcher.readWatchedListFromPacketBuffer(data);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void writePacketData(PacketBuffer data) throws IOException {
/*  67 */     data.writeVarIntToBuffer(this.field_148957_a);
/*  68 */     data.writeUuid(this.field_179820_b);
/*  69 */     data.writeInt(this.field_148956_c);
/*  70 */     data.writeInt(this.field_148953_d);
/*  71 */     data.writeInt(this.field_148954_e);
/*  72 */     data.writeByte(this.field_148951_f);
/*  73 */     data.writeByte(this.field_148952_g);
/*  74 */     data.writeShort(this.field_148959_h);
/*  75 */     this.field_148960_i.writeTo(data);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void processPacket(INetHandlerPlayClient handler) {
/*  83 */     handler.handleSpawnPlayer(this);
/*     */   }
/*     */ 
/*     */   
/*     */   public List func_148944_c() {
/*  88 */     if (this.field_148958_j == null)
/*     */     {
/*  90 */       this.field_148958_j = this.field_148960_i.getAllWatched();
/*     */     }
/*     */     
/*  93 */     return this.field_148958_j;
/*     */   }
/*     */ 
/*     */   
/*     */   public int func_148943_d() {
/*  98 */     return this.field_148957_a;
/*     */   }
/*     */ 
/*     */   
/*     */   public UUID func_179819_c() {
/* 103 */     return this.field_179820_b;
/*     */   }
/*     */ 
/*     */   
/*     */   public int func_148942_f() {
/* 108 */     return this.field_148956_c;
/*     */   }
/*     */ 
/*     */   
/*     */   public int func_148949_g() {
/* 113 */     return this.field_148953_d;
/*     */   }
/*     */ 
/*     */   
/*     */   public int func_148946_h() {
/* 118 */     return this.field_148954_e;
/*     */   }
/*     */ 
/*     */   
/*     */   public byte func_148941_i() {
/* 123 */     return this.field_148951_f;
/*     */   }
/*     */ 
/*     */   
/*     */   public byte func_148945_j() {
/* 128 */     return this.field_148952_g;
/*     */   }
/*     */ 
/*     */   
/*     */   public int func_148947_k() {
/* 133 */     return this.field_148959_h;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void processPacket(INetHandler handler) {
/* 141 */     processPacket((INetHandlerPlayClient)handler);
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\network\play\server\S0CPacketSpawnPlayer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */