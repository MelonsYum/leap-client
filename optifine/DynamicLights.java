/*     */ package optifine;
/*     */ 
/*     */ import java.util.Collection;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.client.multiplayer.WorldClient;
/*     */ import net.minecraft.client.renderer.RenderGlobal;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.item.EntityItem;
/*     */ import net.minecraft.entity.monster.EntityBlaze;
/*     */ import net.minecraft.entity.monster.EntityCreeper;
/*     */ import net.minecraft.entity.monster.EntityMagmaCube;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemBlock;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.BlockPos;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class DynamicLights
/*     */ {
/*  29 */   private static Map<Integer, DynamicLight> mapDynamicLights = new HashMap<>();
/*  30 */   private static long timeUpdateMs = 0L;
/*     */   
/*     */   private static final double MAX_DIST = 7.5D;
/*     */   private static final double MAX_DIST_SQ = 56.25D;
/*     */   private static final int LIGHT_LEVEL_MAX = 15;
/*     */   private static final int LIGHT_LEVEL_FIRE = 15;
/*     */   private static final int LIGHT_LEVEL_BLAZE = 10;
/*     */   private static final int LIGHT_LEVEL_MAGMA_CUBE = 8;
/*     */   private static final int LIGHT_LEVEL_MAGMA_CUBE_CORE = 13;
/*     */   private static final int LIGHT_LEVEL_GLOWSTONE_DUST = 8;
/*     */   private static final int LIGHT_LEVEL_PRISMARINE_CRYSTALS = 8;
/*     */   
/*     */   public static void entityAdded(Entity entityIn, RenderGlobal renderGlobal) {}
/*     */   
/*     */   public static void entityRemoved(Entity entityIn, RenderGlobal renderGlobal) {
/*  45 */     Map<Integer, DynamicLight> var2 = mapDynamicLights;
/*     */     
/*  47 */     synchronized (mapDynamicLights) {
/*     */       
/*  49 */       DynamicLight dynamicLight = mapDynamicLights.remove(IntegerCache.valueOf(entityIn.getEntityId()));
/*     */       
/*  51 */       if (dynamicLight != null)
/*     */       {
/*  53 */         dynamicLight.updateLitChunks(renderGlobal);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static void update(RenderGlobal renderGlobal) {
/*  60 */     long timeNowMs = System.currentTimeMillis();
/*     */     
/*  62 */     if (timeNowMs >= timeUpdateMs + 50L) {
/*     */       
/*  64 */       timeUpdateMs = timeNowMs;
/*  65 */       Map<Integer, DynamicLight> var3 = mapDynamicLights;
/*     */       
/*  67 */       synchronized (mapDynamicLights) {
/*     */         
/*  69 */         updateMapDynamicLights(renderGlobal);
/*     */         
/*  71 */         if (mapDynamicLights.size() > 0) {
/*     */           
/*  73 */           Collection<DynamicLight> dynamicLights = mapDynamicLights.values();
/*  74 */           Iterator<DynamicLight> it = dynamicLights.iterator();
/*     */           
/*  76 */           while (it.hasNext()) {
/*     */             
/*  78 */             DynamicLight dynamicLight = it.next();
/*  79 */             dynamicLight.update(renderGlobal);
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private static void updateMapDynamicLights(RenderGlobal renderGlobal) {
/*  88 */     WorldClient world = renderGlobal.getWorld();
/*     */     
/*  90 */     if (world != null) {
/*     */       
/*  92 */       List entities = world.getLoadedEntityList();
/*  93 */       Iterator<Entity> it = entities.iterator();
/*     */       
/*  95 */       while (it.hasNext()) {
/*     */         
/*  97 */         Entity entity = it.next();
/*  98 */         int lightLevel = getLightLevel(entity);
/*     */ 
/*     */ 
/*     */         
/* 102 */         if (lightLevel > 0) {
/*     */           
/* 104 */           Integer integer = IntegerCache.valueOf(entity.getEntityId());
/* 105 */           DynamicLight dynamicLight1 = mapDynamicLights.get(integer);
/*     */           
/* 107 */           if (dynamicLight1 == null) {
/*     */             
/* 109 */             dynamicLight1 = new DynamicLight(entity);
/* 110 */             mapDynamicLights.put(integer, dynamicLight1);
/*     */           } 
/*     */           
/*     */           continue;
/*     */         } 
/* 115 */         Integer key = IntegerCache.valueOf(entity.getEntityId());
/* 116 */         DynamicLight dynamicLight = mapDynamicLights.remove(key);
/*     */         
/* 118 */         if (dynamicLight != null)
/*     */         {
/* 120 */           dynamicLight.updateLitChunks(renderGlobal);
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static int getCombinedLight(BlockPos pos, int combinedLight) {
/* 129 */     double lightPlayer = getLightLevel(pos);
/* 130 */     combinedLight = getCombinedLight(lightPlayer, combinedLight);
/* 131 */     return combinedLight;
/*     */   }
/*     */ 
/*     */   
/*     */   public static int getCombinedLight(Entity entity, int combinedLight) {
/* 136 */     double lightPlayer = getLightLevel(entity);
/* 137 */     combinedLight = getCombinedLight(lightPlayer, combinedLight);
/* 138 */     return combinedLight;
/*     */   }
/*     */ 
/*     */   
/*     */   public static int getCombinedLight(double lightPlayer, int combinedLight) {
/* 143 */     if (lightPlayer > 0.0D) {
/*     */       
/* 145 */       int lightPlayerFF = (int)(lightPlayer * 16.0D);
/* 146 */       int lightBlockFF = combinedLight & 0xFF;
/*     */       
/* 148 */       if (lightPlayerFF > lightBlockFF) {
/*     */         
/* 150 */         combinedLight &= 0xFFFFFF00;
/* 151 */         combinedLight |= lightPlayerFF;
/*     */       } 
/*     */     } 
/*     */     
/* 155 */     return combinedLight;
/*     */   }
/*     */ 
/*     */   
/*     */   public static double getLightLevel(BlockPos pos) {
/* 160 */     double lightLevelMax = 0.0D;
/* 161 */     Map<Integer, DynamicLight> lightPlayer = mapDynamicLights;
/*     */     
/* 163 */     synchronized (mapDynamicLights) {
/*     */       
/* 165 */       Collection<DynamicLight> dynamicLights = mapDynamicLights.values();
/* 166 */       Iterator<DynamicLight> it = dynamicLights.iterator();
/*     */ 
/*     */ 
/*     */       
/* 170 */       while (it.hasNext()) {
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 175 */         DynamicLight dynamicLight = it.next();
/* 176 */         int dynamicLightLevel = dynamicLight.getLastLightLevel();
/*     */         
/* 178 */         if (dynamicLightLevel > 0) {
/*     */           
/* 180 */           double px = dynamicLight.getLastPosX();
/* 181 */           double py = dynamicLight.getLastPosY();
/* 182 */           double pz = dynamicLight.getLastPosZ();
/* 183 */           double dx = pos.getX() - px;
/* 184 */           double dy = pos.getY() - py;
/* 185 */           double dz = pos.getZ() - pz;
/* 186 */           double distSq = dx * dx + dy * dy + dz * dz;
/*     */           
/* 188 */           if (dynamicLight.isUnderwater() && !Config.isClearWater()) {
/*     */             
/* 190 */             dynamicLightLevel = Config.limit(dynamicLightLevel - 2, 0, 15);
/* 191 */             distSq *= 2.0D;
/*     */           } 
/*     */           
/* 194 */           if (distSq <= 56.25D) {
/*     */             
/* 196 */             double dist = Math.sqrt(distSq);
/* 197 */             double light = 1.0D - dist / 7.5D;
/* 198 */             double lightLevel = light * dynamicLightLevel;
/*     */             
/* 200 */             if (lightLevel > lightLevelMax)
/*     */             {
/* 202 */               lightLevelMax = lightLevel;
/*     */             }
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 209 */     double lightPlayer1 = Config.limit(lightLevelMax, 0.0D, 15.0D);
/* 210 */     return lightPlayer1;
/*     */   }
/*     */ 
/*     */   
/*     */   public static int getLightLevel(ItemStack itemStack) {
/* 215 */     if (itemStack == null)
/*     */     {
/* 217 */       return 0;
/*     */     }
/*     */ 
/*     */     
/* 221 */     Item item = itemStack.getItem();
/*     */     
/* 223 */     if (item instanceof ItemBlock) {
/*     */       
/* 225 */       ItemBlock itemBlock = (ItemBlock)item;
/* 226 */       Block block = itemBlock.getBlock();
/*     */       
/* 228 */       if (block != null)
/*     */       {
/* 230 */         return block.getLightValue();
/*     */       }
/*     */     } 
/*     */     
/* 234 */     return (item == Items.lava_bucket) ? Blocks.lava.getLightValue() : ((item != Items.blaze_rod && item != Items.blaze_powder) ? ((item == Items.glowstone_dust) ? 8 : ((item == Items.prismarine_crystals) ? 8 : ((item == Items.magma_cream) ? 8 : ((item == Items.nether_star) ? (Blocks.beacon.getLightValue() / 2) : 0)))) : 10);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static int getLightLevel(Entity entity) {
/* 240 */     if (entity == Config.getMinecraft().func_175606_aa() && !Config.isDynamicHandLight())
/*     */     {
/* 242 */       return 0;
/*     */     }
/*     */ 
/*     */     
/* 246 */     if (entity instanceof EntityPlayer) {
/*     */       
/* 248 */       EntityPlayer entityItem = (EntityPlayer)entity;
/*     */       
/* 250 */       if (entityItem.func_175149_v())
/*     */       {
/* 252 */         return 0;
/*     */       }
/*     */     } 
/*     */     
/* 256 */     if (entity.isBurning())
/*     */     {
/* 258 */       return 15;
/*     */     }
/* 260 */     if (entity instanceof net.minecraft.entity.projectile.EntityFireball)
/*     */     {
/* 262 */       return 15;
/*     */     }
/* 264 */     if (entity instanceof net.minecraft.entity.item.EntityTNTPrimed)
/*     */     {
/* 266 */       return 15;
/*     */     }
/* 268 */     if (entity instanceof EntityBlaze) {
/*     */       
/* 270 */       EntityBlaze entityItem5 = (EntityBlaze)entity;
/* 271 */       return entityItem5.func_70845_n() ? 15 : 10;
/*     */     } 
/* 273 */     if (entity instanceof EntityMagmaCube) {
/*     */       
/* 275 */       EntityMagmaCube entityItem4 = (EntityMagmaCube)entity;
/* 276 */       return (entityItem4.squishFactor > 0.6D) ? 13 : 8;
/*     */     } 
/*     */ 
/*     */     
/* 280 */     if (entity instanceof EntityCreeper) {
/*     */       
/* 282 */       EntityCreeper entityItem1 = (EntityCreeper)entity;
/*     */       
/* 284 */       if (entityItem1.getCreeperFlashIntensity(0.0F) > 0.001D)
/*     */       {
/* 286 */         return 15;
/*     */       }
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 292 */     if (entity instanceof EntityLivingBase) {
/*     */       
/* 294 */       EntityLivingBase entityItem3 = (EntityLivingBase)entity;
/* 295 */       ItemStack itemStack = entityItem3.getHeldItem();
/* 296 */       int levelMain = getLightLevel(itemStack);
/* 297 */       ItemStack stackHead = entityItem3.getEquipmentInSlot(4);
/* 298 */       int levelHead = getLightLevel(stackHead);
/* 299 */       return Math.max(levelMain, levelHead);
/*     */     } 
/* 301 */     if (entity instanceof EntityItem) {
/*     */       
/* 303 */       EntityItem entityItem2 = (EntityItem)entity;
/* 304 */       ItemStack itemStack = getItemStack(entityItem2);
/* 305 */       return getLightLevel(itemStack);
/*     */     } 
/*     */ 
/*     */     
/* 309 */     return 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void removeLights(RenderGlobal renderGlobal) {
/* 317 */     Map<Integer, DynamicLight> var1 = mapDynamicLights;
/*     */     
/* 319 */     synchronized (mapDynamicLights) {
/*     */       
/* 321 */       Collection<DynamicLight> lights = mapDynamicLights.values();
/* 322 */       Iterator<DynamicLight> it = lights.iterator();
/*     */       
/* 324 */       while (it.hasNext()) {
/*     */         
/* 326 */         DynamicLight dynamicLight = it.next();
/* 327 */         it.remove();
/* 328 */         dynamicLight.updateLitChunks(renderGlobal);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static void clear() {
/* 335 */     Map<Integer, DynamicLight> var0 = mapDynamicLights;
/*     */     
/* 337 */     synchronized (mapDynamicLights) {
/*     */       
/* 339 */       mapDynamicLights.clear();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static int getCount() {
/* 345 */     Map<Integer, DynamicLight> var0 = mapDynamicLights;
/*     */     
/* 347 */     synchronized (mapDynamicLights) {
/*     */       
/* 349 */       return mapDynamicLights.size();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static ItemStack getItemStack(EntityItem entityItem) {
/* 355 */     ItemStack itemstack = entityItem.getDataWatcher().getWatchableObjectItemStack(10);
/* 356 */     return itemstack;
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\optifine\DynamicLights.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */