/*     */ package net.minecraft.world.biome;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import java.util.List;
/*     */ import net.minecraft.server.MinecraftServer;
/*     */ import net.minecraft.util.LongHashMap;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BiomeCache
/*     */ {
/*     */   private final WorldChunkManager chunkManager;
/*     */   private long lastCleanupTime;
/*  19 */   private LongHashMap cacheMap = new LongHashMap();
/*     */ 
/*     */   
/*  22 */   private List cache = Lists.newArrayList();
/*     */   
/*     */   private static final String __OBFID = "CL_00000162";
/*     */   
/*     */   public BiomeCache(WorldChunkManager p_i1973_1_) {
/*  27 */     this.chunkManager = p_i1973_1_;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Block getBiomeCacheBlock(int p_76840_1_, int p_76840_2_) {
/*  35 */     p_76840_1_ >>= 4;
/*  36 */     p_76840_2_ >>= 4;
/*  37 */     long var3 = p_76840_1_ & 0xFFFFFFFFL | (p_76840_2_ & 0xFFFFFFFFL) << 32L;
/*  38 */     Block var5 = (Block)this.cacheMap.getValueByKey(var3);
/*     */     
/*  40 */     if (var5 == null) {
/*     */       
/*  42 */       var5 = new Block(p_76840_1_, p_76840_2_);
/*  43 */       this.cacheMap.add(var3, var5);
/*  44 */       this.cache.add(var5);
/*     */     } 
/*     */     
/*  47 */     var5.lastAccessTime = MinecraftServer.getCurrentTimeMillis();
/*  48 */     return var5;
/*     */   }
/*     */ 
/*     */   
/*     */   public BiomeGenBase func_180284_a(int p_180284_1_, int p_180284_2_, BiomeGenBase p_180284_3_) {
/*  53 */     BiomeGenBase var4 = getBiomeCacheBlock(p_180284_1_, p_180284_2_).getBiomeGenAt(p_180284_1_, p_180284_2_);
/*  54 */     return (var4 == null) ? p_180284_3_ : var4;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void cleanupCache() {
/*  62 */     long var1 = MinecraftServer.getCurrentTimeMillis();
/*  63 */     long var3 = var1 - this.lastCleanupTime;
/*     */     
/*  65 */     if (var3 > 7500L || var3 < 0L) {
/*     */       
/*  67 */       this.lastCleanupTime = var1;
/*     */       
/*  69 */       for (int var5 = 0; var5 < this.cache.size(); var5++) {
/*     */         
/*  71 */         Block var6 = this.cache.get(var5);
/*  72 */         long var7 = var1 - var6.lastAccessTime;
/*     */         
/*  74 */         if (var7 > 30000L || var7 < 0L) {
/*     */           
/*  76 */           this.cache.remove(var5--);
/*  77 */           long var9 = var6.xPosition & 0xFFFFFFFFL | (var6.zPosition & 0xFFFFFFFFL) << 32L;
/*  78 */           this.cacheMap.remove(var9);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public BiomeGenBase[] getCachedBiomes(int p_76839_1_, int p_76839_2_) {
/*  89 */     return (getBiomeCacheBlock(p_76839_1_, p_76839_2_)).biomes;
/*     */   }
/*     */   
/*     */   public class Block
/*     */   {
/*  94 */     public float[] rainfallValues = new float[256];
/*  95 */     public BiomeGenBase[] biomes = new BiomeGenBase[256];
/*     */     
/*     */     public int xPosition;
/*     */     public int zPosition;
/*     */     public long lastAccessTime;
/*     */     private static final String __OBFID = "CL_00000163";
/*     */     
/*     */     public Block(int p_i1972_2_, int p_i1972_3_) {
/* 103 */       this.xPosition = p_i1972_2_;
/* 104 */       this.zPosition = p_i1972_3_;
/* 105 */       BiomeCache.this.chunkManager.getRainfall(this.rainfallValues, p_i1972_2_ << 4, p_i1972_3_ << 4, 16, 16);
/* 106 */       BiomeCache.this.chunkManager.getBiomeGenAt(this.biomes, p_i1972_2_ << 4, p_i1972_3_ << 4, 16, 16, false);
/*     */     }
/*     */ 
/*     */     
/*     */     public BiomeGenBase getBiomeGenAt(int p_76885_1_, int p_76885_2_) {
/* 111 */       return this.biomes[p_76885_1_ & 0xF | (p_76885_2_ & 0xF) << 4];
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\world\biome\BiomeCache.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */