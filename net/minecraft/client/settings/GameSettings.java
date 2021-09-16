/*      */ package net.minecraft.client.settings;
/*      */ 
/*      */ import com.google.common.collect.ImmutableSet;
/*      */ import com.google.common.collect.Lists;
/*      */ import com.google.common.collect.Maps;
/*      */ import com.google.common.collect.Sets;
/*      */ import com.google.gson.Gson;
/*      */ import java.io.BufferedReader;
/*      */ import java.io.File;
/*      */ import java.io.FileReader;
/*      */ import java.io.FileWriter;
/*      */ import java.io.PrintWriter;
/*      */ import java.lang.reflect.ParameterizedType;
/*      */ import java.lang.reflect.Type;
/*      */ import java.util.Arrays;
/*      */ import java.util.Iterator;
/*      */ import java.util.List;
/*      */ import java.util.Map;
/*      */ import java.util.Set;
/*      */ import net.minecraft.client.Minecraft;
/*      */ import net.minecraft.client.audio.SoundCategory;
/*      */ import net.minecraft.client.gui.GuiNewChat;
/*      */ import net.minecraft.client.renderer.texture.TextureMap;
/*      */ import net.minecraft.client.resources.I18n;
/*      */ import net.minecraft.client.stream.TwitchStream;
/*      */ import net.minecraft.entity.player.EntityPlayer;
/*      */ import net.minecraft.entity.player.EnumPlayerModelParts;
/*      */ import net.minecraft.network.Packet;
/*      */ import net.minecraft.network.play.client.C15PacketClientSettings;
/*      */ import net.minecraft.util.MathHelper;
/*      */ import net.minecraft.world.EnumDifficulty;
/*      */ import net.minecraft.world.World;
/*      */ import optifine.ClearWater;
/*      */ import optifine.Config;
/*      */ import optifine.CustomColors;
/*      */ import optifine.CustomSky;
/*      */ import optifine.DynamicLights;
/*      */ import optifine.Lang;
/*      */ import optifine.NaturalTextures;
/*      */ import optifine.RandomMobs;
/*      */ import optifine.Reflector;
/*      */ import org.apache.commons.lang3.ArrayUtils;
/*      */ import org.apache.logging.log4j.LogManager;
/*      */ import org.apache.logging.log4j.Logger;
/*      */ import org.lwjgl.input.Keyboard;
/*      */ import org.lwjgl.input.Mouse;
/*      */ import org.lwjgl.opengl.Display;
/*      */ import shadersmod.client.Shaders;
/*      */ 
/*      */ public class GameSettings {
/*   51 */   private static final Logger logger = LogManager.getLogger();
/*   52 */   private static final Gson gson = new Gson();
/*   53 */   private static final ParameterizedType typeListString = new ParameterizedType()
/*      */     {
/*      */       private static final String __OBFID = "CL_00000651";
/*      */       
/*      */       public Type[] getActualTypeArguments() {
/*   58 */         return new Type[] { String.class };
/*      */       }
/*      */       
/*      */       public Type getRawType() {
/*   62 */         return List.class;
/*      */       }
/*      */       
/*      */       public Type getOwnerType() {
/*   66 */         return null;
/*      */       }
/*      */     };
/*      */ 
/*      */   
/*   71 */   private static final String[] GUISCALES = new String[] { "options.guiScale.auto", "options.guiScale.small", "options.guiScale.normal", "options.guiScale.large" };
/*   72 */   private static final String[] PARTICLES = new String[] { "options.particles.all", "options.particles.decreased", "options.particles.minimal" };
/*   73 */   private static final String[] AMBIENT_OCCLUSIONS = new String[] { "options.ao.off", "options.ao.min", "options.ao.max" };
/*   74 */   private static final String[] STREAM_COMPRESSIONS = new String[] { "options.stream.compression.low", "options.stream.compression.medium", "options.stream.compression.high" };
/*   75 */   private static final String[] STREAM_CHAT_MODES = new String[] { "options.stream.chat.enabled.streaming", "options.stream.chat.enabled.always", "options.stream.chat.enabled.never" };
/*   76 */   private static final String[] STREAM_CHAT_FILTER_MODES = new String[] { "options.stream.chat.userFilter.all", "options.stream.chat.userFilter.subs", "options.stream.chat.userFilter.mods" };
/*   77 */   private static final String[] STREAM_MIC_MODES = new String[] { "options.stream.mic_toggle.mute", "options.stream.mic_toggle.talk" };
/*   78 */   public float mouseSensitivity = 0.5F;
/*      */   public boolean invertMouse;
/*   80 */   public int renderDistanceChunks = -1;
/*      */   public boolean viewBobbing = true;
/*      */   public boolean anaglyph;
/*      */   public boolean fboEnable = true;
/*   84 */   public int limitFramerate = 120;
/*      */ 
/*      */   
/*      */   public boolean clouds = true;
/*      */   
/*      */   public boolean fancyGraphics = true;
/*      */   
/*   91 */   public int ambientOcclusion = 2;
/*   92 */   public List resourcePacks = Lists.newArrayList();
/*      */   
/*      */   public EntityPlayer.EnumChatVisibility chatVisibility;
/*      */   
/*      */   public boolean chatColours;
/*      */   
/*      */   public boolean chatLinks;
/*      */   
/*      */   public boolean chatLinksPrompt;
/*      */   
/*      */   public float chatOpacity;
/*      */   
/*      */   public boolean snooperEnabled;
/*      */   
/*      */   public boolean fullScreen;
/*      */   
/*      */   public boolean enableVsync;
/*      */   
/*      */   public boolean field_178881_t;
/*      */   
/*      */   public boolean field_178880_u;
/*      */   
/*      */   public boolean field_178879_v;
/*      */   
/*      */   public boolean hideServerAddress;
/*      */   
/*      */   public boolean advancedItemTooltips;
/*      */   
/*      */   public boolean pauseOnLostFocus;
/*      */   
/*      */   private final Set field_178882_aU;
/*      */   
/*      */   public boolean touchscreen;
/*      */   
/*      */   public int overrideWidth;
/*      */   
/*      */   public int overrideHeight;
/*      */   public boolean heldItemTooltips;
/*      */   public float chatScale;
/*      */   public float chatWidth;
/*      */   public float chatHeightUnfocused;
/*      */   public float chatHeightFocused;
/*      */   public boolean showInventoryAchievementHint;
/*      */   public int mipmapLevels;
/*      */   private Map mapSoundLevels;
/*      */   public float streamBytesPerPixel;
/*      */   public float streamMicVolume;
/*      */   public float streamGameVolume;
/*      */   public float streamKbps;
/*      */   public float streamFps;
/*      */   public int streamCompression;
/*      */   public boolean streamSendMetadata;
/*      */   public String streamPreferredServer;
/*      */   public int streamChatEnabled;
/*      */   public int streamChatUserFilter;
/*      */   public int streamMicToggleBehavior;
/*      */   public KeyBinding keyBindForward;
/*      */   public KeyBinding keyBindLeft;
/*      */   public KeyBinding keyBindBack;
/*      */   public KeyBinding keyBindRight;
/*      */   public KeyBinding keyBindJump;
/*      */   public KeyBinding keyBindSneak;
/*      */   public KeyBinding keyBindInventory;
/*      */   public KeyBinding keyBindUseItem;
/*      */   public KeyBinding keyBindDrop;
/*      */   public KeyBinding keyBindAttack;
/*      */   public KeyBinding keyBindPickBlock;
/*      */   public KeyBinding keyBindSprint;
/*      */   public KeyBinding keyBindChat;
/*      */   public KeyBinding keyBindPlayerList;
/*      */   public KeyBinding keyBindCommand;
/*      */   public KeyBinding keyBindScreenshot;
/*      */   public KeyBinding keyBindTogglePerspective;
/*      */   public KeyBinding keyBindSmoothCamera;
/*      */   public KeyBinding keyBindFullscreen;
/*      */   public KeyBinding field_178883_an;
/*      */   public KeyBinding keyBindStreamStartStop;
/*      */   public KeyBinding keyBindStreamPauseUnpause;
/*      */   public KeyBinding keyBindStreamCommercials;
/*      */   public KeyBinding keyBindStreamToggleMic;
/*      */   public KeyBinding[] keyBindsHotbar;
/*      */   public KeyBinding[] keyBindings;
/*      */   protected Minecraft mc;
/*      */   private File optionsFile;
/*      */   public EnumDifficulty difficulty;
/*      */   public boolean hideGUI;
/*      */   public int thirdPersonView;
/*      */   public boolean showDebugInfo;
/*      */   public boolean showDebugProfilerChart;
/*      */   public String lastServer;
/*      */   public boolean smoothCamera;
/*      */   public boolean debugCamEnable;
/*      */   public float fovSetting;
/*      */   public float gammaSetting;
/*      */   public float saturation;
/*      */   public int guiScale;
/*      */   public int particleSetting;
/*      */   public String language;
/*      */   public boolean forceUnicodeFont;
/*      */   private static final String __OBFID = "CL_00000650";
/*  192 */   public int ofFogType = 1;
/*  193 */   public float ofFogStart = 0.8F;
/*  194 */   public int ofMipmapType = 0;
/*      */   public boolean ofOcclusionFancy = false;
/*      */   public boolean ofSmoothFps = false;
/*  197 */   public boolean ofSmoothWorld = Config.isSingleProcessor();
/*  198 */   public boolean ofLazyChunkLoading = Config.isSingleProcessor();
/*  199 */   public float ofAoLevel = 1.0F;
/*  200 */   public int ofAaLevel = 0;
/*  201 */   public int ofAfLevel = 1;
/*  202 */   public int ofClouds = 0;
/*  203 */   public float ofCloudsHeight = 0.0F;
/*  204 */   public int ofTrees = 0;
/*  205 */   public int ofRain = 0;
/*  206 */   public int ofDroppedItems = 0;
/*  207 */   public int ofBetterGrass = 3;
/*  208 */   public int ofAutoSaveTicks = 4000;
/*      */   public boolean ofLagometer = false;
/*      */   public boolean ofProfiler = false;
/*      */   public boolean ofShowFps = false;
/*      */   public boolean ofWeather = true;
/*      */   public boolean ofSky = true;
/*      */   public boolean ofStars = true;
/*      */   public boolean ofSunMoon = true;
/*  216 */   public int ofVignette = 0;
/*  217 */   public int ofChunkUpdates = 1;
/*      */   public boolean ofChunkUpdatesDynamic = false;
/*  219 */   public int ofTime = 0;
/*      */   public boolean ofClearWater = false;
/*      */   public boolean ofBetterSnow = false;
/*  222 */   public String ofFullscreenMode = "Default";
/*      */   public boolean ofSwampColors = true;
/*      */   public boolean ofRandomMobs = true;
/*      */   public boolean ofSmoothBiomes = true;
/*      */   public boolean ofCustomFonts = true;
/*      */   public boolean ofCustomColors = true;
/*      */   public boolean ofCustomSky = true;
/*      */   public boolean ofShowCapes = true;
/*  230 */   public int ofConnectedTextures = 2;
/*      */   public boolean ofCustomItems = true;
/*      */   public boolean ofNaturalTextures = false;
/*      */   public boolean ofFastMath = false;
/*      */   public boolean ofFastRender = true;
/*  235 */   public int ofTranslucentBlocks = 0;
/*      */   public boolean ofDynamicFov = true;
/*  237 */   public int ofDynamicLights = 3;
/*  238 */   public int ofAnimatedWater = 0;
/*  239 */   public int ofAnimatedLava = 0;
/*      */   public boolean ofAnimatedFire = true;
/*      */   public boolean ofAnimatedPortal = true;
/*      */   public boolean ofAnimatedRedstone = true;
/*      */   public boolean ofAnimatedExplosion = true;
/*      */   public boolean ofAnimatedFlame = true;
/*      */   public boolean ofAnimatedSmoke = true;
/*      */   public boolean ofVoidParticles = true;
/*      */   public boolean ofWaterParticles = true;
/*      */   public boolean ofRainSplash = true;
/*      */   public boolean ofPortalParticles = true;
/*      */   public boolean ofPotionParticles = true;
/*      */   public boolean ofFireworkParticles = true;
/*      */   public boolean ofDrippingWaterLava = true;
/*      */   public boolean ofAnimatedTerrain = true;
/*      */   public boolean ofAnimatedTextures = true;
/*      */   public static final int DEFAULT = 0;
/*      */   public static final int FAST = 1;
/*      */   public static final int FANCY = 2;
/*      */   public static final int OFF = 3;
/*      */   public static final int SMART = 4;
/*      */   public static final int ANIM_ON = 0;
/*      */   public static final int ANIM_GENERATED = 1;
/*      */   public static final int ANIM_OFF = 2;
/*      */   public static final String DEFAULT_STR = "Default";
/*  264 */   private static final int[] OF_TREES_VALUES = new int[] { 0, 1, 4, 2 };
/*  265 */   private static final int[] OF_DYNAMIC_LIGHTS = new int[] { 3, 1, 2 };
/*  266 */   private static final String[] KEYS_DYNAMIC_LIGHTS = new String[] { "options.off", "options.graphics.fast", "options.graphics.fancy" };
/*      */   
/*      */   public KeyBinding ofKeyBindZoom;
/*      */   private File optionsFileOF;
/*      */   
/*      */   public GameSettings(Minecraft mcIn, File p_i46326_2_) {
/*  272 */     this.chatVisibility = EntityPlayer.EnumChatVisibility.FULL;
/*  273 */     this.chatColours = true;
/*  274 */     this.chatLinks = true;
/*  275 */     this.chatLinksPrompt = true;
/*  276 */     this.chatOpacity = 1.0F;
/*  277 */     this.snooperEnabled = true;
/*  278 */     this.enableVsync = true;
/*  279 */     this.field_178881_t = false;
/*  280 */     this.field_178880_u = true;
/*  281 */     this.field_178879_v = false;
/*  282 */     this.pauseOnLostFocus = true;
/*  283 */     this.field_178882_aU = Sets.newHashSet((Object[])EnumPlayerModelParts.values());
/*  284 */     this.heldItemTooltips = true;
/*  285 */     this.chatScale = 1.0F;
/*  286 */     this.chatWidth = 1.0F;
/*  287 */     this.chatHeightUnfocused = 0.44366196F;
/*  288 */     this.chatHeightFocused = 1.0F;
/*  289 */     this.showInventoryAchievementHint = true;
/*  290 */     this.mipmapLevels = 4;
/*  291 */     this.mapSoundLevels = Maps.newEnumMap(SoundCategory.class);
/*  292 */     this.streamBytesPerPixel = 0.5F;
/*  293 */     this.streamMicVolume = 1.0F;
/*  294 */     this.streamGameVolume = 1.0F;
/*  295 */     this.streamKbps = 0.5412844F;
/*  296 */     this.streamFps = 0.31690142F;
/*  297 */     this.streamCompression = 1;
/*  298 */     this.streamSendMetadata = true;
/*  299 */     this.streamPreferredServer = "";
/*  300 */     this.streamChatEnabled = 0;
/*  301 */     this.streamChatUserFilter = 0;
/*  302 */     this.streamMicToggleBehavior = 0;
/*  303 */     this.keyBindForward = new KeyBinding("key.forward", 17, "key.categories.movement");
/*  304 */     this.keyBindLeft = new KeyBinding("key.left", 30, "key.categories.movement");
/*  305 */     this.keyBindBack = new KeyBinding("key.back", 31, "key.categories.movement");
/*  306 */     this.keyBindRight = new KeyBinding("key.right", 32, "key.categories.movement");
/*  307 */     this.keyBindJump = new KeyBinding("key.jump", 57, "key.categories.movement");
/*  308 */     this.keyBindSneak = new KeyBinding("key.sneak", 42, "key.categories.movement");
/*  309 */     this.keyBindInventory = new KeyBinding("key.inventory", 18, "key.categories.inventory");
/*  310 */     this.keyBindUseItem = new KeyBinding("key.use", -99, "key.categories.gameplay");
/*  311 */     this.keyBindDrop = new KeyBinding("key.drop", 16, "key.categories.gameplay");
/*  312 */     this.keyBindAttack = new KeyBinding("key.attack", -100, "key.categories.gameplay");
/*  313 */     this.keyBindPickBlock = new KeyBinding("key.pickItem", -98, "key.categories.gameplay");
/*  314 */     this.keyBindSprint = new KeyBinding("key.sprint", 29, "key.categories.gameplay");
/*  315 */     this.keyBindChat = new KeyBinding("key.chat", 20, "key.categories.multiplayer");
/*  316 */     this.keyBindPlayerList = new KeyBinding("key.playerlist", 15, "key.categories.multiplayer");
/*  317 */     this.keyBindCommand = new KeyBinding("key.command", 53, "key.categories.multiplayer");
/*  318 */     this.keyBindScreenshot = new KeyBinding("key.screenshot", 60, "key.categories.misc");
/*  319 */     this.keyBindTogglePerspective = new KeyBinding("key.togglePerspective", 63, "key.categories.misc");
/*  320 */     this.keyBindSmoothCamera = new KeyBinding("key.smoothCamera", 0, "key.categories.misc");
/*  321 */     this.keyBindFullscreen = new KeyBinding("key.fullscreen", 87, "key.categories.misc");
/*  322 */     this.field_178883_an = new KeyBinding("key.spectatorOutlines", 0, "key.categories.misc");
/*  323 */     this.keyBindStreamStartStop = new KeyBinding("key.streamStartStop", 64, "key.categories.stream");
/*  324 */     this.keyBindStreamPauseUnpause = new KeyBinding("key.streamPauseUnpause", 65, "key.categories.stream");
/*  325 */     this.keyBindStreamCommercials = new KeyBinding("key.streamCommercial", 0, "key.categories.stream");
/*  326 */     this.keyBindStreamToggleMic = new KeyBinding("key.streamToggleMic", 0, "key.categories.stream");
/*  327 */     this.keyBindsHotbar = new KeyBinding[] { new KeyBinding("key.hotbar.1", 2, "key.categories.inventory"), new KeyBinding("key.hotbar.2", 3, "key.categories.inventory"), new KeyBinding("key.hotbar.3", 4, "key.categories.inventory"), new KeyBinding("key.hotbar.4", 5, "key.categories.inventory"), new KeyBinding("key.hotbar.5", 6, "key.categories.inventory"), new KeyBinding("key.hotbar.6", 7, "key.categories.inventory"), new KeyBinding("key.hotbar.7", 8, "key.categories.inventory"), new KeyBinding("key.hotbar.8", 9, "key.categories.inventory"), new KeyBinding("key.hotbar.9", 10, "key.categories.inventory") };
/*  328 */     this.keyBindings = (KeyBinding[])ArrayUtils.addAll((Object[])new KeyBinding[] { this.keyBindAttack, this.keyBindUseItem, this.keyBindForward, this.keyBindLeft, this.keyBindBack, this.keyBindRight, this.keyBindJump, this.keyBindSneak, this.keyBindDrop, this.keyBindInventory, this.keyBindChat, this.keyBindPlayerList, this.keyBindPickBlock, this.keyBindCommand, this.keyBindScreenshot, this.keyBindTogglePerspective, this.keyBindSmoothCamera, this.keyBindSprint, this.keyBindStreamStartStop, this.keyBindStreamPauseUnpause, this.keyBindStreamCommercials, this.keyBindStreamToggleMic, this.keyBindFullscreen, this.field_178883_an }, (Object[])this.keyBindsHotbar);
/*  329 */     this.difficulty = EnumDifficulty.NORMAL;
/*  330 */     this.lastServer = "";
/*  331 */     this.fovSetting = 70.0F;
/*  332 */     this.language = "en_US";
/*  333 */     this.forceUnicodeFont = false;
/*  334 */     this.mc = mcIn;
/*  335 */     this.optionsFile = new File(p_i46326_2_, "options.txt");
/*  336 */     this.optionsFileOF = new File(p_i46326_2_, "optionsof.txt");
/*  337 */     this.limitFramerate = (int)Options.FRAMERATE_LIMIT.getValueMax();
/*  338 */     this.ofKeyBindZoom = new KeyBinding("of.key.zoom", 46, "key.categories.misc");
/*  339 */     this.keyBindings = (KeyBinding[])ArrayUtils.add((Object[])this.keyBindings, this.ofKeyBindZoom);
/*  340 */     Options.RENDER_DISTANCE.setValueMax(32.0F);
/*  341 */     this.renderDistanceChunks = 8;
/*  342 */     loadOptions();
/*  343 */     Config.initGameSettings(this);
/*      */   }
/*      */ 
/*      */   
/*      */   public GameSettings() {
/*  348 */     this.chatVisibility = EntityPlayer.EnumChatVisibility.FULL;
/*  349 */     this.chatColours = true;
/*  350 */     this.chatLinks = true;
/*  351 */     this.chatLinksPrompt = true;
/*  352 */     this.chatOpacity = 1.0F;
/*  353 */     this.snooperEnabled = true;
/*  354 */     this.enableVsync = true;
/*  355 */     this.field_178881_t = false;
/*  356 */     this.field_178880_u = true;
/*  357 */     this.field_178879_v = false;
/*  358 */     this.pauseOnLostFocus = true;
/*  359 */     this.field_178882_aU = Sets.newHashSet((Object[])EnumPlayerModelParts.values());
/*  360 */     this.heldItemTooltips = true;
/*  361 */     this.chatScale = 1.0F;
/*  362 */     this.chatWidth = 1.0F;
/*  363 */     this.chatHeightUnfocused = 0.44366196F;
/*  364 */     this.chatHeightFocused = 1.0F;
/*  365 */     this.showInventoryAchievementHint = true;
/*  366 */     this.mipmapLevels = 4;
/*  367 */     this.mapSoundLevels = Maps.newEnumMap(SoundCategory.class);
/*  368 */     this.streamBytesPerPixel = 0.5F;
/*  369 */     this.streamMicVolume = 1.0F;
/*  370 */     this.streamGameVolume = 1.0F;
/*  371 */     this.streamKbps = 0.5412844F;
/*  372 */     this.streamFps = 0.31690142F;
/*  373 */     this.streamCompression = 1;
/*  374 */     this.streamSendMetadata = true;
/*  375 */     this.streamPreferredServer = "";
/*  376 */     this.streamChatEnabled = 0;
/*  377 */     this.streamChatUserFilter = 0;
/*  378 */     this.streamMicToggleBehavior = 0;
/*  379 */     this.keyBindForward = new KeyBinding("key.forward", 17, "key.categories.movement");
/*  380 */     this.keyBindLeft = new KeyBinding("key.left", 30, "key.categories.movement");
/*  381 */     this.keyBindBack = new KeyBinding("key.back", 31, "key.categories.movement");
/*  382 */     this.keyBindRight = new KeyBinding("key.right", 32, "key.categories.movement");
/*  383 */     this.keyBindJump = new KeyBinding("key.jump", 57, "key.categories.movement");
/*  384 */     this.keyBindSneak = new KeyBinding("key.sneak", 42, "key.categories.movement");
/*  385 */     this.keyBindInventory = new KeyBinding("key.inventory", 18, "key.categories.inventory");
/*  386 */     this.keyBindUseItem = new KeyBinding("key.use", -99, "key.categories.gameplay");
/*  387 */     this.keyBindDrop = new KeyBinding("key.drop", 16, "key.categories.gameplay");
/*  388 */     this.keyBindAttack = new KeyBinding("key.attack", -100, "key.categories.gameplay");
/*  389 */     this.keyBindPickBlock = new KeyBinding("key.pickItem", -98, "key.categories.gameplay");
/*  390 */     this.keyBindSprint = new KeyBinding("key.sprint", 29, "key.categories.gameplay");
/*  391 */     this.keyBindChat = new KeyBinding("key.chat", 20, "key.categories.multiplayer");
/*  392 */     this.keyBindPlayerList = new KeyBinding("key.playerlist", 15, "key.categories.multiplayer");
/*  393 */     this.keyBindCommand = new KeyBinding("key.command", 53, "key.categories.multiplayer");
/*  394 */     this.keyBindScreenshot = new KeyBinding("key.screenshot", 60, "key.categories.misc");
/*  395 */     this.keyBindTogglePerspective = new KeyBinding("key.togglePerspective", 63, "key.categories.misc");
/*  396 */     this.keyBindSmoothCamera = new KeyBinding("key.smoothCamera", 0, "key.categories.misc");
/*  397 */     this.keyBindFullscreen = new KeyBinding("key.fullscreen", 87, "key.categories.misc");
/*  398 */     this.field_178883_an = new KeyBinding("key.spectatorOutlines", 0, "key.categories.misc");
/*  399 */     this.keyBindStreamStartStop = new KeyBinding("key.streamStartStop", 64, "key.categories.stream");
/*  400 */     this.keyBindStreamPauseUnpause = new KeyBinding("key.streamPauseUnpause", 65, "key.categories.stream");
/*  401 */     this.keyBindStreamCommercials = new KeyBinding("key.streamCommercial", 0, "key.categories.stream");
/*  402 */     this.keyBindStreamToggleMic = new KeyBinding("key.streamToggleMic", 0, "key.categories.stream");
/*  403 */     this.keyBindsHotbar = new KeyBinding[] { new KeyBinding("key.hotbar.1", 2, "key.categories.inventory"), new KeyBinding("key.hotbar.2", 3, "key.categories.inventory"), new KeyBinding("key.hotbar.3", 4, "key.categories.inventory"), new KeyBinding("key.hotbar.4", 5, "key.categories.inventory"), new KeyBinding("key.hotbar.5", 6, "key.categories.inventory"), new KeyBinding("key.hotbar.6", 7, "key.categories.inventory"), new KeyBinding("key.hotbar.7", 8, "key.categories.inventory"), new KeyBinding("key.hotbar.8", 9, "key.categories.inventory"), new KeyBinding("key.hotbar.9", 10, "key.categories.inventory") };
/*  404 */     this.keyBindings = (KeyBinding[])ArrayUtils.addAll((Object[])new KeyBinding[] { this.keyBindAttack, this.keyBindUseItem, this.keyBindForward, this.keyBindLeft, this.keyBindBack, this.keyBindRight, this.keyBindJump, this.keyBindSneak, this.keyBindDrop, this.keyBindInventory, this.keyBindChat, this.keyBindPlayerList, this.keyBindPickBlock, this.keyBindCommand, this.keyBindScreenshot, this.keyBindTogglePerspective, this.keyBindSmoothCamera, this.keyBindSprint, this.keyBindStreamStartStop, this.keyBindStreamPauseUnpause, this.keyBindStreamCommercials, this.keyBindStreamToggleMic, this.keyBindFullscreen, this.field_178883_an }, (Object[])this.keyBindsHotbar);
/*  405 */     this.difficulty = EnumDifficulty.NORMAL;
/*  406 */     this.lastServer = "";
/*  407 */     this.fovSetting = 70.0F;
/*  408 */     this.language = "en_US";
/*  409 */     this.forceUnicodeFont = false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String getKeyDisplayString(int p_74298_0_) {
/*  417 */     return (p_74298_0_ < 0) ? I18n.format("key.mouseButton", new Object[] { Integer.valueOf(p_74298_0_ + 101) }) : ((p_74298_0_ < 256) ? Keyboard.getKeyName(p_74298_0_) : String.format("%c", new Object[] { Character.valueOf((char)(p_74298_0_ - 256)) }).toUpperCase());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean isKeyDown(KeyBinding p_100015_0_) {
/*  425 */     int keyCode = p_100015_0_.getKeyCode();
/*  426 */     return (keyCode >= -100 && keyCode <= 255) ? ((p_100015_0_.getKeyCode() == 0) ? false : ((p_100015_0_.getKeyCode() < 0) ? Mouse.isButtonDown(p_100015_0_.getKeyCode() + 100) : Keyboard.isKeyDown(p_100015_0_.getKeyCode()))) : false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setOptionKeyBinding(KeyBinding p_151440_1_, int p_151440_2_) {
/*  434 */     p_151440_1_.setKeyCode(p_151440_2_);
/*  435 */     saveOptions();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setOptionFloatValue(Options p_74304_1_, float p_74304_2_) {
/*  443 */     setOptionFloatValueOF(p_74304_1_, p_74304_2_);
/*      */     
/*  445 */     if (p_74304_1_ == Options.SENSITIVITY)
/*      */     {
/*  447 */       this.mouseSensitivity = p_74304_2_;
/*      */     }
/*      */     
/*  450 */     if (p_74304_1_ == Options.FOV)
/*      */     {
/*  452 */       this.fovSetting = p_74304_2_;
/*      */     }
/*      */     
/*  455 */     if (p_74304_1_ == Options.GAMMA)
/*      */     {
/*  457 */       this.gammaSetting = p_74304_2_;
/*      */     }
/*      */     
/*  460 */     if (p_74304_1_ == Options.FRAMERATE_LIMIT) {
/*      */       
/*  462 */       this.limitFramerate = (int)p_74304_2_;
/*  463 */       this.enableVsync = false;
/*      */       
/*  465 */       if (this.limitFramerate <= 0) {
/*      */         
/*  467 */         this.limitFramerate = (int)Options.FRAMERATE_LIMIT.getValueMax();
/*  468 */         this.enableVsync = true;
/*      */       } 
/*      */       
/*  471 */       updateVSync();
/*      */     } 
/*      */     
/*  474 */     if (p_74304_1_ == Options.CHAT_OPACITY) {
/*      */       
/*  476 */       this.chatOpacity = p_74304_2_;
/*  477 */       this.mc.ingameGUI.getChatGUI().refreshChat();
/*      */     } 
/*      */     
/*  480 */     if (p_74304_1_ == Options.CHAT_HEIGHT_FOCUSED) {
/*      */       
/*  482 */       this.chatHeightFocused = p_74304_2_;
/*  483 */       this.mc.ingameGUI.getChatGUI().refreshChat();
/*      */     } 
/*      */     
/*  486 */     if (p_74304_1_ == Options.CHAT_HEIGHT_UNFOCUSED) {
/*      */       
/*  488 */       this.chatHeightUnfocused = p_74304_2_;
/*  489 */       this.mc.ingameGUI.getChatGUI().refreshChat();
/*      */     } 
/*      */     
/*  492 */     if (p_74304_1_ == Options.CHAT_WIDTH) {
/*      */       
/*  494 */       this.chatWidth = p_74304_2_;
/*  495 */       this.mc.ingameGUI.getChatGUI().refreshChat();
/*      */     } 
/*      */     
/*  498 */     if (p_74304_1_ == Options.CHAT_SCALE) {
/*      */       
/*  500 */       this.chatScale = p_74304_2_;
/*  501 */       this.mc.ingameGUI.getChatGUI().refreshChat();
/*      */     } 
/*      */     
/*  504 */     if (p_74304_1_ == Options.MIPMAP_LEVELS) {
/*      */       
/*  506 */       int var3 = this.mipmapLevels;
/*  507 */       this.mipmapLevels = (int)p_74304_2_;
/*      */       
/*  509 */       if (var3 != p_74304_2_) {
/*      */         
/*  511 */         this.mc.getTextureMapBlocks().setMipmapLevels(this.mipmapLevels);
/*  512 */         this.mc.getTextureManager().bindTexture(TextureMap.locationBlocksTexture);
/*  513 */         this.mc.getTextureMapBlocks().func_174937_a(false, (this.mipmapLevels > 0));
/*  514 */         this.mc.func_175603_A();
/*      */       } 
/*      */     } 
/*      */     
/*  518 */     if (p_74304_1_ == Options.BLOCK_ALTERNATIVES) {
/*      */       
/*  520 */       this.field_178880_u = !this.field_178880_u;
/*  521 */       this.mc.renderGlobal.loadRenderers();
/*      */     } 
/*      */     
/*  524 */     if (p_74304_1_ == Options.RENDER_DISTANCE) {
/*      */       
/*  526 */       this.renderDistanceChunks = (int)p_74304_2_;
/*  527 */       this.mc.renderGlobal.func_174979_m();
/*      */     } 
/*      */     
/*  530 */     if (p_74304_1_ == Options.STREAM_BYTES_PER_PIXEL)
/*      */     {
/*  532 */       this.streamBytesPerPixel = p_74304_2_;
/*      */     }
/*      */     
/*  535 */     if (p_74304_1_ == Options.STREAM_VOLUME_MIC) {
/*      */       
/*  537 */       this.streamMicVolume = p_74304_2_;
/*  538 */       this.mc.getTwitchStream().func_152915_s();
/*      */     } 
/*      */     
/*  541 */     if (p_74304_1_ == Options.STREAM_VOLUME_SYSTEM) {
/*      */       
/*  543 */       this.streamGameVolume = p_74304_2_;
/*  544 */       this.mc.getTwitchStream().func_152915_s();
/*      */     } 
/*      */     
/*  547 */     if (p_74304_1_ == Options.STREAM_KBPS)
/*      */     {
/*  549 */       this.streamKbps = p_74304_2_;
/*      */     }
/*      */     
/*  552 */     if (p_74304_1_ == Options.STREAM_FPS)
/*      */     {
/*  554 */       this.streamFps = p_74304_2_;
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setOptionValue(Options p_74306_1_, int p_74306_2_) {
/*  563 */     setOptionValueOF(p_74306_1_, p_74306_2_);
/*      */     
/*  565 */     if (p_74306_1_ == Options.INVERT_MOUSE)
/*      */     {
/*  567 */       this.invertMouse = !this.invertMouse;
/*      */     }
/*      */     
/*  570 */     if (p_74306_1_ == Options.GUI_SCALE)
/*      */     {
/*  572 */       this.guiScale = this.guiScale + p_74306_2_ & 0x3;
/*      */     }
/*      */     
/*  575 */     if (p_74306_1_ == Options.PARTICLES)
/*      */     {
/*  577 */       this.particleSetting = (this.particleSetting + p_74306_2_) % 3;
/*      */     }
/*      */     
/*  580 */     if (p_74306_1_ == Options.VIEW_BOBBING)
/*      */     {
/*  582 */       this.viewBobbing = !this.viewBobbing;
/*      */     }
/*      */     
/*  585 */     if (p_74306_1_ == Options.RENDER_CLOUDS)
/*      */     {
/*  587 */       this.clouds = !this.clouds;
/*      */     }
/*      */     
/*  590 */     if (p_74306_1_ == Options.FORCE_UNICODE_FONT) {
/*      */       
/*  592 */       this.forceUnicodeFont = !this.forceUnicodeFont;
/*  593 */       this.mc.fontRendererObj.setUnicodeFlag(!(!this.mc.getLanguageManager().isCurrentLocaleUnicode() && !this.forceUnicodeFont));
/*      */     } 
/*      */     
/*  596 */     if (p_74306_1_ == Options.FBO_ENABLE)
/*      */     {
/*  598 */       this.fboEnable = !this.fboEnable;
/*      */     }
/*      */     
/*  601 */     if (p_74306_1_ == Options.ANAGLYPH) {
/*      */       
/*  603 */       this.anaglyph = !this.anaglyph;
/*  604 */       this.mc.refreshResources();
/*      */     } 
/*      */     
/*  607 */     if (p_74306_1_ == Options.GRAPHICS) {
/*      */       
/*  609 */       this.fancyGraphics = !this.fancyGraphics;
/*  610 */       updateRenderClouds();
/*  611 */       this.mc.renderGlobal.loadRenderers();
/*      */     } 
/*      */     
/*  614 */     if (p_74306_1_ == Options.AMBIENT_OCCLUSION) {
/*      */       
/*  616 */       this.ambientOcclusion = (this.ambientOcclusion + p_74306_2_) % 3;
/*  617 */       this.mc.renderGlobal.loadRenderers();
/*      */     } 
/*      */     
/*  620 */     if (p_74306_1_ == Options.CHAT_VISIBILITY)
/*      */     {
/*  622 */       this.chatVisibility = EntityPlayer.EnumChatVisibility.getEnumChatVisibility((this.chatVisibility.getChatVisibility() + p_74306_2_) % 3);
/*      */     }
/*      */     
/*  625 */     if (p_74306_1_ == Options.STREAM_COMPRESSION)
/*      */     {
/*  627 */       this.streamCompression = (this.streamCompression + p_74306_2_) % 3;
/*      */     }
/*      */     
/*  630 */     if (p_74306_1_ == Options.STREAM_SEND_METADATA)
/*      */     {
/*  632 */       this.streamSendMetadata = !this.streamSendMetadata;
/*      */     }
/*      */     
/*  635 */     if (p_74306_1_ == Options.STREAM_CHAT_ENABLED)
/*      */     {
/*  637 */       this.streamChatEnabled = (this.streamChatEnabled + p_74306_2_) % 3;
/*      */     }
/*      */     
/*  640 */     if (p_74306_1_ == Options.STREAM_CHAT_USER_FILTER)
/*      */     {
/*  642 */       this.streamChatUserFilter = (this.streamChatUserFilter + p_74306_2_) % 3;
/*      */     }
/*      */     
/*  645 */     if (p_74306_1_ == Options.STREAM_MIC_TOGGLE_BEHAVIOR)
/*      */     {
/*  647 */       this.streamMicToggleBehavior = (this.streamMicToggleBehavior + p_74306_2_) % 2;
/*      */     }
/*      */     
/*  650 */     if (p_74306_1_ == Options.CHAT_COLOR)
/*      */     {
/*  652 */       this.chatColours = !this.chatColours;
/*      */     }
/*      */     
/*  655 */     if (p_74306_1_ == Options.CHAT_LINKS)
/*      */     {
/*  657 */       this.chatLinks = !this.chatLinks;
/*      */     }
/*      */     
/*  660 */     if (p_74306_1_ == Options.CHAT_LINKS_PROMPT)
/*      */     {
/*  662 */       this.chatLinksPrompt = !this.chatLinksPrompt;
/*      */     }
/*      */     
/*  665 */     if (p_74306_1_ == Options.SNOOPER_ENABLED)
/*      */     {
/*  667 */       this.snooperEnabled = !this.snooperEnabled;
/*      */     }
/*      */     
/*  670 */     if (p_74306_1_ == Options.TOUCHSCREEN)
/*      */     {
/*  672 */       this.touchscreen = !this.touchscreen;
/*      */     }
/*      */     
/*  675 */     if (p_74306_1_ == Options.USE_FULLSCREEN) {
/*      */       
/*  677 */       this.fullScreen = !this.fullScreen;
/*      */       
/*  679 */       if (this.mc.isFullScreen() != this.fullScreen)
/*      */       {
/*  681 */         this.mc.toggleFullscreen();
/*      */       }
/*      */     } 
/*      */     
/*  685 */     if (p_74306_1_ == Options.ENABLE_VSYNC) {
/*      */       
/*  687 */       this.enableVsync = !this.enableVsync;
/*  688 */       Display.setVSyncEnabled(this.enableVsync);
/*      */     } 
/*      */     
/*  691 */     if (p_74306_1_ == Options.USE_VBO) {
/*      */       
/*  693 */       this.field_178881_t = !this.field_178881_t;
/*  694 */       this.mc.renderGlobal.loadRenderers();
/*      */     } 
/*      */     
/*  697 */     if (p_74306_1_ == Options.BLOCK_ALTERNATIVES) {
/*      */       
/*  699 */       this.field_178880_u = !this.field_178880_u;
/*  700 */       this.mc.renderGlobal.loadRenderers();
/*      */     } 
/*      */     
/*  703 */     if (p_74306_1_ == Options.REDUCED_DEBUG_INFO)
/*      */     {
/*  705 */       this.field_178879_v = !this.field_178879_v;
/*      */     }
/*      */     
/*  708 */     saveOptions();
/*      */   }
/*      */ 
/*      */   
/*      */   public float getOptionFloatValue(Options p_74296_1_) {
/*  713 */     return (p_74296_1_ == Options.CLOUD_HEIGHT) ? this.ofCloudsHeight : ((p_74296_1_ == Options.AO_LEVEL) ? this.ofAoLevel : ((p_74296_1_ == Options.AA_LEVEL) ? this.ofAaLevel : ((p_74296_1_ == Options.AF_LEVEL) ? this.ofAfLevel : ((p_74296_1_ == Options.MIPMAP_TYPE) ? this.ofMipmapType : ((p_74296_1_ == Options.FRAMERATE_LIMIT) ? ((this.limitFramerate == Options.FRAMERATE_LIMIT.getValueMax() && this.enableVsync) ? 0.0F : this.limitFramerate) : ((p_74296_1_ == Options.FOV) ? this.fovSetting : ((p_74296_1_ == Options.GAMMA) ? this.gammaSetting : ((p_74296_1_ == Options.SATURATION) ? this.saturation : ((p_74296_1_ == Options.SENSITIVITY) ? this.mouseSensitivity : ((p_74296_1_ == Options.CHAT_OPACITY) ? this.chatOpacity : ((p_74296_1_ == Options.CHAT_HEIGHT_FOCUSED) ? this.chatHeightFocused : ((p_74296_1_ == Options.CHAT_HEIGHT_UNFOCUSED) ? this.chatHeightUnfocused : ((p_74296_1_ == Options.CHAT_SCALE) ? this.chatScale : ((p_74296_1_ == Options.CHAT_WIDTH) ? this.chatWidth : ((p_74296_1_ == Options.FRAMERATE_LIMIT) ? this.limitFramerate : ((p_74296_1_ == Options.MIPMAP_LEVELS) ? this.mipmapLevels : ((p_74296_1_ == Options.RENDER_DISTANCE) ? this.renderDistanceChunks : ((p_74296_1_ == Options.STREAM_BYTES_PER_PIXEL) ? this.streamBytesPerPixel : ((p_74296_1_ == Options.STREAM_VOLUME_MIC) ? this.streamMicVolume : ((p_74296_1_ == Options.STREAM_VOLUME_SYSTEM) ? this.streamGameVolume : ((p_74296_1_ == Options.STREAM_KBPS) ? this.streamKbps : ((p_74296_1_ == Options.STREAM_FPS) ? this.streamFps : 0.0F))))))))))))))))))))));
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean getOptionOrdinalValue(Options p_74308_1_) {
/*  718 */     switch (SwitchOptions.optionIds[p_74308_1_.ordinal()]) {
/*      */       
/*      */       case 1:
/*  721 */         return this.invertMouse;
/*      */       
/*      */       case 2:
/*  724 */         return this.viewBobbing;
/*      */       
/*      */       case 3:
/*  727 */         return this.anaglyph;
/*      */       
/*      */       case 4:
/*  730 */         return this.fboEnable;
/*      */       
/*      */       case 5:
/*  733 */         return this.clouds;
/*      */       
/*      */       case 6:
/*  736 */         return this.chatColours;
/*      */       
/*      */       case 7:
/*  739 */         return this.chatLinks;
/*      */       
/*      */       case 8:
/*  742 */         return this.chatLinksPrompt;
/*      */       
/*      */       case 9:
/*  745 */         return this.snooperEnabled;
/*      */       
/*      */       case 10:
/*  748 */         return this.fullScreen;
/*      */       
/*      */       case 11:
/*  751 */         return this.enableVsync;
/*      */       
/*      */       case 12:
/*  754 */         return this.field_178881_t;
/*      */       
/*      */       case 13:
/*  757 */         return this.touchscreen;
/*      */       
/*      */       case 14:
/*  760 */         return this.streamSendMetadata;
/*      */       
/*      */       case 15:
/*  763 */         return this.forceUnicodeFont;
/*      */       
/*      */       case 16:
/*  766 */         return this.field_178880_u;
/*      */       
/*      */       case 17:
/*  769 */         return this.field_178879_v;
/*      */     } 
/*      */     
/*  772 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static String getTranslation(String[] p_74299_0_, int p_74299_1_) {
/*  782 */     if (p_74299_1_ < 0 || p_74299_1_ >= p_74299_0_.length)
/*      */     {
/*  784 */       p_74299_1_ = 0;
/*      */     }
/*      */     
/*  787 */     return I18n.format(p_74299_0_[p_74299_1_], new Object[0]);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getKeyBinding(Options p_74297_1_) {
/*  795 */     String strOF = getKeyBindingOF(p_74297_1_);
/*      */     
/*  797 */     if (strOF != null)
/*      */     {
/*  799 */       return strOF;
/*      */     }
/*      */ 
/*      */     
/*  803 */     String var2 = String.valueOf(I18n.format(p_74297_1_.getEnumString(), new Object[0])) + ": ";
/*      */     
/*  805 */     if (p_74297_1_.getEnumFloat()) {
/*      */       
/*  807 */       float var32 = getOptionFloatValue(p_74297_1_);
/*  808 */       float var4 = p_74297_1_.normalizeValue(var32);
/*  809 */       return (p_74297_1_ == Options.SENSITIVITY) ? ((var4 == 0.0F) ? (String.valueOf(var2) + I18n.format("options.sensitivity.min", new Object[0])) : ((var4 == 1.0F) ? (String.valueOf(var2) + I18n.format("options.sensitivity.max", new Object[0])) : (String.valueOf(var2) + (int)(var4 * 200.0F) + "%"))) : ((p_74297_1_ == Options.FOV) ? ((var32 == 70.0F) ? (String.valueOf(var2) + I18n.format("options.fov.min", new Object[0])) : ((var32 == 110.0F) ? (String.valueOf(var2) + I18n.format("options.fov.max", new Object[0])) : (String.valueOf(var2) + (int)var32))) : ((p_74297_1_ == Options.FRAMERATE_LIMIT) ? ((var32 == p_74297_1_.valueMax) ? (String.valueOf(var2) + I18n.format("options.framerateLimit.max", new Object[0])) : (String.valueOf(var2) + (int)var32 + " fps")) : ((p_74297_1_ == Options.RENDER_CLOUDS) ? ((var32 == p_74297_1_.valueMin) ? (String.valueOf(var2) + I18n.format("options.cloudHeight.min", new Object[0])) : (String.valueOf(var2) + ((int)var32 + 128))) : ((p_74297_1_ == Options.GAMMA) ? ((var4 == 0.0F) ? (String.valueOf(var2) + I18n.format("options.gamma.min", new Object[0])) : ((var4 == 1.0F) ? (String.valueOf(var2) + I18n.format("options.gamma.max", new Object[0])) : (String.valueOf(var2) + "+" + (int)(var4 * 100.0F) + "%"))) : ((p_74297_1_ == Options.SATURATION) ? (String.valueOf(var2) + (int)(var4 * 400.0F) + "%") : ((p_74297_1_ == Options.CHAT_OPACITY) ? (String.valueOf(var2) + (int)(var4 * 90.0F + 10.0F) + "%") : ((p_74297_1_ == Options.CHAT_HEIGHT_UNFOCUSED) ? (String.valueOf(var2) + GuiNewChat.calculateChatboxHeight(var4) + "px") : ((p_74297_1_ == Options.CHAT_HEIGHT_FOCUSED) ? (String.valueOf(var2) + GuiNewChat.calculateChatboxHeight(var4) + "px") : ((p_74297_1_ == Options.CHAT_WIDTH) ? (String.valueOf(var2) + GuiNewChat.calculateChatboxWidth(var4) + "px") : ((p_74297_1_ == Options.RENDER_DISTANCE) ? (String.valueOf(var2) + (int)var32 + " chunks") : ((p_74297_1_ == Options.MIPMAP_LEVELS) ? ((var32 == 0.0F) ? (String.valueOf(var2) + I18n.format("options.off", new Object[0])) : (String.valueOf(var2) + (int)var32)) : ((p_74297_1_ == Options.STREAM_FPS) ? (String.valueOf(var2) + TwitchStream.func_152948_a(var4) + " fps") : ((p_74297_1_ == Options.STREAM_KBPS) ? (String.valueOf(var2) + TwitchStream.func_152946_b(var4) + " Kbps") : ((p_74297_1_ == Options.STREAM_BYTES_PER_PIXEL) ? (String.valueOf(var2) + String.format("%.3f bpp", new Object[] { Float.valueOf(TwitchStream.func_152947_c(var4)) })) : ((var4 == 0.0F) ? (String.valueOf(var2) + I18n.format("options.off", new Object[0])) : (String.valueOf(var2) + (int)(var4 * 100.0F) + "%"))))))))))))))));
/*      */     } 
/*  811 */     if (p_74297_1_.getEnumBoolean()) {
/*      */       
/*  813 */       boolean var31 = getOptionOrdinalValue(p_74297_1_);
/*  814 */       return var31 ? (String.valueOf(var2) + I18n.format("options.on", new Object[0])) : (String.valueOf(var2) + I18n.format("options.off", new Object[0]));
/*      */     } 
/*  816 */     if (p_74297_1_ == Options.GUI_SCALE)
/*      */     {
/*  818 */       return String.valueOf(var2) + getTranslation(GUISCALES, this.guiScale);
/*      */     }
/*  820 */     if (p_74297_1_ == Options.CHAT_VISIBILITY)
/*      */     {
/*  822 */       return String.valueOf(var2) + I18n.format(this.chatVisibility.getResourceKey(), new Object[0]);
/*      */     }
/*  824 */     if (p_74297_1_ == Options.PARTICLES)
/*      */     {
/*  826 */       return String.valueOf(var2) + getTranslation(PARTICLES, this.particleSetting);
/*      */     }
/*  828 */     if (p_74297_1_ == Options.AMBIENT_OCCLUSION)
/*      */     {
/*  830 */       return String.valueOf(var2) + getTranslation(AMBIENT_OCCLUSIONS, this.ambientOcclusion);
/*      */     }
/*  832 */     if (p_74297_1_ == Options.STREAM_COMPRESSION)
/*      */     {
/*  834 */       return String.valueOf(var2) + getTranslation(STREAM_COMPRESSIONS, this.streamCompression);
/*      */     }
/*  836 */     if (p_74297_1_ == Options.STREAM_CHAT_ENABLED)
/*      */     {
/*  838 */       return String.valueOf(var2) + getTranslation(STREAM_CHAT_MODES, this.streamChatEnabled);
/*      */     }
/*  840 */     if (p_74297_1_ == Options.STREAM_CHAT_USER_FILTER)
/*      */     {
/*  842 */       return String.valueOf(var2) + getTranslation(STREAM_CHAT_FILTER_MODES, this.streamChatUserFilter);
/*      */     }
/*  844 */     if (p_74297_1_ == Options.STREAM_MIC_TOGGLE_BEHAVIOR)
/*      */     {
/*  846 */       return String.valueOf(var2) + getTranslation(STREAM_MIC_MODES, this.streamMicToggleBehavior);
/*      */     }
/*  848 */     if (p_74297_1_ == Options.GRAPHICS) {
/*      */       
/*  850 */       if (this.fancyGraphics)
/*      */       {
/*  852 */         return String.valueOf(var2) + I18n.format("options.graphics.fancy", new Object[0]);
/*      */       }
/*      */ 
/*      */       
/*  856 */       String var3 = "options.graphics.fast";
/*  857 */       return String.valueOf(var2) + I18n.format("options.graphics.fast", new Object[0]);
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*  862 */     return var2;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void loadOptions() {
/*      */     try {
/*  874 */       if (!this.optionsFile.exists()) {
/*      */         return;
/*      */       }
/*      */ 
/*      */       
/*  879 */       BufferedReader var9 = new BufferedReader(new FileReader(this.optionsFile));
/*  880 */       String var2 = "";
/*  881 */       this.mapSoundLevels.clear();
/*      */       
/*  883 */       while ((var2 = var9.readLine()) != null) {
/*      */ 
/*      */         
/*      */         try {
/*  887 */           String[] var8 = var2.split(":");
/*      */           
/*  889 */           if (var8[0].equals("mouseSensitivity"))
/*      */           {
/*  891 */             this.mouseSensitivity = parseFloat(var8[1]);
/*      */           }
/*      */           
/*  894 */           if (var8[0].equals("fov"))
/*      */           {
/*  896 */             this.fovSetting = parseFloat(var8[1]) * 40.0F + 70.0F;
/*      */           }
/*      */           
/*  899 */           if (var8[0].equals("gamma"))
/*      */           {
/*  901 */             this.gammaSetting = parseFloat(var8[1]);
/*      */           }
/*      */           
/*  904 */           if (var8[0].equals("saturation"))
/*      */           {
/*  906 */             this.saturation = parseFloat(var8[1]);
/*      */           }
/*      */           
/*  909 */           if (var8[0].equals("invertYMouse"))
/*      */           {
/*  911 */             this.invertMouse = var8[1].equals("true");
/*      */           }
/*      */           
/*  914 */           if (var8[0].equals("renderDistance"))
/*      */           {
/*  916 */             this.renderDistanceChunks = Integer.parseInt(var8[1]);
/*      */           }
/*      */           
/*  919 */           if (var8[0].equals("guiScale"))
/*      */           {
/*  921 */             this.guiScale = Integer.parseInt(var8[1]);
/*      */           }
/*      */           
/*  924 */           if (var8[0].equals("particles"))
/*      */           {
/*  926 */             this.particleSetting = Integer.parseInt(var8[1]);
/*      */           }
/*      */           
/*  929 */           if (var8[0].equals("bobView"))
/*      */           {
/*  931 */             this.viewBobbing = var8[1].equals("true");
/*      */           }
/*      */           
/*  934 */           if (var8[0].equals("anaglyph3d"))
/*      */           {
/*  936 */             this.anaglyph = var8[1].equals("true");
/*      */           }
/*      */           
/*  939 */           if (var8[0].equals("maxFps")) {
/*      */             
/*  941 */             this.limitFramerate = Integer.parseInt(var8[1]);
/*  942 */             this.enableVsync = false;
/*      */             
/*  944 */             if (this.limitFramerate <= 0) {
/*      */               
/*  946 */               this.limitFramerate = (int)Options.FRAMERATE_LIMIT.getValueMax();
/*  947 */               this.enableVsync = true;
/*      */             } 
/*      */             
/*  950 */             updateVSync();
/*      */           } 
/*      */           
/*  953 */           if (var8[0].equals("fboEnable"))
/*      */           {
/*  955 */             this.fboEnable = var8[1].equals("true");
/*      */           }
/*      */           
/*  958 */           if (var8[0].equals("difficulty"))
/*      */           {
/*  960 */             this.difficulty = EnumDifficulty.getDifficultyEnum(Integer.parseInt(var8[1]));
/*      */           }
/*      */           
/*  963 */           if (var8[0].equals("fancyGraphics")) {
/*      */             
/*  965 */             this.fancyGraphics = var8[1].equals("true");
/*  966 */             updateRenderClouds();
/*      */           } 
/*      */           
/*  969 */           if (var8[0].equals("ao"))
/*      */           {
/*  971 */             if (var8[1].equals("true")) {
/*      */               
/*  973 */               this.ambientOcclusion = 2;
/*      */             }
/*  975 */             else if (var8[1].equals("false")) {
/*      */               
/*  977 */               this.ambientOcclusion = 0;
/*      */             }
/*      */             else {
/*      */               
/*  981 */               this.ambientOcclusion = Integer.parseInt(var8[1]);
/*      */             } 
/*      */           }
/*      */           
/*  985 */           if (var8[0].equals("renderClouds"))
/*      */           {
/*  987 */             this.clouds = var8[1].equals("true");
/*      */           }
/*      */           
/*  990 */           if (var8[0].equals("resourcePacks")) {
/*      */             
/*  992 */             this.resourcePacks = (List)gson.fromJson(var2.substring(var2.indexOf(':') + 1), typeListString);
/*      */             
/*  994 */             if (this.resourcePacks == null)
/*      */             {
/*  996 */               this.resourcePacks = Lists.newArrayList();
/*      */             }
/*      */           } 
/*      */           
/* 1000 */           if (var8[0].equals("lastServer") && var8.length >= 2)
/*      */           {
/* 1002 */             this.lastServer = var2.substring(var2.indexOf(':') + 1);
/*      */           }
/*      */           
/* 1005 */           if (var8[0].equals("lang") && var8.length >= 2)
/*      */           {
/* 1007 */             this.language = var8[1];
/*      */           }
/*      */           
/* 1010 */           if (var8[0].equals("chatVisibility"))
/*      */           {
/* 1012 */             this.chatVisibility = EntityPlayer.EnumChatVisibility.getEnumChatVisibility(Integer.parseInt(var8[1]));
/*      */           }
/*      */           
/* 1015 */           if (var8[0].equals("chatColors"))
/*      */           {
/* 1017 */             this.chatColours = var8[1].equals("true");
/*      */           }
/*      */           
/* 1020 */           if (var8[0].equals("chatLinks"))
/*      */           {
/* 1022 */             this.chatLinks = var8[1].equals("true");
/*      */           }
/*      */           
/* 1025 */           if (var8[0].equals("chatLinksPrompt"))
/*      */           {
/* 1027 */             this.chatLinksPrompt = var8[1].equals("true");
/*      */           }
/*      */           
/* 1030 */           if (var8[0].equals("chatOpacity"))
/*      */           {
/* 1032 */             this.chatOpacity = parseFloat(var8[1]);
/*      */           }
/*      */           
/* 1035 */           if (var8[0].equals("snooperEnabled"))
/*      */           {
/* 1037 */             this.snooperEnabled = var8[1].equals("true");
/*      */           }
/*      */           
/* 1040 */           if (var8[0].equals("fullscreen"))
/*      */           {
/* 1042 */             this.fullScreen = var8[1].equals("true");
/*      */           }
/*      */           
/* 1045 */           if (var8[0].equals("enableVsync")) {
/*      */             
/* 1047 */             this.enableVsync = var8[1].equals("true");
/* 1048 */             updateVSync();
/*      */           } 
/*      */           
/* 1051 */           if (var8[0].equals("useVbo"))
/*      */           {
/* 1053 */             this.field_178881_t = var8[1].equals("true");
/*      */           }
/*      */           
/* 1056 */           if (var8[0].equals("hideServerAddress"))
/*      */           {
/* 1058 */             this.hideServerAddress = var8[1].equals("true");
/*      */           }
/*      */           
/* 1061 */           if (var8[0].equals("advancedItemTooltips"))
/*      */           {
/* 1063 */             this.advancedItemTooltips = var8[1].equals("true");
/*      */           }
/*      */           
/* 1066 */           if (var8[0].equals("pauseOnLostFocus"))
/*      */           {
/* 1068 */             this.pauseOnLostFocus = var8[1].equals("true");
/*      */           }
/*      */           
/* 1071 */           if (var8[0].equals("touchscreen"))
/*      */           {
/* 1073 */             this.touchscreen = var8[1].equals("true");
/*      */           }
/*      */           
/* 1076 */           if (var8[0].equals("overrideHeight"))
/*      */           {
/* 1078 */             this.overrideHeight = Integer.parseInt(var8[1]);
/*      */           }
/*      */           
/* 1081 */           if (var8[0].equals("overrideWidth"))
/*      */           {
/* 1083 */             this.overrideWidth = Integer.parseInt(var8[1]);
/*      */           }
/*      */           
/* 1086 */           if (var8[0].equals("heldItemTooltips"))
/*      */           {
/* 1088 */             this.heldItemTooltips = var8[1].equals("true");
/*      */           }
/*      */           
/* 1091 */           if (var8[0].equals("chatHeightFocused"))
/*      */           {
/* 1093 */             this.chatHeightFocused = parseFloat(var8[1]);
/*      */           }
/*      */           
/* 1096 */           if (var8[0].equals("chatHeightUnfocused"))
/*      */           {
/* 1098 */             this.chatHeightUnfocused = parseFloat(var8[1]);
/*      */           }
/*      */           
/* 1101 */           if (var8[0].equals("chatScale"))
/*      */           {
/* 1103 */             this.chatScale = parseFloat(var8[1]);
/*      */           }
/*      */           
/* 1106 */           if (var8[0].equals("chatWidth"))
/*      */           {
/* 1108 */             this.chatWidth = parseFloat(var8[1]);
/*      */           }
/*      */           
/* 1111 */           if (var8[0].equals("showInventoryAchievementHint"))
/*      */           {
/* 1113 */             this.showInventoryAchievementHint = var8[1].equals("true");
/*      */           }
/*      */           
/* 1116 */           if (var8[0].equals("mipmapLevels"))
/*      */           {
/* 1118 */             this.mipmapLevels = Integer.parseInt(var8[1]);
/*      */           }
/*      */           
/* 1121 */           if (var8[0].equals("streamBytesPerPixel"))
/*      */           {
/* 1123 */             this.streamBytesPerPixel = parseFloat(var8[1]);
/*      */           }
/*      */           
/* 1126 */           if (var8[0].equals("streamMicVolume"))
/*      */           {
/* 1128 */             this.streamMicVolume = parseFloat(var8[1]);
/*      */           }
/*      */           
/* 1131 */           if (var8[0].equals("streamSystemVolume"))
/*      */           {
/* 1133 */             this.streamGameVolume = parseFloat(var8[1]);
/*      */           }
/*      */           
/* 1136 */           if (var8[0].equals("streamKbps"))
/*      */           {
/* 1138 */             this.streamKbps = parseFloat(var8[1]);
/*      */           }
/*      */           
/* 1141 */           if (var8[0].equals("streamFps"))
/*      */           {
/* 1143 */             this.streamFps = parseFloat(var8[1]);
/*      */           }
/*      */           
/* 1146 */           if (var8[0].equals("streamCompression"))
/*      */           {
/* 1148 */             this.streamCompression = Integer.parseInt(var8[1]);
/*      */           }
/*      */           
/* 1151 */           if (var8[0].equals("streamSendMetadata"))
/*      */           {
/* 1153 */             this.streamSendMetadata = var8[1].equals("true");
/*      */           }
/*      */           
/* 1156 */           if (var8[0].equals("streamPreferredServer") && var8.length >= 2)
/*      */           {
/* 1158 */             this.streamPreferredServer = var2.substring(var2.indexOf(':') + 1);
/*      */           }
/*      */           
/* 1161 */           if (var8[0].equals("streamChatEnabled"))
/*      */           {
/* 1163 */             this.streamChatEnabled = Integer.parseInt(var8[1]);
/*      */           }
/*      */           
/* 1166 */           if (var8[0].equals("streamChatUserFilter"))
/*      */           {
/* 1168 */             this.streamChatUserFilter = Integer.parseInt(var8[1]);
/*      */           }
/*      */           
/* 1171 */           if (var8[0].equals("streamMicToggleBehavior"))
/*      */           {
/* 1173 */             this.streamMicToggleBehavior = Integer.parseInt(var8[1]);
/*      */           }
/*      */           
/* 1176 */           if (var8[0].equals("forceUnicodeFont"))
/*      */           {
/* 1178 */             this.forceUnicodeFont = var8[1].equals("true");
/*      */           }
/*      */           
/* 1181 */           if (var8[0].equals("allowBlockAlternatives"))
/*      */           {
/* 1183 */             this.field_178880_u = var8[1].equals("true");
/*      */           }
/*      */           
/* 1186 */           if (var8[0].equals("reducedDebugInfo"))
/*      */           {
/* 1188 */             this.field_178879_v = var8[1].equals("true");
/*      */           }
/*      */           
/* 1191 */           KeyBinding[] var4 = this.keyBindings;
/* 1192 */           int var5 = var4.length;
/*      */           
/*      */           int var6;
/* 1195 */           for (var6 = 0; var6 < var5; var6++) {
/*      */             
/* 1197 */             KeyBinding var10 = var4[var6];
/*      */             
/* 1199 */             if (var8[0].equals("key_" + var10.getKeyDescription()))
/*      */             {
/* 1201 */               var10.setKeyCode(Integer.parseInt(var8[1]));
/*      */             }
/*      */           } 
/*      */           
/* 1205 */           SoundCategory[] var12 = SoundCategory.values();
/* 1206 */           var5 = var12.length;
/*      */           
/* 1208 */           for (var6 = 0; var6 < var5; var6++) {
/*      */             
/* 1210 */             SoundCategory var11 = var12[var6];
/*      */             
/* 1212 */             if (var8[0].equals("soundCategory_" + var11.getCategoryName()))
/*      */             {
/* 1214 */               this.mapSoundLevels.put(var11, Float.valueOf(parseFloat(var8[1])));
/*      */             }
/*      */           } 
/*      */           
/* 1218 */           EnumPlayerModelParts[] var131 = EnumPlayerModelParts.values();
/* 1219 */           var5 = var131.length;
/*      */           
/* 1221 */           for (var6 = 0; var6 < var5; var6++)
/*      */           {
/* 1223 */             EnumPlayerModelParts var13 = var131[var6];
/*      */             
/* 1225 */             if (var8[0].equals("modelPart_" + var13.func_179329_c()))
/*      */             {
/* 1227 */               func_178878_a(var13, var8[1].equals("true"));
/*      */             }
/*      */           }
/*      */         
/* 1231 */         } catch (Exception var101) {
/*      */           
/* 1233 */           logger.warn("Skipping bad option: " + var2);
/* 1234 */           var101.printStackTrace();
/*      */         } 
/*      */       } 
/*      */       
/* 1238 */       KeyBinding.resetKeyBindingArrayAndHash();
/* 1239 */       var9.close();
/*      */     }
/* 1241 */     catch (Exception var111) {
/*      */       
/* 1243 */       logger.error("Failed to load options", var111);
/*      */     } 
/*      */     
/* 1246 */     loadOfOptions();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private float parseFloat(String p_74305_1_) {
/* 1254 */     return p_74305_1_.equals("true") ? 1.0F : (p_74305_1_.equals("false") ? 0.0F : Float.parseFloat(p_74305_1_));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void saveOptions() {
/* 1262 */     if (Reflector.FMLClientHandler.exists()) {
/*      */       
/* 1264 */       Object var6 = Reflector.call(Reflector.FMLClientHandler_instance, new Object[0]);
/*      */       
/* 1266 */       if (var6 != null && Reflector.callBoolean(var6, Reflector.FMLClientHandler_isLoading, new Object[0])) {
/*      */         return;
/*      */       }
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*      */     try {
/* 1274 */       PrintWriter var9 = new PrintWriter(new FileWriter(this.optionsFile));
/* 1275 */       var9.println("invertYMouse:" + this.invertMouse);
/* 1276 */       var9.println("mouseSensitivity:" + this.mouseSensitivity);
/* 1277 */       var9.println("fov:" + ((this.fovSetting - 70.0F) / 40.0F));
/* 1278 */       var9.println("gamma:" + this.gammaSetting);
/* 1279 */       var9.println("saturation:" + this.saturation);
/* 1280 */       var9.println("renderDistance:" + this.renderDistanceChunks);
/* 1281 */       var9.println("guiScale:" + this.guiScale);
/* 1282 */       var9.println("particles:" + this.particleSetting);
/* 1283 */       var9.println("bobView:" + this.viewBobbing);
/* 1284 */       var9.println("anaglyph3d:" + this.anaglyph);
/* 1285 */       var9.println("maxFps:" + this.limitFramerate);
/* 1286 */       var9.println("fboEnable:" + this.fboEnable);
/* 1287 */       var9.println("difficulty:" + this.difficulty.getDifficultyId());
/* 1288 */       var9.println("fancyGraphics:" + this.fancyGraphics);
/* 1289 */       var9.println("ao:" + this.ambientOcclusion);
/* 1290 */       var9.println("renderClouds:" + this.clouds);
/* 1291 */       var9.println("resourcePacks:" + gson.toJson(this.resourcePacks));
/* 1292 */       var9.println("lastServer:" + this.lastServer);
/* 1293 */       var9.println("lang:" + this.language);
/* 1294 */       var9.println("chatVisibility:" + this.chatVisibility.getChatVisibility());
/* 1295 */       var9.println("chatColors:" + this.chatColours);
/* 1296 */       var9.println("chatLinks:" + this.chatLinks);
/* 1297 */       var9.println("chatLinksPrompt:" + this.chatLinksPrompt);
/* 1298 */       var9.println("chatOpacity:" + this.chatOpacity);
/* 1299 */       var9.println("snooperEnabled:" + this.snooperEnabled);
/* 1300 */       var9.println("fullscreen:" + this.fullScreen);
/* 1301 */       var9.println("enableVsync:" + this.enableVsync);
/* 1302 */       var9.println("useVbo:" + this.field_178881_t);
/* 1303 */       var9.println("hideServerAddress:" + this.hideServerAddress);
/* 1304 */       var9.println("advancedItemTooltips:" + this.advancedItemTooltips);
/* 1305 */       var9.println("pauseOnLostFocus:" + this.pauseOnLostFocus);
/* 1306 */       var9.println("touchscreen:" + this.touchscreen);
/* 1307 */       var9.println("overrideWidth:" + this.overrideWidth);
/* 1308 */       var9.println("overrideHeight:" + this.overrideHeight);
/* 1309 */       var9.println("heldItemTooltips:" + this.heldItemTooltips);
/* 1310 */       var9.println("chatHeightFocused:" + this.chatHeightFocused);
/* 1311 */       var9.println("chatHeightUnfocused:" + this.chatHeightUnfocused);
/* 1312 */       var9.println("chatScale:" + this.chatScale);
/* 1313 */       var9.println("chatWidth:" + this.chatWidth);
/* 1314 */       var9.println("showInventoryAchievementHint:" + this.showInventoryAchievementHint);
/* 1315 */       var9.println("mipmapLevels:" + this.mipmapLevels);
/* 1316 */       var9.println("streamBytesPerPixel:" + this.streamBytesPerPixel);
/* 1317 */       var9.println("streamMicVolume:" + this.streamMicVolume);
/* 1318 */       var9.println("streamSystemVolume:" + this.streamGameVolume);
/* 1319 */       var9.println("streamKbps:" + this.streamKbps);
/* 1320 */       var9.println("streamFps:" + this.streamFps);
/* 1321 */       var9.println("streamCompression:" + this.streamCompression);
/* 1322 */       var9.println("streamSendMetadata:" + this.streamSendMetadata);
/* 1323 */       var9.println("streamPreferredServer:" + this.streamPreferredServer);
/* 1324 */       var9.println("streamChatEnabled:" + this.streamChatEnabled);
/* 1325 */       var9.println("streamChatUserFilter:" + this.streamChatUserFilter);
/* 1326 */       var9.println("streamMicToggleBehavior:" + this.streamMicToggleBehavior);
/* 1327 */       var9.println("forceUnicodeFont:" + this.forceUnicodeFont);
/* 1328 */       var9.println("allowBlockAlternatives:" + this.field_178880_u);
/* 1329 */       var9.println("reducedDebugInfo:" + this.field_178879_v);
/* 1330 */       KeyBinding[] var2 = this.keyBindings;
/* 1331 */       int var3 = var2.length;
/*      */       
/*      */       int var4;
/* 1334 */       for (var4 = 0; var4 < var3; var4++) {
/*      */         
/* 1336 */         KeyBinding var7 = var2[var4];
/* 1337 */         var9.println("key_" + var7.getKeyDescription() + ":" + var7.getKeyCode());
/*      */       } 
/*      */       
/* 1340 */       SoundCategory[] var101 = SoundCategory.values();
/* 1341 */       var3 = var101.length;
/*      */       
/* 1343 */       for (var4 = 0; var4 < var3; var4++) {
/*      */         
/* 1345 */         SoundCategory var8 = var101[var4];
/* 1346 */         var9.println("soundCategory_" + var8.getCategoryName() + ":" + getSoundLevel(var8));
/*      */       } 
/*      */       
/* 1349 */       EnumPlayerModelParts[] var11 = EnumPlayerModelParts.values();
/* 1350 */       var3 = var11.length;
/*      */       
/* 1352 */       for (var4 = 0; var4 < var3; var4++) {
/*      */         
/* 1354 */         EnumPlayerModelParts var10 = var11[var4];
/* 1355 */         var9.println("modelPart_" + var10.func_179329_c() + ":" + this.field_178882_aU.contains(var10));
/*      */       } 
/*      */       
/* 1358 */       var9.close();
/*      */     }
/* 1360 */     catch (Exception var81) {
/*      */       
/* 1362 */       logger.error("Failed to save options", var81);
/*      */     } 
/*      */     
/* 1365 */     saveOfOptions();
/* 1366 */     sendSettingsToServer();
/*      */   }
/*      */ 
/*      */   
/*      */   public float getSoundLevel(SoundCategory p_151438_1_) {
/* 1371 */     return this.mapSoundLevels.containsKey(p_151438_1_) ? ((Float)this.mapSoundLevels.get(p_151438_1_)).floatValue() : 1.0F;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setSoundLevel(SoundCategory p_151439_1_, float p_151439_2_) {
/* 1376 */     this.mc.getSoundHandler().setSoundLevel(p_151439_1_, p_151439_2_);
/* 1377 */     this.mapSoundLevels.put(p_151439_1_, Float.valueOf(p_151439_2_));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void sendSettingsToServer() {
/* 1385 */     if (this.mc.thePlayer != null) {
/*      */       
/* 1387 */       int var1 = 0;
/*      */ 
/*      */       
/* 1390 */       for (Iterator<EnumPlayerModelParts> var2 = this.field_178882_aU.iterator(); var2.hasNext(); var1 |= var3.func_179327_a())
/*      */       {
/* 1392 */         EnumPlayerModelParts var3 = var2.next();
/*      */       }
/*      */       
/* 1395 */       this.mc.thePlayer.sendQueue.addToSendQueue((Packet)new C15PacketClientSettings(this.language, this.renderDistanceChunks, this.chatVisibility, this.chatColours, var1));
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public Set func_178876_d() {
/* 1401 */     return (Set)ImmutableSet.copyOf(this.field_178882_aU);
/*      */   }
/*      */ 
/*      */   
/*      */   public void func_178878_a(EnumPlayerModelParts p_178878_1_, boolean p_178878_2_) {
/* 1406 */     if (p_178878_2_) {
/*      */       
/* 1408 */       this.field_178882_aU.add(p_178878_1_);
/*      */     }
/*      */     else {
/*      */       
/* 1412 */       this.field_178882_aU.remove(p_178878_1_);
/*      */     } 
/*      */     
/* 1415 */     sendSettingsToServer();
/*      */   }
/*      */ 
/*      */   
/*      */   public void func_178877_a(EnumPlayerModelParts p_178877_1_) {
/* 1420 */     if (!func_178876_d().contains(p_178877_1_)) {
/*      */       
/* 1422 */       this.field_178882_aU.add(p_178877_1_);
/*      */     }
/*      */     else {
/*      */       
/* 1426 */       this.field_178882_aU.remove(p_178877_1_);
/*      */     } 
/*      */     
/* 1429 */     sendSettingsToServer();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean shouldRenderClouds() {
/* 1437 */     return (this.renderDistanceChunks >= 4 && this.clouds);
/*      */   }
/*      */ 
/*      */   
/*      */   private void setOptionFloatValueOF(Options option, float val) {
/* 1442 */     if (option == Options.CLOUD_HEIGHT) {
/*      */       
/* 1444 */       this.ofCloudsHeight = val;
/* 1445 */       this.mc.renderGlobal.resetClouds();
/*      */     } 
/*      */     
/* 1448 */     if (option == Options.AO_LEVEL) {
/*      */       
/* 1450 */       this.ofAoLevel = val;
/* 1451 */       this.mc.renderGlobal.loadRenderers();
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 1456 */     if (option == Options.AA_LEVEL) {
/*      */       
/* 1458 */       int valInt = (int)val;
/*      */       
/* 1460 */       if (valInt > 0 && Config.isShaders()) {
/*      */         
/* 1462 */         Config.showGuiMessage(Lang.get("of.message.aa.shaders1"), Lang.get("of.message.aa.shaders2"));
/*      */         
/*      */         return;
/*      */       } 
/* 1466 */       int[] aaLevels = { 0, 2, 4, 6, 8, 12, 16 };
/* 1467 */       this.ofAaLevel = 0;
/*      */       
/* 1469 */       for (int l = 0; l < aaLevels.length; l++) {
/*      */         
/* 1471 */         if (valInt >= aaLevels[l])
/*      */         {
/* 1473 */           this.ofAaLevel = aaLevels[l];
/*      */         }
/*      */       } 
/*      */       
/* 1477 */       this.ofAaLevel = Config.limit(this.ofAaLevel, 0, 16);
/*      */     } 
/*      */     
/* 1480 */     if (option == Options.AF_LEVEL) {
/*      */       
/* 1482 */       int valInt = (int)val;
/*      */       
/* 1484 */       if (valInt > 1 && Config.isShaders()) {
/*      */         
/* 1486 */         Config.showGuiMessage(Lang.get("of.message.af.shaders1"), Lang.get("of.message.af.shaders2"));
/*      */         
/*      */         return;
/*      */       } 
/* 1490 */       for (this.ofAfLevel = 1; this.ofAfLevel * 2 <= valInt; this.ofAfLevel *= 2);
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1495 */       this.ofAfLevel = Config.limit(this.ofAfLevel, 1, 16);
/* 1496 */       this.mc.refreshResources();
/*      */     } 
/*      */     
/* 1499 */     if (option == Options.MIPMAP_TYPE) {
/*      */       
/* 1501 */       int valInt = (int)val;
/* 1502 */       this.ofMipmapType = Config.limit(valInt, 0, 3);
/* 1503 */       this.mc.refreshResources();
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   private void setOptionValueOF(Options par1EnumOptions, int par2) {
/* 1509 */     if (par1EnumOptions == Options.FOG_FANCY)
/*      */     {
/* 1511 */       switch (this.ofFogType) {
/*      */         
/*      */         case 1:
/* 1514 */           this.ofFogType = 2;
/*      */           
/* 1516 */           if (!Config.isFancyFogAvailable())
/*      */           {
/* 1518 */             this.ofFogType = 3;
/*      */           }
/*      */           break;
/*      */ 
/*      */         
/*      */         case 2:
/* 1524 */           this.ofFogType = 3;
/*      */           break;
/*      */         
/*      */         case 3:
/* 1528 */           this.ofFogType = 1;
/*      */           break;
/*      */         
/*      */         default:
/* 1532 */           this.ofFogType = 1;
/*      */           break;
/*      */       } 
/*      */     }
/* 1536 */     if (par1EnumOptions == Options.FOG_START) {
/*      */       
/* 1538 */       this.ofFogStart += 0.2F;
/*      */       
/* 1540 */       if (this.ofFogStart > 0.81F)
/*      */       {
/* 1542 */         this.ofFogStart = 0.2F;
/*      */       }
/*      */     } 
/*      */     
/* 1546 */     if (par1EnumOptions == Options.SMOOTH_FPS)
/*      */     {
/* 1548 */       this.ofSmoothFps = !this.ofSmoothFps;
/*      */     }
/*      */     
/* 1551 */     if (par1EnumOptions == Options.SMOOTH_WORLD) {
/*      */       
/* 1553 */       this.ofSmoothWorld = !this.ofSmoothWorld;
/* 1554 */       Config.updateThreadPriorities();
/*      */     } 
/*      */     
/* 1557 */     if (par1EnumOptions == Options.CLOUDS) {
/*      */       
/* 1559 */       this.ofClouds++;
/*      */       
/* 1561 */       if (this.ofClouds > 3)
/*      */       {
/* 1563 */         this.ofClouds = 0;
/*      */       }
/*      */       
/* 1566 */       updateRenderClouds();
/* 1567 */       this.mc.renderGlobal.resetClouds();
/*      */     } 
/*      */     
/* 1570 */     if (par1EnumOptions == Options.TREES) {
/*      */       
/* 1572 */       this.ofTrees = nextValue(this.ofTrees, OF_TREES_VALUES);
/* 1573 */       this.mc.renderGlobal.loadRenderers();
/*      */     } 
/*      */     
/* 1576 */     if (par1EnumOptions == Options.DROPPED_ITEMS) {
/*      */       
/* 1578 */       this.ofDroppedItems++;
/*      */       
/* 1580 */       if (this.ofDroppedItems > 2)
/*      */       {
/* 1582 */         this.ofDroppedItems = 0;
/*      */       }
/*      */     } 
/*      */     
/* 1586 */     if (par1EnumOptions == Options.RAIN) {
/*      */       
/* 1588 */       this.ofRain++;
/*      */       
/* 1590 */       if (this.ofRain > 3)
/*      */       {
/* 1592 */         this.ofRain = 0;
/*      */       }
/*      */     } 
/*      */     
/* 1596 */     if (par1EnumOptions == Options.ANIMATED_WATER) {
/*      */       
/* 1598 */       this.ofAnimatedWater++;
/*      */       
/* 1600 */       if (this.ofAnimatedWater == 1)
/*      */       {
/* 1602 */         this.ofAnimatedWater++;
/*      */       }
/*      */       
/* 1605 */       if (this.ofAnimatedWater > 2)
/*      */       {
/* 1607 */         this.ofAnimatedWater = 0;
/*      */       }
/*      */     } 
/*      */     
/* 1611 */     if (par1EnumOptions == Options.ANIMATED_LAVA) {
/*      */       
/* 1613 */       this.ofAnimatedLava++;
/*      */       
/* 1615 */       if (this.ofAnimatedLava == 1)
/*      */       {
/* 1617 */         this.ofAnimatedLava++;
/*      */       }
/*      */       
/* 1620 */       if (this.ofAnimatedLava > 2)
/*      */       {
/* 1622 */         this.ofAnimatedLava = 0;
/*      */       }
/*      */     } 
/*      */     
/* 1626 */     if (par1EnumOptions == Options.ANIMATED_FIRE)
/*      */     {
/* 1628 */       this.ofAnimatedFire = !this.ofAnimatedFire;
/*      */     }
/*      */     
/* 1631 */     if (par1EnumOptions == Options.ANIMATED_PORTAL)
/*      */     {
/* 1633 */       this.ofAnimatedPortal = !this.ofAnimatedPortal;
/*      */     }
/*      */     
/* 1636 */     if (par1EnumOptions == Options.ANIMATED_REDSTONE)
/*      */     {
/* 1638 */       this.ofAnimatedRedstone = !this.ofAnimatedRedstone;
/*      */     }
/*      */     
/* 1641 */     if (par1EnumOptions == Options.ANIMATED_EXPLOSION)
/*      */     {
/* 1643 */       this.ofAnimatedExplosion = !this.ofAnimatedExplosion;
/*      */     }
/*      */     
/* 1646 */     if (par1EnumOptions == Options.ANIMATED_FLAME)
/*      */     {
/* 1648 */       this.ofAnimatedFlame = !this.ofAnimatedFlame;
/*      */     }
/*      */     
/* 1651 */     if (par1EnumOptions == Options.ANIMATED_SMOKE)
/*      */     {
/* 1653 */       this.ofAnimatedSmoke = !this.ofAnimatedSmoke;
/*      */     }
/*      */     
/* 1656 */     if (par1EnumOptions == Options.VOID_PARTICLES)
/*      */     {
/* 1658 */       this.ofVoidParticles = !this.ofVoidParticles;
/*      */     }
/*      */     
/* 1661 */     if (par1EnumOptions == Options.WATER_PARTICLES)
/*      */     {
/* 1663 */       this.ofWaterParticles = !this.ofWaterParticles;
/*      */     }
/*      */     
/* 1666 */     if (par1EnumOptions == Options.PORTAL_PARTICLES)
/*      */     {
/* 1668 */       this.ofPortalParticles = !this.ofPortalParticles;
/*      */     }
/*      */     
/* 1671 */     if (par1EnumOptions == Options.POTION_PARTICLES)
/*      */     {
/* 1673 */       this.ofPotionParticles = !this.ofPotionParticles;
/*      */     }
/*      */     
/* 1676 */     if (par1EnumOptions == Options.FIREWORK_PARTICLES)
/*      */     {
/* 1678 */       this.ofFireworkParticles = !this.ofFireworkParticles;
/*      */     }
/*      */     
/* 1681 */     if (par1EnumOptions == Options.DRIPPING_WATER_LAVA)
/*      */     {
/* 1683 */       this.ofDrippingWaterLava = !this.ofDrippingWaterLava;
/*      */     }
/*      */     
/* 1686 */     if (par1EnumOptions == Options.ANIMATED_TERRAIN)
/*      */     {
/* 1688 */       this.ofAnimatedTerrain = !this.ofAnimatedTerrain;
/*      */     }
/*      */     
/* 1691 */     if (par1EnumOptions == Options.ANIMATED_TEXTURES)
/*      */     {
/* 1693 */       this.ofAnimatedTextures = !this.ofAnimatedTextures;
/*      */     }
/*      */     
/* 1696 */     if (par1EnumOptions == Options.RAIN_SPLASH)
/*      */     {
/* 1698 */       this.ofRainSplash = !this.ofRainSplash;
/*      */     }
/*      */     
/* 1701 */     if (par1EnumOptions == Options.LAGOMETER)
/*      */     {
/* 1703 */       this.ofLagometer = !this.ofLagometer;
/*      */     }
/*      */     
/* 1706 */     if (par1EnumOptions == Options.SHOW_FPS)
/*      */     {
/* 1708 */       this.ofShowFps = !this.ofShowFps;
/*      */     }
/*      */     
/* 1711 */     if (par1EnumOptions == Options.AUTOSAVE_TICKS) {
/*      */       
/* 1713 */       this.ofAutoSaveTicks *= 10;
/*      */       
/* 1715 */       if (this.ofAutoSaveTicks > 40000)
/*      */       {
/* 1717 */         this.ofAutoSaveTicks = 40;
/*      */       }
/*      */     } 
/*      */     
/* 1721 */     if (par1EnumOptions == Options.BETTER_GRASS) {
/*      */       
/* 1723 */       this.ofBetterGrass++;
/*      */       
/* 1725 */       if (this.ofBetterGrass > 3)
/*      */       {
/* 1727 */         this.ofBetterGrass = 1;
/*      */       }
/*      */       
/* 1730 */       this.mc.renderGlobal.loadRenderers();
/*      */     } 
/*      */     
/* 1733 */     if (par1EnumOptions == Options.CONNECTED_TEXTURES) {
/*      */       
/* 1735 */       this.ofConnectedTextures++;
/*      */       
/* 1737 */       if (this.ofConnectedTextures > 3)
/*      */       {
/* 1739 */         this.ofConnectedTextures = 1;
/*      */       }
/*      */       
/* 1742 */       if (this.ofConnectedTextures != 2)
/*      */       {
/* 1744 */         this.mc.refreshResources();
/*      */       }
/*      */     } 
/*      */     
/* 1748 */     if (par1EnumOptions == Options.WEATHER)
/*      */     {
/* 1750 */       this.ofWeather = !this.ofWeather;
/*      */     }
/*      */     
/* 1753 */     if (par1EnumOptions == Options.SKY)
/*      */     {
/* 1755 */       this.ofSky = !this.ofSky;
/*      */     }
/*      */     
/* 1758 */     if (par1EnumOptions == Options.STARS)
/*      */     {
/* 1760 */       this.ofStars = !this.ofStars;
/*      */     }
/*      */     
/* 1763 */     if (par1EnumOptions == Options.SUN_MOON)
/*      */     {
/* 1765 */       this.ofSunMoon = !this.ofSunMoon;
/*      */     }
/*      */     
/* 1768 */     if (par1EnumOptions == Options.VIGNETTE) {
/*      */       
/* 1770 */       this.ofVignette++;
/*      */       
/* 1772 */       if (this.ofVignette > 2)
/*      */       {
/* 1774 */         this.ofVignette = 0;
/*      */       }
/*      */     } 
/*      */     
/* 1778 */     if (par1EnumOptions == Options.CHUNK_UPDATES) {
/*      */       
/* 1780 */       this.ofChunkUpdates++;
/*      */       
/* 1782 */       if (this.ofChunkUpdates > 5)
/*      */       {
/* 1784 */         this.ofChunkUpdates = 1;
/*      */       }
/*      */     } 
/*      */     
/* 1788 */     if (par1EnumOptions == Options.CHUNK_UPDATES_DYNAMIC)
/*      */     {
/* 1790 */       this.ofChunkUpdatesDynamic = !this.ofChunkUpdatesDynamic;
/*      */     }
/*      */     
/* 1793 */     if (par1EnumOptions == Options.TIME) {
/*      */       
/* 1795 */       this.ofTime++;
/*      */       
/* 1797 */       if (this.ofTime > 2)
/*      */       {
/* 1799 */         this.ofTime = 0;
/*      */       }
/*      */     } 
/*      */     
/* 1803 */     if (par1EnumOptions == Options.CLEAR_WATER) {
/*      */       
/* 1805 */       this.ofClearWater = !this.ofClearWater;
/* 1806 */       updateWaterOpacity();
/*      */     } 
/*      */     
/* 1809 */     if (par1EnumOptions == Options.PROFILER)
/*      */     {
/* 1811 */       this.ofProfiler = !this.ofProfiler;
/*      */     }
/*      */     
/* 1814 */     if (par1EnumOptions == Options.BETTER_SNOW) {
/*      */       
/* 1816 */       this.ofBetterSnow = !this.ofBetterSnow;
/* 1817 */       this.mc.renderGlobal.loadRenderers();
/*      */     } 
/*      */     
/* 1820 */     if (par1EnumOptions == Options.SWAMP_COLORS) {
/*      */       
/* 1822 */       this.ofSwampColors = !this.ofSwampColors;
/* 1823 */       CustomColors.updateUseDefaultGrassFoliageColors();
/* 1824 */       this.mc.renderGlobal.loadRenderers();
/*      */     } 
/*      */     
/* 1827 */     if (par1EnumOptions == Options.RANDOM_MOBS) {
/*      */       
/* 1829 */       this.ofRandomMobs = !this.ofRandomMobs;
/* 1830 */       RandomMobs.resetTextures();
/*      */     } 
/*      */     
/* 1833 */     if (par1EnumOptions == Options.SMOOTH_BIOMES) {
/*      */       
/* 1835 */       this.ofSmoothBiomes = !this.ofSmoothBiomes;
/* 1836 */       CustomColors.updateUseDefaultGrassFoliageColors();
/* 1837 */       this.mc.renderGlobal.loadRenderers();
/*      */     } 
/*      */     
/* 1840 */     if (par1EnumOptions == Options.CUSTOM_FONTS) {
/*      */       
/* 1842 */       this.ofCustomFonts = !this.ofCustomFonts;
/* 1843 */       this.mc.fontRendererObj.onResourceManagerReload(Config.getResourceManager());
/* 1844 */       this.mc.standardGalacticFontRenderer.onResourceManagerReload(Config.getResourceManager());
/*      */     } 
/*      */     
/* 1847 */     if (par1EnumOptions == Options.CUSTOM_COLORS) {
/*      */       
/* 1849 */       this.ofCustomColors = !this.ofCustomColors;
/* 1850 */       CustomColors.update();
/* 1851 */       this.mc.renderGlobal.loadRenderers();
/*      */     } 
/*      */     
/* 1854 */     if (par1EnumOptions == Options.CUSTOM_ITEMS) {
/*      */       
/* 1856 */       this.ofCustomItems = !this.ofCustomItems;
/* 1857 */       this.mc.refreshResources();
/*      */     } 
/*      */     
/* 1860 */     if (par1EnumOptions == Options.CUSTOM_SKY) {
/*      */       
/* 1862 */       this.ofCustomSky = !this.ofCustomSky;
/* 1863 */       CustomSky.update();
/*      */     } 
/*      */     
/* 1866 */     if (par1EnumOptions == Options.SHOW_CAPES)
/*      */     {
/* 1868 */       this.ofShowCapes = !this.ofShowCapes;
/*      */     }
/*      */     
/* 1871 */     if (par1EnumOptions == Options.NATURAL_TEXTURES) {
/*      */       
/* 1873 */       this.ofNaturalTextures = !this.ofNaturalTextures;
/* 1874 */       NaturalTextures.update();
/* 1875 */       this.mc.renderGlobal.loadRenderers();
/*      */     } 
/*      */     
/* 1878 */     if (par1EnumOptions == Options.FAST_MATH) {
/*      */       
/* 1880 */       this.ofFastMath = !this.ofFastMath;
/* 1881 */       MathHelper.fastMath = this.ofFastMath;
/*      */     } 
/*      */     
/* 1884 */     if (par1EnumOptions == Options.FAST_RENDER) {
/*      */       
/* 1886 */       if (!this.ofFastRender && Config.isShaders()) {
/*      */         
/* 1888 */         Config.showGuiMessage(Lang.get("of.message.fr.shaders1"), Lang.get("of.message.fr.shaders2"));
/*      */         
/*      */         return;
/*      */       } 
/* 1892 */       this.ofFastRender = !this.ofFastRender;
/*      */       
/* 1894 */       if (this.ofFastRender)
/*      */       {
/* 1896 */         this.mc.entityRenderer.stopUseShader();
/*      */       }
/*      */       
/* 1899 */       Config.updateFramebufferSize();
/*      */     } 
/*      */     
/* 1902 */     if (par1EnumOptions == Options.TRANSLUCENT_BLOCKS) {
/*      */       
/* 1904 */       if (this.ofTranslucentBlocks == 0) {
/*      */         
/* 1906 */         this.ofTranslucentBlocks = 1;
/*      */       }
/* 1908 */       else if (this.ofTranslucentBlocks == 1) {
/*      */         
/* 1910 */         this.ofTranslucentBlocks = 2;
/*      */       }
/* 1912 */       else if (this.ofTranslucentBlocks == 2) {
/*      */         
/* 1914 */         this.ofTranslucentBlocks = 0;
/*      */       }
/*      */       else {
/*      */         
/* 1918 */         this.ofTranslucentBlocks = 0;
/*      */       } 
/*      */       
/* 1921 */       this.mc.renderGlobal.loadRenderers();
/*      */     } 
/*      */     
/* 1924 */     if (par1EnumOptions == Options.LAZY_CHUNK_LOADING) {
/*      */       
/* 1926 */       this.ofLazyChunkLoading = !this.ofLazyChunkLoading;
/* 1927 */       Config.updateAvailableProcessors();
/*      */       
/* 1929 */       if (!Config.isSingleProcessor())
/*      */       {
/* 1931 */         this.ofLazyChunkLoading = false;
/*      */       }
/*      */       
/* 1934 */       this.mc.renderGlobal.loadRenderers();
/*      */     } 
/*      */     
/* 1937 */     if (par1EnumOptions == Options.FULLSCREEN_MODE) {
/*      */       
/* 1939 */       List<String> modeList = Arrays.asList(Config.getFullscreenModes());
/*      */       
/* 1941 */       if (this.ofFullscreenMode.equals("Default")) {
/*      */         
/* 1943 */         this.ofFullscreenMode = modeList.get(0);
/*      */       }
/*      */       else {
/*      */         
/* 1947 */         int index = modeList.indexOf(this.ofFullscreenMode);
/*      */         
/* 1949 */         if (index < 0) {
/*      */           
/* 1951 */           this.ofFullscreenMode = "Default";
/*      */         }
/*      */         else {
/*      */           
/* 1955 */           index++;
/*      */           
/* 1957 */           if (index >= modeList.size()) {
/*      */             
/* 1959 */             this.ofFullscreenMode = "Default";
/*      */           }
/*      */           else {
/*      */             
/* 1963 */             this.ofFullscreenMode = modeList.get(index);
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     } 
/*      */     
/* 1969 */     if (par1EnumOptions == Options.DYNAMIC_FOV)
/*      */     {
/* 1971 */       this.ofDynamicFov = !this.ofDynamicFov;
/*      */     }
/*      */     
/* 1974 */     if (par1EnumOptions == Options.DYNAMIC_LIGHTS) {
/*      */       
/* 1976 */       this.ofDynamicLights = nextValue(this.ofDynamicLights, OF_DYNAMIC_LIGHTS);
/* 1977 */       DynamicLights.removeLights(this.mc.renderGlobal);
/*      */     } 
/*      */     
/* 1980 */     if (par1EnumOptions == Options.HELD_ITEM_TOOLTIPS)
/*      */     {
/* 1982 */       this.heldItemTooltips = !this.heldItemTooltips;
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   private String getKeyBindingOF(Options par1EnumOptions) {
/* 1988 */     String var2 = String.valueOf(I18n.format(par1EnumOptions.getEnumString(), new Object[0])) + ": ";
/*      */     
/* 1990 */     if (var2 == null)
/*      */     {
/* 1992 */       var2 = par1EnumOptions.getEnumString();
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 1997 */     if (par1EnumOptions == Options.RENDER_DISTANCE) {
/*      */       
/* 1999 */       int var61 = (int)getOptionFloatValue(par1EnumOptions);
/* 2000 */       String str = I18n.format("options.renderDistance.tiny", new Object[0]);
/* 2001 */       byte baseDist = 2;
/*      */       
/* 2003 */       if (var61 >= 4) {
/*      */         
/* 2005 */         str = I18n.format("options.renderDistance.short", new Object[0]);
/* 2006 */         baseDist = 4;
/*      */       } 
/*      */       
/* 2009 */       if (var61 >= 8) {
/*      */         
/* 2011 */         str = I18n.format("options.renderDistance.normal", new Object[0]);
/* 2012 */         baseDist = 8;
/*      */       } 
/*      */       
/* 2015 */       if (var61 >= 16) {
/*      */         
/* 2017 */         str = I18n.format("options.renderDistance.far", new Object[0]);
/* 2018 */         baseDist = 16;
/*      */       } 
/*      */       
/* 2021 */       if (var61 >= 32) {
/*      */         
/* 2023 */         str = Lang.get("of.options.renderDistance.extreme");
/* 2024 */         baseDist = 32;
/*      */       } 
/*      */       
/* 2027 */       int diff = this.renderDistanceChunks - baseDist;
/* 2028 */       String descr = str;
/*      */       
/* 2030 */       if (diff > 0)
/*      */       {
/* 2032 */         descr = String.valueOf(str) + "+";
/*      */       }
/*      */       
/* 2035 */       return String.valueOf(var2) + var61 + " " + descr;
/*      */     } 
/* 2037 */     if (par1EnumOptions == Options.FOG_FANCY) {
/*      */       
/* 2039 */       switch (this.ofFogType) {
/*      */         
/*      */         case 1:
/* 2042 */           return String.valueOf(var2) + Lang.getFast();
/*      */         
/*      */         case 2:
/* 2045 */           return String.valueOf(var2) + Lang.getFancy();
/*      */         
/*      */         case 3:
/* 2048 */           return String.valueOf(var2) + Lang.getOff();
/*      */       } 
/*      */       
/* 2051 */       return String.valueOf(var2) + Lang.getOff();
/*      */     } 
/*      */     
/* 2054 */     if (par1EnumOptions == Options.FOG_START)
/*      */     {
/* 2056 */       return String.valueOf(var2) + this.ofFogStart;
/*      */     }
/* 2058 */     if (par1EnumOptions == Options.MIPMAP_TYPE) {
/*      */       
/* 2060 */       switch (this.ofMipmapType) {
/*      */         
/*      */         case 0:
/* 2063 */           return String.valueOf(var2) + Lang.get("of.options.mipmap.nearest");
/*      */         
/*      */         case 1:
/* 2066 */           return String.valueOf(var2) + Lang.get("of.options.mipmap.linear");
/*      */         
/*      */         case 2:
/* 2069 */           return String.valueOf(var2) + Lang.get("of.options.mipmap.bilinear");
/*      */         
/*      */         case 3:
/* 2072 */           return String.valueOf(var2) + Lang.get("of.options.mipmap.trilinear");
/*      */       } 
/*      */       
/* 2075 */       return String.valueOf(var2) + "of.options.mipmap.nearest";
/*      */     } 
/*      */     
/* 2078 */     if (par1EnumOptions == Options.SMOOTH_FPS)
/*      */     {
/* 2080 */       return this.ofSmoothFps ? (String.valueOf(var2) + Lang.getOn()) : (String.valueOf(var2) + Lang.getOff());
/*      */     }
/* 2082 */     if (par1EnumOptions == Options.SMOOTH_WORLD)
/*      */     {
/* 2084 */       return this.ofSmoothWorld ? (String.valueOf(var2) + Lang.getOn()) : (String.valueOf(var2) + Lang.getOff());
/*      */     }
/* 2086 */     if (par1EnumOptions == Options.CLOUDS) {
/*      */       
/* 2088 */       switch (this.ofClouds) {
/*      */         
/*      */         case 1:
/* 2091 */           return String.valueOf(var2) + Lang.getFast();
/*      */         
/*      */         case 2:
/* 2094 */           return String.valueOf(var2) + Lang.getFancy();
/*      */         
/*      */         case 3:
/* 2097 */           return String.valueOf(var2) + Lang.getOff();
/*      */       } 
/*      */       
/* 2100 */       return String.valueOf(var2) + Lang.getDefault();
/*      */     } 
/*      */     
/* 2103 */     if (par1EnumOptions == Options.TREES) {
/*      */       
/* 2105 */       switch (this.ofTrees) {
/*      */         
/*      */         case 1:
/* 2108 */           return String.valueOf(var2) + Lang.getFast();
/*      */         
/*      */         case 2:
/* 2111 */           return String.valueOf(var2) + Lang.getFancy();
/*      */ 
/*      */         
/*      */         default:
/* 2115 */           return String.valueOf(var2) + Lang.getDefault();
/*      */         case 4:
/*      */           break;
/* 2118 */       }  return String.valueOf(var2) + Lang.get("of.general.smart");
/*      */     } 
/*      */     
/* 2121 */     if (par1EnumOptions == Options.DROPPED_ITEMS) {
/*      */       
/* 2123 */       switch (this.ofDroppedItems) {
/*      */         
/*      */         case 1:
/* 2126 */           return String.valueOf(var2) + Lang.getFast();
/*      */         
/*      */         case 2:
/* 2129 */           return String.valueOf(var2) + Lang.getFancy();
/*      */       } 
/*      */       
/* 2132 */       return String.valueOf(var2) + Lang.getDefault();
/*      */     } 
/*      */     
/* 2135 */     if (par1EnumOptions == Options.RAIN) {
/*      */       
/* 2137 */       switch (this.ofRain) {
/*      */         
/*      */         case 1:
/* 2140 */           return String.valueOf(var2) + Lang.getFast();
/*      */         
/*      */         case 2:
/* 2143 */           return String.valueOf(var2) + Lang.getFancy();
/*      */         
/*      */         case 3:
/* 2146 */           return String.valueOf(var2) + Lang.getOff();
/*      */       } 
/*      */       
/* 2149 */       return String.valueOf(var2) + Lang.getDefault();
/*      */     } 
/*      */     
/* 2152 */     if (par1EnumOptions == Options.ANIMATED_WATER) {
/*      */       
/* 2154 */       switch (this.ofAnimatedWater) {
/*      */         
/*      */         case 1:
/* 2157 */           return String.valueOf(var2) + Lang.get("of.options.animation.dynamic");
/*      */         
/*      */         case 2:
/* 2160 */           return String.valueOf(var2) + Lang.getOff();
/*      */       } 
/*      */       
/* 2163 */       return String.valueOf(var2) + Lang.getOn();
/*      */     } 
/*      */     
/* 2166 */     if (par1EnumOptions == Options.ANIMATED_LAVA) {
/*      */       
/* 2168 */       switch (this.ofAnimatedLava) {
/*      */         
/*      */         case 1:
/* 2171 */           return String.valueOf(var2) + Lang.get("of.options.animation.dynamic");
/*      */         
/*      */         case 2:
/* 2174 */           return String.valueOf(var2) + Lang.getOff();
/*      */       } 
/*      */       
/* 2177 */       return String.valueOf(var2) + Lang.getOn();
/*      */     } 
/*      */     
/* 2180 */     if (par1EnumOptions == Options.ANIMATED_FIRE)
/*      */     {
/* 2182 */       return this.ofAnimatedFire ? (String.valueOf(var2) + Lang.getOn()) : (String.valueOf(var2) + Lang.getOff());
/*      */     }
/* 2184 */     if (par1EnumOptions == Options.ANIMATED_PORTAL)
/*      */     {
/* 2186 */       return this.ofAnimatedPortal ? (String.valueOf(var2) + Lang.getOn()) : (String.valueOf(var2) + Lang.getOff());
/*      */     }
/* 2188 */     if (par1EnumOptions == Options.ANIMATED_REDSTONE)
/*      */     {
/* 2190 */       return this.ofAnimatedRedstone ? (String.valueOf(var2) + Lang.getOn()) : (String.valueOf(var2) + Lang.getOff());
/*      */     }
/* 2192 */     if (par1EnumOptions == Options.ANIMATED_EXPLOSION)
/*      */     {
/* 2194 */       return this.ofAnimatedExplosion ? (String.valueOf(var2) + Lang.getOn()) : (String.valueOf(var2) + Lang.getOff());
/*      */     }
/* 2196 */     if (par1EnumOptions == Options.ANIMATED_FLAME)
/*      */     {
/* 2198 */       return this.ofAnimatedFlame ? (String.valueOf(var2) + Lang.getOn()) : (String.valueOf(var2) + Lang.getOff());
/*      */     }
/* 2200 */     if (par1EnumOptions == Options.ANIMATED_SMOKE)
/*      */     {
/* 2202 */       return this.ofAnimatedSmoke ? (String.valueOf(var2) + Lang.getOn()) : (String.valueOf(var2) + Lang.getOff());
/*      */     }
/* 2204 */     if (par1EnumOptions == Options.VOID_PARTICLES)
/*      */     {
/* 2206 */       return this.ofVoidParticles ? (String.valueOf(var2) + Lang.getOn()) : (String.valueOf(var2) + Lang.getOff());
/*      */     }
/* 2208 */     if (par1EnumOptions == Options.WATER_PARTICLES)
/*      */     {
/* 2210 */       return this.ofWaterParticles ? (String.valueOf(var2) + Lang.getOn()) : (String.valueOf(var2) + Lang.getOff());
/*      */     }
/* 2212 */     if (par1EnumOptions == Options.PORTAL_PARTICLES)
/*      */     {
/* 2214 */       return this.ofPortalParticles ? (String.valueOf(var2) + Lang.getOn()) : (String.valueOf(var2) + Lang.getOff());
/*      */     }
/* 2216 */     if (par1EnumOptions == Options.POTION_PARTICLES)
/*      */     {
/* 2218 */       return this.ofPotionParticles ? (String.valueOf(var2) + Lang.getOn()) : (String.valueOf(var2) + Lang.getOff());
/*      */     }
/* 2220 */     if (par1EnumOptions == Options.FIREWORK_PARTICLES)
/*      */     {
/* 2222 */       return this.ofFireworkParticles ? (String.valueOf(var2) + Lang.getOn()) : (String.valueOf(var2) + Lang.getOff());
/*      */     }
/* 2224 */     if (par1EnumOptions == Options.DRIPPING_WATER_LAVA)
/*      */     {
/* 2226 */       return this.ofDrippingWaterLava ? (String.valueOf(var2) + Lang.getOn()) : (String.valueOf(var2) + Lang.getOff());
/*      */     }
/* 2228 */     if (par1EnumOptions == Options.ANIMATED_TERRAIN)
/*      */     {
/* 2230 */       return this.ofAnimatedTerrain ? (String.valueOf(var2) + Lang.getOn()) : (String.valueOf(var2) + Lang.getOff());
/*      */     }
/* 2232 */     if (par1EnumOptions == Options.ANIMATED_TEXTURES)
/*      */     {
/* 2234 */       return this.ofAnimatedTextures ? (String.valueOf(var2) + Lang.getOn()) : (String.valueOf(var2) + Lang.getOff());
/*      */     }
/* 2236 */     if (par1EnumOptions == Options.RAIN_SPLASH)
/*      */     {
/* 2238 */       return this.ofRainSplash ? (String.valueOf(var2) + Lang.getOn()) : (String.valueOf(var2) + Lang.getOff());
/*      */     }
/* 2240 */     if (par1EnumOptions == Options.LAGOMETER)
/*      */     {
/* 2242 */       return this.ofLagometer ? (String.valueOf(var2) + Lang.getOn()) : (String.valueOf(var2) + Lang.getOff());
/*      */     }
/* 2244 */     if (par1EnumOptions == Options.SHOW_FPS)
/*      */     {
/* 2246 */       return this.ofShowFps ? (String.valueOf(var2) + Lang.getOn()) : (String.valueOf(var2) + Lang.getOff());
/*      */     }
/* 2248 */     if (par1EnumOptions == Options.AUTOSAVE_TICKS)
/*      */     {
/* 2250 */       return (this.ofAutoSaveTicks <= 40) ? (String.valueOf(var2) + Lang.get("of.options.save.default")) : ((this.ofAutoSaveTicks <= 400) ? (String.valueOf(var2) + Lang.get("of.options.save.20s")) : ((this.ofAutoSaveTicks <= 4000) ? (String.valueOf(var2) + Lang.get("of.options.save.3min")) : (String.valueOf(var2) + Lang.get("of.options.save.30min"))));
/*      */     }
/* 2252 */     if (par1EnumOptions == Options.BETTER_GRASS) {
/*      */       
/* 2254 */       switch (this.ofBetterGrass) {
/*      */         
/*      */         case 1:
/* 2257 */           return String.valueOf(var2) + Lang.getFast();
/*      */         
/*      */         case 2:
/* 2260 */           return String.valueOf(var2) + Lang.getFancy();
/*      */       } 
/*      */       
/* 2263 */       return String.valueOf(var2) + Lang.getOff();
/*      */     } 
/*      */     
/* 2266 */     if (par1EnumOptions == Options.CONNECTED_TEXTURES) {
/*      */       
/* 2268 */       switch (this.ofConnectedTextures) {
/*      */         
/*      */         case 1:
/* 2271 */           return String.valueOf(var2) + Lang.getFast();
/*      */         
/*      */         case 2:
/* 2274 */           return String.valueOf(var2) + Lang.getFancy();
/*      */       } 
/*      */       
/* 2277 */       return String.valueOf(var2) + Lang.getOff();
/*      */     } 
/*      */     
/* 2280 */     if (par1EnumOptions == Options.WEATHER)
/*      */     {
/* 2282 */       return this.ofWeather ? (String.valueOf(var2) + Lang.getOn()) : (String.valueOf(var2) + Lang.getOff());
/*      */     }
/* 2284 */     if (par1EnumOptions == Options.SKY)
/*      */     {
/* 2286 */       return this.ofSky ? (String.valueOf(var2) + Lang.getOn()) : (String.valueOf(var2) + Lang.getOff());
/*      */     }
/* 2288 */     if (par1EnumOptions == Options.STARS)
/*      */     {
/* 2290 */       return this.ofStars ? (String.valueOf(var2) + Lang.getOn()) : (String.valueOf(var2) + Lang.getOff());
/*      */     }
/* 2292 */     if (par1EnumOptions == Options.SUN_MOON)
/*      */     {
/* 2294 */       return this.ofSunMoon ? (String.valueOf(var2) + Lang.getOn()) : (String.valueOf(var2) + Lang.getOff());
/*      */     }
/* 2296 */     if (par1EnumOptions == Options.VIGNETTE) {
/*      */       
/* 2298 */       switch (this.ofVignette) {
/*      */         
/*      */         case 1:
/* 2301 */           return String.valueOf(var2) + Lang.getFast();
/*      */         
/*      */         case 2:
/* 2304 */           return String.valueOf(var2) + Lang.getFancy();
/*      */       } 
/*      */       
/* 2307 */       return String.valueOf(var2) + Lang.getDefault();
/*      */     } 
/*      */     
/* 2310 */     if (par1EnumOptions == Options.CHUNK_UPDATES)
/*      */     {
/* 2312 */       return String.valueOf(var2) + this.ofChunkUpdates;
/*      */     }
/* 2314 */     if (par1EnumOptions == Options.CHUNK_UPDATES_DYNAMIC)
/*      */     {
/* 2316 */       return this.ofChunkUpdatesDynamic ? (String.valueOf(var2) + Lang.getOn()) : (String.valueOf(var2) + Lang.getOff());
/*      */     }
/* 2318 */     if (par1EnumOptions == Options.TIME)
/*      */     {
/* 2320 */       return (this.ofTime == 1) ? (String.valueOf(var2) + Lang.get("of.options.time.dayOnly")) : ((this.ofTime == 2) ? (String.valueOf(var2) + Lang.get("of.options.time.nightOnly")) : (String.valueOf(var2) + Lang.getDefault()));
/*      */     }
/* 2322 */     if (par1EnumOptions == Options.CLEAR_WATER)
/*      */     {
/* 2324 */       return this.ofClearWater ? (String.valueOf(var2) + Lang.getOn()) : (String.valueOf(var2) + Lang.getOff());
/*      */     }
/* 2326 */     if (par1EnumOptions == Options.AA_LEVEL) {
/*      */       
/* 2328 */       String var62 = "";
/*      */       
/* 2330 */       if (this.ofAaLevel != Config.getAntialiasingLevel())
/*      */       {
/* 2332 */         var62 = " (" + Lang.get("of.general.restart") + ")";
/*      */       }
/*      */       
/* 2335 */       return (this.ofAaLevel == 0) ? (String.valueOf(var2) + Lang.getOff() + var62) : (String.valueOf(var2) + this.ofAaLevel + var62);
/*      */     } 
/* 2337 */     if (par1EnumOptions == Options.AF_LEVEL)
/*      */     {
/* 2339 */       return (this.ofAfLevel == 1) ? (String.valueOf(var2) + Lang.getOff()) : (String.valueOf(var2) + this.ofAfLevel);
/*      */     }
/* 2341 */     if (par1EnumOptions == Options.PROFILER)
/*      */     {
/* 2343 */       return this.ofProfiler ? (String.valueOf(var2) + Lang.getOn()) : (String.valueOf(var2) + Lang.getOff());
/*      */     }
/* 2345 */     if (par1EnumOptions == Options.BETTER_SNOW)
/*      */     {
/* 2347 */       return this.ofBetterSnow ? (String.valueOf(var2) + Lang.getOn()) : (String.valueOf(var2) + Lang.getOff());
/*      */     }
/* 2349 */     if (par1EnumOptions == Options.SWAMP_COLORS)
/*      */     {
/* 2351 */       return this.ofSwampColors ? (String.valueOf(var2) + Lang.getOn()) : (String.valueOf(var2) + Lang.getOff());
/*      */     }
/* 2353 */     if (par1EnumOptions == Options.RANDOM_MOBS)
/*      */     {
/* 2355 */       return this.ofRandomMobs ? (String.valueOf(var2) + Lang.getOn()) : (String.valueOf(var2) + Lang.getOff());
/*      */     }
/* 2357 */     if (par1EnumOptions == Options.SMOOTH_BIOMES)
/*      */     {
/* 2359 */       return this.ofSmoothBiomes ? (String.valueOf(var2) + Lang.getOn()) : (String.valueOf(var2) + Lang.getOff());
/*      */     }
/* 2361 */     if (par1EnumOptions == Options.CUSTOM_FONTS)
/*      */     {
/* 2363 */       return this.ofCustomFonts ? (String.valueOf(var2) + Lang.getOn()) : (String.valueOf(var2) + Lang.getOff());
/*      */     }
/* 2365 */     if (par1EnumOptions == Options.CUSTOM_COLORS)
/*      */     {
/* 2367 */       return this.ofCustomColors ? (String.valueOf(var2) + Lang.getOn()) : (String.valueOf(var2) + Lang.getOff());
/*      */     }
/* 2369 */     if (par1EnumOptions == Options.CUSTOM_SKY)
/*      */     {
/* 2371 */       return this.ofCustomSky ? (String.valueOf(var2) + Lang.getOn()) : (String.valueOf(var2) + Lang.getOff());
/*      */     }
/* 2373 */     if (par1EnumOptions == Options.SHOW_CAPES)
/*      */     {
/* 2375 */       return this.ofShowCapes ? (String.valueOf(var2) + Lang.getOn()) : (String.valueOf(var2) + Lang.getOff());
/*      */     }
/* 2377 */     if (par1EnumOptions == Options.CUSTOM_ITEMS)
/*      */     {
/* 2379 */       return this.ofCustomItems ? (String.valueOf(var2) + Lang.getOn()) : (String.valueOf(var2) + Lang.getOff());
/*      */     }
/* 2381 */     if (par1EnumOptions == Options.NATURAL_TEXTURES)
/*      */     {
/* 2383 */       return this.ofNaturalTextures ? (String.valueOf(var2) + Lang.getOn()) : (String.valueOf(var2) + Lang.getOff());
/*      */     }
/* 2385 */     if (par1EnumOptions == Options.FAST_MATH)
/*      */     {
/* 2387 */       return this.ofFastMath ? (String.valueOf(var2) + Lang.getOn()) : (String.valueOf(var2) + Lang.getOff());
/*      */     }
/* 2389 */     if (par1EnumOptions == Options.FAST_RENDER)
/*      */     {
/* 2391 */       return this.ofFastRender ? (String.valueOf(var2) + Lang.getOn()) : (String.valueOf(var2) + Lang.getOff());
/*      */     }
/* 2393 */     if (par1EnumOptions == Options.TRANSLUCENT_BLOCKS)
/*      */     {
/* 2395 */       return (this.ofTranslucentBlocks == 1) ? (String.valueOf(var2) + Lang.getFast()) : ((this.ofTranslucentBlocks == 2) ? (String.valueOf(var2) + Lang.getFancy()) : (String.valueOf(var2) + Lang.getDefault()));
/*      */     }
/* 2397 */     if (par1EnumOptions == Options.LAZY_CHUNK_LOADING)
/*      */     {
/* 2399 */       return this.ofLazyChunkLoading ? (String.valueOf(var2) + Lang.getOn()) : (String.valueOf(var2) + Lang.getOff());
/*      */     }
/* 2401 */     if (par1EnumOptions == Options.DYNAMIC_FOV)
/*      */     {
/* 2403 */       return this.ofDynamicFov ? (String.valueOf(var2) + Lang.getOn()) : (String.valueOf(var2) + Lang.getOff());
/*      */     }
/* 2405 */     if (par1EnumOptions == Options.DYNAMIC_LIGHTS) {
/*      */       
/* 2407 */       int var61 = indexOf(this.ofDynamicLights, OF_DYNAMIC_LIGHTS);
/* 2408 */       return String.valueOf(var2) + getTranslation(KEYS_DYNAMIC_LIGHTS, var61);
/*      */     } 
/* 2410 */     if (par1EnumOptions == Options.FULLSCREEN_MODE)
/*      */     {
/* 2412 */       return this.ofFullscreenMode.equals("Default") ? (String.valueOf(var2) + Lang.getDefault()) : (String.valueOf(var2) + this.ofFullscreenMode);
/*      */     }
/* 2414 */     if (par1EnumOptions == Options.HELD_ITEM_TOOLTIPS)
/*      */     {
/* 2416 */       return this.heldItemTooltips ? (String.valueOf(var2) + Lang.getOn()) : (String.valueOf(var2) + Lang.getOff());
/*      */     }
/* 2418 */     if (par1EnumOptions == Options.FRAMERATE_LIMIT) {
/*      */       
/* 2420 */       float var6 = getOptionFloatValue(par1EnumOptions);
/* 2421 */       return (var6 == 0.0F) ? (String.valueOf(var2) + Lang.get("of.options.framerateLimit.vsync")) : ((var6 == par1EnumOptions.valueMax) ? (String.valueOf(var2) + I18n.format("options.framerateLimit.max", new Object[0])) : (String.valueOf(var2) + (int)var6 + " fps"));
/*      */     } 
/*      */ 
/*      */     
/* 2425 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void loadOfOptions() {
/*      */     try {
/* 2433 */       File exception = this.optionsFileOF;
/*      */       
/* 2435 */       if (!exception.exists())
/*      */       {
/* 2437 */         exception = this.optionsFile;
/*      */       }
/*      */       
/* 2440 */       if (!exception.exists()) {
/*      */         return;
/*      */       }
/*      */ 
/*      */       
/* 2445 */       BufferedReader bufferedreader = new BufferedReader(new FileReader(exception));
/* 2446 */       String s = "";
/*      */       
/* 2448 */       while ((s = bufferedreader.readLine()) != null) {
/*      */ 
/*      */         
/*      */         try {
/* 2452 */           String[] exception1 = s.split(":");
/*      */           
/* 2454 */           if (exception1[0].equals("ofRenderDistanceChunks") && exception1.length >= 2) {
/*      */             
/* 2456 */             this.renderDistanceChunks = Integer.valueOf(exception1[1]).intValue();
/* 2457 */             this.renderDistanceChunks = Config.limit(this.renderDistanceChunks, 2, 32);
/*      */           } 
/*      */           
/* 2460 */           if (exception1[0].equals("ofFogType") && exception1.length >= 2) {
/*      */             
/* 2462 */             this.ofFogType = Integer.valueOf(exception1[1]).intValue();
/* 2463 */             this.ofFogType = Config.limit(this.ofFogType, 1, 3);
/*      */           } 
/*      */           
/* 2466 */           if (exception1[0].equals("ofFogStart") && exception1.length >= 2) {
/*      */             
/* 2468 */             this.ofFogStart = Float.valueOf(exception1[1]).floatValue();
/*      */             
/* 2470 */             if (this.ofFogStart < 0.2F)
/*      */             {
/* 2472 */               this.ofFogStart = 0.2F;
/*      */             }
/*      */             
/* 2475 */             if (this.ofFogStart > 0.81F)
/*      */             {
/* 2477 */               this.ofFogStart = 0.8F;
/*      */             }
/*      */           } 
/*      */           
/* 2481 */           if (exception1[0].equals("ofMipmapType") && exception1.length >= 2) {
/*      */             
/* 2483 */             this.ofMipmapType = Integer.valueOf(exception1[1]).intValue();
/* 2484 */             this.ofMipmapType = Config.limit(this.ofMipmapType, 0, 3);
/*      */           } 
/*      */           
/* 2487 */           if (exception1[0].equals("ofOcclusionFancy") && exception1.length >= 2)
/*      */           {
/* 2489 */             this.ofOcclusionFancy = Boolean.valueOf(exception1[1]).booleanValue();
/*      */           }
/*      */           
/* 2492 */           if (exception1[0].equals("ofSmoothFps") && exception1.length >= 2)
/*      */           {
/* 2494 */             this.ofSmoothFps = Boolean.valueOf(exception1[1]).booleanValue();
/*      */           }
/*      */           
/* 2497 */           if (exception1[0].equals("ofSmoothWorld") && exception1.length >= 2)
/*      */           {
/* 2499 */             this.ofSmoothWorld = Boolean.valueOf(exception1[1]).booleanValue();
/*      */           }
/*      */           
/* 2502 */           if (exception1[0].equals("ofAoLevel") && exception1.length >= 2) {
/*      */             
/* 2504 */             this.ofAoLevel = Float.valueOf(exception1[1]).floatValue();
/* 2505 */             this.ofAoLevel = Config.limit(this.ofAoLevel, 0.0F, 1.0F);
/*      */           } 
/*      */           
/* 2508 */           if (exception1[0].equals("ofClouds") && exception1.length >= 2) {
/*      */             
/* 2510 */             this.ofClouds = Integer.valueOf(exception1[1]).intValue();
/* 2511 */             this.ofClouds = Config.limit(this.ofClouds, 0, 3);
/* 2512 */             updateRenderClouds();
/*      */           } 
/*      */           
/* 2515 */           if (exception1[0].equals("ofCloudsHeight") && exception1.length >= 2) {
/*      */             
/* 2517 */             this.ofCloudsHeight = Float.valueOf(exception1[1]).floatValue();
/* 2518 */             this.ofCloudsHeight = Config.limit(this.ofCloudsHeight, 0.0F, 1.0F);
/*      */           } 
/*      */           
/* 2521 */           if (exception1[0].equals("ofTrees") && exception1.length >= 2) {
/*      */             
/* 2523 */             this.ofTrees = Integer.valueOf(exception1[1]).intValue();
/* 2524 */             this.ofTrees = limit(this.ofTrees, OF_TREES_VALUES);
/*      */           } 
/*      */           
/* 2527 */           if (exception1[0].equals("ofDroppedItems") && exception1.length >= 2) {
/*      */             
/* 2529 */             this.ofDroppedItems = Integer.valueOf(exception1[1]).intValue();
/* 2530 */             this.ofDroppedItems = Config.limit(this.ofDroppedItems, 0, 2);
/*      */           } 
/*      */           
/* 2533 */           if (exception1[0].equals("ofRain") && exception1.length >= 2) {
/*      */             
/* 2535 */             this.ofRain = Integer.valueOf(exception1[1]).intValue();
/* 2536 */             this.ofRain = Config.limit(this.ofRain, 0, 3);
/*      */           } 
/*      */           
/* 2539 */           if (exception1[0].equals("ofAnimatedWater") && exception1.length >= 2) {
/*      */             
/* 2541 */             this.ofAnimatedWater = Integer.valueOf(exception1[1]).intValue();
/* 2542 */             this.ofAnimatedWater = Config.limit(this.ofAnimatedWater, 0, 2);
/*      */           } 
/*      */           
/* 2545 */           if (exception1[0].equals("ofAnimatedLava") && exception1.length >= 2) {
/*      */             
/* 2547 */             this.ofAnimatedLava = Integer.valueOf(exception1[1]).intValue();
/* 2548 */             this.ofAnimatedLava = Config.limit(this.ofAnimatedLava, 0, 2);
/*      */           } 
/*      */           
/* 2551 */           if (exception1[0].equals("ofAnimatedFire") && exception1.length >= 2)
/*      */           {
/* 2553 */             this.ofAnimatedFire = Boolean.valueOf(exception1[1]).booleanValue();
/*      */           }
/*      */           
/* 2556 */           if (exception1[0].equals("ofAnimatedPortal") && exception1.length >= 2)
/*      */           {
/* 2558 */             this.ofAnimatedPortal = Boolean.valueOf(exception1[1]).booleanValue();
/*      */           }
/*      */           
/* 2561 */           if (exception1[0].equals("ofAnimatedRedstone") && exception1.length >= 2)
/*      */           {
/* 2563 */             this.ofAnimatedRedstone = Boolean.valueOf(exception1[1]).booleanValue();
/*      */           }
/*      */           
/* 2566 */           if (exception1[0].equals("ofAnimatedExplosion") && exception1.length >= 2)
/*      */           {
/* 2568 */             this.ofAnimatedExplosion = Boolean.valueOf(exception1[1]).booleanValue();
/*      */           }
/*      */           
/* 2571 */           if (exception1[0].equals("ofAnimatedFlame") && exception1.length >= 2)
/*      */           {
/* 2573 */             this.ofAnimatedFlame = Boolean.valueOf(exception1[1]).booleanValue();
/*      */           }
/*      */           
/* 2576 */           if (exception1[0].equals("ofAnimatedSmoke") && exception1.length >= 2)
/*      */           {
/* 2578 */             this.ofAnimatedSmoke = Boolean.valueOf(exception1[1]).booleanValue();
/*      */           }
/*      */           
/* 2581 */           if (exception1[0].equals("ofVoidParticles") && exception1.length >= 2)
/*      */           {
/* 2583 */             this.ofVoidParticles = Boolean.valueOf(exception1[1]).booleanValue();
/*      */           }
/*      */           
/* 2586 */           if (exception1[0].equals("ofWaterParticles") && exception1.length >= 2)
/*      */           {
/* 2588 */             this.ofWaterParticles = Boolean.valueOf(exception1[1]).booleanValue();
/*      */           }
/*      */           
/* 2591 */           if (exception1[0].equals("ofPortalParticles") && exception1.length >= 2)
/*      */           {
/* 2593 */             this.ofPortalParticles = Boolean.valueOf(exception1[1]).booleanValue();
/*      */           }
/*      */           
/* 2596 */           if (exception1[0].equals("ofPotionParticles") && exception1.length >= 2)
/*      */           {
/* 2598 */             this.ofPotionParticles = Boolean.valueOf(exception1[1]).booleanValue();
/*      */           }
/*      */           
/* 2601 */           if (exception1[0].equals("ofFireworkParticles") && exception1.length >= 2)
/*      */           {
/* 2603 */             this.ofFireworkParticles = Boolean.valueOf(exception1[1]).booleanValue();
/*      */           }
/*      */           
/* 2606 */           if (exception1[0].equals("ofDrippingWaterLava") && exception1.length >= 2)
/*      */           {
/* 2608 */             this.ofDrippingWaterLava = Boolean.valueOf(exception1[1]).booleanValue();
/*      */           }
/*      */           
/* 2611 */           if (exception1[0].equals("ofAnimatedTerrain") && exception1.length >= 2)
/*      */           {
/* 2613 */             this.ofAnimatedTerrain = Boolean.valueOf(exception1[1]).booleanValue();
/*      */           }
/*      */           
/* 2616 */           if (exception1[0].equals("ofAnimatedTextures") && exception1.length >= 2)
/*      */           {
/* 2618 */             this.ofAnimatedTextures = Boolean.valueOf(exception1[1]).booleanValue();
/*      */           }
/*      */           
/* 2621 */           if (exception1[0].equals("ofRainSplash") && exception1.length >= 2)
/*      */           {
/* 2623 */             this.ofRainSplash = Boolean.valueOf(exception1[1]).booleanValue();
/*      */           }
/*      */           
/* 2626 */           if (exception1[0].equals("ofLagometer") && exception1.length >= 2)
/*      */           {
/* 2628 */             this.ofLagometer = Boolean.valueOf(exception1[1]).booleanValue();
/*      */           }
/*      */           
/* 2631 */           if (exception1[0].equals("ofShowFps") && exception1.length >= 2)
/*      */           {
/* 2633 */             this.ofShowFps = Boolean.valueOf(exception1[1]).booleanValue();
/*      */           }
/*      */           
/* 2636 */           if (exception1[0].equals("ofAutoSaveTicks") && exception1.length >= 2) {
/*      */             
/* 2638 */             this.ofAutoSaveTicks = Integer.valueOf(exception1[1]).intValue();
/* 2639 */             this.ofAutoSaveTicks = Config.limit(this.ofAutoSaveTicks, 40, 40000);
/*      */           } 
/*      */           
/* 2642 */           if (exception1[0].equals("ofBetterGrass") && exception1.length >= 2) {
/*      */             
/* 2644 */             this.ofBetterGrass = Integer.valueOf(exception1[1]).intValue();
/* 2645 */             this.ofBetterGrass = Config.limit(this.ofBetterGrass, 1, 3);
/*      */           } 
/*      */           
/* 2648 */           if (exception1[0].equals("ofConnectedTextures") && exception1.length >= 2) {
/*      */             
/* 2650 */             this.ofConnectedTextures = Integer.valueOf(exception1[1]).intValue();
/* 2651 */             this.ofConnectedTextures = Config.limit(this.ofConnectedTextures, 1, 3);
/*      */           } 
/*      */           
/* 2654 */           if (exception1[0].equals("ofWeather") && exception1.length >= 2)
/*      */           {
/* 2656 */             this.ofWeather = Boolean.valueOf(exception1[1]).booleanValue();
/*      */           }
/*      */           
/* 2659 */           if (exception1[0].equals("ofSky") && exception1.length >= 2)
/*      */           {
/* 2661 */             this.ofSky = Boolean.valueOf(exception1[1]).booleanValue();
/*      */           }
/*      */           
/* 2664 */           if (exception1[0].equals("ofStars") && exception1.length >= 2)
/*      */           {
/* 2666 */             this.ofStars = Boolean.valueOf(exception1[1]).booleanValue();
/*      */           }
/*      */           
/* 2669 */           if (exception1[0].equals("ofSunMoon") && exception1.length >= 2)
/*      */           {
/* 2671 */             this.ofSunMoon = Boolean.valueOf(exception1[1]).booleanValue();
/*      */           }
/*      */           
/* 2674 */           if (exception1[0].equals("ofVignette") && exception1.length >= 2) {
/*      */             
/* 2676 */             this.ofVignette = Integer.valueOf(exception1[1]).intValue();
/* 2677 */             this.ofVignette = Config.limit(this.ofVignette, 0, 2);
/*      */           } 
/*      */           
/* 2680 */           if (exception1[0].equals("ofChunkUpdates") && exception1.length >= 2) {
/*      */             
/* 2682 */             this.ofChunkUpdates = Integer.valueOf(exception1[1]).intValue();
/* 2683 */             this.ofChunkUpdates = Config.limit(this.ofChunkUpdates, 1, 5);
/*      */           } 
/*      */           
/* 2686 */           if (exception1[0].equals("ofChunkUpdatesDynamic") && exception1.length >= 2)
/*      */           {
/* 2688 */             this.ofChunkUpdatesDynamic = Boolean.valueOf(exception1[1]).booleanValue();
/*      */           }
/*      */           
/* 2691 */           if (exception1[0].equals("ofTime") && exception1.length >= 2) {
/*      */             
/* 2693 */             this.ofTime = Integer.valueOf(exception1[1]).intValue();
/* 2694 */             this.ofTime = Config.limit(this.ofTime, 0, 2);
/*      */           } 
/*      */           
/* 2697 */           if (exception1[0].equals("ofClearWater") && exception1.length >= 2) {
/*      */             
/* 2699 */             this.ofClearWater = Boolean.valueOf(exception1[1]).booleanValue();
/* 2700 */             updateWaterOpacity();
/*      */           } 
/*      */           
/* 2703 */           if (exception1[0].equals("ofAaLevel") && exception1.length >= 2) {
/*      */             
/* 2705 */             this.ofAaLevel = Integer.valueOf(exception1[1]).intValue();
/* 2706 */             this.ofAaLevel = Config.limit(this.ofAaLevel, 0, 16);
/*      */           } 
/*      */           
/* 2709 */           if (exception1[0].equals("ofAfLevel") && exception1.length >= 2) {
/*      */             
/* 2711 */             this.ofAfLevel = Integer.valueOf(exception1[1]).intValue();
/* 2712 */             this.ofAfLevel = Config.limit(this.ofAfLevel, 1, 16);
/*      */           } 
/*      */           
/* 2715 */           if (exception1[0].equals("ofProfiler") && exception1.length >= 2)
/*      */           {
/* 2717 */             this.ofProfiler = Boolean.valueOf(exception1[1]).booleanValue();
/*      */           }
/*      */           
/* 2720 */           if (exception1[0].equals("ofBetterSnow") && exception1.length >= 2)
/*      */           {
/* 2722 */             this.ofBetterSnow = Boolean.valueOf(exception1[1]).booleanValue();
/*      */           }
/*      */           
/* 2725 */           if (exception1[0].equals("ofSwampColors") && exception1.length >= 2)
/*      */           {
/* 2727 */             this.ofSwampColors = Boolean.valueOf(exception1[1]).booleanValue();
/*      */           }
/*      */           
/* 2730 */           if (exception1[0].equals("ofRandomMobs") && exception1.length >= 2)
/*      */           {
/* 2732 */             this.ofRandomMobs = Boolean.valueOf(exception1[1]).booleanValue();
/*      */           }
/*      */           
/* 2735 */           if (exception1[0].equals("ofSmoothBiomes") && exception1.length >= 2)
/*      */           {
/* 2737 */             this.ofSmoothBiomes = Boolean.valueOf(exception1[1]).booleanValue();
/*      */           }
/*      */           
/* 2740 */           if (exception1[0].equals("ofCustomFonts") && exception1.length >= 2)
/*      */           {
/* 2742 */             this.ofCustomFonts = Boolean.valueOf(exception1[1]).booleanValue();
/*      */           }
/*      */           
/* 2745 */           if (exception1[0].equals("ofCustomColors") && exception1.length >= 2)
/*      */           {
/* 2747 */             this.ofCustomColors = Boolean.valueOf(exception1[1]).booleanValue();
/*      */           }
/*      */           
/* 2750 */           if (exception1[0].equals("ofCustomItems") && exception1.length >= 2)
/*      */           {
/* 2752 */             this.ofCustomItems = Boolean.valueOf(exception1[1]).booleanValue();
/*      */           }
/*      */           
/* 2755 */           if (exception1[0].equals("ofCustomSky") && exception1.length >= 2)
/*      */           {
/* 2757 */             this.ofCustomSky = Boolean.valueOf(exception1[1]).booleanValue();
/*      */           }
/*      */           
/* 2760 */           if (exception1[0].equals("ofShowCapes") && exception1.length >= 2)
/*      */           {
/* 2762 */             this.ofShowCapes = Boolean.valueOf(exception1[1]).booleanValue();
/*      */           }
/*      */           
/* 2765 */           if (exception1[0].equals("ofNaturalTextures") && exception1.length >= 2)
/*      */           {
/* 2767 */             this.ofNaturalTextures = Boolean.valueOf(exception1[1]).booleanValue();
/*      */           }
/*      */           
/* 2770 */           if (exception1[0].equals("ofLazyChunkLoading") && exception1.length >= 2)
/*      */           {
/* 2772 */             this.ofLazyChunkLoading = Boolean.valueOf(exception1[1]).booleanValue();
/*      */           }
/*      */           
/* 2775 */           if (exception1[0].equals("ofDynamicFov") && exception1.length >= 2)
/*      */           {
/* 2777 */             this.ofDynamicFov = Boolean.valueOf(exception1[1]).booleanValue();
/*      */           }
/*      */           
/* 2780 */           if (exception1[0].equals("ofDynamicLights") && exception1.length >= 2) {
/*      */             
/* 2782 */             this.ofDynamicLights = Integer.valueOf(exception1[1]).intValue();
/* 2783 */             this.ofDynamicLights = limit(this.ofDynamicLights, OF_DYNAMIC_LIGHTS);
/*      */           } 
/*      */           
/* 2786 */           if (exception1[0].equals("ofFullscreenMode") && exception1.length >= 2)
/*      */           {
/* 2788 */             this.ofFullscreenMode = exception1[1];
/*      */           }
/*      */           
/* 2791 */           if (exception1[0].equals("ofFastMath") && exception1.length >= 2) {
/*      */             
/* 2793 */             this.ofFastMath = Boolean.valueOf(exception1[1]).booleanValue();
/* 2794 */             MathHelper.fastMath = this.ofFastMath;
/*      */           } 
/*      */           
/* 2797 */           if (exception1[0].equals("ofFastRender") && exception1.length >= 2)
/*      */           {
/* 2799 */             this.ofFastRender = Boolean.valueOf(exception1[1]).booleanValue();
/*      */           }
/*      */           
/* 2802 */           if (exception1[0].equals("ofTranslucentBlocks") && exception1.length >= 2) {
/*      */             
/* 2804 */             this.ofTranslucentBlocks = Integer.valueOf(exception1[1]).intValue();
/* 2805 */             this.ofTranslucentBlocks = Config.limit(this.ofTranslucentBlocks, 0, 2);
/*      */           } 
/*      */           
/* 2808 */           if (exception1[0].equals("key_" + this.ofKeyBindZoom.getKeyDescription()))
/*      */           {
/* 2810 */             this.ofKeyBindZoom.setKeyCode(Integer.parseInt(exception1[1]));
/*      */           }
/*      */         }
/* 2813 */         catch (Exception var5) {
/*      */           
/* 2815 */           Config.dbg("Skipping bad option: " + s);
/* 2816 */           var5.printStackTrace();
/*      */         } 
/*      */       } 
/*      */       
/* 2820 */       KeyBinding.resetKeyBindingArrayAndHash();
/* 2821 */       bufferedreader.close();
/*      */     }
/* 2823 */     catch (Exception var6) {
/*      */       
/* 2825 */       Config.warn("Failed to load options");
/* 2826 */       var6.printStackTrace();
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void saveOfOptions() {
/*      */     try {
/* 2834 */       PrintWriter exception = new PrintWriter(new FileWriter(this.optionsFileOF));
/* 2835 */       exception.println("ofRenderDistanceChunks:" + this.renderDistanceChunks);
/* 2836 */       exception.println("ofFogType:" + this.ofFogType);
/* 2837 */       exception.println("ofFogStart:" + this.ofFogStart);
/* 2838 */       exception.println("ofMipmapType:" + this.ofMipmapType);
/* 2839 */       exception.println("ofOcclusionFancy:" + this.ofOcclusionFancy);
/* 2840 */       exception.println("ofSmoothFps:" + this.ofSmoothFps);
/* 2841 */       exception.println("ofSmoothWorld:" + this.ofSmoothWorld);
/* 2842 */       exception.println("ofAoLevel:" + this.ofAoLevel);
/* 2843 */       exception.println("ofClouds:" + this.ofClouds);
/* 2844 */       exception.println("ofCloudsHeight:" + this.ofCloudsHeight);
/* 2845 */       exception.println("ofTrees:" + this.ofTrees);
/* 2846 */       exception.println("ofDroppedItems:" + this.ofDroppedItems);
/* 2847 */       exception.println("ofRain:" + this.ofRain);
/* 2848 */       exception.println("ofAnimatedWater:" + this.ofAnimatedWater);
/* 2849 */       exception.println("ofAnimatedLava:" + this.ofAnimatedLava);
/* 2850 */       exception.println("ofAnimatedFire:" + this.ofAnimatedFire);
/* 2851 */       exception.println("ofAnimatedPortal:" + this.ofAnimatedPortal);
/* 2852 */       exception.println("ofAnimatedRedstone:" + this.ofAnimatedRedstone);
/* 2853 */       exception.println("ofAnimatedExplosion:" + this.ofAnimatedExplosion);
/* 2854 */       exception.println("ofAnimatedFlame:" + this.ofAnimatedFlame);
/* 2855 */       exception.println("ofAnimatedSmoke:" + this.ofAnimatedSmoke);
/* 2856 */       exception.println("ofVoidParticles:" + this.ofVoidParticles);
/* 2857 */       exception.println("ofWaterParticles:" + this.ofWaterParticles);
/* 2858 */       exception.println("ofPortalParticles:" + this.ofPortalParticles);
/* 2859 */       exception.println("ofPotionParticles:" + this.ofPotionParticles);
/* 2860 */       exception.println("ofFireworkParticles:" + this.ofFireworkParticles);
/* 2861 */       exception.println("ofDrippingWaterLava:" + this.ofDrippingWaterLava);
/* 2862 */       exception.println("ofAnimatedTerrain:" + this.ofAnimatedTerrain);
/* 2863 */       exception.println("ofAnimatedTextures:" + this.ofAnimatedTextures);
/* 2864 */       exception.println("ofRainSplash:" + this.ofRainSplash);
/* 2865 */       exception.println("ofLagometer:" + this.ofLagometer);
/* 2866 */       exception.println("ofShowFps:" + this.ofShowFps);
/* 2867 */       exception.println("ofAutoSaveTicks:" + this.ofAutoSaveTicks);
/* 2868 */       exception.println("ofBetterGrass:" + this.ofBetterGrass);
/* 2869 */       exception.println("ofConnectedTextures:" + this.ofConnectedTextures);
/* 2870 */       exception.println("ofWeather:" + this.ofWeather);
/* 2871 */       exception.println("ofSky:" + this.ofSky);
/* 2872 */       exception.println("ofStars:" + this.ofStars);
/* 2873 */       exception.println("ofSunMoon:" + this.ofSunMoon);
/* 2874 */       exception.println("ofVignette:" + this.ofVignette);
/* 2875 */       exception.println("ofChunkUpdates:" + this.ofChunkUpdates);
/* 2876 */       exception.println("ofChunkUpdatesDynamic:" + this.ofChunkUpdatesDynamic);
/* 2877 */       exception.println("ofTime:" + this.ofTime);
/* 2878 */       exception.println("ofClearWater:" + this.ofClearWater);
/* 2879 */       exception.println("ofAaLevel:" + this.ofAaLevel);
/* 2880 */       exception.println("ofAfLevel:" + this.ofAfLevel);
/* 2881 */       exception.println("ofProfiler:" + this.ofProfiler);
/* 2882 */       exception.println("ofBetterSnow:" + this.ofBetterSnow);
/* 2883 */       exception.println("ofSwampColors:" + this.ofSwampColors);
/* 2884 */       exception.println("ofRandomMobs:" + this.ofRandomMobs);
/* 2885 */       exception.println("ofSmoothBiomes:" + this.ofSmoothBiomes);
/* 2886 */       exception.println("ofCustomFonts:" + this.ofCustomFonts);
/* 2887 */       exception.println("ofCustomColors:" + this.ofCustomColors);
/* 2888 */       exception.println("ofCustomItems:" + this.ofCustomItems);
/* 2889 */       exception.println("ofCustomSky:" + this.ofCustomSky);
/* 2890 */       exception.println("ofShowCapes:" + this.ofShowCapes);
/* 2891 */       exception.println("ofNaturalTextures:" + this.ofNaturalTextures);
/* 2892 */       exception.println("ofLazyChunkLoading:" + this.ofLazyChunkLoading);
/* 2893 */       exception.println("ofDynamicFov:" + this.ofDynamicFov);
/* 2894 */       exception.println("ofDynamicLights:" + this.ofDynamicLights);
/* 2895 */       exception.println("ofFullscreenMode:" + this.ofFullscreenMode);
/* 2896 */       exception.println("ofFastMath:" + this.ofFastMath);
/* 2897 */       exception.println("ofFastRender:" + this.ofFastRender);
/* 2898 */       exception.println("ofTranslucentBlocks:" + this.ofTranslucentBlocks);
/* 2899 */       exception.println("key_" + this.ofKeyBindZoom.getKeyDescription() + ":" + this.ofKeyBindZoom.getKeyCode());
/* 2900 */       exception.close();
/*      */     }
/* 2902 */     catch (Exception var2) {
/*      */       
/* 2904 */       Config.warn("Failed to save options");
/* 2905 */       var2.printStackTrace();
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   private void updateRenderClouds() {
/* 2911 */     switch (this.ofClouds) {
/*      */ 
/*      */ 
/*      */       
/*      */       default:
/* 2916 */         this.clouds = true; return;
/*      */       case 3:
/*      */         break;
/*      */     } 
/* 2920 */     this.clouds = false;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void resetSettings() {
/* 2926 */     this.renderDistanceChunks = 8;
/* 2927 */     this.viewBobbing = true;
/* 2928 */     this.anaglyph = false;
/* 2929 */     this.limitFramerate = (int)Options.FRAMERATE_LIMIT.getValueMax();
/* 2930 */     this.enableVsync = false;
/* 2931 */     updateVSync();
/* 2932 */     this.mipmapLevels = 4;
/* 2933 */     this.fancyGraphics = true;
/* 2934 */     this.ambientOcclusion = 2;
/* 2935 */     this.clouds = true;
/* 2936 */     this.fovSetting = 70.0F;
/* 2937 */     this.gammaSetting = 0.0F;
/* 2938 */     this.guiScale = 0;
/* 2939 */     this.particleSetting = 0;
/* 2940 */     this.heldItemTooltips = true;
/* 2941 */     this.field_178881_t = false;
/* 2942 */     this.field_178880_u = true;
/* 2943 */     this.forceUnicodeFont = false;
/* 2944 */     this.ofFogType = 1;
/* 2945 */     this.ofFogStart = 0.8F;
/* 2946 */     this.ofMipmapType = 0;
/* 2947 */     this.ofOcclusionFancy = false;
/* 2948 */     this.ofSmoothFps = false;
/* 2949 */     Config.updateAvailableProcessors();
/* 2950 */     this.ofSmoothWorld = Config.isSingleProcessor();
/* 2951 */     this.ofLazyChunkLoading = Config.isSingleProcessor();
/* 2952 */     this.ofFastMath = false;
/* 2953 */     this.ofFastRender = false;
/* 2954 */     this.ofTranslucentBlocks = 0;
/* 2955 */     this.ofDynamicFov = true;
/* 2956 */     this.ofDynamicLights = 3;
/* 2957 */     this.ofAoLevel = 1.0F;
/* 2958 */     this.ofAaLevel = 0;
/* 2959 */     this.ofAfLevel = 1;
/* 2960 */     this.ofClouds = 0;
/* 2961 */     this.ofCloudsHeight = 0.0F;
/* 2962 */     this.ofTrees = 0;
/* 2963 */     this.ofRain = 0;
/* 2964 */     this.ofBetterGrass = 3;
/* 2965 */     this.ofAutoSaveTicks = 4000;
/* 2966 */     this.ofLagometer = false;
/* 2967 */     this.ofShowFps = false;
/* 2968 */     this.ofProfiler = false;
/* 2969 */     this.ofWeather = true;
/* 2970 */     this.ofSky = true;
/* 2971 */     this.ofStars = true;
/* 2972 */     this.ofSunMoon = true;
/* 2973 */     this.ofVignette = 0;
/* 2974 */     this.ofChunkUpdates = 1;
/* 2975 */     this.ofChunkUpdatesDynamic = false;
/* 2976 */     this.ofTime = 0;
/* 2977 */     this.ofClearWater = false;
/* 2978 */     this.ofBetterSnow = false;
/* 2979 */     this.ofFullscreenMode = "Default";
/* 2980 */     this.ofSwampColors = true;
/* 2981 */     this.ofRandomMobs = true;
/* 2982 */     this.ofSmoothBiomes = true;
/* 2983 */     this.ofCustomFonts = true;
/* 2984 */     this.ofCustomColors = true;
/* 2985 */     this.ofCustomItems = true;
/* 2986 */     this.ofCustomSky = true;
/* 2987 */     this.ofShowCapes = true;
/* 2988 */     this.ofConnectedTextures = 2;
/* 2989 */     this.ofNaturalTextures = false;
/* 2990 */     this.ofAnimatedWater = 0;
/* 2991 */     this.ofAnimatedLava = 0;
/* 2992 */     this.ofAnimatedFire = true;
/* 2993 */     this.ofAnimatedPortal = true;
/* 2994 */     this.ofAnimatedRedstone = true;
/* 2995 */     this.ofAnimatedExplosion = true;
/* 2996 */     this.ofAnimatedFlame = true;
/* 2997 */     this.ofAnimatedSmoke = true;
/* 2998 */     this.ofVoidParticles = true;
/* 2999 */     this.ofWaterParticles = true;
/* 3000 */     this.ofRainSplash = true;
/* 3001 */     this.ofPortalParticles = true;
/* 3002 */     this.ofPotionParticles = true;
/* 3003 */     this.ofFireworkParticles = true;
/* 3004 */     this.ofDrippingWaterLava = true;
/* 3005 */     this.ofAnimatedTerrain = true;
/* 3006 */     this.ofAnimatedTextures = true;
/* 3007 */     Shaders.setShaderPack(Shaders.packNameNone);
/* 3008 */     Shaders.configAntialiasingLevel = 0;
/* 3009 */     Shaders.uninit();
/* 3010 */     Shaders.storeConfig();
/* 3011 */     updateWaterOpacity();
/* 3012 */     this.mc.refreshResources();
/* 3013 */     saveOptions();
/*      */   }
/*      */ 
/*      */   
/*      */   public void updateVSync() {
/* 3018 */     Display.setVSyncEnabled(this.enableVsync);
/*      */   }
/*      */ 
/*      */   
/*      */   private void updateWaterOpacity() {
/* 3023 */     if (this.mc.isIntegratedServerRunning() && this.mc.getIntegratedServer() != null)
/*      */     {
/* 3025 */       Config.waterOpacityChanged = true;
/*      */     }
/*      */     
/* 3028 */     ClearWater.updateWaterOpacity(this, (World)this.mc.theWorld);
/*      */   }
/*      */ 
/*      */   
/*      */   public void setAllAnimations(boolean flag) {
/* 3033 */     int animVal = flag ? 0 : 2;
/* 3034 */     this.ofAnimatedWater = animVal;
/* 3035 */     this.ofAnimatedLava = animVal;
/* 3036 */     this.ofAnimatedFire = flag;
/* 3037 */     this.ofAnimatedPortal = flag;
/* 3038 */     this.ofAnimatedRedstone = flag;
/* 3039 */     this.ofAnimatedExplosion = flag;
/* 3040 */     this.ofAnimatedFlame = flag;
/* 3041 */     this.ofAnimatedSmoke = flag;
/* 3042 */     this.ofVoidParticles = flag;
/* 3043 */     this.ofWaterParticles = flag;
/* 3044 */     this.ofRainSplash = flag;
/* 3045 */     this.ofPortalParticles = flag;
/* 3046 */     this.ofPotionParticles = flag;
/* 3047 */     this.ofFireworkParticles = flag;
/* 3048 */     this.particleSetting = flag ? 0 : 2;
/* 3049 */     this.ofDrippingWaterLava = flag;
/* 3050 */     this.ofAnimatedTerrain = flag;
/* 3051 */     this.ofAnimatedTextures = flag;
/*      */   }
/*      */ 
/*      */   
/*      */   private static int nextValue(int val, int[] vals) {
/* 3056 */     int index = indexOf(val, vals);
/*      */     
/* 3058 */     if (index < 0)
/*      */     {
/* 3060 */       return vals[0];
/*      */     }
/*      */ 
/*      */     
/* 3064 */     index++;
/*      */     
/* 3066 */     if (index >= vals.length)
/*      */     {
/* 3068 */       index = 0;
/*      */     }
/*      */     
/* 3071 */     return vals[index];
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private static int limit(int val, int[] vals) {
/* 3077 */     int index = indexOf(val, vals);
/* 3078 */     return (index < 0) ? vals[0] : val;
/*      */   }
/*      */ 
/*      */   
/*      */   private static int indexOf(int val, int[] vals) {
/* 3083 */     for (int i = 0; i < vals.length; i++) {
/*      */       
/* 3085 */       if (vals[i] == val)
/*      */       {
/* 3087 */         return i;
/*      */       }
/*      */     } 
/*      */     
/* 3091 */     return -1;
/*      */   }
/*      */   
/*      */   public enum Options
/*      */   {
/* 3096 */     INVERT_MOUSE("INVERT_MOUSE", 0, "INVERT_MOUSE", 0, "options.invertMouse", false, true),
/* 3097 */     SENSITIVITY("SENSITIVITY", 1, "SENSITIVITY", 1, "options.sensitivity", true, false),
/* 3098 */     FOV("FOV", 2, "FOV", 2, "options.fov", true, false, 30.0F, 110.0F, 1.0F),
/* 3099 */     GAMMA("GAMMA", 3, "GAMMA", 3, "options.gamma", true, false),
/* 3100 */     SATURATION("SATURATION", 4, "SATURATION", 4, "options.saturation", true, false),
/* 3101 */     RENDER_DISTANCE("RENDER_DISTANCE", 5, "RENDER_DISTANCE", 5, "options.renderDistance", true, false, 2.0F, 16.0F, 1.0F),
/* 3102 */     VIEW_BOBBING("VIEW_BOBBING", 6, "VIEW_BOBBING", 6, "options.viewBobbing", false, true),
/* 3103 */     ANAGLYPH("ANAGLYPH", 7, "ANAGLYPH", 7, "options.anaglyph", false, true),
/* 3104 */     FRAMERATE_LIMIT("FRAMERATE_LIMIT", 8, "FRAMERATE_LIMIT", 8, "options.framerateLimit", true, false, 0.0F, 260.0F, 5.0F),
/* 3105 */     FBO_ENABLE("FBO_ENABLE", 9, "FBO_ENABLE", 9, "options.fboEnable", false, true),
/* 3106 */     RENDER_CLOUDS("RENDER_CLOUDS", 10, "RENDER_CLOUDS", 10, "options.renderClouds", false, true),
/* 3107 */     GRAPHICS("GRAPHICS", 11, "GRAPHICS", 11, "options.graphics", false, false),
/* 3108 */     AMBIENT_OCCLUSION("AMBIENT_OCCLUSION", 12, "AMBIENT_OCCLUSION", 12, "options.ao", false, false),
/* 3109 */     GUI_SCALE("GUI_SCALE", 13, "GUI_SCALE", 13, "options.guiScale", false, false),
/* 3110 */     PARTICLES("PARTICLES", 14, "PARTICLES", 14, "options.particles", false, false),
/* 3111 */     CHAT_VISIBILITY("CHAT_VISIBILITY", 15, "CHAT_VISIBILITY", 15, "options.chat.visibility", false, false),
/* 3112 */     CHAT_COLOR("CHAT_COLOR", 16, "CHAT_COLOR", 16, "options.chat.color", false, true),
/* 3113 */     CHAT_LINKS("CHAT_LINKS", 17, "CHAT_LINKS", 17, "options.chat.links", false, true),
/* 3114 */     CHAT_OPACITY("CHAT_OPACITY", 18, "CHAT_OPACITY", 18, "options.chat.opacity", true, false),
/* 3115 */     CHAT_LINKS_PROMPT("CHAT_LINKS_PROMPT", 19, "CHAT_LINKS_PROMPT", 19, "options.chat.links.prompt", false, true),
/* 3116 */     SNOOPER_ENABLED("SNOOPER_ENABLED", 20, "SNOOPER_ENABLED", 20, "options.snooper", false, true),
/* 3117 */     USE_FULLSCREEN("USE_FULLSCREEN", 21, "USE_FULLSCREEN", 21, "options.fullscreen", false, true),
/* 3118 */     ENABLE_VSYNC("ENABLE_VSYNC", 22, "ENABLE_VSYNC", 22, "options.vsync", false, true),
/* 3119 */     USE_VBO("USE_VBO", 23, "USE_VBO", 23, "options.vbo", false, true),
/* 3120 */     TOUCHSCREEN("TOUCHSCREEN", 24, "TOUCHSCREEN", 24, "options.touchscreen", false, true),
/* 3121 */     CHAT_SCALE("CHAT_SCALE", 25, "CHAT_SCALE", 25, "options.chat.scale", true, false),
/* 3122 */     CHAT_WIDTH("CHAT_WIDTH", 26, "CHAT_WIDTH", 26, "options.chat.width", true, false),
/* 3123 */     CHAT_HEIGHT_FOCUSED("CHAT_HEIGHT_FOCUSED", 27, "CHAT_HEIGHT_FOCUSED", 27, "options.chat.height.focused", true, false),
/* 3124 */     CHAT_HEIGHT_UNFOCUSED("CHAT_HEIGHT_UNFOCUSED", 28, "CHAT_HEIGHT_UNFOCUSED", 28, "options.chat.height.unfocused", true, false),
/* 3125 */     MIPMAP_LEVELS("MIPMAP_LEVELS", 29, "MIPMAP_LEVELS", 29, "options.mipmapLevels", true, false, 0.0F, 4.0F, 1.0F),
/* 3126 */     FORCE_UNICODE_FONT("FORCE_UNICODE_FONT", 30, "FORCE_UNICODE_FONT", 30, "options.forceUnicodeFont", false, true),
/* 3127 */     STREAM_BYTES_PER_PIXEL("STREAM_BYTES_PER_PIXEL", 31, "STREAM_BYTES_PER_PIXEL", 31, "options.stream.bytesPerPixel", true, false),
/* 3128 */     STREAM_VOLUME_MIC("STREAM_VOLUME_MIC", 32, "STREAM_VOLUME_MIC", 32, "options.stream.micVolumne", true, false),
/* 3129 */     STREAM_VOLUME_SYSTEM("STREAM_VOLUME_SYSTEM", 33, "STREAM_VOLUME_SYSTEM", 33, "options.stream.systemVolume", true, false),
/* 3130 */     STREAM_KBPS("STREAM_KBPS", 34, "STREAM_KBPS", 34, "options.stream.kbps", true, false),
/* 3131 */     STREAM_FPS("STREAM_FPS", 35, "STREAM_FPS", 35, "options.stream.fps", true, false),
/* 3132 */     STREAM_COMPRESSION("STREAM_COMPRESSION", 36, "STREAM_COMPRESSION", 36, "options.stream.compression", false, false),
/* 3133 */     STREAM_SEND_METADATA("STREAM_SEND_METADATA", 37, "STREAM_SEND_METADATA", 37, "options.stream.sendMetadata", false, true),
/* 3134 */     STREAM_CHAT_ENABLED("STREAM_CHAT_ENABLED", 38, "STREAM_CHAT_ENABLED", 38, "options.stream.chat.enabled", false, false),
/* 3135 */     STREAM_CHAT_USER_FILTER("STREAM_CHAT_USER_FILTER", 39, "STREAM_CHAT_USER_FILTER", 39, "options.stream.chat.userFilter", false, false),
/* 3136 */     STREAM_MIC_TOGGLE_BEHAVIOR("STREAM_MIC_TOGGLE_BEHAVIOR", 40, "STREAM_MIC_TOGGLE_BEHAVIOR", 40, "options.stream.micToggleBehavior", false, false),
/* 3137 */     BLOCK_ALTERNATIVES("BLOCK_ALTERNATIVES", 41, "BLOCK_ALTERNATIVES", 41, "options.blockAlternatives", false, true),
/* 3138 */     REDUCED_DEBUG_INFO("REDUCED_DEBUG_INFO", 42, "REDUCED_DEBUG_INFO", 42, "options.reducedDebugInfo", false, true),
/* 3139 */     FOG_FANCY("FOG_FANCY", 43, "", 999, "of.options.FOG_FANCY", false, false),
/* 3140 */     FOG_START("FOG_START", 44, "", 999, "of.options.FOG_START", false, false),
/* 3141 */     MIPMAP_TYPE("MIPMAP_TYPE", 45, "", 999, "of.options.MIPMAP_TYPE", true, false, 0.0F, 3.0F, 1.0F),
/* 3142 */     SMOOTH_FPS("SMOOTH_FPS", 46, "", 999, "of.options.SMOOTH_FPS", false, false),
/* 3143 */     CLOUDS("CLOUDS", 47, "", 999, "of.options.CLOUDS", false, false),
/* 3144 */     CLOUD_HEIGHT("CLOUD_HEIGHT", 48, "", 999, "of.options.CLOUD_HEIGHT", true, false),
/* 3145 */     TREES("TREES", 49, "", 999, "of.options.TREES", false, false),
/* 3146 */     RAIN("RAIN", 50, "", 999, "of.options.RAIN", false, false),
/* 3147 */     ANIMATED_WATER("ANIMATED_WATER", 51, "", 999, "of.options.ANIMATED_WATER", false, false),
/* 3148 */     ANIMATED_LAVA("ANIMATED_LAVA", 52, "", 999, "of.options.ANIMATED_LAVA", false, false),
/* 3149 */     ANIMATED_FIRE("ANIMATED_FIRE", 53, "", 999, "of.options.ANIMATED_FIRE", false, false),
/* 3150 */     ANIMATED_PORTAL("ANIMATED_PORTAL", 54, "", 999, "of.options.ANIMATED_PORTAL", false, false),
/* 3151 */     AO_LEVEL("AO_LEVEL", 55, "", 999, "of.options.AO_LEVEL", true, false),
/* 3152 */     LAGOMETER("LAGOMETER", 56, "", 999, "of.options.LAGOMETER", false, false),
/* 3153 */     SHOW_FPS("SHOW_FPS", 57, "", 999, "of.options.SHOW_FPS", false, false),
/* 3154 */     AUTOSAVE_TICKS("AUTOSAVE_TICKS", 58, "", 999, "of.options.AUTOSAVE_TICKS", false, false),
/* 3155 */     BETTER_GRASS("BETTER_GRASS", 59, "", 999, "of.options.BETTER_GRASS", false, false),
/* 3156 */     ANIMATED_REDSTONE("ANIMATED_REDSTONE", 60, "", 999, "of.options.ANIMATED_REDSTONE", false, false),
/* 3157 */     ANIMATED_EXPLOSION("ANIMATED_EXPLOSION", 61, "", 999, "of.options.ANIMATED_EXPLOSION", false, false),
/* 3158 */     ANIMATED_FLAME("ANIMATED_FLAME", 62, "", 999, "of.options.ANIMATED_FLAME", false, false),
/* 3159 */     ANIMATED_SMOKE("ANIMATED_SMOKE", 63, "", 999, "of.options.ANIMATED_SMOKE", false, false),
/* 3160 */     WEATHER("WEATHER", 64, "", 999, "of.options.WEATHER", false, false),
/* 3161 */     SKY("SKY", 65, "", 999, "of.options.SKY", false, false),
/* 3162 */     STARS("STARS", 66, "", 999, "of.options.STARS", false, false),
/* 3163 */     SUN_MOON("SUN_MOON", 67, "", 999, "of.options.SUN_MOON", false, false),
/* 3164 */     VIGNETTE("VIGNETTE", 68, "", 999, "of.options.VIGNETTE", false, false),
/* 3165 */     CHUNK_UPDATES("CHUNK_UPDATES", 69, "", 999, "of.options.CHUNK_UPDATES", false, false),
/* 3166 */     CHUNK_UPDATES_DYNAMIC("CHUNK_UPDATES_DYNAMIC", 70, "", 999, "of.options.CHUNK_UPDATES_DYNAMIC", false, false),
/* 3167 */     TIME("TIME", 71, "", 999, "of.options.TIME", false, false),
/* 3168 */     CLEAR_WATER("CLEAR_WATER", 72, "", 999, "of.options.CLEAR_WATER", false, false),
/* 3169 */     SMOOTH_WORLD("SMOOTH_WORLD", 73, "", 999, "of.options.SMOOTH_WORLD", false, false),
/* 3170 */     VOID_PARTICLES("VOID_PARTICLES", 74, "", 999, "of.options.VOID_PARTICLES", false, false),
/* 3171 */     WATER_PARTICLES("WATER_PARTICLES", 75, "", 999, "of.options.WATER_PARTICLES", false, false),
/* 3172 */     RAIN_SPLASH("RAIN_SPLASH", 76, "", 999, "of.options.RAIN_SPLASH", false, false),
/* 3173 */     PORTAL_PARTICLES("PORTAL_PARTICLES", 77, "", 999, "of.options.PORTAL_PARTICLES", false, false),
/* 3174 */     POTION_PARTICLES("POTION_PARTICLES", 78, "", 999, "of.options.POTION_PARTICLES", false, false),
/* 3175 */     FIREWORK_PARTICLES("FIREWORK_PARTICLES", 79, "", 999, "of.options.FIREWORK_PARTICLES", false, false),
/* 3176 */     PROFILER("PROFILER", 80, "", 999, "of.options.PROFILER", false, false),
/* 3177 */     DRIPPING_WATER_LAVA("DRIPPING_WATER_LAVA", 81, "", 999, "of.options.DRIPPING_WATER_LAVA", false, false),
/* 3178 */     BETTER_SNOW("BETTER_SNOW", 82, "", 999, "of.options.BETTER_SNOW", false, false),
/* 3179 */     FULLSCREEN_MODE("FULLSCREEN_MODE", 83, "", 999, "of.options.FULLSCREEN_MODE", false, false),
/* 3180 */     ANIMATED_TERRAIN("ANIMATED_TERRAIN", 84, "", 999, "of.options.ANIMATED_TERRAIN", false, false),
/* 3181 */     SWAMP_COLORS("SWAMP_COLORS", 85, "", 999, "of.options.SWAMP_COLORS", false, false),
/* 3182 */     RANDOM_MOBS("RANDOM_MOBS", 86, "", 999, "of.options.RANDOM_MOBS", false, false),
/* 3183 */     SMOOTH_BIOMES("SMOOTH_BIOMES", 87, "", 999, "of.options.SMOOTH_BIOMES", false, false),
/* 3184 */     CUSTOM_FONTS("CUSTOM_FONTS", 88, "", 999, "of.options.CUSTOM_FONTS", false, false),
/* 3185 */     CUSTOM_COLORS("CUSTOM_COLORS", 89, "", 999, "of.options.CUSTOM_COLORS", false, false),
/* 3186 */     SHOW_CAPES("SHOW_CAPES", 90, "", 999, "of.options.SHOW_CAPES", false, false),
/* 3187 */     CONNECTED_TEXTURES("CONNECTED_TEXTURES", 91, "", 999, "of.options.CONNECTED_TEXTURES", false, false),
/* 3188 */     CUSTOM_ITEMS("CUSTOM_ITEMS", 92, "", 999, "of.options.CUSTOM_ITEMS", false, false),
/* 3189 */     AA_LEVEL("AA_LEVEL", 93, "", 999, "of.options.AA_LEVEL", true, false, 0.0F, 16.0F, 1.0F),
/* 3190 */     AF_LEVEL("AF_LEVEL", 94, "", 999, "of.options.AF_LEVEL", true, false, 1.0F, 16.0F, 1.0F),
/* 3191 */     ANIMATED_TEXTURES("ANIMATED_TEXTURES", 95, "", 999, "of.options.ANIMATED_TEXTURES", false, false),
/* 3192 */     NATURAL_TEXTURES("NATURAL_TEXTURES", 96, "", 999, "of.options.NATURAL_TEXTURES", false, false),
/* 3193 */     HELD_ITEM_TOOLTIPS("HELD_ITEM_TOOLTIPS", 97, "", 999, "of.options.HELD_ITEM_TOOLTIPS", false, false),
/* 3194 */     DROPPED_ITEMS("DROPPED_ITEMS", 98, "", 999, "of.options.DROPPED_ITEMS", false, false),
/* 3195 */     LAZY_CHUNK_LOADING("LAZY_CHUNK_LOADING", 99, "", 999, "of.options.LAZY_CHUNK_LOADING", false, false),
/* 3196 */     CUSTOM_SKY("CUSTOM_SKY", 100, "", 999, "of.options.CUSTOM_SKY", false, false),
/* 3197 */     FAST_MATH("FAST_MATH", 101, "", 999, "of.options.FAST_MATH", false, false),
/* 3198 */     FAST_RENDER("FAST_RENDER", 102, "", 999, "of.options.FAST_RENDER", false, false),
/* 3199 */     TRANSLUCENT_BLOCKS("TRANSLUCENT_BLOCKS", 103, "", 999, "of.options.TRANSLUCENT_BLOCKS", false, false),
/* 3200 */     DYNAMIC_FOV("DYNAMIC_FOV", 104, "", 999, "of.options.DYNAMIC_FOV", false, false),
/* 3201 */     DYNAMIC_LIGHTS("DYNAMIC_LIGHTS", 105, "", 999, "of.options.DYNAMIC_LIGHTS", false, false);
/*      */     private final boolean enumFloat;
/*      */     private final boolean enumBoolean;
/*      */     private final String enumString;
/*      */     private final float valueStep;
/*      */     private float valueMin;
/*      */     private float valueMax;
/* 3208 */     private static final Options[] $VALUES = new Options[] { INVERT_MOUSE, SENSITIVITY, FOV, GAMMA, SATURATION, RENDER_DISTANCE, VIEW_BOBBING, ANAGLYPH, FRAMERATE_LIMIT, FBO_ENABLE, RENDER_CLOUDS, GRAPHICS, AMBIENT_OCCLUSION, GUI_SCALE, PARTICLES, CHAT_VISIBILITY, CHAT_COLOR, CHAT_LINKS, CHAT_OPACITY, CHAT_LINKS_PROMPT, SNOOPER_ENABLED, USE_FULLSCREEN, ENABLE_VSYNC, USE_VBO, TOUCHSCREEN, CHAT_SCALE, CHAT_WIDTH, CHAT_HEIGHT_FOCUSED, CHAT_HEIGHT_UNFOCUSED, MIPMAP_LEVELS, FORCE_UNICODE_FONT, STREAM_BYTES_PER_PIXEL, STREAM_VOLUME_MIC, STREAM_VOLUME_SYSTEM, STREAM_KBPS, STREAM_FPS, STREAM_COMPRESSION, STREAM_SEND_METADATA, STREAM_CHAT_ENABLED, STREAM_CHAT_USER_FILTER, STREAM_MIC_TOGGLE_BEHAVIOR, BLOCK_ALTERNATIVES, REDUCED_DEBUG_INFO };
/*      */     
/*      */     private static final String __OBFID = "CL_00000653";
/* 3211 */     private static final Options[] $VALUES$ = new Options[] { INVERT_MOUSE, SENSITIVITY, FOV, GAMMA, SATURATION, RENDER_DISTANCE, VIEW_BOBBING, ANAGLYPH, FRAMERATE_LIMIT, FBO_ENABLE, RENDER_CLOUDS, GRAPHICS, AMBIENT_OCCLUSION, GUI_SCALE, PARTICLES, CHAT_VISIBILITY, CHAT_COLOR, CHAT_LINKS, CHAT_OPACITY, CHAT_LINKS_PROMPT, SNOOPER_ENABLED, USE_FULLSCREEN, ENABLE_VSYNC, USE_VBO, TOUCHSCREEN, CHAT_SCALE, CHAT_WIDTH, CHAT_HEIGHT_FOCUSED, CHAT_HEIGHT_UNFOCUSED, MIPMAP_LEVELS, FORCE_UNICODE_FONT, STREAM_BYTES_PER_PIXEL, STREAM_VOLUME_MIC, STREAM_VOLUME_SYSTEM, STREAM_KBPS, STREAM_FPS, STREAM_COMPRESSION, STREAM_SEND_METADATA, STREAM_CHAT_ENABLED, STREAM_CHAT_USER_FILTER, STREAM_MIC_TOGGLE_BEHAVIOR, BLOCK_ALTERNATIVES, REDUCED_DEBUG_INFO, FOG_FANCY, FOG_START, MIPMAP_TYPE, SMOOTH_FPS, CLOUDS, CLOUD_HEIGHT, TREES, RAIN, ANIMATED_WATER, ANIMATED_LAVA, ANIMATED_FIRE, ANIMATED_PORTAL, AO_LEVEL, LAGOMETER, SHOW_FPS, AUTOSAVE_TICKS, BETTER_GRASS, ANIMATED_REDSTONE, ANIMATED_EXPLOSION, ANIMATED_FLAME, ANIMATED_SMOKE, WEATHER, SKY, STARS, SUN_MOON, VIGNETTE, CHUNK_UPDATES, CHUNK_UPDATES_DYNAMIC, TIME, CLEAR_WATER, SMOOTH_WORLD, VOID_PARTICLES, WATER_PARTICLES, RAIN_SPLASH, PORTAL_PARTICLES, POTION_PARTICLES, FIREWORK_PARTICLES, PROFILER, DRIPPING_WATER_LAVA, BETTER_SNOW, FULLSCREEN_MODE, ANIMATED_TERRAIN, SWAMP_COLORS, RANDOM_MOBS, SMOOTH_BIOMES, CUSTOM_FONTS, CUSTOM_COLORS, SHOW_CAPES, CONNECTED_TEXTURES, CUSTOM_ITEMS, AA_LEVEL, AF_LEVEL, ANIMATED_TEXTURES, NATURAL_TEXTURES, HELD_ITEM_TOOLTIPS, DROPPED_ITEMS, LAZY_CHUNK_LOADING, CUSTOM_SKY, FAST_MATH, FAST_RENDER, TRANSLUCENT_BLOCKS, DYNAMIC_FOV, DYNAMIC_LIGHTS };
/*      */ 
/*      */ 
/*      */     
/*      */     public static Options getEnumOptions(int p_74379_0_) {
/* 3216 */       Options[] var1 = values();
/* 3217 */       int var2 = var1.length;
/*      */       
/* 3219 */       for (int var3 = 0; var3 < var2; var3++) {
/*      */         
/* 3221 */         Options var4 = var1[var3];
/*      */         
/* 3223 */         if (var4.returnEnumOrdinal() == p_74379_0_)
/*      */         {
/* 3225 */           return var4;
/*      */         }
/*      */       } 
/*      */       
/* 3229 */       return null;
/*      */     }
/*      */ 
/*      */     
/*      */     static {
/*      */     
/*      */     }
/*      */ 
/*      */     
/*      */     Options(String p_i46376_1_, int p_i46376_2_, String p_i45004_1_, int p_i45004_2_, String p_i45004_3_, boolean p_i45004_4_, boolean p_i45004_5_, float p_i45004_6_, float p_i45004_7_, float p_i45004_8_) {
/* 3239 */       this.enumString = p_i45004_3_;
/* 3240 */       this.enumFloat = p_i45004_4_;
/* 3241 */       this.enumBoolean = p_i45004_5_;
/* 3242 */       this.valueMin = p_i45004_6_;
/* 3243 */       this.valueMax = p_i45004_7_;
/* 3244 */       this.valueStep = p_i45004_8_;
/*      */     }
/*      */ 
/*      */     
/*      */     public boolean getEnumFloat() {
/* 3249 */       return this.enumFloat;
/*      */     }
/*      */ 
/*      */     
/*      */     public boolean getEnumBoolean() {
/* 3254 */       return this.enumBoolean;
/*      */     }
/*      */ 
/*      */     
/*      */     public int returnEnumOrdinal() {
/* 3259 */       return ordinal();
/*      */     }
/*      */ 
/*      */     
/*      */     public String getEnumString() {
/* 3264 */       return this.enumString;
/*      */     }
/*      */ 
/*      */     
/*      */     public float getValueMax() {
/* 3269 */       return this.valueMax;
/*      */     }
/*      */ 
/*      */     
/*      */     public void setValueMax(float p_148263_1_) {
/* 3274 */       this.valueMax = p_148263_1_;
/*      */     }
/*      */ 
/*      */     
/*      */     public float normalizeValue(float p_148266_1_) {
/* 3279 */       return MathHelper.clamp_float((snapToStepClamp(p_148266_1_) - this.valueMin) / (this.valueMax - this.valueMin), 0.0F, 1.0F);
/*      */     }
/*      */ 
/*      */     
/*      */     public float denormalizeValue(float p_148262_1_) {
/* 3284 */       return snapToStepClamp(this.valueMin + (this.valueMax - this.valueMin) * MathHelper.clamp_float(p_148262_1_, 0.0F, 1.0F));
/*      */     }
/*      */ 
/*      */     
/*      */     public float snapToStepClamp(float p_148268_1_) {
/* 3289 */       p_148268_1_ = snapToStep(p_148268_1_);
/* 3290 */       return MathHelper.clamp_float(p_148268_1_, this.valueMin, this.valueMax);
/*      */     }
/*      */ 
/*      */     
/*      */     protected float snapToStep(float p_148264_1_) {
/* 3295 */       if (this.valueStep > 0.0F)
/*      */       {
/* 3297 */         p_148264_1_ = this.valueStep * Math.round(p_148264_1_ / this.valueStep);
/*      */       }
/*      */       
/* 3300 */       return p_148264_1_;
/*      */     }
/*      */   }
/*      */   
/*      */   static final class SwitchOptions
/*      */   {
/* 3306 */     static final int[] optionIds = new int[(GameSettings.Options.values()).length];
/*      */     
/*      */     private static final String __OBFID = "CL_00000652";
/*      */ 
/*      */     
/*      */     static {
/*      */       try {
/* 3313 */         optionIds[GameSettings.Options.INVERT_MOUSE.ordinal()] = 1;
/*      */       }
/* 3315 */       catch (NoSuchFieldError noSuchFieldError) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       try {
/* 3322 */         optionIds[GameSettings.Options.VIEW_BOBBING.ordinal()] = 2;
/*      */       }
/* 3324 */       catch (NoSuchFieldError noSuchFieldError) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       try {
/* 3331 */         optionIds[GameSettings.Options.ANAGLYPH.ordinal()] = 3;
/*      */       }
/* 3333 */       catch (NoSuchFieldError noSuchFieldError) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       try {
/* 3340 */         optionIds[GameSettings.Options.FBO_ENABLE.ordinal()] = 4;
/*      */       }
/* 3342 */       catch (NoSuchFieldError noSuchFieldError) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       try {
/* 3349 */         optionIds[GameSettings.Options.RENDER_CLOUDS.ordinal()] = 5;
/*      */       }
/* 3351 */       catch (NoSuchFieldError noSuchFieldError) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       try {
/* 3358 */         optionIds[GameSettings.Options.CHAT_COLOR.ordinal()] = 6;
/*      */       }
/* 3360 */       catch (NoSuchFieldError noSuchFieldError) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       try {
/* 3367 */         optionIds[GameSettings.Options.CHAT_LINKS.ordinal()] = 7;
/*      */       }
/* 3369 */       catch (NoSuchFieldError noSuchFieldError) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       try {
/* 3376 */         optionIds[GameSettings.Options.CHAT_LINKS_PROMPT.ordinal()] = 8;
/*      */       }
/* 3378 */       catch (NoSuchFieldError noSuchFieldError) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       try {
/* 3385 */         optionIds[GameSettings.Options.SNOOPER_ENABLED.ordinal()] = 9;
/*      */       }
/* 3387 */       catch (NoSuchFieldError noSuchFieldError) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       try {
/* 3394 */         optionIds[GameSettings.Options.USE_FULLSCREEN.ordinal()] = 10;
/*      */       }
/* 3396 */       catch (NoSuchFieldError noSuchFieldError) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       try {
/* 3403 */         optionIds[GameSettings.Options.ENABLE_VSYNC.ordinal()] = 11;
/*      */       }
/* 3405 */       catch (NoSuchFieldError noSuchFieldError) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       try {
/* 3412 */         optionIds[GameSettings.Options.USE_VBO.ordinal()] = 12;
/*      */       }
/* 3414 */       catch (NoSuchFieldError noSuchFieldError) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       try {
/* 3421 */         optionIds[GameSettings.Options.TOUCHSCREEN.ordinal()] = 13;
/*      */       }
/* 3423 */       catch (NoSuchFieldError noSuchFieldError) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       try {
/* 3430 */         optionIds[GameSettings.Options.STREAM_SEND_METADATA.ordinal()] = 14;
/*      */       }
/* 3432 */       catch (NoSuchFieldError noSuchFieldError) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       try {
/* 3439 */         optionIds[GameSettings.Options.FORCE_UNICODE_FONT.ordinal()] = 15;
/*      */       }
/* 3441 */       catch (NoSuchFieldError noSuchFieldError) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       try {
/* 3448 */         optionIds[GameSettings.Options.BLOCK_ALTERNATIVES.ordinal()] = 16;
/*      */       }
/* 3450 */       catch (NoSuchFieldError noSuchFieldError) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       try {
/* 3457 */         optionIds[GameSettings.Options.REDUCED_DEBUG_INFO.ordinal()] = 17;
/*      */       }
/* 3459 */       catch (NoSuchFieldError noSuchFieldError) {}
/*      */     }
/*      */   }
/*      */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\settings\GameSettings.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */