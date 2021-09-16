/*     */ package optifine;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import java.util.Properties;
/*     */ import net.minecraft.block.properties.IProperty;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.renderer.texture.TextureAtlasSprite;
/*     */ import net.minecraft.client.renderer.texture.TextureMap;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.world.biome.BiomeGenBase;
/*     */ 
/*     */ public class ConnectedProperties
/*     */ {
/*  16 */   public String name = null;
/*  17 */   public String basePath = null;
/*  18 */   public MatchBlock[] matchBlocks = null;
/*  19 */   public int[] metadatas = null;
/*  20 */   public String[] matchTiles = null;
/*  21 */   public int method = 0;
/*  22 */   public String[] tiles = null;
/*  23 */   public int connect = 0;
/*  24 */   public int faces = 63;
/*  25 */   public BiomeGenBase[] biomes = null;
/*  26 */   public int minHeight = 0;
/*  27 */   public int maxHeight = 1024;
/*  28 */   public int renderPass = 0;
/*     */   public boolean innerSeams = false;
/*  30 */   public int width = 0;
/*  31 */   public int height = 0;
/*  32 */   public int[] weights = null;
/*  33 */   public int symmetry = 1;
/*  34 */   public int[] sumWeights = null;
/*  35 */   public int sumAllWeights = 1;
/*  36 */   public TextureAtlasSprite[] matchTileIcons = null;
/*  37 */   public TextureAtlasSprite[] tileIcons = null;
/*     */   
/*     */   public static final int METHOD_NONE = 0;
/*     */   public static final int METHOD_CTM = 1;
/*     */   public static final int METHOD_HORIZONTAL = 2;
/*     */   public static final int METHOD_TOP = 3;
/*     */   public static final int METHOD_RANDOM = 4;
/*     */   public static final int METHOD_REPEAT = 5;
/*     */   public static final int METHOD_VERTICAL = 6;
/*     */   public static final int METHOD_FIXED = 7;
/*     */   public static final int METHOD_HORIZONTAL_VERTICAL = 8;
/*     */   public static final int METHOD_VERTICAL_HORIZONTAL = 9;
/*     */   public static final int CONNECT_NONE = 0;
/*     */   public static final int CONNECT_BLOCK = 1;
/*     */   public static final int CONNECT_TILE = 2;
/*     */   public static final int CONNECT_MATERIAL = 3;
/*     */   public static final int CONNECT_UNKNOWN = 128;
/*     */   public static final int FACE_BOTTOM = 1;
/*     */   public static final int FACE_TOP = 2;
/*     */   public static final int FACE_NORTH = 4;
/*     */   public static final int FACE_SOUTH = 8;
/*     */   public static final int FACE_WEST = 16;
/*     */   public static final int FACE_EAST = 32;
/*     */   public static final int FACE_SIDES = 60;
/*     */   public static final int FACE_ALL = 63;
/*     */   public static final int FACE_UNKNOWN = 128;
/*     */   public static final int SYMMETRY_NONE = 1;
/*     */   public static final int SYMMETRY_OPPOSITE = 2;
/*     */   public static final int SYMMETRY_ALL = 6;
/*     */   public static final int SYMMETRY_UNKNOWN = 128;
/*     */   
/*     */   public ConnectedProperties(Properties props, String path) {
/*  69 */     ConnectedParser cp = new ConnectedParser("ConnectedTextures");
/*  70 */     this.name = cp.parseName(path);
/*  71 */     this.basePath = cp.parseBasePath(path);
/*  72 */     this.matchBlocks = cp.parseMatchBlocks(props.getProperty("matchBlocks"));
/*  73 */     this.metadatas = cp.parseIntList(props.getProperty("metadata"));
/*  74 */     this.matchTiles = parseMatchTiles(props.getProperty("matchTiles"));
/*  75 */     this.method = parseMethod(props.getProperty("method"));
/*  76 */     this.tiles = parseTileNames(props.getProperty("tiles"));
/*  77 */     this.connect = parseConnect(props.getProperty("connect"));
/*  78 */     this.faces = parseFaces(props.getProperty("faces"));
/*  79 */     this.biomes = cp.parseBiomes(props.getProperty("biomes"));
/*  80 */     this.minHeight = cp.parseInt(props.getProperty("minHeight"), -1);
/*  81 */     this.maxHeight = cp.parseInt(props.getProperty("maxHeight"), 1024);
/*  82 */     this.renderPass = cp.parseInt(props.getProperty("renderPass"));
/*  83 */     this.innerSeams = ConnectedParser.parseBoolean(props.getProperty("innerSeams"));
/*  84 */     this.width = cp.parseInt(props.getProperty("width"));
/*  85 */     this.height = cp.parseInt(props.getProperty("height"));
/*  86 */     this.weights = cp.parseIntList(props.getProperty("weights"));
/*  87 */     this.symmetry = parseSymmetry(props.getProperty("symmetry"));
/*     */   }
/*     */ 
/*     */   
/*     */   private String[] parseMatchTiles(String str) {
/*  92 */     if (str == null)
/*     */     {
/*  94 */       return null;
/*     */     }
/*     */ 
/*     */     
/*  98 */     String[] names = Config.tokenize(str, " ");
/*     */     
/* 100 */     for (int i = 0; i < names.length; i++) {
/*     */       
/* 102 */       String iconName = names[i];
/*     */       
/* 104 */       if (iconName.endsWith(".png"))
/*     */       {
/* 106 */         iconName = iconName.substring(0, iconName.length() - 4);
/*     */       }
/*     */       
/* 109 */       iconName = TextureUtils.fixResourcePath(iconName, this.basePath);
/* 110 */       names[i] = iconName;
/*     */     } 
/*     */     
/* 113 */     return names;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static String parseName(String path) {
/* 119 */     String str = path;
/* 120 */     int pos = path.lastIndexOf('/');
/*     */     
/* 122 */     if (pos >= 0)
/*     */     {
/* 124 */       str = path.substring(pos + 1);
/*     */     }
/*     */     
/* 127 */     int pos2 = str.lastIndexOf('.');
/*     */     
/* 129 */     if (pos2 >= 0)
/*     */     {
/* 131 */       str = str.substring(0, pos2);
/*     */     }
/*     */     
/* 134 */     return str;
/*     */   }
/*     */ 
/*     */   
/*     */   private static String parseBasePath(String path) {
/* 139 */     int pos = path.lastIndexOf('/');
/* 140 */     return (pos < 0) ? "" : path.substring(0, pos);
/*     */   }
/*     */ 
/*     */   
/*     */   private String[] parseTileNames(String str) {
/* 145 */     if (str == null)
/*     */     {
/* 147 */       return null;
/*     */     }
/*     */ 
/*     */     
/* 151 */     ArrayList<String> list = new ArrayList();
/* 152 */     String[] iconStrs = Config.tokenize(str, " ,");
/*     */ 
/*     */     
/* 155 */     for (int names = 0; names < iconStrs.length; names++) {
/*     */       
/* 157 */       String i = iconStrs[names];
/*     */       
/* 159 */       if (i.contains("-")) {
/*     */         
/* 161 */         String[] iconName = Config.tokenize(i, "-");
/*     */         
/* 163 */         if (iconName.length == 2) {
/*     */           
/* 165 */           int pathBlocks = Config.parseInt(iconName[0], -1);
/* 166 */           int max = Config.parseInt(iconName[1], -1);
/*     */           
/* 168 */           if (pathBlocks >= 0 && max >= 0) {
/*     */             
/* 170 */             if (pathBlocks <= max) {
/*     */               
/* 172 */               int n = pathBlocks;
/*     */ 
/*     */ 
/*     */               
/* 176 */               while (n <= max) {
/*     */ 
/*     */ 
/*     */ 
/*     */                 
/* 181 */                 list.add(String.valueOf(n));
/* 182 */                 n++;
/*     */               } 
/*     */             } else {
/*     */               
/* 186 */               Config.warn("Invalid interval: " + i + ", when parsing: " + str);
/*     */             } 
/*     */             continue;
/*     */           } 
/*     */         } 
/*     */       } 
/* 192 */       list.add(i);
/*     */       continue;
/*     */     } 
/* 195 */     String[] var10 = list.<String>toArray(new String[list.size()]);
/*     */     
/* 197 */     for (int var11 = 0; var11 < var10.length; var11++) {
/*     */       
/* 199 */       String var12 = var10[var11];
/* 200 */       var12 = TextureUtils.fixResourcePath(var12, this.basePath);
/*     */       
/* 202 */       if (!var12.startsWith(this.basePath) && !var12.startsWith("textures/") && !var12.startsWith("mcpatcher/"))
/*     */       {
/* 204 */         var12 = String.valueOf(this.basePath) + "/" + var12;
/*     */       }
/*     */       
/* 207 */       if (var12.endsWith(".png"))
/*     */       {
/* 209 */         var12 = var12.substring(0, var12.length() - 4);
/*     */       }
/*     */       
/* 212 */       String var13 = "textures/blocks/";
/*     */       
/* 214 */       if (var12.startsWith(var13))
/*     */       {
/* 216 */         var12 = var12.substring(var13.length());
/*     */       }
/*     */       
/* 219 */       if (var12.startsWith("/"))
/*     */       {
/* 221 */         var12 = var12.substring(1);
/*     */       }
/*     */       
/* 224 */       var10[var11] = var12;
/*     */     } 
/*     */     
/* 227 */     return var10;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static int parseSymmetry(String str) {
/* 233 */     if (str == null)
/*     */     {
/* 235 */       return 1;
/*     */     }
/* 237 */     if (str.equals("opposite"))
/*     */     {
/* 239 */       return 2;
/*     */     }
/* 241 */     if (str.equals("all"))
/*     */     {
/* 243 */       return 6;
/*     */     }
/*     */ 
/*     */     
/* 247 */     Config.warn("Unknown symmetry: " + str);
/* 248 */     return 1;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static int parseFaces(String str) {
/* 254 */     if (str == null)
/*     */     {
/* 256 */       return 63;
/*     */     }
/*     */ 
/*     */     
/* 260 */     String[] faceStrs = Config.tokenize(str, " ,");
/* 261 */     int facesMask = 0;
/*     */     
/* 263 */     for (int i = 0; i < faceStrs.length; i++) {
/*     */       
/* 265 */       String faceStr = faceStrs[i];
/* 266 */       int faceMask = parseFace(faceStr);
/* 267 */       facesMask |= faceMask;
/*     */     } 
/*     */     
/* 270 */     return facesMask;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static int parseFace(String str) {
/* 276 */     str = str.toLowerCase();
/*     */     
/* 278 */     if (!str.equals("bottom") && !str.equals("down")) {
/*     */       
/* 280 */       if (!str.equals("top") && !str.equals("up")) {
/*     */         
/* 282 */         if (str.equals("north"))
/*     */         {
/* 284 */           return 4;
/*     */         }
/* 286 */         if (str.equals("south"))
/*     */         {
/* 288 */           return 8;
/*     */         }
/* 290 */         if (str.equals("east"))
/*     */         {
/* 292 */           return 32;
/*     */         }
/* 294 */         if (str.equals("west"))
/*     */         {
/* 296 */           return 16;
/*     */         }
/* 298 */         if (str.equals("sides"))
/*     */         {
/* 300 */           return 60;
/*     */         }
/* 302 */         if (str.equals("all"))
/*     */         {
/* 304 */           return 63;
/*     */         }
/*     */ 
/*     */         
/* 308 */         Config.warn("Unknown face: " + str);
/* 309 */         return 128;
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 314 */       return 2;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 319 */     return 1;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static int parseConnect(String str) {
/* 325 */     if (str == null)
/*     */     {
/* 327 */       return 0;
/*     */     }
/* 329 */     if (str.equals("block"))
/*     */     {
/* 331 */       return 1;
/*     */     }
/* 333 */     if (str.equals("tile"))
/*     */     {
/* 335 */       return 2;
/*     */     }
/* 337 */     if (str.equals("material"))
/*     */     {
/* 339 */       return 3;
/*     */     }
/*     */ 
/*     */     
/* 343 */     Config.warn("Unknown connect: " + str);
/* 344 */     return 128;
/*     */   }
/*     */ 
/*     */   
/*     */   public static IProperty getProperty(String key, Collection properties) {
/*     */     IProperty prop;
/* 350 */     Iterator<IProperty> it = properties.iterator();
/*     */ 
/*     */ 
/*     */     
/*     */     do {
/* 355 */       if (!it.hasNext())
/*     */       {
/* 357 */         return null;
/*     */       }
/*     */       
/* 360 */       prop = it.next();
/*     */     }
/* 362 */     while (!key.equals(prop.getName()));
/*     */     
/* 364 */     return prop;
/*     */   }
/*     */ 
/*     */   
/*     */   private static int parseMethod(String str) {
/* 369 */     if (str == null)
/*     */     {
/* 371 */       return 1;
/*     */     }
/* 373 */     if (!str.equals("ctm") && !str.equals("glass")) {
/*     */       
/* 375 */       if (!str.equals("horizontal") && !str.equals("bookshelf")) {
/*     */         
/* 377 */         if (str.equals("vertical"))
/*     */         {
/* 379 */           return 6;
/*     */         }
/* 381 */         if (str.equals("top"))
/*     */         {
/* 383 */           return 3;
/*     */         }
/* 385 */         if (str.equals("random"))
/*     */         {
/* 387 */           return 4;
/*     */         }
/* 389 */         if (str.equals("repeat"))
/*     */         {
/* 391 */           return 5;
/*     */         }
/* 393 */         if (str.equals("fixed"))
/*     */         {
/* 395 */           return 7;
/*     */         }
/* 397 */         if (!str.equals("horizontal+vertical") && !str.equals("h+v")) {
/*     */           
/* 399 */           if (!str.equals("vertical+horizontal") && !str.equals("v+h")) {
/*     */             
/* 401 */             Config.warn("Unknown method: " + str);
/* 402 */             return 0;
/*     */           } 
/*     */ 
/*     */           
/* 406 */           return 9;
/*     */         } 
/*     */ 
/*     */ 
/*     */         
/* 411 */         return 8;
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 416 */       return 2;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 421 */     return 1;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isValid(String path) {
/* 427 */     if (this.name != null && this.name.length() > 0) {
/*     */       
/* 429 */       if (this.basePath == null) {
/*     */         
/* 431 */         Config.warn("No base path found: " + path);
/* 432 */         return false;
/*     */       } 
/*     */ 
/*     */       
/* 436 */       if (this.matchBlocks == null)
/*     */       {
/* 438 */         this.matchBlocks = detectMatchBlocks();
/*     */       }
/*     */       
/* 441 */       if (this.matchTiles == null && this.matchBlocks == null)
/*     */       {
/* 443 */         this.matchTiles = detectMatchTiles();
/*     */       }
/*     */       
/* 446 */       if (this.matchBlocks == null && this.matchTiles == null) {
/*     */         
/* 448 */         Config.warn("No matchBlocks or matchTiles specified: " + path);
/* 449 */         return false;
/*     */       } 
/* 451 */       if (this.method == 0) {
/*     */         
/* 453 */         Config.warn("No method: " + path);
/* 454 */         return false;
/*     */       } 
/* 456 */       if (this.tiles != null && this.tiles.length > 0) {
/*     */         
/* 458 */         if (this.connect == 0)
/*     */         {
/* 460 */           this.connect = detectConnect();
/*     */         }
/*     */         
/* 463 */         if (this.connect == 128) {
/*     */           
/* 465 */           Config.warn("Invalid connect in: " + path);
/* 466 */           return false;
/*     */         } 
/* 468 */         if (this.renderPass > 0) {
/*     */           
/* 470 */           Config.warn("Render pass not supported: " + this.renderPass);
/* 471 */           return false;
/*     */         } 
/* 473 */         if ((this.faces & 0x80) != 0) {
/*     */           
/* 475 */           Config.warn("Invalid faces in: " + path);
/* 476 */           return false;
/*     */         } 
/* 478 */         if ((this.symmetry & 0x80) != 0) {
/*     */           
/* 480 */           Config.warn("Invalid symmetry in: " + path);
/* 481 */           return false;
/*     */         } 
/*     */ 
/*     */         
/* 485 */         switch (this.method) {
/*     */           
/*     */           case 1:
/* 488 */             return isValidCtm(path);
/*     */           
/*     */           case 2:
/* 491 */             return isValidHorizontal(path);
/*     */           
/*     */           case 3:
/* 494 */             return isValidTop(path);
/*     */           
/*     */           case 4:
/* 497 */             return isValidRandom(path);
/*     */           
/*     */           case 5:
/* 500 */             return isValidRepeat(path);
/*     */           
/*     */           case 6:
/* 503 */             return isValidVertical(path);
/*     */           
/*     */           case 7:
/* 506 */             return isValidFixed(path);
/*     */           
/*     */           case 8:
/* 509 */             return isValidHorizontalVertical(path);
/*     */           
/*     */           case 9:
/* 512 */             return isValidVerticalHorizontal(path);
/*     */         } 
/*     */         
/* 515 */         Config.warn("Unknown method: " + path);
/* 516 */         return false;
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 522 */       Config.warn("No tiles specified: " + path);
/* 523 */       return false;
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 529 */     Config.warn("No name found: " + path);
/* 530 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private int detectConnect() {
/* 536 */     return (this.matchBlocks != null) ? 1 : ((this.matchTiles != null) ? 2 : 128);
/*     */   }
/*     */ 
/*     */   
/*     */   private MatchBlock[] detectMatchBlocks() {
/* 541 */     int[] ids = detectMatchBlockIds();
/*     */     
/* 543 */     if (ids == null)
/*     */     {
/* 545 */       return null;
/*     */     }
/*     */ 
/*     */     
/* 549 */     MatchBlock[] mbs = new MatchBlock[ids.length];
/*     */     
/* 551 */     for (int i = 0; i < mbs.length; i++)
/*     */     {
/* 553 */       mbs[i] = new MatchBlock(ids[i]);
/*     */     }
/*     */     
/* 556 */     return mbs;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private int[] detectMatchBlockIds() {
/* 562 */     if (!this.name.startsWith("block"))
/*     */     {
/* 564 */       return null;
/*     */     }
/*     */ 
/*     */     
/* 568 */     int startPos = "block".length();
/*     */     
/*     */     int pos;
/* 571 */     for (pos = startPos; pos < this.name.length(); pos++) {
/*     */       
/* 573 */       char idStr = this.name.charAt(pos);
/*     */       
/* 575 */       if (idStr < '0' || idStr > '9') {
/*     */         break;
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 581 */     if (pos == startPos)
/*     */     {
/* 583 */       return null;
/*     */     }
/*     */ 
/*     */     
/* 587 */     String var5 = this.name.substring(startPos, pos);
/* 588 */     int id = Config.parseInt(var5, -1);
/* 589 */     (new int[1])[0] = id; return (id < 0) ? null : new int[1];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private String[] detectMatchTiles() {
/* 596 */     TextureAtlasSprite icon = getIcon(this.name);
/* 597 */     (new String[1])[0] = this.name; return (icon == null) ? null : new String[1];
/*     */   }
/*     */ 
/*     */   
/*     */   private static TextureAtlasSprite getIcon(String iconName) {
/* 602 */     TextureMap textureMapBlocks = Minecraft.getMinecraft().getTextureMapBlocks();
/* 603 */     TextureAtlasSprite icon = textureMapBlocks.getSpriteSafe(iconName);
/*     */     
/* 605 */     if (icon != null)
/*     */     {
/* 607 */       return icon;
/*     */     }
/*     */ 
/*     */     
/* 611 */     icon = textureMapBlocks.getSpriteSafe("blocks/" + iconName);
/* 612 */     return icon;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean isValidCtm(String path) {
/* 618 */     if (this.tiles == null)
/*     */     {
/* 620 */       this.tiles = parseTileNames("0-11 16-27 32-43 48-58");
/*     */     }
/*     */     
/* 623 */     if (this.tiles.length < 47) {
/*     */       
/* 625 */       Config.warn("Invalid tiles, must be at least 47: " + path);
/* 626 */       return false;
/*     */     } 
/*     */ 
/*     */     
/* 630 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean isValidHorizontal(String path) {
/* 636 */     if (this.tiles == null)
/*     */     {
/* 638 */       this.tiles = parseTileNames("12-15");
/*     */     }
/*     */     
/* 641 */     if (this.tiles.length != 4) {
/*     */       
/* 643 */       Config.warn("Invalid tiles, must be exactly 4: " + path);
/* 644 */       return false;
/*     */     } 
/*     */ 
/*     */     
/* 648 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean isValidVertical(String path) {
/* 654 */     if (this.tiles == null) {
/*     */       
/* 656 */       Config.warn("No tiles defined for vertical: " + path);
/* 657 */       return false;
/*     */     } 
/* 659 */     if (this.tiles.length != 4) {
/*     */       
/* 661 */       Config.warn("Invalid tiles, must be exactly 4: " + path);
/* 662 */       return false;
/*     */     } 
/*     */ 
/*     */     
/* 666 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean isValidHorizontalVertical(String path) {
/* 672 */     if (this.tiles == null) {
/*     */       
/* 674 */       Config.warn("No tiles defined for horizontal+vertical: " + path);
/* 675 */       return false;
/*     */     } 
/* 677 */     if (this.tiles.length != 7) {
/*     */       
/* 679 */       Config.warn("Invalid tiles, must be exactly 7: " + path);
/* 680 */       return false;
/*     */     } 
/*     */ 
/*     */     
/* 684 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean isValidVerticalHorizontal(String path) {
/* 690 */     if (this.tiles == null) {
/*     */       
/* 692 */       Config.warn("No tiles defined for vertical+horizontal: " + path);
/* 693 */       return false;
/*     */     } 
/* 695 */     if (this.tiles.length != 7) {
/*     */       
/* 697 */       Config.warn("Invalid tiles, must be exactly 7: " + path);
/* 698 */       return false;
/*     */     } 
/*     */ 
/*     */     
/* 702 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean isValidRandom(String path) {
/* 708 */     if (this.tiles != null && this.tiles.length > 0) {
/*     */       
/* 710 */       if (this.weights != null) {
/*     */ 
/*     */ 
/*     */         
/* 714 */         if (this.weights.length > this.tiles.length) {
/*     */           
/* 716 */           Config.warn("More weights defined than tiles, trimming weights: " + path);
/* 717 */           int[] sum = new int[this.tiles.length];
/* 718 */           System.arraycopy(this.weights, 0, sum, 0, sum.length);
/* 719 */           this.weights = sum;
/*     */         } 
/*     */ 
/*     */ 
/*     */         
/* 724 */         if (this.weights.length < this.tiles.length) {
/*     */           
/* 726 */           Config.warn("Less weights defined than tiles, expanding weights: " + path);
/* 727 */           int[] sum = new int[this.tiles.length];
/* 728 */           System.arraycopy(this.weights, 0, sum, 0, this.weights.length);
/* 729 */           int j = MathUtils.getAverage(this.weights);
/*     */           
/* 731 */           for (int i1 = this.weights.length; i1 < sum.length; i1++)
/*     */           {
/* 733 */             sum[i1] = j;
/*     */           }
/*     */           
/* 736 */           this.weights = sum;
/*     */         } 
/*     */         
/* 739 */         this.sumWeights = new int[this.weights.length];
/* 740 */         int var5 = 0;
/*     */         
/* 742 */         for (int i = 0; i < this.weights.length; i++) {
/*     */           
/* 744 */           var5 += this.weights[i];
/* 745 */           this.sumWeights[i] = var5;
/*     */         } 
/*     */         
/* 748 */         this.sumAllWeights = var5;
/*     */         
/* 750 */         if (this.sumAllWeights <= 0) {
/*     */           
/* 752 */           Config.warn("Invalid sum of all weights: " + var5);
/* 753 */           this.sumAllWeights = 1;
/*     */         } 
/*     */       } 
/*     */       
/* 757 */       return true;
/*     */     } 
/*     */ 
/*     */     
/* 761 */     Config.warn("Tiles not defined: " + path);
/* 762 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean isValidRepeat(String path) {
/* 768 */     if (this.tiles == null) {
/*     */       
/* 770 */       Config.warn("Tiles not defined: " + path);
/* 771 */       return false;
/*     */     } 
/* 773 */     if (this.width > 0 && this.width <= 16) {
/*     */       
/* 775 */       if (this.height > 0 && this.height <= 16) {
/*     */         
/* 777 */         if (this.tiles.length != this.width * this.height) {
/*     */           
/* 779 */           Config.warn("Number of tiles does not equal width x height: " + path);
/* 780 */           return false;
/*     */         } 
/*     */ 
/*     */         
/* 784 */         return true;
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 789 */       Config.warn("Invalid height: " + path);
/* 790 */       return false;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 795 */     Config.warn("Invalid width: " + path);
/* 796 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean isValidFixed(String path) {
/* 802 */     if (this.tiles == null) {
/*     */       
/* 804 */       Config.warn("Tiles not defined: " + path);
/* 805 */       return false;
/*     */     } 
/* 807 */     if (this.tiles.length != 1) {
/*     */       
/* 809 */       Config.warn("Number of tiles should be 1 for method: fixed.");
/* 810 */       return false;
/*     */     } 
/*     */ 
/*     */     
/* 814 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean isValidTop(String path) {
/* 820 */     if (this.tiles == null)
/*     */     {
/* 822 */       this.tiles = parseTileNames("66");
/*     */     }
/*     */     
/* 825 */     if (this.tiles.length != 1) {
/*     */       
/* 827 */       Config.warn("Invalid tiles, must be exactly 1: " + path);
/* 828 */       return false;
/*     */     } 
/*     */ 
/*     */     
/* 832 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void updateIcons(TextureMap textureMap) {
/* 838 */     if (this.matchTiles != null)
/*     */     {
/* 840 */       this.matchTileIcons = registerIcons(this.matchTiles, textureMap);
/*     */     }
/*     */     
/* 843 */     if (this.tiles != null)
/*     */     {
/* 845 */       this.tileIcons = registerIcons(this.tiles, textureMap);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private static TextureAtlasSprite[] registerIcons(String[] tileNames, TextureMap textureMap) {
/* 851 */     if (tileNames == null)
/*     */     {
/* 853 */       return null;
/*     */     }
/*     */ 
/*     */     
/* 857 */     ArrayList<TextureAtlasSprite> iconList = new ArrayList();
/*     */     
/* 859 */     for (int icons = 0; icons < tileNames.length; icons++) {
/*     */       
/* 861 */       String iconName = tileNames[icons];
/* 862 */       ResourceLocation resLoc = new ResourceLocation(iconName);
/* 863 */       String domain = resLoc.getResourceDomain();
/* 864 */       String path = resLoc.getResourcePath();
/*     */       
/* 866 */       if (!path.contains("/"))
/*     */       {
/* 868 */         path = "textures/blocks/" + path;
/*     */       }
/*     */       
/* 871 */       String filePath = String.valueOf(path) + ".png";
/* 872 */       ResourceLocation locFile = new ResourceLocation(domain, filePath);
/* 873 */       boolean exists = Config.hasResource(locFile);
/*     */       
/* 875 */       if (!exists)
/*     */       {
/* 877 */         Config.warn("File not found: " + filePath);
/*     */       }
/*     */       
/* 880 */       String prefixTextures = "textures/";
/* 881 */       String pathSprite = path;
/*     */       
/* 883 */       if (path.startsWith(prefixTextures))
/*     */       {
/* 885 */         pathSprite = path.substring(prefixTextures.length());
/*     */       }
/*     */       
/* 888 */       ResourceLocation locSprite = new ResourceLocation(domain, pathSprite);
/* 889 */       TextureAtlasSprite icon = textureMap.func_174942_a(locSprite);
/* 890 */       iconList.add(icon);
/*     */     } 
/*     */     
/* 893 */     TextureAtlasSprite[] var15 = iconList.<TextureAtlasSprite>toArray(new TextureAtlasSprite[iconList.size()]);
/* 894 */     return var15;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean matchesBlockId(int blockId) {
/* 900 */     return Matches.blockId(blockId, this.matchBlocks);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean matchesBlock(int blockId, int metadata) {
/* 905 */     return !Matches.block(blockId, metadata, this.matchBlocks) ? false : Matches.metadata(metadata, this.metadatas);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean matchesIcon(TextureAtlasSprite icon) {
/* 910 */     return Matches.sprite(icon, this.matchTileIcons);
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 915 */     return "CTM name: " + this.name + ", basePath: " + this.basePath + ", matchBlocks: " + Config.arrayToString((Object[])this.matchBlocks) + ", matchTiles: " + Config.arrayToString((Object[])this.matchTiles);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean matchesBiome(BiomeGenBase biome) {
/* 920 */     return Matches.biome(biome, this.biomes);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getMetadataMax() {
/* 925 */     byte max = -1;
/* 926 */     int var4 = getMax(this.metadatas, max);
/*     */     
/* 928 */     if (this.matchBlocks != null)
/*     */     {
/* 930 */       for (int i = 0; i < this.matchBlocks.length; i++) {
/*     */         
/* 932 */         MatchBlock mb = this.matchBlocks[i];
/* 933 */         var4 = getMax(mb.getMetadatas(), var4);
/*     */       } 
/*     */     }
/*     */     
/* 937 */     return var4;
/*     */   }
/*     */ 
/*     */   
/*     */   private int getMax(int[] mds, int max) {
/* 942 */     if (mds == null)
/*     */     {
/* 944 */       return max;
/*     */     }
/*     */ 
/*     */     
/* 948 */     for (int i = 0; i < mds.length; i++) {
/*     */       
/* 950 */       int md = mds[i];
/*     */       
/* 952 */       if (md > max)
/*     */       {
/* 954 */         max = md;
/*     */       }
/*     */     } 
/*     */     
/* 958 */     return max;
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\optifine\ConnectedProperties.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */