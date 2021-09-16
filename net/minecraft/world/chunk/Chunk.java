/*      */ package net.minecraft.world.chunk;
/*      */ 
/*      */ import com.google.common.base.Predicate;
/*      */ import com.google.common.collect.Maps;
/*      */ import com.google.common.collect.Queues;
/*      */ import java.util.Arrays;
/*      */ import java.util.Collection;
/*      */ import java.util.Iterator;
/*      */ import java.util.List;
/*      */ import java.util.Map;
/*      */ import java.util.Random;
/*      */ import java.util.concurrent.Callable;
/*      */ import java.util.concurrent.ConcurrentLinkedQueue;
/*      */ import net.minecraft.block.Block;
/*      */ import net.minecraft.block.ITileEntityProvider;
/*      */ import net.minecraft.block.material.Material;
/*      */ import net.minecraft.block.state.IBlockState;
/*      */ import net.minecraft.crash.CrashReport;
/*      */ import net.minecraft.crash.CrashReportCategory;
/*      */ import net.minecraft.entity.Entity;
/*      */ import net.minecraft.init.Blocks;
/*      */ import net.minecraft.tileentity.TileEntity;
/*      */ import net.minecraft.util.AxisAlignedBB;
/*      */ import net.minecraft.util.BlockPos;
/*      */ import net.minecraft.util.ClassInheratanceMultiMap;
/*      */ import net.minecraft.util.EnumFacing;
/*      */ import net.minecraft.util.MathHelper;
/*      */ import net.minecraft.util.ReportedException;
/*      */ import net.minecraft.world.ChunkCoordIntPair;
/*      */ import net.minecraft.world.EnumSkyBlock;
/*      */ import net.minecraft.world.World;
/*      */ import net.minecraft.world.WorldType;
/*      */ import net.minecraft.world.biome.BiomeGenBase;
/*      */ import net.minecraft.world.biome.WorldChunkManager;
/*      */ import net.minecraft.world.chunk.storage.ExtendedBlockStorage;
/*      */ import net.minecraft.world.gen.ChunkProviderDebug;
/*      */ import org.apache.logging.log4j.LogManager;
/*      */ import org.apache.logging.log4j.Logger;
/*      */ 
/*      */ public class Chunk {
/*   41 */   private static final Logger logger = LogManager.getLogger();
/*      */ 
/*      */ 
/*      */   
/*      */   private final ExtendedBlockStorage[] storageArrays;
/*      */ 
/*      */ 
/*      */   
/*      */   private final byte[] blockBiomeArray;
/*      */ 
/*      */   
/*      */   private final int[] precipitationHeightMap;
/*      */ 
/*      */   
/*      */   private final boolean[] updateSkylightColumns;
/*      */ 
/*      */   
/*      */   private boolean isChunkLoaded;
/*      */ 
/*      */   
/*      */   private final World worldObj;
/*      */ 
/*      */   
/*      */   private final int[] heightMap;
/*      */ 
/*      */   
/*      */   public final int xPosition;
/*      */ 
/*      */   
/*      */   public final int zPosition;
/*      */ 
/*      */   
/*      */   private boolean isGapLightingUpdated;
/*      */ 
/*      */   
/*      */   private final Map chunkTileEntityMap;
/*      */ 
/*      */   
/*      */   private final ClassInheratanceMultiMap[] entityLists;
/*      */ 
/*      */   
/*      */   private boolean isTerrainPopulated;
/*      */ 
/*      */   
/*      */   private boolean isLightPopulated;
/*      */ 
/*      */   
/*      */   private boolean field_150815_m;
/*      */ 
/*      */   
/*      */   private boolean isModified;
/*      */ 
/*      */   
/*      */   private boolean hasEntities;
/*      */ 
/*      */   
/*      */   private long lastSaveTime;
/*      */ 
/*      */   
/*      */   private int heightMapMinimum;
/*      */ 
/*      */   
/*      */   private long inhabitedTime;
/*      */ 
/*      */   
/*      */   private int queuedLightChecks;
/*      */ 
/*      */   
/*      */   private ConcurrentLinkedQueue field_177447_w;
/*      */ 
/*      */   
/*      */   private static final String __OBFID = "CL_00000373";
/*      */ 
/*      */ 
/*      */   
/*      */   public Chunk(World worldIn, int x, int z) {
/*  117 */     this.storageArrays = new ExtendedBlockStorage[16];
/*  118 */     this.blockBiomeArray = new byte[256];
/*  119 */     this.precipitationHeightMap = new int[256];
/*  120 */     this.updateSkylightColumns = new boolean[256];
/*  121 */     this.chunkTileEntityMap = Maps.newHashMap();
/*  122 */     this.queuedLightChecks = 4096;
/*  123 */     this.field_177447_w = Queues.newConcurrentLinkedQueue();
/*  124 */     this.entityLists = new ClassInheratanceMultiMap[16];
/*  125 */     this.worldObj = worldIn;
/*  126 */     this.xPosition = x;
/*  127 */     this.zPosition = z;
/*  128 */     this.heightMap = new int[256];
/*      */     
/*  130 */     for (int var4 = 0; var4 < this.entityLists.length; var4++)
/*      */     {
/*  132 */       this.entityLists[var4] = new ClassInheratanceMultiMap(Entity.class);
/*      */     }
/*      */     
/*  135 */     Arrays.fill(this.precipitationHeightMap, -999);
/*  136 */     Arrays.fill(this.blockBiomeArray, (byte)-1);
/*      */   }
/*      */ 
/*      */   
/*      */   public Chunk(World worldIn, ChunkPrimer primer, int x, int z) {
/*  141 */     this(worldIn, x, z);
/*  142 */     short var5 = 256;
/*  143 */     boolean var6 = !worldIn.provider.getHasNoSky();
/*      */     
/*  145 */     for (int var7 = 0; var7 < 16; var7++) {
/*      */       
/*  147 */       for (int var8 = 0; var8 < 16; var8++) {
/*      */         
/*  149 */         for (int var9 = 0; var9 < var5; var9++) {
/*      */           
/*  151 */           int var10 = var7 * var5 * 16 | var8 * var5 | var9;
/*  152 */           IBlockState var11 = primer.getBlockState(var10);
/*      */           
/*  154 */           if (var11.getBlock().getMaterial() != Material.air) {
/*      */             
/*  156 */             int var12 = var9 >> 4;
/*      */             
/*  158 */             if (this.storageArrays[var12] == null)
/*      */             {
/*  160 */               this.storageArrays[var12] = new ExtendedBlockStorage(var12 << 4, var6);
/*      */             }
/*      */             
/*  163 */             this.storageArrays[var12].set(var7, var9 & 0xF, var8, var11);
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isAtLocation(int x, int z) {
/*  175 */     return (x == this.xPosition && z == this.zPosition);
/*      */   }
/*      */ 
/*      */   
/*      */   public int getHeight(BlockPos pos) {
/*  180 */     return getHeight(pos.getX() & 0xF, pos.getZ() & 0xF);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getHeight(int x, int z) {
/*  188 */     return this.heightMap[z << 4 | x];
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getTopFilledSegment() {
/*  196 */     for (int var1 = this.storageArrays.length - 1; var1 >= 0; var1--) {
/*      */       
/*  198 */       if (this.storageArrays[var1] != null)
/*      */       {
/*  200 */         return this.storageArrays[var1].getYLocation();
/*      */       }
/*      */     } 
/*      */     
/*  204 */     return 0;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ExtendedBlockStorage[] getBlockStorageArray() {
/*  212 */     return this.storageArrays;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void generateHeightMap() {
/*  220 */     int var1 = getTopFilledSegment();
/*  221 */     this.heightMapMinimum = Integer.MAX_VALUE;
/*      */     
/*  223 */     for (int var2 = 0; var2 < 16; var2++) {
/*      */       
/*  225 */       int var3 = 0;
/*      */       
/*  227 */       while (var3 < 16) {
/*      */         
/*  229 */         this.precipitationHeightMap[var2 + (var3 << 4)] = -999;
/*  230 */         int var4 = var1 + 16;
/*      */ 
/*      */ 
/*      */         
/*  234 */         while (var4 > 0) {
/*      */           
/*  236 */           Block var5 = getBlock0(var2, var4 - 1, var3);
/*      */           
/*  238 */           if (var5.getLightOpacity() == 0) {
/*      */             
/*  240 */             var4--;
/*      */             
/*      */             continue;
/*      */           } 
/*  244 */           this.heightMap[var3 << 4 | var2] = var4;
/*      */           
/*  246 */           if (var4 < this.heightMapMinimum)
/*      */           {
/*  248 */             this.heightMapMinimum = var4;
/*      */           }
/*      */         } 
/*      */         
/*  252 */         var3++;
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*  258 */     this.isModified = true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void generateSkylightMap() {
/*  266 */     int var1 = getTopFilledSegment();
/*  267 */     this.heightMapMinimum = Integer.MAX_VALUE;
/*      */     
/*  269 */     for (int var2 = 0; var2 < 16; var2++) {
/*      */       
/*  271 */       int var3 = 0;
/*      */       
/*  273 */       while (var3 < 16) {
/*      */         
/*  275 */         this.precipitationHeightMap[var2 + (var3 << 4)] = -999;
/*  276 */         int var4 = var1 + 16;
/*      */ 
/*      */ 
/*      */         
/*  280 */         while (var4 > 0) {
/*      */           
/*  282 */           if (getBlockLightOpacity(var2, var4 - 1, var3) == 0) {
/*      */             
/*  284 */             var4--;
/*      */             
/*      */             continue;
/*      */           } 
/*  288 */           this.heightMap[var3 << 4 | var2] = var4;
/*      */           
/*  290 */           if (var4 < this.heightMapMinimum)
/*      */           {
/*  292 */             this.heightMapMinimum = var4;
/*      */           }
/*      */         } 
/*      */         
/*  296 */         if (!this.worldObj.provider.getHasNoSky()) {
/*      */           
/*  298 */           var4 = 15;
/*  299 */           int var5 = var1 + 16 - 1;
/*      */ 
/*      */           
/*      */           do {
/*  303 */             int var6 = getBlockLightOpacity(var2, var5, var3);
/*      */             
/*  305 */             if (var6 == 0 && var4 != 15)
/*      */             {
/*  307 */               var6 = 1;
/*      */             }
/*      */             
/*  310 */             var4 -= var6;
/*      */             
/*  312 */             if (var4 <= 0)
/*      */               continue; 
/*  314 */             ExtendedBlockStorage var7 = this.storageArrays[var5 >> 4];
/*      */             
/*  316 */             if (var7 == null)
/*      */               continue; 
/*  318 */             var7.setExtSkylightValue(var2, var5 & 0xF, var3, var4);
/*  319 */             this.worldObj.notifyLightSet(new BlockPos((this.xPosition << 4) + var2, var5, (this.zPosition << 4) + var3));
/*      */ 
/*      */ 
/*      */             
/*  323 */             --var5;
/*      */           }
/*  325 */           while (var5 > 0 && var4 > 0);
/*      */         } 
/*      */         
/*  328 */         var3++;
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*  334 */     this.isModified = true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void propagateSkylightOcclusion(int x, int z) {
/*  342 */     this.updateSkylightColumns[x + z * 16] = true;
/*  343 */     this.isGapLightingUpdated = true;
/*      */   }
/*      */ 
/*      */   
/*      */   private void recheckGaps(boolean p_150803_1_) {
/*  348 */     this.worldObj.theProfiler.startSection("recheckGaps");
/*      */     
/*  350 */     if (this.worldObj.isAreaLoaded(new BlockPos(this.xPosition * 16 + 8, 0, this.zPosition * 16 + 8), 16)) {
/*      */       
/*  352 */       for (int var2 = 0; var2 < 16; var2++) {
/*      */         
/*  354 */         for (int var3 = 0; var3 < 16; var3++) {
/*      */           
/*  356 */           if (this.updateSkylightColumns[var2 + var3 * 16]) {
/*      */             
/*  358 */             this.updateSkylightColumns[var2 + var3 * 16] = false;
/*  359 */             int var4 = getHeight(var2, var3);
/*  360 */             int var5 = this.xPosition * 16 + var2;
/*  361 */             int var6 = this.zPosition * 16 + var3;
/*  362 */             int var7 = Integer.MAX_VALUE;
/*      */             
/*      */             Iterator<EnumFacing> var8;
/*      */             
/*  366 */             for (var8 = EnumFacing.Plane.HORIZONTAL.iterator(); var8.hasNext(); var7 = Math.min(var7, this.worldObj.getChunksLowestHorizon(var5 + var9.getFrontOffsetX(), var6 + var9.getFrontOffsetZ())))
/*      */             {
/*  368 */               EnumFacing var9 = var8.next();
/*      */             }
/*      */             
/*  371 */             checkSkylightNeighborHeight(var5, var6, var7);
/*  372 */             var8 = EnumFacing.Plane.HORIZONTAL.iterator();
/*      */             
/*  374 */             while (var8.hasNext()) {
/*      */               
/*  376 */               EnumFacing var9 = var8.next();
/*  377 */               checkSkylightNeighborHeight(var5 + var9.getFrontOffsetX(), var6 + var9.getFrontOffsetZ(), var4);
/*      */             } 
/*      */             
/*  380 */             if (p_150803_1_) {
/*      */               
/*  382 */               this.worldObj.theProfiler.endSection();
/*      */               
/*      */               return;
/*      */             } 
/*      */           } 
/*      */         } 
/*      */       } 
/*  389 */       this.isGapLightingUpdated = false;
/*      */     } 
/*      */     
/*  392 */     this.worldObj.theProfiler.endSection();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void checkSkylightNeighborHeight(int x, int p_76599_2_, int z) {
/*  400 */     int var4 = this.worldObj.getHorizon(new BlockPos(x, 0, p_76599_2_)).getY();
/*      */     
/*  402 */     if (var4 > z) {
/*      */       
/*  404 */       updateSkylightNeighborHeight(x, p_76599_2_, z, var4 + 1);
/*      */     }
/*  406 */     else if (var4 < z) {
/*      */       
/*  408 */       updateSkylightNeighborHeight(x, p_76599_2_, var4, z + 1);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   private void updateSkylightNeighborHeight(int x, int z, int startY, int endY) {
/*  414 */     if (endY > startY && this.worldObj.isAreaLoaded(new BlockPos(x, 0, z), 16)) {
/*      */       
/*  416 */       for (int var5 = startY; var5 < endY; var5++)
/*      */       {
/*  418 */         this.worldObj.checkLightFor(EnumSkyBlock.SKY, new BlockPos(x, var5, z));
/*      */       }
/*      */       
/*  421 */       this.isModified = true;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void relightBlock(int x, int y, int z) {
/*  430 */     int var4 = this.heightMap[z << 4 | x] & 0xFF;
/*  431 */     int var5 = var4;
/*      */     
/*  433 */     if (y > var4)
/*      */     {
/*  435 */       var5 = y;
/*      */     }
/*      */     
/*  438 */     while (var5 > 0 && getBlockLightOpacity(x, var5 - 1, z) == 0)
/*      */     {
/*  440 */       var5--;
/*      */     }
/*      */     
/*  443 */     if (var5 != var4) {
/*      */       
/*  445 */       this.worldObj.markBlocksDirtyVertical(x + this.xPosition * 16, z + this.zPosition * 16, var5, var4);
/*  446 */       this.heightMap[z << 4 | x] = var5;
/*  447 */       int var6 = this.xPosition * 16 + x;
/*  448 */       int var7 = this.zPosition * 16 + z;
/*      */ 
/*      */ 
/*      */       
/*  452 */       if (!this.worldObj.provider.getHasNoSky()) {
/*      */ 
/*      */ 
/*      */         
/*  456 */         if (var5 < var4) {
/*      */           
/*  458 */           for (int j = var5; j < var4; j++) {
/*      */             
/*  460 */             ExtendedBlockStorage var9 = this.storageArrays[j >> 4];
/*      */             
/*  462 */             if (var9 != null)
/*      */             {
/*  464 */               var9.setExtSkylightValue(x, j & 0xF, z, 15);
/*  465 */               this.worldObj.notifyLightSet(new BlockPos((this.xPosition << 4) + x, j, (this.zPosition << 4) + z));
/*      */             }
/*      */           
/*      */           } 
/*      */         } else {
/*      */           
/*  471 */           for (int j = var4; j < var5; j++) {
/*      */             
/*  473 */             ExtendedBlockStorage var9 = this.storageArrays[j >> 4];
/*      */             
/*  475 */             if (var9 != null) {
/*      */               
/*  477 */               var9.setExtSkylightValue(x, j & 0xF, z, 0);
/*  478 */               this.worldObj.notifyLightSet(new BlockPos((this.xPosition << 4) + x, j, (this.zPosition << 4) + z));
/*      */             } 
/*      */           } 
/*      */         } 
/*      */         
/*  483 */         int i = 15;
/*      */         
/*  485 */         while (var5 > 0 && i > 0) {
/*      */           
/*  487 */           var5--;
/*  488 */           int j = getBlockLightOpacity(x, var5, z);
/*      */           
/*  490 */           if (j == 0)
/*      */           {
/*  492 */             j = 1;
/*      */           }
/*      */           
/*  495 */           i -= j;
/*      */           
/*  497 */           if (i < 0)
/*      */           {
/*  499 */             i = 0;
/*      */           }
/*      */           
/*  502 */           ExtendedBlockStorage var10 = this.storageArrays[var5 >> 4];
/*      */           
/*  504 */           if (var10 != null)
/*      */           {
/*  506 */             var10.setExtSkylightValue(x, var5 & 0xF, z, i);
/*      */           }
/*      */         } 
/*      */       } 
/*      */       
/*  511 */       int var8 = this.heightMap[z << 4 | x];
/*  512 */       int var13 = var4;
/*  513 */       int var14 = var8;
/*      */       
/*  515 */       if (var8 < var4) {
/*      */         
/*  517 */         var13 = var8;
/*  518 */         var14 = var4;
/*      */       } 
/*      */       
/*  521 */       if (var8 < this.heightMapMinimum)
/*      */       {
/*  523 */         this.heightMapMinimum = var8;
/*      */       }
/*      */       
/*  526 */       if (!this.worldObj.provider.getHasNoSky()) {
/*      */         
/*  528 */         Iterator<EnumFacing> var11 = EnumFacing.Plane.HORIZONTAL.iterator();
/*      */         
/*  530 */         while (var11.hasNext()) {
/*      */           
/*  532 */           EnumFacing var12 = var11.next();
/*  533 */           updateSkylightNeighborHeight(var6 + var12.getFrontOffsetX(), var7 + var12.getFrontOffsetZ(), var13, var14);
/*      */         } 
/*      */         
/*  536 */         updateSkylightNeighborHeight(var6, var7, var13, var14);
/*      */       } 
/*      */       
/*  539 */       this.isModified = true;
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public int getBlockLightOpacity(BlockPos pos) {
/*  545 */     return getBlock(pos).getLightOpacity();
/*      */   }
/*      */ 
/*      */   
/*      */   private int getBlockLightOpacity(int p_150808_1_, int p_150808_2_, int p_150808_3_) {
/*  550 */     return getBlock0(p_150808_1_, p_150808_2_, p_150808_3_).getLightOpacity();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private Block getBlock0(int x, int y, int z) {
/*  558 */     Block var4 = Blocks.air;
/*      */     
/*  560 */     if (y >= 0 && y >> 4 < this.storageArrays.length) {
/*      */       
/*  562 */       ExtendedBlockStorage var5 = this.storageArrays[y >> 4];
/*      */       
/*  564 */       if (var5 != null) {
/*      */         
/*      */         try {
/*      */           
/*  568 */           var4 = var5.getBlockByExtId(x, y & 0xF, z);
/*      */         }
/*  570 */         catch (Throwable var8) {
/*      */           
/*  572 */           CrashReport var7 = CrashReport.makeCrashReport(var8, "Getting block");
/*  573 */           throw new ReportedException(var7);
/*      */         } 
/*      */       }
/*      */     } 
/*      */     
/*  578 */     return var4;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public Block getBlock(final int x, final int y, final int z) {
/*      */     try {
/*  585 */       return getBlock0(x & 0xF, y, z & 0xF);
/*      */     }
/*  587 */     catch (ReportedException var6) {
/*      */       
/*  589 */       CrashReportCategory var5 = var6.getCrashReport().makeCategory("Block being got");
/*  590 */       var5.addCrashSectionCallable("Location", new Callable()
/*      */           {
/*      */             private static final String __OBFID = "CL_00000374";
/*      */             
/*      */             public String call() {
/*  595 */               return CrashReportCategory.getCoordinateInfo(new BlockPos(Chunk.this.xPosition * 16 + x, y, Chunk.this.zPosition * 16 + z));
/*      */             }
/*      */           });
/*  598 */       throw var6;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public Block getBlock(final BlockPos pos) {
/*      */     try {
/*  606 */       return getBlock0(pos.getX() & 0xF, pos.getY(), pos.getZ() & 0xF);
/*      */     }
/*  608 */     catch (ReportedException var4) {
/*      */       
/*  610 */       CrashReportCategory var3 = var4.getCrashReport().makeCategory("Block being got");
/*  611 */       var3.addCrashSectionCallable("Location", new Callable()
/*      */           {
/*      */             private static final String __OBFID = "CL_00002011";
/*      */             
/*      */             public String func_177455_a() {
/*  616 */               return CrashReportCategory.getCoordinateInfo(pos);
/*      */             }
/*      */             
/*      */             public Object call() {
/*  620 */               return func_177455_a();
/*      */             }
/*      */           });
/*  623 */       throw var4;
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public IBlockState getBlockState(final BlockPos pos) {
/*  629 */     if (this.worldObj.getWorldType() == WorldType.DEBUG_WORLD) {
/*      */       
/*  631 */       IBlockState var7 = null;
/*      */       
/*  633 */       if (pos.getY() == 60)
/*      */       {
/*  635 */         var7 = Blocks.barrier.getDefaultState();
/*      */       }
/*      */       
/*  638 */       if (pos.getY() == 70)
/*      */       {
/*  640 */         var7 = ChunkProviderDebug.func_177461_b(pos.getX(), pos.getZ());
/*      */       }
/*      */       
/*  643 */       return (var7 == null) ? Blocks.air.getDefaultState() : var7;
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*      */     try {
/*  649 */       if (pos.getY() >= 0 && pos.getY() >> 4 < this.storageArrays.length) {
/*      */         
/*  651 */         ExtendedBlockStorage var2 = this.storageArrays[pos.getY() >> 4];
/*      */         
/*  653 */         if (var2 != null) {
/*      */           
/*  655 */           int var8 = pos.getX() & 0xF;
/*  656 */           int var9 = pos.getY() & 0xF;
/*  657 */           int var5 = pos.getZ() & 0xF;
/*  658 */           return var2.get(var8, var9, var5);
/*      */         } 
/*      */       } 
/*      */       
/*  662 */       return Blocks.air.getDefaultState();
/*      */     }
/*  664 */     catch (Throwable var6) {
/*      */       
/*  666 */       CrashReport var3 = CrashReport.makeCrashReport(var6, "Getting block state");
/*  667 */       CrashReportCategory var4 = var3.makeCategory("Block being got");
/*  668 */       var4.addCrashSectionCallable("Location", new Callable()
/*      */           {
/*      */             private static final String __OBFID = "CL_00002010";
/*      */             
/*      */             public String func_177448_a() {
/*  673 */               return CrashReportCategory.getCoordinateInfo(pos);
/*      */             }
/*      */             
/*      */             public Object call() {
/*  677 */               return func_177448_a();
/*      */             }
/*      */           });
/*  680 */       throw new ReportedException(var3);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private int getBlockMetadata(int p_76628_1_, int p_76628_2_, int p_76628_3_) {
/*  690 */     if (p_76628_2_ >> 4 >= this.storageArrays.length)
/*      */     {
/*  692 */       return 0;
/*      */     }
/*      */ 
/*      */     
/*  696 */     ExtendedBlockStorage var4 = this.storageArrays[p_76628_2_ >> 4];
/*  697 */     return (var4 != null) ? var4.getExtBlockMetadata(p_76628_1_, p_76628_2_ & 0xF, p_76628_3_) : 0;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public int getBlockMetadata(BlockPos pos) {
/*  703 */     return getBlockMetadata(pos.getX() & 0xF, pos.getY(), pos.getZ() & 0xF);
/*      */   }
/*      */ 
/*      */   
/*      */   public IBlockState setBlockState(BlockPos p_177436_1_, IBlockState p_177436_2_) {
/*  708 */     int var3 = p_177436_1_.getX() & 0xF;
/*  709 */     int var4 = p_177436_1_.getY();
/*  710 */     int var5 = p_177436_1_.getZ() & 0xF;
/*  711 */     int var6 = var5 << 4 | var3;
/*      */     
/*  713 */     if (var4 >= this.precipitationHeightMap[var6] - 1)
/*      */     {
/*  715 */       this.precipitationHeightMap[var6] = -999;
/*      */     }
/*      */     
/*  718 */     int var7 = this.heightMap[var6];
/*  719 */     IBlockState var8 = getBlockState(p_177436_1_);
/*      */     
/*  721 */     if (var8 == p_177436_2_)
/*      */     {
/*  723 */       return null;
/*      */     }
/*      */ 
/*      */     
/*  727 */     Block var9 = p_177436_2_.getBlock();
/*  728 */     Block var10 = var8.getBlock();
/*  729 */     ExtendedBlockStorage var11 = this.storageArrays[var4 >> 4];
/*  730 */     boolean var12 = false;
/*      */     
/*  732 */     if (var11 == null) {
/*      */       
/*  734 */       if (var9 == Blocks.air)
/*      */       {
/*  736 */         return null;
/*      */       }
/*      */       
/*  739 */       var11 = this.storageArrays[var4 >> 4] = new ExtendedBlockStorage(var4 >> 4 << 4, !this.worldObj.provider.getHasNoSky());
/*  740 */       var12 = (var4 >= var7);
/*      */     } 
/*      */     
/*  743 */     var11.set(var3, var4 & 0xF, var5, p_177436_2_);
/*      */     
/*  745 */     if (var10 != var9)
/*      */     {
/*  747 */       if (!this.worldObj.isRemote) {
/*      */         
/*  749 */         var10.breakBlock(this.worldObj, p_177436_1_, var8);
/*      */       }
/*  751 */       else if (var10 instanceof ITileEntityProvider) {
/*      */         
/*  753 */         this.worldObj.removeTileEntity(p_177436_1_);
/*      */       } 
/*      */     }
/*      */     
/*  757 */     if (var11.getBlockByExtId(var3, var4 & 0xF, var5) != var9)
/*      */     {
/*  759 */       return null;
/*      */     }
/*      */ 
/*      */     
/*  763 */     if (var12) {
/*      */       
/*  765 */       generateSkylightMap();
/*      */     }
/*      */     else {
/*      */       
/*  769 */       int var13 = var9.getLightOpacity();
/*  770 */       int var14 = var10.getLightOpacity();
/*      */       
/*  772 */       if (var13 > 0) {
/*      */         
/*  774 */         if (var4 >= var7)
/*      */         {
/*  776 */           relightBlock(var3, var4 + 1, var5);
/*      */         }
/*      */       }
/*  779 */       else if (var4 == var7 - 1) {
/*      */         
/*  781 */         relightBlock(var3, var4, var5);
/*      */       } 
/*      */       
/*  784 */       if (var13 != var14 && (var13 < var14 || getLightFor(EnumSkyBlock.SKY, p_177436_1_) > 0 || getLightFor(EnumSkyBlock.BLOCK, p_177436_1_) > 0))
/*      */       {
/*  786 */         propagateSkylightOcclusion(var3, var5);
/*      */       }
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*  792 */     if (var10 instanceof ITileEntityProvider) {
/*      */       
/*  794 */       TileEntity var15 = func_177424_a(p_177436_1_, EnumCreateEntityType.CHECK);
/*      */       
/*  796 */       if (var15 != null)
/*      */       {
/*  798 */         var15.updateContainingBlockInfo();
/*      */       }
/*      */     } 
/*      */     
/*  802 */     if (!this.worldObj.isRemote && var10 != var9)
/*      */     {
/*  804 */       var9.onBlockAdded(this.worldObj, p_177436_1_, p_177436_2_);
/*      */     }
/*      */     
/*  807 */     if (var9 instanceof ITileEntityProvider) {
/*      */       
/*  809 */       TileEntity var15 = func_177424_a(p_177436_1_, EnumCreateEntityType.CHECK);
/*      */       
/*  811 */       if (var15 == null) {
/*      */         
/*  813 */         var15 = ((ITileEntityProvider)var9).createNewTileEntity(this.worldObj, var9.getMetaFromState(p_177436_2_));
/*  814 */         this.worldObj.setTileEntity(p_177436_1_, var15);
/*      */       } 
/*      */       
/*  817 */       if (var15 != null)
/*      */       {
/*  819 */         var15.updateContainingBlockInfo();
/*      */       }
/*      */     } 
/*      */     
/*  823 */     this.isModified = true;
/*  824 */     return var8;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getLightFor(EnumSkyBlock p_177413_1_, BlockPos p_177413_2_) {
/*  831 */     int var3 = p_177413_2_.getX() & 0xF;
/*  832 */     int var4 = p_177413_2_.getY();
/*  833 */     int var5 = p_177413_2_.getZ() & 0xF;
/*  834 */     ExtendedBlockStorage var6 = this.storageArrays[var4 >> 4];
/*  835 */     return (var6 == null) ? (canSeeSky(p_177413_2_) ? p_177413_1_.defaultLightValue : 0) : ((p_177413_1_ == EnumSkyBlock.SKY) ? (this.worldObj.provider.getHasNoSky() ? 0 : var6.getExtSkylightValue(var3, var4 & 0xF, var5)) : ((p_177413_1_ == EnumSkyBlock.BLOCK) ? var6.getExtBlocklightValue(var3, var4 & 0xF, var5) : p_177413_1_.defaultLightValue));
/*      */   }
/*      */ 
/*      */   
/*      */   public void setLightFor(EnumSkyBlock p_177431_1_, BlockPos p_177431_2_, int p_177431_3_) {
/*  840 */     int var4 = p_177431_2_.getX() & 0xF;
/*  841 */     int var5 = p_177431_2_.getY();
/*  842 */     int var6 = p_177431_2_.getZ() & 0xF;
/*  843 */     ExtendedBlockStorage var7 = this.storageArrays[var5 >> 4];
/*      */     
/*  845 */     if (var7 == null) {
/*      */       
/*  847 */       var7 = this.storageArrays[var5 >> 4] = new ExtendedBlockStorage(var5 >> 4 << 4, !this.worldObj.provider.getHasNoSky());
/*  848 */       generateSkylightMap();
/*      */     } 
/*      */     
/*  851 */     this.isModified = true;
/*      */     
/*  853 */     if (p_177431_1_ == EnumSkyBlock.SKY) {
/*      */       
/*  855 */       if (!this.worldObj.provider.getHasNoSky())
/*      */       {
/*  857 */         var7.setExtSkylightValue(var4, var5 & 0xF, var6, p_177431_3_);
/*      */       }
/*      */     }
/*  860 */     else if (p_177431_1_ == EnumSkyBlock.BLOCK) {
/*      */       
/*  862 */       var7.setExtBlocklightValue(var4, var5 & 0xF, var6, p_177431_3_);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public int setLight(BlockPos p_177443_1_, int p_177443_2_) {
/*  868 */     int var3 = p_177443_1_.getX() & 0xF;
/*  869 */     int var4 = p_177443_1_.getY();
/*  870 */     int var5 = p_177443_1_.getZ() & 0xF;
/*  871 */     ExtendedBlockStorage var6 = this.storageArrays[var4 >> 4];
/*      */     
/*  873 */     if (var6 == null)
/*      */     {
/*  875 */       return (!this.worldObj.provider.getHasNoSky() && p_177443_2_ < EnumSkyBlock.SKY.defaultLightValue) ? (EnumSkyBlock.SKY.defaultLightValue - p_177443_2_) : 0;
/*      */     }
/*      */ 
/*      */     
/*  879 */     int var7 = this.worldObj.provider.getHasNoSky() ? 0 : var6.getExtSkylightValue(var3, var4 & 0xF, var5);
/*  880 */     var7 -= p_177443_2_;
/*  881 */     int var8 = var6.getExtBlocklightValue(var3, var4 & 0xF, var5);
/*      */     
/*  883 */     if (var8 > var7)
/*      */     {
/*  885 */       var7 = var8;
/*      */     }
/*      */     
/*  888 */     return var7;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void addEntity(Entity entityIn) {
/*  897 */     this.hasEntities = true;
/*  898 */     int var2 = MathHelper.floor_double(entityIn.posX / 16.0D);
/*  899 */     int var3 = MathHelper.floor_double(entityIn.posZ / 16.0D);
/*      */     
/*  901 */     if (var2 != this.xPosition || var3 != this.zPosition) {
/*      */       
/*  903 */       logger.warn("Wrong location! (" + var2 + ", " + var3 + ") should be (" + this.xPosition + ", " + this.zPosition + "), " + entityIn, new Object[] { entityIn });
/*  904 */       entityIn.setDead();
/*      */     } 
/*      */     
/*  907 */     int var4 = MathHelper.floor_double(entityIn.posY / 16.0D);
/*      */     
/*  909 */     if (var4 < 0)
/*      */     {
/*  911 */       var4 = 0;
/*      */     }
/*      */     
/*  914 */     if (var4 >= this.entityLists.length)
/*      */     {
/*  916 */       var4 = this.entityLists.length - 1;
/*      */     }
/*      */     
/*  919 */     entityIn.addedToChunk = true;
/*  920 */     entityIn.chunkCoordX = this.xPosition;
/*  921 */     entityIn.chunkCoordY = var4;
/*  922 */     entityIn.chunkCoordZ = this.zPosition;
/*  923 */     this.entityLists[var4].add(entityIn);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void removeEntity(Entity p_76622_1_) {
/*  931 */     removeEntityAtIndex(p_76622_1_, p_76622_1_.chunkCoordY);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void removeEntityAtIndex(Entity p_76608_1_, int p_76608_2_) {
/*  939 */     if (p_76608_2_ < 0)
/*      */     {
/*  941 */       p_76608_2_ = 0;
/*      */     }
/*      */     
/*  944 */     if (p_76608_2_ >= this.entityLists.length)
/*      */     {
/*  946 */       p_76608_2_ = this.entityLists.length - 1;
/*      */     }
/*      */     
/*  949 */     this.entityLists[p_76608_2_].remove(p_76608_1_);
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean canSeeSky(BlockPos pos) {
/*  954 */     int var2 = pos.getX() & 0xF;
/*  955 */     int var3 = pos.getY();
/*  956 */     int var4 = pos.getZ() & 0xF;
/*  957 */     return (var3 >= this.heightMap[var4 << 4 | var2]);
/*      */   }
/*      */ 
/*      */   
/*      */   private TileEntity createNewTileEntity(BlockPos pos) {
/*  962 */     Block var2 = getBlock(pos);
/*  963 */     return !var2.hasTileEntity() ? null : ((ITileEntityProvider)var2).createNewTileEntity(this.worldObj, getBlockMetadata(pos));
/*      */   }
/*      */ 
/*      */   
/*      */   public TileEntity func_177424_a(BlockPos p_177424_1_, EnumCreateEntityType p_177424_2_) {
/*  968 */     TileEntity var3 = (TileEntity)this.chunkTileEntityMap.get(p_177424_1_);
/*      */     
/*  970 */     if (var3 == null) {
/*      */       
/*  972 */       if (p_177424_2_ == EnumCreateEntityType.IMMEDIATE)
/*      */       {
/*  974 */         var3 = createNewTileEntity(p_177424_1_);
/*  975 */         this.worldObj.setTileEntity(p_177424_1_, var3);
/*      */       }
/*  977 */       else if (p_177424_2_ == EnumCreateEntityType.QUEUED)
/*      */       {
/*  979 */         this.field_177447_w.add(p_177424_1_);
/*      */       }
/*      */     
/*  982 */     } else if (var3.isInvalid()) {
/*      */       
/*  984 */       this.chunkTileEntityMap.remove(p_177424_1_);
/*  985 */       return null;
/*      */     } 
/*      */     
/*  988 */     return var3;
/*      */   }
/*      */ 
/*      */   
/*      */   public void addTileEntity(TileEntity tileEntityIn) {
/*  993 */     addTileEntity(tileEntityIn.getPos(), tileEntityIn);
/*      */     
/*  995 */     if (this.isChunkLoaded)
/*      */     {
/*  997 */       this.worldObj.addTileEntity(tileEntityIn);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void addTileEntity(BlockPos pos, TileEntity tileEntityIn) {
/* 1003 */     tileEntityIn.setWorldObj(this.worldObj);
/* 1004 */     tileEntityIn.setPos(pos);
/*      */     
/* 1006 */     if (getBlock(pos) instanceof ITileEntityProvider) {
/*      */       
/* 1008 */       if (this.chunkTileEntityMap.containsKey(pos))
/*      */       {
/* 1010 */         ((TileEntity)this.chunkTileEntityMap.get(pos)).invalidate();
/*      */       }
/*      */       
/* 1013 */       tileEntityIn.validate();
/* 1014 */       this.chunkTileEntityMap.put(pos, tileEntityIn);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public void removeTileEntity(BlockPos pos) {
/* 1020 */     if (this.isChunkLoaded) {
/*      */       
/* 1022 */       TileEntity var2 = (TileEntity)this.chunkTileEntityMap.remove(pos);
/*      */       
/* 1024 */       if (var2 != null)
/*      */       {
/* 1026 */         var2.invalidate();
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void onChunkLoad() {
/* 1036 */     this.isChunkLoaded = true;
/* 1037 */     this.worldObj.addTileEntities(this.chunkTileEntityMap.values());
/*      */     
/* 1039 */     for (int var1 = 0; var1 < this.entityLists.length; var1++) {
/*      */       
/* 1041 */       Iterator<Entity> var2 = this.entityLists[var1].iterator();
/*      */       
/* 1043 */       while (var2.hasNext()) {
/*      */         
/* 1045 */         Entity var3 = var2.next();
/* 1046 */         var3.onChunkLoad();
/*      */       } 
/*      */       
/* 1049 */       this.worldObj.loadEntities((Collection)this.entityLists[var1]);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void onChunkUnload() {
/* 1058 */     this.isChunkLoaded = false;
/* 1059 */     Iterator<TileEntity> var1 = this.chunkTileEntityMap.values().iterator();
/*      */     
/* 1061 */     while (var1.hasNext()) {
/*      */       
/* 1063 */       TileEntity var2 = var1.next();
/* 1064 */       this.worldObj.markTileEntityForRemoval(var2);
/*      */     } 
/*      */     
/* 1067 */     for (int var3 = 0; var3 < this.entityLists.length; var3++)
/*      */     {
/* 1069 */       this.worldObj.unloadEntities((Collection)this.entityLists[var3]);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setChunkModified() {
/* 1078 */     this.isModified = true;
/*      */   }
/*      */ 
/*      */   
/*      */   public void func_177414_a(Entity p_177414_1_, AxisAlignedBB p_177414_2_, List<Entity> p_177414_3_, Predicate p_177414_4_) {
/* 1083 */     int var5 = MathHelper.floor_double((p_177414_2_.minY - 2.0D) / 16.0D);
/* 1084 */     int var6 = MathHelper.floor_double((p_177414_2_.maxY + 2.0D) / 16.0D);
/* 1085 */     var5 = MathHelper.clamp_int(var5, 0, this.entityLists.length - 1);
/* 1086 */     var6 = MathHelper.clamp_int(var6, 0, this.entityLists.length - 1);
/*      */     
/* 1088 */     for (int var7 = var5; var7 <= var6; var7++) {
/*      */       
/* 1090 */       Iterator<Entity> var8 = this.entityLists[var7].iterator();
/*      */       
/* 1092 */       while (var8.hasNext()) {
/*      */         
/* 1094 */         Entity var9 = var8.next();
/*      */         
/* 1096 */         if (var9 != p_177414_1_ && var9.getEntityBoundingBox().intersectsWith(p_177414_2_) && (p_177414_4_ == null || p_177414_4_.apply(var9))) {
/*      */           
/* 1098 */           p_177414_3_.add(var9);
/* 1099 */           Entity[] var10 = var9.getParts();
/*      */           
/* 1101 */           if (var10 != null)
/*      */           {
/* 1103 */             for (int var11 = 0; var11 < var10.length; var11++) {
/*      */               
/* 1105 */               var9 = var10[var11];
/*      */               
/* 1107 */               if (var9 != p_177414_1_ && var9.getEntityBoundingBox().intersectsWith(p_177414_2_) && (p_177414_4_ == null || p_177414_4_.apply(var9)))
/*      */               {
/* 1109 */                 p_177414_3_.add(var9);
/*      */               }
/*      */             } 
/*      */           }
/*      */         } 
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public void func_177430_a(Class p_177430_1_, AxisAlignedBB p_177430_2_, List<Entity> p_177430_3_, Predicate p_177430_4_) {
/* 1120 */     int var5 = MathHelper.floor_double((p_177430_2_.minY - 2.0D) / 16.0D);
/* 1121 */     int var6 = MathHelper.floor_double((p_177430_2_.maxY + 2.0D) / 16.0D);
/* 1122 */     var5 = MathHelper.clamp_int(var5, 0, this.entityLists.length - 1);
/* 1123 */     var6 = MathHelper.clamp_int(var6, 0, this.entityLists.length - 1);
/*      */     
/* 1125 */     for (int var7 = var5; var7 <= var6; var7++) {
/*      */       
/* 1127 */       Iterator<Entity> var8 = this.entityLists[var7].func_180215_b(p_177430_1_).iterator();
/*      */       
/* 1129 */       while (var8.hasNext()) {
/*      */         
/* 1131 */         Entity var9 = var8.next();
/*      */         
/* 1133 */         if (var9.getEntityBoundingBox().intersectsWith(p_177430_2_) && (p_177430_4_ == null || p_177430_4_.apply(var9)))
/*      */         {
/* 1135 */           p_177430_3_.add(var9);
/*      */         }
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean needsSaving(boolean p_76601_1_) {
/* 1146 */     if (p_76601_1_) {
/*      */       
/* 1148 */       if ((this.hasEntities && this.worldObj.getTotalWorldTime() != this.lastSaveTime) || this.isModified)
/*      */       {
/* 1150 */         return true;
/*      */       }
/*      */     }
/* 1153 */     else if (this.hasEntities && this.worldObj.getTotalWorldTime() >= this.lastSaveTime + 600L) {
/*      */       
/* 1155 */       return true;
/*      */     } 
/*      */     
/* 1158 */     return this.isModified;
/*      */   }
/*      */ 
/*      */   
/*      */   public Random getRandomWithSeed(long seed) {
/* 1163 */     return new Random(this.worldObj.getSeed() + (this.xPosition * this.xPosition * 4987142) + (this.xPosition * 5947611) + (this.zPosition * this.zPosition) * 4392871L + (this.zPosition * 389711) ^ seed);
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isEmpty() {
/* 1168 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   public void populateChunk(IChunkProvider p_76624_1_, IChunkProvider p_76624_2_, int p_76624_3_, int p_76624_4_) {
/* 1173 */     boolean var5 = p_76624_1_.chunkExists(p_76624_3_, p_76624_4_ - 1);
/* 1174 */     boolean var6 = p_76624_1_.chunkExists(p_76624_3_ + 1, p_76624_4_);
/* 1175 */     boolean var7 = p_76624_1_.chunkExists(p_76624_3_, p_76624_4_ + 1);
/* 1176 */     boolean var8 = p_76624_1_.chunkExists(p_76624_3_ - 1, p_76624_4_);
/* 1177 */     boolean var9 = p_76624_1_.chunkExists(p_76624_3_ - 1, p_76624_4_ - 1);
/* 1178 */     boolean var10 = p_76624_1_.chunkExists(p_76624_3_ + 1, p_76624_4_ + 1);
/* 1179 */     boolean var11 = p_76624_1_.chunkExists(p_76624_3_ - 1, p_76624_4_ + 1);
/* 1180 */     boolean var12 = p_76624_1_.chunkExists(p_76624_3_ + 1, p_76624_4_ - 1);
/*      */     
/* 1182 */     if (var6 && var7 && var10)
/*      */     {
/* 1184 */       if (!this.isTerrainPopulated) {
/*      */         
/* 1186 */         p_76624_1_.populate(p_76624_2_, p_76624_3_, p_76624_4_);
/*      */       }
/*      */       else {
/*      */         
/* 1190 */         p_76624_1_.func_177460_a(p_76624_2_, this, p_76624_3_, p_76624_4_);
/*      */       } 
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 1196 */     if (var8 && var7 && var11) {
/*      */       
/* 1198 */       Chunk var13 = p_76624_1_.provideChunk(p_76624_3_ - 1, p_76624_4_);
/*      */       
/* 1200 */       if (!var13.isTerrainPopulated) {
/*      */         
/* 1202 */         p_76624_1_.populate(p_76624_2_, p_76624_3_ - 1, p_76624_4_);
/*      */       }
/*      */       else {
/*      */         
/* 1206 */         p_76624_1_.func_177460_a(p_76624_2_, var13, p_76624_3_ - 1, p_76624_4_);
/*      */       } 
/*      */     } 
/*      */     
/* 1210 */     if (var5 && var6 && var12) {
/*      */       
/* 1212 */       Chunk var13 = p_76624_1_.provideChunk(p_76624_3_, p_76624_4_ - 1);
/*      */       
/* 1214 */       if (!var13.isTerrainPopulated) {
/*      */         
/* 1216 */         p_76624_1_.populate(p_76624_2_, p_76624_3_, p_76624_4_ - 1);
/*      */       }
/*      */       else {
/*      */         
/* 1220 */         p_76624_1_.func_177460_a(p_76624_2_, var13, p_76624_3_, p_76624_4_ - 1);
/*      */       } 
/*      */     } 
/*      */     
/* 1224 */     if (var9 && var5 && var8) {
/*      */       
/* 1226 */       Chunk var13 = p_76624_1_.provideChunk(p_76624_3_ - 1, p_76624_4_ - 1);
/*      */       
/* 1228 */       if (!var13.isTerrainPopulated) {
/*      */         
/* 1230 */         p_76624_1_.populate(p_76624_2_, p_76624_3_ - 1, p_76624_4_ - 1);
/*      */       }
/*      */       else {
/*      */         
/* 1234 */         p_76624_1_.func_177460_a(p_76624_2_, var13, p_76624_3_ - 1, p_76624_4_ - 1);
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public BlockPos func_177440_h(BlockPos p_177440_1_) {
/* 1241 */     int var2 = p_177440_1_.getX() & 0xF;
/* 1242 */     int var3 = p_177440_1_.getZ() & 0xF;
/* 1243 */     int var4 = var2 | var3 << 4;
/* 1244 */     BlockPos var5 = new BlockPos(p_177440_1_.getX(), this.precipitationHeightMap[var4], p_177440_1_.getZ());
/*      */     
/* 1246 */     if (var5.getY() == -999) {
/*      */       
/* 1248 */       int var6 = getTopFilledSegment() + 15;
/* 1249 */       var5 = new BlockPos(p_177440_1_.getX(), var6, p_177440_1_.getZ());
/* 1250 */       int var7 = -1;
/*      */       
/* 1252 */       while (var5.getY() > 0 && var7 == -1) {
/*      */         
/* 1254 */         Block var8 = getBlock(var5);
/* 1255 */         Material var9 = var8.getMaterial();
/*      */         
/* 1257 */         if (!var9.blocksMovement() && !var9.isLiquid()) {
/*      */           
/* 1259 */           var5 = var5.offsetDown();
/*      */           
/*      */           continue;
/*      */         } 
/* 1263 */         var7 = var5.getY() + 1;
/*      */       } 
/*      */ 
/*      */       
/* 1267 */       this.precipitationHeightMap[var4] = var7;
/*      */     } 
/*      */     
/* 1270 */     return new BlockPos(p_177440_1_.getX(), this.precipitationHeightMap[var4], p_177440_1_.getZ());
/*      */   }
/*      */ 
/*      */   
/*      */   public void func_150804_b(boolean p_150804_1_) {
/* 1275 */     if (this.isGapLightingUpdated && !this.worldObj.provider.getHasNoSky() && !p_150804_1_)
/*      */     {
/* 1277 */       recheckGaps(this.worldObj.isRemote);
/*      */     }
/*      */     
/* 1280 */     this.field_150815_m = true;
/*      */     
/* 1282 */     if (!this.isLightPopulated && this.isTerrainPopulated)
/*      */     {
/* 1284 */       func_150809_p();
/*      */     }
/*      */     
/* 1287 */     while (!this.field_177447_w.isEmpty()) {
/*      */       
/* 1289 */       BlockPos var2 = this.field_177447_w.poll();
/*      */       
/* 1291 */       if (func_177424_a(var2, EnumCreateEntityType.CHECK) == null && getBlock(var2).hasTileEntity()) {
/*      */         
/* 1293 */         TileEntity var3 = createNewTileEntity(var2);
/* 1294 */         this.worldObj.setTileEntity(var2, var3);
/* 1295 */         this.worldObj.markBlockRangeForRenderUpdate(var2, var2);
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isPopulated() {
/* 1302 */     return (this.field_150815_m && this.isTerrainPopulated && this.isLightPopulated);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ChunkCoordIntPair getChunkCoordIntPair() {
/* 1310 */     return new ChunkCoordIntPair(this.xPosition, this.zPosition);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean getAreLevelsEmpty(int p_76606_1_, int p_76606_2_) {
/* 1319 */     if (p_76606_1_ < 0)
/*      */     {
/* 1321 */       p_76606_1_ = 0;
/*      */     }
/*      */     
/* 1324 */     if (p_76606_2_ >= 256)
/*      */     {
/* 1326 */       p_76606_2_ = 255;
/*      */     }
/*      */     
/* 1329 */     for (int var3 = p_76606_1_; var3 <= p_76606_2_; var3 += 16) {
/*      */       
/* 1331 */       ExtendedBlockStorage var4 = this.storageArrays[var3 >> 4];
/*      */       
/* 1333 */       if (var4 != null && !var4.isEmpty())
/*      */       {
/* 1335 */         return false;
/*      */       }
/*      */     } 
/*      */     
/* 1339 */     return true;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setStorageArrays(ExtendedBlockStorage[] newStorageArrays) {
/* 1344 */     if (this.storageArrays.length != newStorageArrays.length) {
/*      */       
/* 1346 */       logger.warn("Could not set level chunk sections, array length is " + newStorageArrays.length + " instead of " + this.storageArrays.length);
/*      */     }
/*      */     else {
/*      */       
/* 1350 */       for (int var2 = 0; var2 < this.storageArrays.length; var2++)
/*      */       {
/* 1352 */         this.storageArrays[var2] = newStorageArrays[var2];
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public void func_177439_a(byte[] p_177439_1_, int p_177439_2_, boolean p_177439_3_) {
/* 1359 */     int var4 = 0;
/* 1360 */     boolean var5 = !this.worldObj.provider.getHasNoSky();
/*      */     
/*      */     int var6;
/* 1363 */     for (var6 = 0; var6 < this.storageArrays.length; var6++) {
/*      */       
/* 1365 */       if ((p_177439_2_ & 1 << var6) != 0) {
/*      */         
/* 1367 */         if (this.storageArrays[var6] == null)
/*      */         {
/* 1369 */           this.storageArrays[var6] = new ExtendedBlockStorage(var6 << 4, var5);
/*      */         }
/*      */         
/* 1372 */         char[] var7 = this.storageArrays[var6].getData();
/*      */         
/* 1374 */         for (int var8 = 0; var8 < var7.length; var8++)
/*      */         {
/* 1376 */           var7[var8] = (char)((p_177439_1_[var4 + 1] & 0xFF) << 8 | p_177439_1_[var4] & 0xFF);
/* 1377 */           var4 += 2;
/*      */         }
/*      */       
/* 1380 */       } else if (p_177439_3_ && this.storageArrays[var6] != null) {
/*      */         
/* 1382 */         this.storageArrays[var6] = null;
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 1388 */     for (var6 = 0; var6 < this.storageArrays.length; var6++) {
/*      */       
/* 1390 */       if ((p_177439_2_ & 1 << var6) != 0 && this.storageArrays[var6] != null) {
/*      */         
/* 1392 */         NibbleArray var10 = this.storageArrays[var6].getBlocklightArray();
/* 1393 */         System.arraycopy(p_177439_1_, var4, var10.getData(), 0, (var10.getData()).length);
/* 1394 */         var4 += (var10.getData()).length;
/*      */       } 
/*      */     } 
/*      */     
/* 1398 */     if (var5)
/*      */     {
/* 1400 */       for (var6 = 0; var6 < this.storageArrays.length; var6++) {
/*      */         
/* 1402 */         if ((p_177439_2_ & 1 << var6) != 0 && this.storageArrays[var6] != null) {
/*      */           
/* 1404 */           NibbleArray var10 = this.storageArrays[var6].getSkylightArray();
/* 1405 */           System.arraycopy(p_177439_1_, var4, var10.getData(), 0, (var10.getData()).length);
/* 1406 */           var4 += (var10.getData()).length;
/*      */         } 
/*      */       } 
/*      */     }
/*      */     
/* 1411 */     if (p_177439_3_) {
/*      */       
/* 1413 */       System.arraycopy(p_177439_1_, var4, this.blockBiomeArray, 0, this.blockBiomeArray.length);
/* 1414 */       int i = var4 + this.blockBiomeArray.length;
/*      */     } 
/*      */     
/* 1417 */     for (var6 = 0; var6 < this.storageArrays.length; var6++) {
/*      */       
/* 1419 */       if (this.storageArrays[var6] != null && (p_177439_2_ & 1 << var6) != 0)
/*      */       {
/* 1421 */         this.storageArrays[var6].removeInvalidBlocks();
/*      */       }
/*      */     } 
/*      */     
/* 1425 */     this.isLightPopulated = true;
/* 1426 */     this.isTerrainPopulated = true;
/* 1427 */     generateHeightMap();
/* 1428 */     Iterator<TileEntity> var9 = this.chunkTileEntityMap.values().iterator();
/*      */     
/* 1430 */     while (var9.hasNext()) {
/*      */       
/* 1432 */       TileEntity var11 = var9.next();
/* 1433 */       var11.updateContainingBlockInfo();
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public BiomeGenBase getBiome(BlockPos pos, WorldChunkManager chunkManager) {
/* 1439 */     int var3 = pos.getX() & 0xF;
/* 1440 */     int var4 = pos.getZ() & 0xF;
/* 1441 */     int var5 = this.blockBiomeArray[var4 << 4 | var3] & 0xFF;
/*      */ 
/*      */     
/* 1444 */     if (var5 == 255) {
/*      */       
/* 1446 */       BiomeGenBase biomeGenBase = chunkManager.func_180300_a(pos, BiomeGenBase.plains);
/* 1447 */       var5 = biomeGenBase.biomeID;
/* 1448 */       this.blockBiomeArray[var4 << 4 | var3] = (byte)(var5 & 0xFF);
/*      */     } 
/*      */     
/* 1451 */     BiomeGenBase var6 = BiomeGenBase.getBiome(var5);
/* 1452 */     return (var6 == null) ? BiomeGenBase.plains : var6;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public byte[] getBiomeArray() {
/* 1460 */     return this.blockBiomeArray;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setBiomeArray(byte[] biomeArray) {
/* 1469 */     if (this.blockBiomeArray.length != biomeArray.length) {
/*      */       
/* 1471 */       logger.warn("Could not set level chunk biomes, array length is " + biomeArray.length + " instead of " + this.blockBiomeArray.length);
/*      */     }
/*      */     else {
/*      */       
/* 1475 */       for (int var2 = 0; var2 < this.blockBiomeArray.length; var2++)
/*      */       {
/* 1477 */         this.blockBiomeArray[var2] = biomeArray[var2];
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void resetRelightChecks() {
/* 1487 */     this.queuedLightChecks = 0;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void enqueueRelightChecks() {
/* 1497 */     BlockPos var1 = new BlockPos(this.xPosition << 4, 0, this.zPosition << 4);
/*      */     
/* 1499 */     for (int var2 = 0; var2 < 8; var2++) {
/*      */       
/* 1501 */       if (this.queuedLightChecks >= 4096) {
/*      */         return;
/*      */       }
/*      */ 
/*      */       
/* 1506 */       int var3 = this.queuedLightChecks % 16;
/* 1507 */       int var4 = this.queuedLightChecks / 16 % 16;
/* 1508 */       int var5 = this.queuedLightChecks / 256;
/* 1509 */       this.queuedLightChecks++;
/*      */       
/* 1511 */       for (int var6 = 0; var6 < 16; var6++) {
/*      */         
/* 1513 */         BlockPos var7 = var1.add(var4, (var3 << 4) + var6, var5);
/* 1514 */         boolean var8 = !(var6 != 0 && var6 != 15 && var4 != 0 && var4 != 15 && var5 != 0 && var5 != 15);
/*      */         
/* 1516 */         if ((this.storageArrays[var3] == null && var8) || (this.storageArrays[var3] != null && this.storageArrays[var3].getBlockByExtId(var4, var6, var5).getMaterial() == Material.air)) {
/*      */           
/* 1518 */           EnumFacing[] var9 = EnumFacing.values();
/* 1519 */           int var10 = var9.length;
/*      */           
/* 1521 */           for (int var11 = 0; var11 < var10; var11++) {
/*      */             
/* 1523 */             EnumFacing var12 = var9[var11];
/* 1524 */             BlockPos var13 = var7.offset(var12);
/*      */             
/* 1526 */             if (this.worldObj.getBlockState(var13).getBlock().getLightValue() > 0)
/*      */             {
/* 1528 */               this.worldObj.checkLight(var13);
/*      */             }
/*      */           } 
/*      */           
/* 1532 */           this.worldObj.checkLight(var7);
/*      */         } 
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public void func_150809_p() {
/* 1540 */     this.isTerrainPopulated = true;
/* 1541 */     this.isLightPopulated = true;
/* 1542 */     BlockPos var1 = new BlockPos(this.xPosition << 4, 0, this.zPosition << 4);
/*      */     
/* 1544 */     if (!this.worldObj.provider.getHasNoSky())
/*      */     {
/* 1546 */       if (this.worldObj.isAreaLoaded(var1.add(-1, 0, -1), var1.add(16, 63, 16))) {
/*      */         int var2;
/*      */ 
/*      */         
/* 1550 */         label31: for (var2 = 0; var2 < 16; var2++) {
/*      */           
/* 1552 */           for (int var3 = 0; var3 < 16; var3++) {
/*      */             
/* 1554 */             if (!func_150811_f(var2, var3)) {
/*      */               
/* 1556 */               this.isLightPopulated = false;
/*      */               
/*      */               break label31;
/*      */             } 
/*      */           } 
/*      */         } 
/* 1562 */         if (this.isLightPopulated)
/*      */         {
/* 1564 */           Iterator<EnumFacing> var5 = EnumFacing.Plane.HORIZONTAL.iterator();
/*      */           
/* 1566 */           while (var5.hasNext()) {
/*      */             
/* 1568 */             EnumFacing var6 = var5.next();
/* 1569 */             int var4 = (var6.getAxisDirection() == EnumFacing.AxisDirection.POSITIVE) ? 16 : 1;
/* 1570 */             this.worldObj.getChunkFromBlockCoords(var1.offset(var6, var4)).func_180700_a(var6.getOpposite());
/*      */           } 
/*      */           
/* 1573 */           func_177441_y();
/*      */         }
/*      */       
/*      */       } else {
/*      */         
/* 1578 */         this.isLightPopulated = false;
/*      */       } 
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   private void func_177441_y() {
/* 1585 */     for (int var1 = 0; var1 < this.updateSkylightColumns.length; var1++)
/*      */     {
/* 1587 */       this.updateSkylightColumns[var1] = true;
/*      */     }
/*      */     
/* 1590 */     recheckGaps(false);
/*      */   }
/*      */ 
/*      */   
/*      */   private void func_180700_a(EnumFacing p_180700_1_) {
/* 1595 */     if (this.isTerrainPopulated)
/*      */     {
/*      */ 
/*      */       
/* 1599 */       if (p_180700_1_ == EnumFacing.EAST) {
/*      */         
/* 1601 */         for (int var2 = 0; var2 < 16; var2++)
/*      */         {
/* 1603 */           func_150811_f(15, var2);
/*      */         }
/*      */       }
/* 1606 */       else if (p_180700_1_ == EnumFacing.WEST) {
/*      */         
/* 1608 */         for (int var2 = 0; var2 < 16; var2++)
/*      */         {
/* 1610 */           func_150811_f(0, var2);
/*      */         }
/*      */       }
/* 1613 */       else if (p_180700_1_ == EnumFacing.SOUTH) {
/*      */         
/* 1615 */         for (int var2 = 0; var2 < 16; var2++)
/*      */         {
/* 1617 */           func_150811_f(var2, 15);
/*      */         }
/*      */       }
/* 1620 */       else if (p_180700_1_ == EnumFacing.NORTH) {
/*      */         
/* 1622 */         for (int var2 = 0; var2 < 16; var2++)
/*      */         {
/* 1624 */           func_150811_f(var2, 0);
/*      */         }
/*      */       } 
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   private boolean func_150811_f(int p_150811_1_, int p_150811_2_) {
/* 1632 */     BlockPos var3 = new BlockPos(this.xPosition << 4, 0, this.zPosition << 4);
/* 1633 */     int var4 = getTopFilledSegment();
/* 1634 */     boolean var5 = false;
/* 1635 */     boolean var6 = false;
/*      */     
/*      */     int var7;
/*      */     
/* 1639 */     for (var7 = var4 + 16 - 1; var7 > 63 || (var7 > 0 && !var6); var7--) {
/*      */       
/* 1641 */       BlockPos var8 = var3.add(p_150811_1_, var7, p_150811_2_);
/* 1642 */       int var9 = getBlockLightOpacity(var8);
/*      */       
/* 1644 */       if (var9 == 255 && var7 < 63)
/*      */       {
/* 1646 */         var6 = true;
/*      */       }
/*      */       
/* 1649 */       if (!var5 && var9 > 0) {
/*      */         
/* 1651 */         var5 = true;
/*      */       }
/* 1653 */       else if (var5 && var9 == 0 && !this.worldObj.checkLight(var8)) {
/*      */         
/* 1655 */         return false;
/*      */       } 
/*      */     } 
/*      */     
/* 1659 */     for (; var7 > 0; var7--) {
/*      */       
/* 1661 */       BlockPos var8 = var3.add(p_150811_1_, var7, p_150811_2_);
/*      */       
/* 1663 */       if (getBlock(var8).getLightValue() > 0)
/*      */       {
/* 1665 */         this.worldObj.checkLight(var8);
/*      */       }
/*      */     } 
/*      */     
/* 1669 */     return true;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isLoaded() {
/* 1674 */     return this.isChunkLoaded;
/*      */   }
/*      */ 
/*      */   
/*      */   public void func_177417_c(boolean p_177417_1_) {
/* 1679 */     this.isChunkLoaded = p_177417_1_;
/*      */   }
/*      */ 
/*      */   
/*      */   public World getWorld() {
/* 1684 */     return this.worldObj;
/*      */   }
/*      */ 
/*      */   
/*      */   public int[] getHeightMap() {
/* 1689 */     return this.heightMap;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setHeightMap(int[] newHeightMap) {
/* 1694 */     if (this.heightMap.length != newHeightMap.length) {
/*      */       
/* 1696 */       logger.warn("Could not set level chunk heightmap, array length is " + newHeightMap.length + " instead of " + this.heightMap.length);
/*      */     }
/*      */     else {
/*      */       
/* 1700 */       for (int var2 = 0; var2 < this.heightMap.length; var2++)
/*      */       {
/* 1702 */         this.heightMap[var2] = newHeightMap[var2];
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public Map getTileEntityMap() {
/* 1709 */     return this.chunkTileEntityMap;
/*      */   }
/*      */ 
/*      */   
/*      */   public ClassInheratanceMultiMap[] getEntityLists() {
/* 1714 */     return this.entityLists;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isTerrainPopulated() {
/* 1719 */     return this.isTerrainPopulated;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setTerrainPopulated(boolean terrainPopulated) {
/* 1724 */     this.isTerrainPopulated = terrainPopulated;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isLightPopulated() {
/* 1729 */     return this.isLightPopulated;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setLightPopulated(boolean lightPopulated) {
/* 1734 */     this.isLightPopulated = lightPopulated;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setModified(boolean modified) {
/* 1739 */     this.isModified = modified;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setHasEntities(boolean hasEntitiesIn) {
/* 1744 */     this.hasEntities = hasEntitiesIn;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setLastSaveTime(long saveTime) {
/* 1749 */     this.lastSaveTime = saveTime;
/*      */   }
/*      */ 
/*      */   
/*      */   public int getLowestHeight() {
/* 1754 */     return this.heightMapMinimum;
/*      */   }
/*      */ 
/*      */   
/*      */   public long getInhabitedTime() {
/* 1759 */     return this.inhabitedTime;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setInhabitedTime(long newInhabitedTime) {
/* 1764 */     this.inhabitedTime = newInhabitedTime;
/*      */   }
/*      */   
/*      */   public enum EnumCreateEntityType
/*      */   {
/* 1769 */     IMMEDIATE("IMMEDIATE", 0),
/* 1770 */     QUEUED("QUEUED", 1),
/* 1771 */     CHECK("CHECK", 2);
/*      */     
/* 1773 */     private static final EnumCreateEntityType[] $VALUES = new EnumCreateEntityType[] { IMMEDIATE, QUEUED, CHECK };
/*      */     private static final String __OBFID = "CL_00002009";
/*      */     
/*      */     static {
/*      */     
/*      */     }
/*      */   }
/*      */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\world\chunk\Chunk.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */