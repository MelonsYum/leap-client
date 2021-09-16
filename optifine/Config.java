/*      */ package optifine;
/*      */ 
/*      */ import java.awt.Dimension;
/*      */ import java.awt.image.BufferedImage;
/*      */ import java.io.BufferedReader;
/*      */ import java.io.ByteArrayOutputStream;
/*      */ import java.io.File;
/*      */ import java.io.FileDescriptor;
/*      */ import java.io.FileInputStream;
/*      */ import java.io.FileOutputStream;
/*      */ import java.io.IOException;
/*      */ import java.io.InputStream;
/*      */ import java.io.InputStreamReader;
/*      */ import java.io.PrintStream;
/*      */ import java.lang.reflect.Array;
/*      */ import java.lang.reflect.Field;
/*      */ import java.lang.reflect.Method;
/*      */ import java.nio.ByteBuffer;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Arrays;
/*      */ import java.util.Comparator;
/*      */ import java.util.Iterator;
/*      */ import java.util.List;
/*      */ import java.util.Properties;
/*      */ import java.util.StringTokenizer;
/*      */ import java.util.regex.Matcher;
/*      */ import java.util.regex.Pattern;
/*      */ import javax.imageio.ImageIO;
/*      */ import net.minecraft.client.LoadingScreenRenderer;
/*      */ import net.minecraft.client.Minecraft;
/*      */ import net.minecraft.client.gui.ScaledResolution;
/*      */ import net.minecraft.client.multiplayer.WorldClient;
/*      */ import net.minecraft.client.renderer.GlStateManager;
/*      */ import net.minecraft.client.renderer.RenderGlobal;
/*      */ import net.minecraft.client.renderer.texture.DynamicTexture;
/*      */ import net.minecraft.client.renderer.texture.TextureManager;
/*      */ import net.minecraft.client.renderer.texture.TextureMap;
/*      */ import net.minecraft.client.resources.DefaultResourcePack;
/*      */ import net.minecraft.client.resources.IResource;
/*      */ import net.minecraft.client.resources.IResourceManager;
/*      */ import net.minecraft.client.resources.IResourcePack;
/*      */ import net.minecraft.client.resources.ResourcePackRepository;
/*      */ import net.minecraft.client.resources.model.ModelManager;
/*      */ import net.minecraft.client.settings.GameSettings;
/*      */ import net.minecraft.server.integrated.IntegratedServer;
/*      */ import net.minecraft.util.BlockPos;
/*      */ import net.minecraft.util.ResourceLocation;
/*      */ import net.minecraft.world.WorldProvider;
/*      */ import net.minecraft.world.WorldServer;
/*      */ import org.apache.commons.io.IOUtils;
/*      */ import org.lwjgl.LWJGLException;
/*      */ import org.lwjgl.Sys;
/*      */ import org.lwjgl.opengl.Display;
/*      */ import org.lwjgl.opengl.DisplayMode;
/*      */ import org.lwjgl.opengl.GL11;
/*      */ import org.lwjgl.opengl.GLContext;
/*      */ import org.lwjgl.opengl.PixelFormat;
/*      */ import org.lwjgl.util.glu.GLU;
/*      */ import shadersmod.client.Shaders;
/*      */ 
/*      */ 
/*      */ public class Config
/*      */ {
/*      */   public static final String OF_NAME = "OptiFine";
/*      */   public static final String MC_VERSION = "1.8";
/*      */   public static final String OF_EDITION = "HD_U";
/*      */   public static final String OF_RELEASE = "H6";
/*      */   public static final String VERSION = "OptiFine_1.8_HD_U_H6";
/*   69 */   private static String newRelease = null;
/*      */   private static boolean notify64BitJava = false;
/*   71 */   public static String openGlVersion = null;
/*   72 */   public static String openGlRenderer = null;
/*   73 */   public static String openGlVendor = null;
/*      */   public static boolean fancyFogAvailable = false;
/*      */   public static boolean occlusionAvailable = false;
/*   76 */   private static GameSettings gameSettings = null;
/*   77 */   private static Minecraft minecraft = null;
/*      */   private static boolean initialized = false;
/*   79 */   private static Thread minecraftThread = null;
/*   80 */   private static DisplayMode desktopDisplayMode = null;
/*   81 */   private static int antialiasingLevel = 0;
/*   82 */   private static int availableProcessors = 0;
/*      */   public static boolean zoomMode = false;
/*   84 */   private static int texturePackClouds = 0;
/*      */   public static boolean waterOpacityChanged = false;
/*      */   private static boolean fullscreenModeChecked = false;
/*      */   private static boolean desktopModeChecked = false;
/*   88 */   private static DefaultResourcePack defaultResourcePack = null;
/*   89 */   private static ModelManager modelManager = null;
/*   90 */   private static PrintStream systemOut = new PrintStream(new FileOutputStream(FileDescriptor.out));
/*   91 */   public static final Float DEF_ALPHA_FUNC_LEVEL = Float.valueOf(0.1F);
/*      */ 
/*      */   
/*      */   public static String getVersion() {
/*   95 */     return "OptiFine_1.8_HD_U_H6";
/*      */   }
/*      */ 
/*      */   
/*      */   public static String getVersionDebug() {
/*  100 */     StringBuffer sb = new StringBuffer(32);
/*      */     
/*  102 */     if (isDynamicLights()) {
/*      */       
/*  104 */       sb.append("DL: ");
/*  105 */       sb.append(String.valueOf(DynamicLights.getCount()));
/*  106 */       sb.append(", ");
/*      */     } 
/*      */     
/*  109 */     sb.append("OptiFine_1.8_HD_U_H6");
/*  110 */     String shaderPack = Shaders.getShaderPackName();
/*      */     
/*  112 */     if (shaderPack != null) {
/*      */       
/*  114 */       sb.append(", ");
/*  115 */       sb.append(shaderPack);
/*      */     } 
/*      */     
/*  118 */     return sb.toString();
/*      */   }
/*      */ 
/*      */   
/*      */   public static void initGameSettings(GameSettings settings) {
/*  123 */     if (gameSettings == null) {
/*      */       
/*  125 */       gameSettings = settings;
/*  126 */       minecraft = Minecraft.getMinecraft();
/*  127 */       desktopDisplayMode = Display.getDesktopDisplayMode();
/*  128 */       updateAvailableProcessors();
/*  129 */       ReflectorForge.putLaunchBlackboard("optifine.ForgeSplashCompatible", Boolean.TRUE);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public static void initDisplay() {
/*  135 */     checkInitialized();
/*  136 */     antialiasingLevel = gameSettings.ofAaLevel;
/*  137 */     checkDisplaySettings();
/*  138 */     checkDisplayMode();
/*  139 */     minecraftThread = Thread.currentThread();
/*  140 */     updateThreadPriorities();
/*  141 */     Shaders.startup(Minecraft.getMinecraft());
/*      */   }
/*      */ 
/*      */   
/*      */   public static void checkInitialized() {
/*  146 */     if (!initialized)
/*      */     {
/*  148 */       if (Display.isCreated()) {
/*      */         
/*  150 */         initialized = true;
/*  151 */         checkOpenGlCaps();
/*  152 */         startVersionCheckThread();
/*      */       } 
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   private static void checkOpenGlCaps() {
/*  159 */     log("");
/*  160 */     log(getVersion());
/*  161 */     log("Build: " + getBuild());
/*  162 */     log("OS: " + System.getProperty("os.name") + " (" + System.getProperty("os.arch") + ") version " + System.getProperty("os.version"));
/*  163 */     log("Java: " + System.getProperty("java.version") + ", " + System.getProperty("java.vendor"));
/*  164 */     log("VM: " + System.getProperty("java.vm.name") + " (" + System.getProperty("java.vm.info") + "), " + System.getProperty("java.vm.vendor"));
/*  165 */     log("LWJGL: " + Sys.getVersion());
/*  166 */     openGlVersion = GL11.glGetString(7938);
/*  167 */     openGlRenderer = GL11.glGetString(7937);
/*  168 */     openGlVendor = GL11.glGetString(7936);
/*  169 */     log("OpenGL: " + openGlRenderer + ", version " + openGlVersion + ", " + openGlVendor);
/*  170 */     log("OpenGL Version: " + getOpenGlVersionString());
/*      */     
/*  172 */     if (!(GLContext.getCapabilities()).OpenGL12)
/*      */     {
/*  174 */       log("OpenGL Mipmap levels: Not available (GL12.GL_TEXTURE_MAX_LEVEL)");
/*      */     }
/*      */     
/*  177 */     fancyFogAvailable = (GLContext.getCapabilities()).GL_NV_fog_distance;
/*      */     
/*  179 */     if (!fancyFogAvailable)
/*      */     {
/*  181 */       log("OpenGL Fancy fog: Not available (GL_NV_fog_distance)");
/*      */     }
/*      */     
/*  184 */     occlusionAvailable = (GLContext.getCapabilities()).GL_ARB_occlusion_query;
/*      */     
/*  186 */     if (!occlusionAvailable)
/*      */     {
/*  188 */       log("OpenGL Occlussion culling: Not available (GL_ARB_occlusion_query)");
/*      */     }
/*      */     
/*  191 */     int maxTexSize = Minecraft.getGLMaximumTextureSize();
/*  192 */     dbg("Maximum texture size: " + maxTexSize + "x" + maxTexSize);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private static String getBuild() {
/*      */     try {
/*  199 */       InputStream e = Config.class.getResourceAsStream("/buildof.txt");
/*      */       
/*  201 */       if (e == null)
/*      */       {
/*  203 */         return null;
/*      */       }
/*      */ 
/*      */       
/*  207 */       String build = readLines(e)[0];
/*  208 */       return build;
/*      */     
/*      */     }
/*  211 */     catch (Exception var2) {
/*      */       
/*  213 */       warn(var2.getClass().getName() + ": " + var2.getMessage());
/*  214 */       return null;
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public static boolean isFancyFogAvailable() {
/*  220 */     return fancyFogAvailable;
/*      */   }
/*      */ 
/*      */   
/*      */   public static boolean isOcclusionAvailable() {
/*  225 */     return occlusionAvailable;
/*      */   }
/*      */ 
/*      */   
/*      */   public static String getOpenGlVersionString() {
/*  230 */     int ver = getOpenGlVersion();
/*  231 */     String verStr = (ver / 10) + "." + (ver % 10);
/*  232 */     return verStr;
/*      */   }
/*      */ 
/*      */   
/*      */   private static int getOpenGlVersion() {
/*  237 */     return !(GLContext.getCapabilities()).OpenGL11 ? 10 : (!(GLContext.getCapabilities()).OpenGL12 ? 11 : (!(GLContext.getCapabilities()).OpenGL13 ? 12 : (!(GLContext.getCapabilities()).OpenGL14 ? 13 : (!(GLContext.getCapabilities()).OpenGL15 ? 14 : (!(GLContext.getCapabilities()).OpenGL20 ? 15 : (!(GLContext.getCapabilities()).OpenGL21 ? 20 : (!(GLContext.getCapabilities()).OpenGL30 ? 21 : (!(GLContext.getCapabilities()).OpenGL31 ? 30 : (!(GLContext.getCapabilities()).OpenGL32 ? 31 : (!(GLContext.getCapabilities()).OpenGL33 ? 32 : (!(GLContext.getCapabilities()).OpenGL40 ? 33 : 40)))))))))));
/*      */   }
/*      */ 
/*      */   
/*      */   public static void updateThreadPriorities() {
/*  242 */     updateAvailableProcessors();
/*  243 */     boolean ELEVATED_PRIORITY = true;
/*      */     
/*  245 */     if (isSingleProcessor()) {
/*      */       
/*  247 */       if (isSmoothWorld())
/*      */       {
/*  249 */         minecraftThread.setPriority(10);
/*  250 */         setThreadPriority("Server thread", 1);
/*      */       }
/*      */       else
/*      */       {
/*  254 */         minecraftThread.setPriority(5);
/*  255 */         setThreadPriority("Server thread", 5);
/*      */       }
/*      */     
/*      */     } else {
/*      */       
/*  260 */       minecraftThread.setPriority(10);
/*  261 */       setThreadPriority("Server thread", 5);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private static void setThreadPriority(String prefix, int priority) {
/*      */     try {
/*  269 */       ThreadGroup e = Thread.currentThread().getThreadGroup();
/*      */       
/*  271 */       if (e == null) {
/*      */         return;
/*      */       }
/*      */ 
/*      */       
/*  276 */       int num = (e.activeCount() + 10) * 2;
/*  277 */       Thread[] ts = new Thread[num];
/*  278 */       e.enumerate(ts, false);
/*      */       
/*  280 */       for (int i = 0; i < ts.length; i++)
/*      */       {
/*  282 */         Thread t = ts[i];
/*      */         
/*  284 */         if (t != null && t.getName().startsWith(prefix))
/*      */         {
/*  286 */           t.setPriority(priority);
/*      */         }
/*      */       }
/*      */     
/*  290 */     } catch (Throwable var7) {
/*      */       
/*  292 */       warn(String.valueOf(var7.getClass().getName()) + ": " + var7.getMessage());
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public static boolean isMinecraftThread() {
/*  298 */     return (Thread.currentThread() == minecraftThread);
/*      */   }
/*      */ 
/*      */   
/*      */   private static void startVersionCheckThread() {
/*  303 */     VersionCheckThread vct = new VersionCheckThread();
/*  304 */     vct.start();
/*      */   }
/*      */ 
/*      */   
/*      */   public static boolean isMipmaps() {
/*  309 */     return (gameSettings.mipmapLevels > 0);
/*      */   }
/*      */ 
/*      */   
/*      */   public static int getMipmapLevels() {
/*  314 */     return gameSettings.mipmapLevels;
/*      */   }
/*      */ 
/*      */   
/*      */   public static int getMipmapType() {
/*  319 */     switch (gameSettings.ofMipmapType) {
/*      */       
/*      */       case 0:
/*  322 */         return 9986;
/*      */       
/*      */       case 1:
/*  325 */         return 9986;
/*      */       
/*      */       case 2:
/*  328 */         if (isMultiTexture())
/*      */         {
/*  330 */           return 9985;
/*      */         }
/*      */         
/*  333 */         return 9986;
/*      */       
/*      */       case 3:
/*  336 */         if (isMultiTexture())
/*      */         {
/*  338 */           return 9987;
/*      */         }
/*      */         
/*  341 */         return 9986;
/*      */     } 
/*      */     
/*  344 */     return 9986;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean isUseAlphaFunc() {
/*  350 */     float alphaFuncLevel = getAlphaFuncLevel();
/*  351 */     return (alphaFuncLevel > DEF_ALPHA_FUNC_LEVEL.floatValue() + 1.0E-5F);
/*      */   }
/*      */ 
/*      */   
/*      */   public static float getAlphaFuncLevel() {
/*  356 */     return DEF_ALPHA_FUNC_LEVEL.floatValue();
/*      */   }
/*      */ 
/*      */   
/*      */   public static boolean isFogFancy() {
/*  361 */     return !isFancyFogAvailable() ? false : ((gameSettings.ofFogType == 2));
/*      */   }
/*      */ 
/*      */   
/*      */   public static boolean isFogFast() {
/*  366 */     return (gameSettings.ofFogType == 1);
/*      */   }
/*      */ 
/*      */   
/*      */   public static boolean isFogOff() {
/*  371 */     return (gameSettings.ofFogType == 3);
/*      */   }
/*      */ 
/*      */   
/*      */   public static float getFogStart() {
/*  376 */     return gameSettings.ofFogStart;
/*      */   }
/*      */ 
/*      */   
/*      */   public static void dbg(String s) {
/*  381 */     systemOut.print("[OptiFine] ");
/*  382 */     systemOut.println(s);
/*      */   }
/*      */ 
/*      */   
/*      */   public static void warn(String s) {
/*  387 */     systemOut.print("[OptiFine] [WARN] ");
/*  388 */     systemOut.println(s);
/*      */   }
/*      */ 
/*      */   
/*      */   public static void error(String s) {
/*  393 */     systemOut.print("[OptiFine] [ERROR] ");
/*  394 */     systemOut.println(s);
/*      */   }
/*      */ 
/*      */   
/*      */   public static void log(String s) {
/*  399 */     dbg(s);
/*      */   }
/*      */ 
/*      */   
/*      */   public static int getUpdatesPerFrame() {
/*  404 */     return gameSettings.ofChunkUpdates;
/*      */   }
/*      */ 
/*      */   
/*      */   public static boolean isDynamicUpdates() {
/*  409 */     return gameSettings.ofChunkUpdatesDynamic;
/*      */   }
/*      */ 
/*      */   
/*      */   public static boolean isRainFancy() {
/*  414 */     return (gameSettings.ofRain == 0) ? gameSettings.fancyGraphics : ((gameSettings.ofRain == 2));
/*      */   }
/*      */ 
/*      */   
/*      */   public static boolean isRainOff() {
/*  419 */     return (gameSettings.ofRain == 3);
/*      */   }
/*      */ 
/*      */   
/*      */   public static boolean isCloudsFancy() {
/*  424 */     return (gameSettings.ofClouds != 0) ? ((gameSettings.ofClouds == 2)) : ((isShaders() && !Shaders.shaderPackClouds.isDefault()) ? Shaders.shaderPackClouds.isFancy() : ((texturePackClouds != 0) ? ((texturePackClouds == 2)) : gameSettings.fancyGraphics));
/*      */   }
/*      */ 
/*      */   
/*      */   public static boolean isCloudsOff() {
/*  429 */     return (gameSettings.ofClouds != 0) ? ((gameSettings.ofClouds == 3)) : ((isShaders() && !Shaders.shaderPackClouds.isDefault()) ? Shaders.shaderPackClouds.isOff() : ((texturePackClouds != 0) ? ((texturePackClouds == 3)) : false));
/*      */   }
/*      */ 
/*      */   
/*      */   public static void updateTexturePackClouds() {
/*  434 */     texturePackClouds = 0;
/*  435 */     IResourceManager rm = getResourceManager();
/*      */     
/*  437 */     if (rm != null) {
/*      */       
/*      */       try {
/*      */         
/*  441 */         InputStream e = rm.getResource(new ResourceLocation("mcpatcher/color.properties")).getInputStream();
/*      */         
/*  443 */         if (e == null) {
/*      */           return;
/*      */         }
/*      */ 
/*      */         
/*  448 */         Properties props = new Properties();
/*  449 */         props.load(e);
/*  450 */         e.close();
/*  451 */         String cloudStr = props.getProperty("clouds");
/*      */         
/*  453 */         if (cloudStr == null) {
/*      */           return;
/*      */         }
/*      */ 
/*      */         
/*  458 */         dbg("Texture pack clouds: " + cloudStr);
/*  459 */         cloudStr = cloudStr.toLowerCase();
/*      */         
/*  461 */         if (cloudStr.equals("fast"))
/*      */         {
/*  463 */           texturePackClouds = 1;
/*      */         }
/*      */         
/*  466 */         if (cloudStr.equals("fancy"))
/*      */         {
/*  468 */           texturePackClouds = 2;
/*      */         }
/*      */         
/*  471 */         if (cloudStr.equals("off"))
/*      */         {
/*  473 */           texturePackClouds = 3;
/*      */         }
/*      */       }
/*  476 */       catch (Exception exception) {}
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void setModelManager(ModelManager modelManager) {
/*  485 */     modelManager = modelManager;
/*      */   }
/*      */ 
/*      */   
/*      */   public static ModelManager getModelManager() {
/*  490 */     return modelManager;
/*      */   }
/*      */ 
/*      */   
/*      */   public static boolean isTreesFancy() {
/*  495 */     return (gameSettings.ofTrees == 0) ? gameSettings.fancyGraphics : ((gameSettings.ofTrees != 1));
/*      */   }
/*      */ 
/*      */   
/*      */   public static boolean isTreesSmart() {
/*  500 */     return (gameSettings.ofTrees == 4);
/*      */   }
/*      */ 
/*      */   
/*      */   public static boolean isCullFacesLeaves() {
/*  505 */     return (gameSettings.ofTrees == 0) ? (!gameSettings.fancyGraphics) : ((gameSettings.ofTrees == 4));
/*      */   }
/*      */ 
/*      */   
/*      */   public static boolean isDroppedItemsFancy() {
/*  510 */     return (gameSettings.ofDroppedItems == 0) ? gameSettings.fancyGraphics : ((gameSettings.ofDroppedItems == 2));
/*      */   }
/*      */ 
/*      */   
/*      */   public static int limit(int val, int min, int max) {
/*  515 */     return (val < min) ? min : ((val > max) ? max : val);
/*      */   }
/*      */ 
/*      */   
/*      */   public static float limit(float val, float min, float max) {
/*  520 */     return (val < min) ? min : ((val > max) ? max : val);
/*      */   }
/*      */ 
/*      */   
/*      */   public static double limit(double val, double min, double max) {
/*  525 */     return (val < min) ? min : ((val > max) ? max : val);
/*      */   }
/*      */ 
/*      */   
/*      */   public static float limitTo1(float val) {
/*  530 */     return (val < 0.0F) ? 0.0F : ((val > 1.0F) ? 1.0F : val);
/*      */   }
/*      */ 
/*      */   
/*      */   public static boolean isAnimatedWater() {
/*  535 */     return (gameSettings.ofAnimatedWater != 2);
/*      */   }
/*      */ 
/*      */   
/*      */   public static boolean isGeneratedWater() {
/*  540 */     return (gameSettings.ofAnimatedWater == 1);
/*      */   }
/*      */ 
/*      */   
/*      */   public static boolean isAnimatedPortal() {
/*  545 */     return gameSettings.ofAnimatedPortal;
/*      */   }
/*      */ 
/*      */   
/*      */   public static boolean isAnimatedLava() {
/*  550 */     return (gameSettings.ofAnimatedLava != 2);
/*      */   }
/*      */ 
/*      */   
/*      */   public static boolean isGeneratedLava() {
/*  555 */     return (gameSettings.ofAnimatedLava == 1);
/*      */   }
/*      */ 
/*      */   
/*      */   public static boolean isAnimatedFire() {
/*  560 */     return gameSettings.ofAnimatedFire;
/*      */   }
/*      */ 
/*      */   
/*      */   public static boolean isAnimatedRedstone() {
/*  565 */     return gameSettings.ofAnimatedRedstone;
/*      */   }
/*      */ 
/*      */   
/*      */   public static boolean isAnimatedExplosion() {
/*  570 */     return gameSettings.ofAnimatedExplosion;
/*      */   }
/*      */ 
/*      */   
/*      */   public static boolean isAnimatedFlame() {
/*  575 */     return gameSettings.ofAnimatedFlame;
/*      */   }
/*      */ 
/*      */   
/*      */   public static boolean isAnimatedSmoke() {
/*  580 */     return gameSettings.ofAnimatedSmoke;
/*      */   }
/*      */ 
/*      */   
/*      */   public static boolean isVoidParticles() {
/*  585 */     return gameSettings.ofVoidParticles;
/*      */   }
/*      */ 
/*      */   
/*      */   public static boolean isWaterParticles() {
/*  590 */     return gameSettings.ofWaterParticles;
/*      */   }
/*      */ 
/*      */   
/*      */   public static boolean isRainSplash() {
/*  595 */     return gameSettings.ofRainSplash;
/*      */   }
/*      */ 
/*      */   
/*      */   public static boolean isPortalParticles() {
/*  600 */     return gameSettings.ofPortalParticles;
/*      */   }
/*      */ 
/*      */   
/*      */   public static boolean isPotionParticles() {
/*  605 */     return gameSettings.ofPotionParticles;
/*      */   }
/*      */ 
/*      */   
/*      */   public static boolean isFireworkParticles() {
/*  610 */     return gameSettings.ofFireworkParticles;
/*      */   }
/*      */ 
/*      */   
/*      */   public static float getAmbientOcclusionLevel() {
/*  615 */     return gameSettings.ofAoLevel;
/*      */   }
/*      */ 
/*      */   
/*      */   private static Method getMethod(Class cls, String methodName, Object[] params) {
/*  620 */     Method[] methods = cls.getMethods();
/*      */     
/*  622 */     for (int i = 0; i < methods.length; i++) {
/*      */       
/*  624 */       Method m = methods[i];
/*      */       
/*  626 */       if (m.getName().equals(methodName) && (m.getParameterTypes()).length == params.length)
/*      */       {
/*  628 */         return m;
/*      */       }
/*      */     } 
/*      */     
/*  632 */     warn("No method found for: " + cls.getName() + "." + methodName + "(" + arrayToString(params) + ")");
/*  633 */     return null;
/*      */   }
/*      */ 
/*      */   
/*      */   public static String arrayToString(Object[] arr) {
/*  638 */     if (arr == null)
/*      */     {
/*  640 */       return "";
/*      */     }
/*      */ 
/*      */     
/*  644 */     StringBuffer buf = new StringBuffer(arr.length * 5);
/*      */     
/*  646 */     for (int i = 0; i < arr.length; i++) {
/*      */       
/*  648 */       Object obj = arr[i];
/*      */       
/*  650 */       if (i > 0)
/*      */       {
/*  652 */         buf.append(", ");
/*      */       }
/*      */       
/*  655 */       buf.append(String.valueOf(obj));
/*      */     } 
/*      */     
/*  658 */     return buf.toString();
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public static String arrayToString(int[] arr) {
/*  664 */     if (arr == null)
/*      */     {
/*  666 */       return "";
/*      */     }
/*      */ 
/*      */     
/*  670 */     StringBuffer buf = new StringBuffer(arr.length * 5);
/*      */     
/*  672 */     for (int i = 0; i < arr.length; i++) {
/*      */       
/*  674 */       int x = arr[i];
/*      */       
/*  676 */       if (i > 0)
/*      */       {
/*  678 */         buf.append(", ");
/*      */       }
/*      */       
/*  681 */       buf.append(String.valueOf(x));
/*      */     } 
/*      */     
/*  684 */     return buf.toString();
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public static Minecraft getMinecraft() {
/*  690 */     return minecraft;
/*      */   }
/*      */ 
/*      */   
/*      */   public static TextureManager getTextureManager() {
/*  695 */     return minecraft.getTextureManager();
/*      */   }
/*      */ 
/*      */   
/*      */   public static IResourceManager getResourceManager() {
/*  700 */     return minecraft.getResourceManager();
/*      */   }
/*      */ 
/*      */   
/*      */   public static InputStream getResourceStream(ResourceLocation location) throws IOException {
/*  705 */     return getResourceStream(minecraft.getResourceManager(), location);
/*      */   }
/*      */ 
/*      */   
/*      */   public static InputStream getResourceStream(IResourceManager resourceManager, ResourceLocation location) throws IOException {
/*  710 */     IResource res = resourceManager.getResource(location);
/*  711 */     return (res == null) ? null : res.getInputStream();
/*      */   }
/*      */ 
/*      */   
/*      */   public static IResource getResource(ResourceLocation location) throws IOException {
/*  716 */     return minecraft.getResourceManager().getResource(location);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean hasResource(ResourceLocation location) {
/*      */     try {
/*  723 */       IResource e = getResource(location);
/*  724 */       return (e != null);
/*      */     }
/*  726 */     catch (IOException var2) {
/*      */       
/*  728 */       return false;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean hasResource(IResourceManager resourceManager, ResourceLocation location) {
/*      */     try {
/*  736 */       IResource e = resourceManager.getResource(location);
/*  737 */       return (e != null);
/*      */     }
/*  739 */     catch (IOException var3) {
/*      */       
/*  741 */       return false;
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public static IResourcePack[] getResourcePacks() {
/*  747 */     ResourcePackRepository rep = minecraft.getResourcePackRepository();
/*  748 */     List entries = rep.getRepositoryEntries();
/*  749 */     ArrayList<IResourcePack> list = new ArrayList();
/*  750 */     Iterator<ResourcePackRepository.Entry> rps = entries.iterator();
/*      */     
/*  752 */     while (rps.hasNext()) {
/*      */       
/*  754 */       ResourcePackRepository.Entry entry = rps.next();
/*  755 */       list.add(entry.getResourcePack());
/*      */     } 
/*      */     
/*  758 */     if (rep.getResourcePackInstance() != null)
/*      */     {
/*  760 */       list.add(rep.getResourcePackInstance());
/*      */     }
/*      */     
/*  763 */     IResourcePack[] rps1 = list.<IResourcePack>toArray(new IResourcePack[list.size()]);
/*  764 */     return rps1;
/*      */   }
/*      */ 
/*      */   
/*      */   public static String getResourcePackNames() {
/*  769 */     if (minecraft == null)
/*      */     {
/*  771 */       return "";
/*      */     }
/*  773 */     if (minecraft.getResourcePackRepository() == null)
/*      */     {
/*  775 */       return "";
/*      */     }
/*      */ 
/*      */     
/*  779 */     IResourcePack[] rps = getResourcePacks();
/*      */     
/*  781 */     if (rps.length <= 0)
/*      */     {
/*  783 */       return getDefaultResourcePack().getPackName();
/*      */     }
/*      */ 
/*      */     
/*  787 */     String[] names = new String[rps.length];
/*      */     
/*  789 */     for (int nameStr = 0; nameStr < rps.length; nameStr++)
/*      */     {
/*  791 */       names[nameStr] = rps[nameStr].getPackName();
/*      */     }
/*      */     
/*  794 */     String var3 = arrayToString((Object[])names);
/*  795 */     return var3;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static DefaultResourcePack getDefaultResourcePack() {
/*  802 */     if (defaultResourcePack == null) {
/*      */       
/*  804 */       Minecraft mc = Minecraft.getMinecraft();
/*      */ 
/*      */       
/*      */       try {
/*  808 */         Field[] repository = mc.getClass().getDeclaredFields();
/*      */         
/*  810 */         for (int i = 0; i < repository.length; i++) {
/*      */           
/*  812 */           Field field = repository[i];
/*      */           
/*  814 */           if (field.getType() == DefaultResourcePack.class) {
/*      */             
/*  816 */             field.setAccessible(true);
/*  817 */             defaultResourcePack = (DefaultResourcePack)field.get(mc);
/*      */             
/*      */             break;
/*      */           } 
/*      */         } 
/*  822 */       } catch (Exception var4) {
/*      */         
/*  824 */         warn("Error getting default resource pack: " + var4.getClass().getName() + ": " + var4.getMessage());
/*      */       } 
/*      */       
/*  827 */       if (defaultResourcePack == null) {
/*      */         
/*  829 */         ResourcePackRepository var5 = mc.getResourcePackRepository();
/*      */         
/*  831 */         if (var5 != null)
/*      */         {
/*  833 */           defaultResourcePack = (DefaultResourcePack)var5.rprDefaultResourcePack;
/*      */         }
/*      */       } 
/*      */     } 
/*      */     
/*  838 */     return defaultResourcePack;
/*      */   }
/*      */ 
/*      */   
/*      */   public static boolean isFromDefaultResourcePack(ResourceLocation loc) {
/*  843 */     IResourcePack rp = getDefiningResourcePack(loc);
/*  844 */     return (rp == getDefaultResourcePack());
/*      */   }
/*      */ 
/*      */   
/*      */   public static IResourcePack getDefiningResourcePack(ResourceLocation loc) {
/*  849 */     IResourcePack[] rps = getResourcePacks();
/*      */     
/*  851 */     for (int i = rps.length - 1; i >= 0; i--) {
/*      */       
/*  853 */       IResourcePack rp = rps[i];
/*      */       
/*  855 */       if (rp.resourceExists(loc))
/*      */       {
/*  857 */         return rp;
/*      */       }
/*      */     } 
/*      */     
/*  861 */     if (getDefaultResourcePack().resourceExists(loc))
/*      */     {
/*  863 */       return (IResourcePack)getDefaultResourcePack();
/*      */     }
/*      */ 
/*      */     
/*  867 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public static RenderGlobal getRenderGlobal() {
/*  873 */     return (minecraft == null) ? null : minecraft.renderGlobal;
/*      */   }
/*      */ 
/*      */   
/*      */   public static boolean isBetterGrass() {
/*  878 */     return (gameSettings.ofBetterGrass != 3);
/*      */   }
/*      */ 
/*      */   
/*      */   public static boolean isBetterGrassFancy() {
/*  883 */     return (gameSettings.ofBetterGrass == 2);
/*      */   }
/*      */ 
/*      */   
/*      */   public static boolean isWeatherEnabled() {
/*  888 */     return gameSettings.ofWeather;
/*      */   }
/*      */ 
/*      */   
/*      */   public static boolean isSkyEnabled() {
/*  893 */     return gameSettings.ofSky;
/*      */   }
/*      */ 
/*      */   
/*      */   public static boolean isSunMoonEnabled() {
/*  898 */     return gameSettings.ofSunMoon;
/*      */   }
/*      */ 
/*      */   
/*      */   public static boolean isVignetteEnabled() {
/*  903 */     return (gameSettings.ofVignette == 0) ? gameSettings.fancyGraphics : ((gameSettings.ofVignette == 2));
/*      */   }
/*      */ 
/*      */   
/*      */   public static boolean isStarsEnabled() {
/*  908 */     return gameSettings.ofStars;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public static void sleep(long ms) {
/*      */     try {
/*  915 */       Thread.currentThread();
/*  916 */       Thread.sleep(ms);
/*      */     }
/*  918 */     catch (InterruptedException var3) {
/*      */       
/*  920 */       var3.printStackTrace();
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public static boolean isTimeDayOnly() {
/*  926 */     return (gameSettings.ofTime == 1);
/*      */   }
/*      */ 
/*      */   
/*      */   public static boolean isTimeDefault() {
/*  931 */     return (gameSettings.ofTime == 0);
/*      */   }
/*      */ 
/*      */   
/*      */   public static boolean isTimeNightOnly() {
/*  936 */     return (gameSettings.ofTime == 2);
/*      */   }
/*      */ 
/*      */   
/*      */   public static boolean isClearWater() {
/*  941 */     return gameSettings.ofClearWater;
/*      */   }
/*      */ 
/*      */   
/*      */   public static int getAnisotropicFilterLevel() {
/*  946 */     return gameSettings.ofAfLevel;
/*      */   }
/*      */ 
/*      */   
/*      */   public static boolean isAnisotropicFiltering() {
/*  951 */     return (getAnisotropicFilterLevel() > 1);
/*      */   }
/*      */ 
/*      */   
/*      */   public static int getAntialiasingLevel() {
/*  956 */     return antialiasingLevel;
/*      */   }
/*      */ 
/*      */   
/*      */   public static boolean isAntialiasing() {
/*  961 */     return (getAntialiasingLevel() > 0);
/*      */   }
/*      */ 
/*      */   
/*      */   public static boolean isAntialiasingConfigured() {
/*  966 */     return ((getGameSettings()).ofAaLevel > 0);
/*      */   }
/*      */ 
/*      */   
/*      */   public static boolean isMultiTexture() {
/*  971 */     return (getAnisotropicFilterLevel() > 1) ? true : ((getAntialiasingLevel() > 0));
/*      */   }
/*      */ 
/*      */   
/*      */   public static boolean between(int val, int min, int max) {
/*  976 */     return (val >= min && val <= max);
/*      */   }
/*      */ 
/*      */   
/*      */   public static boolean isDrippingWaterLava() {
/*  981 */     return gameSettings.ofDrippingWaterLava;
/*      */   }
/*      */ 
/*      */   
/*      */   public static boolean isBetterSnow() {
/*  986 */     return gameSettings.ofBetterSnow;
/*      */   }
/*      */ 
/*      */   
/*      */   public static Dimension getFullscreenDimension() {
/*  991 */     if (desktopDisplayMode == null)
/*      */     {
/*  993 */       return null;
/*      */     }
/*  995 */     if (gameSettings == null)
/*      */     {
/*  997 */       return new Dimension(desktopDisplayMode.getWidth(), desktopDisplayMode.getHeight());
/*      */     }
/*      */ 
/*      */     
/* 1001 */     String dimStr = gameSettings.ofFullscreenMode;
/*      */     
/* 1003 */     if (dimStr.equals("Default"))
/*      */     {
/* 1005 */       return new Dimension(desktopDisplayMode.getWidth(), desktopDisplayMode.getHeight());
/*      */     }
/*      */ 
/*      */     
/* 1009 */     String[] dimStrs = tokenize(dimStr, " x");
/* 1010 */     return (dimStrs.length < 2) ? new Dimension(desktopDisplayMode.getWidth(), desktopDisplayMode.getHeight()) : new Dimension(parseInt(dimStrs[0], -1), parseInt(dimStrs[1], -1));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static int parseInt(String str, int defVal) {
/*      */     try {
/* 1019 */       if (str == null)
/*      */       {
/* 1021 */         return defVal;
/*      */       }
/*      */ 
/*      */       
/* 1025 */       str = str.trim();
/* 1026 */       return Integer.parseInt(str);
/*      */     
/*      */     }
/* 1029 */     catch (NumberFormatException var3) {
/*      */       
/* 1031 */       return defVal;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public static float parseFloat(String str, float defVal) {
/*      */     try {
/* 1039 */       if (str == null)
/*      */       {
/* 1041 */         return defVal;
/*      */       }
/*      */ 
/*      */       
/* 1045 */       str = str.trim();
/* 1046 */       return Float.parseFloat(str);
/*      */     
/*      */     }
/* 1049 */     catch (NumberFormatException var3) {
/*      */       
/* 1051 */       return defVal;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean parseBoolean(String str, boolean defVal) {
/*      */     try {
/* 1059 */       if (str == null)
/*      */       {
/* 1061 */         return defVal;
/*      */       }
/*      */ 
/*      */       
/* 1065 */       str = str.trim();
/* 1066 */       return Boolean.parseBoolean(str);
/*      */     
/*      */     }
/* 1069 */     catch (NumberFormatException var3) {
/*      */       
/* 1071 */       return defVal;
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public static String[] tokenize(String str, String delim) {
/* 1077 */     StringTokenizer tok = new StringTokenizer(str, delim);
/* 1078 */     ArrayList<String> list = new ArrayList();
/*      */     
/* 1080 */     while (tok.hasMoreTokens()) {
/*      */       
/* 1082 */       String strs = tok.nextToken();
/* 1083 */       list.add(strs);
/*      */     } 
/*      */     
/* 1086 */     String[] strs1 = list.<String>toArray(new String[list.size()]);
/* 1087 */     return strs1;
/*      */   }
/*      */ 
/*      */   
/*      */   public static DisplayMode getDesktopDisplayMode() {
/* 1092 */     return desktopDisplayMode;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public static DisplayMode[] getFullscreenDisplayModes() {
/*      */     try {
/* 1099 */       DisplayMode[] e = Display.getAvailableDisplayModes();
/* 1100 */       ArrayList<DisplayMode> list = new ArrayList();
/*      */       
/* 1102 */       for (int fsModes = 0; fsModes < e.length; fsModes++) {
/*      */         
/* 1104 */         DisplayMode comp = e[fsModes];
/*      */         
/* 1106 */         if (desktopDisplayMode == null || (comp.getBitsPerPixel() == desktopDisplayMode.getBitsPerPixel() && comp.getFrequency() == desktopDisplayMode.getFrequency()))
/*      */         {
/* 1108 */           list.add(comp);
/*      */         }
/*      */       } 
/*      */       
/* 1112 */       DisplayMode[] var5 = list.<DisplayMode>toArray(new DisplayMode[list.size()]);
/* 1113 */       Comparator<? super DisplayMode> var6 = new Comparator()
/*      */         {
/*      */           public int compare(Object o1, Object o2)
/*      */           {
/* 1117 */             DisplayMode dm1 = (DisplayMode)o1;
/* 1118 */             DisplayMode dm2 = (DisplayMode)o2;
/* 1119 */             return (dm1.getWidth() != dm2.getWidth()) ? (dm2.getWidth() - dm1.getWidth()) : ((dm1.getHeight() != dm2.getHeight()) ? (dm2.getHeight() - dm1.getHeight()) : 0);
/*      */           }
/*      */         };
/* 1122 */       Arrays.sort(var5, var6);
/* 1123 */       return var5;
/*      */     }
/* 1125 */     catch (Exception var4) {
/*      */       
/* 1127 */       var4.printStackTrace();
/* 1128 */       return new DisplayMode[] { desktopDisplayMode };
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public static String[] getFullscreenModes() {
/* 1134 */     DisplayMode[] modes = getFullscreenDisplayModes();
/* 1135 */     String[] names = new String[modes.length];
/*      */     
/* 1137 */     for (int i = 0; i < modes.length; i++) {
/*      */       
/* 1139 */       DisplayMode mode = modes[i];
/* 1140 */       String name = mode.getWidth() + "x" + mode.getHeight();
/* 1141 */       names[i] = name;
/*      */     } 
/*      */     
/* 1144 */     return names;
/*      */   }
/*      */ 
/*      */   
/*      */   public static DisplayMode getDisplayMode(Dimension dim) throws LWJGLException {
/* 1149 */     DisplayMode[] modes = Display.getAvailableDisplayModes();
/*      */     
/* 1151 */     for (int i = 0; i < modes.length; i++) {
/*      */       
/* 1153 */       DisplayMode dm = modes[i];
/*      */       
/* 1155 */       if (dm.getWidth() == dim.width && dm.getHeight() == dim.height && (desktopDisplayMode == null || (dm.getBitsPerPixel() == desktopDisplayMode.getBitsPerPixel() && dm.getFrequency() == desktopDisplayMode.getFrequency())))
/*      */       {
/* 1157 */         return dm;
/*      */       }
/*      */     } 
/*      */     
/* 1161 */     return desktopDisplayMode;
/*      */   }
/*      */ 
/*      */   
/*      */   public static boolean isAnimatedTerrain() {
/* 1166 */     return gameSettings.ofAnimatedTerrain;
/*      */   }
/*      */ 
/*      */   
/*      */   public static boolean isAnimatedTextures() {
/* 1171 */     return gameSettings.ofAnimatedTextures;
/*      */   }
/*      */ 
/*      */   
/*      */   public static boolean isSwampColors() {
/* 1176 */     return gameSettings.ofSwampColors;
/*      */   }
/*      */ 
/*      */   
/*      */   public static boolean isRandomMobs() {
/* 1181 */     return gameSettings.ofRandomMobs;
/*      */   }
/*      */ 
/*      */   
/*      */   public static void checkGlError(String loc) {
/* 1186 */     int i = GL11.glGetError();
/*      */     
/* 1188 */     if (i != 0) {
/*      */       
/* 1190 */       String text = GLU.gluErrorString(i);
/* 1191 */       error("OpenGlError: " + i + " (" + text + "), at: " + loc);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public static boolean isSmoothBiomes() {
/* 1197 */     return gameSettings.ofSmoothBiomes;
/*      */   }
/*      */ 
/*      */   
/*      */   public static boolean isCustomColors() {
/* 1202 */     return gameSettings.ofCustomColors;
/*      */   }
/*      */ 
/*      */   
/*      */   public static boolean isCustomSky() {
/* 1207 */     return gameSettings.ofCustomSky;
/*      */   }
/*      */ 
/*      */   
/*      */   public static boolean isCustomFonts() {
/* 1212 */     return gameSettings.ofCustomFonts;
/*      */   }
/*      */ 
/*      */   
/*      */   public static boolean isShowCapes() {
/* 1217 */     return gameSettings.ofShowCapes;
/*      */   }
/*      */ 
/*      */   
/*      */   public static boolean isConnectedTextures() {
/* 1222 */     return (gameSettings.ofConnectedTextures != 3);
/*      */   }
/*      */ 
/*      */   
/*      */   public static boolean isNaturalTextures() {
/* 1227 */     return gameSettings.ofNaturalTextures;
/*      */   }
/*      */ 
/*      */   
/*      */   public static boolean isConnectedTexturesFancy() {
/* 1232 */     return (gameSettings.ofConnectedTextures == 2);
/*      */   }
/*      */ 
/*      */   
/*      */   public static boolean isFastRender() {
/* 1237 */     return gameSettings.ofFastRender;
/*      */   }
/*      */ 
/*      */   
/*      */   public static boolean isTranslucentBlocksFancy() {
/* 1242 */     return (gameSettings.ofTranslucentBlocks == 0) ? gameSettings.fancyGraphics : ((gameSettings.ofTranslucentBlocks == 2));
/*      */   }
/*      */ 
/*      */   
/*      */   public static boolean isShaders() {
/* 1247 */     return Shaders.shaderPackLoaded;
/*      */   }
/*      */ 
/*      */   
/*      */   public static String[] readLines(File file) throws IOException {
/* 1252 */     FileInputStream fis = new FileInputStream(file);
/* 1253 */     return readLines(fis);
/*      */   }
/*      */ 
/*      */   
/*      */   public static String[] readLines(InputStream is) throws IOException {
/* 1258 */     ArrayList<String> list = new ArrayList();
/* 1259 */     InputStreamReader isr = new InputStreamReader(is, "ASCII");
/* 1260 */     BufferedReader br = new BufferedReader(isr);
/*      */ 
/*      */     
/*      */     while (true) {
/* 1264 */       String lines = br.readLine();
/*      */       
/* 1266 */       if (lines == null) {
/*      */         
/* 1268 */         String[] lines1 = (String[])list.toArray((Object[])new String[list.size()]);
/* 1269 */         return lines1;
/*      */       } 
/*      */       
/* 1272 */       list.add(lines);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public static String readFile(File file) throws IOException {
/* 1278 */     FileInputStream fin = new FileInputStream(file);
/* 1279 */     return readInputStream(fin, "ASCII");
/*      */   }
/*      */ 
/*      */   
/*      */   public static String readInputStream(InputStream in) throws IOException {
/* 1284 */     return readInputStream(in, "ASCII");
/*      */   }
/*      */ 
/*      */   
/*      */   public static String readInputStream(InputStream in, String encoding) throws IOException {
/* 1289 */     InputStreamReader inr = new InputStreamReader(in, encoding);
/* 1290 */     BufferedReader br = new BufferedReader(inr);
/* 1291 */     StringBuffer sb = new StringBuffer();
/*      */ 
/*      */     
/*      */     while (true) {
/* 1295 */       String line = br.readLine();
/*      */       
/* 1297 */       if (line == null)
/*      */       {
/* 1299 */         return sb.toString();
/*      */       }
/*      */       
/* 1302 */       sb.append(line);
/* 1303 */       sb.append("\n");
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public static byte[] readAll(InputStream is) throws IOException {
/* 1309 */     ByteArrayOutputStream baos = new ByteArrayOutputStream();
/* 1310 */     byte[] buf = new byte[1024];
/*      */ 
/*      */     
/*      */     while (true) {
/* 1314 */       int bytes = is.read(buf);
/*      */       
/* 1316 */       if (bytes < 0) {
/*      */         
/* 1318 */         is.close();
/* 1319 */         byte[] bytes1 = baos.toByteArray();
/* 1320 */         return bytes1;
/*      */       } 
/*      */       
/* 1323 */       baos.write(buf, 0, bytes);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public static GameSettings getGameSettings() {
/* 1329 */     return gameSettings;
/*      */   }
/*      */ 
/*      */   
/*      */   public static String getNewRelease() {
/* 1334 */     return newRelease;
/*      */   }
/*      */ 
/*      */   
/*      */   public static void setNewRelease(String newRelease) {
/* 1339 */     newRelease = newRelease;
/*      */   }
/*      */ 
/*      */   
/*      */   public static int compareRelease(String rel1, String rel2) {
/* 1344 */     String[] rels1 = splitRelease(rel1);
/* 1345 */     String[] rels2 = splitRelease(rel2);
/* 1346 */     String branch1 = rels1[0];
/* 1347 */     String branch2 = rels2[0];
/*      */     
/* 1349 */     if (!branch1.equals(branch2))
/*      */     {
/* 1351 */       return branch1.compareTo(branch2);
/*      */     }
/*      */ 
/*      */     
/* 1355 */     int rev1 = parseInt(rels1[1], -1);
/* 1356 */     int rev2 = parseInt(rels2[1], -1);
/*      */     
/* 1358 */     if (rev1 != rev2)
/*      */     {
/* 1360 */       return rev1 - rev2;
/*      */     }
/*      */ 
/*      */     
/* 1364 */     String suf1 = rels1[2];
/* 1365 */     String suf2 = rels2[2];
/*      */     
/* 1367 */     if (!suf1.equals(suf2)) {
/*      */       
/* 1369 */       if (suf1.isEmpty())
/*      */       {
/* 1371 */         return 1;
/*      */       }
/*      */       
/* 1374 */       if (suf2.isEmpty())
/*      */       {
/* 1376 */         return -1;
/*      */       }
/*      */     } 
/*      */     
/* 1380 */     return suf1.compareTo(suf2);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static String[] splitRelease(String relStr) {
/* 1387 */     if (relStr != null && relStr.length() > 0) {
/*      */       
/* 1389 */       Pattern p = Pattern.compile("([A-Z])([0-9]+)(.*)");
/* 1390 */       Matcher m = p.matcher(relStr);
/*      */       
/* 1392 */       if (!m.matches())
/*      */       {
/* 1394 */         return new String[] { "", "", "" };
/*      */       }
/*      */ 
/*      */       
/* 1398 */       String branch = normalize(m.group(1));
/* 1399 */       String revision = normalize(m.group(2));
/* 1400 */       String suffix = normalize(m.group(3));
/* 1401 */       return new String[] { branch, revision, suffix };
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 1406 */     return new String[] { "", "", "" };
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public static int intHash(int x) {
/* 1412 */     x = x ^ 0x3D ^ x >> 16;
/* 1413 */     x += x << 3;
/* 1414 */     x ^= x >> 4;
/* 1415 */     x *= 668265261;
/* 1416 */     x ^= x >> 15;
/* 1417 */     return x;
/*      */   }
/*      */ 
/*      */   
/*      */   public static int getRandom(BlockPos blockPos, int face) {
/* 1422 */     int rand = intHash(face + 37);
/* 1423 */     rand = intHash(rand + blockPos.getX());
/* 1424 */     rand = intHash(rand + blockPos.getZ());
/* 1425 */     rand = intHash(rand + blockPos.getY());
/* 1426 */     return rand;
/*      */   }
/*      */ 
/*      */   
/*      */   public static WorldServer getWorldServer() {
/* 1431 */     if (minecraft == null)
/*      */     {
/* 1433 */       return null;
/*      */     }
/*      */ 
/*      */     
/* 1437 */     WorldClient world = minecraft.theWorld;
/*      */     
/* 1439 */     if (world == null)
/*      */     {
/* 1441 */       return null;
/*      */     }
/* 1443 */     if (!minecraft.isIntegratedServerRunning())
/*      */     {
/* 1445 */       return null;
/*      */     }
/*      */ 
/*      */     
/* 1449 */     IntegratedServer is = minecraft.getIntegratedServer();
/*      */     
/* 1451 */     if (is == null)
/*      */     {
/* 1453 */       return null;
/*      */     }
/*      */ 
/*      */     
/* 1457 */     WorldProvider wp = world.provider;
/*      */     
/* 1459 */     if (wp == null)
/*      */     {
/* 1461 */       return null;
/*      */     }
/*      */ 
/*      */     
/* 1465 */     int wd = wp.getDimensionId();
/*      */ 
/*      */     
/*      */     try {
/* 1469 */       WorldServer e = is.worldServerForDimension(wd);
/* 1470 */       return e;
/*      */     }
/* 1472 */     catch (NullPointerException var5) {
/*      */       
/* 1474 */       return null;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static int getAvailableProcessors() {
/* 1484 */     return availableProcessors;
/*      */   }
/*      */ 
/*      */   
/*      */   public static void updateAvailableProcessors() {
/* 1489 */     availableProcessors = Runtime.getRuntime().availableProcessors();
/*      */   }
/*      */ 
/*      */   
/*      */   public static boolean isSingleProcessor() {
/* 1494 */     return (getAvailableProcessors() <= 1);
/*      */   }
/*      */ 
/*      */   
/*      */   public static boolean isSmoothWorld() {
/* 1499 */     return gameSettings.ofSmoothWorld;
/*      */   }
/*      */ 
/*      */   
/*      */   public static boolean isLazyChunkLoading() {
/* 1504 */     return !isSingleProcessor() ? false : gameSettings.ofLazyChunkLoading;
/*      */   }
/*      */ 
/*      */   
/*      */   public static boolean isDynamicFov() {
/* 1509 */     return gameSettings.ofDynamicFov;
/*      */   }
/*      */ 
/*      */   
/*      */   public static int getChunkViewDistance() {
/* 1514 */     if (gameSettings == null)
/*      */     {
/* 1516 */       return 10;
/*      */     }
/*      */ 
/*      */     
/* 1520 */     int chunkDistance = gameSettings.renderDistanceChunks;
/* 1521 */     return chunkDistance;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean equals(Object o1, Object o2) {
/* 1527 */     return (o1 == o2) ? true : ((o1 == null) ? false : o1.equals(o2));
/*      */   }
/*      */ 
/*      */   
/*      */   public static String normalize(String s) {
/* 1532 */     return (s == null) ? "" : s;
/*      */   }
/*      */ 
/*      */   
/*      */   public static void checkDisplaySettings() {
/* 1537 */     int samples = getAntialiasingLevel();
/*      */     
/* 1539 */     if (samples > 0) {
/*      */       
/* 1541 */       DisplayMode displayMode = Display.getDisplayMode();
/* 1542 */       dbg("FSAA Samples: " + samples);
/*      */ 
/*      */       
/*      */       try {
/* 1546 */         Display.destroy();
/* 1547 */         Display.setDisplayMode(displayMode);
/* 1548 */         Display.create((new PixelFormat()).withDepthBits(24).withSamples(samples));
/* 1549 */         Display.setResizable(false);
/* 1550 */         Display.setResizable(true);
/*      */       }
/* 1552 */       catch (LWJGLException var15) {
/*      */         
/* 1554 */         warn("Error setting FSAA: " + samples + "x");
/* 1555 */         var15.printStackTrace();
/*      */ 
/*      */         
/*      */         try {
/* 1559 */           Display.setDisplayMode(displayMode);
/* 1560 */           Display.create((new PixelFormat()).withDepthBits(24));
/* 1561 */           Display.setResizable(false);
/* 1562 */           Display.setResizable(true);
/*      */         }
/* 1564 */         catch (LWJGLException var14) {
/*      */           
/* 1566 */           var14.printStackTrace();
/*      */ 
/*      */           
/*      */           try {
/* 1570 */             Display.setDisplayMode(displayMode);
/* 1571 */             Display.create();
/* 1572 */             Display.setResizable(false);
/* 1573 */             Display.setResizable(true);
/*      */           }
/* 1575 */           catch (LWJGLException var13) {
/*      */             
/* 1577 */             var13.printStackTrace();
/*      */           } 
/*      */         } 
/*      */       } 
/*      */       
/* 1582 */       if (!Minecraft.isRunningOnMac && getDefaultResourcePack() != null) {
/*      */         
/* 1584 */         InputStream var2 = null;
/* 1585 */         InputStream var3 = null;
/*      */ 
/*      */         
/*      */         try {
/* 1589 */           var2 = getDefaultResourcePack().func_152780_c(new ResourceLocation("icons/icon_16x16.png"));
/* 1590 */           var3 = getDefaultResourcePack().func_152780_c(new ResourceLocation("icons/icon_32x32.png"));
/*      */           
/* 1592 */           if (var2 != null && var3 != null)
/*      */           {
/* 1594 */             Display.setIcon(new ByteBuffer[] { readIconImage(var2), readIconImage(var3) });
/*      */           }
/*      */         }
/* 1597 */         catch (IOException var11) {
/*      */           
/* 1599 */           warn("Error setting window icon: " + var11.getClass().getName() + ": " + var11.getMessage());
/*      */         }
/*      */         finally {
/*      */           
/* 1603 */           IOUtils.closeQuietly(var2);
/* 1604 */           IOUtils.closeQuietly(var3);
/*      */         } 
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   private static ByteBuffer readIconImage(InputStream is) throws IOException {
/* 1612 */     BufferedImage var2 = ImageIO.read(is);
/* 1613 */     int[] var3 = var2.getRGB(0, 0, var2.getWidth(), var2.getHeight(), null, 0, var2.getWidth());
/* 1614 */     ByteBuffer var4 = ByteBuffer.allocate(4 * var3.length);
/* 1615 */     int[] var5 = var3;
/* 1616 */     int var6 = var3.length;
/*      */     
/* 1618 */     for (int var7 = 0; var7 < var6; var7++) {
/*      */       
/* 1620 */       int var8 = var5[var7];
/* 1621 */       var4.putInt(var8 << 8 | var8 >> 24 & 0xFF);
/*      */     } 
/*      */     
/* 1624 */     var4.flip();
/* 1625 */     return var4;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public static void checkDisplayMode() {
/*      */     try {
/* 1632 */       if (minecraft.isFullScreen())
/*      */       {
/* 1634 */         if (fullscreenModeChecked) {
/*      */           return;
/*      */         }
/*      */ 
/*      */         
/* 1639 */         fullscreenModeChecked = true;
/* 1640 */         desktopModeChecked = false;
/* 1641 */         DisplayMode e = Display.getDisplayMode();
/* 1642 */         Dimension dim = getFullscreenDimension();
/*      */         
/* 1644 */         if (dim == null) {
/*      */           return;
/*      */         }
/*      */ 
/*      */         
/* 1649 */         if (e.getWidth() == dim.width && e.getHeight() == dim.height) {
/*      */           return;
/*      */         }
/*      */ 
/*      */         
/* 1654 */         DisplayMode newMode = getDisplayMode(dim);
/*      */         
/* 1656 */         if (newMode == null) {
/*      */           return;
/*      */         }
/*      */ 
/*      */         
/* 1661 */         Display.setDisplayMode(newMode);
/* 1662 */         minecraft.displayWidth = Display.getDisplayMode().getWidth();
/* 1663 */         minecraft.displayHeight = Display.getDisplayMode().getHeight();
/*      */         
/* 1665 */         if (minecraft.displayWidth <= 0)
/*      */         {
/* 1667 */           minecraft.displayWidth = 1;
/*      */         }
/*      */         
/* 1670 */         if (minecraft.displayHeight <= 0)
/*      */         {
/* 1672 */           minecraft.displayHeight = 1;
/*      */         }
/*      */         
/* 1675 */         if (minecraft.currentScreen != null) {
/*      */           
/* 1677 */           ScaledResolution sr = new ScaledResolution(minecraft, minecraft.displayWidth, minecraft.displayHeight);
/* 1678 */           int sw = sr.getScaledWidth();
/* 1679 */           int sh = sr.getScaledHeight();
/* 1680 */           minecraft.currentScreen.setWorldAndResolution(minecraft, sw, sh);
/*      */         } 
/*      */         
/* 1683 */         minecraft.loadingScreen = new LoadingScreenRenderer(minecraft);
/* 1684 */         updateFramebufferSize();
/* 1685 */         Display.setFullscreen(true);
/* 1686 */         minecraft.gameSettings.updateVSync();
/* 1687 */         GlStateManager.func_179098_w();
/*      */       }
/*      */       else
/*      */       {
/* 1691 */         if (desktopModeChecked) {
/*      */           return;
/*      */         }
/*      */ 
/*      */         
/* 1696 */         desktopModeChecked = true;
/* 1697 */         fullscreenModeChecked = false;
/* 1698 */         minecraft.gameSettings.updateVSync();
/* 1699 */         Display.update();
/* 1700 */         GlStateManager.func_179098_w();
/* 1701 */         Display.setResizable(false);
/* 1702 */         Display.setResizable(true);
/*      */       }
/*      */     
/* 1705 */     } catch (Exception var6) {
/*      */       
/* 1707 */       var6.printStackTrace();
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public static void updateFramebufferSize() {
/* 1713 */     minecraft.getFramebuffer().createBindFramebuffer(minecraft.displayWidth, minecraft.displayHeight);
/*      */     
/* 1715 */     if (minecraft.entityRenderer != null)
/*      */     {
/* 1717 */       minecraft.entityRenderer.updateShaderGroupSize(minecraft.displayWidth, minecraft.displayHeight);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public static Object[] addObjectToArray(Object[] arr, Object obj) {
/* 1723 */     if (arr == null)
/*      */     {
/* 1725 */       throw new NullPointerException("The given array is NULL");
/*      */     }
/*      */ 
/*      */     
/* 1729 */     int arrLen = arr.length;
/* 1730 */     int newLen = arrLen + 1;
/* 1731 */     Object[] newArr = (Object[])Array.newInstance(arr.getClass().getComponentType(), newLen);
/* 1732 */     System.arraycopy(arr, 0, newArr, 0, arrLen);
/* 1733 */     newArr[arrLen] = obj;
/* 1734 */     return newArr;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public static Object[] addObjectToArray(Object[] arr, Object obj, int index) {
/* 1740 */     ArrayList<Object> list = new ArrayList(Arrays.asList(arr));
/* 1741 */     list.add(index, obj);
/* 1742 */     Object[] newArr = (Object[])Array.newInstance(arr.getClass().getComponentType(), list.size());
/* 1743 */     return list.toArray(newArr);
/*      */   }
/*      */ 
/*      */   
/*      */   public static Object[] addObjectsToArray(Object[] arr, Object[] objs) {
/* 1748 */     if (arr == null)
/*      */     {
/* 1750 */       throw new NullPointerException("The given array is NULL");
/*      */     }
/* 1752 */     if (objs.length == 0)
/*      */     {
/* 1754 */       return arr;
/*      */     }
/*      */ 
/*      */     
/* 1758 */     int arrLen = arr.length;
/* 1759 */     int newLen = arrLen + objs.length;
/* 1760 */     Object[] newArr = (Object[])Array.newInstance(arr.getClass().getComponentType(), newLen);
/* 1761 */     System.arraycopy(arr, 0, newArr, 0, arrLen);
/* 1762 */     System.arraycopy(objs, 0, newArr, arrLen, objs.length);
/* 1763 */     return newArr;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean isCustomItems() {
/* 1769 */     return gameSettings.ofCustomItems;
/*      */   }
/*      */ 
/*      */   
/*      */   public static void drawFps() {
/* 1774 */     Minecraft var10000 = minecraft;
/* 1775 */     int fps = Minecraft.func_175610_ah();
/* 1776 */     String updates = getUpdates(minecraft.debug);
/* 1777 */     int renderersActive = minecraft.renderGlobal.getCountActiveRenderers();
/* 1778 */     int entities = minecraft.renderGlobal.getCountEntitiesRendered();
/* 1779 */     int tileEntities = minecraft.renderGlobal.getCountTileEntitiesRendered();
/* 1780 */     String fpsStr = fps + " fps, C: " + renderersActive + ", E: " + entities + "+" + tileEntities + ", U: " + updates;
/* 1781 */     minecraft.fontRendererObj.drawString(fpsStr, 2.0D, 2.0D, -2039584);
/*      */   }
/*      */ 
/*      */   
/*      */   private static String getUpdates(String str) {
/* 1786 */     int pos1 = str.indexOf('(');
/*      */     
/* 1788 */     if (pos1 < 0)
/*      */     {
/* 1790 */       return "";
/*      */     }
/*      */ 
/*      */     
/* 1794 */     int pos2 = str.indexOf(' ', pos1);
/* 1795 */     return (pos2 < 0) ? "" : str.substring(pos1 + 1, pos2);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public static int getBitsOs() {
/* 1801 */     String progFiles86 = System.getenv("ProgramFiles(X86)");
/* 1802 */     return (progFiles86 != null) ? 64 : 32;
/*      */   }
/*      */ 
/*      */   
/*      */   public static int getBitsJre() {
/* 1807 */     String[] propNames = { "sun.arch.data.model", "com.ibm.vm.bitmode", "os.arch" };
/*      */     
/* 1809 */     for (int i = 0; i < propNames.length; i++) {
/*      */       
/* 1811 */       String propName = propNames[i];
/* 1812 */       String propVal = System.getProperty(propName);
/*      */       
/* 1814 */       if (propVal != null && propVal.contains("64"))
/*      */       {
/* 1816 */         return 64;
/*      */       }
/*      */     } 
/*      */     
/* 1820 */     return 32;
/*      */   }
/*      */ 
/*      */   
/*      */   public static boolean isNotify64BitJava() {
/* 1825 */     return notify64BitJava;
/*      */   }
/*      */ 
/*      */   
/*      */   public static void setNotify64BitJava(boolean flag) {
/* 1830 */     notify64BitJava = flag;
/*      */   }
/*      */ 
/*      */   
/*      */   public static boolean isConnectedModels() {
/* 1835 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   public static String fillLeft(String s, int len, char fillChar) {
/* 1840 */     if (s == null)
/*      */     {
/* 1842 */       s = "";
/*      */     }
/*      */     
/* 1845 */     if (s.length() >= len)
/*      */     {
/* 1847 */       return s;
/*      */     }
/*      */ 
/*      */     
/* 1851 */     StringBuffer buf = new StringBuffer(s);
/*      */     
/* 1853 */     while (buf.length() < len - s.length())
/*      */     {
/* 1855 */       buf.append(fillChar);
/*      */     }
/*      */     
/* 1858 */     return String.valueOf(buf.toString()) + s;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public static String fillRight(String s, int len, char fillChar) {
/* 1864 */     if (s == null)
/*      */     {
/* 1866 */       s = "";
/*      */     }
/*      */     
/* 1869 */     if (s.length() >= len)
/*      */     {
/* 1871 */       return s;
/*      */     }
/*      */ 
/*      */     
/* 1875 */     StringBuffer buf = new StringBuffer(s);
/*      */     
/* 1877 */     while (buf.length() < len)
/*      */     {
/* 1879 */       buf.append(fillChar);
/*      */     }
/*      */     
/* 1882 */     return buf.toString();
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public static void showGuiMessage(String line1, String line2) {
/* 1888 */     GuiMessage gui = new GuiMessage(minecraft.currentScreen, line1, line2);
/* 1889 */     minecraft.displayGuiScreen(gui);
/*      */   }
/*      */ 
/*      */   
/*      */   public static int[] addIntToArray(int[] intArray, int intValue) {
/* 1894 */     return addIntsToArray(intArray, new int[] { intValue });
/*      */   }
/*      */ 
/*      */   
/*      */   public static int[] addIntsToArray(int[] intArray, int[] copyFrom) {
/* 1899 */     if (intArray != null && copyFrom != null) {
/*      */       
/* 1901 */       int arrLen = intArray.length;
/* 1902 */       int newLen = arrLen + copyFrom.length;
/* 1903 */       int[] newArray = new int[newLen];
/* 1904 */       System.arraycopy(intArray, 0, newArray, 0, arrLen);
/*      */       
/* 1906 */       for (int index = 0; index < copyFrom.length; index++)
/*      */       {
/* 1908 */         newArray[index + arrLen] = copyFrom[index];
/*      */       }
/*      */       
/* 1911 */       return newArray;
/*      */     } 
/*      */ 
/*      */     
/* 1915 */     throw new NullPointerException("The given array is NULL");
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static DynamicTexture getMojangLogoTexture(DynamicTexture texDefault) {
/*      */     try {
/* 1923 */       ResourceLocation e = new ResourceLocation("textures/gui/title/mojang.png");
/* 1924 */       InputStream in = getResourceStream(e);
/*      */       
/* 1926 */       if (in == null)
/*      */       {
/* 1928 */         return texDefault;
/*      */       }
/*      */ 
/*      */       
/* 1932 */       BufferedImage bi = ImageIO.read(in);
/*      */       
/* 1934 */       if (bi == null)
/*      */       {
/* 1936 */         return texDefault;
/*      */       }
/*      */ 
/*      */       
/* 1940 */       DynamicTexture dt = new DynamicTexture(bi);
/* 1941 */       return dt;
/*      */ 
/*      */     
/*      */     }
/* 1945 */     catch (Exception var5) {
/*      */       
/* 1947 */       warn(String.valueOf(var5.getClass().getName()) + ": " + var5.getMessage());
/* 1948 */       return texDefault;
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public static void writeFile(File file, String str) throws IOException {
/* 1954 */     FileOutputStream fos = new FileOutputStream(file);
/* 1955 */     byte[] bytes = str.getBytes("ASCII");
/* 1956 */     fos.write(bytes);
/* 1957 */     fos.close();
/*      */   }
/*      */ 
/*      */   
/*      */   public static TextureMap getTextureMap() {
/* 1962 */     return getMinecraft().getTextureMapBlocks();
/*      */   }
/*      */ 
/*      */   
/*      */   public static boolean isDynamicLights() {
/* 1967 */     return (gameSettings.ofDynamicLights != 3);
/*      */   }
/*      */ 
/*      */   
/*      */   public static boolean isDynamicLightsFast() {
/* 1972 */     return (gameSettings.ofDynamicLights == 1);
/*      */   }
/*      */ 
/*      */   
/*      */   public static boolean isDynamicHandLight() {
/* 1977 */     return !isDynamicLights() ? false : (isShaders() ? Shaders.isDynamicHandLight() : true);
/*      */   }
/*      */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\optifine\Config.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */