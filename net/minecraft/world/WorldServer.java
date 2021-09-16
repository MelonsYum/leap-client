/*      */ package net.minecraft.world;
/*      */ 
/*      */ import com.google.common.base.Predicate;
/*      */ import com.google.common.collect.Lists;
/*      */ import com.google.common.collect.Maps;
/*      */ import com.google.common.collect.Sets;
/*      */ import com.google.common.util.concurrent.ListenableFuture;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Iterator;
/*      */ import java.util.List;
/*      */ import java.util.Map;
/*      */ import java.util.Random;
/*      */ import java.util.Set;
/*      */ import java.util.TreeSet;
/*      */ import java.util.UUID;
/*      */ import net.minecraft.block.Block;
/*      */ import net.minecraft.block.BlockEventData;
/*      */ import net.minecraft.block.material.Material;
/*      */ import net.minecraft.block.state.IBlockState;
/*      */ import net.minecraft.crash.CrashReport;
/*      */ import net.minecraft.crash.CrashReportCategory;
/*      */ import net.minecraft.entity.Entity;
/*      */ import net.minecraft.entity.EntityLivingBase;
/*      */ import net.minecraft.entity.EntityTracker;
/*      */ import net.minecraft.entity.EnumCreatureType;
/*      */ import net.minecraft.entity.effect.EntityLightningBolt;
/*      */ import net.minecraft.entity.player.EntityPlayer;
/*      */ import net.minecraft.entity.player.EntityPlayerMP;
/*      */ import net.minecraft.init.Blocks;
/*      */ import net.minecraft.init.Items;
/*      */ import net.minecraft.item.Item;
/*      */ import net.minecraft.network.Packet;
/*      */ import net.minecraft.network.play.server.S19PacketEntityStatus;
/*      */ import net.minecraft.network.play.server.S24PacketBlockAction;
/*      */ import net.minecraft.network.play.server.S27PacketExplosion;
/*      */ import net.minecraft.network.play.server.S2APacketParticles;
/*      */ import net.minecraft.network.play.server.S2BPacketChangeGameState;
/*      */ import net.minecraft.network.play.server.S2CPacketSpawnGlobalEntity;
/*      */ import net.minecraft.profiler.Profiler;
/*      */ import net.minecraft.scoreboard.Scoreboard;
/*      */ import net.minecraft.scoreboard.ScoreboardSaveData;
/*      */ import net.minecraft.scoreboard.ServerScoreboard;
/*      */ import net.minecraft.server.MinecraftServer;
/*      */ import net.minecraft.server.management.PlayerManager;
/*      */ import net.minecraft.tileentity.TileEntity;
/*      */ import net.minecraft.util.AxisAlignedBB;
/*      */ import net.minecraft.util.BlockPos;
/*      */ import net.minecraft.util.EnumParticleTypes;
/*      */ import net.minecraft.util.IProgressUpdate;
/*      */ import net.minecraft.util.IThreadListener;
/*      */ import net.minecraft.util.ReportedException;
/*      */ import net.minecraft.util.Vec3;
/*      */ import net.minecraft.util.WeightedRandom;
/*      */ import net.minecraft.util.WeightedRandomChestContent;
/*      */ import net.minecraft.village.VillageCollection;
/*      */ import net.minecraft.village.VillageSiege;
/*      */ import net.minecraft.world.biome.BiomeGenBase;
/*      */ import net.minecraft.world.biome.WorldChunkManager;
/*      */ import net.minecraft.world.chunk.Chunk;
/*      */ import net.minecraft.world.chunk.IChunkProvider;
/*      */ import net.minecraft.world.chunk.storage.ExtendedBlockStorage;
/*      */ import net.minecraft.world.chunk.storage.IChunkLoader;
/*      */ import net.minecraft.world.gen.ChunkProviderServer;
/*      */ import net.minecraft.world.gen.feature.WorldGeneratorBonusChest;
/*      */ import net.minecraft.world.gen.structure.StructureBoundingBox;
/*      */ import net.minecraft.world.storage.ISaveHandler;
/*      */ import net.minecraft.world.storage.MapStorage;
/*      */ import net.minecraft.world.storage.WorldInfo;
/*      */ import org.apache.logging.log4j.LogManager;
/*      */ import org.apache.logging.log4j.Logger;
/*      */ 
/*      */ public class WorldServer
/*      */   extends World
/*      */   implements IThreadListener {
/*   75 */   private static final Logger logger = LogManager.getLogger();
/*      */   private final MinecraftServer mcServer;
/*      */   private final EntityTracker theEntityTracker;
/*      */   private final PlayerManager thePlayerManager;
/*   79 */   private final Set pendingTickListEntriesHashSet = Sets.newHashSet();
/*      */ 
/*      */   
/*   82 */   private final TreeSet pendingTickListEntriesTreeSet = new TreeSet();
/*   83 */   private final Map entitiesByUuid = Maps.newHashMap();
/*      */ 
/*      */   
/*      */   public ChunkProviderServer theChunkProviderServer;
/*      */ 
/*      */   
/*      */   public boolean disableLevelSaving;
/*      */   
/*      */   private boolean allPlayersSleeping;
/*      */   
/*      */   private int updateEntityTick;
/*      */   
/*      */   private final Teleporter worldTeleporter;
/*      */   
/*   97 */   private final SpawnerAnimals field_175742_R = new SpawnerAnimals();
/*   98 */   protected final VillageSiege villageSiege = new VillageSiege(this);
/*   99 */   private ServerBlockEventList[] field_147490_S = new ServerBlockEventList[] { new ServerBlockEventList(null), new ServerBlockEventList(null) };
/*      */   private int blockEventCacheIndex;
/*  101 */   private static final List bonusChestContent = Lists.newArrayList((Object[])new WeightedRandomChestContent[] { new WeightedRandomChestContent(Items.stick, 0, 1, 3, 10), new WeightedRandomChestContent(Item.getItemFromBlock(Blocks.planks), 0, 1, 3, 10), new WeightedRandomChestContent(Item.getItemFromBlock(Blocks.log), 0, 1, 3, 10), new WeightedRandomChestContent(Items.stone_axe, 0, 1, 1, 3), new WeightedRandomChestContent(Items.wooden_axe, 0, 1, 1, 5), new WeightedRandomChestContent(Items.stone_pickaxe, 0, 1, 1, 3), new WeightedRandomChestContent(Items.wooden_pickaxe, 0, 1, 1, 5), new WeightedRandomChestContent(Items.apple, 0, 2, 3, 5), new WeightedRandomChestContent(Items.bread, 0, 2, 3, 3), new WeightedRandomChestContent(Item.getItemFromBlock(Blocks.log2), 0, 1, 3, 10) });
/*  102 */   private List pendingTickListEntriesThisTick = Lists.newArrayList();
/*      */   
/*      */   private static final String __OBFID = "CL_00001437";
/*      */   
/*      */   public WorldServer(MinecraftServer server, ISaveHandler saveHandlerIn, WorldInfo info, int dimensionId, Profiler profilerIn) {
/*  107 */     super(saveHandlerIn, info, WorldProvider.getProviderForDimension(dimensionId), profilerIn, false);
/*  108 */     this.mcServer = server;
/*  109 */     this.theEntityTracker = new EntityTracker(this);
/*  110 */     this.thePlayerManager = new PlayerManager(this);
/*  111 */     this.provider.registerWorld(this);
/*  112 */     this.chunkProvider = createChunkProvider();
/*  113 */     this.worldTeleporter = new Teleporter(this);
/*  114 */     calculateInitialSkylight();
/*  115 */     calculateInitialWeather();
/*  116 */     getWorldBorder().setSize(server.getMaxWorldSize());
/*      */   }
/*      */ 
/*      */   
/*      */   public World init() {
/*  121 */     this.mapStorage = new MapStorage(this.saveHandler);
/*  122 */     String var1 = VillageCollection.func_176062_a(this.provider);
/*  123 */     VillageCollection var2 = (VillageCollection)this.mapStorage.loadData(VillageCollection.class, var1);
/*      */     
/*  125 */     if (var2 == null) {
/*      */       
/*  127 */       this.villageCollectionObj = new VillageCollection(this);
/*  128 */       this.mapStorage.setData(var1, (WorldSavedData)this.villageCollectionObj);
/*      */     }
/*      */     else {
/*      */       
/*  132 */       this.villageCollectionObj = var2;
/*  133 */       this.villageCollectionObj.func_82566_a(this);
/*      */     } 
/*      */     
/*  136 */     this.worldScoreboard = (Scoreboard)new ServerScoreboard(this.mcServer);
/*  137 */     ScoreboardSaveData var3 = (ScoreboardSaveData)this.mapStorage.loadData(ScoreboardSaveData.class, "scoreboard");
/*      */     
/*  139 */     if (var3 == null) {
/*      */       
/*  141 */       var3 = new ScoreboardSaveData();
/*  142 */       this.mapStorage.setData("scoreboard", (WorldSavedData)var3);
/*      */     } 
/*      */     
/*  145 */     var3.func_96499_a(this.worldScoreboard);
/*  146 */     ((ServerScoreboard)this.worldScoreboard).func_96547_a(var3);
/*  147 */     getWorldBorder().setCenter(this.worldInfo.func_176120_C(), this.worldInfo.func_176126_D());
/*  148 */     getWorldBorder().func_177744_c(this.worldInfo.func_176140_I());
/*  149 */     getWorldBorder().setDamageBuffer(this.worldInfo.func_176138_H());
/*  150 */     getWorldBorder().setWarningDistance(this.worldInfo.func_176131_J());
/*  151 */     getWorldBorder().setWarningTime(this.worldInfo.func_176139_K());
/*      */     
/*  153 */     if (this.worldInfo.func_176134_F() > 0L) {
/*      */       
/*  155 */       getWorldBorder().setTransition(this.worldInfo.func_176137_E(), this.worldInfo.func_176132_G(), this.worldInfo.func_176134_F());
/*      */     }
/*      */     else {
/*      */       
/*  159 */       getWorldBorder().setTransition(this.worldInfo.func_176137_E());
/*      */     } 
/*      */     
/*  162 */     return this;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void tick() {
/*  170 */     super.tick();
/*      */     
/*  172 */     if (getWorldInfo().isHardcoreModeEnabled() && getDifficulty() != EnumDifficulty.HARD)
/*      */     {
/*  174 */       getWorldInfo().setDifficulty(EnumDifficulty.HARD);
/*      */     }
/*      */     
/*  177 */     this.provider.getWorldChunkManager().cleanupCache();
/*      */     
/*  179 */     if (areAllPlayersAsleep()) {
/*      */       
/*  181 */       if (getGameRules().getGameRuleBooleanValue("doDaylightCycle")) {
/*      */         
/*  183 */         long var1 = this.worldInfo.getWorldTime() + 24000L;
/*  184 */         this.worldInfo.setWorldTime(var1 - var1 % 24000L);
/*      */       } 
/*      */       
/*  187 */       wakeAllPlayers();
/*      */     } 
/*      */     
/*  190 */     this.theProfiler.startSection("mobSpawner");
/*      */     
/*  192 */     if (getGameRules().getGameRuleBooleanValue("doMobSpawning") && this.worldInfo.getTerrainType() != WorldType.DEBUG_WORLD)
/*      */     {
/*  194 */       this.field_175742_R.findChunksForSpawning(this, this.spawnHostileMobs, this.spawnPeacefulMobs, (this.worldInfo.getWorldTotalTime() % 400L == 0L));
/*      */     }
/*      */     
/*  197 */     this.theProfiler.endStartSection("chunkSource");
/*  198 */     this.chunkProvider.unloadQueuedChunks();
/*  199 */     int var3 = calculateSkylightSubtracted(1.0F);
/*      */     
/*  201 */     if (var3 != getSkylightSubtracted())
/*      */     {
/*  203 */       setSkylightSubtracted(var3);
/*      */     }
/*      */     
/*  206 */     this.worldInfo.incrementTotalWorldTime(this.worldInfo.getWorldTotalTime() + 1L);
/*      */     
/*  208 */     if (getGameRules().getGameRuleBooleanValue("doDaylightCycle"))
/*      */     {
/*  210 */       this.worldInfo.setWorldTime(this.worldInfo.getWorldTime() + 1L);
/*      */     }
/*      */     
/*  213 */     this.theProfiler.endStartSection("tickPending");
/*  214 */     tickUpdates(false);
/*  215 */     this.theProfiler.endStartSection("tickBlocks");
/*  216 */     func_147456_g();
/*  217 */     this.theProfiler.endStartSection("chunkMap");
/*  218 */     this.thePlayerManager.updatePlayerInstances();
/*  219 */     this.theProfiler.endStartSection("village");
/*  220 */     this.villageCollectionObj.tick();
/*  221 */     this.villageSiege.tick();
/*  222 */     this.theProfiler.endStartSection("portalForcer");
/*  223 */     this.worldTeleporter.removeStalePortalLocations(getTotalWorldTime());
/*  224 */     this.theProfiler.endSection();
/*  225 */     func_147488_Z();
/*      */   }
/*      */ 
/*      */   
/*      */   public BiomeGenBase.SpawnListEntry func_175734_a(EnumCreatureType p_175734_1_, BlockPos p_175734_2_) {
/*  230 */     List var3 = getChunkProvider().func_177458_a(p_175734_1_, p_175734_2_);
/*  231 */     return (var3 != null && !var3.isEmpty()) ? (BiomeGenBase.SpawnListEntry)WeightedRandom.getRandomItem(this.rand, var3) : null;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean func_175732_a(EnumCreatureType p_175732_1_, BiomeGenBase.SpawnListEntry p_175732_2_, BlockPos p_175732_3_) {
/*  236 */     List var4 = getChunkProvider().func_177458_a(p_175732_1_, p_175732_3_);
/*  237 */     return (var4 != null && !var4.isEmpty()) ? var4.contains(p_175732_2_) : false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void updateAllPlayersSleepingFlag() {
/*  245 */     this.allPlayersSleeping = false;
/*      */     
/*  247 */     if (!this.playerEntities.isEmpty()) {
/*      */       
/*  249 */       int var1 = 0;
/*  250 */       int var2 = 0;
/*  251 */       Iterator<EntityPlayer> var3 = this.playerEntities.iterator();
/*      */       
/*  253 */       while (var3.hasNext()) {
/*      */         
/*  255 */         EntityPlayer var4 = var3.next();
/*      */         
/*  257 */         if (var4.func_175149_v()) {
/*      */           
/*  259 */           var1++; continue;
/*      */         } 
/*  261 */         if (var4.isPlayerSleeping())
/*      */         {
/*  263 */           var2++;
/*      */         }
/*      */       } 
/*      */       
/*  267 */       this.allPlayersSleeping = (var2 > 0 && var2 >= this.playerEntities.size() - var1);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   protected void wakeAllPlayers() {
/*  273 */     this.allPlayersSleeping = false;
/*  274 */     Iterator<EntityPlayer> var1 = this.playerEntities.iterator();
/*      */     
/*  276 */     while (var1.hasNext()) {
/*      */       
/*  278 */       EntityPlayer var2 = var1.next();
/*      */       
/*  280 */       if (var2.isPlayerSleeping())
/*      */       {
/*  282 */         var2.wakeUpPlayer(false, false, true);
/*      */       }
/*      */     } 
/*      */     
/*  286 */     resetRainAndThunder();
/*      */   }
/*      */ 
/*      */   
/*      */   private void resetRainAndThunder() {
/*  291 */     this.worldInfo.setRainTime(0);
/*  292 */     this.worldInfo.setRaining(false);
/*  293 */     this.worldInfo.setThunderTime(0);
/*  294 */     this.worldInfo.setThundering(false);
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean areAllPlayersAsleep() {
/*  299 */     if (this.allPlayersSleeping && !this.isRemote) {
/*      */       EntityPlayer var2;
/*  301 */       Iterator<EntityPlayer> var1 = this.playerEntities.iterator();
/*      */ 
/*      */ 
/*      */       
/*      */       do {
/*  306 */         if (!var1.hasNext())
/*      */         {
/*  308 */           return true;
/*      */         }
/*      */         
/*  311 */         var2 = var1.next();
/*      */       }
/*  313 */       while (!var2.func_175149_v() && var2.isPlayerFullyAsleep());
/*      */       
/*  315 */       return false;
/*      */     } 
/*      */ 
/*      */     
/*  319 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setInitialSpawnLocation() {
/*  328 */     if (this.worldInfo.getSpawnY() <= 0)
/*      */     {
/*  330 */       this.worldInfo.setSpawnY(64);
/*      */     }
/*      */     
/*  333 */     int var1 = this.worldInfo.getSpawnX();
/*  334 */     int var2 = this.worldInfo.getSpawnZ();
/*  335 */     int var3 = 0;
/*      */     
/*  337 */     while (getGroundAboveSeaLevel(new BlockPos(var1, 0, var2)).getMaterial() == Material.air) {
/*      */       
/*  339 */       var1 += this.rand.nextInt(8) - this.rand.nextInt(8);
/*  340 */       var2 += this.rand.nextInt(8) - this.rand.nextInt(8);
/*  341 */       var3++;
/*      */       
/*  343 */       if (var3 == 10000) {
/*      */         break;
/*      */       }
/*      */     } 
/*      */ 
/*      */     
/*  349 */     this.worldInfo.setSpawnX(var1);
/*  350 */     this.worldInfo.setSpawnZ(var2);
/*      */   }
/*      */ 
/*      */   
/*      */   protected void func_147456_g() {
/*  355 */     super.func_147456_g();
/*      */     
/*  357 */     if (this.worldInfo.getTerrainType() == WorldType.DEBUG_WORLD) {
/*      */       
/*  359 */       Iterator<ChunkCoordIntPair> var21 = this.activeChunkSet.iterator();
/*      */       
/*  361 */       while (var21.hasNext())
/*      */       {
/*  363 */         ChunkCoordIntPair var22 = var21.next();
/*  364 */         getChunkFromChunkCoords(var22.chunkXPos, var22.chunkZPos).func_150804_b(false);
/*      */       }
/*      */     
/*      */     } else {
/*      */       
/*  369 */       int var1 = 0;
/*  370 */       int var2 = 0;
/*      */       
/*  372 */       for (Iterator<ChunkCoordIntPair> var3 = this.activeChunkSet.iterator(); var3.hasNext(); this.theProfiler.endSection()) {
/*      */         
/*  374 */         ChunkCoordIntPair var4 = var3.next();
/*  375 */         int var5 = var4.chunkXPos * 16;
/*  376 */         int var6 = var4.chunkZPos * 16;
/*  377 */         this.theProfiler.startSection("getChunk");
/*  378 */         Chunk var7 = getChunkFromChunkCoords(var4.chunkXPos, var4.chunkZPos);
/*  379 */         func_147467_a(var5, var6, var7);
/*  380 */         this.theProfiler.endStartSection("tickChunk");
/*  381 */         var7.func_150804_b(false);
/*  382 */         this.theProfiler.endStartSection("thunder");
/*      */ 
/*      */ 
/*      */         
/*  386 */         if (this.rand.nextInt(100000) == 0 && isRaining() && isThundering()) {
/*      */           
/*  388 */           this.updateLCG = this.updateLCG * 3 + 1013904223;
/*  389 */           int i = this.updateLCG >> 2;
/*  390 */           BlockPos var9 = func_175736_a(new BlockPos(var5 + (i & 0xF), 0, var6 + (i >> 8 & 0xF)));
/*      */           
/*  392 */           if (func_175727_C(var9))
/*      */           {
/*  394 */             addWeatherEffect((Entity)new EntityLightningBolt(this, var9.getX(), var9.getY(), var9.getZ()));
/*      */           }
/*      */         } 
/*      */         
/*  398 */         this.theProfiler.endStartSection("iceandsnow");
/*      */         
/*  400 */         if (this.rand.nextInt(16) == 0) {
/*      */           
/*  402 */           this.updateLCG = this.updateLCG * 3 + 1013904223;
/*  403 */           int i = this.updateLCG >> 2;
/*  404 */           BlockPos var9 = func_175725_q(new BlockPos(var5 + (i & 0xF), 0, var6 + (i >> 8 & 0xF)));
/*  405 */           BlockPos var10 = var9.offsetDown();
/*      */           
/*  407 */           if (func_175662_w(var10))
/*      */           {
/*  409 */             setBlockState(var10, Blocks.ice.getDefaultState());
/*      */           }
/*      */           
/*  412 */           if (isRaining() && func_175708_f(var9, true))
/*      */           {
/*  414 */             setBlockState(var9, Blocks.snow_layer.getDefaultState());
/*      */           }
/*      */           
/*  417 */           if (isRaining() && getBiomeGenForCoords(var10).canSpawnLightningBolt())
/*      */           {
/*  419 */             getBlockState(var10).getBlock().fillWithRain(this, var10);
/*      */           }
/*      */         } 
/*      */         
/*  423 */         this.theProfiler.endStartSection("tickBlocks");
/*  424 */         int var8 = getGameRules().getInt("randomTickSpeed");
/*      */         
/*  426 */         if (var8 > 0) {
/*      */           
/*  428 */           ExtendedBlockStorage[] var23 = var7.getBlockStorageArray();
/*  429 */           int var24 = var23.length;
/*      */           
/*  431 */           for (int var11 = 0; var11 < var24; var11++) {
/*      */             
/*  433 */             ExtendedBlockStorage var12 = var23[var11];
/*      */             
/*  435 */             if (var12 != null && var12.getNeedsRandomTick())
/*      */             {
/*  437 */               for (int var13 = 0; var13 < var8; var13++) {
/*      */                 
/*  439 */                 this.updateLCG = this.updateLCG * 3 + 1013904223;
/*  440 */                 int var14 = this.updateLCG >> 2;
/*  441 */                 int var15 = var14 & 0xF;
/*  442 */                 int var16 = var14 >> 8 & 0xF;
/*  443 */                 int var17 = var14 >> 16 & 0xF;
/*  444 */                 var2++;
/*  445 */                 BlockPos var18 = new BlockPos(var15 + var5, var17 + var12.getYLocation(), var16 + var6);
/*  446 */                 IBlockState var19 = var12.get(var15, var17, var16);
/*  447 */                 Block var20 = var19.getBlock();
/*      */                 
/*  449 */                 if (var20.getTickRandomly()) {
/*      */                   
/*  451 */                   var1++;
/*  452 */                   var20.randomTick(this, var18, var19, this.rand);
/*      */                 } 
/*      */               } 
/*      */             }
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   protected BlockPos func_175736_a(BlockPos p_175736_1_) {
/*  464 */     BlockPos var2 = func_175725_q(p_175736_1_);
/*  465 */     AxisAlignedBB var3 = (new AxisAlignedBB(var2, new BlockPos(var2.getX(), getHeight(), var2.getZ()))).expand(3.0D, 3.0D, 3.0D);
/*  466 */     List<EntityLivingBase> var4 = func_175647_a(EntityLivingBase.class, var3, new Predicate()
/*      */         {
/*      */           private static final String __OBFID = "CL_00001889";
/*      */           
/*      */           public boolean func_180242_a(EntityLivingBase p_180242_1_) {
/*  471 */             return (p_180242_1_ != null && p_180242_1_.isEntityAlive() && WorldServer.this.isAgainstSky(p_180242_1_.getPosition()));
/*      */           }
/*      */           
/*      */           public boolean apply(Object p_apply_1_) {
/*  475 */             return func_180242_a((EntityLivingBase)p_apply_1_);
/*      */           }
/*      */         });
/*  478 */     return !var4.isEmpty() ? ((EntityLivingBase)var4.get(this.rand.nextInt(var4.size()))).getPosition() : var2;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isBlockTickPending(BlockPos pos, Block blockType) {
/*  483 */     NextTickListEntry var3 = new NextTickListEntry(pos, blockType);
/*  484 */     return this.pendingTickListEntriesThisTick.contains(var3);
/*      */   }
/*      */ 
/*      */   
/*      */   public void scheduleUpdate(BlockPos pos, Block blockIn, int delay) {
/*  489 */     func_175654_a(pos, blockIn, delay, 0);
/*      */   }
/*      */ 
/*      */   
/*      */   public void func_175654_a(BlockPos p_175654_1_, Block p_175654_2_, int p_175654_3_, int p_175654_4_) {
/*  494 */     NextTickListEntry var5 = new NextTickListEntry(p_175654_1_, p_175654_2_);
/*  495 */     byte var6 = 0;
/*      */     
/*  497 */     if (this.scheduledUpdatesAreImmediate && p_175654_2_.getMaterial() != Material.air) {
/*      */       
/*  499 */       if (p_175654_2_.requiresUpdates()) {
/*      */         
/*  501 */         var6 = 8;
/*      */         
/*  503 */         if (isAreaLoaded(var5.field_180282_a.add(-var6, -var6, -var6), var5.field_180282_a.add(var6, var6, var6))) {
/*      */           
/*  505 */           IBlockState var7 = getBlockState(var5.field_180282_a);
/*      */           
/*  507 */           if (var7.getBlock().getMaterial() != Material.air && var7.getBlock() == var5.func_151351_a())
/*      */           {
/*  509 */             var7.getBlock().updateTick(this, var5.field_180282_a, var7, this.rand);
/*      */           }
/*      */         } 
/*      */         
/*      */         return;
/*      */       } 
/*      */       
/*  516 */       p_175654_3_ = 1;
/*      */     } 
/*      */     
/*  519 */     if (isAreaLoaded(p_175654_1_.add(-var6, -var6, -var6), p_175654_1_.add(var6, var6, var6))) {
/*      */       
/*  521 */       if (p_175654_2_.getMaterial() != Material.air) {
/*      */         
/*  523 */         var5.setScheduledTime(p_175654_3_ + this.worldInfo.getWorldTotalTime());
/*  524 */         var5.setPriority(p_175654_4_);
/*      */       } 
/*      */       
/*  527 */       if (!this.pendingTickListEntriesHashSet.contains(var5)) {
/*      */         
/*  529 */         this.pendingTickListEntriesHashSet.add(var5);
/*  530 */         this.pendingTickListEntriesTreeSet.add(var5);
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public void func_180497_b(BlockPos p_180497_1_, Block p_180497_2_, int p_180497_3_, int p_180497_4_) {
/*  537 */     NextTickListEntry var5 = new NextTickListEntry(p_180497_1_, p_180497_2_);
/*  538 */     var5.setPriority(p_180497_4_);
/*      */     
/*  540 */     if (p_180497_2_.getMaterial() != Material.air)
/*      */     {
/*  542 */       var5.setScheduledTime(p_180497_3_ + this.worldInfo.getWorldTotalTime());
/*      */     }
/*      */     
/*  545 */     if (!this.pendingTickListEntriesHashSet.contains(var5)) {
/*      */       
/*  547 */       this.pendingTickListEntriesHashSet.add(var5);
/*  548 */       this.pendingTickListEntriesTreeSet.add(var5);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void updateEntities() {
/*  557 */     if (this.playerEntities.isEmpty()) {
/*      */       
/*  559 */       if (this.updateEntityTick++ >= 1200)
/*      */       {
/*      */         return;
/*      */       }
/*      */     }
/*      */     else {
/*      */       
/*  566 */       resetUpdateEntityTick();
/*      */     } 
/*      */     
/*  569 */     super.updateEntities();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void resetUpdateEntityTick() {
/*  577 */     this.updateEntityTick = 0;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean tickUpdates(boolean p_72955_1_) {
/*  585 */     if (this.worldInfo.getTerrainType() == WorldType.DEBUG_WORLD)
/*      */     {
/*  587 */       return false;
/*      */     }
/*      */ 
/*      */     
/*  591 */     int var2 = this.pendingTickListEntriesTreeSet.size();
/*      */     
/*  593 */     if (var2 != this.pendingTickListEntriesHashSet.size())
/*      */     {
/*  595 */       throw new IllegalStateException("TickNextTick list out of synch");
/*      */     }
/*      */ 
/*      */     
/*  599 */     if (var2 > 1000)
/*      */     {
/*  601 */       var2 = 1000;
/*      */     }
/*      */     
/*  604 */     this.theProfiler.startSection("cleaning");
/*      */ 
/*      */     
/*  607 */     for (int var3 = 0; var3 < var2; var3++) {
/*      */       
/*  609 */       NextTickListEntry var4 = this.pendingTickListEntriesTreeSet.first();
/*      */       
/*  611 */       if (!p_72955_1_ && var4.scheduledTime > this.worldInfo.getWorldTotalTime()) {
/*      */         break;
/*      */       }
/*      */ 
/*      */       
/*  616 */       this.pendingTickListEntriesTreeSet.remove(var4);
/*  617 */       this.pendingTickListEntriesHashSet.remove(var4);
/*  618 */       this.pendingTickListEntriesThisTick.add(var4);
/*      */     } 
/*      */     
/*  621 */     this.theProfiler.endSection();
/*  622 */     this.theProfiler.startSection("ticking");
/*  623 */     Iterator<NextTickListEntry> var11 = this.pendingTickListEntriesThisTick.iterator();
/*      */     
/*  625 */     while (var11.hasNext()) {
/*      */       
/*  627 */       NextTickListEntry var4 = var11.next();
/*  628 */       var11.remove();
/*  629 */       byte var5 = 0;
/*      */       
/*  631 */       if (isAreaLoaded(var4.field_180282_a.add(-var5, -var5, -var5), var4.field_180282_a.add(var5, var5, var5))) {
/*      */         
/*  633 */         IBlockState var6 = getBlockState(var4.field_180282_a);
/*      */         
/*  635 */         if (var6.getBlock().getMaterial() != Material.air && Block.isEqualTo(var6.getBlock(), var4.func_151351_a())) {
/*      */           
/*      */           try {
/*      */             
/*  639 */             var6.getBlock().updateTick(this, var4.field_180282_a, var6, this.rand);
/*      */           }
/*  641 */           catch (Throwable var10) {
/*      */             
/*  643 */             CrashReport var8 = CrashReport.makeCrashReport(var10, "Exception while ticking a block");
/*  644 */             CrashReportCategory var9 = var8.makeCategory("Block being ticked");
/*  645 */             CrashReportCategory.addBlockInfo(var9, var4.field_180282_a, var6);
/*  646 */             throw new ReportedException(var8);
/*      */           } 
/*      */         }
/*      */         
/*      */         continue;
/*      */       } 
/*  652 */       scheduleUpdate(var4.field_180282_a, var4.func_151351_a(), 0);
/*      */     } 
/*      */ 
/*      */     
/*  656 */     this.theProfiler.endSection();
/*  657 */     this.pendingTickListEntriesThisTick.clear();
/*  658 */     return !this.pendingTickListEntriesTreeSet.isEmpty();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public List getPendingBlockUpdates(Chunk p_72920_1_, boolean p_72920_2_) {
/*  665 */     ChunkCoordIntPair var3 = p_72920_1_.getChunkCoordIntPair();
/*  666 */     int var4 = (var3.chunkXPos << 4) - 2;
/*  667 */     int var5 = var4 + 16 + 2;
/*  668 */     int var6 = (var3.chunkZPos << 4) - 2;
/*  669 */     int var7 = var6 + 16 + 2;
/*  670 */     return func_175712_a(new StructureBoundingBox(var4, 0, var6, var5, 256, var7), p_72920_2_);
/*      */   }
/*      */ 
/*      */   
/*      */   public List func_175712_a(StructureBoundingBox p_175712_1_, boolean p_175712_2_) {
/*  675 */     ArrayList<NextTickListEntry> var3 = null;
/*      */     
/*  677 */     for (int var4 = 0; var4 < 2; var4++) {
/*      */       Iterator<NextTickListEntry> var5;
/*      */ 
/*      */       
/*  681 */       if (var4 == 0) {
/*      */         
/*  683 */         var5 = this.pendingTickListEntriesTreeSet.iterator();
/*      */       }
/*      */       else {
/*      */         
/*  687 */         var5 = this.pendingTickListEntriesThisTick.iterator();
/*      */         
/*  689 */         if (!this.pendingTickListEntriesThisTick.isEmpty())
/*      */         {
/*  691 */           logger.debug("toBeTicked = " + this.pendingTickListEntriesThisTick.size());
/*      */         }
/*      */       } 
/*      */       
/*  695 */       while (var5.hasNext()) {
/*      */         
/*  697 */         NextTickListEntry var6 = var5.next();
/*  698 */         BlockPos var7 = var6.field_180282_a;
/*      */         
/*  700 */         if (var7.getX() >= p_175712_1_.minX && var7.getX() < p_175712_1_.maxX && var7.getZ() >= p_175712_1_.minZ && var7.getZ() < p_175712_1_.maxZ) {
/*      */           
/*  702 */           if (p_175712_2_) {
/*      */             
/*  704 */             this.pendingTickListEntriesHashSet.remove(var6);
/*  705 */             var5.remove();
/*      */           } 
/*      */           
/*  708 */           if (var3 == null)
/*      */           {
/*  710 */             var3 = Lists.newArrayList();
/*      */           }
/*      */           
/*  713 */           var3.add(var6);
/*      */         } 
/*      */       } 
/*      */     } 
/*      */     
/*  718 */     return var3;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void updateEntityWithOptionalForce(Entity p_72866_1_, boolean p_72866_2_) {
/*  727 */     if (!func_175735_ai() && (p_72866_1_ instanceof net.minecraft.entity.passive.EntityAnimal || p_72866_1_ instanceof net.minecraft.entity.passive.EntityWaterMob))
/*      */     {
/*  729 */       p_72866_1_.setDead();
/*      */     }
/*      */     
/*  732 */     if (!func_175738_ah() && p_72866_1_ instanceof net.minecraft.entity.INpc)
/*      */     {
/*  734 */       p_72866_1_.setDead();
/*      */     }
/*      */     
/*  737 */     super.updateEntityWithOptionalForce(p_72866_1_, p_72866_2_);
/*      */   }
/*      */ 
/*      */   
/*      */   private boolean func_175738_ah() {
/*  742 */     return this.mcServer.getCanSpawnNPCs();
/*      */   }
/*      */ 
/*      */   
/*      */   private boolean func_175735_ai() {
/*  747 */     return this.mcServer.getCanSpawnAnimals();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected IChunkProvider createChunkProvider() {
/*  755 */     IChunkLoader var1 = this.saveHandler.getChunkLoader(this.provider);
/*  756 */     this.theChunkProviderServer = new ChunkProviderServer(this, var1, this.provider.createChunkGenerator());
/*  757 */     return (IChunkProvider)this.theChunkProviderServer;
/*      */   }
/*      */ 
/*      */   
/*      */   public List func_147486_a(int p_147486_1_, int p_147486_2_, int p_147486_3_, int p_147486_4_, int p_147486_5_, int p_147486_6_) {
/*  762 */     ArrayList<TileEntity> var7 = Lists.newArrayList();
/*      */     
/*  764 */     for (int var8 = 0; var8 < this.loadedTileEntityList.size(); var8++) {
/*      */       
/*  766 */       TileEntity var9 = this.loadedTileEntityList.get(var8);
/*  767 */       BlockPos var10 = var9.getPos();
/*      */       
/*  769 */       if (var10.getX() >= p_147486_1_ && var10.getY() >= p_147486_2_ && var10.getZ() >= p_147486_3_ && var10.getX() < p_147486_4_ && var10.getY() < p_147486_5_ && var10.getZ() < p_147486_6_)
/*      */       {
/*  771 */         var7.add(var9);
/*      */       }
/*      */     } 
/*      */     
/*  775 */     return var7;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isBlockModifiable(EntityPlayer p_175660_1_, BlockPos p_175660_2_) {
/*  780 */     return (!this.mcServer.isBlockProtected(this, p_175660_2_, p_175660_1_) && getWorldBorder().contains(p_175660_2_));
/*      */   }
/*      */ 
/*      */   
/*      */   public void initialize(WorldSettings settings) {
/*  785 */     if (!this.worldInfo.isInitialized()) {
/*      */ 
/*      */       
/*      */       try {
/*  789 */         createSpawnPosition(settings);
/*      */         
/*  791 */         if (this.worldInfo.getTerrainType() == WorldType.DEBUG_WORLD)
/*      */         {
/*  793 */           setDebugWorldSettings();
/*      */         }
/*      */         
/*  796 */         super.initialize(settings);
/*      */       }
/*  798 */       catch (Throwable var6) {
/*      */         
/*  800 */         CrashReport var3 = CrashReport.makeCrashReport(var6, "Exception initializing level");
/*      */ 
/*      */         
/*      */         try {
/*  804 */           addWorldInfoToCrashReport(var3);
/*      */         }
/*  806 */         catch (Throwable throwable) {}
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  811 */         throw new ReportedException(var3);
/*      */       } 
/*      */       
/*  814 */       this.worldInfo.setServerInitialized(true);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   private void setDebugWorldSettings() {
/*  820 */     this.worldInfo.setMapFeaturesEnabled(false);
/*  821 */     this.worldInfo.setAllowCommands(true);
/*  822 */     this.worldInfo.setRaining(false);
/*  823 */     this.worldInfo.setThundering(false);
/*  824 */     this.worldInfo.func_176142_i(1000000000);
/*  825 */     this.worldInfo.setWorldTime(6000L);
/*  826 */     this.worldInfo.setGameType(WorldSettings.GameType.SPECTATOR);
/*  827 */     this.worldInfo.setHardcore(false);
/*  828 */     this.worldInfo.setDifficulty(EnumDifficulty.PEACEFUL);
/*  829 */     this.worldInfo.setDifficultyLocked(true);
/*  830 */     getGameRules().setOrCreateGameRule("doDaylightCycle", "false");
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void createSpawnPosition(WorldSettings p_73052_1_) {
/*  838 */     if (!this.provider.canRespawnHere()) {
/*      */       
/*  840 */       this.worldInfo.setSpawn(BlockPos.ORIGIN.offsetUp(this.provider.getAverageGroundLevel()));
/*      */     }
/*  842 */     else if (this.worldInfo.getTerrainType() == WorldType.DEBUG_WORLD) {
/*      */       
/*  844 */       this.worldInfo.setSpawn(BlockPos.ORIGIN.offsetUp());
/*      */     }
/*      */     else {
/*      */       
/*  848 */       this.findingSpawnPoint = true;
/*  849 */       WorldChunkManager var2 = this.provider.getWorldChunkManager();
/*  850 */       List var3 = var2.getBiomesToSpawnIn();
/*  851 */       Random var4 = new Random(getSeed());
/*  852 */       BlockPos var5 = var2.findBiomePosition(0, 0, 256, var3, var4);
/*  853 */       int var6 = 0;
/*  854 */       int var7 = this.provider.getAverageGroundLevel();
/*  855 */       int var8 = 0;
/*      */       
/*  857 */       if (var5 != null) {
/*      */         
/*  859 */         var6 = var5.getX();
/*  860 */         var8 = var5.getZ();
/*      */       }
/*      */       else {
/*      */         
/*  864 */         logger.warn("Unable to find spawn biome");
/*      */       } 
/*      */       
/*  867 */       int var9 = 0;
/*      */       
/*  869 */       while (!this.provider.canCoordinateBeSpawn(var6, var8)) {
/*      */         
/*  871 */         var6 += var4.nextInt(64) - var4.nextInt(64);
/*  872 */         var8 += var4.nextInt(64) - var4.nextInt(64);
/*  873 */         var9++;
/*      */         
/*  875 */         if (var9 == 1000) {
/*      */           break;
/*      */         }
/*      */       } 
/*      */ 
/*      */       
/*  881 */       this.worldInfo.setSpawn(new BlockPos(var6, var7, var8));
/*  882 */       this.findingSpawnPoint = false;
/*      */       
/*  884 */       if (p_73052_1_.isBonusChestEnabled())
/*      */       {
/*  886 */         createBonusChest();
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void createBonusChest() {
/*  896 */     WorldGeneratorBonusChest var1 = new WorldGeneratorBonusChest(bonusChestContent, 10);
/*      */     
/*  898 */     for (int var2 = 0; var2 < 10; var2++) {
/*      */       
/*  900 */       int var3 = this.worldInfo.getSpawnX() + this.rand.nextInt(6) - this.rand.nextInt(6);
/*  901 */       int var4 = this.worldInfo.getSpawnZ() + this.rand.nextInt(6) - this.rand.nextInt(6);
/*  902 */       BlockPos var5 = func_175672_r(new BlockPos(var3, 0, var4)).offsetUp();
/*      */       
/*  904 */       if (var1.generate(this, this.rand, var5)) {
/*      */         break;
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public BlockPos func_180504_m() {
/*  913 */     return this.provider.func_177496_h();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void saveAllChunks(boolean p_73044_1_, IProgressUpdate p_73044_2_) throws MinecraftException {
/*  921 */     if (this.chunkProvider.canSave()) {
/*      */       
/*  923 */       if (p_73044_2_ != null)
/*      */       {
/*  925 */         p_73044_2_.displaySavingString("Saving level");
/*      */       }
/*      */       
/*  928 */       saveLevel();
/*      */       
/*  930 */       if (p_73044_2_ != null)
/*      */       {
/*  932 */         p_73044_2_.displayLoadingString("Saving chunks");
/*      */       }
/*      */       
/*  935 */       this.chunkProvider.saveChunks(p_73044_1_, p_73044_2_);
/*  936 */       List var3 = this.theChunkProviderServer.func_152380_a();
/*  937 */       Iterator<Chunk> var4 = var3.iterator();
/*      */       
/*  939 */       while (var4.hasNext()) {
/*      */         
/*  941 */         Chunk var5 = var4.next();
/*      */         
/*  943 */         if (!this.thePlayerManager.func_152621_a(var5.xPosition, var5.zPosition))
/*      */         {
/*  945 */           this.theChunkProviderServer.dropChunk(var5.xPosition, var5.zPosition);
/*      */         }
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void saveChunkData() {
/*  956 */     if (this.chunkProvider.canSave())
/*      */     {
/*  958 */       this.chunkProvider.saveExtraData();
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void saveLevel() throws MinecraftException {
/*  967 */     checkSessionLock();
/*  968 */     this.worldInfo.func_176145_a(getWorldBorder().getDiameter());
/*  969 */     this.worldInfo.func_176124_d(getWorldBorder().getCenterX());
/*  970 */     this.worldInfo.func_176141_c(getWorldBorder().getCenterZ());
/*  971 */     this.worldInfo.func_176129_e(getWorldBorder().getDamageBuffer());
/*  972 */     this.worldInfo.func_176125_f(getWorldBorder().func_177727_n());
/*  973 */     this.worldInfo.func_176122_j(getWorldBorder().getWarningDistance());
/*  974 */     this.worldInfo.func_176136_k(getWorldBorder().getWarningTime());
/*  975 */     this.worldInfo.func_176118_b(getWorldBorder().getTargetSize());
/*  976 */     this.worldInfo.func_176135_e(getWorldBorder().getTimeUntilTarget());
/*  977 */     this.saveHandler.saveWorldInfoWithPlayer(this.worldInfo, this.mcServer.getConfigurationManager().getHostPlayerData());
/*  978 */     this.mapStorage.saveAllData();
/*      */   }
/*      */ 
/*      */   
/*      */   protected void onEntityAdded(Entity p_72923_1_) {
/*  983 */     super.onEntityAdded(p_72923_1_);
/*  984 */     this.entitiesById.addKey(p_72923_1_.getEntityId(), p_72923_1_);
/*  985 */     this.entitiesByUuid.put(p_72923_1_.getUniqueID(), p_72923_1_);
/*  986 */     Entity[] var2 = p_72923_1_.getParts();
/*      */     
/*  988 */     if (var2 != null)
/*      */     {
/*  990 */       for (int var3 = 0; var3 < var2.length; var3++)
/*      */       {
/*  992 */         this.entitiesById.addKey(var2[var3].getEntityId(), var2[var3]);
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   protected void onEntityRemoved(Entity p_72847_1_) {
/*  999 */     super.onEntityRemoved(p_72847_1_);
/* 1000 */     this.entitiesById.removeObject(p_72847_1_.getEntityId());
/* 1001 */     this.entitiesByUuid.remove(p_72847_1_.getUniqueID());
/* 1002 */     Entity[] var2 = p_72847_1_.getParts();
/*      */     
/* 1004 */     if (var2 != null)
/*      */     {
/* 1006 */       for (int var3 = 0; var3 < var2.length; var3++)
/*      */       {
/* 1008 */         this.entitiesById.removeObject(var2[var3].getEntityId());
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean addWeatherEffect(Entity p_72942_1_) {
/* 1018 */     if (super.addWeatherEffect(p_72942_1_)) {
/*      */       
/* 1020 */       this.mcServer.getConfigurationManager().sendToAllNear(p_72942_1_.posX, p_72942_1_.posY, p_72942_1_.posZ, 512.0D, this.provider.getDimensionId(), (Packet)new S2CPacketSpawnGlobalEntity(p_72942_1_));
/* 1021 */       return true;
/*      */     } 
/*      */ 
/*      */     
/* 1025 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setEntityState(Entity entityIn, byte p_72960_2_) {
/* 1034 */     getEntityTracker().func_151248_b(entityIn, (Packet)new S19PacketEntityStatus(entityIn, p_72960_2_));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Explosion newExplosion(Entity p_72885_1_, double p_72885_2_, double p_72885_4_, double p_72885_6_, float p_72885_8_, boolean p_72885_9_, boolean p_72885_10_) {
/* 1042 */     Explosion var11 = new Explosion(this, p_72885_1_, p_72885_2_, p_72885_4_, p_72885_6_, p_72885_8_, p_72885_9_, p_72885_10_);
/* 1043 */     var11.doExplosionA();
/* 1044 */     var11.doExplosionB(false);
/*      */     
/* 1046 */     if (!p_72885_10_)
/*      */     {
/* 1048 */       var11.func_180342_d();
/*      */     }
/*      */     
/* 1051 */     Iterator<EntityPlayer> var12 = this.playerEntities.iterator();
/*      */     
/* 1053 */     while (var12.hasNext()) {
/*      */       
/* 1055 */       EntityPlayer var13 = var12.next();
/*      */       
/* 1057 */       if (var13.getDistanceSq(p_72885_2_, p_72885_4_, p_72885_6_) < 4096.0D)
/*      */       {
/* 1059 */         ((EntityPlayerMP)var13).playerNetServerHandler.sendPacket((Packet)new S27PacketExplosion(p_72885_2_, p_72885_4_, p_72885_6_, p_72885_8_, var11.func_180343_e(), (Vec3)var11.func_77277_b().get(var13)));
/*      */       }
/*      */     } 
/*      */     
/* 1063 */     return var11;
/*      */   }
/*      */ 
/*      */   
/*      */   public void addBlockEvent(BlockPos pos, Block blockIn, int eventID, int eventParam) {
/* 1068 */     BlockEventData var7, var5 = new BlockEventData(pos, blockIn, eventID, eventParam);
/* 1069 */     Iterator<E> var6 = this.field_147490_S[this.blockEventCacheIndex].iterator();
/*      */ 
/*      */ 
/*      */     
/*      */     do {
/* 1074 */       if (!var6.hasNext()) {
/*      */         
/* 1076 */         this.field_147490_S[this.blockEventCacheIndex].add((E)var5);
/*      */         
/*      */         return;
/*      */       } 
/* 1080 */       var7 = (BlockEventData)var6.next();
/*      */     }
/* 1082 */     while (!var7.equals(var5));
/*      */   }
/*      */ 
/*      */   
/*      */   private void func_147488_Z() {
/* 1087 */     while (!this.field_147490_S[this.blockEventCacheIndex].isEmpty()) {
/*      */       
/* 1089 */       int var1 = this.blockEventCacheIndex;
/* 1090 */       this.blockEventCacheIndex ^= 0x1;
/* 1091 */       Iterator<E> var2 = this.field_147490_S[var1].iterator();
/*      */       
/* 1093 */       while (var2.hasNext()) {
/*      */         
/* 1095 */         BlockEventData var3 = (BlockEventData)var2.next();
/*      */         
/* 1097 */         if (func_147485_a(var3))
/*      */         {
/* 1099 */           this.mcServer.getConfigurationManager().sendToAllNear(var3.func_180328_a().getX(), var3.func_180328_a().getY(), var3.func_180328_a().getZ(), 64.0D, this.provider.getDimensionId(), (Packet)new S24PacketBlockAction(var3.func_180328_a(), var3.getBlock(), var3.getEventID(), var3.getEventParameter()));
/*      */         }
/*      */       } 
/*      */       
/* 1103 */       this.field_147490_S[var1].clear();
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   private boolean func_147485_a(BlockEventData p_147485_1_) {
/* 1109 */     IBlockState var2 = getBlockState(p_147485_1_.func_180328_a());
/* 1110 */     return (var2.getBlock() == p_147485_1_.getBlock()) ? var2.getBlock().onBlockEventReceived(this, p_147485_1_.func_180328_a(), var2, p_147485_1_.getEventID(), p_147485_1_.getEventParameter()) : false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void flush() {
/* 1118 */     this.saveHandler.flush();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void updateWeather() {
/* 1126 */     boolean var1 = isRaining();
/* 1127 */     super.updateWeather();
/*      */     
/* 1129 */     if (this.prevRainingStrength != this.rainingStrength)
/*      */     {
/* 1131 */       this.mcServer.getConfigurationManager().sendPacketToAllPlayersInDimension((Packet)new S2BPacketChangeGameState(7, this.rainingStrength), this.provider.getDimensionId());
/*      */     }
/*      */     
/* 1134 */     if (this.prevThunderingStrength != this.thunderingStrength)
/*      */     {
/* 1136 */       this.mcServer.getConfigurationManager().sendPacketToAllPlayersInDimension((Packet)new S2BPacketChangeGameState(8, this.thunderingStrength), this.provider.getDimensionId());
/*      */     }
/*      */     
/* 1139 */     if (var1 != isRaining()) {
/*      */       
/* 1141 */       if (var1) {
/*      */         
/* 1143 */         this.mcServer.getConfigurationManager().sendPacketToAllPlayers((Packet)new S2BPacketChangeGameState(2, 0.0F));
/*      */       }
/*      */       else {
/*      */         
/* 1147 */         this.mcServer.getConfigurationManager().sendPacketToAllPlayers((Packet)new S2BPacketChangeGameState(1, 0.0F));
/*      */       } 
/*      */       
/* 1150 */       this.mcServer.getConfigurationManager().sendPacketToAllPlayers((Packet)new S2BPacketChangeGameState(7, this.rainingStrength));
/* 1151 */       this.mcServer.getConfigurationManager().sendPacketToAllPlayers((Packet)new S2BPacketChangeGameState(8, this.thunderingStrength));
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   protected int getRenderDistanceChunks() {
/* 1157 */     return this.mcServer.getConfigurationManager().getViewDistance();
/*      */   }
/*      */ 
/*      */   
/*      */   public MinecraftServer func_73046_m() {
/* 1162 */     return this.mcServer;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public EntityTracker getEntityTracker() {
/* 1170 */     return this.theEntityTracker;
/*      */   }
/*      */ 
/*      */   
/*      */   public PlayerManager getPlayerManager() {
/* 1175 */     return this.thePlayerManager;
/*      */   }
/*      */ 
/*      */   
/*      */   public Teleporter getDefaultTeleporter() {
/* 1180 */     return this.worldTeleporter;
/*      */   }
/*      */ 
/*      */   
/*      */   public void func_175739_a(EnumParticleTypes p_175739_1_, double p_175739_2_, double p_175739_4_, double p_175739_6_, int p_175739_8_, double p_175739_9_, double p_175739_11_, double p_175739_13_, double p_175739_15_, int... p_175739_17_) {
/* 1185 */     func_180505_a(p_175739_1_, false, p_175739_2_, p_175739_4_, p_175739_6_, p_175739_8_, p_175739_9_, p_175739_11_, p_175739_13_, p_175739_15_, p_175739_17_);
/*      */   }
/*      */ 
/*      */   
/*      */   public void func_180505_a(EnumParticleTypes p_180505_1_, boolean p_180505_2_, double p_180505_3_, double p_180505_5_, double p_180505_7_, int p_180505_9_, double p_180505_10_, double p_180505_12_, double p_180505_14_, double p_180505_16_, int... p_180505_18_) {
/* 1190 */     S2APacketParticles var19 = new S2APacketParticles(p_180505_1_, p_180505_2_, (float)p_180505_3_, (float)p_180505_5_, (float)p_180505_7_, (float)p_180505_10_, (float)p_180505_12_, (float)p_180505_14_, (float)p_180505_16_, p_180505_9_, p_180505_18_);
/*      */     
/* 1192 */     for (int var20 = 0; var20 < this.playerEntities.size(); var20++) {
/*      */       
/* 1194 */       EntityPlayerMP var21 = this.playerEntities.get(var20);
/* 1195 */       BlockPos var22 = var21.getPosition();
/* 1196 */       double var23 = var22.distanceSq(p_180505_3_, p_180505_5_, p_180505_7_);
/*      */       
/* 1198 */       if (var23 <= 256.0D || (p_180505_2_ && var23 <= 65536.0D))
/*      */       {
/* 1200 */         var21.playerNetServerHandler.sendPacket((Packet)var19);
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public Entity getEntityFromUuid(UUID uuid) {
/* 1207 */     return (Entity)this.entitiesByUuid.get(uuid);
/*      */   }
/*      */ 
/*      */   
/*      */   public ListenableFuture addScheduledTask(Runnable runnableToSchedule) {
/* 1212 */     return this.mcServer.addScheduledTask(runnableToSchedule);
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isCallingFromMinecraftThread() {
/* 1217 */     return this.mcServer.isCallingFromMinecraftThread();
/*      */   }
/*      */   
/*      */   static class ServerBlockEventList
/*      */     extends ArrayList
/*      */   {
/*      */     private static final String __OBFID = "CL_00001439";
/*      */     
/*      */     private ServerBlockEventList() {}
/*      */     
/*      */     ServerBlockEventList(Object p_i1521_1_) {
/* 1228 */       this();
/*      */     }
/*      */   }
/*      */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\world\WorldServer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */