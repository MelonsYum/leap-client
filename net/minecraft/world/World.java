/*      */ package net.minecraft.world;
/*      */ 
/*      */ import com.google.common.base.Predicate;
/*      */ import com.google.common.collect.Lists;
/*      */ import com.google.common.collect.Sets;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Calendar;
/*      */ import java.util.Collection;
/*      */ import java.util.Iterator;
/*      */ import java.util.List;
/*      */ import java.util.Random;
/*      */ import java.util.Set;
/*      */ import java.util.UUID;
/*      */ import java.util.concurrent.Callable;
/*      */ import net.minecraft.block.Block;
/*      */ import net.minecraft.block.BlockLiquid;
/*      */ import net.minecraft.block.BlockSlab;
/*      */ import net.minecraft.block.BlockSnow;
/*      */ import net.minecraft.block.BlockStairs;
/*      */ import net.minecraft.block.material.Material;
/*      */ import net.minecraft.block.properties.IProperty;
/*      */ import net.minecraft.block.state.IBlockState;
/*      */ import net.minecraft.command.IEntitySelector;
/*      */ import net.minecraft.crash.CrashReport;
/*      */ import net.minecraft.crash.CrashReportCategory;
/*      */ import net.minecraft.entity.Entity;
/*      */ import net.minecraft.entity.EntityLiving;
/*      */ import net.minecraft.entity.player.EntityPlayer;
/*      */ import net.minecraft.init.Blocks;
/*      */ import net.minecraft.item.ItemStack;
/*      */ import net.minecraft.nbt.NBTTagCompound;
/*      */ import net.minecraft.profiler.Profiler;
/*      */ import net.minecraft.scoreboard.Scoreboard;
/*      */ import net.minecraft.server.MinecraftServer;
/*      */ import net.minecraft.server.gui.IUpdatePlayerListBox;
/*      */ import net.minecraft.tileentity.TileEntity;
/*      */ import net.minecraft.util.AxisAlignedBB;
/*      */ import net.minecraft.util.BlockPos;
/*      */ import net.minecraft.util.EnumFacing;
/*      */ import net.minecraft.util.EnumParticleTypes;
/*      */ import net.minecraft.util.IntHashMap;
/*      */ import net.minecraft.util.MathHelper;
/*      */ import net.minecraft.util.MovingObjectPosition;
/*      */ import net.minecraft.util.ReportedException;
/*      */ import net.minecraft.util.Vec3;
/*      */ import net.minecraft.village.VillageCollection;
/*      */ import net.minecraft.world.biome.BiomeGenBase;
/*      */ import net.minecraft.world.biome.WorldChunkManager;
/*      */ import net.minecraft.world.border.WorldBorder;
/*      */ import net.minecraft.world.chunk.Chunk;
/*      */ import net.minecraft.world.chunk.IChunkProvider;
/*      */ import net.minecraft.world.gen.structure.StructureBoundingBox;
/*      */ import net.minecraft.world.storage.ISaveHandler;
/*      */ import net.minecraft.world.storage.MapStorage;
/*      */ import net.minecraft.world.storage.WorldInfo;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public abstract class World
/*      */   implements IBlockAccess
/*      */ {
/*      */   protected boolean scheduledUpdatesAreImmediate;
/*   65 */   public final List loadedEntityList = Lists.newArrayList();
/*   66 */   protected final List unloadedEntityList = Lists.newArrayList();
/*      */ 
/*      */   
/*   69 */   public final List loadedTileEntityList = Lists.newArrayList();
/*   70 */   public final List tickableTileEntities = Lists.newArrayList();
/*   71 */   private final List addedTileEntityList = Lists.newArrayList();
/*   72 */   private final List tileEntitiesToBeRemoved = Lists.newArrayList();
/*      */ 
/*      */   
/*   75 */   public final List playerEntities = Lists.newArrayList();
/*      */ 
/*      */   
/*   78 */   public final List weatherEffects = Lists.newArrayList();
/*   79 */   protected final IntHashMap entitiesById = new IntHashMap();
/*   80 */   private long cloudColour = 16777215L;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private int skylightSubtracted;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*   90 */   protected int updateLCG = (new Random()).nextInt();
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*   95 */   protected final int DIST_HASH_MAGIC = 1013904223;
/*      */ 
/*      */   
/*      */   protected float prevRainingStrength;
/*      */   
/*      */   protected float rainingStrength;
/*      */   
/*      */   protected float prevThunderingStrength;
/*      */   
/*      */   protected float thunderingStrength;
/*      */   
/*      */   private int lastLightningBolt;
/*      */   
/*  108 */   public final Random rand = new Random();
/*      */   
/*      */   public final WorldProvider provider;
/*      */   
/*  112 */   protected List worldAccesses = Lists.newArrayList();
/*      */ 
/*      */   
/*      */   protected IChunkProvider chunkProvider;
/*      */ 
/*      */   
/*      */   protected final ISaveHandler saveHandler;
/*      */ 
/*      */   
/*      */   protected WorldInfo worldInfo;
/*      */   
/*      */   protected boolean findingSpawnPoint;
/*      */   
/*      */   protected MapStorage mapStorage;
/*      */   
/*      */   protected VillageCollection villageCollectionObj;
/*      */   
/*      */   public final Profiler theProfiler;
/*      */   
/*  131 */   private final Calendar theCalendar = Calendar.getInstance();
/*  132 */   protected Scoreboard worldScoreboard = new Scoreboard();
/*      */ 
/*      */   
/*      */   public final boolean isRemote;
/*      */ 
/*      */   
/*  138 */   protected Set activeChunkSet = Sets.newHashSet();
/*      */ 
/*      */   
/*      */   private int ambientTickCountdown;
/*      */ 
/*      */   
/*      */   protected boolean spawnHostileMobs;
/*      */ 
/*      */   
/*      */   protected boolean spawnPeacefulMobs;
/*      */ 
/*      */   
/*      */   private boolean processingLoadedTiles;
/*      */ 
/*      */   
/*      */   private final WorldBorder worldBorder;
/*      */ 
/*      */   
/*      */   int[] lightUpdateBlockList;
/*      */   
/*      */   private static final String __OBFID = "CL_00000140";
/*      */ 
/*      */   
/*      */   protected World(ISaveHandler saveHandlerIn, WorldInfo info, WorldProvider providerIn, Profiler profilerIn, boolean client) {
/*  162 */     this.ambientTickCountdown = this.rand.nextInt(12000);
/*  163 */     this.spawnHostileMobs = true;
/*  164 */     this.spawnPeacefulMobs = true;
/*  165 */     this.lightUpdateBlockList = new int[32768];
/*  166 */     this.saveHandler = saveHandlerIn;
/*  167 */     this.theProfiler = profilerIn;
/*  168 */     this.worldInfo = info;
/*  169 */     this.provider = providerIn;
/*  170 */     this.isRemote = client;
/*  171 */     this.worldBorder = providerIn.getWorldBorder();
/*      */   }
/*      */ 
/*      */   
/*      */   public World init() {
/*  176 */     return this;
/*      */   }
/*      */ 
/*      */   
/*      */   public BiomeGenBase getBiomeGenForCoords(final BlockPos pos) {
/*  181 */     if (isBlockLoaded(pos)) {
/*      */       
/*  183 */       Chunk var2 = getChunkFromBlockCoords(pos);
/*      */ 
/*      */       
/*      */       try {
/*  187 */         return var2.getBiome(pos, this.provider.getWorldChunkManager());
/*      */       }
/*  189 */       catch (Throwable var6) {
/*      */         
/*  191 */         CrashReport var4 = CrashReport.makeCrashReport(var6, "Getting biome");
/*  192 */         CrashReportCategory var5 = var4.makeCategory("Coordinates of biome request");
/*  193 */         var5.addCrashSectionCallable("Location", new Callable()
/*      */             {
/*      */               private static final String __OBFID = "CL_00000141";
/*      */               
/*      */               public String call() {
/*  198 */                 return CrashReportCategory.getCoordinateInfo(pos);
/*      */               }
/*      */             });
/*  201 */         throw new ReportedException(var4);
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/*  206 */     return this.provider.getWorldChunkManager().func_180300_a(pos, BiomeGenBase.plains);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public WorldChunkManager getWorldChunkManager() {
/*  212 */     return this.provider.getWorldChunkManager();
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected abstract IChunkProvider createChunkProvider();
/*      */ 
/*      */ 
/*      */   
/*      */   public void initialize(WorldSettings settings) {
/*  222 */     this.worldInfo.setServerInitialized(true);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setInitialSpawnLocation() {
/*  230 */     setSpawnLocation(new BlockPos(8, 64, 8));
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public Block getGroundAboveSeaLevel(BlockPos pos) {
/*      */     BlockPos var2;
/*  237 */     for (var2 = new BlockPos(pos.getX(), 63, pos.getZ()); !isAirBlock(var2.offsetUp()); var2 = var2.offsetUp());
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  242 */     return getBlockState(var2).getBlock();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean isValid(BlockPos pos) {
/*  250 */     return (pos.getX() >= -30000000 && pos.getZ() >= -30000000 && pos.getX() < 30000000 && pos.getZ() < 30000000 && pos.getY() >= 0 && pos.getY() < 256);
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isAirBlock(BlockPos pos) {
/*  255 */     return (getBlockState(pos).getBlock().getMaterial() == Material.air);
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isBlockLoaded(BlockPos pos) {
/*  260 */     return isBlockLoaded(pos, true);
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isBlockLoaded(BlockPos pos, boolean p_175668_2_) {
/*  265 */     return !isValid(pos) ? false : isChunkLoaded(pos.getX() >> 4, pos.getZ() >> 4, p_175668_2_);
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isAreaLoaded(BlockPos p_175697_1_, int radius) {
/*  270 */     return isAreaLoaded(p_175697_1_, radius, true);
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isAreaLoaded(BlockPos p_175648_1_, int radius, boolean p_175648_3_) {
/*  275 */     return isAreaLoaded(p_175648_1_.getX() - radius, p_175648_1_.getY() - radius, p_175648_1_.getZ() - radius, p_175648_1_.getX() + radius, p_175648_1_.getY() + radius, p_175648_1_.getZ() + radius, p_175648_3_);
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isAreaLoaded(BlockPos p_175707_1_, BlockPos p_175707_2_) {
/*  280 */     return isAreaLoaded(p_175707_1_, p_175707_2_, true);
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isAreaLoaded(BlockPos p_175706_1_, BlockPos p_175706_2_, boolean p_175706_3_) {
/*  285 */     return isAreaLoaded(p_175706_1_.getX(), p_175706_1_.getY(), p_175706_1_.getZ(), p_175706_2_.getX(), p_175706_2_.getY(), p_175706_2_.getZ(), p_175706_3_);
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isAreaLoaded(StructureBoundingBox p_175711_1_) {
/*  290 */     return isAreaLoaded(p_175711_1_, true);
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isAreaLoaded(StructureBoundingBox p_175639_1_, boolean p_175639_2_) {
/*  295 */     return isAreaLoaded(p_175639_1_.minX, p_175639_1_.minY, p_175639_1_.minZ, p_175639_1_.maxX, p_175639_1_.maxY, p_175639_1_.maxZ, p_175639_2_);
/*      */   }
/*      */ 
/*      */   
/*      */   private boolean isAreaLoaded(int p_175663_1_, int p_175663_2_, int p_175663_3_, int p_175663_4_, int p_175663_5_, int p_175663_6_, boolean p_175663_7_) {
/*  300 */     if (p_175663_5_ >= 0 && p_175663_2_ < 256) {
/*      */       
/*  302 */       p_175663_1_ >>= 4;
/*  303 */       p_175663_3_ >>= 4;
/*  304 */       p_175663_4_ >>= 4;
/*  305 */       p_175663_6_ >>= 4;
/*      */       
/*  307 */       for (int var8 = p_175663_1_; var8 <= p_175663_4_; var8++) {
/*      */         
/*  309 */         for (int var9 = p_175663_3_; var9 <= p_175663_6_; var9++) {
/*      */           
/*  311 */           if (!isChunkLoaded(var8, var9, p_175663_7_))
/*      */           {
/*  313 */             return false;
/*      */           }
/*      */         } 
/*      */       } 
/*      */       
/*  318 */       return true;
/*      */     } 
/*      */ 
/*      */     
/*  322 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean isChunkLoaded(int x, int z, boolean allowEmpty) {
/*  328 */     return (this.chunkProvider.chunkExists(x, z) && (allowEmpty || !this.chunkProvider.provideChunk(x, z).isEmpty()));
/*      */   }
/*      */ 
/*      */   
/*      */   public Chunk getChunkFromBlockCoords(BlockPos pos) {
/*  333 */     return getChunkFromChunkCoords(pos.getX() >> 4, pos.getZ() >> 4);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Chunk getChunkFromChunkCoords(int chunkX, int chunkZ) {
/*  344 */     return this.chunkProvider.provideChunk(chunkX, chunkZ);
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean setBlockState(BlockPos pos, IBlockState newState, int flags) {
/*  349 */     if (!isValid(pos))
/*      */     {
/*  351 */       return false;
/*      */     }
/*  353 */     if (!this.isRemote && this.worldInfo.getTerrainType() == WorldType.DEBUG_WORLD)
/*      */     {
/*  355 */       return false;
/*      */     }
/*      */ 
/*      */     
/*  359 */     Chunk var4 = getChunkFromBlockCoords(pos);
/*  360 */     Block var5 = newState.getBlock();
/*  361 */     IBlockState var6 = var4.setBlockState(pos, newState);
/*      */     
/*  363 */     if (var6 == null)
/*      */     {
/*  365 */       return false;
/*      */     }
/*      */ 
/*      */     
/*  369 */     Block var7 = var6.getBlock();
/*      */     
/*  371 */     if (var5.getLightOpacity() != var7.getLightOpacity() || var5.getLightValue() != var7.getLightValue()) {
/*      */       
/*  373 */       this.theProfiler.startSection("checkLight");
/*  374 */       checkLight(pos);
/*  375 */       this.theProfiler.endSection();
/*      */     } 
/*      */     
/*  378 */     if ((flags & 0x2) != 0 && (!this.isRemote || (flags & 0x4) == 0) && var4.isPopulated())
/*      */     {
/*  380 */       markBlockForUpdate(pos);
/*      */     }
/*      */     
/*  383 */     if (!this.isRemote && (flags & 0x1) != 0) {
/*      */       
/*  385 */       func_175722_b(pos, var6.getBlock());
/*      */       
/*  387 */       if (var5.hasComparatorInputOverride())
/*      */       {
/*  389 */         updateComparatorOutputLevel(pos, var5);
/*      */       }
/*      */     } 
/*      */     
/*  393 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean setBlockToAir(BlockPos pos) {
/*  400 */     return setBlockState(pos, Blocks.air.getDefaultState(), 3);
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean destroyBlock(BlockPos pos, boolean dropBlock) {
/*  405 */     IBlockState var3 = getBlockState(pos);
/*  406 */     Block var4 = var3.getBlock();
/*      */     
/*  408 */     if (var4.getMaterial() == Material.air)
/*      */     {
/*  410 */       return false;
/*      */     }
/*      */ 
/*      */     
/*  414 */     playAuxSFX(2001, pos, Block.getStateId(var3));
/*      */     
/*  416 */     if (dropBlock)
/*      */     {
/*  418 */       var4.dropBlockAsItem(this, pos, var3, 0);
/*      */     }
/*      */     
/*  421 */     return setBlockState(pos, Blocks.air.getDefaultState(), 3);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean setBlockState(BlockPos pos, IBlockState state) {
/*  430 */     return setBlockState(pos, state, 3);
/*      */   }
/*      */ 
/*      */   
/*      */   public void markBlockForUpdate(BlockPos pos) {
/*  435 */     for (int var2 = 0; var2 < this.worldAccesses.size(); var2++)
/*      */     {
/*  437 */       ((IWorldAccess)this.worldAccesses.get(var2)).markBlockForUpdate(pos);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void func_175722_b(BlockPos pos, Block blockType) {
/*  443 */     if (this.worldInfo.getTerrainType() != WorldType.DEBUG_WORLD)
/*      */     {
/*  445 */       notifyNeighborsOfStateChange(pos, blockType);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void markBlocksDirtyVertical(int x1, int z1, int x2, int z2) {
/*  456 */     if (x2 > z2) {
/*      */       
/*  458 */       int var5 = z2;
/*  459 */       z2 = x2;
/*  460 */       x2 = var5;
/*      */     } 
/*      */     
/*  463 */     if (!this.provider.getHasNoSky())
/*      */     {
/*  465 */       for (int var5 = x2; var5 <= z2; var5++)
/*      */       {
/*  467 */         checkLightFor(EnumSkyBlock.SKY, new BlockPos(x1, var5, z1));
/*      */       }
/*      */     }
/*      */     
/*  471 */     markBlockRangeForRenderUpdate(x1, x2, z1, x1, z2, z1);
/*      */   }
/*      */ 
/*      */   
/*      */   public void markBlockRangeForRenderUpdate(BlockPos rangeMin, BlockPos rangeMax) {
/*  476 */     markBlockRangeForRenderUpdate(rangeMin.getX(), rangeMin.getY(), rangeMin.getZ(), rangeMax.getX(), rangeMax.getY(), rangeMax.getZ());
/*      */   }
/*      */ 
/*      */   
/*      */   public void markBlockRangeForRenderUpdate(int x1, int y1, int z1, int x2, int y2, int z2) {
/*  481 */     for (int var7 = 0; var7 < this.worldAccesses.size(); var7++)
/*      */     {
/*  483 */       ((IWorldAccess)this.worldAccesses.get(var7)).markBlockRangeForRenderUpdate(x1, y1, z1, x2, y2, z2);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void notifyNeighborsOfStateChange(BlockPos pos, Block blockType) {
/*  489 */     notifyBlockOfStateChange(pos.offsetWest(), blockType);
/*  490 */     notifyBlockOfStateChange(pos.offsetEast(), blockType);
/*  491 */     notifyBlockOfStateChange(pos.offsetDown(), blockType);
/*  492 */     notifyBlockOfStateChange(pos.offsetUp(), blockType);
/*  493 */     notifyBlockOfStateChange(pos.offsetNorth(), blockType);
/*  494 */     notifyBlockOfStateChange(pos.offsetSouth(), blockType);
/*      */   }
/*      */ 
/*      */   
/*      */   public void notifyNeighborsOfStateExcept(BlockPos pos, Block blockType, EnumFacing skipSide) {
/*  499 */     if (skipSide != EnumFacing.WEST)
/*      */     {
/*  501 */       notifyBlockOfStateChange(pos.offsetWest(), blockType);
/*      */     }
/*      */     
/*  504 */     if (skipSide != EnumFacing.EAST)
/*      */     {
/*  506 */       notifyBlockOfStateChange(pos.offsetEast(), blockType);
/*      */     }
/*      */     
/*  509 */     if (skipSide != EnumFacing.DOWN)
/*      */     {
/*  511 */       notifyBlockOfStateChange(pos.offsetDown(), blockType);
/*      */     }
/*      */     
/*  514 */     if (skipSide != EnumFacing.UP)
/*      */     {
/*  516 */       notifyBlockOfStateChange(pos.offsetUp(), blockType);
/*      */     }
/*      */     
/*  519 */     if (skipSide != EnumFacing.NORTH)
/*      */     {
/*  521 */       notifyBlockOfStateChange(pos.offsetNorth(), blockType);
/*      */     }
/*      */     
/*  524 */     if (skipSide != EnumFacing.SOUTH)
/*      */     {
/*  526 */       notifyBlockOfStateChange(pos.offsetSouth(), blockType);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void notifyBlockOfStateChange(BlockPos pos, final Block blockIn) {
/*  532 */     if (!this.isRemote) {
/*      */       
/*  534 */       IBlockState var3 = getBlockState(pos);
/*      */ 
/*      */       
/*      */       try {
/*  538 */         var3.getBlock().onNeighborBlockChange(this, pos, var3, blockIn);
/*      */       }
/*  540 */       catch (Throwable var7) {
/*      */         
/*  542 */         CrashReport var5 = CrashReport.makeCrashReport(var7, "Exception while updating neighbours");
/*  543 */         CrashReportCategory var6 = var5.makeCategory("Block being updated");
/*  544 */         var6.addCrashSectionCallable("Source block type", new Callable()
/*      */             {
/*      */               private static final String __OBFID = "CL_00000142";
/*      */ 
/*      */               
/*      */               public String call() {
/*      */                 try {
/*  551 */                   return String.format("ID #%d (%s // %s)", new Object[] { Integer.valueOf(Block.getIdFromBlock(this.val$blockIn)), this.val$blockIn.getUnlocalizedName(), this.val$blockIn.getClass().getCanonicalName() });
/*      */                 }
/*  553 */                 catch (Throwable var2) {
/*      */                   
/*  555 */                   return "ID #" + Block.getIdFromBlock(blockIn);
/*      */                 } 
/*      */               }
/*      */             });
/*  559 */         CrashReportCategory.addBlockInfo(var6, pos, var3);
/*  560 */         throw new ReportedException(var5);
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isBlockTickPending(BlockPos pos, Block blockType) {
/*  567 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isAgainstSky(BlockPos pos) {
/*  572 */     return getChunkFromBlockCoords(pos).canSeeSky(pos);
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean canBlockSeeSky(BlockPos pos) {
/*  577 */     if (pos.getY() >= 63)
/*      */     {
/*  579 */       return isAgainstSky(pos);
/*      */     }
/*      */ 
/*      */     
/*  583 */     BlockPos var2 = new BlockPos(pos.getX(), 63, pos.getZ());
/*      */     
/*  585 */     if (!isAgainstSky(var2))
/*      */     {
/*  587 */       return false;
/*      */     }
/*      */ 
/*      */     
/*  591 */     for (var2 = var2.offsetDown(); var2.getY() > pos.getY(); var2 = var2.offsetDown()) {
/*      */       
/*  593 */       Block var3 = getBlockState(var2).getBlock();
/*      */       
/*  595 */       if (var3.getLightOpacity() > 0 && !var3.getMaterial().isLiquid())
/*      */       {
/*  597 */         return false;
/*      */       }
/*      */     } 
/*      */     
/*  601 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getLight(BlockPos pos) {
/*  608 */     if (pos.getY() < 0)
/*      */     {
/*  610 */       return 0;
/*      */     }
/*      */ 
/*      */     
/*  614 */     if (pos.getY() >= 256)
/*      */     {
/*  616 */       pos = new BlockPos(pos.getX(), 255, pos.getZ());
/*      */     }
/*      */     
/*  619 */     return getChunkFromBlockCoords(pos).setLight(pos, 0);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public int getLightFromNeighbors(BlockPos pos) {
/*  625 */     return getLight(pos, true);
/*      */   }
/*      */ 
/*      */   
/*      */   public int getLight(BlockPos pos, boolean checkNeighbors) {
/*  630 */     if (pos.getX() >= -30000000 && pos.getZ() >= -30000000 && pos.getX() < 30000000 && pos.getZ() < 30000000) {
/*      */       
/*  632 */       if (checkNeighbors && getBlockState(pos).getBlock().getUseNeighborBrightness()) {
/*      */         
/*  634 */         int var8 = getLight(pos.offsetUp(), false);
/*  635 */         int var4 = getLight(pos.offsetEast(), false);
/*  636 */         int var5 = getLight(pos.offsetWest(), false);
/*  637 */         int var6 = getLight(pos.offsetSouth(), false);
/*  638 */         int var7 = getLight(pos.offsetNorth(), false);
/*      */         
/*  640 */         if (var4 > var8)
/*      */         {
/*  642 */           var8 = var4;
/*      */         }
/*      */         
/*  645 */         if (var5 > var8)
/*      */         {
/*  647 */           var8 = var5;
/*      */         }
/*      */         
/*  650 */         if (var6 > var8)
/*      */         {
/*  652 */           var8 = var6;
/*      */         }
/*      */         
/*  655 */         if (var7 > var8)
/*      */         {
/*  657 */           var8 = var7;
/*      */         }
/*      */         
/*  660 */         return var8;
/*      */       } 
/*  662 */       if (pos.getY() < 0)
/*      */       {
/*  664 */         return 0;
/*      */       }
/*      */ 
/*      */       
/*  668 */       if (pos.getY() >= 256)
/*      */       {
/*  670 */         pos = new BlockPos(pos.getX(), 255, pos.getZ());
/*      */       }
/*      */       
/*  673 */       Chunk var3 = getChunkFromBlockCoords(pos);
/*  674 */       return var3.setLight(pos, this.skylightSubtracted);
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*  679 */     return 15;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public BlockPos getHorizon(BlockPos pos) {
/*      */     int var2;
/*  687 */     if (pos.getX() >= -30000000 && pos.getZ() >= -30000000 && pos.getX() < 30000000 && pos.getZ() < 30000000) {
/*      */       
/*  689 */       if (isChunkLoaded(pos.getX() >> 4, pos.getZ() >> 4, true))
/*      */       {
/*  691 */         var2 = getChunkFromChunkCoords(pos.getX() >> 4, pos.getZ() >> 4).getHeight(pos.getX() & 0xF, pos.getZ() & 0xF);
/*      */       }
/*      */       else
/*      */       {
/*  695 */         var2 = 0;
/*      */       }
/*      */     
/*      */     } else {
/*      */       
/*  700 */       var2 = 64;
/*      */     } 
/*      */     
/*  703 */     return new BlockPos(pos.getX(), var2, pos.getZ());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getChunksLowestHorizon(int x, int z) {
/*  711 */     if (x >= -30000000 && z >= -30000000 && x < 30000000 && z < 30000000) {
/*      */       
/*  713 */       if (!isChunkLoaded(x >> 4, z >> 4, true))
/*      */       {
/*  715 */         return 0;
/*      */       }
/*      */ 
/*      */       
/*  719 */       Chunk var3 = getChunkFromChunkCoords(x >> 4, z >> 4);
/*  720 */       return var3.getLowestHeight();
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*  725 */     return 64;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public int getLightFromNeighborsFor(EnumSkyBlock type, BlockPos p_175705_2_) {
/*  731 */     if (this.provider.getHasNoSky() && type == EnumSkyBlock.SKY)
/*      */     {
/*  733 */       return 0;
/*      */     }
/*      */ 
/*      */     
/*  737 */     if (p_175705_2_.getY() < 0)
/*      */     {
/*  739 */       p_175705_2_ = new BlockPos(p_175705_2_.getX(), 0, p_175705_2_.getZ());
/*      */     }
/*      */     
/*  742 */     if (!isValid(p_175705_2_))
/*      */     {
/*  744 */       return type.defaultLightValue;
/*      */     }
/*  746 */     if (!isBlockLoaded(p_175705_2_))
/*      */     {
/*  748 */       return type.defaultLightValue;
/*      */     }
/*  750 */     if (getBlockState(p_175705_2_).getBlock().getUseNeighborBrightness()) {
/*      */       
/*  752 */       int var8 = getLightFor(type, p_175705_2_.offsetUp());
/*  753 */       int var4 = getLightFor(type, p_175705_2_.offsetEast());
/*  754 */       int var5 = getLightFor(type, p_175705_2_.offsetWest());
/*  755 */       int var6 = getLightFor(type, p_175705_2_.offsetSouth());
/*  756 */       int var7 = getLightFor(type, p_175705_2_.offsetNorth());
/*      */       
/*  758 */       if (var4 > var8)
/*      */       {
/*  760 */         var8 = var4;
/*      */       }
/*      */       
/*  763 */       if (var5 > var8)
/*      */       {
/*  765 */         var8 = var5;
/*      */       }
/*      */       
/*  768 */       if (var6 > var8)
/*      */       {
/*  770 */         var8 = var6;
/*      */       }
/*      */       
/*  773 */       if (var7 > var8)
/*      */       {
/*  775 */         var8 = var7;
/*      */       }
/*      */       
/*  778 */       return var8;
/*      */     } 
/*      */ 
/*      */     
/*  782 */     Chunk var3 = getChunkFromBlockCoords(p_175705_2_);
/*  783 */     return var3.getLightFor(type, p_175705_2_);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getLightFor(EnumSkyBlock type, BlockPos pos) {
/*  790 */     if (pos.getY() < 0)
/*      */     {
/*  792 */       pos = new BlockPos(pos.getX(), 0, pos.getZ());
/*      */     }
/*      */     
/*  795 */     if (!isValid(pos))
/*      */     {
/*  797 */       return type.defaultLightValue;
/*      */     }
/*  799 */     if (!isBlockLoaded(pos))
/*      */     {
/*  801 */       return type.defaultLightValue;
/*      */     }
/*      */ 
/*      */     
/*  805 */     Chunk var3 = getChunkFromBlockCoords(pos);
/*  806 */     return var3.getLightFor(type, pos);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void setLightFor(EnumSkyBlock type, BlockPos pos, int lightValue) {
/*  812 */     if (isValid(pos))
/*      */     {
/*  814 */       if (isBlockLoaded(pos)) {
/*      */         
/*  816 */         Chunk var4 = getChunkFromBlockCoords(pos);
/*  817 */         var4.setLightFor(type, pos, lightValue);
/*  818 */         notifyLightSet(pos);
/*      */       } 
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void notifyLightSet(BlockPos pos) {
/*  825 */     for (int var2 = 0; var2 < this.worldAccesses.size(); var2++)
/*      */     {
/*  827 */       ((IWorldAccess)this.worldAccesses.get(var2)).notifyLightSet(pos);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public int getCombinedLight(BlockPos p_175626_1_, int p_175626_2_) {
/*  833 */     int var3 = getLightFromNeighborsFor(EnumSkyBlock.SKY, p_175626_1_);
/*  834 */     int var4 = getLightFromNeighborsFor(EnumSkyBlock.BLOCK, p_175626_1_);
/*      */     
/*  836 */     if (var4 < p_175626_2_)
/*      */     {
/*  838 */       var4 = p_175626_2_;
/*      */     }
/*      */     
/*  841 */     return var3 << 20 | var4 << 4;
/*      */   }
/*      */ 
/*      */   
/*      */   public float getLightBrightness(BlockPos pos) {
/*  846 */     return this.provider.getLightBrightnessTable()[getLightFromNeighbors(pos)];
/*      */   }
/*      */ 
/*      */   
/*      */   public IBlockState getBlockState(BlockPos pos) {
/*  851 */     if (!isValid(pos))
/*      */     {
/*  853 */       return Blocks.air.getDefaultState();
/*      */     }
/*      */ 
/*      */     
/*  857 */     Chunk var2 = getChunkFromBlockCoords(pos);
/*  858 */     return var2.getBlockState(pos);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isDaytime() {
/*  867 */     return (this.skylightSubtracted < 4);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public MovingObjectPosition rayTraceBlocks(Vec3 p_72933_1_, Vec3 p_72933_2_) {
/*  875 */     return rayTraceBlocks(p_72933_1_, p_72933_2_, false, false, false);
/*      */   }
/*      */ 
/*      */   
/*      */   public MovingObjectPosition rayTraceBlocks(Vec3 p_72901_1_, Vec3 p_72901_2_, boolean p_72901_3_) {
/*  880 */     return rayTraceBlocks(p_72901_1_, p_72901_2_, p_72901_3_, false, false);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public MovingObjectPosition rayTraceBlocks(Vec3 p_147447_1_, Vec3 p_147447_2_, boolean p_147447_3_, boolean p_147447_4_, boolean p_147447_5_) {
/*  889 */     if (!Double.isNaN(p_147447_1_.xCoord) && !Double.isNaN(p_147447_1_.yCoord) && !Double.isNaN(p_147447_1_.zCoord)) {
/*      */       
/*  891 */       if (!Double.isNaN(p_147447_2_.xCoord) && !Double.isNaN(p_147447_2_.yCoord) && !Double.isNaN(p_147447_2_.zCoord)) {
/*      */         
/*  893 */         int var6 = MathHelper.floor_double(p_147447_2_.xCoord);
/*  894 */         int var7 = MathHelper.floor_double(p_147447_2_.yCoord);
/*  895 */         int var8 = MathHelper.floor_double(p_147447_2_.zCoord);
/*  896 */         int var9 = MathHelper.floor_double(p_147447_1_.xCoord);
/*  897 */         int var10 = MathHelper.floor_double(p_147447_1_.yCoord);
/*  898 */         int var11 = MathHelper.floor_double(p_147447_1_.zCoord);
/*  899 */         BlockPos var12 = new BlockPos(var9, var10, var11);
/*      */         
/*  901 */         IBlockState var14 = getBlockState(var12);
/*  902 */         Block var15 = var14.getBlock();
/*      */         
/*  904 */         if ((!p_147447_4_ || var15.getCollisionBoundingBox(this, var12, var14) != null) && var15.canCollideCheck(var14, p_147447_3_)) {
/*      */           
/*  906 */           MovingObjectPosition var16 = var15.collisionRayTrace(this, var12, p_147447_1_, p_147447_2_);
/*      */           
/*  908 */           if (var16 != null)
/*      */           {
/*  910 */             return var16;
/*      */           }
/*      */         } 
/*      */         
/*  914 */         MovingObjectPosition var41 = null;
/*  915 */         int var42 = 200;
/*      */         
/*  917 */         while (var42-- >= 0) {
/*      */           EnumFacing var37;
/*  919 */           if (Double.isNaN(p_147447_1_.xCoord) || Double.isNaN(p_147447_1_.yCoord) || Double.isNaN(p_147447_1_.zCoord))
/*      */           {
/*  921 */             return null;
/*      */           }
/*      */           
/*  924 */           if (var9 == var6 && var10 == var7 && var11 == var8)
/*      */           {
/*  926 */             return p_147447_5_ ? var41 : null;
/*      */           }
/*      */           
/*  929 */           boolean var43 = true;
/*  930 */           boolean var17 = true;
/*  931 */           boolean var18 = true;
/*  932 */           double var19 = 999.0D;
/*  933 */           double var21 = 999.0D;
/*  934 */           double var23 = 999.0D;
/*      */           
/*  936 */           if (var6 > var9) {
/*      */             
/*  938 */             var19 = var9 + 1.0D;
/*      */           }
/*  940 */           else if (var6 < var9) {
/*      */             
/*  942 */             var19 = var9 + 0.0D;
/*      */           }
/*      */           else {
/*      */             
/*  946 */             var43 = false;
/*      */           } 
/*      */           
/*  949 */           if (var7 > var10) {
/*      */             
/*  951 */             var21 = var10 + 1.0D;
/*      */           }
/*  953 */           else if (var7 < var10) {
/*      */             
/*  955 */             var21 = var10 + 0.0D;
/*      */           }
/*      */           else {
/*      */             
/*  959 */             var17 = false;
/*      */           } 
/*      */           
/*  962 */           if (var8 > var11) {
/*      */             
/*  964 */             var23 = var11 + 1.0D;
/*      */           }
/*  966 */           else if (var8 < var11) {
/*      */             
/*  968 */             var23 = var11 + 0.0D;
/*      */           }
/*      */           else {
/*      */             
/*  972 */             var18 = false;
/*      */           } 
/*      */           
/*  975 */           double var25 = 999.0D;
/*  976 */           double var27 = 999.0D;
/*  977 */           double var29 = 999.0D;
/*  978 */           double var31 = p_147447_2_.xCoord - p_147447_1_.xCoord;
/*  979 */           double var33 = p_147447_2_.yCoord - p_147447_1_.yCoord;
/*  980 */           double var35 = p_147447_2_.zCoord - p_147447_1_.zCoord;
/*      */           
/*  982 */           if (var43)
/*      */           {
/*  984 */             var25 = (var19 - p_147447_1_.xCoord) / var31;
/*      */           }
/*      */           
/*  987 */           if (var17)
/*      */           {
/*  989 */             var27 = (var21 - p_147447_1_.yCoord) / var33;
/*      */           }
/*      */           
/*  992 */           if (var18)
/*      */           {
/*  994 */             var29 = (var23 - p_147447_1_.zCoord) / var35;
/*      */           }
/*      */           
/*  997 */           if (var25 == -0.0D)
/*      */           {
/*  999 */             var25 = -1.0E-4D;
/*      */           }
/*      */           
/* 1002 */           if (var27 == -0.0D)
/*      */           {
/* 1004 */             var27 = -1.0E-4D;
/*      */           }
/*      */           
/* 1007 */           if (var29 == -0.0D)
/*      */           {
/* 1009 */             var29 = -1.0E-4D;
/*      */           }
/*      */ 
/*      */ 
/*      */           
/* 1014 */           if (var25 < var27 && var25 < var29) {
/*      */             
/* 1016 */             var37 = (var6 > var9) ? EnumFacing.WEST : EnumFacing.EAST;
/* 1017 */             p_147447_1_ = new Vec3(var19, p_147447_1_.yCoord + var33 * var25, p_147447_1_.zCoord + var35 * var25);
/*      */           }
/* 1019 */           else if (var27 < var29) {
/*      */             
/* 1021 */             var37 = (var7 > var10) ? EnumFacing.DOWN : EnumFacing.UP;
/* 1022 */             p_147447_1_ = new Vec3(p_147447_1_.xCoord + var31 * var27, var21, p_147447_1_.zCoord + var35 * var27);
/*      */           }
/*      */           else {
/*      */             
/* 1026 */             var37 = (var8 > var11) ? EnumFacing.NORTH : EnumFacing.SOUTH;
/* 1027 */             p_147447_1_ = new Vec3(p_147447_1_.xCoord + var31 * var29, p_147447_1_.yCoord + var33 * var29, var23);
/*      */           } 
/*      */           
/* 1030 */           var9 = MathHelper.floor_double(p_147447_1_.xCoord) - ((var37 == EnumFacing.EAST) ? 1 : 0);
/* 1031 */           var10 = MathHelper.floor_double(p_147447_1_.yCoord) - ((var37 == EnumFacing.UP) ? 1 : 0);
/* 1032 */           var11 = MathHelper.floor_double(p_147447_1_.zCoord) - ((var37 == EnumFacing.SOUTH) ? 1 : 0);
/* 1033 */           var12 = new BlockPos(var9, var10, var11);
/* 1034 */           IBlockState var38 = getBlockState(var12);
/* 1035 */           Block var39 = var38.getBlock();
/*      */           
/* 1037 */           if (!p_147447_4_ || var39.getCollisionBoundingBox(this, var12, var38) != null) {
/*      */             
/* 1039 */             if (var39.canCollideCheck(var38, p_147447_3_)) {
/*      */               
/* 1041 */               MovingObjectPosition var40 = var39.collisionRayTrace(this, var12, p_147447_1_, p_147447_2_);
/*      */               
/* 1043 */               if (var40 != null)
/*      */               {
/* 1045 */                 return var40;
/*      */               }
/*      */               
/*      */               continue;
/*      */             } 
/* 1050 */             var41 = new MovingObjectPosition(MovingObjectPosition.MovingObjectType.MISS, p_147447_1_, var37, var12);
/*      */           } 
/*      */         } 
/*      */ 
/*      */         
/* 1055 */         return p_147447_5_ ? var41 : null;
/*      */       } 
/*      */ 
/*      */       
/* 1059 */       return null;
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 1064 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void playSoundAtEntity(Entity p_72956_1_, String p_72956_2_, float p_72956_3_, float p_72956_4_) {
/* 1074 */     for (int var5 = 0; var5 < this.worldAccesses.size(); var5++)
/*      */     {
/* 1076 */       ((IWorldAccess)this.worldAccesses.get(var5)).playSound(p_72956_2_, p_72956_1_.posX, p_72956_1_.posY, p_72956_1_.posZ, p_72956_3_, p_72956_4_);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void playSoundToNearExcept(EntityPlayer p_85173_1_, String p_85173_2_, float p_85173_3_, float p_85173_4_) {
/* 1085 */     for (int var5 = 0; var5 < this.worldAccesses.size(); var5++)
/*      */     {
/* 1087 */       ((IWorldAccess)this.worldAccesses.get(var5)).playSoundToNearExcept(p_85173_1_, p_85173_2_, p_85173_1_.posX, p_85173_1_.posY, p_85173_1_.posZ, p_85173_3_, p_85173_4_);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void playSoundEffect(double x, double y, double z, String soundName, float volume, float pitch) {
/* 1098 */     for (int var10 = 0; var10 < this.worldAccesses.size(); var10++)
/*      */     {
/* 1100 */       ((IWorldAccess)this.worldAccesses.get(var10)).playSound(soundName, x, y, z, volume, pitch);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void playSound(double x, double y, double z, String soundName, float volume, float pitch, boolean distanceDelay) {}
/*      */ 
/*      */ 
/*      */   
/*      */   public void func_175717_a(BlockPos p_175717_1_, String p_175717_2_) {
/* 1111 */     for (int var3 = 0; var3 < this.worldAccesses.size(); var3++)
/*      */     {
/* 1113 */       ((IWorldAccess)this.worldAccesses.get(var3)).func_174961_a(p_175717_2_, p_175717_1_);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void spawnParticle(EnumParticleTypes p_175688_1_, double p_175688_2_, double p_175688_4_, double p_175688_6_, double p_175688_8_, double p_175688_10_, double p_175688_12_, int... p_175688_14_) {
/* 1119 */     spawnParticle(p_175688_1_.func_179348_c(), p_175688_1_.func_179344_e(), p_175688_2_, p_175688_4_, p_175688_6_, p_175688_8_, p_175688_10_, p_175688_12_, p_175688_14_);
/*      */   }
/*      */ 
/*      */   
/*      */   public void spawnParticle(EnumParticleTypes p_175682_1_, boolean p_175682_2_, double p_175682_3_, double p_175682_5_, double p_175682_7_, double p_175682_9_, double p_175682_11_, double p_175682_13_, int... p_175682_15_) {
/* 1124 */     spawnParticle(p_175682_1_.func_179348_c(), p_175682_1_.func_179344_e() | p_175682_2_, p_175682_3_, p_175682_5_, p_175682_7_, p_175682_9_, p_175682_11_, p_175682_13_, p_175682_15_);
/*      */   }
/*      */ 
/*      */   
/*      */   private void spawnParticle(int p_175720_1_, boolean p_175720_2_, double p_175720_3_, double p_175720_5_, double p_175720_7_, double p_175720_9_, double p_175720_11_, double p_175720_13_, int... p_175720_15_) {
/* 1129 */     for (int var16 = 0; var16 < this.worldAccesses.size(); var16++)
/*      */     {
/* 1131 */       ((IWorldAccess)this.worldAccesses.get(var16)).func_180442_a(p_175720_1_, p_175720_2_, p_175720_3_, p_175720_5_, p_175720_7_, p_175720_9_, p_175720_11_, p_175720_13_, p_175720_15_);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean addWeatherEffect(Entity p_72942_1_) {
/* 1140 */     this.weatherEffects.add(p_72942_1_);
/* 1141 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean spawnEntityInWorld(Entity p_72838_1_) {
/* 1149 */     int var2 = MathHelper.floor_double(p_72838_1_.posX / 16.0D);
/* 1150 */     int var3 = MathHelper.floor_double(p_72838_1_.posZ / 16.0D);
/* 1151 */     boolean var4 = p_72838_1_.forceSpawn;
/*      */     
/* 1153 */     if (p_72838_1_ instanceof EntityPlayer)
/*      */     {
/* 1155 */       var4 = true;
/*      */     }
/*      */     
/* 1158 */     if (!var4 && !isChunkLoaded(var2, var3, true))
/*      */     {
/* 1160 */       return false;
/*      */     }
/*      */ 
/*      */     
/* 1164 */     if (p_72838_1_ instanceof EntityPlayer) {
/*      */       
/* 1166 */       EntityPlayer var5 = (EntityPlayer)p_72838_1_;
/* 1167 */       this.playerEntities.add(var5);
/* 1168 */       updateAllPlayersSleepingFlag();
/*      */     } 
/*      */     
/* 1171 */     getChunkFromChunkCoords(var2, var3).addEntity(p_72838_1_);
/* 1172 */     this.loadedEntityList.add(p_72838_1_);
/* 1173 */     onEntityAdded(p_72838_1_);
/* 1174 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected void onEntityAdded(Entity p_72923_1_) {
/* 1180 */     for (int var2 = 0; var2 < this.worldAccesses.size(); var2++)
/*      */     {
/* 1182 */       ((IWorldAccess)this.worldAccesses.get(var2)).onEntityAdded(p_72923_1_);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   protected void onEntityRemoved(Entity p_72847_1_) {
/* 1188 */     for (int var2 = 0; var2 < this.worldAccesses.size(); var2++)
/*      */     {
/* 1190 */       ((IWorldAccess)this.worldAccesses.get(var2)).onEntityRemoved(p_72847_1_);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void removeEntity(Entity p_72900_1_) {
/* 1199 */     if (p_72900_1_.riddenByEntity != null)
/*      */     {
/* 1201 */       p_72900_1_.riddenByEntity.mountEntity(null);
/*      */     }
/*      */     
/* 1204 */     if (p_72900_1_.ridingEntity != null)
/*      */     {
/* 1206 */       p_72900_1_.mountEntity(null);
/*      */     }
/*      */     
/* 1209 */     p_72900_1_.setDead();
/*      */     
/* 1211 */     if (p_72900_1_ instanceof EntityPlayer) {
/*      */       
/* 1213 */       this.playerEntities.remove(p_72900_1_);
/* 1214 */       updateAllPlayersSleepingFlag();
/* 1215 */       onEntityRemoved(p_72900_1_);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void removePlayerEntityDangerously(Entity p_72973_1_) {
/* 1224 */     p_72973_1_.setDead();
/*      */     
/* 1226 */     if (p_72973_1_ instanceof EntityPlayer) {
/*      */       
/* 1228 */       this.playerEntities.remove(p_72973_1_);
/* 1229 */       updateAllPlayersSleepingFlag();
/*      */     } 
/*      */     
/* 1232 */     int var2 = p_72973_1_.chunkCoordX;
/* 1233 */     int var3 = p_72973_1_.chunkCoordZ;
/*      */     
/* 1235 */     if (p_72973_1_.addedToChunk && isChunkLoaded(var2, var3, true))
/*      */     {
/* 1237 */       getChunkFromChunkCoords(var2, var3).removeEntity(p_72973_1_);
/*      */     }
/*      */     
/* 1240 */     this.loadedEntityList.remove(p_72973_1_);
/* 1241 */     onEntityRemoved(p_72973_1_);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void addWorldAccess(IWorldAccess p_72954_1_) {
/* 1249 */     this.worldAccesses.add(p_72954_1_);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void removeWorldAccess(IWorldAccess p_72848_1_) {
/* 1257 */     this.worldAccesses.remove(p_72848_1_);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public List getCollidingBoundingBoxes(Entity p_72945_1_, AxisAlignedBB p_72945_2_) {
/* 1266 */     ArrayList<AxisAlignedBB> var3 = Lists.newArrayList();
/* 1267 */     int var4 = MathHelper.floor_double(p_72945_2_.minX);
/* 1268 */     int var5 = MathHelper.floor_double(p_72945_2_.maxX + 1.0D);
/* 1269 */     int var6 = MathHelper.floor_double(p_72945_2_.minY);
/* 1270 */     int var7 = MathHelper.floor_double(p_72945_2_.maxY + 1.0D);
/* 1271 */     int var8 = MathHelper.floor_double(p_72945_2_.minZ);
/* 1272 */     int var9 = MathHelper.floor_double(p_72945_2_.maxZ + 1.0D);
/*      */     
/* 1274 */     for (int var10 = var4; var10 < var5; var10++) {
/*      */       
/* 1276 */       for (int var11 = var8; var11 < var9; var11++) {
/*      */         
/* 1278 */         if (isBlockLoaded(new BlockPos(var10, 64, var11)))
/*      */         {
/* 1280 */           for (int var12 = var6 - 1; var12 < var7; var12++) {
/*      */             IBlockState var16;
/* 1282 */             BlockPos var13 = new BlockPos(var10, var12, var11);
/* 1283 */             boolean var14 = p_72945_1_.isOutsideBorder();
/* 1284 */             boolean var15 = isInsideBorder(getWorldBorder(), p_72945_1_);
/*      */             
/* 1286 */             if (var14 && var15) {
/*      */               
/* 1288 */               p_72945_1_.setOutsideBorder(false);
/*      */             }
/* 1290 */             else if (!var14 && !var15) {
/*      */               
/* 1292 */               p_72945_1_.setOutsideBorder(true);
/*      */             } 
/*      */ 
/*      */ 
/*      */             
/* 1297 */             if (!getWorldBorder().contains(var13) && var15) {
/*      */               
/* 1299 */               var16 = Blocks.stone.getDefaultState();
/*      */             }
/*      */             else {
/*      */               
/* 1303 */               var16 = getBlockState(var13);
/*      */             } 
/*      */             
/* 1306 */             var16.getBlock().addCollisionBoxesToList(this, var13, var16, p_72945_2_, var3, p_72945_1_);
/*      */           } 
/*      */         }
/*      */       } 
/*      */     } 
/*      */     
/* 1312 */     double var17 = 0.25D;
/* 1313 */     List<Entity> var18 = getEntitiesWithinAABBExcludingEntity(p_72945_1_, p_72945_2_.expand(var17, var17, var17));
/*      */     
/* 1315 */     for (int var19 = 0; var19 < var18.size(); var19++) {
/*      */       
/* 1317 */       if (p_72945_1_.riddenByEntity != var18 && p_72945_1_.ridingEntity != var18) {
/*      */         
/* 1319 */         AxisAlignedBB var20 = ((Entity)var18.get(var19)).getBoundingBox();
/*      */         
/* 1321 */         if (var20 != null && var20.intersectsWith(p_72945_2_))
/*      */         {
/* 1323 */           var3.add(var20);
/*      */         }
/*      */         
/* 1326 */         var20 = p_72945_1_.getCollisionBox(var18.get(var19));
/*      */         
/* 1328 */         if (var20 != null && var20.intersectsWith(p_72945_2_))
/*      */         {
/* 1330 */           var3.add(var20);
/*      */         }
/*      */       } 
/*      */     } 
/*      */     
/* 1335 */     return var3;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isInsideBorder(WorldBorder p_175673_1_, Entity p_175673_2_) {
/* 1340 */     double var3 = p_175673_1_.minX();
/* 1341 */     double var5 = p_175673_1_.minZ();
/* 1342 */     double var7 = p_175673_1_.maxX();
/* 1343 */     double var9 = p_175673_1_.maxZ();
/*      */     
/* 1345 */     if (p_175673_2_.isOutsideBorder()) {
/*      */       
/* 1347 */       var3++;
/* 1348 */       var5++;
/* 1349 */       var7--;
/* 1350 */       var9--;
/*      */     }
/*      */     else {
/*      */       
/* 1354 */       var3--;
/* 1355 */       var5--;
/* 1356 */       var7++;
/* 1357 */       var9++;
/*      */     } 
/*      */     
/* 1360 */     return (p_175673_2_.posX > var3 && p_175673_2_.posX < var7 && p_175673_2_.posZ > var5 && p_175673_2_.posZ < var9);
/*      */   }
/*      */ 
/*      */   
/*      */   public List func_147461_a(AxisAlignedBB p_147461_1_) {
/* 1365 */     ArrayList var2 = Lists.newArrayList();
/* 1366 */     int var3 = MathHelper.floor_double(p_147461_1_.minX);
/* 1367 */     int var4 = MathHelper.floor_double(p_147461_1_.maxX + 1.0D);
/* 1368 */     int var5 = MathHelper.floor_double(p_147461_1_.minY);
/* 1369 */     int var6 = MathHelper.floor_double(p_147461_1_.maxY + 1.0D);
/* 1370 */     int var7 = MathHelper.floor_double(p_147461_1_.minZ);
/* 1371 */     int var8 = MathHelper.floor_double(p_147461_1_.maxZ + 1.0D);
/*      */     
/* 1373 */     for (int var9 = var3; var9 < var4; var9++) {
/*      */       
/* 1375 */       for (int var10 = var7; var10 < var8; var10++) {
/*      */         
/* 1377 */         if (isBlockLoaded(new BlockPos(var9, 64, var10)))
/*      */         {
/* 1379 */           for (int var11 = var5 - 1; var11 < var6; var11++) {
/*      */             IBlockState var12;
/* 1381 */             BlockPos var13 = new BlockPos(var9, var11, var10);
/*      */ 
/*      */             
/* 1384 */             if (var9 >= -30000000 && var9 < 30000000 && var10 >= -30000000 && var10 < 30000000) {
/*      */               
/* 1386 */               var12 = getBlockState(var13);
/*      */             }
/*      */             else {
/*      */               
/* 1390 */               var12 = Blocks.bedrock.getDefaultState();
/*      */             } 
/*      */             
/* 1393 */             var12.getBlock().addCollisionBoxesToList(this, var13, var12, p_147461_1_, var2, null);
/*      */           } 
/*      */         }
/*      */       } 
/*      */     } 
/*      */     
/* 1399 */     return var2;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int calculateSkylightSubtracted(float p_72967_1_) {
/* 1407 */     float var2 = getCelestialAngle(p_72967_1_);
/* 1408 */     float var3 = 1.0F - MathHelper.cos(var2 * 3.1415927F * 2.0F) * 2.0F + 0.5F;
/* 1409 */     var3 = MathHelper.clamp_float(var3, 0.0F, 1.0F);
/* 1410 */     var3 = 1.0F - var3;
/* 1411 */     var3 = (float)(var3 * (1.0D - (getRainStrength(p_72967_1_) * 5.0F) / 16.0D));
/* 1412 */     var3 = (float)(var3 * (1.0D - (getWeightedThunderStrength(p_72967_1_) * 5.0F) / 16.0D));
/* 1413 */     var3 = 1.0F - var3;
/* 1414 */     return (int)(var3 * 11.0F);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public float getSunBrightness(float p_72971_1_) {
/* 1422 */     float var2 = getCelestialAngle(p_72971_1_);
/* 1423 */     float var3 = 1.0F - MathHelper.cos(var2 * 3.1415927F * 2.0F) * 2.0F + 0.2F;
/* 1424 */     var3 = MathHelper.clamp_float(var3, 0.0F, 1.0F);
/* 1425 */     var3 = 1.0F - var3;
/* 1426 */     var3 = (float)(var3 * (1.0D - (getRainStrength(p_72971_1_) * 5.0F) / 16.0D));
/* 1427 */     var3 = (float)(var3 * (1.0D - (getWeightedThunderStrength(p_72971_1_) * 5.0F) / 16.0D));
/* 1428 */     return var3 * 0.8F + 0.2F;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Vec3 getSkyColor(Entity p_72833_1_, float p_72833_2_) {
/* 1436 */     float var3 = getCelestialAngle(p_72833_2_);
/* 1437 */     float var4 = MathHelper.cos(var3 * 3.1415927F * 2.0F) * 2.0F + 0.5F;
/* 1438 */     var4 = MathHelper.clamp_float(var4, 0.0F, 1.0F);
/* 1439 */     int var5 = MathHelper.floor_double(p_72833_1_.posX);
/* 1440 */     int var6 = MathHelper.floor_double(p_72833_1_.posY);
/* 1441 */     int var7 = MathHelper.floor_double(p_72833_1_.posZ);
/* 1442 */     BlockPos var8 = new BlockPos(var5, var6, var7);
/* 1443 */     BiomeGenBase var9 = getBiomeGenForCoords(var8);
/* 1444 */     float var10 = var9.func_180626_a(var8);
/* 1445 */     int var11 = var9.getSkyColorByTemp(var10);
/* 1446 */     float var12 = (var11 >> 16 & 0xFF) / 255.0F;
/* 1447 */     float var13 = (var11 >> 8 & 0xFF) / 255.0F;
/* 1448 */     float var14 = (var11 & 0xFF) / 255.0F;
/* 1449 */     var12 *= var4;
/* 1450 */     var13 *= var4;
/* 1451 */     var14 *= var4;
/* 1452 */     float var15 = getRainStrength(p_72833_2_);
/*      */ 
/*      */ 
/*      */     
/* 1456 */     if (var15 > 0.0F) {
/*      */       
/* 1458 */       float f1 = (var12 * 0.3F + var13 * 0.59F + var14 * 0.11F) * 0.6F;
/* 1459 */       float var17 = 1.0F - var15 * 0.75F;
/* 1460 */       var12 = var12 * var17 + f1 * (1.0F - var17);
/* 1461 */       var13 = var13 * var17 + f1 * (1.0F - var17);
/* 1462 */       var14 = var14 * var17 + f1 * (1.0F - var17);
/*      */     } 
/*      */     
/* 1465 */     float var16 = getWeightedThunderStrength(p_72833_2_);
/*      */     
/* 1467 */     if (var16 > 0.0F) {
/*      */       
/* 1469 */       float var17 = (var12 * 0.3F + var13 * 0.59F + var14 * 0.11F) * 0.2F;
/* 1470 */       float var18 = 1.0F - var16 * 0.75F;
/* 1471 */       var12 = var12 * var18 + var17 * (1.0F - var18);
/* 1472 */       var13 = var13 * var18 + var17 * (1.0F - var18);
/* 1473 */       var14 = var14 * var18 + var17 * (1.0F - var18);
/*      */     } 
/*      */     
/* 1476 */     if (this.lastLightningBolt > 0) {
/*      */       
/* 1478 */       float var17 = this.lastLightningBolt - p_72833_2_;
/*      */       
/* 1480 */       if (var17 > 1.0F)
/*      */       {
/* 1482 */         var17 = 1.0F;
/*      */       }
/*      */       
/* 1485 */       var17 *= 0.45F;
/* 1486 */       var12 = var12 * (1.0F - var17) + 0.8F * var17;
/* 1487 */       var13 = var13 * (1.0F - var17) + 0.8F * var17;
/* 1488 */       var14 = var14 * (1.0F - var17) + 1.0F * var17;
/*      */     } 
/*      */     
/* 1491 */     return new Vec3(var12, var13, var14);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public float getCelestialAngle(float p_72826_1_) {
/* 1499 */     return this.provider.calculateCelestialAngle(this.worldInfo.getWorldTime(), p_72826_1_);
/*      */   }
/*      */ 
/*      */   
/*      */   public int getMoonPhase() {
/* 1504 */     return this.provider.getMoonPhase(this.worldInfo.getWorldTime());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public float getCurrentMoonPhaseFactor() {
/* 1512 */     return WorldProvider.moonPhaseFactors[this.provider.getMoonPhase(this.worldInfo.getWorldTime())];
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public float getCelestialAngleRadians(float p_72929_1_) {
/* 1520 */     float var2 = getCelestialAngle(p_72929_1_);
/* 1521 */     return var2 * 3.1415927F * 2.0F;
/*      */   }
/*      */ 
/*      */   
/*      */   public Vec3 getCloudColour(float p_72824_1_) {
/* 1526 */     float var2 = getCelestialAngle(p_72824_1_);
/* 1527 */     float var3 = MathHelper.cos(var2 * 3.1415927F * 2.0F) * 2.0F + 0.5F;
/* 1528 */     var3 = MathHelper.clamp_float(var3, 0.0F, 1.0F);
/* 1529 */     float var4 = (float)(this.cloudColour >> 16L & 0xFFL) / 255.0F;
/* 1530 */     float var5 = (float)(this.cloudColour >> 8L & 0xFFL) / 255.0F;
/* 1531 */     float var6 = (float)(this.cloudColour & 0xFFL) / 255.0F;
/* 1532 */     float var7 = getRainStrength(p_72824_1_);
/*      */ 
/*      */ 
/*      */     
/* 1536 */     if (var7 > 0.0F) {
/*      */       
/* 1538 */       float f1 = (var4 * 0.3F + var5 * 0.59F + var6 * 0.11F) * 0.6F;
/* 1539 */       float var9 = 1.0F - var7 * 0.95F;
/* 1540 */       var4 = var4 * var9 + f1 * (1.0F - var9);
/* 1541 */       var5 = var5 * var9 + f1 * (1.0F - var9);
/* 1542 */       var6 = var6 * var9 + f1 * (1.0F - var9);
/*      */     } 
/*      */     
/* 1545 */     var4 *= var3 * 0.9F + 0.1F;
/* 1546 */     var5 *= var3 * 0.9F + 0.1F;
/* 1547 */     var6 *= var3 * 0.85F + 0.15F;
/* 1548 */     float var8 = getWeightedThunderStrength(p_72824_1_);
/*      */     
/* 1550 */     if (var8 > 0.0F) {
/*      */       
/* 1552 */       float var9 = (var4 * 0.3F + var5 * 0.59F + var6 * 0.11F) * 0.2F;
/* 1553 */       float var10 = 1.0F - var8 * 0.95F;
/* 1554 */       var4 = var4 * var10 + var9 * (1.0F - var10);
/* 1555 */       var5 = var5 * var10 + var9 * (1.0F - var10);
/* 1556 */       var6 = var6 * var10 + var9 * (1.0F - var10);
/*      */     } 
/*      */     
/* 1559 */     return new Vec3(var4, var5, var6);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Vec3 getFogColor(float p_72948_1_) {
/* 1567 */     float var2 = getCelestialAngle(p_72948_1_);
/* 1568 */     return this.provider.getFogColor(var2, p_72948_1_);
/*      */   }
/*      */ 
/*      */   
/*      */   public BlockPos func_175725_q(BlockPos p_175725_1_) {
/* 1573 */     return getChunkFromBlockCoords(p_175725_1_).func_177440_h(p_175725_1_);
/*      */   }
/*      */ 
/*      */   
/*      */   public BlockPos func_175672_r(BlockPos p_175672_1_) {
/* 1578 */     Chunk var2 = getChunkFromBlockCoords(p_175672_1_);
/*      */ 
/*      */ 
/*      */     
/* 1582 */     for (BlockPos var3 = new BlockPos(p_175672_1_.getX(), var2.getTopFilledSegment() + 16, p_175672_1_.getZ()); var3.getY() >= 0; var3 = var4) {
/*      */       
/* 1584 */       BlockPos var4 = var3.offsetDown();
/* 1585 */       Material var5 = var2.getBlock(var4).getMaterial();
/*      */       
/* 1587 */       if (var5.blocksMovement() && var5 != Material.leaves) {
/*      */         break;
/*      */       }
/*      */     } 
/*      */ 
/*      */     
/* 1593 */     return var3;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public float getStarBrightness(float p_72880_1_) {
/* 1601 */     float var2 = getCelestialAngle(p_72880_1_);
/* 1602 */     float var3 = 1.0F - MathHelper.cos(var2 * 3.1415927F * 2.0F) * 2.0F + 0.25F;
/* 1603 */     var3 = MathHelper.clamp_float(var3, 0.0F, 1.0F);
/* 1604 */     return var3 * var3 * 0.5F;
/*      */   }
/*      */ 
/*      */   
/*      */   public void scheduleUpdate(BlockPos pos, Block blockIn, int delay) {}
/*      */ 
/*      */   
/*      */   public void func_175654_a(BlockPos p_175654_1_, Block p_175654_2_, int p_175654_3_, int p_175654_4_) {}
/*      */ 
/*      */   
/*      */   public void func_180497_b(BlockPos p_180497_1_, Block p_180497_2_, int p_180497_3_, int p_180497_4_) {}
/*      */ 
/*      */   
/*      */   public void updateEntities() {
/* 1618 */     this.theProfiler.startSection("entities");
/* 1619 */     this.theProfiler.startSection("global");
/*      */ 
/*      */     
/*      */     int var1;
/*      */ 
/*      */     
/* 1625 */     for (var1 = 0; var1 < this.weatherEffects.size(); var1++) {
/*      */       
/* 1627 */       Entity var2 = this.weatherEffects.get(var1);
/*      */ 
/*      */       
/*      */       try {
/* 1631 */         var2.ticksExisted++;
/* 1632 */         var2.onUpdate();
/*      */       }
/* 1634 */       catch (Throwable var9) {
/*      */         
/* 1636 */         CrashReport var4 = CrashReport.makeCrashReport(var9, "Ticking entity");
/* 1637 */         CrashReportCategory var5 = var4.makeCategory("Entity being ticked");
/*      */         
/* 1639 */         if (var2 == null) {
/*      */           
/* 1641 */           var5.addCrashSection("Entity", "~~NULL~~");
/*      */         }
/*      */         else {
/*      */           
/* 1645 */           var2.addEntityCrashInfo(var5);
/*      */         } 
/*      */         
/* 1648 */         throw new ReportedException(var4);
/*      */       } 
/*      */       
/* 1651 */       if (var2.isDead)
/*      */       {
/* 1653 */         this.weatherEffects.remove(var1--);
/*      */       }
/*      */     } 
/*      */     
/* 1657 */     this.theProfiler.endStartSection("remove");
/* 1658 */     this.loadedEntityList.removeAll(this.unloadedEntityList);
/*      */ 
/*      */ 
/*      */     
/* 1662 */     for (var1 = 0; var1 < this.unloadedEntityList.size(); var1++) {
/*      */       
/* 1664 */       Entity var2 = this.unloadedEntityList.get(var1);
/* 1665 */       int var3 = var2.chunkCoordX;
/* 1666 */       int var15 = var2.chunkCoordZ;
/*      */       
/* 1668 */       if (var2.addedToChunk && isChunkLoaded(var3, var15, true))
/*      */       {
/* 1670 */         getChunkFromChunkCoords(var3, var15).removeEntity(var2);
/*      */       }
/*      */     } 
/*      */     
/* 1674 */     for (var1 = 0; var1 < this.unloadedEntityList.size(); var1++)
/*      */     {
/* 1676 */       onEntityRemoved(this.unloadedEntityList.get(var1));
/*      */     }
/*      */     
/* 1679 */     this.unloadedEntityList.clear();
/* 1680 */     this.theProfiler.endStartSection("regular");
/*      */     
/* 1682 */     for (var1 = 0; var1 < this.loadedEntityList.size(); var1++) {
/*      */       
/* 1684 */       Entity var2 = this.loadedEntityList.get(var1);
/*      */       
/* 1686 */       if (var2.ridingEntity != null) {
/*      */         
/* 1688 */         if (!var2.ridingEntity.isDead && var2.ridingEntity.riddenByEntity == var2) {
/*      */           continue;
/*      */         }
/*      */ 
/*      */         
/* 1693 */         var2.ridingEntity.riddenByEntity = null;
/* 1694 */         var2.ridingEntity = null;
/*      */       } 
/*      */       
/* 1697 */       this.theProfiler.startSection("tick");
/*      */       
/* 1699 */       if (!var2.isDead) {
/*      */         
/*      */         try {
/*      */           
/* 1703 */           updateEntity(var2);
/*      */         }
/* 1705 */         catch (Throwable var8) {
/*      */           
/* 1707 */           CrashReport var4 = CrashReport.makeCrashReport(var8, "Ticking entity");
/* 1708 */           CrashReportCategory var5 = var4.makeCategory("Entity being ticked");
/* 1709 */           var2.addEntityCrashInfo(var5);
/* 1710 */           throw new ReportedException(var4);
/*      */         } 
/*      */       }
/*      */       
/* 1714 */       this.theProfiler.endSection();
/* 1715 */       this.theProfiler.startSection("remove");
/*      */       
/* 1717 */       if (var2.isDead) {
/*      */         
/* 1719 */         int var3 = var2.chunkCoordX;
/* 1720 */         int var15 = var2.chunkCoordZ;
/*      */         
/* 1722 */         if (var2.addedToChunk && isChunkLoaded(var3, var15, true))
/*      */         {
/* 1724 */           getChunkFromChunkCoords(var3, var15).removeEntity(var2);
/*      */         }
/*      */         
/* 1727 */         this.loadedEntityList.remove(var1--);
/* 1728 */         onEntityRemoved(var2);
/*      */       } 
/*      */       
/* 1731 */       this.theProfiler.endSection();
/*      */       continue;
/*      */     } 
/* 1734 */     this.theProfiler.endStartSection("blockEntities");
/* 1735 */     this.processingLoadedTiles = true;
/* 1736 */     Iterator<TileEntity> var10 = this.tickableTileEntities.iterator();
/*      */     
/* 1738 */     while (var10.hasNext()) {
/*      */       
/* 1740 */       TileEntity var11 = var10.next();
/*      */       
/* 1742 */       if (!var11.isInvalid() && var11.hasWorldObj()) {
/*      */         
/* 1744 */         BlockPos var13 = var11.getPos();
/*      */         
/* 1746 */         if (isBlockLoaded(var13) && this.worldBorder.contains(var13)) {
/*      */           
/*      */           try {
/*      */             
/* 1750 */             ((IUpdatePlayerListBox)var11).update();
/*      */           }
/* 1752 */           catch (Throwable var7) {
/*      */             
/* 1754 */             CrashReport var16 = CrashReport.makeCrashReport(var7, "Ticking block entity");
/* 1755 */             CrashReportCategory var6 = var16.makeCategory("Block entity being ticked");
/* 1756 */             var11.addInfoToCrashReport(var6);
/* 1757 */             throw new ReportedException(var16);
/*      */           } 
/*      */         }
/*      */       } 
/*      */       
/* 1762 */       if (var11.isInvalid()) {
/*      */         
/* 1764 */         var10.remove();
/* 1765 */         this.loadedTileEntityList.remove(var11);
/*      */         
/* 1767 */         if (isBlockLoaded(var11.getPos()))
/*      */         {
/* 1769 */           getChunkFromBlockCoords(var11.getPos()).removeTileEntity(var11.getPos());
/*      */         }
/*      */       } 
/*      */     } 
/*      */     
/* 1774 */     this.processingLoadedTiles = false;
/*      */     
/* 1776 */     if (!this.tileEntitiesToBeRemoved.isEmpty()) {
/*      */       
/* 1778 */       this.tickableTileEntities.removeAll(this.tileEntitiesToBeRemoved);
/* 1779 */       this.loadedTileEntityList.removeAll(this.tileEntitiesToBeRemoved);
/* 1780 */       this.tileEntitiesToBeRemoved.clear();
/*      */     } 
/*      */     
/* 1783 */     this.theProfiler.endStartSection("pendingBlockEntities");
/*      */     
/* 1785 */     if (!this.addedTileEntityList.isEmpty()) {
/*      */       
/* 1787 */       for (int var12 = 0; var12 < this.addedTileEntityList.size(); var12++) {
/*      */         
/* 1789 */         TileEntity var14 = this.addedTileEntityList.get(var12);
/*      */         
/* 1791 */         if (!var14.isInvalid()) {
/*      */           
/* 1793 */           if (!this.loadedTileEntityList.contains(var14))
/*      */           {
/* 1795 */             addTileEntity(var14);
/*      */           }
/*      */           
/* 1798 */           if (isBlockLoaded(var14.getPos()))
/*      */           {
/* 1800 */             getChunkFromBlockCoords(var14.getPos()).addTileEntity(var14.getPos(), var14);
/*      */           }
/*      */           
/* 1803 */           markBlockForUpdate(var14.getPos());
/*      */         } 
/*      */       } 
/*      */       
/* 1807 */       this.addedTileEntityList.clear();
/*      */     } 
/*      */     
/* 1810 */     this.theProfiler.endSection();
/* 1811 */     this.theProfiler.endSection();
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean addTileEntity(TileEntity tile) {
/* 1816 */     boolean var2 = this.loadedTileEntityList.add(tile);
/*      */     
/* 1818 */     if (var2 && tile instanceof IUpdatePlayerListBox)
/*      */     {
/* 1820 */       this.tickableTileEntities.add(tile);
/*      */     }
/*      */     
/* 1823 */     return var2;
/*      */   }
/*      */ 
/*      */   
/*      */   public void addTileEntities(Collection tileEntityCollection) {
/* 1828 */     if (this.processingLoadedTiles) {
/*      */       
/* 1830 */       this.addedTileEntityList.addAll(tileEntityCollection);
/*      */     }
/*      */     else {
/*      */       
/* 1834 */       Iterator<TileEntity> var2 = tileEntityCollection.iterator();
/*      */       
/* 1836 */       while (var2.hasNext()) {
/*      */         
/* 1838 */         TileEntity var3 = var2.next();
/* 1839 */         this.loadedTileEntityList.add(var3);
/*      */         
/* 1841 */         if (var3 instanceof IUpdatePlayerListBox)
/*      */         {
/* 1843 */           this.tickableTileEntities.add(var3);
/*      */         }
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void updateEntity(Entity ent) {
/* 1854 */     updateEntityWithOptionalForce(ent, true);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void updateEntityWithOptionalForce(Entity p_72866_1_, boolean p_72866_2_) {
/* 1863 */     int var3 = MathHelper.floor_double(p_72866_1_.posX);
/* 1864 */     int var4 = MathHelper.floor_double(p_72866_1_.posZ);
/* 1865 */     byte var5 = 32;
/*      */     
/* 1867 */     if (!p_72866_2_ || isAreaLoaded(var3 - var5, 0, var4 - var5, var3 + var5, 0, var4 + var5, true)) {
/*      */       
/* 1869 */       p_72866_1_.lastTickPosX = p_72866_1_.posX;
/* 1870 */       p_72866_1_.lastTickPosY = p_72866_1_.posY;
/* 1871 */       p_72866_1_.lastTickPosZ = p_72866_1_.posZ;
/* 1872 */       p_72866_1_.prevRotationYaw = p_72866_1_.rotationYaw;
/* 1873 */       p_72866_1_.prevRotationPitch = p_72866_1_.rotationPitch;
/*      */       
/* 1875 */       if (p_72866_2_ && p_72866_1_.addedToChunk) {
/*      */         
/* 1877 */         p_72866_1_.ticksExisted++;
/*      */         
/* 1879 */         if (p_72866_1_.ridingEntity != null) {
/*      */           
/* 1881 */           p_72866_1_.updateRidden();
/*      */         }
/*      */         else {
/*      */           
/* 1885 */           p_72866_1_.onUpdate();
/*      */         } 
/*      */       } 
/*      */       
/* 1889 */       this.theProfiler.startSection("chunkCheck");
/*      */       
/* 1891 */       if (Double.isNaN(p_72866_1_.posX) || Double.isInfinite(p_72866_1_.posX))
/*      */       {
/* 1893 */         p_72866_1_.posX = p_72866_1_.lastTickPosX;
/*      */       }
/*      */       
/* 1896 */       if (Double.isNaN(p_72866_1_.posY) || Double.isInfinite(p_72866_1_.posY))
/*      */       {
/* 1898 */         p_72866_1_.posY = p_72866_1_.lastTickPosY;
/*      */       }
/*      */       
/* 1901 */       if (Double.isNaN(p_72866_1_.posZ) || Double.isInfinite(p_72866_1_.posZ))
/*      */       {
/* 1903 */         p_72866_1_.posZ = p_72866_1_.lastTickPosZ;
/*      */       }
/*      */       
/* 1906 */       if (Double.isNaN(p_72866_1_.rotationPitch) || Double.isInfinite(p_72866_1_.rotationPitch))
/*      */       {
/* 1908 */         p_72866_1_.rotationPitch = p_72866_1_.prevRotationPitch;
/*      */       }
/*      */       
/* 1911 */       if (Double.isNaN(p_72866_1_.rotationYaw) || Double.isInfinite(p_72866_1_.rotationYaw))
/*      */       {
/* 1913 */         p_72866_1_.rotationYaw = p_72866_1_.prevRotationYaw;
/*      */       }
/*      */       
/* 1916 */       int var6 = MathHelper.floor_double(p_72866_1_.posX / 16.0D);
/* 1917 */       int var7 = MathHelper.floor_double(p_72866_1_.posY / 16.0D);
/* 1918 */       int var8 = MathHelper.floor_double(p_72866_1_.posZ / 16.0D);
/*      */       
/* 1920 */       if (!p_72866_1_.addedToChunk || p_72866_1_.chunkCoordX != var6 || p_72866_1_.chunkCoordY != var7 || p_72866_1_.chunkCoordZ != var8) {
/*      */         
/* 1922 */         if (p_72866_1_.addedToChunk && isChunkLoaded(p_72866_1_.chunkCoordX, p_72866_1_.chunkCoordZ, true))
/*      */         {
/* 1924 */           getChunkFromChunkCoords(p_72866_1_.chunkCoordX, p_72866_1_.chunkCoordZ).removeEntityAtIndex(p_72866_1_, p_72866_1_.chunkCoordY);
/*      */         }
/*      */         
/* 1927 */         if (isChunkLoaded(var6, var8, true)) {
/*      */           
/* 1929 */           p_72866_1_.addedToChunk = true;
/* 1930 */           getChunkFromChunkCoords(var6, var8).addEntity(p_72866_1_);
/*      */         }
/*      */         else {
/*      */           
/* 1934 */           p_72866_1_.addedToChunk = false;
/*      */         } 
/*      */       } 
/*      */       
/* 1938 */       this.theProfiler.endSection();
/*      */       
/* 1940 */       if (p_72866_2_ && p_72866_1_.addedToChunk && p_72866_1_.riddenByEntity != null)
/*      */       {
/* 1942 */         if (!p_72866_1_.riddenByEntity.isDead && p_72866_1_.riddenByEntity.ridingEntity == p_72866_1_) {
/*      */           
/* 1944 */           updateEntity(p_72866_1_.riddenByEntity);
/*      */         }
/*      */         else {
/*      */           
/* 1948 */           p_72866_1_.riddenByEntity.ridingEntity = null;
/* 1949 */           p_72866_1_.riddenByEntity = null;
/*      */         } 
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean checkNoEntityCollision(AxisAlignedBB p_72855_1_) {
/* 1960 */     return checkNoEntityCollision(p_72855_1_, null);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean checkNoEntityCollision(AxisAlignedBB p_72917_1_, Entity p_72917_2_) {
/* 1968 */     List<Entity> var3 = getEntitiesWithinAABBExcludingEntity(null, p_72917_1_);
/*      */     
/* 1970 */     for (int var4 = 0; var4 < var3.size(); var4++) {
/*      */       
/* 1972 */       Entity var5 = var3.get(var4);
/*      */       
/* 1974 */       if (!var5.isDead && var5.preventEntitySpawning && var5 != p_72917_2_ && (p_72917_2_ == null || (p_72917_2_.ridingEntity != var5 && p_72917_2_.riddenByEntity != var5)))
/*      */       {
/* 1976 */         return false;
/*      */       }
/*      */     } 
/*      */     
/* 1980 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean checkBlockCollision(AxisAlignedBB p_72829_1_) {
/* 1988 */     int var2 = MathHelper.floor_double(p_72829_1_.minX);
/* 1989 */     int var3 = MathHelper.floor_double(p_72829_1_.maxX);
/* 1990 */     int var4 = MathHelper.floor_double(p_72829_1_.minY);
/* 1991 */     int var5 = MathHelper.floor_double(p_72829_1_.maxY);
/* 1992 */     int var6 = MathHelper.floor_double(p_72829_1_.minZ);
/* 1993 */     int var7 = MathHelper.floor_double(p_72829_1_.maxZ);
/*      */     
/* 1995 */     for (int var8 = var2; var8 <= var3; var8++) {
/*      */       
/* 1997 */       for (int var9 = var4; var9 <= var5; var9++) {
/*      */         
/* 1999 */         for (int var10 = var6; var10 <= var7; var10++) {
/*      */           
/* 2001 */           Block var11 = getBlockState(new BlockPos(var8, var9, var10)).getBlock();
/*      */           
/* 2003 */           if (var11.getMaterial() != Material.air)
/*      */           {
/* 2005 */             return true;
/*      */           }
/*      */         } 
/*      */       } 
/*      */     } 
/*      */     
/* 2011 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isAnyLiquid(AxisAlignedBB p_72953_1_) {
/* 2019 */     int var2 = MathHelper.floor_double(p_72953_1_.minX);
/* 2020 */     int var3 = MathHelper.floor_double(p_72953_1_.maxX);
/* 2021 */     int var4 = MathHelper.floor_double(p_72953_1_.minY);
/* 2022 */     int var5 = MathHelper.floor_double(p_72953_1_.maxY);
/* 2023 */     int var6 = MathHelper.floor_double(p_72953_1_.minZ);
/* 2024 */     int var7 = MathHelper.floor_double(p_72953_1_.maxZ);
/*      */     
/* 2026 */     for (int var8 = var2; var8 <= var3; var8++) {
/*      */       
/* 2028 */       for (int var9 = var4; var9 <= var5; var9++) {
/*      */         
/* 2030 */         for (int var10 = var6; var10 <= var7; var10++) {
/*      */           
/* 2032 */           Block var11 = getBlockState(new BlockPos(var8, var9, var10)).getBlock();
/*      */           
/* 2034 */           if (var11.getMaterial().isLiquid())
/*      */           {
/* 2036 */             return true;
/*      */           }
/*      */         } 
/*      */       } 
/*      */     } 
/*      */     
/* 2042 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean func_147470_e(AxisAlignedBB p_147470_1_) {
/* 2047 */     int var2 = MathHelper.floor_double(p_147470_1_.minX);
/* 2048 */     int var3 = MathHelper.floor_double(p_147470_1_.maxX + 1.0D);
/* 2049 */     int var4 = MathHelper.floor_double(p_147470_1_.minY);
/* 2050 */     int var5 = MathHelper.floor_double(p_147470_1_.maxY + 1.0D);
/* 2051 */     int var6 = MathHelper.floor_double(p_147470_1_.minZ);
/* 2052 */     int var7 = MathHelper.floor_double(p_147470_1_.maxZ + 1.0D);
/*      */     
/* 2054 */     if (isAreaLoaded(var2, var4, var6, var3, var5, var7, true))
/*      */     {
/* 2056 */       for (int var8 = var2; var8 < var3; var8++) {
/*      */         
/* 2058 */         for (int var9 = var4; var9 < var5; var9++) {
/*      */           
/* 2060 */           for (int var10 = var6; var10 < var7; var10++) {
/*      */             
/* 2062 */             Block var11 = getBlockState(new BlockPos(var8, var9, var10)).getBlock();
/*      */             
/* 2064 */             if (var11 == Blocks.fire || var11 == Blocks.flowing_lava || var11 == Blocks.lava)
/*      */             {
/* 2066 */               return true;
/*      */             }
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     }
/*      */     
/* 2073 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean handleMaterialAcceleration(AxisAlignedBB p_72918_1_, Material p_72918_2_, Entity p_72918_3_) {
/* 2081 */     int var4 = MathHelper.floor_double(p_72918_1_.minX);
/* 2082 */     int var5 = MathHelper.floor_double(p_72918_1_.maxX + 1.0D);
/* 2083 */     int var6 = MathHelper.floor_double(p_72918_1_.minY);
/* 2084 */     int var7 = MathHelper.floor_double(p_72918_1_.maxY + 1.0D);
/* 2085 */     int var8 = MathHelper.floor_double(p_72918_1_.minZ);
/* 2086 */     int var9 = MathHelper.floor_double(p_72918_1_.maxZ + 1.0D);
/*      */     
/* 2088 */     if (!isAreaLoaded(var4, var6, var8, var5, var7, var9, true))
/*      */     {
/* 2090 */       return false;
/*      */     }
/*      */ 
/*      */     
/* 2094 */     boolean var10 = false;
/* 2095 */     Vec3 var11 = new Vec3(0.0D, 0.0D, 0.0D);
/*      */     
/* 2097 */     for (int var12 = var4; var12 < var5; var12++) {
/*      */       
/* 2099 */       for (int var13 = var6; var13 < var7; var13++) {
/*      */         
/* 2101 */         for (int var14 = var8; var14 < var9; var14++) {
/*      */           
/* 2103 */           BlockPos var15 = new BlockPos(var12, var13, var14);
/* 2104 */           IBlockState var16 = getBlockState(var15);
/* 2105 */           Block var17 = var16.getBlock();
/*      */           
/* 2107 */           if (var17.getMaterial() == p_72918_2_) {
/*      */             
/* 2109 */             double var18 = ((var13 + 1) - BlockLiquid.getLiquidHeightPercent(((Integer)var16.getValue((IProperty)BlockLiquid.LEVEL)).intValue()));
/*      */             
/* 2111 */             if (var7 >= var18) {
/*      */               
/* 2113 */               var10 = true;
/* 2114 */               var11 = var17.modifyAcceleration(this, var15, p_72918_3_, var11);
/*      */             } 
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     } 
/*      */     
/* 2121 */     if (var11.lengthVector() > 0.0D && p_72918_3_.isPushedByWater()) {
/*      */       
/* 2123 */       var11 = var11.normalize();
/* 2124 */       double var20 = 0.014D;
/* 2125 */       p_72918_3_.motionX += var11.xCoord * var20;
/* 2126 */       p_72918_3_.motionY += var11.yCoord * var20;
/* 2127 */       p_72918_3_.motionZ += var11.zCoord * var20;
/*      */     } 
/*      */     
/* 2130 */     return var10;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isMaterialInBB(AxisAlignedBB p_72875_1_, Material p_72875_2_) {
/* 2139 */     int var3 = MathHelper.floor_double(p_72875_1_.minX);
/* 2140 */     int var4 = MathHelper.floor_double(p_72875_1_.maxX + 1.0D);
/* 2141 */     int var5 = MathHelper.floor_double(p_72875_1_.minY);
/* 2142 */     int var6 = MathHelper.floor_double(p_72875_1_.maxY + 1.0D);
/* 2143 */     int var7 = MathHelper.floor_double(p_72875_1_.minZ);
/* 2144 */     int var8 = MathHelper.floor_double(p_72875_1_.maxZ + 1.0D);
/*      */     
/* 2146 */     for (int var9 = var3; var9 < var4; var9++) {
/*      */       
/* 2148 */       for (int var10 = var5; var10 < var6; var10++) {
/*      */         
/* 2150 */         for (int var11 = var7; var11 < var8; var11++) {
/*      */           
/* 2152 */           if (getBlockState(new BlockPos(var9, var10, var11)).getBlock().getMaterial() == p_72875_2_)
/*      */           {
/* 2154 */             return true;
/*      */           }
/*      */         } 
/*      */       } 
/*      */     } 
/*      */     
/* 2160 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isAABBInMaterial(AxisAlignedBB p_72830_1_, Material p_72830_2_) {
/* 2168 */     int var3 = MathHelper.floor_double(p_72830_1_.minX);
/* 2169 */     int var4 = MathHelper.floor_double(p_72830_1_.maxX + 1.0D);
/* 2170 */     int var5 = MathHelper.floor_double(p_72830_1_.minY);
/* 2171 */     int var6 = MathHelper.floor_double(p_72830_1_.maxY + 1.0D);
/* 2172 */     int var7 = MathHelper.floor_double(p_72830_1_.minZ);
/* 2173 */     int var8 = MathHelper.floor_double(p_72830_1_.maxZ + 1.0D);
/*      */     
/* 2175 */     for (int var9 = var3; var9 < var4; var9++) {
/*      */       
/* 2177 */       for (int var10 = var5; var10 < var6; var10++) {
/*      */         
/* 2179 */         for (int var11 = var7; var11 < var8; var11++) {
/*      */           
/* 2181 */           BlockPos var12 = new BlockPos(var9, var10, var11);
/* 2182 */           IBlockState var13 = getBlockState(var12);
/* 2183 */           Block var14 = var13.getBlock();
/*      */           
/* 2185 */           if (var14.getMaterial() == p_72830_2_) {
/*      */             
/* 2187 */             int var15 = ((Integer)var13.getValue((IProperty)BlockLiquid.LEVEL)).intValue();
/* 2188 */             double var16 = (var10 + 1);
/*      */             
/* 2190 */             if (var15 < 8)
/*      */             {
/* 2192 */               var16 = (var10 + 1) - var15 / 8.0D;
/*      */             }
/*      */             
/* 2195 */             if (var16 >= p_72830_1_.minY)
/*      */             {
/* 2197 */               return true;
/*      */             }
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     } 
/*      */     
/* 2204 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Explosion createExplosion(Entity p_72876_1_, double p_72876_2_, double p_72876_4_, double p_72876_6_, float p_72876_8_, boolean p_72876_9_) {
/* 2212 */     return newExplosion(p_72876_1_, p_72876_2_, p_72876_4_, p_72876_6_, p_72876_8_, false, p_72876_9_);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Explosion newExplosion(Entity p_72885_1_, double p_72885_2_, double p_72885_4_, double p_72885_6_, float p_72885_8_, boolean p_72885_9_, boolean p_72885_10_) {
/* 2220 */     Explosion var11 = new Explosion(this, p_72885_1_, p_72885_2_, p_72885_4_, p_72885_6_, p_72885_8_, p_72885_9_, p_72885_10_);
/* 2221 */     var11.doExplosionA();
/* 2222 */     var11.doExplosionB(true);
/* 2223 */     return var11;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public float getBlockDensity(Vec3 p_72842_1_, AxisAlignedBB p_72842_2_) {
/* 2231 */     double var3 = 1.0D / ((p_72842_2_.maxX - p_72842_2_.minX) * 2.0D + 1.0D);
/* 2232 */     double var5 = 1.0D / ((p_72842_2_.maxY - p_72842_2_.minY) * 2.0D + 1.0D);
/* 2233 */     double var7 = 1.0D / ((p_72842_2_.maxZ - p_72842_2_.minZ) * 2.0D + 1.0D);
/*      */     
/* 2235 */     if (var3 >= 0.0D && var5 >= 0.0D && var7 >= 0.0D) {
/*      */       
/* 2237 */       int var9 = 0;
/* 2238 */       int var10 = 0;
/*      */       
/* 2240 */       for (float var11 = 0.0F; var11 <= 1.0F; var11 = (float)(var11 + var3)) {
/*      */         
/* 2242 */         for (float var12 = 0.0F; var12 <= 1.0F; var12 = (float)(var12 + var5)) {
/*      */           
/* 2244 */           for (float var13 = 0.0F; var13 <= 1.0F; var13 = (float)(var13 + var7)) {
/*      */             
/* 2246 */             double var14 = p_72842_2_.minX + (p_72842_2_.maxX - p_72842_2_.minX) * var11;
/* 2247 */             double var16 = p_72842_2_.minY + (p_72842_2_.maxY - p_72842_2_.minY) * var12;
/* 2248 */             double var18 = p_72842_2_.minZ + (p_72842_2_.maxZ - p_72842_2_.minZ) * var13;
/*      */             
/* 2250 */             if (rayTraceBlocks(new Vec3(var14, var16, var18), p_72842_1_) == null)
/*      */             {
/* 2252 */               var9++;
/*      */             }
/*      */             
/* 2255 */             var10++;
/*      */           } 
/*      */         } 
/*      */       } 
/*      */       
/* 2260 */       return var9 / var10;
/*      */     } 
/*      */ 
/*      */     
/* 2264 */     return 0.0F;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean func_175719_a(EntityPlayer p_175719_1_, BlockPos p_175719_2_, EnumFacing p_175719_3_) {
/* 2270 */     p_175719_2_ = p_175719_2_.offset(p_175719_3_);
/*      */     
/* 2272 */     if (getBlockState(p_175719_2_).getBlock() == Blocks.fire) {
/*      */       
/* 2274 */       playAuxSFXAtEntity(p_175719_1_, 1004, p_175719_2_, 0);
/* 2275 */       setBlockToAir(p_175719_2_);
/* 2276 */       return true;
/*      */     } 
/*      */ 
/*      */     
/* 2280 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getDebugLoadedEntities() {
/* 2289 */     return "All: " + this.loadedEntityList.size();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getProviderName() {
/* 2297 */     return this.chunkProvider.makeString();
/*      */   }
/*      */ 
/*      */   
/*      */   public TileEntity getTileEntity(BlockPos pos) {
/* 2302 */     if (!isValid(pos))
/*      */     {
/* 2304 */       return null;
/*      */     }
/*      */ 
/*      */     
/* 2308 */     TileEntity var2 = null;
/*      */ 
/*      */ 
/*      */     
/* 2312 */     if (this.processingLoadedTiles)
/*      */     {
/* 2314 */       for (int var3 = 0; var3 < this.addedTileEntityList.size(); var3++) {
/*      */         
/* 2316 */         TileEntity var4 = this.addedTileEntityList.get(var3);
/*      */         
/* 2318 */         if (!var4.isInvalid() && var4.getPos().equals(pos)) {
/*      */           
/* 2320 */           var2 = var4;
/*      */           
/*      */           break;
/*      */         } 
/*      */       } 
/*      */     }
/* 2326 */     if (var2 == null)
/*      */     {
/* 2328 */       var2 = getChunkFromBlockCoords(pos).func_177424_a(pos, Chunk.EnumCreateEntityType.IMMEDIATE);
/*      */     }
/*      */     
/* 2331 */     if (var2 == null)
/*      */     {
/* 2333 */       for (int var3 = 0; var3 < this.addedTileEntityList.size(); var3++) {
/*      */         
/* 2335 */         TileEntity var4 = this.addedTileEntityList.get(var3);
/*      */         
/* 2337 */         if (!var4.isInvalid() && var4.getPos().equals(pos)) {
/*      */           
/* 2339 */           var2 = var4;
/*      */           
/*      */           break;
/*      */         } 
/*      */       } 
/*      */     }
/* 2345 */     return var2;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void setTileEntity(BlockPos p_175690_1_, TileEntity p_175690_2_) {
/* 2351 */     if (p_175690_2_ != null && !p_175690_2_.isInvalid())
/*      */     {
/* 2353 */       if (this.processingLoadedTiles) {
/*      */         
/* 2355 */         p_175690_2_.setPos(p_175690_1_);
/* 2356 */         Iterator<TileEntity> var3 = this.addedTileEntityList.iterator();
/*      */         
/* 2358 */         while (var3.hasNext()) {
/*      */           
/* 2360 */           TileEntity var4 = var3.next();
/*      */           
/* 2362 */           if (var4.getPos().equals(p_175690_1_)) {
/*      */             
/* 2364 */             var4.invalidate();
/* 2365 */             var3.remove();
/*      */           } 
/*      */         } 
/*      */         
/* 2369 */         this.addedTileEntityList.add(p_175690_2_);
/*      */       }
/*      */       else {
/*      */         
/* 2373 */         addTileEntity(p_175690_2_);
/* 2374 */         getChunkFromBlockCoords(p_175690_1_).addTileEntity(p_175690_1_, p_175690_2_);
/*      */       } 
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void removeTileEntity(BlockPos pos) {
/* 2381 */     TileEntity var2 = getTileEntity(pos);
/*      */     
/* 2383 */     if (var2 != null && this.processingLoadedTiles) {
/*      */       
/* 2385 */       var2.invalidate();
/* 2386 */       this.addedTileEntityList.remove(var2);
/*      */     }
/*      */     else {
/*      */       
/* 2390 */       if (var2 != null) {
/*      */         
/* 2392 */         this.addedTileEntityList.remove(var2);
/* 2393 */         this.loadedTileEntityList.remove(var2);
/* 2394 */         this.tickableTileEntities.remove(var2);
/*      */       } 
/*      */       
/* 2397 */       getChunkFromBlockCoords(pos).removeTileEntity(pos);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void markTileEntityForRemoval(TileEntity tileEntityIn) {
/* 2406 */     this.tileEntitiesToBeRemoved.add(tileEntityIn);
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean func_175665_u(BlockPos p_175665_1_) {
/* 2411 */     IBlockState var2 = getBlockState(p_175665_1_);
/* 2412 */     AxisAlignedBB var3 = var2.getBlock().getCollisionBoundingBox(this, p_175665_1_, var2);
/* 2413 */     return (var3 != null && var3.getAverageEdgeLength() >= 1.0D);
/*      */   }
/*      */ 
/*      */   
/*      */   public static boolean doesBlockHaveSolidTopSurface(IBlockAccess p_175683_0_, BlockPos p_175683_1_) {
/* 2418 */     IBlockState var2 = p_175683_0_.getBlockState(p_175683_1_);
/* 2419 */     Block var3 = var2.getBlock();
/* 2420 */     return (var3.getMaterial().isOpaque() && var3.isFullCube()) ? true : ((var3 instanceof BlockStairs) ? ((var2.getValue((IProperty)BlockStairs.HALF) == BlockStairs.EnumHalf.TOP)) : ((var3 instanceof BlockSlab) ? ((var2.getValue((IProperty)BlockSlab.HALF_PROP) == BlockSlab.EnumBlockHalf.TOP)) : ((var3 instanceof net.minecraft.block.BlockHopper) ? true : ((var3 instanceof BlockSnow) ? ((((Integer)var2.getValue((IProperty)BlockSnow.LAYERS_PROP)).intValue() == 7)) : false))));
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean func_175677_d(BlockPos p_175677_1_, boolean p_175677_2_) {
/* 2425 */     if (!isValid(p_175677_1_))
/*      */     {
/* 2427 */       return p_175677_2_;
/*      */     }
/*      */ 
/*      */     
/* 2431 */     Chunk var3 = this.chunkProvider.func_177459_a(p_175677_1_);
/*      */     
/* 2433 */     if (var3.isEmpty())
/*      */     {
/* 2435 */       return p_175677_2_;
/*      */     }
/*      */ 
/*      */     
/* 2439 */     Block var4 = getBlockState(p_175677_1_).getBlock();
/* 2440 */     return (var4.getMaterial().isOpaque() && var4.isFullCube());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void calculateInitialSkylight() {
/* 2450 */     int var1 = calculateSkylightSubtracted(1.0F);
/*      */     
/* 2452 */     if (var1 != this.skylightSubtracted)
/*      */     {
/* 2454 */       this.skylightSubtracted = var1;
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setAllowedSpawnTypes(boolean hostile, boolean peaceful) {
/* 2463 */     this.spawnHostileMobs = hostile;
/* 2464 */     this.spawnPeacefulMobs = peaceful;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void tick() {
/* 2472 */     updateWeather();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void calculateInitialWeather() {
/* 2480 */     if (this.worldInfo.isRaining()) {
/*      */       
/* 2482 */       this.rainingStrength = 1.0F;
/*      */       
/* 2484 */       if (this.worldInfo.isThundering())
/*      */       {
/* 2486 */         this.thunderingStrength = 1.0F;
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void updateWeather() {
/* 2496 */     if (!this.provider.getHasNoSky())
/*      */     {
/* 2498 */       if (!this.isRemote) {
/*      */         
/* 2500 */         int var1 = this.worldInfo.func_176133_A();
/*      */         
/* 2502 */         if (var1 > 0) {
/*      */           
/* 2504 */           var1--;
/* 2505 */           this.worldInfo.func_176142_i(var1);
/* 2506 */           this.worldInfo.setThunderTime(this.worldInfo.isThundering() ? 1 : 2);
/* 2507 */           this.worldInfo.setRainTime(this.worldInfo.isRaining() ? 1 : 2);
/*      */         } 
/*      */         
/* 2510 */         int var2 = this.worldInfo.getThunderTime();
/*      */         
/* 2512 */         if (var2 <= 0) {
/*      */           
/* 2514 */           if (this.worldInfo.isThundering())
/*      */           {
/* 2516 */             this.worldInfo.setThunderTime(this.rand.nextInt(12000) + 3600);
/*      */           }
/*      */           else
/*      */           {
/* 2520 */             this.worldInfo.setThunderTime(this.rand.nextInt(168000) + 12000);
/*      */           }
/*      */         
/*      */         } else {
/*      */           
/* 2525 */           var2--;
/* 2526 */           this.worldInfo.setThunderTime(var2);
/*      */           
/* 2528 */           if (var2 <= 0)
/*      */           {
/* 2530 */             this.worldInfo.setThundering(!this.worldInfo.isThundering());
/*      */           }
/*      */         } 
/*      */         
/* 2534 */         this.prevThunderingStrength = this.thunderingStrength;
/*      */         
/* 2536 */         if (this.worldInfo.isThundering()) {
/*      */           
/* 2538 */           this.thunderingStrength = (float)(this.thunderingStrength + 0.01D);
/*      */         }
/*      */         else {
/*      */           
/* 2542 */           this.thunderingStrength = (float)(this.thunderingStrength - 0.01D);
/*      */         } 
/*      */         
/* 2545 */         this.thunderingStrength = MathHelper.clamp_float(this.thunderingStrength, 0.0F, 1.0F);
/* 2546 */         int var3 = this.worldInfo.getRainTime();
/*      */         
/* 2548 */         if (var3 <= 0) {
/*      */           
/* 2550 */           if (this.worldInfo.isRaining())
/*      */           {
/* 2552 */             this.worldInfo.setRainTime(this.rand.nextInt(12000) + 12000);
/*      */           }
/*      */           else
/*      */           {
/* 2556 */             this.worldInfo.setRainTime(this.rand.nextInt(168000) + 12000);
/*      */           }
/*      */         
/*      */         } else {
/*      */           
/* 2561 */           var3--;
/* 2562 */           this.worldInfo.setRainTime(var3);
/*      */           
/* 2564 */           if (var3 <= 0)
/*      */           {
/* 2566 */             this.worldInfo.setRaining(!this.worldInfo.isRaining());
/*      */           }
/*      */         } 
/*      */         
/* 2570 */         this.prevRainingStrength = this.rainingStrength;
/*      */         
/* 2572 */         if (this.worldInfo.isRaining()) {
/*      */           
/* 2574 */           this.rainingStrength = (float)(this.rainingStrength + 0.01D);
/*      */         }
/*      */         else {
/*      */           
/* 2578 */           this.rainingStrength = (float)(this.rainingStrength - 0.01D);
/*      */         } 
/*      */         
/* 2581 */         this.rainingStrength = MathHelper.clamp_float(this.rainingStrength, 0.0F, 1.0F);
/*      */       } 
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   protected void setActivePlayerChunksAndCheckLight() {
/* 2588 */     this.activeChunkSet.clear();
/* 2589 */     this.theProfiler.startSection("buildList");
/*      */ 
/*      */ 
/*      */     
/*      */     int var1;
/*      */ 
/*      */     
/* 2596 */     for (var1 = 0; var1 < this.playerEntities.size(); var1++) {
/*      */       
/* 2598 */       EntityPlayer var2 = this.playerEntities.get(var1);
/* 2599 */       int var3 = MathHelper.floor_double(var2.posX / 16.0D);
/* 2600 */       int var4 = MathHelper.floor_double(var2.posZ / 16.0D);
/* 2601 */       int var5 = getRenderDistanceChunks();
/*      */       
/* 2603 */       for (int var6 = -var5; var6 <= var5; var6++) {
/*      */         
/* 2605 */         for (int var7 = -var5; var7 <= var5; var7++)
/*      */         {
/* 2607 */           this.activeChunkSet.add(new ChunkCoordIntPair(var6 + var3, var7 + var4));
/*      */         }
/*      */       } 
/*      */     } 
/*      */     
/* 2612 */     this.theProfiler.endSection();
/*      */     
/* 2614 */     if (this.ambientTickCountdown > 0)
/*      */     {
/* 2616 */       this.ambientTickCountdown--;
/*      */     }
/*      */     
/* 2619 */     this.theProfiler.startSection("playerCheckLight");
/*      */     
/* 2621 */     if (!this.playerEntities.isEmpty()) {
/*      */       
/* 2623 */       var1 = this.rand.nextInt(this.playerEntities.size());
/* 2624 */       EntityPlayer var2 = this.playerEntities.get(var1);
/* 2625 */       int var3 = MathHelper.floor_double(var2.posX) + this.rand.nextInt(11) - 5;
/* 2626 */       int var4 = MathHelper.floor_double(var2.posY) + this.rand.nextInt(11) - 5;
/* 2627 */       int var5 = MathHelper.floor_double(var2.posZ) + this.rand.nextInt(11) - 5;
/* 2628 */       checkLight(new BlockPos(var3, var4, var5));
/*      */     } 
/*      */     
/* 2631 */     this.theProfiler.endSection();
/*      */   }
/*      */ 
/*      */   
/*      */   protected abstract int getRenderDistanceChunks();
/*      */   
/*      */   protected void func_147467_a(int p_147467_1_, int p_147467_2_, Chunk p_147467_3_) {
/* 2638 */     this.theProfiler.endStartSection("moodSound");
/*      */     
/* 2640 */     if (this.ambientTickCountdown == 0 && !this.isRemote) {
/*      */       
/* 2642 */       this.updateLCG = this.updateLCG * 3 + 1013904223;
/* 2643 */       int var4 = this.updateLCG >> 2;
/* 2644 */       int var5 = var4 & 0xF;
/* 2645 */       int var6 = var4 >> 8 & 0xF;
/* 2646 */       int var7 = var4 >> 16 & 0xFF;
/* 2647 */       BlockPos var8 = new BlockPos(var5, var7, var6);
/* 2648 */       Block var9 = p_147467_3_.getBlock(var8);
/* 2649 */       var5 += p_147467_1_;
/* 2650 */       var6 += p_147467_2_;
/*      */       
/* 2652 */       if (var9.getMaterial() == Material.air && getLight(var8) <= this.rand.nextInt(8) && getLightFor(EnumSkyBlock.SKY, var8) <= 0) {
/*      */         
/* 2654 */         EntityPlayer var10 = getClosestPlayer(var5 + 0.5D, var7 + 0.5D, var6 + 0.5D, 8.0D);
/*      */         
/* 2656 */         if (var10 != null && var10.getDistanceSq(var5 + 0.5D, var7 + 0.5D, var6 + 0.5D) > 4.0D) {
/*      */           
/* 2658 */           playSoundEffect(var5 + 0.5D, var7 + 0.5D, var6 + 0.5D, "ambient.cave.cave", 0.7F, 0.8F + this.rand.nextFloat() * 0.2F);
/* 2659 */           this.ambientTickCountdown = this.rand.nextInt(12000) + 6000;
/*      */         } 
/*      */       } 
/*      */     } 
/*      */     
/* 2664 */     this.theProfiler.endStartSection("checkLight");
/* 2665 */     p_147467_3_.enqueueRelightChecks();
/*      */   }
/*      */ 
/*      */   
/*      */   protected void func_147456_g() {
/* 2670 */     setActivePlayerChunksAndCheckLight();
/*      */   }
/*      */ 
/*      */   
/*      */   public void func_175637_a(Block p_175637_1_, BlockPos p_175637_2_, Random p_175637_3_) {
/* 2675 */     this.scheduledUpdatesAreImmediate = true;
/* 2676 */     p_175637_1_.updateTick(this, p_175637_2_, getBlockState(p_175637_2_), p_175637_3_);
/* 2677 */     this.scheduledUpdatesAreImmediate = false;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean func_175675_v(BlockPos p_175675_1_) {
/* 2682 */     return func_175670_e(p_175675_1_, false);
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean func_175662_w(BlockPos p_175662_1_) {
/* 2687 */     return func_175670_e(p_175662_1_, true);
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean func_175670_e(BlockPos p_175670_1_, boolean p_175670_2_) {
/* 2692 */     BiomeGenBase var3 = getBiomeGenForCoords(p_175670_1_);
/* 2693 */     float var4 = var3.func_180626_a(p_175670_1_);
/*      */     
/* 2695 */     if (var4 > 0.15F)
/*      */     {
/* 2697 */       return false;
/*      */     }
/*      */ 
/*      */     
/* 2701 */     if (p_175670_1_.getY() >= 0 && p_175670_1_.getY() < 256 && getLightFor(EnumSkyBlock.BLOCK, p_175670_1_) < 10) {
/*      */       
/* 2703 */       IBlockState var5 = getBlockState(p_175670_1_);
/* 2704 */       Block var6 = var5.getBlock();
/*      */       
/* 2706 */       if ((var6 == Blocks.water || var6 == Blocks.flowing_water) && ((Integer)var5.getValue((IProperty)BlockLiquid.LEVEL)).intValue() == 0) {
/*      */         
/* 2708 */         if (!p_175670_2_)
/*      */         {
/* 2710 */           return true;
/*      */         }
/*      */         
/* 2713 */         boolean var7 = (func_175696_F(p_175670_1_.offsetWest()) && func_175696_F(p_175670_1_.offsetEast()) && func_175696_F(p_175670_1_.offsetNorth()) && func_175696_F(p_175670_1_.offsetSouth()));
/*      */         
/* 2715 */         if (!var7)
/*      */         {
/* 2717 */           return true;
/*      */         }
/*      */       } 
/*      */     } 
/*      */     
/* 2722 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean func_175696_F(BlockPos p_175696_1_) {
/* 2728 */     return (getBlockState(p_175696_1_).getBlock().getMaterial() == Material.water);
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean func_175708_f(BlockPos p_175708_1_, boolean p_175708_2_) {
/* 2733 */     BiomeGenBase var3 = getBiomeGenForCoords(p_175708_1_);
/* 2734 */     float var4 = var3.func_180626_a(p_175708_1_);
/*      */     
/* 2736 */     if (var4 > 0.15F)
/*      */     {
/* 2738 */       return false;
/*      */     }
/* 2740 */     if (!p_175708_2_)
/*      */     {
/* 2742 */       return true;
/*      */     }
/*      */ 
/*      */     
/* 2746 */     if (p_175708_1_.getY() >= 0 && p_175708_1_.getY() < 256 && getLightFor(EnumSkyBlock.BLOCK, p_175708_1_) < 10) {
/*      */       
/* 2748 */       Block var5 = getBlockState(p_175708_1_).getBlock();
/*      */       
/* 2750 */       if (var5.getMaterial() == Material.air && Blocks.snow_layer.canPlaceBlockAt(this, p_175708_1_))
/*      */       {
/* 2752 */         return true;
/*      */       }
/*      */     } 
/*      */     
/* 2756 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean checkLight(BlockPos p_175664_1_) {
/* 2762 */     boolean var2 = false;
/*      */     
/* 2764 */     if (!this.provider.getHasNoSky())
/*      */     {
/* 2766 */       var2 |= checkLightFor(EnumSkyBlock.SKY, p_175664_1_);
/*      */     }
/*      */     
/* 2769 */     var2 |= checkLightFor(EnumSkyBlock.BLOCK, p_175664_1_);
/* 2770 */     return var2;
/*      */   }
/*      */ 
/*      */   
/*      */   private int func_175638_a(BlockPos p_175638_1_, EnumSkyBlock p_175638_2_) {
/* 2775 */     if (p_175638_2_ == EnumSkyBlock.SKY && isAgainstSky(p_175638_1_))
/*      */     {
/* 2777 */       return 15;
/*      */     }
/*      */ 
/*      */     
/* 2781 */     Block var3 = getBlockState(p_175638_1_).getBlock();
/* 2782 */     int var4 = (p_175638_2_ == EnumSkyBlock.SKY) ? 0 : var3.getLightValue();
/* 2783 */     int var5 = var3.getLightOpacity();
/*      */     
/* 2785 */     if (var5 >= 15 && var3.getLightValue() > 0)
/*      */     {
/* 2787 */       var5 = 1;
/*      */     }
/*      */     
/* 2790 */     if (var5 < 1)
/*      */     {
/* 2792 */       var5 = 1;
/*      */     }
/*      */     
/* 2795 */     if (var5 >= 15)
/*      */     {
/* 2797 */       return 0;
/*      */     }
/* 2799 */     if (var4 >= 14)
/*      */     {
/* 2801 */       return var4;
/*      */     }
/*      */ 
/*      */     
/* 2805 */     EnumFacing[] var6 = EnumFacing.values();
/* 2806 */     int var7 = var6.length;
/*      */     
/* 2808 */     for (int var8 = 0; var8 < var7; var8++) {
/*      */       
/* 2810 */       EnumFacing var9 = var6[var8];
/* 2811 */       BlockPos var10 = p_175638_1_.offset(var9);
/* 2812 */       int var11 = getLightFor(p_175638_2_, var10) - var5;
/*      */       
/* 2814 */       if (var11 > var4)
/*      */       {
/* 2816 */         var4 = var11;
/*      */       }
/*      */       
/* 2819 */       if (var4 >= 14)
/*      */       {
/* 2821 */         return var4;
/*      */       }
/*      */     } 
/*      */     
/* 2825 */     return var4;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean checkLightFor(EnumSkyBlock p_180500_1_, BlockPos p_180500_2_) {
/* 2832 */     if (!isAreaLoaded(p_180500_2_, 17, false))
/*      */     {
/* 2834 */       return false;
/*      */     }
/*      */ 
/*      */     
/* 2838 */     int var3 = 0;
/* 2839 */     int var4 = 0;
/* 2840 */     this.theProfiler.startSection("getBrightness");
/* 2841 */     int var5 = getLightFor(p_180500_1_, p_180500_2_);
/* 2842 */     int var6 = func_175638_a(p_180500_2_, p_180500_1_);
/* 2843 */     int var7 = p_180500_2_.getX();
/* 2844 */     int var8 = p_180500_2_.getY();
/* 2845 */     int var9 = p_180500_2_.getZ();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2855 */     if (var6 > var5) {
/*      */       
/* 2857 */       this.lightUpdateBlockList[var4++] = 133152;
/*      */     }
/* 2859 */     else if (var6 < var5) {
/*      */       
/* 2861 */       this.lightUpdateBlockList[var4++] = 0x20820 | var5 << 18;
/*      */       
/* 2863 */       while (var3 < var4) {
/*      */         
/* 2865 */         int var10 = this.lightUpdateBlockList[var3++];
/* 2866 */         int var11 = (var10 & 0x3F) - 32 + var7;
/* 2867 */         int var12 = (var10 >> 6 & 0x3F) - 32 + var8;
/* 2868 */         int var13 = (var10 >> 12 & 0x3F) - 32 + var9;
/* 2869 */         int var14 = var10 >> 18 & 0xF;
/* 2870 */         BlockPos var15 = new BlockPos(var11, var12, var13);
/* 2871 */         int var16 = getLightFor(p_180500_1_, var15);
/*      */         
/* 2873 */         if (var16 == var14) {
/*      */           
/* 2875 */           setLightFor(p_180500_1_, var15, 0);
/*      */           
/* 2877 */           if (var14 > 0) {
/*      */             
/* 2879 */             int var17 = MathHelper.abs_int(var11 - var7);
/* 2880 */             int var18 = MathHelper.abs_int(var12 - var8);
/* 2881 */             int var19 = MathHelper.abs_int(var13 - var9);
/*      */             
/* 2883 */             if (var17 + var18 + var19 < 17) {
/*      */               
/* 2885 */               EnumFacing[] var20 = EnumFacing.values();
/* 2886 */               int var21 = var20.length;
/*      */               
/* 2888 */               for (int var22 = 0; var22 < var21; var22++) {
/*      */                 
/* 2890 */                 EnumFacing var23 = var20[var22];
/* 2891 */                 int var24 = var11 + var23.getFrontOffsetX();
/* 2892 */                 int var25 = var12 + var23.getFrontOffsetY();
/* 2893 */                 int var26 = var13 + var23.getFrontOffsetZ();
/* 2894 */                 BlockPos var27 = new BlockPos(var24, var25, var26);
/* 2895 */                 int var28 = Math.max(1, getBlockState(var27).getBlock().getLightOpacity());
/* 2896 */                 var16 = getLightFor(p_180500_1_, var27);
/*      */                 
/* 2898 */                 if (var16 == var14 - var28 && var4 < this.lightUpdateBlockList.length)
/*      */                 {
/* 2900 */                   this.lightUpdateBlockList[var4++] = var24 - var7 + 32 | var25 - var8 + 32 << 6 | var26 - var9 + 32 << 12 | var14 - var28 << 18;
/*      */                 }
/*      */               } 
/*      */             } 
/*      */           } 
/*      */         } 
/*      */       } 
/*      */       
/* 2908 */       var3 = 0;
/*      */     } 
/*      */     
/* 2911 */     this.theProfiler.endSection();
/* 2912 */     this.theProfiler.startSection("checkedPosition < toCheckCount");
/*      */     
/* 2914 */     while (var3 < var4) {
/*      */       
/* 2916 */       int var10 = this.lightUpdateBlockList[var3++];
/* 2917 */       int var11 = (var10 & 0x3F) - 32 + var7;
/* 2918 */       int var12 = (var10 >> 6 & 0x3F) - 32 + var8;
/* 2919 */       int var13 = (var10 >> 12 & 0x3F) - 32 + var9;
/* 2920 */       BlockPos var29 = new BlockPos(var11, var12, var13);
/* 2921 */       int var30 = getLightFor(p_180500_1_, var29);
/* 2922 */       int var16 = func_175638_a(var29, p_180500_1_);
/*      */       
/* 2924 */       if (var16 != var30) {
/*      */         
/* 2926 */         setLightFor(p_180500_1_, var29, var16);
/*      */         
/* 2928 */         if (var16 > var30) {
/*      */           
/* 2930 */           int var17 = Math.abs(var11 - var7);
/* 2931 */           int var18 = Math.abs(var12 - var8);
/* 2932 */           int var19 = Math.abs(var13 - var9);
/* 2933 */           boolean var31 = (var4 < this.lightUpdateBlockList.length - 6);
/*      */           
/* 2935 */           if (var17 + var18 + var19 < 17 && var31) {
/*      */             
/* 2937 */             if (getLightFor(p_180500_1_, var29.offsetWest()) < var16)
/*      */             {
/* 2939 */               this.lightUpdateBlockList[var4++] = var11 - 1 - var7 + 32 + (var12 - var8 + 32 << 6) + (var13 - var9 + 32 << 12);
/*      */             }
/*      */             
/* 2942 */             if (getLightFor(p_180500_1_, var29.offsetEast()) < var16)
/*      */             {
/* 2944 */               this.lightUpdateBlockList[var4++] = var11 + 1 - var7 + 32 + (var12 - var8 + 32 << 6) + (var13 - var9 + 32 << 12);
/*      */             }
/*      */             
/* 2947 */             if (getLightFor(p_180500_1_, var29.offsetDown()) < var16)
/*      */             {
/* 2949 */               this.lightUpdateBlockList[var4++] = var11 - var7 + 32 + (var12 - 1 - var8 + 32 << 6) + (var13 - var9 + 32 << 12);
/*      */             }
/*      */             
/* 2952 */             if (getLightFor(p_180500_1_, var29.offsetUp()) < var16)
/*      */             {
/* 2954 */               this.lightUpdateBlockList[var4++] = var11 - var7 + 32 + (var12 + 1 - var8 + 32 << 6) + (var13 - var9 + 32 << 12);
/*      */             }
/*      */             
/* 2957 */             if (getLightFor(p_180500_1_, var29.offsetNorth()) < var16)
/*      */             {
/* 2959 */               this.lightUpdateBlockList[var4++] = var11 - var7 + 32 + (var12 - var8 + 32 << 6) + (var13 - 1 - var9 + 32 << 12);
/*      */             }
/*      */             
/* 2962 */             if (getLightFor(p_180500_1_, var29.offsetSouth()) < var16)
/*      */             {
/* 2964 */               this.lightUpdateBlockList[var4++] = var11 - var7 + 32 + (var12 - var8 + 32 << 6) + (var13 + 1 - var9 + 32 << 12);
/*      */             }
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     } 
/*      */     
/* 2971 */     this.theProfiler.endSection();
/* 2972 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean tickUpdates(boolean p_72955_1_) {
/* 2981 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   public List getPendingBlockUpdates(Chunk p_72920_1_, boolean p_72920_2_) {
/* 2986 */     return null;
/*      */   }
/*      */ 
/*      */   
/*      */   public List func_175712_a(StructureBoundingBox p_175712_1_, boolean p_175712_2_) {
/* 2991 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public List getEntitiesWithinAABBExcludingEntity(Entity p_72839_1_, AxisAlignedBB p_72839_2_) {
/* 2999 */     return func_175674_a(p_72839_1_, p_72839_2_, IEntitySelector.field_180132_d);
/*      */   }
/*      */ 
/*      */   
/*      */   public List func_175674_a(Entity p_175674_1_, AxisAlignedBB p_175674_2_, Predicate p_175674_3_) {
/* 3004 */     ArrayList var4 = Lists.newArrayList();
/* 3005 */     int var5 = MathHelper.floor_double((p_175674_2_.minX - 2.0D) / 16.0D);
/* 3006 */     int var6 = MathHelper.floor_double((p_175674_2_.maxX + 2.0D) / 16.0D);
/* 3007 */     int var7 = MathHelper.floor_double((p_175674_2_.minZ - 2.0D) / 16.0D);
/* 3008 */     int var8 = MathHelper.floor_double((p_175674_2_.maxZ + 2.0D) / 16.0D);
/*      */     
/* 3010 */     for (int var9 = var5; var9 <= var6; var9++) {
/*      */       
/* 3012 */       for (int var10 = var7; var10 <= var8; var10++) {
/*      */         
/* 3014 */         if (isChunkLoaded(var9, var10, true))
/*      */         {
/* 3016 */           getChunkFromChunkCoords(var9, var10).func_177414_a(p_175674_1_, p_175674_2_, var4, p_175674_3_);
/*      */         }
/*      */       } 
/*      */     } 
/*      */     
/* 3021 */     return var4;
/*      */   }
/*      */ 
/*      */   
/*      */   public List func_175644_a(Class p_175644_1_, Predicate p_175644_2_) {
/* 3026 */     ArrayList<Entity> var3 = Lists.newArrayList();
/* 3027 */     Iterator<Entity> var4 = this.loadedEntityList.iterator();
/*      */     
/* 3029 */     while (var4.hasNext()) {
/*      */       
/* 3031 */       Entity var5 = var4.next();
/*      */       
/* 3033 */       if (p_175644_1_.isAssignableFrom(var5.getClass()) && p_175644_2_.apply(var5))
/*      */       {
/* 3035 */         var3.add(var5);
/*      */       }
/*      */     } 
/*      */     
/* 3039 */     return var3;
/*      */   }
/*      */ 
/*      */   
/*      */   public List func_175661_b(Class p_175661_1_, Predicate p_175661_2_) {
/* 3044 */     ArrayList<Entity> var3 = Lists.newArrayList();
/* 3045 */     Iterator<Entity> var4 = this.playerEntities.iterator();
/*      */     
/* 3047 */     while (var4.hasNext()) {
/*      */       
/* 3049 */       Entity var5 = var4.next();
/*      */       
/* 3051 */       if (p_175661_1_.isAssignableFrom(var5.getClass()) && p_175661_2_.apply(var5))
/*      */       {
/* 3053 */         var3.add(var5);
/*      */       }
/*      */     } 
/*      */     
/* 3057 */     return var3;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public List getEntitiesWithinAABB(Class p_72872_1_, AxisAlignedBB p_72872_2_) {
/* 3065 */     return func_175647_a(p_72872_1_, p_72872_2_, IEntitySelector.field_180132_d);
/*      */   }
/*      */ 
/*      */   
/*      */   public List func_175647_a(Class p_175647_1_, AxisAlignedBB p_175647_2_, Predicate p_175647_3_) {
/* 3070 */     int var4 = MathHelper.floor_double((p_175647_2_.minX - 2.0D) / 16.0D);
/* 3071 */     int var5 = MathHelper.floor_double((p_175647_2_.maxX + 2.0D) / 16.0D);
/* 3072 */     int var6 = MathHelper.floor_double((p_175647_2_.minZ - 2.0D) / 16.0D);
/* 3073 */     int var7 = MathHelper.floor_double((p_175647_2_.maxZ + 2.0D) / 16.0D);
/* 3074 */     ArrayList var8 = Lists.newArrayList();
/*      */     
/* 3076 */     for (int var9 = var4; var9 <= var5; var9++) {
/*      */       
/* 3078 */       for (int var10 = var6; var10 <= var7; var10++) {
/*      */         
/* 3080 */         if (isChunkLoaded(var9, var10, true))
/*      */         {
/* 3082 */           getChunkFromChunkCoords(var9, var10).func_177430_a(p_175647_1_, p_175647_2_, var8, p_175647_3_);
/*      */         }
/*      */       } 
/*      */     } 
/*      */     
/* 3087 */     return var8;
/*      */   }
/*      */ 
/*      */   
/*      */   public Entity findNearestEntityWithinAABB(Class p_72857_1_, AxisAlignedBB p_72857_2_, Entity p_72857_3_) {
/* 3092 */     List<Entity> var4 = getEntitiesWithinAABB(p_72857_1_, p_72857_2_);
/* 3093 */     Entity var5 = null;
/* 3094 */     double var6 = Double.MAX_VALUE;
/*      */     
/* 3096 */     for (int var8 = 0; var8 < var4.size(); var8++) {
/*      */       
/* 3098 */       Entity var9 = var4.get(var8);
/*      */       
/* 3100 */       if (var9 != p_72857_3_ && IEntitySelector.field_180132_d.apply(var9)) {
/*      */         
/* 3102 */         double var10 = p_72857_3_.getDistanceSqToEntity(var9);
/*      */         
/* 3104 */         if (var10 <= var6) {
/*      */           
/* 3106 */           var5 = var9;
/* 3107 */           var6 = var10;
/*      */         } 
/*      */       } 
/*      */     } 
/*      */     
/* 3112 */     return var5;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Entity getEntityByID(int p_73045_1_) {
/* 3120 */     return (Entity)this.entitiesById.lookup(p_73045_1_);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public List getLoadedEntityList() {
/* 3128 */     return this.loadedEntityList;
/*      */   }
/*      */ 
/*      */   
/*      */   public void func_175646_b(BlockPos p_175646_1_, TileEntity p_175646_2_) {
/* 3133 */     if (isBlockLoaded(p_175646_1_))
/*      */     {
/* 3135 */       getChunkFromBlockCoords(p_175646_1_).setChunkModified();
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int countEntities(Class entityType) {
/* 3144 */     int var2 = 0;
/* 3145 */     Iterator<Entity> var3 = this.loadedEntityList.iterator();
/*      */     
/* 3147 */     while (var3.hasNext()) {
/*      */       
/* 3149 */       Entity var4 = var3.next();
/*      */       
/* 3151 */       if ((!(var4 instanceof EntityLiving) || !((EntityLiving)var4).isNoDespawnRequired()) && entityType.isAssignableFrom(var4.getClass()))
/*      */       {
/* 3153 */         var2++;
/*      */       }
/*      */     } 
/*      */     
/* 3157 */     return var2;
/*      */   }
/*      */ 
/*      */   
/*      */   public void loadEntities(Collection entityCollection) {
/* 3162 */     this.loadedEntityList.addAll(entityCollection);
/* 3163 */     Iterator<Entity> var2 = entityCollection.iterator();
/*      */     
/* 3165 */     while (var2.hasNext()) {
/*      */       
/* 3167 */       Entity var3 = var2.next();
/* 3168 */       onEntityAdded(var3);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public void unloadEntities(Collection entityCollection) {
/* 3174 */     this.unloadedEntityList.addAll(entityCollection);
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean canBlockBePlaced(Block p_175716_1_, BlockPos p_175716_2_, boolean p_175716_3_, EnumFacing p_175716_4_, Entity p_175716_5_, ItemStack p_175716_6_) {
/* 3179 */     Block var7 = getBlockState(p_175716_2_).getBlock();
/* 3180 */     AxisAlignedBB var8 = p_175716_3_ ? null : p_175716_1_.getCollisionBoundingBox(this, p_175716_2_, p_175716_1_.getDefaultState());
/* 3181 */     return (var8 != null && !checkNoEntityCollision(var8, p_175716_5_)) ? false : ((var7.getMaterial() == Material.circuits && p_175716_1_ == Blocks.anvil) ? true : ((var7.getMaterial().isReplaceable() && p_175716_1_.canReplace(this, p_175716_2_, p_175716_4_, p_175716_6_))));
/*      */   }
/*      */ 
/*      */   
/*      */   public int getStrongPower(BlockPos pos, EnumFacing direction) {
/* 3186 */     IBlockState var3 = getBlockState(pos);
/* 3187 */     return var3.getBlock().isProvidingStrongPower(this, pos, var3, direction);
/*      */   }
/*      */ 
/*      */   
/*      */   public WorldType getWorldType() {
/* 3192 */     return this.worldInfo.getTerrainType();
/*      */   }
/*      */ 
/*      */   
/*      */   public int getStrongPower(BlockPos pos) {
/* 3197 */     byte var2 = 0;
/* 3198 */     int var3 = Math.max(var2, getStrongPower(pos.offsetDown(), EnumFacing.DOWN));
/*      */     
/* 3200 */     if (var3 >= 15)
/*      */     {
/* 3202 */       return var3;
/*      */     }
/*      */ 
/*      */     
/* 3206 */     var3 = Math.max(var3, getStrongPower(pos.offsetUp(), EnumFacing.UP));
/*      */     
/* 3208 */     if (var3 >= 15)
/*      */     {
/* 3210 */       return var3;
/*      */     }
/*      */ 
/*      */     
/* 3214 */     var3 = Math.max(var3, getStrongPower(pos.offsetNorth(), EnumFacing.NORTH));
/*      */     
/* 3216 */     if (var3 >= 15)
/*      */     {
/* 3218 */       return var3;
/*      */     }
/*      */ 
/*      */     
/* 3222 */     var3 = Math.max(var3, getStrongPower(pos.offsetSouth(), EnumFacing.SOUTH));
/*      */     
/* 3224 */     if (var3 >= 15)
/*      */     {
/* 3226 */       return var3;
/*      */     }
/*      */ 
/*      */     
/* 3230 */     var3 = Math.max(var3, getStrongPower(pos.offsetWest(), EnumFacing.WEST));
/*      */     
/* 3232 */     if (var3 >= 15)
/*      */     {
/* 3234 */       return var3;
/*      */     }
/*      */ 
/*      */     
/* 3238 */     var3 = Math.max(var3, getStrongPower(pos.offsetEast(), EnumFacing.EAST));
/* 3239 */     return (var3 >= 15) ? var3 : var3;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean func_175709_b(BlockPos p_175709_1_, EnumFacing p_175709_2_) {
/* 3249 */     return (getRedstonePower(p_175709_1_, p_175709_2_) > 0);
/*      */   }
/*      */ 
/*      */   
/*      */   public int getRedstonePower(BlockPos pos, EnumFacing facing) {
/* 3254 */     IBlockState var3 = getBlockState(pos);
/* 3255 */     Block var4 = var3.getBlock();
/* 3256 */     return var4.isNormalCube() ? getStrongPower(pos) : var4.isProvidingWeakPower(this, pos, var3, facing);
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isBlockPowered(BlockPos pos) {
/* 3261 */     return (getRedstonePower(pos.offsetDown(), EnumFacing.DOWN) > 0) ? true : ((getRedstonePower(pos.offsetUp(), EnumFacing.UP) > 0) ? true : ((getRedstonePower(pos.offsetNorth(), EnumFacing.NORTH) > 0) ? true : ((getRedstonePower(pos.offsetSouth(), EnumFacing.SOUTH) > 0) ? true : ((getRedstonePower(pos.offsetWest(), EnumFacing.WEST) > 0) ? true : ((getRedstonePower(pos.offsetEast(), EnumFacing.EAST) > 0))))));
/*      */   }
/*      */ 
/*      */   
/*      */   public int func_175687_A(BlockPos p_175687_1_) {
/* 3266 */     int var2 = 0;
/* 3267 */     EnumFacing[] var3 = EnumFacing.values();
/* 3268 */     int var4 = var3.length;
/*      */     
/* 3270 */     for (int var5 = 0; var5 < var4; var5++) {
/*      */       
/* 3272 */       EnumFacing var6 = var3[var5];
/* 3273 */       int var7 = getRedstonePower(p_175687_1_.offset(var6), var6);
/*      */       
/* 3275 */       if (var7 >= 15)
/*      */       {
/* 3277 */         return 15;
/*      */       }
/*      */       
/* 3280 */       if (var7 > var2)
/*      */       {
/* 3282 */         var2 = var7;
/*      */       }
/*      */     } 
/*      */     
/* 3286 */     return var2;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public EntityPlayer getClosestPlayerToEntity(Entity entityIn, double distance) {
/* 3295 */     return getClosestPlayer(entityIn.posX, entityIn.posY, entityIn.posZ, distance);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public EntityPlayer getClosestPlayer(double x, double y, double z, double distance) {
/* 3304 */     double var9 = -1.0D;
/* 3305 */     EntityPlayer var11 = null;
/*      */     
/* 3307 */     for (int var12 = 0; var12 < this.playerEntities.size(); var12++) {
/*      */       
/* 3309 */       EntityPlayer var13 = this.playerEntities.get(var12);
/*      */       
/* 3311 */       if (IEntitySelector.field_180132_d.apply(var13)) {
/*      */         
/* 3313 */         double var14 = var13.getDistanceSq(x, y, z);
/*      */         
/* 3315 */         if ((distance < 0.0D || var14 < distance * distance) && (var9 == -1.0D || var14 < var9)) {
/*      */           
/* 3317 */           var9 = var14;
/* 3318 */           var11 = var13;
/*      */         } 
/*      */       } 
/*      */     } 
/*      */     
/* 3323 */     return var11;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean func_175636_b(double p_175636_1_, double p_175636_3_, double p_175636_5_, double p_175636_7_) {
/* 3328 */     for (int var9 = 0; var9 < this.playerEntities.size(); var9++) {
/*      */       
/* 3330 */       EntityPlayer var10 = this.playerEntities.get(var9);
/*      */       
/* 3332 */       if (IEntitySelector.field_180132_d.apply(var10)) {
/*      */         
/* 3334 */         double var11 = var10.getDistanceSq(p_175636_1_, p_175636_3_, p_175636_5_);
/*      */         
/* 3336 */         if (p_175636_7_ < 0.0D || var11 < p_175636_7_ * p_175636_7_)
/*      */         {
/* 3338 */           return true;
/*      */         }
/*      */       } 
/*      */     } 
/*      */     
/* 3343 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public EntityPlayer getPlayerEntityByName(String name) {
/* 3351 */     for (int var2 = 0; var2 < this.playerEntities.size(); var2++) {
/*      */       
/* 3353 */       EntityPlayer var3 = this.playerEntities.get(var2);
/*      */       
/* 3355 */       if (name.equals(var3.getName()))
/*      */       {
/* 3357 */         return var3;
/*      */       }
/*      */     } 
/*      */     
/* 3361 */     return null;
/*      */   }
/*      */ 
/*      */   
/*      */   public EntityPlayer getPlayerEntityByUUID(UUID uuid) {
/* 3366 */     for (int var2 = 0; var2 < this.playerEntities.size(); var2++) {
/*      */       
/* 3368 */       EntityPlayer var3 = this.playerEntities.get(var2);
/*      */       
/* 3370 */       if (uuid.equals(var3.getUniqueID()))
/*      */       {
/* 3372 */         return var3;
/*      */       }
/*      */     } 
/*      */     
/* 3376 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void sendQuittingDisconnectingPacket() {}
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void checkSessionLock() throws MinecraftException {
/* 3389 */     this.saveHandler.checkSessionLock();
/*      */   }
/*      */ 
/*      */   
/*      */   public void func_82738_a(long p_82738_1_) {
/* 3394 */     this.worldInfo.incrementTotalWorldTime(p_82738_1_);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public long getSeed() {
/* 3402 */     return this.worldInfo.getSeed();
/*      */   }
/*      */ 
/*      */   
/*      */   public long getTotalWorldTime() {
/* 3407 */     return this.worldInfo.getWorldTotalTime();
/*      */   }
/*      */ 
/*      */   
/*      */   public long getWorldTime() {
/* 3412 */     return this.worldInfo.getWorldTime();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setWorldTime(long time) {
/* 3420 */     this.worldInfo.setWorldTime(time);
/*      */   }
/*      */ 
/*      */   
/*      */   public BlockPos getSpawnPoint() {
/* 3425 */     BlockPos var1 = new BlockPos(this.worldInfo.getSpawnX(), this.worldInfo.getSpawnY(), this.worldInfo.getSpawnZ());
/*      */     
/* 3427 */     if (!getWorldBorder().contains(var1))
/*      */     {
/* 3429 */       var1 = getHorizon(new BlockPos(getWorldBorder().getCenterX(), 0.0D, getWorldBorder().getCenterZ()));
/*      */     }
/*      */     
/* 3432 */     return var1;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setSpawnLocation(BlockPos p_175652_1_) {
/* 3437 */     this.worldInfo.setSpawn(p_175652_1_);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void joinEntityInSurroundings(Entity entityIn) {
/* 3445 */     int var2 = MathHelper.floor_double(entityIn.posX / 16.0D);
/* 3446 */     int var3 = MathHelper.floor_double(entityIn.posZ / 16.0D);
/* 3447 */     byte var4 = 2;
/*      */     
/* 3449 */     for (int var5 = var2 - var4; var5 <= var2 + var4; var5++) {
/*      */       
/* 3451 */       for (int var6 = var3 - var4; var6 <= var3 + var4; var6++)
/*      */       {
/* 3453 */         getChunkFromChunkCoords(var5, var6);
/*      */       }
/*      */     } 
/*      */     
/* 3457 */     if (!this.loadedEntityList.contains(entityIn))
/*      */     {
/* 3459 */       this.loadedEntityList.add(entityIn);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isBlockModifiable(EntityPlayer p_175660_1_, BlockPos p_175660_2_) {
/* 3465 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setEntityState(Entity entityIn, byte p_72960_2_) {}
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public IChunkProvider getChunkProvider() {
/* 3478 */     return this.chunkProvider;
/*      */   }
/*      */ 
/*      */   
/*      */   public void addBlockEvent(BlockPos pos, Block blockIn, int eventID, int eventParam) {
/* 3483 */     blockIn.onBlockEventReceived(this, pos, getBlockState(pos), eventID, eventParam);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ISaveHandler getSaveHandler() {
/* 3491 */     return this.saveHandler;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public WorldInfo getWorldInfo() {
/* 3499 */     return this.worldInfo;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public GameRules getGameRules() {
/* 3507 */     return this.worldInfo.getGameRulesInstance();
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void updateAllPlayersSleepingFlag() {}
/*      */ 
/*      */ 
/*      */   
/*      */   public float getWeightedThunderStrength(float p_72819_1_) {
/* 3517 */     return (this.prevThunderingStrength + (this.thunderingStrength - this.prevThunderingStrength) * p_72819_1_) * getRainStrength(p_72819_1_);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setThunderStrength(float p_147442_1_) {
/* 3525 */     this.prevThunderingStrength = p_147442_1_;
/* 3526 */     this.thunderingStrength = p_147442_1_;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public float getRainStrength(float p_72867_1_) {
/* 3534 */     return this.prevRainingStrength + (this.rainingStrength - this.prevRainingStrength) * p_72867_1_;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setRainStrength(float strength) {
/* 3542 */     this.prevRainingStrength = strength;
/* 3543 */     this.rainingStrength = strength;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isThundering() {
/* 3551 */     return (getWeightedThunderStrength(1.0F) > 0.9D);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isRaining() {
/* 3559 */     return (getRainStrength(1.0F) > 0.2D);
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean func_175727_C(BlockPos p_175727_1_) {
/* 3564 */     if (!isRaining())
/*      */     {
/* 3566 */       return false;
/*      */     }
/* 3568 */     if (!isAgainstSky(p_175727_1_))
/*      */     {
/* 3570 */       return false;
/*      */     }
/* 3572 */     if (func_175725_q(p_175727_1_).getY() > p_175727_1_.getY())
/*      */     {
/* 3574 */       return false;
/*      */     }
/*      */ 
/*      */     
/* 3578 */     BiomeGenBase var2 = getBiomeGenForCoords(p_175727_1_);
/* 3579 */     return var2.getEnableSnow() ? false : (func_175708_f(p_175727_1_, false) ? false : var2.canSpawnLightningBolt());
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean func_180502_D(BlockPos p_180502_1_) {
/* 3585 */     BiomeGenBase var2 = getBiomeGenForCoords(p_180502_1_);
/* 3586 */     return var2.isHighHumidity();
/*      */   }
/*      */ 
/*      */   
/*      */   public MapStorage func_175693_T() {
/* 3591 */     return this.mapStorage;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setItemData(String p_72823_1_, WorldSavedData p_72823_2_) {
/* 3600 */     this.mapStorage.setData(p_72823_1_, p_72823_2_);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public WorldSavedData loadItemData(Class p_72943_1_, String p_72943_2_) {
/* 3609 */     return this.mapStorage.loadData(p_72943_1_, p_72943_2_);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getUniqueDataId(String p_72841_1_) {
/* 3618 */     return this.mapStorage.getUniqueDataId(p_72841_1_);
/*      */   }
/*      */ 
/*      */   
/*      */   public void func_175669_a(int p_175669_1_, BlockPos p_175669_2_, int p_175669_3_) {
/* 3623 */     for (int var4 = 0; var4 < this.worldAccesses.size(); var4++)
/*      */     {
/* 3625 */       ((IWorldAccess)this.worldAccesses.get(var4)).func_180440_a(p_175669_1_, p_175669_2_, p_175669_3_);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void playAuxSFX(int p_175718_1_, BlockPos p_175718_2_, int p_175718_3_) {
/* 3631 */     playAuxSFXAtEntity(null, p_175718_1_, p_175718_2_, p_175718_3_);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void playAuxSFXAtEntity(EntityPlayer p_180498_1_, int p_180498_2_, BlockPos p_180498_3_, int p_180498_4_) {
/*      */     try {
/* 3638 */       for (int var5 = 0; var5 < this.worldAccesses.size(); var5++)
/*      */       {
/* 3640 */         ((IWorldAccess)this.worldAccesses.get(var5)).func_180439_a(p_180498_1_, p_180498_2_, p_180498_3_, p_180498_4_);
/*      */       }
/*      */     }
/* 3643 */     catch (Throwable var8) {
/*      */       
/* 3645 */       CrashReport var6 = CrashReport.makeCrashReport(var8, "Playing level event");
/* 3646 */       CrashReportCategory var7 = var6.makeCategory("Level event being played");
/* 3647 */       var7.addCrashSection("Block coordinates", CrashReportCategory.getCoordinateInfo(p_180498_3_));
/* 3648 */       var7.addCrashSection("Event source", p_180498_1_);
/* 3649 */       var7.addCrashSection("Event type", Integer.valueOf(p_180498_2_));
/* 3650 */       var7.addCrashSection("Event data", Integer.valueOf(p_180498_4_));
/* 3651 */       throw new ReportedException(var6);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getHeight() {
/* 3660 */     return 256;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getActualHeight() {
/* 3668 */     return this.provider.getHasNoSky() ? 128 : 256;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Random setRandomSeed(int p_72843_1_, int p_72843_2_, int p_72843_3_) {
/* 3676 */     long var4 = p_72843_1_ * 341873128712L + p_72843_2_ * 132897987541L + getWorldInfo().getSeed() + p_72843_3_;
/* 3677 */     this.rand.setSeed(var4);
/* 3678 */     return this.rand;
/*      */   }
/*      */ 
/*      */   
/*      */   public BlockPos func_180499_a(String p_180499_1_, BlockPos p_180499_2_) {
/* 3683 */     return getChunkProvider().func_180513_a(this, p_180499_1_, p_180499_2_);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean extendedLevelsInChunkCache() {
/* 3691 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public double getHorizon() {
/* 3699 */     return (this.worldInfo.getTerrainType() == WorldType.FLAT) ? 0.0D : 63.0D;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public CrashReportCategory addWorldInfoToCrashReport(CrashReport report) {
/* 3707 */     CrashReportCategory var2 = report.makeCategoryDepth("Affected level", 1);
/* 3708 */     var2.addCrashSection("Level name", (this.worldInfo == null) ? "????" : this.worldInfo.getWorldName());
/* 3709 */     var2.addCrashSectionCallable("All players", new Callable()
/*      */         {
/*      */           private static final String __OBFID = "CL_00000143";
/*      */           
/*      */           public String call() {
/* 3714 */             return String.valueOf(World.this.playerEntities.size()) + " total; " + World.this.playerEntities.toString();
/*      */           }
/*      */         });
/* 3717 */     var2.addCrashSectionCallable("Chunk stats", new Callable()
/*      */         {
/*      */           private static final String __OBFID = "CL_00000144";
/*      */           
/*      */           public String call() {
/* 3722 */             return World.this.chunkProvider.makeString();
/*      */           }
/*      */         });
/*      */ 
/*      */     
/*      */     try {
/* 3728 */       this.worldInfo.addToCrashReport(var2);
/*      */     }
/* 3730 */     catch (Throwable var4) {
/*      */       
/* 3732 */       var2.addCrashSectionThrowable("Level Data Unobtainable", var4);
/*      */     } 
/*      */     
/* 3735 */     return var2;
/*      */   }
/*      */ 
/*      */   
/*      */   public void sendBlockBreakProgress(int breakerId, BlockPos pos, int progress) {
/* 3740 */     for (int var4 = 0; var4 < this.worldAccesses.size(); var4++) {
/*      */       
/* 3742 */       IWorldAccess var5 = this.worldAccesses.get(var4);
/* 3743 */       var5.sendBlockBreakProgress(breakerId, pos, progress);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Calendar getCurrentDate() {
/* 3752 */     if (getTotalWorldTime() % 600L == 0L)
/*      */     {
/* 3754 */       this.theCalendar.setTimeInMillis(MinecraftServer.getCurrentTimeMillis());
/*      */     }
/*      */     
/* 3757 */     return this.theCalendar;
/*      */   }
/*      */ 
/*      */   
/*      */   public void makeFireworks(double x, double y, double z, double motionX, double motionY, double motionZ, NBTTagCompound compund) {}
/*      */   
/*      */   public Scoreboard getScoreboard() {
/* 3764 */     return this.worldScoreboard;
/*      */   }
/*      */ 
/*      */   
/*      */   public void updateComparatorOutputLevel(BlockPos pos, Block blockIn) {
/* 3769 */     Iterator<EnumFacing> var3 = EnumFacing.Plane.HORIZONTAL.iterator();
/*      */     
/* 3771 */     while (var3.hasNext()) {
/*      */       
/* 3773 */       EnumFacing var4 = var3.next();
/* 3774 */       BlockPos var5 = pos.offset(var4);
/*      */       
/* 3776 */       if (isBlockLoaded(var5)) {
/*      */         
/* 3778 */         IBlockState var6 = getBlockState(var5);
/*      */         
/* 3780 */         if (Blocks.unpowered_comparator.func_149907_e(var6.getBlock())) {
/*      */           
/* 3782 */           var6.getBlock().onNeighborBlockChange(this, var5, var6, blockIn); continue;
/*      */         } 
/* 3784 */         if (var6.getBlock().isNormalCube()) {
/*      */           
/* 3786 */           var5 = var5.offset(var4);
/* 3787 */           var6 = getBlockState(var5);
/*      */           
/* 3789 */           if (Blocks.unpowered_comparator.func_149907_e(var6.getBlock()))
/*      */           {
/* 3791 */             var6.getBlock().onNeighborBlockChange(this, var5, var6, blockIn);
/*      */           }
/*      */         } 
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public DifficultyInstance getDifficultyForLocation(BlockPos pos) {
/* 3800 */     long var2 = 0L;
/* 3801 */     float var4 = 0.0F;
/*      */     
/* 3803 */     if (isBlockLoaded(pos)) {
/*      */       
/* 3805 */       var4 = getCurrentMoonPhaseFactor();
/* 3806 */       var2 = getChunkFromBlockCoords(pos).getInhabitedTime();
/*      */     } 
/*      */     
/* 3809 */     return new DifficultyInstance(getDifficulty(), getWorldTime(), var2, var4);
/*      */   }
/*      */ 
/*      */   
/*      */   public EnumDifficulty getDifficulty() {
/* 3814 */     return getWorldInfo().getDifficulty();
/*      */   }
/*      */ 
/*      */   
/*      */   public int getSkylightSubtracted() {
/* 3819 */     return this.skylightSubtracted;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setSkylightSubtracted(int newSkylightSubtracted) {
/* 3824 */     this.skylightSubtracted = newSkylightSubtracted;
/*      */   }
/*      */ 
/*      */   
/*      */   public int func_175658_ac() {
/* 3829 */     return this.lastLightningBolt;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setLastLightningBolt(int lastLightningBoltIn) {
/* 3834 */     this.lastLightningBolt = lastLightningBoltIn;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isFindingSpawnPoint() {
/* 3839 */     return this.findingSpawnPoint;
/*      */   }
/*      */ 
/*      */   
/*      */   public VillageCollection getVillageCollection() {
/* 3844 */     return this.villageCollectionObj;
/*      */   }
/*      */ 
/*      */   
/*      */   public WorldBorder getWorldBorder() {
/* 3849 */     return this.worldBorder;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean chunkExists(int x, int z) {
/* 3857 */     BlockPos var3 = getSpawnPoint();
/* 3858 */     int var4 = x * 16 + 8 - var3.getX();
/* 3859 */     int var5 = z * 16 + 8 - var3.getZ();
/* 3860 */     short var6 = 128;
/* 3861 */     return (var4 >= -var6 && var4 <= var6 && var5 >= -var6 && var5 <= var6);
/*      */   }
/*      */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\world\World.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */