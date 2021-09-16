/*     */ package net.minecraft.entity;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import com.google.common.collect.Maps;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import net.minecraft.entity.ai.EntityMinecartMobSpawner;
/*     */ import net.minecraft.entity.boss.EntityDragon;
/*     */ import net.minecraft.entity.boss.EntityWither;
/*     */ import net.minecraft.entity.item.EntityArmorStand;
/*     */ import net.minecraft.entity.item.EntityBoat;
/*     */ import net.minecraft.entity.item.EntityEnderCrystal;
/*     */ import net.minecraft.entity.item.EntityEnderEye;
/*     */ import net.minecraft.entity.item.EntityEnderPearl;
/*     */ import net.minecraft.entity.item.EntityExpBottle;
/*     */ import net.minecraft.entity.item.EntityFallingBlock;
/*     */ import net.minecraft.entity.item.EntityFireworkRocket;
/*     */ import net.minecraft.entity.item.EntityItem;
/*     */ import net.minecraft.entity.item.EntityItemFrame;
/*     */ import net.minecraft.entity.item.EntityMinecart;
/*     */ import net.minecraft.entity.item.EntityMinecartChest;
/*     */ import net.minecraft.entity.item.EntityMinecartEmpty;
/*     */ import net.minecraft.entity.item.EntityMinecartFurnace;
/*     */ import net.minecraft.entity.item.EntityMinecartHopper;
/*     */ import net.minecraft.entity.item.EntityMinecartTNT;
/*     */ import net.minecraft.entity.item.EntityPainting;
/*     */ import net.minecraft.entity.item.EntityTNTPrimed;
/*     */ import net.minecraft.entity.item.EntityXPOrb;
/*     */ import net.minecraft.entity.monster.EntityBlaze;
/*     */ import net.minecraft.entity.monster.EntityCaveSpider;
/*     */ import net.minecraft.entity.monster.EntityCreeper;
/*     */ import net.minecraft.entity.monster.EntityEnderman;
/*     */ import net.minecraft.entity.monster.EntityEndermite;
/*     */ import net.minecraft.entity.monster.EntityGhast;
/*     */ import net.minecraft.entity.monster.EntityGiantZombie;
/*     */ import net.minecraft.entity.monster.EntityGuardian;
/*     */ import net.minecraft.entity.monster.EntityIronGolem;
/*     */ import net.minecraft.entity.monster.EntityMagmaCube;
/*     */ import net.minecraft.entity.monster.EntityMob;
/*     */ import net.minecraft.entity.monster.EntityPigZombie;
/*     */ import net.minecraft.entity.monster.EntitySilverfish;
/*     */ import net.minecraft.entity.monster.EntitySkeleton;
/*     */ import net.minecraft.entity.monster.EntitySlime;
/*     */ import net.minecraft.entity.monster.EntitySnowman;
/*     */ import net.minecraft.entity.monster.EntitySpider;
/*     */ import net.minecraft.entity.monster.EntityWitch;
/*     */ import net.minecraft.entity.monster.EntityZombie;
/*     */ import net.minecraft.entity.passive.EntityBat;
/*     */ import net.minecraft.entity.passive.EntityChicken;
/*     */ import net.minecraft.entity.passive.EntityCow;
/*     */ import net.minecraft.entity.passive.EntityHorse;
/*     */ import net.minecraft.entity.passive.EntityMooshroom;
/*     */ import net.minecraft.entity.passive.EntityOcelot;
/*     */ import net.minecraft.entity.passive.EntityPig;
/*     */ import net.minecraft.entity.passive.EntityRabbit;
/*     */ import net.minecraft.entity.passive.EntitySheep;
/*     */ import net.minecraft.entity.passive.EntitySquid;
/*     */ import net.minecraft.entity.passive.EntityVillager;
/*     */ import net.minecraft.entity.passive.EntityWolf;
/*     */ import net.minecraft.entity.projectile.EntityArrow;
/*     */ import net.minecraft.entity.projectile.EntityLargeFireball;
/*     */ import net.minecraft.entity.projectile.EntityPotion;
/*     */ import net.minecraft.entity.projectile.EntitySmallFireball;
/*     */ import net.minecraft.entity.projectile.EntitySnowball;
/*     */ import net.minecraft.entity.projectile.EntityWitherSkull;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.stats.StatBase;
/*     */ import net.minecraft.stats.StatList;
/*     */ import net.minecraft.world.World;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class EntityList
/*     */ {
/*  80 */   private static final Logger logger = LogManager.getLogger();
/*     */ 
/*     */   
/*  83 */   private static final Map stringToClassMapping = Maps.newHashMap();
/*     */ 
/*     */   
/*  86 */   private static final Map classToStringMapping = Maps.newHashMap();
/*     */ 
/*     */   
/*  89 */   private static final Map idToClassMapping = Maps.newHashMap();
/*     */ 
/*     */   
/*  92 */   private static final Map classToIDMapping = Maps.newHashMap();
/*  93 */   private static final Map field_180126_g = Maps.newHashMap();
/*     */ 
/*     */   
/*  96 */   public static final Map entityEggs = Maps.newLinkedHashMap();
/*     */ 
/*     */   
/*     */   private static final String __OBFID = "CL_00001538";
/*     */ 
/*     */ 
/*     */   
/*     */   private static void addMapping(Class<?> p_75618_0_, String p_75618_1_, int p_75618_2_) {
/* 104 */     if (stringToClassMapping.containsKey(p_75618_1_))
/*     */     {
/* 106 */       throw new IllegalArgumentException("ID is already registered: " + p_75618_1_);
/*     */     }
/* 108 */     if (idToClassMapping.containsKey(Integer.valueOf(p_75618_2_)))
/*     */     {
/* 110 */       throw new IllegalArgumentException("ID is already registered: " + p_75618_2_);
/*     */     }
/* 112 */     if (p_75618_2_ == 0)
/*     */     {
/* 114 */       throw new IllegalArgumentException("Cannot register to reserved id: " + p_75618_2_);
/*     */     }
/* 116 */     if (p_75618_0_ == null)
/*     */     {
/* 118 */       throw new IllegalArgumentException("Cannot register null clazz for id: " + p_75618_2_);
/*     */     }
/*     */ 
/*     */     
/* 122 */     stringToClassMapping.put(p_75618_1_, p_75618_0_);
/* 123 */     classToStringMapping.put(p_75618_0_, p_75618_1_);
/* 124 */     idToClassMapping.put(Integer.valueOf(p_75618_2_), p_75618_0_);
/* 125 */     classToIDMapping.put(p_75618_0_, Integer.valueOf(p_75618_2_));
/* 126 */     field_180126_g.put(p_75618_1_, Integer.valueOf(p_75618_2_));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static void addMapping(Class p_75614_0_, String p_75614_1_, int p_75614_2_, int p_75614_3_, int p_75614_4_) {
/* 135 */     addMapping(p_75614_0_, p_75614_1_, p_75614_2_);
/* 136 */     entityEggs.put(Integer.valueOf(p_75614_2_), new EntityEggInfo(p_75614_2_, p_75614_3_, p_75614_4_));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Entity createEntityByName(String p_75620_0_, World worldIn) {
/* 144 */     Entity var2 = null;
/*     */ 
/*     */     
/*     */     try {
/* 148 */       Class<Entity> var3 = (Class)stringToClassMapping.get(p_75620_0_);
/*     */       
/* 150 */       if (var3 != null)
/*     */       {
/* 152 */         var2 = var3.getConstructor(new Class[] { World.class }).newInstance(new Object[] { worldIn });
/*     */       }
/*     */     }
/* 155 */     catch (Exception var4) {
/*     */       
/* 157 */       var4.printStackTrace();
/*     */     } 
/*     */     
/* 160 */     return var2;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Entity createEntityFromNBT(NBTTagCompound p_75615_0_, World worldIn) {
/* 168 */     Entity var2 = null;
/*     */     
/* 170 */     if ("Minecart".equals(p_75615_0_.getString("id"))) {
/*     */       
/* 172 */       p_75615_0_.setString("id", EntityMinecart.EnumMinecartType.func_180038_a(p_75615_0_.getInteger("Type")).func_180040_b());
/* 173 */       p_75615_0_.removeTag("Type");
/*     */     } 
/*     */ 
/*     */     
/*     */     try {
/* 178 */       Class<Entity> var3 = (Class)stringToClassMapping.get(p_75615_0_.getString("id"));
/*     */       
/* 180 */       if (var3 != null)
/*     */       {
/* 182 */         var2 = var3.getConstructor(new Class[] { World.class }).newInstance(new Object[] { worldIn });
/*     */       }
/*     */     }
/* 185 */     catch (Exception var4) {
/*     */       
/* 187 */       var4.printStackTrace();
/*     */     } 
/*     */     
/* 190 */     if (var2 != null) {
/*     */       
/* 192 */       var2.readFromNBT(p_75615_0_);
/*     */     }
/*     */     else {
/*     */       
/* 196 */       logger.warn("Skipping Entity with id " + p_75615_0_.getString("id"));
/*     */     } 
/*     */     
/* 199 */     return var2;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Entity createEntityByID(int p_75616_0_, World worldIn) {
/* 207 */     Entity var2 = null;
/*     */ 
/*     */     
/*     */     try {
/* 211 */       Class<Entity> var3 = getClassFromID(p_75616_0_);
/*     */       
/* 213 */       if (var3 != null)
/*     */       {
/* 215 */         var2 = var3.getConstructor(new Class[] { World.class }).newInstance(new Object[] { worldIn });
/*     */       }
/*     */     }
/* 218 */     catch (Exception var4) {
/*     */       
/* 220 */       var4.printStackTrace();
/*     */     } 
/*     */     
/* 223 */     if (var2 == null)
/*     */     {
/* 225 */       logger.warn("Skipping Entity with id " + p_75616_0_);
/*     */     }
/*     */     
/* 228 */     return var2;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int getEntityID(Entity p_75619_0_) {
/* 236 */     Integer var1 = (Integer)classToIDMapping.get(p_75619_0_.getClass());
/* 237 */     return (var1 == null) ? 0 : var1.intValue();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Class getClassFromID(int p_90035_0_) {
/* 245 */     return (Class)idToClassMapping.get(Integer.valueOf(p_90035_0_));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String getEntityString(Entity p_75621_0_) {
/* 253 */     return (String)classToStringMapping.get(p_75621_0_.getClass());
/*     */   }
/*     */ 
/*     */   
/*     */   public static int func_180122_a(String p_180122_0_) {
/* 258 */     Integer var1 = (Integer)field_180126_g.get(p_180122_0_);
/* 259 */     return (var1 == null) ? 90 : var1.intValue();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String getStringFromID(int p_75617_0_) {
/* 267 */     return (String)classToStringMapping.get(getClassFromID(p_75617_0_));
/*     */   }
/*     */ 
/*     */   
/*     */   public static void func_151514_a() {}
/*     */   
/*     */   public static List func_180124_b() {
/* 274 */     Set var0 = stringToClassMapping.keySet();
/* 275 */     ArrayList<String> var1 = Lists.newArrayList();
/* 276 */     Iterator<String> var2 = var0.iterator();
/*     */     
/* 278 */     while (var2.hasNext()) {
/*     */       
/* 280 */       String var3 = var2.next();
/* 281 */       Class var4 = (Class)stringToClassMapping.get(var3);
/*     */       
/* 283 */       if ((var4.getModifiers() & 0x400) != 1024)
/*     */       {
/* 285 */         var1.add(var3);
/*     */       }
/*     */     } 
/*     */     
/* 289 */     var1.add("LightningBolt");
/* 290 */     return var1;
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean func_180123_a(Entity p_180123_0_, String p_180123_1_) {
/* 295 */     String var2 = getEntityString(p_180123_0_);
/*     */     
/* 297 */     if (var2 == null && p_180123_0_ instanceof net.minecraft.entity.player.EntityPlayer) {
/*     */       
/* 299 */       var2 = "Player";
/*     */     }
/* 301 */     else if (var2 == null && p_180123_0_ instanceof net.minecraft.entity.effect.EntityLightningBolt) {
/*     */       
/* 303 */       var2 = "LightningBolt";
/*     */     } 
/*     */     
/* 306 */     return p_180123_1_.equals(var2);
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean func_180125_b(String p_180125_0_) {
/* 311 */     return !(!"Player".equals(p_180125_0_) && !func_180124_b().contains(p_180125_0_));
/*     */   }
/*     */ 
/*     */   
/*     */   static {
/* 316 */     addMapping(EntityItem.class, "Item", 1);
/* 317 */     addMapping(EntityXPOrb.class, "XPOrb", 2);
/* 318 */     addMapping(EntityLeashKnot.class, "LeashKnot", 8);
/* 319 */     addMapping(EntityPainting.class, "Painting", 9);
/* 320 */     addMapping(EntityArrow.class, "Arrow", 10);
/* 321 */     addMapping(EntitySnowball.class, "Snowball", 11);
/* 322 */     addMapping(EntityLargeFireball.class, "Fireball", 12);
/* 323 */     addMapping(EntitySmallFireball.class, "SmallFireball", 13);
/* 324 */     addMapping(EntityEnderPearl.class, "ThrownEnderpearl", 14);
/* 325 */     addMapping(EntityEnderEye.class, "EyeOfEnderSignal", 15);
/* 326 */     addMapping(EntityPotion.class, "ThrownPotion", 16);
/* 327 */     addMapping(EntityExpBottle.class, "ThrownExpBottle", 17);
/* 328 */     addMapping(EntityItemFrame.class, "ItemFrame", 18);
/* 329 */     addMapping(EntityWitherSkull.class, "WitherSkull", 19);
/* 330 */     addMapping(EntityTNTPrimed.class, "PrimedTnt", 20);
/* 331 */     addMapping(EntityFallingBlock.class, "FallingSand", 21);
/* 332 */     addMapping(EntityFireworkRocket.class, "FireworksRocketEntity", 22);
/* 333 */     addMapping(EntityArmorStand.class, "ArmorStand", 30);
/* 334 */     addMapping(EntityBoat.class, "Boat", 41);
/* 335 */     addMapping(EntityMinecartEmpty.class, EntityMinecart.EnumMinecartType.RIDEABLE.func_180040_b(), 42);
/* 336 */     addMapping(EntityMinecartChest.class, EntityMinecart.EnumMinecartType.CHEST.func_180040_b(), 43);
/* 337 */     addMapping(EntityMinecartFurnace.class, EntityMinecart.EnumMinecartType.FURNACE.func_180040_b(), 44);
/* 338 */     addMapping(EntityMinecartTNT.class, EntityMinecart.EnumMinecartType.TNT.func_180040_b(), 45);
/* 339 */     addMapping(EntityMinecartHopper.class, EntityMinecart.EnumMinecartType.HOPPER.func_180040_b(), 46);
/* 340 */     addMapping(EntityMinecartMobSpawner.class, EntityMinecart.EnumMinecartType.SPAWNER.func_180040_b(), 47);
/* 341 */     addMapping(EntityMinecartCommandBlock.class, EntityMinecart.EnumMinecartType.COMMAND_BLOCK.func_180040_b(), 40);
/* 342 */     addMapping(EntityLiving.class, "Mob", 48);
/* 343 */     addMapping(EntityMob.class, "Monster", 49);
/* 344 */     addMapping(EntityCreeper.class, "Creeper", 50, 894731, 0);
/* 345 */     addMapping(EntitySkeleton.class, "Skeleton", 51, 12698049, 4802889);
/* 346 */     addMapping(EntitySpider.class, "Spider", 52, 3419431, 11013646);
/* 347 */     addMapping(EntityGiantZombie.class, "Giant", 53);
/* 348 */     addMapping(EntityZombie.class, "Zombie", 54, 44975, 7969893);
/* 349 */     addMapping(EntitySlime.class, "Slime", 55, 5349438, 8306542);
/* 350 */     addMapping(EntityGhast.class, "Ghast", 56, 16382457, 12369084);
/* 351 */     addMapping(EntityPigZombie.class, "PigZombie", 57, 15373203, 5009705);
/* 352 */     addMapping(EntityEnderman.class, "Enderman", 58, 1447446, 0);
/* 353 */     addMapping(EntityCaveSpider.class, "CaveSpider", 59, 803406, 11013646);
/* 354 */     addMapping(EntitySilverfish.class, "Silverfish", 60, 7237230, 3158064);
/* 355 */     addMapping(EntityBlaze.class, "Blaze", 61, 16167425, 16775294);
/* 356 */     addMapping(EntityMagmaCube.class, "LavaSlime", 62, 3407872, 16579584);
/* 357 */     addMapping(EntityDragon.class, "EnderDragon", 63);
/* 358 */     addMapping(EntityWither.class, "WitherBoss", 64);
/* 359 */     addMapping(EntityBat.class, "Bat", 65, 4996656, 986895);
/* 360 */     addMapping(EntityWitch.class, "Witch", 66, 3407872, 5349438);
/* 361 */     addMapping(EntityEndermite.class, "Endermite", 67, 1447446, 7237230);
/* 362 */     addMapping(EntityGuardian.class, "Guardian", 68, 5931634, 15826224);
/* 363 */     addMapping(EntityPig.class, "Pig", 90, 15771042, 14377823);
/* 364 */     addMapping(EntitySheep.class, "Sheep", 91, 15198183, 16758197);
/* 365 */     addMapping(EntityCow.class, "Cow", 92, 4470310, 10592673);
/* 366 */     addMapping(EntityChicken.class, "Chicken", 93, 10592673, 16711680);
/* 367 */     addMapping(EntitySquid.class, "Squid", 94, 2243405, 7375001);
/* 368 */     addMapping(EntityWolf.class, "Wolf", 95, 14144467, 13545366);
/* 369 */     addMapping(EntityMooshroom.class, "MushroomCow", 96, 10489616, 12040119);
/* 370 */     addMapping(EntitySnowman.class, "SnowMan", 97);
/* 371 */     addMapping(EntityOcelot.class, "Ozelot", 98, 15720061, 5653556);
/* 372 */     addMapping(EntityIronGolem.class, "VillagerGolem", 99);
/* 373 */     addMapping(EntityHorse.class, "EntityHorse", 100, 12623485, 15656192);
/* 374 */     addMapping(EntityRabbit.class, "Rabbit", 101, 10051392, 7555121);
/* 375 */     addMapping(EntityVillager.class, "Villager", 120, 5651507, 12422002);
/* 376 */     addMapping(EntityEnderCrystal.class, "EnderCrystal", 200);
/*     */   }
/*     */ 
/*     */   
/*     */   public static class EntityEggInfo
/*     */   {
/*     */     public final int spawnedID;
/*     */     public final int primaryColor;
/*     */     public final int secondaryColor;
/*     */     public final StatBase field_151512_d;
/*     */     public final StatBase field_151513_e;
/*     */     private static final String __OBFID = "CL_00001539";
/*     */     
/*     */     public EntityEggInfo(int p_i1583_1_, int p_i1583_2_, int p_i1583_3_) {
/* 390 */       this.spawnedID = p_i1583_1_;
/* 391 */       this.primaryColor = p_i1583_2_;
/* 392 */       this.secondaryColor = p_i1583_3_;
/* 393 */       this.field_151512_d = StatList.func_151182_a(this);
/* 394 */       this.field_151513_e = StatList.func_151176_b(this);
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\entity\EntityList.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */