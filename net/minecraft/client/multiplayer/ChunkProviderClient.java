/*     */ package net.minecraft.client.multiplayer;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import net.minecraft.entity.EnumCreatureType;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.IProgressUpdate;
/*     */ import net.minecraft.util.LongHashMap;
/*     */ import net.minecraft.world.ChunkCoordIntPair;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.chunk.Chunk;
/*     */ import net.minecraft.world.chunk.EmptyChunk;
/*     */ import net.minecraft.world.chunk.IChunkProvider;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ 
/*     */ public class ChunkProviderClient
/*     */   implements IChunkProvider {
/*  20 */   private static final Logger logger = LogManager.getLogger();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private Chunk blankChunk;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  31 */   private LongHashMap chunkMapping = new LongHashMap();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  37 */   private List chunkListing = Lists.newArrayList();
/*     */   
/*     */   private World worldObj;
/*     */   
/*     */   private static final String __OBFID = "CL_00000880";
/*     */ 
/*     */   
/*     */   public ChunkProviderClient(World worldIn) {
/*  45 */     this.blankChunk = (Chunk)new EmptyChunk(worldIn, 0, 0);
/*  46 */     this.worldObj = worldIn;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean chunkExists(int p_73149_1_, int p_73149_2_) {
/*  54 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void unloadChunk(int p_73234_1_, int p_73234_2_) {
/*  63 */     Chunk var3 = provideChunk(p_73234_1_, p_73234_2_);
/*     */     
/*  65 */     if (!var3.isEmpty())
/*     */     {
/*  67 */       var3.onChunkUnload();
/*     */     }
/*     */     
/*  70 */     this.chunkMapping.remove(ChunkCoordIntPair.chunkXZ2Int(p_73234_1_, p_73234_2_));
/*  71 */     this.chunkListing.remove(var3);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Chunk loadChunk(int p_73158_1_, int p_73158_2_) {
/*  79 */     Chunk var3 = new Chunk(this.worldObj, p_73158_1_, p_73158_2_);
/*  80 */     this.chunkMapping.add(ChunkCoordIntPair.chunkXZ2Int(p_73158_1_, p_73158_2_), var3);
/*  81 */     this.chunkListing.add(var3);
/*  82 */     var3.func_177417_c(true);
/*  83 */     return var3;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Chunk provideChunk(int p_73154_1_, int p_73154_2_) {
/*  92 */     Chunk var3 = (Chunk)this.chunkMapping.getValueByKey(ChunkCoordIntPair.chunkXZ2Int(p_73154_1_, p_73154_2_));
/*  93 */     return (var3 == null) ? this.blankChunk : var3;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean saveChunks(boolean p_73151_1_, IProgressUpdate p_73151_2_) {
/* 102 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void saveExtraData() {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean unloadQueuedChunks() {
/* 116 */     long var1 = System.currentTimeMillis();
/* 117 */     Iterator<Chunk> var3 = this.chunkListing.iterator();
/*     */     
/* 119 */     while (var3.hasNext()) {
/*     */       
/* 121 */       Chunk var4 = var3.next();
/* 122 */       var4.func_150804_b((System.currentTimeMillis() - var1 > 5L));
/*     */     } 
/*     */     
/* 125 */     if (System.currentTimeMillis() - var1 > 100L)
/*     */     {
/* 127 */       logger.info("Warning: Clientside chunk ticking took {} ms", new Object[] { Long.valueOf(System.currentTimeMillis() - var1) });
/*     */     }
/*     */     
/* 130 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canSave() {
/* 138 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void populate(IChunkProvider p_73153_1_, int p_73153_2_, int p_73153_3_) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_177460_a(IChunkProvider p_177460_1_, Chunk p_177460_2_, int p_177460_3_, int p_177460_4_) {
/* 148 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String makeString() {
/* 156 */     return "MultiplayerChunkCache: " + this.chunkMapping.getNumHashElements() + ", " + this.chunkListing.size();
/*     */   }
/*     */ 
/*     */   
/*     */   public List func_177458_a(EnumCreatureType p_177458_1_, BlockPos p_177458_2_) {
/* 161 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public BlockPos func_180513_a(World worldIn, String p_180513_2_, BlockPos p_180513_3_) {
/* 166 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getLoadedChunkCount() {
/* 171 */     return this.chunkListing.size();
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_180514_a(Chunk p_180514_1_, int p_180514_2_, int p_180514_3_) {}
/*     */   
/*     */   public Chunk func_177459_a(BlockPos p_177459_1_) {
/* 178 */     return provideChunk(p_177459_1_.getX() >> 4, p_177459_1_.getZ() >> 4);
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\multiplayer\ChunkProviderClient.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */