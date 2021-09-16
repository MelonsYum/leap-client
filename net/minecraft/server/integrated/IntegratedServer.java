/*     */ package net.minecraft.server.integrated;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import com.google.common.util.concurrent.Futures;
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.Queue;
/*     */ import java.util.concurrent.Callable;
/*     */ import java.util.concurrent.Future;
/*     */ import java.util.concurrent.FutureTask;
/*     */ import net.minecraft.client.ClientBrandRetriever;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.multiplayer.ThreadLanServerPing;
/*     */ import net.minecraft.command.ServerCommandManager;
/*     */ import net.minecraft.crash.CrashReport;
/*     */ import net.minecraft.entity.player.EntityPlayerMP;
/*     */ import net.minecraft.profiler.PlayerUsageSnooper;
/*     */ import net.minecraft.server.MinecraftServer;
/*     */ import net.minecraft.util.CryptManager;
/*     */ import net.minecraft.util.HttpUtil;
/*     */ import net.minecraft.world.EnumDifficulty;
/*     */ import net.minecraft.world.IWorldAccess;
/*     */ import net.minecraft.world.WorldManager;
/*     */ import net.minecraft.world.WorldServer;
/*     */ import net.minecraft.world.WorldServerMulti;
/*     */ import net.minecraft.world.WorldSettings;
/*     */ import net.minecraft.world.WorldType;
/*     */ import net.minecraft.world.demo.DemoWorldServer;
/*     */ import net.minecraft.world.storage.ISaveHandler;
/*     */ import net.minecraft.world.storage.WorldInfo;
/*     */ import optifine.Reflector;
/*     */ import optifine.WorldServerOF;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ 
/*     */ public class IntegratedServer
/*     */   extends MinecraftServer {
/*  40 */   private static final Logger logger = LogManager.getLogger();
/*     */   
/*     */   private final Minecraft mc;
/*     */   
/*     */   private final WorldSettings theWorldSettings;
/*     */   
/*     */   private boolean isGamePaused;
/*     */   private boolean isPublic;
/*     */   private ThreadLanServerPing lanServerPing;
/*     */   
/*     */   public IntegratedServer(Minecraft mcIn) {
/*  51 */     super(mcIn.getProxy(), new File(mcIn.mcDataDir, USER_CACHE_FILE.getName()));
/*  52 */     this.mc = mcIn;
/*  53 */     this.theWorldSettings = null;
/*     */   }
/*     */ 
/*     */   
/*     */   public IntegratedServer(Minecraft mcIn, String folderName, String worldName, WorldSettings settings) {
/*  58 */     super(new File(mcIn.mcDataDir, "saves"), mcIn.getProxy(), new File(mcIn.mcDataDir, USER_CACHE_FILE.getName()));
/*  59 */     setServerOwner(mcIn.getSession().getUsername());
/*  60 */     setFolderName(folderName);
/*  61 */     setWorldName(worldName);
/*  62 */     setDemo(mcIn.isDemo());
/*  63 */     canCreateBonusChest(settings.isBonusChestEnabled());
/*  64 */     setBuildLimit(256);
/*  65 */     setConfigManager(new IntegratedPlayerList(this));
/*  66 */     this.mc = mcIn;
/*  67 */     this.theWorldSettings = isDemo() ? DemoWorldServer.demoWorldSettings : settings;
/*     */   }
/*     */ 
/*     */   
/*     */   protected ServerCommandManager createNewCommandManager() {
/*  72 */     return new IntegratedServerCommandManager();
/*     */   }
/*     */ 
/*     */   
/*     */   protected void loadAllWorlds(String p_71247_1_, String p_71247_2_, long seed, WorldType type, String p_71247_6_) {
/*  77 */     convertMapIfNeeded(p_71247_1_);
/*  78 */     ISaveHandler var7 = getActiveAnvilConverter().getSaveLoader(p_71247_1_, true);
/*  79 */     setResourcePackFromWorld(getFolderName(), var7);
/*  80 */     WorldInfo var8 = var7.loadWorldInfo();
/*     */     
/*  82 */     if (Reflector.DimensionManager.exists()) {
/*     */       
/*  84 */       WorldServer var9 = isDemo() ? (WorldServer)(new DemoWorldServer(this, var7, var8, 0, this.theProfiler)).init() : (WorldServer)(new WorldServerOF(this, var7, var8, 0, this.theProfiler)).init();
/*  85 */       var9.initialize(this.theWorldSettings);
/*  86 */       Integer[] var10 = (Integer[])Reflector.call(Reflector.DimensionManager_getStaticDimensionIDs, new Object[0]);
/*  87 */       Integer[] arr$ = var10;
/*  88 */       int len$ = var10.length;
/*     */       
/*  90 */       for (int i$ = 0; i$ < len$; i$++) {
/*     */         
/*  92 */         int dim = arr$[i$].intValue();
/*  93 */         WorldServer world = (dim == 0) ? var9 : (WorldServer)(new WorldServerMulti(this, var7, dim, var9, this.theProfiler)).init();
/*  94 */         world.addWorldAccess((IWorldAccess)new WorldManager(this, world));
/*     */         
/*  96 */         if (!isSinglePlayer())
/*     */         {
/*  98 */           world.getWorldInfo().setGameType(getGameType());
/*     */         }
/*     */         
/* 101 */         if (Reflector.EventBus.exists())
/*     */         {
/* 103 */           Reflector.postForgeBusEvent(Reflector.WorldEvent_Load_Constructor, new Object[] { world });
/*     */         }
/*     */       } 
/*     */       
/* 107 */       getConfigurationManager().setPlayerManager(new WorldServer[] { var9 });
/*     */       
/* 109 */       if (var9.getWorldInfo().getDifficulty() == null)
/*     */       {
/* 111 */         setDifficultyForAllWorlds(this.mc.gameSettings.difficulty);
/*     */       }
/*     */     }
/*     */     else {
/*     */       
/* 116 */       this.worldServers = new WorldServer[3];
/* 117 */       this.timeOfLastDimensionTick = new long[this.worldServers.length][100];
/* 118 */       setResourcePackFromWorld(getFolderName(), var7);
/*     */       
/* 120 */       if (var8 == null) {
/*     */         
/* 122 */         var8 = new WorldInfo(this.theWorldSettings, p_71247_2_);
/*     */       }
/*     */       else {
/*     */         
/* 126 */         var8.setWorldName(p_71247_2_);
/*     */       } 
/*     */       
/* 129 */       for (int var16 = 0; var16 < this.worldServers.length; var16++) {
/*     */         
/* 131 */         byte var17 = 0;
/*     */         
/* 133 */         if (var16 == 1)
/*     */         {
/* 135 */           var17 = -1;
/*     */         }
/*     */         
/* 138 */         if (var16 == 2)
/*     */         {
/* 140 */           var17 = 1;
/*     */         }
/*     */         
/* 143 */         if (var16 == 0) {
/*     */           
/* 145 */           if (isDemo()) {
/*     */             
/* 147 */             this.worldServers[var16] = (WorldServer)(new DemoWorldServer(this, var7, var8, var17, this.theProfiler)).init();
/*     */           }
/*     */           else {
/*     */             
/* 151 */             this.worldServers[var16] = (WorldServer)(new WorldServerOF(this, var7, var8, var17, this.theProfiler)).init();
/*     */           } 
/*     */           
/* 154 */           this.worldServers[var16].initialize(this.theWorldSettings);
/*     */         }
/*     */         else {
/*     */           
/* 158 */           this.worldServers[var16] = (WorldServer)(new WorldServerMulti(this, var7, var17, this.worldServers[0], this.theProfiler)).init();
/*     */         } 
/*     */         
/* 161 */         this.worldServers[var16].addWorldAccess((IWorldAccess)new WorldManager(this, this.worldServers[var16]));
/*     */       } 
/*     */       
/* 164 */       getConfigurationManager().setPlayerManager(this.worldServers);
/*     */       
/* 166 */       if (this.worldServers[0].getWorldInfo().getDifficulty() == null)
/*     */       {
/* 168 */         setDifficultyForAllWorlds(this.mc.gameSettings.difficulty);
/*     */       }
/*     */     } 
/*     */     
/* 172 */     initialWorldChunkLoad();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean startServer() throws IOException {
/* 180 */     logger.info("Starting integrated minecraft server version 1.8");
/* 181 */     setOnlineMode(true);
/* 182 */     setCanSpawnAnimals(true);
/* 183 */     setCanSpawnNPCs(true);
/* 184 */     setAllowPvp(true);
/* 185 */     setAllowFlight(true);
/* 186 */     logger.info("Generating keypair");
/* 187 */     setKeyPair(CryptManager.generateKeyPair());
/*     */ 
/*     */     
/* 190 */     if (Reflector.FMLCommonHandler_handleServerAboutToStart.exists()) {
/*     */       
/* 192 */       Object inst = Reflector.call(Reflector.FMLCommonHandler_instance, new Object[0]);
/*     */       
/* 194 */       if (!Reflector.callBoolean(inst, Reflector.FMLCommonHandler_handleServerAboutToStart, new Object[] { this }))
/*     */       {
/* 196 */         return false;
/*     */       }
/*     */     } 
/*     */     
/* 200 */     loadAllWorlds(getFolderName(), getWorldName(), this.theWorldSettings.getSeed(), this.theWorldSettings.getTerrainType(), this.theWorldSettings.getWorldName());
/* 201 */     setMOTD(String.valueOf(getServerOwner()) + " - " + this.worldServers[0].getWorldInfo().getWorldName());
/*     */     
/* 203 */     if (Reflector.FMLCommonHandler_handleServerStarting.exists()) {
/*     */       
/* 205 */       Object inst = Reflector.call(Reflector.FMLCommonHandler_instance, new Object[0]);
/*     */       
/* 207 */       if (Reflector.FMLCommonHandler_handleServerStarting.getReturnType() == boolean.class)
/*     */       {
/* 209 */         return Reflector.callBoolean(inst, Reflector.FMLCommonHandler_handleServerStarting, new Object[] { this });
/*     */       }
/*     */       
/* 212 */       Reflector.callVoid(inst, Reflector.FMLCommonHandler_handleServerStarting, new Object[] { this });
/*     */     } 
/*     */     
/* 215 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void tick() {
/* 223 */     boolean var1 = this.isGamePaused;
/* 224 */     this.isGamePaused = (Minecraft.getMinecraft().getNetHandler() != null && Minecraft.getMinecraft().isGamePaused());
/*     */     
/* 226 */     if (!var1 && this.isGamePaused) {
/*     */       
/* 228 */       logger.info("Saving and pausing game...");
/* 229 */       getConfigurationManager().saveAllPlayerData();
/* 230 */       saveAllWorlds(false);
/*     */     } 
/*     */     
/* 233 */     if (this.isGamePaused) {
/*     */       
/* 235 */       Queue var10 = this.futureTaskQueue;
/* 236 */       Queue var3 = this.futureTaskQueue;
/*     */       
/* 238 */       synchronized (this.futureTaskQueue) {
/*     */         
/* 240 */         while (!this.futureTaskQueue.isEmpty()) {
/*     */           
/*     */           try
/*     */           {
/* 244 */             if (Reflector.FMLCommonHandler_callFuture.exists()) {
/*     */               
/* 246 */               Reflector.callVoid(Reflector.FMLCommonHandler_callFuture, new Object[] { this.futureTaskQueue.poll() });
/*     */               
/*     */               continue;
/*     */             } 
/* 250 */             ((FutureTask)this.futureTaskQueue.poll()).run();
/*     */           
/*     */           }
/* 253 */           catch (Throwable var8)
/*     */           {
/* 255 */             logger.fatal(var8);
/*     */           }
/*     */         
/*     */         } 
/*     */       } 
/*     */     } else {
/*     */       
/* 262 */       super.tick();
/*     */       
/* 264 */       if (this.mc.gameSettings.renderDistanceChunks != getConfigurationManager().getViewDistance()) {
/*     */         
/* 266 */         logger.info("Changing view distance to {}, from {}", new Object[] { Integer.valueOf(this.mc.gameSettings.renderDistanceChunks), Integer.valueOf(getConfigurationManager().getViewDistance()) });
/* 267 */         getConfigurationManager().setViewDistance(this.mc.gameSettings.renderDistanceChunks);
/*     */       } 
/*     */       
/* 270 */       if (this.mc.theWorld != null) {
/*     */         
/* 272 */         WorldInfo var101 = this.worldServers[0].getWorldInfo();
/* 273 */         WorldInfo var11 = this.mc.theWorld.getWorldInfo();
/*     */         
/* 275 */         if (!var101.isDifficultyLocked() && var11.getDifficulty() != var101.getDifficulty()) {
/*     */           
/* 277 */           logger.info("Changing difficulty to {}, from {}", new Object[] { var11.getDifficulty(), var101.getDifficulty() });
/* 278 */           setDifficultyForAllWorlds(var11.getDifficulty());
/*     */         }
/* 280 */         else if (var11.isDifficultyLocked() && !var101.isDifficultyLocked()) {
/*     */           
/* 282 */           logger.info("Locking difficulty to {}", new Object[] { var11.getDifficulty() });
/* 283 */           WorldServer[] var4 = this.worldServers;
/* 284 */           int var5 = var4.length;
/*     */           
/* 286 */           for (int var6 = 0; var6 < var5; var6++) {
/*     */             
/* 288 */             WorldServer var7 = var4[var6];
/*     */             
/* 290 */             if (var7 != null)
/*     */             {
/* 292 */               var7.getWorldInfo().setDifficultyLocked(true);
/*     */             }
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canStructuresSpawn() {
/* 302 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public WorldSettings.GameType getGameType() {
/* 307 */     return this.theWorldSettings.getGameType();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EnumDifficulty getDifficulty() {
/* 315 */     return (this.mc.theWorld == null) ? this.mc.gameSettings.difficulty : this.mc.theWorld.getWorldInfo().getDifficulty();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isHardcore() {
/* 323 */     return this.theWorldSettings.getHardcoreEnabled();
/*     */   }
/*     */ 
/*     */   
/*     */   public File getDataDirectory() {
/* 328 */     return this.mc.mcDataDir;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isDedicatedServer() {
/* 333 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void finalTick(CrashReport report) {
/* 341 */     this.mc.crashed(report);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CrashReport addServerInfoToCrashReport(CrashReport report) {
/* 349 */     report = super.addServerInfoToCrashReport(report);
/* 350 */     report.getCategory().addCrashSectionCallable("Type", new Callable()
/*     */         {
/*     */           public String call()
/*     */           {
/* 354 */             return "Integrated Server (map_client.txt)";
/*     */           }
/*     */         });
/* 357 */     report.getCategory().addCrashSectionCallable("Is Modded", new Callable()
/*     */         {
/*     */           public String call()
/*     */           {
/* 361 */             String var1 = ClientBrandRetriever.getClientModName();
/*     */             
/* 363 */             if (!var1.equals("vanilla"))
/*     */             {
/* 365 */               return "Definitely; Client brand changed to '" + var1 + "'";
/*     */             }
/*     */ 
/*     */             
/* 369 */             var1 = IntegratedServer.this.getServerModName();
/* 370 */             return !var1.equals("vanilla") ? ("Definitely; Server brand changed to '" + var1 + "'") : ((Minecraft.class.getSigners() == null) ? "Very likely; Jar signature invalidated" : "Probably not. Jar signature remains and both client + server brands are untouched.");
/*     */           }
/*     */         });
/*     */     
/* 374 */     return report;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDifficultyForAllWorlds(EnumDifficulty difficulty) {
/* 379 */     super.setDifficultyForAllWorlds(difficulty);
/*     */     
/* 381 */     if (this.mc.theWorld != null)
/*     */     {
/* 383 */       this.mc.theWorld.getWorldInfo().setDifficulty(difficulty);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void addServerStatsToSnooper(PlayerUsageSnooper playerSnooper) {
/* 389 */     super.addServerStatsToSnooper(playerSnooper);
/* 390 */     playerSnooper.addClientStat("snooper_partner", this.mc.getPlayerUsageSnooper().getUniqueID());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isSnooperEnabled() {
/* 398 */     return Minecraft.getMinecraft().isSnooperEnabled();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String shareToLAN(WorldSettings.GameType type, boolean allowCheats) {
/*     */     try {
/* 408 */       int var6 = -1;
/*     */ 
/*     */       
/*     */       try {
/* 412 */         var6 = HttpUtil.getSuitableLanPort();
/*     */       }
/* 414 */       catch (IOException iOException) {}
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 419 */       if (var6 <= 0)
/*     */       {
/* 421 */         var6 = 25564;
/*     */       }
/*     */       
/* 424 */       getNetworkSystem().addLanEndpoint(null, var6);
/* 425 */       logger.info("Started on " + var6);
/* 426 */       this.isPublic = true;
/* 427 */       this.lanServerPing = new ThreadLanServerPing(getMOTD(), (new StringBuilder(String.valueOf(var6))).toString());
/* 428 */       this.lanServerPing.start();
/* 429 */       getConfigurationManager().func_152604_a(type);
/* 430 */       getConfigurationManager().setCommandsAllowedForAll(allowCheats);
/* 431 */       return (new StringBuilder(String.valueOf(var6))).toString();
/*     */     }
/* 433 */     catch (IOException var61) {
/*     */       
/* 435 */       return null;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void stopServer() {
/* 444 */     super.stopServer();
/*     */     
/* 446 */     if (this.lanServerPing != null) {
/*     */       
/* 448 */       this.lanServerPing.interrupt();
/* 449 */       this.lanServerPing = null;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void initiateShutdown() {
/* 458 */     Futures.getUnchecked((Future)addScheduledTask(new Runnable()
/*     */           {
/*     */             public void run()
/*     */             {
/* 462 */               ArrayList var1 = Lists.newArrayList((IntegratedServer.this.getConfigurationManager()).playerEntityList);
/* 463 */               Iterator<EntityPlayerMP> var2 = var1.iterator();
/*     */               
/* 465 */               while (var2.hasNext()) {
/*     */                 
/* 467 */                 EntityPlayerMP var3 = var2.next();
/* 468 */                 IntegratedServer.this.getConfigurationManager().playerLoggedOut(var3);
/*     */               } 
/*     */             }
/*     */           }));
/* 472 */     super.initiateShutdown();
/*     */     
/* 474 */     if (this.lanServerPing != null) {
/*     */       
/* 476 */       this.lanServerPing.interrupt();
/* 477 */       this.lanServerPing = null;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_175592_a() {
/* 483 */     func_175585_v();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean getPublic() {
/* 491 */     return this.isPublic;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setGameType(WorldSettings.GameType gameMode) {
/* 499 */     getConfigurationManager().func_152604_a(gameMode);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isCommandBlockEnabled() {
/* 507 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getOpPermissionLevel() {
/* 512 */     return 4;
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\server\integrated\IntegratedServer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */