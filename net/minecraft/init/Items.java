/*     */ package net.minecraft.init;
/*     */ 
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemArmor;
/*     */ import net.minecraft.item.ItemArmorStand;
/*     */ import net.minecraft.item.ItemBow;
/*     */ import net.minecraft.item.ItemEmptyMap;
/*     */ import net.minecraft.item.ItemEnchantedBook;
/*     */ import net.minecraft.item.ItemFishingRod;
/*     */ import net.minecraft.item.ItemMap;
/*     */ import net.minecraft.item.ItemPotion;
/*     */ import net.minecraft.item.ItemShears;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Items
/*     */ {
/*     */   private static Item getRegisteredItem(String p_179554_0_) {
/* 208 */     return (Item)Item.itemRegistry.getObject(new ResourceLocation(p_179554_0_));
/*     */   }
/*     */ 
/*     */   
/*     */   static {
/* 213 */     if (!Bootstrap.isRegistered())
/*     */     {
/* 215 */       throw new RuntimeException("Accessed Items before Bootstrap!");
/*     */     }
/*     */   }
/*     */   
/* 219 */   public static final Item iron_shovel = getRegisteredItem("iron_shovel");
/* 220 */   public static final Item iron_pickaxe = getRegisteredItem("iron_pickaxe");
/* 221 */   public static final Item iron_axe = getRegisteredItem("iron_axe");
/* 222 */   public static final Item flint_and_steel = getRegisteredItem("flint_and_steel");
/* 223 */   public static final Item apple = getRegisteredItem("apple");
/* 224 */   public static final ItemBow bow = (ItemBow)getRegisteredItem("bow");
/* 225 */   public static final Item arrow = getRegisteredItem("arrow");
/* 226 */   public static final Item coal = getRegisteredItem("coal");
/* 227 */   public static final Item diamond = getRegisteredItem("diamond");
/* 228 */   public static final Item iron_ingot = getRegisteredItem("iron_ingot");
/* 229 */   public static final Item gold_ingot = getRegisteredItem("gold_ingot");
/* 230 */   public static final Item iron_sword = getRegisteredItem("iron_sword");
/* 231 */   public static final Item wooden_sword = getRegisteredItem("wooden_sword");
/* 232 */   public static final Item wooden_shovel = getRegisteredItem("wooden_shovel");
/* 233 */   public static final Item wooden_pickaxe = getRegisteredItem("wooden_pickaxe");
/* 234 */   public static final Item wooden_axe = getRegisteredItem("wooden_axe");
/* 235 */   public static final Item stone_sword = getRegisteredItem("stone_sword");
/* 236 */   public static final Item stone_shovel = getRegisteredItem("stone_shovel");
/* 237 */   public static final Item stone_pickaxe = getRegisteredItem("stone_pickaxe");
/* 238 */   public static final Item stone_axe = getRegisteredItem("stone_axe");
/* 239 */   public static final Item diamond_sword = getRegisteredItem("diamond_sword");
/* 240 */   public static final Item diamond_shovel = getRegisteredItem("diamond_shovel");
/* 241 */   public static final Item diamond_pickaxe = getRegisteredItem("diamond_pickaxe");
/* 242 */   public static final Item diamond_axe = getRegisteredItem("diamond_axe");
/* 243 */   public static final Item stick = getRegisteredItem("stick");
/* 244 */   public static final Item bowl = getRegisteredItem("bowl");
/* 245 */   public static final Item mushroom_stew = getRegisteredItem("mushroom_stew");
/* 246 */   public static final Item golden_sword = getRegisteredItem("golden_sword");
/* 247 */   public static final Item golden_shovel = getRegisteredItem("golden_shovel");
/* 248 */   public static final Item golden_pickaxe = getRegisteredItem("golden_pickaxe");
/* 249 */   public static final Item golden_axe = getRegisteredItem("golden_axe");
/* 250 */   public static final Item string = getRegisteredItem("string");
/* 251 */   public static final Item feather = getRegisteredItem("feather");
/* 252 */   public static final Item gunpowder = getRegisteredItem("gunpowder");
/* 253 */   public static final Item wooden_hoe = getRegisteredItem("wooden_hoe");
/* 254 */   public static final Item stone_hoe = getRegisteredItem("stone_hoe");
/* 255 */   public static final Item iron_hoe = getRegisteredItem("iron_hoe");
/* 256 */   public static final Item diamond_hoe = getRegisteredItem("diamond_hoe");
/* 257 */   public static final Item golden_hoe = getRegisteredItem("golden_hoe");
/* 258 */   public static final Item wheat_seeds = getRegisteredItem("wheat_seeds");
/* 259 */   public static final Item wheat = getRegisteredItem("wheat");
/* 260 */   public static final Item bread = getRegisteredItem("bread");
/* 261 */   public static final ItemArmor leather_helmet = (ItemArmor)getRegisteredItem("leather_helmet");
/* 262 */   public static final ItemArmor leather_chestplate = (ItemArmor)getRegisteredItem("leather_chestplate");
/* 263 */   public static final ItemArmor leather_leggings = (ItemArmor)getRegisteredItem("leather_leggings");
/* 264 */   public static final ItemArmor leather_boots = (ItemArmor)getRegisteredItem("leather_boots");
/* 265 */   public static final ItemArmor chainmail_helmet = (ItemArmor)getRegisteredItem("chainmail_helmet");
/* 266 */   public static final ItemArmor chainmail_chestplate = (ItemArmor)getRegisteredItem("chainmail_chestplate");
/* 267 */   public static final ItemArmor chainmail_leggings = (ItemArmor)getRegisteredItem("chainmail_leggings");
/* 268 */   public static final ItemArmor chainmail_boots = (ItemArmor)getRegisteredItem("chainmail_boots");
/* 269 */   public static final ItemArmor iron_helmet = (ItemArmor)getRegisteredItem("iron_helmet");
/* 270 */   public static final ItemArmor iron_chestplate = (ItemArmor)getRegisteredItem("iron_chestplate");
/* 271 */   public static final ItemArmor iron_leggings = (ItemArmor)getRegisteredItem("iron_leggings");
/* 272 */   public static final ItemArmor iron_boots = (ItemArmor)getRegisteredItem("iron_boots");
/* 273 */   public static final ItemArmor diamond_helmet = (ItemArmor)getRegisteredItem("diamond_helmet");
/* 274 */   public static final ItemArmor diamond_chestplate = (ItemArmor)getRegisteredItem("diamond_chestplate");
/* 275 */   public static final ItemArmor diamond_leggings = (ItemArmor)getRegisteredItem("diamond_leggings");
/* 276 */   public static final ItemArmor diamond_boots = (ItemArmor)getRegisteredItem("diamond_boots");
/* 277 */   public static final ItemArmor golden_helmet = (ItemArmor)getRegisteredItem("golden_helmet");
/* 278 */   public static final ItemArmor golden_chestplate = (ItemArmor)getRegisteredItem("golden_chestplate");
/* 279 */   public static final ItemArmor golden_leggings = (ItemArmor)getRegisteredItem("golden_leggings");
/* 280 */   public static final ItemArmor golden_boots = (ItemArmor)getRegisteredItem("golden_boots");
/* 281 */   public static final Item flint = getRegisteredItem("flint");
/* 282 */   public static final Item porkchop = getRegisteredItem("porkchop");
/* 283 */   public static final Item cooked_porkchop = getRegisteredItem("cooked_porkchop");
/* 284 */   public static final Item painting = getRegisteredItem("painting");
/* 285 */   public static final Item golden_apple = getRegisteredItem("golden_apple");
/* 286 */   public static final Item sign = getRegisteredItem("sign");
/* 287 */   public static final Item oak_door = getRegisteredItem("wooden_door");
/* 288 */   public static final Item spruce_door = getRegisteredItem("spruce_door");
/* 289 */   public static final Item birch_door = getRegisteredItem("birch_door");
/* 290 */   public static final Item jungle_door = getRegisteredItem("jungle_door");
/* 291 */   public static final Item acacia_door = getRegisteredItem("acacia_door");
/* 292 */   public static final Item dark_oak_door = getRegisteredItem("dark_oak_door");
/* 293 */   public static final Item bucket = getRegisteredItem("bucket");
/* 294 */   public static final Item water_bucket = getRegisteredItem("water_bucket");
/* 295 */   public static final Item lava_bucket = getRegisteredItem("lava_bucket");
/* 296 */   public static final Item minecart = getRegisteredItem("minecart");
/* 297 */   public static final Item saddle = getRegisteredItem("saddle");
/* 298 */   public static final Item iron_door = getRegisteredItem("iron_door");
/* 299 */   public static final Item redstone = getRegisteredItem("redstone");
/* 300 */   public static final Item snowball = getRegisteredItem("snowball");
/* 301 */   public static final Item boat = getRegisteredItem("boat");
/* 302 */   public static final Item leather = getRegisteredItem("leather");
/* 303 */   public static final Item milk_bucket = getRegisteredItem("milk_bucket");
/* 304 */   public static final Item brick = getRegisteredItem("brick");
/* 305 */   public static final Item clay_ball = getRegisteredItem("clay_ball");
/* 306 */   public static final Item reeds = getRegisteredItem("reeds");
/* 307 */   public static final Item paper = getRegisteredItem("paper");
/* 308 */   public static final Item book = getRegisteredItem("book");
/* 309 */   public static final Item slime_ball = getRegisteredItem("slime_ball");
/* 310 */   public static final Item chest_minecart = getRegisteredItem("chest_minecart");
/* 311 */   public static final Item furnace_minecart = getRegisteredItem("furnace_minecart");
/* 312 */   public static final Item egg = getRegisteredItem("egg");
/* 313 */   public static final Item compass = getRegisteredItem("compass");
/* 314 */   public static final ItemFishingRod fishing_rod = (ItemFishingRod)getRegisteredItem("fishing_rod");
/* 315 */   public static final Item clock = getRegisteredItem("clock");
/* 316 */   public static final Item glowstone_dust = getRegisteredItem("glowstone_dust");
/* 317 */   public static final Item fish = getRegisteredItem("fish");
/* 318 */   public static final Item cooked_fish = getRegisteredItem("cooked_fish");
/* 319 */   public static final Item dye = getRegisteredItem("dye");
/* 320 */   public static final Item bone = getRegisteredItem("bone");
/* 321 */   public static final Item sugar = getRegisteredItem("sugar");
/* 322 */   public static final Item cake = getRegisteredItem("cake");
/* 323 */   public static final Item bed = getRegisteredItem("bed");
/* 324 */   public static final Item repeater = getRegisteredItem("repeater");
/* 325 */   public static final Item cookie = getRegisteredItem("cookie");
/* 326 */   public static final ItemMap filled_map = (ItemMap)getRegisteredItem("filled_map");
/* 327 */   public static final ItemShears shears = (ItemShears)getRegisteredItem("shears");
/* 328 */   public static final Item melon = getRegisteredItem("melon");
/* 329 */   public static final Item pumpkin_seeds = getRegisteredItem("pumpkin_seeds");
/* 330 */   public static final Item melon_seeds = getRegisteredItem("melon_seeds");
/* 331 */   public static final Item beef = getRegisteredItem("beef");
/* 332 */   public static final Item cooked_beef = getRegisteredItem("cooked_beef");
/* 333 */   public static final Item chicken = getRegisteredItem("chicken");
/* 334 */   public static final Item cooked_chicken = getRegisteredItem("cooked_chicken");
/* 335 */   public static final Item mutton = getRegisteredItem("mutton");
/* 336 */   public static final Item cooked_mutton = getRegisteredItem("cooked_mutton");
/* 337 */   public static final Item rabbit = getRegisteredItem("rabbit");
/* 338 */   public static final Item cooked_rabbit = getRegisteredItem("cooked_rabbit");
/* 339 */   public static final Item rabbit_stew = getRegisteredItem("rabbit_stew");
/* 340 */   public static final Item rabbit_foot = getRegisteredItem("rabbit_foot");
/* 341 */   public static final Item rabbit_hide = getRegisteredItem("rabbit_hide");
/* 342 */   public static final Item rotten_flesh = getRegisteredItem("rotten_flesh");
/* 343 */   public static final Item ender_pearl = getRegisteredItem("ender_pearl");
/* 344 */   public static final Item blaze_rod = getRegisteredItem("blaze_rod");
/* 345 */   public static final Item ghast_tear = getRegisteredItem("ghast_tear");
/* 346 */   public static final Item gold_nugget = getRegisteredItem("gold_nugget");
/* 347 */   public static final Item nether_wart = getRegisteredItem("nether_wart");
/* 348 */   public static final ItemPotion potionitem = (ItemPotion)getRegisteredItem("potion");
/* 349 */   public static final Item glass_bottle = getRegisteredItem("glass_bottle");
/* 350 */   public static final Item spider_eye = getRegisteredItem("spider_eye");
/* 351 */   public static final Item fermented_spider_eye = getRegisteredItem("fermented_spider_eye");
/* 352 */   public static final Item blaze_powder = getRegisteredItem("blaze_powder");
/* 353 */   public static final Item magma_cream = getRegisteredItem("magma_cream");
/* 354 */   public static final Item brewing_stand = getRegisteredItem("brewing_stand");
/* 355 */   public static final Item cauldron = getRegisteredItem("cauldron");
/* 356 */   public static final Item ender_eye = getRegisteredItem("ender_eye");
/* 357 */   public static final Item speckled_melon = getRegisteredItem("speckled_melon");
/* 358 */   public static final Item spawn_egg = getRegisteredItem("spawn_egg");
/* 359 */   public static final Item experience_bottle = getRegisteredItem("experience_bottle");
/* 360 */   public static final Item fire_charge = getRegisteredItem("fire_charge");
/* 361 */   public static final Item writable_book = getRegisteredItem("writable_book");
/* 362 */   public static final Item written_book = getRegisteredItem("written_book");
/* 363 */   public static final Item emerald = getRegisteredItem("emerald");
/* 364 */   public static final Item item_frame = getRegisteredItem("item_frame");
/* 365 */   public static final Item flower_pot = getRegisteredItem("flower_pot");
/* 366 */   public static final Item carrot = getRegisteredItem("carrot");
/* 367 */   public static final Item potato = getRegisteredItem("potato");
/* 368 */   public static final Item baked_potato = getRegisteredItem("baked_potato");
/* 369 */   public static final Item poisonous_potato = getRegisteredItem("poisonous_potato");
/* 370 */   public static final ItemEmptyMap map = (ItemEmptyMap)getRegisteredItem("map");
/* 371 */   public static final Item golden_carrot = getRegisteredItem("golden_carrot");
/* 372 */   public static final Item skull = getRegisteredItem("skull");
/* 373 */   public static final Item carrot_on_a_stick = getRegisteredItem("carrot_on_a_stick");
/* 374 */   public static final Item nether_star = getRegisteredItem("nether_star");
/* 375 */   public static final Item pumpkin_pie = getRegisteredItem("pumpkin_pie");
/* 376 */   public static final Item fireworks = getRegisteredItem("fireworks");
/* 377 */   public static final Item firework_charge = getRegisteredItem("firework_charge");
/* 378 */   public static final ItemEnchantedBook enchanted_book = (ItemEnchantedBook)getRegisteredItem("enchanted_book");
/* 379 */   public static final Item comparator = getRegisteredItem("comparator");
/* 380 */   public static final Item netherbrick = getRegisteredItem("netherbrick");
/* 381 */   public static final Item quartz = getRegisteredItem("quartz");
/* 382 */   public static final Item tnt_minecart = getRegisteredItem("tnt_minecart");
/* 383 */   public static final Item hopper_minecart = getRegisteredItem("hopper_minecart");
/* 384 */   public static final ItemArmorStand armor_stand = (ItemArmorStand)getRegisteredItem("armor_stand");
/* 385 */   public static final Item iron_horse_armor = getRegisteredItem("iron_horse_armor");
/* 386 */   public static final Item golden_horse_armor = getRegisteredItem("golden_horse_armor");
/* 387 */   public static final Item diamond_horse_armor = getRegisteredItem("diamond_horse_armor");
/* 388 */   public static final Item lead = getRegisteredItem("lead");
/* 389 */   public static final Item name_tag = getRegisteredItem("name_tag");
/* 390 */   public static final Item command_block_minecart = getRegisteredItem("command_block_minecart");
/* 391 */   public static final Item record_13 = getRegisteredItem("record_13");
/* 392 */   public static final Item record_cat = getRegisteredItem("record_cat");
/* 393 */   public static final Item record_blocks = getRegisteredItem("record_blocks");
/* 394 */   public static final Item record_chirp = getRegisteredItem("record_chirp");
/* 395 */   public static final Item record_far = getRegisteredItem("record_far");
/* 396 */   public static final Item record_mall = getRegisteredItem("record_mall");
/* 397 */   public static final Item record_mellohi = getRegisteredItem("record_mellohi");
/* 398 */   public static final Item record_stal = getRegisteredItem("record_stal");
/* 399 */   public static final Item record_strad = getRegisteredItem("record_strad");
/* 400 */   public static final Item record_ward = getRegisteredItem("record_ward");
/* 401 */   public static final Item record_11 = getRegisteredItem("record_11");
/* 402 */   public static final Item record_wait = getRegisteredItem("record_wait");
/* 403 */   public static final Item prismarine_shard = getRegisteredItem("prismarine_shard");
/* 404 */   public static final Item prismarine_crystals = getRegisteredItem("prismarine_crystals");
/* 405 */   public static final Item banner = getRegisteredItem("banner");
/*     */   private static final String __OBFID = "CL_00000044";
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\init\Items.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */