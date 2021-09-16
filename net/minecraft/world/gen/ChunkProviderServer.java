/*     */ package net.minecraft.world.gen;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import java.io.IOException;
/*     */ import java.util.Collections;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Set;
/*     */ import java.util.concurrent.ConcurrentHashMap;
/*     */ import net.minecraft.crash.CrashReport;
/*     */ import net.minecraft.crash.CrashReportCategory;
/*     */ import net.minecraft.entity.EnumCreatureType;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.IProgressUpdate;
/*     */ import net.minecraft.util.LongHashMap;
/*     */ import net.minecraft.util.ReportedException;
/*     */ import net.minecraft.world.ChunkCoordIntPair;
/*     */ import net.minecraft.world.MinecraftException;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.WorldServer;
/*     */ import net.minecraft.world.chunk.Chunk;
/*     */ import net.minecraft.world.chunk.EmptyChunk;
/*     */ import net.minecraft.world.chunk.IChunkProvider;
/*     */ import net.minecraft.world.chunk.storage.IChunkLoader;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ 
/*     */ public class ChunkProviderServer
/*     */   implements IChunkProvider {
/*  30 */   private static final Logger logger = LogManager.getLogger();
/*  31 */   private Set droppedChunksSet = Collections.newSetFromMap(new ConcurrentHashMap<>());
/*     */ 
/*     */ 
/*     */   
/*     */   private Chunk dummyChunk;
/*     */ 
/*     */ 
/*     */   
/*     */   private IChunkProvider serverChunkGenerator;
/*     */ 
/*     */ 
/*     */   
/*     */   private IChunkLoader chunkLoader;
/*     */ 
/*     */   
/*     */   public boolean chunkLoadOverride = true;
/*     */ 
/*     */   
/*  49 */   private LongHashMap id2ChunkMap = new LongHashMap();
/*  50 */   private List loadedChunks = Lists.newArrayList();
/*     */   
/*     */   private WorldServer worldObj;
/*     */   private static final String __OBFID = "CL_00001436";
/*     */   
/*     */   public ChunkProviderServer(WorldServer p_i1520_1_, IChunkLoader p_i1520_2_, IChunkProvider p_i1520_3_) {
/*  56 */     this.dummyChunk = (Chunk)new EmptyChunk((World)p_i1520_1_, 0, 0);
/*  57 */     this.worldObj = p_i1520_1_;
/*  58 */     this.chunkLoader = p_i1520_2_;
/*  59 */     this.serverChunkGenerator = p_i1520_3_;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean chunkExists(int p_73149_1_, int p_73149_2_) {
/*  67 */     return this.id2ChunkMap.containsItem(ChunkCoordIntPair.chunkXZ2Int(p_73149_1_, p_73149_2_));
/*     */   }
/*     */ 
/*     */   
/*     */   public List func_152380_a() {
/*  72 */     return this.loadedChunks;
/*     */   }
/*     */ 
/*     */   
/*     */   public void dropChunk(int p_73241_1_, int p_73241_2_) {
/*  77 */     if (this.worldObj.provider.canRespawnHere()) {
/*     */       
/*  79 */       if (!this.worldObj.chunkExists(p_73241_1_, p_73241_2_))
/*     */       {
/*  81 */         this.droppedChunksSet.add(Long.valueOf(ChunkCoordIntPair.chunkXZ2Int(p_73241_1_, p_73241_2_)));
/*     */       }
/*     */     }
/*     */     else {
/*     */       
/*  86 */       this.droppedChunksSet.add(Long.valueOf(ChunkCoordIntPair.chunkXZ2Int(p_73241_1_, p_73241_2_)));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void unloadAllChunks() {
/*  95 */     Iterator<Chunk> var1 = this.loadedChunks.iterator();
/*     */     
/*  97 */     while (var1.hasNext()) {
/*     */       
/*  99 */       Chunk var2 = var1.next();
/* 100 */       dropChunk(var2.xPosition, var2.zPosition);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Chunk loadChunk(int p_73158_1_, int p_73158_2_) {
/* 109 */     long var3 = ChunkCoordIntPair.chunkXZ2Int(p_73158_1_, p_73158_2_);
/* 110 */     this.droppedChunksSet.remove(Long.valueOf(var3));
/* 111 */     Chunk var5 = (Chunk)this.id2ChunkMap.getValueByKey(var3);
/*     */     
/* 113 */     if (var5 == null) {
/*     */       
/* 115 */       var5 = loadChunkFromFile(p_73158_1_, p_73158_2_);
/*     */       
/* 117 */       if (var5 == null)
/*     */       {
/* 119 */         if (this.serverChunkGenerator == null) {
/*     */           
/* 121 */           var5 = this.dummyChunk;
/*     */         } else {
/*     */ 
/*     */           
/*     */           try {
/*     */             
/* 127 */             var5 = this.serverChunkGenerator.provideChunk(p_73158_1_, p_73158_2_);
/*     */           }
/* 129 */           catch (Throwable var9) {
/*     */             
/* 131 */             CrashReport var7 = CrashReport.makeCrashReport(var9, "Exception generating new chunk");
/* 132 */             CrashReportCategory var8 = var7.makeCategory("Chunk to be generated");
/* 133 */             var8.addCrashSection("Location", String.format("%d,%d", new Object[] { Integer.valueOf(p_73158_1_), Integer.valueOf(p_73158_2_) }));
/* 134 */             var8.addCrashSection("Position hash", Long.valueOf(var3));
/* 135 */             var8.addCrashSection("Generator", this.serverChunkGenerator.makeString());
/* 136 */             throw new ReportedException(var7);
/*     */           } 
/*     */         } 
/*     */       }
/*     */       
/* 141 */       this.id2ChunkMap.add(var3, var5);
/* 142 */       this.loadedChunks.add(var5);
/* 143 */       var5.onChunkLoad();
/* 144 */       var5.populateChunk(this, this, p_73158_1_, p_73158_2_);
/*     */     } 
/*     */     
/* 147 */     return var5;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Chunk provideChunk(int p_73154_1_, int p_73154_2_) {
/* 156 */     Chunk var3 = (Chunk)this.id2ChunkMap.getValueByKey(ChunkCoordIntPair.chunkXZ2Int(p_73154_1_, p_73154_2_));
/* 157 */     return (var3 == null) ? ((!this.worldObj.isFindingSpawnPoint() && !this.chunkLoadOverride) ? this.dummyChunk : loadChunk(p_73154_1_, p_73154_2_)) : var3;
/*     */   }
/*     */ 
/*     */   
/*     */   private Chunk loadChunkFromFile(int p_73239_1_, int p_73239_2_) {
/* 162 */     if (this.chunkLoader == null)
/*     */     {
/* 164 */       return null;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/* 170 */       Chunk var3 = this.chunkLoader.loadChunk((World)this.worldObj, p_73239_1_, p_73239_2_);
/*     */       
/* 172 */       if (var3 != null) {
/*     */         
/* 174 */         var3.setLastSaveTime(this.worldObj.getTotalWorldTime());
/*     */         
/* 176 */         if (this.serverChunkGenerator != null)
/*     */         {
/* 178 */           this.serverChunkGenerator.func_180514_a(var3, p_73239_1_, p_73239_2_);
/*     */         }
/*     */       } 
/*     */       
/* 182 */       return var3;
/*     */     }
/* 184 */     catch (Exception var4) {
/*     */       
/* 186 */       logger.error("Couldn't load chunk", var4);
/* 187 */       return null;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void saveChunkExtraData(Chunk p_73243_1_) {
/* 194 */     if (this.chunkLoader != null) {
/*     */       
/*     */       try {
/*     */         
/* 198 */         this.chunkLoader.saveExtraChunkData((World)this.worldObj, p_73243_1_);
/*     */       }
/* 200 */       catch (Exception var3) {
/*     */         
/* 202 */         logger.error("Couldn't save entities", var3);
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private void saveChunkData(Chunk p_73242_1_) {
/* 209 */     if (this.chunkLoader != null) {
/*     */       
/*     */       try {
/*     */         
/* 213 */         p_73242_1_.setLastSaveTime(this.worldObj.getTotalWorldTime());
/* 214 */         this.chunkLoader.saveChunk((World)this.worldObj, p_73242_1_);
/*     */       }
/* 216 */       catch (IOException var3) {
/*     */         
/* 218 */         logger.error("Couldn't save chunk", var3);
/*     */       }
/* 220 */       catch (MinecraftException var4) {
/*     */         
/* 222 */         logger.error("Couldn't save chunk; already in use by another instance of Minecraft?", (Throwable)var4);
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void populate(IChunkProvider p_73153_1_, int p_73153_2_, int p_73153_3_) {
/* 232 */     Chunk var4 = provideChunk(p_73153_2_, p_73153_3_);
/*     */     
/* 234 */     if (!var4.isTerrainPopulated()) {
/*     */       
/* 236 */       var4.func_150809_p();
/*     */       
/* 238 */       if (this.serverChunkGenerator != null) {
/*     */         
/* 240 */         this.serverChunkGenerator.populate(p_73153_1_, p_73153_2_, p_73153_3_);
/* 241 */         var4.setChunkModified();
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_177460_a(IChunkProvider p_177460_1_, Chunk p_177460_2_, int p_177460_3_, int p_177460_4_) {
/* 248 */     if (this.serverChunkGenerator != null && this.serverChunkGenerator.func_177460_a(p_177460_1_, p_177460_2_, p_177460_3_, p_177460_4_)) {
/*     */       
/* 250 */       Chunk var5 = provideChunk(p_177460_3_, p_177460_4_);
/* 251 */       var5.setChunkModified();
/* 252 */       return true;
/*     */     } 
/*     */ 
/*     */     
/* 256 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean saveChunks(boolean p_73151_1_, IProgressUpdate p_73151_2_) {
/* 266 */     int var3 = 0;
/*     */     
/* 268 */     for (int var4 = 0; var4 < this.loadedChunks.size(); var4++) {
/*     */       
/* 270 */       Chunk var5 = this.loadedChunks.get(var4);
/*     */       
/* 272 */       if (p_73151_1_)
/*     */       {
/* 274 */         saveChunkExtraData(var5);
/*     */       }
/*     */       
/* 277 */       if (var5.needsSaving(p_73151_1_)) {
/*     */         
/* 279 */         saveChunkData(var5);
/* 280 */         var5.setModified(false);
/* 281 */         var3++;
/*     */         
/* 283 */         if (var3 == 24 && !p_73151_1_)
/*     */         {
/* 285 */           return false;
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 290 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void saveExtraData() {
/* 299 */     if (this.chunkLoader != null)
/*     */     {
/* 301 */       this.chunkLoader.saveExtraData();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean unloadQueuedChunks() {
/* 310 */     if (!this.worldObj.disableLevelSaving) {
/*     */       
/* 312 */       for (int var1 = 0; var1 < 100; var1++) {
/*     */         
/* 314 */         if (!this.droppedChunksSet.isEmpty()) {
/*     */           
/* 316 */           Long var2 = this.droppedChunksSet.iterator().next();
/* 317 */           Chunk var3 = (Chunk)this.id2ChunkMap.getValueByKey(var2.longValue());
/*     */           
/* 319 */           if (var3 != null) {
/*     */             
/* 321 */             var3.onChunkUnload();
/* 322 */             saveChunkData(var3);
/* 323 */             saveChunkExtraData(var3);
/* 324 */             this.id2ChunkMap.remove(var2.longValue());
/* 325 */             this.loadedChunks.remove(var3);
/*     */           } 
/*     */           
/* 328 */           this.droppedChunksSet.remove(var2);
/*     */         } 
/*     */       } 
/*     */       
/* 332 */       if (this.chunkLoader != null)
/*     */       {
/* 334 */         this.chunkLoader.chunkTick();
/*     */       }
/*     */     } 
/*     */     
/* 338 */     return this.serverChunkGenerator.unloadQueuedChunks();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canSave() {
/* 346 */     return !this.worldObj.disableLevelSaving;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String makeString() {
/* 354 */     return "ServerChunkCache: " + this.id2ChunkMap.getNumHashElements() + " Drop: " + this.droppedChunksSet.size();
/*     */   }
/*     */ 
/*     */   
/*     */   public List func_177458_a(EnumCreatureType p_177458_1_, BlockPos p_177458_2_) {
/* 359 */     return this.serverChunkGenerator.func_177458_a(p_177458_1_, p_177458_2_);
/*     */   }
/*     */ 
/*     */   
/*     */   public BlockPos func_180513_a(World worldIn, String p_180513_2_, BlockPos p_180513_3_) {
/* 364 */     return this.serverChunkGenerator.func_180513_a(worldIn, p_180513_2_, p_180513_3_);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getLoadedChunkCount() {
/* 369 */     return this.id2ChunkMap.getNumHashElements();
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_180514_a(Chunk p_180514_1_, int p_180514_2_, int p_180514_3_) {}
/*     */   
/*     */   public Chunk func_177459_a(BlockPos p_177459_1_) {
/* 376 */     return provideChunk(p_177459_1_.getX() >> 4, p_177459_1_.getZ() >> 4);
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\world\gen\ChunkProviderServer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */