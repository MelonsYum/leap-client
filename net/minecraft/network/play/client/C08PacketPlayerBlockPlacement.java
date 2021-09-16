/*     */ package net.minecraft.network.play.client;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.network.INetHandler;
/*     */ import net.minecraft.network.Packet;
/*     */ import net.minecraft.network.PacketBuffer;
/*     */ import net.minecraft.network.play.INetHandlerPlayServer;
/*     */ import net.minecraft.util.BlockPos;
/*     */ 
/*     */ public class C08PacketPlayerBlockPlacement
/*     */   implements Packet {
/*  13 */   private static final BlockPos field_179726_a = new BlockPos(-1, -1, -1);
/*     */   
/*     */   private BlockPos field_179725_b;
/*     */   private int placedBlockDirection;
/*     */   private ItemStack stack;
/*     */   private float facingX;
/*     */   private float facingY;
/*     */   private float facingZ;
/*     */   private static final String __OBFID = "CL_00001371";
/*     */   
/*     */   public C08PacketPlayerBlockPlacement() {}
/*     */   
/*     */   public C08PacketPlayerBlockPlacement(ItemStack p_i45930_1_) {
/*  26 */     this(field_179726_a, 255, p_i45930_1_, 0.0F, 0.0F, 0.0F);
/*     */   }
/*     */ 
/*     */   
/*     */   public C08PacketPlayerBlockPlacement(BlockPos p_i45931_1_, int p_i45931_2_, ItemStack p_i45931_3_, float p_i45931_4_, float p_i45931_5_, float p_i45931_6_) {
/*  31 */     this.field_179725_b = p_i45931_1_;
/*  32 */     this.placedBlockDirection = p_i45931_2_;
/*  33 */     this.stack = (p_i45931_3_ != null) ? p_i45931_3_.copy() : null;
/*  34 */     this.facingX = p_i45931_4_;
/*  35 */     this.facingY = p_i45931_5_;
/*  36 */     this.facingZ = p_i45931_6_;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void readPacketData(PacketBuffer data) throws IOException {
/*  44 */     this.field_179725_b = data.readBlockPos();
/*  45 */     this.placedBlockDirection = data.readUnsignedByte();
/*  46 */     this.stack = data.readItemStackFromBuffer();
/*  47 */     this.facingX = data.readUnsignedByte() / 16.0F;
/*  48 */     this.facingY = data.readUnsignedByte() / 16.0F;
/*  49 */     this.facingZ = data.readUnsignedByte() / 16.0F;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void writePacketData(PacketBuffer data) throws IOException {
/*  57 */     data.writeBlockPos(this.field_179725_b);
/*  58 */     data.writeByte(this.placedBlockDirection);
/*  59 */     data.writeItemStackToBuffer(this.stack);
/*  60 */     data.writeByte((int)(this.facingX * 16.0F));
/*  61 */     data.writeByte((int)(this.facingY * 16.0F));
/*  62 */     data.writeByte((int)(this.facingZ * 16.0F));
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_180769_a(INetHandlerPlayServer p_180769_1_) {
/*  67 */     p_180769_1_.processPlayerBlockPlacement(this);
/*     */   }
/*     */ 
/*     */   
/*     */   public BlockPos func_179724_a() {
/*  72 */     return this.field_179725_b;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getPlacedBlockDirection() {
/*  77 */     return this.placedBlockDirection;
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemStack getStack() {
/*  82 */     return this.stack;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getPlacedBlockOffsetX() {
/*  90 */     return this.facingX;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getPlacedBlockOffsetY() {
/*  98 */     return this.facingY;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getPlacedBlockOffsetZ() {
/* 106 */     return this.facingZ;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void processPacket(INetHandler handler) {
/* 114 */     func_180769_a((INetHandlerPlayServer)handler);
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\network\play\client\C08PacketPlayerBlockPlacement.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */