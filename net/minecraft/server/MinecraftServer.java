/*      */ package net.minecraft.server;
/*      */ import com.google.common.collect.Lists;
/*      */ import com.google.common.collect.Queues;
/*      */ import com.google.common.util.concurrent.Futures;
/*      */ import com.google.common.util.concurrent.ListenableFuture;
/*      */ import com.google.common.util.concurrent.ListenableFutureTask;
/*      */ import com.mojang.authlib.GameProfile;
/*      */ import com.mojang.authlib.GameProfileRepository;
/*      */ import com.mojang.authlib.minecraft.MinecraftSessionService;
/*      */ import com.mojang.authlib.yggdrasil.YggdrasilAuthenticationService;
/*      */ import io.netty.buffer.ByteBuf;
/*      */ import io.netty.buffer.ByteBufOutputStream;
/*      */ import io.netty.buffer.Unpooled;
/*      */ import io.netty.handler.codec.base64.Base64;
/*      */ import java.awt.GraphicsEnvironment;
/*      */ import java.awt.image.BufferedImage;
/*      */ import java.io.File;
/*      */ import java.io.IOException;
/*      */ import java.io.OutputStream;
/*      */ import java.net.Proxy;
/*      */ import java.security.KeyPair;
/*      */ import java.text.SimpleDateFormat;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Arrays;
/*      */ import java.util.Collections;
/*      */ import java.util.Date;
/*      */ import java.util.Iterator;
/*      */ import java.util.List;
/*      */ import java.util.Queue;
/*      */ import java.util.Random;
/*      */ import java.util.UUID;
/*      */ import java.util.concurrent.Callable;
/*      */ import java.util.concurrent.Executors;
/*      */ import java.util.concurrent.FutureTask;
/*      */ import javax.imageio.ImageIO;
/*      */ import net.minecraft.command.CommandBase;
/*      */ import net.minecraft.command.CommandResultStats;
/*      */ import net.minecraft.command.ICommandManager;
/*      */ import net.minecraft.command.ICommandSender;
/*      */ import net.minecraft.command.ServerCommandManager;
/*      */ import net.minecraft.crash.CrashReport;
/*      */ import net.minecraft.entity.Entity;
/*      */ import net.minecraft.entity.player.EntityPlayer;
/*      */ import net.minecraft.entity.player.EntityPlayerMP;
/*      */ import net.minecraft.network.NetworkSystem;
/*      */ import net.minecraft.network.Packet;
/*      */ import net.minecraft.network.ServerStatusResponse;
/*      */ import net.minecraft.network.play.server.S03PacketTimeUpdate;
/*      */ import net.minecraft.profiler.IPlayerUsage;
/*      */ import net.minecraft.profiler.PlayerUsageSnooper;
/*      */ import net.minecraft.profiler.Profiler;
/*      */ import net.minecraft.server.gui.IUpdatePlayerListBox;
/*      */ import net.minecraft.server.management.PlayerProfileCache;
/*      */ import net.minecraft.server.management.ServerConfigurationManager;
/*      */ import net.minecraft.util.BlockPos;
/*      */ import net.minecraft.util.ChatComponentText;
/*      */ import net.minecraft.util.IChatComponent;
/*      */ import net.minecraft.util.IProgressUpdate;
/*      */ import net.minecraft.util.IThreadListener;
/*      */ import net.minecraft.util.MathHelper;
/*      */ import net.minecraft.util.ReportedException;
/*      */ import net.minecraft.util.Vec3;
/*      */ import net.minecraft.world.EnumDifficulty;
/*      */ import net.minecraft.world.IWorldAccess;
/*      */ import net.minecraft.world.MinecraftException;
/*      */ import net.minecraft.world.World;
/*      */ import net.minecraft.world.WorldManager;
/*      */ import net.minecraft.world.WorldServer;
/*      */ import net.minecraft.world.WorldServerMulti;
/*      */ import net.minecraft.world.WorldSettings;
/*      */ import net.minecraft.world.WorldType;
/*      */ import net.minecraft.world.chunk.storage.AnvilSaveConverter;
/*      */ import net.minecraft.world.demo.DemoWorldServer;
/*      */ import net.minecraft.world.storage.ISaveFormat;
/*      */ import net.minecraft.world.storage.ISaveHandler;
/*      */ import net.minecraft.world.storage.WorldInfo;
/*      */ import org.apache.commons.lang3.Validate;
/*      */ import org.apache.logging.log4j.LogManager;
/*      */ import org.apache.logging.log4j.Logger;
/*      */ 
/*      */ public abstract class MinecraftServer implements ICommandSender, Runnable, IThreadListener, IPlayerUsage {
/*   82 */   private static final Logger logger = LogManager.getLogger();
/*   83 */   public static final File USER_CACHE_FILE = new File("usercache.json");
/*      */ 
/*      */   
/*      */   private static MinecraftServer mcServer;
/*      */   
/*      */   private final ISaveFormat anvilConverterForAnvilFile;
/*      */   
/*   90 */   private final PlayerUsageSnooper usageSnooper = new PlayerUsageSnooper("server", this, getCurrentTimeMillis());
/*      */   
/*      */   private final File anvilFile;
/*      */   
/*   94 */   private final List playersOnline = Lists.newArrayList();
/*      */   private final ICommandManager commandManager;
/*   96 */   public final Profiler theProfiler = new Profiler();
/*      */   private final NetworkSystem networkSystem;
/*   98 */   private final ServerStatusResponse statusResponse = new ServerStatusResponse();
/*   99 */   private final Random random = new Random();
/*      */ 
/*      */   
/*  102 */   private int serverPort = -1;
/*      */ 
/*      */   
/*      */   public WorldServer[] worldServers;
/*      */ 
/*      */   
/*      */   private ServerConfigurationManager serverConfigManager;
/*      */ 
/*      */   
/*      */   private boolean serverRunning = true;
/*      */ 
/*      */   
/*      */   private boolean serverStopped;
/*      */ 
/*      */   
/*      */   private int tickCounter;
/*      */ 
/*      */   
/*      */   protected final Proxy serverProxy;
/*      */ 
/*      */   
/*      */   public String currentTask;
/*      */ 
/*      */   
/*      */   public int percentDone;
/*      */ 
/*      */   
/*      */   private boolean onlineMode;
/*      */ 
/*      */   
/*      */   private boolean canSpawnAnimals;
/*      */ 
/*      */   
/*      */   private boolean canSpawnNPCs;
/*      */ 
/*      */   
/*      */   private boolean pvpEnabled;
/*      */ 
/*      */   
/*      */   private boolean allowFlight;
/*      */ 
/*      */   
/*      */   private String motd;
/*      */   
/*      */   private int buildLimit;
/*      */   
/*  148 */   private int maxPlayerIdleMinutes = 0;
/*  149 */   public final long[] tickTimeArray = new long[100];
/*      */ 
/*      */   
/*      */   public long[][] timeOfLastDimensionTick;
/*      */   
/*      */   private KeyPair serverKeyPair;
/*      */   
/*      */   private String serverOwner;
/*      */   
/*      */   private String folderName;
/*      */   
/*      */   private String worldName;
/*      */   
/*      */   private boolean isDemo;
/*      */   
/*      */   private boolean enableBonusChest;
/*      */   
/*      */   private boolean worldIsBeingDeleted;
/*      */   
/*  168 */   private String resourcePackUrl = "";
/*  169 */   private String resourcePackHash = "";
/*      */   
/*      */   private boolean serverIsRunning;
/*      */   
/*      */   private long timeOfLastWarning;
/*      */   
/*      */   private String userMessage;
/*      */   
/*      */   private boolean startProfiling;
/*      */   private boolean isGamemodeForced;
/*      */   private final YggdrasilAuthenticationService authService;
/*      */   private final MinecraftSessionService sessionService;
/*  181 */   private long nanoTimeSinceStatusRefresh = 0L;
/*      */   private final GameProfileRepository profileRepo;
/*      */   private final PlayerProfileCache profileCache;
/*  184 */   protected final Queue futureTaskQueue = Queues.newArrayDeque();
/*      */   private Thread serverThread;
/*  186 */   private long currentTime = getCurrentTimeMillis();
/*      */   
/*      */   private static final String __OBFID = "CL_00001462";
/*      */   
/*      */   public MinecraftServer(Proxy p_i46053_1_, File p_i46053_2_) {
/*  191 */     this.serverProxy = p_i46053_1_;
/*  192 */     mcServer = this;
/*  193 */     this.anvilFile = null;
/*  194 */     this.networkSystem = null;
/*  195 */     this.profileCache = new PlayerProfileCache(this, p_i46053_2_);
/*  196 */     this.commandManager = null;
/*  197 */     this.anvilConverterForAnvilFile = null;
/*  198 */     this.authService = new YggdrasilAuthenticationService(p_i46053_1_, UUID.randomUUID().toString());
/*  199 */     this.sessionService = this.authService.createMinecraftSessionService();
/*  200 */     this.profileRepo = this.authService.createProfileRepository();
/*      */   }
/*      */ 
/*      */   
/*      */   public MinecraftServer(File workDir, Proxy proxy, File profileCacheDir) {
/*  205 */     this.serverProxy = proxy;
/*  206 */     mcServer = this;
/*  207 */     this.anvilFile = workDir;
/*  208 */     this.networkSystem = new NetworkSystem(this);
/*  209 */     this.profileCache = new PlayerProfileCache(this, profileCacheDir);
/*  210 */     this.commandManager = (ICommandManager)createNewCommandManager();
/*  211 */     this.anvilConverterForAnvilFile = (ISaveFormat)new AnvilSaveConverter(workDir);
/*  212 */     this.authService = new YggdrasilAuthenticationService(proxy, UUID.randomUUID().toString());
/*  213 */     this.sessionService = this.authService.createMinecraftSessionService();
/*  214 */     this.profileRepo = this.authService.createProfileRepository();
/*      */   }
/*      */ 
/*      */   
/*      */   protected ServerCommandManager createNewCommandManager() {
/*  219 */     return new ServerCommandManager();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void convertMapIfNeeded(String worldNameIn) {
/*  229 */     if (getActiveAnvilConverter().isOldMapFormat(worldNameIn)) {
/*      */       
/*  231 */       logger.info("Converting map!");
/*  232 */       setUserMessage("menu.convertingLevel");
/*  233 */       getActiveAnvilConverter().convertMapFormat(worldNameIn, new IProgressUpdate()
/*      */           {
/*  235 */             private long startTime = System.currentTimeMillis(); private static final String __OBFID = "CL_00001417";
/*      */             public void displaySavingString(String message) {}
/*      */             
/*      */             public void resetProgressAndMessage(String p_73721_1_) {}
/*      */             
/*      */             public void setLoadingProgress(int progress) {
/*  241 */               if (System.currentTimeMillis() - this.startTime >= 1000L) {
/*      */                 
/*  243 */                 this.startTime = System.currentTimeMillis();
/*  244 */                 MinecraftServer.logger.info("Converting... " + progress + "%");
/*      */               } 
/*      */             }
/*      */ 
/*      */             
/*      */             public void setDoneWorking() {}
/*      */ 
/*      */             
/*      */             public void displayLoadingString(String message) {}
/*      */           });
/*      */     } 
/*      */   }
/*      */   
/*      */   protected synchronized void setUserMessage(String message) {
/*  258 */     this.userMessage = message;
/*      */   }
/*      */ 
/*      */   
/*      */   public synchronized String getUserMessage() {
/*  263 */     return this.userMessage;
/*      */   }
/*      */   
/*      */   protected void loadAllWorlds(String p_71247_1_, String p_71247_2_, long seed, WorldType type, String p_71247_6_) {
/*      */     WorldSettings var8;
/*  268 */     convertMapIfNeeded(p_71247_1_);
/*  269 */     setUserMessage("menu.loadingLevel");
/*  270 */     this.worldServers = new WorldServer[3];
/*  271 */     this.timeOfLastDimensionTick = new long[this.worldServers.length][100];
/*  272 */     ISaveHandler var7 = this.anvilConverterForAnvilFile.getSaveLoader(p_71247_1_, true);
/*  273 */     setResourcePackFromWorld(getFolderName(), var7);
/*  274 */     WorldInfo var9 = var7.loadWorldInfo();
/*      */ 
/*      */     
/*  277 */     if (var9 == null) {
/*      */       
/*  279 */       if (isDemo()) {
/*      */         
/*  281 */         var8 = DemoWorldServer.demoWorldSettings;
/*      */       }
/*      */       else {
/*      */         
/*  285 */         var8 = new WorldSettings(seed, getGameType(), canStructuresSpawn(), isHardcore(), type);
/*  286 */         var8.setWorldName(p_71247_6_);
/*      */         
/*  288 */         if (this.enableBonusChest)
/*      */         {
/*  290 */           var8.enableBonusChest();
/*      */         }
/*      */       } 
/*      */       
/*  294 */       var9 = new WorldInfo(var8, p_71247_2_);
/*      */     }
/*      */     else {
/*      */       
/*  298 */       var9.setWorldName(p_71247_2_);
/*  299 */       var8 = new WorldSettings(var9);
/*      */     } 
/*      */     
/*  302 */     for (int var10 = 0; var10 < this.worldServers.length; var10++) {
/*      */       
/*  304 */       byte var11 = 0;
/*      */       
/*  306 */       if (var10 == 1)
/*      */       {
/*  308 */         var11 = -1;
/*      */       }
/*      */       
/*  311 */       if (var10 == 2)
/*      */       {
/*  313 */         var11 = 1;
/*      */       }
/*      */       
/*  316 */       if (var10 == 0) {
/*      */         
/*  318 */         if (isDemo()) {
/*      */           
/*  320 */           this.worldServers[var10] = (WorldServer)(new DemoWorldServer(this, var7, var9, var11, this.theProfiler)).init();
/*      */         }
/*      */         else {
/*      */           
/*  324 */           this.worldServers[var10] = (WorldServer)(new WorldServer(this, var7, var9, var11, this.theProfiler)).init();
/*      */         } 
/*      */         
/*  327 */         this.worldServers[var10].initialize(var8);
/*      */       }
/*      */       else {
/*      */         
/*  331 */         this.worldServers[var10] = (WorldServer)(new WorldServerMulti(this, var7, var11, this.worldServers[0], this.theProfiler)).init();
/*      */       } 
/*      */       
/*  334 */       this.worldServers[var10].addWorldAccess((IWorldAccess)new WorldManager(this, this.worldServers[var10]));
/*      */       
/*  336 */       if (!isSinglePlayer())
/*      */       {
/*  338 */         this.worldServers[var10].getWorldInfo().setGameType(getGameType());
/*      */       }
/*      */     } 
/*      */     
/*  342 */     this.serverConfigManager.setPlayerManager(this.worldServers);
/*  343 */     setDifficultyForAllWorlds(getDifficulty());
/*  344 */     initialWorldChunkLoad();
/*      */   }
/*      */ 
/*      */   
/*      */   protected void initialWorldChunkLoad() {
/*  349 */     boolean var1 = true;
/*  350 */     boolean var2 = true;
/*  351 */     boolean var3 = true;
/*  352 */     boolean var4 = true;
/*  353 */     int var5 = 0;
/*  354 */     setUserMessage("menu.generatingTerrain");
/*  355 */     byte var6 = 0;
/*  356 */     logger.info("Preparing start region for level " + var6);
/*  357 */     WorldServer var7 = this.worldServers[var6];
/*  358 */     BlockPos var8 = var7.getSpawnPoint();
/*  359 */     long var9 = getCurrentTimeMillis();
/*      */     
/*  361 */     for (int var11 = -192; var11 <= 192 && isServerRunning(); var11 += 16) {
/*      */       
/*  363 */       for (int var12 = -192; var12 <= 192 && isServerRunning(); var12 += 16) {
/*      */         
/*  365 */         long var13 = getCurrentTimeMillis();
/*      */         
/*  367 */         if (var13 - var9 > 1000L) {
/*      */           
/*  369 */           outputPercentRemaining("Preparing spawn area", var5 * 100 / 625);
/*  370 */           var9 = var13;
/*      */         } 
/*      */         
/*  373 */         var5++;
/*  374 */         var7.theChunkProviderServer.loadChunk(var8.getX() + var11 >> 4, var8.getZ() + var12 >> 4);
/*      */       } 
/*      */     } 
/*      */     
/*  378 */     clearCurrentTask();
/*      */   }
/*      */ 
/*      */   
/*      */   protected void setResourcePackFromWorld(String worldNameIn, ISaveHandler saveHandlerIn) {
/*  383 */     File var3 = new File(saveHandlerIn.getWorldDirectory(), "resources.zip");
/*      */     
/*  385 */     if (var3.isFile())
/*      */     {
/*  387 */       setResourcePack("level://" + worldNameIn + "/" + var3.getName(), "");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void outputPercentRemaining(String message, int percent) {
/*  412 */     this.currentTask = message;
/*  413 */     this.percentDone = percent;
/*  414 */     logger.info(String.valueOf(message) + ": " + percent + "%");
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void clearCurrentTask() {
/*  422 */     this.currentTask = null;
/*  423 */     this.percentDone = 0;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void saveAllWorlds(boolean dontLog) {
/*  431 */     if (!this.worldIsBeingDeleted) {
/*      */       
/*  433 */       WorldServer[] var2 = this.worldServers;
/*  434 */       int var3 = var2.length;
/*      */       
/*  436 */       for (int var4 = 0; var4 < var3; var4++) {
/*      */         
/*  438 */         WorldServer var5 = var2[var4];
/*      */         
/*  440 */         if (var5 != null) {
/*      */           
/*  442 */           if (!dontLog)
/*      */           {
/*  444 */             logger.info("Saving chunks for level '" + var5.getWorldInfo().getWorldName() + "'/" + var5.provider.getDimensionName());
/*      */           }
/*      */ 
/*      */           
/*      */           try {
/*  449 */             var5.saveAllChunks(true, null);
/*      */           }
/*  451 */           catch (MinecraftException var7) {
/*      */             
/*  453 */             logger.warn(var7.getMessage());
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
/*      */   public void stopServer() {
/*  465 */     if (!this.worldIsBeingDeleted) {
/*      */       
/*  467 */       logger.info("Stopping server");
/*      */       
/*  469 */       if (getNetworkSystem() != null)
/*      */       {
/*  471 */         getNetworkSystem().terminateEndpoints();
/*      */       }
/*      */       
/*  474 */       if (this.serverConfigManager != null) {
/*      */         
/*  476 */         logger.info("Saving players");
/*  477 */         this.serverConfigManager.saveAllPlayerData();
/*  478 */         this.serverConfigManager.removeAllPlayers();
/*      */       } 
/*      */       
/*  481 */       if (this.worldServers != null) {
/*      */         
/*  483 */         logger.info("Saving worlds");
/*  484 */         saveAllWorlds(false);
/*      */         
/*  486 */         for (int var1 = 0; var1 < this.worldServers.length; var1++) {
/*      */           
/*  488 */           WorldServer var2 = this.worldServers[var1];
/*  489 */           var2.flush();
/*      */         } 
/*      */       } 
/*      */       
/*  493 */       if (this.usageSnooper.isSnooperRunning())
/*      */       {
/*  495 */         this.usageSnooper.stopSnooper();
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isServerRunning() {
/*  502 */     return this.serverRunning;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void initiateShutdown() {
/*  510 */     this.serverRunning = false;
/*      */   }
/*      */ 
/*      */   
/*      */   protected void func_175585_v() {
/*  515 */     mcServer = this;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void run() {
/*      */     try {
/*  522 */       if (startServer()) {
/*      */         
/*  524 */         this.currentTime = getCurrentTimeMillis();
/*  525 */         long var1 = 0L;
/*  526 */         this.statusResponse.setServerDescription((IChatComponent)new ChatComponentText(this.motd));
/*  527 */         this.statusResponse.setProtocolVersionInfo(new ServerStatusResponse.MinecraftProtocolVersionIdentifier("1.8", 47));
/*  528 */         addFaviconToStatusResponse(this.statusResponse);
/*      */         
/*  530 */         while (this.serverRunning)
/*      */         {
/*  532 */           long var48 = getCurrentTimeMillis();
/*  533 */           long var5 = var48 - this.currentTime;
/*      */           
/*  535 */           if (var5 > 2000L && this.currentTime - this.timeOfLastWarning >= 15000L) {
/*      */             
/*  537 */             logger.warn("Can't keep up! Did the system time change, or is the server overloaded? Running {}ms behind, skipping {} tick(s)", new Object[] { Long.valueOf(var5), Long.valueOf(var5 / 50L) });
/*  538 */             var5 = 2000L;
/*  539 */             this.timeOfLastWarning = this.currentTime;
/*      */           } 
/*      */           
/*  542 */           if (var5 < 0L) {
/*      */             
/*  544 */             logger.warn("Time ran backwards! Did the system time change?");
/*  545 */             var5 = 0L;
/*      */           } 
/*      */           
/*  548 */           var1 += var5;
/*  549 */           this.currentTime = var48;
/*      */           
/*  551 */           if (this.worldServers[0].areAllPlayersAsleep()) {
/*      */             
/*  553 */             tick();
/*  554 */             var1 = 0L;
/*      */           }
/*      */           else {
/*      */             
/*  558 */             while (var1 > 50L) {
/*      */               
/*  560 */               var1 -= 50L;
/*  561 */               tick();
/*      */             } 
/*      */           } 
/*      */           
/*  565 */           Thread.sleep(Math.max(1L, 50L - var1));
/*  566 */           this.serverIsRunning = true;
/*      */         }
/*      */       
/*      */       } else {
/*      */         
/*  571 */         finalTick(null);
/*      */       }
/*      */     
/*  574 */     } catch (Throwable var46) {
/*      */       
/*  576 */       logger.error("Encountered an unexpected exception", var46);
/*  577 */       CrashReport var2 = null;
/*      */       
/*  579 */       if (var46 instanceof ReportedException) {
/*      */         
/*  581 */         var2 = addServerInfoToCrashReport(((ReportedException)var46).getCrashReport());
/*      */       }
/*      */       else {
/*      */         
/*  585 */         var2 = addServerInfoToCrashReport(new CrashReport("Exception in server tick loop", var46));
/*      */       } 
/*      */       
/*  588 */       File var3 = new File(new File(getDataDirectory(), "crash-reports"), "crash-" + (new SimpleDateFormat("yyyy-MM-dd_HH.mm.ss")).format(new Date()) + "-server.txt");
/*      */       
/*  590 */       if (var2.saveToFile(var3)) {
/*      */         
/*  592 */         logger.error("This crash report has been saved to: " + var3.getAbsolutePath());
/*      */       }
/*      */       else {
/*      */         
/*  596 */         logger.error("We were unable to save this crash report to disk.");
/*      */       } 
/*      */       
/*  599 */       finalTick(var2);
/*      */     } finally {
/*      */ 
/*      */       
/*      */       try {
/*      */         
/*  605 */         stopServer();
/*  606 */         this.serverStopped = true;
/*      */       }
/*  608 */       catch (Throwable var44) {
/*      */         
/*  610 */         logger.error("Exception stopping the server", var44);
/*      */       }
/*      */       finally {
/*      */         
/*  614 */         systemExitNow();
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   private void addFaviconToStatusResponse(ServerStatusResponse response) {
/*  621 */     File var2 = getFile("server-icon.png");
/*      */     
/*  623 */     if (var2.isFile()) {
/*      */       
/*  625 */       ByteBuf var3 = Unpooled.buffer();
/*      */ 
/*      */       
/*      */       try {
/*  629 */         BufferedImage var4 = ImageIO.read(var2);
/*  630 */         Validate.validState((var4.getWidth() == 64), "Must be 64 pixels wide", new Object[0]);
/*  631 */         Validate.validState((var4.getHeight() == 64), "Must be 64 pixels high", new Object[0]);
/*  632 */         ImageIO.write(var4, "PNG", (OutputStream)new ByteBufOutputStream(var3));
/*  633 */         ByteBuf var5 = Base64.encode(var3);
/*  634 */         response.setFavicon("data:image/png;base64," + var5.toString(Charsets.UTF_8));
/*      */       }
/*  636 */       catch (Exception var9) {
/*      */         
/*  638 */         logger.error("Couldn't load server icon", var9);
/*      */       }
/*      */       finally {
/*      */         
/*  642 */         var3.release();
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public File getDataDirectory() {
/*  649 */     return new File(".");
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void finalTick(CrashReport report) {}
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void systemExitNow() {}
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void tick() {
/*  667 */     long var1 = System.nanoTime();
/*  668 */     this.tickCounter++;
/*      */     
/*  670 */     if (this.startProfiling) {
/*      */       
/*  672 */       this.startProfiling = false;
/*  673 */       this.theProfiler.profilingEnabled = true;
/*  674 */       this.theProfiler.clearProfiling();
/*      */     } 
/*      */     
/*  677 */     this.theProfiler.startSection("root");
/*  678 */     updateTimeLightAndEntities();
/*      */     
/*  680 */     if (var1 - this.nanoTimeSinceStatusRefresh >= 5000000000L) {
/*      */       
/*  682 */       this.nanoTimeSinceStatusRefresh = var1;
/*  683 */       this.statusResponse.setPlayerCountData(new ServerStatusResponse.PlayerCountData(getMaxPlayers(), getCurrentPlayerCount()));
/*  684 */       GameProfile[] var3 = new GameProfile[Math.min(getCurrentPlayerCount(), 12)];
/*  685 */       int var4 = MathHelper.getRandomIntegerInRange(this.random, 0, getCurrentPlayerCount() - var3.length);
/*      */       
/*  687 */       for (int var5 = 0; var5 < var3.length; var5++)
/*      */       {
/*  689 */         var3[var5] = ((EntityPlayerMP)this.serverConfigManager.playerEntityList.get(var4 + var5)).getGameProfile();
/*      */       }
/*      */       
/*  692 */       Collections.shuffle(Arrays.asList((Object[])var3));
/*  693 */       this.statusResponse.getPlayerCountData().setPlayers(var3);
/*      */     } 
/*      */     
/*  696 */     if (this.tickCounter % 900 == 0) {
/*      */       
/*  698 */       this.theProfiler.startSection("save");
/*  699 */       this.serverConfigManager.saveAllPlayerData();
/*  700 */       saveAllWorlds(true);
/*  701 */       this.theProfiler.endSection();
/*      */     } 
/*      */     
/*  704 */     this.theProfiler.startSection("tallying");
/*  705 */     this.tickTimeArray[this.tickCounter % 100] = System.nanoTime() - var1;
/*  706 */     this.theProfiler.endSection();
/*  707 */     this.theProfiler.startSection("snooper");
/*      */     
/*  709 */     if (!this.usageSnooper.isSnooperRunning() && this.tickCounter > 100)
/*      */     {
/*  711 */       this.usageSnooper.startSnooper();
/*      */     }
/*      */     
/*  714 */     if (this.tickCounter % 6000 == 0)
/*      */     {
/*  716 */       this.usageSnooper.addMemoryStatsToSnooper();
/*      */     }
/*      */     
/*  719 */     this.theProfiler.endSection();
/*  720 */     this.theProfiler.endSection();
/*      */   }
/*      */ 
/*      */   
/*      */   public void updateTimeLightAndEntities() {
/*  725 */     this.theProfiler.startSection("jobs");
/*  726 */     Queue var1 = this.futureTaskQueue;
/*      */     
/*  728 */     synchronized (this.futureTaskQueue) {
/*      */       
/*  730 */       while (!this.futureTaskQueue.isEmpty()) {
/*      */ 
/*      */         
/*      */         try {
/*  734 */           ((FutureTask)this.futureTaskQueue.poll()).run();
/*      */         }
/*  736 */         catch (Throwable var9) {
/*      */           
/*  738 */           logger.fatal(var9);
/*      */         } 
/*      */       } 
/*      */     } 
/*      */     
/*  743 */     this.theProfiler.endStartSection("levels");
/*      */     
/*      */     int var11;
/*  746 */     for (var11 = 0; var11 < this.worldServers.length; var11++) {
/*      */       
/*  748 */       long var2 = System.nanoTime();
/*      */       
/*  750 */       if (var11 == 0 || getAllowNether()) {
/*      */         
/*  752 */         WorldServer var4 = this.worldServers[var11];
/*  753 */         this.theProfiler.startSection(var4.getWorldInfo().getWorldName());
/*      */         
/*  755 */         if (this.tickCounter % 20 == 0) {
/*      */           
/*  757 */           this.theProfiler.startSection("timeSync");
/*  758 */           this.serverConfigManager.sendPacketToAllPlayersInDimension((Packet)new S03PacketTimeUpdate(var4.getTotalWorldTime(), var4.getWorldTime(), var4.getGameRules().getGameRuleBooleanValue("doDaylightCycle")), var4.provider.getDimensionId());
/*  759 */           this.theProfiler.endSection();
/*      */         } 
/*      */         
/*  762 */         this.theProfiler.startSection("tick");
/*      */ 
/*      */ 
/*      */         
/*      */         try {
/*  767 */           var4.tick();
/*      */         }
/*  769 */         catch (Throwable var8) {
/*      */           
/*  771 */           CrashReport var6 = CrashReport.makeCrashReport(var8, "Exception ticking world");
/*  772 */           var4.addWorldInfoToCrashReport(var6);
/*  773 */           throw new ReportedException(var6);
/*      */         } 
/*      */ 
/*      */         
/*      */         try {
/*  778 */           var4.updateEntities();
/*      */         }
/*  780 */         catch (Throwable var7) {
/*      */           
/*  782 */           CrashReport var6 = CrashReport.makeCrashReport(var7, "Exception ticking world entities");
/*  783 */           var4.addWorldInfoToCrashReport(var6);
/*  784 */           throw new ReportedException(var6);
/*      */         } 
/*      */         
/*  787 */         this.theProfiler.endSection();
/*  788 */         this.theProfiler.startSection("tracker");
/*  789 */         var4.getEntityTracker().updateTrackedEntities();
/*  790 */         this.theProfiler.endSection();
/*  791 */         this.theProfiler.endSection();
/*      */       } 
/*      */       
/*  794 */       this.timeOfLastDimensionTick[var11][this.tickCounter % 100] = System.nanoTime() - var2;
/*      */     } 
/*      */     
/*  797 */     this.theProfiler.endStartSection("connection");
/*  798 */     getNetworkSystem().networkTick();
/*  799 */     this.theProfiler.endStartSection("players");
/*  800 */     this.serverConfigManager.onTick();
/*  801 */     this.theProfiler.endStartSection("tickables");
/*      */     
/*  803 */     for (var11 = 0; var11 < this.playersOnline.size(); var11++)
/*      */     {
/*  805 */       ((IUpdatePlayerListBox)this.playersOnline.get(var11)).update();
/*      */     }
/*      */     
/*  808 */     this.theProfiler.endSection();
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean getAllowNether() {
/*  813 */     return true;
/*      */   }
/*      */ 
/*      */   
/*      */   public void startServerThread() {
/*  818 */     this.serverThread = new Thread(this, "Server thread");
/*  819 */     this.serverThread.start();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public File getFile(String fileName) {
/*  827 */     return new File(getDataDirectory(), fileName);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void logWarning(String msg) {
/*  835 */     logger.warn(msg);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public WorldServer worldServerForDimension(int dimension) {
/*  843 */     return (dimension == -1) ? this.worldServers[1] : ((dimension == 1) ? this.worldServers[2] : this.worldServers[0]);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getMinecraftVersion() {
/*  851 */     return "1.8";
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getCurrentPlayerCount() {
/*  859 */     return this.serverConfigManager.getCurrentPlayerCount();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getMaxPlayers() {
/*  867 */     return this.serverConfigManager.getMaxPlayers();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String[] getAllUsernames() {
/*  875 */     return this.serverConfigManager.getAllUsernames();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public GameProfile[] getGameProfiles() {
/*  883 */     return this.serverConfigManager.getAllProfiles();
/*      */   }
/*      */ 
/*      */   
/*      */   public String getServerModName() {
/*  888 */     return "vanilla";
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public CrashReport addServerInfoToCrashReport(CrashReport report) {
/*  896 */     report.getCategory().addCrashSectionCallable("Profiler Position", new Callable()
/*      */         {
/*      */           private static final String __OBFID = "CL_00001418";
/*      */           
/*      */           public String func_179879_a() {
/*  901 */             return MinecraftServer.this.theProfiler.profilingEnabled ? MinecraftServer.this.theProfiler.getNameOfLastSection() : "N/A (disabled)";
/*      */           }
/*      */           
/*      */           public Object call() {
/*  905 */             return func_179879_a();
/*      */           }
/*      */         });
/*      */     
/*  909 */     if (this.serverConfigManager != null)
/*      */     {
/*  911 */       report.getCategory().addCrashSectionCallable("Player Count", new Callable()
/*      */           {
/*      */             private static final String __OBFID = "CL_00001419";
/*      */             
/*      */             public String call() {
/*  916 */               return String.valueOf(MinecraftServer.this.serverConfigManager.getCurrentPlayerCount()) + " / " + MinecraftServer.this.serverConfigManager.getMaxPlayers() + "; " + MinecraftServer.this.serverConfigManager.playerEntityList;
/*      */             }
/*      */           });
/*      */     }
/*      */     
/*  921 */     return report;
/*      */   }
/*      */ 
/*      */   
/*      */   public List func_180506_a(ICommandSender p_180506_1_, String p_180506_2_, BlockPos p_180506_3_) {
/*  926 */     ArrayList<String> var4 = Lists.newArrayList();
/*      */     
/*  928 */     if (p_180506_2_.startsWith("/")) {
/*      */       
/*  930 */       p_180506_2_ = p_180506_2_.substring(1);
/*  931 */       boolean var11 = !p_180506_2_.contains(" ");
/*  932 */       List var12 = this.commandManager.getTabCompletionOptions(p_180506_1_, p_180506_2_, p_180506_3_);
/*      */       
/*  934 */       if (var12 != null) {
/*      */         
/*  936 */         Iterator<String> var13 = var12.iterator();
/*      */         
/*  938 */         while (var13.hasNext()) {
/*      */           
/*  940 */           String var14 = var13.next();
/*      */           
/*  942 */           if (var11) {
/*      */             
/*  944 */             var4.add("/" + var14);
/*      */             
/*      */             continue;
/*      */           } 
/*  948 */           var4.add(var14);
/*      */         } 
/*      */       } 
/*      */ 
/*      */       
/*  953 */       return var4;
/*      */     } 
/*      */ 
/*      */     
/*  957 */     String[] var5 = p_180506_2_.split(" ", -1);
/*  958 */     String var6 = var5[var5.length - 1];
/*  959 */     String[] var7 = this.serverConfigManager.getAllUsernames();
/*  960 */     int var8 = var7.length;
/*      */     
/*  962 */     for (int var9 = 0; var9 < var8; var9++) {
/*      */       
/*  964 */       String var10 = var7[var9];
/*      */       
/*  966 */       if (CommandBase.doesStringStartWith(var6, var10))
/*      */       {
/*  968 */         var4.add(var10);
/*      */       }
/*      */     } 
/*      */     
/*  972 */     return var4;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static MinecraftServer getServer() {
/*  981 */     return mcServer;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean func_175578_N() {
/*  986 */     return (this.anvilFile != null);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getName() {
/*  994 */     return "Server";
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void addChatMessage(IChatComponent message) {
/* 1005 */     logger.info(message.getUnformattedText());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean canCommandSenderUseCommand(int permissionLevel, String command) {
/* 1013 */     return true;
/*      */   }
/*      */ 
/*      */   
/*      */   public ICommandManager getCommandManager() {
/* 1018 */     return this.commandManager;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public KeyPair getKeyPair() {
/* 1026 */     return this.serverKeyPair;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getServerOwner() {
/* 1034 */     return this.serverOwner;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setServerOwner(String owner) {
/* 1042 */     this.serverOwner = owner;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isSinglePlayer() {
/* 1047 */     return (this.serverOwner != null);
/*      */   }
/*      */ 
/*      */   
/*      */   public String getFolderName() {
/* 1052 */     return this.folderName;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setFolderName(String name) {
/* 1057 */     this.folderName = name;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setWorldName(String p_71246_1_) {
/* 1062 */     this.worldName = p_71246_1_;
/*      */   }
/*      */ 
/*      */   
/*      */   public String getWorldName() {
/* 1067 */     return this.worldName;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setKeyPair(KeyPair keyPair) {
/* 1072 */     this.serverKeyPair = keyPair;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setDifficultyForAllWorlds(EnumDifficulty difficulty) {
/* 1077 */     for (int var2 = 0; var2 < this.worldServers.length; var2++) {
/*      */       
/* 1079 */       WorldServer var3 = this.worldServers[var2];
/*      */       
/* 1081 */       if (var3 != null)
/*      */       {
/* 1083 */         if (var3.getWorldInfo().isHardcoreModeEnabled()) {
/*      */           
/* 1085 */           var3.getWorldInfo().setDifficulty(EnumDifficulty.HARD);
/* 1086 */           var3.setAllowedSpawnTypes(true, true);
/*      */         }
/* 1088 */         else if (isSinglePlayer()) {
/*      */           
/* 1090 */           var3.getWorldInfo().setDifficulty(difficulty);
/* 1091 */           var3.setAllowedSpawnTypes((var3.getDifficulty() != EnumDifficulty.PEACEFUL), true);
/*      */         }
/*      */         else {
/*      */           
/* 1095 */           var3.getWorldInfo().setDifficulty(difficulty);
/* 1096 */           var3.setAllowedSpawnTypes(allowSpawnMonsters(), this.canSpawnAnimals);
/*      */         } 
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   protected boolean allowSpawnMonsters() {
/* 1104 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isDemo() {
/* 1112 */     return this.isDemo;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setDemo(boolean demo) {
/* 1120 */     this.isDemo = demo;
/*      */   }
/*      */ 
/*      */   
/*      */   public void canCreateBonusChest(boolean enable) {
/* 1125 */     this.enableBonusChest = enable;
/*      */   }
/*      */ 
/*      */   
/*      */   public ISaveFormat getActiveAnvilConverter() {
/* 1130 */     return this.anvilConverterForAnvilFile;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void deleteWorldAndStopServer() {
/* 1139 */     this.worldIsBeingDeleted = true;
/* 1140 */     getActiveAnvilConverter().flushCache();
/*      */     
/* 1142 */     for (int var1 = 0; var1 < this.worldServers.length; var1++) {
/*      */       
/* 1144 */       WorldServer var2 = this.worldServers[var1];
/*      */       
/* 1146 */       if (var2 != null)
/*      */       {
/* 1148 */         var2.flush();
/*      */       }
/*      */     } 
/*      */     
/* 1152 */     getActiveAnvilConverter().deleteWorldDirectory(this.worldServers[0].getSaveHandler().getWorldDirectoryName());
/* 1153 */     initiateShutdown();
/*      */   }
/*      */ 
/*      */   
/*      */   public String getResourcePackUrl() {
/* 1158 */     return this.resourcePackUrl;
/*      */   }
/*      */ 
/*      */   
/*      */   public String getResourcePackHash() {
/* 1163 */     return this.resourcePackHash;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setResourcePack(String url, String hash) {
/* 1168 */     this.resourcePackUrl = url;
/* 1169 */     this.resourcePackHash = hash;
/*      */   }
/*      */ 
/*      */   
/*      */   public void addServerStatsToSnooper(PlayerUsageSnooper playerSnooper) {
/* 1174 */     playerSnooper.addClientStat("whitelist_enabled", Boolean.valueOf(false));
/* 1175 */     playerSnooper.addClientStat("whitelist_count", Integer.valueOf(0));
/*      */     
/* 1177 */     if (this.serverConfigManager != null) {
/*      */       
/* 1179 */       playerSnooper.addClientStat("players_current", Integer.valueOf(getCurrentPlayerCount()));
/* 1180 */       playerSnooper.addClientStat("players_max", Integer.valueOf(getMaxPlayers()));
/* 1181 */       playerSnooper.addClientStat("players_seen", Integer.valueOf((this.serverConfigManager.getAvailablePlayerDat()).length));
/*      */     } 
/*      */     
/* 1184 */     playerSnooper.addClientStat("uses_auth", Boolean.valueOf(this.onlineMode));
/* 1185 */     playerSnooper.addClientStat("gui_state", getGuiEnabled() ? "enabled" : "disabled");
/* 1186 */     playerSnooper.addClientStat("run_time", Long.valueOf((getCurrentTimeMillis() - playerSnooper.getMinecraftStartTimeMillis()) / 60L * 1000L));
/* 1187 */     playerSnooper.addClientStat("avg_tick_ms", Integer.valueOf((int)(MathHelper.average(this.tickTimeArray) * 1.0E-6D)));
/* 1188 */     int var2 = 0;
/*      */     
/* 1190 */     if (this.worldServers != null)
/*      */     {
/* 1192 */       for (int var3 = 0; var3 < this.worldServers.length; var3++) {
/*      */         
/* 1194 */         if (this.worldServers[var3] != null) {
/*      */           
/* 1196 */           WorldServer var4 = this.worldServers[var3];
/* 1197 */           WorldInfo var5 = var4.getWorldInfo();
/* 1198 */           playerSnooper.addClientStat("world[" + var2 + "][dimension]", Integer.valueOf(var4.provider.getDimensionId()));
/* 1199 */           playerSnooper.addClientStat("world[" + var2 + "][mode]", var5.getGameType());
/* 1200 */           playerSnooper.addClientStat("world[" + var2 + "][difficulty]", var4.getDifficulty());
/* 1201 */           playerSnooper.addClientStat("world[" + var2 + "][hardcore]", Boolean.valueOf(var5.isHardcoreModeEnabled()));
/* 1202 */           playerSnooper.addClientStat("world[" + var2 + "][generator_name]", var5.getTerrainType().getWorldTypeName());
/* 1203 */           playerSnooper.addClientStat("world[" + var2 + "][generator_version]", Integer.valueOf(var5.getTerrainType().getGeneratorVersion()));
/* 1204 */           playerSnooper.addClientStat("world[" + var2 + "][height]", Integer.valueOf(this.buildLimit));
/* 1205 */           playerSnooper.addClientStat("world[" + var2 + "][chunks_loaded]", Integer.valueOf(var4.getChunkProvider().getLoadedChunkCount()));
/* 1206 */           var2++;
/*      */         } 
/*      */       } 
/*      */     }
/*      */     
/* 1211 */     playerSnooper.addClientStat("worlds", Integer.valueOf(var2));
/*      */   }
/*      */ 
/*      */   
/*      */   public void addServerTypeToSnooper(PlayerUsageSnooper playerSnooper) {
/* 1216 */     playerSnooper.addStatToSnooper("singleplayer", Boolean.valueOf(isSinglePlayer()));
/* 1217 */     playerSnooper.addStatToSnooper("server_brand", getServerModName());
/* 1218 */     playerSnooper.addStatToSnooper("gui_supported", GraphicsEnvironment.isHeadless() ? "headless" : "supported");
/* 1219 */     playerSnooper.addStatToSnooper("dedicated", Boolean.valueOf(isDedicatedServer()));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isSnooperEnabled() {
/* 1227 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isServerInOnlineMode() {
/* 1234 */     return this.onlineMode;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setOnlineMode(boolean online) {
/* 1239 */     this.onlineMode = online;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean getCanSpawnAnimals() {
/* 1244 */     return this.canSpawnAnimals;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setCanSpawnAnimals(boolean spawnAnimals) {
/* 1249 */     this.canSpawnAnimals = spawnAnimals;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean getCanSpawnNPCs() {
/* 1254 */     return this.canSpawnNPCs;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setCanSpawnNPCs(boolean spawnNpcs) {
/* 1259 */     this.canSpawnNPCs = spawnNpcs;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isPVPEnabled() {
/* 1264 */     return this.pvpEnabled;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setAllowPvp(boolean allowPvp) {
/* 1269 */     this.pvpEnabled = allowPvp;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isFlightAllowed() {
/* 1274 */     return this.allowFlight;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setAllowFlight(boolean allow) {
/* 1279 */     this.allowFlight = allow;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getMOTD() {
/* 1289 */     return this.motd;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setMOTD(String motdIn) {
/* 1294 */     this.motd = motdIn;
/*      */   }
/*      */ 
/*      */   
/*      */   public int getBuildLimit() {
/* 1299 */     return this.buildLimit;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setBuildLimit(int maxBuildHeight) {
/* 1304 */     this.buildLimit = maxBuildHeight;
/*      */   }
/*      */ 
/*      */   
/*      */   public ServerConfigurationManager getConfigurationManager() {
/* 1309 */     return this.serverConfigManager;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setConfigManager(ServerConfigurationManager configManager) {
/* 1314 */     this.serverConfigManager = configManager;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setGameType(WorldSettings.GameType gameMode) {
/* 1322 */     for (int var2 = 0; var2 < this.worldServers.length; var2++)
/*      */     {
/* 1324 */       (getServer()).worldServers[var2].getWorldInfo().setGameType(gameMode);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public NetworkSystem getNetworkSystem() {
/* 1330 */     return this.networkSystem;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean serverIsInRunLoop() {
/* 1335 */     return this.serverIsRunning;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean getGuiEnabled() {
/* 1340 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getTickCounter() {
/* 1350 */     return this.tickCounter;
/*      */   }
/*      */ 
/*      */   
/*      */   public void enableProfiling() {
/* 1355 */     this.startProfiling = true;
/*      */   }
/*      */ 
/*      */   
/*      */   public PlayerUsageSnooper getPlayerUsageSnooper() {
/* 1360 */     return this.usageSnooper;
/*      */   }
/*      */ 
/*      */   
/*      */   public BlockPos getPosition() {
/* 1365 */     return BlockPos.ORIGIN;
/*      */   }
/*      */ 
/*      */   
/*      */   public Vec3 getPositionVector() {
/* 1370 */     return new Vec3(0.0D, 0.0D, 0.0D);
/*      */   }
/*      */ 
/*      */   
/*      */   public World getEntityWorld() {
/* 1375 */     return (World)this.worldServers[0];
/*      */   }
/*      */ 
/*      */   
/*      */   public Entity getCommandSenderEntity() {
/* 1380 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getSpawnProtectionSize() {
/* 1388 */     return 16;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isBlockProtected(World worldIn, BlockPos pos, EntityPlayer playerIn) {
/* 1393 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean getForceGamemode() {
/* 1398 */     return this.isGamemodeForced;
/*      */   }
/*      */ 
/*      */   
/*      */   public Proxy getServerProxy() {
/* 1403 */     return this.serverProxy;
/*      */   }
/*      */ 
/*      */   
/*      */   public static long getCurrentTimeMillis() {
/* 1408 */     return System.currentTimeMillis();
/*      */   }
/*      */ 
/*      */   
/*      */   public int getMaxPlayerIdleMinutes() {
/* 1413 */     return this.maxPlayerIdleMinutes;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setPlayerIdleTimeout(int idleTimeout) {
/* 1418 */     this.maxPlayerIdleMinutes = idleTimeout;
/*      */   }
/*      */ 
/*      */   
/*      */   public IChatComponent getDisplayName() {
/* 1423 */     return (IChatComponent)new ChatComponentText(getName());
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isAnnouncingPlayerAchievements() {
/* 1428 */     return true;
/*      */   }
/*      */ 
/*      */   
/*      */   public MinecraftSessionService getMinecraftSessionService() {
/* 1433 */     return this.sessionService;
/*      */   }
/*      */ 
/*      */   
/*      */   public GameProfileRepository getGameProfileRepository() {
/* 1438 */     return this.profileRepo;
/*      */   }
/*      */ 
/*      */   
/*      */   public PlayerProfileCache getPlayerProfileCache() {
/* 1443 */     return this.profileCache;
/*      */   }
/*      */ 
/*      */   
/*      */   public ServerStatusResponse getServerStatusResponse() {
/* 1448 */     return this.statusResponse;
/*      */   }
/*      */ 
/*      */   
/*      */   public void refreshStatusNextTick() {
/* 1453 */     this.nanoTimeSinceStatusRefresh = 0L;
/*      */   }
/*      */ 
/*      */   
/*      */   public Entity getEntityFromUuid(UUID uuid) {
/* 1458 */     WorldServer[] var2 = this.worldServers;
/* 1459 */     int var3 = var2.length;
/*      */     
/* 1461 */     for (int var4 = 0; var4 < var3; var4++) {
/*      */       
/* 1463 */       WorldServer var5 = var2[var4];
/*      */       
/* 1465 */       if (var5 != null) {
/*      */         
/* 1467 */         Entity var6 = var5.getEntityFromUuid(uuid);
/*      */         
/* 1469 */         if (var6 != null)
/*      */         {
/* 1471 */           return var6;
/*      */         }
/*      */       } 
/*      */     } 
/*      */     
/* 1476 */     return null;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean sendCommandFeedback() {
/* 1481 */     return (getServer()).worldServers[0].getGameRules().getGameRuleBooleanValue("sendCommandFeedback");
/*      */   }
/*      */ 
/*      */   
/*      */   public void func_174794_a(CommandResultStats.Type p_174794_1_, int p_174794_2_) {}
/*      */   
/*      */   public int getMaxWorldSize() {
/* 1488 */     return 29999984;
/*      */   }
/*      */ 
/*      */   
/*      */   public ListenableFuture callFromMainThread(Callable callable) {
/* 1493 */     Validate.notNull(callable);
/*      */     
/* 1495 */     if (!isCallingFromMinecraftThread()) {
/*      */       
/* 1497 */       ListenableFutureTask var2 = ListenableFutureTask.create(callable);
/* 1498 */       Queue var3 = this.futureTaskQueue;
/*      */       
/* 1500 */       synchronized (this.futureTaskQueue) {
/*      */         
/* 1502 */         this.futureTaskQueue.add(var2);
/* 1503 */         return (ListenableFuture)var2;
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*      */     try {
/* 1510 */       return Futures.immediateFuture(callable.call());
/*      */     }
/* 1512 */     catch (Exception var6) {
/*      */       
/* 1514 */       return (ListenableFuture)Futures.immediateFailedCheckedFuture(var6);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public ListenableFuture addScheduledTask(Runnable runnableToSchedule) {
/* 1521 */     Validate.notNull(runnableToSchedule);
/* 1522 */     return callFromMainThread(Executors.callable(runnableToSchedule));
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isCallingFromMinecraftThread() {
/* 1527 */     return (Thread.currentThread() == this.serverThread);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getNetworkCompressionTreshold() {
/* 1535 */     return 256;
/*      */   }
/*      */   
/*      */   protected abstract boolean startServer() throws IOException;
/*      */   
/*      */   public abstract boolean canStructuresSpawn();
/*      */   
/*      */   public abstract WorldSettings.GameType getGameType();
/*      */   
/*      */   public abstract EnumDifficulty getDifficulty();
/*      */   
/*      */   public abstract boolean isHardcore();
/*      */   
/*      */   public abstract int getOpPermissionLevel();
/*      */   
/*      */   public abstract boolean isDedicatedServer();
/*      */   
/*      */   public abstract boolean isCommandBlockEnabled();
/*      */   
/*      */   public abstract String shareToLAN(WorldSettings.GameType paramGameType, boolean paramBoolean);
/*      */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\server\MinecraftServer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */