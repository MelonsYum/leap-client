/*     */ package optifine;
/*     */ 
/*     */ import java.io.FileNotFoundException;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Properties;
/*     */ import java.util.Random;
/*     */ import java.util.UUID;
/*     */ import net.minecraft.client.renderer.RenderGlobal;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLiving;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.WorldServer;
/*     */ 
/*     */ public class RandomMobs
/*     */ {
/*  22 */   private static Map locationProperties = new HashMap<>();
/*  23 */   private static RenderGlobal renderGlobal = null;
/*     */   private static boolean initialized = false;
/*  25 */   private static Random random = new Random();
/*     */   private static boolean working = false;
/*     */   public static final String SUFFIX_PNG = ".png";
/*     */   public static final String SUFFIX_PROPERTIES = ".properties";
/*     */   public static final String PREFIX_TEXTURES_ENTITY = "textures/entity/";
/*     */   public static final String PREFIX_MCPATCHER_MOB = "mcpatcher/mob/";
/*  31 */   private static final String[] DEPENDANT_SUFFIXES = new String[] { "_armor", "_eyes", "_exploding", "_shooting", "_fur", "_eyes", "_invulnerable", "_angry", "_tame", "_collar" };
/*     */ 
/*     */   
/*     */   public static void entityLoaded(Entity entity, World world) {
/*  35 */     if (entity instanceof EntityLiving)
/*     */     {
/*  37 */       if (world != null) {
/*     */         
/*  39 */         EntityLiving el = (EntityLiving)entity;
/*  40 */         el.spawnPosition = el.getPosition();
/*  41 */         el.spawnBiome = world.getBiomeGenForCoords(el.spawnPosition);
/*  42 */         WorldServer ws = Config.getWorldServer();
/*     */         
/*  44 */         if (ws != null) {
/*     */           
/*  46 */           Entity es = ws.getEntityByID(entity.getEntityId());
/*     */           
/*  48 */           if (es instanceof EntityLiving) {
/*     */             
/*  50 */             EntityLiving els = (EntityLiving)es;
/*  51 */             UUID uuid = els.getUniqueID();
/*  52 */             long uuidLow = uuid.getLeastSignificantBits();
/*  53 */             int id = (int)(uuidLow & 0x7FFFFFFFL);
/*  54 */             el.randomMobsId = id;
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public static void worldChanged(World oldWorld, World newWorld) {
/*  63 */     if (newWorld != null) {
/*     */       
/*  65 */       List<Entity> entityList = newWorld.getLoadedEntityList();
/*     */       
/*  67 */       for (int e = 0; e < entityList.size(); e++) {
/*     */         
/*  69 */         Entity entity = entityList.get(e);
/*  70 */         entityLoaded(entity, newWorld);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public static ResourceLocation getTextureLocation(ResourceLocation loc) {
/*     */     ResourceLocation var5;
/*  77 */     if (working)
/*     */     {
/*  79 */       return loc;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  87 */     try { working = true;
/*     */       
/*  89 */       if (!initialized)
/*     */       {
/*  91 */         initialize();
/*     */       }
/*     */       
/*  94 */       if (renderGlobal == null) {
/*     */         
/*  96 */         ResourceLocation entity1 = loc;
/*  97 */         return entity1;
/*     */       } 
/*     */       
/* 100 */       Entity entity = renderGlobal.renderedEntity;
/*     */       
/* 102 */       if (!(entity instanceof EntityLiving)) {
/*     */         
/* 104 */         ResourceLocation entityLiving1 = loc;
/* 105 */         return entityLiving1;
/*     */       } 
/*     */       
/* 108 */       EntityLiving entityLiving = (EntityLiving)entity;
/* 109 */       String name = loc.getResourcePath();
/*     */       
/* 111 */       if (!name.startsWith("textures/entity/")) {
/*     */         
/* 113 */         ResourceLocation props1 = loc;
/* 114 */         return props1;
/*     */       } 
/*     */       
/* 117 */       RandomMobsProperties props = getProperties(loc);
/*     */       
/* 119 */       if (props != null)
/*     */       {
/* 121 */         ResourceLocation resourceLocation = props.getTextureLocation(loc, entityLiving);
/* 122 */         return resourceLocation;
/*     */       
/*     */       }
/*     */        }
/*     */     
/*     */     finally
/*     */     
/* 129 */     { working = false; }  working = false;
/*     */ 
/*     */     
/* 132 */     return var5;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static RandomMobsProperties getProperties(ResourceLocation loc) {
/* 138 */     String name = loc.getResourcePath();
/* 139 */     RandomMobsProperties props = (RandomMobsProperties)locationProperties.get(name);
/*     */     
/* 141 */     if (props == null) {
/*     */       
/* 143 */       props = makeProperties(loc);
/* 144 */       locationProperties.put(name, props);
/*     */     } 
/*     */     
/* 147 */     return props;
/*     */   }
/*     */ 
/*     */   
/*     */   private static RandomMobsProperties makeProperties(ResourceLocation loc) {
/* 152 */     String path = loc.getResourcePath();
/* 153 */     ResourceLocation propLoc = getPropertyLocation(loc);
/*     */     
/* 155 */     if (propLoc != null) {
/*     */       
/* 157 */       RandomMobsProperties variants = parseProperties(propLoc, loc);
/*     */       
/* 159 */       if (variants != null)
/*     */       {
/* 161 */         return variants;
/*     */       }
/*     */     } 
/*     */     
/* 165 */     ResourceLocation[] variants1 = getTextureVariants(loc);
/* 166 */     return new RandomMobsProperties(path, variants1);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static RandomMobsProperties parseProperties(ResourceLocation propLoc, ResourceLocation resLoc) {
/*     */     try {
/* 173 */       String e = propLoc.getResourcePath();
/* 174 */       Config.dbg("RandomMobs: " + resLoc.getResourcePath() + ", variants: " + e);
/* 175 */       InputStream in = Config.getResourceStream(propLoc);
/*     */       
/* 177 */       if (in == null) {
/*     */         
/* 179 */         Config.warn("RandomMobs properties not found: " + e);
/* 180 */         return null;
/*     */       } 
/*     */ 
/*     */       
/* 184 */       Properties props = new Properties();
/* 185 */       props.load(in);
/* 186 */       in.close();
/* 187 */       RandomMobsProperties rmp = new RandomMobsProperties(props, e, resLoc);
/* 188 */       return !rmp.isValid(e) ? null : rmp;
/*     */     
/*     */     }
/* 191 */     catch (FileNotFoundException var6) {
/*     */       
/* 193 */       Config.warn("RandomMobs file not found: " + resLoc.getResourcePath());
/* 194 */       return null;
/*     */     }
/* 196 */     catch (IOException var7) {
/*     */       
/* 198 */       var7.printStackTrace();
/* 199 */       return null;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private static ResourceLocation getPropertyLocation(ResourceLocation loc) {
/* 205 */     ResourceLocation locMcp = getMcpatcherLocation(loc);
/*     */     
/* 207 */     if (locMcp == null)
/*     */     {
/* 209 */       return null;
/*     */     }
/*     */ 
/*     */     
/* 213 */     String domain = locMcp.getResourceDomain();
/* 214 */     String path = locMcp.getResourcePath();
/* 215 */     String pathBase = path;
/*     */     
/* 217 */     if (path.endsWith(".png"))
/*     */     {
/* 219 */       pathBase = path.substring(0, path.length() - ".png".length());
/*     */     }
/*     */     
/* 222 */     String pathProps = String.valueOf(pathBase) + ".properties";
/* 223 */     ResourceLocation locProps = new ResourceLocation(domain, pathProps);
/*     */     
/* 225 */     if (Config.hasResource(locProps))
/*     */     {
/* 227 */       return locProps;
/*     */     }
/*     */ 
/*     */     
/* 231 */     String pathParent = getParentPath(pathBase);
/*     */     
/* 233 */     if (pathParent == null)
/*     */     {
/* 235 */       return null;
/*     */     }
/*     */ 
/*     */     
/* 239 */     ResourceLocation locParentProps = new ResourceLocation(domain, String.valueOf(pathParent) + ".properties");
/* 240 */     return Config.hasResource(locParentProps) ? locParentProps : null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static ResourceLocation getMcpatcherLocation(ResourceLocation loc) {
/* 248 */     String path = loc.getResourcePath();
/*     */     
/* 250 */     if (!path.startsWith("textures/entity/"))
/*     */     {
/* 252 */       return null;
/*     */     }
/*     */ 
/*     */     
/* 256 */     String pathMcp = "mcpatcher/mob/" + path.substring("textures/entity/".length());
/* 257 */     return new ResourceLocation(loc.getResourceDomain(), pathMcp);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static ResourceLocation getLocationIndexed(ResourceLocation loc, int index) {
/* 263 */     if (loc == null)
/*     */     {
/* 265 */       return null;
/*     */     }
/*     */ 
/*     */     
/* 269 */     String path = loc.getResourcePath();
/* 270 */     int pos = path.lastIndexOf('.');
/*     */     
/* 272 */     if (pos < 0)
/*     */     {
/* 274 */       return null;
/*     */     }
/*     */ 
/*     */     
/* 278 */     String prefix = path.substring(0, pos);
/* 279 */     String suffix = path.substring(pos);
/* 280 */     String pathNew = String.valueOf(prefix) + index + suffix;
/* 281 */     ResourceLocation locNew = new ResourceLocation(loc.getResourceDomain(), pathNew);
/* 282 */     return locNew;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static String getParentPath(String path) {
/* 289 */     for (int i = 0; i < DEPENDANT_SUFFIXES.length; i++) {
/*     */       
/* 291 */       String suffix = DEPENDANT_SUFFIXES[i];
/*     */       
/* 293 */       if (path.endsWith(suffix)) {
/*     */         
/* 295 */         String pathParent = path.substring(0, path.length() - suffix.length());
/* 296 */         return pathParent;
/*     */       } 
/*     */     } 
/*     */     
/* 300 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   private static ResourceLocation[] getTextureVariants(ResourceLocation loc) {
/* 305 */     ArrayList<ResourceLocation> list = new ArrayList();
/* 306 */     list.add(loc);
/* 307 */     ResourceLocation locMcp = getMcpatcherLocation(loc);
/*     */     
/* 309 */     if (locMcp == null)
/*     */     {
/* 311 */       return null;
/*     */     }
/*     */ 
/*     */     
/* 315 */     for (int locs = 1; locs < list.size() + 10; locs++) {
/*     */       
/* 317 */       int index = locs + 1;
/* 318 */       ResourceLocation locIndex = getLocationIndexed(locMcp, index);
/*     */       
/* 320 */       if (Config.hasResource(locIndex))
/*     */       {
/* 322 */         list.add(locIndex);
/*     */       }
/*     */     } 
/*     */     
/* 326 */     if (list.size() <= 1)
/*     */     {
/* 328 */       return null;
/*     */     }
/*     */ 
/*     */     
/* 332 */     ResourceLocation[] var6 = list.<ResourceLocation>toArray(new ResourceLocation[list.size()]);
/* 333 */     Config.dbg("RandomMobs: " + loc.getResourcePath() + ", variants: " + var6.length);
/* 334 */     return var6;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void resetTextures() {
/* 341 */     locationProperties.clear();
/*     */     
/* 343 */     if (Config.isRandomMobs())
/*     */     {
/* 345 */       initialize();
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private static void initialize() {
/* 351 */     renderGlobal = Config.getRenderGlobal();
/*     */     
/* 353 */     if (renderGlobal != null) {
/*     */       
/* 355 */       initialized = true;
/* 356 */       ArrayList<String> list = new ArrayList();
/* 357 */       list.add("bat");
/* 358 */       list.add("blaze");
/* 359 */       list.add("cat/black");
/* 360 */       list.add("cat/ocelot");
/* 361 */       list.add("cat/red");
/* 362 */       list.add("cat/siamese");
/* 363 */       list.add("chicken");
/* 364 */       list.add("cow/cow");
/* 365 */       list.add("cow/mooshroom");
/* 366 */       list.add("creeper/creeper");
/* 367 */       list.add("enderman/enderman");
/* 368 */       list.add("enderman/enderman_eyes");
/* 369 */       list.add("ghast/ghast");
/* 370 */       list.add("ghast/ghast_shooting");
/* 371 */       list.add("iron_golem");
/* 372 */       list.add("pig/pig");
/* 373 */       list.add("sheep/sheep");
/* 374 */       list.add("sheep/sheep_fur");
/* 375 */       list.add("silverfish");
/* 376 */       list.add("skeleton/skeleton");
/* 377 */       list.add("skeleton/wither_skeleton");
/* 378 */       list.add("slime/slime");
/* 379 */       list.add("slime/magmacube");
/* 380 */       list.add("snowman");
/* 381 */       list.add("spider/cave_spider");
/* 382 */       list.add("spider/spider");
/* 383 */       list.add("spider_eyes");
/* 384 */       list.add("squid");
/* 385 */       list.add("villager/villager");
/* 386 */       list.add("villager/butcher");
/* 387 */       list.add("villager/farmer");
/* 388 */       list.add("villager/librarian");
/* 389 */       list.add("villager/priest");
/* 390 */       list.add("villager/smith");
/* 391 */       list.add("wither/wither");
/* 392 */       list.add("wither/wither_armor");
/* 393 */       list.add("wither/wither_invulnerable");
/* 394 */       list.add("wolf/wolf");
/* 395 */       list.add("wolf/wolf_angry");
/* 396 */       list.add("wolf/wolf_collar");
/* 397 */       list.add("wolf/wolf_tame");
/* 398 */       list.add("zombie_pigman");
/* 399 */       list.add("zombie/zombie");
/* 400 */       list.add("zombie/zombie_villager");
/*     */       
/* 402 */       for (int i = 0; i < list.size(); i++) {
/*     */         
/* 404 */         String name = list.get(i);
/* 405 */         String tex = "textures/entity/" + name + ".png";
/* 406 */         ResourceLocation texLoc = new ResourceLocation(tex);
/*     */         
/* 408 */         if (!Config.hasResource(texLoc))
/*     */         {
/* 410 */           Config.warn("Not found: " + texLoc);
/*     */         }
/*     */         
/* 413 */         getProperties(texLoc);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\optifine\RandomMobs.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */