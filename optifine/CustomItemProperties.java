/*     */ package optifine;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.LinkedHashMap;
/*     */ import java.util.Map;
/*     */ import java.util.Properties;
/*     */ import java.util.Set;
/*     */ import java.util.TreeSet;
/*     */ import net.minecraft.client.renderer.GlStateManager;
/*     */ import net.minecraft.client.renderer.block.model.BakedQuad;
/*     */ import net.minecraft.client.renderer.block.model.BlockPart;
/*     */ import net.minecraft.client.renderer.block.model.BlockPartFace;
/*     */ import net.minecraft.client.renderer.block.model.FaceBakery;
/*     */ import net.minecraft.client.renderer.block.model.ItemModelGenerator;
/*     */ import net.minecraft.client.renderer.block.model.ModelBlock;
/*     */ import net.minecraft.client.renderer.texture.ITextureObject;
/*     */ import net.minecraft.client.renderer.texture.TextureAtlasSprite;
/*     */ import net.minecraft.client.renderer.texture.TextureManager;
/*     */ import net.minecraft.client.renderer.texture.TextureMap;
/*     */ import net.minecraft.client.resources.model.IBakedModel;
/*     */ import net.minecraft.client.resources.model.ModelResourceLocation;
/*     */ import net.minecraft.client.resources.model.ModelRotation;
/*     */ import net.minecraft.client.resources.model.SimpleBakedModel;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemArmor;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ public class CustomItemProperties
/*     */ {
/*  35 */   public String name = null;
/*  36 */   public String basePath = null;
/*  37 */   public int type = 1;
/*  38 */   public int[] items = null;
/*  39 */   public String texture = null;
/*  40 */   public Map<String, String> mapTextures = null;
/*  41 */   public RangeListInt damage = null;
/*     */   public boolean damagePercent = false;
/*  43 */   public int damageMask = 0;
/*  44 */   public RangeListInt stackSize = null;
/*  45 */   public RangeListInt enchantmentIds = null;
/*  46 */   public RangeListInt enchantmentLevels = null;
/*  47 */   public NbtTagValue[] nbtTagValues = null;
/*  48 */   public int blend = 1;
/*  49 */   public float speed = 0.0F;
/*  50 */   public float rotation = 0.0F;
/*  51 */   public int layer = 0;
/*  52 */   public float duration = 1.0F;
/*  53 */   public int weight = 0;
/*  54 */   public ResourceLocation textureLocation = null;
/*  55 */   public Map mapTextureLocations = null;
/*  56 */   public TextureAtlasSprite sprite = null;
/*  57 */   public Map mapSprites = null;
/*  58 */   public IBakedModel model = null;
/*  59 */   public Map<String, IBakedModel> mapModels = null;
/*  60 */   private int textureWidth = 0;
/*  61 */   private int textureHeight = 0;
/*     */   
/*     */   public static final int TYPE_UNKNOWN = 0;
/*     */   public static final int TYPE_ITEM = 1;
/*     */   public static final int TYPE_ENCHANTMENT = 2;
/*     */   public static final int TYPE_ARMOR = 3;
/*     */   
/*     */   public CustomItemProperties(Properties props, String path) {
/*  69 */     this.name = parseName(path);
/*  70 */     this.basePath = parseBasePath(path);
/*  71 */     this.type = parseType(props.getProperty("type"));
/*  72 */     this.items = parseItems(props.getProperty("items"), props.getProperty("matchItems"));
/*  73 */     this.mapTextures = parseTextures(props, this.basePath);
/*  74 */     this.texture = parseTexture(props.getProperty("texture"), props.getProperty("tile"), props.getProperty("source"), path, this.basePath, this.type, this.mapTextures);
/*  75 */     String damageStr = props.getProperty("damage");
/*     */     
/*  77 */     if (damageStr != null) {
/*     */       
/*  79 */       this.damagePercent = damageStr.contains("%");
/*  80 */       damageStr.replace("%", "");
/*  81 */       this.damage = parseRangeListInt(damageStr);
/*  82 */       this.damageMask = parseInt(props.getProperty("damageMask"), 0);
/*     */     } 
/*     */     
/*  85 */     this.stackSize = parseRangeListInt(props.getProperty("stackSize"));
/*  86 */     this.enchantmentIds = parseRangeListInt(props.getProperty("enchantmentIDs"));
/*  87 */     this.enchantmentLevels = parseRangeListInt(props.getProperty("enchantmentLevels"));
/*  88 */     this.nbtTagValues = parseNbtTagValues(props);
/*  89 */     this.blend = Blender.parseBlend(props.getProperty("blend"));
/*  90 */     this.speed = parseFloat(props.getProperty("speed"), 0.0F);
/*  91 */     this.rotation = parseFloat(props.getProperty("rotation"), 0.0F);
/*  92 */     this.layer = parseInt(props.getProperty("layer"), 0);
/*  93 */     this.weight = parseInt(props.getProperty("weight"), 0);
/*  94 */     this.duration = parseFloat(props.getProperty("duration"), 1.0F);
/*     */   }
/*     */ 
/*     */   
/*     */   private static String parseName(String path) {
/*  99 */     String str = path;
/* 100 */     int pos = path.lastIndexOf('/');
/*     */     
/* 102 */     if (pos >= 0)
/*     */     {
/* 104 */       str = path.substring(pos + 1);
/*     */     }
/*     */     
/* 107 */     int pos2 = str.lastIndexOf('.');
/*     */     
/* 109 */     if (pos2 >= 0)
/*     */     {
/* 111 */       str = str.substring(0, pos2);
/*     */     }
/*     */     
/* 114 */     return str;
/*     */   }
/*     */ 
/*     */   
/*     */   private static String parseBasePath(String path) {
/* 119 */     int pos = path.lastIndexOf('/');
/* 120 */     return (pos < 0) ? "" : path.substring(0, pos);
/*     */   }
/*     */ 
/*     */   
/*     */   private int parseType(String str) {
/* 125 */     if (str == null)
/*     */     {
/* 127 */       return 1;
/*     */     }
/* 129 */     if (str.equals("item"))
/*     */     {
/* 131 */       return 1;
/*     */     }
/* 133 */     if (str.equals("enchantment"))
/*     */     {
/* 135 */       return 2;
/*     */     }
/* 137 */     if (str.equals("armor"))
/*     */     {
/* 139 */       return 3;
/*     */     }
/*     */ 
/*     */     
/* 143 */     Config.warn("Unknown method: " + str);
/* 144 */     return 0;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private int[] parseItems(String str, String str2) {
/* 150 */     if (str == null)
/*     */     {
/* 152 */       str = str2;
/*     */     }
/*     */     
/* 155 */     if (str == null)
/*     */     {
/* 157 */       return null;
/*     */     }
/*     */ 
/*     */     
/* 161 */     str = str.trim();
/* 162 */     TreeSet<Integer> setItemIds = new TreeSet();
/* 163 */     String[] tokens = Config.tokenize(str, " ");
/*     */ 
/*     */ 
/*     */     
/* 167 */     for (int integers = 0; integers < tokens.length; integers++) {
/*     */       
/* 169 */       String ints = tokens[integers];
/* 170 */       int j = Config.parseInt(ints, -1);
/*     */       
/* 172 */       if (j >= 0) {
/*     */         
/* 174 */         setItemIds.add(new Integer(j));
/*     */ 
/*     */         
/*     */         continue;
/*     */       } 
/*     */       
/* 180 */       if (ints.contains("-")) {
/*     */         
/* 182 */         String[] item = Config.tokenize(ints, "-");
/*     */         
/* 184 */         if (item.length == 2) {
/*     */           
/* 186 */           int id = Config.parseInt(item[0], -1);
/* 187 */           int val2 = Config.parseInt(item[1], -1);
/*     */           
/* 189 */           if (id >= 0 && val2 >= 0) {
/*     */             
/* 191 */             int min = Math.min(id, val2);
/* 192 */             int max = Math.max(id, val2);
/* 193 */             int x = min;
/*     */ 
/*     */ 
/*     */             
/* 197 */             while (x <= max) {
/*     */ 
/*     */ 
/*     */ 
/*     */               
/* 202 */               setItemIds.add(new Integer(x));
/* 203 */               x++;
/*     */             } 
/*     */             continue;
/*     */           } 
/*     */         } 
/*     */       } 
/* 209 */       Item var16 = Item.getByNameOrId(ints);
/*     */       
/* 211 */       if (var16 == null) {
/*     */         
/* 213 */         Config.warn("Item not found: " + ints);
/*     */       }
/*     */       else {
/*     */         
/* 217 */         int id = Item.getIdFromItem(var16);
/*     */         
/* 219 */         if (id < 0) {
/*     */           
/* 221 */           Config.warn("Item not found: " + ints);
/*     */         }
/*     */         else {
/*     */           
/* 225 */           setItemIds.add(new Integer(id));
/*     */         } 
/*     */       } 
/*     */       
/*     */       continue;
/*     */     } 
/* 231 */     Integer[] var14 = (Integer[])setItemIds.toArray((Object[])new Integer[setItemIds.size()]);
/* 232 */     int[] var15 = new int[var14.length];
/*     */     
/* 234 */     for (int i = 0; i < var15.length; i++)
/*     */     {
/* 236 */       var15[i] = var14[i].intValue();
/*     */     }
/*     */     
/* 239 */     return var15;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static String parseTexture(String texStr, String texStr2, String texStr3, String path, String basePath, int type, Map<String, String> mapTexs) {
/* 245 */     if (texStr == null)
/*     */     {
/* 247 */       texStr = texStr2;
/*     */     }
/*     */     
/* 250 */     if (texStr == null)
/*     */     {
/* 252 */       texStr = texStr3;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 257 */     if (texStr != null) {
/*     */       
/* 259 */       String str1 = ".png";
/*     */       
/* 261 */       if (texStr.endsWith(str1))
/*     */       {
/* 263 */         texStr = texStr.substring(0, texStr.length() - str1.length());
/*     */       }
/*     */       
/* 266 */       texStr = fixTextureName(texStr, basePath);
/* 267 */       return texStr;
/*     */     } 
/* 269 */     if (type == 3)
/*     */     {
/* 271 */       return null;
/*     */     }
/*     */ 
/*     */     
/* 275 */     if (mapTexs != null) {
/*     */       
/* 277 */       String str1 = mapTexs.get("texture.bow_standby");
/*     */       
/* 279 */       if (str1 != null)
/*     */       {
/* 281 */         return str1;
/*     */       }
/*     */     } 
/*     */     
/* 285 */     String str = path;
/* 286 */     int pos = path.lastIndexOf('/');
/*     */     
/* 288 */     if (pos >= 0)
/*     */     {
/* 290 */       str = path.substring(pos + 1);
/*     */     }
/*     */     
/* 293 */     int pos2 = str.lastIndexOf('.');
/*     */     
/* 295 */     if (pos2 >= 0)
/*     */     {
/* 297 */       str = str.substring(0, pos2);
/*     */     }
/*     */     
/* 300 */     str = fixTextureName(str, basePath);
/* 301 */     return str;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static Map parseTextures(Properties props, String basePath) {
/* 307 */     String prefix = "texture.";
/* 308 */     Map mapProps = getMatchingProperties(props, prefix);
/*     */     
/* 310 */     if (mapProps.size() <= 0)
/*     */     {
/* 312 */       return null;
/*     */     }
/*     */ 
/*     */     
/* 316 */     Set keySet = mapProps.keySet();
/* 317 */     LinkedHashMap<Object, Object> mapTex = new LinkedHashMap<>();
/* 318 */     Iterator<String> it = keySet.iterator();
/*     */     
/* 320 */     while (it.hasNext()) {
/*     */       
/* 322 */       String key = it.next();
/* 323 */       String val = (String)mapProps.get(key);
/* 324 */       val = fixTextureName(val, basePath);
/* 325 */       mapTex.put(key, val);
/*     */     } 
/*     */     
/* 328 */     return mapTex;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static String fixTextureName(String iconName, String basePath) {
/* 334 */     iconName = TextureUtils.fixResourcePath(iconName, basePath);
/*     */     
/* 336 */     if (!iconName.startsWith(basePath) && !iconName.startsWith("textures/") && !iconName.startsWith("mcpatcher/"))
/*     */     {
/* 338 */       iconName = String.valueOf(basePath) + "/" + iconName;
/*     */     }
/*     */     
/* 341 */     if (iconName.endsWith(".png"))
/*     */     {
/* 343 */       iconName = iconName.substring(0, iconName.length() - 4);
/*     */     }
/*     */     
/* 346 */     String pathBlocks = "textures/blocks/";
/*     */     
/* 348 */     if (iconName.startsWith(pathBlocks))
/*     */     {
/* 350 */       iconName = iconName.substring(pathBlocks.length());
/*     */     }
/*     */     
/* 353 */     if (iconName.startsWith("/"))
/*     */     {
/* 355 */       iconName = iconName.substring(1);
/*     */     }
/*     */     
/* 358 */     return iconName;
/*     */   }
/*     */ 
/*     */   
/*     */   private int parseInt(String str, int defVal) {
/* 363 */     if (str == null)
/*     */     {
/* 365 */       return defVal;
/*     */     }
/*     */ 
/*     */     
/* 369 */     str = str.trim();
/* 370 */     int val = Config.parseInt(str, -2147483648);
/*     */     
/* 372 */     if (val == Integer.MIN_VALUE) {
/*     */       
/* 374 */       Config.warn("Invalid integer: " + str);
/* 375 */       return defVal;
/*     */     } 
/*     */ 
/*     */     
/* 379 */     return val;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private float parseFloat(String str, float defVal) {
/* 386 */     if (str == null)
/*     */     {
/* 388 */       return defVal;
/*     */     }
/*     */ 
/*     */     
/* 392 */     str = str.trim();
/* 393 */     float val = Config.parseFloat(str, Float.MIN_VALUE);
/*     */     
/* 395 */     if (val == Float.MIN_VALUE) {
/*     */       
/* 397 */       Config.warn("Invalid float: " + str);
/* 398 */       return defVal;
/*     */     } 
/*     */ 
/*     */     
/* 402 */     return val;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private RangeListInt parseRangeListInt(String str) {
/* 409 */     if (str == null)
/*     */     {
/* 411 */       return null;
/*     */     }
/*     */ 
/*     */     
/* 415 */     String[] tokens = Config.tokenize(str, " ");
/* 416 */     RangeListInt rangeList = new RangeListInt();
/*     */     
/* 418 */     for (int i = 0; i < tokens.length; i++) {
/*     */       
/* 420 */       String token = tokens[i];
/* 421 */       RangeInt range = parseRangeInt(token);
/*     */       
/* 423 */       if (range == null) {
/*     */         
/* 425 */         Config.warn("Invalid range list: " + str);
/* 426 */         return null;
/*     */       } 
/*     */       
/* 429 */       rangeList.addRange(range);
/*     */     } 
/*     */     
/* 432 */     return rangeList;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private RangeInt parseRangeInt(String str) {
/* 438 */     if (str == null)
/*     */     {
/* 440 */       return null;
/*     */     }
/*     */ 
/*     */     
/* 444 */     str = str.trim();
/* 445 */     int countMinus = str.length() - str.replace("-", "").length();
/*     */     
/* 447 */     if (countMinus > 1) {
/*     */       
/* 449 */       Config.warn("Invalid range: " + str);
/* 450 */       return null;
/*     */     } 
/*     */ 
/*     */     
/* 454 */     String[] tokens = Config.tokenize(str, "- ");
/* 455 */     int[] vals = new int[tokens.length];
/*     */     
/*     */     int min;
/* 458 */     for (min = 0; min < tokens.length; min++) {
/*     */       
/* 460 */       String max = tokens[min];
/* 461 */       int val = Config.parseInt(max, -1);
/*     */       
/* 463 */       if (val < 0) {
/*     */         
/* 465 */         Config.warn("Invalid range: " + str);
/* 466 */         return null;
/*     */       } 
/*     */       
/* 469 */       vals[min] = val;
/*     */     } 
/*     */     
/* 472 */     if (vals.length == 1) {
/*     */       
/* 474 */       min = vals[0];
/*     */       
/* 476 */       if (str.startsWith("-"))
/*     */       {
/* 478 */         return new RangeInt(0, min);
/*     */       }
/* 480 */       if (str.endsWith("-"))
/*     */       {
/* 482 */         return new RangeInt(min, 255);
/*     */       }
/*     */ 
/*     */       
/* 486 */       return new RangeInt(min, min);
/*     */     } 
/*     */     
/* 489 */     if (vals.length == 2) {
/*     */       
/* 491 */       min = Math.min(vals[0], vals[1]);
/* 492 */       int var8 = Math.max(vals[0], vals[1]);
/* 493 */       return new RangeInt(min, var8);
/*     */     } 
/*     */ 
/*     */     
/* 497 */     Config.warn("Invalid range: " + str);
/* 498 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private NbtTagValue[] parseNbtTagValues(Properties props) {
/* 506 */     String PREFIX_NBT = "nbt.";
/* 507 */     Map mapNbt = getMatchingProperties(props, PREFIX_NBT);
/*     */     
/* 509 */     if (mapNbt.size() <= 0)
/*     */     {
/* 511 */       return null;
/*     */     }
/*     */ 
/*     */     
/* 515 */     ArrayList<NbtTagValue> listNbts = new ArrayList();
/* 516 */     Set keySet = mapNbt.keySet();
/* 517 */     Iterator<String> nbts = keySet.iterator();
/*     */     
/* 519 */     while (nbts.hasNext()) {
/*     */       
/* 521 */       String key = nbts.next();
/* 522 */       String val = (String)mapNbt.get(key);
/* 523 */       String id = key.substring(PREFIX_NBT.length());
/* 524 */       NbtTagValue nbt = new NbtTagValue(id, val);
/* 525 */       listNbts.add(nbt);
/*     */     } 
/*     */     
/* 528 */     NbtTagValue[] nbts1 = listNbts.<NbtTagValue>toArray(new NbtTagValue[listNbts.size()]);
/* 529 */     return nbts1;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static Map getMatchingProperties(Properties props, String keyPrefix) {
/* 535 */     LinkedHashMap<Object, Object> map = new LinkedHashMap<>();
/* 536 */     Set keySet = props.keySet();
/* 537 */     Iterator<String> it = keySet.iterator();
/*     */     
/* 539 */     while (it.hasNext()) {
/*     */       
/* 541 */       String key = it.next();
/* 542 */       String val = props.getProperty(key);
/*     */       
/* 544 */       if (key.startsWith(keyPrefix))
/*     */       {
/* 546 */         map.put(key, val);
/*     */       }
/*     */     } 
/*     */     
/* 550 */     return map;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isValid(String path) {
/* 555 */     if (this.name != null && this.name.length() > 0) {
/*     */       
/* 557 */       if (this.basePath == null) {
/*     */         
/* 559 */         Config.warn("No base path found: " + path);
/* 560 */         return false;
/*     */       } 
/* 562 */       if (this.type == 0) {
/*     */         
/* 564 */         Config.warn("No type defined: " + path);
/* 565 */         return false;
/*     */       } 
/* 567 */       if ((this.type == 1 || this.type == 3) && this.items == null) {
/*     */         
/* 569 */         Config.warn("No items defined: " + path);
/* 570 */         return false;
/*     */       } 
/* 572 */       if (this.texture == null && this.mapTextures == null) {
/*     */         
/* 574 */         Config.warn("No texture specified: " + path);
/* 575 */         return false;
/*     */       } 
/* 577 */       if (this.type == 2 && this.enchantmentIds == null) {
/*     */         
/* 579 */         Config.warn("No enchantmentIDs specified: " + path);
/* 580 */         return false;
/*     */       } 
/*     */ 
/*     */       
/* 584 */       return true;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 589 */     Config.warn("No name found: " + path);
/* 590 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void updateIcons(TextureMap textureMap) {
/* 596 */     if (this.texture != null) {
/*     */       
/* 598 */       this.textureLocation = getTextureLocation(this.texture);
/*     */       
/* 600 */       if (this.type == 1) {
/*     */         
/* 602 */         ResourceLocation keySet = getSpriteLocation(this.textureLocation);
/* 603 */         this.sprite = textureMap.func_174942_a(keySet);
/*     */       } 
/*     */     } 
/*     */     
/* 607 */     if (this.mapTextures != null) {
/*     */       
/* 609 */       this.mapTextureLocations = new HashMap<>();
/* 610 */       this.mapSprites = new HashMap<>();
/* 611 */       Set<String> keySet1 = this.mapTextures.keySet();
/* 612 */       Iterator<String> it = keySet1.iterator();
/*     */       
/* 614 */       while (it.hasNext()) {
/*     */         
/* 616 */         String key = it.next();
/* 617 */         String val = this.mapTextures.get(key);
/* 618 */         ResourceLocation locTex = getTextureLocation(val);
/* 619 */         this.mapTextureLocations.put(key, locTex);
/*     */         
/* 621 */         if (this.type == 1) {
/*     */           
/* 623 */           ResourceLocation locSprite = getSpriteLocation(locTex);
/* 624 */           TextureAtlasSprite icon = textureMap.func_174942_a(locSprite);
/* 625 */           this.mapSprites.put(key, icon);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private ResourceLocation getTextureLocation(String texName) {
/* 633 */     if (texName == null)
/*     */     {
/* 635 */       return null;
/*     */     }
/*     */ 
/*     */     
/* 639 */     ResourceLocation resLoc = new ResourceLocation(texName);
/* 640 */     String domain = resLoc.getResourceDomain();
/* 641 */     String path = resLoc.getResourcePath();
/*     */     
/* 643 */     if (!path.contains("/"))
/*     */     {
/* 645 */       path = "textures/blocks/" + path;
/*     */     }
/*     */     
/* 648 */     String filePath = String.valueOf(path) + ".png";
/* 649 */     ResourceLocation locFile = new ResourceLocation(domain, filePath);
/* 650 */     boolean exists = Config.hasResource(locFile);
/*     */     
/* 652 */     if (!exists)
/*     */     {
/* 654 */       Config.warn("File not found: " + filePath);
/*     */     }
/*     */     
/* 657 */     return locFile;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private ResourceLocation getSpriteLocation(ResourceLocation resLoc) {
/* 663 */     String pathTex = resLoc.getResourcePath();
/* 664 */     pathTex = StrUtils.removePrefix(pathTex, "textures/");
/* 665 */     pathTex = StrUtils.removeSuffix(pathTex, ".png");
/* 666 */     ResourceLocation locTex = new ResourceLocation(resLoc.getResourceDomain(), pathTex);
/* 667 */     return locTex;
/*     */   }
/*     */ 
/*     */   
/*     */   public void updateModel(TextureMap textureMap, ItemModelGenerator itemModelGenerator) {
/* 672 */     String[] textures = getModelTextures();
/* 673 */     boolean useTint = isUseTint();
/* 674 */     this.model = makeBakedModel(textureMap, itemModelGenerator, textures, useTint);
/*     */     
/* 676 */     if (this.type == 1 && this.mapTextures != null) {
/*     */       
/* 678 */       Set<String> keySet = this.mapTextures.keySet();
/* 679 */       Iterator<String> it = keySet.iterator();
/*     */       
/* 681 */       while (it.hasNext()) {
/*     */         
/* 683 */         String key = it.next();
/* 684 */         String tex = this.mapTextures.get(key);
/* 685 */         String path = StrUtils.removePrefix(key, "texture.");
/*     */         
/* 687 */         if (path.startsWith("bow") || path.startsWith("fishing_rod")) {
/*     */           
/* 689 */           String[] texNames = { tex };
/* 690 */           IBakedModel modelTex = makeBakedModel(textureMap, itemModelGenerator, texNames, useTint);
/*     */           
/* 692 */           if (this.mapModels == null)
/*     */           {
/* 694 */             this.mapModels = new HashMap<>();
/*     */           }
/*     */           
/* 697 */           this.mapModels.put(path, modelTex);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean isUseTint() {
/* 705 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   private static IBakedModel makeBakedModel(TextureMap textureMap, ItemModelGenerator itemModelGenerator, String[] textures, boolean useTint) {
/* 710 */     ModelBlock modelBlockBase = makeModelBlock(textures);
/* 711 */     ModelBlock modelBlock = itemModelGenerator.func_178392_a(textureMap, modelBlockBase);
/* 712 */     IBakedModel model = bakeModel(textureMap, modelBlock, useTint);
/* 713 */     return model;
/*     */   }
/*     */ 
/*     */   
/*     */   private String[] getModelTextures() {
/* 718 */     if (this.type == 1 && this.items.length == 1) {
/*     */       
/* 720 */       Item item = Item.getItemById(this.items[0]);
/*     */ 
/*     */ 
/*     */       
/* 724 */       if (item == Items.potionitem && this.damage != null && this.damage.getCountRanges() > 0) {
/*     */         
/* 726 */         RangeInt itemArmor1 = this.damage.getRange(0);
/* 727 */         int material1 = itemArmor1.getMin();
/* 728 */         boolean type1 = ((material1 & 0x4000) != 0);
/* 729 */         String key = getMapTexture(this.mapTextures, "texture.potion_overlay", "items/potion_overlay");
/* 730 */         String texMain = null;
/*     */         
/* 732 */         if (type1) {
/*     */           
/* 734 */           texMain = getMapTexture(this.mapTextures, "texture.potion_bottle_splash", "items/potion_bottle_splash");
/*     */         }
/*     */         else {
/*     */           
/* 738 */           texMain = getMapTexture(this.mapTextures, "texture.potion_bottle_drinkable", "items/potion_bottle_drinkable");
/*     */         } 
/*     */         
/* 741 */         return new String[] { key, texMain };
/*     */       } 
/*     */       
/* 744 */       if (item instanceof ItemArmor) {
/*     */         
/* 746 */         ItemArmor itemArmor = (ItemArmor)item;
/*     */         
/* 748 */         if (itemArmor.getArmorMaterial() == ItemArmor.ArmorMaterial.LEATHER) {
/*     */           
/* 750 */           String material = "leather";
/* 751 */           String type = "helmet";
/*     */           
/* 753 */           if (itemArmor.armorType == 0)
/*     */           {
/* 755 */             type = "helmet";
/*     */           }
/*     */           
/* 758 */           if (itemArmor.armorType == 1)
/*     */           {
/* 760 */             type = "chestplate";
/*     */           }
/*     */           
/* 763 */           if (itemArmor.armorType == 2)
/*     */           {
/* 765 */             type = "leggings";
/*     */           }
/*     */           
/* 768 */           if (itemArmor.armorType == 3)
/*     */           {
/* 770 */             type = "boots";
/*     */           }
/*     */           
/* 773 */           String key = String.valueOf(material) + "_" + type;
/* 774 */           String texMain = getMapTexture(this.mapTextures, "texture." + key, "items/" + key);
/* 775 */           String texOverlay = getMapTexture(this.mapTextures, "texture." + key + "_overlay", "items/" + key + "_overlay");
/* 776 */           return new String[] { texMain, texOverlay };
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 781 */     return new String[] { this.texture };
/*     */   }
/*     */ 
/*     */   
/*     */   private String getMapTexture(Map<String, String> map, String key, String def) {
/* 786 */     if (map == null)
/*     */     {
/* 788 */       return def;
/*     */     }
/*     */ 
/*     */     
/* 792 */     String str = map.get(key);
/* 793 */     return (str == null) ? def : str;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static ModelBlock makeModelBlock(String[] modelTextures) {
/* 799 */     StringBuffer sb = new StringBuffer();
/* 800 */     sb.append("{\"parent\": \"builtin/generated\",\"textures\": {");
/*     */     
/* 802 */     for (int modelStr = 0; modelStr < modelTextures.length; modelStr++) {
/*     */       
/* 804 */       String model = modelTextures[modelStr];
/*     */       
/* 806 */       if (modelStr > 0)
/*     */       {
/* 808 */         sb.append(", ");
/*     */       }
/*     */       
/* 811 */       sb.append("\"layer" + modelStr + "\": \"" + model + "\"");
/*     */     } 
/*     */     
/* 814 */     sb.append("}}");
/* 815 */     String var4 = sb.toString();
/* 816 */     ModelBlock var5 = ModelBlock.deserialize(var4);
/* 817 */     return var5;
/*     */   }
/*     */ 
/*     */   
/*     */   private static IBakedModel bakeModel(TextureMap textureMap, ModelBlock modelBlockIn, boolean useTint) {
/* 822 */     ModelRotation modelRotationIn = ModelRotation.X0_Y0;
/* 823 */     boolean uvLocked = false;
/* 824 */     TextureAtlasSprite var4 = textureMap.getSpriteSafe(modelBlockIn.resolveTextureName("particle"));
/* 825 */     SimpleBakedModel.Builder var5 = (new SimpleBakedModel.Builder(modelBlockIn)).func_177646_a(var4);
/* 826 */     Iterator<BlockPart> var6 = modelBlockIn.getElements().iterator();
/*     */     
/* 828 */     while (var6.hasNext()) {
/*     */       
/* 830 */       BlockPart var7 = var6.next();
/* 831 */       Iterator<EnumFacing> var8 = var7.field_178240_c.keySet().iterator();
/*     */       
/* 833 */       while (var8.hasNext()) {
/*     */         
/* 835 */         EnumFacing var9 = var8.next();
/* 836 */         BlockPartFace var10 = (BlockPartFace)var7.field_178240_c.get(var9);
/*     */         
/* 838 */         if (!useTint)
/*     */         {
/* 840 */           var10 = new BlockPartFace(var10.field_178244_b, -1, var10.field_178242_d, var10.field_178243_e);
/*     */         }
/*     */         
/* 843 */         TextureAtlasSprite var11 = textureMap.getSpriteSafe(modelBlockIn.resolveTextureName(var10.field_178242_d));
/* 844 */         BakedQuad quad = makeBakedQuad(var7, var10, var11, var9, modelRotationIn, uvLocked);
/*     */         
/* 846 */         if (var10.field_178244_b == null) {
/*     */           
/* 848 */           var5.func_177648_a(quad);
/*     */           
/*     */           continue;
/*     */         } 
/* 852 */         var5.func_177650_a(modelRotationIn.func_177523_a(var10.field_178244_b), quad);
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 857 */     return var5.func_177645_b();
/*     */   }
/*     */ 
/*     */   
/*     */   private static BakedQuad makeBakedQuad(BlockPart blockPart, BlockPartFace blockPartFace, TextureAtlasSprite textureAtlasSprite, EnumFacing enumFacing, ModelRotation modelRotation, boolean uvLocked) {
/* 862 */     FaceBakery faceBakery = new FaceBakery();
/* 863 */     return faceBakery.func_178414_a(blockPart.field_178241_a, blockPart.field_178239_b, blockPartFace, textureAtlasSprite, enumFacing, modelRotation, blockPart.field_178237_d, uvLocked, blockPart.field_178238_e);
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 868 */     return this.basePath + "/" + this.name + ", type: " + this.type + ", items: [" + Config.arrayToString(this.items) + "], textture: " + this.texture;
/*     */   }
/*     */ 
/*     */   
/*     */   public float getTextureWidth(TextureManager textureManager) {
/* 873 */     if (this.textureWidth <= 0) {
/*     */       
/* 875 */       if (this.textureLocation != null) {
/*     */         
/* 877 */         ITextureObject tex = textureManager.getTexture(this.textureLocation);
/* 878 */         int texId = tex.getGlTextureId();
/* 879 */         int prevTexId = GlStateManager.getBoundTexture();
/* 880 */         GlStateManager.func_179144_i(texId);
/* 881 */         this.textureWidth = GL11.glGetTexLevelParameteri(3553, 0, 4096);
/* 882 */         GlStateManager.func_179144_i(prevTexId);
/*     */       } 
/*     */       
/* 885 */       if (this.textureWidth <= 0)
/*     */       {
/* 887 */         this.textureWidth = 16;
/*     */       }
/*     */     } 
/*     */     
/* 891 */     return this.textureWidth;
/*     */   }
/*     */ 
/*     */   
/*     */   public float getTextureHeight(TextureManager textureManager) {
/* 896 */     if (this.textureHeight <= 0) {
/*     */       
/* 898 */       if (this.textureLocation != null) {
/*     */         
/* 900 */         ITextureObject tex = textureManager.getTexture(this.textureLocation);
/* 901 */         int texId = tex.getGlTextureId();
/* 902 */         int prevTexId = GlStateManager.getBoundTexture();
/* 903 */         GlStateManager.func_179144_i(texId);
/* 904 */         this.textureHeight = GL11.glGetTexLevelParameteri(3553, 0, 4097);
/* 905 */         GlStateManager.func_179144_i(prevTexId);
/*     */       } 
/*     */       
/* 908 */       if (this.textureHeight <= 0)
/*     */       {
/* 910 */         this.textureHeight = 16;
/*     */       }
/*     */     } 
/*     */     
/* 914 */     return this.textureHeight;
/*     */   }
/*     */ 
/*     */   
/*     */   public IBakedModel getModel(ModelResourceLocation modelLocation) {
/* 919 */     if (modelLocation != null && this.mapTextures != null) {
/*     */       
/* 921 */       String modelPath = modelLocation.getResourcePath();
/*     */       
/* 923 */       if (this.mapModels != null) {
/*     */         
/* 925 */         IBakedModel customModel = this.mapModels.get(modelPath);
/*     */         
/* 927 */         if (customModel != null)
/*     */         {
/* 929 */           return customModel;
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 934 */     return this.model;
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\optifine\CustomItemProperties.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */