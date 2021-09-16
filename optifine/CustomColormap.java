/*     */ package optifine;
/*     */ 
/*     */ import java.awt.image.BufferedImage;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.util.HashSet;
/*     */ import java.util.Properties;
/*     */ import java.util.regex.Matcher;
/*     */ import java.util.regex.Pattern;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.state.BlockStateBase;
/*     */ import net.minecraft.client.renderer.texture.TextureUtil;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.biome.BiomeGenBase;
/*     */ 
/*     */ public class CustomColormap
/*     */   implements CustomColors.IColorizer {
/*  21 */   public String name = null;
/*  22 */   public String basePath = null;
/*  23 */   private int format = -1;
/*  24 */   private MatchBlock[] matchBlocks = null;
/*  25 */   private String source = null;
/*  26 */   private int color = -1;
/*  27 */   private int yVariance = 0;
/*  28 */   private int yOffset = 0;
/*  29 */   private int width = 0;
/*  30 */   private int height = 0;
/*  31 */   private int[] colors = null;
/*  32 */   private float[][] colorsRgb = null;
/*     */   
/*     */   private static final int FORMAT_UNKNOWN = -1;
/*     */   private static final int FORMAT_VANILLA = 0;
/*     */   private static final int FORMAT_GRID = 1;
/*     */   private static final int FORMAT_FIXED = 2;
/*     */   public static final String KEY_FORMAT = "format";
/*     */   public static final String KEY_BLOCKS = "blocks";
/*     */   public static final String KEY_SOURCE = "source";
/*     */   public static final String KEY_COLOR = "color";
/*     */   public static final String KEY_Y_VARIANCE = "yVariance";
/*     */   public static final String KEY_Y_OFFSET = "yOffset";
/*     */   
/*     */   public CustomColormap(Properties props, String path, int width, int height) {
/*  46 */     ConnectedParser cp = new ConnectedParser("Colormap");
/*  47 */     this.name = cp.parseName(path);
/*  48 */     this.basePath = cp.parseBasePath(path);
/*  49 */     this.format = parseFormat(props.getProperty("format"));
/*  50 */     this.matchBlocks = cp.parseMatchBlocks(props.getProperty("blocks"));
/*  51 */     this.source = parseTexture(props.getProperty("source"), path, this.basePath);
/*  52 */     this.color = ConnectedParser.parseColor(props.getProperty("color"), -1);
/*  53 */     this.yVariance = cp.parseInt(props.getProperty("yVariance"), 0);
/*  54 */     this.yOffset = cp.parseInt(props.getProperty("yOffset"), 0);
/*  55 */     this.width = width;
/*  56 */     this.height = height;
/*     */   }
/*     */ 
/*     */   
/*     */   private int parseFormat(String str) {
/*  61 */     if (str == null)
/*     */     {
/*  63 */       return 0;
/*     */     }
/*  65 */     if (str.equals("vanilla"))
/*     */     {
/*  67 */       return 0;
/*     */     }
/*  69 */     if (str.equals("grid"))
/*     */     {
/*  71 */       return 1;
/*     */     }
/*  73 */     if (str.equals("fixed"))
/*     */     {
/*  75 */       return 2;
/*     */     }
/*     */ 
/*     */     
/*  79 */     warn("Unknown format: " + str);
/*  80 */     return -1;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isValid(String path) {
/*  86 */     if (this.format != 0 && this.format != 1) {
/*     */       
/*  88 */       if (this.format != 2)
/*     */       {
/*  90 */         return false;
/*     */       }
/*     */       
/*  93 */       if (this.color < 0)
/*     */       {
/*  95 */         this.color = 16777215;
/*     */       }
/*     */     }
/*     */     else {
/*     */       
/* 100 */       if (this.source == null) {
/*     */         
/* 102 */         warn("Source not defined: " + path);
/* 103 */         return false;
/*     */       } 
/*     */       
/* 106 */       readColors();
/*     */       
/* 108 */       if (this.colors == null)
/*     */       {
/* 110 */         return false;
/*     */       }
/*     */       
/* 113 */       if (this.color < 0) {
/*     */         
/* 115 */         if (this.format == 0)
/*     */         {
/* 117 */           this.color = getColor(127, 127);
/*     */         }
/*     */         
/* 120 */         if (this.format == 1)
/*     */         {
/* 122 */           this.color = getColorGrid(BiomeGenBase.plains, new BlockPos(0, 64, 0));
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 127 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isValidMatchBlocks(String path) {
/* 132 */     if (this.matchBlocks == null) {
/*     */       
/* 134 */       this.matchBlocks = detectMatchBlocks();
/*     */       
/* 136 */       if (this.matchBlocks == null) {
/*     */         
/* 138 */         warn("Match blocks not defined: " + path);
/* 139 */         return false;
/*     */       } 
/*     */     } 
/*     */     
/* 143 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   private MatchBlock[] detectMatchBlocks() {
/* 148 */     Block block = Block.getBlockFromName(this.name);
/*     */     
/* 150 */     if (block != null)
/*     */     {
/* 152 */       return new MatchBlock[] { new MatchBlock(Block.getIdFromBlock(block)) };
/*     */     }
/*     */ 
/*     */     
/* 156 */     Pattern p = Pattern.compile("^block([0-9]+).*$");
/* 157 */     Matcher m = p.matcher(this.name);
/*     */     
/* 159 */     if (m.matches()) {
/*     */       
/* 161 */       String cp = m.group(1);
/* 162 */       int mbs = Config.parseInt(cp, -1);
/*     */       
/* 164 */       if (mbs >= 0)
/*     */       {
/* 166 */         return new MatchBlock[] { new MatchBlock(mbs) };
/*     */       }
/*     */     } 
/*     */     
/* 170 */     ConnectedParser cp1 = new ConnectedParser("Colormap");
/* 171 */     MatchBlock[] mbs1 = cp1.parseMatchBlock(this.name);
/* 172 */     return (mbs1 != null) ? mbs1 : null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void readColors() {
/*     */     try {
/* 180 */       this.colors = null;
/*     */       
/* 182 */       if (this.source == null) {
/*     */         return;
/*     */       }
/*     */ 
/*     */       
/* 187 */       String e = String.valueOf(this.source) + ".png";
/* 188 */       ResourceLocation loc = new ResourceLocation(e);
/* 189 */       InputStream is = Config.getResourceStream(loc);
/*     */       
/* 191 */       if (is == null) {
/*     */         return;
/*     */       }
/*     */ 
/*     */       
/* 196 */       BufferedImage img = TextureUtil.func_177053_a(is);
/*     */       
/* 198 */       if (img == null) {
/*     */         return;
/*     */       }
/*     */ 
/*     */       
/* 203 */       int imgWidth = img.getWidth();
/* 204 */       int imgHeight = img.getHeight();
/* 205 */       boolean widthOk = !(this.width >= 0 && this.width != imgWidth);
/* 206 */       boolean heightOk = !(this.height >= 0 && this.height != imgHeight);
/*     */       
/* 208 */       if (!widthOk || !heightOk)
/*     */       {
/* 210 */         dbg("Non-standard palette size: " + imgWidth + "x" + imgHeight + ", should be: " + this.width + "x" + this.height + ", path: " + e);
/*     */       }
/*     */       
/* 213 */       this.width = imgWidth;
/* 214 */       this.height = imgHeight;
/*     */       
/* 216 */       if (this.width <= 0 || this.height <= 0) {
/*     */         
/* 218 */         warn("Invalid palette size: " + imgWidth + "x" + imgHeight + ", path: " + e);
/*     */         
/*     */         return;
/*     */       } 
/* 222 */       this.colors = new int[imgWidth * imgHeight];
/* 223 */       img.getRGB(0, 0, imgWidth, imgHeight, this.colors, 0, imgWidth);
/*     */     }
/* 225 */     catch (IOException var9) {
/*     */       
/* 227 */       var9.printStackTrace();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private static void dbg(String str) {
/* 233 */     Config.dbg("CustomColors: " + str);
/*     */   }
/*     */ 
/*     */   
/*     */   private static void warn(String str) {
/* 238 */     Config.warn("CustomColors: " + str);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static String parseTexture(String texStr, String path, String basePath) {
/* 245 */     if (texStr != null) {
/*     */       
/* 247 */       String str1 = ".png";
/*     */       
/* 249 */       if (texStr.endsWith(str1))
/*     */       {
/* 251 */         texStr = texStr.substring(0, texStr.length() - str1.length());
/*     */       }
/*     */       
/* 254 */       texStr = fixTextureName(texStr, basePath);
/* 255 */       return texStr;
/*     */     } 
/*     */ 
/*     */     
/* 259 */     String str = path;
/* 260 */     int pos = path.lastIndexOf('/');
/*     */     
/* 262 */     if (pos >= 0)
/*     */     {
/* 264 */       str = path.substring(pos + 1);
/*     */     }
/*     */     
/* 267 */     int pos2 = str.lastIndexOf('.');
/*     */     
/* 269 */     if (pos2 >= 0)
/*     */     {
/* 271 */       str = str.substring(0, pos2);
/*     */     }
/*     */     
/* 274 */     str = fixTextureName(str, basePath);
/* 275 */     return str;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static String fixTextureName(String iconName, String basePath) {
/* 281 */     iconName = TextureUtils.fixResourcePath(iconName, basePath);
/*     */     
/* 283 */     if (!iconName.startsWith(basePath) && !iconName.startsWith("textures/") && !iconName.startsWith("mcpatcher/"))
/*     */     {
/* 285 */       iconName = String.valueOf(basePath) + "/" + iconName;
/*     */     }
/*     */     
/* 288 */     if (iconName.endsWith(".png"))
/*     */     {
/* 290 */       iconName = iconName.substring(0, iconName.length() - 4);
/*     */     }
/*     */     
/* 293 */     String pathBlocks = "textures/blocks/";
/*     */     
/* 295 */     if (iconName.startsWith(pathBlocks))
/*     */     {
/* 297 */       iconName = iconName.substring(pathBlocks.length());
/*     */     }
/*     */     
/* 300 */     if (iconName.startsWith("/"))
/*     */     {
/* 302 */       iconName = iconName.substring(1);
/*     */     }
/*     */     
/* 305 */     return iconName;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean matchesBlock(BlockStateBase blockState) {
/* 310 */     return Matches.block(blockState, this.matchBlocks);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getColorRandom() {
/* 315 */     if (this.format == 2)
/*     */     {
/* 317 */       return this.color;
/*     */     }
/*     */ 
/*     */     
/* 321 */     int index = CustomColors.random.nextInt(this.colors.length);
/* 322 */     return this.colors[index];
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getColor(int index) {
/* 328 */     index = Config.limit(index, 0, this.colors.length);
/* 329 */     return this.colors[index] & 0xFFFFFF;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getColor(int cx, int cy) {
/* 334 */     cx = Config.limit(cx, 0, this.width - 1);
/* 335 */     cy = Config.limit(cy, 0, this.height - 1);
/* 336 */     return this.colors[cy * this.width + cx] & 0xFFFFFF;
/*     */   }
/*     */ 
/*     */   
/*     */   public float[][] getColorsRgb() {
/* 341 */     if (this.colorsRgb == null)
/*     */     {
/* 343 */       this.colorsRgb = toRgb(this.colors);
/*     */     }
/*     */     
/* 346 */     return this.colorsRgb;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getColor(IBlockAccess blockAccess, BlockPos blockPos) {
/* 351 */     BiomeGenBase biome = CustomColors.getColorBiome(blockAccess, blockPos);
/* 352 */     return getColor(biome, blockPos);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isColorConstant() {
/* 357 */     return (this.format == 2);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getColor(BiomeGenBase biome, BlockPos blockPos) {
/* 362 */     return (this.format == 0) ? getColorVanilla(biome, blockPos) : ((this.format == 1) ? getColorGrid(biome, blockPos) : this.color);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getColorSmooth(IBlockAccess blockAccess, double x, double y, double z, int radius) {
/* 367 */     if (this.format == 2)
/*     */     {
/* 369 */       return this.color;
/*     */     }
/*     */ 
/*     */     
/* 373 */     int x0 = MathHelper.floor_double(x);
/* 374 */     int y0 = MathHelper.floor_double(y);
/* 375 */     int z0 = MathHelper.floor_double(z);
/* 376 */     int sumRed = 0;
/* 377 */     int sumGreen = 0;
/* 378 */     int sumBlue = 0;
/* 379 */     int count = 0;
/* 380 */     BlockPosM blockPosM = new BlockPosM(0, 0, 0);
/*     */ 
/*     */     
/*     */     int r;
/*     */     
/* 385 */     for (r = x0 - radius; r <= x0 + radius; r++) {
/*     */       
/* 387 */       for (int i = z0 - radius; i <= z0 + radius; i++) {
/*     */         
/* 389 */         blockPosM.setXyz(r, y0, i);
/* 390 */         int j = getColor(blockAccess, blockPosM);
/* 391 */         sumRed += j >> 16 & 0xFF;
/* 392 */         sumGreen += j >> 8 & 0xFF;
/* 393 */         sumBlue += j & 0xFF;
/* 394 */         count++;
/*     */       } 
/*     */     } 
/*     */     
/* 398 */     r = sumRed / count;
/* 399 */     int g = sumGreen / count;
/* 400 */     int b = sumBlue / count;
/* 401 */     return r << 16 | g << 8 | b;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private int getColorVanilla(BiomeGenBase biome, BlockPos blockPos) {
/* 407 */     double temperature = MathHelper.clamp_float(biome.func_180626_a(blockPos), 0.0F, 1.0F);
/* 408 */     double rainfall = MathHelper.clamp_float(biome.getFloatRainfall(), 0.0F, 1.0F);
/* 409 */     rainfall *= temperature;
/* 410 */     int cx = (int)((1.0D - temperature) * (this.width - 1));
/* 411 */     int cy = (int)((1.0D - rainfall) * (this.height - 1));
/* 412 */     return getColor(cx, cy);
/*     */   }
/*     */ 
/*     */   
/*     */   private int getColorGrid(BiomeGenBase biome, BlockPos blockPos) {
/* 417 */     int cx = biome.biomeID;
/* 418 */     int cy = blockPos.getY() - this.yOffset;
/*     */     
/* 420 */     if (this.yVariance > 0) {
/*     */       
/* 422 */       int seed = blockPos.getX() << 16 + blockPos.getZ();
/* 423 */       int rand = Config.intHash(seed);
/* 424 */       int range = this.yVariance * 2 + 1;
/* 425 */       int diff = (rand & 0xFF) % range - this.yVariance;
/* 426 */       cy += diff;
/*     */     } 
/*     */     
/* 429 */     return getColor(cx, cy);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getLength() {
/* 434 */     return (this.format == 2) ? 1 : this.colors.length;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getWidth() {
/* 439 */     return this.width;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getHeight() {
/* 444 */     return this.height;
/*     */   }
/*     */ 
/*     */   
/*     */   private static float[][] toRgb(int[] cols) {
/* 449 */     float[][] colsRgb = new float[cols.length][3];
/*     */     
/* 451 */     for (int i = 0; i < cols.length; i++) {
/*     */       
/* 453 */       int col = cols[i];
/* 454 */       float rf = (col >> 16 & 0xFF) / 255.0F;
/* 455 */       float gf = (col >> 8 & 0xFF) / 255.0F;
/* 456 */       float bf = (col & 0xFF) / 255.0F;
/* 457 */       float[] colRgb = colsRgb[i];
/* 458 */       colRgb[0] = rf;
/* 459 */       colRgb[1] = gf;
/* 460 */       colRgb[2] = bf;
/*     */     } 
/*     */     
/* 463 */     return colsRgb;
/*     */   }
/*     */ 
/*     */   
/*     */   public void addMatchBlock(MatchBlock mb) {
/* 468 */     if (this.matchBlocks == null)
/*     */     {
/* 470 */       this.matchBlocks = new MatchBlock[0];
/*     */     }
/*     */     
/* 473 */     this.matchBlocks = (MatchBlock[])Config.addObjectToArray((Object[])this.matchBlocks, mb);
/*     */   }
/*     */ 
/*     */   
/*     */   public void addMatchBlock(int blockId, int metadata) {
/* 478 */     MatchBlock mb = getMatchBlock(blockId);
/*     */     
/* 480 */     if (mb != null) {
/*     */       
/* 482 */       if (metadata >= 0)
/*     */       {
/* 484 */         mb.addMetadata(metadata);
/*     */       }
/*     */     }
/*     */     else {
/*     */       
/* 489 */       addMatchBlock(new MatchBlock(blockId, metadata));
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private MatchBlock getMatchBlock(int blockId) {
/* 495 */     if (this.matchBlocks == null)
/*     */     {
/* 497 */       return null;
/*     */     }
/*     */ 
/*     */     
/* 501 */     for (int i = 0; i < this.matchBlocks.length; i++) {
/*     */       
/* 503 */       MatchBlock mb = this.matchBlocks[i];
/*     */       
/* 505 */       if (mb.getBlockId() == blockId)
/*     */       {
/* 507 */         return mb;
/*     */       }
/*     */     } 
/*     */     
/* 511 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int[] getMatchBlockIds() {
/* 517 */     if (this.matchBlocks == null)
/*     */     {
/* 519 */       return null;
/*     */     }
/*     */ 
/*     */     
/* 523 */     HashSet<Integer> setIds = new HashSet();
/*     */     
/* 525 */     for (int ints = 0; ints < this.matchBlocks.length; ints++) {
/*     */       
/* 527 */       MatchBlock ids = this.matchBlocks[ints];
/*     */       
/* 529 */       if (ids.getBlockId() >= 0)
/*     */       {
/* 531 */         setIds.add(Integer.valueOf(ids.getBlockId()));
/*     */       }
/*     */     } 
/*     */     
/* 535 */     Integer[] var5 = (Integer[])setIds.toArray((Object[])new Integer[setIds.size()]);
/* 536 */     int[] var6 = new int[var5.length];
/*     */     
/* 538 */     for (int i = 0; i < var5.length; i++)
/*     */     {
/* 540 */       var6[i] = var5[i].intValue();
/*     */     }
/*     */     
/* 543 */     return var6;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 549 */     return this.basePath + "/" + this.name + ", blocks: " + Config.arrayToString((Object[])this.matchBlocks) + ", source: " + this.source;
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\optifine\CustomColormap.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */