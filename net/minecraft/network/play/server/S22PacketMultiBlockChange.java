/*     */ package net.minecraft.network.play.server;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.network.INetHandler;
/*     */ import net.minecraft.network.Packet;
/*     */ import net.minecraft.network.PacketBuffer;
/*     */ import net.minecraft.network.play.INetHandlerPlayClient;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.Vec3i;
/*     */ import net.minecraft.world.ChunkCoordIntPair;
/*     */ import net.minecraft.world.chunk.Chunk;
/*     */ 
/*     */ public class S22PacketMultiBlockChange
/*     */   implements Packet {
/*     */   private ChunkCoordIntPair field_148925_b;
/*     */   private BlockUpdateData[] field_179845_b;
/*     */   private static final String __OBFID = "CL_00001290";
/*     */   
/*     */   public S22PacketMultiBlockChange() {}
/*     */   
/*     */   public S22PacketMultiBlockChange(int p_i45181_1_, short[] p_i45181_2_, Chunk p_i45181_3_) {
/*  24 */     this.field_148925_b = new ChunkCoordIntPair(p_i45181_3_.xPosition, p_i45181_3_.zPosition);
/*  25 */     this.field_179845_b = new BlockUpdateData[p_i45181_1_];
/*     */     
/*  27 */     for (int var4 = 0; var4 < this.field_179845_b.length; var4++)
/*     */     {
/*  29 */       this.field_179845_b[var4] = new BlockUpdateData(p_i45181_2_[var4], p_i45181_3_);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void readPacketData(PacketBuffer data) throws IOException {
/*  38 */     this.field_148925_b = new ChunkCoordIntPair(data.readInt(), data.readInt());
/*  39 */     this.field_179845_b = new BlockUpdateData[data.readVarIntFromBuffer()];
/*     */     
/*  41 */     for (int var2 = 0; var2 < this.field_179845_b.length; var2++)
/*     */     {
/*  43 */       this.field_179845_b[var2] = new BlockUpdateData(data.readShort(), (IBlockState)Block.BLOCK_STATE_IDS.getByValue(data.readVarIntFromBuffer()));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void writePacketData(PacketBuffer data) throws IOException {
/*  52 */     data.writeInt(this.field_148925_b.chunkXPos);
/*  53 */     data.writeInt(this.field_148925_b.chunkZPos);
/*  54 */     data.writeVarIntToBuffer(this.field_179845_b.length);
/*  55 */     BlockUpdateData[] var2 = this.field_179845_b;
/*  56 */     int var3 = var2.length;
/*     */     
/*  58 */     for (int var4 = 0; var4 < var3; var4++) {
/*     */       
/*  60 */       BlockUpdateData var5 = var2[var4];
/*  61 */       data.writeShort(var5.func_180089_b());
/*  62 */       data.writeVarIntToBuffer(Block.BLOCK_STATE_IDS.get(var5.func_180088_c()));
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_180729_a(INetHandlerPlayClient p_180729_1_) {
/*  68 */     p_180729_1_.handleMultiBlockChange(this);
/*     */   }
/*     */ 
/*     */   
/*     */   public BlockUpdateData[] func_179844_a() {
/*  73 */     return this.field_179845_b;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void processPacket(INetHandler handler) {
/*  81 */     func_180729_a((INetHandlerPlayClient)handler);
/*     */   }
/*     */ 
/*     */   
/*     */   public class BlockUpdateData
/*     */   {
/*     */     private final short field_180091_b;
/*     */     private final IBlockState field_180092_c;
/*     */     private static final String __OBFID = "CL_00002302";
/*     */     
/*     */     public BlockUpdateData(short p_i45984_2_, IBlockState p_i45984_3_) {
/*  92 */       this.field_180091_b = p_i45984_2_;
/*  93 */       this.field_180092_c = p_i45984_3_;
/*     */     }
/*     */ 
/*     */     
/*     */     public BlockUpdateData(short p_i45985_2_, Chunk p_i45985_3_) {
/*  98 */       this.field_180091_b = p_i45985_2_;
/*  99 */       this.field_180092_c = p_i45985_3_.getBlockState(func_180090_a());
/*     */     }
/*     */ 
/*     */     
/*     */     public BlockPos func_180090_a() {
/* 104 */       return new BlockPos((Vec3i)S22PacketMultiBlockChange.this.field_148925_b.getBlock(this.field_180091_b >> 12 & 0xF, this.field_180091_b & 0xFF, this.field_180091_b >> 8 & 0xF));
/*     */     }
/*     */ 
/*     */     
/*     */     public short func_180089_b() {
/* 109 */       return this.field_180091_b;
/*     */     }
/*     */ 
/*     */     
/*     */     public IBlockState func_180088_c() {
/* 114 */       return this.field_180092_c;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\network\play\server\S22PacketMultiBlockChange.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */