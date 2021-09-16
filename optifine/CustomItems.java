/*     */ package optifine;
/*     */ 
/*     */ import java.io.FileNotFoundException;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.Comparator;
/*     */ import java.util.HashMap;
/*     */ import java.util.HashSet;
/*     */ import java.util.LinkedHashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Properties;
/*     */ import java.util.Set;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.model.ModelBase;
/*     */ import net.minecraft.client.renderer.GlStateManager;
/*     */ import net.minecraft.client.renderer.block.model.ItemModelGenerator;
/*     */ import net.minecraft.client.renderer.entity.RenderItem;
/*     */ import net.minecraft.client.renderer.texture.TextureManager;
/*     */ import net.minecraft.client.renderer.texture.TextureMap;
/*     */ import net.minecraft.client.resources.IResourcePack;
/*     */ import net.minecraft.client.resources.model.IBakedModel;
/*     */ import net.minecraft.client.resources.model.ModelResourceLocation;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemArmor;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.nbt.NBTTagList;
/*     */ import net.minecraft.potion.Potion;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import shadersmod.client.Shaders;
/*     */ import shadersmod.client.ShadersRender;
/*     */ 
/*     */ public class CustomItems {
/*  40 */   private static CustomItemProperties[][] itemProperties = null;
/*  41 */   private static CustomItemProperties[][] enchantmentProperties = null;
/*  42 */   private static Map mapPotionIds = null;
/*  43 */   private static ItemModelGenerator itemModelGenerator = new ItemModelGenerator();
/*     */   private static boolean useGlint = true;
/*     */   public static final int MASK_POTION_SPLASH = 16384;
/*     */   public static final int MASK_POTION_NAME = 63;
/*     */   public static final String KEY_TEXTURE_OVERLAY = "texture.potion_overlay";
/*     */   public static final String KEY_TEXTURE_SPLASH = "texture.potion_bottle_splash";
/*     */   public static final String KEY_TEXTURE_DRINKABLE = "texture.potion_bottle_drinkable";
/*     */   public static final String DEFAULT_TEXTURE_OVERLAY = "items/potion_overlay";
/*     */   public static final String DEFAULT_TEXTURE_SPLASH = "items/potion_bottle_splash";
/*     */   public static final String DEFAULT_TEXTURE_DRINKABLE = "items/potion_bottle_drinkable";
/*  53 */   private static final int[] EMPTY_INT_ARRAY = new int[0];
/*  54 */   private static final int[][] EMPTY_INT2_ARRAY = new int[0][];
/*     */ 
/*     */   
/*     */   public static void updateIcons(TextureMap textureMap) {
/*  58 */     itemProperties = null;
/*  59 */     enchantmentProperties = null;
/*  60 */     useGlint = true;
/*     */     
/*  62 */     if (Config.isCustomItems()) {
/*     */       
/*  64 */       readCitProperties("mcpatcher/cit.properties");
/*  65 */       IResourcePack[] rps = Config.getResourcePacks();
/*     */       
/*  67 */       for (int i = rps.length - 1; i >= 0; i--) {
/*     */         
/*  69 */         IResourcePack rp = rps[i];
/*  70 */         updateIcons(textureMap, rp);
/*     */       } 
/*     */       
/*  73 */       updateIcons(textureMap, (IResourcePack)Config.getDefaultResourcePack());
/*     */       
/*  75 */       if (itemProperties.length <= 0)
/*     */       {
/*  77 */         itemProperties = null;
/*     */       }
/*     */       
/*  80 */       if (enchantmentProperties.length <= 0)
/*     */       {
/*  82 */         enchantmentProperties = null;
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static void readCitProperties(String fileName) {
/*     */     try {
/*  91 */       ResourceLocation e = new ResourceLocation(fileName);
/*  92 */       InputStream in = Config.getResourceStream(e);
/*     */       
/*  94 */       if (in == null) {
/*     */         return;
/*     */       }
/*     */ 
/*     */       
/*  99 */       Config.dbg("CustomItems: Loading " + fileName);
/* 100 */       Properties props = new Properties();
/* 101 */       props.load(in);
/* 102 */       in.close();
/* 103 */       useGlint = Config.parseBoolean(props.getProperty("useGlint"), true);
/*     */     }
/* 105 */     catch (FileNotFoundException var4) {
/*     */       
/*     */       return;
/*     */     }
/* 109 */     catch (IOException var5) {
/*     */       
/* 111 */       var5.printStackTrace();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private static void updateIcons(TextureMap textureMap, IResourcePack rp) {
/* 117 */     String[] names = ResUtils.collectFiles(rp, "mcpatcher/cit/", ".properties", (String[])null);
/* 118 */     Map mapAutoProperties = makeAutoImageProperties(rp);
/*     */     
/* 120 */     if (mapAutoProperties.size() > 0) {
/*     */       
/* 122 */       Set itemList = mapAutoProperties.keySet();
/* 123 */       String[] enchantmentList = (String[])itemList.toArray((Object[])new String[itemList.size()]);
/* 124 */       names = (String[])Config.addObjectsToArray((Object[])names, (Object[])enchantmentList);
/*     */     } 
/*     */     
/* 127 */     Arrays.sort((Object[])names);
/* 128 */     List var14 = makePropertyList(itemProperties);
/* 129 */     List var15 = makePropertyList(enchantmentProperties);
/*     */     
/* 131 */     for (int comp = 0; comp < names.length; comp++) {
/*     */       
/* 133 */       String i = names[comp];
/* 134 */       Config.dbg("CustomItems: " + i);
/*     */ 
/*     */       
/*     */       try {
/* 138 */         CustomItemProperties cips = null;
/*     */         
/* 140 */         if (mapAutoProperties.containsKey(i))
/*     */         {
/* 142 */           cips = (CustomItemProperties)mapAutoProperties.get(i);
/*     */         }
/*     */         
/* 145 */         if (cips == null)
/*     */         
/* 147 */         { ResourceLocation locFile = new ResourceLocation(i);
/* 148 */           InputStream in = rp.getInputStream(locFile);
/*     */           
/* 150 */           if (in == null)
/*     */           
/* 152 */           { Config.warn("CustomItems file not found: " + i); }
/*     */           
/*     */           else
/*     */           
/* 156 */           { Properties props = new Properties();
/* 157 */             props.load(in);
/* 158 */             cips = new CustomItemProperties(props, i);
/*     */ 
/*     */             
/* 161 */             if (cips.isValid(i))
/*     */             
/* 163 */             { cips.updateIcons(textureMap);
/* 164 */               addToItemList(cips, var14);
/* 165 */               addToEnchantmentList(cips, var15); }  }  continue; }  if (cips.isValid(i)) { cips.updateIcons(textureMap); addToItemList(cips, var14); addToEnchantmentList(cips, var15); }
/*     */ 
/*     */       
/* 168 */       } catch (FileNotFoundException var12) {
/*     */         
/* 170 */         Config.warn("CustomItems file not found: " + i);
/*     */         continue;
/* 172 */       } catch (Exception var13) {
/*     */         
/* 174 */         var13.printStackTrace();
/*     */         continue;
/*     */       } 
/*     */     } 
/* 178 */     itemProperties = propertyListToArray(var14);
/* 179 */     enchantmentProperties = propertyListToArray(var15);
/* 180 */     Comparator<? super CustomItemProperties> var16 = getPropertiesComparator();
/*     */     
/*     */     int var17;
/*     */     
/* 184 */     for (var17 = 0; var17 < itemProperties.length; var17++) {
/*     */       
/* 186 */       CustomItemProperties[] var18 = itemProperties[var17];
/*     */       
/* 188 */       if (var18 != null)
/*     */       {
/* 190 */         Arrays.sort(var18, var16);
/*     */       }
/*     */     } 
/*     */     
/* 194 */     for (var17 = 0; var17 < enchantmentProperties.length; var17++) {
/*     */       
/* 196 */       CustomItemProperties[] var18 = enchantmentProperties[var17];
/*     */       
/* 198 */       if (var18 != null)
/*     */       {
/* 200 */         Arrays.sort(var18, var16);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private static Comparator getPropertiesComparator() {
/* 207 */     Comparator comp = new Comparator()
/*     */       {
/*     */         public int compare(Object o1, Object o2)
/*     */         {
/* 211 */           CustomItemProperties cip1 = (CustomItemProperties)o1;
/* 212 */           CustomItemProperties cip2 = (CustomItemProperties)o2;
/* 213 */           return (cip1.layer != cip2.layer) ? (cip1.layer - cip2.layer) : ((cip1.weight != cip2.weight) ? (cip2.weight - cip1.weight) : (!cip1.basePath.equals(cip2.basePath) ? cip1.basePath.compareTo(cip2.basePath) : cip1.name.compareTo(cip2.name)));
/*     */         }
/*     */       };
/* 216 */     return comp;
/*     */   }
/*     */ 
/*     */   
/*     */   public static void updateModels() {
/* 221 */     if (itemProperties != null)
/*     */     {
/* 223 */       for (int id = 0; id < itemProperties.length; id++) {
/*     */         
/* 225 */         CustomItemProperties[] cips = itemProperties[id];
/*     */         
/* 227 */         if (cips != null)
/*     */         {
/* 229 */           for (int c = 0; c < cips.length; c++) {
/*     */             
/* 231 */             CustomItemProperties cip = cips[c];
/*     */             
/* 233 */             if (cip != null && cip.type == 1) {
/*     */               
/* 235 */               TextureMap textureMap = Minecraft.getMinecraft().getTextureMapBlocks();
/* 236 */               cip.updateModel(textureMap, itemModelGenerator);
/*     */             } 
/*     */           } 
/*     */         }
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private static Map makeAutoImageProperties(IResourcePack rp) {
/* 246 */     HashMap<Object, Object> map = new HashMap<>();
/* 247 */     map.putAll(makePotionImageProperties(rp, false));
/* 248 */     map.putAll(makePotionImageProperties(rp, true));
/* 249 */     return map;
/*     */   }
/*     */ 
/*     */   
/*     */   private static Map makePotionImageProperties(IResourcePack rp, boolean splash) {
/* 254 */     HashMap<Object, Object> map = new HashMap<>();
/* 255 */     String type = splash ? "splash/" : "normal/";
/* 256 */     String[] prefixes = { "mcpatcher/cit/potion/" + type, "mcpatcher/cit/Potion/" + type };
/* 257 */     String[] suffixes = { ".png" };
/* 258 */     String[] names = ResUtils.collectFiles(rp, prefixes, suffixes);
/*     */     
/* 260 */     for (int i = 0; i < names.length; i++) {
/*     */       
/* 262 */       String path = names[i];
/* 263 */       String name = StrUtils.removePrefixSuffix(path, prefixes, suffixes);
/* 264 */       Properties props = makePotionProperties(name, splash, path);
/*     */       
/* 266 */       if (props != null) {
/*     */         
/* 268 */         String pathProp = String.valueOf(StrUtils.removeSuffix(path, suffixes)) + ".properties";
/* 269 */         CustomItemProperties cip = new CustomItemProperties(props, pathProp);
/* 270 */         map.put(pathProp, cip);
/*     */       } 
/*     */     } 
/*     */     
/* 274 */     return map;
/*     */   }
/*     */ 
/*     */   
/*     */   private static Properties makePotionProperties(String name, boolean splash, String path) {
/* 279 */     if (StrUtils.endsWith(name, new String[] { "_n", "_s" }))
/*     */     {
/* 281 */       return null;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 287 */     if (name.equals("empty") && !splash) {
/*     */       
/* 289 */       int i = Item.getIdFromItem(Items.glass_bottle);
/* 290 */       Properties var8 = new Properties();
/* 291 */       var8.put("type", "item");
/* 292 */       var8.put("items", i);
/* 293 */       return var8;
/*     */     } 
/*     */ 
/*     */     
/* 297 */     int potionItemId = Item.getIdFromItem((Item)Items.potionitem);
/* 298 */     int[] damages = (int[])getMapPotionIds().get(name);
/*     */     
/* 300 */     if (damages == null) {
/*     */       
/* 302 */       Config.warn("Potion not found for image: " + path);
/* 303 */       return null;
/*     */     } 
/*     */ 
/*     */     
/* 307 */     StringBuffer bufDamage = new StringBuffer();
/*     */     
/* 309 */     for (int damageMask = 0; damageMask < damages.length; damageMask++) {
/*     */       
/* 311 */       int props = damages[damageMask];
/*     */       
/* 313 */       if (splash)
/*     */       {
/* 315 */         props |= 0x4000;
/*     */       }
/*     */       
/* 318 */       if (damageMask > 0)
/*     */       {
/* 320 */         bufDamage.append(" ");
/*     */       }
/*     */       
/* 323 */       bufDamage.append(props);
/*     */     } 
/*     */     
/* 326 */     short var9 = 16447;
/* 327 */     Properties var10 = new Properties();
/* 328 */     var10.put("type", "item");
/* 329 */     var10.put("items", potionItemId);
/* 330 */     var10.put("damage", bufDamage.toString());
/* 331 */     var10.put("damageMask", var9);
/*     */     
/* 333 */     if (splash) {
/*     */       
/* 335 */       var10.put("texture.potion_bottle_splash", name);
/*     */     }
/*     */     else {
/*     */       
/* 339 */       var10.put("texture.potion_bottle_drinkable", name);
/*     */     } 
/*     */     
/* 342 */     return var10;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static Map getMapPotionIds() {
/* 350 */     if (mapPotionIds == null) {
/*     */       
/* 352 */       mapPotionIds = new LinkedHashMap<>();
/* 353 */       mapPotionIds.put("water", new int[1]);
/* 354 */       mapPotionIds.put("awkward", new int[] { 16 });
/* 355 */       mapPotionIds.put("thick", new int[] { 32 });
/* 356 */       mapPotionIds.put("potent", new int[] { 48 });
/* 357 */       mapPotionIds.put("regeneration", getPotionIds(1));
/* 358 */       mapPotionIds.put("moveSpeed", getPotionIds(2));
/* 359 */       mapPotionIds.put("fireResistance", getPotionIds(3));
/* 360 */       mapPotionIds.put("poison", getPotionIds(4));
/* 361 */       mapPotionIds.put("heal", getPotionIds(5));
/* 362 */       mapPotionIds.put("nightVision", getPotionIds(6));
/* 363 */       mapPotionIds.put("clear", getPotionIds(7));
/* 364 */       mapPotionIds.put("bungling", getPotionIds(23));
/* 365 */       mapPotionIds.put("charming", getPotionIds(39));
/* 366 */       mapPotionIds.put("rank", getPotionIds(55));
/* 367 */       mapPotionIds.put("weakness", getPotionIds(8));
/* 368 */       mapPotionIds.put("damageBoost", getPotionIds(9));
/* 369 */       mapPotionIds.put("moveSlowdown", getPotionIds(10));
/* 370 */       mapPotionIds.put("diffuse", getPotionIds(11));
/* 371 */       mapPotionIds.put("smooth", getPotionIds(27));
/* 372 */       mapPotionIds.put("refined", getPotionIds(43));
/* 373 */       mapPotionIds.put("acrid", getPotionIds(59));
/* 374 */       mapPotionIds.put("harm", getPotionIds(12));
/* 375 */       mapPotionIds.put("waterBreathing", getPotionIds(13));
/* 376 */       mapPotionIds.put("invisibility", getPotionIds(14));
/* 377 */       mapPotionIds.put("thin", getPotionIds(15));
/* 378 */       mapPotionIds.put("debonair", getPotionIds(31));
/* 379 */       mapPotionIds.put("sparkling", getPotionIds(47));
/* 380 */       mapPotionIds.put("stinky", getPotionIds(63));
/*     */     } 
/*     */     
/* 383 */     return mapPotionIds;
/*     */   }
/*     */ 
/*     */   
/*     */   private static int[] getPotionIds(int baseId) {
/* 388 */     return new int[] { baseId, baseId + 16, baseId + 32, baseId + 48 };
/*     */   }
/*     */ 
/*     */   
/*     */   private static int getPotionNameDamage(String name) {
/* 393 */     String fullName = "potion." + name;
/* 394 */     Potion[] effectPotions = Potion.potionTypes;
/*     */     
/* 396 */     for (int i = 0; i < effectPotions.length; i++) {
/*     */       
/* 398 */       Potion potion = effectPotions[i];
/*     */       
/* 400 */       if (potion != null) {
/*     */         
/* 402 */         String potionName = potion.getName();
/*     */         
/* 404 */         if (fullName.equals(potionName))
/*     */         {
/* 406 */           return potion.getId();
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 411 */     return -1;
/*     */   }
/*     */ 
/*     */   
/*     */   private static List makePropertyList(CustomItemProperties[][] propsArr) {
/* 416 */     ArrayList<ArrayList> list = new ArrayList();
/*     */     
/* 418 */     if (propsArr != null)
/*     */     {
/* 420 */       for (int i = 0; i < propsArr.length; i++) {
/*     */         
/* 422 */         CustomItemProperties[] props = propsArr[i];
/* 423 */         ArrayList propList = null;
/*     */         
/* 425 */         if (props != null)
/*     */         {
/* 427 */           propList = new ArrayList(Arrays.asList((Object[])props));
/*     */         }
/*     */         
/* 430 */         list.add(propList);
/*     */       } 
/*     */     }
/*     */     
/* 434 */     return list;
/*     */   }
/*     */ 
/*     */   
/*     */   private static CustomItemProperties[][] propertyListToArray(List<List> list) {
/* 439 */     CustomItemProperties[][] propArr = new CustomItemProperties[list.size()][];
/*     */     
/* 441 */     for (int i = 0; i < list.size(); i++) {
/*     */       
/* 443 */       List subList = list.get(i);
/*     */       
/* 445 */       if (subList != null) {
/*     */         
/* 447 */         CustomItemProperties[] subArr = (CustomItemProperties[])subList.toArray((Object[])new CustomItemProperties[subList.size()]);
/* 448 */         Arrays.sort(subArr, new CustomItemsComparator());
/* 449 */         propArr[i] = subArr;
/*     */       } 
/*     */     } 
/*     */     
/* 453 */     return propArr;
/*     */   }
/*     */ 
/*     */   
/*     */   private static void addToItemList(CustomItemProperties cp, List itemList) {
/* 458 */     if (cp.items != null)
/*     */     {
/* 460 */       for (int i = 0; i < cp.items.length; i++) {
/*     */         
/* 462 */         int itemId = cp.items[i];
/*     */         
/* 464 */         if (itemId <= 0) {
/*     */           
/* 466 */           Config.warn("Invalid item ID: " + itemId);
/*     */         }
/*     */         else {
/*     */           
/* 470 */           addToList(cp, itemList, itemId);
/*     */         } 
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private static void addToEnchantmentList(CustomItemProperties cp, List enchantmentList) {
/* 478 */     if (cp.type == 2)
/*     */     {
/* 480 */       if (cp.enchantmentIds != null)
/*     */       {
/* 482 */         for (int i = 0; i < 256; i++) {
/*     */           
/* 484 */           if (cp.enchantmentIds.isInRange(i))
/*     */           {
/* 486 */             addToList(cp, enchantmentList, i);
/*     */           }
/*     */         } 
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private static void addToList(CustomItemProperties cp, List<List> list, int id) {
/* 495 */     while (id >= list.size())
/*     */     {
/* 497 */       list.add(null);
/*     */     }
/*     */     
/* 500 */     Object subList = list.get(id);
/*     */     
/* 502 */     if (subList == null) {
/*     */       
/* 504 */       subList = new ArrayList();
/* 505 */       list.set(id, subList);
/*     */     } 
/*     */     
/* 508 */     ((List<CustomItemProperties>)subList).add(cp);
/*     */   }
/*     */ 
/*     */   
/*     */   public static IBakedModel getCustomItemModel(ItemStack itemStack, IBakedModel model, ModelResourceLocation modelLocation) {
/* 513 */     if (model.isAmbientOcclusionEnabled())
/*     */     {
/* 515 */       return model;
/*     */     }
/* 517 */     if (itemProperties == null)
/*     */     {
/* 519 */       return model;
/*     */     }
/*     */ 
/*     */     
/* 523 */     CustomItemProperties props = getCustomItemProperties(itemStack, 1);
/* 524 */     return (props == null) ? model : props.getModel(modelLocation);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean bindCustomArmorTexture(ItemStack itemStack, int layer, String overlay) {
/* 530 */     if (itemProperties == null)
/*     */     {
/* 532 */       return false;
/*     */     }
/*     */ 
/*     */     
/* 536 */     ResourceLocation loc = getCustomArmorLocation(itemStack, layer, overlay);
/*     */     
/* 538 */     if (loc == null)
/*     */     {
/* 540 */       return false;
/*     */     }
/*     */ 
/*     */     
/* 544 */     Config.getTextureManager().bindTexture(loc);
/* 545 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static ResourceLocation getCustomArmorLocation(ItemStack itemStack, int layer, String overlay) {
/* 552 */     CustomItemProperties props = getCustomItemProperties(itemStack, 3);
/*     */     
/* 554 */     if (props == null)
/*     */     {
/* 556 */       return null;
/*     */     }
/* 558 */     if (props.mapTextureLocations == null)
/*     */     {
/* 560 */       return null;
/*     */     }
/*     */ 
/*     */     
/* 564 */     Item item = itemStack.getItem();
/*     */     
/* 566 */     if (!(item instanceof ItemArmor))
/*     */     {
/* 568 */       return null;
/*     */     }
/*     */ 
/*     */     
/* 572 */     ItemArmor itemArmor = (ItemArmor)item;
/* 573 */     String material = itemArmor.getArmorMaterial().func_179242_c();
/* 574 */     StringBuffer sb = new StringBuffer();
/* 575 */     sb.append("texture.");
/* 576 */     sb.append(material);
/* 577 */     sb.append("_layer_");
/* 578 */     sb.append(layer);
/*     */     
/* 580 */     if (overlay != null) {
/*     */       
/* 582 */       sb.append("_");
/* 583 */       sb.append(overlay);
/*     */     } 
/*     */     
/* 586 */     String key = sb.toString();
/* 587 */     ResourceLocation loc = (ResourceLocation)props.mapTextureLocations.get(key);
/* 588 */     return loc;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static CustomItemProperties getCustomItemProperties(ItemStack itemStack, int type) {
/* 595 */     if (itemProperties == null)
/*     */     {
/* 597 */       return null;
/*     */     }
/* 599 */     if (itemStack == null)
/*     */     {
/* 601 */       return null;
/*     */     }
/*     */ 
/*     */     
/* 605 */     Item item = itemStack.getItem();
/* 606 */     int itemId = Item.getIdFromItem(item);
/*     */     
/* 608 */     if (itemId >= 0 && itemId < itemProperties.length) {
/*     */       
/* 610 */       CustomItemProperties[] cips = itemProperties[itemId];
/*     */       
/* 612 */       if (cips != null)
/*     */       {
/* 614 */         for (int i = 0; i < cips.length; i++) {
/*     */           
/* 616 */           CustomItemProperties cip = cips[i];
/*     */           
/* 618 */           if (cip.type == type && matchesProperties(cip, itemStack, null))
/*     */           {
/* 620 */             return cip;
/*     */           }
/*     */         } 
/*     */       }
/*     */     } 
/*     */     
/* 626 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static boolean matchesProperties(CustomItemProperties cip, ItemStack itemStack, int[][] enchantmentIdLevels) {
/* 632 */     Item item = itemStack.getItem();
/*     */     
/* 634 */     if (cip.damage != null) {
/*     */       
/* 636 */       int idLevels = itemStack.getItemDamage();
/*     */       
/* 638 */       if (cip.damageMask != 0)
/*     */       {
/* 640 */         idLevels &= cip.damageMask;
/*     */       }
/*     */       
/* 643 */       if (cip.damagePercent) {
/*     */         
/* 645 */         int nbt = item.getMaxDamage();
/* 646 */         idLevels = (int)((idLevels * 100) / nbt);
/*     */       } 
/*     */       
/* 649 */       if (!cip.damage.isInRange(idLevels))
/*     */       {
/* 651 */         return false;
/*     */       }
/*     */     } 
/*     */     
/* 655 */     if (cip.stackSize != null && !cip.stackSize.isInRange(itemStack.stackSize))
/*     */     {
/* 657 */       return false;
/*     */     }
/*     */ 
/*     */     
/* 661 */     int[][] var8 = enchantmentIdLevels;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 666 */     if (cip.enchantmentIds != null) {
/*     */       
/* 668 */       if (enchantmentIdLevels == null)
/*     */       {
/* 670 */         var8 = getEnchantmentIdLevels(itemStack);
/*     */       }
/*     */       
/* 673 */       boolean var9 = false;
/*     */       
/* 675 */       for (int i = 0; i < var8.length; i++) {
/*     */         
/* 677 */         int ntv = var8[i][0];
/*     */         
/* 679 */         if (cip.enchantmentIds.isInRange(ntv)) {
/*     */           
/* 681 */           var9 = true;
/*     */           
/*     */           break;
/*     */         } 
/*     */       } 
/* 686 */       if (!var9)
/*     */       {
/* 688 */         return false;
/*     */       }
/*     */     } 
/*     */     
/* 692 */     if (cip.enchantmentLevels != null) {
/*     */       
/* 694 */       if (var8 == null)
/*     */       {
/* 696 */         var8 = getEnchantmentIdLevels(itemStack);
/*     */       }
/*     */       
/* 699 */       boolean var9 = false;
/*     */       
/* 701 */       for (int i = 0; i < var8.length; i++) {
/*     */         
/* 703 */         int ntv = var8[i][1];
/*     */         
/* 705 */         if (cip.enchantmentLevels.isInRange(ntv)) {
/*     */           
/* 707 */           var9 = true;
/*     */           
/*     */           break;
/*     */         } 
/*     */       } 
/* 712 */       if (!var9)
/*     */       {
/* 714 */         return false;
/*     */       }
/*     */     } 
/*     */     
/* 718 */     if (cip.nbtTagValues != null) {
/*     */       
/* 720 */       NBTTagCompound var10 = itemStack.getTagCompound();
/*     */       
/* 722 */       for (int i = 0; i < cip.nbtTagValues.length; i++) {
/*     */         
/* 724 */         NbtTagValue var11 = cip.nbtTagValues[i];
/*     */         
/* 726 */         if (!var11.matches(var10))
/*     */         {
/* 728 */           return false;
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 733 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static int[][] getEnchantmentIdLevels(ItemStack itemStack) {
/* 739 */     Item item = itemStack.getItem();
/* 740 */     NBTTagList nbt = (item == Items.enchanted_book) ? Items.enchanted_book.func_92110_g(itemStack) : itemStack.getEnchantmentTagList();
/*     */     
/* 742 */     if (nbt != null && nbt.tagCount() > 0) {
/*     */       
/* 744 */       int[][] arr = new int[nbt.tagCount()][2];
/*     */       
/* 746 */       for (int i = 0; i < nbt.tagCount(); i++) {
/*     */         
/* 748 */         NBTTagCompound tag = nbt.getCompoundTagAt(i);
/* 749 */         short id = tag.getShort("id");
/* 750 */         short lvl = tag.getShort("lvl");
/* 751 */         arr[i][0] = id;
/* 752 */         arr[i][1] = lvl;
/*     */       } 
/*     */       
/* 755 */       return arr;
/*     */     } 
/*     */ 
/*     */     
/* 759 */     return EMPTY_INT2_ARRAY;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean renderCustomEffect(RenderItem renderItem, ItemStack itemStack, IBakedModel model) {
/* 765 */     if (enchantmentProperties == null)
/*     */     {
/* 767 */       return false;
/*     */     }
/* 769 */     if (itemStack == null)
/*     */     {
/* 771 */       return false;
/*     */     }
/*     */ 
/*     */     
/* 775 */     int[][] idLevels = getEnchantmentIdLevels(itemStack);
/*     */     
/* 777 */     if (idLevels.length <= 0)
/*     */     {
/* 779 */       return false;
/*     */     }
/*     */ 
/*     */     
/* 783 */     HashSet<Integer> layersRendered = null;
/* 784 */     boolean rendered = false;
/* 785 */     TextureManager textureManager = Config.getTextureManager();
/*     */     
/* 787 */     for (int i = 0; i < idLevels.length; i++) {
/*     */       
/* 789 */       int id = idLevels[i][0];
/*     */       
/* 791 */       if (id >= 0 && id < enchantmentProperties.length) {
/*     */         
/* 793 */         CustomItemProperties[] cips = enchantmentProperties[id];
/*     */         
/* 795 */         if (cips != null)
/*     */         {
/* 797 */           for (int p = 0; p < cips.length; p++) {
/*     */             
/* 799 */             CustomItemProperties cip = cips[p];
/*     */             
/* 801 */             if (layersRendered == null)
/*     */             {
/* 803 */               layersRendered = new HashSet();
/*     */             }
/*     */             
/* 806 */             if (layersRendered.add(Integer.valueOf(id)) && matchesProperties(cip, itemStack, idLevels) && cip.textureLocation != null) {
/*     */               
/* 808 */               textureManager.bindTexture(cip.textureLocation);
/* 809 */               float width = cip.getTextureWidth(textureManager);
/*     */               
/* 811 */               if (!rendered) {
/*     */                 
/* 813 */                 rendered = true;
/* 814 */                 GlStateManager.depthMask(false);
/* 815 */                 GlStateManager.depthFunc(514);
/* 816 */                 GlStateManager.disableLighting();
/* 817 */                 GlStateManager.matrixMode(5890);
/*     */               } 
/*     */               
/* 820 */               Blender.setupBlend(cip.blend, 1.0F);
/* 821 */               GlStateManager.pushMatrix();
/* 822 */               GlStateManager.scale(width / 2.0F, width / 2.0F, width / 2.0F);
/* 823 */               float offset = cip.speed * (float)(Minecraft.getSystemTime() % 3000L) / 3000.0F / 8.0F;
/* 824 */               GlStateManager.translate(offset, 0.0F, 0.0F);
/* 825 */               GlStateManager.rotate(cip.rotation, 0.0F, 0.0F, 1.0F);
/* 826 */               renderItem.func_175035_a(model, -1);
/* 827 */               GlStateManager.popMatrix();
/*     */             } 
/*     */           } 
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 834 */     if (rendered) {
/*     */       
/* 836 */       GlStateManager.enableAlpha();
/* 837 */       GlStateManager.enableBlend();
/* 838 */       GlStateManager.blendFunc(770, 771);
/* 839 */       GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
/* 840 */       GlStateManager.matrixMode(5888);
/* 841 */       GlStateManager.enableLighting();
/* 842 */       GlStateManager.depthFunc(515);
/* 843 */       GlStateManager.depthMask(true);
/* 844 */       textureManager.bindTexture(TextureMap.locationBlocksTexture);
/*     */     } 
/*     */     
/* 847 */     return rendered;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean renderCustomArmorEffect(EntityLivingBase entity, ItemStack itemStack, ModelBase model, float limbSwing, float prevLimbSwing, float partialTicks, float timeLimbSwing, float yaw, float pitch, float scale) {
/* 854 */     if (enchantmentProperties == null)
/*     */     {
/* 856 */       return false;
/*     */     }
/* 858 */     if (Config.isShaders() && Shaders.isShadowPass)
/*     */     {
/* 860 */       return false;
/*     */     }
/* 862 */     if (itemStack == null)
/*     */     {
/* 864 */       return false;
/*     */     }
/*     */ 
/*     */     
/* 868 */     int[][] idLevels = getEnchantmentIdLevels(itemStack);
/*     */     
/* 870 */     if (idLevels.length <= 0)
/*     */     {
/* 872 */       return false;
/*     */     }
/*     */ 
/*     */     
/* 876 */     HashSet<Integer> layersRendered = null;
/* 877 */     boolean rendered = false;
/* 878 */     TextureManager textureManager = Config.getTextureManager();
/*     */     
/* 880 */     for (int i = 0; i < idLevels.length; i++) {
/*     */       
/* 882 */       int id = idLevels[i][0];
/*     */       
/* 884 */       if (id >= 0 && id < enchantmentProperties.length) {
/*     */         
/* 886 */         CustomItemProperties[] cips = enchantmentProperties[id];
/*     */         
/* 888 */         if (cips != null)
/*     */         {
/* 890 */           for (int p = 0; p < cips.length; p++) {
/*     */             
/* 892 */             CustomItemProperties cip = cips[p];
/*     */             
/* 894 */             if (layersRendered == null)
/*     */             {
/* 896 */               layersRendered = new HashSet();
/*     */             }
/*     */             
/* 899 */             if (layersRendered.add(Integer.valueOf(id)) && matchesProperties(cip, itemStack, idLevels) && cip.textureLocation != null) {
/*     */               
/* 901 */               textureManager.bindTexture(cip.textureLocation);
/* 902 */               float width = cip.getTextureWidth(textureManager);
/*     */               
/* 904 */               if (!rendered) {
/*     */                 
/* 906 */                 rendered = true;
/*     */                 
/* 908 */                 if (Config.isShaders())
/*     */                 {
/* 910 */                   ShadersRender.layerArmorBaseDrawEnchantedGlintBegin();
/*     */                 }
/*     */                 
/* 913 */                 GlStateManager.enableBlend();
/* 914 */                 GlStateManager.depthFunc(514);
/* 915 */                 GlStateManager.depthMask(false);
/*     */               } 
/*     */               
/* 918 */               Blender.setupBlend(cip.blend, 1.0F);
/* 919 */               GlStateManager.disableLighting();
/* 920 */               GlStateManager.matrixMode(5890);
/* 921 */               GlStateManager.loadIdentity();
/* 922 */               GlStateManager.rotate(cip.rotation, 0.0F, 0.0F, 1.0F);
/* 923 */               float texScale = width / 8.0F;
/* 924 */               GlStateManager.scale(texScale, texScale / 2.0F, texScale);
/* 925 */               float offset = cip.speed * (float)(Minecraft.getSystemTime() % 3000L) / 3000.0F / 8.0F;
/* 926 */               GlStateManager.translate(0.0F, offset, 0.0F);
/* 927 */               GlStateManager.matrixMode(5888);
/* 928 */               model.render((Entity)entity, limbSwing, prevLimbSwing, timeLimbSwing, yaw, pitch, scale);
/*     */             } 
/*     */           } 
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 935 */     if (rendered) {
/*     */       
/* 937 */       GlStateManager.enableAlpha();
/* 938 */       GlStateManager.enableBlend();
/* 939 */       GlStateManager.blendFunc(770, 771);
/* 940 */       GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
/* 941 */       GlStateManager.matrixMode(5890);
/* 942 */       GlStateManager.loadIdentity();
/* 943 */       GlStateManager.matrixMode(5888);
/* 944 */       GlStateManager.enableLighting();
/* 945 */       GlStateManager.depthMask(true);
/* 946 */       GlStateManager.depthFunc(515);
/* 947 */       GlStateManager.disableBlend();
/*     */       
/* 949 */       if (Config.isShaders())
/*     */       {
/* 951 */         ShadersRender.layerArmorBaseDrawEnchantedGlintEnd();
/*     */       }
/*     */     } 
/*     */     
/* 955 */     return rendered;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean isUseGlint() {
/* 962 */     return useGlint;
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\optifine\CustomItems.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */