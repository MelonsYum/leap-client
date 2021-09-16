/*     */ package optifine;
/*     */ 
/*     */ import java.awt.Dimension;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.RenderingHints;
/*     */ import java.awt.image.BufferedImage;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.nio.IntBuffer;
/*     */ import java.util.Iterator;
/*     */ import javax.imageio.ImageIO;
/*     */ import javax.imageio.ImageReader;
/*     */ import javax.imageio.stream.ImageInputStream;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.renderer.GLAllocation;
/*     */ import net.minecraft.client.renderer.GlStateManager;
/*     */ import net.minecraft.client.renderer.texture.ITextureObject;
/*     */ import net.minecraft.client.renderer.texture.ITickableTextureObject;
/*     */ import net.minecraft.client.renderer.texture.SimpleTexture;
/*     */ import net.minecraft.client.renderer.texture.TextureAtlasSprite;
/*     */ import net.minecraft.client.renderer.texture.TextureMap;
/*     */ import net.minecraft.client.resources.IReloadableResourceManager;
/*     */ import net.minecraft.client.resources.IResourceManager;
/*     */ import net.minecraft.client.resources.IResourceManagerReloadListener;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ import org.lwjgl.opengl.GLContext;
/*     */ import shadersmod.client.MultiTexID;
/*     */ import shadersmod.client.Shaders;
/*     */ 
/*     */ 
/*     */ public class TextureUtils
/*     */ {
/*     */   public static final String texGrassTop = "grass_top";
/*     */   public static final String texStone = "stone";
/*     */   public static final String texDirt = "dirt";
/*     */   public static final String texCoarseDirt = "coarse_dirt";
/*     */   public static final String texGrassSide = "grass_side";
/*     */   public static final String texStoneslabSide = "stone_slab_side";
/*     */   public static final String texStoneslabTop = "stone_slab_top";
/*     */   public static final String texBedrock = "bedrock";
/*     */   public static final String texSand = "sand";
/*     */   public static final String texGravel = "gravel";
/*     */   public static final String texLogOak = "log_oak";
/*     */   public static final String texLogBigOak = "log_big_oak";
/*     */   public static final String texLogAcacia = "log_acacia";
/*     */   public static final String texLogSpruce = "log_spruce";
/*     */   public static final String texLogBirch = "log_birch";
/*     */   public static final String texLogJungle = "log_jungle";
/*     */   public static final String texLogOakTop = "log_oak_top";
/*     */   public static final String texLogBigOakTop = "log_big_oak_top";
/*     */   public static final String texLogAcaciaTop = "log_acacia_top";
/*     */   public static final String texLogSpruceTop = "log_spruce_top";
/*     */   public static final String texLogBirchTop = "log_birch_top";
/*     */   public static final String texLogJungleTop = "log_jungle_top";
/*     */   public static final String texLeavesOak = "leaves_oak";
/*     */   public static final String texLeavesBigOak = "leaves_big_oak";
/*     */   public static final String texLeavesAcacia = "leaves_acacia";
/*     */   public static final String texLeavesBirch = "leaves_birch";
/*     */   public static final String texLeavesSpuce = "leaves_spruce";
/*     */   public static final String texLeavesJungle = "leaves_jungle";
/*     */   public static final String texGoldOre = "gold_ore";
/*     */   public static final String texIronOre = "iron_ore";
/*     */   public static final String texCoalOre = "coal_ore";
/*     */   public static final String texObsidian = "obsidian";
/*     */   public static final String texGrassSideOverlay = "grass_side_overlay";
/*     */   public static final String texSnow = "snow";
/*     */   public static final String texGrassSideSnowed = "grass_side_snowed";
/*     */   public static final String texMyceliumSide = "mycelium_side";
/*     */   public static final String texMyceliumTop = "mycelium_top";
/*     */   public static final String texDiamondOre = "diamond_ore";
/*     */   public static final String texRedstoneOre = "redstone_ore";
/*     */   public static final String texLapisOre = "lapis_ore";
/*     */   public static final String texCactusSide = "cactus_side";
/*     */   public static final String texClay = "clay";
/*     */   public static final String texFarmlandWet = "farmland_wet";
/*     */   public static final String texFarmlandDry = "farmland_dry";
/*     */   public static final String texNetherrack = "netherrack";
/*     */   public static final String texSoulSand = "soul_sand";
/*     */   public static final String texGlowstone = "glowstone";
/*     */   public static final String texLeavesSpruce = "leaves_spruce";
/*     */   public static final String texLeavesSpruceOpaque = "leaves_spruce_opaque";
/*     */   public static final String texEndStone = "end_stone";
/*     */   public static final String texSandstoneTop = "sandstone_top";
/*     */   public static final String texSandstoneBottom = "sandstone_bottom";
/*     */   public static final String texRedstoneLampOff = "redstone_lamp_off";
/*     */   public static final String texRedstoneLampOn = "redstone_lamp_on";
/*     */   public static final String texWaterStill = "water_still";
/*     */   public static final String texWaterFlow = "water_flow";
/*     */   public static final String texLavaStill = "lava_still";
/*     */   public static final String texLavaFlow = "lava_flow";
/*     */   public static final String texFireLayer0 = "fire_layer_0";
/*     */   public static final String texFireLayer1 = "fire_layer_1";
/*     */   public static final String texPortal = "portal";
/*     */   public static final String texGlass = "glass";
/*     */   public static final String texGlassPaneTop = "glass_pane_top";
/*     */   public static final String texCompass = "compass";
/*     */   public static final String texClock = "clock";
/*     */   public static TextureAtlasSprite iconGrassTop;
/*     */   public static TextureAtlasSprite iconGrassSide;
/*     */   public static TextureAtlasSprite iconGrassSideOverlay;
/*     */   public static TextureAtlasSprite iconSnow;
/*     */   public static TextureAtlasSprite iconGrassSideSnowed;
/*     */   public static TextureAtlasSprite iconMyceliumSide;
/*     */   public static TextureAtlasSprite iconMyceliumTop;
/*     */   public static TextureAtlasSprite iconWaterStill;
/*     */   public static TextureAtlasSprite iconWaterFlow;
/*     */   public static TextureAtlasSprite iconLavaStill;
/*     */   public static TextureAtlasSprite iconLavaFlow;
/*     */   public static TextureAtlasSprite iconPortal;
/*     */   public static TextureAtlasSprite iconFireLayer0;
/*     */   public static TextureAtlasSprite iconFireLayer1;
/*     */   public static TextureAtlasSprite iconGlass;
/*     */   public static TextureAtlasSprite iconGlassPaneTop;
/*     */   public static TextureAtlasSprite iconCompass;
/*     */   public static TextureAtlasSprite iconClock;
/*     */   public static final String SPRITE_PREFIX_BLOCKS = "minecraft:blocks/";
/*     */   public static final String SPRITE_PREFIX_ITEMS = "minecraft:items/";
/* 120 */   private static IntBuffer staticBuffer = GLAllocation.createDirectIntBuffer(256);
/*     */ 
/*     */   
/*     */   public static void update() {
/* 124 */     TextureMap mapBlocks = getTextureMapBlocks();
/*     */     
/* 126 */     if (mapBlocks != null) {
/*     */       
/* 128 */       String prefix = "minecraft:blocks/";
/* 129 */       iconGrassTop = mapBlocks.getSpriteSafe(String.valueOf(prefix) + "grass_top");
/* 130 */       iconGrassSide = mapBlocks.getSpriteSafe(String.valueOf(prefix) + "grass_side");
/* 131 */       iconGrassSideOverlay = mapBlocks.getSpriteSafe(String.valueOf(prefix) + "grass_side_overlay");
/* 132 */       iconSnow = mapBlocks.getSpriteSafe(String.valueOf(prefix) + "snow");
/* 133 */       iconGrassSideSnowed = mapBlocks.getSpriteSafe(String.valueOf(prefix) + "grass_side_snowed");
/* 134 */       iconMyceliumSide = mapBlocks.getSpriteSafe(String.valueOf(prefix) + "mycelium_side");
/* 135 */       iconMyceliumTop = mapBlocks.getSpriteSafe(String.valueOf(prefix) + "mycelium_top");
/* 136 */       iconWaterStill = mapBlocks.getSpriteSafe(String.valueOf(prefix) + "water_still");
/* 137 */       iconWaterFlow = mapBlocks.getSpriteSafe(String.valueOf(prefix) + "water_flow");
/* 138 */       iconLavaStill = mapBlocks.getSpriteSafe(String.valueOf(prefix) + "lava_still");
/* 139 */       iconLavaFlow = mapBlocks.getSpriteSafe(String.valueOf(prefix) + "lava_flow");
/* 140 */       iconFireLayer0 = mapBlocks.getSpriteSafe(String.valueOf(prefix) + "fire_layer_0");
/* 141 */       iconFireLayer1 = mapBlocks.getSpriteSafe(String.valueOf(prefix) + "fire_layer_1");
/* 142 */       iconPortal = mapBlocks.getSpriteSafe(String.valueOf(prefix) + "portal");
/* 143 */       iconGlass = mapBlocks.getSpriteSafe(String.valueOf(prefix) + "glass");
/* 144 */       iconGlassPaneTop = mapBlocks.getSpriteSafe(String.valueOf(prefix) + "glass_pane_top");
/* 145 */       String prefixItems = "minecraft:items/";
/* 146 */       iconCompass = mapBlocks.getSpriteSafe(String.valueOf(prefixItems) + "compass");
/* 147 */       iconClock = mapBlocks.getSpriteSafe(String.valueOf(prefixItems) + "clock");
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static BufferedImage fixTextureDimensions(String name, BufferedImage bi) {
/* 153 */     if (name.startsWith("/mob/zombie") || name.startsWith("/mob/pigzombie")) {
/*     */       
/* 155 */       int width = bi.getWidth();
/* 156 */       int height = bi.getHeight();
/*     */       
/* 158 */       if (width == height * 2) {
/*     */         
/* 160 */         BufferedImage scaledImage = new BufferedImage(width, height * 2, 2);
/* 161 */         Graphics2D gr = scaledImage.createGraphics();
/* 162 */         gr.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
/* 163 */         gr.drawImage(bi, 0, 0, width, height, null);
/* 164 */         return scaledImage;
/*     */       } 
/*     */     } 
/*     */     
/* 168 */     return bi;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int ceilPowerOfTwo(int val) {
/* 175 */     for (int i = 1; i < val; i *= 2);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 180 */     return i;
/*     */   }
/*     */ 
/*     */   
/*     */   public static int getPowerOfTwo(int val) {
/* 185 */     int i = 1;
/*     */ 
/*     */     
/* 188 */     for (int po2 = 0; i < val; po2++)
/*     */     {
/* 190 */       i *= 2;
/*     */     }
/*     */     
/* 193 */     return po2;
/*     */   }
/*     */ 
/*     */   
/*     */   public static int twoToPower(int power) {
/* 198 */     int val = 1;
/*     */     
/* 200 */     for (int i = 0; i < power; i++)
/*     */     {
/* 202 */       val *= 2;
/*     */     }
/*     */     
/* 205 */     return val;
/*     */   }
/*     */ 
/*     */   
/*     */   public static ITextureObject getTexture(ResourceLocation loc) {
/* 210 */     ITextureObject tex = Config.getTextureManager().getTexture(loc);
/*     */     
/* 212 */     if (tex != null)
/*     */     {
/* 214 */       return tex;
/*     */     }
/* 216 */     if (!Config.hasResource(loc))
/*     */     {
/* 218 */       return null;
/*     */     }
/*     */ 
/*     */     
/* 222 */     SimpleTexture tex1 = new SimpleTexture(loc);
/* 223 */     Config.getTextureManager().loadTexture(loc, (ITextureObject)tex1);
/* 224 */     return (ITextureObject)tex1;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static void resourcesReloaded(IResourceManager rm) {
/* 230 */     if (getTextureMapBlocks() != null) {
/*     */       
/* 232 */       Config.dbg("*** Reloading custom textures ***");
/* 233 */       CustomSky.reset();
/* 234 */       TextureAnimations.reset();
/* 235 */       update();
/* 236 */       NaturalTextures.update();
/* 237 */       BetterGrass.update();
/* 238 */       BetterSnow.update();
/* 239 */       TextureAnimations.update();
/* 240 */       CustomColors.update();
/* 241 */       CustomSky.update();
/* 242 */       RandomMobs.resetTextures();
/* 243 */       CustomItems.updateModels();
/* 244 */       Shaders.resourcesReloaded();
/* 245 */       Lang.resourcesReloaded();
/* 246 */       Config.updateTexturePackClouds();
/* 247 */       SmartLeaves.updateLeavesModels();
/* 248 */       Config.getTextureManager().tick();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static TextureMap getTextureMapBlocks() {
/* 254 */     return Minecraft.getMinecraft().getTextureMapBlocks();
/*     */   }
/*     */ 
/*     */   
/*     */   public static void registerResourceListener() {
/* 259 */     IResourceManager rm = Config.getResourceManager();
/*     */     
/* 261 */     if (rm instanceof IReloadableResourceManager) {
/*     */       
/* 263 */       IReloadableResourceManager tto = (IReloadableResourceManager)rm;
/* 264 */       IResourceManagerReloadListener ttol = new IResourceManagerReloadListener()
/*     */         {
/*     */           public void onResourceManagerReload(IResourceManager var1)
/*     */           {
/* 268 */             TextureUtils.resourcesReloaded(var1);
/*     */           }
/*     */         };
/* 271 */       tto.registerReloadListener(ttol);
/*     */     } 
/*     */     
/* 274 */     ITickableTextureObject tto1 = new ITickableTextureObject()
/*     */       {
/*     */         public void tick()
/*     */         {
/* 278 */           TextureAnimations.updateCustomAnimations();
/*     */         }
/*     */         public void loadTexture(IResourceManager var1) throws IOException {}
/*     */         
/*     */         public int getGlTextureId() {
/* 283 */           return 0;
/*     */         }
/*     */         
/*     */         public void func_174936_b(boolean p_174936_1, boolean p_174936_2) {}
/*     */         
/*     */         public MultiTexID getMultiTexID() {
/* 289 */           return null;
/*     */         } public void func_174935_a() {}
/*     */       };
/* 292 */     ResourceLocation ttol1 = new ResourceLocation("optifine/TickableTextures");
/* 293 */     Config.getTextureManager().loadTickableTexture(ttol1, tto1);
/*     */   }
/*     */ 
/*     */   
/*     */   public static String fixResourcePath(String path, String basePath) {
/* 298 */     String strAssMc = "assets/minecraft/";
/*     */     
/* 300 */     if (path.startsWith(strAssMc)) {
/*     */       
/* 302 */       path = path.substring(strAssMc.length());
/* 303 */       return path;
/*     */     } 
/* 305 */     if (path.startsWith("./")) {
/*     */       
/* 307 */       path = path.substring(2);
/*     */       
/* 309 */       if (!basePath.endsWith("/"))
/*     */       {
/* 311 */         basePath = String.valueOf(basePath) + "/";
/*     */       }
/*     */       
/* 314 */       path = String.valueOf(basePath) + path;
/* 315 */       return path;
/*     */     } 
/*     */ 
/*     */     
/* 319 */     if (path.startsWith("/~"))
/*     */     {
/* 321 */       path = path.substring(1);
/*     */     }
/*     */     
/* 324 */     String strMcpatcher = "mcpatcher/";
/*     */     
/* 326 */     if (path.startsWith("~/")) {
/*     */       
/* 328 */       path = path.substring(2);
/* 329 */       path = String.valueOf(strMcpatcher) + path;
/* 330 */       return path;
/*     */     } 
/* 332 */     if (path.startsWith("/")) {
/*     */       
/* 334 */       path = String.valueOf(strMcpatcher) + path.substring(1);
/* 335 */       return path;
/*     */     } 
/*     */ 
/*     */     
/* 339 */     return path;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String getBasePath(String path) {
/* 346 */     int pos = path.lastIndexOf('/');
/* 347 */     return (pos < 0) ? "" : path.substring(0, pos);
/*     */   }
/*     */ 
/*     */   
/*     */   public static void applyAnisotropicLevel() {
/* 352 */     if ((GLContext.getCapabilities()).GL_EXT_texture_filter_anisotropic) {
/*     */       
/* 354 */       float maxLevel = GL11.glGetFloat(34047);
/* 355 */       float level = Config.getAnisotropicFilterLevel();
/* 356 */       level = Math.min(level, maxLevel);
/* 357 */       GL11.glTexParameterf(3553, 34046, level);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static void bindTexture(int glTexId) {
/* 363 */     GlStateManager.func_179144_i(glTexId);
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean isPowerOfTwo(int x) {
/* 368 */     int x2 = MathHelper.roundUpToPowerOfTwo(x);
/* 369 */     return (x2 == x);
/*     */   }
/*     */ 
/*     */   
/*     */   public static BufferedImage scaleToPowerOfTwo(BufferedImage bi, int minSize) {
/* 374 */     if (bi == null)
/*     */     {
/* 376 */       return bi;
/*     */     }
/*     */ 
/*     */     
/* 380 */     int w = bi.getWidth();
/* 381 */     int h = bi.getHeight();
/* 382 */     int w2 = Math.max(w, minSize);
/* 383 */     w2 = MathHelper.roundUpToPowerOfTwo(w2);
/*     */     
/* 385 */     if (w2 == w)
/*     */     {
/* 387 */       return bi;
/*     */     }
/*     */ 
/*     */     
/* 391 */     int h2 = h * w2 / w;
/* 392 */     BufferedImage bi2 = new BufferedImage(w2, h2, 2);
/* 393 */     Graphics2D g2 = bi2.createGraphics();
/* 394 */     Object method = RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR;
/*     */     
/* 396 */     if (w2 % w != 0)
/*     */     {
/* 398 */       method = RenderingHints.VALUE_INTERPOLATION_BILINEAR;
/*     */     }
/*     */     
/* 401 */     g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, method);
/* 402 */     g2.drawImage(bi, 0, 0, w2, h2, null);
/* 403 */     return bi2;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static BufferedImage scaleMinTo(BufferedImage bi, int minSize) {
/* 410 */     if (bi == null)
/*     */     {
/* 412 */       return bi;
/*     */     }
/*     */ 
/*     */     
/* 416 */     int w = bi.getWidth();
/* 417 */     int h = bi.getHeight();
/*     */     
/* 419 */     if (w >= minSize)
/*     */     {
/* 421 */       return bi;
/*     */     }
/*     */ 
/*     */     
/*     */     int w2;
/*     */     
/* 427 */     for (w2 = w; w2 < minSize; w2 *= 2);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 432 */     int h2 = h * w2 / w;
/* 433 */     BufferedImage bi2 = new BufferedImage(w2, h2, 2);
/* 434 */     Graphics2D g2 = bi2.createGraphics();
/* 435 */     Object method = RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR;
/* 436 */     g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, method);
/* 437 */     g2.drawImage(bi, 0, 0, w2, h2, null);
/* 438 */     return bi2;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Dimension getImageSize(InputStream in, String suffix) {
/* 445 */     Iterator<ImageReader> iter = ImageIO.getImageReadersBySuffix(suffix);
/*     */ 
/*     */ 
/*     */     
/* 449 */     while (iter.hasNext()) {
/*     */       Dimension var7;
/* 451 */       ImageReader reader = iter.next();
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/* 456 */         ImageInputStream e = ImageIO.createImageInputStream(in);
/* 457 */         reader.setInput(e);
/* 458 */         int width = reader.getWidth(reader.getMinIndex());
/* 459 */         int height = reader.getHeight(reader.getMinIndex());
/* 460 */         var7 = new Dimension(width, height);
/*     */       }
/* 462 */       catch (IOException var11) {
/*     */         
/*     */         continue;
/*     */       }
/*     */       finally {
/*     */         
/* 468 */         reader.dispose();
/*     */       } 
/*     */       
/* 471 */       return var7;
/*     */     } 
/*     */     
/* 474 */     return null;
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\optifine\TextureUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */