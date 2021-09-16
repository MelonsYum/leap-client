/*      */ package optifine;
/*      */ 
/*      */ import java.awt.image.BufferedImage;
/*      */ import java.io.FileNotFoundException;
/*      */ import java.io.IOException;
/*      */ import java.io.InputStream;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Arrays;
/*      */ import java.util.HashMap;
/*      */ import java.util.Iterator;
/*      */ import java.util.List;
/*      */ import java.util.Properties;
/*      */ import java.util.Random;
/*      */ import java.util.Set;
/*      */ import javax.imageio.ImageIO;
/*      */ import net.minecraft.block.Block;
/*      */ import net.minecraft.block.BlockRedstoneWire;
/*      */ import net.minecraft.block.material.MapColor;
/*      */ import net.minecraft.block.material.Material;
/*      */ import net.minecraft.block.properties.IProperty;
/*      */ import net.minecraft.block.state.BlockStateBase;
/*      */ import net.minecraft.block.state.IBlockState;
/*      */ import net.minecraft.client.Minecraft;
/*      */ import net.minecraft.client.multiplayer.WorldClient;
/*      */ import net.minecraft.client.particle.EntityFX;
/*      */ import net.minecraft.client.renderer.block.model.BakedQuad;
/*      */ import net.minecraft.entity.Entity;
/*      */ import net.minecraft.entity.EntityList;
/*      */ import net.minecraft.init.Blocks;
/*      */ import net.minecraft.item.EnumDyeColor;
/*      */ import net.minecraft.item.Item;
/*      */ import net.minecraft.item.ItemMonsterPlacer;
/*      */ import net.minecraft.item.ItemStack;
/*      */ import net.minecraft.potion.Potion;
/*      */ import net.minecraft.potion.PotionHelper;
/*      */ import net.minecraft.util.BlockPos;
/*      */ import net.minecraft.util.MathHelper;
/*      */ import net.minecraft.util.ResourceLocation;
/*      */ import net.minecraft.util.Vec3;
/*      */ import net.minecraft.world.ColorizerFoliage;
/*      */ import net.minecraft.world.IBlockAccess;
/*      */ import net.minecraft.world.World;
/*      */ import net.minecraft.world.biome.BiomeGenBase;
/*      */ import org.apache.commons.lang3.tuple.ImmutablePair;
/*      */ import org.apache.commons.lang3.tuple.Pair;
/*      */ 
/*      */ public class CustomColors
/*      */ {
/*   49 */   private static CustomColormap waterColors = null;
/*   50 */   private static CustomColormap foliagePineColors = null;
/*   51 */   private static CustomColormap foliageBirchColors = null;
/*   52 */   private static CustomColormap swampFoliageColors = null;
/*   53 */   private static CustomColormap swampGrassColors = null;
/*   54 */   private static CustomColormap[] colorsBlockColormaps = null;
/*   55 */   private static CustomColormap[][] blockColormaps = null;
/*   56 */   private static CustomColormap skyColors = null;
/*   57 */   private static CustomColorFader skyColorFader = new CustomColorFader();
/*   58 */   private static CustomColormap fogColors = null;
/*   59 */   private static CustomColorFader fogColorFader = new CustomColorFader();
/*   60 */   private static CustomColormap underwaterColors = null;
/*   61 */   private static CustomColorFader underwaterColorFader = new CustomColorFader();
/*   62 */   private static CustomColormap[] lightMapsColorsRgb = null;
/*   63 */   private static int lightmapMinDimensionId = 0;
/*   64 */   private static float[][] sunRgbs = new float[16][3];
/*   65 */   private static float[][] torchRgbs = new float[16][3];
/*   66 */   private static CustomColormap redstoneColors = null;
/*   67 */   private static CustomColormap xpOrbColors = null;
/*   68 */   private static CustomColormap stemColors = null;
/*   69 */   private static CustomColormap stemMelonColors = null;
/*   70 */   private static CustomColormap stemPumpkinColors = null;
/*   71 */   private static CustomColormap myceliumParticleColors = null;
/*      */   private static boolean useDefaultGrassFoliageColors = true;
/*   73 */   private static int particleWaterColor = -1;
/*   74 */   private static int particlePortalColor = -1;
/*   75 */   private static int lilyPadColor = -1;
/*   76 */   private static int expBarTextColor = -1;
/*   77 */   private static int bossTextColor = -1;
/*   78 */   private static int signTextColor = -1;
/*   79 */   private static Vec3 fogColorNether = null;
/*   80 */   private static Vec3 fogColorEnd = null;
/*   81 */   private static Vec3 skyColorEnd = null;
/*   82 */   private static int[] spawnEggPrimaryColors = null;
/*   83 */   private static int[] spawnEggSecondaryColors = null;
/*   84 */   private static float[][] wolfCollarColors = null;
/*   85 */   private static float[][] sheepColors = null;
/*   86 */   private static int[] textColors = null;
/*   87 */   private static int[] mapColorsOriginal = null;
/*   88 */   private static int[] potionColors = null;
/*   89 */   private static final IBlockState BLOCK_STATE_DIRT = Blocks.dirt.getDefaultState();
/*   90 */   private static final IBlockState BLOCK_STATE_WATER = Blocks.water.getDefaultState();
/*   91 */   public static Random random = new Random();
/*   92 */   private static final IColorizer COLORIZER_GRASS = new IColorizer()
/*      */     {
/*      */       public int getColor(IBlockAccess blockAccess, BlockPos blockPos)
/*      */       {
/*   96 */         BiomeGenBase biome = CustomColors.getColorBiome(blockAccess, blockPos);
/*   97 */         return (CustomColors.swampGrassColors != null && biome == BiomeGenBase.swampland) ? CustomColors.swampGrassColors.getColor(biome, blockPos) : biome.func_180627_b(blockPos);
/*      */       }
/*      */       
/*      */       public boolean isColorConstant() {
/*  101 */         return false;
/*      */       }
/*      */     };
/*  104 */   private static final IColorizer COLORIZER_FOLIAGE = new IColorizer()
/*      */     {
/*      */       public int getColor(IBlockAccess blockAccess, BlockPos blockPos)
/*      */       {
/*  108 */         BiomeGenBase biome = CustomColors.getColorBiome(blockAccess, blockPos);
/*  109 */         return (CustomColors.swampFoliageColors != null && biome == BiomeGenBase.swampland) ? CustomColors.swampFoliageColors.getColor(biome, blockPos) : biome.func_180625_c(blockPos);
/*      */       }
/*      */       
/*      */       public boolean isColorConstant() {
/*  113 */         return false;
/*      */       }
/*      */     };
/*  116 */   private static final IColorizer COLORIZER_FOLIAGE_PINE = new IColorizer()
/*      */     {
/*      */       public int getColor(IBlockAccess blockAccess, BlockPos blockPos)
/*      */       {
/*  120 */         return (CustomColors.foliagePineColors != null) ? CustomColors.foliagePineColors.getColor(blockAccess, blockPos) : ColorizerFoliage.getFoliageColorPine();
/*      */       }
/*      */       
/*      */       public boolean isColorConstant() {
/*  124 */         return (CustomColors.foliagePineColors == null);
/*      */       }
/*      */     };
/*  127 */   private static final IColorizer COLORIZER_FOLIAGE_BIRCH = new IColorizer()
/*      */     {
/*      */       public int getColor(IBlockAccess blockAccess, BlockPos blockPos)
/*      */       {
/*  131 */         return (CustomColors.foliageBirchColors != null) ? CustomColors.foliageBirchColors.getColor(blockAccess, blockPos) : ColorizerFoliage.getFoliageColorBirch();
/*      */       }
/*      */       
/*      */       public boolean isColorConstant() {
/*  135 */         return (CustomColors.foliageBirchColors == null);
/*      */       }
/*      */     };
/*  138 */   private static final IColorizer COLORIZER_WATER = new IColorizer()
/*      */     {
/*      */       public int getColor(IBlockAccess blockAccess, BlockPos blockPos)
/*      */       {
/*  142 */         BiomeGenBase biome = CustomColors.getColorBiome(blockAccess, blockPos);
/*  143 */         return (CustomColors.waterColors != null) ? CustomColors.waterColors.getColor(biome, blockPos) : (Reflector.ForgeBiomeGenBase_getWaterColorMultiplier.exists() ? Reflector.callInt(biome, Reflector.ForgeBiomeGenBase_getWaterColorMultiplier, new Object[0]) : biome.waterColorMultiplier);
/*      */       }
/*      */       
/*      */       public boolean isColorConstant() {
/*  147 */         return false;
/*      */       }
/*      */     };
/*      */ 
/*      */   
/*      */   public static void update() {
/*  153 */     waterColors = null;
/*  154 */     foliageBirchColors = null;
/*  155 */     foliagePineColors = null;
/*  156 */     swampGrassColors = null;
/*  157 */     swampFoliageColors = null;
/*  158 */     skyColors = null;
/*  159 */     fogColors = null;
/*  160 */     underwaterColors = null;
/*  161 */     redstoneColors = null;
/*  162 */     xpOrbColors = null;
/*  163 */     stemColors = null;
/*  164 */     myceliumParticleColors = null;
/*  165 */     lightMapsColorsRgb = null;
/*  166 */     particleWaterColor = -1;
/*  167 */     particlePortalColor = -1;
/*  168 */     lilyPadColor = -1;
/*  169 */     expBarTextColor = -1;
/*  170 */     bossTextColor = -1;
/*  171 */     signTextColor = -1;
/*  172 */     fogColorNether = null;
/*  173 */     fogColorEnd = null;
/*  174 */     skyColorEnd = null;
/*  175 */     colorsBlockColormaps = null;
/*  176 */     blockColormaps = null;
/*  177 */     useDefaultGrassFoliageColors = true;
/*  178 */     spawnEggPrimaryColors = null;
/*  179 */     spawnEggSecondaryColors = null;
/*  180 */     wolfCollarColors = null;
/*  181 */     sheepColors = null;
/*  182 */     textColors = null;
/*  183 */     setMapColors(mapColorsOriginal);
/*  184 */     potionColors = null;
/*  185 */     PotionHelper.clearPotionColorCache();
/*  186 */     String mcpColormap = "mcpatcher/colormap/";
/*  187 */     String[] waterPaths = { "water.png", "watercolorX.png" };
/*  188 */     waterColors = getCustomColors(mcpColormap, waterPaths, 256, 256);
/*  189 */     updateUseDefaultGrassFoliageColors();
/*      */     
/*  191 */     if (Config.isCustomColors()) {
/*      */       
/*  193 */       String[] pinePaths = { "pine.png", "pinecolor.png" };
/*  194 */       foliagePineColors = getCustomColors(mcpColormap, pinePaths, 256, 256);
/*  195 */       String[] birchPaths = { "birch.png", "birchcolor.png" };
/*  196 */       foliageBirchColors = getCustomColors(mcpColormap, birchPaths, 256, 256);
/*  197 */       String[] swampGrassPaths = { "swampgrass.png", "swampgrasscolor.png" };
/*  198 */       swampGrassColors = getCustomColors(mcpColormap, swampGrassPaths, 256, 256);
/*  199 */       String[] swampFoliagePaths = { "swampfoliage.png", "swampfoliagecolor.png" };
/*  200 */       swampFoliageColors = getCustomColors(mcpColormap, swampFoliagePaths, 256, 256);
/*  201 */       String[] sky0Paths = { "sky0.png", "skycolor0.png" };
/*  202 */       skyColors = getCustomColors(mcpColormap, sky0Paths, 256, 256);
/*  203 */       String[] fog0Paths = { "fog0.png", "fogcolor0.png" };
/*  204 */       fogColors = getCustomColors(mcpColormap, fog0Paths, 256, 256);
/*  205 */       String[] underwaterPaths = { "underwater.png", "underwatercolor.png" };
/*  206 */       underwaterColors = getCustomColors(mcpColormap, underwaterPaths, 256, 256);
/*  207 */       String[] redstonePaths = { "redstone.png", "redstonecolor.png" };
/*  208 */       redstoneColors = getCustomColors(mcpColormap, redstonePaths, 16, 1);
/*  209 */       xpOrbColors = getCustomColors(String.valueOf(mcpColormap) + "xporb.png", -1, -1);
/*  210 */       String[] stemPaths = { "stem.png", "stemcolor.png" };
/*  211 */       stemColors = getCustomColors(mcpColormap, stemPaths, 8, 1);
/*  212 */       stemPumpkinColors = getCustomColors(String.valueOf(mcpColormap) + "pumpkinstem.png", 8, 1);
/*  213 */       stemMelonColors = getCustomColors(String.valueOf(mcpColormap) + "melonstem.png", 8, 1);
/*  214 */       String[] myceliumPaths = { "myceliumparticle.png", "myceliumparticlecolor.png" };
/*  215 */       myceliumParticleColors = getCustomColors(mcpColormap, myceliumPaths, -1, -1);
/*  216 */       Pair<CustomColormap[], Integer> lightMaps = parseLightmapsRgb();
/*  217 */       lightMapsColorsRgb = (CustomColormap[])lightMaps.getLeft();
/*  218 */       lightmapMinDimensionId = ((Integer)lightMaps.getRight()).intValue();
/*  219 */       readColorProperties("mcpatcher/color.properties");
/*  220 */       blockColormaps = readBlockColormaps(new String[] { String.valueOf(mcpColormap) + "custom/", String.valueOf(mcpColormap) + "blocks/" }, colorsBlockColormaps, 256, 256);
/*  221 */       updateUseDefaultGrassFoliageColors();
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   private static Pair<CustomColormap[], Integer> parseLightmapsRgb() {
/*  227 */     String lightmapPrefix = "mcpatcher/lightmap/world";
/*  228 */     String lightmapSuffix = ".png";
/*  229 */     String[] pathsLightmap = ResUtils.collectFiles(lightmapPrefix, lightmapSuffix);
/*  230 */     HashMap<Object, Object> mapLightmaps = new HashMap<>();
/*      */ 
/*      */     
/*  233 */     for (int setDimIds = 0; setDimIds < pathsLightmap.length; setDimIds++) {
/*      */       
/*  235 */       String dimIds = pathsLightmap[setDimIds];
/*  236 */       String minDimId = StrUtils.removePrefixSuffix(dimIds, lightmapPrefix, lightmapSuffix);
/*  237 */       int j = Config.parseInt(minDimId, -2147483648);
/*      */       
/*  239 */       if (j == Integer.MIN_VALUE) {
/*      */         
/*  241 */         warn("Invalid dimension ID: " + minDimId + ", path: " + dimIds);
/*      */       }
/*      */       else {
/*      */         
/*  245 */         mapLightmaps.put(Integer.valueOf(j), dimIds);
/*      */       } 
/*      */     } 
/*      */     
/*  249 */     Set var15 = mapLightmaps.keySet();
/*  250 */     Integer[] var16 = (Integer[])var15.toArray((Object[])new Integer[var15.size()]);
/*  251 */     Arrays.sort((Object[])var16);
/*      */     
/*  253 */     if (var16.length <= 0)
/*      */     {
/*  255 */       return (Pair<CustomColormap[], Integer>)new ImmutablePair(null, Integer.valueOf(0));
/*      */     }
/*      */ 
/*      */     
/*  259 */     int var17 = var16[0].intValue();
/*  260 */     int maxDimId = var16[var16.length - 1].intValue();
/*  261 */     int countDim = maxDimId - var17 + 1;
/*  262 */     CustomColormap[] colormaps = new CustomColormap[countDim];
/*      */     
/*  264 */     for (int i = 0; i < var16.length; i++) {
/*      */       
/*  266 */       Integer dimId = var16[i];
/*  267 */       String path = (String)mapLightmaps.get(dimId);
/*  268 */       CustomColormap colors = getCustomColors(path, -1, -1);
/*      */       
/*  270 */       if (colors != null)
/*      */       {
/*  272 */         if (colors.getWidth() < 16) {
/*      */           
/*  274 */           warn("Invalid lightmap width: " + colors.getWidth() + ", path: " + path);
/*      */         }
/*      */         else {
/*      */           
/*  278 */           int lightmapIndex = dimId.intValue() - var17;
/*  279 */           colormaps[lightmapIndex] = colors;
/*      */         } 
/*      */       }
/*      */     } 
/*      */     
/*  284 */     return (Pair<CustomColormap[], Integer>)new ImmutablePair(colormaps, Integer.valueOf(var17));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static int getTextureHeight(String path, int defHeight) {
/*      */     try {
/*  292 */       InputStream e = Config.getResourceStream(new ResourceLocation(path));
/*      */       
/*  294 */       if (e == null)
/*      */       {
/*  296 */         return defHeight;
/*      */       }
/*      */ 
/*      */       
/*  300 */       BufferedImage bi = ImageIO.read(e);
/*  301 */       e.close();
/*  302 */       return (bi == null) ? defHeight : bi.getHeight();
/*      */     
/*      */     }
/*  305 */     catch (IOException var4) {
/*      */       
/*  307 */       return defHeight;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private static void readColorProperties(String fileName) {
/*      */     try {
/*  315 */       ResourceLocation e = new ResourceLocation(fileName);
/*  316 */       InputStream in = Config.getResourceStream(e);
/*      */       
/*  318 */       if (in == null) {
/*      */         return;
/*      */       }
/*      */ 
/*      */       
/*  323 */       dbg("Loading " + fileName);
/*  324 */       Properties props = new Properties();
/*  325 */       props.load(in);
/*  326 */       in.close();
/*  327 */       particleWaterColor = readColor(props, new String[] { "particle.water", "drop.water" });
/*  328 */       particlePortalColor = readColor(props, "particle.portal");
/*  329 */       lilyPadColor = readColor(props, "lilypad");
/*  330 */       expBarTextColor = readColor(props, "text.xpbar");
/*  331 */       bossTextColor = readColor(props, "text.boss");
/*  332 */       signTextColor = readColor(props, "text.sign");
/*  333 */       fogColorNether = readColorVec3(props, "fog.nether");
/*  334 */       fogColorEnd = readColorVec3(props, "fog.end");
/*  335 */       skyColorEnd = readColorVec3(props, "sky.end");
/*  336 */       colorsBlockColormaps = readCustomColormaps(props, fileName);
/*  337 */       spawnEggPrimaryColors = readSpawnEggColors(props, fileName, "egg.shell.", "Spawn egg shell");
/*  338 */       spawnEggSecondaryColors = readSpawnEggColors(props, fileName, "egg.spots.", "Spawn egg spot");
/*  339 */       wolfCollarColors = readDyeColors(props, fileName, "collar.", "Wolf collar");
/*  340 */       sheepColors = readDyeColors(props, fileName, "sheep.", "Sheep");
/*  341 */       textColors = readTextColors(props, fileName, "text.code.", "Text");
/*  342 */       int[] mapColors = readMapColors(props, fileName, "map.", "Map");
/*      */       
/*  344 */       if (mapColors != null) {
/*      */         
/*  346 */         if (mapColorsOriginal == null)
/*      */         {
/*  348 */           mapColorsOriginal = getMapColors();
/*      */         }
/*      */         
/*  351 */         setMapColors(mapColors);
/*      */       } 
/*      */       
/*  354 */       potionColors = readPotionColors(props, fileName, "potion.", "Potion");
/*      */     }
/*  356 */     catch (FileNotFoundException var5) {
/*      */       
/*      */       return;
/*      */     }
/*  360 */     catch (IOException var6) {
/*      */       
/*  362 */       var6.printStackTrace();
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   private static CustomColormap[] readCustomColormaps(Properties props, String fileName) {
/*  368 */     ArrayList<CustomColormap> list = new ArrayList();
/*  369 */     String palettePrefix = "palette.block.";
/*  370 */     HashMap<Object, Object> map = new HashMap<>();
/*  371 */     Set keys = props.keySet();
/*  372 */     Iterator<String> propNames = keys.iterator();
/*      */ 
/*      */     
/*  375 */     while (propNames.hasNext()) {
/*      */       
/*  377 */       String cms = propNames.next();
/*  378 */       String name = props.getProperty(cms);
/*      */       
/*  380 */       if (cms.startsWith(palettePrefix))
/*      */       {
/*  382 */         map.put(cms, name);
/*      */       }
/*      */     } 
/*      */     
/*  386 */     String[] var17 = (String[])map.keySet().toArray((Object[])new String[map.size()]);
/*      */     
/*  388 */     for (int var18 = 0; var18 < var17.length; var18++) {
/*      */       
/*  390 */       String name = var17[var18];
/*  391 */       String value = props.getProperty(name);
/*  392 */       dbg("Block palette: " + name + " = " + value);
/*  393 */       String path = name.substring(palettePrefix.length());
/*  394 */       String basePath = TextureUtils.getBasePath(fileName);
/*  395 */       path = TextureUtils.fixResourcePath(path, basePath);
/*  396 */       CustomColormap colors = getCustomColors(path, 256, 256);
/*      */       
/*  398 */       if (colors == null) {
/*      */         
/*  400 */         warn("Colormap not found: " + path);
/*      */       }
/*      */       else {
/*      */         
/*  404 */         ConnectedParser cp = new ConnectedParser("CustomColors");
/*  405 */         MatchBlock[] mbs = cp.parseMatchBlocks(value);
/*      */         
/*  407 */         if (mbs != null && mbs.length > 0) {
/*      */           
/*  409 */           for (int m = 0; m < mbs.length; m++) {
/*      */             
/*  411 */             MatchBlock mb = mbs[m];
/*  412 */             colors.addMatchBlock(mb);
/*      */           } 
/*      */           
/*  415 */           list.add(colors);
/*      */         }
/*      */         else {
/*      */           
/*  419 */           warn("Invalid match blocks: " + value);
/*      */         } 
/*      */       } 
/*      */     } 
/*      */     
/*  424 */     if (list.size() <= 0)
/*      */     {
/*  426 */       return null;
/*      */     }
/*      */ 
/*      */     
/*  430 */     CustomColormap[] var19 = list.<CustomColormap>toArray(new CustomColormap[list.size()]);
/*  431 */     return var19;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private static CustomColormap[][] readBlockColormaps(String[] basePaths, CustomColormap[] basePalettes, int width, int height) {
/*  437 */     String[] paths = ResUtils.collectFiles(basePaths, new String[] { ".properties" });
/*  438 */     Arrays.sort((Object[])paths);
/*  439 */     ArrayList blockList = new ArrayList();
/*      */     
/*      */     int cmArr;
/*  442 */     for (cmArr = 0; cmArr < paths.length; cmArr++) {
/*      */       
/*  444 */       String cm = paths[cmArr];
/*  445 */       dbg("Block colormap: " + cm);
/*      */ 
/*      */       
/*      */       try {
/*  449 */         ResourceLocation e = new ResourceLocation("minecraft", cm);
/*  450 */         InputStream in = Config.getResourceStream(e);
/*      */         
/*  452 */         if (in == null)
/*      */         {
/*  454 */           warn("File not found: " + cm);
/*      */         }
/*      */         else
/*      */         {
/*  458 */           Properties props = new Properties();
/*  459 */           props.load(in);
/*  460 */           CustomColormap cm1 = new CustomColormap(props, cm, width, height);
/*      */           
/*  462 */           if (cm1.isValid(cm) && cm1.isValidMatchBlocks(cm))
/*      */           {
/*  464 */             addToBlockList(cm1, blockList);
/*      */           }
/*      */         }
/*      */       
/*  468 */       } catch (FileNotFoundException var12) {
/*      */         
/*  470 */         warn("File not found: " + cm);
/*      */       }
/*  472 */       catch (Exception var13) {
/*      */         
/*  474 */         var13.printStackTrace();
/*      */       } 
/*      */     } 
/*      */     
/*  478 */     if (basePalettes != null)
/*      */     {
/*  480 */       for (cmArr = 0; cmArr < basePalettes.length; cmArr++) {
/*      */         
/*  482 */         CustomColormap var15 = basePalettes[cmArr];
/*  483 */         addToBlockList(var15, blockList);
/*      */       } 
/*      */     }
/*      */     
/*  487 */     if (blockList.size() <= 0)
/*      */     {
/*  489 */       return null;
/*      */     }
/*      */ 
/*      */     
/*  493 */     CustomColormap[][] var14 = blockListToArray(blockList);
/*  494 */     return var14;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private static void addToBlockList(CustomColormap cm, List blockList) {
/*  500 */     int[] ids = cm.getMatchBlockIds();
/*      */     
/*  502 */     if (ids != null && ids.length > 0) {
/*      */       
/*  504 */       for (int i = 0; i < ids.length; i++) {
/*      */         
/*  506 */         int blockId = ids[i];
/*      */         
/*  508 */         if (blockId < 0)
/*      */         {
/*  510 */           warn("Invalid block ID: " + blockId);
/*      */         }
/*      */         else
/*      */         {
/*  514 */           addToList(cm, blockList, blockId);
/*      */         }
/*      */       
/*      */       } 
/*      */     } else {
/*      */       
/*  520 */       warn("No match blocks: " + Config.arrayToString(ids));
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   private static void addToList(CustomColormap cm, List<List> list, int id) {
/*  526 */     while (id >= list.size())
/*      */     {
/*  528 */       list.add(null);
/*      */     }
/*      */     
/*  531 */     Object subList = list.get(id);
/*      */     
/*  533 */     if (subList == null) {
/*      */       
/*  535 */       subList = new ArrayList();
/*  536 */       list.set(id, subList);
/*      */     } 
/*      */     
/*  539 */     ((List<CustomColormap>)subList).add(cm);
/*      */   }
/*      */ 
/*      */   
/*      */   private static CustomColormap[][] blockListToArray(List<List> list) {
/*  544 */     CustomColormap[][] colArr = new CustomColormap[list.size()][];
/*      */     
/*  546 */     for (int i = 0; i < list.size(); i++) {
/*      */       
/*  548 */       List subList = list.get(i);
/*      */       
/*  550 */       if (subList != null) {
/*      */         
/*  552 */         CustomColormap[] subArr = (CustomColormap[])subList.toArray((Object[])new CustomColormap[subList.size()]);
/*  553 */         colArr[i] = subArr;
/*      */       } 
/*      */     } 
/*      */     
/*  557 */     return colArr;
/*      */   }
/*      */ 
/*      */   
/*      */   private static int readColor(Properties props, String[] names) {
/*  562 */     for (int i = 0; i < names.length; i++) {
/*      */       
/*  564 */       String name = names[i];
/*  565 */       int col = readColor(props, name);
/*      */       
/*  567 */       if (col >= 0)
/*      */       {
/*  569 */         return col;
/*      */       }
/*      */     } 
/*      */     
/*  573 */     return -1;
/*      */   }
/*      */ 
/*      */   
/*      */   private static int readColor(Properties props, String name) {
/*  578 */     String str = props.getProperty(name);
/*      */     
/*  580 */     if (str == null)
/*      */     {
/*  582 */       return -1;
/*      */     }
/*      */ 
/*      */     
/*  586 */     str = str.trim();
/*  587 */     int color = parseColor(str);
/*      */     
/*  589 */     if (color < 0) {
/*      */       
/*  591 */       warn("Invalid color: " + name + " = " + str);
/*  592 */       return color;
/*      */     } 
/*      */ 
/*      */     
/*  596 */     dbg(String.valueOf(name) + " = " + str);
/*  597 */     return color;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static int parseColor(String str) {
/*  604 */     if (str == null)
/*      */     {
/*  606 */       return -1;
/*      */     }
/*      */ 
/*      */     
/*  610 */     str = str.trim();
/*      */ 
/*      */     
/*      */     try {
/*  614 */       int e = Integer.parseInt(str, 16) & 0xFFFFFF;
/*  615 */       return e;
/*      */     }
/*  617 */     catch (NumberFormatException var2) {
/*      */       
/*  619 */       return -1;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private static Vec3 readColorVec3(Properties props, String name) {
/*  626 */     int col = readColor(props, name);
/*      */     
/*  628 */     if (col < 0)
/*      */     {
/*  630 */       return null;
/*      */     }
/*      */ 
/*      */     
/*  634 */     int red = col >> 16 & 0xFF;
/*  635 */     int green = col >> 8 & 0xFF;
/*  636 */     int blue = col & 0xFF;
/*  637 */     float redF = red / 255.0F;
/*  638 */     float greenF = green / 255.0F;
/*  639 */     float blueF = blue / 255.0F;
/*  640 */     return new Vec3(redF, greenF, blueF);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private static CustomColormap getCustomColors(String basePath, String[] paths, int width, int height) {
/*  646 */     for (int i = 0; i < paths.length; i++) {
/*      */       
/*  648 */       String path = paths[i];
/*  649 */       path = String.valueOf(basePath) + path;
/*  650 */       CustomColormap cols = getCustomColors(path, width, height);
/*      */       
/*  652 */       if (cols != null)
/*      */       {
/*  654 */         return cols;
/*      */       }
/*      */     } 
/*      */     
/*  658 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public static CustomColormap getCustomColors(String pathImage, int width, int height) {
/*      */     try {
/*  665 */       ResourceLocation e = new ResourceLocation(pathImage);
/*      */       
/*  667 */       if (!Config.hasResource(e))
/*      */       {
/*  669 */         return null;
/*      */       }
/*      */ 
/*      */       
/*  673 */       dbg("Colormap " + pathImage);
/*  674 */       Properties props = new Properties();
/*  675 */       String pathProps = StrUtils.replaceSuffix(pathImage, ".png", ".properties");
/*  676 */       ResourceLocation locProps = new ResourceLocation(pathProps);
/*      */       
/*  678 */       if (Config.hasResource(locProps)) {
/*      */         
/*  680 */         InputStream cm = Config.getResourceStream(locProps);
/*  681 */         props.load(cm);
/*  682 */         cm.close();
/*  683 */         dbg("Colormap properties: " + pathProps);
/*      */       }
/*      */       else {
/*      */         
/*  687 */         props.put("format", "vanilla");
/*  688 */         props.put("source", pathImage);
/*  689 */         pathProps = pathImage;
/*      */       } 
/*      */       
/*  692 */       CustomColormap cm1 = new CustomColormap(props, pathProps, width, height);
/*  693 */       return !cm1.isValid(pathProps) ? null : cm1;
/*      */     
/*      */     }
/*  696 */     catch (Exception var8) {
/*      */       
/*  698 */       var8.printStackTrace();
/*  699 */       return null;
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public static void updateUseDefaultGrassFoliageColors() {
/*  705 */     useDefaultGrassFoliageColors = (foliageBirchColors == null && foliagePineColors == null && swampGrassColors == null && swampFoliageColors == null && Config.isSwampColors() && Config.isSmoothBiomes());
/*      */   }
/*      */   
/*      */   public static int getColorMultiplier(BakedQuad quad, Block block, IBlockAccess blockAccess, BlockPos blockPos, RenderEnv renderEnv) {
/*      */     IColorizer colorizer1;
/*  710 */     if (blockColormaps != null) {
/*      */       
/*  712 */       IBlockState metadata = renderEnv.getBlockState();
/*      */       
/*  714 */       if (!quad.func_178212_b()) {
/*      */         
/*  716 */         if (block == Blocks.grass)
/*      */         {
/*  718 */           metadata = BLOCK_STATE_DIRT;
/*      */         }
/*      */         
/*  721 */         if (block == Blocks.redstone_wire)
/*      */         {
/*  723 */           return -1;
/*      */         }
/*      */       } 
/*      */       
/*  727 */       if (block == Blocks.double_plant && renderEnv.getMetadata() >= 8) {
/*      */         
/*  729 */         blockPos = blockPos.offsetDown();
/*  730 */         metadata = blockAccess.getBlockState(blockPos);
/*      */       } 
/*      */       
/*  733 */       CustomColormap colorizer = getBlockColormap(metadata);
/*      */       
/*  735 */       if (colorizer != null) {
/*      */         
/*  737 */         if (Config.isSmoothBiomes() && !colorizer.isColorConstant())
/*      */         {
/*  739 */           return getSmoothColorMultiplier(blockAccess, blockPos, colorizer, renderEnv.getColorizerBlockPosM());
/*      */         }
/*      */         
/*  742 */         return colorizer.getColor(blockAccess, blockPos);
/*      */       } 
/*      */     } 
/*      */     
/*  746 */     if (!quad.func_178212_b())
/*      */     {
/*  748 */       return -1;
/*      */     }
/*  750 */     if (block == Blocks.waterlily)
/*      */     {
/*  752 */       return getLilypadColorMultiplier(blockAccess, blockPos);
/*      */     }
/*  754 */     if (block == Blocks.redstone_wire)
/*      */     {
/*  756 */       return getRedstoneColor(renderEnv.getBlockState());
/*      */     }
/*  758 */     if (block instanceof net.minecraft.block.BlockStem)
/*      */     {
/*  760 */       return getStemColorMultiplier(block, blockAccess, blockPos, renderEnv);
/*      */     }
/*  762 */     if (useDefaultGrassFoliageColors)
/*      */     {
/*  764 */       return -1;
/*      */     }
/*      */ 
/*      */     
/*  768 */     int metadata1 = renderEnv.getMetadata();
/*      */ 
/*      */     
/*  771 */     if (block != Blocks.grass && block != Blocks.tallgrass && block != Blocks.double_plant) {
/*      */       
/*  773 */       if (block == Blocks.double_plant) {
/*      */         
/*  775 */         colorizer1 = COLORIZER_GRASS;
/*      */         
/*  777 */         if (metadata1 >= 8)
/*      */         {
/*  779 */           blockPos = blockPos.offsetDown();
/*      */         }
/*      */       }
/*  782 */       else if (block == Blocks.leaves) {
/*      */         
/*  784 */         switch (metadata1 & 0x3) {
/*      */           
/*      */           case 0:
/*  787 */             colorizer1 = COLORIZER_FOLIAGE;
/*      */             break;
/*      */           
/*      */           case 1:
/*  791 */             colorizer1 = COLORIZER_FOLIAGE_PINE;
/*      */             break;
/*      */           
/*      */           case 2:
/*  795 */             colorizer1 = COLORIZER_FOLIAGE_BIRCH;
/*      */             break;
/*      */           
/*      */           default:
/*  799 */             colorizer1 = COLORIZER_FOLIAGE;
/*      */             break;
/*      */         } 
/*  802 */       } else if (block == Blocks.leaves2) {
/*      */         
/*  804 */         colorizer1 = COLORIZER_FOLIAGE;
/*      */       }
/*      */       else {
/*      */         
/*  808 */         if (block != Blocks.vine)
/*      */         {
/*  810 */           return -1;
/*      */         }
/*      */         
/*  813 */         colorizer1 = COLORIZER_FOLIAGE;
/*      */       }
/*      */     
/*      */     } else {
/*      */       
/*  818 */       colorizer1 = COLORIZER_GRASS;
/*      */     } 
/*      */     
/*  821 */     return (Config.isSmoothBiomes() && !colorizer1.isColorConstant()) ? getSmoothColorMultiplier(blockAccess, blockPos, colorizer1, renderEnv.getColorizerBlockPosM()) : colorizer1.getColor(blockAccess, blockPos);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected static BiomeGenBase getColorBiome(IBlockAccess blockAccess, BlockPos blockPos) {
/*  827 */     BiomeGenBase biome = blockAccess.getBiomeGenForCoords(blockPos);
/*      */     
/*  829 */     if (biome == BiomeGenBase.swampland && !Config.isSwampColors())
/*      */     {
/*  831 */       biome = BiomeGenBase.plains;
/*      */     }
/*      */     
/*  834 */     return biome;
/*      */   }
/*      */ 
/*      */   
/*      */   private static CustomColormap getBlockColormap(IBlockState blockState) {
/*  839 */     if (blockColormaps == null)
/*      */     {
/*  841 */       return null;
/*      */     }
/*  843 */     if (!(blockState instanceof BlockStateBase))
/*      */     {
/*  845 */       return null;
/*      */     }
/*      */ 
/*      */     
/*  849 */     BlockStateBase bs = (BlockStateBase)blockState;
/*  850 */     int blockId = bs.getBlockId();
/*      */     
/*  852 */     if (blockId >= 0 && blockId < blockColormaps.length) {
/*      */       
/*  854 */       CustomColormap[] cms = blockColormaps[blockId];
/*      */       
/*  856 */       if (cms == null)
/*      */       {
/*  858 */         return null;
/*      */       }
/*      */ 
/*      */       
/*  862 */       for (int i = 0; i < cms.length; i++) {
/*      */         
/*  864 */         CustomColormap cm = cms[i];
/*      */         
/*  866 */         if (cm.matchesBlock(bs))
/*      */         {
/*  868 */           return cm;
/*      */         }
/*      */       } 
/*      */       
/*  872 */       return null;
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*  877 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static int getSmoothColorMultiplier(IBlockAccess blockAccess, BlockPos blockPos, IColorizer colorizer, BlockPosM blockPosM) {
/*  884 */     int sumRed = 0;
/*  885 */     int sumGreen = 0;
/*  886 */     int sumBlue = 0;
/*  887 */     int x = blockPos.getX();
/*  888 */     int y = blockPos.getY();
/*  889 */     int z = blockPos.getZ();
/*  890 */     BlockPosM posM = blockPosM;
/*      */ 
/*      */     
/*      */     int r;
/*      */     
/*  895 */     for (r = x - 1; r <= x + 1; r++) {
/*      */       
/*  897 */       for (int i = z - 1; i <= z + 1; i++) {
/*      */         
/*  899 */         posM.setXyz(r, y, i);
/*  900 */         int j = colorizer.getColor(blockAccess, posM);
/*  901 */         sumRed += j >> 16 & 0xFF;
/*  902 */         sumGreen += j >> 8 & 0xFF;
/*  903 */         sumBlue += j & 0xFF;
/*      */       } 
/*      */     } 
/*      */     
/*  907 */     r = sumRed / 9;
/*  908 */     int g = sumGreen / 9;
/*  909 */     int b = sumBlue / 9;
/*  910 */     return r << 16 | g << 8 | b;
/*      */   }
/*      */ 
/*      */   
/*      */   public static int getFluidColor(IBlockAccess blockAccess, IBlockState blockState, BlockPos blockPos, RenderEnv renderEnv) {
/*  915 */     Block block = blockState.getBlock();
/*  916 */     Object colorizer = getBlockColormap(blockState);
/*      */     
/*  918 */     if (colorizer == null && block.getMaterial() == Material.water)
/*      */     {
/*  920 */       colorizer = COLORIZER_WATER;
/*      */     }
/*      */     
/*  923 */     return (colorizer == null) ? block.colorMultiplier(blockAccess, blockPos) : ((Config.isSmoothBiomes() && !((IColorizer)colorizer).isColorConstant()) ? getSmoothColorMultiplier(blockAccess, blockPos, (IColorizer)colorizer, renderEnv.getColorizerBlockPosM()) : ((IColorizer)colorizer).getColor(blockAccess, blockPos));
/*      */   }
/*      */ 
/*      */   
/*      */   public static void updatePortalFX(EntityFX fx) {
/*  928 */     if (particlePortalColor >= 0) {
/*      */       
/*  930 */       int col = particlePortalColor;
/*  931 */       int red = col >> 16 & 0xFF;
/*  932 */       int green = col >> 8 & 0xFF;
/*  933 */       int blue = col & 0xFF;
/*  934 */       float redF = red / 255.0F;
/*  935 */       float greenF = green / 255.0F;
/*  936 */       float blueF = blue / 255.0F;
/*  937 */       fx.setRBGColorF(redF, greenF, blueF);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public static void updateMyceliumFX(EntityFX fx) {
/*  943 */     if (myceliumParticleColors != null) {
/*      */       
/*  945 */       int col = myceliumParticleColors.getColorRandom();
/*  946 */       int red = col >> 16 & 0xFF;
/*  947 */       int green = col >> 8 & 0xFF;
/*  948 */       int blue = col & 0xFF;
/*  949 */       float redF = red / 255.0F;
/*  950 */       float greenF = green / 255.0F;
/*  951 */       float blueF = blue / 255.0F;
/*  952 */       fx.setRBGColorF(redF, greenF, blueF);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   private static int getRedstoneColor(IBlockState blockState) {
/*  958 */     if (redstoneColors == null)
/*      */     {
/*  960 */       return -1;
/*      */     }
/*      */ 
/*      */     
/*  964 */     int level = getRedstoneLevel(blockState, 15);
/*  965 */     int col = redstoneColors.getColor(level);
/*  966 */     return col;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public static void updateReddustFX(EntityFX fx, IBlockAccess blockAccess, double x, double y, double z) {
/*  972 */     if (redstoneColors != null) {
/*      */       
/*  974 */       IBlockState state = blockAccess.getBlockState(new BlockPos(x, y, z));
/*  975 */       int level = getRedstoneLevel(state, 15);
/*  976 */       int col = redstoneColors.getColor(level);
/*  977 */       int red = col >> 16 & 0xFF;
/*  978 */       int green = col >> 8 & 0xFF;
/*  979 */       int blue = col & 0xFF;
/*  980 */       float redF = red / 255.0F;
/*  981 */       float greenF = green / 255.0F;
/*  982 */       float blueF = blue / 255.0F;
/*  983 */       fx.setRBGColorF(redF, greenF, blueF);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   private static int getRedstoneLevel(IBlockState state, int def) {
/*  989 */     Block block = state.getBlock();
/*      */     
/*  991 */     if (!(block instanceof BlockRedstoneWire))
/*      */     {
/*  993 */       return def;
/*      */     }
/*      */ 
/*      */     
/*  997 */     Comparable val = state.getValue((IProperty)BlockRedstoneWire.POWER);
/*      */     
/*  999 */     if (!(val instanceof Integer))
/*      */     {
/* 1001 */       return def;
/*      */     }
/*      */ 
/*      */     
/* 1005 */     Integer valInt = (Integer)val;
/* 1006 */     return valInt.intValue();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static int getXpOrbColor(float timer) {
/* 1013 */     if (xpOrbColors == null)
/*      */     {
/* 1015 */       return -1;
/*      */     }
/*      */ 
/*      */     
/* 1019 */     int index = (int)(((MathHelper.sin(timer) + 1.0F) * (xpOrbColors.getLength() - 1)) / 2.0D);
/* 1020 */     int col = xpOrbColors.getColor(index);
/* 1021 */     return col;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public static void updateWaterFX(EntityFX fx, IBlockAccess blockAccess, double x, double y, double z) {
/* 1027 */     if (waterColors != null || blockColormaps != null) {
/*      */       
/* 1029 */       BlockPos blockPos = new BlockPos(x, y, z);
/* 1030 */       RenderEnv renderEnv = RenderEnv.getInstance(blockAccess, BLOCK_STATE_WATER, blockPos);
/* 1031 */       int col = getFluidColor(blockAccess, BLOCK_STATE_WATER, blockPos, renderEnv);
/* 1032 */       int red = col >> 16 & 0xFF;
/* 1033 */       int green = col >> 8 & 0xFF;
/* 1034 */       int blue = col & 0xFF;
/* 1035 */       float redF = red / 255.0F;
/* 1036 */       float greenF = green / 255.0F;
/* 1037 */       float blueF = blue / 255.0F;
/*      */       
/* 1039 */       if (particleWaterColor >= 0) {
/*      */         
/* 1041 */         int redDrop = particleWaterColor >> 16 & 0xFF;
/* 1042 */         int greenDrop = particleWaterColor >> 8 & 0xFF;
/* 1043 */         int blueDrop = particleWaterColor & 0xFF;
/* 1044 */         redF *= redDrop / 255.0F;
/* 1045 */         greenF *= greenDrop / 255.0F;
/* 1046 */         blueF *= blueDrop / 255.0F;
/*      */       } 
/*      */       
/* 1049 */       fx.setRBGColorF(redF, greenF, blueF);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   private static int getLilypadColorMultiplier(IBlockAccess blockAccess, BlockPos blockPos) {
/* 1055 */     return (lilyPadColor < 0) ? Blocks.waterlily.colorMultiplier(blockAccess, blockPos) : lilyPadColor;
/*      */   }
/*      */ 
/*      */   
/*      */   private static Vec3 getFogColorNether(Vec3 col) {
/* 1060 */     return (fogColorNether == null) ? col : fogColorNether;
/*      */   }
/*      */ 
/*      */   
/*      */   private static Vec3 getFogColorEnd(Vec3 col) {
/* 1065 */     return (fogColorEnd == null) ? col : fogColorEnd;
/*      */   }
/*      */ 
/*      */   
/*      */   private static Vec3 getSkyColorEnd(Vec3 col) {
/* 1070 */     return (skyColorEnd == null) ? col : skyColorEnd;
/*      */   }
/*      */ 
/*      */   
/*      */   public static Vec3 getSkyColor(Vec3 skyColor3d, IBlockAccess blockAccess, double x, double y, double z) {
/* 1075 */     if (skyColors == null)
/*      */     {
/* 1077 */       return skyColor3d;
/*      */     }
/*      */ 
/*      */     
/* 1081 */     int col = skyColors.getColorSmooth(blockAccess, x, y, z, 3);
/* 1082 */     int red = col >> 16 & 0xFF;
/* 1083 */     int green = col >> 8 & 0xFF;
/* 1084 */     int blue = col & 0xFF;
/* 1085 */     float redF = red / 255.0F;
/* 1086 */     float greenF = green / 255.0F;
/* 1087 */     float blueF = blue / 255.0F;
/* 1088 */     float cRed = (float)skyColor3d.xCoord / 0.5F;
/* 1089 */     float cGreen = (float)skyColor3d.yCoord / 0.66275F;
/* 1090 */     float cBlue = (float)skyColor3d.zCoord;
/* 1091 */     redF *= cRed;
/* 1092 */     greenF *= cGreen;
/* 1093 */     blueF *= cBlue;
/* 1094 */     Vec3 newCol = skyColorFader.getColor(redF, greenF, blueF);
/* 1095 */     return newCol;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private static Vec3 getFogColor(Vec3 fogColor3d, IBlockAccess blockAccess, double x, double y, double z) {
/* 1101 */     if (fogColors == null)
/*      */     {
/* 1103 */       return fogColor3d;
/*      */     }
/*      */ 
/*      */     
/* 1107 */     int col = fogColors.getColorSmooth(blockAccess, x, y, z, 3);
/* 1108 */     int red = col >> 16 & 0xFF;
/* 1109 */     int green = col >> 8 & 0xFF;
/* 1110 */     int blue = col & 0xFF;
/* 1111 */     float redF = red / 255.0F;
/* 1112 */     float greenF = green / 255.0F;
/* 1113 */     float blueF = blue / 255.0F;
/* 1114 */     float cRed = (float)fogColor3d.xCoord / 0.753F;
/* 1115 */     float cGreen = (float)fogColor3d.yCoord / 0.8471F;
/* 1116 */     float cBlue = (float)fogColor3d.zCoord;
/* 1117 */     redF *= cRed;
/* 1118 */     greenF *= cGreen;
/* 1119 */     blueF *= cBlue;
/* 1120 */     Vec3 newCol = fogColorFader.getColor(redF, greenF, blueF);
/* 1121 */     return newCol;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public static Vec3 getUnderwaterColor(IBlockAccess blockAccess, double x, double y, double z) {
/* 1127 */     if (underwaterColors == null)
/*      */     {
/* 1129 */       return null;
/*      */     }
/*      */ 
/*      */     
/* 1133 */     int col = underwaterColors.getColorSmooth(blockAccess, x, y, z, 3);
/* 1134 */     int red = col >> 16 & 0xFF;
/* 1135 */     int green = col >> 8 & 0xFF;
/* 1136 */     int blue = col & 0xFF;
/* 1137 */     float redF = red / 255.0F;
/* 1138 */     float greenF = green / 255.0F;
/* 1139 */     float blueF = blue / 255.0F;
/* 1140 */     Vec3 newCol = underwaterColorFader.getColor(redF, greenF, blueF);
/* 1141 */     return newCol;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private static int getStemColorMultiplier(Block blockStem, IBlockAccess blockAccess, BlockPos blockPos, RenderEnv renderEnv) {
/* 1147 */     CustomColormap colors = stemColors;
/*      */     
/* 1149 */     if (blockStem == Blocks.pumpkin_stem && stemPumpkinColors != null)
/*      */     {
/* 1151 */       colors = stemPumpkinColors;
/*      */     }
/*      */     
/* 1154 */     if (blockStem == Blocks.melon_stem && stemMelonColors != null)
/*      */     {
/* 1156 */       colors = stemMelonColors;
/*      */     }
/*      */     
/* 1159 */     if (colors == null)
/*      */     {
/* 1161 */       return -1;
/*      */     }
/*      */ 
/*      */     
/* 1165 */     int level = renderEnv.getMetadata();
/* 1166 */     return colors.getColor(level);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean updateLightmap(World world, float torchFlickerX, int[] lmColors, boolean nightvision) {
/* 1172 */     if (world == null)
/*      */     {
/* 1174 */       return false;
/*      */     }
/* 1176 */     if (lightMapsColorsRgb == null)
/*      */     {
/* 1178 */       return false;
/*      */     }
/*      */ 
/*      */     
/* 1182 */     int dimensionId = world.provider.getDimensionId();
/* 1183 */     int lightMapIndex = dimensionId - lightmapMinDimensionId;
/*      */     
/* 1185 */     if (lightMapIndex >= 0 && lightMapIndex < lightMapsColorsRgb.length) {
/*      */       
/* 1187 */       CustomColormap lightMapRgb = lightMapsColorsRgb[lightMapIndex];
/*      */       
/* 1189 */       if (lightMapRgb == null)
/*      */       {
/* 1191 */         return false;
/*      */       }
/*      */ 
/*      */       
/* 1195 */       int height = lightMapRgb.getHeight();
/*      */       
/* 1197 */       if (nightvision && height < 64)
/*      */       {
/* 1199 */         return false;
/*      */       }
/*      */ 
/*      */       
/* 1203 */       int width = lightMapRgb.getWidth();
/*      */       
/* 1205 */       if (width < 16) {
/*      */         
/* 1207 */         warn("Invalid lightmap width: " + width + " for dimension: " + dimensionId);
/* 1208 */         lightMapsColorsRgb[lightMapIndex] = null;
/* 1209 */         return false;
/*      */       } 
/*      */ 
/*      */       
/* 1213 */       int startIndex = 0;
/*      */       
/* 1215 */       if (nightvision)
/*      */       {
/* 1217 */         startIndex = width * 16 * 2;
/*      */       }
/*      */       
/* 1220 */       float sun = 1.1666666F * (world.getSunBrightness(1.0F) - 0.2F);
/*      */       
/* 1222 */       if (world.func_175658_ac() > 0)
/*      */       {
/* 1224 */         sun = 1.0F;
/*      */       }
/*      */       
/* 1227 */       sun = Config.limitTo1(sun);
/* 1228 */       float sunX = sun * (width - 1);
/* 1229 */       float torchX = Config.limitTo1(torchFlickerX + 0.5F) * (width - 1);
/* 1230 */       float gamma = Config.limitTo1((Config.getGameSettings()).gammaSetting);
/* 1231 */       boolean hasGamma = (gamma > 1.0E-4F);
/* 1232 */       float[][] colorsRgb = lightMapRgb.getColorsRgb();
/* 1233 */       getLightMapColumn(colorsRgb, sunX, startIndex, width, sunRgbs);
/* 1234 */       getLightMapColumn(colorsRgb, torchX, startIndex + 16 * width, width, torchRgbs);
/* 1235 */       float[] rgb = new float[3];
/*      */       
/* 1237 */       for (int is = 0; is < 16; is++) {
/*      */         
/* 1239 */         for (int it = 0; it < 16; it++) {
/*      */           int r;
/*      */ 
/*      */           
/* 1243 */           for (r = 0; r < 3; r++) {
/*      */             
/* 1245 */             float g = Config.limitTo1(sunRgbs[is][r] + torchRgbs[it][r]);
/*      */             
/* 1247 */             if (hasGamma) {
/*      */               
/* 1249 */               float b = 1.0F - g;
/* 1250 */               b = 1.0F - b * b * b * b;
/* 1251 */               g = gamma * b + (1.0F - gamma) * g;
/*      */             } 
/*      */             
/* 1254 */             rgb[r] = g;
/*      */           } 
/*      */           
/* 1257 */           r = (int)(rgb[0] * 255.0F);
/* 1258 */           int var22 = (int)(rgb[1] * 255.0F);
/* 1259 */           int var23 = (int)(rgb[2] * 255.0F);
/* 1260 */           lmColors[is * 16 + it] = 0xFF000000 | r << 16 | var22 << 8 | var23;
/*      */         } 
/*      */       } 
/*      */       
/* 1264 */       return true;
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1271 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static void getLightMapColumn(float[][] origMap, float x, int offset, int width, float[][] colRgb) {
/* 1278 */     int xLow = (int)Math.floor(x);
/* 1279 */     int xHigh = (int)Math.ceil(x);
/*      */     
/* 1281 */     if (xLow == xHigh) {
/*      */       
/* 1283 */       for (int var14 = 0; var14 < 16; var14++)
/*      */       {
/* 1285 */         float[] var15 = origMap[offset + var14 * width + xLow];
/* 1286 */         float[] var16 = colRgb[var14];
/*      */         
/* 1288 */         for (int var17 = 0; var17 < 3; var17++)
/*      */         {
/* 1290 */           var16[var17] = var15[var17];
/*      */         }
/*      */       }
/*      */     
/*      */     } else {
/*      */       
/* 1296 */       float dLow = 1.0F - x - xLow;
/* 1297 */       float dHigh = 1.0F - xHigh - x;
/*      */       
/* 1299 */       for (int y = 0; y < 16; y++) {
/*      */         
/* 1301 */         float[] rgbLow = origMap[offset + y * width + xLow];
/* 1302 */         float[] rgbHigh = origMap[offset + y * width + xHigh];
/* 1303 */         float[] rgb = colRgb[y];
/*      */         
/* 1305 */         for (int i = 0; i < 3; i++)
/*      */         {
/* 1307 */           rgb[i] = rgbLow[i] * dLow + rgbHigh[i] * dHigh;
/*      */         }
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   public static Vec3 getWorldFogColor(Vec3 fogVec, WorldClient world, Entity renderViewEntity, float partialTicks) {
/*      */     Minecraft mc;
/* 1315 */     int worldType = world.provider.getDimensionId();
/*      */     
/* 1317 */     switch (worldType) {
/*      */       
/*      */       case -1:
/* 1320 */         fogVec = getFogColorNether(fogVec);
/*      */         break;
/*      */       
/*      */       case 0:
/* 1324 */         mc = Minecraft.getMinecraft();
/* 1325 */         fogVec = getFogColor(fogVec, (IBlockAccess)mc.theWorld, renderViewEntity.posX, renderViewEntity.posY + 1.0D, renderViewEntity.posZ);
/*      */         break;
/*      */       
/*      */       case 1:
/* 1329 */         fogVec = getFogColorEnd(fogVec);
/*      */         break;
/*      */     } 
/* 1332 */     return fogVec;
/*      */   }
/*      */   
/*      */   public static Vec3 getWorldSkyColor(Vec3 skyVec, WorldClient world, Entity renderViewEntity, float partialTicks) {
/*      */     Minecraft mc;
/* 1337 */     int worldType = world.provider.getDimensionId();
/*      */     
/* 1339 */     switch (worldType) {
/*      */       
/*      */       case 0:
/* 1342 */         mc = Minecraft.getMinecraft();
/* 1343 */         skyVec = getSkyColor(skyVec, (IBlockAccess)mc.theWorld, renderViewEntity.posX, renderViewEntity.posY + 1.0D, renderViewEntity.posZ);
/*      */         break;
/*      */       
/*      */       case 1:
/* 1347 */         skyVec = getSkyColorEnd(skyVec);
/*      */         break;
/*      */     } 
/* 1350 */     return skyVec;
/*      */   }
/*      */ 
/*      */   
/*      */   private static int[] readSpawnEggColors(Properties props, String fileName, String prefix, String logName) {
/* 1355 */     ArrayList<Integer> list = new ArrayList();
/* 1356 */     Set keys = props.keySet();
/* 1357 */     int countColors = 0;
/* 1358 */     Iterator<String> colors = keys.iterator();
/*      */     
/* 1360 */     while (colors.hasNext()) {
/*      */       
/* 1362 */       String i = colors.next();
/* 1363 */       String value = props.getProperty(i);
/*      */       
/* 1365 */       if (i.startsWith(prefix)) {
/*      */         
/* 1367 */         String name = StrUtils.removePrefix(i, prefix);
/* 1368 */         int id = getEntityId(name);
/* 1369 */         int color = parseColor(value);
/*      */         
/* 1371 */         if (id >= 0 && color >= 0) {
/*      */           
/* 1373 */           while (list.size() <= id)
/*      */           {
/* 1375 */             list.add(Integer.valueOf(-1));
/*      */           }
/*      */           
/* 1378 */           list.set(id, Integer.valueOf(color));
/* 1379 */           countColors++;
/*      */           
/*      */           continue;
/*      */         } 
/* 1383 */         warn("Invalid spawn egg color: " + i + " = " + value);
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/* 1388 */     if (countColors <= 0)
/*      */     {
/* 1390 */       return null;
/*      */     }
/*      */ 
/*      */     
/* 1394 */     dbg(String.valueOf(logName) + " colors: " + countColors);
/* 1395 */     int[] var13 = new int[list.size()];
/*      */     
/* 1397 */     for (int var14 = 0; var14 < var13.length; var14++)
/*      */     {
/* 1399 */       var13[var14] = ((Integer)list.get(var14)).intValue();
/*      */     }
/*      */     
/* 1402 */     return var13;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private static int getSpawnEggColor(ItemMonsterPlacer item, ItemStack itemStack, int layer, int color) {
/* 1408 */     int id = itemStack.getMetadata();
/* 1409 */     int[] eggColors = (layer == 0) ? spawnEggPrimaryColors : spawnEggSecondaryColors;
/*      */     
/* 1411 */     if (eggColors == null)
/*      */     {
/* 1413 */       return color;
/*      */     }
/* 1415 */     if (id >= 0 && id < eggColors.length) {
/*      */       
/* 1417 */       int eggColor = eggColors[id];
/* 1418 */       return (eggColor < 0) ? color : eggColor;
/*      */     } 
/*      */ 
/*      */     
/* 1422 */     return color;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public static int getColorFromItemStack(ItemStack itemStack, int layer, int color) {
/* 1428 */     if (itemStack == null)
/*      */     {
/* 1430 */       return color;
/*      */     }
/*      */ 
/*      */     
/* 1434 */     Item item = itemStack.getItem();
/* 1435 */     return (item == null) ? color : ((item instanceof ItemMonsterPlacer) ? getSpawnEggColor((ItemMonsterPlacer)item, itemStack, layer, color) : color);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private static float[][] readDyeColors(Properties props, String fileName, String prefix, String logName) {
/* 1441 */     EnumDyeColor[] dyeValues = EnumDyeColor.values();
/* 1442 */     HashMap<Object, Object> mapDyes = new HashMap<>();
/*      */     
/* 1444 */     for (int colors = 0; colors < dyeValues.length; colors++) {
/*      */       
/* 1446 */       EnumDyeColor countColors = dyeValues[colors];
/* 1447 */       mapDyes.put(countColors.getName(), countColors);
/*      */     } 
/*      */     
/* 1450 */     float[][] var16 = new float[dyeValues.length][];
/* 1451 */     int var17 = 0;
/* 1452 */     Set keys = props.keySet();
/* 1453 */     Iterator<String> iter = keys.iterator();
/*      */     
/* 1455 */     while (iter.hasNext()) {
/*      */       
/* 1457 */       String key = iter.next();
/* 1458 */       String value = props.getProperty(key);
/*      */       
/* 1460 */       if (key.startsWith(prefix)) {
/*      */         
/* 1462 */         String name = StrUtils.removePrefix(key, prefix);
/*      */         
/* 1464 */         if (name.equals("lightBlue"))
/*      */         {
/* 1466 */           name = "light_blue";
/*      */         }
/*      */         
/* 1469 */         EnumDyeColor dye = (EnumDyeColor)mapDyes.get(name);
/* 1470 */         int color = parseColor(value);
/*      */         
/* 1472 */         if (dye != null && color >= 0) {
/*      */           
/* 1474 */           float[] rgb = { (color >> 16 & 0xFF) / 255.0F, (color >> 8 & 0xFF) / 255.0F, (color & 0xFF) / 255.0F };
/* 1475 */           var16[dye.ordinal()] = rgb;
/* 1476 */           var17++;
/*      */           
/*      */           continue;
/*      */         } 
/* 1480 */         warn("Invalid color: " + key + " = " + value);
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/* 1485 */     if (var17 <= 0)
/*      */     {
/* 1487 */       return null;
/*      */     }
/*      */ 
/*      */     
/* 1491 */     dbg(String.valueOf(logName) + " colors: " + var17);
/* 1492 */     return var16;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private static float[] getDyeColors(EnumDyeColor dye, float[][] dyeColors, float[] colors) {
/* 1498 */     if (dyeColors == null)
/*      */     {
/* 1500 */       return colors;
/*      */     }
/* 1502 */     if (dye == null)
/*      */     {
/* 1504 */       return colors;
/*      */     }
/*      */ 
/*      */     
/* 1508 */     float[] customColors = dyeColors[dye.ordinal()];
/* 1509 */     return (customColors == null) ? colors : customColors;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public static float[] getWolfCollarColors(EnumDyeColor dye, float[] colors) {
/* 1515 */     return getDyeColors(dye, wolfCollarColors, colors);
/*      */   }
/*      */ 
/*      */   
/*      */   public static float[] getSheepColors(EnumDyeColor dye, float[] colors) {
/* 1520 */     return getDyeColors(dye, sheepColors, colors);
/*      */   }
/*      */ 
/*      */   
/*      */   private static int[] readTextColors(Properties props, String fileName, String prefix, String logName) {
/* 1525 */     int[] colors = new int[32];
/* 1526 */     Arrays.fill(colors, -1);
/* 1527 */     int countColors = 0;
/* 1528 */     Set keys = props.keySet();
/* 1529 */     Iterator<String> iter = keys.iterator();
/*      */     
/* 1531 */     while (iter.hasNext()) {
/*      */       
/* 1533 */       String key = iter.next();
/* 1534 */       String value = props.getProperty(key);
/*      */       
/* 1536 */       if (key.startsWith(prefix)) {
/*      */         
/* 1538 */         String name = StrUtils.removePrefix(key, prefix);
/* 1539 */         int code = Config.parseInt(name, -1);
/* 1540 */         int color = parseColor(value);
/*      */         
/* 1542 */         if (code >= 0 && code < colors.length && color >= 0) {
/*      */           
/* 1544 */           colors[code] = color;
/* 1545 */           countColors++;
/*      */           
/*      */           continue;
/*      */         } 
/* 1549 */         warn("Invalid color: " + key + " = " + value);
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/* 1554 */     if (countColors <= 0)
/*      */     {
/* 1556 */       return null;
/*      */     }
/*      */ 
/*      */     
/* 1560 */     dbg(String.valueOf(logName) + " colors: " + countColors);
/* 1561 */     return colors;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public static int getTextColor(int index, int color) {
/* 1567 */     if (textColors == null)
/*      */     {
/* 1569 */       return color;
/*      */     }
/* 1571 */     if (index >= 0 && index < textColors.length) {
/*      */       
/* 1573 */       int customColor = textColors[index];
/* 1574 */       return (customColor < 0) ? color : customColor;
/*      */     } 
/*      */ 
/*      */     
/* 1578 */     return color;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private static int[] readMapColors(Properties props, String fileName, String prefix, String logName) {
/* 1584 */     int[] colors = new int[MapColor.mapColorArray.length];
/* 1585 */     Arrays.fill(colors, -1);
/* 1586 */     int countColors = 0;
/* 1587 */     Set keys = props.keySet();
/* 1588 */     Iterator<String> iter = keys.iterator();
/*      */     
/* 1590 */     while (iter.hasNext()) {
/*      */       
/* 1592 */       String key = iter.next();
/* 1593 */       String value = props.getProperty(key);
/*      */       
/* 1595 */       if (key.startsWith(prefix)) {
/*      */         
/* 1597 */         String name = StrUtils.removePrefix(key, prefix);
/* 1598 */         int index = getMapColorIndex(name);
/* 1599 */         int color = parseColor(value);
/*      */         
/* 1601 */         if (index >= 0 && index < colors.length && color >= 0) {
/*      */           
/* 1603 */           colors[index] = color;
/* 1604 */           countColors++;
/*      */           
/*      */           continue;
/*      */         } 
/* 1608 */         warn("Invalid color: " + key + " = " + value);
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/* 1613 */     if (countColors <= 0)
/*      */     {
/* 1615 */       return null;
/*      */     }
/*      */ 
/*      */     
/* 1619 */     dbg(String.valueOf(logName) + " colors: " + countColors);
/* 1620 */     return colors;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private static int[] readPotionColors(Properties props, String fileName, String prefix, String logName) {
/* 1626 */     int[] colors = new int[Potion.potionTypes.length];
/* 1627 */     Arrays.fill(colors, -1);
/* 1628 */     int countColors = 0;
/* 1629 */     Set keys = props.keySet();
/* 1630 */     Iterator<String> iter = keys.iterator();
/*      */     
/* 1632 */     while (iter.hasNext()) {
/*      */       
/* 1634 */       String key = iter.next();
/* 1635 */       String value = props.getProperty(key);
/*      */       
/* 1637 */       if (key.startsWith(prefix)) {
/*      */         
/* 1639 */         int index = getPotionId(key);
/* 1640 */         int color = parseColor(value);
/*      */         
/* 1642 */         if (index >= 0 && index < colors.length && color >= 0) {
/*      */           
/* 1644 */           colors[index] = color;
/* 1645 */           countColors++;
/*      */           
/*      */           continue;
/*      */         } 
/* 1649 */         warn("Invalid color: " + key + " = " + value);
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/* 1654 */     if (countColors <= 0)
/*      */     {
/* 1656 */       return null;
/*      */     }
/*      */ 
/*      */     
/* 1660 */     dbg(String.valueOf(logName) + " colors: " + countColors);
/* 1661 */     return colors;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private static int getPotionId(String name) {
/* 1667 */     if (name.equals("potion.water"))
/*      */     {
/* 1669 */       return 0;
/*      */     }
/*      */ 
/*      */     
/* 1673 */     Potion[] potions = Potion.potionTypes;
/*      */     
/* 1675 */     for (int i = 0; i < potions.length; i++) {
/*      */       
/* 1677 */       Potion potion = potions[i];
/*      */       
/* 1679 */       if (potion != null && potion.getName().equals(name))
/*      */       {
/* 1681 */         return potion.getId();
/*      */       }
/*      */     } 
/*      */     
/* 1685 */     return -1;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public static int getPotionColor(int potionId, int color) {
/* 1691 */     if (potionColors == null)
/*      */     {
/* 1693 */       return color;
/*      */     }
/* 1695 */     if (potionId >= 0 && potionId < potionColors.length) {
/*      */       
/* 1697 */       int potionColor = potionColors[potionId];
/* 1698 */       return (potionColor < 0) ? color : potionColor;
/*      */     } 
/*      */ 
/*      */     
/* 1702 */     return color;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private static int getMapColorIndex(String name) {
/* 1708 */     return (name == null) ? -1 : (name.equals("air") ? MapColor.airColor.colorIndex : (name.equals("grass") ? MapColor.grassColor.colorIndex : (name.equals("sand") ? MapColor.sandColor.colorIndex : (name.equals("cloth") ? MapColor.clothColor.colorIndex : (name.equals("tnt") ? MapColor.tntColor.colorIndex : (name.equals("ice") ? MapColor.iceColor.colorIndex : (name.equals("iron") ? MapColor.ironColor.colorIndex : (name.equals("foliage") ? MapColor.foliageColor.colorIndex : (name.equals("snow") ? MapColor.snowColor.colorIndex : (name.equals("clay") ? MapColor.clayColor.colorIndex : (name.equals("dirt") ? MapColor.dirtColor.colorIndex : (name.equals("stone") ? MapColor.stoneColor.colorIndex : (name.equals("water") ? MapColor.waterColor.colorIndex : (name.equals("wood") ? MapColor.woodColor.colorIndex : (name.equals("quartz") ? MapColor.quartzColor.colorIndex : (name.equals("adobe") ? MapColor.adobeColor.colorIndex : (name.equals("magenta") ? MapColor.magentaColor.colorIndex : (name.equals("lightBlue") ? MapColor.lightBlueColor.colorIndex : (name.equals("light_blue") ? MapColor.lightBlueColor.colorIndex : (name.equals("yellow") ? MapColor.yellowColor.colorIndex : (name.equals("lime") ? MapColor.limeColor.colorIndex : (name.equals("pink") ? MapColor.pinkColor.colorIndex : (name.equals("gray") ? MapColor.grayColor.colorIndex : (name.equals("silver") ? MapColor.silverColor.colorIndex : (name.equals("cyan") ? MapColor.cyanColor.colorIndex : (name.equals("purple") ? MapColor.purpleColor.colorIndex : (name.equals("blue") ? MapColor.blueColor.colorIndex : (name.equals("brown") ? MapColor.brownColor.colorIndex : (name.equals("green") ? MapColor.greenColor.colorIndex : (name.equals("red") ? MapColor.redColor.colorIndex : (name.equals("black") ? MapColor.blackColor.colorIndex : (name.equals("gold") ? MapColor.goldColor.colorIndex : (name.equals("diamond") ? MapColor.diamondColor.colorIndex : (name.equals("lapis") ? MapColor.lapisColor.colorIndex : (name.equals("emerald") ? MapColor.emeraldColor.colorIndex : (name.equals("obsidian") ? MapColor.obsidianColor.colorIndex : (name.equals("netherrack") ? MapColor.netherrackColor.colorIndex : -1)))))))))))))))))))))))))))))))))))));
/*      */   }
/*      */ 
/*      */   
/*      */   private static int[] getMapColors() {
/* 1713 */     MapColor[] mapColors = MapColor.mapColorArray;
/* 1714 */     int[] colors = new int[mapColors.length];
/* 1715 */     Arrays.fill(colors, -1);
/*      */     
/* 1717 */     for (int i = 0; i < mapColors.length && i < colors.length; i++) {
/*      */       
/* 1719 */       MapColor mapColor = mapColors[i];
/*      */       
/* 1721 */       if (mapColor != null)
/*      */       {
/* 1723 */         colors[i] = mapColor.colorValue;
/*      */       }
/*      */     } 
/*      */     
/* 1727 */     return colors;
/*      */   }
/*      */ 
/*      */   
/*      */   private static void setMapColors(int[] colors) {
/* 1732 */     if (colors != null) {
/*      */       
/* 1734 */       MapColor[] mapColors = MapColor.mapColorArray;
/*      */       
/* 1736 */       for (int i = 0; i < mapColors.length && i < colors.length; i++) {
/*      */         
/* 1738 */         MapColor mapColor = mapColors[i];
/*      */         
/* 1740 */         if (mapColor != null) {
/*      */           
/* 1742 */           int color = colors[i];
/*      */           
/* 1744 */           if (color >= 0)
/*      */           {
/* 1746 */             mapColor.colorValue = color;
/*      */           }
/*      */         } 
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   private static int getEntityId(String name) {
/* 1755 */     if (name == null)
/*      */     {
/* 1757 */       return -1;
/*      */     }
/*      */ 
/*      */     
/* 1761 */     int id = EntityList.func_180122_a(name);
/*      */     
/* 1763 */     if (id < 0)
/*      */     {
/* 1765 */       return -1;
/*      */     }
/*      */ 
/*      */     
/* 1769 */     String idName = EntityList.getStringFromID(id);
/* 1770 */     return !Config.equals(name, idName) ? -1 : id;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static void dbg(String str) {
/* 1777 */     Config.dbg("CustomColors: " + str);
/*      */   }
/*      */ 
/*      */   
/*      */   private static void warn(String str) {
/* 1782 */     Config.warn("CustomColors: " + str);
/*      */   }
/*      */ 
/*      */   
/*      */   public static int getExpBarTextColor(int color) {
/* 1787 */     return (expBarTextColor < 0) ? color : expBarTextColor;
/*      */   }
/*      */ 
/*      */   
/*      */   public static int getBossTextColor(int color) {
/* 1792 */     return (bossTextColor < 0) ? color : bossTextColor;
/*      */   }
/*      */ 
/*      */   
/*      */   public static int getSignTextColor(int color) {
/* 1797 */     return (signTextColor < 0) ? color : signTextColor;
/*      */   }
/*      */   
/*      */   public static interface IColorizer {
/*      */     int getColor(IBlockAccess param1IBlockAccess, BlockPos param1BlockPos);
/*      */     
/*      */     boolean isColorConstant();
/*      */   }
/*      */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\optifine\CustomColors.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */