/*     */ package net.minecraft.util;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import com.google.common.collect.Maps;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Map;
/*     */ 
/*     */ public enum EnumParticleTypes
/*     */ {
/*  10 */   EXPLOSION_NORMAL("EXPLOSION_NORMAL", 0, "explode", 0, true),
/*  11 */   EXPLOSION_LARGE("EXPLOSION_LARGE", 1, "largeexplode", 1, true),
/*  12 */   EXPLOSION_HUGE("EXPLOSION_HUGE", 2, "hugeexplosion", 2, true),
/*  13 */   FIREWORKS_SPARK("FIREWORKS_SPARK", 3, "fireworksSpark", 3, false),
/*  14 */   WATER_BUBBLE("WATER_BUBBLE", 4, "bubble", 4, false),
/*  15 */   WATER_SPLASH("WATER_SPLASH", 5, "splash", 5, false),
/*  16 */   WATER_WAKE("WATER_WAKE", 6, "wake", 6, false),
/*  17 */   SUSPENDED("SUSPENDED", 7, "suspended", 7, false),
/*  18 */   SUSPENDED_DEPTH("SUSPENDED_DEPTH", 8, "depthsuspend", 8, false),
/*  19 */   CRIT("CRIT", 9, "crit", 9, false),
/*  20 */   CRIT_MAGIC("CRIT_MAGIC", 10, "magicCrit", 10, false),
/*  21 */   SMOKE_NORMAL("SMOKE_NORMAL", 11, "smoke", 11, false),
/*  22 */   SMOKE_LARGE("SMOKE_LARGE", 12, "largesmoke", 12, false),
/*  23 */   SPELL("SPELL", 13, "spell", 13, false),
/*  24 */   SPELL_INSTANT("SPELL_INSTANT", 14, "instantSpell", 14, false),
/*  25 */   SPELL_MOB("SPELL_MOB", 15, "mobSpell", 15, false),
/*  26 */   SPELL_MOB_AMBIENT("SPELL_MOB_AMBIENT", 16, "mobSpellAmbient", 16, false),
/*  27 */   SPELL_WITCH("SPELL_WITCH", 17, "witchMagic", 17, false),
/*  28 */   DRIP_WATER("DRIP_WATER", 18, "dripWater", 18, false),
/*  29 */   DRIP_LAVA("DRIP_LAVA", 19, "dripLava", 19, false),
/*  30 */   VILLAGER_ANGRY("VILLAGER_ANGRY", 20, "angryVillager", 20, false),
/*  31 */   VILLAGER_HAPPY("VILLAGER_HAPPY", 21, "happyVillager", 21, false),
/*  32 */   TOWN_AURA("TOWN_AURA", 22, "townaura", 22, false),
/*  33 */   NOTE("NOTE", 23, "note", 23, false),
/*  34 */   PORTAL("PORTAL", 24, "portal", 24, false),
/*  35 */   ENCHANTMENT_TABLE("ENCHANTMENT_TABLE", 25, "enchantmenttable", 25, false),
/*  36 */   FLAME("FLAME", 26, "flame", 26, false),
/*  37 */   LAVA("LAVA", 27, "lava", 27, false),
/*  38 */   FOOTSTEP("FOOTSTEP", 28, "footstep", 28, false),
/*  39 */   CLOUD("CLOUD", 29, "cloud", 29, false),
/*  40 */   REDSTONE("REDSTONE", 30, "reddust", 30, false),
/*  41 */   SNOWBALL("SNOWBALL", 31, "snowballpoof", 31, false),
/*  42 */   SNOW_SHOVEL("SNOW_SHOVEL", 32, "snowshovel", 32, false),
/*  43 */   SLIME("SLIME", 33, "slime", 33, false),
/*  44 */   HEART("HEART", 34, "heart", 34, false),
/*  45 */   BARRIER("BARRIER", 35, "barrier", 35, false),
/*  46 */   ITEM_CRACK("ITEM_CRACK", 36, "iconcrack_", 36, false, 2),
/*  47 */   BLOCK_CRACK("BLOCK_CRACK", 37, "blockcrack_", 37, false, 1),
/*  48 */   BLOCK_DUST("BLOCK_DUST", 38, "blockdust_", 38, false, 1),
/*  49 */   WATER_DROP("WATER_DROP", 39, "droplet", 39, false),
/*  50 */   ITEM_TAKE("ITEM_TAKE", 40, "take", 40, false),
/*  51 */   MOB_APPEARANCE("MOB_APPEARANCE", 41, "mobappearance", 41, true);
/*     */   private final String field_179369_Q;
/*     */   private final int field_179372_R;
/*     */   
/*     */   static {
/*  56 */     field_179365_U = Maps.newHashMap();
/*     */ 
/*     */     
/*  59 */     $VALUES = new EnumParticleTypes[] { EXPLOSION_NORMAL, EXPLOSION_LARGE, EXPLOSION_HUGE, FIREWORKS_SPARK, WATER_BUBBLE, WATER_SPLASH, WATER_WAKE, SUSPENDED, SUSPENDED_DEPTH, CRIT, CRIT_MAGIC, SMOKE_NORMAL, SMOKE_LARGE, SPELL, SPELL_INSTANT, SPELL_MOB, SPELL_MOB_AMBIENT, SPELL_WITCH, DRIP_WATER, DRIP_LAVA, VILLAGER_ANGRY, VILLAGER_HAPPY, TOWN_AURA, NOTE, PORTAL, ENCHANTMENT_TABLE, FLAME, LAVA, FOOTSTEP, CLOUD, REDSTONE, SNOWBALL, SNOW_SHOVEL, SLIME, HEART, BARRIER, ITEM_CRACK, BLOCK_CRACK, BLOCK_DUST, WATER_DROP, ITEM_TAKE, MOB_APPEARANCE };
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
/* 111 */     ArrayList<String> var0 = Lists.newArrayList();
/* 112 */     EnumParticleTypes[] var1 = values();
/* 113 */     int var2 = var1.length;
/*     */     
/* 115 */     for (int var3 = 0; var3 < var2; var3++) {
/*     */       
/* 117 */       EnumParticleTypes var4 = var1[var3];
/* 118 */       field_179365_U.put(Integer.valueOf(var4.func_179348_c()), var4);
/*     */       
/* 120 */       if (!var4.func_179346_b().endsWith("_"))
/*     */       {
/* 122 */         var0.add(var4.func_179346_b());
/*     */       }
/*     */     } 
/*     */     
/* 126 */     field_179368_V = var0.<String>toArray(new String[var0.size()]);
/*     */   }
/*     */   
/*     */   private final boolean field_179371_S;
/*     */   private final int field_179366_T;
/*     */   private static final Map field_179365_U;
/*     */   private static final String[] field_179368_V;
/*     */   private static final EnumParticleTypes[] $VALUES;
/*     */   private static final String __OBFID = "CL_00002317";
/*     */   
/*     */   EnumParticleTypes(String p_i46011_1_, int p_i46011_2_, String p_i46011_3_, int p_i46011_4_, boolean p_i46011_5_, int p_i46011_6_) {
/*     */     this.field_179369_Q = p_i46011_3_;
/*     */     this.field_179372_R = p_i46011_4_;
/*     */     this.field_179371_S = p_i46011_5_;
/*     */     this.field_179366_T = p_i46011_6_;
/*     */   }
/*     */   
/*     */   public static String[] func_179349_a() {
/*     */     return field_179368_V;
/*     */   }
/*     */   
/*     */   public String func_179346_b() {
/*     */     return this.field_179369_Q;
/*     */   }
/*     */   
/*     */   public int func_179348_c() {
/*     */     return this.field_179372_R;
/*     */   }
/*     */   
/*     */   public int func_179345_d() {
/*     */     return this.field_179366_T;
/*     */   }
/*     */   
/*     */   public boolean func_179344_e() {
/*     */     return this.field_179371_S;
/*     */   }
/*     */   
/*     */   public boolean func_179343_f() {
/*     */     return (this.field_179366_T > 0);
/*     */   }
/*     */   
/*     */   public static EnumParticleTypes func_179342_a(int p_179342_0_) {
/*     */     return (EnumParticleTypes)field_179365_U.get(Integer.valueOf(p_179342_0_));
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraf\\util\EnumParticleTypes.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */