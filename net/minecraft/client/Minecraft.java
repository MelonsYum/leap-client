/*      */ package net.minecraft.client;
/*      */ 
/*      */ import com.google.common.collect.Iterables;
/*      */ import com.google.common.collect.Lists;
/*      */ import com.google.common.collect.Maps;
/*      */ import com.google.common.collect.Queues;
/*      */ import com.google.common.collect.Sets;
/*      */ import com.google.common.util.concurrent.Futures;
/*      */ import com.google.common.util.concurrent.ListenableFuture;
/*      */ import com.google.common.util.concurrent.ListenableFutureTask;
/*      */ import com.mojang.authlib.minecraft.MinecraftSessionService;
/*      */ import com.mojang.authlib.properties.Property;
/*      */ import com.mojang.authlib.properties.PropertyMap;
/*      */ import com.mojang.authlib.yggdrasil.YggdrasilAuthenticationService;
/*      */ import java.awt.image.BufferedImage;
/*      */ import java.io.File;
/*      */ import java.io.IOException;
/*      */ import java.io.InputStream;
/*      */ import java.net.Proxy;
/*      */ import java.net.SocketAddress;
/*      */ import java.nio.ByteBuffer;
/*      */ import java.nio.ByteOrder;
/*      */ import java.text.DecimalFormat;
/*      */ import java.text.SimpleDateFormat;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Collections;
/*      */ import java.util.Date;
/*      */ import java.util.HashMap;
/*      */ import java.util.HashSet;
/*      */ import java.util.Iterator;
/*      */ import java.util.List;
/*      */ import java.util.Map;
/*      */ import java.util.Queue;
/*      */ import java.util.UUID;
/*      */ import java.util.concurrent.Callable;
/*      */ import java.util.concurrent.Executors;
/*      */ import java.util.concurrent.FutureTask;
/*      */ import javax.imageio.ImageIO;
/*      */ import leap.Client;
/*      */ import leap.ui.MainMenu;
/*      */ import net.minecraft.block.Block;
/*      */ import net.minecraft.block.material.Material;
/*      */ import net.minecraft.client.audio.MusicTicker;
/*      */ import net.minecraft.client.audio.SoundHandler;
/*      */ import net.minecraft.client.entity.EntityPlayerSP;
/*      */ import net.minecraft.client.gui.FontRenderer;
/*      */ import net.minecraft.client.gui.GuiChat;
/*      */ import net.minecraft.client.gui.GuiControls;
/*      */ import net.minecraft.client.gui.GuiGameOver;
/*      */ import net.minecraft.client.gui.GuiIngame;
/*      */ import net.minecraft.client.gui.GuiIngameMenu;
/*      */ import net.minecraft.client.gui.GuiMemoryErrorScreen;
/*      */ import net.minecraft.client.gui.GuiScreen;
/*      */ import net.minecraft.client.gui.GuiSleepMP;
/*      */ import net.minecraft.client.gui.GuiYesNo;
/*      */ import net.minecraft.client.gui.GuiYesNoCallback;
/*      */ import net.minecraft.client.gui.ScaledResolution;
/*      */ import net.minecraft.client.gui.achievement.GuiAchievement;
/*      */ import net.minecraft.client.gui.inventory.GuiInventory;
/*      */ import net.minecraft.client.gui.stream.GuiStreamUnavailable;
/*      */ import net.minecraft.client.main.GameConfiguration;
/*      */ import net.minecraft.client.multiplayer.GuiConnecting;
/*      */ import net.minecraft.client.multiplayer.PlayerControllerMP;
/*      */ import net.minecraft.client.multiplayer.ServerData;
/*      */ import net.minecraft.client.multiplayer.WorldClient;
/*      */ import net.minecraft.client.network.NetHandlerLoginClient;
/*      */ import net.minecraft.client.network.NetHandlerPlayClient;
/*      */ import net.minecraft.client.particle.EffectRenderer;
/*      */ import net.minecraft.client.renderer.BlockRendererDispatcher;
/*      */ import net.minecraft.client.renderer.EntityRenderer;
/*      */ import net.minecraft.client.renderer.GlStateManager;
/*      */ import net.minecraft.client.renderer.ItemRenderer;
/*      */ import net.minecraft.client.renderer.OpenGlHelper;
/*      */ import net.minecraft.client.renderer.RenderGlobal;
/*      */ import net.minecraft.client.renderer.Tessellator;
/*      */ import net.minecraft.client.renderer.WorldRenderer;
/*      */ import net.minecraft.client.renderer.chunk.RenderChunk;
/*      */ import net.minecraft.client.renderer.entity.RenderItem;
/*      */ import net.minecraft.client.renderer.entity.RenderManager;
/*      */ import net.minecraft.client.renderer.texture.DynamicTexture;
/*      */ import net.minecraft.client.renderer.texture.ITickableTextureObject;
/*      */ import net.minecraft.client.renderer.texture.TextureManager;
/*      */ import net.minecraft.client.renderer.texture.TextureMap;
/*      */ import net.minecraft.client.resources.DefaultResourcePack;
/*      */ import net.minecraft.client.resources.FoliageColorReloadListener;
/*      */ import net.minecraft.client.resources.GrassColorReloadListener;
/*      */ import net.minecraft.client.resources.I18n;
/*      */ import net.minecraft.client.resources.IReloadableResourceManager;
/*      */ import net.minecraft.client.resources.IResourceManager;
/*      */ import net.minecraft.client.resources.IResourceManagerReloadListener;
/*      */ import net.minecraft.client.resources.IResourcePack;
/*      */ import net.minecraft.client.resources.LanguageManager;
/*      */ import net.minecraft.client.resources.ResourceIndex;
/*      */ import net.minecraft.client.resources.ResourcePackRepository;
/*      */ import net.minecraft.client.resources.SimpleReloadableResourceManager;
/*      */ import net.minecraft.client.resources.SkinManager;
/*      */ import net.minecraft.client.resources.data.AnimationMetadataSection;
/*      */ import net.minecraft.client.resources.data.AnimationMetadataSectionSerializer;
/*      */ import net.minecraft.client.resources.data.FontMetadataSection;
/*      */ import net.minecraft.client.resources.data.FontMetadataSectionSerializer;
/*      */ import net.minecraft.client.resources.data.IMetadataSectionSerializer;
/*      */ import net.minecraft.client.resources.data.IMetadataSerializer;
/*      */ import net.minecraft.client.resources.data.LanguageMetadataSection;
/*      */ import net.minecraft.client.resources.data.LanguageMetadataSectionSerializer;
/*      */ import net.minecraft.client.resources.data.PackMetadataSection;
/*      */ import net.minecraft.client.resources.data.PackMetadataSectionSerializer;
/*      */ import net.minecraft.client.resources.data.TextureMetadataSection;
/*      */ import net.minecraft.client.resources.data.TextureMetadataSectionSerializer;
/*      */ import net.minecraft.client.resources.model.ModelManager;
/*      */ import net.minecraft.client.settings.GameSettings;
/*      */ import net.minecraft.client.settings.KeyBinding;
/*      */ import net.minecraft.client.shader.Framebuffer;
/*      */ import net.minecraft.client.stream.IStream;
/*      */ import net.minecraft.client.stream.NullStream;
/*      */ import net.minecraft.client.stream.TwitchStream;
/*      */ import net.minecraft.crash.CrashReport;
/*      */ import net.minecraft.crash.CrashReportCategory;
/*      */ import net.minecraft.entity.Entity;
/*      */ import net.minecraft.entity.EntityList;
/*      */ import net.minecraft.entity.boss.BossStatus;
/*      */ import net.minecraft.entity.item.EntityItemFrame;
/*      */ import net.minecraft.entity.item.EntityMinecart;
/*      */ import net.minecraft.entity.player.EntityPlayer;
/*      */ import net.minecraft.entity.player.InventoryPlayer;
/*      */ import net.minecraft.init.Bootstrap;
/*      */ import net.minecraft.init.Items;
/*      */ import net.minecraft.item.Item;
/*      */ import net.minecraft.item.ItemStack;
/*      */ import net.minecraft.nbt.NBTBase;
/*      */ import net.minecraft.nbt.NBTTagCompound;
/*      */ import net.minecraft.nbt.NBTTagList;
/*      */ import net.minecraft.nbt.NBTTagString;
/*      */ import net.minecraft.network.EnumConnectionState;
/*      */ import net.minecraft.network.INetHandler;
/*      */ import net.minecraft.network.NetworkManager;
/*      */ import net.minecraft.network.Packet;
/*      */ import net.minecraft.network.handshake.client.C00Handshake;
/*      */ import net.minecraft.network.login.client.C00PacketLoginStart;
/*      */ import net.minecraft.network.play.client.C16PacketClientStatus;
/*      */ import net.minecraft.profiler.IPlayerUsage;
/*      */ import net.minecraft.profiler.PlayerUsageSnooper;
/*      */ import net.minecraft.profiler.Profiler;
/*      */ import net.minecraft.server.MinecraftServer;
/*      */ import net.minecraft.server.integrated.IntegratedServer;
/*      */ import net.minecraft.stats.AchievementList;
/*      */ import net.minecraft.stats.IStatStringFormat;
/*      */ import net.minecraft.stats.StatFileWriter;
/*      */ import net.minecraft.tileentity.TileEntity;
/*      */ import net.minecraft.util.BlockPos;
/*      */ import net.minecraft.util.ChatComponentText;
/*      */ import net.minecraft.util.IChatComponent;
/*      */ import net.minecraft.util.IThreadListener;
/*      */ import net.minecraft.util.MathHelper;
/*      */ import net.minecraft.util.MinecraftError;
/*      */ import net.minecraft.util.MouseHelper;
/*      */ import net.minecraft.util.MovementInput;
/*      */ import net.minecraft.util.MovementInputFromOptions;
/*      */ import net.minecraft.util.MovingObjectPosition;
/*      */ import net.minecraft.util.ReportedException;
/*      */ import net.minecraft.util.ResourceLocation;
/*      */ import net.minecraft.util.ScreenShotHelper;
/*      */ import net.minecraft.util.Session;
/*      */ import net.minecraft.util.Timer;
/*      */ import net.minecraft.util.Util;
/*      */ import net.minecraft.world.EnumDifficulty;
/*      */ import net.minecraft.world.World;
/*      */ import net.minecraft.world.WorldSettings;
/*      */ import net.minecraft.world.chunk.storage.AnvilSaveConverter;
/*      */ import net.minecraft.world.storage.ISaveFormat;
/*      */ import net.minecraft.world.storage.ISaveHandler;
/*      */ import net.minecraft.world.storage.WorldInfo;
/*      */ import org.apache.commons.io.IOUtils;
/*      */ import org.apache.commons.lang3.Validate;
/*      */ import org.apache.logging.log4j.LogManager;
/*      */ import org.apache.logging.log4j.Logger;
/*      */ import org.lwjgl.LWJGLException;
/*      */ import org.lwjgl.Sys;
/*      */ import org.lwjgl.input.Keyboard;
/*      */ import org.lwjgl.input.Mouse;
/*      */ import org.lwjgl.opengl.ContextCapabilities;
/*      */ import org.lwjgl.opengl.Display;
/*      */ import org.lwjgl.opengl.DisplayMode;
/*      */ import org.lwjgl.opengl.GL11;
/*      */ import org.lwjgl.opengl.GLContext;
/*      */ import org.lwjgl.opengl.OpenGLException;
/*      */ import org.lwjgl.opengl.PixelFormat;
/*      */ import org.lwjgl.util.glu.GLU;
/*      */ 
/*      */ 
/*      */ public class Minecraft
/*      */   implements IThreadListener, IPlayerUsage
/*      */ {
/*  193 */   private static final Logger logger = LogManager.getLogger();
/*  194 */   private static final ResourceLocation locationMojangPng = new ResourceLocation("textures/gui/title/mojang.png");
/*  195 */   public static final boolean isRunningOnMac = (Util.getOSType() == Util.EnumOS.OSX);
/*      */ 
/*      */   
/*  198 */   public static byte[] memoryReserve = new byte[10485760];
/*  199 */   private static final List macDisplayModes = Lists.newArrayList((Object[])new DisplayMode[] { new DisplayMode(2560, 1600), new DisplayMode(2880, 1800) });
/*      */   
/*      */   private final File fileResourcepacks;
/*      */   
/*      */   private final PropertyMap twitchDetails;
/*      */   
/*      */   private ServerData currentServerData;
/*      */   
/*      */   public TextureManager renderEngine;
/*      */   
/*      */   private static Minecraft theMinecraft;
/*      */   
/*      */   public PlayerControllerMP playerController;
/*      */   
/*      */   private boolean fullscreen;
/*      */   
/*      */   private boolean field_175619_R = true;
/*      */   private boolean hasCrashed;
/*      */   private CrashReport crashReporter;
/*      */   public int displayWidth;
/*      */   public int displayHeight;
/*  220 */   public Timer timer = new Timer(20.0F);
/*      */ 
/*      */   
/*  223 */   private PlayerUsageSnooper usageSnooper = new PlayerUsageSnooper("client", this, MinecraftServer.getCurrentTimeMillis());
/*      */   
/*      */   public WorldClient theWorld;
/*      */   
/*      */   public RenderGlobal renderGlobal;
/*      */   
/*      */   public RenderManager renderManager;
/*      */   
/*      */   private RenderItem renderItem;
/*      */   
/*      */   private ItemRenderer itemRenderer;
/*      */   
/*      */   public EntityPlayerSP thePlayer;
/*      */   
/*      */   private Entity field_175622_Z;
/*      */   
/*      */   public Entity pointedEntity;
/*      */   
/*      */   public EffectRenderer effectRenderer;
/*      */   
/*      */   public Session session;
/*      */   
/*      */   private boolean isGamePaused;
/*      */   
/*      */   public FontRenderer fontRendererObj;
/*      */   
/*      */   public FontRenderer standardGalacticFontRenderer;
/*      */   
/*      */   public GuiScreen currentScreen;
/*      */   
/*      */   public LoadingScreenRenderer loadingScreen;
/*      */   
/*      */   public EntityRenderer entityRenderer;
/*      */   
/*      */   private int leftClickCounter;
/*      */   
/*      */   private int tempDisplayWidth;
/*      */   
/*      */   private int tempDisplayHeight;
/*      */   
/*      */   private IntegratedServer theIntegratedServer;
/*      */   
/*      */   public GuiAchievement guiAchievement;
/*      */   
/*      */   public GuiIngame ingameGUI;
/*      */   
/*      */   public boolean skipRenderWorld;
/*      */   
/*      */   public MovingObjectPosition objectMouseOver;
/*      */   
/*      */   public GameSettings gameSettings;
/*      */   
/*      */   public MouseHelper mouseHelper;
/*      */   
/*      */   public final File mcDataDir;
/*      */   
/*      */   private final File fileAssets;
/*      */   
/*      */   private final String launchedVersion;
/*      */   
/*      */   private final Proxy proxy;
/*      */   
/*      */   private ISaveFormat saveLoader;
/*      */   
/*      */   private static int debugFPS;
/*      */   
/*      */   private int rightClickDelayTimer;
/*      */   
/*      */   private String serverName;
/*      */   
/*      */   private int serverPort;
/*      */   public boolean inGameHasFocus;
/*  295 */   long systemTime = getSystemTime();
/*      */   
/*      */   private int joinPlayerCounter;
/*      */   
/*      */   private final boolean jvm64bit;
/*      */   
/*      */   private final boolean isDemo;
/*      */   
/*      */   private NetworkManager myNetworkManager;
/*      */   private boolean integratedServerIsRunning;
/*  305 */   public final Profiler mcProfiler = new Profiler();
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  310 */   private long debugCrashKeyPressTime = -1L;
/*      */   private IReloadableResourceManager mcResourceManager;
/*  312 */   private final IMetadataSerializer metadataSerializer_ = new IMetadataSerializer();
/*  313 */   private final List defaultResourcePacks = Lists.newArrayList();
/*      */   private final DefaultResourcePack mcDefaultResourcePack;
/*      */   private ResourcePackRepository mcResourcePackRepository;
/*      */   private LanguageManager mcLanguageManager;
/*      */   private IStream stream;
/*      */   private Framebuffer framebufferMc;
/*      */   private TextureMap textureMapBlocks;
/*      */   private SoundHandler mcSoundHandler;
/*      */   private MusicTicker mcMusicTicker;
/*      */   private ResourceLocation mojangLogo;
/*      */   private final MinecraftSessionService sessionService;
/*      */   private SkinManager skinManager;
/*  325 */   private final Queue scheduledTasks = Queues.newArrayDeque();
/*  326 */   private long field_175615_aJ = 0L;
/*  327 */   private final Thread mcThread = Thread.currentThread();
/*      */ 
/*      */   
/*      */   private ModelManager modelManager;
/*      */ 
/*      */   
/*      */   private BlockRendererDispatcher field_175618_aM;
/*      */   
/*      */   volatile boolean running = true;
/*      */   
/*  337 */   public String debug = "";
/*      */   
/*      */   public boolean field_175613_B = false;
/*      */   
/*      */   public boolean field_175614_C = false;
/*      */   public boolean field_175611_D = false;
/*      */   public boolean field_175612_E = true;
/*  344 */   long debugUpdateTime = getSystemTime();
/*      */   
/*      */   int fpsCounter;
/*      */   
/*  348 */   long prevFrameTime = -1L;
/*      */ 
/*      */   
/*  351 */   private String debugProfilerName = "root";
/*      */   
/*      */   private static final String __OBFID = "CL_00000631";
/*      */   
/*      */   public Minecraft(GameConfiguration p_i45547_1_) {
/*  356 */     theMinecraft = this;
/*  357 */     this.mcDataDir = p_i45547_1_.field_178744_c.field_178760_a;
/*  358 */     this.fileAssets = p_i45547_1_.field_178744_c.field_178759_c;
/*  359 */     this.fileResourcepacks = p_i45547_1_.field_178744_c.field_178758_b;
/*  360 */     this.launchedVersion = p_i45547_1_.field_178741_d.field_178755_b;
/*  361 */     this.twitchDetails = p_i45547_1_.field_178745_a.field_178750_b;
/*  362 */     this.mcDefaultResourcePack = new DefaultResourcePack((new ResourceIndex(p_i45547_1_.field_178744_c.field_178759_c, p_i45547_1_.field_178744_c.field_178757_d)).func_152782_a());
/*  363 */     this.proxy = (p_i45547_1_.field_178745_a.field_178751_c == null) ? Proxy.NO_PROXY : p_i45547_1_.field_178745_a.field_178751_c;
/*  364 */     this.sessionService = (new YggdrasilAuthenticationService(p_i45547_1_.field_178745_a.field_178751_c, UUID.randomUUID().toString())).createMinecraftSessionService();
/*  365 */     this.session = p_i45547_1_.field_178745_a.field_178752_a;
/*  366 */     logger.info("Setting user: " + this.session.getUsername());
/*  367 */     logger.info("(Session ID is " + this.session.getSessionID() + ")");
/*  368 */     this.isDemo = p_i45547_1_.field_178741_d.field_178756_a;
/*  369 */     this.displayWidth = (p_i45547_1_.field_178743_b.field_178764_a > 0) ? p_i45547_1_.field_178743_b.field_178764_a : 1;
/*  370 */     this.displayHeight = (p_i45547_1_.field_178743_b.field_178762_b > 0) ? p_i45547_1_.field_178743_b.field_178762_b : 1;
/*  371 */     this.tempDisplayWidth = p_i45547_1_.field_178743_b.field_178764_a;
/*  372 */     this.tempDisplayHeight = p_i45547_1_.field_178743_b.field_178762_b;
/*  373 */     this.fullscreen = p_i45547_1_.field_178743_b.field_178763_c;
/*  374 */     this.jvm64bit = isJvm64bit();
/*  375 */     this.theIntegratedServer = new IntegratedServer(this);
/*      */     
/*  377 */     if (p_i45547_1_.field_178742_e.field_178754_a != null) {
/*      */       
/*  379 */       this.serverName = p_i45547_1_.field_178742_e.field_178754_a;
/*  380 */       this.serverPort = p_i45547_1_.field_178742_e.field_178753_b;
/*      */     } 
/*      */     
/*  383 */     ImageIO.setUseCache(false);
/*  384 */     Bootstrap.register();
/*      */   }
/*      */ 
/*      */   
/*      */   public void run() {
/*  389 */     this.running = true;
/*      */ 
/*      */ 
/*      */     
/*      */     try {
/*  394 */       startGame();
/*      */     }
/*  396 */     catch (Throwable var11) {
/*      */       
/*  398 */       CrashReport var2 = CrashReport.makeCrashReport(var11, "Initializing game");
/*  399 */       var2.makeCategory("Initialization");
/*  400 */       displayCrashReport(addGraphicsAndWorldToCrashReport(var2));
/*      */ 
/*      */       
/*      */       return;
/*      */     } 
/*      */ 
/*      */     
/*      */     while (true) {
/*  408 */       if (!this.running)
/*      */       
/*      */       { 
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
/*  450 */         shutdownMinecraftApplet(); break; }  try { if (!this.hasCrashed || this.crashReporter == null) { try { runGameLoop(); } catch (OutOfMemoryError var10) { freeMemory(); displayGuiScreen((GuiScreen)new GuiMemoryErrorScreen()); System.gc(); }  continue; }  displayCrashReport(this.crashReporter); return; } catch (MinecraftError minecraftError) { break; } catch (ReportedException var13) { addGraphicsAndWorldToCrashReport(var13.getCrashReport()); freeMemory(); logger.fatal("Reported exception thrown!", (Throwable)var13); displayCrashReport(var13.getCrashReport()); break; } catch (Throwable var14) { CrashReport var2 = addGraphicsAndWorldToCrashReport(new CrashReport("Unexpected error", var14)); freeMemory(); logger.fatal("Unreported exception thrown!", var14); displayCrashReport(var2); break; } finally { shutdownMinecraftApplet(); }
/*      */     
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void startGame() throws LWJGLException {
/*  462 */     this.gameSettings = new GameSettings(this, this.mcDataDir);
/*  463 */     this.defaultResourcePacks.add(this.mcDefaultResourcePack);
/*  464 */     startTimerHackThread();
/*      */     
/*  466 */     if (this.gameSettings.overrideHeight > 0 && this.gameSettings.overrideWidth > 0) {
/*      */       
/*  468 */       this.displayWidth = this.gameSettings.overrideWidth;
/*  469 */       this.displayHeight = this.gameSettings.overrideHeight;
/*      */     } 
/*      */     
/*  472 */     logger.info("LWJGL Version: " + Sys.getVersion());
/*  473 */     func_175594_ao();
/*  474 */     func_175605_an();
/*  475 */     func_175609_am();
/*  476 */     OpenGlHelper.initializeTextures();
/*  477 */     this.framebufferMc = new Framebuffer(this.displayWidth, this.displayHeight, true);
/*  478 */     this.framebufferMc.setFramebufferColor(0.0F, 0.0F, 0.0F, 0.0F);
/*  479 */     func_175608_ak();
/*  480 */     this.mcResourcePackRepository = new ResourcePackRepository(this.fileResourcepacks, new File(this.mcDataDir, "server-resource-packs"), (IResourcePack)this.mcDefaultResourcePack, this.metadataSerializer_, this.gameSettings);
/*  481 */     this.mcResourceManager = (IReloadableResourceManager)new SimpleReloadableResourceManager(this.metadataSerializer_);
/*  482 */     this.mcLanguageManager = new LanguageManager(this.metadataSerializer_, this.gameSettings.language);
/*  483 */     this.mcResourceManager.registerReloadListener((IResourceManagerReloadListener)this.mcLanguageManager);
/*  484 */     refreshResources();
/*  485 */     this.renderEngine = new TextureManager((IResourceManager)this.mcResourceManager);
/*  486 */     this.mcResourceManager.registerReloadListener((IResourceManagerReloadListener)this.renderEngine);
/*  487 */     func_180510_a(this.renderEngine);
/*  488 */     func_175595_al();
/*  489 */     this.skinManager = new SkinManager(this.renderEngine, new File(this.fileAssets, "skins"), this.sessionService);
/*  490 */     this.saveLoader = (ISaveFormat)new AnvilSaveConverter(new File(this.mcDataDir, "saves"));
/*  491 */     this.mcSoundHandler = new SoundHandler((IResourceManager)this.mcResourceManager, this.gameSettings);
/*  492 */     this.mcResourceManager.registerReloadListener((IResourceManagerReloadListener)this.mcSoundHandler);
/*  493 */     this.mcMusicTicker = new MusicTicker(this);
/*  494 */     this.fontRendererObj = new FontRenderer(this.gameSettings, new ResourceLocation("textures/font/ascii.png"), this.renderEngine, false);
/*      */     
/*  496 */     if (this.gameSettings.language != null) {
/*      */       
/*  498 */       this.fontRendererObj.setUnicodeFlag(isUnicode());
/*  499 */       this.fontRendererObj.setBidiFlag(this.mcLanguageManager.isCurrentLanguageBidirectional());
/*      */     } 
/*      */     
/*  502 */     this.standardGalacticFontRenderer = new FontRenderer(this.gameSettings, new ResourceLocation("textures/font/ascii_sga.png"), this.renderEngine, false);
/*  503 */     this.mcResourceManager.registerReloadListener((IResourceManagerReloadListener)this.fontRendererObj);
/*  504 */     this.mcResourceManager.registerReloadListener((IResourceManagerReloadListener)this.standardGalacticFontRenderer);
/*  505 */     this.mcResourceManager.registerReloadListener((IResourceManagerReloadListener)new GrassColorReloadListener());
/*  506 */     this.mcResourceManager.registerReloadListener((IResourceManagerReloadListener)new FoliageColorReloadListener());
/*  507 */     AchievementList.openInventory.setStatStringFormatter(new IStatStringFormat()
/*      */         {
/*      */           private static final String __OBFID = "CL_00000632";
/*      */ 
/*      */           
/*      */           public String formatString(String p_74535_1_) {
/*      */             try {
/*  514 */               return String.format(p_74535_1_, new Object[] { GameSettings.getKeyDisplayString(this.this$0.gameSettings.keyBindInventory.getKeyCode()) });
/*      */             }
/*  516 */             catch (Exception var3) {
/*      */               
/*  518 */               return "Error: " + var3.getLocalizedMessage();
/*      */             } 
/*      */           }
/*      */         });
/*  522 */     this.mouseHelper = new MouseHelper();
/*  523 */     checkGLError("Pre startup");
/*  524 */     GlStateManager.func_179098_w();
/*  525 */     GlStateManager.shadeModel(7425);
/*  526 */     GlStateManager.clearDepth(1.0D);
/*  527 */     GlStateManager.enableDepth();
/*  528 */     GlStateManager.depthFunc(515);
/*  529 */     GlStateManager.enableAlpha();
/*  530 */     GlStateManager.alphaFunc(516, 0.1F);
/*  531 */     GlStateManager.cullFace(1029);
/*  532 */     GlStateManager.matrixMode(5889);
/*  533 */     GlStateManager.loadIdentity();
/*  534 */     GlStateManager.matrixMode(5888);
/*  535 */     checkGLError("Startup");
/*  536 */     this.textureMapBlocks = new TextureMap("textures");
/*  537 */     this.textureMapBlocks.setMipmapLevels(this.gameSettings.mipmapLevels);
/*  538 */     this.renderEngine.loadTickableTexture(TextureMap.locationBlocksTexture, (ITickableTextureObject)this.textureMapBlocks);
/*  539 */     this.renderEngine.bindTexture(TextureMap.locationBlocksTexture);
/*  540 */     this.textureMapBlocks.func_174937_a(false, (this.gameSettings.mipmapLevels > 0));
/*  541 */     this.modelManager = new ModelManager(this.textureMapBlocks);
/*  542 */     this.mcResourceManager.registerReloadListener((IResourceManagerReloadListener)this.modelManager);
/*  543 */     this.renderItem = new RenderItem(this.renderEngine, this.modelManager);
/*  544 */     this.renderManager = new RenderManager(this.renderEngine, this.renderItem);
/*  545 */     this.itemRenderer = new ItemRenderer(this);
/*  546 */     this.mcResourceManager.registerReloadListener((IResourceManagerReloadListener)this.renderItem);
/*  547 */     this.entityRenderer = new EntityRenderer(this, (IResourceManager)this.mcResourceManager);
/*  548 */     this.mcResourceManager.registerReloadListener((IResourceManagerReloadListener)this.entityRenderer);
/*  549 */     this.field_175618_aM = new BlockRendererDispatcher(this.modelManager.getBlockModelShapes(), this.gameSettings);
/*  550 */     this.mcResourceManager.registerReloadListener((IResourceManagerReloadListener)this.field_175618_aM);
/*  551 */     this.renderGlobal = new RenderGlobal(this);
/*  552 */     this.mcResourceManager.registerReloadListener((IResourceManagerReloadListener)this.renderGlobal);
/*  553 */     this.guiAchievement = new GuiAchievement(this);
/*  554 */     GlStateManager.viewport(0, 0, this.displayWidth, this.displayHeight);
/*  555 */     this.effectRenderer = new EffectRenderer((World)this.theWorld, this.renderEngine);
/*  556 */     checkGLError("Post startup");
/*  557 */     this.ingameGUI = new GuiIngame(this);
/*      */     
/*  559 */     Client.startup();
/*      */     
/*  561 */     if (this.serverName != null) {
/*      */       
/*  563 */       displayGuiScreen((GuiScreen)new GuiConnecting((GuiScreen)new MainMenu(), this, this.serverName, this.serverPort));
/*      */     }
/*      */     else {
/*      */       
/*  567 */       displayGuiScreen((GuiScreen)new MainMenu());
/*      */     } 
/*      */     
/*  570 */     this.renderEngine.deleteTexture(this.mojangLogo);
/*  571 */     this.mojangLogo = null;
/*  572 */     this.loadingScreen = new LoadingScreenRenderer(this);
/*      */     
/*  574 */     if (this.gameSettings.fullScreen && !this.fullscreen)
/*      */     {
/*  576 */       toggleFullscreen();
/*      */     }
/*      */ 
/*      */     
/*      */     try {
/*  581 */       Display.setVSyncEnabled(this.gameSettings.enableVsync);
/*      */     }
/*  583 */     catch (OpenGLException var2) {
/*      */       
/*  585 */       this.gameSettings.enableVsync = false;
/*  586 */       this.gameSettings.saveOptions();
/*      */     } 
/*      */     
/*  589 */     this.renderGlobal.func_174966_b();
/*      */   }
/*      */ 
/*      */   
/*      */   private void func_175608_ak() {
/*  594 */     this.metadataSerializer_.registerMetadataSectionType((IMetadataSectionSerializer)new TextureMetadataSectionSerializer(), TextureMetadataSection.class);
/*  595 */     this.metadataSerializer_.registerMetadataSectionType((IMetadataSectionSerializer)new FontMetadataSectionSerializer(), FontMetadataSection.class);
/*  596 */     this.metadataSerializer_.registerMetadataSectionType((IMetadataSectionSerializer)new AnimationMetadataSectionSerializer(), AnimationMetadataSection.class);
/*  597 */     this.metadataSerializer_.registerMetadataSectionType((IMetadataSectionSerializer)new PackMetadataSectionSerializer(), PackMetadataSection.class);
/*  598 */     this.metadataSerializer_.registerMetadataSectionType((IMetadataSectionSerializer)new LanguageMetadataSectionSerializer(), LanguageMetadataSection.class);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private void func_175595_al() {
/*      */     try {
/*  605 */       this.stream = (IStream)new TwitchStream(this, (Property)Iterables.getFirst(this.twitchDetails.get("twitch_access_token"), null));
/*      */     }
/*  607 */     catch (Throwable var2) {
/*      */       
/*  609 */       this.stream = (IStream)new NullStream(var2);
/*  610 */       logger.error("Couldn't initialize twitch stream");
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   private void func_175609_am() throws LWJGLException {
/*  616 */     Display.setResizable(true);
/*  617 */     Display.setTitle("Loading " + Client.name);
/*      */ 
/*      */     
/*      */     try {
/*  621 */       Display.create((new PixelFormat()).withDepthBits(24));
/*      */     }
/*  623 */     catch (LWJGLException var4) {
/*      */       
/*  625 */       logger.error("Couldn't set pixel format", (Throwable)var4);
/*      */ 
/*      */       
/*      */       try {
/*  629 */         Thread.sleep(1000L);
/*      */       }
/*  631 */       catch (InterruptedException interruptedException) {}
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  636 */       if (this.fullscreen)
/*      */       {
/*  638 */         updateDisplayMode();
/*      */       }
/*      */       
/*  641 */       Display.create();
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   private void func_175605_an() throws LWJGLException {
/*  647 */     if (this.fullscreen) {
/*      */       
/*  649 */       Display.setFullscreen(true);
/*  650 */       DisplayMode var1 = Display.getDisplayMode();
/*  651 */       this.displayWidth = Math.max(1, var1.getWidth());
/*  652 */       this.displayHeight = Math.max(1, var1.getHeight());
/*      */     }
/*      */     else {
/*      */       
/*  656 */       Display.setDisplayMode(new DisplayMode(this.displayWidth, this.displayHeight));
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   private void func_175594_ao() {
/*  662 */     Util.EnumOS var1 = Util.getOSType();
/*      */     
/*  664 */     if (var1 != Util.EnumOS.OSX) {
/*      */       
/*  666 */       InputStream var2 = null;
/*  667 */       InputStream var3 = null;
/*      */ 
/*      */       
/*      */       try {
/*  671 */         var2 = this.mcDefaultResourcePack.func_152780_c(new ResourceLocation("icons/icon_16x16.png"));
/*  672 */         var3 = this.mcDefaultResourcePack.func_152780_c(new ResourceLocation("icons/icon_32x32.png"));
/*      */         
/*  674 */         if (var2 != null && var3 != null)
/*      */         {
/*  676 */           Display.setIcon(new ByteBuffer[] { readImageToBuffer(var2), readImageToBuffer(var3) });
/*      */         }
/*      */       }
/*  679 */       catch (IOException var8) {
/*      */         
/*  681 */         logger.error("Couldn't set icon", var8);
/*      */       }
/*      */       finally {
/*      */         
/*  685 */         IOUtils.closeQuietly(var2);
/*  686 */         IOUtils.closeQuietly(var3);
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   private static boolean isJvm64bit() {
/*  693 */     String[] var0 = { "sun.arch.data.model", "com.ibm.vm.bitmode", "os.arch" };
/*  694 */     String[] var1 = var0;
/*  695 */     int var2 = var0.length;
/*      */     
/*  697 */     for (int var3 = 0; var3 < var2; var3++) {
/*      */       
/*  699 */       String var4 = var1[var3];
/*  700 */       String var5 = System.getProperty(var4);
/*      */       
/*  702 */       if (var5 != null && var5.contains("64"))
/*      */       {
/*  704 */         return true;
/*      */       }
/*      */     } 
/*      */     
/*  708 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   public Framebuffer getFramebuffer() {
/*  713 */     return this.framebufferMc;
/*      */   }
/*      */ 
/*      */   
/*      */   public String func_175600_c() {
/*  718 */     return this.launchedVersion;
/*      */   }
/*      */ 
/*      */   
/*      */   private void startTimerHackThread() {
/*  723 */     Thread var1 = new Thread("Timer hack thread")
/*      */       {
/*      */         private static final String __OBFID = "CL_00000639";
/*      */         
/*      */         public void run() {
/*  728 */           while (Minecraft.this.running) {
/*      */ 
/*      */             
/*      */             try {
/*  732 */               Thread.sleep(2147483647L);
/*      */             }
/*  734 */             catch (InterruptedException interruptedException) {}
/*      */           } 
/*      */         }
/*      */       };
/*      */ 
/*      */ 
/*      */     
/*  741 */     var1.setDaemon(true);
/*  742 */     var1.start();
/*      */   }
/*      */ 
/*      */   
/*      */   public void crashed(CrashReport crash) {
/*  747 */     this.hasCrashed = true;
/*  748 */     this.crashReporter = crash;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void displayCrashReport(CrashReport crashReportIn) {
/*  756 */     File var2 = new File((getMinecraft()).mcDataDir, "crash-reports");
/*  757 */     File var3 = new File(var2, "crash-" + (new SimpleDateFormat("yyyy-MM-dd_HH.mm.ss")).format(new Date()) + "-client.txt");
/*  758 */     Bootstrap.func_179870_a(crashReportIn.getCompleteReport());
/*      */     
/*  760 */     if (crashReportIn.getFile() != null) {
/*      */       
/*  762 */       Bootstrap.func_179870_a("#@!@# Game crashed! Crash report saved to: #@!@# " + crashReportIn.getFile());
/*  763 */       System.exit(-1);
/*      */     }
/*  765 */     else if (crashReportIn.saveToFile(var3)) {
/*      */       
/*  767 */       Bootstrap.func_179870_a("#@!@# Game crashed! Crash report saved to: #@!@# " + var3.getAbsolutePath());
/*  768 */       System.exit(-1);
/*      */     }
/*      */     else {
/*      */       
/*  772 */       Bootstrap.func_179870_a("#@?@# Game crashed! Crash report could not be saved. #@?@#");
/*  773 */       System.exit(-2);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isUnicode() {
/*  779 */     return !(!this.mcLanguageManager.isCurrentLocaleUnicode() && !this.gameSettings.forceUnicodeFont);
/*      */   }
/*      */ 
/*      */   
/*      */   public void refreshResources() {
/*  784 */     ArrayList<IResourcePack> var1 = Lists.newArrayList(this.defaultResourcePacks);
/*  785 */     Iterator<ResourcePackRepository.Entry> var2 = this.mcResourcePackRepository.getRepositoryEntries().iterator();
/*      */     
/*  787 */     while (var2.hasNext()) {
/*      */       
/*  789 */       ResourcePackRepository.Entry var3 = var2.next();
/*  790 */       var1.add(var3.getResourcePack());
/*      */     } 
/*      */     
/*  793 */     if (this.mcResourcePackRepository.getResourcePackInstance() != null)
/*      */     {
/*  795 */       var1.add(this.mcResourcePackRepository.getResourcePackInstance());
/*      */     }
/*      */ 
/*      */     
/*      */     try {
/*  800 */       this.mcResourceManager.reloadResources(var1);
/*      */     }
/*  802 */     catch (RuntimeException var4) {
/*      */       
/*  804 */       logger.info("Caught error stitching, removing all assigned resourcepacks", var4);
/*  805 */       var1.clear();
/*  806 */       var1.addAll(this.defaultResourcePacks);
/*  807 */       this.mcResourcePackRepository.func_148527_a(Collections.emptyList());
/*  808 */       this.mcResourceManager.reloadResources(var1);
/*  809 */       this.gameSettings.resourcePacks.clear();
/*  810 */       this.gameSettings.saveOptions();
/*      */     } 
/*      */     
/*  813 */     this.mcLanguageManager.parseLanguageMetadata(var1);
/*      */     
/*  815 */     if (this.renderGlobal != null)
/*      */     {
/*  817 */       this.renderGlobal.loadRenderers();
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   private ByteBuffer readImageToBuffer(InputStream imageStream) throws IOException {
/*  823 */     BufferedImage var2 = ImageIO.read(imageStream);
/*  824 */     int[] var3 = var2.getRGB(0, 0, var2.getWidth(), var2.getHeight(), null, 0, var2.getWidth());
/*  825 */     ByteBuffer var4 = ByteBuffer.allocate(4 * var3.length);
/*  826 */     int[] var5 = var3;
/*  827 */     int var6 = var3.length;
/*      */     
/*  829 */     for (int var7 = 0; var7 < var6; var7++) {
/*      */       
/*  831 */       int var8 = var5[var7];
/*  832 */       var4.putInt(var8 << 8 | var8 >> 24 & 0xFF);
/*      */     } 
/*      */     
/*  835 */     var4.flip();
/*  836 */     return var4;
/*      */   }
/*      */ 
/*      */   
/*      */   private void updateDisplayMode() throws LWJGLException {
/*  841 */     HashSet<? super DisplayMode> var1 = Sets.newHashSet();
/*  842 */     Collections.addAll(var1, Display.getAvailableDisplayModes());
/*  843 */     DisplayMode var2 = Display.getDesktopDisplayMode();
/*      */     
/*  845 */     if (!var1.contains(var2) && Util.getOSType() == Util.EnumOS.OSX) {
/*      */       
/*  847 */       Iterator<DisplayMode> var3 = macDisplayModes.iterator();
/*      */       
/*  849 */       while (var3.hasNext()) {
/*      */         
/*  851 */         DisplayMode var4 = var3.next();
/*  852 */         boolean var5 = true;
/*  853 */         Iterator<? super DisplayMode> var6 = var1.iterator();
/*      */ 
/*      */         
/*  856 */         while (var6.hasNext()) {
/*      */           
/*  858 */           DisplayMode var7 = var6.next();
/*      */           
/*  860 */           if (var7.getBitsPerPixel() == 32 && var7.getWidth() == var4.getWidth() && var7.getHeight() == var4.getHeight()) {
/*      */             
/*  862 */             var5 = false;
/*      */             
/*      */             break;
/*      */           } 
/*      */         } 
/*  867 */         if (!var5) {
/*      */           
/*  869 */           var6 = var1.iterator();
/*      */           
/*  871 */           while (var6.hasNext()) {
/*      */             
/*  873 */             DisplayMode var7 = var6.next();
/*      */             
/*  875 */             if (var7.getBitsPerPixel() == 32 && var7.getWidth() == var4.getWidth() / 2 && var7.getHeight() == var4.getHeight() / 2) {
/*      */               
/*  877 */               var2 = var7;
/*      */               
/*      */               break;
/*      */             } 
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     } 
/*  885 */     Display.setDisplayMode(var2);
/*  886 */     this.displayWidth = var2.getWidth();
/*  887 */     this.displayHeight = var2.getHeight();
/*      */   }
/*      */ 
/*      */   
/*      */   private void func_180510_a(TextureManager p_180510_1_) {
/*  892 */     ScaledResolution var2 = new ScaledResolution(this, this.displayWidth, this.displayHeight);
/*  893 */     int var3 = var2.getScaleFactor();
/*  894 */     Framebuffer var4 = new Framebuffer(var2.getScaledWidth() * var3, var2.getScaledHeight() * var3, true);
/*  895 */     var4.bindFramebuffer(false);
/*  896 */     GlStateManager.matrixMode(5889);
/*  897 */     GlStateManager.loadIdentity();
/*  898 */     GlStateManager.ortho(0.0D, var2.getScaledWidth(), var2.getScaledHeight(), 0.0D, 1000.0D, 3000.0D);
/*  899 */     GlStateManager.matrixMode(5888);
/*  900 */     GlStateManager.loadIdentity();
/*  901 */     GlStateManager.translate(0.0F, 0.0F, -2000.0F);
/*  902 */     GlStateManager.disableLighting();
/*  903 */     GlStateManager.disableFog();
/*  904 */     GlStateManager.disableDepth();
/*  905 */     GlStateManager.func_179098_w();
/*  906 */     InputStream var5 = null;
/*      */ 
/*      */     
/*      */     try {
/*  910 */       var5 = this.mcDefaultResourcePack.getInputStream(locationMojangPng);
/*  911 */       this.mojangLogo = p_180510_1_.getDynamicTextureLocation("logo", new DynamicTexture(ImageIO.read(var5)));
/*  912 */       p_180510_1_.bindTexture(this.mojangLogo);
/*      */     }
/*  914 */     catch (IOException var12) {
/*      */       
/*  916 */       logger.error("Unable to load logo: " + locationMojangPng, var12);
/*      */     }
/*      */     finally {
/*      */       
/*  920 */       IOUtils.closeQuietly(var5);
/*      */     } 
/*      */     
/*  923 */     Tessellator var6 = Tessellator.getInstance();
/*  924 */     WorldRenderer var7 = var6.getWorldRenderer();
/*  925 */     var7.startDrawingQuads();
/*  926 */     var7.func_178991_c(16777215);
/*  927 */     var7.addVertexWithUV(0.0D, this.displayHeight, 0.0D, 0.0D, 0.0D);
/*  928 */     var7.addVertexWithUV(this.displayWidth, this.displayHeight, 0.0D, 0.0D, 0.0D);
/*  929 */     var7.addVertexWithUV(this.displayWidth, 0.0D, 0.0D, 0.0D, 0.0D);
/*  930 */     var7.addVertexWithUV(0.0D, 0.0D, 0.0D, 0.0D, 0.0D);
/*  931 */     var6.draw();
/*  932 */     GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
/*  933 */     var7.func_178991_c(16777215);
/*  934 */     short var8 = 256;
/*  935 */     short var9 = 256;
/*  936 */     scaledTessellator((var2.getScaledWidth() - var8) / 2, (var2.getScaledHeight() - var9) / 2, 0, 0, var8, var9);
/*  937 */     GlStateManager.disableLighting();
/*  938 */     GlStateManager.disableFog();
/*  939 */     var4.unbindFramebuffer();
/*  940 */     var4.framebufferRender(var2.getScaledWidth() * var3, var2.getScaledHeight() * var3);
/*  941 */     GlStateManager.enableAlpha();
/*  942 */     GlStateManager.alphaFunc(516, 0.1F);
/*  943 */     func_175601_h();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void scaledTessellator(int width, int height, int width2, int height2, int stdTextureWidth, int stdTextureHeight) {
/*  951 */     float var7 = 0.00390625F;
/*  952 */     float var8 = 0.00390625F;
/*  953 */     WorldRenderer var9 = Tessellator.getInstance().getWorldRenderer();
/*  954 */     var9.startDrawingQuads();
/*  955 */     var9.addVertexWithUV((width + 0), (height + stdTextureHeight), 0.0D, ((width2 + 0) * var7), ((height2 + stdTextureHeight) * var8));
/*  956 */     var9.addVertexWithUV((width + stdTextureWidth), (height + stdTextureHeight), 0.0D, ((width2 + stdTextureWidth) * var7), ((height2 + stdTextureHeight) * var8));
/*  957 */     var9.addVertexWithUV((width + stdTextureWidth), (height + 0), 0.0D, ((width2 + stdTextureWidth) * var7), ((height2 + 0) * var8));
/*  958 */     var9.addVertexWithUV((width + 0), (height + 0), 0.0D, ((width2 + 0) * var7), ((height2 + 0) * var8));
/*  959 */     Tessellator.getInstance().draw();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ISaveFormat getSaveLoader() {
/*  967 */     return this.saveLoader;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void displayGuiScreen(GuiScreen guiScreenIn) {
/*      */     MainMenu mainMenu;
/*      */     GuiGameOver guiGameOver;
/*  975 */     if (this.currentScreen != null)
/*      */     {
/*  977 */       this.currentScreen.onGuiClosed();
/*      */     }
/*      */     
/*  980 */     if (guiScreenIn == null && this.theWorld == null) {
/*      */       
/*  982 */       mainMenu = new MainMenu();
/*      */     }
/*  984 */     else if (mainMenu == null && this.thePlayer.getHealth() <= 0.0F) {
/*      */       
/*  986 */       guiGameOver = new GuiGameOver();
/*      */     } 
/*      */     
/*  989 */     if (guiGameOver instanceof MainMenu) {
/*      */       
/*  991 */       this.gameSettings.showDebugInfo = false;
/*  992 */       this.ingameGUI.getChatGUI().clearChatMessages();
/*      */     } 
/*      */     
/*  995 */     this.currentScreen = (GuiScreen)guiGameOver;
/*      */     
/*  997 */     if (guiGameOver != null) {
/*      */       
/*  999 */       setIngameNotInFocus();
/* 1000 */       ScaledResolution var2 = new ScaledResolution(this, this.displayWidth, this.displayHeight);
/* 1001 */       int var3 = var2.getScaledWidth();
/* 1002 */       int var4 = var2.getScaledHeight();
/* 1003 */       guiGameOver.setWorldAndResolution(this, var3, var4);
/* 1004 */       this.skipRenderWorld = false;
/*      */     }
/*      */     else {
/*      */       
/* 1008 */       this.mcSoundHandler.resumeSounds();
/* 1009 */       setIngameFocus();
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void checkGLError(String message) {
/* 1018 */     if (this.field_175619_R) {
/*      */       
/* 1020 */       int var2 = GL11.glGetError();
/*      */       
/* 1022 */       if (var2 != 0) {
/*      */         
/* 1024 */         String var3 = GLU.gluErrorString(var2);
/* 1025 */         logger.error("########## GL ERROR ##########");
/* 1026 */         logger.error("@ " + message);
/* 1027 */         logger.error(String.valueOf(var2) + ": " + var3);
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void shutdownMinecraftApplet() {
/*      */     try {
/* 1040 */       this.stream.shutdownStream();
/* 1041 */       logger.info("Stopping!");
/*      */ 
/*      */       
/*      */       try {
/* 1045 */         loadWorld(null);
/*      */       }
/* 1047 */       catch (Throwable throwable) {}
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1052 */       this.mcSoundHandler.unloadSounds();
/*      */     }
/*      */     finally {
/*      */       
/* 1056 */       Display.destroy();
/*      */       
/* 1058 */       if (!this.hasCrashed)
/*      */       {
/* 1060 */         System.exit(0);
/*      */       }
/*      */     } 
/*      */     
/* 1064 */     System.gc();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void runGameLoop() throws IOException {
/* 1072 */     this.mcProfiler.startSection("root");
/*      */     
/* 1074 */     if (Display.isCreated() && Display.isCloseRequested())
/*      */     {
/* 1076 */       shutdown();
/*      */     }
/*      */     
/* 1079 */     if (this.isGamePaused && this.theWorld != null) {
/*      */       
/* 1081 */       float var1 = this.timer.renderPartialTicks;
/* 1082 */       this.timer.updateTimer();
/* 1083 */       this.timer.renderPartialTicks = var1;
/*      */     }
/*      */     else {
/*      */       
/* 1087 */       this.timer.updateTimer();
/*      */     } 
/*      */     
/* 1090 */     this.mcProfiler.startSection("scheduledExecutables");
/* 1091 */     Queue var6 = this.scheduledTasks;
/*      */     
/* 1093 */     synchronized (this.scheduledTasks) {
/*      */       
/* 1095 */       while (!this.scheduledTasks.isEmpty())
/*      */       {
/* 1097 */         ((FutureTask)this.scheduledTasks.poll()).run();
/*      */       }
/*      */     } 
/*      */     
/* 1101 */     this.mcProfiler.endSection();
/* 1102 */     long var7 = System.nanoTime();
/* 1103 */     this.mcProfiler.startSection("tick");
/*      */     
/* 1105 */     for (int var3 = 0; var3 < this.timer.elapsedTicks; var3++)
/*      */     {
/* 1107 */       runTick();
/*      */     }
/*      */     
/* 1110 */     this.mcProfiler.endStartSection("preRenderErrors");
/* 1111 */     long var8 = System.nanoTime() - var7;
/* 1112 */     checkGLError("Pre render");
/* 1113 */     this.mcProfiler.endStartSection("sound");
/* 1114 */     this.mcSoundHandler.setListener((EntityPlayer)this.thePlayer, this.timer.renderPartialTicks);
/* 1115 */     this.mcProfiler.endSection();
/* 1116 */     this.mcProfiler.startSection("render");
/* 1117 */     GlStateManager.pushMatrix();
/* 1118 */     GlStateManager.clear(16640);
/* 1119 */     this.framebufferMc.bindFramebuffer(true);
/* 1120 */     this.mcProfiler.startSection("display");
/* 1121 */     GlStateManager.func_179098_w();
/*      */     
/* 1123 */     if (this.thePlayer != null && this.thePlayer.isEntityInsideOpaqueBlock())
/*      */     {
/* 1125 */       this.gameSettings.thirdPersonView = 0;
/*      */     }
/*      */     
/* 1128 */     this.mcProfiler.endSection();
/*      */     
/* 1130 */     if (!this.skipRenderWorld) {
/*      */       
/* 1132 */       this.mcProfiler.endStartSection("gameRenderer");
/* 1133 */       this.entityRenderer.updateCameraAndRender(this.timer.renderPartialTicks);
/* 1134 */       this.mcProfiler.endSection();
/*      */     } 
/*      */     
/* 1137 */     this.mcProfiler.endSection();
/*      */     
/* 1139 */     if (this.gameSettings.showDebugInfo && this.gameSettings.showDebugProfilerChart && !this.gameSettings.hideGUI) {
/*      */       
/* 1141 */       if (!this.mcProfiler.profilingEnabled)
/*      */       {
/* 1143 */         this.mcProfiler.clearProfiling();
/*      */       }
/*      */       
/* 1146 */       this.mcProfiler.profilingEnabled = true;
/* 1147 */       displayDebugInfo(var8);
/*      */     }
/*      */     else {
/*      */       
/* 1151 */       this.mcProfiler.profilingEnabled = false;
/* 1152 */       this.prevFrameTime = System.nanoTime();
/*      */     } 
/*      */     
/* 1155 */     this.guiAchievement.updateAchievementWindow();
/* 1156 */     this.framebufferMc.unbindFramebuffer();
/* 1157 */     GlStateManager.popMatrix();
/* 1158 */     GlStateManager.pushMatrix();
/* 1159 */     this.framebufferMc.framebufferRender(this.displayWidth, this.displayHeight);
/* 1160 */     GlStateManager.popMatrix();
/* 1161 */     GlStateManager.pushMatrix();
/* 1162 */     this.entityRenderer.func_152430_c(this.timer.renderPartialTicks);
/* 1163 */     GlStateManager.popMatrix();
/* 1164 */     this.mcProfiler.startSection("root");
/* 1165 */     func_175601_h();
/* 1166 */     Thread.yield();
/* 1167 */     this.mcProfiler.startSection("stream");
/* 1168 */     this.mcProfiler.startSection("update");
/* 1169 */     this.stream.func_152935_j();
/* 1170 */     this.mcProfiler.endStartSection("submit");
/* 1171 */     this.stream.func_152922_k();
/* 1172 */     this.mcProfiler.endSection();
/* 1173 */     this.mcProfiler.endSection();
/* 1174 */     checkGLError("Post render");
/* 1175 */     this.fpsCounter++;
/* 1176 */     this.isGamePaused = (isSingleplayer() && this.currentScreen != null && this.currentScreen.doesGuiPauseGame() && !this.theIntegratedServer.getPublic());
/*      */     
/* 1178 */     while (getSystemTime() >= this.debugUpdateTime + 1000L) {
/*      */       
/* 1180 */       debugFPS = this.fpsCounter;
/* 1181 */       this.debug = String.format("%d fps (%d chunk update%s) T: %s%s%s%s%s", new Object[] { Integer.valueOf(debugFPS), Integer.valueOf(RenderChunk.field_178592_a), (RenderChunk.field_178592_a != 1) ? "s" : "", (this.gameSettings.limitFramerate == GameSettings.Options.FRAMERATE_LIMIT.getValueMax()) ? "inf" : Integer.valueOf(this.gameSettings.limitFramerate), this.gameSettings.enableVsync ? " vsync" : "", this.gameSettings.fancyGraphics ? "" : " fast", this.gameSettings.clouds ? " clouds" : "", OpenGlHelper.func_176075_f() ? " vbo" : "" });
/* 1182 */       RenderChunk.field_178592_a = 0;
/* 1183 */       this.debugUpdateTime += 1000L;
/* 1184 */       this.fpsCounter = 0;
/* 1185 */       this.usageSnooper.addMemoryStatsToSnooper();
/*      */       
/* 1187 */       if (!this.usageSnooper.isSnooperRunning())
/*      */       {
/* 1189 */         this.usageSnooper.startSnooper();
/*      */       }
/*      */     } 
/*      */     
/* 1193 */     if (isFramerateLimitBelowMax()) {
/*      */       
/* 1195 */       this.mcProfiler.startSection("fpslimit_wait");
/* 1196 */       Display.sync(getLimitFramerate());
/* 1197 */       this.mcProfiler.endSection();
/*      */     } 
/*      */     
/* 1200 */     this.mcProfiler.endSection();
/*      */   }
/*      */ 
/*      */   
/*      */   public void func_175601_h() {
/* 1205 */     this.mcProfiler.startSection("display_update");
/* 1206 */     Display.update();
/* 1207 */     this.mcProfiler.endSection();
/* 1208 */     func_175604_i();
/*      */   }
/*      */ 
/*      */   
/*      */   protected void func_175604_i() {
/* 1213 */     if (!this.fullscreen && Display.wasResized()) {
/*      */       
/* 1215 */       int var1 = this.displayWidth;
/* 1216 */       int var2 = this.displayHeight;
/* 1217 */       this.displayWidth = Display.getWidth();
/* 1218 */       this.displayHeight = Display.getHeight();
/*      */       
/* 1220 */       if (this.displayWidth != var1 || this.displayHeight != var2) {
/*      */         
/* 1222 */         if (this.displayWidth <= 0)
/*      */         {
/* 1224 */           this.displayWidth = 1;
/*      */         }
/*      */         
/* 1227 */         if (this.displayHeight <= 0)
/*      */         {
/* 1229 */           this.displayHeight = 1;
/*      */         }
/*      */         
/* 1232 */         resize(this.displayWidth, this.displayHeight);
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public int getLimitFramerate() {
/* 1239 */     return (this.theWorld == null && this.currentScreen != null) ? 30 : this.gameSettings.limitFramerate;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isFramerateLimitBelowMax() {
/* 1244 */     return (getLimitFramerate() < GameSettings.Options.FRAMERATE_LIMIT.getValueMax());
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void freeMemory() {
/*      */     try {
/* 1251 */       memoryReserve = new byte[0];
/* 1252 */       this.renderGlobal.deleteAllDisplayLists();
/*      */     }
/* 1254 */     catch (Throwable throwable) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     try {
/* 1261 */       System.gc();
/* 1262 */       loadWorld(null);
/*      */     }
/* 1264 */     catch (Throwable throwable) {}
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1269 */     System.gc();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void updateDebugProfilerName(int keyCount) {
/* 1277 */     List<Profiler.Result> var2 = this.mcProfiler.getProfilingData(this.debugProfilerName);
/*      */     
/* 1279 */     if (var2 != null && !var2.isEmpty()) {
/*      */       
/* 1281 */       Profiler.Result var3 = var2.remove(0);
/*      */       
/* 1283 */       if (keyCount == 0) {
/*      */         
/* 1285 */         if (var3.field_76331_c.length() > 0)
/*      */         {
/* 1287 */           int var4 = this.debugProfilerName.lastIndexOf(".");
/*      */           
/* 1289 */           if (var4 >= 0)
/*      */           {
/* 1291 */             this.debugProfilerName = this.debugProfilerName.substring(0, var4);
/*      */           }
/*      */         }
/*      */       
/*      */       } else {
/*      */         
/* 1297 */         keyCount--;
/*      */         
/* 1299 */         if (keyCount < var2.size() && !((Profiler.Result)var2.get(keyCount)).field_76331_c.equals("unspecified")) {
/*      */           
/* 1301 */           if (this.debugProfilerName.length() > 0)
/*      */           {
/* 1303 */             this.debugProfilerName = String.valueOf(this.debugProfilerName) + ".";
/*      */           }
/*      */           
/* 1306 */           this.debugProfilerName = String.valueOf(this.debugProfilerName) + ((Profiler.Result)var2.get(keyCount)).field_76331_c;
/*      */         } 
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void displayDebugInfo(long elapsedTicksTime) {
/* 1317 */     if (this.mcProfiler.profilingEnabled) {
/*      */       
/* 1319 */       List<Profiler.Result> var3 = this.mcProfiler.getProfilingData(this.debugProfilerName);
/* 1320 */       Profiler.Result var4 = var3.remove(0);
/* 1321 */       GlStateManager.clear(256);
/* 1322 */       GlStateManager.matrixMode(5889);
/* 1323 */       GlStateManager.enableColorMaterial();
/* 1324 */       GlStateManager.loadIdentity();
/* 1325 */       GlStateManager.ortho(0.0D, this.displayWidth, this.displayHeight, 0.0D, 1000.0D, 3000.0D);
/* 1326 */       GlStateManager.matrixMode(5888);
/* 1327 */       GlStateManager.loadIdentity();
/* 1328 */       GlStateManager.translate(0.0F, 0.0F, -2000.0F);
/* 1329 */       GL11.glLineWidth(1.0F);
/* 1330 */       GlStateManager.func_179090_x();
/* 1331 */       Tessellator var5 = Tessellator.getInstance();
/* 1332 */       WorldRenderer var6 = var5.getWorldRenderer();
/* 1333 */       short var7 = 160;
/* 1334 */       int var8 = this.displayWidth - var7 - 10;
/* 1335 */       int var9 = this.displayHeight - var7 * 2;
/* 1336 */       GlStateManager.enableBlend();
/* 1337 */       var6.startDrawingQuads();
/* 1338 */       var6.func_178974_a(0, 200);
/* 1339 */       var6.addVertex((var8 - var7 * 1.1F), (var9 - var7 * 0.6F - 16.0F), 0.0D);
/* 1340 */       var6.addVertex((var8 - var7 * 1.1F), (var9 + var7 * 2), 0.0D);
/* 1341 */       var6.addVertex((var8 + var7 * 1.1F), (var9 + var7 * 2), 0.0D);
/* 1342 */       var6.addVertex((var8 + var7 * 1.1F), (var9 - var7 * 0.6F - 16.0F), 0.0D);
/* 1343 */       var5.draw();
/* 1344 */       GlStateManager.disableBlend();
/* 1345 */       double var10 = 0.0D;
/*      */ 
/*      */       
/* 1348 */       for (int var12 = 0; var12 < var3.size(); var12++) {
/*      */         
/* 1350 */         Profiler.Result var13 = var3.get(var12);
/* 1351 */         int i = MathHelper.floor_double(var13.field_76332_a / 4.0D) + 1;
/* 1352 */         var6.startDrawing(6);
/* 1353 */         var6.func_178991_c(var13.func_76329_a());
/* 1354 */         var6.addVertex(var8, var9, 0.0D);
/*      */ 
/*      */         
/*      */         int var15;
/*      */ 
/*      */         
/* 1360 */         for (var15 = i; var15 >= 0; var15--) {
/*      */           
/* 1362 */           float var16 = (float)((var10 + var13.field_76332_a * var15 / i) * Math.PI * 2.0D / 100.0D);
/* 1363 */           float var17 = MathHelper.sin(var16) * var7;
/* 1364 */           float var18 = MathHelper.cos(var16) * var7 * 0.5F;
/* 1365 */           var6.addVertex((var8 + var17), (var9 - var18), 0.0D);
/*      */         } 
/*      */         
/* 1368 */         var5.draw();
/* 1369 */         var6.startDrawing(5);
/* 1370 */         var6.func_178991_c((var13.func_76329_a() & 0xFEFEFE) >> 1);
/*      */         
/* 1372 */         for (var15 = i; var15 >= 0; var15--) {
/*      */           
/* 1374 */           float var16 = (float)((var10 + var13.field_76332_a * var15 / i) * Math.PI * 2.0D / 100.0D);
/* 1375 */           float var17 = MathHelper.sin(var16) * var7;
/* 1376 */           float var18 = MathHelper.cos(var16) * var7 * 0.5F;
/* 1377 */           var6.addVertex((var8 + var17), (var9 - var18), 0.0D);
/* 1378 */           var6.addVertex((var8 + var17), (var9 - var18 + 10.0F), 0.0D);
/*      */         } 
/*      */         
/* 1381 */         var5.draw();
/* 1382 */         var10 += var13.field_76332_a;
/*      */       } 
/*      */       
/* 1385 */       DecimalFormat var19 = new DecimalFormat("##0.00");
/* 1386 */       GlStateManager.func_179098_w();
/* 1387 */       String var20 = "";
/*      */       
/* 1389 */       if (!var4.field_76331_c.equals("unspecified"))
/*      */       {
/* 1391 */         var20 = String.valueOf(var20) + "[0] ";
/*      */       }
/*      */       
/* 1394 */       if (var4.field_76331_c.length() == 0) {
/*      */         
/* 1396 */         var20 = String.valueOf(var20) + "ROOT ";
/*      */       }
/*      */       else {
/*      */         
/* 1400 */         var20 = String.valueOf(var20) + var4.field_76331_c + " ";
/*      */       } 
/*      */       
/* 1403 */       int var14 = 16777215;
/* 1404 */       this.fontRendererObj.drawStringWithShadow(var20, (var8 - var7), (var9 - var7 / 2 - 16), var14);
/* 1405 */       this.fontRendererObj.drawStringWithShadow(var20 = String.valueOf(var19.format(var4.field_76330_b)) + "%", (var8 + var7 - this.fontRendererObj.getStringWidth(var20)), (var9 - var7 / 2 - 16), var14);
/*      */       
/* 1407 */       for (int var21 = 0; var21 < var3.size(); var21++) {
/*      */         
/* 1409 */         Profiler.Result var22 = var3.get(var21);
/* 1410 */         String var23 = "";
/*      */         
/* 1412 */         if (var22.field_76331_c.equals("unspecified")) {
/*      */           
/* 1414 */           var23 = String.valueOf(var23) + "[?] ";
/*      */         }
/*      */         else {
/*      */           
/* 1418 */           var23 = String.valueOf(var23) + "[" + (var21 + 1) + "] ";
/*      */         } 
/*      */         
/* 1421 */         var23 = String.valueOf(var23) + var22.field_76331_c;
/* 1422 */         this.fontRendererObj.drawStringWithShadow(var23, (var8 - var7), (var9 + var7 / 2 + var21 * 8 + 20), var22.func_76329_a());
/* 1423 */         this.fontRendererObj.drawStringWithShadow(var23 = String.valueOf(var19.format(var22.field_76332_a)) + "%", (var8 + var7 - 50 - this.fontRendererObj.getStringWidth(var23)), (var9 + var7 / 2 + var21 * 8 + 20), var22.func_76329_a());
/* 1424 */         this.fontRendererObj.drawStringWithShadow(var23 = String.valueOf(var19.format(var22.field_76330_b)) + "%", (var8 + var7 - this.fontRendererObj.getStringWidth(var23)), (var9 + var7 / 2 + var21 * 8 + 20), var22.func_76329_a());
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void shutdown() {
/* 1434 */     Client.shutdown();
/* 1435 */     this.running = false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setIngameFocus() {
/* 1444 */     if (Display.isActive())
/*      */     {
/* 1446 */       if (!this.inGameHasFocus) {
/*      */         
/* 1448 */         this.inGameHasFocus = true;
/* 1449 */         this.mouseHelper.grabMouseCursor();
/* 1450 */         displayGuiScreen(null);
/* 1451 */         this.leftClickCounter = 10000;
/*      */       } 
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setIngameNotInFocus() {
/* 1461 */     if (this.inGameHasFocus) {
/*      */       
/* 1463 */       KeyBinding.unPressAllKeys();
/* 1464 */       this.inGameHasFocus = false;
/* 1465 */       this.mouseHelper.ungrabMouseCursor();
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void displayInGameMenu() {
/* 1474 */     if (this.currentScreen == null) {
/*      */       
/* 1476 */       displayGuiScreen((GuiScreen)new GuiIngameMenu());
/*      */       
/* 1478 */       if (isSingleplayer() && !this.theIntegratedServer.getPublic())
/*      */       {
/* 1480 */         this.mcSoundHandler.pauseSounds();
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   private void sendClickBlockToController(boolean leftClick) {
/* 1487 */     if (!leftClick)
/*      */     {
/* 1489 */       this.leftClickCounter = 0;
/*      */     }
/*      */     
/* 1492 */     if (this.leftClickCounter <= 0 && !this.thePlayer.isUsingItem())
/*      */     {
/* 1494 */       if (leftClick && this.objectMouseOver != null && this.objectMouseOver.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK) {
/*      */         
/* 1496 */         BlockPos var2 = this.objectMouseOver.func_178782_a();
/*      */         
/* 1498 */         if (this.theWorld.getBlockState(var2).getBlock().getMaterial() != Material.air && this.playerController.func_180512_c(var2, this.objectMouseOver.field_178784_b))
/*      */         {
/* 1500 */           this.effectRenderer.func_180532_a(var2, this.objectMouseOver.field_178784_b);
/* 1501 */           this.thePlayer.swingItem();
/*      */         }
/*      */       
/*      */       } else {
/*      */         
/* 1506 */         this.playerController.resetBlockRemoving();
/*      */       } 
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void clickMouse() {
/* 1513 */     if (this.leftClickCounter <= 0) {
/*      */       
/* 1515 */       this.thePlayer.swingItem();
/*      */       
/* 1517 */       if (this.objectMouseOver == null) {
/*      */         
/* 1519 */         logger.error("Null returned as 'hitResult', this shouldn't happen!");
/*      */         
/* 1521 */         if (this.playerController.isNotCreative())
/*      */         {
/* 1523 */           this.leftClickCounter = 10;
/*      */         }
/*      */       } else {
/*      */         BlockPos var1;
/*      */         
/* 1528 */         switch (SwitchEnumMinecartType.field_152390_a[this.objectMouseOver.typeOfHit.ordinal()]) {
/*      */           
/*      */           case 1:
/* 1531 */             this.playerController.attackEntity((EntityPlayer)this.thePlayer, this.objectMouseOver.entityHit);
/*      */             return;
/*      */           
/*      */           case 2:
/* 1535 */             var1 = this.objectMouseOver.func_178782_a();
/*      */             
/* 1537 */             if (this.theWorld.getBlockState(var1).getBlock().getMaterial() != Material.air) {
/*      */               
/* 1539 */               this.playerController.func_180511_b(var1, this.objectMouseOver.field_178784_b);
/*      */               return;
/*      */             } 
/*      */             break;
/*      */         } 
/*      */         
/* 1545 */         if (this.playerController.isNotCreative())
/*      */         {
/*      */ 
/*      */ 
/*      */           
/* 1550 */           this.leftClickCounter = 10;
/*      */         }
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void rightClickMouse() {
/* 1563 */     this.rightClickDelayTimer = 4;
/* 1564 */     boolean var1 = true;
/* 1565 */     ItemStack var2 = this.thePlayer.inventory.getCurrentItem();
/*      */     
/* 1567 */     if (this.objectMouseOver == null) {
/*      */       
/* 1569 */       logger.warn("Null returned as 'hitResult', this shouldn't happen!");
/*      */     } else {
/*      */       BlockPos var3;
/*      */       
/* 1573 */       switch (SwitchEnumMinecartType.field_152390_a[this.objectMouseOver.typeOfHit.ordinal()]) {
/*      */         
/*      */         case 1:
/* 1576 */           if (this.playerController.func_178894_a((EntityPlayer)this.thePlayer, this.objectMouseOver.entityHit, this.objectMouseOver)) {
/*      */             
/* 1578 */             var1 = false; break;
/*      */           } 
/* 1580 */           if (this.playerController.interactWithEntitySendPacket((EntityPlayer)this.thePlayer, this.objectMouseOver.entityHit))
/*      */           {
/* 1582 */             var1 = false;
/*      */           }
/*      */           break;
/*      */ 
/*      */         
/*      */         case 2:
/* 1588 */           var3 = this.objectMouseOver.func_178782_a();
/*      */           
/* 1590 */           if (this.theWorld.getBlockState(var3).getBlock().getMaterial() != Material.air) {
/*      */             
/* 1592 */             int var4 = (var2 != null) ? var2.stackSize : 0;
/*      */             
/* 1594 */             if (this.playerController.func_178890_a(this.thePlayer, this.theWorld, var2, var3, this.objectMouseOver.field_178784_b, this.objectMouseOver.hitVec)) {
/*      */               
/* 1596 */               var1 = false;
/* 1597 */               this.thePlayer.swingItem();
/*      */             } 
/*      */             
/* 1600 */             if (var2 == null) {
/*      */               return;
/*      */             }
/*      */ 
/*      */             
/* 1605 */             if (var2.stackSize == 0) {
/*      */               
/* 1607 */               this.thePlayer.inventory.mainInventory[this.thePlayer.inventory.currentItem] = null; break;
/*      */             } 
/* 1609 */             if (var2.stackSize != var4 || this.playerController.isInCreativeMode())
/*      */             {
/* 1611 */               this.entityRenderer.itemRenderer.resetEquippedProgress();
/*      */             }
/*      */           } 
/*      */           break;
/*      */       } 
/*      */     } 
/* 1617 */     if (var1) {
/*      */       
/* 1619 */       ItemStack var5 = this.thePlayer.inventory.getCurrentItem();
/*      */       
/* 1621 */       if (var5 != null && this.playerController.sendUseItem((EntityPlayer)this.thePlayer, (World)this.theWorld, var5))
/*      */       {
/* 1623 */         this.entityRenderer.itemRenderer.resetEquippedProgress2();
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void toggleFullscreen() {
/*      */     try {
/* 1635 */       this.fullscreen = !this.fullscreen;
/* 1636 */       this.gameSettings.fullScreen = this.fullscreen;
/*      */       
/* 1638 */       if (this.fullscreen) {
/*      */         
/* 1640 */         updateDisplayMode();
/* 1641 */         this.displayWidth = Display.getDisplayMode().getWidth();
/* 1642 */         this.displayHeight = Display.getDisplayMode().getHeight();
/*      */         
/* 1644 */         if (this.displayWidth <= 0)
/*      */         {
/* 1646 */           this.displayWidth = 1;
/*      */         }
/*      */         
/* 1649 */         if (this.displayHeight <= 0)
/*      */         {
/* 1651 */           this.displayHeight = 1;
/*      */         }
/*      */       }
/*      */       else {
/*      */         
/* 1656 */         Display.setDisplayMode(new DisplayMode(this.tempDisplayWidth, this.tempDisplayHeight));
/* 1657 */         this.displayWidth = this.tempDisplayWidth;
/* 1658 */         this.displayHeight = this.tempDisplayHeight;
/*      */         
/* 1660 */         if (this.displayWidth <= 0)
/*      */         {
/* 1662 */           this.displayWidth = 1;
/*      */         }
/*      */         
/* 1665 */         if (this.displayHeight <= 0)
/*      */         {
/* 1667 */           this.displayHeight = 1;
/*      */         }
/*      */       } 
/*      */       
/* 1671 */       if (this.currentScreen != null) {
/*      */         
/* 1673 */         resize(this.displayWidth, this.displayHeight);
/*      */       }
/*      */       else {
/*      */         
/* 1677 */         updateFramebufferSize();
/*      */       } 
/*      */       
/* 1680 */       Display.setFullscreen(this.fullscreen);
/* 1681 */       Display.setVSyncEnabled(this.gameSettings.enableVsync);
/* 1682 */       func_175601_h();
/*      */     }
/* 1684 */     catch (Exception var2) {
/*      */       
/* 1686 */       logger.error("Couldn't toggle fullscreen", var2);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void resize(int width, int height) {
/* 1695 */     this.displayWidth = Math.max(1, width);
/* 1696 */     this.displayHeight = Math.max(1, height);
/*      */     
/* 1698 */     if (this.currentScreen != null) {
/*      */       
/* 1700 */       ScaledResolution var3 = new ScaledResolution(this, width, height);
/* 1701 */       this.currentScreen.func_175273_b(this, var3.getScaledWidth(), var3.getScaledHeight());
/*      */     } 
/*      */     
/* 1704 */     this.loadingScreen = new LoadingScreenRenderer(this);
/* 1705 */     updateFramebufferSize();
/*      */   }
/*      */ 
/*      */   
/*      */   private void updateFramebufferSize() {
/* 1710 */     this.framebufferMc.createBindFramebuffer(this.displayWidth, this.displayHeight);
/*      */     
/* 1712 */     if (this.entityRenderer != null)
/*      */     {
/* 1714 */       this.entityRenderer.updateShaderGroupSize(this.displayWidth, this.displayHeight);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void runTick() throws IOException {
/* 1723 */     if (this.rightClickDelayTimer > 0)
/*      */     {
/* 1725 */       this.rightClickDelayTimer--;
/*      */     }
/*      */     
/* 1728 */     this.mcProfiler.startSection("gui");
/*      */     
/* 1730 */     if (!this.isGamePaused)
/*      */     {
/* 1732 */       this.ingameGUI.updateTick();
/*      */     }
/*      */     
/* 1735 */     this.mcProfiler.endSection();
/* 1736 */     this.entityRenderer.getMouseOver(1.0F);
/* 1737 */     this.mcProfiler.startSection("gameMode");
/*      */     
/* 1739 */     if (!this.isGamePaused && this.theWorld != null)
/*      */     {
/* 1741 */       this.playerController.updateController();
/*      */     }
/*      */     
/* 1744 */     this.mcProfiler.endStartSection("textures");
/*      */     
/* 1746 */     if (!this.isGamePaused)
/*      */     {
/* 1748 */       this.renderEngine.tick();
/*      */     }
/*      */     
/* 1751 */     if (this.currentScreen == null && this.thePlayer != null) {
/*      */       
/* 1753 */       if (this.thePlayer.getHealth() <= 0.0F)
/*      */       {
/* 1755 */         displayGuiScreen(null);
/*      */       }
/* 1757 */       else if (this.thePlayer.isPlayerSleeping() && this.theWorld != null)
/*      */       {
/* 1759 */         displayGuiScreen((GuiScreen)new GuiSleepMP());
/*      */       }
/*      */     
/* 1762 */     } else if (this.currentScreen != null && this.currentScreen instanceof GuiSleepMP && !this.thePlayer.isPlayerSleeping()) {
/*      */       
/* 1764 */       displayGuiScreen(null);
/*      */     } 
/*      */     
/* 1767 */     if (this.currentScreen != null)
/*      */     {
/* 1769 */       this.leftClickCounter = 10000;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1775 */     if (this.currentScreen != null) {
/*      */ 
/*      */       
/*      */       try {
/* 1779 */         this.currentScreen.handleInput();
/*      */       }
/* 1781 */       catch (Throwable var7) {
/*      */         
/* 1783 */         CrashReport var2 = CrashReport.makeCrashReport(var7, "Updating screen events");
/* 1784 */         CrashReportCategory var3 = var2.makeCategory("Affected screen");
/* 1785 */         var3.addCrashSectionCallable("Screen name", new Callable()
/*      */             {
/*      */               private static final String __OBFID = "CL_00000640";
/*      */               
/*      */               public String call() {
/* 1790 */                 return Minecraft.this.currentScreen.getClass().getCanonicalName();
/*      */               }
/*      */               
/*      */               public Object call1() {
/* 1794 */                 return call();
/*      */               }
/*      */             });
/* 1797 */         throw new ReportedException(var2);
/*      */       } 
/*      */       
/* 1800 */       if (this.currentScreen != null) {
/*      */         
/*      */         try {
/*      */           
/* 1804 */           this.currentScreen.updateScreen();
/*      */         }
/* 1806 */         catch (Throwable var6) {
/*      */           
/* 1808 */           CrashReport var2 = CrashReport.makeCrashReport(var6, "Ticking screen");
/* 1809 */           CrashReportCategory var3 = var2.makeCategory("Affected screen");
/* 1810 */           var3.addCrashSectionCallable("Screen name", new Callable()
/*      */               {
/*      */                 private static final String __OBFID = "CL_00000642";
/*      */                 
/*      */                 public String call() {
/* 1815 */                   return Minecraft.this.currentScreen.getClass().getCanonicalName();
/*      */                 }
/*      */                 
/*      */                 public Object call1() {
/* 1819 */                   return call();
/*      */                 }
/*      */               });
/* 1822 */           throw new ReportedException(var2);
/*      */         } 
/*      */       }
/*      */     } 
/*      */     
/* 1827 */     if (this.currentScreen == null || this.currentScreen.allowUserInput) {
/*      */       
/* 1829 */       this.mcProfiler.endStartSection("mouse");
/*      */ 
/*      */       
/* 1832 */       while (Mouse.next()) {
/*      */         
/* 1834 */         int i = Mouse.getEventButton();
/* 1835 */         KeyBinding.setKeyBindState(i - 100, Mouse.getEventButtonState());
/*      */         
/* 1837 */         if (Mouse.getEventButtonState())
/*      */         {
/* 1839 */           if (this.thePlayer.func_175149_v() && i == 2) {
/*      */             
/* 1841 */             this.ingameGUI.func_175187_g().func_175261_b();
/*      */           }
/*      */           else {
/*      */             
/* 1845 */             KeyBinding.onTick(i - 100);
/*      */           } 
/*      */         }
/*      */         
/* 1849 */         long var10 = getSystemTime() - this.systemTime;
/*      */         
/* 1851 */         if (var10 <= 200L) {
/*      */           
/* 1853 */           int var4 = Mouse.getEventDWheel();
/*      */           
/* 1855 */           if (var4 != 0)
/*      */           {
/* 1857 */             if (this.thePlayer.func_175149_v()) {
/*      */               
/* 1859 */               var4 = (var4 < 0) ? -1 : 1;
/*      */               
/* 1861 */               if (this.ingameGUI.func_175187_g().func_175262_a())
/*      */               {
/* 1863 */                 this.ingameGUI.func_175187_g().func_175259_b(-var4);
/*      */               }
/*      */               else
/*      */               {
/* 1867 */                 float var5 = MathHelper.clamp_float(this.thePlayer.capabilities.getFlySpeed() + var4 * 0.005F, 0.0F, 0.2F);
/* 1868 */                 this.thePlayer.capabilities.setFlySpeed(var5);
/*      */               }
/*      */             
/*      */             } else {
/*      */               
/* 1873 */               this.thePlayer.inventory.changeCurrentItem(var4);
/*      */             } 
/*      */           }
/*      */           
/* 1877 */           if (this.currentScreen == null) {
/*      */             
/* 1879 */             if (!this.inGameHasFocus && Mouse.getEventButtonState())
/*      */             {
/* 1881 */               setIngameFocus(); } 
/*      */             continue;
/*      */           } 
/* 1884 */           if (this.currentScreen != null)
/*      */           {
/* 1886 */             this.currentScreen.handleMouseInput();
/*      */           }
/*      */         } 
/*      */       } 
/*      */       
/* 1891 */       if (this.leftClickCounter > 0)
/*      */       {
/* 1893 */         this.leftClickCounter--;
/*      */       }
/*      */       
/* 1896 */       this.mcProfiler.endStartSection("keyboard");
/*      */       
/* 1898 */       while (Keyboard.next()) {
/*      */         
/* 1900 */         int i = (Keyboard.getEventKey() == 0) ? (Keyboard.getEventCharacter() + 256) : Keyboard.getEventKey();
/* 1901 */         KeyBinding.setKeyBindState(i, Keyboard.getEventKeyState());
/*      */         
/* 1903 */         if (Keyboard.getEventKeyState())
/*      */         {
/* 1905 */           KeyBinding.onTick(i);
/*      */         }
/*      */         
/* 1908 */         if (this.debugCrashKeyPressTime > 0L) {
/*      */           
/* 1910 */           if (getSystemTime() - this.debugCrashKeyPressTime >= 6000L)
/*      */           {
/* 1912 */             throw new ReportedException(new CrashReport("Manually triggered debug crash", new Throwable()));
/*      */           }
/*      */           
/* 1915 */           if (!Keyboard.isKeyDown(46) || !Keyboard.isKeyDown(61))
/*      */           {
/* 1917 */             this.debugCrashKeyPressTime = -1L;
/*      */           }
/*      */         }
/* 1920 */         else if (Keyboard.isKeyDown(46) && Keyboard.isKeyDown(61)) {
/*      */           
/* 1922 */           this.debugCrashKeyPressTime = getSystemTime();
/*      */         } 
/*      */         
/* 1925 */         dispatchKeypresses();
/*      */         
/* 1927 */         if (Keyboard.getEventKeyState()) {
/*      */           
/* 1929 */           if (i == 62 && this.entityRenderer != null)
/*      */           {
/* 1931 */             this.entityRenderer.func_175071_c();
/*      */           }
/*      */           
/* 1934 */           if (this.currentScreen != null) {
/*      */             
/* 1936 */             this.currentScreen.handleKeyboardInput();
/*      */           }
/*      */           else {
/*      */             
/* 1940 */             Client.keyPress(i);
/*      */             
/* 1942 */             if (i == 1)
/*      */             {
/* 1944 */               displayInGameMenu();
/*      */             }
/*      */             
/* 1947 */             if (i == 32 && Keyboard.isKeyDown(61) && this.ingameGUI != null)
/*      */             {
/* 1949 */               this.ingameGUI.getChatGUI().clearChatMessages();
/*      */             }
/*      */             
/* 1952 */             if (i == 31 && Keyboard.isKeyDown(61))
/*      */             {
/* 1954 */               refreshResources();
/*      */             }
/*      */             
/* 1957 */             if (i != 17 || Keyboard.isKeyDown(61));
/*      */ 
/*      */ 
/*      */ 
/*      */             
/* 1962 */             if (i != 18 || Keyboard.isKeyDown(61));
/*      */ 
/*      */ 
/*      */ 
/*      */             
/* 1967 */             if (i != 47 || Keyboard.isKeyDown(61));
/*      */ 
/*      */ 
/*      */ 
/*      */             
/* 1972 */             if (i != 38 || Keyboard.isKeyDown(61));
/*      */ 
/*      */ 
/*      */ 
/*      */             
/* 1977 */             if (i != 22 || Keyboard.isKeyDown(61));
/*      */ 
/*      */ 
/*      */ 
/*      */             
/* 1982 */             if (i == 20 && Keyboard.isKeyDown(61))
/*      */             {
/* 1984 */               refreshResources();
/*      */             }
/*      */             
/* 1987 */             if (i == 33 && Keyboard.isKeyDown(61)) {
/*      */               
/* 1989 */               int j = Keyboard.isKeyDown(42) | Keyboard.isKeyDown(54);
/* 1990 */               this.gameSettings.setOptionValue(GameSettings.Options.RENDER_DISTANCE, (j != 0) ? -1 : 1);
/*      */             } 
/*      */             
/* 1993 */             if (i == 30 && Keyboard.isKeyDown(61))
/*      */             {
/* 1995 */               this.renderGlobal.loadRenderers();
/*      */             }
/*      */             
/* 1998 */             if (i == 35 && Keyboard.isKeyDown(61)) {
/*      */               
/* 2000 */               this.gameSettings.advancedItemTooltips = !this.gameSettings.advancedItemTooltips;
/* 2001 */               this.gameSettings.saveOptions();
/*      */             } 
/*      */             
/* 2004 */             if (i == 48 && Keyboard.isKeyDown(61))
/*      */             {
/* 2006 */               this.renderManager.func_178629_b(!this.renderManager.func_178634_b());
/*      */             }
/*      */             
/* 2009 */             if (i == 25 && Keyboard.isKeyDown(61)) {
/*      */               
/* 2011 */               this.gameSettings.pauseOnLostFocus = !this.gameSettings.pauseOnLostFocus;
/* 2012 */               this.gameSettings.saveOptions();
/*      */             } 
/*      */             
/* 2015 */             if (i == 59)
/*      */             {
/* 2017 */               this.gameSettings.hideGUI = !this.gameSettings.hideGUI;
/*      */             }
/*      */             
/* 2020 */             if (i == 61) {
/*      */               
/* 2022 */               this.gameSettings.showDebugInfo = !this.gameSettings.showDebugInfo;
/* 2023 */               this.gameSettings.showDebugProfilerChart = GuiScreen.isShiftKeyDown();
/*      */             } 
/*      */             
/* 2026 */             if (this.gameSettings.keyBindTogglePerspective.isPressed()) {
/*      */               
/* 2028 */               this.gameSettings.thirdPersonView++;
/*      */               
/* 2030 */               if (this.gameSettings.thirdPersonView > 2)
/*      */               {
/* 2032 */                 this.gameSettings.thirdPersonView = 0;
/*      */               }
/*      */               
/* 2035 */               if (this.gameSettings.thirdPersonView == 0) {
/*      */                 
/* 2037 */                 this.entityRenderer.func_175066_a(func_175606_aa());
/*      */               }
/* 2039 */               else if (this.gameSettings.thirdPersonView == 1) {
/*      */                 
/* 2041 */                 this.entityRenderer.func_175066_a(null);
/*      */               } 
/*      */             } 
/*      */             
/* 2045 */             if (this.gameSettings.keyBindSmoothCamera.isPressed())
/*      */             {
/* 2047 */               this.gameSettings.smoothCamera = !this.gameSettings.smoothCamera;
/*      */             }
/*      */           } 
/*      */           
/* 2051 */           if (this.gameSettings.showDebugInfo && this.gameSettings.showDebugProfilerChart) {
/*      */             
/* 2053 */             if (i == 11)
/*      */             {
/* 2055 */               updateDebugProfilerName(0);
/*      */             }
/*      */             
/* 2058 */             for (int var12 = 0; var12 < 9; var12++) {
/*      */               
/* 2060 */               if (i == 2 + var12)
/*      */               {
/* 2062 */                 updateDebugProfilerName(var12 + 1);
/*      */               }
/*      */             } 
/*      */           } 
/*      */         } 
/*      */       } 
/*      */       
/* 2069 */       for (int var1 = 0; var1 < 9; var1++) {
/*      */         
/* 2071 */         if (this.gameSettings.keyBindsHotbar[var1].isPressed())
/*      */         {
/* 2073 */           if (this.thePlayer.func_175149_v()) {
/*      */             
/* 2075 */             this.ingameGUI.func_175187_g().func_175260_a(var1);
/*      */           }
/*      */           else {
/*      */             
/* 2079 */             this.thePlayer.inventory.currentItem = var1;
/*      */           } 
/*      */         }
/*      */       } 
/*      */       
/* 2084 */       boolean var9 = (this.gameSettings.chatVisibility != EntityPlayer.EnumChatVisibility.HIDDEN);
/*      */       
/* 2086 */       while (this.gameSettings.keyBindInventory.isPressed()) {
/*      */         
/* 2088 */         if (this.playerController.isRidingHorse()) {
/*      */           
/* 2090 */           this.thePlayer.func_175163_u();
/*      */           
/*      */           continue;
/*      */         } 
/* 2094 */         getNetHandler().addToSendQueue((Packet)new C16PacketClientStatus(C16PacketClientStatus.EnumState.OPEN_INVENTORY_ACHIEVEMENT));
/* 2095 */         displayGuiScreen((GuiScreen)new GuiInventory((EntityPlayer)this.thePlayer));
/*      */       } 
/*      */ 
/*      */       
/* 2099 */       while (this.gameSettings.keyBindDrop.isPressed()) {
/*      */         
/* 2101 */         if (!this.thePlayer.func_175149_v())
/*      */         {
/* 2103 */           this.thePlayer.dropOneItem(GuiScreen.isCtrlKeyDown());
/*      */         }
/*      */       } 
/*      */       
/* 2107 */       while (this.gameSettings.keyBindChat.isPressed() && var9)
/*      */       {
/* 2109 */         displayGuiScreen((GuiScreen)new GuiChat());
/*      */       }
/*      */       
/* 2112 */       if (this.currentScreen == null && this.gameSettings.keyBindCommand.isPressed() && var9)
/*      */       {
/* 2114 */         displayGuiScreen((GuiScreen)new GuiChat("/"));
/*      */       }
/*      */       
/* 2117 */       if (this.thePlayer.isUsingItem()) {
/*      */         
/* 2119 */         if (!this.gameSettings.keyBindUseItem.getIsKeyPressed())
/*      */         {
/* 2121 */           this.playerController.onStoppedUsingItem((EntityPlayer)this.thePlayer);
/*      */         }
/*      */ 
/*      */ 
/*      */         
/*      */         do {
/*      */         
/* 2128 */         } while (this.gameSettings.keyBindAttack.isPressed()); do {
/*      */         
/* 2130 */         } while (this.gameSettings.keyBindUseItem.isPressed());
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 2137 */         while (this.gameSettings.keyBindPickBlock.isPressed());
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       }
/*      */       else {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 2149 */         while (this.gameSettings.keyBindAttack.isPressed())
/*      */         {
/* 2151 */           clickMouse();
/*      */         }
/*      */         
/* 2154 */         while (this.gameSettings.keyBindUseItem.isPressed())
/*      */         {
/* 2156 */           rightClickMouse();
/*      */         }
/*      */         
/* 2159 */         while (this.gameSettings.keyBindPickBlock.isPressed())
/*      */         {
/* 2161 */           middleClickMouse();
/*      */         }
/*      */       } 
/*      */       
/* 2165 */       if (this.gameSettings.keyBindUseItem.getIsKeyPressed() && this.rightClickDelayTimer == 0 && !this.thePlayer.isUsingItem())
/*      */       {
/* 2167 */         rightClickMouse();
/*      */       }
/*      */       
/* 2170 */       sendClickBlockToController((this.currentScreen == null && this.gameSettings.keyBindAttack.getIsKeyPressed() && this.inGameHasFocus));
/*      */     } 
/*      */     
/* 2173 */     if (this.theWorld != null) {
/*      */       
/* 2175 */       if (this.thePlayer != null) {
/*      */         
/* 2177 */         this.joinPlayerCounter++;
/*      */         
/* 2179 */         if (this.joinPlayerCounter == 30) {
/*      */           
/* 2181 */           this.joinPlayerCounter = 0;
/* 2182 */           this.theWorld.joinEntityInSurroundings((Entity)this.thePlayer);
/*      */         } 
/*      */       } 
/*      */       
/* 2186 */       this.mcProfiler.endStartSection("gameRenderer");
/*      */       
/* 2188 */       if (!this.isGamePaused)
/*      */       {
/* 2190 */         this.entityRenderer.updateRenderer();
/*      */       }
/*      */       
/* 2193 */       this.mcProfiler.endStartSection("levelRenderer");
/*      */       
/* 2195 */       if (!this.isGamePaused)
/*      */       {
/* 2197 */         this.renderGlobal.updateClouds();
/*      */       }
/*      */       
/* 2200 */       this.mcProfiler.endStartSection("level");
/*      */       
/* 2202 */       if (!this.isGamePaused) {
/*      */         
/* 2204 */         if (this.theWorld.func_175658_ac() > 0)
/*      */         {
/* 2206 */           this.theWorld.setLastLightningBolt(this.theWorld.func_175658_ac() - 1);
/*      */         }
/*      */         
/* 2209 */         this.theWorld.updateEntities();
/*      */       } 
/*      */     } 
/*      */     
/* 2213 */     if (!this.isGamePaused) {
/*      */       
/* 2215 */       this.mcMusicTicker.update();
/* 2216 */       this.mcSoundHandler.update();
/*      */     } 
/*      */     
/* 2219 */     if (this.theWorld != null) {
/*      */       
/* 2221 */       if (!this.isGamePaused) {
/*      */         
/* 2223 */         this.theWorld.setAllowedSpawnTypes((this.theWorld.getDifficulty() != EnumDifficulty.PEACEFUL), true);
/*      */ 
/*      */         
/*      */         try {
/* 2227 */           this.theWorld.tick();
/*      */         }
/* 2229 */         catch (Throwable var8) {
/*      */           
/* 2231 */           CrashReport var2 = CrashReport.makeCrashReport(var8, "Exception in world tick");
/*      */           
/* 2233 */           if (this.theWorld == null) {
/*      */             
/* 2235 */             CrashReportCategory var3 = var2.makeCategory("Affected level");
/* 2236 */             var3.addCrashSection("Problem", "Level is null!");
/*      */           }
/*      */           else {
/*      */             
/* 2240 */             this.theWorld.addWorldInfoToCrashReport(var2);
/*      */           } 
/*      */           
/* 2243 */           throw new ReportedException(var2);
/*      */         } 
/*      */       } 
/*      */       
/* 2247 */       this.mcProfiler.endStartSection("animateTick");
/*      */       
/* 2249 */       if (!this.isGamePaused && this.theWorld != null)
/*      */       {
/* 2251 */         this.theWorld.doVoidFogParticles(MathHelper.floor_double(this.thePlayer.posX), MathHelper.floor_double(this.thePlayer.posY), MathHelper.floor_double(this.thePlayer.posZ));
/*      */       }
/*      */       
/* 2254 */       this.mcProfiler.endStartSection("particles");
/*      */       
/* 2256 */       if (!this.isGamePaused)
/*      */       {
/* 2258 */         this.effectRenderer.updateEffects();
/*      */       }
/*      */     }
/* 2261 */     else if (this.myNetworkManager != null) {
/*      */       
/* 2263 */       this.mcProfiler.endStartSection("pendingConnection");
/* 2264 */       this.myNetworkManager.processReceivedPackets();
/*      */     } 
/*      */     
/* 2267 */     this.mcProfiler.endSection();
/* 2268 */     this.systemTime = getSystemTime();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void launchIntegratedServer(String folderName, String worldName, WorldSettings worldSettingsIn) {
/* 2276 */     loadWorld(null);
/* 2277 */     System.gc();
/* 2278 */     ISaveHandler var4 = this.saveLoader.getSaveLoader(folderName, false);
/* 2279 */     WorldInfo var5 = var4.loadWorldInfo();
/*      */     
/* 2281 */     if (var5 == null && worldSettingsIn != null) {
/*      */       
/* 2283 */       var5 = new WorldInfo(worldSettingsIn, folderName);
/* 2284 */       var4.saveWorldInfo(var5);
/*      */     } 
/*      */     
/* 2287 */     if (worldSettingsIn == null)
/*      */     {
/* 2289 */       worldSettingsIn = new WorldSettings(var5);
/*      */     }
/*      */ 
/*      */     
/*      */     try {
/* 2294 */       this.theIntegratedServer = new IntegratedServer(this, folderName, worldName, worldSettingsIn);
/* 2295 */       this.theIntegratedServer.startServerThread();
/* 2296 */       this.integratedServerIsRunning = true;
/*      */     }
/* 2298 */     catch (Throwable var10) {
/*      */       
/* 2300 */       CrashReport var7 = CrashReport.makeCrashReport(var10, "Starting integrated server");
/* 2301 */       CrashReportCategory var8 = var7.makeCategory("Starting integrated server");
/* 2302 */       var8.addCrashSection("Level ID", folderName);
/* 2303 */       var8.addCrashSection("Level Name", worldName);
/* 2304 */       throw new ReportedException(var7);
/*      */     } 
/*      */     
/* 2307 */     this.loadingScreen.displaySavingString(I18n.format("menu.loadingLevel", new Object[0]));
/*      */     
/* 2309 */     while (!this.theIntegratedServer.serverIsInRunLoop()) {
/*      */       
/* 2311 */       String var6 = this.theIntegratedServer.getUserMessage();
/*      */       
/* 2313 */       if (var6 != null) {
/*      */         
/* 2315 */         this.loadingScreen.displayLoadingString(I18n.format(var6, new Object[0]));
/*      */       }
/*      */       else {
/*      */         
/* 2319 */         this.loadingScreen.displayLoadingString("");
/*      */       } 
/*      */ 
/*      */       
/*      */       try {
/* 2324 */         Thread.sleep(200L);
/*      */       }
/* 2326 */       catch (InterruptedException interruptedException) {}
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2332 */     displayGuiScreen(null);
/* 2333 */     SocketAddress var11 = this.theIntegratedServer.getNetworkSystem().addLocalEndpoint();
/* 2334 */     NetworkManager var12 = NetworkManager.provideLocalClient(var11);
/* 2335 */     var12.setNetHandler((INetHandler)new NetHandlerLoginClient(var12, this, null));
/* 2336 */     var12.sendPacket((Packet)new C00Handshake(47, var11.toString(), 0, EnumConnectionState.LOGIN));
/* 2337 */     var12.sendPacket((Packet)new C00PacketLoginStart(getSession().getProfile()));
/* 2338 */     this.myNetworkManager = var12;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void loadWorld(WorldClient worldClientIn) {
/* 2346 */     loadWorld(worldClientIn, "");
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void loadWorld(WorldClient worldClientIn, String loadingMessage) {
/* 2354 */     if (worldClientIn == null) {
/*      */       
/* 2356 */       NetHandlerPlayClient var3 = getNetHandler();
/*      */       
/* 2358 */       if (var3 != null)
/*      */       {
/* 2360 */         var3.cleanup();
/*      */       }
/*      */       
/* 2363 */       if (this.theIntegratedServer != null && this.theIntegratedServer.func_175578_N()) {
/*      */         
/* 2365 */         this.theIntegratedServer.initiateShutdown();
/* 2366 */         this.theIntegratedServer.func_175592_a();
/*      */       } 
/*      */       
/* 2369 */       this.theIntegratedServer = null;
/* 2370 */       this.guiAchievement.clearAchievements();
/* 2371 */       this.entityRenderer.getMapItemRenderer().func_148249_a();
/*      */     } 
/*      */     
/* 2374 */     this.field_175622_Z = null;
/* 2375 */     this.myNetworkManager = null;
/*      */     
/* 2377 */     if (this.loadingScreen != null) {
/*      */       
/* 2379 */       this.loadingScreen.resetProgressAndMessage(loadingMessage);
/* 2380 */       this.loadingScreen.displayLoadingString("");
/*      */     } 
/*      */     
/* 2383 */     if (worldClientIn == null && this.theWorld != null) {
/*      */       
/* 2385 */       if (this.mcResourcePackRepository.getResourcePackInstance() != null) {
/*      */         
/* 2387 */         this.mcResourcePackRepository.func_148529_f();
/* 2388 */         func_175603_A();
/*      */       } 
/*      */       
/* 2391 */       setServerData(null);
/* 2392 */       this.integratedServerIsRunning = false;
/*      */     } 
/*      */     
/* 2395 */     this.mcSoundHandler.stopSounds();
/* 2396 */     this.theWorld = worldClientIn;
/*      */     
/* 2398 */     if (worldClientIn != null) {
/*      */       
/* 2400 */       if (this.renderGlobal != null)
/*      */       {
/* 2402 */         this.renderGlobal.setWorldAndLoadRenderers(worldClientIn);
/*      */       }
/*      */       
/* 2405 */       if (this.effectRenderer != null)
/*      */       {
/* 2407 */         this.effectRenderer.clearEffects((World)worldClientIn);
/*      */       }
/*      */       
/* 2410 */       if (this.thePlayer == null) {
/*      */         
/* 2412 */         this.thePlayer = this.playerController.func_178892_a((World)worldClientIn, new StatFileWriter());
/* 2413 */         this.playerController.flipPlayer((EntityPlayer)this.thePlayer);
/*      */       } 
/*      */       
/* 2416 */       this.thePlayer.preparePlayerToSpawn();
/* 2417 */       worldClientIn.spawnEntityInWorld((Entity)this.thePlayer);
/* 2418 */       this.thePlayer.movementInput = (MovementInput)new MovementInputFromOptions(this.gameSettings);
/* 2419 */       this.playerController.setPlayerCapabilities((EntityPlayer)this.thePlayer);
/* 2420 */       this.field_175622_Z = (Entity)this.thePlayer;
/*      */     }
/*      */     else {
/*      */       
/* 2424 */       this.saveLoader.flushCache();
/* 2425 */       this.thePlayer = null;
/*      */     } 
/*      */     
/* 2428 */     System.gc();
/* 2429 */     this.systemTime = 0L;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setDimensionAndSpawnPlayer(int dimension) {
/* 2434 */     this.theWorld.setInitialSpawnLocation();
/* 2435 */     this.theWorld.removeAllEntities();
/* 2436 */     int var2 = 0;
/* 2437 */     String var3 = null;
/*      */     
/* 2439 */     if (this.thePlayer != null) {
/*      */       
/* 2441 */       var2 = this.thePlayer.getEntityId();
/* 2442 */       this.theWorld.removeEntity((Entity)this.thePlayer);
/* 2443 */       var3 = this.thePlayer.getClientBrand();
/*      */     } 
/*      */     
/* 2446 */     this.field_175622_Z = null;
/* 2447 */     EntityPlayerSP var4 = this.thePlayer;
/* 2448 */     this.thePlayer = this.playerController.func_178892_a((World)this.theWorld, (this.thePlayer == null) ? new StatFileWriter() : this.thePlayer.getStatFileWriter());
/* 2449 */     this.thePlayer.getDataWatcher().updateWatchedObjectsFromList(var4.getDataWatcher().getAllWatched());
/* 2450 */     this.thePlayer.dimension = dimension;
/* 2451 */     this.field_175622_Z = (Entity)this.thePlayer;
/* 2452 */     this.thePlayer.preparePlayerToSpawn();
/* 2453 */     this.thePlayer.func_175158_f(var3);
/* 2454 */     this.theWorld.spawnEntityInWorld((Entity)this.thePlayer);
/* 2455 */     this.playerController.flipPlayer((EntityPlayer)this.thePlayer);
/* 2456 */     this.thePlayer.movementInput = (MovementInput)new MovementInputFromOptions(this.gameSettings);
/* 2457 */     this.thePlayer.setEntityId(var2);
/* 2458 */     this.playerController.setPlayerCapabilities((EntityPlayer)this.thePlayer);
/* 2459 */     this.thePlayer.func_175150_k(var4.func_175140_cp());
/*      */     
/* 2461 */     if (this.currentScreen instanceof GuiGameOver)
/*      */     {
/* 2463 */       displayGuiScreen(null);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final boolean isDemo() {
/* 2472 */     return this.isDemo;
/*      */   }
/*      */ 
/*      */   
/*      */   public NetHandlerPlayClient getNetHandler() {
/* 2477 */     return (this.thePlayer != null) ? this.thePlayer.sendQueue : null;
/*      */   }
/*      */ 
/*      */   
/*      */   public static boolean isGuiEnabled() {
/* 2482 */     return !(theMinecraft != null && theMinecraft.gameSettings.hideGUI);
/*      */   }
/*      */ 
/*      */   
/*      */   public static boolean isFancyGraphicsEnabled() {
/* 2487 */     return (theMinecraft != null && theMinecraft.gameSettings.fancyGraphics);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean isAmbientOcclusionEnabled() {
/* 2495 */     return (theMinecraft != null && theMinecraft.gameSettings.ambientOcclusion != 0);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void middleClickMouse() {
/* 2503 */     if (this.objectMouseOver != null) {
/*      */       Object var2;
/* 2505 */       boolean var1 = this.thePlayer.capabilities.isCreativeMode;
/* 2506 */       int var3 = 0;
/* 2507 */       boolean var4 = false;
/* 2508 */       TileEntity var5 = null;
/*      */ 
/*      */       
/* 2511 */       if (this.objectMouseOver.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK) {
/*      */         
/* 2513 */         BlockPos var6 = this.objectMouseOver.func_178782_a();
/* 2514 */         Block var7 = this.theWorld.getBlockState(var6).getBlock();
/*      */         
/* 2516 */         if (var7.getMaterial() == Material.air) {
/*      */           return;
/*      */         }
/*      */ 
/*      */         
/* 2521 */         var2 = var7.getItem((World)this.theWorld, var6);
/*      */         
/* 2523 */         if (var2 == null) {
/*      */           return;
/*      */         }
/*      */ 
/*      */         
/* 2528 */         if (var1 && GuiScreen.isCtrlKeyDown())
/*      */         {
/* 2530 */           var5 = this.theWorld.getTileEntity(var6);
/*      */         }
/*      */         
/* 2533 */         Block var8 = (var2 instanceof net.minecraft.item.ItemBlock && !var7.isFlowerPot()) ? Block.getBlockFromItem((Item)var2) : var7;
/* 2534 */         var3 = var8.getDamageValue((World)this.theWorld, var6);
/* 2535 */         var4 = ((Item)var2).getHasSubtypes();
/*      */       }
/*      */       else {
/*      */         
/* 2539 */         if (this.objectMouseOver.typeOfHit != MovingObjectPosition.MovingObjectType.ENTITY || this.objectMouseOver.entityHit == null || !var1) {
/*      */           return;
/*      */         }
/*      */ 
/*      */         
/* 2544 */         if (this.objectMouseOver.entityHit instanceof net.minecraft.entity.item.EntityPainting) {
/*      */           
/* 2546 */           var2 = Items.painting;
/*      */         }
/* 2548 */         else if (this.objectMouseOver.entityHit instanceof net.minecraft.entity.EntityLeashKnot) {
/*      */           
/* 2550 */           var2 = Items.lead;
/*      */         }
/* 2552 */         else if (this.objectMouseOver.entityHit instanceof EntityItemFrame) {
/*      */           
/* 2554 */           EntityItemFrame var11 = (EntityItemFrame)this.objectMouseOver.entityHit;
/* 2555 */           ItemStack var14 = var11.getDisplayedItem();
/*      */           
/* 2557 */           if (var14 == null)
/*      */           {
/* 2559 */             var2 = Items.item_frame;
/*      */           }
/*      */           else
/*      */           {
/* 2563 */             var2 = var14.getItem();
/* 2564 */             var3 = var14.getMetadata();
/* 2565 */             var4 = true;
/*      */           }
/*      */         
/* 2568 */         } else if (this.objectMouseOver.entityHit instanceof EntityMinecart) {
/*      */           
/* 2570 */           EntityMinecart var12 = (EntityMinecart)this.objectMouseOver.entityHit;
/*      */           
/* 2572 */           switch (SwitchEnumMinecartType.field_178901_b[var12.func_180456_s().ordinal()]) {
/*      */             
/*      */             case 1:
/* 2575 */               var2 = Items.furnace_minecart;
/*      */               break;
/*      */             
/*      */             case 2:
/* 2579 */               var2 = Items.chest_minecart;
/*      */               break;
/*      */             
/*      */             case 3:
/* 2583 */               var2 = Items.tnt_minecart;
/*      */               break;
/*      */             
/*      */             case 4:
/* 2587 */               var2 = Items.hopper_minecart;
/*      */               break;
/*      */             
/*      */             case 5:
/* 2591 */               var2 = Items.command_block_minecart;
/*      */               break;
/*      */             
/*      */             default:
/* 2595 */               var2 = Items.minecart;
/*      */               break;
/*      */           } 
/* 2598 */         } else if (this.objectMouseOver.entityHit instanceof net.minecraft.entity.item.EntityBoat) {
/*      */           
/* 2600 */           var2 = Items.boat;
/*      */         }
/* 2602 */         else if (this.objectMouseOver.entityHit instanceof net.minecraft.entity.item.EntityArmorStand) {
/*      */           
/* 2604 */           var2 = Items.armor_stand;
/*      */         }
/*      */         else {
/*      */           
/* 2608 */           var2 = Items.spawn_egg;
/* 2609 */           var3 = EntityList.getEntityID(this.objectMouseOver.entityHit);
/* 2610 */           var4 = true;
/*      */           
/* 2612 */           if (!EntityList.entityEggs.containsKey(Integer.valueOf(var3))) {
/*      */             return;
/*      */           }
/*      */         } 
/*      */       } 
/*      */ 
/*      */       
/* 2619 */       InventoryPlayer var13 = this.thePlayer.inventory;
/*      */       
/* 2621 */       if (var5 == null) {
/*      */         
/* 2623 */         var13.setCurrentItem((Item)var2, var3, var4, var1);
/*      */       }
/*      */       else {
/*      */         
/* 2627 */         NBTTagCompound var15 = new NBTTagCompound();
/* 2628 */         var5.writeToNBT(var15);
/* 2629 */         ItemStack var17 = new ItemStack((Item)var2, 1, var3);
/* 2630 */         var17.setTagInfo("BlockEntityTag", (NBTBase)var15);
/* 2631 */         NBTTagCompound var9 = new NBTTagCompound();
/* 2632 */         NBTTagList var10 = new NBTTagList();
/* 2633 */         var10.appendTag((NBTBase)new NBTTagString("(+NBT)"));
/* 2634 */         var9.setTag("Lore", (NBTBase)var10);
/* 2635 */         var17.setTagInfo("display", (NBTBase)var9);
/* 2636 */         var13.setInventorySlotContents(var13.currentItem, var17);
/*      */       } 
/*      */       
/* 2639 */       if (var1) {
/*      */         
/* 2641 */         int var16 = this.thePlayer.inventoryContainer.inventorySlots.size() - 9 + var13.currentItem;
/* 2642 */         this.playerController.sendSlotPacket(var13.getStackInSlot(var13.currentItem), var16);
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public CrashReport addGraphicsAndWorldToCrashReport(CrashReport theCrash) {
/* 2652 */     theCrash.getCategory().addCrashSectionCallable("Launched Version", new Callable()
/*      */         {
/*      */           private static final String __OBFID = "CL_00000643";
/*      */           
/*      */           public String call() {
/* 2657 */             return Minecraft.this.launchedVersion;
/*      */           }
/*      */           
/*      */           public Object call1() {
/* 2661 */             return call();
/*      */           }
/*      */         });
/* 2664 */     theCrash.getCategory().addCrashSectionCallable("LWJGL", new Callable()
/*      */         {
/*      */           private static final String __OBFID = "CL_00000644";
/*      */           
/*      */           public String call() {
/* 2669 */             return Sys.getVersion();
/*      */           }
/*      */           
/*      */           public Object call1() {
/* 2673 */             return call();
/*      */           }
/*      */         });
/* 2676 */     theCrash.getCategory().addCrashSectionCallable("OpenGL", new Callable()
/*      */         {
/*      */           private static final String __OBFID = "CL_00000645";
/*      */           
/*      */           public String call() {
/* 2681 */             return String.valueOf(GL11.glGetString(7937)) + " GL version " + GL11.glGetString(7938) + ", " + GL11.glGetString(7936);
/*      */           }
/*      */           
/*      */           public Object call1() {
/* 2685 */             return call();
/*      */           }
/*      */         });
/* 2688 */     theCrash.getCategory().addCrashSectionCallable("GL Caps", new Callable()
/*      */         {
/*      */           private static final String __OBFID = "CL_00000646";
/*      */           
/*      */           public String call() {
/* 2693 */             return OpenGlHelper.func_153172_c();
/*      */           }
/*      */           
/*      */           public Object call1() {
/* 2697 */             return call();
/*      */           }
/*      */         });
/* 2700 */     theCrash.getCategory().addCrashSectionCallable("Using VBOs", new Callable()
/*      */         {
/*      */           private static final String __OBFID = "CL_00000647";
/*      */           
/*      */           public String call() {
/* 2705 */             return Minecraft.this.gameSettings.field_178881_t ? "Yes" : "No";
/*      */           }
/*      */           
/*      */           public Object call1() {
/* 2709 */             return call();
/*      */           }
/*      */         });
/* 2712 */     theCrash.getCategory().addCrashSectionCallable("Is Modded", new Callable()
/*      */         {
/*      */           private static final String __OBFID = "CL_00000633";
/*      */           
/*      */           public String call() {
/* 2717 */             String var1 = ClientBrandRetriever.getClientModName();
/* 2718 */             return !var1.equals("vanilla") ? ("Definitely; Client brand changed to '" + var1 + "'") : ((Minecraft.class.getSigners() == null) ? "Very likely; Jar signature invalidated" : "Probably not. Jar signature remains and client brand is untouched.");
/*      */           }
/*      */           
/*      */           public Object call1() {
/* 2722 */             return call();
/*      */           }
/*      */         });
/* 2725 */     theCrash.getCategory().addCrashSectionCallable("Type", new Callable()
/*      */         {
/*      */           private static final String __OBFID = "CL_00000634";
/*      */           
/*      */           public String call() {
/* 2730 */             return "Client (map_client.txt)";
/*      */           }
/*      */           
/*      */           public Object call1() {
/* 2734 */             return call();
/*      */           }
/*      */         });
/* 2737 */     theCrash.getCategory().addCrashSectionCallable("Resource Packs", new Callable()
/*      */         {
/*      */           private static final String __OBFID = "CL_00000635";
/*      */           
/*      */           public String call() {
/* 2742 */             return Minecraft.this.gameSettings.resourcePacks.toString();
/*      */           }
/*      */           
/*      */           public Object call1() {
/* 2746 */             return call();
/*      */           }
/*      */         });
/* 2749 */     theCrash.getCategory().addCrashSectionCallable("Current Language", new Callable()
/*      */         {
/*      */           private static final String __OBFID = "CL_00000636";
/*      */           
/*      */           public String call() {
/* 2754 */             return Minecraft.this.mcLanguageManager.getCurrentLanguage().toString();
/*      */           }
/*      */           
/*      */           public Object call1() {
/* 2758 */             return call();
/*      */           }
/*      */         });
/* 2761 */     theCrash.getCategory().addCrashSectionCallable("Profiler Position", new Callable()
/*      */         {
/*      */           private static final String __OBFID = "CL_00000637";
/*      */           
/*      */           public String call1() {
/* 2766 */             return Minecraft.this.mcProfiler.profilingEnabled ? Minecraft.this.mcProfiler.getNameOfLastSection() : "N/A (disabled)";
/*      */           }
/*      */           
/*      */           public Object call() {
/* 2770 */             return call1();
/*      */           }
/*      */         });
/*      */     
/* 2774 */     if (this.theWorld != null)
/*      */     {
/* 2776 */       this.theWorld.addWorldInfoToCrashReport(theCrash);
/*      */     }
/*      */     
/* 2779 */     return theCrash;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static Minecraft getMinecraft() {
/* 2787 */     return theMinecraft;
/*      */   }
/*      */ 
/*      */   
/*      */   public ListenableFuture func_175603_A() {
/* 2792 */     return addScheduledTask(new Runnable()
/*      */         {
/*      */           private static final String __OBFID = "CL_00001853";
/*      */           
/*      */           public void run() {
/* 2797 */             Minecraft.this.refreshResources();
/*      */           }
/*      */         });
/*      */   }
/*      */ 
/*      */   
/*      */   public void addServerStatsToSnooper(PlayerUsageSnooper playerSnooper) {
/* 2804 */     playerSnooper.addClientStat("fps", Integer.valueOf(debugFPS));
/* 2805 */     playerSnooper.addClientStat("vsync_enabled", Boolean.valueOf(this.gameSettings.enableVsync));
/* 2806 */     playerSnooper.addClientStat("display_frequency", Integer.valueOf(Display.getDisplayMode().getFrequency()));
/* 2807 */     playerSnooper.addClientStat("display_type", this.fullscreen ? "fullscreen" : "windowed");
/* 2808 */     playerSnooper.addClientStat("run_time", Long.valueOf((MinecraftServer.getCurrentTimeMillis() - playerSnooper.getMinecraftStartTimeMillis()) / 60L * 1000L));
/* 2809 */     String var2 = (ByteOrder.nativeOrder() == ByteOrder.LITTLE_ENDIAN) ? "little" : "big";
/* 2810 */     playerSnooper.addClientStat("endianness", var2);
/* 2811 */     playerSnooper.addClientStat("resource_packs", Integer.valueOf(this.mcResourcePackRepository.getRepositoryEntries().size()));
/* 2812 */     int var3 = 0;
/* 2813 */     Iterator<ResourcePackRepository.Entry> var4 = this.mcResourcePackRepository.getRepositoryEntries().iterator();
/*      */     
/* 2815 */     while (var4.hasNext()) {
/*      */       
/* 2817 */       ResourcePackRepository.Entry var5 = var4.next();
/* 2818 */       playerSnooper.addClientStat("resource_pack[" + var3++ + "]", var5.getResourcePackName());
/*      */     } 
/*      */     
/* 2821 */     if (this.theIntegratedServer != null && this.theIntegratedServer.getPlayerUsageSnooper() != null)
/*      */     {
/* 2823 */       playerSnooper.addClientStat("snooper_partner", this.theIntegratedServer.getPlayerUsageSnooper().getUniqueID());
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void addServerTypeToSnooper(PlayerUsageSnooper playerSnooper) {
/* 2829 */     playerSnooper.addStatToSnooper("opengl_version", GL11.glGetString(7938));
/* 2830 */     playerSnooper.addStatToSnooper("opengl_vendor", GL11.glGetString(7936));
/* 2831 */     playerSnooper.addStatToSnooper("client_brand", ClientBrandRetriever.getClientModName());
/* 2832 */     playerSnooper.addStatToSnooper("launched_version", this.launchedVersion);
/* 2833 */     ContextCapabilities var2 = GLContext.getCapabilities();
/* 2834 */     playerSnooper.addStatToSnooper("gl_caps[ARB_arrays_of_arrays]", Boolean.valueOf(var2.GL_ARB_arrays_of_arrays));
/* 2835 */     playerSnooper.addStatToSnooper("gl_caps[ARB_base_instance]", Boolean.valueOf(var2.GL_ARB_base_instance));
/* 2836 */     playerSnooper.addStatToSnooper("gl_caps[ARB_blend_func_extended]", Boolean.valueOf(var2.GL_ARB_blend_func_extended));
/* 2837 */     playerSnooper.addStatToSnooper("gl_caps[ARB_clear_buffer_object]", Boolean.valueOf(var2.GL_ARB_clear_buffer_object));
/* 2838 */     playerSnooper.addStatToSnooper("gl_caps[ARB_color_buffer_float]", Boolean.valueOf(var2.GL_ARB_color_buffer_float));
/* 2839 */     playerSnooper.addStatToSnooper("gl_caps[ARB_compatibility]", Boolean.valueOf(var2.GL_ARB_compatibility));
/* 2840 */     playerSnooper.addStatToSnooper("gl_caps[ARB_compressed_texture_pixel_storage]", Boolean.valueOf(var2.GL_ARB_compressed_texture_pixel_storage));
/* 2841 */     playerSnooper.addStatToSnooper("gl_caps[ARB_compute_shader]", Boolean.valueOf(var2.GL_ARB_compute_shader));
/* 2842 */     playerSnooper.addStatToSnooper("gl_caps[ARB_copy_buffer]", Boolean.valueOf(var2.GL_ARB_copy_buffer));
/* 2843 */     playerSnooper.addStatToSnooper("gl_caps[ARB_copy_image]", Boolean.valueOf(var2.GL_ARB_copy_image));
/* 2844 */     playerSnooper.addStatToSnooper("gl_caps[ARB_depth_buffer_float]", Boolean.valueOf(var2.GL_ARB_depth_buffer_float));
/* 2845 */     playerSnooper.addStatToSnooper("gl_caps[ARB_compute_shader]", Boolean.valueOf(var2.GL_ARB_compute_shader));
/* 2846 */     playerSnooper.addStatToSnooper("gl_caps[ARB_copy_buffer]", Boolean.valueOf(var2.GL_ARB_copy_buffer));
/* 2847 */     playerSnooper.addStatToSnooper("gl_caps[ARB_copy_image]", Boolean.valueOf(var2.GL_ARB_copy_image));
/* 2848 */     playerSnooper.addStatToSnooper("gl_caps[ARB_depth_buffer_float]", Boolean.valueOf(var2.GL_ARB_depth_buffer_float));
/* 2849 */     playerSnooper.addStatToSnooper("gl_caps[ARB_depth_clamp]", Boolean.valueOf(var2.GL_ARB_depth_clamp));
/* 2850 */     playerSnooper.addStatToSnooper("gl_caps[ARB_depth_texture]", Boolean.valueOf(var2.GL_ARB_depth_texture));
/* 2851 */     playerSnooper.addStatToSnooper("gl_caps[ARB_draw_buffers]", Boolean.valueOf(var2.GL_ARB_draw_buffers));
/* 2852 */     playerSnooper.addStatToSnooper("gl_caps[ARB_draw_buffers_blend]", Boolean.valueOf(var2.GL_ARB_draw_buffers_blend));
/* 2853 */     playerSnooper.addStatToSnooper("gl_caps[ARB_draw_elements_base_vertex]", Boolean.valueOf(var2.GL_ARB_draw_elements_base_vertex));
/* 2854 */     playerSnooper.addStatToSnooper("gl_caps[ARB_draw_indirect]", Boolean.valueOf(var2.GL_ARB_draw_indirect));
/* 2855 */     playerSnooper.addStatToSnooper("gl_caps[ARB_draw_instanced]", Boolean.valueOf(var2.GL_ARB_draw_instanced));
/* 2856 */     playerSnooper.addStatToSnooper("gl_caps[ARB_explicit_attrib_location]", Boolean.valueOf(var2.GL_ARB_explicit_attrib_location));
/* 2857 */     playerSnooper.addStatToSnooper("gl_caps[ARB_explicit_uniform_location]", Boolean.valueOf(var2.GL_ARB_explicit_uniform_location));
/* 2858 */     playerSnooper.addStatToSnooper("gl_caps[ARB_fragment_layer_viewport]", Boolean.valueOf(var2.GL_ARB_fragment_layer_viewport));
/* 2859 */     playerSnooper.addStatToSnooper("gl_caps[ARB_fragment_program]", Boolean.valueOf(var2.GL_ARB_fragment_program));
/* 2860 */     playerSnooper.addStatToSnooper("gl_caps[ARB_fragment_shader]", Boolean.valueOf(var2.GL_ARB_fragment_shader));
/* 2861 */     playerSnooper.addStatToSnooper("gl_caps[ARB_fragment_program_shadow]", Boolean.valueOf(var2.GL_ARB_fragment_program_shadow));
/* 2862 */     playerSnooper.addStatToSnooper("gl_caps[ARB_framebuffer_object]", Boolean.valueOf(var2.GL_ARB_framebuffer_object));
/* 2863 */     playerSnooper.addStatToSnooper("gl_caps[ARB_framebuffer_sRGB]", Boolean.valueOf(var2.GL_ARB_framebuffer_sRGB));
/* 2864 */     playerSnooper.addStatToSnooper("gl_caps[ARB_geometry_shader4]", Boolean.valueOf(var2.GL_ARB_geometry_shader4));
/* 2865 */     playerSnooper.addStatToSnooper("gl_caps[ARB_gpu_shader5]", Boolean.valueOf(var2.GL_ARB_gpu_shader5));
/* 2866 */     playerSnooper.addStatToSnooper("gl_caps[ARB_half_float_pixel]", Boolean.valueOf(var2.GL_ARB_half_float_pixel));
/* 2867 */     playerSnooper.addStatToSnooper("gl_caps[ARB_half_float_vertex]", Boolean.valueOf(var2.GL_ARB_half_float_vertex));
/* 2868 */     playerSnooper.addStatToSnooper("gl_caps[ARB_instanced_arrays]", Boolean.valueOf(var2.GL_ARB_instanced_arrays));
/* 2869 */     playerSnooper.addStatToSnooper("gl_caps[ARB_map_buffer_alignment]", Boolean.valueOf(var2.GL_ARB_map_buffer_alignment));
/* 2870 */     playerSnooper.addStatToSnooper("gl_caps[ARB_map_buffer_range]", Boolean.valueOf(var2.GL_ARB_map_buffer_range));
/* 2871 */     playerSnooper.addStatToSnooper("gl_caps[ARB_multisample]", Boolean.valueOf(var2.GL_ARB_multisample));
/* 2872 */     playerSnooper.addStatToSnooper("gl_caps[ARB_multitexture]", Boolean.valueOf(var2.GL_ARB_multitexture));
/* 2873 */     playerSnooper.addStatToSnooper("gl_caps[ARB_occlusion_query2]", Boolean.valueOf(var2.GL_ARB_occlusion_query2));
/* 2874 */     playerSnooper.addStatToSnooper("gl_caps[ARB_pixel_buffer_object]", Boolean.valueOf(var2.GL_ARB_pixel_buffer_object));
/* 2875 */     playerSnooper.addStatToSnooper("gl_caps[ARB_seamless_cube_map]", Boolean.valueOf(var2.GL_ARB_seamless_cube_map));
/* 2876 */     playerSnooper.addStatToSnooper("gl_caps[ARB_shader_objects]", Boolean.valueOf(var2.GL_ARB_shader_objects));
/* 2877 */     playerSnooper.addStatToSnooper("gl_caps[ARB_shader_stencil_export]", Boolean.valueOf(var2.GL_ARB_shader_stencil_export));
/* 2878 */     playerSnooper.addStatToSnooper("gl_caps[ARB_shader_texture_lod]", Boolean.valueOf(var2.GL_ARB_shader_texture_lod));
/* 2879 */     playerSnooper.addStatToSnooper("gl_caps[ARB_shadow]", Boolean.valueOf(var2.GL_ARB_shadow));
/* 2880 */     playerSnooper.addStatToSnooper("gl_caps[ARB_shadow_ambient]", Boolean.valueOf(var2.GL_ARB_shadow_ambient));
/* 2881 */     playerSnooper.addStatToSnooper("gl_caps[ARB_stencil_texturing]", Boolean.valueOf(var2.GL_ARB_stencil_texturing));
/* 2882 */     playerSnooper.addStatToSnooper("gl_caps[ARB_sync]", Boolean.valueOf(var2.GL_ARB_sync));
/* 2883 */     playerSnooper.addStatToSnooper("gl_caps[ARB_tessellation_shader]", Boolean.valueOf(var2.GL_ARB_tessellation_shader));
/* 2884 */     playerSnooper.addStatToSnooper("gl_caps[ARB_texture_border_clamp]", Boolean.valueOf(var2.GL_ARB_texture_border_clamp));
/* 2885 */     playerSnooper.addStatToSnooper("gl_caps[ARB_texture_buffer_object]", Boolean.valueOf(var2.GL_ARB_texture_buffer_object));
/* 2886 */     playerSnooper.addStatToSnooper("gl_caps[ARB_texture_cube_map]", Boolean.valueOf(var2.GL_ARB_texture_cube_map));
/* 2887 */     playerSnooper.addStatToSnooper("gl_caps[ARB_texture_cube_map_array]", Boolean.valueOf(var2.GL_ARB_texture_cube_map_array));
/* 2888 */     playerSnooper.addStatToSnooper("gl_caps[ARB_texture_non_power_of_two]", Boolean.valueOf(var2.GL_ARB_texture_non_power_of_two));
/* 2889 */     playerSnooper.addStatToSnooper("gl_caps[ARB_uniform_buffer_object]", Boolean.valueOf(var2.GL_ARB_uniform_buffer_object));
/* 2890 */     playerSnooper.addStatToSnooper("gl_caps[ARB_vertex_blend]", Boolean.valueOf(var2.GL_ARB_vertex_blend));
/* 2891 */     playerSnooper.addStatToSnooper("gl_caps[ARB_vertex_buffer_object]", Boolean.valueOf(var2.GL_ARB_vertex_buffer_object));
/* 2892 */     playerSnooper.addStatToSnooper("gl_caps[ARB_vertex_program]", Boolean.valueOf(var2.GL_ARB_vertex_program));
/* 2893 */     playerSnooper.addStatToSnooper("gl_caps[ARB_vertex_shader]", Boolean.valueOf(var2.GL_ARB_vertex_shader));
/* 2894 */     playerSnooper.addStatToSnooper("gl_caps[EXT_bindable_uniform]", Boolean.valueOf(var2.GL_EXT_bindable_uniform));
/* 2895 */     playerSnooper.addStatToSnooper("gl_caps[EXT_blend_equation_separate]", Boolean.valueOf(var2.GL_EXT_blend_equation_separate));
/* 2896 */     playerSnooper.addStatToSnooper("gl_caps[EXT_blend_func_separate]", Boolean.valueOf(var2.GL_EXT_blend_func_separate));
/* 2897 */     playerSnooper.addStatToSnooper("gl_caps[EXT_blend_minmax]", Boolean.valueOf(var2.GL_EXT_blend_minmax));
/* 2898 */     playerSnooper.addStatToSnooper("gl_caps[EXT_blend_subtract]", Boolean.valueOf(var2.GL_EXT_blend_subtract));
/* 2899 */     playerSnooper.addStatToSnooper("gl_caps[EXT_draw_instanced]", Boolean.valueOf(var2.GL_EXT_draw_instanced));
/* 2900 */     playerSnooper.addStatToSnooper("gl_caps[EXT_framebuffer_multisample]", Boolean.valueOf(var2.GL_EXT_framebuffer_multisample));
/* 2901 */     playerSnooper.addStatToSnooper("gl_caps[EXT_framebuffer_object]", Boolean.valueOf(var2.GL_EXT_framebuffer_object));
/* 2902 */     playerSnooper.addStatToSnooper("gl_caps[EXT_framebuffer_sRGB]", Boolean.valueOf(var2.GL_EXT_framebuffer_sRGB));
/* 2903 */     playerSnooper.addStatToSnooper("gl_caps[EXT_geometry_shader4]", Boolean.valueOf(var2.GL_EXT_geometry_shader4));
/* 2904 */     playerSnooper.addStatToSnooper("gl_caps[EXT_gpu_program_parameters]", Boolean.valueOf(var2.GL_EXT_gpu_program_parameters));
/* 2905 */     playerSnooper.addStatToSnooper("gl_caps[EXT_gpu_shader4]", Boolean.valueOf(var2.GL_EXT_gpu_shader4));
/* 2906 */     playerSnooper.addStatToSnooper("gl_caps[EXT_multi_draw_arrays]", Boolean.valueOf(var2.GL_EXT_multi_draw_arrays));
/* 2907 */     playerSnooper.addStatToSnooper("gl_caps[EXT_packed_depth_stencil]", Boolean.valueOf(var2.GL_EXT_packed_depth_stencil));
/* 2908 */     playerSnooper.addStatToSnooper("gl_caps[EXT_paletted_texture]", Boolean.valueOf(var2.GL_EXT_paletted_texture));
/* 2909 */     playerSnooper.addStatToSnooper("gl_caps[EXT_rescale_normal]", Boolean.valueOf(var2.GL_EXT_rescale_normal));
/* 2910 */     playerSnooper.addStatToSnooper("gl_caps[EXT_separate_shader_objects]", Boolean.valueOf(var2.GL_EXT_separate_shader_objects));
/* 2911 */     playerSnooper.addStatToSnooper("gl_caps[EXT_shader_image_load_store]", Boolean.valueOf(var2.GL_EXT_shader_image_load_store));
/* 2912 */     playerSnooper.addStatToSnooper("gl_caps[EXT_shadow_funcs]", Boolean.valueOf(var2.GL_EXT_shadow_funcs));
/* 2913 */     playerSnooper.addStatToSnooper("gl_caps[EXT_shared_texture_palette]", Boolean.valueOf(var2.GL_EXT_shared_texture_palette));
/* 2914 */     playerSnooper.addStatToSnooper("gl_caps[EXT_stencil_clear_tag]", Boolean.valueOf(var2.GL_EXT_stencil_clear_tag));
/* 2915 */     playerSnooper.addStatToSnooper("gl_caps[EXT_stencil_two_side]", Boolean.valueOf(var2.GL_EXT_stencil_two_side));
/* 2916 */     playerSnooper.addStatToSnooper("gl_caps[EXT_stencil_wrap]", Boolean.valueOf(var2.GL_EXT_stencil_wrap));
/* 2917 */     playerSnooper.addStatToSnooper("gl_caps[EXT_texture_3d]", Boolean.valueOf(var2.GL_EXT_texture_3d));
/* 2918 */     playerSnooper.addStatToSnooper("gl_caps[EXT_texture_array]", Boolean.valueOf(var2.GL_EXT_texture_array));
/* 2919 */     playerSnooper.addStatToSnooper("gl_caps[EXT_texture_buffer_object]", Boolean.valueOf(var2.GL_EXT_texture_buffer_object));
/* 2920 */     playerSnooper.addStatToSnooper("gl_caps[EXT_texture_integer]", Boolean.valueOf(var2.GL_EXT_texture_integer));
/* 2921 */     playerSnooper.addStatToSnooper("gl_caps[EXT_texture_lod_bias]", Boolean.valueOf(var2.GL_EXT_texture_lod_bias));
/* 2922 */     playerSnooper.addStatToSnooper("gl_caps[EXT_texture_sRGB]", Boolean.valueOf(var2.GL_EXT_texture_sRGB));
/* 2923 */     playerSnooper.addStatToSnooper("gl_caps[EXT_vertex_shader]", Boolean.valueOf(var2.GL_EXT_vertex_shader));
/* 2924 */     playerSnooper.addStatToSnooper("gl_caps[EXT_vertex_weighting]", Boolean.valueOf(var2.GL_EXT_vertex_weighting));
/* 2925 */     playerSnooper.addStatToSnooper("gl_caps[gl_max_vertex_uniforms]", Integer.valueOf(GL11.glGetInteger(35658)));
/* 2926 */     GL11.glGetError();
/* 2927 */     playerSnooper.addStatToSnooper("gl_caps[gl_max_fragment_uniforms]", Integer.valueOf(GL11.glGetInteger(35657)));
/* 2928 */     GL11.glGetError();
/* 2929 */     playerSnooper.addStatToSnooper("gl_caps[gl_max_vertex_attribs]", Integer.valueOf(GL11.glGetInteger(34921)));
/* 2930 */     GL11.glGetError();
/* 2931 */     playerSnooper.addStatToSnooper("gl_caps[gl_max_vertex_texture_image_units]", Integer.valueOf(GL11.glGetInteger(35660)));
/* 2932 */     GL11.glGetError();
/* 2933 */     playerSnooper.addStatToSnooper("gl_caps[gl_max_texture_image_units]", Integer.valueOf(GL11.glGetInteger(34930)));
/* 2934 */     GL11.glGetError();
/* 2935 */     playerSnooper.addStatToSnooper("gl_caps[gl_max_texture_image_units]", Integer.valueOf(GL11.glGetInteger(35071)));
/* 2936 */     GL11.glGetError();
/* 2937 */     playerSnooper.addStatToSnooper("gl_max_texture_size", Integer.valueOf(getGLMaximumTextureSize()));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static int getGLMaximumTextureSize() {
/* 2945 */     for (int var0 = 16384; var0 > 0; var0 >>= 1) {
/*      */       
/* 2947 */       GL11.glTexImage2D(32868, 0, 6408, var0, var0, 0, 6408, 5121, null);
/* 2948 */       int var1 = GL11.glGetTexLevelParameteri(32868, 0, 4096);
/*      */       
/* 2950 */       if (var1 != 0)
/*      */       {
/* 2952 */         return var0;
/*      */       }
/*      */     } 
/*      */     
/* 2956 */     return -1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isSnooperEnabled() {
/* 2964 */     return this.gameSettings.snooperEnabled;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setServerData(ServerData serverDataIn) {
/* 2972 */     this.currentServerData = serverDataIn;
/*      */   }
/*      */ 
/*      */   
/*      */   public ServerData getCurrentServerData() {
/* 2977 */     return this.currentServerData;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isIntegratedServerRunning() {
/* 2982 */     return this.integratedServerIsRunning;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isSingleplayer() {
/* 2990 */     return (this.integratedServerIsRunning && this.theIntegratedServer != null);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public IntegratedServer getIntegratedServer() {
/* 2998 */     return this.theIntegratedServer;
/*      */   }
/*      */ 
/*      */   
/*      */   public static void stopIntegratedServer() {
/* 3003 */     if (theMinecraft != null) {
/*      */       
/* 3005 */       IntegratedServer var0 = theMinecraft.getIntegratedServer();
/*      */       
/* 3007 */       if (var0 != null)
/*      */       {
/* 3009 */         var0.stopServer();
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public PlayerUsageSnooper getPlayerUsageSnooper() {
/* 3019 */     return this.usageSnooper;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static long getSystemTime() {
/* 3027 */     return Sys.getTime() * 1000L / Sys.getTimerResolution();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isFullScreen() {
/* 3035 */     return this.fullscreen;
/*      */   }
/*      */ 
/*      */   
/*      */   public Session getSession() {
/* 3040 */     return this.session;
/*      */   }
/*      */ 
/*      */   
/*      */   public PropertyMap func_180509_L() {
/* 3045 */     return this.twitchDetails;
/*      */   }
/*      */ 
/*      */   
/*      */   public Proxy getProxy() {
/* 3050 */     return this.proxy;
/*      */   }
/*      */ 
/*      */   
/*      */   public TextureManager getTextureManager() {
/* 3055 */     return this.renderEngine;
/*      */   }
/*      */ 
/*      */   
/*      */   public IResourceManager getResourceManager() {
/* 3060 */     return (IResourceManager)this.mcResourceManager;
/*      */   }
/*      */ 
/*      */   
/*      */   public ResourcePackRepository getResourcePackRepository() {
/* 3065 */     return this.mcResourcePackRepository;
/*      */   }
/*      */ 
/*      */   
/*      */   public LanguageManager getLanguageManager() {
/* 3070 */     return this.mcLanguageManager;
/*      */   }
/*      */ 
/*      */   
/*      */   public TextureMap getTextureMapBlocks() {
/* 3075 */     return this.textureMapBlocks;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isJava64bit() {
/* 3080 */     return this.jvm64bit;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isGamePaused() {
/* 3085 */     return this.isGamePaused;
/*      */   }
/*      */ 
/*      */   
/*      */   public SoundHandler getSoundHandler() {
/* 3090 */     return this.mcSoundHandler;
/*      */   }
/*      */ 
/*      */   
/*      */   public MusicTicker.MusicType getAmbientMusicType() {
/* 3095 */     return (this.currentScreen instanceof net.minecraft.client.gui.GuiWinGame) ? MusicTicker.MusicType.CREDITS : ((this.thePlayer != null) ? ((this.thePlayer.worldObj.provider instanceof net.minecraft.world.WorldProviderHell) ? MusicTicker.MusicType.NETHER : ((this.thePlayer.worldObj.provider instanceof net.minecraft.world.WorldProviderEnd) ? ((BossStatus.bossName != null && BossStatus.statusBarTime > 0) ? MusicTicker.MusicType.END_BOSS : MusicTicker.MusicType.END) : ((this.thePlayer.capabilities.isCreativeMode && this.thePlayer.capabilities.allowFlying) ? MusicTicker.MusicType.CREATIVE : MusicTicker.MusicType.GAME))) : MusicTicker.MusicType.MENU);
/*      */   }
/*      */ 
/*      */   
/*      */   public IStream getTwitchStream() {
/* 3100 */     return this.stream;
/*      */   }
/*      */ 
/*      */   
/*      */   public void dispatchKeypresses() {
/* 3105 */     int var1 = (Keyboard.getEventKey() == 0) ? Keyboard.getEventCharacter() : Keyboard.getEventKey();
/*      */     
/* 3107 */     if (var1 != 0 && !Keyboard.isRepeatEvent())
/*      */     {
/* 3109 */       if (!(this.currentScreen instanceof GuiControls) || ((GuiControls)this.currentScreen).time <= getSystemTime() - 20L)
/*      */       {
/* 3111 */         if (Keyboard.getEventKeyState()) {
/*      */           
/* 3113 */           if (var1 == this.gameSettings.keyBindStreamStartStop.getKeyCode()) {
/*      */             
/* 3115 */             if (getTwitchStream().func_152934_n())
/*      */             {
/* 3117 */               getTwitchStream().func_152914_u();
/*      */             }
/* 3119 */             else if (getTwitchStream().func_152924_m())
/*      */             {
/* 3121 */               displayGuiScreen((GuiScreen)new GuiYesNo(new GuiYesNoCallback()
/*      */                     {
/*      */                       private static final String __OBFID = "CL_00001852";
/*      */                       
/*      */                       public void confirmClicked(boolean result, int id) {
/* 3126 */                         if (result)
/*      */                         {
/* 3128 */                           Minecraft.this.getTwitchStream().func_152930_t();
/*      */                         }
/*      */                         
/* 3131 */                         Minecraft.this.displayGuiScreen(null);
/*      */                       }
/* 3133 */                     },  I18n.format("stream.confirm_start", new Object[0]), "", 0));
/*      */             }
/* 3135 */             else if (getTwitchStream().func_152928_D() && getTwitchStream().func_152936_l())
/*      */             {
/* 3137 */               if (this.theWorld != null)
/*      */               {
/* 3139 */                 this.ingameGUI.getChatGUI().printChatMessage((IChatComponent)new ChatComponentText("Not ready to start streaming yet!"));
/*      */               }
/*      */             }
/*      */             else
/*      */             {
/* 3144 */               GuiStreamUnavailable.func_152321_a(this.currentScreen);
/*      */             }
/*      */           
/* 3147 */           } else if (var1 == this.gameSettings.keyBindStreamPauseUnpause.getKeyCode()) {
/*      */             
/* 3149 */             if (getTwitchStream().func_152934_n())
/*      */             {
/* 3151 */               if (getTwitchStream().isPaused())
/*      */               {
/* 3153 */                 getTwitchStream().func_152933_r();
/*      */               }
/*      */               else
/*      */               {
/* 3157 */                 getTwitchStream().func_152916_q();
/*      */               }
/*      */             
/*      */             }
/* 3161 */           } else if (var1 == this.gameSettings.keyBindStreamCommercials.getKeyCode()) {
/*      */             
/* 3163 */             if (getTwitchStream().func_152934_n())
/*      */             {
/* 3165 */               getTwitchStream().func_152931_p();
/*      */             }
/*      */           }
/* 3168 */           else if (var1 == this.gameSettings.keyBindStreamToggleMic.getKeyCode()) {
/*      */             
/* 3170 */             this.stream.func_152910_a(true);
/*      */           }
/* 3172 */           else if (var1 == this.gameSettings.keyBindFullscreen.getKeyCode()) {
/*      */             
/* 3174 */             toggleFullscreen();
/*      */           }
/* 3176 */           else if (var1 == this.gameSettings.keyBindScreenshot.getKeyCode()) {
/*      */             
/* 3178 */             this.ingameGUI.getChatGUI().printChatMessage(ScreenShotHelper.saveScreenshot(this.mcDataDir, this.displayWidth, this.displayHeight, this.framebufferMc));
/*      */           }
/*      */         
/* 3181 */         } else if (var1 == this.gameSettings.keyBindStreamToggleMic.getKeyCode()) {
/*      */           
/* 3183 */           this.stream.func_152910_a(false);
/*      */         } 
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public MinecraftSessionService getSessionService() {
/* 3191 */     return this.sessionService;
/*      */   }
/*      */ 
/*      */   
/*      */   public SkinManager getSkinManager() {
/* 3196 */     return this.skinManager;
/*      */   }
/*      */ 
/*      */   
/*      */   public Entity func_175606_aa() {
/* 3201 */     return this.field_175622_Z;
/*      */   }
/*      */ 
/*      */   
/*      */   public void func_175607_a(Entity p_175607_1_) {
/* 3206 */     this.field_175622_Z = p_175607_1_;
/* 3207 */     this.entityRenderer.func_175066_a(p_175607_1_);
/*      */   }
/*      */ 
/*      */   
/*      */   public ListenableFuture addScheduledTask(Callable callableToSchedule) {
/* 3212 */     Validate.notNull(callableToSchedule);
/*      */     
/* 3214 */     if (!isCallingFromMinecraftThread()) {
/*      */       
/* 3216 */       ListenableFutureTask var2 = ListenableFutureTask.create(callableToSchedule);
/* 3217 */       Queue var3 = this.scheduledTasks;
/*      */       
/* 3219 */       synchronized (this.scheduledTasks) {
/*      */         
/* 3221 */         this.scheduledTasks.add(var2);
/* 3222 */         return (ListenableFuture)var2;
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*      */     try {
/* 3229 */       return Futures.immediateFuture(callableToSchedule.call());
/*      */     }
/* 3231 */     catch (Exception var6) {
/*      */       
/* 3233 */       return (ListenableFuture)Futures.immediateFailedCheckedFuture(var6);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public ListenableFuture addScheduledTask(Runnable runnableToSchedule) {
/* 3240 */     Validate.notNull(runnableToSchedule);
/* 3241 */     return addScheduledTask(Executors.callable(runnableToSchedule));
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isCallingFromMinecraftThread() {
/* 3246 */     return (Thread.currentThread() == this.mcThread);
/*      */   }
/*      */ 
/*      */   
/*      */   public BlockRendererDispatcher getBlockRendererDispatcher() {
/* 3251 */     return this.field_175618_aM;
/*      */   }
/*      */ 
/*      */   
/*      */   public RenderManager getRenderManager() {
/* 3256 */     return this.renderManager;
/*      */   }
/*      */ 
/*      */   
/*      */   public RenderItem getRenderItem() {
/* 3261 */     return this.renderItem;
/*      */   }
/*      */ 
/*      */   
/*      */   public ItemRenderer getItemRenderer() {
/* 3266 */     return this.itemRenderer;
/*      */   }
/*      */ 
/*      */   
/*      */   public static int func_175610_ah() {
/* 3271 */     return debugFPS;
/*      */   }
/*      */ 
/*      */   
/*      */   public static Map func_175596_ai() {
/* 3276 */     HashMap<String, String> var0 = Maps.newHashMap();
/* 3277 */     var0.put("X-Minecraft-Username", getMinecraft().getSession().getUsername());
/* 3278 */     var0.put("X-Minecraft-UUID", getMinecraft().getSession().getPlayerID());
/* 3279 */     var0.put("X-Minecraft-Version", "1.8");
/* 3280 */     return var0;
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
/*      */   static final class SwitchEnumMinecartType
/*      */   {
/* 3337 */     static final int[] field_152390_a = new int[(MovingObjectPosition.MovingObjectType.values()).length]; static final int[] field_178901_b = new int[(EntityMinecart.EnumMinecartType.values()).length]; private static final String __OBFID = "CL_00001959";
/*      */     
/*      */     static {
/*      */       try {
/* 3341 */         field_152390_a[MovingObjectPosition.MovingObjectType.ENTITY.ordinal()] = 1;
/*      */       }
/* 3343 */       catch (NoSuchFieldError noSuchFieldError) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       try {
/* 3350 */         field_152390_a[MovingObjectPosition.MovingObjectType.BLOCK.ordinal()] = 2;
/*      */       }
/* 3352 */       catch (NoSuchFieldError noSuchFieldError) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       try {
/* 3359 */         field_152390_a[MovingObjectPosition.MovingObjectType.MISS.ordinal()] = 3;
/*      */       }
/* 3361 */       catch (NoSuchFieldError noSuchFieldError) {}
/*      */     }
/*      */   }
/*      */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\Minecraft.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */