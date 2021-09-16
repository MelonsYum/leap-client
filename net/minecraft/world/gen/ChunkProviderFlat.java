/*     */ package net.minecraft.world.gen;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.entity.EnumCreatureType;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.IProgressUpdate;
/*     */ import net.minecraft.world.ChunkCoordIntPair;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.biome.BiomeGenBase;
/*     */ import net.minecraft.world.chunk.Chunk;
/*     */ import net.minecraft.world.chunk.ChunkPrimer;
/*     */ import net.minecraft.world.chunk.IChunkProvider;
/*     */ import net.minecraft.world.gen.feature.WorldGenDungeons;
/*     */ import net.minecraft.world.gen.feature.WorldGenLakes;
/*     */ import net.minecraft.world.gen.structure.MapGenMineshaft;
/*     */ import net.minecraft.world.gen.structure.MapGenScatteredFeature;
/*     */ import net.minecraft.world.gen.structure.MapGenStronghold;
/*     */ import net.minecraft.world.gen.structure.MapGenStructure;
/*     */ import net.minecraft.world.gen.structure.MapGenVillage;
/*     */ import net.minecraft.world.gen.structure.StructureOceanMonument;
/*     */ 
/*     */ public class ChunkProviderFlat implements IChunkProvider {
/*     */   private World worldObj;
/*     */   private Random random;
/*  32 */   private final IBlockState[] cachedBlockIDs = new IBlockState[256];
/*     */   private final FlatGeneratorInfo flatWorldGenInfo;
/*  34 */   private final List structureGenerators = Lists.newArrayList();
/*     */   
/*     */   private final boolean hasDecoration;
/*     */   private final boolean hasDungeons;
/*     */   private WorldGenLakes waterLakeGenerator;
/*     */   private WorldGenLakes lavaLakeGenerator;
/*     */   private static final String __OBFID = "CL_00000391";
/*     */   
/*     */   public ChunkProviderFlat(World worldIn, long p_i2004_2_, boolean p_i2004_4_, String p_i2004_5_) {
/*  43 */     this.worldObj = worldIn;
/*  44 */     this.random = new Random(p_i2004_2_);
/*  45 */     this.flatWorldGenInfo = FlatGeneratorInfo.createFlatGeneratorFromString(p_i2004_5_);
/*     */     
/*  47 */     if (p_i2004_4_) {
/*     */       
/*  49 */       Map var6 = this.flatWorldGenInfo.getWorldFeatures();
/*     */       
/*  51 */       if (var6.containsKey("village")) {
/*     */         
/*  53 */         Map<String, String> var7 = (Map)var6.get("village");
/*     */         
/*  55 */         if (!var7.containsKey("size"))
/*     */         {
/*  57 */           var7.put("size", "1");
/*     */         }
/*     */         
/*  60 */         this.structureGenerators.add(new MapGenVillage(var7));
/*     */       } 
/*     */       
/*  63 */       if (var6.containsKey("biome_1"))
/*     */       {
/*  65 */         this.structureGenerators.add(new MapGenScatteredFeature((Map)var6.get("biome_1")));
/*     */       }
/*     */       
/*  68 */       if (var6.containsKey("mineshaft"))
/*     */       {
/*  70 */         this.structureGenerators.add(new MapGenMineshaft((Map)var6.get("mineshaft")));
/*     */       }
/*     */       
/*  73 */       if (var6.containsKey("stronghold"))
/*     */       {
/*  75 */         this.structureGenerators.add(new MapGenStronghold((Map)var6.get("stronghold")));
/*     */       }
/*     */       
/*  78 */       if (var6.containsKey("oceanmonument"))
/*     */       {
/*  80 */         this.structureGenerators.add(new StructureOceanMonument((Map)var6.get("oceanmonument")));
/*     */       }
/*     */     } 
/*     */     
/*  84 */     if (this.flatWorldGenInfo.getWorldFeatures().containsKey("lake"))
/*     */     {
/*  86 */       this.waterLakeGenerator = new WorldGenLakes((Block)Blocks.water);
/*     */     }
/*     */     
/*  89 */     if (this.flatWorldGenInfo.getWorldFeatures().containsKey("lava_lake"))
/*     */     {
/*  91 */       this.lavaLakeGenerator = new WorldGenLakes((Block)Blocks.lava);
/*     */     }
/*     */     
/*  94 */     this.hasDungeons = this.flatWorldGenInfo.getWorldFeatures().containsKey("dungeon");
/*  95 */     boolean var11 = true;
/*  96 */     Iterator<FlatLayerInfo> var12 = this.flatWorldGenInfo.getFlatLayers().iterator();
/*     */     
/*  98 */     while (var12.hasNext()) {
/*     */       
/* 100 */       FlatLayerInfo var8 = var12.next();
/*     */       
/* 102 */       for (int var9 = var8.getMinY(); var9 < var8.getMinY() + var8.getLayerCount(); var9++) {
/*     */         
/* 104 */         IBlockState var10 = var8.func_175900_c();
/*     */         
/* 106 */         if (var10.getBlock() != Blocks.air) {
/*     */           
/* 108 */           var11 = false;
/* 109 */           this.cachedBlockIDs[var9] = var10;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 114 */     this.hasDecoration = var11 ? false : this.flatWorldGenInfo.getWorldFeatures().containsKey("decoration");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Chunk provideChunk(int p_73154_1_, int p_73154_2_) {
/* 123 */     ChunkPrimer var3 = new ChunkPrimer();
/*     */ 
/*     */     
/* 126 */     for (int var4 = 0; var4 < this.cachedBlockIDs.length; var4++) {
/*     */       
/* 128 */       IBlockState var5 = this.cachedBlockIDs[var4];
/*     */       
/* 130 */       if (var5 != null)
/*     */       {
/* 132 */         for (int var6 = 0; var6 < 16; var6++) {
/*     */           
/* 134 */           for (int i = 0; i < 16; i++)
/*     */           {
/* 136 */             var3.setBlockState(var6, var4, i, var5);
/*     */           }
/*     */         } 
/*     */       }
/*     */     } 
/*     */     
/* 142 */     Iterator<MapGenBase> var8 = this.structureGenerators.iterator();
/*     */     
/* 144 */     while (var8.hasNext()) {
/*     */       
/* 146 */       MapGenBase var10 = var8.next();
/* 147 */       var10.func_175792_a(this, this.worldObj, p_73154_1_, p_73154_2_, var3);
/*     */     } 
/*     */     
/* 150 */     Chunk var9 = new Chunk(this.worldObj, var3, p_73154_1_, p_73154_2_);
/* 151 */     BiomeGenBase[] var11 = this.worldObj.getWorldChunkManager().loadBlockGeneratorData(null, p_73154_1_ * 16, p_73154_2_ * 16, 16, 16);
/* 152 */     byte[] var12 = var9.getBiomeArray();
/*     */     
/* 154 */     for (int var7 = 0; var7 < var12.length; var7++)
/*     */     {
/* 156 */       var12[var7] = (byte)(var11[var7]).biomeID;
/*     */     }
/*     */     
/* 159 */     var9.generateSkylightMap();
/* 160 */     return var9;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean chunkExists(int p_73149_1_, int p_73149_2_) {
/* 168 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void populate(IChunkProvider p_73153_1_, int p_73153_2_, int p_73153_3_) {
/* 176 */     int var4 = p_73153_2_ * 16;
/* 177 */     int var5 = p_73153_3_ * 16;
/* 178 */     BlockPos var6 = new BlockPos(var4, 0, var5);
/* 179 */     BiomeGenBase var7 = this.worldObj.getBiomeGenForCoords(new BlockPos(var4 + 16, 0, var5 + 16));
/* 180 */     boolean var8 = false;
/* 181 */     this.random.setSeed(this.worldObj.getSeed());
/* 182 */     long var9 = this.random.nextLong() / 2L * 2L + 1L;
/* 183 */     long var11 = this.random.nextLong() / 2L * 2L + 1L;
/* 184 */     this.random.setSeed(p_73153_2_ * var9 + p_73153_3_ * var11 ^ this.worldObj.getSeed());
/* 185 */     ChunkCoordIntPair var13 = new ChunkCoordIntPair(p_73153_2_, p_73153_3_);
/* 186 */     Iterator<MapGenStructure> var14 = this.structureGenerators.iterator();
/*     */     
/* 188 */     while (var14.hasNext()) {
/*     */       
/* 190 */       MapGenStructure var15 = var14.next();
/* 191 */       boolean var16 = var15.func_175794_a(this.worldObj, this.random, var13);
/*     */       
/* 193 */       if (var15 instanceof MapGenVillage)
/*     */       {
/* 195 */         var8 |= var16;
/*     */       }
/*     */     } 
/*     */     
/* 199 */     if (this.waterLakeGenerator != null && !var8 && this.random.nextInt(4) == 0)
/*     */     {
/* 201 */       this.waterLakeGenerator.generate(this.worldObj, this.random, var6.add(this.random.nextInt(16) + 8, this.random.nextInt(256), this.random.nextInt(16) + 8));
/*     */     }
/*     */     
/* 204 */     if (this.lavaLakeGenerator != null && !var8 && this.random.nextInt(8) == 0) {
/*     */       
/* 206 */       BlockPos var17 = var6.add(this.random.nextInt(16) + 8, this.random.nextInt(this.random.nextInt(248) + 8), this.random.nextInt(16) + 8);
/*     */       
/* 208 */       if (var17.getY() < 63 || this.random.nextInt(10) == 0)
/*     */       {
/* 210 */         this.lavaLakeGenerator.generate(this.worldObj, this.random, var17);
/*     */       }
/*     */     } 
/*     */     
/* 214 */     if (this.hasDungeons)
/*     */     {
/* 216 */       for (int var18 = 0; var18 < 8; var18++)
/*     */       {
/* 218 */         (new WorldGenDungeons()).generate(this.worldObj, this.random, var6.add(this.random.nextInt(16) + 8, this.random.nextInt(256), this.random.nextInt(16) + 8));
/*     */       }
/*     */     }
/*     */     
/* 222 */     if (this.hasDecoration)
/*     */     {
/* 224 */       var7.func_180624_a(this.worldObj, this.random, new BlockPos(var4, 0, var5));
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_177460_a(IChunkProvider p_177460_1_, Chunk p_177460_2_, int p_177460_3_, int p_177460_4_) {
/* 230 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean saveChunks(boolean p_73151_1_, IProgressUpdate p_73151_2_) {
/* 239 */     return true;
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
/* 253 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canSave() {
/* 261 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String makeString() {
/* 269 */     return "FlatLevelSource";
/*     */   }
/*     */ 
/*     */   
/*     */   public List func_177458_a(EnumCreatureType p_177458_1_, BlockPos p_177458_2_) {
/* 274 */     BiomeGenBase var3 = this.worldObj.getBiomeGenForCoords(p_177458_2_);
/* 275 */     return var3.getSpawnableList(p_177458_1_);
/*     */   }
/*     */ 
/*     */   
/*     */   public BlockPos func_180513_a(World worldIn, String p_180513_2_, BlockPos p_180513_3_) {
/* 280 */     if ("Stronghold".equals(p_180513_2_)) {
/*     */       
/* 282 */       Iterator<MapGenStructure> var4 = this.structureGenerators.iterator();
/*     */       
/* 284 */       while (var4.hasNext()) {
/*     */         
/* 286 */         MapGenStructure var5 = var4.next();
/*     */         
/* 288 */         if (var5 instanceof MapGenStronghold)
/*     */         {
/* 290 */           return var5.func_180706_b(worldIn, p_180513_3_);
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 295 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getLoadedChunkCount() {
/* 300 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_180514_a(Chunk p_180514_1_, int p_180514_2_, int p_180514_3_) {
/* 305 */     Iterator<MapGenStructure> var4 = this.structureGenerators.iterator();
/*     */     
/* 307 */     while (var4.hasNext()) {
/*     */       
/* 309 */       MapGenStructure var5 = var4.next();
/* 310 */       var5.func_175792_a(this, this.worldObj, p_180514_2_, p_180514_3_, null);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public Chunk func_177459_a(BlockPos p_177459_1_) {
/* 316 */     return provideChunk(p_177459_1_.getX() >> 4, p_177459_1_.getZ() >> 4);
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\world\gen\ChunkProviderFlat.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */