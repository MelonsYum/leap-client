/*      */ package shadersmod.client;
/*      */ 
/*      */ import java.io.BufferedReader;
/*      */ import java.io.File;
/*      */ import java.io.FileInputStream;
/*      */ import java.io.FileOutputStream;
/*      */ import java.io.FileReader;
/*      */ import java.io.FileWriter;
/*      */ import java.io.IOException;
/*      */ import java.io.InputStream;
/*      */ import java.io.InputStreamReader;
/*      */ import java.nio.ByteBuffer;
/*      */ import java.nio.FloatBuffer;
/*      */ import java.nio.IntBuffer;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Arrays;
/*      */ import java.util.HashMap;
/*      */ import java.util.HashSet;
/*      */ import java.util.IdentityHashMap;
/*      */ import java.util.Iterator;
/*      */ import java.util.List;
/*      */ import java.util.Map;
/*      */ import java.util.Properties;
/*      */ import java.util.Set;
/*      */ import java.util.regex.Matcher;
/*      */ import java.util.regex.Pattern;
/*      */ import net.minecraft.block.Block;
/*      */ import net.minecraft.block.material.Material;
/*      */ import net.minecraft.client.Minecraft;
/*      */ import net.minecraft.client.model.ModelBase;
/*      */ import net.minecraft.client.model.ModelRenderer;
/*      */ import net.minecraft.client.renderer.EntityRenderer;
/*      */ import net.minecraft.client.renderer.GlStateManager;
/*      */ import net.minecraft.client.renderer.OpenGlHelper;
/*      */ import net.minecraft.client.renderer.RenderHelper;
/*      */ import net.minecraft.client.renderer.Tessellator;
/*      */ import net.minecraft.client.renderer.WorldRenderer;
/*      */ import net.minecraft.client.renderer.entity.Render;
/*      */ import net.minecraft.client.renderer.entity.RendererLivingEntity;
/*      */ import net.minecraft.client.renderer.texture.ITextureObject;
/*      */ import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
/*      */ import net.minecraft.client.settings.GameSettings;
/*      */ import net.minecraft.entity.Entity;
/*      */ import net.minecraft.entity.EntityList;
/*      */ import net.minecraft.entity.EntityLivingBase;
/*      */ import net.minecraft.item.Item;
/*      */ import net.minecraft.item.ItemStack;
/*      */ import net.minecraft.tileentity.TileEntity;
/*      */ import net.minecraft.util.ChatComponentText;
/*      */ import net.minecraft.util.IChatComponent;
/*      */ import net.minecraft.util.ResourceLocation;
/*      */ import net.minecraft.util.Vec3;
/*      */ import net.minecraft.world.World;
/*      */ import optifine.Config;
/*      */ import optifine.Lang;
/*      */ import optifine.PropertiesOrdered;
/*      */ import optifine.Reflector;
/*      */ import org.lwjgl.BufferUtils;
/*      */ import org.lwjgl.opengl.ARBShaderObjects;
/*      */ import org.lwjgl.opengl.ARBVertexShader;
/*      */ import org.lwjgl.opengl.ContextCapabilities;
/*      */ import org.lwjgl.opengl.EXTFramebufferObject;
/*      */ import org.lwjgl.opengl.GL11;
/*      */ import org.lwjgl.opengl.GL20;
/*      */ import org.lwjgl.opengl.GL30;
/*      */ import org.lwjgl.opengl.GLContext;
/*      */ import org.lwjgl.util.glu.GLU;
/*      */ import shadersmod.common.SMCLog;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class Shaders
/*      */ {
/*   76 */   static Minecraft mc = Minecraft.getMinecraft();
/*      */   static EntityRenderer entityRenderer;
/*      */   public static boolean isInitializedOnce = false;
/*      */   public static boolean isShaderPackInitialized = false;
/*      */   public static ContextCapabilities capabilities;
/*      */   public static String glVersionString;
/*      */   public static String glVendorString;
/*      */   public static String glRendererString;
/*      */   public static boolean hasGlGenMipmap = false;
/*      */   public static boolean hasForge = false;
/*   86 */   public static int numberResetDisplayList = 0;
/*      */   static boolean needResetModels = false;
/*   88 */   private static int renderDisplayWidth = 0;
/*   89 */   private static int renderDisplayHeight = 0;
/*   90 */   public static int renderWidth = 0;
/*   91 */   public static int renderHeight = 0;
/*      */   public static boolean isRenderingWorld = false;
/*      */   public static boolean isRenderingSky = false;
/*      */   public static boolean isCompositeRendered = false;
/*      */   public static boolean isRenderingDfb = false;
/*      */   public static boolean isShadowPass = false;
/*      */   public static boolean isSleeping;
/*      */   public static boolean isHandRendered;
/*      */   public static boolean renderItemPass1DepthMask = false;
/*      */   public static ItemStack itemToRender;
/*  101 */   static float[] sunPosition = new float[4];
/*  102 */   static float[] moonPosition = new float[4];
/*  103 */   static float[] shadowLightPosition = new float[4];
/*  104 */   static float[] upPosition = new float[4];
/*  105 */   static float[] shadowLightPositionVector = new float[4];
/*  106 */   static float[] upPosModelView = new float[] { 0.0F, 100.0F, 0.0F, 0.0F };
/*  107 */   static float[] sunPosModelView = new float[] { 0.0F, 100.0F, 0.0F, 0.0F };
/*  108 */   static float[] moonPosModelView = new float[] { 0.0F, -100.0F, 0.0F, 0.0F };
/*  109 */   private static float[] tempMat = new float[16];
/*      */   static float clearColorR;
/*      */   static float clearColorG;
/*      */   static float clearColorB;
/*      */   static float skyColorR;
/*      */   static float skyColorG;
/*      */   static float skyColorB;
/*  116 */   static long worldTime = 0L;
/*  117 */   static long lastWorldTime = 0L;
/*  118 */   static long diffWorldTime = 0L;
/*  119 */   static float celestialAngle = 0.0F;
/*  120 */   static float sunAngle = 0.0F;
/*  121 */   static float shadowAngle = 0.0F;
/*  122 */   static int moonPhase = 0;
/*  123 */   static long systemTime = 0L;
/*  124 */   static long lastSystemTime = 0L;
/*  125 */   static long diffSystemTime = 0L;
/*  126 */   static int frameCounter = 0;
/*  127 */   static float frameTimeCounter = 0.0F;
/*  128 */   static int systemTimeInt32 = 0;
/*  129 */   static float rainStrength = 0.0F;
/*  130 */   static float wetness = 0.0F;
/*  131 */   public static float wetnessHalfLife = 600.0F;
/*  132 */   public static float drynessHalfLife = 200.0F;
/*  133 */   public static float eyeBrightnessHalflife = 10.0F;
/*      */   static boolean usewetness = false;
/*  135 */   static int isEyeInWater = 0;
/*  136 */   static int eyeBrightness = 0;
/*  137 */   static float eyeBrightnessFadeX = 0.0F;
/*  138 */   static float eyeBrightnessFadeY = 0.0F;
/*  139 */   static float eyePosY = 0.0F;
/*  140 */   static float centerDepth = 0.0F;
/*  141 */   static float centerDepthSmooth = 0.0F;
/*  142 */   static float centerDepthSmoothHalflife = 1.0F;
/*      */   static boolean centerDepthSmoothEnabled = false;
/*  144 */   static int superSamplingLevel = 1;
/*      */   static boolean updateChunksErrorRecorded = false;
/*      */   static boolean lightmapEnabled = false;
/*      */   static boolean fogEnabled = true;
/*  148 */   public static int entityAttrib = 10;
/*  149 */   public static int midTexCoordAttrib = 11;
/*  150 */   public static int tangentAttrib = 12;
/*      */   public static boolean useEntityAttrib = false;
/*      */   public static boolean useMidTexCoordAttrib = false;
/*      */   public static boolean useMultiTexCoord3Attrib = false;
/*      */   public static boolean useTangentAttrib = false;
/*      */   public static boolean progUseEntityAttrib = false;
/*      */   public static boolean progUseMidTexCoordAttrib = false;
/*      */   public static boolean progUseTangentAttrib = false;
/*  158 */   public static int atlasSizeX = 0;
/*  159 */   public static int atlasSizeY = 0;
/*  160 */   public static ShaderUniformFloat4 uniformEntityColor = new ShaderUniformFloat4("entityColor");
/*  161 */   public static ShaderUniformInt uniformEntityId = new ShaderUniformInt("entityId");
/*  162 */   public static ShaderUniformInt uniformBlockEntityId = new ShaderUniformInt("blockEntityId");
/*      */   static double previousCameraPositionX;
/*      */   static double previousCameraPositionY;
/*      */   static double previousCameraPositionZ;
/*      */   static double cameraPositionX;
/*      */   static double cameraPositionY;
/*      */   static double cameraPositionZ;
/*  169 */   static int shadowPassInterval = 0;
/*      */   public static boolean needResizeShadow = false;
/*  171 */   static int shadowMapWidth = 1024;
/*  172 */   static int shadowMapHeight = 1024;
/*  173 */   static int spShadowMapWidth = 1024;
/*  174 */   static int spShadowMapHeight = 1024;
/*  175 */   static float shadowMapFOV = 90.0F;
/*  176 */   static float shadowMapHalfPlane = 160.0F;
/*      */   static boolean shadowMapIsOrtho = true;
/*  178 */   static int shadowPassCounter = 0;
/*      */   static int preShadowPassThirdPersonView;
/*      */   public static boolean shouldSkipDefaultShadow = false;
/*      */   static boolean waterShadowEnabled = false;
/*      */   static final int MaxDrawBuffers = 8;
/*      */   static final int MaxColorBuffers = 8;
/*      */   static final int MaxDepthBuffers = 3;
/*      */   static final int MaxShadowColorBuffers = 8;
/*      */   static final int MaxShadowDepthBuffers = 2;
/*  187 */   static int usedColorBuffers = 0;
/*  188 */   static int usedDepthBuffers = 0;
/*  189 */   static int usedShadowColorBuffers = 0;
/*  190 */   static int usedShadowDepthBuffers = 0;
/*  191 */   static int usedColorAttachs = 0;
/*  192 */   static int usedDrawBuffers = 0;
/*  193 */   static int dfb = 0;
/*  194 */   static int sfb = 0;
/*  195 */   private static int[] gbuffersFormat = new int[8];
/*  196 */   public static int activeProgram = 0;
/*      */   public static final int ProgramNone = 0;
/*      */   public static final int ProgramBasic = 1;
/*      */   public static final int ProgramTextured = 2;
/*      */   public static final int ProgramTexturedLit = 3;
/*      */   public static final int ProgramSkyBasic = 4;
/*      */   public static final int ProgramSkyTextured = 5;
/*      */   public static final int ProgramClouds = 6;
/*      */   public static final int ProgramTerrain = 7;
/*      */   public static final int ProgramTerrainSolid = 8;
/*      */   public static final int ProgramTerrainCutoutMip = 9;
/*      */   public static final int ProgramTerrainCutout = 10;
/*      */   public static final int ProgramDamagedBlock = 11;
/*      */   public static final int ProgramWater = 12;
/*      */   public static final int ProgramBlock = 13;
/*      */   public static final int ProgramBeaconBeam = 14;
/*      */   public static final int ProgramItem = 15;
/*      */   public static final int ProgramEntities = 16;
/*      */   public static final int ProgramArmorGlint = 17;
/*      */   public static final int ProgramSpiderEyes = 18;
/*      */   public static final int ProgramHand = 19;
/*      */   public static final int ProgramWeather = 20;
/*      */   public static final int ProgramComposite = 21;
/*      */   public static final int ProgramComposite1 = 22;
/*      */   public static final int ProgramComposite2 = 23;
/*      */   public static final int ProgramComposite3 = 24;
/*      */   public static final int ProgramComposite4 = 25;
/*      */   public static final int ProgramComposite5 = 26;
/*      */   public static final int ProgramComposite6 = 27;
/*      */   public static final int ProgramComposite7 = 28;
/*      */   public static final int ProgramFinal = 29;
/*      */   public static final int ProgramShadow = 30;
/*      */   public static final int ProgramShadowSolid = 31;
/*      */   public static final int ProgramShadowCutout = 32;
/*      */   public static final int ProgramCount = 33;
/*      */   public static final int MaxCompositePasses = 8;
/*  232 */   private static final String[] programNames = new String[] { "", "gbuffers_basic", "gbuffers_textured", "gbuffers_textured_lit", "gbuffers_skybasic", "gbuffers_skytextured", "gbuffers_clouds", "gbuffers_terrain", "gbuffers_terrain_solid", "gbuffers_terrain_cutout_mip", "gbuffers_terrain_cutout", "gbuffers_damagedblock", "gbuffers_water", "gbuffers_block", "gbuffers_beaconbeam", "gbuffers_item", "gbuffers_entities", "gbuffers_armor_glint", "gbuffers_spidereyes", "gbuffers_hand", "gbuffers_weather", "composite", "composite1", "composite2", "composite3", "composite4", "composite5", "composite6", "composite7", "final", "shadow", "shadow_solid", "shadow_cutout" };
/*  233 */   private static final int[] programBackups = new int[] { 0, 0, 1, 2, 1, 2, 2, 3, 7, 7, 7, 7, 7, 7, 2, 3, 3, 2, 2, 3, 3, 30, 30 };
/*  234 */   static int[] programsID = new int[33];
/*  235 */   private static int[] programsRef = new int[33];
/*  236 */   private static int programIDCopyDepth = 0;
/*  237 */   private static String[] programsDrawBufSettings = new String[33];
/*  238 */   private static String newDrawBufSetting = null;
/*  239 */   static IntBuffer[] programsDrawBuffers = new IntBuffer[33];
/*  240 */   static IntBuffer activeDrawBuffers = null;
/*  241 */   private static String[] programsColorAtmSettings = new String[33];
/*  242 */   private static String newColorAtmSetting = null;
/*  243 */   private static String activeColorAtmSettings = null;
/*  244 */   private static int[] programsCompositeMipmapSetting = new int[33];
/*  245 */   private static int newCompositeMipmapSetting = 0;
/*  246 */   private static int activeCompositeMipmapSetting = 0;
/*  247 */   public static Properties loadedShaders = null;
/*  248 */   public static Properties shadersConfig = null;
/*  249 */   public static ITextureObject defaultTexture = null;
/*      */   public static boolean normalMapEnabled = false;
/*  251 */   public static boolean[] shadowHardwareFilteringEnabled = new boolean[2];
/*  252 */   public static boolean[] shadowMipmapEnabled = new boolean[2];
/*  253 */   public static boolean[] shadowFilterNearest = new boolean[2];
/*  254 */   public static boolean[] shadowColorMipmapEnabled = new boolean[8];
/*  255 */   public static boolean[] shadowColorFilterNearest = new boolean[8];
/*      */   public static boolean configTweakBlockDamage = true;
/*      */   public static boolean configCloudShadow = true;
/*  258 */   public static float configHandDepthMul = 0.125F;
/*  259 */   public static float configRenderResMul = 1.0F;
/*  260 */   public static float configShadowResMul = 1.0F;
/*  261 */   public static int configTexMinFilB = 0;
/*  262 */   public static int configTexMinFilN = 0;
/*  263 */   public static int configTexMinFilS = 0;
/*  264 */   public static int configTexMagFilB = 0;
/*  265 */   public static int configTexMagFilN = 0;
/*  266 */   public static int configTexMagFilS = 0;
/*      */   public static boolean configShadowClipFrustrum = true;
/*      */   public static boolean configNormalMap = true;
/*      */   public static boolean configSpecularMap = true;
/*  270 */   public static PropertyDefaultTrueFalse configOldLighting = new PropertyDefaultTrueFalse("oldLighting", "Classic Lighting", 2);
/*  271 */   public static int configAntialiasingLevel = 0;
/*      */   public static final int texMinFilRange = 3;
/*      */   public static final int texMagFilRange = 2;
/*  274 */   public static final String[] texMinFilDesc = new String[] { "Nearest", "Nearest-Nearest", "Nearest-Linear" };
/*  275 */   public static final String[] texMagFilDesc = new String[] { "Nearest", "Linear" };
/*  276 */   public static final int[] texMinFilValue = new int[] { 9728, 9984, 9986 };
/*  277 */   public static final int[] texMagFilValue = new int[] { 9728, 9729 };
/*  278 */   static IShaderPack shaderPack = null;
/*      */   public static boolean shaderPackLoaded = false;
/*      */   static File currentshader;
/*      */   static String currentshadername;
/*  282 */   public static String packNameNone = "OFF";
/*  283 */   static String packNameDefault = "(internal)";
/*  284 */   static String shaderpacksdirname = "shaderpacks";
/*  285 */   static String optionsfilename = "optionsshaders.txt";
/*  286 */   static File shadersdir = new File((Minecraft.getMinecraft()).mcDataDir, "shaders");
/*  287 */   static File shaderpacksdir = new File((Minecraft.getMinecraft()).mcDataDir, shaderpacksdirname);
/*  288 */   static File configFile = new File((Minecraft.getMinecraft()).mcDataDir, optionsfilename);
/*  289 */   static ShaderOption[] shaderPackOptions = null;
/*  290 */   static ShaderProfile[] shaderPackProfiles = null;
/*  291 */   static Map<String, ShaderOption[]> shaderPackGuiScreens = null;
/*  292 */   public static PropertyDefaultFastFancyOff shaderPackClouds = new PropertyDefaultFastFancyOff("clouds", "Clouds", 0);
/*  293 */   public static PropertyDefaultTrueFalse shaderPackOldLighting = new PropertyDefaultTrueFalse("oldLighting", "Classic Lighting", 0);
/*  294 */   public static PropertyDefaultTrueFalse shaderPackDynamicHandLight = new PropertyDefaultTrueFalse("dynamicHandLight", "Dynamic Hand Light", 0);
/*  295 */   private static Map<String, String> shaderPackResources = new HashMap<>();
/*  296 */   private static World currentWorld = null;
/*  297 */   private static List<Integer> shaderPackDimensions = new ArrayList<>();
/*      */   public static final boolean enableShadersOption = true;
/*      */   private static final boolean enableShadersDebug = true;
/*  300 */   private static final boolean saveFinalShaders = System.getProperty("shaders.debug.save", "false").equals("true");
/*  301 */   public static float blockLightLevel05 = 0.5F;
/*  302 */   public static float blockLightLevel06 = 0.6F;
/*  303 */   public static float blockLightLevel08 = 0.8F;
/*  304 */   public static float aoLevel = 0.8F;
/*  305 */   public static float blockAoLight = 1.0F - aoLevel;
/*  306 */   public static float sunPathRotation = 0.0F;
/*  307 */   public static float shadowAngleInterval = 0.0F;
/*  308 */   public static int fogMode = 0;
/*      */   public static float fogColorR;
/*      */   public static float fogColorG;
/*      */   public static float fogColorB;
/*  312 */   public static float shadowIntervalSize = 2.0F;
/*  313 */   public static int terrainIconSize = 16;
/*  314 */   public static int[] terrainTextureSize = new int[2];
/*      */   private static HFNoiseTexture noiseTexture;
/*      */   private static boolean noiseTextureEnabled = false;
/*  317 */   private static int noiseTextureResolution = 256;
/*  318 */   static final int[] dfbColorTexturesA = new int[16];
/*  319 */   static final int[] colorTexturesToggle = new int[8];
/*  320 */   static final int[] colorTextureTextureImageUnit = new int[] { 0, 1, 2, 3, 7, 8, 9, 10 };
/*  321 */   static final boolean[][] programsToggleColorTextures = new boolean[33][8];
/*      */   private static final int bigBufferSize = 2196;
/*  323 */   private static final ByteBuffer bigBuffer = (ByteBuffer)BufferUtils.createByteBuffer(2196).limit(0);
/*  324 */   static final float[] faProjection = new float[16];
/*  325 */   static final float[] faProjectionInverse = new float[16];
/*  326 */   static final float[] faModelView = new float[16];
/*  327 */   static final float[] faModelViewInverse = new float[16];
/*  328 */   static final float[] faShadowProjection = new float[16];
/*  329 */   static final float[] faShadowProjectionInverse = new float[16];
/*  330 */   static final float[] faShadowModelView = new float[16];
/*  331 */   static final float[] faShadowModelViewInverse = new float[16];
/*  332 */   static final FloatBuffer projection = nextFloatBuffer(16);
/*  333 */   static final FloatBuffer projectionInverse = nextFloatBuffer(16);
/*  334 */   static final FloatBuffer modelView = nextFloatBuffer(16);
/*  335 */   static final FloatBuffer modelViewInverse = nextFloatBuffer(16);
/*  336 */   static final FloatBuffer shadowProjection = nextFloatBuffer(16);
/*  337 */   static final FloatBuffer shadowProjectionInverse = nextFloatBuffer(16);
/*  338 */   static final FloatBuffer shadowModelView = nextFloatBuffer(16);
/*  339 */   static final FloatBuffer shadowModelViewInverse = nextFloatBuffer(16);
/*  340 */   static final FloatBuffer previousProjection = nextFloatBuffer(16);
/*  341 */   static final FloatBuffer previousModelView = nextFloatBuffer(16);
/*  342 */   static final FloatBuffer tempMatrixDirectBuffer = nextFloatBuffer(16);
/*  343 */   static final FloatBuffer tempDirectFloatBuffer = nextFloatBuffer(16);
/*  344 */   static final IntBuffer dfbColorTextures = nextIntBuffer(16);
/*  345 */   static final IntBuffer dfbDepthTextures = nextIntBuffer(3);
/*  346 */   static final IntBuffer sfbColorTextures = nextIntBuffer(8);
/*  347 */   static final IntBuffer sfbDepthTextures = nextIntBuffer(2);
/*  348 */   static final IntBuffer dfbDrawBuffers = nextIntBuffer(8);
/*  349 */   static final IntBuffer sfbDrawBuffers = nextIntBuffer(8);
/*  350 */   static final IntBuffer drawBuffersNone = nextIntBuffer(8);
/*  351 */   static final IntBuffer drawBuffersAll = nextIntBuffer(8);
/*  352 */   static final IntBuffer drawBuffersClear0 = nextIntBuffer(8);
/*  353 */   static final IntBuffer drawBuffersClear1 = nextIntBuffer(8);
/*  354 */   static final IntBuffer drawBuffersClearColor = nextIntBuffer(8);
/*  355 */   static final IntBuffer drawBuffersColorAtt0 = nextIntBuffer(8);
/*  356 */   static final IntBuffer[] drawBuffersBuffer = nextIntBufferArray(33, 8);
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   static Map<Block, Integer> mapBlockToEntityData;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static ByteBuffer nextByteBuffer(int size) {
/*  368 */     ByteBuffer buffer = bigBuffer;
/*  369 */     int pos = buffer.limit();
/*  370 */     buffer.position(pos).limit(pos + size);
/*  371 */     return buffer.slice();
/*      */   }
/*      */ 
/*      */   
/*      */   private static IntBuffer nextIntBuffer(int size) {
/*  376 */     ByteBuffer buffer = bigBuffer;
/*  377 */     int pos = buffer.limit();
/*  378 */     buffer.position(pos).limit(pos + size * 4);
/*  379 */     return buffer.asIntBuffer();
/*      */   }
/*      */ 
/*      */   
/*      */   private static FloatBuffer nextFloatBuffer(int size) {
/*  384 */     ByteBuffer buffer = bigBuffer;
/*  385 */     int pos = buffer.limit();
/*  386 */     buffer.position(pos).limit(pos + size * 4);
/*  387 */     return buffer.asFloatBuffer();
/*      */   }
/*      */ 
/*      */   
/*      */   private static IntBuffer[] nextIntBufferArray(int count, int size) {
/*  392 */     IntBuffer[] aib = new IntBuffer[count];
/*      */     
/*  394 */     for (int i = 0; i < count; i++)
/*      */     {
/*  396 */       aib[i] = nextIntBuffer(size);
/*      */     }
/*      */     
/*  399 */     return aib;
/*      */   }
/*      */ 
/*      */   
/*      */   public static void loadConfig() {
/*  404 */     SMCLog.info("Load ShadersMod configuration.");
/*      */ 
/*      */     
/*      */     try {
/*  408 */       if (!shaderpacksdir.exists())
/*      */       {
/*  410 */         shaderpacksdir.mkdir();
/*      */       }
/*      */     }
/*  413 */     catch (Exception var8) {
/*      */       
/*  415 */       SMCLog.severe("Failed to open the shaderpacks directory: " + shaderpacksdir);
/*      */     } 
/*      */     
/*  418 */     shadersConfig = (Properties)new PropertiesOrdered();
/*  419 */     shadersConfig.setProperty(EnumShaderOption.SHADER_PACK.getPropertyKey(), "");
/*      */     
/*  421 */     if (configFile.exists()) {
/*      */       
/*      */       try {
/*      */         
/*  425 */         FileReader ops = new FileReader(configFile);
/*  426 */         shadersConfig.load(ops);
/*  427 */         ops.close();
/*      */       }
/*  429 */       catch (Exception exception) {}
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  435 */     if (!configFile.exists()) {
/*      */       
/*      */       try {
/*      */         
/*  439 */         storeConfig();
/*      */       }
/*  441 */       catch (Exception exception) {}
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  447 */     EnumShaderOption[] var9 = EnumShaderOption.values();
/*      */     
/*  449 */     for (int i = 0; i < var9.length; i++) {
/*      */       
/*  451 */       EnumShaderOption op = var9[i];
/*  452 */       String key = op.getPropertyKey();
/*  453 */       String def = op.getValueDefault();
/*  454 */       String val = shadersConfig.getProperty(key, def);
/*  455 */       setEnumShaderOption(op, val);
/*      */     } 
/*      */     
/*  458 */     loadShaderPack();
/*      */   }
/*      */ 
/*      */   
/*      */   private static void setEnumShaderOption(EnumShaderOption eso, String str) {
/*  463 */     if (str == null)
/*      */     {
/*  465 */       str = eso.getValueDefault();
/*      */     }
/*      */     
/*  468 */     switch (eso) {
/*      */       
/*      */       case ANTIALIASING:
/*  471 */         configAntialiasingLevel = Config.parseInt(str, 0);
/*      */         return;
/*      */       
/*      */       case NORMAL_MAP:
/*  475 */         configNormalMap = Config.parseBoolean(str, true);
/*      */         return;
/*      */       
/*      */       case SPECULAR_MAP:
/*  479 */         configSpecularMap = Config.parseBoolean(str, true);
/*      */         return;
/*      */       
/*      */       case RENDER_RES_MUL:
/*  483 */         configRenderResMul = Config.parseFloat(str, 1.0F);
/*      */         return;
/*      */       
/*      */       case SHADOW_RES_MUL:
/*  487 */         configShadowResMul = Config.parseFloat(str, 1.0F);
/*      */         return;
/*      */       
/*      */       case HAND_DEPTH_MUL:
/*  491 */         configHandDepthMul = Config.parseFloat(str, 0.125F);
/*      */         return;
/*      */       
/*      */       case CLOUD_SHADOW:
/*  495 */         configCloudShadow = Config.parseBoolean(str, true);
/*      */         return;
/*      */       
/*      */       case OLD_LIGHTING:
/*  499 */         configOldLighting.setPropertyValue(str);
/*      */         return;
/*      */       
/*      */       case SHADER_PACK:
/*  503 */         currentshadername = str;
/*      */         return;
/*      */       
/*      */       case TWEAK_BLOCK_DAMAGE:
/*  507 */         configTweakBlockDamage = Config.parseBoolean(str, true);
/*      */         return;
/*      */       
/*      */       case SHADOW_CLIP_FRUSTRUM:
/*  511 */         configShadowClipFrustrum = Config.parseBoolean(str, true);
/*      */         return;
/*      */       
/*      */       case TEX_MIN_FIL_B:
/*  515 */         configTexMinFilB = Config.parseInt(str, 0);
/*      */         return;
/*      */       
/*      */       case TEX_MIN_FIL_N:
/*  519 */         configTexMinFilN = Config.parseInt(str, 0);
/*      */         return;
/*      */       
/*      */       case TEX_MIN_FIL_S:
/*  523 */         configTexMinFilS = Config.parseInt(str, 0);
/*      */         return;
/*      */       
/*      */       case TEX_MAG_FIL_B:
/*  527 */         configTexMagFilB = Config.parseInt(str, 0);
/*      */         return;
/*      */       
/*      */       case TEX_MAG_FIL_N:
/*  531 */         configTexMagFilB = Config.parseInt(str, 0);
/*      */         return;
/*      */       
/*      */       case TEX_MAG_FIL_S:
/*  535 */         configTexMagFilB = Config.parseInt(str, 0);
/*      */         return;
/*      */     } 
/*      */     
/*  539 */     throw new IllegalArgumentException("Unknown option: " + eso);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public static void storeConfig() {
/*  545 */     SMCLog.info("Save ShadersMod configuration.");
/*      */     
/*  547 */     if (shadersConfig == null)
/*      */     {
/*  549 */       shadersConfig = (Properties)new PropertiesOrdered();
/*      */     }
/*      */     
/*  552 */     EnumShaderOption[] ops = EnumShaderOption.values();
/*      */     
/*  554 */     for (int ex = 0; ex < ops.length; ex++) {
/*      */       
/*  556 */       EnumShaderOption op = ops[ex];
/*  557 */       String key = op.getPropertyKey();
/*  558 */       String val = getEnumShaderOption(op);
/*  559 */       shadersConfig.setProperty(key, val);
/*      */     } 
/*      */ 
/*      */     
/*      */     try {
/*  564 */       FileWriter var6 = new FileWriter(configFile);
/*  565 */       shadersConfig.store(var6, (String)null);
/*  566 */       var6.close();
/*      */     }
/*  568 */     catch (Exception var5) {
/*      */       
/*  570 */       SMCLog.severe("Error saving configuration: " + var5.getClass().getName() + ": " + var5.getMessage());
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public static String getEnumShaderOption(EnumShaderOption eso) {
/*  576 */     switch (eso) {
/*      */       
/*      */       case ANTIALIASING:
/*  579 */         return Integer.toString(configAntialiasingLevel);
/*      */       
/*      */       case NORMAL_MAP:
/*  582 */         return Boolean.toString(configNormalMap);
/*      */       
/*      */       case SPECULAR_MAP:
/*  585 */         return Boolean.toString(configSpecularMap);
/*      */       
/*      */       case RENDER_RES_MUL:
/*  588 */         return Float.toString(configRenderResMul);
/*      */       
/*      */       case SHADOW_RES_MUL:
/*  591 */         return Float.toString(configShadowResMul);
/*      */       
/*      */       case HAND_DEPTH_MUL:
/*  594 */         return Float.toString(configHandDepthMul);
/*      */       
/*      */       case CLOUD_SHADOW:
/*  597 */         return Boolean.toString(configCloudShadow);
/*      */       
/*      */       case OLD_LIGHTING:
/*  600 */         return configOldLighting.getPropertyValue();
/*      */       
/*      */       case SHADER_PACK:
/*  603 */         return currentshadername;
/*      */       
/*      */       case TWEAK_BLOCK_DAMAGE:
/*  606 */         return Boolean.toString(configTweakBlockDamage);
/*      */       
/*      */       case SHADOW_CLIP_FRUSTRUM:
/*  609 */         return Boolean.toString(configShadowClipFrustrum);
/*      */       
/*      */       case TEX_MIN_FIL_B:
/*  612 */         return Integer.toString(configTexMinFilB);
/*      */       
/*      */       case TEX_MIN_FIL_N:
/*  615 */         return Integer.toString(configTexMinFilN);
/*      */       
/*      */       case TEX_MIN_FIL_S:
/*  618 */         return Integer.toString(configTexMinFilS);
/*      */       
/*      */       case TEX_MAG_FIL_B:
/*  621 */         return Integer.toString(configTexMagFilB);
/*      */       
/*      */       case TEX_MAG_FIL_N:
/*  624 */         return Integer.toString(configTexMagFilB);
/*      */       
/*      */       case TEX_MAG_FIL_S:
/*  627 */         return Integer.toString(configTexMagFilB);
/*      */     } 
/*      */     
/*  630 */     throw new IllegalArgumentException("Unknown option: " + eso);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public static void setShaderPack(String par1name) {
/*  636 */     currentshadername = par1name;
/*  637 */     shadersConfig.setProperty(EnumShaderOption.SHADER_PACK.getPropertyKey(), par1name);
/*  638 */     loadShaderPack();
/*      */   }
/*      */ 
/*      */   
/*      */   public static void loadShaderPack() {
/*  643 */     boolean shaderPackLoadedPrev = shaderPackLoaded;
/*  644 */     boolean oldLightingPrev = isOldLighting();
/*  645 */     shaderPackLoaded = false;
/*      */     
/*  647 */     if (shaderPack != null) {
/*      */       
/*  649 */       shaderPack.close();
/*  650 */       shaderPack = null;
/*  651 */       shaderPackResources.clear();
/*  652 */       shaderPackDimensions.clear();
/*  653 */       shaderPackOptions = null;
/*  654 */       shaderPackProfiles = null;
/*  655 */       shaderPackGuiScreens = null;
/*  656 */       shaderPackClouds.resetValue();
/*  657 */       shaderPackDynamicHandLight.resetValue();
/*  658 */       shaderPackOldLighting.resetValue();
/*      */     } 
/*      */     
/*  661 */     boolean shadersBlocked = false;
/*      */     
/*  663 */     if (Config.isAntialiasing()) {
/*      */       
/*  665 */       SMCLog.info("Shaders can not be loaded, Antialiasing is enabled: " + Config.getAntialiasingLevel() + "x");
/*  666 */       shadersBlocked = true;
/*      */     } 
/*      */     
/*  669 */     if (Config.isAnisotropicFiltering()) {
/*      */       
/*  671 */       SMCLog.info("Shaders can not be loaded, Anisotropic Filtering is enabled: " + Config.getAnisotropicFilterLevel() + "x");
/*  672 */       shadersBlocked = true;
/*      */     } 
/*      */     
/*  675 */     if (Config.isFastRender()) {
/*      */       
/*  677 */       SMCLog.info("Shaders can not be loaded, Fast Render is enabled.");
/*  678 */       shadersBlocked = true;
/*      */     } 
/*      */     
/*  681 */     String packName = shadersConfig.getProperty(EnumShaderOption.SHADER_PACK.getPropertyKey(), packNameDefault);
/*      */     
/*  683 */     if (!packName.isEmpty() && !packName.equals(packNameNone) && !shadersBlocked)
/*      */     {
/*  685 */       if (packName.equals(packNameDefault)) {
/*      */         
/*  687 */         shaderPack = new ShaderPackDefault();
/*  688 */         shaderPackLoaded = true;
/*      */       } else {
/*      */ 
/*      */         
/*      */         try {
/*      */           
/*  694 */           File formatChanged = new File(shaderpacksdir, packName);
/*      */           
/*  696 */           if (formatChanged.isDirectory())
/*      */           {
/*  698 */             shaderPack = new ShaderPackFolder(packName, formatChanged);
/*  699 */             shaderPackLoaded = true;
/*      */           }
/*  701 */           else if (formatChanged.isFile() && packName.toLowerCase().endsWith(".zip"))
/*      */           {
/*  703 */             shaderPack = new ShaderPackZip(packName, formatChanged);
/*  704 */             shaderPackLoaded = true;
/*      */           }
/*      */         
/*  707 */         } catch (Exception exception) {}
/*      */       } 
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  714 */     if (shaderPack != null) {
/*      */       
/*  716 */       SMCLog.info("Loaded shaderpack: " + getShaderPackName());
/*      */     }
/*      */     else {
/*      */       
/*  720 */       SMCLog.info("No shaderpack loaded.");
/*  721 */       shaderPack = new ShaderPackNone();
/*      */     } 
/*      */     
/*  724 */     loadShaderPackResources();
/*  725 */     loadShaderPackDimensions();
/*  726 */     shaderPackOptions = loadShaderPackOptions();
/*  727 */     loadShaderPackProperties();
/*  728 */     boolean formatChanged1 = shaderPackLoaded ^ shaderPackLoadedPrev;
/*  729 */     boolean oldLightingChanged = isOldLighting() ^ oldLightingPrev;
/*      */     
/*  731 */     if (formatChanged1 || oldLightingChanged) {
/*      */       
/*  733 */       DefaultVertexFormats.updateVertexFormats();
/*      */       
/*  735 */       if (Reflector.LightUtil.exists()) {
/*      */         
/*  737 */         Reflector.LightUtil_itemConsumer.setValue(null);
/*  738 */         Reflector.LightUtil_tessellator.setValue(null);
/*      */       } 
/*      */       
/*  741 */       updateBlockLightLevel();
/*  742 */       mc.func_175603_A();
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   private static void loadShaderPackDimensions() {
/*  748 */     shaderPackDimensions.clear();
/*  749 */     StringBuffer sb = new StringBuffer();
/*      */     
/*  751 */     for (int i = -128; i <= 128; i++) {
/*      */       
/*  753 */       String worldDir = "/shaders/world" + i;
/*      */       
/*  755 */       if (shaderPack.hasDirectory(worldDir)) {
/*      */         
/*  757 */         shaderPackDimensions.add(Integer.valueOf(i));
/*  758 */         sb.append(" " + i);
/*      */       } 
/*      */     } 
/*      */     
/*  762 */     if (sb.length() > 0)
/*      */     {
/*  764 */       Config.dbg("[Shaders] Dimensions:" + sb);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   private static void loadShaderPackProperties() {
/*  770 */     shaderPackClouds.resetValue();
/*  771 */     shaderPackDynamicHandLight.resetValue();
/*  772 */     shaderPackOldLighting.resetValue();
/*      */     
/*  774 */     if (shaderPack != null) {
/*      */       
/*  776 */       String path = "/shaders/shaders.properties";
/*      */ 
/*      */       
/*      */       try {
/*  780 */         InputStream e = shaderPack.getResourceAsStream(path);
/*      */         
/*  782 */         if (e == null) {
/*      */           return;
/*      */         }
/*      */ 
/*      */         
/*  787 */         PropertiesOrdered props = new PropertiesOrdered();
/*  788 */         props.load(e);
/*  789 */         e.close();
/*  790 */         shaderPackClouds.loadFrom((Properties)props);
/*  791 */         shaderPackDynamicHandLight.loadFrom((Properties)props);
/*  792 */         shaderPackOldLighting.loadFrom((Properties)props);
/*  793 */         shaderPackProfiles = ShaderPackParser.parseProfiles((Properties)props, shaderPackOptions);
/*  794 */         shaderPackGuiScreens = ShaderPackParser.parseGuiScreens((Properties)props, shaderPackProfiles, shaderPackOptions);
/*      */       }
/*  796 */       catch (IOException var3) {
/*      */         
/*  798 */         Config.warn("[Shaders] Error reading: " + path);
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public static ShaderOption[] getShaderPackOptions(String screenName) {
/*  805 */     ShaderOption[] ops = (ShaderOption[])shaderPackOptions.clone();
/*      */     
/*  807 */     if (shaderPackGuiScreens == null) {
/*      */       
/*  809 */       if (shaderPackProfiles != null) {
/*      */         
/*  811 */         ShaderOptionProfile var8 = new ShaderOptionProfile(shaderPackProfiles, ops);
/*  812 */         ops = (ShaderOption[])Config.addObjectToArray((Object[])ops, var8, 0);
/*      */       } 
/*      */       
/*  815 */       ops = getVisibleOptions(ops);
/*  816 */       return ops;
/*      */     } 
/*      */ 
/*      */     
/*  820 */     String key = (screenName != null) ? ("screen." + screenName) : "screen";
/*  821 */     ShaderOption[] sos = shaderPackGuiScreens.get(key);
/*      */     
/*  823 */     if (sos == null)
/*      */     {
/*  825 */       return new ShaderOption[0];
/*      */     }
/*      */ 
/*      */     
/*  829 */     ArrayList<ShaderOption> list = new ArrayList();
/*      */     
/*  831 */     for (int sosExp = 0; sosExp < sos.length; sosExp++) {
/*      */       
/*  833 */       ShaderOption so = sos[sosExp];
/*      */       
/*  835 */       if (so == null) {
/*      */         
/*  837 */         list.add(null);
/*      */       }
/*  839 */       else if (so instanceof ShaderOptionRest) {
/*      */         
/*  841 */         ShaderOption[] restOps = getShaderOptionsRest(shaderPackGuiScreens, ops);
/*  842 */         list.addAll(Arrays.asList(restOps));
/*      */       }
/*      */       else {
/*      */         
/*  846 */         list.add(so);
/*      */       } 
/*      */     } 
/*      */     
/*  850 */     ShaderOption[] var9 = list.<ShaderOption>toArray(new ShaderOption[list.size()]);
/*  851 */     return var9;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static ShaderOption[] getShaderOptionsRest(Map<String, ShaderOption[]> mapScreens, ShaderOption[] ops) {
/*  858 */     HashSet<String> setNames = new HashSet();
/*  859 */     Set<String> keys = mapScreens.keySet();
/*  860 */     Iterator<String> list = keys.iterator();
/*      */     
/*  862 */     while (list.hasNext()) {
/*      */       
/*  864 */       String sos = list.next();
/*  865 */       ShaderOption[] so = mapScreens.get(sos);
/*      */       
/*  867 */       for (int name = 0; name < so.length; name++) {
/*      */         
/*  869 */         ShaderOption so1 = so[name];
/*      */         
/*  871 */         if (so1 != null)
/*      */         {
/*  873 */           setNames.add(so1.getName());
/*      */         }
/*      */       } 
/*      */     } 
/*      */     
/*  878 */     ArrayList<ShaderOption> var9 = new ArrayList();
/*      */     
/*  880 */     for (int var10 = 0; var10 < ops.length; var10++) {
/*      */       
/*  882 */       ShaderOption var12 = ops[var10];
/*      */       
/*  884 */       if (var12.isVisible()) {
/*      */         
/*  886 */         String var13 = var12.getName();
/*      */         
/*  888 */         if (!setNames.contains(var13))
/*      */         {
/*  890 */           var9.add(var12);
/*      */         }
/*      */       } 
/*      */     } 
/*      */     
/*  895 */     ShaderOption[] var11 = var9.<ShaderOption>toArray(new ShaderOption[var9.size()]);
/*  896 */     return var11;
/*      */   }
/*      */ 
/*      */   
/*      */   public static ShaderOption getShaderOption(String name) {
/*  901 */     return ShaderUtils.getShaderOption(name, shaderPackOptions);
/*      */   }
/*      */ 
/*      */   
/*      */   public static ShaderOption[] getShaderPackOptions() {
/*  906 */     return shaderPackOptions;
/*      */   }
/*      */ 
/*      */   
/*      */   private static ShaderOption[] getVisibleOptions(ShaderOption[] ops) {
/*  911 */     ArrayList<ShaderOption> list = new ArrayList();
/*      */     
/*  913 */     for (int sos = 0; sos < ops.length; sos++) {
/*      */       
/*  915 */       ShaderOption so = ops[sos];
/*      */       
/*  917 */       if (so.isVisible())
/*      */       {
/*  919 */         list.add(so);
/*      */       }
/*      */     } 
/*      */     
/*  923 */     ShaderOption[] var4 = list.<ShaderOption>toArray(new ShaderOption[list.size()]);
/*  924 */     return var4;
/*      */   }
/*      */ 
/*      */   
/*      */   public static void saveShaderPackOptions() {
/*  929 */     saveShaderPackOptions(shaderPackOptions, shaderPack);
/*      */   }
/*      */ 
/*      */   
/*      */   private static void saveShaderPackOptions(ShaderOption[] sos, IShaderPack sp) {
/*  934 */     Properties props = new Properties();
/*      */     
/*  936 */     if (shaderPackOptions != null)
/*      */     {
/*  938 */       for (int e = 0; e < sos.length; e++) {
/*      */         
/*  940 */         ShaderOption so = sos[e];
/*      */         
/*  942 */         if (so.isChanged() && so.isEnabled())
/*      */         {
/*  944 */           props.setProperty(so.getName(), so.getValue());
/*      */         }
/*      */       } 
/*      */     }
/*      */ 
/*      */     
/*      */     try {
/*  951 */       saveOptionProperties(sp, props);
/*      */     }
/*  953 */     catch (IOException var5) {
/*      */       
/*  955 */       Config.warn("[Shaders] Error saving configuration for " + shaderPack.getName());
/*  956 */       var5.printStackTrace();
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   private static void saveOptionProperties(IShaderPack sp, Properties props) throws IOException {
/*  962 */     String path = String.valueOf(shaderpacksdirname) + "/" + sp.getName() + ".txt";
/*  963 */     File propFile = new File((Minecraft.getMinecraft()).mcDataDir, path);
/*      */     
/*  965 */     if (props.isEmpty()) {
/*      */       
/*  967 */       propFile.delete();
/*      */     }
/*      */     else {
/*      */       
/*  971 */       FileOutputStream fos = new FileOutputStream(propFile);
/*  972 */       props.store(fos, (String)null);
/*  973 */       fos.flush();
/*  974 */       fos.close();
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private static ShaderOption[] loadShaderPackOptions() {
/*      */     try {
/*  982 */       ShaderOption[] e = ShaderPackParser.parseShaderPackOptions(shaderPack, programNames, shaderPackDimensions);
/*  983 */       Properties props = loadOptionProperties(shaderPack);
/*      */       
/*  985 */       for (int i = 0; i < e.length; i++) {
/*      */         
/*  987 */         ShaderOption so = e[i];
/*  988 */         String val = props.getProperty(so.getName());
/*      */         
/*  990 */         if (val != null) {
/*      */           
/*  992 */           so.resetValue();
/*      */           
/*  994 */           if (!so.setValue(val))
/*      */           {
/*  996 */             Config.warn("[Shaders] Invalid value, option: " + so.getName() + ", value: " + val);
/*      */           }
/*      */         } 
/*      */       } 
/*      */       
/* 1001 */       return e;
/*      */     }
/* 1003 */     catch (IOException var5) {
/*      */       
/* 1005 */       Config.warn("[Shaders] Error reading configuration for " + shaderPack.getName());
/* 1006 */       var5.printStackTrace();
/* 1007 */       return null;
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   private static Properties loadOptionProperties(IShaderPack sp) throws IOException {
/* 1013 */     Properties props = new Properties();
/* 1014 */     String path = String.valueOf(shaderpacksdirname) + "/" + sp.getName() + ".txt";
/* 1015 */     File propFile = new File((Minecraft.getMinecraft()).mcDataDir, path);
/*      */     
/* 1017 */     if (propFile.exists() && propFile.isFile() && propFile.canRead()) {
/*      */       
/* 1019 */       FileInputStream fis = new FileInputStream(propFile);
/* 1020 */       props.load(fis);
/* 1021 */       fis.close();
/* 1022 */       return props;
/*      */     } 
/*      */ 
/*      */     
/* 1026 */     return props;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public static ShaderOption[] getChangedOptions(ShaderOption[] ops) {
/* 1032 */     ArrayList<ShaderOption> list = new ArrayList();
/*      */     
/* 1034 */     for (int cops = 0; cops < ops.length; cops++) {
/*      */       
/* 1036 */       ShaderOption op = ops[cops];
/*      */       
/* 1038 */       if (op.isEnabled() && op.isChanged())
/*      */       {
/* 1040 */         list.add(op);
/*      */       }
/*      */     } 
/*      */     
/* 1044 */     ShaderOption[] var4 = list.<ShaderOption>toArray(new ShaderOption[list.size()]);
/* 1045 */     return var4;
/*      */   }
/*      */ 
/*      */   
/*      */   private static String applyOptions(String line, ShaderOption[] ops) {
/* 1050 */     if (ops != null && ops.length > 0) {
/*      */       
/* 1052 */       for (int i = 0; i < ops.length; i++) {
/*      */         
/* 1054 */         ShaderOption op = ops[i];
/* 1055 */         String opName = op.getName();
/*      */         
/* 1057 */         if (op.matchesLine(line)) {
/*      */           
/* 1059 */           line = op.getSourceLine();
/*      */           
/*      */           break;
/*      */         } 
/*      */       } 
/* 1064 */       return line;
/*      */     } 
/*      */ 
/*      */     
/* 1068 */     return line;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   static ArrayList listOfShaders() {
/* 1074 */     ArrayList<String> list = new ArrayList();
/* 1075 */     list.add(packNameNone);
/* 1076 */     list.add(packNameDefault);
/*      */ 
/*      */     
/*      */     try {
/* 1080 */       if (!shaderpacksdir.exists())
/*      */       {
/* 1082 */         shaderpacksdir.mkdir();
/*      */       }
/*      */       
/* 1085 */       File[] e = shaderpacksdir.listFiles();
/*      */       
/* 1087 */       for (int i = 0; i < e.length; i++) {
/*      */         
/* 1089 */         File file = e[i];
/* 1090 */         String name = file.getName();
/*      */         
/* 1092 */         if (file.isDirectory())
/*      */         {
/* 1094 */           File subDir = new File(file, "shaders");
/*      */           
/* 1096 */           if (subDir.exists() && subDir.isDirectory())
/*      */           {
/* 1098 */             list.add(name);
/*      */           }
/*      */         }
/* 1101 */         else if (file.isFile() && name.toLowerCase().endsWith(".zip"))
/*      */         {
/* 1103 */           list.add(name);
/*      */         }
/*      */       
/*      */       } 
/* 1107 */     } catch (Exception exception) {}
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1112 */     return list;
/*      */   }
/*      */ 
/*      */   
/*      */   static String versiontostring(int vv) {
/* 1117 */     String vs = Integer.toString(vv);
/* 1118 */     return String.valueOf(Integer.toString(Integer.parseInt(vs.substring(1, 3)))) + "." + Integer.toString(Integer.parseInt(vs.substring(3, 5))) + "." + Integer.toString(Integer.parseInt(vs.substring(5)));
/*      */   }
/*      */ 
/*      */   
/*      */   static void checkOptifine() {}
/*      */   
/*      */   public static int checkFramebufferStatus(String location) {
/* 1125 */     int status = EXTFramebufferObject.glCheckFramebufferStatusEXT(36160);
/*      */     
/* 1127 */     if (status != 36053)
/*      */     {
/* 1129 */       System.err.format("FramebufferStatus 0x%04X at %s\n", new Object[] { Integer.valueOf(status), location });
/*      */     }
/*      */     
/* 1132 */     return status;
/*      */   }
/*      */ 
/*      */   
/*      */   public static int checkGLError(String location) {
/* 1137 */     int errorCode = GL11.glGetError();
/*      */     
/* 1139 */     if (errorCode != 0) {
/*      */       
/* 1141 */       boolean skipPrint = false;
/*      */       
/* 1143 */       if (!skipPrint)
/*      */       {
/* 1145 */         if (errorCode == 1286) {
/*      */           
/* 1147 */           int status = EXTFramebufferObject.glCheckFramebufferStatusEXT(36160);
/* 1148 */           System.err.format("GL error 0x%04X: %s (Fb status 0x%04X) at %s\n", new Object[] { Integer.valueOf(errorCode), GLU.gluErrorString(errorCode), Integer.valueOf(status), location });
/*      */         }
/*      */         else {
/*      */           
/* 1152 */           System.err.format("GL error 0x%04X: %s at %s\n", new Object[] { Integer.valueOf(errorCode), GLU.gluErrorString(errorCode), location });
/*      */         } 
/*      */       }
/*      */     } 
/*      */     
/* 1157 */     return errorCode;
/*      */   }
/*      */ 
/*      */   
/*      */   public static int checkGLError(String location, String info) {
/* 1162 */     int errorCode = GL11.glGetError();
/*      */     
/* 1164 */     if (errorCode != 0)
/*      */     {
/* 1166 */       System.err.format("GL error 0x%04x: %s at %s %s\n", new Object[] { Integer.valueOf(errorCode), GLU.gluErrorString(errorCode), location, info });
/*      */     }
/*      */     
/* 1169 */     return errorCode;
/*      */   }
/*      */ 
/*      */   
/*      */   public static int checkGLError(String location, String info1, String info2) {
/* 1174 */     int errorCode = GL11.glGetError();
/*      */     
/* 1176 */     if (errorCode != 0)
/*      */     {
/* 1178 */       System.err.format("GL error 0x%04x: %s at %s %s %s\n", new Object[] { Integer.valueOf(errorCode), GLU.gluErrorString(errorCode), location, info1, info2 });
/*      */     }
/*      */     
/* 1181 */     return errorCode;
/*      */   }
/*      */ 
/*      */   
/*      */   private static void printChat(String str) {
/* 1186 */     mc.ingameGUI.getChatGUI().printChatMessage((IChatComponent)new ChatComponentText(str));
/*      */   }
/*      */ 
/*      */   
/*      */   private static void printChatAndLogError(String str) {
/* 1191 */     SMCLog.severe(str);
/* 1192 */     mc.ingameGUI.getChatGUI().printChatMessage((IChatComponent)new ChatComponentText(str));
/*      */   }
/*      */ 
/*      */   
/*      */   public static void printIntBuffer(String title, IntBuffer buf) {
/* 1197 */     StringBuilder sb = new StringBuilder(128);
/* 1198 */     sb.append(title).append(" [pos ").append(buf.position()).append(" lim ").append(buf.limit()).append(" cap ").append(buf.capacity()).append(" :");
/* 1199 */     int lim = buf.limit();
/*      */     
/* 1201 */     for (int i = 0; i < lim; i++)
/*      */     {
/* 1203 */       sb.append(" ").append(buf.get(i));
/*      */     }
/*      */     
/* 1206 */     sb.append("]");
/* 1207 */     SMCLog.info(sb.toString());
/*      */   }
/*      */ 
/*      */   
/*      */   public static void startup(Minecraft mc) {
/* 1212 */     checkShadersModInstalled();
/* 1213 */     Shaders.mc = mc;
/* 1214 */     capabilities = GLContext.getCapabilities();
/* 1215 */     glVersionString = GL11.glGetString(7938);
/* 1216 */     glVendorString = GL11.glGetString(7936);
/* 1217 */     glRendererString = GL11.glGetString(7937);
/* 1218 */     SMCLog.info("ShadersMod version: 2.4.12");
/* 1219 */     SMCLog.info("OpenGL Version: " + glVersionString);
/* 1220 */     SMCLog.info("Vendor:  " + glVendorString);
/* 1221 */     SMCLog.info("Renderer: " + glRendererString);
/* 1222 */     SMCLog.info("Capabilities: " + (capabilities.OpenGL20 ? " 2.0 " : " - ") + (capabilities.OpenGL21 ? " 2.1 " : " - ") + (capabilities.OpenGL30 ? " 3.0 " : " - ") + (capabilities.OpenGL32 ? " 3.2 " : " - ") + (capabilities.OpenGL40 ? " 4.0 " : " - "));
/* 1223 */     SMCLog.info("GL_MAX_DRAW_BUFFERS: " + GL11.glGetInteger(34852));
/* 1224 */     SMCLog.info("GL_MAX_COLOR_ATTACHMENTS_EXT: " + GL11.glGetInteger(36063));
/* 1225 */     SMCLog.info("GL_MAX_TEXTURE_IMAGE_UNITS: " + GL11.glGetInteger(34930));
/* 1226 */     hasGlGenMipmap = capabilities.OpenGL30;
/* 1227 */     loadConfig();
/*      */   }
/*      */ 
/*      */   
/*      */   private static String toStringYN(boolean b) {
/* 1232 */     return b ? "Y" : "N";
/*      */   }
/*      */ 
/*      */   
/*      */   public static void updateBlockLightLevel() {
/* 1237 */     if (isOldLighting()) {
/*      */       
/* 1239 */       blockLightLevel05 = 0.5F;
/* 1240 */       blockLightLevel06 = 0.6F;
/* 1241 */       blockLightLevel08 = 0.8F;
/*      */     }
/*      */     else {
/*      */       
/* 1245 */       blockLightLevel05 = 1.0F;
/* 1246 */       blockLightLevel06 = 1.0F;
/* 1247 */       blockLightLevel08 = 1.0F;
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public static boolean isDynamicHandLight() {
/* 1253 */     return !shaderPackDynamicHandLight.isDefault() ? shaderPackDynamicHandLight.isTrue() : true;
/*      */   }
/*      */ 
/*      */   
/*      */   public static boolean isOldLighting() {
/* 1258 */     return !configOldLighting.isDefault() ? configOldLighting.isTrue() : (!shaderPackOldLighting.isDefault() ? shaderPackOldLighting.isTrue() : true);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public static void init() {
/*      */     boolean firstInit;
/* 1265 */     if (!isInitializedOnce) {
/*      */       
/* 1267 */       isInitializedOnce = true;
/* 1268 */       firstInit = true;
/*      */     }
/*      */     else {
/*      */       
/* 1272 */       firstInit = false;
/*      */     } 
/*      */     
/* 1275 */     if (!isShaderPackInitialized) {
/*      */       
/* 1277 */       checkGLError("Shaders.init pre");
/*      */       
/* 1279 */       if (getShaderPackName() != null);
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1284 */       if (!capabilities.OpenGL20)
/*      */       {
/* 1286 */         printChatAndLogError("No OpenGL 2.0");
/*      */       }
/*      */       
/* 1289 */       if (!capabilities.GL_EXT_framebuffer_object)
/*      */       {
/* 1291 */         printChatAndLogError("No EXT_framebuffer_object");
/*      */       }
/*      */       
/* 1294 */       dfbDrawBuffers.position(0).limit(8);
/* 1295 */       dfbColorTextures.position(0).limit(16);
/* 1296 */       dfbDepthTextures.position(0).limit(3);
/* 1297 */       sfbDrawBuffers.position(0).limit(8);
/* 1298 */       sfbDepthTextures.position(0).limit(2);
/* 1299 */       sfbColorTextures.position(0).limit(8);
/* 1300 */       usedColorBuffers = 4;
/* 1301 */       usedDepthBuffers = 1;
/* 1302 */       usedShadowColorBuffers = 0;
/* 1303 */       usedShadowDepthBuffers = 0;
/* 1304 */       usedColorAttachs = 1;
/* 1305 */       usedDrawBuffers = 1;
/* 1306 */       Arrays.fill(gbuffersFormat, 6408);
/* 1307 */       Arrays.fill(shadowHardwareFilteringEnabled, false);
/* 1308 */       Arrays.fill(shadowMipmapEnabled, false);
/* 1309 */       Arrays.fill(shadowFilterNearest, false);
/* 1310 */       Arrays.fill(shadowColorMipmapEnabled, false);
/* 1311 */       Arrays.fill(shadowColorFilterNearest, false);
/* 1312 */       centerDepthSmoothEnabled = false;
/* 1313 */       noiseTextureEnabled = false;
/* 1314 */       sunPathRotation = 0.0F;
/* 1315 */       shadowIntervalSize = 2.0F;
/* 1316 */       aoLevel = 0.8F;
/* 1317 */       blockAoLight = 1.0F - aoLevel;
/* 1318 */       useEntityAttrib = false;
/* 1319 */       useMidTexCoordAttrib = false;
/* 1320 */       useMultiTexCoord3Attrib = false;
/* 1321 */       useTangentAttrib = false;
/* 1322 */       waterShadowEnabled = false;
/* 1323 */       updateChunksErrorRecorded = false;
/* 1324 */       updateBlockLightLevel();
/* 1325 */       ShaderProfile activeProfile = ShaderUtils.detectProfile(shaderPackProfiles, shaderPackOptions, false);
/* 1326 */       String worldPrefix = "";
/*      */ 
/*      */       
/* 1329 */       if (currentWorld != null) {
/*      */         
/* 1331 */         int i = currentWorld.provider.getDimensionId();
/*      */         
/* 1333 */         if (shaderPackDimensions.contains(Integer.valueOf(i)))
/*      */         {
/* 1335 */           worldPrefix = "world" + i + "/";
/*      */         }
/*      */       } 
/*      */       
/* 1339 */       if (saveFinalShaders)
/*      */       {
/* 1341 */         clearDirectory(new File(shaderpacksdir, "debug"));
/*      */       }
/*      */       
/*      */       int maxDrawBuffers;
/*      */       
/* 1346 */       for (maxDrawBuffers = 0; maxDrawBuffers < 33; maxDrawBuffers++) {
/*      */         
/* 1348 */         String drawBuffersMap = programNames[maxDrawBuffers];
/*      */         
/* 1350 */         if (drawBuffersMap.equals("")) {
/*      */           
/* 1352 */           programsRef[maxDrawBuffers] = 0; programsID[maxDrawBuffers] = 0;
/* 1353 */           programsDrawBufSettings[maxDrawBuffers] = null;
/* 1354 */           programsColorAtmSettings[maxDrawBuffers] = null;
/* 1355 */           programsCompositeMipmapSetting[maxDrawBuffers] = 0;
/*      */         }
/*      */         else {
/*      */           
/* 1359 */           newDrawBufSetting = null;
/* 1360 */           newColorAtmSetting = null;
/* 1361 */           newCompositeMipmapSetting = 0;
/* 1362 */           String i = String.valueOf(worldPrefix) + drawBuffersMap;
/*      */           
/* 1364 */           if (activeProfile != null && activeProfile.isProgramDisabled(i)) {
/*      */             
/* 1366 */             SMCLog.info("Program disabled: " + i);
/* 1367 */             drawBuffersMap = "<disabled>";
/* 1368 */             i = String.valueOf(worldPrefix) + drawBuffersMap;
/*      */           } 
/*      */           
/* 1371 */           String n = "/shaders/" + i;
/* 1372 */           int intbuf = setupProgram(maxDrawBuffers, String.valueOf(n) + ".vsh", String.valueOf(n) + ".fsh");
/*      */           
/* 1374 */           if (intbuf > 0)
/*      */           {
/* 1376 */             SMCLog.info("Program loaded: " + i);
/*      */           }
/*      */           
/* 1379 */           programsRef[maxDrawBuffers] = intbuf; programsID[maxDrawBuffers] = intbuf;
/* 1380 */           programsDrawBufSettings[maxDrawBuffers] = (intbuf != 0) ? newDrawBufSetting : null;
/* 1381 */           programsColorAtmSettings[maxDrawBuffers] = (intbuf != 0) ? newColorAtmSetting : null;
/* 1382 */           programsCompositeMipmapSetting[maxDrawBuffers] = (intbuf != 0) ? newCompositeMipmapSetting : 0;
/*      */         } 
/*      */       } 
/*      */       
/* 1386 */       maxDrawBuffers = GL11.glGetInteger(34852);
/*      */       
/*      */       int var12;
/*      */       
/* 1390 */       for (var12 = 0; var12 < 33; var12++) {
/*      */         
/* 1392 */         Arrays.fill(programsToggleColorTextures[var12], false);
/*      */         
/* 1394 */         if (var12 == 29) {
/*      */           
/* 1396 */           programsDrawBuffers[var12] = null;
/*      */         }
/* 1398 */         else if (programsID[var12] == 0) {
/*      */           
/* 1400 */           if (var12 == 30)
/*      */           {
/* 1402 */             programsDrawBuffers[var12] = drawBuffersNone;
/*      */           }
/*      */           else
/*      */           {
/* 1406 */             programsDrawBuffers[var12] = drawBuffersColorAtt0;
/*      */           }
/*      */         
/*      */         } else {
/*      */           
/* 1411 */           String n = programsDrawBufSettings[var12];
/*      */           
/* 1413 */           if (n != null) {
/*      */             
/* 1415 */             IntBuffer var14 = drawBuffersBuffer[var12];
/* 1416 */             int numDB = n.length();
/*      */             
/* 1418 */             if (numDB > usedDrawBuffers)
/*      */             {
/* 1420 */               usedDrawBuffers = numDB;
/*      */             }
/*      */             
/* 1423 */             if (numDB > maxDrawBuffers)
/*      */             {
/* 1425 */               numDB = maxDrawBuffers;
/*      */             }
/*      */             
/* 1428 */             programsDrawBuffers[var12] = var14;
/* 1429 */             var14.limit(numDB);
/*      */             
/* 1431 */             for (int i1 = 0; i1 < numDB; i1++)
/*      */             {
/* 1433 */               int drawBuffer = 0;
/*      */               
/* 1435 */               if (n.length() > i1) {
/*      */                 
/* 1437 */                 int ca = n.charAt(i1) - 48;
/*      */                 
/* 1439 */                 if (var12 != 30) {
/*      */                   
/* 1441 */                   if (ca >= 0 && ca <= 7)
/*      */                   {
/* 1443 */                     programsToggleColorTextures[var12][ca] = true;
/* 1444 */                     drawBuffer = ca + 36064;
/*      */                     
/* 1446 */                     if (ca > usedColorAttachs)
/*      */                     {
/* 1448 */                       usedColorAttachs = ca;
/*      */                     }
/*      */                     
/* 1451 */                     if (ca > usedColorBuffers)
/*      */                     {
/* 1453 */                       usedColorBuffers = ca;
/*      */                     }
/*      */                   }
/*      */                 
/* 1457 */                 } else if (ca >= 0 && ca <= 1) {
/*      */                   
/* 1459 */                   drawBuffer = ca + 36064;
/*      */                   
/* 1461 */                   if (ca > usedShadowColorBuffers)
/*      */                   {
/* 1463 */                     usedShadowColorBuffers = ca;
/*      */                   }
/*      */                 } 
/*      */               } 
/*      */               
/* 1468 */               var14.put(i1, drawBuffer);
/*      */             }
/*      */           
/* 1471 */           } else if (var12 != 30 && var12 != 31 && var12 != 32) {
/*      */             
/* 1473 */             programsDrawBuffers[var12] = dfbDrawBuffers;
/* 1474 */             usedDrawBuffers = usedColorBuffers;
/* 1475 */             Arrays.fill(programsToggleColorTextures[var12], 0, usedColorBuffers, true);
/*      */           }
/*      */           else {
/*      */             
/* 1479 */             programsDrawBuffers[var12] = sfbDrawBuffers;
/*      */           } 
/*      */         } 
/*      */       } 
/*      */       
/* 1484 */       usedColorAttachs = usedColorBuffers;
/* 1485 */       shadowPassInterval = (usedShadowDepthBuffers > 0) ? 1 : 0;
/* 1486 */       shouldSkipDefaultShadow = (usedShadowDepthBuffers > 0);
/* 1487 */       SMCLog.info("usedColorBuffers: " + usedColorBuffers);
/* 1488 */       SMCLog.info("usedDepthBuffers: " + usedDepthBuffers);
/* 1489 */       SMCLog.info("usedShadowColorBuffers: " + usedShadowColorBuffers);
/* 1490 */       SMCLog.info("usedShadowDepthBuffers: " + usedShadowDepthBuffers);
/* 1491 */       SMCLog.info("usedColorAttachs: " + usedColorAttachs);
/* 1492 */       SMCLog.info("usedDrawBuffers: " + usedDrawBuffers);
/* 1493 */       dfbDrawBuffers.position(0).limit(usedDrawBuffers);
/* 1494 */       dfbColorTextures.position(0).limit(usedColorBuffers * 2);
/*      */       
/* 1496 */       for (var12 = 0; var12 < usedDrawBuffers; var12++)
/*      */       {
/* 1498 */         dfbDrawBuffers.put(var12, 36064 + var12);
/*      */       }
/*      */       
/* 1501 */       if (usedDrawBuffers > maxDrawBuffers)
/*      */       {
/* 1503 */         printChatAndLogError("[Shaders] Error: Not enough draw buffers, needed: " + usedDrawBuffers + ", available: " + maxDrawBuffers);
/*      */       }
/*      */       
/* 1506 */       sfbDrawBuffers.position(0).limit(usedShadowColorBuffers);
/*      */       
/* 1508 */       for (var12 = 0; var12 < usedShadowColorBuffers; var12++)
/*      */       {
/* 1510 */         sfbDrawBuffers.put(var12, 36064 + var12);
/*      */       }
/*      */       
/* 1513 */       for (var12 = 0; var12 < 33; var12++) {
/*      */         int var13;
/*      */ 
/*      */         
/* 1517 */         for (var13 = var12; programsID[var13] == 0 && programBackups[var13] != var13; var13 = programBackups[var13]);
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1522 */         if (var13 != var12 && var12 != 30) {
/*      */           
/* 1524 */           programsID[var12] = programsID[var13];
/* 1525 */           programsDrawBufSettings[var12] = programsDrawBufSettings[var13];
/* 1526 */           programsDrawBuffers[var12] = programsDrawBuffers[var13];
/*      */         } 
/*      */       } 
/*      */       
/* 1530 */       resize();
/* 1531 */       resizeShadow();
/*      */       
/* 1533 */       if (noiseTextureEnabled)
/*      */       {
/* 1535 */         setupNoiseTexture();
/*      */       }
/*      */       
/* 1538 */       if (defaultTexture == null)
/*      */       {
/* 1540 */         defaultTexture = ShadersTex.createDefaultTexture();
/*      */       }
/*      */       
/* 1543 */       GlStateManager.pushMatrix();
/* 1544 */       GlStateManager.rotate(-90.0F, 0.0F, 1.0F, 0.0F);
/* 1545 */       preCelestialRotate();
/* 1546 */       postCelestialRotate();
/* 1547 */       GlStateManager.popMatrix();
/* 1548 */       isShaderPackInitialized = true;
/* 1549 */       loadEntityDataMap();
/* 1550 */       resetDisplayList();
/*      */       
/* 1552 */       if (!firstInit);
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1557 */       checkGLError("Shaders.init");
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public static void resetDisplayList() {
/* 1563 */     numberResetDisplayList++;
/* 1564 */     needResetModels = true;
/* 1565 */     SMCLog.info("Reset world renderers");
/* 1566 */     mc.renderGlobal.loadRenderers();
/*      */   }
/*      */ 
/*      */   
/*      */   public static void resetDisplayListModels() {
/* 1571 */     if (needResetModels) {
/*      */       
/* 1573 */       needResetModels = false;
/* 1574 */       SMCLog.info("Reset model renderers");
/* 1575 */       Iterator<Render> it = mc.getRenderManager().getEntityRenderMap().values().iterator();
/*      */       
/* 1577 */       while (it.hasNext()) {
/*      */         
/* 1579 */         Render ren = it.next();
/*      */         
/* 1581 */         if (ren instanceof RendererLivingEntity) {
/*      */           
/* 1583 */           RendererLivingEntity rle = (RendererLivingEntity)ren;
/* 1584 */           resetDisplayListModel(rle.getMainModel());
/*      */         } 
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public static void resetDisplayListModel(ModelBase model) {
/* 1592 */     if (model != null) {
/*      */       
/* 1594 */       Iterator it = model.boxList.iterator();
/*      */       
/* 1596 */       while (it.hasNext()) {
/*      */         
/* 1598 */         Object obj = it.next();
/*      */         
/* 1600 */         if (obj instanceof ModelRenderer)
/*      */         {
/* 1602 */           resetDisplayListModelRenderer((ModelRenderer)obj);
/*      */         }
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public static void resetDisplayListModelRenderer(ModelRenderer mrr) {
/* 1610 */     mrr.resetDisplayList();
/*      */     
/* 1612 */     if (mrr.childModels != null) {
/*      */       
/* 1614 */       int i = 0;
/*      */       
/* 1616 */       for (int n = mrr.childModels.size(); i < n; i++)
/*      */       {
/* 1618 */         resetDisplayListModelRenderer(mrr.childModels.get(i));
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   private static int setupProgram(int program, String vShaderPath, String fShaderPath) {
/* 1625 */     checkGLError("pre setupProgram");
/* 1626 */     int programid = ARBShaderObjects.glCreateProgramObjectARB();
/* 1627 */     checkGLError("create");
/*      */     
/* 1629 */     if (programid != 0) {
/*      */       
/* 1631 */       progUseEntityAttrib = false;
/* 1632 */       progUseMidTexCoordAttrib = false;
/* 1633 */       progUseTangentAttrib = false;
/* 1634 */       int vShader = createVertShader(vShaderPath);
/* 1635 */       int fShader = createFragShader(fShaderPath);
/* 1636 */       checkGLError("create");
/*      */       
/* 1638 */       if (vShader == 0 && fShader == 0) {
/*      */         
/* 1640 */         ARBShaderObjects.glDeleteObjectARB(programid);
/* 1641 */         programid = 0;
/*      */       }
/*      */       else {
/*      */         
/* 1645 */         if (vShader != 0) {
/*      */           
/* 1647 */           ARBShaderObjects.glAttachObjectARB(programid, vShader);
/* 1648 */           checkGLError("attach");
/*      */         } 
/*      */         
/* 1651 */         if (fShader != 0) {
/*      */           
/* 1653 */           ARBShaderObjects.glAttachObjectARB(programid, fShader);
/* 1654 */           checkGLError("attach");
/*      */         } 
/*      */         
/* 1657 */         if (progUseEntityAttrib) {
/*      */           
/* 1659 */           ARBVertexShader.glBindAttribLocationARB(programid, entityAttrib, "mc_Entity");
/* 1660 */           checkGLError("mc_Entity");
/*      */         } 
/*      */         
/* 1663 */         if (progUseMidTexCoordAttrib) {
/*      */           
/* 1665 */           ARBVertexShader.glBindAttribLocationARB(programid, midTexCoordAttrib, "mc_midTexCoord");
/* 1666 */           checkGLError("mc_midTexCoord");
/*      */         } 
/*      */         
/* 1669 */         if (progUseTangentAttrib) {
/*      */           
/* 1671 */           ARBVertexShader.glBindAttribLocationARB(programid, tangentAttrib, "at_tangent");
/* 1672 */           checkGLError("at_tangent");
/*      */         } 
/*      */         
/* 1675 */         ARBShaderObjects.glLinkProgramARB(programid);
/*      */         
/* 1677 */         if (GL20.glGetProgrami(programid, 35714) != 1)
/*      */         {
/* 1679 */           SMCLog.severe("Error linking program: " + programid);
/*      */         }
/*      */         
/* 1682 */         printLogInfo(programid, String.valueOf(vShaderPath) + ", " + fShaderPath);
/*      */         
/* 1684 */         if (vShader != 0) {
/*      */           
/* 1686 */           ARBShaderObjects.glDetachObjectARB(programid, vShader);
/* 1687 */           ARBShaderObjects.glDeleteObjectARB(vShader);
/*      */         } 
/*      */         
/* 1690 */         if (fShader != 0) {
/*      */           
/* 1692 */           ARBShaderObjects.glDetachObjectARB(programid, fShader);
/* 1693 */           ARBShaderObjects.glDeleteObjectARB(fShader);
/*      */         } 
/*      */         
/* 1696 */         programsID[program] = programid;
/* 1697 */         useProgram(program);
/* 1698 */         ARBShaderObjects.glValidateProgramARB(programid);
/* 1699 */         useProgram(0);
/* 1700 */         printLogInfo(programid, String.valueOf(vShaderPath) + ", " + fShaderPath);
/* 1701 */         int valid = GL20.glGetProgrami(programid, 35715);
/*      */         
/* 1703 */         if (valid != 1) {
/*      */           
/* 1705 */           String Q = "\"";
/* 1706 */           printChatAndLogError("[Shaders] Error: Invalid program " + Q + programNames[program] + Q);
/* 1707 */           ARBShaderObjects.glDeleteObjectARB(programid);
/* 1708 */           programid = 0;
/*      */         } 
/*      */       } 
/*      */     } 
/*      */     
/* 1713 */     return programid;
/*      */   }
/*      */ 
/*      */   
/*      */   private static int createVertShader(String filename) {
/* 1718 */     int vertShader = ARBShaderObjects.glCreateShaderObjectARB(35633);
/*      */     
/* 1720 */     if (vertShader == 0)
/*      */     {
/* 1722 */       return 0;
/*      */     }
/*      */ 
/*      */     
/* 1726 */     StringBuilder vertexCode = new StringBuilder(131072);
/* 1727 */     BufferedReader reader = null;
/*      */ 
/*      */     
/*      */     try {
/* 1731 */       reader = new BufferedReader(new InputStreamReader(shaderPack.getResourceAsStream(filename)));
/*      */     }
/* 1733 */     catch (Exception var8) {
/*      */ 
/*      */       
/*      */       try {
/* 1737 */         reader = new BufferedReader(new FileReader(new File(filename)));
/*      */       }
/* 1739 */       catch (Exception var7) {
/*      */         
/* 1741 */         ARBShaderObjects.glDeleteObjectARB(vertShader);
/* 1742 */         return 0;
/*      */       } 
/*      */     } 
/*      */     
/* 1746 */     ShaderOption[] activeOptions = getChangedOptions(shaderPackOptions);
/*      */     
/* 1748 */     if (reader != null) {
/*      */       
/*      */       try {
/*      */         
/* 1752 */         reader = ShaderPackParser.resolveIncludes(reader, filename, shaderPack, 0);
/*      */         
/*      */         String line;
/* 1755 */         while ((line = reader.readLine()) != null) {
/*      */           
/* 1757 */           line = applyOptions(line, activeOptions);
/* 1758 */           vertexCode.append(line).append('\n');
/*      */           
/* 1760 */           if (line.matches("attribute [_a-zA-Z0-9]+ mc_Entity.*")) {
/*      */             
/* 1762 */             useEntityAttrib = true;
/* 1763 */             progUseEntityAttrib = true; continue;
/*      */           } 
/* 1765 */           if (line.matches("attribute [_a-zA-Z0-9]+ mc_midTexCoord.*")) {
/*      */             
/* 1767 */             useMidTexCoordAttrib = true;
/* 1768 */             progUseMidTexCoordAttrib = true; continue;
/*      */           } 
/* 1770 */           if (line.matches(".*gl_MultiTexCoord3.*")) {
/*      */             
/* 1772 */             useMultiTexCoord3Attrib = true; continue;
/*      */           } 
/* 1774 */           if (line.matches("attribute [_a-zA-Z0-9]+ at_tangent.*")) {
/*      */             
/* 1776 */             useTangentAttrib = true;
/* 1777 */             progUseTangentAttrib = true;
/*      */           } 
/*      */         } 
/*      */         
/* 1781 */         reader.close();
/*      */       }
/* 1783 */       catch (Exception var9) {
/*      */         
/* 1785 */         SMCLog.severe("Couldn't read " + filename + "!");
/* 1786 */         var9.printStackTrace();
/* 1787 */         ARBShaderObjects.glDeleteObjectARB(vertShader);
/* 1788 */         return 0;
/*      */       } 
/*      */     }
/*      */     
/* 1792 */     if (saveFinalShaders)
/*      */     {
/* 1794 */       saveShader(filename, vertexCode.toString());
/*      */     }
/*      */     
/* 1797 */     ARBShaderObjects.glShaderSourceARB(vertShader, vertexCode);
/* 1798 */     ARBShaderObjects.glCompileShaderARB(vertShader);
/*      */     
/* 1800 */     if (GL20.glGetShaderi(vertShader, 35713) != 1)
/*      */     {
/* 1802 */       SMCLog.severe("Error compiling vertex shader: " + filename);
/*      */     }
/*      */     
/* 1805 */     printShaderLogInfo(vertShader, filename);
/* 1806 */     return vertShader;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private static int createFragShader(String filename) {
/* 1812 */     int fragShader = ARBShaderObjects.glCreateShaderObjectARB(35632);
/*      */     
/* 1814 */     if (fragShader == 0)
/*      */     {
/* 1816 */       return 0;
/*      */     }
/*      */ 
/*      */     
/* 1820 */     StringBuilder fragCode = new StringBuilder(131072);
/* 1821 */     BufferedReader reader = null;
/*      */ 
/*      */     
/*      */     try {
/* 1825 */       reader = new BufferedReader(new InputStreamReader(shaderPack.getResourceAsStream(filename)));
/*      */     }
/* 1827 */     catch (Exception var12) {
/*      */ 
/*      */       
/*      */       try {
/* 1831 */         reader = new BufferedReader(new FileReader(new File(filename)));
/*      */       }
/* 1833 */       catch (Exception var11) {
/*      */         
/* 1835 */         ARBShaderObjects.glDeleteObjectARB(fragShader);
/* 1836 */         return 0;
/*      */       } 
/*      */     } 
/*      */     
/* 1840 */     ShaderOption[] activeOptions = getChangedOptions(shaderPackOptions);
/*      */     
/* 1842 */     if (reader != null) {
/*      */       
/*      */       try {
/*      */         
/* 1846 */         reader = ShaderPackParser.resolveIncludes(reader, filename, shaderPack, 0);
/*      */         
/*      */         String line;
/* 1849 */         while ((line = reader.readLine()) != null) {
/*      */           
/* 1851 */           line = applyOptions(line, activeOptions);
/* 1852 */           fragCode.append(line).append('\n');
/*      */           
/* 1854 */           if (!line.matches("#version .*")) {
/*      */             
/* 1856 */             if (line.matches("uniform [ _a-zA-Z0-9]+ shadow;.*")) {
/*      */               
/* 1858 */               if (usedShadowDepthBuffers < 1)
/*      */               {
/* 1860 */                 usedShadowDepthBuffers = 1; } 
/*      */               continue;
/*      */             } 
/* 1863 */             if (line.matches("uniform [ _a-zA-Z0-9]+ watershadow;.*")) {
/*      */               
/* 1865 */               waterShadowEnabled = true;
/*      */               
/* 1867 */               if (usedShadowDepthBuffers < 2)
/*      */               {
/* 1869 */                 usedShadowDepthBuffers = 2; } 
/*      */               continue;
/*      */             } 
/* 1872 */             if (line.matches("uniform [ _a-zA-Z0-9]+ shadowtex0;.*")) {
/*      */               
/* 1874 */               if (usedShadowDepthBuffers < 1)
/*      */               {
/* 1876 */                 usedShadowDepthBuffers = 1; } 
/*      */               continue;
/*      */             } 
/* 1879 */             if (line.matches("uniform [ _a-zA-Z0-9]+ shadowtex1;.*")) {
/*      */               
/* 1881 */               if (usedShadowDepthBuffers < 2)
/*      */               {
/* 1883 */                 usedShadowDepthBuffers = 2; } 
/*      */               continue;
/*      */             } 
/* 1886 */             if (line.matches("uniform [ _a-zA-Z0-9]+ shadowcolor;.*")) {
/*      */               
/* 1888 */               if (usedShadowColorBuffers < 1)
/*      */               {
/* 1890 */                 usedShadowColorBuffers = 1; } 
/*      */               continue;
/*      */             } 
/* 1893 */             if (line.matches("uniform [ _a-zA-Z0-9]+ shadowcolor0;.*")) {
/*      */               
/* 1895 */               if (usedShadowColorBuffers < 1)
/*      */               {
/* 1897 */                 usedShadowColorBuffers = 1; } 
/*      */               continue;
/*      */             } 
/* 1900 */             if (line.matches("uniform [ _a-zA-Z0-9]+ shadowcolor1;.*")) {
/*      */               
/* 1902 */               if (usedShadowColorBuffers < 2)
/*      */               {
/* 1904 */                 usedShadowColorBuffers = 2; } 
/*      */               continue;
/*      */             } 
/* 1907 */             if (line.matches("uniform [ _a-zA-Z0-9]+ shadowcolor2;.*")) {
/*      */               
/* 1909 */               if (usedShadowColorBuffers < 3)
/*      */               {
/* 1911 */                 usedShadowColorBuffers = 3; } 
/*      */               continue;
/*      */             } 
/* 1914 */             if (line.matches("uniform [ _a-zA-Z0-9]+ shadowcolor3;.*")) {
/*      */               
/* 1916 */               if (usedShadowColorBuffers < 4)
/*      */               {
/* 1918 */                 usedShadowColorBuffers = 4; } 
/*      */               continue;
/*      */             } 
/* 1921 */             if (line.matches("uniform [ _a-zA-Z0-9]+ depthtex0;.*")) {
/*      */               
/* 1923 */               if (usedDepthBuffers < 1)
/*      */               {
/* 1925 */                 usedDepthBuffers = 1; } 
/*      */               continue;
/*      */             } 
/* 1928 */             if (line.matches("uniform [ _a-zA-Z0-9]+ depthtex1;.*")) {
/*      */               
/* 1930 */               if (usedDepthBuffers < 2)
/*      */               {
/* 1932 */                 usedDepthBuffers = 2; } 
/*      */               continue;
/*      */             } 
/* 1935 */             if (line.matches("uniform [ _a-zA-Z0-9]+ depthtex2;.*")) {
/*      */               
/* 1937 */               if (usedDepthBuffers < 3)
/*      */               {
/* 1939 */                 usedDepthBuffers = 3; } 
/*      */               continue;
/*      */             } 
/* 1942 */             if (line.matches("uniform [ _a-zA-Z0-9]+ gdepth;.*")) {
/*      */               
/* 1944 */               if (gbuffersFormat[1] == 6408)
/*      */               {
/* 1946 */                 gbuffersFormat[1] = 34836; } 
/*      */               continue;
/*      */             } 
/* 1949 */             if (usedColorBuffers < 5 && line.matches("uniform [ _a-zA-Z0-9]+ gaux1;.*")) {
/*      */               
/* 1951 */               usedColorBuffers = 5; continue;
/*      */             } 
/* 1953 */             if (usedColorBuffers < 6 && line.matches("uniform [ _a-zA-Z0-9]+ gaux2;.*")) {
/*      */               
/* 1955 */               usedColorBuffers = 6; continue;
/*      */             } 
/* 1957 */             if (usedColorBuffers < 7 && line.matches("uniform [ _a-zA-Z0-9]+ gaux3;.*")) {
/*      */               
/* 1959 */               usedColorBuffers = 7; continue;
/*      */             } 
/* 1961 */             if (usedColorBuffers < 8 && line.matches("uniform [ _a-zA-Z0-9]+ gaux4;.*")) {
/*      */               
/* 1963 */               usedColorBuffers = 8; continue;
/*      */             } 
/* 1965 */             if (usedColorBuffers < 5 && line.matches("uniform [ _a-zA-Z0-9]+ colortex4;.*")) {
/*      */               
/* 1967 */               usedColorBuffers = 5; continue;
/*      */             } 
/* 1969 */             if (usedColorBuffers < 6 && line.matches("uniform [ _a-zA-Z0-9]+ colortex5;.*")) {
/*      */               
/* 1971 */               usedColorBuffers = 6; continue;
/*      */             } 
/* 1973 */             if (usedColorBuffers < 7 && line.matches("uniform [ _a-zA-Z0-9]+ colortex6;.*")) {
/*      */               
/* 1975 */               usedColorBuffers = 7; continue;
/*      */             } 
/* 1977 */             if (usedColorBuffers < 8 && line.matches("uniform [ _a-zA-Z0-9]+ colortex7;.*")) {
/*      */               
/* 1979 */               usedColorBuffers = 8; continue;
/*      */             } 
/* 1981 */             if (usedColorBuffers < 8 && line.matches("uniform [ _a-zA-Z0-9]+ centerDepthSmooth;.*")) {
/*      */               
/* 1983 */               centerDepthSmoothEnabled = true;
/*      */ 
/*      */               
/*      */               continue;
/*      */             } 
/*      */             
/* 1989 */             if (line.matches("/\\* SHADOWRES:[0-9]+ \\*/.*")) {
/*      */               
/* 1991 */               String[] e = line.split("(:| )", 4);
/* 1992 */               SMCLog.info("Shadow map resolution: " + e[2]);
/* 1993 */               spShadowMapWidth = spShadowMapHeight = Integer.parseInt(e[2]);
/* 1994 */               shadowMapWidth = shadowMapHeight = Math.round(spShadowMapWidth * configShadowResMul); continue;
/*      */             } 
/* 1996 */             if (line.matches("[ \t]*const[ \t]*int[ \t]*shadowMapResolution[ \t]*=[ \t]*-?[0-9.]+f?;.*")) {
/*      */               
/* 1998 */               String[] e = line.split("(=[ \t]*|;)");
/* 1999 */               SMCLog.info("Shadow map resolution: " + e[1]);
/* 2000 */               spShadowMapWidth = spShadowMapHeight = Integer.parseInt(e[1]);
/* 2001 */               shadowMapWidth = shadowMapHeight = Math.round(spShadowMapWidth * configShadowResMul); continue;
/*      */             } 
/* 2003 */             if (line.matches("/\\* SHADOWFOV:[0-9\\.]+ \\*/.*")) {
/*      */               
/* 2005 */               String[] e = line.split("(:| )", 4);
/* 2006 */               SMCLog.info("Shadow map field of view: " + e[2]);
/* 2007 */               shadowMapFOV = Float.parseFloat(e[2]);
/* 2008 */               shadowMapIsOrtho = false; continue;
/*      */             } 
/* 2010 */             if (line.matches("/\\* SHADOWHPL:[0-9\\.]+ \\*/.*")) {
/*      */               
/* 2012 */               String[] e = line.split("(:| )", 4);
/* 2013 */               SMCLog.info("Shadow map half-plane: " + e[2]);
/* 2014 */               shadowMapHalfPlane = Float.parseFloat(e[2]);
/* 2015 */               shadowMapIsOrtho = true; continue;
/*      */             } 
/* 2017 */             if (line.matches("[ \t]*const[ \t]*float[ \t]*shadowDistance[ \t]*=[ \t]*-?[0-9.]+f?;.*")) {
/*      */               
/* 2019 */               String[] e = line.split("(=[ \t]*|;)");
/* 2020 */               SMCLog.info("Shadow map distance: " + e[1]);
/* 2021 */               shadowMapHalfPlane = Float.parseFloat(e[1]);
/* 2022 */               shadowMapIsOrtho = true; continue;
/*      */             } 
/* 2024 */             if (line.matches("[ \t]*const[ \t]*float[ \t]*shadowIntervalSize[ \t]*=[ \t]*-?[0-9.]+f?;.*")) {
/*      */               
/* 2026 */               String[] e = line.split("(=[ \t]*|;)");
/* 2027 */               SMCLog.info("Shadow map interval size: " + e[1]);
/* 2028 */               shadowIntervalSize = Float.parseFloat(e[1]); continue;
/*      */             } 
/* 2030 */             if (line.matches("[ \t]*const[ \t]*bool[ \t]*generateShadowMipmap[ \t]*=[ \t]*true[ \t]*;.*")) {
/*      */               
/* 2032 */               SMCLog.info("Generate shadow mipmap");
/* 2033 */               Arrays.fill(shadowMipmapEnabled, true); continue;
/*      */             } 
/* 2035 */             if (line.matches("[ \t]*const[ \t]*bool[ \t]*generateShadowColorMipmap[ \t]*=[ \t]*true[ \t]*;.*")) {
/*      */               
/* 2037 */               SMCLog.info("Generate shadow color mipmap");
/* 2038 */               Arrays.fill(shadowColorMipmapEnabled, true); continue;
/*      */             } 
/* 2040 */             if (line.matches("[ \t]*const[ \t]*bool[ \t]*shadowHardwareFiltering[ \t]*=[ \t]*true[ \t]*;.*")) {
/*      */               
/* 2042 */               SMCLog.info("Hardware shadow filtering enabled.");
/* 2043 */               Arrays.fill(shadowHardwareFilteringEnabled, true); continue;
/*      */             } 
/* 2045 */             if (line.matches("[ \t]*const[ \t]*bool[ \t]*shadowHardwareFiltering0[ \t]*=[ \t]*true[ \t]*;.*")) {
/*      */               
/* 2047 */               SMCLog.info("shadowHardwareFiltering0");
/* 2048 */               shadowHardwareFilteringEnabled[0] = true; continue;
/*      */             } 
/* 2050 */             if (line.matches("[ \t]*const[ \t]*bool[ \t]*shadowHardwareFiltering1[ \t]*=[ \t]*true[ \t]*;.*")) {
/*      */               
/* 2052 */               SMCLog.info("shadowHardwareFiltering1");
/* 2053 */               shadowHardwareFilteringEnabled[1] = true; continue;
/*      */             } 
/* 2055 */             if (line.matches("[ \t]*const[ \t]*bool[ \t]*(shadowtex0Mipmap|shadowtexMipmap)[ \t]*=[ \t]*true[ \t]*;.*")) {
/*      */               
/* 2057 */               SMCLog.info("shadowtex0Mipmap");
/* 2058 */               shadowMipmapEnabled[0] = true; continue;
/*      */             } 
/* 2060 */             if (line.matches("[ \t]*const[ \t]*bool[ \t]*(shadowtex1Mipmap)[ \t]*=[ \t]*true[ \t]*;.*")) {
/*      */               
/* 2062 */               SMCLog.info("shadowtex1Mipmap");
/* 2063 */               shadowMipmapEnabled[1] = true; continue;
/*      */             } 
/* 2065 */             if (line.matches("[ \t]*const[ \t]*bool[ \t]*(shadowcolor0Mipmap|shadowColor0Mipmap)[ \t]*=[ \t]*true[ \t]*;.*")) {
/*      */               
/* 2067 */               SMCLog.info("shadowcolor0Mipmap");
/* 2068 */               shadowColorMipmapEnabled[0] = true; continue;
/*      */             } 
/* 2070 */             if (line.matches("[ \t]*const[ \t]*bool[ \t]*(shadowcolor1Mipmap|shadowColor1Mipmap)[ \t]*=[ \t]*true[ \t]*;.*")) {
/*      */               
/* 2072 */               SMCLog.info("shadowcolor1Mipmap");
/* 2073 */               shadowColorMipmapEnabled[1] = true; continue;
/*      */             } 
/* 2075 */             if (line.matches("[ \t]*const[ \t]*bool[ \t]*(shadowtex0Nearest|shadowtexNearest|shadow0MinMagNearest)[ \t]*=[ \t]*true[ \t]*;.*")) {
/*      */               
/* 2077 */               SMCLog.info("shadowtex0Nearest");
/* 2078 */               shadowFilterNearest[0] = true; continue;
/*      */             } 
/* 2080 */             if (line.matches("[ \t]*const[ \t]*bool[ \t]*(shadowtex1Nearest|shadow1MinMagNearest)[ \t]*=[ \t]*true[ \t]*;.*")) {
/*      */               
/* 2082 */               SMCLog.info("shadowtex1Nearest");
/* 2083 */               shadowFilterNearest[1] = true; continue;
/*      */             } 
/* 2085 */             if (line.matches("[ \t]*const[ \t]*bool[ \t]*(shadowcolor0Nearest|shadowColor0Nearest|shadowColor0MinMagNearest)[ \t]*=[ \t]*true[ \t]*;.*")) {
/*      */               
/* 2087 */               SMCLog.info("shadowcolor0Nearest");
/* 2088 */               shadowColorFilterNearest[0] = true; continue;
/*      */             } 
/* 2090 */             if (line.matches("[ \t]*const[ \t]*bool[ \t]*(shadowcolor1Nearest|shadowColor1Nearest|shadowColor1MinMagNearest)[ \t]*=[ \t]*true[ \t]*;.*")) {
/*      */               
/* 2092 */               SMCLog.info("shadowcolor1Nearest");
/* 2093 */               shadowColorFilterNearest[1] = true; continue;
/*      */             } 
/* 2095 */             if (line.matches("/\\* WETNESSHL:[0-9\\.]+ \\*/.*")) {
/*      */               
/* 2097 */               String[] e = line.split("(:| )", 4);
/* 2098 */               SMCLog.info("Wetness halflife: " + e[2]);
/* 2099 */               wetnessHalfLife = Float.parseFloat(e[2]); continue;
/*      */             } 
/* 2101 */             if (line.matches("[ \t]*const[ \t]*float[ \t]*wetnessHalflife[ \t]*=[ \t]*-?[0-9.]+f?;.*")) {
/*      */               
/* 2103 */               String[] e = line.split("(=[ \t]*|;)");
/* 2104 */               SMCLog.info("Wetness halflife: " + e[1]);
/* 2105 */               wetnessHalfLife = Float.parseFloat(e[1]); continue;
/*      */             } 
/* 2107 */             if (line.matches("/\\* DRYNESSHL:[0-9\\.]+ \\*/.*")) {
/*      */               
/* 2109 */               String[] e = line.split("(:| )", 4);
/* 2110 */               SMCLog.info("Dryness halflife: " + e[2]);
/* 2111 */               drynessHalfLife = Float.parseFloat(e[2]); continue;
/*      */             } 
/* 2113 */             if (line.matches("[ \t]*const[ \t]*float[ \t]*drynessHalflife[ \t]*=[ \t]*-?[0-9.]+f?;.*")) {
/*      */               
/* 2115 */               String[] e = line.split("(=[ \t]*|;)");
/* 2116 */               SMCLog.info("Dryness halflife: " + e[1]);
/* 2117 */               drynessHalfLife = Float.parseFloat(e[1]); continue;
/*      */             } 
/* 2119 */             if (line.matches("[ \t]*const[ \t]*float[ \t]*eyeBrightnessHalflife[ \t]*=[ \t]*-?[0-9.]+f?;.*")) {
/*      */               
/* 2121 */               String[] e = line.split("(=[ \t]*|;)");
/* 2122 */               SMCLog.info("Eye brightness halflife: " + e[1]);
/* 2123 */               eyeBrightnessHalflife = Float.parseFloat(e[1]); continue;
/*      */             } 
/* 2125 */             if (line.matches("[ \t]*const[ \t]*float[ \t]*centerDepthHalflife[ \t]*=[ \t]*-?[0-9.]+f?;.*")) {
/*      */               
/* 2127 */               String[] e = line.split("(=[ \t]*|;)");
/* 2128 */               SMCLog.info("Center depth halflife: " + e[1]);
/* 2129 */               centerDepthSmoothHalflife = Float.parseFloat(e[1]); continue;
/*      */             } 
/* 2131 */             if (line.matches("[ \t]*const[ \t]*float[ \t]*sunPathRotation[ \t]*=[ \t]*-?[0-9.]+f?;.*")) {
/*      */               
/* 2133 */               String[] e = line.split("(=[ \t]*|;)");
/* 2134 */               SMCLog.info("Sun path rotation: " + e[1]);
/* 2135 */               sunPathRotation = Float.parseFloat(e[1]); continue;
/*      */             } 
/* 2137 */             if (line.matches("[ \t]*const[ \t]*float[ \t]*ambientOcclusionLevel[ \t]*=[ \t]*-?[0-9.]+f?;.*")) {
/*      */               
/* 2139 */               String[] e = line.split("(=[ \t]*|;)");
/* 2140 */               SMCLog.info("AO Level: " + e[1]);
/* 2141 */               aoLevel = Float.parseFloat(e[1]);
/* 2142 */               blockAoLight = 1.0F - aoLevel; continue;
/*      */             } 
/* 2144 */             if (line.matches("[ \t]*const[ \t]*int[ \t]*superSamplingLevel[ \t]*=[ \t]*-?[0-9.]+f?;.*")) {
/*      */               
/* 2146 */               String[] e = line.split("(=[ \t]*|;)");
/* 2147 */               int name1 = Integer.parseInt(e[1]);
/*      */               
/* 2149 */               if (name1 > 1) {
/*      */                 
/* 2151 */                 SMCLog.info("Super sampling level: " + name1 + "x");
/* 2152 */                 superSamplingLevel = name1;
/*      */                 
/*      */                 continue;
/*      */               } 
/* 2156 */               superSamplingLevel = 1;
/*      */               continue;
/*      */             } 
/* 2159 */             if (line.matches("[ \t]*const[ \t]*int[ \t]*noiseTextureResolution[ \t]*=[ \t]*-?[0-9.]+f?;.*")) {
/*      */               
/* 2161 */               String[] e = line.split("(=[ \t]*|;)");
/* 2162 */               SMCLog.info("Noise texture enabled");
/* 2163 */               SMCLog.info("Noise texture resolution: " + e[1]);
/* 2164 */               noiseTextureResolution = Integer.parseInt(e[1]);
/* 2165 */               noiseTextureEnabled = true;
/*      */ 
/*      */               
/*      */               continue;
/*      */             } 
/*      */ 
/*      */             
/* 2172 */             if (line.matches("[ \t]*const[ \t]*int[ \t]*\\w+Format[ \t]*=[ \t]*[RGBA81632FUI_SNORM]*[ \t]*;.*")) {
/*      */               
/* 2174 */               Matcher e1 = gbufferFormatPattern.matcher(line);
/* 2175 */               e1.matches();
/* 2176 */               String name = e1.group(1);
/* 2177 */               String bufferindex2 = e1.group(2);
/* 2178 */               int bufferindex1 = getBufferIndexFromString(name);
/* 2179 */               int format = getTextureFormatFromString(bufferindex2);
/*      */               
/* 2181 */               if (bufferindex1 >= 0 && format != 0) {
/*      */                 
/* 2183 */                 gbuffersFormat[bufferindex1] = format;
/* 2184 */                 SMCLog.info("%s format: %s", new Object[] { name, bufferindex2 });
/*      */               }  continue;
/*      */             } 
/* 2187 */             if (line.matches("/\\* GAUX4FORMAT:RGBA32F \\*/.*")) {
/*      */               
/* 2189 */               SMCLog.info("gaux4 format : RGB32AF");
/* 2190 */               gbuffersFormat[7] = 34836; continue;
/*      */             } 
/* 2192 */             if (line.matches("/\\* GAUX4FORMAT:RGB32F \\*/.*")) {
/*      */               
/* 2194 */               SMCLog.info("gaux4 format : RGB32F");
/* 2195 */               gbuffersFormat[7] = 34837; continue;
/*      */             } 
/* 2197 */             if (line.matches("/\\* GAUX4FORMAT:RGB16 \\*/.*")) {
/*      */               
/* 2199 */               SMCLog.info("gaux4 format : RGB16");
/* 2200 */               gbuffersFormat[7] = 32852; continue;
/*      */             } 
/* 2202 */             if (line.matches("[ \t]*const[ \t]*bool[ \t]*\\w+MipmapEnabled[ \t]*=[ \t]*true[ \t]*;.*")) {
/*      */               
/* 2204 */               if (filename.matches(".*composite[0-9]?.fsh") || filename.matches(".*final.fsh")) {
/*      */                 
/* 2206 */                 Matcher e1 = gbufferMipmapEnabledPattern.matcher(line);
/* 2207 */                 e1.matches();
/* 2208 */                 String name = e1.group(1);
/* 2209 */                 int bufferindex = getBufferIndexFromString(name);
/*      */                 
/* 2211 */                 if (bufferindex >= 0) {
/*      */                   
/* 2213 */                   newCompositeMipmapSetting |= 1 << bufferindex;
/* 2214 */                   SMCLog.info("%s mipmap enabled for %s", new Object[] { name, filename });
/*      */                 } 
/*      */               }  continue;
/*      */             } 
/* 2218 */             if (line.matches("/\\* DRAWBUFFERS:[0-7N]* \\*/.*")) {
/*      */               
/* 2220 */               String[] e = line.split("(:| )", 4);
/* 2221 */               newDrawBufSetting = e[2];
/*      */             } 
/*      */           } 
/*      */         } 
/*      */ 
/*      */ 
/*      */         
/* 2228 */         reader.close();
/*      */       }
/* 2230 */       catch (Exception var13) {
/*      */         
/* 2232 */         SMCLog.severe("Couldn't read " + filename + "!");
/* 2233 */         var13.printStackTrace();
/* 2234 */         ARBShaderObjects.glDeleteObjectARB(fragShader);
/* 2235 */         return 0;
/*      */       } 
/*      */     }
/*      */     
/* 2239 */     if (saveFinalShaders)
/*      */     {
/* 2241 */       saveShader(filename, fragCode.toString());
/*      */     }
/*      */     
/* 2244 */     ARBShaderObjects.glShaderSourceARB(fragShader, fragCode);
/* 2245 */     ARBShaderObjects.glCompileShaderARB(fragShader);
/*      */     
/* 2247 */     if (GL20.glGetShaderi(fragShader, 35713) != 1)
/*      */     {
/* 2249 */       SMCLog.severe("Error compiling fragment shader: " + filename);
/*      */     }
/*      */     
/* 2252 */     printShaderLogInfo(fragShader, filename);
/* 2253 */     return fragShader;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static void saveShader(String filename, String code) {
/*      */     try {
/* 2261 */       File e = new File(shaderpacksdir, "debug/" + filename);
/* 2262 */       e.getParentFile().mkdirs();
/* 2263 */       Config.writeFile(e, code);
/*      */     }
/* 2265 */     catch (IOException var3) {
/*      */       
/* 2267 */       Config.warn("Error saving: " + filename);
/* 2268 */       var3.printStackTrace();
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   private static void clearDirectory(File dir) {
/* 2274 */     if (dir.exists())
/*      */     {
/* 2276 */       if (dir.isDirectory()) {
/*      */         
/* 2278 */         File[] files = dir.listFiles();
/*      */         
/* 2280 */         if (files != null)
/*      */         {
/* 2282 */           for (int i = 0; i < files.length; i++) {
/*      */             
/* 2284 */             File file = files[i];
/*      */             
/* 2286 */             if (file.isDirectory())
/*      */             {
/* 2288 */               clearDirectory(file);
/*      */             }
/*      */             
/* 2291 */             file.delete();
/*      */           } 
/*      */         }
/*      */       } 
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   private static boolean printLogInfo(int obj, String name) {
/* 2300 */     IntBuffer iVal = BufferUtils.createIntBuffer(1);
/* 2301 */     ARBShaderObjects.glGetObjectParameterARB(obj, 35716, iVal);
/* 2302 */     int length = iVal.get();
/*      */     
/* 2304 */     if (length > 1) {
/*      */       
/* 2306 */       ByteBuffer infoLog = BufferUtils.createByteBuffer(length);
/* 2307 */       iVal.flip();
/* 2308 */       ARBShaderObjects.glGetInfoLogARB(obj, iVal, infoLog);
/* 2309 */       byte[] infoBytes = new byte[length];
/* 2310 */       infoLog.get(infoBytes);
/*      */       
/* 2312 */       if (infoBytes[length - 1] == 0)
/*      */       {
/* 2314 */         infoBytes[length - 1] = 10;
/*      */       }
/*      */       
/* 2317 */       String out = new String(infoBytes);
/* 2318 */       SMCLog.info("Info log: " + name + "\n" + out);
/* 2319 */       return false;
/*      */     } 
/*      */ 
/*      */     
/* 2323 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private static boolean printShaderLogInfo(int shader, String name) {
/* 2329 */     IntBuffer iVal = BufferUtils.createIntBuffer(1);
/* 2330 */     int length = GL20.glGetShaderi(shader, 35716);
/*      */     
/* 2332 */     if (length > 1) {
/*      */       
/* 2334 */       String log = GL20.glGetShaderInfoLog(shader, length);
/* 2335 */       SMCLog.info("Shader info log: " + name + "\n" + log);
/* 2336 */       return false;
/*      */     } 
/*      */ 
/*      */     
/* 2340 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public static void setDrawBuffers(IntBuffer drawBuffers) {
/* 2346 */     if (drawBuffers == null)
/*      */     {
/* 2348 */       drawBuffers = drawBuffersNone;
/*      */     }
/*      */     
/* 2351 */     if (activeDrawBuffers != drawBuffers) {
/*      */       
/* 2353 */       activeDrawBuffers = drawBuffers;
/* 2354 */       GL20.glDrawBuffers(drawBuffers);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public static void useProgram(int program) {
/* 2360 */     checkGLError("pre-useProgram");
/*      */     
/* 2362 */     if (isShadowPass) {
/*      */       
/* 2364 */       program = 30;
/*      */       
/* 2366 */       if (programsID[30] == 0) {
/*      */         
/* 2368 */         normalMapEnabled = false;
/*      */         
/*      */         return;
/*      */       } 
/*      */     } 
/* 2373 */     if (activeProgram != program) {
/*      */       
/* 2375 */       activeProgram = program;
/* 2376 */       ARBShaderObjects.glUseProgramObjectARB(programsID[program]);
/*      */       
/* 2378 */       if (programsID[program] == 0) {
/*      */         
/* 2380 */         normalMapEnabled = false;
/*      */       } else {
/*      */         int itemID;
/*      */         Block block;
/* 2384 */         if (checkGLError("useProgram ", programNames[program]) != 0)
/*      */         {
/* 2386 */           programsID[program] = 0;
/*      */         }
/*      */         
/* 2389 */         IntBuffer drawBuffers = programsDrawBuffers[program];
/*      */         
/* 2391 */         if (isRenderingDfb) {
/*      */           
/* 2393 */           setDrawBuffers(drawBuffers);
/* 2394 */           checkGLError(programNames[program], " draw buffers = ", programsDrawBufSettings[program]);
/*      */         } 
/*      */         
/* 2397 */         activeCompositeMipmapSetting = programsCompositeMipmapSetting[program];
/* 2398 */         uniformEntityColor.setProgram(programsID[activeProgram]);
/* 2399 */         uniformEntityId.setProgram(programsID[activeProgram]);
/* 2400 */         uniformBlockEntityId.setProgram(programsID[activeProgram]);
/*      */         
/* 2402 */         switch (program) {
/*      */           
/*      */           case 1:
/*      */           case 2:
/*      */           case 3:
/*      */           case 4:
/*      */           case 5:
/*      */           case 6:
/*      */           case 7:
/*      */           case 8:
/*      */           case 9:
/*      */           case 10:
/*      */           case 11:
/*      */           case 12:
/*      */           case 13:
/*      */           case 16:
/*      */           case 18:
/*      */           case 19:
/*      */           case 20:
/* 2421 */             normalMapEnabled = true;
/* 2422 */             setProgramUniform1i("texture", 0);
/* 2423 */             setProgramUniform1i("lightmap", 1);
/* 2424 */             setProgramUniform1i("normals", 2);
/* 2425 */             setProgramUniform1i("specular", 3);
/* 2426 */             setProgramUniform1i("shadow", waterShadowEnabled ? 5 : 4);
/* 2427 */             setProgramUniform1i("watershadow", 4);
/* 2428 */             setProgramUniform1i("shadowtex0", 4);
/* 2429 */             setProgramUniform1i("shadowtex1", 5);
/* 2430 */             setProgramUniform1i("depthtex0", 6);
/* 2431 */             setProgramUniform1i("depthtex1", 12);
/* 2432 */             setProgramUniform1i("shadowcolor", 13);
/* 2433 */             setProgramUniform1i("shadowcolor0", 13);
/* 2434 */             setProgramUniform1i("shadowcolor1", 14);
/* 2435 */             setProgramUniform1i("noisetex", 15);
/*      */             break;
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*      */           default:
/* 2442 */             normalMapEnabled = false;
/*      */             break;
/*      */           
/*      */           case 21:
/*      */           case 22:
/*      */           case 23:
/*      */           case 24:
/*      */           case 25:
/*      */           case 26:
/*      */           case 27:
/*      */           case 28:
/*      */           case 29:
/* 2454 */             normalMapEnabled = false;
/* 2455 */             setProgramUniform1i("gcolor", 0);
/* 2456 */             setProgramUniform1i("gdepth", 1);
/* 2457 */             setProgramUniform1i("gnormal", 2);
/* 2458 */             setProgramUniform1i("composite", 3);
/* 2459 */             setProgramUniform1i("gaux1", 7);
/* 2460 */             setProgramUniform1i("gaux2", 8);
/* 2461 */             setProgramUniform1i("gaux3", 9);
/* 2462 */             setProgramUniform1i("gaux4", 10);
/* 2463 */             setProgramUniform1i("colortex0", 0);
/* 2464 */             setProgramUniform1i("colortex1", 1);
/* 2465 */             setProgramUniform1i("colortex2", 2);
/* 2466 */             setProgramUniform1i("colortex3", 3);
/* 2467 */             setProgramUniform1i("colortex4", 7);
/* 2468 */             setProgramUniform1i("colortex5", 8);
/* 2469 */             setProgramUniform1i("colortex6", 9);
/* 2470 */             setProgramUniform1i("colortex7", 10);
/* 2471 */             setProgramUniform1i("shadow", waterShadowEnabled ? 5 : 4);
/* 2472 */             setProgramUniform1i("watershadow", 4);
/* 2473 */             setProgramUniform1i("shadowtex0", 4);
/* 2474 */             setProgramUniform1i("shadowtex1", 5);
/* 2475 */             setProgramUniform1i("gdepthtex", 6);
/* 2476 */             setProgramUniform1i("depthtex0", 6);
/* 2477 */             setProgramUniform1i("depthtex1", 11);
/* 2478 */             setProgramUniform1i("depthtex2", 12);
/* 2479 */             setProgramUniform1i("shadowcolor", 13);
/* 2480 */             setProgramUniform1i("shadowcolor0", 13);
/* 2481 */             setProgramUniform1i("shadowcolor1", 14);
/* 2482 */             setProgramUniform1i("noisetex", 15);
/*      */             break;
/*      */           
/*      */           case 30:
/*      */           case 31:
/*      */           case 32:
/* 2488 */             setProgramUniform1i("tex", 0);
/* 2489 */             setProgramUniform1i("texture", 0);
/* 2490 */             setProgramUniform1i("lightmap", 1);
/* 2491 */             setProgramUniform1i("normals", 2);
/* 2492 */             setProgramUniform1i("specular", 3);
/* 2493 */             setProgramUniform1i("shadow", waterShadowEnabled ? 5 : 4);
/* 2494 */             setProgramUniform1i("watershadow", 4);
/* 2495 */             setProgramUniform1i("shadowtex0", 4);
/* 2496 */             setProgramUniform1i("shadowtex1", 5);
/* 2497 */             setProgramUniform1i("shadowcolor", 13);
/* 2498 */             setProgramUniform1i("shadowcolor0", 13);
/* 2499 */             setProgramUniform1i("shadowcolor1", 14);
/* 2500 */             setProgramUniform1i("noisetex", 15);
/*      */             break;
/*      */         } 
/* 2503 */         ItemStack stack = mc.thePlayer.getCurrentEquippedItem();
/* 2504 */         Item item = (stack != null) ? stack.getItem() : null;
/*      */ 
/*      */ 
/*      */         
/* 2508 */         if (item != null) {
/*      */           
/* 2510 */           itemID = Item.itemRegistry.getIDForObject(item);
/* 2511 */           block = (Block)Block.blockRegistry.getObjectById(itemID);
/*      */         }
/*      */         else {
/*      */           
/* 2515 */           itemID = -1;
/* 2516 */           block = null;
/*      */         } 
/*      */         
/* 2519 */         int blockLight = (block != null) ? block.getLightValue() : 0;
/* 2520 */         setProgramUniform1i("heldItemId", itemID);
/* 2521 */         setProgramUniform1i("heldBlockLightValue", blockLight);
/* 2522 */         setProgramUniform1i("fogMode", fogEnabled ? fogMode : 0);
/* 2523 */         setProgramUniform3f("fogColor", fogColorR, fogColorG, fogColorB);
/* 2524 */         setProgramUniform3f("skyColor", skyColorR, skyColorG, skyColorB);
/* 2525 */         setProgramUniform1i("worldTime", (int)worldTime % 24000);
/* 2526 */         setProgramUniform1i("moonPhase", moonPhase);
/* 2527 */         setProgramUniform1f("frameTimeCounter", frameTimeCounter);
/* 2528 */         setProgramUniform1f("sunAngle", sunAngle);
/* 2529 */         setProgramUniform1f("shadowAngle", shadowAngle);
/* 2530 */         setProgramUniform1f("rainStrength", rainStrength);
/* 2531 */         setProgramUniform1f("aspectRatio", renderWidth / renderHeight);
/* 2532 */         setProgramUniform1f("viewWidth", renderWidth);
/* 2533 */         setProgramUniform1f("viewHeight", renderHeight);
/* 2534 */         setProgramUniform1f("near", 0.05F);
/* 2535 */         setProgramUniform1f("far", (mc.gameSettings.renderDistanceChunks * 16));
/* 2536 */         setProgramUniform3f("sunPosition", sunPosition[0], sunPosition[1], sunPosition[2]);
/* 2537 */         setProgramUniform3f("moonPosition", moonPosition[0], moonPosition[1], moonPosition[2]);
/* 2538 */         setProgramUniform3f("shadowLightPosition", shadowLightPosition[0], shadowLightPosition[1], shadowLightPosition[2]);
/* 2539 */         setProgramUniform3f("upPosition", upPosition[0], upPosition[1], upPosition[2]);
/* 2540 */         setProgramUniform3f("previousCameraPosition", (float)previousCameraPositionX, (float)previousCameraPositionY, (float)previousCameraPositionZ);
/* 2541 */         setProgramUniform3f("cameraPosition", (float)cameraPositionX, (float)cameraPositionY, (float)cameraPositionZ);
/* 2542 */         setProgramUniformMatrix4ARB("gbufferModelView", false, modelView);
/* 2543 */         setProgramUniformMatrix4ARB("gbufferModelViewInverse", false, modelViewInverse);
/* 2544 */         setProgramUniformMatrix4ARB("gbufferPreviousProjection", false, previousProjection);
/* 2545 */         setProgramUniformMatrix4ARB("gbufferProjection", false, projection);
/* 2546 */         setProgramUniformMatrix4ARB("gbufferProjectionInverse", false, projectionInverse);
/* 2547 */         setProgramUniformMatrix4ARB("gbufferPreviousModelView", false, previousModelView);
/*      */         
/* 2549 */         if (usedShadowDepthBuffers > 0) {
/*      */           
/* 2551 */           setProgramUniformMatrix4ARB("shadowProjection", false, shadowProjection);
/* 2552 */           setProgramUniformMatrix4ARB("shadowProjectionInverse", false, shadowProjectionInverse);
/* 2553 */           setProgramUniformMatrix4ARB("shadowModelView", false, shadowModelView);
/* 2554 */           setProgramUniformMatrix4ARB("shadowModelViewInverse", false, shadowModelViewInverse);
/*      */         } 
/*      */         
/* 2557 */         setProgramUniform1f("wetness", wetness);
/* 2558 */         setProgramUniform1f("eyeAltitude", eyePosY);
/* 2559 */         setProgramUniform2i("eyeBrightness", eyeBrightness & 0xFFFF, eyeBrightness >> 16);
/* 2560 */         setProgramUniform2i("eyeBrightnessSmooth", Math.round(eyeBrightnessFadeX), Math.round(eyeBrightnessFadeY));
/* 2561 */         setProgramUniform2i("terrainTextureSize", terrainTextureSize[0], terrainTextureSize[1]);
/* 2562 */         setProgramUniform1i("terrainIconSize", terrainIconSize);
/* 2563 */         setProgramUniform1i("isEyeInWater", isEyeInWater);
/* 2564 */         setProgramUniform1i("hideGUI", mc.gameSettings.hideGUI ? 1 : 0);
/* 2565 */         setProgramUniform1f("centerDepthSmooth", centerDepthSmooth);
/* 2566 */         setProgramUniform2i("atlasSize", atlasSizeX, atlasSizeY);
/* 2567 */         checkGLError("useProgram ", programNames[program]);
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public static void setProgramUniform1i(String name, int x) {
/* 2574 */     int gp = programsID[activeProgram];
/*      */     
/* 2576 */     if (gp != 0) {
/*      */       
/* 2578 */       int uniform = ARBShaderObjects.glGetUniformLocationARB(gp, name);
/* 2579 */       ARBShaderObjects.glUniform1iARB(uniform, x);
/* 2580 */       checkGLError(programNames[activeProgram], name);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public static void setProgramUniform2i(String name, int x, int y) {
/* 2586 */     int gp = programsID[activeProgram];
/*      */     
/* 2588 */     if (gp != 0) {
/*      */       
/* 2590 */       int uniform = ARBShaderObjects.glGetUniformLocationARB(gp, name);
/* 2591 */       ARBShaderObjects.glUniform2iARB(uniform, x, y);
/* 2592 */       checkGLError(programNames[activeProgram], name);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public static void setProgramUniform1f(String name, float x) {
/* 2598 */     int gp = programsID[activeProgram];
/*      */     
/* 2600 */     if (gp != 0) {
/*      */       
/* 2602 */       int uniform = ARBShaderObjects.glGetUniformLocationARB(gp, name);
/* 2603 */       ARBShaderObjects.glUniform1fARB(uniform, x);
/* 2604 */       checkGLError(programNames[activeProgram], name);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public static void setProgramUniform3f(String name, float x, float y, float z) {
/* 2610 */     int gp = programsID[activeProgram];
/*      */     
/* 2612 */     if (gp != 0) {
/*      */       
/* 2614 */       int uniform = ARBShaderObjects.glGetUniformLocationARB(gp, name);
/* 2615 */       ARBShaderObjects.glUniform3fARB(uniform, x, y, z);
/* 2616 */       checkGLError(programNames[activeProgram], name);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public static void setProgramUniformMatrix4ARB(String name, boolean transpose, FloatBuffer matrix) {
/* 2622 */     int gp = programsID[activeProgram];
/*      */     
/* 2624 */     if (gp != 0 && matrix != null) {
/*      */       
/* 2626 */       int uniform = ARBShaderObjects.glGetUniformLocationARB(gp, name);
/* 2627 */       ARBShaderObjects.glUniformMatrix4ARB(uniform, transpose, matrix);
/* 2628 */       checkGLError(programNames[activeProgram], name);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   private static int getBufferIndexFromString(String name) {
/* 2634 */     return (!name.equals("colortex0") && !name.equals("gcolor")) ? ((!name.equals("colortex1") && !name.equals("gdepth")) ? ((!name.equals("colortex2") && !name.equals("gnormal")) ? ((!name.equals("colortex3") && !name.equals("composite")) ? ((!name.equals("colortex4") && !name.equals("gaux1")) ? ((!name.equals("colortex5") && !name.equals("gaux2")) ? ((!name.equals("colortex6") && !name.equals("gaux3")) ? ((!name.equals("colortex7") && !name.equals("gaux4")) ? -1 : 7) : 6) : 5) : 4) : 3) : 2) : 1) : 0;
/*      */   }
/*      */ 
/*      */   
/*      */   private static int getTextureFormatFromString(String par) {
/* 2639 */     par = par.trim();
/*      */     
/* 2641 */     for (int i = 0; i < formatNames.length; i++) {
/*      */       
/* 2643 */       String name = formatNames[i];
/*      */       
/* 2645 */       if (par.equals(name))
/*      */       {
/* 2647 */         return formatIds[i];
/*      */       }
/*      */     } 
/*      */     
/* 2651 */     return 0;
/*      */   }
/*      */ 
/*      */   
/*      */   private static void setupNoiseTexture() {
/* 2656 */     if (noiseTexture == null)
/*      */     {
/* 2658 */       noiseTexture = new HFNoiseTexture(noiseTextureResolution, noiseTextureResolution);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   private static void loadEntityDataMap() {
/* 2664 */     mapBlockToEntityData = new IdentityHashMap<>(300);
/*      */     
/* 2666 */     if (mapBlockToEntityData.isEmpty()) {
/*      */       
/* 2668 */       Iterator<ResourceLocation> reader = Block.blockRegistry.getKeys().iterator();
/*      */       
/* 2670 */       while (reader.hasNext()) {
/*      */         
/* 2672 */         ResourceLocation e = reader.next();
/* 2673 */         Block m = (Block)Block.blockRegistry.getObject(e);
/* 2674 */         int name = Block.blockRegistry.getIDForObject(m);
/* 2675 */         mapBlockToEntityData.put(m, Integer.valueOf(name));
/*      */       } 
/*      */     } 
/*      */     
/* 2679 */     BufferedReader reader1 = null;
/*      */ 
/*      */     
/*      */     try {
/* 2683 */       reader1 = new BufferedReader(new InputStreamReader(shaderPack.getResourceAsStream("/mc_Entity_x.txt")));
/*      */     }
/* 2685 */     catch (Exception exception) {}
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2690 */     if (reader1 != null) {
/*      */       try {
/*      */         String e1;
/*      */ 
/*      */ 
/*      */         
/* 2696 */         while ((e1 = reader1.readLine()) != null)
/*      */         {
/* 2698 */           Matcher m1 = patternLoadEntityDataMap.matcher(e1);
/*      */           
/* 2700 */           if (m1.matches()) {
/*      */             
/* 2702 */             String name1 = m1.group(1);
/* 2703 */             String value = m1.group(2);
/* 2704 */             int id = Integer.parseInt(value);
/* 2705 */             Block block = Block.getBlockFromName(name1);
/*      */             
/* 2707 */             if (block != null) {
/*      */               
/* 2709 */               mapBlockToEntityData.put(block, Integer.valueOf(id));
/*      */               
/*      */               continue;
/*      */             } 
/* 2713 */             SMCLog.warning("Unknown block name %s", new Object[] { name1 });
/*      */             
/*      */             continue;
/*      */           } 
/*      */           
/* 2718 */           SMCLog.warning("unmatched %s\n", new Object[] { e1 });
/*      */         }
/*      */       
/*      */       }
/* 2722 */       catch (Exception var9) {
/*      */         
/* 2724 */         SMCLog.warning("Error parsing mc_Entity_x.txt");
/*      */       } 
/*      */     }
/*      */     
/* 2728 */     if (reader1 != null) {
/*      */       
/*      */       try {
/*      */         
/* 2732 */         reader1.close();
/*      */       }
/* 2734 */       catch (Exception e1) {}
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static IntBuffer fillIntBufferZero(IntBuffer buf) {
/* 2743 */     int limit = buf.limit();
/*      */     
/* 2745 */     for (int i = buf.position(); i < limit; i++)
/*      */     {
/* 2747 */       buf.put(i, 0);
/*      */     }
/*      */     
/* 2750 */     return buf;
/*      */   }
/*      */ 
/*      */   
/*      */   public static void uninit() {
/* 2755 */     if (isShaderPackInitialized) {
/*      */       
/* 2757 */       checkGLError("Shaders.uninit pre");
/*      */       
/* 2759 */       for (int i = 0; i < 33; i++) {
/*      */         
/* 2761 */         if (programsRef[i] != 0) {
/*      */           
/* 2763 */           ARBShaderObjects.glDeleteObjectARB(programsRef[i]);
/* 2764 */           checkGLError("del programRef");
/*      */         } 
/*      */         
/* 2767 */         programsRef[i] = 0;
/* 2768 */         programsID[i] = 0;
/* 2769 */         programsDrawBufSettings[i] = null;
/* 2770 */         programsDrawBuffers[i] = null;
/* 2771 */         programsCompositeMipmapSetting[i] = 0;
/*      */       } 
/*      */       
/* 2774 */       if (dfb != 0) {
/*      */         
/* 2776 */         EXTFramebufferObject.glDeleteFramebuffersEXT(dfb);
/* 2777 */         dfb = 0;
/* 2778 */         checkGLError("del dfb");
/*      */       } 
/*      */       
/* 2781 */       if (sfb != 0) {
/*      */         
/* 2783 */         EXTFramebufferObject.glDeleteFramebuffersEXT(sfb);
/* 2784 */         sfb = 0;
/* 2785 */         checkGLError("del sfb");
/*      */       } 
/*      */       
/* 2788 */       if (dfbDepthTextures != null) {
/*      */         
/* 2790 */         GlStateManager.deleteTextures(dfbDepthTextures);
/* 2791 */         fillIntBufferZero(dfbDepthTextures);
/* 2792 */         checkGLError("del dfbDepthTextures");
/*      */       } 
/*      */       
/* 2795 */       if (dfbColorTextures != null) {
/*      */         
/* 2797 */         GlStateManager.deleteTextures(dfbColorTextures);
/* 2798 */         fillIntBufferZero(dfbColorTextures);
/* 2799 */         checkGLError("del dfbTextures");
/*      */       } 
/*      */       
/* 2802 */       if (sfbDepthTextures != null) {
/*      */         
/* 2804 */         GlStateManager.deleteTextures(sfbDepthTextures);
/* 2805 */         fillIntBufferZero(sfbDepthTextures);
/* 2806 */         checkGLError("del shadow depth");
/*      */       } 
/*      */       
/* 2809 */       if (sfbColorTextures != null) {
/*      */         
/* 2811 */         GlStateManager.deleteTextures(sfbColorTextures);
/* 2812 */         fillIntBufferZero(sfbColorTextures);
/* 2813 */         checkGLError("del shadow color");
/*      */       } 
/*      */       
/* 2816 */       if (dfbDrawBuffers != null)
/*      */       {
/* 2818 */         fillIntBufferZero(dfbDrawBuffers);
/*      */       }
/*      */       
/* 2821 */       if (noiseTexture != null) {
/*      */         
/* 2823 */         noiseTexture.destroy();
/* 2824 */         noiseTexture = null;
/*      */       } 
/*      */       
/* 2827 */       SMCLog.info("Uninit");
/* 2828 */       shadowPassInterval = 0;
/* 2829 */       shouldSkipDefaultShadow = false;
/* 2830 */       isShaderPackInitialized = false;
/* 2831 */       checkGLError("Shaders.uninit");
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public static void scheduleResize() {
/* 2837 */     renderDisplayHeight = 0;
/*      */   }
/*      */ 
/*      */   
/*      */   public static void scheduleResizeShadow() {
/* 2842 */     needResizeShadow = true;
/*      */   }
/*      */ 
/*      */   
/*      */   private static void resize() {
/* 2847 */     renderDisplayWidth = mc.displayWidth;
/* 2848 */     renderDisplayHeight = mc.displayHeight;
/* 2849 */     renderWidth = Math.round(renderDisplayWidth * configRenderResMul);
/* 2850 */     renderHeight = Math.round(renderDisplayHeight * configRenderResMul);
/* 2851 */     setupFrameBuffer();
/*      */   }
/*      */ 
/*      */   
/*      */   private static void resizeShadow() {
/* 2856 */     needResizeShadow = false;
/* 2857 */     shadowMapWidth = Math.round(spShadowMapWidth * configShadowResMul);
/* 2858 */     shadowMapHeight = Math.round(spShadowMapHeight * configShadowResMul);
/* 2859 */     setupShadowFrameBuffer();
/*      */   }
/*      */ 
/*      */   
/*      */   private static void setupFrameBuffer() {
/* 2864 */     if (dfb != 0) {
/*      */       
/* 2866 */       EXTFramebufferObject.glDeleteFramebuffersEXT(dfb);
/* 2867 */       GlStateManager.deleteTextures(dfbDepthTextures);
/* 2868 */       GlStateManager.deleteTextures(dfbColorTextures);
/*      */     } 
/*      */     
/* 2871 */     dfb = EXTFramebufferObject.glGenFramebuffersEXT();
/* 2872 */     GL11.glGenTextures((IntBuffer)dfbDepthTextures.clear().limit(usedDepthBuffers));
/* 2873 */     GL11.glGenTextures((IntBuffer)dfbColorTextures.clear().limit(16));
/* 2874 */     dfbDepthTextures.position(0);
/* 2875 */     dfbColorTextures.position(0);
/* 2876 */     dfbColorTextures.get(dfbColorTexturesA).position(0);
/* 2877 */     EXTFramebufferObject.glBindFramebufferEXT(36160, dfb);
/* 2878 */     GL20.glDrawBuffers(0);
/* 2879 */     GL11.glReadBuffer(0);
/*      */     
/*      */     int status;
/* 2882 */     for (status = 0; status < usedDepthBuffers; status++) {
/*      */       
/* 2884 */       GlStateManager.func_179144_i(dfbDepthTextures.get(status));
/* 2885 */       GL11.glTexParameteri(3553, 10242, 10496);
/* 2886 */       GL11.glTexParameteri(3553, 10243, 10496);
/* 2887 */       GL11.glTexParameteri(3553, 10241, 9728);
/* 2888 */       GL11.glTexParameteri(3553, 10240, 9728);
/* 2889 */       GL11.glTexParameteri(3553, 34891, 6409);
/* 2890 */       GL11.glTexImage2D(3553, 0, 6402, renderWidth, renderHeight, 0, 6402, 5126, null);
/*      */     } 
/*      */     
/* 2893 */     EXTFramebufferObject.glFramebufferTexture2DEXT(36160, 36096, 3553, dfbDepthTextures.get(0), 0);
/* 2894 */     GL20.glDrawBuffers(dfbDrawBuffers);
/* 2895 */     GL11.glReadBuffer(0);
/* 2896 */     checkGLError("FT d");
/*      */     
/* 2898 */     for (status = 0; status < usedColorBuffers; status++) {
/*      */       
/* 2900 */       GlStateManager.func_179144_i(dfbColorTexturesA[status]);
/* 2901 */       GL11.glTexParameteri(3553, 10242, 10496);
/* 2902 */       GL11.glTexParameteri(3553, 10243, 10496);
/* 2903 */       GL11.glTexParameteri(3553, 10241, 9729);
/* 2904 */       GL11.glTexParameteri(3553, 10240, 9729);
/* 2905 */       GL11.glTexImage2D(3553, 0, gbuffersFormat[status], renderWidth, renderHeight, 0, 32993, 33639, null);
/* 2906 */       EXTFramebufferObject.glFramebufferTexture2DEXT(36160, 36064 + status, 3553, dfbColorTexturesA[status], 0);
/* 2907 */       checkGLError("FT c");
/*      */     } 
/*      */     
/* 2910 */     for (status = 0; status < usedColorBuffers; status++) {
/*      */       
/* 2912 */       GlStateManager.func_179144_i(dfbColorTexturesA[8 + status]);
/* 2913 */       GL11.glTexParameteri(3553, 10242, 10496);
/* 2914 */       GL11.glTexParameteri(3553, 10243, 10496);
/* 2915 */       GL11.glTexParameteri(3553, 10241, 9729);
/* 2916 */       GL11.glTexParameteri(3553, 10240, 9729);
/* 2917 */       GL11.glTexImage2D(3553, 0, gbuffersFormat[status], renderWidth, renderHeight, 0, 32993, 33639, null);
/* 2918 */       checkGLError("FT ca");
/*      */     } 
/*      */     
/* 2921 */     status = EXTFramebufferObject.glCheckFramebufferStatusEXT(36160);
/*      */     
/* 2923 */     if (status == 36058) {
/*      */       
/* 2925 */       printChatAndLogError("[Shaders] Error: Failed framebuffer incomplete formats");
/*      */       
/* 2927 */       for (int i = 0; i < usedColorBuffers; i++) {
/*      */         
/* 2929 */         GlStateManager.func_179144_i(dfbColorTextures.get(i));
/* 2930 */         GL11.glTexImage2D(3553, 0, 6408, renderWidth, renderHeight, 0, 32993, 33639, null);
/* 2931 */         EXTFramebufferObject.glFramebufferTexture2DEXT(36160, 36064 + i, 3553, dfbColorTextures.get(i), 0);
/* 2932 */         checkGLError("FT c");
/*      */       } 
/*      */       
/* 2935 */       status = EXTFramebufferObject.glCheckFramebufferStatusEXT(36160);
/*      */       
/* 2937 */       if (status == 36053)
/*      */       {
/* 2939 */         SMCLog.info("complete");
/*      */       }
/*      */     } 
/*      */     
/* 2943 */     GlStateManager.func_179144_i(0);
/*      */     
/* 2945 */     if (status != 36053) {
/*      */       
/* 2947 */       printChatAndLogError("[Shaders] Error: Failed creating framebuffer! (Status " + status + ")");
/*      */     }
/*      */     else {
/*      */       
/* 2951 */       SMCLog.info("Framebuffer created.");
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   private static void setupShadowFrameBuffer() {
/* 2957 */     if (usedShadowDepthBuffers != 0) {
/*      */       
/* 2959 */       if (sfb != 0) {
/*      */         
/* 2961 */         EXTFramebufferObject.glDeleteFramebuffersEXT(sfb);
/* 2962 */         GlStateManager.deleteTextures(sfbDepthTextures);
/* 2963 */         GlStateManager.deleteTextures(sfbColorTextures);
/*      */       } 
/*      */       
/* 2966 */       sfb = EXTFramebufferObject.glGenFramebuffersEXT();
/* 2967 */       EXTFramebufferObject.glBindFramebufferEXT(36160, sfb);
/* 2968 */       GL11.glDrawBuffer(0);
/* 2969 */       GL11.glReadBuffer(0);
/* 2970 */       GL11.glGenTextures((IntBuffer)sfbDepthTextures.clear().limit(usedShadowDepthBuffers));
/* 2971 */       GL11.glGenTextures((IntBuffer)sfbColorTextures.clear().limit(usedShadowColorBuffers));
/* 2972 */       sfbDepthTextures.position(0);
/* 2973 */       sfbColorTextures.position(0);
/*      */       
/*      */       int status;
/*      */       
/* 2977 */       for (status = 0; status < usedShadowDepthBuffers; status++) {
/*      */         
/* 2979 */         GlStateManager.func_179144_i(sfbDepthTextures.get(status));
/* 2980 */         GL11.glTexParameterf(3553, 10242, 10496.0F);
/* 2981 */         GL11.glTexParameterf(3553, 10243, 10496.0F);
/* 2982 */         int filter = shadowFilterNearest[status] ? 9728 : 9729;
/* 2983 */         GL11.glTexParameteri(3553, 10241, filter);
/* 2984 */         GL11.glTexParameteri(3553, 10240, filter);
/*      */         
/* 2986 */         if (shadowHardwareFilteringEnabled[status])
/*      */         {
/* 2988 */           GL11.glTexParameteri(3553, 34892, 34894);
/*      */         }
/*      */         
/* 2991 */         GL11.glTexImage2D(3553, 0, 6402, shadowMapWidth, shadowMapHeight, 0, 6402, 5126, null);
/*      */       } 
/*      */       
/* 2994 */       EXTFramebufferObject.glFramebufferTexture2DEXT(36160, 36096, 3553, sfbDepthTextures.get(0), 0);
/* 2995 */       checkGLError("FT sd");
/*      */       
/* 2997 */       for (status = 0; status < usedShadowColorBuffers; status++) {
/*      */         
/* 2999 */         GlStateManager.func_179144_i(sfbColorTextures.get(status));
/* 3000 */         GL11.glTexParameterf(3553, 10242, 10496.0F);
/* 3001 */         GL11.glTexParameterf(3553, 10243, 10496.0F);
/* 3002 */         int filter = shadowColorFilterNearest[status] ? 9728 : 9729;
/* 3003 */         GL11.glTexParameteri(3553, 10241, filter);
/* 3004 */         GL11.glTexParameteri(3553, 10240, filter);
/* 3005 */         GL11.glTexImage2D(3553, 0, 6408, shadowMapWidth, shadowMapHeight, 0, 32993, 33639, null);
/* 3006 */         EXTFramebufferObject.glFramebufferTexture2DEXT(36160, 36064 + status, 3553, sfbColorTextures.get(status), 0);
/* 3007 */         checkGLError("FT sc");
/*      */       } 
/*      */       
/* 3010 */       GlStateManager.func_179144_i(0);
/*      */       
/* 3012 */       if (usedShadowColorBuffers > 0)
/*      */       {
/* 3014 */         GL20.glDrawBuffers(sfbDrawBuffers);
/*      */       }
/*      */       
/* 3017 */       status = EXTFramebufferObject.glCheckFramebufferStatusEXT(36160);
/*      */       
/* 3019 */       if (status != 36053) {
/*      */         
/* 3021 */         printChatAndLogError("[Shaders] Error: Failed creating shadow framebuffer! (Status " + status + ")");
/*      */       }
/*      */       else {
/*      */         
/* 3025 */         SMCLog.info("Shadow framebuffer created.");
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public static void beginRender(Minecraft minecraft, float partialTicks, long finishTimeNano) {
/* 3032 */     checkGLError("pre beginRender");
/* 3033 */     checkWorldChanged((World)mc.theWorld);
/* 3034 */     mc = minecraft;
/* 3035 */     mc.mcProfiler.startSection("init");
/* 3036 */     entityRenderer = mc.entityRenderer;
/*      */     
/* 3038 */     if (!isShaderPackInitialized) {
/*      */       
/*      */       try {
/*      */         
/* 3042 */         init();
/*      */       }
/* 3044 */       catch (IllegalStateException var7) {
/*      */         
/* 3046 */         if (Config.normalize(var7.getMessage()).equals("Function is not supported")) {
/*      */           
/* 3048 */           printChatAndLogError("[Shaders] Error: " + var7.getMessage());
/* 3049 */           var7.printStackTrace();
/* 3050 */           setShaderPack(packNameNone);
/*      */           
/*      */           return;
/*      */         } 
/*      */       } 
/*      */     }
/* 3056 */     if (mc.displayWidth != renderDisplayWidth || mc.displayHeight != renderDisplayHeight)
/*      */     {
/* 3058 */       resize();
/*      */     }
/*      */     
/* 3061 */     if (needResizeShadow)
/*      */     {
/* 3063 */       resizeShadow();
/*      */     }
/*      */     
/* 3066 */     worldTime = mc.theWorld.getWorldTime();
/* 3067 */     diffWorldTime = (worldTime - lastWorldTime) % 24000L;
/*      */     
/* 3069 */     if (diffWorldTime < 0L)
/*      */     {
/* 3071 */       diffWorldTime += 24000L;
/*      */     }
/*      */     
/* 3074 */     lastWorldTime = worldTime;
/* 3075 */     moonPhase = mc.theWorld.getMoonPhase();
/* 3076 */     systemTime = System.currentTimeMillis();
/*      */     
/* 3078 */     if (lastSystemTime == 0L)
/*      */     {
/* 3080 */       lastSystemTime = systemTime;
/*      */     }
/*      */     
/* 3083 */     diffSystemTime = systemTime - lastSystemTime;
/* 3084 */     lastSystemTime = systemTime;
/* 3085 */     frameTimeCounter += (float)diffSystemTime * 0.001F;
/* 3086 */     frameTimeCounter %= 3600.0F;
/* 3087 */     rainStrength = minecraft.theWorld.getRainStrength(partialTicks);
/* 3088 */     float renderViewEntity = (float)diffSystemTime * 0.01F;
/* 3089 */     float i = (float)Math.exp(Math.log(0.5D) * renderViewEntity / ((wetness < rainStrength) ? drynessHalfLife : wetnessHalfLife));
/* 3090 */     wetness = wetness * i + rainStrength * (1.0F - i);
/* 3091 */     Entity var8 = mc.func_175606_aa();
/* 3092 */     isSleeping = (var8 instanceof EntityLivingBase && ((EntityLivingBase)var8).isPlayerSleeping());
/* 3093 */     eyePosY = (float)var8.posY * partialTicks + (float)var8.lastTickPosY * (1.0F - partialTicks);
/* 3094 */     eyeBrightness = var8.getBrightnessForRender(partialTicks);
/* 3095 */     i = (float)diffSystemTime * 0.01F;
/* 3096 */     float temp2 = (float)Math.exp(Math.log(0.5D) * i / eyeBrightnessHalflife);
/* 3097 */     eyeBrightnessFadeX = eyeBrightnessFadeX * temp2 + (eyeBrightness & 0xFFFF) * (1.0F - temp2);
/* 3098 */     eyeBrightnessFadeY = eyeBrightnessFadeY * temp2 + (eyeBrightness >> 16) * (1.0F - temp2);
/* 3099 */     isEyeInWater = (mc.gameSettings.thirdPersonView == 0 && !isSleeping && mc.thePlayer.isInsideOfMaterial(Material.water)) ? 1 : 0;
/* 3100 */     Vec3 var9 = mc.theWorld.getSkyColor(mc.func_175606_aa(), partialTicks);
/* 3101 */     skyColorR = (float)var9.xCoord;
/* 3102 */     skyColorG = (float)var9.yCoord;
/* 3103 */     skyColorB = (float)var9.zCoord;
/* 3104 */     isRenderingWorld = true;
/* 3105 */     isCompositeRendered = false;
/* 3106 */     isHandRendered = false;
/*      */     
/* 3108 */     if (usedShadowDepthBuffers >= 1) {
/*      */       
/* 3110 */       GlStateManager.setActiveTexture(33988);
/* 3111 */       GlStateManager.func_179144_i(sfbDepthTextures.get(0));
/*      */       
/* 3113 */       if (usedShadowDepthBuffers >= 2) {
/*      */         
/* 3115 */         GlStateManager.setActiveTexture(33989);
/* 3116 */         GlStateManager.func_179144_i(sfbDepthTextures.get(1));
/*      */       } 
/*      */     } 
/*      */     
/* 3120 */     GlStateManager.setActiveTexture(33984);
/*      */     
/*      */     int var10;
/* 3123 */     for (var10 = 0; var10 < usedColorBuffers; var10++) {
/*      */       
/* 3125 */       GlStateManager.func_179144_i(dfbColorTexturesA[var10]);
/* 3126 */       GL11.glTexParameteri(3553, 10240, 9729);
/* 3127 */       GL11.glTexParameteri(3553, 10241, 9729);
/* 3128 */       GlStateManager.func_179144_i(dfbColorTexturesA[8 + var10]);
/* 3129 */       GL11.glTexParameteri(3553, 10240, 9729);
/* 3130 */       GL11.glTexParameteri(3553, 10241, 9729);
/*      */     } 
/*      */     
/* 3133 */     GlStateManager.func_179144_i(0);
/*      */     
/* 3135 */     for (var10 = 0; var10 < 4 && 4 + var10 < usedColorBuffers; var10++) {
/*      */       
/* 3137 */       GlStateManager.setActiveTexture(33991 + var10);
/* 3138 */       GlStateManager.func_179144_i(dfbColorTextures.get(4 + var10));
/*      */     } 
/*      */     
/* 3141 */     GlStateManager.setActiveTexture(33990);
/* 3142 */     GlStateManager.func_179144_i(dfbDepthTextures.get(0));
/*      */     
/* 3144 */     if (usedDepthBuffers >= 2) {
/*      */       
/* 3146 */       GlStateManager.setActiveTexture(33995);
/* 3147 */       GlStateManager.func_179144_i(dfbDepthTextures.get(1));
/*      */       
/* 3149 */       if (usedDepthBuffers >= 3) {
/*      */         
/* 3151 */         GlStateManager.setActiveTexture(33996);
/* 3152 */         GlStateManager.func_179144_i(dfbDepthTextures.get(2));
/*      */       } 
/*      */     } 
/*      */     
/* 3156 */     for (var10 = 0; var10 < usedShadowColorBuffers; var10++) {
/*      */       
/* 3158 */       GlStateManager.setActiveTexture(33997 + var10);
/* 3159 */       GlStateManager.func_179144_i(sfbColorTextures.get(var10));
/*      */     } 
/*      */     
/* 3162 */     if (noiseTextureEnabled) {
/*      */       
/* 3164 */       GlStateManager.setActiveTexture(33984 + noiseTexture.textureUnit);
/* 3165 */       GlStateManager.func_179144_i(noiseTexture.getID());
/* 3166 */       GL11.glTexParameteri(3553, 10242, 10497);
/* 3167 */       GL11.glTexParameteri(3553, 10243, 10497);
/* 3168 */       GL11.glTexParameteri(3553, 10240, 9729);
/* 3169 */       GL11.glTexParameteri(3553, 10241, 9729);
/*      */     } 
/*      */     
/* 3172 */     GlStateManager.setActiveTexture(33984);
/* 3173 */     previousCameraPositionX = cameraPositionX;
/* 3174 */     previousCameraPositionY = cameraPositionY;
/* 3175 */     previousCameraPositionZ = cameraPositionZ;
/* 3176 */     previousProjection.position(0);
/* 3177 */     projection.position(0);
/* 3178 */     previousProjection.put(projection);
/* 3179 */     previousProjection.position(0);
/* 3180 */     projection.position(0);
/* 3181 */     previousModelView.position(0);
/* 3182 */     modelView.position(0);
/* 3183 */     previousModelView.put(modelView);
/* 3184 */     previousModelView.position(0);
/* 3185 */     modelView.position(0);
/* 3186 */     checkGLError("beginRender");
/* 3187 */     ShadersRender.renderShadowMap(entityRenderer, 0, partialTicks, finishTimeNano);
/* 3188 */     mc.mcProfiler.endSection();
/* 3189 */     EXTFramebufferObject.glBindFramebufferEXT(36160, dfb);
/*      */     
/* 3191 */     for (var10 = 0; var10 < usedColorBuffers; var10++) {
/*      */       
/* 3193 */       colorTexturesToggle[var10] = 0;
/* 3194 */       EXTFramebufferObject.glFramebufferTexture2DEXT(36160, 36064 + var10, 3553, dfbColorTexturesA[var10], 0);
/*      */     } 
/*      */     
/* 3197 */     checkGLError("end beginRender");
/*      */   }
/*      */ 
/*      */   
/*      */   private static void checkWorldChanged(World world) {
/* 3202 */     if (currentWorld != world) {
/*      */       
/* 3204 */       World oldWorld = currentWorld;
/* 3205 */       currentWorld = world;
/*      */       
/* 3207 */       if (oldWorld != null && world != null) {
/*      */         
/* 3209 */         int dimIdOld = oldWorld.provider.getDimensionId();
/* 3210 */         int dimIdNew = world.provider.getDimensionId();
/* 3211 */         boolean dimShadersOld = shaderPackDimensions.contains(Integer.valueOf(dimIdOld));
/* 3212 */         boolean dimShadersNew = shaderPackDimensions.contains(Integer.valueOf(dimIdNew));
/*      */         
/* 3214 */         if (dimShadersOld || dimShadersNew)
/*      */         {
/* 3216 */           uninit();
/*      */         }
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public static void beginRenderPass(int pass, float partialTicks, long finishTimeNano) {
/* 3224 */     if (!isShadowPass) {
/*      */       
/* 3226 */       EXTFramebufferObject.glBindFramebufferEXT(36160, dfb);
/* 3227 */       GL11.glViewport(0, 0, renderWidth, renderHeight);
/* 3228 */       activeDrawBuffers = null;
/* 3229 */       ShadersTex.bindNSTextures(defaultTexture.getMultiTexID());
/* 3230 */       useProgram(2);
/* 3231 */       checkGLError("end beginRenderPass");
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public static void setViewport(int vx, int vy, int vw, int vh) {
/* 3237 */     GlStateManager.colorMask(true, true, true, true);
/*      */     
/* 3239 */     if (isShadowPass) {
/*      */       
/* 3241 */       GL11.glViewport(0, 0, shadowMapWidth, shadowMapHeight);
/*      */     }
/*      */     else {
/*      */       
/* 3245 */       GL11.glViewport(0, 0, renderWidth, renderHeight);
/* 3246 */       EXTFramebufferObject.glBindFramebufferEXT(36160, dfb);
/* 3247 */       isRenderingDfb = true;
/* 3248 */       GlStateManager.enableCull();
/* 3249 */       GlStateManager.enableDepth();
/* 3250 */       setDrawBuffers(drawBuffersNone);
/* 3251 */       useProgram(2);
/* 3252 */       checkGLError("beginRenderPass");
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public static int setFogMode(int val) {
/* 3258 */     fogMode = val;
/* 3259 */     return val;
/*      */   }
/*      */ 
/*      */   
/*      */   public static void setFogColor(float r, float g, float b) {
/* 3264 */     fogColorR = r;
/* 3265 */     fogColorG = g;
/* 3266 */     fogColorB = b;
/*      */   }
/*      */ 
/*      */   
/*      */   public static void setClearColor(float red, float green, float blue, float alpha) {
/* 3271 */     GlStateManager.clearColor(red, green, blue, alpha);
/* 3272 */     clearColorR = red;
/* 3273 */     clearColorG = green;
/* 3274 */     clearColorB = blue;
/*      */   }
/*      */ 
/*      */   
/*      */   public static void clearRenderBuffer() {
/* 3279 */     if (isShadowPass) {
/*      */       
/* 3281 */       checkGLError("shadow clear pre");
/* 3282 */       EXTFramebufferObject.glFramebufferTexture2DEXT(36160, 36096, 3553, sfbDepthTextures.get(0), 0);
/* 3283 */       GL11.glClearColor(1.0F, 1.0F, 1.0F, 1.0F);
/* 3284 */       GL20.glDrawBuffers(programsDrawBuffers[30]);
/* 3285 */       checkFramebufferStatus("shadow clear");
/* 3286 */       GL11.glClear(16640);
/* 3287 */       checkGLError("shadow clear");
/*      */     }
/*      */     else {
/*      */       
/* 3291 */       checkGLError("clear pre");
/* 3292 */       GL20.glDrawBuffers(36064);
/* 3293 */       GL11.glClear(16384);
/* 3294 */       GL20.glDrawBuffers(36065);
/* 3295 */       GL11.glClearColor(1.0F, 1.0F, 1.0F, 1.0F);
/* 3296 */       GL11.glClear(16384);
/*      */       
/* 3298 */       for (int i = 2; i < usedColorBuffers; i++) {
/*      */         
/* 3300 */         GL20.glDrawBuffers(36064 + i);
/* 3301 */         GL11.glClearColor(0.0F, 0.0F, 0.0F, 0.0F);
/* 3302 */         GL11.glClear(16384);
/*      */       } 
/*      */       
/* 3305 */       setDrawBuffers(dfbDrawBuffers);
/* 3306 */       checkFramebufferStatus("clear");
/* 3307 */       checkGLError("clear");
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public static void setCamera(float partialTicks) {
/* 3313 */     Entity viewEntity = mc.func_175606_aa();
/* 3314 */     double x = viewEntity.lastTickPosX + (viewEntity.posX - viewEntity.lastTickPosX) * partialTicks;
/* 3315 */     double y = viewEntity.lastTickPosY + (viewEntity.posY - viewEntity.lastTickPosY) * partialTicks;
/* 3316 */     double z = viewEntity.lastTickPosZ + (viewEntity.posZ - viewEntity.lastTickPosZ) * partialTicks;
/* 3317 */     cameraPositionX = x;
/* 3318 */     cameraPositionY = y;
/* 3319 */     cameraPositionZ = z;
/* 3320 */     GL11.glGetFloat(2983, (FloatBuffer)projection.position(0));
/* 3321 */     SMath.invertMat4FBFA((FloatBuffer)projectionInverse.position(0), (FloatBuffer)projection.position(0), faProjectionInverse, faProjection);
/* 3322 */     projection.position(0);
/* 3323 */     projectionInverse.position(0);
/* 3324 */     GL11.glGetFloat(2982, (FloatBuffer)modelView.position(0));
/* 3325 */     SMath.invertMat4FBFA((FloatBuffer)modelViewInverse.position(0), (FloatBuffer)modelView.position(0), faModelViewInverse, faModelView);
/* 3326 */     modelView.position(0);
/* 3327 */     modelViewInverse.position(0);
/* 3328 */     checkGLError("setCamera");
/*      */   }
/*      */ 
/*      */   
/*      */   public static void setCameraShadow(float partialTicks) {
/* 3333 */     Entity viewEntity = mc.func_175606_aa();
/* 3334 */     double x = viewEntity.lastTickPosX + (viewEntity.posX - viewEntity.lastTickPosX) * partialTicks;
/* 3335 */     double y = viewEntity.lastTickPosY + (viewEntity.posY - viewEntity.lastTickPosY) * partialTicks;
/* 3336 */     double z = viewEntity.lastTickPosZ + (viewEntity.posZ - viewEntity.lastTickPosZ) * partialTicks;
/* 3337 */     cameraPositionX = x;
/* 3338 */     cameraPositionY = y;
/* 3339 */     cameraPositionZ = z;
/* 3340 */     GL11.glGetFloat(2983, (FloatBuffer)projection.position(0));
/* 3341 */     SMath.invertMat4FBFA((FloatBuffer)projectionInverse.position(0), (FloatBuffer)projection.position(0), faProjectionInverse, faProjection);
/* 3342 */     projection.position(0);
/* 3343 */     projectionInverse.position(0);
/* 3344 */     GL11.glGetFloat(2982, (FloatBuffer)modelView.position(0));
/* 3345 */     SMath.invertMat4FBFA((FloatBuffer)modelViewInverse.position(0), (FloatBuffer)modelView.position(0), faModelViewInverse, faModelView);
/* 3346 */     modelView.position(0);
/* 3347 */     modelViewInverse.position(0);
/* 3348 */     GL11.glViewport(0, 0, shadowMapWidth, shadowMapHeight);
/* 3349 */     GL11.glMatrixMode(5889);
/* 3350 */     GL11.glLoadIdentity();
/*      */     
/* 3352 */     if (shadowMapIsOrtho) {
/*      */       
/* 3354 */       GL11.glOrtho(-shadowMapHalfPlane, shadowMapHalfPlane, -shadowMapHalfPlane, shadowMapHalfPlane, 0.05000000074505806D, 256.0D);
/*      */     }
/*      */     else {
/*      */       
/* 3358 */       GLU.gluPerspective(shadowMapFOV, shadowMapWidth / shadowMapHeight, 0.05F, 256.0F);
/*      */     } 
/*      */     
/* 3361 */     GL11.glMatrixMode(5888);
/* 3362 */     GL11.glLoadIdentity();
/* 3363 */     GL11.glTranslatef(0.0F, 0.0F, -100.0F);
/* 3364 */     GL11.glRotatef(90.0F, 1.0F, 0.0F, 0.0F);
/* 3365 */     celestialAngle = mc.theWorld.getCelestialAngle(partialTicks);
/* 3366 */     sunAngle = (celestialAngle < 0.75F) ? (celestialAngle + 0.25F) : (celestialAngle - 0.75F);
/* 3367 */     float angle = celestialAngle * -360.0F;
/* 3368 */     float angleInterval = (shadowAngleInterval > 0.0F) ? (angle % shadowAngleInterval - shadowAngleInterval * 0.5F) : 0.0F;
/*      */     
/* 3370 */     if (sunAngle <= 0.5D) {
/*      */       
/* 3372 */       GL11.glRotatef(angle - angleInterval, 0.0F, 0.0F, 1.0F);
/* 3373 */       GL11.glRotatef(sunPathRotation, 1.0F, 0.0F, 0.0F);
/* 3374 */       shadowAngle = sunAngle;
/*      */     }
/*      */     else {
/*      */       
/* 3378 */       GL11.glRotatef(angle + 180.0F - angleInterval, 0.0F, 0.0F, 1.0F);
/* 3379 */       GL11.glRotatef(sunPathRotation, 1.0F, 0.0F, 0.0F);
/* 3380 */       shadowAngle = sunAngle - 0.5F;
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 3386 */     if (shadowMapIsOrtho) {
/*      */       
/* 3388 */       float f1 = shadowIntervalSize;
/* 3389 */       float f2 = f1 / 2.0F;
/* 3390 */       GL11.glTranslatef((float)x % f1 - f2, (float)y % f1 - f2, (float)z % f1 - f2);
/*      */     } 
/*      */     
/* 3393 */     float raSun = sunAngle * 6.2831855F;
/* 3394 */     float x1 = (float)Math.cos(raSun);
/* 3395 */     float y1 = (float)Math.sin(raSun);
/* 3396 */     float raTilt = sunPathRotation * 6.2831855F;
/* 3397 */     float x2 = x1;
/* 3398 */     float y2 = y1 * (float)Math.cos(raTilt);
/* 3399 */     float z2 = y1 * (float)Math.sin(raTilt);
/*      */     
/* 3401 */     if (sunAngle > 0.5D) {
/*      */       
/* 3403 */       x2 = -x1;
/* 3404 */       y2 = -y2;
/* 3405 */       z2 = -z2;
/*      */     } 
/*      */     
/* 3408 */     shadowLightPositionVector[0] = x2;
/* 3409 */     shadowLightPositionVector[1] = y2;
/* 3410 */     shadowLightPositionVector[2] = z2;
/* 3411 */     shadowLightPositionVector[3] = 0.0F;
/* 3412 */     GL11.glGetFloat(2983, (FloatBuffer)shadowProjection.position(0));
/* 3413 */     SMath.invertMat4FBFA((FloatBuffer)shadowProjectionInverse.position(0), (FloatBuffer)shadowProjection.position(0), faShadowProjectionInverse, faShadowProjection);
/* 3414 */     shadowProjection.position(0);
/* 3415 */     shadowProjectionInverse.position(0);
/* 3416 */     GL11.glGetFloat(2982, (FloatBuffer)shadowModelView.position(0));
/* 3417 */     SMath.invertMat4FBFA((FloatBuffer)shadowModelViewInverse.position(0), (FloatBuffer)shadowModelView.position(0), faShadowModelViewInverse, faShadowModelView);
/* 3418 */     shadowModelView.position(0);
/* 3419 */     shadowModelViewInverse.position(0);
/* 3420 */     setProgramUniformMatrix4ARB("gbufferProjection", false, projection);
/* 3421 */     setProgramUniformMatrix4ARB("gbufferProjectionInverse", false, projectionInverse);
/* 3422 */     setProgramUniformMatrix4ARB("gbufferPreviousProjection", false, previousProjection);
/* 3423 */     setProgramUniformMatrix4ARB("gbufferModelView", false, modelView);
/* 3424 */     setProgramUniformMatrix4ARB("gbufferModelViewInverse", false, modelViewInverse);
/* 3425 */     setProgramUniformMatrix4ARB("gbufferPreviousModelView", false, previousModelView);
/* 3426 */     setProgramUniformMatrix4ARB("shadowProjection", false, shadowProjection);
/* 3427 */     setProgramUniformMatrix4ARB("shadowProjectionInverse", false, shadowProjectionInverse);
/* 3428 */     setProgramUniformMatrix4ARB("shadowModelView", false, shadowModelView);
/* 3429 */     setProgramUniformMatrix4ARB("shadowModelViewInverse", false, shadowModelViewInverse);
/* 3430 */     mc.gameSettings.thirdPersonView = 1;
/* 3431 */     checkGLError("setCamera");
/*      */   }
/*      */ 
/*      */   
/*      */   public static void preCelestialRotate() {
/* 3436 */     setUpPosition();
/* 3437 */     GL11.glRotatef(sunPathRotation * 1.0F, 0.0F, 0.0F, 1.0F);
/* 3438 */     checkGLError("preCelestialRotate");
/*      */   }
/*      */ 
/*      */   
/*      */   public static void postCelestialRotate() {
/* 3443 */     FloatBuffer modelView = tempMatrixDirectBuffer;
/* 3444 */     modelView.clear();
/* 3445 */     GL11.glGetFloat(2982, modelView);
/* 3446 */     modelView.get(tempMat, 0, 16);
/* 3447 */     SMath.multiplyMat4xVec4(sunPosition, tempMat, sunPosModelView);
/* 3448 */     SMath.multiplyMat4xVec4(moonPosition, tempMat, moonPosModelView);
/* 3449 */     System.arraycopy((shadowAngle == sunAngle) ? sunPosition : moonPosition, 0, shadowLightPosition, 0, 3);
/* 3450 */     checkGLError("postCelestialRotate");
/*      */   }
/*      */ 
/*      */   
/*      */   public static void setUpPosition() {
/* 3455 */     FloatBuffer modelView = tempMatrixDirectBuffer;
/* 3456 */     modelView.clear();
/* 3457 */     GL11.glGetFloat(2982, modelView);
/* 3458 */     modelView.get(tempMat, 0, 16);
/* 3459 */     SMath.multiplyMat4xVec4(upPosition, tempMat, upPosModelView);
/*      */   }
/*      */ 
/*      */   
/*      */   public static void genCompositeMipmap() {
/* 3464 */     if (hasGlGenMipmap) {
/*      */       
/* 3466 */       for (int i = 0; i < usedColorBuffers; i++) {
/*      */         
/* 3468 */         if ((activeCompositeMipmapSetting & 1 << i) != 0) {
/*      */           
/* 3470 */           GlStateManager.setActiveTexture(33984 + colorTextureTextureImageUnit[i]);
/* 3471 */           GL11.glTexParameteri(3553, 10241, 9987);
/* 3472 */           GL30.glGenerateMipmap(3553);
/*      */         } 
/*      */       } 
/*      */       
/* 3476 */       GlStateManager.setActiveTexture(33984);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public static void drawComposite() {
/* 3482 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 3483 */     GL11.glBegin(7);
/* 3484 */     GL11.glTexCoord2f(0.0F, 0.0F);
/* 3485 */     GL11.glVertex3f(0.0F, 0.0F, 0.0F);
/* 3486 */     GL11.glTexCoord2f(1.0F, 0.0F);
/* 3487 */     GL11.glVertex3f(1.0F, 0.0F, 0.0F);
/* 3488 */     GL11.glTexCoord2f(1.0F, 1.0F);
/* 3489 */     GL11.glVertex3f(1.0F, 1.0F, 0.0F);
/* 3490 */     GL11.glTexCoord2f(0.0F, 1.0F);
/* 3491 */     GL11.glVertex3f(0.0F, 1.0F, 0.0F);
/* 3492 */     GL11.glEnd();
/*      */   }
/*      */ 
/*      */   
/*      */   public static void renderCompositeFinal() {
/* 3497 */     if (!isShadowPass) {
/*      */       
/* 3499 */       checkGLError("pre-renderCompositeFinal");
/* 3500 */       GL11.glPushMatrix();
/* 3501 */       GL11.glLoadIdentity();
/* 3502 */       GL11.glMatrixMode(5889);
/* 3503 */       GL11.glPushMatrix();
/* 3504 */       GL11.glLoadIdentity();
/* 3505 */       GL11.glOrtho(0.0D, 1.0D, 0.0D, 1.0D, 0.0D, 1.0D);
/* 3506 */       GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 3507 */       GlStateManager.func_179098_w();
/* 3508 */       GlStateManager.disableAlpha();
/* 3509 */       GlStateManager.disableBlend();
/* 3510 */       GlStateManager.enableDepth();
/* 3511 */       GlStateManager.depthFunc(519);
/* 3512 */       GlStateManager.depthMask(false);
/* 3513 */       GlStateManager.disableLighting();
/*      */       
/* 3515 */       if (usedShadowDepthBuffers >= 1) {
/*      */         
/* 3517 */         GlStateManager.setActiveTexture(33988);
/* 3518 */         GlStateManager.func_179144_i(sfbDepthTextures.get(0));
/*      */         
/* 3520 */         if (usedShadowDepthBuffers >= 2) {
/*      */           
/* 3522 */           GlStateManager.setActiveTexture(33989);
/* 3523 */           GlStateManager.func_179144_i(sfbDepthTextures.get(1));
/*      */         } 
/*      */       } 
/*      */       
/*      */       int enableAltBuffers;
/*      */       
/* 3529 */       for (enableAltBuffers = 0; enableAltBuffers < usedColorBuffers; enableAltBuffers++) {
/*      */         
/* 3531 */         GlStateManager.setActiveTexture(33984 + colorTextureTextureImageUnit[enableAltBuffers]);
/* 3532 */         GlStateManager.func_179144_i(dfbColorTexturesA[enableAltBuffers]);
/*      */       } 
/*      */       
/* 3535 */       GlStateManager.setActiveTexture(33990);
/* 3536 */       GlStateManager.func_179144_i(dfbDepthTextures.get(0));
/*      */       
/* 3538 */       if (usedDepthBuffers >= 2) {
/*      */         
/* 3540 */         GlStateManager.setActiveTexture(33995);
/* 3541 */         GlStateManager.func_179144_i(dfbDepthTextures.get(1));
/*      */         
/* 3543 */         if (usedDepthBuffers >= 3) {
/*      */           
/* 3545 */           GlStateManager.setActiveTexture(33996);
/* 3546 */           GlStateManager.func_179144_i(dfbDepthTextures.get(2));
/*      */         } 
/*      */       } 
/*      */       
/* 3550 */       for (enableAltBuffers = 0; enableAltBuffers < usedShadowColorBuffers; enableAltBuffers++) {
/*      */         
/* 3552 */         GlStateManager.setActiveTexture(33997 + enableAltBuffers);
/* 3553 */         GlStateManager.func_179144_i(sfbColorTextures.get(enableAltBuffers));
/*      */       } 
/*      */       
/* 3556 */       if (noiseTextureEnabled) {
/*      */         
/* 3558 */         GlStateManager.setActiveTexture(33984 + noiseTexture.textureUnit);
/* 3559 */         GlStateManager.func_179144_i(noiseTexture.getID());
/* 3560 */         GL11.glTexParameteri(3553, 10242, 10497);
/* 3561 */         GL11.glTexParameteri(3553, 10243, 10497);
/* 3562 */         GL11.glTexParameteri(3553, 10240, 9729);
/* 3563 */         GL11.glTexParameteri(3553, 10241, 9729);
/*      */       } 
/*      */       
/* 3566 */       GlStateManager.setActiveTexture(33984);
/* 3567 */       boolean var5 = true;
/*      */       
/*      */       int maskR;
/* 3570 */       for (maskR = 0; maskR < usedColorBuffers; maskR++)
/*      */       {
/* 3572 */         EXTFramebufferObject.glFramebufferTexture2DEXT(36160, 36064 + maskR, 3553, dfbColorTexturesA[8 + maskR], 0);
/*      */       }
/*      */       
/* 3575 */       EXTFramebufferObject.glFramebufferTexture2DEXT(36160, 36096, 3553, dfbDepthTextures.get(0), 0);
/* 3576 */       GL20.glDrawBuffers(dfbDrawBuffers);
/* 3577 */       checkGLError("pre-composite");
/*      */       
/* 3579 */       for (maskR = 0; maskR < 8; maskR++) {
/*      */         
/* 3581 */         if (programsID[21 + maskR] != 0) {
/*      */           
/* 3583 */           useProgram(21 + maskR);
/* 3584 */           checkGLError(programNames[21 + maskR]);
/*      */           
/* 3586 */           if (activeCompositeMipmapSetting != 0)
/*      */           {
/* 3588 */             genCompositeMipmap();
/*      */           }
/*      */           
/* 3591 */           drawComposite();
/*      */           
/* 3593 */           for (int i = 0; i < usedColorBuffers; i++) {
/*      */             
/* 3595 */             if (programsToggleColorTextures[21 + maskR][i]) {
/*      */               
/* 3597 */               int t0 = colorTexturesToggle[i];
/* 3598 */               int t1 = colorTexturesToggle[i] = 8 - t0;
/* 3599 */               GlStateManager.setActiveTexture(33984 + colorTextureTextureImageUnit[i]);
/* 3600 */               GlStateManager.func_179144_i(dfbColorTexturesA[t1 + i]);
/* 3601 */               EXTFramebufferObject.glFramebufferTexture2DEXT(36160, 36064 + i, 3553, dfbColorTexturesA[t0 + i], 0);
/*      */             } 
/*      */           } 
/*      */           
/* 3605 */           GlStateManager.setActiveTexture(33984);
/*      */         } 
/*      */       } 
/*      */       
/* 3609 */       checkGLError("composite");
/* 3610 */       isRenderingDfb = false;
/* 3611 */       mc.getFramebuffer().bindFramebuffer(true);
/* 3612 */       OpenGlHelper.func_153188_a(OpenGlHelper.field_153198_e, OpenGlHelper.field_153200_g, 3553, (mc.getFramebuffer()).framebufferTexture, 0);
/* 3613 */       GL11.glViewport(0, 0, mc.displayWidth, mc.displayHeight);
/*      */       
/* 3615 */       if (EntityRenderer.anaglyphEnable) {
/*      */         
/* 3617 */         boolean var6 = (EntityRenderer.anaglyphField != 0);
/* 3618 */         GlStateManager.colorMask(var6, !var6, !var6, true);
/*      */       } 
/*      */       
/* 3621 */       GlStateManager.depthMask(true);
/* 3622 */       GL11.glClearColor(clearColorR, clearColorG, clearColorB, 1.0F);
/* 3623 */       GL11.glClear(16640);
/* 3624 */       GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 3625 */       GlStateManager.func_179098_w();
/* 3626 */       GlStateManager.disableAlpha();
/* 3627 */       GlStateManager.disableBlend();
/* 3628 */       GlStateManager.enableDepth();
/* 3629 */       GlStateManager.depthFunc(519);
/* 3630 */       GlStateManager.depthMask(false);
/* 3631 */       checkGLError("pre-final");
/* 3632 */       useProgram(29);
/* 3633 */       checkGLError("final");
/*      */       
/* 3635 */       if (activeCompositeMipmapSetting != 0)
/*      */       {
/* 3637 */         genCompositeMipmap();
/*      */       }
/*      */       
/* 3640 */       drawComposite();
/* 3641 */       checkGLError("renderCompositeFinal");
/* 3642 */       isCompositeRendered = true;
/* 3643 */       GlStateManager.enableLighting();
/* 3644 */       GlStateManager.func_179098_w();
/* 3645 */       GlStateManager.enableAlpha();
/* 3646 */       GlStateManager.enableBlend();
/* 3647 */       GlStateManager.depthFunc(515);
/* 3648 */       GlStateManager.depthMask(true);
/* 3649 */       GL11.glPopMatrix();
/* 3650 */       GL11.glMatrixMode(5888);
/* 3651 */       GL11.glPopMatrix();
/* 3652 */       useProgram(0);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public static void endRender() {
/* 3658 */     if (isShadowPass) {
/*      */       
/* 3660 */       checkGLError("shadow endRender");
/*      */     }
/*      */     else {
/*      */       
/* 3664 */       if (!isCompositeRendered)
/*      */       {
/* 3666 */         renderCompositeFinal();
/*      */       }
/*      */       
/* 3669 */       isRenderingWorld = false;
/* 3670 */       GlStateManager.colorMask(true, true, true, true);
/* 3671 */       useProgram(0);
/* 3672 */       RenderHelper.disableStandardItemLighting();
/* 3673 */       checkGLError("endRender end");
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public static void beginSky() {
/* 3679 */     isRenderingSky = true;
/* 3680 */     fogEnabled = true;
/* 3681 */     setDrawBuffers(dfbDrawBuffers);
/* 3682 */     useProgram(5);
/* 3683 */     pushEntity(-2, 0);
/*      */   }
/*      */ 
/*      */   
/*      */   public static void setSkyColor(Vec3 v3color) {
/* 3688 */     skyColorR = (float)v3color.xCoord;
/* 3689 */     skyColorG = (float)v3color.yCoord;
/* 3690 */     skyColorB = (float)v3color.zCoord;
/* 3691 */     setProgramUniform3f("skyColor", skyColorR, skyColorG, skyColorB);
/*      */   }
/*      */ 
/*      */   
/*      */   public static void drawHorizon() {
/* 3696 */     WorldRenderer tess = Tessellator.getInstance().getWorldRenderer();
/* 3697 */     float farDistance = (mc.gameSettings.renderDistanceChunks * 16);
/* 3698 */     double xzq = farDistance * 0.9238D;
/* 3699 */     double xzp = farDistance * 0.3826D;
/* 3700 */     double xzn = -xzp;
/* 3701 */     double xzm = -xzq;
/* 3702 */     double top = 16.0D;
/* 3703 */     double bot = -cameraPositionY;
/* 3704 */     tess.startDrawingQuads();
/* 3705 */     tess.addVertex(xzn, bot, xzm);
/* 3706 */     tess.addVertex(xzn, top, xzm);
/* 3707 */     tess.addVertex(xzm, top, xzn);
/* 3708 */     tess.addVertex(xzm, bot, xzn);
/* 3709 */     tess.addVertex(xzm, bot, xzn);
/* 3710 */     tess.addVertex(xzm, top, xzn);
/* 3711 */     tess.addVertex(xzm, top, xzp);
/* 3712 */     tess.addVertex(xzm, bot, xzp);
/* 3713 */     tess.addVertex(xzm, bot, xzp);
/* 3714 */     tess.addVertex(xzm, top, xzp);
/* 3715 */     tess.addVertex(xzn, top, xzp);
/* 3716 */     tess.addVertex(xzn, bot, xzp);
/* 3717 */     tess.addVertex(xzn, bot, xzp);
/* 3718 */     tess.addVertex(xzn, top, xzp);
/* 3719 */     tess.addVertex(xzp, top, xzq);
/* 3720 */     tess.addVertex(xzp, bot, xzq);
/* 3721 */     tess.addVertex(xzp, bot, xzq);
/* 3722 */     tess.addVertex(xzp, top, xzq);
/* 3723 */     tess.addVertex(xzq, top, xzp);
/* 3724 */     tess.addVertex(xzq, bot, xzp);
/* 3725 */     tess.addVertex(xzq, bot, xzp);
/* 3726 */     tess.addVertex(xzq, top, xzp);
/* 3727 */     tess.addVertex(xzq, top, xzn);
/* 3728 */     tess.addVertex(xzq, bot, xzn);
/* 3729 */     tess.addVertex(xzq, bot, xzn);
/* 3730 */     tess.addVertex(xzq, top, xzn);
/* 3731 */     tess.addVertex(xzp, top, xzm);
/* 3732 */     tess.addVertex(xzp, bot, xzm);
/* 3733 */     tess.addVertex(xzp, bot, xzm);
/* 3734 */     tess.addVertex(xzp, top, xzm);
/* 3735 */     tess.addVertex(xzn, top, xzm);
/* 3736 */     tess.addVertex(xzn, bot, xzm);
/* 3737 */     Tessellator.getInstance().draw();
/*      */   }
/*      */ 
/*      */   
/*      */   public static void preSkyList() {
/* 3742 */     GL11.glColor3f(fogColorR, fogColorG, fogColorB);
/* 3743 */     drawHorizon();
/* 3744 */     GL11.glColor3f(skyColorR, skyColorG, skyColorB);
/*      */   }
/*      */ 
/*      */   
/*      */   public static void endSky() {
/* 3749 */     isRenderingSky = false;
/* 3750 */     setDrawBuffers(dfbDrawBuffers);
/* 3751 */     useProgram(lightmapEnabled ? 3 : 2);
/* 3752 */     popEntity();
/*      */   }
/*      */ 
/*      */   
/*      */   public static void beginUpdateChunks() {
/* 3757 */     checkGLError("beginUpdateChunks1");
/* 3758 */     checkFramebufferStatus("beginUpdateChunks1");
/*      */     
/* 3760 */     if (!isShadowPass)
/*      */     {
/* 3762 */       useProgram(7);
/*      */     }
/*      */     
/* 3765 */     checkGLError("beginUpdateChunks2");
/* 3766 */     checkFramebufferStatus("beginUpdateChunks2");
/*      */   }
/*      */ 
/*      */   
/*      */   public static void endUpdateChunks() {
/* 3771 */     checkGLError("endUpdateChunks1");
/* 3772 */     checkFramebufferStatus("endUpdateChunks1");
/*      */     
/* 3774 */     if (!isShadowPass)
/*      */     {
/* 3776 */       useProgram(7);
/*      */     }
/*      */     
/* 3779 */     checkGLError("endUpdateChunks2");
/* 3780 */     checkFramebufferStatus("endUpdateChunks2");
/*      */   }
/*      */ 
/*      */   
/*      */   public static boolean shouldRenderClouds(GameSettings gs) {
/* 3785 */     if (!shaderPackLoaded)
/*      */     {
/* 3787 */       return true;
/*      */     }
/*      */ 
/*      */     
/* 3791 */     checkGLError("shouldRenderClouds");
/* 3792 */     return isShadowPass ? configCloudShadow : gs.clouds;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public static void beginClouds() {
/* 3798 */     fogEnabled = true;
/* 3799 */     pushEntity(-3, 0);
/* 3800 */     useProgram(6);
/*      */   }
/*      */ 
/*      */   
/*      */   public static void endClouds() {
/* 3805 */     disableFog();
/* 3806 */     popEntity();
/* 3807 */     useProgram(lightmapEnabled ? 3 : 2);
/*      */   }
/*      */ 
/*      */   
/*      */   public static void beginEntities() {
/* 3812 */     if (isRenderingWorld) {
/*      */       
/* 3814 */       useProgram(16);
/* 3815 */       resetDisplayListModels();
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public static void nextEntity(Entity entity) {
/* 3821 */     if (isRenderingWorld) {
/*      */       
/* 3823 */       useProgram(16);
/* 3824 */       setEntityId(entity);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public static void setEntityId(Entity entity) {
/* 3830 */     if (isRenderingWorld && !isShadowPass && uniformEntityId.isDefined()) {
/*      */       
/* 3832 */       int id = EntityList.getEntityID(entity);
/* 3833 */       uniformEntityId.setValue(id);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public static void beginSpiderEyes() {
/* 3839 */     if (isRenderingWorld && programsID[18] != programsID[0]) {
/*      */       
/* 3841 */       useProgram(18);
/* 3842 */       GlStateManager.enableAlpha();
/* 3843 */       GlStateManager.alphaFunc(516, 0.0F);
/* 3844 */       GlStateManager.blendFunc(770, 771);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public static void endEntities() {
/* 3850 */     if (isRenderingWorld)
/*      */     {
/* 3852 */       useProgram(lightmapEnabled ? 3 : 2);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public static void setEntityColor(float r, float g, float b, float a) {
/* 3858 */     if (isRenderingWorld && !isShadowPass)
/*      */     {
/* 3860 */       uniformEntityColor.setValue(r, g, b, a);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public static void beginLivingDamage() {
/* 3866 */     if (isRenderingWorld) {
/*      */       
/* 3868 */       ShadersTex.bindTexture(defaultTexture);
/*      */       
/* 3870 */       if (!isShadowPass)
/*      */       {
/* 3872 */         setDrawBuffers(drawBuffersColorAtt0);
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public static void endLivingDamage() {
/* 3879 */     if (isRenderingWorld && !isShadowPass)
/*      */     {
/* 3881 */       setDrawBuffers(programsDrawBuffers[16]);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public static void beginBlockEntities() {
/* 3887 */     if (isRenderingWorld) {
/*      */       
/* 3889 */       checkGLError("beginBlockEntities");
/* 3890 */       useProgram(13);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public static void nextBlockEntity(TileEntity tileEntity) {
/* 3896 */     if (isRenderingWorld) {
/*      */       
/* 3898 */       checkGLError("nextBlockEntity");
/* 3899 */       useProgram(13);
/* 3900 */       setBlockEntityId(tileEntity);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public static void setBlockEntityId(TileEntity tileEntity) {
/* 3906 */     if (isRenderingWorld && !isShadowPass && uniformBlockEntityId.isDefined()) {
/*      */       
/* 3908 */       Block block = tileEntity.getBlockType();
/* 3909 */       int blockId = Block.getIdFromBlock(block);
/* 3910 */       uniformBlockEntityId.setValue(blockId);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public static void endBlockEntities() {
/* 3916 */     if (isRenderingWorld) {
/*      */       
/* 3918 */       checkGLError("endBlockEntities");
/* 3919 */       useProgram(lightmapEnabled ? 3 : 2);
/* 3920 */       ShadersTex.bindNSTextures(defaultTexture.getMultiTexID());
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public static void beginLitParticles() {
/* 3926 */     useProgram(3);
/*      */   }
/*      */ 
/*      */   
/*      */   public static void beginParticles() {
/* 3931 */     useProgram(2);
/*      */   }
/*      */ 
/*      */   
/*      */   public static void endParticles() {
/* 3936 */     useProgram(3);
/*      */   }
/*      */ 
/*      */   
/*      */   public static void readCenterDepth() {
/* 3941 */     if (!isShadowPass && centerDepthSmoothEnabled) {
/*      */       
/* 3943 */       tempDirectFloatBuffer.clear();
/* 3944 */       GL11.glReadPixels(renderWidth / 2, renderHeight / 2, 1, 1, 6402, 5126, tempDirectFloatBuffer);
/* 3945 */       centerDepth = tempDirectFloatBuffer.get(0);
/* 3946 */       float fadeScalar = (float)diffSystemTime * 0.01F;
/* 3947 */       float fadeFactor = (float)Math.exp(Math.log(0.5D) * fadeScalar / centerDepthSmoothHalflife);
/* 3948 */       centerDepthSmooth = centerDepthSmooth * fadeFactor + centerDepth * (1.0F - fadeFactor);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public static void beginWeather() {
/* 3954 */     if (!isShadowPass) {
/*      */       
/* 3956 */       if (usedDepthBuffers >= 3) {
/*      */         
/* 3958 */         GlStateManager.setActiveTexture(33996);
/* 3959 */         GL11.glCopyTexSubImage2D(3553, 0, 0, 0, 0, 0, renderWidth, renderHeight);
/* 3960 */         GlStateManager.setActiveTexture(33984);
/*      */       } 
/*      */       
/* 3963 */       GlStateManager.enableDepth();
/* 3964 */       GlStateManager.enableBlend();
/* 3965 */       GlStateManager.blendFunc(770, 771);
/* 3966 */       GlStateManager.enableAlpha();
/* 3967 */       useProgram(20);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public static void endWeather() {
/* 3973 */     GlStateManager.disableBlend();
/* 3974 */     useProgram(3);
/*      */   }
/*      */ 
/*      */   
/*      */   public static void preWater() {
/* 3979 */     if (usedDepthBuffers >= 2) {
/*      */       
/* 3981 */       GlStateManager.setActiveTexture(33995);
/* 3982 */       checkGLError("pre copy depth");
/* 3983 */       GL11.glCopyTexSubImage2D(3553, 0, 0, 0, 0, 0, renderWidth, renderHeight);
/* 3984 */       checkGLError("copy depth");
/* 3985 */       GlStateManager.setActiveTexture(33984);
/*      */     } 
/*      */     
/* 3988 */     ShadersTex.bindNSTextures(defaultTexture.getMultiTexID());
/*      */   }
/*      */ 
/*      */   
/*      */   public static void beginWater() {
/* 3993 */     if (isRenderingWorld)
/*      */     {
/* 3995 */       if (!isShadowPass) {
/*      */         
/* 3997 */         useProgram(12);
/* 3998 */         GlStateManager.enableBlend();
/* 3999 */         GlStateManager.depthMask(true);
/*      */       }
/*      */       else {
/*      */         
/* 4003 */         GlStateManager.depthMask(true);
/*      */       } 
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public static void endWater() {
/* 4010 */     if (isRenderingWorld) {
/*      */       
/* 4012 */       if (isShadowPass);
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 4017 */       useProgram(lightmapEnabled ? 3 : 2);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public static void beginProjectRedHalo() {
/* 4023 */     if (isRenderingWorld)
/*      */     {
/* 4025 */       useProgram(1);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public static void endProjectRedHalo() {
/* 4031 */     if (isRenderingWorld)
/*      */     {
/* 4033 */       useProgram(3);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public static void applyHandDepth() {
/* 4039 */     if (configHandDepthMul != 1.0D)
/*      */     {
/* 4041 */       GL11.glScaled(1.0D, 1.0D, configHandDepthMul);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public static void beginHand() {
/* 4047 */     GL11.glMatrixMode(5888);
/* 4048 */     GL11.glPushMatrix();
/* 4049 */     GL11.glMatrixMode(5889);
/* 4050 */     GL11.glPushMatrix();
/* 4051 */     GL11.glMatrixMode(5888);
/* 4052 */     useProgram(19);
/* 4053 */     checkGLError("beginHand");
/* 4054 */     checkFramebufferStatus("beginHand");
/*      */   }
/*      */ 
/*      */   
/*      */   public static void endHand() {
/* 4059 */     checkGLError("pre endHand");
/* 4060 */     checkFramebufferStatus("pre endHand");
/* 4061 */     GL11.glMatrixMode(5889);
/* 4062 */     GL11.glPopMatrix();
/* 4063 */     GL11.glMatrixMode(5888);
/* 4064 */     GL11.glPopMatrix();
/* 4065 */     GlStateManager.blendFunc(770, 771);
/* 4066 */     checkGLError("endHand");
/*      */   }
/*      */ 
/*      */   
/*      */   public static void beginFPOverlay() {
/* 4071 */     GlStateManager.disableLighting();
/* 4072 */     GlStateManager.disableBlend();
/*      */   }
/*      */ 
/*      */   
/*      */   public static void endFPOverlay() {}
/*      */   
/*      */   public static void glEnableWrapper(int cap) {
/* 4079 */     GL11.glEnable(cap);
/*      */     
/* 4081 */     if (cap == 3553) {
/*      */       
/* 4083 */       enableTexture2D();
/*      */     }
/* 4085 */     else if (cap == 2912) {
/*      */       
/* 4087 */       enableFog();
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public static void glDisableWrapper(int cap) {
/* 4093 */     GL11.glDisable(cap);
/*      */     
/* 4095 */     if (cap == 3553) {
/*      */       
/* 4097 */       disableTexture2D();
/*      */     }
/* 4099 */     else if (cap == 2912) {
/*      */       
/* 4101 */       disableFog();
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public static void sglEnableT2D(int cap) {
/* 4107 */     GL11.glEnable(cap);
/* 4108 */     enableTexture2D();
/*      */   }
/*      */ 
/*      */   
/*      */   public static void sglDisableT2D(int cap) {
/* 4113 */     GL11.glDisable(cap);
/* 4114 */     disableTexture2D();
/*      */   }
/*      */ 
/*      */   
/*      */   public static void sglEnableFog(int cap) {
/* 4119 */     GL11.glEnable(cap);
/* 4120 */     enableFog();
/*      */   }
/*      */ 
/*      */   
/*      */   public static void sglDisableFog(int cap) {
/* 4125 */     GL11.glDisable(cap);
/* 4126 */     disableFog();
/*      */   }
/*      */ 
/*      */   
/*      */   public static void enableTexture2D() {
/* 4131 */     if (isRenderingSky) {
/*      */       
/* 4133 */       useProgram(5);
/*      */     }
/* 4135 */     else if (activeProgram == 1) {
/*      */       
/* 4137 */       useProgram(lightmapEnabled ? 3 : 2);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public static void disableTexture2D() {
/* 4143 */     if (isRenderingSky) {
/*      */       
/* 4145 */       useProgram(4);
/*      */     }
/* 4147 */     else if (activeProgram == 2 || activeProgram == 3) {
/*      */       
/* 4149 */       useProgram(1);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public static void beginLeash() {
/* 4155 */     useProgram(1);
/*      */   }
/*      */ 
/*      */   
/*      */   public static void endLeash() {
/* 4160 */     useProgram(16);
/*      */   }
/*      */ 
/*      */   
/*      */   public static void enableFog() {
/* 4165 */     fogEnabled = true;
/* 4166 */     setProgramUniform1i("fogMode", fogMode);
/*      */   }
/*      */ 
/*      */   
/*      */   public static void disableFog() {
/* 4171 */     fogEnabled = false;
/* 4172 */     setProgramUniform1i("fogMode", 0);
/*      */   }
/*      */ 
/*      */   
/*      */   public static void setFog(int fogMode) {
/* 4177 */     GlStateManager.setFog(fogMode);
/* 4178 */     fogMode = fogMode;
/*      */     
/* 4180 */     if (fogEnabled)
/*      */     {
/* 4182 */       setProgramUniform1i("fogMode", fogMode);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public static void sglFogi(int pname, int param) {
/* 4188 */     GL11.glFogi(pname, param);
/*      */     
/* 4190 */     if (pname == 2917) {
/*      */       
/* 4192 */       fogMode = param;
/*      */       
/* 4194 */       if (fogEnabled)
/*      */       {
/* 4196 */         setProgramUniform1i("fogMode", fogMode);
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public static void enableLightmap() {
/* 4203 */     lightmapEnabled = true;
/*      */     
/* 4205 */     if (activeProgram == 2)
/*      */     {
/* 4207 */       useProgram(3);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public static void disableLightmap() {
/* 4213 */     lightmapEnabled = false;
/*      */     
/* 4215 */     if (activeProgram == 3)
/*      */     {
/* 4217 */       useProgram(2);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public static int getEntityData() {
/* 4223 */     return entityData[entityDataIndex * 2];
/*      */   }
/*      */ 
/*      */   
/*      */   public static int getEntityData2() {
/* 4228 */     return entityData[entityDataIndex * 2 + 1];
/*      */   }
/*      */ 
/*      */   
/*      */   public static int setEntityData1(int data1) {
/* 4233 */     entityData[entityDataIndex * 2] = entityData[entityDataIndex * 2] & 0xFFFF | data1 << 16;
/* 4234 */     return data1;
/*      */   }
/*      */ 
/*      */   
/*      */   public static int setEntityData2(int data2) {
/* 4239 */     entityData[entityDataIndex * 2 + 1] = entityData[entityDataIndex * 2 + 1] & 0xFFFF0000 | data2 & 0xFFFF;
/* 4240 */     return data2;
/*      */   }
/*      */ 
/*      */   
/*      */   public static void pushEntity(int data0, int data1) {
/* 4245 */     entityDataIndex++;
/* 4246 */     entityData[entityDataIndex * 2] = data0 & 0xFFFF | data1 << 16;
/* 4247 */     entityData[entityDataIndex * 2 + 1] = 0;
/*      */   }
/*      */ 
/*      */   
/*      */   public static void pushEntity(int data0) {
/* 4252 */     entityDataIndex++;
/* 4253 */     entityData[entityDataIndex * 2] = data0 & 0xFFFF;
/* 4254 */     entityData[entityDataIndex * 2 + 1] = 0;
/*      */   }
/*      */ 
/*      */   
/*      */   public static void pushEntity(Block block) {
/* 4259 */     entityDataIndex++;
/* 4260 */     entityData[entityDataIndex * 2] = Block.blockRegistry.getIDForObject(block) & 0xFFFF | block.getRenderType() << 16;
/* 4261 */     entityData[entityDataIndex * 2 + 1] = 0;
/*      */   }
/*      */ 
/*      */   
/*      */   public static void popEntity() {
/* 4266 */     entityData[entityDataIndex * 2] = 0;
/* 4267 */     entityData[entityDataIndex * 2 + 1] = 0;
/* 4268 */     entityDataIndex--;
/*      */   }
/*      */ 
/*      */   
/*      */   public static void mcProfilerEndSection() {
/* 4273 */     mc.mcProfiler.endSection();
/*      */   }
/*      */ 
/*      */   
/*      */   public static String getShaderPackName() {
/* 4278 */     return (shaderPack == null) ? null : ((shaderPack instanceof ShaderPackNone) ? null : shaderPack.getName());
/*      */   }
/*      */ 
/*      */   
/*      */   public static void nextAntialiasingLevel() {
/* 4283 */     configAntialiasingLevel += 2;
/* 4284 */     configAntialiasingLevel = configAntialiasingLevel / 2 * 2;
/*      */     
/* 4286 */     if (configAntialiasingLevel > 4)
/*      */     {
/* 4288 */       configAntialiasingLevel = 0;
/*      */     }
/*      */     
/* 4291 */     configAntialiasingLevel = Config.limit(configAntialiasingLevel, 0, 4);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public static void checkShadersModInstalled() {
/*      */     try {
/* 4298 */       Class<?> clazz = Class.forName("shadersmod.transform.SMCClassTransformer");
/*      */     }
/* 4300 */     catch (Throwable var1) {
/*      */       return;
/*      */     } 
/*      */ 
/*      */     
/* 4305 */     throw new RuntimeException("Shaders Mod detected. Please remove it, OptiFine has built-in support for shaders.");
/*      */   }
/*      */ 
/*      */   
/*      */   public static void resourcesReloaded() {
/* 4310 */     loadShaderPackResources();
/*      */   }
/*      */ 
/*      */   
/*      */   private static void loadShaderPackResources() {
/* 4315 */     shaderPackResources = new HashMap<>();
/*      */     
/* 4317 */     if (shaderPackLoaded) {
/*      */       
/* 4319 */       ArrayList<String> listFiles = new ArrayList();
/* 4320 */       String PREFIX = "/shaders/lang/";
/* 4321 */       String EN_US = "en_US";
/* 4322 */       String SUFFIX = ".lang";
/* 4323 */       listFiles.add(String.valueOf(PREFIX) + EN_US + SUFFIX);
/*      */       
/* 4325 */       if (!(Config.getGameSettings()).language.equals(EN_US))
/*      */       {
/* 4327 */         listFiles.add(String.valueOf(PREFIX) + (Config.getGameSettings()).language + SUFFIX);
/*      */       }
/*      */ 
/*      */       
/*      */       try {
/* 4332 */         Iterator<String> e = listFiles.iterator();
/*      */         
/* 4334 */         while (e.hasNext()) {
/*      */           
/* 4336 */           String file = e.next();
/* 4337 */           InputStream in = shaderPack.getResourceAsStream(file);
/*      */           
/* 4339 */           if (in != null) {
/*      */             
/* 4341 */             Properties props = new Properties();
/* 4342 */             Lang.loadLocaleData(in, props);
/* 4343 */             in.close();
/* 4344 */             Set keys = props.keySet();
/* 4345 */             Iterator<String> itp = keys.iterator();
/*      */             
/* 4347 */             while (itp.hasNext())
/*      */             {
/* 4349 */               String key = itp.next();
/* 4350 */               String value = props.getProperty(key);
/* 4351 */               shaderPackResources.put(key, value);
/*      */             }
/*      */           
/*      */           } 
/*      */         } 
/* 4356 */       } catch (IOException var12) {
/*      */         
/* 4358 */         var12.printStackTrace();
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public static String translate(String key, String def) {
/* 4365 */     String str = shaderPackResources.get(key);
/* 4366 */     return (str == null) ? def : str;
/*      */   }
/*      */ 
/*      */   
/*      */   public static boolean isProgramPath(String program) {
/* 4371 */     if (program == null)
/*      */     {
/* 4373 */       return false;
/*      */     }
/* 4375 */     if (program.length() <= 0)
/*      */     {
/* 4377 */       return false;
/*      */     }
/*      */ 
/*      */     
/* 4381 */     int pos = program.lastIndexOf("/");
/*      */     
/* 4383 */     if (pos >= 0)
/*      */     {
/* 4385 */       program = program.substring(pos + 1);
/*      */     }
/*      */     
/* 4388 */     return Arrays.<String>asList(programNames).contains(program);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   static {
/* 4394 */     drawBuffersNone.limit(0);
/* 4395 */     drawBuffersColorAtt0.put(36064).position(0).limit(1);
/* 4396 */   } private static final Pattern gbufferFormatPattern = Pattern.compile("[ \t]*const[ \t]*int[ \t]*(\\w+)Format[ \t]*=[ \t]*([RGBA81632FUI_SNORM]*)[ \t]*;.*");
/* 4397 */   private static final Pattern gbufferMipmapEnabledPattern = Pattern.compile("[ \t]*const[ \t]*bool[ \t]*(\\w+)MipmapEnabled[ \t]*=[ \t]*true[ \t]*;.*");
/* 4398 */   private static final String[] formatNames = new String[] { "R8", "RG8", "RGB8", "RGBA8", "R8_SNORM", "RG8_SNORM", "RGB8_SNORM", "RGBA8_SNORM", "R16", "RG16", "RGB16", "RGBA16", "R16_SNORM", "RG16_SNORM", "RGB16_SNORM", "RGBA16_SNORM", "R32F", "RG32F", "RGB32F", "RGBA32F", "R32I", "RG32I", "RGB32I", "RGBA32I", "R32UI", "RG32UI", "RGB32UI", "RGBA32UI" };
/* 4399 */   private static final int[] formatIds = new int[] { 33321, 33323, 32849, 32856, 36756, 36757, 36758, 36759, 33322, 33324, 32852, 32859, 36760, 36761, 36762, 36763, 33326, 33328, 34837, 34836, 33333, 33339, 36227, 36226, 33334, 33340, 36209, 36208 };
/* 4400 */   private static final Pattern patternLoadEntityDataMap = Pattern.compile("\\s*([\\w:]+)\\s*=\\s*([-]?\\d+)\\s*");
/* 4401 */   public static int[] entityData = new int[32];
/* 4402 */   public static int entityDataIndex = 0;
/*      */ 
/*      */   
/*      */   static class NamelessClass341846571
/*      */   {
/* 4407 */     static final int[] $SwitchMap$shadersmod$client$EnumShaderOption = new int[(EnumShaderOption.values()).length];
/*      */ 
/*      */ 
/*      */     
/*      */     static {
/*      */       try {
/* 4413 */         $SwitchMap$shadersmod$client$EnumShaderOption[EnumShaderOption.ANTIALIASING.ordinal()] = 1;
/*      */       }
/* 4415 */       catch (NoSuchFieldError noSuchFieldError) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       try {
/* 4422 */         $SwitchMap$shadersmod$client$EnumShaderOption[EnumShaderOption.NORMAL_MAP.ordinal()] = 2;
/*      */       }
/* 4424 */       catch (NoSuchFieldError noSuchFieldError) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       try {
/* 4431 */         $SwitchMap$shadersmod$client$EnumShaderOption[EnumShaderOption.SPECULAR_MAP.ordinal()] = 3;
/*      */       }
/* 4433 */       catch (NoSuchFieldError noSuchFieldError) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       try {
/* 4440 */         $SwitchMap$shadersmod$client$EnumShaderOption[EnumShaderOption.RENDER_RES_MUL.ordinal()] = 4;
/*      */       }
/* 4442 */       catch (NoSuchFieldError noSuchFieldError) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       try {
/* 4449 */         $SwitchMap$shadersmod$client$EnumShaderOption[EnumShaderOption.SHADOW_RES_MUL.ordinal()] = 5;
/*      */       }
/* 4451 */       catch (NoSuchFieldError noSuchFieldError) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       try {
/* 4458 */         $SwitchMap$shadersmod$client$EnumShaderOption[EnumShaderOption.HAND_DEPTH_MUL.ordinal()] = 6;
/*      */       }
/* 4460 */       catch (NoSuchFieldError noSuchFieldError) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       try {
/* 4467 */         $SwitchMap$shadersmod$client$EnumShaderOption[EnumShaderOption.CLOUD_SHADOW.ordinal()] = 7;
/*      */       }
/* 4469 */       catch (NoSuchFieldError noSuchFieldError) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       try {
/* 4476 */         $SwitchMap$shadersmod$client$EnumShaderOption[EnumShaderOption.OLD_LIGHTING.ordinal()] = 8;
/*      */       }
/* 4478 */       catch (NoSuchFieldError noSuchFieldError) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       try {
/* 4485 */         $SwitchMap$shadersmod$client$EnumShaderOption[EnumShaderOption.SHADER_PACK.ordinal()] = 9;
/*      */       }
/* 4487 */       catch (NoSuchFieldError noSuchFieldError) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       try {
/* 4494 */         $SwitchMap$shadersmod$client$EnumShaderOption[EnumShaderOption.TWEAK_BLOCK_DAMAGE.ordinal()] = 10;
/*      */       }
/* 4496 */       catch (NoSuchFieldError noSuchFieldError) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       try {
/* 4503 */         $SwitchMap$shadersmod$client$EnumShaderOption[EnumShaderOption.SHADOW_CLIP_FRUSTRUM.ordinal()] = 11;
/*      */       }
/* 4505 */       catch (NoSuchFieldError noSuchFieldError) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       try {
/* 4512 */         $SwitchMap$shadersmod$client$EnumShaderOption[EnumShaderOption.TEX_MIN_FIL_B.ordinal()] = 12;
/*      */       }
/* 4514 */       catch (NoSuchFieldError noSuchFieldError) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       try {
/* 4521 */         $SwitchMap$shadersmod$client$EnumShaderOption[EnumShaderOption.TEX_MIN_FIL_N.ordinal()] = 13;
/*      */       }
/* 4523 */       catch (NoSuchFieldError noSuchFieldError) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       try {
/* 4530 */         $SwitchMap$shadersmod$client$EnumShaderOption[EnumShaderOption.TEX_MIN_FIL_S.ordinal()] = 14;
/*      */       }
/* 4532 */       catch (NoSuchFieldError noSuchFieldError) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       try {
/* 4539 */         $SwitchMap$shadersmod$client$EnumShaderOption[EnumShaderOption.TEX_MAG_FIL_B.ordinal()] = 15;
/*      */       }
/* 4541 */       catch (NoSuchFieldError noSuchFieldError) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       try {
/* 4548 */         $SwitchMap$shadersmod$client$EnumShaderOption[EnumShaderOption.TEX_MAG_FIL_N.ordinal()] = 16;
/*      */       }
/* 4550 */       catch (NoSuchFieldError noSuchFieldError) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       try {
/* 4557 */         $SwitchMap$shadersmod$client$EnumShaderOption[EnumShaderOption.TEX_MAG_FIL_S.ordinal()] = 17;
/*      */       }
/* 4559 */       catch (NoSuchFieldError noSuchFieldError) {}
/*      */     }
/*      */   }
/*      */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\shadersmod\client\Shaders.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */