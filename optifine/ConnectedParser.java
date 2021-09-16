/*     */ package optifine;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.Collection;
/*     */ import java.util.EnumSet;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.BlockDoublePlant;
/*     */ import net.minecraft.block.properties.IProperty;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.world.biome.BiomeGenBase;
/*     */ 
/*     */ public class ConnectedParser
/*     */ {
/*  22 */   private String context = null;
/*  23 */   private static final MatchBlock[] NO_MATCH_BLOCKS = new MatchBlock[0];
/*     */ 
/*     */   
/*     */   public ConnectedParser(String context) {
/*  27 */     this.context = context;
/*     */   }
/*     */ 
/*     */   
/*     */   public String parseName(String path) {
/*  32 */     String str = path;
/*  33 */     int pos = path.lastIndexOf('/');
/*     */     
/*  35 */     if (pos >= 0)
/*     */     {
/*  37 */       str = path.substring(pos + 1);
/*     */     }
/*     */     
/*  40 */     int pos2 = str.lastIndexOf('.');
/*     */     
/*  42 */     if (pos2 >= 0)
/*     */     {
/*  44 */       str = str.substring(0, pos2);
/*     */     }
/*     */     
/*  47 */     return str;
/*     */   }
/*     */ 
/*     */   
/*     */   public String parseBasePath(String path) {
/*  52 */     int pos = path.lastIndexOf('/');
/*  53 */     return (pos < 0) ? "" : path.substring(0, pos);
/*     */   }
/*     */ 
/*     */   
/*     */   public MatchBlock[] parseMatchBlocks(String propMatchBlocks) {
/*  58 */     if (propMatchBlocks == null)
/*     */     {
/*  60 */       return null;
/*     */     }
/*     */ 
/*     */     
/*  64 */     ArrayList list = new ArrayList();
/*  65 */     String[] blockStrs = Config.tokenize(propMatchBlocks, " ");
/*     */     
/*  67 */     for (int mbs = 0; mbs < blockStrs.length; mbs++) {
/*     */       
/*  69 */       String blockStr = blockStrs[mbs];
/*  70 */       MatchBlock[] mbs1 = parseMatchBlock(blockStr);
/*     */       
/*  72 */       if (mbs1 == null)
/*     */       {
/*  74 */         return NO_MATCH_BLOCKS;
/*     */       }
/*     */       
/*  77 */       list.addAll(Arrays.asList(mbs1));
/*     */     } 
/*     */     
/*  80 */     MatchBlock[] var7 = (MatchBlock[])list.toArray((Object[])new MatchBlock[list.size()]);
/*  81 */     return var7;
/*     */   }
/*     */ 
/*     */   
/*     */   public MatchBlock[] parseMatchBlock(String blockStr) {
/*     */     byte var14;
/*  87 */     if (blockStr == null)
/*     */     {
/*  89 */       return null;
/*     */     }
/*     */ 
/*     */     
/*  93 */     blockStr = blockStr.trim();
/*     */     
/*  95 */     if (blockStr.length() <= 0)
/*     */     {
/*  97 */       return null;
/*     */     }
/*     */ 
/*     */     
/* 101 */     String[] parts = Config.tokenize(blockStr, ":");
/* 102 */     String domain = "minecraft";
/* 103 */     boolean blockIndex = false;
/*     */ 
/*     */     
/* 106 */     if (parts.length > 1 && isFullBlockName(parts)) {
/*     */       
/* 108 */       domain = parts[0];
/* 109 */       var14 = 1;
/*     */     }
/*     */     else {
/*     */       
/* 113 */       domain = "minecraft";
/* 114 */       var14 = 0;
/*     */     } 
/*     */     
/* 117 */     String blockPart = parts[var14];
/* 118 */     String[] params = Arrays.<String>copyOfRange(parts, var14 + 1, parts.length);
/* 119 */     Block[] blocks = parseBlockPart(domain, blockPart);
/*     */     
/* 121 */     if (blocks == null)
/*     */     {
/* 123 */       return null;
/*     */     }
/*     */ 
/*     */     
/* 127 */     MatchBlock[] datas = new MatchBlock[blocks.length];
/*     */     
/* 129 */     for (int i = 0; i < blocks.length; i++) {
/*     */       
/* 131 */       Block block = blocks[i];
/* 132 */       int blockId = Block.getIdFromBlock(block);
/* 133 */       int[] metadatas = null;
/*     */       
/* 135 */       if (params.length > 0) {
/*     */         
/* 137 */         metadatas = parseBlockMetadatas(block, params);
/*     */         
/* 139 */         if (metadatas == null)
/*     */         {
/* 141 */           return null;
/*     */         }
/*     */       } 
/*     */       
/* 145 */       MatchBlock bd = new MatchBlock(blockId, metadatas);
/* 146 */       datas[i] = bd;
/*     */     } 
/*     */     
/* 149 */     return datas;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isFullBlockName(String[] parts) {
/* 157 */     if (parts.length < 2)
/*     */     {
/* 159 */       return false;
/*     */     }
/*     */ 
/*     */     
/* 163 */     String part1 = parts[1];
/* 164 */     return (part1.length() < 1) ? false : (startsWithDigit(part1) ? false : (!part1.contains("=")));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean startsWithDigit(String str) {
/* 170 */     if (str == null)
/*     */     {
/* 172 */       return false;
/*     */     }
/* 174 */     if (str.length() < 1)
/*     */     {
/* 176 */       return false;
/*     */     }
/*     */ 
/*     */     
/* 180 */     char ch = str.charAt(0);
/* 181 */     return Character.isDigit(ch);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Block[] parseBlockPart(String domain, String blockPart) {
/* 187 */     if (startsWithDigit(blockPart)) {
/*     */       
/* 189 */       int[] var8 = parseIntList(blockPart);
/*     */       
/* 191 */       if (var8 == null)
/*     */       {
/* 193 */         return null;
/*     */       }
/*     */ 
/*     */       
/* 197 */       Block[] var9 = new Block[var8.length];
/*     */       
/* 199 */       for (int var10 = 0; var10 < var8.length; var10++) {
/*     */         
/* 201 */         int id = var8[var10];
/* 202 */         Block block1 = Block.getBlockById(id);
/*     */         
/* 204 */         if (block1 == null) {
/*     */           
/* 206 */           warn("Block not found for id: " + id);
/* 207 */           return null;
/*     */         } 
/*     */         
/* 210 */         var9[var10] = block1;
/*     */       } 
/*     */       
/* 213 */       return var9;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 218 */     String fullName = String.valueOf(domain) + ":" + blockPart;
/* 219 */     Block block = Block.getBlockFromName(fullName);
/*     */     
/* 221 */     if (block == null) {
/*     */       
/* 223 */       warn("Block not found for name: " + fullName);
/* 224 */       return null;
/*     */     } 
/*     */ 
/*     */     
/* 228 */     Block[] blocks = { block };
/* 229 */     return blocks;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int[] parseBlockMetadatas(Block block, String[] params) {
/* 236 */     if (params.length <= 0)
/*     */     {
/* 238 */       return null;
/*     */     }
/*     */ 
/*     */     
/* 242 */     String param0 = params[0];
/*     */     
/* 244 */     if (startsWithDigit(param0)) {
/*     */       
/* 246 */       int[] var19 = parseIntList(param0);
/* 247 */       return var19;
/*     */     } 
/*     */ 
/*     */     
/* 251 */     IBlockState stateDefault = block.getDefaultState();
/* 252 */     Collection properties = stateDefault.getPropertyNames();
/* 253 */     HashMap<Object, Object> mapPropValues = new HashMap<>();
/*     */     
/* 255 */     for (int listMetadatas = 0; listMetadatas < params.length; listMetadatas++) {
/*     */       
/* 257 */       String metadatas = params[listMetadatas];
/*     */       
/* 259 */       if (metadatas.length() > 0) {
/*     */         
/* 261 */         String[] i = Config.tokenize(metadatas, "=");
/*     */         
/* 263 */         if (i.length != 2) {
/*     */           
/* 265 */           warn("Invalid block property: " + metadatas);
/* 266 */           return null;
/*     */         } 
/*     */         
/* 269 */         String e = i[0];
/* 270 */         String valStr = i[1];
/* 271 */         IProperty prop = ConnectedProperties.getProperty(e, properties);
/*     */         
/* 273 */         if (prop == null) {
/*     */           
/* 275 */           warn("Property not found: " + e + ", block: " + block);
/* 276 */           return null;
/*     */         } 
/*     */         
/* 279 */         Object list = mapPropValues.get(e);
/*     */         
/* 281 */         if (list == null) {
/*     */           
/* 283 */           list = new ArrayList();
/* 284 */           mapPropValues.put(prop, list);
/*     */         } 
/*     */         
/* 287 */         String[] vals = Config.tokenize(valStr, ",");
/*     */         
/* 289 */         for (int v = 0; v < vals.length; v++) {
/*     */           
/* 291 */           String val = vals[v];
/* 292 */           Comparable propVal = parsePropertyValue(prop, val);
/*     */           
/* 294 */           if (propVal == null) {
/*     */             
/* 296 */             warn("Property value not found: " + val + ", property: " + e + ", block: " + block);
/* 297 */             return null;
/*     */           } 
/*     */           
/* 300 */           ((List<Comparable>)list).add(propVal);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 305 */     if (mapPropValues.isEmpty())
/*     */     {
/* 307 */       return null;
/*     */     }
/*     */ 
/*     */     
/* 311 */     ArrayList<Integer> var20 = new ArrayList();
/*     */ 
/*     */     
/* 314 */     for (int var21 = 0; var21 < 16; var21++) {
/*     */       
/* 316 */       int i = var21;
/*     */ 
/*     */       
/*     */       try {
/* 320 */         IBlockState var24 = getStateFromMeta(block, i);
/*     */         
/* 322 */         if (matchState(var24, (Map)mapPropValues))
/*     */         {
/* 324 */           var20.add(Integer.valueOf(i));
/*     */         }
/*     */       }
/* 327 */       catch (IllegalArgumentException illegalArgumentException) {}
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 333 */     if (var20.size() == 16)
/*     */     {
/* 335 */       return null;
/*     */     }
/*     */ 
/*     */     
/* 339 */     int[] var22 = new int[var20.size()];
/*     */     
/* 341 */     for (int var23 = 0; var23 < var22.length; var23++)
/*     */     {
/* 343 */       var22[var23] = ((Integer)var20.get(var23)).intValue();
/*     */     }
/*     */     
/* 346 */     return var22;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private IBlockState getStateFromMeta(Block block, int md) {
/*     */     try {
/* 357 */       IBlockState e = block.getStateFromMeta(md);
/*     */       
/* 359 */       if (block == Blocks.double_plant && md > 7) {
/*     */         
/* 361 */         IBlockState bsLow = block.getStateFromMeta(md & 0x7);
/* 362 */         e = e.withProperty((IProperty)BlockDoublePlant.VARIANT_PROP, bsLow.getValue((IProperty)BlockDoublePlant.VARIANT_PROP));
/*     */       } 
/*     */       
/* 365 */       return e;
/*     */     }
/* 367 */     catch (IllegalArgumentException var5) {
/*     */       
/* 369 */       return block.getDefaultState();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static Comparable parsePropertyValue(IProperty prop, String valStr) {
/* 375 */     Class valueClass = prop.getValueClass();
/* 376 */     Comparable valueObj = parseValue(valStr, valueClass);
/*     */     
/* 378 */     if (valueObj == null) {
/*     */       
/* 380 */       Collection propertyValues = prop.getAllowedValues();
/* 381 */       valueObj = getPropertyValue(valStr, propertyValues);
/*     */     } 
/*     */     
/* 384 */     return valueObj;
/*     */   }
/*     */   
/*     */   public static Comparable getPropertyValue(String value, Collection propertyValues) {
/*     */     Comparable obj;
/* 389 */     Iterator<Comparable> it = propertyValues.iterator();
/*     */ 
/*     */ 
/*     */     
/*     */     do {
/* 394 */       if (!it.hasNext())
/*     */       {
/* 396 */         return null;
/*     */       }
/*     */       
/* 399 */       obj = it.next();
/*     */     }
/* 401 */     while (!String.valueOf(obj).equals(value));
/*     */     
/* 403 */     return obj;
/*     */   }
/*     */ 
/*     */   
/*     */   public static Comparable parseValue(String str, Class<String> cls) {
/* 408 */     return (cls == String.class) ? str : ((cls == Boolean.class) ? Boolean.valueOf(str) : Double.valueOf((cls == Float.class) ? Float.valueOf(str).floatValue() : ((cls == Double.class) ? Double.valueOf(str).doubleValue() : ((cls == Integer.class) ? Integer.valueOf(str).intValue() : ((cls == Long.class) ? Long.valueOf(str) : null).longValue()))));
/*     */   }
/*     */   public boolean matchState(IBlockState bs, Map<IProperty, List<Comparable>> mapPropValues) {
/*     */     List vals;
/*     */     Comparable bsVal;
/* 413 */     Set<IProperty> keys = mapPropValues.keySet();
/* 414 */     Iterator<IProperty> it = keys.iterator();
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     do {
/* 420 */       if (!it.hasNext())
/*     */       {
/* 422 */         return true;
/*     */       }
/*     */       
/* 425 */       IProperty prop = it.next();
/* 426 */       vals = mapPropValues.get(prop);
/* 427 */       bsVal = bs.getValue(prop);
/*     */       
/* 429 */       if (bsVal == null)
/*     */       {
/* 431 */         return false;
/*     */       }
/*     */     }
/* 434 */     while (vals.contains(bsVal));
/*     */     
/* 436 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public BiomeGenBase[] parseBiomes(String str) {
/* 441 */     if (str == null)
/*     */     {
/* 443 */       return null;
/*     */     }
/*     */ 
/*     */     
/* 447 */     String[] biomeNames = Config.tokenize(str, " ");
/* 448 */     ArrayList<BiomeGenBase> list = new ArrayList();
/*     */     
/* 450 */     for (int biomeArr = 0; biomeArr < biomeNames.length; biomeArr++) {
/*     */       
/* 452 */       String biomeName = biomeNames[biomeArr];
/* 453 */       BiomeGenBase biome = findBiome(biomeName);
/*     */       
/* 455 */       if (biome == null) {
/*     */         
/* 457 */         warn("Biome not found: " + biomeName);
/*     */       }
/*     */       else {
/*     */         
/* 461 */         list.add(biome);
/*     */       } 
/*     */     } 
/*     */     
/* 465 */     BiomeGenBase[] var7 = list.<BiomeGenBase>toArray(new BiomeGenBase[list.size()]);
/* 466 */     return var7;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public BiomeGenBase findBiome(String biomeName) {
/* 472 */     biomeName = biomeName.toLowerCase();
/*     */     
/* 474 */     if (biomeName.equals("nether"))
/*     */     {
/* 476 */       return BiomeGenBase.hell;
/*     */     }
/*     */ 
/*     */     
/* 480 */     BiomeGenBase[] biomeList = BiomeGenBase.getBiomeGenArray();
/*     */     
/* 482 */     for (int i = 0; i < biomeList.length; i++) {
/*     */       
/* 484 */       BiomeGenBase biome = biomeList[i];
/*     */       
/* 486 */       if (biome != null) {
/*     */         
/* 488 */         String name = biome.biomeName.replace(" ", "").toLowerCase();
/*     */         
/* 490 */         if (name.equals(biomeName))
/*     */         {
/* 492 */           return biome;
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 497 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int parseInt(String str) {
/* 503 */     if (str == null)
/*     */     {
/* 505 */       return -1;
/*     */     }
/*     */ 
/*     */     
/* 509 */     int num = Config.parseInt(str, -1);
/*     */     
/* 511 */     if (num < 0)
/*     */     {
/* 513 */       warn("Invalid number: " + str);
/*     */     }
/*     */     
/* 516 */     return num;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int parseInt(String str, int defVal) {
/* 522 */     if (str == null)
/*     */     {
/* 524 */       return defVal;
/*     */     }
/*     */ 
/*     */     
/* 528 */     int num = Config.parseInt(str, -1);
/*     */     
/* 530 */     if (num < 0) {
/*     */       
/* 532 */       warn("Invalid number: " + str);
/* 533 */       return defVal;
/*     */     } 
/*     */ 
/*     */     
/* 537 */     return num;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int[] parseIntList(String str) {
/* 544 */     if (str == null)
/*     */     {
/* 546 */       return null;
/*     */     }
/*     */ 
/*     */     
/* 550 */     ArrayList<Integer> list = new ArrayList();
/* 551 */     String[] intStrs = Config.tokenize(str, " ,");
/*     */     
/* 553 */     for (int ints = 0; ints < intStrs.length; ints++) {
/*     */       
/* 555 */       String i = intStrs[ints];
/*     */       
/* 557 */       if (i.contains("-")) {
/*     */         
/* 559 */         String[] val = Config.tokenize(i, "-");
/*     */         
/* 561 */         if (val.length != 2) {
/*     */           
/* 563 */           warn("Invalid interval: " + i + ", when parsing: " + str);
/*     */         }
/*     */         else {
/*     */           
/* 567 */           int min = Config.parseInt(val[0], -1);
/* 568 */           int max = Config.parseInt(val[1], -1);
/*     */           
/* 570 */           if (min >= 0 && max >= 0 && min <= max)
/*     */           {
/* 572 */             for (int n = min; n <= max; n++)
/*     */             {
/* 574 */               list.add(Integer.valueOf(n));
/*     */             }
/*     */           }
/*     */           else
/*     */           {
/* 579 */             warn("Invalid interval: " + i + ", when parsing: " + str);
/*     */           }
/*     */         
/*     */         } 
/*     */       } else {
/*     */         
/* 585 */         int var12 = Config.parseInt(i, -1);
/*     */         
/* 587 */         if (var12 < 0) {
/*     */           
/* 589 */           warn("Invalid number: " + i + ", when parsing: " + str);
/*     */         }
/*     */         else {
/*     */           
/* 593 */           list.add(Integer.valueOf(var12));
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 598 */     int[] var10 = new int[list.size()];
/*     */     
/* 600 */     for (int var11 = 0; var11 < var10.length; var11++)
/*     */     {
/* 602 */       var10[var11] = ((Integer)list.get(var11)).intValue();
/*     */     }
/*     */     
/* 605 */     return var10;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean[] parseFaces(String str, boolean[] defVal) {
/* 611 */     if (str == null)
/*     */     {
/* 613 */       return defVal;
/*     */     }
/*     */ 
/*     */     
/* 617 */     EnumSet<EnumFacing> setFaces = EnumSet.allOf(EnumFacing.class);
/* 618 */     String[] faceStrs = Config.tokenize(str, " ,");
/*     */     
/* 620 */     for (int faces = 0; faces < faceStrs.length; faces++) {
/*     */       
/* 622 */       String i = faceStrs[faces];
/*     */       
/* 624 */       if (i.equals("sides")) {
/*     */         
/* 626 */         setFaces.add(EnumFacing.NORTH);
/* 627 */         setFaces.add(EnumFacing.SOUTH);
/* 628 */         setFaces.add(EnumFacing.WEST);
/* 629 */         setFaces.add(EnumFacing.EAST);
/*     */       }
/* 631 */       else if (i.equals("all")) {
/*     */         
/* 633 */         setFaces.addAll(Arrays.asList(EnumFacing.VALUES));
/*     */       }
/*     */       else {
/*     */         
/* 637 */         EnumFacing face = parseFace(i);
/*     */         
/* 639 */         if (face != null)
/*     */         {
/* 641 */           setFaces.add(face);
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 646 */     boolean[] var8 = new boolean[EnumFacing.VALUES.length];
/*     */     
/* 648 */     for (int var9 = 0; var9 < var8.length; var9++)
/*     */     {
/* 650 */       var8[var9] = setFaces.contains(EnumFacing.VALUES[var9]);
/*     */     }
/*     */     
/* 653 */     return var8;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public EnumFacing parseFace(String str) {
/* 659 */     str = str.toLowerCase();
/*     */     
/* 661 */     if (!str.equals("bottom") && !str.equals("down")) {
/*     */       
/* 663 */       if (!str.equals("top") && !str.equals("up")) {
/*     */         
/* 665 */         if (str.equals("north"))
/*     */         {
/* 667 */           return EnumFacing.NORTH;
/*     */         }
/* 669 */         if (str.equals("south"))
/*     */         {
/* 671 */           return EnumFacing.SOUTH;
/*     */         }
/* 673 */         if (str.equals("east"))
/*     */         {
/* 675 */           return EnumFacing.EAST;
/*     */         }
/* 677 */         if (str.equals("west"))
/*     */         {
/* 679 */           return EnumFacing.WEST;
/*     */         }
/*     */ 
/*     */         
/* 683 */         Config.warn("Unknown face: " + str);
/* 684 */         return null;
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 689 */       return EnumFacing.UP;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 694 */     return EnumFacing.DOWN;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void dbg(String str) {
/* 700 */     Config.dbg(this.context + ": " + str);
/*     */   }
/*     */ 
/*     */   
/*     */   public void warn(String str) {
/* 705 */     Config.warn(this.context + ": " + str);
/*     */   }
/*     */ 
/*     */   
/*     */   public RangeListInt parseRangeListInt(String str) {
/* 710 */     if (str == null)
/*     */     {
/* 712 */       return null;
/*     */     }
/*     */ 
/*     */     
/* 716 */     RangeListInt list = new RangeListInt();
/* 717 */     String[] parts = Config.tokenize(str, " ,");
/*     */     
/* 719 */     for (int i = 0; i < parts.length; i++) {
/*     */       
/* 721 */       String part = parts[i];
/* 722 */       RangeInt ri = parseRangeInt(part);
/*     */       
/* 724 */       if (ri == null)
/*     */       {
/* 726 */         return null;
/*     */       }
/*     */       
/* 729 */       list.addRange(ri);
/*     */     } 
/*     */     
/* 732 */     return list;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private RangeInt parseRangeInt(String str) {
/* 738 */     if (str == null)
/*     */     {
/* 740 */       return null;
/*     */     }
/* 742 */     if (str.indexOf('-') >= 0) {
/*     */       
/* 744 */       String[] val1 = Config.tokenize(str, "-");
/*     */       
/* 746 */       if (val1.length != 2) {
/*     */         
/* 748 */         warn("Invalid range: " + str);
/* 749 */         return null;
/*     */       } 
/*     */ 
/*     */       
/* 753 */       int min = Config.parseInt(val1[0], -1);
/* 754 */       int max = Config.parseInt(val1[1], -1);
/*     */       
/* 756 */       if (min >= 0 && max >= 0)
/*     */       {
/* 758 */         return new RangeInt(min, max);
/*     */       }
/*     */ 
/*     */       
/* 762 */       warn("Invalid range: " + str);
/* 763 */       return null;
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 769 */     int val = Config.parseInt(str, -1);
/*     */     
/* 771 */     if (val < 0) {
/*     */       
/* 773 */       warn("Invalid integer: " + str);
/* 774 */       return null;
/*     */     } 
/*     */ 
/*     */     
/* 778 */     return new RangeInt(val, val);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean parseBoolean(String str) {
/* 785 */     return (str == null) ? false : str.toLowerCase().equals("true");
/*     */   }
/*     */ 
/*     */   
/*     */   public static int parseColor(String str, int defVal) {
/* 790 */     if (str == null)
/*     */     {
/* 792 */       return defVal;
/*     */     }
/*     */ 
/*     */     
/* 796 */     str = str.trim();
/*     */ 
/*     */     
/*     */     try {
/* 800 */       int e = Integer.parseInt(str, 16) & 0xFFFFFF;
/* 801 */       return e;
/*     */     }
/* 803 */     catch (NumberFormatException var3) {
/*     */       
/* 805 */       return defVal;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\optifine\ConnectedParser.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */